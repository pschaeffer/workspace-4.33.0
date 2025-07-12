package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.time.Instant; 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
/**
 * Class for supporting the tree of nodes
 *
 * Each instance of this class describes one node in the node tree. The top
 * level node is called the top node. The top node has subnodes for each
 * company. Of course, the top node may have zero, one, or more than one company
 * subnodes. If the pass-through editor is in use, then the top node will have
 * exactly two subnodes. They are 'Companies' (without the quotes) and 'Reports'
 * (without the quotes).
 * 
 * if the pass-through editor is in use, then each company wiil be a subnode of
 * the 'Companies' (without the quotes) node.
 *
 * Each company node has zero or more subnodes. Each subnode of a company node
 * is a division node if the pass-through editor is not in use. The number of
 * division nodes may be zero or greater than zero.
 * 
 * If the pass-through editor is in use, then each company node, will have
 * exactly four subnodes. They are 'Data', 'Ignore Lists', 'Reports', and
 * 'Rules' (all without the quotes). Of course, in this case, division nodes
 * are subnodes of the Rules node and also subnodes of the Data node.
 *
 * Each division node has zero or more subnodes. Each subnode of a division node
 * is a site node. The number of site nodes may be zero or greater than zero.
 *
 * Each site node has zero or more subnodes. Each subnode of a site node is a
 * modification node. The number of modification nodes may be zero or greater
 * than zero.
 *
 * In all cases, child nodes are stored in the children array. Child nodes must
 * always be in ascending name order. This order must be maintained when new
 * child nodes are inserted into the children array.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmTree {
	/* The next statement initializes logging to some degree. Note that having the
	   slf4j jars and the log4j jars in the classpath also plays some role in
	   logging initialization. */
	private static final Logger   LOG = LoggerFactory.getLogger(HDLmTree.class);
	/* Build the (initially) empty top node. We would like to build the correct top
	   node here. However, we can not. The tree top is statically initialized. */
	private static HDLmTree   nodeModTreeTop = addTop();
	/* Build the (initially) empty top node. We would like to build the correct top
	   node here. However, we can not. The tree top is statically initialized. */
	private static HDLmTree   nodePassTreeTop = addTop();
	/* Some very important details about the data row with all of the tree nodes and
	   modification rules is saved when the data row is fetched from the server.
	   This information is used later to update the data row.
	   
	   The map now stores saved information for modifications and any other content
	   types we need to care about. The key is the content type (all in uppercase).
	   The object is the actual saved details. Since order does not make any
	   difference, we can use a hash map here. */
	private static Map<String, Map<String, Object>> savedDetailsMap = new HashMap<String, Map<String, Object>>();
	/* The next field (an array) contains all of the pending deletes from
     the database maintained by the server. Each delete is a string which 
     is really an ID value. Each of the pending deletes is obtained from a 
     tree node or something like a tree node. */
  private static ArrayList<String>  pendingDeletes = new ArrayList<String>();
  /* The next field (an array) contains all of the pending inserts into
     the database maintained by the server. Each insert is a string with 
     no ID value. The server will provide the ID values that will be added
     to each tree node. Each of the pending inserts is obtained from a 
     tree node or something like a tree node. */
  private static ArrayList<String>  pendingInserts = new ArrayList<String>();
  /* The next field (an array) contains all of the pending updates. The 
     updates are eventually sent to the server and are used to update the
     database. Each update has a new value for a tree node (or something
     like a tree node) and an ID value. The ID value is used to identify
     the server database row that must be updated. */
  private static ArrayList<String>  pendingUpdates = new ArrayList<String>();	
	/* All instances of the HDLmTree class have a standard set of fields. These
	   fields are initialized by the constructor. Details are really only used by
	   HDLmTree instances for modifications and a few other types. */
	private HDLmTreeTypes         type;
	private String                tooltip;
	/* The details would appear to be a instance of the HDLmMod class. This may be
	   true. However, in some cases, the details will actually be a class that
	   extends the HDLmMod class. For example, the details might actually be an
	   instance of the HDLmProxy class. */
	private HDLmMod               details;
	private ArrayList<String>     nodePath;
	private String                id;
	private ArrayList<HDLmTree>   children;
	/* This is the standard (and only) constructor for this class. Build a tree
	   instance. The caller provides the name and type. Initially, each tree
	   instance has no children. Children can be added later. Note that many of the
	   values can be null. This is not considered to be an error condition in this
	   case.
	   
	   The details are not set by this constructor. They can be set later as need
	   be. The node path passed by the caller is copied below. This means that the
	   node path passed by the caller can be changed as need be. */
	protected HDLmTree(final HDLmTreeTypes newType, 
			               final String newTooltip, 
			               ArrayList<String> newNodePath) {
		if (newType == null) {
			String  errorText = "New type value passed to tree object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (newType == HDLmTreeTypes.NONE) {
			String  errorText = "New type value passed to tree object constructor is not set";
			HDLmAssertAction(false, errorText);
		}
		if (newTooltip == null) {
			String  errorText = "New tooltip value passed to tree object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (newNodePath == null) {
			String  errorText = "New node path value passed to tree object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Before we actually construct the HDLmTree object, we need to make sure the
		   last entry in the node path is the actual node name */
		int newNodePathSize = newNodePath.size();
		this.details = null;
		this.setType(newType);
		this.setTooltip(newTooltip);
		/* We need to make a copy of the node path passed by the caller. The node
		   path array passed by the caller, might be modified. We don't want the
		   modifications to impact this object instance. */
		newNodePath = new ArrayList<String>(newNodePath);
		this.setNodePath(newNodePath);
		this.setChildren(new ArrayList<HDLmTree>());
		/* Set the initial value of the ID string for an HDLmTree instance to null */
		this.setIdNull();
	}
	/* Add a set of levels to the node tree. The actual node tree has
	   several levels. The intermediate levels (company, Data or Rules,
	   division, site) may or may not exist. Create these levels if 
	   need be using the information provided by the caller.  */
	protected static ArrayList<String>  addLevels(final HDLmTree topTreeNode, 
			                                          final String hostName,
			                                          final HDLmTreeTypes nodeType) {
		/* Check if the top tree node passed by the caller is null */
		if (topTreeNode == null) {
			String  errorText = "Top tree node value passed to addLevels is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name value passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name value passed to addLevels is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the node type value passed by the caller is null */
		if (nodeType == null) {
			String  errorText = "Node type value passed to addLevels is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a boolean value showing if any inserts have
		   actually been done */ 
		boolean   insertsDone = false;
		/* Locate or add a set of levels to the modifications tree.
		   The idea here is that we either find or create all of 
		   tree levels that are needed to finally build the leaf
		   node (the new modification rule or saved data value). */
		ArrayList<String>   nodePath = new ArrayList<String>();
		HDLmTree  nodeParent = null;
		int       nodeLevels = 6;
		for (int i = 0; i < nodeLevels; i++) {
			String  nodeName = null;
			/* Create an enum for the node type */
			HDLmTreeTypes   nodeEnum = null;
			/* Set the correct node enum value based on the loop index */
			if ((i+1) == 1)
				nodeEnum = HDLmTreeTypes.TOP;
			else if ((i+1) == 2)
				nodeEnum = HDLmTreeTypes.COMPANIES;
			else if ((i+1) == 3)
				nodeEnum = HDLmTreeTypes.COMPANY;
			else if ((i+1) == 4) {
				if (nodeType == HDLmTreeTypes.DATA)
				  nodeEnum = HDLmTreeTypes.DATA;
				if (nodeType == HDLmTreeTypes.MOD)
				  nodeEnum = HDLmTreeTypes.RULES;
				if (nodeType == HDLmTreeTypes.RULES)
				  nodeEnum = HDLmTreeTypes.RULES;
				if (nodeType == HDLmTreeTypes.VALUE)
				  nodeEnum = HDLmTreeTypes.DATA;
			}
			else if ((i+1) == 5)
				nodeEnum = HDLmTreeTypes.DIVISION;
			else if ((i+1) == 6)
				nodeEnum = HDLmTreeTypes.SITE;
			else if ((i+1) == 7) {
				if (nodeType == HDLmTreeTypes.DATA)
				  nodeEnum = HDLmTreeTypes.VALUE;
				if (nodeType == HDLmTreeTypes.RULES)
				  nodeEnum = HDLmTreeTypes.MOD;
			}
			/* Determine what we need to add to the node path */
			switch (nodeEnum) {
			  case TOP: {
			  	nodeName = HDLmDefines.getString("HDLMTOPNODENAME");
			  	break;
			  }
			  case COMPANIES: {
			  	nodeName = HDLmDefines.getString("HDLMCOMPANIESNODENAME");
			  	break;
			  }
			  case COMPANY: {
			  	nodeName = hostName;
			  	break;
			  }
			  case DATA: {
			  	nodeName = HDLmDefines.getString("HDLMDATANODENAME");
			  	break;
			  }
			  case RULES: {
			  	nodeName = HDLmDefines.getString("HDLMRULESNODENAME");
			  	break;
			  }
			  case DIVISION: {
			  	nodeName = HDLmDefines.getString("HDLMDIVISIONNODENAME");
			  	break;
			  }
			  case SITE: {
			  	nodeName = HDLmDefines.getString("HDLMSITENODENAME");
			  	break;
			  }
			  case MOD: {
			  	nodeName = null;
			  	break;
			  }
			  case VALUE: {
			  	nodeName = null;
			  	break;
			  }
			  /* Report an error if none of the cases matched. At least
			     one of the cases should have matched. The error text
			     actually shows the loop index value (plus one) that
			     failed. This is likely to be more informative than 
			     actual enum that did not match. */
			  default: {
		    	String  errorFormat = "No tree type enum value (%d) matched in addLevels";
					String  errorText = String.format(errorFormat, i+1);
					HDLmAssertAction(false, errorText);			  	
			  }
			}
			/* Update the node path with the new name value */
			nodePath.add(nodeName);
			/* Try to find the tree node for the current node path. 
			   This may or may not work. It is not an error for this
			   operation not to work */
			HDLmTree  nodeTree = HDLmTree.locateTreeNode(topTreeNode, nodePath);	
			/* The locate operation may have succeeded. We need to save the 
			   tree node so that it can serve as a parent tree node later.
			   Later means the next time this loop is executed. */
			if (nodeTree != null) {
				nodeParent = nodeTree;
				continue;
			}
			/* Check if the thing that was not found, was a Top node. This should
	       never really happen because a Top node should always exist. */ 
		  if (nodeEnum == HDLmTreeTypes.TOP) { 
		    HDLmAssertAction(false, "Top tree node not found");
		  }
			/* Check if the thing that was not found, was a Companies node. This should
	       never really happen because a Top node should always have a Companies
	       node. */ 
		  if (nodeEnum == HDLmTreeTypes.COMPANIES) { 
		    HDLmAssertAction(false, "Companies tree node not found under Top tree node");
		  }		  
			/* Check if the thing that was not found, was a company. This can
			   really happen, if a new company is being added to the node tree. */
			if (nodeEnum == HDLmTreeTypes.COMPANY) {			
		  	HDLmPassThruCompany   company; 
		    company = HDLmPassThruCompany.addCompanyExtended(hostName);	;
				if (company == null) 
					HDLmAssertAction(false, "PassThru company reference not returned by add company extended routine");				
		    /* Save the newly created node tree reference so that it can become
		       the parent of the next tree node that is created */
				nodeTree = HDLmTree.locateTreeNode(topTreeNode, nodePath);
		    nodeParent = nodeTree;
		    /* We don't need to add an insert here because the company node was 
		       already written out to the database */ 
		    if (1 == 2) {
		      HDLmTree.addPendingInserts(nodeParent);
		      insertsDone = true;
		    }
			}
			/* Check if the thing that was not found, was a Data node. This should
	       never really happen because a company node should always have a Data
	       node under it. */ 
		  if (nodeEnum == HDLmTreeTypes.DATA) { 
		    HDLmAssertAction(false, "Data tree node not found under company tree node");
		  }
			/* Check if the thing that was not found, was a Rules node. This should
		     never really happen because a company node should always have a Rules
		     node under it. */ 
			if (nodeEnum == HDLmTreeTypes.RULES) { 
			  HDLmAssertAction(false, "Rules tree node not found under company tree node");
			}
			/* Check if the thing that was not found, was a division. This can
		     really happen, if a new company is being added to the node tree. */
		  if (nodeEnum == HDLmTreeTypes.DIVISION) {	
				/* Get some information about the current instance */
		  	ArrayList<String>   divisionNodePath = new ArrayList<String>(nodePath);
				String              divisionNodeName = HDLmDefines.getString("HDLMDIVISIONNODENAME");
				String              divisionNodeTypeStringLowerCase = nodeEnum.toString().toLowerCase();
				String              divisionNodeString = "new" + divisionNodeTypeStringLowerCase;
				String              divisionNodeTooltip = HDLmTree.getTooltipString(divisionNodeString);
				/* Build a new tree node with the correct information */
				HDLmTree  divisionTree = new HDLmTree(nodeEnum,
						                                  divisionNodeTooltip,
						                                  divisionNodePath);
		    /* Make sure the new tree node was actually created */
				if (divisionTree == null) 
					HDLmAssertAction(false, "Null division tree node returned by new tree node constructor");
				/* Add an extended modification to the division tree node */
			  HDLmPassThruDivision  newModNode = new HDLmPassThruDivision();
		    /* Make sure the new extended modification node was actually created */
				if (newModNode == null) 
					HDLmAssertAction(false, "Null division extended modification returned by new extended modification constructor");
				divisionTree.setDetails(newModNode);				
				/* Add the division tree node to the Data or Rules tree node */
				nodeParent.addOrReplaceChild(divisionTree);
				nodeParent = divisionTree;
				HDLmTree.addPendingInserts(nodeParent);
				insertsDone = true;
		  }
			/* Check if the thing that was not found, was a site. This can
	       really happen, if a new company is being added to the node
	       tree. */
		  if (nodeEnum == HDLmTreeTypes.SITE) {	
				/* Get some information about the current instance */
		  	ArrayList<String>   siteNodePath = new ArrayList<String>(nodePath);
				String              siteNodeName = HDLmDefines.getString("HDLMSITENODENAME");
				String              siteNodeTypeStringLowerCase = nodeEnum.toString().toLowerCase();
				String              siteNodeString = "new" + siteNodeTypeStringLowerCase;
				String              siteNodeTooltip = HDLmTree.getTooltipString(siteNodeString);
				/* Build a new tree node with the correct information */
				HDLmTree  siteTree = new HDLmTree(nodeEnum,
						                              siteNodeTooltip,
						                              siteNodePath);
		    /* Make sure the new tree node was actually created */
				if (siteTree == null) 
					HDLmAssertAction(false, "Null site tree node returned by new tree node constructor");
				/* Add an extended modification to the site tree node */
			  HDLmPassThruSite  newModNode = new HDLmPassThruSite();
		    /* Make sure the new extended modification node was actually created */
				if (newModNode == null) 
					HDLmAssertAction(false, "Null site extended modification returned by new extended modification constructor");
				siteTree.setDetails(newModNode);	
				/* Add the site tree node to the division tree node */
				nodeParent.addOrReplaceChild(siteTree);
				nodeParent = siteTree;
				HDLmTree.addPendingInserts(nodeParent);
				insertsDone = true;
		  }
		}    
		/* Check if we have done any inserts in this routine. If we have
		   done some pending inserts, they need to be completed here. */ 
		if (insertsDone == true)
		  HDLmTree.processPendingInserts();
		return nodePath;
	}	
	/* Add a set of node identifier values to the node tree. The actual node
	   tree has several levels. The intermediate levels (company, Rules, division, 
	   site) may or may not exist. Create these levels if need be using the 
	   information provided by the caller. 
	  
	   Build a new rule using the information provided by the caller and add
	   the new rule in the correct place. The higher levels may or may not 
	   have been created by this routine. */
	@SuppressWarnings("unused")
	protected static HDLmTree  addNodeIden(final JsonElement jsonElements, 
														     				 final String hostName,
																		     final String urlValue,
																		     final boolean nodeCopyElements) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElements == null) {
			String  errorText = "JSON elements passed to addNodeIden is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name string instance passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string instance passed to addNodeIden is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the URL string reference passed by the caller is null */
		if (urlValue == null) {
			String  errorText = "URL string reference passed to addNodeIden is null";
			throw new NullPointerException(errorText);
		}	
		/* Build all of the intermediate levels as need be. This call will
	     update the node tree (HDLmTree) in memory and send any new nodes
	     to the database as need be. */ 
	  HDLmModResponse     modResponse = HDLmTree.buildLevelsGetSetTree(hostName,
	  		                                                             HDLmFreshCopy.FRESHCOPYNO,
	  		                                                             HDLmTreeTypes.RULES); 
	  HDLmTree            topTreeNode = modResponse.getTreeNode();
	  ArrayList<String>   nodePath = modResponse.getNodePath();
	  if (topTreeNode == null) {
		  HDLmAssertAction(false, "Null modifications tree returned by buildLevelsGetSetTree");
	  } 
		/* Try to find the tree node for the current node path.
		   This will be the parent of the tree node we are about
		   to create. */   
	  HDLmTree  nodeParent = HDLmTree.locateTreeNode(topTreeNode, nodePath);		
		if (nodeParent == null) {
			String  nodeString = nodePath.toString(); 
			HDLmUtility.logString(nodeString, LOG);
			HDLmUtility.logStackTrace();
			HDLmAssertAction(false, "Null tree node returned by locateTreeNode");
		}
		/* We can now create the tree node for the modification rule 
		   and the actual modification */
		ArrayList<String>   nodePathTree = new ArrayList<String>(nodePath);
		HDLmTreeTypes       nodeEnum = HDLmTreeTypes.MOD;
		int                 nodeLevel = nodeEnum.getValue();
		String              nodeName = "Temporary name";
		nodePathTree.add(nodeName);
		String              nodeTooltip = "Temporary tooltip";
	  HDLmTree  newTreeNode = new HDLmTree(nodeEnum,  
	 	 	                                   nodeTooltip, nodePathTree);
	 /* Make sure the new tree node was actually created */
		if (newTreeNode == null) 
			HDLmAssertAction(false, "Null modification tree node returned by new tree node constructor");
		/* We can now create the modification rule structure */ 
		HDLmMod   newModNode = new HDLmMod();
		newModNode.setIfNotSetTimes();
		newModNode.setEnabled((Boolean) true);
	  newModNode.setName(nodeName);
	  newTreeNode.setDetails(newModNode);		
		/* Check if the JSON element is a null value */
		if (jsonElements.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElements.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the JSON object from the JSON element */ 
		JsonObject  jsonObject = jsonElements.getAsJsonObject();
		/* Try to obtain the new order information string from 
		   the JSON object. Of course, this will only work if
		   the JSON object actually has new order information
		   in it. */ 
		String  newOrderInfo = null;
		String  newOrderInfoKey = "HDLmOrderInfo";
		if (HDLmJson.hasJsonKey(jsonElements, newOrderInfoKey)) {
			JsonElement   newOrderJsonElement = HDLmJson.getJsonValue(jsonElements,
					                                                      newOrderInfoKey);
			/* Make sure the order information JSON element is set */
			if (newOrderJsonElement == null)
				HDLmAssertAction(false, "Order information JSON element is not set");
			HDLmJson.removeJsonKey(jsonElements, newOrderInfoKey);
			newOrderInfo = HDLmJson.getStringJson(newOrderJsonElement);		
			int   newOrderInfoLength = newOrderInfo.length();
			newOrderInfo = newOrderInfo.substring(1, newOrderInfoLength-1);
		}		   
	  /* We can now invoke a special routine to provide values for 
	     many of the fields we could not set above */
	  HDLmMenus.provideDefaultValues(HDLmOperationTypes.NONE,
	  		                           nodeParent,
	 		                             newTreeNode,
	 		                             jsonObject,
	 		                             urlValue,
	 		                             newOrderInfo,
	 		                             nodeCopyElements);  
	  /* At this point, the new tree node is pretty much done. We may need to 
       get the perceptual hash values (really just one) for any images that
       the tree node uses. The new instance below is used just to provide
       a way of invoking the function that actually does all of the work. */
	  HDLmProcessTreePHash  pHashInstance = new HDLmProcessTreePHash();
    pHashInstance.processTreePos(newTreeNode); 	
	  /* Add the newly created tree node to the tree node tree. This should
	     only be done after we have the final name for the tree node and the
	     modification. At this point we have the final name for both. */
		nodeParent.addOrReplaceChild(newTreeNode);
		HDLmTree.addPendingInserts(newTreeNode);
		HDLmTree.processPendingInserts();
		/* The call below sends the updated node tree back to the server.
	     The call also sets or resets the tree top to the new value. 
	     The new tree will include the tree node possibly added above.  */  
		if (1 == 2) {
		  String  contentType = HDLmEditorTypes.PASS.toString();
		  HDLmTree.replaceEntireTree(contentType, topTreeNode);		 
		  /* Save the updated tree node in a common location. This has
		     the effect of making sure that the updated node tree is 
		     used for all future work. */  
		  HDLmTree.setNodePassTreeTop(topTreeNode); 
		}
		return newTreeNode;
	}
	/* Add a child HDLmTree node to the children array. This routine will not always
	   add a new child HDLmTree node to the children array. If a name match is
	   found, then an existing child node will be replaced. Note that this is not a
	   static method, the current object (this) must be an HDLmTree object that has
	   a child array. */
	protected void         addOrReplaceChild(final HDLmTree newChildNode) {
		if (newChildNode == null) {
			String  errorText = "New tree child node value is null";
			throw new NullPointerException(errorText);
		}
		if (!(newChildNode instanceof HDLmTree)) {
			HDLmAssertAction(false, "New tree child node value has incorrect type");
		}
		/* Declare and define a few local values */
		boolean addChildDone = false;
		/* Get the current level and the child level. The child level must be exactly
		   one below the current level. That means one higher actually. */
		int parentLevel = this.getNodePathLength();
		int childLevel = newChildNode.getNodePathLength();
		if ((parentLevel + 1) != childLevel) {
			String  errorText = String.format("Parent level (%d) and child level (%d) are wrong", parentLevel, childLevel);
			HDLmAssertAction(false, errorText);
		}
		/* Get the new child node name value, if possible */
		String newChildNodeName = newChildNode.getLastNodePathEntry();
		if (newChildNodeName == null) {
			String  errorText = "New child node name not set in addOrReplaceChild";
			HDLmAssertAction(false, errorText);
		}
		/* Get the current length of the children array */
		int parentChildrenLength = this.children.size();
		for (int i = 0; i < parentChildrenLength; i++) {
			HDLmTree  childNode = this.children.get(i);
			/* Get the name of the current child node */
			String childNodeName = childNode.getLastNodePathEntry();
			if (childNodeName == null)
				break;
			/* Compare the new child node name with the node name from the children array.
			   If we have found the correct place to insert the new node, insert the new
			   node. */
			int compareValue = newChildNodeName.compareTo(childNodeName);
			/* Check if the names match. If they match, replace the existing child node. */
			if (compareValue == 0) {
				this.children.set(i, newChildNode);
				addChildDone = true;
				break;
			}
			/* Add the new child node to the children array */
			if (compareValue < 0) {
				this.children.add(i, newChildNode);
				addChildDone = true;
				break;
			}
		}
		/* Just add the child to the end of the children array, if need be */
		if (!addChildDone)
			this.children.add(newChildNode);
	}
  /* This routine adds a set of pending deletes to the pending deletes 
	   array. The caller passes a tree node (which may or may not have 
	   children) that needs to be deleted. The passed tree node (which
	   may or may have children) is not modified by this call. The children
	   (which may or may not exist) are not modified by this call. The 
	   tree node passed by the caller (and all of it's children) are added
	   to the pending deletes array. Eventually the passed tree node (and 
	   the possible children) will be deleted by other code. */
	protected static void  addPendingDeletes(final HDLmTree treePos) { 
	  HDLmTree.addPendingDeletes(treePos, false); 
	}
	protected static void  addPendingDeletes(final HDLmTree treePos, 
			                                     final boolean processSubNodes) { 
	  /* Check the tree position value passed by the caller */		 
		if (treePos == null) {
			String  errorText = "Tree position reference passed by the caller to addPendingDeletes is null";
			throw new NullPointerException(errorText);
		}
	  /* Add the ID string to the array of pending deletes */
	  HDLmTree.pendingDeletes.add(treePos.getId());
	  /* Check if subnodes should be handled or not */
	  if (processSubNodes == false)
	    return;
	  /* Handle all of the children of the tree node passed
	     by the caller */
	  ArrayList<HDLmTree>   childArray = treePos.children;
	  int   i;
	  int   childArraySize = childArray.size();
	  for (i = 0; i < childArraySize; i++) {
	    HDLmTree.addPendingDeletes(childArray.get(i), processSubNodes);
	  }
	}
  /* This routine adds a set of pending inserts to the pending inserts 
		 array. The caller passes a tree node (which may or may not have 
		 children) that needs to be inserted. The passed tree node (which
		 may or may have children) is not modified by this call. The children
		 (which may or may not exist) are not modified by this call. The 
		 tree node passed by the caller (and all of it's children) are added
		 to the pending inserts array. Eventually the passed tree node (and 
		 the possible children) will be modified when the ID value(s) are returned
		 by the database server. A variety of unneeded information is (if present)
		 removed from the temporary copy of the tree node (and it's children) 
		 passed by the caller. */
	protected static void  addPendingInserts(final HDLmTree treePos) { 
	  HDLmTree.addPendingInserts(treePos, false); 
	}
	protected static void  addPendingInserts(final HDLmTree treePos, 
			                                     final boolean processSubNodes) {
	  /* Check the tree position value passed by the caller */		 
		if (treePos == null) {
			String  errorText = "Tree position reference passed by the caller to addPendingInserts is null";
			throw new NullPointerException(errorText);
		}
		/* Get a few values from the current HDLmTree instance */
		HDLmTreeTypes       tempType = treePos.getType();
		String              tempTooltip = treePos.getTooltip();
		ArrayList<String>   tempNodePath = treePos.getNodePath();
		HDLmMod             tempDetails = treePos.getDetails();
		/* Allocate the temporary HDLmTree instance */		 
		HDLmTree  tempTreePos = new HDLmTree(tempType, tempTooltip, tempNodePath);
		/* Check if the temporary tree node was properly allocated */ 
		if (tempTreePos == null) {
			String  errorText = "Temporary tree node was not allocated in addPendingInserts";
			HDLmAssertAction(false, errorText);
		}
		/* Add the details (the HDLmMod object) to the temporary tree object */
		if (tempDetails != null)
			tempTreePos.setDetails(tempDetails);		
		/* Convert the temporary object into a string */		
		String  tempPosStr = getJsonStringTree(tempTreePos);
		/* console.log(tempPos); */
		/* Add the string (created from the temporary node) to the 
		   array of pending inserts */
		HDLmTree.pendingInserts.add(tempPosStr);
		/* Check if subnodes should be handled or not */
		if (processSubNodes == false)
		  return;
		/* Handle all of the children of the tree node passed
		   by the caller */
		ArrayList<HDLmTree>   childArray = treePos.getChildren();
		int   childArraySize = childArray.size();
		int   i;
		for (i = 0; i < childArraySize; i++) {
		  HDLmTree.addPendingInserts(childArray.get(i), processSubNodes);
		}
	}
  /* This routine adds set of a pending updates to the pending updates 
	   array. The caller passes a tree node (which may or may not have
	   children) that needs to be updated. The passed tree node is not 
	   modified by this call. The children (which may or may not exist)
	   are not modified by this call. The tree node passed by the caller
	   (and all of it's children) are added to the pending updates array.
 	
	   Each pending update is actually an object with two properties 
	   (keys). The first property (a string) is the ID value of the 
	   node to be updated. The second property (also a string) is the 
	   contents of the updated node. A variety of unneeded information 
	   is (if present) removed from the temporary copy of the tree node
	   passed by the caller. */
	protected static void  addPendingUpdates(final HDLmTree treePos) { 
	  HDLmTree.addPendingUpdates(treePos, false); 
	}
	protected static void  addPendingUpdates(final HDLmTree treePos, 
			                                     final boolean processSubNodes) {
		/* Check the tree position value passed by the caller */		 
		if (treePos == null) {
		  String  errorText = "Tree position reference passed by the caller to addPendingUpdates is null";
		  throw new NullPointerException(errorText);
	  }	
		/* Get a few values from the current HDLmTree instance */
		HDLmMod             tempDetails = treePos.getDetails();
		HDLmTreeTypes       tempType = treePos.getType();
		String              tempTooltip = treePos.getTooltip();
		ArrayList<String>   tempNodePath = treePos.getNodePath();
		/* Allocate the temporary HDLmTree instance */		 
		HDLmTree  tempTreePos = new HDLmTree(tempType, tempTooltip, tempNodePath);
		/* Check if the temporary tree node was properly allocated */ 
		if (tempTreePos == null) {
			String  errorText = "Temporary tree node was not allocated";
			HDLmAssertAction(false, errorText);
		}
		/* We must now reset a few fields in the temporary node instance */
		tempTreePos.setChildrenNull();
		if (tempDetails != null)
			tempTreePos.setDetails(tempDetails);
		/* Convert the temporary instance into a string */
		String  tempPosStr = HDLmTree.getJsonStringTree(tempTreePos);
		/* Allocate another temporary HDLmTree instance */		 
		HDLmTree  tempTreeObj = new HDLmTree(tempType, tempTooltip, tempNodePath);
		/* Check if the temporary tree node (object) was properly allocated */ 
		if (tempTreeObj == null) {
			String  errorText = "Temporary tree node (object) was not allocated";
			HDLmAssertAction(false, errorText);
		}
		tempTreeObj.setChildrenNull();
		tempTreeObj.setType(null);
		tempTreeObj.setNodePathNull();
	  /* Copy the ID value out of the existing tree node. The ID value
	     is used to actually do the update. */
	  tempTreeObj.setId(treePos.getId());
		/* Store the entire temporary instance as the tooltip of another 
		   HDLmTree instance. This is just a trick to store the JSON in
		   the tooltip field. Of course, the JSON is not a tooltip. */ 
	  tempTreeObj.setTooltip(tempPosStr);
	  /* Add the temporary object (created from the temporary node) to the 
	     array of pending updates */
		Gson    gsonObj = HDLmMain.gsonMain;
		String  tempObjStr = gsonObj.toJson(tempTreeObj);
	  HDLmTree.pendingUpdates.add(tempObjStr);
	  /* Check if subnodes should be handled or not */
	  if (processSubNodes == false)
	    return;
		/* Handle all of the children of the tree node passed
       by the caller */
    ArrayList<HDLmTree>   childArray = treePos.getChildren();
    int   childArraySize = childArray.size();
    int   i;
    for (i = 0; i < childArraySize; i++) {
      HDLmTree.addPendingInserts(childArray.get(i), processSubNodes);
    }
	}
	/* Add a string to a string builder. A comma may also be 
	   added. */
	protected static void  addStringBuilder(final StringBuilder outBuilder,
			                                    final MutableInt counter,
			                                    final String addString) {
	  /* Check if the string builder reference passed by the caller is null */		 
		if (outBuilder == null) {
			String  errorText = "String builder reference passed by the caller to addStringBuilder is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the counter reference passed by the caller is null */		 
		if (counter == null) {
			String  errorText = "Counter reference passed by the caller to addStringBuilder is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the string (to be added) reference passed by the caller is null */		 
		if (counter == null) {
		  String  errorText = "String reference passed by the caller to addStringBuilder is null";
		  throw new NullPointerException(errorText);
		}
		/* Increment and check the counter */
		counter.increment();
		if (counter.getValue() > 1)
			outBuilder.append(',');
		/* Add the string passed by the caller */ 
		outBuilder.append(addString);
	}
	/* Build the top node of the node tree. This must only be done once. 
	   This method is in use at this time. This routine initializes the 
	   tree top node and returns the tree top node to the caller. This 
	   routine does not build an HDLmPassThruTop instance and store it
	   in the new node instance. */
	protected static HDLmTree addTop() {
		ArrayList<String>   topPath = new ArrayList<String>(List.of("Top"));
		return new HDLmTree(HDLmTreeTypes.TOP, "Top node of the node tree", topPath);
	}
	/* Add the JSON returned by AJAX to the node tree. This method may add many
	   nodes to the node tree at many levels. This method does appear to be in use
	   at this time. This method is based on the database format where one row is
	   one tree node.
	   
	   This routine is definitely used by the Java code and is not obsolete. Note
	   that this routine can be used to build any of the trees. That includes the
	   modifications tree, the proxy tree, the configuration tree, and/or the
	   ignore-lists tree. Of course, additional trees may be added over time. */
	protected static HDLmTree addToTree(final String jsonStr, 
			                                final HDLmEditorTypes editorType, 
			                                final HDLmStartupMode startupMode) {
		/* Declare and define a few variables */
		HDLmTree topTreeNode = null;
		/* Check if the JSON string passed by the caller is null */
		if (jsonStr == null) {
			String  errorText = "JSON string value passed by caller is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON string passed by the caller has the wrong data type */
		if (!(jsonStr instanceof String)) {
			HDLmAssertAction(false, "JSON string value has incorrect data type");
		}
		/* Check if the editor type passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
			HDLmAssertAction(false, "Editor type value is invalid");
		}
		/* Check if the startup mode passed by the caller is null */
		if (startupMode == null) {
			String  errorText = "Startup mode value in addToTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the startup mode passed by the caller is invalid */
		if (startupMode == HDLmStartupMode.NONE) {
			HDLmAssertAction(false, "Startup mode value in addToTree is invalid");
		}
		/* Create an JSON row comparator for use in sorting the array list elements */
		class jsonRowComparator implements Comparator<JsonObject> {
			@Override
			public int compare(JsonObject e1, JsonObject e2) {
				/* Declare and define a few values */
				int compareInt;
				int e1ArraySize;
				int e2ArraySize;
				JsonArray e1Array;
				JsonArray e2Array;
				JsonElement e1Element;
				JsonElement e2Element;
				JsonObject e1Info;
				JsonObject e2Info;
				String e1Name;
				String e2Name;
				/* Get the info values from the JSON objects */
				e1Info = HDLmJson.getJsonObject(e1, "info");
				if (e1Info == null) {
					String errorFormat = "Object value for member name (%s) not obtained from JSON object";
					String  errorText = String.format(errorFormat, "info");
					HDLmAssertAction(false, errorText);
				}
				e2Info = HDLmJson.getJsonObject(e2, "info");
				if (e2Info == null) {
					String errorFormat = "Object value for member name (%s) not obtained from JSON object";
					String  errorText = String.format(errorFormat, "info");
					HDLmAssertAction(false, errorText);
				}
				/* Get the node path arrays from each of the info objects */
				e1Array = HDLmJson.getJsonArray(e1Info, "nodePath");
				if (e1Array == null) {
					String errorFormat = "Array value for member name (%s) not obtained from JSON object";
					String  errorText = String.format(errorFormat, "nodePath");
					HDLmAssertAction(false, errorText);
				}
				e2Array = HDLmJson.getJsonArray(e2Info, "nodePath");
				if (e2Array == null) {
					String errorFormat = "Array value for member name (%s) not obtained from JSON object";
					String  errorText = String.format(errorFormat, "nodePath");
					HDLmAssertAction(false, errorText);
				}
				/* Get the size of each JSON array */
				e1ArraySize = e1Array.size();
				e2ArraySize = e2Array.size();
				/* Get the last element from each array */
				e1Element = e1Array.get(e1ArraySize - 1);
				e2Element = e2Array.get(e2ArraySize - 1);
				/* Get the name values from the JSON objects */
				e1Name = e1Element.getAsString();
				if (e1Name == null) {
					String errorFormat = "String value for member name (%s) not obtained from JSON object";
					String  errorText = String.format(errorFormat, "name");
					HDLmAssertAction(false, errorText);
				}
				e2Name = e2Element.getAsString();
				if (e2Name == null) {
					String errorFormat = "String value for member name (%s) not obtained from JSON object";
					String  errorText = String.format(errorFormat, "name");
					HDLmAssertAction(false, errorText);
				}
				/* Compare the names */
				compareInt = e1Name.compareTo(e2Name);
				return compareInt;
			}
		}
		/* Get the editor type in a lower-case string */
		String editorString = editorType.toString().toLowerCase();
		/* We get the length of the JSON string for error checking. This length is not
		   actually used except for checking below. */
		int jsonStrLen = jsonStr.length();
		/* The HTTP request to the local server does not appear to fail. However, the
		   HTTP request to the remote server does appear to fail if the network
		   connection is not working. This can result in an empty string being passed to
		   this routine. We need to check for this case and handle it. */
		if (jsonStrLen == 0) {
			String  errorText = "Nothing retrieved";
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Retrieval Failure", 16, errorText);
			return topTreeNode;
		}
		/* Convert the AJAX JSON to a JSON object and get some information from the
		   object */
		JsonParser    parser = new JsonParser();
		JsonElement   jsonResponse = parser.parse(jsonStr);
		if (jsonResponse.isJsonNull()) {
			String  errorText = String.format("JSON response returned from JSON parsing is JSON null");
			HDLmAssertAction(false, errorText);
		}
		JsonObject  jsonResponseObject = jsonResponse.getAsJsonObject();
		/* We need to extract the number of data rows. The number of data rows should
		   always be exactly one. */
		Integer dataRowsCount = HDLmJson.getJsonInteger(jsonResponseObject, "rows_returned");
		if (dataRowsCount == null) {
			String  errorText = String.format("Integer value for member name (%s) not obtained from JSON object",
					"rows_returned");
			HDLmAssertAction(false, errorText);
		}
		if (dataRowsCount <= 0) {
			String  errorText = String.format("Invalid number of data rows (%d) returned from the server", dataRowsCount);
			HDLmAssertAction(false, errorText);
		}
		/* Get the array of data rows from JSON object. Of course, we really only have
		   one data row at this point. */
		JsonArray dataRowsArray = HDLmJson.getJsonArray(jsonResponseObject, "data");
		if (dataRowsArray == null) {
			HDLmAssertAction(false, "Data rows array not obtained from JSON");
		}
		/* At this point we need to process all of the rows in the JSON array. Each row
		   will become a node in the node tree. They will probably not be in the correct
		   order. A sort will be needed to put them into the correct order. */
		ArrayList<JsonObject>   rowsArray = new ArrayList<JsonObject>();
		for (int i = 0; i < dataRowsCount; i++) {
			/* We can now obtain the a row from the JSON array */
			JsonElement dataRowElement = dataRowsArray.get(i);
			if (dataRowElement.isJsonNull()) {
				String  errorText = String.format("Data row element from JSON array is JSON null");
				HDLmAssertAction(false, errorText);
			}
			/* Get the info string */
			String dataRowInfoString = HDLmJson.getJsonString(dataRowElement, "info");
			if (dataRowInfoString == null) {
				String  errorText = String.format("Info string not obtained from data row object");
				HDLmAssertAction(false, errorText);
			}
			/* Convert the info string to a JSON object */
			JsonParser    dataRowInfoParser = new JsonParser();
			JsonElement   dataRowInfoJson = dataRowInfoParser.parse(dataRowInfoString);
			HDLmJson.setJsonValue(dataRowElement, "info", dataRowInfoJson);
			/* Add the updated data row element to the rows array */
			rowsArray.add((JsonObject) dataRowElement);
		}
		/* Sort the array list by comparing the names of each object */
		rowsArray.sort(new jsonRowComparator());
		/* Build the entire node tree */
		topTreeNode = HDLmTree.buildNodeTree(rowsArray, null, editorType);
		/* Check if the editor type passed by the caller shows that pass-through data is
		   being handled */
		if (editorType == HDLmEditorTypes.PASS) {
			topTreeNode.modifyPassThruTree(startupMode);
		}	
		return topTreeNode;
	}
	/* Add the JSON returned by AJAX to the node tree. This method may add many
	   nodes to the node tree at many levels. This method does not appear to be in
	   use at this time. This method appears to be based on the old database format.
	   
	   This routine is definitely used by the Java code and is not obsolete. Note
	   that this routine can be used to build any of the trees. That includes the
	   modifications tree, the proxy tree, the configuration tree, and/or the
	   ignore-lists tree. Of course, additional trees may be added over time. */
	protected static HDLmTree addToTreeMod(final String jsonStr, 
			                                   final HDLmEditorTypes editorType) {
		HDLmTree topTreeNode = null;
		/* Check if the JSON string passed by the caller is null */
		if (jsonStr == null) {
			String  errorText = "JSON string value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON string passed by the caller has the wrong data type */
		if (!(jsonStr instanceof String)) {
			HDLmAssertAction(false, "JSON string value has incorrect data type");
		}
		/* Check if the editor type passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
			HDLmAssertAction(false, "Editor type value is invalid");
		}
		/* Get the editor type in a lower-case string */
		String editorString = editorType.toString().toLowerCase();
		/* We get the length of the JSON string for error checking. This length is not
		   actually used except for checking below. */
		int jsonStrLen = jsonStr.length();
		/* The HTTP request to the local server does not appear to fail. However, the
		   HTTP request to the remote server does appear to fail if the network
		   connection is not working. This can result in an empty string being passed to
		   this routine. We need to check for this case and handle it. */
		if (jsonStrLen == 0) {
			String  errorText = "Nothing retrieved";
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Retrieval Failure", 16, errorText);
			return topTreeNode;
		}
		/* Convert the AJAX JSON to a JSON object and get some information from the
		   object */
		JsonParser    parser = new JsonParser();
		JsonElement   topNode = parser.parse(jsonStr);
		if (topNode.isJsonNull()) {
			String  errorText = String.format("Top node returned from JSON parsing is JSON null");
			HDLmAssertAction(false, errorText);
		}
		JsonObject topObject = topNode.getAsJsonObject();
		/* We need to extract the number of data rows. The number of data rows should
		   always be exactly one. */
		Integer dataRowsCount = getIntegerFromJson(topObject, "rows_returned");
		if (dataRowsCount == null) {
			String  errorText = String.format("Integer value for member name (%s) not obtained from JSON object",
			                              		"rows_returned");
			HDLmAssertAction(false, errorText);
		}
		if (dataRowsCount != 1) {
			String  errorText = String.format("Invalid number of data rows (%d) returned from the server", dataRowsCount);
			HDLmAssertAction(false, errorText);
		}
		/* Get the array of data rows from JSON object. Of course, we really only have
		   one data row at this point. */
		JsonArray   dataRowsArray = getArrayFromJson(topObject, "data");
		if (dataRowsArray == null) {
			HDLmAssertAction(false, "Data rows array not obtained from JSON");
		}
		/* We can now obtain the one and only row from the JSON array */
		JsonElement   dataRowElement = dataRowsArray.get(0);
		if (dataRowElement.isJsonNull()) {
			String  errorText = String.format("Data row element from JSON array is JSON null");
			HDLmAssertAction(false, errorText);
		}
		JsonObject dataRowObject = dataRowElement.getAsJsonObject();
		String contentType = getStringFromJson(dataRowObject, "content");
		if (contentType == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "content");
			HDLmAssertAction(false, errorText);
		}
		/* Make sure the content type is correct */
		String contentSuffix = HDLmConfigInfo.getEntriesDatabaseContentSuffix();
		String contentSuffixSystem = HDLmStateInfo.getSystemValue();
    /* The content suffix is wrong in the test environment. We must 
       force the value to be 'a' (without the quotes) in all cases. */
    contentSuffixSystem = "a";
		contentSuffix += contentSuffixSystem;
		String valueSuffix = HDLmUtility.getFinalValue(editorString, contentSuffix);
		if (!contentType.equals(valueSuffix)) {
			String  errorText = String.format("Data row has wrong content type (%s)", contentType);
			HDLmAssertAction(false, errorText);
		}
		/* We can now extract a set of fields from the data row. These field are (can
		   be) used to update the data row later. These fields are saved for use later. */
		Map<String, Object>   savedDetails = new HashMap<String, Object>();
		String                savedCompanyName = HDLmJson.getJsonString(dataRowElement, "company");
		String                savedDivisionName = HDLmJson.getJsonString(dataRowElement, "division");
		String                savedSiteName = HDLmJson.getJsonString(dataRowElement, "site");
		String                savedIdValue = HDLmJson.getJsonString(dataRowElement, "id");
		Boolean               savedEnabledValue = HDLmJson.getJsonBoolean(dataRowElement, "enabled");
		savedDetails.put("company", savedCompanyName);
		savedDetails.put("division", savedDivisionName);
		savedDetails.put("site", savedSiteName);
		savedDetails.put("id", savedIdValue);
		savedDetails.put("enabled", savedEnabledValue);
		String contentTypeStr = editorType.toString();
		setSavedDetails(contentTypeStr, savedDetails);
		/* We can now extract the actual modifications (or proxy definitions or
		   configuration definitions or ignore-lists) from the one data row. The
		   modifications (or proxy definitions or configuration definitions or
		   ignore-lists) may actually be an array. We should process just the first
		   element of the array.
		   
		   Note that we need to obtain a JSON value with the name of "mods" even if we
		   are not handling modifications. The JSON value always has a name of "mods"
		   even for proxy definitions or configuration definitions or ignore-lists. The
		   name "mods" is actually a column name in the table that contains the actual
		   data for all types of definitions. */
		String dataString = getStringFromJson(dataRowObject, "mods");
		if (dataString == null) {
			String errorFormat = "String value for member name (%s) not obtained from JSON object";
			String  errorText = String.format(errorFormat, "mods");
			HDLmAssertAction(false, errorText);
		}
		JsonElement modsElement = parser.parse(dataString);
		if (modsElement.isJsonArray()) {
			JsonArray modsArray = modsElement.getAsJsonArray();
			modsElement = modsArray.get(0);
		}
		topTreeNode = buildTreeFromJsonMod(modsElement, editorType);
		/* Check if the editor type passed by the caller shows that pass-through data is
		   being handled */
		if (editorType == HDLmEditorTypes.PASS) {
			topTreeNode.modifyPassThruTree(HDLmStartupMode.STARTUPMODENO);
		}
		return topTreeNode;
	}
	/* Add the JSON returned by AJAX to the node tree. This method may add many
	   nodes to the node tree at many levels. This method does not appear to be in
	   use at this time. This method appears to be based on the old database format.
	   
	   This routine is definitely used by the Java code and is not obsolete. Note
	   that this routine can be used to build any of the trees. That includes the
	   modifications tree, the proxy tree, the configuration tree, and/or the
	   ignore-lists tree. Of course, additional trees may be added over time. */
	protected static HDLmTree addToTreeProxy(final String jsonStr, 
			                                     final HDLmEditorTypes editorType) {
		HDLmTree topTreeNode = null;
		/* Check if the JSON string passed by the caller is null */
		if (jsonStr == null) {
			String  errorText = "JSON string value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON string passed by the caller has the wrong data type */
		if (!(jsonStr instanceof String)) {
			HDLmAssertAction(false, "JSON string value has incorrect data type");
		}
		/* Check if the editor type passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
			HDLmAssertAction(false, "Editor type value is invalid");
		}
		/* Get the editor type in a lower-case string */
		String editorString = editorType.toString().toLowerCase();
		/* We get the length of the JSON string for error checking. This length is not
		   actually used except for checking below. */
		int jsonStrLen = jsonStr.length();
		/* The HTTP request to the local server does not appear to fail. However, the
		   HTTP request to the remote server does appear to fail if the network
		   connection is not working. This can result in an empty string being passed to
		   this routine. We need to check for this case and handle it. */
		if (jsonStrLen == 0) {
			String  errorText = "Nothing retrieved";
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Retrieval Failure", 16, errorText);
			return topTreeNode;
		}
		/* Convert the AJAX JSON to a JSON object and get some information from the
		   object */
		JsonParser    parser = new JsonParser();
		JsonElement   topNode = parser.parse(jsonStr);
		if (topNode.isJsonNull()) {
			String  errorText = String.format("Top node returned from JSON parsing is JSON null");
			HDLmAssertAction(false, errorText);
		}
		JsonObject topObject = topNode.getAsJsonObject();
		/* We need to extract the number of data rows. The number of data rows should
		   always be exactly one. */
		Integer dataRowsCount = getIntegerFromJson(topObject, "rows_returned");
		if (dataRowsCount == null) {
			String  errorText = String.format("Integer value for member name (%s) not obtained from JSON object",
					"rows_returned");
			HDLmAssertAction(false, errorText);
		}
		if (dataRowsCount != 1) {
			String  errorText = String.format("Invalid number of data rows (%d) returned from the server", dataRowsCount);
			HDLmAssertAction(false, errorText);
		}
		/* Get the array of data rows from JSON object. Of course, we really only have
		   one data row at this point. */
		JsonArray dataRowsArray = getArrayFromJson(topObject, "data");
		if (dataRowsArray == null) {
			HDLmAssertAction(false, "Data rows array not obtained from JSON");
		}
		/* We can now obtain the one and only row from the JSON array */
		JsonElement dataRowElement = dataRowsArray.get(0);
		if (dataRowElement.isJsonNull()) {
			String  errorText = String.format("Data row element from JSON array is JSON null");
			HDLmAssertAction(false, errorText);
		}
		JsonObject dataRowObject = dataRowElement.getAsJsonObject();
		String contentType = getStringFromJson(dataRowObject, "content");
		if (contentType == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "content");
			HDLmAssertAction(false, errorText);
		}
		/* Make sure the content type is correct */
		String contentSuffix = HDLmConfigInfo.getEntriesDatabaseContentSuffix();
		String contentSuffixSystem = HDLmStateInfo.getSystemValue();
    /* The content suffix is wrong in the test environment. We must 
       force the value to be 'a' (without the quotes) in all cases. */
    contentSuffixSystem = "a";
		contentSuffix += contentSuffixSystem;
		String valueSuffix = HDLmUtility.getFinalValue(editorString, contentSuffix);
		if (!contentType.equals(valueSuffix)) {
			String  errorText = String.format("Data row has wrong content type (%s)", contentType);
			HDLmAssertAction(false, errorText);
		}
		/* We can now extract a set of fields from the data row. These field are (can
		   be) used to update the data row later. These fields are saved for use later. */
		Map<String, Object> savedDetails = new HashMap<String, Object>();
		String savedCompanyName = HDLmJson.getJsonString(dataRowElement, "company");
		String savedDivisionName = HDLmJson.getJsonString(dataRowElement, "division");
		String savedSiteName = HDLmJson.getJsonString(dataRowElement, "site");
		String savedIdValue = HDLmJson.getJsonString(dataRowElement, "id");
		Boolean savedEnabledValue = HDLmJson.getJsonBoolean(dataRowElement, "enabled");
		savedDetails.put("company", savedCompanyName);
		savedDetails.put("division", savedDivisionName);
		savedDetails.put("site", savedSiteName);
		savedDetails.put("id", savedIdValue);
		savedDetails.put("enabled", savedEnabledValue);
		String contentTypeStr = editorType.toString();
		setSavedDetails(contentTypeStr, savedDetails);
		/* We can now extract the actual modifications (or proxy definitions or
		   configuration definitions or ignore-lists) from the one data row. The
		   modifications (or proxy definitions or configuration definitions or
		   ignore-lists) may actually be an array. We should process just the first
		   element of the array.
		   
		   Note that we need to obtain a JSON value with the name of "mods" even if we
		   are not handling modifications. The JSON value always has a name of "mods"
		   even for proxy definitions or configuration definitions or ignore-lists. The
		   name "mods" is actually a column name in the table that contains the actual
		   data for all types of definitions. */
		String dataString = getStringFromJson(dataRowObject, "mods");
		if (dataString == null) {
			String errorFormat = "String value for member name (%s) not obtained from JSON object";
			String  errorText = String.format(errorFormat, "mods");
			HDLmAssertAction(false, errorText);
		}
		JsonElement modsElement = parser.parse(dataString);
		if (modsElement.isJsonArray()) {
			JsonArray modsArray = modsElement.getAsJsonArray();
			modsElement = modsArray.get(0);
		}
		topTreeNode = buildTreeFromJsonProxy(modsElement, editorType);
		/* Check if the editor type passed by the caller shows that pass-through data is
		   being handled */
		if (editorType == HDLmEditorTypes.PASS) {
			topTreeNode.modifyPassThruTree(HDLmStartupMode.STARTUPMODENO);
		}
		return topTreeNode;
	}
	/* Add a tree node value to the node tree. The actual node tree has
	   several levels. The intermediate levels (company, rules, division, site)
	   may or may not exist. Create these levels if need be using the information
	   provided by the caller. 
	  
	   Add the new rule in the correct place. The higher levels may or may not 
	   have been created by this routine. */
	@SuppressWarnings("unused")
	protected static void  addTreeNode(final JsonElement jsonElements, 
			                               final String curHostName, 
			                               final ArrayList<String> curNodePath) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElements == null) {
			String  errorText = "JSON elements passed to addTreeNode is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element is a null value */
		if (jsonElements.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElements.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		} 
		/* Check if the host name string instance passed by the caller is null */
		if (curHostName == null) {
			String  errorText = "Host name string instance passed to addTreeNode is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the node path passed by the caller is null */
		if (curNodePath == null) {
			String  errorText = "Node path array list passed to addTreeNode is null";
			throw new NullPointerException(errorText);
		}
		/* At this point, we may want to add a data value or we may want to add
		   a rule (a modification). We need to check for each case. */
		String  localTypeString = HDLmJson.getJsonString(jsonElements, "type");
		HDLmTreeTypes   nodeType = HDLmTreeTypes.MOD;
		if (localTypeString.equals("value"))
			nodeType = HDLmTreeTypes.VALUE;		
		/* Build all of the intermediate levels as need be. This call will
	     update the node tree (HDLmTree) in memory and send any new nodes
	     to the database as need be. */ 
	  HDLmModResponse     modResponse = HDLmTree.buildLevelsGetSetTree(curHostName,
	  		                                                             HDLmFreshCopy.FRESHCOPYNO,
	                                                                   nodeType); 
	  HDLmTree            topTreeNode = modResponse.getTreeNode();
	  ArrayList<String>   parentNodePath = modResponse.getNodePath();
	  if (topTreeNode == null) {
		  HDLmAssertAction(false, "Null modifications tree returned by buildLevelsGetSetTree");
	  } 
		/* Try to find the tree node for the current node path.
		   This will be the parent of the tree node we are about
		   to create. */   
	  HDLmTree  nodeParent = HDLmTree.locateTreeNode(topTreeNode, parentNodePath);		
		if (nodeParent == null) {
			String  nodeString = parentNodePath.toString(); 
			HDLmUtility.logString(nodeString, LOG);
			HDLmUtility.logStackTrace();
			HDLmAssertAction(false, "Null tree node returned by locateTreeNode");
		}
		/* We can now create the tree node for the modification rule 
		   and the actual modification */
		HDLmTreeTypes       nodeEnum = nodeType;
		JsonElement         nodeDetails = HDLmJson.getJsonValue(jsonElements, "details");
		String              nodeTooltip = HDLmJson.getJsonString(jsonElements, "tooltip");
	  HDLmTree  newTreeNode = new HDLmTree(nodeEnum,  
			                                   nodeTooltip, curNodePath);
	  /* Make sure the new tree node was actually created */
		if (newTreeNode == null) 
			HDLmAssertAction(false, "Null modification tree node returned by new tree node constructor");
		/* We can now create the modification rule structure */ 
		if (nodeType != HDLmTreeTypes.VALUE) {		
		  HDLmMod   newModNode = new HDLmMod(nodeDetails);
		  newModNode.setIfNotSetTimes();
	    newTreeNode.setDetails(newModNode);		 
	    /* At this point, the new tree node is pretty much done. We may need to 
	       get the perceptual hash values (really just one) for any images that
	       the tree node uses. The new instance below is used just to provide
	       a way of invoking the function that actually does all of the work. */
	  	HDLmProcessTreePHash  pHashInstance = new HDLmProcessTreePHash();
	    pHashInstance.processTreePos(newTreeNode); 	  
		}
		else {
		  HDLmMod   newValueNode = new HDLmPassThruValue(nodeDetails);
	    newTreeNode.setDetails(newValueNode);					
		}
	  /* Add the newly created tree node to the tree node tree. This should
	     only be done after we have the final name for the tree node and the
	     modification. At this point we have the final name for both. */
		nodeParent.addOrReplaceChild(newTreeNode);
		HDLmTree.addPendingInserts(newTreeNode);
		HDLmTree.processPendingInserts();
		/* The call below sends the updated rule tree back to the server.
	     The call also sets or resets the tree top to the new value. 
	     The new tree will include the tree node possibly added above. 
	     
	     The entire tree of nodes (the HDLmTree) is no longer sent back
	     to the database here. Instead, new nodes are selectively added
	     to the database as need be. */ 
		if (1 == 2) {
	    String  contentType = HDLmEditorTypes.PASS.toString();
	    HDLmTree.replaceEntireTree(contentType, topTreeNode);
		  /* Save the updated tree node in a common location. This has
	       the effect of making sure that the updated node tree is 
	       used for all future work. */  
	    HDLmTree.setNodePassTreeTop(topTreeNode); 
		}
	}
  /* This routine builds an information array with one entry for
     node in the node tree. Note that recursion is used to build
     the information array. */
	/* This routine builds an array of JSON Strings from the HDLmTree
     passed to it. The HDLmTree is used and not modified by this call. 
     Each HDLmTree element is used to build one JSON string. The final
     array will contain one entry for each HDLmTree element. */
	protected static void  buildInfoArray(final ArrayList<String> infoArray, 
			                                  final ArrayList<Integer> idArray,
			                                  final HDLmTree treePos) {
	  /* Check if the information array reference passed by the caller is null */		 
	  if (infoArray == null) {
		  String  errorText = "Information array reference passed by the caller to buildInfoArray is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the ID array reference passed by the caller is null */		 
	  if (idArray == null) {
		  String  errorText = "ID array reference passed by the caller to buildInfoArray is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the tree position value passed by the caller is null */		 
		if (treePos == null) {
			String  errorText = "Tree position reference passed by the caller to buildInfoArray is null";
			throw new NullPointerException(errorText);
		}
		/* Convert the current HDLmTree instance to a JSON string */
		String  infoStr = HDLmTree.getJsonStringTree(treePos);		 
	  /* Save the children array and then remove it from the current
	     temporary tree node */
	  ArrayList<HDLmTree>  childrenArray = treePos.getChildren();
	  /* Insert the current node into the information array */
	  infoArray.add(infoStr);     
	  /* Insert the current node ID value into the ID array */
	  String  idString = treePos.getId();
	  /* Check if the ID value is null. This can really happen if 
	     we are adding new companies, divisions, sites, etc. to 
	     the tree. Since we don't have an ID value, we can not 
	     convert the ID value to a number and delete the row. */
	  if (idString != null)
	    idArray.add(HDLmUtility.convertInteger(idString));
	  /* Process all of the children of the current node */
	  int   childrenCount = childrenArray.size();
	  int   i;
	  for (i = 0; i < childrenCount; i++) {
	    HDLmTree  curNode = childrenArray.get(i);
	    HDLmTree.buildInfoArray(infoArray, idArray, curNode);
	  }
	  return;
	}
	/* This method builds a list of integers from the rule names in the list of
	   rules passed by the caller. Only rules with names that begin with a value
	   passed by the caller are considered. These rules, may or may not, end with a
	   'nnnn' value. All other rules are just ignored. The 'nnnn' values are
	   extracted and added to a list. The list is returned to the caller. Stated
	   differently, this routine builds a list of numbers used to create unique rule
	   names. */
	protected static ArrayList<Integer> buildIntegerListName(final String ruleNamePrefix, 
			                                                     final ArrayList<HDLmTree> childList) {
		/* Check if the rule name prefix string passed by the caller is null */
		if (ruleNamePrefix == null) {
			String  errorText = "Rule name prefix value passed to buildIntegerListName is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the child list passed by the caller is null */
		if (childList == null) {
			String  errorText = "Child list array list passed to buildIntegerListName is null";
			throw new NullPointerException(errorText);
		}
		/* Get all of the integer values (if any) from the child list */
		int childListLen = childList.size();
		ArrayList<Integer>  integerList = new ArrayList<Integer>();
		int rulePrefixLen = ruleNamePrefix.length();
		for (int i = 0; i < childListLen; i++) {
			/* Get the current rule name */
			String currentName = childList.get(i).getLastNodePathEntry();
			/* Check if the rule name starts with the required prefix */
			if (currentName.startsWith(ruleNamePrefix) == false)
				continue;
			/* Strip the prefix from the rule name */
			currentName = currentName.substring(rulePrefixLen);
			int currentNameLength = currentName.length();
			/* Check if the current name has a leading blank. Skip the leading blank if need
			   be. */
			if (currentNameLength > 0 && currentName.charAt(0) == ' ') {
				currentName = currentName.substring(1);
				currentNameLength--;
			}
			/* Skip the current name if the remaining name length is zero */
			if (currentNameLength == 0)
				continue;
			/* We can now add the integer to the list */
			int currentNumber = HDLmUtility.convertInteger(currentName);
			integerList.add(currentNumber);
		}
		return integerList;
	}
	/* Add a set of string to the output string builder followed
	   by an integer */
	protected static void  buildJsonInteger(final StringBuilder outBuilder,
		                             	        final MutableInt  counter,
			                                    final String keyStr,
		                                      final Integer intValue) {
	  /* Check if the string builder reference passed by the caller is null */		 
		if (outBuilder == null) {
			String  errorText = "String builder reference passed by the caller to buildJsonInteger is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the key string reference passed by the caller is null */		 
		if (keyStr == null) {
			String  errorText = "String builder reference passed by the caller to buildJsonInteger is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the integer value reference passed by the caller is null */		 
		if (intValue == null) {
			String  errorText = "Integer value reference passed by the caller to buildJsonInteger is null";
			throw new NullPointerException(errorText);
		}
		/* Add the integer key (property name) to the output string builder */
		HDLmTree.buildJsonStrings(outBuilder, counter, keyStr, null);
		/* Convert the integer value to a string */
		String  intValueStr = intValue.toString();
		/* Add the integer value to the output string builder */
		outBuilder.append(intValueStr);
	}
	/* Add a set of strings to the output string builder. Either string
     can be a null value in which case the value will not be used. */
	protected static void  buildJsonStrings(final StringBuilder outBuilder,
			                                    final MutableInt counter,
			                                    final String firstString,
			                                    final String secondString) {
	  /* Check if the string builder reference passed by the caller is null */		 
		if (outBuilder == null) {
			String  errorText = "String builder reference passed by the caller to buildJsonStrings is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the counter reference passed by the caller is null */		 
		if (counter == null) {
			String  errorText = "Counter reference passed by the caller to buildJsonStrings is null";
			throw new NullPointerException(errorText);
		}
		/* Increment and check the counter */
		counter.increment();
		if (counter.getValue() > 1)
			outBuilder.append(',');
		/* Check if the first value was passed */
		if (firstString != null) {
			outBuilder.append('"');
			outBuilder.append(firstString);
			outBuilder.append('"');
		}
		/* Add the colon between the values */
		outBuilder.append(':');
		/* Check if the second value was passed */
		if (secondString != null) {
			outBuilder.append('"');
			outBuilder.append(secondString);
			outBuilder.append('"');
		}
	}
	/* This method builds a string with all of rows in it. The format (by design)
	   exactly matches the format returned by the database in response to a get
	   request. The caller passes a starting tree position. This routine converts
	   the starting tree position into a row and all of the children (indirect 
	   included) into rows. */
	protected static String  buildJsonStringTree(final HDLmTree treePos) {
	  /* Check if the tree position value passed by the caller is null */		 
		if (treePos == null) {
			String  errorText = "Tree position reference passed by the caller to buildJsonStringTree is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		MutableInt      counter = new MutableInt(0);
		StringBuilder   outBuilder = new StringBuilder();
		if (outBuilder == null) {
			String  errorText = "String builder new failed in buildJsonStringTree";
			HDLmAssertAction(false, errorText);			
		}
		ArrayList<String>  infoArray = new ArrayList<String>();
	  /* Check if the new information array reference is null */		 
	  if (infoArray == null) {
		  String  errorText = "Information array new failed in buildJsonStringTree";
		  HDLmAssertAction(false, errorText);	
		}
		ArrayList<Integer>  idArray = new ArrayList<Integer>();
	  /* Check if the new ID array reference is null */		 
	  if (idArray == null) {
		  String  errorText = "ID array new failed in buildJsonStringTree";
		  HDLmAssertAction(false, errorText);	
		}
		/* Convert the current HDLmTree instance and all of the children 
		   (direct and indirect) of the current HDLmTree instance to an 
		   array of strings. */
		HDLmTree.buildInfoArray(infoArray, idArray, treePos);	
		int   infoArraySize = infoArray.size();
		/* infoArraySize = 34; */
		/* Build the output string */
		outBuilder.append('{');
		HDLmTree.buildJsonInteger(outBuilder, counter, "rows_returned", infoArraySize);
		String  commentsStr = "io for " + HDLmConfigInfo.getEntriesDatabaseTableName();
		HDLmTree.buildJsonStrings(outBuilder, counter, "comments", commentsStr);
		HDLmTree.buildJsonInteger(outBuilder, counter, "retCode", 1);
		HDLmTree.buildJsonStrings(outBuilder, counter, "data", null);
		outBuilder.append('[');		
		/* Get the content string for use below */
		String  content = HDLmUtility.getContentString();
		/* Add all of the rows to the output string */
		int         i;
		MutableInt  rowCounter = new MutableInt();
		for (i = 0; i < infoArraySize; i++) {
			if (i == 285)
				i = i;
			/* Reset the counter for the current row for each row */
			rowCounter.setValue(0);
			/* Check if we need to add a comma */
			if (i > 0)
				outBuilder.append(',');
			/* Build the current object */
			outBuilder.append('{');
			HDLmTree.buildJsonStrings(outBuilder, rowCounter, "content", content);
			/* Add the ID string */
			Integer   idInteger = idArray.get(i);
			HDLmTree.buildJsonStrings(outBuilder, rowCounter, "id", idInteger.toString());
			/* Get the current information object (for one row) */
			String  infoStr = infoArray.get(i);
		  /* We can now process the response text */
		  JsonParser    parser = new JsonParser();  
		  JsonElement   infoJson = parser.parse(infoStr); 
			/* Add a information object */
		  /* LOG.info(((Integer) (i)).toString()); */	
		  /* LOG.info(infoStr); */
		  infoStr = infoStr.replaceAll("\\\\", "\\\\\\\\");
		  /* LOG.info(infoStr); */
		  infoStr = infoStr.replaceAll("\"", "\\\\\"");
		  /* LOG.info(infoStr); */
		  /* infoStr = "{\\\"test\\\":\\\"ab\\\"cd\\\"}"; */
		  HDLmTree.buildJsonStrings(outBuilder, rowCounter, "info", infoStr);
		  /* outBuilder.append(infoStr); */
		  /* Get the name of the current row */
		  JsonArray     nodePathJson = HDLmJson.getJsonArray(infoJson,  "nodePath");
		  int           nodePathSize = nodePathJson.size();
		  /* Get and check the row name string. The row name string will be 
		     the last entry in the node path array. */ 
		  JsonElement   nodePathLastEntry = nodePathJson.get(nodePathSize-1);		  
			if (!nodePathLastEntry.isJsonPrimitive()) {
				HDLmAssertAction(false, "JSON field element is not a JSON primitive value");
			}
			/* Get the JSON primitive value */
		  JsonPrimitive   jsonPrimitive = nodePathLastEntry.getAsJsonPrimitive();
			if (!jsonPrimitive.isString()) {
				HDLmAssertAction(false, "JSON field element is not a JSON string value");
			}	
			/* Get the rule name as a string. Note that this string 
			   does not have double quotes around it nor does it have 
			   escapes before each double quote. */ 
		  String  nameStr = jsonPrimitive.getAsString();	
		  /* Build an object with the rule name in it. The rule
		     name is passed to the object constructor as a string. */ 
		  HDLmJsonName  nameObject = new HDLmJsonName(nameStr);		
		  /* Convert the new object instance to JSON */
			Gson    gsonInstance = HDLmMain.gsonMain;
			String  nameJson = gsonInstance.toJson(nameObject);	 
			/* Remove the leading and trailing braces from the JSON string. 
			   The leading and trailing braces are added because an object  
			   is converted to JSON. */ 	
			int   nameJsonlen = nameJson.length();
			nameJson = nameJson.substring(1, nameJsonlen-1);
		  HDLmTree.addStringBuilder(outBuilder, rowCounter, nameJson);
		  outBuilder.append('}');
		}
		/* Finish the output string */
	  outBuilder.append(']'); 
		outBuilder.append('}'); 
		return outBuilder.toString();
	}
	/* This routine gets a fresh copy of the rule tree (in some cases) and 
	   adds tree levels as need be. The top of the rule tree is returned to 
	   the caller. */
	protected static HDLmModResponse  buildLevelsGetSetTree(final String hostName,
			                                                    final HDLmFreshCopy freshTree,
			                                                    final HDLmTreeTypes nodeType) {
		/* Check if the host name value string reference passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string value reference passed to buildLevelsGetSetTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the fresh copy of the tree enum value passed by the caller is null */
		if (freshTree == null) {
			String  errorText = "Fresh copy of the tree enum value passed to buildLevelsGetSetTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the fresh copy of the tree enum value passed by the caller is invalid */
		if (freshTree == HDLmFreshCopy.NONE) {
			String  errorText = "Fresh copy of the tree enum value passed to buildLevelsGetSetTree is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the node type value passed by the caller is null */
		if (nodeType == null) {
			String  errorText = "Node type value passed to buildLevelsGetSetTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the node type value passed by the caller is invalid */
		if (nodeType == HDLmTreeTypes.NONE) {
			String  errorText = "Node Type value passed to buildLevelsGetSetTree is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Build an instance of the response object used to return values
		   to the caller */
		HDLmModResponse   modResponse = new HDLmModResponse();
		/* In some cases, we need to get a fresh copy of all of the 
		   modifications here. In other cases, we do not need to do
		   so. */ 
		HDLmTree   topTreeNode = null;
		if (freshTree == HDLmFreshCopy.FRESHCOPYYES) {
			/* Get a fresh copy of all of the modifications. The new rule
			   is added to a fresh copy of the modifications. */ 
		  topTreeNode = HDLmTree.getPassThruFreshTreeSetTop();
			if (topTreeNode == null) {
				HDLmAssertAction(false, "Null modifications tree returned by getFreshTreeSetTop");
			}
		}
		/* In other cases, we don't need to get a fresh copy of all of the
		   modifications. However, we do need to get a reference to the top
		   tree (HDLmTree) node for use below. */
		else 
			topTreeNode = HDLmTree.getNodePassTreeTop();
		/* Save a reference to the top tree node in the response object */
		modResponse.setTreeNode(topTreeNode);
		/* Add all of the (possibly) missing tree levels to the modifications
		   tree. Note that some or all of the levels may already exist or may
		   have to be created. */
		ArrayList<String>   nodePath;
		nodePath = HDLmTree.addLevels(topTreeNode, hostName, nodeType);
		if (nodePath == null) {
			HDLmAssertAction(false, "Null node path returned by addLevels");
		} 
		/* Save a reference to the node path in the response object */
		modResponse.setNodePath(nodePath);
		return modResponse;		
	}
	/* This routine builds a HDLmMod object from some JSON, if possible */
	protected static HDLmMod  buildModDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmMod newMod = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for modification details is null";
			throw new NullPointerException(errorText);
		}
		newMod = new HDLmMod(jsonElement);
		newMod.setIfNotSetTimes();
		return newMod;
	}
	/* This routine builds a node path array from some JSON */
	protected static ArrayList<String>  buildNodePathFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		/* Build an empty node path array list. Entried are added to the node path array
		   list below. */
		ArrayList<String>   nodePathArray = new ArrayList<String>();
		/* Get the array of node path values from JSON object */
		JsonArray nodePathJsonArray = getArrayFromJson(jsonObject, "nodePath");
		if (nodePathJsonArray == null) {
			HDLmAssertAction(false, "Node path array not obtained from JSON");
		}
		/* We can now obtain each of the node path values from the JSON array */
		for (JsonElement jsonElement : nodePathJsonArray) {
			if (jsonElement.isJsonNull())
				continue;
			String nodePathEntry = jsonElement.getAsString();
			nodePathArray.add(nodePathEntry);
		}
		return nodePathArray;
	}
	/* This routine builds the node tree using the JSON data passed by the caller.
	   The node tree is built from the top (literally) down. When this routine is
	   first invoked, it is passed a null value for the current node. The null value
	   causes this routine to find and build the top node. All other nodes are built
	   as direct and indirect children of the top node. */
	protected static HDLmTree buildNodeTree(final ArrayList<JsonObject> jsonData, 
		                                    	HDLmTree curNode,
			                                    final HDLmEditorTypes editorType) {
		/* Check if the JSON data array list passed by the caller is null */
		if (jsonData == null) {
			String  errorText = "JSON data array list value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON data array list size passed by the caller is invalid */
		if (jsonData.size() <= 0) {
			HDLmAssertAction(false, "JSON data array list size is invalid");
		}
		/* Check if the editor type passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
			HDLmAssertAction(false, "Editor type value is invalid");
		}
		/* Declare and define a few variables */
		ArrayList<JsonObject> outArray;
		JsonArray emptyJsonArray = new JsonArray();
		JsonObject infoJson;
		/* If the current node is null, then this is the first call to this routine. We
		   need to find an build the top node here. */
		if (curNode == null) {
			outArray = HDLmTree.getMatchingEntries(jsonData, 1, emptyJsonArray);
			if (outArray == null) {
				String  errorText = "JSON output array value is null";
				throw new NullPointerException(errorText);
			}
			if (outArray.size() != 1) {
				String  errorText = String.format("JSON output array size (%d) is invalid", outArray.size());
				HDLmAssertAction(false, errorText);
			}
			infoJson = HDLmJson.getJsonObject(outArray.get(0), "info");
			if (infoJson == null) {
				String  errorText = "JSON top info value is null";
				throw new NullPointerException(errorText);
			}
			String  idString = HDLmJson.getJsonString(outArray.get(0), "id");
			if (idString == null) {
				String  errorText = "JSON top ID string value is null";
				throw new NullPointerException(errorText);
			}
			curNode = HDLmTree.buildTreeFromJson(infoJson, idString, editorType);
			if (curNode == null) {
				String  errorText = "JSON top tree value is null";
				throw new NullPointerException(errorText);
			}
		}
		/* Find all of the children of the current node */
		int findLevel = curNode.getNodePathLength();
		if (findLevel <= 0) {
			String  errorText = String.format("Invalid node path length value obtained from current node");
			HDLmAssertAction(false, errorText);
		}
		findLevel += 1;
		/* Copy all of the entries in the current node path to a JSON array */
		ArrayList<String>  curNodePath = curNode.getNodePath();
		JsonArray findNodes = new JsonArray();
		for (var curNodePathEntry : curNodePath)
			findNodes.add(curNodePathEntry);
		outArray = HDLmTree.getMatchingEntries(jsonData, findLevel, findNodes);
		int   outLen = outArray.size();
		/* Each of the matching entries is a child of the current entry. add each child
		   entry. */
		for (int i = 0; i < outLen; i++) {
			JsonElement jsonArrayEntry = outArray.get(i);
			if (jsonArrayEntry == null) {
				String  errorText = "JSON data array list entry value is null";
				throw new NullPointerException(errorText);
			}
			String  jsonArrayEntryId = HDLmJson.getJsonString(jsonArrayEntry, "id");
			if (jsonArrayEntryId == null) {
				String  errorText = "JSON data array list entry ID value is null";
				throw new NullPointerException(errorText);
			}
			JsonElement   jsonArrayEntryInfo = HDLmJson.getJsonObject(jsonArrayEntry, "info");
			if (jsonArrayEntryInfo == null) {
				String  errorText = "JSON data array list entry info value is null";
				throw new NullPointerException(errorText);
			}
			/* The following code waw added just for debugging. This code
			   is commented out for now. */ 
			/*
			{
				if (jsonArrayEntryId.equals("17425") ||
					  jsonArrayEntryId.equals("17426")) { 
					editorType = editorType;					
				}
			}			
			*/
			HDLmTree jsonArrayEntryTree = HDLmTree.buildTreeFromJson(jsonArrayEntryInfo,
					                                                     jsonArrayEntryId,
					                                                     editorType);
			if (jsonArrayEntryTree == null) {
				String  errorText = "JSON data array list entry tree value is null";
				throw new NullPointerException(errorText);
			}
			curNode.children.add(jsonArrayEntryTree);
			HDLmTree.buildNodeTree(jsonData, jsonArrayEntryTree, editorType);
		}
		return curNode;
	}	
	/* This method builds a map with entries for all of the parameter numbers that
	   are actually in use. The caller passes a list of child nodes that may or may
	   not have parameter numbers. This routine scans all of the child nodes and
	   uses the parameter numbers (if they can be found) to build the parameter
	   number usage map. For example, if the child nodes used the parameter number
	   3, two times, then the returned map would have an entry with a key of 3 and a
	   value of two. */
	protected static Map<Integer, Integer> buildParameterMap(final ArrayList<HDLmTree> childList) {
		/* Get the number of children in the child list passed by the caller */
		int childListLen = childList.size();
		/* Build a map that shows how many times each parameter number is used */
		Map<Integer, Integer> parmMapObj = new HashMap<Integer, Integer>();
		/* Check all of the children of the parent node */
		for (int i = 0; i < childListLen; i++) {
			/* Get the current child */
			HDLmTree childEntry = childList.get(i);
			HDLmMod childDetails = childEntry.getDetails();
			if (childDetails == null)
				continue;
			/* Try to obtain the parameter number of the current rule */
			Integer childParameterNumber = childDetails.getParameterNumber();
			if (childParameterNumber == null)
				continue;
			/* Check if the parameter map already has the current parameter number. If the
			   parameter map already has the current parameter number, increment the count
			   value. Otherwise, create the map entry with a count of 1. */
			if (parmMapObj.containsKey(childParameterNumber) == false)
				parmMapObj.put(childParameterNumber, Integer.valueOf(1));
			else {
				int parmMapEntryCount = parmMapObj.get(childParameterNumber);
				parmMapEntryCount++;
				parmMapObj.put(childParameterNumber, Integer.valueOf(parmMapEntryCount));
			}
		}
		return parmMapObj;
	}
	/* This routine builds a HDLmPassThruCompanies object from some JSON, if
	   possible. This routine actually builds a companies instance from JSON. */
	protected static HDLmPassThruCompanies buildPassThruCompaniesDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruCompanies newCompanies = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for companies entry details is null";
			throw new NullPointerException(errorText);
		}
		newCompanies = new HDLmPassThruCompanies(jsonElement);
		return newCompanies;
	}
	/* This routine builds a HDLmPassThruCompany object from some JSON, if possible.
	   This routine actually builds a company instance from JSON. */
	protected static HDLmPassThruCompany buildPassThruCompanyDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruCompany newCompany = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for company entry details is null";
			throw new NullPointerException(errorText);
		}
		newCompany = new HDLmPassThruCompany(jsonElement);
		return newCompany;
	}
	/* This routine builds a HDLmPassThruData object from some JSON, if possible.
 	   This routine actually builds a data instance from JSON. */
	protected static HDLmPassThruData buildPassThruDataDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruData  newData = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for data entry details is null";
			throw new NullPointerException(errorText);
		}
		newData = new HDLmPassThruData(jsonElement);
		return newData;
	}
	/* This routine builds a HDLmPassThruDivision object from some JSON, if possible.
	   This routine actually builds a division instance from JSON. */
	protected static HDLmPassThruDivision  buildPassThruDivisionDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruDivision  newDivision = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement  jsonElement = jsonObject.get("details");
		/* This code was added to handle the case where the division details are not
		   present in the JSON. This code was needed to handle migration from the old
		   code where division nodes (tree nodes) did not have details to the new code
		   where division nodes (tree nodes) do have details. As a consequence, this 
		   code should never really be executed. */		
		if (jsonElement == null) {
			LOG.info("Division details are not present in the JSON");
			/* Create a default division extended modification object */
			HDLmPassThruDivision  divisionObject = HDLmPassThruDivision.buildDefaultDivision();
			/* Get the created and last modified times and move them to
			   dummy fields */
			Instant   saveCreated = divisionObject.getCreated();
			Instant   saveLastModified = divisionObject.getLastModified();
			divisionObject.setCreatedNull();
			divisionObject.setLastModifiedNull();
			divisionObject.setDummyCreated(saveCreated.toString());
			divisionObject.setDummyLastModified(saveLastModified.toString());
			String  divisionString = HDLmJson.getJsonString(divisionObject);
			/* Change the JSON string to use the correct field names */
			divisionString = divisionString.replaceAll("dummyCreated", "created");
			divisionString = divisionString.replaceAll("dummyLastModified", "lastmodified");
			/* Parse the JSON string to get the JSON object */
		  JsonParser    parser = new JsonParser();		
	    JsonElement   infoJsonElement = null; 
		  try {
			  JsonObject  divisionJsonObject = parser.parse(divisionString).getAsJsonObject();
			  jsonObject.add("details", divisionJsonObject);
			  jsonElement = jsonObject.get("details");
			}
			catch (Exception exception) {
				LOG.info("Exception while executing parse of division string");
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				return null;
			}
		}
		/* Check if we found a JSON element for the details */
		if (jsonElement == null) {
			String  errorText = "JSON element for division details is null";
			throw new NullPointerException(errorText);
		}
		newDivision = new HDLmPassThruDivision(jsonElement);
		return newDivision;
	}
	/* This routine builds a HDLmPassThruIgnore object from some JSON, if possible.
	   This routine actually builds an ignore-list entry instance from JSON. */
	protected static HDLmPassThruIgnore buildPassThruIgnoreDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruIgnore newIgnore = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for ignore-list entry details is null";
			throw new NullPointerException(errorText);
		}
		newIgnore = new HDLmPassThruIgnore(jsonElement);
		return newIgnore;
	}
	/* This routine builds a HDLmPassThruLine object from some JSON, if possible.
	   This routine actually builds a line instance from JSON. */
	protected static HDLmPassThruLine buildPassThruLineDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruLine newLine = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for line details is null";
			throw new NullPointerException(errorText);
		}
		newLine = new HDLmPassThruLine(jsonElement);
		return newLine;
	}
	/* This routine builds a HDLmPassThruLines object from some JSON, if possible.
	   This routine actually builds a lines instance from JSON. */
	protected static HDLmPassThruLines buildPassThruLinesDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruLines newLines = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for lines details is null";
			throw new NullPointerException(errorText);
		}
		newLines = new HDLmPassThruLines(jsonElement);
		return newLines;
	}
	/* This routine builds a HDLmPassThruList object from some JSON, if possible.
	   This routine actually builds a list instance from JSON. */
	protected static HDLmPassThruList buildPassThruListDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruList newList = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for list details is null";
			throw new NullPointerException(errorText);
		}
		newList = new HDLmPassThruList(jsonElement);
		return newList;
	}
	/* This routine builds a HDLmPassThruLists object from some JSON, if possible.
	   This routine actually builds a lists instance from JSON. */
	protected static HDLmPassThruLists buildPassThruListsDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruLists newLists = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for lists details is null";
			throw new NullPointerException(errorText);
		}
		newLists = new HDLmPassThruLists(jsonElement);
		return newLists;
	}
	/* This routine builds a HDLmPassThruReport object from some JSON, if possible.
	   This routine actually builds a report instance from JSON. */
	protected static HDLmPassThruReport buildPassThruReportDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruReport newReport = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for report details is null";
			throw new NullPointerException(errorText);
		}
		newReport = new HDLmPassThruReport(jsonElement);
		return newReport;
	}
	/* This routine builds a HDLmPassThruReports object from some JSON, if possible.
	   This routine actually builds a reports instance from JSON. */
	protected static HDLmPassThruReports buildPassThruReportsDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruReports newReports = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for reports entry details is null";
			throw new NullPointerException(errorText);
		}
		newReports = new HDLmPassThruReports(jsonElement);
		return newReports;
	}
	/* This routine builds a HDLmPassThruRules object from some JSON, if possible.
	   This routine actually builds a rules instance from JSON. */
	protected static HDLmPassThruRules  buildPassThruRulesDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruRules newRules = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for rules entry details is null";
			throw new NullPointerException(errorText);
		}
		newRules = new HDLmPassThruRules(jsonElement);
		return newRules;
	}
	/* This routine builds a HDLmPassThruSite object from some JSON, if possible.
	   This routine actually builds a site instance from JSON. */
	protected static HDLmPassThruSite  buildPassThruSiteDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruSite  newSite = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		/* This code was added to handle the case where the site details are not
	     present in the JSON. This code was needed to handle migration from the old
	     code where site nodes (tree nodes) did not have details to the new code
	     where site nodes (tree nodes) do have details. As a consequence, this 
	     code should never really be executed. */		
		if (jsonElement == null) {
			LOG.info("Site details are not present in the JSON");
			/* Create a default site extended modification object */
			HDLmPassThruSite  siteObject = HDLmPassThruSite.buildDefaultSite();
			/* Get the created and last modified times and move them to
		     dummy fields */
			Instant   saveCreated = siteObject.getCreated();
			Instant   saveLastModified = siteObject.getLastModified();
			siteObject.setCreatedNull();
			siteObject.setLastModifiedNull();
			siteObject.setDummyCreated(saveCreated.toString());
			siteObject.setDummyLastModified(saveLastModified.toString());
			String  siteString = HDLmJson.getJsonString(siteObject);
			/* Change the JSON string to use the correct field names */
			siteString = siteString.replaceAll("dummyCreated", "created");
			siteString = siteString.replaceAll("dummyLastModified", "lastmodified");
			/* Parse the JSON string to get the JSON object */
		  JsonParser    parser = new JsonParser();		
	    JsonElement   infoJsonElement = null; 
		  try {
			  JsonObject  siteJsonObject = parser.parse(siteString).getAsJsonObject();
			  jsonObject.add("details", siteJsonObject);
			  jsonElement = jsonObject.get("details");
			}
			catch (Exception exception) {
				LOG.info("Exception while executing parse of site string");
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				return null;
			}
		}		
		/* Check if we found a JSON element for the details */
		if (jsonElement == null) {
			String  errorText = "JSON element for site details is null";
			throw new NullPointerException(errorText);
		}
		newSite = new HDLmPassThruSite(jsonElement);
		return newSite;
	}
	/* This routine builds a HDLmPassThruTop object from some JSON, if possible.
	   This routine actually builds an top instance from JSON. */
	protected static HDLmPassThruTop buildPassThruTopDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruTop newTop = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for top entry details is null";
			throw new NullPointerException(errorText);
		}
		newTop = new HDLmPassThruTop(jsonElement);
		return newTop;
	}
	/* This routine builds a HDLmPassThruValue object from some JSON, if possible.
	   This routine actually builds a value instance from JSON. */
	protected static HDLmPassThruValue buildPassThruValueDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmPassThruValue   newValue = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for value entry details is null";
			throw new NullPointerException(errorText);
		}
		newValue = new HDLmPassThruValue(jsonElement);
		return newValue;
	}
	/* This routine builds a HDLmProxy object from some JSON, if possible */
	protected static HDLmProxy buildProxyDetailsFromJson(final JsonObject jsonObject) {
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		HDLmProxy newProxy = null;
		/* Get the JSON element for the details, if possibles */
		JsonElement jsonElement = jsonObject.get("details");
		if (jsonElement == null) {
			String  errorText = "JSON element for proxy definition details is null";
			throw new NullPointerException(errorText);
		}
		newProxy = new HDLmProxy(jsonElement);
		return newProxy;
	}
	/* This routine builds a tree node for something (an HDLmMod or something that
	   extends HDLmMod) and returns the final tree node to the caller. caller. The
	   something reference is used to set the details of the new tree node. */
	protected static <T extends HDLmMod> HDLmTree buildTree(final ArrayList<String> oldNodePath, 
			                                                    final T newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
			String  errorText = "Old node path reference passed to buildTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the something (an HDLmMod or something that extends HDLmMod)
		   reference passed by the caller is null */
		if (newValue == null) {
			String  errorText = "New value reference passed to buildTree is null";
			throw new NullPointerException(errorText);
		}
		/* Get some information about the current instance */
		HDLmTreeTypes       nodeType = newValue.getAssociatedNodeType();
		String              nodeName = newValue.getName();
		String              nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String              nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>   nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree currentTree = new HDLmTree(nodeType, nodeTooltip, nodePath);
		if (currentTree == null) {
			String  errorText = "New tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the instance passed by the caller as the details of the new tree node */
		currentTree.setDetails(newValue);
		return currentTree;
	}
	/* This routine builds an HDLmTree object from some JSON. This is the
	   routine that does most of the work of actually building and HDLm
	   tree entry. */
	protected static HDLmTree buildTreeFromJson(final JsonElement jsonElement, 
		                               	          final String idString,
			                                        final HDLmEditorTypes editorType) {
		/* Check a few values passed by the caller */
		if (jsonElement == null) {
			String  errorText = "JSON element value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonElement instanceof JsonElement)) {
			HDLmAssertAction(false, "JSON element value has incorrect type");
		}
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element passed by the caller is JSON null");
		}
		if (idString == null) {
			String  errorText = "ID string reference value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
			HDLmAssertAction(false, "Editor type value is invalid");
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		/* Get each of the values from the JSON object */
		String newTooltip = getStringFromJson(jsonObject, "tooltip");
		if (newTooltip == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "tooltip");
			HDLmAssertAction(false, errorText);
		}
		String newTypeStr = getStringFromJson(jsonObject, "type");
		if (newTypeStr == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "type");
			HDLmAssertAction(false, errorText);
		}
		/* Convert the HDLmTree node type string to uppercase. This step greatly
		   simplifies various checks below. It also makes checking the value easier. */
		String newTopStr = HDLmDefines.getString("HDLMTOPNODENAME");
		String newTopStrUpper = newTopStr.toUpperCase();
		String newTypeStrUpper = newTypeStr.toUpperCase();
		String newTypeStrUpperTop = HDLmDefines.getString("HDLMTOPNODETYPE").toUpperCase();
		if (newTypeStrUpper.equals(newTypeStrUpperTop))
			newTypeStrUpper = newTopStrUpper;
		HDLmTreeTypes newTypeEnum = HDLmTreeTypes.valueOfString(newTypeStrUpper);
		if (newTypeEnum == HDLmTreeTypes.NONE) {
			String errorString = newTypeStr;
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Invalid Tree Type", 20, errorString);
			return null;
		}
		/* Check if we are building an HDLmTree node for pass-through processing. In
		   all (or almost all) cases we have some details. We will actually have details
		   even for the top-level tree node and each of the company nodes. Of course, we
		   will have details for lower-level nodes as well. */
		HDLmMod                 newMod = null;
		HDLmPassThruCompanies   newHDLmPassThruCompanies = null;
		HDLmPassThruCompany     newHDLmPassThruCompany = null;
		HDLmPassThruData        newHDLmPassThruData = null;
		HDLmPassThruDivision    newHDLmPassThruDivision = null;
		HDLmPassThruIgnore      newHDLmPassThruIgnore = null;
		HDLmPassThruLine        newHDLmPassThruLine = null;
		HDLmPassThruLines       newHDLmPassThruLines = null;
		HDLmPassThruList        newHDLmPassThruList = null;
		HDLmPassThruLists       newHDLmPassThruLists = null;
		HDLmPassThruRules       newHDLmPassThruRules = null;
		HDLmPassThruReports     newHDLmPassThruReports = null;
		HDLmPassThruReport      newHDLmPassThruReport = null;
		HDLmPassThruSite        newHDLmPassThruSite = null;
		HDLmPassThruTop         newHDLmPassThruTop = null;
		HDLmPassThruValue       newHDLmPassThruValue = null;
		if (editorType == HDLmEditorTypes.PASS) {
			if (newTypeEnum == HDLmTreeTypes.COMPANIES) {
				newHDLmPassThruCompanies = buildPassThruCompaniesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.COMPANY) {
				newHDLmPassThruCompany = buildPassThruCompanyDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.DATA) {
				newHDLmPassThruData = buildPassThruDataDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.DIVISION) {
				newHDLmPassThruDivision = buildPassThruDivisionDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.IGNORE) {
				newHDLmPassThruIgnore = buildPassThruIgnoreDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LINES) {
				newHDLmPassThruLines = buildPassThruLinesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LINE) {
				newHDLmPassThruLine = buildPassThruLineDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LISTS) {
				newHDLmPassThruLists = buildPassThruListsDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LIST) {
				newHDLmPassThruList = buildPassThruListDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.MOD) {
				newMod = buildModDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.REPORTS) {
				newHDLmPassThruReports = buildPassThruReportsDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.REPORT) {
				newHDLmPassThruReport = buildPassThruReportDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.RULES) {
				newHDLmPassThruRules = buildPassThruRulesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.SITE) {
				newHDLmPassThruSite = buildPassThruSiteDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.TOP) {
				newHDLmPassThruTop = buildPassThruTopDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.VALUE) {
				newHDLmPassThruValue = buildPassThruValueDetailsFromJson(jsonObject);
			}
		}
		/* Check if we are building an HDLmTree node for a company. In some cases we
		   will have some details. Generally, we don't have any details for a company
		   tree node. However, we will have details for company tree nodes, if we are
		   building the proxy tree. The details in this case are actually an HDLm proxy
		   object. */
		HDLmProxy newProxy = null;
		if (editorType == HDLmEditorTypes.PROXY) {
			if (newTypeEnum == HDLmTreeTypes.COMPANY) {
				newProxy = buildProxyDetailsFromJson(jsonObject);
			}
		}
		/* Get the node path from the input JSON. We should always have a node path for
		   all HDLmTree instances. */
		ArrayList<String> newNodePath = buildNodePathFromJson(jsonObject);
		/* Build the new HDLmTree object */
		HDLmTree newTree = new HDLmTree(newTypeEnum, newTooltip, newNodePath);
		newTree.setId(idString);
		/* We need to save a reference to the details object, if we created a details
		   object. This won't always be true. However, if it is true, then we must save
		   the details object reference. */
		if (newHDLmPassThruCompanies != null)
			newTree.setDetails(newHDLmPassThruCompanies);
		if (newHDLmPassThruCompany != null)
			newTree.setDetails(newHDLmPassThruCompany);
		if (newHDLmPassThruData != null)
			newTree.setDetails(newHDLmPassThruData);
		if (newHDLmPassThruDivision != null)
			newTree.setDetails(newHDLmPassThruDivision);
		if (newHDLmPassThruIgnore != null)
			newTree.setDetails(newHDLmPassThruIgnore);
		if (newHDLmPassThruLine != null)
			newTree.setDetails(newHDLmPassThruLine);
		if (newHDLmPassThruLines != null)
			newTree.setDetails(newHDLmPassThruLines);
		if (newHDLmPassThruList != null)
			newTree.setDetails(newHDLmPassThruList);
		if (newHDLmPassThruLists != null)
			newTree.setDetails(newHDLmPassThruLists);
		if (newHDLmPassThruReport != null)
			newTree.setDetails(newHDLmPassThruReport);
		if (newHDLmPassThruReports != null)
			newTree.setDetails(newHDLmPassThruReports);
		if (newHDLmPassThruRules != null)
			newTree.setDetails(newHDLmPassThruRules);
		if (newHDLmPassThruSite != null)
			newTree.setDetails(newHDLmPassThruSite);
		if (newHDLmPassThruTop != null)
			newTree.setDetails(newHDLmPassThruTop);
		if (newHDLmPassThruValue != null)
			newTree.setDetails(newHDLmPassThruValue);
		if (newMod != null)
			newTree.setDetails(newMod);
		if (newProxy != null)
			newTree.setDetails(newProxy);
		return newTree;
	}
	/* This routine builds an HDLmTree object from some JSON. This routine calls
	   itself recursively to build the children array of each HDLmTree element.
	   This is the routine that does most of the work of actually build the HDLm
	   tree. */
	protected static HDLmTree buildTreeFromJsonMod(final JsonElement jsonElement, 
			                                           final HDLmEditorTypes editorType) {
		if (jsonElement == null) {
			String  errorText = "JSON element value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonElement instanceof JsonElement)) {
			HDLmAssertAction(false, "JSON element value has incorrect type");
		}
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element passed by the caller is JSON null");
		}
		/* Check if the editor type passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
			HDLmAssertAction(false, "Editor type value is invalid");
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		/* Get each of the values from the JSON object */
		String newTooltip = getStringFromJson(jsonObject, "tooltip");
		if (newTooltip == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "tooltip");
			HDLmAssertAction(false, errorText);
		}
		String newTypeStr = getStringFromJson(jsonObject, "type");
		if (newTypeStr == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "type");
			HDLmAssertAction(false, errorText);
		}
		/* Convert the HDLmTree node type string to uppercase. This step greatly
		   simplifies various checks below. It also makes checking the value easier. */
		String newTopStr = HDLmDefines.getString("HDLMTOPNODENAME");
		String newTopStrUpper = newTopStr.toUpperCase();
		String newTypeStrUpper = newTypeStr.toUpperCase();
		String newTypeStrUpperTop = HDLmDefines.getString("HDLMTOPNODETYPE").toUpperCase();
		if (newTypeStrUpper.equals(newTypeStrUpperTop))
			newTypeStrUpper = newTopStrUpper;
		HDLmTreeTypes newTypeEnum = HDLmTreeTypes.valueOfString(newTypeStrUpper);
		if (newTypeEnum == HDLmTreeTypes.NONE) {
			String errorString = newTypeStr;
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Invalid Tree Type", 20, errorString);
			return null;
		}
		/* Check if we are building an HDLmTree node for pass-through processing. In
		   all (or almost all) cases we have some details. We will actually have details
		   even for the top-level tree node and each of the company nodes. Of course, we
		   will have details for lower-level nodes as well. */
		HDLmMod                 newMod = null;
		HDLmPassThruCompanies   newHDLmPassThruCompanies = null;
		HDLmPassThruCompany     newHDLmPassThruCompany = null;
		HDLmPassThruData        newHDLmPassThruData = null;
		HDLmPassThruDivision    newHDLmPassThruDivision = null;
		HDLmPassThruIgnore      newHDLmPassThruIgnore = null;
		HDLmPassThruLine        newHDLmPassThruLine = null;
		HDLmPassThruLines       newHDLmPassThruLines = null;
		HDLmPassThruList        newHDLmPassThruList = null;
		HDLmPassThruLists       newHDLmPassThruLists = null;
		HDLmPassThruReport      newHDLmPassThruReport = null;
		HDLmPassThruReports     newHDLmPassThruReports = null;
		HDLmPassThruRules       newHDLmPassThruRules = null;
		HDLmPassThruSite        newHDLmPassThruSite = null;
		HDLmPassThruTop         newHDLmPassThruTop = null;
		HDLmPassThruValue       newHDLmPassThruValue = null;
		if (editorType == HDLmEditorTypes.MOD) {
			if (newTypeEnum == HDLmTreeTypes.MOD) {
				newMod = buildModDetailsFromJson(jsonObject);
			}
		}
		if (editorType == HDLmEditorTypes.PASS) {
			if (newTypeEnum == HDLmTreeTypes.COMPANIES) {
				newHDLmPassThruCompanies = buildPassThruCompaniesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.COMPANY) {
				newHDLmPassThruCompany = buildPassThruCompanyDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.DATA) {
				newHDLmPassThruData = buildPassThruDataDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.DIVISION) {
				newHDLmPassThruDivision = buildPassThruDivisionDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.IGNORE) {
				newHDLmPassThruIgnore = buildPassThruIgnoreDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LINES) {
				newHDLmPassThruLines = buildPassThruLinesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LINE) {
				newHDLmPassThruLine = buildPassThruLineDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LISTS) {
				newHDLmPassThruLists = buildPassThruListsDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LIST) {
				newHDLmPassThruList = buildPassThruListDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.MOD) {
				newMod = buildModDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.REPORTS) {
				newHDLmPassThruReports = buildPassThruReportsDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.REPORT) {
				newHDLmPassThruReport = buildPassThruReportDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.RULES) {
				newHDLmPassThruRules = buildPassThruRulesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.SITE) {
				newHDLmPassThruSite = buildPassThruSiteDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.TOP) {
				newHDLmPassThruTop = buildPassThruTopDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.VALUE) {
				newHDLmPassThruValue = buildPassThruValueDetailsFromJson(jsonObject);
			}
		}
		/* Check if we are building an HDLmTree node for a company. In some cases we
		   will have some details. Generally, we don't have any details for a company
		   tree node. However, we will have details for company tree nodes, if we are
		   building the proxy tree. The details in this case are actually an HDLm proxy
		   object. */
		HDLmProxy newProxy = null;
		if (editorType == HDLmEditorTypes.PROXY) {
			if (newTypeEnum == HDLmTreeTypes.COMPANY) {
				newProxy = buildProxyDetailsFromJson(jsonObject);
			}
		}
		/* Get the node path from the input JSON. We should always have a node path for
		   all HDLmTree instances. */
		ArrayList<String> newNodePath = buildNodePathFromJson(jsonObject);
		/* Get all of the children as a JSON array. They are added to the children array
		   below after the current tree instance is constructed. */
		JsonArray jsonArray = getArrayFromJson(jsonObject, "children");
		if (jsonArray == null) {
			HDLmAssertAction(false, "Children array not obtained from JSON");
		}
		/* Build the new HDLmTree object */
		HDLmTree newTree = new HDLmTree(newTypeEnum, newTooltip, newNodePath);
		/* We need to save a reference to the details object, if we created a details
		   object. This won't always be true. However, if it is true, then we must save
		   the details object reference. */
		if (newHDLmPassThruCompanies != null)
			newTree.setDetails(newHDLmPassThruCompanies);
		if (newHDLmPassThruCompany != null)
			newTree.setDetails(newHDLmPassThruCompany);
		if (newHDLmPassThruData != null)
			newTree.setDetails(newHDLmPassThruData);
		if (newHDLmPassThruDivision != null)
			newTree.setDetails(newHDLmPassThruDivision);
		if (newHDLmPassThruIgnore != null)
			newTree.setDetails(newHDLmPassThruIgnore);
		if (newHDLmPassThruLine != null)
			newTree.setDetails(newHDLmPassThruLine);
		if (newHDLmPassThruLines != null)
			newTree.setDetails(newHDLmPassThruLines);
		if (newHDLmPassThruLists != null)
			newTree.setDetails(newHDLmPassThruLists);
		if (newHDLmPassThruList != null)
			newTree.setDetails(newHDLmPassThruList);
		if (newHDLmPassThruReport != null)
			newTree.setDetails(newHDLmPassThruReport);
		if (newHDLmPassThruReports != null)
			newTree.setDetails(newHDLmPassThruReports);
		if (newHDLmPassThruRules != null)
			newTree.setDetails(newHDLmPassThruRules);
		if (newHDLmPassThruSite != null)
			newTree.setDetails(newHDLmPassThruSite);
		if (newHDLmPassThruTop != null)
			newTree.setDetails(newHDLmPassThruTop);
		if (newHDLmPassThruValue != null)
			newTree.setDetails(newHDLmPassThruValue);
		if (newMod != null)
			newTree.setDetails(newMod);
		if (newProxy != null)
			newTree.setDetails(newProxy);
		/* Process each of the children */
		for (JsonElement subElement : jsonArray) {
			HDLmTree subTree = buildTreeFromJsonMod(subElement, editorType);
			newTree.addOrReplaceChild(subTree);
		}
		return newTree;
	}
	/* This routine builds an HDLm modification object from some JSON. The
	   modification object is returned to the caller. This routine does not 
	   appear to be in use other than by some test code, which does use it. */
	protected static HDLmTree  buildTreeFromJsonNotUsed(final JsonElement jsonElement, 
			                                                final HDLmEditorTypes editorType) {
		if (jsonElement == null) {
			String  errorText = "JSON element value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonElement instanceof JsonElement)) {
			HDLmAssertAction(false, "JSON element value has incorrect type");
		}
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element passed by the caller is JSON null");
		}
		/* Check if the editor type passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
			HDLmAssertAction(false, "Editor type value is invalid");
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		/* Get each of the values from the JSON object */
		String newTooltip = getStringFromJson(jsonObject, "tooltip");
		if (newTooltip == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "tooltip");
			HDLmAssertAction(false, errorText);
		}
		String newTypeStr = getStringFromJson(jsonObject, "type");
		if (newTypeStr == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "type");
			HDLmAssertAction(false, errorText);
		}
		/* Convert the HDLmTree node type string to uppercase. This steps greatly
		   simplifies various checks below. It also makes checking the value easier. */
		String newTopStr = HDLmDefines.getString("HDLMTOPNODENAME");
		String newTopStrUpper = newTopStr.toUpperCase();
		String newTypeStrUpper = newTypeStr.toUpperCase();
		String newTypeStrUpperTop = HDLmDefines.getString("HDLMTOPNODETYPE").toUpperCase();
		if (newTypeStrUpper.equals(newTypeStrUpperTop))
			newTypeStrUpper = newTopStrUpper;
		HDLmTreeTypes newTypeEnum = HDLmTreeTypes.valueOfString(newTypeStrUpper);
		if (newTypeEnum == HDLmTreeTypes.NONE) {
			String errorString = newTypeStr;
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Invalid Tree Type", 20, errorString);
			return null;
		}
		/* Check if we are building an HDLmTree node for a modification. If we are,
		   then we should (must) have some details. The details are actually an HDLm
		   modifications object. */
		HDLmMod newMod = null;
		if (newTypeEnum == HDLmTreeTypes.MOD) {
			newMod = buildModDetailsFromJson(jsonObject);
		}
		/* Get the node path from the input JSON. We should always have a node path for
		   all HDLmTree instances. */
		ArrayList<String> newNodePath = buildNodePathFromJson(jsonObject);
		/* Get all of the children as a JSON array. They are added to the children array
		   below after the current tree instance is constructed. */
		JsonArray jsonArray = getArrayFromJson(jsonObject, "children");
		if (jsonArray == null) {
			HDLmAssertAction(false, "Children array not obtained from JSON");
		}
		/* Build the new HDLmTree object */
		HDLmTree newTree = new HDLmTree(newTypeEnum, newTooltip, newNodePath);
		if (newMod != null)
			newTree.setDetails(newMod);
		/* Process each of the children */
		for (JsonElement subElement : jsonArray) {
			HDLmTree subTree = buildTreeFromJsonNotUsed(subElement, editorType);
			newTree.addOrReplaceChild(subTree);
		}
		return newTree;
	}
	/* This routine builds an HDLmTree object from some JSON. This routine calls
	   itself recursively to build the children array of each HDLmTree element.
	   This is the routine that does most of the work of actually build the HDLm
	   tree. */
	protected static HDLmTree buildTreeFromJsonProxy(final JsonElement jsonElement, 
			                                             final HDLmEditorTypes editorType) {
		if (jsonElement == null) {
			String  errorText = "JSON element value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonElement instanceof JsonElement)) {
			HDLmAssertAction(false, "JSON element value has incorrect type");
		}
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element passed by the caller is JSON null");
		}
		/* Check if the editor type passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
			HDLmAssertAction(false, "Editor type value is invalid");
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		/* Get each of the values from the JSON object */
		String newTooltip = getStringFromJson(jsonObject, "tooltip");
		if (newTooltip == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "tooltip");
			HDLmAssertAction(false, errorText);
		}
		String newTypeStr = getStringFromJson(jsonObject, "type");
		if (newTypeStr == null) {
			String  errorText = String.format("String value for member name (%s) not obtained from JSON object", "type");
			HDLmAssertAction(false, errorText);
		}
		/* Convert the HDLmTree node type string to uppercase. This step greatly
		   simplifies various checks below. It also makes checking the value easier. */
		String newTopStr = HDLmDefines.getString("HDLMTOPNODENAME");
		String newTopStrUpper = newTopStr.toUpperCase();
		String newTypeStrUpper = newTypeStr.toUpperCase();
		String newTypeStrUpperTop = HDLmDefines.getString("HDLMTOPNODETYPE").toUpperCase();
		if (newTypeStrUpper.equals(newTypeStrUpperTop))
			newTypeStrUpper = newTopStrUpper;
		HDLmTreeTypes newTypeEnum = HDLmTreeTypes.valueOfString(newTypeStrUpper);
		if (newTypeEnum == HDLmTreeTypes.NONE) {
			String errorString = newTypeStr;
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Invalid Tree Type", 20, errorString);
			return null;
		}
		/* Check if we are building an HDLmTree node for pass-through processing. In
		   all (or almost all) cases we have some details. We will actually have details
		   even for the top-level tree node and each of the company nodes. Of course, we
		   will have details for lower-level nodes as well. */
		HDLmMod                 newMod = null;
		HDLmPassThruTop         newHDLmPassThruTop = null;
		HDLmPassThruCompanies   newHDLmPassThruCompanies = null;
		HDLmPassThruCompany     newHDLmPassThruCompany = null;
		HDLmPassThruData        newHDLmPassThruData = null;
		HDLmPassThruDivision    newHDLmPassThruDivision = null;
		HDLmPassThruIgnore      newHDLmPassThruIgnore = null;
		HDLmPassThruLine        newHDLmPassThruLine = null;
		HDLmPassThruLines       newHDLmPassThruLines = null;
		HDLmPassThruList        newHDLmPassThruList = null;
		HDLmPassThruLists       newHDLmPassThruLists = null;
		HDLmPassThruRules       newHDLmPassThruRules = null;
		HDLmPassThruReports     newHDLmPassThruReports = null;
		HDLmPassThruReport      newHDLmPassThruReport = null;
		HDLmPassThruSite        newHDLmPassThruSite = null;
		HDLmPassThruValue       newHDLmPassThruValue = null;
		if (editorType == HDLmEditorTypes.MOD) {
			if (newTypeEnum == HDLmTreeTypes.MOD) {
				newMod = buildModDetailsFromJson(jsonObject);
			}
		}
		if (editorType == HDLmEditorTypes.PASS) {
			if (newTypeEnum == HDLmTreeTypes.COMPANIES) {
				newHDLmPassThruCompanies = buildPassThruCompaniesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.COMPANY) {
				newHDLmPassThruCompany = buildPassThruCompanyDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.DATA) {
				newHDLmPassThruData = buildPassThruDataDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.DIVISION) {
				newHDLmPassThruDivision = buildPassThruDivisionDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.IGNORE) {
				newHDLmPassThruIgnore = buildPassThruIgnoreDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LINES) {
				newHDLmPassThruLines = buildPassThruLinesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LINE) {
				newHDLmPassThruLine = buildPassThruLineDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LISTS) {
				newHDLmPassThruLists = buildPassThruListsDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.LIST) {
				newHDLmPassThruList = buildPassThruListDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.MOD) {
				newMod = buildModDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.REPORTS) {
				newHDLmPassThruReports = buildPassThruReportsDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.REPORT) {
				newHDLmPassThruReport = buildPassThruReportDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.RULES) {
				newHDLmPassThruRules = buildPassThruRulesDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.SITE) {
				newHDLmPassThruSite = buildPassThruSiteDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.TOP) {
				newHDLmPassThruTop = buildPassThruTopDetailsFromJson(jsonObject);
			}
			if (newTypeEnum == HDLmTreeTypes.VALUE) {
				newHDLmPassThruValue = buildPassThruValueDetailsFromJson(jsonObject);
			}
		}
		/* Check if we are building an HDLmTree node for a company. In some cases we
		   will have some details. Generally, we don't have any details for a company
		   tree node. However, we will have details for company tree nodes, if we are
		   building the proxy tree. The details in this case are actually an HDLm proxy
		   object. */
		HDLmProxy newProxy = null;
		if (editorType == HDLmEditorTypes.PROXY) {
			if (newTypeEnum == HDLmTreeTypes.COMPANY) {
				newProxy = buildProxyDetailsFromJson(jsonObject);
			}
		}
		/* Get the node path from the input JSON. We should always have a node path for
		   all HDLmTree instances. */
		ArrayList<String> newNodePath = buildNodePathFromJson(jsonObject);
		/* Get all of the children as a JSON array. They are added to the children array
		   below after the current tree instance is constructed. */
		JsonArray jsonArray = getArrayFromJson(jsonObject, "children");
		if (jsonArray == null) {
			HDLmAssertAction(false, "Children array not obtained from JSON");
		}
		/* Build the new HDLmTree object */
		HDLmTree newTree = new HDLmTree(newTypeEnum, newTooltip, newNodePath);
		/* We need to save a reference to the details object, if we created a details
		   object. This won't always be true. However, if it is true, then we must save
		   the details object reference. */
		if (newHDLmPassThruCompanies != null)
			newTree.setDetails(newHDLmPassThruCompanies);
		if (newHDLmPassThruCompany != null)
			newTree.setDetails(newHDLmPassThruCompany);
		if (newHDLmPassThruData != null)
			newTree.setDetails(newHDLmPassThruData);
		if (newHDLmPassThruDivision != null)
			newTree.setDetails(newHDLmPassThruDivision);
		if (newHDLmPassThruIgnore != null)
			newTree.setDetails(newHDLmPassThruIgnore);
		if (newHDLmPassThruLine != null)
			newTree.setDetails(newHDLmPassThruLine);
		if (newHDLmPassThruLines != null)
			newTree.setDetails(newHDLmPassThruLines);
		if (newHDLmPassThruLists != null)
			newTree.setDetails(newHDLmPassThruLists);
		if (newHDLmPassThruList != null)
			newTree.setDetails(newHDLmPassThruList);
		if (newHDLmPassThruReport != null)
			newTree.setDetails(newHDLmPassThruReport);
		if (newHDLmPassThruReports != null)
			newTree.setDetails(newHDLmPassThruReports);
		if (newHDLmPassThruRules != null)
			newTree.setDetails(newHDLmPassThruRules);
		if (newHDLmPassThruSite != null)
			newTree.setDetails(newHDLmPassThruSite);
		if (newHDLmPassThruTop != null)
			newTree.setDetails(newHDLmPassThruTop);
		if (newHDLmPassThruValue != null)
			newTree.setDetails(newHDLmPassThruValue);
		if (newMod != null)
			newTree.setDetails(newMod);
		if (newProxy != null)
			newTree.setDetails(newProxy);
		/* Process each of the children */
		for (JsonElement subElement : jsonArray) {
			HDLmTree subTree = buildTreeFromJsonProxy(subElement, editorType);
			newTree.addOrReplaceChild(subTree);
		}
		return newTree;
	}
	/* Try to access a field in the JSON used to build the current object.
     Report an error if the field is not found. If an error is reported,
     the error count is also incremented. The return value from this function 
     is the value of the field, if the field is found. This routine checks 
     the actual details (which should be an HDLmMod). */
	protected static HDLmMod  checkFieldDetails(final HDLmEditorTypes editorType, 
																					    final MutableInt errorCounter,
																					    final ArrayList<String> errorMessages,
																					    final JsonObject jsonObject, 
																					    final Set<String> jsonKeys,
																					    final String name, 
																					    final HDLmReportErrors reportErrors) {
		/* Check a few fields passed by the caller */
		if (errorCounter == null) {
			String  errorText = "Mutable int for error counter passed to checkFieldDetails is null";
			throw new NullPointerException(errorText);
		}
		if (errorMessages == null) {
			String  errorText = "ArrayList for error messages passed to checkFieldDetails is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to checkFieldDetails is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to checkFieldDetails is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to checkFieldDetails is null";
			throw new NullPointerException(errorText);
		} 
		if (reportErrors == null) {
			String  errorText = "Report errors enum passed to checkFieldDetails is null";
			throw new NullPointerException(errorText);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorText = "Report errors enum passed to checkFieldDetails is NONE";
			throw new NullPointerException(errorText);
		}
		/* Run the checkFieldObject method. This method returns a JsonObject
		   and may set various error field. */ 
		{
	    String  errorMessagePrefix = "Tree";
	    int     errorNumberMissing = 64;
	    int     errorNumberIsNull = 65;
	    int     errorNumberIsPrimitive = 65;
	    int     errorNumberNotObject = 65;
	    int     errorNumberException = 65;  
			HDLmField.checkFieldJsonObject(editorType, 
					                           errorCounter, 
					                           errorMessages, 
					                           jsonObject, 
					                           jsonKeys, 
					                           name, 
					                 		       errorMessagePrefix,
															       errorNumberMissing,
															       errorNumberIsNull,
															       errorNumberIsPrimitive,
															       errorNumberNotObject,
															       errorNumberException,
					                           reportErrors);
			/* Check if any errors were found */ 
		  int   errorCountInt = errorCounter.intValue(); 
		  if (errorCountInt > 0) 
		  	return null;			
		}
	  boolean   fieldFound = false;
	  HDLmMod   newDetails = null;   
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if we have the field in the JSON */
			if (!jsonKeys.contains(name))
				break;
			/* Show that the field was found */
			fieldFound = true;
			/* Get they array of JSON objects for the strings */
			JsonElement   jsonElement = jsonObject.get(name);
			if (jsonElement.isJsonNull()) {
		    HDLmField.reportError(editorType, 
		    		                  errorCounter, 
									            errorMessages,
									            jsonObject, 
									            name,
									            "Tree JSON field is null", 
									            65, 
									            reportErrors);
				break;
			}
			/* Get some values from the input JSON */
			if (!jsonElement.isJsonObject()) {
		    HDLmField.reportError(editorType, 
		    		                  errorCounter, 
	                            errorMessages,
	                            jsonObject,
	                            "name",
	                            "Tree JSON field is not an object", 
	                            65, 
	                            reportErrors);			
				break;
		  }
			/* Check the actual details (and HDLmMod). This routine
			   produced many error messages if the details are in 
			   error. */
			newDetails = new HDLmMod(jsonElement);
			/* Check if any errors were found in the details (the 
			   HDLmMod). Copy the errors out of the HDLmod into  
			   the parent error reporting areas. */ 
			if (newDetails.getErrorCount() > 0) {
				errorCounter.add(newDetails.getErrorCount());
				errorMessages.addAll(newDetails.getErrorMsgs());
			}
			break;
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
	  	HDLmField.reportErrorNoValue(editorType, 
	  			                         errorCounter,
		                               errorMessages,
		                               jsonObject, 
		                               name, 
		                               "Tree JSON missing field", 
		                               64, 
		                               reportErrors);
		return newDetails;
	}	
	/* Try to access a field in the JSON used to build the current object.
	   Report an error if the field is not found. If an error is reported,
	   the error count is also incremented. The return value from this function 
	   is the value of the field, if the field is found. */
	protected static HDLmTreeTypes  checkFieldTreeType(final HDLmEditorTypes editorType, 
																								     final MutableInt errorCounter,
																								     final ArrayList<String> errorMessages,
																								     final JsonObject jsonObject, 
																								     final Set<String> jsonKeys,
																								     final String name, 
																								     final HDLmReportErrors reportErrors) {
		/* Check a few fields passed by the caller */
		if (errorCounter == null) {
			String  errorText = "Mutable int for error counter passed to checkFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		if (errorMessages == null) {
			String  errorText = "ArrayList for error messages passed to checkFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to checkFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to checkFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to checkFieldTreeType is null";
			throw new NullPointerException(errorText);
		} 
		if (reportErrors == null) {
			String  errorText = "Report errors enum passed to checkFieldTreeType is null";
			throw new NullPointerException(errorText);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorText = "Report errors enum passed to checkFieldTreeType is NONE";
			throw new NullPointerException(errorText);
		}
    boolean         fieldFound = false;
    HDLmTreeTypes   modTreeType = HDLmTreeTypes.NONE;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if we have the field in the JSON */
			if (!jsonKeys.contains(name))
				break;
			/* Show that the field was found */
			fieldFound = true;
			/* Get they array of JSON objects for the strings */
			JsonElement jsonElement = jsonObject.get(name);
			if (jsonElement.isJsonNull()) {
		    HDLmField.reportError(editorType, 
		    		                  errorCounter, 
									            errorMessages,
									            jsonObject, 
									            "Tree JSON field is null",
									            name,
									            65, 
									            reportErrors);
				break;
			}
			/* Get some values from the input JSON */
			if (!jsonElement.isJsonPrimitive()) {
		    HDLmField.reportError(editorType, 
		    		                  errorCounter, 
	                            errorMessages,
	                            jsonObject,
	                            name,
	                            "Tree JSON field is not a primitive", 
	                            65, 
	                            reportErrors);			
				break;
		  }
			/* Get the value of the type field */
			String  localTypeString = jsonElement.getAsString();	 
			/* Get the tree type and check the value */ 
			modTreeType = HDLmTreeTypes.valueOfString(localTypeString);
			if (modTreeType == HDLmTreeTypes.NONE) {
		  	String  value = localTypeString;
			  HDLmField.reportErrorValue(editorType, 
			  		                       errorCounter,
			                             errorMessages,
			                             jsonObject, 
			                             name, 
			                             value, 
			                             "Tree JSON invalid field", 
			                             66, 
			                             reportErrors);
			}		
			if (modTreeType == null) {
		  	String  value = localTypeString;
			  HDLmField.reportErrorValue(editorType, 
			  		                       errorCounter,
			                             errorMessages,
			                             jsonObject, 
			                             name, 
			                             value, 
			                             "Tree JSON invalid field", 
			                             67, 
			                             reportErrors);
			}	
			break;
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
	  	HDLmField.reportErrorNoValue(editorType, 
	  			                         errorCounter,
		                               errorMessages,
		                               jsonObject, 
		                               name, 
		                               "Tree JSON missing field", 
		                               64, 
		                               reportErrors);
		return modTreeType;
  }
	/* This routine checks if it is passed a valid tree JSON object. All of the 
	   tree fields must be present and they must be valid. The checks include
	   checking the HDLmMod instance contained in the tree JSON object. The 
	   HDLmMod instance is called 'details' (without the quotes). The return
	   value is a class instance that may (or may not) contain an error message. */ 
	@SuppressWarnings("unused")
	protected static HDLmResponse  checkTreeJsonObj(final JsonElement jsonElement) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element used to check tree JSON is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few fields for use below */
		String  jsonFieldName;
		/* Allocate the response object. This object is used to 
	     return error messages to the caller. */
    HDLmResponse  outResponse = new HDLmResponse();
		/* Set the error count to zero. The error count is incremented each time an
		   error is detected. If the final error count (for the current tree element) 
		   is greater than zero, the current modification object is disabled (the enabled
		   field is set false). Note that a reference is used below so that the error
		   count can be updated by the routines called using error count.*/
		MutableInt  errorCounter = new MutableInt(0);		
		/* Build an array list for error message strings. Each error
		   message is stored in this array list. */
		ArrayList<String>   errorMessages = new ArrayList<String>();
		if (errorMessages == null) {
			String  errorText = "Error message ArrayList allocation in checkTreeJsonObj is null";
			throw new NullPointerException(errorText);
		}	
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element passed to checkTreeJsonObj is not a JSON object");
		}
		/* Check if the JSON element is a JSON null */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element used to checkTreeJsonObj is JSON null");
		}
		/* Get the list of keywords and values in the JSON object */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		Set<String>   jsonKeys = jsonObject.keySet();		
		/* Check the tooltip value. The tooltip must be present and 
		   a non-zero length string. */ 
		{
			String  errorMessagePrefix = "Tree";
			int     errorNumberMissing = 64; 
	    int     errorNumberIsNull = 65;
	    int     errorNumberNotPrimitive = 65;
	    int     errorNumberException = 65;
	    int     errorNumberInvalidLength = 65;
	    int     errorNumberInvalidWhiteSpace = 65;
			String  localToolTip = HDLmField.checkFieldString(editorType, 
					                                              errorCounter, 		
																							          errorMessages, 
																							          jsonObject, 
																							          jsonKeys, 
																							          "tooltip", 
																							          errorMessagePrefix,
																							     		  errorNumberMissing,
																							          errorNumberIsNull,
																							          errorNumberNotPrimitive,
																							          errorNumberException,
																							          errorNumberInvalidLength,
																							          errorNumberInvalidWhiteSpace, 
																							          HDLmWhiteSpace.WHITESPACENOTOK,
																							          HDLmReportErrors.REPORTERRORS,
																							          HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		}
		/* Check the nodePath value. The node path must be present and 
	     must contain non-zero length strings. */ 
		Integer   minArraylength = HDLmDefines.getNumber("HDLMMINPASSNODEPATHLENGTH");
		Integer   maxArraylength = HDLmDefines.getNumber("HDLMMAXPASSNODEPATHLENGTH");
		ArrayList<String>  localNodePath = null;
		{
	    String  errorMessagePrefix = "Tree";
	    int     errorNumberMissing = 64;
	    int     errorNumberIsNull = 65;
	    int     errorNumberIsPrimitive = 65;
	    int     errorNumberNotPrimitive = 65;
	    int     errorNumberNotArray = 65;
	    int     errorNumberException = 65;
	    int     errorNumberTooSmall = 70;
	    int     errorNumberTooLarge = 71; 
	    int     errorNumberInvalidLength = 65;
	    int     errorNumberInvalidWhiteSpace = 65;
		  localNodePath = HDLmField.checkFieldStringArray(editorType, 
                                                      errorCounter, 		
												                              errorMessages, 
																						          jsonObject, 
																						          jsonKeys, 
																						          "nodePath", 
																						          minArraylength,
																						          maxArraylength,			
																						          errorMessagePrefix,
																						          errorNumberMissing,
																						          errorNumberIsNull,
																						          errorNumberIsPrimitive,
																						          errorNumberNotPrimitive,
																						          errorNumberNotArray,
																						          errorNumberException,
																						          errorNumberTooSmall,
																						          errorNumberTooLarge, 
																						          errorNumberInvalidLength,
																						          errorNumberInvalidWhiteSpace,
																						          HDLmWhiteSpace.WHITESPACENOTOK,
																						          HDLmReportErrors.REPORTERRORS,
																						          HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		}
	  /* Check the division value. Only some values are allowed. If the value
	     is invalid, report an error. */
	  int     localDivisionIndex = HDLmDefines.getNumber("HDLMDIVISIONNODEPATHINDEX");
	  String  localDivisionStr = localNodePath.get(localDivisionIndex);
	  String  localDivisionExpected = HDLmDefines.getString("HDLMDIVISIONNODENAME");
	  if (!localDivisionStr.equals(localDivisionExpected)) {
			HDLmField.reportErrorValue(editorType, 
                                 errorCounter, 
                                 errorMessages,
                                 jsonObject, 
                                 "division", 
                                 localDivisionStr, 
                                 "nodePath division is invalid",
                                 66,
                                 HDLmReportErrors.REPORTERRORS);  	
	  }	
	  /* Check the site value. Only some values are allowed. If the value
       is invalid, report an error. */
    int     localSiteIndex = HDLmDefines.getNumber("HDLMSITENODEPATHINDEX");
    String  localSiteStr = localNodePath.get(localSiteIndex);
    String  localSiteExpected = HDLmDefines.getString("HDLMSITENODENAME");
		if (!localSiteStr.equals(localSiteExpected)) {
			HDLmField.reportErrorValue(editorType, 
		                             errorCounter, 
		                             errorMessages,
		                             jsonObject, 
		                             "site", 
		                             localSiteStr, 
		                             "nodePath site is invalid",
		                             66,
		                             HDLmReportErrors.REPORTERRORS);  	
		}	
		/* Check the tree type value. The tree type must be present and 
	     a valid string. */ 
		HDLmTreeTypes   localTreeType = HDLmTree.checkFieldTreeType(editorType, 
				                                                        errorCounter, 		
																												        errorMessages, 
																												        jsonObject, 
																												        jsonKeys, 
																												        "type",  
																												        HDLmReportErrors.REPORTERRORS);
		/* Check if the details (HDLmMod) are present and if any errors
		   can be found */ 
		HDLmMod  localMod = HDLmTree.checkFieldDetails(editorType, 
				                                           errorCounter, 		
																						       errorMessages, 
																						       jsonObject, 
																						       jsonKeys, 
																						       "details",  
																						       HDLmReportErrors.REPORTERRORS);		
		/* Check if any errors were found. If any errors were found, store 
		   them in the response object */ 
		int     errorCountInt = errorCounter.intValue(); 
		if (errorCountInt > 0) {
			outResponse.setReturnNumber(errorCountInt);
			outResponse.setErrorMessage(errorMessages.get(0));			
		}
		else {
			outResponse.setReturnCodeZero();	
			outResponse.setReturnNumberZero();
		}
		return outResponse;
	}	    
	/* This routine checks all of the subnodes of the parent node passed to it and
	   counts the number of subnodes with matching names. The number of subnodes
	   with matching names may be zero or greater than zero. The name matching
	   algorithm is caseless. In other words, ABCD is deemed to match abcd. Note
	   that ABCD(2) will also match abcd.
	   
	   A null value can be (and is in some cases) passed for current tree node to
	   force all of the children of the parent tree node to be checked. Passing a
	   null value for current tree node is not an error condition.
	   
	   The removal of file number tails (such as (2)) is actually optional. The
	   caller passes a flag that controls this behavior. If the flag is set to true,
	   then file number tails are removed. If this flag is set to false, then file
	   number tails are not removed. */
	protected static HDLmNameMatch countSubNodeNames(String nodeName, 
			                                             final HDLmTree parentTreeNode, 
			                                             final HDLmTree currentTreeNode,
			boolean removeTails) {
		/* Check if the node name passed by the caller is null */
		if (nodeName == null) {
			String  errorText = "Node name passed to countSubNodeNames is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the parent tree node passed by the caller is null */
		if (parentTreeNode == null) {
			String  errorText = "Parent tree node passed to countSubNodeNames is null";
			throw new NullPointerException(errorText);
		}
		HDLmNameMatch matchObj = new HDLmNameMatch();
		/* The code below will convert the file name passed by the caller in two ways.
		   First any numeric file number tail (such as (3)) will be removed. The file
		   name will then be converted to lower case. Note that the file name may not
		   have a file number tail. This is not an error condition. */
		if (removeTails)
			nodeName = HDLmString.removeFileNumberTail(nodeName);
		nodeName = nodeName.toLowerCase();
		/* Get the children of the parent tree node and the number of children */
		ArrayList<HDLmTree>   parentTreeNodeChildren = parentTreeNode.children;
		int                   parentTreeNodeChildrenLen = parentTreeNodeChildren.size();
		/* Check all of the names in the subnodes of the current parent node */
		for (int i = 0; i < parentTreeNodeChildrenLen; i++) {
			/* Get the current child node (an HDLmTree) */
			HDLmTree  childTreeNode = parentTreeNodeChildren.get(i);
			if (childTreeNode == null) {
				HDLmAssertAction(false, "Child tree node is null in loop");
			}
			/* Check if the we are checking against ourself. We don't need to check for a
			   match in our own node. */
			if (currentTreeNode != null && currentTreeNode == childTreeNode)
				continue;
			/* Get the subnode name with the file number tail removed (if any) and converted
			   to lower case */
			String siblingName = childTreeNode.getLastNodePathEntry();
			String siblingNameSave = siblingName;
			if (removeTails)
				siblingName = HDLmString.removeFileNumberTail(siblingName);
			siblingName = siblingName.toLowerCase();
			/* Check if the names match exactly */
			if (siblingName.equals(nodeName)) {
				matchObj.incrementCount();
				matchObj.addName(siblingNameSave);
			}
		}
		return matchObj;
	}	
	/* This routine does all of the work needed to delete a node from the node tree
	   and the database. Note that the subnodes of the current node (if any) are 
	   deleted first. Note also that the current node is removed from the children
	   array of the parent node (if any). When the aforementioned steps have been 
	   completed, the node is (Java) deleted to free any storage used by the node. */
	protected static void  deleteNode(final HDLmTree treePos,
			                              final ArrayList<Integer> idArray) {
		/* Check if the tree position reference passed by the caller is null */
		if (treePos == null) {
			String  errorText = "Tree position reference passed to deleteNode is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the ID array reference passed by the caller is null */
		if (idArray == null) {
			String  errorText = "ID array reference passed to deleteNode is null";
			throw new NullPointerException(errorText);
		}
		/* Try to delete all of the children (if any) of the current node */
		ArrayList<HDLmTree>  childArray = treePos.getChildren();
		int                  childArrayLen = childArray.size();
		if (childArrayLen > 0) {
			HDLmTree.deleteSubNodes(treePos, idArray);
		}		
		/* Delete the current node from the subnodes array of the parent 
		   of the current node (if the current tree node has a parent) */
		ArrayList<String>  nodePath = treePos.getNodePath();
		int                nodePathLen = nodePath.size();
		if (nodePathLen > 1) {
			ArrayList<String>  nodePathParent = new ArrayList<String>(nodePath);
			nodePathParent.remove(nodePathLen - 1);
			/* Try to find the parent tree node from the parent node path.
		     This will be the parent of the current tree node. */   
	    HDLmTree  parentNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
  	  		                                           nodePathParent);		
			if (parentNode == null) {
				String  nodeString = nodePath.toString(); 
				HDLmUtility.logString(nodeString, LOG);
				HDLmUtility.logStackTrace();
				HDLmAssertAction(false, "Null parent tree node returned by locateTreeNode");
			}
			/* Search all of the children of the parent node looking for the current 
			   node */
			String  currentNodeName = treePos.getLastNodePathEntry();
			int   targetIndex = -1;
			ArrayList<HDLmTree>  childArrayParent = parentNode.getChildren();
			int                  childArrayParentLen = childArrayParent.size();
			for(int i = 0; i < childArrayParentLen; i++) {
				HDLmTree  childNode = childArrayParent.get(i);
				String    childName = childNode.getLastNodePathEntry();
				if (childName.compareTo(currentNodeName) == 0) {
					targetIndex = i;
					break;
				}				
			}
			/* Check if we found a matching name. We should always find 
			   a matching name, but you never know. */ 
			if (targetIndex < 0) {
				String  errorFormat = "Current name (%s) not found in children of parent";
				String  errorText = String.format(errorFormat, currentNodeName);
				throw new NoSuchElementException(errorText);				
			}
			/* Remove the current node from the subnodes of the parent node */
			childArrayParent.remove(targetIndex);
			/* Delete the tree entry and the details (if any) */
			HDLmMod  currentDetails = treePos.getDetails();
			if (currentDetails != null) 
				treePos.details = null;		
			/* Add the current node to the list (array list) of nodes to 
			   be deleted from the database */
			idArray.add(Integer.valueOf(treePos.getId()));
		}
	}	 
	/* This routine does all of the work needed to delete a node from the node tree
	   and the database. Note that the subnodes of the current node (if any) are 
	   deleted first. Note also that the current node is removed from the children
	   array of the parent node (if any). When the aforementioned steps have been 
	   completed, the node is (Java) deleted to free any storage used by the node. */
	protected static void  deleteNodeTreeDatabase(final HDLmTree treePos) {
		/* Check if the tree position reference passed by the caller is null */
		if (treePos == null) {
			String  errorText = "Tree position reference passed to deleteNode is null";
			throw new NullPointerException(errorText);
		}
	  /* Declare and define a few values */
	  ArrayList<Integer>  idArray = new ArrayList<Integer>();
		if (idArray == null) {
			String  errorText = "ID array not allocated in getIdArray";
			HDLmAssertAction(false, errorText);
		}
		/* Delete the current node from the node tree */
		HDLmTree.deleteNode(treePos, idArray);
		/* Update the database */
		HDLmTree.passDeleteRows(idArray);
	}	
	/* This routine does all of the work needed to delete the subnodes of 
	   the current node. The passed node may, or may not, have any actual 
	   subnodes. The lack of subnode is not an error condition  */
  protected static void  deleteSubNodes(final HDLmTree treePos,
  		                                  final ArrayList<Integer> idArray) {
		/* Check if the tree position reference passed by the caller is null */
		if (treePos == null) {
			String  errorText = "Tree position reference passed to deleteSubNodes is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the ID array reference passed by the caller is null */
		if (idArray == null) {
			String  errorText = "ID array reference passed to deleteSubNodes is null";
			throw new NullPointerException(errorText);
		}
		/* Try to delete all of the children (if any) of the current node.
		   This step is repeated until the child array is empty. */
		while (true) {
		  ArrayList<HDLmTree>  childArray = treePos.getChildren();
		  int                  childArrayLen = childArray.size();
		  /* Check if the child array is empty */
		  if (childArrayLen <= 0)
		  	break;
		  /* Run delete on the first child array entry */	
		  HDLmTree  subNode = childArray.get(0);
			HDLmTree.deleteNode(subNode, idArray);						
		} 
  }
	/* This method scans a parameter number usage map to find the parameter number
	   that is used the least. The least used parameter number is returned to the
	   caller. Note that if the parameter map does not have any information for a
	   parameter number, then the usage is assumed to be zero. */
	protected static int findLowestParameter(final Map<Integer, Integer> parmMap) {
		int currentCount;
		int maxParameterCount = HDLmDefines.getNumber("HDLMMAXPARAMETERCOUNT");
		int maxValue = Integer.MAX_VALUE;
		int minParmNumber = 0;
		/* Check all of the parameter numbers looking for the one that is used the least */
		for (int i = 0; i < maxParameterCount; i++) {
			/* Check if the parameter map passed by the caller has an entry for the current
			   parameter number */
			if (parmMap.containsKey(Integer.valueOf(i)))
				currentCount = parmMap.get(Integer.valueOf(i));
			else
				currentCount = 0;
			/* Check if the current count is less than lowest value we have seen so far */
			if (currentCount < maxValue) {
				maxValue = currentCount;
				minParmNumber = i;
				/* If the current count is zero, then we really don't need to keep searching. We
				   are done at this point. */
				if (currentCount == 0)
					break;
			}
		}
		return minParmNumber;
	}
	/* Get an array of JSON elements from the JSON object, if possible. Note that
	   this routine returns a null value if the array of JSON elements could not be
	   obtained. This routine does not issue any error messages. */
	protected static JsonArray getArrayFromJson(final JsonObject jsonObject, 
			                                        final String memberName) {
		JsonArray localArray = null;
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		if (memberName == null) {
			String  errorText = "JSON member name value is null";
			throw new NullPointerException(errorText);
		}
		if (!(memberName instanceof String)) {
			HDLmAssertAction(false, "JSON member name value has incorrect type");
		}
		/* Check if the JSON object really has the member name passed by the caller */
		if (!jsonObject.has(memberName)) {
			return localArray;
		}
		/* Get the JSON element and obtain the value */
		if (jsonObject.isJsonNull()) {
			return localArray;
		}
		JsonElement jsonElement = jsonObject.get(memberName);
		/* We need to make sure that the JSON element is not a JSON null */
		if (jsonElement.isJsonNull()) {
			return localArray;
		}
		/* We need to make sure that the JSON element is an array value */
		if (!jsonElement.isJsonArray()) {
			return localArray;
		}
		localArray = jsonElement.getAsJsonArray();
		return localArray;
	}
	/* Get the children from an HDLmTree element */
	protected ArrayList<HDLmTree> getChildren() {
		return children;
	}
	/* Get the details from an HDLmTree element. Actual details are only available
	   for modification HDLmTree elements. */
	protected HDLmMod getDetails() {
		return details;
	}
	/* Get the ID string for an HDLmTree element */
	protected String getId() {
		return id;
	}
	/* This routine extracts all of the ID values from a set
	   of rows passed by the caller and returns an array of 
	   ID values The row came from a read all operation that 
	   returned all of the rows with a specific content value. */
	protected static ArrayList<Integer>  getIdArray(final ArrayList<HDLmDatabaseRow> rowList) {
		/* Check if the row list reference passed by the caller is null */
		if (rowList == null) {
			String  errorText = "Row list reference passed to getIdArray is null";
			throw new NullPointerException(errorText);
		}
	  /* Declare and define a few values */
	  ArrayList<Integer>  idArray = new ArrayList<Integer>();
		if (idArray == null) {
			String  errorText = "ID array not allocated in getIdArray";
			HDLmAssertAction(false, errorText);
		}
	  /* Extract all of the ID values from the row array */
	  int   rowCount = rowList.size();
	  int   i;
	  /* Process each of the rows */
	  for (i = 0; i < rowCount; i++) {
      HDLmDatabaseRow   currentRow = rowList.get(i);
	    idArray.add(HDLmUtility.convertInteger(currentRow.getId()));
	  }
	  return idArray;
	}
  /* This routine extracts ID values from the node tree 
	   and stores the ID values in an array */ 
	protected static void  getIdValuesFromNodeTree(final ArrayList<Integer> treeIdArray, 
			                                           final HDLmTree treePos) {
		/* Check if the ID array reference passed by the caller is null */
		if (treeIdArray == null) {
			String  errorText = "ID array reference passed to getIdValuesFromNodeTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the tree position reference passed by the caller is null */
		if (treePos == null) {
			String  errorText = "Tree position reference passed to getIdValuesFromNodeTree is null";
			throw new NullPointerException(errorText);
		}
	  /* Add an entry to the tree array */
		String  treePosId = treePos.getId();
		if (treePosId == null) {
			String  errorText = "ID value obtained from tree position in getIdValuesFromNodeTree is null";
			throw new NullPointerException(errorText);
		}
		if (StringUtils.isWhitespace(treePosId)) {
			String  errorText = "ID value obtained from tree position in getIdValuesFromNodeTree is whitespace";
		}
	  treeIdArray.add(HDLmUtility.convertInteger(treePosId));
	  /* Process all of the children of the current node */
	  ArrayList<HDLmTree>   childrenArray = treePos.children;
	  int   childrenCount = childrenArray.size();
	  int   i;
	  for (i = 0; i < childrenCount; i++) {
	    HDLmTree  curNode = childrenArray.get(i);
	    HDLmTree.getIdValuesFromNodeTree(treeIdArray, curNode);
	  }
	  return;
	}
	/* Get the integer value of a JSON element, if possible. If the integer value
	   can not be obtained, a null value will be returned to the caller. No error
	   messages will be issued. */
	protected static Integer  getIntegerFromJson(final JsonObject jsonObject, 
			                                         final String memberName) {
		Integer localInt = null;
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		if (memberName == null) {
			String  errorText = "JSON member name value is null";
			throw new NullPointerException(errorText);
		}
		if (!(memberName instanceof String)) {
			HDLmAssertAction(false, "JSON member name value has incorrect type");
		}
		/* Check if the JSON object really has the member name passed by the caller */
		if (!jsonObject.has(memberName)) {
			return localInt;
		}
		/* Get the JSON element and obtain the value */
		JsonElement jsonElement = jsonObject.get(memberName);
		/* We need to make sure that the JSON element is a primitive value */
		if (!jsonElement.isJsonPrimitive()) {
			return localInt;
		}
		localInt = jsonElement.getAsInt();
		return localInt;
	}
  /* This routine converts one HDLmTree instance to a string and returns
	   the string to the caller. The string is suitable for adding to the
	   database. Note that this routine does not handle the children of the
	   HDLmTree instance. A different routine must handle the children. 
	   The name of this routine was changed to make it clear which routine
	   is being executed. */
	protected static String  getJsonStringTree(final HDLmTree treePos) {
		/* Check if the tree position reference passed by the caller is null */		 
	  if (treePos == null) {
		  String  errorText = "Tree position reference passed by the caller to getJsonString is null";
			throw new NullPointerException(errorText);
		}
		/* Get a few values from the current HDLmTree instance */
		HDLmTreeTypes       tempType = treePos.getType();
		String              tempTooltip = treePos.getTooltip();
		ArrayList<String>   tempNodePath = treePos.getNodePath();
		/* Allocate the temporary HDLmTree instance */		 
		HDLmTree  tempTreePos = new HDLmTree(tempType, tempTooltip, tempNodePath);
		/* Check if the temporary tree node was properly allocated */ 
		if (tempTreePos == null) {
			String  errorText = "Temporary tree node was not allocated";
			HDLmAssertAction(false, errorText);
		}
	  String  infoStr = null;
	  tempTreePos.setChildrenNull();
		/* Add the details information (an HDLmMod) if possible */
		if (treePos.getDetails() != null) {
			/* Get the HDLmMod reference from the current tree position.
			   This may actually be something that extends the HDLmMod
			   instance. */
		  HDLmMod   treeMod = treePos.getDetails();
		  HDLmMod   tempTreeMod = null; 	
		  switch (treePos.getType()) {
		    case COMPANIES: {
					HDLmPassThruCompanies   newHDLmPassThruCompanies = new HDLmPassThruCompanies();
					tempTreeMod = newHDLmPassThruCompanies;
					Instant   savedCreated = ((HDLmPassThruCompanies) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruCompanies) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */	
					newHDLmPassThruCompanies.setDummyCreated(savedCreated.toString());
					newHDLmPassThruCompanies.setDummyLastModified(savedLastModified.toString());	
					newHDLmPassThruCompanies.setCompaniesCount(((HDLmPassThruCompanies) treeMod).getCompaniesCount());
					newHDLmPassThruCompanies.setCompaniesTreeMapNull(); 	
					newHDLmPassThruCompanies.setAssociatedNodeTypeNull(); 	
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setExtra("");
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case COMPANY: {
					HDLmPassThruCompany   newHDLmPassThruCompany = new HDLmPassThruCompany();
					tempTreeMod = newHDLmPassThruCompany;
					Instant   savedCreated = ((HDLmPassThruCompany) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruCompany) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */	 
					newHDLmPassThruCompany.setDummyCreated(savedCreated.toString());
					newHDLmPassThruCompany.setDummyLastModified(savedLastModified.toString());		
					newHDLmPassThruCompany.setStatusNull(); 
					newHDLmPassThruCompany.setAssociatedNodeTypeNull(); 
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setDummyPassThru((Boolean) false);
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setEnabled((Boolean) null);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);				 
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case DATA: {
					HDLmPassThruData  newHDLmPassThruData = new HDLmPassThruData();
					Instant   savedCreated = ((HDLmPassThruData) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruData) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */	 
					newHDLmPassThruData.setDummyCreated(savedCreated.toString());
					newHDLmPassThruData.setDummyLastModified(savedLastModified.toString());		
					tempTreeMod = newHDLmPassThruData; 
					newHDLmPassThruData.setDivisionsCount(((HDLmPassThruData) treeMod).getDivisionsCount()); 
					tempTreeMod = newHDLmPassThruData;
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case DIVISION: {
					HDLmPassThruDivision  newHDLmPassThruDivision = new HDLmPassThruDivision();
					tempTreeMod = newHDLmPassThruDivision;
					Instant   savedCreated = ((HDLmPassThruDivision) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruDivision) treeMod).getLastModified(); 
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */ 
					newHDLmPassThruDivision.setDummyCreated(savedCreated.toString());
					newHDLmPassThruDivision.setDummyLastModified(savedLastModified.toString());		
					newHDLmPassThruDivision.setSitesCount(((HDLmPassThruDivision) treeMod).getSitesCount());
					newHDLmPassThruDivision.setAssociatedNodeTypeNull(); 
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					if (tempTreeMod.getExtra() == null)
			      tempTreeMod.setExtra(""); 
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					/* LOG.info(infoStr); */
					break;
		    }
		    case IGNORE: {
					HDLmPassThruIgnore  newHDLmPassThruIgnore = new HDLmPassThruIgnore();
					tempTreeMod = newHDLmPassThruIgnore;
					Instant   savedCreated = ((HDLmPassThruIgnore) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruIgnore) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */	 
					newHDLmPassThruIgnore.setDummyCreated(savedCreated.toString());
					newHDLmPassThruIgnore.setDummyLastModified(savedLastModified.toString());		
					tempTreeMod = newHDLmPassThruIgnore;
					newHDLmPassThruIgnore.setAssociatedNodeTypeNull(); 
					newHDLmPassThruIgnore.setMatchCacheNull(); 
					newHDLmPassThruIgnore.setCreatedFromVerificationCheck(((HDLmPassThruIgnore) treeMod).getCreatedFromVerificationCheck());
					newHDLmPassThruIgnore.setDescription(((HDLmPassThruIgnore) treeMod).getDescription());
					newHDLmPassThruIgnore.setDetailsOne(((HDLmPassThruIgnore) treeMod).getDetailsOne());
					newHDLmPassThruIgnore.setDetailsTwo(((HDLmPassThruIgnore) treeMod).getDetailsTwo());
					newHDLmPassThruIgnore.setDetailsThree(((HDLmPassThruIgnore) treeMod).getDetailsThree());
					newHDLmPassThruIgnore.setLanguage(((HDLmPassThruIgnore) treeMod).getLanguage());
					newHDLmPassThruIgnore.setScriptId(((HDLmPassThruIgnore) treeMod).getScriptId());
					newHDLmPassThruIgnore.setStepNumber(((HDLmPassThruIgnore) treeMod).getStepNumber());
					newHDLmPassThruIgnore.setTestCase(((HDLmPassThruIgnore) treeMod).getTestCase());
					newHDLmPassThruIgnore.setTestResults(((HDLmPassThruIgnore) treeMod).getTestResults());
					newHDLmPassThruIgnore.setTicketPackage(((HDLmPassThruIgnore) treeMod).getTicketPackage());
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					if (tempTreeMod.getExtra() == null)
			      tempTreeMod.setExtra(""); 
					if (tempTreeMod.getComments() == null)
						tempTreeMod.setComments("");
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case LINE: {
					HDLmPassThruLine  newHDLmPassThruLine = new HDLmPassThruLine();
					tempTreeMod = newHDLmPassThruLine;
					Instant   savedCreated = ((HDLmPassThruLine) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruLine) treeMod).getLastModified();
					Instant   savedCreatedFromVerificationCheck = ((HDLmPassThruLine) treeMod).getCreatedFromVerificationCheck();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */ 
					newHDLmPassThruLine.setDummyCreated(savedCreated.toString());
					newHDLmPassThruLine.setDummyLastModified(savedLastModified.toString());	
					if (savedCreatedFromVerificationCheck != null)
					  newHDLmPassThruLine.setDummyCreatedFromVerificationCheck(savedCreatedFromVerificationCheck.toString());
					/* LOG.info(savedCreatedFromVerificationCheck.toString()); */
					newHDLmPassThruLine.setAssociatedNodeTypeNull(); 
					newHDLmPassThruLine.setDescription(((HDLmPassThruLine) treeMod).getDescription());
					newHDLmPassThruLine.setDetailsOne(((HDLmPassThruLine) treeMod).getDetailsOne());
					newHDLmPassThruLine.setDetailsTwo(((HDLmPassThruLine) treeMod).getDetailsTwo());
					newHDLmPassThruLine.setDetailsThree(((HDLmPassThruLine) treeMod).getDetailsThree());
					newHDLmPassThruLine.setLanguage(((HDLmPassThruLine) treeMod).getLanguage());
					newHDLmPassThruLine.setScriptId(((HDLmPassThruLine) treeMod).getScriptId());
					newHDLmPassThruLine.setStepNumber(((HDLmPassThruLine) treeMod).getStepNumber());
					newHDLmPassThruLine.setTestCase(((HDLmPassThruLine) treeMod).getTestCase());
					newHDLmPassThruLine.setTestResults(((HDLmPassThruLine) treeMod).getTestResults());
					newHDLmPassThruLine.setTicketPackage(((HDLmPassThruLine) treeMod).getTicketPackage());
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					newHDLmPassThruLine.setCreatedFromVerificationCheckNull();
					if (tempTreeMod.getExtra() == null)
			      tempTreeMod.setExtra(""); 
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					infoStr = infoStr.replace("\"dummyCreatedFromVerificationCheck\":", "\"createdFromVerificationCheck\":");
					/* LOG.info(infoStr); */
					break;
		    }
		    case LINES: {
					HDLmPassThruLines   newHDLmPassThruLines = new HDLmPassThruLines();
					tempTreeMod = newHDLmPassThruLines;
					Instant   savedCreated = ((HDLmPassThruLines) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruLines) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */ 
					newHDLmPassThruLines.setDummyCreated(savedCreated.toString());
					newHDLmPassThruLines.setDummyLastModified(savedLastModified.toString());		
					tempTreeMod = newHDLmPassThruLines;
					newHDLmPassThruLines.setLinesCount(((HDLmPassThruLines) treeMod).getLinesCount());
					newHDLmPassThruLines.setLinesNull(); 
					newHDLmPassThruLines.setAssociatedNodeTypeNull();  
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					if (tempTreeMod.getExtra() == null)
			      tempTreeMod.setExtra(""); 
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setDummyTable("dummyTable");
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case LIST: {
					HDLmPassThruList  newHDLmPassThruList = new HDLmPassThruList();
					tempTreeMod = newHDLmPassThruList;
					Instant   savedCreated = ((HDLmPassThruList) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruList) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */ 
					newHDLmPassThruList.setDummyCreated(savedCreated.toString());
					newHDLmPassThruList.setDummyLastModified(savedLastModified.toString());		
					tempTreeMod = newHDLmPassThruList;
					newHDLmPassThruList.setIgnoresCount(((HDLmPassThruList) treeMod).getIgnoresCount());
					newHDLmPassThruList.setAssociatedNodeTypeNull(); 
					newHDLmPassThruList.setIgnoreEntriesNull();  
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					if (tempTreeMod.getExtra() == null)
			      tempTreeMod.setExtra(""); 
					if (tempTreeMod.getComments() == null)
						tempTreeMod.setComments("");
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case LISTS: {
					HDLmPassThruLists   newHDLmPassThruLists = new HDLmPassThruLists();
					tempTreeMod = newHDLmPassThruLists;
					Instant   savedCreated = ((HDLmPassThruLists) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruLists) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */ 
					newHDLmPassThruLists.setDummyCreated(savedCreated.toString());
					newHDLmPassThruLists.setDummyLastModified(savedLastModified.toString());	
					newHDLmPassThruLists.setListsCount(((HDLmPassThruLists) treeMod).getListsCount());
					newHDLmPassThruLists.setAssociatedNodeTypeNull(); 
					newHDLmPassThruLists.setIgnoreListsNull(); 
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setEnabled((Boolean) null);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case MOD: { 
					tempTreeMod = new HDLmMod();
					Instant   savedCreated = ((HDLmMod) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmMod) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;		
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
		      /* Set a few fields */
					tempTreeMod.setDummyCreated(savedCreated.toString());
					tempTreeMod.setDummyLastModified(savedLastModified.toString());		
					/* Create a temporary node identifier */
					HDLmNodeIden  treeModNodeIden = treeMod.getNodeIden();
					if (treeModNodeIden != null) {
					  HDLmNodeIden  tempTreeModNodeIden = new HDLmNodeIden(treeModNodeIden);
					  tempTreeModNodeIden.setNodeEnabledNull();
					  tempTreeMod.setNodeIden(tempTreeModNodeIden);
					}
					if (tempTreeMod.getFinds() == null) {
						tempTreeMod.setFinds(HDLmFind.emptyFindsInstance);
					}
					if (tempTreeMod.getExtra() == null)
			      tempTreeMod.setExtra("");  
					if (tempTreeMod.getXPath() == null)
						tempTreeMod.setXPath("");
					if (tempTreeMod.getComments() == null)
						tempTreeMod.setComments("");
					if (tempTreeMod.getCssSelector() == null)
						tempTreeMod.setCssSelector("");
					if (tempTreeMod.getNodeIden() == null)
						tempTreeMod.setNodeIden(HDLmNodeIden.getEmptyNodeIden());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					String  newTypeStr = tempTreeMod.getType().toString().toLowerCase();
					String  newValuesName = HDLmMod.getModificationTypeValuesName(newTypeStr);
					infoStr = infoStr.replace("\"values\":", "\"" + newValuesName + "\":"); 
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case REPORT: {
					HDLmPassThruReport   newHDLmPassThruReport = new HDLmPassThruReport();
					tempTreeMod = newHDLmPassThruReport;
					Instant   savedCreated = ((HDLmPassThruReport) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruReport) treeMod).getLastModified();
					/* Set the saved last modified value if need be ) */
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */			
					newHDLmPassThruReport.setDummyCreated(savedCreated.toString());
					newHDLmPassThruReport.setDummyLastModified(savedLastModified.toString());		
					HDLmReportTypes  reportType = ((HDLmPassThruReport) treeMod).getReportType();
					if (reportType != null)
						newHDLmPassThruReport.setReportType(reportType);
					newHDLmPassThruReport.setStatusNull(); 
					newHDLmPassThruReport.setAssociatedNodeTypeNull(); 
					tempTreeMod = newHDLmPassThruReport;
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setExtra(""); 
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyPassThru((Boolean) false);
					tempTreeMod.setDummyTable("dummyTable");
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case REPORTS: {
					HDLmPassThruReports   newHDLmPassThruReports = new HDLmPassThruReports();
					tempTreeMod = newHDLmPassThruReports;
					Instant   savedCreated = ((HDLmPassThruReports) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruReports) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */
					newHDLmPassThruReports.setDummyCreated(savedCreated.toString());
					newHDLmPassThruReports.setDummyLastModified(savedLastModified.toString());	
					newHDLmPassThruReports.setReportsCount(((HDLmPassThruReports) treeMod).getReportsCount());
					newHDLmPassThruReports.setReportsNull(); 
					newHDLmPassThruReports.setAssociatedNodeTypeNull(); 
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setExtra(""); 
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case RULES: {
					HDLmPassThruRules   newHDLmPassThruRules = new HDLmPassThruRules();
					tempTreeMod = newHDLmPassThruRules;
					Instant   savedCreated = ((HDLmPassThruRules) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruRules) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */
					newHDLmPassThruRules.setDummyCreated(savedCreated.toString());
					newHDLmPassThruRules.setDummyLastModified(savedLastModified.toString());	
					newHDLmPassThruRules.setDivisionsCount(((HDLmPassThruRules) treeMod).getDivisionsCount());
					newHDLmPassThruRules.setAssociatedNodeTypeNull(); 
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setExtra(""); 
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case SITE: {
					HDLmPassThruSite  newHDLmPassThruSite = new HDLmPassThruSite();
					tempTreeMod = newHDLmPassThruSite;
					Instant   savedCreated = ((HDLmPassThruSite) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruSite) treeMod).getLastModified(); 
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */ 
					newHDLmPassThruSite.setDummyCreated(savedCreated.toString());
					newHDLmPassThruSite.setDummyLastModified(savedLastModified.toString());			
					newHDLmPassThruSite.setRulesOrValuesCount(((HDLmPassThruSite) treeMod).getRulesOrValuesCount());
					newHDLmPassThruSite.setAssociatedNodeTypeNull(); 
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					if (tempTreeMod.getExtra() == null)
			      tempTreeMod.setExtra(""); 
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					/* LOG.info(infoStr); */
					break;
		    }
		    case TOP: {
					HDLmPassThruTop   newHDLmPassThruTop = new HDLmPassThruTop();
					tempTreeMod = newHDLmPassThruTop;
					Instant   savedCreated = ((HDLmPassThruTop) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruTop) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */
					newHDLmPassThruTop.setDummyCreated(savedCreated.toString());
					newHDLmPassThruTop.setDummyLastModified(savedLastModified.toString());		
					newHDLmPassThruTop.setStatusNull(); 
					newHDLmPassThruTop.setAssociatedNodeTypeNull(); 		 
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setExtra("");
					tempTreeMod.setDummyPassThru((Boolean) false);
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setDummyUpdated((Boolean) false);
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
		    case VALUE: {
					HDLmPassThruValue   newHDLmPassThruValue = new HDLmPassThruValue();
					tempTreeMod = newHDLmPassThruValue;
					Instant   savedCreated = ((HDLmPassThruValue) treeMod).getCreated();
					Instant   savedLastModified = ((HDLmPassThruValue) treeMod).getLastModified();
					if (savedLastModified == null &&
					    savedCreated != null) 
						savedLastModified = savedCreated;					
		      /* Set a few fields */
					newHDLmPassThruValue.setDummyCreated(savedCreated.toString());
					newHDLmPassThruValue.setDummyLastModified(savedLastModified.toString());	
					newHDLmPassThruValue.setValue(((HDLmPassThruValue) treeMod).getValue());
					/* Finish the temporary object */
					tempTreeMod.copyModFields(treeMod);
					tempTreeMod.setCreatedNull();
					tempTreeMod.setLastModifiedNull();
					tempTreeMod.setDummyType(tempTreePos.getType().toString().toLowerCase());
					tempTreeMod.setPathValueRe((Boolean) null);
					tempTreeMod.setType((HDLmModTypes) null);
					tempTreePos.setDetails(tempTreeMod);
					/* Convert the temporary object into a string */
					Gson    gson = HDLmMain.gsonMain;
					infoStr = gson.toJson(tempTreePos);
					infoStr = infoStr.replace("\"dummyType\":", "\"type\":");
					infoStr = infoStr.replace("\"dummyCreated\":", "\"created\":");
					infoStr = infoStr.replace("\"dummyLastModified\":", "\"lastmodified\":");
					break;
		    }
			  /* Handle the default case. Of course, the default case should
		       never occur. However, you never know. */ 
		    default: {
		  	  String  newTypeString = treePos.getType().toString();
				  String  errorText = String.format("Type of value (%s) is not supported", newTypeString);
				  HDLmAssertAction(false, errorText);			  	
		    } 
		  }			
		}
		/* It appears that we don't have any details */
		else {
			/* Convert the temporary object into a string */
			Gson    gson = HDLmMain.gsonMain;
			infoStr = gson.toJson(tempTreePos);
		}
	  return infoStr;		
	}	
	/* The method below takes a tree node and returns one JSON element. The tree node
	   can be anywhere in the tree structure. The JSON element may have any number
	   of elements inside it. Basically this call takes a node tree and converts it
	   into a set of JSON elements. Note that modifications are also converted to
	   JSON elements as need be.
	   
	   This routine does not appear to be in use. The only routine that invokes this
	   routine does not appear to be in use. 
	   
	   Tree was added to the method name to help distinguish between different
	   routines that used to have the same name. */
	protected static JsonElement getJsonTreeTree(final String contentType, 
			                                         final HDLmTree nodeTree) {
		/* Check if the content type reference passed by the caller is null */
		if (contentType == null) {
			String  errorText = "Content type reference passed to getJsonTreeTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the node tree value passed by the caller is null */
		if (nodeTree == null) {
			String  errorText = "Node tree value passed to getJsonTreeTree is null";
			throw new NullPointerException(errorText);
		}
		/* Convert the node tree to JSON */
		Gson gson = HDLmMain.gsonMain;
		JsonElement jsonElement = gson.toJsonTree(nodeTree);
		/* Check if we obtained an actual hierarchy of JSON elements */
		if (jsonElement == null)
			HDLmAssertAction(false, "JSON element not returned from gson.toJsonTree call");
		/* Process the entire tree of JSON elements */
		HDLmTree.processJsonTree(contentType, jsonElement);
		return jsonElement;
	}
	/* Get the last node path entry for the current HDLmTree instance */ 
	protected String getLastNodePathEntry() {
		/* Check a few values */
		if (nodePath == null) {
			String  errorText = "Node path field is null in getLastNodePathEntry";
			throw new NullPointerException(errorText);
		}
		/* Get and check the node path length */
		int nodePathLength = nodePath.size();
		if (nodePathLength < 1) {
			HDLmAssertAction(false, "Node path length is less than one in getLastNodePathEntry");
		}
		return nodePath.get(nodePathLength - 1);
	}
	/* This routine scans the input array and returns all of the matching entries in
	   the output array. For an entry to match, the entry must have the correct
	   level number and the node array passed by the caller must match the node
	   array of the entry. The node array passed by the caller is compared to the
	   node array of each entry by comparing each entry in both arrays. */
	protected static ArrayList<JsonObject> getMatchingEntries(final ArrayList<JsonObject> inArray, 
			                                                      final int matchLevel,
			                                                      final JsonArray jsonNodeArray) {
		/* Check the values passed by the caller */
		if (inArray == null) {
			String  errorText = "Array list of JSON objects is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON object array passed by the caller is empty */
		if (inArray.size() <= 0) {
			String  errorFormat = "Size of array list of JSON objects is invalid";
			String  errorText = String.format(errorFormat);
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the match level value passed by the caller is valid */
		if (matchLevel <= 0) {
			String  errorFormat = "Integer value (%d) for level passed to getMatchingEntries is invalid";
			String  errorText = String.format(errorFormat, matchLevel);
			throw new IllegalArgumentException(errorText);
		}
		/* Check the JSON node array array passed by the caller */
		if (jsonNodeArray == null) {
			String  errorText = "JSON nodes array passed by the caller is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON node array size passed by the caller is invalid */
		if (jsonNodeArray.size() < 0) {
			String  errorFormat = "Size of JSON nodes array passed by the caller is invalid";
			String  errorText = String.format(errorFormat);
			throw new IllegalArgumentException(errorText);
		}
		/* Declare and define a few values */
		int inLen = inArray.size();
		int nodesLen = jsonNodeArray.size();
		ArrayList<JsonObject> outArray = new ArrayList<JsonObject>();
		/* Build an array of strings from JSON node array passed by the caller */
		String[] stringNodeArray = new String[nodesLen];
		for (int i = 0; i < nodesLen; i++) {
			JsonElement jsonArrayElement = jsonNodeArray.get(i);
			if (jsonArrayElement == null) {
				String errorFormat = "JSON array element (%d) is null";
				String  errorText = String.format(errorFormat, i);
				throw new NullPointerException(errorText);
			}
			if (!jsonArrayElement.isJsonPrimitive()) {
				String errorFormat = "JSON array element (%d) is not a JSON primitive";
				String  errorText = String.format(errorFormat, i);
				throw new NullPointerException(errorText);
			}
			stringNodeArray[i] = jsonArrayElement.getAsString();
		}
		/* Scan the input array looking for matches */
		for (int i = 0; i < inLen; i++) {
			/* Get the current array entry */
			JsonObject curEntry = inArray.get(i);
			/* Check if the array entry is at the correct level */
			JsonObject curInfoJsonObject = HDLmJson.getJsonObject(curEntry, "info");
			if (curInfoJsonObject == null) {
				String  errorText = String.format("Info object not obtained from current entry object");
				HDLmAssertAction(false, errorText);
			}
			JsonArray curInfoNodePath = HDLmJson.getJsonArray(curInfoJsonObject, "nodePath");
			if (curInfoNodePath == null) {
				String  errorText = String.format("Node path array not obtained from current info object");
				HDLmAssertAction(false, errorText);
			}
			Integer curInfoNodePathLength = curInfoNodePath.size();
			if (curInfoNodePathLength == null) {
				String  errorText = String.format("Length value not obtained for current node path array");
				HDLmAssertAction(false, errorText);
			}
			if (matchLevel != curInfoNodePathLength)
				continue;
			/* Make sure that the node arrays match */
			boolean nodeMismatch = false;
			for (int j = 0; j < nodesLen; j++) {
				String nodePathString = curInfoNodePath.get(j).getAsString();
				if (!stringNodeArray[j].equals(nodePathString)) {
					nodeMismatch = true;
					break;
				}
			}
			/* Check if we found a node array mismatch */
			if (nodeMismatch)
				continue;
			/* At this point, we have an array entry that passed all of the tests */
			outArray.add(curEntry);
		}
		return outArray;
	}
	/* Search an array of nodes (possibly an empty array) for the first node with a
	   name greater than or equal to the name passed by the caller. Return the index
	   of the first node with a name that is greater than or equal to the name
	   passed by the caller. If no such node is found, return a minus one value to
	   the caller. This method appears to be only used by the add node routine which
	   is apparently no longer in use. */
	protected static int getNodeIndexGE(final ArrayList<HDLmTree> nodeArray, 
			                                final String searchName) {
		int subPos = 0;
		if (nodeArray == null) {
			String  errorText = "Node array value is null";
			throw new NullPointerException(errorText);
		}
		if (!(nodeArray instanceof ArrayList<?>)) {
			HDLmAssertAction(false, "Node array value has incorrect type");
		}
		if (searchName == null) {
			String  errorText = "Name value is null";
			throw new NullPointerException(errorText);
		}
		if (!(searchName instanceof String)) {
			HDLmAssertAction(false, "Name value has incorrect type");
		}
		/* Search the node array passed by the caller for the first node with a name
		   greater than or equal to the caller. This seach may not may not succeed. */
		for (HDLmTree node : nodeArray) {
			String  nodeName = node.getLastNodePathEntry();
			if (nodeName.compareTo(searchName) >= 0)
				break;
			subPos++;
		}
		/* If the desired node was never found, just return a minus one value to the
		   caller */
		if (subPos >= nodeArray.size())
			subPos = -1;
		return subPos;
	}
	/* Get the top tree node and return it to the caller */
	protected static HDLmTree  getNodeModTreeTop() {
		return HDLmTree.nodeModTreeTop;
	}
  /* Get the top tree node and return it to the caller */
	protected static HDLmTree  getNodePassTreeTop() {
		return HDLmTree.nodePassTreeTop;
	}
	/* Get the node path from an HDLmTree element */
	protected ArrayList<String>  getNodePath() {
		return nodePath;
	}
	/* Get the node path for the current node */ 
	protected static ArrayList<String>  getNodePath(final HDLmTree topTreeNode, 
			                                            final String hostName,
			                                            final HDLmTreeTypes nodeType) {
		/* Check if the top tree node passed by the caller is null */
		if (topTreeNode == null) {
			String  errorText = "Top tree node value passed to getNodePath is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name value passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name value passed to getNodePath is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the node type value passed by the caller is null */
		if (nodeType == null) {
			String  errorText = "Node type value passed to getNodePath is null";
			throw new NullPointerException(errorText);
		}
		/* Build the node path */  
		ArrayList<String>   nodePath = new ArrayList<String>();
		HDLmTree  nodeParent = null;
		int       nodeLevels = 6;
		for (int i = 0; i < nodeLevels; i++) {
			String  nodeName = null;
			/* Create an enum for the node type */
			HDLmTreeTypes   nodeEnum = null;
			/* Set the correct node enum value based on the loop index */
			if ((i+1) == 1)
				nodeEnum = HDLmTreeTypes.TOP;
			else if ((i+1) == 2)
				nodeEnum = HDLmTreeTypes.COMPANIES;
			else if ((i+1) == 3)
				nodeEnum = HDLmTreeTypes.COMPANY;
			else if ((i+1) == 4) {
				if (nodeType == HDLmTreeTypes.DATA)
				  nodeEnum = HDLmTreeTypes.DATA;
				if (nodeType == HDLmTreeTypes.MOD)
				  nodeEnum = HDLmTreeTypes.RULES;
				if (nodeType == HDLmTreeTypes.RULES)
				  nodeEnum = HDLmTreeTypes.RULES;
				if (nodeType == HDLmTreeTypes.VALUE)
				  nodeEnum = HDLmTreeTypes.DATA;
			}
			else if ((i+1) == 5)
				nodeEnum = HDLmTreeTypes.DIVISION;
			else if ((i+1) == 6)
				nodeEnum = HDLmTreeTypes.SITE;
			else if ((i+1) == 7) {
				if (nodeType == HDLmTreeTypes.DATA)
				  nodeEnum = HDLmTreeTypes.VALUE;
				if (nodeType == HDLmTreeTypes.RULES)
				  nodeEnum = HDLmTreeTypes.MOD;
			}
			/* Determine what we need to add to the node path */
			switch (nodeEnum) {
			  case TOP: {
			  	nodeName = HDLmDefines.getString("HDLMTOPNODENAME");
			  	break;
			  }
			  case COMPANIES: {
			  	nodeName = HDLmDefines.getString("HDLMCOMPANIESNODENAME");
			  	break;
			  }
			  case COMPANY: {
			  	nodeName = hostName;
			  	break;
			  }
			  case DATA: {
			  	nodeName = HDLmDefines.getString("HDLMDATANODENAME");
			  	break;
			  }
			  case RULES: {
			  	nodeName = HDLmDefines.getString("HDLMRULESNODENAME");
			  	break;
			  }
			  case DIVISION: {
			  	nodeName = HDLmDefines.getString("HDLMDIVISIONNODENAME");
			  	break;
			  }
			  case SITE: {
			  	nodeName = HDLmDefines.getString("HDLMSITENODENAME");
			  	break;
			  }
			  case MOD: {
			  	nodeName = null;
			  	break;
			  }
			  case VALUE: {
			  	nodeName = null;
			  	break;
			  }
			  /* Report an error if none of the cases matched. At least
			     one of the cases should have matched. The error text
			     actually shows the loop index value (plus one) that
			     failed. This is likely to be more informative than 
			     actual enum that did not match. */
			  default: {
		    	String  errorFormat = "No tree type enum value (%d) matched in getNodePath";
					String  errorText = String.format(errorFormat, i+1);
					HDLmAssertAction(false, errorText);			  	
			  }
			}
			/* Update the node path with the new name value */
			nodePath.add(nodeName);					
		}    
		return nodePath;
	}		
	/* Get the node path from the information string and return the node path
	   to the caller. A null value is returned to the caller, if the node path
	   can not be extracted. */ 
	protected static ArrayList<String>  getNodePath(final String infoStr) {
		/* Check a few values passed by the caller */
		if (infoStr == null) {
			String  errorText = "Information string is null in getNodePath";
			throw new NullPointerException(errorText);
		}
		/* Try to convert the information JSON string to a JSON object. If this fails,
	     then we do not have a string than can be converted to a JSON object. If this
	     works, then we do have string than can be converted to a JSON object. */
	  JsonParser    parser = new JsonParser();		
    JsonElement   infoJsonElement = null; 
	  try {
		  infoJsonElement = parser.parse(infoStr);
		}
		catch (Exception exception) {
		  if (infoStr != null)
		    LOG.info("Information string - " + infoStr);
			LOG.info("Exception while executing parse of information string");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return null;
		}	 
	  /* Get and check the node path JSON */
		JsonArray   nodePathJson = HDLmJson.getJsonArray(infoJsonElement, "nodePath");
		if (nodePathJson == null) {
		  String  errorText = "JSON array in getNodePath is invalid";
		  HDLmAssertAction(false, errorText);
	  }
	  /* Check if the JSON array is valid or not */
		if (!nodePathJson.isJsonArray()) {
		  String  errorText = "JSON array in getNodePath is invalid";
		  HDLmAssertAction(false, errorText);
	  }
		int nodePathJsonSize = nodePathJson.size();
		/* Convert the node path JSON to an array list. First, 
		   get the maximum node path length */
		int   maxPathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		/* Check if the node path length is too long */ 
		if  (nodePathJsonSize > maxPathLength) {
			String errorFormat = "info node path length (%d) is too long";
			String  errorText = String.format(errorFormat, nodePathJsonSize);
			HDLmAssertAction(false, errorText);
		}
		/* Check if the node path length is too short */ 
		if  (nodePathJsonSize < 1) {
			String errorFormat = "Info node path length (%d) is too short";
			String  errorText = String.format(errorFormat, nodePathJsonSize);
			HDLmAssertAction(false, errorText);
		}
		/* Convert the JSON array with the node path to an ArrayList with the node path */
		ArrayList<String>   infoNodePath = new ArrayList<String>();
		if (infoNodePath == null) {
			String  errorText = "Node path ArrayList allocation in getNodePath is null";
			throw new NullPointerException(errorText);
		}
		/* Loop over and process each element of the JSON array */
		for (JsonElement arrayEntry : nodePathJson) {
			String  nodePathEntry = arrayEntry.getAsString();
			infoNodePath.add(nodePathEntry);
		}
		/* Return the final node path array to the caller */
		return infoNodePath;
	}
	/* Get the node path length from an HDLmTree element */
	protected int          getNodePathLength() {
		return nodePath.size();
	}
	/* Get the JSON object value of a JSON element, if possible. If the JSON object
	   value can not be obtained, a null value will be returned to the caller. No
	   error messages will be issued. */
	protected static JsonObject getObjectFromJson(final JsonObject jsonObject, 
			                                          final String memberName) {
		JsonObject  localJsonObject = null;
		/* Check a few values passed by the caller */
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		if (memberName == null) {
			String  errorText = "JSON member name value is null";
			throw new NullPointerException(errorText);
		}
		if (!(memberName instanceof String)) {
			HDLmAssertAction(false, "JSON member name value has incorrect type");
		}
		/* Check if the JSON object really has the member name passed by the caller */
		if (!jsonObject.has(memberName)) {
			return localJsonObject;
		}
		/* Get the JSON element and obtain the value */
		JsonElement jsonElement = jsonObject.get(memberName);
		/* We need to make sure that the JSON element is a JSON object */
		if (!jsonElement.isJsonObject()) {
			return localJsonObject;
		}
		localJsonObject = jsonElement.getAsJsonObject();
		return localJsonObject;
	}
	/* Get a fresh copy of the node tree from the database. Set or reset the tree
	   top using the fresh copy. */
	static HDLmTree getPassThruFreshTreeSetTop() {
		/* Get a fresh copy of all of the modifications. The new rule is added to a
		   fresh copy of the modifications. */
		HDLmTree  topTreeNode = HDLmMain.buildNodeTreeMain(null, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO); 
		if (topTreeNode == null) {
			HDLmAssertAction(false, "Null modifications tree returned by buildNodeTreeMain");
		}
		/* Store the reference to the new top tree. The new top tree was built using the
		   latest set of nodes. We might as well use the latest set of nodes. */
		HDLmTree.setNodePassTreeTop(topTreeNode);
		return topTreeNode;
	}
	/* Get the string value of a JSON element, if possible. If the string value can
	   not be obtained, a null value will be returned to the caller. No error
	   messages will be issued. */
	protected static String getStringFromJson(final JsonObject jsonObject, 
			                                      final String memberName) {
		String localString = null;
		if (jsonObject == null) {
			String  errorText = "JSON object value is null";
			throw new NullPointerException(errorText);
		}
		if (!(jsonObject instanceof JsonObject)) {
			HDLmAssertAction(false, "JSON object value has incorrect type");
		}
		if (memberName == null) {
			String  errorText = "JSON member name value is null";
			throw new NullPointerException(errorText);
		}
		if (!(memberName instanceof String)) {
			HDLmAssertAction(false, "JSON member name value has incorrect type");
		}
		/* Check if the JSON object really has the member name passed by the caller */
		if (!jsonObject.has(memberName)) {
			return localString;
		}
		/* Get the JSON element and obtain the value */
		JsonElement jsonElement = jsonObject.get(memberName);
		/* We need to make sure that the JSON element is a primitive value */
		if (!jsonElement.isJsonPrimitive()) {
			return localString;
		}
		localString = jsonElement.getAsString();
		return localString;
	}
	/* Get the tooltip from an HDLmTree element */
	protected String getTooltip() {
		return tooltip;
	}
	/* Get the Tooltip value for the current node. This code is in a separate
	   routine so that it can be invoked from several places. This code only works
	   for leaf nodes. Leaf nodes are modification nodes in this case. */
	protected static String getTooltipString(final String type) {
		/* Check if the node type value passed by the caller is null */
		if (type == null) {
			String  errorText = "Node type value passed to getTooltipString is null";
			throw new NullPointerException(errorText);
		}
		JsonElement typeJson;
		String tooltip = null;
		/* Get the tree information JSON element */
		JsonElement  treeInfoJson = HDLmMod.getTreeInfo();
		if (treeInfoJson == null) {
			String  errorText = "Tree information JSON not obtained from getTreeInfo";
			HDLmAssertAction(false, errorText);
		}
		/* Get the modification information JSON element */
		JsonElement modInfoJson = HDLmMod.getModInfo();
		if (modInfoJson == null) {
			String  errorText = "Modification information JSON not obtained from getModInfo";
			HDLmAssertAction(false, errorText);
		}
		if (type.equals("newcompmod")) {
			typeJson = HDLmJson.getJsonValue(treeInfoJson, "compmod");
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else if (type.equals("newcompproxy")) {
			typeJson = HDLmJson.getJsonValue(treeInfoJson, "compproxy");
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else if (type.equals("newcompstore")) {
			typeJson = HDLmJson.getJsonValue(treeInfoJson, "compstore");
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else if (type.equals("newconfig")) {
			typeJson = HDLmJson.getJsonValue(treeInfoJson, "config");
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else if (type.equals("newtop")) {
			String typeKey = HDLmDefines.getString("HDLMTOPNODETYPE");
			typeJson = HDLmJson.getJsonValue(treeInfoJson, typeKey);
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else if (type.equals("newSite")) {
			typeJson = HDLmJson.getJsonValue(treeInfoJson, "division");
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else if (type.equals("newsite")) {
			typeJson = HDLmJson.getJsonValue(treeInfoJson, "site");
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else if (type.equals("newmod")) {
			typeJson = HDLmJson.getJsonValue(treeInfoJson, "mod");
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else if (type.equals("newstore")) {
			typeJson = HDLmJson.getJsonValue(treeInfoJson, "store");
			tooltip = HDLmJson.getJsonString(typeJson, "tooltip");
		} else {
			/* Build the tooltip information */
			typeJson = HDLmJson.getJsonValue(modInfoJson, type);
			if (typeJson != null)
				tooltip = HDLmJson.getJsonString(typeJson, "longname");
			else
				tooltip = type;
			tooltip = HDLmString.ucFirst(tooltip) + " " + "modification";
		}
		return tooltip;
	}
  /* Get the type from an HDLmTree element */
	protected HDLmTreeTypes getType() {
		return type;
	}
	/* Check if an array of nodes (possibly an empty array) has a node with the name
	   passed by the caller. If a node with the same name is found, the node is
	   returned to the caller. Otherwise, a null value is returned to the caller. */
	protected static HDLmTree hasNode(final ArrayList<HDLmTree> nodeArray, 
			                              final String searchName) {
		HDLmTree node = null;
		if (nodeArray == null) {
			String  errorText = "Node array value is null";
			throw new NullPointerException(errorText);
		}
		if (!(nodeArray instanceof ArrayList<?>)) {
			HDLmAssertAction(false, "Node array value has incorrect type");
		}
		if (searchName == null) {
			String  errorText = "Name value is null";
			throw new NullPointerException(errorText);
		}
		if (!(searchName instanceof String)) {
			HDLmAssertAction(false, "Name value has incorrect type");
		}
		for (HDLmTree forNode : nodeArray) {
			if (forNode.getLastNodePathEntry().equals(searchName)) {
				node = forNode;
				break;
			}
		}
		return node;
	}
	/* Locate (find) a node using a node path. A node path is all of the names that
	   lead a node. The first entry in the node path is for the top node. This is
	   required so that this function can find the top node. This routine will
	   return the target node if it is found. If the target node can not be found,
	   this routine will return a null value. */
	protected static HDLmTree locateTreeNode(final HDLmTree topTreeNode, 
			                                     final ArrayList<String> nodePath) {
		if (topTreeNode == null) {
			String  errorText = "Top tree node value is null";
			throw new NullPointerException(errorText);
		}
		if (nodePath == null) {
			String  errorText = "Node path value is null";
			throw new NullPointerException(errorText);
		}
		if (!(nodePath instanceof ArrayList<?>)) {
			HDLmAssertAction(false, "Node path value has incorrect type");
		}
		HDLmTree currentNode = null;
		int nodePathLength = nodePath.size();
		for (int i = 0; i < nodePathLength; i++) {
			String currentName = nodePath.get(i);
			/* Check if we are handling level 1. Level 1 is always the top-level node and
			   does not have to be found in the same way. */
			if (i == 0) {
				currentNode = topTreeNode;
				/* fail if top is null */
				continue;
			}
			/* We are not handling level 1. Use the current node to get the array of child
			   nodes. Search the array of child nodes. */
			currentNode = HDLmTree.hasNode(currentNode.children, currentName);
			if (currentNode == null)
				return null;
		}
		return currentNode;
	}
	/* This routine goes through the node tree and adds all of the child objects to
	   the details for each parent. In other words this routine fills in all of the
	   details. */
	protected void         modifyPassThruTree(final HDLmStartupMode startupMode) {						
		/* Check a few values passed by the caller */
		/* Check if the startup mode value passed by the caller is null */
		if (startupMode == null) {
			String   errorText = "Startup mode value passed to modifyPassThruTree is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the startup mode passed by the caller is invalid */
		if (startupMode == HDLmStartupMode.NONE) {
		  HDLmAssertAction(false, "Startup mode value passed to modifyPassThruTree is invalid");
		}
		/* Check if we are handling the companies node. All of the individual companies
		   must be added to the companies node. */
		if (type == HDLmTreeTypes.COMPANIES) {
			HDLmPassThruCompanies companies = (HDLmPassThruCompanies) details;
			for (HDLmTree child : children) {
				HDLmPassThruCompany company = (HDLmPassThruCompany) child.details;
				boolean   updateLast = true;
				if (startupMode == HDLmStartupMode.STARTUPMODEYES)
					updateLast = false;
				companies.addCompanyCompanies(company.getName(), company, updateLast);
			}
		}
		/* Check if we are handling a company node. All of the children of a company
		   node must be added to each company. The children are actually quite fixed.
		   A company node always has four children. They are a set of data, a set of
		   ignore-lists and a set of reports and a set of rules. */
		if (type == HDLmTreeTypes.COMPANY) {
			HDLmPassThruCompany company = (HDLmPassThruCompany) details;
			int childrenSize = children.size();
			if (childrenSize != 3 && childrenSize != 4) {
				String errorFormat = "Number of children (%d) of a company node is not 3 or 4";
				String  errorText = String.format(errorFormat, childrenSize);
				HDLmAssertAction(false, errorText);
			}
			/* Set the adjustment factor. If the we only have three children
			   (of a company), then the data subnode is probably missing. This
			   is OK. Set the adjustment factor to one in this case. */
			int   adjustmentFactor = 0;
			if (childrenSize == 3)
				adjustmentFactor = 1;
			/* LOG.info(((Integer) adjustmentFactor).toString()); */
			/* LOG.info(((Integer) childrenSize).toString()); */
			/* The second child will be the set of ignore-lists */
			HDLmTree ignoreListsNode = children.get(1 - adjustmentFactor);
			company.setIgnoreLists((HDLmPassThruLists) ignoreListsNode.details);
			/* The third child will be the set of reports */
			HDLmTree reportsNode = children.get(2 - adjustmentFactor);
			company.setReports((HDLmPassThruReports) reportsNode.details);
			/* The fourth child will be the set of rules */
			HDLmTree rulesNode = children.get(3 - adjustmentFactor);
			company.setRules((HDLmPassThruRules) rulesNode.details);			
			/* Create the event and rule entries for the internally generated
			   'visit' event. The generated JavaScript program will always 
			   trigger this event when a web page loads the JavaScript 
			   program. 
			   
			   This code is no longer in use. We no longer create events for 
			   internally generated modification execution events. As a consequence,
			   this code is no longer needed and could cause problems (because the
			   number of events will always be zero). */
			/* 
			String  nameCompany = details.getName();
			String  nameDivision = HDLmDefines.getString("HDLMDIVISIONNODENAME");
			String  nameSite = HDLmDefines.getString("HDLMSITENODENAME");  
			String  nameModification = HDLmDefines.getString("HDLMLOADPAGEMODNAME"); 
			HDLmEvent.addEvent(HDLmEventTypes.MOD, nameCompany, nameDivision, nameSite, nameModification);
			HDLmRule.addRule(HDLmRuleTypes.MOD, nameCompany, nameDivision, nameSite, nameModification);
			*/			
		}
		/* Check if we are handling a data (set of divisions) node. All of the children
		   (individual divisions) of a data (set of divisions) node must be added to
		   each data node. */
		if (type == HDLmTreeTypes.DATA) {
			HDLmPassThruData  data = (HDLmPassThruData) details;
			for (HDLmTree child : children) {
				data.addDivision(child, startupMode);
			}
		}
		/* Check if we are handling a division (set of sites) node. All of the 
		   children (individual sites) of a division (set of sites) node must 
		   be added to each division node. */
		if (type == HDLmTreeTypes.DIVISION) {
			HDLmPassThruDivision  division = (HDLmPassThruDivision) details;
			for (HDLmTree child : children) {
				division.addSite(child, startupMode);
			}
		}
		/* Check if we are handling a lines (set of lines) node. All of the
		   children(individual lines) of a lines node must be added to each lines node. */
		if (type == HDLmTreeTypes.LINES) {
			HDLmPassThruLines lines = (HDLmPassThruLines) details;
			for (HDLmTree child : children) {
				HDLmPassThruLine line = (HDLmPassThruLine) child.details;
				lines.addLine(line);
			}
		}
		/* Check if we are handling a list (ignore-list) node. All of the children
		   (individual ignore-list entries) of a list node must be added to each list
		   node. */
		if (type == HDLmTreeTypes.LIST) {
			HDLmPassThruList ignoreList = (HDLmPassThruList) details;
			for (HDLmTree child : children) {
				HDLmPassThruIgnore ignoreListEntry = (HDLmPassThruIgnore) child.details;
				ignoreList.addIgnoreEntry(ignoreListEntry, startupMode);
			}
		}
		/* Check if we are handling a lists (ignore-lists) node. All of the children
		   (individual ignore-lists) of a lists node must be added to each lists node. */
		if (type == HDLmTreeTypes.LISTS) {
			HDLmPassThruLists ignoreLists = (HDLmPassThruLists) details;
			for (HDLmTree child : children) {
				HDLmPassThruList ignoreList = (HDLmPassThruList) child.details;
				String ignoreListName = ignoreList.getName();
				ignoreLists.addIgnoreList(ignoreListName, ignoreList, startupMode);
			}
		}
		/* Check if we are handling a modification. If this is correct, then we must add
		   an event for the current modification. */
		if (type == HDLmTreeTypes.MOD) {
			/* Get the node path for the current event */
			ArrayList<String> path = this.nodePath;
			if (path.size() != 7) {
				String errorFormat = "Node path length is not correct (%d)";
				String  errorText = String.format(errorFormat, path.size());
				HDLmAssertAction(false, errorText);
			}
			/* Get the values from the node path */
			String nameCompany = path.get(2);
			String nameDivision = path.get(4);
			String nameSite = path.get(5);
			String nameModification = path.get(6);
			HDLmEvent.addEvent(HDLmEventTypes.MOD, nameCompany, nameDivision, nameSite, nameModification);
			HDLmRule.addRule(HDLmRuleTypes.MOD, nameCompany, nameDivision, nameSite, nameModification);
		}
		/* Check if we are handling a report node. All of the children of a report node
		   must be added to each report. The children are actually quite fixed. A report
		   node always has four children. They are three sets of lines and one
		   ignore-list. The ignore-list is actually optional. This means that a report
		   node might only have three children. */
		if (type == HDLmTreeTypes.REPORT) {
			HDLmPassThruReport report = (HDLmPassThruReport) details;
			int childrenSize = children.size();
			if (childrenSize != 3 && childrenSize != 4) {
				String errorFormat = "Number of children (%d) of a report node is not 3 or 4";
				String  errorText = String.format(errorFormat, childrenSize);
				HDLmAssertAction(false, errorText);
			}
			HDLmTreeTypes curType;
			int curCounter = 0;
			/* Loop over the children, handling each child node as need be */
			for (HDLmTree child : children) {
				/* Get the type of the current child. It might be a lines node or it might be an
				   ignore-list node. */
				curType = child.getType();
				/* If we have a lines node, then it should be one of the three expected lines
				   nodes. */
				if (curType == HDLmTreeTypes.LINES) {
					curCounter++;
					if (curCounter == 1) {
						report.setInvalidRef((HDLmPassThruLines) child.details);
					} else if (curCounter == 2) {
						report.setOverallRef((HDLmPassThruLines) child.details);
					} else if (curCounter == 3) {
						report.setValidRef((HDLmPassThruLines) child.details);
					}
				} else if (curType == HDLmTreeTypes.LIST) {
					HDLmPassThruList ignoreList = (HDLmPassThruList) child.details;
					report.setIgnoreListRef(ignoreList);
				}
			}
		}
		/* Check if we are handling a reports (set of reports) node. All of the children
		   (individual reports) of a reports (set of reports) node must be added to each
		   reports node. */
		if (type == HDLmTreeTypes.REPORTS) {
			HDLmPassThruReports reports = (HDLmPassThruReports) details;
			for (HDLmTree child : children) {
				HDLmPassThruReport report = (HDLmPassThruReport) child.details;
				reports.addReport(report, startupMode);
			}
		}
		/* Check if we are handling a rules (set of divisions) node. All of the children
		   (individual divisions) of a rules (set of divisions) node must be added to
		   each rules node. */
		if (type == HDLmTreeTypes.RULES) {
			HDLmPassThruRules rules = (HDLmPassThruRules) details;
			for (HDLmTree child : children) {
				rules.addDivision(child, startupMode);
			}
		}
		/* Check if we are handling a site (set of rules) node. All of the 
		   children (individual rules) of a site (set of rules) node must 
		   be added to each site node. */
		if (type == HDLmTreeTypes.SITE) {
			HDLmPassThruSite  site = (HDLmPassThruSite) details;
			for (HDLmTree child : children) {
				site.addRule(child, startupMode);
			}
		}
		/* Check if we are handling the top node. All of the children of a top node must
		   be added to each top node. The children are actually quite fixed. A top node
		   always has either one or two children. A top node will always have a
		   companies child node. A top node may have a reports child node as well. */
		if (type == HDLmTreeTypes.TOP) {
			HDLmPassThruTop top = (HDLmPassThruTop) details;
			int   childrenSize = children.size();
			if (childrenSize != 1 && childrenSize != 2) {
				String errorFormat = "Number of children (%d) of the top node is not 1 or 2";
				String  errorText = String.format(errorFormat, childrenSize);
				HDLmAssertAction(false, errorText);
			}
			/* The first child will be the companies node */
			HDLmTree companiesNode = children.get(0);
			top.setCompaniesReference((HDLmPassThruCompanies) companiesNode.details);
			/* The second child (if it exists) will be the reports node */
			int childrenLength = children.size();
			if (childrenLength > 1) {
				HDLmTree reportsNode = children.get(1);
				top.setReportsReference((HDLmPassThruReports) reportsNode.details);
			}			 
		}
		/* Reset the subnode count for the current node. The 
		   subnode counts are actually in the current modification
		   associated with the current tree node.*/
		HDLmTree.resetSubnodeCounts(startupMode, this);
		/* Reset (if need be) the created and last modified dates and times
		   for the current node. The created and last modified dates and times
		   are actually in the current modification associated with the current
		   tree node. */
    this.resetDatesAndTimes(); 
		/* Recursively call this routine for all children of the current tree node */
		for (HDLmTree child : children) {
			child.modifyPassThruTree(startupMode);
		}
	}  
  /* This routine deletes a set of rows specified by the caller.
	   The caller passes an array with a set of ID values in it.
	   All of the rows with the specified ID values are deleted
	   by this code. This code returns nothing to the caller and
	   is synchronous. */
	protected static void  passDeleteRows(final ArrayList<Integer> idArray) {
		/* Check if the ID array reference passed by the caller is null */
		if (idArray == null) {
			String  errorText = "ID array reference passed to passDeleteRows is null";
			throw new NullPointerException(errorText);
		}
		/* Declare/define a few variables */
		ArrayList<HDLmDatabaseRow>  localRowList = null;
		int            i;
		int            idLen = idArray.size();
		Integer        currentRowId;
		/* Allocate the row ID list for use below */
		localRowList = new ArrayList<HDLmDatabaseRow>();
		if (localRowList == null) {
	 	  String   errorText = "Local row list is null in passDeleteRows";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Loop over the IDs passed by the caller */
    for (i = 0; i < idLen; i++) {
      currentRowId = idArray.get(i);
    	/* Create a new (empty) database row object */
			HDLmDatabaseRow   databaseRow = new HDLmDatabaseRow();
			if (databaseRow == null) {
		 	  String   errorText = "Local database row is null in passDeleteRows";
				HDLmAssertAction(false, errorText);		    	
		  }
			databaseRow.setId(currentRowId.toString());
			/* Add the new row to the list of rows */
			localRowList.add(databaseRow);      
    }    
		/* Try to delete some rows from the database */
		String  tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		HDLmDatabase.deleteTableRows(localRowList, tableName); 
	}
  /* This routine deletes a set of rows specified by the caller.
	   The rows are the nodes of a tree. The caller passes the top 
	   node of the tree. The row deletion is finished when the 
	   Promise is resolved. 
	   
	   This routine is no longer in use. A new routine is used to
	   delete nodes from the in-memory node trae and rows from the
	   database. */
	protected static void  passDeleteTree(final HDLmTree treePos) {
	  /* Check if the tree position value passed by the caller is null */		 
		if (treePos == null) {
			String  errorText = "Tree position reference passed by the caller to passDeleteTree is null";
			throw new NullPointerException(errorText);
		}
	  /* Declare and define a few variables */
	  ArrayList<Integer>   treeIdArray = new ArrayList<Integer>(); 
	  /* Get the ID values from a tree of nodes */	
	  HDLmTree.getIdValuesFromNodeTree(treeIdArray, treePos);
	  /* Delete all of the rows that were part of the node tree */
	  HDLmTree.passDeleteRows(treeIdArray);     
	  return;
	}
	/* This code inserts one or more rows into the database. One row
	   is created for each node of the tree. The caller passes the top
	   node of the tree. Note that this may not be the overall top node
	   of the tree. */
	protected static void  passInsertOneTreePos(final HDLmTree treePos) {
	  /* Check if the tree position value passed by the caller is null */		 
		if (treePos == null) {
			String  errorText = "Tree position reference passed by the caller to passInsertOneTreePos is null";
			throw new NullPointerException(errorText);
		}
	  /* Build the content string for use below. */
	  String  content = HDLmUtility.getContentString();
	  /* Get the data values from a tree of nodes */
	  ArrayList<String>   treeDataArray = new ArrayList<String>();
	  ArrayList<Integer>  tempTreeIdArray = new ArrayList<Integer>();
	  HDLmTree.buildInfoArray(treeDataArray, tempTreeIdArray, treePos);
	  /* Insert all of the rows associated with the tree */ 
	  ArrayList<Integer>  rowIdList = HDLmTree.passInsertRows(content, treeDataArray);
	  /* The ID values returned by the insert must be stored in each
	     node in the node tree */
	  HDLmTree.resetIdValues(rowIdList, treeDataArray);	  
	  return;
	}  
	/* This code inserts zero or more rows into the database. The caller  
	   passes an array with zero or more entries. This routine returns
	   a list of integers that are the new row ID values. */
	protected static ArrayList<Integer>  passInsertRows(final String content, 
			                                                final ArrayList<String> infoArray) {
	  /* Check if the content reference passed by the caller is null */		 
		if (content == null) {
			String  errorText = "Content reference passed by the caller to passInsertRows is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content passed by the caller is whitespace */
		if (StringUtils.isWhitespace(content)) {
			String  errorText = "Content passed by the caller to passInsertRows is all whitepace";
			throw new IllegalArgumentException(errorText);
		}
	  /* Check if the information array reference passed by the caller is null */		 
	  if (infoArray == null) {
		  String  errorText = "Information array reference passed by the caller to passInsertRows is null";
			throw new NullPointerException(errorText);
		}
	  /* Declare and define one or more variables */
	  ArrayList<Integer>          localRowIdList = null;
		ArrayList<HDLmDatabaseRow>  localRowList = null;
		Set<String>                 jsonKeys = null;
		int                         i;
	  int                         infoArrayLen = infoArray.size();
	  infoArrayLen = infoArray.size(); 
	  /* LOG.info(((Integer) infoArrayLen).toString()); */ 
	  /* Declare and define a few variables */
	  localRowList = new ArrayList<HDLmDatabaseRow>();
	  if (localRowList == null) {
	  	String  errorText = "Local row list not allocated in passInsertRows";
	  	HDLmAssert.HDLmAssertAction(false, errorText);	  	
	  }
		/* Build the set of columns to be updated */
		jsonKeys = new HashSet<String>();
		jsonKeys.add("content");
		jsonKeys.add("info");
		jsonKeys.add("name");	
	  /* Process each entry in the data array */
	  for (i = 0; i < infoArrayLen; i++) {
    	/* Create a new (empty) database row object */
			HDLmDatabaseRow   databaseRow = new HDLmDatabaseRow();
			if (databaseRow == null) {
		 	  String   errorText = "Local database row is null in passInsertRows";
				HDLmAssertAction(false, errorText);		    	
		  } 
			databaseRow.setContent(content);
	  	/* Get the current values from each array element */ 
	    String          infoEntry = infoArray.get(i);
			databaseRow.setInfo(infoEntry);
			/* LOG.info("Before null check"); */
	    if (infoEntry != null) {
	    	/* LOG.info("After null check"); */
	    	/* LOG.info(((Integer) i).toString()); */
	    	String    tempInfoEntry = infoEntry;
	    	tempInfoEntry = tempInfoEntry.replace("\"created\":", "\"dummyCreated\":");
	    	tempInfoEntry = tempInfoEntry.replace("\"lastModified\":", "\"dummyLastModified\":");
	  	  Gson      gson = HDLmMain.gsonMain;
	  	  HDLmTree  dataEntryObj = gson.fromJson(tempInfoEntry, HDLmTree.class);
	      String    dataEntryObjName = dataEntryObj.getLastNodePathEntry(); 	
	      databaseRow.setName(dataEntryObjName);
	    }
			/* Add the new row to the list of rows */
			localRowList.add(databaseRow);   
	  }  
		/* Try to insert some number of rows into the database with
		   the JSON string */
    String  tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
    localRowIdList = HDLmDatabase.insertTableRows(localRowList, jsonKeys, tableName);			
    return localRowIdList;
	}
  /* This code reads all of the rows from the database and returns 
	   a string to the caller. When the routine finishes all of the 
	   rows will be in the response text. The set of rows depends on 
	   the current content type. The current content type depends on
	   the current editor type which will always be pass for this
	   routine. This routine returns all of the rows from one specific
	   editor type. The editor type is hard coded below. */
	protected static ArrayList<HDLmDatabaseRow>  passReadAllRows(final HDLmEditorTypes editorType) {
	  /* Check if the editor type reference passed by the caller is null */		 
		if (editorType == null) {
			String  errorText = "Editor type reference passed by the caller to passReadAllRows is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		String  content = HDLmUtility.getContentString(editorType);
		/* Get all of the rows from the server */
		String  tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		return HDLmDatabase.getTableRows(content, tableName, null);
	}  
  /* This code updates all of the rows in a database that match
	   the content string passed by the caller. The first step is
	   to get all of the ID values for all of the rows in the 
	   database, that match the content string passed by the caller. 
	   The second step (if need be) is to delete all of those rows.
	   The third step is to insert all of the new rows. */
  protected static void  passUpdateAllRows(final String content, 
  		                                     final HDLmTree treeTop, 
  		                                     final boolean force) {
	  /* Check if the content reference passed by the caller is null */		 
		if (content == null) {
			String  errorText = "Content reference passed by the caller to passUpdateAllRows is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content passed by the caller is whitespace */
		if (StringUtils.isWhitespace(content)) {
			String  errorText = "Content passed by the caller to passUpdateAllRows is all whitepace";
			throw new IllegalArgumentException(errorText);
		}
	  /* Check the info reference (HDLmTree converted to JSON passed
	     by the caller is null */		 
		if (treeTop == null) {
			String  errorText = "Tree top reference passed by the caller to passUpdateAllRows is null";
			throw new NullPointerException(errorText);
		}	  
	  /* Declare and define a few variables */
	  ArrayList<Integer>  idArray = new ArrayList<Integer>();
	  if (idArray == null) {
	  	String  errorText = "ID array not allocated in passUpdateAllRows";
	  	HDLmAssert.HDLmAssertAction(false, errorText);	  	
	  }
	  ArrayList<String>   infoArray = new ArrayList<String>();
	  if (infoArray == null) {
	  	String  errorText = "Info array not allocated in passUpdateAllRows";
	  	HDLmAssert.HDLmAssertAction(false, errorText);	  	
	  }
	  ArrayList<Integer>  tempIdArray = new ArrayList<Integer>();
	  if (tempIdArray == null) {
	  	String  errorText = "Temporary ID array not allocated in passUpdateAllRows";
	  	HDLmAssert.HDLmAssertAction(false, errorText);	  	
	  }
	  int     rowCount; 
	  /* Increment the update counter. If the new update counter is
	     greater than one, just return to the caller. Note that the
	     force parameter can be set to true. If this is correct, then
	     the code below really will be executed. The true value is used
	     to force the execution of this code when this code is invoked
	     by a then routine. */
	  HDLmGlobals.incrementUpdateCounter(); 
	  if (HDLmGlobals.getUpdateCounter() > 1 && force == false)
	    return;
	  /* The information array can be built from the node tree */
	  HDLmTree.buildInfoArray(infoArray, tempIdArray, treeTop);
	  int   infoArrayLen = infoArray.size();
	  int   tempIdArrayLen = tempIdArray.size();
	  /* LOG.info(((Integer) infoArrayLen).toString()); */
	  /* LOG.info(((Integer) tempIdArrayLen).toString()); */	  
	  /* We can now try to get all of the rows with a matching 
	     content type value */
	  ArrayList<HDLmDatabaseRow>  rowList = HDLmTree.passReadAllRows(HDLmEditorTypes.PASS);     
	  /* Extract all of the ID values from the response text */
	  idArray = HDLmTree.getIdArray(rowList);
	  rowCount = idArray.size(); 
	  /* LOG.info(((Integer) rowCount).toString()); */	
	  /* We can now delete all of the matching rows from the
	     database. The number of rows might be zero or it might
	     be greater than zero. */ 
	  HDLmTree.passDeleteRows(idArray);   
	  rowCount = idArray.size(); 
	  /* LOG.info(((Integer) rowCount).toString()); */	 
	  /* Now that all of the old rows have been deleted, we can
	     insert all of the new rows */
	  ArrayList<Integer>  rowIdList = HDLmTree.passInsertRows(content, infoArray);
	  infoArrayLen = infoArray.size(); 
	  /* LOG.info(((Integer) infoArrayLen).toString()); */ 	
    /* The ID values returned by the insert must be stored in each
       node in the node tree */
    HDLmTree.resetIdValues(rowIdList, infoArray);
    /* The update operation is now complete. The update counter
       must be decremented by one. If the update counter is still
       greater then zero, then we have pending updates that need
       to be processed. The update counter is again decremented 
       by one and the current routine is invoked with a force value
       of true. */
    HDLmGlobals.decrementUpdateCounter();
    if (HDLmGlobals.getUpdateCounter() >= 1) {
      HDLmGlobals.decrementUpdateCounter();
      HDLmTree.passUpdateAllRows(content, treeTop, true);
    } 
	  return;
	}
	/* This code updates zero or more rows (zero or more nodes) in the server.
	   The nodes could be any type of node. For example, one of the nodes, 
	   could be a report node. */
	protected static void  passUpdateRows(final ArrayList<Integer> idArray, 
			                                  final ArrayList<String> dataArray) {
		/* Check if the ID array reference passed by the caller is null */
		if (idArray == null) {
			String  errorText = "ID array reference passed to passUpdateRows is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the data array reference passed by the caller is null */		 
	  if (dataArray == null) {
		  String  errorText = "Data array reference passed by the caller to passUpdateRows is null";
			throw new NullPointerException(errorText);
		}
		/* Declare/define a few variables */
		ArrayList<HDLmDatabaseRow>  localRowList = null;
		boolean                     logDebugEnabled = LOG.isDebugEnabled();
		Set<String>                 jsonKeys = null;
	  /* Get the length of each array passed by the caller. The lengths
	     must be the same. */
	  int   dataArrayLen = dataArray.size();
	  int   i;
	  int   idArrayLen = idArray.size();
	  if (idArrayLen != dataArrayLen) {
	  	String errorFormat = "idArray length (%d) is not equal to dataArray length (%d)";
	    String  errorText = String.format(errorFormat, idArrayLen, dataArrayLen);
	    HDLmAssertAction(false, errorText);
	  }
		/* Allocate the row ID list for use below */
		localRowList = new ArrayList<HDLmDatabaseRow>();
		if (localRowList == null) {
	 	  String   errorText = "Local row list is null in passUpdateRows";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Build the set of columns to be updated */
		jsonKeys = new HashSet<String>();
		jsonKeys.add("info");
		jsonKeys.add("name");		
		jsonKeys.add("id");
	  /* Process all of the rows to be updated */
	  for (i = 0; i < idArrayLen; i++) {
	    /* Get the current values from each array */
	    Integer   idEntry = idArray.get(i);
	    String    dataEntry = dataArray.get(i);
	    if (logDebugEnabled) {
	      LOG.debug("In HdLmTree passUpdateRows");
	      LOG.debug(idEntry.toString());
	      LOG.debug(dataEntry);
	    }
    	/* Create a new (empty) database row object */
			HDLmDatabaseRow   databaseRow = new HDLmDatabaseRow();
			if (databaseRow == null) {
		 	  String   errorText = "Local database row is null in passUpdateRows";
				HDLmAssertAction(false, errorText);		    	
		  }
			databaseRow.setId(idEntry.toString());
			databaseRow.setInfo(dataEntry);
	    if (dataEntry != null) {
	  	  Gson      gson = HDLmMain.gsonMain;
	  	  if (logDebugEnabled)
	  	    LOG.debug(dataEntry);
	  		dataEntry = dataEntry.replaceAll("created", "dummyCreated");
	  		dataEntry = dataEntry.replaceAll("lastmodified", "dummyLastModified");
	  	  HDLmTree  dataEntryObj = gson.fromJson(dataEntry, HDLmTree.class);
	      String    dataEntryObjName = dataEntryObj.getLastNodePathEntry(); 	
	      databaseRow.setName(dataEntryObjName);
	    }
			/* Add the new row to the list of rows */
			localRowList.add(databaseRow); 
	  }	  	 
    /* Try to update some number of rows in the database */
    String  tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		HDLmDatabase.updateTableRows(localRowList, jsonKeys, tableName);	
    return;
	}
	/* The method below processes an entire hierarchy of JSON elements. Each JSON
	   element is checked for children and this method calls itself to handle any
	   child JSON elements. If a JSON element with details (a modification) is
	   found, a special routine is invoked to handle the details. */
	protected static void processJsonTree(final String contentType, 
			                                  final JsonElement jsonElement) {
		/* Check if the content type reference passed by the caller is null */
		if (contentType == null) {
			String  errorText = "Content type reference passed to processJsonTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "Node tree value passed to processJsonTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current JSON element is really a JSON object */
		if (!jsonElement.isJsonObject())
			return;
		/* The current JSON element may have details (modifications and other types that
		   extend modifications have details ) associated with it. Check for and handle
		   any details. */
		JsonElement jsonElementDetails = HDLmJson.getJsonValue(jsonElement, "details");
		if (jsonElementDetails != null) {
			String passContentType = HDLmEditorTypes.PASS.toString();
			if (contentType.equals(passContentType))
				HDLmPassThru.processJsonPass(jsonElementDetails);
			else
				HDLmMod.processJsonMod(jsonElementDetails);
		}
		/* We need to make a few changes to the JSON at this point */
		JsonElement jsonElementChildren = HDLmJson.getJsonValue(jsonElement, "children");
		JsonElement jsonElementNodePath = HDLmJson.getJsonValue(jsonElement, "nodePath");
		String newTopStr = HDLmDefines.getString("HDLMTOPNODENAME");
		String newTopStrLower = newTopStr.toLowerCase();
		String nodeType = HDLmJson.getJsonString(jsonElement, "type");
		nodeType = nodeType.toLowerCase();
		if (nodeType.equals(newTopStrLower))
			nodeType = HDLmDefines.getString("HDLMTOPNODETYPE");
		HDLmJson.setJsonString(jsonElement, "type", nodeType);
		HDLmJson.setJsonValue(jsonElement, "children", jsonElementChildren);
		if (jsonElementDetails != null)
			HDLmJson.setJsonValue(jsonElement, "details", jsonElementDetails);
		HDLmJson.setJsonValue(jsonElement, "nodePath", jsonElementNodePath);
		/* Check the children of the current JSON element */
		if (jsonElementChildren == null)
			return;
		if (!jsonElementChildren.isJsonArray())
			return;
		JsonArray jsonChildrenArray = jsonElementChildren.getAsJsonArray();
		/* Process all of the children of the current JSON element */
		for (var jsonElementChild : jsonChildrenArray) {
			HDLmTree.processJsonTree(contentType, jsonElementChild);
		}
	}
	/* This code inserts zero or more rows into the database. One row
	   is created for each pending insert. The caller does not pass
	   anything. The array of pending inserts may be empty. This is 
	   not an error condition. Of course, nothing will be done in 
	   this case. When the server responds with a set of ID values,
	   the ID values are extracted and added to each tree node. */
	protected static void  processPendingInserts() {
	  /* Build the content string for use below. */
	  String  content = HDLmUtility.getContentString();
	  /* Get the data values from the pending inserts. Note that 
	     a shallow copy is made here so that the actual pending
	     inserts array can be cleared. */
	  ArrayList<String>  treeDataArray = new ArrayList<String>(HDLmTree.pendingInserts);
	  HDLmTree.pendingInserts.clear();
	  if (treeDataArray.size() == 0)
	    return;	   
	  /* Insert all of the rows associated with the tree */
	  ArrayList<Integer>  rowIdList = HDLmTree.passInsertRows(content, treeDataArray); 
	  /* The ID values returned by the insert must be stored in each
	     node in the node tree */
	  HDLmTree.resetIdValues(rowIdList, treeDataArray);
	}
	/* This code updates zero or more rows in the database. One row
	   is updated by each pending update. The caller does not pass
	   anything. The array of pending updates may be empty. This is 
	   not an error condition. Of course, nothing will be done in 
	   this case.
	
	   If the same ID value is specified more than once, only the last
	   use of the ID value will actually be used. In other words, the
	   same ID value may appear more than once in the pending updates
	   array. Only the last use of each ID value will really be used. */
	protected static void processPendingUpdates() {
	  /* Declare and define a few variables */	 
		Map<String, String>   updateMap = new HashMap<String, String>();
		if (updateMap == null) {			 
	    String  errorText = "Update map allocation failed in processPendingUpdates";
			throw new NullPointerException(errorText);		
		}
	  ArrayList<Integer>  updateIdArray = new ArrayList<Integer>();
		if (updateIdArray == null) {			 
	    String  errorText = "Update ID array allocation failed in processPendingUpdates";
			throw new NullPointerException(errorText);		
		}
	  ArrayList<String>  updateDataArray = new ArrayList<String>();
		if (updateDataArray == null) {			 
	    String  errorText = "Update data array allocation failed in processPendingUpdates";
			throw new NullPointerException(errorText);		
		}
	  /* Check if the number of pending updates is actually zero. If this
	     is true, then we can return to the caller immediately. */
	  if (HDLmTree.pendingUpdates.size() == 0)
	    return;
	  /* Build the content string for use below. */
	  String    content = HDLmUtility.getContentString();
	  boolean   logDebugEnabled = LOG.isDebugEnabled();
	  /* Add each pending update to the Map created above. This 
	     has the effect of only using the last use of each ID
	     value. */
	  int   i;
	  int   pendingCount = HDLmTree.pendingUpdates.size();
	  for (i = 0; i < pendingCount; i++) {
	    String    pendingUpdateStr = HDLmTree.pendingUpdates.get(i);
	    Gson      gson = HDLmMain.gsonMain;
	    if (logDebugEnabled) {
			  LOG.debug("In HDLmTree.processPendingUpdates - about to process a pending update");
			  LOG.debug(pendingUpdateStr);
	    }
	    HDLmTree  pendingUpdate = gson.fromJson(pendingUpdateStr, HDLmTree.class);
	    if (logDebugEnabled)
	      LOG.debug("In HDLmTree.processPendingUpdates - after gson.fromJson");
	    String    pendingId = pendingUpdate.getId();
	    /* The actual JSON for the pending update is really stored
	       in the tooltip field */ 
	    String    pendingStr = pendingUpdate.getTooltip();
	    if (logDebugEnabled)
	      LOG.debug(pendingStr);
	    updateMap.put(pendingId, pendingStr);
	  }
	  /* Remove all of the pending updates */
	  HDLmTree.pendingUpdates.clear();
	  /* Process the map of pending updates */
	  for (Map.Entry<String, String>  updateEntry : updateMap.entrySet()) {		  
	    String   entryId = updateEntry.getKey();
		  String   entryJson = updateEntry.getValue();		 
	    updateIdArray.add(HDLmUtility.convertInteger(entryId));
	    updateDataArray.add(entryJson);
	  }
	  /* Update all of the rows */
	  if (logDebugEnabled)
	    LOG.debug("In HDLmTree.processPendingUpdates - about to call HDLmTree.passUpdateRows");
	  HDLmTree.passUpdateRows(updateIdArray, updateDataArray);
	  return;
	}
	/* This routine process the entire tree (HDLm Tree). The caller 
	   passes the current node (a tree position) and a function that
	   should be invoked using the current tree position. All of the
	   children of the current tree node are handled recursively. The
	   function is passed using an object that has the method we need. */ 
	protected static void  processTree(final HDLmTree treePos, 
                			               final HDLmProcessTreeInterface processTreeInstance) {
		/* Check a few values passed by the caller */
		if (treePos == null) {			 
	    String  errorText = "Passed tree position is null in processTree";
			throw new NullPointerException(errorText);		
		}
		if (processTreeInstance == null) {			 
	    String  errorText = "Passed process tree instance is null in processTree";
			throw new NullPointerException(errorText);		
		}
		/* Run some routine on the current tree position. This will be done
		   for every element in the HDLm tree. */ 
		processTreeInstance.processTreePos(treePos);	
	  /* Handle all of the children of the tree node passed
	     by the caller */
	  ArrayList<HDLmTree>   childArray = treePos.children;
	  int   i;
	  int   childArraySize = childArray.size();
	  for (i = 0; i < childArraySize; i++) {
	    HDLmTree.processTree(childArray.get(i), processTreeInstance);
	  }	
	}
	/* This routine replaces the entire modification rule tree */
	protected static void  replaceEntireTree(final String contentType, 
			                                     final HDLmTree topTreeNode) {
		/* Check if the content type reference passed by the caller is null */
		if (contentType == null) {
			String  errorText = "Content type reference passed to replaceEntireTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the top tree node passed by the caller is null */
		if (topTreeNode == null) {
			String  errorText = "Top tree passed to replaceEntireTree is null";
			throw new NullPointerException(errorText);
		}
		/* The call below will convert the entire JSON element tree to a JSON string and
		   send the JSON string back to the server. Since the JSON element tree was
		   built from the updated node tree and any new modifications, this will have
		   the effect of sending the new tree elements and modifications back to the
		   server.
		 
		   Tree was added to the method name to help distinguish between different
		   routines that used to have the same name. */
		HDLmTree.updateEntireTree(contentType, topTreeNode);
	} 

	/* This routine resets the data and time values for a tree node. The actual 
	   changes (if any) are in the associated modification (the details of the 
	   tree node. */
	protected void         resetDatesAndTimes() {
		/* Check the instants associated with the current tree node. This step
	     should not be required. However, experience has shown that the instants
	     are not always set correctly. */
		HDLmMod  nodeTreeDetails = this.getDetails();
		if (nodeTreeDetails == null) {
			String errorText = "Details value is null in modifyPassThruTree";
			throw new NullPointerException(errorText);
		}
		/* Get a few values from the current modification */
		Instant  detailsCreated = nodeTreeDetails.getCreated();
		Instant  detailsLastModified = nodeTreeDetails.getLastModified();
		String   detailsName = nodeTreeDetails.getName();
		Instant  currentInstant = Instant.now();
		/* Check for a few null values */
		if (detailsCreated == null) {
			detailsCreated = currentInstant;
			nodeTreeDetails.setCreated(detailsCreated); 
			LOG.info(detailsName + " - created instant is null");
		}
		if (detailsLastModified == null) {
			detailsLastModified = currentInstant;
			nodeTreeDetails.setLastModified(detailsLastModified); 
			LOG.info(detailsName + " - last modified instant is null");
		}
		/* detailsLastModified = detailsLastModified.minus(20, ChronoUnit.DAYS); */
		/* Check if the last modified instant is before the
		   created instant */
		if (detailsLastModified.compareTo(detailsCreated) < 0)  {
			detailsLastModified = detailsCreated;
			nodeTreeDetails.setLastModified(detailsLastModified); 
			LOG.info(detailsName + " - last modified instant is before created instant");
		}					
	}
  /* This routine reset ID values in the node tree. We need to have
	   the actual ID values in each node so that the node can be deleted
	   and/or updated. This routine is passed an array of ID values and
	   an array of node information. The node information is used to find
	   the actual nodes that are then updated with current ID information. */
	protected static boolean resetIdValues(final ArrayList<Integer> idArray, 
			                                   final ArrayList<String> infoArray) {
		/* Check if the ID array reference passed by the caller is null */
		if (idArray == null) {
			String  errorText = "ID array reference passed to resetIdValues is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the information array reference passed by the caller is null */		 
	  if (infoArray == null) {
		  String  errorText = "Information array reference passed by the caller to resetIdValues is null";
			throw new NullPointerException(errorText);
		}
	  /* Get the lengths of each array. The lengths must be equal. */
	  int   i;
	  int   idArrayLen = idArray.size();
	  int   infoArrayLen = infoArray.size();
	  if (idArrayLen != infoArrayLen) {
	  	String errorFormat = "idArray length (%d) is not equal to infoArray length (%d)";
	    String  errorText = String.format(errorFormat, idArrayLen, infoArrayLen);
	    HDLmAssertAction(false, errorText);
	  }
  	/* We must get the top tree node for use later */
	  HDLmTree  topTreeNode = HDLmPassThruTop.getTopTree();
	  /* Process each entry in the info array */
	  for (i = 0; i < infoArrayLen; i++) {
	    String    infoArrayEntry = infoArray.get(i);
    	String    tempInfoArrayEntry = infoArrayEntry;
    	tempInfoArrayEntry = tempInfoArrayEntry.replace("\"created\":", "\"dummyCreated\":");
    	tempInfoArrayEntry = tempInfoArrayEntry.replace("\"lastModified\":", "\"dummyLastModified\":");
			Gson      gson = HDLmMain.gsonMain;
			HDLmTree  infoArrayEntryObj = gson.fromJson(tempInfoArrayEntry,  HDLmTree.class); 
	    ArrayList<String>   infoEntryNodePath = infoArrayEntryObj.getNodePath();
	    /* Try to find the node in the node tree we are looking for */
	    HDLmTree  infoEntryNode = HDLmTree.locateTreeNode(topTreeNode, infoEntryNodePath);
	    /* Report an error if the node could not be found */
	    if (infoEntryNode == null) {
	      String  nodeString = infoEntryNodePath.toString(); 
	      HDLmUtility.logStackTrace();
	      HDLmUtility.logString(nodeString, LOG);
				HDLmAssertAction(false, "Null tree node returned by locateTreeNode");		
	      return false;
	    }
	    /* Get the new and old ID values */
	    String   oldIdValue = infoEntryNode.id;
	    String   newIdValue = idArray.get(i).toString();
	    infoEntryNode.setId(newIdValue); 
	  }
	  return true;
	}
	/* This routine resets all of the subnode counts in each of 
	   the modifications. This routine processes the current node.
	   Not all tree nodes (the associated modification) types have 
	   subnode counts. */
	protected static void  resetSubnodeCounts(final HDLmStartupMode startupMode, 
			                                      final HDLmTree treePos) { 
		/* Check if the startup mode value passed by the caller is null */
		if (startupMode == null) {
			String   errorText = "Startup mode value passed to modifyPassThruTree is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the startup mode passed by the caller is invalid */
		if (startupMode == HDLmStartupMode.NONE) {
		  HDLmAssertAction(false, "Startup mode value passed to modifyPassThruTree is invalid");
		}
	  /* Check if the tree position value passed by the caller is null */		 
		if (treePos == null) {
			String  errorText = "Tree position reference passed by the caller to resetSubnodeCount is null";
			throw new NullPointerException(errorText);
		}
		/* Get the children of the current node and (if possible) 
		   the number of children */
		ArrayList<HDLmTree>  childrenArray = treePos.getChildren();
		if (childrenArray == null) 
			return;
		int   childrenCount = childrenArray.size();
		/* Get the details for the current node. The details
		   are the actual modification. */
		HDLmMod   nodeDetails = treePos.getDetails();
		if (nodeDetails == null)
	   return;
		/* Get the type of the current node and reset the
		   count in the current set of details as need be */		
		HDLmTreeTypes  nodeType = treePos.type;
		int   subnodesCount = 0;
		Instant   savedLastModified = null;
		switch (nodeType) { 
	    case COMPANIES:
	    	subnodesCount = ((HDLmPassThruCompanies) nodeDetails).getCompaniesCount();
        if (subnodesCount != childrenCount) {	    	
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruCompanies) nodeDetails).getLastModified();						 
	        /* Reset the subnode count for the current node */
	    	  ((HDLmPassThruCompanies) nodeDetails).setCompaniesCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruCompanies) nodeDetails).setLastModified(savedLastModified);		
        }
	    	break;
	    case DATA:
	    	subnodesCount = ((HDLmPassThruData) nodeDetails).getDivisionsCount();
        if (subnodesCount != childrenCount)	{	
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruData) nodeDetails).getLastModified();	
	        /* Reset the subnode count for the current node */
	    	  ((HDLmPassThruData) nodeDetails).setDivisionsCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruData) nodeDetails).setLastModified(savedLastModified);	
        }
	    	break;
	    case DIVISION:
	    	subnodesCount = ((HDLmPassThruDivision) nodeDetails).getSitesCount();
        if (subnodesCount != childrenCount) {	
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruDivision) nodeDetails).getLastModified();	
          /* Reset the subnode count for the current node */
          ((HDLmPassThruDivision) nodeDetails).setSitesCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruDivision) nodeDetails).setLastModified(savedLastModified);	
        }
	   	  break;
	    case LINES:
	    	subnodesCount = ((HDLmPassThruLines) nodeDetails).getLinesCount();
        if (subnodesCount != childrenCount) {	
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruLines) nodeDetails).getLastModified();	
          /* Reset the subnode count for the current node */
          ((HDLmPassThruLines) nodeDetails).setLinesCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruLines) nodeDetails).setLastModified(savedLastModified);	
        }
	    	break;
	    case LIST:
	    	subnodesCount = ((HDLmPassThruList) nodeDetails).getIgnoresCount();
        if (subnodesCount != childrenCount) {		
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruList) nodeDetails).getLastModified();	
          /* Reset the subnode count for the current node */
          ((HDLmPassThruList) nodeDetails).setIgnoresCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruList) nodeDetails).setLastModified(savedLastModified);	
        }
	    	break;
	    case LISTS:
	    	subnodesCount = ((HDLmPassThruLists) nodeDetails).getListsCount();
        if (subnodesCount != childrenCount) {		
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruLists) nodeDetails).getLastModified();	
          /* Reset the subnode count for the current node */
          ((HDLmPassThruLists) nodeDetails).setListsCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruLists) nodeDetails).setLastModified(savedLastModified);	
        }
	    	break;
	    case REPORT:
	    	subnodesCount = ((HDLmPassThruReport) nodeDetails).getLinesCount();
        if (subnodesCount != childrenCount) {		
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruReport) nodeDetails).getLastModified();	
          /* Reset the subnode count for the current node */
          ((HDLmPassThruReport) nodeDetails).setLinesCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruReport) nodeDetails).setLastModified(savedLastModified);	
        }
	    	break;
	    case REPORTS:
	    	subnodesCount = ((HDLmPassThruReports) nodeDetails).getReportsCount();
        if (subnodesCount != childrenCount) {		
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruReports) nodeDetails).getLastModified();	
          /* Reset the subnode count for the current node */
          ((HDLmPassThruReports) nodeDetails).setReportsCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruReports) nodeDetails).setLastModified(savedLastModified);	
        }
	    	break;
	    case RULES:
	    	subnodesCount = ((HDLmPassThruRules) nodeDetails).getDivisionsCount();
        if (subnodesCount != childrenCount) {		
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruRules) nodeDetails).getLastModified();	
          /* Reset the subnode count for the current node */
          ((HDLmPassThruRules) nodeDetails).setDivisionsCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruRules) nodeDetails).setLastModified(savedLastModified);	
        }
	    	break;
	    case SITE:
	    	subnodesCount = ((HDLmPassThruSite) nodeDetails).getRulesOrValuesCount();
        if (subnodesCount != childrenCount) {		
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						savedLastModified = ((HDLmPassThruSite) nodeDetails).getLastModified();	
	        /* Reset the subnode count for the current node */
          ((HDLmPassThruSite) nodeDetails).setRulesOrValuesCount(childrenCount);
					if (startupMode == HDLmStartupMode.STARTUPMODEYES) 
						((HDLmPassThruSite) nodeDetails).setLastModified(savedLastModified);	
        }
	   	  break;
		}
	}
	/* Add or replace a tree node value to the node tree. The actual node tree has
	   several levels. The intermediate levels (company, data, division, site)
	   may or may not exist. Create these levels if need be using the information
	   provided by the caller. 
	 
	   Add or replace the new or existing data value in the correct place. The 
	   higher levels may or may not have been created by this routine. */
	protected static void  saveDataValue(final JsonElement jsonElements, 
			                                 final String hostName) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElements == null) {
			String  errorText = "JSON elements passed to saveDataValue is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element is a null value */
		if (jsonElements.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElements.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		} 
		/* Check if the host name string instance passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string instance passed to saveDataValue is null";
			throw new NullPointerException(errorText);
		}	
		/* Build all of the intermediate levels as need be. This call will
	     update the node tree (HDLmTree) in memory and send any new nodes
	     to the database as need be. */ 
	  HDLmModResponse   modResponse = HDLmTree.buildLevelsGetSetTree(hostName,
	 		                                                             HDLmFreshCopy.FRESHCOPYNO,
	                                                                 HDLmTreeTypes.DATA); 
	  HDLmTree            topTreeNode = modResponse.getTreeNode();
	  ArrayList<String>   nodePath = modResponse.getNodePath();
	  if (topTreeNode == null) {
		  HDLmAssertAction(false, "Null modifications tree returned by buildLevelsGetSetTree");
	  } 
		/* Try to find the tree node for the current node path.
		   This will be the parent of the tree node we are about
		   to create or replace. */   
	  HDLmTree  nodeParent = HDLmTree.locateTreeNode(topTreeNode, nodePath);		
		if (nodeParent == null) {
			String  nodeString = nodePath.toString(); 
			HDLmUtility.logString(nodeString, LOG);
			HDLmUtility.logStackTrace();
			HDLmAssertAction(false, "Null tree node returned by locateTreeNode");
		}
		/* We can now create or replace the tree node for the data value */ 
		ArrayList<String>   nodePathTree = new ArrayList<String>(nodePath);
		HDLmTreeTypes       nodeEnum = HDLmTreeTypes.MOD;
		JsonElement         nodeDetails  = HDLmJson.getJsonValue(jsonElements, "details");
		JsonArray           nodePathJson = HDLmJson.getJsonArray(jsonElements, "nodePath");
		int                 nodePathJsonSize = nodePathJson.size();
		String              nodeName = nodePathJson.get(nodePathJsonSize-1).getAsString();
		nodePathTree.add(nodeName);
		String              nodeTooltip = HDLmJson.getJsonString(jsonElements, "tooltip");
	  HDLmTree  newTreeNode = new HDLmTree(nodeEnum,  
			                                   nodeTooltip, nodePathTree);
	  /* Make sure the new tree node was actually created */
		if (newTreeNode == null) 
			HDLmAssertAction(false, "Null modification tree node returned by new tree node constructor");
		/* We can now create the modification rule structure */ 
		HDLmMod   newModNode = new HDLmMod(nodeDetails);
		newModNode.setIfNotSetTimes();
	  newTreeNode.setDetails(newModNode);		 
	  /* At this point, the new tree node is pretty much done. We may need to 
	     get the perceptual hash values (really just one) for any images that
	     the tree node uses. The new instance below is used just to provide
	     a way of invoking the function that actually does all of the work. */
		HDLmProcessTreePHash  pHashInstance = new HDLmProcessTreePHash();
	  pHashInstance.processTreePos(newTreeNode); 	  
	  /* Add the newly created tree node to the tree node tree. This should
	     only be done after we have the final name for the tree node and the
	     modification. At this point we have the final name for both. */
		nodeParent.addOrReplaceChild(newTreeNode);
		HDLmTree.addPendingInserts(newTreeNode);
		HDLmTree.processPendingInserts();
		/* The call below sends the updated rule tree back to the server.
	    The call also sets or resets the tree top to the new value. 
	    The new tree will include the tree node possibly added above. 
	    
	    The entire tree of nodes (the HDLmTree) is no longer sent back
	    to the database here. Instead, new nodes are selectively added
	    to the database as need be. */ 
		if (1 == 2) {
	    String  contentType = HDLmEditorTypes.PASS.toString();
	    HDLmTree.replaceEntireTree(contentType, topTreeNode);
		  /* Save the updated tree node in a common location. This has
	      the effect of making sure that the updated node tree is 
	      used for all future work. */  
	    HDLmTree.setNodePassTreeTop(topTreeNode); 
		}
	}
	/* Set the HDLmTree children value */
	private void setChildren(final ArrayList<HDLmTree> newChildren) {
		if (newChildren == null) {
			String  errorText = "New tree children value is null";
			throw new NullPointerException(errorText);
		}
		children = newChildren;
	}
	/* Set the HDLmTree children value to a null value */
	private void setChildrenNull() {
		children = null;
	}
	/* Set the details from an HDLmTree element. Actual details are only used by
	   modification HDLmTree elements. The details object passed here must be
	   unique. It must not be altered by any other change to any other modification
	   object. */
	protected void setDetails(final HDLmMod newDetails) {
		if (newDetails == null) {
			String  errorText = "New tree details value is null";
			throw new NullPointerException(errorText);
		}
		/* We need to make sure that the details have the same modification name has the
		   modification itself. The code below checks this. */
		String detailsName = newDetails.getName();
		String treeName = this.getLastNodePathEntry();
		if (!treeName.equals(detailsName)) {
			String  errorText = String.format("Modification details name (%s) and modification node name (%s) don't match",
				                  	           detailsName, treeName);
			HDLmAssertAction(false, errorText);
		}
		details = newDetails;
	}
	/* Set the HDLmTree ID string value */
	protected void setId(final String newId) {
		if (newId == null) {
			String  errorText = "New tree ID value is null";
			throw new NullPointerException(errorText);
		}
		id = newId;
	}
	/* Set the HDLmTree ID string value to a null value */
	protected void setIdNull() {
		id = null;
	}
	/* Set the tooltip field, if it is not set */
	protected void setIfNotSetTooltip(final String newTooltip) {
		/* Check if the new tooltip value passed by the caller is null */
		if (newTooltip == null) {
			String  errorText = "New tooltip string value passed to setIfNotSetTooltip is null";
			throw new NullPointerException(errorText);
		}
		/* Set the new tooltip value, if the old value is not set */
		if (this.tooltip == null || this.tooltip.equals(""))
			this.tooltip = newTooltip;
	}
	/* Set the node tree (with modifications) top value */
	protected static void setNodeModTreeTop(final HDLmTree newNodeTreeTop) {
		if (newNodeTreeTop == null) {
			String  errorText = "New tree top value is null";
			throw new NullPointerException(errorText);
		}
		HDLmTree.nodeModTreeTop = newNodeTreeTop;
	}
	/* Set the HDLmTree node path array value. The node path array passed here must
	   be unique. It must not be altered by any other change to any other
	   modification object or node path array. */
	private void setNodePath(final ArrayList<String> newNodePath) {
		if (newNodePath == null) {
			String  errorText = "New tree node path value is null";
			throw new NullPointerException(errorText);
		}
		nodePath = newNodePath;
	}
	/* Set the HDLmTree node path array value to null */ 
	private void setNodePathNull() {
		nodePath = null;
	}
	/* Set the node tree (with modifications) top value */
	protected static void setNodePassTreeTop(final HDLmTree newNodeTreeTop) {
		if (newNodeTreeTop == null) {
			String  errorText = "New tree top value is null";
			throw new NullPointerException(errorText);
		}
		HDLmTree.nodePassTreeTop = newNodeTreeTop;
	}
	/* Set the node tree top value to a null value */
	protected static void setNodePassTreeTopNull() {
		HDLmTree.nodePassTreeTop = null;
	}
	/* The next routine sets the saved details (if any) */
	protected static void setSavedDetails(final String contentType, 
			                                  final Map<String, Object> newSavedDetails) {
		/* Check if the content type string reference passed by the caller is null */
		if (contentType == null) {
			String  errorText = "Content type reference passed to setSavedDetails is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the saved details reference passed by the caller is null */
		if (newSavedDetails == null) {
			String  errorText = "Saved details reference passed to setSavedDetails is null";
			throw new NullPointerException(errorText);
		}
		HDLmTree.savedDetailsMap.put(contentType, newSavedDetails);
	}
	/* Set the HDLmTree tooltip value */
	protected void         setTooltip(final String newTooltip) {
		if (newTooltip == null) {
			String  errorText = "New tree tooltip value is null";
			throw new NullPointerException(errorText);
		}
		tooltip = newTooltip;
	}
	/* Set the HDLmTree tooltip value to a null value */
	protected void setTooltipNull() {
		tooltip = null;
	}
	/* Set the HDLmTree type value */
	protected void         setType(final HDLmTreeTypes newType) {
		type = newType;
	}
	/* The code below is the new code. The new code converts the entire node tree 
	   (not the Fancytree) to an array of JSON strings and send them back to the
	   server. This has the effect of saving everything. Eventually a better approach 
	   wil be needed with a permissions check. The entire tree is sent back to the 
	   server using REST.
	 
	   Tree was added to the method name to help distinguish between different
	   routines that used to have the same name. */
	protected static void updateEntireTree(final String contentType, 
			                                   final HDLmTree topTreeNode) {
		/* Check if the content type reference passed by the caller is null */
		if (contentType == null) {
			String  errorText = "Content type reference passed to updateEntireTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the top tree node passed by the caller is null */
		if (topTreeNode == null) {
			String  errorText = "Top tree node passed to updateEntireTree is null";
			throw new NullPointerException(errorText);
		}
		/* Update the entire tree (as stored in the database) using a call
		   for this purpose */ 
		HDLmTree.passUpdateAllRows(HDLmUtility.getContentString(),
												       topTreeNode, 
												       true);		
	}
	/* Update a tree node value in the node tree. In practice only the 
	   details (and HDLmMod instance) change. */  
	protected static void  updateTreeNode(final HDLmTree treePos, 
			                                  final JsonElement jsonElements) {
		/* Check if the tree position reference passed by the caller is null */
		if (treePos == null) {
			String  errorText = "Tree position reference passed to updateTreeNode is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElements == null) {
			String  errorText = "JSON elements passed to updateTreeNode is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element is a null value */
		if (jsonElements.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElements.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		} 
		/* LOG.info("In HDLmTree.updateTreeNode"); */
		/* We can now construct a node path for the target node */   
	  ArrayList<String>   nodePath = treePos.getNodePath();
	  boolean             logDebugEnabled = LOG.isDebugEnabled();
	  JsonElement         nodeDetails  = HDLmJson.getJsonValue(jsonElements, "details");
	  /* Build a new set of details (and HDLmMod instance) */
	  HDLmMod   newDetails = new HDLmMod(nodeDetails);
	  newDetails.setIfNotSetTimes();
	  /* We can now update the tree node with the new details */
	  treePos.setDetails(newDetails);		 
	  /* We can now update the database with the new details */ 
	  if (logDebugEnabled)
	    LOG.debug("In HDLmTree.updateTreeNode - about to add to pending updates");
		HDLmTree.addPendingUpdates(treePos);
		if (logDebugEnabled)
		  LOG.debug("In HDLmTree.updateTreeNode - about to process pending updates");
		HDLmTree.processPendingUpdates();
		if (logDebugEnabled)
		  LOG.debug("In HDLmTree.updateTreeNode - after processing pending updates");
	}
	/* Update a tree node value in the node tree. In practice only the 
	   details (and HDLmMod instance) change. 
	   
	   This routine is no longer in use. A new routine is used to
	   update the in-memory node tree and a row in the database */  
	protected static void  updateTreeNodeNotUsed(final JsonElement jsonElements) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElements == null) {
			String  errorText = "JSON elements passed to updateTreeNodeNotUsed is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element is a null value */
		if (jsonElements.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElements.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		} 
		/* LOG.info("In HDLmTree.updateTreeNode"); */
		/* We can now construct a node path for the target node */   
	  ArrayList<String>   nodePath = new ArrayList<String>(); 
	  JsonElement         nodeDetails  = HDLmJson.getJsonValue(jsonElements, "details");
	  JsonArray           nodePathJson = HDLmJson.getJsonArray(jsonElements, "nodePath");
	  int                 i;
	  int                 nodePathJsonSize = nodePathJson.size();
	  /* Copy each string from the passed node path to the local node path */
	  for (i = 0; i < nodePathJsonSize; i++) {
	  	String  nodePathEntryStr = nodePathJson.get(i).getAsString();
	  	nodePath.add(nodePathEntryStr);
	  }
	  /* Build a new set of details (and HDLmMod instance) */
	  HDLmMod  newDetails = new HDLmMod(nodeDetails);
	  newDetails.setIfNotSetTimes();
		/* Try to find the tree node for the current node path.
		   This will be the parent of the tree node we are about
		   to create. */   
	  HDLmTree  targetNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
	  		                                           nodePath);		
		if (targetNode == null) {
			String  nodeString = nodePath.toString(); 
			HDLmUtility.logString(nodeString, LOG);
			HDLmUtility.logStackTrace();
			HDLmAssertAction(false, "Null tree node returned by locateTreeNode");
		}
	  targetNode.setDetails(newDetails);		 
	  /* We can now update the tree node with the new details */ 
		HDLmTree.addPendingUpdates(targetNode);
		HDLmTree.processPendingUpdates();
	}
	/* Add a modification level node (rule) to the node tree. The actual node
		 tree has several levels. The intermediate levels (company, Rules, division,
		 site) may or may not exist. Create these levels, if need be, using the
		 information provided by the caller. 
		  
		 Update the rule tree using the information provided by the caller
		 and add the new rule in the correct place. The higher levels may
		 or may not have been created by this routine. 
		 
		 This routine does not appear to be in use. The web socket code that
		 invokes this routine does not appear to be in use.
		 
		 PassThru is part of the method name to help distinguish between different
		 routines that used to have the same name. */
	protected static void  updateTreePassThru(final String contentType,
			                                      final String hostName,
			                                      final JsonElement jsonElement) {
		/* Check if the content type string reference passed by the caller is null */
		if (contentType == null) {
			String  errorText = "Content type reference passed to updateTreePassThru is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name value string reference passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string value reference passed to updateTreePassThru is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to updateTreePassThru is null";
			throw new NullPointerException(errorText);
		}
		/* Build all of the intermediate levels as need be. This call will
		   update the node tree (HDLmTree) in memory and send any new nodes
		   to the database as need be. */ 
	  HDLmModResponse     modResponse = HDLmTree.buildLevelsGetSetTree(hostName,
	 		                                                               HDLmFreshCopy.FRESHCOPYNO,
	 		                                                               HDLmTreeTypes.RULES); 
	  HDLmTree            topTreeNode = modResponse.getTreeNode();
	  ArrayList<String>   nodePath = modResponse.getNodePath();
		if (topTreeNode == null) {
			HDLmAssertAction(false, "Null modifications tree returned by buildLevelsGetSetTree");
		} 
		/* Try to find the tree node for the current node path.
		   This will be the parent of the tree node we are about
		   to create. */   
	  HDLmTree  nodeParent = HDLmTree.locateTreeNode(topTreeNode, nodePath);		
		if (nodeParent == null) {
			String  nodeString = nodePath.toString(); 
			HDLmUtility.logString(nodeString, LOG);
			HDLmUtility.logStackTrace();
			HDLmAssertAction(false, "Null tree node returned by locateTreeNode");
		}
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Make a few changes to the JSON element tree for the conversion 
		   below. This conversion works pretty from the tree node, but not
		   as well for the details. The details are handled later. */
	  String  treeTypeStr = HDLmJson.getJsonString(jsonElement, "type");
	  treeTypeStr = treeTypeStr.toUpperCase();
	  HDLmJson.setJsonString(jsonElement, "type", treeTypeStr);		
		/* Get the Java object from the JSON element */ 
		Gson      gsonInstance = HDLmMain.gsonMain;
		HDLmTree  newTreeNode = gsonInstance.fromJson(jsonElement, HDLmTree.class);
	  /* Make sure the new tree node was actually created */
		if (newTreeNode == null) 
			HDLmAssertAction(false, "Null tree node returned by fromJson converter");
		/* We can now try to build the details correctly. The details were 
		   already built by the call above. However, they are wrong. The
		   correct details will be built by the call below. */
		HDLmMod   newModNode = HDLmTree.buildModDetailsFromJson(jsonElement.getAsJsonObject()); 
		if (newModNode == null) 
			HDLmAssertAction(false, "Null new modifications returned by buildModDetailsFromJson converter");
		newTreeNode.setDetails(newModNode);   
	  /* Add the (possibly) newly created tree node to the tree node tree.
	     This should only be done after we have the final name for the tree
	     node and the modification. At this point we have the final name
	     for both. */
		nodeParent.addOrReplaceChild(newTreeNode);
		/* The call below sends the updated rule tree back to the server.
		   The call also sets or resets the tree top to the new value. 
		   The new tree will include the tree node possibly added above.  */  
		HDLmTree.replaceEntireTree(contentType, 
	 	                           topTreeNode);
	  /* Save the updated tree node in a common location. This has
	     the effect of making sure that the updated node tree is 
	     used for all future work. */  
	  HDLmTree.setNodePassTreeTop(topTreeNode);
	} 
}
package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruList short summary.
 *
 * HDLmPassThruList description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each ignore-list. An ignore-List is
   created so that lines in a report will be ignored. Each line
   in an ignore-list will cause zero or more lines in a report
   to be ignored. */ 
/* An instance of this class is created to store an ignore-list. 
   Each entry in an ignore-list specifies what lines if a report  
   should be ignored. */ 
/* An instance of this class is created for each list. The 
	 name of this class notwithstanding, this class does not do 
	 any pass-through processing. The name was picked for consistency
	 with other related classes. */ 
public class HDLmPassThruList extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruList() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
  	super();
  	/* Declare and define a few variables */
  	Instant   currentTimestamp = Instant.now(); 
  	/* Set a few fields in the new report instance */
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.LIST;
  	countIgnores = 0;
  	ignoreEntries = new TreeMap<String, HDLmPassThruIgnore>();
  	if (ignoreEntries == null)
  		HDLmAssertAction(false, "New TreeMap<> not created for ignore-list entries");  
  }
	/* This is a copy constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
	protected HDLmPassThruList(final HDLmPassThruList oldList) { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		created = oldList.created;
		lastModified = oldList.lastModified;
		associatedNodeType = oldList.associatedNodeType;
		countIgnores = oldList.countIgnores;
		ignoreEntries = new TreeMap<String, HDLmPassThruIgnore>();
		if (ignoreEntries == null)
			HDLmAssertAction(false, "New TreeMap<> not created for ignore-list entries");
		for (Map.Entry<String, HDLmPassThruIgnore> entry : oldList.ignoreEntries.entrySet()) {
			HDLmPassThruIgnore  ignoreListEntry = entry.getValue();
			String              ignoreListEntryName = entry.getKey();
			ignoreEntries.put(ignoreListEntryName, ignoreListEntry);
		}
		/* Copy all of the base class fields for the current instance */
		copyModFields(oldList);
	}
	/* This is one of the constructors for the list (ignore-list) definition
	   class. It must be passed a JSON element that contains all of the details
     of the list (ignore-list) definition. */
	protected HDLmPassThruList(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build list definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Integer   curInteger;
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Create the ignore-list entry collection that will be needed later */
   	ignoreEntries = new TreeMap<String, HDLmPassThruIgnore>();
  	if (ignoreEntries == null)
  		HDLmAssertAction(false, "New Treemap<> not created for list");
		/* Set the error count to zero. The error count is incremented each time an
			 error is detected. If the final error count (for the current definition) is
			 greater than zero, the current definition object is disabled (the enabled
			 field is set false). Note that a reference is used below so that the error
			 count can be updated by the routines called using error count.*/
	  MutableInt   errors = new MutableInt(0);
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
	 	  HDLmAssertAction(false, "JSON element used to build list definition is JSON null");
	  }
	  JsonObject jsonObject = jsonElement.getAsJsonObject();
	  Set<String> jsonKeys = jsonObject.keySet();
	  /* Get the standard class instance variables */
	  HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	 	  	                                                                    HDLmTreeTypes.LIST,
	 	  	                                                                    HDLmGetComments.GETCOMMENTSYES,
																																			      HDLmGetCreated.GETCREATEDYES,
																																			      HDLmGetLastModified.GETLASTMODIFIEDYES,
																																			      HDLmGetPassThruStatus.GETSTATUSNO,
																																			      HDLmOptEnabled.OPTENABLEDYES,
																																		        HDLmOptExtra.OPTEXTRAYES,
																																		        HDLmGetUpdated.GETUPDATEDYES);
	  if (response == null) {
	   	String  errorText = "Null response from build standard fields routine";
	 	  HDLmAssertAction(false, errorText);
	  }
	  /* Update the error count with the response value */
	  errors.add(response.getErrorCount()); 
	  /* Extract the fields from the build standard fields response */
	  setName(response.getName());
		/* Get and check the current type */
		associatedNodeType = response.getAssociatedNodeType();
		/* Get a few fields from the JSON object */
		Boolean enabledBoolean = response.getEnabled();
		if (enabledBoolean != null)
		  setEnabled(enabledBoolean);
		/* Try to get the comments value from the JSON element. Note that
		   the call below will never report an error unless the comments
		   field is actually missing from the JSON. This does not appear
		   to be true in any actual case. Note that the comments are
		   optional. Skip this code is we don't have any comments. */
		String  commentsInfo = response.getComments();		 
		if (commentsInfo != null && !StringUtils.isWhitespace(commentsInfo)) {
		  setComments(commentsInfo);
		}	 
		/* Try to get the extra information value from the JSON element.
		   Note that the call below will never report an error unless the
		   extra information field is actually missing from the JSON.
		   This does not appear to be true in any actual case. */
		String extraInfo = response.getExtra();  
		if (extraInfo != null && !StringUtils.isWhitespace(extraInfo)) {
			setExtra(extraInfo);
		}
		/* Get the created date and time and use them to set an instance field */
		created = response.getCreated();
		/* Get the last modified date and time and use them to set an instance field */
		lastModified = response.getLastModified();
		/* Get an integer value and use it to set an instance field */ 
	  curInteger = HDLmMod.modFieldInteger(editorType, errors, 
	                                       jsonObject, jsonKeys, 
		                                     "countIgnores", 
		                                     HDLmReportErrors.REPORTERRORS);
	  countIgnores = curInteger;
	  /* We don't really want to use the count of ignore-list entries we 
	     obtained from the JSON. We really need to use the ignore-list 
	     entries count we get by adding each ignore-list entry to the
	     current ignore-list. */ 
    countIgnores = 0; 
		/* Mark the current list (set of ignore-list entries) definition
		   object as disabled if the error count was greater than zero. 
		   This is actually done by setting the enabled field to false. */
		if (errors.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
     need to be obtained and/or set */ 
	private HDLmTreeTypes                         associatedNodeType = null;
  private Integer                               countIgnores = 0;  
  private TreeMap<String, HDLmPassThruIgnore>   ignoreEntries = null;
  /* This routine adds a new ignore-list entry to an ignore-list */
  protected void         addIgnoreEntry(final HDLmPassThruIgnore ignoreEntry,
  		                                  HDLmStartupMode startupMode) {
		/* Check if the ignore-list entry reference passed by the caller is null */
	  if (ignoreEntry == null) {
 	    String  errorText = "Ignore line reference passed to addIgnoreLine is null";
	    throw new NullPointerException(errorText);
    }
		/* Check if the startup mode value passed by the caller is null */
		if (startupMode == null) {
			String   errorText = "Startup mode value passed to addIgnoreEntry is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the startup mode passed by the caller is invalid */
		if (startupMode == HDLmStartupMode.NONE) {
		  HDLmAssertAction(false, "Startup mode value passed to addIgnoreEntry is invalid");
		}
		/* Reset the last modified timestamp for the current ignore-list */
		if (startupMode == HDLmStartupMode.STARTUPMODENO)
	  	lastModified = Instant.now(); 
		String  ignoreEntryName = ignoreEntry.getName();
  	ignoreEntries.put(ignoreEntryName, ignoreEntry);
  	countIgnores++;
  }
	/* This routine will build a dummy ignore-list and return the dummy 
     ignore-list to the caller */
	protected static HDLmPassThruList  buildDummyIgnoreList() {
	 /* Build an ignore-list */
	 HDLmPassThruList  ignoreList = new HDLmPassThruList();
	 if (ignoreList == null)
	 	 HDLmAssertAction(false, "New ignore-list was not created");
	 /* Build a dummy ignore-list entry */
	 HDLmPassThruIgnore  ignoreEntry = HDLmPassThruIgnore.buildDummyIgnoreLine();
	 /* Add the dummy ignore-list entry to the dummy ignore-list */
	 ignoreList.addIgnoreEntry(ignoreEntry, HDLmStartupMode.STARTUPMODENO);	
	 return ignoreList;		
	}
	/* This routine builds a tree node for a list (just one ignore-list) 
	   and returns the final list (just one ignore-list) tree node to the
	   caller. The list (just one ignore-list) reference is used to set
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruList newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through list (just one ignore-list) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "List reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current list (just one ignore-list) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.LIST;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  listTree = new HDLmTree(nodeType,
				                              nodeTooltip,
				                              nodePath);
		if (listTree == null) {
			String  errorText = "New list tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the list (just one ignore-list) instance as the details of the new tree node */
		listTree.setDetails(newValue);		
		return listTree;	
	}
	/* This routine builds a set of tree nodes for a list (set of
	   ignores) and returns the final list (set of ignores) tree node 
	   to the caller. Other routines are used to build each of the
	   ignore nodes (one individual ignore) that are also associated with
	   a list (set of ignores). The list (set of ignores) reference is 
	   used to set the details of the new tree node. */
	protected static HDLmTree  buildTreeExtended(ArrayList<String> oldNodePath, 
	                                             HDLmPassThruList newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the pass-through list (set of ignores) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "List reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
		}
		/* Build a new tree node with the correct information */
		HDLmTree  listTree = HDLmPassThruList.buildTree(oldNodePath,
				                                            newValue);
		if (listTree == null) {
		  String  errorText = "New list tree node was not created";
		  HDLmAssertAction(false, errorText);
		}	
		/* Get the new node path that includes the list (set of ignores) 
		   tree node */
		ArrayList<String>   newNodePath = listTree.getNodePath();
		/* Add all of the individual ignores as children of the list (set of
		   ignores) tree */ 
		for (Map.Entry<String, HDLmPassThruIgnore> entry : newValue.getIgnoreEntries().entrySet()) {
			HDLmPassThruIgnore  currentIgnore = entry.getValue();
			/* Try to build a tree node for the current ignore-list entry */
			HDLmTree  currentIgnoreTree = HDLmPassThruIgnore.buildTree(newNodePath,
	                                                               currentIgnore);
			if (currentIgnoreTree == null) {
			  String  errorText = "New ignore-list entry tree node was not created";
			  HDLmAssertAction(false, errorText);
			}	
			/* Add the new tree node to the parent */
			listTree.getChildren().add(currentIgnoreTree);			
		}
		return listTree;	
	}
  /* The routine below checks if the results of a verification test 
	   should be ignored or not, based on all of the ignore lines. If the
	   current test should be ignored, then true is returned to the caller.
	   If the current test should not be ignored, then false is returned
	   to the caller. 
	 
	   Note that the current report line is ignored if even one ignore line 
	   indicates shows that the current report line should be ignored. The     
	   current report line will not be ignored if all of the ignore lines
	   indicated that the current report line should not be ignored. */ 
	protected boolean      checkReportLine(final HDLmPassThruLine reportLine) {
		/* Declare and define a few fields */
		boolean   rv = false;
		/* Check each of the ignore lines against the current report line */
		for (Map.Entry<String, HDLmPassThruIgnore> entry : ignoreEntries.entrySet()) {
			HDLmPassThruIgnore  ignoreListEntry = entry.getValue();
			/* Check if the current ignore-list entry shows that the current report
		     line should be ignored */
		  if (ignoreListEntry.checkReportLine(reportLine) == true) 
		  	return true;
		}		
		return rv;
	}
  /* Get the value of the associated node type field and return it to the caller */
  protected HDLmTreeTypes  getAssociatedNodeType() {
  	return associatedNodeType;
  }
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  } 
	/* Get the tree map of ignore-list entries and return it to the caller */
	protected TreeMap<String, HDLmPassThruIgnore>  getIgnoreEntries() {
		return ignoreEntries;
	}
  /* This routine returns the current number of ignores for the 
     current list. No other changes are made. */
  protected int          getIgnoresCount() {
    return countIgnores;
  } 
	/* This routine returns a reference to the last modified instant */  
  protected Instant      getLastModified() {
	  return lastModified;		
  } 
  /* This routine sets the associated node type of this class
     instance to a null value */  
	protected final void   setAssociatedNodeTypeNull() { 
	  lastModified = Instant.now();
	  associatedNodeType = null;
	}
	/* This routine sets the reference to the created instant */  
  protected final void   setCreated(final Instant newCreated) {
	  /* Check if the created reference passed by the caller is null */
	  if (newCreated == null) {
	    String  errorText = "Created reference passed to setCreated is null";
	    throw new NullPointerException(errorText);
	  }
	  created = newCreated;		
  }  
	/* This routine sets the ignore entries reference to a null value */  
  protected final void   setIgnoreEntriesNull() {
  	lastModified = Instant.now(); 
	  ignoreEntries = null; 		
  } 
	/* This routine sets the ignores count to a value passed by the caller */  
  protected final void   setIgnoresCount(final int newIgnoresCount) {
	  /* Check if the ignores count passed by the caller is valid */
	  if (newIgnoresCount < 0) {
	    String  errorText = "Ignores count passed to setIgnoresCount is invalid";
	    throw new IllegalArgumentException(errorText);  	
	  }
	  lastModified = Instant.now(); 
	  countIgnores = newIgnoresCount;		
  } 
	/* This routine sets the reference to the last modified instant */  
  protected final void   setLastModified(final Instant newLastModified) {
	  /* Check if the last modified reference passed by the caller is null */
	  if (newLastModified == null) {
	    String  errorText = "Last modified reference passed to setLastModified is null";
	    throw new NullPointerException(errorText);
	  }
	  lastModified = newLastModified;		
  } 
}
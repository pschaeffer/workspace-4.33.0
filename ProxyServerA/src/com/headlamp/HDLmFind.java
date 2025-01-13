package com.headlamp;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * HDLmFind short summary.
 *
 * HDLmFind description.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmFind {
	/* The static field below contains the one and only instance of the
     empty class */ 
  protected static final ArrayList<HDLmFind>  emptyFindsInstance = new ArrayList<HDLmFind>();  
	/* All instances of the HDLmMod class have a standard set of fields*/	 
	private String   tag = null;
	private String   attribute = null;
	private String   value = null;
  /* This constructor builds a find instance from a set of values 
     passed by the caller */ 
	protected HDLmFind(String newTag, String newAttribute, String newValue) {
		/* Note that any of the values passed to this routine can be null. 
		   That is not an error condition. */ 
		this.tag = newTag;
		this.attribute = newAttribute;
		this.value = newValue;
	}
  /* This constructor copies all of the fields from another find
     instance */
	protected HDLmFind(HDLmFind oldFind) {
		if (oldFind == null) {
			String   errorText = "Old find reference passed to find constructor is null";
			throw new NullPointerException(errorText);		
		}
		this.tag = oldFind.getTag(); 
		this.attribute = oldFind.getAttribute();
		this.value = oldFind.getValue();
	}
  /* Find all of the subnodes that match one level of the search.
     The caller provides an existing node list and a find object
     for one level of the search. This routine scans all of the
     nodes in the existing node list and finds the subnodes that
     match the find criterion passed by the caller. A new node
     list is returned to the caller. */
  protected static Elements findOneLevel(Elements nodeList, HDLmFind find) {
		if (nodeList == null) {
			String   errorText = "Node list passed to findOneLevel is null";
			throw new NullPointerException(errorText);			
		}
		if (find == null) {
			String   errorText = "Find reference passed to findOneLevel is null";
			throw new NullPointerException(errorText);		
		}
	  Elements   outElements = new Elements();
	  Class<?>   elementClass;
	  String     findAttribute = find.getAttribute();
	  if (findAttribute == null)
	  	findAttribute = "";
	  String     findTag = find.getTag();
	  if (findTag == null)
	  	findTag = "";
	  String     findValue = find.getValue();
	  if (findValue == null)
	  	findValue = "";
	  for (Element node : nodeList) {
	    /* Try to search by ID. This should be the fastest and best
	       approach. ID values are supposed to be unique for an entire
	       HTML page. Hence, this code should return just one value.
	       Note that the code below does not test the tag value in
	       the first stage. */
	  	elementClass = node.getClass();
	  	if (elementClass == Document.class &&
	        findAttribute.equals("id")    && 
	        !findValue.equals("")) {
	      /* Search by ID value. The call below may or may not return an
	         array. It may just return one node. Note that the documentation
	         says that the call below will only return one element. */
	      Element  newNode = node.getElementById(findValue);
	      if (newNode != null) {
	        if (!findTag.equals("")) {
            String   newNodeTag = newNode.tagName();      	
	          if (findTag.equals(newNodeTag)) {
	          	if (!outElements.contains(newNode))
	              outElements.add(newNode);
	          }
	        }
	        else {
	        	if (!outElements.contains(newNode))
	        	  outElements.add(newNode);
	        }
	        continue;
	      }
	    }
	    /* Try to search by tag name. This is only possible if the
	       tag name is actually set. If the tag name is not set, then
	       we will have to search some other way. */
	    if (!findTag.equals("")) {
	      /* Search by tag name */
	      Elements   newNodesList = node.getElementsByTag(findTag);
	      /* Now that we have a list of nodes with the correct tag
	         name, we need to keep checking */
	      if (!findAttribute.equals("") &&
	          !findValue.equals("")) {
	        for (Element newNode : newNodesList) {
	          if (!newNode.hasAttr(findAttribute))
	            continue;
	          if (!newNode.attr(findAttribute).equals(findValue)) 
	          	continue;
	          if (!outElements.contains(newNode))
	            outElements.add(newNode);
	        }
	      }
	      /* Since the attribute name and value were not set, we can
	         just copy all of the nodes into the output node list */
	      else {
	        for (Element newNode : newNodesList) {
	        	if (!outElements.contains(newNode))
	        	  outElements.add(newNode);
	        }
	      }
	    }
	    /* At this point, we must scan the subnodes of the current node.
	       This is messy and slow, but we don't have any other choice. */
	    else {
	      /* Get the child nodes of the current node */
	      Elements children = node.getAllElements();
	      for (Element child : children) {
	      	elementClass = child.getClass();
	        if (elementClass == Element.class) {
	          if (!child.hasAttr(findAttribute))
	            continue;
	          if (!child.attr(findAttribute).equals(findValue))
	            continue;
	        }
	        if (!outElements.contains(child))
	          outElements.add(child);
	      }
	    }
	  }
	  return outElements;
	}
  /* Get the attribute value from a HDLm find and return it to the caller */
  protected String getAttribute() {
  	return attribute;
  }
  /* Get the tag value from a HDLm find and return it to the caller */
  protected String getTag() {
  	return tag;
  }
  /* Get the value from a HDLm find and return it to the caller */
  protected String getValue() {
  	return value;
  }
  /* Find a set of HTML nodes that match the find criteria. The find
		 criteria may specify a single level search or a multilevel search.
		 A multilevel search is used to find a nested HTML node. The list
		 of matching nodes is returned to the caller. The list of matching
		 nodes may be empty or have more than one entry. The caller always
		 passes an array of find instances. The array will have just one
		 entry for a single level search and multiple entries for a multilevel
		 search. */
	protected static Elements processFindsArray(Document htmlDom, ArrayList<HDLmFind> finds) {
		if (htmlDom == null) {
			String   errorText = "HTML DOM passed to processFindsArray is null";
			throw new NullPointerException(errorText);			
		}
		if (finds == null) {
			String   errorText = "Finds array passed to processFindsArray is null";
			throw new NullPointerException(errorText);		
		}
		Elements   nodeList = new Elements();
		nodeList.add(htmlDom);
 	  /* Elements   nodeList = htmlDom.getAllElements(); */
	  /* Process each level of the search. Of course, there will typically
	     only be one level. */
	  for (HDLmFind find : finds) {
	    nodeList = HDLmFind.findOneLevel(nodeList, find);
	  }
	  return nodeList;
	}
  /* Set the attribute value for a HDLm find */
  protected void setAttribute(String newAttribute) {
  	this.attribute = newAttribute;
  }
  /* Set the tag value for a HDLm find */
  protected void setTag(String newTag) {
  	this.tag = newTag;
  }
  /* Set the value for a HDLm find */
  protected void setValue(String newValue) {
  	this.value = newValue;
  }
}
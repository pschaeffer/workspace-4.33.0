package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruLists short summary.
 *
 * HDLmPassThruLists description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each set of ignore-lists. An ignore-List
   is created so that lines in a report will be ignored. Each line
   in an ignore-list will cause zero or more lines in a report
   to be ignored. */ 
/* An instance of this class is created to store a set of ignore-lists. 
   Each entry is an ignore-list with zero, one, or more ignore-list 
   entries. Each company has one set of ignore-lists. */ 
/* An instance of this class is created for each set of lists. 
   the name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruLists extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruLists() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
  	super();
  	/* Declare and define a few variables */
  	Instant   currentTimestamp = Instant.now();
  	/* Set a few fields in the new report instance */
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.LISTS;
  	countLists = 0;
  	ignoreLists = new TreeMap<String, HDLmPassThruList>();
  	if (ignoreLists == null)
  		HDLmAssertAction(false, "New TreeMap<> not created for a set of ignore-lists");  
  }
	/* This is one of the constructors for the lists (set of ignore-lists) definition
	   class. It must be passed a JSON element that contains all of the details
     of the lists (set of ignore-lists) definition. */
	protected HDLmPassThruLists(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build lists definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Integer   curInteger;
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Create the ignore-lists collection that will be needed later */
   	ignoreLists = new TreeMap<String, HDLmPassThruList>();
  	if (ignoreLists == null)
  		HDLmAssertAction(false, "New Treemap<> not created for lists");
		/* Set the error count to zero. The error count is incremented each time an
			 error is detected. If the final error count (for the current definition) is
			 greater than zero, the current definition object is disabled (the enabled
			 field is set false). Note that a reference is used below so that the error
			 count can be updated by the routines called using error count.*/
	  MutableInt   errorCounter = new MutableInt(0);
		/* Build an array list for error message strings. Each error
	     message is stored in this array list. */
		ArrayList<String>   errorMessages = new ArrayList<String>();
		if (errorMessages == null) {
			String  errorText = "Error message ArrayList allocation in HDLmPassThruLists constructor is null";
			throw new NullPointerException(errorText);
		}
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
	 	  HDLmAssertAction(false, "JSON element used to build lists definition is JSON null");
	  }
	  JsonObject jsonObject = jsonElement.getAsJsonObject();
	  Set<String> jsonKeys = jsonObject.keySet();
	  /* Get the standard class instance variables */
	  HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	 	  	                                                                    HDLmTreeTypes.LISTS,
	 	  	                                                                    HDLmGetComments.GETCOMMENTSNO,
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
	  errorCounter.add(response.getErrorCount()); 
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
	  curInteger = HDLmMod.modFieldInteger(editorType, 
	  		                                 errorCounter, 
	  		                                 errorMessages,
	                                       jsonObject, 
	                                       jsonKeys, 
		                                     "countLists", 
		                                     HDLmReportErrors.REPORTERRORS);
	  countLists = curInteger;
	  /* We don't really want to use the count of ignore-lists we obtained from
       the JSON. We really need to use the ignore-list count we get by adding
       each ignore-list to the set of ignore-lists. */ 
    countLists = 0; 
		/* Mark the current lists (set of ignore-lists) definition
		   object as disabled if the error count was greater than zero. 
		   This is actually done by setting the enabled field to false. */
		if (errorCounter.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
     need to be obtained and/or set */ 
	private HDLmTreeTypes   associatedNodeType = null;
  private Integer         countLists = 0;  
  private TreeMap<String, HDLmPassThruList>  ignoreLists = null;  
  /* This routine does the work of adding a new ignore-list to the list 
	   of ignore-lists for a company. This routine does not do any other 
	   work. The ignore-list may or may not be empty and the ignore-list
	   may be valid or invalid. */ 
	protected void         addIgnoreList(final String currentIgnoreListName,			
		                                   final HDLmPassThruList currentIgnoreList,
		                                   final HDLmStartupMode startupMode) {
		/* Check if the ignore-list name string reference passed by the caller is null */
		if (currentIgnoreList == null) {
		  String  errorText = "Ignore-list name string reference passed to addIgnoreList is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the ignore-list reference passed by the caller is null */
		if (currentIgnoreList == null) {
		  String  errorText = "Ignore-list instance reference passed to addIgnoreList is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the startup mode value passed by the caller is null */
		if (startupMode == null) {
			String   errorText = "Startup mode value passed to addIgnoreList is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the startup mode passed by the caller is invalid */
		if (startupMode == HDLmStartupMode.NONE) {
		  HDLmAssertAction(false, "Startup mode value passed to addIgnoreList is invalid");
		}
		/* Reset the last modified timestamp for the current company */
		if (startupMode == HDLmStartupMode.STARTUPMODENO)
		  lastModified = Instant.now(); 
		/* Add the new ignore-list to the current company */
		ignoreLists.put(currentIgnoreListName, currentIgnoreList);
		countLists++;
	}
	/* This routine builds a tree node for a lists (set of ignore-lists) 
	   and returns the final lists (set of ignore-lists) tree node to the
	   caller. The lists (set of ignore-lists) reference is used to set
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruLists newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through lists (set of ignore-lists) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Lists reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current lists (set of ignore-lists) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.LISTS;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  listsTree = new HDLmTree(nodeType,
				                               nodeTooltip,
				                               nodePath);
		if (listsTree == null) {
			String  errorText = "New lists tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the lists (set of ignore-lists) instance as the details of the new tree node */
		listsTree.setDetails(newValue);		
		return listsTree;	
	}
  /* This routine returns the associated node type of the current instance.  
     No other changes are made. */
	protected HDLmTreeTypes  getAssociatedNodeType() {
	  return associatedNodeType;
	}
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  } 
  /* This routine returns a reference to the ignore-lists for the 
     current set of ignore-lists. No other changes are made. */
  protected TreeMap<String, HDLmPassThruList>  getIgnoreLists() {
    return ignoreLists;
  } 
  /* This routine returns the current number of ignore-lists for the 
     current lists (set of ignore-lists). No other changes are made. */
  protected int          getIgnoreListsCount() {
    return countLists;
  } 
	/* This routine returns a reference to the last modified instant */  
  protected Instant      getLastModified() {
	  return lastModified;		
  } 
	/* This routine returns the lists count */  
  protected int          getListsCount() {
	  return countLists;		
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
  /* This routine sets the associated node type of this class
     instance to a null value */  
	protected final void   setAssociatedNodeTypeNull() { 
	  lastModified = Instant.now(); 
	  associatedNodeType = null;
	}
	/* This routine sets the ignore lists reference to a null value */  
  protected final void   setIgnoreListsNull() {
  	lastModified = Instant.now();
	  ignoreLists = null; 		
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
	/* This routine sets the lists count to a value passed by the caller */  
  protected final void   setListsCount(final int newListsCount) {
	  /* Check if the lists count passed by the caller is valid */
	  if (newListsCount < 0) {
	    String  errorText = "Lists count passed to setListsCount is invalid";
	    throw new IllegalArgumentException(errorText);  	
	  }
	  lastModified = Instant.now();
	  countLists = newListsCount;		
  } 
}
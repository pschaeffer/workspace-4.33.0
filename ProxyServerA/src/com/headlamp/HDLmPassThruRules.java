package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruRules short summary.
 *
 * HDLmPassThruRules description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, only one instance of
   this class will be created for each company. The one instance
   of this class (for each company) is (will be) the parent to all
   of the rules that pertain to that company. */  
/* An instance of this class is created for each company, to be the
   parent of all of the rules for that company. In actuality, this
   class will be the parent to each of the divisions for that 
   company. */ 
/* An instance of this class is created for each set of rule.  
   The name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruRules extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruRules() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
	  Instant   currentTimestamp = Instant.now();
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.RULES;
  	countDivisions = 0;
  }
	/* This is one of the constructors for the rules definition class.
	   It must be passed a JSON element that contains all of the details
 	   of the rules definition. */
	protected HDLmPassThruRules(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build rules definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Integer   curInteger;
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Set the error count to zero. The error count is incremented each time an
			 error is detected. If the final error count (for the current definition) is
			 greater than zero, the current definition object is disabled (the enabled
			 field is set false). Note that a reference is used below so that the error
			 count can be updated by the routines called using error count.*/
	  MutableInt   errors = new MutableInt(0);
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
		  HDLmAssertAction(false, "JSON element used to build comapnies definition is JSON null");
	  }
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    Set<String> jsonKeys = jsonObject.keySet();
    /* Get the standard class instance variables */
    HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	  	                                                                      HDLmTreeTypes.RULES,
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
	  errors.add(response.getErrorCount()); 
	  /* Extract the fields from the build standard fields response */
	  setName(response.getName());
	  /* Get the current type */
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
		                                     "countDivisions", 
		                                     HDLmReportErrors.REPORTERRORS);
	  countDivisions = curInteger;
	  /* We don't really want to use the count of division we obtained from
       the JSON. We really need to use the divisions count we get by adding
       each division to the set of division. */ 
    countDivisions = 0; 
		/* Mark the current report definition object as disabled
		   if the error count was greater than zero. This is actually
		   done by setting the enabled field to false. */
		if (errors.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
	   need to be obtained and/or set */ 
	private HDLmTreeTypes       associatedNodeType = null;
  private Integer             countDivisions = 0;
  /* This routine builds a tree node for a rules (rules node) 
     and returns the final rules (rules node) tree node to the
	   caller. The rules (rules instance) reference is used to set 
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruRules newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through rules (rules instance) reference 
		   passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Rules reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current rules (rules instance) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.RULES;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  rulesTree = new HDLmTree(nodeType,
                                       nodeTooltip,
                                       nodePath);
    if (rulesTree == null) {
      String  errorText = "New rules tree node was not created";
      HDLmAssertAction(false, errorText);
    }
		/* Use the rules (rules instance) instance as the details 
		   of the new tree node */
		rulesTree.setDetails(newValue);		
		return rulesTree;	
  }
  /* This method adds a division to the existing set of divisions 
	   (zero, one, or more). This routine will be called to add a
	   division to a rules node. Since only actual rules (and certain
	   other tree nodes) have details, the caller passes a tree element,
	   not a modification (an actual rule). */
	protected void         addDivision(HDLmTree newDivision, HDLmStartupMode startupMode) {
		/* Check if the pass-through division reference passed by the caller is null */
		if (newDivision == null) {
		  String  errorText = "New division reference passed to addDivision is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the startup mode passed by the caller is null */
		if (startupMode == null) {
			String  errorText = "Startup mode value in addDivision is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the startup mode passed by the caller is invalid */
		if (startupMode == HDLmStartupMode.NONE) {
			HDLmAssertAction(false, "Startup mode value in addDivision is invalid");
		}
		/* Add the new division to the set of divisions */	
		countDivisions++;
	  /* Reset the last modified date and time */
		if (startupMode == HDLmStartupMode.STARTUPMODENO)
	    lastModified = Instant.now();
	} 
  /* Get the value of the associated node type field and return 
     it to the caller */
  protected HDLmTreeTypes  getAssociatedNodeType() {
	  return associatedNodeType;
  }	
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  } 
	/* This routine returns the divisions count */  
  protected int          getDivisionsCount() {
	  return countDivisions;		
  } 
	/* This routine returns a reference to the last modified instant */  
  protected Instant      getLastModified() {
	  return lastModified;		
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
	/* This routine sets the divisions count to a value passed by the caller */  
  protected final void   setDivisionsCount(final int newDivisionsCount) {
	  /* Check if the divisions count passed by the caller is valid */
	  if (newDivisionsCount < 0) {
	    String  errorText = "Divisions count passed to setDivisionsCount is invalid";
	    throw new IllegalArgumentException(errorText);  	
	  }
	  lastModified = Instant.now();
	  countDivisions = newDivisionsCount;		
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
  /* This routine sets the associated node type of this class
	   instance to a null value */  
	protected final void   setAssociatedNodeTypeNull() { 
	  lastModified = Instant.now();
	  associatedNodeType = null;
	}
}	
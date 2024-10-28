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
 * HDLmPassThruSite short summary.
 *
 * HDLmPassThruSite description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each site under a site node */
/* An instance of this class is created for each site. The 
   name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
/* An instance of this class is created for each site. The 
   name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruSite extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruSite() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
  	/* Declare and define a few variables */
  	Instant   currentTimestamp = Instant.now();
  	/* Set a few fields in the new report instance */
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.SITE;
  }
	/* This is one of the constructors for this class. It doesn't do
     much. However, the name will be set to the value passed by 
     the caller. All of the other fields of this class will be 
     set to the default values specified. */
  protected HDLmPassThruSite(final String siteName) { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		/* Check if the site name passed by the caller, is null */
		if (siteName == null) {
			String  errorText = "Site name string reference passed to the HDLmPassThruSite constructor is null";
			throw new NullPointerException(errorText);		
		}
  	/* Declare and define a few variables */
  	Instant  currentTimestamp = Instant.now();
  	/* Set a few fields in the new report instance */
  	created = currentTimestamp;
  	lastModified = currentTimestamp; 
  	associatedNodeType = HDLmTreeTypes.SITE;
  	countRulesOrValues = 0;
  	setName(siteName);
  }
	/* This is one of the constructors for the site definition
	   class. It must be passed a JSON element that contains all 
	   of the details of the site definition. */
	protected HDLmPassThruSite(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build site definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Integer   curInteger = null;
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
		  HDLmAssertAction(false, "JSON element used to build site definition is JSON null");
	  }
	  JsonObject    jsonObject = jsonElement.getAsJsonObject();
	  Set<String>   jsonKeys = jsonObject.keySet();
	  /* Get the standard class instance variables */
	  HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	 		                                                                      HDLmTreeTypes.SITE,
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
		                                     "countRulesOrValues", 
		                                     HDLmReportErrors.REPORTERRORS);
	  countRulesOrValues = curInteger;
		/* Mark the current site definition object as disabled if  
		   the error count was greater than zero. This is actually 
		   done by setting the enabled field to false. */
		if (errors.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
     need to be obtained and/or set */ 
	private HDLmTreeTypes   associatedNodeType = null;	
	private Integer         countRulesOrValues = 0;
	/* This method adds a rule to the existing set of rules
	   (zero, one, or more). This routine will be called to add 
	   a rule to a site node. Since only actual rule values 
	   (and certain other tree nodes) have details, the caller
	   passes a tree element, not a rule value. */
	protected void         addRule(HDLmTree newRule, HDLmStartupMode startupMode) {
		/* Check if the rule reference passed by the caller is null */
		if (newRule == null) {
		  String  errorText = "New rule reference passed to addRule is null";
		  throw new NullPointerException(errorText);
	  }
	 	/* Check if the startup mode passed by the caller is null */
		if (startupMode == null) {
			String  errorText = "Startup mode value in addRule is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the startup mode passed by the caller is invalid */
		if (startupMode == HDLmStartupMode.NONE) {
			HDLmAssertAction(false, "Startup mode value in addRule is invalid");
		}
		/* Add the new rule or value to the count of rules or values */	
		countRulesOrValues++;
	  /* Reset the last modified date and time */
		if (startupMode == HDLmStartupMode.STARTUPMODENO)
	    lastModified = Instant.now();
	} 	
	/* This routine builds a default site and returns the
	   site to the caller */ 
	protected static HDLmPassThruSite buildDefaultSite() {
		/* Declare and define a few variables */
		String  siteName = HDLmDefines.getString("HDLMSITENODENAME");
		if (siteName == null) {
			String   errorFormat = "Define value for site node name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSITENODENAME");
			HDLmAssertAction(false, errorText);
		}
		/* Create a new site instance */
		HDLmPassThruSite  newSite = new HDLmPassThruSite(siteName);
		if (newSite == null) {
			String errorText = "New site instance was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Set the type of the HDLmMod instance to site. 
	     This is not a real rule type. However, it does
	     avoid certain problems later.   	 */
	 newSite.setType(HDLmModTypes.SITE);
	 /* Set the enablement status of the HDLmMod instance 
	    to true. This avoids an error message later. */ 
	 newSite.setEnabled((Boolean) true); 
	 /* Set the updated status of the HDLmMod instance 
	    to false. This avoids an error message later. */ 
	 newSite.setDummyUpdated((Boolean) false); 
		return newSite;
	}
	/* This routine builds a tree node for a site
	   and returns the final site tree node to the
	   caller. The site reference is used to keep
	   track of the number of sites. */
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruSite newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the site reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Site reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current site */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.SITE;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  siteTree = new HDLmTree(nodeType,
				                                  nodeTooltip,
				                                  nodePath);
		if (siteTree == null) {
			String  errorText = "New site tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the site instance as the details of the new tree node */
		siteTree.setDetails(newValue);		
		return siteTree;	
	}
	 /* Get the value of the associated node type field and 
	    returns it to the caller */
  protected HDLmTreeTypes  getAssociatedNodeType() {
  	return associatedNodeType;
  }
	/* This routine returns a reference to the created insant */  
  protected Instant      getCreated() {
	  return created;		
  } 
	/* This routine returns the rules or values count */  
  protected int          getRulesOrValuesCount() {
	  return countRulesOrValues;	
  }
  /* This routine sets the associated node type of this class
	   instance to a null value */  
	protected final void   setAssociatedNodeTypeNull() { 
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
	/* This routine sets the rules or values count to a value passed by the caller */  
  protected final void   setRulesOrValuesCount(final int newRulesOrValuesCount) {
	  /* Check if the rules or values count passed by the caller is valid */
	  if (newRulesOrValuesCount < 0) {
	    String  errorText = "Rules or values count passed to setRulesOrValuesCount is invalid";
	    throw new IllegalArgumentException(errorText);  	
	  }
	  lastModified = Instant.now();
	  countRulesOrValues = newRulesOrValuesCount;		
  }
}	
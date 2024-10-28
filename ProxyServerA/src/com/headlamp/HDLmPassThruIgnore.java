package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * Class for supporting ignore-list entries
 *
 * Each instance of this class describes one ignore definition. This really
 * means one ignore-list entry definition. The instance has information about
 * about what output from the verification tests should be ignored. 
 *
 * This class has a constructor that builds an instance from JSON describing an
 * instance. The class is designed so that the ignore definitions can be quickly
 * used. The conversion from JSON to a class instance may not be fast at all.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each ignore line. An ignore line 
   is built to ignore the results of zero or more verification 
   tests. */
/* An instance of this class is built to specify that zero or more 
   verification test results should be ignored */  
/* An instance of this class is created for each ignore. The 
   name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruIgnore extends HDLmMod {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmPassThruIgnore.class);
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruIgnore() {
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
  	/* Declare and define a few variables */
  	Instant   currentTimestamp = Instant.now();
  	/* Set a few fields in the new report instance */
  	created = currentTimestamp;
  	lastModified = currentTimestamp;	
  	associatedNodeType = HDLmTreeTypes.IGNORE;
		/* Create the match cache map that will be needed later for optimization */
		matchCache = new HashMap<String, HDLmMatch>();
  	if (matchCache == null)
  		HDLmAssertAction(false, "New HashMap<> not created for match cache");
  }
	/* This is one of the constructors for the ignore definition class.
	   It must be passed a JSON element that contains all of the details
	   of the ignore definition. */
	protected HDLmPassThruIgnore(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build ignore definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		String  curString;
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Create the match cache map that will be needed later for optimization */
		matchCache = new HashMap<String, HDLmMatch>();
  	if (matchCache == null)
  		HDLmAssertAction(false, "New HashMap<> not created for match cache");
		/* Set the error count to zero. The error count is incremented each time an
			 error is detected. If the final error count (for the current definition) is
			 greater than zero, the current definition object is disabled (the enabled
			 field is set false). Note that a reference is used below so that the error
			 count can be updated by the routines called using error count.*/
	  MutableInt   errors = new MutableInt(0);
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
		  HDLmAssertAction(false, "JSON element used to build ignore definition is JSON null");
	  }
	  JsonObject jsonObject = jsonElement.getAsJsonObject();
	  Set<String> jsonKeys = jsonObject.keySet();
	  /* Get the standard class instance variables */
	  HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	  		                                                                    HDLmTreeTypes.IGNORE,
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
		/* Log the new name. This code is bypassed for now */
		if (1 == 2)
		  LOG.info("HDLmPassThruIgnore newName" + " - " + response.getName());
		/* Get and check the associated node type */
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
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "createdFromVerificationCheck", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    createdFromVerificationCheck = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "scriptId", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    scriptId = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "testCase", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    testCase = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "stepNumber", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    stepNumber = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "description", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    description = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "language", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    language = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "ticketPackage", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    ticketPackage = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "testResults", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    testResults = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "detailsOne", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    detailsOne = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "detailsTwo", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    detailsTwo = curString;
		/* Get a string and use it to set a instance field */ 
    curString  = HDLmMod.modFieldString(editorType, errors, 
	                                      jsonObject, jsonKeys, 
		                                    "detailsThree", 
		                                    HDLmWhiteSpace.WHITESPACENOTOK,
		                                    HDLmReportErrors.REPORTERRORS,
		                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    detailsThree = curString;
		/* Mark the current ignore definition object as disabled if the error count
		   was greater than zero. This is actually done by setting the enabled
		   field to false. */
		if (errors.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}	
	/* Getters and setters are provided for the values that 
	   need to be obtained and/or set */ 
	private HDLmTreeTypes   associatedNodeType = null;
  private String          createdFromVerificationCheck = null;
  private String          scriptId = null;
  private String          testCase = null;
  private String          stepNumber = null;
  private String          description = null;
  private String          language = null;
  private String          ticketPackage = null;
  private String          testResults = null;
  private String          detailsOne = null;
  private String          detailsTwo = null;
  private String          detailsThree = null;  
  private Map<String, HDLmMatch>   matchCache = null;
  /* This routine builds a dummy ignore line and returns it to the
     caller. No other work is done by this routine. */
  protected static HDLmPassThruIgnore  buildDummyIgnoreLine() {
  	/* Allocate a new ignore line that is filled in and returned 
  	   to the caller */ 
  	HDLmPassThruIgnore  ignoreLine = new HDLmPassThruIgnore();
  	if (ignoreLine == null)
  	  HDLmAssertAction(false, "Ignore-list entry was not created"); 
  	/* Set all of the fields of the new ignore line */
  	ignoreLine.createdFromVerificationCheck = "*";
  	ignoreLine.scriptId = "*";
  	ignoreLine.testCase = "*";
  	ignoreLine.stepNumber = "*";
  	ignoreLine.description = "*";
  	ignoreLine.language = "*";
  	ignoreLine.ticketPackage = "*";
  	ignoreLine.testResults = "*";
  	ignoreLine.detailsOne = "*";
  	ignoreLine.detailsTwo = "*";
  	ignoreLine.detailsThree = "*";  	
  	return ignoreLine;  	
  }
  /* This routine builds a tree node for a ignore (just one ignore-list
     entry) and returns the final ignore (just one ignore-list entry) 
     tree node to the caller. The ignore (just one ignore-list entry)
     reference is used to set the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruIgnore newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through ignore (just one ignore-list entry) 
		   reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Ignore reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current ignore (just one 
		   ignore-list entry) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.IGNORE;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  ignoreTree = new HDLmTree(nodeType,
				                                nodeTooltip,
				                                nodePath);
		if (ignoreTree == null) {
			String  errorText = "New ignore tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the ignore (just one ignore-list entry) instance as the
		   details of the new tree node */
		ignoreTree.setDetails(newValue);		
		return ignoreTree;	
	}
  /* The routine below checks if the results of a verification test 
     should be ignored or not, based on a specific ignore line. If the
     current test should be ignored, then true is returned to the caller.
     If the current test should not be ignored, then false is returned
     to the caller. 
     
     Note that the current report line can only be ignored if every field
     in the report line matches the current ignore line. If only one field
     in the report line, does not match, then we will return false. */ 
  protected boolean      checkReportLine(final HDLmPassThruLine reportLine) {
  	/* Declare and define a few fields */
  	String    currentStr;
  	/* Check the created field */
  	currentStr = reportLine.getCreatedFromVerificationCheck().toString();
  	if (HDLmMatch.checkStringsSpecial(createdFromVerificationCheck, 
  			                              matchCache,
  			                              currentStr) == false)
  		return false;
  	/* Check the script ID field */
  	currentStr = String.valueOf(reportLine.getScriptId());
  	if (HDLmMatch.checkStringsSpecial(scriptId,
															        matchCache,
															  			currentStr) == false)  		
  		return false;  	
  	/* Check the test case field */
  	currentStr = String.valueOf(reportLine.getTestCase());
  	if (HDLmMatch.checkStringsSpecial(testCase, 
															        matchCache,
															  			currentStr) == false)
  		return false;
  	/* Check the step number field */
  	currentStr = String.valueOf(reportLine.getStepNumber());
  	if (HDLmMatch.checkStringsSpecial(stepNumber, 
															        matchCache,
															  			currentStr) == false)
  		return false;
  	/* Check the description field */
  	currentStr = reportLine.getDescription();
  	if (HDLmMatch.checkStringsSpecial(description, 
															        matchCache,
															  			currentStr) == false)
  		return false;
  	/* Check the language field */
  	currentStr = reportLine.getLanguage();
  	if (HDLmMatch.checkStringsSpecial(language, 
															        matchCache,        
															  			currentStr) == false)
  		return false;
  	/* Check the test package field */
  	currentStr = reportLine.getTicketPackage();
  	if (HDLmMatch.checkStringsSpecial(ticketPackage,
															        matchCache,
															  			currentStr) == false)
  		return false; 
  	/* Check the test results field */
  	currentStr = reportLine.getTestResults();
  	if (HDLmMatch.checkStringsSpecial(testResults, 
															        matchCache,
															  			currentStr) == false)
  		return false;
  	/* Check the details one field */
  	currentStr = reportLine.getDetailsOne();
  	if (HDLmMatch.checkStringsSpecial(detailsOne, 
															        matchCache,
															  			currentStr) == false)
  		return false;
  	/* Check the details two field */
  	currentStr = reportLine.getDetailsTwo();
  	if (HDLmMatch.checkStringsSpecial(detailsTwo, 
															        matchCache,
															  			currentStr) == false)
  		return false;
  	/* Check the details three field */
  	currentStr = reportLine.getDetailsThree();
  	if (HDLmMatch.checkStringsSpecial(detailsThree, 
															        matchCache,
															  			currentStr) == false)
  		return false;
  	return true;
  }
  /* Get the value of the associated node type field and 
	   returns it to the caller */
	protected HDLmTreeTypes  getAssociatedNodeType() {
		return associatedNodeType;
	}
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  }
	/* Get a value and return it to the caller */
	protected String       getCreatedFromVerificationCheck() {
		return createdFromVerificationCheck;
	}
	/* Get a value and return it to the caller */
	protected String       getDescription() {
		return description;
	}
	/* Get a value and return it to the caller */
	protected String       getDetailsOne() {
		return detailsOne;
	}
	/* Get a value and return it to the caller */
	protected String       getDetailsTwo() {
		return detailsTwo;
	}
	/* Get a value and return it to the caller */
	protected String       getDetailsThree() {
		return detailsThree;
	} 
	/* Get a value and return it to the caller */
	protected String       getLanguage() {
		return language;
	}
	/* This routine returns a reference to the last modified instant */  
  protected Instant      getLastModified() {
	  return lastModified;		
  } 
	/* Get a value and return it to the caller */
	protected String       getScriptId() {
		return scriptId; 
	}
	/* Get a value and return it to the caller */
	protected String       getStepNumber() {
		return stepNumber; 
	}
	/* Get a value and return it to the caller */
	protected String       getTestCase() {
		return testCase; 
	}
	/* Get a value and return it to the caller */
	protected String       getTestResults() {
		return testResults;
	}
	/* Get a value and return it to the caller */
	protected String       getTicketPackage() {
		return ticketPackage;
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
	/* Set a value passed by the caller */
	protected void         setCreatedFromVerificationCheck(final String newCreatedFromVerificationCheck) {
		createdFromVerificationCheck = newCreatedFromVerificationCheck;
	}
	/* Set a value passed by the caller */
	protected void         setDescription(final String newDescription) {
		description = newDescription;
	}
	/* Set a value passed by the caller */
	protected void         setDetailsOne(final String newDetailsOne) {
		detailsOne = newDetailsOne;
	}
	/* Set a value passed by the caller */
	protected void         setDetailsTwo(final String newDetailsTwo) {
		detailsTwo = newDetailsTwo;
	}
	/* Set a value passed by the caller */
	protected void         setDetailsThree(final String newDetailsThree) {
		detailsThree = newDetailsThree;
	}
	/* Set a value passed by the caller */
	protected void         setLanguage(final String newLanguage) {
		language = newLanguage;
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
	/* This routine sets the match cache reference to a null value */  
  protected final void   setMatchCacheNull() {
  	lastModified = Instant.now();
	  matchCache = null; 		
  } 
	/* Get a value and return it to the caller */
	protected void         setScriptId(final String newScriptId) {
		scriptId = newScriptId; 
	}
	/* Get a value and return it to the caller */
	protected void         setStepNumber(final String newStepNumber) {
		stepNumber = newStepNumber; 
	}
	/* Get a value and return it to the caller */
	protected void         setTestCase(final String newTestCase) {
		testCase = newTestCase; 
	}
	/* Get a value and return it to the caller */
	protected void         setTestResults(final String newTestResults) {
		testResults = newTestResults;
	}
	/* Get a value and return it to the caller */
	protected void         setTicketPackage(final String newTicketPackage) {
		ticketPackage = newTicketPackage;
	}
}
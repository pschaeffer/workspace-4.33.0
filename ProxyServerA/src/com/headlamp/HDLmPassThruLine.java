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
 * HDLmPassThruLine short summary.
 *
 * HDLmPassThruLine description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each line of each report. A report 
   is created each time the verification tests are run against 
   one company. */
/* An instance of this class is created to store the results from 
   one test from a suite of verification tests */ 
/* An instance of this class is created for each line. The 
   name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruLine extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruLine() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
  	/* Declare and define a few variables */
  	Instant   currentTimestamp = Instant.now();
  	/* Set a few fields in the new report instance */
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.LINE;
  }
	/* This is one of the constructors for this class. It doesn't do
     much. However, the name will be set to the value passed by 
     the caller. All of the other fields of this class will be 
     set to the default values specified. */
  protected HDLmPassThruLine(final String lineName) { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		/* Check if the line name passed by the caller, is null */
		if (lineName == null) {
			String   errorText = "Line name string reference passed to the HDLmPassThruLine constructor is null";
			throw new NullPointerException(errorText);		
		}
  	/* Declare and define a few variables */
  	Instant  currentTimestamp = Instant.now();
  	/* Set a few fields in the new report instance */
  	created = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.LINE;
  	setName(lineName);
  }
	/* This is one of the constructors for the line (report line) definition
	   class. It must be passed a JSON element that contains all of the details
     of the report line definition. */
	protected HDLmPassThruLine(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build line definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Integer   curInteger = null;
		String    curString; 
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
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
			String  errorText = "Error message ArrayList allocation in HDLmPassThruLine constructor is null";
			throw new NullPointerException(errorText);
		}	
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
		  HDLmAssertAction(false, "JSON element used to build line definition is JSON null");
	  }
	  JsonObject jsonObject = jsonElement.getAsJsonObject();
	  Set<String> jsonKeys = jsonObject.keySet();
	  /* Get the standard class instance variables */
	  HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	 		                                                                      HDLmTreeTypes.LINE,
	 		                                                                      HDLmGetComments.GETCOMMENTSNO,
	 		                                                                      HDLmGetCreated.GETCREATEDYES,
	 		                                                                      HDLmGetLastModified.GETLASTMODIFIEDNO,
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
		/* The last modified value is set, even though report don't really
	     have a last modified value. The value is set from the JSON obtained
	     from the database. This step avoids certain errors later. */ 
	  lastModified = response.getLastModified();
	  /* Check if the last modified value is null. If it is null, set it to the current time.
	     This will really happen. We don't get the current date-time from the current row. 
	     This means that the date-time will be null. */
		if (lastModified == null)
			lastModified = Instant.now();
		/* Get a string and use it to set a instance field */ 
		Instant  curInstant; 
	  curInstant = HDLmMod.modFieldDateTime(editorType, 
	  		                                  errorCounter,
	  		                                  errorMessages,
	                                        jsonObject, 
	                                        jsonKeys, 
	 	                                      "createdFromVerificationCheck",  
		                                      HDLmReportErrors.REPORTERRORS);   
	  if (curInstant != null)
	    createdFromVerificationCheck = curInstant;
		/* Get an integer value and use it to set an instance field */ 
	  curInteger = HDLmMod.modFieldInteger(editorType, 
	  		                                 errorCounter,
	  		                                 errorMessages,
	                                       jsonObject, 
	                                       jsonKeys, 
		                                     "scriptId", 
		                                     HDLmReportErrors.REPORTERRORS);
	  scriptId = curInteger;
	  /* Get an integer value and use it to set an instance field */ 
	  curInteger = HDLmMod.modFieldInteger(editorType, 
	  		                                 errorCounter, 
	  		                                 errorMessages,
	                                       jsonObject, 
	                                       jsonKeys, 
		                                     "testCase", 
		                                     HDLmReportErrors.REPORTERRORS);
	  testCase = curInteger;
	  /* Get an integer value and use it to set an instance field */ 
	  curInteger = HDLmMod.modFieldInteger(editorType, 
	  	                                   errorCounter, 
	  	                                   errorMessages,
	                                       jsonObject, jsonKeys, 
		                                     "stepNumber", 
		                                     HDLmReportErrors.REPORTERRORS);
	  stepNumber = curInteger;
		/* Get a string and use it to set an instance field */
	  {
			String  errorMessagePrefix = "Line";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
		  curString = HDLmField.checkFieldString(editorType, 
				  		                               errorCounter,
				  		                               errorMessages,
				                                     jsonObject, 
				                                     jsonKeys, 
					                                   "description", 
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
	  description = curString;
		/* Get a string and use it to set an instance field */ 
	  {
			String  errorMessagePrefix = "Line";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
		  curString = HDLmField.checkFieldString(editorType, 
				  		                               errorCounter,
				  		                               errorMessages,
				                                     jsonObject, 
				                                     jsonKeys, 
					                                   "language",     
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
	  language = curString;
		/* Get a string and use it to set an instance field */ 
	  {
			String  errorMessagePrefix = "Line";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
		  curString = HDLmField.checkFieldString(editorType, 
				  		                               errorCounter,
				  		                               errorMessages,
				                                     jsonObject, 
				                                     jsonKeys, 
					                                   "ticketPackage", 
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
	  ticketPackage = curString;
		/* Get a string and use it to set an instance field */ 
		{
			String  errorMessagePrefix = "Line";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
		  curString = HDLmField.checkFieldString(editorType, 
				  		                                errorCounter,
				  		                                errorMessages,
				                                      jsonObject, 
				                                      jsonKeys, 
			                                        "testResults", 
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
		/* Get a string and use it to set an instance field */ 
	  {
			String  errorMessagePrefix = "Line";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
		  curString = HDLmField.checkFieldString(editorType, 
				  		                               errorCounter,
				  		                               errorMessages,
				                                     jsonObject, 
				                                     jsonKeys, 
					                                   "detailsOne", 
					                                   errorMessagePrefix,
																     	     	 errorNumberMissing,
																             errorNumberIsNull,
																             errorNumberNotPrimitive,
																             errorNumberException,
																	           errorNumberInvalidLength,
																	           errorNumberInvalidWhiteSpace, 
					                                   HDLmWhiteSpace.WHITESPACEOK,
					                                   HDLmReportErrors.REPORTERRORS,
					                                   HDLmZeroLengthOk.ZEROLENGTHNOTOK);
	  }
	  detailsOne = curString;
		/* Get a string and use it to set an instance field */ 
	  {
			String  errorMessagePrefix = "Line";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
		  curString = HDLmField.checkFieldString(editorType, 
				  		                               errorCounter,
				  		                               errorMessages,
				                                     jsonObject, jsonKeys, 
					                                   "detailsTwo", 
					                                   errorMessagePrefix,
																     	     	 errorNumberMissing,
																             errorNumberIsNull,
																             errorNumberNotPrimitive,
																             errorNumberException,
																	           errorNumberInvalidLength,
																	           errorNumberInvalidWhiteSpace, 
					                                   HDLmWhiteSpace.WHITESPACEOK,
					                                   HDLmReportErrors.REPORTERRORS,
			                                       HDLmZeroLengthOk.ZEROLENGTHNOTOK);
	  }
	  detailsTwo = curString;
		/* Get a string and use it to set an instance field */ 
	  {
		  String  errorMessagePrefix = "Line";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
		  curString  = HDLmField.checkFieldString(editorType, 
				  		                                errorCounter,
				  		                                errorMessages,
				                                      jsonObject, 
				                                      jsonKeys, 
					                                    "detailsThree", 
					                                    errorMessagePrefix,
																     	     	  errorNumberMissing,
																              errorNumberIsNull,
																              errorNumberNotPrimitive,
																              errorNumberException,
																	            errorNumberInvalidLength,
																	            errorNumberInvalidWhiteSpace, 
					                                    HDLmWhiteSpace.WHITESPACEOK,
					                                    HDLmReportErrors.REPORTERRORS,
					                                    HDLmZeroLengthOk.ZEROLENGTHNOTOK);
	  }
	  detailsThree = curString;
		/* Mark the current line (report line) definition object as disabled 
		   if the error count was greater than zero. This is actually done by
		   setting the enabled field to false. */
		if (errorCounter.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
     need to be obtained and/or set */ 
	private HDLmTreeTypes   associatedNodeType = null;
	private Instant         createdFromVerificationCheck = null;
	private int             scriptId = 0; 
	private int             testCase = 0; 
	private int             stepNumber = 0;
	private String          description = null;
	private String          dummyCreatedFromVerificationCheck = null;
	private String          language = null;
	private String          ticketPackage = null;
	private String          testResults = null;
	private String          detailsOne = null;
	private String          detailsTwo = null;
	private String          detailsThree = null;
	/* This routine builds an error line from a set of values passed
	   by the caller. The new line instance is added to the overall
	   lines of a report (also passed by the caller). All of the fields 
	   in the new line are set to some value. */
	protected static void  addErrorLine(HDLmPassThruReport curReport,
			                                MutableInt lineCounter,
			                                final String newDescription,
			                                final String newLanguage,
			                                final String newTicketPackage,
			                                final String newTestResults) {
		/* Check if the current report reference passed by the caller is null */ 
	  if (curReport == null) {
		  String  errorText = "Current report reference passed to addErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the line counter mutable integer reference passed by the caller is null */ 
	  if (lineCounter == null) {
		  String  errorText = "Line counter mutable integer reference passed to addErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new description string reference passed by the caller is null */ 
	  if (newDescription == null) {
		  String  errorText = "New description string reference passed to addErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new language string reference passed by the caller is null */ 
	  if (newLanguage == null) {
		  String  errorText = "New language string reference passed to addErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new ticket package string reference passed by the caller is null */ 
	  if (newTicketPackage == null) {
		  String  errorText = "New ticket package string reference passed to addErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new test results string reference passed by the caller is null */ 
	  if (newTestResults == null) {
		  String  errorText = "New test results string reference passed to addErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
	  /* Increment the total number of lines created so far */
		lineCounter.increment(); 
		/* Build the line name string from the total number of lines created so far */
		String  lineName = HDLmPassThruLine.buildLineName(lineCounter.getValue());
		/* Build the error line mostly from values passed by the caller */
		HDLmPassThruLine  errorLine;
		errorLine = HDLmPassThruLine.buildErrorLine(lineName, 
																								newDescription, 
																								newLanguage, 
																								newTicketPackage, 
																								newTestResults);  
		/* The call below appears to add a report line to a report. Actually,
	     the report line is added to the overall set of lines associated 
	     with the report. */ 
	  curReport.addLine(errorLine);
	}
	/* This routine builds an error line from a set of values passed
	   by the caller. The new line instance is added to the overall
	   lines of a report (also passed by the caller). All of the fields 
	   in the new line are set to some value. */
	protected static void  addErrorLines(HDLmPassThruReport curReport,
			                                 MutableInt lineCounter,
			                                 final String newDescription,
			                                 final String newLanguage,
			                                 final String newTicketPackage,
			                                 final String linesStr) {
		/* Check if the current report reference passed by the caller is null */ 
	  if (curReport == null) {
		  String  errorText = "Current report reference passed to addErrorLines is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the line counter mutable integer reference passed by the caller is null */ 
	  if (lineCounter == null) {
		  String  errorText = "Line counter mutable integer reference passed to addErrorLines is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new description string reference passed by the caller is null */ 
	  if (newDescription == null) {
		  String  errorText = "New description string reference passed to addErrorLines is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new language string reference passed by the caller is null */ 
	  if (newLanguage == null) {
		  String  errorText = "New language string reference passed to addErrorLines is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new ticket package string reference passed by the caller is null */ 
	  if (newTicketPackage == null) {
		  String  errorText = "New ticket package string reference passed to addErrorLines is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the set of lines string reference passed by the caller is null */ 
	  if (linesStr == null) {
		  String  errorText = "Set of lines string reference passed to addErrorLines is null";
		  throw new NullPointerException(errorText);
	  }	  
	  /* Split the string passed by the caller into an array of lines */
    String linesArray[] = linesStr.split("\\r?\\n");
    for (String curLine : linesArray) { 
    	/* Check if the current line is blanks. We can skip blank
    	   lines. */ 
    	if (curLine.equals(""))
    		continue;
    	/* Build and add the new error line */	
			HDLmPassThruLine.addErrorLine(curReport, 
					                          lineCounter, 
					                          newDescription, 
					                          newLanguage,
					                          newTicketPackage, 
					                          curLine);	  
    }
	}	
	/* This routine builds an error line from a set of values passed
	   by the caller. The new line instance is returned to the caller.
	   All of the fields in the new line are set to some value. */
	protected static HDLmPassThruLine  buildErrorLine(final String lineName,
			                                              final String newDescription,
			                                              final String newLanguage,
			                                              final String newTicketPackage,
			                                              final String newTestResults) {
		/* Check if the line name string reference passed by the caller is null */ 
	  if (lineName == null) {
		  String  errorText = "Line name string reference passed to buildErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new description string reference passed by the caller is null */ 
	  if (newDescription == null) {
		  String  errorText = "New description string reference passed to buildErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new language string reference passed by the caller is null */ 
	  if (newLanguage == null) {
		  String  errorText = "New language string reference passed to buildErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new ticket package string reference passed by the caller is null */ 
	  if (newTicketPackage == null) {
		  String  errorText = "New ticket package string reference passed to buildErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the new test results string reference passed by the caller is null */ 
	  if (newTestResults == null) {
		  String  errorText = "New test results string reference passed to buildErrorLine is null";
		  throw new NullPointerException(errorText);
	  }
	  /* Try to create the new line instance. The fields are set below. */
		HDLmPassThruLine  newLine = new HDLmPassThruLine(lineName);
		if (newLine == null)
			HDLmAssertAction(false, "New line instance was not created");
		/* Set all of the fields of the new line instance */ 
  	Instant   currentTimestamp = Instant.now();
  	/* Set a few fields in the new report instance */
  	newLine.associatedNodeType = HDLmTreeTypes.LINE;
  	newLine.created = currentTimestamp;
  	newLine.createdFromVerificationCheck = currentTimestamp;
  	newLine.description = newDescription;
  	newLine.language = newLanguage;
  	newLine.ticketPackage = newTicketPackage;
  	newLine.testResults = newTestResults;
  	newLine.detailsOne = "";
  	newLine.detailsTwo = "";
  	newLine.detailsThree = "";	
  	/* Return the newly created instance to the caller */
		return newLine;
	}
  /* This routine build a report line instance from a set of tokens.
	   The tokens are obtained by processing a line of text written
	   by the external program. */
	protected static HDLmPassThruLine  buildLine(final String lineName,
			                                         ArrayList<HDLmToken> lineTokens) {
		/* Check if the line name string reference passed by the caller is null */ 
	  if (lineName == null) {
		  String  errorText = "Line name string reference passed to buildLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the array of tokens passed by the caller is null */ 
	  if (lineTokens == null) {
		  String  errorText = "Array list of tokens passed to buildLine is null";
		  throw new NullPointerException(errorText);
	  }
	  /* Declare and define a few fields */
	  HDLmToken   curToken; 
	  Instant     curInstant;
	  Instant     currentTimestamp = Instant.now();
	  String      curString;
	  /* Get the overall token count. The token count is used to determine
	     which fields can be set. */
	  int   tokenCount = lineTokens.size();
	  /* Start by building the report line instance that will be filled in
	     and returned to the caller */
	  HDLmPassThruLine  reportLine = new HDLmPassThruLine(lineName);
  	/* Set a few fields in the new report instance */
  	reportLine.created = currentTimestamp;
	  /* Set a few fields in the output report line */
	  curToken = lineTokens.get(0);
	  if (curToken.getType() != HDLmTokenTypes.QUOTED)
	    HDLmAssertAction(false, "Token is not a quoted string token"); 
	  curString = curToken.getValue() + "";	  	  	
	  /* Convert a date-time string into an Instant */
    curInstant = Instant.parse(curString);
	  reportLine.createdFromVerificationCheck = curInstant;   
	  /* Make sure we have a comma token after the timestamp */
	  curToken = lineTokens.get(1);
	  if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	    HDLmAssertAction(false, "Token is not an operator token"); 
	  curString = curToken.getValue();
	  if (!curString.equals(","))
	    HDLmAssertAction(false, "Token is not a comma value"); 
	  /* Get the script ID value */
	  curToken = lineTokens.get(2);
	  if (curToken.getType() != HDLmTokenTypes.QUOTED)
	    HDLmAssertAction(false, "Token is not a quoted string token"); 
	  curString = curToken.getValue();
	  reportLine.scriptId = Integer.valueOf(curString);
	  /* Make sure we have a comma token after the script ID */
	  curToken = lineTokens.get(3);
	  if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	    HDLmAssertAction(false, "Token is not an operator token"); 
	  curString = curToken.getValue();
	  if (!curString.equals(","))
	    HDLmAssertAction(false, "Token is not a comma value"); 
	  /* Get the test case value */
	  curToken = lineTokens.get(4);
	  if (curToken.getType() != HDLmTokenTypes.QUOTED)
	    HDLmAssertAction(false, "Token is not a quoted string token"); 
	  curString = curToken.getValue();
	  reportLine.testCase = Integer.valueOf(curString);
	  /* Make sure we have a comma token after the test case */
	  curToken = lineTokens.get(5);
	  if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	    HDLmAssertAction(false, "Token is not an operator token"); 
	  curString = curToken.getValue();
	  if (!curString.equals(","))
	    HDLmAssertAction(false, "Token is not a comma value"); 
	  /* Get the step number value */
	  curToken = lineTokens.get(6);
	  if (curToken.getType() != HDLmTokenTypes.QUOTED)
	    HDLmAssertAction(false, "Token is not a quoted string token"); 
	  curString = curToken.getValue();
	  reportLine.stepNumber = Integer.valueOf(curString);
	  /* Make sure we have a comma token after the step number */
	  curToken = lineTokens.get(7);
	  if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	    HDLmAssertAction(false, "Token is not an operator token"); 
	  curString = curToken.getValue();
	  if (!curString.equals(","))
	    HDLmAssertAction(false, "Token is not a comma value"); 
	  /* Get the description value */
	  curToken = lineTokens.get(8);
	  if (curToken.getType() != HDLmTokenTypes.QUOTED)
	    HDLmAssertAction(false, "Token is not a quoted string token"); 
	  curString = curToken.getValue();
	  reportLine.description = curString;
	  /* Make sure we have a comma token after the description */
	  curToken = lineTokens.get(9);
	  if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	    HDLmAssertAction(false, "Token is not an operator token"); 
	  curString = curToken.getValue();
	  if (!curString.equals(","))
	  	HDLmAssertAction(false, "Token is not a comma value"); 
	  /* Get the language value */
	  curToken = lineTokens.get(10);
	  if (curToken.getType() != HDLmTokenTypes.QUOTED)
	    HDLmAssertAction(false, "Token is not a quoted string token"); 
	  curString = curToken.getValue();
	  reportLine.language = curString;
	  /* Make sure we have a comma token after the language */
	  curToken = lineTokens.get(11);
	  if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	    HDLmAssertAction(false, "Token is not an operator token"); 
	  curString = curToken.getValue();
	  if (!curString.equals(","))
	  	HDLmAssertAction(false, "Token is not a comma value"); 
	  /* Get the ticket package value */
	  curToken = lineTokens.get(12);
	  if (curToken.getType() != HDLmTokenTypes.QUOTED)
	    HDLmAssertAction(false, "Token is not a quoted string token"); 
	  curString = curToken.getValue();
	  reportLine.ticketPackage = curString;
	  /* Make sure we have a comma token after the ticket package */
	  curToken = lineTokens.get(13);
	  if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	    HDLmAssertAction(false, "Token is not an operator token"); 
	  curString = curToken.getValue();
	  if (!curString.equals(","))
	  	HDLmAssertAction(false, "Token is not a comma value"); 
	  /* Get the test results value */
	  curToken = lineTokens.get(14);
	  if (curToken.getType() != HDLmTokenTypes.QUOTED)
	    HDLmAssertAction(false, "Token is not a quoted string token"); 
	  curString = curToken.getValue();
	  reportLine.testResults = curString;
	  /* Make sure we have a comma token after the test results */
	  if (tokenCount >= 18) {
	    curToken = lineTokens.get(15);
	    if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	      HDLmAssertAction(false, "Token is not an operator token"); 
	    curString = curToken.getValue();
	    if (!curString.equals(","))
	    	HDLmAssertAction(false, "Token is not a comma value"); 
	  }
	  /* Get the first set of details value */
	  if (tokenCount >= 18) {
	    curToken = lineTokens.get(16);
	    if (curToken.getType() != HDLmTokenTypes.QUOTED)
	      HDLmAssertAction(false, "Token is not a quoted string token"); 
	    curString = curToken.getValue();
	    reportLine.detailsOne = curString;
	  }
	  /* Make sure we have a comma token after the first set of details */
	  if (tokenCount >= 20) {
	    curToken = lineTokens.get(17);
	    if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	      HDLmAssertAction(false, "Token is not an operator token"); 
	    curString = curToken.getValue();
	    if (!curString.equals(","))
	    	HDLmAssertAction(false, "Token is not a comma value"); 
	  }
	  /* Get the second set of details value */
	  if (tokenCount >= 20) {
	    curToken = lineTokens.get(18);
	    if (curToken.getType() != HDLmTokenTypes.QUOTED)
	      HDLmAssertAction(false, "Token is not a quoted string token"); 
	    curString = curToken.getValue();
	    reportLine.detailsTwo = curString;
	  }
	  /* Make sure we have a comma token after the second set of details */
	  if (tokenCount >= 22) {
	    curToken = lineTokens.get(19);
	    if (curToken.getType() != HDLmTokenTypes.OPERATOR)
	      HDLmAssertAction(false, "Token is not an operator token"); 
	    curString = curToken.getValue();
	    if (!curString.equals(","))
	    	HDLmAssertAction(false, "Token is not a comma value"); 
	  }
	  /* Get the third set of details value */
	  if (tokenCount >= 22) {
	    curToken = lineTokens.get(20);
	    if (curToken.getType() != HDLmTokenTypes.QUOTED)
	      HDLmAssertAction(false, "Token is not a quoted string token"); 
	    curString = curToken.getValue();
	    reportLine.detailsThree = curString;
	  }	  
	  return reportLine;  	
	}
	/* This routine builds a standard line name and returns it 
	   to the caller. Note that the caller passes the line number
	   used to build the line name. We always allow for many 
	   leading digits so that the line names will be in collating
	   order. */ 
	protected static String  buildLineName(final int lineNumber)  {
		return String.format("Line%06d",  lineNumber);		
	}
	/* This routine builds a tree node for a line (just one line)  
	   and returns the final line (just one line) tree node to the
	   caller. The line (just one line) reference is used to set
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruLine newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through line (just one line) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Line reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current line (just one line) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.LINE;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  lineTree = new HDLmTree(nodeType,
				                              nodeTooltip,
				                              nodePath);
		if (lineTree == null) {
			String  errorText = "New line tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the line (just one line) instance as the details of the new tree node */
		lineTree.setDetails(newValue);		
		return lineTree;	
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
  /* Allow access to a few fields using getters */
  protected Instant      getCreatedFromVerificationCheck() {
  	return createdFromVerificationCheck;
  }  
  /* Get the description return it to the caller */
  protected String       getDescription() {
  	return description;
  }
  /* Get the first set of test details and return them to the caller */
  protected String       getDetailsOne() {
  	return detailsOne;
  }
  /* Get the second set of test details and return them to the caller */
  protected String       getDetailsTwo() {
  	return detailsTwo;
  }
  /* Get the third set of test details and return them to the caller */
  protected String       getDetailsThree() {
  	return detailsThree;
  }
  /* Get the language and return it to the caller */
  protected String       getLanguage() {
  	return language;
  }
  /* Get the line name and return it to the caller. This routine
     has a special name so that the name will not collide with 
     the name of a routine in the superclass.  */
  protected String       getLineName() {
  	return getName();
  }
  /* Get the script ID and return it to the caller */
  protected int          getScriptId() {
  	return scriptId;
  }
  /* Get the step number and return it to the caller */
  protected int          getStepNumber() {
  	return stepNumber;
  }
  /* Get the test case and return it to the caller */
  protected int          getTestCase() {
  	return testCase;
  }
  /* Get the test results and return them to the caller */
  protected String       getTestResults() {
  	return testResults;
  }
  /* Get the ticket package and return it to the caller */
  protected String       getTicketPackage() {
  	return ticketPackage;
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
	/* Set a value passed by the caller */
	protected void         setCreatedFromVerificationCheck(final Instant newCreatedFromVerificationCheck) {
		createdFromVerificationCheck = newCreatedFromVerificationCheck;
	}
	/* Set the created from verification check field for a modification 
	   to a null value */
	protected void setCreatedFromVerificationCheckNull() {
		createdFromVerificationCheck = null;
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
	protected void setDummyCreatedFromVerificationCheck(final String newDummyCreatedFromVerificationCheck) {
	  this.dummyCreatedFromVerificationCheck = newDummyCreatedFromVerificationCheck;
	}
	/* Set a value passed by the caller */
	protected void         setLanguage(final String newLanguage) {
		language = newLanguage;
	}	  
	/* Get a value and return it to the caller */
	protected void         setScriptId(final int newScriptId) {
		scriptId = newScriptId; 
	}
	/* Get a value and return it to the caller */
	protected void         setStepNumber(final int newStepNumber) {
		stepNumber = newStepNumber; 
	}
	/* Get a value and return it to the caller */
	protected void         setTestCase(final int newTestCase) {
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
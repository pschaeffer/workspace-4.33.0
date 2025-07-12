package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
/**
 * HDLmResponse short summary.
 *
 * HDLmResponse description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmResponse {
	/* An instance of this class is created to return the results
	   of a request to the caller. Of course, not all of the fields 
	   will always be set. However, all of the fields may be set in 
	   some cases. */
	private int                returnCode = -1;
	private HDLmResponseTypes  returnType = HDLmResponseTypes.NONE;
	private String             returnString = null;
	private String             errorMessage = null;
	private String             returnContext = null;
	private String             returnGoal = null;
	private ArrayList<String>  stringList = null;
	private int                returnNumber = -1;
	/* Get a few values */
	protected String       getErrorMessage() {
		return errorMessage;
	}
	protected int          getReturnCode() {
		return returnCode;
	}
	protected String       getReturnContext() {
		return returnContext;
	}
	protected String       getReturnGoal() {
		return returnGoal;
	}
	protected HDLmResponseTypes  getResponseType() {
		return returnType;
	}
	protected int          getReturnNumber() {
		return returnNumber;
	}
	protected String       getReturnString() {
		return returnString;
	}
	protected ArrayList<String>  getStringList() {
	  return stringList;
	}
	/* Set or reset the error message. Note that the caller can 
	   not pass a null value for the new error message. This is 
	   an error condition. */
	protected void         setErrorMessage(final String newErrorMessage) {
		if (newErrorMessage == null) {
			String  errorText = "New error message string is null";
			throw new NullPointerException(errorText);
		}
		errorMessage = newErrorMessage;
	}
	/* Set or reset the response type. Note that the caller can 
     not pass a null value for the new response type. This is 
     an error condition. */
	protected void         setResponseType(final HDLmResponseTypes newResponseType) {
		if (newResponseType == null) {
			String  errorText = "New reponse type value is null";
			throw new NullPointerException(errorText);
		}
		returnType = newResponseType;
	}
	/* Set or reset the return code. Note that the caller can 
     not pass an invalid value for the new return code. This 
     is an error condition. */
	protected void         setReturnCode(final int newReturnCode) {
		if (newReturnCode <= 0) {
		  HDLmAssertAction(false, "New return code value is invalid"); 
		}
		returnCode = newReturnCode;
	}
	/* Set the return code to zero */
	protected void         setReturnCodeZero() {
		returnCode = 0;
	}
	/* Set or reset the return context. Note that the caller can 
     not pass an invalid context (null) for the new return context.
     This is an error condition. */
  protected void         setReturnContext(final String newReturnContext) {
		if (newReturnContext == null) {
		  HDLmAssertAction(false, "New string return context is invalid"); 
		}
		returnContext = newReturnContext;
	}
	/* Set or reset the return goal. Note that the caller can 
	   not pass an invalid goal (null) for the new return goal.
	   This is an error condition. */
	protected void         setReturnGoal(final String newReturnGoal) {
		if (newReturnGoal == null) {
		  HDLmAssertAction(false, "New string return goal is invalid"); 
		}
		returnGoal = newReturnGoal;
	}
	/* Set or reset the return number. Note that the caller can 
     not pass an invalid number (<= 0) for the new return number.
     This is an error condition. */
  protected void         setReturnNumber(final int newReturnNumber) {
	  if (newReturnNumber <= 0) {
		  String  errorFormat = "Return number value (%d) is invalid";
			String  errorText = String.format(errorFormat, newReturnNumber);
	    HDLmAssertAction(false, errorText);  
	  }
	  returnNumber = newReturnNumber;
  }
	/* Set the return number to zero */
	protected void         setReturnNumberZero() {
	 returnNumber = 0;
	}
	/* Set or reset the return string. Note that the caller can 
	   not pass an invalid string (null) for the new return string.
	   This is an error condition. */
	protected void         setReturnString(final String newReturnString) {
		if (newReturnString == null) {
		  HDLmAssertAction(false, "New string return value is invalid"); 
		}
		returnString = newReturnString;
	}
	/* Set or reset the string list. Note that the caller can 
     not pass an invalid value (null) for the new string list.
     This is an error condition. */
  protected void         setStringList(final ArrayList<String> newStringList) {
  	if (newStringList == null) {
	    HDLmAssertAction(false, "New string list is invalid"); 
	  }
	  stringList = newStringList;
  }
}
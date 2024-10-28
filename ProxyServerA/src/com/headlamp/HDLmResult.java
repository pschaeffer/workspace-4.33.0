package com.headlamp;
/**
 * HDLmResult short summary.
 *
 * HDLmResult description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmResult {
	/* An instance of this class is created to return the results
	   of a request to the caller. Of course, not all of the fields 
	   will always be set. However, all of the fields may be set in 
	   some cases. */
	private Boolean   resultDesired = null;
	private String    resultMessage = null;
	private Integer   resultCode = null;
	private Integer   resultNumber = null;
	protected Boolean      getDesired() {
		return resultDesired;
	}
	/* Get a few values */
	protected String       getMessage() {
		return resultMessage;
	}
	protected Integer      getCode() {
		return resultCode;
	}
	/* Set or reset the code. Note that the caller can 
     not pass an invalid value for the new return code.
     This is an error condition. */
	protected void         setCode(final Integer newCode) {
		if (newCode == null) {
			String  errorText = "New code value is null";
			throw new NullPointerException(errorText);
		}
		resultCode = newCode;
	}
	/* Set or reset the desired value. Note that the caller can 
	   not pass a null value for the new desired value. This is 
	   an error condition. */
	protected void         setDesired(final Boolean newDesired) {
		if (newDesired == null) {
			String  errorText = "New desired boolean is null";
			throw new NullPointerException(errorText);
		}
		resultDesired = newDesired;
	}
	/* Set or reset the message. Note that the caller can 
	   not pass a null value for the new message. This is 
	   an error condition. */
	protected void         setMessage(final String newMessage) {
		if (newMessage == null) {
			String  errorText = "New message string is null";
			throw new NullPointerException(errorText);
		}
		resultMessage = newMessage;
	}
	/* Set or reset the number. Note that the caller can
	   pass a null value for the return number. This is 
	   not an error condition. */
	protected void         setNumber(final Integer newNumber) {
		resultNumber = newNumber;
	}
}

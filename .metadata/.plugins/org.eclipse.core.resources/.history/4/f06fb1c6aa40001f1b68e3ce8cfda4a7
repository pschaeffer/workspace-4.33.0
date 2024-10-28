package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
/**
 * HDLmApacheResponse short summary.
 *
 * HDLmApacheResponse description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmApacheResponse {
	/* An instance of this class is created to return the results
	   of an Apache HTTP request to the caller. Of course, not all
	   of the fields will always be set. However, all of the fields
	   may be set in some cases. */
	private String             errorMessage = null;
	private ArrayList<String>  outHeaders = null;
	private byte[]             outByteContent = null;
	private String             outStringContent = null;
	private int                statusCode = -1;
	private String             statusLine = null;
	/* Get a few values */
	protected byte[]       getByteContent() {
		return outByteContent;
	}
	protected String       getErrorMessage() {
		return errorMessage;
	}
	protected String       getStringContent() {
		return outStringContent;
	}
	protected ArrayList<String> getHeaders() {
		return outHeaders;
	}
	protected int          getStatusCode() {
		return statusCode;
	}
	protected String       getStatusLine() {
		return statusLine;
	}
	/* Set or reset the returned byte content. Note that the caller can 
     not pass a null value for the new byte content value. This is an
     an error condition. */
	protected void setByteContent(byte[] newByteContent) {
		if (newByteContent == null) {
			String  errorText = "New content byte array is null";
			throw new NullPointerException(errorText);
		}
		outByteContent = newByteContent;
	}
	/* Set or reset the error message. Note that the caller can 
	   not pass a null value for the new error message. This is 
	   an error condition. */
	protected void setErrorMessage(String newErrorMessage) {
		if (newErrorMessage == null) {
			String  errorText = "New error message string is null";
			throw new NullPointerException(errorText);
		}
		errorMessage = newErrorMessage;
	}
	/* Set or reset the returned headers. Note that the caller can 
     not pass a null value for the new returned headers. This is
     an error condition. */
	protected void setHeaders(ArrayList<String> newHeaders) {
		if (newHeaders == null) {
			String  errorText = "New headers array list is null";
			throw new NullPointerException(errorText);
		}
		outHeaders = newHeaders;
	}
	/* Set or reset the status code. Note that the caller can 
     not pass an invalid value for the new status code. This 
     is an error condition. */
	protected void setStatusCode(int newStatus) {
		if (newStatus <= 0) {
		  HDLmAssertAction(false, "New status code value is invalid"); 
		}
		statusCode = newStatus;
	}
	/* Set or reset the status line. Note that the caller can 
	   not pass a null value for the new status line value.
	   This is an error condition. */
	protected void setStatusLine(String newStatusLine) {
		if (newStatusLine == null) {
			String  errorText = "New status line string is null";
			throw new NullPointerException(errorText);
		}
		statusLine = newStatusLine;
	}
	/* Set or reset the returned string content. Note that the caller can 
     not pass a null value for the new string content value. This is an
     an error condition. */
  protected void setStringContent(String newStringContent) {
	  if (newStringContent == null) {
		  String  errorText = "New content string value is null";
		  throw new NullPointerException(errorText);
	  }
	  outStringContent = newStringContent; 
  }
}
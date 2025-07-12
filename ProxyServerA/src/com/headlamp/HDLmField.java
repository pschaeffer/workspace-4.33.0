package com.headlamp;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class for processing fields
 * 
 * The fields are typically in JSON format. The fields are checked
 * in a number of ways. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmField {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmField.class);  
	/* This class can never be instantiated */
	private HDLmField() {}
	/* Try to access a JSON object in the JSON used to build the current object. 
	   Report an error if the JSON object is not found. If an error is reported,
	   the error count is also incremented. The return value from this function
	   is the value of the JSON object, if the JSON object is found. */
	protected static JsonObject  checkFieldJsonObject(final HDLmEditorTypes editorType, 
																							      final MutableInt errorCounter,
																							      final ArrayList<String> errorMessages,
																							      final JsonObject jsonObject, 
																							      final Set<String> jsonKeys,
																							      final String name, 
																							      final String errorMessagePrefix,
																							      final int errorNumberMissing,
																							      final int errorNumberIsNull,
																							      final int errorNumberIsPrimitive,
																							      final int errorNumberNotObject,
																							      final int errorNumberException,
																							      final HDLmReportErrors reportErrors) {

		/* Check a few fields passed by the caller */
		if (errorCounter == null) {
			String  errorText = "Mutable int for error counter passed to checkFieldJsonObject is null";
			throw new NullPointerException(errorText);
		}
		if (errorMessages == null) {
			String  errorText = "ArrayList for error messages passed to checkFieldJsonObject is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to checkFieldJsonObject is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to checkFieldJsonObject is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to checkFieldJsonObject is null";
			throw new NullPointerException(errorText);
		}
		if (errorMessagePrefix == null) {
		  String  errorText = "Error message prefix passed to checkFieldJsonObject is null";
		  throw new NullPointerException(errorText);
		}
		if (reportErrors == null) {
			String  errorText = "Report errors enum passed to checkFieldJsonObject is null";
			throw new NullPointerException(errorText);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorText = "Report errors enum passed to checkFieldStringArray is NONE";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(name)) {
			/* Since we do not have the field in the JSON, report an error */
			String  errorText = errorMessagePrefix + " " + "JSON missing field";
			HDLmField.reportErrorNoValue(editorType, 
					                         errorCounter,
					                         errorMessages,
					                         jsonObject, 
					                         name, 
					                         errorText, 
					                         errorNumberMissing, 
					                         reportErrors);
			return null;
		}
		/* Get the JSON element from the JSON object */
		JsonElement   jsonElement = jsonObject.get(name);
		if (jsonElement.isJsonNull()) {
			String  errorText = errorMessagePrefix + " " + "JSON field is null";
	    HDLmField.reportError(editorType, 
	  		                    errorCounter, 
							  	          errorMessages,
							  	          jsonObject,
								            name,
								            errorText, 
								            errorNumberIsNull, 
								            reportErrors);
			return null;
		}
		/* Check if the JSON element is a JSON primitive */
		if (jsonElement.isJsonPrimitive()) {
			String  errorText = errorMessagePrefix + " " + "JSON field is a JSON primitive";
	    HDLmField.reportError(editorType, 
	  		                    errorCounter, 
							  	          errorMessages,
							  	          jsonObject,
								            name,
								            errorText, 
								            errorNumberIsPrimitive, 
								            reportErrors);
			return null;
		}		
		/* Get some values from the input JSON */
		if (!jsonElement.isJsonObject()) {
			String  errorText = errorMessagePrefix + " " + "JSON field is not a JSON object";
	    HDLmField.reportError(editorType, 
	  		                    errorCounter, 
	                          errorMessages,
	                          jsonObject,
	                          name,
	                          errorText, 
	                          errorNumberNotObject,
	                          reportErrors);			
			return null;
	  }		
	  /* Get the object value */
	  JsonObject  newObject = null;				
		try {
			newObject = jsonElement.getAsJsonObject();
		} 
		catch (ClassCastException classCastException) {
			String errorText = errorMessagePrefix + " " + "JSON field is not a JSON object";
			/* Log the error */
			LOG.info(errorText);
			LOG.info(classCastException.getMessage(), classCastException);
			HDLmEvent.eventOccurred("ClassCastException");
	    HDLmField.reportError(editorType, 
	    	                    errorCounter, 
	    	                    errorMessages,
	    	                    jsonObject,
	    	                    name,
	    	                    errorText,  
	    	                    errorNumberException, 
	    	                    reportErrors);
	    return null;
		}
		catch (Exception exception) {
			String errorText = errorMessagePrefix + " " + "JSON field is not a JSON object";
			/* Log the error */
			LOG.info(errorText);
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
	    HDLmField.reportError(editorType, 
						                errorCounter, 
							  	          errorMessages,
								            jsonObject,
								            name,
								            errorText,  
								            errorNumberException, 
								            reportErrors);
	    return null;
		}		
		/* Return the new object to the caller */
		return newObject;
	}
	/* Try to access a field in the JSON used to build the current object
	  Report an error if the field is not found. If an error is reported,
	  the error count is also incremented. The return value from this function 
	  is the value of the field, if the field is found. */
	protected static String  checkFieldString(final HDLmEditorTypes editorType, 
				                                    final MutableInt errorCounter,
				                                    final ArrayList<String> errorMessages,
				                                    final JsonObject jsonObject, 
				                                    final Set<String> jsonKeys, 
				                                    final String name, 
				                                    final String errorMessagePrefix,
				                                    final int errorNumberMissing,
				                                    final int errorNumberIsNull,
				                                    final int errorNumberNotPrimitive,
				                                    final int errorNumberException, 
				                                    final int errorNumberInvalidLength,
				                                    final int errorNumberInvalidWhiteSpace,
				                                    final HDLmWhiteSpace whiteSpaceOK, 
				                                    final HDLmReportErrors reportErrors,
				                                    final HDLmZeroLengthOk zeroLength) {
		/* Check a few fields passed by the caller */
		if (errorCounter == null) {
		  String  errorText = "Mutable int for error counter passed to checkFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (errorMessages == null) {
			String  errorText = "ArrayList for error messages passed to checkFieldString is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
		  String  errorText = "JSON object passed to checkFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
		  String  errorText = "Set of keys passed to checkFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (name == null) {
		  String  errorText = "Name string passed to checkFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (errorMessagePrefix == null) {
		  String  errorText = "Error message prefix passed to checkFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (whiteSpaceOK == null) {
		  String  errorText = "White space OK enum passed to checkFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (whiteSpaceOK == HDLmWhiteSpace.NONE) {
			String  errorText = "White space enum passed to checkFieldString is NONE";
			throw new IllegalArgumentException(errorText);
		}
		if (reportErrors == null) {
		  String  errorText = "Report errors enum passed to checkFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorText = "Report errors enum passed to checkFieldString is NONE";
			throw new IllegalArgumentException(errorText);
		}
		if (zeroLength == null) {
		  String  errorText = "Zero-Length OK enum passed to checkFieldString is null";
		  throw new NullPointerException(errorText);
		}
		if (zeroLength == HDLmZeroLengthOk.NONE) {
			String  errorText = "Zero0length enum passed to checkFieldString is NONE";
			throw new IllegalArgumentException(errorText);
		}
		/* Check the field requested by the caller */ 
	  String  newString = null;
	  /* Check if we have the field in the JSON */
	  if (!jsonKeys.contains(name)) {
	  	String  errorText = errorMessagePrefix + " " + "JSON missing field";
	    HDLmField.reportErrorNoValue(editorType, 
	    		                         errorCounter, 
		 				 					             errorMessages,
							 				             jsonObject, 
							 				             name,
								 			             errorText,
									 		             errorNumberMissing,
									 		             reportErrors);
	    return null;
	  }
	  /* Check if the field is valid */
	  JsonElement   jsonElement = jsonObject.get(name);
	  if (jsonElement.isJsonNull()) {
		  String  errorText = errorMessagePrefix + " " + "JSON field is null";
	    HDLmField.reportError(editorType, 
	   		                    errorCounter, 
								            errorMessages,
								            jsonObject,
								            name,
								            errorText,
								            errorNumberIsNull,
								            reportErrors);
	    return null;
	  }
	  if (!jsonElement.isJsonPrimitive()) {
	  	String  errorText = errorMessagePrefix + " " + "JSON field is not a primitive";
	    HDLmField.reportError(editorType, 
	   		                    errorCounter, 
								            errorMessages,
								            jsonObject,
								            name,
								            errorText, 
								            errorNumberNotPrimitive, 
								            reportErrors);
	    return null;
	  }
	  /* Get the string value from the JSON */		
		try {
			newString = jsonElement.getAsString();
		} 
		catch (ClassCastException classCastException) {
			String  errorText = errorMessagePrefix + " " + "JSON field is not a JSON string";
			/* Log the error */
			LOG.info(errorText);
			LOG.info(classCastException.getMessage(), classCastException);
			HDLmEvent.eventOccurred("ClassCastException");
	    HDLmField.reportError(editorType, 
						                errorCounter, 
								            errorMessages,
								            jsonObject,
								            name,
								            errorText,  
								            errorNumberException,
								            reportErrors);
	    return null;
		}
		catch (Exception exception) {
			String  errorText = errorMessagePrefix + " " + "JSON field is not a JSON string";
			/* Log the error */
			LOG.info(errorText);
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
	    HDLmField.reportError(editorType, 
						                errorCounter, 
								            errorMessages,
								            jsonObject,
								            name,
								            errorText,  
								            errorNumberException,	
								            reportErrors);
	    return null;
		}			
		/* Get the length of string, if possible. If the string   
			 is a null value, then getting the length is not possible. */
		int   newStringLength = -1;
		if (newString != null)
			newStringLength = newString.length();
		/* Generally we can accept a string that is zero-length.
	    However, in some cases we can not. The check below 
	    rejects strings that are zero-length, if need be. */
	  if (zeroLength == HDLmZeroLengthOk.ZEROLENGTHNOTOK) 
	  	/* The following check has been made considerably more 
	  	   complex. It turns out that zero-length fields are 
	  	   considered to be white space. if WHITESPACEOK is
	  	   set, then a zero-length field is really OK. */ 
		  if (newStringLength == 0 &&
		  		whiteSpaceOK == HDLmWhiteSpace.WHITESPACENOTOK) {
		  	String  errorText = errorMessagePrefix + " " + "JSON field is zero-length";
		    HDLmField.reportError(editorType, 
		    		                  errorCounter, 
								  	          errorMessages,
								  	          jsonObject, 
								  	          name,
								  	          errorText,
								  	          errorNumberInvalidLength, 
									            reportErrors);
		  	return null;
			}
		/* Generally we can accept a string that is all white space
		   characters. However, in some cases we can not. The check
		   below rejects strings that are all white space, if need be.
		   Note that an empty string is considered to be a white space
		   string by the utility routine invoked below. */
		if (whiteSpaceOK == HDLmWhiteSpace.WHITESPACENOTOK) {
			/* In at least one case, we really do want to allow
			   a white space string to be accepted. If the string
			   length is zero and the ZEROLENGTHOK value is set, 
			   then the current string should not be treated as
			   white space. */ 
			boolean   newEntryOk = false;
			if (newStringLength == 0 &&
					zeroLength == HDLmZeroLengthOk.ZEROLENGTHOK)
				newEntryOk = true;			
		  if (StringUtils.isWhitespace(newString) &&
		  		newEntryOk == false) {
		  	String  errorText = errorMessagePrefix + " " + "JSON field is white space";
		    HDLmField.reportError(editorType, 
		    		                  errorCounter, 
									            errorMessages,
									            jsonObject,
									            name,
									            errorText,
									            errorNumberInvalidWhiteSpace, 
									            reportErrors);
		  	return null;
			}
	  }
		/* Return the new string value to the caller */
		return newString;
	}
	/* Try to access an array in the JSON used to build the current object. 
	   Report an error if the array is not found. If an error is reported,
	   the error count is also incremented. The return value from this function
	   is the value of the array, if the array is found. The return value is 
 	   an ArrayList of strings. */
	protected static ArrayList<String> checkFieldStringArray(final HDLmEditorTypes editorType, 
																											     final MutableInt errorCounter,
																											     final ArrayList<String> errorMessages,
																											     final JsonObject jsonObject, 
																											     final Set<String> jsonKeys,
																											     final String name, 
																											     final Integer minArrayLength,
																											     final Integer maxArrayLength,
																											     final String errorMessagePrefix,
																											     final int errorNumberMissing,
																											     final int errorNumberIsNull,
																											     final int errorNumberIsPrimitive,
																											     final int errorNumberNotPrimitive,
																											     final int errorNumberNotArray,
																											     final int errorNumberException,
																											     final int errorNumberTooSmall,
																											     final int errorNumberTooLarge, 
																											     final int errorNumberInvalidLength,
																											     final int errorNumberInvalidWhiteSpace) {
	  return HDLmField.checkFieldStringArray(editorType, 
	  		                                   errorCounter, 
	  		                                   errorMessages, 
	  		                                   jsonObject, 
	  		                                   jsonKeys, 
	  		                                   name, 
	  		                                   minArrayLength, 
	  		                                   maxArrayLength, 
	  		                                   errorMessagePrefix, 
	  		                                   errorNumberMissing, 
	  		                                   errorNumberIsNull, 
	  		                                   errorNumberIsPrimitive,
	  		                                   errorNumberNotPrimitive, 
	  		                                   errorNumberNotArray, 
	  		                                   errorNumberException, 
	  		                                   errorNumberTooSmall, 
	  		                                   errorNumberTooLarge, 
	  		                                   errorNumberInvalidLength,
																			     errorNumberInvalidWhiteSpace,
																		       HDLmWhiteSpace.WHITESPACENOTOK,
																	         HDLmReportErrors.REPORTERRORS,
																	         HDLmZeroLengthOk.ZEROLENGTHNOTOK);
	}
	protected static ArrayList<String> checkFieldStringArray(final HDLmEditorTypes editorType, 
			                                                     final MutableInt errorCounter,
			            		                                     final ArrayList<String> errorMessages,
			                                                     final JsonObject jsonObject, 
			                                                     final Set<String> jsonKeys,
			                                                     final String name, 
			                                                     final Integer minArrayLength,
			                                                     final Integer maxArrayLength,
			                                                     final String errorMessagePrefix,
			         				                                     final int errorNumberMissing,
			         				                                     final int errorNumberIsNull,
			         				                                     final int errorNumberIsPrimitive,
			         				                                     final int errorNumberNotPrimitive,
			         				                                     final int errorNumberNotArray,
			         				                                     final int errorNumberException,
			         				                                     final int errorNumberTooSmall,
			         				                                     final int errorNumberTooLarge, 
			         				                                     final int errorNumberInvalidLength,
			         				                                     final int errorNumberInvalidWhiteSpace,
			                                                     final HDLmWhiteSpace whiteSpaceOK, 
			         				                                     final HDLmReportErrors reportErrors,
			         				                                     final HDLmZeroLengthOk zeroLength) {
		/* Check a few fields passed by the caller */
		if (errorCounter == null) {
			String  errorText = "Mutable int for error counter passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (errorMessages == null) {
			String  errorText = "ArrayList for error messages passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		/* The minimmum array length can be null */
		if (minArrayLength == null &&
				minArrayLength != null) {
			String  errorText = "Miniumum array length passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (maxArrayLength == null &&  
			  maxArrayLength != null) {
			String  errorText = "Maximumum array length passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (errorMessagePrefix == null) {
		  String  errorText = "Error message prefix passed to checkFieldStringArray is null";
		  throw new NullPointerException(errorText);
		}
		if (whiteSpaceOK == null) {
			String  errorText = "White space enum passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (whiteSpaceOK == HDLmWhiteSpace.NONE) {
			String  errorText = "White space enum passed to checkFieldStringArray is NONE";
			throw new IllegalArgumentException(errorText);
		}
		if (reportErrors == null) {
			String  errorText = "Report errors enum passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorText = "Report errors enum passed to checkFieldStringArray is NONE";
			throw new IllegalArgumentException(errorText);
		}
		if (zeroLength == null) {
			String  errorText = "Zero-length enum passed to checkFieldStringArray is null";
			throw new NullPointerException(errorText);
		}
		if (zeroLength == HDLmZeroLengthOk.NONE) {
			String  errorText = "Zero-length enum passed to checkFieldStringArray is NONE";
			throw new IllegalArgumentException(errorText);
		}
		ArrayList<String>   newStrings = null;	
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(name)) {
			/* Since we do not have the field in the JSON, report an error */
			String  errorText = errorMessagePrefix + " " + "JSON missing field";
			HDLmField.reportErrorNoValue(editorType, 
					                         errorCounter,
					                         errorMessages,
					                         jsonObject, 
					                         name, 
					                         errorText, 
					                         errorNumberMissing, 
					                         reportErrors);
			return null;
		}
		/* Get the array of JSON objects for the strings */
		JsonElement   jsonElement = jsonObject.get(name);
		if (jsonElement.isJsonNull()) {
			String  errorText = errorMessagePrefix + " " + "JSON field is null";
	    HDLmField.reportError(editorType, 
	   		                    errorCounter, 
							  	          errorMessages,
							  	          jsonObject,
								            name,
								            errorText, 
								            errorNumberIsNull, 
								            reportErrors);
			return null;
		}
		/* Check if the JSON element is a JSON primitive */
		if (jsonElement.isJsonPrimitive()) {
			String  errorText = errorMessagePrefix + " " + "JSON field is a JSON primitive";
	    HDLmField.reportError(editorType, 
	  		                    errorCounter, 
							  	          errorMessages,
							  	          jsonObject,
								            name,
								            errorText, 
								            errorNumberIsPrimitive, 
								            reportErrors);
			return null;
		}
		/* Get some values from the input JSON */
		if (!jsonElement.isJsonArray()) {
			String  errorText = errorMessagePrefix + " " + "JSON field is not an array";
	    HDLmField.reportError(editorType, 
	   		                    errorCounter, 
	                          errorMessages,
	                          jsonObject,
	                          name,
	                          errorText, 
	                          errorNumberNotArray,
	                          reportErrors);			
			return null;
	  }
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		int       jsonArraySize = jsonArray.size();
		/* Check the minimum array size value, in some cases */
		if (minArrayLength != null &&
				jsonArraySize < minArrayLength) {
			String  errorText = errorMessagePrefix + " " + "JSON array is too small";
	    HDLmField.reportError(editorType, 
	   		                    errorCounter, 
	                          errorMessages,
	                          jsonObject,
	                          name,
	                          errorText,
	                          errorNumberTooSmall, 
	                          reportErrors);			
			return null;
	  }
		/* Check the maximum array size value, in some cases */
		if (maxArrayLength != null &&
				jsonArraySize > maxArrayLength) {
			String  errorText = errorMessagePrefix + " " + "JSON array is too small"; 
	    HDLmField.reportError(editorType, 
	     		                  errorCounter, 
	                          errorMessages,
	                          jsonObject,
	                          name,
	                          errorText,
	                          errorNumberTooLarge, 
	                          reportErrors);			
			return null;
	  }
		/* Build the new string array list */
		newStrings = new ArrayList<String>();
		for (JsonElement jsonElementEntry : jsonArray) {
		  /* Check if the field is valid */
		  if (jsonElementEntry.isJsonNull()) {
		  	String  errorText = errorMessagePrefix + " " + "JSON field entry is null";
		    HDLmField.reportError(editorType, 
		      		                errorCounter, 
					  				          errorMessages,
						  			          jsonObject,
							  		          name,
								  	          errorText, 
									            errorNumberIsNull, 
									            reportErrors);
		    return null;
		  }
		  if (!jsonElementEntry.isJsonPrimitive()) {
		  	String  errorText = errorMessagePrefix + " " + "JSON field entry is not a primitive";
		    HDLmField.reportError(editorType, 
		    	                    errorCounter, 
		    	                    errorMessages,
		    	                    jsonObject,
		    	                    name,
		    	                    errorText, 
		    	                    errorNumberNotPrimitive, 
		    	                    reportErrors);
		    return null;
		  }
		  /* Get the string entry value */
		  String  newEntryStr = null;				
			try {
				newEntryStr = jsonElementEntry.getAsString();
			} 
			catch (ClassCastException classCastException) {
				String errorText = errorMessagePrefix + " " + "array entry is not a JSON string";
				/* Log the error */
				LOG.info(errorText);
				LOG.info(classCastException.getMessage(), classCastException);
				HDLmEvent.eventOccurred("ClassCastException");
		    HDLmField.reportError(editorType, 
		    	                    errorCounter, 
		    	                    errorMessages,
		    	                    jsonObject,
		    	                    name,
		    	                    errorText,  
		    	                    errorNumberException, 
		    	                    reportErrors);
		    return null;
			}
			catch (Exception exception) {
				String errorText = errorMessagePrefix + " " + "array entry is not a JSON string";
				/* Log the error */
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
		    HDLmField.reportError(editorType, 
							                errorCounter, 
								  	          errorMessages,
									            jsonObject,
									            name,
									            errorText,  
									            errorNumberException, 
									            reportErrors);
		    return null;
			}					
			/* Get the length of string, if possible. If the string   
				 is a null value, then getting the length is not possible. */
			int   newEntryStrLength = -1;
			if (newEntryStr != null)
					newEntryStrLength = newEntryStr.length();
			/* Generally we can accept a string that is zero-length.
		     However, in some cases we can not. The check below 
		     rejects strings that are zero-length, if need be. */
		  if (zeroLength == HDLmZeroLengthOk.ZEROLENGTHNOTOK) 		  	
		  	/* The following check has been made considerably more 
	  	     complex. It turns out that zero-length fields are 
	  	     considered to be white space. if WHITESPACEOK is
	  	     set, then a zero-length field is really OK. */ 
	  	  if (newEntryStrLength == 0 &&
		  		  whiteSpaceOK == HDLmWhiteSpace.WHITESPACENOTOK) { 
			  	String  errorText = errorMessagePrefix + " " + "JSON field entry is zero-length";
			    HDLmField.reportError(editorType, 
			      		                errorCounter, 
						  				          errorMessages,
							   			          jsonObject,
									  	          name,
										            errorText, 
										            errorNumberInvalidLength, 
										            reportErrors);
			    return null;
				}
			/* Generally we can accept a string that is all white space
			   characters. However, in some cases we can not. The check
			   below rejects strings that are all white space, if need be.
			   Note that an empty string is considered to be a white space
			   string by the utility routine invoked below. */
			if (whiteSpaceOK == HDLmWhiteSpace.WHITESPACENOTOK) { 
				/* In at least one case, we really do want to allow
				   a white space string to be accepted. If the string
				   length is zero and the ZEROLENGTHOK value is set, 
				   then the current string should not be treated as
				   white space. */ 
				boolean   newEntryOk = false;
				if (newEntryStrLength == 0 &&
						zeroLength == HDLmZeroLengthOk.ZEROLENGTHOK)
					newEntryOk = true;			
			  if (StringUtils.isWhitespace(newEntryStr) &&
			  		newEntryOk == false) {
			  	String  errorText = errorMessagePrefix + " " + "JSON field is white space";
			    HDLmField.reportError(editorType, 
			    		                  errorCounter, 
										            errorMessages,
										            jsonObject,
										            name,
										            errorText, 
										            errorNumberInvalidWhiteSpace, 
										            reportErrors);
			    return null;
				}
		  }
			/* Add the string entry to the string array */
			if (newEntryStr != null)
				newStrings.add(newEntryStr);
		}
		/* Return the new strings array list to the caller */
		return newStrings;
	}
	/* Report an error. The caller provides all of the values used to report the
	   error. This routine builds the error message text and reports the error. Note
	   that the caller does not provide any actual value as part of calling this
	   method. */
	protected static String reportError(final HDLmEditorTypes editorType, 
			                                final MutableInt errorCounter,
		                                  final ArrayList<String> errorMessages,
			                                final JsonObject jsonObject, 
			                                final String name,
			                                final String errorText, 
			                                final int errorNumber) {
	 return reportError(editorType, 
	 		               errorCounter,
	 		               errorMessages,
	 		               jsonObject,
	 		               name,
	 		               errorText, 
	 		               errorNumber, 
	 		               HDLmReportErrors.REPORTERRORS);
	}
	protected static String reportError(final HDLmEditorTypes editorType, 
			                                final MutableInt errorCounter, 
		                                  final ArrayList<String> errorMessages,
			                                final JsonObject jsonObject, 
			                                final String name, 
			                                String errorText, 
			                                final int errorNumber,
			                                final HDLmReportErrors reportErrors) {
		if (errorCounter == null) {
			String  errorNullText = "Mutable int for error counter passed to reportError is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorMessages == null) {
			String  errorTextLocal = "ArrayList for error messages passed to reportError is null";
			throw new NullPointerException(errorTextLocal);
		}
		if (jsonObject == null) {
			String  errorNullText = "JSON object passed to reportError is null";
			throw new NullPointerException(errorNullText);
		}
		if (name == null) {
			String  errorNullText = "Field name string passed to reportError is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorText == null) {
			String  errorNullText = "Error text string passed to reportError is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorNumber <= 0) {
			String  errorNumberText = "Error number passed to reportError is invalid";
			throw new IllegalArgumentException(errorNumberText);
		}
		if (reportErrors == null) {
			String  errorTextReport = "Report errors enum passed to reportError is null";
			throw new NullPointerException(errorTextReport);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorTextReport = "Report errors enum passed to reportError is NONE";
			throw new NullPointerException(errorTextReport);
		}
		/* Increment the error count */
		errorCounter.increment();
		/* Build the error message */
		errorText = errorText + " -";
		/* Add the field name */
		errorText += " name (";
		errorText += name;
		errorText += ")";
		/* Add the modification JSON */
		errorText += " JSON (";
		errorText += HDLmMod.truncateJson(jsonObject);
		errorText += ")";
		/* Save the error text in the error messages array list */
		errorMessages.add(errorText);
		/* Report the error, if need be */
		if (reportErrors == HDLmReportErrors.REPORTERRORS) {
			String   typeString = HDLmEditorServlet.getTypeEditor(editorType);
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, typeString, errorNumber, errorText);
		}
		return errorText;
	}
	/* Report an error. The caller provides all of the values used to report the
	   error. This routine builds the error message text and reports the error. Note
	   that the caller does not provide the actual value as part of calling this
	   method. */
	protected static String reportErrorNoValue(final HDLmEditorTypes editorType, 
			                                       final MutableInt errorCounter,
	                                           final ArrayList<String> errorMessages,
			                                       final JsonObject jsonObject, 
			                                       final String name, 
			                                       final String errorText,
			                                       final int errorNumber) {
		return reportErrorNoValue(editorType, 
				                      errorCounter,
				                      errorMessages,
				                      jsonObject, 
				                      name, 
				                      errorText, 
				                      errorNumber, 
				                      HDLmReportErrors.REPORTERRORS);
	}
	protected static String reportErrorNoValue(final HDLmEditorTypes editorType, 
			                                       final MutableInt errorCounter, 
					                                   final ArrayList<String> errorMessages,
			                                       final JsonObject jsonObject, 
			                                       final String name, 
			                                       String errorText,
			                                       final int errorNumber, 
			                                       final HDLmReportErrors reportErrors) {
		if (errorCounter == null) {
			String  errorNullText = "Mutable int for error counter passed to reportErrorNoValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorMessages == null) {
			String  errorTextLocal = "ArrayList for error messages passed to reportErrorNoValue is null";
			throw new NullPointerException(errorTextLocal);
		}
		if (jsonObject == null) {
			String  errorNullText = "JSON object passed to reportErrorNoValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (name == null) {
			String  errorNullText = "Field name string passed to reportErrorNoValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorText == null) {
			String  errorNullText = "Error text string passed to reportErrorNoValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorNumber <= 0) {
			String  errorNumberText = "Error number passed to reportErrorNoValue is invalid";
			throw new IllegalArgumentException(errorNumberText);
		}
		if (reportErrors == null) {
			String  errorTextReport = "Report errors enum passed to reportErrorNoValue is null";
			throw new NullPointerException(errorTextReport);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorTextReport = "Report errors enum passed to reportErrorNoValue is NONE";
			throw new NullPointerException(errorTextReport);
		}
		/* Increment the error count */
		errorCounter.increment();
		/* Build the error message */
		errorText = errorText + " -";
		/* Add the field name */
		errorText += " name (";
		errorText += name;
		errorText += ")";
		/* Add the modification JSON */
		errorText += " JSON (";
		errorText += HDLmMod.truncateJson(jsonObject);
		errorText += ")";
		/* Save the error text in the error messages array list */
		errorMessages.add(errorText);
		/* Report the error, if need be */
		if (reportErrors == HDLmReportErrors.REPORTERRORS) {
			String   typeString = HDLmEditorServlet.getTypeEditor(editorType);
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, typeString, errorNumber, errorText);
		}
		return errorText;
	}
	/* Report an error. The caller provides all of the values used to report the
	   error. This routine builds the error message text and reports the error. Note
	   that the caller provides the actual value as part of calling this method. */
	protected static String  reportErrorValue(final HDLmEditorTypes editorType, 
			                                      final MutableInt errorCounter, 
				                                    final ArrayList<String> errorMessages,
			                                      final JsonObject jsonObject, 
			                                      final String name, 
			                                      final String value,
			                                      final String errorText, 
			                                      final int errorNumber) {
		return reportErrorValue(editorType, 
				                    errorCounter, 
				                    errorMessages,
				                    jsonObject, 
				                    name, 
				                    value, 
				                    errorText, 
				                    errorNumber, 
				                    HDLmReportErrors.REPORTERRORS);
	}
	protected static String  reportErrorValue(final HDLmEditorTypes editorType, 
			                                      final MutableInt errorCounter, 
				                                    final ArrayList<String> errorMessages,
			                                      final JsonObject jsonObject, 
			                                      final String name, 
			                                      final String value,
			                                      String errorText, 
			                                      final int errorNumber, 
			                                      final HDLmReportErrors reportErrors) {
		if (errorCounter == null) {
			String  errorNullText = "Mutable int for error counter passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorMessages == null) {
			String  errorTextLocal = "ArrayList for error messages passed to reportErrorValue is null";
			throw new NullPointerException(errorTextLocal);
		}
		if (jsonObject == null) {
			String  errorNullText = "JSON object passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (name == null) {
			String  errorNullText = "Field name string passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (value == null) {
			String  errorNullText = "Value string passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorText == null) {
			String  errorNullText = "Error text string passed to reportErrorValue is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorNumber <= 0) {
			String  errorNumberText = "Error number passed to reportErrorValue is invalid";
			throw new IllegalArgumentException(errorNumberText);
		}
		if (reportErrors == null) {
			String  errorTextReport = "Report errors enum passed to reportErrorValue is null";
			throw new NullPointerException(errorTextReport);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorTextReport = "Report errors enum passed to reportErrorValue is NONE";
			throw new NullPointerException(errorTextReport);
		}		
		/* Increment the error count */
		errorCounter.increment();
		/* Build the error message */
		errorText = errorText + " -";
		/* Add the field name */
		errorText += " name (";
		errorText += name;
		errorText += ")";
		/* Add the invalid field value */
		errorText += " value (";
		errorText += value;
		errorText += ")";
		/* Add the modification JSON */
		errorText += " JSON (";
		errorText += HDLmMod.truncateJson(jsonObject);
		errorText += ")";
		/* Save the error text in the error messages array list */
		errorMessages.add(errorText);
		/* Report the error, if need be */
		if (reportErrors == HDLmReportErrors.REPORTERRORS) {
			String   typeString = HDLmEditorServlet.getTypeEditor(editorType);
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, typeString, errorNumber, errorText);
		}
		return errorText;
	}
	/* Report an invalid JSON field. This routine always report an error. This
	   routine always increments the error count. The caller provides the field
	   name, the field value, and the JSON. */
	protected static String reportField(final HDLmEditorTypes editorType, 
			                                final MutableInt errorCounter, 
		                                  final ArrayList<String> errorMessages,
			                                final JsonObject jsonObject, 
			                                final String name, 
			                                final String value,
			                                final String errorText, 
			                                final int errorNumber) {
	 return reportField(editorType, 
	 		                errorCounter,
	 		                errorMessages,
	 		                jsonObject, 
	 		                name, 
	 		                value, 
	                    errorText, 
	                    errorNumber,
	 		                HDLmReportErrors.REPORTERRORS);
	}
	protected static String reportField(final HDLmEditorTypes editorType, 
			                                final MutableInt errorCounter, 
		                                  final ArrayList<String> errorMessages,
			                                final JsonObject jsonObject, 
			                                final String name, 
			                                final String value,
			                                final String errorText, 
			                                final int errorNumber,
			                                final HDLmReportErrors reportErrors) {
		if (errorCounter == null) {
			String  errorTextReport = "Mutable int for error counter passed to reportField is null";
			throw new NullPointerException(errorTextReport);
		}
		if (errorMessages == null) {
			String  errorTextReport = "ArrayList for error messages passed to reportField is null";
			throw new NullPointerException(errorTextReport);
		}
		if (jsonObject == null) {
			String  errorTextReport = "JSON object passed to reportField is null";
			throw new NullPointerException(errorTextReport);
		}
		if (name == null) {
			String  errorTextReport = "Field name string passed to reportField is null";
			throw new NullPointerException(errorTextReport);
		}
		if (value == null) {
			String  errorTextReport = "Value string passed to reportField is null";
			throw new NullPointerException(errorTextReport);
		}
		if (errorText == null) {
			String  errorNullText = "Error text string passed to reportField is null";
			throw new NullPointerException(errorNullText);
		}
		if (errorNumber <= 0) {
			String  errorNumberText = "Error number passed to reportField is invalid";
			throw new IllegalArgumentException(errorNumberText);
		}
		if (reportErrors == null) {
			String  errorTextReport = "Report errors enum passed to reportField is null";
			throw new NullPointerException(errorTextReport);
		}
		if (reportErrors == HDLmReportErrors.NONE) {
			String  errorTextReport = "Report errors enum passed to reportField is NONE";
			throw new NullPointerException(errorTextReport);
		}		
		return HDLmField.reportErrorValue(editorType, 
				                            errorCounter, 
				                            errorMessages,
				                            jsonObject, 
				                            name, 
				                            value, 
				                            /* "Modification JSON invalid field", */ 
				                            /* 4, */
				                            errorText,
				                            errorNumber,
				                            reportErrors);
	}
}
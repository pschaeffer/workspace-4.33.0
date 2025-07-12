package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.websocket.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmWebSocketInternalAdapter short summary.
 *
 * HDLmWebSocketInternalAdapter description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class can definitely
   be created */
public class HDLmWebSocketInternalAdapter extends WebSocketAdapter {
	/* The next statement initializes logging to some degree. Note that having the
	   slf4j jars and the log4j jars in the classpath also plays some role in
	   logging initialization.*/
	private static final Logger LOG = LoggerFactory.getLogger(HDLmWebSocketInternalAdapter.class);
	/* Session seems to be the only class instance field. It is not quite clear what
	   this field should be used for. */
	private Session session;
	/* Close a web socket.The caller provides the web socket session
	   and the close code and the close reason. 
	   Of course, the low-level send operation can cause exceptions.
	   These exceptions are caught and reported. */
	protected static void  closeSession(final Session sessionToBeUsed,
			                                final int closeCode, 
                                      final String closeReason) {
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to closeSession is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the close code is valid */
		if (closeCode != 1000 &&
				(closeCode < 3000 || closeCode > 4999)) {
			String errorText = "Invalid close code passed to closeSession";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the close reason is null */
		if (closeReason == null) {
			String errorText = "Close reason string passed to closeSession is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the close reason is empty */
		if (closeReason.length() == 0) {
			String errorText = "Close reason string passed to closeSession is empty";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the close reason is white space */
		if (StringUtils.isBlank(closeReason)) {
			String errorText = "Close reason string passed to closeSession is blank";
			throw new IllegalArgumentException(errorText);
		}
		/* Encode the close reason string in UTF-8 */
		try {
		  byte[]  closeReasonBytes = closeReason.getBytes("UTF-8");	
			/* Actually close the web socket session */
			sessionToBeUsed.close(closeCode, new String(closeReasonBytes, "UTF-8"));
		}
		catch (UnsupportedEncodingException ueException) {
			if (closeReason != null)
				LOG.info("Close session reason - " + closeReason);
			LOG.info("UnsupportedEncodingException while executing web socket close");
			LOG.info(ueException.getMessage(), ueException);
			HDLmEvent.eventOccurred("UnsupportedEncodingException");
			return;
		} 
		catch (Exception exception) {
			if (closeReason != null)
				LOG.info("Close session reason - " + closeReason);
			LOG.info("Exception while executing web socket close");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
		return;
	} 
	/* Get and check a JSON array. The caller provides the JSON element and the
	   name of the JSON array. If the array is not found or is not a valid JSON 
	   array, then this routine returns null and sends a failure message to the 
	   client. */ 
	protected static JsonArray  getJsonArray(final JsonElement jsonElement, 
			                                     final String jsonArrayName,
			                                     final Session sessionToBeUsed) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getJsonArray is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON array name passed by the caller is null */
		if (jsonArrayName == null) {
			String  errorText = "JSON array name passed to getJsonArray is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to getJsonArray is null";
			throw new NullPointerException(errorText);
		}
		JsonArray   rv = null;
		/* Get and check the JSON array */
		rv = HDLmJson.getJsonArray(jsonElement, jsonArrayName);
		if (rv == null) {
		  String  errorFormat = "JSON array (%) is null";
		  String  errorText = String.format(errorFormat, jsonArrayName); 
		  HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 68);
		  return null;
		}	
		/* Make sure we really have a JSON array */
		if (rv.isJsonArray() == false) {
			String errorformat = "Value JSON for array name (%s) is not an JSON array";
			String errorText = String.format(errorformat, jsonArrayName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 69);
			return null;
		}
		return rv;
	}
	/* Get and check a JSON array and the size of the JSON array. The caller
	   provides the JSON element and the name of the JSON array and the minimum
	   and maximum sizes. If the array is not found or is not a valid JSON 
     array, then this routine returns null and sends a failure message to the 
     client. If the array size is invalid, this routine returns null and sends 
     a failure message to the client. If the all checks are OK, then the JSON 
     array size is returned to the caller. */ 
  protected static Integer  getJsonArrayCheckSize(final JsonElement jsonElement, 
		                                              final String jsonArrayName,
		                                              final Session sessionToBeUsed,
		                                              final Integer minimumSize,
		                                              final Integer maximumSize,
		                                              final Integer specificSize) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getJsonArrayCheckSize is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON array name passed by the caller is null */
		if (jsonArrayName == null) {
			String  errorText = "JSON array name passed to getJsonArrayCheckSize is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to getJsonArraCheckSizey is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the minimum size value is too low */ 
		if (minimumSize != null)
			if (minimumSize < 0) {
	  		String errorText = "Minimum size value passed to getJsonArrayCheckSize is invalid";
		 	 throw new IllegalArgumentException(errorText);
		  }
		/* Check if the maximum size value is too high */ 
		if (maximumSize != null)
			if (maximumSize <= 0) {
	  		String errorText = "Maximum size value passed to getJsonArrayCheckSize is invalid";
		 	 throw new IllegalArgumentException(errorText);
		  } 
		/* Check if the specific size value was passed and if it is valid */
		if (specificSize != null)
			if (specificSize <= 0) {
	  		String errorText = "Specific size value passed to getJsonArrayCheckSize is invalid";
		 	 throw new IllegalArgumentException(errorText);
		  }
		/* Get and check the JSON array */
		JsonArray   localJsonArray = HDLmWebSocketInternalAdapter.getJsonArray(jsonElement, 
				                                                                   jsonArrayName, 
				                                                                   sessionToBeUsed);
		if (localJsonArray == null)
		  return null; 
		/* Get the size of the JSON array */		  
		int   localJsonArraySize = localJsonArray.size();
		/* Check if the minimum size is specified and if the actual size is too small */
		if (minimumSize != null)
			if (localJsonArraySize < minimumSize) {
		  	String errorFormat = "JSON array size (%d) is too small for array name (%s)";
			  String errorText = String.format(errorFormat, localJsonArraySize, jsonArrayName);
			  HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 70);
			  return null;
		  }
		/* Check if the maximum size is specified and if the actual size is too large */
		if (maximumSize != null)
			if (localJsonArraySize > maximumSize) {
		  	String errorFormat = "JSON array size (%d) is too large for array name (%s)";
			  String errorText = String.format(errorFormat, localJsonArraySize, jsonArrayName);
			  HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 71);
			  return null;
		  }
		/* Check if the specific size is specified and if the actual size is incorrect */
		if (specificSize != null)
			if (localJsonArraySize != specificSize) {
		  	String errorFormat = "JSON array size (%d) is incorrect for array name (%s)";
			  String errorText = String.format(errorFormat, localJsonArraySize, jsonArrayName);
			  HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 49);
			  return null;
		  }
		/* Return the valid size of the JSON array to the caller */
		return (Integer) localJsonArraySize ;
	}
  /* Get and check a JSON boolean. The caller provides the JSON element and the
	   name of the JSON boolean. If the boolean is not found or is not a valid JSON 
	   boolean, then this routine returns null and sends a failure message to the 
	   client. */ 
	protected static Boolean  getJsonBoolean(final JsonElement jsonElement, 
			                                     final String jsonBooleanName,
			                                     final Session sessionToBeUsed) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getJsonBoolean is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON boolean name passed by the caller is null */
		if (jsonBooleanName == null) {
			String  errorText = "JSON boolean name passed to getJsonBoolean is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to getJsonBoolean is null";
			throw new NullPointerException(errorText);
		}
		/* Get and check the JSON value */
		JsonElement   booleanJson = HDLmJson.getJsonValue(jsonElement, jsonBooleanName);   
		if (booleanJson == null) {
		  String  errorFormat = "JSON boolean (%) is null";
		  String  errorText = String.format(errorFormat, jsonBooleanName); 
		  HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 68);
		  return null;
		}	
		/* Make sure we really have a JSON boolean */
		if (booleanJson.isJsonPrimitive() == false) {
			String errorformat = "Value JSON for boolean name (%s) is not a JSON primitive";
			String errorText = String.format(errorformat, jsonBooleanName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 86);
			return null;
		}
		/* Get a proper Boolean value or null if the value is not a boolean */
		Boolean   rvBoolean = null;
		try {
			rvBoolean = booleanJson.getAsBoolean();
		} 
		catch (ClassCastException classCastException) {
			String errorformat = "Value JSON for boolean name (%s) is not a JSON boolean";
			String errorText = String.format(errorformat, jsonBooleanName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 86);
			LOG.info(errorText);
			LOG.info(classCastException.getMessage(), classCastException);
			HDLmEvent.eventOccurred("ClassCastException");
			return null;
		}
		catch (Exception exception) {
			String errorformat = "Value JSON for boolean name (%s) is not a JSON boolean";
			String errorText = String.format(errorformat, jsonBooleanName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 86);
			LOG.info(errorText);
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return null;
		}
		return rvBoolean;
	}
  /* Get and check a JSON integer. The caller provides the JSON element and the
	   name of the JSON integer. If the integer is not found or is not a valid JSON 
	   integer, then this routine returns null and sends a failure message to the 
	   client. */ 
	protected static Integer  getJsonInteger(final JsonElement jsonElement, 
			                                     final String jsonIntegerName,
			                                     final Session sessionToBeUsed) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getJsonInteger is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON integer name passed by the caller is null */
		if (jsonIntegerName == null) {
			String  errorText = "JSON integer name passed to getJsonInteger is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to getJsonInteger is null";
			throw new NullPointerException(errorText);
		}
		/* Get and check the JSON value */
		JsonElement   integerJson = HDLmJson.getJsonValue(jsonElement, jsonIntegerName);   
		if (integerJson == null) {
		  String  errorFormat = "JSON integer (%) is null";
		  String  errorText = String.format(errorFormat, jsonIntegerName); 
		  HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 68);
		  return null;
		}	
		/* Make sure we really have a JSON integer */
		if (integerJson.isJsonPrimitive() == false) {
			String errorformat = "Value JSON for integer name (%s) is not a JSON primitive";
			String errorText = String.format(errorformat, jsonIntegerName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 87);
			return null;
		}
		/* Get a proper Integer value or null if the value is not an integer */
		Integer   rvInteger = null;
		try {
			rvInteger = integerJson.getAsInt();
		} 
		catch (ClassCastException classCastException) {
			String errorformat = "Value JSON for integer name (%s) is not a JSON integer";
			String errorText = String.format(errorformat, jsonIntegerName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 87);
			LOG.info(errorText);
			LOG.info(classCastException.getMessage(), classCastException);
			HDLmEvent.eventOccurred("ClassCastException");
			return null;
		}
		catch (Exception exception) {
			String errorformat = "Value JSON for integer name (%s) is not a JSON integer";
			String errorText = String.format(errorformat, jsonIntegerName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 87);
			LOG.info(errorText);
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return null;
		}
		return rvInteger;
	}
  /* Get and check a JSON object. The caller provides the JSON element and the
	   name of the JSON object. If the JSON object is not found or is not a valid 
	   JSON object, then this routine returns null and sends a failure message to
	   the client. */ 
	protected static JsonObject  getJsonObject(final JsonElement jsonElement, 
			                                       final String jsonObjectName,
			                                       final Session sessionToBeUsed) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getJsonObject is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON object name passed by the caller is null */
		if (jsonObjectName == null) {
			String  errorText = "JSON object name passed to getJsonObject is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to getJsonObject is null";
			throw new NullPointerException(errorText);
		}
		/* Get and check the JSON object */
		JsonObject  objectJson = HDLmJson.getJsonObject(jsonElement, jsonObjectName);   
		if (objectJson == null) {
		  String  errorFormat = "JSON Object (%) is null";
		  String  errorText = String.format(errorFormat, jsonObjectName); 
		  HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 68);
		  return null;
		}	
		/* Make sure we really have a JSON object */
		if (objectJson.isJsonObject() == false) {
			String errorformat = "Object JSON for string name (%s) is not a JSON Object";
			String errorText = String.format(errorformat, jsonObjectName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 85);
			return null;
		}
		/* Get a proper JsonObject value or null if the value is not an JSON object */
		JsonObject  rvObject = null;
		try {
			rvObject = objectJson;
		} 
		catch (ClassCastException classCastException) {
			String errorformat = "Value JSON for object name (%s) is not a JSON object";
			String errorText = String.format(errorformat, jsonObjectName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 85);
			LOG.info(errorText);
			LOG.info(classCastException.getMessage(), classCastException);
			HDLmEvent.eventOccurred("ClassCastException");
			return null;
		}
		catch (Exception exception) {
			String errorformat = "Value JSON for object name (%s) is not a JSON object";
			String errorText = String.format(errorformat, jsonObjectName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 85);
			LOG.info(errorText);
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return null;
		}
		return rvObject;
	}
	/* Get and check a JSON string. The caller provides the JSON element and the
	   name of the JSON string. If the string is not found or is not a valid JSON 
	   string, then this routine returns null and sends a failure message to the 
	   client. */ 
	protected static String  getJsonString(final JsonElement jsonElement, 
			                                   final String jsonStringName,
			                                   final Session sessionToBeUsed) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getJsonString is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON string name passed by the caller is null */
		if (jsonStringName == null) {
			String  errorText = "JSON string name passed to getJsonString is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to getJsonString is null";
			throw new NullPointerException(errorText);
		}
		/* Get and check the JSON value */
		JsonElement   stringJson = HDLmJson.getJsonValue(jsonElement, jsonStringName);   
		if (stringJson == null) {
		  String  errorFormat = "JSON string (%) is null";
		  String  errorText = String.format(errorFormat, jsonStringName); 
		  HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 68);
		  return null;
		}	
		/* Make sure we really have a JSON string */
		if (stringJson.isJsonPrimitive() == false) {
			String errorformat = "Value JSON for string name (%s) is not a JSON primitive";
			String errorText = String.format(errorformat, jsonStringName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 84);
			return null;
		}
		/* Get a proper String value or null if the value is not a string */
		String  rvString = null;
		try {
			rvString = stringJson.getAsString();
		} 
		catch (ClassCastException classCastException) {
			String errorformat = "Value JSON for string name (%s) is not a JSON string";
			String errorText = String.format(errorformat, jsonStringName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 84);
			LOG.info(errorText);
			LOG.info(classCastException.getMessage(), classCastException);
			HDLmEvent.eventOccurred("ClassCastException");
			return null;
		}
		catch (Exception exception) {
			String errorformat = "Value JSON for string name (%s) is not a JSON string";
			String errorText = String.format(errorformat, jsonStringName);
			/* Send a failure message back to the client */
			HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 84);
			LOG.info(errorText);
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return null;
		}
		return rvString;
	}
	/* This routine checks the existence of the node path in the JSON element 
	   and checks the size of the node path. The caller provides the JSON element 
	   and the session to be used for sending failure messages. If the node path 
	   is not found or is not a valid JSON array, then this routine returns null 
	   and sends a failure message to the client. If the node path size is invalid,
	   this routine returns null and sends a failure message to the client. Each
	   element in the node path must be a non-empty string. If all checks are OK, 
	   then the node path is returned to the caller. */ 
	protected static ArrayList<String>  getNodePathFromJson(final JsonElement jsonElement, 
			                                                    final Integer specificLength,
		                                                      final Session sessionToBeUsed) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getNodePathFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the specific length value was passed and if it is valid */
		if (specificLength != null)
			if (specificLength <= 0) {
	  		String errorText = "Specific size value passed to getNodePathFromJson is invalid";
		 	 throw new IllegalArgumentException(errorText);
		  }
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to getNodePathFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Get the minimum node path length. This is the minimum 
	     node path length needed to get a host name.  */
	  int   minPathLength = HDLmDefines.getNumber("HDLMHOSTNAMENODEPATHLENGTH");
	  /* Get the maximum node path length */
	  int   maxPathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		/* Get and check the JSON array */
		String    jsonArrayName = "nodePath";
		Integer   localJsonArraySize = HDLmWebSocketInternalAdapter.getJsonArrayCheckSize(jsonElement, 
				                                                                              jsonArrayName, 
				                                                                              sessionToBeUsed,
				                                                                              minPathLength,
				                                                                              maxPathLength,
				                                                                              specificLength);
		if (localJsonArraySize == null)
		  return null; 
		/* Get the actual node path from the JSON element */
		ArrayList<String>   nodePathArrayList = HDLmWebSocketInternalAdapter.getStringsFromJson(jsonElement, 
				                                                                                    jsonArrayName,
	                                                                                    			sessionToBeUsed,
	                                                                                    			HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (nodePathArrayList == null)
		  return null; 
		/* Return the node path to the caller */
		return nodePathArrayList;
	}
	/* This routine checks the existence of an array in the JSON element. Each 
	   element in the array must be a valid string. The caller provides the JSON
	   element and the name of the JSON array to be checked. The caller also provides 
	   the session to be used for sending failure messages. If the array is not found
 	   or is not a valid JSON array, then this routine returns null and sends a failure
 	   message to the client. Each element in the node path must be a valid. If all checks
 	   are OK, then a string array is returned to the caller. */ 
	@SuppressWarnings("unused")
	protected static ArrayList<String>  getStringsFromJson(final JsonElement jsonElement, 
		                                                     final String jsonArrayName,
		                                                     final Session sessionToBeUsed,
		                                                     final HDLmZeroLengthOk zeroLengthOk) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getStringsFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON array name passed by the caller is null */
		if (jsonArrayName == null) {
			String  errorText = "JSON array name passed to getStringsFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to getStringsFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the zero length OK enum passed by the caller is null */
		if (zeroLengthOk == null) {
			String  errorText = "Zero length OK enum passed to getStringsFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Get and check the JSON array */ 
		JsonArray   localJsonArray = HDLmWebSocketInternalAdapter.getJsonArray(jsonElement, 
				                                                                   jsonArrayName, 
				                                                                   sessionToBeUsed);
		if (localJsonArray == null)
		  return null; 
		/* Convert the JSON array to an ArrayList<String> */
		ArrayList<String>   localArrayList = new ArrayList<String>();
		if (localArrayList == null) {
			String  errorText = "ArrayList allocation in getStringsFromJson is null";
			throw new NullPointerException(errorText);
		}	
		/* Loop over and process each element of the JSON array */
		for (JsonElement arrayEntry : localJsonArray) {
			/* Check if the array entry is null */
			if (arrayEntry.isJsonNull()) {
				String errorText = "Array entry is null in getStringsFromJson";
				HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 79); 
				break;
			}
			/* Check if the array entry is not a JSON primitive */
			if (arrayEntry.isJsonPrimitive() == false) {
				String errorText = "Array entry is not a JSON primitive in getStringsFromJson";
				HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 82);
				break;
			}
			/* Try to convert the array entry to a string. If this fails, then 
			   we do not have a JSON string than can be converted to a Java String, 
			   If this works, then we do have JSON string than can be converted to
			   a Java String. */
			String  arrayEntryString = null;
			try {
				arrayEntryString = arrayEntry.getAsString();
			} 
			catch (ClassCastException classCastException) {
				String errorText = "Array entry is not a JSON string";
				/* Send a failure message back to the client */
				HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 84);
				LOG.info(errorText);
				LOG.info(classCastException.getMessage(), classCastException);
				HDLmEvent.eventOccurred("ClassCastException");
				return null;
			}
			catch (Exception exception) {
				String errorText = "Array entry is not a JSON string";
				/* Send a failure message back to the client */
				HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 84);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				return null;
			}			
			/* Get and check the length of the array entry string */			
			int   arrayEntryStringLength = arrayEntryString.length();
			/* Check if the array entry string is empty */
			if (zeroLengthOk == HDLmZeroLengthOk.ZEROLENGTHNOTOK && 
					arrayEntryStringLength == 0) {
				String errorText = "Array entry string is empty in getStringsFromJson";
				HDLmWebSocketInternalAdapter.sendFailure(sessionToBeUsed, errorText, 84);
				break;
			}
			localArrayList.add(arrayEntryString);
		}
		/* Return the strings to the caller */
		return localArrayList;
	}
	/* This routine is invoked to handle inbound web sockets text messages. These
	   messages should be in JSON format and should be handleable via JSON. */
	protected void         handleMessage(String message) {
		/* Get the lock used to serialize all WebSocket operations */
		HDLmDatabase.getDatabaseLock();
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
  		/* Check if the JSON message instance passed by the caller is null */
  		if (message == null) {
			  String  errorText = "JSON message instance passed to handleMessage is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
		  }
	  	/* Get the length of the current message value */
		  int   messageLength = message.length();
		  /* Check if the JSON message length passed by the caller is zero */
		  if (messageLength == 0) {
			  String  errorText = "JSON message length passed to handleMessage is zero";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 49);
			  break;
		  }
		  /* Try to convert the message JSON to a JSON object. If this fails, then we do
		     not have a string than can be converted to a JSON object. If this works, then
		     we do have string than can be converted to a JSON object. */
		  JsonParser    parser = new JsonParser();		
		  JsonElement   topNodeJsonElement = null; 
			boolean       logDebugEnabled = LOG.isDebugEnabled();
			try {
				if (logDebugEnabled)
				  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessage - about to parse inbound message");
				topNodeJsonElement = parser.parse(message);
				if (logDebugEnabled)
				  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessage - after parse of inbound message");
			} 
			catch (Exception exception) {
				if (message != null)
					HDLmUtility.logStringInParts("handleMessage", message);
				String errorText = "Exception while executing handleMessage";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				break;
			}		
			/* Check if the JSON message passed by the caller is valid */
			if (!topNodeJsonElement.isJsonObject()) {
				String  errorText = "JSON message passed to handleMessage is invalid";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 75);
				break;
			}
			/* Get the current request type from the message (if possible) */
			String  requestType = HDLmWebSocketInternalAdapter.getJsonString(topNodeJsonElement, 
					                                                             "HDLmRequestType",
					                                                             session);
			if (requestType == null)
			  break; 
			/* Now that we have obtained the request type, we don't need the request type in
			   the JSON */
			HDLmJson.removeJsonKey(topNodeJsonElement, "HDLmRequestType");
			/* Try to get the URL value from the message. The URL value may or may not be
			   available. */
			String urlStr = null;
			if (HDLmJson.hasJsonKey(topNodeJsonElement, "HDLmUrlValue")) {
				urlStr = HDLmWebSocketInternalAdapter.getJsonString(topNodeJsonElement, 
						                                                "HDLmUrlValue", 
						                                                session);
				if (urlStr == null) 
				  break;		
				HDLmJson.removeJsonKey(topNodeJsonElement, "HDLmUrlValue");
			}
			/* Get the copy elements boolean value and remove the key */
			Boolean   nodeCopyElements = false;
			String newCopyElementsKey = "HDLmCopyElements";
			if (HDLmJson.hasJsonKey(topNodeJsonElement, newCopyElementsKey)) {
				nodeCopyElements = HDLmWebSocketInternalAdapter.getJsonBoolean(topNodeJsonElement, 
						                                                           newCopyElementsKey,
						                                                           session);
				if (nodeCopyElements == null)
				  break;
				HDLmJson.removeJsonKey(topNodeJsonElement, newCopyElementsKey);
			}
			/* We can now handle each type of request. Handle each request type */
			switch (requestType) {
			  case "addTreeNode":
				  handleMessageAddTreeNode(topNodeJsonElement);
				  break;
	      /* It doesn't look like the web socket client ever sends this
	         message. It appears that the client doesn't use buildNode
	         in any form. This appears to be left over from an earlier
	         version of the client code. Actually it appears that 
	         HDLmExtensionBothManageRule_saved and HDLmExtensionBothNodeIden
	         use this message. */
			  case "buildNode":
				  handleMessageBuild(topNodeJsonElement, urlStr, nodeCopyElements);
				  break;
			  /* It doesn't look like the web socket client ever sends this message. It
			     appears that the client doesn't use copyNode(s) in any form. This appears 
			     to be left over from an earlier version of the client code. Actually it 
			     appears that HDLmExtensionBothManageRule_saved and HDLmExtensionBothNodeIden
	         use this message. */
			  case "copyNode":
				  handleMessageCopy(topNodeJsonElement, urlStr, nodeCopyElements);
				  break;
			  case "deleteTreeNode":
				  handleMessageDeleteTreeNode(topNodeJsonElement);
				  break;
			  case "getConfigValues":
				  handleMessageGetConfigValues(topNodeJsonElement);
				  break;
			  case "getModPart":
				  handleMessageGetModPart();
				  break;
			  case "getImageChoices":
				  handleMessageGetImageChoices(topNodeJsonElement, urlStr);
				  break;
			  /* It doesn't look like the web socket client ever sends this message. It
			     appears that the client doesn't use getModTree in any form. This appears 
			     to be left over from an earlier version of the client code. */
			  case "getModTree":
				  handleMessageGetPassThruTree();
				  break;
			  case "getTextChoices":
				  handleMessageGetTextChoices(topNodeJsonElement, urlStr);
				  break;
		    /* It doesn't look like the web socket client ever sends this message. It
			     appears that the client doesn't use getModTree in any form. This appears 
			     to be left over from an earlier version of the client code. */
		  	case "reloadNodes":
				  handleMessageReloadNodes();
				  break;
		    /* It doesn't look like the web socket client ever sends this message. It
			     appears that the client doesn't use saveDataValue in any form. The 
			     routine called below was never completed and never used. */ 
			  case "saveDataValue":
				  handleMessageSaveDataValue(topNodeJsonElement);
				  break;
			  case "storeTreeNodes":
				  handleMessageStoreTreeNodes(topNodeJsonElement);
				  break;
				/* Transfer something from one system (probably test) to another system
				   (probably production). The caller specifies all of the details of what
				   should be transferred. */ 
			  case "transferSomething":
			  	handleMessageTransferSomething(topNodeJsonElement); 
			  	break;
				/* Undo the last operation that was done. This is the 'current' operation.
				   This is a very general undo operation. This operation might fail if 
				   there is no 'current' operation. */
				case "unDo":
					handleMessageUnDo(topNodeJsonElement);
					break;
			  /* It doesn't look like the web socket client ever sends this message. It
			     appears that the client doesn't use UpdateTree in any form. This appears 
			     to be left over from an earlier version of the client code. Actually it 
			     appears that HDLmGEM and HDLmGXE_Saved use this message.*/
			  case "updateTree":
				  handleMessageUpdateTree(topNodeJsonElement, urlStr);
				  break;
			  case "updateTreeNode":
				  handleMessageUpdateTreeNode(topNodeJsonElement);
				  break;
			  /* Report an error if the request type did not match one of the expected choices */
			  default:
				  String  errorFormat = "Invalid request type (\"%s\") passed to this routine";
				  String  errorText = String.format(errorFormat, requestType);
				  HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Invalid Request Type", 29, errorText);
				  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 29);
				  break;
			}
			break;
		}
		/* Close the web socket session */
		if (session != null) 
		  HDLmWebSocketInternalAdapter.closeSession(session, 3000, "Close the web socket session in all cases");
		/* Release the lock used to serialize all WebSocket operations */
		HDLmDatabase.releaseDatabaseLock();
	}
	/* This routine is invoked to handle inbound web sockets add tree node messages.
	   These messages should be in JSON format and should be handleable via JSON. */
	@SuppressWarnings("unused")
	protected void         handleMessageAddTreeNode(JsonElement jsonElement) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageAddTreeNode is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			JsonArray   nodePathJson;
			Integer     nodePathJsonSize; 
			/* Get the minimum node path length. This is the minimum 
			   node path length needed to get a host name.  */
			int   hostNamePathLength = HDLmDefines.getNumber("HDLMHOSTNAMENODEPATHLENGTH");
			/* Set the specific path length to null. This is used below to check 
			   the size of the node path JSON. */
			Integer   specificPathLength = null;
			/* Check if the JSON for the tree object (HDLmTree) is valid or not.
			   This call also check the embedded rule (HDLmMod) inside the JSON. */ 
			HDLmResponse  jsonResponse = HDLmTree.checkTreeJsonObj(jsonElement);
			int           errorsCount = jsonResponse.getReturnNumber();
			if (errorsCount > 0) {
				/* At this point, we must send a standard failure message back
				   to the caller. This failure message is a JSON string that
				   indicates that the operation failed. The failure message
				   contains a failure code and an error message. The failure
				   code is a number that indicates the type of failure. The
				   error message is a string that describes the failure to
				   a limited extent. Details about the failure are also in 
				   the log. */
				String  errorMessage = jsonResponse.getErrorMessage(); 
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorMessage, 4);
			  break;
				/* Close the web socket session */
				/* HDLmWebSocketInternalAdapter.closeSession(session, 3000, "Close the web socket session after an error"); */ 
			}
			/* Get and check the node path JSON and the size of the node path JSON */
			ArrayList<String>   addNodePath = HDLmWebSocketInternalAdapter.getNodePathFromJson(jsonElement,  
					                                                                               specificPathLength, 
                                                                                         session); 
			if (addNodePath == null)
		    break;			
			/* Get the host name from the node path. The node path will always contain the
	  	   correct host name. */
			String  hostName = addNodePath.get(hostNamePathLength - 1);
			/* Try to find the tree node from the add node path. This node should
			   not exist at this point. */  
		  HDLmTree  addNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
				                                          addNodePath);
			if (addNode != null) {			
				String  errorText = "Tree node already exists";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 59);
			  break;
				/* Close the web socket session */
				/* HDLmWebSocketInternalAdapter.closeSession(session, 3000, "Close the web socket session after locate failed"); */ 
			}		 
			/* Pass the JSON element to another routine for further handling */
			HDLmTree.addTreeNode(jsonElement, hostName, addNodePath);
			/* Log the current change */		  
			HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
							                HDLmChangeTypes.CHANGETYPEADD,
							                addNodePath, 
							                jsonElement); 
			/* Send a success message back to the caller */ 
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
		  break;
		}
	}
	/* This routine is invoked to handle inbound web sockets build messages. These
	   messages should be in JSON format and should be handleable via JSON. */
	@SuppressWarnings("unused")
	protected void         handleMessageBuild(JsonElement jsonElement, 
			                                      String urlStr, 
			                                      boolean nodeCopyElements) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageBuild is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Check if the URL string reference passed by the caller is null */
			if (urlStr == null) {
				String  errorText = "URL string reference passed to handleMessageBuild is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Get the host name from the URL stored in the JSON elements */
			String  hostName = HDLmUtility.getHostNameFromUrlWithCheck(urlStr);
			if (hostName == null) {
				String  errorText = "Host name not obtained from inbound URL in handleMessageBuild";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 74);
			  break;
			}
		  /* Check if the JSON for the node identifier object (HDLmNodeIden) 
		     is valid or not */ 
			HDLmResponse  jsonResponse = HDLmNodeIden.checkNodeIdenJsonObj(jsonElement);
			int           errorsCount = jsonResponse.getReturnNumber();
			if (errorsCount > 0) {
				String  errorMessage = jsonResponse.getErrorMessage();
				HDLmWebSocketInternalAdapter.sendFailure(session, errorMessage, 39);
				break;
				/* Close the web socket session */
				/* HDLmWebSocketInternalAdapter.closeSession(session, 3000, "Close the web socket session after an error"); */ 
			}		
			/* Declare a variable used below */ 
			ArrayList<String>   addNodePath;
			/* Pass the JSON element to another routine for further handling */			 
			HDLmTree  newTreeNode = HDLmTree.addNodeIden(jsonElement, 
					                                         hostName, 
					                                         urlStr, 
					                                         nodeCopyElements);
			/* Check if a new tree node wsa returned by the above routine */
			if (newTreeNode != null)
				addNodePath = newTreeNode.getNodePath();
			/* The code below is vary unlikely be executed. It is provided
			   as fallback dode in case the above routine fails to return
			   a new tree node. This code should never be executed. */
			else { 
			  addNodePath = HDLmTree.getNodePath(HDLmTree.getNodePassTreeTop(), 
				  	                               hostName, 
					                                 HDLmTreeTypes.MOD);
			}
			/* Log the current change */		  
			HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
							                HDLmChangeTypes.CHANGETYPEADDNODEIDEN,
							                addNodePath, 
							                jsonElement); 
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
		} 
	}
	/* This routine is invoked to handle inbound web sockets copy messages. These
	   messages should be in JSON format and should be handleable via JSON. */
	protected void         handleMessageCopy(JsonElement jsonElement, 
			                                     String urlStr, 
			                                     boolean nodeCopyElements) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageCopy is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Check if the URL string reference passed by the caller is null */
			if (urlStr == null) {
				String  errorText = "URL string reference passed to handleMessageCopy is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Get the host name from the URL stored in the JSON elements */
			String  hostName = HDLmUtility.getHostNameFromUrlWithCheck(urlStr);
			if (hostName == null) {
				String  errorText = "Host name not obtained from inbound URL in handleMessageCopy";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 74);
			  break;
			}
		  /* Check if the JSON for the node identifier object (HDLmNodeIden) 
	       is valid or not */ 
		  HDLmResponse  jsonResponse = HDLmNodeIden.checkNodeIdenJsonObj(jsonElement);
		  int           errorsCount = jsonResponse.getReturnNumber();
		  if (errorsCount > 0) {
		  	String  errorMessage = jsonResponse.getErrorMessage();
		  	HDLmWebSocketInternalAdapter.sendFailure(session, errorMessage, 39);
		  	break;
			  /* Close the web socket session */
			  /* HDLmWebSocketInternalAdapter.closeSession(session, 
			                                               3000, 
			                                               "Close the web socket session after an error"); */
		  }  
			/* Declare a variable used below */ 
			ArrayList<String>   addNodePath;
			/* Pass the JSON element to another routine for further handling */			 
			HDLmTree  newTreeNode = HDLmTree.addNodeIden(jsonElement, 
					                                         hostName, 
					                                         urlStr, 
					                                         nodeCopyElements);
			/* Check if a new tree node wsa returned by the above routine */
			if (newTreeNode != null)
				addNodePath = newTreeNode.getNodePath();
			/* The code below is vary unlikely be executed. It is provided
			   as fallback dode in case the above routine fails to return
			   a new tree node. This code should never be executed. */
			else { 
			  addNodePath = HDLmTree.getNodePath(HDLmTree.getNodePassTreeTop(), 
				  	                               hostName, 
					                                 HDLmTreeTypes.MOD);
			}
			/* Log the current change */		  
			HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
							                HDLmChangeTypes.CHANGETYPECOPYNODEIDEN,
							                addNodePath, 
							                jsonElement);  
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
	  }		
	}
	/* This routine is invoked to handle inbound web sockets delete tree node
	   messages. These messages should be in JSON format and should be handleable
	   via JSON. */
	@SuppressWarnings("unused")
	protected void         handleMessageDeleteTreeNode(JsonElement jsonElement) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
		  /* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageDeleteTreeNode is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Set the specific path length to null. This is used below to check 
		     the size of the node path JSON. */
		  Integer   specificPathLength = null;
			/* Get and check the node path */
			ArrayList<String>   deleteNodePath = HDLmWebSocketInternalAdapter.getNodePathFromJson(jsonElement,  
					                                                                                  specificPathLength,
					                                                                                  session);
			if (deleteNodePath == null)
		    break;
			/* Try to find the tree node for the delete node path. This will be the node
			   that is about to be deleted. */
			HDLmTree deleteNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
					                                          deleteNodePath);
			if (deleteNode == null) {
				String  nodeString = deleteNodePath.toString(); 
				HDLmUtility.logString(nodeString, LOG);
				HDLmUtility.logStackTrace();
				/* Send a failure message back to the client */
				String  errorText = "Null delete tree node returned by locateTreeNode";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 9);
				break;
			}
			/* Delete the node tree database for the delete node */
			HDLmTree.deleteNodeTreeDatabase(deleteNode);
			/* Log the current change */		  
			HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
							                HDLmChangeTypes.CHANGETYPEDELETE,
							                deleteNodePath, 
							                jsonElement); 
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
		}
	}
	/* This routine is invoked to handle inbound web sockets get configuration 
     values messages. These messages should be in JSON format and should be
     handleable via JSON. */
	protected void          handleMessageGetConfigValues(JsonElement jsonElement) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageGetConfigValues is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Get and check the node path JSON and the size of the node path JSON */
			ArrayList<String>  configNames = HDLmWebSocketInternalAdapter.getStringsFromJson(jsonElement, 
					                                                                             "configNames", 
					                                                                             session,
					                                                                             HDLmZeroLengthOk.ZEROLENGTHNOTOK);
			if (configNames == null)
			  break;
			/* Get the configuration names length */	
			int  configNamesSize = configNames.size();
			/* Check if the configuration names length is invalid */
			if (configNamesSize <= 0) {
				String  errorFormat = "Get configuration names length (%d) is incorrect";
				String  errorText = String.format(errorFormat, configNamesSize);
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 49);
			  break;
			}
			/* Build an array list with the configuration names that
			   are allowed to be requested. Configuration names not 
			   in this list will not be returned. */ 
			final ArrayList<String>  allowedConfigNames = new ArrayList<String>(
			                           Arrays.asList("entriesBridgePassword", "entriesBridgeUserid"));
		  /* create a new configuration object */
		  HDLmConfigValues  configValues = new HDLmConfigValues();
		  /* Loop over and process each element of the string array.
		     Each element is a configuration name for which the 
		     value is needed.  */
			for (String configNamesEntry : configNames) {
				/* Check if the configuration name is in the list 
				   of allowed configuration names. If not, generate
				   an exception. */
				if (!allowedConfigNames.contains(configNamesEntry)) {
					String  errorFormat = "Configuration name (%s) is not allowed";
					String  errorText = String.format(errorFormat, configNamesEntry);
					HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 78);
					break;
				}
				/* Get the configuration value for the configuration name */
				String  configValueEntry = HDLmConfig.getConfigValue(configNamesEntry);
				/* Build a configuration object */
				HDLmConfigValue  configValue = new HDLmConfigValue(configNamesEntry, configValueEntry);
				/* Add the new configuration entry to the list of configurations */
				configValues.addConfig(configValue);
			}
			/* Build a JSON string with all of the configurations in it */
		  Gson    gsonInstance = HDLmMain.gsonMain; 
		  String  jsonString = gsonInstance.toJson(configValues);
			/* Get the remote endpoint we need to send a reply to */
			RemoteEndpoint remote = session.getRemote();
			try {
				remote.sendString(jsonString);
				LOG.info("sendString" + " - " + jsonString);
			} 
			catch (IOException ioException) {
				if (jsonString != null)
					LOG.info("JSON String - " + jsonString);
				String  errorText = "IOException while executing handleMessageGetConfigValues";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(ioException.getMessage(), ioException);
				HDLmEvent.eventOccurred("IOException");
				break;
			} 
			catch (Exception exception) {
				if (jsonString != null)
					LOG.info("JSON String - " + jsonString);
				String  errorText = "Exception while executing handleMessageGetConfigValues";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				break;
			}
			break;
		}
	}
	/* This routine is invoked to handle inbound web sockets get image 
     choices messages. These messages should be in JSON format and   
     should be handleable via JSON. */
	@SuppressWarnings("unused")
	protected void         handleMessageGetImageChoices(JsonElement jsonElement, String urlStr) {		
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageGetImageChoices is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Check if the URL string reference passed by the caller is null */
			if (urlStr == null) {
				String  errorText = "URL string reference passed to handleMessageGetImageChoices is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
		  /* Get the input image (URL) that was part of the WebSocket messages */
		  String  imageStr = HDLmWebSocketInternalAdapter.getJsonString(jsonElement, 
		  		                                                          "HDLmInputImage", 
		  		                                                          session); 
			if (imageStr == null)
			  break;
			/* Get the path value string that was part of the WebSocket message */
			String  pathValueStr = HDLmWebSocketInternalAdapter.getJsonString(jsonElement, 
					                                                              "HDLmPathValue", 
					                                                              session);
			if (pathValueStr == null) 
			  break;
			HDLmResponse  getResponse = HDLmOpenAI.getImageChoices(imageStr, urlStr, pathValueStr); 	
			/* ArrayList<String>  choiceList = HDLmOpenAIOld.getImageChoices(imageStr); */ 		
			
			String              errorMessage = getResponse.getErrorMessage(); 
			/* String  errorMessage = null; */
			ArrayList<String>   choiceList = getResponse.getStringList(); 
		  /* Check if the choices list array reference is null */
		  if (choiceList == null)
			  choiceList = new ArrayList<String>();
		  /* Build a JSON string with all of the choices.variants in it */	   
	    HDLmOpenAIEditImageResponse   dataObject = new HDLmOpenAIEditImageResponse(null, 
	 		                                                                           choiceList,
	 		                                                                           errorMessage); 
		  /* Convert the object to a JSON string */
		  Gson     gsonInstance = HDLmMain.gsonMain;
			String   dataJson = gsonInstance.toJson(dataObject);
			/* Get the remote endpoint we need to send a reply to */
			RemoteEndpoint remote = session.getRemote();
			try {
				remote.sendString(dataJson);
				LOG.info("sendString" + " - " + dataJson);
			} 
			catch (IOException ioException) {
				if (dataJson != null)
					LOG.info("JSON response String - " + dataJson);
				String  errorText = "IOException while executing handleMessageGetImageChoices";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(ioException.getMessage(), ioException);
				HDLmEvent.eventOccurred("IOException");
				break;
			} 
			catch (Exception exception) {
				if (dataJson != null)
					LOG.info("JSON response String - " + dataJson);
				String  errorText = "Exception while executing handleMessageGetImageChoices";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				break;
			}
			break;
		}
	}	
	/* This routine is invoked to handle inbound web sockets get modifications
	   partial tree messages. These messages should be in JSON format and should
	   be handleable via JSON. */
	protected void         handleMessageGetModPart() {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			SocketAddress   a = this.session.getRemoteAddress(); 
			InetAddress b = null; 
			try {
				b =  InetAddress.getLocalHost();
			} 
			catch (UnknownHostException unknownHostException) {
				// TODO Auto-generated catch block
				unknownHostException.printStackTrace();
				String  errorText = "Unknown Host Exception while executing handleMessageGetModPart";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(unknownHostException.getMessage(), unknownHostException);
				HDLmEvent.eventOccurred("UnknownHostException");
				break;
			}
			catch (Exception exception) { 
				String  errorText = "Exception while executing handleMessageGetModPart";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				break;
			} 
			/*
			protectedstatic String getClientIp(Session session) {
	      String ip = session.getUserProperties().get("javax.websocket.endpoint.remoteAddress").toString();
	      int i1 = ip.indexOf("/");
	      int i2 = ip.indexOf(":");
	      return ip.substring(i1 + 1, i2);
	    }
			*/			
			/* Build a JSON string with all of the rows in it. We have a routine that does
			   exactly that. */
			String  outTree = HDLmTree.buildJsonStringTree(HDLmTree.getNodePassTreeTop());
			/* Build a JSON element with the entire rule tree and more */
			JsonParser    parser = new JsonParser();
			JsonElement   jsonTopElement = null;
			try {
				jsonTopElement = parser.parse(outTree);
				/*
				if (outTree != null)
					HDLmUtility.logStringInParts("GetModPart", outTree);
			  */
			} 
			catch (Exception exception) {
				if (outTree != null)
					HDLmUtility.logStringInParts("GetModPart", outTree);
				String  errorText = "Exception while executing handleMessageGetModPart";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				break;
			}
			if (jsonTopElement == null) {
				String  errorText = "JSON top element not obtained from JSON tree in handleMessageGetModPart";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 79);
				break;
			}
			/* Convert the JSON elements to a JSON string */
			String  jsonTopString = HDLmJson.getStringJson(jsonTopElement);
			/* Get the remote endpoint we need to send a reply to */
			RemoteEndpoint remote = session.getRemote();
			try {
				remote.sendString(jsonTopString);
				LOG.info("sendString" + " - " + jsonTopString);
			} 
			catch (IOException ioException) {
				if (jsonTopString != null)
					LOG.info("JSON Top String - " + jsonTopString);
				String  errorText = "IOException while executing handleMessageGetModPart";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(ioException.getMessage(), ioException);
				HDLmEvent.eventOccurred("IOException");
				break;
			} 
			catch (Exception exception) {
				if (jsonTopString != null)
					LOG.info("JSON Top String - " + jsonTopString);
				String  errorText = "Exception while executing handleMessageGetModPart";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				break;
			}
			break;
			/* Close the web socket session */
			/* HDLmWebSocketInternalAdapter.closeSession(session, 3000, "Close the web socket session after success"); */ 
		}
	}
	/* This routine is invoked to handle inbound web sockets get modifications tree
	   messages. These messages should be in JSON format and should be handleable
	   via JSON.
	   
	   It doesn't look like the web socket client ever sends this message. It
	   appears that the client doesn't use getModTree in any form. This appears 
	   to be left over from an earlier version of the client code.
	   
	   Note the use of HDLmTreeTypes.MOD below. This is probably obsolete code at
	   this point in time. */
	protected void         handleMessageGetPassThruTree() {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Build a JSON element with the entire rule tree and more */
			String contentType = HDLmTreeTypes.MOD.toString();
			JsonElement  jsonTopElement = HDLmMod.getJsonTreePassThru(contentType, null);
			if (jsonTopElement == null) {
				String  errorText = "JSON top element not returned from getJsonTreePassThruTree";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 79);
				break;
			}
			/* Add the request type to the JSON elements */
			HDLmJson.setJsonString(jsonTopElement, "HDLmRequestType", "handleMessageGetPassThruTree");
			String  jsonTopString = HDLmJson.getStringJson(jsonTopElement);
			/* Get the remote endpoint we need to send a reply to */
			RemoteEndpoint remote = session.getRemote();
			try {
				remote.sendString(jsonTopString);
				LOG.info("sendString" + " - " + jsonTopString);
			} 
			catch (IOException ioException) {
				if (jsonTopString != null)
					LOG.info("JSON Top String - " + jsonTopString);
				String  errorText = "IOException while executing handleMessageGetPassThruTree - sendString";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(ioException.getMessage(), ioException);
				HDLmEvent.eventOccurred("IOException");
				break;
			} 
			catch (Exception exception) {
				if (jsonTopString != null)
					LOG.info("JSON Top String - " + jsonTopString);
				String  errorText = "Exception while executing handleMessageGetPassThruTree - sendString";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				break;
			}
			break;
		}
	} 
	/* This routine is invoked to handle inbound web sockets get text 
	   choices messages. These messages should be in JSON format and   
	   should be handleable via JSON. */
	protected void         handleMessageGetTextChoices(JsonElement jsonElement, String urlStr) {		 
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageGetTextChoices is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Check if the URL string passed by the caller is null */
			if (urlStr == null) {
				String  errorText = "URL string passed to handleMessageGetTextChoices is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Get the input text that was part of the WebSocket message */
			String  textStr = HDLmWebSocketInternalAdapter.getJsonString(jsonElement, 
					                                                         "HDLmInputText", 
					                                                         session); 
			if (textStr == null)
			  break;
			/* Get the path value string that was part of the WebSocket message */
			String  pathValueStr = HDLmWebSocketInternalAdapter.getJsonString(jsonElement, 
					                                                              "HDLmPathValue", 
					                                                              session); 
			if (pathValueStr == null)
			  break;
			HDLmResponse  getResponse = HDLmOpenAI.getTextChoices(textStr, urlStr, pathValueStr);		
			String        errorMessage = getResponse.getErrorMessage();
			ArrayList<String>   choiceList = getResponse.getStringList();
			/* Check if the choices list array reference is null */
			if (choiceList == null)
				choiceList = new ArrayList<String>();
			/* Build a JSON string with all of the choices.variants in it */	   
		  HDLmOpenAIEditTextResponse  dataObject = new HDLmOpenAIEditTextResponse(null, 
		  		                                                                    choiceList,
		  		                                                                    errorMessage); 
		  /* Convert the object to a JSON string */
		  Gson     gsonInstance = HDLmMain.gsonMain;
			String   dataJson = gsonInstance.toJson(dataObject);
			/* Get the remote endpoint we need to send a reply to */
			RemoteEndpoint remote = session.getRemote();
			try {
				remote.sendString(dataJson);
				LOG.info("sendString" + " - " + dataJson);
			} 
			catch (IOException ioException) {
				if (dataJson != null)
					LOG.info("JSON response String - " + dataJson);
				String  errorText = "IOException while executing handleMessageGetTextChoices";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(ioException.getMessage(), ioException);
				HDLmEvent.eventOccurred("IOException");
				break;
			} 
			catch (Exception exception) {
				if (dataJson != null)
					LOG.info("JSON response String - " + dataJson);
				String  errorText = "Exception while executing handleMessageGetTextChoices";
				HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 77);
				LOG.info(errorText);
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				break;
			}
			break;
		}
	}
	/* This routine is invoked to handle inbound web sockets reload nodes messages.
	   These messages should be in JSON format and should be handleable via JSON. */
	protected void         handleMessageReloadNodes() {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Invoke a common routine to reload all of the nodes */
			HDLmMain.reloadNodes();
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
		}
	} 
	/* This routine is invoked to handle inbound web sockets save data value messages.
     These messages should be in JSON format and should be handleable via JSON. */
  /* It doesn't look like the web socket client ever sends this message. It
     appears that the client doesn't use saveDataValue in any form. This
     is a routine that was never completed and never used. */ 
	protected void         handleMessageSaveDataValue(JsonElement jsonElement) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageSaveDataValue is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Get the minimum node path length. This is the minimum 
	  	   node path length needed to get a host name.  */
   		int   hostNamePathLength = HDLmDefines.getNumber("HDLMHOSTNAMENODEPATHLENGTH");
			/* Set the specific path length to null. This is used below to check 
		     the size of the node path JSON. */
		  Integer   specificPathLength = null;
			/* Get and check the node path from the JSON */
			ArrayList<String>   saveDataNodePath = HDLmWebSocketInternalAdapter.getNodePathFromJson(jsonElement,	 
					                                                                                    specificPathLength,
					                                                                                    session);
			if (saveDataNodePath == null)
				break;
			/* Get the host name from the node path. The node path will always contain the
			   correct host name. */
			String hostName = saveDataNodePath.get(hostNamePathLength - 1);
			/* Pass the JSON element to another routine for further handling */
			/* HDLmTree.addTreeNode(jsonElement, hostName); */
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
		}
	}
	/* This routine is invoked to handle inbound web sockets store tree node
	   messages. These messages should be in JSON format and should be handleable
	   via JSON. An add is executed if the tree node does not exist. An update is
	   executed if the tree node, already exists. */
	@SuppressWarnings({ "unused"})
	protected void         handleMessageStoreTreeNodes(JsonElement jsonElement) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageStoreTreeNodes is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			boolean  logDebugEnabled = LOG.isDebugEnabled();
			if (logDebugEnabled)
			  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessageStoreTreeNodes - at the start");
			/* Get the minimum node path length. This is the minimum 
	       node path length needed to get a host name.  */
	    int   hostNamePathLength = HDLmDefines.getNumber("HDLMHOSTNAMENODEPATHLENGTH");
			/* Get the node path length for use later */
			int   specificPathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
			/* Get the number of rules we are storing */
			JsonArray   rulesArrayJson = HDLmWebSocketInternalAdapter.getJsonArray(jsonElement, 
					                                                                   "rules", 
					                                                                   session);
			if (rulesArrayJson == null)
		    break;	
			/* Get the number of rules in the rules array */
			int  rulesArraySize = rulesArrayJson.size();
			/* Check each rule to determine if it is valid */ 
			boolean   rulesValid = true;
			for (int i = 0; i < rulesArraySize; i++) {		
				boolean       scriptsValid = true;
				JsonElement   jsonElementRule = rulesArrayJson.get(i);
				/* Check if we have mod entry. We should always
			     have a mod entry. */
		  	String  typeEntry = HDLmWebSocketInternalAdapter.getJsonString(jsonElementRule, 
		  			                                                           "type", 
		  			                                                           session);
				if (typeEntry == null) 
				  break;
				if (typeEntry.equals("mod") == false) {
					String errorText = "Type entry does not have expected value in storeTreeNodes";
			    HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 80);
			    rulesValid = false;
			    break;
				}
				/* Get the rule details */
				JsonObject  modDetailsObject = HDLmWebSocketInternalAdapter.getJsonObject(jsonElementRule, 
			                                                                      			"details", 
			                                                                      			session);
				if (modDetailsObject == null) { 
			    rulesValid = false;
			    break;
				}
				/* Get the rule name */
				JsonElement   modDetailsElement = (JsonElement) modDetailsObject;
				String  ruleName = HDLmWebSocketInternalAdapter.getJsonString(modDetailsElement, 
						                                                          "name", 
						                                                          session);
				if (ruleName == null)
				  break;
				/* Get the rule type */
				String  ruleType = HDLmWebSocketInternalAdapter.getJsonString(modDetailsElement, 
						                                                          "type", 
						                                                          session);
				if (ruleType == null)
				  break;			 
				/* We have a lot more work to do for script rules */
				if (ruleType.equals("script")) {
					/* Get and check the node path JSON and the size of the node path JSON */
					ArrayList<String>   scriptsArray = HDLmWebSocketInternalAdapter.getStringsFromJson(modDetailsElement,
							                                                                               "scripts",    
							                                                                               session,
							                                                                               HDLmZeroLengthOk.ZEROLENGTHNOTOK);
					if (scriptsArray == null)
				    break;	
					int   scriptsArraySize = scriptsArray.size();
					/* Process each script */
					for (int j = 0; j < scriptsArraySize; j++) {		
						String    scriptString = scriptsArray.get(j);  
						boolean   scriptValid = HDLmHtml.checkIfJavaScriptValid(scriptString, 
								                                                    HDLmReportErrors.REPORTERRORS);
						/* Check if the current script is invalid, if it is, then 
						   the entire set of scripts is treated as invalid */
						if (scriptValid == false)
							scriptsValid = false;
					}
				}		
				/* Skip the current rule if the invalid scripts flag is set */
				/*
				scriptsValid = true;
				*/
				if (scriptsValid == false) {
			    rulesValid = false;
			    break;
				}
				/* Get and check the node path JSON and the size of the node path JSON */
				ArrayList<String>   storeNodePath = HDLmWebSocketInternalAdapter.getNodePathFromJson(jsonElementRule, 
						                                                                                 specificPathLength,
                                                                                             session);
				if (storeNodePath == null)
			    break;	
				/* Check if the JSON for the tree object (HDLmTree) is valid or not.
	 		     This call also check the embedded rule (HDLmMod) inside the JSON. */ 
			  HDLmResponse  jsonResponse = HDLmTree.checkTreeJsonObj(jsonElement);
			  int           errorsCount = jsonResponse.getReturnNumber();
				if (errorsCount > 0) {
					/* At this point, we must send a standard failure message back
					   to the caller. This failure message is a JSON string that
					   indicates that the operation failed. The failure message
					   contains a failure code and an error message. The failure
					   code is a number that indicates the type of failure. The
					   error message is a string that describes the failure to
					   a limited extent. Details about the failure are also in 
					   the log. */
		  		String  errorMessage = jsonResponse.getErrorMessage(); 
			    HDLmWebSocketInternalAdapter.sendFailure(session, errorMessage, 4);
			    rulesValid = false;
			    break;
				  /* Close the web socket session */
				  /* HDLmWebSocketInternalAdapter.closeSession(session, 3000, "Close the web socket session after an error"); */ 
				}
			}
			/* Check if we found any errors in the rules passed to this
			   routine. If we did, then we can just return to the caller
			   at this point. */ 
			if (rulesValid == false) 
				break;			
			/* Process each rule */
			for (int i = 0; i < rulesArraySize; i++) {		
				JsonElement   jsonElementRule = rulesArrayJson.get(i);
				/* Get and check the node path JSON and the size of the node path JSON */		
				ArrayList<String>   storeNodePath = HDLmWebSocketInternalAdapter.getNodePathFromJson(jsonElementRule,
						                                                                                 specificPathLength,
						                           				                                             	 session);
				if (storeNodePath == null)
			    break;	
				/* No work has been done so far */
				boolean   workDone = false;
				/* Try to find the tree node from the update node path. This will be the node
			     that is about to be updated. */
			  HDLmTree  storeNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
					                                            storeNodePath);
			  /* Check if the node already exists, if it does not exist, then add it */
			  if (storeNode == null) {
			  	String  hostName = storeNodePath.get(hostNamePathLength - 1);
					/* Pass the JSON element to another routine for further handling */
					HDLmTree.addTreeNode(jsonElementRule, hostName, storeNodePath);
					/* Log the current change */		  
					HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
									                HDLmChangeTypes.CHANGETYPEADD,
									                storeNodePath, 
									                jsonElementRule); 
					/* Show that work has been done */
					workDone = true;	  	
			  }
				/* Check if any actual work has been done so far. Execute the update 
				   if no add has been done */
			  if (workDone == false) {
					if (logDebugEnabled)
					  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode - about to update tree node");
					/* LOG.info("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode"); */
					/* Pass the JSON element to another routine for further handling */
					HDLmTree.updateTreeNode(storeNode, jsonElementRule);
					/* Log the current change */		  
					HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
									                HDLmChangeTypes.CHANGETYPEMODIFY,
									                storeNodePath, 
									                jsonElementRule); 
					/* Show that work has been done */
					workDone = true;
			  }
			}
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null); 		
			break;
		}
	}
	/* This routine is invoked to handle inbound transfer requests. A typical request
	   might be to copy some number of rules from the test system to the production 
	   system. The caller describes the transfer request in some detail. */
	protected void         handleMessageTransferSomething(JsonElement jsonElement) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageTransferSomething is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
	    /* Try to transfer something as specified by the caller */
			Integer       changeNumber = -1;
			HDLmResponse  transferResponse = HDLmTransferSomething.transferSomething(jsonElement);
			String  errorMessage = transferResponse.getErrorMessage();
			if (errorMessage == null)
				changeNumber = transferResponse.getReturnNumber();
			/* Send a failure message back to the client */
			if (errorMessage != null) {
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorMessage, 60);
			  break;
			} 
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
		}
	} 
	/* This routine is invoked to handle an inbound undo requests. This is
	   a request to undo the 'current' operation. This might be the last
	   recorded operation or it might not be. If the current operation is 
	   at the top of the operation ArrayList, then this will be a request
	   to undo the last recorded operation. However, if this routine is 
	   invoked more than once, the 'current' operation will keep going 
	   down while the 'top' operation stays the same. */
	protected void         handleMessageUnDo(JsonElement jsonElement) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageUnDo is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Get the input text that was part of the WebSocket message */
			Integer   changeNumber = HDLmWebSocketInternalAdapter.getJsonInteger(jsonElement, 
					                                                                 "HDLmChangeNumber",
					                                                                 session); 
			if (changeNumber == null)
			  break;
		  /* Try to undo something as specified by the caller */
			String  errorMessage = HDLmUnRe.unReUnDoOperation(changeNumber);
			/* Send a failure message back to the client */
			if (errorMessage != null) {
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorMessage, 61);
			  break;
			} 
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
		}
	}
	/* This routine is invoked to handle inbound web sockets update tree messages.
	   These messages should be in JSON format and should be handleable via JSON.
	   The tree node passed by the caller may or may not exist. This routine must be
	   able to handle both cases.
	   
	   It doesn't look like the web socket client ever sends this message. It
	   appears that the client doesn't use UpdateTree in any form. This appears 
	   to be left over from an earlier version of the client code. */
	protected void         handleMessageUpdateTree(JsonElement jsonElement, final String urlValue) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageUpdateTree is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Check if the URL string reference passed by the caller is null */
			if (urlValue == null) {
				String  errorText = "URL string reference passed to handleMessageUpdateTree is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			/* Get the minimum node path length. This is the minimum 
		     node path length needed to get a host name.  */
		  int   hostNamePathLength = HDLmDefines.getNumber("HDLMHOSTNAMENODEPATHLENGTH");
		  /* Check if the JSON for the tree object (HDLmTree) is valid or not.
		     This call also check the embedded rule (HDLmMod) inside the JSON. */ 
			HDLmResponse  jsonResponse = HDLmTree.checkTreeJsonObj(jsonElement);
			int           errorsCount = jsonResponse.getReturnNumber();
			if (errorsCount > 0) {
				/* At this point, we must send a standard failure message back
				   to the caller. This failure message is a JSON string that
				   indicates that the operation failed. The failure message
				   contains a failure code and an error message. The failure
				   code is a number that indicates the type of failure. The
				   error message is a string that describes the failure to
				   a limited extent. Details about the failure are also in 
				   the log. */
				String  errorMessage = jsonResponse.getErrorMessage(); 
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorMessage, 4);
			  break; 
			}
			/* Set the specific path length to null. This is used below to check 
		     the size of the node path JSON. */
		  Integer   specificPathLength = null;
			/* Get and check the node path */
			ArrayList<String>   updateTreeNodePath = HDLmWebSocketInternalAdapter.getNodePathFromJson(jsonElement,
					                                                                                      specificPathLength,
					                                                                                      session); 			
			if (updateTreeNodePath == null)
		    break;
			/* Get the host name from the node path. The node path will always contain
			   the correct host name. */
			String  hostNameStr = updateTreeNodePath.get(hostNamePathLength - 1);
			/* Pass the JSON element to another routine for further handling. PassThru is
			   part of the method name to help distinguish between different routines that
			   used to have the same name. */
			String  contentType = HDLmEditorTypes.PASS.toString();
			HDLmTree.updateTreePassThru(contentType, hostNameStr, jsonElement); 
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
		}
	}
	/* This routine is invoked to handle inbound web sockets update tree node
	   messages. These messages should be in JSON format and should be handleable
	   via JSON. */
	@SuppressWarnings("unused")
	protected void         handleMessageUpdateTreeNode(JsonElement jsonElement) {
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the JSON element instance passed by the caller is null */
			if (jsonElement == null) {
				String  errorText = "JSON element instance passed to handleMessageUpdateTreeNode is null";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 26);
			  break;
			}
			boolean  logDebugEnabled = LOG.isDebugEnabled();
			if (logDebugEnabled)
			  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode - at the start");
			/* Get the node path length */
			int   specificPathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		  /* Check if the JSON for the tree object (HDLmTree) is valid or not.
		     This call also check the embedded rule (HDLmMod) inside the JSON. */ 
			HDLmResponse  jsonResponse = HDLmTree.checkTreeJsonObj(jsonElement);
			int           errorsCount = jsonResponse.getReturnNumber();
			if (errorsCount > 0) {
				/* At this point, we must send a standard failure message back
				   to the caller. This failure message is a JSON string that
				   indicates that the operation failed. The failure message
				   contains a failure code and an error message. The failure
				   code is a number that indicates the type of failure. The
				   error message is a string that describes the failure to
				   a limited extent. Details about the failure are also in 
				   the log. */
				String  errorMessage = jsonResponse.getErrorMessage(); 
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorMessage, 4);
			  break; 
			}
			/* Get and check the node path JSON and the size of the node path JSON */ 
			ArrayList<String>   updateTreeNodePath = HDLmWebSocketInternalAdapter.getNodePathFromJson(jsonElement,
					                                                                                      specificPathLength,
					                                                                                      session);
			if (updateTreeNodePath == null)
		    break;		
			/* Try to find the tree node from the update node path. This will be the node
		     that is about to be updated. */
		  HDLmTree  updateNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
				                                             updateTreeNodePath);			
			if (updateNode == null) {
				String  nodeString = updateTreeNodePath.toString(); 
				HDLmUtility.logString(nodeString, LOG);
				HDLmUtility.logStackTrace();
				/* Send a failure message back to the client */
				String  errorText = "Null Update tree node returned by locateTreeNode";
			  HDLmWebSocketInternalAdapter.sendFailure(session, errorText, 9);
				break;
			}
			if (logDebugEnabled)
			  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode - about to update tree node");
			/* LOG.info("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode"); */
			/* Pass the JSON element to another routine for further handling */
			HDLmTree.updateTreeNode(updateNode, jsonElement);
			/* Log the current change */		  
			HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
							                HDLmChangeTypes.CHANGETYPEMODIFY,
							                updateTreeNodePath, 
							                jsonElement); 
			/* Send a success message back to the caller */
			HDLmWebSocketInternalAdapter.sendSuccess(session, null, null);
			break;
		}
	} 
	/* This routine is invoked when a binary message is sent. So far this routine
	   has not been used. */
	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len) {
		String textFormat = "onWebSocketBinary - %d - %d";
		String textString = String.format(textFormat, offset, len);
		LOG.info(textString);
		super.onWebSocketBinary(payload, offset, len);
	}
	/* This routine is invoked when a web socket connection is destroyed. This
	   routine is definitely used (invoked). We should handle the closing of
	   connections in some way. How is not clear. */
	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		this.session = null;
		String textFormat = "onWebSocketClose - %d - %s";
		String textString = String.format(textFormat, statusCode, reason);
		LOG.info(textString);
		super.onWebSocketClose(statusCode, reason);
	}
	/* This routine is invoked when a web socket connection is established. This
	   routine is definitely used (invoked). We should handle new connections by
	   saving some data. What data is not not clear. */
	@Override
	public void onWebSocketConnect(Session session) {
		LOG.info("onWebSocketConnect");
		super.onWebSocketConnect(session);
		this.session = session;
	}
	/* This routine is invoked when a text message is sent. So far this routine has
	   been used each time a message is sent from the client. */
	@Override
	public void onWebSocketText(String message) {
		if (1 == 1)
			LOG.info("onWebSocketText" + " - " + message);
		super.onWebSocketText(message);
		/* Try to handle the current web sockets message */
		handleMessage(message);
	}
	/* Send a failure message back to the client. The caller provides
	   the session and possibly a failure error message and possibly 
	   a failure error number. The failure error message and number 
	   are used to build a failure JSON string that is sent back to 
	   the client. Nothing is returned to the caller. */
	@SuppressWarnings("unused")
	protected static void  sendFailure(final Session sessionToBeUsed, 
			                               final String failureMessage,
			                               final Integer failureNumber) {
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String errorText = "Session object passed to sendFailure is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the failure message is null. The failure message can 
		   be null (this is not an error). */
		if (failureMessage == null && 
	      failureMessage != null) {
			String errorText = "Failure message passed to sendFailure is null";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the failure number is null. The failure number can 
		   be null (this is not an error). */
		if (failureNumber == null && 
 	      failureNumber != null) {
			String errorText = "Failure number passed to sendFailure is null";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the failure number is valid */
		if (failureNumber != null)
			if (failureNumber < 1 || failureNumber > 200) {
				String errorText = "Invalid failure number passed to sendFailure";
				throw new IllegalArgumentException(errorText);
			}
		/* Build a failure JSON string with the failure number */
		String  failureJson = HDLmUtility.buildResultFailureJsonString(failureMessage, 
				                                                           failureNumber);
		/* Send the failure JSON string back to the client */
		HDLmWebSocketInternalAdapter.sendString(sessionToBeUsed, failureJson);
		return;
	}
	/* Send a string back to the caller in a web socket message. The
	   string probably contains JSON, but this is not required. The 
	   caller provides the web socket session and the message string.
	   Of course, the low-level send operation can cause exceptions.
	   These exceptions are caught and reported. */
	protected static void  sendString(final Session sessionToBeUsed, 
			                              final String sendValue) {
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to sendString is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the send string string reference passed by the caller is null */
		if (sendValue == null) {
			String  errorText = "Send string reference passed to sendString is null";
			throw new NullPointerException(errorText);
		}
		/* Get the remote endpoint we need to send a message to */
		RemoteEndpoint remote = sessionToBeUsed.getRemote();
		try {
			remote.sendString(sendValue);
			LOG.info("sendString" + " - " + sendValue);
		} 
		catch (IOException ioException) {
			if (sendValue != null)
				LOG.info("JSON response String - " + sendValue);
			LOG.info("IOException while executing sendString");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			return;
		} 
		catch (Exception exception) {
			if (sendValue != null)
				LOG.info("JSON response String - " + sendValue);
			LOG.info("Exception while executing sendString");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
		return;
	}
	/* Send a success message back to the client. The caller provides
     the session and the success number (which may be a null value).
     The success number is not used to build a success JSON string
     that is sent back to the client. Nothing is returned to the caller. */
	@SuppressWarnings("unused")
	protected static void  sendSuccess(final Session sessionToBeUsed,
			                               final String successMessage,
		                                 final Integer successNumber) {
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String errorText = "Session object passed to sendSuccess is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the success message is null. The success message can 
		   be null (this is not an error). */
		if (successMessage == null && 
				successMessage != null) {
			String errorText = "Success message passed to sendSuccess is null";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the success number is valid */
		if (successNumber != null)
			if (successNumber < 1 || successNumber > 200) {
				String errorText = "Invalid success number passed to sendSuccess";
				throw new IllegalArgumentException(errorText);
			}
		/* Build a success JSON string */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(successNumber);
		/* Send the success JSON string back to the client */
		HDLmWebSocketInternalAdapter.sendString(sessionToBeUsed, successJson);
		return;
	}
}
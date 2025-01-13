package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.SystemUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
/**
 * HDLmBridge short summary.
 *
 * HDLmBridge description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmBridge {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmBridge.class);  
	/* Define a connection that can be reused as need be. Eventually, this will 
	   be some sort of connection pool. However, for now we have just one 
	   connection. */
  private static Connection   connection = null;  
	/* This class can never be instantiated */
	private HDLmBridge() {}		
	/* The next method handles an inbound bridge request. The 		
	   bridge request is checked and passed to a more specific
	   routine for additional processing. */ 
	protected static void  handleRequest(final HttpServletRequest request, 
			                                 final HttpServletResponse response,  
			                                 final String pathValueString,
			                                 final String requestPostPayload,
			                                 final HDLmHttpTypes httpType) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Check the inbound path value string passed by the caller. The
		   path value string has many of the values we need. */ 
		if (pathValueString == null) {
			String   errorText = "Inbound path value string reference passed to handleRequest is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the request post payload value string passed by the caller. The
	     post payload value string has many of the values we need. */ 
		if (requestPostPayload == null &&
				httpType == HDLmHttpTypes.POST) {
			String   errorText = "Request post payload value string reference passed to handleRequest is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the HTTP type enum passed by the caller is null */
		if (httpType == null) {
			String  errorText = "HTTP type enum reference passed to handleRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Declare/define a few variables */
		String  contentValue;
		String  outJson = "";
		String  operationType;
		String  parmStr;
		String  tableName;
		String  userNameStr;
		/* Get some information from the path value string and the
		   parameters (if any) */
		parmStr = request.getParameter("q");
		HDLmUtilityResponse   utilityResponse = HDLmUtility.getBridgeInformation(pathValueString, parmStr);
		/* Extract some information from the utility response */
		contentValue = utilityResponse.getQueryValue();
		operationType = utilityResponse.getOperationType();
		tableName = utilityResponse.getTableName(); 
		/* If the table name was not set or if it set to a 
		   zero-length string, we need to use a real value */ 
		if (tableName == null || 
				tableName.length() == 0) {
		  tableName = HDLmConfigInfo.getEntriesDatabaseTableName();			
		} 
		if (operationType.equals("search")) {
			HDLmJetty.getUseridPassword(request);
			outJson = HDLmBridge.handleRequestRead(request,  contentValue, tableName);
		}		
		else if (operationType.equals("update")) {
			outJson = HDLmBridge.handleRequestUpdate(request, pathValueString, tableName, requestPostPayload); 			
			HDLmChange.recordChangeWeb(HDLmChangeSourceTypes.CHANGESOURCEWEB, 
							                   HDLmChangeTypes.CHANGETYPEMODIFY,
							                   requestPostPayload);  		  
		}	
		else if (operationType.equals("insert")) {
			outJson = HDLmBridge.handleRequestInsert(request, pathValueString, tableName, requestPostPayload); 
			/* Log the current change */		  
			HDLmChange.recordChangeWeb(HDLmChangeSourceTypes.CHANGESOURCEWEB, 
							                   HDLmChangeTypes.CHANGETYPEADD,
							                   requestPostPayload);  
		}	
		else if (operationType.equals("delete")) {
			outJson = HDLmBridge.handleRequestDelete(request, pathValueString, tableName, requestPostPayload); 
			/* Log the current change */		  
			 
			HDLmChange.recordChangeWeb(HDLmChangeSourceTypes.CHANGESOURCEWEB, 
							                   HDLmChangeTypes.CHANGETYPEDELETE,
							                   requestPostPayload);  
		   
		}	
		else {
    	String   errorFormat = "Invalid operation type (%s) in handleRequest";
			String   errorText = String.format(errorFormat, operationType);
			HDLmAssertAction(false, errorText);				
		}
		/* Add the Access-Control-Allow-Origin header */
		HDLmJetty.handleResponseAllowAllOrigins(request, response);
		/* Return the JSON to the caller */
		try {
	    response.setStatus(HttpStatus.OK_200);
			response.getWriter().print(outJson);
			response.getWriter().flush();
		  response.setContentType("application/json");
		} 
		catch (IOException ioException) {
			if (pathValueString != null)
			  LOG.info("Path - " + pathValueString);
			LOG.info("IOException while executing processHttpOperation");
			LOG.info(ioException.getMessage(), ioException);
		  HDLmEvent.eventOccurred("IOException");		
		  return;
		}	  
    return;
	} 
	/* The next method handles an inbound bridge delete request */ 
	protected static String  handleRequestDelete(final HttpServletRequest request,
			                                         final String pathValueString,
			                                         final String tableName,
			                                         final String requestPostPayload) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleRequestDelete is null";
			throw new NullPointerException(errorText);
		}
		/* Check the inbound path value string passed by the caller */ 
	  if (pathValueString == null) {
		  String   errorText = "Inbound path value string reference passed to handleRequestDelete is null";
		  throw new NullPointerException(errorText);			
	  }
		/* Check the table name string passed by the caller */ 
	  if (tableName == null) {
		  String   errorText = "Table name string reference passed to handleRequestDelete is null";
		  throw new NullPointerException(errorText);			
	  }
		/* Check the request POST payload string (JSON) passed by the caller */ 
	  if (requestPostPayload == null) {
		  String   errorText = "Request post payload (JSON) string reference passed to handleRequestDelete is null";
		  throw new NullPointerException(errorText);			
	  }
	  /* Declare/define a few variables */
	  String  outJson = null;	  
		/* Declare/define a few variables */ 
		outJson = HDLmDatabase.deleteTableRowsJson(requestPostPayload, tableName);
	  return outJson;
  } 
	/* The next method handles an inbound bridge insert request */ 
	protected static String  handleRequestInsert(final HttpServletRequest request,
			                                         final String pathValueString,
			                                         final String tableName,
			                                         final String requestPostPayload) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleRequestInsert is null";
			throw new NullPointerException(errorText);
		}
		/* Check the inbound path value string passed by the caller */ 
	  if (pathValueString == null) {
		  String   errorText = "Inbound path value string reference passed to handleRequestInsert is null";
		  throw new NullPointerException(errorText);			
	  }
		/* Check the table name string passed by the caller */ 
	  if (tableName == null) {
		  String   errorText = "Table name string reference passed to handleRequestInsert is null";
		  throw new NullPointerException(errorText);			
	  }
		/* Check the request POST payload string (JSON) passed by the caller */ 
	  if (requestPostPayload == null) {
		  String   errorText = "Request post payload (JSON) string reference passed to handleRequestInsert is null";
		  throw new NullPointerException(errorText);			
	  }
	  /* Declare/define a few variables */
	  String  outJson = null;	  
		/* Declare/define a few variables */ 
		outJson = HDLmDatabase.insertTableRowsJson(requestPostPayload, tableName);
	  return outJson;
  } 
	/* The next method handles an inbound bridge read request */ 
	protected static String  handleRequestRead(final HttpServletRequest request,
			                                       final String contentValue,
			                                       final String tableName) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleRequestRead is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content value passed by the caller is null */
		if (contentValue == null) {
			String  errorText = "Content value passed to handleRequestRead is null";
			throw new NullPointerException(errorText);
		}
		/* Check the table name string passed by the caller */ 
	  if (tableName == null) {
		  String   errorText = "Table name string reference passed to handleRequestRead is null";
		  throw new NullPointerException(errorText);			
	  }		
	  /* Declare and define a few values */
	  boolean   bypassAllChecking = false;
	  boolean   checkMatch = false;
	  boolean   checkLastTimeFailure = true;
	  String    passwordStr = null;
	  String    scopeStr = null;
	  String    userNameStr = null;
		/* Check for Windows versus Unix (of some kind). This code is no 
		   longer in use. We check and use the scope string in all cases.
		   This allows scope to be tested under Windows. The old code has
		   been kept, just in case it is needed. The code here was set to
		   'true || SystemUtils'. */
		if (HDLmMain.getOsType() != HDLmOsTypes.WINDOWS ||
				HDLmMain.isDockerFlagSet() == true) {
	    /* Get the user name and password from the request. These values will be 
	       passed using the authorization header. */
	    HDLmUtilityResponse  authOutputArea = HDLmJetty.getUseridPassword(request);
	    userNameStr = authOutputArea.getUserNameValue();
	    passwordStr = authOutputArea.getPasswordValue();
	    /* LOG.info("In handleRequestRead - UserName - " + userNameStr); */
	    /* Check if the user name and password passed by the caller using the
	       authorization header match the memory data base */
      checkMatch = HDLmSecurity.checkMatchingUsernamePassword(userNameStr,
    	  	                                                    passwordStr,
    	  	                                                    false);	   
	    /* Get the scope string from the internal database */
	    HDLmUtilityResponse   getOutputArea = HDLmSecurity.getInformation(userNameStr);
	    scopeStr = getOutputArea.getScopeValue();
	    /* LOG.info("In handleRequestRead - Scope    - " + scopeStr); */
	    /* Check if the information in the memory database is too old */
		  checkLastTimeFailure = HDLmSecurity.checkLastTimeFailure(userNameStr, 
		                                                           getOutputArea);
		  /* Reset the last time used value, if need be */
		  if (checkLastTimeFailure) {		  
		  	checkLastTimeFailure = false;
			  Instant   instant = Instant.now();
			  String    instantStr = instant.toString();
			  HDLmSecurity.updateLastTime(userNameStr, instantStr);
		  }
		  /* Check for a few very special cases */
		  if (userNameStr.contentEquals("pdschaefferisnotused") ||
		  		userNameStr.contentEquals("myzelenskyisnotuser")) {
		  	checkLastTimeFailure = false;
		  	scopeStr = "admin";
		  }
		  /* LOG.info("In handleRequestRead - checkLastTimeFailure - " + ((Boolean) checkLastTimeFailure).toString()); */
		}
		/* We must be running under Windows. This must be a test copy of
		   the system. */ 
		else {
			bypassAllChecking = true;
			checkMatch = true;
			checkLastTimeFailure = false;
			scopeStr = "*";
		}
	  /* Check if we should allow access */ 
	  if (checkMatch == false)
	  	scopeStr = null; 
	  if (checkLastTimeFailure == true)
	  	scopeStr = null;
		/* Convert the scope string to a scope array/vector, if possible */
		ArrayList<ArrayList<String>>  scopeArray = null;
		if (scopeStr != null)
			scopeArray = HDLmSecurity.convertScopeString(scopeStr);
		ArrayList<ArrayList<String>>  passedScopeArray = scopeArray;
    /* If the current user has a special scope, then unrestricted access
       is always allowed. Note that 'admin' and '*' (without the quotes)
       provide access to everything. */
	  if (scopeArray != null) { 
	  	int   scopeArrayLen = scopeArray.size();
	  	if (scopeArrayLen > 0) {
	  		ArrayList<String>  scopeArrayEntry = scopeArray.get(0);
	  		int   scopeArrayEntryLen = scopeArrayEntry.size();
	  		if (scopeArrayEntryLen > 0) {
	  			String  scopeArrayEntryEntry = scopeArrayEntry.get(0);
				  if (scopeArrayEntryEntry.equals("admin") ||
				  		scopeArrayEntryEntry.equals("*")) {
				  	bypassAllChecking = true;
				  }	
	  		}
	  	}
	  }
		/* Check if all filtering/checking should be bypassed */
		if (bypassAllChecking)
			passedScopeArray = null;
		/* Declare/define a few variables */ 
		String  outJson = HDLmDatabase.getTableRowsJson(contentValue, tableName, passedScopeArray);
		/* Check if all filtering/checking should be bypassed */		 
		if (bypassAllChecking)
			return outJson;
		/* Create a new JSON parser for use below */
    JsonParser    parser = new JsonParser();  
    /* Make sure the inbound payload has the required key */
    JsonElement   oldJsonElement = parser.parse(outJson);
	  /* Check if the JSON string is valid or not */
		if (!oldJsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string from HDLmDatabase.getTableRowsJson in handleRequestRead is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
    JsonObject    newJsonObject = new JsonObject();
    JsonArray     newJsonArray = new JsonArray();
    /* Get the original number of rows. Filtering may or may not reduce
       the number of rows actually returned. */
    boolean  hasNumberOfRowsKey = HDLmJson.hasJsonKey(oldJsonElement, "rows_returned");
    if (hasNumberOfRowsKey == false) {
	 	  String  errorText = "Table rows JSON does not have rows_returned key in handleRequestRead";
	 	  HDLmAssertAction(false, errorText);    	
    }    
    int   numberOfRowsOld = HDLmJson.getJsonInteger(oldJsonElement, "rows_returned");
    boolean  hasDataKey = HDLmJson.hasJsonKey(oldJsonElement, "data");
    if (hasDataKey == false) {
	 	  String  errorText = "Table rows JSON does not have data key in handleRequestRead";
	 	  HDLmAssertAction(false, errorText);    	
    }  
    JsonArray   dataArray = HDLmJson.getJsonArray(oldJsonElement, "data");
    /* Check if the JSON array is valid or not */
		if (!dataArray.isJsonArray()) {
	 	  String  errorText = "JSON array in handleRequestRead is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Check each of the rows */
    int   numberOfRowsNew = 0; 
    for (int i = 0; i < numberOfRowsOld; i++) {
    	/* Get and check each data row */
    	JsonElement         dataRow = dataArray.get(i); 
    	/* A string value from each row will have most of the information we need */
      boolean  hasInfoKey = HDLmJson.hasJsonKey(dataRow, "info");
      if (hasInfoKey == false) {
  	 	  String  errorText = "Data row JSON does not have info key in handleRequestRead";
  	 	  HDLmAssertAction(false, errorText);    	
      }
    	String              dataRowInfoStr = HDLmJson.getJsonString(dataRow, "info");
    	/* Try to get the node path array list from the 'info' string.
    	   This should always be possible. */ 
    	ArrayList<String>   nodePathRow = HDLmTree.getNodePath(dataRowInfoStr);
    	int                 nodePathRowLen = nodePathRow.size();   
    	/* Check if the current row is allowed or not. This check is only 
    	   possible if the scope string is actually set. If not, then we
    	   can not check if the current row is allowed. */
    	boolean   rowAllowed;
    	if (nodePathRowLen == 1)
    		rowAllowed = true;
    	else if (scopeArray == null)
    		rowAllowed = false;
    	else
    	  rowAllowed = HDLmSecurity.checkIfAllowed(scopeArray, nodePathRow); 
    	if (rowAllowed == true) {
    		numberOfRowsNew++;
    		newJsonArray.add(dataRow);    		
    	}    	 	
    }		
    /* Create a JSON primitive for the final number of rows */
    JsonElement  numberOfRowsNewJson = new JsonPrimitive(numberOfRowsNew);
    /* Store some values in the new JSON object */
    newJsonObject.add("rows_returned", numberOfRowsNewJson);
    newJsonObject.add("data", newJsonArray);
    /* Convert the new JSON object to a JSON string */
    Gson  gsonInstance = HDLmMain.gsonMain;
    outJson = gsonInstance.toJson(newJsonObject);
    /* Return the new JSON string */
	  return outJson;
  } 
	/* The next method handles an inbound bridge update request */ 
	protected static String  handleRequestUpdate(final HttpServletRequest request,
			                                         final String pathValueString,
			                                         final String tableName,
			                                         final String requestPostPayload) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleRequestUpdate is null";
			throw new NullPointerException(errorText);
		}
		/* Check the inbound path value string passed by the caller */ 
	  if (pathValueString == null) {
		  String   errorText = "Inbound path value string reference passed to handleRequestUpdate is null";
		  throw new NullPointerException(errorText);			
	  }
		/* Check the table name string passed by the caller */ 
	  if (tableName == null) {
		  String   errorText = "Table name string reference passed to handleRequestUpdate is null";
		  throw new NullPointerException(errorText);			
	  }
		/* Check the request POST payload string (JSON) passed by the caller */ 
	  if (requestPostPayload == null) {
		  String   errorText = "Request post payload (JSON) string reference passed to handleRequestUpdate is null";
		  throw new NullPointerException(errorText);			
	  }
	  /* Declare/define a few variables */
	  String  outJson = null;	
		/* Declare/define a few variables */ 
		outJson = HDLmDatabase.updateTableRowsJson(requestPostPayload, tableName);
	  return outJson;
  } 
}
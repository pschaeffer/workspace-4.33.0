package com.headlamp;

import static com.headlamp.HDLmAssert.HDLmAssertAction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmBridgeTest short summary.
 *
 * HDLmBridgeTest description.
 *
 * @version 1.0
 * @author Peter
 */ 
class HDLmBridgeTest {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmBridgeTest.class); 
  @BeforeAll
	static void HDLmBridgeBeforeAll() throws Exception {
  	/* Start the Jetty server, if it has not already been started */
  	if (HDLmMain.checkMainExecuted() == false) {
      String[]  emptyArgument = {};  	 
			HDLmMain.main(emptyArgument);
	  }
		/* The log changes flag is set to 'no' (without the quotes),
	     so that changes are not logged. This value can be checked
	     to see if changes should be logged. */
	  HDLmState.setName("logChanges", "no");
	}
  @Test
	void handleRequest() {		
		/* Run a few handleRequest tests */
		/* The overall logic of this routine is too insert a company and then
		   delete it. */ 
  	HDLmApacheResponse          apacheResponse;
		HDLmHttpTypes               type; 
		String                      contentStr;
		String                      curlOut;
		ArrayList<HDLmDatabaseRow>  databaseRowList;
		String                      deleteEntriesUrl;
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      insertEntriesUrl;
		String                      jsonDelete;
		String                      jsonInsert; 
		String                      password;
		String                      protocol; 
		String                      tableName;
		String                      url;
		String                      userid; 
		/* Get all the rows (there may be none) for the test company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Get a few values needed below */
		userid = HDLmConfigInfo.getEntriesBridgeUserid();
		password = HDLmConfigInfo.getEntriesBridgePassword(); 
		type = HDLmHttpTypes.POST; 
		deleteEntriesUrl = HDLmConfigInfo.getEntriesBridgeDeleteUrl();
		insertEntriesUrl = HDLmConfigInfo.getEntriesBridgeInsertUrl();
		protocol = HDLmConfigInfo.getEntriesBridgeInternetMethod();
		/* Get the JSON insert statement */ 
		jsonInsert = HDLmTreeData.jsonBridgeInsert; 
		/* Build the final insert URL */
		url = protocol + "://" + insertEntriesUrl + tableName;
		ArrayList<String>   headerList = new ArrayList<String>();
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonInsert,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");
		/* Allocate the database row ID list for use below */ 
		ArrayList<HDLmDatabaseId>   localDatabaseIdList = new ArrayList<HDLmDatabaseId>();
		if (localDatabaseIdList == null) {
	 	  String   errorText = "Local database ID list is null in handleRequest";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Get the rows from the JSON */		
		/* Try to convert the JSON to a JSON object. If this fails, 
		   then we do not have a string than can be converted to a 
		   JSON object. If this works, then we do have string than 
		   can be converted to a JSON object. */
	  JsonParser    parser = new JsonParser();		
	  JsonElement   topNodeJsonElement = null; 
	  try {
		  topNodeJsonElement = parser.parse(curlOut);
	  } 
	  catch (Exception exception) {		
    	if (curlOut != null)
    		LOG.info("JSON string - " + curlOut);
		  LOG.info("Exception while executing handleRequest");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
		  return;
	  }		
	  /* Check if the JSON message passed by the caller is valid */
		if (!topNodeJsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string in handleRequest is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Get all of the row IDs and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in insert output is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Process each element in the JSON array. A row is built from 
	     each element in the JSON array. */ 
		JsonElement   infoElement = null;
		JsonObject    infoObj = null;  
		String        idString = null; 
		for (int i = 0; i < jsonArraySize; i++) { 
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	 	  if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in handleRequest is invalid";
		  	HDLmAssertAction(false, errorText);
		  }			 
			/* Get the ID value if it is present */			 
		  idString = HDLmJson.getJsonString(jsonArrayElement, "id");   
			HDLmDatabaseId  databaseId = new HDLmDatabaseId(idString);
			localDatabaseIdList.add(databaseId); 
		} 
		HDLmDatabaseIds   databaseIds = new HDLmDatabaseIds(localDatabaseIdList);	
		/* Convert the new database rows instance to JSON */
		Gson    gsonInstance = HDLmMain.gsonMain;
		jsonDelete = gsonInstance.toJson(databaseIds); 
		/* Build the final delete URL */
		url = protocol + "://" + deleteEntriesUrl + tableName; 
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonDelete,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);	
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");		
		{ 
			final HDLmHttpTypes       localHttpType = HDLmHttpTypes.POST;
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			HttpServletResponse       localResponse = Mockito.mock(HttpServletResponse.class); 
      final String              localPathValueString = "";
      final String              localClientStr = "127.0.0.1:65432";
      final String              localRequestPostPayload = "{}";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequest(null, 
					                              		                             localResponse, 
					                              		                             localPathValueString,
					                              		                             localRequestPostPayload,
					                              		                             localHttpType);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Servlet request passed to handleRequest is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			final HDLmHttpTypes       localHttpType = HDLmHttpTypes.POST;
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			HttpServletResponse       localResponse = Mockito.mock(HttpServletResponse.class); 
      final String              localPathValueString = "";
      final String              localClientStr = "127.0.0.1:65432";
      final String              localRequestPostPayload = "{}";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequest(localRequest, 
      		                                                               null,  
					                              		                             localPathValueString,
					                              		                             localRequestPostPayload,
					                              		                             localHttpType);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Servlet response passed to handleRequest is null", execMsg,
					         "Unexpected exception message");
		}		 	
		{
			final HDLmHttpTypes       localHttpType = HDLmHttpTypes.POST;
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			HttpServletResponse       localResponse = Mockito.mock(HttpServletResponse.class); 
      final String              localPathValueString = "";
      final String              localClientStr = "127.0.0.1:65432";
      final String              localRequestPostPayload = "{}";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequest(localRequest, 
      		                                                               localResponse,  
					                              		                             null,
					                              		                             localRequestPostPayload,
					                              		                             localHttpType);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Inbound path value string reference passed to handleRequest is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			final HDLmHttpTypes       localHttpType = HDLmHttpTypes.POST;
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			HttpServletResponse       localResponse = Mockito.mock(HttpServletResponse.class); 
      final String              localPathValueString = "";
      final String              localClientStr = "127.0.0.1:65432";
      final String              localRequestPostPayload = "{}";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequest(localRequest, 
      		                                                               localResponse,  
					                              		                             localPathValueString,
					                              		                             null,
					                              		                             localHttpType);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Request post payload value string reference passed to handleRequest is null", execMsg,
					         "Unexpected exception message");
		}		
		{
			final HDLmHttpTypes       localHttpType = HDLmHttpTypes.POST;
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			HttpServletResponse       localResponse = Mockito.mock(HttpServletResponse.class); 
      final String              localPathValueString = "";
      final String              localClientStr = "127.0.0.1:65432";
      final String              localRequestPostPayload = "{}";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequest(localRequest, 
      		                                                               localResponse,  
					                              		                             localPathValueString,
					                              		                             localRequestPostPayload,
					                              		                             null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("HTTP type enum reference passed to handleRequest is null", execMsg,
					         "Unexpected exception message");
		}	
	}
	@Test
	void handleRequestDelete() {	
		/* Run a few handleRequestDelete tests */
		/* The overall logic of this routine is too insert a company and then
		   delete it. The delete of the company is the actual test. */ 
		HDLmApacheResponse          apacheResponse;
		HDLmHttpTypes               type; 
		String                      contentStr;
		String                      curlOut;
		ArrayList<HDLmDatabaseRow>  databaseRowList;
		String                      deleteEntriesUrl;
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      insertEntriesUrl;
		String                      jsonDelete;
		String                      jsonInsert; 
		String                      password;
		String                      protocol; 
		String                      tableName;
		String                      url;
		String                      userid; 
		/* Get all the rows (there may be none) for the test company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Get a few values needed below */
		userid = HDLmConfigInfo.getEntriesBridgeUserid();
		password = HDLmConfigInfo.getEntriesBridgePassword(); 
		type = HDLmHttpTypes.POST; 
		deleteEntriesUrl = HDLmConfigInfo.getEntriesBridgeDeleteUrl();
		insertEntriesUrl = HDLmConfigInfo.getEntriesBridgeInsertUrl();
		protocol = HDLmConfigInfo.getEntriesBridgeInternetMethod();
		/* Get the JSON insert statement */ 
		jsonInsert = HDLmTreeData.jsonBridgeInsert; 
		/* Build the final insert URL */
		url = protocol + "://" + insertEntriesUrl + tableName;
		ArrayList<String>   headerList = new ArrayList<String>();
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonInsert,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);		
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");
		/* Allocate the row ID list for use below */
		ArrayList<Integer>  localRowIdList = new ArrayList<Integer>(); 
		if (localRowIdList == null) {
	 	  String   errorText = "Local row ID list is null in handleRequestDelete";
			HDLmAssertAction(false, errorText);		    	
	  }
		ArrayList<HDLmDatabaseId>   localDatabaseIdList = new ArrayList<HDLmDatabaseId>();
		if (localDatabaseIdList == null) {
	 	  String   errorText = "Local database ID list is null in handleRequestDelete";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Get the rows from the JSON */		
		/* Try to convert the JSON to a JSON object. If this fails, 
		   then we do not have a string than can be converted to a 
		   JSON object. If this works, then we do have string than 
		   can be converted to a JSON object. */
	  JsonParser    parser = new JsonParser();		
	  JsonElement   topNodeJsonElement = null; 
	  try {
		  topNodeJsonElement = parser.parse(curlOut);
	  } 
	  catch (Exception exception) {		
    	if (curlOut != null)
    		LOG.info("JSON string - " + curlOut);
		  LOG.info("Exception while executing handleRequestDelete");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
		  return;
	  }		
	  /* Check if the JSON message passed by the caller is valid */
		if (!topNodeJsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string in handleRequestDelete is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Get all of the row IDs and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in insert output is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Process each element in the JSON array. A row is built from 
	     each element in the JSON array. */ 
		JsonElement   infoElement = null;
		JsonObject    infoObj = null;  
		String        idString = null; 
		for (int i = 0; i < jsonArraySize; i++) { 
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	 	  if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in handleRequestDelete is invalid";
		  	HDLmAssertAction(false, errorText);
		  }			 
			/* Get the ID value if it is present */			 
		  idString = HDLmJson.getJsonString(jsonArrayElement, "id");  
			localRowIdList.add(HDLmUtility.convertInteger(idString)); 
			HDLmDatabaseId  databaseId = new HDLmDatabaseId(idString);
			localDatabaseIdList.add(databaseId); 
		} 
		HDLmDatabaseIds   databaseIds = new HDLmDatabaseIds(localDatabaseIdList);	
		/* Convert the new database rows instance to JSON */
		Gson    gsonInstance = HDLmMain.gsonMain;
		jsonDelete = gsonInstance.toJson(databaseIds); 
		/* Build the final delete URL */
		url = protocol + "://" + deleteEntriesUrl + tableName; 
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonDelete,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);	
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");		
		{ 
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestDelete(null, 
					                              		                                   localPathValueString,
					                                                                     localTableName,
					                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Servlet request passed to handleRequestDelete is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestDelete(localRequest, 
      		                                                                     null, 
                                                                               localTableName,
					                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Inbound path value string reference passed to handleRequestDelete is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestDelete(localRequest, 
					                                                                		 localPathValueString, 
                                                                               null,
					                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Table name string reference passed to handleRequestDelete is null", execMsg,
					         "Unexpected exception message");
		}		
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestDelete(localRequest, 
					                                                                		 localPathValueString, 
                                                                               localTableName,
					                                                                     null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Request post payload (JSON) string reference passed to handleRequestDelete is null", execMsg,
					         "Unexpected exception message");
		}	
	}
	@Test
	void handleRequestInsert() {	 
		/* Run a few handleRequestInsert tests */
		/* The overall logic of this routine is too insert a company and then
		   delete it. The insert of the company is the actual test. */ 
		HDLmHttpTypes               type; 
		String                      contentStr;
		String                      curlOut;
		ArrayList<HDLmDatabaseRow>  databaseRowList;
		String                      deleteEntriesUrl;
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      insertEntriesUrl;
		String                      jsonDelete;
		String                      jsonInsert; 
		String                      password;
		String                      protocol; 
		String                      tableName;
		String                      url;
		String                      userid; 
		/* Get all the rows (there may be none) for the test company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Get a few values needed below */
		userid = HDLmConfigInfo.getEntriesBridgeUserid();
		password = HDLmConfigInfo.getEntriesBridgePassword(); 
		type = HDLmHttpTypes.POST; 
		deleteEntriesUrl = HDLmConfigInfo.getEntriesBridgeDeleteUrl();
		insertEntriesUrl = HDLmConfigInfo.getEntriesBridgeInsertUrl();
		protocol = HDLmConfigInfo.getEntriesBridgeInternetMethod();
		/* Get the JSON insert statement */ 
		jsonInsert = HDLmTreeData.jsonBridgeInsert; 
		/* Build the final insert URL */
		url = protocol + "://" + insertEntriesUrl + tableName;
		ArrayList<String>   headerList = new ArrayList<String>();
		HDLmApacheResponse  apacheResponse;
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonInsert,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);		
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");
		/* Allocate the row ID list for use below */
		ArrayList<Integer>  localRowIdList = new ArrayList<Integer>(); 
		if (localRowIdList == null) {
	 	  String   errorText = "Local row ID list is null in handleRequestInsert";
			HDLmAssertAction(false, errorText);		    	
	  }
		ArrayList<HDLmDatabaseId>   localDatabaseIdList = new ArrayList<HDLmDatabaseId>();
		if (localDatabaseIdList == null) {
	 	  String   errorText = "Local database ID list is null in handleRequestInsert";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Get the rows from the JSON */		
		/* Try to convert the JSON to a JSON object. If this fails, 
		   then we do not have a string than can be converted to a 
		   JSON object. If this works, then we do have string than 
		   can be converted to a JSON object. */
	  JsonParser    parser = new JsonParser();		
	  JsonElement   topNodeJsonElement = null; 
	  try {
		  topNodeJsonElement = parser.parse(curlOut);
	  } 
	  catch (Exception exception) {		
    	if (curlOut != null)
    		LOG.info("JSON string - " + curlOut);
		  LOG.info("Exception while executing handleRequestInsert");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
		  return;
	  }		
	  /* Check if the JSON message passed by the caller is valid */
		if (!topNodeJsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string in handleRequestInsert is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Get all of the row IDs and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in insert output is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Process each element in the JSON array. A row is built from 
	     each element in the JSON array. */ 
		JsonElement   infoElement = null;
		JsonObject    infoObj = null;  
		String        idString = null; 
		for (int i = 0; i < jsonArraySize; i++) { 
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	 	  if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in handleRequestInsert is invalid";
		  	HDLmAssertAction(false, errorText);
		  }			 
			/* Get the ID value if it is present */			 
		  idString = HDLmJson.getJsonString(jsonArrayElement, "id");  
			localRowIdList.add(HDLmUtility.convertInteger(idString)); 
			HDLmDatabaseId  databaseId = new HDLmDatabaseId(idString);
			localDatabaseIdList.add(databaseId); 
		} 
		HDLmDatabaseIds   databaseIds = new HDLmDatabaseIds(localDatabaseIdList);	
		/* Convert the new database rows instance to JSON */
		Gson                gsonInstance = HDLmMain.gsonMain; 
		jsonDelete = gsonInstance.toJson(databaseIds); 
		/* Build the final delete URL */
		url = protocol + "://" + deleteEntriesUrl + tableName;
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonDelete,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);	
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");		
		{ 
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestInsert(null, 
					                              		                                   localPathValueString,
					                                                                     localTableName,
					                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Servlet request passed to handleRequestInsert is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestInsert(localRequest, 
      		                                                                     null, 
                                                                               localTableName,
					                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Inbound path value string reference passed to handleRequestInsert is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestInsert(localRequest, 
					                                                                		 localPathValueString, 
                                                                               null,
					                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Table name string reference passed to handleRequestInsert is null", execMsg,
					         "Unexpected exception message");
		}		
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestInsert(localRequest, 
					                                                                		 localPathValueString, 
                                                                               localTableName,
					                                                                     null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Request post payload (JSON) string reference passed to handleRequestInsert is null", execMsg,
					         "Unexpected exception message");
		}		
	}
  @Test
	void handleRequestRead() {		
		/* Run a few handleRequest tests */
		/* The overall logic of this routine is too insert a company and then
		   delete it. */ 
		HDLmHttpTypes               type; 
		String                      contentStr;
		String                      curlOut;
		String                      readEntriesUrl; 
		String                      jsonRead = ""; 
		String                      password;
		String                      protocol; 
		String                      query;
		String                      table;
		String                      url;
		String                      userid;  
		/* Get a few values needed below */
		contentStr = HDLmUtility.getContentString();
		table = HDLmConfigInfo.getEntriesDatabaseTableName();
		userid = HDLmConfigInfo.getEntriesBridgeUserid();
		password = HDLmConfigInfo.getEntriesBridgePassword(); 
		type = HDLmHttpTypes.GET; 
		readEntriesUrl = HDLmConfigInfo.getEntriesBridgeReadUrl();
		protocol = HDLmConfigInfo.getEntriesBridgeInternetMethod();
		query = HDLmUtility.buildBridgeRestQuery(contentStr, HDLmEditorTypes.PASS);
		/* Build the final read URL */
		url = protocol + "://" + readEntriesUrl + table + '?' + query;
		ArrayList<String>   headerList = new ArrayList<String>();
		HDLmApacheResponse  apacheResponse;
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonRead,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);		
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");
		{ 
			final String              localClientStr = "127.0.0.1:65432";
			final String              localContentStr = contentStr;
			final String              localRequestPostPayload = "{}"; 
			final String              localTableName = table;   
			final HttpServletRequest  localRequest = Mockito.mock(HttpServletRequest.class); 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestRead(null, 
					                              		                                 localContentStr,
					                              		                                 localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Servlet request passed to handleRequestRead is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			final String              localClientStr = "127.0.0.1:65432";
			final String              localContentStr = contentStr;
			final String              localRequestPostPayload = "{}"; 
			final String              localTableName = table;   
			final HttpServletRequest  localRequest = Mockito.mock(HttpServletRequest.class); 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestRead(localRequest,
      		                                                                   null,
      		                                                                   localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Content value passed to handleRequestRead is null", execMsg,
					         "Unexpected exception message");
		}		
		{
			final String              localClientStr = "127.0.0.1:65432";
			final String              localContentStr = contentStr;
			final String              localRequestPostPayload = "{}"; 
			final String              localTableName = table;   
			final HttpServletRequest  localRequest = Mockito.mock(HttpServletRequest.class); 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestRead(localRequest,
      		                                                                   localContentStr,
      		                                                                   null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Table name string reference passed to handleRequestRead is null", execMsg,
					         "Unexpected exception message");
		}
  }
	@Test
	void handleRequestUpdate() {		
		/* Run a few handleRequestUpdate tests */
		/* The overall logic of this routine is too insert a company and then
		   do some updates of the company. Finally, the company is deleted. 
		   The updates of the company are the actual test. */
		HDLmDatabaseRow             databaseRow;
		ArrayList<HDLmDatabaseRow>  databaseRowList;
		HDLmHttpTypes               type; 
		HDLmPassThruCompany         localCompany;
		HDLmPassThruData            localData;
		HDLmPassThruLists           localLists;
		HDLmPassThruReports         localReports;
		HDLmPassThruRules           localRules;
		HDLmTree                    localTree;
		HDLmTreeTypes               localType;
		int                         i;
		String                      contentStr;
		String                      curlOut;
		String                      deleteEntriesUrl;
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      insertEntriesUrl;
		String                      updateEntriesUrl;
		String                      jsonDelete;
		String                      jsonInsert; 
		String                      jsonUpdate;
		String                      password;
		String                      protocol; 
		String                      tableName;
		String                      url;
		String                      userid; 
		/* Get all the rows (there may be none) for the test company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Get a few values needed below */
		userid = HDLmConfigInfo.getEntriesBridgeUserid();
		password = HDLmConfigInfo.getEntriesBridgePassword(); 
		type = HDLmHttpTypes.POST; 
		deleteEntriesUrl = HDLmConfigInfo.getEntriesBridgeDeleteUrl();
		insertEntriesUrl = HDLmConfigInfo.getEntriesBridgeInsertUrl();
		updateEntriesUrl = HDLmConfigInfo.getEntriesBridgeUpdateUrl();
		protocol = HDLmConfigInfo.getEntriesBridgeInternetMethod();
		/* Get the JSON insert statement */ 
		jsonInsert = HDLmTreeData.jsonBridgeInsert; 
		/* Build the final insert URL */
		url = protocol + "://" + insertEntriesUrl + tableName;
		ArrayList<String>   headerList = new ArrayList<String>();
		HDLmApacheResponse  apacheResponse;
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonInsert,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);	
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");
		/* We can now do an update on the row(s) we just inserted */		
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		/* Process each of the rows */
		for (i = 0; i < databaseRowList.size(); i++) {
			/* Get the current row */
			databaseRow = databaseRowList.get(i);
			/* Get the info string and set the info field to null */
			String  jsonInfo = databaseRow.getInfo();	
			String  jsonInfoTemp = jsonInfo;
			databaseRow.setInfoNull();
			jsonInfoTemp = jsonInfoTemp.replaceAll("created", "dummyCreated");
			jsonInfoTemp = jsonInfoTemp.replaceAll("lastModified", "dummyLastModified");
			/* Create an HDLmTree instance from the info string and store 
		     it in the database row */
		  Gson  gsonInstanceRow = HDLmMain.gsonMain;
		  localTree  = gsonInstanceRow.fromJson(jsonInfoTemp, HDLmTree.class); 
		  databaseRow.setHDLmTreeInfo(localTree);
			/* Parse all of the info JSON and use the JSON elements to get
			   the details instance (HDLmMod and the extensions to HDLmMod) */
		  JsonParser    parser = new JsonParser();		
		  JsonElement   topNodeJsonElement = null; 		  
			topNodeJsonElement = parser.parse(jsonInfo);
		  JsonElement   detailsElement = HDLmJson.getJsonObject(topNodeJsonElement, "details");
		  /* Use the details to create the correct type of HDLmMod extension. We have five
		     different type of extensions to chose from. */ 
		  Gson  gsonInstanceMod = HDLmMain.gsonMain;
		  localType = localTree.getType();
		  switch(localType) {
			  case COMPANY: {
					localCompany = new HDLmPassThruCompany(detailsElement);
					/* The next line is not really needed. The updated rows are deleted
             later in any case. The company is not saved in any case. */	
					/* localTree.setDetails(localCompany); */
			  	break;
			  }
			  case DATA: {
					localData = new HDLmPassThruData(detailsElement);
					/* The next line is not really needed. The updated rows are deleted
	           later in any case. The data is not saved in any case. */	
					/* localTree.setDetails(localData); */
			  	break;
			  }			  
			  case LISTS: {
					localLists = new HDLmPassThruLists(detailsElement);
					localLists.setIgnoreListsNull();
					/* The next line is not really needed. The updated rows are deleted
		         later in any case. The lists are not saved in any case. */	
					/* localTree.setDetails(localLists); */
			  	break;
			  }
			  case REPORTS: {
					localReports = new HDLmPassThruReports(detailsElement);
					localReports.setReportsNull();
					/* The next line is not really needed. The updated rows are deleted
			       later in any case. The reports are not saved in any case. */	
					/* localTree.setDetails(localReports); */
			  	break;
			  }
			  case RULES: {
					localRules = new HDLmPassThruRules(detailsElement);
					/* The next line is not really needed. The updated rows are deleted
			       later in any case. The rules are not saved in any case. */	
					/* localTree.setDetails(localRules); */
			  	break;
			  }
			  default: {
	        String  errorText = "Invalid modification type value - " + localType.toString();
	        HDLmAssertAction(false, errorText);
			    break; 	
			  }
		  }			
		}
		/* Get the JSON update string and make a few changes to it */		
		Gson         gsonInstanceList = HDLmMain.gsonMain; 
		jsonUpdate = gsonInstanceList.toJson(databaseRowList); 
		jsonUpdate = jsonUpdate.replaceAll("HDLmTreeInfo", "info");
		jsonUpdate = jsonUpdate.replaceAll(dummyCompanyName, 'x' + dummyCompanyName);
		jsonUpdate = "{\"data\": " + jsonUpdate + '}';
		/* Build the final update URL */
		url = protocol + "://" + updateEntriesUrl + tableName; 
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonUpdate,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);		
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");
		/* Change the company name back to the original value */		
		jsonUpdate = jsonUpdate.replaceAll('x' + dummyCompanyName, dummyCompanyName);
		/* Run the final update URL */
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonUpdate,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);		
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");		
		/* Allocate the row ID list for use below */
		ArrayList<Integer>  localRowIdList = new ArrayList<Integer>(); 
		if (localRowIdList == null) {
	 	  String   errorText = "Local row ID list is null in handleRequestUpdate";
			HDLmAssertAction(false, errorText);		    	
	  }
		ArrayList<HDLmDatabaseId>   localDatabaseIdList = new ArrayList<HDLmDatabaseId>();
		if (localDatabaseIdList == null) {
	 	  String   errorText = "Local database ID list is null in handleRequestUpdate";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Get the rows from the JSON */		
		/* Try to convert the JSON to a JSON object. If this fails, 
		   then we do not have a string than can be converted to a 
		   JSON object. If this works, then we do have string than 
		   can be converted to a JSON object. */
	  JsonParser    parser = new JsonParser();		
	  JsonElement   topNodeJsonElement = null; 
	  try {
		  topNodeJsonElement = parser.parse(curlOut);
	  } 
	  catch (Exception exception) {		
    	if (curlOut != null)
    		LOG.info("JSON string - " + curlOut);
		  LOG.info("Exception while executing handleRequestUpdate");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
		  return;
	  }		
	  /* Check if the JSON message passed by the caller is valid */
		if (!topNodeJsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string in handleRequestUpdate is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Get all of the row IDs and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in Update output is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Process each element in the JSON array. A row is built from 
	     each element in the JSON array. */ 
		JsonElement   infoElement = null;
		JsonObject    infoObj = null;  
		String        idString = null; 
		for (i = 0; i < jsonArraySize; i++) { 
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	 	  if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in handleRequestUpdate is invalid";
		  	HDLmAssertAction(false, errorText);
		  }			 
			/* Get the ID value if it is present */			 
		  idString = HDLmJson.getJsonString(jsonArrayElement, "id");  
			localRowIdList.add(HDLmUtility.convertInteger(idString)); 
			HDLmDatabaseId  databaseId = new HDLmDatabaseId(idString);
			localDatabaseIdList.add(databaseId); 
		} 
		HDLmDatabaseIds   databaseIds = new HDLmDatabaseIds(localDatabaseIdList);	
		/* Convert the new database rows instance to JSON */
		Gson  gsonInstance = HDLmMain.gsonMain; 
		jsonDelete = gsonInstance.toJson(databaseIds); 
		/* Build the final delete URL */
		url = protocol + "://" + deleteEntriesUrl + tableName;
		apacheResponse = HDLmCurlApache.runCurl(url, userid, password, type, 
								                            headerList,
								                            jsonDelete,
								                            null,
								                            HDLmOutboundJson.OUTBOUNDJSONYES,
								                            HDLmSkipAuth.SKIPAUTHNO,
								                            HDLmReportErrors.DONTREPORTERRORS);	
		curlOut = apacheResponse.getStringContent();
		assertNotNull(curlOut, "Null response recieved from run Curl routine");		
		/* Delete all the rows for the dummy company */
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}	
		{ 
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestUpdate(null, 
					                              		                                   localPathValueString,
					                                                                     localTableName,
					                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Servlet request passed to handleRequestUpdate is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestUpdate(localRequest, 
      		                                                                     null, 
                                                                               localTableName,
  				                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Inbound path value string reference passed to handleRequestUpdate is null", execMsg,
					         "Unexpected exception message");
		}	
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestUpdate(localRequest, 
					                                                                		 localPathValueString, 
                                                                               null,
  				                                                                     localRequestPostPayload);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Table name string reference passed to handleRequestUpdate is null", execMsg,
					         "Unexpected exception message");
		}		
		{
			HttpServletRequest        localRequest = Mockito.mock(HttpServletRequest.class); 
			final String              localPathValueString = "";
			final String              localRequestPostPayload = "{}";
      final String              localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBridge.handleRequestUpdate(localRequest, 
					                                                                		 localPathValueString, 
                                                                               localTableName,
  				                                                                     null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Request post payload (JSON) string reference passed to handleRequestUpdate is null", execMsg,
					         "Unexpected exception message");
		}		
	}
}
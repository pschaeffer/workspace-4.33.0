package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmDatabaseTest short summary.
 *
 * HDLmDatabaseTest description.
 *
 * @version 1.0
 * @author Peter
 */ 
class HDLmDatabaseTest {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmDatabaseTest.class); 
  @BeforeAll
	static void HDLmDatabaseBeforeAll() throws Exception {
  	/* Start the Jetty server, if it has not already been started */
  	if (HDLmMain.checkMainExecuted() == false) {
      String[]  emptyArgument = {};  	 
			HDLmMain.main(emptyArgument);
	  }
	}
	@Test
	void deleteTableRows() {		
		/* Run a few deleteTableRows tests */
		/* The overall logic of this routine is too insert a company and then
		   delete it. The delete of the company is the actual test. */  
		ArrayList<HDLmDatabaseRow>  databaseRowList; 
		ArrayList<HDLmDatabaseRow>  rowList = new ArrayList<HDLmDatabaseRow>();
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      content; 
		String                      databaseOut;
		String                      jsonDelete;
		String                      jsonInsert;  
		String                      tableName; 
		/* Get all the rows (there may be none) for the test company */
		content = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Try to insert the company into the table */ 
		jsonInsert = HDLmTreeData.jsonBridgeInsert; 
		databaseOut = HDLmDatabase.insertTableRowsJson(jsonInsert, tableName);
		/* Get the rows from the JSON */		
		/* Try to convert the JSON to a JSON object. If this fails, 
		   then we do not have a string than can be converted to a 
		   JSON object. If this works, then we do have string than 
		   can be converted to a JSON object. */
	  JsonParser    parser = new JsonParser();		
	  JsonElement   topNodeJsonElement = null; 
	  try {
		  topNodeJsonElement = parser.parse(databaseOut);
	  } 
	  catch (Exception exception) {		
    	if (databaseOut != null)
    		LOG.info("JSON string - " + databaseOut);
		  LOG.info("Exception while executing deleteTableRows");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
	  }		
	  /* Check if the JSON message passed by the caller is valid */
		if (!topNodeJsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string in deleteTableRows is invalid";
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
		  	String  errorText = "JSON array element in deleteTableRows is invalid";
		  	HDLmAssertAction(false, errorText);
		  }			 
			/* Get the ID value if it is present */			 
		  idString = HDLmJson.getJsonString(jsonArrayElement, "id"); 		  
		  HDLmDatabaseRow   databaseRow = new HDLmDatabaseRow();
		  databaseRow.setId(idString);		  
			rowList.add(databaseRow); 
		}  
		/* Run the final delete rows */ 		 
		HDLmDatabase.deleteTableRows(rowList, tableName); 
		{ 
      final ArrayList<HDLmDatabaseRow>  localRowList = new ArrayList<HDLmDatabaseRow>(); 
      final String                      localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.deleteTableRows(null, 
					                              		                                 localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Row list value string passed to deleteTableRows is null",
					         "Unexpected exception message");
		}	
		{
      final ArrayList<HDLmDatabaseRow>  localRowList = new ArrayList<HDLmDatabaseRow>(); 
      final String                      localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.deleteTableRows(localRowList, 
      		                                                                   null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to deleteTableRows is null",
					         "Unexpected exception message");
		}			
	}
	@Test
	void deleteTableRowsJson() {		
		/* Run a few deleteTableRowsJson tests */
		/* The overall logic of this routine is too insert a company and then
		   delete it. The delete of the company using JSON is the actual test. */  
		ArrayList<HDLmDatabaseRow>  databaseRowList; 
		ArrayList<HDLmDatabaseRow>  rowList = new ArrayList<HDLmDatabaseRow>();
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      content; 
		String                      databaseOut;
		String                      jsonDelete;
		String                      jsonInsert;  
		String                      tableName; 
		/* Get all the rows (there may be none) for the test company */
		content = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Try to insert the company into the table */ 
		jsonInsert = HDLmTreeData.jsonBridgeInsert; 
		databaseOut = HDLmDatabase.insertTableRowsJson(jsonInsert, tableName);
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
		  topNodeJsonElement = parser.parse(databaseOut);
	  } 
	  catch (Exception exception) {		
    	if (databaseOut != null)
    		LOG.info("JSON string - " + databaseOut);
		  LOG.info("Exception while executing deleteTableRowsJson");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
	  }		
	  /* Check if the JSON message passed by the caller is valid */
		if (!topNodeJsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string in deleteTableRowsJson is invalid";
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
		  	String  errorText = "JSON array element in deleteTableRowsJson is invalid";
		  	HDLmAssertAction(false, errorText);
		  }			 
			/* Get the ID value if it is present */			 
		  idString = HDLmJson.getJsonString(jsonArrayElement, "id"); 		  
		  HDLmDatabaseId  databaseId = new HDLmDatabaseId(idString);
		  localDatabaseIdList.add(databaseId);
		}  
		/* Get the final delete JSON */
	  HDLmDatabaseIds   databaseIds = new HDLmDatabaseIds(localDatabaseIdList);	
	  /* Convert the new database rows instance to JSON */
	  Gson  gsonInstance = HDLmMain.gsonMain;
	  jsonDelete = gsonInstance.toJson(databaseIds); 
		/* Run the final delete rows */ 		 
		databaseOut = HDLmDatabase.deleteTableRowsJson(jsonDelete, tableName); 
		{ 
      final String  localRowListJson = ""; 
      final String  localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.deleteTableRowsJson(null, 
					                              		                                     localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Json string value passed to deleteTableRowsJson is null",
					         "Unexpected exception message");
		}	
		{
      final String  localRowListJson = ""; 
      final String  localTableName = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.deleteTableRowsJson(localRowListJson, 
      		                                                                       null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to deleteTableRowsJson is null",
					         "Unexpected exception message");
		}			
	}
	@Test
	void executeSqlQuery() {		
		/* Run a few (actually one) executeSqlQuery tests */  
		Connection                  localConnection;
		int                         numberOfRows = 0;
		ResultSet                   resultSet;
		Statement                   statement; 
		String                      databaseName;
		String                      sqlStatement;
		String                      tableName; 
		/* Run the execute SQL query routine */ 		
		databaseName = HDLmConfigInfo.getEntriesDatabaseDatabaseName();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		localConnection = HDLmDatabase.getConnectionDatabase();
    statement = HDLmDatabase.getStatement(localConnection); 
    sqlStatement = "SELECT * FROM " + databaseName + '.' + tableName + " WHERE ID = 0";
		resultSet = HDLmDatabase.executeSqlQuery(statement, sqlStatement); 
		assertNotNull(resultSet, "Null response recieved from execute query routine");
		/* Count the number of rows. The number of rows should be zero. */		
		try {
		  while (resultSet.next()) {
			  numberOfRows += 1;			
		  }
		}
	  catch (SQLException sqlException) { 
			if (sqlStatement != null)
			  LOG.info("SQL string - " + sqlStatement);
			LOG.info("SQLException while executing executeSqlQuery");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");				 
		}
		/* Check the number of rows */
		assertEquals(0, numberOfRows, "The result set has the wrong number of rows in executeSqlQuery");
		/* Release the used connection */
		HDLmDatabase.releaseConnection(localConnection);
		{ 
			final Connection  localNullTestConnection = HDLmDatabase.getConnectionDatabase();
      /* final Statement   localStatement = HDLmDatabase.getStatement(localConnection); */ 
      final String      localSql = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.executeSqlQuery(null, 
					                              		                                 localSql);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Statement reference passed to executeSqlQuery is null",
					         "Unexpected exception message");
			/* Release the used connection */
			HDLmDatabase.releaseConnection(localNullTestConnection);
		}	
		{
			final Connection  localNullTestConnection = HDLmDatabase.getConnectionDatabase();
      final Statement   localStatement = HDLmDatabase.getStatement(localNullTestConnection); 
      final String      localSql = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.executeSqlQuery(localStatement, 
      		                                                                   null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "SQL string reference passed to executeSqlQuery is null",
					         "Unexpected exception message");
			/* Release the used connection */
			HDLmDatabase.releaseConnection(localNullTestConnection);
		}			
	}
	@Test
	void getConnection() {		
		/* Run a few (actually one) getConnection tests */  
		Connection  localConnection;
		/* Get the database connection */ 		
		localConnection = HDLmDatabase.getConnectionDatabase();
		assertNotNull(localConnection, "Null response recieved from get connection routine");		
		/* Release the used connection */
		HDLmDatabase.releaseConnection(localConnection);
	}
	@Test
	void getStatement() {		
		/* Run a few (actually one) getStatement tests */  
		Connection  localConnection;
		Statement   statement;     
		/* Get the database statement */ 		
		localConnection = HDLmDatabase.getConnectionDatabase();
		statement = HDLmDatabase.getStatement(localConnection);
		assertNotNull(statement, "Null response recieved from get statement routine");		
		/* Release the used connection */
		HDLmDatabase.releaseConnection(localConnection);
		{
			final Connection  localNullTestConnection = HDLmDatabase.getConnectionDatabase();
      /* final Statement   localStatement = HDLmDatabase.getStatement(localConnection); */  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.getStatement(null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Connection reference passed to getStatement is null",
					         "Unexpected exception message");
			/* Release the used connection */
			HDLmDatabase.releaseConnection(localNullTestConnection);
		}		
	}	
	@Test
	void getTableRows() {		
		/* Run a few (actually one) getTableRows tests */  
		ArrayList<HDLmDatabaseRow>  databaseRowList; 
		int                         numberOfRows; 
		String                      contentStr;  
		String                      tableName; 
		/* Run the get table rows routine */ 		
		contentStr = HDLmUtility.getContentString(); 
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();  
		databaseRowList = HDLmDatabase.getTableRows(contentStr, tableName, null); 
		assertNotNull(databaseRowList, "Null response recieved from get table rows routine");		
		/* Check the number of rows */
		numberOfRows = databaseRowList.size();
		assertNotEquals(0, numberOfRows, "The row list has the wrong number of rows in getTableRows");
		{  
      final String  localContentStr = contentStr;
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.getTableRows(null, 
					                              		                              localTableName,
					                              		                              null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Content value string passed to getTableRows is null",
					         "Unexpected exception message");
		}	
		{
      final String  localContentStr = contentStr;
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.getTableRows(localContentStr, 
      		                                                                null,
      		                                                                null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to getTableRows is null",
					         "Unexpected exception message");
		}			
	}
	@Test
	void getTableRowsCompany() {		
		/* Run a few (actually one) getTableRowsCompany tests */  
		ArrayList<HDLmDatabaseRow>  databaseRowList; 
		int                         numberOfRows; 
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      contentStr;  
		String                      databaseOut;
		String                      jsonInsert;
		String                      tableName; 
		/* Delete all the rows (there may be none) for the dummy company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Run the get table rows company routine */ 		
		contentStr = HDLmUtility.getContentString(); 
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();  
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName); 
		assertNotNull(databaseRowList, "Null response recieved from get table rows company routine");		
		/* Check the number of rows */
		numberOfRows = databaseRowList.size();
		assertEquals(0, numberOfRows, "The row list has the wrong number of rows in getTableRowsCompany");
		/* Try to insert the dummy company into the table */ 
		jsonInsert = HDLmTreeData.jsonBridgeInsert; 
		databaseOut = HDLmDatabase.insertTableRowsJson(jsonInsert, tableName);
		/* The number of rows should now be positive */
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName); 
		assertNotNull(databaseRowList, "Null response recieved from get table rows company routine");		
		/* Check the number of rows */
		numberOfRows = databaseRowList.size();
		assertTrue(numberOfRows > 0, "At this point, we should have a few rows in getTableRowsCompany");
		/* Delete the company we just inserted */
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* The number of rows should now be zero */
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName); 
		assertNotNull(databaseRowList, "Null response recieved from get table rows company routine");		
		/* Check the number of rows */
		numberOfRows = databaseRowList.size();
		assertTrue(numberOfRows == 0, "At this point, we should not have any rows in getTableRowsCompany");
		{  
			final String  localCompanyName = dummyCompanyName;
      final String  localContentStr = contentStr;
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.getTableRowsCompany(null, 
					                              		                                     localTableName,
					                              		                                     localCompanyName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Content value string passed to getTableRowsCompany is null",
					         "Unexpected exception message");
		}	
		{
			final String  localCompanyName = dummyCompanyName;
      final String  localContentStr = contentStr;
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.getTableRowsCompany(localContentStr, 
      		                                                                       null,
      		                                                                       localCompanyName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to getTableRowsCompany is null",
					         "Unexpected exception message");
		}			
		{
			final String  localCompanyName = dummyCompanyName;
      final String  localContentStr = contentStr;
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.getTableRowsCompany(localContentStr, 
      		                                                                       localTableName,
      		                                                                       null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Company name string passed to getTableRowsCompany is null",
					         "Unexpected exception message");
		}		
	}
	@Test
	void getTableRowsJson() {		
		/* Run a few (actually one) getTableRowsJson tests */   
		HDLmDatabaseRows            databaseRows;
		int                         numberOfRows; 
		String                      contentStr;  
		String                      databaseOut;
		String                      tableName; 
		/* Run the get table rows JSON routine */ 		
		contentStr = HDLmUtility.getContentString(); 
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();  
		databaseOut = HDLmDatabase.getTableRowsJson(contentStr, tableName, null); 
		assertNotNull(databaseOut, "Null response recieved from get table rows JSON routine");		
		/* Check the number of rows */
	  Gson  gsonInstanceRows = HDLmMain.gsonMain;
	  databaseRows = gsonInstanceRows.fromJson(databaseOut, HDLmDatabaseRows.class);  
		numberOfRows = databaseRows.getData().size(); 
		assertNotEquals(0, numberOfRows, "The row list has the wrong number of rows in getTableRowsJson");
		{  
      final String  localContentStr = contentStr;
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.getTableRowsJson(null, 
					                              		                                  localTableName,
					                              		                                  null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Content value string passed to getTableRowsJson is null",
					         "Unexpected exception message");
		}	
		{
      final String  localContentStr = contentStr;
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.getTableRowsJson(localContentStr, 
      		                                                                    null,
      		                                                                    null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to getTableRowsJson is null",
					         "Unexpected exception message");
		}			
	}
	@Test
	void insertTableRows() {		
		/* Run a few (actually one) insertTableRows tests */  
		ArrayList<HDLmDatabaseRow>  databaseRowList; 
		HDLmDatabaseRows            databaseRows;
		ArrayList<Integer>          databaseRowIdList;
		Set<String>                 rowKeys = new HashSet<String>();
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      contentStr;  
		String                      jsonInsert;
		String                      tableName; 
		/* Delete all the rows (there may be none) for the dummy company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Build the set that contains the column names */
		rowKeys.add("content");
		rowKeys.add("info");
		rowKeys.add("name");		
		databaseRowList = new ArrayList<HDLmDatabaseRow>();
		/* Run the insert rows routine */  
		jsonInsert = HDLmTreeData.jsonBridgeInsert;
		/* Try to convert the JSON to a JSON object. If this fails, 
	     then we do not have a string than can be converted to a 
	     JSON object. If this works, then we do have string than 
	     can be converted to a JSON object. */
    JsonParser    parser = new JsonParser();		
    JsonElement   topNodeJsonElement = null; 
    try {
	    topNodeJsonElement = parser.parse(jsonInsert);
    } 
    catch (Exception exception) {		
 	    if (jsonInsert != null)
 		    LOG.info("JSON string - " + jsonInsert);
	    LOG.info("Exception while executing insertTableRows");
	    LOG.info(exception.getMessage(), exception);
	    HDLmEvent.eventOccurred("Exception");
    }		
    /* Check if the JSON message passed by the caller is valid */
	  if (!topNodeJsonElement.isJsonObject()) {
	    String  errorText = "JSON string in insertTableRows is invalid";
	    HDLmAssertAction(false, errorText);
    }
		/* Get all of the rows and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in insertTableRows is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Process each element in the JSON array. A row is built from 
	     each element in the JSON array. */ 
		JsonElement   infoElement = null;
		JsonObject    infoObj = null;  
		String        contentString = null;
		String        infoString = null;
		String        nameString = null; 
		for (int i = 0; i < jsonArraySize; i++) { 
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	 	  if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in insertTableRows is invalid";
		  	HDLmAssertAction(false, errorText);
		  }		
	 	  /* Create a new database row instance */
	 	  HDLmDatabaseRow   databaseRow = new HDLmDatabaseRow();
			/* Get a few values, if they are present */	
		  contentString = HDLmJson.getJsonString(jsonArrayElement, "content"); 	 
		  databaseRow.setContent(contentString);	
		  nameString = HDLmJson.getJsonString(jsonArrayElement, "name"); 	 
		  databaseRow.setName(nameString);		
		  
		  infoObj = HDLmJson.getJsonObject(jsonArrayElement, "info"); 
		  Gson  gsonInstanceRows = HDLmMain.gsonMain;
		  infoString = gsonInstanceRows.toJson(infoObj);  
		  databaseRow.setInfo(infoString);
		  /* Add the row to the list of rows */
		  databaseRowList.add(databaseRow); 
		}
	  /* Insert each of the rows */	 
	  databaseRowIdList = HDLmDatabase.insertTableRows(databaseRowList, rowKeys, tableName);	
	  assertNotNull(databaseRowIdList, "Null response recieved from insert rows routine");	
	  assertTrue(databaseRowIdList.size() > 0, "Row ID list is invalid in insert rows routine");
		/* Delete all the rows for the dummy company */
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}	  
		{  
			final ArrayList<HDLmDatabaseRow>  localRowList = databaseRowList;
      final Set<String>                 localRowKeys = rowKeys;
      final String                      localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.insertTableRows(null, 
					                              		                                 localRowKeys,
					                              		                                 localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Row list value string passed to insertTableRows is null",
					         "Unexpected exception message");
		}	
		{
			final ArrayList<HDLmDatabaseRow>  localRowList = databaseRowList;
      final Set<String>                 localRowKeys = rowKeys;
      final String                      localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.insertTableRows(localRowList,
      		                                                                   null,
      		                                                                   localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Row keys value string passed to insertTableRows is null",
					         "Unexpected exception message");
		}			
		{
			final ArrayList<HDLmDatabaseRow>  localRowList = databaseRowList;
      final Set<String>                 localRowKeys = rowKeys;
      final String                      localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.insertTableRows(localRowList, 
      		                                                                   localRowKeys,
      		                                                                   null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to insertTableRows is null",
					         "Unexpected exception message");
		}		
	}
	@Test
	void insertTableRowsJson() {		
		/* Run a few (actually one) insertTableRowsJson tests */  
		ArrayList<HDLmDatabaseRow>  databaseRowList; 
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      contentStr;  
		String                      databaseOut;
		String                      jsonInsert;
		String                      tableName; 
		/* Delete all the rows (there may be none) for the dummy company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		} 
		/* Run the insert rows JSON routine */  
		jsonInsert = HDLmTreeData.jsonBridgeInsert;
	  databaseOut = HDLmDatabase.insertTableRowsJson(jsonInsert, tableName);
	  assertNotNull(databaseOut, "Null response recieved from insert rows JSON routine");	 
		/* Delete all the rows for the dummy company */
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}	  
		{  
      final String                      localJsonString = "";
      final String                      localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.insertTableRowsJson(null, 
					                              		                                     localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Json string value passed to insertTableRowsJson is null",
					         "Unexpected exception message");
		}	
		{
      final String                      localJsonString = "";
      final String                      localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.insertTableRowsJson(localJsonString,
      		                                                                       null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to insertTableRowsJson is null",
					         "Unexpected exception message");
		}	
	}
	@Test
	void prepareStatement() {		
		/* Run a few (actually one) prepareStatement tests */  
		Connection                  localConnection;
		PreparedStatement           preparedStatement;
		Statement                   statement; 
		String                      databaseName;
		String                      sqlStatement;
		String                      tableName; 
		/* Run the execute SQL query routine */ 		
		databaseName = HDLmConfigInfo.getEntriesDatabaseDatabaseName();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		localConnection = HDLmDatabase.getConnectionDatabase(); 
    sqlStatement = "SELECT * FROM " + databaseName + '.' + tableName + " WHERE ID = 0";
		preparedStatement = HDLmDatabase.prepareStatement(localConnection, sqlStatement); 
		assertNotNull(preparedStatement, "Null response recieved from prepare SQL routine");
		/* Release the used connection */
		HDLmDatabase.releaseConnection(localConnection);
		{ 
			final Connection  localNullTestConnection = HDLmDatabase.getConnectionDatabase(); 
      final String      localSql = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.prepareStatement(null, 
					                              		                                  localSql);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Connection reference passed to prepareStatement is null",
					         "Unexpected exception message");
			/* Release the used connection */
			HDLmDatabase.releaseConnection(localNullTestConnection);
		}	
		{
			final Connection  localNullTestConnection = HDLmDatabase.getConnectionDatabase(); 
      final String      localSql = "";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.prepareStatement(localConnection, 
      		                                                                    null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "SQL string reference passed to prepareStatement is null",
					         "Unexpected exception message");
			/* Release the used connection */
			HDLmDatabase.releaseConnection(localNullTestConnection);
		}			
	}
	@Test
	void updateTableRows() {		
		/* Run a few (actually one) updateTableRows tests */
		HDLmDatabaseRow             databaseRow;
		ArrayList<HDLmDatabaseRow>  databaseRowList; 
		HDLmDatabaseRows            databaseRows;
		Integer                     databaseRowId;
		ArrayList<Integer>          databaseRowIdList;
		int                         i;
		int                         idListSize;
		int                         rowListSize;
		Set<String>                 rowKeysInsert = new HashSet<String>();
		Set<String>                 rowKeysUpdate = new HashSet<String>();
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      contentStr;  
		String                      jsonInsert;
		String                      tableName; 
		/* Delete all the rows (there may be none) for the dummy company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Build the set that contains the column names */
		rowKeysInsert.add("content");
		rowKeysInsert.add("info");
		rowKeysInsert.add("name");	
		rowKeysUpdate.add("content");
		rowKeysUpdate.add("id");
		rowKeysUpdate.add("info");
		rowKeysUpdate.add("name");		
		databaseRowList = new ArrayList<HDLmDatabaseRow>();
		/* Run the insert rows routine */  
		jsonInsert = HDLmTreeData.jsonBridgeInsert;
		/* Try to convert the JSON to a JSON object. If this fails, 
	     then we do not have a string than can be converted to a 
	     JSON object. If this works, then we do have string than 
	     can be converted to a JSON object. */
    JsonParser    parser = new JsonParser();		
    JsonElement   topNodeJsonElement = null; 
    try {
	    topNodeJsonElement = parser.parse(jsonInsert);
    } 
    catch (Exception exception) {		
 	    if (jsonInsert != null)
 		    LOG.info("JSON string - " + jsonInsert);
	    LOG.info("Exception while executing updateTableRows");
	    LOG.info(exception.getMessage(), exception);
	    HDLmEvent.eventOccurred("Exception");
    }		
    /* Check if the JSON message passed by the caller is valid */
	  if (!topNodeJsonElement.isJsonObject()) {
	    String  errorText = "JSON string in updateTableRows is invalid";
	    HDLmAssertAction(false, errorText);
    }
		/* Get all of the rows and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in updateTableRows is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Process each element in the JSON array. A row is built from 
	     each element in the JSON array. */ 
		JsonElement   infoElement = null;
		JsonObject    infoObj = null;  
		String        contentString = null;
		String        infoString = null;
		String        nameString = null; 
		for (i = 0; i < jsonArraySize; i++) { 
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	 	  if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in updateTableRows is invalid";
		  	HDLmAssertAction(false, errorText);
		  }		
	 	  /* Create a new database row instance */
	 	  databaseRow = new HDLmDatabaseRow();
			/* Get a few values, if they are present */	
		  contentString = HDLmJson.getJsonString(jsonArrayElement, "content"); 	 
		  databaseRow.setContent(contentString);	
		  nameString = HDLmJson.getJsonString(jsonArrayElement, "name"); 	 
		  databaseRow.setName(nameString);				  
		  infoObj = HDLmJson.getJsonObject(jsonArrayElement, "info"); 
		  Gson  gsonInstanceRows = HDLmMain.gsonMain;
		  infoString = gsonInstanceRows.toJson(infoObj);  
		  databaseRow.setInfo(infoString);
		  /* Add the row to the list of rows */
		  databaseRowList.add(databaseRow); 
		}
	  /* Insert each of the rows */	 
	  databaseRowIdList = HDLmDatabase.insertTableRows(databaseRowList, rowKeysInsert, tableName);	
	  assertNotNull(databaseRowIdList, "Null response recieved from insert rows routine");	
	  assertTrue(databaseRowIdList.size() > 0, "Row ID list is invalid in update rows routine");
	  /* Make sure all the list sizes are equal */
	  idListSize = databaseRowIdList.size();
	  rowListSize = databaseRowList.size();
	  assertTrue(idListSize == rowListSize, "List sizes are not equal in updateTreeRows");
	  /* We can now try to add the row ID values to each row */
	  for (i = 0; i < idListSize; i++) {
	  	databaseRow = databaseRowList.get(i);
	  	databaseRowId = databaseRowIdList.get(i);
	  	databaseRow.setId(databaseRowId.toString());  	
	  }
	  /* We can now try to update the inserted rows */
	  HDLmDatabase.updateTableRows(databaseRowList, rowKeysUpdate, tableName);	  
		/* Delete all the rows for the dummy company */
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}	  
		{  
			final ArrayList<HDLmDatabaseRow>  localRowList = databaseRowList;
      final Set<String>                 localRowKeys = rowKeysUpdate;
      final String                      localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.updateTableRows(null, 
					                              		                                 localRowKeys,
					                              		                                 localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Row list value string passed to updateTableRows is null",
					         "Unexpected exception message");
		}	
		{
			final ArrayList<HDLmDatabaseRow>  localRowList = databaseRowList;
      final Set<String>                 localRowKeys = rowKeysUpdate;
      final String                      localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.updateTableRows(localRowList,
      		                                                                   null,
      		                                                                   localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Row keys value string passed to updateTableRows is null",
					         "Unexpected exception message");
		}			
		{
			final ArrayList<HDLmDatabaseRow>  localRowList = databaseRowList;
      final Set<String>                 localRowKeys = rowKeysUpdate;
      final String                      localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.updateTableRows(localRowList, 
      		                                                                   localRowKeys,
      		                                                                   null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to updateTableRows is null",
					         "Unexpected exception message");
		}		
	}
	@Test
	void updateTableRowsJson() {		
		/* Run a few (actually one) updateTableRowsJson tests */
		HDLmDatabaseRow             databaseRow;
		ArrayList<HDLmDatabaseRow>  databaseRowList; 
		HDLmDatabaseRows            databaseRows;
		HDLmPassThruCompany         localCompany;
		HDLmPassThruData            localData;
		HDLmPassThruLists           localLists;
		HDLmPassThruReports         localReports;
		HDLmPassThruRules           localRules;
		HDLmTree                    localTree;
		HDLmTreeTypes               localType;
		Integer                     databaseRowId;
		ArrayList<Integer>          databaseRowIdList;
		int                         i;
		int                         idListSize;
		int                         rowListSize;
		Set<String>                 rowKeysInsert = new HashSet<String>();
		Set<String>                 rowKeysUpdate = new HashSet<String>();
		String                      dummyCompanyName = "zyxwvutsrq9876543210.com";
		String                      contentStr;  
		String                      infoStr;
		String                      jsonInsert;
		String                      jsonUpdate;
		String                      tableName; 
		/* Delete all the rows (there may be none) for the dummy company */
		contentStr = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}
		/* Build the set that contains the column names */
		rowKeysInsert.add("content");
		rowKeysInsert.add("info");
		rowKeysInsert.add("name");	
		rowKeysUpdate.add("content");
		rowKeysUpdate.add("id");
		rowKeysUpdate.add("info");
		rowKeysUpdate.add("name");		
		databaseRowList = new ArrayList<HDLmDatabaseRow>();
		/* Run the insert rows routine */  
		jsonInsert = HDLmTreeData.jsonBridgeInsert;
		/* Try to convert the JSON to a JSON object. If this fails, 
	     then we do not have a string than can be converted to a 
	     JSON object. If this works, then we do have string than 
	     can be converted to a JSON object. */
    JsonParser    parser = new JsonParser();		
    JsonElement   topNodeJsonElement = null; 
    try {
	    topNodeJsonElement = parser.parse(jsonInsert);
    } 
    catch (Exception exception) {		
 	    if (jsonInsert != null)
 		    LOG.info("JSON string - " + jsonInsert);
	    LOG.info("Exception while executing updateTableRowsJson");
	    LOG.info(exception.getMessage(), exception);
	    HDLmEvent.eventOccurred("Exception");
    }		
    /* Check if the JSON message passed by the caller is valid */
	  if (!topNodeJsonElement.isJsonObject()) {
	    String  errorText = "JSON string in updateTableRowsJson is invalid";
	    HDLmAssertAction(false, errorText);
    }
		/* Get all of the rows and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in updateTableRowsJson is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Process each element in the JSON array. A row is built from 
	     each element in the JSON array. */ 
		JsonElement   infoElement = null;
		JsonObject    infoObj = null;  
		String        contentString = null;
		String        infoString = null;
		String        nameString = null; 
		for (i = 0; i < jsonArraySize; i++) { 
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	 	  if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in updateTableRowsJson is invalid";
		  	HDLmAssertAction(false, errorText);
		  }		
	 	  /* Create a new database row instance */
	 	  databaseRow = new HDLmDatabaseRow();
			/* Get a few values, if they are present */	
		  contentString = HDLmJson.getJsonString(jsonArrayElement, "content"); 	 
		  databaseRow.setContent(contentString);	
		  nameString = HDLmJson.getJsonString(jsonArrayElement, "name"); 	 
		  databaseRow.setName(nameString);				  
		  infoObj = HDLmJson.getJsonObject(jsonArrayElement, "info"); 
		  Gson  gsonInstanceRows = HDLmMain.gsonMain;
		  infoString = gsonInstanceRows.toJson(infoObj);  
		  databaseRow.setInfo(infoString);
		  /* Add the row to the list of rows */
		  databaseRowList.add(databaseRow); 
		}
	  /* Insert each of the rows */	 
	  databaseRowIdList = HDLmDatabase.insertTableRows(databaseRowList, rowKeysInsert, tableName);	
	  assertNotNull(databaseRowIdList, "Null response recieved from insert rows routine");	
	  assertTrue(databaseRowIdList.size() > 0, "Row ID list is invalid in update rows JSON routine");
	  /* Make sure all the list sizes are equal */
	  idListSize = databaseRowIdList.size();
	  rowListSize = databaseRowList.size();
	  assertTrue(idListSize == rowListSize, "List sizes are not equal in updateTreeRowsJson");
	  /* We can now try to add the row ID values to each row */
	  for (i = 0; i < idListSize; i++) {
	  	databaseRow = databaseRowList.get(i);
	  	databaseRowId = databaseRowIdList.get(i);
	  	databaseRow.setId(databaseRowId.toString());
	  	/* Get the info string and set the info field to a 
	  	   null value */
	  	infoStr = databaseRow.getInfo();
	  	String  infoStrTemp = infoStr;
			infoStrTemp = infoStrTemp.replaceAll("created", "dummyCreated");
			infoStrTemp = infoStrTemp.replaceAll("lastModified", "dummyLastModified");
	  	databaseRow.setInfoNull();
	  	/* Create an HDLmTree instance from the info string and store 
	       it in the database row */
	    Gson  gsonInstanceRow = HDLmMain.gsonMain;
	    localTree  = gsonInstanceRow.fromJson(infoStrTemp, HDLmTree.class); 
	    databaseRow.setHDLmTreeInfo(localTree);
		  /* Parse all of the info JSON and use the JSON elements to get
		     the details instance (HDLmMod and the extensions to HDLmMod) */
	    parser = new JsonParser();		
	    topNodeJsonElement = null; 		  
		  topNodeJsonElement = parser.parse(infoStr);
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
			  }
	    }			
	  }
	  /* We can now try to update the inserted rows */
	  databaseRows = new HDLmDatabaseRows(databaseRowList);
	  Gson  gsonInstanceRows = HDLmMain.gsonMain;
	  jsonUpdate = gsonInstanceRows.toJson(databaseRows); 
	  jsonUpdate = jsonUpdate.replaceAll("HDLmTreeInfo", "info");
	  HDLmDatabase.updateTableRowsJson(jsonUpdate, tableName);	  
		/* Delete all the rows for the dummy company */
		databaseRowList = HDLmDatabase.getTableRowsCompany(contentStr, tableName, dummyCompanyName);
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}	  
		{  
			final String  localJsonString = "";
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.updateTableRowsJson(null,  
					                              		                                     localTableName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Json string value passed to updateTableRowsJson is null",
					         "Unexpected exception message");
		}	
		{
			final String  localJsonString = "";
      final String  localTableName = tableName;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmDatabase.updateTableRowsJson(localJsonString,
      		                                                                       null );}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Table name string passed to updateTableRowsJson is null",
					         "Unexpected exception message");
		}	
	}
}
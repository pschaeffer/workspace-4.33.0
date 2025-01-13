package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/**
 * HDLmUnReTest short summary.
 *
 * HDLmUnReTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmUnReTest {
  @BeforeAll
	static void HDLmUnReBeforeAll() {
		/* Get the Hikari data source. This call has the effect
	     of building the JDBC connection pool. */
	  HDLmHikariPool.getDataSource();
	  /* Initialize undo/redo processing. This is done by calling
	     an initialization routine to the undo/redo module. */ 
	  int   unReLimit = HDLmConfigInfo.getUnReLimit();
	  HDLmUnRe.unReStartup(unReLimit);	
  }
  @Test
  void unReAddOperation() {
		{   
			Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {HDLmUnRe.unReAddOperation(null);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "New undo/redo object passed to unReAddOperation is null",
					         "Unexpected exception message");
		}	
  }
  @Test
  void unReunReStartup() {
		{   
			Throwable exception = assertThrows(IllegalArgumentException.class, 
                                         () -> {HDLmUnRe.unReStartup(0);}, 
                                         "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Integer value (0) for undo/redo limit passed to unReStartup is invalid",
					         "Unexpected exception message");
		}	
		{   
			Throwable exception = assertThrows(IllegalArgumentException.class, 
                                         () -> {HDLmUnRe.unReStartup(-1);}, 
                                         "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Integer value (-1) for undo/redo limit passed to unReStartup is invalid",
					         "Unexpected exception message");
		}	
  }
  @Test
  void unReUnDoOperation() {
		{   
			Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {HDLmUnRe.unReUnDoOperation(null);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Change number passed to unReUnDoOperation is null",
					         "Unexpected exception message");
		}	
		{   
			Throwable exception = assertThrows(IllegalArgumentException.class, 
                                         () -> {HDLmUnRe.unReUnDoOperation(0);}, 
                                         "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Change number (0) for undo/redo passed to unReUnDoOperation is invalid",
					         "Unexpected exception message");
		}	
		{   
			Throwable exception = assertThrows(IllegalArgumentException.class, 
                                         () -> {HDLmUnRe.unReUnDoOperation(-1);}, 
                                         "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Change number (-1) for undo/redo passed to unReUnDoOperation is invalid",
					         "Unexpected exception message");
		}	
  }
  @Test
	void checkUnre() {
		/* The overall logic of this routine is too insert a company and then
		   transfer it. The transfer creates an undo/redo operation. We can then
		   undo the transfer operation. The actual test is the undo of the transfer. */  
		ArrayList<HDLmDatabaseRow>    databaseRowList;  
		ArrayList<ArrayList<String>>  dummyScopeArray;
		int                           unReNumber;
		String                        content; 
		String                        databaseOut;
		String                        dummyScope = "(zyxwvutsrq)";
		String                        errorMessage = null;
		String                        firstDummyCompanyName = "zyxwvutsrq9876543210.com";
		String                        jsonInsert; 
		String                        outJson;
		String                        secondDummyCompanyName = "zyxwvutsrq9876543211.com";
		String                        tableName; 
		/* Get all the rows (there may be none) for the test company */
		content = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		/* Delete the first dummy company from the table */
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, firstDummyCompanyName); 
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		} 
		/* Delete the second dummy company from the table */
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, secondDummyCompanyName); 
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}  
		/* Try to insert the company into the table */  
		jsonInsert = HDLmTreeData.jsonUnReInsert; 
		databaseOut = HDLmDatabase.insertTableRowsJson(jsonInsert, tableName); 
		/* The next two lines are not really needed */ 
		dummyScopeArray = HDLmSecurity.convertScopeString(dummyScope);		
		outJson = HDLmDatabase.getTableRowsJson(content, tableName, dummyScopeArray);  
		/* At this point we should have no undo/redo operations */
		unReNumber = HDLmUnRe.unReGetCount();
		assertEquals(0, unReNumber, "We should have no undo/redo operations at this point");
		unReNumber = HDLmUnRe.unReGetCurrent();
		assertEquals(0, unReNumber, "We should have no undo/redo operations at this point");
		unReNumber = HDLmUnRe.unReGetTop();
		assertEquals(0, unReNumber, "We should have no undo/redo operations at this point");
		unReNumber = HDLmUnRe.unReGetBottom();
		assertEquals(0, unReNumber, "We should have no undo/redo operations at this point");
		/* Create a JSON string with the transfer request in it */ 
    String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
										        "\"fromDivision\": \"example.com\"," + 
										        "\"fromSite\": \"example.com\", " +
										        "\"fromRule\": \"Notify Add To Cart\"," + 
										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
		   we do not have a string than can be converted to a JSON object. If this works,
		   then we do have string than can be converted to a JSON object. */
		JsonParser    parser = new JsonParser();		
		JsonElement   jsonElement = null; 
		try {
			jsonElement = parser.parse(transferJson);
		} 
		catch (Exception exception) {
			assertNull(jsonElement, "The transfer JSON string is not valid");
			return;
		}	
		/* Try to execute the transfer request */
		HDLmResponse  transferResponse = HDLmTransferSomething.transferSomething(jsonElement);		
		errorMessage = transferResponse.getErrorMessage();
		assertNull(errorMessage, "The transfer operation failed");
		/* At this point we should have one undo/redo operations */
		unReNumber = HDLmUnRe.unReGetCount();
		assertEquals(1, unReNumber, "We should have one undo/redo operations at this point");
		unReNumber = HDLmUnRe.unReGetCurrent();
		assertEquals(1, unReNumber, "We should have one undo/redo operations at this point");
		unReNumber = HDLmUnRe.unReGetTop();
		assertEquals(1, unReNumber, "We should have one undo/redo operations at this point");
		unReNumber = HDLmUnRe.unReGetBottom();
		assertEquals(1, unReNumber, "We should have one undo/redo operations at this point");
		/* At this point we will try to undo the transfer operation */
		int   undoNumber = 1;
		String  undoString = HDLmUnRe.unReUnDoOperation(undoNumber);
		/* At this point we should have one undo/redo operations */
		unReNumber = HDLmUnRe.unReGetCount();
		assertEquals(1, unReNumber, "We should have one undo/redo operations at this point");
		unReNumber = HDLmUnRe.unReGetCurrent();
		assertEquals(0, unReNumber, "We should have no undo/redo operations available at this point");
		unReNumber = HDLmUnRe.unReGetTop();
		assertEquals(1, unReNumber, "We should have one undo/redo operations at this point");
		unReNumber = HDLmUnRe.unReGetBottom();
		assertEquals(1, unReNumber, "We should have one undo/redo operations at this point");
		/* At this point we should not have any more undo/redo operations */		
		errorMessage = HDLmUnRe.unReUnDoOperation(undoNumber);
		assertNotNull(errorMessage, "The undo/redo operation failed");
		assertEquals(errorMessage, "No undo/redo operations to undo", "The undo/redo stack is empty");	
		/* Delete the first dummy company from the table */
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, firstDummyCompanyName); 
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		} 
		/* Delete the second dummy company from the table */
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, secondDummyCompanyName); 
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		} 
	}
  @Test
	void unReStartup() {
  	/* Add a (test) undo/redo startup */ 		 
		{   
			Throwable exception = assertThrows(IllegalArgumentException.class, 
                                         () -> {HDLmUnRe.unReStartup(0);}, 
                                         "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Integer value (0) for undo/redo limit passed to unReStartup is invalid",
					         "Unexpected exception message");
		}	
		{   
			Throwable exception = assertThrows(IllegalArgumentException.class, 
                                         () -> {HDLmUnRe.unReStartup(-1);}, 
                                         "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Integer value (-1) for undo/redo limit passed to unReStartup is invalid",
					         "Unexpected exception message");
		}
  }  
}
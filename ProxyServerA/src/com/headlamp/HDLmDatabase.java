package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmDatabase short summary.
 *
 * HDLmDatabase description. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmDatabase {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmDatabase.class);  
	/* The next field (a lock) is used to serialize some database 
     operations. In most cases, serialization is not needed or 
     wanted. However, database reads must come after database 
     inserts, updates, and deletes have been finished. */  
  private static ReentrantLock  databaseLock = new ReentrantLock();
	/* The rules (modifications) have been updated flag is initially set
	   to false. This flag is set to true, each time the rules have been
	   updated. */ 
  private static boolean  rulesHaveBeenUpdated = false;
    /* This class can never be instantiated */
	private HDLmDatabase() {}	    
	/* Delete a set of rows from the rules (modifications) table using a set 
	   of row ID values passed by the caller. Each of the rows to be deleted
	   has an ID value that was return to  the caller. The ID value is 
	   generated automatically by the database. */
	protected static void  deleteTableRows(final ArrayList<HDLmDatabaseRow> rowList,
			                                   final String tableName) {
		/* Check if the row list value passed by the caller is null */
		if (rowList == null) {
			String  errorText = "Row list value string passed to deleteTableRows is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to deleteTableRows is null";
			throw new NullPointerException(errorText);
		}
		/* Get the database lock. This lock is not freed until the
	     deletes are done. This step prevents database reads from 
	     happening until the lock is freed. */ 		
	  HDLmDatabase.getDatabaseLock();		
		/* Declare/define a few variables */
		Connection          localConnection = null;
		int                 localIdCount = 0;
		PreparedStatement   localPreparedStatement;
		Statement           localStatement = null;
		StringBuilder       sqlBuilder = new StringBuilder();
		String              sqlStr = "";
		String              database = HDLmConfigInfo.getEntriesDatabaseDatabaseName();
		String              domainName = HDLmConfigInfo.getEntriesDatabaseDomainName();
		/* Get a statement that can be used to execute SQL */
		localConnection = HDLmHikariPool.getConnectionHikari();
		if (localConnection == null) {
		  String   errorText = "Returned connection is null in deleteTableRows";
			HDLmAssertAction(false, errorText);		    	
	  }	 
		/* Build the DELETE that is executed below */
		sqlBuilder.append("DELETE FROM "); 
		sqlBuilder.append(database);
		sqlBuilder.append('.');
		sqlBuilder.append(tableName); 
		sqlBuilder.append(" WHERE ID IN (");  
		/* Process all of the rows passed by the caller */		 
	  for (HDLmDatabaseRow row : rowList) {
	  	/* Add a comma and a blank if need be */
	  	if (localIdCount >= 1)
	  		sqlBuilder.append(", ");
			localIdCount += 1;
			sqlBuilder.append(row.getId());							
		}
	  sqlBuilder.append(')'); 
		/* Get the final SQL string */
		sqlStr = sqlBuilder.toString();
		/* Execute the SQL */
		localPreparedStatement = HDLmDatabase.prepareStatement(localConnection, sqlStr);
		if (localPreparedStatement == null) {
		  String   errorText = "Returned prepared statement is null in deleteTableRows";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Process all of the rows passed by the caller */
		try {
			int   localRowsAffected = localPreparedStatement.executeUpdate();	
			/* Check the number of rows affected. This value should always be equal to
			   the number of rows that were deleted. */
			if (localRowsAffected != localIdCount) {
				LOG.info("Delete rows affected (" + ((Integer) localRowsAffected).toString() + ')'
						      + " should have been (" + ((Integer) localIdCount).toString() + ')');				
			  String   errorText = "Invalid number of affected rows in deleteTableRows";
				HDLmAssertAction(false, errorText);						
			}
			/* Try to release resources as need be */
			localPreparedStatement.close();
	  }		
	  catch (SQLException sqlException) { 
		  if (sqlStr != null)
		    LOG.info("SQL string - " + sqlStr);
		  LOG.info("SQLException while executing deleteTableRows");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			return;
		}			
		/* Release the used connection */
		HDLmHikariPool.releaseConnectionHikari(localConnection);
		/* Show that the database has been updated */
		HDLmDatabase.setRulesHaveBeenUpdated(true);
		/* Free the database lock. This lock is freed when the current 
	     operation is complete. This step prevents database reads from 
	     happening until the lock is freed. */ 		
	  HDLmDatabase.releaseDatabaseLock();
		return;
	}
	/* Delete a set of rows in the rules (modifications) table using the 
	   JSON string passed by the caller. The JSON string passed to this
	   routine must have the row ID for each row that is to be deleteed. */
	protected static String  deleteTableRowsJson(final String jsonStr, 
			                                         final String tableName) {
		/* Check if the json string value passed by the caller is null */
		if (jsonStr == null) {
			String  errorText = "Json string value passed to deleteTableRowsJson is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to deleteTableRowsJson is null";
			throw new NullPointerException(errorText);
		}
		/* Declare/define a few variables */
		ArrayList<HDLmDatabaseRow>  localRowList = null;
		ArrayList<HDLmDatabaseId>   localRowIdList = null;
		HDLmDatabaseReply           databaseReply;
		String                      jsonString;
		/* Allocate the row list for use below */
		localRowList = new ArrayList<HDLmDatabaseRow>();
		if (localRowList == null) {
	 	  String   errorText = "Local row list is null in deleteTableRowsJson";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Allocate the row ID list for use below */
		localRowIdList = new ArrayList<HDLmDatabaseId>();
		if (localRowIdList == null) {
	 	  String   errorText = "Local row ID list is null in deleteTableRowsJson";
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
		  topNodeJsonElement = parser.parse(jsonStr);
	  } 
	  catch (Exception exception) {		
    	if (jsonStr != null)
    		LOG.info("JSON string - " + jsonStr);
		  LOG.info("Exception while executing deleteTableRowsJson");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
		  return null;
	  }		
	  /* Check if the JSON message passed by the caller is valid */
		if (!topNodeJsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string passed to deleteTableRowsJson is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Get all of the deletes and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in inbound data is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Process each element in the JSON array. A row is built from 
	     each element in the JSON array. */ 
		JsonElement   infoElement = null;
		JsonObject    infoObj = null;  
		String        idString = null; 
		for (int i = 0; i < jsonArraySize; i++) {
			/* Create a new (empty) database row object */
			HDLmDatabaseRow   databaseRow = new HDLmDatabaseRow();
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	 	  if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in deleteTableRowsJson is invalid";
		  	HDLmAssertAction(false, errorText);
		  }			 
			/* Get each of the values if they are present */			 
		  idString = HDLmJson.getJsonString(jsonArrayElement, "id");
			databaseRow.setId(idString);	
			HDLmDatabaseId  databaseId = new HDLmDatabaseId(idString);
			if (databaseId == null) {
		 	  String   errorText = "Local row ID instance is null in deleteTableRowsJson";
				HDLmAssertAction(false, errorText);		    	
		  }
			localRowIdList.add(databaseId);
			/* Add the new row to the list of rows */
			localRowList.add(databaseRow);
		} 
		/* Delete all of the rows */
		HDLmDatabase.deleteTableRows(localRowList, tableName); 		
		/* Build a new database reply instance */
		databaseReply = new HDLmDatabaseReply(localRowIdList, 1);
		if (databaseReply == null) {
	 	  String   errorText = "Local reply instance is null in deleteTableRowsJson";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Convert the new database rows instance to JSON */
		Gson     gsonInstance = HDLmMain.gsonMain;
		jsonString = gsonInstance.toJson(databaseReply);
		return jsonString;
	}
	/* Delete a set of rows from the rules (modifications) table using a set 
	   of row ID values passed by the caller. Each of the rows to be deleted
	   has an ID value that was return to  the caller. The ID value is 
	   generated automatically by the database. The system name is a value
	   such as 'prod' or 'test' (without the quotes) that indicates which 
	   system the rows should be deleted from.*/
	protected static void  deleteTableRowsSystem(final ArrayList<HDLmDatabaseRow> deleteList, 
			                                         final String systemName) {			
		/* Check if the row list value passed by the caller is null */
		if (deleteList == null) {
			String  errorText = "Row list value string passed to deleteTableRowsSystem is null";
			throw new NullPointerException(errorText);
		}	 
		/* Check if the system name string value passed by the caller is null */
		if (systemName == null) {
			String  errorText = "System name string passed to deleteTableRowsSystem is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the system name is invalid */
		if (HDLmTransferSomething.checkSystemValue(systemName) != null) {
			String  errorText = "System name string passed to deleteTableRowsSystem is invalid";
			throw new NullPointerException(errorText);
		}
		/* Get the database lock. This lock is not freed until the
	     deletes are done. This step prevents database reads from 
	     happening until the lock is freed. */ 		
	  HDLmDatabase.getDatabaseLock();		
		/* Declare/define a few variables */
		boolean             localCheckTest = false;
		Connection          localConnection = null;
		int                 localIdCount = 0;
		PreparedStatement   localPreparedStatement;
		Statement           localStatement = null;
		StringBuilder       sqlBuilder = new StringBuilder();
		String              sqlStr = "";
		/* Try to get the name of the test system. This should never fail.
       However, you never know. */ 		
		String   testSystemName = HDLmDefines.getString("HDLMSYSTEMTEST");
		if (testSystemName == null) {
			String   errorFormat = "Define value for test system name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSYSTEMTEST");
			HDLmAssertAction(false, errorText);		    	
		}
		/* Check if we should delete rows from the test system or the production
		   system. A local variable is set to indicate this. */ 
		if (systemName.equals(testSystemName)) 
			localCheckTest = true;
		String  database = null; 
		String  domainName = null; 
		String  tableName = null;
		/* Check for the production system versus the test system */
		if (localCheckTest == false) {
		  database = HDLmConfigInfo.getEntriesDatabaseDatabaseNameProd();
		  domainName = HDLmConfigInfo.getEntriesDatabaseDomainNameProd();
		  tableName = HDLmConfigInfo.getEntriesDatabaseTableNameProd();
		}
		else {
		  database = HDLmConfigInfo.getEntriesDatabaseDatabaseNameTest();
		  domainName = HDLmConfigInfo.getEntriesDatabaseDomainNameTest();
		  tableName = HDLmConfigInfo.getEntriesDatabaseTableNameTest();
		}
		/* Try to connect to the database and get a connection object 
	     to use to execute SQL */
  	String  userName = HDLmConfigInfo.getEntriesDatabaseUserid();
	  String  passWord = HDLmConfigInfo.getEntriesDatabasePassword();
	  String  portNumberStr = ((Integer) HDLmConfigInfo.getEntriesDatabasePortNumber()).toString();
    String  urlStr = "jdbc:mysql://" + domainName + ":" + portNumberStr +
	                   "/" + database;
    try {
	  	localConnection = DriverManager.getConnection(urlStr, userName, passWord);
	  } 
    catch (SQLException sqlException) {
		  if (urlStr != null)
		    LOG.info("URL string - " + urlStr);
		  LOG.info("SQLException while executing deleteTableRowsSystem");
		  LOG.info(sqlException.getMessage(), sqlException);
		  HDLmEvent.eventOccurred("SQLException");	
		  return;
	  } 
		/* Check if a connection was obtained */ 
		if (localConnection == null) {
		  String   errorText = "Local connection is null in deleteTableRowsSystem";
			HDLmAssertAction(false, errorText);		    	
	  }	 
		/* Build the DELETE that is executed below */
		sqlBuilder.append("DELETE FROM "); 
		sqlBuilder.append(database);
		sqlBuilder.append('.');
		sqlBuilder.append(tableName); 
		sqlBuilder.append(" WHERE ID IN (");  
		/* Process all of the rows passed by the caller */		 
	  for (HDLmDatabaseRow row : deleteList) {
	  	/* Add a comma and a blank if need be */
	  	if (localIdCount >= 1)
	  		sqlBuilder.append(", ");
			localIdCount += 1;
			sqlBuilder.append(row.getId());							
		}
	  sqlBuilder.append(')'); 
		/* Get the final SQL string */
		sqlStr = sqlBuilder.toString();		
		/* Execute the SQL */
		localPreparedStatement = HDLmDatabase.prepareStatement(localConnection, sqlStr);
		if (localPreparedStatement == null) {
		  String   errorText = "Returned prepared statement is null in deleteTableRowsSystem";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Process all of the rows passed by the caller */
		try {
			int   localRowsAffected = localPreparedStatement.executeUpdate();	
			/* Check the number of rows affected. This value should always be equal to
			   the number of rows that were deleted. */
			if (localRowsAffected != localIdCount) {
				LOG.info("Delete rows affected (" + ((Integer) localRowsAffected).toString() + ')'
						      + " should have been (" + ((Integer) localIdCount).toString() + ')');				
			  String   errorText = "Invalid number of affected rows in deleteTableRowsSystem";
				HDLmAssertAction(false, errorText);						
			}
			/* Try to release resources as need be */
			localPreparedStatement.close();
			/* Release the used connection */
		  if (localConnection != null &&
			    !localConnection.isClosed()) {
			  localConnection.close();
			}
	  }		
	  catch (SQLException sqlException) { 
		  if (sqlStr != null)
		    LOG.info("SQL string - " + sqlStr);
		  LOG.info("SQLException while executing deleteTableRowsSystems");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			return;
		}		
		/* Show that the database has been updated */
		HDLmDatabase.setRulesHaveBeenUpdated(true);
		/* Free the database lock. This lock is freed when the current 
	     operation is complete. This step prevents database reads from 
	     happening until the lock is freed. */ 		
	  HDLmDatabase.releaseDatabaseLock();		
		return;
	}
	/* The next method executes a SQL statement passed by the caller.
	   The caller also provides a JDBC statement that can be used to 
	   execute the SQL. A result set is returned to the caller. */	  
	protected static ResultSet  executeSqlQuery(final Statement passedStatement, 
			                                        final String passedSql) {  	
		/* Check one or more values passed by the caller */
		if (passedStatement == null) {
			String   errorText = "Statement reference passed to executeSqlQuery is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the SQL passed by the caller */
		if (passedSql == null) {
			String   errorText = "SQL string reference passed to executeSqlQuery is null";
			throw new NullPointerException(errorText);			
		}
		/* Declare/define a few variables */
		ResultSet   localResultSet = null;
		try {
			localResultSet = passedStatement.executeQuery(passedSql);
		} 
		catch (SQLException sqlException) {
			if (passedSql != null)
			  LOG.info("Passed SQL - " + passedSql);
			LOG.info("SQLException while executing executeSqlQuery");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			localResultSet = null;
			return null;
		}
    return localResultSet;
	}
  /* The next method gets a database connection and returns it to the 
     caller. Eventually, some type of caching/pooling mechanism might
     be used. For now we create a new connection each time this routine
     is called. */	  
	protected static Connection  getConnectionDatabase() {  	
		/* Declare/define a few variables */
		Connection      localConnection = null;	
		/* Try to actually connect to the database */
	  try {
	  	/* The line below was used to get a JDBC database connection 
	  	   from the driver manager. This statement is no longer in 
	  	   use. We get the database connection from the connection 
	  	   pool instead. */ 
			/* localConnection = DriverManager.getConnection(connectStr); */ 
			localConnection = HDLmHikariPool.getConnectionHikari();
			if (localConnection == null) {
	    	String   errorText = "Returned connection is null in getConnectionDatabase";
				HDLmAssertAction(false, errorText);		    	
	    }
			/* We used to store the local connection in the connection
			   field. However, this routine will return the newly created
			   connection to the caller. This means that the connection 
			   is considered to be in use. */ 
			/* HDLmDatabase.setConnection(localConnection); */
		} 
	  catch (Exception Exception) { 
			LOG.info("Exception while executing HDLmDatabase.getConnectionDatabase");
			LOG.info(Exception.getMessage(), Exception);
			HDLmEvent.eventOccurred("SQLException");	
			localConnection = null;
			return null;
		}
		return localConnection;
	} 	
	/* Get the database lock. The lock may already be held. This is
	   not an error. If the lock is already held, then the lock count
	   is incremented. The 'after' lock hold count is returned to the 
	   caller. */ 
	protected static int  getDatabaseLock() {
		databaseLock.lock();
		return databaseLock.getHoldCount();
	}	  
	/* Return a flag to the caller, showing if the rules have been 
     updated. This flag is checked to determine if the rules need
     to be reloaded. */
  protected static boolean  getRulesHaveBeenUpdated() {
	  return HDLmDatabase.rulesHaveBeenUpdated;
  }
	/* The next method gets a database statement and returns it to the 
	   caller. The caller must provide a valid database connection for
	   this routine to use. */	  
	protected static Statement  getStatement(final Connection passedConnection) {  	
		/* Check one or more values passed by the caller */
		if (passedConnection == null) {
			String   errorText = "Connection reference passed to getStatement is null";
			throw new NullPointerException(errorText);			
		}
		/* Declare/define a few variables */
		Statement   localStatement = null;
		/* Build the statement and return it to the caller */
		try {
			localStatement = passedConnection.createStatement();
		} 
		catch (SQLException sqlException) {
			LOG.info("SQLException while executing getStatement");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			localStatement = null;
			return null;
		}
		return localStatement;
	} 	
	/* Get a set of rows from the rules (modifications) table using the 
	   content value passed by the caller. The content string passed
	   to this routine must be be a full content string such as
	   'config_javaa' (without the quotes). The scope array is
	   passed to this routine to filter the rows returned by this
	   routine. The scope array value may be null. This allows
	   unrestricted access to table rows. */
	protected static ArrayList<HDLmDatabaseRow>  getTableRows(final String contentStr,
			                                                      final String tableName,
			                                                      final ArrayList<ArrayList<String>> scopeArray) {
		/* Check if the content string value passed by the caller is null */
		if (contentStr == null) {
			String  errorText = "Content value string passed to getTableRows is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to getTableRows is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the scope array value passed by the caller is null */
		/* The check below is not OK in all cases, the scope array can 
	     be null in some cases. This is not an error. */ 
	  /*
		if (scopeArray == null) {
			String  errorText = "Scope array passed to getTableRows is null";
			throw new NullPointerException(errorText);
		}
		*/
		/* Get the database lock. This lock is not freed until the
       table rows are read. This step prevents other database 
       reads from happening until the lock is freed. */ 		
    HDLmDatabase.getDatabaseLock();	
		/* Try to get some information (a list of companies) from the scope array */
		ArrayList<String>  scopeArrayEntry = null;
		int                scopeArrayLen = 0;
	  int                scopeArrayEntryLen = 0;
	  if (scopeArray != null) {
	  	scopeArrayLen = scopeArray.size();
	  	if (scopeArrayLen > 0) 
	  		scopeArrayEntry = scopeArray.get(0);
	  	if (scopeArrayEntry != null)
	  		scopeArrayEntryLen = scopeArrayEntry.size();	  	
	  }
		/* Declare/define a few variables */
		Connection      localConnection = null;
		ResultSet       localResultSet = null;
		Statement       localStatement = null;
		StringBuilder   sqlBuilder = new StringBuilder();
		String          sqlStr = "";
		String          database = HDLmConfigInfo.getEntriesDatabaseDatabaseName();
		String          domainName = HDLmConfigInfo.getEntriesDatabaseDomainName();
		/* Allocate the row list that is returned to the caller */
		ArrayList<HDLmDatabaseRow>  rowList = new ArrayList<HDLmDatabaseRow>();
		if (rowList == null) {
    	String   errorText = "Row list is null in getTableRows";
			HDLmAssertAction(false, errorText);		    	
    }
		/* Get a statement that can be used to execute SQL */
		localConnection = HDLmHikariPool.getConnectionHikari();
		if (localConnection == null) {
    	String   errorText = "Returned connection is null in getTableRows";
			HDLmAssertAction(false, errorText);		    	
    }
		localStatement = HDLmDatabase.getStatement(localConnection);
		if (localStatement == null) {
    	String   errorText = "Returned statement is null in getTableRows";
			HDLmAssertAction(false, errorText);		    	
    }
		/* Build the SELECT that is executed below */
		sqlBuilder.append("SELECT info, content, name, id, company, report from "); 
		sqlBuilder.append(database);
		sqlBuilder.append('.');
		sqlBuilder.append(tableName);
		/* Check if we need to build a WHERE clause */ 
		if (contentStr != null) {
			sqlBuilder.append(" WHERE content = '");
			sqlBuilder.append(contentStr);
			sqlBuilder.append('\'');
		}	
		/* Check if the WHERE clause should limit access to a set of companies */
		if (scopeArrayEntryLen > 0) {
			sqlBuilder.append(" AND (company IS NULL");
			for (int i = 0; i < scopeArrayEntryLen; i++) {
				sqlBuilder.append(" OR LOCATE('");
				sqlBuilder.append(scopeArrayEntry.get(i));
				sqlBuilder.append('\'');
				sqlBuilder.append(", company) > 0");				
			}			
			sqlBuilder.append(")");
		}
		/* Get the final SQL string */
		sqlStr = sqlBuilder.toString();
		/* Execute the SQL */
		localResultSet = HDLmDatabase.executeSqlQuery(localStatement, sqlStr);
		if (localResultSet == null) {
    	String   errorText = "Returned result set is null in getTableRows";
			HDLmAssertAction(false, errorText);		    	
    }
		/* Process all of the rows in the result set */
		try {
	    while (localResultSet.next()) { 
	      /* Get some information for the current row */
	    	String localInfo = localResultSet.getString("info");
	      String localContent = localResultSet.getString("content");
	      String localName = localResultSet.getString("name");
	      int    localId = localResultSet.getInt("id");
	      String localCompany = localResultSet.getString("company");
	      String localReport = localResultSet.getString("report");
	      /* Build an row object instance with all of the values */
	      HDLmDatabaseRow   localRow = new HDLmDatabaseRow(localInfo, localContent,
	      		                                             localName, localId,
	      		                                             localCompany, localReport);
	  		if (localRow == null) {
	      	String   errorText = "Returned row is null in getTableRows";
	  			HDLmAssertAction(false, errorText);		    	
	      }
	      rowList.add(localRow);  
	    }
	    /* Try to release resources as need be */
	    localResultSet.close();
	    if (!localStatement.isClosed())
	    	localStatement.close();
		}
	  catch (SQLException sqlException) { 
			if (contentStr != null)
			  LOG.info("Content string - " + contentStr);
			LOG.info("SQLException while executing getTableRows");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			rowList = null;
			return null;
		}
		/* Release the used connection */
		HDLmHikariPool.releaseConnectionHikari(localConnection);
		/* Free the database lock. This lock is freed when the current 
       operation is complete. This step prevents database reads from 
       happening until the lock is freed. */ 		
    HDLmDatabase.releaseDatabaseLock();		
		return rowList;
	}
	/* Get a set of rows from the rules (modifications) table using the 
	   content value passed by the caller and for a specific company. 
	   The content string passed to this routine must be be a full 
	   content string such as 'config_javaa' (without the quotes). 
	   The company name must also be quite specific. */
	protected static ArrayList<HDLmDatabaseRow>  getTableRowsCompany(final String contentStr,
			                                                             final String tableName,
			                                                             final String companyName) {
		/* Check if the content string value passed by the caller is null */
		if (contentStr == null) {
			String  errorText = "Content value string passed to getTableRowsCompany is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to getTableRowsCompany is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the company name string value passed by the caller is null */
		if (companyName == null) {
			String  errorText = "Company name string passed to getTableRowsCompany is null";
			throw new NullPointerException(errorText);
		}
		/* Get the database lock. This lock is not freed until the
       table rows are read. This step prevents other database 
       reads from happening until the lock is freed. */ 		
    HDLmDatabase.getDatabaseLock();	
		/* Declare/define a few variables */
		Connection      localConnection = null;
		ResultSet       localResultSet = null;
		Statement       localStatement = null;
		StringBuilder   sqlBuilder = new StringBuilder();
		String          sqlStr = "";
		String          database = HDLmConfigInfo.getEntriesDatabaseDatabaseName();
		String          domainName = HDLmConfigInfo.getEntriesDatabaseDomainName();
		/* Allocate the row list that is returned to the caller */
		ArrayList<HDLmDatabaseRow>  rowList = new ArrayList<HDLmDatabaseRow>();
		if (rowList == null) {
	 	  String   errorText = "Row list is null in getTableRowsCompany";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Get a statement that can be used to execute SQL */
		localConnection = HDLmHikariPool.getConnectionHikari();
		if (localConnection == null) {
	 	String   errorText = "Returned connection is null in getTableRowsCompany";
			HDLmAssertAction(false, errorText);		    	
	  }
		localStatement = HDLmDatabase.getStatement(localConnection);
		if (localStatement == null) {
	 	  String   errorText = "Returned statement is null in getTableRowsCompany";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Build the SELECT that is executed below */
		sqlBuilder.append("SELECT info, content, name, id, company, report from "); 
		sqlBuilder.append(database);
		sqlBuilder.append('.');
		sqlBuilder.append(tableName);
		/* Check if we need to build a WHERE clause */ 
		if (contentStr != null) {
			sqlBuilder.append(" WHERE content = '");
			sqlBuilder.append(contentStr);
			sqlBuilder.append('\'');
		}	
		/* Add the company name to the where clause in the SQL */
		sqlBuilder.append(" AND company = '");
		sqlBuilder.append(companyName);
		sqlBuilder.append('\'');
		/* Get the final SQL string */
		sqlStr = sqlBuilder.toString();
		/* Execute the SQL */
		localResultSet = HDLmDatabase.executeSqlQuery(localStatement, sqlStr);
		if (localResultSet == null) {
	 	  String   errorText = "Returned result set is null in getTableRowsCompany";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Process all of the rows in the result set */
		try {
	    while (localResultSet.next()) { 
	      /* Get some information for the current row */
	      String  localInfo = localResultSet.getString("info");
	      String  localContent = localResultSet.getString("content");
	      String  localName = localResultSet.getString("name");
	      int     localId = localResultSet.getInt("id");
	      String  localCompany = localResultSet.getString("company");
	      String  localReport = localResultSet.getString("report");
	      /* Build an row object instance with all of the values */
	      HDLmDatabaseRow   localRow = new HDLmDatabaseRow(localInfo, localContent,
	     	 	                                               localName, localId,
	     		                                               localCompany, localReport);
	 		  if (localRow == null) {
	     	  String   errorText = "Returned row is null in getTableRowsCompany";
	 			  HDLmAssertAction(false, errorText);		    	
	      }
	      rowList.add(localRow);  
	    }
	    /* Try to release resources as need be */
	    localResultSet.close();
	    if (!localStatement.isClosed()) {
	   	  localStatement.close();
		  }
		}
	  catch (SQLException sqlException) { 
			if (contentStr != null)
			  LOG.info("Content string - " + contentStr);
			LOG.info("SQLException while executing getTableRowsCompany");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			rowList = null;
			return null;
		}
		/* Release the used connection */
		HDLmHikariPool.releaseConnectionHikari(localConnection);
		/* Free the database lock. This lock is freed when the current 
       operation is complete. This step prevents database reads from 
       happening until the lock is freed. */ 		
    HDLmDatabase.releaseDatabaseLock();	
		return rowList;
	}
	/* Get a set of rows from the rules (modifications) table using the 
	   content value passed by the caller and for a specific company. 
	   The content string passed to this routine must be be a full 
	   content string such as 'config_javaa' (without the quotes). 
	   The company name must also be quite specific. The system name
	   is a value such as 'prod' or 'test' (without the quotes) that
	   indicates which system the rows should be obtained from. */
	protected static ArrayList<HDLmDatabaseRow>  getTableRowsCompanySystem(final String contentStr, 
			                                                                   final String companyName,
			                                                                   final String systemName) {
		/* Check if the content string value passed by the caller is null */
		if (contentStr == null) {
			String  errorText = "Content value string passed to getTableRowsCompanySystem is null";
			throw new NullPointerException(errorText);
		}			 
		/* Check if the company name string value passed by the caller is null */
		if (companyName == null) {
			String  errorText = "Company name string passed to getTableRowsCompanySystem is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the company name is invalid */
		if (HDLmHttp.checkDomainName(companyName) != null) {
			String  errorText = "company name string passed to getTableRowsCompanySystem is invalid";
			throw new NullPointerException(errorText);
		}
		/* Check if the system name string value passed by the caller is null */
		if (systemName == null) {
			String  errorText = "System name string passed to getTableRowsCompanySystem is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the system name is invalid */
		if (HDLmTransferSomething.checkSystemValue(systemName) != null) {
			String  errorText = "System name string passed to getTableRowsCompanySystem is invalid";
			throw new NullPointerException(errorText);
		}
		/* Get the database lock. This lock is not freed until the
       table rows are read. This step prevents other database 
       reads from happening until the lock is freed. */ 		
    HDLmDatabase.getDatabaseLock();	
		/* Declare/define a few variables */
		boolean         localCheckTest = false;
		Connection      localConnection = null;
		ResultSet       localResultSet = null;
		Statement       localStatement = null;
		StringBuilder   sqlBuilder = new StringBuilder();
		String          sqlStr = "";
	  /* Try to get the name of the test system. This should never fail.
       However, you never know. */ 		
		String   testSystemName = HDLmDefines.getString("HDLMSYSTEMTEST");
		if (testSystemName == null) {
			String   errorFormat = "Define value for test system name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSYSTEMTEST");
			HDLmAssertAction(false, errorText);		    	
		}
		/* Check if we should get rows from the test system or the production
		   system. A local variable is set to indicate this. */ 
		if (systemName.equals(testSystemName)) 
			localCheckTest = true;
		String  database = null; 
		String  domainName = null; 
		String  tableName = null;
		/* Check for the production system versus the test system */
		if (localCheckTest == false) {
		  database = HDLmConfigInfo.getEntriesDatabaseDatabaseNameProd();
		  domainName = HDLmConfigInfo.getEntriesDatabaseDomainNameProd();
		  tableName = HDLmConfigInfo.getEntriesDatabaseTableNameProd();
		}
		else {
		  database = HDLmConfigInfo.getEntriesDatabaseDatabaseNameTest();
		  domainName = HDLmConfigInfo.getEntriesDatabaseDomainNameTest();
		  tableName = HDLmConfigInfo.getEntriesDatabaseTableNameTest();
		}
		/* Allocate the row list that is returned to the caller */
		ArrayList<HDLmDatabaseRow>  rowList = new ArrayList<HDLmDatabaseRow>();
		if (rowList == null) {
		  String   errorText = "Row list is null in getTableRowsCompanySystem";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Try to connect to the database and get a connection object 
		   to use to execute SQL */
		String  userName = HDLmConfigInfo.getEntriesDatabaseUserid();
		String  passWord = HDLmConfigInfo.getEntriesDatabasePassword();
		String  portNumberStr = ((Integer) HDLmConfigInfo.getEntriesDatabasePortNumber()).toString();
	  String  urlStr = "jdbc:mysql://" + domainName + ":" + portNumberStr +
		                 "/" + database;
	  try {
			localConnection = DriverManager.getConnection(urlStr, userName, passWord);
		} 
	  catch (SQLException sqlException) {
			if (contentStr != null)
			  LOG.info("Content string - " + contentStr);
			LOG.info("SQLException while executing getTableRowsCompanySystem");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			rowList = null;
			return null;
		} 
		/* Report an error if a connection was not obtained */
		if (localConnection == null) {
	  	String  errorText = "Returned connection is null in getTableRowsCompanySystem";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Get a statement that can be used to execute SQL */
		localStatement = HDLmDatabase.getStatement(localConnection);
		if (localStatement == null) {
		  String   errorText = "Returned statement is null in getTableRowsCompanySystem";
			HDLmAssertAction(false, errorText);		    	
	 }
		/* Build the SELECT that is executed below */
		sqlBuilder.append("SELECT info, content, name, id, company, report from "); 
		sqlBuilder.append(database);
		sqlBuilder.append('.');
		sqlBuilder.append(tableName);
		/* Check if we need to build a WHERE clause */ 
		if (contentStr != null) {
			sqlBuilder.append(" WHERE content = '");
			sqlBuilder.append(contentStr);
			sqlBuilder.append('\'');
		}	
		/* Add the company name to the where clause in the SQL */
		sqlBuilder.append(" AND company = '");
		sqlBuilder.append(companyName);
		sqlBuilder.append('\'');
		/* Get the final SQL string */
		sqlStr = sqlBuilder.toString();
		/* Execute the SQL */
		localResultSet = HDLmDatabase.executeSqlQuery(localStatement, sqlStr);
		if (localResultSet == null) {
		  String   errorText = "Returned result set is null in getTableRowsCompanySystem";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Process all of the rows in the result set */
		try {
	    while (localResultSet.next()) { 
	      /* Get some information for the current row */
	      String  localInfo = localResultSet.getString("info");
	      String  localContent = localResultSet.getString("content");
	      String  localName = localResultSet.getString("name");
	      int     localId = localResultSet.getInt("id");
	      String  localCompany = localResultSet.getString("company");
	      String  localReport = localResultSet.getString("report");
	      /* Build an row object instance with all of the values */
	      HDLmDatabaseRow   localRow = new HDLmDatabaseRow(localInfo, localContent,
	    	 	                                               localName, localId,
	    		                                               localCompany, localReport);
			  if (localRow == null) {
	    	  String   errorText = "Returned row is null in getTableRowsCompanySystem";
				  HDLmAssertAction(false, errorText);		    	
	      }
	      rowList.add(localRow);  
	   }
	   /* Try to release resources as need be */
	   localResultSet.close();
	   /* Try to release the statement */
	   if (localStatement != null &&
	       !localStatement.isClosed()) {
	  	 localStatement.close();
		 }
		 /* Release the used connection */
	   if (localConnection != null &&
		     !localConnection.isClosed()) {
		   localConnection.close();
		 }
	 }
	 catch (SQLException sqlException) { 
			if (contentStr != null)
			  LOG.info("Content string - " + contentStr);
			LOG.info("SQLException while executing getTableRowsCompanySystem");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			rowList = null;
			return null;
		}
		/* Free the database lock. This lock is freed when the current 
       operation is complete. This step prevents database reads from 
       happening until the lock is freed. */ 		
    HDLmDatabase.releaseDatabaseLock();	
		return rowList;
	}
	/* Get a set of rows from the rules (modifications) table using the 
	   content value passed by the caller. The content string passed
	   to this routine must be be a full content string such as
	   'config_javaa' (without the quotes). The scope array is
	   passed to this routine to filter the rows returned by this
	   routine. The scope array value may be null. This allows
	   unrestricted access to table rows. */
	protected static String  getTableRowsJson(final String contentStr,
			                                      final String tableName,
			                                      final ArrayList<ArrayList<String>> scopeArray) {
		/* Check if the content string value passed by the caller is null */
		if (contentStr == null) {
			String  errorText = "Content value string passed to getTableRowsJson is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to getTableRowsJson is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the scope array value passed by the caller is null */
		/* The check below is not OK in all cases, the scope array can 
		   be null in some cases. This is not an error. */ 
		/*
		if (scopeArray == null) {
			String  errorText = "Scope array passed to getTableRowsJson is null";
			throw new NullPointerException(errorText);
		}
		*/
		/* Declare/define a few variables */
		ArrayList<HDLmDatabaseRow>  rowList = null;
		HDLmDatabaseRows  databaseRows = null;
		String            jsonString = null; 
		/* Get the rows from the database */
		if (LOG.isDebugEnabled()) {
		  LOG.debug("In HDLmMain.getTableRowsJson");
		  LOG.debug(contentStr);
		  LOG.debug(tableName);
	  }
		if (scopeArray != null)
			if (LOG.isDebugEnabled())
	    	LOG.debug(scopeArray.toString());
		rowList = HDLmDatabase.getTableRows(contentStr, tableName, scopeArray);
		if (rowList == null) {
	 	  String   errorText = "Returned row list is null in getTableRowsJson";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Build a new database rows instance */
		databaseRows = new HDLmDatabaseRows(rowList);
		/* Convert the new database rows instance to JSON */
		Gson     gsonInstance = HDLmMain.gsonMain;
		jsonString = gsonInstance.toJson(databaseRows);
		return jsonString;
	}
	/* Insert a set of rows into the rules (modifications) table using a set 
	   of row values passed by the caller. Each of the new rows has an ID
	   value that is returned to the caller. The ID value is generated 
	   automatically by the database. */
	protected static ArrayList<Integer>  insertTableRows(final ArrayList<HDLmDatabaseRow> rowList, 
		                                                   final Set<String> rowKeys,
		                                                   final String tableName) {
		/* Check if the row list value passed by the caller is null */
		if (rowList == null) {
			String  errorText = "Row list value string passed to insertTableRows is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the row keys value passed by the caller is null */
		if (rowKeys == null) {
			String  errorText = "Row keys value string passed to insertTableRows is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to insertTableRows is null";
			throw new NullPointerException(errorText);
		}
		/* Get the database lock. This lock is not freed until the
       table rows are inserted. This step prevents database 
       reads from happening until the lock is freed. */ 		
    HDLmDatabase.getDatabaseLock();	
		/* Declare/define a few variables */
		ArrayList<Integer>  rowIdList = null;
		boolean             buildCompany = false;
		boolean             hasCompany = false;
		boolean             hasContent = false;
		boolean             hasId = false;
		boolean             hasInfo = false;
		boolean             hasName = false;
		boolean             hasReport = false;
		Connection          localConnection = null;
		int                 i;
		int                 localColCount = 0;
		int                 parmNumberCompany = 0;
		int                 parmNumberContent = 0;
		int                 parmNumberInfo = 0;
		int                 parmNumberName = 0;
		int                 parmNumberReport = 0;
		Integer             localId;
		PreparedStatement   localPreparedStatementInsert = null;
		PreparedStatement   localPreparedStatementLast = null;
		ResultSet           localResultSet = null; 
		StringBuilder       sqlBuilder = new StringBuilder();
		String              sqlStr = "";
		String              database = HDLmConfigInfo.getEntriesDatabaseDatabaseName();
		String              domainName = HDLmConfigInfo.getEntriesDatabaseDomainName();
		/* Get a statement that can be used to execute SQL */
		localConnection = HDLmHikariPool.getConnectionHikari();
		if (localConnection == null) {
		  String   errorText = "Returned connection is null in insertTableRows";
			HDLmAssertAction(false, errorText);		    	
	  }		 
		/* Prepare the SELECT query that will provide the ID of each 
		   inserted row */
		sqlStr = "SELECT LAST_INSERT_ID()";
		localPreparedStatementLast = HDLmDatabase.prepareStatement(localConnection, sqlStr);
		/* Check which column we actually have */
		hasCompany = rowKeys.contains("company");
		hasContent = rowKeys.contains("content");
		hasId = rowKeys.contains("id");
		hasInfo = rowKeys.contains("info");
		hasName = rowKeys.contains("name");
		hasReport = rowKeys.contains("report");
		/* Report a fatal error if the ID column was passed */
		if (hasId) {
			String   errorText = "Row ID value provided in insertTableRows";
			HDLmAssertAction(false, errorText);		
		}
		/* Check if the company name must be built from the 
		   node path */
		if (hasCompany == false)
			buildCompany = true;
		/* Build the INSERT that is executed below */
		sqlBuilder.append("INSERT INTO "); 
		sqlBuilder.append(database);
		sqlBuilder.append('.');
		sqlBuilder.append(tableName);
		/* Handle all of the columns that are actually passed by the caller */
		for (String rowKey : rowKeys) {	
			/* Handle the current key */
			localColCount += 1;
			if (localColCount > 1)
				sqlBuilder.append(", ");
			else
				sqlBuilder.append(" (");
			/* Set the parameter numbers */
			if (rowKey.equals("info"))
				parmNumberInfo = localColCount;
			if (rowKey.equals("content"))
				parmNumberContent = localColCount;
			if (rowKey.equals("name"))
				parmNumberName = localColCount;
			if (rowKey.equals("company"))
				parmNumberCompany = localColCount;
			if (rowKey.equals("report"))
				parmNumberReport = localColCount;
			/* Continue building the INSERT statement */			 
			sqlBuilder.append(rowKey);
		}
		/* At this point we may need to add the company column */
		if (buildCompany == true) {
			localColCount += 1;
			if (localColCount > 1)
				sqlBuilder.append(", ");
			else
				sqlBuilder.append(" (");
			sqlBuilder.append("company");
			parmNumberCompany = localColCount;			
		}
		/* Finish the insert SQL statement */
		sqlBuilder.append(") VALUES(");
		for (i = 0; i < localColCount; i++) {
			if (i > 0)
				sqlBuilder.append(", ");
			sqlBuilder.append('?');
		}
		sqlBuilder.append(')');
		/* Get the final SQL string */
		sqlStr = sqlBuilder.toString();
		/* Execute the SQL */
		localPreparedStatementInsert = HDLmDatabase.prepareStatement(localConnection, sqlStr);
		if (localPreparedStatementInsert == null) {
		  String   errorText = "Returned prepared statement is null in insertTableRows";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Process (insert) all of the rows passed by the caller */
		rowIdList = new ArrayList<Integer>();
		if (rowIdList == null) {
		  String   errorText = "Return ID list is null in insertTableRows";
			HDLmAssertAction(false, errorText);		    	
	  }
		try {
			for (HDLmDatabaseRow row : rowList) {
				/* Set a few values */
				if (hasInfo) {
				  localPreparedStatementInsert.setString(parmNumberInfo, row.getInfo());
				}
				if (hasContent) {
				  localPreparedStatementInsert.setString(parmNumberContent, row.getContent());
				}
				if (hasName) {
				  localPreparedStatementInsert.setString(parmNumberName, row.getName());
				}
				if (hasCompany || 
						buildCompany == true) {
					String  companyValue = null;
					if (hasCompany)
						companyValue = row.getCompany();
					else if (buildCompany == true) {
						String  infoStr = row.getInfo();
						/* Try to convert the JSON string to a JSON object. If this fails, 
					     then we do not have a string than can be converted to a JSON 
					     object. If this works, then we do have string than can be 
					     converted to a JSON object. */
				    JsonParser    parser = new JsonParser();		
				    JsonElement   jsonElement = null; 
				    try {
					    jsonElement = parser.parse(infoStr);
				    } 
				    catch (Exception exception) {	
				    	if (infoStr != null)
				    		LOG.info("Info string - " + infoStr);
					    LOG.info("Exception while executing insertTableRows");
					    LOG.info(exception.getMessage(), exception);
					    HDLmEvent.eventOccurred("Exception");
					    return null;
				    }		
					  /* Check if the JSON message passed by the caller is valid */
				  	if (!jsonElement.isJsonObject()) {
					  	String  errorText = "JSON info string used in insertTableRows is invalid";
					  	HDLmAssertAction(false, errorText);
					  }	
				  	/* Get the node path from the info string */
				  	JsonArray   jsonArray = HDLmJson.getJsonArray(jsonElement, "nodePath");
				    int         jsonArraySize = jsonArray.size();
				    /* Get the content value and check if we are inserting a proxy entry
				       or not. For proxy entries, the company name is actually the second
				       entry in the node path. */
				    String  content = null;
				    if (hasContent)
				    	content = row.getContent();
				    /* Check if we are inserting a proxy entry */
				    if (content != null && 
				    		content.startsWith("proxy")) {
					    if (jsonArraySize >= 2) {
					    	String  topString = jsonArray.get(0).getAsString(); 
					    	if (topString.equals("Top")) 
					    		companyValue = jsonArray.get(1).getAsString();
					    }				    	
				    }
				    /* Check if we are inserting something other than a proxy entry */
				    if (content != null && 
				    		!content.startsWith("proxy")) {				    
					    if (jsonArraySize >= 3) {
					    	String  topString = jsonArray.get(0).getAsString();
					    	String  companiesString = jsonArray.get(1).getAsString();
					    	if (topString.equals("Top") &&
					    			companiesString.equals("Companies")) 
					    		companyValue = jsonArray.get(2).getAsString();
					    }
				    }
					}
				  localPreparedStatementInsert.setString(parmNumberCompany, companyValue);
				}
				if (hasReport) {
				  localPreparedStatementInsert.setString(parmNumberReport, row.getReport());
				}				
				int   localRowsAffected = localPreparedStatementInsert.executeUpdate();	
				/* Check the number of rows affected. This value should always be one. */
				if (localRowsAffected != 1) {
					LOG.info("Insert rows affected (" + ((Integer) localRowsAffected).toString() + ')');
				  String   errorText = "Invalid number of affected rows in insertTableRows";
					HDLmAssertAction(false, errorText);						
				}
				localResultSet = localPreparedStatementLast.executeQuery();		
				while (localResultSet.next()) {    
          int   id = localResultSet.getInt("LAST_INSERT_ID()");	
          rowIdList.add(id);
        }
				/* Try to release resources as need be */
				localResultSet.close();
			}
			/* Try to release resources as need be */
			localPreparedStatementInsert.close();
			localPreparedStatementLast.close();
	  }		
	  catch (SQLException sqlException) { 
		  if (sqlStr != null)
		    LOG.info("SQL string - " + sqlStr);
		  LOG.info("SQLException while executing insertTableRows");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");
			return null;
		}
		/* Release the used connection */
		HDLmHikariPool.releaseConnectionHikari(localConnection);
		/* Show that the database has been updated */
		HDLmDatabase.setRulesHaveBeenUpdated(true);
		/* Free the database lock. This lock is freed when the current 
       operation is complete. This step prevents database reads from 
       happening until the lock is freed. */ 		
    HDLmDatabase.releaseDatabaseLock();	
		return rowIdList;
	}
	/* Insert a set of rows in the rules (modifications) table using the 
	   JSON string passed by the caller. The JSON string passed to this
	   routine must have all of the information about each row that is 
	   to be inserted. */
	protected static String  insertTableRowsJson(final String jsonStr,
			                                         final String tableName) {
		/* Check if the JSON string value passed by the caller is null */
		if (jsonStr == null) {
			String  errorText = "Json string value passed to insertTableRowsJson is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to insertTableRowsJson is null";
			throw new NullPointerException(errorText);
		}
		/* Declare/define a few variables */
		ArrayList<HDLmDatabaseRow>  localRowList = null;  
		ArrayList<HDLmDatabaseId>   localRowIdObjectList = null;
		ArrayList<Integer>          localRowIdIntegerList = null;
		String                      outJson = null;
		/* Allocate the row list for use below */
		localRowList = new ArrayList<HDLmDatabaseRow>();
		if (localRowList == null) {
    	String   errorText = "Local row list is null in insertTableRowsJson";
			HDLmAssertAction(false, errorText);		    	
    }
		/* Allocate the row database ID list for use below */
		localRowIdObjectList = new ArrayList<HDLmDatabaseId>();
		if (localRowIdObjectList == null) {
    	String   errorText = "Local row database ID list is null in insertTableRowsJson";
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
		  topNodeJsonElement = parser.parse(jsonStr);
	  } 
	  catch (Exception exception) {		 
    	if (jsonStr != null)
    		LOG.info("JSON string - " + jsonStr);
		  LOG.info("Exception while executing insertTableRowsJson");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
		  return null;
	  }		
	  /* Check if the JSON message passed by the caller is valid */
  	if (!topNodeJsonElement.isJsonObject()) {
	  	String  errorText = "JSON string passed to insertTableRowsJson is invalid";
	  	HDLmAssertAction(false, errorText);
	  }
		/* Get all of the inserts and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in inbound data is not large enough";
			HDLmAssertAction(false, errorText);	
		}
    /* Process each element in the JSON array. A row is built from 
       each element in the JSON array. */ 
		boolean             hasCompany = false;
		boolean             hasContent = false;
		boolean             hasId = false;
		boolean             hasInfo = false;
		boolean             hasName = false;
		boolean             hasReport = false;
		HDLmDatabaseReply   databaseReply;
		JsonElement         infoElement = null;
		JsonObject          infoObj = null;
		Set<String>         jsonKeys = null;
		String              jsonString;
		String              company = null;
		String              content = null;
		String              idString = null;
		String              info = null;
		String              name = null;
		String              report = null;
		for (int i = 0; i < jsonArraySize; i++) {
			/* Create a new (empty) database row object */
			HDLmDatabaseRow   databaseRow = new HDLmDatabaseRow();
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	  	if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in insertTableRowsJson is invalid";
		  	HDLmAssertAction(false, errorText);
		  }
			/* Check for the first JSON element. We build the 
			   dictionary of what columns will be inserted, but
			   only from the first JSON element. */
			if (i == 0) {
				JsonObject  jsonArrayObject = jsonArrayElement.getAsJsonObject();
				jsonKeys = jsonArrayObject.keySet();	
				hasCompany = jsonKeys.contains("company");
				hasContent = jsonKeys.contains("content");
				hasId = jsonKeys.contains("id");
				hasInfo = jsonKeys.contains("info");
				hasName = jsonKeys.contains("name");
				hasReport = jsonKeys.contains("report");
			}
			/* Get each of the values if they are present */
			if (hasCompany) {
				company = HDLmJson.getJsonString(jsonArrayElement, "company");
				databaseRow.setCompany(company);
			}	
			if (hasContent) {
				content = HDLmJson.getJsonString(jsonArrayElement, "content");
				databaseRow.setContent(content);
			}
			if (hasId) { 
				idString = HDLmJson.getJsonString(jsonArrayElement, "id");
				databaseRow.setId(idString);
			}	
			if (hasInfo) {
				infoObj = HDLmJson.getJsonObject(jsonArrayElement, "info");
				infoElement = (JsonElement) infoObj;
				info = HDLmJson.getStringJson(infoElement);
				databaseRow.setInfo(info);
			}
			if (hasName) {
				name = HDLmJson.getJsonString(jsonArrayElement, "name");
				databaseRow.setName(name);
			}
			if (hasReport) {
				report = HDLmJson.getJsonString(jsonArrayElement, "report");
				databaseRow.setReport(report);
			}	
			/* Add the new row to the list of rows */
			localRowList.add(databaseRow);
		} 
		/* Insert all of the rows */
		localRowIdIntegerList = HDLmDatabase.insertTableRows(localRowList, jsonKeys, tableName);	
		for (Integer localRowIdInteger : localRowIdIntegerList) {
			HDLmDatabaseId  databaseId = new HDLmDatabaseId(localRowIdInteger.toString());
			localRowIdObjectList.add(databaseId);
		}
		/* Build a new database reply instance */
		databaseReply = new HDLmDatabaseReply(localRowIdObjectList, 1);
		if (databaseReply == null) {
	 	  String   errorText = "Local reply instance is null in insertTableRowsJson";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Convert the new database rows instance to JSON */
		Gson  gsonInstance = HDLmMain.gsonMain;
		jsonString = gsonInstance.toJson(databaseReply);
		return jsonString;
	}
	/* Insert a set of rows into the rules (modifications) table using a set 
	   of row values passed by the caller. Each of the new rows has an ID
	   value that is returned to the caller. The ID value is generated 
	   automatically by the database. The system name is a value such 
	   as 'prod' or 'test' (without the quotes) that indicates which 
	   system the rows should be inserted into. */
	protected static ArrayList<Integer>  insertTableRowsSystem(final ArrayList<HDLmDatabaseRow> insertList, 
		                                                         final Set<String> rowKeys,
		                                                         final String systemName) {
		/* Check if the row list value passed by the caller is null */
		if (insertList == null) {
			String  errorText = "Row list value string passed to insertTableRowsSystem is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the row keys value passed by the caller is null */
		if (rowKeys == null) {
			String  errorText = "Row keys value string passed to insertTableRowsSystem is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the system name string value passed by the caller is null */
		if (systemName == null) {
			String  errorText = "System name string passed to insertTableRowsSystem is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the system name is invalid */
		if (HDLmTransferSomething.checkSystemValue(systemName) != null) {
			String  errorText = "System name string passed to insertTableRowsSystem is invalid";
			throw new NullPointerException(errorText);
		}
		/* Get the database lock. This lock is not freed until the
       table rows are inserted. This step prevents database 
       reads from happening until the lock is freed. */ 		
    HDLmDatabase.getDatabaseLock();	
		/* Declare/define a few variables */
		ArrayList<Integer>  rowIdList = null;
		boolean             buildCompany = false;
		boolean             hasCompany = false;
		boolean             hasContent = false;
		boolean             hasId = false;
		boolean             hasInfo = false;
		boolean             hasName = false;
		boolean             hasReport = false;
		boolean             localCheckTest = false;
		Connection          localConnection = null;
		int                 i;
		int                 localColCount = 0;
		int                 parmNumberCompany = 0;
		int                 parmNumberContent = 0;
		int                 parmNumberInfo = 0;
		int                 parmNumberName = 0;
		int                 parmNumberReport = 0;
		Integer             localId;
		PreparedStatement   localPreparedStatementInsert = null;
		PreparedStatement   localPreparedStatementLast = null;
		ResultSet           localResultSet = null; 
		StringBuilder       sqlBuilder = new StringBuilder();
		String              sqlStr = "";
		/* Try to get the name of the test system. This should never fail.
       However, you never know. */ 		
		String   testSystemName = HDLmDefines.getString("HDLMSYSTEMTEST");
		if (testSystemName == null) {
			String   errorFormat = "Define value for test system name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSYSTEMTEST");
			HDLmAssertAction(false, errorText);		    	
		}
		/* Check if we should insert rows into the test system or the production
		   system. A local variable is set to indicate this. */ 
		if (systemName.equals(testSystemName)) 
			localCheckTest = true;
		String  database = null; 
		String  domainName = null; 
		String  tableName = null;
		/* Check for the production system versus the test system */
		if (localCheckTest == false) {
		  database = HDLmConfigInfo.getEntriesDatabaseDatabaseNameProd();
		  domainName = HDLmConfigInfo.getEntriesDatabaseDomainNameProd();
		  tableName = HDLmConfigInfo.getEntriesDatabaseTableNameProd();
		}
		else {
		  database = HDLmConfigInfo.getEntriesDatabaseDatabaseNameTest();
		  domainName = HDLmConfigInfo.getEntriesDatabaseDomainNameTest();
		  tableName = HDLmConfigInfo.getEntriesDatabaseTableNameTest();
		}
		/* Try to create the array list of row IDs that is returned to
		   the caller */
		rowIdList = new ArrayList<Integer>();
		if (rowIdList == null) {
		  String   errorText = "Return ID list is null in insertTableRowsSystem";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Try to connect to the database and get a connection object 
	     to use to execute SQL */
	  String  userName = HDLmConfigInfo.getEntriesDatabaseUserid();
    String  passWord = HDLmConfigInfo.getEntriesDatabasePassword();
    String  portNumberStr = ((Integer) HDLmConfigInfo.getEntriesDatabasePortNumber()).toString();
    String  urlStr = "jdbc:mysql://" + domainName + ":" + portNumberStr +
                     "/" + database;
    try {
 	    localConnection = DriverManager.getConnection(urlStr, userName, passWord);
    } 
    catch (SQLException sqlException) {
	    if (urlStr != null)
	      LOG.info("URL string - " + urlStr);
	    LOG.info("SQLException while executing insertTableRowsSystem");
	    LOG.info(sqlException.getMessage(), sqlException);
	    HDLmEvent.eventOccurred("SQLException");	
	    return null;
    }  
    /* Check if a connection was obtained */  
		if (localConnection == null) {
		  String   errorText = "Returned connection is null in insertTableRowsSystem";
			HDLmAssertAction(false, errorText);		    	
	  }		 
		/* Prepare the SELECT query that will provide the ID of each 
		   inserted row */
		sqlStr = "SELECT LAST_INSERT_ID()";
		localPreparedStatementLast = HDLmDatabase.prepareStatement(localConnection, sqlStr);
		/* Check which column we actually have */
		hasCompany = rowKeys.contains("company");
		hasContent = rowKeys.contains("content");
		hasId = rowKeys.contains("id");
		hasInfo = rowKeys.contains("info");
		hasName = rowKeys.contains("name");
		hasReport = rowKeys.contains("report");
		/* Report a fatal error if the ID column was passed */
		if (hasId) {
			String   errorText = "Row ID value provided in insertTableRowsSystem";
			HDLmAssertAction(false, errorText);		
		}
		/* Check if the company name must be built from the 
		   node path */
		if (hasCompany == false)
			buildCompany = true;
		/* Build the INSERT that is executed below */
		sqlBuilder.append("INSERT INTO "); 
		sqlBuilder.append(database);
		sqlBuilder.append('.');
		sqlBuilder.append(tableName);
		/* Handle all of the columns that are actually passed by the caller */
		for (String rowKey : rowKeys) {	
			/* Handle the current key */
			localColCount += 1;
			if (localColCount > 1)
				sqlBuilder.append(", ");
			else
				sqlBuilder.append(" (");
			/* Set the parameter numbers */
			if (rowKey.equals("info"))
				parmNumberInfo = localColCount;
			if (rowKey.equals("content"))
				parmNumberContent = localColCount;
			if (rowKey.equals("name"))
				parmNumberName = localColCount;
			if (rowKey.equals("company"))
				parmNumberCompany = localColCount;
			if (rowKey.equals("report"))
				parmNumberReport = localColCount;
			/* Continue building the INSERT statement */			 
			sqlBuilder.append(rowKey);
		}
		/* At this point we may need to add the company column */
		if (buildCompany == true) {
			localColCount += 1;
			if (localColCount > 1)
				sqlBuilder.append(", ");
			else
				sqlBuilder.append(" (");
			sqlBuilder.append("company");
			parmNumberCompany = localColCount;			
		}
		/* Finish the insert SQL statement */
		sqlBuilder.append(") VALUES(");
		for (i = 0; i < localColCount; i++) {
			if (i > 0)
				sqlBuilder.append(", ");
			sqlBuilder.append('?');
		}
		sqlBuilder.append(')');
		/* Get the final SQL string */
		sqlStr = sqlBuilder.toString();
		/* Execute the SQL */
		localPreparedStatementInsert = HDLmDatabase.prepareStatement(localConnection, sqlStr);
		if (localPreparedStatementInsert == null) {
		  String   errorText = "Returned prepared statement is null in insertTableRowsSystem";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Process (insert) all of the rows passed by the caller */
		try {
			for (HDLmDatabaseRow row : insertList) {
				/* Set a few values */
				if (hasInfo) {
				  localPreparedStatementInsert.setString(parmNumberInfo, row.getInfo());
				}
				if (hasContent) {
				  localPreparedStatementInsert.setString(parmNumberContent, row.getContent());
				}
				if (hasName) {
				  localPreparedStatementInsert.setString(parmNumberName, row.getName());
				}
				if (hasCompany || 
						buildCompany == true) {
					String  companyValue = null;
					if (hasCompany)
						companyValue = row.getCompany();
					else if (buildCompany == true) {
						String  infoStr = row.getInfo();
						/* Try to convert the JSON string to a JSON object. If this fails, 
					     then we do not have a string than can be converted to a JSON 
					     object. If this works, then we do have string than can be 
					     converted to a JSON object. */
				    JsonParser    parser = new JsonParser();		
				    JsonElement   jsonElement = null; 
				    try {
					    jsonElement = parser.parse(infoStr);
				    } 
				    catch (Exception exception) {	
				    	if (infoStr != null)
				    		LOG.info("Info string - " + infoStr);
					    LOG.info("Exception while executing insertTableRowsSystem");
					    LOG.info(exception.getMessage(), exception);
					    HDLmEvent.eventOccurred("Exception");
					    return null;
				    }		
					  /* Check if the JSON message passed by the caller is valid */
				  	if (!jsonElement.isJsonObject()) {
					  	String  errorText = "JSON info string used in insertTableRowsSystem is invalid";
					  	HDLmAssertAction(false, errorText);
					  }	
				  	/* Get the node path from the info string */
				  	JsonArray   jsonArray = HDLmJson.getJsonArray(jsonElement, "nodePath");
				    int         jsonArraySize = jsonArray.size();
				    /* Get the content value and check if we are inserting a proxy entry
				       or not. For proxy entries, the company name is actually the second
				       entry in the node path. */
				    String  content = null;
				    if (hasContent)
				    	content = row.getContent();
				    /* Check if we are inserting a proxy entry */
				    if (content != null && 
				    		content.startsWith("proxy")) {
					    if (jsonArraySize >= 2) {
					    	String  topString = jsonArray.get(0).getAsString(); 
					    	if (topString.equals("Top")) 
					    		companyValue = jsonArray.get(1).getAsString();
					    }				    	
				    }
				    /* Check if we are inserting something other than a proxy entry */
				    if (content != null && 
				    		!content.startsWith("proxy")) {				    
					    if (jsonArraySize >= 3) {
					    	String  topString = jsonArray.get(0).getAsString();
					    	String  companiesString = jsonArray.get(1).getAsString();
					    	if (topString.equals("Top") &&
					    			companiesString.equals("Companies")) 
					    		companyValue = jsonArray.get(2).getAsString();
					    }
				    }
					}
				  localPreparedStatementInsert.setString(parmNumberCompany, companyValue);
				}
				if (hasReport) {
				  localPreparedStatementInsert.setString(parmNumberReport, row.getReport());
				}				
				int   localRowsAffected = localPreparedStatementInsert.executeUpdate();	
				/* Check the number of rows affected. This value should always be one. */
				if (localRowsAffected != 1) {
					LOG.info("Insert rows affected (" + ((Integer) localRowsAffected).toString() + ')');
				  String   errorText = "Invalid number of affected rows in insertTableRowsSystem";
					HDLmAssertAction(false, errorText);						
				}
				localResultSet = localPreparedStatementLast.executeQuery();		
				while (localResultSet.next()) {    
	        int   id = localResultSet.getInt("LAST_INSERT_ID()");	
	        rowIdList.add(id);
	      }
				/* Try to release resources as need be */
				localResultSet.close();
			}
	    /* Try to release the prepared insert statement */
	    if (localPreparedStatementInsert != null &&
	        !localPreparedStatementInsert.isClosed()) {
	  	  localPreparedStatementInsert.close();
		  }
	    /* Try to release the prepared last statement */
	    if (localPreparedStatementLast != null &&
	        !localPreparedStatementLast.isClosed()) {
	  	  localPreparedStatementLast.close();
		  }
		  /* Release the used connection */
	    if (localConnection != null &&
		      !localConnection.isClosed()) {
		    localConnection.close();
		  }			
	  }		
	  catch (SQLException sqlException) { 
		  if (sqlStr != null)
		    LOG.info("SQL string - " + sqlStr);
		  LOG.info("SQLException while executing insertTableRowsSystem");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");
			return null;
		}
		/* Show that the database has been updated */
		HDLmDatabase.setRulesHaveBeenUpdated(true);
		/* Free the database lock. This lock is freed when the current 
       operation is complete. This step prevents database reads from 
       happening until the lock is freed. */ 		
    HDLmDatabase.releaseDatabaseLock();	
		return rowIdList;
	}
	/* The next method prepares a SQL statement passed by the caller.
	   The caller also provides a JDBC statement that can be used to 
	   prepare the SQL. A prepared statement is returned to the caller. */	  
	protected static PreparedStatement  prepareStatement(final Connection passedConnection, 
			                                                 final String passedSql) {  	
		/* Check one or more values passed by the caller */
		if (passedConnection == null) {
			String   errorText = "Connection reference passed to prepareStatement is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the SQL passed by the caller */
		if (passedSql == null) {
			String   errorText = "SQL string reference passed to prepareStatement is null";
			throw new NullPointerException(errorText);			
		}
		/* Declare/define a few variables */
		PreparedStatement   localPreparedStatement = null;
		try {
			localPreparedStatement = passedConnection.prepareStatement(passedSql);
		} 
		catch (SQLException sqlException) {
			if (passedSql != null)
			  LOG.info("Passed SQL - " + passedSql);
			LOG.info("SQLException while executing prepareStatement");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			localPreparedStatement = null;
			return null;
		}
	  return localPreparedStatement;
	}
  /* The next method returns a connection after it is used. Eventually, 
	   the connection will be placed back in a connection pool. For now, 
	   we just save the connection for use later. */	  
	protected static void  releaseConnection(final Connection passedConnection) {  
		/* Check one or more values passed by the caller */
		if (passedConnection == null) {
			String   errorText = "Connection reference passed to releaseConnection is null";
			throw new NullPointerException(errorText);			
		}
		/* Release the connection passed by the caller */
		HDLmHikariPool.releaseConnectionHikari(passedConnection);
	}	
	/* Release the database lock. The lock may still be held, if the 
	   lock count is more than one, on entry. This is not an error. If 
	   the lock count is more than one, then the lock count is decremented.
	   Actually, the lock count is decremented in all cases. The 'after' 
	   lock hold count is returned to the caller. */ 
	protected static int  releaseDatabaseLock() {
		databaseLock.unlock();
		return databaseLock.getHoldCount();
	}
	/* Set the flag showing if the rules need to be reloaded. The new 
	   value is passed by the caller. */ 
	protected static void  setRulesHaveBeenUpdated(final boolean newUpdateValue) {
		HDLmDatabase.rulesHaveBeenUpdated = newUpdateValue;
		return;
	}
	/* Update a set of rows in the rules (modifications) table using a set 
	   of row values passed by the caller. */
	protected static void  updateTableRows(final ArrayList<HDLmDatabaseRow> rowList, 
			                                   final Set<String> rowKeys,
			                                   final String tableName) {
		/* Check if the row list value passed by the caller is null */
		if (rowList == null) {
			String  errorText = "Row list value string passed to updateTableRows is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the row keys value passed by the caller is null */
		if (rowKeys == null) {
			String  errorText = "Row keys value string passed to updateTableRows is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to updateTableRows is null";
			throw new NullPointerException(errorText);
		}
		/* Get the database lock. This lock is not freed until the
       table rows are updated. This step prevents database 
       reads from happening until the lock is freed. */ 		
    HDLmDatabase.getDatabaseLock();	
		/* Declare/define a few variables */
		boolean             hasCompany = false;
		boolean             hasContent = false;
		boolean             hasId = false;
		boolean             hasInfo = false;
		boolean             hasName = false;
		boolean             hasReport = false;
		Connection          localConnection = null;
		int                 localColCount = 0;
		int                 parmNumberCompany = 0;
		int                 parmNumberContent = 0;
		int                 parmNumberId = 0;
		int                 parmNumberInfo = 0;
		int                 parmNumberName = 0;
		int                 parmNumberReport = 0;
		Integer             localId;
		PreparedStatement   localPreparedStatement;
		StringBuilder       sqlBuilder = new StringBuilder();
		String              sqlStr = "";
		String              database = HDLmConfigInfo.getEntriesDatabaseDatabaseName(); 
		String              domainName = HDLmConfigInfo.getEntriesDatabaseDomainName();
		/* Get a statement that can be used to execute SQL */
		localConnection = HDLmHikariPool.getConnectionHikari();
		if (localConnection == null) {
		  String   errorText = "Returned connection is null in updateTableRows";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Check which column we actually have */
		hasCompany = rowKeys.contains("company");
		hasContent = rowKeys.contains("content");
		hasId = rowKeys.contains("id");
		hasInfo = rowKeys.contains("info");
		hasName = rowKeys.contains("name");
		hasReport = rowKeys.contains("report");
		/* Build the UPDATE that is executed below */
		sqlBuilder.append("UPDATE "); 
		sqlBuilder.append(database);
		sqlBuilder.append('.');
		sqlBuilder.append(tableName);
		/* Handle all of the columns that are actually passed by the caller */
		for (String rowKey : rowKeys) {
			/* Skip the ID key in all cases */
			if (rowKey.equals("id"))
				continue;
			/* Handle the current key */
			localColCount += 1;
			if (localColCount > 1)
				sqlBuilder.append(    ", ");
			/* Set the parameter numbers */
			if (rowKey.equals("info"))
				parmNumberInfo = localColCount;
			if (rowKey.equals("content"))
				parmNumberContent = localColCount;
			if (rowKey.equals("name"))
				parmNumberName = localColCount;
			if (rowKey.equals("company"))
				parmNumberCompany = localColCount;
			if (rowKey.equals("report"))
				parmNumberReport = localColCount;
			/* Continue building the UPDATE statement */
			if (localColCount <= 1)
			  sqlBuilder.append(" SET ");
			sqlBuilder.append(rowKey);
			sqlBuilder.append("=?");
		}
		sqlBuilder.append(" WHERE id=?");
		parmNumberId = localColCount + 1;
		/* Get the final SQL string */
		sqlStr = sqlBuilder.toString();
		/* Execute the SQL */
		localPreparedStatement = HDLmDatabase.prepareStatement(localConnection, sqlStr);
		if (localPreparedStatement == null) {
		String   errorText = "Returned prepared statement is null in updateTableRows";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Process all of the rows passed by the caller */
		try {
			for (HDLmDatabaseRow row : rowList) {
				/* Set a few values */
				if (hasInfo) {
				  localPreparedStatement.setString(parmNumberInfo, row.getInfo());
				}
				if (hasContent) {
				  localPreparedStatement.setString(parmNumberContent, row.getContent());
				}
				if (hasName) {
				  localPreparedStatement.setString(parmNumberName, row.getName());
				}
				if (hasCompany) {
				  localPreparedStatement.setString(parmNumberCompany, row.getCompany());
				}
				if (hasReport) {
				  localPreparedStatement.setString(parmNumberReport, row.getReport());
				}
				/* Get the row ID and convert it into an integer */
				if (hasId) {
				  localId = HDLmUtility.convertInteger(row.getId());
				  if (localId == null)
				  	break;
				  localPreparedStatement.setInt(parmNumberId, localId);
				}
				int   localRowsAffected = localPreparedStatement.executeUpdate();				
				/* Check the number of rows affected. This value should always be one. */
				if (localRowsAffected != 1) {
					LOG.info("Update rows affected (" + ((Integer) localRowsAffected).toString() + ')');
				  String   errorText = "Invalid number of affected rows in updateTableRows";
					HDLmAssertAction(false, errorText);						
				}
			}
			/* Try to release resources as need be */
			localPreparedStatement.close();
	  }		
	  catch (SQLException sqlException) { 
		  if (sqlStr != null)
			  LOG.info("SQL string - " + sqlStr);
		  LOG.info("SQLException while executing updateTableRows");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			return;
		}
		/* Release the used connection */
		HDLmHikariPool.releaseConnectionHikari(localConnection);
		/* Show that the database has been updated */
		HDLmDatabase.setRulesHaveBeenUpdated(true);
		/* Free the database lock. This lock is freed when the current 
       operation is complete. This step prevents database reads from 
       happening until the lock is freed. */ 		
    HDLmDatabase.releaseDatabaseLock();	
		return;
	}
	/* Update a set of rows in the rules (modifications) table using the 
	   JSON string passed by the caller. The JSON string passed to this
	   routine must have all of the information about each row that is 
	   to be updated. */
	protected static String  updateTableRowsJson(final String jsonStr,
			                                         final String tableName) {
		/* Check if the json string value passed by the caller is null */
		if (jsonStr == null) {
			String  errorText = "Json string value passed to updateTableRowsJson is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the table name string value passed by the caller is null */
		if (tableName == null) {
			String  errorText = "Table name string passed to updateTableRowsJson is null";
			throw new NullPointerException(errorText);
		}
		/* Declare/define a few variables */
		ArrayList<HDLmDatabaseRow>  localRowList = null;
		ArrayList<HDLmDatabaseId>   localRowIdList = null;
		String                      jsonString;
		/* Allocate the row list that is returned to the caller */
		localRowList = new ArrayList<HDLmDatabaseRow>();
		if (localRowList == null) {
    	String   errorText = "Local row list is null in updateTableRowsJson";
			HDLmAssertAction(false, errorText);		    	
    }
		/* Allocate the row ID list for use below */
		localRowIdList = new ArrayList<HDLmDatabaseId>();
		if (localRowIdList == null) {
	 	  String   errorText = "Local row ID list is null in updateTableRowsJson";
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
		  topNodeJsonElement = parser.parse(jsonStr);
	  } 
	  catch (Exception exception) {	
    	if (jsonStr != null)
    		LOG.info("JSON string - " + jsonStr);
		  LOG.info("Exception while executing updateTableRowsJson");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
		  return null;
	  }		
	  /* Check if the JSON message passed by the caller is valid */
  	if (!topNodeJsonElement.isJsonObject()) {
	  	String  errorText = "JSON string passed to updateTableRowsJson is invalid";
	  	HDLmAssertAction(false, errorText);
	  }
		/* Get all of the updates and make sure we have at least one */
		JsonArray   jsonArray = HDLmJson.getJsonArray(topNodeJsonElement, "data");
		int         jsonArraySize = jsonArray.size();
		if (jsonArraySize <= 0) {
			String  errorText = "Data array in inbound data is not large enough";
			HDLmAssertAction(false, errorText);	
		}
    /* Process each element in the JSON array. A row is built from 
       each element in the JSON array. */ 
		boolean             hasCompany = false;
		boolean             hasContent = false;
		boolean             hasId = false;
		boolean             hasInfo = false;
		boolean             hasName = false;
		boolean             hasReport = false;
		HDLmDatabaseReply   databaseReply;
		JsonElement         infoElement = null;
		JsonObject          infoObj = null;
		Set<String>         jsonKeys = null;
		String              company = null;
		String              content = null;
		String              idString = null;
		String              info = null;
		String              name = null;
		String              report = null;
		for (int i = 0; i < jsonArraySize; i++) {
			/* Create a new (empty) database row object */
			HDLmDatabaseRow   databaseRow = new HDLmDatabaseRow();
			/* Get the current JSON element */
			JsonElement   jsonArrayElement = jsonArray.get(i);
		  /* Check if the JSON element is valid */
	  	if (!jsonArrayElement.isJsonObject()) {
		  	String  errorText = "JSON array element in updateTableRowsJson is invalid";
		  	HDLmAssertAction(false, errorText);
		  }
			/* Check for the first JSON element. We build the 
			   dictionary of what columns will be updated, but
			   only from the first JSON element. */
			if (i == 0) {
				JsonObject  jsonArrayObject = jsonArrayElement.getAsJsonObject();
				jsonKeys = jsonArrayObject.keySet();	
				hasCompany = jsonKeys.contains("company");
				hasContent = jsonKeys.contains("content");
				hasId = jsonKeys.contains("id");
				hasInfo = jsonKeys.contains("info");
				hasName = jsonKeys.contains("name");
				hasReport = jsonKeys.contains("report");
			}
			/* Get each of the values if they are present */
			if (hasCompany) {
				company = HDLmJson.getJsonString(jsonArrayElement, "company");
				databaseRow.setCompany(company);
			}	
			if (hasContent) {
				content = HDLmJson.getJsonString(jsonArrayElement, "content");
				databaseRow.setContent(content);
			}
			if (hasId) { 
				idString = HDLmJson.getJsonString(jsonArrayElement, "id");
				databaseRow.setId(idString); 
				HDLmDatabaseId  databaseId = new HDLmDatabaseId(idString);
				if (databaseId == null) {
			 	  String   errorText = "Local row ID instance is null in updateTableRowsJson";
					HDLmAssertAction(false, errorText);		    	
			  }
				localRowIdList.add(databaseId);
			}	
			if (hasInfo) {
				infoObj = HDLmJson.getJsonObject(jsonArrayElement, "info");
				infoElement = (JsonElement) infoObj;
				info = HDLmJson.getStringJson(infoElement);
				databaseRow.setInfo(info);
			}
			if (hasName) {
				name = HDLmJson.getJsonString(jsonArrayElement, "name");
				databaseRow.setName(name);
			}
			if (hasReport) {
				report = HDLmJson.getJsonString(jsonArrayElement, "report");
				databaseRow.setReport(report);
			}	
			/* Add the new row to the list of rows */
			localRowList.add(databaseRow);
		} 
		/* Update all or some of the rows */
		HDLmDatabase.updateTableRows(localRowList, jsonKeys, tableName);		
		/* Build a new database reply instance */
		databaseReply = new HDLmDatabaseReply(localRowIdList, 1);
		if (databaseReply == null) {
	 	  String   errorText = "Local reply instance is null in updateTableRowsJson";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Convert the new database rows instance to JSON */
		Gson     gsonInstance = HDLmMain.gsonMain;
		jsonString = gsonInstance.toJson(databaseReply);
		return jsonString;
	}
}
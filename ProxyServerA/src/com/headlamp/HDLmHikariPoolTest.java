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

import javax.sql.DataSource;

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
 * HDLmHikariPoolTest short summary.
 *
 * HDLmHikariPoolTest description.
 *
 * @version 1.0
 * @author Peter
 */ 
class HDLmHikariPoolTest {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmHikariPoolTest.class); 
  @BeforeAll
	static void HDLmHikariPoolBeforeAll() throws Exception {
  	/* Start the Jetty server, if it has not already been started */
  	if (HDLmMain.checkMainExecuted() == false) {
      String[]  emptyArgument = {};  	 
			HDLmMain.main(emptyArgument);
	  }
	}
	@Test
	void getConnection() {		
		/* Run a few getConnection tests */
    /* Declare and define a few variables */
		Connection  localConnection;
		/* Get a connection */
		localConnection = HDLmHikariPool.getConnectionHikari();
		assertNotNull(localConnection, "Null response recieved from get connection routine");
	}
	@Test
	void getDataSource() {		
		/* Run a few getDataSource tests */
    /* Declare and define a few variables */
		DataSource  localDataSource;
		/* Get a data source object */
		localDataSource = HDLmHikariPool.getDataSource();
		assertNotNull(localDataSource, "Null response recieved from get data source routine");
	}
	@Test
	void releaseConnection() {		
		/* Run a few (actually one) releaseConnection tests */  
		Connection  localConnection; 
		/* Run the release connection routine */ 		
		localConnection = HDLmHikariPool.getConnectionHikari();
		HDLmHikariPool.releaseConnectionHikari(localConnection);			
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmHikariPool.releaseConnectionHikari(null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Connection reference passed to releaseConnectionHikari is null", execMsg,
					         "Unexpected exception message");
		}			
	}
}
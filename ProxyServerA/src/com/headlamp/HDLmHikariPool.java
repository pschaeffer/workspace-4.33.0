package com.headlamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/**
 * HDLmHikariPool summary.
 *
 * HDLmHikariPool description. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmHikariPool {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmHikariPool.class);  
  /* Define a Harkari data source that can be used as need be.
     the data source is initially null, but is set to a non-null
     value as part of startup. */
  private static DataSource   dataSource = null;
	/* This class can never be instantiated */
	private HDLmHikariPool() {}	
	/* This routine gets a connection from the Hikari connection pool.
	   This is the preferred routine for getting a connnection. */ 
	public static Connection getConnectionHikari() {
		Connection  localConnection = null;
		DataSource  dataSource = null;
		/* Get the Hikari data source that will provide the connection */
		dataSource = HDLmHikariPool.getDataSource();
		try {
			localConnection = dataSource.getConnection();
			if (localConnection == null) { ;
				String  errorText = "Connection not obtained from a data source";
				HDLmAssertAction(false, errorText);		    	
	    }
		} 
		catch (SQLException sqlException) {
		  LOG.info("SQLException while executing HDLmHikariPool.getConnectionHiakri");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			localConnection = null;
			return null;
		}
		return localConnection;		
	}	 
	/* Build the data source that will be used to get and release 
	   JDBC connections from the connection pool */
  public static DataSource getDataSource() {
		/* Declare/define a few variables */  
		int             portNumber = HDLmConfigInfo.getEntriesDatabasePortNumber();
		StringBuilder   connectBuilder = new StringBuilder();
		String          connectStr = "";
		String          database = HDLmConfigInfo.getEntriesDatabaseDatabaseName();
		String          domainName = HDLmConfigInfo.getEntriesDatabaseDomainName();
		String          passwordStr = HDLmConfigInfo.getEntriesDatabasePassword();
		String          useridStr = HDLmConfigInfo.getEntriesDatabaseUserid();
  	/* Check if the data source has already been created. Create
  	   the data source if need be. */ 
    if (HDLmHikariPool.dataSource == null) {
    	/* Create a Hakari configuration instance for use below */
      HikariConfig  config = new HikariConfig();
			if (config == null) { ;
			  String  errorText = "Hikari config not obtained using new";
			  HDLmAssertAction(false, errorText);		    	
      }
  		/* Build the connection string */
  		connectBuilder.append("jdbc:mysql://");
  		connectBuilder.append(domainName);
  		connectBuilder.append(':');
  		connectBuilder.append(((Integer) portNumber).toString());
  		connectBuilder.append('/');
  		connectBuilder.append(database);
  		connectStr = connectBuilder.toString();
      /* Fill in the configuration instance */
      config.setJdbcUrl(connectStr);
      config.setUsername(useridStr);
      config.setPassword(passwordStr); 
      config.setMaximumPoolSize(HDLmConfigInfo.getJdbcPoolConnectionCount()); 
      config.addDataSourceProperty("cachePrepStmts", "false");
      /* Create the Hakari data source */
      HDLmHikariPool.dataSource = new HikariDataSource(config);
			if (HDLmHikariPool.dataSource == null) { ;
		    String  errorText = "Hikari data source not obtained using new";
		    HDLmAssertAction(false, errorText);		    	
      }
    }
    /* Return the Hakari data source to the caller */
    return HDLmHikariPool.dataSource;
  }
  /* This routine releases a connection obtained from the Hikari 
     connection pool. This is the preferred routine for releasing
     a connection. */ 
  public static void  releaseConnectionHikari(Connection passedConnection) {
		/* Check one or more values passed by the caller */
		if (passedConnection == null) {
			String   errorText = "Connection reference passed to releaseConnectionHikari is null";
			throw new NullPointerException(errorText);			
		}
  	/* Give the connection back to the pool */
		try {
			passedConnection.close();
		} 
		catch (SQLException sqlException) {
			LOG.info("SQLException while executing HDLmHikariPool.releaseConnectionHikari");
			LOG.info(sqlException.getMessage(), sqlException);
			HDLmEvent.eventOccurred("SQLException");	
			return;
		}
  }
}
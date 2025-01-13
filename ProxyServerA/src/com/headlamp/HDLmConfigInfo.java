package com.headlamp;
/**
 * HDLmConfigInfo short summary.
 *
 * HDLmConfigInfo description.
 *
 * @version 1.0
 * @author Peter
 */
/* No instances of this class are ever created. The configuration
   values are obtained from the configuration JSON. */
public class HDLmConfigInfo {
	/* This class can never be instantiated */
	private HDLmConfigInfo() {}
  /* Get the AWS access key Id value. The access key 
     Id value is returned to the caller. */
  protected static String  getAccessKeyId() {
    return HDLmConfig.getString("AWSAccessKeyId");
  }
  /* Get the AWS Cognito accept encoding value. The 
	   accept encoding value is returned to the caller. */
	protected static String  getAwsCognitoAcceptEncoding() {
	  return HDLmConfig.getString("AWSCognitoAcceptEncoding");
	}
	/* Get the AWS Cognito admin get user attributes API name. 
     This is actually a string. The string is returned to 
     the caller. */
  protected static String  getAwsCognitoApiAdminGetUser() {
    return HDLmConfig.getString("AWSCognitoApiAdminGetUser");
  }
	/* Get the AWS Cognito admin set user password API name. 
     This is actually a string. The string is returned to 
     the caller. */
  protected static String  getAwsCognitoApiAdminSetUserPassword() {
    return HDLmConfig.getString("AWSCognitoApiAdminSetUserPassword");
  }
	/* Get the AWS Cognito get user attributes API name. 
	   This is actually a string. The string is returned 
	   to the caller. */
	protected static String  getAwsCognitoApiGetUser() {
	  return HDLmConfig.getString("AWSCognitoApiGetUser");
	}
	/* Get the AWS Cognito initiate authentication API name. 
	   This is actually a string. The string is returned to 
	   the caller. */
	protected static String  getAwsCognitoApiInitiateAuth() {
	  return HDLmConfig.getString("AWSCognitoApiInitiateAuth");
	}
	/* Get the AWS Cognito respond to challenge API name. 
	   This is actually a string. The string is returned to 
	   the caller. */
	protected static String  getAwsCognitoApiRespondToAuthChallenge() {
	  return HDLmConfig.getString("AWSCognitoApiRespondToAuthChallenge");
	}
	/* Get the AWS Cognito canonical URI. The URI is 
	   sometimes called the path value. The canonical
	   URI string is returned to the caller. */
  protected static String  getAwsCognitoCanonicalUri() {
    return HDLmConfig.getString("AWSCognitoCanonicalUri");
  }
	/* Get the AWS Cognito content type value. The content 
	   type value is returned to the caller. */
	protected static String  getAwsCognitoContentType() {
	  return HDLmConfig.getString("AWSCognitoContentType");
	}
	/* Get the AWS Cognito host name. This is actually a domain name.
	   The domain name is returned to the caller. */
	protected static String  getAwsCognitoHost() {
	  return HDLmConfig.getString("AWSCognitoHost");
	}
	/* Get the AWS Cognito method. This is actually an HTTP method.
     The method string is returned to the caller. */
  protected static String  getAwsCognitoHttpMethod() {
    return HDLmConfig.getString("AWSCognitoHttpMethod");
  }
	/* Get the AWS Cognito service name. The service name
     string is returned to the caller. */
  protected static String  getAwsCognitoServiceName() {
    return HDLmConfig.getString("AWSCognitoServiceName");
  }	
	/* Get the AWS Cognito user agent value. This is actually 
	   a string. The value is not correct, but does work with 
	   AWS Cognito. The user agent value is returned to the 
	   caller. */ 
	protected static String  getAwsCognitoUserAgent() {
	  return HDLmConfig.getString("AWSCognitoUserAgent");
	}
  /* Get the AWS Cognito user pool app Id. This is actually a value
     that identifies the user pool app. */
  protected static String  getAwsCognitoUserPoolClientAppId() {
    return HDLmConfig.getString("AWSCognitoUserPoolClientAppId");
  }
	/* Get the AWS Cognito user pool Id value. This is actually 
     a string. The user pool Id value is returned to the 
     caller. */ 
  protected static String  getAwsCognitoUserPoolId() {
    return HDLmConfig.getString("AWSCognitoUserPoolId");
  }
	/* Get the AWS Cognito user pool name. This is actually the 
     name of the user pool. */
  protected static String  getAwsCognitoUserPoolName() {
    return HDLmConfig.getString("AWSCognitoUserPoolName");
  } 
	/* Get the AWS Cognito user pool region. This is actually the 
     region where the user pool lives. */
  protected static String  getAwsCognitoUserPoolRegion() {
    return HDLmConfig.getString("AWSCognitoUserPoolRegion");
  } 
	/* Get the AWS secret access key value. The secret access 
     key value is returned to the caller. */
  protected static String  getSecretAccessKey() {
    return HDLmConfig.getString("AWSSecretAccessKey");
  }
	/* Get the standard maximum number of clusters and return
	   it to the caller. The maximum number of clusters is
	   always returned to the caller as a proper number, not
	   a string. */
	protected static int  getClustersMaxCount() {
		return HDLmConfig.getInteger("clustersMaxCount");
	}
  /* Get the standard clusters sample size and return it to 
     the caller. The clusters sample size is always returned 
     to the caller as a proper number, not a string. */
  protected static int  getClustersSampleSize() {
	  return HDLmConfig.getInteger("clustersSampleSize");
  }
  /* Get the clusters threshold and return it to the caller. The 
 	   clusters threshold is the maximum similarity value for all 
 	   members of a cluster. The actual algorithm only checks the
	   first member of a cluster against other possible entries. 
	   So it is possible, the entries in a cluster might differ
	   by more than the threshold value. The clusters threshold
	   value is always returned to the caller as a proper number
	   (a double-precision floating-point number), not a string. */
	public static double  getClustersThreshold() {
	  return HDLmConfig.getDouble("clustersThreshold");
	}
  /* Get the current company name */
  protected static String  getCompanyName() {
    return HDLmConfig.getString("companyName");
  }
	/* Get the maximum age for a cookie. This value is 
	   applied to some cookie that we create. */  
	protected static int   getCookieMaxAge() {
 	  return HDLmConfig.getInteger("cookieMaxAge");
	}
	/* Get the current environment. The current environment will 
	   be set to values such as  "prod" or "production" or "test" 
	   (without the quotes). Other values may be added later. */ 
	protected static String  getCurrentEnvironment() {
    return HDLmConfig.getString("currentEnvironment");
  }
	/* Get the company prefix value that may (or may not) come 
		 before the content value. If the company prefix value 
		 is set, it will always be followed by an underscore that
		 separates the company prefix value from the content value
		 (something like 'mod' or 'proxy'). The company prefix value 
		 should not start or end with an underscore. Other code will 
		 supply the underscore. */
	protected static String  getEntriesBridgeCompanyPrefix() {
    return HDLmConfig.getString("entriesBridgeCompanyPrefix");
  }
	/* Get the suffix value that follows the content value. Note
  	 that an underscore always separates the content value
	   (something like 'mod' or 'proxy') from the suffix value,
	   if the suffix value is non-blank. The suffix value should
	   not start with an underscore. Other code will supply the
	   underscore. */
	protected static String  getEntriesBridgeContentSuffix() {
	  return HDLmConfig.getString("entriesBridgeContentSuffix");
	}
	/* Get the URL that is used to delete from the  
	   table that contains the modifications */
	protected static String  getEntriesBridgeDeleteUrl() {
	  return HDLmConfig.getString("serverName") + HDLmConfig.getString("entriesBridgeDeleteUrl");
	}
	/* Get the method that is used to access the table that contains
	   the modifications */
	protected static String  getEntriesBridgeInternetMethod() {
	  return HDLmConfig.getString("entriesBridgeInternetMethod");
	}
	/* Get the URL that is used to insert into the table that
	   contains the modifications */
	protected static String  getEntriesBridgeInsertUrl() {
	  return HDLmConfig.getString("serverName") + HDLmConfig.getString("entriesBridgeInsertUrl");
	}
	/* Return the configuration password */
	protected static String  getEntriesBridgePassword() {
	  return HDLmConfig.getString("entriesBridgePassword");
	}
	/* Get the URL that is used to access the table that contains
	   the modifications */
	protected static String  getEntriesBridgeReadUrl() {
	  return HDLmConfig.getString("serverName") + HDLmConfig.getString("entriesBridgeReadUrl");
	}
	/* Get the URL that is used to update the table that contains
	   the modifications */
	protected static String  getEntriesBridgeUpdateUrl() {
	  return HDLmConfig.getString("serverName") + HDLmConfig.getString("entriesBridgeUpdateUrl");
	}
  /* Get a caching enum string value showing if modifications 
     or proxy definitions should be cached or not and to what
     extent. Note that this value will be an enum value. 
     This routine does not appear to be in use. */
	protected static HDLmCacheTypes  getEntriesBridgeUseCache() {
	  return HDLmConfig.getEnumCache("entriesBridgeUseCache");
	}
	/* Return the configuration userid */
	protected static String  getEntriesBridgeUserid() {
	  return HDLmConfig.getString("entriesBridgeUserid");
	}
  /* Get the company prefix value that may (or may not) come 
	   before the content value. If the company prefix value 
	   is set, it will always be followed by an underscore that
	   separates the company prefix value from the content value
	   (something like 'mod' or 'proxy'). The company prefix value 
	   should not start or end with an underscore. Other code will 
	   supply the underscore. */
	protected static String  getEntriesDatabaseCompanyPrefix() {
	  return HDLmConfig.getString("entriesDatabaseCompanyPrefix");
	}
	/* Get the suffix value that follows the content value. Note
	   that an underscore always separates the content value
	   (something like 'mod' or 'proxy') from the suffix value,
	   if the suffix value is non-blank. The suffix value should
	   not start with an underscore. Other code will supply the
	   underscore. */
	protected static String  getEntriesDatabaseContentSuffix() {
	  return HDLmConfig.getString("entriesDatabaseContentSuffix");
	}
	/* Get the name of the database that is used to connect to 
	   the system that has the table that contains the modifications */
  protected static String  getEntriesDatabaseDatabaseName() {
  	if (HDLmMain.checkProductionSystem())
      return HDLmConfig.getString("entriesDatabaseDatabaseNameProd");
  	else
  		return HDLmConfig.getString("entriesDatabaseDatabaseNameTest");
  }
	/* Get the name of the database that is used to connect to 
     the production system that has the table that contains 
     the modifications */
	protected static String  getEntriesDatabaseDatabaseNameProd() {		 
	  return HDLmConfig.getString("entriesDatabaseDatabaseNameProd");		
	}
	/* Get the name of the database that is used to connect to 
     the test system that has the table that contains 
     the modifications */
	protected static String  getEntriesDatabaseDatabaseNameTest() {		 
	  return HDLmConfig.getString("entriesDatabaseDatabaseNameTest");		
	}
	/* Get the domain name that is used to connect to the database 
	   that has the table that contains the modifications */
  protected static String  getEntriesDatabaseDomainName() {
  	if (HDLmMain.checkProductionSystem())
      return HDLmConfig.getString("entriesDatabaseDomainNameProd");
  	else
  		return HDLmConfig.getString("entriesDatabaseDomainNameTest");
  }
	/* Get the domain name that is used to connect to the production 
	   database that has the table that contains the modifications */
	protected static String  getEntriesDatabaseDomainNameProd() {		 
	  return HDLmConfig.getString("entriesDatabaseDomainNameProd");		 
	}
	/* Get the domain name that is used to connect to the production 
 	   database that has the table that contains the modifications */
	protected static String  getEntriesDatabaseDomainNameTest() {		 
	  return HDLmConfig.getString("entriesDatabaseDomainNameTest");		 
	}
	/* Get the method that is used to access the table that contains
	   the modifications */
	protected static String  getEntriesDatabaseInternetMethod() {
	  return HDLmConfig.getString("entriesDatabaseInternetMethod");
	}
	/* Return the configuration password */
	protected static String  getEntriesDatabasePassword() {
	  return HDLmConfig.getString("entriesDatabasePassword");
	}
	/* Get the port number that is used to connect to the database 
     that has the table that contains the modifications */
  protected static int   getEntriesDatabasePortNumber() {
    return HDLmConfig.getInteger("entriesDatabasePortNumber");
  }
	/* Get the table name that contains the modifications. This is the
	   table that has all of the rules as separate rows. */
	protected static String  getEntriesDatabaseTableName() {
		if (HDLmMain.checkProductionSystem())
	    return HDLmConfig.getString("entriesDatabaseTableNameProd");
		else
			return HDLmConfig.getString("entriesDatabaseTableNameTest");
	} 
	/* Get the table name that contains the production modifications.
	   This is the table that has all of the rules as separate rows. */
	protected static String  getEntriesDatabaseTableNameProd() {		 
    return HDLmConfig.getString("entriesDatabaseTableNameProd");		 
	}
	/* Get the table name that contains the test modifications.
     This is the table that has all of the rules as separate rows. */
  protected static String  getEntriesDatabaseTableNameTest() {		 
    return HDLmConfig.getString("entriesDatabaseTableNameTest");		 
  }
  /* Get a caching enum string value showing if modifications 
     or proxy definitions should be cached or not and to what
     extent. Note that this value will be an enum value. */
	protected static HDLmCacheTypes  getEntriesDatabaseUseCache() {
	  return HDLmConfig.getEnumCache("entriesDatabaseUseCache");
	}
	/* Return the configuration userid */
	protected static String  getEntriesDatabaseUserid() {
	  return HDLmConfig.getString("entriesDatabaseUserid");
	}
  /* Get the company prefix value that may (or may not) come 
		 before the content value. If the company prefix value 
		 is set, it will always be followed by an underscore that
		 separates the company prefix value from the content value
		 (something like 'mod' or 'proxy'). The company prefix value 
		 should not start or end with an underscore. Other code will 
		 supply the underscore. */
	protected static String  getEntriesDreamtsoftCompanyPrefix() {
	 return HDLmConfig.getString("entriesDreamtsoftCompanyPrefix");
	}
  /* Get the suffix value that follows the content value. Note
		 that an underscore always separates the content value
		 (something like 'mod' or 'proxy') from the suffix value,
		 if the suffix value is non-blank. The suffix value should
		 not start with an underscore. Other code will supply the
		 underscore. */
	protected static String  getEntriesDreamtsoftContentSuffix() {
	 return HDLmConfig.getString("entriesDreamtsoftContentSuffix");
	}
  /* Get a true/false value showing if certain JavaScript
     programs should be fixed to make web sockets work. 
     The changes are complex and obscure. Note that this
     value is an actual Java true or false, not "TRUE" 
     or "FALSE". */
  protected static boolean  getFixWebSockets() {
	  return HDLmConfig.getBoolean("fixWebSockets");
  }
  /* Get the maximum number of times, we are willing
     to follow a response location header. The HTTP
     standard allows a server to respond to a request
     with an HTTP code that indicates that the client
     should get the location header from the response
     and send the current request to the new location.
     Of course, this could cause infinite loops. This
     limit should prevent infinite loops. */
	protected static int  getFollowLocation() {
		return HDLmConfig.getInteger("followLocation");
	}
  /* Get a true/false value showing if pass-through
	   mode should be forced in all cases. If this 
	   value is set to true, then rules will not be
	   obtained from the server and rules will not be
	   applied to DOM entries. Note that this value is
	   an actual Java true or false, not "TRUE" or "FALSE". */
	protected static boolean  getForcePassThru() {
	  return HDLmConfig.getBoolean("forcePassThru");
	}
	/* Get the default JDBC pool size (connection count).
	   This value can be changed. However, larger pool
	   sizes use more memory and should be avoided, if 
	   possible. */
	protected static int   getJdbcPoolConnectionCount() {
		return HDLmConfig.getInteger("jdbcPoolconnectionCount");
	}
  /* Get the changes log file name from the configuration */
  protected static String  getLogChangesFileName() {
    return HDLmConfig.getString("logChangesFileName");
  }
  /* Get the log file name from the configuration */
  protected static String  getLogFileName() {
    return HDLmConfig.getString("logFileName");
  }
  /* Get a true/false value showing if rule matching should be
     be traced or not. Note that this value is an actual Java 
     true or false, not "TRUE" or "FALSE". */
  protected static boolean  getLogRuleMatching() {
    return HDLmConfig.getBoolean("logRuleMatching");
  }
	/* Get the Open AI maximum string length. If the string 
	   exceeds this length, it will be truncated. */  
  protected static int   getOpenAIMaximumStringLength() {
	  return HDLmConfig.getInteger("openAIMaximumStringLength");
  }
	/* Get the Open AI API key for Schaeffer */  
  protected static String  getOpenAIApiKeySchaeffer() {
    return HDLmConfig.getString("openAIApiKeySchaeffer");
  }
  /* Get the method that is used to access the parameters */
  protected static String  getParametersAccessMethod() {
    return HDLmConfig.getString("parametersAccessMethod");
  }
  /* Get the method that is used to obtain (access/update) the parameters */
  protected static String  getParametersInternetMethod() {
    return HDLmConfig.getString("parametersInternetMethod");
  }
  /* Get the method that is used to update the parameters */
  protected static String  getParametersUpdateMethod() {
    return HDLmConfig.getString("parametersUpdateMethod");
  }
  /* Get the URL that is used to obtain (access/update) the parameters */
  protected static String  getParametersUrl() {
    return HDLmConfig.getString("parametersUrl");
  }
  /* Get the pass-through limit value abd return it to the caller. The
		 pass-through limit value determines the fraction of events that are
		 treated as null events (nothing is changed). The value is treated as
		 a percentage and can be a non-integer value (such as 5.5). If this
		 value is set to zero, no events will be treated as null events. If
		 this value is set to 10.0, then 10% of events will be treated as null
		 events. If this value is set to 100.0, then all events will be treated
		 as null events. */
	public static double  getPassThroughLimit() {
	  return HDLmConfig.getDouble("passThroughLimit");
	}
	/* Get the name of of the perceptual hash program. The name of pHash
	   program is returned to the caller. */  
	protected static String  getPHashName() {
		return HDLmConfig.getString("phashName");
	}
  /* Get the standard WebSocket server (listener) port number and
     return it to the caller. The port number is always returned 
     to the caller as a proper number, not a string. */
	protected static int   getPortNumberWebSocket() {
		return HDLmConfig.getInteger("portNumberWebSocket");
  }
	/* Get the name of of the proxy program (originally written in a different
	   language) that must be simulated by this code */
  protected static String  getProxyName() {
  	return HDLmConfig.getString("proxyName");
  } 
  /* Get the rate control value and return it to the caller. 
     The rate control value is always returned to the caller 
     as a proper number, not a string. */
	protected static int   getRateControl() {
		return HDLmConfig.getInteger("rateControl");
	}
  /* Get the scope valid for value and return it to the caller. 
     The scope valid for value is always returned to the caller 
     as a proper number, not a string. The value is the number
     of seconds a scope is valid for. */
  protected static int   getScopeValidForSeconds() {
	  return HDLmConfig.getInteger("scopeValidForSeconds");
  }
  /* Get the secret encryption key used for cookies and perhaps
     other things. Return the secret encryption key to the caller
     as a string. */ 
  protected static String  getSecretEncryptionKey() {
  	return HDLmConfig.getString("secretEncryptionKey");
  }
  /* Get the current server name used to handle POST requests
     and perhaps other things. Return the server name to the 
     caller as a string. */ 
	protected static String  getServerName() {
		return HDLmConfig.getString("serverName");
	}
  /* Get a true/false value showing if HTTP/2 should be supported 
     or not. Note that this value is an actual Java true or false,
     not "TRUE" or "FALSE". */
  protected static boolean  getSupportHttp2() {
	  return HDLmConfig.getBoolean("supportHTTP2");
  }
  /* Get a true/false value showing if HTTP/3 should be supported 
     or not. Note that this value is an actual Java true or false,
     not "TRUE" or "FALSE". */
  protected static boolean  getSupportHttp3() {
    return HDLmConfig.getBoolean("supportHTTP3");
  }
  /* Get the maximum number of undo/redo operation that we 
     can keep track of. This value is used as an ArrayList
     size. */ 
  protected static int      getUnReLimit() {
	  return HDLmConfig.getInteger("unReLimit");
  }
  /* Set a true/false value showing if pass-through
	   mode should be forced in all cases. If this 
	   value is set to true, then rules will not be
	   obtained from the server and rules will not be
	   applied to DOM entries. Note that the value passed
	   by the caller is an actual Java true or false,
	   not "TRUE" or "FALSE". */
	protected static void  setForcePassThru(final boolean newValue) {
	  HDLmConfig.setConfigBoolean("forcePassThru", newValue);
	}
}
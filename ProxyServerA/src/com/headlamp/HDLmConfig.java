package com.headlamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
/**
 * HDLmConfig short summary.
 *
 * HDLmConfig description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and many instances of this class
   can definitely be created. Instances of this class are created for
   each configuration value. */ 
public class HDLmConfig {	
	/* This is the default constructor for this class and it can never be invoked */
	private HDLmConfig() {}
	/* The next constructor is used to build instances of this class
	   that are (eventually) added to the main configuration map */
	private HDLmConfig(final HDLmConfigTypes newType, final String newValue) {
		/* Check if the configuration type passed by the caller is null */
		if (newType == null) {
			String  errorText = "Configuration type passed to HDLmConfig constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the configuration type passed by the caller is valid */
		if (newType == HDLmConfigTypes.NONE) {
			String  errorText = "Configuration type passed to HDLmConfig constructor is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the new value (a Java String) passed by the caller is null */
		if (newValue == null) {
			String  errorText = "Configuration value passed to HDLmConfig constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new value (a Java String) passed by the caller is invalid */
		if (newValue.length() < 0) {
			String  errorText = "Configuration value passed to HDLmConfig constructor is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Set a few values in the new class instance */
		configType = newType;
		configValue = newValue;	
		/* Convert the new value to a type specific value based on the type */
		switch (configType) {
		  /* Handle booleans */
		  case BOOLEAN: {
				/* Use the new value and check if the new value is a boolean. This 
			     code will only work if the new value is actually a boolean. */ 
			  String  currentValueString = newValue;
			  if (!currentValueString.equals("false") && !currentValueString.equals("true")) {
				  String  errorText = String.format("Value (%s) is not a boolean", newValue);
				  throw new IllegalArgumentException(errorText);			
			  }
			  configBoolean = Boolean.parseBoolean(currentValueString);		
			  break;
		  }		
		  /* Handle rule cache values */
		  case CACHE: {
				/* Use the new value and check if the new value is a cache enum. This 
		       code will only work if the new value is actually a cache enum. */ 	 
		  	String  currentValueString = newValue;
		  	HDLmCacheTypes  currentEnumValue = HDLmCacheTypes.valueOfString(currentValueString);
			  if (currentEnumValue == null ||
		        currentEnumValue == HDLmCacheTypes.NONE) {
				  String  errorText = String.format("Value (%s) is not a cache enum", newValue);
				  throw new IllegalArgumentException(errorText);			
			  }
			  configCache = currentEnumValue;
			  break;
		  }
		  /* Handle doubles */
		  case DOUBLE: {
				/* Use the new value and check if the new value is a double. This 
			     code will only work if the new value is actually a double. */ 
				String      currentValueString = newValue; 
				Double      currentDouble = HDLmUtility.convertDouble(currentValueString);
				if (currentDouble == null) {
					String  errorText = String.format("Value (%s) is not a double", newValue);
					throw new IllegalArgumentException(errorText);			
				}
				configDouble = currentDouble;		
				break;
		  }		
		  /* Handle ints. Note that this code handles ints, not Integers. */
		  case INT: {
				/* Use the new value and check if the new value is an int. This 
		       code will only work if the new value is actually an int. */ 	 
		  	String      currentValueString = newValue;
				Integer     currentInteger = HDLmUtility.convertInteger(currentValueString);
				if (currentInteger == null) {
					String  errorText = String.format("Value (%s) is not an integer", newValue);
					throw new IllegalArgumentException(errorText);			
				}
				configInt = currentInteger;	
				break;
		  }
		  /* Handle strings */
		  case STRING: {
				/* Use the new value and check if the new value is a string. This 
			     code will only work if the new value is actually a string. */ 
				String      currentValueString = newValue;
				if (StringUtils.isNumeric(currentValueString)) {
					String  errorText = String.format("Value (%s) is numeric", newValue);
					throw new IllegalArgumentException(errorText);			
				}
				/* Check if the value is one of the known boolean values. Report an 
				   error if we find a boolean value. */
				if (currentValueString.equals("false") || currentValueString.equals("true")) {
					String  errorText = String.format("Value (%s) is a boolean", newValue);
					throw new IllegalArgumentException(errorText);			
				}
				configString = currentValueString;
				break;
		  }	
		  /* Handle the default case. Of course, the default case should
		     never occur. However, you never know. */ 
		  default: {
		  	String  newTypeString = newType.toString();
				String  errorText = String.format("Type of value (%s) is not supported", newTypeString);
				throw new IllegalArgumentException(errorText);			  	
		  }
		}
	}
	/* Define the map that contains all of the configuration 
	   values. Note that the JavaScript code has another copy 
	   of these values. */
  private static Map<String, HDLmConfig>  configValues = null; 	
  /* The static block below initializes the map that contains 
     all of the configuration values */ 
	static {
		/* Define a few configuration constants for use in our code.  
		   Note that the JavaScript code has another copy of these 
		   values. */ 		
		final Map<String, HDLmConfig>   configImmutable = Map.ofEntries(	
		  Map.entry("AWSAccessKeyId",                       new HDLmConfig(HDLmConfigTypes.STRING,  "")),
			Map.entry("AWSCognitoAcceptEncoding",             new HDLmConfig(HDLmConfigTypes.STRING,  "identity")),
			Map.entry("AWSCognitoApiAdminGetUser",            new HDLmConfig(HDLmConfigTypes.STRING,  "AWSCognitoIdentityProviderService.AdminGetUser")),
			Map.entry("AWSCognitoApiAdminSetUserPassword",    new HDLmConfig(HDLmConfigTypes.STRING,  "AWSCognitoIdentityProviderService.AdminSetUserPassword")),
			Map.entry("AWSCognitoApiGetUser",                 new HDLmConfig(HDLmConfigTypes.STRING,  "AWSCognitoIdentityProviderService.GetUser")),
			Map.entry("AWSCognitoApiInitiateAuth",            new HDLmConfig(HDLmConfigTypes.STRING,  "AWSCognitoIdentityProviderService.InitiateAuth")),
			Map.entry("AWSCognitoApiRespondToAuthChallenge",  new HDLmConfig(HDLmConfigTypes.STRING,  "AWSCognitoIdentityProviderService.RespondToAuthChallenge")),
			Map.entry("AWSCognitoCanonicalUri",               new HDLmConfig(HDLmConfigTypes.STRING,  "/")),
			Map.entry("AWSCognitoContentType",                new HDLmConfig(HDLmConfigTypes.STRING,  "application/x-amz-json-1.1")),
			Map.entry("AWSCognitoHost",                       new HDLmConfig(HDLmConfigTypes.STRING,  "cognito-idp.us-east-2.amazonaws.com")),
			Map.entry("AWSCognitoHttpMethod",                 new HDLmConfig(HDLmConfigTypes.STRING,  "POST")),
			Map.entry("AWSCognitoServiceName",                new HDLmConfig(HDLmConfigTypes.STRING,  "cognito-idp")),
		  /* The user agent value below is not accurate (at all). However, AWS Cognito
		     demands a user agent value and the value below actually works. */
			Map.entry("AWSCognitoUserAgent",                  new HDLmConfig(HDLmConfigTypes.STRING,  "Boto3/1.26.83 Python/3.9.13 Windows/10 Botocore/1.29.83")),
			Map.entry("AWSCognitoUserPoolClientAppId",        new HDLmConfig(HDLmConfigTypes.STRING,  "4aa1bqd057v64omjq84hc4pnvl")),
			Map.entry("AWSCognitoUserPoolId",                 new HDLmConfig(HDLmConfigTypes.STRING,  "us-east-2_xTvIIRtgB")),
			Map.entry("AWSCognitoUserPoolName",               new HDLmConfig(HDLmConfigTypes.STRING,  "HeadlampUserPool1")),
			Map.entry("AWSCognitoUserPoolRegion",             new HDLmConfig(HDLmConfigTypes.STRING,  "us-east-2")),
			Map.entry("AWSSecretAccessKey",                   new HDLmConfig(HDLmConfigTypes.STRING,  "")),
			Map.entry("clustersMaxCount",                     new HDLmConfig(HDLmConfigTypes.INT,     "10")),		  
			Map.entry("clustersSampleSize",                   new HDLmConfig(HDLmConfigTypes.INT,     "100")),
			Map.entry("clustersThreshold",                    new HDLmConfig(HDLmConfigTypes.DOUBLE,  "0.10")),
		  Map.entry("companyName",                          new HDLmConfig(HDLmConfigTypes.STRING,  "example.com")),
		  /* The maximum age in seconds for a Cookie. A positive value indicates that the cookie will 
		     expire after that many seconds have passed. Note that the value is the maximum age when 
		     the cookie will expire, not the cookie's current age. A negative value means that the 
		     cookie is not stored persistently and will be deleted when the Web browser exits. 
		     A zero value causes the cookie to be deleted. */		  
		  Map.entry("cookieMaxAge",                         new HDLmConfig(HDLmConfigTypes.INT,     "28800")),
		  /* The current environment shows if we are running a production system or a test system. The current
         environment will be set to values such as "prod" or "production" or "test" (without the quotes).
         Other values may be added later. */ 
		  Map.entry("currentEnvironment",                   new HDLmConfig(HDLmConfigTypes.STRING,  "prod")),
		  Map.entry("currentEnvironmentProd",               new HDLmConfig(HDLmConfigTypes.STRING,  "prod")),
		  Map.entry("currentEnvironmentTest",               new HDLmConfig(HDLmConfigTypes.STRING,  "test")),
		  Map.entry("entriesBridgeCompanyPrefix",           new HDLmConfig(HDLmConfigTypes.STRING,  "")),	
		  Map.entry("entriesBridgeContentSuffix",           new HDLmConfig(HDLmConfigTypes.STRING,  "java")),	
		  Map.entry("entriesBridgeDeleteUrl",               new HDLmConfig(HDLmConfigTypes.STRING,  "/io/bucket/delete/")),
		  Map.entry("entriesBridgeInsertUrl",               new HDLmConfig(HDLmConfigTypes.STRING,  "/io/bucket/insert/")),
		  Map.entry("entriesBridgeInternetMethod",          new HDLmConfig(HDLmConfigTypes.STRING,  "https")),	
		  Map.entry("entriesBridgePassword",                new HDLmConfig(HDLmConfigTypes.STRING,  "")),	
		  Map.entry("entriesBridgeReadUrl",                 new HDLmConfig(HDLmConfigTypes.STRING,  "/io/bucket/search/")),
		  Map.entry("entriesBridgeUpdateUrl",               new HDLmConfig(HDLmConfigTypes.STRING,  "/io/bucket/update/")),
		  /* This value does not appear to be in use */		  
		  Map.entry("entriesBridgeUseCache",                new HDLmConfig(HDLmConfigTypes.CACHE,   "update")),	
		  Map.entry("entriesBridgeUserid",                  new HDLmConfig(HDLmConfigTypes.STRING,  "")),
		  /* The database entries follow */
		  Map.entry("entriesDatabaseCompanyPrefix",         new HDLmConfig(HDLmConfigTypes.STRING,  "")),	
		  Map.entry("entriesDatabaseContentSuffix",         new HDLmConfig(HDLmConfigTypes.STRING,  "java")),	
		  Map.entry("entriesDatabaseDatabaseNameProd",      new HDLmConfig(HDLmConfigTypes.STRING,  "main_9")),
		  Map.entry("entriesDatabaseDatabaseNameTest",      new HDLmConfig(HDLmConfigTypes.STRING,  "test_1")),
		  Map.entry("entriesDatabaseDomainNameProd",        new HDLmConfig(HDLmConfigTypes.STRING,  "")),
		  Map.entry("entriesDatabaseDomainNameTest",        new HDLmConfig(HDLmConfigTypes.STRING,  "test-1.cluster-c7czx6cxnjsz.us-east-2.rds.amazonaws.com")),
		  Map.entry("entriesDatabaseInternetMethod",        new HDLmConfig(HDLmConfigTypes.STRING,  "https")),	
		  Map.entry("entriesDatabasePassword",              new HDLmConfig(HDLmConfigTypes.STRING,  "")),	
		  Map.entry("entriesDatabasePortNumber",            new HDLmConfig(HDLmConfigTypes.INT,     "3306")),
		  Map.entry("entriesDatabaseTableNameProd",         new HDLmConfig(HDLmConfigTypes.STRING,  "main_9")),	
		  Map.entry("entriesDatabaseTableNameTest",         new HDLmConfig(HDLmConfigTypes.STRING,  "test_1")),	
		  Map.entry("entriesDatabaseUseCache",              new HDLmConfig(HDLmConfigTypes.CACHE,   "update")),	
		  Map.entry("entriesDatabaseUserid",                new HDLmConfig(HDLmConfigTypes.STRING,  "")),	
		  /* Some Dreamtsoft entries follow */		  
		  Map.entry("entriesDreamtsoftCompanyPrefix",       new HDLmConfig(HDLmConfigTypes.STRING,  "")),
		  Map.entry("entriesDreamtsoftContentSuffix",       new HDLmConfig(HDLmConfigTypes.STRING,  "java")),
	    Map.entry("fixWebSockets",                        new HDLmConfig(HDLmConfigTypes.BOOLEAN, "true")),
	    Map.entry("followLocation",                       new HDLmConfig(HDLmConfigTypes.INT,     "30")),
	    Map.entry("forcePassThru",                        new HDLmConfig(HDLmConfigTypes.BOOLEAN, "false")),
	    Map.entry("jdbcPoolconnectionCount",              new HDLmConfig(HDLmConfigTypes.INT,     "40")),		
	    Map.entry("logChangesFileName",                   new HDLmConfig(HDLmConfigTypes.STRING,  "HDLmChanges.log")),
	    Map.entry("logFileName",                          new HDLmConfig(HDLmConfigTypes.STRING,  "info.log")),
	    Map.entry("logRuleMatching",                      new HDLmConfig(HDLmConfigTypes.BOOLEAN, "false")),
	    Map.entry("openAIMaximumStringLength",            new HDLmConfig(HDLmConfigTypes.INT,     "6000")),	
	    Map.entry("openAIApiKeySchaeffer",                new HDLmConfig(HDLmConfigTypes.STRING,  "")),	
	    Map.entry("parametersAccessMethod",               new HDLmConfig(HDLmConfigTypes.STRING,  "cgi-bin/get-set.py?get")),
	    Map.entry("parametersInternetMethod",             new HDLmConfig(HDLmConfigTypes.STRING,  "http")),
	    Map.entry("parametersUpdateMethod",               new HDLmConfig(HDLmConfigTypes.STRING,  "cgi-bin/get-set.py?set")),
	    Map.entry("parametersUrl",                        new HDLmConfig(HDLmConfigTypes.STRING,  "headlamp-software.com")),
	    Map.entry("passThroughLimit",                     new HDLmConfig(HDLmConfigTypes.DOUBLE,  "5.0")),
	    Map.entry("phashName",                            new HDLmConfig(HDLmConfigTypes.STRING,  "HDLmPHash")),
	    /* The port number below is hardcoded into the browser extension
	       that uses WebSockets to send node identifier values to the 
	       Electron JS application */
	    Map.entry("portNumberWebSocket",                  new HDLmConfig(HDLmConfigTypes.INT,     "8102")),
	    Map.entry("proxyName",                            new HDLmConfig(HDLmConfigTypes.STRING,  "HDLmProxy.php")),
	    Map.entry("rateControl",                          new HDLmConfig(HDLmConfigTypes.INT,     "50")),
	    Map.entry("scopeValidForSeconds" ,                new HDLmConfig(HDLmConfigTypes.INT,     "86400")),
	    /* The secret key must be exactly 16 bytes long. Apparently, AES requires this */
	    Map.entry("secretEncryptionKey",                  new HDLmConfig(HDLmConfigTypes.STRING,  "abcd1234efgh5678")),
	    Map.entry("serverName",                           new HDLmConfig(HDLmConfigTypes.STRING,  "javaproxya.dnsalias.com")),
	    Map.entry("serverNameProd",                       new HDLmConfig(HDLmConfigTypes.STRING,  "javaproxya.dnsalias.com")),
	    Map.entry("serverNameTest",                       new HDLmConfig(HDLmConfigTypes.STRING,  "javaproxya.dnsalias.com")),
	    Map.entry("supportHTTP2",                         new HDLmConfig(HDLmConfigTypes.BOOLEAN, "true")),
	    /* The Jetty folks say that HTTP/3 is not ready for production use. We
         are going to have to wait a while for a production version of HTTP/3. */	 
		  Map.entry("supportHTTP3",                         new HDLmConfig(HDLmConfigTypes.BOOLEAN, "false")),
		  Map.entry("unReLimit",                            new HDLmConfig(HDLmConfigTypes.INT,     "100")));
		/* Create the main configuration map */
		configValues = new HashMap<String, HDLmConfig>();
		/* Copy all of the keys and values from the immutable map to 
		   the mutable configuration map */ 
		for (Map.Entry<String, HDLmConfig> mapEntry : configImmutable.entrySet()) {
	    String      entryKey = mapEntry.getKey();
	    HDLmConfig  entryValue = mapEntry.getValue();
	    configValues.put(entryKey,  entryValue);
   	}
	}
	/* The configuration type is the type of the current configuration value. 
	   All configuration value have a type. The correct function must be used
	   to obtain a value. */ 
	private HDLmConfigTypes   configType = null;
	/* The next field contains that actual configuration value. All configuration
	   values are stored as string. They are converted to other data types when 
	   the correct function is used to obtain a configuration value. */ 
	private String            configValue = null;	
	/* The next set of values contain the actual configuration value converted
	   to the correct data type. These values eliminate the need to convert 
	   each value when it is requested. */ 
	private Boolean           configBoolean = null;
	private HDLmCacheTypes    configCache = null;
	private Double            configDouble = null;
	private Integer           configInt = null;
	private String            configString = null;	 
	/* This static method returns the boolean value of a configuration
	   value if the configuration name is valid (exists) and if the 
	   configuration value is actually a boolean. */
	protected static boolean  getBoolean(String configName) {
		/* Check a few values passed by the caller */
		if (configName == null) {
			String   errorText = "Configuration name reference passed to getBoolean is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the configuration name passed by the caller is valid
		   or not. We need to raise an exception if the configuration name
		   passed by the caller is unknown. */
		if (!configValues.containsKey(configName)) {
			String  errorText = String.format("Invalid configuration name (%s) passed to getBoolean", configName);
			throw new IllegalArgumentException(errorText);
		}
		/* Get the value from the Map and check if the value is a boolean. This 
       method can only be used to obtain values that are actually booleans. */
		HDLmConfig        currentConfigEntry = configValues.get(configName);
		HDLmConfigTypes   currentConfigType = currentConfigEntry.getConfigType();
		if (currentConfigType != HDLmConfigTypes.BOOLEAN) {
			String  errorText = String.format("Configuration name (%s) passed to getBoolean is not for a boolean", configName);
			throw new IllegalArgumentException(errorText);
		}	
		return currentConfigEntry.configBoolean;
	}
	/* This static method returns all of the configuration information
	   as a single JSON string. The JSON string is constructed and 
	   returned to the caller. The caller can use the JSON string 
	   in any possible way. However, the JSON string is probably
	   returned to another machine. */
	protected static String  getConfigs() {
		boolean                   firstValue = true; 
		Map<String, HDLmConfig>   treeCopy = new TreeMap<String, HDLmConfig>(configValues);
		StringBuilder             outBuilder = new StringBuilder();
		/* Add the initial left brace */
		outBuilder.append('{');
		for (String key : treeCopy.keySet()) {
			/* Check if we are adding the first key/value pair. Add
			   a comma if this is not true. */
			if (!firstValue)
				outBuilder.append(',');
			firstValue = false;
			/* Add the current key value */
			outBuilder.append('"');
			outBuilder.append(key);
			outBuilder.append("\":");
			/* Get the value of the current key */		
			HDLmConfig  currentConfig = configValues.get(key);
			String      currentValueString = currentConfig.getConfigValue();
			/* Add the value of the current key to the output JSON */
			outBuilder.append(HDLmJson.getJsonValue(currentValueString));
		}
		/* Add the final right brace */
		outBuilder.append('}');		
		return outBuilder.toString();
	}
	/* This method returns the string value of a configuration instance
	   in all cases. This routine is intended for internal use. This is
	   not a static method and valid instance of this class must exist
	   for this routine to be used. */ 
	protected HDLmConfigTypes  getConfigType() {
		return configType;
	}
	/* This method returns the string value of a configuration instance
	   in all cases. This routine is intended for internal use. This is
	   not a static method and valid instance of this class must exist
	   for this routine to be used. */ 
	protected String       getConfigValue() {
		return configValue;
	}
	/* This static method returns the value of a configuration name if the
	   configuration name is valid (exists). This method can be used to obtain
	   values of any type. The string value of a configuration value is always
	   returned. */
  protected static String  getConfigValue(String configName) {
		/* Check a few values passed by the caller */
		if (configName == null) {
			String   errorText = "Configuration name reference passed to getConfigValue is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the configuration name passed by the caller is valid
		   or not. We need to raise an exception if the configuration name
		   passed by the caller is unknown. */
		if (!configValues.containsKey(configName)) {
			String  errorText = String.format("Invalid configuration name (%s) passed to getConfigValue", configName);
			throw new IllegalArgumentException(errorText);
		}
		/* Get the entry from the map and extract the value from the entry */
	  HDLmConfig  currentConfigEntry = configValues.get(configName);
	  return currentConfigEntry.getConfigValue();
  }
	/* This static method returns the double value of a configuration
	   value if the configuration name is valid (exists) and if the 
	   configuration value is actually a double (floating-point value). */
	protected static double  getDouble(String configName) {
		/* Check a few values passed by the caller */
		if (configName == null) {
			String   errorText = "Configuration name reference passed to getDouble is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the configuration name passed by the caller is valid
		   or not. We need to raise an exception if the configuration name
		   passed by the caller is unknown. */
		if (!configValues.containsKey(configName)) {
			String  errorText = String.format("Invalid configuration name (%s) passed to getDouble", configName);
			throw new IllegalArgumentException(errorText);
		}
		/* Get the value from the Map and check if the value is a double. This 
	     method can only be used to obtain values that are actually doubles. */
		HDLmConfig        currentConfigEntry = configValues.get(configName);
		HDLmConfigTypes   currentConfigType = currentConfigEntry.getConfigType();
		if (currentConfigType != HDLmConfigTypes.DOUBLE) {
			String  errorText = String.format("Configuration name (%s) passed to getDouble is not for a double", configName);
			throw new IllegalArgumentException(errorText);
		}	
		return currentConfigEntry.configDouble;
	}
	/* This static method returns the enum value of a configuration
	   value if the configuration name is valid (exists) and if the 
	   configuration value is actually a cache (rule caching) enum
	   string. */
	protected static HDLmCacheTypes  getEnumCache(String configName) {
		/* Check a few values passed by the caller */
		if (configName == null) {
			String   errorText = "Configuration name reference passed to getEnumCache is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the configuration name passed by the caller is valid
		   or not. We need to raise an exception if the configuration name
		   passed by the caller is unknown. */
		if (!configValues.containsKey(configName)) {
			String  errorText = String.format("Invalid configuration name (%s) passed to getEnumCache", configName);
			throw new IllegalArgumentException(errorText);
		}
		/* Get the value from the Map and check if the value is a cache enum 
		   value. This method can only be used to obtain values that are actually 
		   cache enum values. */
  	HDLmConfig        currentConfigEntry = configValues.get(configName);
	  HDLmConfigTypes   currentConfigType = currentConfigEntry.getConfigType();
	  if (currentConfigType != HDLmConfigTypes.CACHE) {
		  String  errorText = String.format("Configuration name (%s) passed to getEnumCache is not for a cache enum", configName);
		  throw new IllegalArgumentException(errorText);
	  }
		return currentConfigEntry.configCache;
	}
	/* This static method returns the integer value of a configuration
     value if the configuration name is valid (exists) and if the 
     configuration value is actually an integer (not a floating-point 
     value or something else). Note that this function returns an int,
     not an Integer. */
	protected static int   getInteger(String configName) {
		/* Check a few values passed by the caller */
		if (configName == null) {
			String   errorText = "Configuration name reference passed to getInteger is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the configuration name passed by the caller is valid
		   or not. We need to raise an exception if the configuration name
		   passed by the caller is unknown. */
		if (!configValues.containsKey(configName)) {
			String  errorText = String.format("Invalid configuration name (%s) passed to getInteger", configName); 
			throw new IllegalArgumentException(errorText);
		}
		/* Get the value from the Map and check if the value is an int (not an
		   Integer). This method can only be used to obtain values that are actually 
	     int values. */
	  HDLmConfig        currentConfigEntry = configValues.get(configName);
    HDLmConfigTypes   currentConfigType = currentConfigEntry.getConfigType();
    if (currentConfigType != HDLmConfigTypes.INT) {
	     String  errorText = String.format("Configuration name (%s) passed to getInteger is not for an int", configName); 
	     throw new IllegalArgumentException(errorText);
    }
		return currentConfigEntry.configInt;
	}
	/* This static method returns the string value of a configuration
	   value if the configuration name is valid (exists) and if the 
		 configuration value is actually a string (not a number). */
	protected static String  getString(String configName) {
		/* Check a few values passed by the caller */
		if (configName == null) {
			String   errorText = "Configuration name reference passed to getString is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the configuration name passed by the caller is valid
	     or not. We need to raise an exception if the configuration name
	     passed by the caller is unknown. */
		if (!configValues.containsKey(configName)) {
			String  errorText = String.format("Invalid configuration name (%s) passed to getString", configName);
			throw new IllegalArgumentException(errorText);
		}
		/* Get the value from the Map and check if the value is a string. This 
	     method can only be used to obtain values that are actually string 
       values. */
    HDLmConfig        currentConfigEntry = configValues.get(configName);
    HDLmConfigTypes   currentConfigType = currentConfigEntry.getConfigType();
    if (currentConfigType != HDLmConfigTypes.STRING) {
      String  errorText = String.format("Configuration name (%s) passed to getString is not for a string", configName); 
      throw new IllegalArgumentException(errorText);
    }
		return currentConfigEntry.configString;
	}
	/* This static method sets the boolean value of a configuration
	   value if the configuration name is valid (exists) and if the 
	   configuration value is actually a boolean. */
	protected static void  setConfigBoolean(String configName, final boolean newValue) {
		/* Check a few values passed by the caller */
		if (configName == null) {
			String   errorText = "Configuration name reference passed to setBoolean is null";
			throw new NullPointerException(errorText);		
		} 
		/* Check if the configuration name passed by the caller is valid
		   or not. We need to raise an exception if the configuration name
		   passed by the caller is unknown. */
		if (!configValues.containsKey(configName)) {
			String  errorText = String.format("Invalid configuration name (%s) passed to setBoolean", configName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Get the value from the Map and check if the value is a boolean. This 
       method can only be used to update values that are actually boolean 
       values. */
	  HDLmConfig        currentConfigEntry = configValues.get(configName);
	  HDLmConfigTypes   currentConfigType = currentConfigEntry.getConfigType();
	  if (currentConfigType != HDLmConfigTypes.BOOLEAN) {
	    String  errorText = String.format("Configuration name (%s) passed to setBoolean is not for a boolean", configName);
	    Exception exception = new IllegalArgumentException(errorText);
	    throw new IllegalArgumentException(errorText, exception);
	  }
		/* Get the value from the Map and check if the value is a boolean. This 
		   method can only be used to obtain values that are actually booleans. */
		String  currentValueString = currentConfigEntry.getConfigValue();
		if (!currentValueString.equals("false") && !currentValueString.equals("true")) {
			String  errorText = String.format("Value of configuration name (%s) is not for a boolean", configName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);			
		}
		/* Store the value passed by the caller */
		String      newValueStr = String.valueOf(newValue);
		HDLmConfig  newConfig = new HDLmConfig(HDLmConfigTypes.BOOLEAN, newValueStr);
		configValues.put(configName, newConfig);
		return;
	}
	/* This static method sets the string value of a configuration
	   value if the configuration name is valid (exists) */
	protected static void  setConfigString(final String configName, final String newValue) {
		/* Check a few values passed by the caller */
		if (configName == null) {
			String   errorText = "Configuration name reference passed to setConfigValue is null";
			throw new NullPointerException(errorText);		
		} 
		if (newValue == null) {
			String   errorText = "New configuration value reference passed to setConfigValue is null";
			throw new NullPointerException(errorText);		
		} 
		/* Check if the configuration name passed by the caller is valid
		   or not. We need to raise an exception if the configuration name
		   passed by the caller is unknown. */
		if (!configValues.containsKey(configName)) {
			String  errorText = String.format("Invalid configuration name (%s) passed to setConfigValue", configName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Store the value passed by the caller */
		String            newValueStr = String.valueOf(newValue);
		HDLmConfig        currentConfigEntry = configValues.get(configName);
		HDLmConfigTypes   oldConfigType = currentConfigEntry.getConfigType(); 
		HDLmConfig        newConfig = new HDLmConfig(oldConfigType, newValueStr);
		configValues.put(configName, newConfig);
		return;
	}
	/* This routine does all of the work needed to set the configuration
     values. Some secrets are stored in the configuration values. This 
     routine gets the secrets from the AWS Secrets Manager and stores 
     them in the configuration values. */
	protected static void  setConfigurationValues() {
		ArrayList<ArrayList<String>> mainList = new ArrayList<ArrayList<String>>();
		/* The order here is, where to put the configuration value, the AWS name of the value, and the JSON key,
		   if any. */
		ArrayList<String>   infoAwsAccessKeyId =	new ArrayList<String>(Arrays.asList("AWSAccessKeyId", 
				                                                                          "AwsAccessKeyId", "")); 	
		ArrayList<String>   infoAwsSecretAccessKey =	new ArrayList<String>(Arrays.asList("AWSSecretAccessKey",
				                                                                              "AwsSecretAccessKey", ""));
		ArrayList<String>   infoOpenAiKeySchaeffer = new ArrayList<String>(Arrays.asList("openAIApiKeySchaeffer", 
				                                                                             "OpenApiAiKeySchaeffer", ""));
		ArrayList<String>   infoAwsDatabaseUserid = new ArrayList<String>(Arrays.asList("entriesDatabaseUserid", 
				                                                                            "Main9Auroa", "username"));
		ArrayList<String>   infoAwsDatabasePassword = new ArrayList<String>(Arrays.asList("entriesDatabasePassword", 
				                                                                              "Main9Auroa", "password"));
		/* The value that was obtained below was not the actual database name. It is not clear what this
		   value really is/was. */ 
		/*
		ArrayList<String>   infoAwsDatabaseName = new ArrayList<String>(Arrays.asList("entriesDatabaseDatabaseNameProd", 
				                                                                          "Main9Auroa", 
				                                                                          "dbClusterIdentifier"));
		*/
		ArrayList<String>   infoAwsDatabaseHost = new ArrayList<String>(Arrays.asList("entriesDatabaseDomainNameProd", 
				                                                                          "Main9Auroa", "host"));
		ArrayList<String>   infoAwsEntriesUserid = new ArrayList<String>(Arrays.asList("entriesBridgeUserid", 
				                                                                           "EntriesBridgeUserid", ""));
		ArrayList<String>   infoAwsEntriesPassword = new ArrayList<String>(Arrays.asList("entriesBridgePassword", 
				                                                                             "EntriesBridgePassword", ""));
		/* Add each set of information about an AWS secret to the main ArrayList */
		mainList.add(infoAwsAccessKeyId);
		mainList.add(infoAwsSecretAccessKey);
		mainList.add(infoOpenAiKeySchaeffer);
		mainList.add(infoAwsDatabaseUserid);
		mainList.add(infoAwsDatabasePassword);
		/* The value that was obtained below was not the actual database name. It is not clear what this
	     value really is/was. */ 
		/*
		mainList.add(infoAwsDatabaseName);
		*/
		mainList.add(infoAwsDatabaseHost);
		mainList.add(infoAwsEntriesUserid);
		mainList.add(infoAwsEntriesPassword);
	 /* Build a list of AWS secret names */
		ArrayList<String>  secretsName = new ArrayList<String>();
		for (ArrayList<String> secretInfo:mainList) {
			String  secretName = secretInfo.get(1);
			if (!secretsName.contains(secretName))
			  secretsName.add(secretName);  		
		}
		/* Get the actual secrets */
		SecretsManagerClient  client = HDLmAwsUtility.buildAwsSecretsManagerClient("us-east-2");
		Map<String, String>   secretsMap = HDLmAwsUtility.getAMapOfSecrets(client, secretsName);
		/* Store each of the secrets in the configuration values */
		for (ArrayList<String> secretInfo:mainList) {
			String  secretConfigName = secretInfo.get(0);
			String  secretAwsName = secretInfo.get(1);
			String  secretAwsJsonKey = secretInfo.get(2);
			/* Get the secret value from the map */
			String  secretAwsValue = secretsMap.get(secretAwsName);
	   /* Check if we need to extract the actual secret from some JSON */
			if (!secretAwsJsonKey.equals("")) {
				/* Convert the JSON string to a set of JSON objects */
		     JsonParser   parser = new JsonParser();
		     JsonElement  topNode = parser.parse(secretAwsValue);
				 String       actualSecretValue = HDLmJson.getJsonString(topNode, secretAwsJsonKey);
				 HDLmConfig.setConfigString(secretConfigName, actualSecretValue);
			}
			else
				HDLmConfig.setConfigString(secretConfigName, secretAwsValue);  		 		
		}  	
	}  
}
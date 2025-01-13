package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
/**
 * HDLmSession short summary.
 *
 * HDLmSession description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmSession {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmSession.class);  
	/* An instance of this class is created to handle session state
	   information. The actual session state information is typically
	   stored in HTTP cookies in JSON form. However, the JSON is not
	   easily handled by other code. This class is used to manage
	   session state information. */
  private String    index = null;
	private String    parameters = null;
	private String    sessionId = null;
	private String    useMode = null;
	/* This is the default constructor for this class. It doesn't do anything.
	   All fields of this class will be set to the default values specified 
	   above. This constructor is required so that this class can be extended. */
	protected HDLmSession() {}
	/* This constructor copies all of the fields from another session instance.
	   Note that the copy is a deep copy of sorts. All of the fields should
	   actually be copied or should be immutable. */
	protected HDLmSession(HDLmSession oldSession) {
		/* Check if the old session passed to this routine is null or not */
		if (oldSession == null) {
			String  errorText = "Old session reference used to build a new session is null";
			throw new NullPointerException(errorText);
		}
		/* The call below copies the fields from the old session to
		   the new session. In other words, the actual work of a copy
		   constructor is done by the call below. */ 
		copySessionFields(oldSession);
	}
	/* The method below either adds a new entry to the session ID cache
	   or replaces an existing entry. The same call is used in both 
	   cases (put). Of course, the caller must provide a valid cache
	   key and value. */ 
	protected static void addToCache(final String sessionIdStr, 
			                             final HDLmSession sessionObj) {
		/* Check if the session ID string passed by the caller is null */
		if (sessionIdStr == null) {
		  String  errorText = "Session ID string passed to addToCache is null";
      throw new NullPointerException(errorText);
		}
		/* Check if the session object passed by the caller is null */
		if (sessionObj == null) {
		  String  errorText = "Session object passed to addToCache is null";
      throw new NullPointerException(errorText);
		}
		/* Get the session ID constant that is used to build the cache key */
		String   sessionIdName = HDLmDefines.getString("HDLMSESSIONID");
		if (sessionIdName == null) {
			String   errorFormat = "Define value for session ID name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSESSIONID");
			HDLmAssertAction(false, errorText);		    	
		}
    String  cacheKey = sessionIdName + " " + sessionIdStr;
    /* Create a new session object from the session object passed 
       by the caller. The new session object is built so that the
       old session object can be changed without modifying the 
       contents of the cache. */
    HDLmSession   newSessionObj = new HDLmSession(sessionObj);    
		/* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
    if (logIsDebugEnabled) 
     LOG.debug("Session cache log - Storing (" + cacheKey + ")"); 
    HDLmSessionCache.putToCache(cacheKey, newSessionObj);		
	}
	/* Build a JSON string from a session object. The caller provides
	   the non-null object instance (object of this class). The current
	   instance (literally this) is always handled by this routine. This 
	   routine tries to convert the session object into JSON using a
	   standard JSON constructor. */ 
	protected String       buildJson() {
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After entry to buildJson");
		Gson     gsonInstance = HDLmMain.gsonMain;
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After copy gsonMain in buildJson");
		String   jsonString = gsonInstance.toJson(this);
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After to JSON in buildJson");
		return jsonString;
	}
	/* Build a JSON string from a session object with only the session
	   ID in it. The caller provides the non-null object instance (object 
	   of this class). The current instance (literally this) is always 
	   handled by this routine. This routine tries to convert the session
	   object into JSON with only a session ID using a standard JSON 
	   constructor. */ 
	protected String       buildJsonIdOnly() { 
		String        sessionIdStr = this.getSessionId();
		HDLmSession   tempSessionObj = new HDLmSession();
		tempSessionObj.setSessionId(sessionIdStr);
		Gson     gsonInstance = HDLmMain.gsonMain; 
		String   jsonString = gsonInstance.toJson(tempSessionObj); 
		return jsonString;
	}
	/* Build a session object (an object of this class) from
	   a JSON string. The caller provides the JSON string. 
	   This routine tries to convert the contents of the JSON
	   string to an object of this class. */
	protected static HDLmSession  buildSession(final String sessionJson) {
		if (sessionJson == null) {
			String   errorText = "Session JSON string reference passed to buildSession is null";
			throw new NullPointerException(errorText);		
		}
		Gson         gsonInstance = HDLmMain.gsonMain;
		HDLmSession  sessionObj = gsonInstance.fromJson(sessionJson, HDLmSession.class);
		return sessionObj;
	}
	/* This method check if a session ID is present in the cache or
	   not. True is returned if the session ID is present in the cache.
	   False is returned if the session ID is not present in the cache. 
	   The caller provides the session ID string as a simple string. */
	protected static boolean  checkSessionId(final String sessionIdStr) {
		/* Check if the session ID string passed by the caller is null */
    if (sessionIdStr == null) {
      String  errorText = "Session ID string passed to checkSessionId is null";
      throw new NullPointerException(errorText);
    }
    /* Get the session ID constant that is used to build the cache key */
    String   sessionIdName = HDLmDefines.getString("HDLMSESSIONID");
    if (sessionIdName == null) {
      String   errorFormat = "Define value for session ID name not found (%s)";
      String   errorText = String.format(errorFormat, "HDLMSESSIONID");
      HDLmAssertAction(false, errorText);		    	
    }
    /* Build the cache key from the session ID name and the session ID string */
    String  cacheKey = sessionIdName + " " + sessionIdStr;
    /* Check if the session ID is present in the cache or not */
    HDLmSession   sessionObj = HDLmSessionCache.getIfPresentFromCache(cacheKey);
    if (sessionObj == null) 
      return false;
    return true;
	}
	/* This method check if a session ID is present in the cache or
	   not. True is returned if the session ID is present in the cache.
	   False is returned if the session ID is not present in the cache. 
	   The caller provides the session ID string as a simple string. */
	protected static boolean  checkSessionJson(final String sessionIdJson) {
		/* Check if the session ID JSON string passed by the caller is null */
	 if (sessionIdJson == null) {
	   String  errorText = "Session ID JSON string passed to checkSessionJson is null";
	   throw new NullPointerException(errorText);
	 }
	 /* Get the session ID constant that is used to build the cache key */
	 String   sessionIdName = HDLmDefines.getString("HDLMSESSIONID");
	 if (sessionIdName == null) {
	   String   errorFormat = "Define value for session ID name not found (%s)";
	   String   errorText = String.format(errorFormat, "HDLMSESSIONID");
	   HDLmAssertAction(false, errorText);		    	
	 }
	 /* Get the session ID string from the JSON string */
 	 HDLmSession   tempSessionObj = HDLmSession.buildSession(sessionIdJson);
 	 String  sessionIdStr = tempSessionObj.getSessionId(); 
	 /* Build the cache key from the session ID name and the session ID string */
	 String  cacheKey = sessionIdName + " " + sessionIdStr;
	 /* Check if the session ID is present in the cache or not */
	 HDLmSession   sessionObj = HDLmSessionCache.getIfPresentFromCache(cacheKey);
	 if (sessionObj == null) 
	   return false;
	 return true;
	}
	/* This method copies all of the modification fields from an old
	   modification to the current one. This routine is used by copy
	   constructors as need be. */ 
	protected void         copySessionFields(final HDLmSession oldSession) {
		/* Check if the old session passed to this routine is null or not */
		if (oldSession == null) {
			String  errorText = "Old session reference used to build a new session is null";
			throw new NullPointerException(errorText);
		}
		/* copy all of the fields from the old session to the new session */
		this.index = oldSession.getIndex();
		this.parameters = oldSession.getParameters();
		this.sessionId = oldSession.getSessionId();
		this.useMode = oldSession.getUseMode();
	}	
  /* Get a session cache entry from the session cache. This routine 
     returns null if the entry is not found. */
	protected static synchronized HDLmSession  getFromCacheIfPresent(String sessionIdStr) {  	
		/* Check if the session ID string passed by the caller is null */
		if (sessionIdStr == null) {
			String  errorText = "Session ID string reference passed to getFromCacheIfPresent is null";
			throw new NullPointerException(errorText);
		}
		/* Get the session ID constant that is used to build the cache key */
		String   sessionIdName = HDLmDefines.getString("HDLMSESSIONID");
		if (sessionIdName == null) {
			String   errorFormat = "Define value for session ID name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSESSIONID");
			HDLmAssertAction(false, errorText);		    	
		}
    String  cacheKey = sessionIdName + " " + sessionIdStr;
    /* Try to get a session object from the cache. If a session
       object is not found, just return a null value to the caller.
       If a session object is found, make a copy of the session object.
       The copy is made so that the original session object in the
       cache is not modified if the session copy is modified. */ 
		HDLmSession   oldSession =  HDLmSessionCache.getIfPresentFromCache(cacheKey); 
		if (oldSession == null) 
			return null;
		HDLmSession   newSession = new HDLmSession(oldSession);
		return newSession;
	}  
	/* Get a few values */
	protected String       getIndex() {
		return index;
	}
	protected String       getParameters() {
		return parameters;
	}
	protected String       getSessionId() {
		return sessionId;
	}
	protected String       getUseMode() {
		return useMode;
	}
	/* Set or reset the index string */
	protected void         setIndex(final String newIndexStr) {
		if (newIndexStr == null) {
			String  errorText = "New index string is null";
			throw new NullPointerException(errorText);
		}
	  index = newIndexStr;
	}
	/* Set or reset the parameters string */
	protected void         setParameters(final String newParametersStr) {
		if (newParametersStr == null) {
			String  errorText = "New parameters string is null";
			throw new NullPointerException(errorText);
		}
		parameters = newParametersStr;
	}
	/* Set or reset the session ID string */
	protected void         setSessionId(final String newSessionId) {
		if (newSessionId == null) {
			String  errorText = "New session ID string is null";
			throw new NullPointerException(errorText);
		}
		sessionId = newSessionId;
	}
	/* Set or reset the use mode string */
	protected void         setUseMode(final String newUseMode) {
		if (newUseMode == null) {
			String  errorText = "New use mode string is null";
			throw new NullPointerException(errorText);
		}
		useMode = newUseMode;
	}
	/* Set the use mode string to a null value */
	protected void         setUseModeNull() {
		useMode = null;
	}
	/* The method below writes a set of session values to the 
	   log.The caller provides all of the values to be written. */ 
	protected static void  writeLog(final String hostName,
			                            final String timeStamp,
			                           final String sessionIdStr, 
			                           final String sessionStrValue) {
		/* Check if the host name string passed by the caller is null */
		if (hostName == null) {
		  String  errorText = "Host name string passed to writeLog is null";
	    throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
		  String  errorText = "Timestamp string passed to writeLog is null";
	    throw new NullPointerException(errorText);
		}
		/* Check if the session ID string passed by the caller is null */
		if (sessionIdStr == null) {
		  String  errorText = "Session ID string passed to writeLog is null";
	    throw new NullPointerException(errorText);
		}
		/* Check if the session JSON string passed by the caller is null */
		if (sessionStrValue == null) {
		  String  errorText = "Session JSON string passed to writeLog is null";
	    throw new NullPointerException(errorText);
		}	   
		/* Add the parameter information to the file. Note that we add 
		   the timestamp, but not the client string to the file. The client 
		   string is deemed to have PII in it and must not be logged. */
		String  infoLogData = String.format("{\"timestamp\":\"%s\"," +
		                                    "\"hostName\":\"%s\",",
		 	                                  timeStamp, hostName);	    		
		infoLogData += sessionStrValue.substring(1, sessionStrValue.length()-1);
		infoLogData += ",\"reason\":\"parameters\",\"weight\":\"1.0\"}";
		HDLmUtility.filePutAppendLineLogs(infoLogData, hostName); 
	}
}
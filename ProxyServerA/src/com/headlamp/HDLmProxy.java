package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * Class for supporting proxy definitions
 *
 * Each instance of this class describes one proxy definition. The instance has
 * information about how to pass requests from a proxy server to an actual 
 * server. For example, the instance has the name of the actual server that
 * will handle each request.  
 *
 * This class has a constructor that builds an instance from JSON describing an
 * instance. The class is designed so that the proxy definitions can be quickly
 * used. The conversion from JSON to a class instance may not be fast at all.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmProxy extends HDLmMod {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmProxy.class);
	/* Build the (initially) empty top node of the proxy definition tree. We 
	   would like to build the correct top node of the proxy definition tree 
	   here. However, this does not appear to be possible. */
  private static HDLmTree  proxyTreeTop = null;
  private static ArrayList<HDLmProxy>  proxyListTop = null; 
  private static HashMap<String, HDLmProxy>  proxyMapTop = null; 
	/* All instances of the HDLmProxy class have a standard set of fields */
	private HDLmProxyTypes     proxyType = HDLmProxyTypes.NONE;
  private HDLmProtocolTypes  backendType = HDLmProtocolTypes.NONE;
  private String             backendServer = null; 
  private String             secureServer = null; 
	/* This is one of the constructors for the proxy definition class.
	   It must be passed a JSON element that contains all of the details
	   of the proxy definition. */
	protected HDLmProxy(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		if (jsonElement == null) {
			String  errorText = "JSON element used to build proxy definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Show that we are handling a proxy definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PROXY;
		/* Set the error count to zero. The error count is incremented each time an
			 error is detected. If the final error count (for the current definition) is
			 greater than zero, the current definition object is disabled (the enabled
			 field is set false). Note that a reference is used below so that the error
			 count can be updated by the routines called using error count.*/
	  MutableInt   errors = new MutableInt(0);
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
		  HDLmAssertAction(false, "JSON element used to build proxy definition is JSON null");
	  }
	  JsonObject jsonObject = jsonElement.getAsJsonObject();
	  Set<String> jsonKeys = jsonObject.keySet();
	  /* Set the class instance variables */
	  String   newName;
	  newName = HDLmMod.modFieldString(editorType, 
	  		                             errors, 
	  		                             jsonObject, 
	  		                             jsonKeys, 
	  		                             "name", 
		  	                             HDLmWhiteSpace.WHITESPACENOTOK, 
		  	                             HDLmReportErrors.REPORTERRORS,
		  	                             HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (newName == null || StringUtils.isWhitespace(newName)) {
			HDLmAssertAction(false, "Name value not obtained from JSON element");
		}
		this.setName(newName);
		if (1 == 2)
		  LOG.info("HDLmProxy newName" + " - " + newName);
		/* Get the match string and check if it is really a regex. We treat 
		   the match string as a regex, if the first, second and last characters
		   are forward slashes. */
	  String  matchString  = HDLmMod.modFieldString(editorType, errors, 
		    	                                        jsonObject, jsonKeys, 
			                                            "match", 
			                                            HDLmWhiteSpace.WHITESPACENOTOK,
			                                            HDLmReportErrors.REPORTERRORS,
			                                            HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (1 == 2)
	    LOG.info("HDLmProxy matchString" + " - " + matchString);
	  /* Use the match String (if any) to build the match object. The  
	     match object supports all of the different types of matching. */ 
	  if (matchString != null) {
		  /* Check if the match string is valid or not. We don't want to try 
		     to build a match object using an invalid match string. */ 
		  String  valid = HDLmMatch.check(matchString);
		  if (valid != null) {
		  	String  errorText = valid;
			  HDLmMod.reportError(editorType, errors, 
					                  jsonObject, errorText, 
					                  36, HDLmReportErrors.REPORTERRORS);				
		  }
		  else {
		  	if (1 == 2)
		  	  LOG.info("HDLmProxy matchString" + " - " + matchString);
		    this.setPathValueMatch(new HDLmMatch(matchString));
		  }
	  }		
		/* Get a few fields from the JSON object */
		Boolean  enabledBoolean = HDLmMod.modFieldBoolean(editorType, errors, jsonObject, jsonKeys, "enabled");
		if (enabledBoolean == null) {
			HDLmAssertAction(false, "Enabled value not obtained from JSON element");
		}
		this.setEnabled(enabledBoolean);
		/* Try to get the extra information value from the JSON element */
		String extraInfo = HDLmMod.modFieldString(editorType, 
				                                      errors, 
				                                      jsonObject, 
				                                      jsonKeys, 
				                                      "extra", 
				                                      HDLmWhiteSpace.WHITESPACEOK, 
				                                      HDLmReportErrors.REPORTERRORS,
				                                      HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (extraInfo != null && !StringUtils.isWhitespace(extraInfo)) {
			this.setExtra(extraInfo);
		}		
		/* Get the proxy definition type */
		HDLmProxyTypes  newProxyType;
		newProxyType = HDLmProxy.proxyFieldProxyType(editorType, errors, jsonObject, jsonKeys, "type");
		if (newProxyType == HDLmProxyTypes.NONE) {
			HDLmAssertAction(false, "Proxy definition type not obtained from JSON element");
		} 
		this.setProxyType(newProxyType);
		/* Get the proxy definition backend protocol type */
		HDLmProtocolTypes  newProtocolType;
		newProtocolType = HDLmProxy.proxyFieldProtocolType(editorType, errors, jsonObject, 
				                                               jsonKeys, "backendType");
		if (newProtocolType == HDLmProtocolTypes.NONE) {
			HDLmAssertAction(false, "Proxy definition type not obtained from JSON element");
		} 
		this.setProtocolType(newProtocolType);
		/* Try to get the backend server host name */
	  String   newBackendServer;
	  newBackendServer = HDLmMod.modFieldString(editorType, 
	  		                                      errors, 
	  		                                      jsonObject, 
	  		                                      jsonKeys, 
	  		                                      "backendServer", 
		  	                                      HDLmWhiteSpace.WHITESPACENOTOK, 
		  	                                      HDLmReportErrors.REPORTERRORS,
		  	                                      HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (newBackendServer == null || StringUtils.isWhitespace(newBackendServer)) {
			HDLmAssertAction(false, "Backend server host name not obtained from JSON element");
		}
		this.setBackendServer(newBackendServer);	
		/* Try to get the secure server host name */
	  String   newSecureServer;
	  newSecureServer = HDLmMod.modFieldString(editorType, 
	  		                                     errors, 
	  		                                     jsonObject, 
	  		                                     jsonKeys, 
	  		                                     "secureServer", 
		  	                                     HDLmWhiteSpace.WHITESPACENOTOK, 
		  	                                     HDLmReportErrors.REPORTERRORS,
		  	                                     HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (newSecureServer == null || StringUtils.isWhitespace(newSecureServer)) {
			HDLmAssertAction(false, "Secure server host name not obtained from JSON element");
		}
		this.setSecureServer(newSecureServer);	
		/* Mark the current proxy defintion object as disabled if the error count
		   was greater than zero. This is actually done by setting the enabled
		   field to false. */
		if (errors.intValue() > 0) {
			this.setEnabled((Boolean) false);
	  }
	}
	/* This routine build the proxy definitions list. This list is used to find
     (if possible) a proxy definition for a host name. This list is built from
     the proxy definitions tree. */
	protected static ArrayList<HDLmProxy> buildProxyList(HDLmTree proxyTree) {
		if (proxyTree == null) {
			String  errorText = "Proxy definitions tree passed to buildProxyList is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<HDLmProxy>   newProxyList = new ArrayList<HDLmProxy>();
		/* Add each of company entries from the proxy definitions tree to the
		   proxy map */
		ArrayList<HDLmTree>  childList = proxyTree.getChildren();
		for (HDLmTree child : childList) {
			HDLmProxy proxyDetails = (HDLmProxy) child.getDetails();
			newProxyList.add(proxyDetails);
		}
		return newProxyList;
	}
	/* This routine build the proxy definitions map. This map is used to find
	   (if possible) a proxy definition for a host name. This map is built from
	   the proxy definitions tree. */
	protected static HashMap<String, HDLmProxy> buildProxyMap(HDLmTree proxyTree) {
		if (proxyTree == null) {
			String  errorText = "Proxy definitions tree passed to buildProxyMap is null";
			throw new NullPointerException(errorText);
		}
		HashMap<String, HDLmProxy>   newProxyMap = new HashMap<String, HDLmProxy>();
		/* Add each of company entries from the proxy definitions tree to the
		   proxy map */
		ArrayList<HDLmTree>  childList = proxyTree.getChildren();
		for (HDLmTree child : childList) {
			HDLmProxy proxyDetails = (HDLmProxy) child.getDetails();
			String  companyName = proxyDetails.getName();
			newProxyMap.put(companyName, proxyDetails);
		}
		return newProxyMap;
	}	 
	/* Get the backend server name from the current proxy definition */
	protected String getBackendName()  {
		return this.backendServer;
	}
	/* Get the match object reference from the current proxy definition */
	protected HDLmMatch getMatch()  {
		return this.getPathValueMatch();
	}
	/* Get the proxy protocol type from the current proxy definition */
	protected HDLmProtocolTypes  getProtocolType() {
		return this.backendType;
	}
	/* Get the proxy list top reference and return it to the caller */
	protected static ArrayList<HDLmProxy>  getProxyListTop() {
		return HDLmProxy.proxyListTop;
	}
	/* Get the proxy map top reference and return it to the caller */
	protected static HashMap<String, HDLmProxy> getProxyMapTop() {
		return HDLmProxy.proxyMapTop;
	}
	/* Get the proxy tree top reference and return it to the caller */
	protected static HDLmTree  getProxyTreeTop() {
		return HDLmProxy.proxyTreeTop;
	}
	/* Get the proxy type from the current proxy definition */
	protected HDLmProxyTypes getProxyType() {
		return this.proxyType;
	}
	/* Get the secure server name from the current proxy definition */
	protected String getSecureServerName()  {
		return this.secureServer;
	}
	/* This routine gets the proxy definition for a proxy name. Null is 
	   returned if no proxy definition can be found. */ 
	protected static HDLmProxy getProxyDefinitionFromMap(String proxyName,
			                                                 HashMap<String, HDLmProxy> proxyMap) {
		if (proxyName == null) {
			String  errorText = "Proxy name passed to getProxyDefinition is null";
			throw new NullPointerException(errorText);
		}
		/* Make sure the proxy map reference was passed by the caller */
		if (proxyMap == null) {
			String  errorText = "Proxy map reference passed to getProxyDefinition is null";
			throw new NullPointerException(errorText);
		}
		return  proxyMap.get(proxyName);
	}	
	/* This routine gets the proxy definition for a proxy name. Null is 
     returned if no proxy definition can be found. */ 
	protected static HDLmProxy getProxyDefinitionMatch(String proxyName,
			                                               ArrayList<HDLmProxy> proxyList) {
		if (proxyName == null) {
			String  errorText = "Proxy name passed to getProxyDefinition is null";
			throw new NullPointerException(errorText);
		}
		/* Make sure the proxy list reference was passed by the caller */
		if (proxyList == null) {
			String  errorText = "Proxy list reference passed to getProxyDefinition is null";
			throw new NullPointerException(errorText);
		}

		/* Check each of the proxy definitions looking for a match */
		for (HDLmProxy proxyEntry : proxyList) {
			/*
			LOG.info("getProxyDefinitionMatch proxyName" + " - " + proxyName);
			*/
			HDLmMatch   matchProxy = proxyEntry.getMatch();
			/*
			LOG.info("getProxyDefinitionMatch matchProxy" + " - " + matchProxy);
			*/
			boolean   matchFound = matchProxy.match(proxyName);
			/*
			LOG.info("getProxyDefinitionMatch proxyName" + " - " + proxyName);
			LOG.info("getProxyDefinitionMatch matchFound" + " - " + matchFound);
			*/
			if (matchFound) {
				/*
				LOG.info("getProxyDefinitionMatch name" + " - " + proxyEntry.getName());
				*/
				return proxyEntry;
			}
		}
		return null;
	}	
	/* Try to access a field in the JSON used to build the current proxy definition 
		 object. Report an error if the field is not found. If an error is reported,
		 the error count is also incremented. The return value from this function is
		 the value of the field, if the field is found. */
	protected static HDLmProtocolTypes proxyFieldProtocolType(HDLmEditorTypes editorType,
			                                                      MutableInt errors, 
			                                                      JsonObject jsonObject, 
			                                                      Set<String> jsonKeys,
			                                                      String name) {
		return proxyFieldProtocolType(editorType, errors, 
				                          jsonObject, jsonKeys, 
				                          name, HDLmReportErrors.REPORTERRORS);
	}
	protected static HDLmProtocolTypes proxyFieldProtocolType(HDLmEditorTypes editorType,
			                                                      MutableInt errors, 
			                                                      JsonObject jsonObject, 
			                                                      Set<String> jsonKeys,
			                                                      String name, 
			                                                      HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to proxyFieldProtocolType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to proxyFieldProtocolType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to proxyFieldProtocolType is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to proxyFieldProtocolType is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		HDLmProtocolTypes  protocolType = HDLmProtocolTypes.NONE;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			String localString;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				localString = jsonElement.getAsString();
				protocolType = HDLmProtocolTypes.valueOfString(localString);
				if (protocolType == HDLmProtocolTypes.NONE) {
					String value = localString;
					HDLmMod.reportErrorValue(editorType, errors, jsonObject, 
							                     name, value, 
							                     "Proxy definition JSON invalid field", 
							                     4, reportErrors);
				}
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, jsonObject, 
					                       name, "Proxy definition JSON missing field", 
					                       3, reportErrors);
		return protocolType;
	}
	/* Try to access a field in the JSON used to build the current proxy definition 
		 object. Report an error if the field is not found. If an error is reported,
		 the error count is also incremented. The return value from this function is
		 the value of the field, if the field is found. */
	protected static HDLmProxyTypes proxyFieldProxyType(HDLmEditorTypes editorType,
			                                                MutableInt errors, 
			                                                JsonObject jsonObject, 
			                                                Set<String> jsonKeys,
			                                                String name) {
		return proxyFieldProxyType(editorType, errors, jsonObject, jsonKeys, name, HDLmReportErrors.REPORTERRORS);
	}
	protected static HDLmProxyTypes proxyFieldProxyType(HDLmEditorTypes editorType,
			                                                MutableInt errors, 
			                                                JsonObject jsonObject, 
			                                                Set<String> jsonKeys,
			                                                String name, 
			                                                HDLmReportErrors reportErrors) {
		if (errors == null) {
			String  errorText = "Mutable int for errors passed to proxyFieldProxyType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonObject == null) {
			String  errorText = "JSON object passed to proxyFieldProxyType is null";
			throw new NullPointerException(errorText);
		}
		if (jsonKeys == null) {
			String  errorText = "Set of keys passed to proxyFieldProxyType is null";
			throw new NullPointerException(errorText);
		}
		if (name == null) {
			String  errorText = "Name string passed to proxyFieldProxyType is null";
			throw new NullPointerException(errorText);
		}
		boolean fieldFound = false;
		HDLmProxyTypes   proxyType = HDLmProxyTypes.NONE;
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(name)) {
			fieldFound = true;
			String localString;
			JsonElement jsonElement = jsonObject.get(name);
			if (!jsonElement.isJsonNull()) {
				localString = jsonElement.getAsString();
				proxyType = HDLmProxyTypes.valueOfString(localString);
				if (proxyType == HDLmProxyTypes.NONE) {
					String value = localString;
					HDLmMod.reportErrorValue(editorType, errors, jsonObject, 
							                     name, value, 
							                     "Proxy definition JSON invalid field", 
							                     4, reportErrors);
				}
			}
		}
		/* Since we do not have the field in the JSON, report an error */
		if (fieldFound == false)
			HDLmMod.reportErrorNoValue(editorType, errors, jsonObject, 
					                       name, "Proxy definition JSON missing field", 
					                       3, reportErrors);
		return proxyType;
	}
	/* Set the backend server host name value */
	protected void setBackendServer(String newHostName) {
		this.backendServer = newHostName;
	}
	/* Set the proxy definition protocol type value */
	protected void setProtocolType(HDLmProtocolTypes newProtocolType) {
		this.backendType = newProtocolType;
	}
	/* Set the proxy definitions list top value */
	protected static void setProxyListTop(ArrayList<HDLmProxy> newListTop) {
		if (newListTop == null) {
		  String  errorText = "New list top value is null";
		  throw new NullPointerException(errorText);
		}
		HDLmProxy.proxyListTop = newListTop;
	}	
	/* Set the proxy definitions list top value to a null value */
	protected static void setProxyListTopNull() {
		HDLmProxy.proxyListTop = null;
	}	
	/* Set the proxy definitions map top value */
	protected static void setProxyMapTop(HashMap<String, HDLmProxy> newMapTop) {
		if (newMapTop == null) {
		  String  errorText = "New map top value is null";
		  throw new NullPointerException(errorText);
		}
		HDLmProxy.proxyMapTop = newMapTop;
	}	
	/* Set the proxy definitions map top value to a null value*/
	protected static void setProxyMapTopNull() {
		HDLmProxy.proxyMapTop = null;
	}	
	/* Set the proxy definitions tree top value */
	protected static void setProxyTreeTop(HDLmTree newProxyTop) {
		if (newProxyTop == null) {
		  String  errorText = "New tree top value is null";
		  throw new NullPointerException(errorText);
		}
		HDLmProxy.proxyTreeTop = newProxyTop;
	}
	/* Set the proxy definitions tree top value to a null value*/
	protected static void setProxyTreeTopNull() {
		HDLmProxy.proxyTreeTop = null;
	}	
	/* Set the proxy type value */
	protected void setProxyType(HDLmProxyTypes newProxyType) {
		this.proxyType = newProxyType;
	}
	
	/* Set the secure server host name value */
	protected void setSecureServer(String newHostName) {
		this.secureServer = newHostName;
	}
}
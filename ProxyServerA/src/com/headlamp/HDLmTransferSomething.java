package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmTransferSomething short summary.
 *
 * HDLmTransferSomething description.
 *
 * @version 1.0
 * @author Peter
 */
/**
 * It is generally true that a specific value, means a specific
 * value. '*' (without the quotes) means everything and a null
 * value means unspecified. 
 * 
 * The transfer process has (at least) six stages. They are
 * 
 * 1. The JSON keywords are evaluated and checked. The associated
 *    values are stored in a control block for easy access later.
 * 2. Database rows from the 'from' system are selected based on
 *    the contents of the transfer control block. The number of 
 *    rows may be zero. This is not an error condition.
 * 3. Rows are modified with all of the 'to information. Obviously,
 *    the node path is changed to use the 'to' information. Not so
 *    obviously, the value of the name field is changed in some 
 *    cases. 
 * 4. Rows are picked for deletion from the 'to' system based on 
 *    the rows that were picked from the 'from' system with all 
 *    of the changes made. The number of database rows to be
 *    deleted may (or may not) be zero. If the number is zero,
 *    then this is not an error condition.
 * 5. Rows are deleted from the 'to' system as need be. This stop
 *    is required to avoid duplicate rows.
 * 6. Rows are inserted into the 'to' system. These are the rows 
 *    that were picked from the 'from' system earlier. 
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmTransferSomething {
	/* The next statement initializes logging to some degree. Note that having the
     slf4j jars and the log4j jars in the classpath also plays some role in
     logging initialization. */
private static final Logger LOG = LoggerFactory.getLogger(HDLmTransferSomething.class);
	/* This class is used to describe a transfer something request. 
	   The actual request to transfer something is sent using JSON.
	   However, the raw JSON is somewhat hard to handle. An 
	   instance of this class is created and passed around 
	   to make the handling of a transfer something request 
	   easier. */
  private String  fromSystemName = null;
  private String  toSystemName = null;
	private String  fromDomainName = null;
	private String  toDomainName = null;
	private String  fromDivisionName = null;
	private String  toDivisionName = null;
	private String  fromSiteName = null;
	private String  toSiteName = null;
	private String  fromRuleName = null;
	private String  toRuleName = null;	
	/* This constructor builds an empty transfer request object */
	protected HDLmTransferSomething() {}
	/* This is the standard (and major) constructor for this class. Build a something
	   transfer request. The caller provides the 'from' and 'to' information. The
	   information is checked and stored in a request. */ 
	protected HDLmTransferSomething(final String fromSystemValue,
                                  final String toSystemValue,
	                                final String fromDomainNameValue,
					                        final String toDomainNameValue, 
					                        final String fromDivisionNameValue, 
					                        final String toDivisionNameValue, 
					                        final String fromSiteNameValue, 
					                        final String toSiteNameValue,
					                        final String fromRuleNameValue, 
					                        final String toRuleNameValue) throws InstantiationException {
		/* Check a few values passed by the caller */
		if (fromSystemValue == null) {
			String  errorText = "From system value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (toSystemValue == null) {
			String  errorText = "To system value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (fromDomainNameValue == null) {
			String  errorText = "From domain name value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (toDomainNameValue == null && false) {
			String  errorText = "To domain name value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (fromDivisionNameValue == null) {
			String  errorText = "From division name value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* This value really can be null. Null means that the division name will not be 
       changed. This is true in most cases. */
		if (toDivisionNameValue == null && false) {
			String  errorText = "To division name value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (fromSiteNameValue == null) {
			String  errorText = "From site name value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* This value really can be null. Null means that the site name will not be 
	     changed. This is true in most cases. */
		if (toSiteNameValue == null && false) {
			String  errorText = "To site name value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (fromRuleNameValue == null) {
			String  errorText = "From rule name value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* This value really can be null. Null means that the rule name will not be 
		   changed. This is true in most cases. */ 
		if (toRuleNameValue == null && false) {
			String  errorText = "To rule name value passed to transfer object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check the 'from' and 'to' (without the quotes) system values */
		String  errorMsg = null;
		errorMsg = checkSystemValue(fromSystemValue);
		if (errorMsg != null)
			throw new InstantiationException(errorMsg);
		errorMsg = checkSystemValue(toSystemValue);
		if (errorMsg != null)
			throw new InstantiationException(errorMsg);
		if (fromSystemValue.equals(toSystemValue)) {
			errorMsg = "From and to systems are equal";
			throw new InstantiationException(errorMsg);
		}
		/* Check the from company name. The company name is really a
		   domain name. */
		errorMsg = HDLmHttp.checkDomainName(fromDomainNameValue);
		if (errorMsg != null)
			throw new InstantiationException(errorMsg);
		/* The from division name can be set to a specific value (which
		   will generally be the case) or it can be set to an asterisk.
		   If the from division name is set to an asterisk, it means that
		   all from division names are OK. */ 
		errorMsg = HDLmTransferSomething.checkValue("Division", fromDivisionNameValue);
		if (errorMsg != null)
			throw new InstantiationException(errorMsg);	
		/* The from site name can be set to a specific value (which
	     will generally be the case) or it can be set to an asterisk.
	     If the from site name is set to an asterisk, it means that
	     all from site names are OK. */ 
	  errorMsg = HDLmTransferSomething.checkValue("Site", fromSiteNameValue);
	  if (errorMsg != null)
	  	throw new InstantiationException(errorMsg);
		/* The from rule name can be set to a specific value (which
	     will generally be the case) or it can be set to an asterisk.
	     If the from rule name is set to an asterisk, it means that
	     all from rule names are OK. */ 
	  errorMsg = HDLmTransferSomething.checkValue("Rule", fromRuleNameValue);
	  if (errorMsg != null)
	 	  throw new InstantiationException(errorMsg);
		/* Check the to company name (witch may be a null value). The 
	     company name is really a domain name. */
	  if (toDomainNameValue != null) {
		  errorMsg = HDLmHttp.checkDomainName(toDomainNameValue);
		  if (errorMsg != null)
			  throw new InstantiationException(errorMsg);			
	  }
		/* The to division name will generally be a null value. If
		   it is not, then we need to check the value. */
		if (toDivisionNameValue != null) {
			errorMsg = HDLmTransferSomething.checkValueNotAsterisk("Division", toDivisionNameValue);
		  if (errorMsg != null)
			  throw new InstantiationException(errorMsg);	
		}
		/* The to site name will generally be a null value. If
		   it is not, then we need to check the value. */
		if (toSiteNameValue != null) {
		  errorMsg = HDLmTransferSomething.checkValueNotAsterisk("Site", toSiteNameValue);
		  if (errorMsg != null)
			  throw new InstantiationException(errorMsg);	
		}
	  /* The to rule name will generally be a null value. If
	     it is not, then we need to check the value. */
	  if (toRuleNameValue != null) {
		  errorMsg = HDLmTransferSomething.checkValueNotAsterisk("Rule", toRuleNameValue);	
		  if (errorMsg != null)
			  throw new InstantiationException(errorMsg);	
	  }
		/* Store a few values */
	  this.fromSystemName = fromSystemValue;
	  this.toSystemName = toSystemValue;
		this.fromDomainName = fromDomainNameValue;
		this.toDomainName = toDomainNameValue;
		this.fromDivisionName = fromDivisionNameValue;
		this.toDivisionName = toDivisionNameValue;
		this.fromSiteName = fromSiteNameValue;
		this.toSiteName = toSiteNameValue;
		this.fromRuleName = fromRuleNameValue;
		this.toRuleName = toRuleNameValue;
	}
	/* This routine is invoked to build a transfer request. All of the details in 
	   JSON are stored in the object passed by the caller. */
	protected static String  buildTransferRequest(JsonElement jsonElements, 
			                                          HDLmTransferSomething transferObj) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElements == null) {
			String  errorText = "JSON elements passed to buildTransferRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the transfer object passed by the caller is null */
		if (transferObj == null) {
			String  errorText = "Empty transfer object passed to buildTransferRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Set the initial error message */
		String  errorMessage = null;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
		  /* Get a few required values */
		  String  fromSystemName = HDLmJson.getJsonString(jsonElements, "fromSystem");
      errorMessage = checkSystemValue(fromSystemName);
      if (errorMessage != null)
      	break;
      transferObj.fromSystemName = fromSystemName;
		  String  toSystemName = HDLmJson.getJsonString(jsonElements, "toSystem");
      errorMessage = checkSystemValue(toSystemName);
      if (errorMessage != null)
      	break;
      transferObj.toSystemName = toSystemName;
      /* Try to get the from domain name. This is required. */
		  String  fromDomainName = HDLmJson.getJsonString(jsonElements, "fromDomain");
      errorMessage = HDLmHttp.checkDomainName(fromDomainName);
      if (errorMessage != null)
      	break;
      transferObj.fromDomainName = fromDomainName;
      /* Get a few more from values */
		  String  fromDivisionName = HDLmJson.getJsonString(jsonElements, "fromDivision");
		  if (fromDivisionName == null)
		  	fromDivisionName = "*";
      errorMessage = checkValue("division", fromDivisionName);
      if (errorMessage != null)
      	break;
      transferObj.fromDivisionName = fromDivisionName;
		  String  fromSiteName = HDLmJson.getJsonString(jsonElements, "fromSite");
		  if (fromSiteName == null)
		  	fromSiteName = "*";
      errorMessage = checkValue("Site", fromSiteName);
      if (errorMessage != null)
      	break;
      transferObj.fromSiteName = fromSiteName;
		  String  fromRuleName = HDLmJson.getJsonString(jsonElements, "fromRule");
		  if (fromRuleName == null)
		  	fromRuleName = "*";
      errorMessage = checkValue("rule", fromRuleName);
      if (errorMessage != null)
      	break;
      transferObj.fromRuleName = fromRuleName;
      /* Try to get the to domain name. This is not required. */
		  String  toDomainName = HDLmJson.getJsonString(jsonElements, "toDomain");
		  if (toDomainName != null) {
        errorMessage = HDLmHttp.checkDomainName(toDomainName);
        if (errorMessage != null)
      	  break;
        transferObj.toDomainName = toDomainName;
		  }
		  /* Get a few more to values. These values are not required. */
		  String  toDivisionName = HDLmJson.getJsonString(jsonElements, "toDivision");
		  if (toDivisionName != null) { 
        errorMessage = checkValueNotAsterisk("division", toDivisionName);
        if (errorMessage != null)
      	  break;
        transferObj.toDivisionName = toDivisionName;
		  }
		  String  toSiteName = HDLmJson.getJsonString(jsonElements, "toSite");
		  if (toSiteName != null) {
        errorMessage = checkValueNotAsterisk("site", toSiteName);
        if (errorMessage != null)
      	  break;
        transferObj.toSiteName = toSiteName;
		  }
		  String  toRuleName = HDLmJson.getJsonString(jsonElements, "toRule");
		  if (toRuleName != null) {
        errorMessage = checkValueNotAsterisk("rule", toRuleName);
        if (errorMessage != null)
      	  break;
        transferObj.toRuleName = toRuleName;
		  }      
		  /* Check if the 'from' and 'to' system value are the same.
		     This can be true and is not an error condition. */ 
		  if (transferObj.fromSystemName.equals(transferObj.toSystemName)) {
		  	/* Make sure the toDomain value is specified */
		  	if (transferObj.toDomainName == null) {
		  		errorMessage = "From/to system names are the same, to domain name must be specified";
		  		break;		  		
		  	}
		  	/* Make sure that the from/to domain names are not the same */
		  	if (transferObj.fromDomainName.equals(transferObj.toDomainName)) {
		  		errorMessage = "From/to domain names are the same, domain names must not match";
		  		break;
		  	}
		  }
      break;
		}		
		return errorMessage;
	}			
	/* Check a system name passed by the caller. The system name
	   can not be null and must be one defined values. An error
	   message is returned to the caller, if an error is found.
	   If no error is found then a null value is returned to the
	   caller. */
	protected static String  checkSystemValue(final String systemValue)	{
		/* Set the initial error message */
		String  errorMessage = null;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the value passed by the caller is null */
			if (systemValue == null) {
				errorMessage = "System value is null";
				break;			
			}
			/* Get a few define values */ 
			String  prodStr = HDLmDefines.getString("HDLMSYSTEMPROD");
			if (prodStr == null) {
				String   errorFormat = "Define value for production system type not found (%s)";
				String   errorText = String.format(errorFormat, "HDLMSYSTEMPROD");
				HDLmAssertAction(false, errorText);		    	
			}
			String  testStr = HDLmDefines.getString("HDLMSYSTEMTEST");
			if (testStr == null) {
				String   errorFormat = "Define value for test system type not found (%s)";
				String   errorText = String.format(errorFormat, "HDLMSYSTEMTEST");
				HDLmAssertAction(false, errorText);		    	
			}
			/* Get the lengths of the allowed system values */
			int   prodStrLen = prodStr.length();
			int   testStrLen = testStr.length();
			/* Check if the length of the system value is one of the two 
			   allowed values */
			int   systemValueLen = systemValue.length();
			if (systemValueLen != prodStrLen &&
					systemValueLen != testStrLen) {
				String   errorFormat = "System value length (%d) is invalid"; 
				String   errorText = String.format(errorFormat, systemValueLen);
				errorMessage = errorText; 
				break;	
			}
			/* Check if the system value is one of the two allowed values */
			if (!systemValue.equals(prodStr) &&
					!systemValue.equals(testStr)) {
				String   errorFormat = "System value (%s) is invalid"; 
				String   errorText = String.format(errorFormat, systemValue);
				errorMessage = errorText; 
				break;	
			}
			/* No errors have been found */
			break;
		} 
		/* Return the final error message to the caller */
		return errorMessage;
	}
	/* Check a value passed by the caller. The value can not be null
     or zero-length. */
	protected static String  checkValue(final String name,
			                                final String value) {
		/* Set the initial error message */
		String  errorMessage = null;
		String  ucName = HDLmString.ucFirst(name);
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the value passed by the caller is null */
			if (value == null) { 
				String  errorFormat = "%s value passed to checkValue is null";						
				String  errorText = String.format(errorFormat, ucName);
				errorMessage = errorText; 
				break;			
			}
		  /* Get and check the length of the value */
		  int   valueLen = value.length();
		  if (valueLen == 0) {
			  String  errorFormat = "%s value passed to checkValue is zero-length";
				String  errorText = String.format(errorFormat, ucName);
				errorMessage = errorText; 
				break;
		  }
		  break;
		}
		return errorMessage;
	}
	/* Check a value passed by the caller. The value can not be null
	   zero-length, or equal to an asterisk. */
	protected static String  checkValueNotAsterisk(final String name,
			                                           final String value) {
		/* Set the initial error message */
		String  errorMessage = null;
		String  ucName = HDLmString.ucFirst(name);
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the value passed by the caller is null */
			if (value == null) { 
				String  errorFormat = "%s value passed to checkValueNotAsterisk is null";						
				String  errorText = String.format(errorFormat, ucName);
				errorMessage = errorText; 
				break;			
			}
		  /* Get and check the length of the value */
		  int   valueLen = value.length();
		  if (valueLen == 0) {
			  String  errorFormat = "%s value passed to checkValueNotAsterisk is zero-length";
				String  errorText = String.format(errorFormat, ucName);
				errorMessage = errorText; 
				break;
		  }
		  /* Make sure the value does not start with an asterisk */
		  char  firstChar = value.charAt(0);
		  if (firstChar == '*') {
				String  errorFormat = "%s value passed to checkValueNotAsterisk starts with an asterisk";						
				String  errorText = String.format(errorFormat, ucName);
				errorMessage = errorText; 
				break;	
		  }
		  break;
		}
		return errorMessage;		
	}  
	/* This routine is invoked to run a transfer request. All of the details   
	   are stored in the object passed by the caller. */
	protected static ArrayList<HDLmDatabaseRow>  filterRowList(HDLmTransferSomething transferObj,
			                                                       ArrayList<HDLmDatabaseRow> inputRows) { 
		/* Check if the transfer object passed by the caller is null */
		if (transferObj == null) {
			String  errorText = "Transfer object passed to filterRowList is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input row list passed by the caller is null */
		if (inputRows == null) {
			String  errorText = "Input row list passed to filterRowList is null";
			throw new NullPointerException(errorText);
		}
		/* Allocate the row list that just contains the rows that we want */
		ArrayList<HDLmDatabaseRow>  outputRows = new ArrayList<HDLmDatabaseRow>();
		if (outputRows == null) {
		  String   errorText = "Output row list is null in filterRowList";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* We only want the some of the rows */
		for(HDLmDatabaseRow currentRow : inputRows) {
			/* Try to get the information value from the row */
			String  infoStr = currentRow.getInfo();
			if (infoStr == null)
				continue;
    	/* Try to get the node path array list from the 'info' string.
         This should always be possible. */ 
      ArrayList<String>   infoNodePath = HDLmTree.getNodePath(infoStr);
      int                 infoNodePathLen = infoNodePath.size();
			/* Skip some rows */				
			if (infoNodePathLen < 3)
				continue;
			if (infoNodePathLen >= 3) {
				String  nodePathCompany = infoNodePath.get(2);
				if (!transferObj.fromDomainName.equals("*") &&
						!transferObj.fromDomainName.equals(nodePathCompany)) 
					continue;
			}
			if (infoNodePathLen >= 5) {
				String  nodePathDivision = infoNodePath.get(4);
				if (!transferObj.fromDivisionName.equals("*") &&
						!transferObj.fromDivisionName.equals(nodePathDivision)) 
					continue;
			}
			if (infoNodePathLen >= 6) {
				String  nodePathSite = infoNodePath.get(5);
				if (!transferObj.fromSiteName.equals("*") &&
						!transferObj.fromSiteName.equals(nodePathSite)) 
					continue;
			}
			if (infoNodePathLen >= 7) {
				String  nodePathRule = infoNodePath.get(6);
				if (!transferObj.fromRuleName.equals("*") &&
						!transferObj.fromRuleName.equals(nodePathRule)) 
					continue;
			}
			/* The current row passed all of the tests. Add the current
			   row to row list. */ 
			outputRows.add(currentRow);
		}
		return outputRows;
	}
  /* Get a few values */
	protected String getFromDomainName() {
		return fromDomainName;
	}
	/* This routine is invoked to insert each of the rows passed by
	   the caller in the database. Any matching rows are deleted,  
	   first. All of the details are passed by the caller. */  
	protected static HDLmResponse  insertRowList(HDLmTransferSomething transferObj,
			                                         ArrayList<HDLmDatabaseRow> insertRows) { 
		/* Check if the transfer object passed by the caller is null */
		if (transferObj == null) {
			String  errorText = "Transfer object passed to insertRowList is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the row list to be inserted, passed by the caller is null */
		if (insertRows == null) {
			String  errorText = "Input row list passed to insertRowList is null";
			throw new NullPointerException(errorText);
		}
		/* Set the initial error message */
		String  errorMessage = null;
		/* A map is used to keep track of all the updated node paths */
		HashMap   nodePathMap = new HashMap();
		/* Check if the node path hash map was properly allocated */ 
		if (nodePathMap == null) {
			String  errorText = "Node path hash map was not allocated in insertRowList";
			HDLmAssertAction(false, errorText);
		}
		/* The change number is used much later in the routine. It is initialized 
		   to a value that will never be used. */
		int   changeNumber = -1;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Process all of the rows to be inserted  */
			for(HDLmDatabaseRow currentRow : insertRows) {
				/* We need to fix the domain name / company name for the row
				   that will be inserted later */
				if (transferObj.toDomainName != null)
					currentRow.setCompany(transferObj.toDomainName);
				/* Try to get the information value from the row */
				String  infoStr = currentRow.getInfo();
				if (infoStr == null)
					continue;				
	    	/* Try to get the node path array list from the 'info' string.
   	       This should always be possible. */ 
   	    ArrayList<String>   infoNodePath = HDLmTree.getNodePath(infoStr);
   	    int                 infoNodePathLen = infoNodePath.size();
			  /* Update some of the values in the insert row list entry */
				if (transferObj.toDomainName != null &&
						infoNodePathLen >= 3)
					infoNodePath.set(2, transferObj.toDomainName);
				if (transferObj.toDivisionName != null &&
						infoNodePathLen >= 5)
					infoNodePath.set(4, transferObj.toDivisionName);
				if (transferObj.toSiteName != null &&
						infoNodePathLen >= 6)
					infoNodePath.set(5, transferObj.toSiteName);
				if (transferObj.toRuleName != null &&
						infoNodePathLen >= 7)
					infoNodePath.set(6, transferObj.toRuleName); 
				/* Add the updated (possibly) to the hash map. This map
				   is used later to identify rows to be deleted. */ 
				nodePathMap.put(infoNodePath, true);
				/* Convert the updated (possibly) node path back to JSON */ 
				JsonArray   newNodePathJson = new JsonArray();
	  		/* Check if the JSON node path array was properly allocated */ 
				if (newNodePathJson == null) {
					String  errorText = "JSON node path array was not allocated in insertRowList";
					HDLmAssertAction(false, errorText);
				}
				for(String infoNodePathEntry : infoNodePath)
					newNodePathJson.add(infoNodePathEntry);
				/* Try to convert the information JSON string to a JSON object. If this fails,
			     then we do not have a string than can be converted to a JSON object. If this
			     works, then we do have string than can be converted to a JSON object. */
	  	  JsonParser    parser = new JsonParser();		
	  		/* Check if the JSON parser object was properly allocated */ 
				if (parser == null) {
					String  errorText = "JSON parser object was not allocated in insertRowList";
					HDLmAssertAction(false, errorText);
				}
		    JsonElement   infoJsonElement = null; 
		    try {
			    infoJsonElement = parser.parse(infoStr);
	   	  }
				catch (Exception exception) {
				  if (infoStr != null)
				    LOG.info("Information string - " + infoStr);
					LOG.info("Exception while executing parse of information string");
					LOG.info(exception.getMessage(), exception);
					HDLmEvent.eventOccurred("Exception");
					break;
				}	
				/* Update (possibly) the information JSON with the new
				   node path */
				HDLmJson.removeJsonKey(infoJsonElement, "nodePath");
				HDLmJson.addArrayToJsonObject((JsonObject) infoJsonElement, "nodePath", newNodePathJson);
				/* Get the type string for the current row */
				String          rowTypeStr = HDLmJson.getJsonString(infoJsonElement, "type");
				HDLmTreeTypes   rowType = HDLmTreeTypes.valueOfString(rowTypeStr);
				/* Get the details (HDLmMod) from the current information string */
				JsonElement   modDetails = HDLmJson.getJsonValue(infoJsonElement, "details");
				/* In some cases, set the name field in the details */
				if (transferObj.toDomainName != null &&
						rowType == HDLmTreeTypes.COMPANY)
		  		HDLmJson.setJsonString(modDetails, "name", transferObj.toDomainName);
				if (transferObj.toDivisionName != null &&
						rowType == HDLmTreeTypes.DIVISION)
		  		HDLmJson.setJsonString(modDetails, "name", transferObj.toDivisionName);
				if (transferObj.toSiteName != null &&
						rowType == HDLmTreeTypes.SITE)
		  		HDLmJson.setJsonString(modDetails, "name", transferObj.toSiteName);
				if (transferObj.toRuleName != null &&
						rowType == HDLmTreeTypes.MOD)
		  		HDLmJson.setJsonString(modDetails, "name", transferObj.toRuleName);
				/* Convert the JSON element tree back to a string and store
				   the string back in the current row */				 
		    Gson    gsonInstance = HDLmMain.gsonMain; 
		    infoStr = gsonInstance.toJson(infoJsonElement);
		    currentRow.setInfo(infoStr);
		  }	 
			/* Get all of the rows for the current company from database. 
			   Some of these rows will be deleted later. */ 
			String  contentStr = HDLmUtility.getContentString();
			String  companyName = null;
			if (transferObj.toDomainName != null)
				companyName = transferObj.toDomainName;
			else
				companyName = transferObj.fromDomainName;
			if (companyName == null) {
				String  errorText = "Company name is not set in insertRowList";
				HDLmAssertAction(false, errorText);	
			} 
			/* Create a new row array list for the rows that will
			   be deleted */
			ArrayList<HDLmDatabaseRow>  deletedRows = new ArrayList<HDLmDatabaseRow>();
			/* Check if the rows to be deleted object was properly allocated */ 
			if (deletedRows == null) {
				String  errorText = "Rows to be deleted object was not allocated in insertRowList";
				HDLmAssertAction(false, errorText);
			}
			/* Get all of the rows for the current company */
			ArrayList<HDLmDatabaseRow>  companyRows = HDLmDatabase.getTableRowsCompanySystem(contentStr, 
					                                                                             companyName,
					                                                                             transferObj.toSystemName);
			/* Process all of the company rows */
			for(HDLmDatabaseRow currentRow : companyRows) {
				/* Try to get the information value from the row */
				String  infoStr = currentRow.getInfo();
				if (infoStr == null)
					continue;
	    	/* Try to get the node path array list from the 'info' string.
   	       This should always be possible. */ 
   	    ArrayList<String>   infoNodePathRow = HDLmTree.getNodePath(infoStr);				
				/* Check if the node path of the current database row is
				   in the map. If so, add the database row to the list of
				   rows to be deleted. */
				if (nodePathMap.containsKey(infoNodePathRow))
					deletedRows.add(currentRow);					
			}
			/* Delete some of the rows */			 
			if (deletedRows.size() > 0)
			  HDLmDatabase.deleteTableRowsSystem(deletedRows, transferObj.toSystemName);			 
			/* Build the set of columns to be inserted */
			HashSet<String>   insertRowKeys = new HashSet<String>();
			/* Check if the insert row keys set was properly allocated */ 
			if (insertRowKeys == null) {
				String  errorText = "Insert row keys set was not allocated in insertRowList";
				HDLmAssertAction(false, errorText);
			}					
			insertRowKeys.add("content");
			insertRowKeys.add("info");
			insertRowKeys.add("name");
			insertRowKeys.add("company");
			insertRowKeys.add("report");
		  /* Create an ArrayList variable for the row Ids obtained below */
	    ArrayList<Integer>  rowIdList = null;	
			/* Insert some new rows */
			rowIdList = HDLmDatabase.insertTableRowsSystem(insertRows, insertRowKeys, transferObj.toSystemName);
			/* Put the row Ids in the current row list into the rows 
			   that were just inserted */
			int  rowIdListLen = rowIdList.size();
			int  insertRowsLen = insertRows.size();
			String  errorText = "Row Id list length does not match insert row list length";
			HDLmAssertAction(rowIdListLen == insertRowsLen, errorText);
			for (int i = 0; i < rowIdListLen; i++) {
				HDLmDatabaseRow  currentRow = insertRows.get(i);
				currentRow.setId(((Integer) rowIdList.get(i)).toString());
			}			
			/* At this point we can add the current operation to the undo/redo ArrayList. 
			   This ArrayList may be used for undo/redo operations later. */
			changeNumber = HDLmUnRe.unReStartOperation();
			/* Get the current system type */
			HDLmSystemTypes   currentSystemType = HDLmSystemTypes.valueOfString(HDLmConfigInfo.getCurrentEnvironment());
			HDLmUnRe  UnReDelete = new HDLmUnRe(currentSystemType, 
					                                transferObj.toSystemName,
					                                HDLmDatabaseTypes.DELETE, 
					                                changeNumber,
					                                deletedRows);
			HDLmUnRe  UnReInsert = new HDLmUnRe(currentSystemType, 
					                                transferObj.toSystemName,
					                                HDLmDatabaseTypes.INSERT, 
					                                changeNumber,
					                                insertRows);
			/* Add the database operations to the undo/redo ArrayList */
			HDLmUnRe.unReAddOperation(UnReDelete);
			HDLmUnRe.unReAddOperation(UnReInsert);
	    break;			
		}			
		/* At this point we can (must) return a response to the caller. 
		   The response may be an error message or it may be a change 
		   number. */
		HDLmResponse  transferResponse = new HDLmResponse();
		if (errorMessage != null) 
			transferResponse.setErrorMessage(errorMessage);
		else 
			transferResponse.setReturnNumber(changeNumber);		 
		return transferResponse;
  }
	/* This routine is invoked to run a transfer request. All of the details   
     are stored in the object passed by the caller. */
  protected static HDLmResponse  runTransferRequest(HDLmTransferSomething transferObj) { 
		/* Check if the transfer object passed by the caller is null */
		if (transferObj == null) {
			String  errorText = "Transfer object passed to runTransferRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Get the database lock. This lock is not freed until the
	     transfer is complete. This step prevents database reads 
	     from happening until the lock is freed. */ 		
	  HDLmDatabase.getDatabaseLock();
		/* Set the initial error message */
		ArrayList<HDLmDatabaseRow>  databaseRows = null;
		HDLmResponse                insertResponse = null;        
		HDLmResponse                runResponse = new HDLmResponse();
		int                         changeNumber = -1;
		String                      errorMessage = null;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
	    /* Try to get all of the rows for a company / domain name */
			String  contentStr = HDLmUtility.getContentString();
			databaseRows = HDLmDatabase.getTableRowsCompanySystem(contentStr, 
					                                                  transferObj.fromDomainName,
					                                                  transferObj.fromSystemName);
			/* Report an error, if no database rows are returned */
			if (databaseRows == null) {
			  String  errorFormat = "Null value returned for company (%s)";
				String  errorText = String.format(errorFormat, transferObj.fromDomainName);
				errorMessage = errorText; 
				break;
			}
			/* Get the list of rows we really care about */
			ArrayList<HDLmDatabaseRow>  rowList = filterRowList(transferObj, databaseRows);
			if (rowList == null) {
				errorMessage = "Null output row list value returned from filterRowList";
				break;
			}
			/* Insert each of the rows */
			insertResponse = insertRowList(transferObj, rowList);
			errorMessage = insertResponse.getErrorMessage();
			if (errorMessage == null)
				changeNumber = insertResponse.getReturnNumber();
			break;
		}
		/* Get some information from the insert response. The insert
		   response may have an error message or a change number. */
		if (errorMessage != null)
			runResponse.setErrorMessage(errorMessage);
    else
      runResponse.setReturnNumber(changeNumber);
		/* Free the database lock. This lock is freed when the current 
	     operation is complete. This step prevents database reads  
	     from happening until the lock is freed. */ 		
	  HDLmDatabase.releaseDatabaseLock();
		return runResponse;
  }
	/* This routine is invoked to handle inbound transfer requests. A typical request
     might be to copy some number of rules from the test system to the production 
     system. The caller describes the transfer request in some detail. */
	protected static HDLmResponse  transferSomething(JsonElement jsonElements) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElements == null) {
			String  errorText = "JSON elements passed to transferSomething is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element is a null value */
		if (jsonElements.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElements.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Set the initial error message */
		int     changeNumber = -1;
		String  errorMessage = null;
		HDLmResponse  runResponse = null;        
		HDLmResponse  transferResponse = new HDLmResponse();
		/* Allocate an empty transfer request object */ 		 
		HDLmTransferSomething  transferObj = new HDLmTransferSomething();
		/* Check if the transfer object was properly allocated */ 
		if (transferObj == null) {
			String  errorText = "Transfer request object was not allocated in transferSomething";
			HDLmAssertAction(false, errorText);
		}
		/* Build an object with the transfer request in it */
		errorMessage = buildTransferRequest(jsonElements, transferObj);
		if (errorMessage != null) {
			transferResponse.setErrorMessage(errorMessage);
			return transferResponse;
		}
		/* Run a transfer request */
		runResponse = runTransferRequest(transferObj);
		errorMessage = runResponse.getErrorMessage();
		if (errorMessage != null) 
			transferResponse.setErrorMessage(errorMessage);
		else {
			/* Get the change number from the run response */
			changeNumber = runResponse.getReturnNumber();
			transferResponse.setReturnNumber(changeNumber);
		}
	  return transferResponse;
	}				
}
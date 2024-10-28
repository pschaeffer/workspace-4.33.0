package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
/**
 * Class for supporting overall pass-through data and definitions
 *
 * The public class defined below (HDLmPassThru) can not be instantiated. 
 * This class exists only to hold static data and to provide a home
 * for static methods related to handling pass-through definitions 
 * and data.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmPassThru {
	/* This class can never be instantiated */
	private HDLmPassThru() {}
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmPassThru.class);
	/* The method below processes just one set of details (really just
	   one instance that extends the modification type). The JSON element
	   is converted to a JSON object and various changes are mode. */  
	protected static void processJsonPass(JsonElement jsonElement) {		
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
		  String  errorText = "Node tree value passed to processJsonPass is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		boolean   passThruUsed = false;
		boolean   passThruValue = false;
		boolean   reportUsed = false;
		String    comments = null;
		String    detailsOne = null;
		String    detailsTwo = null;
		String    detailsThree = null;
		String    reportValue = null;
		/* Check if the current JSON element is really a JSON object */
		if (!jsonElement.isJsonObject())
			HDLmAssertAction(false, "JSON element passed to processJsonPass is not a JSON object");
		/* Get the current tree type from the current instance */
		String  typeString = HDLmJson.getJsonString(jsonElement, "passThruType");
		if (typeString == null)
			HDLmAssertAction(false, "Type value can not be obtained from the JSON element passed to processJsonPass");
		HDLmTreeTypes   typeValue = HDLmTreeTypes.valueOf(typeString);
		typeString = typeString.toLowerCase();
		/* Get a few more values from the details (really an instance that extends 
		   the modification type) */
		String  nameString = HDLmJson.getJsonString(jsonElement, "name");
		if (nameString == null)
			nameString = "";
		String  extraString = HDLmJson.getJsonString(jsonElement, "extra");
		if (extraString == null)
			extraString = "";
		Boolean   enabledBoolean = HDLmJson.getJsonBoolean(jsonElement, "enabled");
		if (enabledBoolean == null)
			enabledBoolean = false;
		Boolean   updatedBoolean = HDLmJson.getJsonBoolean(jsonElement, "updated");
		if (updatedBoolean == null)
			updatedBoolean = false; 
		/* If we handling details for a line (one line of a report), then we 
		   must make sure a few fields are not null */
    if (typeValue == HDLmTreeTypes.LINE) {
    	detailsOne = HDLmJson.getJsonString(jsonElement, "detailsOne");
    	if (detailsOne == null)
    		detailsOne = "";
    	detailsTwo = HDLmJson.getJsonString(jsonElement, "detailsTwo");
    	if (detailsTwo == null)
    		detailsTwo = "";
    	detailsThree = HDLmJson.getJsonString(jsonElement, "detailsThree");
    	if (detailsThree == null)
    		detailsThree = "";    	
    }		
    /* If we are handling a type that may have comments, then we must
       make sure that they comments are not null */ 
    if (typeValue == HDLmTreeTypes.IGNORE ||
    		typeValue == HDLmTreeTypes.LIST) {
    	comments = HDLmJson.getJsonString(jsonElement, "comments");
    	if (comments == null)
    		comments = ""; 
    }
    /* Check if the pass-through status was set. This will only be true
		   in some cases. */
		if (HDLmJson.hasJsonKey(jsonElement, "passThruStatus")) {
			passThruUsed = true;
	    String  passThruString = HDLmJson.getJsonString(jsonElement, "passThruStatus");
			if (passThruString.equals(HDLmPassThruStatus.PASSTHRUOK.toString()))
				passThruValue = true;			
		}		
		/* Check if the report type was set. This will only be true
		   in some cases. */ 
		if (HDLmJson.hasJsonKey(jsonElement, "reportType")) {
			reportUsed = true;
	    String  reportString = HDLmJson.getJsonString(jsonElement, "reportType");
			if (reportString.equals(HDLmReportTypes.CHECKWEBSITE.toString()))
				reportValue = "Check website";			
			else if (reportString.equals(HDLmReportTypes.CHECKERROR.toString()))
				reportValue = "Check error";
			else 
				reportValue = "None";
		}		
		/* Remove a set of keys from the current JSON object */
		HDLmJson.removeJsonKey(jsonElement, "comments");
		HDLmJson.removeJsonKey(jsonElement, "companies");	
		HDLmJson.removeJsonKey(jsonElement, "companiesReference");
		HDLmJson.removeJsonKey(jsonElement, "companiesTreeMap");	
		HDLmJson.removeJsonKey(jsonElement, "cssSelector");
		HDLmJson.removeJsonKey(jsonElement, "errorCount");
		HDLmJson.removeJsonKey(jsonElement, "finds");
		HDLmJson.removeJsonKey(jsonElement, "ignoreEntries");
		HDLmJson.removeJsonKey(jsonElement, "ignoreList");
		HDLmJson.removeJsonKey(jsonElement, "ignoreLists");
		HDLmJson.removeJsonKey(jsonElement, "invalidLines");
		HDLmJson.removeJsonKey(jsonElement, "lines");
		HDLmJson.removeJsonKey(jsonElement, "matchCache");	
		HDLmJson.removeJsonKey(jsonElement, "nodeIden");
		HDLmJson.removeJsonKey(jsonElement, "overallLines");
		HDLmJson.removeJsonKey(jsonElement, "parameter");
		HDLmJson.removeJsonKey(jsonElement, "parameterNumber");
		HDLmJson.removeJsonKey(jsonElement, "passThruType");
		HDLmJson.removeJsonKey(jsonElement, "passThruStatus");
		HDLmJson.removeJsonKey(jsonElement, "path");
		HDLmJson.removeJsonKey(jsonElement, "pathre");
		HDLmJson.removeJsonKey(jsonElement, "pathRe");
		HDLmJson.removeJsonKey(jsonElement, "reports");	
		HDLmJson.removeJsonKey(jsonElement, "validLines");
		HDLmJson.removeJsonKey(jsonElement, "values");
		HDLmJson.removeJsonKey(jsonElement, "valuesCount");
		HDLmJson.removeJsonKey(jsonElement, "xPath");		
		/* Put the values back in the JSON object in a specific order */
		if (comments != null)
		  HDLmJson.setJsonString(jsonElement, "comments", comments);	
		if (detailsOne != null)
		  HDLmJson.setJsonString(jsonElement, "detailsOne", detailsOne);	
		if (detailsTwo != null)
		  HDLmJson.setJsonString(jsonElement, "detailsTwo", detailsTwo);	
		if (detailsThree != null)
		  HDLmJson.setJsonString(jsonElement, "detailsThree", detailsThree);	
		HDLmJson.setJsonBoolean(jsonElement, "enabled", enabledBoolean);	
		HDLmJson.setJsonString(jsonElement, "extra", extraString);	
		HDLmJson.setJsonString(jsonElement, "name", nameString);
		/* Check if we need to set a pass-through status */
		if (passThruUsed)
			HDLmJson.setJsonBoolean(jsonElement, "passThru", passThruValue);
		/* Check if we need to set a report type */
		if (reportUsed)
			HDLmJson.setJsonString(jsonElement, "reportType", reportValue);
		HDLmJson.setJsonString(jsonElement, "type", typeString);
		HDLmJson.setJsonBoolean(jsonElement, "updated", updatedBoolean);	
	}	
}
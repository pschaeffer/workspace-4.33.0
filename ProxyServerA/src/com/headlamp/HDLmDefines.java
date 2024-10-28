package com.headlamp;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmDefines short summary.
 *
 * HDLmDefines description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmDefines {	
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmDefines.class);  
	/* This class can never be instantiated */
	private HDLmDefines() {}
	/* Define a few constants for use in our code. Note that the 
	   JavaScript code has another copy of these values. */
	private static final Map<String, String> defines = Map.ofEntries(		  
		Map.entry("HDLMAPICHECKUSERNAMEPASSWORD",       "checkUsernamePassword"),
		Map.entry("HDLMAPICHECKLASTTIME",               "checkLastTime"),
		Map.entry("HDLMAPIGETUSER",                     "getUser"),
		Map.entry("HDLMAPISETPASSWORD",                 "setPassword"),
		Map.entry("HDLMAPIVERIFYCODE",                  "verifyCode"),
	  Map.entry("HDLMBACKGROUNDCOLORHEX",             "00BFFF"),
	  Map.entry("HDLMBACKGROUNDCOLORRGB",             "0, 191, 255"), 
	  /* The next entry does not appear to be in use */
	  Map.entry("HDLMBUILDJSNAME",                    "HDLmBuildJs"),
	  /* The next entry does not appear to be in use */
	  Map.entry("HDLMBUILDRULESJSNAME",               "HDLmBuildRulesJs"),
	  Map.entry("HDLMBUILDRULESSETTESTOFF",           "HDLmBuildRulesSetTestOff"),
	  Map.entry("HDLMBUILDRULESSETTESTON",            "HDLmBuildRulesSetTestOn"),
	  Map.entry("HDLMCOMPANIESNODENAME",              "Companies"),
	  Map.entry("HDLMCOMPANIESTYPE",                  "companies"),
	  Map.entry("HDLMCOMPANYTYPE",                    "company"),
	  Map.entry("HDLMCONFIGS",                        "HDLmConfigs"),
	  Map.entry("HDLMCURENV",                         "HDLm_Cur_Env"),
	  Map.entry("HDLMDATANODENAME",                   "Data"),	
	  Map.entry("HDLMDATATYPE",                       "data"),	
	  Map.entry("HDLMDEBUGENABLED",                   "HDLmDebugEnabled"),
    Map.entry("HDLMDEFAULTMODNAME",                 "Modification"),
    Map.entry("HDLMDIVISIONNODENAME",               "example.com"),
    Map.entry("HDLMDIVISIONTYPE",                   "division"),
	  Map.entry("HDLMDOMDOCUMENT",                    "DOMDocument"),
	  Map.entry("HDLMDOMELEMENT",                     "DOMElement"),
	  Map.entry("HDLMENTRYDESCRIPTIONS",              "#entryDescriptions"),
	  Map.entry("HDLMENTRYVALUES",                    "#entryValues"),
	  Map.entry("HDLMERRORTEXT",                      "HDLmErrorText"),
	  Map.entry("HDLMEXPECTEDFILENAME",               "HDLmExpectedGetJsOutput.txt"),
	  Map.entry("HDLMFANCYTREE",                      "#tree"),
	  Map.entry("HDLMFIXEDFILENAME",                  "HDLmFixedJs.txt"),
	  Map.entry("HDLMFONTSIZEMAX",                    "300"),
	  Map.entry("HDLMFONTSIZEMIN",                    "1"),
	  Map.entry("HDLMFORCEVALUE",                     "TCELESECROF"),	  
	  Map.entry("HDLMFULLVALUENAME",                  "Value"),
	  Map.entry("HDLMGEMPREFIX",                      "HDLmGemPrfx"),
	  Map.entry("HDLMGETDATA",                        "HDLmGetData"),
	  Map.entry("HDLMGETJSVALUE",                     "HDLmGetJS"),
	  Map.entry("HDLMGRAALSYNTAXERROR",               "SyntaxError"),
	  Map.entry("HDLMGXEPREFIX",                      "HDLmGxePrfx"),
	  Map.entry("HDLMLEFTDEF",                        "leftDef"),
	  Map.entry("HDLMHEIGHTMAX",                      "2000"),
	  Map.entry("HDLMHEIGHTMIN",                      "1"),
	  Map.entry("HDLMHOSTNAME",                       "HDLm_Host_Name"),
	  Map.entry("HDLMHOSTOS",                         "HDLm_Host_OS"),	  
	  Map.entry("HDLMHOSTUSERDIR",                    "HDLm_Host_UserDir"),
	  Map.entry("HDLMHTMLFOOTER",                     "#footer"),
	  Map.entry("HDLMHTMLHEADER",                     "#header"),
	  Map.entry("HDLMIGNORELISTSNODENAME",            "Ignore Lists"),
	  Map.entry("HDLMINLINELEFT",                     "#entryLeft"),
	  Map.entry("HDLMINLINERIGHT",                    "#entryRight"),
	  Map.entry("HDLMINVALIDNODENAME",                "Invalid"),	  
	  Map.entry("HDLMINVOKEAPI",                      "HDLmInvokeApi"),
	  Map.entry("HDLMLASTARRAYSIZE",                  "100"),
	  Map.entry("HDLMLEFTANDRIGHTPAGE",               "leftAndRightPage"),
	  Map.entry("HDLMLOADMODNAME",                    "load"),
	  Map.entry("HDLMLOADPAGEMODNAME",                "load page"),
	  Map.entry("HDLMLOADPAGEMODNAMEOLD",             "load"),
	  Map.entry("HDLMMAXCHANGES",                     "100"),
	  Map.entry("HDLMMAXIDENTEXTLEN",                 "20"),
	  Map.entry("HDLMMAXJSONERRORLEN",                "50"),
	  Map.entry("HDLMMAXMODNODEPATHLENGTH",           "5"),
	  Map.entry("HDLMMAXPASSNODEPATHLENGTH",          "7"),
	  Map.entry("HDLMMAXPARAMETERCOUNT",              "1000"),
	  Map.entry("HDLMMODS",                           "HDLmMods"),
	  Map.entry("HDLMNODEPATH",                       "HDLmNodePath"),
	  Map.entry("HDLMOVERALLNODENAME",                "Overall"),
	  Map.entry("HDLMPLUSSIGN",                       "HDLmPlusSign"),
	  Map.entry("HDLMPOSTDATA",                       "HDLmPostData"),
	  Map.entry("HDLMPREFIX",                         "HDLm"),
	  Map.entry("HDLMREPORTNAMEPREFIX",               "Report"),
	  Map.entry("HDLMREPORTSNODENAME",                "Reports"),
	  Map.entry("HDLMRIGHTDEF",                       "rightDef"),
	  Map.entry("HDLMRULESNODENAME",                  "Rules"),
	  Map.entry("HDLMRULESNODEPATHLENGTH",            "7"),	  
	  Map.entry("HDLMRULESTYPE",                      "rules"),
    Map.entry("HDLMSESSIONCLASSES",                 "HDLmSessionClasses"),
    Map.entry("HDLMSESSIONCOOKIE",                  "HDLmSessionCookie"),
    Map.entry("HDLMSESSIONDEBUGRULESENABLED",       "HDLmSessionDebugRulesEnabled"),
    Map.entry("HDLMSESSIONID",                      "HDLmSessionId"),
    Map.entry("HDLMSESSIONDEBUGNODEIDENENABLED",    "HDLmSessionDebugNodeIdenEnabled"),
    Map.entry("HDLMSESSIONPASSWORD",                "HDLmSessionPassword"),
    Map.entry("HDLMSESSIONPOSTRULETRACINGENABLED",  "HDLmSessionPostRuleTracingEnabled"),
    Map.entry("HDLMSESSIONRULEINFODIVISIONNAME",    "HDLmSessionRuleInfoDivisionName"),
    Map.entry("HDLMSESSIONRULEINFOHOSTNAME",        "HDLmSessionRuleInfoHostName"),
    Map.entry("HDLMSESSIONRULEINFOSITENAME",        "HDLmSessionRuleInfoSiteName"),
    Map.entry("HDLMSESSIONUSERNAME",                "HDLmSessionUserName"),	 
    Map.entry("HDLMSETLASTTIME",                    "setLastTime"),
	  Map.entry("HDLMSHORTMODNAME",                   "Mod"),
	  Map.entry("HDLMSIMULATEPROXYERROR",             "SimulateProxy Error"),
	  Map.entry("HDLMSIMULATEPROXYFAILURE",           "SimulateProxy Failure"),
	  Map.entry("HDLMSITENODEPATHLENGTH",             "6"),
	  Map.entry("HDLMSITENODENAME",                   "example.com"),
	  Map.entry("HDLMSITETYPE",                       "site"),
	  Map.entry("HDLMSYSTEMPROD",                     "prod"),
	  Map.entry("HDLMSYSTEMTEST",                     "test"),
	  Map.entry("HDLMTIMINGSARRAYSIZE",               "200"),
	  Map.entry("HDLMTOPNODENAME",                    "Top"),
	  Map.entry("HDLMTOPNODEPATHLENGTH",              "1"),
	  Map.entry("HDLMTOPNODETYPE",                    "top"),
	  Map.entry("HDLMTREE",                           "HDLmTree"),	
	  Map.entry("HDLMTYPECOMPANIES",                  "15"),
	  Map.entry("HDLMTYPECOMPANY",                    "2"),
	  Map.entry("HDLMTYPECONFIG",                     "6"),
	  Map.entry("HDLMTYPEDIVISION",                   "3"),
	  Map.entry("HDLMTYPEIGNORE",                     "9"),
	  Map.entry("HDLMTYPELINE",                       "12"),
	  Map.entry("HDLMTYPELINES",                      "13"),
	  Map.entry("HDLMTYPELIST",                       "8"),
	  Map.entry("HDLMTYPELISTS",                      "14"),
	  Map.entry("HDLMTYPEMOD",                        "5"),
	  Map.entry("HDLMTYPEREPORT",                     "11"),
	  Map.entry("HDLMTYPEREPORTS",                    "10"),
	  Map.entry("HDLMTYPERULES",                      "16"),
	  Map.entry("HDLMTYPESITE",                       "4"),
	  Map.entry("HDLMTYPESTORE",                      "7"),
	  Map.entry("HDLMTYPETOP",                        "1"),
	  Map.entry("HDLMUPDATED",                        "HDLmUpdated"),
	  Map.entry("HDLMURLFAILEDTEXT",                  "URL failed"),
	  Map.entry("HDLMUSEARRAYSIZE",                   "100"),
	  Map.entry("HDLMVALIDNODENAME",                  "Valid"),
	  Map.entry("HDLMVALUETYPE",                      "value"),
	  Map.entry("HDLMWIDTHMAX",                       "4000"),
	  Map.entry("HDLMWIDTHMIN",                       "1")	    
	);
	/* This static method returns all of the defines as a single
	   JSON string. The JSON string is constructed and returned 
	   to the caller. The caller can use the JSON string in any
	   possible way. However, the JSON string is probably returned
	   to another machine. */
	protected static String getDefines() {
		boolean              firstValue = true; 
		Map<String, String>  treeCopy = new TreeMap<String, String>(defines);
		StringBuilder        outBuilder = new StringBuilder();
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
			String   value = defines.get(key);
			/* Add the value of the current key to the output JSON */
			outBuilder.append(HDLmJson.getJsonValue(value));
		}
		/* Add the final right brace */
		outBuilder.append('}');		
		return outBuilder.toString();
	}
	/* This static method returns the numeric value of a define
	   if the define name is valid (exists) and if the define
	   value is actually a number. */
	protected static int getNumber(String defineName) {
		if (defineName == null) {
			String   errorText = "Definition name reference passed to getNumber is null";
			throw new NullPointerException(errorText);			
		}
		int rv;
		/* Check if the Define name passed by the caller is valid
		   or not. We need to raise an exception if the Define name
		   passed by the caller is unknown. */
		if (!defines.containsKey(defineName)) {
			String  errorText = String.format("Invalid Define Name (%s) passed to getNumber", defineName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Get the value from the Map and check if the value is a number. This 
		   method can only be used to obtain values that are actually numbers. */
		String getValue = defines.get(defineName);
		try {
			rv = Integer.parseInt(getValue);			
		}
		catch (NumberFormatException numberException) {	
			if (getValue != null)
			  LOG.info("Number - " + getValue);
			LOG.info("NumberFormatException while executing getNumber");
			LOG.info(numberException.getMessage(), numberException);
			HDLmEvent.eventOccurred("NumberFormatException");	
			String  errorText = String.format("Value of Define Name (%s) is not numeric", defineName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);			
		}
		catch (Exception exception) {	
			if (getValue != null)
			  LOG.info("Number - " + getValue);
			LOG.info("Exception while executing getNumber");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");	
			String  errorText = String.format("Value of Define Name (%s) is not numeric", defineName);
			Exception newException = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, newException);			
		}
		return rv;
	}
	/* This static method returns the string value of a define
		 if the define name is valid (exists) and if the define
		 value is actually a string (not a number). */
	protected static String getString(String defineName) {
		if (defineName == null) {
			String   errorText = "Definition name reference passed to getString is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the define name passed by the caller is valid
		   or not. We need to raise an exception if the Define name
		   passed by the caller is unknown. */
		if (!defines.containsKey(defineName)) {
			String  errorText = String.format("Invalid Define Name (%s) passed to getString", defineName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Get the value from the Map and check if the value is a number. This 
		   method can not be used to obtain values that are actually numbers. */
		String rv = defines.get(defineName);
		if (StringUtils.isNumeric(rv)) {
			String  errorText = String.format("Value of Define Name (%s) is numeric", defineName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);			
		}
		return rv;
	}
}
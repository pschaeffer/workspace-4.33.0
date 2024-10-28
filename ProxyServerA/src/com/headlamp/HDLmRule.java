package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmRule short summary.
 *
 * HDLmRule description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmRule {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmRule.class);  	
	/* Define the map that contains all of the rule 
     values */
  private static Map<String, HDLmRule>  rulesValues = new TreeMap<String, HDLmRule>(); 	
  /* This constructor takes a set of company, division, site, and modification
     names and build a new rule instance */ 
	protected HDLmRule(final HDLmRuleTypes newRuleType,
			               final String newRuleCompany,
			               final String newRuleDivision,
			               final String newRuleSite,
			               final String newRuleModificationName) {
		/* Check if the new rule type passed by the caller is null */
		if (newRuleType == null) {
			String  errorText = "New rule type string passed to the HDLmRule constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new company name passed by the caller is null */
		if (newRuleCompany == null) {
			String  errorText = "New company name string passed to the HDLmRule constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new division name passed by the caller is null */
		if (newRuleDivision == null) {
			String  errorText = "New division name string passed to the HDLmRule constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new site name passed by the caller is null */
		if (newRuleSite == null) {
			String  errorText = "New site name string passed to the HDLmRule constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new modification name passed by the caller is null */
		if (newRuleModificationName == null) {
			String  errorText = "New modification name string passed to the HDLmRule constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few fields in the new rule instance */
		ruleType = newRuleType;
		ruleDomain = newRuleCompany;  	
		ruleDivision = newRuleDivision;
		ruleSite = newRuleSite;
		ruleName = newRuleModificationName;
		ruleOverall = newRuleCompany + "/" + newRuleDivision + "/" +
		              newRuleSite + "/" + newRuleModificationName;
	}
	/* All rules have a type. The type of each rules is 
	   stored in the field below. */ 
  private HDLmRuleTypes   ruleType = null;
	/* The rule name can be anything. It could be a file name.
	   It could be a URL name. In most (but not all) cases, the
	   rule name will be a rule name. The concept is that 
	   usually, the rule name for rules will have the 
	   domain name, division name, site name, and rule name 
	   in it. In all cases, the overall rule name will be 
	   a string. */
	private String    ruleOverall = null;
	/* Each rule rule is associated with a specific domain name
	   (company name). The company name is stored in the field 
	   below. */
	private String    ruleDomain = null;
	/* Each rule rule is associated with a specific division name
     The division name is stored in the field below. */
  private String    ruleDivision = null;
	/* Each rule rule is associated with a specific site name
     The site name is stored in the field below. */
  private String    ruleSite = null;
  /* Each rule rule is associated with a specific rule name.
     The rule name (modification name) is stored in the field
     below. */
  private String    ruleName = null;
  /* A rule can be used zero or more times. The number of times
     might be quite large if the server stays up for a long period
     of time and the rule is used at a high rate. */
  private long      ruleCount = 0;
  /* Each time an rule is used, the rule timestamp is updated.
     This value will be null, if the rule has never been used.
     This value will be set to a GMT timestamp each time the
     rule is used. */
  private Instant   ruleLast = null;
  /* Each rule has a status string. Initially the status string will 
     be set to null. The status string will contain some indication 
     of whether the rule worked without error or what the error was.
     The status string may be a simple string or it may be a complex
     JSON string. */ 
  private String    ruleStatus = null;
  /* Each time a rule is used, a rule object (an instance)
     is added to the array below. This is a wraparound array.
     This means that if the array below has 100 entries, then
     the first rule use would use entry 0. The 100th rule use
     would use entry 99 and the 101st rule use would reuse 
     entry 0. */
  private HDLmRule[]  ruleUseArray = null;  
  /* This routine adds an rule to the rules map. Note that this
	   routine can be called more than once with the same rule name.
	   If a duplicate rule name is passed, it will just be ignored.
	   This is not an error condition. */ 
  @SuppressWarnings("unused")
	protected static boolean  addRule(final HDLmRuleTypes newRuleType,			
			                              final String newRuleCompany,
			                              final String newRuleDivision,
			                              final String newRuleSite,
			                              final String newRuleModification) {
		/* Check if the new rule type passed by the caller is null */
		if (newRuleType == null) {
			String  errorText = "New rule type string passed to addRule is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new rule company passed by the caller is null */
		if (newRuleCompany == null) {
			String  errorText = "New rule company string passed to addRule is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new rule division passed by the caller is null */
		if (newRuleDivision == null) {
			String  errorText = "New rule division string passed to addRule is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new rule site passed by the caller is null */
		if (newRuleSite == null) {
			String  errorText = "New rule site string passed to addRule is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new rule modification passed by the caller is null */
		if (newRuleModification == null) {
			String  errorText = "New rule modification string passed to addRule is null";
			throw new NullPointerException(errorText);
		}
		/* Assume that the current rule will not be added */
		boolean   ruleAdded = false;
		/* Build the overall rule name from the values passed by the caller */
		String  ruleNameOverall = newRuleCompany + "/" + newRuleDivision + "/" +
                              newRuleSite + "/" + newRuleModification;
		/* Check if the rule has already been added */
		if (rulesValues.containsKey(ruleNameOverall))
			return ruleAdded;
		/* Create the new rule instance */
		HDLmRule  newRule = new HDLmRule(newRuleType, newRuleCompany,
				                             newRuleDivision, newRuleSite, 
				                             newRuleModification);
	  if (newRule == null)	{   
		  String   errorText = "New rule reference obtained from HDLmRule is null";
			HDLmAssertAction(false, errorText);		    	
	  }
	  /* Build the array that is used to keep track of uses of
	     this rule */ 
	  newRule.buildUseArray();
	  /* Add the new rule to the rules map */
	  rulesValues.put(ruleNameOverall, newRule);
		return ruleAdded;
	}  
  /* This routine builds the rule use array for a rule instance. 
     Normally. this sort of code would go into a class constructor.
     However, we don't want the rule use array to be built for all
     instances of this class. */
  public void            buildUseArray() {
		/* Fill the array with null values */
		int   useArraySize = HDLmDefines.getNumber("HDLMUSEARRAYSIZE");
		ruleUseArray = new HDLmRule[useArraySize]; 
		for (int i = 0; i < useArraySize; i++)
			ruleUseArray[i] = null;  	
  }
  /* This routine builds a JSON string with a set of rule match
     information in it. This routine does all of the work to 
     obtain the rule match information and convert it to a 
     JSON string. */
  public static String   getMatchInfo(final String overallName) {
		/* Check if the overall rule name passed by the caller is null */
		if (overallName == null) {
			String  errorText = "Overall rule name string passed to getMatchInfo is null";
			throw new NullPointerException(errorText);
		}
  	/* Declare and define a few variables */
  	ArrayList<String>              headingsArray = HDLmRule.getRuleHeadings();
  	ArrayList<ArrayList<String>>   outputArray = new ArrayList<ArrayList<String>>();
  	ArrayList<String>              statusArray; 
  	ArrayList<String>              statusArraySublist = null;
  	/* Add a dummy entry to the headings for match details. This dummy 
  	   entry is removed later and is not actually displayed. */ 
  	headingsArray.add("Match Details");
  	int   headingsArraySize = headingsArray.size();
		/* Add the table headings to the final output array */
		outputArray.add(headingsArray);
		/* Get a tree set of match information */
		TreeSet<HDLmRule>   matchSet = HDLmRule.getRuleTree(overallName);
		HDLmAssertAction(matchSet != null, "Tree set not returned by getRuleTree");
		/* Get an array of match status information */
		statusArray = HDLmRule.getRuleStatus(matchSet, HDLmGetRuleStatus.JSONINFORMATION, overallName);
		HDLmAssertAction(statusArray != null, "Status array not returned by getRuleStatus");
		/* Add each of the subarrays to the array list of array lists */
		for (int i = 0; i < statusArray.size(); i += headingsArraySize) {		
			statusArraySublist = new ArrayList<String>();
			for (int j = i; j < i+headingsArraySize; j++)
			  statusArraySublist.add(statusArray.get(j)); 
			outputArray.add(statusArraySublist);
		}		
		/* Build the final output JSON string and return it to the caller */
		Gson    gson = HDLmMain.gsonMain;
		String  outputJson = gson.toJson(outputArray);
		return outputJson;
  }
  /* This routine returns a set of heading suitable for all of
     the uses of a rule */
  public static ArrayList<String>  getRuleHeadings() {
  	ArrayList<String> headings = new ArrayList<String>(List.of("Rule Use<br/>Count", 
																												       "Last Use<br/>Timestamp", 
																												       "Time Since<br/>Last Use",
																												       "Last Rule<br/>Status"));	
		return headings;  	
  }
  /* This routine builds an ArrayList of rule status information.
	   Some callers use this information directly. Other callers
	   extract the information they need. */
	public static ArrayList<String>  getRuleStatus(final TreeSet<HDLmRule> rulesSet,
			                                           final HDLmGetRuleStatus informationType, 
			                                           final String ruleOverallParm) {
		/* Declare and define a few variables */
	  boolean   encodingException = false;
		/* Build a (short) list of the rule status information */			
	  var  statusListContents = new ArrayList<String>();
		/* Process each rule in the rules set. Note that the rules
		   set is in the desired order. */ 
	  for (HDLmRule curRule : rulesSet) {
	 	  /* Declare and define a few values */
		 	encodingException = false;
		 	/* Get a few values from the current rule */
		 	HDLmRuleTypes   ruleType = curRule.getType();
		 	long            ruleCount = curRule.getCount();
		 	String          ruleOverall = curRule.getOverall();
		 	String          ruleOverallEncoded = null;
		 	String          ruleOverallTableCell = null;
		 	String          ruleName = curRule.getName();
		 	String          ruleNameEncoded = null;
		 	String          ruleStatus = curRule.getStatus();
		 	String          ruleStatusEncoded = null;
		 	String          ruleStatusTableCellFirst = null;
		 	String          ruleStatusTableCellSecond = null;
		 	String          ruleTimestamp = curRule.getTimestamp(); 
		 	String          ruleTypeStr = ruleType.toPrint();  
		 	/* Add the current rule name and type. The rule name is the 
		 	   overall rule name and it may (or may not) be clickable. */
			if (ruleOverallParm == null) {
		   	if (ruleCount > 0) {    	
			 		/* The rule name may have embedded blanks in it. The rule name 
			 		   must be URL encoded so that it can be used in HTML. */ 
		   		try {
					  ruleOverallEncoded = URLEncoder.encode(ruleOverall, StandardCharsets.UTF_8.toString());
					  if (informationType == HDLmGetRuleStatus.HTMLINFORMATION == true)
						  ruleOverallTableCell = "<a href=/rules-status?rule=" + ruleOverallEncoded +  
				                             ">" + ruleOverall + "</a>";
					  else
					  	ruleOverallTableCell = ruleOverall;
				  } 
		   		catch (UnsupportedEncodingException e) {
		   			encodingException = true;
		   			HDLmEvent.eventOccurred("UnsupportedEncodingException");
		   			if (informationType == HDLmGetRuleStatus.HTMLINFORMATION)
						  ruleOverallTableCell = "<a href=/rules-status?rule=" +  
				                             ">" + ruleOverall + "</a>"; 
					  else
					  	ruleOverallTableCell = ruleOverall;
		   			return null;
				  }
					statusListContents.add(ruleOverallTableCell);
		   	}
		   	else
		   		statusListContents.add(ruleOverall);
			}
		 	/* Add the rule type value */
			if (ruleOverallParm == null) 
	 	    statusListContents.add(ruleTypeStr);
	 	  /* Check if we actually have a rule timestamp */
	 	  if (ruleTimestamp != null) {
		 		/* Add the rule use count */
		 		String  ruleCountStr = String.valueOf(ruleCount);
		 		statusListContents.add(ruleCountStr);
		 		/* Add the timestamp for the current rule */
		 		ZonedDateTime       ruleZonedDateTime = Instant.parse(ruleTimestamp).atZone(ZoneId.systemDefault());
		 		DateTimeFormatter   ruleFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss.SSS");
		 		String              ruleFormattedString = ruleZonedDateTime.format(ruleFormatter);
		 		statusListContents.add(ruleFormattedString);   
		 		/* Get the elapsed time since the last use of this rule */
		 		Instant   start = Instant.parse(ruleTimestamp);
		 		Instant   finish = Instant.now();
		 		long      ruleElapsedMillis = Duration.between(start, finish).toMillis();
		 		statusListContents.add(String.valueOf(ruleElapsedMillis/1000.0)); 
		 		/* The rule status may be a simple value or it may be a complex
		 		   JSON string. We need to handle both cases. */ 
		 		if (ruleStatus.charAt(0) == '{') {
		 			/* Create a new JSON parser for use below */
		 	    JsonParser    parser = new JsonParser();  
		 	    JsonElement   jsonElement = parser.parse(ruleStatus); 
		 	    HDLmAssertAction(jsonElement.isJsonObject(), "JSON element must be a JSON object");
		 	    /* Get a summary string for the error. The link is associated with this
		 	       summary string */
		 	    JsonObject  jsonObject = jsonElement.getAsJsonObject();
		 	    String  ruleStatusSummary = jsonObject.getAsJsonPrimitive("matcherror").getAsString();   
		 	    ruleStatusSummary = HDLmString.ucFirst(ruleStatusSummary);    	    
	 			  /* Build the get request for the href */ 
			    String getKey = HDLmDefines.getString("HDLMGETDATA");
		   		if (getKey == null) {
		   			String   errorFormat = "Define value for get key not found (%s)";
		   			String   errorText = String.format(errorFormat, "HDLMGETDATA");
		   			HDLmAssertAction(false, errorText);		    	
		   		}
		   		/* The rule status is a JSON string. The JSON string must be URL
		   		   encoded before it is sent back to the client. */
		   		try {
						ruleStatusEncoded = URLEncoder.encode(ruleStatus, StandardCharsets.UTF_8.toString());
						ruleNameEncoded = URLEncoder.encode(ruleName, StandardCharsets.UTF_8.toString());
						if (informationType == HDLmGetRuleStatus.HTMLINFORMATION)
							ruleStatusTableCellFirst = "<a href=/" + getKey + "?json=" + ruleStatusEncoded + 
									                       "&name=" + ruleNameEncoded +
									                       ">" + ruleStatusSummary + "</a>"; 
						else {
							ruleStatusTableCellFirst = ruleStatusSummary;
							ruleStatusTableCellSecond = ruleStatus;
						}	
					} 
		   		/* The encoding of the JSON may cause (rather unlikely) an encoding exception.
		   		   The encoding exception is handled below. */ 
		   		catch (UnsupportedEncodingException ue) {
		   			encodingException = true;
		   			HDLmEvent.eventOccurred("UnsupportedEncodingException");
		   			if (informationType == HDLmGetRuleStatus.HTMLINFORMATION)
		   			  ruleStatusTableCellFirst = "<a href=/" + getKey + "?json=%7B%7D&name=%22%22>" + ruleStatusSummary + "</a>"; 
		   			else {
		   				ruleStatusTableCellFirst = ruleStatusSummary; 
		   				ruleStatusTableCellSecond = ""; 
		   			}
		   			return null;
					}   	    
	 		  }
	 		  /* The rule status may be a simple value */
	 		  else {
	 	  	  ruleStatusTableCellFirst = HDLmString.ucFirst(ruleStatus);
	 	  	  if (informationType == HDLmGetRuleStatus.JSONINFORMATION)
	 	  	    ruleStatusTableCellSecond = HDLmString.ucFirst(ruleStatus);
	 		  }
	 		  statusListContents.add(ruleStatusTableCellFirst);
	 		  if (informationType == HDLmGetRuleStatus.JSONINFORMATION)
	 		  	statusListContents.add(ruleStatusTableCellSecond);
	 	  }    	
	 	  /* The rule timestamp value is not set */
	   	else {
	 		  /* Add values that show that we have no data for all
	 		     of the columns */ 
	 		  statusListContents.add("-");
			  statusListContents.add("-");
			  statusListContents.add("-");
			  statusListContents.add("-");
			  if (informationType == HDLmGetRuleStatus.JSONINFORMATION)
			  	statusListContents.add("-");
	 	  }    	
	  }
	  return statusListContents;
	} 
  /* This routine returns a tree set with all of the uses of a rule */
  public static TreeSet<HDLmRule>  getRuleTree(final String overallName) {
		/* Check if the overall rule name passed by the caller is null */
		if (overallName == null) {
			String  errorText = "Overall rule name string passed to getRuleTree is null";
			throw new NullPointerException(errorText);
		}
    /* Create an rules comparator for use in building/maintaining the
       tree rule set. This comparator orders the rules based on the
       rule count value. */ 		
	  class countComparator implements Comparator<HDLmRule> {     
	    @Override
	    public int compare(HDLmRule e1, HDLmRule e2) {
	  	  long  compareLong;
	  	  compareLong = e1.getCount() - e2.getCount(); 
		    return (int) compareLong;		   
	    }
	  }   
		/* Get a reference to the map of rules information */
		var  rulesMap = HDLmRule.getRulesMap();
		if (rulesMap == null) {
			String   errorText = "Rules map reference obtained from getRulesMap is null";
			HDLmAssertAction(false, errorText);		    	
    }
    /* Create a new tree set that uses the count comparator */
    TreeSet<HDLmRule>   rulesSet = new TreeSet<HDLmRule>(new countComparator());	
	  /* Get the rule that has the array of rule uses */
	  HDLmRule  curRule = rulesMap.get(overallName);
	  HDLmAssertAction(curRule != null, "Current rule obtained from rules map is null");		
	  /* Get the array of rule uses */
		HDLmRule  ruleUseArray[] = curRule.getRuleUse(); 
		int       ruleUseSize = HDLmDefines.getNumber("HDLMUSEARRAYSIZE");
		int       startingIndex;
		long      ruleUseCount = curRule.getCount();			
		/* Get the starting count value */
		if (ruleUseCount <= 0)  
			startingIndex = 0;	 
		else if (ruleUseCount <= ruleUseSize) 
			startingIndex = 0;
		else 
			startingIndex = (int) (ruleUseCount - ruleUseSize);	  
		/* Process all of the rule uses */
		for (int i = startingIndex; i < ruleUseCount; i++) {
			int   indexActual = i % ruleUseSize;
			curRule = ruleUseArray[indexActual];
			rulesSet.add(curRule);			
		}
		return rulesSet;
  } 
  /* Note that an rule has been used. Update the rule instance for 
     the current rule. */ 
	public static void     ruleUsed(final String ruleKey) {
		/* Check if the rule key string passed by the caller is null */
		if (ruleKey == null) {
			String  errorText = "Rule key string passed to ruleUsed is null";
			throw new NullPointerException(errorText);
		}
		/* Get a reference to the map of rules information */
		var  rulesMap = HDLmRule.getRulesMap();
		if (rulesMap == null) {
			String   errorText = "Rules map reference obtained from getRulesMap is null";
			HDLmAssertAction(false, errorText);		    	
    }
		/* Make sure the rules map has the current rule */
		if (!rulesMap.containsKey(ruleKey)) {
			String  errorFormat = "Rules map does not have current rule (%s)"; 
			String  errorText = String.format(errorFormat,  ruleKey);
			HDLmAssertAction(false, errorText);					
		}
		/* Get the rule from the rules map */
		HDLmRule   curRule = rulesMap.get(ruleKey);
    /* Get the current instant */
    Instant  instant = Instant.now();   
		/* Update the rule as need be */
	  curRule.setIsoTimestamp(instant);
	  /* Store the updated rule back in the rules map */
	  rulesMap.put(ruleKey, curRule);		
	}
  /* Note that an rule has been used. Update the rule instance for 
 	   the current rule. */ 
	protected static void  ruleUsed(final String hostName,
			                            final String divisionName,
			                            final String siteName,
			                            final String ruleName,
			                            final String ruleStatus) {
		/* Check if the host name passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to ruleUsed is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the division name passed by the caller is null */
		if (divisionName == null) {
			String  errorText = "Division name string passed to ruleUsed is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the site name passed by the caller is null */
		if (siteName == null) {
			String  errorText = "Site name string passed to ruleUsed is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the rule name passed by the caller is null */
		if (ruleName == null) {
			String  errorText = "New rule site string passed to ruleUsed is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the rule status passed by the caller is null */
		if (ruleStatus == null) {
			String  errorText = "New rule status string passed to ruleUsed is null";
			throw new NullPointerException(errorText);
		}
		/* Get a reference to the map of rules information */
		var  rulesMap = HDLmRule.getRulesMap();
		if (rulesMap == null) {
			String   errorText = "Rules map reference obtained from getRulesMap is null";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Build the overall rule name from the host name, division name, 
		   site name, and rule name */
		String  ruleNameOverall = hostName + "/" + divisionName + "/" +
                              siteName + "/" + ruleName;
		/* Make sure the rules map has the current rule. Note that the check
		   is done using the overall rule name, not just the rule name itself.  */
		if (!rulesMap.containsKey(ruleNameOverall)) {
			String  errorFormat = "Rules map does not have current rule (%s)"; 
			String  errorText = String.format(errorFormat,  ruleName);
			HDLmAssertAction(false, errorText);					
		}
		/* Get the existing rule instance from the rules map */
		HDLmRule   curRule = rulesMap.get(ruleNameOverall);
		/* Make sure we found an actual rule */
		if (curRule == null) {		
	  	String   errorFormat = "Rule name (%s) not found in rule map";
			String   errorText = String.format(errorFormat, ruleNameOverall);
			HDLmAssertAction(false, errorText);		
		}	
		HDLmRuleTypes   curRuleType = curRule.getType();
		/* Create the new rule instance */
		HDLmRule  newRule = new HDLmRule(curRuleType, hostName,
				                             divisionName, siteName, 
				                             ruleName);
	  if (newRule == null)	{   
		  String   errorText = "New rule reference obtained from HDLmRule is null";
			HDLmAssertAction(false, errorText);		    	
	  }	
    /* Get the current instant */
    Instant  instant = Instant.now();
		/* Update the rule as need be */
    long  curCount = curRule.getCount();
    curCount++;
    curRule.setCount(curCount);
    newRule.setCount(curCount);
	  curRule.setIsoTimestamp(instant);
	  newRule.setIsoTimestamp(instant);
	  curRule.setStatus(ruleStatus);
	  newRule.setStatus(ruleStatus);
    /* Add the current rule use to the array of rule uses. 
       What this routine really does is to add the current 
       rule use to the array of rule uses. */ 
    curRule.storeCurrentRuleUse(newRule);
	  /* Store the updated rule back in the rules map */
	  rulesMap.put(ruleName, curRule);		
	}
  /* Return a reference to the main rules map */
  protected static Map<String, HDLmRule>  getRulesMap() {
  	return rulesValues;
  }
  /* Get the count value for a rule */
  protected long         getCount() {
  	return ruleCount;
  }
  /* Get the name of an rule. This is just the modification name.
     This is not the overall name. */
  protected String       getName() {
  	return ruleName;
  }
  /* Get the overall name of a rule. This is the domain name, division
     name, site name, and the modification name combined together. */  
	protected String       getOverall() {
		return ruleOverall;
	}
  /* Get the status of an rule. This is the last status set for a 
     rule. This might be a simple Java string or it might be a JSON
     structure. */  
  protected String       getStatus() {
	  return ruleStatus;
  }
  /* Get the timestamp value for an rule. Note that the
     timestamp value is actually a string. Note also that 
     this could be a null value if the current rule has
     never been used. */
  protected String       getTimestamp() {
  	if (ruleLast == null)
  		return null;
  	return ruleLast.toString();
  }  
  /* Get the type of a rule. All rules have a type
     that can be obtained using this routine. */
  protected HDLmRuleTypes  getType() {
	  return ruleType;
  }
  /* Get a reference to the rule use array. The rule use 
     array is used to keep trace of each use of a rule. */ 
  protected HDLmRule[]   getRuleUse() {
    return ruleUseArray;
  }
  /* Increment the use count value for a rule */
  protected void         incrementCount() {
  	ruleCount++;
  }
  /* Set the count for a rule to a value passed by the caller */
  protected void         setCount(final long newCount) {
  	ruleCount = newCount;
  } 
  /* Set the timestamp for a rule to a value passed by the caller */
  protected void         setIsoTimestamp(final Instant newInstant) {
  	ruleLast = newInstant;
  }  
  /* Set the status string for a rule to a value passed by the caller */ 
  protected void         setStatus(final String newStatus) {
  	ruleStatus = newStatus;
  }
  /* Store the current rule use in the array of rule use instances */
	protected void         storeCurrentRuleUse(final HDLmRule newRule) {
		int   ruleUseSize = HDLmDefines.getNumber("HDLMUSEARRAYSIZE");
		int   curIndex = (int) ((ruleCount-1) % ruleUseSize);
		ruleUseArray[curIndex] = newRule;  	
	}   
}
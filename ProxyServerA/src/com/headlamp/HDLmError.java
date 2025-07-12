package com.headlamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmError short summary.
 *
 * The HDLmError class is not used to create any objects. However, it does
 * contain some of the static methods used for error logging. The standard 
 * error numbers are listed below. These error numbers are shared (and used)
 * by the Java and JavaScript code.  
 *
 * @version 1.0
 * @author Peter
 */
/* Each of product errors is listed here for informative purposes. Note that
	 this list of errors is used by the Java code and the JavaScript code. This
	 is the only copy of the list. If a new Java or JavaScript error number is
	 added, this list must be updated.
		
		1 - Some type of CURL error
		2 - Some type of modification error
		3 - Field missing from modification JSON
		4 - Invalid modification JSON field
		5 - NULL modification JSON field
		6 - Modification JSON field too low
		7 - Modification JSON field too high
		8 - Modification JSON empty array
		9 - Locate failed for a tree node
	 10 - Unable to lookup detailed information for a field type
	 11 - Field missing in node
	 12 - Invalid field type in a switch
	 13 - Invalid field subtype in a switch
	 14 - Some type of Promise error. This error code is used in the
	      index.html (JavaScript) file that starts the editors. This 
	      type of error could occur elsewhere as well.
	 15 - Unmatched quotes error
	 16 - Modifications, proxy definitions, and/or 
	      configuration definitions not retrieved
	 17 - Invalid node type in a switch
	 18 - Invalid command type in a switch
	 19 - Invalid data type in a switch
	 20 - Invalid node type found
	 21 - Node path length is invalid
	 22 - Index operation failed
	 23 - Fields missing from modification JSON
	 24 - Invalid change type in a switch
	 25 - Locate failed for a Fancytree node
	 26 - Invalid null value passed to routine
	 27 - Locate information error
	 28 - Some type of I/O or file error
	 29 - Invalid request type in a switch
	 30 - invalid type value of some kind
	 31 - Invalid modification type in a switch
	 32 - Invalid HTTP request type in a switch
	 33 - Invalid value passed to routine
	 34 - Invalid logging level in a switch
	 35 - Not an error, used for reporting purposes
	 36 - Unable to build match object 
	 37 - Invalid token type in a switch
	 38 - Some type of string format error
	 39 - Node identifier information is invalid
	 40 - Invalid node identifier type in a switch
	 41 - Not an error, used for node identifier reporting purposes
	 42 - Invalid menu item ID in a switch
	 43 - Array size is too small
	 44 - Parent element is null
	 45 - Invalid tag name  
	 46 - Invalid parent element
	 47 - Child element error
	 48 - Regex error
	 49 - Invalid length of some kind
	 50 - Invalid type of value in a switch
	 51 - Not used - Can be reused 
	 52 - Some type of Promise error. This error code is used 
	      in a JavaScript file. 
	 53 - Invalid operation type in a switch
	 54 - The current browser type could not be determined
	 55 - Used to report JavaScript errors
	 56 - Invalid stage in a switch
	 57 - Invalid JSON key in a switch
	 58 - Some type of password error
	 59 - Node already exists
	 60 - Some type of transfer error
	 61 - Some type of undo/redo error
	 62 - Invalid rule name in a switch
	 63 - Invalid choice number in a switch
	 64 - Field missing from some JSON
	 65 - Field error found
	 66 - Field error found with value
	 67 - Field error found with a null value
	 68 - Field does not exist
	 69 - Field is not a JSON array
	 70 - JSON array is too small
	 71 - JSON array is too large
	 72 - Not used - Can be reused
	 73 - Not used - Can be reused
	 74 - Host name not obtained from inbound URL
	 75 - Message is invalid
	 76 - Not used - Can be reused
	 77 - Some type of exception
	 78 - Invalid configuration value of some kind
	 79 - Invalid null value 
	 80 - Type not found
	 81 - Details not found  
	 82 - Array entry is the wrong type
	 83 - Details are not valid
	 84 - String is not valid
	 85 - Object is not valid
	 86 - Boolean is not valid
	 87 - Number is not valid
*/
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmError {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmError.class);  
	/* This class can never be instantiated */
	private HDLmError() {}
  /* This is the standard error function. The caller provides the severity,
     type, number, and error message text. */
	static String buildError(String severity, String type, int number, String text) {
	  String  errorStr = "";
	  String  errorPrefix = HDLmDefines.getString("HDLMPREFIX");
	  /* In some cases the error prefix is not defined. If this routine is 
	     invoked before the defines are built, then the error prefix will
	     not be defined. A substitute value is provided below, if need be. */
	  if (errorPrefix == null)
	    errorPrefix = "HDLm";
	  errorStr += errorPrefix + " ";
	  errorStr += severity + " ";
	  errorStr += type + " ";
	  errorStr += Integer.toString(number) + " ";
	  errorStr += text;
	  HDLmError.errorLogDefaultNull(errorStr);
	  return errorStr;
	}
	/* This is the lowest level error logging method. This method does the actual
	   logging of errors. Note that a logger is actually used to log the error. 
	   The logging level used is ERROR, in keeping with the name and purpose of
	   this routine. Note that this routine is not in use at this time. */
	protected static void errorLogDefaultNull(String errorStr) {
	  /* We would like to test for and reject null values values passed 
	     to this routine. However, this is part of error handling. It 
	     should probably not fail when it is needed to build or issue
	     an error message. */ 
    if (errorStr == null) {
    	HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Null Value", 26, "errorStr");
			errorStr = "Null Error String Value"; 	
    }
		LOG.error(errorStr);		
	}
}
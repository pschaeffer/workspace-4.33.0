package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.*;

import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmHtml short summary.
 *
 * HDLmHtml description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmHtml {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmHtml.class);  
	/* This class can never be instantiated */
	private HDLmHtml() {}
	/* The CSS style table lists all of the values that can be (we hope)
     specified for a CSS style for an HTML element. Note that the 
	   JavaScript code has another copy of these values. */
	private static final Map<String, String> CSSStyles = Map.ofEntries(				
	  Map.entry("align-content",               ""),
    Map.entry("align-items",                 ""),
    Map.entry("align-self",                  ""),
    Map.entry("all",                         ""),
    Map.entry("animation",                   ""),
    Map.entry("animation-delay",             ""),
    Map.entry("animation-direction",         ""),
    Map.entry("animation-duration",          ""),
    Map.entry("animation-fill-mode",         ""),
    Map.entry("animation-iteration-count",   ""),
    Map.entry("animation-name",              ""),
    Map.entry("animation-play-state",        ""),
    Map.entry("animation-timing-function",   ""),
    Map.entry("backface-visibility",         ""),
    Map.entry("background",                  ""),
    Map.entry("background-attachment",       ""),
    Map.entry("background-blend-mode",       ""),
    Map.entry("background-clip",             ""),
    Map.entry("background-color",            ""),
    Map.entry("background-image",            ""),
    Map.entry("background-origin",           ""),
    Map.entry("background-position",         ""),
    Map.entry("background-repeat",           ""),
    Map.entry("background-size",             ""),
    Map.entry("border",                      ""),
    Map.entry("border-bottom",               ""),
    Map.entry("border-bottom-color",         ""),
    Map.entry("border-bottom-left-radius",   ""),
    Map.entry("border-bottom-right-radius",  ""),
    Map.entry("border-bottom-style",         ""),
    Map.entry("border-bottom-width",         ""),
    Map.entry("border-collapse",             ""),
    Map.entry("border-color",                ""),
    Map.entry("border-image",                ""),
    Map.entry("border-image-outset",         ""),
    Map.entry("border-image-repeat",         ""),
    Map.entry("border-image-slice",          ""),
    Map.entry("border-image-source",         ""),
    Map.entry("border-image-width",          ""),
    Map.entry("border-left",                 ""),
    Map.entry("border-left-color",           ""),
    Map.entry("border-left-style",           ""),
    Map.entry("border-left-width",           ""),
    Map.entry("border-radius",               ""),
    Map.entry("border-right",                ""),
    Map.entry("border-right-color",          ""),
    Map.entry("border-right-style",          ""),
    Map.entry("border-right-width",          ""),
    Map.entry("border-spacing",              ""),
    Map.entry("border-style",                ""),
    Map.entry("border-top",                  ""),
    Map.entry("border-top-color",            ""),
    Map.entry("border-top-left-radius",      ""),
    Map.entry("border-top-right-radius",     ""),
    Map.entry("border-top-style",            ""),
    Map.entry("border-top-width",            ""),
    Map.entry("border-width",                ""),
    Map.entry("bottom",                      ""),
    Map.entry("box-decoration-break",        ""),
    Map.entry("box-shadow",                  ""),
    Map.entry("box-sizing",                  ""),
    Map.entry("caption-side",                ""),
    Map.entry("caret-color",                 ""),
    Map.entry("clear",                       ""),
    Map.entry("clip",                        ""),
    Map.entry("color",                       ""),
    Map.entry("column-count",                ""),
    Map.entry("column-fill",                 ""),
    Map.entry("column-gap",                  ""),
    Map.entry("column-rule",                 ""),
    Map.entry("column-rule-color",           ""),
    Map.entry("column-rule-style",           ""),
    Map.entry("column-rule-width",           ""),
    Map.entry("column-span",                 ""),
    Map.entry("column-width",                ""),
    Map.entry("columns",                     ""),
    Map.entry("content",                     ""),
    Map.entry("counter-increment",           ""),
    Map.entry("counter-reset",               ""),
    Map.entry("cursor",                      ""),
    Map.entry("direction",                   ""),
    Map.entry("display",                     ""),
    Map.entry("empty-cells",                 ""),
    Map.entry("filter",                      ""),
    Map.entry("flex",                        ""),
    Map.entry("flex-basis",                  ""),
    Map.entry("flex-direction",              ""),
    Map.entry("flex-flow",                   ""),
    Map.entry("flex-grow",                   ""),
    Map.entry("flex-shrink",                 ""),
    Map.entry("flex-wrap",                   ""),
    Map.entry("float",                       ""),
    Map.entry("font",                        ""),
    Map.entry("font-family",                 ""),
    Map.entry("font-kerning",                ""),
    Map.entry("font-size",                   ""),
    Map.entry("font-size-adjust",            ""),
    Map.entry("font-stretch",                ""),
    Map.entry("font-style",                  ""),
    Map.entry("font-variant",                ""),
    Map.entry("font-weight",                 ""),
    Map.entry("grid",                        ""),
    Map.entry("grid-area",                   ""),
    Map.entry("grid-auto-columns",           ""),
    Map.entry("grid-auto-flow",              ""),
    Map.entry("grid-auto-rows",              ""),
    Map.entry("grid-column",                 ""),
    Map.entry("grid-column-end",             ""),
    Map.entry("grid-column-gap",             ""),
    Map.entry("grid-column-start",           ""),
    Map.entry("grid-gap",                    ""),
    Map.entry("grid-row",                    ""),
    Map.entry("grid-row-end",                ""),
    Map.entry("grid-row-gap",                ""),
    Map.entry("grid-row-start",              ""),
    Map.entry("grid-template",               ""),
    Map.entry("grid-template-areas",         ""),
    Map.entry("grid-template-columns",       ""),
    Map.entry("grid-template-rows",          ""),
    Map.entry("hanging-punctuation",         ""),
    Map.entry("height",                      ""),
    Map.entry("hyphens",                     ""),
    Map.entry("isolation",                   ""),
    Map.entry("justify-content",             ""),
    Map.entry("left",                        ""),
    Map.entry("letter-spacing",              ""),
    Map.entry("line-height",                 ""),
    Map.entry("list-style",                  ""),
    Map.entry("list-style-image",            ""),
    Map.entry("list-style-position",         ""),
    Map.entry("list-style-type",             ""),
    Map.entry("margin",                      ""),
    Map.entry("margin-bottom",               ""),
    Map.entry("margin-left",                 ""),
    Map.entry("margin-right",                ""),
    Map.entry("margin-top",                  ""),
    Map.entry("max-height",                  ""),
    Map.entry("max-width",                   ""),
    Map.entry("min-height",                  ""),
    Map.entry("min-width",                   ""),
    Map.entry("mix-blend-mode",              ""),
    Map.entry("object-fit",                  ""),
    Map.entry("object-position",             ""),
    Map.entry("opacity",                     ""),
    Map.entry("order",                       ""),
    Map.entry("outline",                     ""),
    Map.entry("outline-color",               ""),
    Map.entry("outline-offset",              ""),
    Map.entry("outline-style",               ""),
    Map.entry("outline-width",               ""),
    Map.entry("overflow",                    ""),
    Map.entry("overflow-x",                  ""),
    Map.entry("overflow-y",                  ""),
    Map.entry("padding",                     ""),
    Map.entry("padding-bottom",              ""),
    Map.entry("padding-left",                ""),
    Map.entry("padding-right",               ""),
    Map.entry("padding-top",                 ""),
    Map.entry("page-break-after",            ""),
    Map.entry("page-break-before",           ""),
    Map.entry("page-break-inside",           ""),
    Map.entry("perspective",                 ""),
    Map.entry("perspective-origin",          ""),
    Map.entry("pointer-events",              ""),
    Map.entry("position",                    ""),
    Map.entry("quotes",                      ""),
    Map.entry("resize",                      ""),
    Map.entry("right",                       ""),
    Map.entry("scroll-behavior",             ""),
    Map.entry("tab-size",                    ""),
    Map.entry("table-layout",                ""),
    Map.entry("text-align",                  ""),
    Map.entry("text-align-last",             ""),
    Map.entry("text-decoration",             ""),
    Map.entry("text-decoration-color",       ""),
    Map.entry("text-decoration-line",        ""),
    Map.entry("text-decoration-style",       ""),
    Map.entry("text-indent",                 ""),
    Map.entry("text-justify",                ""),
    Map.entry("text-overflow",               ""),
    Map.entry("text-shadow",                 ""),
    Map.entry("text-transform",              ""),
    Map.entry("top",                         ""),
    Map.entry("transform",                   ""),
    Map.entry("transform-origin",            ""),
    Map.entry("transform-style",             ""),
    Map.entry("transition",                  ""),
    Map.entry("transition-delay",            ""),
    Map.entry("transition-duration",         ""),
    Map.entry("transition-property",         ""),
    Map.entry("transition-timing-function",  ""),
    Map.entry("unicode-bidi",                ""),
    Map.entry("user-select",                 ""),
    Map.entry("vertical-align",              ""),
    Map.entry("visibility",                  ""),
    Map.entry("white-space",                 ""),
    Map.entry("width",                       ""),
    Map.entry("word-break",                  ""),
    Map.entry("word-spacing",                ""),
    Map.entry("word-wrap",                   ""),
    Map.entry("writing-mode",                ""),
    Map.entry("z-index",                     "")
  );
	/* The next two fields are used to search strings for uppercase characters. 
	   Since these values are static and expensive to computer, we declare and
	   define them here. They are only created once. */ 
	private static final String  upperRegexString = "[A-Z]";
	private static final Pattern  upperRegexPattern = Pattern.compile(upperRegexString);
  /* Build an accept encoding HTML header */
  protected static String buildAcceptEncodingHeader(String acceptEncodingStr) {
		/* Check a few values passed by the caller */
		if (acceptEncodingStr == null) {
			String   errorText = "Accept Encoding string reference passed to buildAcceptEncodingHeader is null";
			throw new NullPointerException(errorText);			
		}
    return buildHeader("Accept-Encoding", acceptEncodingStr);
  }
  /* Build an Amazon Authorization HTML header */
  protected static String buildAmzAuthorizationHeader(String authValue) {
		/* Check a few values passed by the caller */
		if (authValue == null) {
			String   errorText = "Authorization string reference passed to buildAmzAuthorizationHeader is null";
			throw new NullPointerException(errorText);			
		}
    return buildHeader("Authorization", authValue);
  }
  /* Build an Amazon SDK Invocation ID HTML header */
  protected static String buildAmzSdkInvocationIdHeader(String sdkIdStr) {
		/* Check a few values passed by the caller */
		if (sdkIdStr == null) {
			String   errorText = "SDK ID string reference passed to buildAmzSdkInvocationIdHeader is null";
			throw new NullPointerException(errorText);			
		}
    return buildHeader("amz-sdk-invocation-id", sdkIdStr);
  }
  /* Build an Amazon SDK Request HTML header */
  protected static String buildAmzSdkRequestHeader(int attemptNumber) {
  	
    String  attemptStr = ((Integer) attemptNumber).toString();
    String  requestStr = "attempt" + "=" + attemptStr;
    return buildHeader("amz-sdk-request", requestStr);
  }
  /* Build an Open AI authorization HTML header */
  protected static String buildAuthorizationHeader(String apiKeyStr) {
		/* Check a few values passed by the caller */
		if (apiKeyStr == null) {
			String   errorText = "API key string reference passed to buildAuthorizationHeader is null";
			throw new NullPointerException(errorText);			
		}
    String  authValueStr = "Bearer" + " " + apiKeyStr;
    return buildHeader("Authorization", authValueStr);
  }
  /* This routine builds an HTML blank line and returns it to the 
     caller. The return value is a standard Java string. The returned
     string contains the HTML needed to create a blank line. */  
  public static String buildBlankLine() {
    return "<br>";
  }
  /* Build a content length HTML header */
  protected static String buildContentLengthHeader(int contentLength) {
    return buildHeader("Content-Length", ((Integer) contentLength).toString());
  }
  /* Build a content type HTML header */
  protected static String buildContentTypeHeader(String contentTypeStr) {
		/* Check a few values passed by the caller */
		if (contentTypeStr == null) {
			String   errorText = "Content type string reference passed to buildContentTypeHeader is null";
			throw new NullPointerException(errorText);			
		}
    return buildHeader("Content-Type", contentTypeStr);
  }
  /* This routine builds a set of CSS for formatting an HTML table
	   or any other (CSS) purpose. The caller can pass all of the values
	   used to format the HTML table or other HTML entity. The values are
	   passed as using the K/V values of a map. */
	protected static String buildCssFromMap(Map<String, String> cssMap) {
	  /* Start building the output CSS */
		StringBuilder  rv = new StringBuilder();
	  rv.append("<style>");
	  /* Check if the CSS map has the required "name" key */	 
	  if (cssMap.containsKey("name")) {
	 	  rv.append(cssMap.get("name"));
	 	  rv.append(" {");	  	
	  }
	  else {
		  String  errorFormat = "Key (%s) not found in CSS map";
		  String  errorText = String.format(errorFormat, "name");
		  /* Clearly the expected key was not found in the CSS map object. 
	      Report an error in this case. */
		  HDLmAssertAction(false, errorText);
	  }
	  /* Add all of the CSS properties */
	  for (var cssEntry : cssMap.entrySet()) {
	 	  String  keyName = cssEntry.getKey();
		  if (keyName.equals("name"))
		    continue;
		  /* Convert the key name to a format appropriate for CSS */
		  keyName = HDLmHtml.formatKeyName(keyName);
		  rv.append(keyName);
		  rv.append(": ");
		  rv.append(cssEntry.getValue());
		  rv.append(";");
	  }
	  /* Finish the CSS */ 
	  rv.append('}');
	  rv.append("</style>");
	  return rv.toString();
	}
  /* This routine builds a set of CSS for formatting a table. The
     caller does not have to pass anything. This routine builds a
     set of table related CSS and returns it to the caller. */
	protected  static String buildCssTable() {
		String         tableCss;
		StringBuilder  rv = new StringBuilder();
	  /* Build some CSS for formatting the table */
	  HashMap<String, String>  cssMap = new HashMap<String, String>(); 
	  cssMap.put("name", "table");
	  cssMap.put("borderCollapse", "collapse");
	  tableCss = HDLmHtml.buildCssFromMap(cssMap);
	  rv.append(tableCss);
	  /* Build some more CSS for formatting the table */
	  cssMap.clear();
	  cssMap.put("name", "table, th, td");
	  cssMap.put("border",  "1px solid black");
	  tableCss = HDLmHtml.buildCssFromMap(cssMap);
	  rv.append(tableCss);
	  return rv.toString();
	}
  /* Build an HTML header string from the values passed the caller */
  protected static String buildHeader(String typeStr, String valueStr) {
		/* Check a few values passed by the caller */
		if (typeStr == null) {
			String   errorText = "Header type string reference passed to buildHeader is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the value string passed by the caller */
		if (valueStr == null) {
			String   errorText = "Header value string reference passed to buildHeader is null";
			throw new NullPointerException(errorText);			
		}
    return typeStr + ": " + valueStr;
  }
  /* This routine builds an HTML heading using the values passed to it.
     The caller must provide the heading type (h1, h2, etc.) and the
     heading text. The return value is the HTML for the heading. */
	public static String buildHeading(String heading, String text) {
		StringBuilder  rv = new StringBuilder();
		rv.append('<');
		rv.append(heading);
		rv.append('>');
		rv.append(text);
		rv.append("</");
		rv.append(heading);
		rv.append('>');
	  return rv.toString();
	}
  /* Build an HTML host header */
  protected static String buildHostHeader(String hostNameStr) {
    return buildHeader("Host", hostNameStr);
  }
  /* This routine builds an HTML table using the values passed to it.
     The caller must provide the column headings and the data to be
     displayed. */
  protected static String buildHtmlTableFromArray(ArrayList<String> headings, 
  		                                            Object data[][]) {
  	Object         cell;
  	StringBuilder  rv = new StringBuilder();
	  int  numCols = headings.size();
	  int  numRows = data.length;
	  /* Start the output table */
	  rv.append("<table>");
	  /* Add all of the column headings */
	  rv.append("<tr>");
	  for (int i = 0; i < numCols; i++) {
	  	rv.append("<th>");
	    rv.append(headings.get(i));
	    rv.append("</th>");
	  }
	  rv.append("</tr>");
	  /* Add all of the data rows */
	  for (int row = 0; row < numRows; row++) {
	  	rv.append("<tr>");
	    for (int col = 0; col < numCols; col++) {
	    	rv.append("<td>");
	      cell = data[row][col];
	      /* Check if the current value is already a string. If the 
	         current value is already a string, we don't have much 
	         to do. */
	      if (cell instanceof String) {
	      	;
	      }
	      /* Check if the current value is an array */
	      else if (cell instanceof Array) 
	        cell = "array";
	      /* Check if the current value is an object */
	      else if (cell instanceof Object) 
	        cell = "object";
	      /* We assume that all other types of data can be converted
	         to a string, at least for now */
	      else
	        cell = cell.toString();
	      rv.append(cell);
	      rv.append("</td>");
	    }
	    rv.append("</tr>");
	  }
	  rv.append("</table>");
	  return rv.toString();
	}
  /* This routine builds an HTML table using the values passed to it.
     The caller must provide the column headings and the data (a list)
     to be displayed. */
	protected static String buildHtmlTableFromList(ArrayList<String> headings, 
			                                           ArrayList<String> data) {
		StringBuilder  rv = new StringBuilder();
	  int  numCols = headings.size();
	  int  rowNumber = 0;
	  int  rowLength = headings.size();
	  int  numRows = data.size() / rowLength;
	  /* Start the output table */
	  rv.append("<table>");
	  /* Add all of the column headings */
	  rv.append("<tr>");
	  for (int i = 0; i < numCols; i++) {
	 	  rv.append("<th>");
	    rv.append(headings.get(i));
	    rv.append("</th>");
	  }
	  rv.append("</tr>");
	  /* Add all of the data rows */
	  for (rowNumber = 0; rowNumber < numRows; rowNumber++) {
	 	  rv.append("<tr>");
	 	  for (int i = 0; i < rowLength; i++) {
		 	  /* Add some data for the current row */
		   	rv.append("<td>");
		   	rv.append(data.get((rowNumber * rowLength)+i));
		   	rv.append("</td>");
	 	  }    
	    rv.append("</tr>");
	  }
	  rv.append("</table>");
	  return rv.toString();
	}
  /* This routine builds an HTML table using the values passed to it.
	   The caller must provide the column headings and the data (a list)
	   to be displayed. The first entry for each row of data is treated
	   as a style value. The style is used, if the value is not null, 
	   and is not a zero-length string. */
	protected static String buildHtmlTableFromListStyle(ArrayList<String> headings, 
			                                                ArrayList<String> data) {
		StringBuilder  rv = new StringBuilder();
	  int  numCols = headings.size();
	  int  rowNumber = 0;
	  int  rowLength = headings.size();
	  int  numRows = data.size() / (rowLength+1);
	  /* Start the output table */
	  rv.append("<table>");
	  /* Add all of the column headings */
	  rv.append("<tr>");
	  for (int i = 0; i < numCols; i++) {
		  rv.append("<th>");
	    rv.append(headings.get(i));
	    rv.append("</th>");
	  }
	  rv.append("</tr>");
	  /* Add all of the data rows */
	  for (rowNumber = 0; rowNumber < numRows; rowNumber++) {
	  	int     rowIndex = rowNumber * (rowLength+1);
	  	if (rowIndex >= data.size()) {
	  		rowIndex = 0;
	  	}
	  	String  rowStyle = data.get(rowIndex);
	  	if (rowStyle == null ||
	  			rowStyle.length() == 0)
		    rv.append("<tr>");
	  	else
	  		rv.append("<tr style=\"" + rowStyle +"\">");
		  for (int i = 0; i < rowLength; i++) {
		 	  /* Add some data for the current row */
		   	rv.append("<td>");
		   	rv.append(data.get((rowNumber * (rowLength+1))+(i+1)));
		   	rv.append("</td>");
		  }    
	    rv.append("</tr>");
	  }
	  rv.append("</table>");
	  return rv.toString();
	}
	/* This routine builds an HTML table using the values passed to it.
     The caller must provide the column headings and the data (a tree
     map) to be displayed. */
	protected static String buildHtmlTableFromTree(ArrayList<String> headings, 
			                                           TreeMap<String, String> data) {
		StringBuilder  rv = new StringBuilder();
	  int  numCols = headings.size();
	  @SuppressWarnings("unused")
	  int  numRows = data.size();
	  /* Start the output table */
	  rv.append("<table>");
	  /* Add all of the column headings */
	  rv.append("<tr>");
	  for (int i = 0; i < numCols; i++) {
		  rv.append("<th>");
	    rv.append(headings.get(i));
	    rv.append("</th>");
	  }
	  rv.append("</tr>");
	  /* Add all of the data rows (tree map entries) */
	  for (var dataEntry : data.entrySet()) {
		  rv.append("<tr>");
		  /* Add the key for the current row */
	  	rv.append("<td>");
	  	rv.append(dataEntry.getKey());
	  	rv.append("</td>");
		  /* Add the data for the current row */
	  	rv.append("<td>");
	  	rv.append(dataEntry.getValue().toString());
	  	rv.append("</td>");	    
	    rv.append("</tr>");
	  }
	  rv.append("</table>");
	  return rv.toString();
	}
  /* Build an Amazon Date HTML header */
  protected static String buildXAmzDateHeader(final String dateTimeValue) {
		/* Check a few values passed by the caller */
		if (dateTimeValue == null) {
			String   errorText = "Date and time string reference passed to buildXAmzDateHeader is null";
			throw new NullPointerException(errorText);			
		}
    return buildHeader("X-Amz-Date", dateTimeValue);
  }
  /* Build a user agent HTML header */
  protected static String buildUserAgentHeader(String userAgentStr) {
		if (userAgentStr == null) {
			String   errorText = "User agent string reference passed to buildUserAgentHeader is null";
			throw new NullPointerException(errorText);			
		}
    return buildHeader("User-Agent", userAgentStr);
  }
  /* Build an X-Amz-Target HTML header */
  protected static String  buildXAmzTargetHeader(String targetStr) {
		if (targetStr == null) {
			String   errorText = "Target string reference passed to buildXAmzTargetHeader is null";
			throw new NullPointerException(errorText);			
		}
    return buildHeader("X-Amz-Target", targetStr);
  }
  /* This routine checks if some JavaScript is valid or not. This routine 
     returns true, if the JavaScript is valid. This routine returns false, 
     if the JavaScript is not valid. The caller can specify if errors should
     be reported or not. */  
	protected static boolean  checkIfJavaScriptValid(String script, 
			                                             final HDLmReportErrors reportErrors) {
		/* Check if the JavaScript string is null */
		if (script == null) {
			String errorText = "Script string passed to checkIfJavaScriptValid is null";
			throw new NullPointerException(errorText);
		}
  	/* Check if the report errors enum is null */
		if (reportErrors == null) {
			String errorText = "Report errors enum passed to checkIfJavaScriptValid is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the report errors enum is set to NONE */
		if (reportErrors == HDLmReportErrors.NONE) {
			String errorText = "Report errors enum passed to checkIfJavaScriptValid is set to NONE";
			throw new NullPointerException(errorText);
		}
		/*
		int  lenScript = script.length();
		if (lenScript > 59 &&
				script.charAt(59) == '{') { 
			script = script.substring(0, 60) +  " console.log('Hxllo') ; " + script.substring(60);
		}
	  */
		/* Build the new script string, replacing all of the single quote 
		   characters with an escape sequence. The x27 is an ASCII single
		   quote. */
		String  newScript = HDLmAi.changeJavaScript(script); 
		/* Build the final JavaScript string using the passed JavaScript */
		String  finalScript = "new Function(`" + newScript + "`);"; 
		/* Try to execute the final JavaScript */
		String  rvStr = HDLmHtml.executeJavaScript(finalScript, reportErrors);
		/* Check if the JavaScript passed by the caller is valid or not */
		String  syntaxErrorStr = HDLmDefines.getString("HDLMGRAALSYNTAXERROR");
		if (rvStr.contains(syntaxErrorStr))
			return false;
		return true;
	}		
  /* This routine executes some JavaScript using the scripting 
     engine loaded by this routine. The caller can specify if
     errors should be reported or not. */ 
	protected static String executeJavaScript(final String script, 
			                                      final HDLmReportErrors reportErrors) {
		/* Check if the JavaScript string is null */
		if (script == null) {
			String errorText = "Script string passed to executeJavaScript is null";
			throw new NullPointerException(errorText);
		}
  	/* Check if the report errors enum is null */
		if (reportErrors == null) {
			String errorText = "Report errors enum passed to executeJavaScript is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the report errors enum is set to NONE */
		if (reportErrors == HDLmReportErrors.NONE) {
			String errorText = "Report errors enum passed to executeJavaScript is set to NONE";
			throw new NullPointerException(errorText);
		}
		/* List the Javascript factories */
		/* List<ScriptEngineFactory>  obj = new ScriptEngineManager().getEngineFactories(); */
	  /* Allows access to all Java classes */
		/* 
		Context context = Context.newBuilder("js")
				                       .allowHostAccess(HostAccess.ALL)
				                       .allowHostClassLookup(className -> true)
				                       .build();
	  */
		/* context.eval("js", script); */		
		/* Get and check the JavaScript scripting manager */
		ScriptEngineManager  manager = new ScriptEngineManager(null);
		if (manager == null)
			HDLmAssertAction(false, "JavaScript scripting manager was not loaded");
		/* Get and check the JavaScript scripting engine */
		ScriptEngine  engine = new ScriptEngineManager().getEngineByName("graal.js");
		if (engine == null)
			HDLmAssertAction(false, "JavaScript scripting engine was not loaded");		
		/* Execute the passed JavaScript */
		String  rvStr = HDLmHtml.executeJavaScriptUsingEngine(engine, script, reportErrors);
		return rvStr;
	}		
  /* This routine executes some JavaScript using the scripting 
     engine passed to it. The caller can specify iferrors should
     be reported or not.*/ 
  protected static String  executeJavaScriptUsingEngine(final ScriptEngine engine, 
  		                                                  final String script,
  		                                                  final HDLmReportErrors reportErrors) {
  	/* Check if the scripting engine is null */
		if (engine == null) {
			String errorText = "Scripting engine reference passed to executeJavaScriptUsingEngine is null";
			throw new NullPointerException(errorText);
		}
  	/* Check if the JavaScript string is null */
		if (script == null) {
			String errorText = "Script string passed to executeJavaScriptUsingEngine is null";
			throw new NullPointerException(errorText);
		}
  	/* Check if the report errors enum is null */
		if (reportErrors == null) {
			String errorText = "Report errors enum passed to executeJavaScriptUsingEngine is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the report errors enum is set to NONE */
		if (reportErrors == HDLmReportErrors.NONE) {
			String errorText = "Report errors enum passed to executeJavaScriptUsingEngine is set to NONE";
			throw new NullPointerException(errorText);
		}
		/* Execute the passed JavaScript */
		Object  rvObj = null;
		String  rvStr = null;
		try {
			rvObj = engine.eval(script);
			rvStr = rvObj.toString();
		} 
		catch (ScriptException scriptException) {
			/* Check if we should report errors. In some cases, this routine
			   is called when we expect errors. These errors should not be
			   reported. */
			if (reportErrors == HDLmReportErrors.REPORTERRORS) { 
				if (script != null)
				  LOG.info("script value - " + script);
				LOG.info("HDLmHtml while executing eval in executeJavaScriptUsingEngine");
				LOG.info(scriptException.getMessage(), scriptException);
				HDLmEvent.eventOccurred("ScriptException");
			}
			rvStr = scriptException.getMessage();
		}
		return rvStr;
  }
	/* This routine formats (builds) a CSS property name from a string
		 passed to it. Of course, this routine could be used to handle
		 non-CSS names as well. The rules are quite simple. All uppercase
		 characters are converted to lowercase and a hyphen is placed in
		 front of each previously uppercase character. Note that if the
		 first character is uppercase, it is converted to lowercase, but
		 no hyphen is placed in front of it. For example, BorderColor
		 becomes border-color. 
		 
		 Note that the definition of uppercase and lowercase is based on
		 traditional UASCII. In other words, the uppercase characters are
		 A-Z and the lowercase characters are a-z. This approach will not
		 work for some European languages and will definitely not work
		 for some Asian languages. */  
	protected static String formatKeyName(String name) {
		String  matchReplace;
	  /* The Array List below is used to store the offsets (indexes)
	     of all of the uppercase characters */
		var  matchesList = new ArrayList<Integer>();
	  /* Search for all of the uppercase characters */
		Matcher  matcher = upperRegexPattern.matcher(name);
		/* Get the indexes (offsets) of all of the uppercase characters */
		while(matcher.find()) 
			matchesList.add(matcher.start()); 
		/* Process all of the uppercase characters by replacing them
		   with either the corresponding lowercase character or a 
		   hyphen followed by the corresponding lowercase character */
		for (int i = matchesList.size()-1; i >= 0; i--) {
			int  matchOffset = matchesList.get(i);
			if (matchOffset > 0)
				matchReplace = "-" + Character.toLowerCase(name.charAt(matchOffset));
			else
				matchReplace = "" + Character.toLowerCase(name.charAt(matchOffset));
			/* Check the offset of the uppercase character. Build a new string
			   with the uppercase character properly replaced. */
			if (matchOffset == 0)
				name = matchReplace + name.substring(1);
			else 
				name = name.substring(0, matchOffset) + matchReplace + name.substring(matchOffset+1);			  
		}
		return name;
	}
	/* This static method returns all of the CSS style information
	   as a single JSON string. The JSON string is constructed and 
	   returned to the caller. The caller can use the JSON string 
	   in any possible way. However, the JSON string is probably
	   returned to another machine. */
	protected static String getStyles() {
		boolean              firstValue = true; 
		Map<String, String>  treeCopy = new TreeMap<String, String>(CSSStyles);
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
			String   value = CSSStyles.get(key);
			/* Add the value of the current key to the output JSON */
			outBuilder.append(HDLmJson.getJsonValue(value));
		}
		/* Add the final right brace */
		outBuilder.append('}');		
		return outBuilder.toString();
	}
	/* This routine build the heading HTML and returns it to the
     caller. This routine can be used with anything, not just the
     session ID cache or the perceptual hash status. */
	protected static String headingHtml(String localServer, 
			                                String prefixStr,
			                                String infoType,
			                                String clientStr) {
	 /* Build the heading text */
	 String  text = HDLmHtml.headingText(localServer, 
	 		                                 prefixStr,
	 		                                 infoType,
	 		                                 clientStr);
	 String  heading = "h2";
	 String  rv = HDLmHtml.buildHeading(heading, text);
	 return rv;
	}
	/* This routine build the heading text and returns it to the
	   caller. This routine can be used with anything, not just the
	   session ID cache or the perceptual hash status. */
	protected static String headingText(String localServer,
			                                String prefixStr,
			                                String infoType,
			                                String clientStr) {
	  /* Build the heading text */
	  StringBuilder  rv = new StringBuilder();
	  rv.append(prefixStr);
	  rv.append(" ");
	  rv.append(infoType);
	  rv.append(" ");
	  rv.append("information for ");
	  rv.append(localServer);
	  rv.append(" (via ");
	  rv.append(clientStr);
	  rv.append(')');
	 return rv.toString();
	}
	/* Get a set of nodes that match a CSS Selector */
  protected static Elements querySelectorAll(Document htmlDom, String cssSelector) {
  	return htmlDom.select(cssSelector);  	
  }
  /* The input to this routine is HTML. This routine removes
     all of the HTML tags passed by the caller and returns a
     string with no HTML tags. */
  protected static String  removeHtmlTags(String htmlStr) {
		/* Check one or more values passed by the caller */
		if (htmlStr == null) {
			String   errorText = "HTML string reference passed to removeHtmlTags is null";
			throw new NullPointerException(errorText);			
		}
		/* Create a string builder for use below */
		StringBuilder   textBuilder = new StringBuilder();
		boolean         tagActive = false;
		/* Process each character of the input string */
		int   htmlLength = htmlStr.length();
		for (int i = 0; i < htmlLength; i++) {
			char  curChar = htmlStr.charAt(i);
			/* Check for the start of a tag */
			if (curChar == '<') {
				tagActive = true;
				textBuilder.append(' ');
			}
			/* Check for the end of tag */
			else if (curChar == '>')
				tagActive = false;
			/* Check if we are not in a tag */
			else if (tagActive == false)
				textBuilder.append(curChar);
		}		
		/* Convert multiple whitespace chracters to one blank */
    String  regexStr = "\\s+";
    /* Compile the regular expression */
    Pattern   patternObj = Pattern.compile(regexStr);
    /* Retrieve the matcher object */
    Matcher   matcherObj = patternObj.matcher(textBuilder.toString());
    /* Replace all of the whitespace characters with single space */
    String  resultStr = matcherObj.replaceAll(" "); 
    /* Now we need to 'fix' the result string by removing (possible)
       leading and trailing blanks */ 
    if (resultStr.startsWith(" "))
    	resultStr = resultStr.substring(1);
    if (resultStr.endsWith(" ")) {
    	int   resultLen = resultStr.length();
    	resultStr = resultStr.substring(0, resultLen-1);
    }	
		/* Return the output text to the caller */
		return resultStr; 	
  }
  /* The input to this routine is HTML. This routine removes
     all of the HTML tags passed by the caller and returns a
     string with no HTML tags. This routine also removes some
     other things from the HTML passed by the caller. */
  protected static String  removeHtmlTagsAndOtherThings(String htmlStr) {
		/* Check one or more values passed by the caller */
		if (htmlStr == null) {
			String   errorText = "HTML string reference passed to removeHtmlTagsAndOtherThings is null";
			throw new NullPointerException(errorText);			
		}
		/* Build a regex that matches everything we need to match */
		String  mainRegexStr = "(?s)"; 
		mainRegexStr += "(\\s*<script[^>]*>.*?</script>\\s*)";
		mainRegexStr += "|";
		mainRegexStr += "(\\s*<noscript[^>]*>.*?</noscript>\\s*)";
		mainRegexStr += "|";
		mainRegexStr += "(\\s*<style[^>]*>.*?</style>\\s*)";
		mainRegexStr += "|";
		mainRegexStr += "(<[^>]*>)";
		/* Replace all of the things we match on with one blank */ 
		String  outText = htmlStr.replaceAll(mainRegexStr, " | ");
		/* Replace all sequences of multiple whitespace characters with one blank */
		outText = outText.replaceAll("\\s+", " ");
		/* Convert multiple vertical bar characters to one vertical bar */
		outText = outText.replaceAll("( \\|)+", " |");
	  /* Now we need to 'fix' the result string by removing (possible)
	     leading and trailing blanks and other characters */ 
		outText = HDLmString.removeChars(outText, " |", HDLmStringTypes.LEADING);
		outText = HDLmString.removeChars(outText, " |", HDLmStringTypes.TRAILING);
		/* Return the output text to the caller */
		return outText; 	
	}
  /* The input to this routine is HTML. This routine removes
     all of the HTML tags passed by the caller and returns a
     string with no HTML tags. This routine also removes all
     inline JavaScript scripts from the HTML. */
	protected static String  removeHtmlTagsAndScripts(String htmlStr) {
		/* Check one or more values passed by the caller */
		if (htmlStr == null) {
			String   errorText = "HTML string reference passed to removeHtmlTagsAndScripts is null";
			throw new NullPointerException(errorText);			
		}
		/* Create a string builder for use below */
		StringBuilder   textBuilder = new StringBuilder();
		boolean         scriptActive = false;
		boolean         tagActive = false;
		/* Process each character of the input string */
		int   htmlLength = htmlStr.length();
		for (int i = 0; i < htmlLength; i++) {
			char  curChar = htmlStr.charAt(i);
			/* Check for the start of a tag */
			if (curChar == '<') {
				tagActive = true;
				textBuilder.append(' ');
				/* Check for the start of an inline JavaScript script */
				if ((i+7) <= htmlLength) {
				  String  tagStr = htmlStr.substring(i, i+7);
				  if (tagStr.contentEquals("<script")) 
				  	scriptActive = true;
				}
				/* Check for the end of an inline JavaScript script */
				if ((i+9) <= htmlLength) {
				  String  tagStr = htmlStr.substring(i, i+9);
				  if (tagStr.contentEquals("</script>")) 
				  	scriptActive = false;
				}
				/* Check for the start of an inline JavaScript noscript */
				if ((i+9) <= htmlLength) {
				  String  tagStr = htmlStr.substring(i, i+9);
				  if (tagStr.contentEquals("<noscript")) 
				  	scriptActive = true;
				}
				/* Check for the end of an inline JavaScript noscript */
				if ((i+11) <= htmlLength) {
				  String  tagStr = htmlStr.substring(i, i+11);
				  if (tagStr.contentEquals("</noscript>")) 
				  	scriptActive = false;
				}
			}
			/* Check for the end of tag */
			else if (curChar == '>')
				tagActive = false;
			/* Check if we are not in a tag */
			else if (tagActive == false && scriptActive == false)
				textBuilder.append(curChar);
		}		
		/* Convert multiple whitespace chracters to one blank */
	  String  regexStr = "\\s+";
	  /* Compile the regular expression */
	  Pattern   patternObj = Pattern.compile(regexStr);
	  /* Retrieve the matcher object */
	  Matcher   matcherObj = patternObj.matcher(textBuilder.toString());
	  /* Replace all of the whitespace characters with single space */
	  String  resultStr = matcherObj.replaceAll(" "); 
	  /* Now we need to 'fix' the result string by removing (possible)
	     leading and trailing blanks */ 
	  if (resultStr.startsWith(" "))
	  	resultStr = resultStr.substring(1);
	  if (resultStr.endsWith(" ")) {
	  	int   resultLen = resultStr.length();
	  	resultStr = resultStr.substring(0, resultLen-1);
	  }	
		/* Return the output text to the caller */
		return resultStr; 	
	}
  /* This routine sets a set of HTML related properties. This routine
     only needs to be called once. The  */
	protected static void  setProperties() {
		HDLmUtility.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
	}
}
package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import org.apache.commons.lang3.SystemUtils;
import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http2.parser.WindowRateControl;
import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
import org.eclipse.jetty.http3.server.HTTP3ServerConnectionFactory;
import org.eclipse.jetty.http3.server.HTTP3ServerConnector;
import org.eclipse.jetty.io.ConnectionStatistics;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnectionStatistics;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.component.Container;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.MonitoredQueuedThreadPool;
import org.eclipse.jetty.websocket.server.config.JettyWebSocketServletContainerInitializer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.Servlet;
/**
 * HDLmMain short summary.
 *
 * HDLmMain description.
 *
 * @version 1.0
 * @author Peter
 *
 * Used (moved to index.html in WebApplication5)
 */
/* Things this class must do (moved to index.html in WebApplication5) */
/* This is a purely static class and no instances of this class
   can ever be created */
public class HDLmMain {
	/* The next statement causes detailed logging initialization messages
	   to be written to the console. These messages are only needed for
	   debugging logging initialization. These messages are not needed
	   in all other cases. */
	/* private static String   setStatusLogger = System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level",
	                                                                "trace"); */
	/* The next statement tells the logging system where to find the
	   configuration file used to initialize logging. This statement
	   does not appear to be needed. The logging system seems to find
	   the correct properties file without any help.

	   In the Windows environment, logging initialization won't look
  	 in the correct directory for the right properties file. As a
  	 consequence, we must specify the correct location. Note that
  	 for Linux, the manifest file that is used to build the jar
  	 file provides the correct location. */
	/* private static String   setConfigurationFile = System.setProperty("log4j.configurationFile",
			   (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS) ?
         "C:\\Users\\pscha\\HeadlampJetty\\workspace-4.33.0\\ProxyServerA\\log4j2.properties" :
         "C:\\Users\\pscha\\HeadlampJetty\\workspace-4.33.0\\ProxyServer\\log4j2.properties"); */
	/* The next statement initializes logging to some degree. Note that
	   having the slf4j jars and the log4j jars in the classpath also
	   plays some role in logging initialization. */
	private static final Logger LOG = LoggerFactory.getLogger(HDLmMain.class);
	/* A reference to the main Jetty server is stored in the field below
	   after the Jetty server is created. This reference can be used by
	   other routines as need be. Note that this reference will initially
	   be a null value. Later it will (hopefully) change to a real value. */
	private static Server             savedServerReference = null;
	/* A reference to the main set of Jetty statistics is stored in the field
	   below after the Jetty server is created. This reference can be used by
	   other routines as need be. Note that this reference will initially
	   be a null value. Later it will (hopefully) change to a real value. */
  private static StatisticsHandler  savedStatisticsReference = null;
  /* The next field contains the number of times a certain routine has
     been invoked. Of course, multiple threads might attempt to use and
     update this field concurrently. This will lead to values that are
     not correct. However, this field does not have to be correct. */
  private static int                GetModsCount = 0;
  /* The next field is set to a specific values as part of startup.
     This value will be correct, even if we are running under Docker.
     This value can be checked at anytime after startup. If we are
     running in a Docker container, this is the type of the parent
     operating system, not the operating system provided by the
     Docker container. */
  private static HDLmOsTypes        osType = HDLmOsTypes.NONE;
  /* The next field shows if we are running in a Docker
     container of some kind. This value can be checked
     at anytime after startup. */
  private static boolean            containerActive = false;
  /* The next field shows if we are running under Junit. This 
     field is not automatically set. This field must be set by
     calling a special routine that sets it. This field is 
     checked by a special utility routine for this purpose. */ 
  private static boolean            junitActive = false;
  /* The next field contains the time (a Java Instant) when the modification
     rules were last reloaded. In some cases, we reload the modification rules
     after a sufficient interval of time has pased. The default time interval
     is 60 seconds. */
  private static Instant            reloadRulesInstant = Instant.now();
  /* The next set of fields contain gson instances that can be reused
     as need be. These instances are created as part of product startup
     and then used/reused as need be later. */
  protected static Gson             gsonMain = new Gson();
  protected static Gson             gsonSerializeNullsMain = new GsonBuilder().serializeNulls().create();
  protected static Gson             gsonPrettyPrintingMain = new GsonBuilder().setPrettyPrinting().create();
  /* This class can never be instantiated */
	private HDLmMain() {}
  /* This code gets a list of application threads and some application
     information about each thread. The information is returned to the
     user. */
	protected static String appthrContents(String localServer, String clientStr) {
		int     threadCount = 0;
		int     threadLimit = 5000;
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the application threads heading HTML */
		String  infoType = "threads";
		String  prefixStr = "Application";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
			                                         prefixStr,
			                                         infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a set of all of the current application threads */
		var  threadTreeContents = HDLmThreadStatus.getMap();
	  var  threadListContents = new ArrayList<String>();
	  /* Process each entry (application thread) in the tree map */
	  for (var threadEntry : threadTreeContents.entrySet()) {
	 	  /* Check if we have reached the limit of the number of threads
	       we will display */
	    if (threadCount >= threadLimit)
		    break;
		  threadListContents.add(threadEntry.getKey());
		  HDLmThreadStatus  threadStatusEntry = threadEntry.getValue();
		  String            threadTimeStamp = threadStatusEntry.getTimeStamp();
		  if (threadTimeStamp != null)
		    threadListContents.add(threadTimeStamp);
		  else
		  	threadListContents.add("None");
		  String            threadStatus = threadStatusEntry.getStatus();
		  if (threadStatus != null)
		    threadListContents.add(threadStatus);
		  else
		  	threadListContents.add("None");
		  threadCount++;
	  }
	  /* Build an ArrayList with all of the headings */
	  ArrayList<String>  headings = new ArrayList<String>(List.of("Name", "Time",
			                                                          "State"));
	  /* Construct an HTML table from the headings and the thread contents
	     list */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, threadListContents);
		rv.append(tableHtml);
	  /* End the main div */
	  rv.append("</div>");
	  return rv.toString();
	}
  /* This code returns some application thread statistics HTML to the caller */
  protected static String appthrStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the thread statistics status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Application";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a reference to the Jetty server */
		var  server = HDLmMain.getServer();
		if (server == null) {
			String   errorText = "Sever reference obtained from getServer is null";
			HDLmAssertAction(false, errorText);
	  }
		/* Build the tree of application thread statistics */
		var  statsTreeStats = HDLmThreadStatus.getStatistics();
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Construct an HTML table from the headings and the statistics tree map */
		tableHtml = HDLmHtml.buildHtmlTableFromTree(headings, statsTreeStats);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
  /* Do all of the work that must be completed before we attempt
	   to handle the first actual HTML modification. The work includes
	   building the real DOM from the HTML. Tell the caller if this step
	   or these steps were executed with any errors or not.

	   Note that the DOM ia passed by reference here. This is required
	   because the DOM is actually built in this code. The modified value
	   must be available to the caller for use later. */
	protected static boolean beforeFirst(String htmlStr, Document dom) {
	  boolean  firstOk = false;
	  /* Build a DOM from the HTML */
	  Document   htmlDoc = Jsoup.parse(htmlStr);
	  firstOk = true;
	  return firstOk;
	}
	/* This routine builds a tree of node information and returns
	   the top tree node to the caller. The actual tree data is
	   used in all cases. Note that a Curl request is used to obtain
	   the current (most updated) node data (in JSON form). The node
	   data (in JSON form) is then used to build the actual tree of
	   nodes. Note that this routine does not set or reset the top
	   tree node value. */
	protected static HDLmTree  buildNodeTreeMain(HDLmEditorTypes editorType, HDLmStartupMode startupMode) {
		return HDLmMain.buildNodeTreeMain(null, editorType, startupMode);
	}
	protected static HDLmTree  buildNodeTreeMain(String jsonStr,
			                                         HDLmEditorTypes editorType,
			                                         HDLmStartupMode startupMode) {
		/* Check a few values passed by the caller */
		if (jsonStr == null && jsonStr != null) {
			String   errorText = "JSON string reference passed to buildNodeTreeMain is null";
			throw new NullPointerException(errorText);
		}
		if (editorType == null) {
			String   errorText = "Editor type value passed to buildNodeTreeMain is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		/* Check if the startup mode value passed by the caller is null */
		if (startupMode == null) {
			String   errorText = "Startup mode value passed to buildNodeTreeMain is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the startup mode passed by the caller is invalid */
		if (startupMode == HDLmStartupMode.NONE) {
		  HDLmAssertAction(false, "Startup mode value passed to buildNodeTreeMain is invalid");
		}
		HDLmTree       topNode = null;
		String         colName;
		String         jsonOut;
		/* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
		/* Get a few values needed below. Note that the caller can
		   provide a non-null JSON string for unit testing. */
		if (logIsDebugEnabled) {
		  LOG.debug("In HDLmMain.buildNodeTreeMain");
		  LOG.debug(jsonStr);
		}
		if (jsonStr == null) {
			/* Get the needed JSON */
			colName = "content";
			if (logIsDebugEnabled)
		  	LOG.debug(editorType.toString());
			jsonOut = HDLmMain.getJsonAllRowsFromSource(colName, editorType);
			if (logIsDebugEnabled)
			  LOG.debug(jsonOut);
		}
		else
			jsonOut = jsonStr;
		/* Build the tree of HDLmTree nodes from some JSON */
	  topNode = HDLmTree.addToTree(jsonOut, editorType, startupMode);
		/* We now need to get the procedural hash values from every HDLm tree
	     element that has one. Of course, many do not. */
    HDLmProcessTreePHash  pHashInstance = new HDLmProcessTreePHash();
	  HDLmTree.processTree(topNode, pHashInstance);
		return topNode;
	}
	/* Check if we are running under Eclipse. This is done by looking for
	   a rather specific property that will be set, if we are running
	   under Eclipse. This code does not appear to work. We do not set
	   the system property and at present we don't know how to set the
	   system property. */
	protected static boolean checkForEclipse() {
		String  hdlmRunUnderEclipse = System.getProperty("HDLmRunUnderEclipse");
		if (hdlmRunUnderEclipse == null)
			return false;
		return "true".equalsIgnoreCase(hdlmRunUnderEclipse);
	}
	/* Process all of the values associated with one replace modification.
	   We need to consider three cases. Fisrt, the value may be a zero-
	   length string. This is not an error condition. Zero-length strings
	   are used to indicate that no changes to the target DOM element are
	   needed or wanted. Second, the value may be an actual JSON string.
	   If this is true, then the JSON string is just used. Third, the value
	   may be the name of a saved data value. The saved data value is found
	   and used as a replacement data value. */
	protected static void  changeReplaceValues(final String company,
																				     final String division,
																				     final String site,
																				     HDLmMod mod) {
		/* Check a few values passed by the caller */
		if (company == null) {
		  String   errorText = "Modification company reference passed to changeReplaceValues is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if a null division was passed to this routine */
		if (division == null) {
		  String   errorText = "Modification division reference passed to changeReplaceValues is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if a null site was passed to this routine */
		if (site == null) {
		  String   errorText = "Modification site reference passed to changeReplaceValues is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if a null modification was passed to this routine */
		if (mod == null) {
		  String   errorText = "Modification reference passed to changeReplaceValues is null";
		  throw new NullPointerException(errorText);
		}
		/* Replace the modification values, if need be */
		ArrayList<String>   values = mod.getValues();
		boolean   valuesChange = false;
		HDLmTree  topTreeNode = HDLmTree.getNodePassTreeTop();
		int       valuesSize = mod.getValuesCount();
		int       valuesIndex;
		String    currentValue;
		/* Scan all of the values of the current replace rule */
		for (valuesIndex = 0; valuesIndex < valuesSize; valuesIndex++) {
		  /* Get the current value for checking below */
		  currentValue = values.get(valuesIndex);
		  int   currentValueLength = currentValue.length();
		  /* Check if the length of the current value is actually zero.
		     This case is allowed. It means no change to original DOM
		     element. */
		  if (currentValueLength == 0)
		    continue;
			/* Check if we already have a value that we can use */
			if (currentValueLength > 0 &&
			    currentValue.charAt(0) == '{')
			  continue;
			/* Build the node path for locating the data value */
			ArrayList<String>   savedValueNodePath = new ArrayList<String>();
			savedValueNodePath.add(HDLmDefines.getString("HDLMTOPNODENAME"));
			savedValueNodePath.add(HDLmDefines.getString("HDLMCOMPANIESNODENAME"));
			savedValueNodePath.add(company);
			savedValueNodePath.add(HDLmDefines.getString("HDLMDATANODENAME"));
			savedValueNodePath.add(division);
			savedValueNodePath.add(site);
			savedValueNodePath.add(currentValue);
			/* Try to find the node in the node tree we are looking for */
			HDLmTree  savedValueEntryNode = HDLmTree.locateTreeNode(topTreeNode, savedValueNodePath);
			/* Report an error if the node could not be found */
			String  errorFormat = null;
			String  errorText = null;
			if (savedValueEntryNode == null) {
			  errorFormat = "Saved value (%s) not found for rule (%s)";
			  errorText = String.format(errorFormat, currentValue, mod.getName());
			  LOG.info(errorText);
      }
			/* Get the saved data value from the saved value tree node */
			String  savedValue;
			if (savedValueEntryNode != null)
			  savedValue = savedValueEntryNode.getDetails().getValue();
			else
			  savedValue = "{\"type\":\"Element\",\"tag\":\"p\",\"text\":\"" + errorText + "\",\"subnodes\":[]}";
			values.set(valuesIndex, savedValue);
			valuesChange = true;
		}
		/* Replace the modification values, if need be */
		if (valuesChange)
		  mod.setValues(values);
  }
	/* Scan a list of modifications looking for modifications with
	   a type of replace. Replace modifications are copied and the
	  c opies are changed to include actual saved data values. */
	protected static void  checkForReplace(final String company,
	                                       final String division,
	                                       final String site,
	                                       ArrayList<HDLmMod> mods) {
		/* Check a few values passed by the caller */
		if (company == null) {
			String   errorText = "Modifications company reference passed to checkForReplace is null";
			throw new NullPointerException(errorText);
		}
		/* Check if a null division was passed to this routine */
		if (division == null) {
			String   errorText = "Modifications division reference passed to checkForReplace is null";
			throw new NullPointerException(errorText);
		}
		/* Check if a null site was passed to this routine */
		if (site == null) {
			String   errorText = "Modifications site reference passed to checkForReplace is null";
			throw new NullPointerException(errorText);
		}
		/* Check if a null modifications array list was passed to this routine */
		if (mods == null) {
			String   errorText = "Modifications array list reference passed to checkForReplace is null";
			throw new NullPointerException(errorText);
		}
		HDLmMod   mod;
		HDLmMod   replaceMod;
		int   modsSize = mods.size();
		int   modsIndex;
		/* Scan all of the modifications, looking for a type of replace */
	  for (modsIndex = 0; modsIndex < modsSize; modsIndex++) {
	   	mod = mods.get(modsIndex);
	 	  if (mod.getType() != HDLmModTypes.REPLACE)
	      continue;
	 	  /* we don't really want to change anything in the node tree.
	 	     As a consequence, actual changes are only made to copies
	 	     of the replace modifications. */
	 	  replaceMod = new HDLmMod(mod);
	 	  replaceMod.setIfNotSetTimes();
	 	  changeReplaceValues(company, division, site, replaceMod);
	 	  /* Update the modifications list with the now changed
	 	     modification */
	 	  mods.set(modsIndex, replaceMod);
	  }
	}
	/* This routine returns either true or false. This routine returns
	   the main executed flag. This flag will be false, if main has
	   never been executed. This flag will be true if main has been
	   executed. */
  protected static boolean  checkMainExecuted() {
  	return (HDLmState.checkString("mainExecuted") != null &&
  			    HDLmStateInfo.getMainExecuted().equals("yes"));
  }
	/* This code sets the overall pass-through status for the
	   server. This code has the effect of enabling all of
	   the current rules unless pass-through is set at the
	   company level. In other words, pass-through mode is
	   turned off. */
	protected static String checkPassThru(String localServer,
			                                  String clientStr,
			                                  HttpServletRequest request) {
		/* Check a few values passed by the caller */
		if (localServer == null) {
			String   errorText = "Local server reference passed to checkPassThru is null";
			throw new NullPointerException(errorText);
		}
		if (clientStr == null) {
			String   errorText = "Client string reference passed to checkPassThru is null";
			throw new NullPointerException(errorText);
		}
		if (request == null) {
			String   errorText = "Request (ServletRequest) reference passed to checkPassThru is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		HDLmUtilityResponse   response;
		String                browser = "firefox";
		String                hostName;
		String                results;
		/* Check if the user specified a browser to be used */
    if (request.getParameterMap().containsKey("browser")) {
      /* Get the browser name from the URL */
      browser = request.getParameter("browser");
    }
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
	  	/* Check if the user provided the target host name */
      if (!request.getParameterMap().containsKey("host")) {
        results = "Host name missing from the command URL";
        break;
      }
      /* Get the target host name from the URL */
      hostName = request.getParameter("host");
  		/* Check for Windows versus Unix (of some kind) */
      String  curLine;
  		if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS &&
  				HDLmMain.isDockerFlagSet() == false) {
			  /* Build the command line to be executed below */
			  curLine = "";
			  curLine += "py";
			  curLine += " ";
			  curLine += "\"C:\\Users\\pscha\\Documents\\Visual_Studio_Code\\Projects\\PythonApps\\PythonApps\\SeleniumTest2.py\"";
			  curLine += " ";
			  curLine += browser;
			  curLine += " ";
			  curLine += hostName;
			  curLine += " ";
			  curLine += "-e -i";
  		}
  		/* The following path is correct for our AWS Linux instance */
  		else {
			  /* Build the command line to be executed below */
			  curLine = "";
			  /* Get a string with the current system character. This string
			     can be used to determine which version of Python we should
			     use. */
			  String  systemCharacter = HDLmStateInfo.getSystemValue();
			  if (systemCharacter.equals("b"))
			    curLine += "/usr/bin/python3.6";
			  else
			  	curLine += "/usr/bin/python3.8";
			  curLine += " ";
			  curLine += "\"/home/ubuntu/Documents/Visual_Studio_Code/Projects/PythonApps/PythonApps/SeleniumTest2.py\"";
			  curLine += " ";
			  curLine += browser;
			  curLine += " ";
			  curLine += hostName;
			  curLine += " ";
			  curLine += "-e -i";
  		}
		  /* Try to run the Python program */
		  response = HDLmUtility.runProcess(curLine, true);
		  /* Check the results from running the external program */
		  if (response.getExecuteMessage()     != null ||
		  		response.getIOExceptionMessage() != null) {
		  	/* Log the failure messages */
		  	if (response.getExecuteMessage() != null) {
		  		LOG.error("Execute message - " + response.getExecuteMessage());
		  	}
		  	if (response.getIOExceptionMessage() != null) {
		  		LOG.error("I/O exception nessage - " + response.getIOExceptionMessage());
		  	}
		  	/* Display the text written to standard error and standard output */
			  LOG.info(response.getStdOut());
			  LOG.info(response.getStdErr());
		  	results = "Failed";
		  	HDLmPassThruTop.reportErrors(hostName,
		  			                         response.getStdOut(),
		  			                         response.getStdErr(),
		  			                         response.getExecuteMessage(),
		  			                         response.getIOExceptionMessage());
		  }
		  /* The external program worked. We can send the output from the
		     external program (a report) to a routine that will check the
		     report. */
		  else {
		    /* Show that the command succeeded and process the command output */
		    results = "Succeeded";
		    HDLmPassThruTop.checkWebSite(hostName, response.getStdOut());
		  }
		  break;
		}
		/* Declare and define a few variables */
		String        tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the set pass-through status heading HTML */
		String  infoType = "site";
		String  prefixStr = "Check web";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
	  ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Build a (short) list of the command execution results */
	  var  actionListContents = new ArrayList<String>();
	  actionListContents.add("Command");
	  actionListContents.add(results);
	  /* Construct an HTML table from the headings and the command execution
	     results */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, actionListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* Check for some type of production system. We consider a system
 	   to be a 'production' system, if the first letter of the current
	   environment is 'p' */
	protected static boolean  checkProductionSystem() {
		boolean   productionActive = false;
		String    currentEnvironment = HDLmConfigInfo.getCurrentEnvironment();
		/* Check for some type of production environment */
		if (currentEnvironment != null) {
			String   currentEnvironmentLower = currentEnvironment.toLowerCase();
			if (currentEnvironmentLower.length() > 0) {
				char  firstChar = currentEnvironmentLower.charAt(0);
				if (firstChar == 'p')
					productionActive = true;
			}
		}
		return productionActive;
	}
	/* This routine returns either true or false. This routine returns
	   true if the server reference is set, this routine returns false
	   if the server reference is not set. */
  protected static boolean  checkServer() {
	  return (savedServerReference != null);
  }
  /* This code uses a hierarchical clustering mechanism for finding and
     using clusters. As a consequence, we need mechanisms for monitoring
     the clusters. Note that the clusters are not saved anywhere. They are
     determined dynamically. The function below is one of those mechanisms
     for reporting on the clusters. */
	protected static String clustersContents(String localServer, String clientStr) {
		int     clustersCount = 0;
		int     clustersLimit = 100;
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the clusters contents heading HTML */
		String  infoType = "content";
		String  prefixStr = "Clusters";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
			                                         prefixStr,
			                                         infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a reference to the set of clusters we want to display */
		var  clustersList = HDLmClustering.getClustersDefault();
		var  clustersListContents = new ArrayList<String>();
	  /* Build the clusters list from the list of clusters */
	  for (var clustersEntry : clustersList) {
	  	/* Check if we have reached the limit of the number of clusters
	  	   we will display */
	  	if (clustersCount >= clustersLimit)
	  		break;
	   	clustersListContents.add(clustersEntry.getClusterName());
	 	  /* Add the average for the current row */
	   	clustersListContents.add(clustersEntry.getClusterAverage());
	   	/* Add the size for the current row */
	   	clustersListContents.add(Integer.toString(clustersEntry.getClusterSize()));
	   	/* Add the minimum for the current row */
	   	clustersListContents.add(Double.toString(clustersEntry.getClusterMin()));
	   	/* Add the maximum for the current row */
	   	clustersListContents.add(Double.toString(clustersEntry.getClusterMax()));
	 	  clustersCount++;
	  }
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Name", "Average", "Size", "Minimum", "Maximum"));
		/* Construct an HTML table from the headings and the clusters list */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, clustersListContents);
		rv.append(tableHtml);
	  /* End the main div */
	  rv.append("</div>");
    return rv.toString();
  }
	/* This code returns some connection statistics HTML to the caller */
	protected static String connectionStatus(String localServer, String clientStr) {
		int  connectorCount = 0;
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the connection statistics status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Connections";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
                                               infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Get a reference to the Jetty server */
		var  server = HDLmMain.getServer();
		if (server == null) {
			String   errorText = "Sever reference obtained from getServer is null";
			HDLmAssertAction(false, errorText);
    }
		/* Process each of the connectors of the Jetty server */
    for (Connector connector : server.getConnectors()) {
    	/* Assume we can't get any connection statistics */
      ConnectionStatistics  connectionStats = null;
      if (connector instanceof Container)
        connectionStats = ((Container) connector).getBean(ConnectionStatistics.class);
      if (connectionStats == null)
      	continue;
      /* Increment the connector count */
      connectorCount++;
      /* Add a blank line before each set of connector statistics
         except for the first */
      if (connectorCount > 1)
      	rv.append(HDLmHtml.buildBlankLine());
  		/* Build the tree of statistics */
  		var  connectionTreeStats = HDLmConnectionStatistics.getStatistics(connectionStats);
  		tableHtml = HDLmHtml.buildHtmlTableFromTree(headings, connectionTreeStats);
  		rv.append(tableHtml);
    }
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* This code sets the overall pass-through status for the
	   server. This code has the effect of enabling all of
	   the current rules unless pass-through is set at the
	   company level. In other words, pass-through mode is
	   turned off at the server level. */
	protected static String disablePassThruCmdResponse(String localServer, String clientStr) {
		/* Check if the local server string passed by the caller is null */
		if (localServer == null) {
			String  errorText = "Local server string passed to disablePassThru is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to disablePassThru is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		HDLmPassThruStatus  newPassThruStatus = HDLmPassThruStatus.PASSTHRUNOTOK;
		String              tableHtml;
		/* Set of reset the pass-through status */
		HDLmMain.setPassThruStatus(newPassThruStatus);
		/* Set or reset force pass-through status */
		HDLmConfigInfo.setForcePassThru(false);
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the set pass-through status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Reset pass-through";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
	  ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Build a (short) list of the command execution results */
	  var  actionListContents = new ArrayList<String>();
	  actionListContents.add("Command");
	  actionListContents.add("Succeeded");
	  /* Construct an HTML table from the headings and the command execution
	     results */
	 	tableHtml = HDLmHtml.buildHtmlTableFromList(headings, actionListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* This code starts the pass-through display. This code obtains
	   an HTML file and returns it to the client. The HTML file
	   manages the pass-through display. */
	protected static String displayPassThruCmdResponse(String localServer,
									                                   String clientStr,
									                                   HttpServletRequest request,
									                                   HttpServletResponse response) {
		/* Check if the local server string passed by the caller is null */
		if (localServer == null) {
			String  errorText = "Local server string passed to displayPassThruCmdResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to displayPassThruCmdResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to displayPassThruCmdResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response instance passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet response passed to displayPassThruCmdResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
    /* Pass the current request off to a standard routine */
		HDLmJetty.editorGet(request, response);
	  return "";
	}
	/* This code returns some elapsed time statistics HTML to the caller */
	protected static String elapsedStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the elapsed time statistics status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Elapsed time";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
                                               infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Elapsed Time<br/>Name", "Elapsed Time<br/>Type",
				                                                        "Elapsed Time<br/>Count",
				                                                        "Timestamp", "Elapsed Time<br/>Average",
				                                                        "Elapsed Time<br/>Standard Deviation"));
		/* Get a reference to the map of elapsed time information */
		var  elapsedMap = HDLmElapsed.getElapsedMap();
		if (elapsedMap == null) {
			String   errorText = "Elapsed time map reference obtained from getElapsedMap is null";
			HDLmAssertAction(false, errorText);
    }
    /* Create an elapsed time comparator for use in building/maintaining the
       tree elapsed time set */
		class elapsedComparator implements Comparator<HDLmElapsed> {
			@Override
			public int compare(HDLmElapsed e1, HDLmElapsed e2) {
				int   compareInt;
				compareInt = e1.getType().getValue() - e2.getType().getValue();
				if (compareInt == 0)
					compareInt = e1.getName().compareTo(e2.getName());
				return compareInt;
			}
	  }
		/* Create a new elapsed time set that uses the comparator we just created */
		TreeSet<HDLmElapsed>  elapsedSet = new TreeSet<HDLmElapsed>(new elapsedComparator());
	  /* Process each elapsed time entry in the elapsed time map. Each elapsed time
	     entry in the elapsed time map is copied into the elapsed time set. This has
	     the effect of creating an elapsed time set with all of the correct vales in
	     the correct order. */
	  for (HDLmElapsed curElapsed : elapsedMap.values())
	  	elapsedSet.add(curElapsed);
  	/* Build a (short) list of the elapsed time status information */
    var  statusListContents = new ArrayList<String>();
		/* Process each elapsed time entry in the elapsed time set. Note that
		   the elapsed time set is in the desired order. */
    for (HDLmElapsed curElapsed : elapsedSet) {
    	/* Get a few values from the current elapsed time entry */
    	String          elapsedName = curElapsed.getName();
    	HDLmElapsedTypes  elapsedType = curElapsed.getType();
    	String          elapsedTypeStr = elapsedType.toPrint();
    	long            elapsedCount = curElapsed.getCount();
    	String          elapsedTimestamp = curElapsed.getTimestamp();
    	/* Add the current elapsed time name */
    	statusListContents.add(elapsedName);
    	statusListContents.add(elapsedTypeStr);
    	/* Check if we actually have an elapsed time timestamp */
    	if (elapsedTimestamp != null) {
    		/* Add the elapsed time entry count */
    		String  elapsedCountStr = String.valueOf(elapsedCount);
    		statusListContents.add(elapsedCountStr);
    		/* Add the timestamp for the current elapsed time entry */
    		ZonedDateTime       elapsedZonedDateTime = Instant.parse(elapsedTimestamp).atZone(ZoneId.systemDefault());
    		DateTimeFormatter   elapsedFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss.SSS");
    		String              elapsedFormattedString = elapsedZonedDateTime.format(elapsedFormatter);
    		statusListContents.add(elapsedFormattedString);
    		/* Get the average amount of time this elapsed time entry took.
    		   The value will come back as a double, so no further work is
    		   needed to convert the value to seconds. */
    		double  elapsedAverageDouble = curElapsed.getAverageElapsedTime();
    		statusListContents.add(String.valueOf(elapsedAverageDouble));
    		/* Get the standard deviation of the amount of time this elapsed
    		   time entry took. The value will come back as a double, so no
    		   further work is needed to convert the value to seconds. */
 		    double  elapsedStdDouble = curElapsed.getStdElapsedTime();
 		    statusListContents.add(String.valueOf(elapsedStdDouble));
    	}
    	/* The elapsed time timestamp value is not set. Note
    	   that we treat the count as a missing value in this
    	   case even though we actually have a count value
    	   (zero) that could be used. */
    	else {
  		  statusListContents.add("-");
  		  statusListContents.add("-");
  		  statusListContents.add("-");
  		  statusListContents.add("-");
    	}
    }
    /* Construct an HTML table from the headings and the elapsed time status list */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, statusListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* This code sets the overall pass-through status for the
	   server. This code has the effect of disabling all of
	   the current rules for all companies. In other words,
	   pass-through mode is turned on at the server level. */
	protected static String enablePassThruCmdResponse(String localServer, String clientStr) {
		/* Check if the local server string passed by the caller is null */
		if (localServer == null) {
			String  errorText = "Local server string passed to enablePassThru is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to enablePassThru is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		HDLmPassThruStatus  newPassThruStatus = HDLmPassThruStatus.PASSTHRUOK;
		String              tableHtml;
		/* Set or reset the pass-through status */
		HDLmMain.setPassThruStatus(newPassThruStatus);
		/* Set or reset force pass-through status */
		HDLmConfigInfo.setForcePassThru(true);
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the set pass-through status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Set pass-through";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
	  ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Build a (short) list of the command execution results */
	  var  actionListContents = new ArrayList<String>();
	  actionListContents.add("Command");
	  actionListContents.add("Succeeded");
	  /* Construct an HTML table from the headings and the command execution
	     results */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, actionListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* This code checks for rule firing anomalies and returns a HTML
	   response with all of the rules that have not fired */
	protected static String eventsCheckAnomalies(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Get a reference to the map of events information */
		var  eventsMap = HDLmEvent.getEventsMap();
		if (eventsMap == null) {
			String   errorText = "Events map reference obtained from getEventsMap is null";
			HDLmAssertAction(false, errorText);
    }
    /* Create an event comparator for use in building/maintaining the
       tree event set */
		class eventComparator implements Comparator<HDLmEvent> {
			@Override
			public int compare(HDLmEvent e1, HDLmEvent e2) {
				int   compareInt;
				compareInt = e1.getType().getValue() - e2.getType().getValue();
				if (compareInt == 0)
					compareInt = e1.getName().compareTo(e2.getName());
				return compareInt;
			}
	  }
		/* Create a new events set that uses the comparator we just created */
		TreeSet<HDLmEvent>  eventsSet = new TreeSet<HDLmEvent>(new eventComparator());
	  /* Process each event in the events map. Each event in the events map
	     is copied into the events set. This has the effect of creating an
	     events set with all of the correct vales in the correct order. */
	  for (HDLmEvent curEvent : eventsMap.values())
	  	eventsSet.add(curEvent);
		/* Process each event in the events set. Note that the events
		   set is in the desired order. */
    for (HDLmEvent curEvent : eventsSet) {
    	/* Get a few values from the current event */
    	String          eventName = curEvent.getName();
    	String          eventTimestamp = curEvent.getTimestamp();
    	/* Add the current event style. The style will only
    	   set to a 'useful' value if an anomaly is detected. */
    	if (eventTimestamp != null) {
    		boolean   anomalyFound = curEvent.checkForAnomaly();
    		if (anomalyFound) {
    			rv.append("<p>");
    			rv.append(eventName);
    			rv.append("</p>");
    		}
    		else {}
    	}
    	else {}
    }
	  return rv.toString();
	}
	/* This code checks for JavaScript exceptions and returns a HTML
	   response with the JavaScript event, if the event count is
	   greater than zero */
	protected static String eventsCheckExceptions(String localServer, String clientStr) {
		HDLmEvent   targetEvent = null;
		String      tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Get a reference to the map of events information */
		var  eventsMap = HDLmEvent.getEventsMap();
		if (eventsMap == null) {
			String   errorText = "Events map reference obtained from getEventsMap is null";
			HDLmAssertAction(false, errorText);
 	  }
		/* Look for the event we really care about. This event should exist.
		   However, you never know. The event count is checked below. */
		HDLmEvent   curEvent = eventsMap.get("JavaScript Exception");
		if (curEvent == null) {
			String   errorText = "Events map does not contain a JavaScript Exception entry";
			HDLmAssertAction(false, errorText);
		}
		/* Get the event count from the event */
		long  eventCount = curEvent.getCount();
   	if (eventCount > 0)
   		targetEvent = curEvent;
    /* Check if we found a valid JavaScript event with a non-zero event count */
 		if (targetEvent != null) {
 			/* Try to add the host name */
 			String  hostName = targetEvent.getHostName();
 			if (hostName != null) {
 			  rv.append("<p>");
 			  rv.append(hostName);
 			  rv.append("</p>");
 			}
 			/* Try to add the division name */
 			String  divisionName = targetEvent.getDivisionName();
 			if (divisionName != null) {
 			  rv.append("<p>");
 			  rv.append(divisionName);
 			  rv.append("</p>");
 			}
 			/* Try to add the site name */
 			String  siteName = targetEvent.getSiteName();
 			if (siteName != null) {
 			  rv.append("<p>");
 			  rv.append(siteName);
 			  rv.append("</p>");
 			}
 			/* Try to add the rule name */
 			String  ruleName = targetEvent.getRuleName();
 			if (ruleName != null) {
 			  rv.append("<p>");
 			  rv.append(ruleName);
 			  rv.append("</p>");
 			}
 			/* Try to add the event count */
 			String  eventCountStr = String.valueOf(eventCount);
	    rv.append("<p>");
			rv.append(eventCountStr);
			rv.append("</p>");
			/* Try to add the time of the last event */
 			Instant   eventInstant = targetEvent.getInstant();
 			String    eventInstantStr = HDLmUtility.getUtcTimeStamp(eventInstant);
	    rv.append("<p>");
			rv.append(eventInstantStr);
			rv.append("</p>");
 		}
	  return rv.toString();
	}
	/* This code returns some events statistics HTML to the caller */
	protected static String eventsStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the event statistics status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Events";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
                                               infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = HDLmEvent.getEventsHeadings(null);
		/* Get a reference to the map of events information */
		var  eventsMap = HDLmEvent.getEventsMap();
		if (eventsMap == null) {
			String   errorText = "Events map reference obtained from getEventsMap is null";
			HDLmAssertAction(false, errorText);
    }
    /* Create an event comparator for use in building/maintaining the
       tree event set */
		class eventComparator implements Comparator<HDLmEvent> {
			@Override
			public int compare(HDLmEvent e1, HDLmEvent e2) {
				int   compareInt;
				compareInt = e1.getType().getValue() - e2.getType().getValue();
				if (compareInt == 0)
					compareInt = e1.getName().compareTo(e2.getName());
				return compareInt;
			}
	  }
		/* Create a new events set that uses the comparator we just created */
		TreeSet<HDLmEvent>  eventsSet = HDLmEvent.getEventsTree(null);
  	/* Build a (short) list of the events status information */
    var  statusListContents = HDLmEvent.getEventsStatus(eventsSet,
    		                                                HDLmGetStatus.HTMLINFORMATION);
    /* Construct an HTML table from the headings and the events status list */
		tableHtml = HDLmHtml.buildHtmlTableFromListStyle(headings, statusListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* This routine extends an array list of doubles. The array list of doubles
	   is passed by the caller. The caller provides the existing array list of
	   doubles and the new size. This routine returns true, if no errors were
	   encountered. This routine returns false, if any error occurred. */
	protected static boolean  extendArrayList(boolean forceNull,
			                                      int newSize,
			                                      ArrayList<Double> doublesArray) {
		/* Check if the new array list size passed by the caller is invalid */
		if (newSize <= 0) {
			String  errorText = "New array size value passed to extendArrayList is invalid";
			throw new IllegalArgumentException(errorText);
		}
		if (doublesArray == null) {
			String  errorText = "Doubles array list passed to extendArrayList is null";
			throw new NullPointerException(errorText);
		}
		boolean   logIsDebugEnabled = LOG.isDebugEnabled();
		int       doublesArraySize = doublesArray.size(); 
		/* Check if the doubles array actually needs to be extended.
		   This may or may not be the case. */
		if (doublesArraySize >= newSize)
			return true;
		if (logIsDebugEnabled) { 
			LOG.debug(String.valueOf(forceNull));
			LOG.debug(String.valueOf(newSize));
			LOG.debug(String.valueOf(doublesArraySize));
		}
    /* At this point, we know we need to expand the doubles array
       list */
	 	boolean   parametersOk;
	 	int   additionalCount = newSize - doublesArraySize;
	 	if (logIsDebugEnabled)
	 	  LOG.debug(String.valueOf(additionalCount));
 	  /* Check if we already had some values */
 	  if (doublesArraySize > 0) {
 		  /* Get the first current value */
 		  Double  firstParameter = doublesArray.get(0);
 	  	/* If the first current value is null, then we must add more nulls */
 		  if (forceNull ||
 		  		firstParameter == null) {
 			  for (int i = 0; i < additionalCount; i++)
 				  doublesArray.add(null);
 		  }
 		  /* Since, the first current value is not null, we must add actual
 		     non-null values */
 		  else {
 			  ArrayList<Double>   additionalDoubleValues = new ArrayList<Double>();
 			  parametersOk = HDLmMain.getParameters(additionalDoubleValues,
 					                                    additionalCount);
        if (parametersOk == false)
          return false;
 			  doublesArray.addAll(additionalDoubleValues);
 			  if (logIsDebugEnabled) {
 			  	LOG.debug(String.valueOf(parametersOk));
 			    LOG.debug(String.valueOf(doublesArray.size()));
 			    LOG.debug(String.valueOf(additionalDoubleValues.size()));
 			  }
 		  }
 	  }
 	  /* We must create the entire doubles array from scratch */
 	  else {
 		  parametersOk = HDLmMain.getParametersPossiblyNull(doublesArray,
 			  	                                              newSize);
     if (parametersOk == false)
       return false;
  	}
		return true;
	}

	/* Do all of the work needed to get an index value. An array list
     is used here so that it can be modified to return a value to the
     caller. */
  protected static boolean getIndex(ArrayList<Double> indexArray) {
	  /* Check if the index array list passed by the caller is null */
	  if (indexArray == null) {
	  	String  errorText = "Index array list reference passed to getindex is null";
		  throw new NullPointerException(errorText);
	  }
	  /* Declare and define a few values */
    boolean  indexOk = false;
    /* Clear the index array */
    indexArray.clear();
    /* Get a random number between 0.0 and 1.0 */
 	  ArrayList<Double>  randomDoubleList = HDLmUtility.getRandomDoubleList(1);
 	  for (Double randomDouble : randomDoubleList) {
 		  indexArray.add(randomDouble);
 	  }
    /* Show that the index was successfully obtained */
    indexOk = true;
    return indexOk;
  }
	/* Do all of the work needed to get an index value. An array list
	   is used here so that it can be modified to return a value to the
	   caller. */
  protected static boolean getIndexPossiblyNull(ArrayList<Double> indexArray) {
  	/* Check if the index array list passed by the caller is null */
	  if (indexArray == null) {
		  String  errorText = "Index array list reference passed to getIndexPossiblyNull is null";
		  throw new NullPointerException(errorText);
	  }
	  /* Get the initial index value */
	  boolean  indexOk;
    indexOk = HDLmMain.getIndex(indexArray);
    if (indexOk == true) {
      /* Get the pass-through limit value */
      double  limitValue = HDLmConfigInfo.getPassThroughLimit();
      double  ranValue = HDLmUtility.getRandomDouble() * 100.0;
      if (ranValue <= limitValue) {
        indexArray.set(0, null);
      }
    }
    return indexOk;
  }
	/* This routine obtains JSON from some external source. At this
	   point the JSON is obtained an AWS database. However, this could
	   change. The caller indicates what should be obtained. The output
	   JSON string is returned to the caller. This routine uses a lower
	   level routine to do the actual work. This routine reports an error
	   if a JSON string is not returned. The output has all of the rows
	   for one editor type.*/
	protected static String  getJsonAllRowsFromSource(String colName, HDLmEditorTypes editorType) {
		/* Check a few values passed by the caller */
		if (colName == null) {
			String   errorText = "Column name reference passed to getJsonAllRowsFromSource is null";
			throw new NullPointerException(errorText);
		}
		if (editorType == null) {
			String   errorText = "Editor type value passed to getJsonAllRowsFromSource is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		/* Declare and define a few variables */
		String    jsonOut;
		Instant   instantStart = Instant.now();
		/* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
    /* Get the needed JSON */
		if (logIsDebugEnabled) {
		  LOG.debug("In HDLmMain.getJsonAllRowsFromSource");
		  LOG.debug(colName);
	  }
		jsonOut = HDLmMain.getJsonStringFromSource(colName, editorType);
		if (logIsDebugEnabled)
	  	LOG.debug(jsonOut);
		/* Check if a JSON string was successfully obtained */
		if (jsonOut == null) {
			String   errorText = "No JSON was successfully obtained from the source";
			HDLmAssertAction(false, errorText);
		}
		/* HDLmUtility.logStringInParts("HDLmMain", jsonOut); */
		/* We have now obtained a fresh copy of the rules from where they are
	     stored on the server. We want to keep track of long this took. */
    Instant   instantFinish = Instant.now();
    Duration  requestDuration = Duration.between(instantStart, instantFinish);
    /* String    elapsedName = HDLmElapsed.elapsedNames.get(1); */
    String    elapsedName = "Database";
    HDLmElapsed.elapsedOccurred(elapsedName, requestDuration);
		return jsonOut;
	}
	/* This routine obtains JSON from some external source. At this
	   point the JSON is obtained from an AWS database. However, this
	   could change. The caller indicates what should be obtained. The
	   output JSON string is returned to the caller. The output has all
	   of the rows for one editor type. */
	protected static String  getJsonStringFromSource(String colName, HDLmEditorTypes editorType) {
		/* Check a few values passed by the caller */
		if (colName == null) {
			String   errorText = "Column name reference passed to getJsonStringFromSource is null";
			throw new NullPointerException(errorText);
		}
		if (editorType == null) {
			String   errorText = "Editor type value passed to getJsonStringFromSource is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		String  contentStr = null;
		String  jsonOut;
		/* Get a few values needed below */
		contentStr = HDLmUtility.getContentString(editorType);
		/* Get and free the database lock. These operations have the effect
		   of stopping any reads (from the database) while the lock is held.
		   The lock is held while any database inserts, updates, and deletes
		   are in progress. */
		HDLmDatabase.getDatabaseLock();
		HDLmDatabase.releaseDatabaseLock();
		/* Run the actual request */
		String  tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		/* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
		if (logIsDebugEnabled) {
		  LOG.debug("In HDLmMain.getJsonStringFromSource");
		  LOG.debug(tableName);
		  LOG.debug(contentStr);
		  LOG.debug(editorType.toString());
		}
		jsonOut = HDLmDatabase.getTableRowsJson(contentStr, tableName, null);
		if (logIsDebugEnabled)
		  LOG.debug(jsonOut);
		return jsonOut;
	} 
	/* This routine returns the current value of the Junit
	   active flag. This flag is manually set. This flag is
	   not automatically set. */ 
	protected static boolean getJunitActive() {
		return HDLmMain.junitActive;
	}
  /* Do all of the work needed to build a set of JavaScript
     functions that can modify the HTML generated by Magento
     or some other product. Note that the caller must pass the
     company name, the division name, and the site name. These
     values are used to select the set of modifications that are
     then used to build the JavaScript functions returned by this
     routine. This routine returns just one script. However, the
     script will contain several (many) JavaScript functions.

     Note that if pass-through is set to true, then all of the
     modifications will be marked as disabled. This will have
     the effect of making no changes to the target system.

     This routine can return a null value if the JavaScript
     functions could not be built for some reason. This should
     not happen, but might happen anyways. */
  @SuppressWarnings("unused")
	protected static String getJsMain(HttpServletRequest request,
		  		                          HttpServletResponse response,
		  		                          HDLmProtocolTypes protocol,
		  		                          HDLmPassThruStatus passThru,
		  		                          String clientStr,
		  		                          String timeStamp,
		                                String companySecure,
		                                String company,
		                                String division,
		                                String site,
		                                String referrerPathValueStr,
		                                HDLmLogMatchingTypes logMatching,
		                                HDLmUsePathValue usePathValue,
		                                String serverName) {
  	boolean       cacheFetchFailed = false;
  	boolean       forceNewExperiment = false;
  	boolean       logIsDebugEnabled = LOG.isDebugEnabled();
    boolean       results;
  	boolean       storeInCache = false;
  	boolean       storeInCookie = false;
  	boolean       writeToLog = false;
  	HDLmSession   sessionObj = null;
    String        actualJS = null;
    String        encryptionKeyValue = HDLmConfigInfo.getSecretEncryptionKey();
    String        sessionIdStr = null;
    String        sessionIdOnlyJsonValue = null;
    /* Try to get the defined cookie name. This should never fail. 
       However, you never know.  */
    String    cookieName = HDLmDefines.getString("HDLMSESSIONCOOKIE");
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After get define for HDLMSESSIONCOOKIE in getJsMain");
		if (cookieName == null) {
			String   errorFormat = "Define value for session cookie name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSESSIONCOOKIE");
			HDLmAssertAction(false, errorText);
		}
	  /* Try to get the name of the session ID. This should never fail.
 	     However, you never know. */
		String   sessionIdName = HDLmDefines.getString("HDLMSESSIONID");
		if (sessionIdName == null) {
			String   errorFormat = "Define value for session ID name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSESSIONID");
			HDLmAssertAction(false, errorText);
		}
		String   plainTextSessionIdJsonValue = null;
    String   encryptedSessionIdJsonValue = HDLmJetty.getCookieExtended(request, cookieName);
    String   requestOriginalPathValue = HDLmJetty.getOriginalPathValue(request);
    /* Try to decrypt the cookie, if possible. The decrypted cookie contains
       just the session ID in JSON form. */
    if (encryptedSessionIdJsonValue != null)  
    	plainTextSessionIdJsonValue = HDLmEncryption.decrypt(encryptionKeyValue, 
    	                                               		   encryptedSessionIdJsonValue);    
		/* We have some special work to do if we are building generic JavaScript.
		   We need to check if we are starting a new experiment or not. If we are
		   not starting a new experiment, then we need to set the session ID value
		   to a cookie value. */
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After get define for HDLMSESIONID in getJsMain");
		if (true) {
			/* Check if the home query parameter was used and if the value is
			   'true' (without the quotes). This code is no longer used. We
			   now have a way to get the actual path value in all cases. */
			/*
			String  homeValueStr = request.getParameter("home");
			if (homeValueStr != null &&
					homeValueStr.equalsIgnoreCase("true"))
				forceNewExperiment = true;
			*/
			/* Try to get the complete URL (minus the scheme and the colon and
			   the two forward slashes) for the original web page. Say the web
			   page is www.yogadirect.com/yoga-direct-1-4-inch-yoga-mat.html. We
			   want the /yoga-direct-1-4-inch-yoga-mat.html part. We need to remove
			   the www.yogadirect.com part. */
			/* if (logIsDebugEnabled) { */ 
			/*   LOG.debug(request.getContextPath()); */
			/*   LOG.debug(request.getPathInfo()); */
			/*   LOG.debug(request.getPathTranslated()); */
			/*   LOG.debug(request.getHeader("referer")); */
			/*   LOG.debug(request.getRequestURI()); */
			/* } */
			/* This is bad code. The referer header (sic) is being phased
			   out because it supposedly causes security problem. The security
			   problems may be a myth. However, the reality is that the header
			   is going away. */
			String  refererHeaderStr = request.getHeader("referer");
			if (refererHeaderStr != null) {
				String  refererPathStr = HDLmUtility.getPathString(refererHeaderStr);
				if (refererPathStr != null &&
						refererPathStr.equals("") == false)
					referrerPathValueStr = refererPathStr;
			}
		}
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to getMods in getJsMain");
    /* Try to get a set of modifications for the current web page */
    ArrayList<HDLmMod>  mods = new ArrayList<HDLmMod>(); 
    results = HDLmMain.getMods(mods, passThru, company,
                               division, site, referrerPathValueStr,
                               usePathValue);
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After call to getMods in getJsMain");
    /* Check if we were able to obtain a set of modifications from the
       server. Just return to the caller if not. */
    if (results == false)
      return actualJS;
    /* Write a few values to the log */
    if (logIsDebugEnabled) {
	    LOG.debug("getJsMain - Just completed getMods");
	    LOG.debug("Company "              + company);
	    LOG.debug("ReferrerPathValueStr " + referrerPathValueStr);
	    LOG.debug("Mods.size "            + String.valueOf(mods.size()));
	    LOG.debug("UsePathValue "         + String.valueOf(usePathValue));
    }
    /* Try to find the high parameter number used by the current set
       of modifications. The called routine may return a null value.
       This is not really an error condition. This must be checked
       below. */
    int   newParametersSizeOf = 0;
    Integer   maxParmNumber = HDLmMod.getHighParmNumber(mods);
    if (maxParmNumber != null)
    	newParametersSizeOf = maxParmNumber + 1;
    /* Set a few initial session values that may be obtained from
       a cookie later */
    ArrayList<Double>  parametersArray = new ArrayList<Double>();
	  /* Check if we are handling a special path value that actually
	     has the session ID in it. This is possible in some cases.
	     If the path value actually has the session ID in it, then
	     we need to use the session ID to get the parameters. In
	     this special case, we do not rely on the cookie we normally
	     use to get the session ID and the parameters. Because the
	     path value has a different host name, the cookie is not sent.

	     This block of code is used to handle the special case where
	     we need the parameter values, but we can not get them from a
	     cookie. This will happen when a special web page (in an iFrame,
	     using a different host name) is invoked by the regular code. */
    if (logIsDebugEnabled) {
		  LOG.debug("Original path value string - " + requestOriginalPathValue);
		  LOG.debug("sessionIdName - " + sessionIdName);
    }
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before check for session ID name in getJsMain");
		int  sessionIdNamePos = requestOriginalPathValue.indexOf(sessionIdName);
 	  if (sessionIdNamePos > 0) {
	    /* Extract the actual session ID from the path value. Because this
	       code can only be run if the path value has a session ID, this
	       code can never fail (hopefully). */
 	  	int   sessionIdNameLen = sessionIdName.length();
	    sessionIdStr = requestOriginalPathValue.substring(sessionIdNamePos+sessionIdNameLen+1);
	    /* The session ID just obtained may have query variables in it.
	       We need to check for query variables and remove them if need
	       be. */
	    int   posQuery = sessionIdStr.indexOf('&');
	    if (posQuery > 0) {
	    	sessionIdStr = sessionIdStr.substring(0, posQuery);
	    }
	    /* Force the cookie to be stored in the response. This is important because
	       we may be handling a different host name at this point. Because the host
	       name is different, the original cookie will not be present. */
	    storeInCookie = true; 
	    if (logIsDebugEnabled) {
	      LOG.debug("session cache log - Fetching (" + sessionIdStr + ")");
	    }
	    sessionObj = HDLmSession.getFromCacheIfPresent(sessionIdStr);
	    String  indexStr = null;
	    String  parametersStr = null;
	    /* Check if we really got a session value (in JSON) from the session
	       cache. Get some values from the session value. */
	    if (sessionObj != null) {	    	 
	    	indexStr = sessionObj.getIndex();
	    	parametersStr = sessionObj.getParameters();
	    }
	    /* The session was not found in the session cache. We need to create
	       all of the session values here. */
	    else {
	      String  errorFormat = "Error fetching value of session key (%s) from session cache";
	      String  errorText = String.format(errorFormat, sessionIdStr);
	      LOG.info(errorText);
	      /* The code below has been commented out. For now we try to recover
	         from session cache misses. */
	      /* HDLmAssertAction(false, errorText); */
	      cacheFetchFailed = true;
	      int     defaultParmCount = 0;
        parametersStr = HDLmString.repeatString("none", " ", defaultParmCount);
        sessionIdStr = "unknown";
        indexStr = "null";
        /* Create a new session object and store a few values in it */
        sessionObj = new HDLmSession();
        sessionObj.setIndex(indexStr);
        sessionObj.setParameters(parametersStr);
        sessionObj.setSessionId(sessionIdStr);  
        sessionObj.setUseModeNull();
	    }
	    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before get parameters array string in getJsMain");
      /* We need to build an array with each of the parameter values */
      parametersArray = HDLmMain.getParametersArray(parametersStr);
      int   parametersArraySize = parametersArray.size();
      /* Check if we need to expand the parameters array. This may
         or may not be true. Note that we may or may not have had
         some number of values already. */
      if (logIsDebugEnabled) {
        LOG.debug("Getting parameter values from a session ID");
        LOG.debug("Old parameter size of - " + String.valueOf(parametersArraySize));
        LOG.debug("New parameter size of - " + String.valueOf(newParametersSizeOf));
      }
      if (newParametersSizeOf > parametersArraySize) {
   	    boolean   extendOk;
		   	storeInCache = true;
		   	writeToLog = true;
		   	extendOk = HDLmMain.extendArrayList(cacheFetchFailed,
		   			                                newParametersSizeOf,
		   			                                parametersArray);
	      if (extendOk == false)
	        return actualJS;
	      /* Rebuild the parameters string */
	      parametersStr = HDLmMain.getParametersStr(parametersArray);
        /* Store a value in the session object */ 
        sessionObj.setParameters(parametersStr);         
      }
      HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After possible call to extendArrayList in getJsMain");
	  }
    /* We really only want to get the parameter values from the server
       if we are starting an experiment. In all other cases, we want to
       get the parameter values from the a cookie. Note that if the cookie
       does not exist, then we clearly need to create the cookie. This
       block of code handles the case where we need a new set of parameter
       values. For now we obtain them internally. */
 	  else if (forceNewExperiment                  ||
 	  		     referrerPathValueStr.equals("/")    ||
 	  		     plainTextSessionIdJsonValue == null ||
 	  		     HDLmSession.checkSessionJson(plainTextSessionIdJsonValue) == false) {
 	  	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to getParametersPossiblyNull in getJsMain");
      /* Try to get the parameter values from the parameter values server.
         This stop may or may not succeed. If this step fails, then we should
         not attempt to build the JavaScript functions. */
 	  	if (logIsDebugEnabled) {
        LOG.debug("Getting parameter values from a scratch");
        LOG.debug("New parameter size of - " + String.valueOf(newParametersSizeOf));
 	  	}
 	  	/* Try to get the index value for use later. We use an
 	  	   array list here, so that the first entry can be set. */
 	  	ArrayList<Double>   indexArray = new ArrayList<Double>();
 	  	boolean  indexOk = HDLmMain.getIndexPossiblyNull(indexArray);
      if (indexOk == false)
        return actualJS;
      Double  indexValue = indexArray.get(0);
      /* Get the index value as a string, possibly null */
      String  indexStr = null;
      if (indexValue == null)                                                                                                                                                                                                                                                                       
      	indexStr = "null";
      else
      	indexStr = indexValue.toString();
      /* Try to get the parameter values */
      boolean  parametersOk = HDLmMain.getParametersPossiblyNull(parametersArray,
      		                                                       newParametersSizeOf);
      if (parametersOk == false)
        return actualJS;
      /* Build a parameter string from parameter values */
      String  parametersStr = HDLmMain.getParametersStr(parametersArray);
      /* The code below get the actual session ID string from the
         request. For now this code is not in use. We just use
         GUID values as the session ID string. This works because
         we aren't really using the session ID for anything. */
      /* String   sessionString = request.getSession().getId(); */
      sessionIdStr = UUID.randomUUID().toString();
      /* Build a new session object and store all of the value
         in it */
      sessionObj = new HDLmSession();
      sessionObj.setIndex(indexStr);
      sessionObj.setParameters(parametersStr);
      sessionObj.setSessionId(sessionIdStr); 
      /* A new session is created here. However, the test mode
         from the old session should be used to set the test 
         mode of the new session. */
			if (plainTextSessionIdJsonValue != null) {
				String  useMode = HDLmMain.getUseModeUsingSessionId(plainTextSessionIdJsonValue);
				if (useMode != null)
				  sessionObj.setUseMode(useMode);
				else
					sessionObj.setUseModeNull();
			}
      storeInCache = true;
      storeInCookie = true;
      writeToLog = true;
    }
    /* In all other cases, we get the session ID from the cookie. 
       The session ID is used to get the parameter values from the
       session cache. Of course, we may need to get additional 
       parameter values. This block of code handles the cookie case. 
       Of course, the new number of parameters may be greater than 
       the old number of parameters. We may have to expand the 
       parameter array at this point. */
    else {
    	HDLmSession   tempSessionObj = HDLmSession.buildSession(plainTextSessionIdJsonValue);
    	sessionIdStr = tempSessionObj.getSessionId();    	
    	sessionObj = HDLmSession.getFromCacheIfPresent(sessionIdStr);
    	String  parametersStr = sessionObj.getParameters();
    	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to getParametersArray in getJsMain");
      /* We need to build an array with each of the parameter values */
      parametersArray = HDLmMain.getParametersArray(parametersStr);
      int   parametersArraySize = parametersArray.size();
      /* Check if we need to expand the parameters array. This may
         or may not be true. Note that we may or may not have had
         some number of values already. */
      if (logIsDebugEnabled) {
        LOG.debug("Getting parameter values from a cookie");
        LOG.debug("Old parameter size of - " + String.valueOf(parametersArraySize));
        LOG.debug("New parameter size of - " + String.valueOf(newParametersSizeOf));
      }
      if (newParametersSizeOf > parametersArraySize) {
      	boolean   extendOk;
      	storeInCache = true;
		   	writeToLog = true;
		   	extendOk = HDLmMain.extendArrayList(false, newParametersSizeOf, parametersArray);
	      if (extendOk == false)
	        return actualJS;
	      /* Build a parameter string from parameter values */
	      parametersStr = HDLmMain.getParametersStr(parametersArray);
	      /* Store the parameter string in the session object
	         and convert the session object to a JSON string */
	      sessionObj.setParameters(parametersStr);
	    }
 	  }
 	  /* Store the session ID and parameters (in string form) in the cache.
 	     Note that the actual cache key includes the session ID name (which
 	     is fixed) and session ID string (which is not fixed). */
 	  if (storeInCache)
 	  	HDLmSession.addToCache(sessionIdStr, sessionObj);
 	  HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before possible call to storeCookie in getJsMain");
    /* Store the product session cookie with the parameter values */
    if (storeInCookie) {
    	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before check of session JSON in getJsMain");
      /* Try to encrypt the cookie, if possible */
      if (sessionObj != null) {
      	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before encrypt cookie in getJsMain");
        sessionIdOnlyJsonValue = sessionObj.buildJsonIdOnly();     	
      	encryptedSessionIdJsonValue = HDLmEncryption.encrypt(encryptionKeyValue, 
      			                                                 sessionIdOnlyJsonValue);
      	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After encrypt cookie in getJsMain");
    	  /* response.setHeader("Access-Control-Expose-Headers", "Set-Cookie"); */
    	  /* response.setHeader("Access-Control-Allow-Credentials", "true"); */
      	/* The server name (javaproxya.dnsalias.com) is hard-coded here.
      	   That is OK because this is just a comment.*/
    	  /* response.setHeader("Access-Control-Allow-Origin", "https://javaproxya.dnsalias.com"); */
      	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before store cookie in getJsMain");
      	Integer   cookieMaxAge = HDLmConfigInfo.getCookieMaxAge();
      	String    cookieSameSite = "None";
    	  HDLmJetty.storeCookie(response, 
    	  		                  encryptedSessionIdJsonValue, 
    	  		                  cookieMaxAge,
    	  		                  cookieSameSite);
    	  HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After store cookie in getJsMain");
      }
    	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before possible set header in getJsMain");
    	/* In some cases, we need to modify the stored cookie */
    	if (false) {
    	  String  cookieStr = HDLmJetty.getHeaderFromResponse(response, "Set-Cookie");
    	  if (logIsDebugEnabled) 
    	    LOG.debug("Cookie string - " + cookieStr); 
    	  cookieStr = "Set-Cookie: " + cookieStr;
    	  cookieStr += "; SameSite=None";
    	  HDLmJetty.setHeader(response, cookieStr);
    	  if (logIsDebugEnabled) 
    	    LOG.debug("Cookie string - " + cookieStr);
    	}
    	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After possible set header in getJsMain");
    }
    /* Write the product parameters out to the log, if need be */
    if (writeToLog) {
    	String  sessionStrValue = sessionObj.buildJson();
    	HDLmSession.writeLog(company, timeStamp, sessionIdStr, sessionStrValue);
    }
    /* At this point, some of the modifications may be replacement
       modifications (have a rule type of replace). These rules
       will specify the name of some saved data that should be
       used. The call below finds replace modifications and uses
       the names of the saved data to get some actual saved data. */
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to checkForReplace in getJsMain");
    checkForReplace(company, division, site, mods);
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After call to checkForReplace in getJsMain");
    /* Remove some of the modifications if we are not in test mode
       and the modifications are marked as test mode only */
    HDLmMain.removeSomeMods(sessionObj.getUseMode(), mods);
    /* Build the JavaScript used to implement the modifications */
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to getJsBuildJs in getJsMain");
    /* Build the Windows version of the JavaScript program */
    if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS) {
      actualJS = HDLmBuildJsNoCompression.getJsBuildJs(protocol,
				                                               companySecure,
				                                               company,
				                                               division,
				                                               site,
				                                               mods,
				                                               sessionObj,
				                                               logMatching,
				                                               serverName);
      /* HDLmUtility.logStringInParts("JS", actualJS); */
    }
    /* Build the Linux version of the JavaScript program */
    else {
      actualJS = HDLmBuildJsCompressBlanks.getJsBuildJs(protocol,
                                                        companySecure,
                                                        company,
                                                        division,
                                                        site,
                                                        mods,
                                                        sessionObj,
                                                        logMatching,
                                                        serverName);
    }
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After call to getJsBuildJs in getJsMain");
    return actualJS;
  }
  /* Do all of the work needed to get a set of modifications for the
     current company, division, site, and path value. The modifications
     are returned to the caller using a array passed by reference. This
     call may not find any applicable modifications. This is not an
     error condition.

     The referrer path value string is that path value of the web page
     that requested that a JavaScript program be built. It is not the
     path value that was sent to the server. The referrer path value
     might be something like /yoga-direct-1-4-inch-yoga-mat.html, not
     /HDLmGetJS.

     The meaning of 'company' in the context of this routine needs some
     explanation. The user might have separate company entries
     for yogaaccessories.com and www.yogaaccessories.com. If both company
     entries exist, then they are treated as totally separate.

     However, the user might have a company entry for just yogaaccessories.com
     and no actual company entry for www.yogaaccessories.com. In that case, the
     company entry for yogaaccessories.com is used for both yogaaccessories.com
     and www.yogaaccessories.com.

     The reverse might also be true. The user might have a company entry
     for www.yogaaccessories.com and no company entry for yogaaccessories.com.
     In that case, the company entry for www.yogaaccessories.com is used for
     both yogaaccessories.com and www.yogaaccessories.com.

     Another possibility is that both company entries don't exist. In that
     case, neither company entry is going to be used because they both
     don't exist. */
  protected static boolean  getMods(ArrayList<HDLmMod> mods,
  		                              HDLmPassThruStatus passThru,
  		                              String company,
                                    String division,
                                    String site,
                                    String referrerPathValueStr,
                                    HDLmUsePathValue usePathValue) {
  	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After entry to getMods");
    boolean  modsStatusOkNotOk = false;
    /* Clear the array list of modifications */
    mods.clear();
    /* Check if force pass-through is set. If this flag is
	     set to true, then we should always return an empty
	     set of modifications to the caller. */
	  if (HDLmConfigInfo.getForcePassThru() == true) {
		  modsStatusOkNotOk = true;
	    return modsStatusOkNotOk;
	  }
    /* Try to use the existing HTML pass-through tree structure */
    boolean   reloadRules = false;
		/* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
    /* Check if modifications should be cached or not. If modifications
       are not being cached, then we don't need to check the cache.
       Note that the cache is used to store the entire tree structure,
       not just one set of modifications. */
    if (logIsDebugEnabled) {
      LOG.debug("getEntriesDatabaseUseCache"); 
      LOG.debug(HDLmConfigInfo.getEntriesDatabaseUseCache().toString()); 
    }
    if (HDLmConfigInfo.getEntriesDatabaseUseCache() == HDLmCacheTypes.NEVER) {
    	reloadRules = true;
    }
    else if (HDLmConfigInfo.getEntriesDatabaseUseCache() == HDLmCacheTypes.TIME) {
    	Instant   currentInstant = Instant.now();
  		long      intervalNanos;
  		long      intervalSeconds;
  		/* Get the interval since the modification rules were last reloaded */
  		Duration  durationSinceLast = Duration.between(reloadRulesInstant, currentInstant);
  	  intervalNanos = (long) durationSinceLast.getNano();
  	  intervalSeconds = durationSinceLast.getSeconds();
  	  if (intervalSeconds > 0)
  	    intervalNanos += (intervalSeconds * 1000000000);
  	  /* Check if the interval since the last rules reload is out-of-bounds */
  	  if (intervalNanos > 60000000000L) {
  	  	reloadRulesInstant = currentInstant;
  	  	reloadRules = true;
  	  }
    }
    else if (HDLmConfigInfo.getEntriesDatabaseUseCache() == HDLmCacheTypes.COUNT) {
    	HDLmMain.GetModsCount++;
    	if (HDLmMain.GetModsCount > 0 &&
    		  (HDLmMain.GetModsCount % 40) == 0)
    		reloadRules = true;
    }
    else if (HDLmConfigInfo.getEntriesDatabaseUseCache() == HDLmCacheTypes.UPDATE) {
			if (logIsDebugEnabled) {
				LOG.debug("getRulesHaveBeenUpdated");
				LOG.debug(String.valueOf(HDLmDatabase.getRulesHaveBeenUpdated()));
			}
    	if (HDLmDatabase.getRulesHaveBeenUpdated()) {
    		HDLmDatabase.setRulesHaveBeenUpdated(false);
    		reloadRules = true;
      }
    }
    /* Check if modifications should be reloaded or not. If modifications
       are reloaded, then the entire tree structure is reloaded, not just
       one set of modifications. */
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before reload rules check in getMods");
    if (reloadRules) {
			/* Use a common routine to reload all of the rules */
    	HDLmMain.reloadNodes();
    }
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After reload rules check in getMods");
    /* Get the string value for the top node of the tree */
    String  nodePathTopString = HDLmDefines.getString("HDLMTOPNODENAME");
	  if (nodePathTopString == null) {
		  String   errorFormat = "Define value for top node name not found (%s)";
		  String   errorText = String.format(errorFormat, "HDLMTOPNODENAME");
		  HDLmAssertAction(false, errorText);
	  }
	  /* Get the string value for the node that has all of companies */
	  String  nodePathCompaniesString = HDLmDefines.getString("HDLMCOMPANIESNODENAME");
	  if (nodePathCompaniesString == null) {
			String   errorFormat = "Define value for companies string not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMCOMPANIESNODENAME");
			HDLmAssertAction(false, errorText);
	  }
    /* Build the company name with and without the 'www.' prefix. These values are used
       below. */
    String  companyWithoutWww = HDLmString.removePrefix("www.", company);
    String  companyWithWww = "www." + companyWithoutWww;
    /* At this point we can try to build node paths for the company name
       with and without the 'www.' prefix */
		ArrayList<String>  nodePathCompanyWithout = new ArrayList<String>(
		  List.of(nodePathTopString, nodePathCompaniesString,
		     		  companyWithoutWww));
		ArrayList<String>  nodePathCompanyWith = new ArrayList<String>(
			  List.of(nodePathTopString, nodePathCompaniesString,
			     		  companyWithWww));
    /* At this point we can try to locate the tree nodes for the company
       with and without the 'www.' prefix */
		HDLmTree   companyWithoutNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(),
			                                                    	nodePathCompanyWithout);
		HDLmTree   companyWithNode    = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(),
                                                            nodePathCompanyWith);
		/* Check if both company nodes exist, with and without the 'www.' prefix.
		   In this case, we really don't make any changes. */
		if (companyWithoutNode != null &&
				companyWithNode    != null) {
			company = company;
		}
		/* Check if the company node without the 'www.' prefix exists and the
		   company node with the 'www.' prefix does not exist. In this case
		   use the company name without the 'www.' prefix. */
		else if (companyWithoutNode != null &&
				     companyWithNode    == null) {
			company = companyWithoutWww;
		}
		/* Check if the company node with the 'www.' prefix exists and the
	     company node without the 'www.' prefix does not exist. In this
	     case use the company name with the 'www.' prefix. */
	  else if (companyWithoutNode == null &&
		  	     companyWithNode    != null) {
 		  company = companyWithWww;
	  }
		/* Check if the company node with the 'www.' prefix does not exist
		   and the company node without the 'www.' prefix does not exist. In
		   this case, we really don't make any changes. */
	  else if (companyWithoutNode == null &&
		 		     companyWithNode    == null) {
			company = company;
		}
		/* Get a string value for the node that has all of the rules under it */
		String  nodePathRulesString = HDLmDefines.getString("HDLMRULESNODENAME");
		if (nodePathRulesString == null)
			HDLmAssertAction(false, "Define value for rules string is null");
    /* At this point we can build a node path to the site node that has
       the actual modifications we need. The node path is used to locate
       the site node that has actual modifications as children. */
		/* Build a node path for all of the modifications */
		ArrayList<String>  nodePath = new ArrayList<String>(
		  List.of(nodePathTopString, nodePathCompaniesString,
		  		    company, nodePathRulesString,
		  		    division, site));
		HDLmTree   siteNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(),
				                                          nodePath);
		/* We used to fail, if the site node could not be found.
		   However, this is probably an error. What if no rules
		   exist for a given site? The code below will show that
		   the current request was successful, but no mods were
		   actually found. */
		if (siteNode == null) {
			modsStatusOkNotOk = true;
	    return modsStatusOkNotOk;
		}
		if (siteNode == null) {
			HDLmAssertAction(false, "Null site node returned by locateTreeNode");
		}
		ArrayList<HDLmMod>   extractedMods = new ArrayList<HDLmMod>();
		extractedMods = HDLmMod.extractMods(referrerPathValueStr, siteNode,
				                                passThru, usePathValue);
		/* Copy each of the extracted modifications into the output array list */
		for (HDLmMod mod : extractedMods)
			mods.add(mod);
    /* Return a value showing if we were able to obtain the modifications
       from the server */
		modsStatusOkNotOk = true;
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After adding mods in getMods");
    return modsStatusOkNotOk;
  }
  /* Get the operating system type. This is the type of the parent
	   operating system, if we are actually running in a Docker
	   container. The Docker container provides (roughly) a Linux
	   operating system. However, if a Docker container is active,
	   this is the operating system that the Docker container is
	   running in. */
	protected static HDLmOsTypes  getOsType() {
	  return HDLmMain.osType;
	}
  /* Do all of the work needed to get the product parameter(s) values
     from the server that provides product parameter(s) values. Return
     the product parameter(s) values to the caller as an array. Note
     that this code really just updates the parameters array passed
     by the caller. The return value is used to show if this routine
     worked, or not. */
  protected static boolean getParameters(ArrayList<Double> parametersArray,
  		                                   int parametersCount) {
  	/* Check if the parameters array list passed by the caller is null */
		if (parametersArray == null) {
			String  errorText = "Parameters array list reference passed to getParameters is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the count of parameters passed by the caller is invalid */
		if (parametersCount < 0) {
			String  errorText = "Parameters count value passed to getParameters is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Declare and define a few values */
    boolean  parametersOk = false;
    String   tempStr = "temp";
    String   tempParametersStr;
    /* Clear the parameters array */
    parametersArray.clear();
    /* Check if we should use the temporary code or try to get the
       parameters from the parameters server */
    if (tempStr.equals("temp")) {
    	ArrayList<Double>  randomDoubleList = HDLmUtility.getRandomDoubleList(parametersCount);
    	for (Double randomDouble : randomDoubleList) {
    		parametersArray.add(randomDouble);
    	}
      /* Show that the parameters were successfully obtained */
      parametersOk = true;
      return parametersOk;
      /* We need to build an array with each of the parameter values/
         The number of parameter values is highly variable and probably
         will not be 10. The example below assumes that there are 10
         parameter values which is actually unlikely to be true.  */
      /* tempParametersStr = "0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5"; */
    }
    /* Get the parameters from the server */
    else {
    	HDLmApacheResponse  apacheResponse;
      String              baseURL;
      String              methodURL;
      String              URL;
	    baseURL = HDLmConfigInfo.getParametersInternetMethod() + "://" +
	              HDLmConfigInfo.getParametersUrl() + "/";
	    methodURL = HDLmConfigInfo.getParametersAccessMethod();
	    URL = baseURL + methodURL;
      apacheResponse = HDLmCurlApache.runCurl(URL, "", "", HDLmHttpTypes.GET);
	    tempParametersStr = apacheResponse.getStringContent();
      /* If we didn't get the parameters from the server, then we
	       really can't do much here. Just return to the caller. */
      if (tempParametersStr == null)
        return parametersOk;
    }
    /* Add a timing event */
  	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to getParametersArray in getJsMain");
    /* We need to build an array with each of the parameter values */
    parametersArray = HDLmMain.getParametersArray(tempParametersStr);
    /* Show that the parameters were successfully obtained */
    parametersOk = true;
    return parametersOk;
  }
  /* Convert a parameters string to a parameters array and return
     the parameters array to the caller. The session cookie has the
     parameters stored as a string. However, in some cases we need
     them as an array of numbers. */
  protected static ArrayList<Double>  getParametersArray(final String parametersStr) {
  	/* Check if the parameters string passed by the caller is null */
		if (parametersStr == null) {
			String  errorText = "Parameters string passed to getParametersArray is null";
			throw new NullPointerException(errorText);
		}
  	/* Build an empty (initially) parameters array */
  	ArrayList<Double>  parametersArray = new ArrayList<Double>();
    /* We need to build an array with each of the parameter values */
    ArrayList<String>  parametersArrayStr = HDLmString.explodeWhitespace(parametersStr);
    Double             parameterValue;
    for (String parameterStr : parametersArrayStr) {
    	if (parameterStr == null        ||
          parameterStr.equals("null") ||
    			parameterStr.equals("none"))
    		parameterValue = null;
    	else
    	  parameterValue = HDLmUtility.convertDouble(parameterStr);
    	parametersArray.add(parameterValue);
    }
    /* Return the final parameters array to the caller */
    return parametersArray;
  }
  /* Do all of the work needed to get the product parameter values
     from the server that provides product parameter values. Return
     the product parameter values to the caller as an array. The
     product parameter values may be set (reset) to null values
     depending on the current limit value and a random number
     generator. */
	protected static boolean getParametersPossiblyNull(ArrayList<Double> parametersArray,
			                                               int parametersCount) {
  	/* Check if the parameters array list passed by the caller is null */
		if (parametersArray == null) {
			String  errorText = "Parameters array list reference passed to getParametersPossiblyNull is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the count of parameters passed by the caller is invalid */
		if (parametersCount < 0) {
			String  errorText = "Parameters count value passed to getParametersPossiblyNull is invalid";
			throw new IllegalArgumentException(errorText);
		}
	  /* Get the initial parameter values */
		boolean  parametersOk;
	  parametersOk = HDLmMain.getParameters(parametersArray, parametersCount);
	  if (parametersOk == true) {
	    int   parametersSize = parametersArray.size();
	    /* Get the pass-through limit value */
	    double  limitValue = HDLmConfigInfo.getPassThroughLimit();
	    double  ranValue = HDLmUtility.getRandomDouble() * 100.0;
	    if (ranValue <= limitValue) {
	      for (int i = 0; i < parametersSize; i++) {
	        parametersArray.set(i, null);
	      }
	    }
	  }
	  return parametersOk;
	}
	/* Get the parameters string from the parameters array. This routine
	   is used to convert fom a binary parameters array to a string that
	   can be (amoung other things) stored in a parameters object. */
	protected static String   getParametersStr(final ArrayList<Double> parametersArray) {
		/* Check if the parameters array passed by the caller is null */
		if (parametersArray == null) {
			String  errorText = "Parameters array passed to getParametersStr is null";
			throw new NullPointerException(errorText);
		}
    /* Build a parameter string from an array of parameter values */
		int    parametesArraySize = parametersArray.size();
    StringBuilder  parametersStr = new StringBuilder();
    int     parametersCount = 0;
    for (Double parameter : parametersArray) {
    	parametersCount += 1;
    	/* Check for a null parameter value */
    	if (parameter == null)
    		parametersStr.append("null");
    	else
    	  parametersStr.append(parameter.toString());
    	/* Add a blank separator, if need be */
    	if (parametersCount < parametesArraySize)
    		parametersStr.append(' ');
    }
		return parametersStr.toString();
	}
	/* This code gets some information for the pass-through display.
	   The type of information is specified as part of the request. */
	protected static String getPassThruStatusCmdResponse(String localServer,
			                                                 String clientStr,
			                                                 HttpServletRequest request,
			                                                 HttpServletResponse response) {
		/* Check if the local server string passed by the caller is null */
		if (localServer == null) {
			String  errorText = "Local server string passed to getPassThruStatusCmdResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to getPassThruStatusCmdResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getPassThruStatusCmdResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response instance passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet response passed to getPassThruStatusCmdResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		/*
		String          modsStr = "[{\\\"tooltip\\\": " +
		                          "\\\"Top node of the node tree\\\", " +
		                          "\\\"type\\\": \\\"top\\\", \\\"children\\\": [], " +
		                          "\\\"nodePath\\\": [\\\"Top\\\"]" +
				                      "}]";
    StringBuilder   rv = new StringBuilder();
		rv.append("{ \"rows_returned\": 1, \"comments\": \"io for test_2\", \"data\": [");
		rv.append("{");
		rv.append("\"company\": \"example.com\", \"content\": \"mod_javac\", \"created\": 1568858794442, ");
		rv.append("\"division\": \"example.com\", \"site\": \"example.com\", ");
		rv.append("\"enabled\": true, \"id\": \"58b895eecd75479f84c31fdec0b4a1fc\", ");
		rv.append("\"mods\": \"" + modsStr + "\" ");
		rv.append("}");
		rv.append("] }");
	  return rv.toString();
	  */
		/* Declare and define a few variables */
		HDLmPassThruStatus  curStatus = HDLmMain.getPassThruStatusServer();
		String              tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the set pass-through status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Get pass-through";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
	  ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Build a (short) list of the command execution results */
	  var  actionListContents = new ArrayList<String>();
	  actionListContents.add("Status");
	  /* Check the actual pass-through status */
	  if (curStatus == HDLmPassThruStatus.NONE)
	    actionListContents.add("None");
	  else if (curStatus == HDLmPassThruStatus.PASSTHRUOK)
	    actionListContents.add("On");
	  if (curStatus == HDLmPassThruStatus.PASSTHRUNOTOK)
	    actionListContents.add("Off");
	  /* Construct an HTML table from the headings and the command execution
	     results */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, actionListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
  /* This routine tries to get the overall pass-through status for
     a specific company. Of course, no company information may exist.
     In that case, the overall pass-through status of the server is
     returned to the caller. */
	protected static HDLmPassThruStatus  getPassThruCompanyStatus(final String hostName) {
		/* Check if the host name value passed by the caller is invalid */
		if (hostName == null) {
			String  errorText = "Host name string passed to getPassThruCompany is null";
			throw new NullPointerException(errorText);
		}
	 /* Declare and define a few variables */
		HDLmPassThruStatus  passThruStatus = HDLmPassThruStatus.PASSTHRUNOTOK;
		HDLmPassThruTop     passThruTop = HDLmMain.getPassThruTopReference();
		/* If we can't access the top node, then we have no more
		   work to do */
		if (passThruTop == null)
		  return passThruStatus;
		/* Since we are able to access the top node, we need to
		   check if the top node shows that we are in pass-through
		   mode */
		if (passThruTop.getStatus() == HDLmPassThruStatus.PASSTHRUOK)
		  return HDLmPassThruStatus.PASSTHRUOK;
		/* Get the companies pass-through reference */
		HDLmPassThruCompanies   passThruCompanies = passThruTop.getCompaniesReference();
		if (passThruCompanies == null)
		  HDLmAssertAction(false, "Companies pass-through reference is null");
		/* Since we are able to access the top node, we can get the
		   list (actually a TreeMap) of companies that are currently
		   known to the server */
		TreeMap<String, HDLmPassThruCompany>  companiesTreeMap = passThruCompanies.getCompaniesTreeMap();
		assert(companiesTreeMap != null);
		/* Check if the host name passed by the caller is in the list
		   of companies already known to the server */
		HDLmPassThruCompany  currentCompany = companiesTreeMap.get(hostName);
		if (currentCompany == null)
			return HDLmPassThruStatus.PASSTHRUNOTOK;
	  return currentCompany.getStatus();
	}
	/* This routine returns the current pass-through status of the server
	   to the caller */
	protected static HDLmPassThruStatus  getPassThruStatusServer() {
		/* Check if the top pass-through node has been created. If not,
		   just return a default value */
		HDLmPassThruTop  topNode = HDLmMain.getPassThruTopReference();
		if (topNode == null)
			return HDLmPassThruStatus.NONE;
		/* Use the top node to get the overall pass-through status */
		return topNode.getStatus();
	}
	/* This routine gets a reference to the top pass-through node of
	   the server and returns it to the caller */
	protected static HDLmPassThruTop  getPassThruTopReference() {
		/* Try to get the start of the pass-through tree */
		HDLmTree  topTree = HDLmTree.getNodePassTreeTop();
		if (topTree == null) {
			String  errorText = "Pass-through tree top is null";
			HDLmAssertAction(false, errorText);
		}
	  /* The overall pass-through status of the server is stored in the
	     instance obtained below. The overall pass-through status can be
	     changed and/or retrieved at any time. Commands are provided for
	     setting and getting the pass-through status of the server. Note
	     that the initial status is for pass-through to be disabled. In
	     other words rules should actually run and possibly make changes. */
		HDLmPassThruTop  topPassThru = (HDLmPassThruTop) topTree.getDetails();
		if (topPassThru == null) {
			String  errorText = "Pass-through top instance is null";
			HDLmAssertAction(false, errorText);
		}
		return topPassThru;
	}
	/* This routine returns a reference to the Jetty server built during
	   initialization. Of course, this reference could be a null value. */
	protected static Server  getServer() {
		return savedServerReference;
	}
	/* This routine returns a reference to the main set of Jetty statistics built
     during initialization. Of course, this reference could be a null value. */
  protected static StatisticsHandler  getStatistics() {
	  return savedStatisticsReference;
  }
  /* This routine gets the use mode from a cookie (indirectly). The cookie
	   provides the session ID. The session ID is used to get the test mode
	   from the session cache. The use mode is returned to the caller. */
	protected static String  getUseModeUsingSessionId(final String sessionIdStr) {
		/* Check if the session ID string passed by the caller is null */
		if (sessionIdStr == null) {
			String   errorText = "Session ID string passed to getUseModeUsingSessionId is null";
			throw new NullPointerException(errorText);
		}
		/* Using the session ID passed by the caller, get the session object
       from the session cache. The session object contains the test mode
       value. */
		HDLmSession   sessionObj = HDLmSession.getFromCacheIfPresent(sessionIdStr);
		if (sessionObj == null)
			return null;
	  String  useMode = sessionObj.getUseMode();
		return useMode;
	}
  /* Return a boolean true/false value showing if the Docker flag
     has been set of not. This value should have been set as part
     of startup. */
	protected static boolean  isDockerFlagSet() {
	  return HDLmMain.containerActive;
	}
  /* The object below is used to save a copy of all of the updates
 	   made to an HTML document. The object starts out empty. Updates
	   are added to the object, describing each change, as need be. */
	/* private static ArrayList<HDLmSavedChange> saveUpdatesObject = new ArrayList<HDLmSavedChange>(); */
	/* This is the main starting point for the entire application. The
	   code below starts Jetty and configures Jetty. */
	public static void main(String[] args) throws Exception {
		/* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
		/* Add some debugging information */
		if (logIsDebugEnabled)
			LOG.debug("in HDLmMain.main near the start");
		/* The main executed flag is set to 'yes' (without the quotes),
		   as soon as the main routine is invoked. This value can be
		   used to check if main has ever been executed or not. */
		HDLmState.setName("mainExecuted", "yes");
	  /* At this point we would like to process the arguments
       passed to this routine (if any). Of course, no arguments
       may have been passed to this routine. */
    HDLmMain.processArguments(args); 
		/* Check the current environment. This check mostly/definitely
		   applies to Docker. If we are running under Docker (or not),
		   then we will determine the current environment, if an
		   environmental variable is set. */
		HDLmUtility.setCurrentEnvironment();
		if (logIsDebugEnabled) {
		  LOG.debug("In HDLmMain.main just after setCurrentEnvironment");
		  LOG.debug(HDLmConfigInfo.getCurrentEnvironment());
		  LOG.debug(HDLmConfigInfo.getServerName());
		}
		/* Set some of the configuration values from secrets stored inside
		   AWS. We get the AWS secrets using the AWS secrets manager. Note
		   that some of the secrets are used to establish database connections.
		   As a consequence, this step must come before any database 
		   connections are established. */
		HDLmConfig.setConfigurationValues(); 
		/* The next set of lines were used to verify that we can 
		   get secrets from AWS. This code is commented out for now. */
		/* 
		String  AWSAccessKeyId = HDLmConfigInfo.getAccessKeyId();
		System.out.println(AWSAccessKeyId);
		*/
		/* Get the Hikari data source. This call has the effect
		   of building the JDBC connection pool. */
		HDLmHikariPool.getDataSource();
		/* Initialize undo/redo processing. This is done by calling
		   an initialization routine to the undo/redo module. */ 
		int   unReLimit = HDLmConfigInfo.getUnReLimit();
		HDLmUnRe.unReStartup(unReLimit);		
		/* Check what operating system (OS) we are running under. This
		   check definitely applies to Docker. If we are running under
		   Docker, then we will determine what operating system Docker
		   is running under. */
		HDLmUtility.setOverallEnvironment();
		if (logIsDebugEnabled) {
		  LOG.debug("In HDLmMain.main just after setOverallEnvironment");
		  LOG.debug(HDLmConfigInfo.getCurrentEnvironment());
		  LOG.debug(HDLmConfigInfo.getServerName());
		}
		/* Set a set of system properties. This properties allow the Graal engine
		   to run JavaScript code, without any reported errors. This call is only
		   needed once, which is why it is made here. */ 
		HDLmHtml.setProperties();	
		
		String  message = "{\"rules\":[{\"tooltip\":\"Script modification\",\"type\":\"mod\",\"children\":[],\"containerWidget\":null,\"details\":{\"name\":\"Mod These Changes Enhance Readability And Clearly Define The Script\",\"extra\":\"\",\"enabled\":true,\"type\":\"script\",\"created\":\"2025-01-26T22:53:07.162Z\",\"lastmodified\":\"2025-01-26T22:53:07.162Z\",\"pathvalue\":\"/yoga-direct-1-4-inch-yoga-mat.html\",\"comments\":\"\",\"usemode\":\"test\",\"cssselector\":\"\",\"xpath\":\"\",\"find\":[],\"nodeiden\":{\"type\":\"tag\",\"attributes\":{\"tag\":\"head\"},\"counts\":{\"tag\":1},\"parent\":{\"tag\":\"html\"}},\"parameter\":0,\"scripts\":[\"\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = 'body { font-family: \\\\'Roboto Condensed\\\\', sans-serif; font-size: 14px; line-height: 1.6; color: #333; }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = 'h1.page_headers, .saleprice.price { color: #2D5C73; }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.saleprice.price { font-weight: bold; font-size: 18px; }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.product-details h1.page_headers { margin-bottom: 20px; }';\\n  document.head.appendChild(styleSheet);\\n}\"],\"updated\":false},\"nodePath\":[\"Top\",\"Companies\",\"www.yogadirect.com\",\"Rules\",\"example.com\",\"example.com\",\"Mod These Changes Enhance Readability And Clearly Define The Script\"]},{\"tooltip\":\"Script modification\",\"type\":\"mod\",\"children\":[],\"containerWidget\":null,\"details\":{\"name\":\"Mod Storytelling Humanizes The Product, Making It Relatable Script\",\"extra\":\"\",\"enabled\":true,\"type\":\"script\",\"created\":\"2025-01-26T22:53:07.167Z\",\"lastmodified\":\"2025-01-26T22:53:07.167Z\",\"pathvalue\":\"/yoga-direct-1-4-inch-yoga-mat.html\",\"comments\":\"\",\"usemode\":\"test\",\"cssselector\":\"\",\"xpath\":\"\",\"find\":[],\"nodeiden\":{\"type\":\"tag\",\"attributes\":{\"tag\":\"head\"},\"counts\":{\"tag\":1},\"parent\":{\"tag\":\"html\"}},\"parameter\":0,\"scripts\":[\"    document.addEventListener(\\\"DOMContentLoaded\\\", function() {\\n        var productStoryHTML = \\\"<div id='product-story' class='product-story'><h2>Why Our Customers Love This Mat</h2><p>Our 1/4 Inch Yoga Mat is trusted by yoga practitioners worldwide for its perfect balance of comfort and durability. It's praised for transforming yoga practices into a beautiful experience. Join our community of wellness with every pose.</p></div>\\\";\\n        var descriptionSection = document.querySelector(\\\".short-description\\\");\\n        if (descriptionSection) {\\n            descriptionSection.insertAdjacentHTML('beforebegin', productStoryHTML);\\n        }\\n    });\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.product-story {\\\\n        background-color: #f9f9f9;\\\\n        padding: 20px;\\\\n        margin-bottom: 30px;\\\\n        border-radius: 8px;\\\\n        box-shadow: 0 2px 4px rgba(0,0,0,0.1);\\\\n    }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.product-story h2 {\\\\n        font-family: \\\\'Roboto Condensed\\\\', sans-serif;\\\\n        color: #333;\\\\n        font-size: 24px;\\\\n        margin-bottom: 10px;\\\\n    }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.product-story p {\\\\n        font-family: \\\\'Lato\\\\', sans-serif;\\\\n        color: #555;\\\\n        line-height: 1.6;\\\\n    }';\\n  document.head.appendChild(styleSheet);\\n}\"],\"updated\":false},\"nodePath\":[\"Top\",\"Companies\",\"www.yogadirect.com\",\"Rules\",\"example.com\",\"example.com\",\"Mod Storytelling Humanizes The Product, Making It Relatable Script\"]},{\"tooltip\":\"Script modification\",\"type\":\"mod\",\"children\":[],\"containerWidget\":null,\"details\":{\"name\":\"Mod Improving The Use Of Whitespace Around Product Details Script\",\"extra\":\"\",\"enabled\":true,\"type\":\"script\",\"created\":\"2025-01-26T22:53:07.169Z\",\"lastmodified\":\"2025-01-26T22:53:07.169Z\",\"pathvalue\":\"/yoga-direct-1-4-inch-yoga-mat.html\",\"comments\":\"\",\"usemode\":\"test\",\"cssselector\":\"\",\"xpath\":\"\",\"find\":[],\"nodeiden\":{\"type\":\"tag\",\"attributes\":{\"tag\":\"head\"},\"counts\":{\"tag\":1},\"parent\":{\"tag\":\"html\"}},\"parameter\":0,\"scripts\":[\"\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.product-details, .product-cols { padding: 20px; }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.main-image, .addl-images { margin-bottom: 20px; }';\\n  document.head.appendChild(styleSheet);\\n}\"],\"updated\":false},\"nodePath\":[\"Top\",\"Companies\",\"www.yogadirect.com\",\"Rules\",\"example.com\",\"example.com\",\"Mod Improving The Use Of Whitespace Around Product Details Script\"]}],\"HDLmRequestType\":\"storeTreeNodes\"}\r\n"
				+ "";
		message = "{\"rules\":[{\"tooltip\":\"Script modification\",\"type\":\"mod\",\"children\":[],\"containerWidget\":null,\"details\":{\"name\":\"Mod Enhancing Whitespace Between Elements Helps In Better Visual Script\",\"extra\":\"\",\"enabled\":true,\"type\":\"script\",\"created\":\"2025-01-27T19:45:15.935Z\",\"lastmodified\":\"2025-01-27T19:45:15.935Z\",\"pathvalue\":\"/yoga-direct-1-4-inch-yoga-mat.html\",\"comments\":\"\",\"usemode\":\"test\",\"cssselector\":\"\",\"xpath\":\"\",\"find\":[],\"nodeiden\":{\"type\":\"tag\",\"attributes\":{\"tag\":\"head\"},\"counts\":{\"tag\":1},\"parent\":{\"tag\":\"html\"}},\"parameter\":0,\"scripts\":[\"\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.product-details { margin-top: 20px; }';\\n  document.head.appendChild(styleSheet);\\n}\"],\"updated\":false},\"nodePath\":[\"Top\",\"Companies\",\"www.yogadirect.com\",\"Rules\",\"example.com\",\"example.com\",\"Mod Enhancing Whitespace Between Elements Helps In Better Visual Script\"]},{\"tooltip\":\"Script modification\",\"type\":\"mod\",\"children\":[],\"containerWidget\":null,\"details\":{\"name\":\"Mod Storytelling Helps In Creating An Emotional Connection Script\",\"extra\":\"\",\"enabled\":true,\"type\":\"script\",\"created\":\"2025-01-27T19:45:15.940Z\",\"lastmodified\":\"2025-01-27T19:45:15.940Z\",\"pathvalue\":\"/yoga-direct-1-4-inch-yoga-mat.html\",\"comments\":\"\",\"usemode\":\"test\",\"cssselector\":\"\",\"xpath\":\"\",\"find\":[],\"nodeiden\":{\"type\":\"tag\",\"attributes\":{\"tag\":\"head\"},\"counts\":{\"tag\":1},\"parent\":{\"tag\":\"html\"}},\"parameter\":0,\"scripts\":[\"    (function() {\\n        var storytellingDiv = document.createElement('div');\\n        storytellingDiv.className = 'storytelling';\\n        storytellingDiv.innerHTML = \\\"<h2>Discover Your Perfect Yoga Companion</h2><p>Embrace every pose with comfort and stability on our premium yoga mat, designed to inspire confidence in every practice. Join thousands of satisfied customers and take your yoga journey to new heights.</p><p><a href='#buy-now' class='call-to-action'>Buy Now</a></p>\\\";\\n        var productDetails = document.querySelector('.product-details');\\n        if (productDetails) {\\n            productDetails.insertAdjacentElement('beforebegin', storytellingDiv);\\n        }\\n    })();\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.storytelling {\\\\n        background-color: #f5f5f5;\\\\n        padding: 20px;\\\\n        margin: 20px 0;\\\\n        border-radius: 5px;\\\\n        text-align: center;\\\\n    }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.storytelling h2 {\\\\n        font-family: \\\\'Playfair Display\\\\', serif;\\\\n        color: #333;\\\\n        font-weight: 700;\\\\n    }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.storytelling p {\\\\n        font-family: \\\\'Lato\\\\', sans-serif;\\\\n        color: #555;\\\\n        font-size: 16px;\\\\n        margin: 10px 0;\\\\n    }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.storytelling .call-to-action {\\\\n        display: inline-block;\\\\n        padding: 10px 20px;\\\\n        color: #fff;\\\\n        background-color: #f57c00;\\\\n        border-radius: 3px;\\\\n        text-decoration: none;\\\\n        font-weight: 700;\\\\n    }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = '.storytelling .call-to-action:hover {\\\\n        background-color: #e65100;\\\\n    }';\\n  document.head.appendChild(styleSheet);\\n}\"],\"updated\":false},\"nodePath\":[\"Top\",\"Companies\",\"www.yogadirect.com\",\"Rules\",\"example.com\",\"example.com\",\"Mod Storytelling Helps In Creating An Emotional Connection Script\"]},{\"tooltip\":\"Script modification\",\"type\":\"mod\",\"children\":[],\"containerWidget\":null,\"details\":{\"name\":\"Mod A Consistent Button Style With Contrasting Colors Will Script\",\"extra\":\"\",\"enabled\":true,\"type\":\"script\",\"created\":\"2025-01-27T19:45:15.942Z\",\"lastmodified\":\"2025-01-27T19:45:15.942Z\",\"pathvalue\":\"/yoga-direct-1-4-inch-yoga-mat.html\",\"comments\":\"\",\"usemode\":\"test\",\"cssselector\":\"\",\"xpath\":\"\",\"find\":[],\"nodeiden\":{\"type\":\"tag\",\"attributes\":{\"tag\":\"head\"},\"counts\":{\"tag\":1},\"parent\":{\"tag\":\"html\"}},\"parameter\":0,\"scripts\":[\"document.querySelectorAll('button').forEach(function(btn) { btn.style.backgroundColor = '#2a9d8f'; btn.style.color = '#fff'; btn.style.borderRadius = '5px'; btn.addEventListener('mouseover', function() { this.style.backgroundColor = '#21867a'; }); btn.addEventListener('mouseout', function() { this.style.backgroundColor = '#2a9d8f'; }); });\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = 'button { background-color: #2a9d8f; color: #fff; border-radius: 5px; }';\\n  document.head.appendChild(styleSheet);\\n}\\n{\\n  var styleSheet = document.createElement(\\\"style\\\");\\n  styleSheet.textContent = 'button:hover { background-color: #21867a; }';\\n  document.head.appendChild(styleSheet);\\n}\"],\"updated\":false},\"nodePath\":[\"Top\",\"Companies\",\"www.yogadirect.com\",\"Rules\",\"example.com\",\"example.com\",\"Mod A Consistent Button Style With Contrasting Colors Will Script\"]}],\"HDLmRequestType\":\"storeTreeNodes\"}\r\n"
				+ "";
		JsonParser  parser = new JsonParser();	
		JsonElement   topNodeJsonElement = null; 
		try {			
			topNodeJsonElement = parser.parse(message);			
		} 
		catch (Exception exception) {			 
			LOG.info("Exception while executing handleMessage");
			LOG.info(exception.getMessage(), exception); 
		}
		int pathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		JsonArray   rulesArrayJson = HDLmJson.getJsonArray(topNodeJsonElement,"rules");
		int  rulesArraySize = rulesArrayJson.size();
		for (int i = 0; i < rulesArraySize; i++) {		
			JsonElement   jsonElementRule = rulesArrayJson.get(i);
			boolean   scriptsValid = true;
			
			/* Check if we have mod entry. We should always
			   have a mod entry. */
		  
			String  modEntry = HDLmJson.getJsonString(jsonElementRule, "type");
			if (modEntry.equals("mod") == false) {
				String errorText = "Mod entry not found in storeTreeNode";
				HDLmAssertAction(false, errorText);
			}
			
			/* Get the rule details */
		 
			JsonObject  modDetaisObject = HDLmJson.getJsonObject(jsonElementRule, "details");
			if (modDetaisObject == null) {
				String errorText = "Mod details object not found in storeTreeNode";
				HDLmAssertAction(false, errorText);
			}
			
			/* Get the rule name */
		   
			JsonElement   modDetailsElement = (JsonElement) modDetaisObject;
			String  ruleName = HDLmJson.getJsonString(modDetailsElement, "name");
			
			/* Get the rule type */
		    
			String  ruleType = HDLmJson.getJsonString(modDetailsElement, "type");
			 
			/* We have a lot more work to do for script rules */
		     
			if (ruleType.equals("script")) {
				JsonArray   scriptsArrayJson = HDLmJson.getJsonArray(modDetailsElement,"scripts");
				int   scriptsArraySize = scriptsArrayJson.size();
				 
				/* Process each script */
		      
				for (int j = 0; j < scriptsArraySize; j++) {		
					JsonElement   scriptJson = scriptsArrayJson.get(j); 
					String  scriptString = scriptJson.getAsString();
					boolean   scriptValid = HDLmHtml.checkIfJavaScriptValid(scriptString, 
							                                                    HDLmReportErrors.REPORTERRORS);
					 
					/* Check if the current script is invalid, if it is, then 
					   the entire set of scripts is treated as invalid */
		        
					if (scriptValid == false)
						scriptsValid = false;
				   
		        
				}
			}		
			 
			/* Skip the current rule if the invalid scripts flag is set */
			/*
			scriptsValid = true;
			*/
		   
			if (scriptsValid == false)
				continue;
			JsonArray   nodePathJson = HDLmJson.getJsonArray(jsonElementRule,"nodePath");
			int nodePathJsonSize = nodePathJson.size();
		}
		 
		/*
		SecretsManagerClient  client = HDLmAwsSecrets.buildAwsSecretsManagerClient(\"us-east-2\");
		ArrayList<String>     actualNames = new ArrayList<String>(Arrays.asList(\"AwsAccessKeyId", "AwsSecretAccessKey", "Main9Auroa"));
		Map<String, String>   actualSecrets = HDLmAwsSecrets.getAMapOfSecrets(client, actualNames);
		*/
		/* 
		HDLmAwsSecrets.setConfigurationValues();
		*/
		/*
		String  secretName = "AwsAccessKeyId";
		String  actualSecret = HDLmAwsSecrets.getJustOneSecretFromAws(null, secretName);
		/*
		/*
		String  script = "return 'abcd';";
		script = "";
		boolean   rvBoolean = HDLmHtml.checkIfJavaScriptValid(script, 
				                                                  HDLmReportErrors.DONTREPORTERRORS);
    */			
		/* In the Windows environment, logging initialization won't look
		   in the correct directory for the right properties file. As a
		   consequence, we must specify the correct location. Note that
		   for Linux, the manifest file that is used to build the jar
		   file provides the correct location.

		   Note, that these comments apply mostly to log4j version 1.
		   For version 2, the location of the logging configuration
		   file is specified much earlier. */
	  if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS &&
	  		HDLmMain.isDockerFlagSet() == false) {
	 	  /* The following temporary code was added to get the current
	 	     working directory. This code is commented out for now. */
	    /* Path currentRelativePath = Paths.get(""); */
	    /* String s = currentRelativePath.toAbsolutePath().toString(); */
	    /* System.out.println("Current absolute path is: " + s); */
	  }
  	/* In the Windows environment, we don't want to use the cache
	     for rules. We want a fresh copy of the rules every time.
	     This makes testing under Windows easier. The check for
	     Eclipse below does not appear to work. */
	  if (HDLmMain.checkForEclipse() == true)
	  	HDLmConfig.setConfigString("entriesDatabaseUseCache", "NEVER");
  	/* In the Windows or Docker environments, we don't want to use
  	   the cache for rules. We want a fresh copy of the rules every
  	   time. This makes testing under Windows or Docker easier. */
		if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS ||
				HDLmMain.isDockerFlagSet() == true)
			HDLmConfig.setConfigString("entriesDatabaseUseCache", "NEVER");
	  /* The next field is used to count errors. If the number
	     of errors is greater than zero, then we can not process
	     any rules. Note that the initial value of this field is
	     zero. The field is incremented each time an error is
	     detected. */
	  int   systemErrorCounter = 0;
	  /* Get the system suffix character. This character is used to
	     build keys for obtaining data. For example, if the current
	     system is javaproxyb.dnsalias.com, then the system suffix
	     character would be 'b' (without the quotes). If the current
	     system is javaproxyc.dnsalias.com, then the system suffix
	     character would be 'c' (without the quotes). These rules
	     really only apply to Linux/Unix. Under Windows the system

	     suffix character is provided by the Java project. If the
	     Java project is ProxyServerA, then the suffix character
	     will be 'a' (without the quotes). If the Java project
	     is ProxySeverB, then the suffix character will be 'b'
	     (without the quotes). */
	  char    systemChar = HDLmUtility.getSystemCharacter();
	  String  systemString = "" + systemChar;
	  HDLmStateInfo.setEntriesSystemValue(systemString);
		if (logIsDebugEnabled) {
			LOG.debug(((Character) systemChar).toString());
			LOG.debug(systemString);
		}
	  /* Add all of the general events to the events map */
	  HDLmEvent.addEventsList(HDLmEvent.eventNames);
		/* The events added flag is set to 'yes' (without the quotes),
  	   as soon as all the events are added to the events map. This
  	   value can be used to check if all of the events have been
  	   added to the events map or not. */
  	HDLmState.setName("eventsAdded", "yes");
	  /* Add all of the general elapsed time entries to the
	     elapsed time map */
	  HDLmElapsed.addElapsedList(HDLmElapsed.elapsedNames);
		/* Check if the force pass-through flag is set. Bypass
		   all modification processing if this flag is set to
		   true. */
		if (HDLmConfigInfo.getForcePassThru() == false) {
			/* Build the pass-through tree from the pass-through data and definitions.
			   Note that this is done in a try block so that errors can be trapped and
			   handled. */
			try {
				if (logIsDebugEnabled)
				  LOG.debug("About to call HDLmMain.buildNodeTreeMain");
			 	HDLmTree   passThroughTree = HDLmMain.buildNodeTreeMain(null, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODEYES);
				if (passThroughTree == null) {
					HDLmAssertAction(false, "Null pass-through definitions tree returned by buildNodeTreeMain");
				}
			  HDLmTree.setNodePassTreeTop(passThroughTree);
			}
			catch (AssertionError assertionError) {
				LOG.info("buildPassThruTree");
			  LOG.info("AssertionError while executing main");
			 	LOG.info(assertionError.getMessage(), assertionError);
			  HDLmEvent.eventOccurred("AssertionError");
			  HDLmTree.setNodePassTreeTopNull();
			  systemErrorCounter++;
			  return;
			}
			catch (Exception e) {
			 	LOG.info("buildPassThruTree");
				LOG.info("Exception while executing main");
				LOG.info(e.getMessage(), e);
				HDLmEvent.eventOccurred("Exception");
			 	HDLmTree.setNodePassTreeTopNull();
			 	systemErrorCounter++;
			 	return;
			}
		}
		/* Build the proxy tree from the proxy definitions */
	  try {
	    HDLmTree   proxyTree = HDLmMain.buildNodeTreeMain(null, HDLmEditorTypes.PROXY, HDLmStartupMode.STARTUPMODEYES);
		  if (proxyTree == null) {
		 	  HDLmAssertAction(false, "Null proxy definitions tree returned by buildNodeTreeMain");
		  }
			HDLmProxy.setProxyTreeTop(proxyTree);
			/* Build the proxy list from the proxy definitions tree */
			ArrayList<HDLmProxy>   proxyList = HDLmProxy.buildProxyList(proxyTree);
			if (proxyList == null) {
				HDLmAssertAction(false, "Null proxy definitions list returned by buildProxyList");
			}
			HDLmProxy.setProxyListTop(proxyList);
			/* Build the proxy map from the proxy definitions tree */
			HashMap<String, HDLmProxy>   proxyMap = HDLmProxy.buildProxyMap(proxyTree);
			if (proxyMap == null) {
				HDLmAssertAction(false, "Null proxy definitions map returned by buildProxyMap");
			}
			HDLmProxy.setProxyMapTop(proxyMap);
		}
		catch (AssertionError e) {
		  LOG.info("buildProxyMap");
			LOG.info("AssertionError while executing main");
			LOG.info(e.getMessage(), e);
			HDLmEvent.eventOccurred("AssertionError");
			HDLmProxy.setProxyListTopNull();
			HDLmProxy.setProxyMapTopNull();
			HDLmProxy.setProxyTreeTopNull();
			systemErrorCounter++;
			return;
		}
		catch (Exception e) {
		  LOG.info("buildProxyMap");
			LOG.info("Exception while executing main");
			LOG.info(e.getMessage(), e);
		  HDLmEvent.eventOccurred("Exception");
		  HDLmProxy.setProxyListTopNull();
		  HDLmProxy.setProxyMapTopNull();
		  HDLmProxy.setProxyTreeTopNull();
		  systemErrorCounter++;
		  return;
		}
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* We have no more work to do if the system error counter
			   is greater than zero? */
			if (systemErrorCounter > 0)
				break;
		  /* Build a few variables for use below */
			int               maxFormSize = 2000000;
		  Server            server = null;
		  ServerConnector   connectorHttp = null;
		  ServerConnector   connectorHttp2 = null;
		  MonitoredQueuedThreadPool   threadPool = null;
	    /* Build a new HTTP configuration. This configuration is used later. */
		  HttpConfiguration httpConfig = null;
	    httpConfig = new HttpConfiguration();
	    httpConfig.setSecureScheme("https");
	    httpConfig.setSecurePort(443);
	    httpConfig.setSendXPoweredBy(true);
	    httpConfig.setSendServerVersion(true);
	    /* Build a new HTTPS configuration. This configuration is used later */
	    HttpConfiguration   httpsConfig = null;
	    httpsConfig = new HttpConfiguration(httpConfig);
	    httpsConfig.addCustomizer(new SecureRequestCustomizer());
	    /* Create a monitored queued thread pool for use with the
	       server */
	    threadPool = new MonitoredQueuedThreadPool();
		  /* Start a new Jetty server with no port number */
	    server = new Server(threadPool);
	    server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", maxFormSize);
	    server.setAttribute("org.eclipse.jetty.server.Request.maxFormKeys", 20000);
	    /* Build a connector for HTTP (including HTTP2C) */
	    connectorHttp = HDLmJetty.buildHttpConnector(server, 80);
	    /* Build a KeyStore instance for use below. This is a default
	       Jetty keystore for now. The keystore is loaded from a file
	       that contains the letsencrypt certificates. */
	    KeyStore  keyStore = HDLmJetty.buildKeyStore();
	    /* Build and configure an SSL/TLS/HTTP2 connector. Add both connectors
	       to the server. Start by building the SSL Context Factory for HTTPS
	       and HTTP/2. */
	    SslContextFactory.Server  sslContextFactoryDotServer = HDLmJetty.buildSslContextFactoryDotServer(keyStore);
	    /* Build an HTTP/2 connection factory. This connection factory is used later. */
	    HTTP2ServerConnectionFactory  http2ConnectionFactory = null;
	    http2ConnectionFactory = new HTTP2ServerConnectionFactory(httpsConfig);
	    http2ConnectionFactory.setRateControlFactory(new WindowRateControl.Factory(HDLmConfigInfo.getRateControl()));
	    /* Build an ALPN connection factory. This connection factory is used later. */
	    ALPNServerConnectionFactory   alpnConnectionFactory = null;
	    alpnConnectionFactory = new ALPNServerConnectionFactory();
	    alpnConnectionFactory.setDefaultProtocol(connectorHttp.getDefaultProtocol());
	    /* Build the SSL connection factory. This connection factory is used later */
	    SslConnectionFactory  sslConnectionFactory = new SslConnectionFactory(sslContextFactoryDotServer,
	   		                                                                    alpnConnectionFactory.getProtocol());
	    /* The call below may or may not build an HTTPS connector that supports
	       HTTP2. The connector will always be an HTTPS connector. It may support
	       HTTP2 and HTTP/1.1 or it may just support HTTP/1.1. This call is no
	       longer in use. See below for the call that actually builds the HTTP/2
	       connection factory. */
	    if (1 == 2) {
	      connectorHttp2 = HDLmJetty.buildHttpsConnector(server,
	     		                                             sslContextFactoryDotServer,
	     		                                             connectorHttp,
	     		                                             443);
	    }
	    else {
	    	if (HDLmConfigInfo.getSupportHttp2() == true) {
		      connectorHttp2 = new ServerConnector(server,
		     	  	                                 sslConnectionFactory,
		     		                                   alpnConnectionFactory,
		   		                                     http2ConnectionFactory,
		   		                                     new HttpConnectionFactory(httpsConfig));
		      connectorHttp2.setPort(443);
	    	}
	    }
	    /* Build a connector for HTTP/3. This code is not in use because the
	       Jetty folks say that HTTP/3 is not ready for production use. We
	       are going to have to wait a while for a production version of HTTP/3. */
		  if (HDLmConfigInfo.getSupportHttp3() == true) {
	      sslContextFactoryDotServer.setKeyStorePath(HDLmJetty.getStandardKeyStorePath());
	      sslContextFactoryDotServer.setKeyStorePassword(HDLmJetty.getStandardKeyStorePassword());
	      HTTP3ServerConnector  connectorHttp3 = null;
	      connectorHttp3 = new HTTP3ServerConnector(server,
	                                                sslContextFactoryDotServer,
	                                                new HTTP3ServerConnectionFactory(httpsConfig));
	      connectorHttp3.setPort(443);
	      server.addConnector(connectorHttp3);
	      HDLmState.setName("http3Connected", "yes");
	    }
	    /* Add the standard Connectors to the Server */
	    server.addConnector(connectorHttp);
	    if (HDLmConfigInfo.getSupportHttp2() == true)
	      server.addConnector(connectorHttp2);
	    /* Build the Servlet context handlers and store the context handlers on the server */
	    ServletContextHandler  contextBase = new ServletContextHandler();
	    ServletContextHandler  contextBaseInfo = new ServletContextHandler();
	    ServletContextHandler  contextInternalWebSockets = new ServletContextHandler();
	    ServletContextHandler  contextStandardWebSockets = new ServletContextHandler();
	    /* We create four context objects here. The first one is obvious and
	       handles most of the traffic. The second one is for a single URL that
	       must be passed (proxyied) to the original server. The third one is for
	       the requests that must be turned into a web socket for internal use.
	       The fourth one is for the requests that must be turned into a web socket

	       The path information specified here is very specific to OWO and Accesso.
	       Note that OWO and Accesso use WebSockets with the path values below. */
	    contextBase.setContextPath("/");
	    contextBaseInfo.setContextPath("/api/socket/info");
	    contextInternalWebSockets.setContextPath("/HDLmWebSocketServer");
	    contextStandardWebSockets.setContextPath("/api/socket/");
	    /* Set the maximum form size values for each of the context handlers.
	       We need very large values (at least for now) because all of the
	       rules are send back to the server as one JSON string. */
	    contextBase.setMaxFormContentSize(maxFormSize);
	    contextBaseInfo.setMaxFormContentSize(maxFormSize);
	    contextInternalWebSockets.setMaxFormContentSize(maxFormSize);
	    contextStandardWebSockets.setMaxFormContentSize(maxFormSize);
	    /* The call below adds a session handler to the current servlet
	       context handler. This call is not used for now. We don't need
	       a session handler at this point. */
	    /* servletContextHandler.setSessionHandler(new SessionHandler()); */
	    contextBase.addServlet(HDLmMainServlet.class, "/");
	    contextBaseInfo.addServlet(HDLmMainServlet.class, "/");
	    contextInternalWebSockets.addServlet(HDLmWebSocketInternalServlet.class, "/");
	    contextStandardWebSockets.addServlet(HDLmWebSocketStandardServlet.class, "/");
	    /* The next two lines were added for Jetty 11. What they do is not at all clear.
	       However, they are needed to make Jetty 11 work. They were not needed for Jetty
	       9. However, they are needed for Jetty 11. */
	    JettyWebSocketServletContainerInitializer.configure(contextInternalWebSockets, null);
	    JettyWebSocketServletContainerInitializer.configure(contextStandardWebSockets, null);
	    /* Create a collection of context handlers */
	    ContextHandlerCollection  contextHandlerCollection = new ContextHandlerCollection();
	    contextHandlerCollection.setHandlers(new Handler[] {contextBase,
	   		                                                  contextBaseInfo,
	   		                                                  contextInternalWebSockets,
	   		                                                  contextStandardWebSockets,
	   		                                                  new DefaultHandler()});
	    /* Create a new list of handlers. This list is added to the
	       Jetty server. */
	    HandlerCollection   handlerCollection     = new HandlerCollection();
	    StatisticsHandler   statisticsHandlerSave = null;
	    /* Check if we should use a handler collection or the older approach */
	    if (1 == 1) {
	      /* Add the handler collection */
	      server.setHandler(handlerCollection);
        /* Create and install a Gzip handler */
	      GzipHandler gzipHandler = new GzipHandler();
	      gzipHandler.setHandler(contextHandlerCollection);
        handlerCollection.addHandler(gzipHandler);
        /* Create a statistics handler. This handler is used to collect
           and (perhaps) display statistics. */
	      StatisticsHandler   statisticsHandler = new StatisticsHandler();
	      statisticsHandler.setServer(server);
	      statisticsHandler.setHandler(contextHandlerCollection);
        ServerConnectionStatistics.addToAllConnectors(server);
        statisticsHandlerSave = statisticsHandler;
        handlerCollection.addHandler(statisticsHandler);
        /* Provide a default handler */
        handlerCollection.addHandler(new DefaultHandler());
      }
      /* The following links have proven useful
         https://dzone.com/refcardz/jetty
         https://github.com/eclipse/jetty.project/blob/jetty-10.0.x/jetty-server/src/main/java/org/eclipse/jetty/server/handler/gzip/GzipHandler.java
         https://stackoverflow.com/questions/24521426/setup-jetty-gziphandler-programmatically
         https://stackoverflow.com/questions/57714253/gziphandler-on-jetty-uber-jar
	       https://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty
	       https://www.eclipse.org/jetty/documentation/jetty-10/programming-guide/index.html */
	    else {
	    	/* Provide a statistics handler */
	      StatisticsHandler   statisticsHandler = new StatisticsHandler();
	      statisticsHandler.setHandler(server.getHandler());
	      server.setHandler(statisticsHandler);
	      ServerConnectionStatistics.addToAllConnectors(server);
	      statisticsHandlerSave = statisticsHandler;
        /* Provide a Gzip handler */
	      GzipHandler gzipHandler = new GzipHandler();
	      gzipHandler.setHandler(contextHandlerCollection);
	      server.setHandler(gzipHandler);
	    }
	    /* Start the Jetty server */
	    server.start();
	    /* Store a reference to the Jetty server, now that the Jetty
	       server has been started */
	    savedServerReference = server;
	    /* Store a refeence to the main set of Jetty statistics, now
	       that the Jetty server has been started */
	    savedStatisticsReference = statisticsHandlerSave;
	    break;
		}
		/* It is not clear that the call below is needed or what it does. */
		/*
			HDLmTree.passUpdateAllRowsTest(HDLmUtility.getContentString(),
					                           HDLmTree.getNodePassTreeTop(),
					                           true);
		*/
		/*
    String checkStr = "{\"type\":\"top\",\"tooltip\":\"Top node of the node tree\",\"details\":{\"enabled\":false,\"dummyLastModified\":\"\",\"passThru\":false,\"type\":\"top\",\"updated\":false,\"extra\":\"\",\"name\":\"Top\"},\"nodePath\":[\"Top\"]}"; 
	  Gson      gson = HDLmMain.gsonMain;
	  HDLmTree  dataEntryObj = gson.fromJson(checkStr, HDLmTree.class);
	  */
		if (logIsDebugEnabled) 
	    LOG.debug(HDLmSecurity.getTimeStamp());	 
	  /* 
		HDLmTree.replaceEntireTree(HDLmUtility.getContentString(),
	                             HDLmTree.getNodePassTreeTop());
	  */ 
		/* 
		String    outTest = HDLmTree.buildJsonStringTree(HDLmTree.getNodePassTreeTop());
		*/
		/* 		
		String  outTest = null;
		*/		 
	}
  /* This code uses the memory storage mechanism for finding and using
	   memory storage. As a consequence, we need mechanisms for monitoring
	   memory storage. Note that the memory storage is stored in memory.
	   The function below is one of those mechanisms for reporting on
	   memory storage. */
	protected static String  memoryContents(String localServer, String clientStr) {
		int     memoryCount = 0;
		int     memoryLimit = 100;
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the memory contents heading HTML */
		String  infoType = "contents";
		String  prefixStr = "Memory Storage";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
			                                         prefixStr,
			                                         infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a reference to the set of memory we want to display */
		Map<String, String>  memoryStorageMap = HDLmMemoryStorage.getMemoryStorageMap();
		var  memoryStorageMapContents = new ArrayList<String>();
	  /* Build the memory list from the list of memory */
	  for (Entry<String, String> memoryStorageEntry : memoryStorageMap.entrySet()) {
	 	  /* Check if we have reached the limit of the number of memory
	 	     storage entries we will display */
	 	  if (memoryCount >= memoryLimit)
	 		  break;
	 	  /* Add the value for the current row */
	 	  memoryStorageMapContents.add(memoryStorageEntry.getKey());
		  /* Add the value for the current row */
	 	  String  valueStr = memoryStorageEntry.getValue();
	 	  valueStr = HDLmJson.maskStringInJson(valueStr, "\"Password\"");
	 	  memoryStorageMapContents.add(valueStr);
		  memoryCount++;
	  }
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Construct an HTML table from the headings and the memory list */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, memoryStorageMapContents);
		rv.append(tableHtml);
	  /* End the main div */
	  rv.append("</div>");
	  return rv.toString();
	}
	/* This code returns some memory storage statistics HTML to the caller */
	protected static String memoryStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the memory storage statistics status heading HTML */
		String  infoType = "Memory storage";
		String  prefixStr = "";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
                                               infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = HDLmMemoryStorage.getMemoryStorageHeadings(null);
		/* Get a reference to the map of memory storage information */
		var  memoryStorageMap = HDLmMemoryStorage.getMemoryStorageMap();
		if (memoryStorageMap == null) {
			String   errorText = "Memory storage map reference obtained from getMemoryStorageMap is null";
			HDLmAssertAction(false, errorText);
    }
  	/* Build a (short) list of the memory storage status information */
    var  statusListContents = HDLmMemoryStorage.getMemoryStorageStatus(memoryStorageMap,
    		                                                               HDLmGetStatus.HTMLINFORMATION);
    /* Construct an HTML table from the headings and the memory storage status list */
		tableHtml = HDLmHtml.buildHtmlTableFromListStyle(headings, statusListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* This code reports the overall pass-through status for the
	   server. This method returns the overall pass-through status
	   of the server to the caller. */
	protected static String passThruStatus(String localServer, String clientStr) {
		/* Declare and define a few variables */
		HDLmPassThruStatus  passThruStatus = HDLmMain.getPassThruStatusServer();
		String              tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the pass-through status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Pass-through";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
	  ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Build a (short) list of the pass-through status */
	  var  statusListContents = new ArrayList<String>();
 	  statusListContents.add("Status");
 	  /* Check the actual pass-through status */
 	  if (passThruStatus == HDLmPassThruStatus.PASSTHRUNOTOK)
      statusListContents.add("Off");
 	  else
 	  	statusListContents.add("On");
	  /* Construct an HTML table from the headings and the pass-through status
	     list */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, statusListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
  /* This code uses a Guava cache to store perceptual hash information.
     As a consequence, we need mechanisms for monitoring the status
     of the perceptual hash cache. The function below is one of those
     mechanisms. */
	protected static String phashContents(String localServer, String clientStr) {
		int     phashCount = 0;
		int     phashLimit = 100;
		String  tableHtml;
		/* Get a reference to the current set of clusters */
		var  clustersList = HDLmClustering.getClustersDefault();
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the perceptual hash cache heading HTML */
		String  infoType = "cache";
		String  prefixStr = "Perceptual hash";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	 /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a reference to the perceptual hash cache that
		   we want to display */
		var  phashCopyCache = HDLmPHashCache.getMap();
	  var  cacheListContents = new ArrayList<String>();
	  /* Build the cache list from the cache */
	  for (var phashEntry : phashCopyCache.entrySet()) {
	  	/* Check if we have reached the limit of the number of perceptual
	  	   hash cache entries we will display */
	  	if (phashCount >= phashLimit)
	  		break;
	  	String  phashString = phashEntry.getValue();
	 	  cacheListContents.add(phashString);
	 		cacheListContents.add(phashEntry.getKey());
	 		/* Try to find the closest cluster for the current cache
	 		   entry */
	 		HDLmCluster closestCluster = HDLmClustering.findClosestCluster(clustersList, phashString);
	 		if (closestCluster != null) {
	 			cacheListContents.add(closestCluster.getClusterName());
	 			String  closestClusterAverage = closestCluster.getClusterAverage();
	 			double  closestClusterSimilarity = HDLmHamming.distanceAdjusted(closestClusterAverage, phashString);
	 			cacheListContents.add(Double.toString(closestClusterSimilarity));
	 		}
	 		else {
	 			cacheListContents.add("none");
	 			cacheListContents.add("none");
	 		}
	 	  phashCount++;
	  }
    /* Build an ArrayList with all of the headings */
	  ArrayList<String>  headings = new ArrayList<String>(List.of("Value", "Key", "Cluster", "Distance"));
	  /* Construct an HTML table from the headings and the perceptual hash
	     cache contents list */
	  tableHtml = HDLmHtml.buildHtmlTableFromList(headings, cacheListContents);
	  rv.append(tableHtml);
    /* End the main div */
    rv.append("</div>");
    return rv.toString();
  }
	/* This code uses a cache to store perceptual hash information.
	   As a consequence, we need mechanisms for monitoring the status
	   of the perceptual hash cache. The function below is one of those
	   mechanisms. */
	protected static String phashStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the perceptual hash status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Perceptual hash";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	 /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Build the tree of perceptual hash cache statistics */
		var  cacheTreeStats = HDLmPHashCache.getStatistics();
		/* The code below builds a 2D Java table from the perceptual hash
		   statistics map. We don't really need the 2D table for now. We
		   can use a different routine to convert the perceptual hash
		   statistics map directly to HTML. */
		/*
		var tableArray = HDLmUtility.buildTable(cacheTreeStats);
		*/
 	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Build a set of table HTML from the table array and the column headings.
		   This step is not needed for now because we can build the table HTML
		   from statistics map. */
		/*
		tableHtml = HDLmHtml.buildHtmlTableFromArray(headings, tableArray);
		*/
		/* Construct an HTML table from the headings and the cache statistics
		   tree map */
		tableHtml = HDLmHtml.buildHtmlTableFromTree(headings, cacheTreeStats);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* Process the arguments passed to this program (if any) */
	protected static void  processArguments(String[] args) {
		/* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
		/* Check if debugging logging is enabled */
		if (logIsDebugEnabled)
	   LOG.debug("In HDLmMain.processArguments");
    /* Check if the passed value is null */
		if (args == null)
			return;
		/* Get the number of arguments */
		int   arSize = args.length;
		if (logIsDebugEnabled) {
		  LOG.debug("In HDLmMain.processArguments"); 
		  LOG.debug(((Integer) arSize).toString()); 
		}
		if (arSize == 0)
			return;
		/* Check each of the arguments */
		for (int i = 0; i < arSize; i += 2) {
			/* Get the current argument */
			String  curArg = args[i];
			/* Check if debugging logging is enabled */
			if (logIsDebugEnabled)
		   LOG.debug(curArg);
			/* Check if the current argument is actually a define of some
			   kind. We really want to skip defines here. */
			if (curArg.startsWith("-D") == true)
				continue;
			/* Check if the current argument is actually an option of some
		     kind. We really want to skip options here. */
		  if (curArg.startsWith("-X") == true)
			  continue;
			/* Check for a valid set configuration command */
			boolean   match = curArg.equalsIgnoreCase("SetConfig");
			/* Report an error, if the argument name is invalid */
			if (!match) {
				String  errorFormat = "Argument number (%d) is invalid (%s)";
				String  errorText = String.format(errorFormat, (i+1), curArg);
        Exception exception = new IllegalArgumentException(errorText);
        throw new IllegalArgumentException(errorText, exception);
			}
			/* Report an error if we don't have a value for the current argument */
			if ((i+1) >= arSize) {
				String  errorFormat = "Argument number (%d) does not have a value";
				String  errorText = String.format(errorFormat, (i+1));
        throw new NoSuchElementException(errorText);
			}
			/* Get the current value and break it into a configuration
			   name and a configuration value */
			String  curVal = args[i+1];
			String  curValSplit[] = curVal.split("=");
			/* Split the current value and make sure we got two pieces */
			int     curValSize = curValSplit.length;
			if (curValSize != 2) {
				String  errorFormat = "Value for argument number (%d) did not split into two pieces (%s)";
				String  errorText = String.format(errorFormat, (i+1), curVal);
        Exception exception = new IllegalArgumentException(errorText);
        throw new IllegalArgumentException(errorText, exception);
			}
			/* Get the configuration name and the configuration value from the
			   split string */
			String  configName = curValSplit[0];
			String  configValue = curValSplit[1];
			HDLmConfig.setConfigString(configName, configValue);
		}
	}
	/* This code reload all of the nodes including the modifications
	   when it is invoked by the caller */
	protected static boolean reloadNodes() {
		/* Declare and define a few variables */
		boolean  modsStatusOkNotOk = false;
    /* Get the needed JSON */
		String    colName = "content";
		String    jsonResults = HDLmMain.getJsonAllRowsFromSource(colName, HDLmEditorTypes.PASS);
	  /* Check if the CURL request failed. Just return to the caller
	     if the CURL request failed. We don't have any modifications
	     to handle, if the CURL request did not work. */
	  if (jsonResults == null ||
	  		jsonResults.isEmpty())
	    return modsStatusOkNotOk;
	  /* Use the output from the Curl request */
	  HDLmTree  treeTopPos = HDLmTree.addToTree(jsonResults, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
	  HDLmTree.setNodePassTreeTop(treeTopPos);
		/* We now need to get the procedural hash values from every HDLm tree
	     element that has one. Of course, many do not. */
	  HDLmProcessTreePHash  pHashInstance = new HDLmProcessTreePHash();
	  HDLmTree.processTree(treeTopPos, pHashInstance);
	  modsStatusOkNotOk = true;
	  return modsStatusOkNotOk;
	}
  
	/* This routine removes zero or more modifications from the modification array.
	   Some modifications can only be used if use mode is set to 'test' (without the
	   quotes). This routine removes those modifications that are test only when the
	   use mode is not set to 'test'. If test mode is not active, and a modification 
	   is 'test' (without the quotes) only, then it is removed. */
	protected static void  removeSomeMods(final String passedUseMode,
			                                  final ArrayList<HDLmMod> modsList) {
			        
		/* Check if the use mode is null. This value can be
		   null (not an error condition). */
		if (passedUseMode == null &&
				passedUseMode != null) {
			String   errorText = "Use mode reference passed to removeSomeMods is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the modifications list is null. */
	  if (modsList == null) {
	  	String   errorText = "Modifications list reference passed to removeSomeMods is null";
		  throw new NullPointerException(errorText);
	  }
	  /* Check if the use mode passed to this routine is null. Just 
	     use the default value if the use mode is null. The default
	     value is 'prod' (without the quotes). */
	  String  localPassedUseMode = "prod";
		if (passedUseMode != null &&
				passedUseMode.length() > 0)
			localPassedUseMode = passedUseMode.toLowerCase();
		/* Check if we are in production mode. Set a flag
	     showing that we are in production mode. */
	  boolean   passedProdMode = false;
	  if (localPassedUseMode.equals("prod") ||
        localPassedUseMode.equals("production"))
		  passedProdMode = true;
		/* Check if we are in test mode. Set a flag
		   showing that we are in test mode. */
		boolean   passedTestMode = false;
		if (localPassedUseMode.equals("test"))
			passedTestMode = true;
	  /* Check each elememt in the modifications list */
		for (int i = 0; i < modsList.size(); i++) {
			/* Get the current modification */
			HDLmMod   modObj = modsList.get(i);
			/* Assume we are not going to use the current
	       rule/modification */		
			boolean   localUseTheMod = false;
			/* Get the use mode for the current modification */
			String  localModUseMode = "prod";			
			if (modObj.getUseMode() != null &&  
					modObj.getUseMode().length() > 0)
				localModUseMode = modObj.getUseMode().toLowerCase();
			/* Check for a couple of values that always cause the 
			   rule/currrent modification to be used*/
			if (localModUseMode.equals("on")  ||
					localModUseMode.equals("all") ||
          localModUseMode.equals("always")) 
				localUseTheMod = true;
			/* Check for a couple of values that always cause the 
		     rule/currrent modification not to be used*/
	  	if (localModUseMode.equals("off")  ||
				  localModUseMode.equals("none") ||
          localModUseMode.equals("never")) 
	  		localUseTheMod = false;
	  	/* Check if the local use mode is 'prod' (without the quotes) 
	  	   in some form. This is actually, the most likely case. */
	  	boolean   localProdUseMod = false;			
			if (localModUseMode.equals("prod") ||
				  localModUseMode.equals("production"))
				localProdUseMod = true;			
			/* Check if this is a production rule/modification   
			   and we are in production mode */
			if (passedProdMode && 
					localProdUseMod)
				localUseTheMod = true;
			/* Check if we are in test mode and the current
			   rule/modification is a test rule/modification 
			   or a production rule/modification */
	   	if (passedTestMode &&
			 	  (localProdUseMod ||
				   localModUseMode.equals("test")))		
	   		localUseTheMod = true;			
			/* Check if the current modification should be used */  
		  if (localUseTheMod == false) {                  
				/* Remove the current modification */
				modsList.remove(i);
				i--;
			}			
		}
	}
  /* This code returns some rules information HTML to the caller.
	   This routine can be used to obtain information about all of
	   the rules or just one rule If this routine is used to obtain
	   information about just one rule, the rule use history for
	   that rule is displayed. */
	protected static HDLmResponse  rulesStatus(final String localServer,
			                                       final String clientStr,
			                                       final HttpServletRequest request) {
		/* Declare and define a few values */
		boolean       encodingException = false;
		boolean       jsonWanted = false;
		HDLmResponse  outResponse = new HDLmResponse();
		String        headingHtml = null;
		String        jsonOutput;
		String        ruleJsonParm = null;
		String        ruleOverallParm = null;
		String        tableHtml;
		/* Create a new string builder for the output HTML or JSON */
		StringBuilder  outStr = new StringBuilder();
		/* Check if this routine was invoked to provide information about
		   all of the rules or just one rule */
		if (request.getParameterMap().containsKey("rule")) {
			ruleOverallParm = request.getParameter("rule");
		}
		/* Check if this routine was invoked to provide information in
		   JSON format */
	  if (request.getParameterMap().containsKey("json")) {
		  ruleJsonParm = request.getParameter("json");
		  if (ruleJsonParm.equalsIgnoreCase("yes"))
		  	jsonWanted = true;
	  }
		/* Start the main div, if need be */
	  if (jsonWanted == false)
	  	outStr.append("<div>");
		/* Build the rules information status heading HTML. This is
		   the heading for all of the rules. */
		if (ruleOverallParm == null) {
			String  infoType = "status";
			String  prefixStr = "Rules";
			headingHtml = HDLmHtml.headingHtml(localServer,
			                                   prefixStr,
	                                       infoType,
			                                   clientStr);
		}
		/* We need to build a heading for just one rule. This is
		   a somewhat different heading for just one rule. */
		else {
			String  infoType = "status";
			String  prefixStr = "Rule";
			headingHtml = HDLmHtml.headingHtml(ruleOverallParm,
			                                   prefixStr,
	                                       infoType,
			                                   clientStr);

		}
		/* Add the rule(s) information status heading HTML to the output
		   if need be */
		if (jsonWanted == false)
			outStr.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		outStr.append(tableCss);
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = null;
		/* Build the rules table heading HTML. This is the table
	     heading for all of the rules. */
		if (ruleOverallParm == null) {
			headings = new ArrayList<String>(List.of("Rule<br/>Name", "Rule<br/>Type",
			                                         "Rule Use<br/>Count",
                                               "Last Use<br/>Timestamp",
                                               "Time Since<br/>Last Use",
                                               "Last Rule<br/>Status"));
		}
		/* We need to build the able heading for just one rule. This is
	     a somewhat different table heading for just one rule. */
		else {
			headings = HDLmRule.getRuleHeadings();
		}
		/* Get a reference to the map of rules information */
		var  rulesMap = HDLmRule.getRulesMap();
		if (rulesMap == null) {
			String   errorText = "Rules map reference obtained from getRulesMap is null";
			HDLmAssertAction(false, errorText);
    }
    /* Create an rules comparator for use in building/maintaining the
       tree rule set. This comparator orders the rules based on the
       rule type and the rule overall name. */
		class rulesComparator implements Comparator<HDLmRule> {
			@Override
			public int compare(HDLmRule e1, HDLmRule e2) {
				int   compareInt;
				compareInt = e1.getType().getValue() - e2.getType().getValue();
				if (compareInt == 0)
					compareInt = e1.getOverall().compareTo(e2.getOverall());
				return compareInt;
			}
	  }
		/* Create a new tree set for use below. This tree set is
		   used to handle all of the rules and the uses of just
		   oen rule. */
		TreeSet<HDLmRule>   rulesSet = null;
		/* Check if we are handling all of the rules or providing
		   detailed information about the use of one rule */
		if (ruleOverallParm == null) {
			/* Create a new rules set that uses the rules comparator */
			rulesSet = new TreeSet<HDLmRule>(new rulesComparator());
		  /* Process each rule in the rules map. Each rule in the rules map
		     is copied into the rules set. This has the effect of creating a
		     rules set with all of the correct vales in the correct order. */
		  for (HDLmRule curRule : rulesMap.values())
		  	rulesSet.add(curRule);
		}
		/* We are providing detailed information about just one rule.
		   We must locate the rule in the rules map and get the uses
		   of the rule. */
		else {
			/* Create a new tree set that uses the count comparator */
			rulesSet = HDLmRule.getRuleTree(ruleOverallParm);
		}
		/* Declare and define the rule status array list */
		ArrayList<String>  statusListContents = null;
    /* Check if we should build HTML or JSON at this point */
    if (jsonWanted) {
    	/* Build a (short) list of the rule status information */
      statusListContents = HDLmRule.getRuleStatus(rulesSet,
                                                  HDLmGetRuleStatus.JSONINFORMATION,
      	                                          ruleOverallParm);
      /* The status list will have some (one per logical row) extra
         values that we need to get rid of */
      ArrayList<String>   adjustedStatusList = new ArrayList<String>();
      int   headingsSize = headings.size();
      int   statusSize = statusListContents.size();
      for (int i = 0; i < statusSize; i += (headingsSize + 1)) {
      	for (int j = i; j < i+headingsSize; j++) {
      		adjustedStatusList.add(statusListContents.get(j));
      	}
      }
      /* Build the output JSON from the adjusted status list */
			jsonOutput = HDLmJson.buildJsonFromList(headings, adjustedStatusList);
			outStr.append(jsonOutput);
    }
    /* Construct an HTML table from the headings and the rules status list */
    else {
    	/* Build a (short) list of the rule status information */
      statusListContents = HDLmRule.getRuleStatus(rulesSet,
                                                  HDLmGetRuleStatus.HTMLINFORMATION,
      	                                          ruleOverallParm);
			tableHtml = HDLmHtml.buildHtmlTableFromList(headings, statusListContents);
			outStr.append(tableHtml);
    }
		/* End the main div, if need be */
		if (jsonWanted == false)
			outStr.append("</div>");
		/* Store the output string in the response object */
		outResponse.setReturnString(outStr.toString());
		/* Set the response type in some cases */
		if (jsonWanted)
			outResponse.setResponseType(HDLmResponseTypes.JSON);
	  return outResponse;
	}
	/* This code returns some server statistics HTML to the caller */
	protected static String serverStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the server statistics status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Servers";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a reference to the statistics handler object */
		var  statisticsHandler = HDLmMain.getStatistics();
		/* Build the tree of statistics */
		var  statsTreeStats = HDLmStatisticsHandler.getStatistics(statisticsHandler);
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Construct an HTML table from the headings and the statistics tree map */
		tableHtml = HDLmHtml.buildHtmlTableFromTree(headings, statsTreeStats);
		rv.append(tableHtml);
		/* Get a reference to the Jetty server */
		var  server = HDLmMain.getServer();
		if (server == null) {
			String   errorText = "Sever reference obtained from getServer is null";
			HDLmAssertAction(false, errorText);
    }
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
  /* This code uses a Guava cache to store session ID information.
     As a consequence, we need mechanisms for monitoring the status
     of the cache. The function below is one of those mechanisms. */
	protected static String sessionIdContents(String localServer, String clientStr) {
		int     sessionCount = 0;
		int     sessionLimit = 100;
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the session ID cache heading HTML */
		String  infoType = "cache";
		String  prefixStr = "Session ID";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
			                                         prefixStr,
			                                         infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a reference to a copy of the cache that we want to display */
		var  sessionCopyCache = HDLmSessionCache.getMap();
	  var  cacheListContents = new ArrayList<String>();
	  /* Build the cache list from the cache */
	  for (var sessionEntry : sessionCopyCache.entrySet()) {
	   	/* Check if we have reached the limit of the number of sessions
	 	     we will display */
	 	  if (sessionCount >= sessionLimit)
	 		    break;
	 	  cacheListContents.add(sessionEntry.getKey());
	    cacheListContents.add((sessionEntry.getValue()).buildJson());
	 	  sessionCount++;
	  }
	  /* Build an ArrayList with all of the headings */
	  ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
	  /* Construct an HTML table from the headings and the cache contents
	     list */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, cacheListContents);
		rv.append(tableHtml);
	  /* End the main div */
	  rv.append("</div>");
	  return rv.toString();
	}
	/* This code uses a cache to store session ID information. As a consequence,
	   we need mechanisms for monitoring the status of the cache. The function
	   below is one of those mechanisms. */
	protected static String sessionIdStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the session ID status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Session ID";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Build the tree of cache statistics */
		var  cacheTreeStats = HDLmSessionCache.getStatistics();
		/* The code below builds a 2D Java table from the sessions statistics
		   map. We don't really need the 2D table for now. We can use a different
		   routine to convert the session statistics map directly to HTML. */
		/*
		var tableArray = HDLmUtility.buildTable(cacheTreeStats);
		*/
	 /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Build a set of table HTML from the table array and the column headings.
		   This step is not needed for now because we can build the table HTML
		   from the statistics map. */
		/*
		tableHtml = HDLmHtml.buildHtmlTableFromArray(headings, tableArray);
		*/
		/* Construct an HTML table from the headings and the cache statistics
		   tree map */
		tableHtml = HDLmHtml.buildHtmlTableFromTree(headings, cacheTreeStats);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	   return rv.toString();
	}
  /* Set a boolean true/false value showing if we are running
     inside a Docker container. This value should be set as
     part of startup. */
	protected static void  setDockerFlagActive(final boolean dockerActiveValue) {
		/* Set the final Docker container active value */
	  HDLmMain.containerActive = dockerActiveValue;
	}
	/* This routine sets the current value of the Junit
     active flag. This flag is manually set. This flag is
     not automatically set. */ 
  protected static void  setJunitActive(final boolean junitActiveValue) {
	  HDLmMain.junitActive = junitActiveValue;
  }
  /* Set the operating system type. This is the type of the parent
	   operating system, if we are actually running in a Docker
	   container. The Docker container provides (roughly) a Linux
	   operating system. However, if a Docker container is active,
	   this is the operating system that the Docker container is
	   running in. */
	protected static void  setOsType(final HDLmOsTypes osTypeValue) {
		/* Check if the OS type value passed by the caller is invalid */
		if (osTypeValue == null) {
			String  errorText = "OS type value passed to setOsType is null";
			throw new NullPointerException(errorText);
		}
		/* Set the final operating system type value */
	  HDLmMain.osType = osTypeValue;
	}
	/* This routine sets the current pass-through status of the server
	   using a value passed by the caller. This can only be done if the
	   pass-through top node has been built. */
	protected static final void setPassThruStatus(final HDLmPassThruStatus newValue) {
		/* Check if the top pass-through node has been created. If not,
		   then we can not set the overall pass-through status of the
		   server. */
	  HDLmPassThruTop  topNode = HDLmMain.getPassThruTopReference();
	  if (topNode == null) {
			String  errorText = "Pass-through top node is null";
			throw new NullPointerException(errorText);
	  }
	  /* Use the top node to set the overall pass-through status */
	  topNode.setStatus(newValue);
	}
	/* This code gets a list of system threads and some information
	   about each thread. The information is returned to the user. */
	protected static String systhrContents(String localServer, String clientStr) {
		int     systhrCount = 0;
		int     systhrLimit = 5000;
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the system threads heading HTML */
		String  infoType = "threads";
		String  prefixStr = "System";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
			                                         prefixStr,
			                                         infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a set of all of the current threads */
		Set<Thread> threads = Thread.getAllStackTraces().keySet();
		var  systhrTreeContents = new TreeMap<String, Thread>();
	  var  systhrListContents = new ArrayList<String>();
	  /* Add all of the system threads to the tree map. This puts
	     them (the threads) in alphabetic order, by thread name. */
	  for (var thread : threads) {
	  	/* Check if we have reached the limit of the number of threads
		     we will display */
		  if (systhrCount >= systhrLimit)
			    break;
		  systhrTreeContents.put(thread.getName(), thread);
		  systhrCount++;
	  }
	  /* Process each entry (thread) in the tree map */
	  for (var threadEntry : systhrTreeContents.entrySet()) {
		  systhrListContents.add(threadEntry.getKey());
		  Thread  thread = threadEntry.getValue();
		  systhrListContents.add(Long.toString(thread.getId()));
	    systhrListContents.add(thread.getState().toString());
	    systhrListContents.add(Integer.toString(thread.getPriority()));
	    systhrListContents.add(thread.getThreadGroup().toString());
	    systhrListContents.add(thread.isAlive() ? "Yes" : "No");
	    systhrListContents.add(thread.isDaemon() ? "Daemon" : "Normal");
	    systhrListContents.add(thread.isInterrupted() ? "Yes" : "No");
		  systhrCount++;
	 }
	 /* Build an ArrayList with all of the headings */
	 ArrayList<String>  headings = new ArrayList<String>(List.of("Name", "ID",
			                                                         "State", "Priority",
			                                                         "Thread Group",
			                                                         "Alive", "Daemon",
			                                                         "Interrupted"));
	 /* Construct an HTML table from the headings and the system thread contents
	    list */
		tableHtml = HDLmHtml.buildHtmlTableFromList(headings, systhrListContents);
		rv.append(tableHtml);
	  /* End the main div */
	  rv.append("</div>");
	  return rv.toString();
	}
	/* This code returns some thread statistics HTML to the caller */
	protected static String systhrStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the thread statistics status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Threads";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
				                                       infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
		/* Get a reference to the Jetty server */
		var  server = HDLmMain.getServer();
		if (server == null) {
			String   errorText = "Sever reference obtained from getServer is null";
			HDLmAssertAction(false, errorText);
    }
		/* Get a reference to the monitored queued thread pool object */
		var  threadPool = server.getThreadPool();
		if (threadPool == null) {
			String   errorText = "Thread pool reference obtained from getThreadPool is null";
			HDLmAssertAction(false, errorText);
    }
		/* Build the tree of thread statistics */
		var  statsTreeStats = HDLmThreadStatistics.getStatistics((MonitoredQueuedThreadPool) threadPool);
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = new ArrayList<String>(List.of("Key", "Value"));
		/* Construct an HTML table from the headings and the statistics tree map */
		tableHtml = HDLmHtml.buildHtmlTableFromTree(headings, statsTreeStats);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
	/* This code returns some timings information to the caller */
	protected static String timingsStatus(String localServer, String clientStr) {
		String  tableHtml;
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Start the main div */
		rv.append("<div>");
		/* Build the timings information status heading HTML */
		String  infoType = "status";
		String  prefixStr = "Timings";
		String  headingHtml = HDLmHtml.headingHtml(localServer,
				                                       prefixStr,
                                               infoType,
				                                       clientStr);
		rv.append(headingHtml);
	  /* Build some CSS for formatting the table */
		String  tableCss = HDLmHtml.buildCssTable();
		rv.append(tableCss);
	  /* Build an ArrayList with all of the headings */
		ArrayList<String>  headings = HDLmTiming.getTimingsHeadings(null);

		/* Create a new timings set */
		TreeSet<HDLmTiming>  timingsSet = HDLmTiming.getTimingsTree(null);
  	/* Build a (short) list of the timings status information */
    var  statusListContents = HDLmTiming.getTimingsStatus(timingsSet,
    		                                                  HDLmGetTimingStatus.HTMLINFORMATION);
    /* Construct an HTML table from the headings and the timings status list */
		tableHtml = HDLmHtml.buildHtmlTableFromListStyle(headings, statusListContents);
		rv.append(tableHtml);
		/* End the main div */
		rv.append("</div>");
	  return rv.toString();
	}
}
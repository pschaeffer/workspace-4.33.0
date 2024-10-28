package com.headlamp; 
import com.google.common.cache.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class for building a set of JavaScript. This is a generated program.
 * A Python program (BuildHDLmBuildJS.py) actually creates this Java 
 * program. This program should never be edited by hand in any way.
 *
 * @version 1.0
 * @author Peter
 */
 /* This is a purely static class and no instances of this class
    can ever be created */
class HDLmBuildJsNoCompression {
	/* The next statement initializes logging to some degree. Note that having the
     slf4j jars and the log4j jars in the classpath also plays some role in
     logging initialization.	 */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmBuildJsNoCompression.class);
	/* This class can never be instantiated */
	private HDLmBuildJsNoCompression() {}
  /* Build a set of JavaScript and return it to the caller */
  public static String getJsBuildJs(HDLmProtocolTypes protocol,
                                    String secureHostName, 
                                    String hostName,
                                    String divisionName,
                                    String siteName,
                                    ArrayList<HDLmMod> mods, 
                                    HDLmSession sessionObj,
                                    HDLmLogMatchingTypes logRuleMatching,
                                    String serverName) {
    if (protocol == null) {
		  String  errorText = "Protocol string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    /* This check is no longer in use. We now allow for a null secure host name 
       to be passed to this routine. This will happen if no proxy definition was
       found for the current host name. This is no longer considered to be an 
       error condition. */ 
    /*
		if (secureHostName == null) {
		  String  errorText = "Secure host name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    */
		if (hostName == null) {
		  String  errorText = "Host name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
		if (divisionName == null) {
		  String  errorText = "Division name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
		if (siteName == null) {
		  String  errorText = "Site name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
		if (mods == null) {
		  String  errorText = "Modifications array passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    if (sessionObj == null) {
		  String  errorText = "Session object passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    if (logRuleMatching == null) {
		  String  errorText = "Log rule matching reference passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    if (serverName == null) {
		  String  errorText = "Server name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    HDLmBuildLines  builder;
    String          actualJS;
    String          fixedJSName = null;
    boolean         useCreateFixedJS = false;
    /* This routine is passed some JSON for the current session.
       Get a session object from the session JSON and get some 
       values from the object. */      
    String        sessionIdJava = sessionObj.getSessionId();
    String        sessionIndexStr = sessionObj.getIndex();
    String        sessionParametersStr = sessionObj.getParameters();
    ArrayList<Double>   sessionParametersArray = HDLmMain.getParametersArray(sessionParametersStr);
    /* This code is clearly no longer in use. However, some related
       code is very much in use. */ 
    if (1 == 2) {
      actualJS = HDLmUtility.fileGetContents("HDLmBuildJsNoCompressionOld.txt");
      return actualJS;
    }
    /* Check if the 'fixed JS' flag is set or not. If the flag is
       set, check if the fixed JavaScript file already exists. If
       it does, just read it and return it to the caller. */
    if (useCreateFixedJS) {
    	fixedJSName = HDLmDefines.getString("HDLMFIXEDFILENAME");
    	boolean   fileExists = HDLmUtility.fileExists(fixedJSName);
    	if (fileExists) {
        actualJS = HDLmUtility.fileGetContents(fixedJSName);
        return actualJS;
    	}   	
    }
    /* If the modifications array is empty (a common case), then 
       just return an empty JavaScript program to the caller. The 
       modifications array will (typically) be empty if the current
       path value did not match any actual modifications. 
       
       This code is no longer in use. This code built an empty 
       JavaScript program if there were no modifications. This 
       prevented visit records from being generated. */
    if (mods.size() == 0 &&
    		mods.size() != 0)
      return "<script></script>";
    /* Build the JavaScript used to implement the modifications */
    builder = new HDLmBuildLines("JS");
    builder.addLine("<script>");
    builder.addLine("  \"use strict\";");
    builder.addLine("  let HDLmNodeIdenTracing = {");
    builder.addLine("    \"none\":  0,");
    builder.addLine("    \"off\":   1,");
    builder.addLine("    \"error\": 2,");
    builder.addLine("    \"all\":   3");
    builder.addLine("  };");
    builder.addLine("  /* Create a keyboard event listener for the keydown event. Check if the");
    builder.addLine("     keydown event was used to enter a Ctrl-B or a Ctrl-I. The Ctrl-B key");
    builder.addLine("     combination is used to enable rule matching trace. The Ctrl-I key");
    builder.addLine("     combination is used to enable node identifier tracing. */");
    builder.addLine("  document.addEventListener(\"keydown\", event => {");
    builder.addLine("    if (event.key == 'b' && event.ctrlKey == true)");
    builder.addLine("      sessionStorage.setItem(\"HDLmSessionDebugRulesEnabled\", 'true');");
    builder.addLine("    if (event.key == 'i' && event.ctrlKey == true)");
    builder.addLine("      sessionStorage.setItem(\"HDLmSessionDebugNodeIdenEnabled\", 'all');");
    builder.addLine("    if (event.key == 'm' && event.ctrlKey == true)");
    builder.addLine("      HDLmToggleStyleSheetEnablement();");
    builder.addLine("    if (event.key == 'q' && event.ctrlKey == true)");
    builder.addLine("      sessionStorage.setItem(\"HDLmSessionPostRuleTracingEnabled\", 'true');");
    builder.addLine("  });");
    builder.addLine("  /* Start the JavaScript function that applies just one modification */");
    builder.addLine("  function HDLmApplyMod(pathValueStr,");
    builder.addLine("                        curMod,");
    builder.addLine("                        sessionIdJS,");
    builder.addLine("                        sessionIndexValue,");
    builder.addLine("                        parametersArray,");
    builder.addLine("                        proxyDomain,");
    builder.addLine("                        hostNameValue,");
    builder.addLine("                        divisionNameValue,");
    builder.addLine("                        siteNameValue,");
    builder.addLine("                        proxySecureDomain,");
    builder.addLine("                        forceSelectStringValue,");
    builder.addLine("                        logRuleMatching,");
    builder.addLine("                        readyState) {");
    builder.addLine("    /* console.log('In HDLmApplyMod', sessionIndexValue, typeof(sessionIndexValue)); */");
    builder.addLine("    /* console.log(readyState); */");
    builder.addLine("    /* Save a few values passed by the caller in session storage. These");
    builder.addLine("       values may be used later to construct new rules or for other");
    builder.addLine("       purposes. */");
    builder.addLine("    sessionStorage.setItem(\"HDLmSessionRuleInfoHostName\", hostNameValue);");
    builder.addLine("    sessionStorage.setItem(\"HDLmSessionRuleInfoDivisionName\", divisionNameValue);");
    builder.addLine("    sessionStorage.setItem(\"HDLmSessionRuleInfoSiteName\", siteNameValue);");
    builder.addLine("    /* Check if rule matching trace has been enabled from the keyboard.");
    builder.addLine("       If it has, force the rule matching trace variable to the needed");
    builder.addLine("       value. */");
    builder.addLine("    if (sessionStorage.getItem('HDLmSessionDebugRulesEnabled') == 'true')");
    builder.addLine("      logRuleMatching = true;");
    builder.addLine("    /* Check if node identifier tracing has been enabled from the keyboard.");
    builder.addLine("       If it has, force the node identifier tracing variable to the needed");
    builder.addLine("       value. */");
    builder.addLine("    let nodeIdenTracing = HDLmNodeIdenTracing.off;");
    builder.addLine("    if (sessionStorage.getItem('HDLmSessionDebugNodeIdenEnabled') == 'all')");
    builder.addLine("      nodeIdenTracing = HDLmNodeIdenTracing.all;");
    builder.addLine("    /* Check if post rule tracing has been enabled from the keyboard.");
    builder.addLine("       If it has, force the post rule tracing variable to the needed");
    builder.addLine("       value. */");
    builder.addLine("    let postRuleTracing = false;");
    builder.addLine("    if (sessionStorage.getItem('HDLmSessionPostRuleTracingEnabled') == 'true')");
    builder.addLine("      postRuleTracing = true;");
    builder.addLine("    let matchFound = false;");
    builder.addLine("    let matchError = '';");
    builder.addLine("    /* console.log('s1'); */");
    builder.addLine("    /* Build the complete rule name and replace a few");
    builder.addLine("       characters in the rule name. The replacement is");
    builder.addLine("       done so that the modified rule name can be used");
    builder.addLine("       as an HTML attribute. */");
    builder.addLine("    let matchModifiedName = hostNameValue + '/' + divisionNameValue + '/' + siteNameValue + '/' + curMod.name");
    builder.addLine("    matchModifiedName = HDLmReplaceInString(matchModifiedName);");
    builder.addLine("    let curType = curMod.type;");
    builder.addLine("    /* The dummy loop below is used to allow break to work */");
    builder.addLine("    while (true) {");
    builder.addLine("      let postTrace = new Object();");
    builder.addLine("      /* console.log('s2'); */");
    builder.addLine("      /* If the current modification is not enabled, then we really");
    builder.addLine("         don't have any more work to do */");
    builder.addLine("      if (curMod.enabled != true) {");
    builder.addLine("        matchError = 'disabled';");
    builder.addLine("        /* console.log('s3'); */");
    builder.addLine("        /* Report that the current rule was not enabled */");
    builder.addLine("        if (postRuleTracing == true) {");
    builder.addLine("          let localUpdates = new Object();");
    builder.addLine("          HDLmSaveChange(localUpdates, null,");
    builder.addLine("                         sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                         null, null,");
    builder.addLine("                         hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                         curMod.path, curMod.type, pathValueStr, null, null);");
    builder.addLine("          postTrace.matcherror = matchError;");
    builder.addLine("          HDLmSendUpdates(localUpdates, 'failure', '1.0', postTrace);");
    builder.addLine("        }");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      let matchRes;");
    builder.addLine("      let matchRe;");
    builder.addLine("      if (curMod.pathre === true) {");
    builder.addLine("        let curModLen = curMod.path.length;");
    builder.addLine("        matchRe = new RegExp(curMod.path.substr(2, curModLen-3));");
    builder.addLine("        matchRes = matchRe.test(pathValueStr);");
    builder.addLine("        if (postRuleTracing == true) {");
    builder.addLine("          postTrace.matchpathre = curMod.pathre;");
    builder.addLine("          postTrace.matchpath = curMod.path;");
    builder.addLine("          postTrace.matchpathvalue = pathValueStr;");
    builder.addLine("          postTrace.matchmatch = matchRes;");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      else {");
    builder.addLine("        matchRes = (curMod.path === pathValueStr);");
    builder.addLine("        if (postRuleTracing == true) {");
    builder.addLine("          postTrace.matchpathre = curMod.pathre;");
    builder.addLine("          postTrace.matchpath = curMod.path;");
    builder.addLine("          postTrace.matchpathvalue = pathValueStr;");
    builder.addLine("          postTrace.matchmatch = matchRes;");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      /* console.log('s4'); */");
    builder.addLine("      /* console.log(curMod.pathre, matchRe); */");
    builder.addLine("      /* Check if we were able to match the current path value in some");
    builder.addLine("         way. Store an error string and break if the path value did not");
    builder.addLine("         match. */");
    builder.addLine("      /* console.log('s5'); */");
    builder.addLine("      if (matchRes == false) {");
    builder.addLine("        matchError = 'Path value mismatch';");
    builder.addLine("        /* Report that the path value did not match */");
    builder.addLine("        if (postRuleTracing == true) {");
    builder.addLine("          let localUpdates = new Object();");
    builder.addLine("          HDLmSaveChange(localUpdates, null,");
    builder.addLine("                         sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                         null, null,");
    builder.addLine("                         hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                         curMod.path, curMod.type, pathValueStr, null, null);");
    builder.addLine("          postTrace.matcherror = matchError;");
    builder.addLine("          HDLmSendUpdates(localUpdates, 'failure', '1.0', postTrace);");
    builder.addLine("        }");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      /* The current rule did match the current path value. We need to report");
    builder.addLine("         this fact for debugging purposes. Note the special error number");
    builder.addLine("         used below. This value is reserved for reporting events that are");
    builder.addLine("         not really errors. */");
    builder.addLine("      else {");
    builder.addLine("        if (logRuleMatching == true) {");
    builder.addLine("          let errorText = HDLmBuildErrorRule(curMod, 'match', pathValueStr);");
    builder.addLine("          HDLmBuildError('Trace', 'Mod', 35, errorText);");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      const fontNames = {");
    builder.addLine("                          'fontcolor':'color',");
    builder.addLine("                          'fontfamily':'font-family',");
    builder.addLine("                          'fontkerning':'font-kerning',");
    builder.addLine("                          'fontsize':'font-size',");
    builder.addLine("                          'fontstyle':'font-style',");
    builder.addLine("                          'fontweight':'font-weight'");
    builder.addLine("                        }");
    builder.addLine("      /* console.log('s6'); */");
    builder.addLine("      /* Check if this rule name is known to have lookup");
    builder.addLine("         value. Use the lookup value if possible. */");
    builder.addLine("      let parameterNumber = -1;");
    builder.addLine("      /* Look up index is always an index into an array of possible");
    builder.addLine("         values for a modification. This index is zero based as one");
    builder.addLine("         might expect. */");
    builder.addLine("      let finalLookupIndex = 0;");
    builder.addLine("      /* Look up value is always a value between zero and one. This");
    builder.addLine("         value might come from the parameter array or it might be a");
    builder.addLine("         copy of the index value. An out-of-range default value is");
    builder.addLine("         set below. */");
    builder.addLine("      let lookupValue = -1.0;");
    builder.addLine("      let sessionIndexValueUsed = false;");
    builder.addLine("      let tempLookupIndex = HDLmGetLookupIndex(curMod.name);");
    builder.addLine("      /* console.log(curMod.name, tempLookupIndex, typeof(tempLookupIndex)); */");
    builder.addLine("      if (typeof(tempLookupIndex) != 'undefined' &&");
    builder.addLine("          tempLookupIndex != null) {");
    builder.addLine("        lookupValue = sessionIndexValue;");
    builder.addLine("        /* console.log(lookupValue); */");
    builder.addLine("        sessionIndexValueUsed = true;");
    builder.addLine("        finalLookupIndex = tempLookupIndex;");
    builder.addLine("      }");
    builder.addLine("      /* If possible, get the lookup value from the parameter");
    builder.addLine("         array. Note that a default lookup value is always set.");
    builder.addLine("         The parameter number can only be used to determine the");
    builder.addLine("         lookup value, if the parameter number is set and valid. */");
    builder.addLine("      else {");
    builder.addLine("        parameterNumber = curMod.parameter;");
    builder.addLine("        if (parameterNumber != null &&");
    builder.addLine("            parameterNumber >= 0 &&");
    builder.addLine("            parameterNumber < parametersArray.length)");
    builder.addLine("          lookupValue = parametersArray[parameterNumber];");
    builder.addLine("        /* console.log('s7'); */");
    builder.addLine("        /* console.log(parameterNumber, lookupValue); */");
    builder.addLine("      }");
    builder.addLine("      /* console.log(finalLookupIndex); */");
    builder.addLine("      /* Get the node list for the current modification and the length");
    builder.addLine("         of the node list. Check if the length of the node list is zero.");
    builder.addLine("         Set the match error string if no matching nodes were found. */");
    builder.addLine("      let nodeList = HDLmFind(curMod, nodeIdenTracing, postRuleTracing, postTrace);");
    builder.addLine("      let nodeListLength = nodeList.length;");
    builder.addLine("      if (nodeListLength == 0 && curType != 'visit') {");
    builder.addLine("        matchError = 'nonodes';");
    builder.addLine("        /* Report that no nodes were found */");
    builder.addLine("        if (postRuleTracing == true) {");
    builder.addLine("          let   localUpdates = new Object();");
    builder.addLine("          HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                         sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                         null, null,");
    builder.addLine("                         hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                         curMod.path, curMod.type, pathValueStr, null, null);");
    builder.addLine("          postTrace.matcherror = matchError;");
    builder.addLine("          HDLmSendUpdates(localUpdates, 'failure', '1.0', postTrace);");
    builder.addLine("        }");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      /* console.log('s8'); */");
    builder.addLine("      /* console.log(curType, nodeList); */");
    builder.addLine("      /* Handle each type of modification */");
    builder.addLine("      /* console.log(finalLookupIndex); */");
    builder.addLine("      switch (curType) {");
    builder.addLine("        case 'attribute': {");
    builder.addLine("          /* Get the extra value and break it into two substrings. The first");
    builder.addLine("             substring is the attribute to be modified. The second substring");
    builder.addLine("             describes the type of modification. */");
    builder.addLine("          let curModExtra = curMod.extra;");
    builder.addLine("          let curModExtraArray = curModExtra.split('/');");
    builder.addLine("          let attributeName = curModExtraArray[0];");
    builder.addLine("          let attributeRequest = curModExtraArray[1]");
    builder.addLine("          /* Process all of the matching nodes */");
    builder.addLine("          for (let i = 0; i < nodeListLength; i++) {");
    builder.addLine("            /* Get a single node and add a class or classes to the node */");
    builder.addLine("            let curNode = nodeList[i];");
    builder.addLine("            HDLmClassAddSpecialClass(curNode, curType, curModExtra);");
    builder.addLine("            matchFound = true;");
    builder.addLine("            /* console.log('Increment curType is', curType); */");
    builder.addLine("            if (HDLmIncrementUpdateCount(curNode, matchModifiedName, readyState) > 0)");
    builder.addLine("              break;");
    builder.addLine("            /* console.log('Increment curType is', curType); */");
    builder.addLine("            HDLmIncrementUpdateCount(curNode, matchModifiedName);");
    builder.addLine("            if (attributeRequest.toUpperCase() == 'USEPROXYHOST') {");
    builder.addLine("              let attributeValue = curNode.getAttribute(attributeName);");
    builder.addLine("              let oldText = attributeValue;");
    builder.addLine("              let nodeURL = new URL(attributeValue);");
    builder.addLine("              nodeURL.host = proxyDomain;");
    builder.addLine("              let newText = nodeURL.href;");
    builder.addLine("              curNode.setAttribute(attributeName, nodeURL.href);");
    builder.addLine("              /* Treat the current attribute change as an update */");
    builder.addLine("              let localUpdates = new Object();");
    builder.addLine("              HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                             sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                             null, null,");
    builder.addLine("                             hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                             curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                             oldText, newText);");
    builder.addLine("              postTrace.matcherror = 'attribute';");
    builder.addLine("              HDLmSendUpdates(localUpdates, 'href', '1.0', postTrace);");
    builder.addLine("            }");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        /* The extract modification type is somewhat unusual in that it");
    builder.addLine("           takes more or less immediate effect. Note that extract does");
    builder.addLine("           not actually change anything on/in an HTML page. However, the");
    builder.addLine("           extracted data is sent immediately back to the server as a");
    builder.addLine("           change. Of course, no actual change is ever made by an extract.");
    builder.addLine("           Note that extracts do not have a real parameter number or lookup");
    builder.addLine("           value. Null is used for these values below. */");
    builder.addLine("        case 'extract': {");
    builder.addLine("          /* Process all of the matching nodes */");
    builder.addLine("          for (let i = 0; i < nodeListLength; i++) {");
    builder.addLine("            /* Get a single node and add a class or classes to the node */");
    builder.addLine("            let curNode = nodeList[i];");
    builder.addLine("            let curModExtra = curMod.extra;");
    builder.addLine("            HDLmClassAddSpecialClass(curNode, curType, curModExtra);");
    builder.addLine("            matchFound = true;");
    builder.addLine("            /* Check if the special extract related event has already been");
    builder.addLine("               executed */");
    builder.addLine("            if (HDLmGetUpdateCount(curNode, matchModifiedName, readyState) > 0)");
    builder.addLine("              break;");
    builder.addLine("            /* console.log('Increment curType is', curType); */");
    builder.addLine("            HDLmIncrementUpdateCount(curNode, matchModifiedName);");
    builder.addLine("            /* Extract the text value of the current node and save it as a change.");
    builder.addLine("               Of course, it is not really a change at all. However, the change");
    builder.addLine("               mechanism is used to keep the value and send it to the server. */");
    builder.addLine("            /* Get the saved extract value. We can't obtain the extract");
    builder.addLine("               value at this point, because the value may have been changed");
    builder.addLine("               by one or more rules. Hence the use of the saved value. */");
    builder.addLine("            let oldText;");
    builder.addLine("            if (HDLmSavedExtracts.hasOwnProperty(curMod.name))");
    builder.addLine("              oldText = HDLmSavedExtracts[curMod.name];");
    builder.addLine("            else");
    builder.addLine("              oldText = null;");
    builder.addLine("            let localUpdates = new Object();");
    builder.addLine("            /* Save the extracted value as a simulated change */");
    builder.addLine("            HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                           sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                           null, null,");
    builder.addLine("                           hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                           curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                           oldText, null);");
    builder.addLine("            /* Get the reason value. Check a number of sources */");
    builder.addLine("            let localReason = 'extract';");
    builder.addLine("            if ((typeof curModExtra) != 'undefined' &&");
    builder.addLine("                curModExtra          != null        &&");
    builder.addLine("                curModExtra          != '')");
    builder.addLine("              localReason = curModExtra;");
    builder.addLine("            postTrace.matcherror = 'extract';");
    builder.addLine("            HDLmSendUpdates(localUpdates, localReason, '1.0', postTrace);");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        case 'modify': {");
    builder.addLine("          /* Check if secure host name is set or not, this code use");
    builder.addLine("             the secure host name and the secure host name must be");
    builder.addLine("             set in this case. */");
    builder.addLine("          if (proxySecureDomain == null) {");
    builder.addLine("            let   errorText = `No secure host name for (${hostNameValue})`;");
    builder.addLine("            HDLmBuildError('Error', 'Mod', 16, errorText);");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          /* Process all of the matching nodes */");
    builder.addLine("          for (let i = 0; i < nodeListLength; i++) {");
    builder.addLine("            /* Get a single node and add a class or classes to the node */");
    builder.addLine("            let curNode = nodeList[i];");
    builder.addLine("            let curModExtra = curMod.extra;");
    builder.addLine("            HDLmClassAddSpecialClass(curNode, curType, curModExtra);");
    builder.addLine("            if (curModExtra.toUpperCase() !== 'FIXIFRAMESRC')");
    builder.addLine("              break;");
    builder.addLine("            matchFound = true;");
    builder.addLine("            if (HDLmGetUpdateCount(curNode, matchModifiedName, readyState) > 0)");
    builder.addLine("              break;");
    builder.addLine("            /* console.log('Increment curType is', curType); */");
    builder.addLine("            HDLmIncrementUpdateCount(curNode, matchModifiedName);");
    builder.addLine("            let nodeSrc = curNode.getAttribute('src');");
    builder.addLine("            let oldText = nodeSrc;");
    builder.addLine("            let nodeURL = new URL(nodeSrc);");
    builder.addLine("            /* Note that we change the URL to point to the secure version");
    builder.addLine("               of the proxy server. We also add the session ID to the path");
    builder.addLine("               value so that the session ID can be extracted later. */");
    builder.addLine("            nodeURL.host = proxySecureDomain;");
    builder.addLine("            let newText = nodeURL.href + '&HDLmSessionId=' + sessionIdJS;");
    builder.addLine("            curNode.setAttribute('src', newText);");
    builder.addLine("            /* Create a local updates object. The current change is stored");
    builder.addLine("               in the local updates object. */");
    builder.addLine("            let localUpdates = new Object();");
    builder.addLine("            /* Save the modified value as a simulated change */");
    builder.addLine("            HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                           sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                           null, null,");
    builder.addLine("                           hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                           curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                           null, null);");
    builder.addLine("            /* Get the reason value. Check a number of sources */");
    builder.addLine("            let localReason = 'modify';");
    builder.addLine("            if ((typeof curModExtra) != 'undefined' &&");
    builder.addLine("                curModExtra          != null        &&");
    builder.addLine("                curModExtra          != '')");
    builder.addLine("              localReason = curModExtra;");
    builder.addLine("            postTrace.matcherror = 'modify';");
    builder.addLine("            HDLmSendUpdates(localUpdates, localReason, '1.0', postTrace);");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        /* The notify modification type is somewhat unusual in that it");
    builder.addLine("           takes effect when a button is clicked. This modification type");
    builder.addLine("           is implemented by adding a 'click' event listener to the button");
    builder.addLine("           specified by the modification. When the button is clicked, zero");
    builder.addLine("           or more extracts are attempted. Note that the extracts are not");
    builder.addLine("           done when this modification is executed. They are done when the");
    builder.addLine("           button is clicked. This requires a JavaScript closure as implemented");
    builder.addLine("           below. */");
    builder.addLine("        case 'notify': {");
    builder.addLine("          /* Check if a node exists that has an update count of zero.");
    builder.addLine("             If the answer is yes, then we will end up, attaching an");
    builder.addLine("             event listener to the node. If the answer is no, then we");
    builder.addLine("             will not end up attaching an event listener to the node.");
    builder.addLine("             We really only want to send an update, if we are actually");
    builder.addLine("             going to attach an event listener. */");
    builder.addLine("          let sendUpdates = false;");
    builder.addLine("          for (let i = 0; i < nodeListLength; i++) {");
    builder.addLine("            let nodeSend = nodeList[i];");
    builder.addLine("            if (HDLmGetUpdateCount(nodeSend, matchModifiedName, readyState) == 0) {");
    builder.addLine("              sendUpdates = true;");
    builder.addLine("              break;");
    builder.addLine("            }");
    builder.addLine("          }");
    builder.addLine("          /* Check if the send updates flag is set. If the flag is set,");
    builder.addLine("             then we can and should build an update and send it. If the");
    builder.addLine("             flag is not set, then we can skip sending the update to the");
    builder.addLine("             server. */");
    builder.addLine("          let curModExtra = curMod.extra;");
    builder.addLine("          if (sendUpdates) {");
    builder.addLine("            /* Report that a node was found for notify */");
    builder.addLine("            let localUpdates = new Object();");
    builder.addLine("            HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                           sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                           null, null,");
    builder.addLine("                           hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                           curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                           null, null);");
    builder.addLine("            let localReason = 'notify';");
    builder.addLine("            if ((typeof curModExtra) != 'undefined' &&");
    builder.addLine("                curModExtra          != null        &&");
    builder.addLine("                curModExtra          != '')");
    builder.addLine("              localReason = curModExtra;");
    builder.addLine("            postTrace.matcherror = 'notify';");
    builder.addLine("            HDLmSendUpdates(localUpdates, localReason, '1.0', postTrace);");
    builder.addLine("          }");
    builder.addLine("          /* Process all of the matching nodes */");
    builder.addLine("          for (let i = 0; i < nodeListLength; i++) {");
    builder.addLine("            /* Get a single node and add a class or classes to the node */");
    builder.addLine("            let curNode = nodeList[i];");
    builder.addLine("            HDLmClassAddSpecialClass(curNode, curType, curModExtra);");
    builder.addLine("            matchFound = true;");
    builder.addLine("            /* Check if the special notify related event listener has");
    builder.addLine("               already been added */");
    builder.addLine("            if (HDLmGetUpdateCount(curNode, matchModifiedName, readyState) > 0)");
    builder.addLine("              break;");
    builder.addLine("            /* console.log('Increment curType is', curType); */");
    builder.addLine("            HDLmIncrementUpdateCount(curNode, matchModifiedName);");
    builder.addLine("            /* The code below uses JavaScript closures. The outer function");
    builder.addLine("               is run and returns the inner function as the return value of");
    builder.addLine("               the output function. The inner function is run when the notify");
    builder.addLine("               event occurs. */");
    builder.addLine("            curNode.addEventListener('click', (function() {");
    builder.addLine("              return function() {");
    builder.addLine("                let localUpdates = new Object();");
    builder.addLine("                /* If we don't have any values to extract, create");
    builder.addLine("                   a dummy simulated update. This step is required");
    builder.addLine("                   so that we will know that the event listener");
    builder.addLine("                   ran after the user clicked on something. */");
    builder.addLine("                if (curMod.valuesCount <= 0) {");
    builder.addLine("                  HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                                 sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                                 null, null,");
    builder.addLine("                                 hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                                 curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                                 null, null);");
    builder.addLine("                }");
    builder.addLine("                /* Extract each of the values and save each of them as");
    builder.addLine("                   a simulated update */");
    builder.addLine("                for (let j = 0; j < curMod.valuesCount; j++) {");
    builder.addLine("                  let searchText = curMod.values[j];");
    builder.addLine("                  searchText = HDLmModifySearch(searchText);");
    builder.addLine("                  /* Get the saved extract value. We can't obtain the extract");
    builder.addLine("                     value at this point, because the value may have been changed");
    builder.addLine("                     by one or more rules. Hence the use of the saved value. */");
    builder.addLine("                  let searchValue;");
    builder.addLine("                  if (HDLmSavedNotifies.hasOwnProperty(searchText))");
    builder.addLine("                    searchValue = HDLmSavedNotifies[searchText];");
    builder.addLine("                  else");
    builder.addLine("                    searchValue = null;");
    builder.addLine("                  HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                                 sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                                 null, null,");
    builder.addLine("                                 hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                                 curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                                 searchValue, null);");
    builder.addLine("                }");
    builder.addLine("                /* Set the local reason value to something appropriate */");
    builder.addLine("                let localReason = 'notify';");
    builder.addLine("                let curModExtra = curMod.extra;");
    builder.addLine("                if ((typeof curModExtra) != 'undefined' &&");
    builder.addLine("                    curModExtra          != null        &&");
    builder.addLine("                    curModExtra          != '')");
    builder.addLine("                  localReason = curModExtra;");
    builder.addLine("                postTrace.matcherror = 'click';");
    builder.addLine("                HDLmSendUpdates(localUpdates, localReason, '1.0', postTrace);");
    builder.addLine("              }");
    builder.addLine("            })());");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        /* Handle a 'visit' rule type. We need to send an update to the server");
    builder.addLine("           with some information for the execution of a 'visit' rule. */");
    builder.addLine("        case 'visit': {");
    builder.addLine("          /* Report that a 'visit' rule has been executed. Of course, a");
    builder.addLine("             'visit' event also happens when the JavaScript program is");
    builder.addLine("             loaded. */");
    builder.addLine("          let testFlag = false;");
    builder.addLine("          HDLmHandleVisitRequest(curMod.extra, postTrace, testFlag, sessionIndexValueUsed,");
    builder.addLine("                                 sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                                 parameterNumber, lookupValue,");
    builder.addLine("                                 hostNameValue, divisionNameValue, siteNameValue, curMod,");
    builder.addLine("                                 pathValueStr);");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        case 'changeattrs':");
    builder.addLine("        case 'changenodes':");
    builder.addLine("        case 'fontcolor':");
    builder.addLine("        case 'fontfamily':");
    builder.addLine("        case 'fontkerning':");
    builder.addLine("        case 'fontsize':");
    builder.addLine("        case 'fontstyle':");
    builder.addLine("        case 'fontweight':");
    builder.addLine("        case 'height':");
    builder.addLine("        case 'image':");
    builder.addLine("        case 'order':");
    builder.addLine("        case 'remove':");
    builder.addLine("        case 'replace':");
    builder.addLine("        case 'script':");
    builder.addLine("        case 'style':");
    builder.addLine("        case 'text':");
    builder.addLine("        case 'textchecked':");
    builder.addLine("        case 'title':");
    builder.addLine("        case 'width': {");
    builder.addLine("          /* console.log(finalLookupIndex); */");
    builder.addLine("          let newTexts = curMod.values;");
    builder.addLine("          let newCount = curMod.valuesCount;");
    builder.addLine("          /* This code is used to get values from the new text array");
    builder.addLine("             using parameters, not the index value. Note that this");
    builder.addLine("             code is only executed if the sessionIndexValueUsed");
    builder.addLine("             variable is not true. */");
    builder.addLine("          if (lookupValue != null && sessionIndexValueUsed == false) {");
    builder.addLine("            finalLookupIndex = Math.floor(newCount * lookupValue);");
    builder.addLine("            finalLookupIndex = Math.min(finalLookupIndex, newCount - 1);");
    builder.addLine("          }");
    builder.addLine("          /* Get the new text value based on the lookup index.");
    builder.addLine("             Check if one of the new text values has a special prefix.");
    builder.addLine("             If this is true, then we must use the text entry with the");
    builder.addLine("             special prefix. Note that the special prefix is removed");
    builder.addLine("             from the text entry before it is used. */");
    builder.addLine("          let forceSelectFound = false;");
    builder.addLine("          let newText;");
    builder.addLine("          if (lookupValue != null) {");
    builder.addLine("            if (finalLookupIndex >= 0)");
    builder.addLine("              newText = newTexts[finalLookupIndex];");
    builder.addLine("          }");
    builder.addLine("          /* This is the code that implements 'force select' (without");
    builder.addLine("             the quotes). The idea is that one choice can be 'forced'");
    builder.addLine("             and made the current choice irrespecitive of all other");
    builder.addLine("             calculations. */");
    builder.addLine("          for (let i = 0; i < newCount; i++) {");
    builder.addLine("            if (newTexts[i].startsWith(forceSelectStringValue)) {");
    builder.addLine("              newText = newTexts[i].substring(forceSelectStringValue.length);");
    builder.addLine("              finalLookupIndex = i;");
    builder.addLine("              forceSelectFound = true;");
    builder.addLine("              break;");
    builder.addLine("            }");
    builder.addLine("          }");
    builder.addLine("          /* Check if the lookup value is really set. If the lookup");
    builder.addLine("             value is not really set, then we have no more work to do.");
    builder.addLine("             It turns out that we will ignore this error if a force");
    builder.addLine("             select value is used. */");
    builder.addLine("          if (lookupValue == null &&");
    builder.addLine("              forceSelectFound == false) {");
    builder.addLine("            matchError = 'Null lookup value';");
    builder.addLine("            /* Report that the lookup value was not set */");
    builder.addLine("            if (postRuleTracing == true) {");
    builder.addLine("              let localUpdates = new Object();");
    builder.addLine("              HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                             sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                             null, null,");
    builder.addLine("                             hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                             curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                             null, null);");
    builder.addLine("              postTrace.matcherror = matchError;");
    builder.addLine("              HDLmSendUpdates(localUpdates, 'failure', '1.0', postTrace);");
    builder.addLine("            }");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          /* Process all of the matching nodes */");
    builder.addLine("          for (let i = 0; i < nodeListLength; i++) {");
    builder.addLine("            /* Get a single node and add a class or classes to the node */");
    builder.addLine("            let curNode = nodeList[i];");
    builder.addLine("            let curModExtra = curMod.extra;");
    builder.addLine("            HDLmClassAddSpecialClass(curNode, curType, curModExtra);");
    builder.addLine("            let oldText;");
    builder.addLine("            /* Get the old text in the correct way for each type. The");
    builder.addLine("               procedure for getting the old text varies considerably");
    builder.addLine("               for each type of modification. */");
    builder.addLine("            if (curType == 'changeattrs') {");
    builder.addLine("              oldText = HDLmGetAttributesString(curNode);");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'changenodes') {");
    builder.addLine("              oldText = curNode.outerHTML;");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'fontcolor'   ||");
    builder.addLine("                     curType == 'fontfamily'  ||");
    builder.addLine("                     curType == 'fontkerning' ||");
    builder.addLine("                     curType == 'fontsize'    ||");
    builder.addLine("                     curType == 'fontstyle'   ||");
    builder.addLine("                     curType == 'fontweight') {");
    builder.addLine("              let newName = fontNames[curType];");
    builder.addLine("              oldText = '';");
    builder.addLine("              if (curNode.style.hasOwnProperty(newName))");
    builder.addLine("                oldText = curNode.style.getPropertyValue(newName);");
    builder.addLine("              else if (curNode.hasAttribute(newName))");
    builder.addLine("                oldText = curNode.getAttribute(newName);");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'height' ||");
    builder.addLine("                     curType == 'width') {");
    builder.addLine("              oldText = '';");
    builder.addLine("              if (curNode.hasAttribute(curType))");
    builder.addLine("                oldText = curNode.getAttribute(curType);");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'image') {");
    builder.addLine("              oldText = '';");
    builder.addLine("              if (curNode.hasAttribute('src')) {");
    builder.addLine("                oldText = curNode.getAttribute('src');");
    builder.addLine("                if (oldText.startsWith('http'))");
    builder.addLine("                  oldText = HDLmRemoveProtocol(oldText);");
    builder.addLine("              }");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'order') {");
    builder.addLine("              oldText = '';");
    builder.addLine("              /* oldText = curNode.textContent; */");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'remove') {");
    builder.addLine("              oldText = '';");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'replace') {");
    builder.addLine("              oldText = '';");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'script') {");
    builder.addLine("              oldText = '';");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'style') {");
    builder.addLine("              oldText = '';");
    builder.addLine("              /* Try to split the extra value into several different");
    builder.addLine("                 strings. This may not yield multiple values, in which");
    builder.addLine("                 case we have just one string to deal with. */");
    builder.addLine("              let curModSplit = HDLmStyleSplitString(curModExtra);");
    builder.addLine("              let curModSplitLength = curModSplit.length;");
    builder.addLine("              /* The extra value has one or more different strings (each");
    builder.addLine("                 of which is a style). Handle this case. */");
    builder.addLine("              for (let i = 0; i < curModSplitLength; i++) {");
    builder.addLine("                let curStyle = curModSplit[i];");
    builder.addLine("                let curValue = '';");
    builder.addLine("                if (curNode.hasAttribute('style')) {");
    builder.addLine("                  if (curNode.style.hasOwnProperty(curStyle))");
    builder.addLine("                    curValue = curNode.style.getPropertyValue(curStyle);");
    builder.addLine("                }");
    builder.addLine("                if (curValue == '' &&");
    builder.addLine("                    curNode.hasAttribute(curStyle)) {");
    builder.addLine("                  curValue = curNode.getAttribute(curStyle);");
    builder.addLine("                }");
    builder.addLine("                if (curValue != '') {");
    builder.addLine("                  if (oldText != '')");
    builder.addLine("                    oldText += ' '");
    builder.addLine("                  oldText += curValue;");
    builder.addLine("                }");
    builder.addLine("              }");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'text'        ||");
    builder.addLine("                     curType == 'textchecked' ||");
    builder.addLine("                     curType == 'title') {");
    builder.addLine("              oldText = curNode.textContent;");
    builder.addLine("            }");
    builder.addLine("            /* Check if the current node should be skipped */");
    builder.addLine("            if (curType == 'textchecked') {");
    builder.addLine("              /* Check if the acutal text matches what we are looking for */");
    builder.addLine("              let textMatch = HDLmCheckTextMatches(oldText, curModExtra,");
    builder.addLine("                                                   matchError, postTrace, postRuleTracing,");
    builder.addLine("                                                   parametersArray, sessionIdJS,");
    builder.addLine("                                                   parameterNumber, lookupValue,");
    builder.addLine("                                                   hostNameValue, divisionNameValue, siteNameValue, curMod,");
    builder.addLine("                                                   pathValueStr);");
    builder.addLine("              /* Check if the text match worked */");
    builder.addLine("              if (!textMatch) {");
    builder.addLine("                matchError = 'textunequal';");
    builder.addLine("                continue;");
    builder.addLine("              }");
    builder.addLine("            }");
    builder.addLine("            matchFound = true;");
    builder.addLine("            /* Generally we can just quit if the updates attribute is already");
    builder.addLine("               set. Generally, we don't want to make the same changes more than");
    builder.addLine("               once. However, testing has shown that this may not be true for");
    builder.addLine("               images and some other types. Strangely, the src attribute seems");
    builder.addLine("               to change back to its original value in some cases. */");
    builder.addLine("            /* console.log(readyState, curType); */");
    builder.addLine("            let matchUpdateCount = HDLmGetUpdateCount(curNode, matchModifiedName, readyState);");
    builder.addLine("            /* console.log(readyState, curType, matchUpdateCount); */");
    builder.addLine("            /* In one very important case, we want to ignore (not quite, the");
    builder.addLine("               update count is one of the parameters below) the update count");
    builder.addLine("               here. One rule type is used for many different things. How the");
    builder.addLine("               update count is handled is different for different cases. */");
    builder.addLine("            if (curType == 'changenodes') {");
    builder.addLine("              /* The check below is really just a placeholder. The match update");
    builder.addLine("                 count will almost certainly never get that high. */");
    builder.addLine("              if (matchUpdateCount > 255)");
    builder.addLine("                break;");
    builder.addLine("              /* Run the change node code in test mode. This invocation");
    builder.addLine("                 doesn't change anything, but does return a flag showing");
    builder.addLine("                 if break is needed. */");
    builder.addLine("              let testFlag = true;");
    builder.addLine("              let forceBreak = HDLmChangeNodes(curNode, newText, matchUpdateCount, testFlag, sessionIndexValueUsed,");
    builder.addLine("                                               matchError, postTrace, postRuleTracing,");
    builder.addLine("                                               parametersArray, sessionIdJS,");
    builder.addLine("                                               sessionIndexValue, parameterNumber, lookupValue,");
    builder.addLine("                                               hostNameValue, divisionNameValue, siteNameValue,");
    builder.addLine("                                               curMod, pathValueStr, oldText);");
    builder.addLine("              if (forceBreak == true)");
    builder.addLine("                break;");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'image') {");
    builder.addLine("              if (matchUpdateCount > 2)");
    builder.addLine("                if (oldText == newText ||");
    builder.addLine("                    oldText.startsWith('data:'))");
    builder.addLine("                  break;");
    builder.addLine("            }");
    builder.addLine("            /* The above algorithm is great in most cases. However, it");
    builder.addLine("               won't really work for remove or replace processing. For");
    builder.addLine("               remove or replace rules, we actually remove and/or replace");
    builder.addLine("               the element where the updated attribute might be set. As a");
    builder.addLine("               consequence, we need to check and set the attribute in the");
    builder.addLine("               parent of the current node. */");
    builder.addLine("            else if (curType == 'remove' ||");
    builder.addLine("                curType == 'replace') {");
    builder.addLine("              let parentNode = curNode.parentNode;");
    builder.addLine("              if (parentNode != null) {");
    builder.addLine("                if (HDLmGetUpdateCount(parentNode, matchModifiedName, readyState) > 0)");
    builder.addLine("                  break;");
    builder.addLine("              }");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'textchecked') {");
    builder.addLine("              if (matchUpdateCount > 1)");
    builder.addLine("                break;");
    builder.addLine("            }");
    builder.addLine("            /* If the update count is greater than zero (an update has already");
    builder.addLine("               been done) we want to exit at this point. The critical exception");
    builder.addLine("               is for images and a few other rule types. For some reason, this");
    builder.addLine("               basic idea does not work for images and a few other rule types. */");
    builder.addLine("            else {");
    builder.addLine("              if (matchUpdateCount > 0)");
    builder.addLine("                break;");
    builder.addLine("            }");
    builder.addLine("            if (curType == 'remove' ||");
    builder.addLine("                curType == 'replace') {");
    builder.addLine("              let parentNode = curNode.parentNode;");
    builder.addLine("              /* console.log('Increment curType is', curType); */");
    builder.addLine("              HDLmIncrementUpdateCount(parentNode, matchModifiedName);");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'script') {");
    builder.addLine("              /* console.log('CurType is script', readyState, curType); */");
    builder.addLine("              if (readyState == 'complete')");
    builder.addLine("                HDLmIncrementUpdateCount(curNode, matchModifiedName);");
    builder.addLine("            }");
    builder.addLine("            else {");
    builder.addLine("              /* console.log('Increment curType is', curType); */");
    builder.addLine("              HDLmIncrementUpdateCount(curNode, matchModifiedName);");
    builder.addLine("            }");
    builder.addLine("            /* Use the new text in the correct way for each type. The");
    builder.addLine("               procedure for using the new text varies considerably");
    builder.addLine("               for each type of modification. */");
    builder.addLine("            if (curType == 'changeattrs') {");
    builder.addLine("              if (newText.trim() != '')");
    builder.addLine("                HDLmChangeAttributes(curNode, newText);");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'changenodes') {");
    builder.addLine("              let testFlag = false;");
    builder.addLine("              HDLmChangeNodes(curNode, newText, matchUpdateCount, testFlag, sessionIndexValueUsed,");
    builder.addLine("                              matchError, postTrace, postRuleTracing,");
    builder.addLine("                              sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                              parameterNumber, lookupValue,");
    builder.addLine("                              hostNameValue, divisionNameValue, siteNameValue,");
    builder.addLine("                              curMod, pathValueStr, oldText);");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'fontcolor'   ||");
    builder.addLine("                     curType == 'fontfamily'  ||");
    builder.addLine("                     curType == 'fontkerning' ||");
    builder.addLine("                     curType == 'fontsize'    ||");
    builder.addLine("                     curType == 'fontstyle'   ||");
    builder.addLine("                     curType == 'fontweight') {");
    builder.addLine("              if (curType == 'fontsize')");
    builder.addLine("                newText = HDLmBuildSuffix(newText, 'px');");
    builder.addLine("              let newName = fontNames[curType];");
    builder.addLine("              curNode.style.setProperty(newName, newText);");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'height' ||");
    builder.addLine("                     curType == 'width') {");
    builder.addLine("              newText = HDLmBuildSuffix(newText, 'px');");
    builder.addLine("              curNode.setAttribute(curType, newText);");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'image') {");
    builder.addLine("              if (newText.startsWith('//'))");
    builder.addLine("                curNode.setAttribute('src', 'https:' + newText);");
    builder.addLine("              if (newText.startsWith('data:'))");
    builder.addLine("                curNode.setAttribute('src', newText);");
    builder.addLine("              if (1 == 1) {");
    builder.addLine("                curNode.style.setProperty('background-repeat', 'no-repeat');");
    builder.addLine("                curNode.style.setProperty('background-size', 'cover');");
    builder.addLine("                curNode.style.setProperty('text-align', 'center');");
    builder.addLine("              }");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'order') {");
    builder.addLine("              let nodeChildrenLength = curNode.children.length;");
    builder.addLine("              let newOrder = HDLmBuildOrder(newText, nodeChildrenLength);");
    builder.addLine("              for (let j = 0; j < newOrder.length; j++) {");
    builder.addLine("                curNode.appendChild(curNode.children[newOrder[j]]);");
    builder.addLine("              }");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'remove') {");
    builder.addLine("              if (HDLmCompareCaseInsensitive(newText, 'yes'))");
    builder.addLine("                curNode.remove();");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'replace') {");
    builder.addLine("              if (newText != '') {");
    builder.addLine("                let parentNode = curNode.parentNode;");
    builder.addLine("                let newNodeObj = JSON.parse(newText);");
    builder.addLine("                let newNode = HDLmBuildNodeFromObject(newNodeObj);");
    builder.addLine("                parentNode.replaceChild(newNode, curNode);");
    builder.addLine("              }");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'script' && readyState == 'complete') {");
    builder.addLine("              let functionStr = 'HDLmExecute' + HDLmReplaceInString(curMod.name) + finalLookupIndex;");
    builder.addLine("              /* console.log('Show function string for script', functionStr); */");
    builder.addLine("              window[functionStr]();");
    builder.addLine("            }");
    builder.addLine("            /* Most style values are handled by just setting the style.");
    builder.addLine("               However, we need a somewhat different approach for");
    builder.addLine("               background-image's. */");
    builder.addLine("            else if (curType == 'style') {");
    builder.addLine("              if (curModExtra == 'background-image') {");
    builder.addLine("                let newData = newText;");
    builder.addLine("                if (newData.startsWith('url')) {");
    builder.addLine("                }");
    builder.addLine("                else if (newData.startsWith('data:')) {");
    builder.addLine("                  newData = 'url(' + newData + ')';");
    builder.addLine("                }");
    builder.addLine("                else if (newData.startsWith('http')) {");
    builder.addLine("                  newData = 'url(' + newData + ')';");
    builder.addLine("                }");
    builder.addLine("                else {");
    builder.addLine("                  if (newData.startsWith('//'))");
    builder.addLine("                    newData = 'url(https:' + newData + ')';");
    builder.addLine("                }");
    builder.addLine("                if (1 == 1) {");
    builder.addLine("                  curNode.style.setProperty(curModExtra, newData);");
    builder.addLine("                  curNode.style.setProperty('background-repeat', 'no-repeat');");
    builder.addLine("                  curNode.style.setProperty('background-size', 'cover');");
    builder.addLine("                  /* curNode.style.setProperty('background-position', 'center'); */");
    builder.addLine("                }");
    builder.addLine("                if (1 == 2) {");
    builder.addLine("                  let finalUrl = HDLmGetBackground(curNode, 'junk.jpg');");
    builder.addLine("                }");
    builder.addLine("              }");
    builder.addLine("              else {");
    builder.addLine("                let curModSplit = HDLmStyleSplitString(curModExtra);");
    builder.addLine("                let newTextSplit = HDLmStyleFixValues(newText);");
    builder.addLine("                for (let i in curModSplit) {");
    builder.addLine("                  let newValue = newTextSplit[i];");
    builder.addLine("                  if (newValue == 'none')");
    builder.addLine("                    continue;");
    builder.addLine("                  curNode.style.setProperty(curModSplit[i], newValue);");
    builder.addLine("                }");
    builder.addLine("              }");
    builder.addLine("            }");
    builder.addLine("            else if (curType == 'title' ||");
    builder.addLine("                     curType == 'text'  ||");
    builder.addLine("                     curType == 'textchecked') {");
    builder.addLine("              curNode.textContent = newText;");
    builder.addLine("            }");
    builder.addLine("            let localUpdates = new Object();");
    builder.addLine("            HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                           sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                           parameterNumber, lookupValue,");
    builder.addLine("                           hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                           curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                           oldText, newText);");
    builder.addLine("            postTrace.matcherror = 'fired';");
    builder.addLine("            HDLmSendUpdates(localUpdates, curType, '1.0', postTrace);");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        default: {");
    builder.addLine("          let errorText = \"Invalid modification type value - \" + curType;");
    builder.addLine("          HDLmBuildError('Error', 'Mod', 31, errorText);");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      if (matchFound === false) {");
    builder.addLine("        if (matchError === '') {");
    builder.addLine("          matchError = 'nomatch';");
    builder.addLine("          /* Report that no match was found */");
    builder.addLine("          if (postRuleTracing == true) {");
    builder.addLine("            let localUpdates = new Object();");
    builder.addLine("            HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                           sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                           null, null,");
    builder.addLine("                           hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                           curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                           null, null);");
    builder.addLine("            postTrace.matcherror = matchError;");
    builder.addLine("            HDLmSendUpdates(localUpdates, 'failure', '1.0', postTrace);");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      break;");
    builder.addLine("    }");
    builder.addLine("    /* The log rule matching check below uses a double equals rather");
    builder.addLine("       than a triple equals because the variable may contain a zero");
    builder.addLine("       or 1 rather than a boolean value */");
    builder.addLine("    if (matchError != '' &&");
    builder.addLine("        logRuleMatching == true) {");
    builder.addLine("      let errorText = HDLmBuildErrorRule(curMod, matchError, pathValueStr);");
    builder.addLine("      HDLmBuildError('Trace', 'Mod', 2, errorText);");
    builder.addLine("    }");
    builder.addLine("    return matchFound;");
    builder.addLine("  }");
    builder.addLine("  /* This function builds a string that represents an array.");
    builder.addLine("     The array value may be null or undefined or have zero");
    builder.addLine("     elements in it. All cases are (hopefully) covered below. */");
    builder.addLine("  function HDLmArrayJoin(curArray, joinChar) {");
    builder.addLine("    let rv = \"\";");
    builder.addLine("    let arrayType = typeof(curArray);");
    builder.addLine("    /* Check if the array is undefined */");
    builder.addLine("    if (arrayType == 'undefined') {");
    builder.addLine("      rv = 'undefined';");
    builder.addLine("      return rv;");
    builder.addLine("    }");
    builder.addLine("    /* Check if the array is a null value */");
    builder.addLine("    if (curArray == null) {");
    builder.addLine("      rv = null;");
    builder.addLine("      return rv;");
    builder.addLine("    }");
    builder.addLine("    /* Get an check the array length */");
    builder.addLine("    let arrayLength = curArray.length;");
    builder.addLine("    if (arrayLength <= 0)");
    builder.addLine("      return rv;");
    builder.addLine("    /* Handle each value of the array */");
    builder.addLine("    for (let i = 0; i < arrayLength; i++) {");
    builder.addLine("      if (i > 0)");
    builder.addLine("        rv += joinChar;");
    builder.addLine("      let curValue = curArray[i];");
    builder.addLine("      if (curValue == null)");
    builder.addLine("        rv += 'null';");
    builder.addLine("      else");
    builder.addLine("        rv += String(curValue);");
    builder.addLine("    }");
    builder.addLine("    return rv;");
    builder.addLine("  }");
    builder.addLine("  /* Build an error text string describing a rule that was not");
    builder.addLine("     used for some reason. Note that this code is also used to");
    builder.addLine("     build text strings for rules that are used as well. */");
    builder.addLine("  function HDLmBuildErrorRule(curMod, matchError, pathValueStr) {");
    builder.addLine("    let errorText = \"Modification \" + matchError + \" - \";");
    builder.addLine("    errorText += \"name (\";");
    builder.addLine("    errorText += curMod.name;");
    builder.addLine("    errorText += \")\";");
    builder.addLine("    if (Array.isArray(curMod.find) &&");
    builder.addLine("        curMod.find.length > 0) {");
    builder.addLine("      errorText += \" key (\";");
    builder.addLine("      let findFirst = curMod.find[0];");
    builder.addLine("      errorText += findFirst.attributeName;");
    builder.addLine("      errorText += \")\";");
    builder.addLine("      errorText += \" value (\";");
    builder.addLine("      errorText += findFirst.attributeValue;");
    builder.addLine("      errorText += \")\";");
    builder.addLine("    }");
    builder.addLine("    /* Add the path value string to the error message. This step facilitates");
    builder.addLine("       debugging of various kinds. */");
    builder.addLine("    errorText += ' - ' + pathValueStr;");
    builder.addLine("    return errorText;");
    builder.addLine("  }");
    builder.addLine("  /* This routine builds a DOM node (probably an element) from a");
    builder.addLine("     JavaScript object. The caller passes the JavaScript object.");
    builder.addLine("     This routine builds the DOM node (probably an element) from");
    builder.addLine("     the JavaScript object and all of the children of the JavaScript");
    builder.addLine("     object. Note that this routine recursively calls itself to");
    builder.addLine("     handle nested JavaScript objects. The result is (in most cases)");
    builder.addLine("     a node with subnodes that is returned to the caller. */");
    builder.addLine("  function HDLmBuildNodeFromObject(domObj) {");
    builder.addLine("    /* Check if the DOM node is really an element. We only handle");
    builder.addLine("       elements for now. */");
    builder.addLine("    if (domObj.type != 'Element')");
    builder.addLine("      return null;");
    builder.addLine("    /* First we must check for an actual tag. Without a tag, we can");
    builder.addLine("       not do much of anything. */");
    builder.addLine("    if (domObj.tag == null)");
    builder.addLine("      return null;");
    builder.addLine("    /* Now we can at least create the element DOM node */");
    builder.addLine("    let domNode = document.createElement(domObj.tag);");
    builder.addLine("    /* We now try add all of the attributes (if any) to the newly");
    builder.addLine("       created DOM element node */");
    builder.addLine("    let attrObj = domObj.attributes;");
    builder.addLine("    if (attrObj != null) {");
    builder.addLine("      for (let attrObjName in attrObj) {");
    builder.addLine("        domNode.setAttribute(attrObjName, attrObj[attrObjName]);");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("    /* We can now try to add the text to the corrent element. This");
    builder.addLine("       actually requires createing a text DOM node and adding it to");
    builder.addLine("       the current DOM element. */");
    builder.addLine("    let domText = domObj.text;");
    builder.addLine("    if (domText != null) {");
    builder.addLine("      let textNode = document.createTextNode(domText);");
    builder.addLine("      domNode.appendChild(textNode);");
    builder.addLine("    }");
    builder.addLine("    /* We can now try to create DOM nodes (probably DOM elements) for");
    builder.addLine("       all of the subnodes of the current node (probably an element) */");
    builder.addLine("    let domSubNodes = domObj.subnodes;");
    builder.addLine("    if (domSubNodes != null) {");
    builder.addLine("      let domSubNodesLength = domSubNodes.length;");
    builder.addLine("      for (let i = 0; i < domSubNodesLength; i++) {");
    builder.addLine("        let domSubNode = domSubNodes[i];");
    builder.addLine("        let domSubNodeNode = HDLmBuildNodeFromObject(domSubNode);");
    builder.addLine("        if (domSubNodeNode != null)");
    builder.addLine("          domNode.appendChild(domSubNodeNode);");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("    /* Return the final node value to the caller */");
    builder.addLine("    return domNode;");
    builder.addLine("  }");
    builder.addLine("  /* Build an array of numbers that can be used to change the");
    builder.addLine("     order of a set of subnode. This routine supports orders");
    builder.addLine("     that start with zero and orders that do not start with");
    builder.addLine("     zero. */");
    builder.addLine("  function HDLmBuildOrder(newText, totalLength) {");
    builder.addLine("    /* Convert commas to blanks to help the split below.");
    builder.addLine("       Commas are easier to read. However, getting rid of");
    builder.addLine("       them makes handling the array simpler. */");
    builder.addLine("    newText = newText.replace(/,/g, ' ');");
    builder.addLine("    /* Split the original text into an array of text strings */");
    builder.addLine("    let newTextArray = newText.split(' ');");
    builder.addLine("    /* The array created below is used for each value after");
    builder.addLine("       it is converted to an integer */");
    builder.addLine("    let newIntArray = [];");
    builder.addLine("    /* The array created below is returned to the caller.");
    builder.addLine("       This array contains a set of integers that can be");
    builder.addLine("       used to impose the desired order. */");
    builder.addLine("    let outIntArray = [];");
    builder.addLine("    /* Build a temporary array with a series of ascending numbers.");
    builder.addLine("       The temporary array created below will be something like");
    builder.addLine("       [0,1,2,3,4,5,6]. */");
    builder.addLine("    let tempIntArray = (function(a, b) {while(a--) b[a] = a; return b})(totalLength, []);");
    builder.addLine("    /* Build an array of integers from the text array */");
    builder.addLine("    for (let i = 0; i < newTextArray.length; i++) {");
    builder.addLine("      if (newTextArray[i] == '')");
    builder.addLine("        continue;");
    builder.addLine("      let tempInt = parseInt(newTextArray[i]);");
    builder.addLine("      if (typeof(tempInt) != 'number')");
    builder.addLine("        continue;");
    builder.addLine("      newIntArray.push(tempInt);");
    builder.addLine("    }");
    builder.addLine("    /* Check if the new integer array has all of the needed values.");
    builder.addLine("        if any values are missing, they are added here. This set of");
    builder.addLine("        code adds any missing numbers in the range of zero to total");
    builder.addLine("        length minus one to the new integer array. */");
    builder.addLine("    for (let i = 0; i < totalLength; i++)");
    builder.addLine("      if (newIntArray.includes(i) == false)");
    builder.addLine("        newIntArray.push(i);");
    builder.addLine("    /* Build the final output array */");
    builder.addLine("    for (let i = 0; i < totalLength; i++) {");
    builder.addLine("      let ix = tempIntArray.indexOf(newIntArray[i]);");
    builder.addLine("      outIntArray.push(ix);");
    builder.addLine("      tempIntArray.splice(ix, 1);");
    builder.addLine("      tempIntArray.push(ix)");
    builder.addLine("    }");
    builder.addLine("    return outIntArray;");
    builder.addLine("  }");
    builder.addLine("  /* Build a new value with a suffix if need be. This code");
    builder.addLine("     checks if the value passed by the caller already has");
    builder.addLine("     a suffix. If the value already has a suffix, then no");
    builder.addLine("     suffix is added. The caller provides the suffix value");
    builder.addLine("     that is used (if need be). Note that the above comment");
    builder.addLine("     is wrong. The actual code does something entirely");
    builder.addLine("     different. The check for a number is also wrong,");
    builder.addLine("     but may have been fixed using typeof. */");
    builder.addLine("  function HDLmBuildSuffix(newValue, suffixStr) {");
    builder.addLine("    if ((typeof(newValue) == 'number') &&");
    builder.addLine("        newValue != '')");
    builder.addLine("      newValue += suffixStr;");
    builder.addLine("    return newValue");
    builder.addLine("  }");
    builder.addLine("  /* This Java code builds the HDLmChangeNodes function. The name is a bit");
    builder.addLine("     wrong. This function only handles one node at a time. Of course, this");
    builder.addLine("     function could be invoked with many different node values. This function");
    builder.addLine("     can be invoked in test mode where no actual changes will be made. The");
    builder.addLine("     force break return value will be set in all cases. */");
    builder.addLine("  /* Start the JavaScript function that changes exactly one (the current) node */");
    builder.addLine("  function HDLmChangeNodes(curNode, jsonText, matchUpdateCount, testFlag, sessionIndexValueUsed,");
    builder.addLine("                           matchError, postTrace, postRuleTracing,");
    builder.addLine("                           sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                           parameterNumber, lookupValue,");
    builder.addLine("                           hostNameValue, divisionNameValue, siteNameValue,");
    builder.addLine("                           curMod, pathValueStr, oldText) {");
    builder.addLine("    let changesObj = JSON.parse(jsonText);");
    builder.addLine("    let forceBreak = false;");
    builder.addLine("    for (const keyValue in changesObj) {");
    builder.addLine("      if (!changesObj.hasOwnProperty(keyValue))");
    builder.addLine("        continue;");
    builder.addLine("      let changesValue = changesObj[keyValue];");
    builder.addLine("      switch (keyValue) {");
    builder.addLine("        case 'text':");
    builder.addLine("        case 'title': {");
    builder.addLine("          if (matchUpdateCount > 0) {");
    builder.addLine("            forceBreak = true;");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          if (testFlag == false)");
    builder.addLine("            curNode.textContent = changesValue;");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        case 'textchecked': {");
    builder.addLine("          let actualText = curNode.textContent;");
    builder.addLine("          let requiredText = changesValue[0];");
    builder.addLine("          let changesMatch = HDLmCheckTextMatches(actualText, requiredText,");
    builder.addLine("                                                  matchError, postTrace, postRuleTracing,");
    builder.addLine("                                                  parametersArray, sessionIdJS,");
    builder.addLine("                                                  parameterNumber, lookupValue,");
    builder.addLine("                                                  hostNameValue, divisionNameValue, siteNameValue, curMod,");
    builder.addLine("                                                  pathValueStr);");
    builder.addLine("          if (matchUpdateCount > 1) {");
    builder.addLine("            forceBreak = true;");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          if (changesMatch && testFlag == false)");
    builder.addLine("            curNode.textContent = changesValue[1];");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        case 'visit': {");
    builder.addLine("          let countHigh = HDLmHandleVisitRequest(changesValue, postTrace, testFlag, sessionIndexValueUsed,");
    builder.addLine("                                                 sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                                                 parameterNumber, lookupValue,");
    builder.addLine("                                                 hostNameValue, divisionNameValue, siteNameValue, curMod,");
    builder.addLine("                                                 pathValueStr);");
    builder.addLine("          if (countHigh == true)");
    builder.addLine("            forceBreak = true;");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        default: {");
    builder.addLine("          if (matchUpdateCount > 0) {");
    builder.addLine("            forceBreak = true;");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          if (changesValue == null) {");
    builder.addLine("            if (testFlag == false)");
    builder.addLine("              curNode.style.removeProperty(keyValue);");
    builder.addLine("          }");
    builder.addLine("          else  {");
    builder.addLine("            let changesType = typeof changesValue;");
    builder.addLine("            if (changesType == 'number') {");
    builder.addLine("              changesValue = changesValue.toString();");
    builder.addLine("              changesValue += 'px';");
    builder.addLine("            }");
    builder.addLine("            if (testFlag == false)");
    builder.addLine("              curNode.style.setProperty(keyValue, changesValue);");
    builder.addLine("          }");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("    /* Finish the current JavaScript function */");
    builder.addLine("    return forceBreak;");
    builder.addLine("  }");
    builder.addLine("  /* This function checks if a set of text matches a value passed");
    builder.addLine("     by the caller. If a match is found, then a true value is returned");
    builder.addLine("     to the caller. If the text does not match, then a false value is");
    builder.addLine("     returned to the caller and a note is made of the error. */");
    builder.addLine("  function HDLmCheckTextMatches(actualText, requiredText,");
    builder.addLine("                                matchError, postTrace, postRuleTracing,");
    builder.addLine("                                parametersArray, sessionIdJS,");
    builder.addLine("                                parameterNumber, lookupValue,");
    builder.addLine("                                hostNameValue, divisionNameValue, siteNameValue, curMod,");
    builder.addLine("                                pathValueStr) {");
    builder.addLine("    /* Define the return value */");
    builder.addLine("    let rv;");
    builder.addLine("    /* Check if the actual text matches the required text */");
    builder.addLine("    let requiredTextLower = requiredText.toLowerCase();");
    builder.addLine("    let actualTextLower = actualText.toLowerCase();");
    builder.addLine("    if (actualTextLower.indexOf(requiredTextLower) === -1) {");
    builder.addLine("      if (postRuleTracing == true) {");
    builder.addLine("        let localUpdates = new Object();");
    builder.addLine("        matchError = 'textunequal';");
    builder.addLine("        HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                       sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                       parameterNumber, lookupValue,");
    builder.addLine("                       hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                       curMod.path, curMod.type, pathValueStr, actualText, requiredText);");
    builder.addLine("        postTrace.matcherror = matchError;");
    builder.addLine("        HDLmSendUpdates(localUpdates, 'failure', '1.0', postTrace);");
    builder.addLine("      }");
    builder.addLine("      rv = false;;");
    builder.addLine("    }");
    builder.addLine("    else");
    builder.addLine("      rv = true;");
    builder.addLine("    return rv;");
    builder.addLine("  }");
    builder.addLine("  /* This function adds a special class to a node in some cases.");
    builder.addLine("     The special class (several exist) are used to show what nodes");
    builder.addLine("     have been modified by one or more rules. The class is selected");
    builder.addLine("     based on the node type and the rule type passed by the caller. */");
    builder.addLine("  function HDLmClassAddSpecialClass(curNode, curType, extraStr) {");
    builder.addLine("    /* Check if the rule type is order. We don't need to do");
    builder.addLine("       anything for order rules. */");
    builder.addLine("    if (curType == 'order')");
    builder.addLine("      return;");
    builder.addLine("    /* Check for a very special case */");
    builder.addLine("    if (curType == 'style' && extraStr == 'background-image') {");
    builder.addLine("      HDLmClassAddEntry(curNode, 'HDLmClassBackground');");
    builder.addLine("      return;");
    builder.addLine("    }");
    builder.addLine("    HDLmClassAddEntry(curNode, 'HDLmClassPrimary');");
    builder.addLine("  }");
    builder.addLine("  /* This function creates a CSS entry from scratch and");
    builder.addLine("     makes it part of the DOM. The new CSS entry is by");
    builder.addLine("     default, disabled or enabled at the outset. Of course,");
    builder.addLine("     it can be enabled or disabled later. */");
    builder.addLine("  function HDLmClassAddCss(passedName, passedRules) {");
    builder.addLine("    /* Create the new style sheet */");
    builder.addLine("    var styleTitle = 'HDLmSessionClasses';");
    builder.addLine("    var styleVar = document.createElement('style');");
    builder.addLine("    styleVar.type = 'text/css';");
    builder.addLine("    styleVar.title = styleTitle;");
    builder.addLine("    document.getElementsByTagName('head')[0].appendChild(styleVar);");
    builder.addLine("    styleVar.sheet.insertRule(passedName+\"{\"+passedRules+\"}\", 0);");
    builder.addLine("    /* Try to get the enablement status of the new style sheet */");
    builder.addLine("    var disabledStatus = sessionStorage.getItem(styleTitle + 'Disabled');");
    builder.addLine("    if (disabledStatus == null)");
    builder.addLine("      disabledStatus = true;");
    builder.addLine("    if (disabledStatus == 'true')");
    builder.addLine("      disabledStatus = true;");
    builder.addLine("    if (disabledStatus == 'false')");
    builder.addLine("      disabledStatus = false;");
    builder.addLine("    /* Find and disable or enabled the style sheet */");
    builder.addLine("    var styleSheetList = document.styleSheets;");
    builder.addLine("    for (let i = 0; i < styleSheetList.length; i++) {");
    builder.addLine("      var styleSheet = styleSheetList[i];");
    builder.addLine("      if (styleSheet.title != null &&");
    builder.addLine("          styleSheet.title == styleTitle) {");
    builder.addLine("        if (styleSheet.disabled != disabledStatus)");
    builder.addLine("          styleSheet.disabled = disabledStatus;");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("  }");
    builder.addLine("  /* This function adds a class name to a DOM element, if need be.");
    builder.addLine("     If the DOM element has no classes (the class list is empty), then");
    builder.addLine("     a class list is created. If the DOM element already has a class");
    builder.addLine("     list than a check for a duplicate entry is done. Duplicate");
    builder.addLine("     entries are never created. */");
    builder.addLine("  function HDLmClassAddEntry(elementNodeReference, newClass) {");
    builder.addLine("    const elementClasses = elementNodeReference.classList;");
    builder.addLine("    /* Add the new HTML class, but only if it has not already been");
    builder.addLine("       added */");
    builder.addLine("    if (elementClasses.length == 0)");
    builder.addLine("      elementClasses.add(newClass);");
    builder.addLine("    else if (elementClasses.contains(newClass) == false)");
    builder.addLine("      elementClasses.add(newClass);");
    builder.addLine("  }");
    builder.addLine("  /* This function does a case insensitive string comparison. Of course,");
    builder.addLine("     this function can be changed as need be to use better case insensitive");
    builder.addLine("     string comparisons. This routine will return true if the strings");
    builder.addLine("     are equal. This routine will return false if the strings are not");
    builder.addLine("     equal. */");
    builder.addLine("  function HDLmCompareCaseInsensitive(firstStr, secondStr) {");
    builder.addLine("    return firstStr.localeCompare(secondStr, undefined, { sensitivity: 'accent' }) === 0;");
    builder.addLine("  }");
    builder.addLine("  /* Build a (JSON) string from an error object. The error object may");
    builder.addLine("     be an actual error object or just a string containing an error");
    builder.addLine("     message. The code below handles both cases and returns a JSON");
    builder.addLine("     string to the caller. */");
    builder.addLine("  function HDLmErrorToString(errorObj) {");
    builder.addLine("    let newObj = {};");
    builder.addLine("    if (typeof errorObj === 'string') {");
    builder.addLine("      newObj.name = '';");
    builder.addLine("      newObj.message = errorObj;");
    builder.addLine("      newObj.reason = 'exception';");
    builder.addLine("    }");
    builder.addLine("    else {");
    builder.addLine("      newObj.name = errorObj.name;");
    builder.addLine("      newObj.message = errorObj.message;");
    builder.addLine("      newObj.stack = errorObj.stack;");
    builder.addLine("      newObj.reason = 'exception';");
    builder.addLine("    }");
    builder.addLine("    return JSON.stringify(newObj);");
    builder.addLine("  }");
    builder.addLine("  /* Start the JavaScript function that finds a set of matching elements.");
    builder.addLine("     This function either searches using a CSS Selector value or an XPath");
    builder.addLine("     search value or uses a set of node identifier values or uses a set");
    builder.addLine("     of finds. The CSS Selector value is used in preference to any other");
    builder.addLine("     value, if the CSS Selector value is specified. If the CSS Selector");
    builder.addLine("     value is not specified, then the XPath search value is used, if it");
    builder.addLine("     is specified. The finds (the finds array) are used if they are specified.");
    builder.addLine("     This will only be true if no CSS Selector and/or XPath information and/or");
    builder.addLine("     node identifier information has been provided. */");
    builder.addLine("  function HDLmFind(curMod, nodeIdenTracing, postRuleTracing, postTrace) {");
    builder.addLine("    let nodeList = [];");
    builder.addLine("    /* Check if we should search by CSS Selector, XPath, or using one");
    builder.addLine("       or more finds */");
    builder.addLine("    if (curMod.cssselector !== '') {");
    builder.addLine("      if (postRuleTracing) {");
    builder.addLine("        postTrace.findtype = 'CSS Selector';");
    builder.addLine("        postTrace.findvalue = curMod.cssselector;");
    builder.addLine("      }");
    builder.addLine("      nodeList = document.querySelectorAll(curMod.cssselector);");
    builder.addLine("    }");
    builder.addLine("    /* Check if the XPath search value has been specified */");
    builder.addLine("    else if (curMod.xpath !== '') {");
    builder.addLine("      if (postRuleTracing) {");
    builder.addLine("        postTrace.findtype = 'XPath';");
    builder.addLine("        postTrace.findvalue = curMod.xpath;");
    builder.addLine("      }");
    builder.addLine("      let nodeIter = document.evaluate(curMod.xpath, document, null,");
    builder.addLine("                                       XPathResult.ORDERED_NODE_ITERATOR_TYPE,");
    builder.addLine("                                       null);");
    builder.addLine("      /* The above call produces an iterator that can be used obtain a set");
    builder.addLine("         of elements. Each element is added to the output node list array. */");
    builder.addLine("      let thisNode = nodeIter.iterateNext();");
    builder.addLine("      while (thisNode) {");
    builder.addLine("        nodeList.push(thisNode);");
    builder.addLine("        thisNode = nodeIter.iterateNext();");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("    /* Check if a set of node identifier has been specified */");
    builder.addLine("    else if (curMod.nodeiden !== null) {");
    builder.addLine("      if (postRuleTracing) {");
    builder.addLine("        postTrace.findtype = 'Node identifier';");
    builder.addLine("        postTrace.findvalue = curMod.nodeiden;");
    builder.addLine("      }");
    builder.addLine("      nodeList = HDLmFindNodeIden(curMod, nodeIdenTracing, postRuleTracing, postTrace);");
    builder.addLine("    }");
    builder.addLine("    /* We must search using one or more finds */");
    builder.addLine("    else {");
    builder.addLine("      let findsArray = curMod.find;");
    builder.addLine("      nodeList = [document];");
    builder.addLine("      if (postRuleTracing) {");
    builder.addLine("        postTrace.findtype = 'Finds';");
    builder.addLine("        postTrace.findvalues = curMod.find;");
    builder.addLine("      }");
    builder.addLine("      let findsArrayLength = findsArray.length");
    builder.addLine("      for (let i = 0; i < findsArrayLength; i++) {");
    builder.addLine("        let findEntry = findsArray[i];");
    builder.addLine("        nodeList = HDLmFindOneLevel(nodeList, findEntry);");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("    return nodeList;");
    builder.addLine("  }");
    builder.addLine("  /* This JavaScript function tries to find a set of HTML elements");
    builder.addLine("     (DOM elements) that match the node identifier passed by the");
    builder.addLine("     caller. The DOM is always searched using one of the built-in");
    builder.addLine("     DOM functions.");
    builder.addLine("");
    builder.addLine("     A copy of this code is use to test for ambiguous sets of node");
    builder.addLine("     identifier information in the browser extension code. The code");
    builder.addLine("     in the copy is a slightly modified version of this code. */");
    builder.addLine("  function HDLmFindNodeIden(curMod, nodeIdenTracing, postRuleTracing, postTrace) {");
    builder.addLine("    let   nodeElement;");
    builder.addLine("    let   nodeElements = [];");
    builder.addLine("    let   nodeIden = curMod.nodeiden;");
    builder.addLine("    let   nodeList = [];");
    builder.addLine("    let   nodeAttributes = nodeIden.attributes;");
    builder.addLine("    let   nodeCounts = nodeIden.counts;");
    builder.addLine("    let   nodeType = nodeIden.type;");
    builder.addLine("    let   nodeValue = null;");
    builder.addLine("    /* We need to use a different function depending on the type");
    builder.addLine("       of node identifier */");
    builder.addLine("    switch (nodeType) {");
    builder.addLine("      /* We may be searching by tag name. This might work in some");
    builder.addLine("         cases. */");
    builder.addLine("      case 'tag': {");
    builder.addLine("        let nodeTag = nodeAttributes.tag;");
    builder.addLine("        nodeValue = nodeTag;");
    builder.addLine("        nodeElements = document.getElementsByTagName(nodeTag);");
    builder.addLine("        /* Check if post rule tracing is active or note. Trace the number");
    builder.addLine("           of nodes, and how they were found, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          postTrace.nodegetby = 'tag';");
    builder.addLine("          postTrace.nodegetvalue = nodeTag;");
    builder.addLine("          postTrace.nodecount = nodeElements.length;");
    builder.addLine("        }");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      /* We may be searching by id. This will only work if the id");
    builder.addLine("         values are permanent, rather than generated. Generated id");
    builder.addLine("         values change each time a web page is loaded. As a consequence,");
    builder.addLine("         they can not be used. */");
    builder.addLine("      case 'id': {");
    builder.addLine("        let nodeId = nodeAttributes.id;");
    builder.addLine("        nodeValue = nodeId;");
    builder.addLine("        nodeElement = document.getElementById(nodeId);");
    builder.addLine("        /* console.log(nodeId); */");
    builder.addLine("        if (nodeElement != null)");
    builder.addLine("          nodeElements = [nodeElement];");
    builder.addLine("        else");
    builder.addLine("          nodeElements = [];");
    builder.addLine("        /* Check if post rule tracing is active or note. Trace the number");
    builder.addLine("           of nodes, and how they were found, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          postTrace.nodegetby = 'id';");
    builder.addLine("          postTrace.nodegetvalue = nodeId;");
    builder.addLine("          postTrace.nodecount = nodeElements.length;");
    builder.addLine("        }");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      /* We may be searching by class name. Class names tend to be");
    builder.addLine("         relatively permanent and hence are a good thing to search");
    builder.addLine("         for. Of course, an HTML DOM node can have more than one");
    builder.addLine("         class name. The first class name is always used. */");
    builder.addLine("      case 'class': {");
    builder.addLine("        let nodeClass;");
    builder.addLine("        /* Check if the 'bestclass' (without the quotes) attribute was");
    builder.addLine("           specified. If so, use the 'bestclass' (without the quotes)");
    builder.addLine("           value, instead of the first class. The best class is the");
    builder.addLine("           class that matches the fewest DOM elements. */");
    builder.addLine("        if (nodeAttributes.hasOwnProperty('bestclass'))");
    builder.addLine("          nodeClass = nodeAttributes['bestclass'];");
    builder.addLine("        else {");
    builder.addLine("          let nodeClassList = nodeAttributes.class;");
    builder.addLine("          nodeClass = nodeClassList[0];");
    builder.addLine("        }");
    builder.addLine("        nodeValue = nodeClass;");
    builder.addLine("        nodeElements = document.getElementsByClassName(nodeClass);");
    builder.addLine("        /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("           node class, if need be. */");
    builder.addLine("        if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("            (nodeIdenTracing == HDLmNodeIdenTracing.error && nodeClass == '')) {");
    builder.addLine("          let   errorText = `Node identifier - node class is (${nodeClass})`;");
    builder.addLine("          HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("        }");
    builder.addLine("        /* Check if post rule tracing is active or note. Trace the number");
    builder.addLine("           of nodes, and how they were found, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          postTrace.nodegetby = 'class';");
    builder.addLine("          postTrace.nodegetvalue = nodeClass;");
    builder.addLine("          postTrace.nodecount = nodeElements.length;");
    builder.addLine("        }");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      /* We may be searching by name. This will work in some cases. */");
    builder.addLine("      case 'name': {");
    builder.addLine("        let nodeName = nodeAttributes.name;");
    builder.addLine("        nodeValue = nodeName;");
    builder.addLine("        nodeElements = document.getElementsByName(nodeName);");
    builder.addLine("        /* Check if post rule tracing is active or note. Trace the number");
    builder.addLine("           of nodes, and how they were found, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          postTrace.nodegetby = 'name';");
    builder.addLine("          postTrace.nodegetvalue = nodeName;");
    builder.addLine("          postTrace.nodecount = nodeElements.length;");
    builder.addLine("        }");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      default: {");
    builder.addLine("        let errorText = \"Invalid node identifier type value - \" + nodeType;");
    builder.addLine("        HDLmBuildError('Error', 'NodeIden', 40, errorText);");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("    /* Save the number of node elements found in a local variable. This variable");
    builder.addLine("       is used in several places below. */");
    builder.addLine("    let   nodeElementsLength = nodeElements.length;");
    builder.addLine("    /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("       number of nodes, if need be. */");
    builder.addLine("    if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("        (nodeIdenTracing == HDLmNodeIdenTracing.error && nodeElementsLength == 0)) {");
    builder.addLine("      let   nodeText = nodeType;");
    builder.addLine("      if (nodeValue != null)");
    builder.addLine("        nodeText = nodeType + '/' + nodeValue");
    builder.addLine("      let   errorText = `Node identifier - get for (${nodeText}) returned (${nodeElementsLength}) nodes`;");
    builder.addLine("      HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("    }");
    builder.addLine("    /* Check for a very special case. If the original node identifier collection");
    builder.addLine("       found just one DOM HTML element for the current type and if current document");
    builder.addLine("       search also found just one DOM HTML element for the current type, then we");
    builder.addLine("       may be done. We specify a different type of attribute check in this case. */");
    builder.addLine("    let nodeIdenCheckType = 'full';");
    builder.addLine("    if (nodeCounts[nodeType] == 1 && nodeElementsLength == 1)");
    builder.addLine("      nodeIdenCheckType = 'partial';");
    builder.addLine("    /* At this point we have a set of HTML node elements. Some of them");
    builder.addLine("       may really match the node identifier criteria. Others may not. */");
    builder.addLine("    nodeList = HDLmFindNodeIdenCheck(nodeElements,");
    builder.addLine("                                     nodeIden,");
    builder.addLine("                                     nodeIdenCheckType,");
    builder.addLine("                                     nodeIdenTracing,");
    builder.addLine("                                     postRuleTracing,");
    builder.addLine("                                     postTrace);");
    builder.addLine("    return nodeList;");
    builder.addLine("  }");
    builder.addLine("  /* This routine takes a list of HTML node elements and checks each one.");
    builder.addLine("     If an HTML node matches the current attributes (well enough), it is");
    builder.addLine("     added to the output list of HTML nodes that is returned to the caller. */");
    builder.addLine("  function HDLmFindNodeIdenCheck(nodeElements,");
    builder.addLine("                                 nodeIden,");
    builder.addLine("                                 nodeIdenCheckType,");
    builder.addLine("                                 nodeIdenTracing,");
    builder.addLine("                                 postRuleTracing,");
    builder.addLine("                                 postTrace) {");
    builder.addLine("    let nodeList = [];");
    builder.addLine("    let nodeCounter = 0;");
    builder.addLine("    let postTraceName;");
    builder.addLine("    let nodeElementsLength = nodeElements.length;");
    builder.addLine("    elementLoop:for (let i = 0; i < nodeElementsLength; i++) {");
    builder.addLine("      let currentElement = nodeElements[i];");
    builder.addLine("      /* Declare and define a few variables */");
    builder.addLine("      let   grandParentElement;");
    builder.addLine("      let   parentElement;");
    builder.addLine("      /* Increment the node counter */");
    builder.addLine("      nodeCounter++;");
    builder.addLine("      postTraceName = 'nodetarget';");
    builder.addLine("      if (nodeCounter > 1)");
    builder.addLine("        postTraceName += String(nodeCounter-1)");
    builder.addLine("      let   nodeCurrentAttributes = nodeIden.attributes;");
    builder.addLine("      let   currentMatchValue = HDLmFindNodeIdenMatch(currentElement,");
    builder.addLine("                                                      nodeCurrentAttributes,");
    builder.addLine("                                                      nodeIdenTracing,");
    builder.addLine("                                                      postRuleTracing,");
    builder.addLine("                                                      postTrace,");
    builder.addLine("                                                      postTraceName);");
    builder.addLine("      /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("         match value, if need be. */");
    builder.addLine("      if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("          (nodeIdenTracing == HDLmNodeIdenTracing.error && currentMatchValue < 0.95)) {");
    builder.addLine("        let   errorText = `Node identifier - current match value (${currentMatchValue}) for element (${currentElement})`;");
    builder.addLine("        HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("      }");
    builder.addLine("      if (currentMatchValue < 0.95)");
    builder.addLine("        continue elementLoop;");
    builder.addLine("      /* The dummy loop below is used to allow break to work */");
    builder.addLine("      while (true) {");
    builder.addLine("        /* We now need to get the parent element, if possible */");
    builder.addLine("        parentElement = currentElement.parentElement;");
    builder.addLine("        if (typeof parentElement == 'undefined' ||");
    builder.addLine("            parentElement == null)");
    builder.addLine("          break;");
    builder.addLine("        /* Check if the node identifier has parent information */");
    builder.addLine("        if (nodeIden.hasOwnProperty('parent') == false)");
    builder.addLine("          break;");
    builder.addLine("        /* We now need to check the attributes of the parent of the current");
    builder.addLine("           HTML DOM element, if possible */");
    builder.addLine("        postTraceName = 'nodeparent';");
    builder.addLine("        if (nodeCounter > 1)");
    builder.addLine("          postTraceName += String(nodeCounter-1);");
    builder.addLine("        let   nodeParentAttributes = nodeIden.parent;");
    builder.addLine("        if (typeof nodeParentAttributes == 'undefined' ||");
    builder.addLine("            nodeParentAttributes == null)");
    builder.addLine("          break;");
    builder.addLine("        let   parentMatchValue = HDLmFindNodeIdenMatch(parentElement,");
    builder.addLine("                                                       nodeParentAttributes,");
    builder.addLine("                                                       nodeIdenTracing,");
    builder.addLine("                                                       postRuleTracing,");
    builder.addLine("                                                       postTrace,");
    builder.addLine("                                                       postTraceName);");
    builder.addLine("        /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("           match value, if need be. */");
    builder.addLine("        if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("            (nodeIdenTracing == HDLmNodeIdenTracing.error && parentMatchValue < 0.95)) {");
    builder.addLine("          let   errorText = `Node identifier - parent match value (${parentMatchValue}) for element (${parentElement})`;");
    builder.addLine("          HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("        }");
    builder.addLine("        if (parentMatchValue < 0.95)");
    builder.addLine("          continue elementLoop;");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      /* The dummy loop below is used to allow break to work */");
    builder.addLine("      while (true) {");
    builder.addLine("        /* We now need to get the grand parent element, if possible */");
    builder.addLine("        if (typeof parentElement == 'undefined' ||");
    builder.addLine("            parentElement == null)");
    builder.addLine("          break;");
    builder.addLine("        /* Check if the node identifier has grand parent information */");
    builder.addLine("        if (nodeIden.hasOwnProperty('grandparent') == false)");
    builder.addLine("          break;");
    builder.addLine("        /* Get and check the grand parent node element */");
    builder.addLine("        grandParentElement = parentElement.parentElement;");
    builder.addLine("        if (typeof grandParentElement == 'undefined' ||");
    builder.addLine("            grandParentElement == null)");
    builder.addLine("          break;");
    builder.addLine("        postTraceName = 'nodegrandparent';");
    builder.addLine("        if (nodeCounter > 1)");
    builder.addLine("          postTraceName += String(nodeCounter-1);");
    builder.addLine("        /* We now need to check the attributes of the grandparent of the current");
    builder.addLine("           HTML DOM element, if need be, and if possible */");
    builder.addLine("        let   nodeGrandParentAttributes = nodeIden.grandparent;");
    builder.addLine("        if (typeof nodeGrandParentAttributes == 'undefined' ||");
    builder.addLine("            nodeGrandParentAttributes == null)");
    builder.addLine("          break;");
    builder.addLine("        let   grandParentMatchValue = HDLmFindNodeIdenMatch(grandParentElement,");
    builder.addLine("                                                            nodeGrandParentAttributes,");
    builder.addLine("                                                            nodeIdenTracing,");
    builder.addLine("                                                            postRuleTracing,");
    builder.addLine("                                                            postTrace,");
    builder.addLine("                                                            postTraceName);");
    builder.addLine("        /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("            match value, if need be. */");
    builder.addLine("        if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("            (nodeIdenTracing == HDLmNodeIdenTracing.error && grandParentMatchValue < 0.95)) {");
    builder.addLine("          let   errorText = `Node identifier - grandparent match value (${grandParentMatchValue}) for element (${grandParentElement})`;");
    builder.addLine("          HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("        }");
    builder.addLine("        if (grandParentMatchValue < 0.95)");
    builder.addLine("          continue elementLoop;");
    builder.addLine("        break;");
    builder.addLine("      }");
    builder.addLine("      nodeList.push(currentElement);");
    builder.addLine("    }");
    builder.addLine("    return nodeList;");
    builder.addLine("  }");
    builder.addLine("  /* This routine takes one HTML node element and checks how well it matches");
    builder.addLine("     a set of attributes. The final match score is returned to the caller.");
    builder.addLine("     The final match score is a floating-point value in the range of 0.0");
    builder.addLine("     to 1.0. The HTML DOM element (node element) and the expected node");
    builder.addLine("     attributes are passed by the caller. */");
    builder.addLine("  function HDLmFindNodeIdenMatch(nodeElement,");
    builder.addLine("                                 nodeAttributes,");
    builder.addLine("                                 nodeIdenTracing,");
    builder.addLine("                                 postRuleTracing,");
    builder.addLine("                                 postTrace,");
    builder.addLine("                                 postName) {");
    builder.addLine("    let   denominator = 0.0;");
    builder.addLine("    let   nodeActualValue;");
    builder.addLine("    let   nodeAttributeValue;");
    builder.addLine("    let   nodeAttributeChecks = [];");
    builder.addLine("    let   numerator = 0.0;");
    builder.addLine("    let   numeratorIncrementValue;");
    builder.addLine("    /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("       attribute values, if need be. */");
    builder.addLine("    if (postRuleTracing) {");
    builder.addLine("      nodeActualValue = nodeElement.tagName;");
    builder.addLine("      nodeAttributeValue = nodeAttributes.tag");
    builder.addLine("      let   nodeAttributeCheck = new Object();");
    builder.addLine("      nodeAttributeCheck.type = 'tag';");
    builder.addLine("      nodeAttributeCheck.attributevalue = nodeAttributeValue;");
    builder.addLine("      nodeAttributeCheck.actualvalue = nodeActualValue;");
    builder.addLine("      let   traceValue = 0.0;");
    builder.addLine("      if (HDLmCompareCaseInsensitive(nodeElement.tagName, nodeAttributes.tag))");
    builder.addLine("        traceValue = 1.0;");
    builder.addLine("      nodeAttributeCheck.matchvalue = traceValue;");
    builder.addLine("      postTrace[postName + 'tag'] = nodeAttributeCheck;");
    builder.addLine("    }");
    builder.addLine("    /* Check for a quick exit. If the tag name doesn't match, then");
    builder.addLine("       we are done. We insist that the tag name match immediately");
    builder.addLine("       and exactly (the actual tag name comparison is caseless). */");
    builder.addLine("    if (HDLmCompareCaseInsensitive(nodeElement.tagName, nodeAttributes.tag) == false) {");
    builder.addLine("      return 0.0;");
    builder.addLine("    }");
    builder.addLine("    /* Check all of the attributes passed by the caller. Get the");
    builder.addLine("       set of keys for each of the attributes. The keys are used");
    builder.addLine("       to obtain the expected and actual value of each attribute. */");
    builder.addLine("    let nodeAttributeKeys = Object.keys(nodeAttributes);");
    builder.addLine("    let nodeAttributeKeysLength = nodeAttributeKeys.length;");
    builder.addLine("    for (let i = 0; i < nodeAttributeKeysLength; i++) {");
    builder.addLine("      let nodeAttributeKey = nodeAttributeKeys[i];");
    builder.addLine("      /* Check if the current attribute key is one that we");
    builder.addLine("         don't want to consider all. Just skip this key if");
    builder.addLine("         we find it. The best class is the class that matches");
    builder.addLine("         the fewest DOM elements. */");
    builder.addLine("      if (nodeAttributeKey == 'bestclass')");
    builder.addLine("        continue;");
    builder.addLine("      let   nodeAttributeCheck = new Object();");
    builder.addLine("      numeratorIncrementValue = 0.0;");
    builder.addLine("      /* Always bump the denominator. This is done for all");
    builder.addLine("         attributes including those that don't match. */");
    builder.addLine("      denominator++;");
    builder.addLine("      /* Get the current node attributes expected value from the node");
    builder.addLine("         attributes passed by the caller. Note that these are the");
    builder.addLine("         expected values. For most attributes this is a string. For");
    builder.addLine("         class attributes, this is an array of class names. */");
    builder.addLine("      nodeAttributeValue = nodeAttributes[nodeAttributeKey];");
    builder.addLine("      /* Check if the attribute we want is the tag. The tag is not really");
    builder.addLine("         an attribute. Special case code is needed to handle the tag.");
    builder.addLine("         A special call is needed to get the actual tag name of the");
    builder.addLine("         DOM element. This call will always return the tag name in");
    builder.addLine("         uppercase. As a consequence, the expected value must also be");
    builder.addLine("         changed to uppercase. */");
    builder.addLine("      if (nodeAttributeKey == 'tag') {");
    builder.addLine("        nodeActualValue = nodeElement.tagName;");
    builder.addLine("        /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("            nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("          let   errorText;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              HDLmCompareCaseInsensitive(nodeActualValue, nodeAttributeValue))");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          /* Check if node identifier tracing should actually happen */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              (nodeIdenTracing == HDLmNodeIdenTracing.error && traceValue != 1.0)) {");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("        /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("          nodeAttributeCheck.attributevalue = nodeAttributeValue;");
    builder.addLine("          nodeAttributeCheck.actualvalue = nodeActualValue;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              HDLmCompareCaseInsensitive(nodeActualValue, nodeAttributeValue))");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          nodeAttributeCheck.matchvalue = traceValue;");
    builder.addLine("          nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("        }");
    builder.addLine("        /* If we don't have a value that we can compare, then we are done */");
    builder.addLine("        if (nodeActualValue == null)");
    builder.addLine("          continue;");
    builder.addLine("        /* Compare the expected value and the actual value. If they are the");
    builder.addLine("           same, then we can increment the numerator. */");
    builder.addLine("        if (HDLmCompareCaseInsensitive(nodeActualValue, nodeAttributeValue))");
    builder.addLine("          numeratorIncrementValue = 1.0;");
    builder.addLine("      }");
    builder.addLine("      /* Check if the attribute we want is the class. The class in the");
    builder.addLine("         DOM element is always just one string. However, the DOM class");
    builder.addLine("         string can have several class names in it. The set of all of");
    builder.addLine("         the actual class names from the DOM node is checked below. */");
    builder.addLine("      else if (nodeAttributeKey == 'class') {");
    builder.addLine("        /* Check if the node attribute value is an array or not. If");
    builder.addLine("           the node attribute value is an array, then we just want");
    builder.addLine("           to use the first element of the array and to convert the");
    builder.addLine("           node attribute value to something that is not an array. */");
    builder.addLine("        if (Array.isArray(nodeAttributeValue) &&");
    builder.addLine("            nodeAttributeValue.length > 0)");
    builder.addLine("          nodeAttributeValue = nodeAttributeValue[0];");
    builder.addLine("        let nodeActualValueString = nodeElement.getAttribute('class');");
    builder.addLine("        if (nodeActualValueString != null) {");
    builder.addLine("          /* Split the class attribute into an array of values */");
    builder.addLine("          let nodeActualValueSplitArray = nodeActualValueString.split(' ');");
    builder.addLine("          let nodeActualValueSplitArrayLen = nodeActualValueSplitArray.length;");
    builder.addLine("          let nodeActualValueSplit = [];");
    builder.addLine("          /* Check if each value ends with a newline. Remove the newline. Add");
    builder.addLine("             all of the remaining entries to the output array, if the remaining");
    builder.addLine("             length is greater than zero. */");
    builder.addLine("          for (let i = 0; i < nodeActualValueSplitArrayLen; i++) {");
    builder.addLine("            /* Get the current value */");
    builder.addLine("            let nodeActualValueSplitValue = nodeActualValueSplitArray[i];");
    builder.addLine("            /* Remove the trailing newline character, if need be */");
    builder.addLine("            if (nodeActualValueSplitValue.endsWith('\\n')) {");
    builder.addLine("              let nodeActualValueSplitValueLen = nodeActualValueSplitValue.length;");
    builder.addLine("              nodeActualValueSplitValue = nodeActualValueSplitValue.substr(0, nodeActualValueSplitValueLen-1);");
    builder.addLine("            }");
    builder.addLine("            /* If the remaining length is greater than zero, add the value to the");
    builder.addLine("               output array */");
    builder.addLine("            if (nodeActualValueSplitValue.length > 0)");
    builder.addLine("              nodeActualValueSplit.push(nodeActualValueSplitValue);");
    builder.addLine("          }");
    builder.addLine("          /* Check if we can actually get the actual value from the output");
    builder.addLine("             array. The actual value will be an array of the class values");
    builder.addLine("             for a node element. */");
    builder.addLine("          if (nodeActualValueSplit.length > 0) {");
    builder.addLine("            nodeActualValue = [...nodeActualValueSplit];");
    builder.addLine("          }");
    builder.addLine("          else");
    builder.addLine("            nodeActualValue = null;");
    builder.addLine("        }");
    builder.addLine("        else");
    builder.addLine("          nodeActualValue = null;");
    builder.addLine("        /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("            nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("          let   errorText;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              nodeActualValue.includes(nodeAttributeValue))");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          /* Check if node identifier tracing should actually happen */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              (nodeIdenTracing == HDLmNodeIdenTracing.error && traceValue != 1.0)) {");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("        /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("          nodeAttributeCheck.attributevalue = nodeAttributeValue;");
    builder.addLine("          nodeAttributeCheck.actualvalue = nodeActualValue;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              nodeActualValue.includes(nodeAttributeValue))");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          nodeAttributeCheck.matchvalue = traceValue;");
    builder.addLine("          nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("        }");
    builder.addLine("        /* If we don't have a value that we can compare, then we are done */");
    builder.addLine("        if (nodeActualValue == null)");
    builder.addLine("          continue;");
    builder.addLine("        /* Check if the actual value (the first actual class value) is one of");
    builder.addLine("           the expected class values. If this is true, then we can increment");
    builder.addLine("           the numerator. */");
    builder.addLine("        if (nodeActualValue.includes(nodeAttributeValue))");
    builder.addLine("          numeratorIncrementValue = 1.0;");
    builder.addLine("      }");
    builder.addLine("      /* Check if the attribute we want is the inner text. The inner");
    builder.addLine("         text is not really an attribute. Special case code is needed");
    builder.addLine("         to handle the inner text. A special call is needed to get the");
    builder.addLine("         actual inner text (if any) for a DOM element. Note that the");
    builder.addLine("         inner text (if any) is always converted to lower case. This");
    builder.addLine("         is because the inner text has a bad habit of changing case,");
    builder.addLine("         as the browser window changes size. To make the inner text");
    builder.addLine("         more stable, we always convert it to lower case. */");
    builder.addLine("      else if (nodeAttributeKey == 'innertext') {");
    builder.addLine("        let nodeIndexOf;");
    builder.addLine("        let nodeInnerText = nodeElement.innerText;");
    builder.addLine("        if ((typeof nodeInnerText) == 'undefined')");
    builder.addLine("          nodeInnerText = null;");
    builder.addLine("        if (nodeInnerText != null) {");
    builder.addLine("          nodeIndexOf = nodeInnerText.indexOf('ï¿½');");
    builder.addLine("          if (nodeIndexOf >= 0)");
    builder.addLine("            nodeInnerText = nodeInnerText.substring(0, nodeIndexOf);");
    builder.addLine("          nodeIndexOf = nodeInnerText.indexOf('\\n');");
    builder.addLine("          if (nodeIndexOf >= 0)");
    builder.addLine("            nodeInnerText = nodeInnerText.substring(0, nodeIndexOf);");
    builder.addLine("          nodeInnerText = nodeInnerText.toLowerCase().trim();");
    builder.addLine("          if (nodeInnerText.length > 20)");
    builder.addLine("            nodeInnerText = nodeInnerText.substring(0, 20);");
    builder.addLine("        }");
    builder.addLine("        nodeActualValue = nodeInnerText;");
    builder.addLine("        /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("            nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("          let   errorText;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              HDLmCompareCaseInsensitive(nodeAttributeValue, nodeActualValue))");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          /* Check if node identifier tracing should actually happen */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              (nodeIdenTracing == HDLmNodeIdenTracing.error && traceValue != 1.0)) {");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("        /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("          nodeAttributeCheck.attributevalue = nodeAttributeValue;");
    builder.addLine("          nodeAttributeCheck.actualvalue = nodeActualValue;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              HDLmCompareCaseInsensitive(nodeAttributeValue, nodeActualValue))");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          nodeAttributeCheck.matchvalue = traceValue;");
    builder.addLine("          nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("        }");
    builder.addLine("        /* If we don't have a value that we can compare, then we are done */");
    builder.addLine("        if (nodeActualValue == null)");
    builder.addLine("          continue;");
    builder.addLine("        /* Compare the expected value and the actual value. If they are the");
    builder.addLine("           same, then we can increment the numerator. */");
    builder.addLine("        if (HDLmCompareCaseInsensitive(nodeAttributeValue, nodeActualValue))");
    builder.addLine("          numeratorIncrementValue = 1.0;");
    builder.addLine("      }");
    builder.addLine("      /* Check if the attribute we want is the perceptual hash. The");
    builder.addLine("         perceptual hash is not really an attribute and cannot be");
    builder.addLine("         compared. Note that the actual DOM will naver have a node");
    builder.addLine("         with a perceptual hash value. */");
    builder.addLine("      else if (nodeAttributeKey == 'phash') {");
    builder.addLine("        /* Use the attribute value as the actual value. This way they");
    builder.addLine("           will always compare as equal. */");
    builder.addLine("        nodeActualValue = nodeAttributeValue;");
    builder.addLine("        /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("            nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("          let   errorText;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              HDLmCompareCaseInsensitive(nodeAttributeValue, nodeActualValue))");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          /* Check if node identifier tracing should actually happen */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              (nodeIdenTracing == HDLmNodeIdenTracing.error && traceValue != 1.0)) {");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("        /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("          nodeAttributeCheck.attributevalue = nodeAttributeValue;");
    builder.addLine("          nodeAttributeCheck.actualvalue = nodeActualValue;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              HDLmCompareCaseInsensitive(nodeAttributeValue, nodeActualValue))");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          nodeAttributeCheck.matchvalue = traceValue;");
    builder.addLine("          nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("        }");
    builder.addLine("        /* If we don't have a value that we can compare, then we are done */");
    builder.addLine("        if (nodeActualValue == null)");
    builder.addLine("          continue;");
    builder.addLine("        /* Compare the expected value and the actual value. If they are the");
    builder.addLine("           same, then we can increment the numerator. */");
    builder.addLine("        if (HDLmCompareCaseInsensitive(nodeAttributeValue, nodeActualValue))");
    builder.addLine("          numeratorIncrementValue = 1.0;");
    builder.addLine("      }");
    builder.addLine("      /* Check if the attribute we want is the source (src). Generally");
    builder.addLine("         src values can be compared in a completely conventional way.");
    builder.addLine("         However, if the source is a URL (very common) and if we have");
    builder.addLine("         a perceptual hash value for the URL, then we really want to");
    builder.addLine("         compare the perceptual hash value, not the URL for the image.");
    builder.addLine("         This is a very complex process (to say the least). */");
    builder.addLine("      else if (nodeAttributeKey == 'src') {");
    builder.addLine("        nodeActualValue = nodeElement.getAttribute(nodeAttributeKey);");
    builder.addLine("        let   nodePHashCheck = false;");
    builder.addLine("        /* The dummy loop below is used to allow break to work */");
    builder.addLine("        while (true) {");
    builder.addLine("          let   nodeActualIndex;");
    builder.addLine("          let   nodeActualPHash;");
    builder.addLine("          let   nodeActualUrl;");
    builder.addLine("          let   nodeAttributesPHashSimilarity;");
    builder.addLine("          /* Check if the actual node value is null. This can happen");
    builder.addLine("             if the source is provided by CSS. */");
    builder.addLine("          if (nodeActualValue == null)");
    builder.addLine("            break;");
    builder.addLine("          /* Try to find the URL in the actual source */");
    builder.addLine("          nodeActualIndex = nodeActualValue.indexOf('http');");
    builder.addLine("          if (nodeActualIndex < 0)");
    builder.addLine("            break;");
    builder.addLine("          nodeActualUrl = HDLmRemoveProtocol(nodeActualValue);");
    builder.addLine("          /* Try to get the perceptual hash value for the current URL */");
    builder.addLine("          nodeActualPHash = HDLmFindPHash(nodeActualUrl);");
    builder.addLine("          if (nodeActualPHash == null) {");
    builder.addLine("            HDLmGetPHash(nodeActualUrl);");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          /* Get the current node attributes perceptual hash expected value");
    builder.addLine("             from the node attributes passed by the caller. Note that this");
    builder.addLine("             is an expected value. This should be a 64-bit number returned");
    builder.addLine("             as a 16-digit hexadecimal string. */");
    builder.addLine("          if (nodeAttributes.hasOwnProperty('phash') == false)");
    builder.addLine("            break;");
    builder.addLine("          let nodeAttributesPHashValue = nodeAttributes['phash'];");
    builder.addLine("          /* At this point we can compare the perceptual hash values and");
    builder.addLine("             show that a perceptual hash comparison was completed */");
    builder.addLine("          nodeAttributesPHashSimilarity = HDLmHammingDistanceAdjusted(nodeAttributesPHashValue,");
    builder.addLine("                                                                      nodeActualPHash);");
    builder.addLine("          nodePHashCheck = true;");
    builder.addLine("          /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("             perceptual hash values, if need be. */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("            let   errorText;");
    builder.addLine("            /* Check if node identifier tracing should actually happen */");
    builder.addLine("            if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("                (nodeIdenTracing == HDLmNodeIdenTracing.error && nodeAttributesPHashSimilarity >= 0.10)) {");
    builder.addLine("              errorText = `Node identifier - key (perceptual hash) actual (${nodeActualPHash}) expected (${nodeAttributesPHashValue})`;");
    builder.addLine("              HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("              errorText = `Node identifier - key (perceptual hash) comparison value (${nodeAttributesPHashSimilarity})`;");
    builder.addLine("              HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            }");
    builder.addLine("          }");
    builder.addLine("          /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("             attribute values, if need be. */");
    builder.addLine("          if (postRuleTracing) {");
    builder.addLine("            nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("            nodeAttributeCheck.attributevalue = nodeAttributesPHashValue;");
    builder.addLine("            nodeAttributeCheck.actualvalue = nodeActualPHash;");
    builder.addLine("            nodeAttributeCheck.matchvalue = nodeAttributesPHashSimilarity;");
    builder.addLine("            nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("          }");
    builder.addLine("          /* Check if the perceptual hash simularity value is low enough */");
    builder.addLine("          if (nodeAttributesPHashSimilarity < 0.10) {");
    builder.addLine("            numeratorIncrementValue = 1.0;");
    builder.addLine("          }");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        /* The code below only needs to be executed if we were not able");
    builder.addLine("           to compare the perceptual hash values */");
    builder.addLine("        if (nodePHashCheck == false) {");
    builder.addLine("          /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("             attribute values, if need be. */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("            let   errorText;");
    builder.addLine("            let   traceValue = 0.0;");
    builder.addLine("            if (nodeActualValue != null &&");
    builder.addLine("                nodeAttributeValue == nodeActualValue)");
    builder.addLine("              traceValue = 1.0;");
    builder.addLine("            /* Check if node identifier tracing should actually happen */");
    builder.addLine("            if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("                (nodeIdenTracing == HDLmNodeIdenTracing.error && traceValue != 1.0)) {");
    builder.addLine("              errorText = `Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("              HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("              errorText = `Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("              HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            }");
    builder.addLine("          }");
    builder.addLine("          /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("             attribute values, if need be. */");
    builder.addLine("          if (postRuleTracing) {");
    builder.addLine("            nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("            nodeAttributeCheck.attributevalue = nodeAttributeValue;");
    builder.addLine("            nodeAttributeCheck.actualvalue = nodeActualValue;");
    builder.addLine("            let   traceValue = 0.0;");
    builder.addLine("            if (nodeActualValue != null &&");
    builder.addLine("                nodeAttributeValue == nodeActualValue)");
    builder.addLine("              traceValue = 1.0;");
    builder.addLine("            nodeAttributeCheck.matchvalue = traceValue;");
    builder.addLine("            nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("          }");
    builder.addLine("          /* If we don't have a value that we can compare, then we are done */");
    builder.addLine("          if (nodeActualValue == null)");
    builder.addLine("            continue;");
    builder.addLine("          /* Compare the expected value and the actual value. If they are the");
    builder.addLine("             same, then we can increment the numerator. */");
    builder.addLine("          if (nodeAttributeValue == nodeActualValue)");
    builder.addLine("            numeratorIncrementValue = 1.0;");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      /* Check if the attribute we want is the style. Generally styles");
    builder.addLine("         can be compared in a completely conventional way. However, if");
    builder.addLine("         the style uses a background image and if we have a perceptual");
    builder.addLine("         hash value for the background image, then we really want to");
    builder.addLine("         compare the perceptual hash value, not the URL for the image.");
    builder.addLine("         This is a very complex process (to say the least). */");
    builder.addLine("      else if (nodeAttributeKey == 'style') {");
    builder.addLine("        nodeActualValue = nodeElement.getAttribute(nodeAttributeKey);");
    builder.addLine("        let   nodePHashCheck = false;");
    builder.addLine("        /* The dummy loop below is used to allow break to work */");
    builder.addLine("        while (true) {");
    builder.addLine("          let   nodeActualIndex;");
    builder.addLine("          let   nodeActualPHash;");
    builder.addLine("          let   nodeActualUrl;");
    builder.addLine("          let   nodeAttributesPHashSimilarity;");
    builder.addLine("          /* Check if the actual node value is null. This can happen");
    builder.addLine("             if the style is provided by CSS. */");
    builder.addLine("          if (nodeActualValue == null)");
    builder.addLine("            break;");
    builder.addLine("          /* Check for a required keyword in the style */");
    builder.addLine("          nodeActualIndex = nodeActualValue.indexOf('background-image');");
    builder.addLine("          if (nodeActualIndex < 0)");
    builder.addLine("            break;");
    builder.addLine("          /* Try to find the URL in the actual style */");
    builder.addLine("          nodeActualIndex = nodeActualValue.indexOf('url(\"http');");
    builder.addLine("          if (nodeActualIndex < 0)");
    builder.addLine("            break;");
    builder.addLine("          nodeActualUrl = nodeActualValue.substr(nodeActualIndex+5);");
    builder.addLine("          /* Get rid of the trailing part of the URL */");
    builder.addLine("          nodeActualIndex = nodeActualUrl.indexOf('\")');");
    builder.addLine("          if (nodeActualIndex < 0)");
    builder.addLine("            break");
    builder.addLine("          nodeActualUrl = nodeActualUrl.substring(0, nodeActualIndex);");
    builder.addLine("          nodeActualUrl = HDLmRemoveProtocol(nodeActualUrl);");
    builder.addLine("          /* Try to get the perceptual hash value for the current URL */");
    builder.addLine("          nodeActualPHash = HDLmFindPHash(nodeActualUrl);");
    builder.addLine("          if (nodeActualPHash == null) {");
    builder.addLine("            HDLmGetPHash(nodeActualUrl);");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("          /* Get the current node attributes perceptual hash expected value");
    builder.addLine("             from the node attributes passed by the caller. Note that this");
    builder.addLine("             is an expected value. This should be a 64-bit number returned");
    builder.addLine("             as a 16-digit hexadecimal string. */");
    builder.addLine("          if (nodeAttributes.hasOwnProperty('phash') == false)");
    builder.addLine("            break;");
    builder.addLine("          let nodeAttributesPHashValue = nodeAttributes['phash'];");
    builder.addLine("          /* At this point we can compare the perceptual hash values and");
    builder.addLine("             show that a perceptual hash comparison was completed */");
    builder.addLine("          nodeAttributesPHashSimilarity = HDLmHammingDistanceAdjusted(nodeAttributesPHashValue,");
    builder.addLine("                                                                      nodeActualPHash);");
    builder.addLine("          nodePHashCheck = true;");
    builder.addLine("          /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("             perceptual hash values, if need be. */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("            let   errorText;");
    builder.addLine("            /* Check if node identifier tracing should actually happen */");
    builder.addLine("            if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("                (nodeIdenTracing == HDLmNodeIdenTracing.error && nodeAttributesPHashSimilarity >= 0.10)) {");
    builder.addLine("              errorText = `Node identifier - key (perceptual hash) actual (${nodeActualPHash}) expected (${nodeAttributesPHashValue})`;");
    builder.addLine("              HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("              errorText = `Node identifier - key (perceptual hash) comparison value (${nodeAttributesPHashSimilarity})`;");
    builder.addLine("              HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            }");
    builder.addLine("          }");
    builder.addLine("          /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("             attribute values, if need be. */");
    builder.addLine("          if (postRuleTracing) {");
    builder.addLine("            nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("            nodeAttributeCheck.attributevalue = nodeAttributesPHashValue;");
    builder.addLine("            nodeAttributeCheck.actualvalue = nodeActualPHash;");
    builder.addLine("            nodeAttributeCheck.matchvalue = nodeAttributesPHashSimilarity;");
    builder.addLine("            nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("          }");
    builder.addLine("          /* Check if the perceptual hash simularity value is low enough */");
    builder.addLine("          if (nodeAttributesPHashSimilarity < 0.10) {");
    builder.addLine("            numeratorIncrementValue = 1.0;");
    builder.addLine("          }");
    builder.addLine("          break;");
    builder.addLine("        }");
    builder.addLine("        /* The code below only needs to be executed if we were not able");
    builder.addLine("           to compare the perceptual hash values */");
    builder.addLine("        if (nodePHashCheck == false) {");
    builder.addLine("          /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("             attribute values, if need be. */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("            let   errorText;");
    builder.addLine("            let   traceValue = 0.0;");
    builder.addLine("            if (nodeActualValue != null &&");
    builder.addLine("                nodeAttributeValue == nodeActualValue)");
    builder.addLine("              traceValue = 1.0;");
    builder.addLine("            /* Check if node identifier tracing should actually happen */");
    builder.addLine("            if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("                (nodeIdenTracing == HDLmNodeIdenTracing.error && traceValue != 1.0)) {");
    builder.addLine("              errorText = `Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("              HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("              errorText = `Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("              HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            }");
    builder.addLine("          }");
    builder.addLine("          /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("             attribute values, if need be. */");
    builder.addLine("          if (postRuleTracing) {");
    builder.addLine("            nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("            nodeAttributeCheck.attributevalue = nodeAttributeValue;");
    builder.addLine("            nodeAttributeCheck.actualvalue = nodeActualValue;");
    builder.addLine("            let   traceValue = 0.0;");
    builder.addLine("            if (nodeActualValue != null &&");
    builder.addLine("                nodeAttributeValue == nodeActualValue)");
    builder.addLine("              traceValue = 1.0;");
    builder.addLine("            nodeAttributeCheck.matchvalue = traceValue;");
    builder.addLine("            nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("          }");
    builder.addLine("          /* If we don't have a value that we can compare, then we are done */");
    builder.addLine("          if (nodeActualValue == null)");
    builder.addLine("            continue;");
    builder.addLine("          /* Compare the expected value and the actual value. If they are the");
    builder.addLine("             same, then we can increment the numerator. */");
    builder.addLine("          if (nodeAttributeValue == nodeActualValue)");
    builder.addLine("            numeratorIncrementValue = 1.0;");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      /* For all other attributes, we can just extract the actual");
    builder.addLine("         attribute value from the DOM element */");
    builder.addLine("      else {");
    builder.addLine("        nodeActualValue = nodeElement.getAttribute(nodeAttributeKey);");
    builder.addLine("        /* Check if the attribute we want is href. Special case code");
    builder.addLine("           is needed for handling href. Basically, we need to remove");
    builder.addLine("           the protocol and host before we do any matching on href.");
    builder.addLine("           This was done in building the node identifier. */");
    builder.addLine("        if (nodeAttributeKey == 'href' &&");
    builder.addLine("            nodeActualValue != null)");
    builder.addLine("          nodeActualValue = HDLmRemoveHost(nodeActualValue);");
    builder.addLine("        /* Check if node identifier tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("            nodeIdenTracing == HDLmNodeIdenTracing.error) {");
    builder.addLine("          let   errorText;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              nodeAttributeValue == nodeActualValue)");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          /* Check if node identifier tracing should actually happen */");
    builder.addLine("          if (nodeIdenTracing == HDLmNodeIdenTracing.all ||");
    builder.addLine("              (nodeIdenTracing == HDLmNodeIdenTracing.error && traceValue != 1.0)) {");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("            errorText = `Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("            HDLmBuildError('Trace', 'NodeIden', 41, errorText);");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("        /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("           attribute values, if need be. */");
    builder.addLine("        if (postRuleTracing) {");
    builder.addLine("          nodeAttributeCheck.type = nodeAttributeKey;");
    builder.addLine("          nodeAttributeCheck.attributevalue = nodeAttributeValue;");
    builder.addLine("          nodeAttributeCheck.actualvalue = nodeActualValue;");
    builder.addLine("          let   traceValue = 0.0;");
    builder.addLine("          if (nodeActualValue != null &&");
    builder.addLine("              nodeAttributeValue == nodeActualValue)");
    builder.addLine("            traceValue = 1.0;");
    builder.addLine("          nodeAttributeCheck.matchvalue = traceValue;");
    builder.addLine("          nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("        }");
    builder.addLine("        /* If we don't have a value that we can compare, then we are done */");
    builder.addLine("        if (nodeActualValue == null)");
    builder.addLine("          continue;");
    builder.addLine("        /* Compare the expected value and the actual value. If they are the");
    builder.addLine("           same, then we can increment the numerator. */");
    builder.addLine("        if (nodeAttributeValue == nodeActualValue)");
    builder.addLine("          numeratorIncrementValue = 1.0;");
    builder.addLine("      }");
    builder.addLine("      /* Possibly increment the numerator */");
    builder.addLine("      numerator += numeratorIncrementValue;");
    builder.addLine("    }");
    builder.addLine("    /* Check if post rule tracing is active or not. Trace the");
    builder.addLine("       attribute values, if need be. */");
    builder.addLine("    if (postRuleTracing)");
    builder.addLine("      postTrace[postName] = nodeAttributeChecks;");
    builder.addLine("    return numerator / denominator;");
    builder.addLine("  }");
    builder.addLine("  /* Start the JavaScript function that handles just one find. Note");
    builder.addLine("     that this function is always passed a list (array) of nodes.");
    builder.addLine("     The list may have just one entry and that one entry may be");
    builder.addLine("     the document level node. This is not an error condition. */");
    builder.addLine("  function HDLmFindOneLevel(nodeList, findEntry) {");
    builder.addLine("    let outArray = [];");
    builder.addLine("    /* Check all of the nodes pass by the caller. The caller may");
    builder.addLine("       have passed an array with just one node (the document). */");
    builder.addLine("    let nodeListLength = nodeList.length;");
    builder.addLine("    for (let i = 0; i < nodeListLength; i++) {");
    builder.addLine("      let curNode = nodeList[i];");
    builder.addLine("      let nodeType = curNode.constructor.name;");
    builder.addLine("      /* Try to search by ID. This should be the fastest and best");
    builder.addLine("         approach. ID values are supposed to be unique for an entire");
    builder.addLine("         HTML page. Hence, this code should return just one value.");
    builder.addLine("         Note that the code below does not test the tag value in");
    builder.addLine("         the first stage. */");
    builder.addLine("        if (typeof curNode.getElementById === 'function' &&");
    builder.addLine("            findEntry.attributeName       === 'id' &&");
    builder.addLine("            findEntry.attributeValue      !== '') {");
    builder.addLine("        /* Search by ID value. The call below may or may not return an");
    builder.addLine("           array. It may just return one node. Note that the documentation");
    builder.addLine("           says that the call below will only return one element or a");
    builder.addLine("           null value if no matching elements are found. */");
    builder.addLine("        let newNode = curNode.getElementById(findEntry.attributeValue);");
    builder.addLine("        /* Check if we actually got an element back from the caller.");
    builder.addLine("           We can only check/use the element, if we actually got one. */");
    builder.addLine("        if (newNode !== null) {");
    builder.addLine("          if (findEntry.tag !== '') {");
    builder.addLine("            if (findEntry.tag.toUpperCase() === newNode.tagName.toUpperCase())");
    builder.addLine("              outArray.push(newNode);");
    builder.addLine("          }");
    builder.addLine("          else");
    builder.addLine("            outArray.push(newNode);");
    builder.addLine("        }");
    builder.addLine("        continue;");
    builder.addLine("      }");
    builder.addLine("      /* Try to search by class name. This should be a relatively fast");
    builder.addLine("         approach. Class name values are not required to be unique for");
    builder.addLine("         an entire HTML page. Hence, this code will always return an");
    builder.addLine("         array-like value. The array-like value may or may not be empty.");
    builder.addLine("         Note that the code below does not test the tag value in the");
    builder.addLine("         first stage. */");
    builder.addLine("      if (typeof curNode.getElementByClassName === 'function' &&");
    builder.addLine("          findEntry.attributeName              === 'class'    &&");
    builder.addLine("          findEntry.attributeValue             !== '') {");
    builder.addLine("        /* Search by class name value. The call below will always return an");
    builder.addLine("           array-like object. The array-like object may or may not be empty. */");
    builder.addLine("        let newNodeList = curNode.getElementByClassName(findEntry.attributeValue);");
    builder.addLine("        let newNodeListLength = newNodeList.length;");
    builder.addLine("        for (let i = 0; i < newNodeListLength; i++) {");
    builder.addLine("          newNode = newNodeList[i];");
    builder.addLine("          if (findEntry.tag !== '') {");
    builder.addLine("            if (findEntry.tag.toUpperCase() === newNode.tagName.toUpperCase())");
    builder.addLine("              outArray.push(newNode);");
    builder.addLine("          }");
    builder.addLine("          else");
    builder.addLine("            outArray.push(newNode);");
    builder.addLine("        }");
    builder.addLine("        continue;");
    builder.addLine("      }");
    builder.addLine("      /* Try to search by tag name. This should be a relatively fast");
    builder.addLine("         approach. Tag name values are not required to be unique for");
    builder.addLine("         an entire HTML page. Hence, this code will always return an");
    builder.addLine("         array-like value. The array-like value may or may not be empty. */");
    builder.addLine("      if (typeof curNode.getElementsByTagName === 'function' &&");
    builder.addLine("          findEntry.tag !== '') {");
    builder.addLine("        let newNodesList = curNode.getElementsByTagName(findEntry.tag);");
    builder.addLine("        let newNodesListLength = newNodesList.length;");
    builder.addLine("        /* Now that we have a list of nodes with the correct tag");
    builder.addLine("           name, we need to keep checking */");
    builder.addLine("        if (findEntry.attributeName  !== '' &&");
    builder.addLine("            findEntry.attributeValue !== '') {");
    builder.addLine("          for (let i = 0; i < newNodesListLength; i++) {");
    builder.addLine("            let newNode = newNodesList[i];");
    builder.addLine("            if (!newNode.hasAttribute(findEntry.attributeName))");
    builder.addLine("              continue;");
    builder.addLine("            if (newNode.getAttribute(findEntry.attributeName) !== findEntry.attributeValue)");
    builder.addLine("              continue;");
    builder.addLine("            outArray.push(newNode);");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("        /* Since the attribute name and value were not set, we can");
    builder.addLine("           just copy all of the nodes into the output node list */");
    builder.addLine("        else {");
    builder.addLine("          for (let i = 0; i < newNodesListLength; i++) {");
    builder.addLine("            let newNode = newNodesList[i];");
    builder.addLine("            outArray.push(newNode);");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("        continue;");
    builder.addLine("      }");
    builder.addLine("      /* At this point, we must scan the subnodes of the current node.");
    builder.addLine("         This is messy and slow, but we don't have any other choice. */");
    builder.addLine("      let curChildren = curNode.childNodes;");
    builder.addLine("      let curChildrenLength = curChildren.length;");
    builder.addLine("      for (let i = 0; i < curChildrenLength; i++) {");
    builder.addLine("        let curChild = curChildren[i];");
    builder.addLine("        if (typeof curChild.hasAttribute !== 'function')");
    builder.addLine("          continue;");
    builder.addLine("        if (typeof curChild.getAttribute !== 'function')");
    builder.addLine("          continue;");
    builder.addLine("        if (!curChild.hasAttribute(findEntry.attributeName))");
    builder.addLine("          continue;");
    builder.addLine("        if (curChild.getAttribute(findEntry.attributeName) !== findEntry.attributeValue)");
    builder.addLine("          continue;");
    builder.addLine("        outArray.push(curChild);");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("    return outArray;");
    builder.addLine("  }");
    builder.addLine("  /* Start the JavaScript function that tries to find perceptual hash");
    builder.addLine("     value for a URL. This routine returns null if no matching URL is");
    builder.addLine("     found. This is not an error condition. Note that the URL string");
    builder.addLine("     is modified by changing the plus signs to blanks. This step is");
    builder.addLine("     required. */");
    builder.addLine("  function HDLmFindPHash(urlStr) {");
    builder.addLine("    let urlStrMod = urlStr.replace(/\\+/g, ' ');");
    builder.addLine("    if (HDLmPHashObject.hasOwnProperty(urlStrMod))");
    builder.addLine("      return HDLmPHashObject[urlStrMod];");
    builder.addLine("    return null;");
    builder.addLine("  }");
    builder.addLine("  /* Get all of the property names for an object and return the");
    builder.addLine("     list to the caller. Many of the property names will be");
    builder.addLine("     associated with objects that the object in question inherits");
    builder.addLine("     from. This routine get the prototype of each object repeatedly");
    builder.addLine("     to get all of the property names. */");
    builder.addLine("  function HDLmGetAllPropertyNames(obj) {");
    builder.addLine("    let result = new Set();");
    builder.addLine("    while (obj) {");
    builder.addLine("      Object.getOwnPropertyNames(obj).forEach(p => result.add(p));");
    builder.addLine("      obj = Object.getPrototypeOf(obj);");
    builder.addLine("    }");
    builder.addLine("    return [...result];");
    builder.addLine("  }");
    builder.addLine("  /* The code below tries to find and fix the URL used for the");
    builder.addLine("     background-image. This is not always possible. But some of");
    builder.addLine("     the times it possible to replace the background image. */");
    builder.addLine("  function HDLmGetBackground(domElement, replacementImageName) {");
    builder.addLine("    let firstElement = domElement;");
    builder.addLine("    let finalUrl = null;");
    builder.addLine("    while (domElement != null) {");
    builder.addLine("      /* Try to get the computer style from the computed element.");
    builder.addLine("         If no computed style is available, then we are done. */");
    builder.addLine("      let computedStyle = window.getComputedStyle(domElement);");
    builder.addLine("      if (computedStyle == null)");
    builder.addLine("        break;");
    builder.addLine("      /* Try to get the background image from the computed style.");
    builder.addLine("         Not all computed styles have a background image. */");
    builder.addLine("      let backStr = computedStyle['background-image'];");
    builder.addLine("      let backType = typeof backStr;");
    builder.addLine("      /* Check if we have a backgroup image that is a URL. If");
    builder.addLine("         not, we search parent elements until we find an element");
    builder.addLine("         that has a URL background image. */");
    builder.addLine("      if (backStr == null || backStr == 'none' || backType != 'string' || backStr.indexOf('url(') < 0) {");
    builder.addLine("        domElement = domElement.parentElement;");
    builder.addLine("        continue;");
    builder.addLine("      }");
    builder.addLine("      /* Handle the background image URL */");
    builder.addLine("      let backLast = backStr.lastIndexOf('/');");
    builder.addLine("      if (backLast > 0)");
    builder.addLine("        finalUrl = backStr.substring(0, backLast+1) + replacementImageName + '\")';");
    builder.addLine("      break;");
    builder.addLine("    }");
    builder.addLine("    return finalUrl;");
    builder.addLine("  }");
    builder.addLine("  /* Get the JSON version of the current event object. Note that");
    builder.addLine("     this routine obtains the inherited properties along with the");
    builder.addLine("     properties of the event object. */");
    builder.addLine("  function HDLmGetJsonForEventObject(obj, objName, hostName, pathName, sessionId) {");
    builder.addLine("    /* Get all of the property names */");
    builder.addLine("    let objProps = HDLmGetAllPropertyNames(obj);");
    builder.addLine("    /* Start the output JSON. Add some information that is always needed. */");
    builder.addLine("    let rv = '{\"eventName\":\"' + objName + '\"' + ',\"hostName\":\"' + hostName + '\"' + ',\"pathName\":\"' + pathName + '\"';");
    builder.addLine("    rv += ',\"sessionId\":\"' + sessionId + '\"';");
    builder.addLine("    /* Handle each property */");
    builder.addLine("    objProps.forEach(prop => {");
    builder.addLine("      let objValue = obj[prop];");
    builder.addLine("      let typeValue = typeof objValue;");
    builder.addLine("      let quotes = true;");
    builder.addLine("      /* Check if we need quotes. In some very real cases, we do not. */");
    builder.addLine("      if (typeValue == 'number'  ||");
    builder.addLine("          typeValue == 'boolean' ||");
    builder.addLine("          objValue == null)");
    builder.addLine("        quotes = false;");
    builder.addLine("      /* Check for a very special case. Some strings are really objects.");
    builder.addLine("         Check the first and last character. If they show that we are");
    builder.addLine("         really handling an object, set the quotes flag to false. */");
    builder.addLine("      if (typeValue == 'string') {");
    builder.addLine("        let lengthValue = objValue.length;");
    builder.addLine("        if (lengthValue >= 2) {");
    builder.addLine("          let objValueFirst = objValue.charAt(0);");
    builder.addLine("          let objValueLast = objValue.charAt(lengthValue-1);");
    builder.addLine("          if (objValueFirst == '{' && objValueLast == '}') {");
    builder.addLine("            /* console.log(prop); */");
    builder.addLine("            /* console.log(objValue); */");
    builder.addLine("            /* console.log(typeValue); */");
    builder.addLine("            quotes = false;");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      /* Get the current length of the return value string. This value");
    builder.addLine("         is needed later. */");
    builder.addLine("      let oldRvLength = rv.length;");
    builder.addLine("      /* In some cases, adding a value to the return value string causes an");
    builder.addLine("         exception. Why is very unclear. Howwever, we need to allow for this");
    builder.addLine("         (quite rare) case and handle it. */");
    builder.addLine("      try {");
    builder.addLine("        rv += ',\"' + prop + '\":';");
    builder.addLine("        if (quotes)");
    builder.addLine("          rv += '\"';");
    builder.addLine("        rv += objValue;");
    builder.addLine("        if (quotes)");
    builder.addLine("          rv += '\"';");
    builder.addLine("      }");
    builder.addLine("      /* Is we get an exception, the code below will gain control. This code");
    builder.addLine("         removes the comma and the property name. */");
    builder.addLine("      catch (errorObj) {");
    builder.addLine("        /* console.log(errorObj); */");
    builder.addLine("        /* console.log(prop); */");
    builder.addLine("        /* console.log(obj); */");
    builder.addLine("        /* console.log(typeValue); */");
    builder.addLine("        /* console.log(objValue); */");
    builder.addLine("        /* Reset the length to remove the comma and the property");
    builder.addLine("           name. Of course, quotes are removed as well. */");
    builder.addLine("        rv = rv.substring(0, oldRvLength);");
    builder.addLine("      }");
    builder.addLine("    });");
    builder.addLine("    rv += '}';");
    builder.addLine("    return rv;");
    builder.addLine("  }");
    builder.addLine("  /* Get the JSON version of the current link */");
    builder.addLine("  function HDLmGetJsonForLink(link, hostName, pathName, sessionId) {");
    builder.addLine("    let rv = '{\"link\":\"' + link + '\"' + ',\"hostName\":\"' + hostName + '\"' + ',\"pathName\":\"' + pathName + '\"';");
    builder.addLine("    rv += ',\"sessionId\":\"' + sessionId + '\"';");
    builder.addLine("    rv += '}';");
    builder.addLine("    return rv;");
    builder.addLine("  }");
    builder.addLine("  /* Get the name of the current object. Return the object name");
    builder.addLine("     to the caller. */");
    builder.addLine("  function HDLmGetObjectName(obj) {");
    builder.addLine("    let result = obj.constructor.name;");
    builder.addLine("    return result;");
    builder.addLine("  }");
    builder.addLine("  /* This JavaScript function gets the current update count value.");
    builder.addLine("     If a count value does not already exist, then a count value");
    builder.addLine("     of zero is returned to the caller. If the count value already");
    builder.addLine("     exists, then it is returned to the caller. */");
    builder.addLine("  function HDLmGetUpdateCount(curNode, matchModifiedName, readyState) {");
    builder.addLine("    /* console.log('In HDLmGetUpdateCount()'); */");
    builder.addLine("    /* console.log(readyState); */");
    builder.addLine("    /* The attribute name is in all lowercase. It appears that");
    builder.addLine("       Windows forces all attribute names to be in lower case. */");
    builder.addLine("    let attributeName = 'hdlmupdated'+matchModifiedName;");
    builder.addLine("    if (curNode.hasAttribute(attributeName) == false) {");
    builder.addLine("      /* console.log('H G U 0'); */");
    builder.addLine("      return 0;");
    builder.addLine("    }");
    builder.addLine("    /* Get the current count value and return it to the caller */");
    builder.addLine("    let currentCount = curNode.getAttribute(attributeName);");
    builder.addLine("    /* console.log('H G U', currentCount); */");
    builder.addLine("    return currentCount;");
    builder.addLine("  }");
    builder.addLine("  /* This is the base Hamming Distance algorithm. This code");
    builder.addLine("     assumes both input values can be stored in a JavaScript");
    builder.addLine("     variables. */");
    builder.addLine("  function HDLmHammingDistance(firstVal, secondVal) {");
    builder.addLine("    /* Run the first XOR */");
    builder.addLine("    let xorValue = firstVal ^ secondVal;");
    builder.addLine("    let distanceCount = 0;");
    builder.addLine("    /* Find all of the bit mismatches */");
    builder.addLine("    while (xorValue > 0) {");
    builder.addLine("      xorValue &= xorValue - 1;");
    builder.addLine("      distanceCount++;");
    builder.addLine("    }");
    builder.addLine("    /* Return the final count value */");
    builder.addLine("    return distanceCount;");
    builder.addLine("  };");
    builder.addLine("  /* This is the adjusted Hamming Distance algorithm. This code");
    builder.addLine("     assumes both input values are (possibly long) hexadecimal");
    builder.addLine("     strings. These strings can be of any length. However, they");
    builder.addLine("     must be the same length. The Hamming Distance is calculated");
    builder.addLine("     and then divided by the length of each input string in bits. */");
    builder.addLine("  function HDLmHammingDistanceAdjusted(firstVal, secondVal) {");
    builder.addLine("    /* Run the raw Hamming Distance value */");
    builder.addLine("    let distanceValue = HDLmHammingDistanceLong(firstVal, secondVal);");
    builder.addLine("    /* Return the final distance value */");
    builder.addLine("    return distanceValue/(4.0 * firstVal.length);");
    builder.addLine("  };");
    builder.addLine("  /* This is the long Hamming Distance algorithm. This code");
    builder.addLine("     assumes both input values are (possibly long) hexadecimal");
    builder.addLine("     strings. These strings can be of any length. However, they");
    builder.addLine("     must be the same length. */");
    builder.addLine("  function HDLmHammingDistanceLong(firstVal, secondVal) {");
    builder.addLine("    let distanceFinal = 0;");
    builder.addLine("    let firstSub, secondSub;");
    builder.addLine("    /* Process each string value */");
    builder.addLine("    while (firstVal.length > 0) {");
    builder.addLine("      /* Check if we have more than 8 hexadecimal digits left */");
    builder.addLine("      if (firstVal.length > 8) {");
    builder.addLine("        firstSub = firstVal.substr(0, 8);");
    builder.addLine("        firstVal = firstVal.substr(8);");
    builder.addLine("        secondSub = secondVal.substr(0, 8);");
    builder.addLine("        secondVal = secondVal.substr(8);");
    builder.addLine("      }");
    builder.addLine("      /* Use the first set of hexadecimal digits */");
    builder.addLine("      else {");
    builder.addLine("        firstSub = firstVal;");
    builder.addLine("        firstVal = '';");
    builder.addLine("        secondSub = secondVal;");
    builder.addLine("        secondVal = '';");
    builder.addLine("      }");
    builder.addLine("      /* Get the Hamming Distance between the two integers */");
    builder.addLine("      let firstInt = parseInt(firstSub, 16);");
    builder.addLine("      let secondInt = parseInt(secondSub, 16);");
    builder.addLine("      distanceFinal += HDLmHammingDistance(firstInt, secondInt);");
    builder.addLine("    }");
    builder.addLine("    /* Return the final count value */");
    builder.addLine("    return distanceFinal;");
    builder.addLine("  };");
    builder.addLine("  /* This function handles a visit request. The actual visit");
    builder.addLine("     is skipped, if we are in test mode or if the count is");
    builder.addLine("     too high. */");
    builder.addLine("  function HDLmHandleVisitRequest(visitText, postTrace, testFlag, sessionIndexValueUsed,");
    builder.addLine("                                  sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                                  parameterNumber, lookupValue,");
    builder.addLine("                                  hostNameValue, divisionNameValue, siteNameValue, curMod,");
    builder.addLine("                                  pathValueStr) {");
    builder.addLine("    /* Assume that the count is not too high. This assumption may");
    builder.addLine("       not be correct. */");
    builder.addLine("    let countHigh = false;");
    builder.addLine("    /* Get the update count for the document and the current rule */");
    builder.addLine("    let updateName = 'HDLmUpdateCount' + curMod.name;");
    builder.addLine("    if (isNaN(window[updateName]))");
    builder.addLine("      window[updateName] = 0;");
    builder.addLine("    else");
    builder.addLine("      if (window[updateName] > 0)");
    builder.addLine("        countHigh = true;");
    builder.addLine("    /* If we are in test mode or if the count is too");
    builder.addLine("       high, just return to the caller at this point */");
    builder.addLine("    if (countHigh || testFlag)");
    builder.addLine("      return countHigh;");
    builder.addLine("    window[updateName] += 1;");
    builder.addLine("    let localUpdates = new Object();");
    builder.addLine("    let oldText = null;");
    builder.addLine("    let newText = null;");
    builder.addLine("    if ((typeof visitText) != 'undefined' &&");
    builder.addLine("        visitText          != null        &&");
    builder.addLine("        visitText          != '')");
    builder.addLine("      oldText = visitText;");
    builder.addLine("    HDLmSaveChange(localUpdates, sessionIndexValueUsed,");
    builder.addLine("                   sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                   parameterNumber, lookupValue,");
    builder.addLine("                   hostNameValue, divisionNameValue, siteNameValue, curMod.name,");
    builder.addLine("                   curMod.path, curMod.type, pathValueStr,");
    builder.addLine("                   oldText, newText);");
    builder.addLine("    /* Set the local reason value to something appropriate */");
    builder.addLine("    let localReason = curMod.type;");
    builder.addLine("    postTrace.matcherror = 'fired';");
    builder.addLine("    HDLmSendUpdates(localUpdates, localReason, '1.0', postTrace);");
    builder.addLine("    return countHigh;");
    builder.addLine("  }");
    builder.addLine("  /* This JavaScript function updates (increments) count");
    builder.addLine("     values. If a count value does not already exist, then");
    builder.addLine("     the count value is set to one. If the count value");
    builder.addLine("     already exists, then it is incremented.by one. */");
    builder.addLine("  function HDLmIncrementUpdateCount(curNode, matchModifiedName) {");
    builder.addLine("    /* console.log('In HDLmIncrementUpdateCount()'); */");
    builder.addLine("    /* The attribute name is in all lowercase. It appears that");
    builder.addLine("       Windows forces all attribute names to be in lower case. */");
    builder.addLine("    let attributeName = 'hdlmupdated'+matchModifiedName;");
    builder.addLine("    if (curNode.hasAttribute(attributeName) == false) {");
    builder.addLine("      curNode.setAttribute(attributeName, 1);");
    builder.addLine("      return 1;");
    builder.addLine("    }");
    builder.addLine("    /* Get the current count and increment the count */");
    builder.addLine("    let currentCount = curNode.getAttribute(attributeName);");
    builder.addLine("    currentCount++;");
    builder.addLine("    curNode.setAttribute(attributeName, currentCount);");
    builder.addLine("    return currentCount;");
    builder.addLine("  }");
    builder.addLine("  /* This JavaScript function changes some search text if need be.");
    builder.addLine("     Check if the inner text has a dollar sign in it. If so, remove the");
    builder.addLine("     inner text from the node identifier. Dollar values tend to change");
    builder.addLine("     a lot, so matching on them does not make sense. */");
    builder.addLine("  function HDLmModifySearch(searchText) {");
    builder.addLine("    let searchObj = JSON.parse(searchText);");
    builder.addLine("    let searchAttrs = searchObj.attributes;");
    builder.addLine("    if (searchAttrs.hasOwnProperty('innertext')) {");
    builder.addLine("      let searchInner = searchAttrs.innertext;");
    builder.addLine("      let searchIndex = searchInner.indexOf('$');");
    builder.addLine("      if (searchIndex >= 0) {");
    builder.addLine("        delete searchAttrs['innertext'];");
    builder.addLine("        searchObj['attributes'] = searchAttrs;");
    builder.addLine("      };");
    builder.addLine("      searchText = JSON.stringify(searchObj);");
    builder.addLine("    }");
    builder.addLine("    return searchText;");
    builder.addLine("  }");
    builder.addLine("  /* This JavaScript function obtains one value (tried to obtain one");
    builder.addLine("     value from the current HTML page. Of course, this value may not");
    builder.addLine("     be found. In that case, a null value will be returned to the caller. */");
    builder.addLine("  function HDLmObtainValue(searchValue) {");
    builder.addLine("    let textValue = null;");
    builder.addLine("    let localMod = {};");
    builder.addLine("    /* Check if the search value is a CSS selector, a set of node identifier,");
    builder.addLine("       or an XPath expression */");
    builder.addLine("    if (searchValue.length > 0 && searchValue.charAt(0) == '/') {");
    builder.addLine("      localMod.cssselector = '';");
    builder.addLine("      localMod.nodeiden = null;");
    builder.addLine("      localMod.xpath = searchValue;");
    builder.addLine("    }");
    builder.addLine("    else if (searchValue.length > 0 && searchValue.charAt(0) == '{') {");
    builder.addLine("      localMod.cssselector = '';");
    builder.addLine("      localMod.nodeiden = JSON.parse(searchValue);");
    builder.addLine("      localMod.xpath = \"\";");
    builder.addLine("    }");
    builder.addLine("    else {");
    builder.addLine("      localMod.cssselector = searchValue;");
    builder.addLine("      localMod.nodeiden = null;");
    builder.addLine("      localMod.xpath = '';");
    builder.addLine("    }");
    builder.addLine("    let localNodeList = HDLmFind(localMod, false, null, null);");
    builder.addLine("    let localNodeListLen = localNodeList.length;");
    builder.addLine("    /* Process all of the found nodes */");
    builder.addLine("    for (let i = 0; i < localNodeListLen; i++) {");
    builder.addLine("      let localNode = localNodeList[i];");
    builder.addLine("      textValue = localNode.textContent;");
    builder.addLine("      break;");
    builder.addLine("    }");
    builder.addLine("    return textValue;");
    builder.addLine("  }");
    builder.addLine("  /* The next routine takes an input URL and removes the protocol");
    builder.addLine("     and the host name from it (if they are present). The returned");
    builder.addLine("     value is the path string followed by the search string followed");
    builder.addLine("     by the fragment string. */");
    builder.addLine("  function HDLmRemoveHost(urlStr) {");
    builder.addLine("    /* Check if the passed URL string has a colon in it. If it does");
    builder.addLine("       not have a colon, then we can just return the input string");
    builder.addLine("       to the caller. */");
    builder.addLine("    let urlStrIndexOfColon = urlStr.indexOf(':');");
    builder.addLine("    if (urlStrIndexOfColon < 0 ||");
    builder.addLine("        urlStrIndexOfColon > 6)");
    builder.addLine("      return urlStr;");
    builder.addLine("    /* Build a URL object from the input string */");
    builder.addLine("    let urlObj = new URL(urlStr);");
    builder.addLine("    /* Return the part of the URL after the protocol, host name,");
    builder.addLine("       and port number */");
    builder.addLine("    return urlStr.substring(urlObj.origin.length);");
    builder.addLine("  }");
    builder.addLine("  /* The next routine takes an input URL and removes the protocol");
    builder.addLine("     from it (if it is present). The colon after the protocol is");
    builder.addLine("     also removed. The returned value is the two slashes followed");
    builder.addLine("     by everything after the two slashes. */");
    builder.addLine("  function HDLmRemoveProtocol(urlStr) {");
    builder.addLine("    /* Check if the passed URL string has a colon in it. If it does");
    builder.addLine("       not have a colon, then we can just return the input string");
    builder.addLine("       to the caller. */");
    builder.addLine("    let urlIndex = urlStr.indexOf(':');");
    builder.addLine("    if (urlIndex < 0)");
    builder.addLine("      return urlStr;");
    builder.addLine("    /* Return the part of the URL after the protocol and the colon */");
    builder.addLine("    return urlStr.substring(urlIndex+1);");
    builder.addLine("  }");
    builder.addLine("  /* The next routine replaces some characters in a string with");
    builder.addLine("     other characters. The replacement process produces a string");
    builder.addLine("     that can be used as an attribute name (among other things). */");
    builder.addLine("  function HDLmReplaceInString(inStr) {");
    builder.addLine("    /* The characters used below are Laotian letters. These values");
    builder.addLine("       were chosen because they are in the BMP, obscure (to most");
    builder.addLine("       English speakers), and do not cause exceptions. */");
    builder.addLine("    /* Handle a few uppercase letters */");
    builder.addLine("    inStr = inStr.replace(/A/g,'\u0e81');");
    builder.addLine("    inStr = inStr.replace(/B/g,'\u0e82');");
    builder.addLine("    inStr = inStr.replace(/C/g,'\u0e84');");
    builder.addLine("    inStr = inStr.replace(/D/g,'\u0e87');");
    builder.addLine("    inStr = inStr.replace(/E/g,'\u0e88');");
    builder.addLine("    inStr = inStr.replace(/F/g,'\u0e8a');");
    builder.addLine("    inStr = inStr.replace(/G/g,'\u0e8d');");
    builder.addLine("    inStr = inStr.replace(/H/g,'\u0e94');");
    builder.addLine("    inStr = inStr.replace(/I/g,'\u0e97');");
    builder.addLine("    inStr = inStr.replace(/J/g,'\u0e99');");
    builder.addLine("    inStr = inStr.replace(/K/g,'\u0e9f');");
    builder.addLine("    inStr = inStr.replace(/L/g,'\u0ea1');");
    builder.addLine("    inStr = inStr.replace(/M/g,'\u0ea3');");
    builder.addLine("    inStr = inStr.replace(/N/g,'\u0ea5');");
    builder.addLine("    inStr = inStr.replace(/O/g,'\u0ea7');");
    builder.addLine("    inStr = inStr.replace(/P/g,'\u0eaa');");
    builder.addLine("    inStr = inStr.replace(/Q/g,'\u0eab');");
    builder.addLine("    inStr = inStr.replace(/R/g,'\u0ead');");
    builder.addLine("    inStr = inStr.replace(/S/g,'\u0eb9');");
    builder.addLine("    inStr = inStr.replace(/T/g,'\u0ebb');");
    builder.addLine("    inStr = inStr.replace(/U/g,'\u0ebd');");
    builder.addLine("    inStr = inStr.replace(/V/g,'\u0ec0');");
    builder.addLine("    inStr = inStr.replace(/W/g,'\u0ec4');");
    builder.addLine("    inStr = inStr.replace(/X/g,'\u0ec6');");
    builder.addLine("    inStr = inStr.replace(/Y/g,'\u0ec8');");
    builder.addLine("    inStr = inStr.replace(/Z/g,'\u0ecd');");
    builder.addLine("    /* Handle a few other characters */");
    builder.addLine("    inStr = inStr.replace(/\\s/g,'\u0ed0');");
    builder.addLine("    inStr = inStr.replace(/\\$/g,'\u0ed1');");
    builder.addLine("    inStr = inStr.replace(/\\./g,'\u0ed2');");
    builder.addLine("    inStr = inStr.replace(/\\//g,'\u0ed3');");
    builder.addLine("    inStr = inStr.replace(/\\(/g,'\u0ed4');");
    builder.addLine("    inStr = inStr.replace(/\\)/g,'\u0ed5');");
    builder.addLine("    return inStr;");
    builder.addLine("  }");
    builder.addLine("  /* This function resets the enablement status of a");
    builder.addLine("     set of style sheets to a value passed by the caller */");
    builder.addLine("  function HDLmResetStyleSheetEnablement(titleValue, disabledStatus) {");
    builder.addLine("    /* Find the style sheet */");
    builder.addLine("    var styleSheetList = document.styleSheets;");
    builder.addLine("    for (let i = 0; i < styleSheetList.length; i++) {");
    builder.addLine("      var styleSheet = styleSheetList[i];");
    builder.addLine("      if (styleSheet.title != null &&");
    builder.addLine("        styleSheet.title == titleValue) {");
    builder.addLine("        styleSheet.disabled = disabledStatus;");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("  }");
    builder.addLine("  /* This JavaScript function saves one change from one modification */");
    builder.addLine("  function HDLmSaveChange(savedUpdates, indexUsed,");
    builder.addLine("                          sessionIndexValue, parametersArray, sessionIdJS,");
    builder.addLine("                          parmNumber, lookupValue,");
    builder.addLine("                          hostNameValue, divisionNameValue, siteNameValue, modName,");
    builder.addLine("                          modPathValue, modType,");
    builder.addLine("                          pathValue, oldValue, newValue) {");
    builder.addLine("    /* Save all of the values passed by the caller in a local");
    builder.addLine("       object */");
    builder.addLine("    let updateObj = {};");
    builder.addLine("    updateObj.indexValue = sessionIndexValue;");
    builder.addLine("    updateObj.indexUsed = indexUsed;");
    builder.addLine("    updateObj.parameters = HDLmArrayJoin(parametersArray, ' ');");
    builder.addLine("    updateObj.sessionId = sessionIdJS;");
    builder.addLine("    updateObj.parmNumber = parmNumber;");
    builder.addLine("    updateObj.lookupValue = lookupValue;");
    builder.addLine("    updateObj.hostName = hostNameValue;");
    builder.addLine("    updateObj.divisionName = divisionNameValue;");
    builder.addLine("    updateObj.siteName = siteNameValue;");
    builder.addLine("    updateObj.modName = modName;");
    builder.addLine("    updateObj.modPathValue = modPathValue;");
    builder.addLine("    updateObj.modType = modType;");
    builder.addLine("    updateObj.pathValue = pathValue;");
    builder.addLine("    updateObj.oldValue = oldValue;");
    builder.addLine("    updateObj.newValue = newValue;");
    builder.addLine("    /* Check if the updates array has already been created. Create the");
    builder.addLine("       updates array if need be. */");
    builder.addLine("    if (!savedUpdates.hasOwnProperty('updates'))");
    builder.addLine("      savedUpdates.updates = [];");
    builder.addLine("    /* Add the current update to the updates array */");
    builder.addLine("    savedUpdates.updates.push(updateObj);");
    builder.addLine("  }");
    builder.addLine("  /* This function takes a 'style' (without the quotes)");
    builder.addLine("     value and returns an array with each individual");
    builder.addLine("     style value. If the input only had one style value,");
    builder.addLine("     then the array will only have one entry. If the");
    builder.addLine("     input had several style values, then the output array");
    builder.addLine("     will have several entries. Each entry will be a valid");
    builder.addLine("     style value (or 'none' without the quotes). */");
    builder.addLine("  function HDLmStyleFixValues(inputStyles) {");
    builder.addLine("    /* console.log('In HDLmStyleFixValue'); */");
    builder.addLine("    /* console.log(typeof(inputStyles)); */");
    builder.addLine("    /* console.log(inputStyles); */");
    builder.addLine("    /* Get rid of any leading and trailing blanks */");
    builder.addLine("    inputStyles = inputStyles.trim();");
    builder.addLine("    /* Get a lowercase version of this string */");
    builder.addLine("    inputStyles = inputStyles.toLowerCase();");
    builder.addLine("    /* Change all sequences of whitespace to blanks */");
    builder.addLine("    inputStyles = inputStyles.replace(/\\s+/g, ' ');");
    builder.addLine("    /* The style string may or may not contain one or more");
    builder.addLine("       semicolons. Semicolons mean that we should split the");
    builder.addLine("       style string on semicolons, not blanks. */");
    builder.addLine("    let splitOn;");
    builder.addLine("    if (inputStyles.indexOf(';') >= 0)");
    builder.addLine("      splitOn = ';'");
    builder.addLine("    else");
    builder.addLine("      splitOn = ' ';");
    builder.addLine("    /* Split the input string, as need be */");
    builder.addLine("    let inputSplit = inputStyles.split(splitOn);");
    builder.addLine("    for (let i in inputSplit) {");
    builder.addLine("      let styleValue = inputSplit[i];");
    builder.addLine("      /* Check if we split on semicolons. Special case");
    builder.addLine("         code is needed to handle this case. */");
    builder.addLine("      if (splitOn == ';')");
    builder.addLine("        styleValue = styleValue.trim();");
    builder.addLine("      /* Check for some value that really means we should");
    builder.addLine("         use the existing value. In other words, we should");
    builder.addLine("         not specify a new value. */");
    builder.addLine("      if (styleValue == 'unchanged' ||");
    builder.addLine("          styleValue == 'novalue'   ||");
    builder.addLine("          styleValue == 'none'      ||");
    builder.addLine("          styleValue.trim().length == 0) {");
    builder.addLine("        styleValue = 'none';");
    builder.addLine("        inputSplit[i] = styleValue;");
    builder.addLine("      }");
    builder.addLine("      /* Check for some value that is missing the pixel suffix.");
    builder.addLine("         Add the pixel suffix, if need be. */");
    builder.addLine("      if (Number.isInteger(Number(styleValue)) == true) {");
    builder.addLine("        styleValue += 'px';");
    builder.addLine("        inputSplit[i] = styleValue;");
    builder.addLine("      }");
    builder.addLine("    }");
    builder.addLine("    return inputSplit;");
    builder.addLine("  }");
    builder.addLine("  /* This function takes a string value value and returns");
    builder.addLine("     an array with each individual word in the string. For");
    builder.addLine("     now we are using this function to split up curModExtra");
    builder.addLine("     values. Of course, this function could be used for anything. */");
    builder.addLine("  function HDLmStyleSplitString(inputString) {");
    builder.addLine("    /* Get rid of any leading and trailing blanks */");
    builder.addLine("    inputString = inputString.trim();");
    builder.addLine("    /* Get a lowercase version of this string */");
    builder.addLine("    inputString = inputString.toLowerCase();");
    builder.addLine("    /* Change all sequences of whitespace to blanks */");
    builder.addLine("    inputString = inputString.replace(/\\s+/g, ' ');");
    builder.addLine("    /* Split the input string, as need be */");
    builder.addLine("    let inputSplit = inputString.split(' ');");
    builder.addLine("    return inputSplit;");
    builder.addLine("  }");
    builder.addLine("  /* This function toggles the enablement status of a");
    builder.addLine("     set of style sheets */");
    builder.addLine("  function HDLmToggleStyleSheetEnablement() {");
    builder.addLine("    /* Get the current style sheet(s) enablement status */");
    builder.addLine("    var disabledStatus;");
    builder.addLine("    var titleValue = 'HDLmSessionClasses';");
    builder.addLine("    disabledStatus = sessionStorage.getItem(titleValue + 'Disabled');");
    builder.addLine("    /* Set the initial enabled status */");
    builder.addLine("    if (disabledStatus == null)");
    builder.addLine("      disabledStatus = 'true';");
    builder.addLine("    /* Toggle the style sheet status */");
    builder.addLine("    disabledStatus = (disabledStatus == 'true') ? false : true;");
    builder.addLine("    /* Save the new disabled status and use the new");
    builder.addLine("       style sheet disabled status */");
    builder.addLine("    sessionStorage.setItem(titleValue + 'Disabled', disabledStatus);");
    builder.addLine("    HDLmResetStyleSheetEnablement(titleValue, disabledStatus);");
    builder.addLine("  }");
    builder.addLine("  /* This method tries to update a JSON string with a new value. The caller");
    builder.addLine("     passes the old JSON string and the update values. This routine converts");
    builder.addLine("     the JSON string to an object and then updates the object. The object is");
    builder.addLine("     then converted back to a JSON string. */");
    builder.addLine("  function HDLmUpdateJsonStr(jsonStr, keyStr, valueStr) {");
    builder.addLine("    /* console.log('In HDLmUpdateJsonStr'); */");
    builder.addLine("    /* console.log(jsonStr, keyStr, valueStr); */");
    builder.addLine("    /* Check the JSON string passed the caller */");
    builder.addLine("    if (jsonStr == null)");
    builder.addLine("      jsonStr = '{}';");
    builder.addLine("    /* Convert the JSON string back to an object so we can add the key");
    builder.addLine("       and the value for the key */");
    builder.addLine("    let jsonObj = JSON.parse(jsonStr);");
    builder.addLine("    jsonObj[keyStr] = valueStr;");
    builder.addLine("    /* console.log(jsonObj); */");
    builder.addLine("    jsonStr = JSON.stringify(jsonObj);");
    builder.addLine("    /* console.log(jsonStr, keyStr, valueStr); */");
    builder.addLine("    return jsonStr;");
    builder.addLine("  }");
    /* Start the JavaScript function that applies all of the modifications */
    builder.addLine("  function HDLmApplyMods(readyState, HDLmIndexValue) {");
    /* Set the path value string */
    builder.addLine("    let pathValueStr = document.location.pathname;");
    /* Build a JavaScript object with all of the modifications */
    builder.addLine("    const modsArray = [");
    /* Add each of the modifications */
    int         counter;
    int         modsCount = mods.size(); 
    String      newLine;
    counter = 0;
    for (HDLmMod mod: mods) {
      counter++;
      newLine = " ".repeat(24);
      newLine += mod.getJsonSpecialSerializeNulls();
      if (counter < modsCount)
        newLine += ",";
      builder.addLine(newLine);
    }
    String  logRuleMatchingString;
    if (logRuleMatching == HDLmLogMatchingTypes.LOGMATCHINGYES)
      logRuleMatchingString = "true";
    else
      logRuleMatchingString = "false";
    Double   arrayEntry;
    String   forceSelectString = HDLmDefines.getString("HDLMFORCEVALUE");
    builder.addLine("                      ];");
    /* Build the session ID value */
    builder.addLine("    const sessionIdJS = '" + sessionIdJava + "';");
    /* Build the parameter values array */ 
    builder.addLine("    const parametersArray = HDLmGetParametersArray();"); 
    /* Get the number of modifications */
    builder.addLine("    let modsArrayLength = modsArray.length;");
    /* Process all of the modifications. Actually we are only interested
       in extract or notify modifications at this point. What we really
       want to do is to run the extracts and notifies and save the extracted 
       values. */
    builder.addLine("    for (let i=0; i < modsArrayLength; i++) {");
    builder.addLine("      let curMod = modsArray[i];");
    builder.addLine("      try {");
    /* Handle each type of modification. We only really process extract
       and notify modifications here. */
    builder.addLine("        switch (curMod.type) {");
    /* Check if the current modification is an extract */
    builder.addLine("          case 'extract': {");
    /* Assuming that the current modification is really a extract, then we 
       should have one or more values associated with it. Try to extract
       the current value and save the current value. */
    builder.addLine("            let nodeList = HDLmFind(curMod, false);");
    builder.addLine("            let nodeListLen = nodeList.length;");
    builder.addLine("            for (let j = 0; j < nodeListLen; j++) {");
    builder.addLine("              let curNode = nodeList[j];");
    /* If the value has already been extracted, then we don't want to extract
       it again */
    builder.addLine("              if (HDLmSavedExtracts.hasOwnProperty(curMod.name) &&");
    builder.addLine("                  HDLmSavedExtracts[curMod.name] != null)");
    builder.addLine("                continue;"); 
    /* Extract the text value of the current node */ 
    builder.addLine("              let oldText = curNode.textContent;");
    builder.addLine("              HDLmSavedExtracts[curMod.name] = oldText;");
    builder.addLine("            }");
    builder.addLine("            break;");
    builder.addLine("          }");
    /* Check if the current modification is a notify */
    builder.addLine("          case 'notify': {");
    /* Assuming that the current modification is really a notify, then we 
       should have one or more values associated with it. Try to extract
       the current value and save the current value. */
    builder.addLine("            for (let j = 0; j < curMod.valuesCount; j++) {");
    builder.addLine("              let searchText = curMod.values[j];");
    builder.addLine("              searchText = HDLmModifySearch(searchText);");
    /* If the value has already been extracted, then we don't want to extract
       it again */
    builder.addLine("              if (HDLmSavedNotifies.hasOwnProperty(searchText) &&");
    builder.addLine("                  HDLmSavedNotifies[searchText] != null)");
    builder.addLine("                continue;");
    builder.addLine("              let searchValue = HDLmObtainValue(searchText);");
    builder.addLine("              HDLmSavedNotifies[searchText] = searchValue;");
    builder.addLine("            }");
    builder.addLine("            break;");
    builder.addLine("          }");
    /* Handle (by ignoring it) the default case */
    builder.addLine("          default: {");
    builder.addLine("            break;");
    builder.addLine("          }");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("      catch (errorObj) {");
    builder.addLine("        console.log(errorObj);");
    builder.addLine("        let errorStr = HDLmErrorToString(errorObj);");
    builder.addLine("        let nameStr = curMod.name;"); 
    builder.addLine("        let siteNameStr = '" + siteName + "';");
    builder.addLine("        let divisionNameStr ='" + divisionName + "';");
    builder.addLine("        let hostNameStr = '" + hostName + "';");
    builder.addLine("        let builtStr = 'Modification (' + nameStr + ') Host (' + hostNameStr + ') Error (' + errorStr + ')';");
    builder.addLine("        console.log(builtStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'modification', nameStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'siteName', siteNameStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'divisionName', divisionNameStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'hostName', hostNameStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'sessionId', sessionIdJS);");
    builder.addLine("        HDLmSendData(errorStr);");
    builder.addLine("      }");
    builder.addLine("    }");
    /* Process each of the modifications */
    builder.addLine("    for (let i=0; i < modsArrayLength; i++) {");
    builder.addLine("      let curMod = modsArray[i];");
    builder.addLine("      try {");
    builder.addLine("        HDLmApplyMod(pathValueStr,");
    builder.addLine("                     curMod,");
    builder.addLine("                     sessionIdJS,"); 
    builder.addLine("                     HDLmIndexValue,");
    builder.addLine("                     parametersArray,"); 
    builder.addLine("                     '" + hostName + "',");  
    builder.addLine("                     '" + hostName + "',"); 
    builder.addLine("                     '" + divisionName + "',");       
    builder.addLine("                     '" + siteName + "',"); 
    if (secureHostName != null) 
      builder.addLine("                     '" + secureHostName + "',");
    else
      builder.addLine("                     null,"); 
    builder.addLine("                     '" + forceSelectString + "',"); 
    builder.addLine("                     " + logRuleMatchingString + ",");
    builder.addLine("                     readyState);");
    builder.addLine("      }");
    builder.addLine("      catch (errorObj) {");
    builder.addLine("        console.log(errorObj);");
    builder.addLine("        let errorStr = HDLmErrorToString(errorObj);");
    builder.addLine("        let nameStr = curMod.name;");
    builder.addLine("        let siteNameStr = '" + siteName + "';");
    builder.addLine("        let divisionNameStr ='" + divisionName + "';");
    builder.addLine("        let hostNameStr = '" + hostName + "';");
    builder.addLine("        let builtStr = 'Modification (' + nameStr + ') Host (' + hostNameStr + ') Error (' + errorStr + ')';");
    builder.addLine("        console.log(builtStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'modification', nameStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'siteName', siteNameStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'divisionName', divisionNameStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'hostName', hostNameStr);");
    builder.addLine("        errorStr = HDLmUpdateJsonStr(errorStr, 'sessionId', sessionIdJS);");
    builder.addLine("        HDLmSendData(errorStr);");
    builder.addLine("      }");
    builder.addLine("    }");
    /* Finish the current JavaScript function */
    builder.addLine("  }");
    /* Start the JavaScript function that reports an error */
    builder.addLine("  function HDLmBuildError(errSeverity, curType, errNumber, errText) {");
    builder.addLine("    let errorStr = '';");
    builder.addLine("    errorStr += '" + HDLmDefines.getString("HDLMPREFIX") + "' + ' ';");
    builder.addLine("    errorStr += errSeverity + ' ';");
    builder.addLine("    errorStr += curType + ' ';");
    builder.addLine("    errorStr += errNumber.toString() + ' ';");
    builder.addLine("    errorStr += errText;");
    builder.addLine("    console.log(errorStr);");
    /* Finish the current JavaScript function */
    builder.addLine("  }");
    /* Start the JavaScript function that changes zero or more node attributes */
    builder.addLine("  function HDLmChangeAttributes(curNode, jsonText) {");
    builder.addLine("    let changesObj = JSON.parse(jsonText);");
    builder.addLine("    for (const keyValue in changesObj) {");
    builder.addLine("      if (!changesObj.hasOwnProperty(keyValue))");
    builder.addLine("        continue;");
    builder.addLine("      let changesValue = changesObj[keyValue];");
    builder.addLine("      if (changesValue == null)");
    builder.addLine("        curNode.removeAttribute(keyValue);");
    builder.addLine("      else {");
    builder.addLine("        if (keyValue == 'class') {");
    builder.addLine("          HDLmClassAddEntry(curNode, changesValue);");
    builder.addLine("        }");
    builder.addLine("        else {");
    builder.addLine("          curNode.setAttribute(keyValue, changesValue);");
    builder.addLine("        }");
    builder.addLine("      }");
    builder.addLine("    }");
    /* Finish the current JavaScript function */
    builder.addLine("  }");
    /* Build a set of functions that contain the JavaScript that is stored
       in each of rules. Note that JavaScript is only store in script rules. */
    for (HDLmMod mod: mods) {
    	/* Skip the current modification if it is not a 'script' (without
  	     the quotes) modification */
    	if (mod.getType() != HDLmModTypes.SCRIPT)
        continue;
    	/* Get the modified name of the rule */
    	String  newName = HDLmMod.replaceInString(mod.getName());
        /* Handle each of the values of the current modification */
        int   valueCount = mod.getValues().size();
        for (int i = 0; i < valueCount; i++) {
          /* Get the current value */
          String  curValue = mod.getValues().get(i);
          /* Build the function with the JavaScript in it */
          newLine = "  function HDLmExecute" + newName + i + "() {";
          builder.addLine(newLine);
          ArrayList<String>   curValues = new ArrayList<String>(Arrays.asList(curValue.split("/n")));
          for (int j = 0; j < curValues.size(); j++) {
            String  curLine = curValues.get(j);
            builder.addLine("    " + curLine);
          }
          newLine = "  }";
          builder.addLine(newLine);
      }
    }
    /* Build a function that contains a set of nested switches.
       The nested switches invoke the functions with JavaScript
       in them. */
    newLine = "  function HDLmExecuteSwitch(modName, choiceNumber) {";
		builder.addLine(newLine);
    newLine = "    switch (modName) {";
		builder.addLine(newLine);
    for (HDLmMod mod: mods) {
    	/* Skip the current modification if it is not a 'script' (without
  	     the quotes) modification */
    	if (mod.getType() != HDLmModTypes.SCRIPT)
        continue;
    	String  curModName = mod.getName();
      newLine = "      case '" + curModName + "':";
		  builder.addLine(newLine);
    	String  curModNameInternal = HDLmMod.replaceInString(curModName);
      newLine = "        switch (choiceNumber) {";
		  builder.addLine(newLine);
     	/* Handle each of the values of the current modification */
    	int   valueCount = mod.getValues().size();
	  	for (int i = 0; i < valueCount; i++) {
      newLine = "          case " + i + ":";
		  builder.addLine(newLine);
        newLine = "            HDLmExecute" + curModNameInternal + i + "();";
		    builder.addLine(newLine);
        newLine = "            break;";
		    builder.addLine(newLine);
      }
      newLine = "          default:";
	  	builder.addLine(newLine);
      newLine = "            let errorText = \"Invalid choice value - \" + choiceNumber" + ";";
  		builder.addLine(newLine);
      newLine = "            HDLmBuildError('Error', 'Choice', 63, errorText);";
	  	builder.addLine(newLine);
      newLine = "            break;";
	  	builder.addLine(newLine);
      newLine = "        }";
	  	builder.addLine(newLine);
    }
      newLine = "      default:";
	  	builder.addLine(newLine);
      newLine = "        let errorText = \"Invalid rule name value - \" + modName;";
  		builder.addLine(newLine);
      newLine = "        HDLmBuildError('Error', 'RuleName', 62, errorText);";
	  	builder.addLine(newLine);
      newLine = "        break;";
	  	builder.addLine(newLine);
    newLine = "    }";
		builder.addLine(newLine);
    newLine = "  }";
		builder.addLine(newLine);
    /* Start the JavaScript function that returns all of the attributes of a node 
       as a string */ 
    builder.addLine("  function HDLmGetAttributesString(curNode) {");
    builder.addLine("    let outputStr = '';");
    builder.addLine("    if (!curNode.hasAttributes())");
    builder.addLine("      return outputStr;");
    builder.addLine("    let attrs = curNode.attributes;");
    builder.addLine("    let attrsLength = attrs.length;");
    builder.addLine("    for (let i = attrsLength - 1; i >= 0; i--) {");
    builder.addLine("      if (outputStr != '')");
    builder.addLine("       outputStr += ' ';");
    builder.addLine("       outputStr += attrs[i].name + '=' + \"'\" + attrs[i].value + \"'\";");
    builder.addLine("    }");
    builder.addLine("    return outputStr;");
    /* Finish the current JavaScript function */
    builder.addLine("  }");
    /* Build a JavaScript function that returns a value(s) index for a rule name */    
    builder.addLine("  /* Get the lookup value (if possible) for a rule name.");
    builder.addLine("     Return the lookup value to the caller. This routine");
    builder.addLine("     returns 'undefined' (with no quotes) if the rule");
    builder.addLine("     name is known to this routine. */");
    builder.addLine("  function HDLmGetLookupIndex(ruleName) {");
    builder.addLine("    /* console.log(ruleName); */");
    builder.addLine("    let lookupData = {");
    /* Get the overall set of index information */
    JsonArray   indexJsonArray = getIndexJsonArray(); 
    /* Check if the JSON array is valid or not */
		if (!indexJsonArray.isJsonArray()) {
	 	  String  errorText = "JSON array in getJSBuildJs is invalid";
	 	  HDLmAssert.HDLmAssertAction(false, errorText);
	  }
		/* Get the JSON array size */
    int   indexJsonArraySize = indexJsonArray.size();
    /* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
    /* Get the needed JSON */
		if (logIsDebugEnabled) {
		  LOG.debug("In HDLmGetLookupIndex");
		  LOG.debug(sessionIndexStr);
	  }
    /* Convert the index string to an index value, if possible */
    double  sessionIndexValue = 0.0;
    if (sessionIndexStr != null &&
    		!sessionIndexStr.equals("null"))  
    	sessionIndexValue = Double.parseDouble(sessionIndexStr);    	 
    /* Check all of the websites looking for a matching website */
    for (int i=0; i < indexJsonArraySize; i++) {
    	JsonElement   indexJsonElement = indexJsonArray.get(i);
    	String  jsonHostName = HDLmJson.getJsonString(indexJsonElement, "website");
    	/* Check if the host names match. If they do, we have a matching website. */
    	if (hostName.equals(jsonHostName)) {
    		JsonArray   rulesJsonArray = HDLmJson.getJsonArray(indexJsonElement, "rules");
    		JsonArray   choicesJsonArray = HDLmJson.getJsonArray(indexJsonElement, "choices");
        /* Check if the JSON arrays are valid or not */
    		if (!rulesJsonArray.isJsonArray()) {
    	 	  String  errorText = "JSON array in getJSBuildJs is invalid";
    	 	  HDLmAssert.HDLmAssertAction(false, errorText);
    	  }
    		if (!choicesJsonArray.isJsonArray()) {
    	 	  String  errorText = "JSON array in getJSBuildJs is invalid";
    	 	  HDLmAssert.HDLmAssertAction(false, errorText);
    	  }
    		/* Get the JSON array sizes */
    		int         rulesJsonArraySize = rulesJsonArray.size();
    		int         choicesJsonArraySize = choicesJsonArray.size();
    		/* If possible, get the choices array entry for use below */
    		JsonArray   choiceJsonArray = null;
        if (sessionIndexStr != null &&
        		!sessionIndexStr.equals("null")) { 
         	double      indexValue = sessionIndexValue * choicesJsonArraySize;
        	int         indexValueInt = (int) Math.floor(indexValue);
        	choiceJsonArray = (JsonArray) choicesJsonArray.get(indexValueInt);
          /* Check if the JSON array is valid or not */
      		if (!choiceJsonArray.isJsonArray()) {
      	 	  String  errorText = "JSON array in getJSBuildJs is invalid";
      	 	  HDLmAssert.HDLmAssertAction(false, errorText);
      	  }
        }    		
    		/* Process all of the rules for the website */
    		counter = 0;
        for (int j=0; j < rulesJsonArraySize; j++) {
        	counter++;
        	/* Add the rule name to the output */
        	JsonElement   ruleJsonElement = rulesJsonArray.get(j);
      		if (!ruleJsonElement.isJsonPrimitive()) {
      			HDLmAssert.HDLmAssertAction(false, "JSON element is not a JSON primitive value");
      		}
        	String        ruleName = ruleJsonElement.getAsString();
          newLine = " ".repeat(23);
          newLine += "'"; 
          newLine += ruleName;
          /* Add some JSON syntax */
          newLine += "': ";
          /* Use the index value to pick an array element */
          if (sessionIndexStr == null ||
          		sessionIndexStr.equals("null"))
          	newLine += "null";
          else {
            JsonElement   choiceJsonElement = choiceJsonArray.get(j); 
        		if (!choiceJsonElement.isJsonPrimitive()) {
        			HDLmAssert.HDLmAssertAction(false, "JSON element is not a JSON primitive value");
        		}
            String  choiceJsonString = choiceJsonElement.getAsString();
            newLine += choiceJsonString;
          }          
          if (counter < rulesJsonArraySize)  
            newLine += ",";       
          builder.addLine(newLine);
        }
    	}   	
    }   
    builder.addLine("                     };");
    builder.addLine("    let lookupIndex = lookupData[ruleName];");
    builder.addLine("    /* console.log(lookupIndex); */");
    builder.addLine("    return lookupIndex;");
    builder.addLine("  }");  
    /* Start the JavaScript function that returns all of the parameters 
       as a array */ 
    builder.addLine("  function HDLmGetParametersArray() {");
    builder.addLine("    let outputStr = '';");
    builder.addLine("    const parametersArray = [");
    counter = 0;
    int   sessionParametersArrayLength = sessionParametersArray.size();
    for (int i = 0; i < sessionParametersArrayLength; i++) {
      counter++;
      newLine = " ".repeat(30);
      arrayEntry = sessionParametersArray.get(i);
      if (arrayEntry == null)
        newLine += "null";
      else
        newLine += arrayEntry;
      if (counter < sessionParametersArrayLength)
        newLine += ",";
      builder.addLine(newLine);
    }
    builder.addLine("                            ];");
    builder.addLine("    return parametersArray;");
    /* Finish the current JavaScript function */
    builder.addLine("  }");
    /* The next routine tries to get a perceptual hash value for
       a URL (the part of the URL that starts with two slashes).
       The caller provides the URL. This routine builds the network
       request and sends it. Of course, the answer comes back later
       and is ignored. No callback routines are provided or used. */
    builder.addLine("  function HDLmGetPHash(urlStr) {");
    builder.addLine("    /* Build the AJAX object */");
    builder.addLine("    let xHttpReq = new XMLHttpRequest();");
    String   protocolStringGetPHash;
    protocolStringGetPHash = protocol.toString().toLowerCase();
    builder.addLine("    let serverNameValue = '" + serverName + "';");
    builder.addLine("    let urlVal = '" + protocolStringGetPHash + "://' + serverNameValue + '/" + HDLmConfigInfo.getPHashName() + "';");
    builder.addLine("    xHttpReq.open('POST', urlVal);");
    builder.addLine("    urlStr = encodeURIComponent(urlStr);");
    builder.addLine("    xHttpReq.send(urlStr);");
    /* Finish the current JavaScript function */ 
    builder.addLine("  }");
    /* Send a message to the server for the current link and some events
       as they occur */
    builder.addLine("  {");    
    /* Set a few value for use later. Some of these values are used many
       times. Note that the session ID is part of the JavaScript program
       that is sent from the server to the client. */
    builder.addLine("    let hostNameStr = location.hostname;");
    builder.addLine("    let linkStr = location.href;");
    builder.addLine("    let pathNameStr = document.location.pathname;");
    builder.addLine("    let sessionIdValue = '" + sessionIdJava + "';");
    /* Get JSON for the current web page and send it to the server */
    builder.addLine("    let eventJson = HDLmGetJsonForLink(linkStr, hostNameStr, pathNameStr, sessionIdValue)");
    /* We really don't want to send event data to the server at this time.
       The event data makes the server log file too large. */
    builder.addLine("    /* HDLmSendData(eventJson); */");
    /* Process all of the keys (really events) for the current window */
    builder.addLine("    Object.keys(window).forEach(key => {"); 
    /* A few events are not of interest to us. We ignore them. */
    builder.addLine("      if (key.startsWith('onmouse'))");
    builder.addLine("        return;");
    builder.addLine("      if (key.startsWith('onpointer'))");
    builder.addLine("        return;"); 
    /* The regular expression below is used to check if the current key starts
       with 'on'. If the current key does start with 'on', then we probably 
       have a key associated with an event. */
    builder.addLine("      if (/^on/.test(key)) {");
    /* The next statement is used to add an event listener for the current key */
    builder.addLine("        window.addEventListener(key.slice(2), event => {");  
    /* The next statement is used to get the name of the current event */
    builder.addLine("          let eventName = HDLmGetObjectName(event);"); 
    /* The next statement is used to get some JSON for the current event.
       After we get the JSON, we send it to the server. */
    builder.addLine("          let eventJson = HDLmGetJsonForEventObject(event, eventName, hostNameStr, pathNameStr, sessionIdValue)");
    /* We really don't want to send event data to the server at this time.
       The event data makes the server log file too large. */
    builder.addLine("          /* HDLmSendData(eventJson); */");
    builder.addLine("        });");
    builder.addLine("      }"); 
    builder.addLine("    });");
    builder.addLine("  };");
    /* Create a shared variable that will contain the saved updates */
    builder.addLine("  let HDLmSavedUpdates = new Object();");
    /* Create a shared variable that will contain the extracted values
       used for extract processing */
    builder.addLine("  let HDLmSavedExtracts = new Object();");
    /* Create a shared variable that will contain the extracted values
       used for notify processing */
    builder.addLine("  let HDLmSavedNotifies = new Object();");
    /* Create a global variable that shows that the update scripts
       have been loaded. This variable is defined with var rather 
       than let so that the window object will be modified by this
       statement. The browser extension (HDLmExtensionNodeIden.js) 
       checks this variable to see if update scripts have already
       been loaded. */
    builder.addLine("  var HDLmCheckVariable = true;");
    /* Build the perceptual hash values object */
    counter = 0;
    builder.addLine("  const HDLmPHashObject = {");
    Map<String, String>  mapObj = HDLmPHashCache.getMap();
    long  mapSize = mapObj.size();
		for (Map.Entry<String, String> entry: mapObj.entrySet()) {
	    String  key = entry.getKey();
	    String  value = entry.getValue();
      counter++;
      newLine = " ".repeat(28);
        newLine += "\"";
        newLine += key;
        newLine += "\":\"";
        newLine += value;
        newLine += "\"";
        if (counter < mapSize)
          newLine += ",";
      builder.addLine(newLine);
    }
    builder.addLine("                          };");
    /* Define the JavaScript function that sends any data back to the
       server */ 
    String   protocolStringLower;
    protocolStringLower = protocol.toString().toLowerCase();
    builder.addLine("  function HDLmSendData(dataStr) {");
    /* Set the host name string */
    builder.addLine("    dataStr = '" + HDLmDefines.getString("HDLMPOSTDATA") + "=" + "' + dataStr;");
    builder.addLine("    let httpReq = new XMLHttpRequest();");
    builder.addLine("    let serverNameValue = '" + serverName + "';");
    builder.addLine("    let urlStr = '" + protocolStringLower + "://' + serverNameValue + '/" + HDLmDefines.getString("HDLMPOSTDATA") + "';");
    builder.addLine("    httpReq.open('POST', urlStr);");
    builder.addLine("    httpReq.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');");
    builder.addLine("    dataStr = encodeURIComponent(dataStr);");
    builder.addLine("    httpReq.send(dataStr);");
    builder.addLine("  }");
    /* Define the JavaScript function that sends the update information
       back to the server */ 
    builder.addLine("  function HDLmSendUpdates(savedUpdates, reasonStr, weightStr, errorStr) {");
    builder.addLine("    savedUpdates.reason = reasonStr;");
    builder.addLine("    savedUpdates.weight = weightStr;");
    builder.addLine("    savedUpdates.error = errorStr;");
    builder.addLine("    let updateStr = JSON.stringify(savedUpdates);");
    builder.addLine("    HDLmSendData(updateStr);");
    builder.addLine("  }");
    /* Store the index value in the generated JavaScript 
       program. This is done so that the index value can 
       be used by the generated JavaScript program. */
    if (sessionIndexStr == null ||
        sessionIndexStr.equals("null"))
      builder.addLine("  let HDLmIndexValue = null;");
    else
    	builder.addLine("  let HDLmIndexValue = " + sessionIndexStr + ";");
    /* Create several CSS entries that will cause certain
       DOM entries to be color-coded as need be */ 
    builder.addLine("  HDLmClassAddCss('.HDLmClassPrimary'," +  
                                      "'background-color: yellow');");
    builder.addLine("  HDLmClassAddCss('.HDLmClassBackground'," + 
                                      "'filter: grayscale(100%)');");  
    /* Add some JavaScript for creating and using a mutation observer */
    builder.addLine("  let HDLmObsTargetNode = document;");
    /* Options for the observer (which mutations to observe) */
    builder.addLine("  let HDLmObsConfig = {attributes: true, childList: true, subtree: true};");
    /* Callback function to execute when mutations are observed */
    builder.addLine("  let HDLmObsCallback = function (mutationsList, HDLmObsObserver) {");
    builder.addLine("    /* console.log(document.readyState); */");
    builder.addLine("    let forceReadyState = false;");
    builder.addLine("    if (document.location.hostname == 'www.themarvelouslandofoz.com' &&");
    builder.addLine("        document.readyState == 'interactive')");
    builder.addLine("      forceReadyState = true;");
    builder.addLine("    HDLmApplyMods(document.readyState, HDLmIndexValue);");
    builder.addLine("    if (document.readyState == 'complete' ||");
    builder.addLine("        forceReadyState == true) {");
    builder.addLine("      HDLmApplyMods(document.readyState, HDLmIndexValue);");
    builder.addLine("    };");
    builder.addLine("  };");
    /* Create an observer instance linked to the callback function */
    builder.addLine("  let HDLmObsObserver = new MutationObserver(HDLmObsCallback);");
    /* Start observing the target node for configured mutations */
    builder.addLine("  HDLmObsObserver.observe(HDLmObsTargetNode, HDLmObsConfig);");
    /* Set the path value string */
    builder.addLine("  let pathValueStr = document.location.pathname;");     
    /* Build a local modification with all of the right values set, so that
       it can be passed to the apply modification routine. This must be done
       at the end of the JavaScript program so that all of the other routines
       will have been processed and as a consequence, available. */  
    builder.addLine("  let curMod = {};");      
    builder.addLine("  curMod.enabled = true;");  
    String  modificationName = HDLmDefines.getString("HDLMLOADPAGEMODNAME");
    builder.addLine("  curMod.name = '" + modificationName + "';"); 
    builder.addLine("  curMod.parameter = -1;");   
    builder.addLine("  curMod.path = '//.*/';");   
    builder.addLine("  curMod.pathre = true;");   
    String  modificationType = HDLmModTypes.VISIT.toString().toLowerCase();
    builder.addLine("  curMod.type = '" + modificationType + "';");     
    builder.addLine("  curMod.values = [ 'Yes' ];"); 
    builder.addLine("  curMod.valuesCount = 1;");   
    /* Build the session ID value */
    builder.addLine("  const sessionIdJS = '" + sessionIdJava + "';");
    /* Build the parameter values array */
    builder.addLine("  const parametersArray = HDLmGetParametersArray()");
    /* Set the ready state value to unknown (at least for now) */
    builder.addLine("  const readyState = 'unknown';");
    /* Create an object. An object is required because only objects
       can be modified. */ 
    builder.addLine("  HDLmApplyMod(pathValueStr,"); 
    builder.addLine("               curMod,");  
    builder.addLine("               sessionIdJS,");
    builder.addLine("               HDLmIndexValue,");
    builder.addLine("               parametersArray,");
    builder.addLine("               '" + hostName + "',");  
    builder.addLine("               '" + hostName + "',"); 
    builder.addLine("               '" + divisionName + "',");      
    builder.addLine("               '" + siteName + "',");
    if (secureHostName != null) 
      builder.addLine("               '" + secureHostName + "',"); 
    else
      builder.addLine("               null,");
    builder.addLine("               '" + forceSelectString + "',");
    builder.addLine("               '" + logRuleMatchingString + "',");
    builder.addLine("               readyState);");
    /* Finish the entire set of JavaScript */
    builder.addLine("</script>");
    actualJS = builder.getLinesWithSuffix("\r\n");
		/* The next set of code rebuilds the fixed JavaScript file. This code
		   is not normally executed because we want to make sure we are actually
		   getting the fixed JavaScript from the routine that builds the 
		   JavaScript. */ 
		if (useCreateFixedJS) {
		  HDLmUtility.fileClearContents(fixedJSName);
		  /* Declare a few values for converting the string */
		  int             i;
		  int             actualJSLen;
		  StringBuilder   actualJSAdjustedBuilder = new StringBuilder();
		  /* Build the output string */
		  actualJSLen = actualJS.length();
		  String  curStr;
		  for (i = 0; i < actualJSLen; i++) {
		  	char  curChar = actualJS.charAt(i);
		  	/* Check for a few special characters */
		  	if (curChar == '\u1000')  
		  	  curStr = "\\u1000";		  
		  	else if (curChar == '\u1001')  
	  	    curStr = "\\u1001";
        else if (curChar == '\u1002')  
	  	    curStr = "\\u1002";
        else if (curChar == '\u1003')  
	  	    curStr = "\\u1003";
        else if (curChar == '\u1004')  
	  	    curStr = "\\u1004";
        else if (curChar == '\u1005')  
	  	    curStr = "\\u1005";
        else if (curChar == '\u1006')  
	  	    curStr = "\\u1006";
        else if (curChar == '\u1007')  
	  	    curStr = "\\u1007";
        else if (curChar == '\u1008')  
	  	    curStr = "\\u1008";
        else if (curChar == '\u1009')  
	  	    curStr = "\\u1009";
        else if (curChar == '\u100a')  
	  	    curStr = "\\u100a";
        else if (curChar == '\u100b')  
	  	    curStr = "\\u100b";
        else if (curChar == '\u100c')  
	  	    curStr = "\\u100c";
        else if (curChar == '\u100d')  
	  	    curStr = "\\u100d";
        else if (curChar == '\u100e')  
	  	    curStr = "\\u100e";
        else if (curChar == '\u100f')  
	  	    curStr = "\\u100f";
        else if (curChar == '\u1010')  
	  	    curStr = "\\u1010";
        else if (curChar == '\u1011')  
	  	    curStr = "\\u1011";
        else if (curChar == '\u1012')  
	  	    curStr = "\\u1012";
        else if (curChar == '\u1013')  
	  	    curStr = "\\u1013";
        else if (curChar == '\u1014')  
	  	    curStr = "\\u1014";
        else if (curChar == '\u1015')  
	  	    curStr = "\\u1015";
        else if (curChar == '\u1016')  
	  	    curStr = "\\u1016";
        else if (curChar == '\u1017')  
	  	    curStr = "\\u1017";
        else if (curChar == '\u1018')  
	  	    curStr = "\\u1018";
        else if (curChar == '\u1019')  
	  	    curStr = "\\u1019";
        else if (curChar == '\u101a')  
	  	    curStr = "\\u101a";
        else if (curChar == '\u101b')  
	  	    curStr = "\\u101b";
        else if (curChar == '\u101c')  
	  	    curStr = "\\u101c";
        else if (curChar == '\u101d')  
	  	    curStr = "\\u101d";
        else if (curChar == '\u101e')  
	  	    curStr = "\\u101e";
        else if (curChar == '\u101f')  
	  	    curStr = "\\u101f";
        else if (curChar == '\u1020')  
	  	    curStr = "\\u1020";
        else if (curChar == '\u1021')  
	  	    curStr = "\\u1021";
        else if (curChar == '\u1022')  
	  	    curStr = "\\u1022";
        else if (curChar == '\u1023')  
	  	    curStr = "\\u1023";
        else if (curChar == '\u1024')  
	  	    curStr = "\\u1024";
        else if (curChar == '\u1025')  
	  	    curStr = "\\u1025";
        else if (curChar == '\u1026')  
	  	    curStr = "\\u1026";
        else if (curChar == '\u1027')  
	  	    curStr = "\\u1027";
        else if (curChar == '\u1028')  
	  	    curStr = "\\u1028";
        else if (curChar == '\u1029')  
	  	    curStr = "\\u1029";
        else if (curChar == '\u102a')  
	  	    curStr = "\\u102a";
        else if (curChar == '\u102b')  
	  	    curStr = "\\u102b";
        else if (curChar == '\u102c')  
	  	    curStr = "\\u102c";
        else if (curChar == '\u102d')  
	  	    curStr = "\\u102d";
        else if (curChar == '\u102e')  
	  	    curStr = "\\u102e";
        else if (curChar == '\u102f')  
	  	    curStr = "\\u102f";
        else if (curChar == '\u1030')  
	  	    curStr = "\\u1030";
        else if (curChar == '\u1031')  
	  	    curStr = "\\u1031";
        else if (curChar == '\u1032')  
	  	    curStr = "\\u1032";
        else if (curChar == '\u1033')  
	  	    curStr = "\\u1033";
        else if (curChar == '\u1034')  
	  	    curStr = "\\u1034";
        else if (curChar == '\u1035')  
	  	    curStr = "\\u1035";
        else if (curChar == '\u1036')  
	  	    curStr = "\\u1036";
        else if (curChar == '\u1037')  
	  	    curStr = "\\u1037";
        else if (curChar == '\u1038')  
	  	    curStr = "\\u1038";
        else if (curChar == '\u1039')  
	  	    curStr = "\\u1039";       
		  	else {
			  	curStr = "";
			  	curStr += curChar;
		  	}	  	
		  	actualJSAdjustedBuilder.append(curStr);
		  }		  
		  /* Write out the modified string */
		  String  actualJSAdjusted = actualJSAdjustedBuilder.toString();
		  HDLmUtility.filePutAppend(fixedJSName, 
		  		                      actualJSAdjusted);
		}		
    return actualJS;
  } 
  /* This Java function returns a JSON object 
     with the web site name and the index values
     in it. */
  public static JsonArray  getIndexJsonArray() {
    class getIndexJsonArrayLocal {
      /* Declare a string that will have JSON in it */
      final static String  jsonString = 
        "[" +
        "  {" +
        "    \"website\":" +
        "      \"www.yogadirect.com\"," +
        "    \"generated\":" +
        "      \"2024-06-13T01:51:52Z\"," +
        "    \"rules\":" +
        "      [" +
        "        \"Change Banner\"," +
        "        \"Change Add To Cart\"" +
        "      ]," +
        "    \"choices\":" +
        "      [" +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]," +
        "        [4,6]," +
        "        [5,0]," +
        "        [0,1]," +
        "        [1,2]," +
        "        [2,3]," +
        "        [3,4]," +
        "        [4,5]," +
        "        [5,6]," +
        "        [0,0]," +
        "        [1,1]," +
        "        [2,2]," +
        "        [3,3]," +
        "        [4,4]," +
        "        [5,5]," +
        "        [0,6]," +
        "        [1,0]," +
        "        [2,1]," +
        "        [3,2]," +
        "        [4,3]," +
        "        [5,4]," +
        "        [0,5]," +
        "        [1,6]," +
        "        [2,0]," +
        "        [3,1]," +
        "        [4,2]," +
        "        [5,3]," +
        "        [0,4]," +
        "        [1,5]," +
        "        [2,6]," +
        "        [3,0]," +
        "        [4,1]," +
        "        [5,2]," +
        "        [0,3]," +
        "        [1,4]," +
        "        [2,5]," +
        "        [3,6]," +
        "        [4,0]," +
        "        [5,1]," +
        "        [0,2]," +
        "        [1,3]," +
        "        [2,4]," +
        "        [3,5]" +
        "      ]" +
        "  }" +
        "]" +
        "";
			/* Create a new JSON parser for use below */
	    static JsonParser  parser = new JsonParser();  
	    /* Convert the JSON string to a JSON array */
	    static JsonArray   rvJsonArray = (JsonArray) parser.parse(jsonString);
  	}
		/* Return the JSON object to the caller */
    return getIndexJsonArrayLocal.rvJsonArray;
  }
}
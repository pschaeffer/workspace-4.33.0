package com.headlamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * HDLmBuildJSTest short summary.
 *
 * HDLmBuildJSTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmBuildJSTest {
  @BeforeAll
	static void HDLmBuildJSBeforeAll() {
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree  modTopTree = HDLmTree.addToTreeMod(HDLmTreeData.jsonGetModStr, HDLmEditorTypes.MOD);
		HDLmTree.setNodeModTreeTop(modTopTree);
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree  passTopTree = HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		HDLmTree.setNodePassTreeTop(passTopTree);
	  /* Add all of the general events to the events map */
	  HDLmEvent.addEventsList(HDLmEvent.eventNames);
	}
	@Test
	void getJSBuildJS() { 
		/* Run a few getJSBuildJS tests */
		String     htmlStr = HDLmTreeData.magentoNeveJacketStr();	
	  String     pathValueStr = "/neve-studio-dance-jacket.html";
	  Document   htmlDoc = Jsoup.parse(htmlStr);
	  ArrayList<Double>  parametersArray = new ArrayList<Double>(
	    List.of(0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5));
	  ArrayList<HDLmSavedChange>   savedChangesArray = new ArrayList<HDLmSavedChange>();
		/* Build a node path for locating a site node */
		ArrayList<String>  nodePath = new ArrayList<String>(
		  List.of("Top", "legends-magento.dnsalias.com", "example.com", "example.com"));
		HDLmTree   locateTree = HDLmTree.locateTreeNode(HDLmTree.getNodeModTreeTop(),
				                                            nodePath);
		assertNotNull(locateTree,
                  "Site Node should have been located");		
		/* Run a few extractMods tests */
		ArrayList<HDLmMod>  matchingMods = new ArrayList<HDLmMod>();
		HDLmPassThruStatus  passThru = HDLmPassThruStatus.PASSTHRUNOTOK;
	  /* Try to extract some matching modifications */
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru, HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  HDLmMod  modDetails = matchingMods.get(0);
	  modDetails.setCreatedNull();
	  modDetails.setLastModifiedNull();
	  String   modName = "Add to Cart Text";
		/* Check the modification node details */
		assertEquals(1, modDetails.getFinds().size(), "Modification finds size must be one"); 
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null"); 
		assertNotNull(modDetails.getValues(), "Modification values value must not be null"); 
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.TITLE, modDetails.getType(), "Modification type value must be title");
		assertEquals(2, modDetails.getValuesCount(), "Modification values count must be two");
		assertEquals(9, modDetails.getParameterNumber(), "Modification parameter number must be nine");
		assertNotEquals(null, modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertNotNull(modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNotEquals("Extra Bott", modDetails.getExtra(), "Modification extra value must not be ('Extra Bott)");
		assertNull(modDetails.getExtra(), "Modification extra value must be null");
		assertEquals(modName, modDetails.getName(), "Modification name value must be '" + modName + "'");
		assertEquals("/neve-studio-dance-jacket.html", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals(null, modDetails.getXPath(), 
				         "Modification XPath value must be null");		
		assertNull(modDetails.getXPath(), "Modification XPath value must be null");
		/* Convert the modification to JSON */
		String   modJson = modDetails.getJsonSpecialSerializeNulls();
		String   modJsonExpected = "" + 
	 	  "{\"find\":[{\"tag\":\"button\",\"attribute\":\"id\",\"value\":\"product-addtocart-button\"}]," +
		  "\"valuesCount\":2," +
		  "\"values\":[\"ADD TO CART\",\"TCELESECROFAdd to ala-carte\"],\"enabled\":true,\"pathre\":false,\"type\":" +
		  "\"title\",\"parameter\":9,\"cssselector\":\"\"," +
		  "\"lastmodified\":null," +
		  "" +
	 	  "\"passThru\":null,\"dummyTable\":null," +
		  "\"dummyType\":null,\"updated\":null," +
	 	  "\"extra\":\"\"," +		  
		  "\"name\":\"Add to Cart Text\",\"nodeiden\":null,\"path\":\"/neve-studio-dance-jacket.html\"," + 
		  "\"usemode\":\"prod\"," +
		  "\"value\":\"\",\"valueSuffix\":" +
		  "\"\",\"xpath\":\"\"}";				 
		modJsonExpected = modJsonExpected.replace("\"dummyTable\":null,", "");  
		modJsonExpected = modJsonExpected.replace("\"dummyType\":null,", "");
		modJsonExpected = modJsonExpected.replace("\"passThru\":null,", "");
		modJsonExpected = modJsonExpected.replace("\"updated\":null,", "");		 
		assertEquals(modJsonExpected, modJson, "JSON from modification does not match expected value");
		HDLmLogMatchingTypes    logMatching = HDLmLogMatchingTypes.LOGMATCHINGYES;
	  HDLmProtocolTypes       protocolType = HDLmProtocolTypes.HTTPS;
	  String  secureHostName = "SecureHost.com";
	  String  backend = "backend.com";
	  String  hostName = "hostName";
	  String  divisionName = "example.com";
	  String  siteName = "example.com";
	  String  sessionIdLocal = "";
	  String  indexLocal = "null";
	  String  pathValueStrLocal = "/pathvalue";
	  String  serverName = HDLmConfigInfo.getServerName();
	  HDLmSession   sessionObj = new HDLmSession();
	  sessionObj.setSessionId(sessionIdLocal);
	  sessionObj.setIndex(indexLocal);
	  ArrayList<Double>   parametersArrayLocal = parametersArray;
	  String  parametersArrayLocalStr = HDLmMain.getParametersStr(parametersArrayLocal);
	  sessionObj.setParameters(parametersArrayLocalStr); 
	  ArrayList<HDLmMod>  mods = matchingMods;
		String  JSString = HDLmBuildJsNoCompression.getJsBuildJs(protocolType, secureHostName,  
				                                                     hostName, divisionName, siteName, mods, 
							                                               sessionObj, 
							                                               logMatching,
							                                               serverName);
		/* The next set of code rebuilds the expected JavaScript file. This code
		   is not normally executed because we want to make sure we are actually
		   getting the expected JavaScript from the routine that builds the 
		   JavaScript. */ 
		if (1 == 1) {
		  HDLmUtility.fileClearContents(HDLmDefines.getString("HDLMEXPECTEDFILENAME"));
		  HDLmUtility.filePutAppend(HDLmDefines.getString("HDLMEXPECTEDFILENAME"), 
		  		                      JSString, 
		  		                      StandardCharsets.UTF_8);
		}		
		/* Convert the old saved JavaScript program to a list of lines and trim
		   all trailing whitespace. The combine the trimmed lines into one long
		   line. */
		String   suffix = "\r\n";
		ArrayList<String>  JSStringArrayLines = HDLmUtility.splitLinesSuffix(JSString, suffix);
		JSStringArrayLines = HDLmUtility.trimLinesRight(JSStringArrayLines);
		JSString = HDLmUtility.combineLinesSuffix(JSStringArrayLines, suffix);  
		/* Get a list of expected lines from a file */
		ArrayList<String>  jssExpectedArrayLines = HDLmUtility.fileGetLines(HDLmDefines.getString("HDLMEXPECTEDFILENAME"),
				                                                                StandardCharsets.UTF_8);
		jssExpectedArrayLines = HDLmUtility.trimLinesRight(jssExpectedArrayLines);
		/* Combine all of the lines of the old JavaScrip program into one long string */
		String   jssExpected = HDLmUtility.combineLinesSuffix(jssExpectedArrayLines, suffix);	 
	  assertEquals(jssExpected, JSString, "Expected JavaScript does not match actual JavaScript");	
	  {	  	 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBuildJsNoCompression.getJsBuildJs(null, secureHostName,  
									                              	     	                              hostName, divisionName, siteName, mods,  
									                              	     	                              sessionObj, 
									                              	     	                              logMatching, 
									                              	     	                              serverName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Protocol string passed to getJsBuildJs is null",
					         "Unexpected exception message");
		}
	  /* At this point, we used to set the secure host name to null and make sure 
	     we got the correct error message back (after the correct exception). However,
	     the secure host name can be null and quite valid. This may happen if no proxy
	     definition is found for the current host name. This (not having a proxy 
	     definition for a host name) is not an error condition. */
	  String  emptyString = "";
	  String  notEmptyString = "notEmptyString";
	  if (emptyString.equals(notEmptyString)) {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBuildJsNoCompression.getJsBuildJs(protocolType, null, 
									                              		                                  hostName, divisionName, siteName, mods,  
									                              		                                  sessionObj, 
									                              		                                  logMatching, 
									                              		                                  serverName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Secure host name string passed to getJsBuildJs is null",
					         "Unexpected exception message");
		}	  
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBuildJsNoCompression.getJsBuildJs(protocolType, secureHostName, 
										                                                                  null, divisionName, siteName, mods, 
										                                                                  sessionObj, 
										                                                                  logMatching, 
										                                                                  serverName);},
									                       "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Host name string passed to getJsBuildJs is null",
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBuildJsNoCompression.getJsBuildJs(protocolType, secureHostName,  
										                                                                  hostName, null, siteName, mods, 
										                                                                  sessionObj, 
										                                                                  logMatching, 
										                                                                  serverName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Division name string passed to getJsBuildJs is null",
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBuildJsNoCompression.getJsBuildJs(protocolType, secureHostName, 
					                                                                            hostName, divisionName, null, mods, 
					                                                                            sessionObj,  
					                                                                            logMatching, 
					                                                                            serverName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Site name string passed to getJsBuildJs is null",
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBuildJsNoCompression.getJsBuildJs(protocolType, secureHostName,  
					                                                                            hostName, divisionName, siteName, null, 
					                                                                            sessionObj,
					                                                                            logMatching, 
					                                                                            serverName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Modifications array passed to getJsBuildJs is null",
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmBuildJsNoCompression.getJsBuildJs(protocolType, secureHostName,  
					                                                                            hostName, divisionName, siteName, mods, 
					                                                                            null,
					                                                                            logMatching, 
					                                                                            serverName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Session object passed to getJsBuildJs is null",
					         "Unexpected exception message");
		}
 	}
}
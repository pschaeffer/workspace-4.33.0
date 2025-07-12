package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
/**
 * HDLmModTest short summary.
 *
 * HDLmModTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmModTest {
  @BeforeAll
	static void HDLmModBeforeAll() {
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree  modTopTree = HDLmTree.addToTreeMod(HDLmTreeData.jsonGetModStr, HDLmEditorTypes.MOD);
		HDLmTree.setNodeModTreeTop(modTopTree);
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree  passTopTree = HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		HDLmTree.setNodePassTreeTop(passTopTree);
	}
	@Test
	void HDLmModCopyConstructor() {
		/* Run a few HDLmModCopyConstructor tests */
		String   modName = "OWO Home Bottom Text";
		/* Build a node path for locating a node */
		ArrayList<String>  nodePath = new ArrayList<String>(
		  List.of("Top", "oneworldobservatory.com", "example.com", "example.com",
					    "OWO Home Bottom Text"));
		HDLmTree   locateTree = HDLmTree.locateTreeNode(HDLmTree.getNodeModTreeTop(),
				                                            nodePath);
		assertNotNull(locateTree,
                  "Node should have been located");
		/* Check the modification node details */
		HDLmMod modDetails = locateTree.getDetails();
		assertEquals(0, modDetails.getFinds().size(), "Modification finds size must be zero"); 
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null"); 
		assertNotNull(modDetails.getValues(), "Modification values value must not be null"); 
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.TEXT, modDetails.getType(), "Modification type value must be 'TEXT'");
		assertEquals(9, modDetails.getValuesCount(), "Modification values count must be nine");
		assertNotEquals(null, modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertNotNull(modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNotEquals("Extra Bott", modDetails.getExtra(), "Modification extra value must not be ('Extra Bott)");
		assertNull(modDetails.getExtra(), "Modification extra value must be null");
		assertEquals(modName, modDetails.getName(), "Modification name value must be '" + modName + "'");
		assertEquals("/", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals("/html/body/div[1]/main/section/div/div/div/section/div/div/a", modDetails.getXPath(), 
				         "Modification XPath value must not be null");		
		assertNotNull(modDetails.getXPath(), "Modification XPath value must not be null");
		/* Copy the modification details */
		modDetails = new HDLmMod(modDetails);
		modDetails.setIfNotSetTimes();
		/* Check the modification node details */
		assertEquals(0, modDetails.getFinds().size(), "Modification finds size must be zero"); 
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null"); 
		assertNotNull(modDetails.getValues(), "Modification values value must not be null"); 
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification value name match type must be 'NONE'");
		assertEquals(HDLmModTypes.TEXT, modDetails.getType(), "Modification type value must be 'TEXT'");
		assertEquals(9, modDetails.getValuesCount(), "Modification values count must be nine");
		assertNotEquals(null, modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertNotNull(modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNotEquals("Extra Bott", modDetails.getExtra(), "Modification extra value must not be ('Extra Bott)");
		assertNull(modDetails.getExtra(), "Modification extra value must be null");
		assertEquals(modName, modDetails.getName(), "Modification name value must be '" + modName + "'");
		assertEquals("/", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals("/html/body/div[1]/main/section/div/div/div/section/div/div/a", modDetails.getXPath(), 
				         "Modification XPath value must not be null");		
		assertNotNull(modDetails.getXPath(), "Modification XPath value must not be null");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {new HDLmMod((HDLmMod) null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Old details reference used to build modification details is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void HDLmModJsonConstructor() {
		/* Run a few HDLmModJsonConstructor tests */
		String       modName = "OWO Home Bottom Text";
    JsonParser   parser = new JsonParser();  
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification JSON element */
    modsElement = modsArray.get(11);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement modsElementDetails = modsObject.get("details"); 
    JsonObject modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
		/* Check the modification node details */
		assertEquals(0, modDetails.getFinds().size(), "Modification finds size must be zero"); 
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null"); 
		assertNotNull(modDetails.getValues(), "Modification values value must not be null"); 
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.TEXT, modDetails.getType(), "Modification type value must be 'TEXT'");
		assertEquals(9, modDetails.getValuesCount(), "Modification values count must be nine");
		assertNotEquals(null, modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertNotNull(modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNotEquals("Extra Bott", modDetails.getExtra(), "Modification extra value must not be ('Extra Bott)");
		assertNull(modDetails.getExtra(), "Modification extra value must be null");
		assertEquals(modName, modDetails.getName(), "Modification name value must be '" + modName + "'");
		assertEquals("/", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals("/html/body/div[1]/main/section/div/div/div/section/div/div/a", modDetails.getXPath(), 
				         "Modification XPath value must be correct");		
		assertNotNull(modDetails.getXPath(), "Modification XPath value must not be null");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {new HDLmMod((JsonElement) null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON element used to build modification details is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void apply() { 
		/* Run a few apply tests */
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
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  /* Process each of the extracted modifications */
	  for (HDLmMod currentMod : matchingMods) {
	  	/* All of the modifications should be enabled. This is actually 
	  	   an indirect way of making sure that no errors were found 
	  	   building each modification. */
	  	boolean  modEnabled = currentMod.getEnabled();
	  	assertTrue(modEnabled, "Current modification is not enabled");
	  	/* This code does not support searching for matching HTML elements
 	       using XPath. Several languages do not support searching using
 	       XPath. */
		 	if (currentMod.getXPath() != null &&
		 			!StringUtils.isWhitespace(currentMod.getXPath()))
		 		continue;
	  	boolean  matchFound;
	  	matchFound = currentMod.apply(htmlDoc, pathValueStr, parametersArray, savedChangesArray);	  	
	  	assertEquals(true, matchFound, "Current modification did not match");
	  }
	  /* Check each of the saved changes */
	  HDLmSavedChange  saved;
	  String           newValue;
	  String           oldValue;
	  saved = savedChangesArray.get(0); 
	  assertEquals("Add to Cart Text", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.TITLE, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  assertEquals("TCELESECROFAdd to ala-carte", saved.getNewValue(), "Saved modification new value did not match");
	  assertEquals("Add to Cart", saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(9, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(1); 	
	  assertEquals("Font Color", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTCOLOR, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  assertEquals("#00ff00", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(1, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(2); 	
	  assertEquals("Font Family", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTFAMILY, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  assertEquals("arial", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(3, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(3); 	
	  assertEquals("Font Kerning", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTKERNING, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  assertEquals("TCELESECROFnone", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(4, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(4); 	
	  assertEquals("Font Size", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTSIZE, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  assertEquals("30", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(0, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(5); 	
	  assertEquals("Font Style", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTSTYLE, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  oldValue = "color:#00ff00;font-family:arial;font-kerning:none;font-size:35px;";
	  assertEquals("normal", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(5, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(6); 	
	  assertEquals("Font Weight", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTWEIGHT, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  oldValue = "color:#00ff00;font-family:arial;font-kerning:none;font-size:35px;font-style:italic;";
	  assertEquals("bold", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(6, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(7); 	
	  assertEquals("Logo Height", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.HEIGHT, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  oldValue = "43";
	  assertEquals("50%", newValue, "Saved modification new value did not match");
	  assertEquals(oldValue, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(8, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(8); 	
	  assertEquals("Logo Width", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.WIDTH, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  oldValue = "148";
	  assertEquals("450px", newValue, "Saved modification new value did not match");
	  assertEquals(oldValue, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(7, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  {
	  	HDLmMod  currentMod = matchingMods.get(0);
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.apply(null, 
					                              		                     pathValueStr, 
					                              		                     parametersArray, 
					                              		                     savedChangesArray);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("HTML DOM passed to apply is null", execMsg,
					         "Unexpected exception message");
		}	
	  {
	  	HDLmMod  currentMod = matchingMods.get(0);
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.apply(htmlDoc, 
					                              		                     null, 
					                              		                     parametersArray, 
					                              		                     savedChangesArray);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path value string passed to apply is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmMod  currentMod = matchingMods.get(0);
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.apply(htmlDoc, 
					                              		                     pathValueStr, 
					                              		                     null, 
					                              		                     savedChangesArray);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Parameters array passed to apply is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmMod  currentMod = matchingMods.get(0);
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.apply(htmlDoc, 
					                              		                     pathValueStr, 
					                              		                     parametersArray, 
					                              		                     null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Saved changes array passed to apply is null", execMsg,
					         "Unexpected exception message");
		}
	}	
	@Test
	void applyStyle() { 
		/* Run a few applyStyle tests */
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
    /* Build an associative array for converting modification type names
       to style font contents names */
    Map<String, String>  fontNames = Map.ofEntries(       		
		  Map.entry("FONTCOLOR", "color"),
		  Map.entry("FONTFAMILY" , "font-family"),
		  Map.entry("FONTKERNING" , "font-kerning"),
		  Map.entry("FONTSTYLE" , "font-style"),
		  Map.entry("FONTWEIGHT" , "font-weight"));
		/* Build an array list for the matching modifications */
		ArrayList<HDLmMod>  matchingMods = new ArrayList<HDLmMod>();
 		HDLmPassThruStatus  passThru = HDLmPassThruStatus.PASSTHRUNOTOK;
	  /* Try to extract some matching modifications */
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  /* Process each of the extracted modifications */
	  for (HDLmMod currentMod : matchingMods) {
	  	/* All of the modifications should be enabled. This is actually 
 	       an indirect way of making sure that no errors were found 
 	       building each modification. */
 	    boolean  modEnabled = currentMod.getEnabled();
 	    assertTrue(modEnabled, "Current modification is not enabled");
	  	HDLmModTypes   currentType = currentMod.getType();
	  	/* Skip the modification types that don't use apply style */
	  	if (currentType != HDLmModTypes.FONTCOLOR   &&
	  			currentType != HDLmModTypes.FONTFAMILY  &&
	  	    currentType != HDLmModTypes.FONTKERNING &&
	  		  currentType != HDLmModTypes.FONTSTYLE   &&
	  		  currentType != HDLmModTypes.FONTWEIGHT) 
	  		continue;
	  	/* This code does not support searching for matching HTML elements
 	       using XPath. Several languages do not support searching using
 	       XPath. */
		 	if (currentMod.getXPath() != null &&
		 			!StringUtils.isWhitespace(currentMod.getXPath()))
		 		continue;
	  	boolean  matchFound;
	  	String   fontText = fontNames.get(currentType.toString());
      /* Get a few values */
      Integer parameterNumber = currentMod.getParameterNumber();
      Double parameterValue = 0.0;
      if (parameterNumber != null &&
      		parameterNumber >= 0    && 
      		parameterNumber < parametersArray.size())
        parameterValue = parametersArray.get(parameterNumber);
	  	matchFound = currentMod.applyStyle(savedChangesArray, htmlDoc, currentType,
	  			                               fontText, parameterNumber, parameterValue); 	
	  	assertEquals(true, matchFound, "Current modification did not match");
	  }
	  /* Check each of the saved changes */
	  HDLmSavedChange  saved;
	  String           newValue;
	  String           oldValue;
	  saved = savedChangesArray.get(0); 	
	  assertEquals("Font Color", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTCOLOR, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  assertEquals("#00ff00", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(1, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(1); 	
	  assertEquals("Font Family", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTFAMILY, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  assertEquals("arial", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(3, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(2); 	
	  assertEquals("Font Kerning", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTKERNING, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  assertEquals("TCELESECROFnone", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(4, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(3); 	
	  assertEquals("Font Style", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTSTYLE, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  oldValue = "color:#00ff00;font-family:arial;font-kerning:none;";
	  assertEquals("normal", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(5, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  saved = savedChangesArray.get(4); 	
	  assertEquals("Font Weight", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.FONTWEIGHT, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  newValue = saved.getNewValue();
	  oldValue = "color:#00ff00;font-family:arial;font-kerning:none;font-style:italic;";
	  assertEquals("bold", newValue, "Saved modification new value did not match");
	  assertEquals(null, saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(6, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  {
	  	HDLmMod        currentMod = matchingMods.get(1);
	  	HDLmModTypes   currentType = currentMod.getType();
	  	String         fontText = fontNames.get(currentType.toString());
      /* Get a few values */
      Integer parameterNumber = currentMod.getParameterNumber();
      Double parameterValue = 0.0;
      if (parameterNumber != null &&
      		parameterNumber >= 0    && 
      		parameterNumber < parametersArray.size())
        parameterValue = parametersArray.get(parameterNumber);
      Double parameterValueLocal = parameterValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.applyStyle(null, 
					                              		                          htmlDoc, 
					                              		                          currentType,
			  			                                                        fontText, 
			  			                                                        parameterNumber, 
			  			                                                        parameterValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Saved changes array passed to applyStyle is null", execMsg,
					         "Unexpected exception message");
		}	
	  {
	  	HDLmMod        currentMod = matchingMods.get(1);
	  	HDLmModTypes   currentType = currentMod.getType();
	  	String         fontText = fontNames.get(currentType.toString());
      /* Get a few values */
      Integer parameterNumber = currentMod.getParameterNumber();
      Double parameterValue = 0.0;
      if (parameterNumber != null &&
      		parameterNumber >= 0    && 
      		parameterNumber < parametersArray.size())
        parameterValue = parametersArray.get(parameterNumber);
      Double parameterValueLocal = parameterValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.applyStyle(savedChangesArray, 
					                              		                          null, 
					                              		                          currentType,
			  			                                                        fontText, 
			  			                                                        parameterNumber, 
			  			                                                        parameterValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("HTML DOM passed to applyStyle is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmMod        currentMod = matchingMods.get(1);
	  	HDLmModTypes   currentType = currentMod.getType();
	  	String         fontText = fontNames.get(currentType.toString());
      /* Get a few values */
      Integer parameterNumber = currentMod.getParameterNumber();
      Double parameterValue = 0.0;
      if (parameterNumber != null &&
      		parameterNumber >= 0    && 
      		parameterNumber < parametersArray.size())
        parameterValue = parametersArray.get(parameterNumber);
      Double parameterValueLocal = parameterValue;
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {currentMod.applyStyle(savedChangesArray, 
					                              		                          htmlDoc, 
					                              		                          HDLmModTypes.NONE,
			  			                                                        fontText, 
			  			                                                        parameterNumber, 
			  			                                                        parameterValueLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Modification type value passed to applyStyle is not set", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmMod        currentMod = matchingMods.get(1);
	  	HDLmModTypes   currentType = currentMod.getType();
	  	String         fontText = fontNames.get(currentType.toString());
      /* Get a few values */
      Integer parameterNumber = currentMod.getParameterNumber();
      Double parameterValue = 0.0;
      if (parameterNumber != null &&
      		parameterNumber >= 0    && 
      		parameterNumber < parametersArray.size())
        parameterValue = parametersArray.get(parameterNumber);
      Double parameterValueLocal = parameterValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.applyStyle(savedChangesArray, 
					                              		                          htmlDoc, 
					                              		                          null,
			  			                                                        fontText, 
			  			                                                        parameterNumber, 
			  			                                                        parameterValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Modification type value passed to applyStyle is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmMod        currentMod = matchingMods.get(1);
	  	HDLmModTypes   currentType = currentMod.getType();
	  	String         fontText = fontNames.get(currentType.toString());
      /* Get a few values */
      Integer parameterNumber = currentMod.getParameterNumber();
      Double parameterValue = 0.0;
      if (parameterNumber != null &&
      		parameterNumber >= 0    && 
      		parameterNumber < parametersArray.size())
        parameterValue = parametersArray.get(parameterNumber);
      Double parameterValueLocal = parameterValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.applyStyle(savedChangesArray, 
					                              		                          htmlDoc, 
					                              		                          currentType,
			  			                                                        null, 
			  			                                                        parameterNumber, 
			  			                                                        parameterValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Font text passed to applyStyle is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmMod        currentMod = matchingMods.get(1);
	  	HDLmModTypes   currentType = currentMod.getType();
	  	String         fontText = fontNames.get(currentType.toString());
      /* Get a few values */
      Integer parameterNumber = currentMod.getParameterNumber();
      Double parameterValue = 0.0;
      if (parameterNumber != null &&
      		parameterNumber >= 0    && 
      		parameterNumber < parametersArray.size())
        parameterValue = parametersArray.get(parameterNumber);
      Double parameterValueLocal = parameterValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.applyStyle(savedChangesArray, 
					                              		                          htmlDoc, 
					                              		                          currentType,
			  			                                                        fontText, 
			  			                                                        null, 
			  			                                                        parameterValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Parameter number passed to applyStyle is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmMod        currentMod = matchingMods.get(1);
	  	HDLmModTypes   currentType = currentMod.getType();
	  	String         fontText = fontNames.get(currentType.toString());
      /* Get a few values */
      Integer parameterNumber = currentMod.getParameterNumber();
      Double parameterValue = 0.0;
      if (parameterNumber != null &&
      		parameterNumber >= 0    && 
      		parameterNumber < parametersArray.size())
        parameterValue = parametersArray.get(parameterNumber);
      Double parameterValueLocal = parameterValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.applyStyle(savedChangesArray, 
					                              		                          htmlDoc, 
					                              		                          currentType,
			  			                                                        fontText, 
			  			                                                        parameterNumber, 
			  			                                                        null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Parameter value passed to applyStyle is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void copyMod() { 
		/* Run a few copyMod tests */
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
	  matchingMods = HDLmMod.extractMods(pathValueStr, 
	  		                               locateTree, 
	  		                               passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  HDLmMod  modDetails = matchingMods.get(0);
	  String   modName = "Add to Cart Text";
		/* Check the modification node details */
		assertEquals(1, modDetails.getFinds().size(), "Modification finds size must be one"); 
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null"); 
		assertNotNull(modDetails.getValues(), "Modification values value must not be null"); 
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		
		assertEquals(HDLmModTypes.TITLE, modDetails.getType(), "Modification type value must be 'TITLE'");
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
		/* Copy the modification */
		modDetails = modDetails.copyMod();
		/* Check the modification node details */
		assertEquals(1, modDetails.getFinds().size(), "Modification finds size must be one"); 
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null"); 
		assertNotNull(modDetails.getValues(), "Modification values value must not be null"); 
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.TITLE, modDetails.getType(), "Modification type value must be 'TITLE'");
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
	}
	@Test
	void extractMods() { 
		/* Run a few extractMods tests */
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
	  String              pathValueStr = "/neve-studio-dance-jacket.html";
	  /* Try to extract some matching modifications */
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  /* Try a different (bad) path value string */
	  pathValueStr = "/neve-studio-dance-jacket.htmlx";
	  /* Try to extract some matching modifications */
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  extractCount = matchingMods.size();
	  assertEquals(0, extractCount, "Incorrect number of modifications extracted");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.extractMods(null, 
					                              		                        locateTree, 
					                              		                        passThru,
					                              		                        HDLmUsePathValue.USEPATHVALUEOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path value string passed to extract modifications is null", execMsg,
					         "Unexpected exception message");
		}	  
	}
	@Test
	void find() { 
		/* Run a few find tests */
		/* Get the HTML string and build a document from it */
	  String     htmlStr = HDLmTreeData.magentoNeveJacketStr();	
	  String     pathValueStr = "/neve-studio-dance-jacket.html";
	  Document   htmlDoc = Jsoup.parse(htmlStr);
	  /* Build a node path for locating a site node */
		ArrayList<String>  nodePath = new ArrayList<String>(
		  List.of("Top", "legends-magento.dnsalias.com", "example.com", "example.com"));
		HDLmTree   locateTree = HDLmTree.locateTreeNode(HDLmTree.getNodeModTreeTop(),
				                                            nodePath);
		assertNotNull(locateTree,
                  "Site Node should have been located");		
		/* Extract the matching modifications */
		ArrayList<HDLmMod>  matchingMods = new ArrayList<HDLmMod>();
		HDLmPassThruStatus  passThru = HDLmPassThruStatus.PASSTHRUNOTOK;
	  /* Try to extract some matching modifications */
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  /* Process each of the extracted modifications */
	  int  modCounter = 0;
	  for (HDLmMod currentMod : matchingMods) {
	  	modCounter++;
	  	ArrayList<HDLmFind>  findsInfo = currentMod.getFinds();
	  	if (findsInfo == null)
	  		continue;	  	 
	  	/* This code does not support searching for matching HTML elements
	  	   using XPath. Several languages do not support searching using 
	  	   XPath. */
	  	if (currentMod.getXPath() != null &&
	  			!StringUtils.isWhitespace(currentMod.getXPath()))
	  		continue;
	  	Elements   nodeList = currentMod.find(htmlDoc); 
	  	int        nodeListLen = nodeList.size();
	  	if (modCounter >= 1 && modCounter <= 11)
	  		assertEquals(1, nodeListLen, "Incorrect number of matching nodes");
	  }
	  {
	  	HDLmMod  currentMod = matchingMods.get(0);
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {currentMod.find(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("HTML DOM passed to find is null", execMsg,
					         "Unexpected exception message");
		}	  
	}
	@Test
	void getJsonSerializeNulls() { 
		/* Run a few getJson tests */
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
		if (1 == 1)
			return;
		assertNotNull(locateTree,
                  "Site Node should have been located");		
		/* Run a few extractMods tests */
		ArrayList<HDLmMod>  matchingMods = new ArrayList<HDLmMod>();
		HDLmPassThruStatus  passThru = HDLmPassThruStatus.PASSTHRUNOTOK;
	  /* Try to extract some matching modifications */
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  HDLmMod  modDetails = matchingMods.get(0);
	  String   modName = "Add to Cart Text";
		/* Check the modification node details */
		assertEquals(1, modDetails.getFinds().size(), "Modification finds size must be one"); 
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null"); 
		assertNotNull(modDetails.getValues(), "Modification values value must not be null"); 
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.TITLE, modDetails.getType(), "Modification type value must be 'TITLE'");
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
		String   modJson = modDetails.getJsonSerializeNulls();
    modJson = modJson.replace(",\"path\":\"/neve-studio-dance-jacket.html\"",
                              "");
    modJson = modJson.replace(",\"name\":\"Add to Cart Text\"",
                              ",\"name\":\"Add to Cart Text\",\"path\":\"/neve-studio-dance-jacket.html\"");
		String   modJsonExpected = "" + 
	 	  "{\"find\":[{\"tag\":\"button\",\"attribute\":\"id\",\"value\":\"product-addtocart-button\"}]," +
		  "\"values\":[\"ADD TO CART\",\"TCELESECROFAdd to ala-carte\"]," +
	 	  "\"enabled\":true,\"pathre\":false,\"type\":" +
		  "\"title\",\"parameter\":9,\"cssselector\":null," +
	 	  "\"comments\":null,\"passThru\":null,\"dummyTable\":null," +
		  "\"dummyType\":null,\"updated\":null," +
	 	  "\"extra\":null,\"name\":" +
		  "\"Add to Cart Text\",\"path\":\"/neve-studio-dance-jacket.html\",\"nodeiden\":null," + 
		  "\"value\":null,\"valueSuffix\":" +
		  "null,\"xpath\":null}";		 
		assertEquals(modJsonExpected, modJson, "JSON from modification does not match expected value");
	}
	@Test
	void getJsonSpecialSerializeNulls() { 
		/* Run a few getJsonSpecial tests */
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
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
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
		assertEquals(HDLmModTypes.TITLE, modDetails.getType(), "Modification type value must be 'TITLE'");
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
		  "\"extra\":\"\",\"name\":" +		  
		  "\"Add to Cart Text\",\"nodeiden\":null,\"path\":\"/neve-studio-dance-jacket.html\"," +
		  "\"prob\":100.0," +
		  "\"usemode\":\"prod\"," +
		  "\"value\":\"\",\"valueSuffix\":" +
		  "\"\",\"xpath\":\"\"}";		
		/* The JSON string needs to be modified in a few ways. Some 
	     values really don't need to be serialized. */ 
		modJsonExpected = modJsonExpected.replace("\"dummyTable\":null,", "");  
		modJsonExpected = modJsonExpected.replace("\"dummyType\":null,", "");
		modJsonExpected = modJsonExpected.replace("\"passThru\":null,", "");
		modJsonExpected = modJsonExpected.replace("\"updated\":null,", "");
		assertEquals(modJsonExpected, modJson, "JSON from modification does not match expected value");
	}
	@Test
	void getOldStyleValue() { 
		/* Run a few getOldStyleValue tests */
		String   oldValue = "color:#00ff00;font-family:arial;font-kerning:none;";
		String   styleName = "font-family";
		/* Try to get the old font family value */
		String   oldStyleValue = HDLmMod.getOldStyleValue(oldValue, styleName);
		assertEquals(oldStyleValue, "arial", "Incorrect old style value obtained");
		/* Run another test that should return null */
		oldStyleValue = HDLmMod.getOldStyleValue(oldValue, "font-familyx");
		assertEquals(null, oldStyleValue, "Old style value should be null");
		/* Run another test that should not return null */
		oldStyleValue = HDLmMod.getOldStyleValue(oldValue, "color");
		assertEquals("#00ff00", oldStyleValue, "Incorrect old style value obtained");
		/* Run another test that should not return null */
		oldStyleValue = HDLmMod.getOldStyleValue(oldValue, "font-kerning");
		assertEquals("none", oldStyleValue, "Incorrect old style value obtained");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.getOldStyleValue(null, "");},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Old style string passed to getOldStyleValue is null", execMsg,
					         "Unexpected exception message");
		}	
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.getOldStyleValue(oldValue, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Style name passed to getOldStyleValue is null", execMsg,
					         "Unexpected exception message");
		}	
	}
  @Test
	void modArrayCheck() { 
		/* Run a few modArrayCheck tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(1);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the modification values */
    JsonElement  modsElementValues = modsElementObject.get("newtexts");
    JsonArray    modsObjectArray = modsElementValues.getAsJsonArray();
    MutableInt   errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    Integer      modsArraySize;
    modsArraySize = HDLmMod.modArrayCheck(editorType, 
    		                                  errorCounter, 
    		                                  errorMessages,
    		                                  modsElementObject, 
    		                                  "newtexts", 
    		                                  2,
    		                                  HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    assertEquals(2, modsArraySize, "Incorrect array size value returned");
    modsArraySize = HDLmMod.modArrayCheck(editorType, 
    		                                  errorCounter, 
    		                                  errorMessages,
    		                                  modsElementObject, 
    		                                  "newtexts", 
    		                                  0,
    		                                  HDLmZeroLengthOk.ZEROLENGTHNOTOK,
    		                                  HDLmReportErrors.DONTREPORTERRORS);
    assertEquals(null, modsArraySize, "Returned array size value should be null");
    int  intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");   
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modArrayCheck(editorType, 
					                              		                          null, 
					                              		                          errorMessages,
					                              		                          modsElementObject, 
					                              		                          "newtexts", 
					                              		                          2,
					                              		                          HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to modArrayCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modArrayCheck(editorType, 
					                              		                          errorCounter, 
					                              		                          null,
					                              		                          modsElementObject, 
					                              		                          "newtexts", 
					                              		                          2,
					                              		                          HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to modArrayCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modArrayCheck(editorType, 
					                              		                          errorCounter, 
					                              		                          errorMessages, 
					                              		                          null,
					                              		                          "newtexts", 
					                              		                          2,
					                              		                          HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to modArrayCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modArrayCheck(editorType, 
					                              		                          errorCounter, 
					                              		                          errorMessages,
					                              		                          modsElementObject, 
					                              		                          null, 
					                              		                          2,
					                              		                          HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to modArrayCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modArrayCheck(editorType, 
					                              		                          errorCounter, 
					                              		                          errorMessages,
					                              		                          modsElementObject, 
					                              		                          "newtexts", 
					                              		                          null,
					                              		                          HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Array size reference passed to modArrayCheck is null", execMsg,
					         "Unexpected exception message");
		}	  
	}
  @Test
	void modFieldBoolean() { 
		/* Run a few modFieldBoolean tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(1);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the modification values */
    JsonElement  modsElementValues = modsElementObject.get("newtexts");
    JsonArray    modsObjectArray = modsElementValues.getAsJsonArray();
    MutableInt   errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    Boolean      modEnabled;
    modEnabled = HDLmMod.modFieldBoolean(editorType, 
    		                                 errorCounter,
    		                                 errorMessages,
    		                                 modsElementObject, 
    		                                 modsElementKeys, 
    		                                 "enabled");
    assertEquals(true, modEnabled, "Incorrect modification enablement status");
    modEnabled = HDLmMod.modFieldBoolean(editorType, 
    		                                 errorCounter, 
    		                                 errorMessages,
    		                                 modsElementObject, 
    		                                 modsElementKeys, 
    		                                 "enabledx", 
    		                                 HDLmReportErrors.DONTREPORTERRORS);
    assertEquals(null, modEnabled, "Returned array size value should be null");
    int  intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");    
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldBoolean(editorType, 
					                              		                            null, 
					                              		                            errorMessages,
					                              		                            modsElementObject, 
					                              		                            modsElementKeys, 
					                              		                            "enabled");},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to modFieldBoolean is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldBoolean(editorType, 
					                              		                            errorCounter,
					                              		                            null,
					                              		                            modsElementObject, 
					                              		                            modsElementKeys, 
					                              		                            "enabled");},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to modFieldBoolean is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldBoolean(editorType, 
					                              		                            errorCounter,
					                              		                            errorMessages,
					                              		                            null, 
					                              		                            modsElementKeys, 
					                              		                            "enabled");},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to modFieldBoolean is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldBoolean(editorType, 
					                              		                            errorCounter,
					                              		                            errorMessages,
					                              		                            modsElementObject, 
					                              		                            null,
					                              		                            "enabled");},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Set of keys passed to modFieldBoolean is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldBoolean(editorType, 
					                              		                            errorCounter,
					                              		                            errorMessages,
					                              		                            modsElementObject, 
					                              		                            modsElementKeys, 
					                              		                            null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to modFieldBoolean is null", execMsg,
					         "Unexpected exception message");
		}	  
	}
  @Test
	void modFieldFind() { 
		/* Run a few modFieldFind tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the modification finds */
    String       fieldName = "find";
    JsonElement  modsElementFinds = modsElementObject.get(fieldName);
    JsonArray    modsObjectArray = modsElementFinds.getAsJsonArray();
    /* Try to access the JSON element for one modification find */
    JsonElement  modsElementFind = modsObjectArray.get(0);
    JsonObject   modsObjectFind = modsElementFind.getAsJsonObject();
    Set<String>  modsFindKeys = modsObjectFind.keySet();
    MutableInt   errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    HDLmFind     modFind;
    modFind = HDLmMod.modFieldFind(editorType, 
    		                           errorCounter,
    		                           errorMessages,
    		                           modsObjectFind, 
    		                           modsFindKeys);
    assertNotNull(modFind, "Modification find value is null");
    modFind = HDLmMod.modFieldFind(editorType, 
    		                           errorCounter, 
    		                           errorMessages,
    		                           modsObjectFind, 
    		                           modsElementKeys, 
    		                           HDLmReportErrors.DONTREPORTERRORS);
    assertNotNull(modFind, "Modification find value is null");
    int  intValue = errorCounter.intValue();
    assertEquals(3, intValue, "Error value must be three");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFind(editorType, 
					                              		                         null, 
					                              		                         errorMessages,
					                              		                         modsElementObject, 
					                              		                         modsElementKeys);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to modFieldFind is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFind(editorType, 
					                              		                         errorCounter,
					                              		                         null,
					                              		                         modsElementObject, 
					                              		                         modsElementKeys);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to modFieldFind is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFind(editorType, 
					                              		                         errorCounter, 
					                              		                         errorMessages,
					                              		                         null, 
					                              		                         modsElementKeys);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to modFieldFind is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFind(editorType, 
					                              		                         errorCounter,
					                              		                         errorMessages,
					                              		                         modsElementObject, 
					                              		                         null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Set of keys passed to modFieldFind is null", execMsg,
					         "Unexpected exception message");
		}  
	}
  @Test
	void modFieldFindArray() { 
		/* Run a few modFieldFindArray tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the modification finds  */
    String   fieldName = "find";
    JsonElement  modsElementFinds = modsElementObject.get(fieldName);
    JsonArray    modsObjectFinds = modsElementFinds.getAsJsonArray();
    MutableInt   errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    ArrayList<HDLmFind>  modFinds;
    modFinds = HDLmMod.modFieldFindArray(editorType, 
    		                                 errorCounter, 
    		                                 errorMessages, 
    		                                 modsElementObject, 
    		                                 modsElementKeys, 
    		                                 fieldName);
    assertNotNull(modFinds, "Modification finds array value is null");
    modFinds = HDLmMod.modFieldFindArray(editorType, 
    		                                 errorCounter, 
    		                                 errorMessages, 
    		                                 modsElementObject, 
    		                                 modsElementKeys, 
    		                                 "findx", 
    		                                 HDLmReportErrors.DONTREPORTERRORS);
    assertNull(modFinds, "Modification finds array value is not null");
    int  intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be three");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFindArray(editorType, 
					                              		                              null, 
					                              		                              errorMessages,
					                              		                              modsElementObject, 
					                              		                              modsElementKeys, 
					                              		                              fieldName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to modFieldFindArray is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFindArray(editorType, 
					                              		                              errorCounter, 
					                              		                              null,
					                              		                              modsElementObject, 
					                              		                              modsElementKeys, 
					                              		                              fieldName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to modFieldFindArray is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFindArray(editorType, 
					                              		                              errorCounter, 
					                              		                              errorMessages, 
					                              		                              null, 
					                              		                              modsElementKeys, 
					                              		                              fieldName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to modFieldFindArray is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFindArray(editorType, 
					                              		                              errorCounter, 
					                              		                              errorMessages, 
					                              		                              modsElementObject, 
					                              		                              null, 
					                              		                              fieldName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Set of keys passed to modFieldFindArray is null", execMsg,
					         "Unexpected exception message");
		}  
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldFindArray(editorType, 
					                              		                              errorCounter, 
					                              		                              errorMessages, 
					                              		                              modsElementObject, 
					                              		                              modsElementKeys, 
					                              		                              null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to modFieldFindArray is null", execMsg,
					         "Unexpected exception message");
		} 
	}
  @Test
	void modFieldInteger() { 
		/* Run a few modFieldInteger tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the parameter number  */
    String         fieldName = "parameter";
    JsonElement    modsElementParameterNumber = modsElementObject.get(fieldName);
    JsonPrimitive  modsPrimitiveParameterNumber = modsElementParameterNumber.getAsJsonPrimitive();
    MutableInt     errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    Integer        modInteger;
    modInteger = HDLmMod.modFieldInteger(editorType, 
    		                                 errorCounter, 
    		                                 errorMessages, 
    		                                 modsElementObject, 
    		                                 modsElementKeys, 
    		                                 fieldName);
    assertNotNull(modInteger, "Modification parameter number value is null");
    assertEquals(9, modInteger, "Modification parameter number value is not nine");
    modInteger = HDLmMod.modFieldInteger(editorType, 
    		                                 errorCounter, 
    		                                 errorMessages, 
    		                                 modsElementObject, 
    		                                 modsElementKeys, 
    		                                 fieldName + "x", 
    		                                 HDLmReportErrors.DONTREPORTERRORS);
    assertNull(modInteger, "Modification parameter number value is not null");
    int  intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldInteger(editorType, 
					                              		                            null, 
					                              		                            errorMessages,
					                              		                            modsElementObject, 
					                              		                            modsElementKeys, 
					                              		                            fieldName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to modFieldInteger is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldInteger(editorType, 
					                              		                            errorCounter, 
					                              		                            null,
					                              		                            modsElementObject, 
					                              		                            modsElementKeys, 
					                              		                            fieldName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to modFieldInteger is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldInteger(editorType, 
					                              		                            errorCounter, 
					                              		                            errorMessages, 
					                              		                            null, 
					                              		                            modsElementKeys, 
					                              		                            fieldName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to modFieldInteger is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldInteger(editorType, 
					                              		                            errorCounter, 
					                              		                            errorMessages, 
					                              		                            modsElementObject, 
					                              		                            null, 
					                              		                            fieldName);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Set of keys passed to modFieldInteger is null", execMsg,
					         "Unexpected exception message");
		}  
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFieldInteger(editorType, 
					                              		                            errorCounter, 
					                              		                            errorMessages, 
					                              		                            modsElementObject, 
					                              		                            modsElementKeys, 
					                              		                            null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to modFieldInteger is null", execMsg,
					         "Unexpected exception message");
		} 
	}
  @Test
 	void modFieldModType() { 
 		/* Run a few modFieldModType tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
 		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the modification type */
    String         fieldName = "type";
    JsonElement    modsElementType = modsElementObject.get(fieldName);
    JsonPrimitive  modsPrimitiveType = modsElementType.getAsJsonPrimitive();
    MutableInt     errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    HDLmModTypes   modType;
    modType = HDLmMod.modFieldModType(editorType, 
    		                              errorCounter, 
    		                              errorMessages, 
    		                              modsElementObject, 
    		                              modsElementKeys, 
    		                              fieldName);
    assertNotNull(modType, "Modification type value is null");
    assertEquals(HDLmModTypes.TITLE, modType, "Modification type value is not 'TITLE'");
    modType = HDLmMod.modFieldModType(editorType, 
    		                              errorCounter, 
    		                              errorMessages, 
    		                              modsElementObject, 
    		                              modsElementKeys, 
    		                              fieldName + "x", 
    		                              HDLmReportErrors.DONTREPORTERRORS);
    assertEquals(HDLmModTypes.NONE, modType, "Modification type value is not 'NONE'");
    int  intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmMod.modFieldModType(editorType, 
 					                              		                            null, 
 					                              		                            errorMessages,
 					                              		                            modsElementObject, 
 					                              		                            modsElementKeys, 
 					                              		                            fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals("Mutable int for error counter passed to modFieldModType is null", execMsg,
 					         "Unexpected exception message");
 		}
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmMod.modFieldModType(editorType, 
 					                              		                            errorCounter,
 					                              		                            null,
 					                              		                            modsElementObject, 
 					                              		                            modsElementKeys, 
 					                              		                            fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals("ArrayList for error messages passed to modFieldModType is null", execMsg,
 					         "Unexpected exception message");
 		}
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmMod.modFieldModType(editorType, 
 					                              		                            errorCounter, 
 					                              		                            errorMessages, 
 					                              		                            null, 
 					                              		                            modsElementKeys, 
 					                              		                            fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals("JSON object passed to modFieldModType is null", execMsg,
 					         "Unexpected exception message");
 		}
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmMod.modFieldModType(editorType, 
 					                              		                            errorCounter, 
 					                              		                            errorMessages, 
 					                              		                            modsElementObject, 
 					                              		                            null, 
 					                              		                            fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals("Set of keys passed to modFieldModType is null", execMsg,
 					         "Unexpected exception message");
 		}  
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmMod.modFieldModType(editorType, 
 					                              		                            errorCounter, 
 					                              		                            errorMessages, 
 					                              		                            modsElementObject, 
 					                              		                            modsElementKeys, 
 					                              		                            null);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals("Name string passed to modFieldModType is null", execMsg,
 					         "Unexpected exception message");
 		} 
 	}
  @Test
	void modFieldString() { 
		/* Run a few modFieldString tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the modification path value */
    String         fieldName = "path";
    JsonElement    modsElementPathValue = modsElementObject.get(fieldName);
    JsonPrimitive  modsPrimitivePathValue = modsElementPathValue.getAsJsonPrimitive();
    MutableInt     errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    String         modString;
    {
			String         errorMessagePrefix = "Modification";
			int            errorNumberMissing = 3; 
	    int            errorNumberIsNull = 4;
	    int            errorNumberNotPrimitive = 4;
	    int            errorNumberException = 4;
	    int            errorNumberInvalidLength = 4;
	    int            errorNumberInvalidWhiteSpace = 4;	
	    modString = HDLmField.checkFieldString(editorType, 
			    		                               errorCounter, 
			    		                               errorMessages, 
			    		                               modsElementObject, 
			    		                               modsElementKeys, 
			    		                               fieldName, 
			    		                               errorMessagePrefix,
															     	     	   errorNumberMissing,
															               errorNumberIsNull,
															               errorNumberNotPrimitive,
															               errorNumberException,
															               errorNumberInvalidLength,
															               errorNumberInvalidWhiteSpace, 
    		                                     HDLmWhiteSpace.WHITESPACENOTOK, 
    	                                    	 HDLmReportErrors.DONTREPORTERRORS,
    	                                    	 HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    assertNotNull(modString, "Modification path value is null");
    assertEquals("/neve-studio-dance-jacket.html", modString, "Modification path value is invalid");
    modString = HDLmField.checkFieldString(editorType, 
		    		                               errorCounter, 
		    		                               errorMessages,
		    		                               modsElementObject, 
		    		                               modsElementKeys, 
		    		                               fieldName + "x", 
		    		                               errorMessagePrefix,
														     	     	   errorNumberMissing,
														               errorNumberIsNull,
														               errorNumberNotPrimitive,
														               errorNumberException,
															             errorNumberInvalidLength,
															             errorNumberInvalidWhiteSpace, 
		                                    	 HDLmWhiteSpace.WHITESPACENOTOK, 
		                                    	 HDLmReportErrors.DONTREPORTERRORS,
		                                    	 HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    assertNull(modString, "Modification path value is not null");
    int  intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
    }
	  {
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldString(editorType, 
							                              		                           null, 
							                              		                           errorMessages,
							                              		                           modsElementObject, 
							                              		                           modsElementKeys, 
							                              		                           fieldName, 
							                              		                           errorMessagePrefix,
							 																						     	     	     errorNumberMissing,
							 																						                 errorNumberIsNull,
							 																						                 errorNumberNotPrimitive,
							 																						                 errorNumberException,
							 																							               errorNumberInvalidLength,
							 																							               errorNumberInvalidWhiteSpace, 
							                              		                           HDLmWhiteSpace.WHITESPACENOTOK, 
							                              		                           HDLmReportErrors.DONTREPORTERRORS,
							                              		                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldString(editorType, 
							                              		                           errorCounter,
							                              		                           null,
							                              		                           modsElementObject, 
							                              		                           modsElementKeys, 
							                              		                           fieldName, 
							                              		                           errorMessagePrefix,
							 																						     	     	     errorNumberMissing,
							 																						                 errorNumberIsNull,
							 																						                 errorNumberNotPrimitive,
							 																						                 errorNumberException,
							 																							               errorNumberInvalidLength,
							 																							               errorNumberInvalidWhiteSpace, 
							                              		                           HDLmWhiteSpace.WHITESPACENOTOK, 
							                              		                           HDLmReportErrors.DONTREPORTERRORS,
							                              		                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldString(editorType, 
							                              		                           errorCounter, 
							                              		                           errorMessages, 
							                              		                           null, 
							                              		                           modsElementKeys, fieldName, 
							                              		                           errorMessagePrefix,
							 																						     	     	     errorNumberMissing,
							 																						                 errorNumberIsNull,
							 																						                 errorNumberNotPrimitive,
							 																						                 errorNumberException,
							 																							               errorNumberInvalidLength,
							 																							               errorNumberInvalidWhiteSpace, 
							                              		                           HDLmWhiteSpace.WHITESPACENOTOK, 
							                              		                           HDLmReportErrors.DONTREPORTERRORS,
							                              		                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldString(editorType, 
							                              		                           errorCounter,
							                              		                           errorMessages,
							                              		                           modsElementObject, 
							                              		                           null, 
							                              		                           fieldName, 
							                              		                           errorMessagePrefix,
							 																						     	     	     errorNumberMissing,
							 																						                 errorNumberIsNull,
							 																						                 errorNumberNotPrimitive,
							 																						                 errorNumberException,
							 																							               errorNumberInvalidLength,
							 																							               errorNumberInvalidWhiteSpace, 
							                              		                           HDLmWhiteSpace.WHITESPACENOTOK, 
							                              		                           HDLmReportErrors.DONTREPORTERRORS,
							                              		                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Set of keys passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		}  
	  { 
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldString(editorType, 
							                              		                           errorCounter,
							                              		                           errorMessages,
							                              		                           modsElementObject, 
							                              		                           modsElementKeys, 
							                              		                           null, 
							                              		                           errorMessagePrefix,
							 																						     	     	     errorNumberMissing,
							 																						                 errorNumberIsNull,
							 																						                 errorNumberNotPrimitive,
							 																						                 errorNumberException,
							 																							               errorNumberInvalidLength,
							 																							               errorNumberInvalidWhiteSpace, 
							                              		                           HDLmWhiteSpace.WHITESPACENOTOK, 
							                              		                           HDLmReportErrors.DONTREPORTERRORS,
							                              		                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		} 
	}
  @Test
	void modFieldStringArray() { 
		/* Run a few modFieldStringArray tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the modification new title values */
    String         fieldName = "titles";
    JsonElement    modsElementTitles = modsElementObject.get(fieldName);
    JsonArray      modsArrayTitles = modsElementTitles.getAsJsonArray();
    MutableInt     errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    ArrayList<String>   modStringArray;
    {
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotPrimitive = 4;
			int     errorNumberNotArray = 4;
			int     errorNumberException = 4;
			int     errorNumberTooSmall = 70;
			int     errorNumberTooLarge = 71; 
			int     errorNumberInvalidLength = 4;
			int     errorNumberInvalidWhiteSpace = 4;
	    modStringArray = HDLmField.checkFieldStringArray(editorType, 
			    		                                         errorCounter, 
			    		                                         errorMessages, 
			    		                                         modsElementObject, 
			    		                                         modsElementKeys, 
			    		                                         fieldName, 
							                                         null,
							                                         null,
							                                         errorMessagePrefix,
							                                         errorNumberMissing,
							                                         errorNumberIsNull,
							                                         errorNumberIsPrimitive,
							                                         errorNumberNotPrimitive,
							                                         errorNumberNotArray,
							                                         errorNumberException,
							                                         errorNumberTooSmall,
							                                         errorNumberTooLarge, 
							                                         errorNumberInvalidLength,
							                                         errorNumberInvalidWhiteSpace,
							                                         HDLmWhiteSpace.WHITESPACENOTOK,
																							         HDLmReportErrors.DONTREPORTERRORS,
																							         HDLmZeroLengthOk.ZEROLENGTHNOTOK);
    }
    assertNotNull(modStringArray, "Modification titles value is null");
    assertEquals("ADD TO CART", modStringArray.get(0), "Modification first title value is invalid");
    assertEquals("TCELESECROFAdd to ala-carte", modStringArray.get(1), "Modification second title value is invalid");
    assertEquals(2, modStringArray.size(), "Modification titles array size value is invalid");
    {
	  	String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotPrimitive = 4;
			int     errorNumberNotArray = 4;
			int     errorNumberException = 4;
			int     errorNumberTooSmall = 70;
			int     errorNumberTooLarge = 71; 
			int     errorNumberInvalidLength = 4;
			int     errorNumberInvalidWhiteSpace = 4;
	    modStringArray = HDLmField.checkFieldStringArray(editorType, 
			    		                                         errorCounter, 
			    		                                         errorMessages, 
			    		                                         modsElementObject, 
			    		                                         modsElementKeys, 
			    		                                         fieldName + "x", 
							                                         null,
							                                         null,
							                                         errorMessagePrefix,
							                                         errorNumberMissing,
							                                         errorNumberIsNull,
							                                         errorNumberIsPrimitive,
							                                         errorNumberNotPrimitive,
							                                         errorNumberNotArray,
							                                         errorNumberException,
							                                         errorNumberTooSmall,
							                                         errorNumberTooLarge, 
							                                         errorNumberInvalidLength,
							                                         errorNumberInvalidWhiteSpace,
							                                         HDLmWhiteSpace.WHITESPACENOTOK,
																							         HDLmReportErrors.DONTREPORTERRORS,
																							         HDLmZeroLengthOk.ZEROLENGTHNOTOK); 
    }
    assertNull(modStringArray, "Modification titles value is not null");
    int  intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
	  {
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotPrimitive = 4;
			int     errorNumberNotArray = 4;
			int     errorNumberException = 4;
			int     errorNumberTooSmall = 70;
			int     errorNumberTooLarge = 71; 
			int     errorNumberInvalidLength = 4;
			int     errorNumberInvalidWhiteSpace = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldStringArray(editorType, 
							                              		                                null, 
							                              		                                errorMessages,
							                              		                                modsElementObject, 
							                              		                                modsElementKeys, 
							                              		                                fieldName, 
							             						                                          null,
							            						                                          null,
							            						                                          errorMessagePrefix,
							            						                                          errorNumberMissing,
							            						                                          errorNumberIsNull,
							            						                                          errorNumberIsPrimitive,
							            						                                          errorNumberNotPrimitive,
							            						                                          errorNumberNotArray,
							            						                                          errorNumberException,
							            						                                          errorNumberTooSmall,
							            						                                          errorNumberTooLarge, 
							            						                                          errorNumberInvalidLength,
							            						                                          errorNumberInvalidWhiteSpace,
							            						                                          HDLmWhiteSpace.WHITESPACENOTOK,
							            																						          HDLmReportErrors.DONTREPORTERRORS,
							            																						          HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to checkFieldStringArray is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotPrimitive = 4;
			int     errorNumberNotArray = 4;
			int     errorNumberException = 4;
			int     errorNumberTooSmall = 70;
			int     errorNumberTooLarge = 71; 
			int     errorNumberInvalidLength = 4;
			int     errorNumberInvalidWhiteSpace = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldStringArray(editorType, 
							                              		                                errorCounter,
							                              		                                null,
							                              		                                modsElementObject, 
							                              		                                modsElementKeys, 
							                              		                                fieldName, 
								             						                                        null,
								            						                                        null,
								            						                                        errorMessagePrefix,
								            						                                        errorNumberMissing,
								            						                                        errorNumberIsNull,
								            						                                        errorNumberIsPrimitive,
								            						                                        errorNumberNotPrimitive,
								            						                                        errorNumberNotArray,
								            						                                        errorNumberException,
								            						                                        errorNumberTooSmall,
								            						                                        errorNumberTooLarge, 
								            						                                        errorNumberInvalidLength,
								            						                                        errorNumberInvalidWhiteSpace,
								            						                                        HDLmWhiteSpace.WHITESPACENOTOK,
								            																						        HDLmReportErrors.DONTREPORTERRORS,
								            																						        HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to checkFieldStringArray is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotPrimitive = 4;
			int     errorNumberNotArray = 4;
			int     errorNumberException = 4;
			int     errorNumberTooSmall = 70;
			int     errorNumberTooLarge = 71; 
			int     errorNumberInvalidLength = 4;
			int     errorNumberInvalidWhiteSpace = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldStringArray(editorType, 
							                              		                                errorCounter, 
							                              		                                errorMessages, 
							                              		                                null, 
							                              		                                modsElementKeys, 
							                              		                                fieldName, 
								             						                                        null,
								            						                                        null,
								            						                                        errorMessagePrefix,
								            						                                        errorNumberMissing,
								            						                                        errorNumberIsNull,
								            						                                        errorNumberIsPrimitive,
								            						                                        errorNumberNotPrimitive,
								            						                                        errorNumberNotArray,
								            						                                        errorNumberException,
								            						                                        errorNumberTooSmall,
								            						                                        errorNumberTooLarge, 
								            						                                        errorNumberInvalidLength,
								            						                                        errorNumberInvalidWhiteSpace,
								            						                                        HDLmWhiteSpace.WHITESPACENOTOK,
								            																						        HDLmReportErrors.DONTREPORTERRORS,
								            																						        HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to checkFieldStringArray is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotPrimitive = 4;
			int     errorNumberNotArray = 4;
			int     errorNumberException = 4;
			int     errorNumberTooSmall = 70;
			int     errorNumberTooLarge = 71; 
			int     errorNumberInvalidLength = 4;
			int     errorNumberInvalidWhiteSpace = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldStringArray(editorType, 
							                              		                                errorCounter, 
							                              		                                errorMessages, 
							                              		                                modsElementObject, 
							                              		                                null, 
							                              		                                fieldName, 
								             						                                        null,
								            						                                        null,
								            						                                        errorMessagePrefix,
								            						                                        errorNumberMissing,
								            						                                        errorNumberIsNull,
								            						                                        errorNumberIsPrimitive,
								            						                                        errorNumberNotPrimitive,
								            						                                        errorNumberNotArray,
								            						                                        errorNumberException,
								            						                                        errorNumberTooSmall,
								            						                                        errorNumberTooLarge, 
								            						                                        errorNumberInvalidLength,
								            						                                        errorNumberInvalidWhiteSpace,
								            						                                        HDLmWhiteSpace.WHITESPACENOTOK,
								            																						        HDLmReportErrors.DONTREPORTERRORS,
								            																						        HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Set of keys passed to checkFieldStringArray is null", execMsg,
					         "Unexpected exception message");
		}  
	  {
			String  errorMessagePrefix = "Modification";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotPrimitive = 4;
			int     errorNumberNotArray = 4;
			int     errorNumberException = 4;
			int     errorNumberTooSmall = 70;
			int     errorNumberTooLarge = 71; 
			int     errorNumberInvalidLength = 4;
			int     errorNumberInvalidWhiteSpace = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldStringArray(editorType, 
							                              		                                errorCounter, 
							                              		                                errorMessages, 
							                              		                                modsElementObject, 
							                              		                                modsElementKeys, 
							                              		                                null, 
								             						                                        null,
								            						                                        null,
								            						                                        errorMessagePrefix,
								            						                                        errorNumberMissing,
								            						                                        errorNumberIsNull,
								            						                                        errorNumberIsPrimitive,
								            						                                        errorNumberNotPrimitive,
								            						                                        errorNumberNotArray,
								            						                                        errorNumberException,
								            						                                        errorNumberTooSmall,
								            						                                        errorNumberTooLarge, 
								            						                                        errorNumberInvalidLength,
								            						                                        errorNumberInvalidWhiteSpace,
								            						                                        HDLmWhiteSpace.WHITESPACENOTOK,
								            																						        HDLmReportErrors.DONTREPORTERRORS,
								            																						        HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to checkFieldStringArray is null", execMsg,
					         "Unexpected exception message");
		} 
	}
  @Test
	void modFindCheck() { 
		/* Run a few modFindCheck tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the modification finds */
    String       fieldName = "find";
    JsonElement  modsElementFinds = modsElementObject.get(fieldName);
    JsonArray    modsObjectArray = modsElementFinds.getAsJsonArray();
    /* Try to access the JSON element for one modification find */
    JsonElement  modsElementFind = modsObjectArray.get(0);
    JsonObject   modsObjectFind = modsElementFind.getAsJsonObject();
    Set<String>  modsFindKeys = modsObjectFind.keySet();
    MutableInt   errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    int        intValue;
    HDLmFind   modFind;
    modFind = HDLmMod.modFieldFind(editorType, 
    		                           errorCounter, 
    		                           errorMessages, 
    		                           modsObjectFind, 
    		                           modsFindKeys);
    assertNotNull(modFind, "Modification find value is null");
    /* Check the find we just found */    
    HDLmMod.modFindCheck(editorType, 
    		                 errorCounter, 
    		                 errorMessages, 
    		                 modsObjectFind, 
    		                 modFind);
    intValue = errorCounter.intValue();
    assertEquals(0, intValue, "Error value must be zero");
    /* Modify and check the find we just found */  
    modFind.setValue(null);
    HDLmMod.modFindCheck(editorType, 
    		                 errorCounter, 
    		                 errorMessages, 
    		                 modsObjectFind, 
    		                 modFind, 
    		                 HDLmReportErrors.DONTREPORTERRORS);
    intValue = errorCounter.intValue();
    assertEquals(0, intValue, "Error value must be zero");   
	  {
	  	HDLmFind   modFindLocal = modFind;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFindCheck(editorType, 
					                              		                         null, 
					                              		                         errorMessages,
					                              		                         modsObjectFind, 
					                              		                         modFindLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to modFindCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmFind   modFindLocal = modFind;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFindCheck(editorType, 
					                              		                         errorCounter, 
					                              		                         null,
					                              		                         modsObjectFind, 
					                              		                         modFindLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to modFindCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	HDLmFind   modFindLocal = modFind;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFindCheck(editorType, 
					                              		                         errorCounter, 
					                              		                         errorMessages, 
					                              		                         null, 
					                              		                         modFindLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to modFindCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modFindCheck(editorType, 
					                              		                         errorCounter, 
					                              		                         errorMessages, 
					                              		                         modsObjectFind, 
					                              		                         null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Find object passed to modFindCheck is null", execMsg,
					         "Unexpected exception message");
		}  
	}
  @Test
	void modLocateCheck() { 
		/* Run a few modLocateCheck tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Get a few values from the modifications */
    ArrayList<HDLmFind>  findInfo = modDetails.getFinds();
    String        cssInfo = modDetails.getCssSelector();
    String        xPathInfo = modDetails.getXPath();   
    HDLmNodeIden  nodeIden = modDetails.getNodeIden();
    HDLmModTypes  modType = modDetails.getType();
    MutableInt    errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    HDLmMod.modLocateCheck(editorType, 
    		                   modType,
    		                   errorCounter, 
    		                   errorMessages,
    		                   modsElementObject, 
    		                   cssInfo, 
    		                   xPathInfo, 
    		                   findInfo,
    		                   nodeIden);
    int  intValue;
    intValue = errorCounter.intValue();
    assertEquals(0, intValue, "Error value must be zero");
    /* Make the locate information invalid */
    cssInfo = "Dummy CSS value";
    HDLmMod.modLocateCheck(editorType, 
    		                   modType,
    		                   errorCounter, 
    		                   errorMessages,
    		                   modsElementObject, 
    		                   cssInfo, 
    		                   xPathInfo, 
    		                   findInfo,
    		                   nodeIden,
    		                   HDLmReportErrors.DONTREPORTERRORS);
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
    /* Make the locate information invalid */
    cssInfo = null; 
    findInfo = null;
    nodeIden = null;
    HDLmMod.modLocateCheck(editorType, 
    		                   modType,    		
    		                   errorCounter, 
    		                   errorMessages,
    		                   modsElementObject, 
    		                   cssInfo, 
    		                   xPathInfo, 
    		                   findInfo,
    		                   nodeIden,
    		                   HDLmReportErrors.DONTREPORTERRORS);
    intValue = errorCounter.intValue();
    assertEquals(2, intValue, "Error value must be two");
	  {
	  	String   cssInfoLocal = cssInfo;
	  	ArrayList<HDLmFind>  findInfoLocal = findInfo;
	  	HDLmNodeIden         nodeIdenLocal = nodeIden;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modLocateCheck(editorType, 
					                              		                           modType,
					                              		                           null, 
					                              		                           errorMessages,
					                              		                           modsElementObject, 
					                              		                           cssInfoLocal, 
					                              		                           xPathInfo, 
					                              		                           findInfoLocal,
					                              		                           nodeIdenLocal);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to modLocateCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	String   cssInfoLocal = cssInfo;
	  	ArrayList<HDLmFind>  findInfoLocal = findInfo;
	  	HDLmNodeIden         nodeIdenLocal = nodeIden;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modLocateCheck(editorType, 
					                              		                           modType,
					                              		                           errorCounter, 
					                              		                           null,
					                              		                           modsElementObject, 
					                              		                           cssInfoLocal, 
					                              		                           xPathInfo, 
					                              		                           findInfoLocal,
					                              		                           nodeIdenLocal);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to modLocateCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	String   cssInfoLocal = cssInfo;
	  	ArrayList<HDLmFind>  findInfoLocal = findInfo;
	  	HDLmNodeIden         nodeIdenLocal = nodeIden;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modLocateCheck(editorType, 
					                              		                           modType,
					                              		                           errorCounter, 
					                              		                           errorMessages,
					                              		                           null, 
					                                                        		 cssInfoLocal, 
					                                                        		 xPathInfo, 
					                                                        		 findInfoLocal,
					                                                        		 nodeIdenLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to modLocateCheck is null", execMsg,
					         "Unexpected exception message");
		}
	}
  @Test
	void modPercentPixel() { 
		/* Run a few modPercentPixel tests */
  	HDLmEditorTypes    editorType = HDLmEditorTypes.MOD;
  	ArrayList<String>  pixelOutput;
  	/* Run a modPercentPixel test */
  	pixelOutput = HDLmMod.modPercentPixel("auto");
  	assertEquals(2, pixelOutput.size(), "Pixel output array has the wrong size");
  	assertEquals("auto", pixelOutput.get(0), "Pixel output array first entry is incorrect");
  	assertEquals("", pixelOutput.get(1), "Pixel output array second entry is incorrect");
  	/* Run a modPercentPixel test */
  	pixelOutput = HDLmMod.modPercentPixel("234");
  	assertEquals(2, pixelOutput.size(), "Pixel output array has the wrong size");
  	assertEquals("234", pixelOutput.get(0), "Pixel output array first entry is incorrect");
  	assertEquals("px", pixelOutput.get(1), "Pixel output array second entry is incorrect");
  	/* Run a modPercentPixel test */
  	pixelOutput = HDLmMod.modPercentPixel("234%");
  	assertEquals(2, pixelOutput.size(), "Pixel output array has the wrong size");
  	assertEquals("234", pixelOutput.get(0), "Pixel output array first entry is incorrect");
  	assertEquals("%", pixelOutput.get(1), "Pixel output array second entry is incorrect");
   	/* Run a modPercentPixel test */
  	pixelOutput = HDLmMod.modPercentPixel("234px");
  	assertEquals(2, pixelOutput.size(), "Pixel output array has the wrong size");
  	assertEquals("234", pixelOutput.get(0), "Pixel output array first entry is incorrect");
  	assertEquals("px", pixelOutput.get(1), "Pixel output array second entry is incorrect");
  	/* Run a modPercentPixel test */
  	pixelOutput = HDLmMod.modPercentPixel("234xx");
  	assertEquals(0, pixelOutput.size(), "Pixel output array has the wrong size");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modPercentPixel(null);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Size string passed to modPercentPixel is null", execMsg,
					         "Unexpected exception message");
		}
	}  
  @Test
	void modSuffixCheck() { 
		/* Run a few modSuffixCheck tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
		/* Build a list of acceptable suffix values */
		ArrayList<String>  fontSuffixValues = new ArrayList<String>(
		  List.of("px", "em", "%"));
    JsonParser   parser = new JsonParser();  
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(4);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the font size array */
    String              fieldName = "sizes";
    JsonArray           modsElementArray = modsElementObject.getAsJsonArray(fieldName);
    JsonElement         modsElementString = modsElementArray.get(0);
    JsonPrimitive       modsPrimitiveFontSizeEntry = modsElementString.getAsJsonPrimitive();
    String              fontValue = modsPrimitiveFontSizeEntry.getAsString();
  	ArrayList<String>   fontValueArray = HDLmMod.modPercentPixel(fontValue);
  	String              fontSuffixValue = fontValueArray.get(1);
    MutableInt          errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    int                 intValue;
    /* Check if we have the correct font value */
    assertNotNull(fontValue, "Font value is null");
    assertEquals("100px", fontValue, "Font value is invalid");
    /* Check if we have the correct suffix value */
    assertNotNull(fontSuffixValue, "Font suffix value is null");
    assertEquals("px", fontSuffixValue, "Font suffix value is invalid");
    /* Run a modSuffixCheck test */
    HDLmMod.modSuffixCheck(editorType, 
    		                   errorCounter, 
    		                   errorMessages, 
    		                   modsElementObject, 
    		                   fieldName, 
    	                     fontSuffixValues, 
    	                     fontSuffixValue); 
    intValue = errorCounter.intValue();
    assertEquals(0, intValue, "Error value must be zero");
    /* Run a modSuffixCheck test */ 
    fontSuffixValue = "xx";
    HDLmMod.modSuffixCheck(editorType, 
    		                   errorCounter, 
    		                   errorMessages, 
    		                   modsElementObject, 
    		                   fieldName, 
    	                   	 fontSuffixValues, fontSuffixValue, 
                           HDLmReportErrors.DONTREPORTERRORS); 
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
    /* Run a modSuffixCheck test */
    fontSuffixValue = "px";
    HDLmMod.modSuffixCheck(editorType, 
    		                   errorCounter, 
    		                   errorMessages, 
    		                   modsElementObject, 
    		                   fieldName, 
    	                   	 fontSuffixValues, 
    	                   	 fontSuffixValue);
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
	  { 
	  	String   fontSuffixValueLocal = fontSuffixValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modSuffixCheck(editorType, 
					                              		                           null, 
					                              		                           errorMessages,
					                              		                           modsElementObject, 
					                              		                           fieldName,
					                                                             fontSuffixValues, 
					                                                             fontSuffixValueLocal);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to modSuffixCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
	  	String   fontSuffixValueLocal = fontSuffixValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modSuffixCheck(editorType, 
					                              		                           errorCounter, 
					                              		                           null,
					                              		                           modsElementObject, 
					                              		                           fieldName,
					                                                             fontSuffixValues, 
					                                                             fontSuffixValueLocal);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to modSuffixCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
	  	String   fontSuffixValueLocal = fontSuffixValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modSuffixCheck(editorType, 
					                              		                           errorCounter, 
					                              		                           errorMessages, 
					                              		                           null, 
					                              		                           fieldName,
					                                                        		 fontSuffixValues, 
					                                                        		 fontSuffixValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to modSuffixCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	String   fontSuffixValueLocal = fontSuffixValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modSuffixCheck(editorType, 
					                              		                           errorCounter, 
					                              		                           errorMessages, 
					                              		                           modsElementObject, 
					                              		                           null, 
					                              	                           	 fontSuffixValues, 
					                              	                           	 fontSuffixValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to modSuffixCheck is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	String   fontSuffixValueLocal = fontSuffixValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.modSuffixCheck(editorType, 
					                              		                           errorCounter, 
					                              		                           errorMessages, 
					                              		                           modsElementObject, 
					                              		                           fieldName,
					                              	                           	 null, 
					                              	                           	 fontSuffixValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Array of valid suffix values passed to modSuffixCheck is null", execMsg,
					         "Unexpected exception message");
		}
	}   
  @Test
	void rangeField() { 
		/* Run a few rangeField tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Try to access the JSON element for the parameter number  */
    String         fieldName = "parameter";
    JsonElement    modsElementParameterNumber = modsElementObject.get(fieldName);
    JsonPrimitive  modsPrimitiveParameterNumber = modsElementParameterNumber.getAsJsonPrimitive();
    MutableInt     errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    Integer        modInteger;
    modInteger = HDLmMod.modFieldInteger(editorType, 
    		                                 errorCounter, 
    		                                 errorMessages, 
    		                                 modsElementObject, 
    		                                 modsElementKeys, 
    		                                 fieldName);
    assertNotNull(modInteger, "Modification parameter number value is null");
    assertEquals(9, modInteger, "Modification parameter number value is not nine");
    int      intValue;
    Integer  minValue = 0;
    Integer  maxValue = 9;
    /* Run a rangeField test */
    HDLmMod.rangeField(editorType, 
    		               errorCounter, 
    		               errorMessages, 
    		               modsElementObject, 
    		               fieldName, 
    		               modInteger, 
    		               minValue, 
    		               maxValue); 
    intValue = errorCounter.intValue();
    assertEquals(0, intValue, "Error value must be zero");
    /* Run a rangeField test */
    maxValue = 8;
    HDLmMod.rangeField(editorType, 
    		               errorCounter, 
    		               errorMessages, 
    		               modsElementObject, 
    		               fieldName, 
                       modInteger, 
                       minValue, 
                       maxValue, 
                       HDLmReportErrors.DONTREPORTERRORS); 
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
    /* Run a rangeField test */
    maxValue = 18;
    HDLmMod.rangeField(editorType, 
    		               errorCounter, 
    		               errorMessages, 
    		               modsElementObject, 
    		               fieldName, 
                       modInteger, 
                       minValue, 
                       maxValue); 
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
	  {
	  	Integer maxValueLocal = maxValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.rangeField(editorType, 
					                              		                       null, 
					                              		                       errorMessages,
					                              		                       modsElementObject, 
					                              		                       fieldName,
					                              	               	         modInteger, 
					                              	               	         minValue, 
					                              	               	         maxValueLocal);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to rangeField is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	Integer maxValueLocal = maxValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.rangeField(editorType, 
					                              		                       errorCounter, 
					                              		                       null,
					                              		                       modsElementObject, 
					                              		                       fieldName,
					                              	               	         modInteger, 
					                              	               	         minValue, 
					                              	               	         maxValueLocal);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to rangeField is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	Integer maxValueLocal = maxValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.rangeField(editorType, 
					                              		                       errorCounter, 
					                              		                       errorMessages, 
					                              		                       null, 
					                              		                       fieldName,
					                                  		                   modInteger, 
					                                  		                   minValue, 
					                                  		                   maxValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to rangeField is null", execMsg,
					         "Unexpected exception message");
		}
	  {
	  	Integer maxValueLocal = maxValue;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.rangeField(editorType, 
					                              		                       errorCounter, 
					                              		                       errorMessages, 
					                              		                       modsElementObject, 
					                              		                       null, 
					                              		                       modInteger, 
					                              		                       minValue, 
					                              		                       maxValueLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to rangeField is null", execMsg,
					         "Unexpected exception message");
		}
	}
  @Test
	void reportError() { 
		/* Run a few reportError tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Run a reportError test */
    int         intValue;
    MutableInt  errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    String      expectedOutput = " - name (test) JSON ({\"prob\":100.0,\"usemode\":\"prod\",\"created\":\"2022-11-)";
    String      inErrorText;
    String      outErrorText;
    inErrorText = "";
    outErrorText = HDLmField.reportError(editorType, 
    		                                 errorCounter, 
    		                                 errorMessages, 
    		                                 modsElementObject, 
    		                                 "test",
    		                                 inErrorText, 
    		                                 7, 
    		                                 HDLmReportErrors.DONTREPORTERRORS); 
    assertEquals(expectedOutput, outErrorText, "Error string value must be correct");
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one"); 
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportError(editorType, 
					                              		                          null, 
					                              		                          errorMessages,
					                              		                          modsElementObject,  
					                              		                          "test",
					                              		                          inErrorText, 
					                              		                          7);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to reportError is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportError(editorType, 
					                              		                          errorCounter, 
					                              		                          null,
					                              		                          modsElementObject,  
					                              		                          "test",
					                              		                          inErrorText, 
					                              		                          7);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to reportError is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportError(editorType, 
					                              		                          errorCounter, 
					                              		                          errorMessages, 
					                              		                          (JsonObject) null,  
					                              		                          "test",
					                              	                          	inErrorText, 
					                              	                          	7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to reportError is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportError(editorType, 
					                              		                          errorCounter, 
					                              		                          errorMessages, 
					                              		                          modsElementObject,
					                              		                          "test",
					                              		                          null, 
					                              		                          7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Error text string passed to reportError is null", execMsg,
					         "Unexpected exception message");
		}
	}
  @Test
	void reportErrorNoValue() { 
		/* Run a few reportErrorNoValue tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Run a reportErrorNoValue test */
    int         intValue;
    MutableInt  errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    String      expectedOutput = " - name (path) JSON ({\"prob\":100.0,\"usemode\":\"prod\",\"created\":\"2022-11-)";
    String      inErrorText;
    String      outErrorText;
    String      fieldName = "path";
    inErrorText = "";
    outErrorText = HDLmField.reportErrorNoValue(editorType, 
    		                                        errorCounter, 
    		                                        errorMessages, 
    		                                        modsElementObject, 
    		                                        fieldName,
    		                                        inErrorText, 
    		                                        7, 
    		                                        HDLmReportErrors.DONTREPORTERRORS); 
    assertEquals(expectedOutput, outErrorText, "Error string value must be correct");
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorNoValue(editorType, 
					                              		                                 null, 
					                              		                                 errorMessages,
					                              		                                 modsElementObject, 
					                              		                                 fieldName, 
					                              		                                 inErrorText, 
					                              		                                 7);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to reportErrorNoValue is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorNoValue(editorType, 
					                              		                                 errorCounter, 
					                              		                                 null,
					                              		                                 modsElementObject, 
					                              		                                 fieldName, 
					                              		                                 inErrorText, 
					                              		                                 7);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to reportErrorNoValue is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorNoValue(editorType, 
					                              		                                 errorCounter, 
					                              		                                 errorMessages, 
					                              		                                 (JsonObject) null, 
					                              		                                 fieldName,
					                              	                        	         inErrorText, 
					                              	                        	         7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to reportErrorNoValue is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorNoValue(editorType, 
					                              		                                 errorCounter, 
					                              		                                 errorMessages, 
					                              		                                 modsElementObject, 
					                              		                                 null,
					                              	                        	         inErrorText, 
					                              	                        	         7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Field name string passed to reportErrorNoValue is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorNoValue(editorType, 
					                              		                                 errorCounter, 
					                              		                                 errorMessages, 
					                              		                                 modsElementObject, 
					                              		                                 fieldName,
					                              		                                 null, 
					                              		                                 7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Error text string passed to reportErrorNoValue is null", execMsg,
					         "Unexpected exception message");
		}
	}
  @Test
	void reportErrorValue() { 
		/* Run a few reportErrorValue tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Run a reportErrorValue test */
    int          intValue;
    MutableInt   errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    String       expectedOutput = " - name (path) value (test) JSON ({\"prob\":100.0,\"usemode\":\"prod\",\"created\":\"2022-11-18T02:04:30.296Z\",\"lastModified\":\"2022-11-18T02:04:30.296Z\",\"name\":\"Add to Cart Text\",\"path\":\"/neve-studio-da)";
    String       inErrorText;
    String       outErrorText;
    String       fieldName = "path";
    String       value = "test";
    expectedOutput = " - name (path) value (test) JSON ({\"prob\":100.0,\"usemode\":\"prod\",\"created\":\"2022-11-)";
    inErrorText = "";
    outErrorText = HDLmField.reportErrorValue(editorType, 
    		                                      errorCounter, 
    		                                      errorMessages, 
    		                                      modsElementObject, 
    		                                      fieldName, 
    		                                      value,
    		                                      inErrorText, 
    		                                      7, 
    		                                      HDLmReportErrors.DONTREPORTERRORS); 
    assertEquals(expectedOutput, outErrorText, "Error string value must be correct");
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorValue(editorType, 
					                              		                               null, 
					                              		                               errorMessages,
					                              		                               modsElementObject, 
					                              		                               fieldName, 
					                              		                               value, 
					                              		                               inErrorText, 
					                              		                               7);},		 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to reportErrorValue is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorValue(editorType, 
					                              		                               errorCounter, 
					                              		                               null,
					                              		                               modsElementObject, 
					                              		                               fieldName, 
					                              		                               value, 
					                              		                               inErrorText, 
					                              		                             7);},		 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to reportErrorValue is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorValue(editorType, 
					                              		                               errorCounter, 
					                              		                               errorMessages, 
					                              		                               (JsonObject) null, 
					                              		                               fieldName,
					                              	                          	     value, 
					                              	                        	       inErrorText, 
					                              	                        	       7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to reportErrorValue is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorValue(editorType, 
					                              		                               errorCounter, 
					                              		                               errorMessages, 
					                              		                               modsElementObject, 
					                              		                               null,
					                              	                          	     value, 
					                              	                        	       inErrorText, 
					                              	                        	       7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Field name string passed to reportErrorValue is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorValue(editorType, 
					                              		                               errorCounter, 
					                              		                               errorMessages, 
					                              		                               modsElementObject, 
					                              		                               fieldName,
					                              		                               null, 
					                              		                               inErrorText, 
					                              		                               7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Value string passed to reportErrorValue is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportErrorValue(editorType, 
					                              		                               errorCounter, 
					                              		                               errorMessages, 
					                              		                               modsElementObject, 
					                              		                               fieldName,
					                              		                               value, 
					                              		                               null, 
					                              		                               7);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Error text string passed to reportErrorValue is null", execMsg,
					         "Unexpected exception message");
		}
	}
  @Test
	void reportField() { 
		/* Run a few reportField tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.MOD;
		String           modName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject       topNodeObject = topNode.getAsJsonObject();
    Set<String>      topKeys = topNodeObject.keySet();
    JsonObject       topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Run a reportField test */
    int          intValue;
    MutableInt   errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    String       expectedOutput = "Modification JSON invalid field - name (path) value (test) JSON ({\"prob\":100.0,\"usemode\":\"prod\",\"created\":\"2022-11-)";
    String       outErrorText;
    String       fieldName = "path";
    String       value = "test"; 
    outErrorText = HDLmField.reportField(editorType, 
    		                                 errorCounter, 
    		                                 errorMessages, 
    		                                 modsElementObject, 
    		                                 fieldName,  
    		                                 value,
    		 	                               "Modification JSON invalid field", 
    			                               4,
    		                                 HDLmReportErrors.DONTREPORTERRORS); 
    assertEquals(expectedOutput, outErrorText, "Error string value must be correct");
    intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
    /* Run a reportField test */    
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportField(editorType, 
					                              		                          null, 
					                              		                          errorMessages,
					                              		                          modsElementObject, 
					                              		                          fieldName, 
					                              		                          value,
					                            	                              "Modification JSON invalid field", 
					                            	                              4);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to reportField is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportField(editorType, 
					                              		                          errorCounter, 
					                              		                          null,
					                              		                          modsElementObject, 
					                              		                          fieldName, 
					                              		                          value,
					                            	                              "Modification JSON invalid field", 
					                            	                              4);},					                              		                              
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to reportField is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportField(editorType, 
					                              		                          errorCounter, 
					                              		                          errorMessages, 
					                              		                          (JsonObject) null, 
					                              		                          fieldName,
					                              	                          	value,
					                            	                              "Modification JSON invalid field", 
					                            	                              4);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to reportField is null", execMsg,
					         "Unexpected exception message");
		}
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportField(editorType, 
					                              		                          errorCounter, 
					                              		                          errorMessages, 
					                              		                          modsElementObject, 
					                              		                          null,
					                              	                          	value,
					                            	                              "Modification JSON invalid field", 
					                            	                              4);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Field name string passed to reportField is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.reportField(editorType, 
					                              		                          errorCounter, 
					                              		                          errorMessages, 
					                              		                          modsElementObject, 
					                              		                          fieldName,
					                              		                          null,
					                            	                              "Modification JSON invalid field", 
					                            	                              4);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Value string passed to reportField is null", execMsg,
					         "Unexpected exception message");
		}
	}
  @Test
	void truncateJson() { 
		/* Run a few truncateJson tests */
		String       modName = "OWO Home Bottom Text";
    JsonParser   parser = new JsonParser();  
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetModStr); 
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of 
       data rows should always be exactly one. */    
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only 
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString); 
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);   	
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray(); 
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children"); 
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement  modsElementDetails = modsObject.get("details"); 
    JsonObject   modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys;
    modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod  modDetails = new HDLmMod(modsElementDetails);
    modDetails.setIfNotSetTimes();
    /* Run a truncateJson test */
    String       expectedOutput = "{\"prob\":100.0,\"usemode\":\"prod\",\"created\":\"2022-11-";
    String       outErrorText;
    outErrorText = HDLmMod.truncateJson(modsElementObject); 
    assertEquals(expectedOutput, outErrorText, "Error string value must be correct");
	  { 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.truncateJson((JsonObject) null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to truncateJson is null", execMsg,
					         "Unexpected exception message");
		}	 
  }
  @Test
	void saveChanges() { 
		/* Run a few saveChanges tests */
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
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  /* Process one of the extracted modifications */
	  HDLmMod  currentMod = matchingMods.get(0);
  	/* All of the modifications should be enabled. This is actually 
  	   an indirect way of making sure that no errors were found 
  	   building each modification. */
  	boolean  modEnabled = currentMod.getEnabled();
  	assertTrue(modEnabled, "Current modification is not enabled");
  	boolean  matchFound;
  	matchFound = currentMod.apply(htmlDoc, pathValueStr, parametersArray, savedChangesArray);	  	
  	assertEquals(true, matchFound, "Current modification did not match");	  
	  /* Check the saved changes */
	  HDLmSavedChange  saved;
	  String           newValue;
	  String           oldValue;
	  saved = savedChangesArray.get(0); 
	  assertEquals("Add to Cart Text", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.TITLE, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  assertEquals("TCELESECROFAdd to ala-carte", saved.getNewValue(), "Saved modification new value did not match");
	  assertEquals("Add to Cart", saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(9, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");
	  /* Save a few values */
	  String         savedModName = saved.getModName();
	  HDLmModTypes   savedModType = saved.getModType();
	  String         savedModPathValue = saved.getModPathValue();
	  String         savedNewValue = saved.getNewValue();
	  String         savedOldValue = saved.getOldValue();
	  Integer        savedParameterNumber = saved.getParameterNumber();
	  Double         savedParameterValue = saved.getParameterValue();
	  /* Clear the save changes array and add a new set of values to it */
	  savedChangesArray.clear();
	  HDLmMod.saveChanges(savedChangesArray, savedParameterNumber, 
                        savedParameterValue,
                        savedModName, savedModPathValue, savedModType,
                        savedOldValue, savedNewValue);
	  /* Check the saved changes */
	  saved = savedChangesArray.get(0); 
	  assertEquals("Add to Cart Text", saved.getModName(), "Saved modification name did not match");
	  assertEquals(HDLmModTypes.TITLE, saved.getModType(), "Saved modification type did not match");
	  assertEquals("/neve-studio-dance-jacket.html", saved.getModPathValue(), "Saved modification path value did not match");
	  assertEquals("TCELESECROFAdd to ala-carte", saved.getNewValue(), "Saved modification new value did not match");
	  assertEquals("Add to Cart", saved.getOldValue(), "Saved modification old value did not match");
	  assertEquals(9, saved.getParameterNumber(), "Saved modification parameter number did not match");
	  assertEquals(0.5, saved.getParameterValue(), "Saved modification parameter value did not match");	  
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmMod.saveChanges(null, 9, 0.5, "Add to Cart Text",
					                              		                        "/neve-studio-dance-jacket.html",
					                              		                        HDLmModTypes.TITLE,
					                              		                        "Add to Cart", "Add to ala-carte");},
					                               "Expected RuntimeException");	
			String execMsg = exception.getMessage();
			assertEquals("Saved changes array passed to saveChanges is null", execMsg,
					         "Unexpected exception message");
		}	
	}	
}
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
 * HDLmFieldTest short summary.
 *
 * HDLmFieldTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmFieldTest {
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
	void checkFieldJsonObject() { 
		/* Run a few checkFieldJsonObject tests */
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
    /* Get the tree object */
    JsonObject   treeElementObject = modsObject;
    Set<String>  treeElementKeys;
    treeElementKeys = treeElementObject.keySet();
    /* Try to access the JSON element for the details */
    String         fieldName = "details"; 
    MutableInt     errorCounter = new MutableInt(0);
    ArrayList<String>   errorMessages = new ArrayList<String>();
    JsonObject          checkJsonObject;
    {
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4;
	    checkJsonObject = HDLmField.checkFieldJsonObject(editorType, 
			    		                                         errorCounter, 
			    		                                         errorMessages, 
			    		                                         treeElementObject, 
			    		                                         treeElementKeys, 
			    		                                         fieldName, 
							                                         errorMessagePrefix,
							                                         errorNumberMissing,
							                                         errorNumberIsNull,
							                                         errorNumberIsPrimitive,
							                                         errorNumberNotObject,
							                                         errorNumberException,
																							         HDLmReportErrors.DONTREPORTERRORS);
    }
    assertNotNull(checkJsonObject, "Modification object value is null");
    {
	  	String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4;
	    checkJsonObject = HDLmField.checkFieldJsonObject(editorType, 
			    		                                         errorCounter, 
			    		                                         errorMessages, 
			    		                                         treeElementObject, 
			    		                                         treeElementKeys, 
			    		                                         fieldName + "x", 
							                                         errorMessagePrefix,
							                                         errorNumberMissing,
							                                         errorNumberIsNull,
							                                         errorNumberIsPrimitive,
							                                         errorNumberNotObject,
							                                         errorNumberException,
																							         HDLmReportErrors.DONTREPORTERRORS); 
    }
    assertNull(checkJsonObject, "Modification object value is null");
    int  intValue = errorCounter.intValue();
    assertEquals(1, intValue, "Error value must be one");
	  {
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4;  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldJsonObject(editorType, 
							                              		                               null, 
							                              		                               errorMessages,
							                              		                               treeElementObject, 
							                              		                               treeElementKeys, 
							                              		                               fieldName, 
							            						                                         errorMessagePrefix,
							            						                                         errorNumberMissing,
							            						                                         errorNumberIsNull,
							            						                                         errorNumberIsPrimitive,
							            						                                         errorNumberNotObject,
							            						                                         errorNumberException,
							            																						         HDLmReportErrors.DONTREPORTERRORS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Mutable int for error counter passed to checkFieldJsonObject is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldJsonObject(editorType, 
							                              		                               errorCounter,
							                              		                               null,
							                              		                               treeElementObject, 
							                              		                               treeElementKeys, 
							                              		                               fieldName,  
								            						                                       errorMessagePrefix,
								            						                                       errorNumberMissing,
								            						                                       errorNumberIsNull,
								            						                                       errorNumberIsPrimitive,
								            						                                       errorNumberNotObject,
								            						                                       errorNumberException,
								            																						       HDLmReportErrors.DONTREPORTERRORS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("ArrayList for error messages passed to checkFieldJsonObject is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldJsonObject(editorType, 
							                              		                               errorCounter, 
							                              		                               errorMessages, 
							                              		                               null, 
							                              		                               treeElementKeys, 
							                              		                               fieldName, 
								            						                                       errorMessagePrefix,
								            						                                       errorNumberMissing,
								            						                                       errorNumberIsNull,
								            						                                       errorNumberIsPrimitive,
								            						                                       errorNumberNotObject,
								            						                                       errorNumberException,
								            																						       HDLmReportErrors.DONTREPORTERRORS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object passed to checkFieldJsonObject is null", execMsg,
					         "Unexpected exception message");
		}
	  {
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldJsonObject(editorType, 
							                              		                               errorCounter, 
							                              		                               errorMessages, 
							                              		                               treeElementObject, 
							                              		                               null, 
							                              		                               fieldName, 
								            						                                       errorMessagePrefix,
								            						                                       errorNumberMissing,
								            						                                       errorNumberIsNull,
								            						                                       errorNumberIsPrimitive,
								            						                                       errorNumberNotObject,
								            						                                       errorNumberException, 
								            																						       HDLmReportErrors.DONTREPORTERRORS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Set of keys passed to checkFieldJsonObject is null", execMsg,
					         "Unexpected exception message");
		}  
	  {
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldJsonObject(editorType, 
							                              		                               errorCounter, 
							                              		                               errorMessages, 
							                              		                               treeElementObject, 
							                              		                               treeElementKeys, 
							                              		                               null,  
								            						                                       errorMessagePrefix,
								            						                                       errorNumberMissing,
								            						                                       errorNumberIsNull,
								            						                                       errorNumberIsPrimitive,
								            						                                       errorNumberNotObject,
								            						                                       errorNumberException,  
								            																						       HDLmReportErrors.DONTREPORTERRORS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name string passed to checkFieldJsonObject is null", execMsg,
					         "Unexpected exception message");
		} 
	  {
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4; 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldJsonObject(editorType, 
							                              		                               errorCounter, 
							                              		                               errorMessages, 
							                              		                               treeElementObject, 
							                              		                               treeElementKeys, 
							                              		                               fieldName,
								             						                                       null,
								            						                                       errorNumberMissing,
								            						                                       errorNumberIsNull,
								            						                                       errorNumberIsPrimitive,
								            						                                       errorNumberNotObject,
								            						                                       errorNumberException,
								            																						       HDLmReportErrors.DONTREPORTERRORS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Error message prefix passed to checkFieldJsonObject is null", execMsg,
					         "Unexpected exception message");
		}  
	  {
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotObject = 4;
			int     errorNumberException = 4; 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmField.checkFieldJsonObject(editorType, 
							                              		                               errorCounter, 
							                              		                               errorMessages, 
							                              		                               treeElementObject, 
							                              		                               treeElementKeys, 
							                              		                               fieldName,
								            						                                       errorMessagePrefix,
								            						                                       errorNumberMissing,
								            						                                       errorNumberIsNull,
								            						                                       errorNumberIsPrimitive,
								            						                                       errorNumberNotObject,
								            						                                       errorNumberException,  
								            																						       null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Report errors enum passed to checkFieldJsonObject is null", execMsg,
					         "Unexpected exception message");
		} 
	}
  @Test
	void checkFieldString() { 
		/* Run a few checkFieldString tests */
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
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3; 
	    int     errorNumberIsNull = 4;
	    int     errorNumberNotPrimitive = 4;
	    int     errorNumberException = 4;
	    int     errorNumberInvalidLength = 4;
	    int     errorNumberInvalidWhiteSpace = 4;	
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
			String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
	  {
			String  errorMessagePrefix = "Field";
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
							                              		                           fieldName, 
							                              		                           null,
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
			assertEquals("Error message prefix passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		} 
	  {
			String  errorMessagePrefix = "Field";
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
							                              		                           fieldName, 
							                              		                           errorMessagePrefix,
							 																						     	     	     errorNumberMissing,
							 																						                 errorNumberIsNull,
							 																						                 errorNumberNotPrimitive,
							 																						                 errorNumberException,
							 																							               errorNumberInvalidLength,
							 																							               errorNumberInvalidWhiteSpace, 
							                              		                           null, 
							                              		                           HDLmReportErrors.DONTREPORTERRORS,
							                              		                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("White space OK enum passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		} 
	  { 
			String  errorMessagePrefix = "Field";
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
							                              		                           fieldName, 
							                              		                           errorMessagePrefix,
							 																						     	     	     errorNumberMissing,
							 																						                 errorNumberIsNull,
							 																						                 errorNumberNotPrimitive,
							 																						                 errorNumberException,
							 																							               errorNumberInvalidLength,
							 																							               errorNumberInvalidWhiteSpace, 
							                              		                           HDLmWhiteSpace.WHITESPACENOTOK, 
							                              		                           null,
							                              		                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Report errors enum passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		} 
	  {
			String  errorMessagePrefix = "Field";
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
							                              		                           null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Zero-Length OK enum passed to checkFieldString is null", execMsg,
					         "Unexpected exception message");
		} 
	}
  @Test
	void checkFieldStringArray() { 
		/* Run a few checkFieldStringArray tests */
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
			String  errorMessagePrefix = "Field";
			int     errorNumberMissing = 3;
			int     errorNumberIsNull = 4;
			int     errorNumberIsPrimitive = 4;
			int     errorNumberNotPrimitive = 4;
			int     errorNumberNotObject = 4;
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
							                                         errorNumberNotObject,
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
	  	String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
			String  errorMessagePrefix = "Field";
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
	  {
			String  errorMessagePrefix = "Field";
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
							                              		                                fieldName,
								             						                                        null,
								            						                                        null,
								            						                                        null,
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
			assertEquals("Error message prefix passed to checkFieldStringArray is null", execMsg,
					         "Unexpected exception message");
		} 
	  {
			String  errorMessagePrefix = "Field";
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
								            						                                        null,
								            																						        HDLmReportErrors.DONTREPORTERRORS,
								            																						        HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("White space enum passed to checkFieldStringArray is null", execMsg,
					         "Unexpected exception message");
		} 
	  {
			String  errorMessagePrefix = "Field";
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
								            																						        null,
								            																						        HDLmZeroLengthOk.ZEROLENGTHNOTOK);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Report errors enum passed to checkFieldStringArray is null", execMsg,
					         "Unexpected exception message");
		} 
	  {
			String  errorMessagePrefix = "Field";
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
								            																						        null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Zero-length enum passed to checkFieldStringArray is null", execMsg,
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
}
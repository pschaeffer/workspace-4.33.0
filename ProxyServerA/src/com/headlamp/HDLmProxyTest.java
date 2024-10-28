package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
/**
 * HDLmProxyTest short summary.
 *
 * HDLmProxyTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmProxyTest {
  @BeforeAll
	static void HDLmProxyBeforeAll() {
		/* Build the tree of HDLmTree proxy definition nodes from some JSON */
		HDLmTree   proxyTopTree = HDLmTree.addToTreeProxy(HDLmTreeData.jsonGetProxyStrSaved1, HDLmEditorTypes.PROXY);
		HDLmProxy.setProxyTreeTop(proxyTopTree); 
		HashMap<String, HDLmProxy>   proxyMap = HDLmProxy.buildProxyMap(proxyTopTree);
		HDLmProxy.setProxyMapTop(proxyMap);
	}	
	@Test
	void HDLmProxyJsonConstructor() {
		/* Run a few HDLmProxyJsonConstructor tests */
		String       proxyName = "legends-owo.dnsalias.com";
    JsonParser   parser = new JsonParser();  
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetProxyStrSaved1); 
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
    /* We can now extract the actual proxy definitions from the one data row.
       The proxy definitions may actually be an array. We should process just
       the first element of the array.  */
    String proxyString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  proxyElement = parser.parse(proxyString); 
    if (proxyElement.isJsonArray()) {
    	JsonArray proxyArray = proxyElement.getAsJsonArray();
    	proxyElement = proxyArray.get(0);   	
    }
    JsonObject  proxyObject = proxyElement.getAsJsonObject();
    proxyElement = proxyObject.get("children"); 
    JsonArray  proxyArray = proxyElement.getAsJsonArray();
    /* Get the company JSON element */
    proxyElement = proxyArray.get(1);
    proxyObject = proxyElement.getAsJsonObject();
    proxyElement = proxyObject.get("children"); 
    proxyArray = proxyElement.getAsJsonArray();
    /* Get the proxy definition details */
    JsonElement proxyElementDetails = proxyObject.get("details"); 
    JsonObject proxyElementObject = proxyElementDetails.getAsJsonObject();
    Set<String>  proxyElementKeys = proxyElementObject.keySet();
    /* Build the proxy definition details */
    HDLmProxy  proxyDetails = new HDLmProxy(proxyElementDetails);
		/* Check the proxy definition node details */
		assertEquals(null, proxyDetails.getFinds(), "Proxy definition finds reference must be null"); 
		assertEquals(null, proxyDetails.getValues(), "Proxy definition values value must be null"); 
		assertNull(proxyDetails.getValues(), "Proxy definition values value must be null"); 
		assertEquals(true, proxyDetails.getEnabled(), "Proxy definition enabled value must be true");
		assertEquals(false, proxyDetails.getPathValueRe(), "Proxy definition regex value must be false");
		assertNotEquals(null, proxyDetails.getPathValueMatch(), "Proxy definition match must not be null");
		assertEquals(HDLmModTypes.NONE, proxyDetails.getType(), "Proxy definition type value must be 'NONE'");
		assertEquals(HDLmProxyTypes.INJECT, proxyDetails.getProxyType(), "Proxy definition type value must be 'INJECT");
		assertEquals(0, proxyDetails.getValuesCount(), "Proxy definition values count must be zero");
		assertEquals(null, proxyDetails.getParameterNumber(), "Proxy definition parameter number must be null");
		assertNull(proxyDetails.getParameterNumber(), "Proxy definition parameter number must be null");
		assertEquals(null, proxyDetails.getCssSelector(), "Proxy definition CSS Selected must be null");
		assertNull(proxyDetails.getCssSelector(), "Proxy definition CSS Selected must be null");
		assertNotEquals("Extra Bott", proxyDetails.getExtra(), "Proxy definition extra value must not be ('Extra Bott)");
		assertNull(proxyDetails.getExtra(), "Proxy definition extra value must be null");
		assertEquals(proxyName, proxyDetails.getName(), "Proxy definition name value must be '" + proxyName + "'");
		assertNotEquals(null, proxyDetails.getPathValueMatch(), "Proxy definition path value match must not be null");
		assertEquals(null, proxyDetails.getValue(), "Proxy definition value value must be null");
		assertEquals(null, proxyDetails.getValueSuffix(), "Proxy definition value suffix value must be null");
		assertEquals(null, proxyDetails.getXPath(), 
				         "Proxy definition XPath value must be null");		
		assertNull(proxyDetails.getXPath(), "Proxy definition XPath value must be null");
		assertEquals("oneworldobservatory.com", proxyDetails.getBackendName(), 
                 "Proxy definition backend host name value must be set");
    assertEquals(HDLmProtocolTypes.HTTPS, proxyDetails.getProtocolType(), 
                 "Proxy definition protocol type value must be 'HTTPS");
    assertEquals("legends-owo-secure.dnsalias.com", proxyDetails.getSecureServerName(), 
                 "Proxy definition secure server host name value must be set");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {new HDLmProxy((JsonElement) null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "JSON element used to build proxy definition details is null",
					         "Unexpected exception message");
		}
	}	
	@Test
	void buildProxyMap() {
		/* Run a few buildProxyMap tests */
		/* Build the tree of HDLmTree proxy definition nodes from some JSON */
		HDLmTree   proxyTree = HDLmTree.addToTree(HDLmTreeData.jsonGetProxyStr, HDLmEditorTypes.PROXY, HDLmStartupMode.STARTUPMODENO);
		HashMap<String, HDLmProxy>   proxyMap = HDLmProxy.buildProxyMap(proxyTree);
		assertNotNull(proxyMap, "Proxy map reference must not be null");
		int  proxyMapSize = proxyMap.size();
		assertEquals(7, proxyMapSize, "Proxy map size must be eight");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmProxy.buildProxyMap(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Proxy definitions tree passed to buildProxyMap is null",
					         "Unexpected exception message");
		}
	}	
	@Test
	void getProxyDefinition() {
		/* Run a few getProxyDefinition tests */
		String       proxyName = "legends-owo.dnsalias.com";
		HashMap<String, HDLmProxy>  proxyMap = HDLmProxy.getProxyMapTop();
		HDLmProxy    proxyDetails = HDLmProxy.getProxyDefinitionFromMap(proxyName, proxyMap);
		/* Check the proxy definition node details */
		assertEquals(null, proxyDetails.getFinds(), "Proxy definition finds reference must be null"); 
		assertEquals(null, proxyDetails.getValues(), "Proxy definition values value must be null"); 
		assertNull(proxyDetails.getValues(), "Proxy definition values value must be null"); 
		assertEquals(true, proxyDetails.getEnabled(), "Proxy definition enabled value must be true");
		assertEquals(false, proxyDetails.getPathValueRe(), "Proxy definition regex value must be false");
		assertNotEquals(null, proxyDetails.getPathValueMatch(), "Proxy definition match must not be null");
		assertEquals(HDLmModTypes.NONE, proxyDetails.getType(), "Proxy definition type value must be 'NONE'");
		assertEquals(HDLmProxyTypes.INJECT, proxyDetails.getProxyType(), "Proxy definition type value must be 'INJECT");
		assertEquals(0, proxyDetails.getValuesCount(), "Proxy definition values count must be zero");
		assertEquals(null, proxyDetails.getParameterNumber(), "Proxy definition parameter number must be null");
		assertNull(proxyDetails.getParameterNumber(), "Proxy definition parameter number must be null");
		assertEquals(null, proxyDetails.getCssSelector(), "Proxy definition CSS Selected must be null");
		assertNull(proxyDetails.getCssSelector(), "Proxy definition CSS Selected must be null");
		assertNotEquals("Extra Bott", proxyDetails.getExtra(), "Proxy definition extra value must not be ('Extra Bott)");
		assertNull(proxyDetails.getExtra(), "Proxy definition extra value must be null");
		assertEquals(proxyName, proxyDetails.getName(), "Proxy definition name value must be '" + proxyName + "'");
		assertNotEquals(null, proxyDetails.getPathValueMatch(), "Proxy definition path value match must not be null");
		assertEquals(null, proxyDetails.getValue(), "Proxy definition value value must be null");
		assertEquals(null, proxyDetails.getValueSuffix(), "Proxy definition value suffix value must be null");
		assertEquals(null, proxyDetails.getXPath(), 
				         "Proxy definition XPath value must be null");		
		assertNull(proxyDetails.getXPath(), "Proxy definition XPath value must be null");
		assertEquals("oneworldobservatory.com", proxyDetails.getBackendName(), 
                 "Proxy definition backend host name value must be set");
    assertEquals(HDLmProtocolTypes.HTTPS, proxyDetails.getProtocolType(), 
                 "Proxy definition protocol type value must be 'HTTPS");
    assertEquals("legends-owo-secure.dnsalias.com", proxyDetails.getSecureServerName(), 
                 "Proxy definition secure server host name value must be set");
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmProxy.getProxyDefinitionFromMap(null, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Proxy name passed to getProxyDefinition is null",
					         "Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmProxy.getProxyDefinitionFromMap("", null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Proxy map reference passed to getProxyDefinition is null",
					         "Unexpected exception message");
		}
	}		
  @Test
 	void proxyFieldProtocolType() { 
 		/* Run a few proxyFieldProtocolType tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.PROXY;
 		String           proxyName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetProxyStrSaved1); 
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
    /* We can now extract the actual proxy definition from the one data row.
       The proxy definitions may actually be an array. We should process just
       the first element of the array.  */
    String proxyJsonString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  proxyElement = parser.parse(proxyJsonString); 
    if (proxyElement.isJsonArray()) {
    	JsonArray proxyArray = proxyElement.getAsJsonArray();
    	proxyElement = proxyArray.get(0);   	
    }
    JsonObject  proxyObject = proxyElement.getAsJsonObject();
    proxyElement = proxyObject.get("children"); 
    JsonArray  proxyArray = proxyElement.getAsJsonArray();
    /* Get the company JSON element */
    proxyElement = proxyArray.get(0);
    proxyObject = proxyElement.getAsJsonObject();
    proxyElement = proxyObject.get("children"); 
    proxyArray = proxyElement.getAsJsonArray();
    /* Get the proxy definition details */
    JsonElement  proxyElementDetails = proxyObject.get("details"); 
    JsonObject   proxyElementObject = proxyElementDetails.getAsJsonObject();
    Set<String>  proxyElementKeys;
    proxyElementKeys = proxyElementObject.keySet();
    /* Build the proxy definition details */
    HDLmProxy  proxyDetails = new HDLmProxy(proxyElementDetails);
    /* Try to access the JSON element for the proxy definition type */
    String           fieldName = "backendType";
    JsonElement      proxyElementType = proxyElementObject.get(fieldName);
    JsonPrimitive    proxyPrimitiveType = proxyElementType.getAsJsonPrimitive();
    MutableInt       errors = new MutableInt(0);
    HDLmProtocolTypes  protocolType;
    protocolType = HDLmProxy.proxyFieldProtocolType(editorType, 
    		                                            errors, 
    		                                            proxyElementObject, 
    		                                            proxyElementKeys, 
    		                                            fieldName);
    assertNotNull(protocolType, "Proxy definition protocol type value is null");
    assertEquals(HDLmProtocolTypes.HTTPS, protocolType, "Proxy definition protocol type value is not 'HTTPS'");
    protocolType = HDLmProxy.proxyFieldProtocolType(editorType, 
    		                                            errors, 
    		                                            proxyElementObject, 
    		                                            proxyElementKeys, fieldName + "x", 
    		                                            HDLmReportErrors.DONTREPORTERRORS);
    assertEquals(HDLmProtocolTypes.NONE, protocolType, "Proxy definition type protocol value is not 'NONE'");
    int  intValue = errors.intValue();
    assertEquals(1, intValue, "Error value must be one");
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmProxy.proxyFieldProtocolType(editorType, 
 					                                                                       null, 
 					                                                                       proxyElementObject, 
 					                                                                       proxyElementKeys, 
 					                                                                       fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals(execMsg, "Mutable int for errors passed to proxyFieldProtocolType is null",
 					         "Unexpected exception message");
 		}
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmProxy.proxyFieldProtocolType(editorType, 
 					                              		                                     errors, 
 					                              		                                     null, 
 					                              		                                     proxyElementKeys, 
 					                              		                                     fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals(execMsg, "JSON object passed to proxyFieldProtocolType is null",
 					         "Unexpected exception message");
 		}
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmProxy.proxyFieldProtocolType(editorType, 
 					                              		                                     errors, 
 					                              		                                     proxyElementObject, 
 					                              		                                     null, 
 					                              		                                     fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals(execMsg, "Set of keys passed to proxyFieldProtocolType is null",
 					         "Unexpected exception message");
 		}  
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmProxy.proxyFieldProtocolType(editorType, 
 					                              		                                     errors, 
 					                              		                                     proxyElementObject, 
 					                              		                                     proxyElementKeys, 
 					                              		                                     null);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals(execMsg, "Name string passed to proxyFieldProtocolType is null",
 					         "Unexpected exception message");
 		} 
 	}
  @Test
 	void proxyFieldProxyType() { 
 		/* Run a few proxyFieldProxyType tests */
  	HDLmEditorTypes  editorType = HDLmEditorTypes.PROXY;
 		String           proxyName = "OWO Home Bottom Text";
    JsonParser       parser = new JsonParser();  
    JsonElement      topNode = parser.parse(HDLmTreeData.jsonGetProxyStrSaved1); 
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
    /* We can now extract the actual proxy definition from the one data row.
       The proxy definitions may actually be an array. We should process just
       the first element of the array.  */
    String proxyJsonString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  proxyElement = parser.parse(proxyJsonString); 
    if (proxyElement.isJsonArray()) {
    	JsonArray proxyArray = proxyElement.getAsJsonArray();
    	proxyElement = proxyArray.get(0);   	
    }
    JsonObject  proxyObject = proxyElement.getAsJsonObject();
    proxyElement = proxyObject.get("children"); 
    JsonArray  proxyArray = proxyElement.getAsJsonArray();
    /* Get the company JSON element */
    proxyElement = proxyArray.get(0);
    proxyObject = proxyElement.getAsJsonObject();
    proxyElement = proxyObject.get("children"); 
    proxyArray = proxyElement.getAsJsonArray();
    /* Get the proxy definition details */
    JsonElement  proxyElementDetails = proxyObject.get("details"); 
    JsonObject   proxyElementObject = proxyElementDetails.getAsJsonObject();
    Set<String>  proxyElementKeys;
    proxyElementKeys = proxyElementObject.keySet();
    /* Build the proxy definition details */
    HDLmProxy  proxyDetails = new HDLmProxy(proxyElementDetails);
    /* Try to access the JSON element for the proxy definition type */
    String           fieldName = "type";
    JsonElement      proxyElementType = proxyElementObject.get(fieldName);
    JsonPrimitive    proxyPrimitiveType = proxyElementType.getAsJsonPrimitive();
    MutableInt       errors = new MutableInt(0);
    HDLmProxyTypes   proxyType;
    proxyType = HDLmProxy.proxyFieldProxyType(editorType, 
    		                                      errors, 
    		                                      proxyElementObject, 
    		                                      proxyElementKeys, 
    		                                      fieldName);
    assertNotNull(proxyType, "Proxy definition type value is null");
    assertEquals(HDLmProxyTypes.INJECT, proxyType, "Proxy definition type value is not 'INJECT'");
    proxyType = HDLmProxy.proxyFieldProxyType(editorType, 
    		                                      errors, 
    		                                      proxyElementObject, 
    		                                      proxyElementKeys, fieldName + "x", 
    		                                      HDLmReportErrors.DONTREPORTERRORS);
    assertEquals(HDLmProxyTypes.NONE, proxyType, "Proxy definition type value is not 'NONE'");
    int  intValue = errors.intValue();
    assertEquals(1, intValue, "Error value must be one");
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmProxy.proxyFieldProxyType(editorType, 
 					                                                                    null, 
 					                                                                    proxyElementObject, 
 					                                                                    proxyElementKeys, 
 					                                                                    fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals(execMsg, "Mutable int for errors passed to proxyFieldProxyType is null",
 					         "Unexpected exception message");
 		}
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmProxy.proxyFieldProxyType(editorType, 
 					                              		                                  errors, 
 					                              		                                  null, 
 					                              		                                  proxyElementKeys, 
 					                              		                                  fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals(execMsg, "JSON object passed to proxyFieldProxyType is null",
 					         "Unexpected exception message");
 		}
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmProxy.proxyFieldProxyType(editorType, 
 					                              		                                  errors, 
 					                              		                                  proxyElementObject, 
 					                              		                                  null, 
 					                              		                                  fieldName);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals(execMsg, "Set of keys passed to proxyFieldProxyType is null",
 					         "Unexpected exception message");
 		}  
 	  {
 			Throwable exception = assertThrows(RuntimeException.class, 
 					                               () -> {HDLmProxy.proxyFieldProxyType(editorType, 
 					                              		                                  errors, 
 					                              		                                  proxyElementObject, 
 					                              		                                  proxyElementKeys, 
 					                              		                                  null);},
 					                               "Expected RuntimeException");
 			String execMsg = exception.getMessage();
 			assertEquals(execMsg, "Name string passed to proxyFieldProxyType is null",
 					         "Unexpected exception message");
 		} 
 	}
}
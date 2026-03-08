package com.headlamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmOpenRouterTest short summary.
 *
 * HDLmOpenRouterTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmOpenRouterTest {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmOpenRouterTest.class); 
  @BeforeAll
	static void HDLmUtilityBeforeAll() {
		/* Set some of the configuration values from secrets stored inside
	     AWS. We get the AWS secrets using the AWS secrets manager. Note
	     that some of the secrets are used to establish database connections.
	     As a consequence, this step must come before any database 
	     connections are established. */
  	HDLmConfig.setConfigurationValues();
	  /* Add all of the general events to the events map */
	  HDLmEvent.addEventsList(HDLmEvent.eventNames);
	}
  @Test
	void buildAuthorizationHeader() {
		String  apiKeyStr = "abc";
		String  desiredHeader = "Authorization: Bearer abc";
		String  newHeader;
		newHeader = HDLmOpenRouter.buildAuthorizationHeaderUsingAPIKeyStr(apiKeyStr);
		assertEquals(newHeader, desiredHeader, "New authorization header is invalid");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                           () -> {HDLmOpenRouter.buildAuthorizationHeaderUsingAPIKeyStr(null);},
					                           "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("API key string reference passed to buildAuthorizationHeaderUsingAPIKeyStr is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void buildContentTypeHeader() {		
		/* Run a few (actually one) buildContentTypeHeader tests */   
		String  contentTypeStr = "abc"; 
		String  desiredHeader = "Content-Type: abc";
		String  newHeader;
		/* Build the content type header */ 		 
		newHeader = HDLmOpenRouter.buildContentTypeHeader(contentTypeStr); 
		assertEquals(newHeader, desiredHeader, "New content type header is invalid");
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.buildContentTypeHeader(null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Content type string reference passed to buildContentTypeHeader is null", execMsg,
					         "Unexpected exception message");
		}					
	}
	@Test
	void buildJsonContentTypeHeader() {		
		/* Run a few (actually one) buildJsonContentTypeHeader tests */   
		String  desiredHeader = "Content-Type: application/json";
		String  newHeader;
		/* Build the application/json content type header */ 		 
		newHeader = HDLmOpenRouter.buildJsonContentTypeHeader(); 
		assertEquals(newHeader, desiredHeader, "New content type header is invalid");
	}
	@Test
	void buildMultipartFormContentTypeHeader() {		
		/* Run a few (actually one) buildMultipartFormContentTypeHeader tests */   
		String  boundaryStr = "abc"; 
		String  desiredHeader = "Content-Type: multipart/form-data; boundary=abc";
		String  newHeader;
		/* Build the content type header */ 		 
		newHeader = HDLmOpenRouter.buildMultipartFormContentTypeHeader(boundaryStr); 
		assertEquals(newHeader, desiredHeader, "New boundary header is invalid");
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.buildMultipartFormContentTypeHeader(null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Boundary string reference passed to buildMultipartFormContentTypeHeader is null", execMsg,
					         "Unexpected exception message");
		}					
	}
	@Test
	void executeOpenRouterRequest() {
		Throwable exception = assertThrows(RuntimeException.class,
				                               () -> {HDLmOpenRouter.executeOpenRouterRequestWebPageImprover(null);},
				                               "Expected RuntimeException");
		String execMsg = exception.getMessage();
		assertEquals("Body string reference passed to executeOpenRouterRequest is null", execMsg,
				         "Unexpected exception message");
	}	
	@Test
	void getImageChoices() { 
		/* Run a few (actually one) getImageChoices tests */  
		/* Declare and define a few variables */
		/* The original image is always obtained from Open AI even for Open Router. Open
	     Router does not have a CDN that we can use to get an image for testing. As 
	     a consequence, we use the original image from Open AI's CDN as the input
	     image for testing in all cases. */
		String  inputImageUrl = "https://cdn.openai.com/API/images/guides/image_variation_original.webp";
		/* Get some variations on the image */
		String  urlStr = "";
		String  pathValueStr = "/";
		int   i = 0;
		for(i = 0; i < 1; i++) {
			HDLmResponse  outResponse = HDLmOpenRouter.getImageChoices(inputImageUrl, urlStr, pathValueStr, HDLmApiKey.APIKEYOPENROUTERTEST);
			String  errorMessage = outResponse.getErrorMessage();
			if (errorMessage != null) {
				assertNotEquals(0, errorMessage.length(), "Error message length is not zero in getImageChoices");
				continue;
			}
		  /* Try to get each of the choices/variants from the Open AI output */
		  ArrayList<String>   choiceList = outResponse.getStringList();
		  if (choiceList == null)
		  	choiceList = null;
		  assertNotNull(choiceList, "Choice list is null in getImageChoices");
		  /* Check if the choice list is empty */
		  int   choiceListLen = 0;
		  if (choiceList != null) {
		  	choiceListLen = choiceList.size();
		    assertNotEquals(choiceListLen, 0, "Choice list is empty in getImageChoices");
		  }		
		}
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getImageChoices(null, urlStr, pathValueStr, HDLmApiKey.APIKEYOPENROUTERTEST);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Input image URL reference passed to getImageChoices is null", execMsg,
					         "Unexpected exception message");
		}		
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getImageChoices(inputImageUrl, null, pathValueStr, HDLmApiKey.APIKEYOPENROUTERTEST);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Web page URL string reference passed to getImageChoices is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getImageChoices(inputImageUrl, urlStr, null, HDLmApiKey.APIKEYOPENROUTERTEST);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Web page Path value string reference passed to getImageChoices is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getImageChoices(inputImageUrl, urlStr, pathValueStr, null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("API key enum reference passed to getImageChoices is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getSomeOpenRouterData() {
		String localPathStr = "api/v1/chat/completions";
		ArrayList<String> localHeaderList = new ArrayList<String>();
		String localJsonData = "{}";
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmOpenRouter.getSomeOpenRouterData(null,
							                                                                       localHeaderList,
							                                                                       localJsonData);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path string reference passed to getSomeOpenRouterData is null", execMsg,
				    	     "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                           () -> {HDLmOpenRouter.getSomeOpenRouterData(localPathStr,
							                                                      null,
							                                                      localJsonData);},
					                           "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Header list reference passed to getSomeOpenRouterData is null", execMsg,
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                           () -> {HDLmOpenRouter.getSomeOpenRouterData(localPathStr,
							                                                      localHeaderList,
							                                                      null);},
					                           "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Json data reference passed to getSomeOpenRouterData is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getSomeOpenRouterImageData() {		
		/* Run a few (actually one) getSomeOpenRouterImageData tests */  
		/* Declare and define a few variables */
		/* The original image is always obtained from Open AI even for Open Router. Open
	     Router does not have a CDN that we can use to get an image for testing. As 
	     a consequence, we use the original image from Open AI's CDN as the input
	     image for testing in all cases. */
		String  inputUrl = "https://cdn.openai.com/API/images/guides/image_variation_original.webp";
		/* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenRouter.buildHeaders(HDLmApiKey.APIKEYOPENROUTERTEST); 
	  String  jsonData = HDLmOpenRouter.buildJsonImageVariations(inputUrl);
	  /* Try to get some image choices/variants */
	  HDLmResponse  outResponse = HDLmOpenRouter.getSomeOpenRouterImageData("v1/chat/completions", headerList, jsonData);
	  String  errorMessage = outResponse.getErrorMessage();
	  if (errorMessage != null) {
	  	assertNotEquals(0, errorMessage.length(), "Error message length is not zero in getSomeOpenRouterImageData");
	  	return;
	  }
	  String  outputJson = outResponse.getReturnString();
	  if (outputJson == null)
	  	outputJson = null;
	  assertNotNull(outputJson, "Output JSON is null in getSomeOpenRouterImageData");
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   choiceList = HDLmOpenRouter.getImageVariations(outputJson);	  
	  if (choiceList == null)
	  	choiceList = null;
	  assertNotNull(choiceList, "Choice list is null in getSomeOpenRouterImageData");
	  /* Check if the choice list is empty */
	  int   choiceListLen = 0;
	  if (choiceList != null) {
	  	choiceListLen = choiceList.size();
	    if (choiceListLen == 0) {
	    	assertTrue(outputJson.contains("\"choices\""), "Output JSON has no choices in getSomeOpenRouterImageData");
	    }
	  }
		{ 
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
		  String              localJsonData = jsonData;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getSomeOpenRouterImageData(null,
					                              		                                              localHeaderList,
					                              		                                              localJsonData);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path string reference passed to getSomeOpenRouterImageData is null", execMsg,
					         "Unexpected exception message");
		}		
		{  
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
		  String              localJsonData = jsonData;
			Throwable exception = assertThrows(RuntimeException.class, 
																				 () -> {HDLmOpenRouter.getSomeOpenRouterImageData(localPathStr,
															                                                            null,
																		                                                      localJsonData);},  
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Header list reference passed to getSomeOpenRouterImageData is null", execMsg,
					         "Unexpected exception message");
		}
		{  
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getSomeOpenRouterImageData(localPathStr,
					                              		                                              localHeaderList,
					                              		                                              null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Json data reference passed to getSomeOpenRouterImageData is null", execMsg,
					         "Unexpected exception message");
		}
	}	
	@Test
	void getSomeOpenRouterTextData() {		
		/* Run a few (actually one) getSomeOpenRouterTextData tests */  
		/* Declare and define a few variables */
		String  inputStr = "Buy Now"; 
		/* Get some variations on the text */
		String  urlStr = "";
		String  pathValueStr = "/";
		/* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenRouter.buildHeaders(HDLmApiKey.APIKEYOPENROUTERTEST);  
    HDLmResponse  buildResponse = HDLmOpenRouter.buildJsonTextVariations(inputStr, urlStr, pathValueStr);
		String   dataJson = buildResponse.getReturnString();
	  /* Try to get some text choices/variants */
	  HDLmResponse  outputResponse = HDLmOpenRouter.getSomeOpenRouterTextData("v1/chat/completions", headerList, dataJson);
	  String        outputJson = outputResponse.getReturnString();
	  if (outputJson == null)
	  	outputJson = null;
	  assertNotNull(outputJson, "Output JSON is null in getSomeOpenRouterTextData");	
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   choiceList = HDLmOpenRouter.getTextVariations(outputJson);
	  if (choiceList == null)
	  	choiceList = null;
		/* Check the choice list */
	  assertNotNull(choiceList, "Choice list is null in getSomeOpenRouterTextData");
	  /* Check if the choice list is empty */
	  int   choiceListLen = 0;
	  if (choiceList != null) {
	  	choiceListLen = choiceList.size();
	    assertNotEquals(choiceListLen, 0, "Choice list is empty in getSomeOpenRouterTextData");
	  }
		{ 
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
      String              localJsonData = "";	
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getSomeOpenRouterTextData(null,
					                              		                                     localHeaderList,
					                              		                                     localJsonData);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path string reference passed to getSomeOpenRouterTextData is null", execMsg,
					         "Unexpected exception message");
		}		
		{  
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
		  String              localJsonData = "";	
			Throwable exception = assertThrows(RuntimeException.class, 
																				 () -> {HDLmOpenRouter.getSomeOpenRouterTextData(localPathStr,
															                                                   null,
															                                                   localJsonData);},  
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Header list reference passed to getSomeOpenRouterTextData is null", execMsg,
					         "Unexpected exception message");
		}
		{  
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
		  String              localJsonData = "";		
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getSomeOpenRouterTextData(localPathStr,
					                              		                                     localHeaderList,
					                              		                                     null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Json data reference passed to getSomeOpenRouterTextData is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getSquareImage() {		
		/* Run a few (actually one) getSquareImage tests */  
		/* Declare and define a few variables */
		/* The original image is always obtained from Open AI even for Open Router. Open
		   Router does not have a CDN that we can use to get an image for testing. As 
		   a consequence, we use the original image from Open AI's CDN as the input
		   image for testing in all cases. */
		String  inputUrl = "https://cdn.openai.com/API/images/guides/image_variation_original.webp";
		/* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenRouter.buildHeaders(HDLmApiKey.APIKEYOPENROUTERTEST);	  
		/* Build the JSON string used below. The call below will use    
		   the HDLmOpenRouter.getSquareImage routine. */
		String  jsonData = HDLmOpenRouter.buildJsonImageVariations(inputUrl);
	  /* Try to get some image choices/variants */	  
	  HDLmResponse  outResponse = HDLmOpenRouter.getSomeOpenRouterImageData("v1/chat/completions", headerList, jsonData); 
	  String  errorMessage = outResponse.getErrorMessage();
	  if (errorMessage != null) {
	  	assertNotEquals(0, errorMessage.length(), "Error message length is not zero in getSquareImage");
	  }
	  else {
 	    String  outputJson = outResponse.getReturnString();	  
	    if (outputJson == null)
	  	  outputJson = null;
	    assertNotNull(outputJson, "Output JSON is null in getSquareImage");
	    /* Try to get each of the choices/variants from the Open AI output */
	    ArrayList<String>   choiceList = HDLmOpenRouter.getImageVariations(outputJson);	  
	    if (choiceList == null)
	  	  choiceList = null;
	    assertNotNull(choiceList, "Choice list is null in getSquareImage");
	    /* Check if the choice list is empty */
	    int   choiceListLen = 0;
	    if (choiceList != null) {
	  	  choiceListLen = choiceList.size();
	      if (choiceListLen == 0) {
	    	  assertTrue(outputJson.contains("\"choices\""), "Output JSON has no choices in getSquareImage");
	      }
	    }
	  }
		{ 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getSquareImage(null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Buffered image reference passed to getSquareImage is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getTextChoices() {		
		/* Run a few (actually one) getTextChoices tests */  
		/* Declare and define a few variables */
		String  inputStr = "Buy Now"; 
		/* Get some variations on the text */
		String  urlStr = "";
		String  pathValueStr = "/";
		HDLmResponse  outResponse = HDLmOpenRouter.getTextChoices(inputStr, urlStr, pathValueStr, HDLmApiKey.APIKEYOPENROUTERTEST);
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   choiceList = outResponse.getStringList();
	  if (choiceList == null)
	  	choiceList = null;
	  assertNotNull(choiceList, "Choice list is null in getTextChoices");
	  /* Check if the choice list is empty */
	  int   choiceListLen = 0;
	  if (choiceList != null) {
	  	choiceListLen = choiceList.size();
	    assertNotEquals(choiceListLen, 0, "Choice list is empty in getTextChoices");
	  }
		{ 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getTextChoices(null, urlStr, pathValueStr, HDLmApiKey.APIKEYOPENROUTERTEST);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Input string reference passed to getTextChoices is null", execMsg,
					         "Unexpected exception message");
		}
		{ 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getTextChoices(inputStr, null, pathValueStr, HDLmApiKey.APIKEYOPENROUTERTEST);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("URL string reference passed to getTextChoices is null", execMsg,
					         "Unexpected exception message");
		}
		{ 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getTextChoices(inputStr, urlStr, null, HDLmApiKey.APIKEYOPENROUTERTEST);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path value string reference passed to getTextChoices is null", execMsg,
					         "Unexpected exception message");
		}
		{ 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenRouter.getTextChoices(inputStr, urlStr, pathValueStr, null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("API key enum reference passed to getTextChoices is null", execMsg,
					         "Unexpected exception message");
		}
	}
}

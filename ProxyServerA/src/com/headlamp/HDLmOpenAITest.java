package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue; 

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmOpenAITest short summary.
 *
 * HDLmOpenAITest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmOpenAITest {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmOpenAITest.class); 
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
		/* Run a few (actually one) buildAuthorizationHeader tests */   
		String  apiKeyStr = "abc"; 
		String  desiredHeader = "Authorization: Bearer abc";
		String  newHeader;
		/* Build the authorization header */ 		 
		newHeader = HDLmOpenAI.buildAuthorizationHeaderUsingAPIKeyStr(apiKeyStr); 
		assertEquals(newHeader, desiredHeader, "New authorization header is invalid");
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.buildAuthorizationHeaderUsingAPIKeyStr(null);}, 
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
		newHeader = HDLmOpenAI.buildContentTypeHeader(contentTypeStr); 
		assertEquals(newHeader, desiredHeader, "New content type header is invalid");
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.buildContentTypeHeader(null);}, 
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
		newHeader = HDLmOpenAI.buildJsonContentTypeHeader(); 
		assertEquals(newHeader, desiredHeader, "New content type header is invalid");
	}
	@Test
	void buildMultipartFormContentTypeHeader() {		
		/* Run a few (actually one) buildMultipartFormContentTypeHeader tests */   
		String  boundaryStr = "abc"; 
		String  desiredHeader = "Content-Type: multipart/form-data; boundary=abc";
		String  newHeader;
		/* Build the content type header */ 		 
		newHeader = HDLmOpenAI.buildMultipartFormContentTypeHeader(boundaryStr); 
		assertEquals(newHeader, desiredHeader, "New boundary header is invalid");
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.buildMultipartFormContentTypeHeader(null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Boundary string reference passed to buildMultipartFormContentTypeHeader is null", execMsg,
					         "Unexpected exception message");
		}					
	}
	@Test
	void getImageChoices() { 
		/* Run a few (actually one) getImageChoices tests */  
		/* Declare and define a few variables */
		String  inputImageUrl = "https://cdn.openai.com/API/images/guides/image_variation_original.webp";
		/* Get some variations on the image */
		String  urlStr = "";
		String  pathValueStr = "/";
		int   i = 0;
		for(i = 0; i < 1; i++) {
			HDLmResponse  outResponse = HDLmOpenAI.getImageChoices(inputImageUrl, urlStr, pathValueStr);
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
					                               () -> {HDLmOpenAI.getImageChoices(null, urlStr, pathValueStr);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Input image URL reference passed to getImageChoices is null", execMsg,
					         "Unexpected exception message");
		}		
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getImageChoices(inputImageUrl, null, pathValueStr);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Web page URL string reference passed to getImageChoices is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getImageChoices(inputImageUrl, urlStr, null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Web page Path value string reference passed to getImageChoices is null", execMsg,
					         "Unexpected exception message");
		}	
	}
	@Test
	void getSomeOpenAIImageData() {		
		/* Run a few (actually one) getSomeOpenAIImageData tests */  
		/* Declare and define a few variables */
		String  inputUrl = "https://cdn.openai.com/API/images/guides/image_variation_original.webp";
		/* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenAI.buildHeaders(); 
	  /* Build the data entity used below */
	  HttpEntity  dataEntity = HDLmOpenAI.buildDataEntity(inputUrl);
	  /* Use the entity to get the multipart/form-data header that must
	     be added to the array of headers */
	  Header  contentTypeHeader = dataEntity.getContentType();
	  String  contentTypeHeaderStr = contentTypeHeader.toString(); 
	  headerList.add(contentTypeHeaderStr);	  
	  /* Try to get some image choices/variants */
	  HDLmResponse  outResponse = HDLmOpenAI.getSomeOpenAIImageData("v1/images/variations", headerList, dataEntity);
	  String  outputJson = outResponse.getReturnString();
	  if (outputJson == null)
	  	outputJson = null;
	  assertNotNull(outputJson, "Output JSON is null in getSomeOpenAIImageData");
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   choiceList = HDLmOpenAI.getImageVariations(outputJson);	  
	  if (choiceList == null)
	  	choiceList = null;
	  assertNotNull(choiceList, "Choice list is null in getSomeOpenAIImageData");
	  /* Check if the choice list is empty */
	  int   choiceListLen = 0;
	  if (choiceList != null) {
	  	choiceListLen = choiceList.size();
	    assertNotEquals(choiceListLen, 0, "Choice list is empty in getSomeOpenAIImageData");
	  }
		{ 
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
      HttpEntity          localDataEntity = dataEntity;			
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getSomeOpenAIImageData(null,
					                              		                                      localHeaderList,
					                              		                                      localDataEntity);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path string reference passed to getSomeOpenAIImageData is null", execMsg,
					         "Unexpected exception message");
		}		
		{  
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
      HttpEntity          localDataEntity = dataEntity;	
			Throwable exception = assertThrows(RuntimeException.class, 
																				 () -> {HDLmOpenAI.getSomeOpenAIImageData(localPathStr,
															                                                    null,
															                                                    localDataEntity);},  
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Header list reference passed to getSomeOpenAIImageData is null", execMsg,
					         "Unexpected exception message");
		}
		{  
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
      HttpEntity          localDataEntity = dataEntity;	
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getSomeOpenAIImageData(localPathStr,
					                              		                                      localHeaderList,
					                              		                                      null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Data entity reference passed to getSomeOpenAIImageData is null", execMsg,
					         "Unexpected exception message");
		}
	}	
	@Test
	void getSomeOpenAITextData() {		
		/* Run a few (actually one) getSomeOpenAITextData tests */  
		/* Declare and define a few variables */
		String  inputStr = "Buy Now"; 
		/* Get some variations on the text */
		String  urlStr = "";
		String  pathValueStr = "/";
		/* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenAI.buildHeaders();  
    HDLmResponse  buildResponse = HDLmOpenAI.buildJsonTextVariations(inputStr, urlStr, pathValueStr);
		String   dataJson = buildResponse.getReturnString();
	  /* Try to get some text choices/variants */
	  HDLmResponse  outputResponse = HDLmOpenAI.getSomeOpenAITextData("v1/chat/completions", headerList, dataJson);
	  String        outputJson = outputResponse.getReturnString();
	  if (outputJson == null)
	  	outputJson = null;
	  assertNotNull(outputJson, "Output JSON is null in getSomeOpenAITextData");	
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   choiceList = HDLmOpenAI.getTextVariations(outputJson);
	  if (choiceList == null)
	  	choiceList = null;
		/* Check the choice list */
	  assertNotNull(choiceList, "Choice list is null in getSomeOpenAITextData");
	  /* Check if the choice list is empty */
	  int   choiceListLen = 0;
	  if (choiceList != null) {
	  	choiceListLen = choiceList.size();
	    assertNotEquals(choiceListLen, 0, "Choice list is empty in getSomeOpenAITextData");
	  }
		{ 
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
      String              localJsonData = "";	
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getSomeOpenAITextData(null,
					                              		                                     localHeaderList,
					                              		                                     localJsonData);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path string reference passed to getSomeOpenAITextData is null", execMsg,
					         "Unexpected exception message");
		}		
		{  
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
		  String              localJsonData = "";	
			Throwable exception = assertThrows(RuntimeException.class, 
																				 () -> {HDLmOpenAI.getSomeOpenAITextData(localPathStr,
															                                                   null,
															                                                   localJsonData);},  
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Header list reference passed to getSomeOpenAITextData is null", execMsg,
					         "Unexpected exception message");
		}
		{  
			String              localPathStr = "";
		  ArrayList<String>   localHeaderList = headerList;
		  String              localJsonData = "";		
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getSomeOpenAITextData(localPathStr,
					                              		                                     localHeaderList,
					                              		                                     null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Json data reference passed to getSomeOpenAITextData is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getSquareImage() {		
		/* Run a few (actually one) getSquareImage tests */  
		/* Declare and define a few variables */
		String  inputUrl = "https://cdn.openai.com/API/images/guides/image_variation_original.webp";
		/* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenAI.buildHeaders();	  
		/* Build the data entity used below. The call below will use    
		   the HDLmOpenAI.getSquareImage routine. */
	  HttpEntity  dataEntity = HDLmOpenAI.buildDataEntity(inputUrl);
	  /* Use the entity to get the multipart/form-data header that must
	     be added to the array of headers */
	  Header  contentTypeHeader = dataEntity.getContentType();
	  String  contentTypeHeaderStr = contentTypeHeader.toString(); 
	  headerList.add(contentTypeHeaderStr);	  
	  /* Try to get some image choices/variants */
	  HDLmResponse  outResponse = HDLmOpenAI.getSomeOpenAIImageData("v1/images/variations", headerList, dataEntity);
	  String  outputJson = outResponse.getReturnString();	  
	  if (outputJson == null)
	  	outputJson = null;
	  assertNotNull(outputJson, "Output JSON is null in getSquareImage");
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   choiceList = HDLmOpenAI.getImageVariations(outputJson);	  
	  if (choiceList == null)
	  	choiceList = null;
	  assertNotNull(choiceList, "Choice list is null in getSquareImage");
	  /* Check if the choice list is empty */
	  int   choiceListLen = 0;
	  if (choiceList != null) {
	  	choiceListLen = choiceList.size();
	    assertNotEquals(choiceListLen, 0, "Choice list is empty in getSquareImage");
	  }	 
		{ 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getSquareImage(null);}, 
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
		HDLmResponse  outResponse = HDLmOpenAI.getTextChoices(inputStr, urlStr, pathValueStr);
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
					                               () -> {HDLmOpenAI.getTextChoices(null, urlStr, pathValueStr);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Input string reference passed to getTextChoices is null", execMsg,
					         "Unexpected exception message");
		}
		{ 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getTextChoices(inputStr, null, pathValueStr);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("URL string reference passed to getTextChoices is null", execMsg,
					         "Unexpected exception message");
		}
		{ 
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmOpenAI.getTextChoices(inputStr, urlStr, null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Path value string reference passed to getTextChoices is null", execMsg,
					         "Unexpected exception message");
		}
	}
}
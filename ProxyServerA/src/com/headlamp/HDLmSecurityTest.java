package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;  
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmSecurityTest short summary.
 *
 * HDLmSecurityTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmSecurityTest {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmSecurityTest.class); 
  @Test
	void checkUsernameExists() {
		/* Run a few checkUsernamePassword tests */
		boolean   outResponse; 
		String    userName = "pschaeffer";
		outResponse = HDLmSecurity.checkUsernameExists(userName);
		assertTrue(outResponse == true, "Output response is not true in checkUsernameExists");
	  /* Run another test with invalid information */ 
		userName = "junk1234";
		outResponse = HDLmSecurity.checkUsernameExists(userName); 
		assertTrue(outResponse == false, "Output response is not false in checkUsernameExists");
	}
	@Test
	void checkUsernamePassword() {
		/* Run a few checkUsernamePassword tests */
		HDLmApacheResponse  outResponse;
		String              outStr;
		String              passwordStr = "Pentium8023!";
		String              userName = "pschaeffer";
		outResponse = HDLmSecurity.checkUsernamePassword(userName, passwordStr);
		outStr = outResponse.getStringContent();
		assertNotNull(outStr, "Null JSON response recieved from check user name and password routine");
		/* Try to convert the JSON to a JSON object. If this fails, 
	     then we do not have a string than can be converted to a 
	     JSON object. If this works, then we do have string than 
	     can be converted to a JSON object. */
    JsonParser    parser = new JsonParser();		
    JsonElement   topNodeJsonElement = null; 
		try {
		  topNodeJsonElement = parser.parse(outStr);
		} 
		catch (Exception exception) {		
			if (outStr != null)
			  LOG.info("JSON string - " + outStr);
			LOG.info("Exception while executing checkUsernamePassword");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}		
    /* Check if the JSON element is a JSON object */
	  if (!topNodeJsonElement.isJsonObject()) {
	    String  errorText = "JSON string in checkUsernamePassword is invalid";
	    HDLmAssertAction(false, errorText);
    }
	  /* Get all of the information from the JSON */
	  String  challengeStr = HDLmJson.getJsonString(topNodeJsonElement, "ChallengeName");
	  assertNotNull(challengeStr, "Challenge name string is null in response to check user name and password routine");
	  assertEquals(challengeStr, "SMS_MFA", "Challenge string is wrong in response to check user name and password routine");
	  String  sessionStr = HDLmJson.getJsonString(topNodeJsonElement, "Session");
	  assertNotNull(sessionStr, "Session string is null in response to check user name and password routine");
	  /* Get the challenge parameters */
	  JsonElement   challengeObj = HDLmJson.getJsonObject(topNodeJsonElement, "ChallengeParameters");
    /* Check if the JSON element is a JSON object */
	  if (!challengeObj.isJsonObject()) {
	    String  errorText = "JSON element in checkUsernamePassword is invalid";
	    HDLmAssertAction(false, errorText);
    }
	  /* Run another test with invalid information */
		passwordStr = "junk1234";
		userName = "junk1234";
		outResponse = HDLmSecurity.checkUsernamePassword(userName, passwordStr);
		outStr = outResponse.getStringContent();
		assertNotNull(outStr, "Output string is null in checkUsernamePassword");
	}
	@Test
	void convertScopeStr() {
		/* Run a few convertScopeStr tests */
		int                           vectorLen = 0;
		int                           vectorEntryLen = 0;
		String                        scopeStr = null;
		String                        vectorEntryValue = null;
		ArrayList<ArrayList<String>>  scopeVector = null; 
		ArrayList<String>             vectorEntry = null;
		scopeStr = " ( admin ) "; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
	  vectorEntryLen = vectorEntry.size();
		assertEquals(1, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("*", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = " ( admin ) "; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(1, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("*", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = " ( yogadirect ) "; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(1, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = "( yogadirect ) "; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(1, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = "(yogadirect ) "; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(1, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = "(yogadirect) "; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(1, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = "(yogadirect)"; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(1, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = "(yogadirect yogaaccessories)"; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(2, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		vectorEntryValue = vectorEntry.get(1);
		assertEquals("yogaaccessories", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = "(yogadirect,yogaaccessories)"; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(2, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		vectorEntryValue = vectorEntry.get(1);
		assertEquals("yogaaccessories", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = "(yogadirect  ,yogaaccessories)"; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(2, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		vectorEntryValue = vectorEntry.get(1);
		assertEquals("yogaaccessories", vectorEntryValue, "Scope vector entry contents are wrong");
		scopeStr = "(yogadirect  ,  yogaaccessories)"; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(1, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(2, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		vectorEntryValue = vectorEntry.get(1);
		assertEquals("yogaaccessories", vectorEntryValue, "Scope vector entry contents are wrong");			
		scopeStr = "(yogadirect  ,  yogaaccessories) yogaother"; 
		scopeVector = HDLmSecurity.convertScopeString(scopeStr); 
		assertNotNull(scopeVector, "Null response from convert scope string routine");
		vectorLen = scopeVector.size();
		assertEquals(2, vectorLen, "Scope vector length is wrong");
		vectorEntry = scopeVector.get(0);
		vectorEntryLen = vectorEntry.size();
		assertEquals(2, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogadirect", vectorEntryValue, "Scope vector entry contents are wrong");
		vectorEntryValue = vectorEntry.get(1);
		assertEquals("yogaaccessories", vectorEntryValue, "Scope vector entry contents are wrong");	
		vectorEntry = scopeVector.get(1);
		vectorEntryLen = vectorEntry.size();
		assertEquals(1, vectorEntryLen, "Scope vector entry length is wrong");
		vectorEntryValue = vectorEntry.get(0);
		assertEquals("yogaother", vectorEntryValue, "Scope vector entry contents are wrong");
		/* Check the error messages from the convert scope string routine */
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.convertScopeString(null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Scope string passed to convertScopeString is null", execMsg,
					         "Unexpected exception message");
		}	
		{ 
			String  localScopeStr = "";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("The number of scope vector entries (0) is invalid", execMsg,
					         "Unexpected error message");
		}	
		{ 
			String  localScopeStr = "a b c d";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("The number of scope vector entries (4) is invalid", execMsg,
					         "Unexpected error message");
		}	
		{ 
			String  localScopeStr = "a (b";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("The final parenthesis count (1) is not zero", execMsg,
					         "Unexpected error message");
		}	
		{ 
			String  localScopeStr = "19";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Invalid token (19) found at position (1)", execMsg,
					         "Unexpected error message");
		}	
		{ 
			String  localScopeStr = "a b =";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Invalid operator token (=) detected at position (5)", execMsg,
					         "Unexpected error message");
		}	
		{ 
			String  localScopeStr = "a b ()";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Invalid parse state (AFTERLPAREN), right parenthesis found in invalid location", execMsg,
					         "Unexpected error message");
		}
		{ 
			String  localScopeStr = "a b )";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Invalid parse state (BEFOREENTRY), right parenthesis found in invalid location", execMsg,
					         "Unexpected error message");
		}
		{ 
			String  localScopeStr = "a b ((";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Invalid parse state (AFTERLPAREN), left parenthesis found in invalid location", execMsg,
					         "Unexpected error message");
		}
		{ 
			String  localScopeStr = "a b (,";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Invalid parse state (AFTERLPAREN), comma found in invalid location", execMsg,
					         "Unexpected error message");
		}
		{ 
			String  localScopeStr = "a b (c*";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Invalid parse state (AFTERIDENINPARENS), asterisk found in invalid location", execMsg,
					         "Unexpected error message");
		}
		{ 
			String  localScopeStr = "a b c*";
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmSecurity.convertScopeString(localScopeStr);}, 
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Invalid parse state (AFTERENTRY), asterisk found in invalid location", execMsg,
					         "Unexpected error message");
		}
	}
	@Test
	void checkcheckVerificationCode() {  
		/* Run a few checkcheckVerificationCode tests */
		/* We would like to run some tests on the verification code.
		   However, the verification code will change each time. This
		   make testing impossible. */
	}
	@Test
	void getAttributes() {  
		/* Run a few getAttributes tests */
		/* We would like to run some tests on the get attributes code.
		   However, the get attributes code must follow a successful 
		   verification. The verification code will change each time. 
		   This make testing impossible. */
	}
	@Test
	void getHeadersGetAttributes() {
		/* Run a few getHeadersGetAttributes tests */
		int     contentLength = 5;
		String  hostNameStr = "test";
		ArrayList<String>   getAttributeHeaders = HDLmSecurity.getHeadersGetAttributes(hostNameStr, contentLength);
		int     headersLength = getAttributeHeaders.size();
		assertTrue(headersLength > 0, "Size of headers returned by getAttributeHeaders is zero");
		/* Check the error messages from the get attributes headers routine */
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getHeadersGetAttributes(null, 
					                              		                                         3);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Host name string passed to getHeadersGetAttributes is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getHeadersGetAttributes("host", 
					                              		                                         -3);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Content length passed to getHeadersGetAttributes is invalid", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getHeadersInitiateAuth() {
		/* Run a few getHeadersInitiateAuth tests */
		int     contentLength = 5;
		String  hostNameStr = "test";
		ArrayList<String>   getAttributeHeaders = HDLmSecurity.getHeadersInitiateAuth(hostNameStr, contentLength);
		int     headersLength = getAttributeHeaders.size();
		assertTrue(headersLength > 0, "Size of headers returned by getAttributeHeaders is zero");
		/* Check the error messages from the get attributes headers routine */
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getHeadersInitiateAuth(null, 
					                              		                                        3);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Host name string passed to getHeadersInitiateAuth is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getHeadersInitiateAuth("host", 
					                              		                                        -3);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Content length passed to getHeadersInitiateAuth is invalid", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getHeadersRespondToChallenge() {
		/* Run a few getHeadersRespondToChallenge tests */
		int     contentLength = 5;
		String  hostNameStr = "test";
		ArrayList<String>   getAttributeHeaders = HDLmSecurity.getHeadersRespondToChallenge(hostNameStr, contentLength);
		int     headersLength = getAttributeHeaders.size();
		assertTrue(headersLength > 0, "Size of headers returned by getAttributeHeaders is zero");
		/* Check the error messages from the get attributes headers routine */
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getHeadersRespondToChallenge(null, 
					                              		                                              3);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Host name string passed to getHeadersRespondToChallenge is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getHeadersRespondToChallenge("host", 
					                              		                                              -3);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Content length passed to getHeadersRespondToChallenge is invalid", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getHeadersStandard() {
		/* Run a few getHeadersRespondToChallenge tests */
		int     contentLength = 5;
		String  hostNameStr = "test";
		ArrayList<String>   getAttributeHeaders = HDLmSecurity.getHeadersStandard(hostNameStr, contentLength);
		int     headersLength = getAttributeHeaders.size();
		assertTrue(headersLength > 0, "Size of headers returned by getAttributeHeaders is zero");
		/* Check the error messages from the get attributes headers routine */
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getHeadersStandard(null, 
					                              		                                    3);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Host name string passed to getHeadersStandard is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getHeadersStandard("host", 
					                              		                                    -3);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Content length passed to getHeadersStandard is invalid", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void getJsonGetAttributes() {  
		/* Run a few getJsonGetAttributes tests */
		/* We would like to run some tests on the get JSON attributes code.
		   However, the get attributes code must follow a successful 
		   verification. The verification code will change each time. 
		   This make testing impossible. */
	}
	@Test
	void getJsonInitiateAuth() {  
		/* Run a few getJsonInitiateAuth tests */
		String  clientId = "12";
		String  outStr;
		String  passwordStr = "Pentium8023!";
		String  userName = "pschaeffer";
		outStr = HDLmSecurity.getJsonInitiateAuth(clientId, userName, passwordStr);
		assertNotNull(outStr, "Null response recieved from get JSON initiate auth routine");
		/* Check the error messages from the get attributes headers routine */
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getJsonInitiateAuth(null, 
					                              		                                     "test1234",
					                              		                                     "test1234");}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Client ID string passed to getJsonInitiateAuth is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getJsonInitiateAuth("test1234", 
					                              		                                     null,
					                              		                                     "test1234");}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("User name string passed to getJsonInitiateAuth is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.getJsonInitiateAuth("test1234", 
					                              		                                     "test1234",
					                              		                                     null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Password string passed to getJsonInitiateAuth is null", execMsg,
					         "Unexpected exception message");
		}	
	}
	@Test
	void getJsonRespondToChallenge() {  
		/* Run a few getJsonRespondToChallenge tests */
		/* We would like to run some tests on the JSON verification code.
		   However, the verification code will change each time. This
		   make testing impossible. */
	}
	@Test
	void invokeCognitoApi() {  
		/* Run a few invokeCognitoApi tests */
		ArrayList<String>   apiHeaders = null;
		HDLmApacheResponse  outResponse;
		int     apiJsonLen;
		String  apiJson = null;
		String  AWSCognitoHostName = HDLmConfigInfo.getAwsCognitoHost();
		String  clientId = HDLmConfigInfo.getAwsCognitoUserPoolId();
		String  clientAppId = HDLmConfigInfo.getAwsCognitoUserPoolClientAppId();
		String  outStr;
		String  passwordStr = "Pentium8023!";
		String  userName = "pschaeffer";
		/* Get some JSON to pass to the API */
		apiJson = HDLmSecurity.getJsonInitiateAuth(clientAppId, userName, passwordStr);
		/* Get some headers to pass to the API */
		apiJsonLen = apiJson.length();
		apiHeaders = HDLmSecurity.getHeadersInitiateAuth(AWSCognitoHostName, apiJsonLen);
		outResponse = HDLmSecurity.invokeCognitoApi(apiHeaders, apiJson); 
		outStr = outResponse.getStringContent();
		assertNotNull(outStr, "Null response recieved from invokeCognitoApi routine");
	  /* Run another test with invalid information */
		passwordStr = "junk1234";
		userName = "junk1234";
		/* Get some JSON to pass to the API */
		apiJson = HDLmSecurity.getJsonInitiateAuth(clientAppId, userName, passwordStr);
		/* Get some headers to pass to the API */
		apiJsonLen = apiJson.length();
		apiHeaders = HDLmSecurity.getHeadersInitiateAuth(AWSCognitoHostName, apiJsonLen);
		outResponse = HDLmSecurity.invokeCognitoApi(apiHeaders, apiJson);  
		outStr = outResponse.getStringContent();
		assertNotNull(outStr, "Output string is null in invokeCognitoApi");
		/* Check the error messages from the get attributes headers routine */
		{  
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.invokeCognitoApi(null, 		
					                              		                                  "test1234");}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("API headers array passed to invokeCognitoApi is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			ArrayList<String>   testArrayList = new ArrayList<String>();
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSecurity.invokeCognitoApi(testArrayList, 
					                              		                                  null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("API JSON string passed to invokeCognitoApi is null", execMsg,
					         "Unexpected exception message");
		}	
	}
}
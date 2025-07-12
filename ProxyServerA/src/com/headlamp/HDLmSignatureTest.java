package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * HDLmSignatureTest short summary.
 *
 * HDLmSignatureTest description.
 *
 * @version 1.0
 * @author Peter Schaeffer
 */
class HDLmSignatureTest {
  @BeforeAll
  static void HDLmUnReBeforeAll() {
		/* Set some of the configuration values from secrets stored inside
	     AWS. We get the AWS secrets using the AWS secrets manager. Note
	     that some of the secrets are used to establish database connections.
	     As a consequence, this step must come before any database 
	     connections are established. */
	  HDLmConfig.setConfigurationValues();
  }
	@Test
	void build() throws Exception {
		/* Run a few build tests */
		/* Set a few values for use later */
		String  accessKeyID = HDLmConfigInfo.getAccessKeyId();
		String  secretAccessKey = HDLmConfigInfo.getSecretAccessKey(); 
		String  regionName = HDLmConfigInfo.getAwsCognitoUserPoolRegion();
		String  serviceName = HDLmConfigInfo.getAwsCognitoServiceName();
		String  httpMethodName = HDLmConfigInfo.getAwsCognitoHttpMethod();
		String  canonicalUri = HDLmConfigInfo.getAwsCognitoCanonicalUri();
    LinkedHashMap<String, String>  queryParameters = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String>  awsHeaders = new LinkedHashMap<String, String>();
    String  userPoolId = HDLmConfigInfo.getAwsCognitoUserPoolId();
    String  userName = "pschaeffer";
    String  payload = HDLmSecurity.getJsonAdminGetUser(userPoolId, userName);
    String  xAmzTarget = HDLmConfigInfo.getAwsCognitoApiAdminGetUser();
    String  contentType = HDLmConfigInfo.getAwsCognitoContentType();
    String  hostName = HDLmConfigInfo.getAwsCognitoHost();
    String  dateTime = HDLmSecurity.getTimeStamp();
		/* Create a Builder object for use below */
		HDLmSignature.Builder   builderObj = new HDLmSignature.Builder(accessKeyID, secretAccessKey);
		/* Set a few fields in the Builder object */
		builderObj.regionName(regionName);
		builderObj.serviceName(serviceName);
		builderObj.httpMethodName(httpMethodName);
		builderObj.canonicalUri(canonicalUri);
		builderObj.queryParameters(queryParameters);
		builderObj.awsHeaders(awsHeaders);
		builderObj.payload(payload);
		builderObj.xAmzTarget(xAmzTarget);
		builderObj.contentType(contentType);
		builderObj.hostName(hostName);
		builderObj.dateTime(dateTime);
		/* Get an HDLmSignature instance */
		HDLmSignature   hdlmSignature = builderObj.build();
		/* Use the HDLmSignature instance */
		Map<String, String>   headers = null;
		headers = hdlmSignature.getHeaders();
		/* Get another AWS signature using fixed values. This signature
		   won't change over time and can (hence) be checked. */ 
		accessKeyID = HDLmConfigInfo.getAccessKeyId();
		secretAccessKey = HDLmConfigInfo.getSecretAccessKey();    
		regionName = HDLmConfigInfo.getAwsCognitoUserPoolRegion();
		serviceName = "cognito-idp";
		httpMethodName = "POST"; 
		canonicalUri = "/"; 
    queryParameters = new LinkedHashMap<String, String>();
    awsHeaders = new LinkedHashMap<String, String>();
    userPoolId = "us-east-2_xTvIIRtgB";
    userName = "pschaeffer";
    payload = HDLmSecurity.getJsonAdminGetUser(userPoolId, userName);
    xAmzTarget = "AWSCognitoIdentityProviderService.AdminGetUser";
    contentType = "application/x-amz-json-1.1";
    hostName = "cognito-idp.us-east-2.amazonaws.com";
    dateTime = "20230415T121341Z";
		/* Create a Builder object for use below */
		builderObj = new HDLmSignature.Builder(accessKeyID, secretAccessKey);
		/* Set a few fields in the Builder object */
		builderObj.regionName(regionName);
		builderObj.serviceName(serviceName);
		builderObj.httpMethodName(httpMethodName);
		builderObj.canonicalUri(canonicalUri);
		builderObj.queryParameters(queryParameters);
		builderObj.awsHeaders(awsHeaders);
		builderObj.payload(payload);
		builderObj.xAmzTarget(xAmzTarget);
		builderObj.contentType(contentType);
		builderObj.hostName(hostName);
		builderObj.dateTime(dateTime);
		/* Get an HDLmSignature instance */
		hdlmSignature = builderObj.build();
		/* Use the HDLmSignature instance */
		headers = hdlmSignature.getHeaders();
		int   headersLength = headers.size();
		/* Get the authorization header */
		String  authHeader = headers.get("Authorization");
		String  dateHeader = headers.get("x-amz-date");
		assertEquals(2, headersLength, "Length of the headers returned from signature object is not valid");
		/* Build the expected value. The expected value contains the access key ID. 
		   As a consequence the expected value must be constructed. */ 
		String  authValueExpected = "AWS4-HMAC-SHA256 Credential=";
		authValueExpected += accessKeyID;
		authValueExpected += "/20230415/us-east-2/cognito-idp/aws4_request, " + 
		                     "SignedHeaders=content-type;host;x-amz-date;x-amz-target, " + 
				                 "Signature=f24a202b41b280d254c0d88586c4b03fb880199cf8a4659de843894c3ab2964a";
		assertEquals(authValueExpected, authHeader, "Actual authorization header does not match expected value");
		String  dateValueExpected = "20230415T121341Z";
		assertEquals(dateHeader, dateValueExpected, "Actual date header does not match expected value");
	}	
}
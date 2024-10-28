package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmCurlApache short summary.
 *
 * HDLmCurlApache description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmCurlApache {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmCurlApache.class);  
	/* This class can never be instantiated */
	private HDLmCurlApache() {}
  /* This routine does all of the work needed to report a Curl error. 
	   Hopefully, we won't have any Curl errors. However, if we do have
	   Curl errors, this routine will report them. */ 
	protected static String  reportCurlError(String errorMsg, HDLmReportErrors reportErrors) {
		/* Check a few values passed by the caller */
		if (errorMsg == null) {
			String   errorText = "Error message reference passed to reportCurlError is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the report errors enum is null */
		if (reportErrors == null) {
			String   errorText = "Error reporting reference passed to reportCurlError is null";
			throw new NullPointerException(errorText);		
		}
		/* Check the value of the report error enum */
		if (reportErrors != HDLmReportErrors.DONTREPORTERRORS &&
				reportErrors != HDLmReportErrors.REPORTERRORS) {
			String   errorFormat = "Report errors value (%s) passed to reportCurlError is invalid";
			String   errorText = String.format(errorFormat, reportErrors.toString());
			HDLmAssertAction(false, errorText);			
		}
		String   outString = null;
		String   errorText;
	  /* Build the error message */
	  errorText = "Curl request failed - ";
	  /* Add the error message passed by the caller */
	  errorText += errorMsg;
	  /* Report the Curl error */ 
	  if (reportErrors == HDLmReportErrors.REPORTERRORS)
	    outString = HDLmLogMsg.buildLogMsg(HDLmLogLevels.FATAL, "Curl", 1, errorText);  	
	  else 
	 	  outString = errorMsg;
	  return outString;
	}
	/* Run a URL passed by the caller with a userid and password. The 
	   synchronized keyword (below) forces this routine to complete 
	   before another database I/O operation can be started. The 
	   synchronized keyword is no longer used at this time. Other
	   techniques (locks) are used to serialize the database. */
	protected static HDLmApacheResponse  runCurl(String url, 
																               String userid, 
																               String password, 
													                     HDLmHttpTypes type) {
		/* Check a few values passed by the caller */
		if (url == null) {
			String   errorText = "URL reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the userid value, which may not be null. A zero-length
		   string can be passed, but not null. */ 
		if (userid == null) {
			String   errorText = "Userid reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		/* Check the password value, which may not be null. A zero-length
	     string can be passed, but not null. */ 
		if (password == null) {
			String   errorText = "Password reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the type value, which may not be null. The type value
		   must be valid and not equal to NONE. */
		if (type == null) {
			String   errorText = "Type reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		/* The type value must be one of the supported choices */
		if (type != HDLmHttpTypes.GET &&
        type != HDLmHttpTypes.POST) {
			String   errorFormat = "Type value (%s) passed to runCurl is invalid";
			String   errorText = String.format(errorFormat, type.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Create an empty list of headers for use below */
		ArrayList<String>   headerList = new ArrayList<String>();
  	return runCurl(url, 
  			           userid, 
  			           password, 
  			           type,  
  			           headerList,
  			           "",
  			           null,
  			           HDLmOutboundJson.OUTBOUNDJSONYES,
  			           HDLmSkipAuth.SKIPAUTHNO,
  			           HDLmReportErrors.REPORTERRORS);
  }
	/* This is a low-level Curl routine. This routine gives the
	   caller more control over the actual network I/O operation(s). */ 
	protected static HDLmApacheResponse  runCurl(String url, 
			                                         String userid, 
																               String password, 
													  		               HDLmHttpTypes type, 							  		              
													  		               ArrayList<String> headerList,
													  		               String extraInfo,
													  		               HttpEntity passedEntity,
													  		               /* The next set of values are actually types that 
													  		                  have a yes and no value. They are not strings 
													  		                  with data in them. */ 						  		              
													  		               HDLmOutboundJson outboundJson,
													  		               HDLmSkipAuth skipAuth,
													  		               HDLmReportErrors reportErrors) {
		/* Check a few values passed by the caller */
		if (url == null) {
			String   errorText = "URL reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the userid value, which may not be null. A zero-length
		   string can be passed, but not null. */ 
		if (userid == null) {
			String   errorText = "Userid reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		/* Check the password value, which may not be null. A zero-length
	     string can be passed, but not null. */ 
		if (password == null) {
			String   errorText = "Password reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the type value, which may not be null. The type value
		   must be valid and not equal to NONE. */
		if (type == null) {
			String   errorText = "Type reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		/* The type value must be one of the supported choices */
		if (type != HDLmHttpTypes.GET &&
        type != HDLmHttpTypes.POST) {
			String   errorFormat = "Type value (%s) passed to runCurl is invalid";
			String   errorText = String.format(errorFormat, type.toString());
			HDLmAssertAction(false, errorText);			
		}
		if (headerList == null) {
			String   errorText = "Header list reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		/* The extra information is generally a JSON string. However, it is OK 
		   (in some cases), to pass a zero-length string. It is not OK to pass
		   a null value. */ 
		if (extraInfo == null) {
			String   errorText = "Extra information string reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		/* The passed entity is only used in some cases. It is OK to pass a null
		   value for the passed entity value. */
		/*
		if (passedEntity == null) {
			String   errorText = "Passed entity reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		*/
		if (outboundJson == null) {
			String   errorText = "Outbound JSON reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		if (outboundJson != HDLmOutboundJson.OUTBOUNDJSONNO &&
				outboundJson != HDLmOutboundJson.OUTBOUNDJSONYES) {
			String   errorFormat = "Outbound JSON value (%s) passed to runCurl is invalid";
			String   errorText = String.format(errorFormat, outboundJson.toString());
			HDLmAssertAction(false, errorText);			
		}
		if (skipAuth == null) {
			String   errorText = "Skip authorization reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		if (skipAuth != HDLmSkipAuth.SKIPAUTHNO &&
				skipAuth != HDLmSkipAuth.SKIPAUTHYES) {
			String   errorFormat = "Skip authorization value (%s) passed to runCurl is invalid";
			String   errorText = String.format(errorFormat, skipAuth.toString());
			HDLmAssertAction(false, errorText);			
		}
		if (reportErrors == null) {
			String   errorText = "Report errors reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		if (reportErrors != HDLmReportErrors.DONTREPORTERRORS &&
				reportErrors != HDLmReportErrors.REPORTERRORS) {
			String   errorFormat = "Report errors value (%s) passed to runCurl is invalid";
			String   errorText = String.format(errorFormat, reportErrors.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Declare a set of variables needed to run HTTP */
		HDLmApacheResponse     response;
		int                    responseCode;
		String                 responseString = null;
    /* Invoke another routine to do the actual work */
		response = runCurlResponse(url, 
                               userid, 
                               password,
														   type, 							  		              
														   headerList,
														   extraInfo,
														   passedEntity,
														   /* The next set of values are actually types that 
														      have a yes and no value. They are not strings 
														      with data in them. */ 						  		              
														   outboundJson,
														   skipAuth,
														   reportErrors);   
		/* Check if the request was successful */
		responseCode = response.getStatusCode();
		/* Check if we encountered some type of error */
		if (responseCode != HttpStatus.SC_OK) {
	    /* Build the error message */
  		String   errorMsg = String.format("HTTP code (%d) URL (%s)",  responseCode, url);
	    LOG.info("In HDLmApacheResponse.runCurl - " + errorMsg);
  		/* Report the Curl error and return a null value. The caller can
	       check the return value to determine if the request completed
	       or not. */		   
	    HDLmCurlApache.reportCurlError(errorMsg, reportErrors);
		}
		/* It appears that the current operation succeeded. This means 
		   that we can try to obtain the response content from the response
		   instance. Note that this code obtains the response as a string,
		   not an array of bytes. */
		else {
			/* Process the response from the server */
		  responseString = response.getStringContent();
		  response.setStringContent(responseString);
		}		  	
		/* Set a boolean (not a Boolean) based on whether debug logging 
       is enabled or not. This is used to avoid the overhead of
       logging, when debug logging is not enabled. */
    boolean   logIsDebugEnabled = LOG.isDebugEnabled();
		/* Add some debugging information */
		if (logIsDebugEnabled) {
  	/* Log a few value */
	  	LOG.info("Returning response from HDLmCurlApache.runCurl");
		  LOG.info("URL - " + url);
		  LOG.info("Response code - " + ((Integer) response.getStatusCode()).toString());
	  }
		/* Return the final response from the server */
		return response;	
  }
	/* This is a low-level Curl routine. This routine gives the
	   caller more control over the actual network I/O operation(s). 
	   This routine returns a control block with all of the details
	   in it. */ 
	protected static HDLmApacheResponse runCurlResponse(String url, 
													                            String userid, 
																				              String password, 
																	  		              HDLmHttpTypes type, 							  		              
																	  		              ArrayList<String> headerList,
																	  		              String extraInfo,
																	  		              HttpEntity passedEntity,
																	  		              /* The next set of values are actually types that 
																	  		                 have a yes and no value. They are not strings 
																	  		                 with data in them. */ 						  		              
																	  		              HDLmOutboundJson outboundJson,
																	  		              HDLmSkipAuth skipAuth,
																	  		              HDLmReportErrors reportErrors) {
		/* Check a few values passed by the caller */
		if (url == null) {
			String   errorText = "URL reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the userid value, which may not be null. A zero-length
		   string can be passed, but not null. */ 
		if (userid == null) {
			String   errorText = "Userid reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);		
		}
		/* Check the password value, which may not be null. A zero-length
	    string can be passed, but not null. */ 
		if (password == null) {
			String   errorText = "Password reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);			
		}
		/* Check the type value, which may not be null. The type value
		   must be valid and not equal to NONE. */
		if (type == null) {
			String   errorText = "Type reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);		
		}
		/* The type value must be one of the supported choices */
		if (type != HDLmHttpTypes.GET &&
	      type != HDLmHttpTypes.POST) {
			String   errorFormat = "Type value (%s) passed to runCurlResponse is invalid";
			String   errorText = String.format(errorFormat, type.toString());
			HDLmAssertAction(false, errorText);			
		}
		if (headerList == null) {
			String   errorText = "Header list reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);			
		}
		/* The extra information is generally a JSON string. However, it is OK 
		   (in some cases), to pass a zero-length string. It is not OK to pass
		   a null value. */ 
		if (extraInfo == null) {
			String   errorText = "Extra information string reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);			
		}
		/* The passed entity is only used in some cases. It is OK to pass a null
		   value for the passed entity value. */
		/*
		if (passedEntity == null) {
			String   errorText = "Passed entity reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);			
		}
		*/
		if (outboundJson == null) {
			String   errorText = "Outbound JSON reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);		
		}
		if (outboundJson != HDLmOutboundJson.OUTBOUNDJSONNO &&
				outboundJson != HDLmOutboundJson.OUTBOUNDJSONYES) {
			String   errorFormat = "Outbound JSON value (%s) passed to runCurlResponse is invalid";
			String   errorText = String.format(errorFormat, outboundJson.toString());
			HDLmAssertAction(false, errorText);			
		}
		if (skipAuth == null) {
			String   errorText = "Skip authorization reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);		
		}
		if (skipAuth != HDLmSkipAuth.SKIPAUTHNO &&
				skipAuth != HDLmSkipAuth.SKIPAUTHYES) {
			String   errorFormat = "Skip authorization value (%s) passed to runCurlResponse is invalid";
			String   errorText = String.format(errorFormat, skipAuth.toString());
			HDLmAssertAction(false, errorText);			
		}
		if (reportErrors == null) {
			String   errorText = "Report errors reference passed to runCurlResponse is null";
			throw new NullPointerException(errorText);		
		}
		if (reportErrors != HDLmReportErrors.DONTREPORTERRORS &&
				reportErrors != HDLmReportErrors.REPORTERRORS) {
			String   errorFormat = "Report errors value (%s) passed to runCurlResponse is invalid";
			String   errorText = String.format(errorFormat, reportErrors.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Declare a set of variables needed to run HTTP */
		ArrayList<String>      responseHeaders = new ArrayList<String>();
		byte[]                 authEncoded;
		CloseableHttpClient    httpClient = null;
		CloseableHttpResponse  response = null;
		HDLmApacheResponse     apacheResponse;
	  HttpEntity             responseEntity;
		HttpGet                httpGet;
		HttpPost               httpPost;
		int                    responseCode ;
		StatusLine             responseStatusLine;
		String                 authHeader = null;
		String                 authString;
		String                 responseString = null;
		/* Create the Apache Response area. Some of the fields in this 
		   area are set below. */
		apacheResponse = new HDLmApacheResponse();
		/* Save the userid and password. This approach send the basic authorization
		   information with the HTTP request. One network turnaround is eliminated. */
		if (skipAuth == HDLmSkipAuth.SKIPAUTHNO) {
		  authString = userid + ":" + password;
		  authEncoded = Base64.encodeBase64(authString.getBytes(Charset.forName("ISO_8859_1")));
		  authHeader = "Basic " + new String(authEncoded);
		}
		/* Try to run the actual request */
		try {
			/* Build and execute the HTTP request */
	 	  httpClient = HttpClients.createDefault();
	 	  /* Check for a get request */
	 	  if (type == HDLmHttpTypes.GET) {
	   	  httpGet = new HttpGet(url);
	   	  /* Add the authorization header, in some cases */
	   	  if (skipAuth == HDLmSkipAuth.SKIPAUTHNO)
	     	  httpGet.setHeader(HttpHeaders.AUTHORIZATION, authHeader); 
	      /* Add all of the additional headers, if any. The header
	         list may or may not be empty. */
	      for (String header : headerList) {
	     	  int     headerIndex = header.indexOf(' ');
	     	  String  headerName = header.substring(0, headerIndex-1);
	     	  String  headerValue = header.substring(headerIndex+1);
	        httpGet.setHeader(headerName, headerValue);
	      }	
				response = httpClient.execute(httpGet);
	 	  }
	 	  /* Check for a post request */
	 	  else if (type == HDLmHttpTypes.POST) {
	   	  httpPost = new HttpPost(url);
	   	  /* Add the authorization header, in some cases */
	   	  if (skipAuth == HDLmSkipAuth.SKIPAUTHNO)
	   	    httpPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader); 
	   	  /* We can always accept a JSON reply */
	      httpPost.setHeader(HttpHeaders.ACCEPT, "application/json");
	      /* In some (but not all) cases, we are going to send JSON */
	      if (outboundJson == HDLmOutboundJson.OUTBOUNDJSONYES)
	        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");   
	      /* Add all of the additional headers, if any. The header
	         list may or may not be empty. */
	      for (String header : headerList) {
	       	int     headerIndex = header.indexOf(' ');
	     	  String  headerName = header.substring(0, headerIndex-1);
	     	  String  headerValue = header.substring(headerIndex+1);
	        httpPost.setHeader(headerName, headerValue);
	      }	      
	      /* Build and add the string entity if need be */
	      if (passedEntity == null) {
	     	  StringEntity  stringEntity = new StringEntity(extraInfo);
	     	  httpPost.setEntity(stringEntity);
	      }
	      /* Add the entity passed by the caller, if the passed entity
	         is not null */
	      if (passedEntity != null)
	        httpPost.setEntity(passedEntity);      
				response = httpClient.execute(httpPost);
	 	  }
			/* Check if the request was successful */
			responseStatusLine = response.getStatusLine();
			responseCode = responseStatusLine.getStatusCode();
			/* Store some information in the response area */
			apacheResponse.setStatusCode(responseCode);
			apacheResponse.setStatusLine(responseStatusLine.toString());
			/* Handle all of the headers in the response */
			Header[]  responseHeadersArray = response.getAllHeaders();
			for (Header header : responseHeadersArray) {
				String   headerStr = header.toString();
				responseHeaders.add(headerStr);				
			}
			apacheResponse.setHeaders(responseHeaders);
			/* Process the response from the server */
			responseEntity = response.getEntity();
			if (responseEntity != null) {
				responseString = EntityUtils.toString(responseEntity);
				apacheResponse.setStringContent(responseString);
			}
			/* Check if we encountered some type of error */
			if (responseCode != HttpStatus.SC_OK) {
		    /* Build the error message */
	 		  String   errorMsg = String.format("HTTP code (%d) URL (%s)",  responseCode, url);
	 		  LOG.info("In HDLmApacheResponse.runCurlResponse - " + errorMsg);
	 		  /* Report the Curl error and return a null value. The caller can
		       check the return value to determine if the request completed
		       or not. */		   
		    HDLmCurlApache.reportCurlError(errorMsg, reportErrors);
			}
		} 			
		/* Catch any I/O exceptions thrown by the code above. Apparently, the Apache
		   HTTP client will throw exceptions in quite a few cases. */ 
		catch (IOException ioException) {	 
			if (url != null)
			  LOG.info("URL - " + url);
			LOG.info("IOException while executing runCurlResponse");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");	
			String   execMsg = ioException.getMessage();
	    /* Build the error message */
			String   errorMsg = String.format("IOException message (%s) URL (%s)",  execMsg, url);
	    /* Report the Curl error and return a null value. The caller can
	       check the return value to determine if the request completed
	       or not. */		   
			HDLmCurlApache.reportCurlError(errorMsg, reportErrors);
			apacheResponse.setErrorMessage(errorMsg);
			/* This routine used to return a null value. However, that
			   caused problems in the caller. The Apache response with 
			   the error message is now returned. */ 
			/* return null; */
			return apacheResponse;
		}		 
		/* Catch any remaining exceptions thrown by the code above. Apparently, the Apache
	     HTTP client will throw exceptions in quite a few cases. */ 
		catch (Exception exception) {	  
			if (url != null)
			  LOG.info("URL - " + url);
			LOG.info("Exception while executing runCurlResponse");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");	
			String   execMsg = exception.getMessage();
	    /* Build the error message */
			String   errorMsg = String.format("Exception message (%s) URL (%s)",  execMsg, url);
	    /* Report the Curl error and return a null value. The caller can
	       check the return value to determine if the request completed
	       or not. */		   
			HDLmCurlApache.reportCurlError(errorMsg, reportErrors);
			apacheResponse.setErrorMessage(errorMsg);
			/* This routine used to return a null value. However, that
	  	   caused problems in the caller. The Apache response with 
		     the error message is now returned. */ 
			/* return null; */
			return apacheResponse;
		}	
		finally {			 
			try {
				if (response != null)
				  response.close();
				if (httpClient != null)
				  httpClient.close();
			} 
		  catch (IOException subException) { 
		  	if (url != null)
				  LOG.info("URL - " + url);
				LOG.info("IOException while executing runCurlResponse - finally");
		  	LOG.info(subException.getMessage(), subException);
		  	HDLmEvent.eventOccurred("IOException");	
		  	/* Get the message from the exception */
		  	String   execMsg = subException.getMessage();
		    /* Build the error message */
				String   errorMsg = String.format("Exception message (%s) URL (%s)",  execMsg, url);
				/* Store the error message in the Apache response */
				apacheResponse.setErrorMessage(errorMsg);
				/* This routine used to return a null value. However, that
	  	     caused problems in the caller. The Apache response with 
		       the error message is now returned. */ 
			  /* return null; */
			  return apacheResponse;
		  }
		  catch (Exception subException) { 
		  	if (url != null)
				  LOG.info("URL - " + url);
				LOG.info("Exception while executing runCurlResponse - finally");
		  	LOG.info(subException.getMessage(), subException);
		  	HDLmEvent.eventOccurred("Exception");	
		  	/* Get the message from the exception */
		  	String   execMsg = subException.getMessage();
		    /* Build the error message */
				String   errorMsg = String.format("Exception message (%s) URL (%s)",  execMsg, url);
				/* Store the error message in the Apache response */
				apacheResponse.setErrorMessage(errorMsg);
				/* This routine used to return a null value. However, that
	  	     caused problems in the caller. The Apache response with 
		       the error message is now returned. */ 
			  /* return null; */
			  return apacheResponse;
		  }
		}
		/* This code was added for debugging and is not needed for production. This code is not
		   executed under normal circumstances. */
		if (1 == 2) {
	 	  LOG.info("The URL is");
	 	  HDLmUtility.logStringInParts("runCurlResponse", url); 
	 	  LOG.info("The response is");
	 	  if (responseString != null)
	 		  HDLmUtility.logStringInParts("runCurlResponse", responseString);  	
	 	  LOG.info("After the response");
		}
		/* Return the final response from the server or null */
		return apacheResponse;	
	}
}
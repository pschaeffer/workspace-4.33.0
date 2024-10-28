package com.headlamp;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.util.FutureResponseListener;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmCurlJetty short summary.
 *
 * HDLmCurlJetty description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmCurlJetty {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmCurlJetty.class);  
	/* This class can never be instantiated */
	private HDLmCurlJetty() {}
	/* Run a URL passed by the caller with a userid and password. This routine
	   does not appear to be in use, except for some test code that runs it.
	   The Apache routine does appear to be in use. This routine is retained
	   only for historical reasons. */
	protected static String  runCurl(String url, 
			                             String userid, 
			                             String password, 
                                   HDLmHttpTypes type) {
  	return runCurl(url, userid, password, type, "", HDLmReportErrors.REPORTERRORS);
  }
	protected static String  runCurl(String url, 
			                             String userid, 
			                             String password, 
  		                             HDLmHttpTypes type, 
  		                             String extraInfo, 
  		                             HDLmReportErrors reportErrors) {
		if (url == null) {
			String   errorText = "URL reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		if (userid == null) {
			String   errorText = "Userid reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		if (password == null) {
			String   errorText = "Password reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		if (type == null) {
			String   errorText = "Type reference passed to runCurl is null";
			throw new NullPointerException(errorText);		
		}
		if (type != HDLmHttpTypes.GET &&
        type != HDLmHttpTypes.POST) {
			String   errorText = String.format("Type value (%s) passed to runCurl is invalid", type.toString());
			throw new NullPointerException(errorText);			
		}
		if (extraInfo == null) {
			String   errorText = "Extra information reference passed to runCurl is null";
			throw new NullPointerException(errorText);			
		}
		/* Declare a set of variables needed to run HTTP */
		byte[]                 authEncoded;  
		HttpClient             httpClient = null;
		int                    responseCode = 0;
		Request                request;
		Response               response = null;
		String                 authHeader;
		String                 authString;
		String                 responseString = null;
		/* Save the userid and password. This approach send the basic authorization
		   information with the HTTP request. Once network turnaround is eliminated. */
		authString = userid + ":" + password;
		authEncoded = Base64.encodeBase64(authString.getBytes(Charset.forName("ISO_8859_1")));
		authHeader = "Basic " + new String(authEncoded);
		/* Try to run the actual request */
  	try {
  		/* Build and execute the HTTP request. Note that we check for HTTPS 
  		   here. We only need to use HTTPS in some cases. If HTTPS is not in
  		   use, then we don't need to build an SSL context factory. */
  		if (url.startsWith("https")) {
	  		SslContextFactory.Client  sslContextFactoryDotClient = new SslContextFactory.Client();
	  		/* httpClient = new HttpClient(sslContextFactoryDotClient); */
	  		httpClient = new HttpClient();
  		}
  		else
  			httpClient = new HttpClient();
  		/* Start the new HTTP(S) client in all cases */
  		httpClient.start();
    	/* Check for a get request */
    	if (type == HDLmHttpTypes.GET) {
    		request = httpClient.newRequest(url);
    		request.method(HttpMethod.GET);
    		request.header(HttpHeader.AUTHORIZATION, authHeader);
    		FutureResponseListener listener = new FutureResponseListener(request);
    		request.send(listener);
    		response = listener.get();
    		responseCode = response.getStatus();
    		if (responseCode == HttpStatus.OK_200)
    			responseString = listener.getContentAsString();    		
    	}
    	/* Check for a post request */
    	else if (type == HDLmHttpTypes.POST) {
    		request = httpClient.newRequest(url);
    		request.method(HttpMethod.POST);
    		request.header(HttpHeader.AUTHORIZATION, authHeader);
    		request.header(HttpHeader.ACCEPT, "application/json");
    		request.header(HttpHeader.CONTENT_TYPE, "application/json");
    		request.content(new StringContentProvider("application/json", 
    				                                      extraInfo, 
    				                                      StandardCharsets.UTF_8));
    		FutureResponseListener listener = new FutureResponseListener(request);
    		request.send(listener);
    		response = listener.get();
    		responseCode = response.getStatus();
    		if (responseCode == HttpStatus.OK_200)
    			responseString = listener.getContentAsString(); 
    	}
			/* Check if we encountered some type of error */
			if (responseCode != HttpStatus.OK_200) {
		    /* Build the error message */
	  		String   errorMsg = String.format("HTTP code (%d) URL (%s)",  responseCode, url);
		    /* Report the Curl error and return a null value. The caller can
		       check the return value to determine if the request completed
		       or not. */		   
		    HDLmCurlJetty.reportCurlError(errorMsg, reportErrors);
			}
		} 
  	/* Catch any I/O exceptions thrown by the code above. Apparently, the 
  	   Jetty HTTP client will throw exceptions in quite a few cases. */ 
  	catch (IOException ioException) {
  		if (url != null)
			  LOG.info("URL - " + url);
			LOG.info("IOException while executing runCurl");
  		LOG.info(ioException.getMessage(), ioException);
  		HDLmEvent.eventOccurred("IOException");	
  		String   execMsg = ioException.getMessage();
	    /* Build the error message */
  		String   errorMsg = String.format("IOException message (%s) URL (%s)",  execMsg, url);
	    /* Report the Curl error and return a null value. The caller can
	       check the return value to determine if the request completed
	       or not. */		   
  		HDLmCurlJetty.reportCurlError(errorMsg, reportErrors);
  		return null;
  	}		 
  	/* Catch any remaining exceptions thrown by the code above. Apparently, the   
	     Jetty HTTP client will throw exceptions in quite a few cases. */ 
  	catch (Exception exception) {	  		
  		if (url != null)
			  LOG.info("URL - " + url);
			LOG.info("Exception while executing runCurl");
  		LOG.info(exception.getMessage(), exception);
  		HDLmEvent.eventOccurred("Exception");	
  		String   execMsg = exception.getMessage();
	    /* Build the error message */
  		String   errorMsg = String.format("Exception message (%s) URL (%s)",  execMsg, url);
	    /* Report the Curl error and return a null value. The caller can
	       check the return value to determine if the request completed
	       or not. */		   
  		HDLmCurlJetty.reportCurlError(errorMsg, reportErrors);
  		return null;
  	}	
		finally {			 
			try {
				if (httpClient != null)
				  httpClient.stop();
			} 
		  catch (IOException ioException) { 
		  	if (url != null)
				  LOG.info("URL - " + url);
				LOG.info("IOException while executing runCurl - finally");
		  	LOG.info(ioException.getMessage(), ioException);
		  	HDLmEvent.eventOccurred("IOException");	
		  	return null;
		  } 
			catch (Exception subException) { 	
				if (url != null)
				  LOG.info("URL - " + url);
				LOG.info("Exception while executing runCurl - finally");
				LOG.info(subException.getMessage(), subException);
				HDLmEvent.eventOccurred("Exception");	
				return null;
			}
		}
  	/* Return the final response from the server or null */
  	return responseString;	
  }
  /* This routine does all of the work needed to report a Curl error. 
     Hopefully, we won't have any Curl errors. However, if we do have
     Curl errors, this routine will report them. */ 
  protected static String reportCurlError(String errorMsg, HDLmReportErrors reportErrors) {
		if (errorMsg == null) {
			String   errorText = "Error message reference passed to reportCurlError is null";
			throw new NullPointerException(errorText);		
		}
		if (reportErrors == null) {
			String   errorText = "Error reporting reference passed to reportCurlError is null";
			throw new NullPointerException(errorText);		
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
}
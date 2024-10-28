package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.eclipse.jetty.io.EofException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmApache short summary.
 *
 * HDLmApache description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmApache {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmApache.class);  
	/* This class can never be instantiated */
	private HDLmApache() {}
	/* This routine looks for any HTTP header in a list of headers. 
	   The caller specifies what header we should be looking for.
	   This routine returns the first matching header (if one is
	   found). If the HTTP header is found, the index is returned 
	   to the caller. If the HTTP header is not found, then a negative 
	   number is returned to the caller. Note that this routine, 
	   returns an index, not a position. That means that zero, is 
	   a valid return value. */ 
	protected static int findHeader(ArrayList<String> headers, 
			                            String targetHeader) {
		/* Check if the HTTP host headers passed by the caller are null */
		if (headers == null) {
			String  errorText = "HTTP headers array passed to findHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP target header passed by the caller is null */
		if (targetHeader == null) {
			String  errorText = "HTTP target header passed to findHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP headers array passed by the caller is empty */
		if (headers.isEmpty() == true) {
			String  errorText = "HTTP headers array passed to findHeader is empty";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the HTTP target header passed by the caller is empty */
		if (targetHeader.isEmpty() == true) {
			String  errorText = "HTTP target header passed to findHeader is empty";
			throw new IllegalArgumentException(errorText);
		}
		/* Set a few variables for scanning the headers */
		int  headerCounter = 0;
		int  headerIndex = -1;
		String  targetHeaderLower = targetHeader.toLowerCase();
		targetHeaderLower += ':';
		/* Look for the specific HTTP header */
		for (String header : headers) {
			String  headerLower = header.toLowerCase();
			if (headerLower.startsWith(targetHeaderLower)) {
				headerIndex = headerCounter;
				break;				
			}
			headerCounter++;			
		}
		return headerIndex;		
	}
	/* This routine looks for the HTTP host name header in a list of
 	   headers. If the HTTP host name header is found, the index is 
	   returned to the caller. If the HTTP host name header is not
	   found, then a negative number is returned to the caller. Note
	   that this routine, returns an index, not a position. That means
	   that zero, is a valid return value. */ 
	protected static int findHostHeader(ArrayList<String> headers) {
		/* Check if the HTTP headers array passed by the caller is null */
		if (headers == null) {
			String  errorText = "HTTP headers array passed to findHostHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few variables for scanning the headers */
		int  headerCounter = 0;
		int  hostIndex = -1;
		/* Look for the HTTP host name header */
		for (String header : headers) {
			String  headerLower = header.toLowerCase();
			if (headerLower.startsWith("host:")) {
				hostIndex = headerCounter;
				break;				
			}
			headerCounter++;			
		}
		return hostIndex;		
	}
	/* This routine looks for the HTTP location header in a list of
     headers. If the HTTP location header is found, the index is 
     returned to the caller. If the HTTP location header is not
     found, then a negative number is returned to the caller. Note
     that this routine, returns an index, not a position. That means
     that zero, is a valid return value. */ 
  protected static int findLocationHeader(ArrayList<String> headers) {
		/* Check if the HTTP headers array passed by the caller is null */
		if (headers == null) {
			String  errorText = "HTTP headers array passed to findLocationHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few variables for scanning the headers */
		int  headerCounter = 0;
		int  locationIndex = -1;
		/* Look for the HTTP location header */
		for (String header : headers) {
			String  headerLower = header.toLowerCase();
			if (headerLower.startsWith("location:")) {
				locationIndex = headerCounter;
				break;				
			}
			headerCounter++;			
		}
		return locationIndex;		
	}
	/* This routine looks for the HTTP referer (a misspelling of
	   referrer) header in a list of headers. If the HTTP referer
	   header is found, the index is returned to the caller. If
	   the HTTP referer header is not found, then a negative number 
	   is returned to the caller. Note that this routine, returns
	   an index, not a position. That means that zero, is a valid
	   return value. */ 
	protected static int findReferrerHeader(ArrayList<String> headers) {
		/* Check if the HTTP headers array passed by the caller is null */
		if (headers == null) {
			String  errorText = "HTTP headers array passed to findReferrerHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few variables for scanning the headers */
		int  headerCounter = 0;
		int  referrerIndex = -1;
		/* Look for the HTTP referer (a misspelling of referrer) header */
		for (String header : headers) {
			String  headerLower = header.toLowerCase();
			if (headerLower.startsWith("referer:")) {
				referrerIndex = headerCounter;
				break;				
			}
			headerCounter++;			
		}
		return referrerIndex;		
	}
	/* This routine fixes the host name in a HTTP host name header. This
     step is required so that the correct host name is sent to the 
     actual server (not the proxy server). The caller provides the
     header and the new host name. The corrected HTTP header is returned
     to the caller. */
	protected static String fixHostName(String hostHeader, String newHostName) {
		/* Check if the HTTP host header passed by the caller is null */
		if (hostHeader == null) {
			String  errorText = "HTTP Host header passed to fixHostName is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new host name passed by the caller is null */
		if (newHostName == null) {
			String  errorText = "New host name passed to fixHostName is null";
			throw new NullPointerException(errorText);
		}
		StringBuilder  newHostHeader = new StringBuilder();
		/* Break the HTTP host header down into a set of tokens */
		ArrayList<HDLmToken>   tokens = HDLmString.getTokens(hostHeader);
		int                    tokenCount = tokens.size();
		/* Get rid of the sentinel token, if possible */
		if (tokenCount > 0)
			tokenCount--;
		int              tokenIndex;
		HDLmToken        token;
		HDLmTokenTypes   tokenType;
		/* The first token must be a very specific string */
		tokenIndex = 0;
		token = tokens.get(tokenIndex);
		String     tokenValue = token.getValue();
		if (!tokenValue.equalsIgnoreCase("Host")) {
    	String   errorFormat = "Host prefix not found in HTTP header (%s)";
			String   errorText = String.format(errorFormat, tokenValue);
			HDLmAssertAction(false, errorText);		    	
    }
		newHostHeader.append(tokenValue);
		tokenIndex++;
		/* The second token must be a very specific character */
		token = tokens.get(tokenIndex);
		tokenValue = token.getValue();
		if (!tokenValue.equals(":")) {
    	String   errorFormat = "Colon after Host prefix not found in HTTP header (%s)";
			String   errorText = String.format(errorFormat, tokenValue);
			HDLmAssertAction(false, errorText);		    	
    }
		newHostHeader.append(tokenValue);
		tokenIndex++;
		/* The third token might be one or more blanks. If this is true,
		   then we need to use the value of the third token. */
		token = tokens.get(tokenIndex);
		tokenValue = token.getValue();
		tokenType = token.getType();
		if (tokenType == HDLmTokenTypes.SPACE) {			
			newHostHeader.append(tokenValue);
			tokenIndex++;
		}
		/* We can now add the new host name to the output string */
		newHostHeader.append(newHostName);
		/* We now need to skip all of the input HTTP Host header tokens
		   until we reach a comma or we have no more tokens */
		while (tokenIndex < tokenCount) {
			token = tokens.get(tokenIndex);
			tokenValue = token.getValue();
			if (tokenValue.equals(",")) 
				break;				
			tokenIndex++;			
		}
		/* We can now add all of the remaining tokens (if any) from
		   the input HTTP headers to the new output HTTP header */		 
		while (tokenIndex < tokenCount) {
			token = tokens.get(tokenIndex);
			tokenValue = token.getValue();
			newHostHeader.append(tokenValue);				
			tokenIndex++;			
		}		
		return newHostHeader.toString();
	}	
	/* This routine fixes the location in a HTTP location header. This
     step is required so that the correct location is returned to the 
     actual browser client (not the proxy server). The caller provides 
     the location header and the old/new host names. The corrected HTTP
     header is returned to the caller. */
	protected static String fixLocationName(String locationHeader, 
			                                    String oldHostName,
			                                    String newHostName) {
		/* Check if the HTTP location header passed by the caller is null */
		if (locationHeader == null) {
			String  errorText = "HTTP Location header passed to fixLocationName is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the old host name passed by the caller is null */
		if (oldHostName == null) {
			String  errorText = "Old host name passed to fixLocationName is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new host name passed by the caller is null */
		if (newHostName == null) {
			String  errorText = "New host name passed to fixLocationName is null";
			throw new NullPointerException(errorText);
		}
		/* The replacement process must use the base host names, not the host names 
    	 with a 'www.' prefix. The reason is that many location names use a host
    	 name with a 'www.' prefix. Of course, location names should not have a
    	 host name in them (generally) at all. However, many do anyway. */ 
		String  oldHostNameNoPrefix = HDLmString.removePrefix("www.", oldHostName);
		String  newHostNameNoPrefix = HDLmString.removePrefix("www.", newHostName);
	  locationHeader = locationHeader.replace(oldHostNameNoPrefix, newHostNameNoPrefix);
		return locationHeader;
	}	
	/* Dump all of the headers for an Apache request */
	protected static void dumpAllHeaders(HttpMessage request) {
		/* Check if the Apache request passed by the caller is null */
		if (request == null) {
			String  errorText = "Apache request passed to dumpAllHeaders is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<Header>  allHeaders = HDLmApache.getAllHeaders(request);
		for (Header header : allHeaders) {
			LOG.info(header.toString());
		}		
	}
	/* Get all of the headers for an Apache request. The array list of
	   headers is returned to the caller. Of course, the array list may
	   be empty (but is probably not empty). */
	protected static ArrayList<Header> getAllHeaders(HttpMessage request) {
		/* Check if the Apache request passed by the caller is null */
		if (request == null) {
			String  errorText = "Apache request passed to getAllHeaders is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<Header>  allHeaders = new ArrayList<Header>();
		Header[]           headersArray;
		headersArray = request.getAllHeaders();
		for (Header header : headersArray) {
			allHeaders.add(header);		
		}		
		return allHeaders;
	}
	/* The next routine handles one HTTP(S) request. This routine checks for
	   302 HTTP codes and follows location headers as need be. */ 
	protected static HDLmApacheResponse performHttpOperation(HDLmProtocolTypes protocolType, 
			                                                     HDLmHttpTypes operationType, 
			                                                     String hostName, 
			                                                     String pathValue,
			                                                     ArrayList<String> inputHeaders,
			                                                     byte[] inputContentBytes,
			                                                     HDLmApacheRedirect redirect,
			                                                     HDLmShowRequest showRequest,
			                                                     HDLmShowResponse showResponse) throws ClientProtocolException, IOException {
		/* Check if the protocol type enum value passed by the caller is null */
		if (protocolType == null) {
			String  errorText = "Protocol type enum value passed by the caller to performHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the protocol type is supported */
		if (protocolType != HDLmProtocolTypes.HTTP &&
        protocolType != HDLmProtocolTypes.HTTPS) {
			String   errorFormat = "Protocol type value (%s) passed to performHttpOperation is invalid";
			String   errorText = String.format(errorFormat, protocolType.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the operation type enum value passed by the caller is null */
		if (operationType == null) {
			String  errorText = "Operation type enum value passed by the caller to performHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the operation type is supported */
		if (operationType != HDLmHttpTypes.GET     &&
				operationType != HDLmHttpTypes.HEAD    &&				
        operationType != HDLmHttpTypes.POST    &&
        operationType != HDLmHttpTypes.PUT     &&
        operationType != HDLmHttpTypes.DELETE  &&
        operationType != HDLmHttpTypes.TRACE   &&
        operationType != HDLmHttpTypes.OPTIONS &&
        operationType != HDLmHttpTypes.PATCH) {
			String   errorFormat = "Operation type value (%s) passed to performHttpOperation is invalid";
			String   errorText = String.format(errorFormat, operationType.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the host name string by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to performHttpOperation is null";
			throw new NullPointerException(errorText);
		}		
		/* Check the path string (if any) passed by the caller. The path 
		   string can be null. If the path string is not null, it must 
		   start with a leading forward slash operation. */
		if (pathValue != null) {
			if (pathValue.length() == 0) {
				String   errorText = "Empty path string passed to performHttpOperation";
				HDLmAssertAction(false, errorText);	
			}
			if (pathValue.charAt(0) != '/') {
				String   errorText = "Leading forward slash missing from path string passed to performHttpOperation";
				HDLmAssertAction(false, errorText);					
			}			
		}		
		/* Check if the redirect enum value passed by the caller is null */
		if (redirect == null) {
			String  errorText = "Redirect enum value passed by the caller to performHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the redirect enum value is supported */
		if (redirect != HDLmApacheRedirect.DISABLE &&
        redirect != HDLmApacheRedirect.LAX) {
			String   errorFormat = "Redirect enum value (%s) passed to performHttpOperation is invalid";
			String   errorText = String.format(errorFormat, redirect.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the show request enum value passed by the caller is null */
		if (showRequest == null) {
			String  errorText = "Show request enum value passed by the caller to performHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the show request enum value is supported */
		if (showRequest != HDLmShowRequest.SHOWREQUESTOK &&
				showRequest != HDLmShowRequest.SHOWREQUESTNOTOK) {
			String   errorFormat = "Show request enum value (%s) passed to performHttpOperation is invalid";
			String   errorText = String.format(errorFormat, redirect.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the show response enum value passed by the caller is null */
		if (showResponse == null) {
			String  errorText = "Show response enum value passed by the caller to performHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the show response enum value is supported */
		if (showResponse != HDLmShowResponse.SHOWRESPONSEOK &&
				showResponse != HDLmShowResponse.SHOWRESPONSENOTOK) {
			String   errorFormat = "Show response enum value (%s) passed to performHttpOperation is invalid";
			String   errorText = String.format(errorFormat, redirect.toString());
			HDLmAssertAction(false, errorText);			
		}	
		/* Declare and define a variable that can be used to return the results
		   of the current Apache operation to the caller */ 
		HDLmApacheResponse   httpResponse = null;
		int                  loopCounter = 0;
		int                  loopCounterLimit = HDLmConfigInfo.getFollowLocation();
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Increment the loop counter and check if this loop has executed
			   too much */
			loopCounter++;
			if (loopCounter > loopCounterLimit) {
				String   errorFormat = "Follow location limit exceeded for host name (%s) path (%s)";
				String   errorText = String.format(errorFormat, hostName, pathValue);
				HDLmAssertAction(false, errorText);						
			}
			/* Display some information about the current request, if we are requested to do so.
			   The code below is intended only for trace purposes. The URL string is not otherwise
			   used. */
			/* showRequest = HDLmShowRequest.SHOWREQUESTOK; */
			/* showResponse = HDLmShowResponse.SHOWRESPONSEOK; */
			if (showRequest == HDLmShowRequest.SHOWREQUESTOK) {
				/* Build the URL string from the values passed by the caller and
				   values that may be changed here */
				String   urlString = protocolType.toString().toLowerCase();
				urlString += "://";
				urlString += hostName;
				if (pathValue != null)
					urlString += pathValue;
				else
					urlString += '/';
				LOG.info("PerformHttpOperation - InsideWhileLoop"); 
				LOG.info("ProtocolType  " + String.valueOf(protocolType));
				LOG.info("OperationType " + String.valueOf(operationType));
				LOG.info("HostName      " + hostName);
				LOG.info("Path          " + pathValue);	 	
				LOG.info("UrlString     " + urlString);
			}
		  /* Run the current Apache request */
		  httpResponse =  HDLmApache.runHttpOperation(protocolType, operationType, hostName, 
								                                  pathValue, inputHeaders, inputContentBytes,
								                                  redirect, showRequest, showResponse); 
		  /* Check if we didn't get a HTTP response back. This should never happen,
		     but you never know. */
		  if (httpResponse == null)
		  	break;
			/* Get the response status code */
			int   responseStatus = httpResponse.getStatusCode();	
			/* Display the response status code, if we are requested to do so.
			   The response status code is always a simple integer and is 
			   better known as the HTTP status code. */ 
			if (showResponse == HDLmShowResponse.SHOWRESPONSEOK) 
				LOG.info("PerformHttpOperation - InsideWhileLoop - " + String.valueOf(responseStatus));			
			/* Check the response status */
   		if (responseStatus != HttpStatus.SC_MOVED_TEMPORARILY)
		    break;
			/* Get the response headers, if any */
			ArrayList<String>   responseHeaders = httpResponse.getHeaders();
			if (responseHeaders == null)
				break;
			/* We can now follow the location to a new host */
		  String  locationValue = HDLmJetty.getHeaderValue(responseHeaders, "Location");
		  /* The location value may or may not start with a forward slash. We 
		     need to add a forward slash in some cases. */
		  if (locationValue != null      &&
		  		locationValue.length() > 0 &&
		  		locationValue.charAt(0) != '/')
		  	locationValue = "/" + locationValue;		   
		  /* LOG.info("LocationValue     " + locationValue); */
			String 	locationFullPath = HDLmUtility.getFullPathString(locationValue);
			/* LOG.info("LocationFullPath  " + locationFullPath); */
			/* At this point we may or may not want to get the host name from the
			   Location (not case) header. The Location header may or may not have
			   a host name. If the location header does not have a host name, then
			   we don't want to try to get a host name from it. */
			String	locationHostName = null; 	
			if (HDLmUtility.checkForHostNameAfterProtocol(locationValue))		
			  locationHostName = HDLmUtility.getHostNameFromUrl(locationValue);
			else {
				/* LOG.info("Using the host name in performHttpOperation"); */
				locationHostName = hostName;
			}	
			/* Display some information about the new location, if we are requested to do so */
			if (showResponse == HDLmShowResponse.SHOWRESPONSEOK) {
				LOG.info("HeaderLocation   " + locationValue);		
				LOG.info("LocationHostName " + locationHostName);
				LOG.info("LocationFullPath " + locationFullPath);
			}
			/* Search for the host header */
			int   hostHeaderIndex = HDLmApache.findHostHeader(inputHeaders);
			if (hostHeaderIndex >= 0) {
				/* Get the old host header from the input headers */			 
			  String  oldHostHeader = inputHeaders.get(hostHeaderIndex);
			  if (showResponse == HDLmShowResponse.SHOWRESPONSEOK) {
					LOG.info("FixHostName OldHostHeader   " + oldHostHeader);
					LOG.info("FixHostName hostName        " + hostName);
					LOG.info("FixHostName locationHostName" + locationHostName);
			  }
			  /* Build the new host header */
				String newHostHeader = HDLmApache.fixLocationName(oldHostHeader, 
					                                                hostName,
							                                            locationHostName);
				inputHeaders.set(hostHeaderIndex, newHostHeader);
				/* Display the new host header, if we are requested to do so */
				if (showResponse == HDLmShowResponse.SHOWRESPONSEOK)
					LOG.info("FixHostName newHostHeader   " + newHostHeader);					
		  }	
			/* Reset the host name and the path */
			hostName = locationHostName;
			pathValue = locationFullPath;
		}					                                    
		/* Return the response instance with all of the output values */		
		return httpResponse;
	}	
	/* The next routine handles one HTTP(S) request. The caller provides
	   the HTTP headers and the content to be transmitted (if any). The 
	   response is returned to the caller. The caller must specify the
	   protocol type to be used and the type of HTTP(S) operation. The
	   caller must also specify the target host name. Note that if the 
 	   caller passes headers, they must already be updated with the
 	   correct host name. 
 	  
 	   Indeed, this routine will not alter any of the headers passed by
	   the caller (if they are passed). The caller must make any changes
	   that are needed before this routine is caller. 
	  
	   If the caller passes a non-null path value, it must start with a 
	   leading forward slash. Of course, the path value can be null. The 
	   path value passed by the caller, should including the query string
	   (if any) and the fragment (if any).
	  
	   The content (if any) passed by the caller must be an array of bytes
	   (not characters). The caller must handle any encoding operations 
	   before this routine is called.
	  
	   The content (if any) returned by this routine will be an array of
	   bytes (not characters) The caller must handling any decoding 
	   operations needed for the data. 
	  
	   Note that content is only passed to the server for HTTP POST, PUT, 
	   and PATCH operations. This implementation does not follow the 
	   specification exactly. Content for the server is only banned for
	   TRACE operations. However, we do not pass the content in many 
	   other cases. 
	   
	   Note that the specification includes support for HTTP CONNECT
	   operations. However, it appears that Apache does not support
	   HTTP CONNECT operations. Hopefully, this will not be a problem. */ 
	protected static HDLmApacheResponse processHttpOperation(HDLmProtocolTypes protocolType, 
			                                                     HDLmHttpTypes operationType, 
			                                                     String hostName, 
			                                                     String pathValue,
			                                                     ArrayList<String> inputHeaders,
			                                                     byte[] inputContentBytes,
			                                                     HDLmApacheRedirect redirect) throws ClientProtocolException, IOException {
		/* Check if the protocol type enum value passed by the caller is null */
		if (protocolType == null) {
			String  errorText = "Protocol type enum value passed by the caller to processHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the protocol type is supported */
		if (protocolType != HDLmProtocolTypes.HTTP &&
	      protocolType != HDLmProtocolTypes.HTTPS) {
			String   errorFormat = "Protocol type value (%s) passed to processHttpOperation is invalid";
			String   errorText = String.format(errorFormat, protocolType.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the operation type enum value passed by the caller is null */
		if (operationType == null) {
			String  errorText = "Operation type enum value passed by the caller to processHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the operation type is supported */
		if (operationType != HDLmHttpTypes.GET     &&
				operationType != HDLmHttpTypes.HEAD    &&				
	      operationType != HDLmHttpTypes.POST    &&
	      operationType != HDLmHttpTypes.PUT     &&
	      operationType != HDLmHttpTypes.DELETE  &&
	      operationType != HDLmHttpTypes.TRACE   &&
	      operationType != HDLmHttpTypes.OPTIONS &&
	      operationType != HDLmHttpTypes.PATCH) {
			String   errorFormat = "Operation type value (%s) passed to processHttpOperation is invalid";
			String   errorText = String.format(errorFormat, operationType.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the host name string by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to processHttpOperation is null";
			throw new NullPointerException(errorText);
		}		
		/* Check the path string (if any) passed by the caller. The path 
		   string can be null. If the path string is not null, it must
		   start with a leading forward slash operation. */
		if (pathValue != null) {
			if (pathValue.length() == 0) {
				String   errorText = "Empty path string passed to processHttpOperation";
				HDLmAssertAction(false, errorText);	
			}
			if (pathValue.charAt(0) != '/') {
				String   errorText = "Leading forward slash missing from path string passed to processHttpOperation";
				HDLmAssertAction(false, errorText);					
			}			
		}		
		/* Check if the redirect enum value passed by the caller is null */
		if (redirect == null) {
			String  errorText = "Redirect enum value passed by the caller to processHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the redirect enum value is supported */
		if (redirect != HDLmApacheRedirect.DISABLE &&
	      redirect != HDLmApacheRedirect.LAX) {
			String   errorFormat = "Redirect enum value (%s) passed to processHttpOperation is invalid";
			String   errorText = String.format(errorFormat, redirect.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Build the instance used to return the results of this Apache operation
		   to the caller */
		HDLmApacheResponse   apacheResponse = new HDLmApacheResponse();
		/* Build the URL string from the values passed by the caller */
		String   urlString = protocolType.toString().toLowerCase();
		urlString += "://";
		urlString += hostName;
		if (pathValue != null)
			urlString += pathValue;
		else
			urlString += '/';
		/* Build a few variables for use below */
		ArrayList<String>      responseHeaders = new ArrayList<String>();
		byte[]                 outputBytes = null;
		ByteArrayEntity        inputByteEntity = null;
		CloseableHttpClient    closeableHttpClient = null;
		CloseableHttpResponse  closeableResponse = null;
		HttpDelete             httpDelete;
		HttpEntity             outputEntity;
		HttpGet                httpGet;
		HttpHead               httpHead;		
		HttpOptions            httpOptions;
		HttpPatch              httpPatch;
		HttpPost               httpPost;
		HttpPut                httpPut;
		HttpTrace              httpTrace;
		InputStream            outputStream = null; 
		int                    responseCode ;
		StatusLine             responseStatusLine;		
		/* Try to run the actual request */
		try {
			/* Check how redirects should be handled */
			if (redirect == HDLmApacheRedirect.DISABLE) {
	 		/* Build and execute the HTTP request. Note that the builder used below
	 		   disables default redirect handling. We want redirects to flow back to
	 		   the client so that the client can handle them. */
				closeableHttpClient = HttpClientBuilder.create().disableRedirectHandling().build();
			}
			else {
	 		/* Build and execute the HTTP request. Note that the builder used below
		       does not disable default redirect handling. The LAX redirect strategy
		       is enabled instead. */
				closeableHttpClient = HttpClientBuilder.create().setRedirectStrategy(LaxRedirectStrategy.INSTANCE).build(); 
			}
			/* The call below was used to build the Apache HTTP client at one time.
			   This call built a default HTTP client handler that did redirects 
			   internally. This caused problems.   		   
			   httpClient = HttpClients.createDefault(); */
	 	  if (inputContentBytes != null)
	        inputByteEntity = new ByteArrayEntity(inputContentBytes); 
	 	  /* Check for each type of request */
	 	  switch (operationType) {
	 	    case GET: {
	 	  	  httpGet = new HttpGet(urlString);    	  	
	 	      HDLmApache.setHeadersNull(httpGet, inputHeaders);
	 	      closeableResponse = closeableHttpClient.execute(httpGet);
					break;
	 	    }
	 	    case HEAD: {
	 	  	  httpHead = new HttpHead(urlString);
	 	  	  HDLmApache.setHeadersNull(httpHead, inputHeaders);
	 	  	  closeableResponse = closeableHttpClient.execute(httpHead);
					break;
	 	    }
	 	    case POST: {
	 	    	httpPost = new HttpPost(urlString);
	 	  	  HDLmApache.setHeadersNull(httpPost, inputHeaders);
	 	  	  /* For some reason, the Apache code gets really upset if it
	 	  	     finds a header (HTTP header) with a content length value
	 	  	     of zero (or non-zero). Why is not clear. The call below
	 	  	     removes the offending header from the Apache request. */
	 	  	  if (inputByteEntity != null)
	 	  	    httpPost.setEntity(inputByteEntity);
	 	  	  HDLmApache.removeContentLength(httpPost);
	 	  	  closeableResponse = closeableHttpClient.execute(httpPost);
					break;
	 	    }
	 	    case PUT: {
	 	  	  httpPut = new HttpPut(urlString);
	 	  	  HDLmApache.setHeadersNull(httpPut, inputHeaders);
	 	  	  if (inputByteEntity != null)
	 	  	    httpPut.setEntity(inputByteEntity);
	 	  	  closeableResponse = closeableHttpClient.execute(httpPut);
					break;
	 	    }
	 	    case DELETE: {
	 	  	  httpDelete = new HttpDelete(urlString);
	 	  	  HDLmApache.setHeadersNull(httpDelete, inputHeaders);
	 	  	  closeableResponse = closeableHttpClient.execute(httpDelete);
					break;
	 	    }
	 	    case TRACE: {
	 	  	  httpTrace = new HttpTrace(urlString);
	 	  	  HDLmApache.setHeadersNull(httpTrace, inputHeaders);
	 	  	  closeableResponse = closeableHttpClient.execute(httpTrace);
					break;
	 	    }
	 	    case OPTIONS: {
	 	  	  httpOptions = new HttpOptions(urlString);
	 	  	  HDLmApache.setHeadersNull(httpOptions, inputHeaders);
	 	  	  closeableResponse = closeableHttpClient.execute(httpOptions);
	 	    }
	 	    case PATCH: {
	 	  	  httpPatch = new HttpPatch(urlString);
	 	  	  HDLmApache.setHeadersNull(httpPatch, inputHeaders);
	 	  	  if (inputByteEntity != null)
	 	  	    httpPatch.setEntity(inputByteEntity);
	 	  	  closeableResponse = closeableHttpClient.execute(httpPatch);
					break;
	 	    }
			  /* Report an error if the HTTP request type did not match one of the expected choices */
		    default: 
		      String   errorFormat = "Invalid HTTP request type (\"%s\") passed to this routine";
		      String   errorText = String.format(errorFormat, operationType.toString());
		      HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Invalid HTTP Request", 32, errorText);  
	 	  }
			/* Check if the request was successful */
			responseStatusLine = closeableResponse.getStatusLine();
			responseCode = responseStatusLine.getStatusCode();
			apacheResponse.setStatusCode(responseCode);
			/* Handle all of the headers in the response */
			Header[] responseHeadersArray = closeableResponse.getAllHeaders();
			for (Header header : responseHeadersArray) {
				String   headerStr = header.toString();
				responseHeaders.add(headerStr);				
			}
			apacheResponse.setHeaders(responseHeaders);
			/* Handle the output content (if any). Check for a special status
			   code that shows that we recieved no output content. */
			if (responseCode != HttpStatus.SC_NO_CONTENT) {
				outputEntity = closeableResponse.getEntity();
				if (outputEntity != null) {
			    outputStream = outputEntity.getContent();
				    outputBytes = outputStream.readAllBytes();
				  outputStream.close();
				  outputStream = null;
				  apacheResponse.setByteContent(outputBytes);		
				}
			}
		}	
	  /* Catch any Eof exceptions (note case) thrown by the code above. Apparently, 
	     the Apache HTTP client will throw exceptions in quite a few cases. */ 
	  catch (EofException eofException) {	
	 	  if (pathValue != null)
		    LOG.info("Path - " + pathValue);
		  LOG.info("EOFException while executing processHttpOperation");
		  LOG.info(eofException.getMessage(), eofException);
	    HDLmEvent.eventOccurred("EofException");		
	 	  String  EOFExecMsg = eofException.getMessage();
	    /* Build the error message */
	  	String   errorMsg = String.format("EOFException message (%s) URL (%s)",  
	  			                              EOFExecMsg, 
				                                urlString);
	  	apacheResponse.setErrorMessage(errorMsg);
	  	return null;
	  }
	  /* Catch any EOF exceptions (note case)( thrown by the code above. Apparently, 
	     the Apache HTTP client will throw exceptions in quite a few cases. */ 
	  catch (EOFException eofException) {	
	  	if (pathValue != null)
		    LOG.info("Path - " + pathValue);
		  LOG.info("EOFException while executing processHttpOperation");
		  LOG.info(eofException.getMessage(), eofException);
	    HDLmEvent.eventOccurred("EOFException");		
	  	String  EOFExecMsg = eofException.getMessage();
	    /* Build the error message */
	   	String   errorMsg = String.format("EOFException message (%s) URL (%s)",  
	   			                              EOFExecMsg, 
				                                urlString);
	   	apacheResponse.setErrorMessage(errorMsg);
	   	return null;
	  }	
	  /* Catch any I/O exceptions thrown by the code above. Apparently, the Apache
	     HTTP client will throw exceptions in quite a few cases. */ 
		catch (IOException IOException) {	
			if (pathValue != null)
			  LOG.info("Path - " + pathValue);
			LOG.info("IOException while executing processHttpOperation");
			LOG.info(IOException.getMessage(), IOException);
		  HDLmEvent.eventOccurred("IOException");		
			String   IOExecMsg = IOException.getMessage();
		  /* Build the error message */
			String   errorMsg = String.format("IOException message (%s) URL (%s)",  
					                              IOExecMsg, 
					                              urlString);
			apacheResponse.setErrorMessage(errorMsg);
			return null;
		}		 
		/* Catch any remaining exceptions thrown by the code above. Apparently, the Apache
		   HTTP client will throw exceptions in quite a few cases. */ 
		catch (Exception exception) {	
			if (pathValue != null)
			  LOG.info("Path - " + pathValue);
			LOG.info("Exception while executing processHttpOperation");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");	
			String   execMsg = exception.getMessage();
		  /* Build the error message */
			String   errorMsg = String.format("Exception message (%s) URL (%s)",  
					                              execMsg, 
					                              urlString);
			apacheResponse.setErrorMessage(errorMsg);
			return null;
		}	
		finally {			 
			try {
				if (closeableResponse != null)
					closeableResponse.close();
				if (closeableHttpClient != null)
					closeableHttpClient.close();
				if (outputStream != null)
					outputStream.close();
			} 
		  catch (IOException subException) { 
		  	if (pathValue != null)
				  LOG.info("Path - " + pathValue);
				LOG.info("IOException while executing processHttpOperation - finally");
		  	LOG.info(subException.getMessage(), subException);
		  	HDLmEvent.eventOccurred("IOException");	
		  	return null;
		  }
		  catch (Exception exception) { 
		  	if (pathValue != null)
				  LOG.info("Path - " + pathValue);
				LOG.info("Exception while executing processHttpOperation - finally");
		  	LOG.info(exception.getMessage(), exception);
		  	HDLmEvent.eventOccurred("Exception");	
		  	return null;
		  }
		}
		/* Return the response instance with all of the output values */		
		return apacheResponse;
	}	 
	/* This routine looks for a Content-Length header in the headers
		 associated with an Apache request. In all cases, the Content-Length
		 header is removed from the message headers. This step is required
		 to avoid certain errors in the Apache code. */ 
	protected static int removeContentLength(HttpMessage httpMessage) {		 
		/* Check if the HTTP message passed by the caller is null */
		if (httpMessage == null) {
			String  errorText = "Apache HTTP message passed to removeContentLength is null";
			throw new NullPointerException(errorText);
		}
		/* Get all of the headers associated with the Apache request */
		Header     badHeader = null;
		Header[]   headers = httpMessage.getAllHeaders();
		int        removeCount = 0;
		/* Check each header for a Content-Length name */ 
		for (var header : headers) {			
			String   headerName = header.getName();
			if (!headerName.equalsIgnoreCase("Content-Length")) 
				continue;				
		  badHeader = header;		
		  break;
		}
		/* Remove the bad header, if we found it */
		if (badHeader != null) {
			httpMessage.removeHeader(badHeader);		
			removeCount++;
		}
		return removeCount;
	}
  /* This routine looks for a Content-Length header in the headers
	   associated with an Apache request. If the header is found, the
	   length is checked. If the length is zero, the Content-Length
	   header is removed from the message headers. This step is required
	   to avoid certain errors in the Apache code. */ 
	protected static int removeContentLengthZero(HttpMessage httpMessage) {		 
		/* Check if the HTTP message passed by the caller is null */
		if (httpMessage == null) {
			String  errorText = "Apache HTTP message passed to removeContentLengthZero is null";
			throw new NullPointerException(errorText);
		}
		/* Get all of the headers associated with the Apache request */
		Header     badHeader = null;
		Header[]   headers = httpMessage.getAllHeaders();
		int        removeCount = 0;
		/* Check each header for a Content-Length header with a length
		   of zero */  
		for (var header : headers) {			
			String   headerName = header.getName();
			if (headerName.equalsIgnoreCase("Content-Length")) {
			  String   headerValue = header.getValue();
			  if (headerValue.equals("0")) 
			  	badHeader = header;
			}
		}
		/* Remove the bad header, if we found it */
		if (badHeader != null) {
			httpMessage.removeHeader(badHeader);		
			removeCount++;
		}
		return removeCount;		
	}
	/* The next routine handles one HTTP(S) request */ 
	protected static HDLmApacheResponse runHttpOperation(HDLmProtocolTypes protocolType, 
			                                                 HDLmHttpTypes operationType, 
			                                                 String hostName, 
			                                                 String pathValue,
			                                                 ArrayList<String> inputHeaders,
			                                                 byte[] inputContentBytes,
			                                                 HDLmApacheRedirect redirect,
			                                                 HDLmShowRequest showRequest,
			                                                 HDLmShowResponse showResponse) throws ClientProtocolException, IOException {
		/* Check if the protocol type enum value passed by the caller is null */
		if (protocolType == null) {
			String  errorText = "Protocol type enum value passed by the caller to runHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the protocol type is supported */
		if (protocolType != HDLmProtocolTypes.HTTP &&
        protocolType != HDLmProtocolTypes.HTTPS) {
			String   errorFormat = "Protocol type value (%s) passed to runHttpOperation is invalid";
			String   errorText = String.format(errorFormat, protocolType.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the operation type enum value passed by the caller is null */
		if (operationType == null) {
			String  errorText = "Operation type enum value passed by the caller to runHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the operation type is supported */
		if (operationType != HDLmHttpTypes.GET     &&
				operationType != HDLmHttpTypes.HEAD    &&				
        operationType != HDLmHttpTypes.POST    &&
        operationType != HDLmHttpTypes.PUT     &&
        operationType != HDLmHttpTypes.DELETE  &&
        operationType != HDLmHttpTypes.TRACE   &&
        operationType != HDLmHttpTypes.OPTIONS &&
        operationType != HDLmHttpTypes.PATCH) {
			String   errorFormat = "Operation type value (%s) passed to runHttpOperation is invalid";
			String   errorText = String.format(errorFormat, operationType.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the host name string by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to runHttpOperation is null";
			throw new NullPointerException(errorText);
		}		
		/* Check the path string (if any) passed by the caller. The path
		   string can be null. If the path string is not null, it must
		   start with a leading forward slash operation. */
		if (pathValue != null) {
			if (pathValue.length() == 0) {
				String   errorText = "Empty path string passed to runHttpOperation";
				HDLmAssertAction(false, errorText);	
			}
			if (pathValue.charAt(0) != '/') {
				String   errorText = "Leading forward slash missing from path string passed to runHttpOperation";
				HDLmAssertAction(false, errorText);					
			}			
		}		
		/* Check if the redirect enum value passed by the caller is null */
		if (redirect == null) {
			String  errorText = "Redirect enum value passed by the caller to runHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the redirect enum value is supported */
		if (redirect != HDLmApacheRedirect.DISABLE &&
        redirect != HDLmApacheRedirect.LAX) {
			String   errorFormat = "Redirect enum value (%s) passed to runHttpOperation is invalid";
			String   errorText = String.format(errorFormat, redirect.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the show request enum value passed by the caller is null */
		if (showRequest == null) {
			String  errorText = "Show request enum value passed by the caller to runHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the show request enum value is supported */
		if (showRequest != HDLmShowRequest.SHOWREQUESTOK &&
				showRequest != HDLmShowRequest.SHOWREQUESTNOTOK) {
			String   errorFormat = "Show request enum value (%s) passed to runHttpOperation is invalid";
			String   errorText = String.format(errorFormat, redirect.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Check if the show response enum value passed by the caller is null */
		if (showResponse == null) {
			String  errorText = "Show response enum value passed by the caller to runHttpOperation is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the show response enum value is supported */
		if (showResponse != HDLmShowResponse.SHOWRESPONSEOK &&
				showResponse != HDLmShowResponse.SHOWRESPONSENOTOK) {
			String   errorFormat = "Show response enum value (%s) passed to runHttpOperation is invalid";
			String   errorText = String.format(errorFormat, redirect.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Display the Apache request headers, if requested */
		if (showRequest == HDLmShowRequest.SHOWREQUESTOK) {
			HDLmString.displayStrings("RequestHeader", inputHeaders); 
		}
		/* Declare and define a variable that can be used to return the results
		   of the current Apache operation to the caller */ 
		HDLmApacheResponse   httpResponse = null;
		/* Run the current Apache request */
		httpResponse =  HDLmApache.processHttpOperation(protocolType, operationType, hostName, 
								                                    pathValue, inputHeaders, inputContentBytes,
								                                    redirect); 
		/* Display the Apache response headers, if requested */
		if (httpResponse != null && 
		    showResponse == HDLmShowResponse.SHOWRESPONSEOK) {
			ArrayList<String>   responseHeaders = httpResponse.getHeaders();
			if (responseHeaders != null)
			  HDLmString.displayStrings("ResponseHeader", responseHeaders);
		}
		/* Return the response instance with all of the output values */		
		return httpResponse;
	}	 
	/* This routine tries to add all of the headers passed to it, to
     the Apache request object. The Apache request object may already
     have some headers set. The caller must deal with this issue (if
     it is a problem). The headers passed by the caller are just Java 
     strings. This routine handles breaking each string as need be.
  
     If the headers need to be modified, they must be changed before
     this routine is called. */
	protected static void setHeaders(HttpMessage request,
			                             ArrayList<String> inputHeaders) { 
		/* Check if the Apache request passed by the caller is null */
		if (request == null) {
			String  errorText = "Apache request passed to setHeaders is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input headers passed by the caller are null */
		if (inputHeaders == null) {
			String  errorText = "Input headers passed to setHeaders are null";
			throw new NullPointerException(errorText);
		}
		/* Process each input header */
		for (String header : inputHeaders) {
	   int  colonIndex = header.indexOf(':');
	   if (colonIndex <= 0) {
	   	String   errorFormat = "Colon not found or misplaceed in HTTP header (%s)";
				String   errorText = String.format(errorFormat, header);
				HDLmAssertAction(false, errorText);		    	
	   }
	   /* Split the HTTP header string into two parts. The first part is
	      the header type. The second part is the header value. Note that
	      we try to skip the leading blank in the header value. */
			String   headerType = header.substring(0, colonIndex);
			if (header.charAt(colonIndex+1) == ' ')
				colonIndex++;
			String   headerValue = header.substring(colonIndex+1);
			request.setHeader(headerType, headerValue);			
		}	
	}
	/* This routine is just a wrapper for the main set headers routine.
	   This routine allows the headers array reference to be null. */
	protected static void setHeadersNull(HttpMessage request,
                                       ArrayList<String> inputHeaders) {
		/* Check if the Apache request passed by the caller is null */
		if (request == null) {
			String  errorText = "Apache request passed to setHeadersNull is null";
			throw new NullPointerException(errorText);
		}
		if (inputHeaders != null)
			HDLmApache.setHeaders(request, inputHeaders);
	}	
}
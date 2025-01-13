package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmHttp short summary.
 *
 * HDLmHttp description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmHttp {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmHttp.class); 
	/* This class can never be instantiated */
	private HDLmHttp() {}	
	/* Build a header string */
	protected static String  buildHeader(String headerName,
			                                 String headerValue) {
		/* Check a few values passed by the caller */
		if (headerName == null) {
			String   errorText = "Header name reference passed to buildHeader is null";
			throw new NullPointerException(errorText);			
		}
		if (headerValue == null) {
			String   errorText = "Header value reference passed to buildHeader is null";
			throw new NullPointerException(errorText);		
		}
		/* Create a string builder for use below */
		StringBuilder   headerBuilder = new StringBuilder();
		/* Build the header */
		headerBuilder.append(headerName);
		headerBuilder.append(": ");
		headerBuilder.append(headerValue);
		/* Return the finished header to the caller */
		return headerBuilder.toString();
	}
	/* Build a content type header string */
	protected static String  buildHeaderContentType(String contentType) {
		/* Check one or more values passed by the caller */
		if (contentType == null) {
			String   errorText = "Content type string reference passed to buildHeaderContentType is null";
			throw new NullPointerException(errorText);			
		}
		/* Create a string builder for use below */
		StringBuilder   headerBuilder = new StringBuilder();
		/* Build the header */
		headerBuilder.append("Content-Type");
		headerBuilder.append(": ");
		headerBuilder.append(contentType);
		/* Return the finished header to the caller */
		return headerBuilder.toString();
	}
	/* Check a domain name to see if is valid */
	protected static String  checkDomainName(final String nameStr) {
		/* Set the initial error message */
		String  errorMessage = null;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the value passed by the caller is null */
			if (nameStr == null) {
				errorMessage = "Domain name is null";
				break;			
			}
			/* Check if the length of the domain name is valid */
  		int   nameStrLen = nameStr.length();
			if (nameStrLen < 1 ||
					nameStrLen > 253) {
				String   errorFormat = "Domain name string length (%d) is invalid"; 
				String   errorText = String.format(errorFormat, nameStrLen);
				errorMessage = errorText; 
				break;	
			}
			/* Check if the domain name starts or ends with a hyphen */
			char  firstChar = nameStr.charAt(0);
			char  lastChar = nameStr.charAt(nameStrLen - 1);
			if (firstChar == '-') {
				errorMessage = "Domain name starts with a hyphen";
				break;				
			}	
			if (lastChar == '-') {
				errorMessage = "Domain name ends with a hyphen";
				break;				
			}
			/* Split the domain name into parts (called labels). Check
			   each part. */ 
			String[]  labels = nameStr.split("\\.");
			for(String label : labels) {
				errorMessage = checkLabel(label);
				if (errorMessage != null)
					break;
			}
			if (errorMessage != null)
				break;
			/* No errors have been found */
			break;
		}	
		/* Return the final error message to the caller */
		return errorMessage;
	}	
	/* Check a label (part of a domain name) to see if is valid.
	   We allow labels to contain an underscore character. */
	protected static String  checkLabel(final String nameStr) {
		/* Set the initial error message */
		String  errorMessage = null;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the value passed by the caller is null */
			if (nameStr == null) {
				errorMessage = "Label is null";
				break;			
			}
			/* Check if the length of the label is valid */
  		int   nameStrLen = nameStr.length();
			if (nameStrLen < 1 ||
					nameStrLen > 63) {
				String   errorFormat = "Label (part of a domain name) string length (%d) is invalid"; 
				String   errorText = String.format(errorFormat, nameStrLen);
				errorMessage = errorText; 
				break;	
			}
			/* Check if each character is valid */
			for(int i = 0; i < nameStrLen; i++) {
				char  curChar = nameStr.charAt(i);
				/* Check if the current character is valid */
				if (HDLmString.isAlphaNumericHyphen(curChar))
					continue;
				if (curChar == '_')
					continue;
				/* Report an invalid character error */
				String   errorFormat = "Label (%s) has an invalid character (%c) at offset (%d)"; 
				String   errorText = String.format(errorFormat, nameStr, curChar, i);
				errorMessage = errorText; 
				break;				
			}
			/* No errors have been found */
			break;
		}	
		/* Return the final error message to the caller */
		return errorMessage;
	}
	/* This routine encodes part of the URL string passed to it. The 
     encoded URL is then constructed and returned to the caller. This
     code URL encodes the path (but not the first forward slash in 
     the path), the query, and the reference. The user information
     (if any), the protocol, and the domain name are not URL encoded. */
	protected static URL  encodeUrl(String urlStr) {
		/* Check one or more values passed by the caller */
		if (urlStr == null) {
			String   errorText = "URL string reference passed to encodeUrl is null";
			throw new NullPointerException(errorText);			
		}
		/* Construct a URL object from the URL string passed to this routine */
		URL  urlObject = null;
		try {
			urlObject = new URL(urlStr);
		}
		catch (MalformedURLException malformedException) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("MalformedURLException while executing encodeUrl");
			LOG.info(malformedException.getMessage(), malformedException);
			HDLmEvent.eventOccurred("MalformedURLException");	
			return null;
		}
		/* Get the parts of URL object for use below */
		String  urlAuthority = urlObject.getAuthority();
		String  urlProtocol = urlObject.getProtocol();
		String  urlUserInfo = urlObject.getUserInfo();
		String  urlHost = urlObject.getHost();
		int     urlPort = urlObject.getPort();
		String  urlPath = urlObject.getPath();
		String  urlQuery = urlObject.getQuery();
		String  urlRef = urlObject.getRef();
		/* Check if the URL path begins with a forward slash. Remove
		   the forward slash, if need be. */
		if (urlPath.startsWith("/") == true)
			urlPath = urlPath.substring(1);
		/* Build the user information and host string */
		String  urlUserInfoHost = urlHost;
		if (urlUserInfo != null)
			urlUserInfoHost = urlUserInfo + '@' + urlHost;
		/* URL encode the path, query, and reference */
		try {
			urlPath = URLEncoder.encode(urlPath, StandardCharsets.UTF_8.toString());
			if (urlQuery != null)
				urlQuery = URLEncoder.encode(urlQuery, StandardCharsets.UTF_8.toString());
			if (urlRef != null)
				urlRef = URLEncoder.encode(urlRef, StandardCharsets.UTF_8.toString());
		} 
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("UnsupportedEncodingException while executing encodeUrl");
			LOG.info(unsupportedEncodingException.getMessage(), unsupportedEncodingException);
			HDLmEvent.eventOccurred("UnsupportedEncodingException");	
			return null;
		}
		/* Build the URL file string from the path, query, and reference */
		String  urlFile = urlPath;
		if (urlFile.startsWith("/") == false)
			urlFile = '/' + urlFile;
		if (urlQuery != null)
			urlFile += '?' + urlQuery;
		if (urlRef != null)
			urlFile += '#' + urlRef;
		/* Build a URL object from the encoded path, query, and reference */
		URL  newUrlObject = null;
		try {
			newUrlObject = new URL(urlProtocol, urlUserInfoHost, urlPort, urlFile);
		} 
		catch (MalformedURLException malformedException) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("MalformedURLException while executing encodeUrl");
			LOG.info(malformedException.getMessage(), malformedException);
			HDLmEvent.eventOccurred("MalformedURLException");	
			return null;
		} 
		return newUrlObject;
	}
	/* Get a web page using the URL passed by the caller */
	protected static String  getWebPage(String urlStr) {
		/* Check one or more values passed by the caller */
		if (urlStr == null) {
			String   errorText = "URL string reference passed to getWebPage is null";
			throw new NullPointerException(errorText);			
		}
		HDLmApacheResponse  apacheResponse;
		String              curlOut = null;
		apacheResponse = HDLmCurlApache.runCurl(urlStr, "", "", HDLmHttpTypes.GET);
		curlOut = apacheResponse.getStringContent();
		return curlOut;		
	}
	/* Get the text string from a web page. The URL for the web page is passed
	   by the caller. This routine tries to get the HTML for the web page and
	   then tries to remove all of the HTML tags. */
	protected static String  getTextFromWebPage(String urlStr) {
		/* Check one or more values passed by the caller */
		if (urlStr == null) {
			String   errorText = "URL string reference passed to getTextFromWebPage is null";
			throw new NullPointerException(errorText);			
		}
		/* Try to get the HTML for the URL */
		HDLmApacheResponse  apacheResponse;
		String              curlOut = null;
		apacheResponse = HDLmCurlApache.runCurl(urlStr, "", "", HDLmHttpTypes.GET);
		curlOut = apacheResponse.getStringContent();
		if (curlOut == null)
			return null;
		/* Remove all of the HTML tags from the HTM */
		String  textStr = HDLmHtml.removeHtmlTagsAndOtherThings(curlOut);
		return textStr;
	}
}
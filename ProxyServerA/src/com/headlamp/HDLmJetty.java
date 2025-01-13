package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.security.KeyStore; 
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.http.MetaData;
import org.eclipse.jetty.http2.HTTP2Cipher;
import org.eclipse.jetty.http2.server.HTTP2CServerConnectionFactory;
import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.UrlEncoded;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua_parser.Client;
import ua_parser.Parser;
/**
 * HDLmJetty short summary.
 *
 * HDLmJetty description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class can ever be
   created */
public class HDLmJetty {
	/* The next statement initializes logging to some degree. Note that having the
	   slf4j jars and the log4j jars in the classpath also plays some role in
	   logging initialization.	 */
	private static final Logger LOG = LoggerFactory.getLogger(HDLmJetty.class);
	/* This class can never be instantiated */
	private HDLmJetty() {}
	/* This static method adds a very special header to the HTTP response. This
	   header shows that HTTP/3 is available at a special UDP port number. */
	protected static void addAltSvcheader(HttpServletResponse response) {
		/* Check if the servlet response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to addAltSvcheader is null";
			throw new NullPointerException(errorText);
		}
		/* Check if HTTP/3 is in use or not. If HTTP/3 is
	     in use, send back a header showing where HTTP/3
	     can be found. */
	  if (HDLmState.checkString("http3Connected") != null &&
	  		HDLmStateInfo.getHttp3Connected().equals("yes"))
 	    HDLmJetty.handleResponseHeader(response, "Alt-Svc", "h3=\":443\"");
	}
	/* Build a connector for HTTP (including HTTP2C). This connector does not
	   support encryption of any kind. Generally we won't use this connector for
	   traffic to the server. */
	protected static ServerConnector buildHttpConnector(Server server, int httpPort) {
		/* Check if the Jetty server instance passed by the caller is null */
		if (server == null) {
			String  errorText = "Jetty server instance passed to buildHttpConnector is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP port number passed by the caller is invalid */
		if (httpPort < 0) {
			String  errorText = "HTTP port number passed to buildHttpConnector is invalid";
			HDLmAssertAction(false, errorText);
		}
		/* Build some standard HTTP configuration values */
		HttpConfiguration               httpConfigForHttp = new HttpConfiguration();
		HttpConnectionFactory           http1 = new HttpConnectionFactory(httpConfigForHttp);
		HTTP2CServerConnectionFactory   http2c = new HTTP2CServerConnectionFactory(httpConfigForHttp);
		/* Build a connector for HTTP. This connector is quite simple. Note that HTTP/1
		   is listed first so that older clients will still be able to use this
		   connection. */
		ServerConnector   connectorHttp = new ServerConnector(server, http1, http2c);
		connectorHttp.setPort(httpPort);
		return connectorHttp;
	}
	/* Build a connector for HTTPS including HTTP/2 and ALPN. This connector does
	   support HTTP/2 and/or ALPN. This connector is not currently used except
	   by the unit test code. */
	protected static ServerConnector buildHttpsConnector(Server server,
			                                                 SslContextFactory.Server sslContextFactoryDotServer,
			                                                 ServerConnector connectorHttp,
			                                                 int httpsPort) {
		/* Check if the Jetty server instance passed by the caller is null */
		if (server == null) {
			String  errorText = "Jetty server instance passed to buildHttpsConnector is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the SSL/TLS context factory passed by the caller is null */
		if (sslContextFactoryDotServer == null) {
			String  errorText = "SSL/TLS context factory passed to buildHttpsConnector is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP connector passed by the caller is null */
		if (connectorHttp == null) {
			String  errorText = "HTTP connector passed to buildHttpsConnector is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP port number passed by the caller is invalid */
		if (httpsPort < 0) {
			String  errorText = "HTTPS port number passed to buildHttpsConnector is invalid";
			HDLmAssertAction(false, errorText);
		}
		sslContextFactoryDotServer.setCipherComparator(HTTP2Cipher.COMPARATOR);
		/* HTTPS Configuration */
		HttpConfiguration httpConfigForHttps = new HttpConfiguration();
		HttpConfiguration httpsConfigForHttps = new HttpConfiguration(httpConfigForHttps);
		httpsConfigForHttps.setSecureScheme("https");
		httpsConfigForHttps.setSecurePort(httpsPort);
		httpsConfigForHttps.addCustomizer(new SecureRequestCustomizer());
		/* Build just an SSL/TLS connector. This code does not use HTTP2 and ALPN. This
		   connector was used to fix a SPDY problem. */
		if (HDLmConfigInfo.getSupportHttp2() == false) {
			ServerConnector sslConnector = new ServerConnector(server,
					                                               new SslConnectionFactory(sslContextFactoryDotServer, "http/1.1"),
					                                               new HttpConnectionFactory(httpsConfigForHttps));
			sslConnector.setPort(httpsPort);
			return sslConnector;
		}
		/* HTTP/2 Connection Factory */
		HTTP2ServerConnectionFactory http2ConnectionFactory = new HTTP2ServerConnectionFactory(httpsConfigForHttps);
		/* It appears that the API invoked below no longer exists */
		/* NegotiatingServerConnectionFactory.checkProtocolNegotiationAvailable(); */
		ALPNServerConnectionFactory alpnConnectionFactory = new ALPNServerConnectionFactory();
		/* The default protocol set below was 'h2'. It is now obtained from HTTP connector
		   passed by the caller. */
		alpnConnectionFactory.setDefaultProtocol(connectorHttp.getDefaultProtocol());
		/* SSL Connection Factory */
		SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactoryDotServer,
				                                                                 alpnConnectionFactory.getProtocol());
		sslContextFactoryDotServer.setExcludeProtocols("TLSv1.3");
		/* Create the HTTP/2 Connector */
		ServerConnector http2Connector = new ServerConnector(server,
				                                                 sslConnectionFactory,
				                                                 alpnConnectionFactory,
				                                                 http2ConnectionFactory,
				                                                 new HttpConnectionFactory(httpsConfigForHttps));
		http2Connector.setPort(httpsPort);
		/* Turn off ALPN debugging. As of Jetty 9.4.49, this is no
		   longer possible. The line below has been commented out.
		   it it very unclear what difference this change will make. */
		/* ALPN.debug = false; */
		return http2Connector;
	}
	/* Build a connector for HTTPS. This connector does not support HTTP/2 and/or
	   ALPN. This connector may no longer be in use other than by the unit test
	   code. */
	protected static ServerConnector buildHttpsConnectorOld(Server server,
			                                                    SslContextFactory.Server sslCFS,
			                                                    int httpsPort) {
		/* Check if the Jetty server instance passed by the caller is null */
		if (server == null) {
			String  errorText = "Jetty server instance passed to buildHttpsConnectorOld is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the SSL/TLS context factory passed by the caller is null */
		if (sslCFS == null) {
			String  errorText = "SSL/TLS context factory passed to buildHttpsConnectorOld is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP port number passed by the caller is invalid */
		if (httpsPort < 0) {
			String  errorText = "HTTPS port number passed to buildHttpsConnectorOld is invalid";
			HDLmAssertAction(false, errorText);
		}
		/* Build a connector for HTTPS */
		ServerConnector   connectorHttps = new ServerConnector(server, sslCFS);
		connectorHttps.setPort(httpsPort);
		return connectorHttps;
	}
	/* Build a Java keystore instance and return it to the caller. The caller just
	   uses the keystore. This code knows the location of the keystore instance. */
	protected static KeyStore buildKeyStore() throws NoSuchAlgorithmException,
	                                                 CertificateException,
			                                             FileNotFoundException,
			                                             IOException, 
			                                             KeyStoreException,
			                                             UnrecoverableKeyException {
		char[] pwdArray;
		KeyStore keyStore = null;
		String jetty_home;
		String keyStorePlainPassword;
		String testStr = "HeadlampKeyStore";
		/* This code is not really used for now */
		if (testStr.equals("JettyKeyStore")) {
			/* Set the name of the Jetty home folder. This isn't even correct. By default,
			   the current working directory is workspace-4.33.0/ProxySever. More 'go back
			   one directory' operations are needed' */
			jetty_home = "../jetty-distribution-9.4.18.v20190429";
			/* Build a KeyStore instance for use below. This is the default Jetty keystore
			   for now. */
			keyStorePlainPassword = "storepwd";
			pwdArray = keyStorePlainPassword.toCharArray();
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(new FileInputStream("../" + jetty_home + "/modules/ssl/keystore"), pwdArray);
		}
		/* Try to use the Java Keystore with the actual certificates */
		else {
			/* Build a KeyStore instance for use below. This is not the default Jetty
			   keystore. Note that if this password is changed, they the password used to
			   build the SSL/TLS connection factory must also be changed. */
			keyStorePlainPassword = HDLmJetty.getStandardKeyStorePassword();
			pwdArray = keyStorePlainPassword.toCharArray();
			keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream(HDLmJetty.getStandardKeyStorePath()), pwdArray);
		}
		return keyStore;
	}
	/* Build an SSL context factory and return it to the caller. This SSL context
	   factory is not configured for HTTP/2. The caller must do this as need be. */
	protected static SslContextFactory.Server buildSslContextFactoryDotServer(KeyStore keyStore) {
		/* Check if the KeyStore instance passed by the caller is null */
		if (keyStore == null) {
			String  errorText = "KeyStore instance passed to buildSslContextFactory is null";
			throw new NullPointerException(errorText);
		}
		/* Build and configure a context factory for SSL */
		SslContextFactory.Server  sslContextFactoryDotServer = new SslContextFactory.Server();
		sslContextFactoryDotServer.setKeyStore(keyStore);
		String testStr = "HeadlampKeyStore";
		/* This code is not really used for now */
		if (testStr.equals("JettyKeyStore")) {
			sslContextFactoryDotServer.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
			sslContextFactoryDotServer.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");
		}
		/* Set the actual keystore passwords. Note that if these passwords are changed,
		   they the password used to build the Java keystore must also be changed. */
		else {
			sslContextFactoryDotServer.setKeyStorePassword("headlamp");
			sslContextFactoryDotServer.setKeyManagerPassword("headlamp");
		}
		return sslContextFactoryDotServer;
	}
	/* This routine checks if a header is present in a request. The header
	   is returned, if it is found. Otherwise, a null value is returned to
	   the caller. This routine will never cause an exception if a header
	   is not found. Note that the entire header is returned to the caller,
	   not just the header value. */
	protected static String  checkForHeader(HttpServletRequest request, String headerName) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
		  String  errorText = "Servlet request passed to checkForHeader is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the header name is null */
		if (headerName == null) {
		  String  errorText = "Header name passed to checkForHeader is null";
		  throw new NullPointerException(errorText);
		}
		/* The call below may or may not work. The requested header may not exist
		   in the current request. We may get a null value back from the call. That
		   is OK. The null value will be returned to the caller. */
	  String  headerStr = request.getHeader(headerName);
	  return headerStr;
	}
	/* This routine checks if a session cookie value is present in a request.
	   A session object is returned, if the session cookie value is present or
	   not. This routine will never cause an exception if the session cookie
	   is not found. The session cookie only has the session ID. The actual 
	   session must be obtained from the session cache. */
	protected static HDLmSession  checkForSession(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
		  String  errorText = "Servlet request passed to checkForSession is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a few values */
		HDLmSession   sessionObj = null;
		String        plainTextSessionIdJsonValue = null;
	  /* Try to get the name of the session cookie. This should never fail.
       However, you never know. */
    String   cookieName = HDLmDefines.getString("HDLMSESSIONCOOKIE");
    if (cookieName == null) {
    	String   errorFormat = "Define value for session cookie name not found (%s)";
		  String   errorText = String.format(errorFormat, "HDLMSESSIONCOOKIE");
		  HDLmAssertAction(false, errorText);
    }
	  /* Try to get the actual cookie (that contains encrypted JSON), 
	     now that we have the cookie name */
	  String   encryptedSessionIdJsonValue = HDLmJetty.getCookieExtended(request, cookieName);
	  /* LOG.info("Encrypted session id value - " + encryptedSessionIDJsonValue); */
	  /* LOG.info("Cookie name - " + cookieName); */
	  /* The request to get a cookie value, may or may not have succeeded. If this
	     request failed, then we may need to report an error. However, we may be
	     able to return a null value to the caller. */
	  if (encryptedSessionIdJsonValue == null)
	    return sessionObj;
    /* Try to decrypt the cookie, if possible */
    if (encryptedSessionIdJsonValue != null) {
    	String  encryptionKeyValue = HDLmConfigInfo.getSecretEncryptionKey();
    	plainTextSessionIdJsonValue = HDLmEncryption.decrypt(encryptionKeyValue, 
    			                                                 encryptedSessionIdJsonValue);
    }
	  /* Get the session JSON from the session cache, if possible */
  	HDLmSession   tempSessionObj = HDLmSession.buildSession(plainTextSessionIdJsonValue);
  	String sessionIdStr = tempSessionObj.getSessionId();    	
    sessionObj = HDLmSession.getFromCacheIfPresent(sessionIdStr); 
	  if (sessionObj == null &&
	  		sessionObj != null) {
	   	String   errorFormat = "Session object not obtained using session cookie";
			String   errorText = String.format(errorFormat);
			HDLmAssertAction(false, errorText);
	  }
    return sessionObj;
	}
	/* Check the JSON element passed by the caller for JavaScript
	   exceptions. The overall purpose of this routine is to note
	   JavaScript exceptions when they occur and are sent to the
	   server as post data. */
	protected static void  checkForJavaScriptExceptions(final JsonElement jsonElement) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to checkForJavaScriptExceptions is null";
			throw new NullPointerException(errorText);
		}
		/* Check for a JSON object. A JSON object will contain zero or
	     more name/value pairs. */
	  if (!jsonElement.isJsonObject())
		  return;
		/* Get the JSON object from the JSON element */
		JsonObject  jsonObject = jsonElement.getAsJsonObject();
		/* Check if the current JSON object has a name we are
		   looking for */
		if (!jsonObject.has("reason"))
			return;
		/* Get the value associated with the name we are looking for */
	  String  curValue = jsonObject.getAsJsonPrimitive("reason").getAsString();
	  if (!curValue.equals("exception"))
	  	return;
		/* Note that a JavaScript exception event has occurred */
	  String  hostName = jsonObject.getAsJsonPrimitive("hostName").getAsString();
	  if (hostName == null) {
			String  errorText = "JSON element passed to checkForJavaScriptExceptions did not contain host name";
			throw new NullPointerException(errorText);
	  }
	  String  divisionName = jsonObject.getAsJsonPrimitive("divisionName").getAsString();
	  if (divisionName == null) {
			String  errorText = "JSON element passed to checkForJavaScriptExceptions did not contain division name";
			throw new NullPointerException(errorText);
	  }
	  String  siteName = jsonObject.getAsJsonPrimitive("siteName").getAsString();
	  if (siteName == null) {
			String  errorText = "JSON element passed to checkForJavaScriptExceptions did not contain site name";
			throw new NullPointerException(errorText);
	  }
	  String  ruleName = jsonObject.getAsJsonPrimitive("modification").getAsString();
	  if (ruleName == null) {
			String  errorText = "JSON element passed to checkForJavaScriptExceptions did not contain rule name";
			throw new NullPointerException(errorText);
	  }
	  HDLmEvent.eventOccurredJavaScriptException(hostName, divisionName, siteName, ruleName);
	}
	/* Dump all of the headers from a servlet request out */
	protected static void dumpAllHeaders(HttpServletRequest request) {
		/* Check if the servlet request passed by the caller is null */
		if (request == null) {
			String  errorText = "Serlet request passed to dumpAllHeaders is null";
			throw new NullPointerException(errorText);
		}
		/* Get all of the HTTP(s) header names */
		Enumeration<String>  headersNames = request.getHeaderNames();
		while (headersNames.hasMoreElements()) {
			String headerNameStr = headersNames.nextElement();
			if (headerNameStr == null)
				continue;
			String  headerValueStr = request.getHeader(headerNameStr);
			LOG.info(headerNameStr + ": " + headerValueStr);
		}
	}
	/* Dump the contents of a servlet request out. The servlet response is used as
	   the target for the contents of the servlet request. The servlet request is
	   queried in a number of ways. In addition, the HTTP headers are dumped out. */
	protected static void dumpRequestContents(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* Check if the servlet request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to dumpRequestContents is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the servlet response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to dumpRequestContents is null";
			throw new NullPointerException(errorText);
		}
		/* Get some information from the servlet reqeust */
		response.getWriter().println("Context Path " + request.getContextPath());
		response.getWriter().println("Auth Type " + request.getAuthType());
		response.getWriter().println("Method " + request.getMethod());
		response.getWriter().println("Request Path value " + request.getRequestURI());
		response.getWriter().println("Query String " + request.getQueryString());
		response.getWriter().println("Path Translated " + request.getPathTranslated());
		response.getWriter().println("Path Info " + request.getPathInfo());
		response.getWriter().println("Protocol " + request.getProtocol());
		response.getWriter().println("Local Port " + request.getLocalPort());
		response.getWriter().println("Local Address " + request.getLocalAddr());
		response.getWriter().println("Server Port " + request.getServerPort());
		/* Get and use all of the HTTP header names */
		Enumeration<String> headerTypeNames = request.getHeaderNames();
		while (headerTypeNames.hasMoreElements()) {
			String headerType = headerTypeNames.nextElement();
			response.getWriter().println("Header Name " + headerType);
			response.getWriter().println("Header " + request.getHeader(headerType));
			/* Get all of the HTTP headers of a specific type */
			Enumeration<String>  headers = request.getHeaders(headerType);
			while (headers.hasMoreElements()) {
				String headerStr = headers.nextElement();
				response.getWriter().println("Header " + headerStr);
			}
		}
	}
	/* This routine does the actual work of the servlet editor get method.
	   The code is separate so that it can be used by other routines. This
	   code handles returning the rule editor code and any other files. */
	protected static void editorGet(HttpServletRequest request, HttpServletResponse response) {
		/* Check if the servlet request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to editorGet is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the servlet response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to editorGet is null";
			throw new NullPointerException(errorText);
		}
		/* Build the path name for the file */
		String pathStr = "";
		/* First check if we are inside a Docker container. Special
	     case code is needed if a Docker container is active. */
  	if (HDLmMain.isDockerFlagSet() == false) {
			/* Check for Windows versus Unix (of some kind) */
			if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS &&
					HDLmMain.isDockerFlagSet() == false) {
				pathStr += "\\Users\\pscha\\Documents\\Visual_Studio_Code";
				pathStr += "\\Projects\\WebApplication5\\WebApplication5\\";
			}
			/* The following path is correct for our AWS Linux instance */
			else {
				pathStr += "/var/www/html";
				pathStr += "/example.com/public_html/";
			}
  	}
  	/* Looks like we are running inside a Docker container */
    else {
			pathStr += "/var/www/html";
			pathStr += "/example.com/public_html/";
    }
		/* Get the path value request string from the current request */
		String pathValueString = HDLmJetty.getPathValueString(request);
		/* Use the path value request string to get the name of the file we need to return.
		   Note that if the path value did not contain a file name, we just use a default
		   value. */
		String fileName;
		if (pathValueString.equals("/")               ||
				pathValueString.equals("/mod")            ||
				pathValueString.equals("/mods")           ||
				pathValueString.equals("/modification")   ||
				pathValueString.equals("/modifications")  ||
				pathValueString.equals("/proxy")          ||
				pathValueString.equals("/proxys")         ||
				pathValueString.equals("/proxies")        ||
				pathValueString.equals("/config")         ||
				pathValueString.equals("/configs")        ||
				pathValueString.equals("/configuration")  ||
				pathValueString.equals("/configurations") ||
				pathValueString.equals("/store")          ||
				pathValueString.equals("/stores")         ||
				pathValueString.equals("/storage"))
			fileName = "html/index.html";
		/* Check if the pass-through display has been requested */
		else if (pathValueString.equals("/passthru-display"))
			fileName = "html/index.html";
		/* Check if the build function (one or more rules) has been requested */
		else if (pathValueString.equalsIgnoreCase("/BuildRules"))
			fileName = "html/index.html";
		else {
			/* Check if the file name is long enough to remove the first character. The
			   first character is probably a forward slash. */
			if (pathValueString.length() > 0) {
				fileName = pathValueString.substring(1);
				fileName = java.net.URLDecoder.decode(fileName, StandardCharsets.UTF_8);
			} else
				fileName = "";
		}
		/* Get the file from the file system. Files are handled in very different ways
		   depending on their type. */
		/* For debugging show a few values */
		if (LOG.isDebugEnabled()) {
			LOG.debug("in HDLmJetty.editorGet");
			LOG.debug(request.toString());
			LOG.debug(response.toString());
			LOG.debug(pathStr);
			LOG.debug(fileName);
		}
		HDLmJetty.processFile(request, response, pathStr, fileName);
	}
	/* This routine does the actual work of the servlet editor post method.
	   The code is separate so that it can be used by other routines. This
	   routine seems to handle requests for the proxy program and requests
	   to calculate (and save) a perceptual hash value. */
	protected static void editorPost(HttpServletRequest request,
	  		                           HttpServletResponse response,
	  		                           String postPayload) throws IOException {
		/* Check if the servlet request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to editorPost is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the servlet response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to editorPost is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the post request payload string passed by the caller is null */
		if (postPayload == null) {
			String  errorText = "Post string passed to editorPost is null";
			throw new NullPointerException(errorText);
		}
		/* LOG.info("HDLmJetty.editorPost - start"); */
		String  fileName = null;
		/* Get the actual path value request string from the current request */
		String  pathValueString = HDLmJetty.getPathValueString(request);
		String  pHashNameString = HDLmConfigInfo.getPHashName();
		String  proxyNameString = HDLmConfigInfo.getProxyName();
	  if (pathValueString.endsWith(proxyNameString)) {
			/* Get some of the post data. This used to be done by getting
			   a set of parameters. The post data is actually passed as a
			   string. We used to be able to access the post string as a
			   set of parameters. Because the post data is obtained
			   elsewhere, this no longer works. */
			/* Try to convert the JSON to a JSON object. If this fails,
		     then we do not have a string than can be converted to a
		     JSON object. If this works, then we do have string than
		     can be converted to a JSON object. */
	    JsonParser    parser = new JsonParser();
	    JsonElement   jsonElement = null;
	    try {
	    	/* The "JSON" string is actually in HTML parameter format.
	    	   We need to convert the string to real JSON format. */
	    	postPayload = postPayload.replaceAll("=", "\":\"");
	    	postPayload = postPayload.replaceAll("&", "\",\"");
	    	postPayload = "{\"" + postPayload + "\"}";
		    jsonElement = parser.parse(postPayload);
	    }
	    catch (Exception exception) {
	     	if (postPayload != null)
	 	    	LOG.info("JSON string - " + postPayload);
		    LOG.info("Exception while executing editorPost");
		    LOG.info(exception.getMessage(), exception);
		    HDLmEvent.eventOccurred("Exception");
		    return;
	    }
			/* Get a few values from the JSON */
			String  parmRequestType = HDLmJson.getJsonString(jsonElement, "requesttype");
			String  parmUrl = HDLmJson.getJsonString(jsonElement, "URL");
			String  parmUserid = HDLmJson.getJsonString(jsonElement, "userid");
			String  parmPassword = HDLmJson.getJsonString(jsonElement, "password");
			String  parmType = HDLmJson.getJsonString(jsonElement, "type");
			String  parmExtraInfo = HDLmJson.getJsonString(jsonElement, "extrainfo");
			/* Get the file name from the path value */
			/* Check if the file name is long enough to remove the first character. The
			   first character is probably a forward slash. */
			if (pathValueString.length() > 0) {
				fileName = pathValueString.substring(1);
				fileName = java.net.URLDecoder.decode(fileName, StandardCharsets.UTF_8);
			} else
				fileName = "";
			/* Check for the proxy file name. If the caller asked for the
			   proxy file name, then we must simulate the execution of the
			   proxy program. */
			if (fileName.equals(proxyNameString)) {
				String outJson = null;
				outJson = HDLmEditorServlet.simulateProxy(request, response,
						                                      parmRequestType, parmUrl, parmUserid, parmPassword, parmType,
	                                                parmExtraInfo);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(outJson);
				response.getWriter().flush();
			}
	  }
		/* Check for the perceptual hash program. If the caller asked for the procedural
		   hash program, then we must load the URL passed by the caller and then get the
		   perceptual hash value for the image. The URL passed to this routine may or may
		   not be a data URL. If it not a data URL, then it may start with two forward
       slash characters without an HTTP/HTTPS protocol in front of the two forward
       slash characters.*/
		if (pathValueString.endsWith(pHashNameString)) {
			/* LOG.info("HDLmJetty.editorPost - phash"); */
			Boolean               pHashUpdateCache = null;
			Image                 image = null;
			String                pHashHex = null;
			String                pHashUrl = null;
			String                outJson = null;
			String                urlStr = null;
			HDLmUtilityResponse   utilityResponse = null;
			/* This code used to get the image URL from the request.
			   Now the image URL is passed to this code. The call
			   below is no longer in use and won't work. */
			/* urlStr = getRequestPayloadChars(request); */
			urlStr = postPayload;
			/* Experience has shown that the URL string we get back from
			   the call above, can be null. The reason(s) are not clear.
			   However, we do get back a null value from time-to-time. */
			if (urlStr == null) {
				String  hostName = HDLmJetty.getHostNameFromRequest(request);
				String  requestOriginalPathValue = HDLmJetty.getOriginalPathValue(request);
				LOG.info("URL string is null in editorPost" + " - " + requestOriginalPathValue);
				LOG.info("URL string is null in editorPost" + " - " + hostName);
			}
			/* What follows is a dummy loop used only to allow break to work */
			while (true) {
			  /* We now have the URL string that we need the perceptual hash
		       value for. This code is only going to work if the URL starts
		       with 'http' or 'https' (without the quotes in both case).
		       Testing has show that URL strings actually begin with two
		       slash characters, not 'http' or https' (without the quotes). */
			  /* LOG.info(urlStr); */
			  urlStr = URLDecoder.decode(urlStr);
		    /* LOG.info("In editorPost" + " " + urlStr); */
		    /* LOG.info(urlStr); */
		    /* Get the suffix from the URL string. The suffix will show if we
		       really have an image at this point. */
		    String  urlSuffix = HDLmString.getFileNameSuffix(urlStr);
		    if (urlSuffix == null)
		    	break;
		    /* Check if we really have an image at this point */
		    String  urlType = HDLmString.getFileNameType(urlSuffix);
		    if (urlType == null ||
		    	  !urlType.equals("image"))
		    	break;
		    /* Get the perceptual hash value */
			  utilityResponse = HDLmUtility.getPerceptualHashFromUrl(urlStr, null);
			  pHashHex = utilityResponse.getPHashValue();
			  pHashUrl = utilityResponse.getUrlStr();
			  pHashUpdateCache = utilityResponse.getUpdateCache();
			  if (pHashUpdateCache != null &&
			  		pHashUpdateCache == true)
				  HDLmUtility.updatePHashCache(pHashUrl, pHashHex);
			  HDLmJetty.handleResponseAllowAllOrigins(request, response);
			  break;
			}
		  /* Get the request headers. The request headers may or may not
	       contain a referer (a misspelling of referrer) header. If we
	       don't have a referer header than we can not go any further. */
	    ArrayList<String>   requestHeaders = HDLmJetty.getRequestHeaders(request);
	    /* LOG.info(String.valueOf(requestHeaders.contains("referer"))); */
	    /* LOG.info(requestHeaders.toString()); */
	    boolean   referrerFound = false;
	    for (String requestHeader:requestHeaders) {
	  	  if (requestHeader.startsWith("referer")) {
	  		  referrerFound = true;
	  		  break;
	  	  }
	    }
	    if (referrerFound) {
			  /* Get the referrer host name. This is not the host name that
		  	   the browser is running on. This is not the host name of the
		  	   AWS server. */
		    String  referrerHostName = HDLmJetty.getReferrerHostName(request);
		    /* LOG.info(referrerHostName); */
			  if (referrerHostName == null) {
				  String   errorText = "Referrer host name not obtained from the request";
				  HDLmAssertAction(false, errorText);
			  }
				String  referrerProtocol = HDLmJetty.getReferrerProtocol(request);
			  if (referrerProtocol == null) {
				  String   errorText = "Referrer protocol not obtained from the request";
				  HDLmAssertAction(false, errorText);
			  }
				/* Add the Access-Control-Allow-Origin header, if need be */
				HDLmJetty.handleResponseHeader(response,
						                           "Access-Control-Allow-Origin",
						                           referrerProtocol + "://" + referrerHostName);
			  /* Check if an actual perceptual hash value was calculated
			     above. We don't want to return a null perceptual hash
			     value to the caller. */
			  if (pHashHex != null) {
				  outJson = "{\"phash\":\"" + pHashHex + "\"}";
				  response.setContentType("application/json");
				  response.setCharacterEncoding("UTF-8");
				  response.getWriter().print(outJson);
				  response.getWriter().flush();
			  }
			}
		}
	}
	/* This routine sends a special response back to the client that forces a
	   redirect to a SSL/TLS enabled port. If a client tries to use a non-SSL/TLS
	   port (in some cases), this routine is used to build the reply. */
	protected static void forceRedirect(HttpServletResponse response,
			                                String oldLocation,
			                                Integer httpsPort,
			                                String pathValueString) {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to forceRedirect is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the old location passed by the caller is null */
		if (oldLocation == null) {
			String  errorText = "Old location passed to forceRedirect is null";
			throw new NullPointerException(errorText);
		}
		response.setStatus(HttpStatus.MOVED_PERMANENTLY_301);
		/* Build the new location string */
		String newLocation = "https://";
		newLocation += oldLocation;
		if (httpsPort != null)
			newLocation += ":" + httpsPort.toString();
		if (pathValueString != null)
			newLocation += pathValueString;
		response.addHeader("Location", newLocation);
	}
	/* This routine tries to return the value of the Authority header in the current
	   request. This value may or may not be available. If the value is available it
	   is returned to the caller. This routine does not appear to work. The get header
	   call below fails, even though the Authority was sent. */
	protected static String getAuthorityHeader(HttpServletRequest request) {
		String  authorityValue = null;
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getAuthorityHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Get the Authority header, if possible */
		String headerType = ":authority";
		String headerStr = request.getHeader(headerType);
		authorityValue = headerStr;
		return authorityValue;
	}
	/* This routine tries to return information about a client based on the
	   User-Agent string. The User-Agent string is checked in many ways and values
	   are extracted from it. The caller must supply the User-Agent string. */
	protected static HDLmClientInfo getClientInformationNew(String userAgentHeader) {
		/* Check if the User-Agent string passed by the caller is null */
		if (userAgentHeader == null) {
			String  errorText = "User-Agent string passed to getClientInformationNew is null";
			throw new NullPointerException(errorText);
		}
		/* Build the instance used to return the results of this Apache operation to the
		   caller */
		HDLmClientInfo clientInfo = new HDLmClientInfo();
		String family;
		Parser uaParser;
		/* Try to extract the needed information using a standard User Agent parser.
		   This parser uses YAML and regexes to analyze the User Agent string. It is not
		   that fast. It is not clear if the paser is thread safe or not. The code below
		   assumes that it is not thread safe. */
		try {
			uaParser = new Parser();
			Client client = uaParser.parse(userAgentHeader);
			family = client.userAgent.family;
			/* Check for each type of browser */
			if (family.indexOf("Android") >= 0)
				clientInfo.setBrowserType(HDLmBrowserTypes.ANDROID);
			else if (family.indexOf("Safari") >= 0)
				clientInfo.setBrowserType(HDLmBrowserTypes.SAFARI);
			else if (family.indexOf("Opera") >= 0)
				clientInfo.setBrowserType(HDLmBrowserTypes.OPERA);
			else if (family.indexOf("IE") >= 0)
				clientInfo.setBrowserType(HDLmBrowserTypes.IE);
			else if (family.indexOf("Internet Explorer") >= 0)
				clientInfo.setBrowserType(HDLmBrowserTypes.IE);
			else if (family.indexOf("Edge") >= 0)
				clientInfo.setBrowserType(HDLmBrowserTypes.EDGE);
			else if (family.indexOf("Chrome") >= 0)
				clientInfo.setBrowserType(HDLmBrowserTypes.CHROME);
			else if (family.indexOf("Firefox") >= 0)
				clientInfo.setBrowserType(HDLmBrowserTypes.FIREFOX);
		}
		/* If we do get an exception, report all of the related information */
		catch (IOException IOException) {
			if (userAgentHeader != null)
			  LOG.info("Path value - " + userAgentHeader);
			LOG.info("IOException while executing getClientInformationNew");
			LOG.info(IOException.getMessage(), IOException);
			HDLmEvent.eventOccurred("IOException");
			return null;
		}
		catch (Exception exception) {
			if (userAgentHeader != null)
			  LOG.info("Path value - " + userAgentHeader);
			LOG.info("Exception while executing getClientInformationNew");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return null;
		}
		/* Store the User-Agent header string reference */
		clientInfo.setUserAgent(userAgentHeader);
		return clientInfo;
	}
	/* This routine tries to return information about a client based on the
	   User-Agent string. The User-Agent string is checked in many ways and values
	   are extracted from it. The caller must supply the User-Agent string. */
	protected static HDLmClientInfo getClientInformationOld(String userAgentHeader) {
		/* Check if the User-Agent string passed by the caller is null */
		if (userAgentHeader == null) {
			String  errorText = "User-Agent string passed to getClientInformationOld is null";
			throw new NullPointerException(errorText);
		}
		/* Build the instance used to return the results of this Apache operation to the
		   caller */
		HDLmClientInfo clientInfo = new HDLmClientInfo();
		int indexAndroid = userAgentHeader.indexOf("Android");
		int indexChrome = userAgentHeader.indexOf("Chrome");
		int indexFirefox = userAgentHeader.indexOf("Firefox");
		int indexSafari = userAgentHeader.indexOf("Safari");
		int indexTrident = userAgentHeader.indexOf("Trident");
		/* Store the User-Agent header string reference */
		clientInfo.setUserAgent(userAgentHeader);
		/* Check for Microsoft Edge */
		if (indexAndroid > 0)
			clientInfo.setBrowserType(HDLmBrowserTypes.ANDROID);
		else if (indexSafari > 0 && indexChrome < 0)
			clientInfo.setBrowserType(HDLmBrowserTypes.SAFARI);
		else if (userAgentHeader.indexOf("OPR/") >= 0)
			clientInfo.setBrowserType(HDLmBrowserTypes.OPERA);
		else if (userAgentHeader.indexOf("MSIE ") >= 0 || indexTrident > 0)
			clientInfo.setBrowserType(HDLmBrowserTypes.IE);
		else if (userAgentHeader.indexOf("Edge/") >= 0)
			clientInfo.setBrowserType(HDLmBrowserTypes.EDGE);
		else if (indexChrome > 0)
			clientInfo.setBrowserType(HDLmBrowserTypes.CHROME);
		else if (indexFirefox > 0)
			clientInfo.setBrowserType(HDLmBrowserTypes.FIREFOX);
		return clientInfo;
	}
	/* This routine tries to obtain the content type from the response headers. This
	   should generally work, but might not. If the content type can not be
	   obtained, a null value will be returned to the caller. */
	protected static String getContentType(HttpServletResponse response) {
		String contentType = null;
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet request passed to getContentType is null";
			throw new NullPointerException(errorText);
		}
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* We need to find the (first) content header (if any) */
			String contentHeader = HDLmJetty.getResponseHeader(response, "Content-Type");
			if (contentHeader == null)
				break;
			/* We need to fix the content header in a few ways */
			contentHeader = contentHeader.replace(';', ' ');
			contentHeader = contentHeader.replace(':', ' ');
			/* We need to split the content header into words */
			String[] contentHeaderSplit = contentHeader.split("\\s+");
			if (contentHeaderSplit.length < 1)
				break;
			contentType = contentHeaderSplit[0];
			break;
		}
		return contentType;
	}
	/* This routine tries to return the value of a specific cookie to the caller.
	   The caller passes a servlet request and a cookie name. The cookies associated
	   with the request are scanned looking for the specific name passed by the
	   caller. If the cookie is found by name, the value of the cookie is returned
	   to the caller. 
	   
	   This routine does not appear to be in use. No calls to this routine (except
	   for testing) appear to exist in the code. */
	protected static String getCookie(HttpServletRequest request, String desiredCookieName) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getCookie is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the cookie name passed by the caller is null */
		if (desiredCookieName == null) {
			String  errorText = "Cookie name passed to getCookie is null";
			throw new NullPointerException(errorText);
		}
		Cookie[] cookiesArray = request.getCookies();
		String cookieName;
		String cookieValue = null;
		if (cookiesArray != null) {
			for (Cookie cookie : cookiesArray) {
				cookieName = cookie.getName();
				if (!cookieName.equals(desiredCookieName))
					continue;
				cookieValue = cookie.getValue();
				break;
			}
		}
		return cookieValue;
	}
	/* This routine tries to return the value of a specific cookie to the caller.
	   The caller passes a servlet request and a cookie name. The cookies associated
	   with the request are scanned looking for the specific name passed by the
	   caller. If the cookie is found by name, the value of the cookie is returned
	   to the caller. Note that the value of the cookie is URL decoded, if need be. */
	protected static String getCookieExtended(HttpServletRequest request, String desiredCookieName) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getCookieExtended is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the cookie name passed by the caller is null */
		if (desiredCookieName == null) {
			String  errorText = "Cookie name passed to getCookieExtended is null";
			throw new NullPointerException(errorText);
		}
		Cookie[] cookiesArray = request.getCookies();
		String cookieName;
		String cookieValue = null;
		if (cookiesArray != null) {
			for (Cookie cookie : cookiesArray) {
				cookieName = cookie.getName();
				if (!cookieName.equals(desiredCookieName))
					continue;
				cookieValue = cookie.getValue();
				break;
			}
		}
		/* The cookie value we just obtained may have been URL encoded. That is dumb,
		   but Jetty requires it. Note that browsers do not typically require URL
		   encoding of cookie values. If the cookie value is URL encoded, then we must
		   decode it before we can use it any further. */
		if (cookieValue != null && cookieValue.indexOf('%') >= 0)
			cookieValue = UrlEncoded.decodeString(cookieValue);
		return cookieValue;
	}
	/* This routine tries to return all of the HTTP cookies from a servlet request
	   instance. The list of cookies might be empty (although this is unlikely).
	   This routine will always return a non-null ArrayList<Cookie> to the caller. A
	   null value will never be returned. */
	protected static ArrayList<Cookie> getCookies(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getCookies is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<Cookie> cookiesArrayList = new ArrayList<Cookie>();
		Cookie[] cookiesArray = request.getCookies();
		if (cookiesArray != null) {
			for (Cookie cookie : cookiesArray) {
				cookiesArrayList.add(cookie);
			}
		}
		return cookiesArrayList;
	}
	/* This routine tries to return all of the HTTP cookies from a servlet request
	   instance as a map. The map of cookies might be empty (although this is
	   unlikely). This routine will always return a non-null Map<String, String> to
	   the caller. A null value will never be returned. */
	protected static Map<String, String> getCookiesMap(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getCookiesMap is null";
			throw new NullPointerException(errorText);
		}
		Map<String, String> cookiesMap = new HashMap<String, String>();
		Cookie[] cookiesArray = request.getCookies();
		String cookieName;
		String cookieValue;
		if (cookiesArray != null) {
			for (Cookie cookie : cookiesArray) {
				cookieName = cookie.getName();
				cookieValue = cookie.getValue();
				cookiesMap.put(cookieName, cookieValue);
			}
		}
		return cookiesMap;
	}
	/* This routine tries to return the value of the requested header in the current
	   response (not request). This value may or may not be available. If the value
	   is available it is returned to the caller. */
	protected static String getResponseHeader(HttpServletResponse response, String headerType) {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to getResponseHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the header type passed by the caller is null */
		if (headerType == null) {
			String  errorText = "Header type passed to getResponseHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Get the requested header, if possible */
		String headerStr = response.getHeader(headerType);
		return headerStr;
	}
	/* This routine returns a header. The name of the header is
	   provided by the caller along with any array of headers.
	   The requested header is located in the array (if possible)
	   and returned to the caller. If the requested header can
	   not be found, a null value is returned to the caller. */
	protected static String getHeader(ArrayList<String> headers,
                                    String targetHeader) {
    /* Check if the HTTP host headers passed by the caller are null */
    if (headers == null) {
      String  errorText = "HTTP headers array passed to getHeader is null";
      throw new NullPointerException(errorText);
    }
    /* Check if the HTTP target header passed by the caller is null */
    if (targetHeader == null) {
      String  errorText = "HTTP target header passed to getHeader is null";
      throw new NullPointerException(errorText);
    }
    /* Check if the HTTP target header passed by the caller is empty */
    if (targetHeader.isEmpty() == true) {
	    String  errorText = "HTTP target header passed to getHeader is empty";
	    throw new IllegalArgumentException(errorText);
    }
	  /* Get the index of the header we are looking for */
    int   headerIndex;
    headerIndex = HDLmApache.findHeader(headers, targetHeader);
    if (headerIndex < 0)
    	return null;
    return headers.get(headerIndex);
	}
	/* This routine returns the value of a header. The value of a header is the
	   part that follows the header type and the associated colon. Note that
	   all leading and trailing blanks are removed from the header value. */
	protected static String getHeaderValue(final String curHeader) {
		/* Check if the input header passed by the caller is null */
		if (curHeader == null) {
			String  errorText = "Header passed to getHeaderValue is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few value */
		int     curHeaderIndex = curHeader.indexOf(':');
		String  curHeaderValue;
		if (curHeaderIndex < 0) {
    	String   errorFormat = "Colon after header type not found in HTTP header (%s)";
			String   errorText = String.format(errorFormat, curHeader);
			HDLmAssertAction(false, errorText);
    }
		curHeaderValue = curHeader.substring(curHeaderIndex+1);
		curHeaderValue = curHeaderValue.trim();
		return curHeaderValue;
	}
	/* This routine tries to get the value of a header from a response.
	   The caller provides the response object and a string with the
	   name of the header. This routine either returns the response
	   header or a null value. Note the response header just includes
	   the value of the header. The header name is passed by the caller
	   but not returned by this routine. */
	protected static String  getHeaderFromResponse(final HttpServletResponse response,
			                                           final String headerName) {
		/* Check the values passed by the caller */
		/* Check if the input response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to getHeaderFromResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input header name passed by the caller is null */
		if (headerName == null) {
			String  errorText = "Header name passed to getHeaderFromResponse is null";
			throw new NullPointerException(errorText);
		}
	  String  headerStr = null;
	  /* Try to get the actual response header from the response */
	  headerStr = response.getHeader(headerName);
	  return headerStr;
	}
	/* This routine returns the value of a header. The caller provides the
	   name of the header. The value of a header is the part that follows
	   the header type and the associated colon. Note that all leading and
	   trailing blanks are removed from the header value. */
	protected static String getHeaderValue(ArrayList<String> headers,
	                                       String targetHeader) {
	  /* Check if the HTTP host headers passed by the caller are null */
	  if (headers == null) {
	    String  errorText = "HTTP headers array passed to getHeaderValue is null";
	    throw new NullPointerException(errorText);
	  }
	  /* Check if the HTTP target header passed by the caller is null */
	  if (targetHeader == null) {
	    String  errorText = "HTTP target header passed to getHeaderValue is null";
	    throw new NullPointerException(errorText);
	  }
		/* Check if the HTTP headers array passed by the caller is empty */
		if (headers.isEmpty() == true) {
			String  errorText = "HTTP headers array passed to getHeaderValue is empty";
			throw new IllegalArgumentException(errorText);
		}
	  /* Check if the HTTP target header passed by the caller is empty */
	  if (targetHeader.isEmpty() == true) {
	    String  errorText = "HTTP target header passed to getHeaderValue is empty";
	    throw new IllegalArgumentException(errorText);
	  }
	  /* Get the header we are looking for (if possible) */
	  String  headerStr;
	  String  headerValue;
	  headerStr = HDLmJetty.getHeader(headers, targetHeader);
	  if (headerStr == null)
	    return null;
	  headerValue = HDLmJetty.getHeaderValue(headerStr);
	  return headerValue;
	}
	/* This routine tries to return the value of the Host header in the current
	   request. This value may or may not be available. If the value is available it
	   is returned to the caller. */
	protected static String getHostHeader(HttpServletRequest request) {
		String hostValue = null;
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getHostHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Get the Host header, if possible */
		String headerType = "Host";
		String headerStr = request.getHeader(headerType);
		hostValue = headerStr;
		return hostValue;
	}
	/* This routine tries to extract a host name from a string passed to it. The
	   passed string should be something like www.abc.com:80 or https://www.abc.com:80.
	   This routine will only return a host name, if a host name string can be found.
	   A null value will be returned otherwise. */
	protected static String getHostName(String inStr) {
		HDLmToken curToken;
		int tokenIndex = 0;
		int usedTokens = 0;
		String curValue;
		String hostName = null;
		/* Check if the input string value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to getHostName is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<HDLmToken> tokens = HDLmString.getTokens(inStr);
		StringBuilder hostNameBuilder = new StringBuilder();
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			int tokenCount = tokens.size();
			/* Remove the trailing sentinel token */
			tokenCount--;
			/* Check if the input string has an http or https prefix. These prefix values
			   must be skipped. */
			if (tokenCount > tokenIndex) {
				curToken = tokens.get(tokenIndex);
				curValue = curToken.getValue();
				if (curValue.equals("http") || curValue.equals("https")) {
					/* Skip past the protocol type token */
					tokenIndex++;
					/* Get the next token. It must be a colon operator. */
					curToken = tokens.get(tokenIndex);
					curValue = curToken.getValue();
					if (curToken.getType() != HDLmTokenTypes.OPERATOR) {
						String  errorText = "Colon operator token not found after http/https prefix";
						throw new IllegalStateException(errorText);
					}
					if (!curValue.equals(":")) {
						String  errorText = "Colon value not found after http/https prefix";
						throw new IllegalStateException(errorText);
					}
					tokenIndex++;
					/* Get the next token. It must be a forward slash operator. */
					curToken = tokens.get(tokenIndex);
					curValue = curToken.getValue();
					if (curToken.getType() != HDLmTokenTypes.OPERATOR) {
						String  errorText = "First slash token not found after http/https prefix and colon";
						throw new IllegalStateException(errorText);
					}
					if (!curValue.equals("/")) {
						String  errorText = "First slash value not found after http/https prefix and colon";
						throw new IllegalStateException(errorText);
					}
					tokenIndex++;
					/* Get the next token. It must be a forward slash operator. */
					curToken = tokens.get(tokenIndex);
					curValue = curToken.getValue();
					if (curToken.getType() != HDLmTokenTypes.OPERATOR) {
						String  errorText = "Second slash token not found after first slash";
						throw new IllegalStateException(errorText);
					}
					if (!curValue.equals("/")) {
						String  errorText = "Second slash value not found after first slash";
						throw new IllegalStateException(errorText);
					}
					tokenIndex++;
				}
			}
			/* Add all of the remaining tokens to the output host name. Stop if we find a
			   colon or forward slash. */
			while (tokenIndex < tokenCount) {
				curToken = tokens.get(tokenIndex);
				curValue = curToken.getValue();
				if (curValue.equals(":") || curValue.equals("/"))
					break;
				hostNameBuilder.append(curValue);
				usedTokens++;
				tokenIndex++;
			}
			break;
		}
		/* Check if we actually used any tokens. If we did then we can use the host name
		   string. */
		if (usedTokens > 0)
			hostName = hostNameBuilder.toString();
		return hostName;
	}
	/* This routine was never finished and is not really used. It turns out
	   that we can not get an Authority header from the in-bound request. */
	protected static String getHostNameFromAuthority(String authorityHeader) {
		/* Check if the Authority header passed by the caller is null */
		if (authorityHeader == null) {
			String  errorText = "Authority header passed to getHostNameFromAuthority is null";
			throw new NullPointerException(errorText);
		}
		return "";
	}
	/* This routine tries to extract a host name from a servlet request. The servlet
	   request has zero or more HTTP headers associated with it. One of the headers
	   may be a HTTP Host header. The HTTP Host header will have a host name in it.
	   If the HTTP Host header can be found, the host name from the header will be
	   returned to the caller. */
	protected static String getHostNameFromRequest(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getHostNameFromRequest is null";
			throw new NullPointerException(errorText);
		}
		/* The code below tries to get the Host header from the request.
		   This does not always work. For the newest releases of Jetty
		   and HTTP2, the host header is not sent from the client or
		   built by the server. Note that the client does send a
		   pseudo-header authority header (':authority' without the
		   quotes). However, the pseudo-header can be accessed. */
		String  hostHeaderStr = null;
		String  hostName = null;
		/* Try to get the HTTP Host header from the request */
		hostHeaderStr = HDLmJetty.getHostHeader(request);
		if (hostHeaderStr != null) {
			hostName = HDLmJetty.getHostName(hostHeaderStr);
		  return hostName;
		}
		/* Sad to say, we did not get a Host header. This is an
		   HTTP2 problem. The client doesn't send a Host header
		   for HTTP2 and the server code does not create one. */
		String  authorityHeaderStr = null;
		authorityHeaderStr = HDLmJetty.getAuthorityHeader(request);
		if (authorityHeaderStr != null) {
			hostName = HDLmJetty.getHostNameFromAuthority(authorityHeaderStr);
		  return hostName;
		}
		/* Get the host name from the request */
		StringBuffer  urlStr = null;
		urlStr = request.getRequestURL();
		if (urlStr == null)
			return null;
		hostName = HDLmJetty.getHostName(urlStr.toString());
		return hostName;
	}
	/* Get JavaScript exceptions from a set of JSON. The overall purpose of
	   this routine is to note JavaScript exceptions when they occur. In
	   practice JavaScript exceptions are caught (using try/catch) and
	   sent to the server as post data. This routine checks the post data
	   for JavaScript exceptions and notes them. */
	protected static void  getJavaScriptExceptionsFromJson(final String jsonString) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonString == null) {
			String  errorText = "String containing JSON passed to getJavaScriptExceptionsFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Create a new JSON parser for use below */
	  JsonParser   parser = new JsonParser();
	  JsonElement  jsonElement = parser.parse(jsonString);
	  HDLmJetty.checkForJavaScriptExceptions(jsonElement);
  }
	/* This routine tries to return the value of the local port number of the
	   current request */
	protected static int getLocalPort(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getLocalPort is null";
			throw new NullPointerException(errorText);
		}
		/* Return the local port number */
		return request.getLocalPort();
	}
	/* This routine tries to return the value of the HTTP method (GET, POST, etc.)
	   of the current request */
	protected static String getMethod(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getMethod is null";
			throw new NullPointerException(errorText);
		}
		/* Return the HTTP method stored inside the request */
		return request.getMethod();
	}
	/* This routine returns the original path value to the caller. It turns out that no
	   existing API returns the original path value to the caller. This routine constructs
	   the original path value from other parts. */
	protected static String getOriginalPathValue(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getOriginalPathValue is null";
			throw new NullPointerException(errorText);
		}
		/* Get the base path value using a standard call */
		String  basePathValue = request.getRequestURI();
		/* Get the query string, which may be null */
		String  queryStr = request.getQueryString();
		if (queryStr != null) {
			basePathValue = basePathValue + '?' + queryStr;
		}
		/* Return the final path value value */
		return basePathValue;
	}
	/* This routine tries to return the value of the path value request string from the
     current request */
  protected static String getPathValueString(HttpServletRequest request) {
	  /* Check if the input request passed by the caller is null */
	  if (request == null) {
		  String  errorText = "Servlet request passed to getPathValueString is null";
		  throw new NullPointerException(errorText);
	  }
	  /* Return the path value request string */
	  return request.getRequestURI();
  }
	/* This routine tries to extract a port number from a string passed to it. The
	   passed string should be something like www.abc.com:80. This routine will only
	   return a proper integer if the last token in an integer and the token before
	   it is a colon. */
	protected static Integer getPortNumber(String inStr) {
		Integer portNumber = null;
		/* Check if the input string value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to getPortNumber is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<HDLmToken> tokens = HDLmString.getTokens(inStr);
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			int tokenCount = tokens.size();
			/* Remove the trailing sentinel token */
			tokenCount--;
			/* Check if we have enough tokens */
			if (tokenCount < 2)
				break;
			/* Get the last and next-to-last tokens */
			HDLmToken lastToken = tokens.get(tokenCount - 1);
			HDLmToken nextToLast = tokens.get(tokenCount - 2);
			/* Make sure the tokens have the right values */
			if (lastToken.getType() != HDLmTokenTypes.INTEGER)
				break;
			if (nextToLast.getType() != HDLmTokenTypes.OPERATOR)
				break;
			if (!nextToLast.getValue().equals(":"))
				break;
			portNumber = HDLmUtility.convertInteger(lastToken.getValue());
			break;
		}
		return portNumber;
	}
	/* This routine get the post payload (if possible) and returns the
	   post payload to the caller. The request original path value passed
	   to this routine must start with a leading forward slash. This routine
	   uses getReader to get a reader that returns the payload. As a
	   consequence, this routine can only be called once for each post. */
	protected static String getPostPayload(HttpServletRequest  request,
			                                   HDLmHttpTypes  httpType,
			                                   String  requestOriginalPathValue) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getPostPayload is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP type passed by the caller is null */
		if (httpType == null) {
			String  errorText = "HTTP type passed to getPostPayload is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the request original path value passed by the caller is null */
		if (requestOriginalPathValue == null) {
			String  errorText = "Request orginal path passed to getPostPayload is null";
			throw new NullPointerException(errorText);
		}
		String  requestPayload = null;
		/* Get the HTTP headers and the payload (if any) provided by the client
		   (probably a browser) */
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the correct type of HTTP request was passed by the caller */
		  if (httpType != HDLmHttpTypes.POST)
		  	break;
			/* The next request has a bad habit of failing with an
			   exception. It should never fail in any case. However,
			   it does fail for some unknown reason or reasons. As
			   a consequence, it must be run inside a try/catch block. */
			try {
				/* The code below used to work. It is by no means clear
				   why it doesn't work anymore. */
			  /* requestPayload = request.getParameter(postKey); */
				/* The next gets a reader that can be used to obtain the entire
				   post payload. We used to use a very different approach. It is
				   not clear why this no longer works. */
				BufferedReader  requestReader = request.getReader();
				/* The next line get the entire post response. The post response
				   may require several lines. The call below will combine them
				   all. */
			  requestPayload = requestReader.lines().collect(Collectors.joining(System.lineSeparator()));
			  requestPayload = URLDecoder.decode(requestPayload);
			}
			catch (Exception exception) {
				LOG.info("Exception while executing getPostPayload");
		 		LOG.info(exception.getMessage(), exception);
		 		HDLmEvent.eventOccurred("Exception");
				/* Just return to the caller if an exception occurred */
			  break;
			}
			break;
		}
		return requestPayload;
	}
	/* This routine tries to return the value of the query string (if any) from the
	   current request */
	protected static String getQueryString(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getQueryString is null";
			throw new NullPointerException(errorText);
		}
		/* Return the query string */
		return request.getQueryString();
	}
	/* This routine tries to get the referer (a misspelling of referrer) name
	   from the headers associated with a request. If anything goes wrong, a
	   null value will be returned to the caller. */
	protected static String  getReferrerHostName(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
		  String  errorText = "Servlet request passed to getReferrerHostName is null";
		  throw new NullPointerException(errorText);
		}
		String  referrerName = null;
		/* At this point we need to get the real host name. This is not the name
	     of the server (probably an AWS server) that will build the JavaScript
	     program, but the target HTML server. */
	  String  referrerHeaderName = "referer";
	  String  referrerHeaderStr = request.getHeader(referrerHeaderName);
		if (referrerHeaderStr == null) {
			String   errorFormat = "Header not obtained from the request (%s)";
			String   errorText = String.format(errorFormat, referrerHeaderName);
			HDLmAssertAction(false, errorText);
		}
	  String  referrerHostName = HDLmUtility.getHostNameFromUrlWithCheck(referrerHeaderStr);
	  if (referrerHostName == null) {
		  String   errorFormat = "Referrer host name not obtained from the request URL (%s)";
		  String   errorText = String.format(errorFormat, referrerHeaderName);
		  HDLmAssertAction(false, errorText);
	  }
	  referrerName = referrerHostName;
	  return referrerName;
	}
	/* This routine tries to get the referer (a misspelling of referrer) protocol
	   from the headers associated with a request. If anything goes wrong, a null
	   value will be returned to the caller. */
	protected static String  getReferrerProtocol(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
		  String  errorText = "Servlet request passed to getReferrerProtocol is null";
		  throw new NullPointerException(errorText);
		}
		String  referrerProtocolName = null;
		/* At this point we need to get the real header. This is not the name
	     of the server (probably an AWS server) that will build the JavaScript
	     program, but the target HTML server. */
	  String  referrerHeaderName = "referer";
	  String  referrerHeaderStr = request.getHeader(referrerHeaderName);
		if (referrerHeaderStr == null) {
			String   errorFormat = "Header not obtained from the request (%s)";
			String   errorText = String.format(errorFormat, referrerHeaderName);
			HDLmAssertAction(false, errorText);
		}
	  String  referrerProtocol = HDLmUtility.getProtocolFromUrl(referrerHeaderStr);
 	  if (referrerProtocol == null) {
		  String   errorFormat = "Referrer protocol not obtained from the request URL (%s)";
		  String   errorText = String.format(errorFormat, referrerHeaderName);
		  HDLmAssertAction(false, errorText);
	  }
	  referrerProtocolName = referrerProtocol;
	  return referrerProtocolName;
	}
	/* This routine tries to return all of the HTTP headers from a servlet request
	   instance. The list of headers might be empty (although this is unlikely).
	   This routine will always return a non-null ArrayList<String> to the caller. A
	   null value will never be returned. */
	protected static ArrayList<String>  getRequestHeaders(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getRequestHeaders is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<String> headersArray = new ArrayList<String>();
		/* Get and use all of the HTTP header names */
		Enumeration<String> headerTypeNames = request.getHeaderNames();
		while (headerTypeNames.hasMoreElements()) {
			String headerType = headerTypeNames.nextElement();
			/* Get all of the HTTP headers of a specific type */
			Enumeration<String> headers = request.getHeaders(headerType);
			while (headers.hasMoreElements()) {
				String header = headers.nextElement();
				header = headerType + ": " + header;
				headersArray.add(header);
			}
		}
		return headersArray;
	}
	/* This routine tries to read a servlet request payload. The payload is returned
	   to the caller as an array of bytes. Note that this routine will read the
	   entire payload and return the entire payload to the caller. This routine will
	   return a null value, if an exception occurs getting the input stream or
	   reading the bytes. This routine might also return an empty byte array. */
	protected static byte[] getRequestPayloadBinary(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getRequestPayloadBinary is null";
			throw new NullPointerException(errorText);
		}
		/* Get the request payload, if possible */
		byte[]        byteArray = null;
		InputStream   inputStream = null;
		try {
			inputStream = request.getInputStream();
			if (inputStream != null) {
				byteArray = inputStream.readAllBytes();
				inputStream.close();
				inputStream = null;
			}
		}
		catch (IOException ioException) {
			String pathValueString = request.getRequestURI();
			if (pathValueString != null)
			  LOG.info("Path value - " + pathValueString);
			LOG.info("IOException while executing getRequestPayloadBinary");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			byteArray = null;
			return null;
		}
		catch (Exception exception) {
			String pathValueString = request.getRequestURI();
			if (pathValueString != null)
			  LOG.info("Path value - " + pathValueString);
			LOG.info("Exception while executing getRequestPayloadBinary");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			byteArray = null;
			return null;
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException ioException) {
					String  pathValueString = request.getRequestURI();
					if (pathValueString != null)
					  LOG.info("Path value - " + pathValueString);
					LOG.info("IOException while executing getRequestPayloadBinary - finally");
					LOG.info(ioException.getMessage(), ioException);
					HDLmEvent.eventOccurred("IOException");
					return null;
				}
				catch (Exception exception) {
					String  pathValueString = request.getRequestURI();
					if (pathValueString != null)
					  LOG.info("Path value - " + pathValueString);
					LOG.info("Exception while executing getRequestPayloadBinary - finally");
					LOG.info(exception.getMessage(), exception);
					HDLmEvent.eventOccurred("Exception");
					return null;
				}
			}
		}
		return byteArray;
	}
	/* This routine tries to return the value of the remote IP address of the
     current request. The remote IP address known to the servlet may not
     actually be correct. We need to check the HTTP headers as well. If
     the remote IP address can not be found, this routien returns a null
     reference to the caller. */
	protected static String getRemoteIpAddress(HttpServletRequest request) {
		String  remoteIpAddress = null;
		/* The table below lists some of the alternative HTTP headers that
		   might have the actual client IP address. This table is not in use
		   at this time. */
	  final String possibleHeaders[] = { "X-Forwarded-For",
                                       "Proxy-Client-IP",
															         "WL-Proxy-Client-IP",
																       "HTTP_X_FORWARDED_FOR",
																       "HTTP_X_FORWARDED",
																       "HTTP_X_CLUSTER_CLIENT_IP",
																       "HTTP_CLIENT_IP",
																       "HTTP_FORWARDED_FOR",
																       "HTTP_FORWARDED",
																       "HTTP_VIA",
																       "REMOTE_ADDR" };
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getRemoteIpAddress is null";
			throw new NullPointerException(errorText);
		}
		/* Check for a header showing that the request was forwarded */
    String  forwards = request.getHeader("X-Forwarded-For");
    if (StringUtils.isNotBlank(forwards)) {
      /* Extract the remote IP address from the header */
    	int   commaIndex = forwards.indexOf(',');
    	if (commaIndex > 0)
    	  remoteIpAddress = StringUtils.substringBefore(forwards, ",");
    }
    /* We did not find the header we were looking for. We need to ask
       the servlet for the remote IP address. */
    else {
    	String  localIpAddress = request.getRemoteAddr();
    	if (StringUtils.isNotBlank(localIpAddress))
    		remoteIpAddress = localIpAddress;
    }
		/* Return the remote IP address or a null reference */
		return remoteIpAddress;
	}
	/* This routine tries to return the value of the remote IP address and
	   port number of the current request. The port number follows a colon
	   that separates it from the IP address. */
	protected static String getRemoteIpAndPort(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getRemoteIpAndPort is null";
			throw new NullPointerException(errorText);
		}
		/* Get the remote port number and IP address */
		int   remotePort = request.getRemotePort();
		String  remoteAddress = HDLmJetty.getRemoteIpAddress(request);
		if (remoteAddress == null)
			remoteAddress = "";
		String  remoteHost = request.getRemoteHost();
		/* Return the remote IP address  */
		return remoteAddress + ':' + Integer.toString(remotePort);
	}
	/* This routine tries to return the value of the remote port number of the
     current request */
	protected static int getRemotePort(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getRemotePort is null";
			throw new NullPointerException(errorText);
		}
		/* Return the remote port number */
		return request.getRemotePort();
	}
	/* This routine tries to read a servlet request payload. The payload is returned
	   to the caller as a Java character string. Note that this routine will read
	   the entire payload and return the entire payload to the caller. This routine
	   will return a null value, if an exception occurs getting the input stream or
	   reading the characters. This routine might also return an empty character
	   string. */
	protected static String getRequestPayloadChars(HttpServletRequest request) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getRequestPayloadChars is null";
			throw new NullPointerException(errorText);
		}
		/* Get the request payload as a character string, if possible */
		BufferedReader bufferedReader = null;
		String outString = null;
		try {
			bufferedReader = request.getReader();
			if (bufferedReader != null)
				outString = IOUtils.toString(request.getReader());
		}
		catch (IOException ioException) {
			String pathValueString = request.getRequestURI();
			if (pathValueString != null)
			  LOG.info("Path value - " + pathValueString);
			LOG.info("IOException while executing getRequestPayloadChars");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			outString = null;
			return null;
		}
		catch (Exception exception) {
			String pathValueString = request.getRequestURI();
			if (pathValueString != null)
			  LOG.info("Path value - " + pathValueString);
			LOG.info("Exception while executing getRequestPayloadChars");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			outString = null;
			return null;
		}
		finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (IOException ioException) {
					String pathValueString = request.getRequestURI();
					if (pathValueString != null)
					  LOG.info("Path value - " + pathValueString);
					LOG.info("IOException while executing getRequestPayloadChars - finally");
					LOG.info(ioException.getMessage(), ioException);
					HDLmEvent.eventOccurred("IOException");
					return null;
				}
				catch (Exception exception) {
					String pathValueString = request.getRequestURI();
					if (pathValueString != null)
					  LOG.info("Path value - " + pathValueString);
					LOG.info("Exception while executing getRequestPayloadChars - finally");
					LOG.info(exception.getMessage(), exception);
					HDLmEvent.eventOccurred("Exception");
					return null;
				}
			}
		}
		return outString;
	}
	/* This routine tries to return all of the HTTP headers from a servlet response
	   instance. The list of headers might be empty (although this is unlikely).
	   This routine will always return a non-null ArrayList<String> to the caller. A
	   null value will never be returned. */
	protected static ArrayList<String>  getResponseHeaders(HttpServletResponse response) {
		/* Check if the input response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to getResponseHeaders is null";
			throw new NullPointerException(errorText);
		}
		ArrayList<String> headersArray = new ArrayList<String>();
		/* Get and use all of the HTTP header names */
		Collection<String>  headerTypeNames = response.getHeaderNames();
		for (String headerTypeName:headerTypeNames) {
			/* Get all of the HTTP headers of a specific type */
			Collection<String> headers = response.getHeaders(headerTypeName);
			for (String header:headers) {
				header = headerTypeName + ": " + header;
				headersArray.add("");
			}
		}
		return headersArray;
	}
	/* This routine tries get session information from a cookie that should have
     been passed in by the browser client. This is a multi-step process. First,
     we have to find the correct cookie. Then we have to get session information
     from it. */
	protected static HDLmSession  getSessionInfo(HttpServletRequest request,
			                                         HDLmReturnNull returnNull) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getSessionInfo is null";
			throw new NullPointerException(errorText);
		}
	  /* Try to get the name of the session cookie. This should never fail.
	     However, you never know. */
	  String   cookieName = HDLmDefines.getString("HDLMSESSIONCOOKIE");
	  if (cookieName == null) {
	   	String   errorFormat = "Define value for session cookie name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSESSIONCOOKIE");
			HDLmAssertAction(false, errorText);
	  }
	  /* Try to get the actual cookie, now that we have the cookie name */
	  String   encryptedSessionIdJsonValue = HDLmJetty.getCookieExtended(request, cookieName);
	  String   plainTextSessionIdJsonValue = null;
	  /* The request to get a cookie value, may or may not have succeeded. If this
	     request failed, then we may need to report an error. However, we may be
	     able to return a null value to the caller. */
	  if (encryptedSessionIdJsonValue == null) {
	  	if (returnNull == HDLmReturnNull.RETURNNULLOK)
	  		return null;
	   	String   errorFormat = "Session cookie (%s) not found in HTTP headers";
			String   errorText = String.format(errorFormat, cookieName);
			HDLmAssertAction(false, errorText);
	  }
    /* Try to decrypt the cookie, if possible */
    if (encryptedSessionIdJsonValue != null) {
    	String  encryptionKeyValue = HDLmConfigInfo.getSecretEncryptionKey();
    	plainTextSessionIdJsonValue = HDLmEncryption.decrypt(encryptionKeyValue, 
    			                                                 encryptedSessionIdJsonValue);
    }
    /* Get the session ID from the decrypted session cookie */
    HDLmSession   tempSessionObj = HDLmSession.buildSession(plainTextSessionIdJsonValue);
  	String        sessionIdStr = tempSessionObj.getSessionId();  
    /* Get the session object from the session cache */   	
  	HDLmSession   sessionObj = HDLmSession.getFromCacheIfPresent(sessionIdStr); 
	  if (sessionObj == null) {
	   	String   errorFormat = "Session object not obtained using session cookie";
			String   errorText = String.format(errorFormat);
			HDLmAssertAction(false, errorText);
	  }
		return sessionObj;
	}
	/* The code below gets and sets a few Jetty related system properties. It does
	   not appear that this code is needed. */
	protected static void getSetSystemProperties() {
		String jetty_home = System.getProperty("jetty.home", "../jetty-distribution-9.4.18.v20190429");
		System.setProperty("jetty.home", jetty_home);
	}
	/* Get the password of the standard Key Store and return it to the caller */
	protected static String getStandardKeyStorePassword() {
		return "headlamp";
	}
	/* Get the path of the standard Key Store and return it to the caller */
	protected static String getStandardKeyStorePath() {
		/* First check if we are inside a Docker container. Special
	     case code is needed if a Docker container is active. */
    if (HDLmMain.isDockerFlagSet() == false) {
    	if (HDLmMain.checkProductionSystem()) {
    		if (LOG.isDebugEnabled())
    		  LOG.debug("In HDLmJetty.getStandardKeyStorePath");
    		return "../../letsencrypt/headlamp.jks";
    	}
    	else
    	  return "../../letsencrypt/headlamp.jks";
  	}
	  /* Looks like we are running inside a Docker container. The Java
	     key store is in the current working directory. */
    else {
    	return "headlamp.jks";
    }
	}
	/* This routine tries to return the value of the User-Agent header in the
	   current request. This value may or may not be available. If the value is
	   available it is returned to the caller. */
	protected static String getUserAgentHeader(HttpServletRequest request) {
		String hostValue = null;
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to getUserAgentHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Get the User-Agent header, if possible */
		String headerType = "User-Agent";
		String headerStr = request.getHeader(headerType);
		hostValue = headerStr;
		return hostValue;
	}
	/* This routine tries to return the values of the user name and password
	   provided in a standard header */
	protected static HDLmUtilityResponse   getUseridPassword(HttpServletRequest request) {
	  /* Check if the input request passed by the caller is null */
	  if (request == null) {
		  String  errorText = "Servlet request passed to getUseridPassword is null";
		  throw new NullPointerException(errorText);
	  }
	  /* Try to get the actual authorization header from the request */
	  String  authHeader = request.getHeader("Authorization");
		if (authHeader == null) {
			String  errorText = "Authorization header not found in request";
			HDLmAssertAction(false, errorText);
		}
    /* Split the authorization header value into several parts */
		int   blankIndex;
		blankIndex = authHeader.indexOf(' ');
		if (blankIndex < 0) {
			String  errorText = "Blank not found in authorization header";
			HDLmAssertAction(false, errorText);
		}
    /* Get the part of the authorization header we need/want */
    String  authHeaderPart = authHeader.substring(blankIndex+1);
    /* Convert the part of the ID token to a JSON string */
    byte[]  authHeaderPartBytes = Base64.getDecoder().decode(authHeaderPart);
    String  authHeaderPartStr = new String(authHeaderPartBytes);
    /* Split the authorization header value into several parts */
    blankIndex = authHeaderPartStr.indexOf(':');
		if (blankIndex < 0) {
			String  errorText = "Colon not found in authorization header part";
			HDLmAssertAction(false, errorText);
		}
    /* Get the user name and password from the authorization header */
    String  userNameStr = authHeaderPartStr.substring(0, blankIndex);
		if (userNameStr == null || userNameStr.length() == 0) {
			String  errorText = "User name not found in authorization header part";
			HDLmAssertAction(false, errorText);
		}
    String  passwordStr = authHeaderPartStr.substring(blankIndex+1);
		if (passwordStr == null || passwordStr.length() == 0) {
			String  errorText = "Password not found in authorization header part";
			HDLmAssertAction(false, errorText);
		}
		/* Create a new output area for the values we get from JSON */
		HDLmUtilityResponse   outputArea = new HDLmUtilityResponse();
		outputArea.setUserNameValue(userNameStr);
		outputArea.setPasswordValue(passwordStr);
	  /* Return the output area to the caller */
	  return outputArea;
	}
	/* This routine handles certain action commands that can be
 	   sent using a browser. The commands are routed as need be
 	   and the response is set back to the browser. */
	protected static void handleActionCommands(String pathValueString,
						                                 String hostName,
						                                 HttpServletRequest request,
						                                 HttpServletResponse response,
						                                 String clientStr,
						                                 String timeStamp) {
		/* Check if the path value string passed by the caller is null */
		if (pathValueString == null) {
			String  errorText = "Path value string passed to handleActionCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name passed to handleActionCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleActionCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleActionCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the client string passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to handleActionCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string passed to handleActionCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		boolean   displayOutput = true;
		String    rv = "";
		/* Check which command the caller passed. Invoke the appropriate action
		   command routine. The action command routines return a string with the
		   correct HTML in it. This routine handles the rest of the Jetty response. */
		if (pathValueString.startsWith("/passthru-check"))
			rv = HDLmMain.checkPassThru(hostName, clientStr, request);
		else if (pathValueString.startsWith("/passthru-display")) {
			rv = HDLmMain.displayPassThruCmdResponse(hostName,
											                         clientStr,
											                         request,
											                         response);
			displayOutput = false;
		}
		else if (pathValueString.startsWith("/passthru-disable"))
			rv = HDLmMain.disablePassThruCmdResponse(hostName, clientStr);
		else if (pathValueString.startsWith("/passthru-enable"))
			rv = HDLmMain.enablePassThruCmdResponse(hostName, clientStr);
		else if (pathValueString.startsWith("/passthru-get"))
			rv = HDLmMain.getPassThruStatusCmdResponse(hostName,
                                                 clientStr,
                                                 request,
                                                 response);
		else {
			String  errorFormat = "Invalid action command (%s) sent to server action command handler";
			String  errorText = String.format(errorFormat,  pathValueString);
			HDLmAssertAction(false, errorText);
		}
		/* Now that we have the HTML command output, we need to send the HTML
		   command output back to the browser. The code below does this. */
		try {
			if (displayOutput) {
			  response.setStatus(HttpStatus.OK_200);
			  response.getWriter().print(rv);
			  response.getWriter().flush();
			  response.setContentType("text/html");
			}
		}
		catch (Exception exception) {
			if (pathValueString != null)
			  LOG.info("Path value - " + pathValueString);
			LOG.info("Exception while executing handleActionCommands");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	}
	/* This routine handles certain contents information commands that can be
     sent using a browser. The commands are routed as need be and the
     response is set back to the browser. */
	protected static void handleContentsCommands(String pathValueString,
			                                         String hostName,
			                                         HttpServletRequest request,
			                                         HttpServletResponse response,
			                                         String clientStr,
			                                         String timeStamp) {
		String  rv = "";
		/* Check if the path value string passed by the caller is null */
		if (pathValueString == null) {
			String  errorText = "Path value string passed to handleContentsCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name passed to handleContentsCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleContentsCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleContentsCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the client string passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to handleContentsCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string passed to handleContentsCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check which command the caller passed. Invoke the appropriate contents
		   command routine. The contents command routines return a string with the
		   correct HTML in it. This routine handles the rest of the Jetty response. */
		if (pathValueString.startsWith("/appthr-contents"))
			rv = HDLmMain.appthrContents(hostName, clientStr);
		else if (pathValueString.startsWith("/cluster-contents"))
			rv = HDLmMain.clustersContents(hostName, clientStr);
		else if (pathValueString.startsWith("/memory-contents"))
			rv = HDLmMain.memoryContents(hostName, clientStr);
		else if (pathValueString.startsWith("/phash-contents"))
			rv = HDLmMain.phashContents(hostName, clientStr);
		else if (pathValueString.startsWith("/sessionId-contents"))
			rv = HDLmMain.sessionIdContents(hostName, clientStr);
		else if (pathValueString.startsWith("/systhr-contents"))
			rv = HDLmMain.systhrContents(hostName, clientStr);
		else {
			String  errorFormat = "Invalid contents command (%s) sent to common contents command handler";
			String  errorText = String.format(errorFormat,  pathValueString);
			HDLmAssertAction(false, errorText);
		}
		/* Now that we have the HTML command output, we need to send the HTML
		   command output back to the browser. The code below does this. */
		try {
			 response.setStatus(HttpStatus.OK_200);
			 response.getWriter().print(rv);
			 response.getWriter().flush();
			 response.setContentType("text/html");
		}
		catch (Exception exception) {
			if (pathValueString != null)
			  LOG.info("Path value - " + pathValueString);
			LOG.info("Exception while executing handleContentsCommands");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	}
	/* This routine handles certain contents information commands that can be
	   sent using a browser. The commands are routed as need be and the
	   response is set back to the browser. */
	protected static void handleGetData(String pathValueString,
                                      String hostName,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      String clientStr,
                                      String timeStamp) {
		/* Create a new string builder for the output HTML */
		StringBuilder  rv = new StringBuilder();
		/* Check if the path value string passed by the caller is null */
		if (pathValueString == null) {
			String  errorText = "Path value string passed to handleGetData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name passed to handleGetData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleGetData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleGetData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the client string passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to handleGetData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string passed to handleGetData is null";
			throw new NullPointerException(errorText);
		}
		/* Start the main div */
		rv.append("<div>");
		/* Build the rule trace information heading HTML */
		String  ruleName = request.getParameter("name");
		String  infoType = "trace";
		String  prefixStr = "Rule";
		String  headingHtml = HDLmHtml.headingHtml(ruleName,
				                                       prefixStr,
                                               infoType,
				                                       clientStr);
		rv.append(headingHtml);
		/* Start a pre. The preformatted area is used for the actual JSON
		   string. */
		rv.append("<pre>");
		/* Get the JSON from the inbound GET request */
		String  jsonStr = request.getParameter("json");
		/* The JSON is pretty-printed using gson */
		Gson gson = HDLmMain.gsonPrettyPrintingMain;
		JsonParser    jsonParser = new JsonParser();
		JsonElement   jsonElement = jsonParser.parse(jsonStr);
		String        jsonStringPretty = gson.toJson(jsonElement);
		/* Add the pretty-printed JSON to the output HTML */
		rv.append(jsonStringPretty);
		/* End the preformatted area */
		rv.append("</pre>");
		/* End the main div */
		rv.append("</div>");
		/* Now that we have the HTML command output, we need to send the HTML
		   command output back to the browser. The code below does this. */
		try {
			 response.setStatus(HttpStatus.OK_200);
			 response.getWriter().print(rv.toString());
			 response.getWriter().flush();
			 response.setContentType("text/html");
		}
		catch (Exception exception) {
			if (pathValueString != null)
			  LOG.info("Path value - " + pathValueString);
			LOG.info("Exception while executing handleContentsCommands");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	}
	/* This routine handles certain requests to invoke an API from the client. These
	   POST requests are used to invoke API using data provided by the caller. */
	protected static void handleInvokeApi(HttpServletRequest request,
			                                  HttpServletResponse response,
			                                  String hostName,
			                                  String requestPostPayload,
			                                  String timeStamp,
	                                      HDLmHttpTypes httpType,
	                                      String requestOriginalPathValue) {
		/* LOG.info("In HDLmJetty.handleInvokeApi"); */
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name string passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP type passed by the caller is null */
		if (httpType == null) {
			String  errorText = "HTTP type passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP operation type passed by the caller is valid */
		if (httpType == HDLmHttpTypes.NONE) {
			String  errorText = "HTTP type passed to handleInvokeApi is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the request original path value passed by the caller is null */
		if (requestOriginalPathValue == null) {
			String  errorText = "Request orginal path passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the inbound request has an active session. If yes, extract
		   the session and set a session object to a non-null value. If not,
		   create the session for later use. */
		HDLmSession   sessionObj = HDLmJetty.checkForSession(request);
		/* LOG.info("Session active - " + ((Boolean) (sessionObj != null)).toString()); */
		if (sessionObj == null) {
			/* Create a new session object for use in this routine */
			sessionObj = new HDLmSession();
			/* Get a session ID and store it in thie new session object */
			String   sessionIdStr = UUID.randomUUID().toString();
			sessionObj.setSessionId(sessionIdStr);
			
			/* Get a string with a random double in it */
			double  randomDouble = HDLmUtility.getRandomDouble();
			String  randomDoubleStr = String.valueOf(randomDouble);		
			/* Set the index value string to a random value */
		  sessionObj.setIndex(randomDoubleStr);
			/* Get an empty set of doubles and store them in the session object
			   in string form */
			ArrayList<Double>  emptyDoubleArrayList = new ArrayList<Double>();
			String             emptyDoubleArrayListStr = HDLmMain.getParametersStr(emptyDoubleArrayList);
			sessionObj.setParameters(emptyDoubleArrayListStr);
			Integer   cookieMaxAge = HDLmConfigInfo.getCookieMaxAge();
			String    cookieSameSite = "None"; 
			String    plainTextSessionIdJsonValue = sessionObj.buildJsonIdOnly();
			String    encryptionKeyValue = HDLmConfigInfo.getSecretEncryptionKey();
			String    encryptedSessionIdJsonValue = HDLmEncryption.encrypt(encryptionKeyValue, 
					                                                           plainTextSessionIdJsonValue);
			HDLmJetty.storeCookie(response, 
					                  encryptedSessionIdJsonValue, 
					                  cookieMaxAge,
					                  cookieSameSite);
			/* Add the session object to the cache */			 
			HDLmSession.addToCache(sessionIdStr, sessionObj);
		}
		/* The allow all header has not been added to the response */
		boolean   allowAll = false;
		/* The response has not been flushed */
		boolean   flushExecuted = false;
		/* We need to keep track of how long it took to handle the current
		   operation. We need to save the current instant in time to do this. */
	  Instant   instantStart = Instant.now();
	  String    errorMessageHeader = "x-amzn-ErrorMessage";
	  String    hostNameStr = null;
	  String    requestPayload = requestPostPayload;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
	    String  checkLastTimeApiName = HDLmDefines.getString("HDLMAPICHECKLASTTIME");
	    if (checkLastTimeApiName == null) {
	   	  String   errorFormat = "Define value for last time API name not found (%s)";
			  String   errorText = String.format(errorFormat, "HDLMAPICHECKLASTTIME");
			  HDLmAssertAction(false, errorText);
	    }
	    String  checkUserNamePasswordApiName = HDLmDefines.getString("HDLMAPICHECKUSERNAMEPASSWORD");
	    if (checkUserNamePasswordApiName == null) {
	   	  String   errorFormat = "Define value for check API name not found (%s)";
			  String   errorText = String.format(errorFormat, "HDLMAPICHECKUSERNAMEPASSWORD");
			  HDLmAssertAction(false, errorText);
	    }
	    String  getUserApiName = HDLmDefines.getString("HDLMAPIGETUSER");
	    if (getUserApiName == null) {
	   	  String   errorFormat = "Define value for get user API name not found (%s)";
			  String   errorText = String.format(errorFormat, "HDLMAPIGETUSER");
			  HDLmAssertAction(false, errorText);
	    }
	    String  setLastTimeName = HDLmDefines.getString("HDLMSETLASTTIME");
	    if (setLastTimeName == null) {
	   	  String   errorFormat = "Define value for last time API name not found (%s)";
			  String   errorText = String.format(errorFormat, "HDLMSETLASTTIME");
			  HDLmAssertAction(false, errorText);
	    }
	    String  setPasswordApiName = HDLmDefines.getString("HDLMAPISETPASSWORD");
	    if (setPasswordApiName == null) {
	   	  String   errorFormat = "Define value for set password API name not found (%s)";
			  String   errorText = String.format(errorFormat, "HDLMAPISETPASSWORD");
			  HDLmAssertAction(false, errorText);
	    }
	    String  verifyApiName = HDLmDefines.getString("HDLMAPIVERIFYCODE");
	    if (verifyApiName == null) {
	   	  String   errorFormat = "Define value for verify code API name not found (%s)";
			  String   errorText = String.format(errorFormat, "HDLMAPIVERIFYCODE");
			  HDLmAssertAction(false, errorText);
	    }
			String  parmName;
			/* Check if we actually obtained a post payload. We have no more
			   work to do if we did not obtain an actual post payload. */
			if (requestPayload == null)
				break;
			/* Find out what API we need to invoke */
			parmName = request.getParameter("Name");
			/* LOG.info("In HDLmJetty.handleInvokeApi - parmName - " + parmName); */
			/* Get an output writer for use below */
			PrintWriter   outWriter = null;
			try {
				outWriter = response.getWriter();
			}
			catch (Exception exception) {
				LOG.info("API name - " + parmName);
				LOG.info("Exception while executing handleInvokeApi");
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
				return;
			}
			/* Check for the last time API call. This call is used to determine if
		     the client verification code UI is needed or not. */
			if (parmName.equals(checkLastTimeApiName)) {
				/* Create a new JSON parser for use below */
		    JsonParser    parser = new JsonParser();
		    /* Make sure the inbound payload has the required key */
		    JsonElement   jsonElement = parser.parse(requestPayload);
			  /* Check if the JSON message passed by the caller is valid */
				if (!jsonElement.isJsonObject()) {
			 	  String  errorText = "JSON string in handleInvokeApi for last time is invalid";
			 	  HDLmAssertAction(false, errorText);
			  }
		    boolean   hasUserNameKey = HDLmJson.hasJsonKey(jsonElement, "Username");
				if (hasUserNameKey == false) {
					String  errorText = "Inbound JSON data does not have Username key";
					HDLmAssertAction(false, errorText);
				}
				/* Get some information passed by the client */
				String    userNameStr = HDLmJson.getJsonString(jsonElement, "Username");
				boolean   lastTimeFailure = HDLmSecurity.checkLastTimeFailure(userNameStr, null);
				/* LOG.info("In HDLmJetty.handleInvokeApi - lastTimeFailure - " + ((Boolean) lastTimeFailure).toString()); */
				/* Check if some type of error was found above. Set a special error
				   message if need be. */
				if (lastTimeFailure)
		      response.setHeader(errorMessageHeader, "Last time value not found or invalid");
				/* Add the Access-Control-Expose-Headers header */
				HDLmJetty.handleResponseExposeHeaders(response);
				/* Add the Access-Control-Allow-Origin header */
				HDLmJetty.handleResponseAllowAllOrigins(request, response);
				allowAll = true;
	   	  outWriter.flush();
	   	  flushExecuted = true;
			}
			/* Check for the check user name and password API */
			if (parmName.equals(checkUserNamePasswordApiName)) {
				/* Create a new JSON parser for use below */
		    JsonParser    parser = new JsonParser();
		    /* Make sure the inbound payload has the required key */
		    JsonElement   jsonElement = parser.parse(requestPayload);
			  /* Check if the JSON message passed by the caller is valid */
				if (!jsonElement.isJsonObject()) {
			 	  String  errorText = "JSON string in handleInvokeApi for check user name and password is invalid";
			 	  HDLmAssertAction(false, errorText);
			  }
		    boolean       hasAuthParametersKey = HDLmJson.hasJsonKey(jsonElement, "AuthParameters");
				if (hasAuthParametersKey == false) {
					String  errorText = "Inbound JSON data does not have AuthParameters key";
					HDLmAssertAction(false, errorText);
				}
		    /* Get the user name and the password */
				JsonElement   authParameters = HDLmJson.getJsonObject(jsonElement, "AuthParameters");
			  /* Check if the authentication parameters are a valid JSON object */
				if (!jsonElement.isJsonObject()) {
			 	  String  errorText = "JSON authentication parameters in handleInvokeApi for check user name and password is invalid";
			 	  HDLmAssertAction(false, errorText);
			  }
		    boolean       hasUsernameKey = HDLmJson.hasJsonKey(authParameters, "USERNAME");
				if (hasUsernameKey == false) {
					String  errorText = "Inbound JSON data does not have USERNAME key in AuthParameters";
					HDLmAssertAction(false, errorText);
				}
		    boolean       hasPasswordStrKey = HDLmJson.hasJsonKey(authParameters, "PASSWORD");
				if (hasPasswordStrKey == false) {
					String  errorText = "Inbound JSON data does not have PASSWORD key in AuthParameters";
					HDLmAssertAction(false, errorText);
				}
		    String        userNameStr = HDLmJson.getJsonString(authParameters, "USERNAME");
		    String        passwordStr = HDLmJson.getJsonString(authParameters, "PASSWORD");
		    /* In some cases, we can skip going to AWS Cognito to do the actual user name
		       and password check. The AWS Cognito user name and password check will send
		       an SMS message to the user's phone with a verification code. This code is
		       not needed in all cases. In some cases, we can user internal checks instead. */
		    boolean       checkLastTimeFailure = true;
		    boolean       checkMatch = false;
		    boolean       useActualCognitoCheck = false;
		    boolean       checkUserNameExists = false;
		    /* Check if the user name and password passed by the caller using the
	         authorization header match the memory data base */
		    if (useActualCognitoCheck == false) {
		    	boolean   traceCheck = false;
          checkMatch = HDLmSecurity.checkMatchingUsernamePassword(userNameStr,
   	  	                                                          passwordStr,
   	  	                                                          traceCheck);
          if (checkMatch == false) {
        	  useActualCognitoCheck = true;
        	  /* LOG.info("In HDLmJetty.handleInvokeApi - check password"); */
        	  /* LOG.info(passwordStr); */
        	  /* Redo the password match check with trace turned off */
        	  traceCheck = false;
            checkMatch = HDLmSecurity.checkMatchingUsernamePassword(userNameStr,
                                                                    passwordStr,
                                                                    traceCheck);
          }
		    }
  	    /* Check if the information in the memory database is too old */
        if (useActualCognitoCheck == false) {
  		    checkLastTimeFailure = HDLmSecurity.checkLastTimeFailure(userNameStr,
  		                                                             null);
  		    if (checkLastTimeFailure == true) {
  		  	  useActualCognitoCheck = true;
  		  	  /* LOG.info("In HDLmJetty.handleInvokeApi - check last time"); */
  		  	  /* LOG.info(userNameStr); */
  		    }
        }
		    /* Check if we got a session object earlier */
  		  if (useActualCognitoCheck == false) {
		      if (sessionObj == null) {
		    	  useActualCognitoCheck = true;
		    	  /* LOG.info("In HDLmJetty.handleInvokeApi - no session object"); */
		      }
  		  }
		    /* Check if the user name actually exists at this point
		       in time. The user name might have been deleted (by hand
		       or by using a program). We need to detect this case.

		       This check may be a bridge too far. This check is commented
		       out for now. */
  		  /*
		    if (useActualCognitoCheck == false) {
		      checkUserNameExists = HDLmSecurity.checkUsernameExists(userNameStr);
		      if (checkUserNameExists == false) {
		    	  useActualCognitoCheck = true;
		    	  LOG.info("In HDLmJetty.handleInvokeApi - user name exists", userNameStr);
		    	}
		    }
		    */
		    /* Invoke the Cognito check user name and password API.
		       We do this in all cases, even if the use actual value
		       is false. We do this so that the phone number, challenge
		       name, and session are always sent back to the client.

		       The above comment is not quite right. If the password
		       can be checked and is correct and if we can pass the
		       last time check, then we really don't want to send
		       verification code information back to the client. */
		    if (useActualCognitoCheck == true) {
			    ArrayList<String>   outHeaders;
			    HDLmApacheResponse  outResponse;
			    int                 outCode;
			    String              outStr;
			    outResponse = HDLmSecurity.checkUsernamePassword(userNameStr, passwordStr);
			    outCode = outResponse.getStatusCode();
			    outHeaders = outResponse.getHeaders();
			    outStr = outResponse.getStringContent();
			    /* Check if we obtained a response from the check user name and password routine */
			    if (outStr == null) {
			    	response.setHeader(errorMessageHeader, "No response was received from the check API call");
			    	response.setStatus(HttpStatus.BAD_REQUEST_400);
			    }
			    /* We get a response from the Cognito API */
			    else {
			    	/* Look for an Amazon error message header */
			    	for (String headerStr : outHeaders) {
			    		if (headerStr.startsWith(errorMessageHeader)) {
			    			int     headerIndx = headerStr.indexOf(' ');
			    			String  headerValue = headerStr.substring(headerIndx+1);
			    			response.setHeader(errorMessageHeader, headerValue);
			    			break;
			    		}
			    	}
			    	/* Update the user name / password, if the current request worked */
			    	if (outCode == HttpStatus.OK_200)
			    		HDLmSecurity.updatePassword(userNameStr, passwordStr);
						/* Send the JSON back to the invoker */
			    	response.setStatus(outCode);
			    	response.setContentType("application/json");
			    	response.setCharacterEncoding("UTF-8");
			    	outWriter.print(outStr);
			    }
		    }
		    /* We skipped the actual AWS Cognito user name and password check */
		    else {}
				/* Add the Access-Control-Expose-Headers header */
				HDLmJetty.handleResponseExposeHeaders(response);
				/* Add the Access-Control-Allow-Origin header */
				HDLmJetty.handleResponseAllowAllOrigins(request, response);
				allowAll = true;
	    	outWriter.flush();
	    	flushExecuted = true;
			}
			/* Check for the admin get user API */
			if (parmName.equals(getUserApiName)) {
				/* Create a new JSON parser for use below */
		    JsonParser    parser = new JsonParser();
		    /* Make sure the inbound payload has the required key */
		    JsonElement   jsonElement = parser.parse(requestPayload);
			  /* Check if the JSON message passed by the caller is valid */
				if (!jsonElement.isJsonObject()) {
			 	  String  errorText = "JSON string in handleInvokeApi for get user is invalid";
			 	  HDLmAssertAction(false, errorText);
			  }
		    boolean       hasUserPoolIdKey = HDLmJson.hasJsonKey(jsonElement, "UserPoolId");
				if (hasUserPoolIdKey == false) {
					String  errorText = "Inbound JSON data does not have UserPoolId key";
					HDLmAssertAction(false, errorText);
				}
		    boolean       hasUserNameKey = HDLmJson.hasJsonKey(jsonElement, "UserName");
				if (hasUserNameKey == false) {
					String  errorText = "Inbound JSON data does not have UserName key";
					HDLmAssertAction(false, errorText);
				}
			  /* Get the user name and the user pool ID from the inbound JSON */
				String  userName = HDLmJson.getJsonString(jsonElement, "UserName");
				String  userPoolId = HDLmJson.getJsonString(jsonElement, "UserPoolId");
		    /* Invoke the Cognito admin get user password API */
		    ArrayList<String>   outHeaders;
		    HDLmApacheResponse  outResponse;
		    int                 outCode;
		    String              outStr;
		    outResponse = HDLmSecurity.getUser(userPoolId,
		    		                               userName);
		    outCode = outResponse.getStatusCode();
		    outHeaders = outResponse.getHeaders();
		    outStr = outResponse.getStringContent();
		    /* LOG.info("In HDLmJetty.handleInvokeApi - outCode - " + ((Integer) outCode).toString()); */
		    /* LOG.info("In HDLmJetty.handleInvokeApi - outStr - " + outStr); */
		    /* Check if we obtained a response from the get user information routine */
		    if (outStr == null) {
		    	response.setHeader(errorMessageHeader, "No response was received from the get user API call");
		    	response.setStatus(HttpStatus.BAD_REQUEST_400);
		    }
		    /* We get a response from the Cognito API */
		    else {
		    	/* Look for an Amazon error message header */
		    	for (String headerStr : outHeaders) {
		    		if (headerStr.startsWith(errorMessageHeader)) {
		    			int     headerIndx = headerStr.indexOf(' ');
		    			String  headerValue = headerStr.substring(headerIndx+1);
		    			response.setHeader(errorMessageHeader, headerValue);
		    			break;
		    		}
		    	}
					/* Create a new JSON parser for use below */
			    JsonParser    parses = new JsonParser();
			    /* Make sure the get user response has the required key */
			    JsonElement   jsonElemenu = parser.parse(outStr);
				  /* Check if the JSON message passed by the caller is valid */
					if (!jsonElement.isJsonObject()) {
				 	  String  errorText = "JSON string in handleInvokeApi for admin get user is invalid";
				 	  HDLmAssertAction(false, errorText);
				  }
			    boolean   hasUserAttributesKey = HDLmJson.hasJsonKey(jsonElemenu, "UserAttributes");
					if (hasUserAttributesKey == false) {
						String  errorText = "Inbound JSON data does not have UserAttributes key";
						HDLmAssertAction(false, errorText);
					}
					JsonArray   userAttributesArray = HDLmJson.getJsonArray(jsonElemenu, "UserAttributes");
			    /* Check if the JSON array is valid or not */
					if (!userAttributesArray.isJsonArray()) {
				 	  String  errorText = "JSON array in handleInvokeApi is invalid";
				 	  HDLmAssertAction(false, errorText);
				  }
		    	int       userAttributesArrayLen = userAttributesArray.size();
		    	/* Convert the JSON node path array to a standard Java ArrayList */
		    	for (int j = 0; j < userAttributesArrayLen; j++) {
		    		/* Get the current entry in the JSON array */
		    		JsonElement  userAttributesArrayEntry = userAttributesArray.get(j);
		    		/* Make sure the entry has a Name property */
				    boolean   hasNameKey = HDLmJson.hasJsonKey(userAttributesArrayEntry, "Name");
						if (hasUserAttributesKey == false) {
							String  errorText = "JSON array entry does not have Name key";
							HDLmAssertAction(false, errorText);
						}
						/* Get the actual name and check it */
						String  nameValue = HDLmJson.getJsonString(userAttributesArrayEntry, "Name");
						if (!nameValue.contentEquals("custom:Scope"))
							continue;
		    		/* Make sure the entry has a Value property */
				    boolean   hasValueKey = HDLmJson.hasJsonKey(userAttributesArrayEntry, "Value");
						if (hasUserAttributesKey == false) {
							String  errorText = "JSON array entry does not have Value key";
							HDLmAssertAction(false, errorText);
						}
						/* Get the actual value for the scope */
						String  scopeValue = HDLmJson.getJsonString(userAttributesArrayEntry, "Value");
						/* Store the scope vaue in the memory database */
						HDLmSecurity.updateScope(userName, scopeValue);
						break;
		    	}
					/* Send the JSON back to the invoker */
		    	response.setStatus(outCode);
		    	response.setContentType("application/json");
		    	response.setCharacterEncoding("UTF-8");
		    	outWriter.print(outStr);
		    }
				/* Add the Access-Control-Expose-Headers header */
				HDLmJetty.handleResponseExposeHeaders(response);
				/* Add the Access-Control-Allow-Origin header */
				HDLmJetty.handleResponseAllowAllOrigins(request, response);
				allowAll = true;
	    	outWriter.flush();
	    	flushExecuted = true;
			}
			/* Check for the set last time invocation */
			if (parmName.equals(setLastTimeName)) {
				/* Create a new JSON parser for use below */
		    JsonParser    parser = new JsonParser();
		    /* Make sure the inbound payload has the required key */
		    JsonElement   jsonElement = parser.parse(requestPayload);
			  /* Check if the JSON message passed by the caller is valid */
				if (!jsonElement.isJsonObject()) {
			 	  String  errorText = "JSON string in handleInvokeApi for get user is invalid";
			 	  HDLmAssertAction(false, errorText);
			  }
		    boolean       hasUserNameKey = HDLmJson.hasJsonKey(jsonElement, "UserName");
				if (hasUserNameKey == false) {
					String  errorText = "Inbound JSON data does not have UserName key";
					HDLmAssertAction(false, errorText);
				}
			  /* Get the user name from the inbound JSON */
				String  userName = HDLmJson.getJsonString(jsonElement, "UserName");
		    int                 outCode = HttpStatus.OK_200;
		    String              outStr;
	    	/* Reset the last time used to the current time */
		    Instant   instant = Instant.now();
		    String    instantStr = instant.toString();
		    HDLmSecurity.updateLastTime(userName, instantStr);
		    /* Create a new JSON object for use below */
				JsonObject  newJsonObject = new JsonObject();
				HDLmJson.setJsonString(newJsonObject, "LastTime", instantStr);
				/* Convert the object to a JSON string */
			  Gson     gsonInstance = HDLmMain.gsonMain;
				String   jsonValueStr = gsonInstance.toJson(newJsonObject);
				/* Send the JSON back to the invoker */
	    	response.setStatus(outCode);
	    	response.setContentType("application/json");
	    	response.setCharacterEncoding("UTF-8");
	    	outWriter.print(jsonValueStr);
				/* Add the Access-Control-Expose-Headers header */
				HDLmJetty.handleResponseExposeHeaders(response);
				/* Add the Access-Control-Allow-Origin header */
				HDLmJetty.handleResponseAllowAllOrigins(request, response);
				allowAll = true;
	    	outWriter.flush();
	    	flushExecuted = true;
			}
			/* Check for the set user password API */
			if (parmName.equals(setPasswordApiName)) {
				/* Create a new JSON parser for use below */
		    JsonParser    parser = new JsonParser();
		    /* Make sure the inbound payload has the required key */
		    JsonElement   jsonElement = parser.parse(requestPayload);
			  /* Check if the JSON message passed by the caller is valid */
				if (!jsonElement.isJsonObject()) {
			 	  String  errorText = "JSON string in handleInvokeApi for set password is invalid";
			 	  HDLmAssertAction(false, errorText);
			  }
		    boolean       hasUserPoolIdKey = HDLmJson.hasJsonKey(jsonElement, "UserPoolId");
				if (hasUserPoolIdKey == false) {
					String  errorText = "Inbound JSON data does not have UserPoolId key";
					HDLmAssertAction(false, errorText);
				}
		    boolean       hasUserNameKey = HDLmJson.hasJsonKey(jsonElement, "UserName");
				if (hasUserNameKey == false) {
					String  errorText = "Inbound JSON data does not have UserName key";
					HDLmAssertAction(false, errorText);
				}
		    boolean       hasPasswordKey = HDLmJson.hasJsonKey(jsonElement, "Password");
				if (hasPasswordKey == false) {
					String  errorText = "Inbound JSON data does not have Password key";
					HDLmAssertAction(false, errorText);
				}
		    /* Get the needed values from the inbound JSON */
				String        userPoolId = HDLmJson.getJsonString(jsonElement, "UserPoolId");
		    String        userNameStr = HDLmJson.getJsonString(jsonElement, "UserName");
		    String        passwordStr = HDLmJson.getJsonString(jsonElement, "Password");
		    /* Invoke the Cognito set user password API */
		    ArrayList<String>   outHeaders;
		    HDLmApacheResponse  outResponse;
		    int                 outCode;
		    String              outStr;
		    outResponse = HDLmSecurity.setPassword(userPoolId,
		    		                                   userNameStr,
		    		                                   passwordStr);
		    outCode = outResponse.getStatusCode();
		    outHeaders = outResponse.getHeaders();
		    outStr = outResponse.getStringContent();
		    /* LOG.info("In HDLmJetty.handleInvokeApi - outCode - " + ((Integer) outCode).toString()); */
		    /* LOG.info("In HDLmJetty.handleInvokeApi - outStr - " + outStr); */
		    /* Check if we obtained a response from the set user password routine */
		    if (outStr == null) {
		    	response.setHeader(errorMessageHeader, "No response was received from the set password API call");
		    	response.setStatus(HttpStatus.BAD_REQUEST_400);
		    }
		    /* We get a response from the Cognito API */
		    else {
		    	/* Look for an Amazon error message header */
		    	for (String headerStr : outHeaders) {
		    		if (headerStr.startsWith(errorMessageHeader)) {
		    			int     headerIndx = headerStr.indexOf(' ');
		    			String  headerValue = headerStr.substring(headerIndx+1);
		    			response.setHeader(errorMessageHeader, headerValue);
		    			break;
		    		}
		    	}
		    	/* Store the updated password in the memory database */
		    	HDLmSecurity.updatePassword(userNameStr, passwordStr);
					/* Send the JSON back to the invoker */
		    	response.setStatus(outCode);
		    	response.setContentType("application/json");
		    	response.setCharacterEncoding("UTF-8");
		    	outWriter.print(outStr);
		    }
				/* Add the Access-Control-Expose-Headers header */
				HDLmJetty.handleResponseExposeHeaders(response);
				/* Add the Access-Control-Allow-Origin header */
				HDLmJetty.handleResponseAllowAllOrigins(request, response);
				allowAll = true;
	    	outWriter.flush();
	    	flushExecuted = true;
			}
			/* Check for the verify code API */
			if (parmName.equals(verifyApiName)) {
				/* Create a new JSON parser for use below */
		    JsonParser    parser = new JsonParser();
		    /* Make sure the inbound payload has the required key */
		    JsonElement   jsonElement = parser.parse(requestPayload);
			  /* Check if the JSON message passed by the caller is valid */
				if (!jsonElement.isJsonObject()) {
			 	  String  errorText = "JSON string in handleInvokeApi for verify code is invalid";
			 	  HDLmAssertAction(false, errorText);
			  }
		    boolean       hasChallengeNameKey = HDLmJson.hasJsonKey(jsonElement, "ChallengeName");
				if (hasChallengeNameKey == false) {
					String  errorText = "Inbound JSON data does not have ChallengeName key";
					HDLmAssertAction(false, errorText);
				}
		    boolean       hasChallengeResponsesKey = HDLmJson.hasJsonKey(jsonElement, "ChallengeResponses");
				if (hasChallengeResponsesKey == false) {
					String  errorText = "Inbound JSON data does not have ChallengeResponses key";
					HDLmAssertAction(false, errorText);
				}
		    boolean       hasSessionKey = HDLmJson.hasJsonKey(jsonElement, "Session");
				if (hasSessionKey == false) {
					String  errorText = "Inbound JSON data does not have Session key";
					HDLmAssertAction(false, errorText);
				}
		    /* Get the needed values from the inbound JSON */
				String        challengeName = HDLmJson.getJsonString(jsonElement, "ChallengeName");
		    String        sessionStr = HDLmJson.getJsonString(jsonElement, "Session");
		    JsonElement   challengeResponses = HDLmJson.getJsonObject(jsonElement, "ChallengeResponses");
			  /* Check if the challengeResponses JSON object is valid */
				if (!jsonElement.isJsonObject()) {
					String  errorText = "JSON challenge responses in handleInvokeApi for verify code are invalid";
			 	  HDLmAssertAction(false, errorText);
			  }
		    boolean   hasUserNameKey = HDLmJson.hasJsonKey(challengeResponses, "USERNAME");
				if (hasUserNameKey == false) {
					String  errorText = "Inbound JSON data does not have USERNAME key in ChallengeResponses";
					HDLmAssertAction(false, errorText);
				}
		    boolean   hasVerificationCodeKey = HDLmJson.hasJsonKey(challengeResponses, "SMS_MFA_CODE");
				if (hasVerificationCodeKey == false) {
					String  errorText = "Inbound JSON data does not have SMS_MFA_CODE key in ChallengeResponses";
					HDLmAssertAction(false, errorText);
				}
				String  userName = HDLmJson.getJsonString(challengeResponses, "USERNAME");
				String  verificationCode = HDLmJson.getJsonString(challengeResponses, "SMS_MFA_CODE");
		    /* Invoke the Cognito check verification code API */
		    ArrayList<String>   outHeaders;
		    HDLmApacheResponse  outResponse;
		    int                 outCode;
		    String              outStr;
		    outResponse = HDLmSecurity.checkVerificationCode(challengeName,
		    		                                             userName,
		    		                                             verificationCode,
		    		                                             sessionStr);
		    outCode = outResponse.getStatusCode();
		    outHeaders = outResponse.getHeaders();
		    outStr = outResponse.getStringContent();
		    /* LOG.info("In HDLmJetty.handleInvokeApi - outCode - " + ((Integer) outCode).toString()); */
		    /* LOG.info("In HDLmJetty.handleInvokeApi - outStr - " + outStr); */
		    /* Check if we obtained a response from the check verification code routine */
		    if (outStr == null) {
		    	response.setHeader(errorMessageHeader, "No response was received from the verify API call");
		    	response.setStatus(HttpStatus.BAD_REQUEST_400);
		    }
		    /* We get a response from the Cognito API */
		    else {
		    	/* Look for an Amazon error message header */
		    	for (String headerStr : outHeaders) {
		    		if (headerStr.startsWith(errorMessageHeader)) {
		    			int     headerIndx = headerStr.indexOf(' ');
		    			String  headerValue = headerStr.substring(headerIndx+1);
		    			response.setHeader(errorMessageHeader, headerValue);
		    			break;
		    		}
		    	}
					/* Send the JSON back to the invoker */
		    	response.setStatus(outCode);
		    	response.setContentType("application/json");
		    	response.setCharacterEncoding("UTF-8");
		    	outWriter.print(outStr);
		    }
				/* Add the Access-Control-Expose-Headers header */
				HDLmJetty.handleResponseExposeHeaders(response);
				/* Add the Access-Control-Allow-Origin header */
				HDLmJetty.handleResponseAllowAllOrigins(request, response);
				allowAll = true;
	    	outWriter.flush();
	    	flushExecuted = true;
	    	/* At this point we can extract from information from the
	    	   verification response */
	    	String  scopeStr;
	    	String  userNameStr;
	    	HDLmUtilityResponse   utilityResponse = HDLmSecurity.processVerificationResponse(outResponse);
	    	/* Try to get some information from the response area */
	    	scopeStr = utilityResponse.getScopeValue();
	  		/* Check if the scope string has been obtained */
	  		if (scopeStr == null) {
	  			String  errorText = "Scope string not obtained from the response area";
	  			throw new NullPointerException(errorText);
	  		}
	  		/* LOG.info("In HDLmJetty.handleInvokeApi - scopeStr - " + scopeStr); */
	    	userNameStr = utilityResponse.getUserNameValue();
	  		/* Check if the user name string has been obtained */
	  		if (userNameStr == null) {
	  			String  errorText = "User name string not obtained from the response area";
	  			throw new NullPointerException(errorText);
	  		}
	  		/* Check if the verification call worked. If it did, then we can update
	  		   the last time value and the scope string. */
	  		if (outCode == HttpStatus.OK_200) {
	  			Instant   instant = Instant.now();
	  			String    instantStr = instant.toString();
	    	  HDLmSecurity.updateLastTimeScope(userNameStr, instantStr, scopeStr);
	  		}
			}
			break;
		}
		/* Add the Access-Control-Allow-Origin header, if need be */
		if (allowAll == false) {
		  HDLmJetty.handleResponseAllowAllOrigins(request, response);
		  allowAll = true;
		}
		/* We have now handled the current post request. This took
	    a certain amount of elapsed time. Calculate how much. */
		Instant   instantFinish = Instant.now();
		Duration  requestDuration = Duration.between(instantStart, instantFinish);
		/* String  elapsedName = HDLmElapsed.elapsedNames.get(4); */
		String    elapsedName = "Invoke";
		HDLmElapsed.elapsedOccurred(elapsedName, requestDuration);
	}
	/* This routine tries to handle a request for a JavaScript program.
	   The JavaScript program is generated by calling a number of other
	   routines. The generated JavaScript program is sent back to the
	   caller (probably a client browser). */
	@SuppressWarnings("unused")
	protected static void handleJavaScriptRequest(final HttpServletRequest request,
		                            	              final HttpServletResponse response,
		                            	              final String clientStr,
		                            	              final String timeStamp) throws IOException {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleJavaScriptRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleJavaScriptRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the client string passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to handleJavaScriptRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string passed to handleJavaScriptRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Get the host name for use below */
		String  hostName = HDLmJetty.getHostNameFromRequest(request);
		/* Check if the host name returned from the get request is null */
		if (hostName == null) {
			String  errorText = "Host name return from getHostNameFromRequest is null";
			throw new NullPointerException(errorText);
		}
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After get host name in handleJavaScriptRequest");
		/* Declare and define a few variable for use later */
		String  referrerBackendName = null;
		String  referrerSecureName = null;
		/* Check if we should request logging of modification rule matching */
		HDLmLogMatchingTypes  logMatching;
		if (HDLmConfigInfo.getLogRuleMatching())
			logMatching = HDLmLogMatchingTypes.LOGMATCHINGYES;
		else
			logMatching = HDLmLogMatchingTypes.LOGMATCHINGNO;
		/* Get the pass-througth status passed below */
		HDLmPassThruStatus  passThruStatus = HDLmPassThruStatus.NONE;
		if (HDLmConfigInfo.getForcePassThru() == false)
			passThruStatus = HDLmMain.getPassThruCompanyStatus(hostName);
		else
			passThruStatus = HDLmPassThruStatus.PASSTHRUOK;
		/* At this time, we need the referrer (where the browser was going
		   to, such as 'www.themarvelouslandofoz.com' without the quotes)
		   host name from the request. We may not be able to get it. If
		   we can not get the referrer host name, then we need to fail
		   with an error message. */
		String  referrerHostName;
		if (HDLmJetty.checkForHeader(request, "referer") == null) {
		  String  requestStr = request.toString();
		  String  errorFormat = "Referrer header not obtained from request - request string was (%s)";
		  String  errorMessage = String.format(errorFormat, requestStr);
			LOG.info(errorMessage);
			referrerHostName = "";
			/* Display the HTTP(s) headers for debugging */
			HDLmJetty.dumpAllHeaders(request);
		}
		/* Apparently, the referrer header can be obtained from the request.
		   Use it to get the target host name the request. */
		else {
		  referrerHostName = HDLmJetty.getReferrerHostName(request);
			if (referrerHostName == null) {
				String   errorText = "Referrer host name not obtained from the request";
				HDLmAssertAction(false, errorText);
			}
		}
	  /* Check if this is a request for the JavaScript program we really want */
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After check for pass-through status in handleJavaScriptRequest");
	  HDLmProxy  referrerProxyDefinition = HDLmProxy.getProxyDefinitionMatch(referrerHostName,
	                                                                         HDLmProxy.getProxyListTop());
	  /* The following code is no longer in use. We now allow for the case where a proxy
	     definition can not be found. This is no longer considered to be an error condition.
	     Of course, we can't get some types of information without a proxy definition. */
	  HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After get proxy definition in handleJavaScriptRequest");
		if (referrerProxyDefinition == null &&
				referrerProxyDefinition != null) {
			String  errorFormat = "Proxy defintion not found for (getting JS) - %s";
			String  errorMessage = String.format(errorFormat, referrerHostName);
			LOG.info(errorMessage);
			HDLmJetty.reportError(response,
					                  HttpStatus.INTERNAL_SERVER_ERROR_500,
					                  errorMessage);
			return;
		}
		/* In some, but not all, cases we can get some information here.
		   To get this information we need an actual proxy definitition.
		   We will not have an actual proxy definition in all cases. */
		if (referrerProxyDefinition != null) {
		  referrerBackendName = referrerProxyDefinition.getBackendName();
		  referrerSecureName = referrerProxyDefinition.getSecureServerName();
		}
		/* Get the JavaScript program that needs to be sent back to the client */
		String  divisionName = HDLmDefines.getString("HDLMDIVISIONNODENAME");
		if (divisionName == null) {
			String   errorFormat = "Define value for division node name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMDIVISIONNODENAME");
			HDLmAssertAction(false, errorText);
		}
		String  siteName = HDLmDefines.getString("HDLMSITENODENAME");
		if (siteName == null) {
			String   errorFormat = "Define value for site node name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSITENODENAME");
			HDLmAssertAction(false, errorText);
		}
		String  serverName = HDLmConfigInfo.getServerName();
		if (serverName == null) {
			String   errorFormat = "Get value for server name not found (%s)";
			String   errorText = String.format(errorFormat, "serverName");
			HDLmAssertAction(false, errorText);
		}
		/* Note that an empty string is passed as the path value string below.
		   In this case, the path value string is not used so it does not need
		   to be passed. */
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to getJSMain in handleJavaScriptRequest");
		String  actualJS = HDLmMain.getJsMain(request,
			                                    response,
																	        HDLmProtocolTypes.HTTPS,
																	        passThruStatus,
																	        clientStr,
																	        timeStamp,
																	        referrerSecureName,
																	        referrerHostName,
																	        divisionName,
																	        siteName,
																	        "",
																	        logMatching,
																	        HDLmUsePathValue.USEPATHVALUENOTOK,
																	        serverName);
		if (actualJS == null) {
			response.setStatus(HttpStatus.NOT_FOUND_404);
			HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After call to set status in handleJavaScriptRequest");
			return;
		}
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After call to getJSMain in handleJavaScriptRequest");
		/* Get the length of the returned JavaScript program. The length may
		   be so low that we can not do anything with the returned JavaScript
		   program. */
		int   actualJSLen = actualJS.length();
		if (actualJSLen > 17) {
		  /* We must remove the first set of bytes from the JavaScript.
		     This code has the effect of removing the initial <script>
		     tag and the associated carriage return and line feed. */
		  actualJS = actualJS.substring(10);
		  /* We must remove the last set of bytes from the JavaScript.
	       This code has the effect of removing the final </script>
	       tag and the associated carriage return and line feed. */
		  actualJSLen = actualJS.length();
		  actualJS = actualJS.substring(0, actualJSLen - 13);
		}
		else
			actualJS = "";
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before set status in handleJavaScriptRequest");
		/* Send the file back to the client */
		response.setStatus(HttpStatus.OK_200);
		response.setHeader("Content-Type", "text/javascript");
		response.setCharacterEncoding("UTF-8");
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before  send JavaScript back in handleJavaScriptRequest");
		try {
			response.getWriter().print(actualJS);
			HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After print in handleJavaScriptRequest");
			response.getWriter().flush();
			HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After flush in handleJavaScriptRequest");
		}
		catch (NoSuchFileException exception) {
			LOG.info("Host name - " + hostName);
			LOG.info("NoSuchFileException while executing handleJavaScriptRequest");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("NoSuchFileException");
			response.setStatus(HttpStatus.NOT_FOUND_404);
			return;
		}
		catch (FileNotFoundException exception) {
			LOG.info("Host name - " + hostName);
			LOG.info("FileNotFoundException while executing handleJavaScriptRequest");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("FileNotFoundException");
			response.setStatus(HttpStatus.NOT_FOUND_404);
			return;
		}
		catch (IOException ioException) {
			LOG.info("Host name - " + hostName);
			LOG.info("IOException while executing handleJavaScriptRequest");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			return;
		}
		catch (Exception exception) {
			LOG.info("Host name - " + hostName);
			LOG.info("Exception while executing handleJavaScriptRequest");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			return;
		}
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After send JavaScript back in handleJavaScriptRequest");
	}
	/* This routine handles all OPTIONS requests from the client. These
	   OPTIONS requests are used to authorize certain POST requests. These
	   POST requests are preceeded by an OPTIONS request that attempts to
	   authorize them using a mechanism called a SOP/CORS preflight request. */
	protected static void handleOptionsRequest(HttpServletRequest request,
			                                       HttpServletResponse response,
			                                       String hostName) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleOptionsRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleOptionsRequest is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name string passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to handleOptionsRequest is null";
			throw new NullPointerException(errorText);
		}
	  /* Get the request headers */
    ArrayList<String>   requestHeaders = HDLmJetty.getRequestHeaders(request);
		/* Previously, the Access-Control-Allow-Origin header was only
	     added if the referrer was actually found in the request
	     headers. This was changed. We now only allow all origins,
	     in all cases, even if the referrer can not be found. This
	     was actually a problem with VS code. */
		/* Add the Access-Control-Allow-Origin header */
		HDLmJetty.handleResponseAllowAllOrigins(request, response);
		/*
		HDLmJetty.handleResponseHeader(response,
                                   "Access-Control-Allow-Origin",
                                   "null");
    */
		/*
		HDLmJetty.handleResponseHeader(response,
                                   "Access-Control-Allow-Credentials",
                                   "true");
    /*
		/* referrerProtocol + "://" + referrerHostName); */
		/* Add the Access-Control-Allow-Methods header, if need be */
		HDLmJetty.handleResponseHeader(response,
				                           "Access-Control-Allow-Methods",
				                           "GET, POST, OPTIONS");
		/* Get the extra request headers, if any */
		String  extraRequestHeaders = HDLmJetty.getHeaderValue(requestHeaders,
				                                                   "access-control-request-headers");
		/* Add the Access-Control-Allow-Headers header, if need be */
		if (extraRequestHeaders != null) {
		  HDLmJetty.handleResponseHeader(response,
		  		                           "Access-Control-Allow-Headers",
			  	                           extraRequestHeaders);
		}
		/* Add the Access-Control-Max-Age, if need be */
		HDLmJetty.handleResponseHeader(response,
				                           "Access-Control-Max-Age",
				                           "86400");
		response.setStatus(HttpStatus.NO_CONTENT_204);
	}
	/* This routine adds one response header that allows all origins
	   to be accepted. This approach avoid SOP/CORS problems as they
	   arise. */
	protected static void handleResponseAllowAllOrigins(final HttpServletRequest request,
	                                                    final HttpServletResponse response) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleResponseAllowAllOrigins is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleResponseAllowAllOrigins is null";
			throw new NullPointerException(errorText);
		}
		/* Add the Access-Control-Allow-Origin header */
		HDLmJetty.handleResponseHeader(response,
				                           "Access-Control-Allow-Origin",
				                           "*");
	}
	/* This routine adds one response header that exposes all
     headers. This approach makes all headers available to
     an application. */
	protected static void handleResponseExposeHeaders(final HttpServletResponse response) {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleResponseExposeHeaders is null";
			throw new NullPointerException(errorText);
		}
		/* Add the Access-Control-Expose-Headers header */
		HDLmJetty.handleResponseHeader(response,
				                           "Access-Control-Expose-Headers",
				                           "*");
	}
	/* This routine handles one response header. The response header is
	   either added to the response of set in the response. The caller
	   provides the header name and the header value. */
	protected static void handleResponseHeader(final HttpServletResponse response,
                                             final String headerName,
                                             final String headerValue) {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleResponseHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the header name string passed by the caller is null */
		if (headerName == null) {
			String  errorText = "Header name string passed to handleResponseHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the header value string passed by the caller is null */
		if (headerValue == null) {
			String  errorText = "Header value string passed to handleResponseHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the response header already exists */
		if (response.getHeader(headerName) != null) {
			response.setHeader(headerName, headerValue);
		}
		else
			response.addHeader(headerName, headerValue);
	}
	/* This routine does the actual work needed by the proxy server. The caller
	   indicates where the current request should be sent. The current request is
	   sent to the actual sever (not the proxy server) and the output is returned to
	   the caller. */
	protected static void handleProxy(HttpServletRequest request,
			                              HttpServletResponse response,
			                              String clientStr,
			                              String timeStamp,
			                              String actualServerName,
		                               	HDLmHttpTypes operationType,
		                               	HDLmProxy proxyDefinition) throws IOException {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleProxy is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleProxy is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the client string passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to handleProxy is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string passed to handleProxy is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the actual server name passed by the caller is null */
		if (actualServerName == null) {
			String  errorText = "Actual server name string passed to handleProxy is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP operation type passed by the caller is valid */
		if (operationType == HDLmHttpTypes.NONE) {
			String  errorText = "HTTP operation type passed to handleProxy is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the proxy definition passed by the caller is null.
		   this is no longer considered to be an error condition. We now
		   support not finding the proxy definition for a host. */
		/*
		if (proxyDefinition == null) {
			String  errorText = "Proxy definition passed to handleProxy is null";
			throw new NullPointerException(errorText);
		}
		*/
		/* Get the HTTP headers and the payload (if any) provided by the client
		   (probably a browser) */
		ArrayList<String>   requestHeaders = HDLmJetty.getRequestHeaders(request);
		byte[]              requestPayload = HDLmJetty.getRequestPayloadBinary(request);
		HDLmApacheResponse  apacheResponse;
		String              hostName = HDLmJetty.getHostNameFromRequest(request);
		String              modifiedServerName = null;
		String              requestOriginalPathValue = HDLmJetty.getOriginalPathValue(request);
		/* Build an application thread status string for use below */
		String  threadStatus = String.format("Handle Proxy Method (%s) Path Value (%s)",
				                                 HDLmJetty.getMethod(request),
				                                 requestOriginalPathValue);
		HDLmThreadStatus.put(threadStatus);
		/* Check if the current request came in on an unsecured port. Specify the
		   protocol based on the original port number. */
		HDLmProtocolTypes protocolType;
		int requestLocalPort = HDLmJetty.getLocalPort(request);
		if (requestLocalPort == 80)
			protocolType = HDLmProtocolTypes.HTTP;
		else
			protocolType = HDLmProtocolTypes.HTTPS;
	  /* Try to get the name of the session ID. This should never fail.
       However, you never know. */
		String   idName = HDLmDefines.getString("HDLMSESSIONID");
		if (idName == null) {
			String   errorFormat = "Define value for session ID name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSESSIONID");
			HDLmAssertAction(false, errorText);
		}
		/* The original path value string might contain some extra data (a session ID). This can
		   happen when a session ID is added to a link for communication purposes (of the sesssion
		   ID). The session ID must be removed must be removed from the path value. Note that some part
		   of the original path value might even come after the session ID. */
		int  idNamePos = requestOriginalPathValue.indexOf(idName);
		/* Check if the original path value contains a session ID or not. If the original path
		   value has a session ID, then the session ID must be removed. This can be a complex
		   process. See below for the details. */
		if (idNamePos > 0) {
			/* The raw session ID will contain the actual session ID and any query
			   variables after the session ID. This is actually pretty common. The
			   query variables after the session ID are needed as part of the original
			   URL. */
		  String  requestOriginalPathValueFirstPart = requestOriginalPathValue.substring(0, idNamePos-1);
		  String  rawSessionId = requestOriginalPathValue.substring(idNamePos);
		  /* Check if any query variables actually follow the session ID. If this is
		     true, then the original path value must be constructed from the part of
		     the path value before the session ID and the part of the path value after
		     the session ID. */
		  int  posQuery = rawSessionId.indexOf('&');
		  if (posQuery > 0) {
		  	String  requestOriginalPathValueSecondPart = rawSessionId.substring(posQuery);
		  	requestOriginalPathValue = requestOriginalPathValueFirstPart +
		  	     		                   requestOriginalPathValueSecondPart;
		  }
		  /* The original URU can be constructed from the information before the
		     session ID. No part of the original path value follows the session ID. */
		  else
		  	requestOriginalPathValue = requestOriginalPathValueFirstPart;
		}

		if (requestOriginalPathValue.equals("/api/socket/info/") ) {
			requestOriginalPathValue = requestOriginalPathValue;
		}
		/* Check for Windows versus Unix (of some kind). If we are running
		   under Windows, then this is a test system used for development
		   purposes. Change the server name to a name that has the same
		   IP address as oneworldobservatory.com. */
		if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS &&
				HDLmMain.isDockerFlagSet() == false) {
			modifiedServerName = actualServerName;
			if (actualServerName.equals("oneworldobservatory.com"))
				modifiedServerName = "aspirenewyork.com";
			if (actualServerName.equals("www.oneworldobservatory.com"))
				modifiedServerName = "www.aspirenewyork.com";
		}
		else {
			modifiedServerName = actualServerName;
		}
		/* We need to do some work on the HTTP headers before we use them. We need to
	     update the HTTP host name to actual host name that will handle the current
	     request (not the proxy server host name). The first step is to get the index
	     of the HTTP host name header. */
	  int   hostIndex = HDLmApache.findHostHeader(requestHeaders);
  	String  newHostHeader = null;
		String  oldHostHeader = null;
	  if (hostIndex >= 0) {
	  	newHostHeader = null;
  		oldHostHeader = requestHeaders.get(hostIndex);
  		if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS &&
  				HDLmMain.isDockerFlagSet() == false)
		    newHostHeader = HDLmApache.fixHostName(oldHostHeader, actualServerName);
  		else
  			newHostHeader = HDLmApache.fixHostName(oldHostHeader, modifiedServerName);
		  requestHeaders.set(hostIndex, newHostHeader);
	  }
	  /* In some cases, we don't have a host HTTP header at this point. We should have
	     an HTTP host headers. But for some reason, we don't. We must construct one in
	     that case. */
	  else {
	  	newHostHeader = null;
	  	if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS &&
					HDLmMain.isDockerFlagSet() == false)
		    newHostHeader = "Host: " + actualServerName;
	  	else
	  		newHostHeader = "Host: " + modifiedServerName;
		  requestHeaders.add(newHostHeader);
	  }
	  /* We need to check for a very strange case here. The old host may be
	     the same as the new host. This is an error. If the old host is the
	     same as the new host, we will cause a loop (an HTTP loop). This is
	     a bug and must be avoided. */
	  if (oldHostHeader != null &&
	  		newHostHeader != null) {
	  	String  oldHostValue = HDLmJetty.getHeaderValue(oldHostHeader);
	  	String  newHostValue = HDLmJetty.getHeaderValue(newHostHeader);
  		if (oldHostValue.equals(newHostValue)) {
  	    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
  	    return;
      }
	  }
	  /* We need to find the accept-encoding header and remove
	     Brotlie (br) support from it. It turns out that the Apache
	     HTTP client does not support Brotli. */
	  int   acceptIndex = HDLmApache.findHeader(requestHeaders, "accept-encoding");
	  if (acceptIndex >= 0) {
	  	String  newAcceptHeader = null;
  		String  oldAcceptHeader = requestHeaders.get(acceptIndex);
  		newAcceptHeader = oldAcceptHeader;
  		if (oldAcceptHeader.indexOf("br ,") >= 0)
  		  newAcceptHeader = oldAcceptHeader.replace("br ,", "");
  		else if (oldAcceptHeader.indexOf("br,") >= 0)
  			newAcceptHeader = oldAcceptHeader.replace("br,", "");
  		else if (oldAcceptHeader.indexOf(", br") >= 0)
  			newAcceptHeader = oldAcceptHeader.replace(", br", "");
  		else if (oldAcceptHeader.indexOf(",br") >= 0)
  			newAcceptHeader = oldAcceptHeader.replace(",br", "");
  		else if (oldAcceptHeader.indexOf("br") >= 0)
  			newAcceptHeader = oldAcceptHeader.replace("br", "");
		  requestHeaders.set(acceptIndex, newAcceptHeader);
	  }
		/* We need to keep track of how long it took to send the current
		   request to a backend server for processing. We need to save
		   the current instant in time to do this. */
		Instant   instantStart = Instant.now();
		/* Pass this operation to the actual server */
		HDLmApacheRedirect  apacheRedirect = HDLmApacheRedirect.DISABLE;
		/* Perform the actual HTTP(S) operation */
		apacheResponse = HDLmApache.performHttpOperation(protocolType, operationType,
				                                             modifiedServerName,
				                                             requestOriginalPathValue,
	                                              		 requestHeaders, requestPayload,
	                                              		 apacheRedirect,
	                                              		 HDLmShowRequest.SHOWREQUESTNOTOK,
	                                              		 HDLmShowResponse.SHOWRESPONSENOTOK);
		/* We have now sent the current request to a back-end for
		   processing. We want to keep track of long this took. */
		Instant   instantFinish = Instant.now();
		Duration  requestDuration = Duration.between(instantStart, instantFinish);
		/* String    elapsedName = HDLmElapsed.elapsedNames.get(2); */
		String    elapsedName = "Backend";
		HDLmElapsed.elapsedOccurred(elapsedName, requestDuration);
		/* Set the response status code */
		int responseStatus = apacheResponse.getStatusCode();
		/* Log the response status code and a set of other information */
		if (true) {
		  String  accessFormat = "Access from %s - actual server name %s - response status %d";
		  String  accessMessage = String.format(accessFormat,
		  		                                  clientStr,
		  		                                  actualServerName,
		  		                                  responseStatus);
		  LOG.info(accessMessage);
		}
		/* At this point we can store the status value in the response. However, the
		   status value may not be valid. Trying to set an invalid status value in
		   the response will cause an illegal argument exception. We don't want that.
		   So we check, for illegal values and provide a legal one. */
		if (responseStatus < 0)
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR_500;
		response.setStatus(responseStatus);
		/* In the Windows environment (which really means testing), we want to
		   change some part of the Apache response in some cases. The Apache
		   response may have a status code of 301 and a location HTTP header.
		   This is a standard redirect. However, the redirect location may be
		   (probably will be) outside of the Windows test environment. We want
		   to change the location header to point back to the Windows test
		   environment. */
		if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS &&
				HDLmMain.isDockerFlagSet() == false) {
			if (responseStatus == HttpStatus.MOVED_PERMANENTLY_301) {
				/* Get all of the Apache response headers */
			  ArrayList<String>   apacheHeaders = apacheResponse.getHeaders();
			  /* Search for the location header */
				int locationIndex = HDLmApache.findLocationHeader(apacheHeaders);
				if (locationIndex >= 0) {
					String oldLocationHeader = apacheHeaders.get(locationIndex);
					String newLocationHeader = HDLmApache.fixLocationName(oldLocationHeader,
							                                                  actualServerName,
							                                                  hostName);
					apacheHeaders.set(locationIndex, newLocationHeader);
				}
			}
	  }
		/* Add the output headers to the response, if possible. This is not always
		   completely possible. In some cases we need to avoid adding certain HTTP
		   headers to the output response. The HTTP2 specification does not allow a
		   "Transfer-Encoding" header in the response. */
		if (apacheResponse.getHeaders() != null) {
			/* Check for HTTP (not HTTP2). If we are using HTTP then we can add all of the
			   response headers. */
			if (HDLmConfigInfo.getSupportHttp2() == false) {
				HDLmJetty.setHeaders(response, apacheResponse.getHeaders());
			}
			/* For HTTP2 we need to avoid adding a "Transfer-Encoding" response header.
			   Note the we match on the header name after it has been converted to
			   uppercase. This allows both upper and lowercase versions of the string
			   to match. */
			else {
				ArrayList<String>   outHeaders = apacheResponse.getHeaders();
				for (String headerStr : outHeaders) {
					/* Convert the current header to lower case to make checking
					   easier. Get rid of several headers that are not allowed
					   with HTTP2. */
					String  headerStrLowerCase = headerStr.toLowerCase();
					if (headerStrLowerCase.startsWith("connection")        ||
							headerStrLowerCase.startsWith("keep-alive")        ||
					    headerStrLowerCase.startsWith("proxy-connection")  ||
					    headerStrLowerCase.startsWith("transfer-encoding") ||
					    headerStrLowerCase.startsWith("upgrade"))	{
						/* LOG.info("HandleProxy - Remove Header"); */
						/* LOG.info("Header " + headerStr); */
						continue;
					}
					/* We need to check for one very specific header. The TE
					   header is allowed with HTTP2 in some cases. */
					if (headerStrLowerCase.startsWith("te:")) {
						String  headerValueLowerCase = getHeaderValue(headerStrLowerCase);
						if (!headerStrLowerCase.equals("trailers"))
							continue;
					}
					HDLmJetty.setHeader(response, headerStr);
				}
			}
		}
		byte[]  apacheRespnseContent = apacheResponse.getByteContent();
		/* Write the output bytes to the response, if possible */
		if (apacheRespnseContent != null) {
			/* The while block below does all of the work that is specific to the the
			   oneworldobservatory.com web site. Some of this work would be needed for any
			   web site. However, some is just for the one world site. */
			/* What follows is a dummy loop used only to allow break to work */
			while (true) {
				if (operationType != HDLmHttpTypes.GET)
					break;
				/* Get the host name specified by the client */
				String    actualJS;
				String    secureServerName = null;
				if (proxyDefinition != null)
				  secureServerName = proxyDefinition.getSecureServerName();
				String    pathValueString = request.getRequestURI();
				String    contentType = HDLmJetty.getContentType(response);
				boolean   browserOk = HDLmUtility.checkBrowser(request, timeStamp);
				boolean   responseStrChanged = false;
				if (contentType == null)
					break;
				/* Get the response output as a Java string */
				String responseStr = new String(apacheResponse.getByteContent(), StandardCharsets.UTF_8);
				/* Some changes are always needed */
				if (true) {
					boolean fixWebSockets = HDLmConfigInfo.getFixWebSockets();
					String needle;
					String newVl;
					String newVm;
					/* The next change is used to fix vendor-048268f2d2.js. This JavaScript program
					   is under legends-owo-secure.dnsalias.com which is under 2447 (for the $35
					   package). See under ALL-INCLUSIVE%20EXPERIENCE for the $55 package. The
					   change below is in the vendor JavaScript program. This change no longer
					   works. The minified JavaScript has changed. See the code below for the
					   newer version of this change. */
					needle = "i.ri=e,i.url=a";
					if (true && fixWebSockets && responseStr.contains(needle)) {
						HDLmJetty.noteEvent("Aaaa1");
						/* newVl = "a = a.replace('legends-owo-secure.dnsalias.com', 'owo.secure.accesso.com'),"; */
						newVl = "a = a.replace('legends-owo-secure.dnsalias.com', 'owo.secure-cdn.na.accessoticketing.com'),";
						/* newVl = "console.log('aaaa' + a),"; */
						responseStr = HDLmString.strReplaceOnce(needle, newVl + needle, responseStr);
						responseStrChanged = true;
					}
					/* The next change is used to fix vendor-048268f2d2.js. This JavaScript program
					   is under legends-owo-secure.dnsalias.com which is under 2447 (for the $35
					   package). See under ALL-INCLUSIVE%20EXPERIENCE for the $55 package. The
					   change below is in the vendor JavaScript program. This is the updated
					   version of this change. The minified JavaScript changed. This code works
					   with the newer version of the minified JavaScript. This code is very specific
					   to OWO and Accesso. The file is now called vendor-14b0610c9c.js and it is
					   under ALL-INCLUSIVE%20EXPERIENCE/owo.secure.dnsalias.com/scripts. */
					needle = "i.ri=e,i.url=o";
					if (true && fixWebSockets && responseStr.contains(needle)) {
						HDLmJetty.noteEvent("Aaaa2");
						/* newVl = "o = o.replace('owo.secure.dnsalias.com', 'owo.secure.accesso.com'),"; */
						newVl = "o = o.replace('owo.secure.dnsalias.com', 'owo.secure-cdn.na.accessoticketing.com'),";
						/* newVl = "console.log('aaaa' + o),"; */
						responseStr = HDLmString.strReplaceOnce(needle, newVl + needle, responseStr);
						responseStrChanged = true;
					}
					/* The next change is used to fix vendor-048268f2d2.js. This JavaScript program
					   is under legends-owo-secure.dnsalias.com which is under 2447 (for the $35
					   package). See under ALL-INCLUSIVE%20EXPERIENCE for the $55 package. */
					needle = "this.ws.send(\"[\"+e+";
					if (true && fixWebSockets && responseStr.contains(needle)) {
						HDLmJetty.noteEvent("Bbbb");
						/* newVl = "e = e.replace('owo.secure.dnsalias.com', 'owo.secure.accesso.com');"; */
						newVl = "e = e.replace('owo.secure.dnsalias.com', 'owo.secure-cdn.na.accessoticketing.com');";
						/* newVl = "console.log('bbbb' + e);"; */
						responseStr = HDLmString.strReplaceOnce(needle, newVl + needle, responseStr);
						responseStrChanged = true;
					}
					/* The next change is used to fix vendor-048268f2d2.js. This JavaScript program
					   is under legends-owo-secure.dnsalias.com which is under 2447 (for the $35
					   package). See under ALL-INCLUSIVE%20EXPERIENCE for the $55 package. */
					needle = "this.ws.send(\"[\"+e+";
					if (fixWebSockets && responseStr.contains(needle)) {
						HDLmJetty.noteEvent("Cccc");
						HDLmJetty.noteEvent("Dddd");
						newVl = "e = e.replace('LEGENDS-OWO', 'OWO');";
						newVm = "e = e.replace('OWO-SECURE', 'OWO');";
						/* newVl = "console.log('cccc' + e);"; */
						/* newVm = "console.log('cccc' + e);"; */
						responseStr = HDLmString.strReplaceOnce(needle, newVl + newVm + needle, responseStr);
						responseStrChanged = true;
					}
					/* The next change is used to fix scripts-f4ccb5f07e.js. This JavaScript program
					   is under legends-owo-secure.dnsalias.com which is under 2447 (for the $35
					   package). See under ALL-INCLUSIVE%20EXPERIENCE for the $55 package. Note that
					   the name and location of the script may change. However, it will always be
					   scripts-something under legends-owo-secure.dnsalias.com. */
					needle = "?n.develop=!1:n.develop=!0";
					if (responseStr.contains(needle)) {
						HDLmJetty.noteEvent("Eeee1");
						newVl = "?n.develop=!1:n.develop=!1";
						responseStr = HDLmString.strReplaceOnce(needle, newVl, responseStr);
						responseStrChanged = true;
					}
					/* The next change is used to fix scripts-7afebeeb.js41. This JavaScript program
					   is under legends-owo-secure.dnsalias.com which is under 2447 (for the $35
					   package). See under ALL-INCLUSIVE%20EXPERIENCE for the $55 package. Note that
					   the name and location of the script may change. However, it will always be
					   scripts-something under legends-owo-secure.dnsalias.com. */
					needle = "?n.autofill=!1:n.autofill=!0";
					if (responseStr.contains(needle)) {
						HDLmJetty.noteEvent("Eeee2");
						newVl = "?n.autofill=!1:n.autofill=!1";
						responseStr = HDLmString.strReplaceOnce(needle, newVl, responseStr);
						responseStrChanged = true;
					}
					/* The next change is used to fix scripts-7afebeeb.js41. This JavaScript program
				     is under legends-owo-secure.dnsalias.com which is under 2447 (for the $35
				     package). See under ALL-INCLUSIVE%20EXPERIENCE for the $55 package. Note that
				     the name and location of the script may change. However, it will always be
				     scripts-something under legends-owo-secure.dnsalias.com. */
					needle = "r.autofill=F&&angular.isUndefined(a.$$search._a)||a.$$search._a&&w.getBoolean(a.$$search._a),";
					if (responseStr.contains(needle)) {
						HDLmJetty.noteEvent("Eeee3");
						newVl = "r.develop=0,r.autofill=0,";
						responseStr = HDLmString.strReplaceOnce(needle, needle + newVl, responseStr);
						responseStrChanged = true;
					}
					/* The next change is used to add a data source for Google Analytics. Note that
					   this is the only change (of this set), that works on the home page of
					   oneworldobservatory.com. The change is in the <!-- Google Analytics -->
					   section of index.html. */
					/* needle = "ga('create', 'UA-46457244-1', 'auto');"; */
					needle =  "<!-- Google Tag Manager -->";
					if (responseStr.contains(needle)) {
						HDLmJetty.noteEvent("Ffff");
						/* newVl = "ga('set', 'dataSource', 'HDLmProxy');"; */
						newVl =   "<script>dataLayer = [{'HDLmProxy': 'true'}];</script>";
						responseStr = HDLmString.strReplaceOnce(needle, needle + newVl, responseStr);
						responseStrChanged = true;
					}
				}
				/* Some changes are only needed for HTML */
				if (contentType.equals("text/html") && browserOk) {
					/* Check if we should request logging of modification rule matching */
					HDLmLogMatchingTypes  logMatching;
					if (HDLmConfigInfo.getLogRuleMatching())
						logMatching = HDLmLogMatchingTypes.LOGMATCHINGYES;
					else
						logMatching = HDLmLogMatchingTypes.LOGMATCHINGNO;
					/* Get the pass-througth status passed below */
					HDLmPassThruStatus  passThruStatus = HDLmPassThruStatus.NONE;
					if (HDLmConfigInfo.getForcePassThru() == false)
						passThruStatus = HDLmMain.getPassThruCompanyStatus(hostName);
					else
						passThruStatus = HDLmPassThruStatus.PASSTHRUOK;
					/* Get the JavaScript we need to add to the response HTML */
					String  divisionName = HDLmDefines.getString("HDLMDIVISIONNODENAME");
					if (divisionName == null) {
						String   errorFormat = "Define value for division node name not found (%s)";
						String   errorText = String.format(errorFormat, "HDLMDIVISIONNODENAME");
						HDLmAssertAction(false, errorText);
					}
					String  siteName = HDLmDefines.getString("HDLMSITENODENAME");
					if (siteName == null) {
						String   errorFormat = "Define value for site node name not found (%s)";
						String   errorText = String.format(errorFormat, "HDLMSITENODENAME");
						HDLmAssertAction(false, errorText);
					}
					String  serverName = HDLmConfigInfo.getServerName();
					if (serverName == null) {
						String   errorFormat = "Get value for server name not found (%s)";
						String   errorText = String.format(errorFormat, "serverName");
						HDLmAssertAction(false, errorText);
					}
					actualJS = HDLmMain.getJsMain(request,
													              response,
													              HDLmProtocolTypes.HTTPS,
													              passThruStatus,
													              clientStr,
													              timeStamp,
													              secureServerName,
													              hostName,
													              divisionName,
													              siteName,
													              pathValueString,
													              logMatching,
													              HDLmUsePathValue.USEPATHVALUEOK,
													              serverName);
					/* We may want to try to remove an existing copy of the JavaScript
					   program from the response, before we add a new copy of the JavaScript
					   program. We need to find the the existing copy of the JavaScript program
					   (if any) and remove it. */
					if (actualJS != null) {
						/* Set the length of the strings we are going to search for
						   in the existing response */
						int     partsLength = 30;
						/* Get the length of the JavaScript program we will be adding */
						int     actualJSLength = actualJS.length();
						/* Get the first and last part of the JavaScript program */
						if (actualJSLength > partsLength) {
							String  firstPart = actualJS.substring(0,  partsLength);
							String  lastPart = actualJS.substring(actualJSLength-partsLength, actualJSLength);
							/* Search the current response string for the first and last parts
							   of the JavaScript program. If we find the parts, then we can
							   remove the existing JavaScript program. */
							int     firstPartIndex = responseStr.indexOf(firstPart);
							if (firstPartIndex >= 0) {
							  int     lastPartIndex = responseStr.indexOf(lastPart);
							  if (lastPartIndex >= firstPartIndex)
							    responseStr = HDLmString.removeSubstring(responseStr,
							    		                                     firstPartIndex,
							    		                                     lastPartIndex+partsLength);
							}
						}
				  }
					/* Check if we obtained some actual JavaScript from the above function */
					if (actualJS != null) {
						/* HDLmString.splitDisplayString(actualJS, '\n'); */
						String endStr = "</head>";
						String oldStr = endStr;
						String newStr = actualJS + endStr;
						responseStr = HDLmString.strReplaceOnce(oldStr, newStr, responseStr);
						responseStrChanged = true;
					}
				}
				/* Change all of the references from the actual backend server to the proxy
				   server. We were changing references in "text/javascript" files. This caused
				   major problems. Effectively, we were undoing changes that had already been
				   made. */
				if (contentType.equals("text/html") || contentType.equals("application/javascript")) {
					/* The replacement process must use the base host names, not the host names
					   with a 'www.' prefix. The reason is that many of the embedded links use
					   a host name with a 'www.' prefix. Of course, the embedded links should
					   not have a host name in them (generally) at all. However, many do anyway. */
					String  actualServerNameNoPrefix = HDLmString.removePrefix("www.", actualServerName);
					String  hostNameNoPrefix = HDLmString.removePrefix("www.", hostName);
					responseStr = responseStr.replace(actualServerNameNoPrefix, hostNameNoPrefix);
					responseStrChanged = true;
				}
				if (responseStrChanged) {
					/* LOG.info("handleProxy - responseStrChanged true"); */
					apacheResponse.setByteContent(responseStr.getBytes(StandardCharsets.UTF_8));
				}
				break;
			}
			/* The operation below should never fail. However, experience has shown that it
			   does fail with various exceptions. These exceptions are probably bugs in the
			   Apache, Chrome, or Jetty code. However, the bottom line is that the operation
			   below does fail with exceptions. Very strange. */
			try {
				byte[]        contentBytes;
				int           contentBytesLength;
				OutputStream  outputStream;
				outputStream = response.getOutputStream();
        contentBytes = apacheResponse.getByteContent();
        contentBytesLength = contentBytes.length;
        /*
        LOG.info("handleProxy - try block");
        LOG.info("content bytes length (part1) - " + contentBytesLength);
        */
				outputStream.write(contentBytes);
				/*
				LOG.info("content bytes length (part2) - " + contentBytesLength);
				*/
			}
			catch (Exception exception) {
				String pathValueString = request.getRequestURI();
				if (pathValueString != null)
				  LOG.info("Path value - " + pathValueString);
				LOG.info("Exception while executing handleProxy");
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				return;
			}
		}
	}
	/* This routine handles certain requests to set test mode off and on from 
	   the client. These POST requests are used to set the test mode specified
	   by the caller. */
	protected static void handleSetTest(HttpServletRequest request,
			                                HttpServletResponse response,
	                                    String requestOriginalPathValue) {
		/* LOG.info("In HDLmJetty.handleSetTest"); */
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the request original path value passed by the caller is null */
		if (requestOriginalPathValue == null) {
			String  errorText = "Request orginal path passed to handleInvokeApi is null";
			throw new NullPointerException(errorText);
		}
		/* Define a local variable */
		String  sessionIdStr = null;
		/* Check if the inbound request has an active session. If yes, extract
		   the session and set a session object to a non-null value. If not,
		   create the session for later use. */
		HDLmSession   sessionObj = HDLmJetty.checkForSession(request);
		/* LOG.info("Session active - " + ((Boolean) (sessionObj != null)).toString()); */
		if (sessionObj == null) {
			/* Create a new session object for use in this routine */
			sessionObj = new HDLmSession();
			/* Get a session ID and store it in thie new session object */
			sessionIdStr = UUID.randomUUID().toString();
			sessionObj.setSessionId(sessionIdStr);			
			/* Get a string with a random double in it */
			double  randomDouble = HDLmUtility.getRandomDouble();
			String  randomDoubleStr = String.valueOf(randomDouble);		
			/* Set the index value string to a random value */
		  sessionObj.setIndex(randomDoubleStr);
			/* Get an empty set of doubles and store them in the session object
			   in string form */
			ArrayList<Double>  emptyDoubleArrayList = new ArrayList<Double>();
			String             emptyDoubleArrayListStr = HDLmMain.getParametersStr(emptyDoubleArrayList);
			sessionObj.setParameters(emptyDoubleArrayListStr);
			/* Store the session object in the session cache */
			HDLmSession.addToCache(sessionIdStr, sessionObj);
			/* Store the cookie in the client */		
			Integer   cookieMaxAge = HDLmConfigInfo.getCookieMaxAge();
			String    cookieSameSite = "None";
			String    plainTextSessionIdJsonValue = sessionObj.buildJsonIdOnly();
			String    encryptionKeyValue = HDLmConfigInfo.getSecretEncryptionKey();
			String    encryptedSessionIdJsonValue = HDLmEncryption.encrypt(encryptionKeyValue, 
	                                                           				 plainTextSessionIdJsonValue);
			HDLmJetty.storeCookie(response, 
					                  encryptedSessionIdJsonValue, 
					                  cookieMaxAge,
					                  cookieSameSite);
		}
		/* An existing session was found. Get the session ID from 
		   the session object */
		else {
			sessionIdStr = sessionObj.getSessionId();
		}
		/* Check if test mode should be turned on or off */
		if (requestOriginalPathValue != null) {	 
			String  useMode = null;
			if (requestOriginalPathValue.contains("On"))
		    useMode = "test";
			if (requestOriginalPathValue.contains("Off"))
		    useMode = "prod";
			sessionObj.setUseMode(useMode);			 
		}
		/* Add the session object to the session cache */	   
	  HDLmSession.addToCache(sessionIdStr, sessionObj);
	}
	/* This routine handles certain special POST requests from the client. These
	   POST requests are used to log (record) the results from our AI experiments. */
	protected static void handleSpecialPost(HttpServletRequest request,
			                                    HttpServletResponse response,
			                                    String hostName,
			                                    String requestPostPayload,
			                                    String clientStr,
			                                    String timeStamp,
                                          HDLmHttpTypes httpType,
                                          String requestOriginalPathValue) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleSpecialPost is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleSpecialPost is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name string passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to handleSpecialPost is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the client string passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to handleSpecialPost is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string passed to handleSpecialPost is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the HTTP type passed by the caller is null */
		if (httpType == null) {
			String  errorText = "HTTP type passed to handleSpecialPost is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the request original path value passed by the caller is null */
		if (requestOriginalPathValue == null) {
			String  errorText = "Request orginal path passed to handleSpecialPost is null";
			throw new NullPointerException(errorText);
		}
		/* Get the HTTP headers and the payload (if any) provided by the client
	     (probably a browser) */
	  String postDataKey = HDLmDefines.getString("HDLMPOSTDATA");
	  if (postDataKey == null) {
		  String   errorFormat = "Define value for post data key not found (%s)";
		  String   errorText = String.format(errorFormat, "HDLMPOSTDATA");
		  HDLmAssertAction(false, errorText);
	  }
	  int   postDataKeyLen = postDataKey.length();
	  /* Create a few variables that are later set to true, in some
	     cases. The inbound payload may contain some of the keys
	     mentioned below. */
    boolean   hasEventNameKey = false;
    boolean   hasLinkKey = false;
    boolean   hasUpdatesKey = false;
    boolean   skipLogs = false;
		/* We need to keep track of how long it took to handle the current
		   operation. We need to save the current instant in time to do this. */
	  Instant   instantStart = Instant.now();
	  String    hostNameStr = null;
	  String    requestPayload = requestPostPayload;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the correct type of HTTP request was passed by the caller */
		  if (httpType != HDLmHttpTypes.POST)
		  	break;
			/* Check if we actually obtained a post payload. We have no more
			   work to do if we did not obtain an actual post payload. */
			if (requestPayload == null)
				break;
		  /* Check if the request original path passed by the caller
		     was correct or not */
			if (!requestOriginalPathValue.equals('/' + postDataKey))
				break;
		  /* Remove the first part of request payload, if possible, and
		     if need be */
		  int   requestPayloadLen = requestPayload.length();
		  /* Do some checking on request payload */
		  if (requestPayloadLen > (postDataKeyLen+1)) {
		  	if (postDataKey.equals(requestPayload.substring(0, postDataKeyLen))) {
		  		if (requestPayload.charAt(postDataKeyLen) == '=')
		  			requestPayload = requestPayload.substring(postDataKeyLen+1);
		  		else
		  			requestPayload = null;
		  	}
		  	else
		  		requestPayload = null;
		  }
		  else
		  	requestPayload = null;
			/* Look for JavaScript exception information in the posted
			   JSON and note each JavaScript exception. Of course, this
			   should not occur. However, experience has shown that it
			   does occur. */
			HDLmJetty.getJavaScriptExceptionsFromJson(requestPayload);
			/* Extract the modification names (rule names) and update
			   the last fired statistics */
			HDLmMod.getModificationsFromJson(requestPayload);
			/* Add the posted changes to the file. Note that we add the timestamp,
			   but not the client string to the file. The client string is deemed
			   to have PII in it and must not be logged. */
			String  infoLogData = String.format("{\"timestamp\":\"%s\",",
					                                timeStamp);
			/* Try to extract the session information from the inbound HTTP headers */
		 	HDLmSession  newSessionObj = HDLmJetty.getSessionInfo(request,
		 			                                                  HDLmReturnNull.RETURNNULLOK);
		 	/* Check if we didn't get a new session object back from the above
		 	   call. This can happen, if we don't get (back) a cookie with the
		 	   session information. */
		 	if (newSessionObj == null) {
		 		/* We need to create a session ID from scratch here. We were
		 		   not able to obtain the session information from a cookie.
		 		   Of course, the session ID is 'unknown' and we don't have
		 		   any actual parameter values. */
	      newSessionObj = new HDLmSession();
	      newSessionObj.setSessionId("unknown");
	      /* Set the index value to a known value that shows that we could
	         not get the index value from the request */
	      newSessionObj.setIndex("null");
	      /* Since we did not get any session information from the inbound
	         HTTP headers, we need to create it (invent it from scratch)
	         here. We will assume that we have no parameters. */
	      int     defaultParmCount = 0;
		    String  parametersStr = HDLmString.repeatString("none", " ", defaultParmCount);
		    newSessionObj.setParameters(parametersStr);
		    /* Try to get the session ID from the request payload. This
		       should always work. */
				/* Create a new JSON parser for use below */
		    JsonParser    parser = new JsonParser();
		    /* Make sure the inbound payload has the required key */
		    JsonElement   jsonElement = parser.parse(requestPayload);
		    /* Set a few variables based in the inbound payload. The inbound
		       payload will be in JSON and POSTed to the server. */
		    hasEventNameKey = HDLmJson.hasJsonKey(jsonElement, "eventName");
		    hasLinkKey = HDLmJson.hasJsonKey(jsonElement, "link");
		    hasUpdatesKey = HDLmJson.hasJsonKey(jsonElement, "updates");
		    /* In some important cases, the inbound post data does not have the 'updates'
		       (without the quotes) key. For example, if the generated JavaScript program
		       encounters an error, we will not have an updates key. */
				if (hasUpdatesKey == false &&
						hasUpdatesKey == true) {
					String  errorText = "Inbound data does not have updates key";
					HDLmAssertAction(false, errorText);
				}
				/* Check if the inbound post data really has an updates key or not */
				if (hasUpdatesKey) {
					/* Get all of the updates and make sure we have at least one */
					JsonArray   jsonArray = HDLmJson.getJsonArray(jsonElement, "updates");
					int   jsonArraySize = jsonArray.size();
					if (jsonArraySize <= 0) {
						String  errorText = "Updates array in inbound data is not large enough";
						HDLmAssertAction(false, errorText);
					}
					jsonElement = jsonArray.get(0);
					/* Make sure we have a session ID value in the first update */
					if (!HDLmJson.hasJsonKey(jsonElement, "sessionId")) {
						String  errorText = "First element of the updates array does not have a session ID";
						HDLmAssertAction(false, errorText);
					}
					/* Get the actual session ID string */
					String  sessionId = HDLmJson.getJsonString(jsonElement, "sessionId");
					newSessionObj.setSessionId(sessionId);
					/* Make sure we have an index value is the first update */
					if (!HDLmJson.hasJsonKey(jsonElement, "indexValue")) {
						String  errorText = "First element of the updates array does not have an index value string";
						HDLmAssertAction(false, errorText);
					}
					/* Get the actual index value */
					Double  indexDouble = HDLmJson.getJsonDouble(jsonElement, "indexValue");
					String  indexStr = null;
					if (indexDouble == null)
						indexStr = "null";
					else
						indexStr = indexDouble.toString();
					newSessionObj.setIndex(indexStr);
					/* Make sure we have a parameter string value in the first update */
					if (!HDLmJson.hasJsonKey(jsonElement, "parameters")) {
						String  errorText = "First element of the updates array does not have a parameters string";
						HDLmAssertAction(false, errorText);
					}
					/* Get the actual parameters string */
					parametersStr = HDLmJson.getJsonString(jsonElement, "parameters");
					newSessionObj.setParameters(parametersStr);
					/* Make sure we have a host name string value in the first update */
					if (!HDLmJson.hasJsonKey(jsonElement, "hostName")) {
						String  errorText = "First element of the updates array does not have a host name string";
						HDLmAssertAction(false, errorText);
					}
					/* Get the actual host name string */
					hostNameStr = HDLmJson.getJsonString(jsonElement, "hostName");
					/*
					if (hostNameStr == null)
						LOG.info("HostName from JSON 1 - null");
					else
					  LOG.info("HostName from JSON 1 - " + hostNameStr);
					*/
			 	}
				/* Check if the inbound post data really has an event name key or not */
				else if (hasEventNameKey) {
					/* We really don't want to log events. There are too many of them and
					   they take up too much space. */
					skipLogs = true;
			 		boolean   hasHostNameKey = HDLmJson.hasJsonKey(jsonElement, "hostName");
					/* Make sure we have a host name string value */
					if (!HDLmJson.hasJsonKey(jsonElement, "hostName")) {
						String  errorText = "Host name string value not found";
						HDLmAssertAction(false, errorText);
					}
					/* Get the actual host name string */
					hostNameStr = HDLmJson.getJsonString(jsonElement, "hostName");
					/* Get the actual session ID string */
					String  sessionId = HDLmJson.getJsonString(jsonElement, "sessionId");
					newSessionObj.setSessionId(sessionId);
					HDLmJson.removeJsonKey(jsonElement, "sessionId");
					/* At this point, we probably should rebuild the request payload
					   string for use below. This code does not do so. As a consequence,
					   the session ID value occurs twice in the final string. */
				}
				/* Check if the inbound post data really has a link key or not */
				else if (hasLinkKey) {
					/* We really don't want to log links. There are too many of them and
				     they take up too much space. */
				  skipLogs = true;
			 		boolean   hasHostNameKey = HDLmJson.hasJsonKey(jsonElement, "hostName");
					/* Make sure we have a host name string value */
					if (!HDLmJson.hasJsonKey(jsonElement, "hostName")) {
						String  errorText = "Host name string value not found";
						HDLmAssertAction(false, errorText);
					}
					/* Get the actual host name string */
					hostNameStr = HDLmJson.getJsonString(jsonElement, "hostName");
					/* Get the actual session ID string */
					String  sessionId = HDLmJson.getJsonString(jsonElement, "sessionId");
					newSessionObj.setSessionId(sessionId);
					HDLmJson.removeJsonKey(jsonElement, "sessionId");
					/* At this point, we probably should rebuild the request payload
				     string for use below. This code does not do so. As a consequence,
				     the session ID value occurs twice in the final string. */
				}
			 	/* If we don't have any updates and this is not an event, then we
			 	   must try to get the host name from a different location */
			 	else {
			 		boolean   hasHostNameKey = HDLmJson.hasJsonKey(jsonElement, "hostName");
					/* Make sure we have a host name string value */
					if (!HDLmJson.hasJsonKey(jsonElement, "hostName")) {
						String  errorText = "Host name string value not found";
						HDLmAssertAction(false, errorText);
					}
					/* Get the actual host name string */
					hostNameStr = HDLmJson.getJsonString(jsonElement, "hostName");
					/* We may want to replace the session ID with an actual value
					   at this point. This is only done in some cases. The most
					   important case is if we are handling an exception. */
					if (newSessionObj.getSessionId().contentEquals("unknown")) {
						/* Get the actual session ID string */
						String  sessionId = HDLmJson.getJsonString(jsonElement, "sessionId");
						if (sessionId != null)
						  newSessionObj.setSessionId(sessionId);
					}
					/*
					if (hostNameStr == null)
						LOG.info("HostName from JSON 2 - null");
					else
					  LOG.info("HostName from JSON 2 - " + hostNameStr);
					*/
			 	}
		 	}
	    String  newSessionJson = newSessionObj.buildJson();
	    infoLogData += newSessionJson.substring(1, newSessionJson.length()-1);
	    infoLogData += ',';
			infoLogData += requestPayload.substring(1);
			if (!skipLogs)
	  		HDLmUtility.filePutAppendLineLogs(infoLogData, hostNameStr);
			break;
		};
	  String  referrerHostName = HDLmJetty.getReferrerHostName(request);
	  if (referrerHostName == null) {
		  String   errorText = "Referrer host name not obtained from the request";
		  HDLmAssertAction(false, errorText);
	  }
		String  referrerProtocol = HDLmJetty.getReferrerProtocol(request);
	  if (referrerProtocol == null) {
		  String   errorText = "Referrer protocol not obtained from the request";
		  HDLmAssertAction(false, errorText);
	  }
		/* Add the Access-Control-Allow-Origin header, if need be */
		HDLmJetty.handleResponseHeader(response,
				                           "Access-Control-Allow-Origin",
				                           referrerProtocol + "://" + referrerHostName);
		/* We have now handled the current post request. This took
	     a certain amount of elapsed time. Calculate how much. */
		Instant   instantFinish = Instant.now();
		Duration  requestDuration = Duration.between(instantStart, instantFinish);
		/* String    elapsedName = HDLmElapsed.elapsedNames.get(3); */
		String    elapsedName = "Post";
		HDLmElapsed.elapsedOccurred(elapsedName, requestDuration);
	}
	/* This routine handles certain status information commands that can be
     sent using a browser. The commands are routed as need be and the
     response is set back to the browser. */
	protected static void handleStatusCommands(String pathValueString,
						                                 String hostName,
						                                 HttpServletRequest request,
						                                 HttpServletResponse response,
						                                 String clientStr,
						                                 String timeStamp) {
		HDLmResponse        rvResponse;
		HDLmResponseTypes   rvResponseType;
		String              contentType = "text/html";
		String              rvString = "";
		/* Check if the path value string passed by the caller is null */
		if (pathValueString == null) {
			String  errorText = "Path value string passed to handleStatusCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the host name passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name passed to handleStatusCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to handleStatusCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to handleStatusCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the client string passed by the caller is null */
		if (clientStr == null) {
			String  errorText = "Client string passed to handleStatusCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string passed to handleStatusCommands is null";
			throw new NullPointerException(errorText);
		}
		/* Check which command the caller passed. Invoke the appropriate status
		   command routine. The status command routines return a string with the
		   correct HTML in it. This routine handles the rest of the Jetty response. */
		if (pathValueString.startsWith("/appthr-status"))
			rvString = HDLmMain.appthrStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/connection-status"))
			rvString = HDLmMain.connectionStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/elapsed-status"))
			rvString = HDLmMain.elapsedStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/events-checkAnomalies"))
			rvString = HDLmMain.eventsCheckAnomalies(hostName, clientStr);
		else if (pathValueString.startsWith("/events-checkExceptions"))
			rvString = HDLmMain.eventsCheckExceptions(hostName, clientStr);
		else if (pathValueString.startsWith("/events-status"))
			rvString = HDLmMain.eventsStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/memory-status"))
			rvString = HDLmMain.memoryStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/passthru-status"))
			rvString = HDLmMain.passThruStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/phash-status"))
			rvString = HDLmMain.phashStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/rules-status")) {
			rvResponse = HDLmMain.rulesStatus(hostName, clientStr, request);
			rvResponseType = rvResponse.getResponseType();
			if (rvResponseType == HDLmResponseTypes.JSON)
				contentType = "application/json";
			rvString = rvResponse.getReturnString();
		}
		else if (pathValueString.startsWith("/sessionId-status"))
		  rvString = HDLmMain.sessionIdStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/server-status"))
		  rvString = HDLmMain.serverStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/systhr-status"))
			rvString = HDLmMain.systhrStatus(hostName, clientStr);
		else if (pathValueString.startsWith("/timings-status"))
			rvString = HDLmMain.timingsStatus(hostName, clientStr);
		else {
			String  errorFormat = "Invalid status command (%s) sent to server status command handler";
			String  errorText = String.format(errorFormat,  pathValueString);
			HDLmAssertAction(false, errorText);
		}
		/* Now that we have the HTML command output, we need to send the HTML
		   command output back to the browser. The code below does this. */
		try {
			 response.setStatus(HttpStatus.OK_200);
			 response.getWriter().print(rvString);
			 response.getWriter().flush();
			 response.setContentType(contentType);
		}
		catch (Exception exception) {
			if (pathValueString != null)
			  LOG.info("Path value - " + pathValueString);
			LOG.info("Exception while executing handleStatusCommands");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	}
	/* This routine tries to return a boolean showing if the current browser is OK
	   or not. If the current browser is not OK, then we can not use the current
	   browser to run our code. If the current browser is OK, then we can use the
	   current browser to run our code. */
	protected static boolean isBrowserOk(HttpServletRequest request) {
		boolean browserOk = false;
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to isBrowserOk is null";
			throw new NullPointerException(errorText);
		}
		/* Get the User-Agent header, if possible */
		String userAgent = HDLmJetty.getUserAgentHeader(request);
		if (userAgent == null)
			return browserOk;
		/* Get some information about the current browser */
		HDLmClientInfo clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		if (clientInfo.getBrowserType() == HDLmBrowserTypes.IE)
			return browserOk;
		browserOk = true;
		return browserOk;
	}
	/* This routine notes that an event has happened. In practice, the event
	   is logged and the events map is updated. */
	protected static void  noteEvent(final String curEventName) {
		/* Check if the current event name passed by the caller is null */
		if (curEventName == null) {
			String  errorText = "Current event name passed to noteEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Try to get the event number (index) from the event name */
		int  eventIndex = HDLmEvent.eventNumbers.get(curEventName);
		/* Check if the input event index passed by the caller is valid
		   or not */
		if (eventIndex < 0) {
			String  errorFormat = "Event index passed to noteEvent is invalid (%d)";
			String  errorText = String.format(errorFormat,  eventIndex);
			throw new NullPointerException(errorText);
		}
		/* Check if the event index value is too high */
		if (eventIndex >= HDLmEvent.eventNames.size()) {
			String  errorFormat = "Event index passed to noteEvent is too high (%d)";
			String  errorText = String.format(errorFormat,  eventIndex);
			throw new IllegalArgumentException(errorText);
		}
		String  eventName = curEventName;
		LOG.info("Change " + eventName);
		HDLmEvent.eventOccurred(eventName);
	}
	/* This routine tries to handle the file specified by the caller. Different
	   types of files are handled in different ways. This routine tries to handle
	   each type of file correctly. */
	protected static void processFile(HttpServletRequest request,
		                            	  HttpServletResponse response,
		                            	  String pathStr,
			                              String fileName) {
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to processFile is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to processFile is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the path string passed by the caller is null */
		if (pathStr == null) {
			String  errorText = "Path string passed to processFile is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the file name passed by the caller is null */
		if (fileName == null) {
			String  errorText = "File name passed to processFile is null";
			throw new NullPointerException(errorText);
		}
		/* For debugging show a few values */
		if (LOG.isDebugEnabled()) {
			LOG.debug("in HDLmJetty.processFile");
			LOG.debug(request.toString());
			LOG.debug(response.toString());
			LOG.debug(pathStr);
			LOG.debug(fileName);
		}
		/* Get the suffix of the file name. The suffix tells us how handle the file. */
		Boolean fileBinary;
		String fileSuffix = HDLmUtility.fileGetSuffix(fileName);
		/* We need to check for a very strange case here. In at least one case
		   we get sent what looks like a file request, but is actually part of
		   the Cognito system. Why is not clear (at all). This appears to be
		   new as of 2023-10-15. */
		if (fileSuffix == null) {
			String userPoolName = HDLmConfigInfo.getAwsCognitoUserPoolName();
			String userPoolNameLower = userPoolName.toLowerCase();
			LOG.info("In HDLmJetty.processFile - file name - " + fileName);
			LOG.info("In HDLmJetty.processFile - user pool name - " + userPoolName);
			LOG.info("In HDLmJetty.processFile - user pool name lower - " + userPoolNameLower);
			if (true && fileName.contentEquals(userPoolNameLower)) {
	  		response.setStatus(HttpStatus.OK_200);
	  		String responseStr = "<div><h3>The permanent password was set</h3></div>";
				try {
					response.getWriter().print(responseStr);
					response.getWriter().flush();
				}
				catch (IOException ioException) {
					LOG.info("IOException while executing processFile");
					LOG.info(ioException.getMessage(), ioException);
					HDLmEvent.eventOccurred("IOException");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
					return;
				}
		  	return;
	  	}
		}
		/* Check if the file suffix is null. We can not handle
		   a file, if the file suffix null. We can just give up
		   in this case. */
		if (fileSuffix == null) {
			LOG.info("File name - " + fileName);
			LOG.info("File suffix is null while executing processFile");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			return;
		}
		/* Check for a binary file */
		fileBinary = HDLmUtility.isTypeBinary(fileSuffix);
		if (fileBinary == null) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			return;
		}
		/* Get the MIME types for the current file */
		String fileMimeType;
		fileMimeType = HDLmUtility.getMimeType(fileSuffix);
		if (fileMimeType == null) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			return;
		}
		/* Check for some kind of binary file */
		if (fileBinary) {
			File file = new File(pathStr, fileName);
			response.setStatus(HttpStatus.OK_200);
			response.setHeader("Content-Type", fileMimeType);
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			}
			catch (NoSuchFileException exception) {
				if (fileName != null)
				  LOG.info("File name - " + fileName);
				LOG.info("NoSuchFileException while executing processFile");
				boolean   logException = true;
				/* We really don't want to log all of the details of the exception
				   if the exception was actually expected */
				if (fileName != null &&
						fileName.indexOf("favicon.ico") >= 0)
					logException = false;
				if (logException)
				  LOG.info(exception.getMessage(), exception);
				else
					LOG.info(exception.getMessage());
				HDLmEvent.eventOccurred("NoSuchFileException");
				response.setStatus(HttpStatus.NOT_FOUND_404);
				return;
			}
			catch (FileNotFoundException exception) {
				if (fileName != null)
				  LOG.info("File name - " + fileName);
				LOG.info("FileNotFoundException while executing processFile");
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("FileNotFoundException");
				response.setStatus(HttpStatus.NOT_FOUND_404);
				return;
			}
			catch (IOException ioException) {
				if (fileName != null)
				  LOG.info("File name - " + fileName);
				LOG.info("IOException while executing processFile");
				LOG.info(ioException.getMessage(), ioException);
				HDLmEvent.eventOccurred("IOException");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
				return;
			}
			catch (Exception exception) {
				if (fileName != null)
				  LOG.info("File name - " + fileName);
				LOG.info("Exception while executing processFile");
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
				return;
			}
		}
		/* Check for some kind of character file */
		if (!fileBinary) {
			/* Try to read the file into memory. Check if the file was actually found and
			   read. */
			LOG.info("Path is " + pathStr);
			LOG.info("File name is " + fileName);
			/* The code below was added just for debugging a problem. This
			   test should never be true. */ 
			if (fileName.equals("JS/HDLmBuild.js")) {
				fileName = fileName;				
			}
			String fileStr = HDLmUtility.fileGetContents(pathStr + fileName, StandardCharsets.UTF_8);
			if (fileStr == null) {
				response.setStatus(HttpStatus.NOT_FOUND_404);
				return;
			}
			/* Send the file back to the client */
			response.setStatus(HttpStatus.OK_200);
			response.setHeader("Content-Type", fileMimeType);
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().print(fileStr);
				response.getWriter().flush();
			}
			catch (NoSuchFileException exception) {
				if (fileName != null)
				  LOG.info("File name - " + fileName);
				LOG.info("NoSuchFileException while executing processFile");
				boolean   logException = true;
				/* We really don't want to log all of the details of the exception
				   if the exception was actually expected */
				if (fileName != null &&
						fileName.indexOf("favicon.ico") >= 0)
					logException = false;
				if (logException)
				  LOG.info(exception.getMessage(), exception);
				else
					LOG.info(exception.getMessage());
				HDLmEvent.eventOccurred("NoSuchFileException");
				response.setStatus(HttpStatus.NOT_FOUND_404);
				return;
			}
			catch (FileNotFoundException exception) {
				if (fileName != null)
				  LOG.info("File name - " + fileName);
				LOG.info("FileNotFoundException while executing processFile");
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("FileNotFoundException");
				response.setStatus(HttpStatus.NOT_FOUND_404);
				return;
			}
			catch (IOException ioException) {
				if (fileName != null)
				  LOG.info("File name - " + fileName);
				LOG.info("IOException while executing processFile");
				LOG.info(ioException.getMessage(), ioException);
				HDLmEvent.eventOccurred("IOException");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
				return;
			}
			catch (Exception exception) {
				if (fileName != null)
				  LOG.info("File name - " + fileName);
				LOG.info("Exception while executing processFile");
				LOG.info(exception.getMessage(), exception);
				HDLmEvent.eventOccurred("Exception");
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
				return;
			}
		}
	}
	/* Report an error back to the client. The client is probably a browser.
	   However, this will not always be true. The caller provides the error code and
	   the error message. Both are returned to the caller. Note that a formatted
	   error message is returned to the caller. The formatted message uses the same
	   format as the standard Jetty error message. */
	protected static void reportError(HttpServletResponse response, int statusCode, String errorMessage)
			throws IOException {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to reportError is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the status code passed by the caller is invalid */
		if (statusCode <= 0) {
			String  errorText = "Status code passed to reportError is invalid";
			HDLmAssertAction(false, errorText);
		}
		/* Check if the output error message passed by the caller is null */
		if (errorMessage == null) {
			String  errorText = "Output error message passed to reportError is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output error message passed by the caller is empty */
		if (errorMessage.isEmpty()) {
			String  errorText = "Output error message passed to reportError is empty";
			HDLmAssertAction(false, errorText);
		}
		/* Build a few variables */
		String statusCodeStr = Integer.toString(statusCode);
		StringBuilder outputStr = new StringBuilder();
		outputStr.append("<html>");
		outputStr.append("<head>");
		outputStr.append("<meta http-equiv=\"Content-Type\" ");
		outputStr.append("content=\"text/html;charset=utf-8\">");
		outputStr.append("<title>Error ");
		outputStr.append(statusCodeStr);
		outputStr.append(" Server Error</title>");
		outputStr.append("</head>");
		outputStr.append("<body>");
		outputStr.append("<h2>HTTP ERROR ");
		outputStr.append(statusCodeStr);
		outputStr.append("</h2>");
		outputStr.append("<p>");
		outputStr.append(errorMessage);
		outputStr.append("</p>");
		outputStr.append("</body>");
		outputStr.append("</html>");
		/* Report the error */
		response.setStatus(statusCode);
		response.getWriter().print(outputStr.toString());
	}
	/* Report an error back to the client. The client is probably a browser.
	   However, this will not always be true. The caller provides the error code and
	   the error message. Both are returned to the caller. This is the basic error
	   message routine. It appears to be no longer in use. */
	protected static void reportErrorBasic(HttpServletResponse response, int statusCode, String errorMessage)
			throws IOException {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to reportErrorBasic is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the status code passed by the caller is invalid */
		if (statusCode <= 0) {
			String  errorText = "Status code passed to reportErrorBasic is invalid";
			HDLmAssertAction(false, errorText);
		}
		/* Check if the output error message passed by the caller is null */
		if (errorMessage == null) {
			String  errorText = "Output error message passed to reportErrorBasic is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output error message passed by the caller is empty */
		if (errorMessage.isEmpty()) {
			String  errorText = "Output error message passed to reportErrorBasic is empty";
			HDLmAssertAction(false, errorText);
		}
		/* Report the error */
		response.setStatus(statusCode);
		response.getWriter().println(errorMessage);
	}
	/* This routine tries to set a cookie in the servlet response. The caller
	   provides the servlet response object, the cookie name, and the cookie value.
	   The cookie value will be encoded (URL encoded) by this routine. URL encoding
	   isn't actually required by browsers. However, the Jetty code does require URL
	   encoding of cookie values. Note that the caller passes the cookie maximum age
	   value, which may be null.*/
	protected static void setCookie(final HttpServletResponse response,
		    	                        final String cookieName,
		    	                        final String cookieValue,
		    	                        final String cookiePath,
		    	                        final HDLmSecureCookie cookieSecure,
		    	                        final HDLmHttpOnly cookieHttpOnly,
		    	                        final Integer cookieMaxAge,
		    	                        final String cookieSameSite) {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to setCookie is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the cookie name passed by the caller is null */
		if (cookieName == null) {
			String  errorText = "Cookie name passed to setCookie is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the cookie value passed by the caller is null */
		if (cookieValue == null) {
			String  errorText = "Cookie value passed to setCookie is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the cookie path value passed by the caller is null.
	     This value can be null, in some cases. A null value is not
	     an error. */
		if (cookiePath == null &&
			  cookiePath != null) {
	    String  errorText = "Cookie path value passed to setCookie is null";
	    throw new NullPointerException(errorText);
	  }
		/* Check if the cookie secure value passed by the caller is null.
       This value can be null, in some cases. A null value is not
       an error. */
		if (cookieSecure == null &&
			  cookieSecure != null) {
	    String  errorText = "Cookie secure value passed to setCookie is null";
	    throw new NullPointerException(errorText);
	  }
		/* Check if the cookie HTTP only value passed by the caller
		   is null. This value can be null, in some cases. A null
		   value is not an error. */
		if (cookieHttpOnly == null &&
			  cookieHttpOnly != null) {
	    String  errorText = "Cookie HTTP only value passed to setCookie is null";
	    throw new NullPointerException(errorText);
	  }
		/* Check if the maximum cookie age value passed by the caller is null.
	     This value can be null, in some cases. A null value is not an error. */
  	if (cookieMaxAge == null &&
			  cookieMaxAge != null) {
	    String  errorText = "Cookie maximum age value passed to setCookie is null";
      throw new NullPointerException(errorText);
	  }
		/* Check if the same site cookie value passed by the caller is null.
	     This value can be null, in some cases. A null value is not an
	     error. */
		if (cookieSameSite == null &&
			  cookieSameSite != null) {
	   String  errorText = "Cookie same site value passed to setCookie is null";
	   throw new NullPointerException(errorText);
	 }
		/* Sadly, the Jetty code won't build a cookie from JSON. The JSON has double
		   quote characters that offend Jetty (real browsers dont care). As a
		   consequence, we must URL encode the cookie value before we try to create a
		   cookie from it. */
		String cookieValueEncoded = UrlEncoded.encodeString(cookieValue);
		/* Build and add the new cookie */
		Cookie  newCookie = new Cookie(cookieName, cookieValueEncoded);
		/* Check if the caller provided a cookie path value */
		if (cookiePath != null)
			newCookie.setPath(cookiePath);
		/* Check if the caller requested a secure cookie */
		if (cookieSecure != null && 
				cookieSecure == HDLmSecureCookie.SECURECOOKIETRUE)
			newCookie.setSecure(true);
		else
			newCookie.setSecure(false);
		/* Check if the caller requested an HTTP only cookie */
		if (cookieHttpOnly != null && cookieHttpOnly == HDLmHttpOnly.HTTPONLYTRUE)
			newCookie.setHttpOnly(true);
		else
			newCookie.setHttpOnly(false);
		/* Set the maximum age for the new cookie, if need be */
		if (cookieMaxAge != null)
			newCookie.setMaxAge(cookieMaxAge);
		/* Add the new cookie */
		response.addCookie(newCookie);
		/* Check if the caller provided a same site value. Use the value,
		   if it was provided. */
		if (cookieSameSite != null) {
  	  String  cookieStr = HDLmJetty.getHeaderFromResponse(response, "Set-Cookie");
  	  cookieStr = "Set-Cookie: " + cookieStr;
  	  cookieStr += "; SameSite=" + cookieSameSite;
  	  HDLmJetty.setHeader(response, cookieStr);			
		}		
		return;
	}
	/* This routine tries to add the header passed to it, to the servlet response
	   object. The servlet response object may already have some headers set. The
	   caller must deal with this issue (if it is a problem). The header passed by
	   the caller is just a Java string. This routine handles breaking the string
	   down as need be.

	   If the headers need to be modified, they must be changed before this routine
	   is called. */
	protected static void setHeader(HttpServletResponse response, String outputHeader) {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to setHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output header passed by the caller is null */
		if (outputHeader == null) {
			String  errorText = "Output header passed to setHeader is null";
			throw new NullPointerException(errorText);
		}
		/* Process the output header */
		int colonIndex = outputHeader.indexOf(':');
		if (colonIndex <= 0) {
			String errorFormat = "Colon not found or misplaced in HTTP header (%s)";
			String  errorText = String.format(errorFormat, outputHeader);
			HDLmAssertAction(false, errorText);
		}
		/*
		   Split the HTTP header string into two parts. The first part is the header
		   type. The second part is the header value. Note that we try to skip the
		   leading blank in the header value.
		 */
		int outputHeaderLength = outputHeader.length();
		String headerType = outputHeader.substring(0, colonIndex);
		if ((colonIndex + 1) < outputHeaderLength && outputHeader.charAt(colonIndex + 1) == ' ')
			colonIndex++;
		String headerValue = outputHeader.substring(colonIndex + 1);
		response.setHeader(headerType, headerValue);
	}
	/* This routine tries to add all of the headers passed to it, to the servlet
	   response object. The servlet response object may already have some headers
	   set. The caller must deal with this issue (if it is a problem). The headers
	   passed by the caller are just Java strings. This routine handles breaking
	   down each string as need be.

	   If the headers need to be modified, they must be changed before this routine
	   is called. */
	protected static void setHeaders(HttpServletResponse response, ArrayList<String> outputHeaders) {
		/* Check if the output response passed by the caller is null */
		if (response == null) {
			String  errorText = "Servlet response passed to setHeaders is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output headers passed by the caller are null */
		if (outputHeaders == null) {
			String  errorText = "Output headers passed to setHeaders are null";
			throw new NullPointerException(errorText);
		}
		/* Process each output header */
		for (String header : outputHeaders) {
			int colonIndex = header.indexOf(':');
			if (colonIndex <= 0) {
				String errorFormat = "Colon not found or misplaced in HTTP header (%s)";
				String  errorText = String.format(errorFormat, header);
				HDLmAssertAction(false, errorText);
			}
			/* Split the HTTP header string into two parts. The first part is the header
			   type. The second part is the header value. Note that we try to skip the
			   leading blank in the header value. */
			int headerLength = header.length();
			String headerType = header.substring(0, colonIndex);
			if ((colonIndex + 1) < headerLength && header.charAt(colonIndex + 1) == ' ')
				colonIndex++;
			String headerValue = header.substring(colonIndex + 1);
			response.setHeader(headerType, headerValue);
		}
	}
	/* The method below builds and stores a cookie using values
	   passed by the caller. The cookie value is probably a JSON
	   string with only the session ID (in string form). Note that
	   the caller passes the cookie maximum age value, which may 
	   be null.
	   
	   The session parameters, the session index value, and the
	   session use mode are not stored in the cookie. They are 
	   stored in the session cache and the session object (if 
	   one exists). */ 
	protected static void storeCookie(HttpServletResponse response,
			                              final String cookieValue,
			                              final Integer cookieMaxAge,
			                              final String cookieSameSite) {
		/* Check if the HTTP servlet response passed by the caller is null */
		if (response == null) {
		  String  errorText = "HTTP servlet response passed to storeCookie is null";
	    throw new NullPointerException(errorText);
		}
		/* Check if the cookie value passed by the caller is null */
		if (cookieValue == null) {
		  String  errorText = "Cookie value passed to storeCookie is null";
	    throw new NullPointerException(errorText);
		}
		/* Check if the maximum cookie age value passed by the caller is null.
		   This value can be null, in some cases. A null value is not an error. */
		if (cookieMaxAge == null &&
				cookieMaxAge != null) {
		  String  errorText = "Maximum age value passed to storeCookie is null";
	    throw new NullPointerException(errorText);
		}
		/* Check if the same site cookie value passed by the caller is null.
       This value can be null, in some cases. A null value is not an
       error. */
	  if (cookieSameSite == null &&
	  	  cookieSameSite != null) {
      String  errorText = "Cookie same site value passed to setCookie is null";
      throw new NullPointerException(errorText);
    }
		/* Get the cookie name for use later */
    String   cookieName = HDLmDefines.getString("HDLMSESSIONCOOKIE");
		if (cookieName == null) {
			String   errorFormat = "Define value for session cookie name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSESSIONCOOKIE");
			HDLmAssertAction(false, errorText);
		}
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to setCookie in storeCookie");
    HDLmJetty.setCookie(response,
    		                cookieName,
    		                cookieValue,
    		                "/",
    		                HDLmSecureCookie.SECURECOOKIETRUE,
    		                HDLmHttpOnly.HTTPONLYTRUE,
    		                cookieMaxAge,
    		                cookieSameSite);
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After call to setCookie in storeCookie");
	}
}
package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.jupiter.api.Test;
/**
 * HDLmJettyTest short summary.
 *
 * HDLmJettyTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmJettyTest {
	@Test
	void buildHttpConnector() {
  	/* Start a new Jetty server with no port number */
    Server server = new Server();
		/* Run a few buildHttpConnector tests */ 
		ServerConnector  httpConnector;
		httpConnector = HDLmJetty.buildHttpConnector(server, 80);
		assertNotNull(httpConnector, "HDLmJetty.buildHttpConnector should have returned a non-null value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.buildHttpConnector(null, 80);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Jetty server instance passed to buildHttpConnector is null");
		}
		{
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmJetty.buildHttpConnector(server, -1);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTTP port number passed to buildHttpConnector is invalid");
		}
	}
	@Test
	void buildHttpsConnector() throws KeyStoreException, 
	                                  NoSuchAlgorithmException, 
	                                  CertificateException, 
	                                  FileNotFoundException, 
	                                  IOException, 
	                                  UnrecoverableKeyException {
  	/* Start a new Jetty server with no port number */
    Server server = new Server();
    /* Build a KeyStore instance for use below. This is the default
       Jetty keystore for now. */
    KeyStore keyStore = HDLmJetty.buildKeyStore();
    /*  Build the SSL Context Factory for HTTPS and HTTP/2. */
    SslContextFactory.Server  sslContextFactoryDotServer = HDLmJetty.buildSslContextFactoryDotServer(keyStore);
		/* Run a few buildHttpsConnector tests */ 
		ServerConnector  httpConnector;
		ServerConnector  http2Connector;
		httpConnector = HDLmJetty.buildHttpConnector(server, 80);	 
		assertNotNull(httpConnector, "HDLmJetty.buildHttpConnector should have returned a non-null value");
		http2Connector = HDLmJetty.buildHttpsConnector(server, 
				                                           sslContextFactoryDotServer, 
				                                           httpConnector,
				                                           443);
		assertNotNull(http2Connector, "HDLmJetty.buildHttpsConnector should have returned a non-null value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.buildHttpsConnector(null, 
					                              		                                  sslContextFactoryDotServer, 
					                              		                                  httpConnector,
					                              		                                  443);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Jetty server instance passed to buildHttpsConnector is null");
		}
		{
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmJetty.buildHttpsConnector(server, 
					                              		                                  sslContextFactoryDotServer, 
					                              		                                  httpConnector,
					                              		                                  -1);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTTPS port number passed to buildHttpsConnector is invalid");
		}
	}
	@Test
	void buildHttpsConnectorOld() throws KeyStoreException, 
	                                     NoSuchAlgorithmException, 
	                                     CertificateException, 
	                                     FileNotFoundException, 
	                                     IOException, UnrecoverableKeyException {
  	/* Start a new Jetty server with no port number */
    Server server = new Server();
    /* Build a KeyStore instance for use below. This is the default
       Jetty keystore for now. */
    KeyStore keyStore = HDLmJetty.buildKeyStore();
    /*  Build the SSL Context Factory for HTTPS and HTTP/2. */
    SslContextFactory.Server  sslContextFactoryDotServer = HDLmJetty.buildSslContextFactoryDotServer(keyStore);
		/* Run a few buildHttpsConnectorOld tests */ 
		ServerConnector  httpConnector;
		httpConnector = HDLmJetty.buildHttpsConnectorOld(server, sslContextFactoryDotServer, 443);
		assertNotNull(httpConnector, "HDLmJetty.buildHttpsConnectorOld should have returned a non-null value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.buildHttpsConnectorOld(null, 
					                              		                                     sslContextFactoryDotServer, 
					                              		                                     443);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Jetty server instance passed to buildHttpsConnectorOld is null");
		}
		{
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmJetty.buildHttpsConnectorOld(server, 
                                                                                 sslContextFactoryDotServer, 
                                                                                 -1);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTTPS port number passed to buildHttpsConnectorOld is invalid");
		}
	}
	@Test
	void buildKeyStore() throws KeyStoreException, 
	                            NoSuchAlgorithmException, 
	                            CertificateException, 
	                            FileNotFoundException, 
	                            IOException, UnrecoverableKeyException {
		/* Run a few buildKeystore tests */ 
		KeyStore   keyStore; 
		keyStore = HDLmJetty.buildKeyStore();
		assertNotNull(keyStore, "HDLmJetty.buildKeyStore should have returned a non-null value");
	}
	@Test
	void buildSslContextFactory() throws KeyStoreException, 
	                                     NoSuchAlgorithmException, 
	                                     CertificateException, 
	                                     FileNotFoundException, 
	                                     IOException, UnrecoverableKeyException {
    /* Build a KeyStore instance for use below. This is the default
       Jetty keystore for now. */
    KeyStore keyStore = HDLmJetty.buildKeyStore();
		/* Run a few buildSslContextFactory tests */ 
    SslContextFactory.Server  sslContextFactoryDotServer = HDLmJetty.buildSslContextFactoryDotServer(keyStore);
		assertNotNull(sslContextFactoryDotServer, 
				          "HDLmJetty.buildSslContextFactory should have returned a non-null value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.buildSslContextFactoryDotServer(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "KeyStore instance passed to buildSslContextFactory is null");
		}
	}
	@Test
	void dumpRequestContents() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.dumpRequestContents(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to dumpRequestContents is null");
		}
	}
	@Test
	void editorGet() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.editorGet(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to editorGet is null");
		}
	}
	@Test
	void editorPost() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.editorPost(null, null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to editorPost is null");
		}
	}
	@Test
	void forceRedirect() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.forceRedirect(null, null, null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet response passed to forceRedirect is null");
		}
	}
	@Test
	void getClientInformationNew() {
		/* Run a few getClientInformationNew tests */
		HDLmBrowserTypes   browserType;
	  HDLmClientInfo     clientInfo;
		String             userAgent;
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148";
		clientInfo = HDLmJetty.getClientInformationNew(userAgent);
		assertNotNull(clientInfo, "HDLmJetty.getClientInformation should not have returned a null value");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Linux; U; Android 2.2) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
		clientInfo = HDLmJetty.getClientInformationNew(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.ANDROID, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
		clientInfo = HDLmJetty.getClientInformationNew(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.CHROME, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134";
		clientInfo = HDLmJetty.getClientInformationNew(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.EDGE, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0";
		clientInfo = HDLmJetty.getClientInformationNew(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.FIREFOX, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)";
		clientInfo = HDLmJetty.getClientInformationNew(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.IE, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36 OPR/43.0.2442.991";
		clientInfo = HDLmJetty.getClientInformationNew(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.OPERA, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";
		clientInfo = HDLmJetty.getClientInformationNew(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.SAFARI, browserType, "Browser type is not correct");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getClientInformationNew(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "User-Agent string passed to getClientInformationNew is null");
		}
	}
	@Test
	void getClientInformationOld() {
		/* Run a few getClientInformationOld tests */
		HDLmBrowserTypes   browserType;
	  HDLmClientInfo     clientInfo;
		String             userAgent;
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148";
		clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		assertNotNull(clientInfo, "HDLmJetty.getClientInformation should not have returned a null value");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Linux; U; Android 2.2) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
		clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.ANDROID, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
		clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.CHROME, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134";
		clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.EDGE, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0";
		clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.FIREFOX, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)";
		clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.IE, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36 OPR/43.0.2442.991";
		clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.OPERA, browserType, "Browser type is not correct");
		/* Run a getClientInformation test */
		userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1";
		clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		browserType = clientInfo.getBrowserType();
		assertEquals(HDLmBrowserTypes.SAFARI, browserType, "Browser type is not correct");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getClientInformationOld(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "User-Agent string passed to getClientInformationOld is null");
		}
	}
	@Test
	void getContentType() {
		/* Run a few getContentType tests */
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getContentType(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getContentType is null");
		}
	}
	@Test
	void getCookie() {
		/* Run a few getCookie tests */
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getCookie(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getCookie is null");
		}
	}
	@Test
	void getCookieExtended() {
		/* Run a few getCookieExtended tests */
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getCookieExtended(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getCookieExtended is null");
		}
	}
	@Test
	void getCookies() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getCookies(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getCookies is null");
		}
	}
	@Test
	void getCookiesMap() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getCookiesMap(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getCookiesMap is null");
		}
	}
	@Test
	void getRequestHeaders() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getRequestHeaders(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getRequestHeaders is null");
		}
	}
	@Test
	void getHostHeader() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getHostHeader(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getHostHeader is null");
		}
	}
	@Test
	void getHostName() {
		/* Run a few getHostName tests */
		String  hostName;
		String  inputURL;
		hostName = HDLmJetty.getHostName("");
		assertEquals(null, hostName, "HDLmJetty.getHostName should have returned a null value");
		hostName = HDLmJetty.getHostName("ww");
		assertEquals("ww", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("ww.");
		assertEquals("ww.", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("ww.xx");
		assertEquals("ww.xx", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("ww.xx:");
		assertEquals("ww.xx", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("ww.xx:80");
		assertEquals("ww.xx", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("ww.xx:80/");
		assertEquals("ww.xx", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("ww.xx/");
		assertEquals("ww.xx", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("http://ww.xx/");
		assertEquals("ww.xx", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("https://ww.xx/");
		assertEquals("ww.xx", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("https://ww.xx");
		assertEquals("ww.xx", hostName, "HDLmJetty.getHostName should have a corect value");
		hostName = HDLmJetty.getHostName("https://www.abc.com:80/");
		assertEquals("www.abc.com", hostName, "HDLmJetty.getHostName should have a corect value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getHostName(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Input string value passed to getHostName is null");
		}
		{
			String   inputURLLocal = "http*//www.abc.com/";
			Throwable exception = assertThrows(IllegalStateException.class, 
					                               () -> {HDLmJetty.getHostName(inputURLLocal);},
					                               "Expected IllegalStateException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Colon value not found after http/https prefix");
		}
		{
			String   inputURLLocal = "http:*/www.abc.com/";
			Throwable exception = assertThrows(IllegalStateException.class, 
					                               () -> {HDLmJetty.getHostName(inputURLLocal);},
					                               "Expected IllegalStateException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "First slash value not found after http/https prefix and colon");
		}
		{
			String   inputURLLocal = "http:/*/www.abc.com/";
			Throwable exception = assertThrows(IllegalStateException.class, 
					                               () -> {HDLmJetty.getHostName(inputURLLocal);},
					                               "Expected IllegalStateException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Second slash value not found after first slash");
		}
	}
	@Test
	void getHostNameFromRequest() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getHostNameFromRequest(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getHostNameFromRequest is null");
		}
	}
	@Test
	void getLocalPort() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getLocalPort(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getLocalPort is null");
		}
	}
	@Test
	void getOriginalPathValue() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getOriginalPathValue(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getOriginalPathValue is null");
		}
	}
	@Test
	void getPathValueString() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getPathValueString(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getPathValueString is null");
		}
	}
	@Test
	void getPortNumber() {
		/* Run a few getPortNumber tests */ 
		Integer  portNumber;
		String   inputString;
		/* Run a getPortNumber test */
		inputString = "abc";
		portNumber = HDLmJetty.getPortNumber(inputString);
		assertEquals(null, portNumber, "HDLmJetty.getPortNumber should have returned a null value");
		/* Run a getPortNumber test */
		inputString = "";
		portNumber = HDLmJetty.getPortNumber(inputString);
		assertEquals(null, portNumber, "HDLmJetty.getPortNumber should have returned a null value");
		/* Run a getPortNumber test */
		inputString = "abc:80";
		portNumber = HDLmJetty.getPortNumber(inputString);
		assertEquals(80, portNumber, "HDLmJetty.getPortNumber should have returned a value of 80");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getPortNumber(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Input string value passed to getPortNumber is null");
		}
	}
	@Test
	void getQueryString() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getQueryString(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getQueryString is null");
		}
	}
	@Test
	void getRequestPayloadBinary() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getRequestPayloadBinary(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getRequestPayloadBinary is null");
		}
	}
	@Test
	void getRequestPayloadChars() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getRequestPayloadChars(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getRequestPayloadChars is null");
		}
	}
	@Test
	void getResponseHeader() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getResponseHeader(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet response passed to getResponseHeader is null");
		}
	}
	@Test
	void getSetSystemProperties() {}
	@Test
	void getUserAgentHeader() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.getUserAgentHeader(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to getUserAgentHeader is null");
		}
	}
	@Test
	void handleProxy() {
		{
			String  localClientStr = "dummy";
			String  localTimeStamp = HDLmUtility.getUtcTimeStampNow();
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.handleProxy(null, 
					                              		                          null, 
					                              		                          localClientStr,
					                              		                          localTimeStamp,
					                              		                          null, 
					                              		                          null, 
					                              		                          null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to handleProxy is null");
		}
	}
	@Test
	void handleSpecialPost() {
		{
			HDLmHttpTypes  httpType = HDLmHttpTypes.POST;
			String         localClientStr = "dummy";
			String         localTimeStamp = HDLmUtility.getUtcTimeStampNow();
			String         pathValueString = "dummy";
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.handleSpecialPost(null, 
					                              		                                null,
					                              		                                null,
					                              		                                null,
					                              		                                localClientStr,
					                              		                                localTimeStamp,
					                              		                                httpType,
					                              		                                pathValueString);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to handleSpecialPost is null");
		}
	}
	@Test
	void isBrowserOk() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.isBrowserOk(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to isBrowserOk is null");
		}
	}
	@Test
	void processFile() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.processFile(null, null, null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet request passed to processFile is null");
		}
	}
	@Test
	void reportError() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.reportError(null, 0, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet response passed to reportError is null");
		}
	}
	@Test
	void reportErrorBasic() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.reportErrorBasic(null, 0, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet response passed to reportErrorBasic is null");
		}
	}
	@Test
	void setHeader() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.setHeader(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet response passed to setHeader is null");
		}
	}
	@Test
	void setHeaders() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmJetty.setHeaders(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Servlet response passed to setHeaders is null");
		}
	}
}
package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This servlet provides all of the functions needed to support the
 * proxy server and the editors. Most requests are handled by this
 * code. A few requests are sent to other routines for handling. 
 * Note that this routine does not really support the editors. All
 * of the editor support is in other routines. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmMainServlet extends HttpServlet {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmMainServlet.class);
	/* Handle the current servlet operations. The code is the same for 
     all cases, as it turns out. */
	protected void doAll(HttpServletRequest request, 
			                 HttpServletResponse response,
			                 HDLmHttpTypes httpType)
			                 throws IOException {
		/* Get the host name specified by the client. This call does not
		   always return a valid host name. For some reason, this call
		   returns null in some cases. We must be able to handle a null
		   host name value. */
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After entry to doAll"); 
		String   hostName = HDLmJetty.getHostNameFromRequest(request);
		if (hostName == null)
			return;
		/* Get the client IP address and port number string. Also get
		   the timestamp. Both values are needed below. */
		String  clientStr = HDLmJetty.getRemoteIpAndPort(request);
		String  timeStamp = HDLmUtility.getUtcTimeStampNow();
		/* In some cases, users try to connect using an unsecured port.
	     We need to redirect them to the secured port immediately.
	     The secured port using SSL/TLS (these days HTTP2 over SSL/TLS). */ 
    int  requestLocalPort =  HDLmJetty.getLocalPort(request);
    String  requestPostPayload = null;
    /* At this point we need to build an access message for all requests.
       This message is generated for all requests and should be helpful
       for debugging purposes. */
    if (true) {
    	String  requestOriginalPathValue = HDLmJetty.getOriginalPathValue(request);
			String  accessFormat = "Access from %s - port %d - using %s - host name %s - original path value %s";
			String  accessMessage = String.format(accessFormat,
					                                  clientStr,
					                                  requestLocalPort,
					                                  httpType.toString(),
					                                  hostName,
					                                  requestOriginalPathValue);  
			/* Check if we need to add the post payload to the current access messaspge. This
			   is only done in some cases. The reason for doing this is so that the post
			   payload will be in the log file. */
			if (httpType == HDLmHttpTypes.POST) {	
				/* LOG.info("HDLmMainServlet HTTP POST"); */
			  requestPostPayload = HDLmJetty.getPostPayload(request, httpType, requestOriginalPathValue);
        if (requestPostPayload != null) {
        	/* LOG.info(requestPostPayload); */
        	String  modifiedPostPayload = requestPostPayload;
        	modifiedPostPayload = HDLmJson.maskStringInJson(modifiedPostPayload, 
        			                                             "\"PASSWORD\"");
        	modifiedPostPayload = HDLmJson.maskStringInJson(modifiedPostPayload, 
                                                           "\"Password\"");
        	modifiedPostPayload = HDLmJson.maskStringInJson(modifiedPostPayload,
        			                                             "\"password\"");
        	if (modifiedPostPayload.equals("[object Object]") == false)
				    accessMessage += " - Post Payload - " + modifiedPostPayload;
        }
			}
			LOG.info(accessMessage);    	
			/* Check if the host name is made up of numbers and periods. This
		     (for example, 3.14.54.178) is not allowed. We only allow host
		     names with strings in them. */
		  if (hostName.matches("(\\d|\\.)*") == true) {
			  response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);	
			  return;
		  }
    }    
    HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After logging access message in doAll"); 
    /* Note that an inbound request has been received */
    String  eventName = "Inbound Request";
    HDLmEvent.eventOccurred(eventName);
    /* We need to keep track of how long this request took 
       to process */
    Instant   instantStart = Instant.now();
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
	    /* Check if the current request came in on an unsecured port. 
	       Force a redirect if need be. This code isn't really needed,
	       at least for OWO. It turns out that OWO will return an HTTP
	       code of 301 (permanent redirect) for a request than comes in
	       on port 80. This code is very specific to OWO and Accesso. */	   
			if (requestLocalPort == 80) {
			 	String   pathValueString = HDLmJetty.getPathValueString(request);
			  HDLmJetty.forceRedirect(response, 
							                  hostName,
							                  null,
							                  pathValueString);
			  break; 	  		  	    	  	
			}
			/* Check if the host name shows that this might be a request 
			   for one of the editors. Note that this test is not definitive.
			   The current operation might be a request for one of the built-in
			   commands. */
			boolean  hostNameJavaCheck = false;
			if (hostName.startsWith("javaproxy"))
				hostNameJavaCheck = true;
			/* Get the current path value string. This is only possible in some 
			   cases. The path value string is checked below for a set of specific
			   command values. */	 
			String  pathValueString = null;
			if (httpType == HDLmHttpTypes.GET ||
					httpType == HDLmHttpTypes.POST)
				pathValueString = HDLmJetty.getPathValueString(request);
			/* Check if this is a request for the generic JavaScript
			   program. If this true, then we set a flag to true that
			   can be checked later. */ 
			boolean   getGenericJsCheck = false;
			if (httpType == HDLmHttpTypes.GET) {
		    /* Get the name used for the JavaScript file constructed by 
           this code. The name is used in several places. As a 
           consequence, the name is maintained as a define value. */
        String  getJSName = HDLmDefines.getString("HDLMGETJSVALUE");
		    if (getJSName == null) {
		   	  String   errorFormat = "Define value for get JavaScript file name not found (%s)";
				  String   errorText = String.format(errorFormat, "HDLMGETJSVALUE");
				  HDLmAssertAction(false, errorText);		    	
		    }						
		    /* Check if this is a request for the JavaScript program we really want */
	      if (pathValueString.equals("/" + getJSName + ".js")) {
	      	getGenericJsCheck = true;
				}
	      HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After check for generic JavaScript in doAll"); 
			}
			/* We assume that the current request is not a special 
			   post. Most of the time that is true. However, some
			   special posts are sent to the server. */
			boolean   specialPostCheck = false;
			if (httpType == HDLmHttpTypes.POST) {
		    /* Get the name used for special posts. The name
		       is used in several places. As a consequence, 
		       the name is maintained as a define value. */
        String  specialPostName = HDLmDefines.getString("HDLMPOSTDATA");
		    if (specialPostName == null) {
		   	  String   errorFormat = "Define value for special post name not found (%s)";
				  String   errorText = String.format(errorFormat, "HDLMPOSTDATA");
				  HDLmAssertAction(false, errorText);		    	
		    }						
		    /* Check if this is a request to post special data */
	      if (pathValueString.equals("/" + specialPostName)) {
	        specialPostCheck = true;
				}
			}
			/* We assume that the current request is not an invoke 
			   API post. Most of the time that is true. However, some
			   invoke API posts are sent to the server. */
			boolean   invokeApiCheck = false;
			if (httpType == HDLmHttpTypes.POST) {
		    /* Get the name used for invoke API posts. The name
		       is used in several places. As a consequence, 
		       the name is maintained as a define value. */
	      String  invokeApiName = HDLmDefines.getString("HDLMINVOKEAPI");
		    if (invokeApiName == null) {
		   	  String   errorFormat = "Define value for invoke API name not found (%s)";
				  String   errorText = String.format(errorFormat, "HDLMINVOKEAPI");
				  HDLmAssertAction(false, errorText);		    	
		    }						
		    /* Check if this is a request to invoke an API */
	      if (pathValueString.equals("/" + invokeApiName)) {
	        invokeApiCheck = true;
				}
			}
			/* We assume that the current request is not a set
			   test mode on or off request. Set test mode requests
			   are sent to the server.    */
			boolean   setTestCheck = false;
			if (httpType == HDLmHttpTypes.POST) {
		    /* Get the name used for set test mode off posts.
		       The name is used in several places. As a 
		       consequence, the name is maintained as a 
		       define value. */
	      String  setTestModeOffName = HDLmDefines.getString("HDLMBUILDRULESSETTESTOFF");
		    if (setTestModeOffName == null) {
		   	  String   errorFormat = "Define value for set test mode off name not found (%s)";
				  String   errorText = String.format(errorFormat, "HDLMBUILDRULESSETTESTOFF");
				  HDLmAssertAction(false, errorText);		    	
		    }		
		    /* Get the name used for set test mode on posts.
	         The name is used in several places. As a 
	         consequence, the name is maintained as a 
	         define value. */
	      String  setTestModeOnName = HDLmDefines.getString("HDLMBUILDRULESSETTESTON");
		    if (setTestModeOnName == null) {
		   	  String   errorFormat = "Define value for set test mode on name not found (%s)";
				  String   errorText = String.format(errorFormat, "HDLMBUILDRULESSETTESTON");
				  HDLmAssertAction(false, errorText);		    	
		    }
		    /* Check if this is a request to invoke an API */
				if (pathValueString.equals("/" + setTestModeOffName) || 
						pathValueString.equals("/" + setTestModeOnName)) {
	         setTestCheck = true;
				}
			}
			/* We assume that the current request is not for a set
			   of options. Most of the time that is true. However,
			   some options requests are sent to the server. */
		  boolean   httpOptionsCheck = false;
		  if (httpType == HDLmHttpTypes.OPTIONS) {
		  	httpOptionsCheck = true;			
		  }			
			/* Check if this is a request for one of the built-in  
	       action commands. If we find a built-in action 
	       command, we pass it to an appropriate handler. */
			boolean  actionCommandCheck = false;
			if (pathValueString != null &&
					(pathValueString.equals("/passthru-check")     ||
					 pathValueString.equals("/passthru-check/")    || 
					 pathValueString.equals("/passthru-disable")   ||
					 pathValueString.equals("/passthru-disable/")  || 
					 pathValueString.equals("/passthru-display")   ||
					 pathValueString.equals("/passthru-display/")  || 
					 pathValueString.equals("/passthru-enable")    ||
				   pathValueString.equals("/passthru-enable/")   ||
				   pathValueString.equals("/passthru-get")       ||
			     pathValueString.equals("/passthru-get/")))
			  actionCommandCheck = true;
			/* Check if this is a request for one of the built-in contents 
		     information commands. If we find a built-in contents status 
		     command, we pass it to an appropriate handler. */
			boolean  contentsCommandCheck = false;
			if (pathValueString != null &&
					(pathValueString.equals("/appthr-contents")     ||
					 pathValueString.equals("/appthr-contents/")    || 
					 pathValueString.equals("/cluster-contents")    ||
				   pathValueString.equals("/cluster-contents/")   || 
					 pathValueString.equals("/memory-contents")     ||
				   pathValueString.equals("/memory-contents/")    || 
					 pathValueString.equals("/phash-contents")      || 
				   pathValueString.equals("/phash-contents/")     || 
				   pathValueString.equals("/sessionId-contents")  || 
					 pathValueString.equals("/sessionId-contents/") ||
				   pathValueString.equals("/systhr-contents")     || 
			     pathValueString.equals("/systhr-contents/")))
			  contentsCommandCheck = true;		
			/* Check for a get request of some type */ 
		  String  getDataKey = HDLmDefines.getString("HDLMGETDATA");
   		if (getDataKey == null) {
   			String   errorFormat = "Define value for get data key not found (%s)";
   			String   errorText = String.format(errorFormat, "HDLMGETDATA");
   			HDLmAssertAction(false, errorText);		    	
   		}
   		boolean  getDataCheck = false;
			if (pathValueString != null &&
				  (pathValueString.equals("/" + getDataKey)))
			  getDataCheck = true;
			/* Check for and handle one of the server status commands */
			boolean  statusCommandCheck = false;
			if (pathValueString != null &&
				  (pathValueString.equals("/appthr-status")           ||
					 pathValueString.equals("/appthr-status/")          ||	
				   pathValueString.equals("/connection-status")       ||
				   pathValueString.equals("/connection-status/")      ||	
					 pathValueString.equals("/elapsed-status")          ||
					 pathValueString.equals("/elapsed-status/")         || 
					 pathValueString.equals("/events-checkAnomalies")   ||
					 pathValueString.equals("/events-checkAnomalies/")  || 
					 pathValueString.equals("/events-checkExceptions")  ||
					 pathValueString.equals("/events-checkExceptions/") ||
					 pathValueString.equals("/events-status")           ||
					 pathValueString.equals("/events-status/")          || 
					 pathValueString.equals("/memory-status")           ||
					 pathValueString.equals("/memory-status/")          || 
				   pathValueString.equals("/passthru-status")         ||
				   pathValueString.equals("/passthru-status/")        ||				   
				   pathValueString.equals("/phash-status")            ||
				   pathValueString.equals("/phash-status/")           ||
					 pathValueString.equals("/rules-status")            ||
					 pathValueString.equals("/rules-status/")           || 
					 pathValueString.equals("/server-status")           ||
				   pathValueString.equals("/server-status/")          ||
					 pathValueString.equals("/sessionId-status")        ||
					 pathValueString.equals("/sessionId-status/")       ||		
			     pathValueString.equals("/systhr-status")           ||
			     pathValueString.equals("/systhr-status/")          ||
			     pathValueString.equals("/timings-status")          ||
			     pathValueString.equals("/timings-status/")))
				statusCommandCheck = true;		 
			/* Check for and handle a bridge request */
			boolean  bridgeRequestCheck = false;
			if (pathValueString != null &&
				  (pathValueString.startsWith("/io/bucket/")))
				bridgeRequestCheck = true;	
			/* Check if this is a request for one of the editors */
			if (hostNameJavaCheck    == true  &&
					actionCommandCheck   == false &&
					contentsCommandCheck == false &&
				  getDataCheck         == false &&
					statusCommandCheck   == false &&
					getGenericJsCheck    == false &&
					specialPostCheck     == false &&
					invokeApiCheck       == false &&
					setTestCheck         == false &&
					httpOptionsCheck     == false &&
					bridgeRequestCheck   == false) {
				if (httpType == HDLmHttpTypes.GET) {
					HDLmJetty.editorGet(request, response);				
				}
				if (httpType == HDLmHttpTypes.POST) {
					HDLmJetty.editorPost(request, 
							                 response, 
							                 requestPostPayload);				
				}
				break;		
			}
			/* Check if this is a request for obtaining a perceptual hash value */
			String  pHashNameStr = "/" + HDLmConfigInfo.getPHashName();
			if (pathValueString != null &&
					pathValueString.equals(pHashNameStr)) {
				/* LOG.info("HDLmMainServlet - PHash"); */
				if (httpType == HDLmHttpTypes.POST) {
					HDLmJetty.editorPost(request, 
							                 response, 
							                 requestPostPayload);				
				}
				break;	
			}		
			/* Check if this is a request for the proxy handler. In 
			   some cases, the request will be sent to a host name
			   such as www.oneworldobserveratory.com rather than 
			   the expected javaproxya.dnsalias.com. We must check
			   for and handle this case. The server name (javaproxya.
			   dnsalias.com) is hard-coded here. That is OK because
			   this is just a comment. */ 
			String  proxyNameStr = HDLmConfigInfo.getProxyName();
			if (pathValueString != null &&
				  pathValueString.equals("/" + proxyNameStr)) {  		
				if (httpType == HDLmHttpTypes.POST) {
					HDLmJetty.editorPost(request, 
							                 response,
							                 requestPostPayload);				
				}
				break;	
			}
			/* Check if this is a request for one of the built-in  
		     action commands. If we find a built-in action  
		     command, we pass it to an appropriate handler. */
			if (actionCommandCheck) {
				HDLmJetty.handleActionCommands(pathValueString,
						                           hostName,
						                           request, 
												               response,
												               clientStr,
												               timeStamp);
				break;
			}
			/* Check if this is a request for one of the built-in contents 
			   information commands. If we find a built-in contents status 
			   command, we pass it to an appropriate handler. */
			if (contentsCommandCheck) {
				HDLmJetty.handleContentsCommands(pathValueString,
						                             hostName,
						                             request, 
												                 response,
												                 clientStr,
												                 timeStamp);
				break;
			}
			/* Check if this is a request to get some data. This type
			   of operation is passed to a handler that does the actual 
			   work. */   
			if (getDataCheck) {
				HDLmJetty.handleGetData(pathValueString,
		                            hostName,
		                            request, 
								                response,
								                clientStr,
								                timeStamp);
				break;
			}
			/* Check for and handle one of the status commands */
			if (statusCommandCheck) {
				HDLmJetty.handleStatusCommands(pathValueString,
						                           hostName,
						                           request, 
												               response,
												               clientStr,
												               timeStamp);
				break;
			}	   
			/* Check if this is a post operation that must be handled by
			   this server and not sent to the actual backend host. This
			   will be true, if the browser is trying to send changes for 
			   logging. */  
			if (specialPostCheck == true) { 	
				HDLmJetty.handleSpecialPost(request, 
											              response,
											              hostName,
											              requestPostPayload,
											              clientStr,
											              timeStamp,
											              httpType,
											              pathValueString);
				break;	 
			}		
			/* Check if this is an invoke API operation that must be handled
		     by this server and not sent to the actual backend host. This
		     will be true, if the browser is trying to send request to run
		     an API. */   
			if (invokeApiCheck == true) { 	
				HDLmJetty.handleInvokeApi(request, 
											            response,
											            hostName,
											            requestPostPayload, 
											            timeStamp,
											            httpType,
											            pathValueString);
				break;	 
			}	
			/* Check if this is a set test operation that must be handled
		     by this server and not sent to the actual backend host. This
		     will be true, if the browser is trying to send a requst to 
		     turn test mode on or off. */   
			if (setTestCheck == true) { 	
				HDLmJetty.handleSetTest(request, 
											          response,
											          pathValueString);
				break;	 
			}	
			/* Check if this is a request for the generated JavaScript program.
			   In some (quite important) cases, each web page will request a 
			   copy of this JavaScript program by sending a request to server. */
			if (getGenericJsCheck == true) {			
				HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before handleJavaScriptRequest in doAll"); 
      	HDLmJetty.handleJavaScriptRequest(request, response, clientStr,
                                          timeStamp);
      	HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After handleJavaScriptRequest in doAll"); 
      	int   responseCode = response.getStatus();
      	/* LOG.info("In getGenericJsCheck"); */
      	/* LOG.info(String.valueOf(responseCode)); */
      	break;      
			}			 
			/* Check if this is an options request that must be handled by
			   this server and not sent to the actual backend host. if this
			   is true, if the browser is probably trying to preflight a post 
			   that will come later. */  
			if (httpOptionsCheck == true) { 	  
				HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before handleOptionsRequest in doAll"); 
				HDLmJetty.handleOptionsRequest(request, 
											                 response,
											                 hostName);
				HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After handleOptionsRequest in doAll"); 
				break; 
			}	
			/* Check if this a bridge request that must be handled by
			   this server. Pass the request to a routine that will 
			   handle bridge processing. */
			if (bridgeRequestCheck == true) { 	
				HDLmBridge.handleRequest(request, 
						                     response,
											           pathValueString,
											           requestPostPayload,
											           httpType);
				break; 
			}	
			/* Get the proxy definition. The proxy definition may not be found. This 
			   is no longer considered to be an error condition. */
			HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before get proxy definition in doAll");
			HDLmProxy  proxyDefinition = HDLmProxy.getProxyDefinitionMatch(hostName,
					                                                           HDLmProxy.getProxyListTop());
			HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After get proxy definition in doAll");
			/* 
			if (proxyDefinition == null) {
				String   errorFormat = "Proxy defintion not found for (Proxy not found) - %s";
				String   errorMessage = String.format(errorFormat, hostName);
				LOG.info(errorMessage);
				HDLmJetty.reportError(response, 
						                  HttpStatus.INTERNAL_SERVER_ERROR_500,
						                  errorMessage);    
				break;
			}
			*/
			/* Proxy the current request. In some cases, the proxy definition 
			   will be null. This is not considered to be an error condition. */
			String   actualServerName;
			if (proxyDefinition != null)
			  actualServerName = proxyDefinition.getBackendName();
			else
				actualServerName = hostName;
			HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before call to handleProxy in doAll");
			HDLmJetty.handleProxy(request, 
					                  response,
					                  clientStr,
					                  timeStamp,
					                  actualServerName,
					                  httpType,
					                  proxyDefinition);
			HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "After call to handleProxy in doAll");
			break;
		}
		/* Check if HTTP/3 is in use or not. If HTTP/3 is
	     in use, send back a header showing where HTTP/3
	     can be found. */ 
	  if (HDLmState.checkString("http3Connected") != null &&
 		    HDLmStateInfo.getHttp3Connected().equals("yes"))
 	    HDLmJetty.addAltSvcheader(response);
		/* We have now handled the current inbound request. This took
		   a certain amount of elapsed time. Calculate how much. */
		Instant   instantFinish = Instant.now();
		Duration  requestDuration = Duration.between(instantStart, instantFinish);
		/* String    elapsedName = HDLmElapsed.elapsedNames.get(0); */
		String    elapsedName = "Requests";
		HDLmElapsed.elapsedOccurred(elapsedName, requestDuration);
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, "Before exit from doAll"); 
	}	
	/* The next method handles all of the inbound (DELETE) requests 
     generated for the proxy server and the editors. Each request
     is checked and handled as need be. */ 
  @Override
  protected void doDelete(HttpServletRequest request, 
  		                    HttpServletResponse response)
                          throws ServletException, IOException {
		/* Handle the current servlet request */
		doAll(request, response, HDLmHttpTypes.DELETE);
  }	
	/* The next method handles all of the inbound (GET) requests 
	   generated for the proxy server and the editors. Each request
	   is checked and handled as need be. */	 
  @Override
  protected void doGet(HttpServletRequest request, 
  		                 HttpServletResponse response)
                       throws ServletException, IOException {
		/* Add some thread status information */
		HDLmThreadStatus.put("Main Servlet Get");
		/* Handle the current servlet request */
		doAll(request, response, HDLmHttpTypes.GET);
  }
	/* The next method handles all of the inbound (HEAD) requests 
     generated for the proxy server and the editors. Each request
     is checked and handled as need be. */	 
	@Override
	protected void doHead(HttpServletRequest request, 
			                  HttpServletResponse response)
	                      throws ServletException, IOException {
		/* Add some thread status information */
		HDLmThreadStatus.put("Main Servlet Head");
		/* Handle the current servlet request */
		doAll(request, response, HDLmHttpTypes.HEAD);
	}
	/* The next method handles all of the inbound (OPTIONS) requests 
     generated for the proxy server and the editors. Each request
     is checked and handled as need be. */
	@Override
	protected void doOptions(HttpServletRequest request, 
			                     HttpServletResponse response)
	                         throws ServletException, IOException {
		/* Add some thread status information */
		HDLmThreadStatus.put("Main Servlet Options");
		/* Handle the current servlet request */
		doAll(request, response, HDLmHttpTypes.OPTIONS);
	}	
	/* The next method handles all of the inbound (POST) requests 
     generated for the proxy server and the editors. Each request
     is checked and handled as need be. */
	@Override
	protected void doPost(HttpServletRequest request, 
			                  HttpServletResponse response)
	                      throws ServletException, IOException {
		/* Add some thread status information */
		HDLmThreadStatus.put("Main Servlet Post");
		/* Handle the current servlet request */
		doAll(request, response, HDLmHttpTypes.POST);
	}	
	/* The next method handles all of the inbound (PUT) requests 
     generated for the proxy server and the editors. Each request
     is checked and handled as need be. */ 
	@Override
	protected void doPut(HttpServletRequest request, 
			                 HttpServletResponse response)
	                     throws ServletException, IOException {
		/* Add some thread status information */
		HDLmThreadStatus.put("Main Servlet Put");
		/* Handle the current servlet request */
		doAll(request, response, HDLmHttpTypes.PUT);
	}	  
}
package com.headlamp;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.server.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmWebSocketInternalServlet short summary.
 *
 * HDLmWebSocketInternalServlet description.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmWebSocketInternalServlet extends JettyWebSocketServlet {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmWebSocketInternalServlet.class); 
  @Override
  public void configure(JettyWebSocketServletFactory factory) {
  	LOG.info("HDLmWebSocketInternalServlet configure");
    factory.register(HDLmWebSocketInternalAdapter.class);
    /* The code below resets the web socket idle timeout to  
       a (hopefully) higher value. Note that the factory is 
       changed to use the new, higher idle timeout value. */
    Duration  someTime = Duration.ofSeconds(90);
    factory.setIdleTimeout(someTime);
  }
  /* The next method handles all of the inbound (DELETE) requests 
     generated for/by web sockets. Each request is checked and 
     handled as need be. */	 
	@Override
	protected void doDelete(HttpServletRequest request, 
			                    HttpServletResponse response)
	                        throws ServletException, IOException {
		/* Handle the current servlet request */
		LOG.info("HDLmWebSocketInternalServlet doDelete");
		/* doAll(request, response, HDLmHttpTypes.DELETE); */
	}
  /* The next method handles all of the inbound (GET) requests 
     generated for/by web sockets. Each request is checked and 
     handled as need be. */	 
	@Override
	protected void doGet(HttpServletRequest request, 
			                 HttpServletResponse response)
	                     throws ServletException, IOException {
		/* Handle the current servlet request */
		LOG.info("HDLmWebSocketInternalServlet doGet");
		/* doAll(request, response, HDLmHttpTypes.GET); */
	}
  /* The next method handles all of the inbound (POST) requests 
     generated for/by web sockets. Each request is checked and 
     handled as need be. */	 
	@Override
	protected void doPost(HttpServletRequest request, 
			                  HttpServletResponse response)
	                      throws ServletException, IOException {
		/* Handle the current servlet request */
		LOG.info("HDLmWebSocketInternalServlet doPost");
		ArrayList<String>    requestHeaders = HDLmJetty.getRequestHeaders(request);
		byte[]               requestPayload = HDLmJetty.getRequestPayloadBinary(request);
		String               requestOriginalPath = HDLmJetty.getOriginalPathValue(request);
	  String               requestStr = new String(requestPayload, StandardCharsets.UTF_8);
	  LOG.info("HDLmWebSocketInternalServlet doPost - " + requestStr);
		/* doAll(request, response, HDLmHttpTypes.POST); */
	}
  /* The next method handles all of the inbound (PUT) requests 
     generated for/by web sockets. Each request is checked and 
     handled as need be. */	 
	@Override
	protected void doPut(HttpServletRequest request, 
			                 HttpServletResponse response)
	                     throws ServletException, IOException {
		/* Handle the current servlet request */
		LOG.info("HDLmWebSocketInternalServlet doPut");
		/* doAll(request, response, HDLmHttpTypes.PUT); */
	}
}
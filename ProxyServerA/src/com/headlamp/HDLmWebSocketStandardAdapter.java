package com.headlamp;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmWebSocketStandardAdapter short summary.
 *
 * HDLmWebSocketStandardAdapter description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmWebSocketStandardAdapter extends WebSocketAdapter {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmWebSocketStandardAdapter.class); 
  /* Session seems to be the only class instance field. It is not quite 
     clear what this field should be used for. */
	private Session  session;
  /* This routine is invoked when a web socket connection is destroyed. 
     This routine is definitely used (invoked). We should handle the
     closing of connections in some way. How is not clear. */   
  @Override
  public void onWebSocketClose(int statusCode, String reason) {
  	this.session = null;
  	String  textFormat = "onWebSocketClose - %d - %s";
  	String  textString = String.format(textFormat, statusCode, reason); 
    LOG.info(textString);
    super.onWebSocketClose(statusCode, reason); 
  } 
  /* This routine is invoked when a web socket connection is established. 
     This routine is definitely used (invoked). We should handle new
     connections by saving some data. What data is not not clear. */  
  @Override
  public void onWebSocketConnect(Session session) {
  	LOG.info("onWebSocketConnect");
    super.onWebSocketConnect(session); 
    this.session = session;
  }
  /* This routine is invoked when a text message is sent. So far
     this routine has not been used. */ 
  @Override
  public void onWebSocketText(String message) {
  	LOG.info("onWebSocketText" + " - " + message);
    super.onWebSocketText(message);           
  }    
}
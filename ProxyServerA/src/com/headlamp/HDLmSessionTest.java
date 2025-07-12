package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
/**
 * HDLmSessionTest short summary.
 *
 * HDLmSessionTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmSessionTest {
	@Test
	void buildJson() {
		/* Run a few buildJson tests */
		HDLmSession  sessionObj = new HDLmSession();
		sessionObj.setSessionId("123456");
		sessionObj.setIndex("index");
		sessionObj.setParameters("parameters");
		String   expectedJson = "{\"index\":\"index\",\"parameters\":\"parameters\",\"sessionId\":\"123456\"}";
		String   sessionJson = sessionObj.buildJson();		
		assertEquals(expectedJson, sessionJson, "Unexpected JSON string returned by conversion");
	}
	@Test
	void buildSession() {
		/* Run a few buildSession tests */
		HDLmSession  sessionObj = new HDLmSession();
		sessionObj.setSessionId("123456");
		sessionObj.setIndex("index");
		sessionObj.setParameters("parameters");
		String   expectedJson = "{\"index\":\"index\",\"parameters\":\"parameters\",\"sessionId\":\"123456\"}";
		String   sessionJson = sessionObj.buildJson();		
		assertEquals(expectedJson, sessionJson, "Unexpected JSON string returned by conversion");
		/* Convert the JSON back to an object */
		HDLmSession  sessionNew = HDLmSession.buildSession(sessionJson);
		String  indexString = sessionNew.getIndex();
		String  idString = sessionNew.getSessionId();
		String  parametersString = sessionNew.getParameters();
		assertEquals(idString, "123456", "Unexpected ID string obtained from the JSON object");
		assertEquals(indexString, "index", "Unexpected index string obtained from the JSON object");
		assertEquals(parametersString, "parameters", "Unexpected parameters string obtained from the JSON object");
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmSession.buildSession(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Session JSON string reference passed to buildSession is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getIndex() {
		/* Run a few getIndex tests */
		HDLmSession  sessionObj = new HDLmSession();
		sessionObj.setSessionId("123456");
		sessionObj.setIndex("index");
		sessionObj.setParameters("parameters");
		String   expectedJson = "{\"index\":\"index\",\"parameters\":\"parameters\",\"sessionId\":\"123456\"}";
		String   sessionJson = sessionObj.buildJson();		
		assertEquals(expectedJson, sessionJson, "Unexpected JSON string returned by conversion");
		/* Convert the JSON back to an object */
		HDLmSession  sessionNew = HDLmSession.buildSession(sessionJson);
		String  indexString = sessionNew.getIndex();
		String  idString = sessionNew.getSessionId();
		String  parametersString = sessionNew.getParameters();
		assertEquals(idString, "123456", "Unexpected ID string obtained from the JSON object");
		assertEquals(indexString, "index", "Unexpected index string obtained from the JSON object");
		assertEquals(parametersString, "parameters", "Unexpected parameters string obtained from the JSON object");
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {sessionObj.setParameters(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New parameters string is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getParameters() {
		/* Run a few getParameters tests */
		HDLmSession  sessionObj = new HDLmSession();
		sessionObj.setSessionId("123456");
		sessionObj.setIndex("index");
		sessionObj.setParameters("parameters");
		String   expectedJson = "{\"index\":\"index\",\"parameters\":\"parameters\",\"sessionId\":\"123456\"}";
		String   sessionJson = sessionObj.buildJson();		
		assertEquals(expectedJson, sessionJson, "Unexpected JSON string returned by conversion");
		/* Convert the JSON back to an object */
		HDLmSession  sessionNew = HDLmSession.buildSession(sessionJson);
		String  indexString = sessionNew.getIndex();
		String  idString = sessionNew.getSessionId();
		String  parametersString = sessionNew.getParameters();
		assertEquals(idString, "123456", "Unexpected ID string obtained from the JSON object");
		assertEquals(indexString, "index", "Unexpected index string obtained from the JSON object");
		assertEquals(parametersString, "parameters", "Unexpected parameters string obtained from the JSON object");
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {sessionObj.setParameters(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New parameters string is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getSessionId() {
		/* Run a few getSessionId tests */
		HDLmSession  sessionObj = new HDLmSession();
		sessionObj.setSessionId("123456");
		sessionObj.setIndex("index");
		sessionObj.setParameters("parameters");
		String   expectedJson = "{\"index\":\"index\",\"parameters\":\"parameters\",\"sessionId\":\"123456\"}";
		String   sessionJson = sessionObj.buildJson();		
		assertEquals(expectedJson, sessionJson, "Unexpected JSON string returned by conversion");
		/* Convert the JSON back to an object */
		HDLmSession  sessionNew = HDLmSession.buildSession(sessionJson);
		String  indexString = sessionNew.getIndex();
		String  idString = sessionNew.getSessionId();
		String  parametersString = sessionNew.getParameters();
		assertEquals(idString, "123456", "Unexpected ID string obtained from the JSON object");
		assertEquals(indexString, "index", "Unexpected index string obtained from the JSON object");
		assertEquals(parametersString, "parameters", "Unexpected parameters string obtained from the JSON object");
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {sessionObj.setSessionId(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New session ID string is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void setIndex() {
		/* Run a few setIndex tests */
		HDLmSession  sessionObj = new HDLmSession();
		sessionObj.setSessionId("123456");
		sessionObj.setIndex("index");
		sessionObj.setParameters("parameters");
		String   expectedJson = "{\"index\":\"index\",\"parameters\":\"parameters\",\"sessionId\":\"123456\"}";
		String   sessionJson = sessionObj.buildJson();		
		assertEquals(expectedJson, sessionJson, "Unexpected JSON string returned by conversion");
		/* Convert the JSON back to an object */
		HDLmSession  sessionNew = HDLmSession.buildSession(sessionJson);
		String  indexString = sessionNew.getIndex();
		String  idString = sessionNew.getSessionId();
		String  parametersString = sessionNew.getParameters();
		assertEquals(idString, "123456", "Unexpected ID string obtained from the JSON object");
		assertEquals(indexString, "index", "Unexpected index string obtained from the JSON object");
		assertEquals(parametersString, "parameters", "Unexpected parameters string obtained from the JSON object");
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {sessionObj.setParameters(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New parameters string is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void setParameters() {
		/* Run a few setParameters tests */
		HDLmSession  sessionObj = new HDLmSession();
		sessionObj.setSessionId("123456");
		sessionObj.setIndex("index");
		sessionObj.setParameters("parameters");
		String   expectedJson = "{\"index\":\"index\",\"parameters\":\"parameters\",\"sessionId\":\"123456\"}";
		String   sessionJson = sessionObj.buildJson();		
		assertEquals(expectedJson, sessionJson, "Unexpected JSON string returned by conversion");
		/* Convert the JSON back to an object */
		HDLmSession  sessionNew = HDLmSession.buildSession(sessionJson);
		String  indexString = sessionNew.getIndex();
		String  idString = sessionNew.getSessionId();
		String  parametersString = sessionNew.getParameters();
		assertEquals(idString, "123456", "Unexpected ID string obtained from the JSON object");
		assertEquals(indexString, "index", "Unexpected index string obtained from the JSON object");
		assertEquals(parametersString, "parameters", "Unexpected parameters string obtained from the JSON object");
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {sessionObj.setParameters(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New parameters string is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void setSessionId() {
		/* Run a few setSessionId tests */
		HDLmSession  sessionObj = new HDLmSession();
		sessionObj.setSessionId("123456");
		sessionObj.setIndex("index");
		sessionObj.setParameters("parameters");
		String   expectedJson = "{\"index\":\"index\",\"parameters\":\"parameters\",\"sessionId\":\"123456\"}";
		String   sessionJson = sessionObj.buildJson();		
		assertEquals(expectedJson, sessionJson, "Unexpected JSON string returned by conversion");
		/* Convert the JSON back to an object */
		HDLmSession  sessionNew = HDLmSession.buildSession(sessionJson);
		String  indexString = sessionNew.getIndex();
		String  idString = sessionNew.getSessionId();
		String  parametersString = sessionNew.getParameters();
		assertEquals(idString, "123456", "Unexpected ID string obtained from the JSON object");
		assertEquals(indexString, "index", "Unexpected index string obtained from the JSON object");
		assertEquals(parametersString, "parameters", "Unexpected parameters string obtained from the JSON object");
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {sessionObj.setSessionId(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New session ID string is null",  execMsg,"Unexpected exception message");
		}
	}
}
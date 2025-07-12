package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * HDLmEditorServletTest short summary.
 *
 * HDLmEditorServletTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmEditorServletTest {
	@Test
	void getTypeEditor() {
		/* Run a few getTypeEditor tests */
		HDLmEditorTypes  editorType = HDLmEditorTypes.AUTH;
		String   outStr = "Auth";
		assertEquals(outStr, 
			           HDLmEditorServlet.getTypeEditor(editorType),
				         "HDLmEditorServlet.getTypeEditor did not return correct value");
		/* Run a getTypeEditor test */
		editorType = HDLmEditorTypes.PROXY;
		outStr = "Proxy";
		assertEquals(outStr, 
			           HDLmEditorServlet.getTypeEditor(editorType),
				         "HDLmEditorServlet.getTypeEditor did not return correct value");
		/* Run a getTypeEditor test */
		editorType = HDLmEditorTypes.NONE;
		outStr = "Invalid Editor Type";
		assertEquals(outStr, 
			           HDLmEditorServlet.getTypeEditor(editorType),
				         "HDLmEditorServlet.getTypeEditor did not return correct value");
		/* Run a getTypeEditor test */
		editorType = null;
		outStr = "Null Editor Type";
		assertEquals(outStr, 
			           HDLmEditorServlet.getTypeEditor(editorType),
				         "HDLmEditorServlet.getTypeEditor did not return correct value");
	}
	@Test
	void simulateProxy() {
		/* Run a few simulateProxy tests */
		HttpServletRequest    mockRequest = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse   mockResponse = Mockito.mock(HttpServletResponse.class);
		String                extraInfo = "none";
		String                jsonOut;
		String                password = "none";
		String                requestType = "invalid";
		String                type = "none";
		String                url = "none";
		String                userid = "none"; 	
		jsonOut = HDLmEditorServlet.simulateProxy(mockRequest, mockResponse,
				                                      requestType, url, userid, password,
				                                      type, extraInfo, HDLmReportErrors.DONTREPORTERRORS);
		assertNull(jsonOut, "SimulateProxy should have returned a null value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmEditorServlet.simulateProxy(null, null, 
					                              		                                    null, null, null,
					                              		                                    null, null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Servlet request passed to simulateProxy is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmEditorServlet.simulateProxy(mockRequest, null, 
					                              		                                    null, null, null,
					                              		                                    null, null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Servlet response passed to simulateProxy is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmEditorServlet.simulateProxy(mockRequest, 
					                              		                                    mockResponse, 
					                              		                                    null, null, null,
					                              		                                    null, null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Request type string passed to simulateProxy is null",  execMsg,"Unexpected exception message");
		}
	}
}
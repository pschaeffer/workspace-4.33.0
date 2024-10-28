package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
/**
 * HDLmApacheTest short summary.
 *
 * HDLmApacheTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmApacheTest {
	@Test
	void dumpAllHeaders() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.dumpAllHeaders(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Apache request passed to dumpAllHeaders is null");
		}
	}
	@Test
	void findHeader() {
		ArrayList<String>  hostHeaders = new ArrayList<String>();
		int      hostIndex;
		String   headerStr;
  	/* Run a findHeader test */
		hostHeaders.clear();
		headerStr = "Host: 10.17";
		hostHeaders.add(headerStr);
		headerStr = "oneworld";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHeader(hostHeaders, "Host"); 
		assertEquals(0, hostIndex, "HDLmApache.findHeader returned an incorrect value");
  	/* Run a findHeader test */
		hostHeaders.clear();
		headerStr = "oneworld";
		hostHeaders.add(headerStr);
		headerStr = "Host: 10.17";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHeader(hostHeaders, "Host"); 
		assertEquals(1, hostIndex, "HDLmApache.findHeader returned an incorrect value");
  	/* Run a findHeader test */
		hostHeaders.clear();
		headerStr = "Host: 10.17";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHeader(hostHeaders, "Host"); 
		assertEquals(0, hostIndex, "HDLmApache.findHeader returned an incorrect value");
  	/* Run a findHeader test */
		hostHeaders.clear();
		headerStr = "Host: 10.17";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHeader(hostHeaders, "Most"); 
		assertEquals(-1, hostIndex, "HDLmApache.findHeader returned an incorrect value");
  	/* Run a findHeader test */
		hostHeaders.clear();
		headerStr = "Most: 10.17";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHeader(hostHeaders, "Most"); 
		assertEquals(0, hostIndex, "HDLmApache.findHeader returned an incorrect value");
  	/* Run a findHeader test */
		hostHeaders.clear();
		headerStr = "oneworld";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHeader(hostHeaders, "Host"); 
		assertEquals(-1, hostIndex, "HDLmApache.findHeader returned an incorrect value");
  	/* Run a findHeader test*/
		hostHeaders.clear();
		headerStr = "oneworld";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHeader(hostHeaders, "Host"); 
		/* The headers array is cleared here for the tests below. Note that it is
		   not set to a null value. */
		hostHeaders.clear();
		assertEquals(-1, hostIndex, "HDLmApache.findHeader returned an incorrect value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.findHeader(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTTP headers array passed to findHeader is null");
		}
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.findHeader(hostHeaders, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTTP target header passed to findHeader is null");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmApache.findHeader(hostHeaders, "");},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTTP headers array passed to findHeader is empty");
		}
	}
	@Test
	void findHostHeader() {
		ArrayList<String>  hostHeaders = new ArrayList<String>();
		int      hostIndex;
		String   headerStr;
  	/* Run a findHostHeader test */
		hostHeaders.clear();
		headerStr = "Host: 10.17";
		hostHeaders.add(headerStr);
		headerStr = "oneworld";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHostHeader(hostHeaders); 
		assertEquals(0, hostIndex, "HDLmApache.findHostHeader returned an incorrect value");
  	/* Run a findHostHeader test */
		hostHeaders.clear();
		headerStr = "oneworld";
		hostHeaders.add(headerStr);
		headerStr = "Host: 10.17";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHostHeader(hostHeaders); 
		assertEquals(1, hostIndex, "HDLmApache.findHostHeader returned an incorrect value");
  	/* Run a findHostHeader test */
		hostHeaders.clear();
		headerStr = "Host: 10.17";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHostHeader(hostHeaders); 
		assertEquals(0, hostIndex, "HDLmApache.findHostHeader returned an incorrect value");
  	/* Run a findHostHeader test */
		hostHeaders.clear();
		headerStr = "oneworld";
		hostHeaders.add(headerStr);
		hostIndex = HDLmApache.findHostHeader(hostHeaders); 
		assertEquals(-1, hostIndex, "HDLmApache.findHostHeader returned an incorrect value");
  	/* Run a findHostHeader test */
		hostHeaders.clear();
		hostIndex = HDLmApache.findHostHeader(hostHeaders); 
		assertEquals(-1, hostIndex, "HDLmApache.findHostHeader returned an incorrect value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.findHostHeader(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTTP headers array passed to findHostHeader is null");
		}
	}
	@Test
	void fixHostName() {
		String   headerStr;
		String   newHeader;
		String   newHostName;
  	/* Run a fixHostName test */
		headerStr = "Host: 10.17";
		newHostName = "oneworld";
		newHeader = HDLmApache.fixHostName(headerStr,  newHostName);
		assertEquals("Host: oneworld", newHeader, "HDLmApache.fixHostName returned an incorrect value");
  	/* Run a fixHostName test */
		headerStr = "Host: 10.17,";
		newHostName = "oneworld";
		newHeader = HDLmApache.fixHostName(headerStr,  newHostName);
		assertEquals("Host: oneworld,", newHeader, "HDLmApache.fixHostName returned an incorrect value");
  	/* Run a fixHostName test */
		headerStr = "Host: 10.17, ";
		newHostName = "oneworld";
		newHeader = HDLmApache.fixHostName(headerStr,  newHostName);
		assertEquals("Host: oneworld, ", newHeader, "HDLmApache.fixHostName returned an incorrect value");
  	/* Run a fixHostName test */
		headerStr = "Host: 10.17, xyz";
		newHostName = "oneworld";
		newHeader = HDLmApache.fixHostName(headerStr,  newHostName);
		assertEquals("Host: oneworld, xyz", newHeader, "HDLmApache.fixHostName returned an incorrect value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.fixHostName(null, "newHost");},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTTP Host header passed to fixHostName is null");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmApache.fixHostName("header", null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "New host name passed to fixHostName is null");
		}
	}
	@Test
	void getAllHeaders() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.getAllHeaders(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Apache request passed to getAllHeaders is null");
		}

	}
	@Test
	void processHttpOperation() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.processHttpOperation(null, null,
					                              		                                    null, null,
					                              		                                    null, null, 
					                              		                                    null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Protocol type enum value passed by the caller to processHttpOperation is null");
		}
		{
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmApache.processHttpOperation(HDLmProtocolTypes.NONE, null,
					                              		                                    null, null,
					                              		                                    null, null, 
					                              		                                    null);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Protocol type value (NONE) passed to processHttpOperation is invalid");
		}
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.processHttpOperation(HDLmProtocolTypes.HTTP, null,
					                              		                                    null, null,
					                              		                                    null, null, 
					                              		                                    null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Operation type enum value passed by the caller to processHttpOperation is null");
		}
		{
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmApache.processHttpOperation(HDLmProtocolTypes.HTTP,
					                              		                                    HDLmHttpTypes.NONE,
					                              		                                    null, null,
					                              		                                    null, null, 
					                              		                                    null);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Operation type value (NONE) passed to processHttpOperation is invalid");
		}		 
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.processHttpOperation(HDLmProtocolTypes.HTTP,
     		                                                                        HDLmHttpTypes.GET,
					                              		                                    null, null,
					                              		                                    null, null,
					                              		                                    null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Host name string passed to processHttpOperation is null");
		}
	}
	@Test
	void removeContentLength() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.removeContentLength(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Apache HTTP message passed to removeContentLength is null");
		}
	}
	@Test
	void removeContentLengthZero() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.removeContentLengthZero(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Apache HTTP message passed to removeContentLengthZero is null");
		}
	}
	@Test
	void setHeaders() {
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmApache.setHeaders(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Apache request passed to setHeaders is null");
		}
	}
}
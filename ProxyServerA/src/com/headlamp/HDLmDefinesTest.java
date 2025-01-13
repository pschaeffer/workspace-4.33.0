package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * HDLmDefinesTest short summary.
 *
 * HDLmDefinesTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmDefinesTest {
  @BeforeAll
	static void HDLmDefinesBeforeAll() {
	  /* Add all of the general events to the events map */
	  HDLmEvent.addEventsList(HDLmEvent.eventNames);
	}
	@Test
	void test() {
		/* Run a few getNumber tests */
		assertEquals(2000, HDLmDefines.getNumber("HDLMHEIGHTMAX"), "Value for HDLMHEIGHTMAX must be 2000");
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmDefines.getNumber("doesNotExist");},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Invalid Define Name (doesNotExist) passed to getNumber");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
	                                       () -> {HDLmDefines.getNumber("HDLMCONFIGS");},
	                                      "Expected IllegalArgumentException");
	    String execMsg = exception.getMessage();
	    assertEquals(execMsg, "Value of Define Name (HDLMCONFIGS) is not numeric");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class, 
	                                       () -> {HDLmDefines.getNumber(null);},
	                                      "Expected RuntimeException");
	    String execMsg = exception.getMessage();
	    assertEquals(execMsg, "Definition name reference passed to getNumber is null");
		}
		/* Run a few getString tests */
		assertEquals(HDLmDefines.getString("HDLMTREE"), "HDLmTree");
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmDefines.getString("doesNotExist");},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Invalid Define Name (doesNotExist) passed to getString");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
	                                       () -> {HDLmDefines.getString("HDLMHEIGHTMAX");},
	                                      "Expected IllegalArgumentException");
	    String execMsg = exception.getMessage();
	    assertEquals(execMsg, "Value of Define Name (HDLMHEIGHTMAX) is numeric");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class, 
	                                       () -> {HDLmDefines.getString(null);},
	                                      "Expected RuntimeException");
	    String execMsg = exception.getMessage();
	    assertEquals(execMsg, "Definition name reference passed to getString is null");
		}
	}
}
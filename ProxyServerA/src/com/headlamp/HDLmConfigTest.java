package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
/**
 * HDLmConfigTest short summary.
 *
 * HDLmConfigTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmConfigTest {
	@Test
	void test() {
		/* Run a few getBoolean tests */
		assertFalse(HDLmConfig.getBoolean("logRuleMatching"), "Value for log rule matching must be False");
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmConfig.getBoolean("doesNotExist");},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Invalid configuration name (doesNotExist) passed to getBoolean");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
	                                       () -> {HDLmConfig.getBoolean("logFileName");},
	                                      "Expected IllegalArgumentException");
	    String execMsg = exception.getMessage();
	    assertEquals(execMsg, "Configuration name (logFileName) passed to getBoolean is not for a boolean");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class, 
	                                       () -> {HDLmConfig.getBoolean(null);},
	                                      "Expected RuntimeException");
	    String execMsg = exception.getMessage();
	    assertEquals(execMsg, "Configuration name reference passed to getBoolean is null");
		}
		/* Run a few getString tests */
		assertEquals(HDLmConfig.getString("logFileName"), "info.log");
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmConfig.getString("doesNotExist");},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Invalid configuration name (doesNotExist) passed to getString");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
	                                       () -> {HDLmConfig.getString("logRuleMatching");},
	                                      "Expected IllegalArgumentException");
	    String execMsg = exception.getMessage();
	    assertEquals(execMsg, "Configuration name (logRuleMatching) passed to getString is not for a string");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class, 
	                                       () -> {HDLmConfig.getString(null);},
	                                      "Expected RuntimeException");
	    String execMsg = exception.getMessage();
	    assertEquals(execMsg, "Configuration name reference passed to getString is null");
		}		
	}
}
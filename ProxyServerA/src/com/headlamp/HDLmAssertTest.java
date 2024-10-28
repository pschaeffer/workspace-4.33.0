package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
/**
 * HDLmAssertTest short summary.
 *
 * HDLmAssertTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmAssertTest {
	@Test
	void HDLmAssert() {
		/* Run a few HDLmAssert tests */
		/* The function call below should never fail */
		String   errorText;
		errorText = "This is an error test";
		HDLmAssert.HDLmAssertAction(true, errorText);
		{
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmAssert.HDLmAssertAction(false, errorText);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "This is an error test",
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(AssertionError.class, 
					                               () -> {HDLmAssert.HDLmAssertAction(false, null);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Null Error Text",
					         "Unexpected exception message");
		}
	}
}
package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * HDLmJsonTest short summary.
 *
 * HDLmJsonTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmJsonTest {	
	@Test
	void getJsonValue() {	
		boolean  fileBinary;
		String   jsonValue;
		String   value;
		/* Run a getJsonValue test */
	  value = null;
		jsonValue = HDLmJson.getJsonValue(value);
		assertEquals("null", jsonValue, "Incorrect JSON value returned");
		/* Run a getJsonValue test */
	  value = "false";
		jsonValue = HDLmJson.getJsonValue(value);
		assertEquals("false", jsonValue, "Incorrect JSON value returned");
		/* Run a getJsonValue test */
	  value = "true";
		jsonValue = HDLmJson.getJsonValue(value);
		assertEquals("true", jsonValue, "Incorrect JSON value returned");
		/* Run a getJsonValue test */
	  value = "131";
		jsonValue = HDLmJson.getJsonValue(value);
		assertEquals("131", jsonValue, "Incorrect JSON value returned");
		/* Run a getJsonValue test */
	  value = "abc";
		jsonValue = HDLmJson.getJsonValue(value);
		assertEquals("\"abc\"", jsonValue, "Incorrect JSON value returned");
	}	
}
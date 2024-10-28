package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
/**
 * HDLmSavedChangeTest short summary.
 *
 * HDLmSavedChangeTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmSavedChangeTest {
	@Test
	void HDLmSavedChangeConstructor() {
		/* Run a few HDLmSavedChange constructor tests */
		Integer        parameterNumber;
		Double         parameterValue;
		String         modName;
		String         modPathValue;
		HDLmModTypes   modType = HDLmModTypes.NONE;
		String         oldValue;
		String         newValue;
		/* Set a few values */
		parameterNumber = 7;
		parameterValue = 0.5;
		modName = "Test Mod Name";
		modPathValue = "/Test";
		modType = HDLmModTypes.TEXT;
		oldValue = "Old Text";
		newValue = "New Text";
		/* Create the new instance */
		HDLmSavedChange  newSavedChange = new HDLmSavedChange(parameterNumber, parameterValue, 
				                                                  modName, modPathValue, 
				                                                  modType, 
				                                                  oldValue, newValue);
		assertNotNull(newSavedChange, "New saved change reference is null"); 
		assertEquals(parameterNumber, newSavedChange.getParameterNumber(), 
				         "Parameter number value is not correct");
		assertEquals(parameterValue, newSavedChange.getParameterValue(), 
                 "Parameter value value is not correct");
		assertEquals(modName, newSavedChange.getModName(), 
                 "Modification name value is not correct");
		assertEquals(modPathValue, newSavedChange.getModPathValue(), 
                 "Modification path value is not correct");
		assertEquals(modType, newSavedChange.getModType(), 
                 "Modification type value is not correct");
		assertEquals(oldValue, newSavedChange.getOldValue(), 
                 "Modification old change value is not correct");
		assertEquals(newValue, newSavedChange.getNewValue(), 
                 "Modification new change value is not correct");
	}
}
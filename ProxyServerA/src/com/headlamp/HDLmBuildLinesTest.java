package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
/**
 * HDLmBuildLinesTest short summary.
 *
 * HDLmBuildLinesTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmBuildLinesTest {
	@Test
	void HDLmBuildLinesConstructor() {
		/* Run a few HDLmBuildLines constructor tests */
		HDLmBuildLines   newBuildLines = new HDLmBuildLines("JS");
		String           allLines;
		allLines = newBuildLines.getLinesWithSuffix("");
		assertNotNull(newBuildLines, "New build lines reference is null"); 
		assertEquals("", allLines, "All lines value is not correct");
		/* Pass a null find reference to the find constructor */ 
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {new HDLmBuildLines(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Build type reference passed to build lines constructor is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void addLine() {
		/* Run a few addLine tests */
		HDLmBuildLines   newBuildLines = new HDLmBuildLines("JS");
		String           allLines;
		String           newLine1;
		String           newLine2;
		allLines = newBuildLines.getLinesWithSuffix("");
		assertNotNull(newBuildLines, "New build lines reference is null"); 
		assertEquals("", allLines, "All lines value is not correct");
		/* Add a line to the program */
		newLine1 = "New line of text";
		newBuildLines.addLine(newLine1);
		allLines = newBuildLines.getLinesWithSuffix("");
		assertEquals(newLine1, allLines, "All lines value is not correct");
		/* Add a line to the program */
		newLine2 = "second line of text";
		newBuildLines.addLine(newLine2);
		allLines = newBuildLines.getLinesWithSuffix("");
		assertEquals(newLine1 + newLine2, allLines, "All lines value is not correct");
		/* Pass a null find reference to the find constructor */ 
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {newBuildLines.addLine(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New line reference passed to addLine is null", execMsg,
					         "Unexpected exception message");
		}	
	}
	@Test
	void getLinesWithSuffix() {
		/* Run a few getLinesWithSuffix tests */
		HDLmBuildLines   newBuildLines = new HDLmBuildLines("JS");
		String           allLines;
		String           newLine1;
		String           newLine2;
		allLines = newBuildLines.getLinesWithSuffix("");
		assertNotNull(newBuildLines, "New build lines reference is null"); 
		assertEquals("", allLines, "All lines value is not correct");
		/* Add a line to the program */
		newLine1 = "New line of text";
		newBuildLines.addLine(newLine1);
		allLines = newBuildLines.getLinesWithSuffix("");
		assertEquals(newLine1, allLines, "All lines value is not correct");
		/* Add a line to the program */
		newLine2 = "second line of text";
		newBuildLines.addLine(newLine2);
		allLines = newBuildLines.getLinesWithSuffix("");
		assertEquals(newLine1 + newLine2, allLines, "All lines value is not correct");
		/* Pass a null find reference to the find constructor */ 
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {newBuildLines.getLinesWithSuffix(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Suffix reference passed to getLinesWithSuffix is null", execMsg,
					         "Unexpected exception message");
		}	
	}
}
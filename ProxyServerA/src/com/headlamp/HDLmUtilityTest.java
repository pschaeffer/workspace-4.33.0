package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * HDLmUtilityTest short summary.
 *
 * HDLmUtilityTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmUtilityTest {
  @BeforeAll
	static void HDLmUtilityBeforeAll() {
	  /* Add all of the general events to the events map */
	  HDLmEvent.addEventsList(HDLmEvent.eventNames);
	}
  @Test
	void buildBridgeRestQuery() {
		String   colName;
		String   companyPrefix;
		String   contentSuffix;
		String   contentSuffixSystem;
		String   queryString;
		String   value;		 
		/* Run a buildBridgeRestQuerytest */
		colName = "content";
		value = "mod";
		companyPrefix = HDLmConfigInfo.getEntriesBridgeCompanyPrefix();
		contentSuffix = HDLmConfigInfo.getEntriesBridgeContentSuffix();
		contentSuffixSystem = HDLmStateInfo.getSystemValue();  
		contentSuffix += contentSuffixSystem;
		queryString = HDLmUtility.buildBridgeRestQuery(colName, HDLmEditorTypes.PASS);
		assertEquals("q=[[['content','eq','pass_javaa','pass_javaa']]]", 
				         queryString, 
				         "Incorrect query string returned from buildBridgeRestQuerycall");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.buildBridgeRestQuery(null, HDLmEditorTypes.PASS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Column name reference passed to buildBridgeRestQuery is null",
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmUtility.buildBridgeRestQuery(colName, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Editor type value passed to buildBridgeRestQuery is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void buildDreamRestQuery() {
		String   colName;
		String   companyPrefix;
		String   contentSuffix;
		String   contentSuffixSystem;
		String   queryString;
		String   value;		 
		/* Run a buildDreamRestQuery test */
		colName = "content";
		value = "mod";
		companyPrefix = HDLmConfigInfo.getEntriesDreamtsoftCompanyPrefix();
		contentSuffix = HDLmConfigInfo.getEntriesDreamtsoftContentSuffix();
		contentSuffixSystem = HDLmStateInfo.getSystemValue();  
		contentSuffix += contentSuffixSystem;
		queryString = HDLmUtility.buildDreamRestQuery(colName, HDLmEditorTypes.PASS);
		assertEquals("q=[[['content','eq','pass_javaa','pass_javaa']]]", 
				         queryString, 
				         "Incorrect query string returned from buildDreamRestQuerycall");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.buildDreamRestQuery(null, HDLmEditorTypes.PASS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Column name reference passed to buildDreamRestQuery is null",
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmUtility.buildDreamRestQuery(colName, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Editor type value passed to buildDreamRestQuery is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void combineLinesSuffix() {		 
		ArrayList<String>  fileLines;
		String             fileName;
		String             outString;
		/* Run a combineLinesSuffixtest */
		fileName = "HDLmUtilityTestFile.txt";
		HDLmUtility.fileClearContents(fileName); 
		HDLmUtility.filePutAppendLine(fileName, "dcba ");
		HDLmUtility.filePutAppendLine(fileName, "dcba  ");
		HDLmUtility.filePutAppendLine(fileName, "dcba   ");
		fileLines = HDLmUtility.fileGetLines(fileName); 
		fileLines = HDLmUtility.trimLinesRight(fileLines);
		assertEquals("dcba", fileLines.get(0), "Incorrect file contents returned");
		assertEquals("dcba", fileLines.get(1), "Incorrect file contents returned");
		assertEquals("dcba", fileLines.get(2), "Incorrect file contents returned");
		/* Combine the lines */
		outString = HDLmUtility.combineLinesSuffix(fileLines, "\r\n");
		assertEquals(18, outString.length(), "Incorrect file contents length returned");
		assertEquals("dcba\r\ndcba\r\ndcba\r\n", outString, "Incorrect file contents returned");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.combineLinesSuffix(null, "");},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Input lines array passed to combineLinesSuffix is null",
					         "Unexpected exception message");
		}
		{ 
			ArrayList<String>  arrayLinesLocal = fileLines;
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.combineLinesSuffix(arrayLinesLocal, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Suffix string passed to combineLinesSuffix is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void convertDouble() {
		Double   doubleValue;
		String   doubleString;
		/* Run a convertDoubletest */
		doubleString = "0.5";
		doubleValue = HDLmUtility.convertDouble(doubleString);
		assertEquals(0.5, doubleValue, "Incorrect double value returned from convertDoublecall");
		/* Run a convertDoubletest */
		doubleString = "abc";
		doubleValue = HDLmUtility.convertDouble(doubleString);
		assertNull(doubleValue, "Incorrect double value returned from convertDoublecall");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.convertDouble(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "String passed to double conversion is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void convertInteger() {
		Integer  integerValue;
		String   integerString;
		/* Run a convertIntegertest */
		integerString = "512";
		integerValue = HDLmUtility.convertInteger(integerString);
		assertEquals(512, integerValue, "Incorrect double value returned from convertIntegercall");
		/* Run a convertIntegertest */
		integerString = "abc";
	  integerValue = HDLmUtility.convertInteger(integerString);
		assertNull(integerValue, "Incorrect integer value returned from convertInteger call");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.convertInteger(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "String passed to integer conversion is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void fileClearContents() {		 
		String   fileContents;
		String   fileName;
		/* Run a fileClearContents test */
		fileName = "HDLmUtilityTestFile.txt";
		HDLmUtility.filePutAppend(fileName, "abcd");
		HDLmUtility.fileClearContents(fileName); 
		fileContents = HDLmUtility.fileGetContents(fileName); 
		assertEquals("", fileContents, "Incorrect file contents returned");
		/* Run a fileClearContentstest */
		HDLmUtility.filePutAppend(fileName, "abcd");
		fileContents = HDLmUtility.fileGetContents(fileName); 
		assertEquals("abcd", fileContents, "Incorrect file contents returned");
		HDLmUtility.fileClearContents(fileName); 
		fileContents = HDLmUtility.fileGetContents(fileName); 
		assertEquals("", fileContents, "Incorrect file contents returned");		
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.fileClearContents(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File name string passed to fileClearContents is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void fileGetBytes() {		 
		byte[]   fileBytes;
		String   fileString;
		String   fileName;
		/* Run a fileGetBytes test */
		fileName = "HDLmUtilityTestFile.txt";
		HDLmUtility.fileClearContents(fileName); 
		HDLmUtility.filePutAppend(fileName, "dcba");
		fileBytes = HDLmUtility.fileGetBytes(fileName); 
		fileString = new String(fileBytes);
		assertEquals("dcba", fileString, "Incorrect file contents returned");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.fileGetBytes(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File name string passed to fileGetBytes is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void fileGetContents() {		 
		String   fileContents;
		String   fileName;
		/* Run a fileGetContents test */
		fileName = "HDLmUtilityTestFile.txt";
		HDLmUtility.fileClearContents(fileName); 
		HDLmUtility.filePutAppend(fileName, "dcba");
		fileContents = HDLmUtility.fileGetContents(fileName); 
		assertEquals("dcba", fileContents, "Incorrect file contents returned");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.fileGetContents(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File name string passed to fileGetContents is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void fileGetLines() {		 
		ArrayList<String>  fileLines;
		String             fileName;
		/* Run a fileGetLines test */
		fileName = "HDLmUtilityTestFile.txt";
		HDLmUtility.fileClearContents(fileName); 
		HDLmUtility.filePutAppendLine(fileName, "dcba");
		HDLmUtility.filePutAppendLine(fileName, "dcba");
		HDLmUtility.filePutAppendLine(fileName, "dcba");
		fileLines = HDLmUtility.fileGetLines(fileName); 
		assertEquals(3, fileLines.size(), "Incorrect file contents returned");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.fileGetLines(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File name string passed to fileGetLines is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void fileGetSuffix() {	
		String   fileSuffix;
		String   fileName;
		/* Run a fileGetSuffix test */
		fileName = "HDLmUtilityTestFile.txt";
		fileSuffix = HDLmUtility.fileGetSuffix(fileName); 
		assertEquals("txt", fileSuffix, "Incorrect file suffix returned");
		/* Run a fileGetSuffix test */
		fileName = "HDLmUtilityTestFile.css";
		fileSuffix = HDLmUtility.fileGetSuffix(fileName); 
		assertEquals("css", fileSuffix, "Incorrect file suffix returned");
		/* Run a fileGetSuffix test */
		fileName = "HDLmUtilityTestFile.";
		fileSuffix = HDLmUtility.fileGetSuffix(fileName); 
		assertEquals("", fileSuffix, "Incorrect file suffix returned");
		/* Run a fileGetSuffix test */
		fileName = "HDLmUtilityTestFile";
		fileSuffix = HDLmUtility.fileGetSuffix(fileName); 
		assertNull(fileSuffix, "Non-null file suffix returned");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.fileGetSuffix(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File name string passed to fileGetSuffix is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void filePutAppend() {		 
		String   fileContents;
		String   fileNewContents;
		String   fileName;
		/* Run a filePutAppend test */
		fileName = "HDLmUtilityTestFile.txt";
		fileContents = "efgh";
		HDLmUtility.fileClearContents(fileName); 
		HDLmUtility.filePutAppend(fileName, fileContents);
		fileNewContents = HDLmUtility.fileGetContents(fileName); 
		assertEquals(fileNewContents, fileContents, "Incorrect file contents returned");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.filePutAppend(null, fileContents);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File name string passed to filePutAppend is null",
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.filePutAppend(fileName, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Output data string passed to filePutAppend is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void filePutAppendLine() {		 
		String   fileContents;
		String   fileNewContents;
		String   fileName;
		/* Run a filePutAppendLine test */
		fileName = "HDLmUtilityTestFile.txt";
		fileContents = "efgh";
		HDLmUtility.fileClearContents(fileName); 
		HDLmUtility.filePutAppendLine(fileName, fileContents);
		fileNewContents = HDLmUtility.fileGetContents(fileName); 
		assertEquals(fileNewContents, fileContents + "\n", "Incorrect file contents returned");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.filePutAppendLine(null, fileContents);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File name string passed to filePutAppendLine is null",
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.filePutAppendLine(fileName, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Output data string passed to filePutAppendLine is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void filePutAppendLineLogs() {		 
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.filePutAppendLineLogs(null, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Output line passed to filePutAppendLineLogs is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void getRandomDouble() {
		/* Run a getRamdomDouble test */
		double   randomDouble;
	  randomDouble = HDLmUtility.getRandomDouble();
	  assertTrue((randomDouble >= 0.0), "Random double value is too low");
	  assertTrue((randomDouble <= 1.0), "Random double value is too high");		
	}
	@Test
	void getRandomDoubleList() {
		/* Run a getRamdomDoubleList test */
		ArrayList<Double>  doubleList;
		double   randomDouble;
	  doubleList = HDLmUtility.getRandomDoubleList(3);
	  assertEquals(3, doubleList.size(), "Random double list length is incorrect");
	  for (Double randomDoubleValue : doubleList) {
		  assertTrue((randomDoubleValue >= 0.0), "Random double value is too low");
		  assertTrue((randomDoubleValue <= 1.0), "Random double value is too high");		
	  }
		{
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmUtility.getRandomDoubleList(-1);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Number of values passed to getRandomDoubleList is less than zero",
					         "Unexpected exception message");
		}
	}
	@Test
	void getRandomFloat() {
		/* Run a getRamdomFloat test */
		float  randomFloat;
	  randomFloat = HDLmUtility.getRandomFloat();
	  assertTrue((randomFloat >= 0.0), "Random float value is too low");
	  assertTrue((randomFloat <= 1.0), "Random float value is too high");		
	}
	@Test
	void getMimeType() {	 
		String   mimeType;
		String   fileSuffix;
		/* Run a getMimeType test */
		fileSuffix = "txt";
		mimeType = HDLmUtility.getMimeType(fileSuffix);
		assertEquals("text/plain", mimeType, "Incorrect file MIME type returned");
		/* Run a getMimeType test */
		fileSuffix = "css";
		mimeType = HDLmUtility.getMimeType(fileSuffix);
		assertEquals("text/css", mimeType, "Incorrect file MIME type returned");
		/* Run a getMimeType test */
		fileSuffix = "js";
		mimeType = HDLmUtility.getMimeType(fileSuffix);
		assertEquals("text/javascript", mimeType, "Incorrect file MIME type returned");
		/* Run a getMimeType test */
		fileSuffix = "ico";
		mimeType = HDLmUtility.getMimeType(fileSuffix);
		assertEquals("image/x-icon", mimeType, "Incorrect file MIME type returned");
		/* Run a getMimeType test */
		fileSuffix = "png";
		mimeType = HDLmUtility.getMimeType(fileSuffix);
		assertEquals("image/png", mimeType, "Incorrect file MIME type returned");
		/* Run a getMimeType test */
		fileSuffix = "abc";
		mimeType = HDLmUtility.getMimeType(fileSuffix);
		assertNull(mimeType, "Incorrect file MIME type returned");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.getMimeType(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File suffix string passed to getMimeType is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void isTypeBinary() {	
		Boolean  fileBinary;
		String   fileSuffix;
		/* Run a isTypeBinary test */
		fileSuffix = "txt";
		fileBinary = HDLmUtility.isTypeBinary(fileSuffix);
		assertFalse(fileBinary, "Incorrect binary file indication returned");
		/* Run a isTypeBinary test */
		fileSuffix = "png";
		fileBinary = HDLmUtility.isTypeBinary(fileSuffix);
		assertTrue(fileBinary, "Incorrect binary file indication returned");
		/* Run a isTypeBinary test */
		fileSuffix = "abc";
		fileBinary = HDLmUtility.isTypeBinary(fileSuffix);
		assertNull(fileBinary, "Non-null value returned where null value expected"); 
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.isTypeBinary(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "File suffix string passed to isTypeBinary is null",
					         "Unexpected exception message");
		}
	}	
	@Test
	void splitLinesSuffix() {		 
		ArrayList<String>  fileLines;
		String             fileName;
		String             outString;
		/* Run a splitLinesSuffix test */
		fileName = "HDLmUtilityTestFile.txt";
		HDLmUtility.fileClearContents(fileName); 
		HDLmUtility.filePutAppendLine(fileName, "dcba ");
		HDLmUtility.filePutAppendLine(fileName, "dcba  ");
		HDLmUtility.filePutAppendLine(fileName, "dcba   ");
		fileLines = HDLmUtility.fileGetLines(fileName); 
		ArrayList<String>  arrayLines = new ArrayList<String>(fileLines);
		arrayLines = HDLmUtility.trimLinesRight(arrayLines);
		assertEquals("dcba", arrayLines.get(0), "Incorrect file contents returned");
		assertEquals("dcba", arrayLines.get(1), "Incorrect file contents returned");
		assertEquals("dcba", arrayLines.get(2), "Incorrect file contents returned");
		/* Combine the lines */
		outString = HDLmUtility.combineLinesSuffix(arrayLines, "\r\n");
		assertEquals(18, outString.length(), "Incorrect file contents length returned");
		assertEquals("dcba\r\ndcba\r\ndcba\r\n", outString, "Incorrect file contents returned");
		/* Try to split the string */
		String       suffix = "\r\n";
		arrayLines = HDLmUtility.splitLinesSuffix(outString, suffix);
		assertEquals(3, arrayLines.size(), "Incorrect array size returned from split lines");
		assertEquals("dcba", arrayLines.get(0), "Incorrect array entry returned from split lines");
		assertEquals("dcba", arrayLines.get(1), "Incorrect array entry returned from split lines");
		assertEquals("dcba", arrayLines.get(2), "Incorrect array entry returned from split lines");		
		/* Try to split the string */
		arrayLines = HDLmUtility.splitLinesSuffix("", suffix);
		assertEquals(0, arrayLines.size(), "Incorrect array size returned from split lines");
		/* Try to split the string */
		arrayLines = HDLmUtility.splitLinesSuffix(" ", suffix);
		assertEquals(1, arrayLines.size(), "Incorrect array size returned from split lines");
		assertEquals(" ", arrayLines.get(0), "Incorrect array entry returned from split lines");
		/* Try to split the string */
		arrayLines = HDLmUtility.splitLinesSuffix("X", suffix);
		assertEquals(1, arrayLines.size(), "Incorrect array size returned from split lines");
		assertEquals("X", arrayLines.get(0), "Incorrect array entry returned from split lines");
		/* Try to split the string */
		outString = "dcba\r\ndcba\r\ndcba\r\n";
		arrayLines = HDLmUtility.splitLinesSuffix(outString, suffix);
		assertEquals(3, arrayLines.size(), "Incorrect array size returned from split lines");
		/* Try to split the string */
		outString = "dc ba\r\ndc  ba\r\ndc   ba";
		arrayLines = HDLmUtility.splitLinesSuffix(outString, suffix);
		assertEquals(3, arrayLines.size(), "Incorrect array size returned from split lines");
		/* Try to split the string */
		outString = "dc ba\r\n";
		arrayLines = HDLmUtility.splitLinesSuffix(outString, suffix);
		assertEquals(1, arrayLines.size(), "Incorrect array size returned from split lines");
		assertEquals("dc ba", arrayLines.get(0), "Incorrect array entry returned from split lines");
		/* Try to split the string */
		outString = "\r\n";
		arrayLines = HDLmUtility.splitLinesSuffix(outString, suffix);
		assertEquals(1, arrayLines.size(), "Incorrect array size returned from split lines");
		assertEquals("", arrayLines.get(0), "Incorrect array entry returned from split lines");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.splitLinesSuffix(null, "");},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Input string passed to splitLinesSuffix is null",
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.splitLinesSuffix(fileName, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Suffix string passed to splitLinesSuffix is null",
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmUtility.splitLinesSuffix(fileName, "");},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Suffix string passed to splitLinesSuffix is empty",
					         "Unexpected exception message");
		}
	}
	@Test
	void trimLinesRight() {		 
		ArrayList<String>  fileLines;
		String             fileName;
		/* Run a trimLinesRight test */
		fileName = "HDLmUtilityTestFile.txt";
		HDLmUtility.fileClearContents(fileName); 
		HDLmUtility.filePutAppendLine(fileName, "dcba ");
		HDLmUtility.filePutAppendLine(fileName, "dcba  ");
		HDLmUtility.filePutAppendLine(fileName, "dcba   ");
		fileLines = HDLmUtility.fileGetLines(fileName); 
		ArrayList<String>  arrayLines = new ArrayList<String>(fileLines);
		arrayLines = HDLmUtility.trimLinesRight(arrayLines);
		assertEquals("dcba", arrayLines.get(0), "Incorrect file contents returned");
		assertEquals("dcba", arrayLines.get(1), "Incorrect file contents returned");
		assertEquals("dcba", arrayLines.get(2), "Incorrect file contents returned");		
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmUtility.trimLinesRight(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Input lines array passed to trimLinesRight is null",
					         "Unexpected exception message");
		}
	}
}
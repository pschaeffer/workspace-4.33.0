package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
/**
 * HDLmMatchTest short summary.
 *
 * HDLmMatchTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmMatchTest {
	@Test
	void globCheck() {
		boolean  isGlob;
		String   testStr;
		/* Run globCheck test */
		testStr = "abc";
		isGlob = HDLmMatch.globCheck(testStr);
		assertFalse(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "abcd";
		isGlob = HDLmMatch.globCheck(testStr);
		assertFalse(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd*ef";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd?ef";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd\\?ef";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd\\[ef";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd\\]ef";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd[ef";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd*ef";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\*cd?ef";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab[cd]";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab\\[cd\\]";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab[!cd]";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab[c!d]";
		isGlob = HDLmMatch.globCheck(testStr);
		assertTrue(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab[^cd";
		isGlob = HDLmMatch.globCheck(testStr);
		assertFalse(isGlob, "Incorrect glob check result"); 
		/* Run globCheck test */
		testStr = "ab[^cd]";
		isGlob = HDLmMatch.globCheck(testStr);
		assertFalse(isGlob, "Incorrect glob check result"); 
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmMatch.globCheck(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Match string passed to globCheck is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void globToRegex() {
		int      outStrLen;
		int      testStrLen;
		String   expStr;
		String   outStr;
		String   testStr;
		/* Run a globToRegex test */
		testStr = "abc";
		expStr = "^abc$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "abcd";
		expStr = "^abcd$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd";
		testStrLen = testStr.length();
		expStr = "^ab\\*cd$";
		outStr = HDLmMatch.globToRegex(testStr);
		outStrLen = outStr.length();
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd*ef";
		expStr = "^ab\\*cd[^\\/]*ef$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef";
		expStr = "^ab\\*cd[^\\/]{1,1}ef$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd\\?ef";
		expStr = "^ab\\*cd\\?ef$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd\\[ef";
		expStr = "^ab\\*cd\\[ef$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd\\]ef";
		expStr = "^ab\\*cd\\]ef$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd*ef";
		expStr = "^ab\\*cd[^\\/]*ef$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef";
		expStr = "^ab\\*cd[^\\/]{1,1}ef$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 	
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\\\";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\\\$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 	
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\?";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\?$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\?";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\?$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\*";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\*$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\[";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\[$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]aaaa";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]aaaa$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]aaaa\\\\";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]aaaa\\\\$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]aaaa\\\\bbbb";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]aaaa\\\\bbbb$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]aaaa\\\\bbbb\\(";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]aaaa\\\\bbbb\\($";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "bbbb\\\\(cccc";
		expStr = "^bbbb\\\\\\(cccc$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]aaaa\\\\bbbb\\\\(cccc";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]aaaa\\\\bbbb\\\\\\(cccc$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]aaaa\\\\bbbb\\\\eeee(cccc";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]aaaa\\\\bbbb\\\\eeee\\(cccc$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]aaaa\\\\bbbb\\\\ee\\\\ee(cccc";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]aaaa\\\\bbbb\\\\ee\\\\ee\\(cccc$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result");
		/* Run a globToRegex test */
		testStr = "ab\\*cd?ef\\]aaaa\\\\bbbb\\\\ee\\\\(cccc";
		expStr = "^ab\\*cd[^\\/]{1,1}ef\\]aaaa\\\\bbbb\\\\ee\\\\\\(cccc$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result");
		/* Run a globToRegex test */
		testStr = "ab[cd]";
		expStr = "^ab[cd]$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab\\[cd\\]";
		expStr = "^ab\\[cd\\]$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result");
		/* Run a globToRegex test */
		testStr = "ab[!cd]";
		expStr = "^ab[^cd]$";
		outStr = HDLmMatch.globToRegex(testStr);
		/* Run a globToRegex test */
		testStr = "ab[\\!cd]";
		expStr = "^ab[!cd]$";
		outStr = HDLmMatch.globToRegex(testStr);
		/* Run a globToRegex test */
		testStr = "ab[\\^cd]";
		expStr = "^ab[\\^cd]$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		testStr = "ab[\\!cd]";
		expStr = "^ab[!cd]$";
		outStr = HDLmMatch.globToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect glob to regex result"); 
		/* Run a globToRegex test */
		{
			String   testStrLocal = "ab\\*cd[[ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.globToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Some type of glob class has already been started",
					         "Unexpected exception message");
		}
		/* Run a globToRegex test */
		{
			String   testStrLocal = "ab\\*cd]ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.globToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "No glob class is currently active",
					         "Unexpected exception message");
		}	
		/* Run a globToRegex test */
		{
			String   testStrLocal = "ab\\*cd[ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.globToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Some type of glob class is active at end of input",
					         "Unexpected exception message");
		}		
		/* Run a globToRegex test */
		{
			String   testStrLocal = "ab\\*cd]ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.globToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "No glob class is currently active",
					         "Unexpected exception message");
		}	
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmMatch.globToRegex(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Glob string passed to globToRegex is null",
					         "Unexpected exception message");
		}
		{
			String   testStrLocal = "ab!ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.globToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Unescaped exclamation mark does not follow left bracket",
					         "Unexpected exception message");
		}
	}
	@Test
	void likeCheck() {
		boolean  isLike;
		String   testStr;
		/* Run likeCheck test */
		testStr = "abc";
		isLike = HDLmMatch.likeCheck(testStr);
		assertFalse(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "abcd";
		isLike = HDLmMatch.likeCheck(testStr);
		assertFalse(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\%cd";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\_cd";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\%cd_ef";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\_cd%ef";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\%cd\\_ef";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\%cd\\[ef";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\%cd\\]ef";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\%cd[ef";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\%cd%ef";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\%cd_ef";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab[cd]";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab\\[cd\\]";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab[!cd]";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab[c!d]";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab[!cd";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab!cd";
		isLike = HDLmMatch.likeCheck(testStr);
		assertFalse(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab[^cd";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result");
		/* Run likeCheck test */
		testStr = "ab[^cd]";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab[^cd]";
		isLike = HDLmMatch.likeCheck(testStr);
		assertTrue(isLike, "Incorrect like check result"); 
		/* Run likeCheck test */
		testStr = "ab!cd";
		isLike = HDLmMatch.likeCheck(testStr);
		assertFalse(isLike, "Incorrect like check result"); 
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmMatch.likeCheck(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Match string passed to likeCheck is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void likeToRegex() {
		int      outStrLen;
		int      testStrLen;
		String   expStr;
		String   outStr;
		String   testStr;
		/* Run a likeToRegex test */
		testStr = "abc";
		expStr = "^abc$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "abcd";
		expStr = "^abcd$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd";
		testStrLen = testStr.length();
		expStr = "^ab%cd$";
		outStr = HDLmMatch.likeToRegex(testStr);
		outStrLen = outStr.length();
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd%ef";
		expStr = "^ab%cd.*ef$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef";
		expStr = "^ab%cd.{1,1}ef$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd\\_ef";
		expStr = "^ab%cd_ef$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd\\[ef";
		expStr = "^ab%cd\\[ef$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd\\]ef";
		expStr = "^ab%cd\\]ef$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd[^]ef";
		expStr = "^ab%cd[^]ef$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef";
		expStr = "^ab%cd.{1,1}ef$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 	
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\\\aa";
		expStr = "^ab%cd.{1,1}ef\\\\aa$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 	
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\_";
		expStr = "^ab%cd.{1,1}ef_$";
		outStr = HDLmMatch.likeToRegex(testStr);
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\\\_";
		expStr = "^ab%cd.{1,1}ef\\\\.{1,1}$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\_";
		expStr = "^ab%cd.{1,1}ef_$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\%";
		expStr = "^ab%cd.{1,1}ef%$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\[";
		expStr = "^ab%cd.{1,1}ef\\[$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\]";
		expStr = "^ab%cd.{1,1}ef\\]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\]aaaa";
		expStr = "^ab%cd.{1,1}ef\\]aaaa$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab%cd_ef\\]aaaa\\\\aa";
		expStr = "^ab.*cd.{1,1}ef\\]aaaa\\\\aa$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\]aaaa\\\\bbbb";
		expStr = "^ab%cd.{1,1}ef\\]aaaa\\\\bbbb$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\]aaaa\\\\bbbb\\(";
		expStr = "^ab%cd.{1,1}ef\\]aaaa\\\\bbbb\\($";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\]aaaa\\\\bbbb\\(cccc";
		expStr = "^ab%cd.{1,1}ef\\]aaaa\\\\bbbb\\(cccc$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\]aaaa\\\\bbbb\\\\eeee(cccc";
		expStr = "^ab%cd.{1,1}ef\\]aaaa\\\\bbbb\\\\eeee\\(cccc$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\]aaaa\\\\bbbb\\\\ee\\\\ee(cccc";
		expStr = "^ab%cd.{1,1}ef\\]aaaa\\\\bbbb\\\\ee\\\\ee\\(cccc$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result");
		/* Run a likeToRegex test */
		testStr = "ab\\%cd_ef\\]aaaa\\\\bbbb\\\\ee\\\\(cccc";
		expStr = "^ab%cd.{1,1}ef\\]aaaa\\\\bbbb\\\\ee\\\\\\(cccc$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result");
		/* Run a likeToRegex test */
		testStr = "ab[cd]";
		expStr = "^ab[cd]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\[cd\\]";
		expStr = "^ab\\[cd\\]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result");
		/* Run a likeToRegex test */
		testStr = "ab[!cd]";
		expStr = "^ab[!cd]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		/* Run a likeToRegex test */
		testStr = "ab[^cd]";
		expStr = "^ab[^cd]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab[\\!cd]";
		expStr = "^ab[!cd]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\[cd\\]";
		expStr = "^ab\\[cd\\]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\[\\^cd\\]";
		expStr = "^ab\\[\\^cd\\]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab\\[c\\^d\\]";
		expStr = "^ab\\[c\\^d\\]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab[c\\^d]";
		expStr = "^ab[c\\^d]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab[\\^cd]";
		expStr = "^ab[\\^cd]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		testStr = "ab[^cd]";
		expStr = "^ab[^cd]$";
		outStr = HDLmMatch.likeToRegex(testStr);
		assertEquals(expStr, outStr, "Incorrect like to regex result"); 
		/* Run a likeToRegex test */
		{
			String   testStrLocal = "ab\\*cd[[ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.likeToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Some type of like class has already been started",
					         "Unexpected exception message");
		}
		/* Run a likeToRegex test */
		{
			String   testStrLocal = "ab\\*cd]ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.likeToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "No like class is currently active",
					         "Unexpected exception message");
		}	
		/* Run a likeToRegex test */
		{
			String   testStrLocal = "ab\\*cd[ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.likeToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Some type of like class is active at end of input",
					         "Unexpected exception message");
		}		
		/* Run a likeToRegex test */
		{
			String   testStrLocal = "ab\\*cd]ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.likeToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "No like class is currently active",
					         "Unexpected exception message");
		}	
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmMatch.likeToRegex(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Like string passed to likeToRegex is null",
					         "Unexpected exception message");
		}
		{
			String   testStrLocal = "ab^ef";
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmMatch.likeToRegex(testStrLocal);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Unescaped not sign does not follow left bracket",
					         "Unexpected exception message");
		}
	}
}
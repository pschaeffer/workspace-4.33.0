package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
/**
 * HDLmStringTest short summary.
 *
 * HDLmStringTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmStringTest {
	@Test
	void endsWith() {
		/* Run a few endsWith tests */
		assertEquals(true, HDLmString.endsWith("haystackofhay", "hay"), "HDLmString.endsWith did not return true");
		assertTrue(HDLmString.endsWith("haystackofhay", "hay"), "HDLmString.endsWith did not return true");
		assertTrue(HDLmString.endsWith("haystack", "haystack"), "HDLmString.endsWith did not return true");
		assertTrue(HDLmString.endsWith("haystack", ""), "HDLmString.endsWith did not return true");
		assertFalse(HDLmString.endsWith("haystack", "nay"), "HDLmString.endsWith did not return false");
		assertFalse(HDLmString.endsWith("haystack", "haystacks"), "HDLmString.endsWith did not return false");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.endsWith(null, "");},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Haystack value passed to endsWith is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(NullPointerException.class, 
	                                       () -> {HDLmString.endsWith("", null);},
	                                      "Expected NullPointerException");
	    String execMsg = exception.getMessage();
	    assertEquals("Needle value passed to endsWith is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void explodeWhitespace() {
		/* Run a few explodeWhitespace tests */
		ArrayList<String> al = new ArrayList<String>();  
		al = HDLmString.explodeWhitespace("");
		assertEquals(0, al.size(), "HDLmString.explodeWhitespace did not return zero");
		al = HDLmString.explodeWhitespace("haystack");
		assertEquals(1, al.size(), "HDLmString.explodeWhitespace did not return one");
		assertEquals("haystack", al.get(0), "HDLmString.explodeWhitespace did not return correct value");
		al = HDLmString.explodeWhitespace("  haystack");
		assertEquals(1, al.size(), "HDLmString.explodeWhitespace did not return one");
		assertEquals("haystack", al.get(0), "HDLmString.explodeWhitespace did not return correct value");
		al = HDLmString.explodeWhitespace("haystack  ");
		assertEquals(1, al.size(), "HDLmString.explodeWhitespace did not return one");
		assertEquals("haystack", al.get(0), "HDLmString.explodeWhitespace did not return correct value");
		al = HDLmString.explodeWhitespace("  haystack  ");
		assertEquals(1, al.size(), "HDLmString.explodeWhitespace did not return one");
		assertEquals("haystack", al.get(0), "HDLmString.explodeWhitespace did not return correct value");
		al = HDLmString.explodeWhitespace("  haystack needle ");
		assertEquals(2, al.size(), "HDLmString.explodeWhitespace did not return one");
		assertEquals("haystack", al.get(0), "HDLmString.explodeWhitespace did not return correct value");
		assertEquals("needle", al.get(1), "HDLmString.explodeWhitespace did not return correct value");
		al = HDLmString.explodeWhitespace("haystack   needle");
		assertEquals(2, al.size(), "HDLmString.explodeWhitespace did not return one");
		assertEquals("haystack", al.get(0), "HDLmString.explodeWhitespace did not return correct value");
		assertEquals("needle", al.get(1), "HDLmString.explodeWhitespace did not return correct value");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.explodeWhitespace(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input string value passed to explodeWhitespace is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void fromArray() {
		/* Run a few fromArray tests */
		ArrayList<String> al = new ArrayList<String>();  
		String            out;
		out = HDLmString.fromArray(al);
		assertEquals(0, out.length(), "HDLmString.fromArray did not return a zero-length string");
		al.add("hay");
		out = HDLmString.fromArray(al);
		assertEquals(3, out.length(), "HDLmString.fromArray did not return the correct string length");
		assertEquals("hay", out, "HDLmString.fromArray did not return the correct string");
		al.add("stack");
		out = HDLmString.fromArray(al);
		assertEquals(9, out.length(), "HDLmString.fromArray did not return the correct string length");
		assertEquals("hay stack", out, "HDLmString.fromArray did not return the correct string");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.fromArray(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input string array list value passed to fromArray is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void fromArrayDoubles() {
		/* Run a few fromArray tests */
		ArrayList<Double>  ad = new ArrayList<Double>();  
		String            out;
		out = HDLmString.fromDoublesArray(ad);
		assertEquals(0, out.length(), "HDLmString.fromDoublesArray did not return a zero-length string");
		ad.add(0.5);
		out = HDLmString.fromDoublesArray(ad);
		assertEquals(3, out.length(), "HDLmString.fromDoublesArray did not return the correct string length");
		assertEquals("0.5", out, "HDLmString.fromArray did not return the correct string");
		ad.add(0.25);
		out = HDLmString.fromDoublesArray(ad);
		assertEquals(8, out.length(), "HDLmString.fromDoublesArray did not return the correct string length");
		assertEquals("0.5 0.25", out, "HDLmString.fromDoublesArray did not return the correct string");
		ad.add(1.75);
		out = HDLmString.fromDoublesArray(ad);
		assertEquals(13, out.length(), "HDLmString.fromDoublesArray did not return the correct string length");
		assertEquals("0.5 0.25 1.75", out, "HDLmString.fromDoublesArray did not return the correct string");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.fromDoublesArray(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input Doubles array list value passed to fromDoublesArray is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getTokens() {
		/* Run a few getTokens tests */
		ArrayList<HDLmToken> al = new ArrayList<HDLmToken>(); 
		HDLmToken            tok;
		String               inStr;
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.getTokens(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input string value passed to getTokens is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmString.getTokens("", 'a');},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals("Quote character (a) passed to getTokens is invalid",  execMsg,"Unexpected exception message");
		}
		inStr = "";
		al = HDLmString.getTokens(inStr);
		assertEquals(1, al.size(), "HDLmString.getTokens did not return the correct number of tokens");
		inStr = "  aaa + 'abc' ";
		al = HDLmString.getTokens(inStr);
		assertEquals(8, al.size(), "HDLmString.getTokens did not return the correct number of tokens");
		tok = al.get(0);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(0, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("  ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(1);
		assertEquals(HDLmTokenTypes.IDENTIFIER, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(2, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("aaa", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(2);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(5, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(3);
		assertEquals(HDLmTokenTypes.OPERATOR, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(6, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("+", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(4);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(7, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(5);
		assertEquals(HDLmTokenTypes.QUOTED, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(8, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("abc", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(6);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(13, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(7);
		assertEquals(HDLmTokenTypes.END, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(14, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		inStr = "  123 ; 'abc' ";
		al = HDLmString.getTokens(inStr);
		assertEquals(8, al.size(), "HDLmString.getTokens did not return the correct number of tokens");
		tok = al.get(0);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(0, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("  ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(1);
		assertEquals(HDLmTokenTypes.INTEGER, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(2, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("123", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(2);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(5, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(3);
		assertEquals(HDLmTokenTypes.UNKNOWN, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(6, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(";", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(4);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(7, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(5);
		assertEquals(HDLmTokenTypes.QUOTED, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(8, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("abc", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(6);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(13, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(7);
		assertEquals(HDLmTokenTypes.END, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(14, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		inStr = "  123 ; 'abc''def' ";
		al = HDLmString.getTokens(inStr);
		assertEquals(8, al.size(), "HDLmString.getTokens did not return the correct number of tokens");
		tok = al.get(0);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(0, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("  ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(1);
		assertEquals(HDLmTokenTypes.INTEGER, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(2, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("123", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(2);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(5, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(3);
		assertEquals(HDLmTokenTypes.UNKNOWN, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(6, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(";", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(4);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(7, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(5);
		assertEquals(HDLmTokenTypes.QUOTED, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(8, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("abc'def", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(6);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(18, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(7);
		assertEquals(HDLmTokenTypes.END, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(19, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		inStr = "  123 ; 'abc''def' ";
		al = HDLmString.getTokens(inStr, '\'');
		assertEquals(8, al.size(), "HDLmString.getTokens did not return the correct number of tokens");
		tok = al.get(0);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(0, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("  ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(1);
		assertEquals(HDLmTokenTypes.INTEGER, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(2, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("123", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(2);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(5, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(3);
		assertEquals(HDLmTokenTypes.UNKNOWN, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(6, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(";", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(4);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(7, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(5);
		assertEquals(HDLmTokenTypes.QUOTED, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(8, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("abc'def", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(6);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(18, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(7);
		assertEquals(HDLmTokenTypes.END, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(19, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		inStr = "  123 ; \"'abc''def'\" ";
		al = HDLmString.getTokens(inStr, '"');
		assertEquals(8, al.size(), "HDLmString.getTokens did not return the correct number of tokens");
		tok = al.get(0);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(0, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("  ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(1);
		assertEquals(HDLmTokenTypes.INTEGER, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(2, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("123", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(2);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(5, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(3);
		assertEquals(HDLmTokenTypes.UNKNOWN, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(6, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(";", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(4);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(7, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(5);
		assertEquals(HDLmTokenTypes.QUOTED, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(8, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("'abc''def'", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(6);
		assertEquals(HDLmTokenTypes.SPACE, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(20, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals(" ", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
		tok = al.get(7);
		assertEquals(HDLmTokenTypes.END, tok.getType(), "HDLmString.getTokens did not return the correct token type");
		assertEquals(21, tok.getIndex(), "HDLmString.getTokens did not return the correct token index");
		assertEquals("", tok.getValue(), "HDLmString.getTokens did not return the correct token value");
	}
	@Test
	void getTokensNonWhite() {
		/* Run a few getTokensNonWhite tests */
		ArrayList<HDLmToken> al = new ArrayList<HDLmToken>(); 
		HDLmToken            tok;
		String               inStr;
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.getTokensNonWhite(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input string value passed to getTokensNonWhite is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmString.getTokensNonWhite("", 'a');},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals("Quote character (a) passed to getTokensNonWhite is invalid",  execMsg,"Unexpected exception message");
		}
		inStr = "";
		al = HDLmString.getTokensNonWhite(inStr);
		assertEquals(1, al.size(), "HDLmString.getTokensNonWhite did not return the correct number of tokens");
		inStr = "  aaa + 'abc' ";
		al = HDLmString.getTokensNonWhite(inStr);
		assertEquals(4, al.size(), "HDLmString.getTokensNonWhite did not return the correct number of tokens");
		tok = al.get(0);
		assertEquals(HDLmTokenTypes.IDENTIFIER, tok.getType(), "HDLmString.getTokensNonWhite did not return the correct token type");
		assertEquals(2, tok.getIndex(), "HDLmString.getTokensNonWhite did not return the correct token index");
		assertEquals("aaa", tok.getValue(), "HDLmString.getTokensNonWhite did not return the correct token value");
		tok = al.get(1);
		assertEquals(HDLmTokenTypes.OPERATOR, tok.getType(), "HDLmString.getTokensNonWhite did not return the correct token type");
		assertEquals(6, tok.getIndex(), "HDLmString.getTokensNonWhite did not return the correct token index");
		assertEquals("+", tok.getValue(), "HDLmString.getTokensNonWhite did not return the correct token value");
		tok = al.get(2);
		assertEquals(HDLmTokenTypes.QUOTED, tok.getType(), "HDLmString.getTokensNonWhite did not return the correct token type");
		assertEquals(8, tok.getIndex(), "HDLmString.getTokensNonWhite did not return the correct token index");
		assertEquals("abc", tok.getValue(), "HDLmString.getTokensNonWhite did not return the correct token value");
		tok = al.get(3);
		assertEquals(HDLmTokenTypes.END, tok.getType(), "HDLmString.getTokensNonWhite did not return the correct token type");
		assertEquals(14, tok.getIndex(), "HDLmString.getTokensNonWhite did not return the correct token index");
		assertEquals("", tok.getValue(), "HDLmString.getTokensNonWhite did not return the correct token value");
		inStr = "  123 ; 'abc' ";
		al = HDLmString.getTokensNonWhite(inStr);		assertEquals(4, al.size(), "HDLmString.getTokensNonWhite did not return the correct number of tokens");
		tok = al.get(0);
		assertEquals(HDLmTokenTypes.INTEGER, tok.getType(), "HDLmString.getTokensNonWhite did not return the correct token type");
		assertEquals(2, tok.getIndex(), "HDLmString.getTokensNonWhite did not return the correct token index");
		assertEquals("123", tok.getValue(), "HDLmString.getTokensNonWhite did not return the correct token value");
		tok = al.get(1);
		assertEquals(HDLmTokenTypes.UNKNOWN, tok.getType(), "HDLmString.getTokensNonWhite did not return the correct token type");
		assertEquals(6, tok.getIndex(), "HDLmString.getTokensNonWhite did not return the correct token index");
		assertEquals(";", tok.getValue(), "HDLmString.getTokensNonWhite did not return the correct token value");
		tok = al.get(2);
		assertEquals(HDLmTokenTypes.QUOTED, tok.getType(), "HDLmString.getTokensNonWhite did not return the correct token type");
		assertEquals(8, tok.getIndex(), "HDLmString.getTokensNonWhite did not return the correct token index");
		assertEquals("abc", tok.getValue(), "HDLmString.getTokensNonWhite did not return the correct token value");
		tok = al.get(3);
		assertEquals(HDLmTokenTypes.END, tok.getType(), "HDLmString.getTokensNonWhite did not return the correct token type");
		assertEquals(14, tok.getIndex(), "HDLmString.getTokensNonWhite did not return the correct token index");
		assertEquals("", tok.getValue(), "HDLmString.getTokensNonWhite did not return the correct token value");
	}
	@Test
	void getTrailingDigits() {
		/* Run a few getTrailingDigits tests */
		String  inStr; 
		String  outStr;
		/* Run a getTrailingDigits test */
		inStr = "";
		outStr = HDLmString.getTrailingDigits(inStr);
		assertEquals("", outStr, "HDLmString.getTrailingDigits did not return the correct value");
		inStr = "A";
		outStr = HDLmString.getTrailingDigits(inStr);
		assertEquals("", outStr, "HDLmString.getTrailingDigits did not return the correct value");
		inStr = "5";
		outStr = HDLmString.getTrailingDigits(inStr);
		assertEquals("5", outStr, "HDLmString.getTrailingDigits did not return the correct value");
		inStr = "A5";
		outStr = HDLmString.getTrailingDigits(inStr);
		assertEquals("5", outStr, "HDLmString.getTrailingDigits did not return the correct value");
		inStr = "5A";
		outStr = HDLmString.getTrailingDigits(inStr);
		assertEquals("", outStr, "HDLmString.getTrailingDigits did not return the correct value");
		inStr = "A56";
		outStr = HDLmString.getTrailingDigits(inStr);
		assertEquals("56", outStr, "HDLmString.getTrailingDigits did not return the correct value");
		inStr = "5A6";
		outStr = HDLmString.getTrailingDigits(inStr);
		assertEquals("6", outStr, "HDLmString.getTrailingDigits did not return the correct value");
		inStr = "Report000023";
		outStr = HDLmString.getTrailingDigits(inStr);
		assertEquals("000023", outStr, "HDLmString.getTrailingDigits did not return the correct value");
		/* Run a getTrailingDigits test */
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.getTrailingDigits(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input string value passed to getTrailingDigits is null",  execMsg,"Unexpected exception message");
		}		
	}
	@Test
	void isAlpha() {
		/* Run a few isAlpha tests */
		assertTrue(HDLmString.isAlphaHyphen('a'), "HDLmString.isAlphaHyphen did not return true");
		assertTrue(HDLmString.isAlphaHyphen('A'), "HDLmString.isAlphaHyphen did not return true");
		assertTrue(HDLmString.isAlphaHyphen('b'), "HDLmString.isAlphaHyphen did not return true");
		assertTrue(HDLmString.isAlphaHyphen('B'), "HDLmString.isAlphaHyphen did not return true");
		assertTrue(HDLmString.isAlphaHyphen('_'), "HDLmString.isAlphaHyphen did not return true");
		assertFalse(HDLmString.isAlphaHyphen('9'), "HDLmString.isAlphaHyphen did not return false");
	}
	@Test
	void isAlphaNumeric() {
		/* Run a few isAlphaNumeric tests */
		assertTrue(HDLmString.isAlphaNumericHyphen('a'), "HDLmString.isAlphaNumericHyphen did not return true");
		assertTrue(HDLmString.isAlphaNumericHyphen('A'), "HDLmString.isAlphaNumericHyphen did not return true");
		assertTrue(HDLmString.isAlphaNumericHyphen('b'), "HDLmString.isAlphaNumericHyphen did not return true");
		assertTrue(HDLmString.isAlphaNumericHyphen('B'), "HDLmString.isAlphaNumericHyphen did not return true");
		assertTrue(HDLmString.isAlphaNumericHyphen('_'), "HDLmString.isAlphaNumericHyphen did not return true");
		assertTrue(HDLmString.isAlphaNumericHyphen('9'), "HDLmString.isAlphaNumericHyphen did not return true");
		assertFalse(HDLmString.isAlphaNumericHyphen('+'), "HDLmString.isAlphaNumericHyphen did not return false");
	}
	@Test
	void isDigit() {
		/* Run a few isDigit tests */
		assertFalse(HDLmString.isDigit('a'), "HDLmString.isDigit did not return false");
		assertFalse(HDLmString.isDigit('A'), "HDLmString.isDigit did not return false");
		assertFalse(HDLmString.isDigit('b'), "HDLmString.isDigit did not return false");
		assertFalse(HDLmString.isDigit('B'), "HDLmString.isDigit did not return false");
		assertFalse(HDLmString.isDigit('_'), "HDLmString.isDigit did not return false");
		assertTrue(HDLmString.isDigit('9'), "HDLmString.isDigit did not return true");
		assertFalse(HDLmString.isDigit('+'), "HDLmString.isDigit did not return false");
	}
	@Test
	void isOperator() {
		/* Run a few isOperator tests */
		assertFalse(HDLmString.isOperator('a'), "HDLmString.isOperator did not return false");
		assertFalse(HDLmString.isOperator('A'), "HDLmString.isOperator did not return false");
		assertFalse(HDLmString.isOperator('b'), "HDLmString.isOperator did not return false");
		assertFalse(HDLmString.isOperator('B'), "HDLmString.isOperator did not return false");
		assertFalse(HDLmString.isOperator('_'), "HDLmString.isOperator did not return false");
		assertTrue(HDLmString.isOperator('+'), "HDLmString.isOperator did not return true");
		assertFalse(HDLmString.isOperator('9'), "HDLmString.isOperator did not return false");
	}
	@Test
	void isWhitespace() {
		/* Run a few isWhitespace tests */
		assertFalse(HDLmString.isWhitespace('a'), "HDLmString.isWhitespace did not return false");
		assertFalse(HDLmString.isWhitespace('A'), "HDLmString.isWhitespace did not return false");
		assertFalse(HDLmString.isWhitespace('b'), "HDLmString.isWhitespace did not return false");
		assertFalse(HDLmString.isWhitespace('B'), "HDLmString.isWhitespace did not return false");
		assertFalse(HDLmString.isWhitespace('_'), "HDLmString.isWhitespace did not return false");
		assertTrue(HDLmString.isWhitespace(' '), "HDLmString.isWhitespace did not return true");
		assertTrue(HDLmString.isWhitespace('\n'), "HDLmString.isWhitespace did not return true");
		assertTrue(HDLmString.isWhitespace('\u000b'), "HDLmString.isWhitespace did not return true");
		assertFalse(HDLmString.isWhitespace('9'), "HDLmString.isOperator did not return false");
	}
	@Test
	void removeFileNumberTail() {
		/* Run a few removeFileNumberTail tests */
		String  expectedString;
		String  nameWithNoTail = "nameWithNoTail";
		expectedString = nameWithNoTail;
		nameWithNoTail = HDLmString.removeFileNumberTail(nameWithNoTail);
		assertEquals(expectedString, nameWithNoTail, "The function removeFileNumberTail did not work");
		String  nameWithTail = "nameWithTail(3)";
		expectedString = "nameWithTail";
		nameWithTail = HDLmString.removeFileNumberTail(nameWithTail);
		assertEquals(expectedString, nameWithTail, "The function removeFileNumberTail did not work");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.removeFileNumberTail(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input string value passed to removeFileNumberTail is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void startsWith() {
		/* Run a few startsWith tests */
		assertEquals(true, HDLmString.startsWith("haystackofhay", "hay"), "HDLmString.startsWith did not return true");
		assertTrue(HDLmString.startsWith("haystackofhay", "hay"), "HDLmString.startsWith did not return true");
		assertTrue(HDLmString.startsWith("haystack", "haystack"), "HDLmString.startsWith did not return true");
		assertTrue(HDLmString.startsWith("haystack", ""), "HDLmString.startsWith did not return true");
		assertFalse(HDLmString.startsWith("haystack", "nay"), "HDLmString.startsWith did not return false");
		assertFalse(HDLmString.startsWith("haystack", "haystacks"), "HDLmString.startsWith did not return false");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.startsWith(null, "");},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Haystack value passed to startsWith is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(NullPointerException.class, 
	                                       () -> {HDLmString.startsWith("", null);},
	                                      "Expected NullPointerException");
	    String execMsg = exception.getMessage();
	    assertEquals("Needle value passed to startsWith is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void strReplaceOnce() {
		String needle;
		String replacement;
		String haystack;
		/* Run a few strReplaceOnce tests */
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.strReplaceOnce(null, "", "");},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Needle value passed to strReplaceOnce is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(NullPointerException.class, 
	                                       () -> {HDLmString.strReplaceOnce("", null, "");},
	                                      "Expected NullPointerException");
	    String execMsg = exception.getMessage();
	    assertEquals("Replacement value passed to strReplaceOnce is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(NullPointerException.class, 
	                                       () -> {HDLmString.strReplaceOnce("", "", null);},
	                                      "Expected NullPointerException");
	    String execMsg = exception.getMessage();
	    assertEquals("Haystack value passed to strReplaceOnce is null",  execMsg,"Unexpected exception message");
		}
		assertEquals(" nay hay ", HDLmString.strReplaceOnce("hay", "nay", " hay hay "), "HDLmString.strReplaceOnce did not return correct value");
		assertEquals(" say hay ", HDLmString.strReplaceOnce("hay", "say", " hay hay "), "HDLmString.strReplaceOnce did not return correct value");
		assertEquals(" hay hay ", HDLmString.strReplaceOnce("nay", "nay", " hay hay "), "HDLmString.strReplaceOnce did not return correct value");
		assertEquals(" hey nay ", HDLmString.strReplaceOnce("hay", "nay", " hey hay "), "HDLmString.strReplaceOnce did not return correct value");
	}
	@Test
	void strTruncate() {
    String inStr;
    String outStr;
		/* Run a few strTruncate tests */
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmString.strTruncate(null, 15);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input string value passed to strTruncate is null",  execMsg,"Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
	                                       () -> {HDLmString.strTruncate("", -2);},
	                                      "Expected IllegalArgumentException");
	    String execMsg = exception.getMessage();
	    assertEquals("Maximum length (-2) passed to strTruncate is less than zero",  execMsg,"Unexpected exception message");
		}
    inStr = "abcd";
    assertEquals("abcd", HDLmString.strTruncate(inStr, 5), "HDLmString.strTruncate did not return correct value");
    assertEquals("abcd", HDLmString.strTruncate(inStr, 4), "HDLmString.strTruncate did not return correct value");
    assertEquals("abc", HDLmString.strTruncate(inStr, 3), "HDLmString.strTruncate did not return correct value");
    assertEquals("ab", HDLmString.strTruncate(inStr, 2), "HDLmString.strTruncate did not return correct value");
    assertEquals("a", HDLmString.strTruncate(inStr, 1), "HDLmString.strTruncate did not return correct value");
    assertEquals("", HDLmString.strTruncate(inStr, 0), "HDLmString.strTruncate did not return correct value");
    inStr = "";
    assertEquals("", HDLmString.strTruncate(inStr, 5), "HDLmString.strTruncate did not return correct value");
    assertEquals("", HDLmString.strTruncate(inStr, 4), "HDLmString.strTruncate did not return correct value");
    assertEquals("", HDLmString.strTruncate(inStr, 3), "HDLmString.strTruncate did not return correct value");
    assertEquals("", HDLmString.strTruncate(inStr, 2), "HDLmString.strTruncate did not return correct value");
    assertEquals("", HDLmString.strTruncate(inStr, 1), "HDLmString.strTruncate did not return correct value");
    assertEquals("", HDLmString.strTruncate(inStr, 0), "HDLmString.strTruncate did not return correct value");		
	}
	@Test
	void ucFirst() {		 
		String   inValue;
		String   outValue;
		/* Run a ucFirst test */
		inValue = "";
		outValue = HDLmString.ucFirst(inValue);
		assertEquals("", outValue, "Incorrect output from uppercase first character");	
		/* Run a ucFirst test */
		inValue = "x";
		outValue = HDLmString.ucFirst(inValue);
		assertEquals("X", outValue, "Incorrect output from uppercase first character");	
		/* Run a ucFirst test */
		inValue = "X";
		outValue = HDLmString.ucFirst(inValue);
		assertEquals("X", outValue, "Incorrect output from uppercase first character");	
		/* Run a ucFirst test */
		inValue = "xy";
		outValue = HDLmString.ucFirst(inValue);
		assertEquals("Xy", outValue, "Incorrect output from uppercase first character");	
		/* Run a ucFirst test */
		inValue = "Xy";
		outValue = HDLmString.ucFirst(inValue);
		assertEquals("Xy", outValue, "Incorrect output from uppercase first character");	
		/* Run a ucFirst test */
		inValue = "XY";
		outValue = HDLmString.ucFirst(inValue);
		assertEquals("XY", outValue, "Incorrect output from uppercase first character");	
		/* Run a ucFirst test */
		inValue = "xyz";
		outValue = HDLmString.ucFirst(inValue);
		assertEquals("Xyz", outValue, "Incorrect output from uppercase first character");	
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmString.ucFirst(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Input string passed to ucFirst is null", execMsg,
					         "Unexpected exception message");
		}
	}
}
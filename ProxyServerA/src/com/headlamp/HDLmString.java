package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmString short summary.
 *
 * HDLmString description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmString {	
	/* The next statement initializes logging to some degree. Note that having the
     slf4j jars and the log4j jars in the classpath also plays some role in
     logging initialization.	 */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmString.class);
	/* This class can never be instantiated */
	private HDLmString() {}
	/* The list of image suffix values is stored below */ 
  public static final ArrayList<String>  HDLmStringImageTypes = new ArrayList<String>(
	  List.of("ai",
		        "avif",
		        "bmp", "dib",
		        "cdr",
		        "eps",
		        "exif",
		        "gif",
		        "heif", "heic",
		        "ico", "cur",
		        "ind", "indd", "indt", "idml",
		        "jpg", "jpeg", "jpe", "jif", "jfif", "jfi", "pjpeg", "pjp",
		        "jp2", "j2k", "jpf", "jpm",
		        "jpg2", "j2c", "jpc", "jpx",
		        "mj2",
		        "pdf",
		        "ppm", "pgm", "pbm", "pnm",
		        "png", "apng",
		        "psd",
		        "raw", "arw", "cr", "crw", "cr2", "cr3", "erf", "k25",
		        "nef", "nrw", "orf", "pef", "rw2", "srf", "sr2", "xdc",
		        "svg", "svgz",
		        "tiff", "tif",
		        "webp",
		        "xbm",
		        "xcf"));
	/* This routine compacts an input string. The input string is not
	   modified. All of the input characters from the input string are
	   copied to the output string, except for blanks and a few other
	   characters. The compacted string is returned to the caller. Note
	   that the sequence \n is also removed. This sequence is a new line. */ 
	protected static String  compactString(String inputString) {
		/* Declare and define a few values */
		String  rvStr = "";
		/* Check a few values passed by the caller */
		if (inputString == null) {
		  String   errorText = "Input string to be compared passed to compareStrings is null";
		  throw new NullPointerException(errorText);		
		}
		String  modString = inputString.replaceAll("\\\\n", "");
		/* Get the modified string length */
		int   modStrLen = modString.length(); 
		/* Build the compacted string, one character at a time */
		for (int i = 0; i < modStrLen; i++) {
			/* Get a character from the input string */
			char  modChar = modString.charAt(i); 
			/* Check if the input character should be skipped */
			if (modChar == 10 ||
					modChar == 32)
				continue;
			rvStr += modChar;
		}
		return rvStr;		
	}
	/* This routine compares two strings. The index of the first 
	   difference is returned to the caller. Each string is changed
	   by removing all blanks and other characters that could cause
	   false mismatches. Note, that if the strings match, then a 
	   value of minus one will be returned to the caller. */
	protected static int  compareStringsCompacted(String firstString, String secondString) {
		/* Declare and define a few values */
		int   rvInt = -1;
		/* Check a few values passed by the caller */
		if (firstString == null) {
		  String   errorText = "First string to be compared passed to compareStringsCompacted is null";
		  throw new NullPointerException(errorText);		
		}
		if (secondString == null) {
		  String   errorText = "second string to be compared passed to compareStringsCompacted is null";
		  throw new NullPointerException(errorText);		
		}
		/* Compact both of the input strings */		 
		String  firstStrComp = HDLmString.compactString(firstString);
		String  secondStrComp = HDLmString.compactString(secondString);
		/* Get all of the string lengths */
		int   firstCompLen = firstStrComp.length();
		int   secondCompLen = secondStrComp.length();
		int   minCompLen = Math.min(firstCompLen, secondCompLen);
		/* Compare the compacted strings, character by character */
		for (int i = 0; i < minCompLen; i++) {
			/* Get a character from each compacted string */
			char  firstCompChar = firstStrComp.charAt(i);
			char  secondCompChar = secondStrComp.charAt(i);
			/* Compare the characters */
			if (firstCompChar == secondCompChar)
				continue;
			rvInt = i;
			/* Display some information related to the mismatch */
			LOG.info(String.valueOf(i));
			LOG.info(firstStrComp.substring(i-350, i-300));
			LOG.info(firstStrComp.substring(i-300, i-250));
			LOG.info(firstStrComp.substring(i-250, i-200));
			LOG.info(firstStrComp.substring(i-200, i-150));
			LOG.info(firstStrComp.substring(i-150, i-100));
			LOG.info(firstStrComp.substring(i-100, i-50));	
			LOG.info(firstStrComp.substring(i-50, i+1));	
			LOG.info(secondStrComp.substring(i-350, i-300));
			LOG.info(secondStrComp.substring(i-300, i-250));
			LOG.info(secondStrComp.substring(i-250, i-200));
			LOG.info(secondStrComp.substring(i-200, i-150));
			LOG.info(secondStrComp.substring(i-150, i-100));
			LOG.info(secondStrComp.substring(i-100, i-50));	
			LOG.info(secondStrComp.substring(i-50, i+1));	
			break;
		}
		return rvInt;		
	}
	/* This routine compares two strings. The strings are compared by
	   building a token array from each string. The token arrays are 
	   then compared and the index of the first mismatch is returned
	   to the caller. Blank tokens in either string are just ignored.  	   
	   Note, that if the strings match, then a value of minus one will
	   be returned to the caller. The returned index is the offset of
	   the first token that does not match. */
	protected static int  compareStringsTokens(String firstString, String secondString) {
		/* Declare and define a few values */
		int   rvInt = -1;
		/* Check a few values passed by the caller */
		if (firstString == null) {
		  String   errorText = "First string to be compared passed to compareStringsTokens is null";
		  throw new NullPointerException(errorText);		
		}
		if (secondString == null) {
		  String   errorText = "second string to be compared passed to compareStringsTokens is null";
		  throw new NullPointerException(errorText);		
		}
		/* Compact both of the input strings */		 
		String                firstStringMod = firstString.replaceAll("\\\\n", "  ");
		ArrayList<HDLmToken>  firstTokens = HDLmString.getTokensNonWhite(firstStringMod);
		ArrayList<HDLmToken>  secondTokens = HDLmString.getTokensNonWhite(secondString);
		/* Get all of the token array lengths */
		int   firstTokensLen = firstTokens.size(); 
		int   secondTokensLen = secondTokens.size(); 
		int   minTokensLen = Math.min(firstTokensLen, secondTokensLen);
		/* Compare the token arrays, token by token */
		for (int i = 0; i < minTokensLen; i++) {
			/* Get a token from each token array */
			HDLmToken   firstToken = firstTokens.get(i);
			HDLmToken   secondToken = secondTokens.get(i); 
			boolean     tokensMatch = true;
			/* Compare the tokens */
			if (tokensMatch &&
			    firstToken.getType() != secondToken.getType()) { 
				tokensMatch = false;
			}
			if (tokensMatch &&
			    firstToken.getLength() != secondToken.getLength()) { 
				tokensMatch = false;
			}
			/* Get the values of each token */
			String  firstValue = firstToken.getValue(); 
			String  secondValue = secondToken.getValue(); 
			if (tokensMatch &&
			    !firstValue.equals(secondValue)) { 
				tokensMatch = false;
			}
			/* If the tokens don't match, report the error */
			if (tokensMatch)
				continue;
			/* Get the index of the first token that does 
			   not match */ 
			rvInt = firstToken.getIndex();
			break;
		}
		return rvInt;		
	}
	/* Convert a string that may (or may not) contain escaped 
	   characters sequences (blank is represented as %20) to 
	   a byte array. The byte array is returned to the caller.
	   The character data is assumed to only contain characters 
	   in the range of zero to 255. This assumption is checked 
	   below. This routine makes no assumptions about the character
	   set in use. All values in the range of zero to 255 are 
	   acceptable using either direct conversion or conversion
	   from hexadecimal. In many cases the output byte values
	   will be negative. This will happen if the character value
	   is greater than 127 (for example, a cedilla) or if a 
	   hexadecimal value greater than 127 is used (for example
	   %b8). */
	protected static byte[] decodeValue(String charData) {
		/* Check if the URL string passed by the caller is null */
		if (charData == null) {
			String  errorText = "Character string data passed to convertCharData is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */
		char  currentChar;
		char  firstChar;
		char  secondChar;
		int   byteCount = 0;
		int   byteIndex = 0;
		int   charDataLength = charData.length();
		/* Get the byte count by scanning the character data */
	  for (int i = 0; i < charDataLength; i++) {
	   	currentChar = charData.charAt(i);
	   	if (currentChar == '%') 
	 	  	i += 2;
	 	  byteCount++;		
		}
	  /* We can now allocate and utilize the byte array */
	  byte[]  byteArray = new byte[byteCount];
		/* Fill in the byte array using data from the input character
		   string */ 
	  for (int i = 0; i < charDataLength; i++) {
	 	  currentChar = charData.charAt(i);
	 	  /* Check if we have a percent sign at this point. The next 
	 	     two characters should be treated as the hexadecimal
	 	     value of one character in this case. */ 
	 	  if (currentChar == '%') {
	 	  	/* Check if we have room for the hexadecimal characters.
	 		     Throw an exception if we do not. */
	 		  if ((charDataLength - i) < 3) {
					String  errorText = String.format("Not enough room for hexadecimal characters at offset (%d)",  
	                                          i); 
	        throw new RuntimeException(errorText);   
	 		  } 
	 		  /* Get the first and second hexadecimal characters. Make sure that they 
	 		     are valid. */ 
	 		  firstChar = charData.charAt(i+1);
	 		  secondChar = charData.charAt(i+2);
	 		  /* Check the first character and make sure it is a valid hexadecimal
	 		     character */ 
	 		  if ((firstChar >= '0' && firstChar <= '9') ||
	 		      (firstChar >= 'a' && firstChar <= 'f') ||
	 		      (firstChar >= 'A' && firstChar <= 'F'))
	 			  ;
	 		  else {
					String  errorText = String.format("Character (%c) at offset (%d) is not a valid hexadecimal value", 
	                                          firstChar, i+1); 
	        throw new RuntimeException(errorText);    			
	 		  }
	 		  /* Check the second character and make sure it is a valid hexadecimal
		       character */ 
		 		if ((secondChar >= '0' && secondChar <= '9') ||
		 		    (secondChar >= 'a' && secondChar <= 'f') ||
		 		    (secondChar >= 'A' && secondChar <= 'F'))
		 			;
		 		else {
						String  errorText = String.format("Character (%c) at offset (%d) is not a valid hexadecimal value", 
		                                          secondChar, i+2); 
		        throw new RuntimeException(errorText);    			
		 		}    		
	 		  /* Convert the hexadecimal value to a byte */
	 	  	byteArray[byteIndex++] = (byte) Integer.parseInt(charData.substring(i+1, i+3), 16);	 		 
	 		  i += 2;
	 	  }
	 	  /* The current character is not a percent sign. We can just
	 	     use the current character to create a byte value. This 
	 	     should not be an error condition. Note that we do check
	 	     if the current character can fit in one byte. */ 
	 	  else {
	 		  /* Check if the current character is out-of-range. This can
	 		     certainly happen. Throw an exception if this error is 
	 		     detected. */ 
	 		  if (currentChar > 255) {
					String  errorText = String.format("Character (%c) at offset (%d) is out-of-range, higher than 255", 
							                              currentChar, i); 
					throw new RuntimeException(errorText);    			
	 		  }
	   	  byteArray[byteIndex++] = (byte) currentChar; 
	 	  }		
		}
	  return byteArray;
	}
	/* Display a set of string passed by the caller. The caller passes the array 
	   of strings and a prefix string. This routine writes each string out to the
	   log file with the prefix specified by the caller. */ 
	protected static void displayStrings(String prefixString,  
			                                 ArrayList<String> stringArray) {
		/* Check if the prefix string is null */
		if (prefixString == null) {
			String  errorText = "Prefix string passed to displayStrings is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the string array is null */
		if (stringArray == null) {
			String  errorText = "String array passed to displayStrings is null";
			throw new NullPointerException(errorText);
		}
		/* Loop over all of the strings */
		int  prefixStringLength = prefixString.length();
		for (String stringEntry : stringArray) {
			if (prefixStringLength > 0)
			  LOG.info(prefixString + " " + stringEntry);			
			else
				LOG.info(stringEntry);	
		}
	}
	/* Check if a string ends with a specific string. This function will
     return true if the haystack ends with the needle. This function
     will return false if the haystack does not end with the needle. */
	protected static boolean endsWith(String haystack, String needle) {
		/* Check if the values passed by the caller are null */
		if (haystack == null) {
			String  errorText = "Haystack value passed to endsWith is null";
			throw new NullPointerException(errorText);
		}
		if (needle == null) {
			String  errorText = "Needle value passed to endsWith is null";
			throw new NullPointerException(errorText);
		}
	  return haystack.endsWith(needle);
	}	
	/* Split an input string into an array of substrings using any set
	   whitespace characters */
	protected static ArrayList<String> explodeWhitespace(String str) {
		/* Check if the value passed by the caller is null */
		if (str == null) {
			String  errorText = "Input string value passed to explodeWhitespace is null";
			throw new NullPointerException(errorText);
		}
	  /* Split the input string into an array */
	  String partsArray[] = str.split("\\s+");
	  ArrayList<String> partsList = new ArrayList<String>(Arrays.asList(partsArray));
	  /* Get the size of the array of substrings */
	  int partsListLen = partsList.size();
	  /* Check if the last element of the array is a zero-length string */
	  if (partsListLen > 0) {
	    String lastPart = partsList.get(partsListLen-1);
	    if (lastPart.equals("")) {
	      partsList.remove(partsListLen-1);
	      partsListLen--;
	    }
	    /* Check if the first element of the array is a zero-length string */
	    if (partsListLen > 0) {
	      String firstPart = partsList.get(0); 
	      if (firstPart.equals(""))
	     	 partsList.remove(0);
	    }
	  }
	  return partsList;
	}
	/* Build a string from all of the entries in an array of strings */
	protected static String fromArray(ArrayList<String> strArray) {
		/* Check if the values passed by the caller are null */
		if (strArray == null) {
			String  errorText = "Input string array list value passed to fromArray is null";
			throw new NullPointerException(errorText);
		}
		/* Add each of the values to the output string */
		int            loopCounter = 0;
	  StringBuilder  outStrBuilder = new StringBuilder();
	  for (String entry : strArray) {
	    if (loopCounter > 0)
	      outStrBuilder.append(' ');
	    /* We need to check if the current entry is a null value 
	       or not. If the current entry is a null value, then we
	       need to use a special string for it. */
	    if (entry == null)
	    	outStrBuilder.append("null");
	    else
	      outStrBuilder.append(entry);
	    loopCounter++;
	 }
	 return outStrBuilder.toString();
	}
	/* Build a string from all of the entries in an array list of doubles.
	   Note that the array list must contain Doubles (boxed doubles), not
	   actual double values. */
	protected static String fromDoublesArray(ArrayList<Double> doubleArray) {
		/* Check if the values passed by the caller are null */
		if (doubleArray == null) {
			String  errorText = "Input Doubles array list value passed to fromDoublesArray is null";
			throw new NullPointerException(errorText);
		}
		/* Add each of the values to the output string */
		int            loopCounter = 0;
	  StringBuilder  outStrBuilder = new StringBuilder();
	  for (var entry : doubleArray) {
	    if (loopCounter > 0)
	      outStrBuilder.append(' ');
	    /* Check if the current array entry is null or not. If the
	       current array entry is null, we must use a literal value
	       here. Otherwise, we can convert the array entry to a string. */
	    if (entry == null)
	    	outStrBuilder.append("null");
	    else
	      outStrBuilder.append(entry.toString());
	    loopCounter++;
	 }
	 return outStrBuilder.toString();
	}
	/* Get the suffix, from what should be a file name. The 
     suffix is everything after the last period. If no 
     period is found a null value is returned to the 
     caller. */
	protected static String  getFileNameSuffix(String fileName) {
		/* Check if the values passed by the caller */
		if (fileName == null) {
			String  errorText = "File name passed to getFileNameSuffix method is null";
			throw new NullPointerException(errorText);
		}
	  /* Find the last occurrence (if any) of a period in the file name string */
		int lastIndex;
	  lastIndex = fileName.lastIndexOf('.');
	  if (lastIndex < 0)
	    return null;
	   return fileName.substring(lastIndex + 1);
	}
	/* Get the file type, from what should be the file name suffix.
	   The file name suffix is everything after the last period in
	   the file name. */ 
	protected static String  getFileNameType(String fileNameSuffix) {
		/* Check if the values passed by the caller */
		if (fileNameSuffix == null) {
			String  errorText = "File name suffix passed to getFileNameType method is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the file name suffix is known */
	  String  rv = null;
	  if (HDLmStringImageTypes.contains(fileNameSuffix))
	    rv = "image";
	  return rv;
	}
	/* This function returns one object to the caller. The object describes
	   the next token found in the input stream. Of course, if the input 
	   stream has been depleted (this is not considered to be an error 
	   condition), then the sentinel token will be returned to the caller.  
	
	   One object will be built, and returned to the caller, from the
	   string passed by the caller. The object describes the token in
	   some detail. The object gives the token position (starting from 
	   0, not 1), the token type,  and the token contents. Note that the
	   token position, takes into account the starting point for the 
	   input string search (for the next token).
	
	   This is a low-level tokenization routine. No attempt is made in this
	   routine to build a higher-level token. Other routines can do this or
	   use the token built by this routine to do so. This routine will return
	   an integer token for sequences of numeric digits. It will not return
	   a number token. This means that a decimal point will end up in a separate
	   operator token.
	
	   Note that an identifier can contain numeric digits in it. This is part
	   of the definition of an identifier in some languages. */
	protected static HDLmToken getNextToken(String inStr, 
			                                    int    curIndex,
			                                    String additionalOperators) {
		return getNextToken(inStr, curIndex, additionalOperators, '\'');
	}
	protected static HDLmToken getNextToken(String inStr, 
			                                    int    curIndex,
			                                    String additionalOperators,
			                                    char   quoteChar) {
		char  curCh;
	  int   inStrLen;
		/* Check if the input string value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to getNextToken is null";
			throw new NullPointerException(errorText);
		}
		inStrLen = inStr.length();
		/* Check if the quote character is one of the valid quote characters */
		if (quoteChar != '\'' && quoteChar != '"') {
			String  errorText = String.format("Quote character (%c) passed to getNextToken is invalid", 
					                             quoteChar);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
	  /* Process each character in the input string */
	  while (curIndex < inStrLen) {
	    /* Get the current character */
	    curCh = inStr.charAt(curIndex);
	    /* Check for an alpha character of some kind. The first character
	       of an identifier must be an alpha character. The other characters
	       can be alphanumeric. */
	    if (HDLmString.isAlphaHyphen(curCh)) {
	      HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.IDENTIFIER);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      /* Append all alphanumeric characters after the first one */
	      while (curIndex < inStrLen) {
	        /* Get the next character */
	     	  curCh = inStr.charAt(curIndex);
	        if (!HDLmString.isAlphaNumericHyphen(curCh))
	          break;
	        newTok.addCharacter(curCh);
	        curIndex++;
	      }
	      return newTok;
	    }
	    /* Check for a numeric character of some kind. Any number of numeric
	       characters are combined into one token. Note that the token type
	       is integer, not number. This routine does not return number tokens.
	       Instead, a decimal point after a series of digits will end up in a
	       separate token. */
	    if (HDLmString.isDigit(curCh)) {
	   	  HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.INTEGER);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      /* Append all numeric characters after the first one */
	      while (curIndex < inStrLen) {
	        /* Get the next character */
	     	  curCh = inStr.charAt(curIndex);
	        if (!HDLmString.isDigit(curCh))
	          break;
	        newTok.addCharacter(curCh);
	        curIndex++;
	      }
	      return newTok;
	    }
	    /* Check for an Operator character of some kind. A separate token
	       is created for each operator. */
	    if (HDLmString.isOperator(curCh) ||
	    		additionalOperators.indexOf(curCh) >= 0) {
	    	HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.OPERATOR);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      return newTok;
	    }
	    /* Check for the quote character. The quote character does not become
	       part of the output token. Pairs of quotes are combined into one
	       quote character that does become part of the output token. The
	       final quote terminates the quoted string. The first quote character
	       and the final quote character do not become part of the output token. */
	    if (curCh == quoteChar) {
	      boolean unmatchedQuotes = true;
	      HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.QUOTED); 
	      newTok.setIndex(curIndex);
	      newTok.setValue("");
	      curIndex++;
	      /* Append the contents of the quoted string not including the
	         leading and trailing quote characters */
	      while (curIndex < inStrLen) {
	        /* Get the next character */
	     	  curCh = inStr.charAt(curIndex);
	        /* Check for a quote character. Non-quote characters are just
	           added to the output token. */
	        if (curCh != quoteChar) {
	          newTok.addCharacter(curCh);
	          curIndex++;
	          continue;
	        }
	        /* At this point we need to check if the next character can
	           be obtained and if the next character is also a quote. If
	           both tests are true, then we have a pair of quotes that
	           can be combined into one quote character. */
	        if ((curIndex + 1) < inStrLen && inStr.charAt(curIndex + 1) == quoteChar) {
	          newTok.addCharacter(curCh);
	          curIndex += 2;
	          continue;
	        }
	        /* At this point we have found the trailing quote character and
	           we can terminate the quoted string. Note that the trailing
	           quote (like the leading quote) is not added to the token. */
	        curIndex++;
	        unmatchedQuotes = false;
	        break;
	      }
	      /* Report an error if the quoted string had unmatched quotes */
	      if (unmatchedQuotes) {	      	
	        String errorString = "(" + inStr + ")";
	        HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Unmatched Quotes", 15, errorString);	      	
	      }
	      return newTok;
	    }
	    /* Check for a white space character of some kind. Any number of white
	       space characters are combined into one token. Note that the function
	       used to check for white space characters only checks for traditional
	       white space characters at this time. */
	    if (HDLmString.isWhitespace(curCh)) {
	    	HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.SPACE);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      /* Append all white space characters after the first one */
	      while (curIndex < inStrLen) {
	        /* Get the next character */
	      	curCh = inStr.charAt(curIndex);
	        if (!HDLmString.isWhitespace(curCh))
	          break;
	        newTok.addCharacter(curCh);
	        curIndex++;
	      }
	      return newTok;
	    }
	    /* All other characters are treated as unknown characters. Because of the
	       rules of some languages, Hash (pound) and curly braces are all treated 
	       as unknown characters. A separate token is created for each unknown 
	       character. */
	    if (1 == 1) {
	    	HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.UNKNOWN);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      return newTok;
	    }
	  }
	  /* Add the sentinel entry at the end of the token object array */
	  HDLmToken newTok = new HDLmToken();
	  newTok.setType(HDLmTokenTypes.END);
	  newTok.setIndex(curIndex);
	  newTok.setValue("");
	  curIndex++;
	  return newTok;
	}
	/* This function returns a vector of objects to the caller. The vector
	   of objects has one entry for each token in the string passed to this
	   routine, plus a sentinel entry to mark the end of the vector. The vector
	   of objects will contain just the sentinel entry if the string passed to
	   this routine is empty. This is not considered to be an error condition.
 	
 	   One object will be built for each token in the string passed by the
 	   caller. The object describes the token in some detail. The object
 	   gives the token position (starting from 0, not 1), the token type,
 	   and the token contents.
	
	   This is a low-level tokenization routine. No attempt is made in this
	   routine to build higher-level tokens. Other routines can do this or
	   use the tokens built by this routine to do so. This routine will return
	   integer tokens for sequences of numeric digits. It will not return
	   number tokens. This means that a decimal point will end up in a separate
	   operator token.
 	
	   Note that an identifier can contain numeric digits in it. This is part
	   of the definition of an identifier in some languages. */
	protected static ArrayList<HDLmToken> getTokens(String inStr) {
		return getTokens(inStr, '\'');
	}
	protected static ArrayList<HDLmToken> getTokens(String inStr, char quoteChar) {
		ArrayList<HDLmToken> rv = new ArrayList<HDLmToken>();
		char curCh;
	  int  curIndex = 0;
	  int  inStrLen;
		/* Check if the input string value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to getTokens is null";
			throw new NullPointerException(errorText);
		}
		inStrLen = inStr.length();
		/* Check if the quote character is one of the valid quote characters */
		if (quoteChar != '\'' && quoteChar != '"') {
			String  errorText = String.format("Quote character (%c) passed to getTokens is invalid", quoteChar);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
	  /* Process each character in the input string */
	  while (curIndex < inStrLen) {
	    /* Get the current character */
	    curCh = inStr.charAt(curIndex);
	    /* Check for an alpha character of some kind. The first character
	       of an identifier must be an alpha character. The other characters
	       can be alphanumeric. */
	    if (HDLmString.isAlphaHyphen(curCh)) {
	      HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.IDENTIFIER);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      /* Append all alphanumeric characters after the first one */
	      while (curIndex < inStrLen) {
	        /* Get the next character */
	      	curCh = inStr.charAt(curIndex);
	        if (!HDLmString.isAlphaNumericHyphen(curCh))
	          break;
	        newTok.addCharacter(curCh);
	        curIndex++;
	      }
	      rv.add(newTok);
	      continue;
	    }
	    /* Check for a numeric character of some kind. Any number of numeric
	       characters are combined into one token. Note that the token type
	       is integer, not number. This routine does not return number tokens.
	       Instead, a decimal point after a series of digits will end up in a
	       separate token. */
	    if (HDLmString.isDigit(curCh)) {
	    	HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.INTEGER);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      /* Append all numeric characters after the first one */
	      while (curIndex < inStrLen) {
	        /* Get the next character */
	      	curCh = inStr.charAt(curIndex);
	        if (!HDLmString.isDigit(curCh))
	          break;
	        newTok.addCharacter(curCh);
	        curIndex++;
	      }
	      rv.add(newTok);
	      continue;
	    }
	    /* Check for an Operator character of some kind. A separate token
	       is created for each operator. */
	    if (HDLmString.isOperator(curCh)) {
	    	HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.OPERATOR);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      rv.add(newTok);
	      continue;
	    }
	    /* Check for the quote character. The quote character does not become
	       part of the output token. Pairs of quotes are combined into one
	       quote character that does become part of the output token. The
	       final quote terminates the quoted string. The first quote character
	       and the final quote character do not become part of the output token. */
	    if (curCh == quoteChar) {
	      boolean unmatchedQuotes = true;
	      HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.QUOTED); 
	      newTok.setIndex(curIndex);
	      newTok.setValue("");
	      curIndex++;
	      /* Append the contents of the quoted string not including the
	         leading and trailing quote characters */
	      while (curIndex < inStrLen) {
	        /* Get the next character */
	      	curCh = inStr.charAt(curIndex);
	        /* Check for a quote character. Non-quote characters are just
	           added to the output token. */
	        if (curCh != quoteChar) {
	          newTok.addCharacter(curCh);
	          curIndex++;
	          continue;
	        }
	        /* At this point we need to check if the next character can
	           be obtained and if the next character is also a quote. If
	           both tests are true, then we have a pair of quotes that
	           can be combined into one quote character. */
	        if ((curIndex + 1) < inStrLen && inStr.charAt(curIndex + 1) == quoteChar) {
	          newTok.addCharacter(curCh);
	          curIndex += 2;
	          continue;
	        }
	        /* At this point we have found the trailing quote character and
	           we can terminate the quoted string. Note that the trailing
	           quote (like the leading quote) is not added to the token. */
	        curIndex++;
	        unmatchedQuotes = false;
	        break;
	      }
	      /* Report an error if the quoted string had unmatched quotes */
	      if (unmatchedQuotes) {	      	
	        String errorString = "(" + inStr + ")";
	        HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Unmatched Quotes", 15, errorString);	      	
	      }
	      rv.add(newTok);
	      continue;
	    }
	    /* Check for a white space character of some kind. Any number of white
	       space characters are combined into one token. Note that the function
	       used to check for white space characters only checks for traditional
	       white space characters at this time. */
	    if (HDLmString.isWhitespace(curCh)) {
	    	HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.SPACE);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      /* Append all white space characters after the first one */
	      while (curIndex < inStrLen) {
	        /* Get the next character */
	      	curCh = inStr.charAt(curIndex);
	        if (!HDLmString.isWhitespace(curCh))
	          break;
	        newTok.addCharacter(curCh);
	        curIndex++;
	      }
	      rv.add(newTok);
	      continue;
	    }
	    /* All other characters are treated as unknown characters. Because of the
	       rules of some languages, Hash (pound) and curly braces are all treated 
	       as unknown characters. A separate token is created for each unknown 
	       character. */
	    if (1 == 1) {
	    	HDLmToken newTok = new HDLmToken();
	      newTok.setType(HDLmTokenTypes.UNKNOWN);
	      newTok.setIndex(curIndex);
	      newTok.setValue(Character.toString(curCh));
	      curIndex++;
	      rv.add(newTok);
	      continue;
	    }
	  }
	  /* Add the sentinel entry at the end of the token object array */
	  HDLmToken newTok = new HDLmToken();
	  newTok.setType(HDLmTokenTypes.END);
	  newTok.setIndex(curIndex);
	  newTok.setValue("");
	  curIndex++;
	  rv.add(newTok);
	  return rv;
	}
	/* This function returns a vector of objects to the caller. The vector
	   of objects has one entry for each non-white token in the string passed
	   to this routine, plus a sentinal entry to mark the end of the vector.
	   The vector of objects will contain just the sentinel entry if the string
	   passed to this routine is emtpy. This is not considered to be an error
	   condition.
 	
 	   One object will be built for each token in the string passed by the
 	   caller. The object describes the token in some detail. The object
 	   gives the token position (starting from 0, not 1), the token type,
	   and the token contents. */
	protected static ArrayList<HDLmToken> getTokensNonWhite(String inStr) {
		return getTokensNonWhite(inStr, '\'');
	}
 	protected static ArrayList<HDLmToken> getTokensNonWhite(String inStr, char quoteChar) {
 		ArrayList<HDLmToken> rv = new ArrayList<HDLmToken>();
		/* Check if the input string value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to getTokensNonWhite is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the quote character is one of the valid quote characters */
		if (quoteChar != '\'' && quoteChar != '"') {
			String  errorText = String.format("Quote character (%c) passed to getTokensNonWhite is invalid", quoteChar);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
 	  ArrayList<HDLmToken>  tokenVec = HDLmString.getTokens(inStr, quoteChar);
	  int tokenLen = tokenVec.size();
	  for (int i = 0; i < tokenLen; i++)
	    if (tokenVec.get(i).getType() != HDLmTokenTypes.SPACE)
	      rv.add(tokenVec.get(i));
	  return rv;
	}
 	/* This routine will return (as a string) all of the trailing
 	   numeric digits of the string that is passed to it. The input
 	   string can not be a null value. However, it can be a zero
 	   length string. The returned string will never be a null
 	   value. However, it may be a zero length string. */ 
 	protected static String  getTrailingDigits(final String inStr) {
		/* Check if the input string value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to getTrailingDigits is null";
			throw new NullPointerException(errorText);
		}
		/* Get the length of the input string */
		int   i;
		int   inStrLen = inStr.length();
		for (i = inStrLen - 1; i >= 0; i--) {
			char  curChar = inStr.charAt(i);
			if (curChar < '0' || curChar > '9') 
				break;			
		}
 		/* Return the final numeric string to the caller */
		return inStr.substring(i+1); 		
 	}
	/* The function below returns a boolean showing if a character
	   is a valid alpha character or not. Note that in keeping with
	   the rules of some languages, underscore is considered to be 
	   an alpha character. */
	protected static boolean isAlphaHyphen(char inChar) {
	  return (inChar >= 'a' && inChar <= 'z') ||
	         (inChar >= 'A' && inChar <= 'Z') ||
	         (inChar == '_');
	}
	/* The function below returns a boolean showing if a character
	   is a valid alphanumeric character or not. Note that in keeping
	   with the rules of some languages, underscore is considered to 
	   be an alphanumeric character. */
	protected static boolean isAlphaNumericHyphen(char inChar) {
	  return (inChar >= '0' && inChar <= '9') ||
	         (inChar == '_')                  ||
	         (inChar >= 'a' && inChar <= 'z') ||
	         (inChar >= 'A' && inChar <= 'Z');
	}
	/* The function below returns a boolean showing if a character
	   is a valid numeric digit or not. Note that the code below does
	   not consider non-traditional digit characters to be digits. 
	   This could be a problem in the future. */
	protected static boolean isDigit(char inChar) {
	  return (inChar >= '0' && inChar <= '9');
	}
	/* The function below returns a boolean showing if a character
	   is a valid operator character or not. Note that comma is
	   included in the list of operator characters below because 
	   comma is a valid operator in some languages (but only in 
	   for loops). Period is the standard string concatentation 
	   operator in some languages. Brackets (left and right) are
	   also included in the list of operator characters because
	   they are used as accessors. Curly braces are not included 
	   because curly braces are not valid operators in some languages.
	   For the same reason the hash (pound) character and the dollar 
	   sign are not included either.
	   
	   The semicolon character is an operator in many languages. 
	   However, it is not in the list or operators below. It must
	   be checked for separately.
	
	   The standard and, or, and xor operators not detected by the code
	   below. Instead they are handled as strings and other code must be
	   used (if need be) to recognize them as operators. */
	protected static boolean isOperator(char inChar) {
  	String haystack = "+-*/%=!><&|~^?:,().[]";
	  int pos = haystack.indexOf(inChar);
	  return (pos >= 0);
	}
	/* The function below returns a boolean showing if a character
	   is a valid white space character or not. The caller actually
	   passes a string. However, the length of the string must be
	   exactly one. The list below only includes the traditional
		 white space characters. Many other (Unicode) non-traditional
		 white space characters exist. They may need to be added in
		 the future. */
	protected static boolean isWhitespace(char inChar) {
		/* The Unicode escape sequence below is actually the vertical
		   tab character */
	  String haystack = " \f\n\r\t\u000b";
	  int pos = haystack.indexOf(inChar);
	  return (pos >= 0);
	}
  /* Convert the first character of a string to lowercase and return the
     string to the caller. The original string is not modified. */ 
  protected static String lcFirst(String value) {
    if (value == null) {
      String  errorText = "Input string passed to lcFirst is null";
      throw new NullPointerException(errorText);
    }
    if (value.length() == 0)
      return value;
    return value.substring(0, 1).toLowerCase() + value.substring(1);
  }
	/* This routine unconditionally removes all occurrences of a character
	   from a string. A few rules. The input string must not be null. This  
	   is checked for, and not allowed. The input string can be zero-length. 
	   This is not considered to be an error condition. If the character is
	   not found in the input string, no error is reported. This is not 
	   considered to be an error. */ 
	protected static String removeAllCharacter(final String inStr, final char inChar) { 
		/* Check if the value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to removeAllCharacter is null";
			throw new NullPointerException(errorText);
		}
	 /* Return the input string with all occurrences of the specified character 
	    removed */
		return StringUtils.remove(inStr, inChar); 
	}
	/* This routine unconditionally removes all occurrences of a string
	   from another string. A few rules. Neither input string can be 
	   null. This is checked for, and not allowed. Either string can be
	   zero-length. This is not considered to be an error condition. 
	   If the needle string is not found, in the haystack, no error is
	   reported. This is not considered to be an error. */ 
	protected static String removeAllSubstring(final String haystack, final String needle ) { 
		/* Check if the values passed by the caller are null */
		if (haystack == null) {
			String  errorText = "Haystack string value passed to removeAllSubstring is null";
			throw new NullPointerException(errorText);
		}
		if (needle == null) {
			String  errorText = "Needle string value passed to removeAllSubstring is null";
			throw new NullPointerException(errorText);
		}
    /* Return the input haystack string with all needle strings removed */
		return StringUtils.remove(haystack, needle); 
	}
	/* This routine unconditionally removes all occurrences of a character
     from a string. A few rules. The input string must not be null. This  
     is checked for, and not allowed. The input string can be zero-length. 
     This is not considered to be an error condition. If the character is
     not found in the input string, no error is reported. This is not 
     considered to be an error. */ 
	protected static String removeChars(String inStr, final String inChars, final HDLmStringTypes direction) { 
		/* Check if the value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to removeChars is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input characters string is null */
		if (inChars == null) {
			String  errorText = "Input characters value passed to removeChars is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the string direction enum value is supported */
		if (direction != HDLmStringTypes.LEADING &&
				direction != HDLmStringTypes.TRAILING) {
			String   errorFormat = "String direction enum value (%s) passed to removeChars is invalid";
			String   errorText = String.format(errorFormat, direction.toString());
			HDLmAssertAction(false, errorText);			
		}
  	/* Handle leading and trailing characters separately. This is done
  	   for performance reasons. */
		int   inStrLen = inStr.length();
		/* Check if we need to remove a set of leading characters */
		if (direction == HDLmStringTypes.LEADING) {
			/* We need to loop until all of the leading characters 
			   we need to remove are gone */
			while(true) {
				/* Check if we have any characters left to check */
				if (inStrLen <= 0)
					break;
				/* Get the first character and check if it need to 
				   be removed */
				char  firstChar = inStr.charAt(0);
				if (inChars.indexOf(firstChar) >= 0) {
					inStr = inStr.substring(1, inStrLen);			
					inStrLen--;
				}
				else 
					break;				
			}
		}
		/* Check if we need to remove a set of trailing characters */
		if (direction == HDLmStringTypes.TRAILING) {
			/* We need to loop until all of the trailing characters 
			   we need to remove are gone */
			while(true) {
				/* Check if we have any characters left to check */
				if (inStrLen <= 0)
					break;
				/* Get the last character and check if it need to 
				   be removed */
				char  lastChar = inStr.charAt(inStrLen-1);
				if (inChars.indexOf(lastChar) >= 0) {
					inStrLen--;
					inStr = inStr.substring(0, inStrLen);							
				}
				else 
					break;				
			}
		}
		/* Return the final string to the caller */	 
		return inStr;
	}
  /* Remove a file number tail (if any) from a string. Some strings end
	   with a file number (of the form (nnn)) that must be removed for 
	   duplicate checking. This routine checks if the input string has 
	   a file number tail and removes it. In other words, 'Abcd' is 
	   returned as 'Abcd' and 'Abcd(3)' is also returned as 'Abcd'. 
	   Note that this routine does not change the case of any part of
	   the input or output strings. */
	protected static String removeFileNumberTail(String inStr) {
		/* Check if the input string value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to removeFileNumberTail is null";
			throw new NullPointerException(errorText);
		}
    /* We can now search for a trailing file number */
		boolean   reMatch;
	  String    reStr = "\\(\\d+\\)$";
	  Pattern   rePattern = Pattern.compile(reStr);
	  Matcher   reMatcher = rePattern.matcher(inStr);
	  /* Try to find the pattern in the string */
	  reMatch = reMatcher.find();
	  if (!reMatch)
	  	return inStr;
	  int   ix = reMatcher.start(); 
	  if (ix < 0)
	    return inStr;
	  else
	    return inStr.substring(0, ix);
	}
	/* This routine conditionally removes a prefix from an input string. 
     If the input string actually begins with the prefix string, the
     input string without the prefix string is returned to the caller.
     However, if the input string does not begin with the prefix 
     string, the entire input string is returned to the caller. */
	protected static String removePrefix(String prefixStr, String inputStr) {
		/* Check if the values passed by the caller are null */
		if (prefixStr == null) {
			String  errorText = "Prefix string value passed to removePrefix is null";
			throw new NullPointerException(errorText);
		}
		if (inputStr == null) {
			String  errorText = "Input string value passed to removePrefix is null";
			throw new NullPointerException(errorText);
		} 
		/* Get the lengths of the prefix and input strings */
		int   inputLength = inputStr.length();
		int   prefixLength = prefixStr.length();
		/* Check if the prefix string is too long for the input string */
		if (prefixLength > inputLength)
		  return inputStr;
		/* Check if the input string starts the input string */
		if (inputStr.startsWith(prefixStr) == false)
			return inputStr;
		return inputStr.substring(prefixLength);
	}
	/* This routine tries to remove a substring from a string. The input
	   string is not modified in place. The modified string is built and
	   returned to the caller. This routine follows the standard rules
	   for using index values. In other words, the ending index is the 
	   first character that should not be removed. For example, if the
	   input string was "abcdef" (with no actual quotes) and the index 
	   values were two and four, then the final returned string would 
	   be "abef" (with no actual quotes). */ 
	protected static String removeSubstring(String inputStr, 
			                                    int startIndex, 
			                                    int endIndex) {
		/* Check if the input string passed by the caller is null */
		if (inputStr == null) {
			String  errorText = "Input string value passed to removeSubstring is null";
			throw new NullPointerException(errorText);
		}			
		/* Make sure that the starting index is greater than or equal to zero */ 
		if (startIndex < 0) {
			String  errorText = String.format("Starting index (%d) passed to removeSubstring is less than zero", 
					                             startIndex);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Make sure that the ending index is greater than or equal to zero */ 
		if (endIndex < 0) {
			String  errorText = String.format("Ending index (%d) passed to removeSubstring is less than zero", 
					                             endIndex);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Make sure that the ending index is greater than or equal to the starting index */ 
		if (endIndex < startIndex) {
			String  errorText = String.format("Ending index (%d) is less than starting index (%d) passed to removeSubstring", 
					                             endIndex, startIndex);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Get the actual length of the input string. This value is needed for the 
		   checks below. */ 
		int   inputStrLength = inputStr.length();
		/* Make sure the starting index is less than or equal to the input string length */
		if (startIndex > inputStrLength) {
			String  errorText = String.format("Starting index (%d) passed to removeSubstring is greater than input string length", 
					                             startIndex);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Make sure the ending index is less than or equal to the input string length */
		if (endIndex > inputStrLength) {
			String  errorText = String.format("Ending index (%d) passed to removeSubstring is greater than input string length", 
					                             endIndex);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if we should just remove the entire string */
		if (startIndex == 0 && endIndex == inputStrLength)
			return "";
		/* Check if we should remove zero characters */
		else if (startIndex == endIndex)
			return inputStr;
		/* Check if the string to be removed starts at the beginning of the 
		   input string */		
		else if (startIndex == 0) 
			return inputStr.substring(endIndex);
		/* Check if the string to be removed ends at the end of the input
		   string */ 
		else if (endIndex == inputStrLength)
			return inputStr.substring(0, startIndex);
		/* Build the returned string from two parts */
		else 
			return inputStr.substring(0, startIndex) + inputStr.substring(endIndex);
	}	
  /* Repeat a string and a separator some number of times. The caller
     provides the string, the separator, and the count value. The
     separator is added after each copy of the input string, except
     for the last. */
	public static String repeatString(String inStr, String inSep, int repeatCount) {
		/* Check if the input string value passed by the caller is null */
		if (inStr == null) {
			String  errorText = "Input string value passed to repeatString is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input separator value passed by the caller is null */
		if (inSep == null) {
			String  errorText = "Input separator value passed to repeatString is null";
			throw new NullPointerException(errorText);
		}
		/* Make sure that the repeat count is zero or positive */
		if (repeatCount < 0) {
			String  errorText = String.format("Repeat count (%d) passed to repeatString is less than zero", repeatCount);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Create a string builder for building the output string */
		StringBuilder  outStrBuilder = new StringBuilder();
	  int   repeatCountM1 = repeatCount - 1;
	  for (int i = 0; i < repeatCount; i++) {
	  	outStrBuilder.append(inStr);
	    if (i < repeatCountM1)
	    	outStrBuilder.append(inSep);
	  }
	  return outStrBuilder.toString();
	}
	/* Split a string into parts and log each of the parts. It is 
	   assumed that the string is too long to simply be displayed.
	   However, the string can be split into parts and each of the
	   parts can be logged. */ 
	protected static void splitDisplayString(final String inputStr, final Character splitChar) {
		/* Declare and define a few variables for use below */
		String    splitRegex = "";
		String[]  inputStrParts;
		/* Build the regex needed to split the string */
		if (splitChar == '\n')
			splitRegex = "\n";
		else 
			splitRegex += splitChar;
		/* Split the string passed by the caller into parts */
		inputStrParts = inputStr.split(splitRegex); 
    for (String part: inputStrParts) {
      LOG.info(part);
    }		
	}
	/* Check if a string starts with a specific string. This function will
	   return true if the haystack starts with the needle. This function
	   will return false if the haystack does not start with the needle. */
	protected static boolean startsWith(String haystack, String needle) {
		/* Check if the values passed by the caller are null */
		if (haystack == null) {
			String  errorText = "Haystack value passed to startsWith is null";
			throw new NullPointerException(errorText);
		}
		if (needle == null) {
			String  errorText = "Needle value passed to startsWith is null";
			throw new NullPointerException(errorText);
		}
	  return haystack.startsWith(needle);
	}
	/* Replace the first and only the first occurrence of a neeedle in
	   a haystack. Note that the if the needle is not found, then the
	   unchanged haystack string is returned to the caller. */
	protected static String strReplaceOnce(String needle, String replacement, String haystack) {
		/* Check if the values passed by the caller are null */
		if (needle == null) {
			String  errorText = "Needle value passed to strReplaceOnce is null";
			throw new NullPointerException(errorText);
		}
		if (replacement == null) {
			String  errorText = "Replacement value passed to strReplaceOnce is null";
			throw new NullPointerException(errorText);
		}
		if (haystack == null) {
			String  errorText = "Haystack value passed to strReplaceOnce is null";
			throw new NullPointerException(errorText);
		}
	  haystack = StringUtils.replaceOnce(haystack, needle, replacement); 
	  return haystack;
	}
	/* Truncate a string passed by the caller. The caller provides the
	   string and the maximum length value. If the passed string length
	   is less than or equal to maximum length value, the passed string
	   is returned to the caller unchanged. If the passed string is
	   longer than the maximum length value, a substring of the passed
	   string is returned to the caller. */
	protected static String strTruncate(String str, int maxLength) {
		/* Check if the value passed by the caller is null */
		if (str == null) {
			String  errorText = "Input string value passed to strTruncate is null";
			throw new NullPointerException(errorText);
		}
		/* Make sure the maximum length is zero or positive */
		if (maxLength < 0) {
			String  errorText = String.format("Maximum length (%d) passed to strTruncate is less than zero", maxLength);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
	  int strLen = str.length();
	  if (strLen <= maxLength)
	    return str;
	  return str.substring(0, maxLength);
	}
  /* Convert the first character of a string to uppercase and return the
     string to the caller. The original string is not modified. */ 
  protected static String ucFirst(String value) {
	  if (value == null) {
	    String  errorText = "Input string passed to ucFirst is null";
	    throw new NullPointerException(errorText);
	  }
	  /* Get the length of the input value */
	  int   valueLen = value.length();
	  /* Check if the string is zero-length */
    if (valueLen == 0)
      return value;
    return value.substring(0, 1).toUpperCase() + value.substring(1);
  }
  /* Convert the first character all of words in a string to uppercase
     and return the possibly modified sentence to the caller */
	protected static String ucFirstSentence(String inputValue) {
		/* Check if the input value passed by the caller is null */
		if (inputValue == null) {
			String  errorText = "Input value string passed to ucFirstSentence is null";
			throw new NullPointerException(errorText);
		}
	  String      outputValue = "";
	  String      valueString;
	  HDLmToken   valueToken;
	  ArrayList<HDLmToken>  valueTokens = HDLmString.getTokens(inputValue);
	  int   tokenCount = valueTokens.size() - 1;
	  for (int i = 0; i < tokenCount; i++) {
	    /* Get some information from the current token */
	    valueToken = valueTokens.get(i);
	    valueString = valueToken.getValue();
	    /* If the current token is an identifier, then we must
	       convert the first character to uppercase */
	    if (valueToken.getType() == HDLmTokenTypes.IDENTIFIER)
	      valueString = HDLmString.ucFirst(valueString);
	    /* Add the current (possibily modified) value to the output 
	       string */
	    outputValue += valueString;
	  }
	  return outputValue;
	}
}
package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmMatch short summary.
 *
 * HDLmMatch description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmMatch {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmMatch.class);  
	/* An instance of this class is created to handle matching. In
	   some cases, matching is done using simple string comparisons.
	   However, in other cases, matching is done using the regex
	   mechanism. Regex patterns are created by compiling regex
	   strings. Regex strings can either be entered by a user or
	   created from a glob string or a like (SQL Like) string. */ 
	private String              value = null;
	private HDLmMatchTypes      type = HDLmMatchTypes.NONE;
	/* The transient qualifier below was added to make Jetty 11 and
	   Java 17 work. Before we go various errors from the gson code. */ 
	private transient Pattern   pattern = null;
	/* This is one of the constructors for the match class. This 
	   constructor takes a match string as input. The match string
	   may be a full regex string or a glob string of a like (SQL 
	   LIKE) string. This constructor checks for each case and 
	   builds an appropriate object. 
	   
	   Note that the caller must make sure that the match string is
	   valid. This routine assumes that the match string is valid. */ 
  protected HDLmMatch(String matchString) { 
		if (matchString == null) {  
		  String  errorText = "Match string passed to HDLmMatch constructor is null";
		  throw new NullPointerException(errorText);
		}  
  	/* Check if the match string is actually a regex */	 
		int matchStringLen = matchString.length();
		/* Check if we really have a regex at this point. The rules for  
		   a regex are reasonably strict. The first and second characters
		   must be a forward slash. The last character must be forward slash. 
		   Note that the first and second characters and the last character 
		   are not actually used for pattern matching. The actual regex is 
		   in the middle. */ 
		if (matchStringLen >= 3) {
			if (matchString.charAt(0) == '/' &&
					matchString.charAt(1) == '/' &&
					matchString.charAt(matchStringLen - 1) == '/') {
				this.value = matchString;
				this.type = HDLmMatchTypes.REGEX;
				this.pattern = Pattern.compile(matchString.substring(2, matchStringLen-1));
			}
		}
		/* If the pattern is not actually a regex, it still might be a glob
		   or a like. Globs and likes are handled by converting them to 
		   regexes. */
		if (this.type != HDLmMatchTypes.REGEX) {
	    /* Note that some match strings are not really regexes. However, we convert
         them to regexes anyway. This is how globs are implemented. If we find
         a non-regex string that contains glob characters, we convert it to a
         regex. Of course, we set the regex flag as well. The same approach is
         used for lies (SQL LIKEs) as well. */
      if (HDLmMatch.globCheck(matchString)) {
       	String  regexString = HDLmMatch.globToRegex(matchString);
       	this.value = "//" + regexString + "/";
       	this.type = HDLmMatchTypes.GLOB;
     	  this.pattern = Pattern.compile(regexString);
      }
      else {
	      if (HDLmMatch.likeCheck(matchString)) {
	      	String  regexString = HDLmMatch.likeToRegex(matchString);
	      	this.value = "//" + regexString + "/";
	      	this.type = HDLmMatchTypes.LIKE;
	      	this.pattern = Pattern.compile(regexString);
	      }				
	      /* If we don't have a real regex or a glob or a like, then we
	         have a simple string comparison. Just save a reference to
	         the original match string. */ 
	      else
	      	this.value = matchString;
      }
		}  	
  }
  /* The next routine returns (as a List) all of the matches of 
     a regex. The callers must pass the test string and the pattern.
     The pattern must be from a regex. The returned List will be
     empty if no matches are found. Otherwise, the List will have
     one or more string entries. */
  @SuppressWarnings("unused")
	protected static List<String>  allMatches(String testString, Pattern testPattern) {
  	/* Check a few passed values */
		if (testString == null) {
		  String  errorText = "Test string passed to allMatches is null";
		  throw new NullPointerException(errorText);
		}
		if (testPattern == null) {
		  String  errorText = "Test pattern passed to allMatches is null";
		  throw new NullPointerException(errorText);
		}
		/* Allocate the returned list of matches */
  	List<String>  allMatches = new ArrayList<String>();
  	/* Check if the JSON element is a null value */
		if (allMatches == null) {
			HDLmAssertAction(false, "Match array not allocated");
		}
		/* Run the pattern against the test string */
		Matcher testMatcher = testPattern.matcher(testString);
		while (testMatcher.find()) {
		  allMatches.add(testMatcher.group());
		}
  	return allMatches;
  }
  /* The next routine checks if the test string passed by the caller
     is a valid match string or not. A valid match string can be a 
     proper regex string, a proper glob string, a proper like (SQL
     LIKE) string, or just a simple string. This routine will return
     an error message, if an invalid match string is detected. This
     routine will return a null value if no errors are detected. This
     routine should not raise an exception if an invalid match string
     is passed to it. */  
	protected static String check(String testString) {
		if (testString == null) {
		  String  errorText = "Test string passed to check is null";
		  throw new NullPointerException(errorText);
		}
		HDLmMatchTypes  testType = HDLmMatchTypes.NONE;
		Pattern         pattern = null;
		String          regexString = null;
		String          rv = null;
  	/* Check if the match string is actually a regex */	 
		int testStringLen = testString.length();
		/* Check if we really have a regex at this point. The rules for  
		   a regex are reasonably strict. The first and second characters
		   must be a forward slash. The last character must be forward slash. 
		   Note that the first and second characters and the last character 
		   are not actually used for pattern matching. The actual regex is 
		   in the middle. */ 
		if (testStringLen >= 3) {
			if (testString.charAt(0) == '/' &&
					testString.charAt(1) == '/' &&
					testString.charAt(testStringLen - 1) == '/') {
				testType = HDLmMatchTypes.REGEX;
				try {
				  pattern = Pattern.compile(testString.substring(2, testStringLen-1));
				}
				catch (PatternSyntaxException e) {
					if (testString != null)
					  LOG.info("Test String - " + testString);
					LOG.info("PatternSyntaxException while executing check");
			 		LOG.info(e.getMessage(), e);
					HDLmEvent.eventOccurred("PatternSyntaxException");	
					rv = 	e.getDescription();					
				}
				catch (Exception e) {
					if (testString != null)
					  LOG.info("Test String - " + testString);
					LOG.info("Exception while executing check");
			 		LOG.info(e.getMessage(), e);
					HDLmEvent.eventOccurred("Exception");	
					rv = 	e.getMessage();					
				}
			}
		}
		/* If the pattern is not actually a regex, it still might be a glob
		   or a like. Globs and likes are handled by converting them to 
		   regexes. */
		if (testType != HDLmMatchTypes.REGEX) {
	    /* Note that some match strings are not really regexes. However, we convert
         them to regexes anyway. This is how globs are implemented. If we find
         a non-regex string that contains glob characters, we convert it to a
         regex. Of course, we set the regex flag as well. The same approach is
         used for lies (SQL LIKEs) as well. */
      if (HDLmMatch.globCheck(testString)) {
      	testType = HDLmMatchTypes.GLOB;
      	try {
       	  regexString = HDLmMatch.globToRegex(testString); 
      	}
      	catch (AssertionError e) {
      		if (testString != null)
					  LOG.info("Test String - " + testString);
					LOG.info("AssertionError while executing check");
			 		LOG.info(e.getMessage(), e);
      		HDLmEvent.eventOccurred("AssertionError");	
      		rv = e.getMessage();
      	}
      	catch (Exception e) {
      		if (testString != null)
					  LOG.info("Test String - " + testString);
					LOG.info("Exception while executing check");
			 		LOG.info(e.getMessage(), e);
      		HDLmEvent.eventOccurred("Exception");	
      		rv = e.getMessage();
      	}
      	/* If we were able to build the regex string without any errors,
      	   then we should be able to compile the regex string as well. */       
      	if (rv == null) {
	       	try {
	     	    pattern = Pattern.compile(regexString);
	       	}
					catch (PatternSyntaxException e) {
						if (testString != null)
						  LOG.info("Test String - " + testString);
						LOG.info("PatternSyntaxException while executing check");
				 		LOG.info(e.getMessage(), e);
						HDLmEvent.eventOccurred("PatternSyntaxException");	
						rv = 	e.getDescription();					
					}
					catch (Exception e) {
						if (testString != null)
						  LOG.info("Test String - " + testString);
						LOG.info("Exception while executing check");
				 		LOG.info(e.getMessage(), e);
						HDLmEvent.eventOccurred("Exception");	
						rv = 	e.getMessage();					
					}
      	}
      }
      /* Since the current string is not a regex string or a glob string,
         then we must check for a like (SQL LIKE) string. */ 
      else {
	      if (HDLmMatch.likeCheck(testString)) {
	      	testType = HDLmMatchTypes.LIKE;
	      	try {
	      	  regexString = HDLmMatch.likeToRegex(testString);
	      	}
	      	catch (AssertionError e) {
	      		if (testString != null)
						  LOG.info("Test String - " + testString);
						LOG.info("AssertionError while executing check");
				 		LOG.info(e.getMessage(), e);
	      		HDLmEvent.eventOccurred("AssertionError");	
	      		rv = e.getMessage();
	      	}
	      	catch (Exception e) {
	      		if (testString != null)
						  LOG.info("Test String - " + testString);
						LOG.info("Exception while executing check");
				 		LOG.info(e.getMessage(), e);
	      		HDLmEvent.eventOccurred("Exception");	
	      		rv = e.getMessage();
	      	}
	      	/* If we were able to build the regex string without any errors,
     	       then we should be able to compile the regex string as well. */       
        	if (rv == null) {
		      	try {
		      	  pattern = Pattern.compile(regexString);
		      	}
						catch (PatternSyntaxException e) {
							if (testString != null)
							  LOG.info("Test String - " + testString);
							LOG.info("PatternSyntaxException while executing check");
					 		LOG.info(e.getMessage(), e);
							HDLmEvent.eventOccurred("PatternSyntaxException");	
							rv = e.getDescription();					
						}
						catch (Exception e) {
							if (testString != null)
							  LOG.info("Test String - " + testString);
							LOG.info("Exception while executing check");
					 		LOG.info(e.getMessage(), e);
							HDLmEvent.eventOccurred("Exception");	
							rv = e.getMessage();	
						}
        	}
	      }				
	      /* If we don't have a real regex or a glob or a like, then we
	         have a simple string comparison. Make sure we don't have a 
	         zero-length string. Perhaps we should allow zero-length 
	         match strings. */ 
	      else {
	      	if (testStringLen == 0)
	      		rv = "Zero-length match string found";
	      }	
      }
		}
	  /* Return a value showing if the test string was valid or not */
	  return rv;		 
	}
	/* This routine checks if two strings match. The first string 
	   is the match string and the second string is the test string.
	   The match string can be a simple string or a string with glob
	   characters or a string with like characters. The test string 
	   is assumed to be a simple string with no glob or like characters.
	   
	   If the two strings are equal (allowing for globs or likes in
	   some cases), then true is returned to the caller. If the two
	   strings are not equal, then false is return to the caller. 
	   
	   The first string (the match string) may be compiled to a match
	   object in some cases. This will only be done if the first string
	   is fairly complex. */ 
	protected static boolean  checkStrings(final String matchString, 
			                                   final String testString) {
		/* Check the values passed by the caller */
		if (matchString == null) {
		  String  errorText = "Match string passed to checkStrings is null";
		  throw new NullPointerException(errorText);
		}
		if (testString == null) {
		  String  errorText = "Test string passed to checkStrings is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the match string is just an asterisk. An asterisk matches
		   all test strings. */
		if (matchString.equals("*"))
			return true;
		/* We can now build a match object from the match string. The match
		   object is used with the test string. */ 
		HDLmMatch   matchObj = new HDLmMatch(matchString);
		/* Check if the match object, matches the test string. Return the
		   results to the caller. */ 
		boolean   rv;
		rv = matchObj.match(testString);		  
		return rv;
	}
	/* This routine checks if two strings match. The first string 
	   is the match string and the second string is the test string.
	   The match string can be a simple string or a string with glob
	   characters or a string with like characters. The test string 
	   is assumed to be a simple string with no glob or like characters.
	   
	   If the two strings are equal (allowing for globs or likes in
	   some cases), then true is returned to the caller. If the two
	   strings are not equal, then false is return to the caller. 
	   
	   The first string (the match string) may be compiled to a match
	   object in some cases. This will only be done if the first string
	   is fairly complex. 
	   
	   This routine implements some special rules. If the match string 
	   is a asterisk, then it will always match. This will be true 
	   even if the test string is a null value. Note that the next
	   special rule is close to the reverse. If the test string is a 
	   null value, then it will never match. Note that special rules
	   are applied in order. */  
	protected static boolean  checkStringsSpecial(final String matchString, 
			                                          Map<String, HDLmMatch>  matchCache,
			                                          final String testString) {
		/* Check the values passed by the caller */
		if (matchString == null) {
		  String  errorText = "Match string passed to checkStringsSpecial is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the match cache passed by the caller is null */
		if (matchCache == null) {
		  String  errorText = "Match cache passed to checkStringsSpecial is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		boolean     rv;
		HDLmMatch   matchObj;
		/* Check if the match string is just an asterisk. An asterisk matches
		   all test strings including null values. */
		if (matchString.equals("*"))
			return true;
		/* Check if the test string is a null value. A null value will never
		   match anything. */
		if (testString == null)
			return false;		
		/* The match cache may already contain a match object for the current
		   match. We need to check for (and handle) this case. This is actually
		   very likely to be true. */
		matchObj = matchCache.get(matchString);	
		/* Since the match object was not in the cache, we must build 
		   the match object at this point. Of course, we save the match
		   object for future use as well. */ 
		if (matchObj == null) {
			/* We can now build a match object from the match string. The match
			   object is used with the test string. */ 
			matchObj = new HDLmMatch(matchString);
			matchCache.put(matchString, matchObj);
		}
		/* Check if the match object, matches the test string. Return the
		   results to the caller. */ 
		rv = matchObj.match(testString);		  
		return rv;
	}
	/* Check if a string contains any glob characters. This routine
		 will not change the input string. However, it will check for
		 glob characters. If any glob characters are found, this routine
		 will return true. If no glob characters are found, this routine
		 will return false. Note that this routine does check for left
		 square bracket, but not the right square bracket. It is assumed
		 that a user will always have a left square bracket with a right
		 square bracket.      
		  
		 Note that an escaped glob character is still considered to be a
		 glob character. This is required so that the input string will
		 still be considered to be a glob string and will be converted
		 to a regex. For example, the exclamation mark character must be
		 escaped to prevent it from being treated as glob class negation.
		 However, the escape should be removed because regex does not treat
		 the exclaamation mark character as a special character.
		
		 There is a major exception to the rules described above. In one very
		 important case, a left square bracket is not considered to be a glob
		 character. If the left square bracket is followed by a not sign, then
		 the left square bracket is really part of a like (SQL LIKE) path value, 
		 not a glob. The code below checks for this case. */ 
	protected static boolean globCheck(String matchString) {
		if (matchString == null) {
		  String  errorText = "Match string passed to globCheck is null";
		  throw new NullPointerException(errorText);
		}
	  boolean         endFlag = false;
	  boolean         escapeFlag = false;
	  boolean         rv = false;
	  Character       matchChar;
	  Character       testChar;
	  Integer         index = -1;
	  HDLmMatchChar   matchRv;
	  HDLmMatchChar   testRv;
	  /* What follows is a dummy loop used only to allow break to work */
	  while (true) {
	    /* Get the next character and terminate if we have reached the end of
	       the string */  
	    matchRv = HDLmMatchChar.nextCharGet(matchString, index); 
	    endFlag = matchRv.getEndFlag();
	    escapeFlag = matchRv.getEscapeFlag();
	    index = matchRv.getIndex();
	    matchChar = matchRv.getMatchChar();
	    if (endFlag)
	      break;
	    /* Check if it is a special glob character */
	    if (matchChar == '*' ||
	        matchChar == '?' ||
	        matchChar == '!') {
	      rv = true;
	      break;
	    }
	    /* Check if we have a left square bracket. Generally, a left square bracket
	       means that we have a glob. However, in one important case, this is not true.
	       If the left square bracket is followed by a not sign, then we actually have
	       a like (SQL LIKE). This is not a glob at all. */
	    if (matchChar == '[') {
	      /* Test the next character and check if it is a not sign. If we actually have
	         a not sign after a left square bracket, then this is probably a like (SQL 
	         LIKE), not a glob. */
	      testRv = HDLmMatchChar.nextCharGet(matchString, index);
		    endFlag = testRv.getEndFlag();
		    escapeFlag = testRv.getEscapeFlag();
		    testChar = testRv.getMatchChar();
	      /* The prior call may not have been able to get a character at all, if 
	         we are at the end of the string. We need to check for this case. */
	      if (endFlag == true)
	      	continue;
	      if (testChar != null &&
	          testChar == '^'  &&
	          escapeFlag == false)
	        continue;
	      rv = true;
	      break;
	    }
	  }
	  return rv;
	}
	/* Convert a glob string pattern to a regex string pattern.
	   This routine takes an input glob string pattern and
	   returns a regex string pattern. At least this routine
	   tries to do so. The rules for how glob is supposed to
	   work are actually quite complex. The generated regexes
		 does not follow all of the rules. A key point is that a
		 glob is not supposed to match a forward slash. However,
		 the generated regex will match a forward slash. This is
		 no longer true. The generated regex will not match a 
		 forward slash.
		 
		 The prior comment may not be true. The current code builds
		 a regex that does not match a forward slash.
		
		 The returned regex string will not have the leading and
		 trailing forward slash characters needed for JavaScript
		 and perhaps other systems. These characters will have to
		 be added if the regex is going to be used with JavaScript
		 and perhaps other systems. 		 
		 		 
		 Note that any character can be escaped (using a backslash). Any
		 escaped character represents itself. This rule includes escape
		 itself. If a backslash is preceded by another backslash, then 
		 the two characters represent a single literal backslash. One
		 consequence is that a single backslash can not be the last 
		 character of an input string. */
	protected static String globToRegex(String globString) {
		if (globString == null) {
		  String  errorText = "Glob string passed to globToRegex is null";
		  throw new NullPointerException(errorText);
		}	  
	  /* Declare and define a few local variables */
	  boolean         endFlag = false;
	  boolean         escapeFlag = false;
	  boolean         startFlag = false;
	  Character       globChar;
	  Character       priorChar;
	  HDLmMatchChar   globRv;
	  HDLmMatchChar   priorRv;
	  Integer         index = -1;
	  String          add;
	  StringBuilder   regexStr = new StringBuilder();
	  /* Start the output regex string by attaching it to the left
	     bound of the test string */
	  regexStr.append('^');
	  /* We assume that we are not in class mode at the outset. Class
	     mode (something like [abc] or [0-9]) is always started by a
	     left square bracket. Of course, at the beginning we have not
	     found a left square bracket yet. */
	  boolean   classMode = false;
	  /* What follows is a dummy loop used only to allow break to work */
	  while (true) {
	    /* Get the next character and terminate if we have reached the end of
	       the string */  
	    globRv = HDLmMatchChar.nextCharGet(globString, index);
	    endFlag = globRv.getEndFlag();
	    escapeFlag = globRv.getEscapeFlag();
	    index = globRv.getIndex();
	    globChar = globRv.getMatchChar();
	    if (endFlag)
	      break;
	    /* The rules for handling each character depend on the character and 
	       whether the escape flag was set */ 
	    switch (globChar) { 
	      /* Handle several different characters in the original glob
	         string. Each of these characters can be handled by simply
	         escaping the character. We don't want these characters to
	         be treated as special regex characters. */
	      case '.':
	      case '^':
	      case '$':
	      case '+':
	      case '(':
	      case ')':
	      case '{':
	      case '}':
	      case '\\':
	      case '/':
	      case '|':
	        add = "\\" + globChar;
	        break;
	      /* Left square bracket is a very special character. It may or may
	         not mark the start of a class (something like [abc] or [0-9]).
	         Of course, if the left square bracket is escaped, then it is
	         just a left square bracket, not the start of a class. */
	      case '[':
	        /* Check if the left square bracket was preceded by a backslash.
	           If this is true, then the left square bracket is not really a
	           glob character at all. Note that the left square bracket must
	           be escaped because regex will treat it as a special character. */
	        if (escapeFlag) {
	     	    add = "\\" + globChar;
	          break;
	        }
	        /* We need to make sure that a class is not already active. Report
	           an error if a class is already started. */
	        if (classMode) {
	          String  errorText = "Some type of glob class has already been started";
	          HDLmAssertAction(false, errorText);
	        }
	        /* At this point, we have a left square bracket that really is
	           the start of a class. The left square bracket does not need
	           to be escaped and in fact, must not be escaped. */
	        add = "" + globChar;
	        classMode = true;
	        break;
	      /* Right square bracket is a very special character. It may or may
	         not mark the end of a class (something like [abc] or [0-9]). Of
	         course, if the right square bracket is escaped, then it is just
	         a right square bracket, not the end of a class. */
	      case ']':
	        /* Check if the right square bracket was preceded by a backslash.
	           If this is true, then the right square bracket is not really a
	           glob character at all. Note that the right square bracket must
	           be escaped because regex will treat it as a special character.*/
	     	  if (escapeFlag) {
	    	      add = "\\" + globChar;
	          break;
	        }
	        /* We need to make sure that a class is already active. Report
	           an error if a class is not already started. */
	        if (classMode == false) {
	          String  errorText = "No glob class is currently active";
	          HDLmAssertAction(false, errorText);
	        }
	        /* At this point, we have a right square bracket that really is
	           the end of a class. The right square bracket does not need
	           to be escaped. */
	        add = "" + globChar;
	        classMode = false;
	        break;
	      /* We now need to consider a character that may or may not be a
	         glob character. The exclamation mark may be a negation of a
	         class or it may just be an exclamation mark. We only consider
	         an exclamation mark to be a glob character, if we are in class
	         mode and the prior character was a left square bracket. */
	      case '!':
	        /* We now need to check for an exclamation mark that is (correctly)
	           preceded by an escape. In this case, the exclamation mark is not 
	           really meant as a glob character. Moreover, an escape should not 
	           be added to the output string because regex won't treat an exclamation
	           mark as a special regex character.*/
	        if (escapeFlag) {
	          add = "" + globChar;
	          break;
	        }
	        /* Check for an exclamation mark, right after a left square bracket.
	           This is the normal, expected case. All we need to do in this case
	           is to copy a not sign (not an exclamation mark) to the output string. */
	        if (classMode == true) { 
	    	    priorRv = HDLmMatchChar.priorCharGet(globString, index);
	    	    escapeFlag = priorRv.getEscapeFlag();
	    	    startFlag = priorRv.getStartFlag();
	    	    priorChar = priorRv.getMatchChar();
	    	    /* We need to look at the character before the current
	    	       (unescaped) exclamation mark. If the prior character
	    	       is a left square bracket and is also not escaped then
	    	       we just have a simple (and valid) negation. */
	    	    if (priorChar  != null &&
	              priorChar  == '['  &&
	     		      escapeFlag == false) {
	            add = "" + '^';
	            break;
	          }
	        }	
	        /* We now have an exclamation mark that is not after a left square bracket
	           and is not escaped. This is an error condition. */
	        if (true) {
	          String  errorText = "Unescaped exclamation mark does not follow left bracket";
	          HDLmAssertAction(false, errorText);
	        }
	      /* We now need to consider a real glob character, the asterisk.
	         The user may want the asterisk to be a true glob or just an
	         asterisk in the pattern. In the latter case, the asterisk must
	         be preceded by an escape. */
	      case '*':
	        /* Check if the asterisk was preceded by a backslash. If this is
	           true, then the asterisk is not really a glob character at all. */
	      	if (escapeFlag) {
	       	  add = "\\" + globChar;
	          break;
	        }
	        /* We have a true glob asterisk. Replace it with a regex pattern
	           that matches any number of non-slash characters. */
	        add = "[^\\/]*";
	        break;
	      /* We now need to consider a real glob character, the question mark.
	         The user may want the question mark to be a true glob or just an
	         question mark in the pattern. In the latter case, the question
	         mark must be preceded by an escape. */
	      case '?':
	        /* Check if the question mark was preceded by a backslash. If this
	           is true, then the question mark is not really a glob character
	           at all. */
	      	if (escapeFlag) {
	       	  add = "\\" + globChar;
	          break;
	        }
	        /* We have a true glob question mark. Replace it with a regex pattern
	           that matches any one non-slash character. */
	        add = "[^\\/]{1,1}";
	        break;
	      /* Handle all other characters. Just copy them to the output regex
	         string. */
	      default:
	   	    add = "" + globChar;
	    }
	    regexStr.append(add);
	  }
	  /* Now that we are done, we should not have a glob class still active.
	     Report an error, if a glob class is still active. */
	  if (classMode == true) {
	    String  errorText = "Some type of glob class is active at end of input";
	    HDLmAssertAction(false, errorText);
	  }
	  /* Add the trailing dollar sign */
	  regexStr.append('$'); 
	  return regexStr.toString();
	}
	/* Convert a glob string pattern to a regex string pattern.
	   This routine takes an input globl string pattern and
	   returns a regex string pattern. At least this routine
	   tries to do so. The rules for how glob is supposed to 
	   work are actually quite complex. The generated regexes
	   do not follow all of the rules. A key point is that a
	   glob is not supposed to match a forward slash. However,
	   the generated regex will match a forward slash.
	
	   The returned regex string will not have the leading and
	   trailing forward slash characters needed for JavaScript
	   and perhaps other systems. These characters will have to
	   be added if the regex is going to be used with JavaScript
	   and perhaps other systems. */
	protected static String globToRegexOld(String globString) {
		if (globString == null) {
		  String  errorText = "Glob string passed to globToRegex is null";
		  throw new NullPointerException(errorText);
		}
	  /* Declare and define a few local variables */
	  int            regexStrLen;
	  String         add;
	  StringBuilder  regexStr = new StringBuilder();
	  /* Start the output regex string by attaching it to the left
	     bound of the test string */
	  regexStr.append('^');
	  /* We assume that we are not in class mode at the outset. Class
	     mode (something like [abc] or [0-9]) is always started by a
	     left square bracket. Of course, at the beginning we have not
	     found a left square bracket yet. */
	  boolean   classMode = false;
	  int       globStringLen = globString.length();
	  for (int i = 0; i < globStringLen; i++) {
	    /* Get the current glob character */
	    char  globChar = globString.charAt(i);
	    switch (globChar) {
	      /* Handle several different characters in the original glob
	         string. Each of these characters can be handled by simply
	         escaping the character. We don't want these characters to
	         be treated as special regex characters. */
	      case '.':
	      case '^':
	      case '$':
	      case '+':
	      case '(':
	      case ')':
	      case '{':
	      case '}':
	      case '\\':
	      case '/':
	      case '|':
	        add = "\\" + globChar;
	        break;
	      /* Left square bracket is a very special character. It may or may
	         not mark the start of a class (something like [abc] or [0-9]).
	         Of course, if the left square bracket is escaped, then it is
	         just a left square bracket, not the start of a class. */
	      case '[':
	        /* Check if the left square bracket was preceded by a backslash.
	           If this is true, then the left square bracket is not really a
	           glob character at all. Note that the left square bracket must
	           be escaped because regex will treat it as a special character. */
	        if (i > 0 &&
	            globString.charAt(i-1) == '\\') {
	       	  add = "\\" + globChar;
	       	  regexStrLen = regexStr.length();
	      	  regexStr.delete(regexStrLen-2, regexStrLen);
	          break;
	        }
	        /* We need to make sure that a class is not already active. Report
	           an error if a class is already started. */
	        if (classMode) {
	          String  errorText = "Some type of glob class has already been started";
	          HDLmAssertAction(false, errorText);
	        }
	        /* At this point, we have a left square bracket that really is
	           the start of a class. The left square bracket does not need
	           to be escaped and in fact, must not be escaped. */
	        add = "" + globChar;
	        classMode = true;
	        break;
	      /* Right square bracket is a very special character. It may or may
	         not mark the end of a class (something like [abc] or [0-9]). Of
	         course, if the right square bracket is escaped, then it is just
	         a right square bracket, not the end of a class. */
	      case ']':
	        /* Check if the right square bracket was preceded by a backslash.
	           If this is true, then the right square bracket is not really a
	           glob character at all. Note that the right square bracket must
	           be escaped because regex will treat it as a special character.*/
	        if (i > 0 &&
	            globString.charAt(i-1) == '\\') {
	      	  add = "\\" + globChar;
	       	  regexStrLen = regexStr.length();
	      	  regexStr.delete(regexStrLen-2, regexStrLen);
	          break;
	        }
	        /* We need to make sure that a class is already active. Report
	           an error if a class is not already started. */
	        if (classMode == false) {
	          String  errorText = "No glob class is currently active";
	          HDLmAssertAction(false, errorText);
	        }
	        /* At this point, we have a right square bracket that really is
	           the end of a class. The right square bracket does not need
	           to be escaped. */
	        add = "" + globChar;
	        classMode = false;
	        break;
	      /* We now need to consider a character that may or may not be a
	         glob character. The exclamation mark may be a negation of a
	         class or it may just be an exclamation mark. We only consider
	         an exclamation mark to be a glob character, if we are in class
	         mode and the prior character was a left square bracket. */
 	      case '!':
	        if (classMode == true &&
	            i > 0             &&
	            globString.charAt(i-1) == '[') {
	          add = "" + '^';
	          break;
	        }
	        /* We now need to check for an exclamation mark that is (correctly)
	           preceded by an escape. In this case, the exclamation mark is not
	           really meant as a glob character. Moreover, an escape should not be
	           added to the output string because regex won't treat an exclamation
	           mark as a special regex character. */
	        if (i > 0 &&
	            globString.charAt(i-1) == '\\') {
	          add = "" + globChar;
	      	  regexStrLen = regexStr.length();
	     	    regexStr.delete(regexStrLen-2, regexStrLen);
	          break;
	        }
	        /* We now have an exclamation mark that is not after a left square bracket
	           and is not escaped. This is an error condition. */
	        if (true) {
	          String  errorText = "Unescaped exclamation mark does not follow left bracket";
	          HDLmAssertAction(false, errorText);
	        }
	      /* We now need to consider a real glob character, the asterisk.
	         The user may want the asterisk to be a true glob or just an
	         asterisk in the pattern. In the latter case, the asterisk must
	         be preceded by an escape. */
	      case '*':
	        /* Check if the asterisk was preceded by a backslash. If this is
	           true, then the asterisk is not really a glob character at all. */
	        if (i > 0 &&
	            globString.charAt(i-1) == '\\') {
	       	  add = "\\" + globChar;
	       	  regexStrLen = regexStr.length();
	     	    regexStr.delete(regexStrLen-2, regexStrLen);
	          break;
	        }
	        /* We have a true glob asterisk. Replace it with a regex pattern
	           that matches any number of non-slash characters. */
	        add = "[^\\/]*";
	        break;
	       /* We now need to consider a real glob character, the question mark.
	          The user may want the question mark to be a true glob or just an
	          question mark in the pattern. In the latter case, the question
	          mark must be preceded by an escape. */
	      case '?':
	        /* Check if the question mark was preceded by a backslash. If this
	           is true, then the question mark is not really a glob character
	           at all. */
	        if (i > 0 &&
	            globString.charAt(i-1) == '\\') {
	          add = "\\" + globChar;
	       	  regexStrLen = regexStr.length();
		        regexStr.delete(regexStrLen-2, regexStrLen);
	          break;
	        }
	        /* We have a true glob question mark. Replace it with a regex pattern
	           that matches any one non-slash character. */
	        add = "[^\\/]{1,1}";
	        break;
	      /* Handle all other characters. Just copy them to the output regex
	         string. */
	      default:
	   	    add = "" + globChar;
	    }
	    regexStr.append(add);
	  }
	  /* Now that we are done, we should not have a glob class still active.
	     Report an error, if a glob class is still active. */
	  if (classMode == true) {
	    String  errorText = "Some type of glob class is active at end of input";
	    HDLmAssertAction(false, errorText);
	  }
	  /* Add the trailing dollar sign */
	  regexStr.append('$'); 
	  return regexStr.toString();
	}
  /* Check if a string contains any like (SQL LIKE) characters.
	   This routine will not change the input string. However, it
	   will check for like (SQL LIKE) characters. If any like (SQL
	   LIKE) characters are found, this routine will return true.
	   If no like (SQL LIKE) characters are found, this routine
	   will return false. Note that this routine does check for left
		 square bracket, but not the right square bracket. It is assumed
		 that a user will always have a left square bracket with a right
		 square bracket. This code follows (more or less) the SQL Server
		 rules for SQL LIKE.
		
		 Note that an escaped like (SQL LIKE) character is still considered
		 to be a like (SQL LIKE) character. This is required so that the input
		 string will still be considered to be a like (SQL LIKE) string and
		 will be converted to a regex. For example, the percent character
		 must be escaped to prevent it from being treated as like percent.
		 However, the escape should be removed because regex does not treat
		 percent as a special character. */
	protected static boolean likeCheck(String matchString) {
		if (matchString == null) {
		  String  errorText = "Match string passed to likeCheck is null";
		  throw new NullPointerException(errorText);
		}
	  boolean  rv = false;
	  char     matchChar;
	  int      matchStringLen = matchString.length();
	  for (int i = 0; i < matchStringLen; i++) {
	    /* Get the current match character */
	    matchChar = matchString.charAt(i);
	    /* Check if it is a special like (SQL LIKE) character */
	    if (matchChar == '%' ||
	        matchChar == '_' ||
	        matchChar == '[' ||
	        matchChar == '^') {
	      rv = true;
	      break;
	    }
	  }
	  return rv;
	}
	/* Convert a like (SQL LIKE) string pattern to a regex string
		 pattern. This routine takes an input like (SQL LIKE) string
		 pattern and returns a regex string pattern. At least this
		 routine tries to do so. The rules for how a like is supposed
		 to work are actually quite complex. The generated regexes
		 do not follow all of the rules, at I don't think so.
		
		 The returned regex string will not have the leading and
		 trailing forward slash characters needed for JavaScript
		 and perhaps other systems. These characters will have to
		 be added if the regex is going to be used with JavaScript
		 and perhaps other systems. This code follows (more or less)
		 the SQL Server rules for SQL LIKE.
		 
		 Note that any character can be escaped (using a backslash). Any
		 escaped character represents itself. This rule includes escape
		 itself. If a backslash is preceded by another backslash, then 
		 the two characters represent a single literal backslash. One
		 consequence is that a single backslash can not be the last 
		 character of an input string. */
	protected static String likeToRegex(String likeString) {
		if (likeString == null) {
		  String  errorText = "Like string passed to likeToRegex is null";
		  throw new NullPointerException(errorText);
		}
	  /* Declare and define a few local variables */
	  boolean         endFlag = false;
	  boolean         escapeFlag = false;
	  boolean         startFlag = false;
	  Character       likeChar;
	  Character       priorChar;
	  HDLmMatchChar   likeRv;
	  HDLmMatchChar   priorRv;
	  Integer         index = -1;
	  String          add;
	  StringBuilder   regexStr = new StringBuilder();
	  /* Start the output regex string by attaching it to the left
	     bound of the test string */
	  regexStr.append('^');
	 	/* We assume that we are not in class mode at the outset. Class
	 	   mode (something like [abc] or [0-9]) is always started by a
	 	   left square bracket. Of course, at the beginning we have not
	 	   found a left square bracket yet. */
		boolean   classMode = false;
	  /* What follows is a dummy loop used only to allow break to work */
	  while (true) {
	    /* Get the next character and terminate if we have reached the end of
	       the string */  
	    likeRv = HDLmMatchChar.nextCharGet(likeString, index);
	    endFlag = likeRv.getEndFlag();
	    escapeFlag = likeRv.getEscapeFlag();
	    index = likeRv.getIndex();
	    likeChar = likeRv.getMatchChar();
	    if (endFlag)
	      break;
	    /* The rules for handling each character depend on the character and 
	       whether the escape flag was set */ 
	    switch (likeChar) {
	      /* Handle several different characters in the original like
	         string. Each of these characters can be handled by simply
	         escaping the character. We don't want these characters to
	         be treated as special regex characters. */
	      case '.':
	      case '$':
	      case '+':
	      case '(':
	      case ')':
	      case '{':
	      case '}':
	      case '\\':
	      case '/':
	      case '|':
	      case '?':
	      case '*':
	        add = "\\" + likeChar;
	        break;
	      /* Left square bracket is a very special character. It may or may
	         not mark the start of a class (something like [abc] or [0-9]).
	         Of course, if the left square bracket is escaped, then it is
	         just a left square bracket, not the start of a class. */
	      case '[':
	        /* Check if the left square bracket was preceded by a backslash.
	           If this is true, then the left square bracket is not really a
	           like character at all. */
	        if (escapeFlag) {
	          add = "\\" + likeChar;
	          break;
	        }
	        /* We need to make sure that a class is not already active. Report
	           an error if a class is already started. */
	        if (classMode) {
	          String  errorText = "Some type of like class has already been started";
	          HDLmAssertAction(false, errorText);
	        }
	        /* At this point, we have a left square bracket that really is
	           the start of a class. The left square bracket does not need
	           to be escaped. */
	        add = "" + likeChar;
	        classMode = true;
	        break;
	      /* Right square bracket is a very special character. It may or may
	         not mark the end of a class (something like [abc] or [0-9]). Of
	         course, if the right square bracket is escaped, then it is just
	         a right square bracket, not the start of a class. */
	      case ']':
	        /* Check if the right square bracket was preceded by a backslash.
	           If this is true, then the right square bracket is not really a
	           like character at all. */
	        if (escapeFlag) {
	          add = "\\" + likeChar;
	          break;
	        }
	        /* We need to make sure that a class is already active. Report
	           an error if a class is not already started. */
	        if (classMode == false) {
	         	String  errorText = "No like class is currently active";
	       	  HDLmAssertAction(false, errorText);
	        }  
	        /* At this point, we have a right square bracket that really is
	           the end of a class. The right square bracket does not need
	           to be escaped. */
	        add = "" + likeChar;
	        classMode = false;
	        break;
	      /* We now need to consider a character that may or may not be a
	         like character. The not sign may be a negation of a class or
	         it may just be a not sign. We only consider a not sign to be
	         like character, if we are in class mode and the prior character
	         was a left square bracket. */
	      case '^':
	        /* We now need to check for a not sign that is (correctly) preceded
	           by an escape. In this case, the not sign is not really meant as
	           a like character. However, an escape must be added to the output
	           string to prevent regex from treating the not sign as a special
	           regex character. */
	        if (escapeFlag) {
	          add = "\\" + likeChar;
	          break;
	        }
	        /* Check for a not sign, right after a left square bracket. This
	           is the normal, expected case. All we need to do in this case
	           is to copy the not sign to the output string. */
	        if (classMode == true) { 
	        	priorRv = HDLmMatchChar.priorCharGet(likeString, index); 
	    	    escapeFlag = priorRv.getEscapeFlag();
	    	    startFlag = priorRv.getStartFlag();
	    	    priorChar = priorRv.getMatchChar();
	       	  /* We need to look at the character before the current
	       	     (unescaped) not sign. If the prior character is a 
	       	     left square bracket and is also not escaped then
	       	     we just have a simple (and valid) negation. */
	    	    if (priorChar  != null && 
	              priorChar  == '['  &&
	        	  	escapeFlag == false) {
	            add = "" + '^';
	            break;
	          }
	        }
	        /* We now have a not sign that is not after a left square bracket
	           and is not escaped. This is an error condition. */
	        if (true) {
		        String  errorText = "Unescaped not sign does not follow left bracket";
		        HDLmAssertAction(false, errorText);
	        }
	      /* We now need to consider a real like character, the percent sign.
	         The user may want the percent sign to be a true like percent sign
	         or just a percent sign character in the pattern. In the latter case,
	         the percent sign must be preceded by an escape in the input string. */
	      case '%':
	        /* Check if the percent sign was preceded by a backslash. If this is
	           true, then the percent sign is not really a like character at all. */
	        if (escapeFlag) {	        	
		        add = "" + likeChar;	      
	          break;
	        }
	        /* We have a true like percent signk. Replace it with a regex pattern
	           that matches any number (zero or more) of characters. Note that this
	           generated pattern will match forward slash characters. */
	        add = ".*";
	        break;
	      /* We now need to consider a real like character, the underscore.
	         The user may want the underscore to be a true like underscore
	         or just an underscore character in the pattern. In the latter
	         case, the underscore mark must be preceded by an escape in the
	         input string. */
	      case '_':
	        /* Check if the underscore character was preceded by a backslash. If this
	           is true, then the underscore character is not really a like character
	           at all. */
	     	  if (escapeFlag) {	
	  	      add = "" + likeChar;	    
	          break;
	        }
	        /* We have a true like underscore character. Replace it with a regex pattern
	           that matches one character (which could even be a forward slash). */
	        add = ".{1,1}";
	        break;
	      /* Handle all other characters. Just copy them to the output regex
	         string. */
	      default:
	        add = "" + likeChar;
	    }
	    regexStr.append(add);
	  }
	  /* Now that we are done, we should not have a like class still active.
	     Report an error, if a like class is still active. */
	  if (classMode) {
	    String  errorText = "Some type of like class is active at end of input";
	    HDLmAssertAction(false, errorText);
	  }
	  /* Add the trailing dollar sign */
	  regexStr.append('$');
	  return regexStr.toString();
	}
	/* Convert a like (SQL LIKE) string pattern to a regex string
		 pattern. This routine takes an input like (SQL LIKE) string
		 pattern and returns a regex string pattern. At least this
		 routine tries to do so. The rules for how a like is supposed
		 to work are actually quite complex. The generated regexes
		 do not follow all of the rules, at I don't think so.
		
		 The returned regex string will not have the leading and
		 trailing forward slash characters needed for JavaScript
		 and perhaps other systems. These characters will have to
		 be added if the regex is going to be used with JavaScript
		 and perhaps other systems. This code follows (more or less)
		 the SQL Server rules for SQL LIKE.*/
	protected static String likeToRegexOld(String likeString) {
		if (likeString == null) {
		  String  errorText = "Like string passed to likeToRegex is null";
		  throw new NullPointerException(errorText);
		}
	  /* Declare and define a few local variables */
	  int            regexStrLen;
	  String         add;
	  StringBuilder  regexStr = new StringBuilder();
	  /* Start the output regex string by attaching it to the left
	     bound of the test string */
	  regexStr.append('^');
	 	/* We assume that we are not in class mode at the outset. Class
	 	   mode (something like [abc] or [0-9]) is always started by a
	 	   left square bracket. Of course, at the beginning we have not
	 	   found a left square bracket yet. */
		boolean   classMode = false;
		int       likeStringLen = likeString.length();
	  for (int i = 0; i < likeStringLen; i++) {
	    /* Get the current like character */
	    char  likeChar = likeString.charAt(i);
	    switch (likeChar) {
	      /* Handle several different characters in the original like
	         string. Each of these characters can be handled by simply
	         escaping the character. We don't want these characters to
	         be treated as special regex characters. */
	      case '.':
	      case '$':
	      case '+':
	      case '(':
	      case ')':
	      case '{':
	      case '}':
	      case '\\':
	      case '/':
	      case '|':
	      case '?':
	      case '*':
	        add = "\\" + likeChar;
	        break;
	      /* Left square bracket is a very special character. It may or may
	         not mark the start of a class (something like [abc] or [0-9]).
	         Of course, if the left square bracket is escaped, then it is
	         just a left square bracket, not the start of a class. */
	      case '[':
	        /* Check if the left square bracket was preceded by a backslash.
	           If this is true, then the left square bracket is not really a
	           like character at all. */
	        if (i > 0 &&
	            likeString.charAt(i-1) == '\\') {
	          add = "\\" + likeChar;
	       	  regexStrLen = regexStr.length();
	      	  regexStr.delete(regexStrLen-2, regexStrLen);
	          break;
	        }
	        /* We need to make sure that a class is not already active. Report
	           an error if a class is already started. */
	        if (classMode) {
	          String  errorText = "Some type of like class has already been started";
	          HDLmAssertAction(false, errorText);
	        }
	        /* At this point, we have a left square bracket that really is
	           the start of a class. The left square bracket does not need
	           to be escaped. */
	        add = "" + likeChar;
	        classMode = true;
	        break;
	      /* Right square bracket is a very special character. It may or may
	         not mark the end of a class (something like [abc] or [0-9]). Of
	         course, if the right square bracket is escaped, then it is just
	         a right square bracket, not the start of a class. */
	      case ']':
	        /* Check if the right square bracket was preceded by a backslash.
	           If this is true, then the right square bracket is not really a
	           like character at all. */
	        if (i > 0 &&
	            likeString.charAt(i-1) == '\\') {
	          add = "\\" + likeChar;
	       	  regexStrLen = regexStr.length();
	      	  regexStr.delete(regexStrLen-2, regexStrLen);
	          break;
	        }
	        /* We need to make sure that a class is already active. Report
	           an error if a class is not already started. */
	        if (classMode == false) {
	        	String  errorText = "No like class is currently active";
	        	HDLmAssertAction(false, errorText);
	        } 
	        /* At this point, we have a right square bracket that really is
	           the end of a class. The right square bracket does not need
	           to be escaped. */
	        add = "" + likeChar;
	        classMode = false;
	        break;
	      /* We now need to consider a character that may or may not be a
	         like character. The not sign may be a negation of a class or
	         it may just be a not sign. We only consider a not sign to be
	         like character, if we are in class mode and the prior character
	         was a left square bracket. */
	      case '^':
	        /* Check for a not sign, right after a left square bracket. This
	           is the normal, expected case. All we need to do in this case
	           is to copy the not sign to the output string. */
	        if (classMode == true &&
	            i > 0             &&
	            likeString.charAt(i-1) == '[') {
	          add = "" + '^';
	          break;
	        }
	        /* We now need to check for a not sign that is (correctly) preceded
	           by an escape. In this case, the not sign is not really meant as
	           a like character. However, an escape must be added to the output
	           string to prevent regex from treating the not sign as a special
	           regex character. */
	        if (i > 0 &&
	            likeString.charAt(i-1) == '\\') {
	          add = "\\" + likeChar;
	       	  regexStrLen = regexStr.length();
	      	  regexStr.delete(regexStrLen-2, regexStrLen);
	          break;
	        } 
	        /* We now have a not sign that is not after a left square bracket
	           and is not escaped. This is an error condition. */
	        if (true) {
		         String  errorText = "Unescaped not sign does not follow left bracket";
		         HDLmAssertAction(false, errorText);
	        }
	      /* We now need to consider a real like character, the percent sign.
	         The user may want the percent sign to be a true like percent sign
	         or just a percent sign character in the pattern. In the latter case,
	         the percent sign must be preceded by an escape in the input string. */
	      case '%':
	        /* Check if the percent sign was preceded by a backslash. If this is
	           true, then the percent sign is not really a like character at all.
	           Note that the preceding backslash must be removed from the output
	           (regex) string because percent sign is not a special regex character. */
	        if (i > 0 &&
	            likeString.charAt(i-1) == '\\') {
	        	/* Check if the character before the backslash was another backslash.
	        	   In that case, we really just a have a percent sign in the input
	        	   pattern. We need to generate the corresponding regex characters. */	        	 
	        	if (i > 1 &&
	        			likeString.charAt(i-2) == '\\') {	        
		           /* We have a true like percent signk. Replace it with a regex pattern
	               that matches any number (zero or more) of characters. Note that this
	               generated pattern will match forward slash characters. */
	            add = ".*";
	        	}
	        	/* The percent sign was escaped. We can just add the percent sign to the
	        	   output regex. Regex will ignore the percent sign. */ 	         
	        	else {	        	
		           add = "" + likeChar;
		        	  regexStrLen = regexStr.length();
		       	  regexStr.delete(regexStrLen-2, regexStrLen);
	        	}
	          break;
	        }
	        /* We have a true like percent signk. Replace it with a regex pattern
	           that matches any number (zero or more) of characters. Note that this
	           generated pattern will match forward slash characters. */
	        add = ".*";
	        break;
	      /* We now need to consider a real like character, the underscore.
	         The user may want the underscore to be a true like underscore
	         or just an underscore character in the pattern. In the latter
	         case, the underscore mark must be preceded by an escape in the
	         input string. */
	      case '_':
	        /* Check if the underscore character was preceded by a backslash. If this
	           is true, then the underscore character is not really a like character
	           at all.  Note that the preceding backslash must be removed from the
	           output (regex) string because underscore is not a special regex character. */
	        if (i > 0 &&
	            likeString.charAt(i-1) == '\\') {
	        	/* Check if the character before the backslash was another backslash.
	    	       In that case, we really just a have an underscore in the input
	    	       pattern. We need to generate the corresponding regex characters. */	        	 
	    	    if (i > 1 &&
	    			    likeString.charAt(i-2) == '\\') {	
	 	          /* We have a true like underscore character. Replace it with a regex pattern
	               that matches one character (which could even be a forward slash). */
	            add = ".{1,1}";
	    	    }
	       	  /* The underscoe was escaped. We can just add the underscoe to the
	    	       output regex. Regex will ignore the percent sign. */
	    	    else {
	    	      add = "" + likeChar;
	      	    regexStrLen = regexStr.length();
	     	      regexStr.delete(regexStrLen-2, regexStrLen);
	    	    }	    
	          break;
	        }
	        /* We have a true like underscore character. Replace it with a regex pattern
	           that matches one character (which could even be a forward slash). */
	        add = ".{1,1}";
	        break;
	      /* Handle all other characters. Just copy them to the output regex
	         string. */
	      default:
	        add = "" + likeChar;
	    }
	    regexStr.append(add);
	  }
	  /* Now that we are done, we should not have a like class still active.
	     Report an error, if a like class is still active. */
	  if (classMode) {
	    String  errorText = "Some type of like class is active at end of input";
	    HDLmAssertAction(false, errorText);
	  }
	  /* Add the trailing dollar sign */
	  regexStr.append('$');
	  return regexStr.toString();
	}	
	/* The next routine checks if the current match instance actually
	   matches the string value passed by the caller. This may be done
	   using a simple string comparison or using the regex mechanism. */
	protected boolean match(String testString) {
		if (testString == null) {
		  String  errorText = "Test string passed to match is null";
		  throw new NullPointerException(errorText);
		}
		/* Make sure the match type value is set */
		if (this.type == null) {
			HDLmAssertAction(false, "Match type value is not set");
		}
		/* Check if we should do a simple string comparison */
		if (this.type == HDLmMatchTypes.NONE) {
			/* Make sure the string value reference is set */
			if (this.value == null) {
				HDLmAssertAction(false, "Match value string reference is not set");
			}
			return value.equals(testString);
		}		
		/* Make sure the regex pattern reference is set */
		if (this.pattern == null) {
			HDLmAssertAction(false, "Match pattern reference is not set");
		}
		/* Try to use the regex pattern to match the test string 
		   passed by the caller. Get a matcher object from pattern */ 
    Matcher   matcher = this.pattern.matcher(testString); 
      /* Check if we actually got a match or not */
    return matcher.matches();		 
	}
	/* Get the pattern from a match object and return it to the caller */
	protected Pattern      getPattern() {
		return this.pattern;
	}
	/* Get the match type from a match object and return it to the caller */
	protected HDLmMatchTypes  getType() {
		return this.type;
	}
	/* Get the match string value from a match object and return it to the caller */
	protected String       getValue() {
		return this.value;
	}
}
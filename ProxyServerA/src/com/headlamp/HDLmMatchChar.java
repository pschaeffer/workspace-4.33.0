package com.headlamp;

/**
 * HDLmMatchChar short summary.
 *
 * HDLmMatchChar description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
/* An instance of this class is created to return one match 
   character from a match string. Many flags and other values
   are returned with the match character (which may not be
   returned at all). */ 
public class HDLmMatchChar {
	/* Some or all of the values below are set by each match 
	   character call. Getters and setters are provided for 
	   the values that need to be obtained and/or set. */ 
  private boolean     startFlag = false;
  private boolean     endFlag = false;
  private boolean     escapeFlag = false;
  private boolean     errorFlag = false;
  private boolean     unmatchedEscapeFlag = false;
  private Character   matchChar = null; 
  private Integer     index = null; 
  private String      errorText = null;
  /* Get the match check character and return it to the caller */
  protected Character getMatchChar() {
    return this.matchChar;
  }
  /* Get the match check end flag and return it to the caller */
  protected boolean getEndFlag() {
    return this.endFlag;
  }
  /* Get the match check escape flag and return it to the caller */
  protected boolean getEscapeFlag() {
    return this.escapeFlag;
  }
  /* Get the match check index value and return it to the caller */
  protected Integer getIndex() {
    return this.index;
  }
  /* Get the match check start flag and return it to the caller */
  protected boolean getStartFlag() {
    return this.startFlag;
  }
  /* This routine returns the next character from the input string.
		 The caller provides the input string and current index (starting
		 from zero, not one). If the next character is an escape, then
		 the character after the next is actually returned by this routine.
		 Note that this routine sets a flag showing if an escape was found
		 and if the end of the string was reached. The caller passes the
		 index of the last character that has already been retrieved. This
		 routine retrieves characters after that character. This routine does
		 not change the index value passed by the caller. 
		 
     The index value passed by the caller needs some explanation. If the 
     character identified by the index is not escaped, then the index will       
     just point to that character. However, if the character is escaped, 
     then the index passed by the caller will point to the escape, not the    
     actual character. */ 
  protected static HDLmMatchChar nextCharGet(String inString, int inIndex) {
		if (inString == null) {
		  String  errorText = "Match string passed to nextCharGet routine is null";
		  throw new NullPointerException(errorText);
		} 
		if (inIndex < -1) {
			String  errorText = String.format("Index value (%d) passed to nextCharGet is less than minus one", inIndex);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Construct a new check match character class object. This object 
		   is used to return several values to the caller. The default 
		   constructor sets all of the values to their defaults. */
		HDLmMatchChar checkRV = new HDLmMatchChar();
		/* Get the input string length. This value is checked below. */
		int   inStringLen = inString.length();
    /* The index value passed by the caller may actually be the index
       value of an escape that precedes the character being escaped. If 
       this is true, then we must skip the escape character before we
       skip the character being escaped. */
	  if (inIndex < inStringLen &&
	      inIndex >= 0          &&
	      inString.charAt(inIndex) == '\\')
	    inIndex += 1;
	  /* Check if the input string is depleted or not. If the input
	     string has already been depleted, all we need to do is to 
	     set the end flag. */
		int  nextIndex = inIndex + 1;
		if (nextIndex >= inStringLen) {
		  checkRV.setEndFlag(true);
		  return checkRV;
		}
		/* Get the next character and check if it an escape. If the next
		   character is not an escape, we are done. */
		char  nextChar = inString.charAt(nextIndex);
		if (nextChar != '\\') {
		  checkRV.setMatchChar(nextChar);
		  checkRV.setIndex(nextIndex);
		  return checkRV;
		}
		/* The next character was an escape. We must get the character
		   after the escape (if possible). */
		nextIndex++;
		if (nextIndex >= inStringLen) {
		  checkRV.setEndFlag(true);
		  checkRV.setErrorFlag(true);
		  checkRV.setUnmatchedEscapeFlag(true);
		  String  errorText = "No character follows escape in the input string";
		  checkRV.setErrorText(errorText);
		}
		/* Get the next character. Note that the returned character is
		   the escaped character (not the escape). However, the index
		   value is the index value of the escape, not the actual 
		   character. */
		nextChar = inString.charAt(nextIndex);
		checkRV.setMatchChar(nextChar);
		checkRV.setIndex(nextIndex - 1);
		checkRV.escapeFlag = true;
		return checkRV;
  }
  /* This routine returns the prior character from the input string,
     if possible. The caller provides the input string and current index
     (starting from zero, not one). The current index is for the last
     character obtained from the input string. The prior character (if
     there is one) is the character before the last character. The last
     character may or may not have been escaped. The character before
     the last character may or may not have been escaped.

     If the prior character was escaped, then the escape flag is set.
     If no prior character can be obtained (because we are at the start
     of the string), the start flag is set.

	   The caller passes the index of the last character that has already
	   been retrieved. This routine retrieves a character before that character.
	   This routine does not change the index value passed by the caller.
	   
	   The index value passed by the caller needs some explanation. If the
     character identified by the index is not escaped, then the index will
     just point to that character. However, if the character is escaped,
     then the index passed by the caller will point to the escape, not the
     actual character. 

	   Note that any character can be escaped (using a backslash). Any
	   escaped character represents itself. This rule includes escape
	   itself. If a backslash is preceded by another backslash, then
	   the two characters represent a single literal backslash. One
	   consequence is that a single backslash can not be the last
	   character of an input string. */
  protected static HDLmMatchChar priorCharGet(String inString, int inIndex) {
		if (inString == null) {
		  String  errorText = "Match string passed to priorCharGet routine is null";
		  throw new NullPointerException(errorText);
		} 
		if (inIndex < -1) {
			String  errorText = String.format("Index value (%d) passed to priorCharGet is less than minus one", inIndex);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
	  /* Construct a new check match character class object. This object
	     is used to return several values to the caller. The default
	     constructor sets all of the values to their defaults. */
		HDLmMatchChar checkRV = new HDLmMatchChar();
	  /* Check if it is even possible to go back further */
	  if (inIndex <= 0) {
	    checkRV.setStartFlag(true);
      checkRV.setErrorFlag(true);
      String  errorText = "Prior character can not be found before start of string";
      checkRV.setErrorText(errorText);
      return checkRV;
	  }
	  /* Get the prior character before the last character */
	  inIndex--;
	  char  priorChar = inString.charAt(inIndex);
	  /* Check if it is even possible to go back further */
	  if (inIndex <= 0) {
	    checkRV.setStartFlag(true);
	    checkRV.setIndex(inIndex);
	    checkRV.setMatchChar(priorChar);
	    return checkRV;
	  }
	  /* Get the character before the prior character and check
	     if it as escape. If it not an escape we are done. */
	  if (inString.charAt(inIndex - 1) != '\\') {
	    checkRV.setIndex(inIndex);
	    checkRV.setMatchChar(priorChar);
	    return checkRV;
	  }
	  /* The character before the prior character was an escape.
	     We need to set the escape flag. The index is set to the
	     location of the escape. The actual character is returned,
	     not the escape. */
	  inIndex--;
	  checkRV.setEscapeFlag(true);
	  checkRV.setIndex(inIndex);
	  checkRV.setMatchChar(priorChar);
	  return checkRV;
	}
  /* Set the match check character value using the value passed by the caller */
  protected void setMatchChar(Character newMatchChar) {
    this.matchChar = newMatchChar;
  }
  /* Set the match check end flag using the value passed by the caller */
  protected void setEndFlag(boolean newEndFlag) {
    this.endFlag = newEndFlag;
  }
  /* Set the match check error flag using the value passed by the caller */
  protected void setErrorFlag(boolean newErrorFlag) {
    this.errorFlag = newErrorFlag;
  }
  /* Set the match check error text using the value passed by the caller */
  protected void setErrorText(String newErrorText) {
    this.errorText = newErrorText;
  }
  /* Set the match check escape flag using the value passed by the caller */
  protected void setEscapeFlag(boolean newEscapeFlag) {
    this.escapeFlag = newEscapeFlag;
  }
  /* Set the match check index value using the value passed by the caller */
  protected void setIndex(int newIndex) {
    this.index = newIndex;
  }
  /* Set the match check start flag using the value passed by the caller */
  protected void setStartFlag(boolean newStartFlag) {
    this.startFlag = newStartFlag;
  }
  /* Set the match check unmatched escape flag using the value passed by 
     the caller */
  protected void setUnmatchedEscapeFlag(boolean newUnmatchedEscapeFlag) {
    this.unmatchedEscapeFlag = newUnmatchedEscapeFlag;
  }
}	
package com.headlamp;
/**
 * HDLmAssert short summary.
 *
 * HDLmAssert description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmAssert {
	/* This class can never be instantiated */
	private HDLmAssert() {}
	/* The HDLmAssert function (which is not a class) is used to
	   provide a simple assert mechanism */	
	protected static void HDLmAssertAction(boolean test, String  errorText) {
		/* Note that the error text string reference passed to this
		   method could be null. This is checked for below. There 
		   is no check for a null value here. */ 	  
		if (test)
	    return;
		/* We would like to test for and reject null values passed to this routine.
	     However, this is an error handler. It should probably not fail when it is
	     needed to throw an exception. */
	  if (errorText == null)
	  	errorText = "Null Error Text";
	  throw new AssertionError(errorText);
  }
}
package com.headlamp;
/**
 * HDLmJsonName short summary.
 *
 * HDLmJsonName description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmJsonName {
	/* An instance of this class is created to store the name
	   of a rule (or anything else) in an object. The object 
	   is then converted to JSON. */
	private String  name = null;
  /* This constructor takes a new rule (or anything else) name string */ 
	protected HDLmJsonName(final String newName) {
	  /* Check if the new rule (or anything else) name passed by the caller is null */
		if (newName == null) {
			String  errorText = "New name string passed to HDLmJsonName is null";
			throw new NullPointerException(errorText);
		}
		/* Set a field in the new rule (or anything else) name instance */
		name = newName;  			
	}
}
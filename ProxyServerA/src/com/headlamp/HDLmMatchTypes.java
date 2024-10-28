package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmMatchTypes short summary.
 *
 * HDLmMatchTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of matches supported by 
   this code. Many types of matches are supported. Of course,
   simple string equality is supported. The other types of 
   matches use the regex mechanisms to do the actual work. 
   
   This means that glob matching and like (SQL LIKE) matching
   are supported by converting a glob string or a like string
   to a regex string. The derived regex string is then compiled
   into a regex pattern. */
public enum HDLmMatchTypes {
	NONE(0),
	REGEX(1), 
	GLOB(2),
	LIKE(3);
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
		List.of("NONE", "REGEX", "GLOB", "LIKE"));	 
	/* Add a field to each enum */
	private final int enumValue;
	/* Provide a constructor for the enum */
	private HDLmMatchTypes(int intValue) {
		this.enumValue = intValue;
	}
	/* Return the integer value of the enum to the caller */
	protected int getValue() {
    return enumValue;
  }
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null.*/
  protected static HDLmMatchTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to match type conversion is null";
		  throw new NullPointerException(errorText);
		}
	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	  	return HDLmMatchTypes.valueOf(newType);
	  return HDLmMatchTypes.NONE;
  }
}
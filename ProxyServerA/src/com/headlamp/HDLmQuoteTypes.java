package com.headlamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
/**
 * HDLmQuoteTypes short summary.
 *
 * HDLmQuoteTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of quotes supported by this
   code. Three types of quotes are supported. Java uses double 
   quotes for strings and single quotes for characters. Other
   computer languages are more flexible. The only general rule
   is that anything that starts with one type of quote must 
   also end with that type of quote. */
public enum HDLmQuoteTypes {
	NONE(0), 
	DOUBLE(1),  
	SINGLE(2),
	ACCENT(3);
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
		List.of("NONE", "DOUBLE", "SINGLE", "ACCENT")); 
	/* Add a field to each enum */
	private final int enumValue;
	/* Provide a constructor for the enum */
	private HDLmQuoteTypes(int intValue) {
		this.enumValue = intValue;
	}
	/* Return the integer value of the enum to the caller */
	protected int getValue() {
    return enumValue;
  }
  /* We provide a non-standard routine for converting integers to 
     enum values. Note that if a matching enum is not found (for
     the integer passed by the caller), a null value is returned
     by this routine. */
	protected static HDLmQuoteTypes valueOfInteger(int newType) {
    /* Scan all of the enum values looking for a match */
    for (var enumValue : values()) {
      if (enumValue.getValue() == newType) 
        return enumValue;      
    }
    return null;
  }
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null. */
  protected static HDLmQuoteTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to quote type conversion is null";
		  throw new NullPointerException(errorText);
		}
	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	  	return HDLmQuoteTypes.valueOf(newType);
	  return HDLmQuoteTypes.NONE;
  }
}
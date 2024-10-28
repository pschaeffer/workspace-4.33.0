package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmResponseTypes short summary.
 *
 * HDLmResponseTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of response definitions supported
   by this code. The HTML type is used to show that HTML is being 
   returned to a (the) caller. The JSON type is used to show that
   JSON is being returned to a (the) caller. The text type is used
   to show that text is being returned to a (the) caller. */ 
public enum HDLmResponseTypes {
	NONE,
	HTML,
	JSON,
	TEXT;
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
    List.of("NONE", "HTML", "JSON", "TEXT"));	 
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null.*/
  protected static HDLmResponseTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to response definition type conversion is null";
		  throw new NullPointerException(errorText);
		}
  	newType = newType.toUpperCase();
  	if (typeValues.contains(newType)) 
  		return HDLmResponseTypes.valueOf(newType);
  	return HDLmResponseTypes.NONE;
  }
}
package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmProxyTypes short summary.
 *
 * HDLmProxyTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of proxy definitions supported
   by this code. The HTML type is used to modify HTML as it flows
   from and too the client (typically a browser). The inject type
   is used to add a JavaScript program to the HTML. The JavaScript
   program makes the actual changes to the web page. */
public enum HDLmProxyTypes {
	NONE,
	HTML,
	INJECT;
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
    List.of("NONE", "HTML", "INJECT"));	 
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null.*/
  protected static HDLmProxyTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to proxy definition type conversion is null";
		  throw new NullPointerException(errorText);
		}
  	newType = newType.toUpperCase();
  	if (typeValues.contains(newType)) 
  		return HDLmProxyTypes.valueOf(newType);
  	return HDLmProxyTypes.NONE;
  }
}
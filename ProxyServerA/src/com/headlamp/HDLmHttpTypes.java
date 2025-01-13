package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmHTTPTypes short summary.
 *
 * HDLmHTTPTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of HTTP operations supported 
   by the HTTP specification. Of course, we don't support all of
   these operations at this point. */
public enum HDLmHttpTypes {
	NONE,
	OPTIONS,
	GET,
	HEAD,
	POST,
	PUT,
	DELETE,
	TRACE,
	CONNECT,
	PATCH;
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
			List.of("NONE", "OPTIONS", "GET", "HEAD", "POST",
					    "PUT", "DELETE", "TRACE", "CONNECT", "PATCH"));	
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null.*/
	protected static HDLmHttpTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to tree type conversion is null";
		  throw new NullPointerException(errorText);
		}
	 newType = newType.toUpperCase();
	 if (typeValues.contains(newType)) 
	 	 return HDLmHttpTypes.valueOf(newType);
	 return HDLmHttpTypes.NONE;
	}
}
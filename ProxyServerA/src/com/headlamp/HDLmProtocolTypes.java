package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmProtocolTypes short summary.
 *
 * HDLmProtocolTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below specifies each of the protocol types we (hopefully)
   support. HTTP and HTTP2C are both clear-text protocols. HTTPS and 
   HTTP2 and both encrypted protocols. These enum values are used to
   pass and return protocol values, as need be. */
public enum HDLmProtocolTypes {
	NONE,
	HTTP,
	HTTPS,
	HTTP2,
	HTTP2C;
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
    List.of("NONE", "HTTP", "HTTPS", "HTTP2", "HTTP2C"));	 
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null.*/
  protected static HDLmProtocolTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to protocol definition type conversion is null";
		  throw new NullPointerException(errorText);
		}
  	newType = newType.toUpperCase();
  	if (typeValues.contains(newType)) 
  		return HDLmProtocolTypes.valueOf(newType);
  	return HDLmProtocolTypes.NONE;
  }
}
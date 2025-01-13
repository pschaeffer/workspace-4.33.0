package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmCacheTypes short summary.
 *
 * HDLmCacheTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the cache values (caching of rule
   definitions) that are supported by this code. Many cache
   values are supported. The set of supported values is 
   listed below. Note that only the listed values are supported.
   Note also, that NONE and NEVER are not the same thing. 
   
   UPDATE means that whenever the rules are changed, the cache
   should be bypassed and new rules obtained. Note, that simply
   reading the rules, is not an update to the rules. 
   
   NONE means that no supported cache value has been specified.
   Never means that rules will not be cached. */ 
public enum HDLmCacheTypes {
	NONE(0),
	NEVER(1), 
	TIME(2),
	COUNT(3),
	ALWAYS(4),
	UPDATE(5);
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
		List.of("NONE", "NEVER", "TIME", "COUNT", "ALWAYS", "UPDATE"));	 
	/* Add a field to each enum */
	private final int enumValue;
	/* Provide a constructor for the enum */
	private HDLmCacheTypes(int intValue) {
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
  protected static HDLmCacheTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to cache type conversion is null";
		  throw new NullPointerException(errorText);
		}
	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	  	return HDLmCacheTypes.valueOf(newType);
	  return HDLmCacheTypes.NONE;
  }
}
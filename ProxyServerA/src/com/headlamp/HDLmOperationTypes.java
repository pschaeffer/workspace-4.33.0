package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmOperationTypes short summary.
 *
 * HDLmOperationTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines a set of operations that can be 
   requested by a caller. Many types of operations are 
   possible. New types of operations can added at any
   time. */
public enum HDLmOperationTypes {
	NONE,
	DEFAULT;
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
			List.of("NONE", "DEFAULT"));	
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null.*/
	protected static HDLmOperationTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to operation type conversion is null";
		  throw new NullPointerException(errorText);
		}
	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	 	  return HDLmOperationTypes.valueOf(newType);
	  return HDLmOperationTypes.NONE;
	}
}
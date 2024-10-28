package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmSystemTypes short summary.
 *
 * HDLmSystemTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of systems supported by the
   product. The product supports a production system and a test
   system. Only one each is really supported. */ 
public enum HDLmSystemTypes {
	NONE(0),
	PROD(1),
	TEST(2);
  private static final ArrayList<String>  typeValues = new ArrayList<String>(
	  List.of("NONE", "PROD", "TEST"));	 
	/* Add a field to each enum */
	private final int enumValue;
	/* Provide a constructor for the enum */
	private HDLmSystemTypes(int intValue) {
		this.enumValue = intValue;
	}
  /* We provide a non-standard routine for converting strings to 
	   enum values. This routine converts the input string value
	   to upper case and checks the value first. The string passed
	   by the caller must not be null.*/
	protected static HDLmSystemTypes valueOfString(String newType) {
		/* Check a value passed by the caller */
		if (newType == null) {
		  String  errorText = "String passed to system type conversion is null";
		  throw new NullPointerException(errorText);
		}
		/* Convert the passed value to upper case */
 	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	 	  return HDLmSystemTypes.valueOf(newType);
	  return HDLmSystemTypes.NONE;
	}
}
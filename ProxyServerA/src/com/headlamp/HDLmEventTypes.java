package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmEventTypes short summary.
 *
 * HDLmEventTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of events supported
   by this code. Many types of events are supported. The
   types of events are specified below. */ 
public enum HDLmEventTypes {
	NONE(0),
	GENERAL(1),
	MOD(2);  
	private static final ArrayList<String>  typePrint = new ArrayList<String>(
	  List.of("None", "General", "Modification"));	 
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
		List.of("NONE", "GENERAL", "MOD"));	 
	/* Add a field to each enum */
	private final int enumValue;
	/* Provide a constructor for the enum */
	private HDLmEventTypes(int intValue) {
		this.enumValue = intValue;
	}
	/* Return the integer value of the enum to the caller */
	protected int getValue() {
    return enumValue;
  }
	/* The routine below returns a printable string for each 
	   possible enum value. The value returned by the caller
	   will always be a string that can be printed or used
	   to build HTML. */ 
	protected String       toPrint() {
		/* Get the integer value associated with each type value */
		int   intValue = this.getValue();
		/* Make sure that the integer value is in range */
		if (intValue < 0 || intValue >= typePrint.size()) {
			String  errorFormat = "Integer value of enum, out-of-range (%d)";
			String  errorText = String.format(errorFormat, intValue);  
      throw new RuntimeException(errorText);   
		}		
	  return typePrint.get(intValue);	
	}	 
  /* We provide a non-standard routine for converting integers to 
     enum values. Note that if a matching enum is not found (for
     the integer passed by the caller), a null value is returned
     by this routine. */
	protected static HDLmEventTypes valueOfInteger(int newType) {
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
  protected static HDLmEventTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to event type conversion is null";
		  throw new NullPointerException(errorText);
		}
	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	  	return HDLmEventTypes.valueOf(newType);
	  return HDLmEventTypes.NONE;
  }
}
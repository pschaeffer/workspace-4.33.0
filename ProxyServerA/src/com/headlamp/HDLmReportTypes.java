package com.headlamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
/**
 * HDLmReportTypes short summary.
 *
 * HDLmReportTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of reports supported by this
   code. Many types of reports are supported.  */
public enum HDLmReportTypes {
	NONE(0),
	@SerializedName("Check website")
	CHECKWEBSITE(1), 
	@SerializedName("Check error")
	CHECKERROR(2);
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
		List.of("NONE", "CHECKWEBSITE", "CHECKERROR"));	 
	/* Add a field to each enum */
	private final int enumValue;
	/* Provide a constructor for the enum */
	private HDLmReportTypes(int intValue) {
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
	protected static HDLmReportTypes valueOfInteger(int newType) {
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
  protected static HDLmReportTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to report type conversion is null";
		  throw new NullPointerException(errorText);
		}
	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	  	return HDLmReportTypes.valueOf(newType);
	  return HDLmReportTypes.NONE;
  }
}
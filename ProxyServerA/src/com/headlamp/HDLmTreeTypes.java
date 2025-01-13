package com.headlamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
/**
 * HDLmTreeTypes short summary.
 *
 * HDLmTreeTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of nodes supported by this
   code. Many types of nodes are supported. We have one and
   only one top-level node. Of course, we can have many
   company, division, site, and modification level nodes. 
   Note that the enum value for modification level nodes 
   is mod, not modification. */
public enum HDLmTreeTypes {
	NONE(0),
	@SerializedName("top")
	TOP(1), 
	@SerializedName("company")
	COMPANY(2),
	@SerializedName("division")
	DIVISION(3),
	@SerializedName("site")
	SITE(4),
	@SerializedName("mod")
	MOD(5),
	@SerializedName("config")
	CONFIG(6),
	@SerializedName("store")
	STORE(7),
	@SerializedName("list")
	LIST(8),
	@SerializedName("ignore")
	IGNORE(9),
	@SerializedName("reports")
  REPORTS(10), 
  @SerializedName("report")
  REPORT(11),
  @SerializedName("line")
  LINE(12),
  @SerializedName("lines")
  LINES(13),
  @SerializedName("lists")
  LISTS(14),
  @SerializedName("companies")
  COMPANIES(15),
  @SerializedName("rules")
  RULES(16),
  @SerializedName("data")
  DATA(17),
  @SerializedName("value")
  VALUE(18);
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
		List.of("NONE", "TOP", "COMPANY", "DIVISION", "SITE",
				    "MOD", "CONFIG", "STORE", "LIST", "IGNORE",
				    "REPORTS", "REPORT", "LINE", "LINES", "LISTS",
				    "COMPANIES", "RULES", "DATA", "VALUE"));	 
	/* Add a field to each enum */
	private final int enumValue;
	/* Provide a constructor for the enum */
	private HDLmTreeTypes(int intValue) {
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
	protected static HDLmTreeTypes valueOfInteger(int newType) {
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
  protected static HDLmTreeTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to tree type conversion is null";
		  throw new NullPointerException(errorText);
		}
	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	  	return HDLmTreeTypes.valueOf(newType);
	  return HDLmTreeTypes.NONE;
  }
}
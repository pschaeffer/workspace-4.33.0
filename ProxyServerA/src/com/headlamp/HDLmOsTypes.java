package com.headlamp;
import java.util.ArrayList;
import java.util.List;
/**
 * HDLmOsTypes short summary.
 *
 * HDLmOsTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of operating systems (OSs)
   supported by this code. The OS type is used to determine where
   files are stored (among other things). The Windows type refers
   to any version of Windows. The macOS type refers to any 
   version of macOS. The Linux type refers to any version of 
   Linux and any version of Unix. */
public enum HDLmOsTypes {
	NONE,
	WINDOWS,
	MACOS,
	LINUX;
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
    List.of("NONE", "WINDOWS", "MACOS", "LINUX"));	 
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null.*/
  protected static HDLmOsTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to OS definition type conversion is null";
		  throw new NullPointerException(errorText);
		}
  	newType = newType.toUpperCase();
  	if (typeValues.contains(newType)) 
  		return HDLmOsTypes.valueOf(newType);
  	return HDLmOsTypes.NONE;
  }
}
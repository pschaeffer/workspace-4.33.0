package com.headlamp;

import java.util.ArrayList;
import java.util.List;

/**
 * HDLmPassThruStatus short summary.
 *
 * HDLmPassThruStatus description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if pass-through mode is
   in use or not. In some cases, we just want all Internet requests
   to pass-through the proxy server. In other cases, we want all the
   current modifications applied to each Internet operations. */
public enum HDLmPassThruStatus {
	NONE,
	PASSTHRUOK,
	PASSTHRUNOTOK;
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
			List.of("NONE", "PASSTHRUOK", "PASSTHRUNOTOK"));	
  /* We provide a non-standard routine for converting strings to 
	   enum values. This routine converts the input string value
	   to uppercase and checks the value first. The string passed
	   by the caller must not be null. */
	protected static HDLmPassThruStatus valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to tree type conversion is null";
		  throw new NullPointerException(errorText);
		}
	  newType = newType.toUpperCase();
	  if (typeValues.contains(newType)) 
	 	  return HDLmPassThruStatus.valueOf(newType);
	  return HDLmPassThruStatus.NONE;
	}
}
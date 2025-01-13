package com.headlamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
/**
 * HDLmState short summary.
 *
 * HDLmState description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmState {	
	/* This class can never be instantiated */
	private HDLmState() {}
	/* Define a few state values for use in our code.
	   Defining the map does not initialize it. However,
	   the initialization call (to a static routine) does
	   initialize the map. */  
	private static Map<String, String>  stateValues = HDLmState.stateValueInitialization();
	/* This static method returns the string value of a state
	   value if the state name is valid (exists) and if the 
	   state value is actually a string (not a number or a
		 boolean). If the state does not exist, this routine
		 does not fail. Instead, a null value is returned to
		 the caller. */ 
	protected static String checkString(String stateName) {
		if (stateName == null) {
			String   errorText = "State name reference passed to checkString is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the state name passed by the caller is valid
	     or not. We need to return a null value if the state name
	     passed by the caller is unknown. */
		if (!stateValues.containsKey(stateName))  
			return null;
		return HDLmState.getString(stateName);
  }
	/* This static method returns the string value of a state
	   value if the state name is valid (exists) and if the 
		 state value is actually a string (not a number or a
		 boolean). */
	protected static String getString(String stateName) {
		if (stateName == null) {
			String   errorText = "State name reference passed to getString is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the state name passed by the caller is valid
	     or not. We need to raise an exception if the state name
	     passed by the caller is unknown. */
		if (!stateValues.containsKey(stateName)) {
			String  errorText = String.format("Invalid state name (%s) passed to getString", stateName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Get the value from the Map and check if the value is a number. This 
		   method can not be used to obtain values that are actually numbers. */
		String rv = stateValues.get(stateName);
		if (StringUtils.isNumeric(rv)) {
			String  errorText = String.format("Value of state name (%s) is numeric", stateName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);			
		}
		/* Check if the value is one of the known boolean values. Report an 
		   error if we find a boolean value. */
		if (rv.equals("false") || rv.equals("true")) {
			String  errorText = String.format("Value of state name (%s) is a boolean", stateName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);			
		}
		return rv;
	}
	/* This static method sets the string value of a state
	   to the value passed by the caller. If the state name
	   does not exist, it is created. */
	protected static void  setName(final String stateName,
                                 final String stateValue) {
		/* Check if the state name passed by the caller is null */
		if (stateName == null) {
		  String   errorText = "State name reference passed to setName is null";
		  throw new NullPointerException(errorText);			
		}
		/* Check if the state value (the new value) passed by the
		   caller is null */
		if (stateValue == null) {
		  String   errorText = "State value reference passed to setName is null";
		  throw new NullPointerException(errorText);			
		}
		/* Check if the state name passed by the caller is valid
		   or not. Add the state name, if it is not found the map
		   of states and values. */
		if (!stateValues.containsKey(stateName))
		  stateValues.putIfAbsent(stateName, "");
		/* Invoke the standard routine to add the final state value */
		HDLmState.setString(stateName, stateValue);
	}
	/* This static method sets the string value of a state
     value if the state name is valid (exists) and if the 
		 state value is actually a string (not a number or a 
		 boolean). The caller provides the new state value. */
	protected static void  setString(final String stateName,
			                             final String stateValue) {
		/* Check if the state name passed by the caller is null */
		if (stateName == null) {
			String   errorText = "State name reference passed to setString is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the state value (the new value) passed by the
		   caller is null */
		if (stateValue == null) {
			String   errorText = "State value reference passed to setString is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the state name passed by the caller is valid
	     or not. We need to raise an exception if the state name
	     passed by the caller is unknown (not in the current map
	     of state names and values). */
		if (!stateValues.containsKey(stateName)) {
			String      errorText = String.format("Invalid state name (%s) passed to setString", stateName);
			Exception   exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Use the value passed by the caller and check if the value is a number. 
		   This method can not be used to set values that are actually numbers. */
		String  nv = stateValue;
		if (StringUtils.isNumeric(nv)) {
			String  errorText = String.format("New value of state name (%s) is numeric", stateName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);			
		}
		/* Check if the value is one of the known boolean values. Report an 
		   error if we find a boolean value. */
		if (nv.equals("false") || nv.equals("true")) {
			String  errorText = String.format("New value of state name (%s) is a boolean", stateName);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);			
		}
		/* Store the new state value passed by the caller */
		stateValues.put(stateName, stateValue);
	}
	/* This routine is used to initialize the state values map. This
	   routine builds a local map that is returned to the caller. The
	   local map already has the one required value. */ 
  private static Map<String, String>  stateValueInitialization() {
  	/* Build a new local map with one entry. The new local map is 
  	   returned to the caller and used as the permanent state values. */
  	Map<String, String>   localMap = new HashMap<String, String>(  			
  	  Map.ofEntries(  		  
  		  Map.entry("eventsAdded",    "no" ),
  		  Map.entry("http3Connected", "no" ),
  		  Map.entry("logChanges",     "yes"),
  		  Map.entry("mainExecuted",   "no" ),
  		  Map.entry("systemValue",    "a"  )));
  	return localMap;  	 
  }  
}
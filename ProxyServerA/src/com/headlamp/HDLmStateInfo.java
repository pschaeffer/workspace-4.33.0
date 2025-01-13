package com.headlamp;
/**
 * HDLmStateInfo short summary.
 *
 * HDLmStateInfo description.
 *
 * @version 1.0
 * @author Peter
 */
/* No instances of this class are ever created. The state values
   are obtained from a variety of sources. */
public class HDLmStateInfo {
	/* This class can never be instantiated */
	private HDLmStateInfo() {}  
  /* Get the 'http3Connected' (without the quotes) value from 
	   the state map. This value shows if the HTTP/3 connector has
	   has been built and added or not. The HTTP/3 connected value
	   is returned to the caller as Java string, not as a Java 
	   character. */ 
	protected static String  getHttp3Connected() {
	  return HDLmState.getString("http3Connected");
	}
  /* Get the 'eventsAdded' (without the quotes) value from 
	   the state map. This value shows if all of the events 
	   have been added to the map or not. In some parts of 
	   initialization, this will not be true. Later the 
	   events will be added to the events map. The events
	   added value is returned to the caller as Java string,
	   not as a Java character. */ 
	protected static String  getEventsAdded() {
	  return HDLmState.getString("eventsAdded");
	}
  /* Get the 'logChanges' (without the quotes) value from 
 	   the state map. This value shows if changes should be 
	   logged or not. The log changes value is returned 
	   to the caller as Java string, not as a Java character. */ 
	protected static String  getLogChanges() {
	 return HDLmState.getString("logChanges");
	}
  /* Get the 'mainExecuted' (without the quotes) value from 
     the state map. This value shows if the main routine has
     been executed or not. The main executed value is returned 
     to the caller as Java string, not as a Java character. */ 
	protected static String  getMainExecuted() {
	  return HDLmState.getString("mainExecuted");
	}
  /* Get the system suffix value that follows the standard
     suffix. This is typically just one character, such as
     'a' or 'b' or 'c' (without the quotes). The system suffix 
     is returned to the caller as Java string, not as a Java
     character. 
     
     This value is used to build the key that is used to 
     obtain data from a database. This value follows the 
     standard suffix. */ 
	protected static String  getSystemValue() {
	  return HDLmState.getString("systemValue");
	}
  /* Set the system suffix value that follows the standard
	   suffix. This is typically just one character, such as
	   'a' or 'b' or 'c' (without the quotes). The system suffix 
	   is set from the string passed by the caller. The actual 
	   value is a Java string, not a Java character. 
	  
	   This value is used to build the key that is used to 
	   obtain data from a database. This value follows the 
	   standard suffix. */ 
	protected static void  setEntriesSystemValue(final String newValue) {
		/* Check if the new state value passed by the caller is null */
		if (newValue == null) {
			String  errorText = "New state value reference passed to setEntriesSystemValue is null";
			throw new NullPointerException(errorText);			
		}
	  HDLmState.setString("systemValue", newValue);
	}
}
package com.headlamp;
import java.util.ArrayList;
/**
 * HDLmDatabaseId short summary.
 *
 * HDLmDatabaseId description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */ 
public class HDLmDatabaseId {
  /* This class can be instantiated using the
     constructor below */
	protected HDLmDatabaseId(final String idValue) {
		/* Check if the ID value passed by the caller is null */
		if (idValue == null) {
			String  errorText = "ID value passed to HDLmDatabaseId is null";
			throw new NullPointerException(errorText);
		}	
		/* Set a field in the new instance */ 
		id = idValue; 
	} 	
	/* The row ID value is specified using the field below */ 
	private String  id; 
}
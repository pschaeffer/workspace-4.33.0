package com.headlamp;
import java.util.ArrayList;
/**
 * HDLmDatabaseIds short summary.
 *
 * HDLmDatabaseIds description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */ 
public class HDLmDatabaseIds {
  /* This class can be instantiated using the
     constructor below */
	protected HDLmDatabaseIds(final ArrayList<HDLmDatabaseId> rowIdList) {
		/* Check if the row ID list value passed by the caller is null */
		if (rowIdList == null) {
			String  errorText = "Row list value passed to HDLmDatabaseIds is null";
			throw new NullPointerException(errorText);
		}		 
		/* Set a few fields in the new instance */
		data = rowIdList;
  }	
	/* The data values (actual row IDs) are specified using the field below */ 
	private ArrayList<HDLmDatabaseId>   data = null;
	/* Get the row ID list (data) and return it to the caller */
	protected ArrayList<HDLmDatabaseId>  getData() {
		return this.data;		
	}
}
package com.headlamp;
import java.util.ArrayList;
/**
 * HDLmDatabaseRows short summary.
 *
 * HDLmDatabaseRows description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */ 
public class HDLmDatabaseRows {
  /* This class can be instantiated using the constructor below */
	protected HDLmDatabaseRows() {		 
		/* Set a few fields in the new instance */
		rows_returned = 0;
		data = new ArrayList<HDLmDatabaseRow>();
  }	
  /* This class can be instantiated using the constructor below */
	protected HDLmDatabaseRows(final ArrayList<HDLmDatabaseRow> rowList) {
		/* Check if the row list value passed by the caller is null */
		if (rowList == null) {
			String  errorText = "Row list value passed to HDLmDatabaseRows is null";
			throw new NullPointerException(errorText);
		}		 
		/* Set a few fields in the new instance */
		rows_returned = rowList.size();
		data = rowList;
  }	
	/* The number of rows returned is specified using the field below */ 
	private int                         rows_returned; 
	/* The data values (actual rows) are specified using the field below */ 
	private ArrayList<HDLmDatabaseRow>  data = null;
	/* Get the row list (data) and return it to the caller */
	protected ArrayList<HDLmDatabaseRow>  getData() {
		return this.data;		
	}
}
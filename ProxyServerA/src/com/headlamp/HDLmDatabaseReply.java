package com.headlamp;
import java.util.ArrayList;
/**
 * HDLmDatabaseReply short summary.
 *
 * HDLmDatabaseReply description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */ 
public class HDLmDatabaseReply {
  /* This class can be instantiated using the
     constructor below */
	protected HDLmDatabaseReply(final ArrayList<HDLmDatabaseId> rowIdList, 
			                        final int retcodeValue) {
		/* Check if the row ID list value passed by the caller is null */
		if (rowIdList == null) {
			String  errorText = "Row ID list value passed to HDLmDatabaseReply is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the return code value passed by the caller is invalid */
		if (retcodeValue < 0) {
			String  errorText = "Return code value passed to HDLmDatabaseReply is invalid";
			throw new IllegalArgumentException(errorText);
		}	
		/* Set a few fields in the new instance */
		rows_affected = rowIdList.size();
		retcode = retcodeValue;
		data = rowIdList;
  }	
	/* The number of rows returned is specified using the field below */ 
	private int                         rows_affected; 
	/* The return code is specified using the field below */ 
	private int                         retcode; 
	/* The data values (actual ID values) are specified using the field below */ 
	private ArrayList<HDLmDatabaseId>   data = null;
	/* Get the row ID list (data) and return it to the caller */
	protected ArrayList<HDLmDatabaseId>  getData() {
		return this.data;		
	}
}
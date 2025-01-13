package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmUnRe short summary.
 * 
 * HDLmUnRe description.
 * 
 *
 * @version 1.0
 * @author Peter 
 */
/**
 * Class for supporting undo and redo processing.      
 * 
 * Zero for any field means that no undo/redo operation 
 * has been recorded
 * 
 * The JavaScript comments for HDLmUnRe.js may be helpful
 * in understanding this code
 * 
 * Send messages to all Java instances telling them to reload the rules
 *   from the database 
 * Consider the multi-user case. Undo should apply to the current user.
 *   Not all the users.
 * insertTableRows may not be passed company and other things 
 * If more than one party is making change, than an undo operation
 *   my fail (at least in part)
 * We need memory barriers in the multi-user case (perhaps we should
 *   use volatile, locks, or synchronized)
 */
public class HDLmUnRe {
	/* The next statement initializes logging to some degree. Note that
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmUnRe.class);
  /* The next field is used to keep track of the number of unto/redo
     operations that have recorded. This value starts with zero, which
     means that no undo/redo operations have been recorded. Note that 
     an integer (32-bits) is used to keep track of how many operations 
     have been recorded. The actual number should not exceed the roughly
     2 billion limit. This value only goes up. One is added to this 
     value each time an undo/redo operation is recorded. */
  private static int                  unReCount   = 0;
  /* The next field is the highest numbered undo/redo operation that 
     actually exists. This value can be greater than the current    
     undo/redo number or it can be the same. Consider the case where
     40 undo/redo operations have been recorded. Of these 40, 20 have
     actually been undone. In this case, the highest numbered ('Top',
     without the quotes) undo/redo operation will be 40. 20 operations
     can be redone. */ 
  private static int                  unReTop     = 0;
  /* The next field is the lowest numbered undo/redo operation that 
		 actually exists. This value can be zero, if no undo/redo oper-
		 ations have been recorded. This value is reset if an undo/redo
		 operation has to be thrown away because the undo/redo ArrayList
		 is full. */ 
  private static int                  unReBottom  = 0;
  /* The next field is the current numbered undo/redo operation that 
	   actually exists. This value can be zero, if no undo/redo oper-
	   ations have been recorded. This value (if it is not zero) is 
	   the current undo/redo operation that will be processed or undone.
	   This value can be much less than the highest numbered undo/redo
	   operation. For example, if 40 undo/redo operations have been
	   recorded, but only 20 have been undone, the current undo/redo
	   operation will be 20. This means that 20 operations can be redone. 
	   and 20 operations can be undone. We need to consider two speical 
	   cases. First, say 40 undo/redo operations are recorded. The top
	   value will be 40. The bottom value will be one. If all 40 oper-
	   ations are undone, then the current value will be (re)set to 
	   zero, showing that there is nothing left to be undone. Second,
	   say 40 undo/redo operations are recorded. 20 operations are then
	   undone. This will cause the current value to be set to 20. If 
	   a new undo/redo operation is recorded, then the top and current 
	   numbers will be (re)set to 21. As a practical matter some 20
	   undo/redo operations will be thrown away, as soon as a new
	   undo/redo operation is started. */
	private static int                  unReCurrent = 0;
	/* The next field is used to keep track of each undo/redo operation
	   that has been recorded. This field is a reference to an ArrayList. */
  private static ArrayList<HDLmUnRe>  unReList = null;
  /* The next field point to the next undo/redo operation. These 
     operations are linked together in a list. The list (or chain)
     defines a set of undates to the node tree. */  
  private HDLmUnRe                    unReNext = null;
  /* The next field shows what type of system the undo/redo 
     object is associated with */
  private HDLmSystemTypes             unReSystemType = null;  
	/* The next field has the name of the target system */
	private String                      unReSystemName = null;
	/* The next field shows what type of operation the undo/redo object is
	   associated with. Note that this is the original type of operation.
	   This type of operation has to be reversed for an undo. */
  private HDLmDatabaseTypes           unReDatabaseOperationType = null; 
  /* The next field contain the change number associated with this undo/redo
	   operation. This is the change number that is used to undo the operation.
	   This number will alway be a positive integer. */
  private int                         unReChangeNumber = 0;
	/* The next field has the database rows changed by tne current 
	   operation. The operation might be an insert or it might be 
	   a deleete. */ 
  private ArrayList<HDLmDatabaseRow>  unReRowList = null;
  /* This is the standard (and major) constructor for this class. Build  
     an undo/redo object. This constructor does not put the object 
     instance on the undo/redo chain. */
  protected HDLmUnRe(final HDLmSystemTypes systemType,
  		               final String systemName,
  		               final HDLmDatabaseTypes databaseOperationType,
  		               final int changeNumber,
  		               final ArrayList<HDLmDatabaseRow> rowList) {
		/* Check a few values passed by the caller */
		if (systemType == null) {
			String  errorText = "System type value passed to undo/redo constructor is null";
			throw new NullPointerException(errorText);
		}
		if (systemType == HDLmSystemTypes.NONE) { 
		  String  errorFormat = "System type (%s) value passed to undo/redo constructor is invalid";
		  String  errorText = String.format(errorFormat, systemType.toString());
		  throw new IllegalArgumentException(errorText); 
		}
		if (systemName == null) {
			String  errorText = "System name value passed to undo/redo constructor is null";
			throw new NullPointerException(errorText);
		}
		if (databaseOperationType == null) {
			String  errorText = "Database operation type  passed to undo/redo constructor is null";
			throw new NullPointerException(errorText);
		}
		if (databaseOperationType == HDLmDatabaseTypes.NONE) { 
		  String  errorFormat = "Database operation type (%s) value passed to undo/redo constructor is invalid";
		  String  errorText = String.format(errorFormat, databaseOperationType.toString());
		  throw new IllegalArgumentException(errorText); 
		}
		if (changeNumber <= 0) {
			String errorFormat = "Integer value (%d) for change number passed to undo/redo constructor is invalid";
			String errorText = String.format(errorFormat, changeNumber);
			throw new IllegalArgumentException(errorText);
		}
		if (rowList == null) {
			String  errorText = "Row list passed to undo/redo constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Store a few values */
	  this.unReSystemType = systemType;
	  this.unReSystemName = systemName;
	  this.unReDatabaseOperationType = databaseOperationType;
	  this.unReChangeNumber = changeNumber;
		this.unReRowList = rowList;
	}
	/* This routine gets the number of the oldest undo/redo operation
	   that can potentially be undone. This number will either be zero
	   (if no undo/redo operations have been recorded) or a positive integer.
	   Undo/redo operations are recorded in a fixed-size ArrayList. As a 
	   consequence, eventually the ArrayList will fill up and then wrap.
	   New undo/redo operation will overwrite very old undo/redo operations.
	   The bottom undo/redo operation number must be adjusted as a consequence. */
	public static int      unReGetBottom() {
		return unReBottom;
	}
	/* This routine gets the count of undo/redo operations. This is the number
 	   undo/redo operations that have been recorded (if any). This number will
 	   either be zero (if no undo/redo operations have been recorded) or a 
 	   positive integer. */
	public static int      unReGetCount() {
		return unReCount;
	}
	/* This routine gets the current undo/redo operation number. This is the  
	   number of the last undo/redo operation that was recorded (if any). This 
	   number will either be zero (if no undo/redo operations have been recorded)
	   or a positive integer. */
	public static int      unReGetCurrent() {
		return unReCurrent;
	}
	/* This routine gets the number of the newest undo/redo operation
	   that can potentially be undone. This number will either be zero
	   (if no undo/redo operations have been recorded) or a positive integer.
	   Undo/redo operations are recorded in a fixed-size ArrayList. As a 
	   consequence, eventually the ArrayList will fill up and then wrap.
	   The top undo/redo operation may or may not be the current undo/redo
	   operation. 
	   
	   For example, if the undo/redo Arraylist is initially empty and 70
	   undo/redo operations are recorded, the top value will be 70. The 
	   bottom value will be 1 and the current value will be 70. If 10 
	   undo/redo operations are undone, the current value will be 60. The
	   top value will still be 70. The bottom value will still be 1. If
	   a new undo/redo operation is recorded, the top value will be 61.  
	   The bottom value will still be 1 and the current value will be 61.
	   In this example, 10 undo/redo operations will be thrown away, numbered
	   61 to 70. */   
	public static int      unReGetTop() {
		return unReTop;
	}
  /* This routine starts an undo/redo operation. This routine is called 
	   at the very start of an undo/redo operation. This type of operation
	   records a set of changes to the node tree. */
	protected static void  unReAddOperation(final HDLmUnRe newUnRe) {
		/* Check a few values passed by the caller */
		if (newUnRe == null) {
			String  errorText = "New undo/redo object passed to unReAddOperation is null";
			throw new NullPointerException(errorText);
		}
		/* Get the size of the undo/redo ArrayList */
		int  unReLimit = HDLmConfigInfo.getUnReLimit();
		/* Get the index of the current undo/redo operation */
		int  unReIndex = (unReCurrent - 1) % unReLimit;
		/* Put all of the existing undo/redo operations in the undo/redo chain
		   after the current undo/redo operation. */
		newUnRe.unReNext = unReList.get(unReIndex);
		/* Put the new undo/redo operation in the undo/redo ArrayList */
		unReList.set(unReIndex, newUnRe);	 
	} 
  /* This routine starts an undo/redo operation. This routine is called 
	   at the very start of an undo/redo operation. This type of operation
	   records a set of changes to the node tree. This routine returns a 
	   transaction number (change number) to the caller. */
	protected static int   unReStartOperation() {
		/* The count of undo/redo operations is always increased by one */
		unReCount++;
		/* Increment the current undo/redo operation number and reset 
		   the top value to the current value */ 
		unReCurrent++;
		unReTop = unReCurrent;
		/* Get the size of the undo/redo ArrayList */
		int  unReLimit = HDLmConfigInfo.getUnReLimit();
		/* Get the index of the current undo/redo operation */
		int  unReIndex = (unReCurrent - 1) % unReLimit;
		/* Set the current entry in to the undo/rodo ArrayList to 
		   null */		
		unReList.set(unReIndex, null);
		/* The bottom value may need to be set of reset */
		if (unReBottom == 0) {
			unReBottom = 1;
		}
		/* Check if any undo/redo operations need to be thrown away. 
		   This happens when the undo/redo ArrayList is full. */
		else {
			int  tempBottom = unReCurrent - unReLimit + 1;
			if (tempBottom > unReBottom) 
				unReBottom = tempBottom;
		}		 
		/* Return the current undo/redo operation number 
		   to the caller. This is the latest change we 
		   really care about (possible to undo). Consider
		   the following case. Say 70 changes are completed.
		   10 changes (in reverse order) are then undone.
		   This would make the 'current' change number 60.
		   The next change would be 61. This change can be 
		   undone as long as no other changes are added 
		   after change number 61. */
		return unReCurrent;
	}   
  /* Initialize undo/redo processing. This routine is only called 
     once as part of startup. */ 
  protected static void  unReStartup(final int unReLimit) {
  	/* Check if the undo/redo limit is invalid */
	  if (unReLimit <= 0) {
		  String  errorFormat = "Integer value (%d) for undo/redo limit passed to unReStartup is invalid";
		  String  errorText = String.format(errorFormat, unReLimit);
		  throw new IllegalArgumentException(errorText);
	  }
	  /* Allocate the ArrayList used to keep track of undo/redo operations */
	  ArrayList<HDLmUnRe>   tempArrayList = new ArrayList<HDLmUnRe>();
		/* Check if the undo/redo ArrayList was properly allocated */ 
		if (tempArrayList == null) {
			String  errorText = "Undo/redo ArrayList was not allocated in unReStartup";
			HDLmAssertAction(false, errorText);
		}
		/* Add the specified number of null entries to the undo/redo ArrayList */
	  int   i;
	  for (i = 0; i < unReLimit; i++) {
	    tempArrayList.add(null);
	  }
	  /* Save the undo/redo ArrayList reference */  
	  unReList = tempArrayList;
	}  
  /* This routine undoes the 'current' undo/redo operation. This routine
     will generally undo the last operation that was recorded. However,
     that will not always be the case. */
	protected static String  unReUnDoOperation(final Integer changeNumber) {
		/* Check a few values passed by the caller */
		if (changeNumber == null) {
			String  errorText = "Change number passed to unReUnDoOperation is null";
			throw new NullPointerException(errorText);
		}
  	/* Check if the undo/redo change number is invalid */
	  if (changeNumber <= 0) {
		  String  errorFormat = "Change number (%d) for undo/redo passed to unReUnDoOperation is invalid";
		  String  errorText = String.format(errorFormat, changeNumber);
		  throw new IllegalArgumentException(errorText);
	  }
		/* Set the initial error message */
		String  errorMessage = null;
		/* Check if we have any undo/redo recorded operations to 
		   undo. This may not be true. */ 
		if (unReCurrent <= 0) {
			errorMessage = "No undo/redo operations to undo";
	    return errorMessage;
		}	
		/* Check if the change number passe by the caller is the 
		   last change operation record. We can only undo the last
		   change operation. */
		if (changeNumber != unReCurrent) {
			String  errorFormat = "The change number passed by the caller (%d) is not the current change number (%d)";
			String  errorText = String.format(errorFormat, ((int) changeNumber), unReCurrent);			
			return errorText;
		}	 
		/* Get the size of the undo/redo ArrayList */
		int  unReLimit = HDLmConfigInfo.getUnReLimit();
		/* Get the index of the current undo/redo operation */
		int  unReIndex = (unReCurrent - 1) % unReLimit;
		/* Get the first element in the undo/redo chain */
		HDLmUnRe  unReFirst = unReList.get(unReIndex);
		HDLmUnRe  unReNext = unReFirst;
		if (unReFirst == null) {
			String  errorText = "First entry is null in undo/redo chain";
			HDLmAssertAction(false, errorText);
		}
		/* Process all the element in the undo/redo chain */
		while (true) {
			/* Check for a delete operation */
			if (unReNext.unReDatabaseOperationType == HDLmDatabaseTypes.DELETE) {
				/* Build the set of columns to be inserted */
				HashSet<String>   insertRowKeys = new HashSet<String>();
				/* Check if the insert row keys set was properly allocated */ 
				if (insertRowKeys == null) {
					String  errorText = "Insert row keys set was not allocated in unReUnDoOperation";
					HDLmAssertAction(false, errorText);
				}					
				insertRowKeys.add("content");
				insertRowKeys.add("info");
				insertRowKeys.add("name");
				insertRowKeys.add("company");
				insertRowKeys.add("report");
				if (unReNext.unReRowList.size() > 0)
				  HDLmDatabase.insertTableRowsSystem(unReNext.unReRowList,
					  	                               insertRowKeys,				                        		 
						                                 unReNext.unReSystemName);
			}
			/* Check for an insert operation */
			if (unReNext.unReDatabaseOperationType == HDLmDatabaseTypes.INSERT) {
				if (unReNext.unReRowList.size() > 0)
			  	HDLmDatabase.deleteTableRowsSystem(unReNext.unReRowList, 
                                             unReNext.unReSystemName);
			}
			unReNext = unReNext.unReNext;
			if (unReNext == null)  
				break;			 
		}
		/* Decrement the current undo/redo operation number */
		unReCurrent--;
		/* Show that the database has been updated */
		HDLmDatabase.setRulesHaveBeenUpdated(true);
		/* Return the null error message */ 
		return errorMessage;
	}
}
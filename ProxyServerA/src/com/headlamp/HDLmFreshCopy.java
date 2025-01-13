package com.headlamp;
/**
 * HDLmFreshCopy short summary.
 *
 * HDLmFreshCopy description.
 *
 * @version 1.0
 * @author Peter
 */ 
/* The enum below provides values showing if a fresh copy
   of all of the modifications should be obtained or not.
   If this values is set to no, then a fresh copy will not
   be obtained and the existing modifications will be used.
   If this value is set to yes, then a fresh copy of the 
   modifications will be obtained from the database. */
public enum HDLmFreshCopy {
	NONE,
	FRESHCOPYNO,
	FRESHCOPYYES
}
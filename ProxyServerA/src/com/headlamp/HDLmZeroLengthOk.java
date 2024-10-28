package com.headlamp;
/**
 * HDLmZeroLengthOk short summary.
 *
 * HDLmZeroLengthOk description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if zero-length arrays
   are acceptable or not. In general, zero-length arrays are 
   not OK. However, in at least one important case, we must
   allow zero-length arrays. As it turns out, notify modification
   events can have zero extract values. */
public enum HDLmZeroLengthOk {
	NONE,
	ZEROLENGTHOK,
	ZEROLENGTHNOTOK
}
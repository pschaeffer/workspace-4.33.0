package com.headlamp;
/**
 * HDLmNullValues short summary.
 *
 * HDLmNullValues description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if null values are
   acceptable or not. In general, null values are not OK. However, 
   in at least one important case, we must allow null values. As 
   it turns out, some valid use mode values are zero-length strings
   These strings are (for some unknown reason) returned as null
   values. */
public enum HDLmNullValues {
	NONE,
	NULLVALUESOK,
	NULLVALUESNOTOK
}
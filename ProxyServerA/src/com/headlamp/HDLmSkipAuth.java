package com.headlamp;
/**
 * HDLmSkipAuth short summary.
 *
 * HDLmSkipAuth description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if an authorization 
   header should be added or not. In most cases, an authorization
   header should be added. However, in a few case, we don't want
   or need a standard authorization header. This enum allows the
   adding of a standard authorization header to be disabled. */ 
public enum HDLmSkipAuth {
	NONE,
	SKIPAUTHNO,
	SKIPAUTHYES
}
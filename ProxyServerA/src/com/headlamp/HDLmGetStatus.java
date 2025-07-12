package com.headlamp;
/**
 * HDLmGetStatus short summary.
 *
 * HDLmGetStatus description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing what type of 
   status information should be returned to the caller.
   The caller may really want HTML information or the
   caller may want information that can be used to build
   JSON. */  
public enum HDLmGetStatus {
	NONE,
	HTMLINFORMATION,
	JSONINFORMATION
}
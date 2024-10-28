package com.headlamp;
/**
 * HDLmShowRequestOk short summary.
 *
 * HDLmShowRequestOk description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if request details 
   (Apache HTTP(S) requests) should be displayed or not. The
   details of how each request is displayed is controlled 
   by the code that actually handles each Apache HTTP(S) 
   request. */ 
public enum HDLmShowRequest {
	NONE,
	SHOWREQUESTOK,
	SHOWREQUESTNOTOK
}
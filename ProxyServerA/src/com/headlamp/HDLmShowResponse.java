package com.headlamp;
/**
 * HDLmShowResponseOk short summary.
 *
 * HDLmShowResponseOk description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if response details 
   (Apache HTTP(S) responses) should be displayed or not. The
   details of how each response is displayed is controlled 
   by the code that actually handles each Apache HTTP(S)
   response. */ 
public enum HDLmShowResponse {
	NONE,
	SHOWRESPONSEOK,
	SHOWRESPONSENOTOK
}
package com.headlamp;
/**
 * HDLmOutboundJson short summary.
 *
 * HDLmOutboundJson description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if we are sending JSON
   or not. In most (but not all) cases, we are sending JSON and
   this enum will be set to yes. However, in a few cases we are
   not sending JSON, and this enum will be set to no. */ 
public enum HDLmOutboundJson {
	NONE,
	OUTBOUNDJSONNO,
	OUTBOUNDJSONYES
}
package com.headlamp;
/**
 * HDLmLevels short summary.
 *
 * HDLmLevels description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides levels for logging. Not all of these levels 
   are in use and not all of these levels are supported by all of the 
   common logging frameworks. Note that SLF4J has no FATAL level. Some
   logging frameworks use SEVERE for FATAL. */ 
public enum HDLmLogLevels {
	NONE,
	FATAL,
	ERROR,
	WARN,
	INFO,
	DEBUG,
	TRACE
}
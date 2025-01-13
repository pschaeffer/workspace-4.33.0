package com.headlamp;
/**
 * HDLmReportErrors short summary.
 *
 * HDLmReportErrors description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if errors should be
   reported or not. In some cases, we don't want to report 
   errors. For example, if we are running unit tests, we 
   really don't want (expected) errors to be reported. in
   other (most) cases, we do want errors to be reported */  
public enum HDLmReportErrors {
	NONE,
	REPORTERRORS,
	DONTREPORTERRORS
}
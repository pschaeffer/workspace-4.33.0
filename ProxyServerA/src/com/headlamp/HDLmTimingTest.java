package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmTimingTest short summary.
 *
 * HDLmTimingTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmTimingTest {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmTimingTest.class); 
	@Test
	void AddTiming() {		
		/* Run a few (actually one) addTiming tests */   
		String  timingNameStr = "abc"; 
		/* Add a (test) timing */ 		 
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, timingNameStr); 
		{  
			HDLmTimingTypes   localTimingType = HDLmTimingTypes.GENERAL;
			String            localTimingName = "abc";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmTiming.addTiming(null,
					                              		                         localTimingName);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New timing type string passed to addTiming is null", execMsg,
					         "Unexpected exception message");
		}		
		{  
			HDLmTimingTypes   localTimingType = HDLmTimingTypes.GENERAL;
			String            localTimingName = "abc";
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmTiming.addTiming(localTimingType,
					                              		                         null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New timing name string passed to addTiming is null", execMsg,
					         "Unexpected exception message");
		}	
	}	
	@Test
	void getTimingsHeadings() {		
		/* Run a few (actually one) getTimingsHeadings tests */
		ArrayList<String>   headingsStr = null;
		String  timingNameStr = "abc";
		/* Build the heading as need be */ 		 
		headingsStr = HDLmTiming.getTimingsHeadings(null); 
		assertNotNull(headingsStr, "Headings are null where they should not be in getTimingsHeadings test");
		/* Build the heading as need be */ 		 
		headingsStr = HDLmTiming.getTimingsHeadings(timingNameStr); 
		assertNotNull(headingsStr, "Headings are null where they should not be in getTimingsHeadings test");		
	}	
	@Test
	void getTimingsStatus() {		
		/* Run a few (actually one) getTimingsStatus tests */   
		ArrayList<String>     outputStr = null;
		HDLmGetTimingStatus   informationType = HDLmGetTimingStatus.JSONINFORMATION;
		TreeSet<HDLmTiming>   timingsSet = new TreeSet<HDLmTiming>();
		/* Get the timings status */ 		 
		outputStr = HDLmTiming.getTimingsStatus(timingsSet, informationType); 
		assertNotNull(outputStr, "Output is null from getTimingsStatus test");	
		{  
			TreeSet<HDLmTiming>   localTimingsSet = new TreeSet<HDLmTiming>();
			HDLmGetTimingStatus   LocalInformationType = HDLmGetTimingStatus.JSONINFORMATION;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmTiming.getTimingsStatus(null,
					                                                              		LocalInformationType);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Timings set passed to getTimingsStatus is null", execMsg,
					         "Unexpected exception message");
		}		
		{  
			TreeSet<HDLmTiming>   localTimingsSet = new TreeSet<HDLmTiming>();
			HDLmGetTimingStatus   LocalInformationType = HDLmGetTimingStatus.JSONINFORMATION;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmTiming.getTimingsStatus(localTimingsSet,
        					                              		                        null);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Information type passed to getTimingsStatus is null", execMsg,
					         "Unexpected exception message");
		}	
		{  
			TreeSet<HDLmTiming>   localTimingsSet = new TreeSet<HDLmTiming>();
			HDLmGetTimingStatus   localInformationType = HDLmGetTimingStatus.NONE;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmTiming.getTimingsStatus(localTimingsSet,
        					                              		                        localInformationType);}, 
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Information type passed to getTimingsStatus is not valid", execMsg,
					         "Unexpected exception message");
		}	
	}
	@Test
	void getTimingsTree() {		
		/* Run a few (actually one) getTimingsTree tests */   
		String                overallName = "abc";
		TreeSet<HDLmTiming>   timingsSet = null; 
		/* Get the timings tree */ 		 
		HDLmTiming.addTiming(HDLmTimingTypes.GENERAL, overallName); 
		overallName = null;
		timingsSet = HDLmTiming.getTimingsTree(overallName);
		assertNotNull(timingsSet, "No output tree set from getTimingsTree test");			
	}
}
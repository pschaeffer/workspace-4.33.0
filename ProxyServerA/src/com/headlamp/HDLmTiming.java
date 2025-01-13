package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmTiming short summary.
 *
 * HDLmTiming description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmTiming {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmTiming.class);  
	/* Define the array list that contains all of the timing values.
	   This array list is used as a wrap-around buffer for saving
	   timing values.  */
  private static ArrayList<HDLmTiming>  timingsValues = null; 
  /* The next field contains the number of timing values that 
     have been saved so far. Note that this is an atomic value
     so that it can be updated by multiple threads. */ 
  private static AtomicLong             timingsCount = new AtomicLong();
  /* The timings array list size is stored in the field below so that
     it does not have to be obtained from the defines each time */ 
  private static int                    timingsSize;
  private static final String           timingsPatternFormat = "yyyy-MM-dd HH:mm:ss.SSS";
  /* This constructor takes a new timing name and type and builds 
     a new timing instance (value) */ 
  protected HDLmTiming(final HDLmTimingTypes newTimingType,
  		                 final String newTimingName) {
		/* Check if the new timing type passed by the caller is null */
		if (newTimingType == null) {
			String  errorText = "New timing type string passed to HDLmTiming is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timing type passed by the caller is valid */
		if (newTimingType == HDLmTimingTypes.NONE) {
			String  errorText = "Timing type passed to HDLmTiming is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the new timing name passed by the caller is null */
		if (newTimingName == null) {
			String  errorText = "New timing name string passed to HDLmTiming is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few fields in the new timing instance */
		timingType = newTimingType;
		timingName = newTimingName;  	
		timingRecorded = Instant.now();
		timingCpu = System.currentTimeMillis();
  }  
	/* All timings have a type. The type of each timings is 
	   stored in the field below. */ 
  private HDLmTimingTypes   timingType = null;
	/* The timing name can be anything. It could be a file name.
	   It could be a URL name. The timing name will typically 
	   show where the timing call came from. In all cases, the
	   timing name will be a string. */
	private String            timingName = null; 
  /* Each time a timing is recorded, the timing timestamp is 
     recorded. This value will be set to a GMT timestamp each
     time a timing is recorded. */ 
  private Instant           timingRecorded = null; 
  /* The thread CPU is stored in the field below. This value 
     is a long because the API that get the thread CPU time
     returns a long. We have not found a good source for this
     data. As a consequence, this field should not be taken 
     too seriously. */ 
  private long              timingCpu = 0;
  /* This routine adds a timing to the timings array list. Note that
     this routine can be called more than once with the same timing name.
     If a duplicate timing name is passed, it will just be added to the
     array list used to store timing references. This is not an error
     condition. */  
	@SuppressWarnings("unused")
	protected static void  addTiming(final HDLmTimingTypes newTimingType,			
			                             final String newTimingName) {
		/* Check if the new timing type passed by the caller is null */
		if (newTimingType == null) {
			String  errorText = "New timing type string passed to addTiming is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new timing name passed by the caller is null */
		if (newTimingName == null) {
			String  errorText = "New timing name string passed to addTiming is null";
			throw new NullPointerException(errorText);
		}
  	/* Create the new timing instance */
  	HDLmTiming  newTiming = new HDLmTiming(newTimingType, newTimingName);
    if (newTiming == null)	{   
  	  String   errorText = "New timing reference obtained from HDLmTiming is null";
  		HDLmAssertAction(false, errorText);		    	
    }
    /* Add the new timing to the timings array list */
    if (timingsValues == null) {
    	Integer   timingsArraySize = HDLmDefines.getNumber("HDLMTIMINGSARRAYSIZE");
  		if (timingsArraySize == null) {
  			String  errorFormat = "Define value for timings array size number not found (%s)";
  			String  errorText = String.format(errorFormat, "HDLMTIMINGSARRAYSIZE");
  			HDLmAssertAction(false, errorText);		    	
  		}
    	/* Save the size of the timings array for easy use later */
  		timingsSize = timingsArraySize;
  		if (timingsSize == 0)
  		  LOG.info(((Integer) timingsSize).toString());
  		timingsValues = new ArrayList<HDLmTiming>(timingsSize);
  		if (timingsValues.size() > 0)
  		  LOG.info(((Integer) timingsValues.size()).toString());
  		if (timingsValues == null) {
  			String  errorText = "Allocation of timings ArrayList failed";
  			HDLmAssertAction(false, errorText);
  		}    	
  		/* Populate the array list with null values */
  		for (int i = 0; i < timingsSize; i++) {
  			timingsValues.add(null);
  		}
    }
    /* Increment the number of timing values that have been created and 
       added so far */
    long  countSoFar = timingsCount.incrementAndGet();
    int   timingsIndex = (int) ((countSoFar - 1) % timingsSize);
    /* Store a reference to the new timing in the array list */
    try {
      timingsValues.set(timingsIndex, newTiming);
    }
    catch (Exception e) {
      LOG.info(((Long) countSoFar).toString());  
      LOG.info(((Integer) timingsIndex).toString()); 
      LOG.info(((Integer) timingsSize).toString()); 
      LOG.info(e.getMessage(), e);
    }
  	return;
  } 
  /* This routine returns a set of heading suitable for all of
     the uses of a timing */
	public static ArrayList<String>  getTimingsHeadings(final String overallName) {
		/* Declare and define a few values */
		ArrayList<String>  headings = null;
    /* Check if we need headings for all of the timings of just one timing */
		if (overallName == null)
		  headings = new ArrayList<String>(List.of("Timing<br/>Name", 
		  		                                     "Timing<br/>Type", 
																			         "Timing<br/>Timestamp"));	
		/* We need to return headings for just one timing */
		else
		  headings = new ArrayList<String>(List.of("Timing<br/>Timestamp"));				
		return headings;  	
	}
  /* Return a reference to the main timings array list */
  protected static ArrayList<HDLmTiming>  getTimingsArrayList() {
  	return timingsValues;
  }
  /* This routine builds an ArrayList of timing/timings status 
     information. Some callers use this information directly.
     Other callers extract the information they need. */
	public static ArrayList<String>  getTimingsStatus(final TreeSet<HDLmTiming> timingsSet,
			                                              final HDLmGetTimingStatus informationType) {
		/* Check if the timings set passed by the caller is null */
		if (timingsSet == null) {
			String  errorText = "Timings set passed to getTimingsStatus is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the information type passed by the caller is null */
		if (informationType == null) {
			String  errorText = "Information type passed to getTimingsStatus is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the information type passed by the caller is valid */
		if (informationType != HDLmGetTimingStatus.HTMLINFORMATION &&
				informationType != HDLmGetTimingStatus.JSONINFORMATION) {
			String  errorText = "Information type passed to getTimingsStatus is not valid";
			throw new IllegalArgumentException(errorText);
		}
		/* Declare and define a few variables */
	  boolean   encodingException = false;
		/* Build a (short) list of the timing/timings status information */			
	  var  statusListContents = new ArrayList<String>();
		/* Process each timing in the timings set. Note that the timings
		   set is in the desired order. */ 
	  for (HDLmTiming curTiming : timingsSet) {
	 	  /* Get a few values from the current timing */
	 	  HDLmTimingTypes   timingType = curTiming.getType();
	 	  Instant           timingTimestamp = curTiming.getTimestamp(); 
	 	  long              timingCpu = curTiming.getCpu();
	 	  String            timingName = curTiming.getName();
	 	  String            timingTypeStr = timingType.toPrint();  
	 	  /* Check if we are trying to get HTML information or not */
	 	  if (informationType == HDLmGetTimingStatus.HTMLINFORMATION) {	
		    /* Add a null value as the timing style */
		    statusListContents.add(null);
	    }
	 	  /* Check if we are trying to get HTML information or not */
	 	  if (informationType == HDLmGetTimingStatus.HTMLINFORMATION) {
		  	/* Add the current timing name and type */
		  	statusListContents.add(timingName);
		  	statusListContents.add(timingTypeStr);
	 	  }
	 	  /* Check if we actually have an timing timestamp */
	 	  if (timingTimestamp != null) {
	 		  /* Add the timestamp for the current timing */
	 		  DateTimeFormatter   formatter = DateTimeFormatter.ofPattern(timingsPatternFormat).
	 		                              		withZone(ZoneId.systemDefault());
	 		  statusListContents.add(formatter.format(timingTimestamp));  
	 		  /* statusListContents.add(String.valueOf(timingCpu)); */
	 	  }    	
	 	  /* The timing timestamp value is not set. Note that 
	 	     we treat the count as a missing value in this 
	 	     case even though we actually have a count value
	 	     (zero) that could be used. */
	 	  else {
	 		/* Add values that show that we have no data for all
	 		   of the columns */ 
			  statusListContents.add("-");
			  statusListContents.add("-");
			  statusListContents.add("-"); 
			  /* statusListContents.add("-"); */
	 	  }    	
	  }		
	  return statusListContents;
	}     
  /* This routine returns a tree set with all of the timings 
     or just one timing */
  public static TreeSet<HDLmTiming>  getTimingsTree(final String overallName) {
		/* Check if the overall timing name passed by the caller is null.
		   Actually, the overall timing name can be null if we want to 
		   get a tree set with all of the timings. */
		if (overallName == null && overallName != null) {
			String  errorText = "Overall rule name string passed to getTimingsTree is null";
			throw new NullPointerException(errorText);
		}
    /* Create a timing comparator for use in building/maintaining the
       tree set of timings */  		
		class timingsComparator implements Comparator<HDLmTiming> {     
			@Override
			public int compare(HDLmTiming e1, HDLmTiming e2) {
				int   compareInt;
				compareInt = e1.getTimestamp().compareTo(e2.getTimestamp());
				/* compareInt = (int) (e1.getCpu() - e2.getCpu()); */
				if (compareInt == 0) 
					compareInt = e1.getName().compareTo(e2.getName());
				return compareInt;		   
			}
	  }   
		/* Get a reference to the array list of timings information */
		var  timingsArrayList = HDLmTiming.getTimingsArrayList();
		if (timingsArrayList == null) {
			String   errorText = "Timings array list reference obtained from getTimingsArrayList is null";
			HDLmAssertAction(false, errorText);		    	
    }    
		/* Create a new timings set that uses the timings comparator we just created */
		TreeSet<HDLmTiming>  timingsSet = new TreeSet<HDLmTiming>(new timingsComparator());		
	  /* Find the upper bound of the array list used to store timings. When 
	     the array list is fully used, this will be the size of the array 
	     list. However, at the start a much lower number must be used. */
		long  countSoFar = timingsCount.get();
		int   upperBound;
		if (countSoFar >= timingsSize) 
			upperBound = timingsSize;
		else
			upperBound = (int) countSoFar;
	  /* Process each actual timing in the timings array list. Each actual 
       timing in the timings array list is copied into the timings set. 
       This has the effect of creating an timings set with all of the 
       correct vales in the correct order. */
		for (int i = 0; i < upperBound; i++) {
			HDLmTiming  curTiming = timingsArrayList.get(i);		 
	  	String  timingName = curTiming.getName();
	  	if (overallName == null ||
	  			overallName.equals(timingName))	    
	  	  timingsSet.add(curTiming);	
	  }
		return timingsSet;
  } 
  /* Get the CPU value of a timing. All timings have a CPU
     value that can be obtained using this routine. */
  protected long         getCpu() {
	  return timingCpu;
  }
  /* Get the name of a timing. All timings have a name
     that can be obtained using this routine. */
  protected String       getName() {
  	return timingName;
  }  
  /* Get the timestamp of a timing. All timings have a timestamp
     that can be obtained using this routine. */
  protected Instant      getTimestamp() {
	  return timingRecorded;
  }  
  /* Get the type of a timing. All timings have a type
     that can be obtained using this routine. */
  protected HDLmTimingTypes  getType() {
  	return timingType;
  }   
}
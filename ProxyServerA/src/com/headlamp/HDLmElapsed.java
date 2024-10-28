package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.math.BigIntegerMath;
/**
 * HDLmElapsed short summary.
 *
 * HDLmElapsed description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmElapsed {
	/* The list of elapsed time names is stored below. These elapsed time names
	   are added to the elapsed time map and used to track elapsed time measurements. */
  protected static final ArrayList<String>  elapsedNames = new ArrayList<String>(
	  List.of("Requests", "Backend", "Post", "Database", "Invoke"));
	/* Define the map that contains all of the elapsed time 
     values */
  private static Map<String, HDLmElapsed>  elapsedValues = new TreeMap<String, HDLmElapsed>(); 	
  /* This constructor takes a new elapsed time name and build a new 
     elapsed time instance */ 
  protected HDLmElapsed(final HDLmElapsedTypes newElapsedType,
  		                  final String newElapsedName) {
		/* Check if the new elapsed time type passed by the caller is null */
		if (newElapsedType == null) {
			String  errorText = "New elapsed time type string passed to HDLmElapsed is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new elapsed time name passed by the caller is null */
		if (newElapsedName == null) {
			String  errorText = "New elapsed time name string passed to HDLmElapsed is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few fields in the new elapsed time instance */
		elapsedType = newElapsedType;
		elapsedName = newElapsedName;  	
  }
  /* All elapsed time entries have a type. The type of each elapsed time entry  
	   is stored in the field below. */ 
  private HDLmElapsedTypes  elapsedType = null;
	/* The elapsed time name can be anything. It could be a file name.
	   It could be a URL name. In most (but not all) cases, the elapsed
	   time name will be something recognizable. In all cases, the overall 
	   elpased time name will be a string. */
	private String  elapsedName = null;
  /* An elapsed time measurement can occur zero or more times. The
     number of times might be quite large if the server stays up 
     for a long period of time and the elapsed time measurement 
     occurs at a high rate. */
  private long  elapsedCount = 0;
  /* The field below is the cumulative duration for this elapsed time
     entry. This is not the duration of the last elapsed time measurement.
     This is the sum of all of the elapsed time measurements for the
     current elapsed time entry, so far. This field actually contains
     the total number of duration nanoseconds so far. Since this value
     might not fit into a long, a big integer is used. */
  private BigInteger  elapsedAccumulatedNanos = new BigInteger("0");
  /* The field below is the cumulative duration for this elapsed time
     entry squared. This is not the duration of the last elapsed time 
     measurement squared. This is the sum of all of the elapsed time
     measurements squared for the current elapsed time entry, so far. 
     This field actually contains the total number of duration nanoseconds 
     (squared) so far. Since this value might not fit into a long, a 
     big integer is used. */
  private BigInteger  elapsedAccumulatedNanosSquared = new BigInteger("0");
  /* The following fields are really just temporary values used for
     various calculations */
  private BigInteger  elapsedNanosSquared = new BigInteger("0");
  private BigInteger  elapsedMean = new BigInteger("0");
  private BigInteger  elapsedMeanSquared = new BigInteger("0");
  private BigInteger  elapsedSumOfSquaresDividedByCount = new BigInteger("0");
  private BigInteger  elapsedVariance = new BigInteger("0");
  private BigInteger  elapsedStandardDeviation = new BigInteger("0");
  /* Each time an elapsed time measurement occurs, the elapsed time
     timestamp is updated. This value will be null, if the elapsed 
     time measurement has never occurred. This value will be set to
     a GMT timestamp each time the elapsed time measurement occurs. */
  private String  elapsedLast = null;
  /* This routine adds an elapsed time entry to the elapsed time map.
     Note that this routine can be called more than once with the same 
     elapsed time name. If a duplicate elapsed time name is passed, it 
     will just be ignored. This is not an error condition. */ 
  @SuppressWarnings("unused")
	protected static boolean  addElapsed(final HDLmElapsedTypes newElapsedType,			
			                                 final String newElapsedName) {
		/* Check if the new elapsed time type passed by the caller is null */
		if (newElapsedType == null) {
			String  errorText = "New elapsed time type string passed to addElapsed is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new elapsed time name passed by the caller is null */
		if (newElapsedName == null) {
			String  errorText = "New elapsed time name string passed to addElapsed is null";
			throw new NullPointerException(errorText);
		}
  	boolean   elapsedAdded = false;
  	/* Check if the elapsed time has already been added */
  	if (elapsedValues.containsKey(newElapsedName))
  		return elapsedAdded;
  	/* Add the new elapsed time entry to the elapsed time map */
  	HDLmElapsed  newElapsed = new HDLmElapsed(newElapsedType, newElapsedName);
    if (newElapsed == null)	{   
  	  String   errorText = "New elapsed time reference obtained from HDLmElapsed is null";
  		HDLmAssertAction(false, errorText);		    	
    }
    elapsedValues.put(newElapsedName, newElapsed);
  	return elapsedAdded;
  }
	/* Add a set of general elapsed time entries to the elapsed time map */
	protected static void  addElapsedList(final ArrayList<String> elapsedNames) {
		/* Check if the elapsed time names array passed by the caller is null */
		if (elapsedNames == null) {
			String  errorText = "Elapsed time names array passed to addElapsedList is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the elapsed time names array is empty */
		if (elapsedNames.size() == 0) {
			String  errorText = "Elapsed time names array passed to addElapsedList is empty";			 
			throw new IllegalArgumentException(errorText);
		}
		/* Set a few local variables */
		int   numberOfElapsedTimeEntries = elapsedNames.size();
		/* Process each of the elapsed time names. Add each of them to the 
		   elapsed time map. */ 
		for (String elapsedName : elapsedNames) {
			HDLmElapsed.addElapsed(HDLmElapsedTypes.GENERAL, elapsedName);			
		}		
	} 
  
  /* Note that an elapsed time measurement has occurred. Update the 
     elapsed time instance for the current elapsed time measurement. */ 
	protected static void  elapsedOccurred(final String curElapsedKey,
			                                   final Duration curElapsedDuration) {
		/* Check if the elapsed time key string passed by the caller is null */
		if (curElapsedKey == null) {
			String  errorText = "Elapsed time key string passed to elapsedOccurred is null";
			throw new NullPointerException(errorText);
		}
		/* Get a reference to the map of elapsed time information */
		var  elapsedMap = HDLmElapsed.getElapsedMap();
		if (elapsedMap == null) {
			String   errorText = "Elapseds time map reference obtained from getElapsedsMap is null";
			HDLmAssertAction(false, errorText);		    	
    }
		/* Make sure the elapsed time map has the current elapsed time entry */
		if (!elapsedMap.containsKey(curElapsedKey)) {
			String  errorFormat = "Elapsed time map does not have current elapsed time entry (%s)"; 
			String  errorText = String.format(errorFormat,  curElapsedKey);
			HDLmAssertAction(false, errorText);					
		}
		/* Get the elapsed time instance from the elapsed time map */
		HDLmElapsed   curElapsed = elapsedMap.get(curElapsedKey);
		/* Update the elapsed time instance as need be */
		curElapsed.incrementCount();
	  curElapsed.setIsoTimestamp();
	  /* Get the total number of nanoseconds for the current elapsed
	     time measurement. This requires adding the actual number of
	     nanoseconds to the seconds value (the seconds value is 
	     multiplied by 1000000000). */ 
	  long  elapsedNanos = (long) curElapsedDuration.getNano();
	  long  elapsedSeconds = curElapsedDuration.getSeconds();
	  if (elapsedSeconds > 0)
	  	elapsedNanos += (elapsedSeconds * 1000000000);
	  /* Update a couple of big integer values with the number of nanoseconds 
	     for current elapsed time measurement and that value squared */
	  curElapsed.elapsedNanosSquared = BigInteger.valueOf(elapsedNanos).multiply(BigInteger.valueOf(elapsedNanos));
	  curElapsed.elapsedAccumulatedNanos = 
	  	curElapsed.elapsedAccumulatedNanos.add(BigInteger.valueOf(elapsedNanos));
	  curElapsed.elapsedAccumulatedNanosSquared = 
	    curElapsed.elapsedAccumulatedNanosSquared.add(curElapsed.elapsedNanosSquared);
	  /* Store the updated elapsed time instance back in the elapsed time map */
	  elapsedMap.put(curElapsedKey, curElapsed);		
	}
  /* Get the average duration value for an elapsed time entry */
  protected double       getAverageElapsedTime() {
  	return elapsedAccumulatedNanos.divide(BigInteger.valueOf(elapsedCount)).longValue()/1000000000.0;
  }
  /* Get the count value for an elapsed time entry */
  protected long         getCount() {
  	return elapsedCount;
  }
  /* Return a reference to the main elapsed time map */
  protected static Map<String, HDLmElapsed>  getElapsedMap() {
  	return elapsedValues;
  }
  /* Get the name of an elapsed time entry */
  protected String       getName() {
  	return elapsedName;
  }
  /* Get the average duration value for an elapsed time entry */
  protected double       getStdElapsedTime() {
  	/* Get a big integer with the average (mean) elapsed time */
  	elapsedMean = elapsedAccumulatedNanos.divide(BigInteger.valueOf(elapsedCount));
  	/* Get a big integer with the average elapsed time squared */
  	elapsedMeanSquared = elapsedMean.multiply(elapsedMean);
  	/* Get the sum of the squares, divided by the number of measurements */
  	elapsedSumOfSquaresDividedByCount = elapsedAccumulatedNanosSquared.divide(BigInteger.valueOf(elapsedCount));
  	/* Get the variance (standard deviation squared) from the values so far */
  	elapsedVariance = elapsedSumOfSquaresDividedByCount.subtract(elapsedMeanSquared);
  	/* Get the standard deviation by taking the square root of the variance */
  	elapsedStandardDeviation = BigIntegerMath.sqrt(elapsedVariance, RoundingMode.HALF_DOWN);
  	return elapsedStandardDeviation.longValue()/1000000000.0;
  }
  /* Get the timestamp value for an elapsed time entry. Note 
     that the timestamp value is actually a string. Note also
     that this could be a null value if the current elapsed 
     time measurement has never occurred. */
  protected String       getTimestamp() {
  	return elapsedLast;
  }    
  /* Get the type of an elapsed time entry. All elapsed time 
     entries have a type that can be obtained using this routine. */
  protected HDLmElapsedTypes  getType() {
  	return elapsedType;
  }   
  /* Increment the count value for an elapsed time entry */
  protected void         incrementCount() {
  	elapsedCount++;
  }
  /* Set the timestamp for an elapsed time measurement to the current ISO8601 timestamp */
  protected void         setIsoTimestamp() {
  	elapsedLast = HDLmUtility.getISO8601();
  }
}
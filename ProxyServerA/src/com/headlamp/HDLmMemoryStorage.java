package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.math.BigIntegerMath;
import com.google.gson.Gson;
/**
 * HDLmMemoryStorage short summary.
 *
 * HDLmMemoryStorage description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmMemoryStorage {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmMemoryStorage.class);  	
	/* This class can never be instantiated */
	private HDLmMemoryStorage() {}
	/* Define the map that contains all of the stored values */  
  private static Map<String, String>  storedValues = new TreeMap<String, String>(); 	
  /* We need to be able to get entries from the map */
  protected static String  get(final String keyStr) {
		/* Check a few values passed by the caller */
		if (keyStr == null) {
			String   errorText = "Key string reference passed to get is null";
			throw new NullPointerException(errorText);			
		} 
  	return storedValues.get(keyStr);
  }
  /* This routine returns a set of headings for memory storage */ 
	public static ArrayList<String>  getMemoryStorageHeadings(final String overallName) {
		/* Declare and define a few values */
		ArrayList<String>  headings = null;
	  /* Build the memory storage headings */		 
		headings = new ArrayList<String>(List.of("Statistic<br/>Name", "Statistic<br/>Value"));	
		return headings;  	
	}
	/* This routine builds an ArrayList of memory storage status
	   information. Some callers use this information directly.
	   Other callers extract the information they need. */
	public static ArrayList<String>  getMemoryStorageStatus(final Map<String, String> memoryStorageMap, 
			                                                    final HDLmGetStatus informationType) {
		/* Declare and define a few variables */
	  boolean   encodingException = false;
		/* Build a (short) list of the memory storage status information */			
	  var   statusListContents = new ArrayList<String>();
    long  mapCount = memoryStorageMap.size(); 	
    /* Add a few values to the status list */ 
    statusListContents.add(null);
    statusListContents.add("Count");
    statusListContents.add(((Long) mapCount).toString());
	  return statusListContents;
	}   
	/* This method gets a reference to the memory storage map and returns
	   it to the caller */ 
	protected static Map<String, String>  getMemoryStorageMap() {
		return storedValues;
	}
  /* We need to be able to add or replace entries in the map */
  protected static void  put(final String keyStr, final String valueStr) {
		/* Check a few values passed by the caller */
		if (keyStr == null) {
			String   errorText = "Key string reference passed to put is null";
			throw new NullPointerException(errorText);			
		}
		if (valueStr == null) {
			String   errorText = "Value string reference passed to put is null";
			throw new NullPointerException(errorText);			
		}
  	storedValues.put(keyStr, valueStr);
  }  
}
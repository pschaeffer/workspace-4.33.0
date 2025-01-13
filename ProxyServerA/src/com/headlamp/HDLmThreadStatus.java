package com.headlamp;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class for tracking the status of each thread
 * 
 * Instances of this class are definitely created. Each instance
 * has information about the status of a thread at some point in
 * time.
 * 
 * This class also has a static field for the overall concurrent
 * thread status map. The concurrent thread status map is created 
 * just once, but is updated as need be. 
 * 
 * The key value is the thread name such as qtp512456259-109. The
 * data value is the thread status at some time. This means that
 * only the newest thread status for a given thread is tracked in
 * the concurrent hash map. Another way of saying this, is that a 
 * new status for a given thread, will replace the old status. 
 * 
 * The status map used by this class is completely hidden internally and
 * never exposed. No method exposes the status map and/or returns a reference
 * to the status map. All methods use the concurrent hash map and are not
 * synchronized. 
 *  
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. An instance of this class is created
   for each thread status value */   
public class HDLmThreadStatus {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  @SuppressWarnings("unused")
  private static final Logger LOG = LoggerFactory.getLogger(HDLmThreadStatus.class);  
  /* All instances of this class have a standard set of fields */
  Instant   currentTime = null;
  String    currentStatus = null;
	/* Build the (initially) empty map of thread status information */
  private static ConcurrentHashMap<String, HDLmThreadStatus>  statusMap = new ConcurrentHashMap<String, HDLmThreadStatus>();
	/* This is the default constructor for this class. It doesn't do anything.
     All fields of this class will be set to the default values specified 
     above. This constructor is required so that this class can be extended. */
  protected HDLmThreadStatus() {}
	/* This is the standard constructor for this class. The time is set to
	   the current time. The status is set to the value passed by the caller.
	   This constructor is the one typically used to build new instances of
	   this class. */  
  protected HDLmThreadStatus(String newStatus) { 
	  currentTime = Instant.now();
	  currentStatus = newStatus;
  }
  /* Get a status map entry from the status map. This routine returns null
     if the entry is not found. */
  protected static HDLmThreadStatus  get(String threadName) {  		 
  	return statusMap.get(threadName); 
  } 
  /* Return a map with the contents of status map. Each and
     every entry in the status map is copied into the returned 
     map. The output map is returned to the caller */ 
	protected static Map<String, HDLmThreadStatus> getMap() {
		/* Create the output map that is returned to the caller */
		Map<String, HDLmThreadStatus>   outputMap = new HashMap<String, HDLmThreadStatus>();
		for (Map.Entry<String, HDLmThreadStatus> entry : statusMap.entrySet()) {
	    String            key = entry.getKey();
	    HDLmThreadStatus  value = entry.getValue();
	    outputMap.put(key, value);
	  }
		return outputMap;
	}
  /* Return a set of statistics describing a status map. The caller does not 
     provides a reference to the status map for which statistics are needed. 
     The standard status map is always used. The statistics are returned as a 
     tree map so that they can be accessed in ascending name order. */ 
  protected static TreeMap<String, String> getStatistics() {
  	var  status = statusMap;
  	var  statusStatsTree = new TreeMap<String, String>();
  	/* Get the status map size. This is the only value that can be obtained
  	   without requesting status map statistics. */  	
  	statusStatsTree.put("Size", new Long(status.size()).toString());
  	/* Return the tree of status map statistics to the caller */
  	return statusStatsTree;
  }
	/* Get the application thread status (if any) and return it to
     the caller. If we don't have an actual application status 
     value, then this routine will return a null value.  */
  protected String getStatus() {
	  return currentStatus;
  }
  /* Add a status entry to the status map. This code could actually replace an
     existing status map entry. */
	protected static void put(String threadName, HDLmThreadStatus threadStatus) { 
	  statusMap.put(threadName, threadStatus);   
	} 
	/* Get the application thread time stamp (if any) and return 
     it to the caller. If we don't have an application time 
     stamp value, then this routine will return a null value. */
  protected String getTimeStamp() {
  	if (currentTime != null)
	    return HDLmUtility.getUtcTimeStamp(currentTime);
  	return null;
  }
  /* Add a status entry to the status map. This code could actually replace an
     existing status map entry. */
	protected static void put(String newStatus) {
		HDLmThreadStatus  threadStatus = new HDLmThreadStatus(newStatus);
		String            threadName = Thread.currentThread().getName();
	  statusMap.put(threadName, threadStatus);   
	} 
}
package com.headlamp;
import java.util.TreeMap;

import org.eclipse.jetty.server.handler.StatisticsHandler;
/**
 * Class for handling statistics  
 * 
 * No instances of this class are ever created. However, the static
 * methods of this class are used to obtain statistics. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmStatisticsHandler {
	/* This class can never be instantiated */
	private HDLmStatisticsHandler() {}
  /* Return a set of statistics to the caller. The caller provides a reference
     to the statistics handler object. This routine returns a tree map with the
     statistics. The statistics are returned as a tree map so that they can be
     accessed in ascending name order. */ 
  protected static TreeMap<String, String> getStatistics(StatisticsHandler stats) {
		if (stats == null) {
			String  errorText = "Statistics handler reference passed to getStatistics is null";
			throw new NullPointerException(errorText);		
		}
		/* Create the new tree map that is returned to the caller */
  	var  statsTree = new TreeMap<String, String>();
  	/* Get the statistics from the statistics handler */
  	statsTree.put("Async dispatches", new Long(stats.getAsyncDispatches()).toString());
  	statsTree.put("Async requests", new Long(stats.getAsyncRequests()).toString());
  	statsTree.put("Async requests waiting", new Long(stats.getAsyncRequestsWaiting()).toString());
  	statsTree.put("Async requests waiting max", new Long(stats.getAsyncRequestsWaitingMax()).toString());
  	statsTree.put("Dispatched", new Long(stats.getDispatched()).toString());
  	statsTree.put("Dispatched active", new Long(stats.getDispatchedActive()).toString());
  	statsTree.put("Dispatched active max", new Long(stats.getDispatchedActiveMax()).toString());
  	statsTree.put("Dispatched time max", new Long(stats.getDispatchedTimeMax()).toString());
  	statsTree.put("Dispatched time mean", new Double(stats.getDispatchedTimeMean()).toString());
  	statsTree.put("Dispatched time standard deviation", new Double(stats.getDispatchedTimeStdDev()).toString());
  	statsTree.put("Dispatched time time total", new Long(stats.getDispatchedTimeTotal()).toString());
  	statsTree.put("Expires", new Long(stats.getExpires()).toString());
  	statsTree.put("Requests", new Long(stats.getRequests()).toString());
  	statsTree.put("Requests active", new Long(stats.getRequestsActive()).toString());
  	statsTree.put("Requests active max", new Long(stats.getRequestsActiveMax()).toString());
  	statsTree.put("Requests time max", new Long(stats.getRequestTimeMax()).toString());
  	statsTree.put("Requests time mean", new Double(stats.getRequestTimeMean()).toString());
  	statsTree.put("Requests time standard deviation", new Double(stats.getRequestTimeStdDev()).toString());
  	statsTree.put("Requests time total", new Long(stats.getRequestTimeTotal()).toString());
  	statsTree.put("Responses 1xx", new Long(stats.getResponses1xx()).toString());
  	statsTree.put("Responses 2xx", new Long(stats.getResponses1xx()).toString());
  	statsTree.put("Responses 3xx", new Long(stats.getResponses1xx()).toString());
  	statsTree.put("Responses 4xx", new Long(stats.getResponses1xx()).toString());
  	statsTree.put("Responses 5xx", new Long(stats.getResponses1xx()).toString());
  	statsTree.put("Responses bytes total", new Long(stats.getResponsesBytesTotal()).toString());
  	statsTree.put("Statistics on milliseconds", new Long(stats.getStatsOnMs()).toString());
  	statsTree.put("Is shutdown", new Boolean(stats.isShutdown()).toString());
  	/* Get some (more) thread statistics and values from the abstract life
       cycle object. The abstract life cycle object is part of the connection
       statistics object. */		
		statsTree.put("State", new String(stats.getState()).toString()); 
		/* statsTree.put("Stop timeout", new Long(stats.getStopTimeout()).toString()); */ 
		statsTree.put("Is failed", new Boolean(stats.isFailed()).toString()); 
		statsTree.put("Is running", new Boolean(stats.isRunning()).toString());	 
		statsTree.put("Is started", new Boolean(stats.isStarted()).toString());	 
		statsTree.put("Is starting", new Boolean(stats.isStarting()).toString());	 
		statsTree.put("Is stopped", new Boolean(stats.isStopped()).toString());	 
		statsTree.put("Is stopping", new Boolean(stats.isStopping()).toString());	
  	/* Return the tree of statistics to the caller */
  	return statsTree;
  }
}
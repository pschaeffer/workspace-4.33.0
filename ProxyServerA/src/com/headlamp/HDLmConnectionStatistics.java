package com.headlamp;
import java.util.TreeMap;

import org.eclipse.jetty.io.ConnectionStatistics;
/**
 * Class for handling connection statistics  
 * 
 * No instances of this class are ever created. However, the static
 * methods of this class are used to obtain connection statistics. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmConnectionStatistics {
	/* This class can never be instantiated */
	private HDLmConnectionStatistics() {}
  /* Return a set of connection statistics to the caller. The caller provides
     a reference to a connection statistics object. This routine returns a tree
     map with the connection statistics. The statistics are returned as a tree 
     map so that they can be accessed in ascending name order. */ 
  protected static TreeMap<String, String> getStatistics(ConnectionStatistics stats) {
		if (stats == null) {
			String  errorText = "Connection statistics reference passed to getStatistics is null";
			throw new NullPointerException(errorText);		
		}
		/* Create the new tree map that is returned to the caller */
  	var  statsTree = new TreeMap<String, String>();
  	/* Get the connection statistics from the connection statistics object */
  	statsTree.put("Connection duration max", new Long(stats.getConnectionDurationMax()).toString());  
  	statsTree.put("Connection duration mean", new Double(stats.getConnectionDurationMean()).toString());
  	statsTree.put("Connection duration standard deviation", new Double(stats.getConnectionDurationStdDev()).toString());
  	statsTree.put("Connections", new Long(stats.getConnections()).toString());
  	statsTree.put("Connections max", new Long(stats.getConnectionsMax()).toString());
  	statsTree.put("Connections total", new Long(stats.getConnectionsTotal()).toString());
  	statsTree.put("Received bytes", new Long(stats.getReceivedBytes()).toString());
  	statsTree.put("Received bytes rate", new Long(stats.getReceivedBytesRate()).toString());
  	statsTree.put("Received messages", new Long(stats.getReceivedMessages()).toString());
  	statsTree.put("Received messages rate", new Long(stats.getReceivedMessagesRate()).toString());
  	statsTree.put("Sent bytes", new Long(stats.getSentBytes()).toString());
  	statsTree.put("Sent bytes rate", new Long(stats.getSentBytesRate()).toString());
  	statsTree.put("Sent messages", new Long(stats.getSentMessages()).toString());
  	statsTree.put("Sent messages rate", new Long(stats.getSentMessagesRate()).toString()); 
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
  	/* Return the tree of connection statistics to the caller */
  	return statsTree;
  }
}
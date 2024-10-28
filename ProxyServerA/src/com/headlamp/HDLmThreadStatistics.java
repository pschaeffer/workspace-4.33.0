package com.headlamp;
import java.util.TreeMap;

import org.eclipse.jetty.util.thread.MonitoredQueuedThreadPool;
/**
 * Class for handling thread statistics  
 * 
 * No instances of this class are ever created. However, the static
 * methods of this class are used to obtain thread statistics. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmThreadStatistics {
	/* This class can never be instantiated */
	private HDLmThreadStatistics() {}
  /* Return a set of thread statistics to the caller. The caller provides
     a reference to a monitored queued thread pool object. This routine 
     returns a tree map with the thread statistics. The statistics are 
     returned as a tree map so that they can be accessed in ascending name order. */ 
  protected static TreeMap<String, String> getStatistics(MonitoredQueuedThreadPool threadPool) {
		if (threadPool == null) {
			String  errorText = "Monitored queued thread pool reference passed to getStatistics is null";
			throw new NullPointerException(errorText);		
		}
		/* Create the new tree map that is returned to the caller */
  	var  statsTree = new TreeMap<String, String>();
  	/* Get some thread statistics and values from the monitored 
  	   queued thread pool object */ 
  	statsTree.put("Average queue latency", new Long(threadPool.getAverageQueueLatency()).toString());  
  	statsTree.put("Average task latency", new Long(threadPool.getAverageTaskLatency()).toString());  
  	statsTree.put("Maximum busy threads", new Long(threadPool.getMaxBusyThreads()).toString()); 
  	statsTree.put("Maximum queue latency", new Long(threadPool.getMaxQueueLatency()).toString()); 
  	statsTree.put("Maximum queue size", new Long(threadPool.getMaxQueueSize()).toString());
  	statsTree.put("Maximum task latency", new Long(threadPool.getMaxTaskLatency()).toString()); 
  	statsTree.put("Tasks", new Long(threadPool.getTasks()).toString());   	
  	/* Get some (more) thread statistics and values from the queued thread 
  	   pool object. The queued thread pool object is part of the monitored 
  	   queued thread pool object. */
  	statsTree.put("Busy threads", new Long(threadPool.getBusyThreads()).toString());  
  	statsTree.put("Idle threads", new Long(threadPool.getIdleThreads()).toString());  
  	statsTree.put("Idle timeout", new Long(threadPool.getIdleTimeout()).toString());
  	statsTree.put("Low threads threshold", new Long(threadPool.getLowThreadsThreshold()).toString());
  	statsTree.put("Maximum threads", new Long(threadPool.getMaxThreads()).toString());  
  	statsTree.put("Minimum threads", new Long(threadPool.getMinThreads()).toString());  
  	statsTree.put("Name", new String(threadPool.getName()).toString()); 
  	statsTree.put("Queue size", new Long(threadPool.getQueueSize()).toString());  
  	statsTree.put("Reserved threads", new Long(threadPool.getReservedThreads()).toString());  
  	statsTree.put("Threads", new Long(threadPool.getThreads()).toString()); 
  	statsTree.put("Threads priority", new Long(threadPool.getThreadsPriority()).toString());
  	statsTree.put("Is daemon", new Boolean(threadPool.isDaemon()).toString()); 
  	statsTree.put("Is detailed dump", new Boolean(threadPool.isDetailedDump()).toString()); 
  	statsTree.put("Is low on threads", new Boolean(threadPool.isLowOnThreads()).toString()); 
  	/* Get some (more) thread statistics and values from the container life
	     cycle object. The container life cycle object is part of the queued 
	     thread pool object. The calls below have been commented out. The calls
	     require a bean and we don't know which bean to pass. */
  	/* 
  	Collection<Object>  beans = threadPool.getBeans();
		statsTree.put("Is auto", new Boolean(threadPool.isAuto()).toString()); 
		statsTree.put("Is managed", new Boolean(threadPool.isManaged()).toString()); 
		statsTree.put("Is unmanaged", new Boolean(threadPool.isUnmanaged()).toString());
		*/ 
  	/* Get some (more) thread statistics and values from the abstract life
       cycle object. The abstract life cycle object is part of the container 
       life cycle object. */		
		statsTree.put("State", new String(threadPool.getState()).toString()); 
		statsTree.put("Stop timeout", new Long(threadPool.getStopTimeout()).toString()); 
		statsTree.put("Is failed", new Boolean(threadPool.isFailed()).toString()); 
		statsTree.put("Is running", new Boolean(threadPool.isRunning()).toString());	 
		statsTree.put("Is started", new Boolean(threadPool.isStarted()).toString());	 
		statsTree.put("Is starting", new Boolean(threadPool.isStarting()).toString());	 
		statsTree.put("Is stopped", new Boolean(threadPool.isStopped()).toString());	 
		statsTree.put("Is stopping", new Boolean(threadPool.isStopping()).toString());	 
  	/* Return the tree of connection statistics to the caller */
  	return statsTree;
  }
}
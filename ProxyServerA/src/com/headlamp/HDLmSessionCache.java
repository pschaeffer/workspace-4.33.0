package com.headlamp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
/** 
 * Class for supporting caching of session information 
 *  
 * No instances of this class are ever created. However, the static
 * information associated with this class is used. This class is used
 * to handle and maintain the session cache. A certain amount of 
 * information is cached for each session. The key is always the
 * session ID. The value is currently, the parameters array. This
 * might be different in the future. 
 * 
 * The cache used by this class is completely hidden internally and
 * never exposed. No method exposes the cache and/or returns a reference
 * to the cache. All methods are synchronized because the cache is not. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmSessionCache {
	/* The next statement initializes logging to some degree. Note that 
 	   having the slf4j jars and the log4j jars in the classpath also
	   plays some role in logging initialization. */
	private static final Logger LOG = LoggerFactory.getLogger(HDLmSessionCache.class);  
	/* This class can never be instantiated */
	private HDLmSessionCache() {}
	/* Build the (initially) empty cache of session information */
  private static Cache<String, HDLmSession>   sessionCache = CacheBuilder.newBuilder()
  		                                                                   .recordStats()
                                                                      	 .expireAfterAccess(7, TimeUnit.DAYS)
  		                                                                   .build();  
  /* Get a session cache entry from the session cache. This routine 
     returns null if the entry is not found. This is a low-level 
     routine that is actually passed a cache entry key.*/
  protected static synchronized HDLmSession  getIfPresentFromCache(final String cacheKey) {  	
  	/* Check if the cache key passed by the caller is null */
		if (cacheKey == null) {
			String  errorText = "Cache key reference passed to getIfPresentFromCache is null";
			throw new NullPointerException(errorText);
		}
		/* Set a boolean (not a Boolean) based on whether debug logging 
	     is enabled or not. This is used to avoid the overhead of
	     logging, when debug logging is not enabled. */
	  boolean   logIsDebugEnabled = LOG.isDebugEnabled();
	  if (logIsDebugEnabled)  
	    LOG.debug("Put cache key - " + cacheKey); 
		/* Get the cache entry from the cache. This routine
	     might not get an existing entry. Null is returned
	     if the entry does not exist. */ 
  	return sessionCache.getIfPresent(cacheKey); 
  }  
  /* Return a map with the contents of the cache. Each and
     every entry in the cache is copied into the map. The
     map is returned to the caller */ 
	protected static synchronized Map<String, HDLmSession>  getMap() {
		/* Create the map that is returned to the caller */
		Map<String, HDLmSession>  cacheMap = new HashMap<String, HDLmSession>();
		for (Map.Entry<String, HDLmSession> entry : sessionCache.asMap().entrySet()) {
	    String        key = entry.getKey();
	    HDLmSession   value = entry.getValue();
	    cacheMap.put(key, value);
	  }
		return cacheMap;
	}
  /* Add a cache entry to the cache. This code could actually replace an
	   existing cache entry. This is a low-level routine that is actually
	   passed a cache entry key. */
	protected static synchronized void  putToCache(final String cacheKey, 
			                                           final HDLmSession cacheValue) { 	 
		/* Check if the cache key passed by the caller is null */
		if (cacheKey == null) {
			String  errorText = "Cache key reference passed to putToCache is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the cache value passed by the caller is null */
		if (cacheValue == null) {
			String  errorText = "Cache value reference passed to putToCache is null";
			throw new NullPointerException(errorText);
		}
		/* Set a boolean (not a Boolean) based on whether debug logging 
		   is enabled or not. This is used to avoid the overhead of
		   logging, when debug logging is not enabled. */
		boolean   logIsDebugEnabled = LOG.isDebugEnabled();
		if (logIsDebugEnabled)  
		  LOG.debug("Put cache key - " + cacheKey); 
		/* Add the cache entry to the cache. This routine
		   might replace an existing entry. The same call 
		   is used in both cases (put). Of course, the caller
		   must provide a valid cache key and value. */ 
	  sessionCache.put(cacheKey, cacheValue);   
	}
  /* Return a set of statistics describing a cache. The caller does not
     provide a reference to the cache for which statistics are needed.
     The standard session information cache is always used. The statistics
     are returned as a tree map so that they can be accessed in ascending
     name order. */ 
  protected static synchronized TreeMap<String, String>  getStatistics() {
    var  cache = sessionCache;
  	var  cacheStatsTree = new TreeMap<String, String>();
  	/* Get the cache size. This is the only value that can be obtained
  	   without requesting cache statistics. */  	
  	cacheStatsTree.put("Size", new Long(cache.size()).toString());
  	/* Get the cache statistics */
  	CacheStats cacheStats = cache.stats();
  	cacheStatsTree.put("Average load penalty", new Double(cacheStats.averageLoadPenalty()).toString());
  	cacheStatsTree.put("Entries", new Long(cache.size()).toString());
  	cacheStatsTree.put("Eviction count", new Long(cacheStats.evictionCount()).toString());
  	cacheStatsTree.put("Hit count", new Long(cacheStats.hitCount()).toString());
  	cacheStatsTree.put("Hit rate", new Double(cacheStats.hitRate()).toString());
  	cacheStatsTree.put("Load count", new Long(cacheStats.loadCount()).toString());
  	cacheStatsTree.put("Load exception count", new Long(cacheStats.loadExceptionCount()).toString());
  	cacheStatsTree.put("Load exception rate", new Double(cacheStats.loadExceptionRate()).toString());
  	cacheStatsTree.put("Load success count", new Long(cacheStats.loadSuccessCount()).toString());
  	cacheStatsTree.put("Miss count", new Long(cacheStats.missCount()).toString());
  	cacheStatsTree.put("Miss rate", new Double(cacheStats.missRate()).toString());
  	cacheStatsTree.put("Request count", new Long(cacheStats.requestCount()).toString());
  	cacheStatsTree.put("Total load time", new Long(cacheStats.totalLoadTime()).toString());
  	/* Return the tree of cache statistics to the caller */
  	return cacheStatsTree;
  } 
}
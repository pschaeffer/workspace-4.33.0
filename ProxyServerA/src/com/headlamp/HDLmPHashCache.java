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
 * Class for supporting caching of perceptual hash information 
 * 
 * No instances of this class are ever created. However, the static
 * information associated with this class is used. This class is used
 * to handle and maintain the perceptual hash cache. The perceptual 
 * hash value is cached for each URL. The key is always the URL
 * value. The value is currently, the perceptual hash value. This
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
public class HDLmPHashCache {
	/* The next statement initializes logging to some degree. Note that 
 	   having the slf4j jars and the log4j jars in the classpath also
	   plays some role in logging initialization. */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(HDLmPHashCache.class);  
	/* This class can never be instantiated */
	private HDLmPHashCache() {}
	/* Build the (initially) empty cache of perceptual hash information */
  private static Cache<String, String>  phashCache = CacheBuilder.newBuilder()
  		                                                           .recordStats()
                                                               	 .expireAfterAccess(7, TimeUnit.DAYS)
  		                                                           .build();  
  /* Get a cache entry from the cache. This routine returns null if the entry 
     is not found. */
  protected static synchronized String getIfPresent(String urlStr) {  		 
  	return phashCache.getIfPresent(urlStr); 
  } 
  /* Return a map with the contents of the cache. Each and
     every entry in the cache is copied into the map. The
     map is returned to the caller */ 
	protected static synchronized Map<String, String> getMap() {
		/* Create the map that is returned to the caller */
		Map<String, String>   cacheMap = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : phashCache.asMap().entrySet()) {
	    String  key = entry.getKey();
	    String  value = entry.getValue();
	    cacheMap.put(key, value);
	  }
		return cacheMap;
	}
  /* Return a set of statistics describing a cache. The caller does not 
     provide a reference to the cache for which statistics are needed.
     The standard perceptual hash cache is always used. The statistics 
     are returned as a tree map so that they can be accessed in ascending
     name order. */ 
  protected static synchronized TreeMap<String, String> getStatistics() {
  	var  cache = phashCache;
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
	/* Return an array of strings where each array entry has a perceptual hash
     value. The array is sized based on the current number of perceptual has
     entries. Each string array entry has one perceptual hash value in it. */ 
	protected static synchronized String[] getValues() {
		/* Get a reference to the perceptual hash cache that
	     we want to find clusters from */
	  int   phashIndex = 0;
	  var   phashCacheSize = phashCache.size(); 
	  var   rawData = new String[(int) phashCacheSize]; 
	  /* Build the array of raw data from the cache entries */
	  for (var phashEntry : phashCache.asMap().entrySet()) {
	    rawData[phashIndex++] = phashEntry.getValue();    	
	  }		
	   return rawData; 
	}	
  /* Add a cache entry to the cache. This code could actually replace an
     existing cache entry. */
	protected static synchronized void put(String urlStr, String value) { 
	  phashCache.put(urlStr, value);   
	} 
}
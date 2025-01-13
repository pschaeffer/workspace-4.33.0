package com.headlamp;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class for supporting clustering of essentially anything
 * 
 * No instances of this class are ever created. However, the static
 * to handle and maintain clustering information. 
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmClustering {
	/* The next statement initializes logging to some degree. Note that 
 	   having the slf4j jars and the log4j jars in the classpath also
	   plays some role in logging initialization. */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(HDLmClustering.class);  
	/* This class can never be instantiated */
	private HDLmClustering() {}   
	/* This code finds the closest cluster for a given perceptual hash value.
     The caller provides the list of cluster and the hash value. This routine
     returns the closest matching cluster (if any). */ 
	protected static HDLmCluster findClosestCluster(ArrayList<HDLmCluster> clustersList, String searchStr) {
		/* Check if the list of clusters is valid or not */
		if (clustersList == null) {
			String  errorText = "List of clusters reference passed to findClosestCluster is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the search string is valid or not */
		if (searchStr == null) {
			String  errorText = "Search string reference passed to findClosestCluster is null";
			throw new NullPointerException(errorText);		
		}
		HDLmCluster  closestCluster = null;
		double       minSimilarity = Double.MAX_VALUE;
		/* Look for the closest matching cluster */
		for (int i = 0; i < clustersList.size(); i++) {
			HDLmCluster   currentCluster = clustersList.get(i);
			String        currentAverage = currentCluster.getClusterAverage();
			double currentSimilarity = HDLmHamming.distanceAdjusted(currentAverage, searchStr);
			if (currentSimilarity < minSimilarity) {
				minSimilarity = currentSimilarity;
				closestCluster = currentCluster;				
			}			
		}
		return closestCluster;
	}
  /* This method gets the average value from a list of samples  
     The technique is quite specific to perceptual hash values. 
     Each bit is processed separately. A count of true (1) values
     is made for each bit. If more than half of the entries are 
     true (1), then the bit is deemed to be on in the output bit
     array. 
     
     Of course, this routine returns a string of hexadecimal digits,
     not a bit array. The caller is required to pass at least one
     sample. An array list with zero values is an error condition. */
	protected static String getAverageBitValue(ArrayList<String> samples) {
		/* Check if the samples array list is valid or not */
		if (samples == null) {
			String  errorText = "Samples reference passed to getAverageBitValue is null";
			throw new NullPointerException(errorText);		
		}  	
		/* Check if the sample count is valid or not */ 
		if (samples.size() == 0) {
			String  errorText = String.format("Number of samples (%d) in getAverageBitValue is less than one", 
					                             samples.size());
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
	  }
	  /* Define a few initial values */
	  int     sampleSize = samples.size(); 
	  int     sampleBitLength = samples.get(0).length() << 2;
	  int[]   bitCountArray = new int[sampleBitLength];
	  Arrays.fill(bitCountArray, 0);
	  /* Scan all of the array entries, adding up all of the bit values */ 
		for (int i = 0; i < sampleSize; i++) {
			/* Save a reference to the current sample */
		  String  currentSample = samples.get(i);
			String  currentSampleString = currentSample;		
			BigInteger  currentSampleBig = new BigInteger(currentSampleString, 16);
			/* Process all of the bits in the current sample */
			for (int j = 0; j < sampleBitLength; j++) {
				if (currentSampleBig.testBit(j)) 
					bitCountArray[j]++;			
			}
		}		
		/* Build the final return value and return it to the caller */
		BigInteger  finalBig = new BigInteger("0");
		for (int j = 0; j < sampleBitLength; j++) {
			if ((bitCountArray[j] << 1) >= sampleSize)
				finalBig = finalBig.setBit(j);
		}
	  return finalBig.toString(16);
	}   
	/* This method gets a count of clusters from a data array and returns 
     the cluster count to the caller */
  protected static int getClusterCount(String[] sampleArray, 
  		                                 double sampleThreshold) {
  	return sampleArray.length;
  } 
  /* This method gets the list of clusters from a sample data Array and returns 
     the cluster list to the caller. This is the lowest level routine for finding
     clusters. This routine does not enforce any limits as to the maximum number 
     of clusters and can return a cluster list much longer than the limit value
     allows. Other routines must enforce the maximum number of clusters limit.  */
	protected static ArrayList<HDLmCluster> getClustersLow(String[] sampleArray, 
	 	                                                     double similarityThreshold,
		                                                     HDLmClusterComparison comparisonInterface) {
  	/* Check if the raw data array is valid or not */
  	if (sampleArray == null) {
			String  errorText = "Sample array reference passed to getClustersLow is null";
			throw new NullPointerException(errorText);		
		}  	
		/* Check if the sample threshold is valid or not */ 
		if (similarityThreshold < 0.0 || similarityThreshold > 1.0) {
			String  errorText = String.format("Similarity threshold value (%f) passed to getClustersLow is" + 
		                                   "less than zero or greater than one", 
					                             similarityThreshold);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the comparison interface is valid or not */
  	if (comparisonInterface == null) {
			String  errorText = "Comparison interface passed to getClustersLow is null";
			throw new NullPointerException(errorText);		
		}
	  /* Allocate the initial, empty cluster list */
		ArrayList<HDLmCluster>  clusterList = new ArrayList<HDLmCluster>();
		int   sampleSize = sampleArray.length;
		/* Allocate and fill an array of booleans showing that none of the samples
		   have been processed / checked (actually assigned to a cluster) so far */
		boolean[]   sampleAssigned = new boolean[sampleSize];
		Arrays.fill(sampleAssigned, false);		 
		/* Scan all of the array entries looking for new clusters */
		for (int i = 0; i < sampleSize; i++) {
			/* Check if the current entry has already been assigned to a cluster */
			if (sampleAssigned[i])
				continue;
			/* Save a reference to the current sample */
			String  currentSample = sampleArray[i];
			/* Since the current entry has not been used, we must create a new cluster.
		     The new cluster will always contain at least the current array entry.
		     Of course, the new cluster may contain many other array entries as
		     well. */
		  HDLmCluster   newCluster = new HDLmCluster();
		  /* Allocate a array for all of the entries of the current cluster. 
		     This list is used later to find the minimum similarity, maximum
		     similarity, and average for the current cluster. It could be used
		     for anything else as well. This list is not kept and is built
		     fresh for each cluster. */
			ArrayList<String>  newClusterEntries = new ArrayList<String>();			
			int   clusterCount = 1;			
			/* Add the current entry to the list of entries for the current cluster */
			newCluster.addEntry(i);
			newClusterEntries.add(currentSample);
			/* Scan all of the remaining entries looking for members of the current 
			   cluster */ 
			for (int j = i+1; j < sampleSize; j++) {
				/* compare the current and test samples checking if the current sample
				   is part of the current cluster */
				String  testSample = sampleArray[j];
				double  similarity = comparisonInterface.compare(currentSample, testSample);
				if (similarity <= similarityThreshold) {
					clusterCount++;
					sampleAssigned[j] = true;	
					newCluster.addEntry(j);
					newClusterEntries.add(testSample);
				}				
			}			
			/* Get the minimum perceptual hash similarity value */
			double  minSimilarity = getMinSimilarity(newClusterEntries, comparisonInterface);
			if (minSimilarity >= 0.0)
			  newCluster.setClusterMinimum(minSimilarity);
			/* Get the maximum perceptual hash similarity value */
			double  maxSimilarity = getMaxSimilarity(newClusterEntries, comparisonInterface);
			if (maxSimilarity >= 0.0)
			  newCluster.setClusterMaximum(maxSimilarity);
			/* Get the average value for all of the entries. A meaningful average
			   can not always be determined. However, the routine called below
			   will always return something. */ 
			String  averageValue = getAverageBitValue(newClusterEntries);  
			newCluster.setClusterAverage(averageValue);
			newCluster.setClusterName(averageValue);
			/* Store the final count of cluster entries in the new cluster */
			newCluster.setClusterSize(clusterCount);
			clusterList.add(newCluster);			
		}		
	  return clusterList;
	}
	/* This method gets the list of clusters using default sources and default values.
	   This method does not enforce the limit on the maximum number of clusters. However,
	   it calls other routines that do enforce the limit on the maximum number of clusters. */ 
	protected static ArrayList<HDLmCluster> getClustersDefault() {
		/* Get an array of strings where each string contains a perceptual 
		   hash value */ 
    var   rawData = HDLmPHashCache.getValues();		
	  /* Get a reference to the set of clusters we want to display */
	  var  maximumClusters = HDLmConfigInfo.getClustersMaxCount();
	  HDLmClusterComparison   similarityLambda = (first, second) -> {return HDLmHamming.distanceAdjusted(first, second);};
	  var  clustersList = HDLmClustering.getClustersList(rawData, 
                                                       maximumClusters,
                                                       similarityLambda);	
	  return clustersList;
	}
  /* This method gets the list of clusters from a raw data array and returns 
     the cluster list to the caller. This routine does not enforce any limit
     on the number of clusters. The list of clusters returned by this routine
     can be much longer than the maximum number of clusters allows for. Other 
     routines must enforce the maximum number of clusters limit.*/
  protected static ArrayList<HDLmCluster> getClustersFromArray(String[] rawDataArray, 
	  	                                                         double similarityThreshold,
	  	                                                         HDLmClusterComparison comparisonInterface) {
  	/* Check if the raw data array is valid or not */
  	if (rawDataArray == null) {
			String  errorText = "Raw data array reference passed to getClustersFromArray is null";
			throw new NullPointerException(errorText);		
		}  	
		/* Check if the similarity threshold is valid or not */ 
		if (similarityThreshold < 0.0 || similarityThreshold > 1.0) {
			String  errorText = String.format("Similarity threshold value (%f) passed to getClustersFromArray is " + 
		                                   "less than zero or greater than one", 
					                             similarityThreshold);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the comparison interface is valid or not */
  	if (comparisonInterface == null) {
			String  errorText = "Comparison interface passed to getClustersFromArray is null";
			throw new NullPointerException(errorText);		
		} 
  	/* Allocate the initial, empty cluster list */
  	ArrayList<HDLmCluster>  clusterList = new ArrayList<HDLmCluster>();
  	/* Get the cluster sample size */
  	int   sampleCount = HDLmConfigInfo.getClustersSampleSize();
  	/* The first step is always to get the sample data array from the
  	   raw data array. The raw data array might be huge (say 1 million
  	   entries). The sample data array will always be a reasonable size
  	   (say 100 entries or less). */ 
  	String[]  sampleArray = HDLmClustering.getSamples(rawDataArray, sampleCount);
  	/* Get the list of clusters from the samples */
  	clusterList = HDLmClustering.getClustersLow(sampleArray, similarityThreshold, comparisonInterface);
  	return clusterList;
  }  
  /* This method gets the list of clusters from a raw data array and returns 
	   the cluster list to the caller. This routine does enforce the limit on
	   the number of clusters. The list of clusters returned by this routine
	   is subject to the maximum number of clusters limit (passed by the caller). */
	protected static ArrayList<HDLmCluster> getClustersList(String[] rawDataArray, 
	 	                                                      int maxClustersLimit, 
	 	                                                      HDLmClusterComparison comparisonInterface) {
		/* Check if the raw data array is valid or not */
		if (rawDataArray == null) {
			String  errorText = "Raw data array reference passed to getClustersList is null";
			throw new NullPointerException(errorText);		
		}  	
		/* Check if the maximum number of clusters is valid or not */ 
		if (maxClustersLimit < 1) {
			String  errorText = String.format("Maximum number of clusters value (%d) passed to getClustersList is " + 
		                                   "less than one", 
					                             maxClustersLimit);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the comparison interface is valid or not */
		if (comparisonInterface == null) {
			String  errorText = "Comparison interface passed to getClustersList is null";
			throw new NullPointerException(errorText);		
		} 
		/* Allocate the initial, empty cluster list */
		ArrayList<HDLmCluster>  clustersList = new ArrayList<HDLmCluster>();
		var  similarityThreshold = HDLmConfigInfo.getClustersThreshold();
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Get the list of clusters from the samples */
			clustersList = HDLmClustering.getClustersFromArray(rawDataArray, 
					                                               similarityThreshold, 
					                                               comparisonInterface);
			/* Check if the number of clusters is acceptable or not */
			if (clustersList.size() <= maxClustersLimit)
				break;
			if (similarityThreshold >= 1.0)
				break;
			/* Raise the similarity threshold and try again */
			similarityThreshold += 0.1;
		}
		return clustersList;
	}  
  /* This method gets the maximum similarity value from a list of samples. 
     Of course, each entry is not compared with itself. If the list of 
     samples only contains one entry, then a minus one value will be
     returned to the caller. */ 
	protected static double getMaxSimilarity(ArrayList<String>  samples,		                                            
			                                     HDLmClusterComparison comparisonInterface) {
		/* Check if the samples array list is valid or not */
		if (samples == null) {
			String  errorText = "Samples reference passed to getMaxSimilarity is null";
			throw new NullPointerException(errorText);		
		} 		
		/* Check if the sample count is valid or not */ 
		if (samples.size() == 0) {
			String  errorText = String.format("Number of samples (%d) in getMaxSimilarity is less than one", 
					                             samples.size());
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
	  }
		/* Check if the comparison interface is valid or not */
		if (comparisonInterface == null) {
			String  errorText = "Comparison interface passed to getMaxSimilarity is null";
			throw new NullPointerException(errorText);		
		}
	 /* Define a few initial values */
		double  sampleMax = -Double.MAX_VALUE;
		int     sampleSize = samples.size(); 
		/* Check if we only have one sample. One sample can not be compared 
	     to itself. */
	  if (sampleSize == 1) 
		  return -1.0;	
		/* Scan all of the array entries looking for the maximum value */
		for (int i = 0; i < sampleSize; i++) {
			/* Save a reference to the current sample */
			String  currentSample = samples.get(i);
			/* Scan all of the remaining entries looking for a new imum similarity 
			   value */  
			for (int j = i+1; j < sampleSize; j++) {
				/* compare the current and test samples checking if the current sample
				   and the test sample have a new maximum similarity value */
				String  testSample = samples.get(j); 
				double  similarity = comparisonInterface.compare(currentSample, testSample);
				if (similarity > sampleMax) 
					sampleMax = similarity;			
			}					
		}		
	  return sampleMax; 
	} 
  /* This method gets the minimum similarity value from a list of samples. 
     Of course, each entry is not compared with itself. If the list of 
     samples only contains one entry, then a minus one value will be
     returned to the caller. */ 
	protected static double getMinSimilarity(ArrayList<String>  samples,		                                            
			                                     HDLmClusterComparison comparisonInterface) {
		/* Check if the samples array list is valid or not */
		if (samples == null) {
			String  errorText = "Samples reference passed to getMinSimilarity is null";
			throw new NullPointerException(errorText);		
		}  	
		/* Check if the sample count is valid or not */ 
		if (samples.size() == 0) {
			String  errorText = String.format("Number of samples (%d) in getMinSimilarity is less than one", 
					                             samples.size());
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
	  }
		/* Check if the comparison interface is valid or not */
		if (comparisonInterface == null) {
			String  errorText = "Comparison interface passed to getMinSimilarity is null";
			throw new NullPointerException(errorText);		
		}
	  /* Define a few initial values */
		double  sampleMin = Double.MAX_VALUE;
		int     sampleSize = samples.size();
		/* Check if we only have one sample. One sample can not be compared 
		   to itself. */
		if (sampleSize == 1) 
			return -1.0;			
		/* Scan all of the array entries looking for the minimum value */
		for (int i = 0; i < sampleSize; i++) {
			/* Save a reference to the current sample */
			String  currentSample = samples.get(i);
			/* Scan all of the remaining entries looking for a new minimum similarity 
			   value */  
			for (int j = i+1; j < sampleSize; j++) {
				/* compare the current and test samples checking if the current sample
				   and the test sample have a new minimum similarity value */
				String  testSample = samples.get(j); 
				double  similarity = comparisonInterface.compare(currentSample, testSample);
				if (similarity < sampleMin) 
					sampleMin = similarity;			
			}					
		}		
	  return sampleMin; 
	}  
	/* The next routine get a set of samples from a array of raw data. The 
     raw data array might be very large. It could have millions of values.
     We need to get just a sample of the data from the array. */
  protected static String[] getSamples(String[] rawData, int sampleCount) {
  	/* Check if the raw data array is valid or not */
		if (rawData == null) {
			String  errorText = "Raw data array reference passed to getSamples is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the sample count is valid or not */ 
		if (sampleCount < 0) {
			String  errorText = String.format("Sample count value value (%d) passed to getSamples is less than zero", 
					                             sampleCount);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Define a few variables */
		double  sampleIndex = 0.0;
		double  sampleJump = 1.0;
		/* Get the actual size of the raw data array */
		int   rawDataCount = rawData.length;
		/* Check if the number of requested samples exceeds the actual raw data array 
		   size */
		if (sampleCount > rawDataCount) 
			sampleCount = rawDataCount;
		/* Create the sample array that is returned to the caller */	
  	String[]  sampleArray = new String[sampleCount];  	
  	/* Calculate how much we need to jump to get each sample */
  	if (sampleCount == rawDataCount ||
  			sampleCount == 0)
  		sampleJump = 1.0;
  	else if (sampleCount < rawDataCount)
  		sampleJump = ((double) rawDataCount) / ((double) sampleCount);
  	/* Build the array of samples */
  	for (int i = 0; i < sampleCount; i++) {
  		sampleArray[i] = rawData[(int) Math.floor(sampleIndex)];
  		sampleIndex += sampleJump;  		
  	}
  	return sampleArray;
  }
}
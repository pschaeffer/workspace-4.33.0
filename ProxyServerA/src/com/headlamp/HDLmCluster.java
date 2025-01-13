package com.headlamp;
import java.util.ArrayList;
/**
 * HDLmCluster short summary.
 *
 * HDLmCluster description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmCluster {
	/* An instance of this class is created for each cluster in 
	   clustered data. The number of clusters can be zero or more
	   than zero. */ 
	/* The cluster name can be anything. It could be a file name.
	   It could be a sample cluster value. It could be a URL name.
	   It need only be a string. */
	private String  clusterName = null;
	/* The cluster average value has a pretty obvious meaning if the 
	   number of entries (cluster size) is one or if we are handling 
	   values with a traditional numeric value (say double-precision
	   floating-point values). However, in other cases, the average
	   value may be much less clear. Say we are handling a list of
	   strings made up of words. What exactly is the average? In some
	   cases, we may have to just pick one value and call it the 
	   average. 
	   
	   For perceptual hash values, the average is really the average
	   value of each bit, rounded up to 1 or down to 0 as need be.
	   In other words, all of the perceptual hash values are added
	   together will no carry operations between bit positions and
	   the number of 1 bits in each position is determined. If the
	   number of 1 bits for any bit position is half or more, the
	   average for that bit position is assumed to be 1. If the
	   number of 1 bits for any bit position is less than half,
	   the average for that bit position is assumed to be zero. */ 
	private String  clusterAverageValue = null;
	/* The minimum and maximum disimilarity values only have a 
	   meaning if the cluster size is greater than one. If the 
	   cluster size is just one, then these value can not be 
	   determined. */ 
	private double  clusterMinSimilarity = -1.0;
	private double  clusterMaxSimilarity = -1.0;
	/* The cluster size value can always be determined. The minimum
	   value is always one. Of course, there really is no maximum 
	   value. */
	private int     clusterSize = -1;
	/* The list of cluster entries is built during the process of
	   determining the number of clusters. The list may or may 
	   not be empty. The entries in the list are actually index
	   values in the sample array. The list will be created and
	   used for the first entry in a cluster. It may not be created
	   or used for all of the later entries in a cluster. Of course,
	   the entry that is used to create a cluster, will be added to
	   the list. That means that the list will (presumably) have one
	   or more entries. */ 
	private ArrayList<Integer>  clusterEntries = null;

	/* Add a new list entry to the current cluster list. This is a 
	   list entry that is associated with the cluster. Note that the
	   code below allocates cluster entries list if need be. */
	protected void addEntry(int entryNumber) {
		/* Check if the entry number is valid or not */ 
		if (entryNumber < 0) {
			String  errorText = String.format("Entry number value (%d) passed to addEntry is less than zero", 
					                             entryNumber);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the list of cluster entries has already been allocated or not */
		if (this.clusterEntries == null)
			this.clusterEntries = new ArrayList<Integer>();
		/* Add the new entry to the list of cluster entries */
		this.clusterEntries.add(entryNumber);		
  }	 	
	/* Get the cluster average (if any) and return it to the caller */
	protected String getClusterAverage() {
		return clusterAverageValue;
	}
	/* Get the cluster name (if any) and return it to the caller */
	protected String getClusterName() {
		return clusterName;
	}
	/* Get the cluster size and return it to the caller */
	protected int getClusterSize() {
		return clusterSize;
	}
	/* Get the cluster maximum similarity and return it to the caller */
	protected double getClusterMax() {
		return clusterMaxSimilarity;
	}
	/* Get the cluster minimum similarity and return it to the caller */
	protected double getClusterMin() {
		return clusterMinSimilarity;
	}
	/* Set or reset the cluster average value. Note that the caller 
	   can not pass a null value for the new cluster average value.
	   This is an error condition. */
	protected void setClusterAverage(String newClusterAverage) {
		if (newClusterAverage == null) {
			String  errorText = "New cluster average value string is null";
			throw new NullPointerException(errorText);
		}
		clusterAverageValue = newClusterAverage;
	}	
	/* Set or reset the cluster maximum similarity value. Note that
     the caller can not pass a negative value or a value greater 
     than 1.0 for the new maximum cluster similarity value. This
     is an error condition. */
	protected void setClusterMaximum(double newClusterMaximum) {
		if (newClusterMaximum < 0.0 || 
				newClusterMaximum > 1.0) {
			String  errorText = String.format("New cluster maximum similarity value (%f) is less than zero or greater than one",
					                              newClusterMaximum);
			throw new NullPointerException(errorText);
		}
		clusterMaxSimilarity = newClusterMaximum;
	}
	/* Set or reset the cluster minimum similarity value. Note that
	   the caller can not pass a negative value or a value greater 
	   than 1.0 for the new minimum cluster similarity value. This
	   is an error condition. */
	protected void setClusterMinimum(double newClusterMinimum) {
		if (newClusterMinimum < 0.0 || 
				newClusterMinimum > 1.0) {
			String  errorText = String.format("New cluster minimum similarity value (%f) is less than zero or greater than one",
					                              newClusterMinimum);
			throw new NullPointerException(errorText);
		}
		clusterMinSimilarity = newClusterMinimum;
	}	
	/* Set or reset the cluster name. Note that the caller can 
     not pass a null value for the new cluster name. This is 
     an error condition. */
	protected void setClusterName(String newClusterName) {
		if (newClusterName == null) {
			String  errorText = "New cluster name string is null";
			throw new NullPointerException(errorText);
		}
		clusterName = newClusterName;
	}	
	/* Set or reset the cluster size. The cluster size is the number
	   of entries in the cluster. Note that the caller can not pass
	   a zero or negative value for the new cluster size. This is an
	   error condition. */
	protected void setClusterSize(int newClusterSize) {
		if (newClusterSize < 1) {
			String  errorText = String.format("New cluster size (%d) is less than one",
					                newClusterSize);
			throw new NullPointerException(errorText);
		}
		clusterSize = newClusterSize;
	}	 
}
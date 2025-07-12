package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.eatthepath.imagehash.PHashTestExample;
/**
 * HDLmClusteringTest short summary.
 *
 * HDLmClusteringTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmClusteringTest {
	private static String[]  rawSamples = null;
	@BeforeAll
	static void beforeAll() {		
		/* Build an instance of the perceptual hash class for use below */
		PHashTestExample pHashTest = new PHashTestExample();
		/* Load all of the images and get perceptual hash values for each one */
		HashMap<String, Image>    imagesLoad = pHashTest.getImages();
		HashMap<String, String>   imagesPHash = new HashMap<String, String>();
		pHashTest.loadImages(imagesLoad);
		pHashTest.buildPHashImages(imagesLoad, imagesPHash);	
		/* Get the number of images and allocate the raw samples array */
		int   sampleIndex = 0;
		int   imagesPHashSize = imagesPHash.size();
		rawSamples = new String[imagesPHashSize];
		/* Add each element to the raw samples array */
	  for (Map.Entry<String, String> mapEntry : imagesPHash.entrySet()) {  	
	  	@SuppressWarnings("unused")
	  	String  entryName = mapEntry.getKey();
	  	String  entryHex = mapEntry.getValue();
	  	rawSamples[sampleIndex++] = entryHex;
		}	
	}
	@Test
	void getClustersFromArray() {		
		/* Run a few getClustersFromArray tests */
	  ArrayList<HDLmCluster>  clusterList = null; 
	  HDLmCluster             clusterEntry;
	  double                  clusterMax;
	  double                  clusterMin;
	  int                     clusterListSize;
	  int                     clusterSize;
	  String                  clusterAverage;
	  String                  clusterName;
		/* Run a getClustersFromArray test */
	  int   maxClustersLimit = HDLmConfigInfo.getClustersMaxCount();
	  clusterList = HDLmClustering.getClustersList(rawSamples, 
	  		                                         maxClustersLimit,
	  		                                         (first, second) -> {return HDLmHamming.distanceAdjusted(first, second);});
	  clusterListSize = clusterList.size();
	  assertEquals(clusterListSize, 10); 
	  clusterEntry = clusterList.get(0);
	  clusterName = clusterEntry.getClusterName();
	  assertEquals(clusterName, "d065769ad262b2d9");	
	  clusterSize = clusterEntry.getClusterSize();
	  assertEquals(clusterSize, 1);
	  clusterAverage = clusterEntry.getClusterAverage();
	  assertEquals(clusterAverage, "d065769ad262b2d9");
	  clusterMin = clusterEntry.getClusterMin();
	  assertEquals(clusterMin, -1.0);
	  clusterMax = clusterEntry.getClusterMax();
	  assertEquals(clusterMax, -1.0);
	  clusterEntry = clusterList.get(1);
	  clusterName = clusterEntry.getClusterName();
	  assertEquals(clusterName, "ce273873bfbc76fb");	  
	  clusterSize = clusterEntry.getClusterSize();
	  assertEquals(clusterSize, 4);
	  clusterAverage = clusterEntry.getClusterAverage();
	  assertEquals(clusterAverage, "ce273873bfbc76fb");
	  clusterMin = clusterEntry.getClusterMin();
	  assertEquals(clusterMin, 0.0);
	  clusterMax = clusterEntry.getClusterMax();
	  assertEquals(clusterMax, 0.0625);	  
	}	
  {
		Throwable exception = assertThrows(NullPointerException.class, 
				                               () -> {HDLmClustering.getClustersFromArray(null, 0.0, null);},
				                               "Expected NullPointerException");
		String execMsg = exception.getMessage();
		assertEquals("Raw data array reference passed to getClustersFromArray is null", execMsg,
			          "Unexpected exception message");
	}
	{
		String[]  emptyStringArray = new String[0];
		Throwable exception = assertThrows(IllegalArgumentException.class, 
				                               () -> {HDLmClustering.getClustersFromArray(emptyStringArray, -0.1, null);},
				                               "Expected IllegalArgumentException");
		String execMsg = exception.getMessage();
		assertEquals("Similarity threshold value (-0.100000) passed to getClustersFromArray is less than zero or greater than one", execMsg,
		          	 "Unexpected exception message");
	}
	{
	  String[]  emptyStringArray = new String[0];
	  Throwable exception = assertThrows(IllegalArgumentException.class, 
			                                 () -> {HDLmClustering.getClustersFromArray(emptyStringArray, 1.1, null);},
			                                 "Expected IllegalArgumentException");
	  String execMsg = exception.getMessage();
	  assertEquals("Similarity threshold value (1.100000) passed to getClustersFromArray is less than zero or greater than one", execMsg,
	  		         "Unexpected exception message");
  }
	{
		String[]  emptyStringArray = new String[0];
		Throwable exception = assertThrows(NullPointerException.class, 
				                               () -> {HDLmClustering.getClustersFromArray(emptyStringArray, 0.5, null);},
				                               "Expected NullPointerException");
		String execMsg = exception.getMessage();
		assertEquals("Comparison interface passed to getClustersFromArray is null", execMsg,
				         "Unexpected exception message");
	}
}
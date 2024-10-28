package com.eatthepath.imagehash;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.headlamp.HDLmEvent;
/**
 * An example demonstrating how images can be loaded and pHash values can be obtained 
 */
public class PHashTestExample {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(PHashTestExample.class);  
	/* This is the standard constructor for this class. At present
     it does not do anything. */
	public PHashTestExample() {}	
	/* Images used for testing */
	private HashMap<String, Image> images = new HashMap<String, Image>();	
	/* Build a hasher */
  private PHashImageHash hasher = new PHashImageHash();
  /* This is the main program that actually gets run when this class 
     is used as an execution target */  
	public static void main(String[] args) {
		PHashTestExample pHashTest = new PHashTestExample();
		pHashTest.loadImages(pHashTest.images);
		pHashTest.processImages(pHashTest.images);
	}
	/* Get the images value and return it to the caller */
	public HashMap<String, Image> getImages() {
		return images;
	}
	/* Build a map of all of the perceptual hash values for each image */ 
	public void buildPHashImages(HashMap<String, Image> imagesMap,
			                         HashMap<String, String>  pHashMap) {
	  for (Map.Entry<String, Image> mapEntry : imagesMap.entrySet()) {  	
	  	String         entryName = mapEntry.getKey();
	  	Image          entryImage = mapEntry.getValue();
	  	long           hashValue = hasher.getPerceptualHash(entryImage); 
	  	String         hashHex = Long.toHexString(hashValue);
	  	pHashMap.put(entryName, hashHex);
		}		 
	}	
	/* Load all of the images we need to process later */
	public void loadImages(HashMap<String, Image> imagesMap) {
		/* Declare and define a variable for use below */
		String  fileName = null;
		/* Run the image processing code in a try block */
		try {
			Image   image = null;
			String  imageFolder = "/Users/pscha/HeadlampJetty/TestImages/";
      File    folder = new File(imageFolder);
      File[]  listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
	      if (file.isFile()) {
		      fileName = file.getName();
		      image = ImageIO.read(new File(imageFolder + fileName));
		      imagesMap.put(fileName, image);
			  }
			}	
		} 
		catch (Exception exception) {
			if (fileName != null)
			  LOG.info("File Name - " + fileName);
			LOG.info("Exception while executing loadImages()");
	 		LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");	
			exception.printStackTrace();
		}
	}
	/* Process all of the images that were loaded earlier */
	public void processImages(HashMap<String, Image> imagesMap) {
	  for (Map.Entry<String, Image> mapEntry : imagesMap.entrySet()) {  	
	  	String         entryName = mapEntry.getKey();
	  	Image          entryImage = mapEntry.getValue();
	  	long           hashValue = hasher.getPerceptualHash(entryImage); 
	  	System.out.println(entryName + " " + Long.toHexString(hashValue));
		}		 
	}
}
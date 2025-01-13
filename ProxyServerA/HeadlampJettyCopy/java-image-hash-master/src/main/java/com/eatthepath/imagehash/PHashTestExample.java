package com.eatthepath.imagehash;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * An example demonstrating how images can be loaded and pHash values can be obtained 
 */
public class PHashTestExample {

	/* Build a hasher */
	private PHashImageHash hasher = new PHashImageHash();

	// Images used for testing
	private HashMap<String, Image> images = new HashMap<String, Image>();
	
	/* This is the standard constructor for this class. At present
	   it does not do anything. */
	public PHashTestExample() { }

	public static void main(String[] args) {
		PHashTestExample pHashTest = new PHashTestExample();
		pHashTest.loadImages(pHashTest.images);
		pHashTest.processImages(pHashTest.images);
	}
	/* Load all of the images we need to process later */
	private void loadImages(HashMap<String, Image> imagesMap) {
		try {
			Image   image = null;
			String  imageFolder = "/Users/Peter/HeadlampJetty/TestImages/";
      File    folder = new File(imageFolder);
      File[]  listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
	      if (file.isFile()) {
		      String  fileName = file.getName();
		      image = ImageIO.read(new File(imageFolder + fileName));
		      imagesMap.put(fileName, image);
			  }
			}	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* Process all of the images that were loaded earlier */
	private void processImages(HashMap<String, Image> imagesMap) {
	  for (Map.Entry<String, Image> mapEntry : imagesMap.entrySet()) {  	
	  	String         entryName = mapEntry.getKey();
	  	Image          entryImage = mapEntry.getValue();
	  	long           hashValue = hasher.getPerceptualHash(entryImage); 
	  	System.out.println(entryName + " " + Long.toHexString(hashValue));
		}		 
	}
}
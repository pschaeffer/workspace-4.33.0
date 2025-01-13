package com.headlamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class for supporting hamming distances  
 * 
 * No instances of this class are ever created. However, the static
 * information associated with this class is used. This class is used
 * to determine hamming distance values.  
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmHamming {
	/* The next statement initializes logging to some degree. Note that 
 	   having the slf4j jars and the log4j jars in the classpath also
	   plays some role in logging initialization. */
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(HDLmHamming.class);  
	/* This class can never be instantiated */
	private HDLmHamming() {}
	/* This is the base hamming distance algorithm. This code
	   assumes both input values can be stored in a Java long
	   (non-Object version of Long) variables. */
	protected static int distance(long first, long second) {
  	/* Run the first xor */
	  long  value = first ^ second;
	  int   distance = 0;
	  /* Find all of the bit mismatches */
	  while (value > 0) {
	    value &= value - 1;
	    distance++;
	  }
    /* Return the final count (distance) value */
    return distance;
  }
  /* This is the adjusted hamming distance algorithm. This code
     assumes both input values are (possibly long) hexadecimal
     strings. These strings can be of any length. However, they
     must be the same length. The hamming distance is calculated
     and then divided by the length of each input string in bits. */
  protected static double distanceAdjusted(String first, String second) {
    /* Run the raw hamming distance value */
    double distance = distanceLong(first, second);
    /* Return the final distance value */
    return distance/(4.0 * first.length());
  } 
  /* This is the long hamming distance algorithm. This code
		 assumes both input values are (possibly long) hexadecimal
		 strings. These strings can be of any length. However, they
		 must be the same length. */
	protected static double distanceLong(String first, String second) {
	  double  finalDistance = 0;
	  String  firstSub = null; 
	  String  secondSub = null;
	  /* Process each string value */
	  while (first.length() > 0) {
	    /* Check if we have more than 8 hexadecimal digits left */
	    if (first.length() > 8) {
	      firstSub = first.substring(0, 8);
	      first = first.substring(8);
	      secondSub = second.substring(0, 8);
	      second = second.substring(8);
	    }
	    /* Use the first set of hexadecimal digits */
	    else {
	      firstSub = first;
	      first = "";
	      secondSub = second;
	      second = "";
	    }
	  }
	  /* Get the hamming distance between the two longs */
	  long  firstLong = Long.parseLong(firstSub, 16);
	  long  secondLong = Long.parseLong(secondSub, 16); 
	  finalDistance += distance(firstLong, secondLong);	
	  /* Return the final count value */
	  return finalDistance;
	}
}
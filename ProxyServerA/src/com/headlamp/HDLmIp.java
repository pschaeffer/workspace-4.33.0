package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmIp short summary.
 *
 * HDLmIp description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class can ever be
   created */
public class HDLmIp {
	/* The next statement initializes logging to some degree. Note that having the
	   slf4j jars and the log4j jars in the classpath also plays some role in
	   logging initialization.	 */
	private static final Logger LOG = LoggerFactory.getLogger(HDLmIp.class);
	/* This class can never be instantiated */
	private HDLmIp() {}
	/* This static method get the IP current IP address. The IP 
	   address is returned to the caller as a string using dot
	   notation. */
	protected static String  getCurrentIpAddress() {
	  return null;
	}
}
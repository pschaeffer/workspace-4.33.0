package com.headlamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmConfigValue short summary.
 *
 * HDLmConfigValue description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmConfigValue {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmConfigValue.class); 
	/* An instance of this class is created for each configuration entry. 
	   This structure holds the configuration name and the actual value. */
	private String  configName = null;
	private String  configValue = null;
	/* This constructor is used to create an instance of this class 
	   with a configuration name and a configuration value. The caller
	   provides the configuration name and configuration value. */
	protected HDLmConfigValue(final String newConfigName, final String newConfigValue) {
		/* Check if the caller passed a null configuration name */
		if (newConfigName == null) {
			String  errorText = "New configuration name string is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the caller passed a null configuration value */
		if (newConfigValue == null) {
			String  errorText = "New config Value string is null";
			throw new NullPointerException(errorText);
		}
		/* Set the configuration name and configuration value */
		configName = newConfigName;
		configValue = newConfigValue;
	}		 
	/* Get a few values */
	protected String       getconfigName() {
		return configName;
	}
	protected String       getconfigValue() {
		return configValue;
	}	
	/* Set or reset the configuration name. Note that the caller can 
	   not pass a null value for the new configuration name. This is 
	   an error condition. */
	protected void         setconfigName(final String newConfigName) {
		if (newConfigName == null) {
			String  errorText = "New configuration name string is null";
			throw new NullPointerException(errorText);
		}
		configName = newConfigName;
	}
	/* Set or reset the configuration value. Note that the caller can 
 	   not pass a null value for the new configuration value. This is 
	   an error condition. */
	protected void         setconfigValue(final String newConfigValue) {
		if (newConfigValue == null) {
			String  errorText = "New configuration value string is null";
			throw new NullPointerException(errorText);
		}
		configValue = newConfigValue;
	}
}
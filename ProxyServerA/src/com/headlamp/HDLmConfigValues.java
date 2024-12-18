package com.headlamp; 

import java.util.ArrayList;
/**
 * HDLmConfigValues short summary.
 *
 * HDLmConfigValues description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmConfigValues {
	/* An instance of this class is created for each set of configuration values. 
	   This structure holds a set of configuration values */
	private ArrayList<HDLmConfigValue>  configsList = new ArrayList<HDLmConfigValue>(); 
	/* Add a configuration value to the list of configuration values. 
	   The caller provide an object of type HDLmConfigValue. The object
	   is added to the list of configuration values. The object has the
	   configuration name and the configuration value. */
	protected void         addConfig(final HDLmConfigValue newConfig) {
		/* Check if the caller passed a null configuration object */
		if (newConfig == null) {
			String  errorText = "New configration object is null";
			throw new NullPointerException(errorText);
		}
		/* Add the configuration value to the list of configuation values */
		configsList.add(newConfig);		
	}	 
	/* Get a few values */
	protected ArrayList<HDLmConfigValue>  getConfigsList() {
		return configsList;
	}
}
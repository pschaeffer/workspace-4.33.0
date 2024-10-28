package com.headlamp;

/**
 * HDLmClientInfo short summary.
 *
 * HDLmClientInfo description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmClientInfo {
	/* An instance of this class is created to provide client (mostly 
	   Web browser) information to another program. Of course, not all
	   of the fields will always be set. However, all of the fields may
	   be set in some cases. */
	private HDLmBrowserTypes   browserType = HDLmBrowserTypes.NONE;    
	private int                browserVersion = -1;
	/* The next field is used to store (a reference to) the entire 
	   User-Agent string */ 
	private String             userAgent = null;
	/* Get a few values */
	protected HDLmBrowserTypes getBrowserType() {
		return browserType;
	}
	protected int getBrowserVersion() {
		return browserVersion;
	}
	protected String getUserAgent() {
		return userAgent;
	}
	/* Set or reset the browser type. Note that the caller can not 
     pass a null value for the new browser type value. This is an
     an error condition. */
	protected void setBrowserType(HDLmBrowserTypes newBrowserType) {
		if (newBrowserType == null) {
			String  errorText = "New browser type value is null";
			throw new NullPointerException(errorText);
		}
		browserType = newBrowserType;
	}
	/* Set or reset the User-Agent string. Note that the caller can 
	   not pass a null value for the new User-Agent string. This is 
	   an error condition. */
	protected void setUserAgent(String newUserAgent) {
		if (newUserAgent == null) {
			String  errorText = "New User-Agent string is null";
			throw new NullPointerException(errorText);
		}
		userAgent = newUserAgent;
	}
}
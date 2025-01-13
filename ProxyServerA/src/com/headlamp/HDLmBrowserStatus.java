package com.headlamp;
import com.google.gson.Gson;
/**
 * HDLmBrowserStatus short summary.
 *
 * HDLmBrowserStatus description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmBrowserStatus {
	/* An instance of this class is created to provide the status
	   of a browser. The main use of this class is converting class
	   instances to JSON for logging. */
	private String  timestamp;
	private String  reason;
	private String  browser;
	private String  version;
	/* This constructor sets the fields of a new browser status instance 
	   using the values passed by the caller. The newly created object 
	   will have all of the values specified by the caller. */ 
	protected HDLmBrowserStatus(String newTimeStamp,
			                        String newReason,
			                        String newBrowser,
			                        String newVersion) {
		if (newTimeStamp == null) {
			String  errorText = "New timestamp reference used to build instance is null";
			throw new NullPointerException(errorText);
		}		
		if (newReason == null) {
			String  errorText = "New reason reference used to build instance is null";
			throw new NullPointerException(errorText);
		}	
		if (newBrowser == null) {
			String  errorText = "New browser reference used to build instance is null";
			throw new NullPointerException(errorText);
		}	
		if (newVersion == null) {
			String  errorText = "New version reference used to build instance is null";
			throw new NullPointerException(errorText);
		}	
		this.timestamp = newTimeStamp;
		this.reason = newReason;
		this.browser = newBrowser;
		this.version = newVersion;
	}
	/* Build a JSON string from a browser status object. The caller 
	   provides the non-null object instance (object of this class).
	   The current instance (literally this) is always handled by this
	   routine. This routine tries to convert the browser status object 
	   into JSON using a standard JSON constructor. */ 
	protected String buildJson() {
		Gson     gsonInstance = HDLmMain.gsonMain;
		String   jsonString = gsonInstance.toJson(this);
		return jsonString;
	}
}
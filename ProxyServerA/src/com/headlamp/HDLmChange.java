package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.time.Instant; 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
/**
 * Class for supporting logging changes 
 *
 * Each instance of this class describes one change to something
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmChange {
	/* The next statement initializes logging to some degree. Note that having the
	   slf4j jars and the log4j jars in the classpath also plays some role in
	   logging initialization. */
	private static final Logger   LOG = LoggerFactory.getLogger(HDLmChange.class);
	/* The following are the fields of the change object 
	   describe each change in some detail. These fields
	   are set by the constructor. */ 	 
	private HDLmChangeSourceTypes   changeSource = null;
	private HDLmChangeTypes         changeType = null;
	private Instant                 time = null;
	private String                  dummyTime = null;
	private String                  top = null;
	private String                  group = null;	
	private String                  company = null;
	private String                  location = null;
	private String                  division = null;
	private String                  site = null;
	private String                  name = null;
	private String                  info = null;
	/* This is the standard constructor for this class. Build a change
	   instance. The caller provides the name and all of the other information. 
	   Note that many of the values can be null. This is not considered to be an 
	   error condition in this case. */
	protected HDLmChange(final HDLmChangeSourceTypes newSource,
		                	 final HDLmChangeTypes newType, 
		                	 final Instant  newTime, 
		                	 final String   newTop,
		                	 final String   newGroup,
		                	 final String   newCompany,
		                	 final String   newLocation,
		                	 final String   newDivision,
			                 final String   newSite, 
			                 final String   newName, 
			                 final String   newInfo) {
		/* Check the change source */
		if (newSource == null) {
			String  errorText = "New source value passed to change object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (newSource == HDLmChangeSourceTypes.NONE) {
			String  errorText = "New source value passed to change object constructor is not set";
			HDLmAssertAction(false, errorText);
		}
		/* Check the change type */
		if (newType == null) {
			String  errorText = "New type value passed to change object constructor is null";
			throw new NullPointerException(errorText);
		}
		if (newType == HDLmChangeTypes.NONE) {
			String  errorText = "New type value passed to change object constructor is not set";
			HDLmAssertAction(false, errorText);
		}
		/* Check the change time */
		if (newTime == null) {
			String  errorText = "New time value passed to change object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check the change top */
		if (newTop == null) {
			String  errorText = "New top value passed to change object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check the change group. The group value may be really a null value. If we 
		   are handling just a top, then the group value will really be null. */
		if (newGroup == null &&
				newGroup != null) {
			String  errorText = "New group value passed to change object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check the change company. The company value may be really a null value. If we 
		   are handling just a group, then the company value will really be null. */
		if (newCompany == null &&
				newCompany != null) {
			String  errorText = "New company value passed to change object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check the change location. The location value may be really a null value. If we 
		   are handling just a company, then the location value will really be null. */
		if (newLocation == null &&
			  newLocation != null) {
			String  errorText = "New location value passed to change object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Check the change information */
		if (newInfo == null) {
			String  errorText = "New information value passed to change object constructor is null";
			throw new NullPointerException(errorText);
		}
		/* Store some values in the change object */
		this.changeSource = newSource;
		this.changeType = newType;
		this.time = newTime;
		this.top = newTop;
		this.group = newGroup;
		this.company = newCompany;
		this.location = newLocation;
		this.division = newDivision;
		this.site = newSite;
		this.name = newName;
		this.info = newInfo;			
	} 
	/* This is the copy constructor for this class. Build a new change instance
	   from an existing change instance */ 
	protected HDLmChange(final HDLmChange  oldChange) {
		/* Copy all of the values from the old change object to the 
		   new change object */
		this.changeSource = oldChange.changeSource;
		this.changeType = oldChange.changeType;
		this.time = oldChange.time;
		this.top = oldChange.top;
		this.group = oldChange.group;
		this.company = oldChange.company;
		this.location = oldChange.location;
		this.division = oldChange.division;
		this.site = oldChange.site;
		this.name = oldChange.name;
		this.info = oldChange.info;		 
	}
	/* The next routine takes a change and writes it out to 
	   the change log. Many changes are made to change (actually
	   just a copy) so that the change log is more user friendly. */ 
	public void            logChange() {
		/* Get the name of the change log file */
		String  filePath = HDLmUtility.getStandardPath();
		String  fileName = HDLmConfigInfo.getLogChangesFileName();
		String  fileNameComplete = filePath + fileName;		
		/* convert the change object to a user friendly string 
		   that describes the change */
		String  changeString = this.toStringUserFriendly();
		/* Write the change string to the change log file */
		HDLmUtility.filePutAppendLine(fileNameComplete, changeString);
	}
	/* This routine returns either true or false. This routine returns
	   the log changes flag. This flag will be false, if changes should
	   not be logged. This flag will be true if changes should be logged. */
	protected static boolean  checkLogChanges() {
		return (HDLmState.checkString("logChanges") != null &&
				    HDLmStateInfo.getLogChanges().equals("yes"));
	}
	/* The next routine records a change. The change may come from
	   several sources. The type of change is highly variable. The
	   current change might be an insert or it might be an update
	   or it might be a delete. The change is recorded in the change
	   log. */
	protected static void  recordChange(final HDLmChangeSourceTypes newSource,
	                                    final HDLmChangeTypes newType,
	                                    final ArrayList<String> nodePath,
	                                    final JsonElement jsonElement) {
		/* Check if the change source passed by the caller is null */
		if (newSource == null) {
			String  errorText = "Change source passed to recordChange is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the change source passed by the caller is invalid */
		if (newSource == HDLmChangeSourceTypes.NONE) {
		  HDLmAssertAction(false, "Change Source value passed to recordChange is invalid");
		}
		/* Check if the change type passed by the caller is null */
		if (newType == null) {
			String  errorText = "Change type passed to recordChange is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the change type passed by the caller is invalid */
		if (newType == HDLmChangeTypes.NONE) {
		  HDLmAssertAction(false, "Change type value passed to recordChange is invalid");
		}
		/* Check if the node path passed by the caller is null */
		if (nodePath == null) {
			String  errorText = "Node path passed to recordChange is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the node path length is invalid */
	  int   nodePathSize = nodePath.size();	
	  int   maxPathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		if (nodePathSize < 1 ||
				nodePathSize > maxPathLength) {
			String  errorFormat = "Node path length (%d) is incorrect";
			String  errorText = String.format(errorFormat, nodePathSize);
			HDLmAssertAction(false, errorText);
			throw new NullPointerException(errorText);
		}
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to recordChange is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */
		String  companyName = null;
		String  topName = null;
		String  groupName = null;		
		String  locationName = null;
		String  divisionName = null;
		String  siteName = null;
		String  nodeInfo = null;
		String  nodeName = null;
		if (nodePathSize >= 1)
			topName = nodePath.get(0);
		if (nodePathSize >= 2)
			groupName = nodePath.get(1);
		if (nodePathSize >= 3)
			companyName = nodePath.get(2);
		if (nodePathSize >= 4)
			locationName = nodePath.get(3);
		if (nodePathSize >= 5)
			divisionName = nodePath.get(4);
		if (nodePathSize>= 6)
			siteName = nodePath.get(5);
		if (nodePathSize >= 7)
			nodeName = nodePath.get(6);
		/* Convert the JSON element to a string that 
		   describes the current change */
	  Gson     gsonInstance = HDLmMain.gsonMain;
		nodeInfo = gsonInstance.toJson(jsonElement);
		/* Create the change object */
		HDLmChange  newChange = new HDLmChange(newSource,
						                               newType,
						                               Instant.now(),
						                               topName,
						                               groupName,
						                               companyName, 
						                               locationName,
						                               divisionName, 
						                               siteName, 
						                               nodeName, 
						                               nodeInfo); 
		/* Check if we actually created the new change */
		if (newChange == null) {
			String errorText = "Could not create a new change object";
			throw new NullPointerException(errorText);
		}
		newChange.logChange();		
	}
	/* The next routine records a change from the web. The type of 
	   change is highly variable. The current change might be an insert
	   or it might be an update or it might be a delete. The change is 
	   recorded in the change log. */
	protected static void  recordChangeWeb(final HDLmChangeSourceTypes newSource,
	                                       final HDLmChangeTypes newType,
	                                       final String requestPostPayload) {
		/* Check if the change source passed by the caller is null */
		if (newSource == null) {
			String  errorText = "Change source passed to recordChangeWeb is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the change source passed by the caller is invalid */
		if (newSource == HDLmChangeSourceTypes.NONE) {
		  HDLmAssertAction(false, "Change Source value passed to recordChangeWeb is invalid");
		}
		/* Check if the change type passed by the caller is null */
		if (newType == null) {
			String  errorText = "Change type passed to recordChangeWeb is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the change type passed by the caller is invalid */
		if (newType == HDLmChangeTypes.NONE) {
		  HDLmAssertAction(false, "Change type value passed to recordChangeWeb is invalid");
		}
		/* Check if the request post payload passed by the caller is null */
		if (requestPostPayload == null) {
			String  errorText = "Node path passed to recordChangeWeb is null";
			throw new NullPointerException(errorText);
		}
		/* Check if changes should be logged or not. The logging of changes
		   is turned off for testing (running test routines) because they 
		   don't provide the change routine with the data (the node path)
		   it needs. */
		if (HDLmChange.checkLogChanges() == false)
			return;
		/* Convert the post payload to a tree of JSON elements */
    JsonParser    parser = new JsonParser();  
	  JsonObject    requestPostPayloadJson = (JsonObject) parser.parse(requestPostPayload); 
	  /* Get the data array */
	  JsonArray     dataJsonArray = (JsonArray) requestPostPayloadJson.get("data"); 
    /* Check if the JSON array is valid or not */
		if (!dataJsonArray.isJsonArray()) {
	 	  String  errorText = "JSON array in recordChangeWeb is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Get the maximum node path length */
		int   i = 0;
		int   maxPathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		/* Process each element in the JSON array */
		for (JsonElement dataArrayEntry : dataJsonArray) {
			/* Increment the counter */
			i++;
			JsonObject  infoObject = (JsonObject) dataArrayEntry.getAsJsonObject().get("info"); 
		  /* Check if the JSON object is null or not. This can really happen
		     in Junit5 testing Some of the tests delete database rows by row
		     ID without providing "info" data. As a consequence the information
		     JSON object will be null. */
			if (infoObject == null && 
					infoObject != null)
				continue;			
			/* Check if the JSON object is valid or not */
			if (!infoObject.isJsonObject()) {
		 	  String  errorText = "JSON object in recordChangeWeb is invalid";
		 	  HDLmAssertAction(false, errorText);
		  }
			/* Get the JSON node path */
		  JsonArray  nodePathJsonArray = (JsonArray) infoObject.get("nodePath"); 
	    /* Check if the node path JSON array is valid or not */
			if (!nodePathJsonArray.isJsonArray()) {
		 	  String  errorText = "JSON node path array in recordChangeWeb is invalid";
		 	  HDLmAssertAction(false, errorText);
		  }
			int nodePathJsonSize = nodePathJsonArray.size();
			if (nodePathJsonSize < 1) {	
				String  errorFormat = "JSON array size (%d) is not large enough for the host name - Node (%d)";
				String  errorText = String.format(errorFormat, nodePathJsonSize, i);
				HDLmAssertAction(false, errorText);
			}
			/* Check if the node path length is too long */ 
			if  (nodePathJsonSize > maxPathLength) {
				String  errorFormat = "Add node path length (%d) is too long";
				String  errorText = String.format(errorFormat, nodePathJsonSize);
				HDLmAssertAction(false, errorText);
			}
			/* Convert the JSON array with the node path to an ArrayList with the node path */
			ArrayList<String>   changeNodePath = new ArrayList<String>();
			if (changeNodePath == null) {
				String  errorText = "Node path ArrayList allocation in recordChangeWeb is null";
				throw new NullPointerException(errorText);
			}
			/* Loop over and process each element of the JSON array */
			for (JsonElement arrayEntry : nodePathJsonArray) {
				String  nodePathEntry = arrayEntry.getAsString();
				changeNodePath.add(nodePathEntry);
			}
			/* Record the current change */
			HDLmChange.recordChange(newSource,
                              newType,
                              changeNodePath,
                              infoObject);  
		}
	}
	/* The next routine takes a change object and builds a user
	   friendly string from it. The string is actually a JSON 
	   string and is returned to the caller. A few changes are 
	   made to the input and the output to make the string
	   more user friendly  */
	public String          toStringUserFriendly() {
		/* Build a new change object from the existing change 
		   object*/
		HDLmChange  newChange = new HDLmChange(this);
		/* Check if we actually created the new change */
		if (newChange == null) {
			String errorText = "Could not create a new change object";
			throw new NullPointerException(errorText);
		}
		newChange.dummyTime = newChange.time.toString();
		newChange.time = null; 
		/* Build a JSON object */
	  Gson     gsonInstance = HDLmMain.gsonMain; 
		String   jsonString = gsonInstance.toJson(newChange); 
		/* Make a few changes to the JSON string to make it more user 
		   friendly */
		jsonString = jsonString.replace("changeSource", "source");
		jsonString = jsonString.replace("CHANGESOURCESOCKETS", "WebSockets");
		jsonString = jsonString.replace("CHANGESOURCEWEB", "web");
		jsonString = jsonString.replace("\"source\":\"NONE\"", "\"source\":\"none\"");
		jsonString = jsonString.replace("changeType", "type");
		jsonString = jsonString.replace("CHANGETYPEADD", "add"); 
		jsonString = jsonString.replace("CHANGETYPEDELETE", "delete"); 
		jsonString = jsonString.replace("CHANGETYPEMODIFY", "modify"); 
		jsonString = jsonString.replace("\"type\":\"NONE\"", "\"type\":\"none\"");
		jsonString = jsonString.replace("dummyTime", "time");
		jsonString = jsonString.replace("\"location\":\"Data\"", "\"location\":\"data\"");
		jsonString = jsonString.replace("\"location\":\"Ignore Lists\"", "\"location\":\"ignore lists\"");
		jsonString = jsonString.replace("\"location\":\"Reports\"", "\"location\":\"reports\"");
		jsonString = jsonString.replace("\"location\":\"Rules\"", "\"location\":\"rules\"");
		return jsonString;
	}  
}
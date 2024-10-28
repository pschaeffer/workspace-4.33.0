package com.headlamp; 
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.time.Instant; 
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruResponse short summary.
 *
 * HDLmPassThruResponse description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. This class is used to return a set
   of fields to a caller for pass-through purposes. */
public class HDLmPassThruResponse {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmPassThruResponse.class); 
	/* This is the standard constructor for this class. At present
     it does not do anything. */
  public HDLmPassThruResponse() {}	
	/* An instance of this class is created to return the results
	   from some sort of pass-through call. Of course, not all
	   of the fields will always be set. However, all of the fields
	   may be set in some cases. */
  private Boolean             enabled = null;
  private Boolean             updated = null;
  private HDLmPassThruStatus  passThruStatus = null;
	private HDLmTreeTypes       associatedNodeType = null;
	private int                 errorCount = 0; 
  private String              comments = null;
  private String              name = null;
  private String              extra = null;
  private Instant             created = null;
  private Instant             lastModified = null;
  /* This routine tries to obtain all of the standard fields from 
     a set of JSON objects passed to it */
  protected static HDLmPassThruResponse  getStandardFields(JsonElement jsonElement,
  		                                                     HDLmTreeTypes expectedType,
  		                                                     HDLmGetComments getComments,
  		                                                     HDLmGetCreated getCreated,
  		                                                     HDLmGetLastModified getLastModified,
  		                                                     HDLmGetPassThruStatus getStatus,
  		                                                     HDLmOptEnabled optEnabled,
  		                                                     HDLmOptExtra optExtra,
  		                                                     HDLmGetUpdated getUpdated) {
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if a null tree type was passed to this routine */
		if (expectedType == null) {
			String  errorText = "Tree type used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the tree type value is properly set */
		if (expectedType == HDLmTreeTypes.NONE) {		
	  	String  errorText = "Tree type value used to to build a set of standard fields is not properly set";
		  throw new IllegalArgumentException(errorText);
		}		
		/* Check if a null get comments enum was passed to this routine */
		if (getComments == null) {
			String  errorText = "Get comments enum used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the get comments enum value is properly set */
		if (getComments == HDLmGetComments.NONE) {		
	  	String  errorText = "Get comments enum value used to to build a set of standard fields is not properly set";
		  throw new IllegalArgumentException(errorText);
		}
		/* Check if a null get created enum was passed to this routine */
		if (getCreated == null) {
			String  errorText = "Get created enum used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the get created enum value is properly set */
		if (getCreated == HDLmGetCreated.NONE) {		
	  	String  errorText = "Get created enum value used to to build a set of standard fields is not properly set";
		  throw new IllegalArgumentException(errorText);
		}	
		/* Check if a null get last modified enum was passed to this routine */
		if (getLastModified == null) {
			String  errorText = "Last modified enum used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the get last modified enum value is properly set */
		if (getLastModified == HDLmGetLastModified.NONE) {		
	  	String  errorText = "Get last modified enum value used to to build a set of standard fields is not properly set";
		  throw new IllegalArgumentException(errorText);
		}	
		/* Check if a null get pass-through status enum was passed to this routine */
		if (getStatus == null) {
			String  errorText = "Pass-through status enum used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the get pass-through status enum value is properly set */
		if (getStatus == HDLmGetPassThruStatus.NONE) {		
	  	String  errorText = "Get pass-through status enum value used to to build a set of standard fields is not properly set";
		  throw new IllegalArgumentException(errorText);
		}
		/* Check if a null enabled optional enum was passed to this routine */
		if (optEnabled == null) {
			String  errorText = "Enabled optional enum used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the enabled optional enum value is properly set */
		if (optEnabled == HDLmOptEnabled.NONE) {		
	  	String  errorText = "Enabled optional enum value used to to build a set of standard fields is not properly set";
		  throw new IllegalArgumentException(errorText);
		}	
		/* Check if a null extra information optional enum was passed to this routine */
		if (optExtra == null) {
			String  errorText = "Extra information optional enum used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the extra information optional enum value is properly set */
		if (optExtra == HDLmOptExtra.NONE) {		
	  	String  errorText = "Extra information optional enum value used to to build a set of standard fields is not properly set";
		  throw new IllegalArgumentException(errorText);
		}	
		/* Check if a null get updated enum was passed to this routine */
		if (getUpdated == null) {
			String  errorText = "Get updated enum used to build a set of standard fields is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the get updated enum value is properly set */
		if (getUpdated == HDLmGetUpdated.NONE) {		
	  	String  errorText = "Get updated enum value used to to build a set of standard fields is not properly set";
		  throw new IllegalArgumentException(errorText);
		}	
  	/* Try to build a response object that can be returned to
  	   the caller */ 
  	HDLmPassThruResponse  response = new HDLmPassThruResponse();
  	if (response == null) {
  		String  errorText = "New pass-through response object was not created";
  		HDLmAssertAction(false, errorText);  		
  	}
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
		  HDLmAssertAction(false, "JSON element used to build a set of standard fields is a JSON null");
	  }
		/* Set the error count to an initial value. The error count is incremented
		   each time an error is detected. The final error count is returned to the
		   caller. Note that a reference is used below so that the error count can
		   be updated by the routines called using error count.*/
    MutableInt   errors = new MutableInt(response.errorCount);
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes   editorType = HDLmEditorTypes.PASS;
	  JsonObject        jsonObject = jsonElement.getAsJsonObject();
	  Set<String>       jsonKeys = jsonObject.keySet();
	  String            curStringDateTime;
  	 /* Get the name value from the JSON. The name field does not
  	    exist in an HDLmTree. The name field is only part of an HDLmMod. */
	  String   newName;
	  newName = HDLmMod.modFieldString(editorType, errors, 
	  		                             jsonObject, jsonKeys, 
	  		                             "name", 
		  	                             HDLmWhiteSpace.WHITESPACENOTOK, 
		  	                             HDLmReportErrors.REPORTERRORS,
		  	                             HDLmZeroLengthOk.ZEROLENGTHNOTOK);
		if (newName == null || StringUtils.isWhitespace(newName)) {
			HDLmAssertAction(false, "Name value not obtained from JSON element");
		}
		response.name = newName;
		/* Get and check the current type */
		HDLmTreeTypes   curType = HDLmTreeTypes.NONE;
		curType = HDLmMod.modFieldTreeType(editorType, errors, 
				                               jsonObject, jsonKeys, 
				                               "type");
		if (curType != expectedType) {
			String  errorText = "Tree type obtained from JSON is invalid";
			HDLmAssertAction(false, errorText);			
		}
		response.associatedNodeType = curType;
		/* Get and check the current pass-through status */
		if (getStatus == HDLmGetPassThruStatus.GETSTATUSYES) {
			HDLmPassThruStatus  curStatus = HDLmPassThruStatus.NONE;
			curStatus = HDLmMod.modFieldStatus(editorType, errors, 
					                               jsonObject, jsonKeys, 
					                               "passThru");
			response.passThruStatus = curStatus;
		}
		/* Check if the enabled field is optional */
		if (optEnabled == HDLmOptEnabled.OPTENABLEDYES &&
				!jsonKeys.contains("enabled")) {}
		else { 
		  /* Get a few fields from the JSON object */
			Boolean enabledBoolean = HDLmMod.modFieldBoolean(editorType, errors, 
					                                             jsonObject, jsonKeys, 
					                                             "enabled");
			if (enabledBoolean != null)
				response.enabled = enabledBoolean;
		}
		/* Check if the updated field is required */
		if (getUpdated == HDLmGetUpdated.GETUPDATEDYES) {
		  /* Get a few fields from the JSON object */
			Boolean  updatedBoolean = HDLmMod.modFieldBoolean(editorType, errors, 
						                                            jsonObject, jsonKeys, 
						                                            "updated");
		  if (updatedBoolean != null)
					response.updated = updatedBoolean;
		}
		else
			response.updated = false;
		/* Try to get the comments value from the JSON element. Note that
		   the call below will never report an error unless the comments
		   field is actually missing from the JSON. This does not appear
		   to be true in any actual case. */
	  if (getComments == HDLmGetComments.GETCOMMENTSYES) {
		  String commentsInfo = HDLmMod.modFieldString(editorType, errors, 
			  	                                         jsonObject, jsonKeys, 
				                                           "comments", 
				                                           HDLmWhiteSpace.WHITESPACEOK, 
				                                           HDLmReportErrors.REPORTERRORS,
				                                           HDLmZeroLengthOk.ZEROLENGTHNOTOK);			 
		  if (commentsInfo != null && !StringUtils.isWhitespace(commentsInfo)) {
		  	response.comments = commentsInfo;
		  }
		}
		/* Check if the extra information field is optional */
	  if (optExtra != HDLmOptExtra.OPTEXTRANEVER) {
			if (optExtra == HDLmOptExtra.OPTEXTRAYES &&
					!jsonKeys.contains("extra")) {}
			else { 
				/* Try to get the extra information value from the JSON element.
				   Note that the call below will never report an error unless the
				   extra information field is actually missing from the JSON.
				   This does not appear to be true in any actual case. */
				String extraInfo = HDLmMod.modFieldString(editorType, errors, 
						                                      jsonObject, jsonKeys, 
						                                      "extra", 
						                                      HDLmWhiteSpace.WHITESPACEOK, 
						                                      HDLmReportErrors.REPORTERRORS,
						                                      HDLmZeroLengthOk.ZEROLENGTHNOTOK);
				if (extraInfo != null && !StringUtils.isWhitespace(extraInfo)) {
					response.extra = extraInfo;
				}
			}
	  }
		/* Get the created date and time and use them to set an instance field */
		if (getCreated == HDLmGetCreated.GETCREATEDYES) {
			curStringDateTime = HDLmMod.modFieldString(editorType, errors, 
					  																     jsonObject, jsonKeys, 
																								 "created", 
																								 HDLmWhiteSpace.WHITESPACENOTOK, 
																								 HDLmReportErrors.REPORTERRORS,
																								 HDLmZeroLengthOk.ZEROLENGTHNOTOK);
	    /* Convert a date-time string into an Instant */
      Instant  curInstant = Instant.parse(curStringDateTime);
			response.created = curInstant;
		}
		/* Get the  date and time and use them to set an instance field */
		if (getLastModified == HDLmGetLastModified.GETLASTMODIFIEDYES) {
			curStringDateTime = HDLmMod.modFieldString(editorType, errors, 
																								 jsonObject, jsonKeys, 
																								 "lastmodified",
																								 HDLmWhiteSpace.WHITESPACENOTOK, 
																								 HDLmReportErrors.REPORTERRORS,
																								 HDLmZeroLengthOk.ZEROLENGTHNOTOK); 
	    /* Convert a date-time string into an Instant */
      Instant  curInstant = Instant.parse(curStringDateTime);
			response.lastModified = curInstant;;  	
		}
  	/* Return the response to the caller */
  	return response;
  }	
  /* Get the value of the associated node type field and return it to the caller */
  protected HDLmTreeTypes  getAssociatedNodeType() {
  	return associatedNodeType;
  }
  /* Get the value of the comments field and return it to the caller */
  protected String       getComments() {
  	return comments;
  }
  /* Get the value of the created field and return it to the caller */
  protected Instant      getCreated() {
  	return created;
  }
  /* Get the value of the enabled field and return it to the caller */
  protected Boolean      getEnabled() {
  	return enabled;
  }
  /* Get the value of the error count field and return it to the caller */
  protected int          getErrorCount() {
  	return errorCount;
  }
  /* Get the value of the extra information field and return it to the caller */
  protected String       getExtra() {
  	return extra;
  }
  /* Get the value of the last modified field and return it to the caller */
  protected Instant      getLastModified() {
  	return lastModified;
  }
  /* Get the value of the name field and return it to the caller */
  protected String       getName() {
  	return name;
  }
  /* Get the value of the pass-through status field and return it to the caller */
  protected HDLmPassThruStatus  getPassThruStatus() {
  	return passThruStatus;
  }
  /* Get the value of the updated field and return it to the caller */
  protected Boolean       getUpdated() {
  	return updated;
  }  
}
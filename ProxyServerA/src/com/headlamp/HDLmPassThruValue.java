package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruValue short summary.
 *
 * HDLmPassThruValue description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each unique data value name. */  
/* An instance of this class is created for each data value
   name. In other words, the actual data values may not be 
   unique. However, the data value names will always be unique. */ 
/* An instance of this class is created for each value. The 
   name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruValue extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruValue() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
  	Instant   currentTimestamp = Instant.now();
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  }
	/* This is one of the constructors for the data value definition class.
	   It must be passed a JSON element that contains all of the details
 	   of the data value definition. */
	protected HDLmPassThruValue(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build data value definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Set the error count to zero. The error count is incremented each time an
			 error is detected. If the final error count (for the current definition) is
			 greater than zero, the current definition object is disabled (the enabled
			 field is set false). Note that a reference is used below so that the error
			 count can be updated by the routines called using error count.*/
	  MutableInt   errors = new MutableInt(0);
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
		  HDLmAssertAction(false, "JSON element used to build comapnies definition is JSON null");
	  }
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    Set<String> jsonKeys = jsonObject.keySet();
    /* Get the standard class instance variables */
    HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	  	                                                                      HDLmTreeTypes.VALUE,
	  	                                                                      HDLmGetComments.GETCOMMENTSNO,
																																		        HDLmGetCreated.GETCREATEDNO,
																																		        HDLmGetLastModified.GETLASTMODIFIEDYES,
																																		        HDLmGetPassThruStatus.GETSTATUSNO,
																																		        HDLmOptEnabled.OPTENABLEDYES,
																																		        HDLmOptExtra.OPTEXTRANEVER,
																																		        HDLmGetUpdated.GETUPDATEDNO);
	  if (response == null) {
	 	  String  errorText = "Null response from build standard fields routine";
	  	HDLmAssertAction(false, errorText);
	  }
	  /* Update the error count with the response value */
	  errors.add(response.getErrorCount()); 
	  /* Extract the fields from the build standard fields response */
	  setName(response.getName());
	  /* Get a few fields from the JSON object */
	  Boolean enabledBoolean = response.getEnabled();
	  if (enabledBoolean != null)
	    setEnabled(enabledBoolean);
	  /* Try to get the comments value from the JSON element. Note that
	     the call below will never report an error unless the comments
	     field is actually missing from the JSON. This does not appear
	     to be true in any actual case. Note that the comments are
	     optional. Skip this code is we don't have any comments. */
	  String  commentsInfo = response.getComments();		 
	  if (commentsInfo != null && !StringUtils.isWhitespace(commentsInfo)) {
	    setComments(commentsInfo);
	  }	 
		/* Set the created date and time in an instance field */
		created = Instant.now();
		/* Get the last modified date and time and use them to set an instance field */
		lastModified = response.getLastModified();
		/* Set the last modified date and time in an instance field */
		lastModified = Instant.now();
		/* Try to get the actual saved data value from the JSON passed to this
		   routine. We should be abled to get the saved data value in all cases.
		   However, you never know. */ 
		String  valueStr = HDLmJson.getJsonString(jsonElement, "value");
		if (valueStr != null)
			((HDLmMod) this).setValue(valueStr);
		/* Mark the current report definition object as disabled
		   if the error count was greater than zero. This is actually
		   done by setting the enabled field to false. */
		if (errors.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
	   need to be obtained and/or set */  
  /* This routine builds a tree node for a data value node
     and returns the final data value tree node to the
	   caller. The data value reference is used to set 
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruValue newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through data value reference 
		   passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Data value reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current data value */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.VALUE;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  dataValueTree = new HDLmTree(nodeType,
                                           nodeTooltip,
                                           nodePath);
    if (dataValueTree == null) {
      String  errorText = "New data value tree node was not created";
      HDLmAssertAction(false, errorText);
    }
		/* Use the data instance as the details of the new tree node */
		dataValueTree.setDetails(newValue);		
		return dataValueTree;	
  }
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  } 
  /* This routine returns a reference to the data value string. The 
     data value is associated with the underlying object instance,
     not the higher-level object. */  
  protected String       getDataValue() {
	  return ((HDLmMod) this).getValue();
  } 
	/* This routine returns a reference to the last modified instant */  
  protected Instant      getLastModified() {
	  return lastModified;		
  } 
	/* This routine sets the reference to the created instant */  
  protected final void   setCreated(final Instant newCreated) {
	  /* Check if the created reference passed by the caller is null */
	  if (newCreated == null) {
	    String  errorText = "Created reference passed to setCreated is null";
	    throw new NullPointerException(errorText);
	  }
	  created = newCreated;		
  } 
	/* This routine sets the reference to the data value string. Note that
	   the underlying object is updated with the new value reference, not
	   the high-level object. */  
  protected final void   setDataValue(final String newDataValue) {
	  /* Check if the data value reference passed by the caller is null */
	  if (newDataValue == null) {
	    String  errorText = "Data value reference passed to setDataValue is null";
	    throw new NullPointerException(errorText);
	  }
	  ((HDLmMod) this).setValue(newDataValue);
  } 
	/* This routine sets the reference to the last modified instant */  
  protected final void   setLastModified(final Instant newLastModified) {
	  /* Check if the last modified reference passed by the caller is null */
	  if (newLastModified == null) {
	    String  errorText = "Last modified reference passed to setLastModified is null";
	    throw new NullPointerException(errorText);
	  }
	  lastModified = newLastModified;		
  } 
}	
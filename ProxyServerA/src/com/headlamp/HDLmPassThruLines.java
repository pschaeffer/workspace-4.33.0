package com.headlamp; 
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruLines short summary.
 *
 * HDLmPassThruLines description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each set of lines in a set of lines
   (one or more sets of lines). Several set of lines are created
   each time the verification tests are run against one company. 
   Each report has several sets of lines. In other words, several 
   instances of this class will be created for each report. */
/* Several instance of this class are created to store the results
   from running the verification tests against one company */ 
/* An instance of this class is created for each set of lines.  
   The name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruLines extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruLines() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
  	/* Declare and define a few variables */
  	Instant   currentTimestamp = Instant.now();
  	/* Set a few fields in the new lines instance */
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.LINES;  	
  	countLines = 0;
   	lines = new TreeMap<String, HDLmPassThruLine>();
  	if (lines == null)
  		HDLmAssertAction(false, "New Treemap<> not created for lines");
  }
	/* This is one of the constructors for this class. It doesn't do
	   much. However, the name will be set to the value passed by 
	   the caller. All of the other fields of this class will be 
	   set to the default values specified. */
	protected HDLmPassThruLines(final String linesName) { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		/* Check if the lines name passed by the caller, is null */
		if (linesName == null) {
			String   errorText = "Lines name string reference passed to the HDLmPassThruLines constructor is null";
			throw new NullPointerException(errorText);		
		}
		/* Declare and define a few variables */
		Instant   currentTimestamp = Instant.now();
		/* Set a few fields in the new lines instance */
		created = currentTimestamp;
		associatedNodeType = HDLmTreeTypes.LINES;  
		setName(linesName);
		countLines = 0;
		lines = new TreeMap<String, HDLmPassThruLine>();
		if (lines == null)
			HDLmAssertAction(false, "New Treemap<> not created for lines");
  }
	/* This is one of the constructors for the lines (set of lines)
	   definition class. It must be passed a JSON element that contains  
	   all of the details of the lines (set of lines) definition. */
	protected HDLmPassThruLines(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build lines definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Integer   curInteger = null;
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Create the lines collection that will be needed later */
   	lines = new TreeMap<String, HDLmPassThruLine>();
  	if (lines == null)
  		HDLmAssertAction(false, "New Treemap<> not created for lines");
		/* Set the error count to zero. The error count is incremented each time an
			 error is detected. If the final error count (for the current definition) is
			 greater than zero, the current definition object is disabled (the enabled
			 field is set false). Note that a reference is used below so that the error
			 count can be updated by the routines called using error count.*/
	  MutableInt   errors = new MutableInt(0);
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
		  HDLmAssertAction(false, "JSON element used to build lines definition is JSON null");
	  }
	  JsonObject jsonObject = jsonElement.getAsJsonObject();
	  Set<String> jsonKeys = jsonObject.keySet();
	  /* Get the standard class instance variables */
	  HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	 		                                                                      HDLmTreeTypes.LINES,
	 		                                                                      HDLmGetComments.GETCOMMENTSNO,
	 		                                                                      HDLmGetCreated.GETCREATEDYES,
	 		                                                                      HDLmGetLastModified.GETLASTMODIFIEDNO,
	 		                                                                      HDLmGetPassThruStatus.GETSTATUSNO,
	 		                                                                      HDLmOptEnabled.OPTENABLEDYES,
																																		        HDLmOptExtra.OPTEXTRAYES,
																																		        HDLmGetUpdated.GETUPDATEDYES);
	  if (response == null) {
	 	  String  errorText = "Null response from build standard fields routine";
	 	  HDLmAssertAction(false, errorText);
	  }
	  /* Update the error count with the response value */
	  errors.add(response.getErrorCount()); 
	  /* Extract the fields from the build standard fields response */
	  setName(response.getName());
		/* Get and check the current type */
		associatedNodeType = response.getAssociatedNodeType();
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
		/* Try to get the extra information value from the JSON element.
		   Note that the call below will never report an error unless the
		   extra information field is actually missing from the JSON.
		   This does not appear to be true in any actual case. */
		String extraInfo = response.getExtra();  
		if (extraInfo != null && !StringUtils.isWhitespace(extraInfo)) {
			setExtra(extraInfo);
		}
		/* Get the created date and time and use them to set an instance field */
		created = response.getCreated();	
		/* The last modified value is set, even though report don't really
	     have a last modified value. The value is set from the JSON obtained
	     from the database. This step avoids certain errors later. */ 
	  lastModified = response.getLastModified();
	  /* Check if the last modified value is null. If it is null, set it to the current time.
       This will really happen. We don't get the current date-time from the current row. 
       This means that the date-time will be null. */
	  if (lastModified == null)
		  lastModified = Instant.now();
		/* Get an integer value and use it to set an instance field */ 
	  curInteger = HDLmMod.modFieldInteger(editorType, errors, 
	                                       jsonObject, jsonKeys, 
		                                     "countLines", 
		                                     HDLmReportErrors.REPORTERRORS);
	  countLines = curInteger;
	  /* We don't really want to use the count of lines we obtained from
	     the JSON. We really need to use the line count we get by adding
	     each line to the lines. */ 
	  countLines = 0; 
		/* Mark the current lines (set of lines) definition object as 
		   disabled if the error count was greater than zero. This is 
		   actually done by setting the enabled field to false. */
		if (errors.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
     need to be obtained and/or set */ 
	private HDLmTreeTypes                       associatedNodeType = null;
	private TreeMap<String, HDLmPassThruLine>   lines = null;
	private Integer                             countLines = 0;
  /* This routine does the work of adding a new line to a set of 
     lines. The new line is not checked or processed in any way.
     The new line is just added to the current set of lines. */
	protected void         addLine(final HDLmPassThruLine newLine) {
		/* Check if the new line reference passed by the caller is null */
		if (newLine == null) {
	 	  String  errorText = "New line reference passed to addLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Add the new line to the current set of lines */
		lines.put(newLine.getName(), newLine);
		countLines++;
	}
	/* This routine builds a tree node for a lines (set of lines) 
	   and returns the final lines (set of lines) tree node to the
	   caller. The lines (set of lines) reference is used to set
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruLines newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through lines (set of lines) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Lines reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current lines (set of lines) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.LINES;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  linesTree = new HDLmTree(nodeType,
				                               nodeTooltip,
				                               nodePath);
		if (linesTree == null) {
			String  errorText = "New lines tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the lines (set of lines instance as the details of the new tree node */
		linesTree.setDetails(newValue);		
		return linesTree;	
	}
	/* This routine builds a set of tree nodes for a lines (set of
	   lines) and returns the final lines (set of lines) tree node 
	   to the caller. Other routines are used to build each of the
	   line nodes (one individual line) that are also associated with
	   a lines (set of lines). The lines (set of lines) reference is 
	   used to set the details of the new tree node. */
	protected static HDLmTree  buildTreeExtended(ArrayList<String> oldNodePath, 
                                               HDLmPassThruLines newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the pass-through lines (set of lines) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Lines reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
		}
		/* Build a new tree node with the correct information */
		HDLmTree  linesTree = HDLmPassThruLines.buildTree(oldNodePath,
				                                              newValue);
		if (linesTree == null) {
		  String  errorText = "New lines tree node was not created";
		  HDLmAssertAction(false, errorText);
		}	
		/* Get the new node path that includes the lines (set of lines) 
		   tree node */
		ArrayList<String>   newNodePath = linesTree.getNodePath();
		/* Add all of the individual lines as children of the lines (set of
		   lines) tree */ 
		for (Map.Entry<String, HDLmPassThruLine> entry : newValue.getLines().entrySet()) {
			HDLmPassThruLine  currentLine = entry.getValue();
			/* Try to build a tree node for the current line */
			HDLmTree  currentLineTree = HDLmPassThruLine.buildTree(newNodePath,
                                                             currentLine);
			if (currentLineTree == null) {
			  String  errorText = "New line tree node was not created";
			  HDLmAssertAction(false, errorText);
			}	
			/* Add the new tree node to the parent */
			linesTree.getChildren().add(currentLineTree);			
		}
		return linesTree;	
	}
  /* Get the value of the type field and return it to the caller */
  protected HDLmTreeTypes  getAssociatedNodeType() {
  	return associatedNodeType;
  }  
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  } 
	/* Get the tree map of lines and return it to the caller */
	protected TreeMap<String, HDLmPassThruLine>  getLines() {
		return lines;
	}
	/* This routine returns the lines count */  
  protected int          getLinesCount() {
	  return countLines;		
  } 
  /* This routine sets the associated node type of this class
	   instance to a null value */  
	protected final void   setAssociatedNodeTypeNull() { 
	  associatedNodeType = null;
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
	/* This routine sets the lines count to a value passed by the caller */  
  protected final void   setLinesCount(final int newLinesCount) {
	  /* Check if the lines count passed by the caller is valid */
	  if (newLinesCount < 0) {
	    String  errorText = "Lines count passed to setLinesCount is invalid";
	    throw new IllegalArgumentException(errorText);  	
	  }
	  countLines = newLinesCount;		
  } 
  /* This routine sets the current lines reference of this class
     instance to a null value */  
  protected final void   setLinesNull() { 
    lines = null;
  }
}	
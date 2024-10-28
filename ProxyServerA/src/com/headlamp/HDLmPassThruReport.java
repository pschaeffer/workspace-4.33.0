package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruReport short summary.
 *
 * HDLmPassThruReport description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each report. A report is created
   each time the verification tests are run against one company. */
/* An instance of this class is created to store the overall
   pass-through status and the results of the verification tests. */  
/* An instance of this class is created for each report. The 
   name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruReport extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruReport() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
  	/* Declare and define a few variables */
	  Instant   currentTimestamp = Instant.now();
  	/* Set a few fields in the new report instance */
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.REPORT;
  	passThruStatus = HDLmPassThruStatus.PASSTHRUNOTOK; 
  	countLines = 0;
  }
	/* This is one of the constructors for the report definition class.
	   It must be passed a JSON element that contains all of the details
	   of the report definition. */
	protected HDLmPassThruReport(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build report definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		HDLmReportTypes  curReportType;
		Integer          curInteger;
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
		  HDLmAssertAction(false, "JSON element used to build report definition is JSON null");
	  }
	  JsonObject jsonObject = jsonElement.getAsJsonObject();
	  Set<String> jsonKeys = jsonObject.keySet();
	  /* Get the standard class instance variables */
	  HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
		  	                                                                    HDLmTreeTypes.REPORT,
		  	                                                                    HDLmGetComments.GETCOMMENTSNO,
																																			      HDLmGetCreated.GETCREATEDYES,
																																			      HDLmGetLastModified.GETLASTMODIFIEDNO,
																																			      HDLmGetPassThruStatus.GETSTATUSYES,
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
	 	/* Get the current type */
	  associatedNodeType = response.getAssociatedNodeType();
		/* Get the current pass-through status */
		passThruStatus = response.getPassThruStatus();
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
		/* Get the report type and use it to set an instance field */ 
	  curReportType = HDLmMod.modFieldReportType(editorType, errors, 
	                                          jsonObject, jsonKeys, 
		                                        "reportType", 
		                                        HDLmReportErrors.REPORTERRORS);
	  reportType = curReportType;
		/* Mark the current report definition object as disabled
		   if the error count was greater than zero. This is actually
		   done by setting the enabled field to false. */
		if (errors.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
	   need to be obtained and/or set */ 
	private HDLmTreeTypes         associatedNodeType = null;
	private HDLmReportTypes       reportType = null;
  private HDLmPassThruStatus    passThruStatus = null;
  Integer                       countLines;
  HDLmPassThruLines             overallLines = null;
  HDLmPassThruLines             validLines = null;
  HDLmPassThruLines             invalidLines = null;
  HDLmPassThruList              ignoreList = null;
  /* This routine does the work of adding a new line to a report.
     The new line is not checked or processed in any way. The new
     line is just added to the report. Note that the new line is 
     always added to the overall set of lines. */
  protected void         addLine(final HDLmPassThruLine reportLine) {
		/* Check if the report line reference passed by the caller is null */
  	if (reportLine == null) {
	  	String  errorText = "Report line reference passed to addLine is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get the reference to the overall set of report lnes. This 
       is a reference to a lines instance. */
    HDLmPassThruLines  overallLinesRef = overallLines;
    if (overallLinesRef == null)
 	    HDLmAssertAction(false, "Reference to overall lines from report is null");
  	/* Add the new report line to the current report. This really 
	     means add the new report line to the overall lines instance
	     of the current report. */
    overallLinesRef.addLine(reportLine);
    countLines++;
  }
  /* This routine creates each of the lines instances and adds them
     to a report. Of course, each of the lines instances is initially
     empty when it is created. The references to each of the lines
     instances are set by this routine. This routine does not return
     anything. */
  protected void         addLinesReport() {
  	/* We can now create the three lines instances that are associated
		   with the current report. We start by creating the lines (set of
		   lines) instance for invalid lines. All report lines that should
		   not be ignored will be added to this lines instance.*/
	  String  invalidLinesName =  HDLmDefines.getString("HDLMINVALIDNODENAME");
		if (invalidLinesName == null) {
			String  errorFormat = "Define value for invalid lines node name not found (%s)";
			String  errorText = String.format(errorFormat, "HDLMINVALIDNODENAME");
			HDLmAssertAction(false, errorText);		    	
		} 
		HDLmPassThruLines   invalidLines = new HDLmPassThruLines(invalidLinesName);
		if (invalidLines == null)
			HDLmAssertAction(false, "Invalid lines instance was not created");
		setInvalidRef(invalidLines);  	
		/* Create the lines (set of lines) instance for the overall report lines.
		   All report lines will be added to this lines instance. */ 
	  String  overallLinesName =  HDLmDefines.getString("HDLMOVERALLNODENAME");
		if (overallLinesName == null) {
			String  errorFormat = "Define value for overall lines node name not found (%s)";
			String  errorText = String.format(errorFormat, "HDLMOVERALLNODENAME");
			HDLmAssertAction(false, errorText);		    	
		}  	
		HDLmPassThruLines   overallLines = new HDLmPassThruLines(overallLinesName);
		if (overallLines == null)
			HDLmAssertAction(false, "Overall lines instance was not created");
		setOverallRef(overallLines);
		/* Create the lines (set of lines) instance for the valid report lines.
		   All report lines that should be ignored will be added to this lines
		   instance. */  
	  String  validLinesName =  HDLmDefines.getString("HDLMVALIDNODENAME");
		if (validLinesName == null) {
			String  errorFormat = "Define value for valid lines node name not found (%s)";
			String  errorText = String.format(errorFormat, "HDLMVALIDNODENAME");
			HDLmAssertAction(false, errorText);		    	
		} 
		HDLmPassThruLines   ValidLines = new HDLmPassThruLines(validLinesName);
		if (ValidLines == null)
			HDLmAssertAction(false, "Valid lines instance was not created");
		setValidRef(ValidLines); 	
  } 
  /* This routine builds a new report (not a tree node) with a unique
	   name and sets the name of the new report. The new report instance
	   is returned to the caller. */ 
	protected static HDLmPassThruReport  buildNewReport(final HDLmPassThruCompany company,
			                                                final HDLmReportTypes reportType) {
		/* Check if the company reference passed by the caller is null */
  	if (company == null) {
	  	String  errorText = "Company reference passed to buildNewReport is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the report type reference passed by the caller is null */
  	if (reportType == null) {
	  	String  errorText = "Report type reference passed to buildNewReport is null";
		  throw new NullPointerException(errorText);
	  }
		/* Create an empty report instance. The lines of the report may 
		   be added later. */ 
		HDLmPassThruReport  report = new HDLmPassThruReport();
		if (report == null)
		  HDLmAssertAction(false, "Report instance was not created");
		/* We now want to set the name of the report. The name is obtained
		   by calling a special routine that should return the next report
		   name. */ 
		String  reportName = HDLmPassThruReport.getNextReportName(company);
		report.setName(reportName);
		/* we can now set the type of the report. The report type is 
		   passed by the caller. */
		report.setReportType(reportType);
		return report;
	}	
  /* This routine builds a new report (not a tree node) with a unique
	   name and sets the name of the new report. The new report instance
	   is returned to the caller. All three of the new lines instances 
	   are also created and added to the new report. */ 
	protected static HDLmPassThruReport  buildNewReportExtended(final HDLmPassThruCompany company,
			                                                        final HDLmReportTypes reportType) {
		/* Check if the company reference passed by the caller is null */
		if (company == null) {
	 	  String  errorText = "Company reference passed to buildNewReport is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the report type reference passed by the caller is null */
		if (reportType == null) {
	 	  String  errorText = "Report type reference passed to buildNewReport is null";
		  throw new NullPointerException(errorText);
	  }
		/* Create an empty report instance. The lines of the report are 
	     added below. */ 
		HDLmPassThruReport  report = HDLmPassThruReport.buildNewReport(company,
				                                                           reportType);
		if (report == null)
			HDLmAssertAction(false, "Report instance was not returned by buildNewReport");
		/* We can now create the three lines instances that are associated
	     with the current report */
	  report.addLinesReport();	
    /* Return the report instance to the caller */
		return report;
	}
	/* This routine builds a standard report name and returns it 
	   to the caller. Note that the caller passes the report number
	   that may be used to build the report name. We always allow 
	   for many leading digits so that the report names will be in 
	   collating order.
	   
	   The final report name must be, and will be, unique. That means
	   that no other report can have the same name. The report number
	   will be incremented as need be to make sure that the final 
	   report name is unique. */  
	protected static String  buildReportName(final HDLmPassThruCompany company,
			                                     int reportNumber)  {
		/* Check if the company reference passed by the caller is null */
		if (company == null) {
		  String  errorText = "Company reference passed to buildReportName is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the starting report number value is invalid */
		if (reportNumber <= 0) {
			String errorFormat = "New report number (%d) value passed to buildReportName is invalid";
			String  errorText = String.format(errorFormat, reportNumber);
			throw new IllegalArgumentException(errorText);
		}
		/* Declare and define a few variables */
		String  reportName; 
		/* Get the tree node for the reports associated with the current company */
		HDLmTree  reportsTree = company.getReportsTree();
		/* Build a report name and check if it is unique */
		while (true) {
			/* Generate a proposed report name */
			reportName = String.format("Report%06d",  reportNumber);
		  /* Get a count of how many times the new report name 
	       is already used. Hopefully, this count will be zero. 
	       However, it might not be zero. */
	    HDLmNameMatch  matchObj = HDLmTree.countSubNodeNames(reportName, reportsTree,
	                                                         null, false);
	    if (matchObj.getCount() == 0) 
	    	break;
	    /* Increment the report number */
	    reportNumber++;	      
	  }		
		return reportName;	
	}
	/* This routine builds a tree node for a report (just one report)  
	   and returns the final report (just one report) tree node to the
	   caller. The report (just one report) reference is used to set
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruReport newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through report (just one report) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Report reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current report (just one report) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.REPORT;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  reportTree = new HDLmTree(nodeType,
				                                nodeTooltip,
				                                nodePath);
		if (reportTree == null) {
			String  errorText = "New report tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the report (just one report) instance as the details of the new tree node */
		reportTree.setDetails(newValue);		
		return reportTree;	
	}
	/* This routine builds a set of tree nodes for a report and
	   returns the final report tree node to the caller. Other 
	   routines are used to build the ignore-lists and report
	   lines nodes that are also associated with a report. The 
	   report reference is used to set the details of the new 
	   tree node. */ 
	protected static HDLmTree  buildTreeExtended(ArrayList<String> oldNodePath, 
			                                         HDLmPassThruReport newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through report reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Report reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Build a new tree node with the correct information */
		HDLmTree  reportTree = HDLmPassThruReport.buildTree(oldNodePath, 
                                                        newValue);
		if (reportTree == null) {
			String  errorText = "New report tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		ArrayList<String>  nodePath = reportTree.getNodePath(); 
		/* Build a tree node for the invalid lines associated with every report.
		   Create a new instance for the invalid lines. This is only done if the
		   invalid lines reference has not already been set. */ 
		HDLmPassThruLines   newInvalidLines = newValue.getInvalidLinesRef();
		if (newInvalidLines == null) {
			newInvalidLines = new HDLmPassThruLines();
			if (newInvalidLines == null)
				HDLmAssertAction(false, "New invalid lines instance was not created");
			newValue.setInvalidRef(newInvalidLines);
		  String  nodePathInvalidLinesString =  HDLmDefines.getString("HDLMINVALIDNODENAME");
			if (nodePathInvalidLinesString == null) {
				String  errorFormat = "Define value for invalid lines node name not found (%s)";
				String  errorText = String.format(errorFormat, "HDLMINVALIDNODENAME");
				HDLmAssertAction(false, errorText);		    	
			}
			newInvalidLines.setName(nodePathInvalidLinesString);
		}
		/* Build an extended tree node for the invalid lines. This tree node
		   will have children for all of the actual invalid lines. */ 
		HDLmTree  invalidLinesTree = HDLmPassThruLines.buildTreeExtended(nodePath, 
                                                                     newInvalidLines);
		/* Build a tree node for the overall lines associated with every report.
		   Create a new instance for the overall lines. This is only done if the
		   overall lines reference has not already been set. */ 
		HDLmPassThruLines   newOverallLines = newValue.getOverallLinesRef();
		if (newOverallLines == null) {
			newOverallLines = new HDLmPassThruLines();
			if (newOverallLines == null)
				HDLmAssertAction(false, "New overall lines instance was not created");
			newValue.setOverallRef(newOverallLines);
		  String  nodePathOverallLinesString =  HDLmDefines.getString("HDLMOVERALLNODENAME");
			if (nodePathOverallLinesString == null) {
				String  errorFormat = "Define value for overall lines node name not found (%s)";
				String  errorText = String.format(errorFormat, "HDLMOVERALLNODENAME");
				HDLmAssertAction(false, errorText);		    	
			}
			newOverallLines.setName(nodePathOverallLinesString);
		}
		/* Build an extended tree node for the overall lines. This tree node
	     will have children for all of the actual overall lines. */
		HDLmTree  overallLinesTree = HDLmPassThruLines.buildTreeExtended(nodePath, 
				                                                             newOverallLines);
		/* Build a tree node for the valid lines associated with every report.
		   Create a new instance for the valid lines. This is only done if the 
		   valid lines reference has not already been set. */
		HDLmPassThruLines   newValidLines = newValue.getValidLinesRef();
		if (newValidLines == null) {
			newValidLines = new HDLmPassThruLines();
			if (newValidLines == null)
				HDLmAssertAction(false, "New valid lines instance was not created");
			newValue.setValidRef(newValidLines);
		  String  nodePathValidLinesString =  HDLmDefines.getString("HDLMVALIDNODENAME");
			if (nodePathValidLinesString == null) {
				String  errorFormat = "Define value for valid lines node name not found (%s)";
				String  errorText = String.format(errorFormat, "HDLMVALIDNODENAME");
				HDLmAssertAction(false, errorText);		    	
			}
			newValidLines.setName(nodePathValidLinesString);
		}
		/* Build an extended tree node for the valid lines. This tree node
       will have children for all of the actual valid lines. */
		HDLmTree  validLinesTree = HDLmPassThruLines.buildTreeExtended(nodePath, 
				                                                           newValidLines);
		/* Build a tree node for the ignore-list associated with every report.
		   This is only done if an ignore-list was actually used to build the
		   report. If no ignore-list was used, then the ignore-list processing
		   will be bypassed. */  
	  HDLmPassThruList   newIgnoreList = newValue.getIgnoreListRef();
	  /* Build an extended tree node for the ignore list. This tree node
       will have children for all of the actual ignores. */
	  HDLmTree  ignoreListTree = null;
	  if (newIgnoreList != null)
	  	 ignoreListTree = HDLmPassThruList.buildTreeExtended(nodePath, 
                                                           newIgnoreList);
		/* Add the new tree nodes to parent tree */
		reportTree.addOrReplaceChild(invalidLinesTree);
		reportTree.addOrReplaceChild(overallLinesTree);
		reportTree.addOrReplaceChild(validLinesTree);
		if (newIgnoreList != null)
			reportTree.addOrReplaceChild(ignoreListTree);
		return reportTree;	
	}
	/* This routine does the overall work of checking a report  
     to see if all of the lines should be ignored. If all of the
     lines should be ignored, then pass-through status is not
     enabled. If at least one line should not be ignored, then
     pass-through status is set.      
      
     This routine assumes that all of the report lines have already
     been generated. It also assumes that the lists of valid and 
     invalid lines have not been generated.
     
     The final ignore status is returned to the caller. The final 
     ignore status is also stored in the report object. The values
     are inverted at this point. In other words, if the ignore status
     is true, then pass-through is set to false and if the ignore 
     status is false, then pass-through is set to true. */ 
  protected static boolean  checkReport(final HDLmPassThruReport report,
                                        final HDLmPassThruList ignoreList) {
		/* Check if the report reference passed by the caller is null */
		if (report == null) {
		  String  errorText = "Report reference passed to checkReport is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the ignore-list reference passed by the caller is null */
		if (ignoreList == null) {
		  String  errorText = "Ignore-list reference passed to checkReport is null";
		  throw new NullPointerException(errorText);
	  }
		/* Declare and define a few variables */
		boolean   reportIgnoreStatus;
		boolean   rv = true;
		/* Check every report line */ 
		reportIgnoreStatus = checkReportLines(report, 
				                                  ignoreList, 
				                                  report.validLines, 
				                                  report.invalidLines);		
		/* Make a copy of the ignore-list object and store a reference
		   to the copy in the report passed by the caller. This is done
		   so that a copy of the ignore-list used to check the report
		   will be saved. */ 
		HDLmPassThruList  ignoreListCopy = new HDLmPassThruList(ignoreList);
		if (ignoreListCopy == null) 
		  HDLmAssertAction(false, "New ignore-list was not created"); 
		report.setIgnoreListRef(ignoreListCopy);		
		/* Store the final report status in the report and return
		   the status to the caller. Note that if the final report
		   status shows that all report lines should be ignored (true),
		   then pass-through status will be set to false. The reverse
		   is also true. */
		if (reportIgnoreStatus)
			report.setStatus(HDLmPassThruStatus.PASSTHRUNOTOK);
		else
			report.setStatus(HDLmPassThruStatus.PASSTHRUOK);  	
		return reportIgnoreStatus;
  }  
  /* This routine checks every line of a report against a set    
     of ignore lines. If every line should be ignored, then this 
     routine will return true. If even one line should not be
     ignored, then this routine will return false.
     
     This routine assumes that all of the report lines have already
     been generated. It also assumes that the lists of valid and 
     invalid lines have not been generated.
     
     Each valid (ignored) report line is added to the array of 
     valid report lines. Each invalid (not ignored) report line
     is added to the array of invalid report lines. */
  protected static boolean  checkReportLines(final HDLmPassThruReport report,
  		                                       final HDLmPassThruList ignoreList,
  		                                       HDLmPassThruLines validLines,
  		                                       HDLmPassThruLines invalidLines) {
  	/* Declare and define a few variables */
  	boolean   lineIgnoreStatus;
  	boolean   rv = true;
		/* Get the reference to the overall set of report lnes. This 
	     is a reference to a lines instance. */
	  HDLmPassThruLines  overallLinesRef = report.overallLines;
	  if (overallLinesRef == null)
	  	HDLmAssertAction(false, "Reference to overall lines from report is null");
  	/* Check every report line */
  	for (Map.Entry<String, HDLmPassThruLine> entry : overallLinesRef.getLines().entrySet()) {
  		String            reportLineName = entry.getKey();
  	  HDLmPassThruLine  reportLine = entry.getValue();
  		/* Check if the current report line should be ignore or not */
  		lineIgnoreStatus = ignoreList.checkReportLine(reportLine);
  		/* If the current line should be ignored, then we add it to the
  		   list of valid lines. Otherwise, we add it to the list of 
  		   invalid lines and reset the return value. */
  		if (lineIgnoreStatus) {
  			validLines.addLine(reportLine);
  		}	
  		else {
  			invalidLines.addLine(reportLine);
  			rv = false;
  		}  		
  	} 	
  	return rv;
  }  
  /* Get the value of the associated node type field and return 
     it to the caller */
	protected HDLmTreeTypes  getAssociatedNodeType() {
		return associatedNodeType;
	}
  /* Get the value of the created field and return it to the caller */
  protected Instant      getCreated() {
  	return created;
  }
  /* Get the ignore-list reference and return it to the caller */
  protected HDLmPassThruList  getIgnoreListRef() {
  	return ignoreList;
  }
  /* Get the invalid lines reference and return it to the caller */
  protected HDLmPassThruLines  getInvalidLinesRef() {
  	return invalidLines;
  }
  /* This routine returns the next report name. The next report name
	   will always be unique and will (in normal cases) be higher than
	   any existing report name. We assume that generated report names
	   take the form 'AAAAAANNNNNN' (without the quotes) where the first
	   part (the As) are a fixed string literal and the second part is 
	   a counter than is incremented. For example, 'Report000001', 
	   'Report000002', and 'Report000003' (without the quotes in all
	   cases) are all standard and valid report names. Note that if
	   the high report name does not have any trailing digits, then
	   the generated report name might not be unique. This is probably
	   not a real problem. */  
	protected static String  getNextReportName(final HDLmPassThruCompany company) {
		/* Check if the company reference passed by the caller is null */
		if (company == null) {
		  String  errorText = "Company reference passed to getNextReportName is null";
		  throw new NullPointerException(errorText);
		}
		/* Try to get the exsting highest report name */
		String  highReportName = company.getHighReportName();
		String  finalReportName;
		/* Try to get the standard report name prefix string */
	  String  reportNamePrefix =  HDLmDefines.getString("HDLMREPORTNAMEPREFIX");
	  if (reportNamePrefix == null) {
	  	String  errorFormat = "Define value for report name prefix string not found (%s)";
		  String  errorText = String.format(errorFormat, "HDLMREPORTNAMEPREFIX");
		  HDLmAssertAction(false, errorText);		    	
	  }
	  /* If the current high report name could not be found, just return
	     a default value to the caller */ 
		if (highReportName == null) {
			finalReportName = reportNamePrefix;
			finalReportName += "000001";
			return finalReportName;  		
		}
		/* Get the trailing numeric digits from the high report name */
		String  trailingDigits = HDLmString.getTrailingDigits(highReportName);
		if (trailingDigits.length() > 0) {
			Integer   trailingInteger = HDLmUtility.convertInteger(trailingDigits);
			if (trailingInteger == null)
				HDLmAssertAction(false, "Report trailing digits could not be converted to an integer");
			finalReportName = HDLmPassThruReport.buildReportName(company,
					                                                 trailingInteger);
			return finalReportName;
		}
		/* We don't have any trailing digits to use */
		finalReportName = reportNamePrefix;
		finalReportName += "000001";
		return finalReportName; 
	} 
  /* Get the overall lines reference and return it to the caller */
  protected HDLmPassThruLines  getOverallLinesRef() {
  	return overallLines;
  }
	/* This routine returns the lines count */  
  protected int          getLinesCount() {
	  return countLines;		
  }
	/* This routine returns the report Type count */  
  protected HDLmReportTypes getReportType() {
	  return reportType;		
  }
	/* This routine returns the current pass-through status of this class
     instance to the caller */ 
  protected HDLmPassThruStatus  getStatus() {
    return passThruStatus;
  } 
  /* Get the valid lines reference and return it to the caller */
  protected HDLmPassThruLines  getValidLinesRef() {
  	return validLines;
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
	/* This routine sets or resets the ignore-list object reference 
     in the current report using a value passed by the caller */  
  protected final void   setIgnoreListRef(final HDLmPassThruList newValue) {
    /* Check if the ignore-list reference passed by the caller is null */
  	if (newValue == null) {
  	  String  errorText = "Ignore-list reference passed to setIgnoreListRef is null";
  	  throw new NullPointerException(errorText);
  	}
    ignoreList = newValue;
  } 
	/* This routine sets or resets the invalid object reference 
	   in the current report using a value passed by the caller 
	   This is a reference to lines object with the name of 
	   'Invalid' (without the quotes). */  
	protected final void   setInvalidRef(final HDLmPassThruLines newValue) {
	  /* Check if the invalid reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Invalid reference passed to setInvalidRef is null";
		  throw new NullPointerException(errorText);
		}
	  invalidLines = newValue;
	} 
	/* This routine sets or resets the overall object reference 
	   in the current report using a value passed by the caller 
	   This is a reference to lines object with the name of 
	   'Overall' (without the quotes). */  
	protected final void   setOverallRef(final HDLmPassThruLines newValue) {
	 /* Check if the overall reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Overall reference passed to setOverallRef is null";
		  throw new NullPointerException(errorText);
		}
	  overallLines = newValue;
	}
	/* This routine sets the lines count to a value passed by the caller */  
  protected final void   setLinesCount(final int newLinesCount) {
	  /* Check if the lists count passed by the caller is valid */
	  if (newLinesCount < 0) {
	    String  errorText = "Lines count passed to setLinesCount is invalid";
	    throw new IllegalArgumentException(errorText);  	
	  }
	  countLines = newLinesCount;		
  }   
	/* This routine sets the match cache reference to a null value */  
  protected final void   setMatchCacheNull() {
	  /* matchCache = null; */ 		
  } 
	/* This routine sets the current report type of a report. Reports have
	   a report type that determines (amount other things) how the report 
	   is formatted. */ 
	protected final void   setReportType(final HDLmReportTypes newValue) {
	  /* Check if the report type enum passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Report type enum reference passed to setReportType is null";
		  throw new NullPointerException(errorText);
		}
	  reportType = newValue;
  }
	/* This routine sets the current pass-through status of this class
	   instance using a value passed by the caller */  
	protected final void   setStatus(final HDLmPassThruStatus newValue) {
	  /* Check if the pass-through status enum passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Pass-through status enum reference passed to setStatus is null";
		  throw new NullPointerException(errorText);
		}
	  passThruStatus = newValue;
	} 
	/* This routine sets the current pass-through status of this class
	   instance to a null value */  
	protected final void   setStatusNull() { 
	  passThruStatus = null;
	}
	/* This routine sets or resets the valid object reference 
	   in the current report using a value passed by the caller 
	   This is a reference to lines object with the name of 
	   'Valid' (without the quotes). */  
	protected final void   setValidRef(final HDLmPassThruLines newValue) {
	  /* Check if the valid reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Valid reference passed to setValidRef is null";
		  throw new NullPointerException(errorText);
		}
	  validLines = newValue;
	}  
}	
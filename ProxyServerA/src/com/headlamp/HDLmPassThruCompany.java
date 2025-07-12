package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction; 
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruCompany short summary.
 *
 * HDLmPassThruCompany description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, one instance of this 
   class will be created for each company. */
/* An instance of this class is created to store the overall
   pass-through status of each company and to maintain the 
   list of reports and ignore-lists associated with each 
   company. */  
public class HDLmPassThruCompany extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruCompany() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
  	/* Declare and define a few variables */
	  Instant  currentTimestampInstant = Instant.now();
  	/* Set a few fields in the new company instance */
  	created = currentTimestampInstant;
  	lastModified = currentTimestampInstant;
  	associatedNodeType = HDLmTreeTypes.COMPANY;
  	passThruStatus = HDLmPassThruStatus.PASSTHRUNOTOK;
  }
	/* This is one of the constructors for this class. It doesn't do
	   anything other than to assign the company name field. Really,
	   this is the web site domain name. All other fields of this 
	   class will be set to the default values specified. */
	protected HDLmPassThruCompany(final String companyName) { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		/* Check if the company name passed by the caller, is null */
		if (companyName == null) {
			String   errorText = "Company name string reference passed to the HDLmPassThruCompany constructor is null";
			throw new NullPointerException(errorText);		
		}
		/* Declare and define a few variables */
		Instant  currentTimestamp = Instant.now();
		/* Set a few fields in the new company instance */
		created = currentTimestamp;
		lastModified = currentTimestamp;
		associatedNodeType = HDLmTreeTypes.COMPANY;
		setName(companyName);
		passThruStatus = HDLmPassThruStatus.PASSTHRUNOTOK;
	}
	/* This is one of the constructors for the company definition class.
	   It must be passed a JSON element that contains all of the details
	   of the company definition. */
	protected HDLmPassThruCompany(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
		   required by the Java language. */ 
		super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build company definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Boolean   curBoolean;
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Set the error count to zero. The error count is incremented each time an
			 error is detected. If the final error count (for the current definition) is
			 greater than zero, the current definition object is disabled (the enabled
			 field is set false). Note that a reference is used below so that the error
			 count can be updated by the routines called using error count.*/
	  MutableInt   errorCounter = new MutableInt(0);
		/* Build an array list for error message strings. Each error
	     message is stored in this array list. */
	  ArrayList<String>   errorMessages = new ArrayList<String>();
	  if (errorMessages == null) {
	  	String  errorText = "Error message ArrayList allocation in HDLmPassThruCompany constructor is null";
	  	throw new NullPointerException(errorText);
	  }	
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
	 	  HDLmAssertAction(false, "JSON element used to build company definition is JSON null");
	  }
	  JsonObject jsonObject = jsonElement.getAsJsonObject();
	  Set<String> jsonKeys = jsonObject.keySet();
	  /* Get the standard class instance variables */
	  HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	 	  	                                                                    HDLmTreeTypes.COMPANY,
	 	  	                                                                    HDLmGetComments.GETCOMMENTSNO,
																																			      HDLmGetCreated.GETCREATEDYES,
																																			      HDLmGetLastModified.GETLASTMODIFIEDYES,
																																			      HDLmGetPassThruStatus.GETSTATUSYES, 
																																			      HDLmOptEnabled.OPTENABLEDYES,
																																		        HDLmOptExtra.OPTEXTRAYES,
																																		        HDLmGetUpdated.GETUPDATEDYES);
	  if (response == null) {
	 	  String  errorText = "Null response from build standard fields routine";
	 	  HDLmAssertAction(false, errorText);
	  }
	  /* Update the error count with the response value */
	  errorCounter.add(response.getErrorCount()); 
	  /* Extract the fields from the build standard fields response */
	  setName(response.getName());
		/* Get the current pass-through type */
		associatedNodeType = response.getAssociatedNodeType();
	  setName(response.getName());
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
		/* Get the last modified date and time and use them to set an instance field */
		lastModified = response.getLastModified();
		/* Get a boolean value and use it to set a instance field */ 
	  curBoolean = HDLmMod.modFieldBoolean(editorType, 
	  		                                 errorCounter,
	  		                                 errorMessages,
	                                       jsonObject, 
	                                       jsonKeys, 
		                                     "passThru",
		                                     HDLmReportErrors.REPORTERRORS);
	  /* Use the boolean value to set the pass-though status field */
	  if (curBoolean != null) {
	  	if (curBoolean)
	  		passThruStatus = HDLmPassThruStatus.PASSTHRUOK;
	  	else
	  		passThruStatus = HDLmPassThruStatus.PASSTHRUNOTOK;
	  }
		/* Mark the current company definition object as disabled if the error count
		   was greater than zero. This is actually done by setting the enabled
		   field to false. */
		if (errorCounter.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
	   need to be obtained and/or set */ 
	private HDLmPassThruStatus    passThruStatus = null;
	private HDLmTreeTypes         associatedNodeType = null;
  private HDLmPassThruData      data = null;
  private HDLmPassThruLists     ignoreLists = null;
  private HDLmPassThruReports   reports = null;
  private HDLmPassThruRules     rules = null;
  /* This routine builds a company instance and does all of the
     additional work associated with building a new company instance.
     This includes building the ignore-lists and reports instances,
     adding the new company to existing companies (if any), building
     an extended tree for the company and everything associated with
     the company, updating the overall pass-through tree with the 
     new company, and finally updating the database with the updated
     tree. */ 
  protected static HDLmPassThruCompany  addCompanyExtended(final String companyName) {
		/* Check if the company name string reference passed by the caller is null */
		if (companyName == null) {
		  String  errorText = "New company name string reference passed to addCompanyExtended is null";
		  throw new NullPointerException(errorText);
	  }
		/* Create the new company instance */
		HDLmPassThruCompany   company = new HDLmPassThruCompany(companyName);
		if (company == null)
			HDLmAssertAction(false, "New company instance not created");
		company.buildCompanyExtended();
  	/* Get the top pass-through instance */
  	HDLmPassThruTop  topPassThru = HDLmMain.getPassThruTopReference();
  	if (topPassThru == null)
  	  HDLmAssertAction(false, "Top pass-through reference is null"); 
		/* Add the new company instance to existing company instances, 
		   if any */ 
  	topPassThru.addCompanyTop(companyName, company); 	
  	/* We can now build a tree node for the company and everything
  	   referenced by the company. The node tree for the company must
	     be added to the existing tree structure. */
		HDLmTree  topNode = HDLmPassThruTop.getTopTree();
	  /* Build a node path for locating the current company */ 
	  String  nodePathTopString =  HDLmDefines.getString("HDLMTOPNODENAME");
	  if (nodePathTopString == null) {
	    String   errorFormat = "Define value for top node name not found (%s)";
	    String   errorText = String.format(errorFormat, "HDLMTOPNODENAME");
	    HDLmAssertAction(false, errorText);                 
	  }
	  String  nodePathCompaniesString =  HDLmDefines.getString("HDLMCOMPANIESNODENAME");
	  if (nodePathCompaniesString == null) {
	    String   errorFormat = "Define value for companies node name not found (%s)";
	    String   errorText = String.format(errorFormat, "HDLMCOMPANIESNODENAME");
	    HDLmAssertAction(false, errorText);                 
	  }
	  /* Build a node path for the Companies node */
	  ArrayList<String>  newCompaniesNodePath = new ArrayList<String>(
	    List.of(nodePathTopString, nodePathCompaniesString));	  
	  HDLmTree  companiesNode = HDLmTree.locateTreeNode(topNode, newCompaniesNodePath); 
	  if (companiesNode == null) {
	    HDLmAssertAction(false, "Null companies node returned by locateTreeNode");
	  }	  		
	  HDLmTree  companyTree = HDLmPassThruCompany.buildTreeExtended(newCompaniesNodePath, company);
	  companiesNode.addOrReplaceChild(companyTree);
	  /* Try to update the JSON source stored on the server, now that
	     we have added a new report */ 			
	  String  contentType = HDLmEditorTypes.PASS.toString();
	  HDLmTree.replaceEntireTree(contentType, topNode);  	
	  return company;
  }   
  /* This method adds an ignore-list to the existing set of 
     ignore-lists (zero, one, or more) for the current company. 
     The first step is to obtain a reference to the ignore-lists
     reference for the current company. */
	protected void          addIgnoreList(String curListName, HDLmPassThruList curList) {
		/* Check if the pass-through ignore-list reference passed by the caller is null */
		if (curList == null) {
		  String  errorText = "New ignore-list reference passed to addIgnoreList is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get the reference to the set of ignore-lists for the current
		   company. This is a reference to an ignore-lists instance. */
		HDLmPassThruLists   listsRef = ignoreLists;
	  if (listsRef == null) {
		  HDLmAssertAction(false, "Reference to ignore-lists from company is null");
	  }
	  /* Add the current ignore-list to the set of ignore-lists */
	  String  ignoreListName = curList.getName();
	  listsRef.addIgnoreList(ignoreListName, curList, HDLmStartupMode.STARTUPMODENO);
	  /* Reset the last modified date and time */
	  lastModified = Instant.now();
	} 
  /* This method adds a report to the existing set of reports 
     (zero, one, or more) for the current company. The first 
     step is to obtain a reference to the reports instance 
     for the current company. */
  protected void         addReport(HDLmPassThruReport curReport) {
  	/* Check if the pass-through report reference passed by the caller is null */
		if (curReport == null) {
	 	String  errorText = "New report reference passed to addReport is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get the reference to the set of reports for the current
		   company. This is a reference to a reports instance. */
  	HDLmPassThruReports  reportsRef = reports;
	  if (reportsRef == null) {
		  HDLmAssertAction(false, "Reference to reports from company is null");
    }
	  /* Add the current report to the set of reports */
	  reportsRef.addReport(curReport, HDLmStartupMode.STARTUPMODENO);
	  /* Reset the last modified date and time */
	  lastModified = Instant.now();
  }  
  /* This method adds a report to the existing set of reports (zero, 
     one, or more) for the current company. The first step is to 
     obtain a reference to the reports instance for the current 
     company. Note that the node tree is also adjusted for the 
     new report. */
	protected void         addReportExtended(HDLmPassThruReport curReport) {
		/* Check if the pass-through report reference passed by the caller is null */
		if (curReport == null) {
	  	String  errorText = "New report reference passed to addReportExtended is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get the reference to the set of reports for the current
		   company. This is a reference to a reports instance. */
		HDLmPassThruReports  reportsRef = reports;
	  if (reportsRef == null) {
		  HDLmAssertAction(false, "Reference to reports from company is null");
	  }
	  /* Add the current report to the set of reports */
	  reportsRef.addReport(curReport, HDLmStartupMode.STARTUPMODENO);
	  /* Reset the last modified date and time */
	  lastModified = Instant.now();
	  /* Try to find the tree node for the reports associated with the
       current company */
    HDLmTree  reportsNode = getReportsTree();
    if (reportsNode == null) {
      HDLmAssertAction(false, "Null reports node returned by getReportsTree");
    }     
	  /* We can now build a tree node for the report and everything
	     referenced by the report. The node tree for the report must
	     be added to the existing reports node tree. */
    ArrayList<String>  nodePath = reportsNode.getNodePath();
	  HDLmTree  reportTree = HDLmPassThruReport.buildTreeExtended(nodePath, 
                                                                curReport);
	  reportsNode.addOrReplaceChild(reportTree);
		/* Try to update the JSON source stored on the server, now that
		   we have added a new report */ 			
		String  contentType = HDLmEditorTypes.PASS.toString();
		HDLmTree.replaceEntireTree(contentType, HDLmTree.getNodePassTreeTop());  
	} 
  /* This routine does some additional work needed to build a company
     instance. A set of instances are created and associated with the
     company, if need be. */ 
  protected void         buildCompanyExtended() {
		/* A data instance is created, if no data instance has been 
       created so far. */
	 HDLmPassThruData   newData = getDataRef();
	 /* Build the data instance if it does not already exist */
	 if (newData == null) {
	   newData = new HDLmPassThruData();
	   if (newData == null)
		    HDLmAssertAction(false, "New data instance was not created");
	   setData(newData);
	   String   nodePathDataString = HDLmDefines.getString("HDLMDATANODENAME");
	   if (nodePathDataString == null) {
	     String  errorFormat = "Define value for data node name not found (%s)";
		    String  errorText = String.format(errorFormat, "HDLMDATANODENAME");
		    HDLmAssertAction(false, errorText);		    	
	   }
	   newData.setName(nodePathDataString);
	 }
    /* An ignore-lists instance is created, if no ignore-lists instance 
		   has been created so far. */ 
	  HDLmPassThruLists   newIgnoreLists = getIgnoreListsRef();		
		/* Build the ignore-lists instance if it does not already exist */
	  if (newIgnoreLists == null) {
		  newIgnoreLists = new HDLmPassThruLists();
		  if (newIgnoreLists == null)
			  HDLmAssertAction(false, "New ignore-lists instance was not created");
		  setIgnoreLists(newIgnoreLists);
	    String  nodePathIgnoreListsString =  HDLmDefines.getString("HDLMIGNORELISTSNODENAME");
		  if (nodePathIgnoreListsString == null) {
		  	String  errorFormat = "Define value for ignore-lists node name not found (%s)";
			  String  errorText = String.format(errorFormat, "HDLMIGNORELISTSNODENAME");
			  HDLmAssertAction(false, errorText);		    	
		  }
		  newIgnoreLists.setName(nodePathIgnoreListsString);
	  }
		/* A reports instance is created, if no reports instance has been 
		   created so far. */
		HDLmPassThruReports   newReports = getReportsRef();
		/* Build the reports instance if it does not already exist */
	  if (newReports == null) {
		  newReports = new HDLmPassThruReports();
		  if (newReports == null)
			  HDLmAssertAction(false, "New reports instance was not created");
		  setReports(newReports);
	    String  nodePathReportsString =  HDLmDefines.getString("HDLMREPORTSNODENAME");
		  if (nodePathReportsString == null) {
		    String  errorFormat = "Define value for reports node name not found (%s)";
			  String  errorText = String.format(errorFormat, "HDLMREPORTSNODENAME");
			  HDLmAssertAction(false, errorText);		    	
		  }
		  newReports.setName(nodePathReportsString);
	  }  	
		/* A rules instance is created, if no rules instance has been 
	     created so far. */
	  HDLmPassThruRules   newRules = getRulesRef();
	  /* Build the rules instance if it does not already exist */
    if (newRules == null) {
	    newRules = new HDLmPassThruRules();
	    if (newRules == null)
		    HDLmAssertAction(false, "New rules instance was not created");
	    setRules(newRules);
      String  nodePathRulesString =  HDLmDefines.getString("HDLMRULESNODENAME");
	    if (nodePathRulesString == null) {
	      String  errorFormat = "Define value for rules node name not found (%s)";
		    String  errorText = String.format(errorFormat, "HDLMRULESNODENAME");
		    HDLmAssertAction(false, errorText);		    	
	    }
	    newRules.setName(nodePathRulesString);
    } 
  }
  /* This routine builds a tree node for a company (just one company) 
	   and returns the final company (just one company) tree node to the
	   caller. The company (just one company) reference is used to set
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruCompany newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through company (just one company) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Company reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current company (just one company) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.COMPANY;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltipString = "newcompmod";
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTooltipString);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  companyTree = new HDLmTree(nodeType,
				                                 nodeTooltip,
				                                 nodePath);
		if (companyTree == null) {
			String  errorText = "New company tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the company (just one company) instance as the details of the new tree node */
		companyTree.setDetails(newValue);		
		return companyTree;	
	}
	/* This routine builds a set of tree nodes for a company and
	   returns the final company tree node to the caller. Other 
	   routines are used to build the ignore-lists and reports
	   nodes (if need be) that are also associated with a company. 
	   The company reference is used to set the details of the 
	   new tree node. */ 
	protected static HDLmTree  buildTreeExtended(ArrayList<String> oldNodePath, 
			                                         HDLmPassThruCompany newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through company reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Company reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Build a new tree node with the correct information */
		HDLmTree  companyTree = HDLmPassThruCompany.buildTree(oldNodePath, 
				                                                  newValue);				 
		if (companyTree == null) {
			String  errorText = "New company tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		ArrayList<String>  nodePath = companyTree.getNodePath();		
		/* Build a tree node for the data values associated with every company. 
       A data instance is created, if no data instance has been 
       created so far. */
    HDLmPassThruData  newData = newValue.getDataRef();
    /* Build the data instance if it does not already exist */
	  if (newData == null) {
	    newData = new HDLmPassThruData();
	    if (newData == null)
		    HDLmAssertAction(false, "New data instance was not created");
	    newValue.setData(newData);
	    String  nodePathDataString =  HDLmDefines.getString("HDLMDATANODENAME");
	    if (nodePathDataString == null) {
	      String  errorFormat = "Define value for data node name not found (%s)";
		    String  errorText = String.format(errorFormat, "HDLMDATANODENAME");
		    HDLmAssertAction(false, errorText);		    	
	    }
	    newData.setName(nodePathDataString);
	  }
	  /* Build a tree node for the data associated with every company */
	  HDLmTree  dataTree = HDLmPassThruData.buildTree(nodePath, 
	                                                  newData);	
		/* Build a tree node for the ignore-lists associated with every company.
		   An ignore-lists instance is created, if no ignore-lists instance 
		   has been created so far. */ 
	  HDLmPassThruLists   newIgnoreLists = newValue.getIgnoreListsRef();		
		/* Build the ignore-lists instance if it does not already exist */
	  if (newIgnoreLists == null) {
		  newIgnoreLists = new HDLmPassThruLists();
		  if (newIgnoreLists == null)
			  HDLmAssertAction(false, "New ignore-lists instance was not created");
		  newValue.setIgnoreLists(newIgnoreLists);
	    String  nodePathIgnoreListsString =  HDLmDefines.getString("HDLMIGNORELISTSNODENAME");
		  if (nodePathIgnoreListsString == null) {
		  	String  errorFormat = "Define value for ignore-lists node name not found (%s)";
			  String  errorText = String.format(errorFormat, "HDLMIGNORELISTSNODENAME");
			  HDLmAssertAction(false, errorText);		    	
		  }
		  newIgnoreLists.setName(nodePathIgnoreListsString);
	  }
		/* Build a tree node for the ignore-lists associated with every company */
		HDLmTree  ignoreListsTree = HDLmPassThruLists.buildTree(nodePath, 
				                                                    newIgnoreLists);
		/* Build a tree node for the reports associated with every company. 
		   A reports instance is created, if no reports instance has been 
		   created so far. */
		HDLmPassThruReports   newReports = newValue.getReportsRef();
		/* Build the reports instance if it does not already exist */
	  if (newReports == null) {
		  newReports = new HDLmPassThruReports();
		  if (newReports == null)
			  HDLmAssertAction(false, "New reports instance was not created");
		  newValue.setReports(newReports);
	    String  nodePathReportsString =  HDLmDefines.getString("HDLMREPORTSNODENAME");
		  if (nodePathReportsString == null) {
		    String  errorFormat = "Define value for reports node name not found (%s)";
			  String  errorText = String.format(errorFormat, "HDLMREPORTSNODENAME");
			  HDLmAssertAction(false, errorText);		    	
		  }
		  newReports.setName(nodePathReportsString);
	  }
	  /* Build a tree node for the reports associated with every company */
		HDLmTree  reportsTree = HDLmPassThruReports.buildTree(nodePath, 
                                                          newReports);
		/* Build a tree node for the rules associated with every company. 
	     A rules instance is created, if no rules instance has been 
	     created so far. */
	  HDLmPassThruRules   newRules = newValue.getRulesRef();
	  /* Build the rules instance if it does not already exist */
    if (newRules == null) {
	    newRules = new HDLmPassThruRules();
	    if (newRules == null)
		    HDLmAssertAction(false, "New rules instance was not created");
	    newValue.setRules(newRules);
      String  nodePathRulesString =  HDLmDefines.getString("HDLMRULESNODENAME");
	    if (nodePathRulesString == null) {
	      String  errorFormat = "Define value for rules node name not found (%s)";
		    String  errorText = String.format(errorFormat, "HDLMRULESNODENAME");
		    HDLmAssertAction(false, errorText);		    	
	    }
	    newRules.setName(nodePathRulesString);
    }
    /* Build a tree node for the rules associated with every company */
	  HDLmTree  rulesTree = HDLmPassThruRules.buildTree(nodePath, 
                                                      newRules);		
		/* Add the new tree nodes to parent tree */
	  companyTree.addOrReplaceChild(dataTree);	
		companyTree.addOrReplaceChild(ignoreListsTree);
		companyTree.addOrReplaceChild(reportsTree);	
		companyTree.addOrReplaceChild(rulesTree);	
		return companyTree;	
	}
  /* Get the value of the associated node type field and return it to the caller */
  protected HDLmTreeTypes  getAssociatedNodeType() {
  	return associatedNodeType;
  }  
  /* The routine get the tree node for a company. The tree node 
     for the company must already exist. */ 
	protected HDLmTree     getCompanyTree() {
	  /* Build a node path for locating the current company */ 
	  String  nodePathTopString =  HDLmDefines.getString("HDLMTOPNODENAME");
	  if (nodePathTopString == null) {
	    String   errorFormat = "Define value for top node name not found (%s)";
	    String   errorText = String.format(errorFormat, "HDLMTOPNODENAME");
	    HDLmAssertAction(false, errorText);                 
	  }
	  String  nodePathCompaniesString =  HDLmDefines.getString("HDLMCOMPANIESNODENAME");
	  if (nodePathCompaniesString == null) {
	    String   errorFormat = "Define value for companies node name not found (%s)";
	    String   errorText = String.format(errorFormat, "HDLMCOMPANIESNODENAME");
	    HDLmAssertAction(false, errorText);                 
	  }
	  /* Build a node path for the current company */
	  ArrayList<String>  nodePath = new ArrayList<String>(
	    List.of(nodePathTopString, nodePathCompaniesString, getName()));
	  /* Use the pass-through node tree structure */ 
	  if (HDLmTree.getNodePassTreeTop() == null)
	  	HDLmAssertAction(false, "Top of pass-through tree is null");
	  /* Try to find the tree node for the current company */ 
	  HDLmTree  companyNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(),
	                                                  nodePath);  
	  if (companyNode == null) {
	    HDLmAssertAction(false, "Null company node returned by locateTreeNode");
	  }  
		return companyNode;  	
	}
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  } 
  /* Get the data reference and return it to the caller */
  protected HDLmPassThruData  getDataRef() {
  	return data;
  }
	/* This routine returns the first ignore-list to the caller. Of course
	   the number of ignore-lists may be one or greater than one. This 
	   routine tries to get the first ignore-list from the ignore-list
	   tree map, rather than then the node tree.
	  
	   This routine will fail if no ignore-lists are available. The caller
	   must make sure that at least one ignore-list is actually available. */
	protected HDLmPassThruList  getFirstIgnoreList() {
		/* Get the reference to the set of ignore-lists. This 
		   is a reference to an ignore-lists instance. */
		HDLmPassThruLists  ignoreListsRef = ignoreLists;
		if (ignoreListsRef == null)
			HDLmAssertAction(false, "Reference to ignore-lists from company is null");
		/* Make sure we have at least one ignore-list */
		int   ignoreListCount = ignoreListsRef.getIgnoreLists().size();
		if (ignoreListCount < 1)
		  HDLmAssertAction(false, "Size of ignore-lists is less than one"); 
		/* Get the first ignore-list from the tree map of ignore-lists */		
		String            keyFirst = ignoreListsRef.getIgnoreLists().firstKey();
		HDLmPassThruList  firstIgnoreList = ignoreListsRef.getIgnoreLists().get(keyFirst);
		if (firstIgnoreList == null)
		  HDLmAssertAction(false, "First ignore-list is null"); 
		return firstIgnoreList;		
	}
	/* This method returns the highest report name to the caller.
	   Of course, no reports may actually exist. In that case, a
	   null value is returned to the caller. */
	protected String       getHighReportName() {
		/* Get the reference to the set of reports for the current
		   company. This is a reference to a reports instance. */
		HDLmPassThruReports   reportsRef = reports; 
	  if (reportsRef == null) {
		  HDLmAssertAction(false, "Reference to reports from company is null");
	  }
	  return reportsRef.getHighReportName();
	} 
  /* This method get the count of the number of ignore-lists in the 
     set of ignore-lists associated with the current company */
	protected int          getIgnoreListsCount() {
		/* Get the reference to the set of ignore-lists for the current
		   company. This is a reference to an ignore-lists instance. */
		HDLmPassThruLists   listsRef = ignoreLists;
	  if (listsRef == null) {
		  HDLmAssertAction(false, "Reference to ignore-lists from company is null");
	  }
    return listsRef.getIgnoreListsCount();
	} 
  /* Get the ignore-lists reference and return it to the caller */
  protected HDLmPassThruLists  getIgnoreListsRef() {
  	return ignoreLists;
  }
	/* This routine returns a reference to the last modified instant */  
  protected Instant      getLastModified() {
	  return lastModified;		
  }  
  /* This method gets the count of the number of reports, in the 
     set of reports, associated with the current company. Note 
     that the count of reports could be low (say 3) even though
     the high report name was something like 'Report000021'
     (without the quotes). This can happen if reports have been
     renamed or deleted. */
	protected int          getReportsCount() {
		/* Get the reference to the set of reports for the current
		   company. This is a reference to a reports instance. */
		HDLmPassThruReports   reportsRef = reports; 
	  if (reportsRef == null) {
		  HDLmAssertAction(false, "Reference to reports from company is null");
	  }
	  return reportsRef.getReportsCount();
	} 
  /* Get the reports reference and return it to the caller */
  protected HDLmPassThruReports  getReportsRef() {
  	return reports;
  }
  /* The routine get the tree node for the reports associated 
     with a company. All companies have a set of reports 
     associated with them. The number of actual reports may
     be zero, one, or greater. */ 
  protected HDLmTree     getReportsTree() {
    /* Build a node path for locating the reports tree node for the 
	     current company */ 
	  String  nodePathTopString =  HDLmDefines.getString("HDLMTOPNODENAME");
	  if (nodePathTopString == null) {
	    String   errorFormat = "Define value for top node name not found (%s)";
	    String   errorText = String.format(errorFormat, "HDLMTOPNODENAME");
	    HDLmAssertAction(false, errorText);                 
	  }
	  String  nodePathCompaniesString = HDLmDefines.getString("HDLMCOMPANIESNODENAME");
	  if (nodePathCompaniesString == null) {
	    String   errorFormat = "Define value for companies node name not found (%s)";
	    String   errorText = String.format(errorFormat, "HDLMCOMPANIESNODENAME");
	    HDLmAssertAction(false, errorText);                 
	  }
	  String  nodePathReportsString = HDLmDefines.getString("HDLMREPORTSNODENAME");
	  if (nodePathReportsString == null) {
	    String   errorFormat = "Define value for reports node name not found (%s)";
	    String   errorText = String.format(errorFormat, "HDLMREPORTSNODENAME");
	    HDLmAssertAction(false, errorText);                 
	  }
	  /* Build a node path for the reports associated with a company */
	  ArrayList<String>  nodePath = new ArrayList<String>(
	    List.of(nodePathTopString, nodePathCompaniesString, getName(), nodePathReportsString));
	  /* Use the pass-through node tree structure */ 
	  if (HDLmTree.getNodePassTreeTop() == null)
	  	HDLmAssertAction(false, "Top of pass-through tree is null");
	  /* Try to find the tree node for the reports associated with the
	     current company */
	  HDLmTree  reportsNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(),
	                                                  nodePath);  
	  if (reportsNode == null) {
	    HDLmAssertAction(false, "Null reports node returned by locateTreeNode");
	  }  
  	return reportsNode;  	
  }
  /* Get the rules reference and return it to the caller */
  protected HDLmPassThruRules  getRulesRef() {
  	return rules;
  }
	/* This routine returns the current pass-through status of this class
     instance (company) to the caller */ 
  protected HDLmPassThruStatus  getStatus() {
    return passThruStatus;
  }
	/* This routine sets the associated node type of this class
	   instance to a null value */  
	protected final void   setAssociatedNodeTypeNull() { 
	  lastModified = Instant.now(); 
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
	/* This routine sets the data reference for a company 
     instance using a value passed by the caller */ 
  protected final void   setData(final HDLmPassThruData newValue) {
    /* Check if the data reference passed by the caller is null */
    if (newValue == null) {
      String  errorText = "Data reference passed to setData is null";
      throw new NullPointerException(errorText);
    }
    data = newValue;
  } 
	/* This routine sets the ignore-lists reference for a company 
	   instance using a value passed by the caller */ 
	protected final void   setIgnoreLists(final HDLmPassThruLists newValue) {
	  /* Check if the ignore-lists reference passed by the caller is null */
	  if (newValue == null) {
	    String  errorText = "Ignore-lists reference passed to setIgnoreLists is null";
	    throw new NullPointerException(errorText);
	  }
	  ignoreLists = newValue;
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
	/* This routine sets the reports reference for a company 
	   instance using a value passed by the caller */ 
	protected final void   setReports(final HDLmPassThruReports newValue) {
	  /* Check if the reports reference passed by the caller is null */
	  if (newValue == null) {
	    String  errorText = "Reports reference passed to setReports is null";
	    throw new NullPointerException(errorText);
	  }
	  reports = newValue;
	}  
	/* This routine sets the rules reference for a company 
     instance using a value passed by the caller */ 
  protected final void   setRules(final HDLmPassThruRules newValue) {
    /* Check if the rules reference passed by the caller is null */
    if (newValue == null) {
      String  errorText = "Rules reference passed to setRules is null";
      throw new NullPointerException(errorText);
    }
    rules = newValue;
  } 
	/* This routine sets the current pass-through status of this class
	   instance using a value passed by the caller */  
	protected final void   setStatus(final HDLmPassThruStatus newValue) {
		/* Check if the pass-through status reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Pass-through status reference passed to setStatus is null";
		  throw new NullPointerException(errorText);
	  }
		/* Reset the last modified timestamp for the current company */
		lastModified = Instant.now(); 
	  passThruStatus = newValue;
	}
	/* This routine sets the current pass-through status of this class
	   instance to a null value */  
	protected final void   setStatusNull() { 
	  lastModified = Instant.now();
	  passThruStatus = null;
	}
}	
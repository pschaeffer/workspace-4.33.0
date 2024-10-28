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
 * HDLmPassThruTop short summary.
 *
 * HDLmPassThruTop description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, only one instance of
   this class will be created as part of product startup.
   The overall pass-through status of the server is stored
   in the one and only instance of this class. */
/* An instance of this class is created to store the overall
   pass-through status of the server and to reference the 
   companies instance. The companies instance is the parent
   of all individual companies. Each company can have it's 
   own pass-through status. */ 
public class HDLmPassThruTop extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruTop() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
	  Instant   currentTimestamp = Instant.now();
  	created = currentTimestamp;
  	lastModified = currentTimestamp;
  	associatedNodeType = HDLmTreeTypes.TOP;
  	passThruStatus = HDLmPassThruStatus.PASSTHRUNOTOK;
  }
	/* This is one of the constructors for the top definition class.
	   It must be passed a JSON element that contains all of the details
 	   of the top definition. */
	protected HDLmPassThruTop(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build top definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Integer   curInteger;
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
		  HDLmAssertAction(false, "JSON element used to build top definition is JSON null");
	  }
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    Set<String> jsonKeys = jsonObject.keySet();
    /* Get the standard class instance variables */
    HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	  	                                                                      HDLmTreeTypes.TOP,
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
		/* Get the last modified date and time and use them to set an instance field */
		lastModified  = response.getLastModified();
		/* Mark the current report definition object as disabled
		   if the error count was greater than zero. This is actually
		   done by setting the enabled field to false. */
		if (errors.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
	   need to be obtained and/or set */ 
	private HDLmTreeTypes           associatedNodeType = null;
  private HDLmPassThruStatus      passThruStatus = null;
  private HDLmPassThruCompanies   companiesReference = null;
  private HDLmPassThruReports     reportsReference = null;
  /* This routine adds a new company to the tree map of companies. 
     This routine will only work if the company does not exist in
     the tree map of companies. */
  protected void         addCompanyTop(final String newName,
  		                                 final HDLmPassThruCompany newCompany) {
		/* Check if the company name string reference passed by the caller is null.
		   This is really a web site name or host domain name, not a company name.  */
	  if (newName == null) {
	  	String  errorText = "Company name reference passed to addCompany is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the company reference passed by the caller is null */
	  if (newCompany == null) {
	  	String  errorText = "Company reference passed to addCompany is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get the companies pass-through reference */
		HDLmPassThruCompanies   companiesPassThru = getCompaniesReference();
		if (companiesPassThru == null)
		  HDLmAssertAction(false, "Companies pass-through reference is null");
		/* Add the company to the tree map of companies using a routine 
		   associated with the companies instance */
		companiesPassThru.addCompanyCompanies(newName, newCompany, true);
  }
  /* This routine builds a tree node for a top (top of the node tree) 
	   and returns the final top (top of the node tree) tree node to the
	   caller. The top (top instance) reference is used to set the details
	   of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruTop newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through top (top instance) reference passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Top reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current top (top instance) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.TOP;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  topTree = new HDLmTree(nodeType,
				                             nodeTooltip,
				                             nodePath);
		if (topTree == null) {
			String  errorText = "New top tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the top (top instance) instance as the details of the new tree node */
		topTree.setDetails(newValue);		
		return topTree;	
	}  
  /* This routine handles the output from checking one web site using
     the standard external program */
  protected static HDLmPassThruStatus  checkWebSite(final String webSite, 
  		                                              final String stdOut) {
		/* Check if the web site string reference (company name) passed 
		   by the caller is null */
		if (webSite == null) {
			String  errorText = "Web site string reference passed to checkWebSite is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the standard output string reference passed by the caller is null */
  	if (stdOut == null) {
	  	String  errorText = "Standard output string reference passed to checkWebSite is null";
		  throw new NullPointerException(errorText);
	  }  	
		/* Build the pass-through tree from the pass-through data and definitions.
		   This is done here so that the newest data from the database will always
		   be used to build the current report. */
  	HDLmTree   passThroughTree = HDLmMain.buildNodeTreeMain(null, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO); 
		if (passThroughTree == null) {
			HDLmAssertAction(false, "Null pass-through definitions tree returned by buildNodeTreeMain");
		}  
		HDLmTree.setNodePassTreeTop(passThroughTree);   	
  	/* Get the top pass-through instance */
  	HDLmPassThruTop  topPassThru = HDLmMain.getPassThruTopReference();
  	if (topPassThru == null)
  	  HDLmAssertAction(false, "Top pass-through reference is null"); 
		/* Get the companies pass-through reference */
		HDLmPassThruCompanies   companiesPassThru = topPassThru.getCompaniesReference();
		if (companiesPassThru == null)
		  HDLmAssertAction(false, "Companies pass-through reference is null");
  	/* Try to locate the web site in the companies tree map. If we 
  	   don't find the company entry, then the company entry must be
  	   created. */ 
  	HDLmPassThruCompany   company = companiesPassThru.companiesTreeMap.get(webSite);
  	if (company == null) {
  		company = HDLmPassThruCompany.addCompanyExtended(webSite);
			if (company == null)
				HDLmAssertAction(false, "New company instance not created");
  	}
  	/* Create an empty report instance. The lines of the report are 
  	   added below. */ 
  	HDLmPassThruReport  report = HDLmPassThruReport.buildNewReportExtended(company,
  			                                                                   HDLmReportTypes.CHECKWEBSITE);
  	if (report == null)
  	  HDLmAssertAction(false, "Report instance was not returned by buildNewReportExtended");  	 	
  	/* Split the string written to standard output into a set of lines 
  	   and check each line to see if it should be in a report */
  	String lines[] = stdOut.split("\\r?\\n");
  	int   lineCounter = 0;
  	for (String line : lines) {
  		ArrayList<HDLmToken>  lineTokens = HDLmString.getTokens(line, '"');  		
  		/* Check if the current line has enough tokens to be considered 
  		   as a report line */ 
  		if (lineTokens.size() >= 16) {
  			lineCounter++;
  			String  lineName = HDLmPassThruLine.buildLineName(lineCounter);
  			HDLmPassThruLine  reportLine = HDLmPassThruLine.buildLine(lineName,
  					                                                      lineTokens);
  			/* The call below appears to add a report line to a report. Actually,
  			   the report line is added to the overall set of lines associated 
  			   with the report. */ 
  			report.addLine(reportLine);
  		}  		
  	}  
  	/* We can not get the count of ignore-lists */
  	int   ignoreListsCount = company.getIgnoreListsCount();
  	/* What follows is temporary code, just for testing. This code is
  	   no longer in use. Note that ignore-lists count check will never
  	   be true. If no ignore-lists are available for a company, then
  	   ignore processing will be skipped. */
  	if (ignoreListsCount == 0 &&
  			ignoreListsCount > 0) {
  		HDLmPassThruList  dummyIgnoreList = HDLmPassThruList.buildDummyIgnoreList();  	
  		if (dummyIgnoreList == null) 
  		  HDLmAssertAction(false, "Dummy ignore-list was not created"); 
  		company.addIgnoreList("DummyIgnoreList", dummyIgnoreList);
  		ignoreListsCount = company.getIgnoreListsCount();
  	}  	
  	/* We can now check the report to see if shows any errors or not.
	     The report will either be valid or invalid. Of course, this
	     check can only be done if we actually have an ignore list
	     to use for the checking. */
  	if (ignoreListsCount > 0) {
  		HDLmPassThruList  firstIgnoreList = company.getFirstIgnoreList();
  		if (firstIgnoreList == null)
  			HDLmAssertAction(false, "First ignore-list reference is null");
  		boolean   reportRv = HDLmPassThruReport.checkReport(report, firstIgnoreList);
  		/* Use the final report status to set the status of the company. 
		     Note that if the final report status shows that all report 
		     lines should be ignored (true), then pass-through status for
		     the company will be set to false. The reverse is also true. */
			if (reportRv)
				company.setStatus(HDLmPassThruStatus.PASSTHRUNOTOK);
			else
				company.setStatus(HDLmPassThruStatus.PASSTHRUOK);    		
  	}   	
		/* Add the current report to the list of reports for the current 
	     company (really web site). Note that this call adds the new
	     report to the group of reports and also adjusts the node tree
	     to include the new report. */ 
	  company.addReportExtended(report); 		
  	/* Get the final pass-through status of the company and return it
  	   to the caller */ 
  	HDLmPassThruStatus  companyPassThru = company.getStatus();  	
  	return companyPassThru; 	
  }  
  /* Get the value of the type field and return it to the caller */
  protected HDLmTreeTypes  getAssociatedNodeType() {
  	return associatedNodeType;
  }
  /* This routine returns a reference to the companies instance that
     is the parent of all actual companies. Of course, this instance
     may not have been created, in which case this routine will return
     a null value. */
  protected HDLmPassThruCompanies  getCompaniesReference() {
	  return companiesReference;  	
  }   
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  } 
	/* This routine returns a reference to the last modified instant */  
  protected Instant      getLastModified() {
	  return lastModified;		
  } 
  /* This routine gets the company reference for a website. The company 
	   is created, if it does not already exist. This means that this 
	   routine will always return a company reference to the caller. */
	protected static HDLmPassThruCompany  getOrAddWebSite(final String webSite) { 
		/* Check if the web site string reference (company name) passed 
		   by the caller is null */
		if (webSite == null) {
			String  errorText = "Web site string reference passed to getOrAddWebSite is null";
			throw new NullPointerException(errorText);
		}
		/* Get the top pass-through instance */
		HDLmPassThruTop  topPassThru = HDLmMain.getPassThruTopReference();
		if (topPassThru == null)
		  HDLmAssertAction(false, "Top pass-through reference is null"); 
		/* Get the companies pass-through reference */
		HDLmPassThruCompanies   companiesPassThru = topPassThru.getCompaniesReference();
		if (companiesPassThru == null)
		  HDLmAssertAction(false, "Companies pass-through reference is null"); 
		/* Try to locate the web site in the companies tree map. If we 
		   don't find the company entry, then the company entry must be
		   created. */ 
		HDLmPassThruCompany   company = companiesPassThru.companiesTreeMap.get(webSite);
		if (company == null) {
			company = HDLmPassThruCompany.addCompanyExtended(webSite);
			if (company == null)
				HDLmAssertAction(false, "New company instance not created");
		}
		/* Return the company reference to the caller */ 
		return company; 		
	}	
	/* This routine returns the current pass-through status of this class
     instance to the caller */ 
  protected HDLmPassThruStatus  getStatus() {
    return passThruStatus;
  }
  /* The routine get the top pass-through tree node. The top 
     tree node must already exist. */ 
	protected static HDLmTree  getTopTree() {
	  /* Get the pass-through node tree structure */  
	  if (HDLmTree.getNodePassTreeTop() == null)
	   	HDLmAssertAction(false, "Top of pass-through tree is null"); 
		return HDLmTree.getNodePassTreeTop();  	
	}
	/* This routine builds a report from a set of error information 
	   passed to it. The report is only comprised of error data and
	   values. */
	protected static void  reportErrors(final String webSite, 
			                                final String stdOut, 
			                                final String stdErr,
			                          		  final String executeMessage, 
			                      		  		final String iOExceptionMessage) {
		/* Check if the web site string reference (company name) passed 
		   by the caller is null */
		if (webSite == null) {
			String  errorText = "Web site string reference passed to reportErrors is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the standard output string reference passed by the caller is null.
		   This check is not really done. Null string references can be passed to this
		   routine. */
		if (stdOut == null &&
				stdOut != null) {
	   	String  errorText = "Standard output string reference passed to reportErrors is null";
		  throw new NullPointerException(errorText);
	  }  	
		/* Check if the standard error string reference passed by the caller is null.
		   This check is not really done. Null string references can be passed to this
		   routine. */
	  if (stdErr == null &&
	  		stdErr != null) {
    	String  errorText = "Standard error string reference passed to reportErrors is null";
 	    throw new NullPointerException(errorText);
    }  	
		/* Build the pass-through tree from the pass-through data and definitions.
		   This is done here so that the newest data from the database will always
		   be used to build the current report. The current report is an error
		   report. However, it still should be added to the newest data. */
		HDLmTree   passThroughTree = HDLmMain.buildNodeTreeMain(null, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO); 
		if (passThroughTree == null) {
			HDLmAssertAction(false, "Null pass-through definitions tree returned by buildNodeTreeMain");
		}  
		HDLmTree.setNodePassTreeTop(passThroughTree);  	
		/* Get the top pass-through instance */
		HDLmPassThruTop  topPassThru = HDLmMain.getPassThruTopReference();
		if (topPassThru == null)
		  HDLmAssertAction(false, "Top pass-through reference is null"); 
		/* Get the companies pass-through reference */
		HDLmPassThruCompanies   companiesPassThru = topPassThru.getCompaniesReference();
		if (companiesPassThru == null)
		  HDLmAssertAction(false, "Companies pass-through reference is null");
		/* Try to locate the web site in the companies tree map. If we 
		   don't find the company entry, then the company entry must be
		   created. */ 
		HDLmPassThruCompany   company = companiesPassThru.companiesTreeMap.get(webSite);
		if (company == null) {
			company = HDLmPassThruCompany.addCompanyExtended(webSite);
			if (company == null)
				HDLmAssertAction(false, "New company instance not created");
		}
		/* Create an empty report instance. The lines of the report are 
		   added by this call. Of course, this is an error report. However, 
		   the standard report mechanism is used for error reporting. */ 
  	HDLmPassThruReport  report = HDLmPassThruReport.buildNewReportExtended(company,
                                                                           HDLmReportTypes.CHECKERROR);
    if (report == null)
      HDLmAssertAction(false, "Report instance was not returned by buildNewReportExtended");

  	/* The mutable integer is used to create the line numbers */ 
  	MutableInt        lineCounter = new MutableInt(0);
  	/* We can now create a new report line using the execute exception message */
  	if (executeMessage != null) {
			HDLmPassThruLine.addErrorLine(report, 
					                          lineCounter, 
					                          "Execute", 
					                          "EN", 
					                          "None", 
					                          executeMessage);  
  	}
  	/* We can now create a new report line using the I/O exception message */
  	if (iOExceptionMessage != null) {  	
			HDLmPassThruLine.addErrorLine(report, 
													          lineCounter, 
													          "Execute", 
													          "EN", 
													          "None", 
													          iOExceptionMessage);  
  	}  
  	/* Check if anything was written to standard output */
  	if (stdOut != null) { 
	    HDLmPassThruLine.addErrorLines(report, 
	    		                           lineCounter,
						                         "Stdout", 
						                         "EN", 
						                         "None", 
						                         stdOut);  

	  }  
  	/* Check if anything was written to error output */
  	if (stdErr != null) { 
	    HDLmPassThruLine.addErrorLines(report, 
                                     lineCounter,
						                         "Stderr", 
						                         "EN", 
						                         "None", 
						                         stdErr); 
	  }   	
		/* Add the current report to the list of reports for the current 
		   company (really web site). Note that this call adds the new
		   report to the group of reports and also adjusts the node tree
		   to include the new report. */ 
		company.addReportExtended(report); 			
	}  
	/* This routine sets the associated node type of this class
	   instance to a null value */  
	protected final void   setAssociatedNodeTypeNull() { 
	  lastModified = Instant.now();
	  associatedNodeType = null;
  }
	/* This routine sets the companies reference for a top instance 
	   using a value passed by the caller */ 
	protected final void   setCompaniesReference(final HDLmPassThruCompanies newValue) {
	  /* Check if the companies reference passed by the caller is null */
	  if (newValue == null) {
	    String  errorText = "Companies reference passed to setCompaniesReference is null";
	    throw new NullPointerException(errorText);
	  }
	  companiesReference = newValue;
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
	/* This routine sets the reference to the last modified instant */  
  protected final void   setLastModified(final Instant newLastModified) {
	  /* Check if the last modified reference passed by the caller is null */
	  if (newLastModified == null) {
	    String  errorText = "Last modified reference passed to setLastModified is null";
	    throw new NullPointerException(errorText);
	  }
	  lastModified = newLastModified;		
  } 
	/* This routine sets the reports reference for a top instance 
     using a value passed by the caller */ 
  protected final void   setReportsReference(final HDLmPassThruReports newValue) {
    /* Check if the reports reference passed by the caller is null */
    if (newValue == null) {
      String  errorText = "Reports reference passed to setReportsReference is null";
      throw new NullPointerException(errorText);
    }
    reportsReference = newValue;
  }
	/* This routine sets the current pass-through status of this class
	   instance to a null value */  
	protected final void   setStatus(final HDLmPassThruStatus newValue) {
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
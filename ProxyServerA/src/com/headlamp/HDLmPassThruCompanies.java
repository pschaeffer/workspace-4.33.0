package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmPassThruCompanies short summary.
 *
 * HDLmPassThruCompanies description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created. In practice, only one instance of
   this class will be created as part of product startup. The 
   one instance of this class is (will be) the parent to each
   individual company. */ 
/* An instance of this class is created to maintain the list
   of companies. Each company can have it's own pass-through
   status. */ 
/* One instance of this class is created for all companies. The 
   name of this class notwithstanding, this class does not do 
   any pass-through processing. The name was picked for consistency
   with other related classes. */ 
public class HDLmPassThruCompanies extends HDLmMod {
	/* This is the default constructor for this class. It doesn't do
	   anything. All fields of this class will be set to the default 
	   values specified. */
  protected HDLmPassThruCompanies() { 
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
  	Instant  currentTimestampInstant = Instant.now(); 
  	created = currentTimestampInstant;
  	lastModified = currentTimestampInstant;
  	associatedNodeType = HDLmTreeTypes.COMPANIES;
  	countCompanies = 0;
  	companiesTreeMap = new TreeMap<String, HDLmPassThruCompany>();
  }
	/* This is one of the constructors for the companies definition class.
	   It must be passed a JSON element that contains all of the details
 	   of the companies definition. */
	protected HDLmPassThruCompanies(JsonElement jsonElement) {
		/* Invoke the default constructor for the parent class. This is 
	     required by the Java language. */ 
	  super();
		/* Check if a null JSON element reference was passed to this routine */
		if (jsonElement == null) {
			String  errorText = "JSON element used to build companies definition details is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		Integer   curInteger;
		/* Show that we are handling a pass-through definition */
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		/* Create the companies collection that will be needed later */
   	companiesTreeMap = new TreeMap<String, HDLmPassThruCompany>();
  	if (companiesTreeMap == null)
  		HDLmAssertAction(false, "New Treemap<> not created for companies");
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
			String  errorText = "Error message ArrayList allocation in HDLmPassThruCompanies constructor is null";
			throw new NullPointerException(errorText);
		}	
	  /* Get the list of keywords and values in the JSON object */
	  if (jsonElement.isJsonNull()) {
		  HDLmAssertAction(false, "JSON element used to build comapnies definition is JSON null");
	  }
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    Set<String> jsonKeys = jsonObject.keySet();
    /* Get the standard class instance variables */
    HDLmPassThruResponse  response = HDLmPassThruResponse.getStandardFields(jsonElement, 
	  	                                                                      HDLmTreeTypes.COMPANIES,
	  	                                                                      HDLmGetComments.GETCOMMENTSNO,
																																		        HDLmGetCreated.GETCREATEDYES,
																																		        HDLmGetLastModified.GETLASTMODIFIEDYES,
																																		        HDLmGetPassThruStatus.GETSTATUSNO,
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
	  /* Get the current type */
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
		/* Get the last modified date and time and use them to set an instance field */
		lastModified = response.getLastModified();
		/* Get an integer value and use it to set an instance field */ 
	  curInteger = HDLmMod.modFieldInteger(editorType, 
	  		                                 errorCounter,
	  		                                 errorMessages,
	                                       jsonObject, 
	                                       jsonKeys, 
		                                     "countCompanies", 
		                                     HDLmReportErrors.REPORTERRORS);
	  countCompanies = curInteger;
	  /* We don't really want to use the count of companies we obtained from
       the JSON. We really need to use the companies count we get by adding
       each company to the set of companies. */ 
    countCompanies = 0; 
		/* Mark the current report definition object as disabled
		   if the error count was greater than zero. This is actually
		   done by setting the enabled field to false. */
		if (errorCounter.intValue() > 0) {
			setEnabled((Boolean) false);
	  }
	}
	/* Getters and setters are provided for the values that 
	   need to be obtained and/or set */ 
	private HDLmTreeTypes       associatedNodeType = null; 
  private Integer             countCompanies = 0;
  TreeMap<String, HDLmPassThruCompany>  companiesTreeMap = null;
  /* This routine adds a new company to the tree map of companies. 
     This routine will only work if the company does not exist in
     the tree map of companies. */
  protected void         addCompanyCompanies(final String newName,
  		                                       final HDLmPassThruCompany newCompany,
  		                                       boolean updateLast) {
		/* Check if the company name string reference passed by the caller is null.
		   This is really a web site name or host domain name, not a company name.  */
	  if (newName == null) {
	  	String  errorText = "Company name reference passed to addCompanyCompanies is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the company reference passed by the caller is null */
	  if (newCompany == null) {
	  	String  errorText = "Company reference passed to addCompanyCompanies is null";
		  throw new NullPointerException(errorText);
	  }
  	/* Check if the company name has already been added to the tree map
  	   of companies */ 
	  if (companiesTreeMap.containsKey(newName)) {
    	String   errorFormat = "Company (%s) has already been added to companies tree map";
			String   errorText = String.format(errorFormat, newName); 
	  	throw new IllegalArgumentException(errorText);  	
	  }
	  /* Add the new company to the companies tree map */
	  companiesTreeMap.put(newName,  newCompany);
	  countCompanies++;
	  if (updateLast)
	    lastModified = Instant.now();
  }
  /* This routine builds a tree node for a companies (companies node) 
     and returns the final companies (companies node) tree node to the
	   caller. The companies (companies instance) reference is used to set 
	   the details of the new tree node. */ 
	protected static HDLmTree  buildTree(ArrayList<String> oldNodePath, 
			                                 HDLmPassThruCompanies newValue) {
		/* Check if the old node path reference passed by the caller is null */
		if (oldNodePath == null) {
		  String  errorText = "Node path reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Check if the pass-through companies (companies instance) reference 
		   passed by the caller is null */
		if (newValue == null) {
		  String  errorText = "Companies reference passed to buildTree is null";
		  throw new NullPointerException(errorText);
	  }
		/* Get some information about the current companies (companies instance) */
		HDLmTreeTypes  nodeType = HDLmTreeTypes.COMPANIES;
		String    nodeName = newValue.getName();
		String    nodeTypeStringLowerCase = nodeType.toString().toLowerCase();
		String    nodeTooltip = HDLmTree.getTooltipString(nodeTypeStringLowerCase);
		ArrayList<String>  nodePath = new ArrayList<String>(oldNodePath);
		nodePath.add(nodeName);
		/* Build a new tree node with the correct information */
		HDLmTree  companiesTree = new HDLmTree(nodeType,
				                                   nodeTooltip,
				                                   nodePath);
		if (companiesTree == null) {
			String  errorText = "New companies tree node was not created";
			HDLmAssertAction(false, errorText);
		}
		/* Use the companies (companies instance) instance as the details 
		   of the new tree node */
		companiesTree.setDetails(newValue);		
		return companiesTree;	
	} 
  /* Get the value of the associated node type field and return it to the caller */
  protected HDLmTreeTypes  getAssociatedNodeType() {
  	return associatedNodeType;
  }
	/* This routine returns the company count */  
  protected int          getCompaniesCount() {
	  return countCompanies;		
  }
  /* This routine get the list (actually a tree map) of companies
     currently known to the server and returns the list to the 
     caller */ 
  protected TreeMap<String, HDLmPassThruCompany>  getCompaniesTreeMap() {
	  return companiesTreeMap;  	
  }   
	/* This routine returns a reference to the created instant */  
  protected Instant      getCreated() {
	  return created;		
  } 
	/* This routine returns a reference to the last modified instant */  
  protected Instant      getLastModified() {
	  return lastModified;		
  } 
	/* This routine sets the associated node type of this class
	   instance to a null value */  
	protected final void   setAssociatedNodeTypeNull() { 
		 lastModified = Instant.now(); 
	  associatedNodeType = null;
	}
	/* This routine sets the companies count to a value passed by the caller */  
  protected final void   setCompaniesCount(final int newCompaniesCount) {
	  /* Check if the companies count passed by the caller is valid */
	  if (newCompaniesCount < 0) {
	    String  errorText = "Companies count passed to setCompaniesCount is invalid";
	    throw new IllegalArgumentException(errorText);  	
	  }
	  countCompanies = newCompaniesCount;		
  } 
	/* This routine sets the companies tree map reference to a null value */  
  protected final void   setCompaniesTreeMapNull() {
	  companiesTreeMap = null; 		
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
}	
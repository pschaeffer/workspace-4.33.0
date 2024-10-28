package com.headlamp;

import com.google.gson.annotations.SerializedName;

/**
 * HDLmDatabaseRow short summary.
 *
 * HDLmDatabaseRow description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */ 
public class HDLmDatabaseRow {
  /* This class can be instantiated using the
     constructor below in some cases */
	protected HDLmDatabaseRow(final String infoStr, final String contentStr,
		                      	final String nameStr, final int idValue,
                      			final String companyStr, final String reportStr) {
		/* Check if the info string value passed by the caller is null */
		if (infoStr == null) {
			String  errorText = "Info value string passed to HDLmDatabaseRow is null";
			throw new NullPointerException(errorText);
		}		 
		/* Check if the content string value passed by the caller is null */
		if (contentStr == null) {
			String  errorText = "Content value string passed to HDLmDatabaseRow is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the name string value passed by the caller is null */
		if (nameStr == null) {
			String  errorText = "Name value string passed to HDLmDatabaseRow is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the row ID numeric value passed by the caller is null */
		/*
		if (idValue == null) {
			String  errorText = "Id value string passed to HDLmDatabaseRow is null";
			throw new NullPointerException(errorText);
		}	
		*/
		/* Check if the row ID numeric value passed by the caller is invalid */
		if (idValue <= 0) {
			String  errorText = "Id value passed to HDLmDatabaseRow is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Check if the company name string value passed by the caller is null */
		if (companyStr == null && companyStr != null) {
			String  errorText = "Company value string passed to HDLmDatabaseRow is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the report name string value passed by the caller is null */
		if (reportStr == null && reportStr != null) {
			String  errorText = "Report value string passed to HDLmDatabaseRow is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few fields in the new instance */
		info = infoStr;
		content = contentStr;
		name = nameStr;
		id = ((Integer) idValue).toString();
		company = companyStr;
		report = reportStr;
  }	
	/* This class can be instantiated using the
     constructor below in some cases */
	protected HDLmDatabaseRow() {
	}	
	/* The info value is specified using the field below */ 
	private String    info = null;
	/* The content value is specified using the field below */ 
	private String    content = null;
	/* The name value is specified using the field below */ 
	private String    name = null;
	/* The id value (always unique) is specified using the field below */
	private String    id;  
	/* The company value is specified using the field below */ 
	private String    company = null;
	/* The report value is specified using the field below */ 
	private String    report = null;
	/* The next field contains the info field organized as 
	   a tree instance, not a string */ 	
	private HDLmTree  HDLmTreeInfo = null;
	/* Get the company value and return it to the caller */
	protected String       getCompany() {
	  return this.company;
	}
	/* Get the content value and return it to the caller */
	protected String       getContent() {
	  return this.content;
	}
	/* Get the id value and return it to the caller */
	protected String       getId() {
	  return this.id;
	}
	/* Get the info value and return it to the caller */
	protected String       getInfo() {
	  return this.info;
	}
	/* Get the name value and return it to the caller */
	protected String       getName() {
	  return this.name;
	}
	/* Get the report value and return it to the caller */
	protected String       getReport() {
	  return this.report;
	}
	/* Set the company value */
	protected void         setCompany(final String newValue) {
		/* Check if the company string value passed by the caller is null */
		if (newValue == null) {
			String  errorText = "Company value string passed to setCompany is null";
			throw new NullPointerException(errorText);
		}
	  this.company = newValue;
	}
	/* Set the content value */
	protected void         setContent(final String newValue) {
		/* Check if the content string value passed by the caller is null */
		if (newValue == null) {
			String  errorText = "Content value string passed to setContent is null";
			throw new NullPointerException(errorText);
		}
	  this.content = newValue;
	}
	/* Set the instance form of the info to a value passed by
     the caller */
  protected void         setHDLmTreeInfo(final HDLmTree newValue) {
	  this.HDLmTreeInfo = newValue;
  }
	/* Set the ID value */
	protected void         setId(final String newValue) {
		/* Check if the ID string value passed by the caller is null */
		if (newValue == null) {
			String  errorText = "Id value string passed to setId is null";
			throw new NullPointerException(errorText);
		}
	  this.id = newValue;
	}
	/* Set the info value */
	protected void         setInfo(final String newValue) {
		/* Check if the info string value passed by the caller is null */
		if (newValue == null) {
			String  errorText = "Info value string passed to setInfo is null";
			throw new NullPointerException(errorText);
		}
	  this.info = newValue;
	}
	/* Set the info to a null value */
	protected void         setInfoNull() {
		this.info = null;
	}
	/* Set the name value */
	protected void         setName(final String newValue) {
		/* Check if the name string value passed by the caller is null */
		if (newValue == null) {
			String  errorText = "Name value string passed to setName is null";
			throw new NullPointerException(errorText);
		}
	  this.name = newValue;
	}
	/* Set the report value */
	protected void         setReport(final String newValue) {
		/* Check if the report string value passed by the caller is null */
		if (newValue == null) {
			String  errorText = "Report value string passed to setReport is null";
			throw new NullPointerException(errorText);
		}
	  this.report = newValue;
	}	
}
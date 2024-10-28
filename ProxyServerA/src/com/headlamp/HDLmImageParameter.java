package com.headlamp;

/**
 * HDLmImageParameter short summary.
 *
 * HDLmImageParameter description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmImageParameter {
	/* An instance of this class is created for each parameter.
	   The number of parameters can be zero or more than zero. */ 
	private String  parmName = null;
	private String  parmValue = null;
	/* Get the parameter name (if any) and return it to the caller */
	protected String getParameterName() {
		return parmName;
	}
	/* Get the parameter value and return it to the caller */
	protected String getParameterValue() {
		return parmValue;
	}
	/* Set or reset the parameter name. Note that the caller can 
     not pass a null value for the new parameter name. This is an
     an error condition. */
	protected void setParameterName(String newParmName) {
		if (newParmName == null) {
			String  errorText = "New parameter name string is null";
			throw new NullPointerException(errorText);
		}
		parmName = newParmName;
	}	
	/* Set or reset the parameter value. Note that the caller can 
     not pass a null value for the new parameter value. This is an
     an error condition. */
  protected void setParameterValue(String newParmValue) {
		if (newParmValue == null) {
			String  errorText = "New parameter value string is null";
			throw new NullPointerException(errorText);
		}
		parmValue = newParmValue;
	}	
}
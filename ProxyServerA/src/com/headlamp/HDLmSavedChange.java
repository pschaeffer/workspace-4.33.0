package com.headlamp;
/**
 * HDLmSavedChange short summary.
 *
 * HDLmSavedChange description.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmSavedChange {
	/* All instances of the HDLmSavedChange class have a standard set of fields */	 
	/* Parameter number is handled as a reference so that it can be set
	   to a null value. If the value is actually set, it must be a positive
	   integer or zero (zero is allowed). */
	private Integer        parameterNumber = null;
	private Double         parameterValue = null;
	private String         modName = null;
	private String         modPathValue = null;
	private HDLmModTypes   modType = HDLmModTypes.NONE;
	private String         oldValue = null;
	private String         newValue = null;
	/* This is one of the constructors for this class. The caller does
	   not pass any values. An empty instance is created. The caller 
	   must set any needed values in the instance. */ 
	protected HDLmSavedChange() {}
	/* This is one of the constructors for this class. The caller passes
	   all of the values that should be stored in the instance. */
	protected HDLmSavedChange(Integer newParameterNumber, Double newParameterValue, 
			                      String newModName, String newModPathValue, HDLmModTypes newModType,
			                      String newOldValue, String newNewValue) {
		/* Store each of the values passed to the constructor. All of the values can
		   be null, so we can not check for nulls here. */
		this.parameterNumber = newParameterNumber;
		this.parameterValue = newParameterValue;
		this.modName = newModName;
		this.modPathValue = newModPathValue;
		this.modType = newModType;
		this.oldValue = newOldValue;
		this.newValue = newNewValue;
	}
  /* Get the modification name for a saved change. This value may 
     be a null reference */ 
	protected String getModName() {
		return this.modName; 
	}
  /* Get the modification path value for a saved change. This value may 
     be a null reference */ 
	protected String getModPathValue() {
	 return this.modPathValue; 
	}
  /* Get the modification type for a saved change. This value may 
     be a null reference */ 
  protected HDLmModTypes getModType() {
    return this.modType; 
  }
  /* Get the new value for a saved change. This value may 
     be a null reference */ 
	protected String getNewValue() {
	  return this.newValue; 
	}
  /* Get the old value for a saved change. This value may 
     be a null reference */ 
  protected String getOldValue() {
    return this.oldValue; 
  }
  /* Get the parameter number for a saved change. This value may 
     be a null reference */ 
  protected Integer getParameterNumber() {
    return this.parameterNumber; 
  }
  /* Get the parameter value for a saved change. This value may 
     be a null reference */ 
  protected Double getParameterValue() {
    return this.parameterValue; 
  }
  /* Set the modification name for a saved change. This value may 
     be a null reference */ 
  protected void setModName(String newModName) {
  	this.modName = newModName; 
  }
  /* Set the modification path value for a saved change. 
     This value may be a null reference */ 
  protected void setModPathValue(String newModPathValue) {
    this.modPathValue = newModPathValue;
  }
  /* Set the modification type value for a saved change */ 
  protected void setModType(HDLmModTypes newModType) {
    this.modType = newModType; 
  }
  /* Set the new value for a saved change. This value may 
     be a null reference */ 
  protected void setNewValue(String newNewValue) {
  	this.newValue = newNewValue;
  }
  /* Set the old value for a saved change. This value may 
     be a null reference */ 
	protected void setOldValue(String newOldValue) {
		this.oldValue = newOldValue;
	}
  /* Set the parameter number for a saved change. This value may 
     be a null reference */ 
  protected void setParameterNumber(Integer newParameterNumber) {
  	this.parameterNumber = newParameterNumber;
  }
  /* Set the parameter value for a saved change. This value may 
     be a null reference */ 
  protected void setParameterValue(Double newParameterValue) {
  	this.parameterValue = newParameterValue;
  }
}
package com.headlamp;
/**
 * HDLmUtilityResponse short summary.
 *
 * HDLmUtilityResponse description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmUtilityResponse {
	/* An instance of this class is created to return the results
	   of a utility request passed by the caller. Of course, not all
	   of the fields will always be set. However, all of the fields
	   may be set in some cases. */
	private Integer   exitCode = null;
	private String    executeMessage = null;
	private Boolean   foundInCache = null;
	private String    operationType = null;
	private String    IOExceptionMessage = null;
	/* This is a time stamp value, not a local time */
	private String    lastTimeValue = null;
	private String    passwordValue = null;
	private Boolean   pHashActuallyCalculated = null;
	private String    pHashValue = null;
	private String    queryTarget = null;
	private String    queryValue = null;
	private String    scopeValue = null;
	private String    stdErr = null;
	private String    stdOut = null;
	private String    tableName = null;
	private Boolean   updateCache = null;
	private String    userNameValue = null;
	private String    urlStr = null;
	/* Get a few values */
	protected Integer      getExitCode() {
		return exitCode;
	}
	protected String       getExecuteMessage() {
		return executeMessage;
	}
	protected Boolean      getFoundInCache() {
		return foundInCache;
	}
	protected String       getIOExceptionMessage() {
		return IOExceptionMessage;
	}
	/* This routine returns a time stamp value, not 
	   a local time */ 
	protected String       getLastTimeValue() {
		return lastTimeValue;
	}
	protected String       getOperationType() {
		return operationType;
	}
	protected String       getPasswordValue() {
		return passwordValue;
	}
	protected Boolean      getPHashActuallyCalculated() {
		return pHashActuallyCalculated;
	}
	protected String       getPHashValue() {
		return pHashValue;
	}
	protected String       getQueryTarget() {
		return queryTarget;
	}
	protected String       getQueryValue() {
		return queryValue;
	}
	protected String       getScopeValue() {
		return scopeValue;
	}
	protected String       getStdErr() {
		return stdErr;
	}
	protected String       getStdOut() {
		return stdOut;
	}
	protected String       getTableName() {
		return tableName;
	}
	protected Boolean      getUpdateCache() {
		return updateCache;
	}
	protected String       getUrlStr() {
		return urlStr;
	}
	protected String       getUserNameValue() {
		return userNameValue;
	}
	/* Set or reset the exit code */
	protected void setExitCode(final Integer newValue) {
		exitCode = newValue;
	}
	/* Set or reset the ExecuteException message */
	protected void setExecuteMessage(final String newValue) {
		executeMessage = newValue;
	}
	/* Set or reset the foundInCache Boolean */
	protected void setFoundInCache(final Boolean newValue) {
		foundInCache = newValue;
	}
	/* Set or reset the IOException message */
	protected void setIOExceptionMessage(final String newValue) {
		IOExceptionMessage = newValue;
	}
	/* Set or reset the last time string. Note that the caller can 
     not pass a null value for the new last time string. This is 
     an error condition. The last time value is a GMT time stamp,
     not a local time. */
	protected void setLastTimeValue(String newLastTimeValue) {
		if (newLastTimeValue == null) {
			String  errorText = "New user name value is null in setLastTimeValue";
			throw new NullPointerException(errorText);
		}
		lastTimeValue = newLastTimeValue;
	}
	/* Set or reset the operation type string value */
	protected void setOperationType(final String newValue) {
		operationType = newValue;
	}
	/* Set or reset the password string. Note that the caller can 
     not pass a null value for the new password string. This is 
     an error condition. */
  protected void setPasswordValue(String newPasswordValue) {
	  if (newPasswordValue == null) {
		  String  errorText = "New password value is null in setPasswordValue";
		  throw new NullPointerException(errorText);
	  }
	  passwordValue = newPasswordValue;
  }
	/* Set or reset the pHashActuallyCalculated Boolean */
	protected void setPHashActuallyCalculated(final Boolean newValue) {
		pHashActuallyCalculated = newValue;
	}
	/* Set or reset the pHash string value */
	protected void setPHashValue(final String newValue) {
		pHashValue = newValue;
	}
	/* Set or reset the query target string value */
	protected void setQueryTarget(final String newValue) {
		queryTarget = newValue;
	}
	/* Set or reset the query value string value */
	protected void setQueryValue(final String newValue) {
		queryValue = newValue;
	}
	/* Set or reset the scope string. Note that the caller can 
	   not pass a null value for the new scope string. This is 
	   an error condition. */
	protected void setScopeValue(String newScopeValue) {
		if (newScopeValue == null) {
			String  errorText = "New scope value is null in setScopeValue";
			throw new NullPointerException(errorText);
		}
	  scopeValue = newScopeValue;
	}
	/* Set or reset the user name string. Note that the caller can 
	   not pass a null value for the new user name string. This is 
	   an error condition. */
	protected void setUserNameValue(String newUsernameValue) {
		if (newUsernameValue == null) {
			String  errorText = "New user name value is null in setUserNameValue";
			throw new NullPointerException(errorText);
		}
		userNameValue = newUsernameValue;
	}
	/* Set or reset the standard error string value */
	protected void setStdErr(final String newValue) {
		stdErr = newValue;
	}
	/* Set or reset the standard output string value */
	protected void setStdOut(final String newValue) {
		stdOut = newValue;
	}	
	/* Set or reset the table name string value */
	protected void setTableName(final String newValue) {
		tableName = newValue;
	}	
	/* Set or reset the updateCache Boolean */
	protected void setUpdateCache(final Boolean newValue) {
		updateCache = newValue;
	}
	/* Set or reset the standard output URL string value */
	protected void setUrlStr(final String newValue) {
		urlStr = newValue;
	}
}
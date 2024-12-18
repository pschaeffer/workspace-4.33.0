package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmSecurity short summary.
 *
 * HDLmSecurity description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmSecurity {
	/* The next statement initializes logging to some degree. Note that having the
     slf4j jars and the log4j jars in the classpath also plays some role in
     logging initialization.	 */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmSecurity.class);
	/* This class can never be instantiated */
	private HDLmSecurity() {}
	/* Check if the scope string lets the users have access or not */
	protected static boolean  checkIfAllowed(final ArrayList<ArrayList<String>> scopeArray, 
			                                     final ArrayList<String> nodePath) {
		/* Check if the scope array passed by the caller is null */
		if (scopeArray == null) {
			String  errorText = "Scope array reference passed to checkIfAllowed is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the node path array passed by the caller is valid or not */
		if (nodePath == null) {
			String  errorText = "Node path array reference passed to checkIfAllowed is null";
			throw new NullPointerException(errorText);
		}
		/* Get and check the length of the node path array */
		int  nodePathLen = nodePath.size();
		if (nodePathLen <= 0) {
    	String   errorFormat = "Node path length (%s) is invalid in checkIfAllowed";
			String   errorText = String.format(errorFormat, nodePathLen);
			HDLmAssertAction(false, errorText);		    	
    }
		/* Make sure the scope vector has at least one entry */
		int   scopeArrayLen = scopeArray.size(); 
		if (scopeArrayLen == 0) { 
			String   errorText = "Scope vector has no entries";
			HDLmAssertAction(false, errorText);				
		}
		ArrayList<String>  scopeArrayEntry = scopeArray.get(0);
		/* Make sure the scope vector entry has at least one entry */
		int   scopeArrayEntryLen = scopeArrayEntry.size();
		if (scopeArrayEntryLen == 0) { 
			String   errorText = "Scope vector entry, has no entries";
			HDLmAssertAction(false, errorText);				
		}
		String  scopeArrayEntryEntry = scopeArrayEntry.get(0);
		/* Declare and define a few variables */
		boolean   accessAllowed = false;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
	    /* Check for an attempt to access the 'Top' node. This is always OK. */
			if (nodePathLen == 1) {
				accessAllowed = true;
				break;				
			}
	    /* If the current user has a special scope, then access
	       is always allowed. Note that 'admin' and '*' (without
	       the quotes) provide access to everything. */
	    if (scopeArrayEntryEntry.equals("admin") ||
	    		scopeArrayEntryEntry.equals("*")) {
				accessAllowed = true;
				break;	    	
	    }
	    String  nodeName;
	    /* Check if the user is trying to access the nodes under
	       the top node */
	    if (nodePathLen == 2) {
	      nodeName = nodePath.get(1);
	      if (!nodeName.equals("Companies")) {
	        accessAllowed = false;
	        break;
	      }
	      else {
					accessAllowed = true;
					break;
	      }
	    }
	    /* Check if the user is trying to access the nodes under
	       the companies node */
	    if (nodePathLen > 2) {
	    	accessAllowed = true;
	      /* Check each part of the scope to see if the user is
	         allowed to access a node */
	      for (int i = 2; i < nodePathLen; i++) {
	        nodeName = nodePath.get(i);
	        String  nodeNameLower = nodeName.toLowerCase();
	        if ((i-2) < scopeArrayLen) {
	        	/* Check if the current scope vector entry provides access
	        	   to the current node path entry. We check all of the entries
	        	   in the current scope vector entry to see if any of them 
	        	   provide access to the node path entry. */         	
	          ArrayList<String>  curScopeArrayEntry = scopeArray.get(i-2);
	          int   curScopeArrayEntryLen = curScopeArrayEntry.size();
	          boolean   nodePathEntryAccessAllowed = false;
	          for (int j = 0; j < curScopeArrayEntryLen; j++) {
	          	String  curScopeStr = curScopeArrayEntry.get(j);
		          /* Check for a special scope value */
		          if (curScopeStr.equals("*")) {
		          	nodePathEntryAccessAllowed = true;
		            break;
		          }
		          if (nodeNameLower.indexOf(curScopeStr) >= 0) {
		          	nodePathEntryAccessAllowed = true;
		            break;
		          }	          	
	          }
	          /* If we don't have access to the current node path entry,
	             then we don't have access overall. Check for this case. */
	          if (nodePathEntryAccessAllowed == false) {
	          	accessAllowed = false;
	          	break;
	          }	          	
	        }
	      }
	    }
	    break;
		}	
	  return accessAllowed;
	}
	/* Check if we cam use the values in the memory database. The values
	   can be used if they are not too old. If they are too old, they
	   can not be used. */
	protected static boolean  checkLastTimeFailure(final String userNameStr,
			                                           HDLmUtilityResponse outputArea) {
		/* Check if the user name string passed by the caller is null */
		if (userNameStr == null) {
			String  errorText = "User name string passed to checkLastTimeFailure is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the output area passed by the caller is null */
		/* This value can be null in some cases. The check below 
		   is commented out for that reason. */ 
		/*
		if (outputArea == null) {
			String  errorText = "Output area passed to checkLastTimeFailure is null";
			throw new NullPointerException(errorText);
		}
		*/
		/* Get some information using the user name string */
		/* LOG.info("In checkLastTimeFailure - userNameStr - " + userNameStr); */
		/*
		if (outputArea == null)
			LOG.info("In checkLastTimeFailure - outputArea is null");
		*/
		boolean   lastTimeFailure = true;
		String    lastTimeStr = null;
		/* Check if an output area was passed to this routine, if not
		   get an output area from the memory database */
		if (outputArea == null)
	    outputArea = HDLmSecurity.getInformation(userNameStr);
		/* Try to get the last time string from the output area */
		if (outputArea != null) 
	    lastTimeStr = outputArea.getLastTimeValue();
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {	
			/* Check if we were able to get a last time string */
			if (lastTimeStr == null)
				break;
			/* Check how long it has been since last time value was set/reset */
			Instant   instantNow = Instant.now(); 
      Instant   instantLastTime = Instant.parse(lastTimeStr);
      Duration  duration = Duration.between(instantLastTime, instantNow);
      long      durationSeconds = duration.getSeconds();
      /* Check if duration shows that the last time is still valid */
			int       validForSeconds = HDLmConfigInfo.getScopeValidForSeconds();
			if (durationSeconds <= (long) validForSeconds)
				lastTimeFailure = false; 
			break;
		}
		return lastTimeFailure;
	}
	/* Check if the user name and password match the contents of the 
	   memory database. This may or may not be true. Note that if the
	   user name is not found in the memory database, then a no match
	   value is returned to the caller. */ 
	protected static boolean  checkMatchingUsernamePassword(final String userNameStr, 
			                                                    final String passedPasswordStr,
			                                                    final boolean traceCheck) {
		/* Check if the user name string passed by the caller is null */
		if (userNameStr == null) {
			String  errorText = "User name string passed to checkMatchingUsernamePassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the passed password string passed by the caller is null */
		if (passedPasswordStr == null) {
			String  errorText = "Password string passed to checkMatchingUsernamePassword is null";
			throw new NullPointerException(errorText);
		}
	  /* Declare and define a few values */
	  boolean   matchOk = false;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
		  /* Build the JSON strings used as the key and value */
		  String  jsonKeyStr = "{\"Type\":\"Username\",\"Value\":\"" + userNameStr + "\"}"; 
		  if (traceCheck) {
		  	LOG.info("In HDLmSecurity.checkMatchingUsernamePassword - jsonKeyStr");
		  	LOG.info(jsonKeyStr);
		  }
		  String  jsonValueStr = HDLmMemoryStorage.get(jsonKeyStr);
		  if (traceCheck) {
		  	LOG.info("In HDLmSecurity.checkMatchingUsernamePassword - jsonValueStr");
		  	LOG.info(jsonValueStr);
		  }
		  /* The map might not contain the user name string */
		  if (jsonValueStr == null)
			  break;  
			/* Create a new JSON parser for use below */
	    JsonParser    parser = new JsonParser();  
	    /* Make sure the JSON value string has the required key */
	    JsonElement   jsonElement = parser.parse(jsonValueStr);
		  /* Check if the JSON message passed by the caller is valid */
			if (!jsonElement.isJsonObject()) {
		 	  String  errorText = "JSON string in checkMatchingUsernamePassword for check match is invalid";
		 	  HDLmAssertAction(false, errorText);
		  }
			/* The code below isn't really used at this time. Instead, we rely on
			   not getting anything from the memory database or an entry that does
			   not have a password */
		  /*
	    boolean   hasPasswordKey = HDLmJson.hasJsonKey(jsonElement, "Password");
			if (hasPasswordKey == false) {
				String  errorText = "Memory database entry JSON data does not have Password key";
				HDLmAssertAction(false, errorText);	
			}
			*/
	    /* Get the password from the JSON from the memory database. If we don't
	       get the password, then we just indicate a mismatch. */		
	    String  memoryPasswordStr = HDLmJson.getJsonString(jsonElement, "Password");
	    if (memoryPasswordStr == null)
	    	break;
		  if (traceCheck) {
		  	LOG.info("In HDLmSecurity.checkMatchingUsernamePassword - memoryPasswordStr");
		  	LOG.info(memoryPasswordStr);
		  }
	    /* Check if the password values match */
	    if (memoryPasswordStr.equals(passedPasswordStr) == false)
	    	break;
	    /* Show that the passwords match */
	    matchOk = true;
	    break;
		}
	  return matchOk;
	}
	/* Check if a user name exist or not. If a user name exists (in a
	   user pool) it will have attributes. If it does not, then asking
	   for the attributes will produce an error message. This code 
	   returns a boolean showing if a user name exists or not. */  
	protected static boolean  checkUsernameExists(final String userName) {
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to checkUsernameExists is null";
			throw new NullPointerException(errorText);
		}
		/* Get some values needed to invoke the AWS Cognito API */
		String  AWSCognitoHostName = HDLmConfigInfo.getAwsCognitoHost(); 
		String  userPoolId = HDLmConfigInfo.getAwsCognitoUserPoolId();
		String  apiJson = HDLmSecurity.getJsonAdminGetUser(userPoolId, userName);
		int     apiJsonLen = apiJson.length();
		ArrayList<String>   apiHeaders = HDLmSecurity.getHeadersAdminGetUser(AWSCognitoHostName,
				                                                                 userPoolId,
				                                                                 userName,
				                                                                 apiJsonLen);
		/* Invoke the Cognito API and return the string to the caller */
		boolean   userNameExists = false;
		HDLmApacheResponse  apiOutput;
		apiOutput = HDLmSecurity.invokeCognitoApi(apiHeaders, apiJson);
		/* Check the status code from the network call */
		int   outStatusCode = apiOutput.getStatusCode();
		if (outStatusCode == HttpStatus.OK_200)
			userNameExists = true;
		return userNameExists;
	}
	/* Check the user name and password that the user entered. The user
		 name and password might be right or they might be wrong. The 
		 actual checking is done remotely. This routine returns a string
		 that can be checked. The string shows if the user name and password
		 were correct or not. */
	protected static HDLmApacheResponse  checkUsernamePassword(final String userName, 
			                                                       final String passwordStr) {
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to checkUsernamePassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the password string passed by the caller is null */
		if (passwordStr == null) {
			String  errorText = "Password string passed to checkUsernamePassword is null";
			throw new NullPointerException(errorText);
		}
		/* Get some values needed to invoke the AWS Cognito API */
		String  AWSCognitoHostName = HDLmConfigInfo.getAwsCognitoHost(); 
		String  clientAppId = HDLmConfigInfo.getAwsCognitoUserPoolClientAppId();
		String  apiJson = HDLmSecurity.getJsonInitiateAuth(clientAppId, userName, passwordStr);
		int     apiJsonLen = apiJson.length();
		ArrayList<String>   apiHeaders = HDLmSecurity.getHeadersInitiateAuth(AWSCognitoHostName, apiJsonLen);
		HDLmApacheResponse  apiOutput;
		/* Invoke the Cognito API and return the string to the caller */
		apiOutput = HDLmSecurity.invokeCognitoApi(apiHeaders, apiJson);
		return apiOutput;
  }
	/* Check the verification code that the user entered. The user
	   verification code might be right or it might be wrong. The
	   actual checking is done remotely. This routine returns a string
	   that can be checked. The response text from the string
	   shows if the verification code were correct or not. */
	protected static HDLmApacheResponse  checkVerificationCode(final String challengeName, 
																														 final String userName, 
																														 final String verificationCode, 
																														 final String sessionStr) {
		/* Check if the challenge name string passed by the caller is null */
		if (challengeName == null) {
			String  errorText = "User name string passed to checkVerificationCode is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to checkVerificationCode is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the verification code string passed by the caller is null */
		if (verificationCode == null) {
			String  errorText = "User name string passed to checkVerificationCode is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session string passed by the caller is null */
		if (sessionStr == null) {
			String  errorText = "User name string passed to checkVerificationCode is null";
			throw new NullPointerException(errorText);
		}
		/* Get some values needed to invoke the AWS Cognito API */
		String  AWSCognitoHostName = HDLmConfigInfo.getAwsCognitoHost(); 
		String  clientAppId = HDLmConfigInfo.getAwsCognitoUserPoolClientAppId();
		String  apiJson = HDLmSecurity.getJsonRespondToChallenge(clientAppId, 
			                                                      challengeName, 
			                                                      userName, 
			                                                      verificationCode, 
			                                                      sessionStr);
		int   apiJsonLen = apiJson.length();
		ArrayList<String>   apiHeaders = HDLmSecurity.getHeadersRespondToChallenge(AWSCognitoHostName, 
			                                                                         apiJsonLen);
		HDLmApacheResponse  apiOutput;
		/* Invoke the Cognito API and return the output string to the caller */
		apiOutput = HDLmSecurity.invokeCognitoApi(apiHeaders, apiJson);
		return apiOutput;
  }
	/* This routine converts a scope string to a scope vector. Scope strings
	   have things like parenthesis in them, that need to removed and taken
	   into account. The string 'admin' is changed into '*' (both, without
	   the quotes) as need be. */
	protected static ArrayList<ArrayList<String>>  convertScopeString(final String scopeStr) {
		/* Check if the scope string passed by the caller is null */
		if (scopeStr == null) {
			String  errorText = "Scope string passed to convertScopeString is null";
			throw new NullPointerException(errorText);
		}
		/* Show what was passed to this routine */
		/* LOG.info("In convertScopeString scopeStr - " + scopeStr); */
		/* Create some values for use later */   
		HDLmScopeParseTypes           parseState = HDLmScopeParseTypes.INITIAL;
		int                           parenCount = 0;
		int                           vectorCount = 0;
		ArrayList<String>             outEntry = new ArrayList<String>();
		ArrayList<ArrayList<String>>  outVector = new ArrayList<ArrayList<String>>();
		/* Break the scope string down into a set of tokens */
		ArrayList<HDLmToken>   tokens = HDLmString.getTokens(scopeStr);
		int                    tokenCount = tokens.size();
		/* Get rid of the sentinel token, if possible */
		if (tokenCount > 0)
			tokenCount--;
		/* Process all of the tokens */
		for (int i = 0; i < tokenCount; i++) {
			/* Get the current token */ 
			HDLmToken       curToken = tokens.get(i);
			String          tokenValue = curToken.getValue();
			HDLmTokenTypes  tokenType = curToken.getType();
			int             tokenIndex = curToken.getIndex();
			/* Make some changes, if need be. In one very specific 
			   case, we change an identifier token into an operator
			   token. Note that the resulting operator token is used
			   as an identifier token later. */ 
			if (vectorCount == 0 &&
					tokenValue.equals("admin")) {
				tokenType = HDLmTokenTypes.OPERATOR;
				tokenValue = "*";
			}
			/* Check the current type of token */
			switch (tokenType) {
			  /* Check for an handle a space token */
			  case SPACE: {
			  	/* Check if a space token is allowed here */
			  	boolean  spaceOk = false;
			  	if (parseState == HDLmScopeParseTypes.INITIAL           ||
			  			parseState == HDLmScopeParseTypes.AFTERENTRY        ||
			  		  parseState == HDLmScopeParseTypes.AFTERLPAREN       ||
			  		  parseState == HDLmScopeParseTypes.AFTERIDENINPARENS ||
			  			parseState == HDLmScopeParseTypes.AFTERCOMMAINPARENS)		  		
			  		spaceOk = true;
			  	/* Check if one or more spaces are allowed at this point */
			  	if (spaceOk == false) {
			    	String  errorFormat = "Space token not allowed at position (%d)";
						String  errorText = String.format(errorFormat, tokenIndex+1);
						HDLmAssertAction(false, errorText); 
			  	}
			  	/* The parsing state will change at this point */
			  	if (parenCount > 0) {
			  		if (parseState == HDLmScopeParseTypes.AFTERLPAREN)
			  			parseState = HDLmScopeParseTypes.AFTERFIRSTSPACEINPARENS;
			  		else if (parseState == HDLmScopeParseTypes.AFTERIDENINPARENS)
			  			parseState = HDLmScopeParseTypes.AFTERLATERSPACEINPARENS;
			  		else if (parseState == HDLmScopeParseTypes.AFTERCOMMAINPARENS)
			  			parseState = HDLmScopeParseTypes.AFTERCOMMASPACEINPARENS;
			  	}
			  	else
			  		if (parseState == HDLmScopeParseTypes.INITIAL)
			  			parseState = HDLmScopeParseTypes.BEFOREENTRY;
			  		else if (parseState == HDLmScopeParseTypes.AFTERENTRY)
			  			parseState = HDLmScopeParseTypes.BEFOREENTRY;
			  	break;
			  }	
			  /* Check for and handle an operator token */
			  case OPERATOR: { 
			  	/* Check for a left parenthesis */
					if (tokenValue.equals("(")) {
						/* Check the current state. Check if a left parenthesis is allowed here. */
						if (parseState != HDLmScopeParseTypes.INITIAL && 
							  parseState != HDLmScopeParseTypes.BEFOREENTRY) {
				    	String  errorFormat = "Invalid parse state (%s), left parenthesis found in invalid location";
							String  errorText = String.format(errorFormat, parseState.toString());
							HDLmAssertAction(false, errorText); 
						}
						/* Handle a left parenthesis */
						parenCount++;
						parseState = HDLmScopeParseTypes.AFTERLPAREN;
					}
					/* Check for a right parenthesis */
					else if (tokenValue.equals(")")) {
						/* Check the current state. Check if a right parenthesis is allowed here. */
						if (parseState != HDLmScopeParseTypes.AFTERIDENINPARENS &&
								parseState != HDLmScopeParseTypes.AFTERLATERSPACEINPARENS) {
				    	String  errorFormat = "Invalid parse state (%s), right parenthesis found in invalid location";
							String  errorText = String.format(errorFormat, parseState.toString());
							HDLmAssertAction(false, errorText); 
						}
						/* Handle a right parenthesis */
						parenCount--;
						ArrayList<String>   newEntry = new ArrayList<String>(outEntry);
						vectorCount++;
						outVector.add(newEntry);
						outEntry.clear();
						parseState = HDLmScopeParseTypes.AFTERENTRY;
					}
					/* Check for a comma */
					else if (tokenValue.equals(",")) {
						/* Check the current state. Check if a comma is allowed here. */
						if (parseState != HDLmScopeParseTypes.AFTERIDENINPARENS &&
								parseState != HDLmScopeParseTypes.AFTERLATERSPACEINPARENS) {
				    	String  errorFormat = "Invalid parse state (%s), comma found in invalid location";
							String  errorText = String.format(errorFormat, parseState.toString());
							HDLmAssertAction(false, errorText); 
						}
						parseState = HDLmScopeParseTypes.AFTERCOMMAINPARENS;
					}
					/* We treat an asterisk as an identifier */
					else if (tokenValue.equals("*")) { 
						if (parenCount > 0) {
							/* Check the current state. Check if an asterisk is allowed here. */
							if (parseState != HDLmScopeParseTypes.AFTERLPAREN             &&
									parseState != HDLmScopeParseTypes.AFTERFIRSTSPACEINPARENS &&
							    parseState != HDLmScopeParseTypes.AFTERCOMMAINPARENS      &&
							    parseState != HDLmScopeParseTypes.AFTERCOMMASPACEINPARENS &&
									parseState != HDLmScopeParseTypes.AFTERLATERSPACEINPARENS) {
					    	String  errorFormat = "Invalid parse state (%s), asterisk found in invalid location";
								String  errorText = String.format(errorFormat, parseState.toString());
								HDLmAssertAction(false, errorText); 
							}
						  parseState = HDLmScopeParseTypes.AFTERIDENINPARENS;
						  outEntry.add(tokenValue);
						}
						else {
							/* Check the current state. Check if an asterisk is allowed here. */
							if (parseState != HDLmScopeParseTypes.INITIAL &&
									parseState != HDLmScopeParseTypes.BEFOREENTRY) {
					    	String  errorFormat = "Invalid parse state (%s), asterisk found in invalid location";
								String  errorText = String.format(errorFormat, parseState.toString());
								HDLmAssertAction(false, errorText); 
							}
							parseState = HDLmScopeParseTypes.AFTERENTRY;		
							outEntry.add(tokenValue);
							ArrayList<String>   newEntry = new ArrayList<String>(outEntry);
							vectorCount++;
							outVector.add(newEntry);
							outEntry.clear();		
						}
					}
					/* All other operators are errors at this point */
					else {
			    	String  errorFormat = "Invalid operator token (%s) detected at position (%d)";
						String  errorText = String.format(errorFormat, tokenValue, tokenIndex+1);
						HDLmAssertAction(false, errorText); 
					}
					break;
			  }		
			  /* Check for and handle an identifier token */
			  case IDENTIFIER: { 				 
					if (parenCount > 0) {
						/* Check the current state. Check if an asterisk is allowed here. */
						if (parseState != HDLmScopeParseTypes.AFTERLPAREN             &&
						    parseState != HDLmScopeParseTypes.AFTERCOMMAINPARENS      &&
				   		  parseState != HDLmScopeParseTypes.AFTERCOMMASPACEINPARENS &&
								parseState != HDLmScopeParseTypes.AFTERFIRSTSPACEINPARENS &&
							  parseState != HDLmScopeParseTypes.AFTERLATERSPACEINPARENS) {
				    	String  errorFormat = "Invalid parse state (%s) asterisk found in invalid location";
							String  errorText = String.format(errorFormat, parseState.toString());
							HDLmAssertAction(false, errorText); 
						}
					  parseState = HDLmScopeParseTypes.AFTERIDENINPARENS;
					  outEntry.add(tokenValue);
					}
					else {
						/* Check the current state. Check if an asterisk is allowed here. */
						if (parseState != HDLmScopeParseTypes.INITIAL &&
								parseState != HDLmScopeParseTypes.BEFOREENTRY) {
				    	String  errorFormat = "Invalid parse state (%s) asterisk found in invalid location";
							String  errorText = String.format(errorFormat, parseState.toString());
							HDLmAssertAction(false, errorText); 
						}
						parseState = HDLmScopeParseTypes.AFTERENTRY;		
						outEntry.add(tokenValue);
						ArrayList<String>   newEntry = new ArrayList<String>(outEntry);
						vectorCount++;
						outVector.add(newEntry);
						outEntry.clear();						
					}										
					break;
			  }
			  /* All other types of tokens are not allowed */
			  default: {
		    	String  errorFormat = "Invalid token (%s) found at position (%d)"; 
					String  errorText = String.format(errorFormat, tokenValue, tokenIndex+1);
					HDLmAssertAction(false, errorText);
			  }
			}			
		}		
		/* At this point we have processed all of the tokens. The number of vector entries
		   must be in a certain range and parenthesis must be balanced. */		
		if (vectorCount < 1 || vectorCount > 3) {
	 	  String  errorFormat = "The number of scope vector entries (%d) is invalid"; 
			String  errorText = String.format(errorFormat, vectorCount);
			HDLmAssertAction(false, errorText);
		}
		if (parenCount != 0) {
	 	  String  errorFormat = "The final parenthesis count (%d) is not zero"; 
			String  errorText = String.format(errorFormat, parenCount);
			HDLmAssertAction(false, errorText);
		}
		/* Display the output vector */
		/* LOG.info("In convertScopeString outVector.size() - " + ((Integer) outVector.size()).toString()); */
		for (int i = 0; i < outVector.size(); i++) {
			/* LOG.info("In convertScopeString i - " + ((Integer) i).toString()); */
			/* LOG.info("In convertScopeString outVector entry - " + outVector.get(i)); */
		}
		return outVector;
	}
	/* Get some attibutes for the current user, using the Cognito network
		 API. These attributes are obtained by making a network API call. 
		 This routine is not really used. The needed attributes are really
		 obtained from the ID token that comes back from the verification 
		 call. */
	protected static HDLmApacheResponse  getAttributes(final String accessToken) {
		/* Check if the access token passed by the caller is null */
		if (accessToken == null) {
			String  errorText = "Access token string passed to getAttributes is null";
			throw new NullPointerException(errorText);
		}
    /* Get some values needed to invoke the AWS Cognito API */
    String  AWSCognitoHostName = HDLmConfigInfo.getAwsCognitoHost();
    String  apiJson = HDLmSecurity.getJsonGetAttributes(accessToken);
    int     apiJsonLen = apiJson.length();
    ArrayList<String>   apiHeaders = getHeadersGetAttributes(AWSCognitoHostName, apiJsonLen);
    HDLmApacheResponse  apiOutput;
    /* Invoke the Cognito API and return the output string to the caller */
    apiOutput = HDLmSecurity.invokeCognitoApi(apiHeaders, apiJson);
    return apiOutput;
  }
	/* Get an authorization value and return it to the caller. The
	   Authorization value includes a signature that takes some 
	   work to calculate. An external routine is used for this 
	   purpose. */
  protected static String  getAuthorizationValue(final String dateValue, 
  		                                           final String payloadValue,
  		                                           final String targetValue) {
		/* Check if the date value string passed by the caller is null */
		if (dateValue == null) {
			String  errorText = "Date value string passed to getAuthorizationValue is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the payload value string passed by the caller is null */
		if (payloadValue == null) {
			String  errorText = "Payload value string passed to getAuthorizationValue is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the Amazon target value string passed by the caller is null */
		if (targetValue == null) {
			String  errorText = "Amazon target value string passed to getAuthorizationValue is null";
			throw new NullPointerException(errorText);
		}
  	/* Set a few values for use later */
		String  accessKeyID = HDLmConfigInfo.getAccessKeyId();
		String  secretAccessKey = HDLmConfigInfo.getSecretAccessKey(); 
		String  regionName = HDLmConfigInfo.getAwsCognitoUserPoolRegion();
		String  serviceName = HDLmConfigInfo.getAwsCognitoServiceName();
		String  httpMethod = HDLmConfigInfo.getAwsCognitoHttpMethod();
		String  canonicalUri = HDLmConfigInfo.getAwsCognitoCanonicalUri();
    LinkedHashMap<String, String>  queryParameters = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String>  awsHeaders = new LinkedHashMap<String, String>();
    String  payload = payloadValue;
    String  xAmzTarget = targetValue;
    String  contentType = HDLmConfigInfo.getAwsCognitoContentType();
    String  hostName = HDLmConfigInfo.getAwsCognitoHost();
		/* Create a Builder object for use below */
		HDLmSignature.Builder   builderObj = new HDLmSignature.Builder(accessKeyID, secretAccessKey);
		/* Set a few fields in the Builder object */
		builderObj.regionName(regionName);
		builderObj.serviceName(serviceName);
		builderObj.httpMethodName(httpMethod);
		builderObj.canonicalUri(canonicalUri);
		builderObj.queryParameters(queryParameters);
		builderObj.awsHeaders(awsHeaders);
		builderObj.payload(payload);
		builderObj.xAmzTarget(xAmzTarget);
		builderObj.contentType(contentType);
		builderObj.hostName(hostName);
		builderObj.dateTime(dateValue);
		/* Get an HDLmSignature instance */
		HDLmSignature   hdlmSignature = builderObj.build();
		/* Use the HDLmSignature instance */
		Map<String, String>   headers = null;
		try {
			headers = hdlmSignature.getHeaders();
		} 
		catch (Exception exception) {
    	if (payloadValue != null)
    		LOG.info("Payload value - " + payloadValue);
		  LOG.info("Exception while executing getAuthorizationValue");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
		  return null;
		}
		return headers.get("Authorization"); 
  }
	/* Get a set of headers for an admin get user request.
	   The headers are built and returned as an array list. */
	protected static ArrayList<String>  getHeadersAdminGetUser(final String hostNameStr, 
			                                                       final String userPoolId,
			                                                       final String userNameStr,
			                                                       final int contentLength) {
		/* Check if the host name string passed by the caller is null */
		if (hostNameStr == null) {
			String  errorText = "Host name string passed to getHeadersAdminGetUser is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user pool ID string passed by the caller is null */
		if (userPoolId == null) {
			String  errorText = "User pool ID string passed to getHeadersAdminGetUser is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user name string passed by the caller is null */
		if (userNameStr == null) {
			String  errorText = "User name string passed to getHeadersAdminGetUser is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content length passed by the caller is valid */
		if (contentLength <= 0) {
			String  errorText = "Content length passed to getHeadersAdminGetUser is invalid";
			throw new IllegalArgumentException(errorText);
		}
 	  /* Build the standard headers */
	  ArrayList<String>   standardHeaders = HDLmSecurity.getHeadersStandard(hostNameStr, contentLength);
	  /* Create an headers Array List with the standard headers in it */
	  ArrayList<String>   headersArrayList = new ArrayList<String>(standardHeaders);
	  String  targetHeader = null;
	  /* Build a X-Amz-Date header and add it to the headers array list */
	  String  dateValue = HDLmSecurity.getTimeStamp();
	  targetHeader = HDLmHtml.buildXAmzDateHeader(dateValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Build a X-Amz-Target header and add it to the headers array list */
	  String  targetValue = HDLmConfigInfo.getAwsCognitoApiAdminGetUser();
	  targetHeader = HDLmHtml.buildXAmzTargetHeader(targetValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Build an Authorization header and add it to the headers array list */  
	  String  jsonStr = HDLmSecurity.getJsonAdminGetUser(userPoolId, userNameStr);
	  String  xAmzTarget = HDLmConfigInfo.getAwsCognitoApiAdminGetUser();
	  String  authValue = HDLmSecurity.getAuthorizationValue(dateValue, jsonStr, xAmzTarget);
	  targetHeader = HDLmHtml.buildAmzAuthorizationHeader(authValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Return the headers array list to the caller */
	  return headersArrayList;
	}
	/* Get a set of headers for an admin set password request.
     The headers are built and returned as an array list. */
  protected static ArrayList<String>  getHeadersAdminSetUserPassword(final String hostNameStr, 
  		                                                               final String userPoolId,
	  	                                                               final String userNameStr,
	  	                                                               final String passwordStr,
	  	                                                               final boolean permanentPassword,
		                                                                 final int contentLength) {
		/* Check if the host name string passed by the caller is null */
		if (hostNameStr == null) {
			String  errorText = "Host name string passed to getHeadersAdminSetUserPassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user pool ID string passed by the caller is null */
		if (userPoolId == null) {
			String  errorText = "User pool ID string passed to getHeadersAdminSetUserPassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user name string passed by the caller is null */
		if (userNameStr == null) {
			String  errorText = "User name string passed to getHeadersAdminSetUserPassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the password string passed by the caller is null */
		if (passwordStr == null) {
			String  errorText = "Password string passed to getHeadersAdminSetUserPassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content length passed by the caller is valid */
		if (contentLength <= 0) {
			String  errorText = "Content length passed to getHeadersAdminSetUserPassword is invalid";
			throw new IllegalArgumentException(errorText);
		}
	  /* Build the standard headers */
	  ArrayList<String>   standardHeaders = HDLmSecurity.getHeadersStandard(hostNameStr, contentLength);
	  /* Create an headers Array List with the standard headers in it */
 	  ArrayList<String>   headersArrayList = new ArrayList<String>(standardHeaders);
	  String  targetHeader = null;
	  /* Build a X-Amz-Date header and add it to the headers array list */
	  String  dateValue = HDLmSecurity.getTimeStamp(); 
	  targetHeader = HDLmHtml.buildXAmzDateHeader(dateValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Build a X-Amz-Target header and add it to the headers array list */
	  String  targetValue = HDLmConfigInfo.getAwsCognitoApiAdminSetUserPassword();
	  targetHeader = HDLmHtml.buildXAmzTargetHeader(targetValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Build an Authorization header and add it to the headers array list */ 
	  String  jsonStr = HDLmSecurity.getJsonAdminSetUserPassword(userPoolId, userNameStr, passwordStr, permanentPassword);
	  String  xAmzTarget = HDLmConfigInfo.getAwsCognitoApiAdminSetUserPassword();
	  String  authValue = HDLmSecurity.getAuthorizationValue(dateValue, jsonStr, xAmzTarget);
	  targetHeader = HDLmHtml.buildAmzAuthorizationHeader(authValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Return the headers array list to the caller */
	  return headersArrayList;
  }
	/* Get a set of headers for a get attributes request.
 	   The headers are built and returned as an array list. */
	protected static ArrayList<String>  getHeadersGetAttributes(final String hostNameStr, 
			final int contentLength) {
		/* Check if the host name string passed by the caller is null */
		if (hostNameStr == null) {
			String  errorText = "Host name string passed to getHeadersGetAttributes is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content length passed by the caller is valid */
		if (contentLength <= 0) {
			String  errorText = "Content length passed to getHeadersGetAttributes is invalid";
			throw new IllegalArgumentException(errorText);
		}
	  /* Build the standard headers */
	  ArrayList<String>   standardHeaders = HDLmSecurity.getHeadersStandard(hostNameStr, contentLength);
	  /* Create an headers Array List with the standard headers in it */
	  ArrayList<String>   headersArrayList = new ArrayList<String>(standardHeaders); 
	  /* Build a X-Amz-Target header and add it to the headers array list */
	  String  targetValue = HDLmConfigInfo.getAwsCognitoApiGetUser();
	  String  targetHeader = HDLmHtml.buildXAmzTargetHeader(targetValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Return the headers array list to the caller */
	  return headersArrayList;
	}
	/* Get a set of headers for an initiate authentication request.
	   The headers are built and returned as an array list. */
	protected static ArrayList<String>   getHeadersInitiateAuth(final String hostNameStr, 
	                                                        		final int contentLength) {
		/* Check if the host name string passed by the caller is null */
		if (hostNameStr == null) {
			String  errorText = "Host name string passed to getHeadersInitiateAuth is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content length passed by the caller is valid */
		if (contentLength <= 0) {
			String  errorText = "Content length passed to getHeadersInitiateAuth is invalid";
			throw new IllegalArgumentException(errorText);
		}
	  /* Build the standard headers */
	  ArrayList<String>   standardHeaders = HDLmSecurity.getHeadersStandard(hostNameStr, contentLength);
	  /* Create an headers Array List with the standard headers in it */
	  ArrayList<String>   headersArrayList = new ArrayList<String>(standardHeaders); 
	  /* Build a X-Amz-Target header and add it to the headers array list */
	  String  targetValue = HDLmConfigInfo.getAwsCognitoApiInitiateAuth();
	  String  targetHeader = HDLmHtml.buildXAmzTargetHeader(targetValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Return the headers array list to the caller */
	  return headersArrayList;	  
	}
	/* Get a set of headers for a respond to challenge request.
	   The headers are built and returned as an array list. */
	protected static ArrayList<String>   getHeadersRespondToChallenge(final String hostNameStr, 
			                                                              final int contentLength) {
		/* Check if the host name string passed by the caller is null */
		if (hostNameStr == null) {
			String  errorText = "Host name string passed to getHeadersRespondToChallenge is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content length passed by the caller is valid */
		if (contentLength <= 0) {
			String  errorText = "Content length passed to getHeadersRespondToChallenge is invalid";
			throw new IllegalArgumentException(errorText);
		}
	  /* Build the standard headers */
	  ArrayList<String>   standardHeaders = HDLmSecurity.getHeadersStandard(hostNameStr, contentLength);
	  /* Create an headers Array List with the standard headers in it */
	  ArrayList<String>   headersArrayList = new ArrayList<String>(standardHeaders); 
	  /* Build a X-Amz-Target header and add it to the headers array list */
	  String  targetValue = HDLmConfigInfo.getAwsCognitoApiRespondToAuthChallenge();
	  String  targetHeader = HDLmHtml.buildXAmzTargetHeader(targetValue);	                                
	  headersArrayList.add(targetHeader);
	  /* Return the headers array list to the caller */
	  return headersArrayList;		 
	} 	
	/* Get a set of standard headers for any AWS Cognito request. 
	   The headers are built and returned as an array list. */
	protected static ArrayList<String>  getHeadersStandard(final String hostNameStr, 
			                                                   final int contentLength) {
		/* Check if the host name string passed by the caller is null */
		if (hostNameStr == null) {
			String  errorText = "Host name string passed to getHeadersStandard is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the content length passed by the caller is valid */
		if (contentLength <= 0) {
			String  errorText = "Content length passed to getHeadersStandard is invalid";
			throw new IllegalArgumentException(errorText);
		}
	  /* Create an empty headers array list */
		ArrayList<String>   headersArrayList = new ArrayList<String>(); 
	  /* Build a host name header and add it to headers array list */
	  String  hostHeader = HDLmHtml.buildHostHeader(hostNameStr);
	  headersArrayList.add(hostHeader);
	  /* Build an accept encoding header and add it to the headers array list */
	  String  acceptValue = HDLmConfigInfo.getAwsCognitoAcceptEncoding();
	  String  acceptHeader = HDLmHtml.buildAcceptEncodingHeader(acceptValue);
	  headersArrayList.add(acceptHeader);
	  /* Build a content type header and add it to the headers array list */
	  String  contentValue = HDLmConfigInfo.getAwsCognitoContentType();
	  String  contentHeader = HDLmHtml.buildContentTypeHeader(contentValue);
	  headersArrayList.add(contentHeader);
	  /* Build a user agent header and add it to the headers array list */
	  String  userAgentValue = HDLmConfigInfo.getAwsCognitoUserAgent();
	  String  userAgentHeader = HDLmHtml.buildUserAgentHeader(userAgentValue);
	  headersArrayList.add(userAgentHeader);
	  /* Build an Amazon SDK invocation ID header and add it to the headers array list */
	  String  uuidStr = HDLmUtility.getUuidStr();
	  String  uuidHeader = HDLmHtml.buildAmzSdkInvocationIdHeader(uuidStr);
	  headersArrayList.add(uuidHeader);
	  /* Build an Amazon SDK request header and add it to the headers array list */
	  String  requestHeader = HDLmHtml.buildAmzSdkRequestHeader(1);
	  headersArrayList.add(requestHeader); 
	  /* For some strange reason, the statement below causes a duplicate header
	     error. It looks like the HDLmCurlApache.runCurlResponse routine (what
	     it calls) builds it's own content length header, causing a fatal duplicate 
	     header error. */ 
	  /* headersArrayList.add(lengthHeader); */
	  /* Return the headers array list to the caller */
	  return headersArrayList;
	}
	/* This routine gets some information from the memory storage area. 
	   The caller is assumed to provide the user name string. The user
	   name string is used as the key. If the data is not available,
	   then the output area will not contain the extracted values. */ 
	protected static HDLmUtilityResponse  getInformation(final String userNameStr) {
		/* Check some values passed by the caller */ 	 
		if (userNameStr == null) {
		  String   errorText = "User name string reference is null in getInformation";
			HDLmAssertAction(false, errorText);		    	
	  }	
		/* Create the output area, that is returned to the caller */
		HDLmUtilityResponse   outputArea = null;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Create a new output area for the values we get from JSON */
			outputArea = new HDLmUtilityResponse();
			/* Check if the output area was actually created. This should
			   always be true. */ 
			if (outputArea == null)
				break;
			/* Build the JSON strings used as the key and value */
			String  jsonKeyStr = "{\"Type\":\"Username\",\"Value\":\"" + userNameStr + "\"}"; 
			/* LOG.info("In getInformation jsonKeyStr - " + jsonKeyStr); */
			String  jsonValueStr = HDLmMemoryStorage.get(jsonKeyStr);
			/* LOG.info("In getInformation jsonValueStr - " + jsonValueStr); */
			/* The map might not contain the user name string */
			if (jsonValueStr == null)
				break;
			/* Create a new JSON parser for use below */
	    JsonParser    parser = new JsonParser();  
	    /* Make sure the inbound payload has the required key */
	    JsonElement   jsonValueJson = parser.parse(jsonValueStr);
		  /* Check if the JSON string is valid or not */
			if (!jsonValueJson.isJsonObject()) {
		 	  String  errorText = "JSON string from memory database in getInformation is invalid";
		 	  HDLmAssertAction(false, errorText);
		  }
			/* We can now get a few values from the JSON object */
			String  lastTimeStr = HDLmJson.getJsonString(jsonValueJson, "LastTime");
			String  passwordStr = HDLmJson.getJsonString(jsonValueJson, "Password"); 
			String  scopeStr = HDLmJson.getJsonString(jsonValueJson, "Scope");
			/* Store the extracted value in the output area */
			if (lastTimeStr != null)
			  outputArea.setLastTimeValue(lastTimeStr);
			if (passwordStr != null)
			  outputArea.setPasswordValue(passwordStr);
			if (scopeStr != null)
			  outputArea.setScopeValue(scopeStr);
		  break;
		}
		/* Return the output area */
		return outputArea;
	}
	/* Get some JSON for an admin get user request */
  protected static String  getJsonAdminGetUser(final String userPoolId, 
  		                                         final String userName) {
		/* Check if the user pool Id string passed by the caller is null */
		if (userPoolId == null) {
			String  errorText = "UserPoolId string passed to getJsonAdminGetUser is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to getJsonAdminGetUser is null";
			throw new NullPointerException(errorText);
		}
    /* Build the initially empty admin get user map */
    Map<String, String>   getMap = new HashMap<String, String>();
    /* Set a few values in the admin get user map */
    getMap.put("UserPoolId", userPoolId); 
    getMap.put("Username", userName); 
    /* Convert the get attributes map to JSON and return
       the JSON to the caller */ 
		Gson    gsonInstance = HDLmMain.gsonMain;
		String  outJson = gsonInstance.toJson(getMap); 
    return outJson;
  }
	/* Get some JSON for an admin set user password request */
  protected static String  getJsonAdminSetUserPassword(final String userPoolId, 
  		                                                 final String userName,
  		                                                 final String password,
  		                                                 final boolean permanentPassword) {
		/* Check if the user pool Id string passed by the caller is null */
		if (userPoolId == null) {
			String  errorText = "UserPoolId string passed to getJsonAdminSetUserPassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to getJsonAdminSetUserPassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the password string passed by the caller is null */
		if (password == null) {
			String  errorText = "Password string passed to getJsonAdminSetUserPassword is null";
			throw new NullPointerException(errorText);
		}
    /* Build the initially empty admin set password map */
    Map<String, Object>   setMap = new HashMap<String, Object>();
    /* Set a few values in the admin get user map */
    setMap.put("UserPoolId", userPoolId); 
    setMap.put("Username", userName); 
    setMap.put("Password", password);
    setMap.put("Permanent", permanentPassword);
    /* Convert the set password map to JSON and return
       the JSON to the caller */ 
		Gson    gsonInstance = HDLmMain.gsonMain;
		String  outJson = gsonInstance.toJson(setMap); 
		/*
		outJson = "{\"UserPoolId\": \"us-east-2_xTvIIRtgB\", \"Username\": \"pdschaeffer\", \"Password\": \"Pdschaeffer123!\", \"Permanent\": true}";
		*/
    return outJson;
  }
	/* Get some JSON for a get attributes request */
  protected static String  getJsonGetAttributes(final String accessToken) {
		/* Check if the access token string passed by the caller is null */
		if (accessToken == null) {
			String  errorText = "Access token string passed to getJsonGetAttributes is null";
			throw new NullPointerException(errorText);
		}
    /* Build the initially empty get attributes map */
    Map<String, String>   getMap = new HashMap<String, String>();
    /* Set a few values in the get attributes map */
    getMap.put("AccessToken", accessToken); 
    /* Convert the get attributes map to JSON and return
       the JSON to the caller */ 
		Gson    gsonInstance = HDLmMain.gsonMain;
		String  outJson = gsonInstance.toJson(getMap); 
    return outJson;
  }
  /* Get some JSON for an initiate authentication request */
  protected static String  getJsonInitiateAuth(final String clientId, 
  		                                         final String userName, 
  		                                         final String passwordStr) {
		/* Check if the client ID string passed by the caller is null */
		if (clientId == null) {
			String  errorText = "Client ID string passed to getJsonInitiateAuth is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to getJsonInitiateAuth is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the password string passed by the caller is null */
		if (passwordStr == null) {
			String  errorText = "Password string passed to getJsonInitiateAuth is null";
			throw new NullPointerException(errorText);
		}
  	Gson    gsonInstance;
    /* Build the initially empty authentication map */
    Map<String, Object>  authMap = new HashMap<String, Object>();
    /* Build the initially empty parameters map */
    Map<String, String>  parmMap = new HashMap<String, String>();
    /* Set a few values in the parameters map */
    parmMap.put("USERNAME", userName);
    parmMap.put("PASSWORD", passwordStr);
    /* Convert the parameters map to JSON */ 
	  gsonInstance = HDLmMain.gsonMain; 
    /* Build the initially empty client metadata map */
    Map<String, String>  clientMap = new HashMap<String, String>();
    /* Set a few values in the client metadata map */
    clientMap.put("username", userName);
    clientMap.put("password", passwordStr);
    /* Set a few values in the authentication map */
    authMap.put("ClientId", clientId);
    authMap.put("AuthFlow", "USER_PASSWORD_AUTH");
    authMap.put("AuthParameters", parmMap);
    authMap.put("ClientMetadata", clientMap);
    /* Convert the authentication map to JSON and return
       the JSON to the caller */
    gsonInstance = HDLmMain.gsonMain;
    String  outJson = gsonInstance.toJson(authMap); 
    return outJson;
  }
  /* Get some JSON for a respond to challenge request */
  protected static String  getJsonRespondToChallenge(final String clientId, 
																								  	 final String challengeName, 
																								  	 final String userName, 
																								  	 final String verificationCode, 
																								  	 final String sessionStr) {
		/* Check if the client ID string passed by the caller is null */
		if (clientId == null) {
			String  errorText = "Client ID string passed to getJsonRespondToChallenge is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the challenge name string passed by the caller is null */
		if (challengeName == null) {
			String  errorText = "Challenge name string passed to getJsonRespondToChallenge is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to getJsonRespondToChallenge is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the verification code string passed by the caller is null */
		if (verificationCode == null) {
			String  errorText = "Verification code string passed to getJsonRespondToChallenge is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the session string passed by the caller is null */
		if (sessionStr == null) {
			String  errorText = "Session string passed to getJsonRespondToChallenge is null";
			throw new NullPointerException(errorText);
		}
    /* Build the initially empty respond to challenge map */
    Map<String, Object>   respondMap = new HashMap<String, Object>();
    /* Build the initially empty challenge responses map */
    Map<String, String>   challengeMap = new HashMap<String, String>();
    /* Set a few values in the parameters map */
    challengeMap.put("SMS_MFA_CODE", verificationCode);
    challengeMap.put("USERNAME", userName);
    /* Build the initially empty client metadata map */
    Map<String, String> clientMap = new HashMap<String, String>();
    /* Set a few values in the client metadata map */
    clientMap.put("sms_mfa_code", verificationCode);
    clientMap.put("username", userName);
    /* Set a few values in the authentication map */
    respondMap.put("ClientId", clientId);
    respondMap.put("ChallengeName", challengeName);
    respondMap.put("Session", sessionStr);
    respondMap.put("ChallengeResponses", challengeMap);
    respondMap.put("ClientMetadata", clientMap);
    /* Convert the respond to challenge map to JSON and return
       the JSON to the caller */
    Gson  gsonInstance = HDLmMain.gsonMain; 
    String  outJson = gsonInstance.toJson(respondMap);
    return outJson;
  }
  /* This routine returns the current date and time in a format
     that AWS Cognito likes. The returned value is used to build
     a header, but could be used for anything. */ 
  protected static String  getTimeStamp() {
    DateFormat  dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
    dateFormat.setTimeZone(TimeZone.getTimeZone("GTM"));
    return dateFormat.format(new Date());       
  }
  /* Get the current user, using the Cognito network API */
	protected static HDLmApacheResponse  getUser(final String userPoolId,
			                                         final String userName) {
		/* Check if the user pool ID string passed by the caller is null */
		if (userPoolId == null) {
			String  errorText = "User pool Id string passed to getUser is null";
			throw new NullPointerException(errorText);
		}	
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to getUser is null";
			throw new NullPointerException(errorText);
		}	
 	  /* Get some values needed to invoke the AWS Cognito API */
	  String  AWSCognitoHostName = HDLmConfigInfo.getAwsCognitoHost(); 
	  String  apiJson = HDLmSecurity.getJsonAdminGetUser(userPoolId, userName);
	  int     apiJsonLen = apiJson.length();
	  ArrayList<String>   apiHeaders = getHeadersAdminGetUser(AWSCognitoHostName, userPoolId, userName, apiJsonLen);
	  HDLmApacheResponse  apiOutput;
	  /* Invoke the Cognito API and return the output string to the caller */
	  apiOutput = HDLmSecurity.invokeCognitoApi(apiHeaders, apiJson);
	  return apiOutput;
	}
  /* Invoke an AWS Cognito API. This routine invokes an AWS Cognito API 
	   and returns a string to the caller. */
	protected static HDLmApacheResponse  invokeCognitoApi(final ArrayList<String> apiHeaders, 
	                                 		                  final String apiJson) {
		/* Check if the API headers array passed by the caller is null */
		if (apiHeaders == null) {
			String  errorText = "API headers array passed to invokeCognitoApi is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the API JSON string passed by the caller is null */
		if (apiJson == null) {
			String  errorText = "API JSON string passed to invokeCognitoApi is null";
			throw new NullPointerException(errorText);
		}
	  /* Declare a few variables for use below */
		HDLmApacheResponse  outResponse; 
	  String              urlStr;
	  /* Get the AWS Cognito domain name */
	  String  cognitoDomain = HDLmConfigInfo.getAwsCognitoHost();
	  /* Build the URL string for use below */
	  String  protocol = "https";
	  urlStr = protocol + "://" + cognitoDomain;
	  /* The callers passes the headers and the JBON */
	  /* Use the Curl mechanism */
	  outResponse = HDLmCurlApache.runCurlResponse(urlStr,
							  		                             "", 
							  		                             "", 
							  		                             HDLmHttpTypes.POST,
							  		                             apiHeaders,
							  		                             apiJson,
							  		                             null,  
																					       HDLmOutboundJson.OUTBOUNDJSONYES,
																					       HDLmSkipAuth.SKIPAUTHYES,
																					       HDLmReportErrors.REPORTERRORS); 
	  /* Return the output response to the caller */
	  return outResponse;
	}
	/* Process the verification response. This routine extracts some useful
	   information from the verification response and stores it in the data
	   area passed to this routine. In other words, the data area passed to
	   this routine acts as both an input area and an output area for this 
	   routine. */
	protected static HDLmUtilityResponse  processVerificationResponse(final HDLmApacheResponse outResponse) {
		/* Check some values passed by the caller */ 	 
		if (outResponse == null) {
	 	  String   errorText = "Curl output reference is null in processVerificationResponse";
			HDLmAssertAction(false, errorText);		    	
	  }	
    /* Get the JSON output area from the response area */
		String  jsonResponse = outResponse.getStringContent();
		if (jsonResponse == null) {
	 	  String   errorText = "JSON Curl response is null in processVerificationResponse";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Create a new JSON parser for use below */
    JsonParser    parser = new JsonParser();  
    /* Make sure the JSON response has the required key */
    JsonElement   jsonElement = parser.parse(jsonResponse);
	  /* Check if the JSON string obtained from the response is valid */
		if (!jsonElement.isJsonObject()) {
	 	  String  errorText = "JSON string in processVerificationResponse from response is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Create the new utility response area */
		HDLmUtilityResponse  utilityResponse = new HDLmUtilityResponse();
    boolean       hasAuthResultsKey = HDLmJson.hasJsonKey(jsonElement, "AuthenticationResult");
		if (hasAuthResultsKey == false) {
			String  errorText = "Response JSON data does not have AuthenticationResult key";
			HDLmAssertAction(false, errorText);	
		}
    /* Get the authentication results */
		JsonElement   authResults = HDLmJson.getJsonObject(jsonElement, "AuthenticationResult");
	  /* Check if the JSON string obtained from the response is valid */
		if (!authResults.isJsonObject()) {
	 	  String  errorText = "JSON authentication results object in processVerificationResponse is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
		/* Check if we can find the ID token for use below */
    boolean       hasIdTokenKey = HDLmJson.hasJsonKey(authResults, "IdToken");
		if (hasIdTokenKey == false) {
			String  errorText = "Response JSON data does not have IdToken key in AuthenticationResult";
			HDLmAssertAction(false, errorText);	
		}
    String        idTokenStr = HDLmJson.getJsonString(authResults, "IdToken"); 
    /* Split the ID token into several parts */
    String[]  idTokenSplit = idTokenStr.split("\\.");   
    /* Get the part of the ID token we need/want */
    String  idTokenPart = idTokenSplit[1];
    /* Convert the part of the ID token to a JSON string */
    byte[]  idTokenPartBytes = Base64.getDecoder().decode(idTokenPart);
    String  idTokenJson = new String(idTokenPartBytes);
		/* Create a new JSON parser for use below */
    parser = new JsonParser();  
    jsonElement = parser.parse(idTokenJson);
	  /* Check if the JSON string obtained from ID token is valid */
		if (!authResults.isJsonObject()) {
	 	  String  errorText = "JSON obtained from the ID token in processVerificationResponse is invalid";
	 	  HDLmAssertAction(false, errorText);
	  }
    /* Make sure the JSON response has the required key */
    boolean       hasCustomScopeKey = HDLmJson.hasJsonKey(jsonElement, "custom:Scope");
		if (hasCustomScopeKey == false) {
			String  errorText = "ID token JSON data does not have custom:Scope key";
			HDLmAssertAction(false, errorText);	
		}
    /* Get the custom:Scope value */
		String  scopeStr = HDLmJson.getJsonString(jsonElement, "custom:Scope"); 
		if (scopeStr == null) {
	 	  String   errorText = "Scope string from Curl response is null in processVerificationResponse";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Save the scope string in the output response */
		utilityResponse.setScopeValue(scopeStr);
		/* Make sure the JSON response has the required key */
    boolean       hasCognitoUsernameKey = HDLmJson.hasJsonKey(jsonElement, "cognito:username");
		if (hasCognitoUsernameKey == false) {
			String  errorText = "ID token JSON data does not have cognito:username key";
			HDLmAssertAction(false, errorText);	
		}
    /* Get the cognito:username value */
		String  userNameStr = HDLmJson.getJsonString(jsonElement, "cognito:username"); 
		if (userNameStr == null) {
	 	  String   errorText = "User name string from Curl response is null in processVerificationResponse";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Save the user name string in the output response */
		utilityResponse.setUserNameValue(userNameStr);
		return utilityResponse;
	}	
  /* Set the password for the current user, using the Cognito network
     API. The password is set by making a network API call. */
	protected static HDLmApacheResponse  setPassword(final String userPoolId,
			                                             final String userName,
			                                             final String password) {
		/* Check if the user pool ID string passed by the caller is null */
		if (userPoolId == null) {
			String  errorText = "User pool ID string passed to setPassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the user name string passed by the caller is null */
		if (userName == null) {
			String  errorText = "User name string passed to setPassword is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the password string passed by the caller is null */
		if (password == null) {
			String  errorText = "Password string passed to setPassword is null";
			throw new NullPointerException(errorText);
		}		
	  /* Get some values needed to invoke the AWS Cognito API */
		boolean   permanentPassword = true;
	  String    AWSCognitoHostName = HDLmConfigInfo.getAwsCognitoHost();
	  String    apiJson = HDLmSecurity.getJsonAdminSetUserPassword(userPoolId, userName, password, permanentPassword);
	  int       apiJsonLen = apiJson.length();
	  ArrayList<String>   apiHeaders = getHeadersAdminSetUserPassword(AWSCognitoHostName, userPoolId, userName, password, permanentPassword, apiJsonLen);
	  HDLmApacheResponse  apiOutput;
	  /* Invoke the Cognito API and return the output string to the caller */
	  apiOutput = HDLmSecurity.invokeCognitoApi(apiHeaders, apiJson);
	  return apiOutput;
	}
	/* This routine uses some information passed to it, to update the memory 
	   storage area. The caller is assumed to provide the user name string 
	   (the key) and the information (the last time string) to be updated. */ 
	protected static void  updateLastTime(final String userNameStr,  
			                                  final String lastTimeStr) {
		/* Check some values passed by the caller */ 	 
		if (userNameStr == null) {
		  String   errorText = "User name string reference is null in updateLastTime";
			HDLmAssertAction(false, errorText);		    	
	  }	 
		/* Check the last time value passed by the caller */
		if (lastTimeStr == null) {
		  String   errorText = "Last time string reference is null in updateLastTime";
			HDLmAssertAction(false, errorText);		    	
	  }	
		/* Build the JSON strings used as the key and value */
		String  jsonKeyStr = "{\"Type\":\"Username\",\"Value\":\"" + userNameStr + "\"}"; 
		/* Try to get the existing (if any) data from the memory database */
		String  jsonValueArea = HDLmMemoryStorage.get(jsonKeyStr);
		String  passwordStr = null;
		String  scopeStr = null;
		if (jsonValueArea != null) {
			/* Create a new JSON parser for use below */
	    JsonParser    parser = new JsonParser();  
	    /* Check if the JSON value area has the required keys */
	    JsonElement   jsonValueJson = parser.parse(jsonValueArea);
		  /* Check if the JSON string is valid or not */
			if (!jsonValueJson.isJsonObject()) {
		 	  String  errorText = "JSON string from memory database in updateLastTime is invalid";
		 	  HDLmAssertAction(false, errorText);
		  }
			/* We can now try to get a few values from the JSON object */
			scopeStr = HDLmJson.getJsonString(jsonValueJson, "Scope");
			passwordStr = HDLmJson.getJsonString(jsonValueJson, "Password");  
		}
		/* Create a new JSON object for use below */
		JsonObject  newJsonObject = new JsonObject();
		HDLmJson.setJsonString(newJsonObject, "LastTime", lastTimeStr);
		if (passwordStr != null)
		  HDLmJson.setJsonString(newJsonObject, "Password", passwordStr);
		if (scopeStr != null)
		  HDLmJson.setJsonString(newJsonObject, "Scope", scopeStr);		
		/* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
		String   jsonValueStr = gsonInstance.toJson(newJsonObject);		
		HDLmMemoryStorage.put(jsonKeyStr, jsonValueStr);
	}
	/* This routine uses some information passed to it, to update the memory 
	   storage area. The caller is assumed to provide the user name string 
	   (the key) and the information (the last time string and the scope 
	   string) to be updated. */ 
	protected static void  updateLastTimeScope(final String userNameStr,  
			                                       final String lastTimeStr,
			                                       final String scopeStr) {
		/* LOG.info("In HDLmSecuriy.updateLastTimeScope"); */
		/* Check some values passed by the caller */ 	 
		if (userNameStr == null) {
		  String   errorText = "User name string reference is null in updateLastTimeScope";
			HDLmAssertAction(false, errorText);		    	
 	  }	 
		/* Check the last time value passed by the caller */
		if (lastTimeStr == null) {
		  String   errorText = "Last time string reference is null in updateLastTimeScope";
			HDLmAssertAction(false, errorText);		    	
	  }	
		/* Check the scope value passed by the caller */
		if (scopeStr == null) {
		  String   errorText = "Scope string reference is null in updateLastTimeScope";
			HDLmAssertAction(false, errorText);		    	
	  }	
		/* Build the JSON strings used as the key and value */
		String  jsonKeyStr = "{\"Type\":\"Username\",\"Value\":\"" + userNameStr + "\"}"; 
		/* Try to get the existing (if any) data from the memory database */
		String  jsonValueArea = HDLmMemoryStorage.get(jsonKeyStr);
		String  passwordStr = null; 
		if (jsonValueArea != null) {
			/* Create a new JSON parser for use below */
	   JsonParser    parser = new JsonParser();  
	   /* Check if the JSON value area has the required keys */
	   JsonElement   jsonValueJson = parser.parse(jsonValueArea);
		  /* Check if the JSON string is valid or not */
			if (!jsonValueJson.isJsonObject()) {
		 	  String  errorText = "JSON string from memory database in updateLastTimeScope is invalid";
		 	  HDLmAssertAction(false, errorText);
		  }
			/* We can now try to get a value from the JSON object */ 
			passwordStr = HDLmJson.getJsonString(jsonValueJson, "Password");  
			/* LOG.info("In HDLmSecuriy.updateLastTimeScope - Password - " + passwordStr); */
		}
		/* Create a new JSON object for use below */
		JsonObject  newJsonObject = new JsonObject();
		HDLmJson.setJsonString(newJsonObject, "LastTime", lastTimeStr);
		HDLmJson.setJsonString(newJsonObject, "Scope", scopeStr);
		/* LOG.info("In HDLmSecuriy.updateLastTimeScope - LastTime - " + lastTimeStr); */
		/* LOG.info("In HDLmSecuriy.updateLastTimeScope - Scope - " + scopeStr); */
		if (passwordStr != null)
		  HDLmJson.setJsonString(newJsonObject, "Password", passwordStr);
		/* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
		String   jsonValueStr = gsonInstance.toJson(newJsonObject);		
		HDLmMemoryStorage.put(jsonKeyStr, jsonValueStr);
	}
	/* This routine uses some information passed to it, to update the memory 
     storage area. The caller is assumed to provide the user name string 
     (the key) and the information (the password string) to be updated. */ 
	protected static void  updatePassword(final String userNameStr,  
			                                  final String passwordStr) {
		/* Check some values passed by the caller */ 	 
		if (userNameStr == null) {
		  String   errorText = "User name string reference is null in updatePassword";
			HDLmAssertAction(false, errorText);		    	
	  }	 
		/* Check some values passed by the caller */ 	 
		if (passwordStr == null) {
		  String   errorText = "Password string reference is null in updatePassword";
			HDLmAssertAction(false, errorText);		    	
	  }	 
		/* Build the JSON strings used as the key and value */
		String  jsonKeyStr = "{\"Type\":\"Username\",\"Value\":\"" + userNameStr + "\"}"; 
		/* Try to get the existing (if any) data from the memory database */
		String  jsonValueArea = HDLmMemoryStorage.get(jsonKeyStr);
		String  lastTimeStr = null;
		String  scopeStr = null;
		if (jsonValueArea != null) {
			/* Create a new JSON parser for use below */
	   JsonParser    parser = new JsonParser();  
	   /* Check if the JSON value area has the required keys */
	   JsonElement   jsonValueJson = parser.parse(jsonValueArea);
		  /* Check if the JSON string is valid or not */
			if (!jsonValueJson.isJsonObject()) {
		 	  String  errorText = "JSON string from memory database in updatePassword is invalid";
		 	  HDLmAssertAction(false, errorText);
		  }
			/* We can now try to get a few values from the JSON object */
			scopeStr = HDLmJson.getJsonString(jsonValueJson, "Scope");
			lastTimeStr = HDLmJson.getJsonString(jsonValueJson, "LastTime");  
		}
		/* Create a new JSON object for use below */
		JsonObject  newJsonObject = new JsonObject();
		HDLmJson.setJsonString(newJsonObject, "Password", passwordStr);
		if (lastTimeStr != null)
		  HDLmJson.setJsonString(newJsonObject, "LastTime", lastTimeStr);
		if (scopeStr != null)
		  HDLmJson.setJsonString(newJsonObject, "Scope", scopeStr);		
		/* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
		String   jsonValueStr = gsonInstance.toJson(newJsonObject);		
		HDLmMemoryStorage.put(jsonKeyStr, jsonValueStr);
	}
	/* This routine uses some information passed to it, to update the memory 
	   storage area. The caller is assumed to provide the user name string 
	   (the key) and the information (the scope string) to be updated. */ 
	protected static void  updateScope(final String userNameStr,  
			                               final String scopeStr) {
		/* Check some values passed by the caller */ 	 
		if (userNameStr == null) {
		  String  errorText = "User name string reference is null in updateScope";
			HDLmAssertAction(false, errorText);		    	
	  }	 
		/* Check some values passed by the caller */ 	 
		if (scopeStr == null) {
		  String  errorText = "Scope string reference is null in updateScope";
			HDLmAssertAction(false, errorText);		    	
	  }	 
		/* Build the JSON strings used as the key and value */
		String  jsonKeyStr = "{\"Type\":\"Username\",\"Value\":\"" + userNameStr + "\"}"; 
		/* Try to get the existing (if any) data from the memory database */
		String  jsonValueArea = HDLmMemoryStorage.get(jsonKeyStr);
		String  lastTimeStr = null;
		String  passwordStr = null;
		if (jsonValueArea != null) {
			/* Create a new JSON parser for use below */
	    JsonParser    parser = new JsonParser();  
	    /* Check if the JSON value area has the required keys */
	    JsonElement   jsonValueJson = parser.parse(jsonValueArea);
		  /* Check if the JSON string is valid or not */
			if (!jsonValueJson.isJsonObject()) {
		 	  String  errorText = "JSON string from memory database in updateScope is invalid";
		 	  HDLmAssertAction(false, errorText);
		  }
			/* We can now try to get a few values from the JSON object */
			passwordStr = HDLmJson.getJsonString(jsonValueJson, "Password");
			lastTimeStr = HDLmJson.getJsonString(jsonValueJson, "LastTime");  
		}
		/* Create a new JSON object for use below */
		JsonObject  newJsonObject = new JsonObject();
		HDLmJson.setJsonString(newJsonObject, "Scope", scopeStr);
		if (lastTimeStr != null)
		  HDLmJson.setJsonString(newJsonObject, "LastTime", lastTimeStr);
		if (passwordStr != null)
		  HDLmJson.setJsonString(newJsonObject, "Password", passwordStr);		
		/* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
		String   jsonValueStr = gsonInstance.toJson(newJsonObject);		
		HDLmMemoryStorage.put(jsonKeyStr, jsonValueStr);
	}
}
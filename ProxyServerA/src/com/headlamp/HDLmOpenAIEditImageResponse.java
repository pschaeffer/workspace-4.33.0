package com.headlamp;

import java.util.ArrayList;

/**
 * HDLmOpenAIEditImageResponse short summary.
 *
 * HDLmOpenAIEditImageResponse description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */ 
public class HDLmOpenAIEditImageResponse {
  /* This class can be instantiated using the
     constructor below */
	@SuppressWarnings("unused")
	protected HDLmOpenAIEditImageResponse(ArrayList<String> nodePathValue, 
			                                  ArrayList<String> choicesValue,
			                                  String messageStr) {
		/* Check if the node path passed by the caller is null. This 
		   check is not really done because the node path value can 
		   be null in some cases. */
		if (nodePathValue == null &&
				nodePathValue != null) {
			String  errorText = "Model value string passed to HDLmOpenAIEditImageResponse is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the choices value passed by the caller is null */
		if (choicesValue == null) {
			String  errorText = "Choices value string passed to HDLmOpenAIEditImageResponse is null";
			throw new NullPointerException(errorText);
		}		
		/* Check if the message string passed by the caller is null. This 
       check is not really done, because the message string value can 
       be null in some cases. */
    if (messageStr == null &&
  	  	messageStr != null) {
	    String  errorText = "Message string passed to HDLmOpenAIEditImageResponse is null";
	    throw new NullPointerException(errorText);
  	}
		/* Set a few fields in the new instance */
		nodePath = nodePathValue;
		choices = choicesValue;
		message = messageStr;
	}	
	/* The node path is specified using the field below */ 
	@SuppressWarnings("unused")
	private ArrayList<String>   nodePath = null;
	/* The image choices/variants are specified using the field below */ 
	@SuppressWarnings("unused")
	private ArrayList<String>   choices = null;
	/* The message (most likely an error message) is specified using the field below */ 
	@SuppressWarnings("unused")
	private String              message = null;
}
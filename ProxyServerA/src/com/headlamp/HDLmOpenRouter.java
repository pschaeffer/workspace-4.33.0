package com.headlamp;

import static com.headlamp.HDLmAssert.HDLmAssertAction;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * HDLmOpenRouter short summary.
 *
 * HDLmOpenRouter description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */
public class HDLmOpenRouter {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
private static final Logger LOG = LoggerFactory.getLogger(HDLmOpenRouter.class);  
	/* This class can never be instantiated */
	private HDLmOpenRouter() {}
	/* Build an Open Router authorization header */
	protected static String  buildAuthorizationHeader(final HDLmApiKey apiKeyEnum) {
		/* Check one or more values passed by the caller */
		/* Check if the API key enum value is null */
		if (apiKeyEnum == null) {
			String errorText = "API key enum reference passed to buildAuthorizationHeader is null";
			throw new NullPointerException(errorText);
		}
		/* The API key enum must be a valid value */  
		if (apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERTEST   &&
				apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERVSCODE &&  
				apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERWEBPAGEIMPROVER) { 
			String   errorFormat = "API key enum value (%s) passed to buildAuthorizationHeader is invalid";
			String   errorText = String.format(errorFormat, apiKeyEnum.toString());
			HDLmAssertAction(false, errorText);			
		}
		String  apiKeyStr = null;
		/* Get the Open Router key from the configuration values */
		switch (apiKeyEnum) {
		case APIKEYOPENROUTERTEST:
			apiKeyStr = HDLmConfigInfo.getOpenRouterTestApiKey();
			break;
		case APIKEYOPENROUTERVSCODE:
			apiKeyStr = HDLmConfigInfo.getOpenRouterVSCodeApiKey();
			break;
		case APIKEYOPENROUTERWEBPAGEIMPROVER:
			apiKeyStr = HDLmConfigInfo.getOpenRouterWebPageImproverApiKey();
			break;
		default:
			String errorFormat = "API key enum value (%s) passed to buildAuthorizationHeader is invalid";
			String errorText = String.format(errorFormat, apiKeyEnum.toString());
			HDLmAssertAction(false, errorText);
		}
		/* Build an authorization header using an API key string */
		return HDLmOpenRouter.buildAuthorizationHeaderUsingAPIKeyStr(apiKeyStr);
	}
	/* Build an Open Router authorization header using an API key string that
	   was passed by the caller */
	protected static String  buildAuthorizationHeaderUsingAPIKeyStr(final String apiKeyStr) {
		/* Check one or more values passed by the caller */
		if (apiKeyStr == null) {
			String   errorText = "API key string reference passed to buildAuthorizationHeaderUsingAPIKeyStr is null";
			throw new NullPointerException(errorText);
		}
		/* Create the string builder for use below */
		StringBuilder   outBuilder = new StringBuilder(); 
		/* Add the parts of the authorization header */
		outBuilder.append("Authorization");
		outBuilder.append(":");
		outBuilder.append(" ");
		outBuilder.append("Bearer");
		outBuilder.append(" ");
		outBuilder.append(apiKeyStr);
		return outBuilder.toString();
	}
	/* Build a content type header */ 
	protected static String  buildContentTypeHeader(final String contentTypeStr) {
		/* Check one or more values passed by the caller */
		if (contentTypeStr == null) {
			String   errorText = "Content type string reference passed to buildContentTypeHeader is null";
			throw new NullPointerException(errorText);			
		}
	  /* Create the string builder for use below */
		StringBuilder   outBuilder = new StringBuilder(); 
		/* Add the parts of the authorization header */	
	  outBuilder.append("Content-Type");
	  outBuilder.append(":");
	  outBuilder.append(" ");
	  outBuilder.append(contentTypeStr);
	  return outBuilder.toString();
	}
	/* Build a header list for use with the Open Router API */
	protected static ArrayList<String> buildHeaders(final HDLmApiKey apiKeyEnum) {
		/* Check one or more values passed by the caller */
		/* Check if the API key enum value is null */
		if (apiKeyEnum == null) {
			String errorText = "API key enum reference passed to buildHeaders is null";
			throw new NullPointerException(errorText);
		}
		/* The API key enum must be a valid value */  
		if (apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERTEST   &&
				apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERVSCODE &&  
				apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERWEBPAGEIMPROVER) { 
			String   errorFormat = "API key enum value (%s) passed to buildHeaders is invalid";
			String   errorText = String.format(errorFormat, apiKeyEnum.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Build a header list for use later */
		ArrayList<String>   headerList = new ArrayList<String>();
		/* Build an authorization header and add it to the header list */
		String  authorizationHeader = HDLmOpenRouter.buildAuthorizationHeader(apiKeyEnum);
		headerList.add(authorizationHeader);
		String  contentTypeHeader = HDLmOpenRouter.buildJsonContentTypeHeader();
		headerList.add(contentTypeHeader);
		/* Return the header list to the caller */
		return headerList;
	}
	/* Build an application JSON content type header */ 
	protected static String  buildJsonContentTypeHeader() {
	  String  outStr = "";
	  outStr += HDLmOpenRouter.buildContentTypeHeader("application/json");
	  return outStr;
	}
	/* Build some JSON for use by the Open Router API for getting image
	   variations through chat/completions multimodal input. */
	protected static String  buildJsonImageVariations(final String inputImageStr) {
		/* Check one or more values passed by the caller */
		if (inputImageStr == null) {
			String   errorText = "Input image URL reference passed to buildJsonImageVariations is null";
			throw new NullPointerException(errorText);
		}
		/* Build a JSON object for use below */
		JsonObject  parentJsonObject = HDLmJson.createJsonObject();
		String      apiModel = HDLmConfigInfo.getOpenRouterImageApiModel();
		HDLmJson.addStringToJsonObject(parentJsonObject, "model", apiModel);
		/* Build a multimodal user message with text instructions + source image URL. */
		JsonArray   messageArray = HDLmJson.createJsonArray();
		JsonObject  userMessageObject = HDLmJson.createJsonObject();
		HDLmJson.addStringToJsonObject(userMessageObject, "role", "user");
		JsonArray   contentArray = HDLmJson.createJsonArray();
		JsonObject  textContentObject = HDLmJson.createJsonObject();
		HDLmJson.addStringToJsonObject(textContentObject, "type", "text");
		HDLmJson.addStringToJsonObject(textContentObject, "text", "Create three image variations of the provided image.");
		HDLmJson.addObjectToJsonArray(contentArray, textContentObject);
		JsonObject  imageContentObject = HDLmJson.createJsonObject();
		HDLmJson.addStringToJsonObject(imageContentObject, "type", "image_url");
		JsonObject  imageUrlObject = HDLmJson.createJsonObject();
		HDLmJson.addStringToJsonObject(imageUrlObject, "url", inputImageStr);
		imageContentObject.add("image_url", imageUrlObject);
		HDLmJson.addObjectToJsonArray(contentArray, imageContentObject);
		HDLmJson.addArrayToJsonObject(userMessageObject, "content", contentArray);
		HDLmJson.addObjectToJsonArray(messageArray, userMessageObject);
		HDLmJson.addArrayToJsonObject(parentJsonObject, "messages", messageArray);
		/* Convert the object to a JSON string */
		Gson     gsonInstance = HDLmMain.gsonMain;
		String   dataJson = gsonInstance.toJson(parentJsonObject);
		return dataJson;
	}
	/* Build some JSON for use by the Open Router API for getting text 
     variations */
	protected static HDLmResponse buildJsonTextVariations(final String inputStr, String urlStr, final String pathValueStr) {
 		/* Check one or more values passed by the caller */
		if (inputStr == null) {
			String   errorText = "Input string reference passed to buildJsonTextVariations is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the URL string is null */ 
		if (urlStr == null) {
			String   errorText = "URL string reference passed to buildJsonTextVariations is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the path value string is null */ 
		if (pathValueStr == null) {
			String   errorText = "Path value string reference passed to buildJsonTextVariations is null";
			throw new NullPointerException(errorText);			
		}
		/* Build a JSON object for use below */
		JsonObject  parentJsonObject = HDLmJson.createJsonObject();
	  /* HDLmJson.addStringToJsonObject(parentJsonObject, "model", "gpt-3.5-turbo-0613"); */ 
	  /* HDLmJson.addStringToJsonObject(parentJsonObject, "model", "gpt-3.5-turbo-1106"); */  
		/* HDLmJson.addStringToJsonObject(parentJsonObject, "model", "gpt-3.5-turbo-0613"); */  
	  /* HDLmJson.addStringToJsonObject(parentJsonObject, "model", "gpt-4"); */		
		/* Get the Open Router model from the configuration values */ 
		String  apiModel = HDLmConfigInfo.getOpenRouterApiModel();		
	  HDLmJson.addStringToJsonObject(parentJsonObject, "model", apiModel);
	  HDLmJson.addNumberToJsonObject(parentJsonObject, "temperature", 0.9);  
	  /* Add the path value string to the URL, if need be */
	  int   pathValueStrLen = pathValueStr.length();
	  /* Save the original URL value for use (checking) later */
	  String  originalUrlStr = urlStr;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check for a regex path. Regex paths are not used. */
			if (pathValueStrLen >= 3                 &&
					pathValueStr.startsWith("//") &&
					pathValueStr.endsWith("/"))
				break;
			/* Check if the path begins with a forward slash */
			if (pathValueStrLen >= 1 &&
					pathValueStr.startsWith("/"))
				urlStr += pathValueStr;
			else
				urlStr = urlStr + "/" + pathValueStr;			
			break;
		}    
	  /* Get the text from the current web page */
		String  textStrs = "";
		if (originalUrlStr.length() > 0) {
	   textStrs = HDLmHttp.getTextFromWebPage(urlStr);   
	   /* We may get a null value back from the call above. 
	      This will happen if the web page can not be adcessed
	      for some reason. We need to be able to handle this 
	      case. */ 
	   if (textStrs == null)
	   	textStrs = "";  
	   /* Force the text strings to an empty string in all cases
	      Experience has shown that the API hangs if actual text 
	      strings are sent do it. Why is not clear. */
	   textStrs = ""; 
		}
	  /* Get the maximum allowed length of the text string */
	  int   maxAllowedLength = HDLmConfigInfo.getOpenRouterMaximumStringLength(); 
	  /* Check if the text string is too long */
	  if (textStrs.length() > maxAllowedLength) {
	 	  /* Get the maximum length string */
	 	  textStrs = textStrs.substring(0, maxAllowedLength);
	 	  /* Find the last vertical bar character */
	 	  int   lastIndex = textStrs.lastIndexOf('|');
	 	  if (lastIndex >= 0) 
	 	    textStrs = textStrs.substring(0, lastIndex);
	 	  /* Remove any trailing vertical bars and blanks */
	 	  textStrs = HDLmString.removeChars(textStrs, " |", HDLmStringTypes.TRAILING);
	  }
	  /* Add a newline to the output text */
	  textStrs += '\n';
	 
	  String  designerStr = "You are a web page designer";
	  String  webSiteStr = "You have a website";
	  String  createStr = "You want to create text that is modern";
	  String  marketingStr = "doesn't sound like marketing speak";
	  String  yetStr = "yet encourages action";
	  String  weSalesUserStr = "We are trying to improve our website to improve sales and user engagement";
	  String  weUserStr = "We are trying to improve our user engagement on our website";
	  String  webSiteTextShort = "The website text is";
	  String  webSiteText = "The current website contains the following text";
	  String  whenWeAsk = "When we ask for variations on existing text, always return your answer as a JSON array";
	  String  answerStr = "Your answers are always in JSON form";
	  String  suchAsStr = "such as";
	  String  text14 = "\"Text 1\", \"Text 2\", \"Text 3\", \"Text 4\", ";
	  String  text58 = "\"Text 5\", \"Text 6\", \"Text 7\", \"Text 8\", ";
	  String  text910 = "\"Text 9\", \"Text 10\"";
	 
	  String  context = designerStr + ". " + webSiteStr + ". " + createStr + " and " + marketingStr + ", " + yetStr + ". " + webSiteText + ": ";
	  context = designerStr + ". " + weSalesUserStr + ". " + webSiteText + ":\n" + textStrs;
	  context = designerStr + ". " + weUserStr + ". " + webSiteText + ":\n" + textStrs;
	  context = designerStr + ". " + weUserStr + ". " + whenWeAsk + ". " + webSiteText + ":\n" + textStrs;
	  /*        
	  context += "\"";
	  context += textStrs;
	  context += "\"";
	  context += ".";
	  */
	  /*
	  context += ". " + answerStr + ", " + suchAsStr + " [" + text14 + text58 + text910 + "]";
	  */
	
	  String  contaxt = "You are a web designer. You have a website where tickets are bought and sold for concerts." + 
	                    " You want to create text that is modern and doesn't sound like marketing speak, " +
	 		                "yet encourages action. Your answers are always in JSON form, " +
	                    "such as [\"Text 1\", \"Text 2\", \"Text 3\", \"Text 4\", " +
	                    "\"Text 5\", \"Text 6\", \"Text 7\", \"Text 8\", " +
	                    "\"Text 9\", \"Text 10\"]"; 
	  String  goal = "Generate 10 variations for \"" +
	                 inputStr +
	 		             "\" text. Return your answer as:\n{\"variations\":[\"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"10\"]}";   
	 
	  goal = "Give us 10 variations for \"" + 
	         inputStr + 
	         "\" text.";
			
	  /* Save the context and goal value for use later */
	  HDLmResponse  outResponse = new HDLmResponse();
	  outResponse.setReturnContext(context);
	  outResponse.setReturnGoal(goal);
	 
	  /* Create a JSON array to store the messages */
	  JsonArray   messageArray = HDLmJson.createJsonArray();
	  /* Create a JSON object for each message and add it to the array */
	  JsonObject  firstMessageObject = HDLmJson.createJsonObject();
	  HDLmJson.addStringToJsonObject(firstMessageObject, "role", "system");
	  HDLmJson.addStringToJsonObject(firstMessageObject, "content", context);
	  HDLmJson.addObjectToJsonArray(messageArray, firstMessageObject);
	  JsonObject  secondMessageObject = HDLmJson.createJsonObject();
	  HDLmJson.addStringToJsonObject(secondMessageObject, "role", "user");
	  HDLmJson.addStringToJsonObject(secondMessageObject, "content", goal);  
	  HDLmJson.addObjectToJsonArray(messageArray, secondMessageObject);
	  /* Add the JSON array of messages to the parent JSON object */
	  HDLmJson.addArrayToJsonObject(parentJsonObject, "messages", messageArray);		    
	  /* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
		String   dataJson = gsonInstance.toJson(parentJsonObject);	
		/* Return the final JSON string to the caller */
		outResponse.setReturnString(dataJson);
		return outResponse;
	}	
	/* Build a multipart/form-data content type header. The caller must pass the 
     correct boundary value. */ 
  protected static String  buildMultipartFormContentTypeHeader(final String boundaryStr) {
	  /* Check one or more values passed by the caller */
	  if (boundaryStr == null) {
		  String   errorText = "Boundary string reference passed to buildMultipartFormContentTypeHeader is null";
		  throw new NullPointerException(errorText);			
	  }
    String  outStr = "";
    outStr += HDLmOpenRouter.buildContentTypeHeader("multipart/form-data; boundary=");
    outStr += boundaryStr;
    return outStr;
  }	  
	/* Execute an Open Router API request. The request could really
	   be anything. The caller provides the body which defines
	   the actual request. */
	protected static HDLmResponse  executeOpenRouterRequestWebpageImprover(String bodyStr) {
		/* Check one or more values passed by the caller */
		if (bodyStr == null) {
			String   errorText = "Body string reference passed to executeOpenRouterRequestWebPageImprover is null";
			throw new NullPointerException(errorText);
		}
		/* Get the Open Router model from the configuration values */
		String  apiModel = HDLmConfigInfo.getOpenRouterApiModel();
		/* Replace the dummy model string with the actual model string */
		bodyStr = bodyStr.replace("dummyModel", apiModel);
		/* Build a response object that can be returned to the caller */
		HDLmResponse  executeResponse = new HDLmResponse();
		/* Build a header list for use later */
		HDLmApiKey  apiKeyEnum = HDLmApiKey.APIKEYOPENROUTERWEBPAGEIMPROVER;
		ArrayList<String>   headerList = HDLmOpenRouter.buildHeaders(apiKeyEnum);
		/* Try to get some Open Router data */
		HDLmResponse  getResponse = getSomeOpenRouterData("api/v1/chat/completions", headerList, bodyStr);
		/* Get some values from the response */
		String  errorMessage = getResponse.getErrorMessage();
		String  outputJson = getResponse.getReturnString();
		/* Check if we got an error message from the low-level AI routine */
		if (errorMessage != null) {
			executeResponse.setErrorMessage(errorMessage);
			return executeResponse;
		}
		/* Check if obtained any output JSON from the Open Router API */
		if (outputJson == null) {
			errorMessage = "Output JSON from Open Router API is null";
			executeResponse.setErrorMessage(errorMessage);
			return executeResponse;
		}
		/* Store the output JSON in the response that will be returned to the caller */
		executeResponse.setReturnCodeZero();
		executeResponse.setReturnString(outputJson);
		return executeResponse;
	}
	/* Execute an Open Router API request. The request could really
	   be anything. The caller provides the body which defines
	   the actual request. */
	protected static HDLmResponse  executeOpenRouterRequestWebpageOptimizer(String bodyStr) {
		/* Check one or more values passed by the caller */
		if (bodyStr == null) {
			String   errorText = "Body string reference passed to executeOpenRouterRequestWebPageOptimizer is null";
			throw new NullPointerException(errorText);
		}
		/* Get the Open Router model from the configuration values */
		String  apiModel = HDLmConfigInfo.getOpenRouterApiModel();
		/* Replace the dummy model string with the actual model string */
		bodyStr = bodyStr.replace("dummyModel", apiModel);
		/* Build a response object that can be returned to the caller */
		HDLmResponse  executeResponse = new HDLmResponse();
		/* Build a header list for use later */
		HDLmApiKey  apiKeyEnum = HDLmApiKey.APIKEYOPENROUTERWEBPAGEIMPROVER;
		ArrayList<String>   headerList = HDLmOpenRouter.buildHeaders(apiKeyEnum);
		/* Show the current timestamp for debugging purposes. */
		String  curTmsp = Instant.now().toString();
		LOG.info("Starting timestamp " + curTmsp); 
		/* Try to get some Open Router data */
		HDLmResponse  getResponse = getSomeOpenRouterData("api/v1/chat/completions", headerList, bodyStr);
		/* Show the current timestamp for debugging purposes. */
		curTmsp = Instant.now().toString();
		LOG.info("Ending timestamp " + curTmsp); 
		/* Get some values from the response */
		String  errorMessage = getResponse.getErrorMessage();
		String  outputJson = getResponse.getReturnString();
		/* Check if we got an error message from the low-level AI routine */
		if (errorMessage != null) {
			executeResponse.setErrorMessage(errorMessage);
			return executeResponse;
		}
		/* Check if obtained any output JSON from the Open Router API */
		if (outputJson == null) {
			errorMessage = "Output JSON from Open Router API is null";
			executeResponse.setErrorMessage(errorMessage);
			return executeResponse;
		}
		/* Store the output JSON in the response that will be returned to the caller */
		executeResponse.setReturnCodeZero();
		executeResponse.setReturnString(outputJson);
		return executeResponse;
	}
	/* Execute the full webpage improver flow. The first request returns an array
	   of improvements with why and what. Each what value is then converted into
	   a markup object that contains scripts and styles arrays. */
	protected static HDLmResponse  executeWebpageImproverRequest(final String bodyStr) {
		/* Check one or more values passed by the caller */
		if (bodyStr == null) {
			String   errorText = "Body string reference passed to executeWebpageImproverRequest is null";
			throw new NullPointerException(errorText);
		}
		HDLmResponse  outputResponse = new HDLmResponse();
		HDLmResponse  initialResponse = HDLmOpenRouter.executeOpenRouterRequestWebpageImprover(bodyStr);
		String        errorMessage = initialResponse.getErrorMessage();
		if (errorMessage != null) {
			outputResponse.setErrorMessage(errorMessage);
			return outputResponse;
		}
		String  outputJsonStr = initialResponse.getReturnString();
		if (outputJsonStr == null) {
			errorMessage = "Initial webpage improver response is missing output JSON";
			outputResponse.setErrorMessage(errorMessage);
			return outputResponse;
		}
		/* Convert the output JSON string into an JSON object.
		   Start by using the JSON parser that was created at 
		   startup. */ 
	  JsonParser    localParser = HDLmMain.gsonJsonParserMain;  
 	  /* Make sure the inbound payload has the required key */
	  JsonElement   outputJsonElement = localParser.parse(outputJsonStr);
	  /* Check if the JSON string is valid or not */
	 	if (!outputJsonElement.isJsonObject()) {
		  String  errorText = "JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageImprover in executeWebpageImproverRequest is invalid";
		  HDLmAssertAction(false, errorText);
	  }
	 	/* Get the 'choices' array from the output JSON
	 	   as a JSON array. It should always have a size 
	 	   of one.*/ 
	 	JsonArray   choicesArray = HDLmJson.getJsonArray(outputJsonElement, "choices"); 
	 	int         choicesSize = choicesArray.size();
		if (choicesSize <= 0) {
			String errorText = "Choices array is empty in JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageImprover in executeWebpageImproverRequest";
			HDLmAssertAction(false, errorText);
		}
		if (choicesSize != 1) {
			String  errorFormat = "Choices array size is wrong (%d) in JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageImprover in executeWebpageImproverRequest";
			String  errorText = String.format(errorFormat, choicesSize);
			HDLmAssertAction(false, errorText);
		}
		/* Get the first choice object from the choices array */
		JsonElement   firstChoiceElement = choicesArray.get(0);
		if (!firstChoiceElement.isJsonObject()) {
			String errorText = "First choice element is not a JSON object in JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageImprover in executeWebpageImproverRequest";
			HDLmAssertAction(false, errorText);
		}
		/* Get the message object from the first choice object */
		JsonObject  firstChoiceObject = firstChoiceElement.getAsJsonObject();
		JsonObject  messageObject = HDLmJson.getJsonObject(firstChoiceObject, "message");
		/* Get the content object from the message object */
		String        contentStr = HDLmJson.getJsonString(messageObject, "content");
		JsonElement   contentElement = localParser.parse(contentStr); 
		/* Get the 'items' array from the content object. An 'item'
		   is created for each improvement. */
	 	JsonArray   itemsArray = HDLmJson.getJsonArray(contentElement, "items"); 
	 	int         itemsSize = itemsArray.size();
		if (itemsSize <= 0) {
			String errorText = "Items array is empty in JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageImprover in executeWebpageImproverRequest";
			HDLmAssertAction(false, errorText);
		}		 
	  /* Check each item/improvement */
		for (int i = 0; i < itemsSize; i++) {
			JsonElement  itemElement = itemsArray.get(i);
			if (!itemElement.isJsonObject()) {
				errorMessage = "A webpage improver entry is not a JSON object";
				outputResponse.setErrorMessage(errorMessage);
				return outputResponse;
			}
			/* Get and check the 'why' string */
			String  whyStr = HDLmOpenRouter.getRequiredJsonString(itemElement,
					                                                  "why",
					                                                  "webpage improver improvement");
			if (whyStr == null) {
				errorMessage = "A webpage improver improvement is missing a why value";
				outputResponse.setErrorMessage(errorMessage);
				return outputResponse;
			}
			/* Get an check the 'what' string */
			String  whatStr = HDLmOpenRouter.getRequiredJsonString(itemElement,
					                                                   "what",
					                                                   "webpage improver improvement");
			if (whatStr == null) {
				errorMessage = "A webpage improver improvement is missing a what value";
				outputResponse.setErrorMessage(errorMessage);
				return outputResponse;
			}
			/* The improvement appears not to have any markup. This check
			   is skipped. */ 
			if (1 == 2) { 
				/* Get and check the markup. The markup should actually be 
				   an object. */
				JsonObject  markupObject = HDLmJson.getJsonObject(itemElement, "markup");
				if (markupObject == null) {
					errorMessage = "A webpage improver improvement is missing markup";
					outputResponse.setErrorMessage(errorMessage);
					return outputResponse;
				}
				if (!markupObject.isJsonObject()) {
					errorMessage = "A webpage improver markup object is not a JSON object";
					outputResponse.setErrorMessage(errorMessage);
					return outputResponse;
				}
			}
		}	
	  /* Build the final string that is sent back to the 
	     client */ 
		JsonObject  outputObject = HDLmJson.createJsonObject();
		HDLmJson.addArrayToJsonObject(outputObject, "improvements", itemsArray);
		Gson    gsonInstance = HDLmMain.gsonMain;
		String  normalizedPayload = gsonInstance.toJson(outputObject);
		outputResponse.setReturnCodeZero();
		outputResponse.setReturnString(normalizedPayload);
		return outputResponse; 
	}
	/* Execute the full webpage optimizer flow */
	protected static HDLmResponse  executeWebpageOptimizerRequest(final String bodyStr) {
		/* Check one or more values passed by the caller */
		if (bodyStr == null) {
			String   errorText = "Body string reference passed to executeWebpageOptimizerRequest is null";
			throw new NullPointerException(errorText);
		}
		HDLmResponse  outputResponse = new HDLmResponse();
		HDLmResponse  initialResponse = HDLmOpenRouter.executeOpenRouterRequestWebpageOptimizer(bodyStr);
		String        errorMessage = initialResponse.getErrorMessage();
		if (errorMessage != null) {
			outputResponse.setErrorMessage(errorMessage);
			return outputResponse;
		}
		String  outputJsonStr = initialResponse.getReturnString();
		if (outputJsonStr == null) {
			errorMessage = "Initial webpage optimizer response is missing output JSON";
			outputResponse.setErrorMessage(errorMessage);
			return outputResponse;
		}
		/* Convert the output JSON string into an JSON object.
		   Start by using the JSON parser that was created at 
		   startup. */ 
	  JsonParser    localParser = HDLmMain.gsonJsonParserMain;  
	  /* Make sure the inbound payload has the required key */
	  JsonElement   outputJsonElement = localParser.parse(outputJsonStr);
	  /* Check if the JSON string is valid or not */
		if (!outputJsonElement.isJsonObject()) {
		  String  errorText = "JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageOptimizer in executeWebpageOptimizerRequest is invalid";
		  HDLmAssertAction(false, errorText);
	  }
		/* Get the 'choices' array from the output JSON
		   as a JSON array. It should always have a size 
		   of one.*/ 
		JsonArray   choicesArray = HDLmJson.getJsonArray(outputJsonElement, "choices"); 
		int         choicesSize = choicesArray.size();
		if (choicesSize <= 0) {
			String errorText = "Choices array is empty in JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageOptimizer in executeWebpageOptimizerRequest";
			HDLmAssertAction(false, errorText);
		}
		if (choicesSize != 1) {
			String  errorFormat = "Choices array size is wrong (%d) in JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageOptimizer in executeWebpageOptimizerRequest";
			String  errorText = String.format(errorFormat, choicesSize);
			HDLmAssertAction(false, errorText);
		}
		/* Get the first choice object from the choices array */
		JsonElement   firstChoiceElement = choicesArray.get(0);
		if (!firstChoiceElement.isJsonObject()) {
			String errorText = "First choice element is not a JSON object in JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageOptimizer in executeWebpageOptimizerRequest";
			HDLmAssertAction(false, errorText);
		}
		/* Get the message object from the first choice object */
		JsonObject  firstChoiceObject = firstChoiceElement.getAsJsonObject();
		JsonObject  messageObject = HDLmJson.getJsonObject(firstChoiceObject, "message");
		/* Get the content object from the message object */
		String        contentStr = HDLmJson.getJsonString(messageObject, "content");
		JsonElement   contentElement = localParser.parse(contentStr); 
		/* Get the 'items' array from the content object */
		JsonArray   itemsArray = HDLmJson.getJsonArray(contentElement, "items"); 
		int         itemsSize = itemsArray.size();
		if (itemsSize <= 0) {
			String errorText = "Items array is empty in JSON string from HDLmOpenRouter.executeOpenRouterRequestWebPageOptimizer in executeWebpageOptimizerRequest";
			HDLmAssertAction(false, errorText);
		}		 
	 /* Check each item */
		for (int i = 0; i < itemsSize; i++) {
			JsonElement  itemElement = itemsArray.get(i);
			if (!itemElement.isJsonObject()) {
				errorMessage = "A webpage optimizer entry is not a JSON object";
				outputResponse.setErrorMessage(errorMessage);
				return outputResponse;
			}
			/* Get and check the 'why' string */
			String  whyStr = HDLmOpenRouter.getRequiredJsonString(itemElement,
					                                                  "why",
					                                                  "webpage optimizer");
			if (whyStr == null) {
				errorMessage = "A webpage optimizer is missing a why value";
				outputResponse.setErrorMessage(errorMessage);
				return outputResponse;
			}
			/* Get an check the 'what' string */
			String  whatStr = HDLmOpenRouter.getRequiredJsonString(itemElement,
					                                                   "what",
					                                                   "webpage optimizer");
			if (whatStr == null) {
				errorMessage = "A webpage optimization is missing a what value";
				outputResponse.setErrorMessage(errorMessage);
				return outputResponse;
			}
			/* Get and check the markup. The markup should actually be 
			   an object. */
			JsonObject  markupObject = HDLmJson.getJsonObject(itemElement, "markup");
			if (markupObject == null) {
				errorMessage = "A webpage optimizer optimization is missing markup";
				outputResponse.setErrorMessage(errorMessage);
				return outputResponse;
			}
			if (!markupObject.isJsonObject()) {
				errorMessage = "A webpage optimizer markup object is not a JSON object";
				outputResponse.setErrorMessage(errorMessage);
				return outputResponse;
			}
		}	
	  /* Build the final string that is sent back to the 
	     client */ 
		JsonObject  outputObject = HDLmJson.createJsonObject();
		HDLmJson.addArrayToJsonObject(outputObject, "optimizations", itemsArray);
		Gson    gsonInstance = HDLmMain.gsonMain;
		String  normalizedPayload = gsonInstance.toJson(outputObject);
		outputResponse.setReturnCodeZero();
		outputResponse.setReturnString(normalizedPayload);
		return outputResponse; 
	}
	/* Extract the message content string from an Open Router response. */
	protected static HDLmResponse  getOpenRouterMessageContent(final String outputJson,
			                                                       final String methodName) {
		/* Check one or more values passed by the caller */
		if (outputJson == null) {
			String   errorText = "Output JSON string reference passed to getOpenRouterMessageContent is null";
			throw new NullPointerException(errorText);
		}
		if (methodName == null) {
			String   errorText = "Method name string reference passed to getOpenRouterMessageContent is null";
			throw new NullPointerException(errorText);
		}
		HDLmResponse  outputResponse = new HDLmResponse();
		JsonParser    parser = HDLmMain.gsonJsonParserMain;
		JsonElement   topNode = parser.parse(outputJson);
		if (!topNode.isJsonObject()) {
			String  errorFormat = "Top node is not a JSON object value in %s";
			String  errorText = String.format(errorFormat, methodName);
			outputResponse.setErrorMessage(errorText);
			return outputResponse;
		}
		if (!HDLmJson.hasJsonKey(topNode, "choices")) {
			String  errorText = HDLmOpenRouter.getApiErrorMessageFromOutputJson(outputJson);
			if (errorText == null ||
					errorText.length() == 0) {
				String  errorFormat = "Choices array is missing from output JSON in %s";
				errorText = String.format(errorFormat, methodName);
			}
			outputResponse.setErrorMessage(errorText);
			return outputResponse;
		}
		JsonArray  choicesArray = HDLmJson.getJsonArray(topNode, "choices");
		if (choicesArray == null ||
				choicesArray.size() <= 0) {
			String  errorFormat = "Choices array is empty in %s";
			String  errorText = String.format(errorFormat, methodName);
			outputResponse.setErrorMessage(errorText);
			return outputResponse;
		}
		JsonElement  firstChoiceElement = choicesArray.get(0);
		if (!firstChoiceElement.isJsonObject()) {
			String  errorFormat = "First choice is not a JSON object in %s";
			String  errorText = String.format(errorFormat, methodName);
			outputResponse.setErrorMessage(errorText);
			return outputResponse;
		}
		JsonObject  messageObject = HDLmJson.getJsonObject(firstChoiceElement, "message");
		if (messageObject == null) {
			String  errorFormat = "Message object is missing from first choice in %s";
			String  errorText = String.format(errorFormat, methodName);
			outputResponse.setErrorMessage(errorText);
			return outputResponse;
		}
		String  contentStr = HDLmJson.getJsonString(messageObject, "content");
		if (contentStr == null) {
			String  errorFormat = "Message content is missing from first choice in %s";
			String  errorText = String.format(errorFormat, methodName);
			outputResponse.setErrorMessage(errorText);
			return outputResponse;
		}
		contentStr = HDLmOpenRouter.removeJsonFences(contentStr);
		outputResponse.setReturnCodeZero();
		outputResponse.setReturnString(contentStr);
		return outputResponse;
	}
	/* Get a required string field from a JSON object-like element. */
	protected static String  getRequiredJsonString(final JsonElement jsonElement,
			                                           final String key,
			                                           final String contextStr) {
		/* Check one or more values passed by the caller */
		if (jsonElement == null) {
			String   errorText = "JSON element reference passed to getRequiredJsonString is null";
			throw new NullPointerException(errorText);
		}
		if (key == null) {
			String   errorText = "Key string reference passed to getRequiredJsonString is null";
			throw new NullPointerException(errorText);
		}
		if (contextStr == null) {
			String   errorText = "Context string reference passed to getRequiredJsonString is null";
			throw new NullPointerException(errorText);
		}
		if (!HDLmJson.hasJsonKey(jsonElement, key)) {
			return null;
		}
		String  valueStr = HDLmJson.getJsonString(jsonElement, key);
		if (valueStr == null ||
				valueStr.length() == 0) {
			return null;
		}
		return valueStr;
	}
	/* Remove fenced JSON markers from a message content string. */
	protected static String  removeJsonFences(final String contentStr) {
		/* Check one or more values passed by the caller */
		if (contentStr == null) {
			String   errorText = "Content string reference passed to removeJsonFences is null";
			throw new NullPointerException(errorText);
		}
		String  outputStr = contentStr.trim();
		if (outputStr.startsWith("```json") &&
				outputStr.endsWith("```")) {
			outputStr = outputStr.substring(7, outputStr.length() - 3).trim();
		}
		else if (outputStr.startsWith("```") &&
			    	 outputStr.endsWith("```")) {
			outputStr = outputStr.substring(3, outputStr.length() - 3).trim();
		}
		return outputStr;
	}
	/* Get some image choices using the Open Router API */
	protected static HDLmResponse  getImageChoices(final String inputImageStr, 
			                                           final String urlStr, 
			                                           final String pathValueStr,
			                                           final HDLmApiKey apiKeyEnum) {
		/* Check one or more values passed by the caller */
		/* Check if the image URL is null or not */
		if (inputImageStr == null) {
			String   errorText = "Input image URL reference passed to getImageChoices is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the web page URL string is null */ 
		if (urlStr == null) {
			String   errorText = "Web page URL string reference passed to getImageChoices is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the web page path value string is null */ 
		if (pathValueStr == null) {
			String   errorText = "Web page Path value string reference passed to getImageChoices is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the API key enum value is null */
		if (apiKeyEnum == null) {
			String errorText = "API key enum reference passed to getImageChoices is null";
			throw new NullPointerException(errorText);
		}
		/* The API key enum must be a valid value */  
		if (apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERTEST   &&
				apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERVSCODE &&  
				apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERWEBPAGEIMPROVER) { 
			String   errorFormat = "API key enum value (%s) passed to getImageChoices is invalid";
			String   errorText = String.format(errorFormat, apiKeyEnum.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Build a response object that can be returned to the caller */
		HDLmResponse  outResponse = new HDLmResponse();
	  /* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenRouter.buildHeaders(apiKeyEnum);
		/* Build some JSON data from the image input */
		String  jsonData = HDLmOpenRouter.buildJsonImageVariations(inputImageStr);
	  /* Try to get some image choices/variants */
	  HDLmResponse  getResponse = getSomeOpenRouterImageData("v1/chat/completions", headerList, jsonData);
	  /* Get some values from the response */
	  String  errorMessage = getResponse.getErrorMessage();
	  String  outputJson = getResponse.getReturnString();
	  /* Check if we got an error message from the low-level Ai routine */
	  if (errorMessage != null) {
	  	outResponse.setErrorMessage(errorMessage);
	  	return outResponse;	  	
	  }	  
	  /* Check if the Open Router APi returned a null JSON string */  
	  if (outputJson == null) {
	  	errorMessage = "Output JSON from Open Router API is null";
	  	outResponse.setErrorMessage(errorMessage);
	  	return outResponse;
	  } 
	  /* Try to get each of the choices/variants from the Open Router output */
	  ArrayList<String>   variantsList = HDLmOpenRouter.getImageVariations(outputJson); 
	  if (variantsList.size() == 0) {
	  	errorMessage = HDLmOpenRouter.getApiErrorMessageFromOutputJson(outputJson);
	  	if (errorMessage == null || 
				  errorMessage.length() == 0) {
	  		errorMessage = "Open Router image response did not include image URLs";
	  	}
	  	outResponse.setErrorMessage(errorMessage);
	  	return outResponse;
	  }
	  /* Add the image variants list to the output response */
	  outResponse.setStringList(variantsList); 
	  return outResponse;
	}
	/* Get a list of image variations from some output (output of Open Router) JSON */
	protected static ArrayList<String>  getImageVariations(final String outputJson) {
		/* Check one or more values passed by the caller */
		if (outputJson == null) {
			String   errorText = "Output JSON string reference passed to getImageVariations is null";
			throw new NullPointerException(errorText);			
		}
	  /* Try to get each of the choices/variants from the Open Router output */
	  ArrayList<String>   imageVariantsList = new ArrayList<String>();  
	  /* Get the image variants from the Open Router output */
    JsonParser   parser = HDLmMain.gsonJsonParserMain;  
	  JsonElement  topNode = parser.parse(outputJson);
		/* Check if the JSON element is not a JSON object value */
		if (!topNode.isJsonObject()) {
			HDLmAssertAction(false, "Top node is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = HDLmJson.getJsonKeys(topNode);
		JsonObject    topNodeObject = topNode.getAsJsonObject();
		/* Handle OpenAI-style image output: data[].url */
		if (jsonKeys.contains("data")) {
			String       key = "data";
			JsonElement  dataElement = topNodeObject.get(key);
			if (dataElement.isJsonArray()) {
				JsonArray  dataArray = (JsonArray) dataElement;
				int        dataArraySize = dataArray.size();
				for (int i = 0; i < dataArraySize; i++) {
					JsonElement   dataArrayElement = dataArray.get(i);
					if (!dataArrayElement.isJsonObject()) {
						continue;
					}
					Set<String>  dataKeys = HDLmJson.getJsonKeys(dataArrayElement);
					if (!dataKeys.contains("url")) {
						continue;
					}
					String  imageStr = HDLmJson.getJsonString(dataArrayElement, "url");
					imageVariantsList.add(imageStr);
				}
			}
		}
		/* Handle chat completion image output: choices[].message.images[].image_url.url */
		if (jsonKeys.contains("choices")) {
			JsonElement  choicesElement = topNodeObject.get("choices");
			if (choicesElement != null && 
				  choicesElement.isJsonArray()) {
				JsonArray  choicesArray = (JsonArray) choicesElement;
				int        choicesArraySize = choicesArray.size();
				for (int i = 0; i < choicesArraySize; i++) {
					JsonElement  choiceElement = choicesArray.get(i);
					if (!choiceElement.isJsonObject()) {
						continue;
					}
					Set<String>  choiceKeys = HDLmJson.getJsonKeys(choiceElement);
					if (!choiceKeys.contains("message")) {
						continue;
					}
					JsonObject  messageObject = HDLmJson.getJsonObject(choiceElement, "message");
					Set<String> messageKeys = HDLmJson.getJsonKeys(messageObject);
					if (!messageKeys.contains("images")) {
						continue;
					}
					JsonElement  imagesElement = messageObject.get("images");
					if (imagesElement == null || 
						  !imagesElement.isJsonArray()) {
						continue;
					}
					JsonArray  imagesArray = (JsonArray) imagesElement;
					int        imagesArraySize = imagesArray.size();
					for (int j = 0; j < imagesArraySize; j++) {
						JsonElement  imageElement = imagesArray.get(j);
						if (!imageElement.isJsonObject()) {
							continue;
						}
						Set<String> imageKeys = HDLmJson.getJsonKeys(imageElement);
						if (!imageKeys.contains("image_url")) {
							continue;
						}
						JsonObject   imageUrlObject = HDLmJson.getJsonObject(imageElement, "image_url");
						Set<String>  imageUrlKeys = HDLmJson.getJsonKeys(imageUrlObject);
						if (!imageUrlKeys.contains("url")) {
							continue;
						}
						String  imageUrl = HDLmJson.getJsonString(imageUrlObject, "url");
						imageVariantsList.add(imageUrl);
					}
				}
			}
		}
		/* Return the image variations list to the caller */
		return imageVariantsList;
	}
	/* Get some Open Router data. It is assumed that the string
	   passed to this routine is already in JSON format. */
	protected static HDLmResponse  getSomeOpenRouterData(final String pathStr,
			                                                 final ArrayList<String> headerList,
			                                                 final String jsonData) {
		/* Check a few values passed by the caller */
		if (pathStr == null) {
			String   errorText = "Path string reference passed to getSomeOpenRouterData is null";
			throw new NullPointerException(errorText);
		}
		if (headerList == null) {
			String   errorText = "Header list reference passed to getSomeOpenRouterData is null";
			throw new NullPointerException(errorText);
		}
		if (jsonData == null) {
			String   errorText = "Json data reference passed to getSomeOpenRouterData is null";
			throw new NullPointerException(errorText);
		}
		/* Build a response object that can be returned to the caller */
		HDLmResponse  outResponse = new HDLmResponse();
		/* Build the URL string passed to the server */
		String  protocol = "https";
		String  hostName = "openrouter.ai";
		String  pathValue = pathStr;
		String  urlString = protocol + "://" + hostName + '/' + pathValue;
		/* Send the request to the Open Router server */
		/* The callers passes the headers and the JSON */
		/* Use the Curl mechanism */
		HDLmApacheResponse  apacheResponse;
		apacheResponse = HDLmCurlApache.runCurlResponse(urlString,
																										"",
																										"",
																										HDLmHttpTypes.POST,
																										headerList,
																										jsonData,
																										null,
																										HDLmOutboundJson.OUTBOUNDJSONYES,
																										HDLmSkipAuth.SKIPAUTHYES,
																										HDLmReportErrors.REPORTERRORS);
		/* Check the response from the Apache HTTP routine */
		int     statusCode = apacheResponse.getStatusCode();
		String  contentString = apacheResponse.getStringContent();
		String  errorMessage = apacheResponse.getErrorMessage();
		String  statusLine = apacheResponse.getStatusLine();
		/* Store some values in the response that this routine will
		   return */
		if (errorMessage != null)
			outResponse.setErrorMessage(errorMessage);
		else if (statusLine != null && 
			       statusCode != HttpStatus.SC_OK)
			outResponse.setErrorMessage(statusLine);
		else if (contentString != null)
			outResponse.setReturnString(contentString);
		return outResponse;
	}
	/* Get some Open Router image data. It is assumed that the string
	   passed to this routine is already in JSON format. */
	protected static HDLmResponse  getSomeOpenRouterImageData(final String pathStr, 
			                                                      final ArrayList<String> headerList,
			                                                      final String jsonData) {
		/* Check a few values passed by the caller */
		if (pathStr == null) {
			String   errorText = "Path string reference passed to getSomeOpenRouterImageData is null";
			throw new NullPointerException(errorText);			
		}
		if (headerList == null) {
			String   errorText = "Header list reference passed to getSomeOpenRouterImageData is null";
			throw new NullPointerException(errorText);		
		}
		if (jsonData == null) {
			String   errorText = "Json data reference passed to getSomeOpenRouterImageData is null";
			throw new NullPointerException(errorText);		
		}
		/* Build a response object that can be returned to the caller */
		HDLmResponse  outResponse = new HDLmResponse();
		/* Build the URL string passed to the server */
	  String  protocol = "https";
	  String  hostName = "openrouter.ai";
	  String  pathValue = pathStr;
	  String  urlString = protocol + "://" + hostName + "/api/" + pathValue; 
		/* Send the request to the Open router server */
	  /* The callers passes the headers and the JSON */
	  /* Use the Curl mechanism */
		HDLmApacheResponse  apacheResponse;;
	  apacheResponse = HDLmCurlApache.runCurlResponse(urlString,
								                                    "",
								                                    "", 
								                                    HDLmHttpTypes.POST,  
								                                    headerList,
								                                    jsonData,
								  					                        null,
								  					                        HDLmOutboundJson.OUTBOUNDJSONYES,
								                                    HDLmSkipAuth.SKIPAUTHYES,
								                                    HDLmReportErrors.REPORTERRORS);																					        
	  /* Check the response from the Apache HTTP routine */
	  int     statusCode = apacheResponse.getStatusCode();
	  String  contentString = apacheResponse.getStringContent();
	  String  errorMessage = apacheResponse.getErrorMessage();
	  String  statusLine = apacheResponse.getStatusLine(); 
	  /* Store some values in the response that this routine will
	     return */
	  if (errorMessage != null) 
	    outResponse.setErrorMessage(errorMessage);
	  else if (statusLine != null && 
			       statusCode != HttpStatus.SC_OK)
	 	  outResponse.setErrorMessage(statusLine);
	  else if (contentString != null)
	 	  outResponse.setReturnString(contentString);
		return outResponse;
	}
	/* Try to extract a provider/API error message from Open Router output JSON. */
	protected static String  getApiErrorMessageFromOutputJson(final String outputJson) {
		if (outputJson == null) {
			String   errorText = "Output JSON string reference passed to getApiErrorMessageFromOutputJson is null";
			throw new NullPointerException(errorText);
		}
		JsonParser   parser = HDLmMain.gsonJsonParserMain;
		JsonElement  topNode = parser.parse(outputJson);
		if (!topNode.isJsonObject()) {
			return null;
		}
		JsonObject  topNodeObject = topNode.getAsJsonObject();
		JsonElement errorElement = topNodeObject.get("error");
		if (errorElement == null || 
			  !errorElement.isJsonObject()) {
			return null;
		}
		JsonObject   errorObject = errorElement.getAsJsonObject();
		JsonElement  messageElement = errorObject.get("message");
		if (messageElement == null || 
			  !messageElement.isJsonPrimitive()) {
			return null;
		}
		return messageElement.getAsString();
	}
	/* Get some Open Router text data. It is assumed that the string
	   passed to this routine is already in JSON format. */
	protected static HDLmResponse  getSomeOpenRouterTextData(final String pathStr, 
			                                                     final ArrayList<String> headerList,
			                                                     final String jsonData) {
		/* Check a few values passed by the caller */
		if (pathStr == null) {
			String   errorText = "Path string reference passed to getSomeOpenRouterTextData is null";
			throw new NullPointerException(errorText);			
		}
		if (headerList == null) {
			String   errorText = "Header list reference passed to getSomeOpenRouterTextData is null";
			throw new NullPointerException(errorText);		
		}
		if (jsonData == null) {
			String   errorText = "Json data reference passed to getSomeOpenRouterTextData is null";
			throw new NullPointerException(errorText);		
		}
		/* Build a response object that can be returned to the caller */
		HDLmResponse  outResponse = new HDLmResponse();
		/* Build the URL string passed to the server */
 	  String  protocol = "https";
	  String  hostName = "openrouter.ai";
	  String  pathValue = pathStr;
	  String  urlString = protocol + "://" + hostName + "/api/" + pathValue; 
		/* Send the request to the Open Router server */
	  /* The callers passes the headers and the SBON */
	  /* Use the Curl mechanism */
		HDLmApacheResponse  apacheResponse;
	  apacheResponse = HDLmCurlApache.runCurlResponse(urlString,
							  		                                "", 
							  		                                "", 
							  		                                HDLmHttpTypes.POST,
							  		                                headerList,
							  		                                jsonData,
							  		                                null,  
																				            HDLmOutboundJson.OUTBOUNDJSONYES,
																					          HDLmSkipAuth.SKIPAUTHYES,
																					          HDLmReportErrors.REPORTERRORS); 
	  /* Check the response from the Apache HTTP routine */
	  int     statusCode = apacheResponse.getStatusCode();
	  String  contentString = apacheResponse.getStringContent();
	  String  errorMessage = apacheResponse.getErrorMessage();
	  String  statusLine = apacheResponse.getStatusLine(); 
	  /* Store some values in the response that this routine will
	     return */
	  if (errorMessage != null) 
	    outResponse.setErrorMessage(errorMessage);
	  else if (statusLine != null && 
			       statusCode != HttpStatus.SC_OK)
	 	  outResponse.setErrorMessage(statusLine);
	  else if (contentString != null)
	 	  outResponse.setReturnString(contentString);
		return outResponse;
	}
	/* Change the input image size. Get a square output image. */
	public static BufferedImage  getSquareImage(final BufferedImage inputBufferedImage) {
		/* Check one or more values passed by the caller */
		if (inputBufferedImage == null) {
			String   errorText = "Buffered image reference passed to getSquareImage is null";
			throw new NullPointerException(errorText);			
		}
		/* Get the input image height and width */
		int   imageWidth = inputBufferedImage.getWidth();
		int   imageHeight = inputBufferedImage.getHeight();
		int   imageMax = Math.max(imageWidth, imageHeight); 
    /* Create a new square image */
    BufferedImage   outputImage = new BufferedImage(imageMax, 
                                                		imageMax,
    		                                            BufferedImage.TYPE_4BYTE_ABGR);
    /* Get the graphics object to draw onto the image */
    Graphics  graphics = outputImage.getGraphics();
    /* This is a transparent color */
    Color transparent = new Color(0f,0f,0f,0f);
    /* Set the transparent color as drawing color */
    graphics.setColor(transparent);
    /* Make the whole image transparent */
    graphics.fillRect(0, 0, imageMax, imageMax);
    /* Draw the input image with transparent margins */
    int   imageXLocation = 0;
    int   imageYLocation = 0;
    if (imageWidth < imageMax)
    	imageXLocation = (imageMax - imageWidth) >> 1;
    if (imageHeight < imageMax)
    	imageYLocation = (imageMax - imageHeight) >> 1;
    graphics.drawImage(inputBufferedImage, 
    		               imageXLocation, 
    		               imageYLocation, 
    		               null);
    /* Release the Graphics object */
    graphics.dispose();
    /* Return the new image */
    return outputImage;
  }
	/* Get some text choices using the Open Router API */
	protected static HDLmResponse  getTextChoices(final String inputStr, 
			                                          final String urlStr, 
			                                          final String pathValueStr,
			                                          final HDLmApiKey apiKeyEnum) {
		/* Check one or more values passed by the caller */
		if (inputStr == null) {
			String   errorText = "Input string reference passed to getTextChoices is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the URL string is null */ 
		if (urlStr == null) {
			String   errorText = "URL string reference passed to getTextChoices is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the path value string is null */ 
		if (pathValueStr == null) {
			String   errorText = "Path value string reference passed to getTextChoices is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the API key enum value is null */
		if (apiKeyEnum == null) {
			String errorText = "API key enum reference passed to getTextChoices is null";
			throw new NullPointerException(errorText);
		}
		/* The API key enum must be a valid value */  
		if (apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERTEST   &&
				apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERVSCODE &&  
				apiKeyEnum != HDLmApiKey.APIKEYOPENROUTERWEBPAGEIMPROVER) { 
			String   errorFormat = "API key enum value (%s) passed to getTextChoices is invalid";
			String   errorText = String.format(errorFormat, apiKeyEnum.toString());
			HDLmAssertAction(false, errorText);			
		}
		/* Build a response object that can be returned to the caller */
		HDLmResponse  getTextResponse = new HDLmResponse();
	  /* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenRouter.buildHeaders(apiKeyEnum);
		/* Build some JSON for use below */
		HDLmResponse  buildResponse = HDLmOpenRouter.buildJsonTextVariations(inputStr, urlStr, pathValueStr);
		String   dataJson = buildResponse.getReturnString();
	  /* Try to get some text choices/variants */
	  HDLmResponse  getResponse = getSomeOpenRouterTextData("v1/chat/completions", headerList, dataJson);
	  /* Get some values from the response */
	  String  errorMessage = getResponse.getErrorMessage();
	  String  outputJson = getResponse.getReturnString();
	  /* Check if we got an error message from the low-level Ai routine */
	  if (errorMessage != null) {
	  	getTextResponse.setErrorMessage(errorMessage);
	  	return getTextResponse;	  	
	  }	  
    /* Check if obtained any output JSON from the Open Router API */ 
	  if (outputJson == null) {
	  	errorMessage = "Output JSON from Open Router API is null";
	  	getTextResponse.setErrorMessage(errorMessage);
	  	return getTextResponse;
	  }
	  /* Try to get each of the text choices/variants from the Open Router output */
	  ArrayList<String>   choiceList = HDLmOpenRouter.getTextVariations(outputJson);
	  /* Store a reference to the choice list in the response object */
	  getTextResponse.setStringList(choiceList);
		return getTextResponse;
	}
	/* Get a list of text variations from some output (output of Open Router) JSON */
	protected static ArrayList<String>  getTextVariations(final String  outputJson) {
		/* Check one or more values passed by the caller */
		if (outputJson == null) {
			String   errorText = "Output JSON string reference passed to getTextVariations is null";
			throw new NullPointerException(errorText);			
		}
	  /* Try to get each of the choices/variants from the Open Router output */
	  ArrayList<String>   choiceList = new ArrayList<String>();  
	  /* Get the text variants from the Open Router output */
	  JsonParser   parser = HDLmMain.gsonJsonParserMain;  
	  JsonElement  topNode = parser.parse(outputJson);
		/* Check if the JSON element is not a JSON object value */
		if (!topNode.isJsonObject()) {
			HDLmAssertAction(false, "Top node is not a JSON object value in getTextVariations");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = HDLmJson.getJsonKeys(topNode);
		/* Check if we have the field in the JSON */
		String  key = "choices"; 
		assertTrue(jsonKeys.contains(key), "Key is missing from output JSON in getTextVariations");
		/* Get the JSON object from the JSON element */
		JsonObject    topNodeObject = topNode.getAsJsonObject();
		JsonElement   choicesElement = topNodeObject.get(key);
		if (!choicesElement.isJsonArray()) {
			HDLmAssertAction(false, "Choices element is not a JSON array value in getTextVariations");
		}
		/* Get all of the variants and make sure we have at least one */
		JsonArray     choicesArray = (JsonArray) choicesElement;
		int           choicesArraySize = choicesArray.size();
		if (choicesArraySize <= 0) {
			String  errorText = "Choices array in inbound data is not large enough in getTextVariations";
			HDLmAssertAction(false, errorText);	
		}
		/* Get the first element of the array. This should be an object that will have
		   our variants much later */
		JsonObject    firstArrayObject = (JsonObject) choicesArray.get(0);
		if (!firstArrayObject.isJsonObject()) {
			HDLmAssertAction(false, "Choice element is not a JSON object value");
		}
		/* Make sure the JSON object has the message key */
		key = "message";
		/* Get the set of JSON keys from the JSON object */
		jsonKeys = HDLmJson.getJsonKeys(firstArrayObject);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			HDLmAssertAction(false, "First array object does not have message in it");
		}	
		/* Get the message object from the first array element object. 
		   This should be a content object that we will use below. */
	  JsonObject    messageObject = HDLmJson.getJsonObject(firstArrayObject, key);
	  if (!messageObject.isJsonObject()) {
		  HDLmAssertAction(false, "Message value is not a JSON object value");
	  }
		key = "content";
		/* Get the set of JSON keys from the JSON object */
		jsonKeys = HDLmJson.getJsonKeys(messageObject);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			HDLmAssertAction(false, "Message object does not have content in it");
		}	
		/* Get the content string from the message object */
	  String  contentStr = HDLmJson.getJsonString(messageObject, key); 
	  /* We may need to make some changes to the content string here. 
	     For some reason, the API returns different text in some cases.
	     The API does not return the same text structure in all cases. */
	  if (contentStr != null && 
			  contentStr.length() >= 10) {
	  	if (contentStr.startsWith("```json") && 
	  			contentStr.endsWith("```")) {
	  		int   contentStrLength = contentStr.length();
	  		/* In this case, the content string has some newline 
	  		   characters that should also be removed */
	  		contentStr = contentStr.substring(8, contentStrLength-4);	  		
	  	}  	
	  }
	  /* Convert the content string to a JSON object */
	  JsonParser   contentParser = HDLmMain.gsonJsonParserMain;  
	  JsonElement  contentElement = contentParser.parse(contentStr);
	  /* The contentElement might actually be a JSON array at this point.
	     The output comes back in two slightly different formats. We need
	     to be able to handle both formats. In the alternative format, 
	     the content string does not have the word 'variations' in it. */
	  if (contentElement.isJsonArray()) {
			/* Get the variations values array that has the text variations */		
			int   contentsSize = ((JsonArray) contentElement).size();
			/* Process each element in the contents JSON array */
			for (int i = 0; i < contentsSize; i++) {
				JsonElement   variationElement = ((JsonArray) contentElement).get(i);
				/* Check if the JSON element is not a JSON primitive value */
				if (!variationElement.isJsonPrimitive()) {
					HDLmAssertAction(false, "Variation element is not a JSON primitive value");
				}
				String  textStr = HDLmJson.getStringJson(variationElement);
				choiceList.add(textStr);			
			}	
	  }
	  /* This is actually the standard (more common) JSON output format.
	     Of course, we need to handle both cases. */ 
	  else {
			/* Check if the JSON element is not a JSON object value */
			if (!contentElement.isJsonObject()) {
				HDLmAssertAction(false, "Content element is not a JSON object value");
			}
			/* Get the set of JSON keys from the JSON object */
			jsonKeys = HDLmJson.getJsonKeys(contentElement);
			key = "variations";
			if (!jsonKeys.contains(key)) {
				HDLmAssertAction(false, "Content element does not have variations in it");
			}	
			/* Get the variations values array that has the text variations */
			JsonElement   variantionsElement = HDLmJson.getJsonValue(contentElement, key);		
			int           variantionsSize = ((JsonArray) variantionsElement).size();
			/* Process each element in the variations JSON array */
			for (int i = 0; i < variantionsSize; i++) {
				JsonElement   variationElement = ((JsonArray) variantionsElement).get(i);
				/* Check if the JSON element is not a JSON primitive value */
				if (!variationElement.isJsonPrimitive()) {
					HDLmAssertAction(false, "Variation element is not a JSON primitive value");
				}
				String  textStr = HDLmJson.getStringJson(variationElement);
				choiceList.add(textStr);			
			}	  
	  }
		/* Return the text variations list to the caller */
		return choiceList;
	}
}
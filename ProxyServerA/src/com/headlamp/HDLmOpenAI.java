package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;  
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.entity.*;
import org.apache.http.entity.mime.content.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import com.google.gson.Gson; 
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
/**
 * HDLmOpenAI short summary.
 *
 * HDLmOpenAI description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmOpenAI {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
private static final Logger LOG = LoggerFactory.getLogger(HDLmOpenAI.class);  
	/* This class can never be instantiated */
	private HDLmOpenAI() {}	
	/* Build an Open AI authorization header */ 
	protected static String  buildAuthorizationHeader() {
		/* Get the Open AI key from the configuration values */ 
		String  apiKeyStr = HDLmConfigInfo.getOpenAIApiKeySchaeffer();
    /* Build an authorization header using an API key string */
	  return HDLmOpenAI.buildAuthorizationHeaderUsingAPIKeyStr(apiKeyStr);
	}
	/* Build an Open AI authorization header using an API key string that
	   was passed by the caller */ 
	protected static String  buildAuthorizationHeaderUsingAPIKeyStr(String apiKeyStr) {
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
	protected static String  buildContentTypeHeader(String contentTypeStr) {
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
	/* Build a data entity for use by other routines */
	protected static HttpEntity buildDataEntity(String inputImageUrl) {
		/* Check one or more values passed by the caller */
		if (inputImageUrl == null) {
			String   errorText = "Input image URL string reference passed to buildDataEntity is null";
			throw new NullPointerException(errorText);			
		}
		/* Build a few bodies that can be added to the entity */
	  StringBody  stringBodyOne = new StringBody("3", ContentType.MULTIPART_FORM_DATA);
	  StringBody  stringBodyTwo = new StringBody("1024x1024", ContentType.MULTIPART_FORM_DATA);  
	  /* Build the input stream that can be added to the entity */
	  ByteArrayInputStream    imageBais = null;
	  String                  inputImageStrEncoded = null;
		try { 
			/* inputImageStr = "https://cdn.openai.com/API/images/guides/image_variation_original.webp"; */
			URL   imageUrl = HDLmHttp.encodeUrl(inputImageUrl);  
			inputImageStrEncoded = imageUrl.toString();
			BufferedImage           imageBuffered = ImageIO.read(imageUrl);
			imageBuffered = HDLmOpenAI.getSquareImage(imageBuffered);
			ByteArrayOutputStream   imageBaos = new ByteArrayOutputStream();
			ImageIO.write(imageBuffered, "png", imageBaos);
			imageBais = new ByteArrayInputStream(imageBaos.toByteArray());
		}
		catch (MalformedURLException malformedException) {
			if (inputImageUrl != null)
			  LOG.info("URL - " + inputImageUrl);
			LOG.info("MalformedURLException while executing buildDataEntity");
			LOG.info(malformedException.getMessage(), malformedException);
			HDLmEvent.eventOccurred("MalformedURLException");	
			return null;
		}
		catch (IOException ioException) {
			if (inputImageUrl != null)
			  LOG.info("URL - " + inputImageUrl);
			LOG.info("IOException while executing buildDataEntity");
  		LOG.info(ioException.getMessage(), ioException);
  		HDLmEvent.eventOccurred("IOException");	
  		return null;
		}	 
	  /* Build the entity that will be use to send the image data */
	  MultipartEntityBuilder  builder = MultipartEntityBuilder.create();
	  builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);	   
	  builder.addBinaryBody("image", imageBais, ContentType.IMAGE_PNG, inputImageStrEncoded); 
	  builder.addPart("n", stringBodyOne);
	  builder.addPart("size", stringBodyTwo);
	  HttpEntity  dataEntity = builder.build();
	  /* Return the value we just built to the caller */
	  return dataEntity;		
	}
	/* Build a header list for use with the Open AI API */
	protected static ArrayList<String> buildHeaders() {
	  /* Build a header list for use later */
		ArrayList<String>   headerList = new ArrayList<String>();  
		/* Build an authorization header and add it to the header list */
	  String  authorizationHeader = HDLmOpenAI.buildAuthorizationHeader();
	  headerList.add(authorizationHeader);
	  String  contentTypeHeader = HDLmOpenAI.buildJsonContentTypeHeader();
	  headerList.add(contentTypeHeader);
	  /* Return the header list to the caller */
	  return headerList;		
	}
	/* Build some JSON for use by the Open AI API for getting text 
	   variations */
	protected static HDLmResponse buildJsonTextVariations(String inputStr, String urlStr, String pathValueStr) {
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
    HDLmJson.addStringToJsonObject(parentJsonObject, "model", "gpt-4-turbo");
    HDLmJson.addNumberToJsonObject(parentJsonObject, "temperature", 0.9);  
    /* Add the path value string to the URL, if need be */
    int   pathValueStrLen = pathValueStr.length();
    /* Save the original URL value for use (checking) later */
    String  originalUrlStr = urlStr;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check for a regex path. Regex paths are not used. */
			if (pathValueStrLen >= 3          &&
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
    int   maxAllowedLength = HDLmConfigInfo.getOpenAIMaximumStringLength(); 
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
	/* Build an application JSON content type header */ 
	protected static String  buildJsonContentTypeHeader() {
	  String  outStr = "";
	  outStr += HDLmOpenAI.buildContentTypeHeader("application/json");
	  return outStr;
	}
	/* Build a multipart/form-data content type header. The caller must pass the 
	   correct boundary value. */ 
	protected static String  buildMultipartFormContentTypeHeader(String boundaryStr) {
		/* Check one or more values passed by the caller */
		if (boundaryStr == null) {
			String   errorText = "Boundary string reference passed to buildMultipartFormContentTypeHeader is null";
			throw new NullPointerException(errorText);			
		}
	  String  outStr = "";
	  outStr += HDLmOpenAI.buildContentTypeHeader("multipart/form-data; boundary=");
	  outStr += boundaryStr;
	  return outStr;
	}	
  /* Get some image choices using the Open AI API */
	protected static HDLmResponse  getImageChoices(String inputImageStr, String urlStr, String pathValueStr) {
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
		/* Build a response object that can be returned to the caller */
		HDLmResponse  outResponse = new HDLmResponse();
	  /* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenAI.buildHeaders(); ;
    /* Build a data entity that can be used below */
	  HttpEntity  dataEntity = HDLmOpenAI.buildDataEntity(inputImageStr);
	  /* Use the entity to get the multipart/form-data header that must
	     be added to the array of headers */
	  Header  contentTypeHeader = dataEntity.getContentType();
	  String  contentTypeHeaderStr = contentTypeHeader.toString(); 
	  headerList.add(contentTypeHeaderStr);	  
	  /* Try to get some image choices/variants */
	  HDLmResponse  getResponse = getSomeOpenAIImageData("v1/images/variations", headerList, dataEntity);
	  /* Get some values from the response */
	  String  errorMessage = getResponse.getErrorMessage();
	  String  outputJson = getResponse.getReturnString();
	  /* Check if we got an error message from the low-level Ai routine */
	  if (errorMessage != null) {
	  	outResponse.setErrorMessage(errorMessage);
	  	return outResponse;	  	
	  }	  
	  /* Check if the Open AI APi returned a null JSON string */  
	  if (outputJson == null) {
	  	errorMessage = "Output JSON from Open AI API is null";
	  	outResponse.setErrorMessage(errorMessage);
	  	return outResponse;
	  } 
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   variantsList = HDLmOpenAI.getImageVariations(outputJson); 
	  /* Add the image variants list to the output response */
	  outResponse.setStringList(variantsList); 
	  return outResponse;
	}
	/* Get a list of image variations from some output (output of Open AI) JSON */
	protected static ArrayList<String> getImageVariations(String  outputJson) {
		/* Check one or more values passed by the caller */
		if (outputJson == null) {
			String   errorText = "Output JSON string reference passed to getImageVariations is null";
			throw new NullPointerException(errorText);			
		}
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   imageVariantsList = new ArrayList<String>();  
	  /* Get the image variants from the Open AI output */
    JsonParser   parser = new JsonParser();  
	  JsonElement  topNode = parser.parse(outputJson);
		/* Check if the JSON element is not a JSON object value */
		if (!topNode.isJsonObject()) {
			HDLmAssertAction(false, "Top node is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = HDLmJson.getJsonKeys(topNode);
		/* Check if we have the field in the JSON */
		String  key = "data";
		if (!jsonKeys.contains(key)) {
			HDLmAssertAction(false, "Output JSON from Open AI API does not have data");
		}	
		/* Get the JSON object from the JSON element */
		JsonObject    topNodeObject = topNode.getAsJsonObject();
		JsonElement   dataElement = topNodeObject.get(key);
		if (!dataElement.isJsonArray()) {
			HDLmAssertAction(false, "Data element is not a JSON array value");
		}
		/* Get all of the variants and make sure we have at least one */
		JsonArray     dataArray = (JsonArray) dataElement;
		int           dataArraySize = dataArray.size();
		if (dataArraySize <= 0) {
			String  errorText = "Data array in inbound data is not large enough";
			HDLmAssertAction(false, errorText);	
		}
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   variantsList = new ArrayList<String>();  
		key = "url";
		/* Process each element in the JSON array */
		for (int i = 0; i < dataArraySize; i++) {
			JsonElement   dataArrayElement = dataArray.get(i);
			/* Check if the JSON element is not a JSON object value */
			if (!dataArrayElement.isJsonObject()) {
				HDLmAssertAction(false, "Data array element is not a JSON object value");
			}
			/* Get the set of JSON keys in the JSON object */
			jsonKeys = HDLmJson.getJsonKeys(dataArrayElement);
			/* Check if we have the field in the JSON */
			if (!jsonKeys.contains(key)) 
				continue;
			String  imageStr = HDLmJson.getJsonString(dataArrayElement, key); 
			imageVariantsList.add(imageStr);			
		}	  
	  
		/* Return the image variations list to the caller */
		return imageVariantsList;
	}
	/* Get some Open AI image data. It is assumed that the string
	   passed to this routine is already in JSON format. */
	protected static HDLmResponse  getSomeOpenAIImageData(String pathStr, 
			                                                  ArrayList<String> headerList,
			                                                  HttpEntity dataEntity) {
		/* Check a few values passed by the caller */
		if (pathStr == null) {
			String   errorText = "Path string reference passed to getSomeOpenAIImageData is null";
			throw new NullPointerException(errorText);			
		}
		if (headerList == null) {
			String   errorText = "Header list reference passed to getSomeOpenAIImageData is null";
			throw new NullPointerException(errorText);		
		}
		if (dataEntity == null) {
			String   errorText = "Data entity reference passed to getSomeOpenAIImageData is null";
			throw new NullPointerException(errorText);		
		}
		/* Build a response object that can be returned to the caller */
		HDLmResponse  outResponse = new HDLmResponse();
		/* Build the URL string passed to the server */
	  String  protocol = "https";
	  String  hostName = "api.openai.com";
	  String  pathValue = pathStr;
	  String  urlString = protocol + "://" + hostName + '/' + pathValue; 
		/* Send the request to the Open AI server */
	  /* The callers passes the headers and the JSON */
	  /* Use the Curl mechanism */
		HDLmApacheResponse  apacheResponse;;
	  apacheResponse = HDLmCurlApache.runCurlResponse(urlString,
								                                    "",
								                                    "", 
								                                    HDLmHttpTypes.POST,  
								                                    headerList,
								                                    "",
								  				                          dataEntity,
								  				                          HDLmOutboundJson.OUTBOUNDJSONNO,
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
	  else if (statusLine != null && statusCode != HttpStatus.SC_OK)
	  	outResponse.setErrorMessage(statusLine);
	  else if (contentString != null)
	  	outResponse.setReturnString(contentString);
		return outResponse;
	}
	/* Get some Open AI text data. It is assumed that the string
	   passed to this routine is already in JSON format. */
	protected static HDLmResponse  getSomeOpenAITextData(String pathStr, 
			                                                 ArrayList<String> headerList,
			                                                 String jsonData) {
		/* Check a few values passed by the caller */
		if (pathStr == null) {
			String   errorText = "Path string reference passed to getSomeOpenAITextData is null";
			throw new NullPointerException(errorText);			
		}
		if (headerList == null) {
			String   errorText = "Header list reference passed to getSomeOpenAITextData is null";
			throw new NullPointerException(errorText);		
		}
		if (jsonData == null) {
			String   errorText = "Json data reference passed to getSomeOpenAITextData is null";
			throw new NullPointerException(errorText);		
		}
		/* Build a response object that can be returned to the caller */
		HDLmResponse  outResponse = new HDLmResponse();
		/* Build the URL string passed to the server */
	  String  protocol = "https";
	  String  hostName = "api.openai.com";
	  String  pathValue = pathStr;
	  String  urlString = protocol + "://" + hostName + '/' + pathValue; 
		/* Send the request to the Open AI server */
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
	  else if (statusLine != null && statusCode != HttpStatus.SC_OK)
	  	outResponse.setErrorMessage(statusLine);
	  else if (contentString != null)
	  	outResponse.setReturnString(contentString);
		return outResponse;
	}
	/* Change the input image size. Get a square output image. */
	public static BufferedImage  getSquareImage(BufferedImage inputBufferedImage) {
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
	/* Get some text choices using the Open AI API */
	protected static HDLmResponse  getTextChoices(String inputStr, String urlStr, String pathValueStr) {
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
		/* Build a response object that can be returned to the caller */
		HDLmResponse  getTextResponse = new HDLmResponse();
	  /* Build a header list for use later */
		ArrayList<String>   headerList = HDLmOpenAI.buildHeaders();
		/* Build some JSON for use below */
		HDLmResponse  buildResponse = HDLmOpenAI.buildJsonTextVariations(inputStr, urlStr, pathValueStr);
		String   dataJson = buildResponse.getReturnString();
	  /* Try to get some text choices/variants */
	  HDLmResponse  getResponse = getSomeOpenAITextData("v1/chat/completions", headerList, dataJson);
	  /* Get some values from the response */
	  String  errorMessage = getResponse.getErrorMessage();
	  String  outputJson = getResponse.getReturnString();
	  /* Check if we got an error message from the low-level Ai routine */
	  if (errorMessage != null) {
	  	getTextResponse.setErrorMessage(errorMessage);
	  	return getTextResponse;	  	
	  }	      /* Check if obtained any output JSON from the Open AI API */ 
	  if (outputJson == null) {
	  	errorMessage = "Output JSON from Open AI API is null";
	  	getTextResponse.setErrorMessage(errorMessage);
	  	return getTextResponse;
	  }
	  /* Try to get each of the text choices/variants from the Open AI output */
	  ArrayList<String>   choiceList = HDLmOpenAI.getTextVariations(outputJson);
	  /* Store a reference to the choice list in the response object */
	  getTextResponse.setStringList(choiceList);
		return getTextResponse;
		 
	}
	/* Get a list of text variations from some output (output of Open AI) JSON */
	protected static ArrayList<String>  getTextVariations(String  outputJson) {
		/* Check one or more values passed by the caller */
		if (outputJson == null) {
			String   errorText = "Output JSON string reference passed to getTextVariations is null";
			throw new NullPointerException(errorText);			
		}
	  /* Try to get each of the choices/variants from the Open AI output */
	  ArrayList<String>   choiceList = new ArrayList<String>();  
	  /* Get the text variants from the Open AI output */
	  JsonParser   parser = new JsonParser();  
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
	  if (contentStr != null && contentStr.length() >= 10) {
	  	if (contentStr.startsWith("```json") && 
	  			contentStr.endsWith("```")) {
	  		int   contentStrLength = contentStr.length();
	  		/* In this case, the content string has some newline 
	  		   characters that should also be removed */
	  		contentStr = contentStr.substring(8, contentStrLength-4);	  		
	  	}  	
	  }
	  /* Convert the content string to a JSON object */
	  JsonParser   contentParser = new JsonParser();  
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
package com.headlamp;

import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * HDLmMenus short summary.
 *
 * The Java code doesn't really have any menus (at this time). However,
 * several routines were built in the JavaScript code and needed a place 
 * for them (Java ports of them) in the Java code. A module with the same
 * name appeared to be the best choice.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmMenus {
	/* The next statement initializes logging to some degree. Note that having the
     slf4j jars and the log4j jars in the classpath also plays some role in
     logging initialization.	 */
private static final Logger LOG = LoggerFactory.getLogger(HDLmMenus.class);
	/* This class can never be instantiated */
	private HDLmMenus() {}
  /* The next method builds a new modification name using a set of 
	   information passed by the caller. This routine does not store
	   the new modification name anywhere. However, it (the new name)
	   is returned to the caller. */
	protected static String  buildModificationName(HDLmTree parentTreeNode, 
			                                           ArrayList<HDLmTree> childList, 
	                                               String newUrlStr, 
	                                               String newDetailsType) {
	  boolean   logIsDebugEnabled = LOG.isDebugEnabled();
		if (logIsDebugEnabled) {
		  LOG.debug("In buildModificationName");
		}
		/* Check if the parent tree node value is null */
		if (parentTreeNode == null) {
			String  errorText = "Parent tree node passed to buildModificationName is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the child list value is null */
		if (childList == null) {
			String  errorText = "Child list passed to buildModificationName is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new details type value is null */
		if (newDetailsType == null) {
			String  errorText = "New details type value passed to buildModificationName is null";
			throw new NullPointerException(errorText);
		}
	  String  defaultShortModName = HDLmDefines.getString("HDLMSHORTMODNAME");
		if (defaultShortModName == null) {
			String   errorFormat = "Define value for short modification name not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMSHORTMODNAME");
			HDLmAssertAction(false, errorText);		    	
		}
	  /* Declare and define a few values */
	  String  newModName = null;
	  /* We now have enough information to build the final modification name. 
	     Of course, the final modification name is just a guess. The user will
	     probably have to change the modification name by hand after the new
	     modification has been added. */
	  newModName = defaultShortModName;
	  /* Use the path value to help build the modification name */
	  if (newUrlStr != null) {
	  	String  newPathString;
	    newPathString = HDLmUtility.getPathString(newUrlStr);
	    /* Replace all occurences of '-' with a blank */
	    newPathString = newPathString.replaceAll("\\-", " ");
	    /* Replace all occurences of '/' with a blank */
	    newPathString = newPathString.replaceAll("\\/", " ");
	    newPathString = newPathString.trim();
	    if (!newPathString.equals(""))
	      newModName += ' ' + newPathString;
	  }
	  /* Use the modification type to help build the modification name */
	  newModName += ' ' + newDetailsType;
	  /* Change the first character of each word to uppercase */
	  newModName = HDLmString.ucFirstSentence(newModName);
	  /* Get a count of how many times the new modification name 
	     is already used. Hopefully, this count will be zero. 
	     However, it might not be zero. */
	  HDLmNameMatch  matchObj = HDLmTree.countSubNodeNames(newModName, parentTreeNode,
	                                                       null, false);
	  if (matchObj.getCount() > 0) {
	    ArrayList<Integer>  integerList = HDLmTree.buildIntegerListName(newModName, childList);
	    /* Get the next available modification number */
	    int   nextInteger = HDLmUtility.getNextInteger(integerList);
	    newModName += ' ' + Integer.toString(nextInteger);
	  }
	  return newModName;
	}
	/* The routine below is not really used in the Java environment.
	   Just check the values passed by the caller and return. */ 
	protected static String finishTreeNode(HDLmTree currentTreeNode, 
			                                   boolean containerAvailable) {
		/* Check if the tree node passed to this routine is null */
		if (currentTreeNode == null) {
			String  errorText = "Tree node reference passed to finishTreeNode is null";
			throw new NullPointerException(errorText);
		}
		return null;
	}
	/* This method gets a URL value from a style string. If the style
	   string does not contain a proper URL value, then this routine 
	   returns an empty (zero-length) string. Note that the returned
	   URL string will always start with two slashes if it is a network
	   (HTTP or HTTPS) URL. It will start with 'data:' if it is a data
	   URL. This code supports both network URLs and data URLs. The leading 
	   protocol (if any) and the leading colon (if one exists) are always
	   removed from network URLs. The style must be a background-image style. */
	protected static String getUrlFromStyle(String styleStr) {
		/* Check if the style string passed to this routine is null */
		if (styleStr == null) {
			String  errorText = "Style string passed to getUrlFromStyle is null";
			throw new NullPointerException(errorText);
		}
	  String  urlStr = "";
	  /* What follows is a dummy loop used only to allow break to work */
	  while (true) {
	    if (styleStr == null ||
	        styleStr.equals("")) 
	    	break;
	    /* At this point we need to analyze the style string. The style
	       string may specific a background image URL. This type of style
	       can be used. */
	    HDLmToken   styleStrToken;
	    ArrayList<HDLmToken>  styleStrTokens = HDLmString.getTokens(styleStr, '"');
	    /* Make sure we have enough tokens */
	    if (styleStrTokens.size() < 9)
	      break;
	    /* Check the first token */
	    styleStrToken = styleStrTokens.get(0); 
	    if (!styleStrToken.getValue().equals("background"))
	      break;
	    /* Check the second token */
	    styleStrToken = styleStrTokens.get(1); 
	    if (!styleStrToken.getValue().equals("-"))
	      break;
	    /* Check the third token */
	    styleStrToken = styleStrTokens.get(2); 
	    if (!styleStrToken.getValue().equals("image"))
	      break;
	    /* Check the fourth token */
	    styleStrToken = styleStrTokens.get(3); 
	    if (!styleStrToken.getValue().equals(":"))
	      break;
	    /* Check the fifth token */
	    styleStrToken = styleStrTokens.get(4);  
	    if (styleStrToken.getType() != HDLmTokenTypes.SPACE)
	      break;
	    /* Check the sixth token */
	    styleStrToken = styleStrTokens.get(5); 
	    if (!styleStrToken.getValue().equals("url"))
	      break; 
	    /* Check the seventh token */
	    styleStrToken = styleStrTokens.get(6); 
	    if (!styleStrToken.getValue().equals("("))
	      break; 
	    /* The URL will be in the eigth token. The URL may be a 
	       traditional HTTP/HTTPS URL or it may be a data URL 
	       value. At least for now we can not handle data values.
	       This has been changed we do support data URLs now.*/
	    styleStrToken = styleStrTokens.get(7);
	    urlStr = styleStrToken.getValue();
		  /* Check if we have a data value at this point. We can not
		     handle data values for now. This has been changed. We do
		     support data URLs at this point. */
		  if (urlStr.startsWith("data")) {
		    urlStr = urlStr;
		  }
	    /* Remove a set of prefixes from the URL string. Note that
	       the code below changes (a lot) network URLs, but does not
	       change data URLs at all. */
	    if (urlStr.startsWith("https"))
	      urlStr = urlStr.substring(5);
	    if (urlStr.startsWith("http"))
	      urlStr = urlStr.substring(4);
	    if (urlStr.startsWith(":"))
	      urlStr = urlStr.substring(1);
	    break;
	  }
	  return urlStr;
  }	
/* This method provides default values for a new modification that
   is being build. Of course, the default values may be wrong. However,
	 they may be OK as well. The user is free to change (using the UI)
	 these values as need be. */
	protected static void provideDefaultValues(HDLmOperationTypes operationType,
			                                       HDLmTree parentTreeNode,
	                                           HDLmTree newTreeNode,
									                           JsonObject newNodeIdenObj,
									                           String newUrlStr,
									                           String newOrderInfo,
									                           boolean newCopyElements) {
		/* Check if the operation type passed to this routine is null */
		if (operationType == null) {
			String  errorText = "Operation type passed to provideDefaultValues is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the parent tree node passed to this routine is null */
		if (parentTreeNode == null) {
			String  errorText = "Parent tree node passed to provideDefaultValues is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new tree node passed to this routine is null */
		if (newTreeNode == null) {
			String  errorText = "New tree node passed to provideDefaultValues is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new node JSON object passed to this routine is null */
		if (newNodeIdenObj == null) {
			String  errorText = "The new node identifier JSON object passed to provideDefaultValues is null";
			throw new NullPointerException(errorText);
		}
	  HDLmMod       newNodeDetails = newTreeNode.getDetails();
	  HDLmNodeIden  newNodeIdenInstance = null;
	  String        newModName = null;
	  String        newDetailsType = null;
	  /* Check if this routine was invoked to handle a copy or one or
	     more DOM elements. If this is true, then we really don't want
	     to set the node identifier field in the modification object. */
	  if (newCopyElements == false) {
	    newNodeIdenInstance = new HDLmNodeIden(newNodeIdenObj);
	    newNodeDetails.setIfNotSetNodeIden(newNodeIdenInstance);
	  }
	  String    newPathString = null;
	  boolean   parameterNumberUsed;
	  boolean   ruleFinished = false;
	  boolean   buildPerceptualHash = false;
	  String    buildPerceptualUrl = null;
	  /* Get all of the current children of the parent node */
	  ArrayList<HDLmTree>   childList = parentTreeNode.getChildren();
	  /* Set the modification path value */
	  if (newUrlStr != null) {
	    newPathString = HDLmUtility.getPathString(newUrlStr);
	    newNodeDetails.setIfNotSetPathValue(newPathString);
	  }
	  /* Check if we should try to build an image rule. An image rule is
	     only constructed if we are processing an (img) DOM element. Of
	     course, this is only a guess. */ 
	  if (ruleFinished == false  &&
	      newOrderInfo == null   &&
	      newNodeIdenObj != null &&
	      HDLmJson.hasJsonKey(newNodeIdenObj, "attributes")) {
	    JsonElement   jsonAttributes = HDLmJson.getJsonValue(newNodeIdenObj, "attributes");	
	    if (!jsonAttributes.isJsonNull()) {
	      if (HDLmJson.hasJsonKey(jsonAttributes, "src") && 
	      		HDLmJson.hasJsonKey(jsonAttributes, "tag")) { 
	        String  tagValue = HDLmJson.getJsonString(jsonAttributes, "tag");
	        if (tagValue.equals("img")) {
	        	String  newImageInfo = HDLmJson.getJsonString(jsonAttributes, "src");
			      /* Set and save a few values needed to build the preceptual hash
		           much later. These value are only needed in some cases. */
	        	if (newImageInfo != null) {
	        		newImageInfo = HDLmUtility.removeHttpPrefix(newImageInfo);
		          buildPerceptualHash = true; 
		          buildPerceptualUrl = newImageInfo;
	        	}  
		        /* The code below does seem to work. The type is set to the correct
		           value. An array is built for the new order values. The first and
		           only entry in the array is set to a default value. */	     
		        newDetailsType = "image";		        
		        newNodeDetails.setType(HDLmModTypes.NONE);
		        newNodeDetails.setIfNotSetType(newDetailsType);
		        ArrayList<String>   newImages = new ArrayList<String>();
		        newImages.add(newImageInfo); 
		        newNodeDetails.setIfNotSetValues(newImages);
		        newNodeDetails.setValuesCount(1);
		        ruleFinished = true;
		      }
		    }
	    }
	  }
	  /* Check if we should try to build an order rule. An order rule is
	     only constructed if we were passed the number of child elements.        
	     Of course, this is just a guess. */
	  if (ruleFinished == false &&
	      newOrderInfo != null) { 
	    if (1 == 1) {
	      /* The code below does seem to work. The type is set to the correct
	         value. An array is built for the new order values. The first and
	         only entry in the array is set to a default value. */
	      if (1 == 1) {
	        newDetailsType = "order"; 
	        newNodeDetails.setType(HDLmModTypes.NONE);
	        newNodeDetails.setIfNotSetType(newDetailsType);
	        ArrayList<String>  newOrders = new ArrayList<String>();
	        newOrders.add(newOrderInfo); 
	        newNodeDetails.setIfNotSetValues(newOrders);
	        newNodeDetails.setValuesCount(1);
	        ruleFinished = true;
	      }
	    }
	  }
	  /* Check if we should try to build a style rule. Try to set
	     the extra information field and the modification type
	     value. This can only be done if the style is set to a
	     specific type value or values. Of course, this is just 
	     a guess. */
	  if (ruleFinished == false &&
	      newNodeIdenObj != null &&
	      HDLmJson.hasJsonKey(newNodeIdenObj, "attributes")) {
		  JsonElement   jsonAttributes = HDLmJson.getJsonValue(newNodeIdenObj, "attributes");	
		  if (!jsonAttributes.isJsonNull()) {
		    if (HDLmJson.hasJsonKey(jsonAttributes, "style")) {			 
	        String  styleStr = HDLmJson.getJsonString(jsonAttributes, "style");
	        if (styleStr != null &&
	            !styleStr.equals("")) {
				    /* At this point we need to analyze the style string. The style
				       string may specific a background image URL. This type of style
				       can be used. Note that we support network URLs (that typically
				       start with two slashes and data URLs (that typically start with
				       a string such as 'data:'. */
	          String urlStr = HDLmMenus.getUrlFromStyle(styleStr); 
	          /* The code below does seem to work. The type is set to the correct
	             value. An array is built for the new style values. The first and
	             only entry in the array is set to a default value. */
	          if (!urlStr.equals("")) {
				      /* Set and save a few values needed to build the preceptual hash
				         much later. These value are only needed in some cases. */
				      buildPerceptualHash = true;
				      buildPerceptualUrl = urlStr;
				      /* Set the type value correctly */
				      newDetailsType = "style"; 
				      newNodeDetails.setType(HDLmModTypes.NONE);
				      newNodeDetails.setIfNotSetType(newDetailsType);
				      newNodeDetails.setIfNotSetExtra("background-image");
			        ArrayList<String>  newStyles = new ArrayList<String>();
			        newStyles.add(urlStr); 
			        newNodeDetails.setIfNotSetValues(newStyles);
			        newNodeDetails.setValuesCount(1);
				      ruleFinished = true;
	          }
	        }
	      }
	    }
	  }
	  /* Check if we should try to build a replace rule. Try to set
	     the modification type value. Note that the node identifier
	     information in this case, is not really a set of node identifier
	     values. It is a JSON string containing a set of HTML elements. 
	     Of course, we can not set the node identifier field. However, 
	     we can set one of the replace values. */ 
	  if (ruleFinished == false   &&
	      newNodeIdenObj != null  && 
		    HDLmJson.hasJsonKey(newNodeIdenObj, "attributes") &&
		    newCopyElements == true ) {
	    JsonElement   jsonAttributes = HDLmJson.getJsonValue(newNodeIdenObj, "attributes");
	    if (!jsonAttributes.isJsonNull()) {
	      /* The code below does seem to work. The type is set to the correct
	         value. An array is built for the new text values. The first and
	         only entry in the array is set to a default value. */
	      newDetailsType = "replace"; 
	      newNodeDetails.setType(HDLmModTypes.NONE);
	      newNodeDetails.setIfNotSetType(newDetailsType);  
	      /* Try to build the JSON object that we can use to update the target 
	         web page. This will only work if we coped an entire web page. */
	      JsonElement  newReplaceObj; 
	      newReplaceObj = HDLmMenus.searchNodeTag(newNodeIdenObj, "body");
	      if (newReplaceObj != null) {
	      	  HDLmJson.setJsonString(newReplaceObj, "tag", "div");
	   	    HDLmJson.setJsonNull(newReplaceObj, "attributes");
	   	    HDLmJson.setJsonNull(newReplaceObj, "text");
	      }
	      /* We may have just copied part of a web page */
	      else  
	        newReplaceObj = newNodeIdenObj;
	      /* Build an array of replace(ment) text with just one entry */
	      ArrayList<String>  newReplaceValues = new ArrayList<String>();
	      String  replaceValue = HDLmJson.getStringJson(newNodeIdenObj);
	      newReplaceValues.add(replaceValue); 
	      newNodeDetails.setIfNotSetValues(newReplaceValues);
	      newNodeDetails.setValuesCount(1);
	      ruleFinished = true;
	    }
	  }
	  /* Check if we should try to build a textchecked rule. Try to set
	     the extra information field and the modification type value. 
	     This can only be done if the inner text is set to a non-blank
	     value. Of course, this is just a guess. */
	  if (ruleFinished == false  &&
	      newNodeIdenObj != null &&
	      HDLmJson.hasJsonKey(newNodeIdenObj, "attributes")) {
	 	  JsonElement   jsonAttributes = HDLmJson.getJsonValue(newNodeIdenObj, "attributes");		
	 	  if (!jsonAttributes.isJsonNull()) {
	 	    if (HDLmJson.hasJsonKey(jsonAttributes, "innertext")) {
	 	      String  innerTextString = HDLmJson.getJsonString(jsonAttributes, "innertext");
	        if (innerTextString != null &&
	            !innerTextString.equals("")) {
				    /* The code below does seem to work. The type is set to the correct
				       value. An array is built for the new text values. The first and
				       only entry in the array is set to a default value. */
				    if (1 == 1) {
				      newDetailsType = "textchecked"; 
				      newNodeDetails.setType(HDLmModTypes.NONE);
				      newNodeDetails.setIfNotSetType(newDetailsType);
				      newNodeDetails.setExtra(null);
				      newNodeDetails.setIfNotSetExtra(innerTextString);
			        ArrayList<String>  newTexts = new ArrayList<String>();
			        newTexts.add(innerTextString); 
			        newNodeDetails.setIfNotSetValues(newTexts);
			        newNodeDetails.setValuesCount(1);
				      ruleFinished = true;
				    }
	        }
	 	    }  
	    }
	  }
    /* Check if we should try to build a text rule (not a textchecked
       rule). In this case, we don't need to set the extra information
       field. We do need to set the modification type value. This can 
       only be done if the inner text is set to a null or an empty value 
       Of course, this is just a guess. */
	  if (ruleFinished == false  &&
	      newNodeIdenObj != null &&
	      HDLmJson.hasJsonKey(newNodeIdenObj, "attributes")) {
		  JsonElement   jsonAttributes = HDLmJson.getJsonValue(newNodeIdenObj, "attributes");		
		  if (!jsonAttributes.isJsonNull()) {
		    if (HDLmJson.hasJsonKey(jsonAttributes, "innertext")) {
		      String  innerTextString = HDLmJson.getJsonString(jsonAttributes, "innertext");
	        if (innerTextString == null ||
	            innerTextString.equals("")) {
				    /* The code below does seem to work. The type is set to the correct
				       value. An array is built for the new text values. The first and
				       only entry in the array is set to a default value. */
				    if (1 == 1) {
				      newDetailsType = "text"; 
				      newNodeDetails.setType(HDLmModTypes.NONE);
				      newNodeDetails.setIfNotSetType(newDetailsType);
			        ArrayList<String>  newTexts = new ArrayList<String>();
			        newTexts.add("New Text"); 
			        newNodeDetails.setIfNotSetValues(newTexts);
			        newNodeDetails.setValuesCount(1);
				      ruleFinished = true;
				    }
	        }
		    }  
	    }
	  }
	  /* Check if a parameter number is used with the current type
	     of rule. This is true in some cases, but not in others. */
	  if (newDetailsType == null)
	  	HDLmAssertAction(false, "Modification type is not set in provideDefaultValues");
	  if (HDLmMod.getModificationTypeParmNumberUsed(newDetailsType) == false)
	    parameterNumberUsed = false;
	  else
	    parameterNumberUsed = true;
	  /* Build a map that shows how many times each parameter number 
	     is used */
	  Map<Integer, Integer>  parmMapObj = HDLmTree.buildParameterMap(childList);
	  if (parmMapObj != null) {
	    int  minParameterNumber = HDLmTree.findLowestParameter(parmMapObj);
	    if (parameterNumberUsed) 
	    	newNodeDetails.setIfNotSetParameterNumber(minParameterNumber);
	  }
	  /* Build and use the new rule name if possible */
	  newModName = HDLmMenus.buildModificationName(parentTreeNode, childList, 
	                                               newUrlStr, newDetailsType);
	  newNodeDetails.setName(null);
	  newNodeDetails.setIfNotSetName(newModName);
	  /* Using the final modification name, we can reset the node path 
	     at this point. The node path must contain the final modification
	     name. */ 
	  ArrayList<String>   nodePath = newTreeNode.getNodePath();
	  int                 nodeIndex = nodePath.size() - 1;
	  nodePath.set(nodeIndex, newModName);
	  /* Set or reset the tooltip based on the final rule type. The 
	     current tooltip value was not based on the final rule type. */ 
	  String  nodeTooltip = HDLmTree.getTooltipString(newDetailsType);
	  newTreeNode.setTooltipNull();
	  newTreeNode.setIfNotSetTooltip(nodeTooltip);
	  /* Check if we must build the perceptual hash value at this point */
	  if (buildPerceptualHash) {
	    /* We may or may not want to add some additional attribute information  
	       to the node identifier object. We only do this if we are handling
	       a style that uses a background image. If that is true (which 
	       will always be true at this point), then we get the perceptual
	       hash value for the background image and add it to the attributes.
	    
	       The call below does get the perceptual hash immediately. The 
	       perceptual hash value is immediately used to update the node 
	       information. */
	  	/* LOG.info("In provideDefaultValues"); */
	  	/* LOG.info(buildPerceptualUrl); */
	  	HDLmUtilityResponse   newUtilityResponse = HDLmUtility.getPerceptualHashFromUrl(buildPerceptualUrl,
	  			                                                                            null);
	    Boolean   newPHashUpdateCache = newUtilityResponse.getUpdateCache();
	    String    newPerceptualHash = newUtilityResponse.getPHashValue();
	    String    newPerceptualUrl = newUtilityResponse.getUrlStr();
	    if (newPerceptualHash == null)
	    	HDLmAssertAction(false, "New perceptual hash not calculated in provideDefaultValues(");	
	    if (newPHashUpdateCache != null && 
	    		newPHashUpdateCache == true)
	    	HDLmUtility.updatePHashCache(newPerceptualUrl, newPerceptualHash);
	    /* Add the new perceptual hash value to the attributes for 
	       the current set of node identifier values. First, make
	       sure we have a valid set of node identifier values. */
	    if (newNodeIdenInstance == null)
	    	HDLmAssertAction(false, "Node identifier field is not set in provideDefaultValues(");		
	    newNodeIdenInstance.addPerceptualHash(newPerceptualHash);
	  }
	}
  /* This method searches a set of nodes (a node hierarchy) for 
     the desired tag value. If the desired tag value is found,
     the node with the desired tag is returned to the caller. */
	protected static JsonElement searchNodeTag(JsonElement nodeObj, 
			                                       String searchStr) {
		/* Check if the JSON element passed to this routine is null */
		if (nodeObj == null) {
			String  errorText = "Node object (JSON element) passed to searchNodeTag is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the search string passed to this routine is null */
		if (searchStr == null) {
			String  errorText = "Search string value passed to searchNodeTag is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if we have found the correct tag */
	  if (HDLmJson.hasJsonKey(nodeObj, "tag")) {
	    String  tagValue = HDLmJson.getJsonString(nodeObj, "tag");	
      if (tagValue != null &&
      		tagValue.equals(searchStr)) 
      	return nodeObj;
	  }
	  /* Recursively check all of the subnode of the current node */
	  if (HDLmJson.hasJsonKey(nodeObj, "subnodes")) {
	  	JsonElement   subNodesElement = HDLmJson.getJsonValue(nodeObj, "subnodes");	
	    if (subNodesElement.isJsonArray()) {
	      JsonArray   subNodesArray = subNodesElement.getAsJsonArray();	
	      int         subNodesLength = subNodesArray.size();
		    for (int i = 0; i < subNodesLength; i++) {
		      JsonElement   subNode = subNodesArray.get(i); 
		      JsonElement   subNodeSearch = HDLmMenus.searchNodeTag(subNode, searchStr);
		      if (subNodeSearch != null)
		        return subNodeSearch;
		    }
	    }
	  }
	  /* Since we did not get a match, just return null */
	  return null;
	}
}
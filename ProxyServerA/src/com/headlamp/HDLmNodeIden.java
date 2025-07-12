package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.mutable.MutableInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;
/**
 * Class for supporting HTML node identifiers
 * 
 * Each instance of this class describes one set of HTML element node identifier
 * values. Node identifiers are used to locate (find) zero or more HTML elements 
 * in a DOM.
 *
 * This class has a constructor that builds an instance from JSON describing 
 * an instance. The class is designed so that node identifiers can be quickly
 * used. The conversion from JSON to a class instance may not be fast at all.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmNodeIden {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmNodeIden.class);  
  /* The statement creates a static empty instance of the node identifier 
     class. This instance is actually used in some cases. */
  private static final HDLmNodeIden   emptyNodeIden = new HDLmNodeIden((Boolean) null);  
	/* All instances of the HDLmNodeIden class have a standard set of fields */
  private Boolean               nodeEnabled = true;
  @SerializedName("type")
	private String                nodeType = null;
  @SerializedName("attributes")
	private Map<String, Object>   nodeAttributes = null;
  @SerializedName("counts")
	private Map<String, Integer>  nodeCounts = null;
	/* An error message ArrayList<String> is used to store error messages. This
     ArrayList is used to store error messages that are generated during the 
     construction of the node identifier instance from JSON. The field is 
     transient so that is is not serialized. The error messages are not 
     serialized because they are not needed after the node identifier the
     node identifier instance is built. */
  private transient ArrayList<String>   nodeMessages = null;
  @SerializedName("grandparent")
	private Map<String, Object>   nodeGrand = null;
  @SerializedName("parent")
	private Map<String, Object>   nodeParent = null;
	/* This is the default constructor for this class. It doesn't do anything.
	   All fields of this class will be set to the default values specified 
	   above. This constructor is required so that this class can be extended. */
	protected HDLmNodeIden() {}
	/* This is not the default constructor for this class. It doesn't do much 
	   except for taking a node identifier enablement value and storing it in
	   a new instance of this class. */
  protected HDLmNodeIden(Boolean newNodeEnabled) {
  	this.nodeEnabled = newNodeEnabled;
  }
	/* This constructor copies all of the fields from another node identifier instance.
	   Note that the copy is a deep copy of sorts. All of the fields should actually
	   be copied or should be immutable. */
	protected HDLmNodeIden(HDLmNodeIden oldNodeIden) {
		if (oldNodeIden == null) {
			String  errorText = "Old node identifier reference used to build node identifier instance is null";
			throw new NullPointerException(errorText);
		}
		this.nodeEnabled = oldNodeIden.getNodeEnabled();
		this.nodeType = oldNodeIden.getNodeType();
		/* We need to copy the attributes of the current element. Of course, 
		   we can only copy the old attributes, if we had any. If we don't 
		   have any old attributes, then we can not copy them. */
		Map<String, Object>   oldAttributes = oldNodeIden.getNodeAttributes();
		if (oldAttributes != null) {
			this.nodeAttributes = new HashMap<String, Object>(oldAttributes);
		}		
		/* We need to copy the count values of the current element. Of course, 
	     we can only copy the count values of the current element, if we have
	     any. If we don't have any old count values for the current element, 
	     then we can not copy them. */
	  Map<String, Integer>  oldCounts = oldNodeIden.getNodeCounts();
	  if (oldCounts != null) {
	   	this.nodeCounts = new HashMap<String, Integer>(oldCounts);
	  }
		/* We need to copy the attributes of the grand parent element. Of course, 
       we can only copy the old grand parent attributes, if we had any. If we
       don't have any old grand parent attributes, then we can not copy them. */
	  Map<String, Object>   oldGrand = oldNodeIden.getNodeGrand();
	  if (oldGrand != null) {
	    this.nodeGrand = new HashMap<String, Object>(oldGrand);
	  }	
		/* We need to copy the attributes of the parent element. Of course, 
	     we can only copy the old parent attributes, if we had any. If we
	     don't have any old parent attributes, then we can not copy them. */
	  Map<String, Object>   oldParent = oldNodeIden.getNodeParent();
	  if (oldParent != null) {
	  	this.nodeParent = new HashMap<String, Object>(oldParent);
	  }	
	}
	/* This is a constructor for the node identifier class. It must be passed a
	   JSON element that contains all of the details about the node identifier. */
	protected HDLmNodeIden(JsonElement jsonElement) {
		if (jsonElement == null) {
			String  errorText = "JSON element used to build node identifier instance is null";
			throw new NullPointerException(errorText);
		}
		boolean               tagAttributeFound = false;
		List<String>          supportedTypeValues = List.of("tag", "name", "id", "class");
		HDLmEditorTypes       editorType = HDLmEditorTypes.PASS;
		HDLmReportErrors      reportErrors = HDLmReportErrors.REPORTERRORS;
		Map<String, Object>   currentAttributesValues = new HashMap<String, Object>();
		Map<String, Object>   grandAttributesValues = new HashMap<String, Object>();
		Map<String, Object>   parentAttributesValues = new HashMap<String, Object>();
		Map<String, Integer>  currentCounts = new HashMap<String, Integer>();
		String                name = "nodeiden";
		/* Set the error count to zero. The error count is incremented each time an
		   error is detected. If the final error count (for the current set of node
		   information) is greater than zero, the current node identifier object is
		   disabled (the enabled field is set false). Note that a reference is used
		   below so that the error count can be updated by the routines called using 
		   error count.*/
		MutableInt  mutableErrorCounter = new MutableInt(0);
		/* Build an array list for error message strings. Each error
	     message is stored in this array list. */
		ArrayList<String>   errorMessages = new ArrayList<String>();
		if (errorMessages == null) {
			String  errorText = "Error message ArrayList allocation in HDLmNodeIden constructor is null";
			throw new NullPointerException(errorText);
		}		
		/* Get the list of keywords and values in the JSON object */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element used to build node identifier is JSON null");
		}
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		Set<String>   jsonKeys = jsonObject.keySet();
		/* Make sure the JSON object has exactly four or five keys */
		if (jsonKeys.size() != 4 &&
				jsonKeys.size() != 5) {
			HDLmField.reportErrorNoValue(editorType, 
					                         mutableErrorCounter, 
					                         errorMessages,
                                   jsonObject, 
                                   name, 
                                   "Modification JSON node identifier invalid key set size", 
                                   39, 
                                   reportErrors);				
		}
		/* Check if we have a required key */ 
		if (jsonKeys.contains("type") == false) {			
			HDLmField.reportErrorNoValue(editorType, 
																	 mutableErrorCounter,
																	 errorMessages,
													         jsonObject, 
													         name, 
													         "Modification JSON node identifier does not contain \"type\" key", 
													         39, 
													         reportErrors);				
		}
		/* Check if we have a required key */ 
		if (jsonKeys.contains("attributes") == false) {			
			HDLmField.reportErrorNoValue(editorType, 
	                          			 mutableErrorCounter,
																	 errorMessages,
													         jsonObject, name, 
													         "Modification JSON node identifier does not contain \"attributes\" key", 
													         39, 
													         reportErrors);				
		}
		/* Check if we have a required key */ 
		if (jsonKeys.contains("counts") == false) {			
			HDLmField.reportErrorNoValue(editorType, 
																	 mutableErrorCounter,
																	 errorMessages,
												           jsonObject, name, 
												           "Modification JSON node identifier does not contain \"counts\" key", 
												           39, 
												           reportErrors);				
		}
		/* Check if we have a required key. Actually, this key is optional.
		   We only have the grandparent key in some (but not all) cases. */ 
		if (jsonKeys.contains("grandparent") == false &&
				jsonKeys.contains("grandparent") == true) {			
			HDLmField.reportErrorNoValue(editorType, 
																	 mutableErrorCounter,
																	 errorMessages,
												           jsonObject, name, 
												           "Modification JSON node identifier does not contain \"grandparent\" key", 
												           39, 
												           reportErrors);				
		}
		/* Check if we have a required key */ 
		if (jsonKeys.contains("parent") == false) {			
			HDLmField.reportErrorNoValue(editorType, 
																	 mutableErrorCounter,
																	 errorMessages,
												           jsonObject, name, 
												           "Modification JSON node identifier does not contain \"parent\" key", 
												           39, reportErrors);				
		}
		/* Get and check the type value in the node identifier JSON */
		jsonElement = jsonObject.get("type");
		if (jsonElement == null) {
			jsonElement = jsonElement;
		}
		if (jsonElement.isJsonNull()) {
			HDLmField.reportErrorNoValue(editorType, 
																	 mutableErrorCounter,
																	 errorMessages,
												           jsonObject, name, 
												           "Modification JSON node identifier for \"type\" is null", 
												           39, 
												           reportErrors);				
		}
		if (jsonElement.isJsonPrimitive() == false) {
			HDLmField.reportErrorNoValue(editorType, 
																	 mutableErrorCounter,
																	 errorMessages,
												           jsonObject, name, 
												           "Modification JSON node identifier for \"type\" is not primitive", 
												           39, 
												           reportErrors);				
		}
		JsonPrimitive   jsonPrimitive = jsonElement.getAsJsonPrimitive();
		String          typeValue = jsonPrimitive.getAsString();
		/* Check if the string value is zero-length or not */
		if (typeValue.length() == 0) {
			HDLmField.reportErrorNoValue(editorType, 
																	 mutableErrorCounter,
																	 errorMessages,
												           jsonObject, name, 
												           "Modification JSON node identifier for \"type\" is a zero-length string", 
												           39, 
												           reportErrors);			
		}		
		/* Check if the string value is a supported type value */
		if (supportedTypeValues.contains(typeValue) == false) {
			HDLmField.reportErrorNoValue(editorType, 
																	 mutableErrorCounter,
																	 errorMessages,
													         jsonObject, name, 
													         "Modification JSON node identifier for \"type\" is an unknown string", 
													         39, 
													         reportErrors);			
		}		
		this.nodeType = typeValue;
		/* Get and check the current element attributes value in the 
		   node identifier JSON */		
		this.nodeAttributes = this.checkAttributes(editorType, 
				                                       mutableErrorCounter,
				                                       errorMessages,
				                                       name, 
				                                       reportErrors,
				                                       "attributes", 
				                                       typeValue,
				                                       true,
				                                       jsonObject);
		/* Get and check the counts values in the node identifier JSON */		
		this.nodeCounts = this.checkCounts(editorType, 
				                               mutableErrorCounter,
				                               errorMessages,
		                                   name, 
		                                   reportErrors,
		                                   "counts",  
		                                   jsonObject);
		/* Get and check the grand parent element attributes value in the 
       node identifier JSON */		
		if (jsonKeys.contains("grandparent") == true) {		
			this.nodeGrand = this.checkAttributes(editorType, 
					                                  mutableErrorCounter,
					                                  errorMessages,
																		        name, reportErrors,
																		        "grandparent", 
																		        null,
																		        false,
																		        jsonObject);
		}
		/* Get and check the parent element attributes value in the 
	     node identifier JSON */		
		this.nodeParent = this.checkAttributes(editorType, 
				                                   mutableErrorCounter,
				                                   errorMessages,
				                                   name, 
				                                   reportErrors,
				                                   "parent", 
				                                   null,
				                                   false,
				                                   jsonObject);
		/* Mark the current node identifier object as disabled if the error count
		   was greater than zero. This is actually done by setting the enabled 
		   field to false. */
    if (mutableErrorCounter.intValue() > 0) {
	    this.nodeEnabled = false;
	    this.nodeMessages = errorMessages;
    }	
  }
	/* Add a perceptual hash value to the attributes for the current set of
	   node identifier values. The caller provides the perceptual hash value
	   and a reference to the node identifier. */
	protected void addPerceptualHash(String perceptualHash) {
  	/* Check if the perceptual hash value passed by the caller is null */
		if (perceptualHash == null) {
		  String  errorText = "Perceptual hash value passed to addPerceptualHash is null";
		  throw new NullPointerException(errorText);
		}
		/* Get and check the attributes map */
		Map<String, Object>   nodeAttributes = this.getNodeAttributes();
		if (nodeAttributes == null)
			HDLmAssertAction(false, "Node attributes not set in node identifier object");
		/* Save the perceptual hash value */
		nodeAttributes.put("phash", perceptualHash);
	}
	/* Check the JSON version of a set of attributes. If the JSON attributes 
	   are correct, return a non-null Map to the caller. The attributes might
	   come form a DOM element or they might come from a parent of a DOM
	   element or they might come from a grand parent of a DOM element. */
	protected Map<String, Object> checkAttributes(HDLmEditorTypes   editorType, 
			                                          MutableInt        mutableErrorCounter, 
			                                          ArrayList<String> errorMessages,
			                                          String            informationTypeName,
			                                          HDLmReportErrors  reportErrors,
			                                          String            sourceKeyName,
			                                          String            searchTypeValue,
			                                          boolean           primaryAttribute,
			                                          JsonObject        jsonObject) {
		/* Declare and define a few local values */
		boolean               tagAttributeFound = false;
		JsonElement           jsonElement = null;
		JsonPrimitive         jsonPrimitive = null;
		Map<String, Object>   attributeValues = new HashMap<String, Object>();
		Set<String>           jsonKeys = null;
		/* Get and check the current/parent element attributes value in the 
       node identifier JSON */
		jsonElement = jsonObject.get(sourceKeyName);
		/* Make sure the JSON element is not a JSON null */
		if (jsonElement.isJsonNull()) {
			String  format = "Modification JSON node identifier for \"%s\" is null";
			String  errorText = String.format(format, sourceKeyName);	
			HDLmField.reportErrorNoValue(editorType, 
					                         mutableErrorCounter,
					                         errorMessages,
	                                 jsonObject, 
	                                 informationTypeName, 
	                                 errorText, 
	                                 39, 
	                                 reportErrors);		
			return null;
		}
		/* Make sure that the JSON element is really a JSON object */
		if (jsonElement.isJsonObject() == false) {
			String  format = "Modification JSON node identifier for \"%s\" is not an object";
			String  errorText = String.format(format, sourceKeyName);	
			HDLmField.reportErrorNoValue(editorType, 
					                         mutableErrorCounter,
					                         errorMessages,
													         jsonObject, 
													         informationTypeName, 
													         errorText,
													         39, 
													         reportErrors);			
			return null;
		}
	  /* Get a JSON object for the (current or parent) attribute keys */
  	jsonObject = jsonElement.getAsJsonObject();
	  jsonKeys = jsonObject.keySet();
	  /* Process each of the attributes */
	  for (String jsonKey : jsonKeys) {
		  /* Get and check the attribute value in the node identifier JSON */
		  jsonElement = jsonObject.get(jsonKey);
		  if (jsonElement.isJsonNull()) {
			  String  format = "Modification JSON node identifier for attribute (%s) is null";
			  String  errorText = String.format(format, jsonKey);						
			  HDLmField.reportErrorNoValue(editorType, 
			  		                         mutableErrorCounter,
			  		                         errorMessages,
												             jsonObject, 
												             informationTypeName, 
												             errorText, 
												             39, 
												             reportErrors);
			  return null;
		  }
		  /* We need to check for a special case here. The JSON element for a 
		     node identifier class attribute will be for an array, not a JSON
		     primitive. This is not an error condition. */
		  if (jsonKey.equals("class")) {
		  	if (jsonElement.isJsonArray() == false) {
		  		String  format = "Modification JSON node identifier for attribute (%s) is not an array";
		  		String  errorText = String.format(format, "class");						
		  		HDLmField.reportErrorNoValue(editorType, 
														  				 mutableErrorCounter,
														  				 errorMessages,
													             jsonObject, informationTypeName, 
													             errorText, 
													             39, 
													             reportErrors);	
		  		return null;
			  }				
			  /* We need to build an array list with all of the class values
			     in it. This array list can be stored in the attribute map. */			 
			  ArrayList<String>   attributeValueArray = new ArrayList<String>(); 
			  JsonArray   jsonArray = jsonElement.getAsJsonArray();
			  /* We now need to check all of the values in the node identifier attributes class array. 
			     All of the values should be strings. */ 
			  for (JsonElement jsonArrayElement : jsonArray) {
			  	/* Check if the current JSON class array entry is a JSON null value.
			  	   This is always an error. We must check for this case and report
			  	   the error. */ 
					if (jsonArrayElement.isJsonNull()) {
						String  format = "Modification JSON node identifier for \"%s\" class array entry is null";
						String  errorText = String.format(format, sourceKeyName);	
						HDLmField.reportErrorNoValue(editorType, 
																				 mutableErrorCounter,
																				 errorMessages,
																         jsonObject, 
																         informationTypeName, 
																         errorText, 
																         39, 
																         reportErrors);		
						return null;
					}
				  /* Check if the current JSON class array entry is a JSON primitive value.
			       The JSON class array entry should always be a JSON primitive value. 
			       Report an error, if the JSON class array entry is not a JSON primitive
			       value. */ 
				  if (jsonArrayElement.isJsonPrimitive() == false) {
					  String  format = "Modification JSON node identifier for \"%s\" class array entry is not primitive";
					  String  errorText = String.format(format, sourceKeyName);		
					  HDLmField.reportErrorNoValue(editorType, 
																	  		 mutableErrorCounter,
																	  		 errorMessages,
														             jsonObject, 
														             informationTypeName, 
														             errorText, 
														             39, 
														             reportErrors);
					  return null;
				  }
				  /* Convert the JSON element for the attributes class array entry to a 
				     JSON primitive. This step allows us to extract the string value from
				     the JSON primitive. The string value is the class array entry value. */
				  JsonPrimitive   jsonArrayPrimitive = jsonArrayElement.getAsJsonPrimitive();
				  /* Check if the current JSON class array entry is a proper string
				     or not. The JSON class array entry must be a string value. Report
				     an error, if the JSON class array entry is not a string. */ 
				  if (jsonArrayPrimitive.isString() == false) {
					  String  format = "Modification JSON node identifier for \"%s\" class array entry is not a string";					  
					  String  errorText = String.format(format, sourceKeyName);		
					  HDLmField.reportErrorNoValue(editorType, 
																	  		 mutableErrorCounter,
																	  		 errorMessages,
														             jsonObject, 
														             informationTypeName, 
														             errorText, 
														             39, 
														             reportErrors);
					  return null;
				  }
          /* Get the actual string value and add it to the array of class
             string values. The class entry is checked below.*/ 
				  String classEntryValue = jsonArrayPrimitive.getAsString();		 
					if (classEntryValue.length() == 0) {
						String  format = "Modification JSON node identifier for \"%s\" class array entry is a zero-length string";
						String  errorText = String.format(format, sourceKeyName);		
						HDLmField.reportErrorNoValue(editorType, 
																				 mutableErrorCounter,
																				 errorMessages,
															           jsonObject, 
															           informationTypeName, 
															           errorText, 
															           39, 
															           reportErrors);	
						return null;
					}	
				  attributeValueArray.add(classEntryValue);
			  }	
			  attributeValues.put(jsonKey, attributeValueArray);
			  continue;
	  	}
		  /* Except for the node identifier class attribute, we should have a JSON 
		     primitive here */ 
		  if (jsonElement.isJsonPrimitive() == false) {
			  String  format = "Modification JSON node identifier for attribute (%s) is not primitive";
			  String  errorText = String.format(format, jsonKey);		
			  HDLmField.reportErrorNoValue(editorType, 
															  		 mutableErrorCounter,
															  		 errorMessages,
												             jsonObject, informationTypeName, 
												             errorText, 
												             39, reportErrors);
			  return null;
		  }
		  /* Convert the JSON element for the attribute value to a JSON
	       primitive. This step allows us to extract the string value 
	       from the JSON primitive. The string value is the attribute 
	       value. */
		  jsonPrimitive = jsonElement.getAsJsonPrimitive();
		  /* Check if the current JSON attribute value is a proper string
	       or not. The JSON attribute value must be a string value. Report
	       an error, if the JSON attribute value is not a string. */ 
		  if (jsonPrimitive.isString() == false) {
			  String  format = "Modification JSON node identifier for attribute (%s) is not a string";					  
			  String  errorText = String.format(format, jsonKey);		
			  HDLmField.reportErrorNoValue(editorType, 
																  	 mutableErrorCounter,
																  	 errorMessages,
														         jsonObject, informationTypeName, 
														         errorText, 
														         39, 
														         reportErrors);
			  return null;
		  }
		  String  attributeValue = jsonPrimitive.getAsString();
		  attributeValues.put(jsonKey, attributeValue);
		  /* Check if we just handled the "tag" attribute. If this is 
		     correct, set a flag. The "tag" attribute is required. */
		  if (jsonKey.equals("tag"))
		  	tagAttributeFound = true;        				
	  }
		/* Report an error if we did not found an attribute with a key of "tag".
		   The attributes are required to contain a "tag" attribute. This 
		   attribute must have a non-blank string value. This is checked
		   below. Note that all sets of attribute (current, parent, etc.)
		   are required to have a "tag" attribute. */
	  if (tagAttributeFound == false) {
		  String  format = "Modification JSON node identifier for attribute (%s) was not found";
	  	String  errorText = String.format(format, "tag");		
		  HDLmField.reportErrorNoValue(editorType, 
		  		                         mutableErrorCounter,
		  		                         errorMessages,
												           jsonObject, 
												           informationTypeName, 
												           errorText, 
												           39, 
												           reportErrors);	
		  return null;
	  }
		/* Make sure the tag value in the node identifier attributes 
		   is a non-blank string */
	  jsonElement = jsonObject.get("tag");
	  if (jsonElement != null) {
	  	/* Check if the current JSON class array entry is a JSON null value.
         This is always an error. We must check for this case and report
         the error. */ 
			if (jsonElement.isJsonNull()) {
				String  format = "Modification JSON node identifier for attribute (%s) is null";
				String  errorText = String.format(format, "tag");	
				HDLmField.reportErrorNoValue(editorType, 
						                         mutableErrorCounter,
						                         errorMessages,
		                                 jsonObject, 
		                                 informationTypeName, 
		                                 errorText, 
		                                 39, 
		                                 reportErrors);		
				return null;
			}
	  /* Check if the current JSON class array entry is a JSON primitive 
       or not. We should always have a JSON primitive here. Report an
       error, if we do not have a JSON primitive here. */ 
		  if (jsonElement.isJsonPrimitive() == false) {
			  String  format = "Modification JSON node identifier for attribute (%s) is not primitive";
			  String  errorText = String.format(format, "tag");		
			  HDLmField.reportErrorNoValue(editorType, 
			  		                         mutableErrorCounter,
			  		                         errorMessages,
	                                   jsonObject, informationTypeName, 
	                                   errorText, 
	                                   39, reportErrors);
		                            	   return null;
		  }
		  jsonPrimitive = jsonElement.getAsJsonPrimitive();
		  if (jsonPrimitive.isString() == false) {
			  String  format = "Modification JSON node identifier for attribute (%s) is not a string";
			  String  errorText = String.format(format, "tag");		
			  HDLmField.reportErrorNoValue(editorType, 
			  		                         mutableErrorCounter,
			  		                         errorMessages,
                                     jsonObject, 
                                     informationTypeName, 
                                     errorText, 
                                     39, 
                                     reportErrors);
			  return null;
		  }
		  /* Get the value of the tag from the node identifier attributes. The tag
		     value is checked below. */ 
		  String attributeTagValue = jsonPrimitive.getAsString();
		  if (attributeTagValue.length() == 0) {
			  String  format = "Modification JSON node identifier for attribute (%s) is a zero-length string";
			  String  errorText = String.format(format, "tag");		
			  HDLmField.reportErrorNoValue(editorType, 
			  		                         mutableErrorCounter,
			  		                         errorMessages,
                                     jsonObject, 
                                     informationTypeName, 
                                     errorText, 
                                     39, 
                                     reportErrors);
			  return null;
		  }
	  }
	  /* Make sure that the node identifier attributes has an entry that 
	     matches the node identifier type. This is required in all cases. 
	     In other words, if the node identifier type is 'id', then the node
	     information attributes must include an 'id' with an actual value.
	     This check only applies to the primary set of attributes. Other sets
	     of attributes (such as the parent attributes) need not have an attriubute
	     that matches the current search type value */
	  if (primaryAttribute) {
			if (jsonKeys.contains(searchTypeValue) == false) {
				String  format = "Modification JSON node identifier attributes doesn't have an entry for type (%s)";
				String  errorText = String.format(format, searchTypeValue);		
				HDLmField.reportErrorNoValue(editorType, 
						                         mutableErrorCounter,
						                         errorMessages,
														         jsonObject, 
														         informationTypeName, 
														         errorText, 
														         39, 
														         reportErrors);		
				return null;
			}
			/* Make sure the value in the node identifier attributes for the current search type 
		     is a non-blank string. This is generally true. However, if we are doing a class
		     search, then the class attribute must have an array value. */
			jsonElement = jsonObject.get(searchTypeValue);
			if (jsonElement != null) {
			  /* We need to check for a special case here. The JSON element for a 
			     node identifier class attribute will be for an array, not a JSON
			     primitive. This is not an error condition. */
				if (searchTypeValue.equals("class")) {
					if (jsonElement.isJsonArray() == false) {
						String  format = "Modification JSON node identifier for attribute (%s) is not an array";
						String  errorText = String.format(format, searchTypeValue);						
						HDLmField.reportErrorNoValue(editorType, 
								                         mutableErrorCounter,
								                         errorMessages,
			                                   jsonObject, informationTypeName, 
			                                   errorText, 
			                                   39, 
			                                   reportErrors);		
						return null;
					}				
				}	
				/* The current search type is not class. This means that the actual value must 
				   be a non-blank string. */ 
				else {
			  	/* Check if the current JSON class array entry is a JSON null value.
	  	       This is always an error. We must check for this case and report
	  	       the error. */ 
					if (jsonElement.isJsonNull()) {
						String  format = "Modification JSON node identifier for attribute (%s) is null";
						String  errorText = String.format(format, searchTypeValue);	
						HDLmField.reportErrorNoValue(editorType, 
								                         mutableErrorCounter,
								                         errorMessages,
				                                 jsonObject, 
				                                 informationTypeName, 
				                                 errorText, 
				                                 39, 
				                                 reportErrors);		
						return null;
					}
				  /* Check if the current JSON class array entry is a JSON primitive 
			       or not. We should always have a JSON primitive here. Report an
			       error, if we do not have a JSON primitive here. */ 
				  if (jsonElement.isJsonPrimitive() == false) {
					  String  format = "Modification JSON node identifier for attribute (%s) is not primitive";
					  String  errorText = String.format(format, searchTypeValue);		
					  HDLmField.reportErrorNoValue(editorType, 
					  		                         mutableErrorCounter,
					  		                         errorMessages,
		                                     jsonObject, 
		                                     informationTypeName, 
		                                     errorText, 
		                                     39, 
		                                     reportErrors);
					  return null;
				  }
					jsonPrimitive = jsonElement.getAsJsonPrimitive();
					if (jsonPrimitive.isString() == false) {
						String  format = "Modification JSON node identifier for attribute (%s) is not a string";
						String  errorText = String.format(format, searchTypeValue);		
						HDLmField.reportErrorNoValue(editorType, 
								                         mutableErrorCounter,
								                         errorMessages,
			                                   jsonObject, 
			                                   informationTypeName, 
			                                   errorText, 
			                                   39, 
			                                   reportErrors);
						return null;
					}
					/* Get the value of the node identifier attribute. The attribute 
					   value is checked below. */ 
					String attributeTagValue = jsonPrimitive.getAsString();
					if (attributeTagValue.length() == 0) {
						String  format = "Modification JSON node identifier for attribute (%s) is a zero-length string";
						String  errorText = String.format(format, searchTypeValue);		
						HDLmField.reportErrorNoValue(editorType, 
								                         mutableErrorCounter,
								                         errorMessages,
			                                   jsonObject, 
			                                   informationTypeName, 
			                                   errorText, 
			                                   39, 
			                                   reportErrors);				
						return null;
					}
				}
			}
		}
		/* The node identifier attributes may contain a class attribute. The class attribute
		   is optional. If the class attribute is present, then it must be an array of strings.
		   All of this is checked below. */ 
		if (jsonKeys.contains("class")) {
			jsonElement = jsonObject.get("class");
			if (jsonElement.isJsonArray() == false) {
				String  format = "Modification JSON node identifier for attribute (%s) is not an array";
				String  errorText = String.format(format, "class");		
				HDLmField.reportErrorNoValue(editorType, 
						                         mutableErrorCounter,
						                         errorMessages,
	                                   jsonObject, 
	                                   informationTypeName, 
	                                   errorText, 
	                                   39, 
	                                   reportErrors);			
				return null;
			}			
			/* We need to make sure that the attributes class array is not empty. In other words,
			   at least one actual class is specified. */
			JsonArray   jsonArray = jsonElement.getAsJsonArray();
			int         jsonArraySize = jsonArray.size();
			if (jsonArraySize == 0) {
				String  format = "Modification JSON node identifier for attribute (%s) is an empty array";
				String  errorText = String.format(format, "class");		
				HDLmField.reportErrorNoValue(editorType, 
						                         mutableErrorCounter,
						                         errorMessages,
	                                   jsonObject, 
	                                   informationTypeName, 
	                                   errorText, 
	                                   39, reportErrors);			
				return null;
			}	
			/* We now need to check all of the values in the node identifier attributes class array. 
			   All of the values should be strings. */ 
			for (JsonElement jsonArrayElement : jsonArray) {
		  	/* Check if the current JSON class array entry is a JSON null value.
	  	     This is always an error. We must check for this case and report
	  	     the error. */ 
				if (jsonArrayElement.isJsonNull()) {
					String  format = "Modification JSON node identifier for \"%s\" class array entry is null";
					String  errorText = String.format(format, sourceKeyName);	
					HDLmField.reportErrorNoValue(editorType, 
							                         mutableErrorCounter,
							                         errorMessages,
			                                 jsonObject, 
			                                 informationTypeName, 
			                                 errorText, 
			                                 39, 
			                                 reportErrors);		
					return null;
				}
			  /* Check if the current JSON class array entry is a JSON primitive 
		       or not. We should always have a JSON primitive here. Report an
		       error, if we do not have a JSON primitive here. */ 
			  if (jsonArrayElement.isJsonPrimitive() == false) {
				  String  format = "Modification JSON node identifier for \"%s\" class array entry is not primitive";
				  String  errorText = String.format(format, sourceKeyName);		
				  HDLmField.reportErrorNoValue(editorType, 
				  		                         mutableErrorCounter,
				  		                         errorMessages,
	                                     jsonObject, 
	                                     informationTypeName, 
	                                     errorText, 
	                                     39, 
	                                     reportErrors);
				  return null;
			  }
				JsonPrimitive jsonArrayPrimitive = jsonArrayElement.getAsJsonPrimitive();
				if (jsonArrayPrimitive.isString() == false) {
					String  format = "Modification JSON node identifier for \"%s\" class array entry is not a string";
					String  errorText = String.format(format,  sourceKeyName);		
					HDLmField.reportErrorNoValue(editorType, 
							                         mutableErrorCounter,
							                         errorMessages,
		                                   jsonObject, 
		                                   informationTypeName, 
		                                   errorText, 
		                                   39, 
		                                   reportErrors);		
					return null;
				}
				/* Get the value of the class entry from the class array. The class entry 
				   is checked below. */
				String classEntryValue = jsonArrayPrimitive.getAsString();
				if (classEntryValue.length() == 0) {
					String  format = "Modification JSON node identifier for \"%s\" class array entry is a zero-length string";
					String  errorText = String.format(format, sourceKeyName);		
					HDLmField.reportErrorNoValue(editorType, 
							                         mutableErrorCounter, 
							                         errorMessages,
		                                   jsonObject, 
		                                   informationTypeName, 
		                                   errorText, 
		                                   39, 
		                                   reportErrors);	
					return null;
				}				
			}			
		}
		return attributeValues;
	}	
  /* Check the JSON version of the counts values. If the JSON counts values 
	   are correct, return a non-null Map to the caller. The counts are
	   always part of the node identifier JSON. */
	protected Map<String, Integer> checkCounts(HDLmEditorTypes   editorType, 
			                                       MutableInt        mutableErrorCounter,
			                                       ArrayList<String> errorMessages,
			                                       String            informationTypeName,
			                                       HDLmReportErrors  reportErrors,
			                                       String            sourceKeyName,
			                                       JsonObject        jsonObject) {
		/* Declare and define a few local values */
		JsonElement           jsonElement = null;
		JsonPrimitive         jsonPrimitive = null;
		Map<String, Integer>  countsValues = new HashMap<String, Integer>();
		Set<String>           jsonKeys = null;
		List<String>          supportedTypeValues = List.of("tag", "name", "id", "class");
		/* Get and check the current element attributes value in the 
	     node identifier JSON */
		jsonElement = jsonObject.get(sourceKeyName);
		/* Make sure the JSON element is not a JSON null */
		if (jsonElement.isJsonNull()) {
			String  format = "Modification JSON node identifier for \"%s\" is null";
			String  errorText = String.format(format, sourceKeyName);	
			HDLmField.reportErrorNoValue(editorType, 
					                         mutableErrorCounter,
					                         errorMessages,
	                                 jsonObject, 
	                                 informationTypeName, 
	                                 errorText, 
	                                 39, 
	                                 reportErrors);		
			return null;
		}
		/* Make sure that the JSON element is really a JSON object */
		if (jsonElement.isJsonObject() == false) {
			String  format = "Modification JSON node identifier for \"%s\" is not an object";
			String  errorText = String.format(format, sourceKeyName);	
			HDLmField.reportErrorNoValue(editorType, 
					                         mutableErrorCounter,
					                         errorMessages,
														       jsonObject, 
														       informationTypeName, 
														       errorText,
														       39, 
														       reportErrors);			
			return null;
		}
	  /* Get a JSON object for the counts values */
	  jsonObject = jsonElement.getAsJsonObject();
	  jsonKeys = jsonObject.keySet();
	  /* Get and check the number of keys */
	  int   jsonKeysSize = jsonKeys.size();
	  if (jsonKeysSize < 1) {
		  String  format = "Modification JSON node identifier for \"%s\" is empty";
		  String  errorText = String.format(format, sourceKeyName);	
			HDLmField.reportErrorNoValue(editorType, 
					                         mutableErrorCounter,
					                         errorMessages,
                                   jsonObject, informationTypeName, 
                                   errorText, 
                                   39, 
                                   reportErrors);	
			return null;	  	
	  }
	  if (jsonKeysSize > 4) {
		  String  format = "Modification JSON node identifier for \"%s\" has too many entries";
		  String  errorText = String.format(format, sourceKeyName);	
			HDLmField.reportErrorNoValue(editorType, 
					                         mutableErrorCounter,
					                         errorMessages,
                                   jsonObject, 
                                   informationTypeName, 
                                   errorText, 
                                   39, 
                                   reportErrors);	
			return null;	  	
	  }
	  /* Process each of the counts values */
	  for (String jsonKey : jsonKeys) {
			/* Check if the counts key value is a supported type value */
			if (supportedTypeValues.contains(jsonKey) == false) {
			  String  format = "Modification JSON node identifier for count value (%s) is not supported";
			  String  errorText = String.format(format, jsonKey);		
				HDLmField.reportErrorNoValue(editorType, 
						                         mutableErrorCounter,
						                         errorMessages,
	                                   jsonObject, 
	                                   informationTypeName, 
	                                   errorText, 
	                                   39, 
	                                   reportErrors);	
				return null;
			}	
		  /* Get and check the count value in the node identifier JSON */
		  jsonElement = jsonObject.get(jsonKey);
		  if (jsonElement.isJsonNull()) {
			  String  format = "Modification JSON node identifier for count value (%s) is null";
			  String  errorText = String.format(format, jsonKey);						
			  HDLmField.reportErrorNoValue(editorType, 
			  		                         mutableErrorCounter,
			  		                         errorMessages,
	                                   jsonObject, 
	                                   informationTypeName, 
	                                   errorText, 
	                                   39, 
	                                   reportErrors);
			  return null;
		  }		  
		  /* We should always have a JSON primitive value here */ 
		  if (jsonElement.isJsonPrimitive() == false) {
			  String  format = "Modification JSON node identifier for count value (%s) is not primitive";
			  String  errorText = String.format(format, jsonKey);		
			  HDLmField.reportErrorNoValue(editorType, 
			  		                         mutableErrorCounter,
			  		                         errorMessages,
	                                   jsonObject, 
	                                   informationTypeName, 
	                                   errorText, 
	                                   39, 
	                                   reportErrors);
			  return null;
		  }
		  /* Convert the JSON element for the count value to a JSON
	       primitive. This step allows us to extract the numeric value 
	       from the JSON primitive. The numeric value is the count 
	       value. */
		  jsonPrimitive = jsonElement.getAsJsonPrimitive();
		  /* Check if the current JSON count value is a proper number or
	       not. The JSON count value must be a numeric value. Report an
	       error, if the JSON count value is not a number. */ 
		  if (jsonPrimitive.isNumber() == false) {
			  String  format = "Modification JSON node identifier for count value (%s) is not a number";					  
			  String  errorText = String.format(format, jsonKey);		
			  HDLmField.reportErrorNoValue(editorType, 
			  		                         mutableErrorCounter,
			  		                         errorMessages,
	                                   jsonObject, 
	                                   informationTypeName, 
	                                   errorText, 
	                                   39, 
	                                   reportErrors);
			  return null;
		  }
		  /* Get and check the actual integer value */
		  Integer   integerValue = jsonPrimitive.getAsInt();
		  if (integerValue <= 0) {
			  String  format = "Modification JSON node identifier for count value (%s) is invalid (%d)";					  
			  String  errorText = String.format(format, jsonKey, integerValue);		
			  HDLmField.reportErrorNoValue(editorType, 
			  		                         mutableErrorCounter,
			  		                         errorMessages,
	                                   jsonObject, informationTypeName, 
	                                   errorText, 
	                                   39, 
	                                   reportErrors);
			  return null;
		  }
		  countsValues.put(jsonKey, integerValue);       				
	  } 
		return countsValues;
	}	
	/* This routine checks if it is passed a valid node identifier JSON object.
	   All of the node identifier JSON object fields must be present and they 
	   must be valid). The return value is a class instance that may (or may 
	   not) contain an error message. */  
	@SuppressWarnings("unused")
	protected static HDLmResponse  checkNodeIdenJsonObj(final JsonElement jsonElement) {
		/* Check if the JSON element passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element used to check tree JSON is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few fields for use below */
		String  jsonFieldName;
		/* Allocate the response object. This object is used to 
 	     return error messages to the caller. */
	  HDLmResponse  outResponse = new HDLmResponse();
		/* Set the error count to zero. The error count is incremented each time an
		   error is detected. If the final error count (for the current tree element) 
		   is greater than zero, the current modification object is disabled (the enabled
		   field is set false). Note that a reference is used below so that the error
		   count can be updated by the routines called using error count.*/
		MutableInt  errorCounter = new MutableInt(0);		
		/* Build an array list for error message strings. Each error
		   message is stored in this array list. */
		ArrayList<String>   errorMessages = new ArrayList<String>();
		if (errorMessages == null) {
			String  errorText = "Error message ArrayList allocation in checkNodeIdenJsonObj is null";
			throw new NullPointerException(errorText);
		}	
		HDLmEditorTypes  editorType = HDLmEditorTypes.PASS;
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element passed to checkNodeIdenJsonObj is not a JSON object");
		}
		/* Check if the JSON element is a JSON null */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element used to checkNodeIdenJsonObj is JSON null");
		}
		/* Get the list of keywords and values in the JSON object */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		Set<String>   jsonKeys = jsonObject.keySet();		
		/* Try to convert the JSON object to a node identifer. 
		   This will have the effect of checking each field
		   and setting error value as need be. */
		HDLmNodeIden  localNodeIden = new HDLmNodeIden(jsonElement);  
		/* Check if the JSON node identifier object is valid */
	  if (localNodeIden.isUsable() == false) {
	  	errorCounter.increment();
	  	ArrayList<String>   localNodeMessages = localNodeIden.getNodeMessages();
	  	if (localNodeMessages != null) 
	  		errorMessages = localNodeMessages;
	  }	
		/* Check if any errors were found. If any errors were found, store 
		   them in the response object */ 
		int     errorCountInt = errorCounter.intValue(); 
		if (errorCountInt > 0) {
			outResponse.setReturnNumber(errorCountInt);
			outResponse.setErrorMessage(errorMessages.get(0));			
		}
		else {
			outResponse.setReturnCodeZero();	
			outResponse.setReturnNumberZero();
		}
		return outResponse;
	}	   	
	/* Get a copy of the current node identifier. The copy is returned to the
	   caller as a standard object. Note that this process should remove all of
	   the permissions from the returned object. In other words, the all of the
	   properties of the returned object should be public. These comments do not 
	   apply to the Java version of this routine. The Java version just makes a 
	   deep copy of the current node identifier object. */
	protected HDLmNodeIden copyNodeIden() {
		HDLmNodeIden newObj = new HDLmNodeIden(this);
		return newObj;
	}	
	/* Get the node attributes from a node identifier object and return them to the caller */
	protected Map<String, Object> getNodeAttributes() {
		return this.nodeAttributes;
	}
	/* Get the node count information and return it to the caller */
	protected Map<String, Integer> getNodeCounts() {
		return this.nodeCounts;
	}
	/* Get the node identifier enablement status and return it to the caller */
	protected Boolean getNodeEnabled() {
		return this.nodeEnabled;
	}
	/* Get the grand parent node attributes from a node identifier object and 
     return them to the caller */
  protected Map<String, Object> getNodeGrand() {
	  return this.nodeGrand;
  }
	/* Get the node messages (if any) from a node identifier object
	   and return them to the caller */
  protected ArrayList<String>  getNodeMessages() {
    return this.nodeMessages;
  }
	/* Get the parent node attributes from a node identifier object and 
	   return them to the caller */
	protected Map<String, Object>  getNodeParent() {
		return this.nodeParent;
	}
	/* This routine returns a reference to the one and only empty static 
	   node identifier */ 
	protected static HDLmNodeIden  getEmptyNodeIden() {
		return emptyNodeIden;
	}
	/* Get the node type from a node identifier object and return it to the caller */
	protected String getNodeType() {
		return this.nodeType;
	}
	/* Check if a set of node identifier values is usable or not. The 
	   node identifier is only usable, if all of the fields are set. */
	protected boolean isUsable() {
		if (this.nodeType != null       && 
			  this.nodeAttributes != null &&
			  this.nodeCounts != null     &&
			  this.nodeParent != null     &&
			  this.nodeEnabled == true)
		  return true;
		return false;
	}
	/* The method below processes a node identifier object. 
     The JSON element is converted to a JSON object and
     various changes are mode. */  
  protected static void processJsonNodeIden(JsonElement jsonElement) {	
  	/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
		  String  errorText = "Node tree value passed to processJsonNodeIden is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the current JSON element is really a JSON object */
		if (!jsonElement.isJsonObject())
			HDLmAssertAction(false, "JSON element passed to processJsonNodeIden is not a JSON object");
		/* Get a few values from the node identifier */
		Boolean   enabledBoolean = HDLmJson.getJsonBoolean(jsonElement, "nodeEnabled");
		if (enabledBoolean == null)
			enabledBoolean = false;
		String  typeString = HDLmJson.getJsonString(jsonElement, "nodeType");
		if (typeString == null)
			HDLmAssertAction(false, "Type value can not be obtained from the JSON element passed to processJsonNodeIden");
		JsonElement   nodeAttributes = HDLmJson.getJsonValue(jsonElement, "nodeAttributes");
		if (nodeAttributes == null)
			nodeAttributes = new JsonObject();	
		else 
			processJsonNodeIdenAttributes(nodeAttributes);
		JsonElement   nodeCounts = HDLmJson.getJsonValue(jsonElement, "nodeCounts");
		if (nodeCounts == null)
			nodeCounts = new JsonObject();	
		JsonElement   nodeGrand = HDLmJson.getJsonValue(jsonElement, "nodeGrand");
		if (nodeGrand == null)
			nodeGrand = new JsonObject();	
		else
			processJsonNodeIdenGrand(nodeGrand);
		JsonElement   nodeParent = HDLmJson.getJsonValue(jsonElement, "nodeParent");
		if (nodeParent == null)
			nodeParent = new JsonObject();	
		else
			processJsonNodeIdenParent(nodeParent);
		/* Remove a set of keys from the current JSON object */
		HDLmJson.removeJsonKey(jsonElement, "nodeEnabled");
		HDLmJson.removeJsonKey(jsonElement, "nodeType");
		HDLmJson.removeJsonKey(jsonElement, "nodeAttributes");
		HDLmJson.removeJsonKey(jsonElement, "nodeCounts");
		HDLmJson.removeJsonKey(jsonElement, "nodeGrand");
		HDLmJson.removeJsonKey(jsonElement, "nodeParent");
		/* Put the values back in the JSON object in a specific order */
		HDLmJson.setJsonString(jsonElement, "type", typeString);
		HDLmJson.setJsonValue(jsonElement, "attributes", nodeAttributes);
		HDLmJson.setJsonValue(jsonElement, "counts", nodeCounts);
		HDLmJson.setJsonValue(jsonElement, "grand", nodeGrand);
		HDLmJson.setJsonValue(jsonElement, "parent", nodeParent);
  }	
  /* The method below processes a node identifier attributes object. 
     The JSON element is converted to a JSON object and various changes
     are mode. */  
	protected static void processJsonNodeIdenAttributes(JsonElement jsonElement) {	
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
		  String  errorText = "Node tree value passed to processJsonNodeIdenAttributes is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the current JSON element is really a JSON object */
		if (!jsonElement.isJsonObject())
			HDLmAssertAction(false, "JSON element passed to processJsonNodeIdenAttribute is not a JSON object");
		/* Get a few values from the node identifier attributes */
		boolean   classFound = true;
		JsonElement   classElement = HDLmJson.getJsonValue(jsonElement, "class");
		if (classElement == null) {
			classFound = false;
			JsonArray   jsonArray = new JsonArray();
			classElement = jsonArray;
		}
		boolean   hrefFound = true;
		String  hrefString = HDLmJson.getJsonString(jsonElement, "href");
		if (hrefString == null) {
			hrefFound = false;
			hrefString = ""; 
		}
		boolean   innerFound = true;
		String  innerString = HDLmJson.getJsonString(jsonElement, "innertext");
		if (innerString == null) {
			innerFound = false;
			innerString = "";
		}
		boolean   phashFound = true;
		String  phashString = HDLmJson.getJsonString(jsonElement, "phash");
		if (phashString == null) {
			phashFound = false;
			phashString = "";
		}
		boolean   relFound = true;
		String  relString = HDLmJson.getJsonString(jsonElement, "rel");
		if (relString == null) {
			relFound = false;
			relString = ""; 
		}
		boolean   styleFound = true;
		String  styleString = HDLmJson.getJsonString(jsonElement, "style");
		if (styleString == null) {
			styleFound = false;
			styleString = "";
		}
		String  targetString = HDLmJson.getJsonString(jsonElement, "target");
		if (targetString == null)
			targetString = "";
		String  tagString = HDLmJson.getJsonString(jsonElement, "tag");
		if (tagString == null)
			tagString = "";		
		/* Remove a set of keys from the current JSON object */
		if (classFound)
			HDLmJson.removeJsonKey(jsonElement, "class");
		if (hrefFound)
			HDLmJson.removeJsonKey(jsonElement, "href");
		if (innerFound)
			HDLmJson.removeJsonKey(jsonElement, "innertext");
		if (phashFound)
			HDLmJson.removeJsonKey(jsonElement, "phash");
		if (relFound)
			HDLmJson.removeJsonKey(jsonElement, "rel");
		if (styleFound)
			HDLmJson.removeJsonKey(jsonElement, "style");
		HDLmJson.removeJsonKey(jsonElement, "target");
		HDLmJson.removeJsonKey(jsonElement, "tag");
		/* Put the values back in the JSON object in a specific order */
		if (hrefFound)
			HDLmJson.setJsonString(jsonElement, "href", hrefString);
		if (classFound)
			HDLmJson.setJsonValue(jsonElement, "class", classElement);
		if (!targetString.equals(""))
	  	HDLmJson.setJsonString(jsonElement, "target", targetString);
		if (relFound)
			HDLmJson.setJsonString(jsonElement, "rel", relString);
		if (styleFound)
			HDLmJson.setJsonString(jsonElement, "style", styleString);
		HDLmJson.setJsonString(jsonElement, "tag", tagString);
		if (innerFound)
			HDLmJson.setJsonString(jsonElement, "innertext", innerString);
		if (phashFound)
			HDLmJson.setJsonString(jsonElement, "phash", phashString);
	}	
	/* The method below processes a node identifier grand parent object. 
     The JSON element is converted to a JSON object and various changes 
     are mode. */  
	protected static void processJsonNodeIdenGrand(JsonElement jsonElement) {	
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
		  String  errorText = "Node tree value passed to processJsonNodeIdenGrand is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the current JSON element is really a JSON object */
		if (!jsonElement.isJsonObject())
			HDLmAssertAction(false, "JSON element passed to processJsonNodeIdenGrand is not a JSON object");
		/* Get a few values from the node identifier grand parent */
		boolean   innerFound = true;
		String  innerString = HDLmJson.getJsonString(jsonElement, "innertext");
		if (innerString == null) {
			innerFound = false;
			innerString = "";
		}
		String  tagString = HDLmJson.getJsonString(jsonElement, "tag");
		if (tagString == null)
			tagString = "";		
		String  targetString = HDLmJson.getJsonString(jsonElement, "target");
		if (targetString == null)
			targetString = "";		
		/* Remove a set of keys from the current JSON object */
		if (innerFound)
			HDLmJson.removeJsonKey(jsonElement, "innertext");
		HDLmJson.removeJsonKey(jsonElement, "tag");
		HDLmJson.removeJsonKey(jsonElement, "target");
		/* Put the values back in the JSON object in a specific order */
		if (!targetString.equals(""))
		  HDLmJson.setJsonString(jsonElement, "target", targetString);
		HDLmJson.setJsonString(jsonElement, "tag", tagString);
		if (innerFound)
			HDLmJson.setJsonString(jsonElement, "innertext", innerString);
	}
  /* The method below processes a node identifier parent object. 
     The JSON element is converted to a JSON object and various 
     changes are mode. */  
	protected static void processJsonNodeIdenParent(JsonElement jsonElement) {	
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
		  String  errorText = "Node tree value passed to processJsonNodeIdenParent is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the current JSON element is really a JSON object */
		if (!jsonElement.isJsonObject())
			HDLmAssertAction(false, "JSON element passed to processJsonNodeIdenParent is not a JSON object");
		/* Get a few values from the node identifier parent */
		boolean   innerFound = true;
		String  innerString = HDLmJson.getJsonString(jsonElement, "innertext");
		if (innerString == null) {
			innerFound = false;
			innerString = "";
		}
		String  tagString = HDLmJson.getJsonString(jsonElement, "tag");
		if (tagString == null)
			tagString = "";		
		String  targetString = HDLmJson.getJsonString(jsonElement, "target");
		if (targetString == null)
			targetString = "";		
		/* Remove a set of keys from the current JSON object */
		if (innerFound)
			HDLmJson.removeJsonKey(jsonElement, "innertext");
		HDLmJson.removeJsonKey(jsonElement, "tag");
		HDLmJson.removeJsonKey(jsonElement, "target");
		/* Put the values back in the JSON object in a specific order */
		if (!targetString.equals(""))
		  HDLmJson.setJsonString(jsonElement, "target", targetString);
		HDLmJson.setJsonString(jsonElement, "tag", tagString);
		if (innerFound)
			HDLmJson.setJsonString(jsonElement, "innertext", innerString);
	}
	/* Set the node identifier enablement status to a null value */
	protected void         setNodeEnabledNull() {
		this.nodeEnabled = null;
	}
}
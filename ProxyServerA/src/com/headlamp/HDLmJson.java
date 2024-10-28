package com.headlamp;

import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
/**
 * HDLmJson short summary.
 *
 * HDLmJson description.
 *
 * @version 1.0
 * @author Peter
 */
/* The gson library (from Google) can be used to do all sort 
   of JSON related conversions. See the examples below.
   
   Converting a tree of Java objects (POJOs) to a JsonElement tree
     JsonElement   jsonElement = gson.toJsonTree(nodeTree)
     
   Converting a tree of JsonElement(s) to a JSON string
     String  jsonStr = gson.toJson(jsonElement)
     
   Converting a JSON string to a tree of JsonElement(s)
     JsonParser   parser = new JsonParser()  
	   JsonElement  topNode = parser.parse(curValue) 
	   
	 Converting a tree of JsonElement(s) to a Java Object (a POJO) 
	   HDLmSession  sessionObj = gsonInstance.fromJson(sessionJsonElements, 
	                                                   HDLmSession.class) 
	                                                   
	 Converting a Java object (a POJO) to a JSON string
	   Gson     gsonInstance = HDLmMain.gsonMain 
		 String   jsonString = gsonInstance.toJson(this)
		 		 	                                                   
	 Converting a Java object (a POJO) to a JSON object
	   Gson         gsonInstance = HDLmMain.gsonMain 
		 JsonObject   newJsonOject = gson.toJsonTree(object).getAsJsonObject();
	                                                    
	 Converting a JSON string to a Java object (a POJO)
	   HDLmSession  sessionObj = gsonInstance.fromJson(sessionJsonString, 
	                                                   HDLmSession.class) */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmJson {
	/* The next statement initializes logging to some degree. Note that having the
     slf4j jars and the log4j jars in the classpath also plays some role in
     logging initialization.	 */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmJson.class);
	/* This class can never be instantiated */
	private HDLmJson() {}
	/* The method below adds an array property to a JSON object. The
     JSON object must already exist */ 
	public static void     addArrayToJsonObject(JsonObject jsonObject, final String jsonName, final JsonArray jsonValue) {	
		/* Check one or more values passed by the caller */
		if (jsonObject == null) {
			String   errorText = "JSON object reference passed to addArrayToJsonObject is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the JSON name reference is null */
		if (jsonName == null) {
			String   errorText = "JSON name string reference passed to addArrayToJsonObject is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the JSON array reference is null */
		if (jsonValue == null) {
			String   errorText = "JSON array value reference passed to addArrayToJsonObject is null";
			throw new NullPointerException(errorText);			
		}
		/* Add the JSON array property to the JSON object */
		jsonObject.add(jsonName, jsonValue);
		return;
	}
	/* The method below adds a number property to a JSON object. The
     JSON object must already exist */ 
	public static void     addNumberToJsonObject(JsonObject jsonObject, final String jsonName, final double jsonValue) {	
		/* Check one or more values passed by the caller */
		if (jsonObject == null) {
			String   errorText = "JSON object reference passed to addNumberToJsonObject is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the JSON name reference is null */
		if (jsonName == null) {
			String   errorText = "JSON name string reference passed to addNumberToJsonObject is null";
			throw new NullPointerException(errorText);			
		}
		/* Add the JSON number property to the JSON object */
		jsonObject.addProperty(jsonName, jsonValue);
		return;
	}
	/* The method below adds a JSON object to a JSON array. The
     JSON array must already exist */ 
	public static void     addObjectToJsonArray(JsonArray jsonArray, final JsonObject jsonObject) {	
		/* Check one or more values passed by the caller */
		if (jsonArray == null) {
			String   errorText = "JSON array reference passed to addObjectToJsonArray is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the JSON object reference is null */
		if (jsonObject == null) {
			String   errorText = "JSON object reference passed to addObjectToJsonArray is null";
			throw new NullPointerException(errorText);			
		}
		/* Add the JSON Object to the JSON array as a new entry */
		jsonArray.add(jsonObject);
		return;
	}
	/* The method below adds a string property to a JSON object. The
	   JSON object must already exist */ 
	public static void     addStringToJsonObject(JsonObject jsonObject, final String jsonName, final String jsonValue) {	
		/* Check one or more values passed by the caller */
		if (jsonObject == null) {
			String   errorText = "JSON object reference passed to addStringToJsonObject is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the JSON name reference is null */
		if (jsonName == null) {
			String   errorText = "JSON name string reference passed to addStringToJsonObject is null";
			throw new NullPointerException(errorText);			
		}
		/* Check if the JSON value reference is null */
		if (jsonValue == null) {
			String   errorText = "JSON value string reference passed to addStringToJsonObject is null";
			throw new NullPointerException(errorText);			
		}
		/* Add the JSON string property to the JSON object */
		jsonObject.addProperty(jsonName, jsonValue);
		return;
	}		
	/* This routine builds a JSON string (containing a JSON array) using
	   the values passed to it. The caller must provide the field names
	   and the data (an ArrayList of values) to be formatted as JSON. The 
	   JSON field names are actually obtained from the HTML column headings. */
	protected static String buildJsonFromList(ArrayList<String> headings, 
		                                      	ArrayList<String> data) {
		StringBuilder  rv = new StringBuilder();
	  int  numCols = headings.size();
	  int  numValues = data.size();
	  /* Convert each of the headings to a field name */
	  ArrayList<String>  fieldNames = new ArrayList<String>();
	  for (int i = 0; i < numCols; i++) {
	  	String  heading = headings.get(i);
	  	heading = HDLmString.removeAllSubstring(heading, "<br/>");
	  	heading = HDLmString.removeAllCharacter(heading, ' ');
	  	heading = heading.toLowerCase();
	  	fieldNames.add(heading);  	
	  }
	  /* Start the output JSON */
	  rv.append('[');
	  /* Add each of the data values to the output JSON */
	  for (int i = 0; i < numValues; i += numCols) {
	  	/* Check if we need to add some formatting characters */
	  	if (i > 0) 
	  		rv.append(", ");
	  	/* Start the current JSON object */
	  	rv.append('{');
	  	for (int j = 0; j < numCols; j++) {
		  	/* Check if we need to add some formatting characters */
		  	if (j > 0) 
		  		rv.append(", ");
		  	/* Add the current field name */
	  		rv.append('"');
	  		rv.append(fieldNames.get(j));
	  		rv.append("\": ");
	  		/* Get the current data value. Check if the data value is
	  		   missing. */ 
	  		String  curData = data.get(i+j);
	  		/* Check for something we treat as a missing value */
	  		if (curData.equals("-"))
	  			rv.append("null");
	  		/* We have an actual value. The value may or may not be 
	  		   a number */
	  		else {
	  			if (HDLmJson.isJsonNumber(curData)) 
	  				rv.append(curData);
	  			/* We don't have a valid JSON number. We will handle the 
	  			   current value as a string. This is not an error of some
	  			   kind. */ 
	  			else {	  				
		  			rv.append('"');  
		  			rv.append(curData);
			  		rv.append('"');    
	  			}
	  		} 		
	  	}
	  	/* Terminate the current JSON object */
	  	rv.append('}');
	  }
	  /* Terminate the output JSON */
	  rv.append(']');	 
	  return rv.toString();
	}
	/* The method below build a string containing JSON from a passed 
	   map. The map has zero or more keys and values. The string is
	   returned to the caller. */ 
	public static String   buildJsonFromMap(Map<String, String>  inputMap) {
		/* Check one or more values passed by the caller */
		if (inputMap == null) {
			String   errorText = "Input map reference passed to buildJsonFromMap is null";
			throw new NullPointerException(errorText);			
		}
		/* Build the JSON string from the dictionary */
		String  jsonStr = HDLmMain.gsonSerializeNullsMain.toJson(inputMap);
		return jsonStr;
	}
	/* The method below creates an initially empty JSON array and 
     returns it to the caller */ 
	public static JsonArray  createJsonArray() {	
		/* Create the new JSON array */
		JsonArray   newJsonArray = new JsonArray();
		/* Check if the new JSON array is a null value */
		if (newJsonArray == null) {
			HDLmAssertAction(false, "JSON array not created by new");
		}
		return newJsonArray;
	}
	/* The method below creates an initially empty JSON object and 
	   returns it to the caller */ 
	public static JsonObject  createJsonObject() {	
		/* Create the new JSON object */
		JsonObject  newJsonObject = new JsonObject();
		/* Check if the new JSON object is a null value */
		if (newJsonObject == null) {
			HDLmAssertAction(false, "JSON object not created by new");
		}
		return newJsonObject;
	}
  /* The method below returns a JSON array value from a JSON element. 
	   This will only work if the JSON element is actually a JSON object
	   and if the object contains the desired key. This routine will
	   return a null value if the desired key cannot be found in the JSON.
	   This routine will fail if the JSON is not a JSON object. */ 
	public static JsonArray getJsonArray(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to getJsonObject is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to getJsonObject is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a JSON null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			return null;
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		JsonElement   jsonFieldElement = jsonObject.get(key);
		if (!jsonFieldElement.isJsonArray()) {
			HDLmAssertAction(false, "JSON field element is not a JSON array value");
		}
		/* Get the JSON array value */
	  JsonArray   jsonFieldArray = jsonFieldElement.getAsJsonArray();
		if (!jsonFieldArray.isJsonArray()) {
			HDLmAssertAction(false, "JSON field element is not a JSON array value");
		}		  
	  return jsonFieldArray;		
	}
  /* The method below returns a boolean value from a JSON element. 
     This will only work if the JSON element is actually a
     JSON object and if the object contains the desired key.
     This routine will return a null value if the desired
     key cannot be found in the JSON. This routine will fail
     if the JSON is not a JSON object. */ 
	public static Boolean getJsonBoolean(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to getJsonBoolean is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to getJsonBoolean is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			return null;
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		JsonElement   jsonFieldElement = jsonObject.get(key);
		if (!jsonFieldElement.isJsonPrimitive()) {
			HDLmAssertAction(false, "JSON field element is not a JSON primitive value");
		}
		/* Get the JSON primitive value */
	  JsonPrimitive   jsonPrimitive = jsonFieldElement.getAsJsonPrimitive();
		if (!jsonPrimitive.isBoolean()) {
			HDLmAssertAction(false, "JSON field element is not a JSON boolean value");
		}	
	  Boolean   jsonBoolean = jsonPrimitive.getAsBoolean();
	  return jsonBoolean;		
	}
	/* The method below returns a double value from a JSON element. 
	   The value returned is actually an object with a double in it.
	   This will only work if the JSON element is actually a
 	   JSON object and if the object contains the desired key.
	   This routine will return a null value if the desired
	   key cannot be found in the JSON. This routine will fail
	   if the JSON is not a JSON object. */ 
	public static Double   getJsonDouble(JsonElement jsonElement, String key) {
	 /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	   String  errorText = "JSON element passed to getJsonDouble is null";
			throw new NullPointerException(errorText);
	 }	
	 /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to getJsonDouble is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			return null;
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		JsonElement   jsonFieldElement = jsonObject.get(key);
		/* Check if the JSON field element is null or a JSON null.
		   This can (will) really happen if the index value is null. */ 
		if (jsonFieldElement == null || 
		    jsonFieldElement.isJsonNull())
			return null;
		if (!jsonFieldElement.isJsonPrimitive()) {			
			LOG.info(jsonFieldElement.toString());
			HDLmAssertAction(false, "JSON field element is not a JSON primitive value");
		}
		/* Get the JSON primitive value */
	  JsonPrimitive   jsonPrimitive = jsonFieldElement.getAsJsonPrimitive();
		if (!jsonPrimitive.isNumber()) {
			LOG.info(jsonFieldElement.toString());
			HDLmAssertAction(false, "JSON field element is not a JSON number value");
		}	
	  Double  jsonDouble = jsonPrimitive.getAsDouble();
	  return jsonDouble;		
	}
  /* The method below returns an integer value from a JSON element. 
	   This will only work if the JSON element is actually a
	   JSON object and if the object contains the desired key.
	   This routine will return a null value if the desired
	   key cannot be found in the JSON. This routine will fail
	   if the JSON is not a JSON object. */ 
	public static Integer getJsonInteger(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to getJsonInteger is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to getJsonInteger is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			return null;
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		JsonElement   jsonFieldElement = jsonObject.get(key);
		if (!jsonFieldElement.isJsonPrimitive()) {
			HDLmAssertAction(false, "JSON field element is not a JSON primitive value");
		}
		/* Get the JSON primitive value */
	  JsonPrimitive   jsonPrimitive = jsonFieldElement.getAsJsonPrimitive();
		if (jsonPrimitive.isBoolean()) {
			HDLmAssertAction(false, "JSON field element is a JSON boolean value");
		}	
	  Integer   jsonInteger = jsonPrimitive.getAsInt();
	  return jsonInteger;		
	}
  /* The method below returns a set of JSON keys from a JSON
     element. This will only work if the JSON element is 
     actually a JSON object. */ 
	public static Set<String> getJsonKeys(JsonElement jsonElement) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to getJsonKeys is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the JSON object from the JSON element */ 
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		Set<String>   jsonKeys = jsonObject.keySet();
		return jsonKeys;		
	}
	/* The method below returns a number value from a JSON element. 
	   This will only work if the JSON element is actually a
	   JSON object and if the object contains the desired key.
	   This routine will return a null value if the desired
	   key cannot be found in the JSON. This routine will fail
	   if the JSON is not a JSON object. */ 
	public static Number   getJsonNumber(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to getJsonNumber is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to getJsonNumber is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			return null;
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		JsonElement   jsonFieldElement = jsonObject.get(key);
		if (!jsonFieldElement.isJsonPrimitive()) {
			HDLmAssertAction(false, "JSON field element is not a JSON primitive value");
		}
		/* Get the JSON primitive value */
	  JsonPrimitive   jsonPrimitive = jsonFieldElement.getAsJsonPrimitive();
		if (!jsonPrimitive.isNumber()) {
			HDLmAssertAction(false, "JSON field element is not a JSON number value");
		}	
	  Number  jsonNumber = jsonPrimitive.getAsNumber();
	  return jsonNumber;		
	}
  /* The method below returns a JSON object value from a JSON element. 
	   This will only work if the JSON element is actually a JSON object
	   and if the object contains the desired key. This routine will
	   return a null value if the desired key cannot be found in the JSON.
	   This routine will fail if the JSON is not a JSON object. */ 
	public static JsonObject getJsonObject(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to getJsonObject is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to getJsonObject is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a JSON null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			return null;
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		JsonElement   jsonFieldElement = jsonObject.get(key);
		if (!jsonFieldElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON field element is not a JSON object value");
		}
		/* Get the JSON object value */
	  JsonObject   jsonFieldObject = jsonFieldElement.getAsJsonObject();
		if (!jsonFieldObject.isJsonObject()) {
			HDLmAssertAction(false, "JSON field element is not a JSON object value");
		}		  
	  return jsonFieldObject;		
	}
	/* The method below returns a JSON object value from any Java object. The 
	   caller passes the Java object to this routine. This routine will convert
	   the Java object to a JSON object and return the JSON object to the caller.
	   The JSON object returned will contain all the fields and values of the
	   Java object. The JSON object returned will be a deep copy of the Java
	   object. The caller can modify the JSON object without affecting the Java
	   object. The JSON object is (in most cases) a tree of JSON elements. */ 
	public static JsonObject  getJsonObjectFromPlainObject(final Object object) {
	  /* Check if the object passed by the caller is null */	 
		if (object == null) {
	    String  errorText = "Object passed to getJsonObjectFromPlainObject is null";
			throw new NullPointerException(errorText);
	  }	
		/* Get the JSON object for the Java object */  
		Gson  gson = HDLmMain.gsonMain;
		JsonObject  newJsonOject = gson.toJsonTree(object).getAsJsonObject();
		return newJsonOject;	  
	}
  /* The method below returns a string value from a JSON element. 
     This will only work if the JSON element is actually a
     JSON object and if the object contains the desired key.
     This routine will return a null value if the desired
     key cannot be found in the JSON. This routine will fail
     if the JSON is not a JSON object. */ 
	public static String   getJsonString(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to getJsonString is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to getJsonString is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			return null;
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		JsonElement   jsonFieldElement = jsonObject.get(key);
		if (!jsonFieldElement.isJsonPrimitive()) {
			HDLmAssertAction(false, "JSON field element is not a JSON primitive value");
		}
		/* Get the JSON primitive value */
	  JsonPrimitive   jsonPrimitive = jsonFieldElement.getAsJsonPrimitive();
		if (!jsonPrimitive.isString()) {
			HDLmAssertAction(false, "JSON field element is not a JSON string value");
		}	
	  String  jsonString = jsonPrimitive.getAsString();
	  return jsonString;		
	}
	/* This routine returns a JSON string from a Java object. The Java object  
	   is just a POJO (a plain old Java object). The routine will convert the
	   Java object to a JSON string and return the JSON string to the caller. */
	protected static String  getJsonString(final Object object) {
	  /* Check if the object passed by the caller is null */	 
		if (object == null) {
	    String  errorText = "Object passed to getJsonString is null";
			throw new NullPointerException(errorText);
	  }	
	  Gson    gsonInstance = HDLmMain.gsonMain; 
		String  jsonString = gsonInstance.toJson(object);
		return jsonString;
	}
  /* Get a value in JSON format. The caller provides the value as a Java
     string reference. The Java string reference may be a null value or 
     a boolean or numeric value or a regular string. This routine decides
     what format would be best for the value and returns the formatted 
     value to the caller. */ 
	protected static String getJsonValue(String inValue) {
		/* We can not actually check for a null value here because 
		   a null value is a valid input to this routine */
	  if (inValue != null &&
		    inValue == null) {
		  String  errorText = "Input value string passed to getJsonValue is null";
		  throw new NullPointerException(errorText);
		}
		String   outString;
		/* Add the value of the current key to the output JSON */
		if (inValue == null) 
			outString = "null";
	  else if (inValue.equals("true") ||
		 	       inValue.equals("false"))
		  outString = inValue;			
	  else if (StringUtils.isNumeric(inValue))
		  outString = inValue;
	  else 
			outString = "\"" + inValue + "\""; 
		return outString;  	
	}  
  /* The method below returns a JSON value from a JSON element. 
	   This will only work if the JSON element is actually a
	   JSON object and if the object contains the desired key.
	   This routine will return a null value if the desired
	   key cannot be found in the JSON. This routine will fail
	   if the JSON is not a JSON object. */ 
	public static JsonElement getJsonValue(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to getJsonValue is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to getJsonValue is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (!jsonKeys.contains(key)) {
			return null;
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		JsonElement   jsonFieldElement = jsonObject.get(key);
	  return jsonFieldElement;		
	}
	/* The method below converts a JSON element to a string and returns
	   the string to the caller. Note that the JSON element may actually
	   be a JSON object. The JSON element may actually be a hierarchy 
	   of JSON elements of various types. */ 
	protected static String  getStringJson(JsonElement jsonElement) {
    /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
		  String  errorText = "JSON element passed to getStringJson is null";
			throw new NullPointerException(errorText);
		}
		Gson  gson = HDLmMain.gsonMain;
		String  jsonStr = gson.toJson(jsonElement);
		return jsonStr;
	}	 
	/* The method below converts a JSON element to a string and returns
	   the string to the caller. Note that the JSON element may actually
	   be a JSON object. The JSON element may actually be a hierarchy 
	   of JSON elements of various types. */ 
	protected static String  getStringJsonNulls(JsonElement jsonElement) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
		  String  errorText = "JSON element passed to getStringJson is null";
			throw new NullPointerException(errorText);
		}
		/* Obtain a Gson instance with serialize nulls set */
		Gson  gson = HDLmMain.gsonSerializeNullsMain;
		/* Get some JSON from a JSON element */
		String  jsonStr = gson.toJson(jsonElement);
		return jsonStr;
	}	 
  /* The method below checks if a name (key) exists in a JSON element. 
	   This will only work if the JSON element is actually a JSON object.
	   This routine will return true if the name (key) exists in the JSON
	   element (really a JSON object). This routine will return false if 
	   the name (key) does not exist in the JSON element (really a JSON 
	   object). */
	public static boolean hasJsonKey(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to hasJsonKey is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to hasJsonKey is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(key)) {
			return true;
		}
		return false;
	}
	/* This method determines if a string is a valid JSON number.
	   JSON numbers are subject to a long list of rules. This
	   routine returns a boolean showing if the string is a 
	   valid JSON number or not. Note that a null string can
	   be passed to this routine. Null strings are assumed not
	   to be valid JSON numbers. */ 
	public static boolean  isJsonNumber(final String inStr) {
		/* Declare and define a few values */
		boolean   exponentDigitsFound = false;
		boolean   exponentFound = false;
		boolean   fractionDigitsFound = false;
		boolean   fractionFound = false;
		boolean   leadingInteger = false;
		char      curChar;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check if the input string is null or not */
			if (inStr == null)
				return false;
			/* Set a few values for use below */
			int   inLength = inStr.length();
			int   inOffset = 0;
			/* Check for a leading minus sign which is allowed */
			curChar = inStr.charAt(inOffset);
			if (curChar == '-') 
				inOffset++;
			/* Check if we have used all of the input characters
			   at this point */
			if (inOffset >= inLength) 
				return false;
			/* At this point we can either have a single leading 
			   zero or an integer that must not begin with a zero. */
			curChar = inStr.charAt(inOffset);
			if (curChar == '0') {
				leadingInteger = true;
				inOffset++;
			}
			/* We did not find a leading zero. This should mean that
			   we possibly have a leading integer. Some more checking 
			   is needed. */
			else {
				/* Check for a valid leading digit. A valid leading digit must
				   not be zero, but must be some other digit value. */
				if (curChar >= '1' && curChar <= '9') {
					leadingInteger = true;
					inOffset++;
				}
			  else
			  	return false;
			  /* At this point we can skip any number of numeric digits.
			     These digits can be zero or non-zero. */
			  while (inOffset < inLength) {
			  	curChar = inStr.charAt(inOffset);
					if (curChar < '0' || curChar > '9') 
						break;		
					inOffset++;
			  }
			}
			/* Check if we found a valid leading digit. This will 
			   not always be true. */
			if (leadingInteger == false)
				return false;
			/* At this point we may have a fraction. Of course, the
			   fraction is optional. */
			if (inOffset >= inLength) 
				return true;
			curChar = inStr.charAt(inOffset);
			if (curChar == '.') {
				inOffset++;
				fractionFound = true;
			  /* At this point we can skip any number of numeric digits.
		       These digits can be zero or non-zero. Note that at least
		       one digit is required. */
			  while (inOffset < inLength) {
			  	curChar = inStr.charAt(inOffset);
					if (curChar < '0' || curChar > '9') 
						break;		
					fractionDigitsFound = true;
					inOffset++;
			  }
			  /* Make sure we found at least one fraction digit */
			  if (fractionDigitsFound == false)
			  	return false;
			}
			/* At this point we may have a exponent. Of course, the
		     exponent is optional. */
		  if (inOffset >= inLength) 
			  return true;
			curChar = inStr.charAt(inOffset);
			if (curChar == 'E' || curChar == 'e') {
				exponentFound = true;
				inOffset++;
				/* The exponent indicator, may be optionally followed
				   by a plus or minus sign. We need to check for and
				   skip the optional plus or minus sign. */
			  if (inOffset >= inLength) 
				  return false;
				curChar = inStr.charAt(inOffset);
				if (curChar == '+' || curChar == '-')
					inOffset++;
			  /* At this point we can skip any number of numeric digits.
	         These digits can be zero or non-zero. Note that at least
	         one digit is required. */
			  while (inOffset < inLength) {
			  	curChar = inStr.charAt(inOffset);
					if (curChar < '0' || curChar > '9') 
						break;		
					exponentDigitsFound = true;
					inOffset++;
			  }
			  /* Make sure we found at least one exponent digit */
			  if (exponentDigitsFound == false)
			  	return false;
			}
			/* If we have used all of the input string at this point,
			   then we are done and we have a valid JSON number */
		  if (inOffset >= inLength) 
			  return true;
		  break;
		}
		return false;
	}
	/* This routine will remove (actually set to stars) a string
		 in some JSON. This type of processing is needed so that 
		 we don't log passwords. If the prefix string is not found
		 in the input string, the entire input string is returned
		 as the output string. Note that the prefix string must 
		 end with a double quote. It is highly recommended that
		 the prefix string start and end with a double quote 
		 character. */ 
	static protected String  maskStringInJson(final String inStr, final String prefixStr) {
	  /* Check if the input string passed by the caller is null */	 
		if (inStr == null) {
	    String  errorText = "Input string passed to maskStringInJson is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the prefix string passed by the caller is null */	 
		if (prefixStr == null) {
	    String  errorText = "Prefix string passed to maskStringInJson is null";
			throw new NullPointerException(errorText);
	  }
		/* Look for the prefix string in the input string. If we 
		   don't find it, just return the entire input string as
		   the output string.  */
		int   strIndx = inStr.indexOf(prefixStr);
		if (strIndx < 0) {
			return inStr;
		}
		/* Build the output string */
		StringBuilder   outStrBuilder = new StringBuilder();
		outStrBuilder.append(inStr.substring(0, strIndx));
		outStrBuilder.append(prefixStr);
		/* Declare and define a few values */
		int   inStrLen = inStr.length();
		int   prefixStrLen = prefixStr.length();
		/* Add a few characters after the prefix string */
		int   endIndx = inStr.indexOf('"', strIndx+prefixStrLen);
		outStrBuilder.append(inStr.substring(strIndx+prefixStrLen, endIndx+1));	
		/* Look for another double quote */
		int   doubleQuotePosition = inStr.indexOf('"', endIndx+1);
		int   passwordLength = doubleQuotePosition - endIndx - 1;
		/* Add the password to the output string as stars */
		outStrBuilder.append("*".repeat(passwordLength));
		/* Add the rest of the input string */
		outStrBuilder.append(inStr.substring(doubleQuotePosition, inStrLen));		 
		return outStrBuilder.toString();
	}
	/* The method below removes a JSON key from a JSON element.
		 This will only work if the JSON element is actually a
	   JSON object. This routine will fail if the JSON is not
	   a JSON object. Note that this routine will return a 
	   boolean value showing if the key was actually removed.
	   This routine can be passed a key that is not in the 
	   JSON. This is not an error condition, but will cause
	   a false value to be returned. */ 
	public static boolean removeJsonKey(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to removeJsonKey is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to removeJsonKey is null";
			throw new NullPointerException(errorText);
	  }	
		/* Declare and define a few values */
		boolean   rv = false;
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Get the JSON object from the JSON element  */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(key)) {
			jsonObject.remove(key);
			rv = true;
		} 
		return rv;
	}	
	/* The method below replaces a JSON value in a JSON element. 
     This will only work if the JSON element is actually a
     JSON object. This routine will fail if the JSON is not
     a JSON object. The caller provides the old key and the
     new key values. The JSON value is not changed. However,
     the key associated with the JSON value is changed. */ 
	public static void replaceJsonValue(JsonElement jsonElement,  
			                                String oldKey, 
			                                String newKey) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to replaceJsonValue is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the old key value passed by the caller is null */	 
		if (oldKey == null) {
	    String  errorText = "Old key value passed to replaceJsonValue is null";
			throw new NullPointerException(errorText);
	  }	
    /* Check if the new key value passed by the caller is null */	 
	  if (newKey == null) {
		  String  errorText = "New key value passed to replaceJsonValue is null";
		  throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null in replaceJsonValue");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value in replaceJsonValue");
		}
		/* Get the set of JSON keys in the JSON element */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Make sure that the old key actually exists */
		if (!jsonKeys.contains(oldKey)) {
    	String   errorFormat = "Old key (%s) not found in JSON object in replaceJsonValue";
			String   errorText = String.format(errorFormat, oldKey);
			HDLmAssertAction(false, errorText);		
		}
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		/* Get the JSON value from the JSON object */
	  JsonElement   value = jsonObject.get(oldKey);
		jsonObject.remove(oldKey);
		/* Use the new key to store the JSON value */
		jsonObject.add(newKey, value);
	}	
	/* The method below sets a boolean value in a JSON element. 
	   This will only work if the JSON element is actually a
	   JSON object. This routine will fail if the JSON is not
	   a JSON object. */ 
	public static void setJsonBoolean(JsonElement jsonElement, String key, Boolean value) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to setJsonBoolean is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to setJsonBoolean is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the replacement boolean value passed by the caller is null */	 
		if (value == null) {
	    String  errorText = "Replacement boolean value passed to setJsonBoolean is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(key)) {
			jsonObject.remove(key);
		}
		jsonObject.addProperty(key, value);
	}
	/* The method below sets an integer value in a JSON element. 
     This will only work if the JSON element is actually a
     JSON object. This routine will fail if the JSON is not
     a JSON object. */ 
	public static void setJsonInteger(JsonElement jsonElement, String key, Integer value) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to setJsonInteger is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to setJsonInteger is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the replacement integer value passed by the caller is null */	 
		if (value == null) {
	    String  errorText = "Replacement integer value passed to setJsonInteger is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(key)) {
			jsonObject.remove(key);
		}
		jsonObject.addProperty(key, value);
	}
	/* The method below sets a null value in a JSON element. 
  	 This will only work if the JSON element is actually a
	   JSON object. This routine will fail if the JSON is not
	   a JSON object. */ 
	public static void setJsonNull(JsonElement jsonElement, String key) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to setJsonNull is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to setJsonNull is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Get the JSON object from the JSON element  */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(key)) {
			jsonObject.remove(key);
		}
		String  value = null;
		jsonObject.addProperty(key, value);
	}
	/* The method below sets a string value in a JSON element. 
		 This will only work if the JSON element is actually a
		 JSON object. This routine will fail if the JSON is not
		 a JSON object. */ 
	public static void setJsonString(JsonElement jsonElement, String key, String value) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to setJsonString is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to setJsonString is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the replacement string value passed by the caller is null */	 
		if (value == null) {
	    String  errorText = "Replacement string value passed to setJsonString is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(key)) {
			jsonObject.remove(key);
		}
		jsonObject.addProperty(key, value);
	}
	/* The method below sets a JSON value in a JSON element. 
	   This will only work if the JSON element is actually a
	   JSON object. This routine will fail if the JSON is not
	   a JSON object. */ 
	public static void setJsonValue(JsonElement jsonElement, String key, JsonElement value) {
	  /* Check if the JSON element passed by the caller is null */	 
		if (jsonElement == null) {
	    String  errorText = "JSON element passed to setJsonValue is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the key value passed by the caller is null */	 
		if (key == null) {
	    String  errorText = "Key value passed to setJsonValue is null";
			throw new NullPointerException(errorText);
	  }	
	  /* Check if the replacement JSON element value passed by the caller is null */	 
		if (value == null) {
	    String  errorText = "Replacement JSON element value passed to setJsonValue is null";
			throw new NullPointerException(errorText);
	  }	
		/* Check if the JSON element is a null value */
		if (jsonElement.isJsonNull()) {
			HDLmAssertAction(false, "JSON element is a JSON null");
		}
		/* Check if the JSON element is not a JSON object value */
		if (!jsonElement.isJsonObject()) {
			HDLmAssertAction(false, "JSON element is not a JSON object value");
		}
		/* Get the set of JSON keys in the JSON object */
		Set<String>   jsonKeys = getJsonKeys(jsonElement);
		/* Get the JSON object from the JSON element */
		JsonObject    jsonObject = jsonElement.getAsJsonObject();
		/* Check if we have the field in the JSON */
		if (jsonKeys.contains(key)) {
			jsonObject.remove(key);
		}
		jsonObject.add(key, value);
	}
}
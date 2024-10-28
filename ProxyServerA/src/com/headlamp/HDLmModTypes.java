package com.headlamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
/**
 * HDLmModTypes short summary.
 *
 * HDLmModTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of modifications supported by
   this code. Note that the first type is NONE which means that 
   the modification type has not been specified. */  
public enum HDLmModTypes {
  NONE,
  @SerializedName("attribute")
  ATTRIBUTE,
  @SerializedName("changeattrs")
  CHANGEATTRS,
  @SerializedName("changenodes")
  CHANGENODES,
  @SerializedName("extract")
  EXTRACT,
  @SerializedName("fontcolor")
  FONTCOLOR,
  @SerializedName("fontfamily")
  FONTFAMILY,
  @SerializedName("fontkerning")
  FONTKERNING,
  @SerializedName("fontsize")
  FONTSIZE,
  @SerializedName("fontstyle")
  FONTSTYLE,
  @SerializedName("fontweight")
  FONTWEIGHT,
  @SerializedName("height")
  HEIGHT,
  @SerializedName("image")
  IMAGE,
  @SerializedName("modify")
  MODIFY,
  @SerializedName("notify")
  NOTIFY,
  @SerializedName("order")
  ORDER,
  @SerializedName("remove")
  REMOVE,
  @SerializedName("replace")
  REPLACE,
  @SerializedName("script")
  SCRIPT,
  @SerializedName("style")
  STYLE,
  @SerializedName("text")
  TEXT,
  @SerializedName("textchecked")
  TEXTCHECKED,
  @SerializedName("title")
  TITLE,
  @SerializedName("visit")
  VISIT,  
  @SerializedName("width")
  WIDTH,   
  /* The entries below are not valid rule types. However, some instances 
     of HDLmMod (actually the classes that extend HDLmMod) are not used 
     for rules. These types are for the other uses of HDLmMod extensions.
     The entries below were actually taken from the tree types. */ 
  @SerializedName("company")
  COMPANY,   
  @SerializedName("data")
  DATA,
  @SerializedName("division")
  DIVISION,
  @SerializedName("lists")
  LISTS,
  @SerializedName("reports")
  REPORTS,  
  @SerializedName("rules")
  RULES,
  @SerializedName("site")
  SITE;
	private static final ArrayList<String>  typeValues = new ArrayList<String>(
		List.of("NONE", "ATTRIBUTE", "CHANGEATTRS",
				    "CHANGENODES", "EXTRACT", 
				    "FONTCOLOR", "FONTFAMILY", "FONTKERNING",
				    "FONTSIZE", "FONTSTYLE", "FONTWEIGHT", 
				    "HEIGHT", "IMAGE", "MODIFY",
				    "NOTIFY", "ORDER",
				    "REMOVE", "REPLACE",
				    "SCRIPT",
				    "STYLE", "TEXT", 
				    "TEXTCHECKED", "TITLE",
				    "VISIT", "WIDTH",
				    /* The entries below are not valid rule types. However, some instances 
				       of HDLmMod (actually the classes that extend HDLmMod) are not used 
				       for rules. These types are for the other uses of HDLmMod extensions.
				       The entries below were actually taken from the tree types. */ 
				    "COMPANY", "DATA",
				    "LISTS", "REPORTS",
				    "RULES"));	 
  /* We provide a non-standard routine for converting strings to 
     enum values. This routine converts the input string value
     to uppercase and checks the value first. The string passed
     by the caller must not be null.*/
  protected static HDLmModTypes valueOfString(String newType) {
		if (newType == null) {
		  String  errorText = "String passed to modification type conversion is null";
		  throw new NullPointerException(errorText);
		}
  	newType = newType.toUpperCase();
  	if (typeValues.contains(newType)) 
  		return HDLmModTypes.valueOf(newType);
  	return HDLmModTypes.NONE;
  }
}
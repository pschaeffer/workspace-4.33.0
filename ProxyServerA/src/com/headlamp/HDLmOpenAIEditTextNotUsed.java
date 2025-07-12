package com.headlamp;
import java.util.ArrayList;
/**
 * HDLmOpenAIEditText short summary.
 *
 * HDLmOpenAIEditText description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */ 
public class HDLmOpenAIEditTextNotUsed {
  /* This class can be instantiated using the
     constructor below */
	protected HDLmOpenAIEditTextNotUsed(String modelValue, String contextValue, 
			                                String goalValue, Float temperatureValue, 
			                                Integer nValue) {
		/* Check if the model value passed by the caller is null */
		if (modelValue == null) {
			String  errorText = "Model value string passed to HDLmOpenAIEditText is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the context value passed by the caller is null */
		if (contextValue == null) {
			String  errorText = "Context value string passed to HDLmOpenAIEditText is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the goal value passed by the caller is null */
		if (goalValue == null) {
			String  errorText = "Goal value string passed to HDLmOpenAIEditText is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the temperature value passed by the caller is null */
		if (temperatureValue == null) {
			String  errorText = "Temperature value string passed to HDLmOpenAIEditText is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the n value (number of generated text values) passed 
		   by the caller is null */
		if (nValue == null) {
			String  errorText = "N value integer passed to HDLmOpenAIEditText is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few fields in the new instance */
		model = modelValue;		
		messages = new ArrayList<String>();
		context = contextValue;
		goal = goalValue;
		temperature = temperatureValue;
		n = nValue;
	}	
	/* The model is specified using the field below */ 
	private String    model = null;
	/*  */
	private ArrayList<String>   messages = null;
	/* The context is specified using the field below */ 
	private String    context = null;
	/* The goal string is specified using the field below */ 
	private String    goal = null;
	/* The temperature is specified using the field below */
	private Float     temperature = null; 
	/* The number of text variants is specified using the field below */ 
	private Integer   n = null;
}
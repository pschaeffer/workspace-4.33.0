package com.headlamp;
/**
 * HDLmOpenAIEditImage short summary.
 *
 * HDLmOpenAIEditImage description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */ 
public class HDLmOpenAIEditImageNotUsed {
  /* This class can be instantiated using the
     constructor below */
	protected HDLmOpenAIEditImageNotUsed(String modelValue, String instructionValue, 
			                                 String inputValue, Float temperatureValue, 
			                                 Integer nValue) {
		/* Check if the model value passed by the caller is null */
		if (modelValue == null) {
			String  errorText = "Model value string passed to HDLmOpenAIEditImage is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the instruction value passed by the caller is null */
		if (instructionValue == null) {
			String  errorText = "Instruction value string passed to HDLmOpenAIEditImage is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the input value passed by the caller is null */
		if (inputValue == null) {
			String  errorText = "Input value string passed to HDLmOpenAIEditImage is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the temperature value passed by the caller is null */
		if (temperatureValue == null) {
			String  errorText = "Temperature value string passed to HDLmOpenAIEditImage is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the n value (number of generated image values) passed 
		   by the caller is null */
		if (nValue == null) {
			String  errorText = "N value integer passed to HDLmOpenAIEditImage is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few fields in the new instance */
		model = modelValue;
		instruction = instructionValue;
		input = inputValue;
		temperature = temperatureValue;
		n = nValue;
	}	
	/* The model is specified using the field below */ 
	private String    model = null;
	/* The instruction is specified using the field below */ 
	private String    instruction = null;
	/* The input string is specified using the field below */ 
	private String    input = null;
	/* The temperature is specified using the field below */
	private Float     temperature = null; 
	/* The number of image variants is specified using the field below */ 
	private Integer   n = null;
}
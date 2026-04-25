package com.headlamp;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmAi short summary.
 *
 * HDLmAi description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmAi {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmAi.class);
	/* The following string constants are used to build the chat template JSON object.
	   These strings we taken from the HDLmAi.js file coded in JavaScript. */
  private static final String chatTemplateContext = 
    "You are an expert at improving web pages, " + 
    "especially their sales and user engagement.\n" +
    "\n" +
    "You will be asked to:\n" +
    "- Analyze a web page.\n" +
    "- Create JSON output.\n" +
    "- Suggest ways to improve it.\n" +
    "- Provide HTML snippets (to be placed in the `<head>` and `<body>` sections) that implement an improvement.\n";   
  private static final String chatTemplateImprovements = 
		"Suggest {quantity} ways to improve this page. For each suggestion, provide:\n" +
		"- What specific change should be made.\n" + "- Why it will improve sales or engagement.\n" + "\n" +
		"Prioritize improvements that will have the most impact on user conversion and interaction.\n" + "\n" +
		"Look for ways to:\n" +
		"- Adjust color schemes, typography, and whitespace for better aesthetics and usability.\n" +
		"- Reword the text to better convey the site's value through storytelling.\n" +
		"- Reorder the content to guide user's attention.\n" +
		"- Add or remove content to improve clarity and reduce clutter.\n" + "\n" + "Each improvement suggestion:\n" +
		"- Should start with the word 'Improve'.\n";  
	private static final String chatTemplateMarkup =
	  "Change the markup on this page to {improvement}.\n" + "\n" + 
	  "The markup must:\n" +
		"- Never modify any existing markup, instead use new insertions to modify the page.\n" +
		"- Include insertions (if needed) to be placed in `<head>`section.\n" +
		"- Include insertions (if needed) to be placed in the `<body>` section.\n" +
		"- Include everything needed, including opening and closing tags for `<style>` and `<script>`.\n" +
		"- Be valid HTML, CSS, or JavaScript.\n" + "- Never include any comments.\n" +
		"- Integrate well with the page's current design and functionality.\n";	
	private static final String chatTemplateWebpageClient = 
		"Focus on the page at {url} and just respond 'OK' after examining it:\n" + 
		"```HTML\n" + 
		"{webpageClient}\n" + 
		"```\n";	
	private static final String chatTemplateWebpageServer = 
		"Focus on the page at {url} and just respond 'OK' after examining it.\n";
  /* Build a JSON object */
  private static JsonObject   chatTemplateJson = HDLmJson.createJsonObject();
  /* This static block is used to initialize the chat template JSON object.
     This block is executed when the class is loaded and it sets the various
     fields of the chat template JSON object. */
  static {
    HDLmJson.setJsonString(chatTemplateJson, "context", chatTemplateContext); 
    HDLmJson.setJsonString(chatTemplateJson, "improvments", chatTemplateImprovements); 
    HDLmJson.setJsonString(chatTemplateJson, "markup", chatTemplateMarkup); 
    HDLmJson.setJsonString(chatTemplateJson, "webpageClient", chatTemplateWebpageClient); 
    HDLmJson.setJsonString(chatTemplateJson, "webpageServer", chatTemplateWebpageServer);     
  }
  /* This class can never be instantiated */
	private HDLmAi() {}	
	/* This method is used to make changes to the input script 
	   passed by the caller. The key idea is that this routine
	   allows strings to contains backslashes followed by 'n'
	   character. 
	   
	   This code does not really work. It turns out that 
	   apostrophe (single quote) is used for all sorts of
	   things in American English and JavaScript. Of course, 
	   single quote is used to start and end strings in Java-
	   Script. However, single quote is also used (several 
	   ways) for the possessive form of nouns. Single quote
	   is also used for contractions. For example, should'nt
	   is a contraction of should not. */ 
	protected static String  changeJavaScriptNotUsed(final String inScript) {
		/* Check if the input script passed by the caller is null */
		if (inScript == null) {
			String errorText = "Input script passed to changeJavaScript is null";
			throw new NullPointerException(errorText);
		}
		/* Make a few changes to the input script */
		/*
		inScript = inScript.replace("\\u0027", "'");
		inScript = inScript.replace("\\x27", "'");		
		*/
		/* Create the quote type stack */
		Stack<HDLmQuoteTypes>   quoteStack = new Stack<HDLmQuoteTypes>();
		/* Set a few variables for changing the input script */
		int     lenInScript = inScript.length(); 
		/* Check if the first and last characters are double quotes.
		   If they are, remove them. */
		/*
		if (lenInScript >= 2          && 
				inScript.charAt(0) == '"' && 
				inScript.charAt(lenInScript - 1) == '"') {
			inScript = inScript.substring(1, lenInScript - 1);
			lenInScript -= 2;
		}
		*/
		String  outScript = "";
		String  outSequence; 
		/* Process each character in the input script */
		for (int i = 0; i < lenInScript; i++) {
			char ch = inScript.charAt(i);
			/*
			LOG.info(((Integer) i).toString() + " " + Character.toString(ch) + " " + ((Integer) quoteStack.size()).toString());
			*/
			/* Different code is needed for different characters */
			switch (ch) {
			  /* Handle a single quote. The single quote
			     might start or end a quoted string. */
			  case '\'': {
			  	/* Build up the output script */
			  	outScript += "\\x27";
					/* Check for a very special case where a single quote is followed
					   by a lowercase 's'. This case is not a string in single quotes. */
					if ((i + 1) < lenInScript) {
						/* A single quote followed by an 's' (without the quotes) is not
						   the start or end of a string in single quotes. It is a possessive
						   form of a noun. For example, the single quote in George's boots 
						   is a reference to something that George's boots have. */						  
						char  nextChar = inScript.charAt(i + 1);
            if (nextChar == 's') {
							/* We really only have the possessive form of a noun if the 's' is followed by a
							   space or a period */
					    if ((i + 2) < lenInScript) {
					    	char  nextNextChar = inScript.charAt(i + 2);
					    }
              break;
            }                      
					}
			  	/* Check if we are starting a string quoted with single quotes */
					if (quoteStack.isEmpty() || 
							quoteStack.get(quoteStack.size() - 1) != HDLmQuoteTypes.SINGLE) {
						quoteStack.push(HDLmQuoteTypes.SINGLE);
						break;
					} 
					/* Check if we are ending a string quoted with single quotes */
					if (!quoteStack.isEmpty() && 
							quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.SINGLE) {
						quoteStack.pop();   
						break;
          }
					break;
        }
			  /* Handle a double quote. The double quote
		       might start or end a quoted string. */
			  case '"': {
			  	/* Build up the output script */
			  	outScript += "\\x22";
			  	/* Check for a very special case where a double quote 
			  	   is preceded by a number. This case is not a string
			  	   in double quote. */
			  	if (i > 0 &&
							Character.isDigit(inScript.charAt(i - 1))) {
			  		outScript += ch;
						break;
					}
			  	/* Check if we are starting a string quoted with double quotes */
					if (quoteStack.isEmpty() || 
							quoteStack.get(quoteStack.size() - 1) != HDLmQuoteTypes.DOUBLE) {
						quoteStack.push(HDLmQuoteTypes.DOUBLE);
						break;
					} 
					/* Check if we are ending a string quoted with double quotes */
					if (!quoteStack.isEmpty() && 
							 quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.DOUBLE) {
						quoteStack.pop(); 
						break;
	        }
					break;
	      } 		
				/* Handle an accent character. The accent character 
				   might start or end a quoted string. */
			  case '`': {
			  	/* Build up the output script */
			  	outScript += "\\x60"; 
			  	/* outScript += ch; */
			  	/* Check if we are starting a string quoted with accent quotes */
					if (quoteStack.isEmpty() || 
							quoteStack.get(quoteStack.size() - 1) != HDLmQuoteTypes.ACCENT) {
						quoteStack.push(HDLmQuoteTypes.ACCENT);
						break;
					} 
					/* Check if we are ending a string quoted with accent quotes */
					if (!quoteStack.isEmpty() && 
							quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.ACCENT) {
						quoteStack.pop();   
						break;
          }
					break;			  	
			  }	
			  /* Check for a line feed character. We really only care about a 
		       line feed character if we are in a string. */ 
			  case '\n': {
			  	/* Handle a line feed character by adding a trailing quote
			  	   followed by a plus sign and a line feed character followed 
			  	   by a leading quote. */
		  		if (!quoteStack.isEmpty()) {
					  /* Check if the last quote is a single quote or a 
					     double quote or an accent*/
					  if (quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.SINGLE) 
						  outSequence = " \\x27 + \n\\x27";
					  else if (quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.DOUBLE) 
						  outSequence = " \\x22 + \n\\x22";
					  else if (quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.ACCENT) 
					  	outSequence = " \\x60 + \n\\x60";
					  else
						  outSequence = "";
			  		outScript += outSequence;
			  		break;
		  		}
				  outScript += ch;
				  break;
			  }     	
			  /* Check for a backslash character. We really only care about a 
			     backslash character if it is followed by the letter 'n' and 
			     we are in a string. */ 
			  case '\\': {
			  	/* Handle a backslash character followed by the letter 'n'
			  	   by adding a trailing quote followed by a plus sign and
			  	   a backslash character followed by the letter 'n' followed
			  	   by a leading quote. */
					if ((i + 1) < lenInScript         && 
							inScript.charAt(i + 1) == 'n' &&
							!quoteStack.isEmpty()) {
						/* Check if the last quote is a single quote or a 
						   double quote or an accent*/
						if (quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.SINGLE) 
							outSequence = " \\x27 + \\n\\x27";
						else if (quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.DOUBLE) 
							outSequence = " \\x22 + \\n\\x22";
						else if (quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.ACCENT) 
							outSequence = " \\x60 + \\n\\x60";
						else
							outSequence = "";
						outScript += outSequence;
						/* Skip past the 'n' character */
						i++;
						break;
					}
				  outScript += ch;
				  break;
			  }          
			  default: {
				  outScript += ch;
				  break;
			  }
			}
		}	
		/* Return the final output script to the caller */
		return outScript;
	}	
	/* Fix the input JavaScript string passed by the caller */ 
	protected static String  fixWebImproverScript(final String inputScript) {
		/* Check if the input script passed by the caller is null */
		if (inputScript == null) {
			String  errorText = "Input script passed to fixWebImproverScript is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few variables for changing the input script */
		int  newLineCounter = 0;
		int  newLineIndex = -1;
		String  outputScript = ""; 	
		/* Modify the output script as need be */
		outputScript = inputScript;		 
		return outputScript;		
	}
}
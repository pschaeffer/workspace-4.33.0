package com.headlamp;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmAi short summary.
 *
 * HDLmApi description.
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
	/* This class can never be instantiated */
	private HDLmAi() {}	
	/* This method is used to make changes to the input script 
	   passed by the caller. The key idea is that this routine
	   allows strings to contains backslashes followed by 'n'
	   character. */
	protected static String  changeJavaScript(final String inScript) {
		/* Check if the input script passed by the caller is null */
		if (inScript == null) {
			String errorText = "Input script passed to changeJavaScript is null";
			throw new NullPointerException(errorText);
		}
		/* Create the quote type stack */
		Stack<HDLmQuoteTypes>   quoteStack = new Stack<HDLmQuoteTypes>();
		/* Set a few variables for changing the input script */
		int     lenInScript = inScript.length(); 
		String  outScript = "";
		String  outSequence; 
		/* Process each character in the input script */
		for (int i = 0; i < lenInScript; i++) {
			char ch = inScript.charAt(i);
			/* Different code is needed for different characters */
			switch (ch) {
			  /* Handle a single quote. The single quote
			     might start or end a quoted string. */
			  case '\'': {
			  	/* Build up the output script */
			  	outScript += "\\x27";
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
						   double quote */
						if (quoteStack.get(quoteStack.size() - 1) == HDLmQuoteTypes.DOUBLE) 
							outSequence = "\\x22 + \\n\\x22";
						else
							outSequence = "\\x27 + \\n\\x27";
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
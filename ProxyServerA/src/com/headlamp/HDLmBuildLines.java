package com.headlamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class for building complex sets of strings (such as a program)
 *
 * Each instance of this class is used to build a set of complex strings.
 * For example, one instance of this class might be used to build a full
 * JavaScript program. Each instance has the accumulated text (so far)
 * and the current indentation level. The class instance methods keep
 * track of the current indentation level.
 *
 * Note that the indentation code is no longer really in use. The caller
 * must provide properly indented code.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmBuildLines {
	/* The next statement initializes logging to some degree. Note that having the
     slf4j jars and the log4j jars in the classpath also plays some role in
     logging initialization.	 */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmJetty.class);
	/* Create an array of string for holding text. Initially this
	   array is empty. Each line is added to this array as need be. */	 
  private  ArrayList<String> lines = new ArrayList<String>();
  /* We have a field for keeping track of the type of program
     we are building. This field is not used for now. */
  private  String type = "";
  /* This constructor takes just one operand which is type
     of lines that are being built */ 
  protected HDLmBuildLines(String buildType) {
		if (buildType == null) {
			String   errorText = "Build type reference passed to build lines constructor is null";
			throw new NullPointerException(errorText);		
		}
  	this.type = buildType;  	
  }
  /* Add a new line to the set of lines */
	protected void addLine(String newLine) {		
		if (newLine == null) {
			String   errorText = "New line reference passed to addLine is null";
			throw new NullPointerException(errorText);			
		}
		this.lines.add(newLine); 
	}
  /* Return the complete set of accumulated lines as one string
     with a suffix added to each line. The suffix is typically
     something CR or LF (or both). */
	protected String getLinesWithSuffix(String suffix) {
		if (suffix == null) {
			String   errorText = "Suffix reference passed to getLinesWithSuffix is null";
			throw new NullPointerException(errorText);		
		}
	  StringBuilder  outStrBuilder = new StringBuilder();
	  /* The original line (with no indentation adjustments) is added to the lines array */
	  for (String line : this.lines) {
	    outStrBuilder.append(line);
	    outStrBuilder.append(suffix);
	  }
	  return outStrBuilder.toString();
	}
}
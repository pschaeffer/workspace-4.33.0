package com.headlamp;
import java.util.ArrayList;
/**
 * HDLmNameMatch short summary.
 *
 * This class was created to return information from certain types of 
 * calls. In some cases, we need to return several data values. This
 * class (really instances) of this class allow for returning several
 * data values at one.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmNameMatch {
	/* An instance of this class is created to return name matching.
	   information to the caller. The name matching information is
	   stored in the fields below. The name matching information can
	   be obtained via several class functions. */
	private int                 count = 0;
	private ArrayList<String>   array = new ArrayList<String>(); 
	/* Add a name value string to the name value array and return the 
     updated value to the caller */
  protected ArrayList<String> addName(String name) {
  	/* Check if the name value passed by the caller is null */
		if (name == null) {
		  String  errorText = "Name value passed to addName is null";
		  throw new NullPointerException(errorText);
		}
	  this.array.add(name);
	  return this.array;
  }
	/* Get the name match array from a name match object and return it to the caller */
	protected ArrayList<String> getArray() {
		return this.array;
	}
	/* Get the name match count from a name match object and return it to the caller */
	protected int getCount() {
		return this.count;
	}
	/* Increment the name match count value for a name match object and return 
	   the updated value to the caller */
	protected int incrementCount() {
		this.count += 1;
		return this.count;
	}
}
package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;
/**
 * HDLmToken short summary.
 *
 * HDLmToken description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmToken {
	/* Every token has the following fields. They are all set
	   by the tokenizer routine. The type is an enum value.
	   The position is actually an index or offset (starts
	   with zero). The string is the value of the token. Note
	   that this will be true even for integer tokens. */
	private HDLmTokenTypes   type;
	private int              index;
	private String           value;
	/* Add a single character to the value string */
	protected void addCharacter(char newCh) {
		value += newCh;
	}
	/* Get a few values */
	protected int getLength() {
		return value.length();
  }
	protected int getIndex() {
		return index;
	}
	protected HDLmTokenTypes getType() {
		return type;
	}
	protected String getValue() {
		return value;
	}
	/* Set a few values */
	protected void setIndex(int newIndex) {
		if (newIndex < 0) {
		  HDLmAssertAction(false, "New token index value is less than zero");
		}
	  index = newIndex;
	}
	protected void setType(HDLmTokenTypes newType) {
		type = newType;
	}
	protected void setValue(String newValue) {
		if (newValue == null) {
			String  errorText = "New token value string is null";
			throw new NullPointerException(errorText);
		}
		value = newValue;
	}
}
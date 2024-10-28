package com.headlamp;
/**
 * HDLmTokenTypes short summary.
 *
 * HDLmTokenTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of tokens supported by the
   tokenizer. An integer is a sequence of numeric digits. A
   number may or may not have a decimal point. Note that the
   end type is used as a sentinel to mark the end of the token
   array. */
public enum HDLmTokenTypes {
	NONE,
	IDENTIFIER,
	OPERATOR,
	QUOTED,
	INTEGER,
	NUMBER,
	SPACE,
	UNKNOWN,
	END
}
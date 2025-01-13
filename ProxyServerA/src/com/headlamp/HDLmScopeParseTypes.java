package com.headlamp;
/**
 * HDLmScopeParseTypes short summary.
 *
 * HDLmScopeParseTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the possible states for scope 
   parsing. New states can be added as need be. The scope
   parsing state determines what sort of tokens are allowed
   next. */   
public enum HDLmScopeParseTypes {
	NONE,
	/* This is the initial state and the beginning of parsing */
	INITIAL,
	/* This state is just before any entry. The entry may or may
	   not be in parenthesis. */ 
	BEFOREENTRY,
	/* This state is after an entry found outside of parenthesis */
	AFTERENTRY,
	/* This state is after a left parenthesis is found in the set 
	   of tokens */ 
	AFTERLPAREN,
	/* This state is after one or more spaces after a left parenthesis */
	AFTERFIRSTSPACEINPARENS,
	/* This state is after one or more spaces (typically after an 
	   identifier in parenthesis) */ 
	AFTERLATERSPACEINPARENS,
	/* This state is after one or more spaces (typically after a 
	   comma inside parenthesis) */
	AFTERCOMMASPACEINPARENS,
	/* This state is after an identifier found inside parenthesis */
	AFTERIDENINPARENS,
	/* This state is after a comma found inside parenthesis */
	AFTERCOMMAINPARENS
}
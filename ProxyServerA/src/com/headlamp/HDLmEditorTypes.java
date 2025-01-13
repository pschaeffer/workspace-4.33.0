package com.headlamp;
/**
 * HDLmEditorTypes short summary.
 *
 * HDLmEditorTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of editors this code can be 
	 used for. This code can be used to build and run a modifications
	 editor, a proxy definition editor, a run-time configuration
	 editor, an authentication editor, and ignore-lists editor,
	 and a store (stored value) editor. All six types of editors 
	 are valid.
	 
	 This code can also be used to run the pass-through mechanism.
	 The pass-through mechanism is not generally an editor (with 
	 at least one exception). However, the editor machinery is 		
	 used to make the pass-through mechanism work. 	  
	  
	 This code was originally used to build and run the 
	 modifications editor. It is now used to build and run several 
	 different types of editors. A global value is used to control 
	 the current editor type in the JavaScript code (but not in the
	 Java code). */
public enum HDLmEditorTypes {
	NONE,
	AUTH,
	CONFIG,
	IGNORE,
	MOD,
	PASS,
	PROXY,
	STORE,
	POPUP,
	SIMPLE
}
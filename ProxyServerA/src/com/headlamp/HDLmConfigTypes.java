package com.headlamp;
/**
 * HDLmConfigTypes short summary.
 *
 * HDLmConfigTypes description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below defines the types of configuration values this
   code supports. All configuration values have a type and the 
   type is one of the values below. 
   
   The CACHE configuration type refers to a value that is one
   of the HDLmCacheTypes. 
   
   New types can be added at any time. However, new types should
   probably be added to the end of the enum, unless all of the 
   code is going to be recompiled.  */
public enum HDLmConfigTypes {
	NONE,
	BOOLEAN,
	CACHE,
	DOUBLE,
	/* Note that int is actually a primitive type, not an Integer */
	INT,
	STRING
}
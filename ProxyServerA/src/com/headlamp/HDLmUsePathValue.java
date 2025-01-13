package com.headlamp;
/**
 * HDLmUsePathValue short summary.
 *
 * HDLmUsePathValue description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if a path value
   can (should) be used or not. In general, path values
   can be used. However, in at least one important case, we 
   must not use the path value passed by the caller. In
   some cases, we do not have a valid path value and we
   should not try to use it. */ 
public enum HDLmUsePathValue {
	NONE,
	USEPATHVALUEOK,
	USEPATHVALUENOTOK
}
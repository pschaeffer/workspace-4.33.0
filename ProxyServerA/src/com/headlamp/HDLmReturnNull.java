package com.headlamp;
/**
 * HDLmReturnNull short summary.
 *
 * HDLmReturnNull description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if returning a null value
   is OK or not for certain calls. In some cases, it is OK to return
   a null value to the caller. In other cases, it is not OK to return
   a null value to the caller. */
public enum HDLmReturnNull {
	NONE,
	RETURNNULLOK,
	RETURNNULLNOTOK
}
package com.headlamp;
/**
 * HDLmHttpOnly short summary.
 *
 * HDLmHttpOnly description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if a cookie should be
   HTTP only or not. Some cookies are marked as HTTP only. This
   means that JavaScript can not access (or modify) these cookies. */
public enum HDLmHttpOnly {
	NONE,
	HTTPONLYTRUE,
	HTTPONLYFALSE;
}
package com.headlamp;
/**
 * HDLmSecureCookie short summary.
 *
 * HDLmSecureCookie description.
 *
 * @version 1.0
 * @author Peter
 */
/* The enum below provides values showing if a cookie should be
   secure or not. Secure cookies are only send and received using
   SSL/TLS. Of course, this value is optional. Cookies are not 
   required to be secure. If practice, most cookies are secure
   cookies. */
public enum HDLmSecureCookie {
	NONE,
	SECURECOOKIETRUE,
	SECURECOOKIEFALSE;
}
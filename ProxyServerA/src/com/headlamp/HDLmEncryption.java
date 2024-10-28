package com.headlamp;
import javax.crypto.Cipher;

import javax.crypto.spec.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmApache short summary.
 *
 * HDLmApache description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is a purely static class and no instances of this class
   can ever be created */ 
public class HDLmEncryption {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmEncryption.class);  
	/* This class can never be instantiated */
	private HDLmEncryption() {}
	/* Encrypt a string of characters using some standard code */ 
	public static String encrypt(final String key, final String value) {
		/* Check if the key value passed by the caller is null */
		if (key == null) {
			String  errorText = "Key value passed to encrypt is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the string value passed by the caller is null */
		if (value == null) {
			String  errorText = "String value passed to encrypt is null";
			throw new NullPointerException(errorText);
		}
		/* Try to actually encrypt some data. This operation may throw
		   an exception. */ 
    try {
    	/* Build and use a secret key specification for use below */
      /* IvParameterSpec   ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8")); */ 
      SecretKeySpec   secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES"); 
      Cipher  cipher = Cipher.getInstance("AES"); 
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
      /* Encrypt the data passed by the caller and return 
         the encrypted data (in base-64) to the caller. */ 
      byte[]  encrypted = cipher.doFinal(value.getBytes()); 
      return Base64.encodeBase64String(encrypted);
    }
    /* Catch any exceptions from the encryption process */ 
    catch (Exception exception) {
    	if (value != null)
    		LOG.info("Value to be encrypted - " + value);
		  LOG.info("Exception while executing encrypt");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
    }
    return null;
  }   
	/* Decrypt a string of characters using some standard code */ 
	public static String decrypt(final String key, final String encrypted) {
		/* Check if the key value passed by the caller is null */
		if (key == null) {
			String  errorText = "Key value passed to decrypt is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the encrypted text passed by the caller is null */
		if (encrypted == null) {
			String  errorText = "String value passed to decrypt is null";
			throw new NullPointerException(errorText);
		}
	  try {
	  	/* Build and use a secret key specification for use below */
	    /* IvParameterSpec   ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8")); */
	    SecretKeySpec  secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");	
	    Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	    /* Decrypt the data passed by the caller and return 
	       the encrypted data (in base-64) to the caller. */
	    byte[]  original = cipher.doFinal(Base64.decodeBase64(encrypted));	
	    return new String(original);
	  } 
	  catch (Exception exception) {
    	if (encrypted != null)
    		LOG.info("Encrypted value - " + encrypted);
		  LOG.info("Exception while executing decrypt");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
	  }	
	  return null;
	}
}
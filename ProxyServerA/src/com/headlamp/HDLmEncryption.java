package com.headlamp;
import java.security.SecureRandom;

import javax.crypto.Cipher;

import javax.crypto.spec.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmEncryption short summary.
 *
 * HDLmEncryption description.
 * 
 * The link below was used to develop this code
 *   https://www.baeldung.com/java-aes-encryption-decryption  
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
	/* Set the encrypt/decrypt mode and the type of padding (not) used */
	private static final String  algorithmStr = "AES/GCM/NoPadding";
	/* Decrypt a string of base-64 characters using some standard code.
	   The string passed to this routine as the value must already be  
	   in base-64 form. */ 
	protected static String  decrypt(final String key, final String encrypted) {
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
	  	GCMParameterSpec  initializationVector = HDLmEncryption.generateIv();
	    SecretKeySpec  secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");	
	    Cipher cipher = Cipher.getInstance(algorithmStr);
	    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, initializationVector);
	    /* Decrypt the data passed by the caller and return 
	       the decrypted value to the caller. */
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
	/* Encrypt a string of characters using some standard code. The string
	   returned to the caller is in base-64 form. */ 
	protected static String  encrypt(final String key, final String value) {
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
    	GCMParameterSpec  initializationVector = HDLmEncryption.generateIv();
      SecretKeySpec   secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES"); 
      Cipher  cipher = Cipher.getInstance(algorithmStr); 
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, initializationVector);
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
	/* Build an initialization vector for use by other code.
	   The initialization vector is the same every time. */
	protected static GCMParameterSpec  generateIv() {
    byte[]  iv = new byte[12];
    /* The next line makes the initialization vector quite
       random. However, we don't want that. The exact same
       initialization vector must be used every time. */ 
    if (1 == 2)
      new SecureRandom().nextBytes(iv);
    return new GCMParameterSpec(128, iv);
  }
}
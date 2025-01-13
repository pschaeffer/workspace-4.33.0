package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmImageInformation short summary.
 *
 * HDLmImageInformation description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmImageInformation {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
private static final Logger LOG = LoggerFactory.getLogger(HDLmImageInformation.class);  
	/* An instance of this class is created to return the results
	   from analyzing a data URL. Data URLs are typically used for
	   images, but they can actually be used for anything. Of course,
	   not all of the fields below will be used in all cases. */
	private boolean   imageValid = false;
	private boolean   imageBase64 = false;
	/* This field contains the bytes obtained by converting the characters from 
	   the data URL into bytes. The conversion is different fom base64 data 
	   versus percent sign encoded data. Of course, the data might not be
	   encoded at all. */ 
	private byte[]    imageDataBytes = null;
	private Image     imageImage = null;
	private String    errorMessage = null;
	/* This field contains the characters obtained from the bytes using some
	   type of conversion. The conversion is highly dependent on the character
	   set in use. Note that ISO-8859-1 is just one of many possible character
	   sets. ISO-8859-1 is also known as ISO-Latin-1. */
	private String    imageDataChars = null;
	/* This field contains the characters actually extracted from the data URL. 
	   These characters might be base64 encoded and they might be percent 
	   encoded. They might not be encoded at all. */ 
	private String    imageDataRaw = null;
	private String    imageSubType = null;
	/* The image type is just the type. For example if the overall
     media type was 'text/plain', then the type would be 'text',
     not 'text/plain'. */ 
	private String    imageType = null;
	private ArrayList<HDLmImageParameter>   imageParameters = new ArrayList<HDLmImageParameter>();
	/* Add an image parameter to the image parameters array. 
	   Note that the caller can not pass an invalid (null) 
	   value for the new image parameter. This is an an 
	   error condition. */
	protected void addParameter(HDLmImageParameter newImageParm) {
		if (newImageParm == null) {
			String  errorText = "New image parameter is null";
			throw new NullPointerException(errorText);
		}
		imageParameters.add(newImageParm); 
	}
	/* Process a data URL value passed by the caller. This routine 
	   does only one thing. This routine checks if a special value
	   (;base64) it the next thing in data URL. Note that the 
	   special value is not really in parenthesis or quotes. This
	   routine returns true, if the special value comes next and
	   false if it does not. This routine does not return the 
	   updated current position to the caller. Note that a special
	   value must not be followed by an equals sign. If the special
	   value is followed by an equals sign, then we really have a 
	   content type, not a true special value. */
	protected static boolean checkBase64(String urlStr,
			                                 int curPos,
			                                 HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to checkBase64 is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to checkBase64 is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to checkBase64 is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		boolean     rv = false;
		HDLmToken   token;
		String      tokenValue;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {			
			/* Get the next token from the URL string. Check if the token could
			   be part of a special value or not. */ 
		  token = HDLmString.getNextToken(urlStr, curPos, ";");
		  /* We don't have any work to do if we have reached the sentinel token */
		  if (token.getType() == HDLmTokenTypes.END)
		  	break;
		  /* We don't have any work to do, if we have found anything other than 
		     an operator. */
		  if (token.getType() != HDLmTokenTypes.OPERATOR)
		  	break;
		  /* We don't have any work to do if we have reached the comma value */
		 	tokenValue = token.getValue();
		  if (tokenValue.equals(","))
		 	  break;	
		  /* We don't have any work to do if we have an operator other than semicolon */
		 	tokenValue = token.getValue();
		  if (!tokenValue.equals(";"))
		 	  break;	
			/* Skip past the semicolon token */
			curPos += token.getValue().length();
			/* Get the next token from the URL string. Check if the token could
		     be part of a special value or not. */ 
	    token = HDLmString.getNextToken(urlStr, curPos, ";");
	    /* We don't have any work to do if we have reached the sentinel token */
	    if (token.getType() == HDLmTokenTypes.END)
	  	  break;
	    /* We don't have any work to do, if we have found anything other than 
	       an identifier */
	    if (token.getType() != HDLmTokenTypes.IDENTIFIER)
	    	break;
		  /* We don't have any work to do, if the next value is unexpected  */
		 	tokenValue = token.getValue();
		  if (!tokenValue.equals("base64"))
		 	  break;	
			/* Skip past the identifier token */
			curPos += token.getValue().length();
			/* Get the next token from the URL string. Check if the token could
	       be part of a special value or not. */ 
      token = HDLmString.getNextToken(urlStr, curPos, ";");
	    /* We don't have any work to do if we have reached the sentinel token */
	    if (token.getType() == HDLmTokenTypes.END) {
	    	rv = true;
	  	  break;
	    }
	    /* We don't have any work to do, if we have found anything other than 
         an operator */
      if (token.getType() != HDLmTokenTypes.OPERATOR) {
      	rv = true;
   	    break;
      }
		  /* We are done if the operator value is anything other than 
		     an equals sign */
      tokenValue = token.getValue();
      if (!tokenValue.equals("=")) {
      	rv = true;
      	break;
      }
	    break;
		}
		return rv;
	}
	/* Convert a set of bytes to an image */   
	protected Image convertBytesToImage() {
		/* Try to convert the bytes to an image */   
    try {
			this.imageImage = ImageIO.read(new ByteArrayInputStream(this.imageDataBytes));
		} 
    catch (IOException ioException) {
			LOG.info("IOException while executing convertBytesToImage");
	 		LOG.info(ioException.getMessage(), ioException);
	 		HDLmEvent.eventOccurred("IOException");	
	 		String   ioExecMsg = ioException.getMessage();
		  /* Build the error message */
	 		String   errorMsg = String.format("IOException message (%s)", ioExecMsg);
	 		this.setErrorMessage(errorMsg);
	 		this.imageImage = null;
	 		return null;
		}	 
    catch (Exception exception) {
			LOG.info("Exception while executing convertBytesToImage");
	 		LOG.info(exception.getMessage(), exception);
	 		HDLmEvent.eventOccurred("Exception");	
	 		String   execMsg = exception.getMessage();
		  /* Build the error message */
	 		String   errorMsg = String.format("Exception message (%s)", execMsg);
	 		this.setErrorMessage(errorMsg);
	 		this.imageImage = null;
	 		return null;
		}	
	  /* Return the image value to the caller. Of course, the image
	     value might be null. */  
	  return this.imageImage;
	}	
	/* Convert the data value from the current data URL to a 
	   set of bytes. The exact approach used depends on whether
	   the base64 flag is set or not. */  
	protected void convertDataToBytes() {	
  	/* The data URL may have specified a character set. We need to find the character
	     set name and use it, if possible */ 
	  String  charset = this.findCharset();    
	  /* If a character set was not specified, we need to use a default value */  
	  if (charset == null)
		  charset = "iso-8859-1"; 
		/* Check if the base64 flag is set or not */
    if (this.imageBase64) {
    	/* Define a few local variables */; 
  		Base64  base64 = new Base64();
    	this.imageDataBytes = base64.decode(imageDataRaw);    
    }
    /* Assume that the characters have been URI encoded. Note that 
       a local routine is used to convert from URI encoded format 
       (where the blank sign is represented as %20) to a set of 
       bytes. */
    else {    
    	/* Convert the image data characters to a byte array */  
    	this.imageDataBytes = HDLmString.decodeValue(imageDataRaw);
    }
  	/* Convert the bytes to characters using the correct encoding 
  	   or a default value */     	
  	try {
			this.imageDataChars = new String(this.imageDataBytes, charset);
		} 
  	catch (UnsupportedEncodingException ueException) {
  		if (charset != null)
			  LOG.info("Charset - " + charset);
			LOG.info("UnsupportedEncodingException while executing convertDataToBytes");
	 		LOG.info(ueException.getMessage(), ueException);
	 		HDLmEvent.eventOccurred("UnsupportedEncodingException");	
	 		String   ueExecMsg = ueException.getMessage();
		  /* Build the error message */
	 		String   errorMsg = String.format("UnsupportedEncodingException message (%s) Charset (%s)",  
	 				                              ueExecMsg, 
	 				                              charset);
	 		this.setErrorMessage(errorMsg);
	 		this.imageDataChars = null;
	 		return;
		}
  	catch (Exception exception) {
  		if (charset != null)
			  LOG.info("Charset - " + charset);
			LOG.info("Exception while executing convertDataToBytes");
	 		LOG.info(exception.getMessage(), exception);
	 		HDLmEvent.eventOccurred("Exception");	
	 		String   execMsg = exception.getMessage();
		  /* Build the error message */
	 		String   errorMsg = String.format("Exception message (%s) Charset (%s)",  
	 				                              execMsg, 
	 				                              charset);
	 		this.setErrorMessage(errorMsg);
	 		this.imageDataChars = null;
	 		return;
		} 	     
		return;
	}	
	/* This routine scans all of the parameters (if any) of the current
	   image information object and returns the first charset value found.
	   Of course, this routine can only be used after an image information
	   object has been built from a data URL. A null value will be returned
	   if the image information object does not have any parameters. A null
	   value will also be returned if none of the parameters specified a 
	   character set. */   
	protected String findCharset() {
		/* Define a few local variables */; 
		HDLmImageParameter  imageParm;
		int   parameterCount = this.getParametersCount();
		/* Check if we have any parameters */
		if (parameterCount <= 0)
			return null;
    /* Scan the parameters looking for the first parameter than
       specifies a characters set */
		for (int i = 0; i < parameterCount; i++) {
			imageParm = this.getParameter(i);
			String  parmName = imageParm.getParameterName();
			if (parmName.equals("charset")) {
				String  parmValue = imageParm.getParameterValue();
				return parmValue;				
			}			
		}
		return null;
	}
	/* Get the base64 value and return it to the caller */
	protected boolean getBase64() {
		return imageBase64;
	}
	/* Process a data URL value passed by the caller. This routine 
     looks for a valid base64 value in the data URL. If a valid
     base64 value is found, the base64 value is skipped and the
     updated current position is returned to the caller. If any
     errors are detected, then an error message is stored in the
     image information object and a negative current position is 
     returned to the caller. */ 
	protected static int getBase64FromData(String urlStr,
			                                   int curPos,
			                                   HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getBase64FromData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getBase64FromData is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getBase64FromData is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		boolean     base64Value;
		HDLmToken   token;
		String      tokenValue;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Get the next token from the URL string */
			token = HDLmString.getNextToken(urlStr, curPos, ";");
			/* Make sure the first token is not the sentinel token */
			if (token.getType() == HDLmTokenTypes.END) {
				String  errorText = String.format("Sentinel token found where base64 value possibily expected"); 
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Make sure the next token is some type of operator token */
			if (token.getType() != HDLmTokenTypes.OPERATOR) {
				String  errorText = String.format("Next token type (%s) invalid where base64 value possbily expected",
						                              token.getType().toString()); 
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Check the value of the operator token */
			tokenValue = token.getValue();
			if (tokenValue.equals(",") == false &&
					tokenValue.equals(";") == false) {
				String  errorText = String.format("Next operator token value (%s) invalid where base64 value possbily expected",
                                          token.getValue()); 
        imageInfo.setErrorMessage(errorText);
        return -1;
			}
		  /* Check for a base64 value in the data URL */
			base64Value = HDLmImageInformation.checkBase64(urlStr, curPos, imageInfo);
			if (base64Value) {
				curPos += 7;
				imageInfo.setBase64(true);
			}
			break;
		}
		return curPos;
	}
	/* Get the bytes (if any) and return them to the caller */
	protected byte[] getBytes() {
		return imageDataBytes;
	}
	/* Process a data URL value passed by the caller. This routine 
	   looks for a valid comma value in the data URL. If a valid
	   comma value is found, the comma value is skipped and the
	   updated current position is returned to the caller. If any
	   errors are detected, then an error message is stored in the
	   image information object and a negative current position is 
	   returned to the caller. */ 
	protected static int getComma(String urlStr,
			                          int curPos,
			                          HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getComma is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getComma is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getComma is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		HDLmToken   token;
		String      tokenValue;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Get the next token from the URL string */
			token = HDLmString.getNextToken(urlStr, curPos, ";");
			/* Make sure the next token is not the sentinel token */
			if (token.getType() == HDLmTokenTypes.END) {
				String  errorText = String.format("Sentinel token found where commma operator expected"); 
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Make sure the next token is some type of operator token */
			if (token.getType() != HDLmTokenTypes.OPERATOR) {
				String  errorText = String.format("Next token type (%s) invalid where comma operator expected",
						                              token.getType().toString()); 
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Check the value of the operator token */
			tokenValue = token.getValue();
			if (tokenValue.equals(",") == false) {
				String  errorText = String.format("Next operator token value (%s) invalid where comma operator expected",
	                                       token.getValue()); 
	      imageInfo.setErrorMessage(errorText);
	      return -1;
			}
		  /* Skip the comma token */
			curPos += 1;
			break;
		}
		return curPos;
	}
	/* Get the data value (if any) and return it to the caller. Note that
	   this routine will return a processed value. In other words a value
	   such as (A brief note) will be returned from (data:,A%20brief%20note).
	   The prior examples have parenthesis around the example values.  */
	protected String getDataChars() {
		return imageDataChars;
	}
	/* Process a data URL value passed by the caller. This routine 
	   looks for a valid data prefix in the data URL. If a valid
	   data prefix is found, the data prefix is skipped and the
	   updated current position is returned to the caller. If any
	   errors are detected, then an error message is stored in the
	   image information object and a negative current position is 
	   returned to the caller. */ 
	protected static int getDataPrefix(String urlStr,
			                               int curPos,
			                               HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getDataPrefix is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos != 0) {
			String  errorText = String.format("Current position value (%d) passed to getDataPrefix is not zero", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getDataPrefix is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		HDLmToken   token;
		/* Check if the URL string passed by the caller is empty or not */
		if (urlStr.length() == 0) {			
			String  errorText = String.format("Caller passed an empty string to processDataUrl"); 
	    imageInfo.setErrorMessage(errorText);
	    return -1;			
		}
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Get the first token from the URL string. The value must be
			   'data' and it must be a string. */
			token = HDLmString.getNextToken(urlStr, curPos, ";");
			/* Make sure the first token has the correct type */
			if (token.getType() != HDLmTokenTypes.IDENTIFIER) {
				String  errorText = String.format("First token type (%s) is wrong", 
						                              token.getType().toString());
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Make sure the first token has the correct value */
			if (!token.getValue().equals("data")) {
				String  errorText = String.format("First token value (%s) is wrong", 
						                              token.getValue());
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Skip past the first token */
			curPos += token.getValue().length();
			/* Check the second token */
			token = HDLmString.getNextToken(urlStr, curPos, ";");
			/* Make sure the second token has the correct type */
			if (token.getType() != HDLmTokenTypes.OPERATOR) {
				String  errorText = String.format("Second token type (%s) is wrong", 
						                              token.getType().toString());
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Make sure the second token has the correct value */
			if (!token.getValue().equals(":")) {
				String  errorText = String.format("Second token value (%s) is wrong", 
						                              token.getValue());
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Skip past the second token */
			curPos += token.getValue().length();
			break;
		}
		return curPos;
	}
	/* Get the data value (if any) and return it to the caller. Note that
     this routine will return the raw value. In other words a value such
     as (A%20brief%20note) will be returned from (data:,A%20brief%20note).
     The prior examples has parenthesis around the example values.  */
  protected String getDataRaw() {
    return imageDataRaw;
  }
	/* Process a data URL value passed by the caller. This routine 
	   looks for a valid data value in the data URL. If a valid
	   data value is found, the data value is stored and then 
	   skipped and the updated current position is returned to 
	   the caller. If any errors are detected, then an error
	   message is stored in the image information object and 
	   a negative current position is returned to the caller. 
	   The data value may be zero-length. This is not an error
	   condition. */ 
	protected static int getDataValue(String urlStr,
			                              int curPos,
			                              HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getDataValue is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getDataValue is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getDataValue is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */; 
		String  dataValue;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			dataValue = urlStr.substring(curPos);
			curPos += dataValue.length();
			break;
		}
		/* Save the data value in the image information instance */
		imageInfo.setDataRaw(dataValue);
		return curPos;
	}
	/* Get the error message (if any) and return it to the caller */
	protected String getErrorMessage() {
		return errorMessage;
	}
	/* Get the image value (if any) and return it to the caller */
  protected Image getImage() {
    return imageImage;
  }
	/* Process a data URL value passed by the caller. This routine 
	   looks for a valid media type in the data URL. If a valid
	   media type is found, the media type is processed and the
	   updated current position is returned to the caller. If any
	   errors are detected, then an error message is stored in the
	   image information object and a negative current position is 
	   returned to the caller. Note that the media type is optional. */ 
	protected static int getMediaType(String urlStr,
			                              int curPos,
			                              HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getMediaType is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getMediaType is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getMediaType is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		HDLmToken   token;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check, process, and skip the optional type and subtype */ 
			curPos = HDLmImageInformation.getTypeSubType(urlStr, curPos, imageInfo);
			if (curPos < 0)
				break;
			/* Check, process, and skip the optional parameters */ 
			curPos = HDLmImageInformation.getParameters(urlStr, curPos, imageInfo);
			if (curPos < 0)
				break;
      break;
		}
		return curPos;
	}
	/* Get a specific parameter from the array (ArrayList to be precise)
	   of parameters. Of course this only works after the data URL has
	   been processed. Note that this is not a static method. This is
	   a class method that requires an instance of the class. The index
	   value passed by the caller must be valid. The index value passed
	   by the caller starts from zero, not one. In other words, the 
	   first valid entry (if there is one) is zero, not one. If the
	   index value passed to this routine is invalid, this routine will
	   throw an exception. */ 
  protected HDLmImageParameter getParameter(int index) {
		/* Check if the index value passed by the caller is invalid or not */
		if (index < 0) {
			String  errorText = String.format("Index value (%d) passed to getParameter is less than zero", 
					                             index);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the index value is valid or not */
		int   parametersSize = this.imageParameters.size();
		if (index >= parametersSize) {
			String  errorText = String.format("Index value (%d) passed to getParameter is too high", 
                                       index);
      Exception exception = new IllegalArgumentException(errorText);
      throw new IllegalArgumentException(errorText, exception);
		}
	  /* Return the requested parameter */ 
	  return this.imageParameters.get(index);
  }
	/* Process a data URL value passed by the caller. This routine 
	   looks for the next parameter in the data URL. If a valid
	   parameter is found, it is processed (saved) and the updated
	   current position is returned to the caller. If any errors
	   are detected, then an error message is stored in the image
	   information object and a negative current position is returned
	   to the caller. Note that parameters are optional. 
	   
	   This routine may or may not find another parameter in the data
	   URL. If another parameter is found, then the current position 
	   will be updated and the new current position value will be 
	   returned to the caller. If another parameter is not found, 
	   then the current position will not be changed. */ 
	protected static int getParameterFromData(String urlStr,
			                                      int curPos,
			                                      HDLmImageInformation imageInfo,
			                                      HDLmImageParameter imageParm) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getParameterFromData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getParameterFromData is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getParameterFromData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the image parameter value passed by the caller is null */
		if (imageParm == null) {
			String  errorText = "Image Parameter value passed to getParameterFromData is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		HDLmToken   token;
		String      tokensValue;
		String      tokenValue;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {			
			/* Get the next token from the URL string. The value might be part
			   of a parameter or we may not have a parameter. */
		  token = HDLmString.getNextToken(urlStr, curPos, ";");
		  /* We don't have any work to do if we have reached the sentinel token */
		  if (token.getType() == HDLmTokenTypes.END)
		  	break;
		  /* We don't have any work to do if we have reached the comma value 
		     or a few other cases */
		  if (token.getType() == HDLmTokenTypes.OPERATOR) {
		  	tokenValue = token.getValue();
		  	if (tokenValue.equals(","))
		  		break;		  	
		  	/* Check if we have reached the special (;base64) value. We don't 
		  	   have any work to do in that case. Note that the special value
		  	   is not really in parenthesis or quotes. */
		  	if (HDLmImageInformation.checkBase64(urlStr, curPos, imageInfo))
		  	  break;
		  	/* The current operator token must be a semicolon at this point.
		  	   Anything other than a semicolon is an error at this point. */
		  	if (!tokenValue.equals(";")) {
					String  errorText = String.format("Parameter value does not start (%s) with a semicolon", 
                                            tokenValue);
          imageInfo.setErrorMessage(errorText);
          return -1;		  		
		  	}	
				/* Skip past the semicolon token */
				curPos += token.getValue().length();
		  }
			/* Get the next token from the URL string. The value might be part
		     of a parameter or we may not have a parameter. */
	    token = HDLmString.getNextToken(urlStr, curPos, ";");
		  /* Check for a sentinel token here. We should never have a sentinel
		     token at this point. */ 
	    if (token.getType() == HDLmTokenTypes.END) {
				String  errorText = String.format("Unexpected end-of-string found where parameter name expected"); 
				imageInfo.setErrorMessage(errorText);
        return -1;		
	    }
		  /* Check for a operator token here. We should never have a operator
	       token at this point. */ 
	    if (token.getType() == HDLmTokenTypes.OPERATOR) {
		 		String  errorText = String.format("Operator token (%s) found where parameter name expected",
		 				                              token.getValue());
		 		imageInfo.setErrorMessage(errorText);
	      return -1;		
	    }
			/* Make sure the current token has the correct type */
		  if (token.getType() != HDLmTokenTypes.IDENTIFIER) {
				String  errorText = String.format("Parameter token type (%s) is wrong, where identifier expected", 
						                              token.getType().toString());
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
		  /* We can now save the parameter name value */
		  tokenValue = token.getValue();
		  imageParm.setParameterName(tokenValue);
			/* Skip past the parameter name */
			curPos += token.getValue().length();
			/* Get the next token from the data URL string. The next token must be
			   the equals sign separating the parameter name from the parameter 
			   value. */  
      token = HDLmString.getNextToken(urlStr, curPos, ";");
	    /* Check for a sentinel token here. We should never have a sentinel
	       token at this point. */ 
      if (token.getType() == HDLmTokenTypes.END) {
		  	String  errorText = String.format("Unexpected end-of-string found where parameter equals sign expected"); 
			  imageInfo.setErrorMessage(errorText);
        return -1;		
      }
  		/* Make sure the current token has the correct type. The only valid
  		   type at this point is an operator token. */
  	  if (token.getType() != HDLmTokenTypes.OPERATOR) {
  			String  errorText = String.format("Parameter token type (%s) is wrong, where equals sign expected", 
  					                              token.getType().toString());
  			imageInfo.setErrorMessage(errorText);
  			return -1;
  		}
  	  /* We can now check for an equals sign that is required at this point */
  	  tokenValue = token.getValue();
  	  if (!tokenValue.equals("=")) {	 
			  String  errorText = String.format("Parameter token value (%s) is wrong, where equals sign expected", 
			  		                              token.getValue());
			  imageInfo.setErrorMessage(errorText);
			  return -1;
		  }
		  /* Skip past the equals sign */
		  curPos += token.getValue().length();
			/* Get the parameter value from the URL string. The parameter value must 
		     be the value after the equals sign. The parameter value can actually 
		     be in several parts. */   
	    tokensValue = HDLmImageInformation.getTokensValue(urlStr, curPos, imageInfo);
	    if (tokensValue == null) {
	    	curPos = -1;
	  	  break;
	    }
	    if (tokensValue.equals("")) {
	    	curPos = -1;
	    	break;
	    }
	    /* We can now use the value we just obtained as the parameter value */
	    imageParm.setParameterValue(tokensValue);
		  /* Skip past the parameter value */
		  curPos += tokensValue.length();	
	    break;
		}
		return curPos;
	}
  /* Process a data URL value passed by the caller. This routine 
	   looks for zero or more parameters in the data URL. If valid
	   parameters are found, they are processed (saved) and the
		 updated current position is returned to the caller. If any
	   errors are detected, then an error message is stored in the
	   image information object and a negative current position is 
	   returned to the caller. Note that the parameters are optional. */ 
	protected static int getParameters(String urlStr,
			                               int curPos,
			                               HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getParameters is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getParameters is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getParameters is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		HDLmToken   token;
		int         newPosition;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {			
			/* Get the next parameter (if possible) from the data URL */
			HDLmImageParameter  imageParm = new HDLmImageParameter();
		  newPosition = HDLmImageInformation.getParameterFromData(urlStr, curPos, imageInfo, imageParm);
		  if (newPosition < 0) {
		  	curPos = newPosition;
		  	break;
		  }
		  /* Check if we actually got a parameter from the data URL. This 
		     will not always be true. */
		  if (newPosition == curPos)
		  	break;
		  /* Use the new image parameter */
		  imageInfo.addParameter(imageParm);
		  curPos = newPosition;
		}
		return curPos;
	}
	/* Get the number of parameters in the current data URL. 
	   Of course this only works after the data URL has been 
	   processed. Note that this is not a static method. This 
	   is a class method that requires an instance of the class. */ 
	protected int getParametersCount() {
		/* Return the number of parameters */ 
		return this.imageParameters.size();
	}
	/* Get the subtype value and return it to the caller */
	protected String getSubType() {
		return imageSubType;
	}
	/* Process a data URL value passed by the caller. This routine 
     looks for a valid subtype in the data URL. If a valid subtype
     is found, then it is processed (saved) and the updated current
     position is returned to the caller. If any errors are detected,
     then an error message is stored in the image information object
     and a negative current position is returned to the caller. Note 
     that the media subtype is not optional at this point. Overall,
     the media type and subtype are optional. However, when this 
     routine is called, we have already obtained the type value which
     makes the subtype mandatory. */ 
	protected static int getSubTypeFromData(String urlStr,
			                                    int curPos,
			                                    HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getSubTypeFromData is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getSubTypeFromData is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getSubTypeFromData is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		HDLmToken   token; 
		int         tokenCount = 0;
		String      operatorValue;
		String      subTypeValue = "";
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {			
			tokenCount++;
			/* Get the next token from the URL string. The token may terminate 
			   the subtype in some way. This is not an error condition. */
		  token = HDLmString.getNextToken(urlStr, curPos, ";");
		  if (token.getType() == HDLmTokenTypes.END) {
		  	/* The first token of a subtype should not be the sentinel token.
		  	   This is an error condition. */
		    if (tokenCount == 1) {
					String  errorText = String.format("First token type (%s) is wrong, where subtype token expected", 
                                            token.getType().toString());
          imageInfo.setErrorMessage(errorText);
          return -1;		    	
		    }
		  	break;
		  }
		  /* We may have an operator token that terminates the subtype */
		  if (token.getType() == HDLmTokenTypes.OPERATOR) {
		  	operatorValue = token.getValue();
		  	if (operatorValue.equals(";") ||
		  			operatorValue.equals(","))
		  		break;
			  /* The first token of a subtype should not be an operator. This is
		       an error condition. */
		    if (tokenCount == 1) {
					String  errorText = String.format("First operator token value (%s) is wrong, where subtype token expected", 
                                            token.getValue());
          imageInfo.setErrorMessage(errorText);
          return -1;		    	
		    }		   
		  }		  
			/* Make sure the current token has the correct type. Actually several
			   token type types are possible at this point.  */
		  if (token.getType() != HDLmTokenTypes.OPERATOR && 
			    token.getType() != HDLmTokenTypes.IDENTIFIER) {
				String  errorText = String.format("Token type (%s) is wrong, where subtype token expected", 
						                              token.getType().toString());
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Use and skip past the current token */
		  subTypeValue += token.getValue();
			curPos += token.getValue().length();
		}
		/* Make sure we got a valid subtype value from the data URL */   	 
		if (subTypeValue.equals("")) {
			String  errorText = String.format("Valid subtype not obtained from the data URL"); 
			imageInfo.setErrorMessage(errorText);
			return -1;
		}
    /* At this point we have a valid subtype value */
		imageInfo.setSubType(subTypeValue);
		return curPos;
	}
	/* Process a data URL value passed by the caller. This routine 
	   looks for a valid value in the data URL. If a valid value is
	   found, then the value is returned to the caller. A valid 
	   value will be comprised of one or more tokens.  
	   
	   If any errors are detected, then an error message is stored 
	   in the image information object and a null string value will
	   be returned to the caller. If a valid value is found, then it
	   is returned as a non-null string to the caller.  
	   
	   The possibly modified current position is not returned to the
	   caller in any way. The caller can infer the updated current
	   position (maybe) by checking the length of the returned string. */ 
	protected static String getTokensValue(String urlStr,
			                                   int curPos,
			                                   HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getTokensValue is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getTokensValue is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getTokensValue is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		HDLmToken   token;  
		int         tokensCount = 0;
		String      operatorValue;
		String      stringValue = "";
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {	
			/* Increment the tokens count */
			tokensCount++;
			/* Get the next token from the URL string. The token may terminate 
			   the string value in some way. This is not an error condition. */
		  token = HDLmString.getNextToken(urlStr, curPos, ";");
		  if (token.getType() == HDLmTokenTypes.END) {
			  if (tokensCount == 1) {
					String  errorText = String.format("First token type (%s) is wrong, where string token expected", 
                                            token.getType().toString()); 
					imageInfo.setErrorMessage(errorText);
					return null;
				}
		  	break;
		  }
		  /* We may have an operator token that terminates the string value */
		  if (token.getType() == HDLmTokenTypes.OPERATOR) {
		  	operatorValue = token.getValue();
		  	if (operatorValue.equals(";") ||
		  			operatorValue.equals(","))
		  		break;	
			  if (tokensCount == 1) {
					String  errorText = String.format("First operator token value (%s) is wrong, where string token expected", 
                                            token.getValue()); 
					imageInfo.setErrorMessage(errorText);
					return null;
				}
		  }		  
			/* Make sure the current token has the correct type. Actually several
			   token type types are possible at this point.  */
		  if (token.getType() != HDLmTokenTypes.INTEGER && 
		      token.getType() != HDLmTokenTypes.OPERATOR && 
			    token.getType() != HDLmTokenTypes.IDENTIFIER) {
		  	if (tokensCount == 1) {
					String  errorText = String.format("First token type (%s) is wrong, where string token expected", 
                                            token.getType().toString());
          imageInfo.setErrorMessage(errorText);
          return null;
		  	}
				String  errorText = String.format("Token type (%s) is wrong, where string token expected", 
						                              token.getType().toString());
				imageInfo.setErrorMessage(errorText);
				return null;
			}
			/* Use and skip past the current token */
		  stringValue += token.getValue();
			curPos += token.getValue().length();
		}
		/* Make sure we got a valid string value from the data URL */   	 
		if (stringValue.equals("")) {
			String  errorText = String.format("Valid string value not obtained from the data URL"); 
			imageInfo.setErrorMessage(errorText);
			return null;
		}
	  /* At this point we have valid string value */
		return stringValue;
	}
	/* Get the type value and return it to the caller */
	protected String getType() {
		return imageType;
	}
  /* Process a data URL value passed by the caller. This routine 
	   looks for a valid type and subtype in the data URL. If a valid
	   type and subtype are found, they are processed (saved) and the
  	 updated current position is returned to the caller. If any
	   errors are detected, then an error message is stored in the
	   image information object and a negative current position is 
	   returned to the caller. Note that the media type and subtype 
	   are optional. */ 
	protected static int getTypeSubType(String urlStr,
			                                int curPos,
			                                HDLmImageInformation imageInfo) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getTypeSubType is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the current position passed by the caller is invalid or not */
		if (curPos < 5) {
			String  errorText = String.format("Current position value (%d) passed to getTypeSubType is less than 5", 
					                             curPos);
			Exception exception = new IllegalArgumentException(errorText);
			throw new IllegalArgumentException(errorText, exception);
		}
		/* Check if the image information value passed by the caller is null */
		if (imageInfo == null) {
			String  errorText = "Image information value passed to getTypeSubType is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */;
		HDLmToken   token;
		String      slashValue;
		String      typeValue;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {			
			/* Get the next token from the URL string. The value might be part
			   of the type or we may not even have a type. */
		  token = HDLmString.getNextToken(urlStr, curPos, ";");
		  typeValue = token.getValue();
		  /* We don't have any work to do if we have reached the sentinel token */
		  if (token.getType() == HDLmTokenTypes.END)
		  	break;
		  /* If we have an operator token at this point, then we don't have a type
		     and subtype. Of course, this test only applies to some operators. */ 
		  if (token.getType() == HDLmTokenTypes.OPERATOR) {
		  	/* Check for an operator value that means that we can not have 
		  	   a type value here */
			  if (typeValue.equals(",") ||
			  		typeValue.equals(";"))
			  	break;
				String  errorText = String.format("Invalid operator (%s) found, where type expected", 
                                          typeValue); 
        imageInfo.setErrorMessage(errorText);
        return -1;
		  }
		  /* The type value must be a proper identifier */
		  if (token.getType() != HDLmTokenTypes.IDENTIFIER)
		  	break;
      /* At this point we have a valid type values */
			imageInfo.setType(typeValue);
			/* Skip past the token */
			curPos += token.getValue().length();
			/* Get the next token from the URL string. The value must 
			   be the slash separating the type from the subtype. */  
		  token = HDLmString.getNextToken(urlStr, curPos, ";");
			/* Make sure the current token has the correct type */
		  slashValue = token.getValue();
			if (token.getType() != HDLmTokenTypes.OPERATOR) {
				String  errorText = String.format("Token type (%s) is wrong, where forward slash expected", 
						                              token.getType().toString());
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Make sure the current token has the correct value */
			if (!slashValue.equals("/")) {
				String  errorText = String.format("Token value (%s) is wrong, where forward slash expected", 
						                              token.getValue());
				imageInfo.setErrorMessage(errorText);
				return -1;
			}
			/* Skip past the token */
			curPos += token.getValue().length();
			/* Get the subtype from the URL string. The value must 
			   be the subtype after the forward slash. The subtype
			   value can actually be in several parts. */   
		  curPos = HDLmImageInformation.getSubTypeFromData(urlStr, curPos, imageInfo);
		  if (curPos < 0)
		  	break;
	    break;
		}
		return curPos;
	}
	/* Process a data URL value passed by the caller. This routine 
	   ultimately returns an object to the caller. The object
	   describes the data URL and contains a reference to the
	   image (if any). */ 
	protected static HDLmImageInformation processDataUrl(String urlStr) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to processDataUrl is null";
			throw new NullPointerException(errorText);
		}
		/* Define a few local variables */
		HDLmImageInformation   imageInfo = new HDLmImageInformation();
		HDLmToken              token;
		int                    curPos = 0;
		/* What follows is a dummy loop used only to allow break to work */
		while (true) {
			/* Check and skip the data (followed by a colon) prefix */
			curPos = HDLmImageInformation.getDataPrefix(urlStr, curPos, imageInfo);
			if (curPos < 0) {
				/* imageInfo = null; */			
				break;
			}	
			/* Check, process, and skip the optional media type */
			curPos = HDLmImageInformation.getMediaType(urlStr, curPos, imageInfo);
			if (curPos < 0) {
				/* imageInfo = null; */			
				break;
			}	
			/* Check, process, and skip the optional base64 value */
			curPos = HDLmImageInformation.getBase64FromData(urlStr, curPos, imageInfo);
			if (curPos < 0) {
				/* imageInfo = null; */			
				break;
			}	
			/* Check and skip the mandatory comma value */
			curPos = HDLmImageInformation.getComma(urlStr, curPos, imageInfo);
			if (curPos < 0) {
				/* imageInfo = null; */			
				break;
			}	
			/* Check and process the mandatory data value. The data value may 
			   be a zero-length string. */
			curPos = HDLmImageInformation.getDataValue(urlStr, curPos, imageInfo);
			if (curPos < 0) {
				/* imageInfo = null; */			
				break;
			}	
		  /* Convert the data value to a byte array. The byte array may be empty. 
		     This is not an error condition. The byte array is converted to a 
		     character string in some cases. */
		  imageInfo.convertDataToBytes(); 
		  if (imageInfo.getErrorMessage() != null) {
		  	/* imageInfo = null; */	
		  	break;
		  }
		  /* Convert the bytes to an image in some cases. This is only done
		     if the base64 flag is set. This operation can fail for any number
		     of reasons. Note that the check for the base64 flag is no longer
		     used. Some actual images are not base64 encoded. */
		  if (imageInfo.imageDataBytes != null &&
		  		imageInfo.imageDataBytes.length > 0) {
			  imageInfo.convertBytesToImage(); 
			  if (imageInfo.getErrorMessage() != null) {
			  	/* imageInfo = null; */
			  	break;	  	
		    }
		  }		  
      /* Show that the image appears to be valid */
		  imageInfo.setImageValid(true);
      break;
		}
		return imageInfo;
	}
	/* Set or reset the base64 value */
	protected void setBase64(boolean newBase64) {
		imageBase64 = newBase64;
	}
	/* Set or reset the data value. Note that the caller can 
     not pass a null value for the new data value. This is 
     an error condition. */
	protected void setDataRaw(String newDataValue) {
		if (newDataValue == null) {
			String  errorText = "New raw data value string is null";
			throw new NullPointerException(errorText);
		}
		imageDataRaw = newDataValue;
	}
	/* Set or reset the error message. Note that the caller can 
	   not pass a null value for the new error message. This is 
	   an error condition. */
	protected void setErrorMessage(String newErrorMessage) {
		if (newErrorMessage == null) {
			String  errorText = "New error message string is null";
			throw new NullPointerException(errorText);
		}
		errorMessage = newErrorMessage;
	}
	/* Set or reset the image reference. Note that the caller can 
     not pass a null value for the new image reference. This is 
     an error condition. */
	protected void setImage(Image newImage) {
		if (newImage == null) {
			String  errorText = "New image reference is null";
			throw new NullPointerException(errorText);
		}
		imageImage = newImage;
	}
	/* Set or reset the image validity value */
	protected void setImageValid(boolean newValid) {
		imageValid = newValid;
	}
	/* Set or reset the subtype value. Note that the caller can 
     not pass an invalid (null) value for the new subtype value. 
     This is an error condition. */
	protected void setSubType(String newSubTypeValue) {
		if (newSubTypeValue == null) {
		  HDLmAssertAction(false, "New subtype value is a null string"); 
		}
		imageSubType = newSubTypeValue;
	}
	/* Set or reset the type value. Note that the caller can 
     not pass an invalid (null) value for the new type value. 
     This is an error condition. */
	protected void setType(String newTypeValue) {
		if (newTypeValue == null) {
		  HDLmAssertAction(false, "New type value is a null string"); 
		}
		imageType = newTypeValue;
	}
}
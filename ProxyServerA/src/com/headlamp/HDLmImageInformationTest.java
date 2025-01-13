package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Image;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmImageInformationTest short summary.
 *
 * HDLmImageInformationTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmImageInformationTest {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmImageInformationTest.class); 
	@Test
	void processDataUrl() {
		/* Run a few processDataUrl tests */
		boolean   imageBase64;
		HDLmImageInformation  imageInfo;
		HDLmImageParameter  imageParm;
		Image   imageValue;
		int     parametersCount;
		String  base64ImageData;
		String  errorMessage;
		String  urlStr = null;
		String  imageDataChars;
		String  imageDataRaw;
		String  imageParmName;
		String  imageParmValue;
		String  imageSubType;
		String  imageType;		
		/* Run a processDataUrl test */
		urlStr = "";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Caller passed an empty string to processDataUrl");	
		/* Run a processDataUrl test */
		urlStr = " ";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First token type (SPACE) is wrong");		
		/* Run a processDataUrl test */
		urlStr = "13";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First token type (INTEGER) is wrong");
		/* Run a processDataUrl test */
		urlStr = "da";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First token value (da) is wrong");
		/* Run a processDataUrl test */
		urlStr = "data";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Second token type (END) is wrong");
		/* Run a processDataUrl test */
		urlStr = "data+";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Second token value (+) is wrong");
		/* Run a processDataUrl test */
		urlStr = "data:";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Sentinel token found where base64 value possibily expected");
		/* Run a processDataUrl test */
		urlStr = "data:text";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Token type (END) is wrong, where forward slash expected");
		/* Run a processDataUrl test */
		urlStr = "data:text+";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Token value (+) is wrong, where forward slash expected");
		/* Run a processDataUrl test */
		urlStr = "data:text/";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First token type (END) is wrong, where subtype token expected");
		/* Run a processDataUrl test */
		urlStr = "data:text/+";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First operator token value (+) is wrong, where subtype token expected");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Sentinel token found where base64 value possibily expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/a-b";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Sentinel token found where base64 value possibily expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "a-b");
		/* Run a processDataUrl test */
		urlStr = "data:text/a-b ";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Token type (SPACE) is wrong, where subtype token expected");
		/* Run a processDataUrl test */
		urlStr = "data:+";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Invalid operator (+) found, where type expected");
		/* Run a processDataUrl test */
		urlStr = "data:text/a-b;charset=iso-8859-7,%be%fe%be";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "a-b");
		/* Run a processDataUrl test */
		urlStr = "data:text/a-b+charset=iso-8859-7,%be%fe%be";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Token type (INTEGER) is wrong, where subtype token expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		/* Run a processDataUrl test */
		urlStr = "data:text/";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First token type (END) is wrong, where subtype token expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		/* Run a processDataUrl test */
		urlStr = "data:text/,";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Valid subtype not obtained from the data URL");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		/* Run a processDataUrl test */
		urlStr = "data:text/;";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Valid subtype not obtained from the data URL");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		/* Run a processDataUrl test */
		urlStr = "data:text/+";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First operator token value (+) is wrong, where subtype token expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		/* Run a processDataUrl test */
		urlStr = "data:text/ ";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Token type (SPACE) is wrong, where subtype token expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		/* Run a processDataUrl test */
		urlStr = "data:text/,";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Valid subtype not obtained from the data URL");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Unexpected end-of-string found where parameter name expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;+";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Operator token (+) found where parameter name expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain; ";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Parameter token type (SPACE) is wrong, where identifier expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Unexpected end-of-string found where parameter equals sign expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset ";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Parameter token type (SPACE) is wrong, where equals sign expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset+";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Parameter token value (+) is wrong, where equals sign expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First token type (END) is wrong, where string token expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset==";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First operator token value (=) is wrong, where string token expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset= ";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "First token type (SPACE) is wrong, where string token expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso ";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Token type (SPACE) is wrong, where string token expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Unexpected end-of-string found where parameter name expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;base64";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Sentinel token found where commma operator expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Sentinel token found where base64 value possibily expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;base64 ";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Next token type (SPACE) invalid where comma operator expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;base64+";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Next operator token value (+) invalid where comma operator expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;base64";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "Sentinel token found where commma operator expected");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;base64,";
		try {
		  imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		}
	  catch (Exception exception) {		
    	if (urlStr != null)
    		LOG.info("URL string - " + urlStr);
		  LOG.info("Exception while executing processDataUrl");
		  LOG.info(exception.getMessage(), exception);
		  HDLmEvent.eventOccurred("Exception");
	  }
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "UnsupportedEncodingException message (iso) Charset (iso)");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;base64,";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "UnsupportedEncodingException message (iso) Charset (iso)");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 1);
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;base64,";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;base64,";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "UnsupportedEncodingException message (iso) Charset (iso)");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 1);
		imageParm = imageInfo.getParameter(0);
		imageParmName = imageParm.getParameterName();
		imageParmValue = imageParm.getParameterValue();
		assertEquals(imageParmName, "charset");
		assertEquals(imageParmValue, "iso");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso;base64,";	
		try {
			imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		}
	  catch (Exception exception) {		
    	if (urlStr != null)
    		LOG.info("URL string - " + urlStr);
		  LOG.info("Exception while executing processDataUrl");
		  /* LOG.info(exception.getMessage(), exception); */
		  HDLmEvent.eventOccurred("Exception");
	  }		
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "UnsupportedEncodingException message (iso) Charset (iso)");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		imageBase64 = imageInfo.getBase64();
		assertTrue(imageBase64, "Base64 boolean value should have been true");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 1);
		imageParm = imageInfo.getParameter(0);
		imageParmName = imageParm.getParameterName();
		imageParmValue = imageParm.getParameterValue();
		assertEquals(imageParmName, "charset");
		assertEquals(imageParmValue, "iso");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso-8859-1,";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		imageBase64 = imageInfo.getBase64();
		assertFalse(imageBase64, "Base64 boolean value should have been false");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 1);
		imageParm = imageInfo.getParameter(0);
		imageParmName = imageParm.getParameterName();
		imageParmValue = imageParm.getParameterValue();
		assertEquals(imageParmName, "charset");
		assertEquals(imageParmValue, "iso-8859-1");
		/* Run a processDataUrl test */
		urlStr = "data:,A%20brief%20note";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, null);
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		imageBase64 = imageInfo.getBase64();
		assertFalse(imageBase64, "Base64 boolean value should have been false");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		/* Run a processDataUrl test */
		urlStr = "data:,A%20brief%20note";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, null);
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		imageBase64 = imageInfo.getBase64();
		assertFalse(imageBase64, "Base64 boolean value should have been false");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "A%20brief%20note");
		/* Run a processDataUrl test */
		base64ImageData = "R0lGODdhMAAwAPAAAAAAAP///ywAAAAAMAAw" + 
										  "AAAC8IyPqcvt3wCcDkiLc7C0qwyGHhSWpjQu5yqmCYsapyuvUUlvONmOZtfzgFz" +
										  "ByTB10QgxOR0TqBQejhRNzOfkVJ+5YiUqrXF5Y5lKh/DeuNcP5yLWGsEbtLiOSp" +
										  "a/TPg7JpJHxyendzWTBfX0cxOnKPjgBzi4diinWGdkF8kjdfnycQZXZeYGejmJl" +
										  "ZeGl9i2icVqaNVailT6F5iJ90m6mvuTS4OK05M0vDk0Q4XUtwvKOzrcd3iq9uis" + 
										  "F81M1OIcR7lEewwcLp7tuNNkM3uNna3F2JQFo97Vriy/Xl4/f1cf5VWzXyym7PH" +
										  "hhx4dbgYKAAA7";
		urlStr = "data:image/gif;base64," + base64ImageData;
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "image");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "gif");
		imageBase64 = imageInfo.getBase64();
		assertTrue(imageBase64, "Base64 boolean value should have been true");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, base64ImageData);
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=iso-8859-7,%be%fe%be";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		imageBase64 = imageInfo.getBase64();
		assertFalse(imageBase64, "Base64 boolean value should have been false");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 1);
		imageParm = imageInfo.getParameter(0);
		imageParmName = imageParm.getParameterName();
		imageParmValue = imageParm.getParameterValue();
		assertEquals(imageParmName, "charset");
		assertEquals(imageParmValue, "iso-8859-7");
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "%be%fe%be");
		/* Run a processDataUrl test */
		urlStr = "data:application/vnd-xxx-" + 
				     "query,select_vcount,fcol_from_fieldtable/local";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "application");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "vnd-xxx-query");
		imageBase64 = imageInfo.getBase64();
		assertFalse(imageBase64, "Base64 boolean value should have been false");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "select_vcount,fcol_from_fieldtable/local");
		/* Run a processDataUrl test */
		urlStr = "data:,A%20brief%20note";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertNull(imageType, "The image type value should be null");
		imageSubType = imageInfo.getSubType();
		assertNull(imageSubType, "The image type value should be null");
		imageBase64 = imageInfo.getBase64();
		assertFalse(imageBase64, "Base64 boolean value should have been false");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "A%20brief%20note");
		imageDataChars = imageInfo.getDataChars();
		assertEquals(imageDataChars, "A brief note");
		/* Run a processDataUrl test */
		urlStr = "data:text/a-b;charset=iso-8859-7,%be%fe%be";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "a-b");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 1);
		imageParm = imageInfo.getParameter(0);
		imageParmName = imageParm.getParameterName();
		imageParmValue = imageParm.getParameterValue();
		assertEquals(imageParmName, "charset");
		assertEquals(imageParmValue, "iso-8859-7");
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "%be%fe%be");
		imageDataChars = imageInfo.getDataChars();
		assertEquals(imageDataChars, "\u038E\u03CE\u038E");
		/* Run a processDataUrl test */
		urlStr = "data:text/vnd-example+xyz;foo=bar;base64,R0lGODdh";		
		try {
			imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		}
	  catch (Exception exception) {		
    	if (urlStr != null)
    		LOG.info("URL string - " + urlStr);
		  LOG.info("Exception while executing processDataUrl");
		  /* LOG.info(exception.getMessage(), exception); */
		  HDLmEvent.eventOccurred("Exception");
	  }		
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, "IOException message (I/O error reading header!)");
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "vnd-example+xyz");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 1);
		imageParm = imageInfo.getParameter(0);
		imageParmName = imageParm.getParameterName();
		imageParmValue = imageParm.getParameterValue();
		assertEquals(imageParmName, "foo");
		assertEquals(imageParmValue, "bar");
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "R0lGODdh");
		imageDataChars = imageInfo.getDataChars();
		assertEquals(imageDataChars, "GIF87a");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;charset=UTF-8;page=21,the%20data:1234,5678";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 2);
		imageParm = imageInfo.getParameter(0);
		imageParmName = imageParm.getParameterName();
		imageParmValue = imageParm.getParameterValue();
		assertEquals(imageParmName, "charset");
		assertEquals(imageParmValue, "UTF-8");
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "the%20data:1234,5678");
		imageDataChars = imageInfo.getDataChars();
		assertEquals(imageDataChars, "the data:1234,5678");
		/* Run a processDataUrl test */
		base64ImageData = "iVBORw0KGgoAAA" + 
				              "ANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4" + 
			                "//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU" + 
			                "5ErkJggg==";
		urlStr = "data:image/png;base64," + base64ImageData;
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "image");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "png");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, base64ImageData);
		imageDataChars = imageInfo.getDataChars();
		assertNotNull(imageDataChars, "Data characters should not be null");
		/* Run a processDataUrl test */
		base64ImageData = "iVB" +
											"ORw0KGgoAAAANSUhEUgAAABAAAAAQAQMAAAAlPW0iAAAABlBMVEU" +
											"AAAD///+l2Z/dAAAAM0lEQVR4nGP4/5/h/1+G/58ZDrAz3D/McH8" + 
											"yw83NDDeNGe4Ug9C9zwz3gVLMDA/A6P9/AFGGFyjOXZtQAAAAAEl" + 
											"FTkSuQmCC";
		urlStr = "data:image/png;base64," + base64ImageData;
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "image");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "png");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, base64ImageData);
		imageDataChars = imageInfo.getDataChars();
		assertNotNull(imageDataChars, "Data characters should not be null");
		/* Run a processDataUrl test */
		base64ImageData = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDADIiJSwlHzIsKSw4NTI7S31RS0VFS5ltc1p9tZ++u7Kf" +
											"r6zI4f/zyNT/16yv+v/9////////wfD/////////////2wBDATU4OEtCS5NRUZP/zq/O////////" +
											"////////////////////////////////////////////////////////////wAARCAAYAEADAREA" +
											"AhEBAxEB/8QAGQAAAgMBAAAAAAAAAAAAAAAAAQMAAgQF/8QAJRABAAIBBAEEAgMAAAAAAAAAAQIR" +
											"AAMSITEEEyJBgTORUWFx/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAA" +
											"AAD/2gAMAwEAAhEDEQA/AOgM52xQDrjvAV5Xv0vfKUALlTQfeBm0HThMNHXkL0Lw/swN5qgA8yT4" +
											"MCS1OEOJV8mBz9Z05yfW8iSx7p4j+jA1aD6Wj7ZMzstsfvAas4UyRHvjrAkC9KhpLMClQntlqFc2" +
											"X1gUj4viwVObKrddH9YDoHvuujAEuNV+bLwFS8XxdSr+Cq3Vf+4F5RgQl6ZR2p1eAzU/HX80YBYy" +
											"JLCuexwJCO2O1bwCRidAfWBSctswbI12GAJT3yiwFR7+MBjGK2g/WAJR3FdF84E2rK5VR0YH/9k="; 
		urlStr = "data:image/jpeg;base64," + base64ImageData;
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "image");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "jpeg");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, base64ImageData);
		assertNotNull(imageDataChars, "Data characters should not be null");
		/* Run a processDataUrl test */
		urlStr = "data:,Hello%2C%20World!";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, null);
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, null);
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "Hello%2C%20World!");
		imageDataChars = imageInfo.getDataChars();
		assertEquals(imageDataChars, "Hello, World!");
		imageValue = imageInfo.getImage();
		assertNull(imageValue, "Image value should be null");
		/* Run a processDataUrl test */
		urlStr = "data:text/plain;base64,SGVsbG8sIFdvcmxkIQ==";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "plain");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "SGVsbG8sIFdvcmxkIQ==");
		imageDataChars = imageInfo.getDataChars();
		assertNotNull(imageDataChars, "Data characters should not be null");
		assertEquals(imageDataChars, "Hello, World!");
		/* Run a processDataUrl test */
		urlStr = "data:text/html,%3Ch1%3EHello%2C%20World!%3C%2Fh1%3E";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "html");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "%3Ch1%3EHello%2C%20World!%3C%2Fh1%3E");
		imageDataChars = imageInfo.getDataChars();
		assertEquals(imageDataChars, "<h1>Hello, World!</h1>");
		/* Run a processDataUrl test */
		urlStr = "data:text/html,<script>alert('hi');</script>";
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "text");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "html");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, "<script>alert('hi');</script>");
		imageDataChars = imageInfo.getDataChars();
		assertEquals(imageDataChars, "<script>alert('hi');</script>");
		/* Run a processDataUrl test */
		base64ImageData = "R0lGODlhEAAQAMQAAORHHOVSKudfOulrSOp3WOyDZu6QdvCchPGo" +
				              "lfO0o/XBs/fNwfjZ0frl3/zy7////wAAAAAAAAAAAAAAAAAAAAAA" +
				              "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAkAABAA" +
				              "LAAAAAAQABAAAAVVICSOZGlCQAosJ6mu7fiyZeKqNKToQGDsM8hB" +
				              "ADgUXoGAiqhSvp5QAnQKGIgUhwFUYLCVDFCrKUE1lBavAViFIDlT" +
				              "ImbKC5Gm2hB0SlBCBMQiB0UjIQA7";
		urlStr = "data:image/gif;base64," + base64ImageData;
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "image");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "gif");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, base64ImageData);
		imageDataChars = imageInfo.getDataChars();
		assertNotNull(imageDataChars, "Data characters should not be null");
		imageValue = imageInfo.getImage();
		assertNotNull(imageValue, "Image value should not be null");
		/* Run a processDataUrl test */
		base64ImageData =  		
		"%89PNG%0D%0A%1A%0A%00%00%00%0DIHDR%00%00%00%96%00%00%00c%08%03" +
		"%00%00%00N%80%09z%00%00%03%00PLTE%FF%FF%FF%FE%FE%FE%FD%FD%FC%FE%FE%FF%FF%FF%FE%FF%FE%FE" +
		"%F0%EE%ED%E7%E7%E8%E6%E5%E6%E4%E4%E3%E4%E4%E4%F6%F8%F9%E9%E8%E8%E3%E2%E1%F8%FA%FD%E9%E8" +
		"%E7%E0%E0%E0%F5%F5%F7%FA%F9%F8%E1%E2%E3%F1%EF%EE%E2%E1%E0%F2%F4%F5%95%92%8B%00%00%00%21" +
		"%1F%1D%E0%E4%EAywo%2F%2B%263AL%E9%EB%ED%FD%FB%FAlg%5B%CC%D1%D7%E9%E7%E4%DB%DF%E3%FC%FC%" +
		"FB%B4%B1%A8%C0%C6%CE%E1%DF%D930.%00%02%16%D9%DD%E1875%08%05%06%03%01%01%1A%19%19%1E%1B%" +
		"1B%0C%03%00%EE%ED%EC%FE%FD%FE%F6%F5%F5%E9%EA%EA%EB%EB%EB%FB%FA%F9%EC%EA%E9vrg%0D%0B%0B%" +
		"10%0F%0F%19%16%14%19%1B%20%3CHU%EE%EF%F1%FC%FD%FE%FE%FD%FA%19%0A%00%00%01%0D%1B%13%01%2" +
		"7%24%23%2A%29%2A%5E%5E%60UUU%17%13%10%07%05%0E%0E%09%06%09%0E%16%40%3F%3E%0B%1B%25121%D" +
		"2%D7%DE%C7%C6%C2%29%28%25%276E%DE%DB%D55%2F%28%82%7Fu%8E%93%9B%21%11%00%B7%B6%B1ry%83%F" +
		"E%FF%FF%C5%CB%D2%E6%E4%E2%0D%14%1B%9C%99%91%3F%40DabcZZ%5B%B1%B7%BE%0A%256%D2%D4%D6%1C%" +
		"22%2B---DCCNNPffgV%5Dh%F5%F3%F0v~%89%E5%E6%E7%FB%FD%FDtpe%B5%BB%C4%F9%F7%F6%D3%D2%D0%A1" +
		"%9F%9Ar%7B%87%F2%F1%EDNH%3C%A9%B0%B7%EC%EC%EC%CB%C9%C5EFL%CF%CC%CB%B6%B7%B6%7D%7BqKXeA%" +
		"3C5%E5%E3%E2%B3%B4%B3%92%91%89aku%F7%F9%FA~%83%8B%CC%CE%CFFLV%B0%B2%B4%B7%B8%B8%B5%B7%B" +
		"8%B8%B8%B7c_Wplb%2A%23%1A8%3A%3A%01%01%06%9C%A0%A8%BF%BF%BDtqk%1F%1E%21%C0%C2%C3%96%95%" +
		"90%00%0E%28%A7%A8%AA%EC%EE%F0%D1%D0%CD%BC%BC%BA69%40%80%87%90%F6%F5%F2%C8%CB%D1goy%FA%F" +
		"B%FD.%23%02RWa%B8%BA%BB%23%26%29agq%E2%E4%E5%FC%FA%F7%A1%A5%AB%C5%C3%C0%23%22%22%BC%BE%" +
		"BD%C6%C6%C6%00%13%2F%A8%AB%B0%FC%FE%FF%B9%BA%B9%E3%E0%DC%14%08%00%C2%C1%C0%BA%BB%BC%15%" +
		"11%06%82%8A%95%AC%AC%AB%90%8E%86eec%B1%B4%BC%EB%ED%EE%D5%D8%DAJHG%C0%C0%BF_%5BQ%F5%F7%F" +
		"8gbY%87%8C%93%EF%F1%F2Vao%B1%B2%B0%96%9C%A5%F2%F1%F0%BD%BF%C0%DD%DE%DE%DE%DC%DC%A2%A9%B" +
		"3%5CVK%00%10%1D%A9%A8%A2%BF%C1%C8%8F%8A%82%E0%DF%DF%DC%DA%D8%A5%A5%A0Zdr%C3%C6%C8kia%DF" +
		"%DF%DF%AD%AC%A5%DA%D9%DA%DC%DC%DC%DC%DA%DA%2A%2F4UOA%AE%B0%B0QRV%C6%C9%CB%CB%CA%C8%E7%E" +
		"5%E4%C5%C4%C3%AF%AF%AD%BA%C0%C9%E0%E1%E2%DD%DC%DC%F2%F2%F2567%86%84%7C%A1%9D%95jr~%DF%D" +
		"D%DD%92%98%9E%88%87%83lkkooo%CA%CB%CD%FF%FD%FD%CF%D1%D2%DB%DB%DC%D7%D6%D6%B3%B5%B5%8C%8" +
		"F%95sss%DA%D8%D6%E6%E9%EA%D8%D7%D7%D9%D8%D9%D6%D4%D2%94%DC%CD%F8%00%00%0DqIDATx%01%EC%D" +
		"0%83%15%04Q%14%03%D0dl%BB%FF%3E%D7%B6%99%7B%F4%F9%04%11%11%11%11%11%11%11%11%F9%4046%98" +
		"8%C5%D8%843O%899%CB%98%E0n%22n%E6%26%CE1%F10%C4%94%BD%DCp%FF%96%27%B2%3A%AE%E7%07K%A1%B" +
		"5%D1%D7%DED%A3x%F1%CC%0F%A2%28%C1%89V%99f%F9%2C%AB%85b%12%BD%0C%27%9B%AA%0EV%CAfVh1%0D%" +
		"18%B9%93%DB%BD%E9%B4%5D%DF%AD%0C%B3%98%07%FB%1E%F3I%0E%0Cv%C5%40%18Mm%EB%5B%DB%D7%D6%FA" +
		"%D9%B6%5D%F3%D5%F8%EB%9D%DA%3D%97%F1%99L%88%29%7Cgzf%F6%7F%FB47%8F%05Fq%D1%B3%08b%89-%B" +
		"3%15%FC%C4%2A%3BJ%D7%1A%88%F5%8DMv%F4%2FZ%5B%E0x%BA%04Q%DAX%90%AF0%85%A9%E75M%3B%AF%1B%" +
		"CA%17%1F%8A%F8%BC%A6%EB%BAiA%84%ED%B8%82%00ok%7B%96%C9%9F%BB%ED%EC%1E%FDSk%CF%FA%A1%B5%" +
		"7FpHZ%06i%F9%E0%0F%04C%8E%28L%90%D6%F2%91e%D2%0AG%A2%B1%F8%DF%B5%12%B09%CE%06%C0%3B%13H" +
		"R%97%14%D2%B6%80%CCM%F6%85%A3%2C%0B%01%9F%B0%3Fu%238%2F7%B3%C3%F2%F0%D9i%14%18%FB%9B%16" +
		"M%F4%25%8Bfq%E3%F0s%16J%B4P%B9%B2%C8%03pQe%9F%A8%21%EA%D4%B7%0E%FE%A6%D5h%C6%C0%C1v%FC%" +
		"ADv%08%12%3A%17%D5%9B%258%00%BA%BD%B3t%3E%BEj%F5%DB%81%40%20%8D%08%06%C3%D1%ED%20%FA%A3" +
		"%3B%9Bw-%2A%DA%B8w%F6%FE%83%A3%95%DF%93%F8%10%8FN%EF%D0%CFc%ED%C9%FE%C1%3E%16%D4%D3%F2S" +
		"%0C%F0%8C%8D%9F7%5B%ADu%BC8uz%E7%FC%CBW8%90%A2%A3%D7K%7F%D1z%F3%F6%1D%5CN%C2%FB%8B%E3%0" +
		"F%10%D2%F8%C8wY%80%B5%B15a%F8%90%8B%06%AD%40%87%5Bw%C7%EA%EE%CEO%DDRw%9C%5B7%5Cz%BB%D4S" +
		"o%EA%DE%C8%25i%A9a%C1jP%D7P%1C%D2%06w%A8%CB%9C%DD%A4%FE%FCSa%F7Y%7B%CF7%DF%CC%1Cf%05%06" +
		"%05%83%7D%DF%BE%7D%FBi%AD%C6z%20%A4%5E%9B%D0%9E~0%11%C2%7C7%91%F6%D3%FF%DD%CC%84o%D9%CA" +
		"%DEf%07%21%7F%F0%D66%D8%0E%FF%A0%A1%26O%DF%D1g%A7%03%EC%12%EE%DE%E3%02%7B%10%AB%DB%16%C" +
		"6%A69~%11f%EE%DDg%8B%F6%81%FD%07%98%83D%EF%B77%1C%12ME%CB%1C%5EpDpt%0D%8C%B6E%2Cf%1F%3E" +
		"%85V%B3%87c%3Dl%0C8k%1E%3FA%04%FA%14%EB%88h1%99%7D%F2T%B8%E8%B4h%2B%8C%86%BEvg%E0%EC9%B" +
		"3%13Nz%3F%BE%F4%AF%F3%DB%A0%03%87u%D2%B9%2Fb%5D%107%96pX%CBER%0B2%15%AF%86%C9%E6N%A4y%F" +
		"A%2FB%8A%B2%FE%AE%16%E3%07%23%E0%88%BC%3EY%7D%12%BAJ%10K%81X%E8%00%D6%03-uj%85%E2%C1T%A" +
		"A%16%B3%98%5C%BC4%60%B3%E2%B4%10%B1%BA%00%1B%03%2C~%E9%3D%FA%3E%3A%2C%07%17V%AD%C8%C6%2" +
		"3%B4X%5B.%9B%E2%FA.%C1L%D9%DC%2B.%ACZ%8A%3Fa%1D%92NE%AC%ABXo%1E%8DG6%B1%83Y%17%AF%5DG%9" +
		"E%25%F3%DD%DC%A2%3AC4%3F%06_~q%8A%A7%85%BE%D9l%1F%AC%A50i%2C%09%8C%3B%CB%28%83%82%E2%F1" +
		"%B6ynQn%0B%A0i%ACI%82%FE%F7Wc%83K%84%A5%2CV%FB%B8Qc%A9Z%B2%C6%E3%96j%D5%DAK%B1F%CDOR%EC" +
		"su%A5X%11%CA%E4%3F%24%D1%CC%2A%18_q%95%B6%B7A%CB%B7%01%E7-%09%DCH%E0yc%F7%01X%88w5%BC%B" +
		"9Wp%3Eq%A7%04%5B%DC-s%2Fr%3B%E8NJ%AA%10%BD5%02%EE%92%FA%5C%F7i%F6%C3%2B%8F%DE%BBo%8B%2B" +
		"%A0X%8E%CC%83a%3B%2F%C1%C3V%8F%80UK%C0%7B%9C%22%40%D9%DB%91%277%C3%2F%3F%851%5D%FF%3Bfe" +
		"%F3%07%AC%5EZ%2C%03%01%D6%B5%0Fb%CDf%F6%21%D63%CC%8B~%F0%828%B8%B0.%86%18%FA%3E%17%9C%7" +
		"Fqf%22%C5%B2F%AC-%7BU%A9%91%22%0E%0B%3B%D3%D6%F9%DB%F7%B7%EC%D1M%8F%A7%C3%DA%BAt%B8%0EK" +
		"%9A%C6a%5D%B7%EBL%B1%E4%CD%1F%BF%3C%D7%AA16%88%C7%BE%97%95%0F%FE%0F%96%B9%16%0B%8F%CFo%" +
		"A5j%F96%A2j%85%92Az%27%C8%23%3C%BCE%3Cn%22%D66%16k%97Ywr%DB%F7Nz%86P%A1%C5B%9A%5EC0%9F%" +
		"D8%A8%F4%B5X%89%99%12%1D%96%82%C3%B2%7C%044%89%9E%84%98%A5f%01P%B5%B2%19-%96%F5%9F%B0Z%" +
		"A5%5Eg%B1x%A8V%F3%EBn%A8%96h%1F%C52%C5%D1%3F%88%3C%C8q%85%23%FC%15%5Br%05%CB%13%E7%0C%C" +
		"7%92M%CA%5BLn%2B%9E%1B%E5%AB%85%89%2CV%1B%1C%9E%1E%BBGN%81%85%E6%5C%A1%EB%11%8F%7BK%BE%" +
		"25Q%F8%8Ab%BD6i%E4%BA%0C%9F%0E%D1%A4%CAD%01%B0%20%A7%25%09%CCS%07%AD%A4X%B9%05%85%7FRK5" +
		"%17%9C%11%8B%3Dk%A3%DF%F8%F5r%AB4%FC%DE39%E1%82%CE%8Cg%29%2A%C1%F2%16%FF%9B%88%0E-B%F1%" +
		"EA%A7Zy%93%FCT%1F%5C%CEZ%C2%C5%E0%160%0C9x%1CV%FC%12%E7%CE.%EC%E9r%F3%F5C%86%A2Z%26jE1%" +
		"F4%E3f%C4%25%E8%C4%0EjK_%CE%5B%AA%3F%7B%AB%C4n%19%AC%F22%F2f%F7%5B~%AF%EB%23%D6%19%F0%D" +
		"7%E4%F3%8D%F1%CF%BA%12d%2CE%AC%F3%DB%96%8E%40%AC%C3%B0%8B%F4%2C%A3Xe%3Ex%A5%BC%07%7B%97" +
		"%97u%3C4%81%D7%C8%C1%25%B1%28%CE%B93UK%D0%CD%9A%C3%CA%97%09%2B%C0%0E2%2B%2B%AB%2A%9B%40" +
		"%3B%B3BT%D2R%C4%26%B1%FA%B1%0D%E1%F6%22z%3Fyk.%2C%C5%27%9E%116z%FF%B3%5C%95%06%F6%3B%E1" +
		"%5Bt%B5%83%D2%06%26%98D%ACD%07N%AD%C7%1Ao%7D~%81%0F.%FF%87%DBl%11%8Bh%938%A7hi%07%F6%B4" +
		"%C6%EC%90V%AD%CB%C50%82z%8B%1C%CDB%BD%E8L%EC%C5%B0X--O%10%F2%BB%B7%0AV%BA%C6%21%96%FFKU" +
		"z%BA%26%BF%E2X%CF%EEm%D1%5BgpY%B8%B4%00%E8%83%D7Bj%17%CB%CF%FB%E5t%C0%93%0D%CB%B0%27d%A" +
		"4%9F%20%EBZ%F5%C6%0A%D8%C0%DE%15u%05u%B4%87%B5%B7%0B9%B5v%E7%8C%1C%B9%03v%F1%EBTo%D4i%0" +
		"E%88u%CC0OY%81n8~%CE%98%9C%DF%3D%0Fg%A2y%83%81O%94i%14%2B%5BY%ABJ%2F%28H%D7%F4%18%F8%D7" +
		"%0Fj%A5%E7I%AF%EB%D6m%8F%F5%D4%86%AC%87y%9C%E5y%A4%04%3Ac%91%B9%BF%DD%2B%E7%89%7D%B7%22" +
		"G%98b1%E9%99%AA%A2%0B%7C%04%CEhyz%D7%EA%ACL%5B%9CDIA%B1%DC%EE.2%9C%11%BD%00%5D%8C%EB%BC" +
		"%E8oLJ%09%BE%7D%0F%B8%5B%D4%88%B1%E3%01%CCd%9E%1B%8Bag%97%AE%A0%0D%F7%A0%E7%F2%1F%B0R%F" +
		"2%84%D7%E9FJ%22%91%9CYv%9F%C3%8A%A3%7D%0B%03%B1v%EC%B4%07%F7%D3%CF%E5%3C%F5%16%DA%BAq%2" +
		"6%A2%B74%DED%40%AE%CFY%86X%FA%84%60%D6%5C%26%F6%E9%07%EFDu%2CVO1sY%F8%02l%EDl%25%7B%EC%" +
		"EC%ED%CE8g%22%965%9A%B4K%E6%F6%10%0B%03%B5%22%91N1a.b%F5%EF%D7%D7%DE%B6_%1F%7B%C9%088%A" +
		"E%A8%FE%01%CB%2C%3DO%F9%080%094%5CX%B5%26%A0%83%FCM%B1oQ%B5%3A%D0%85%84S%2C%05m8%9E8%13" +
		"%EB%17%A8%28%D6%23%BC%C6%F5%AD%D5%F7%5C%BA%A2%EF%92%18%AE%C4%EA%0B%C3O%23%D6%B7hr%1F%B1" +
		"L%E6%E2Q%FF%26%21%165%91%BE%F1h%8C%B0%CB%B9Fb%E0%A2%2F%AB%16S-%F8%8E%D5%D0l%FD%CD%E0%0D" +
		"q%90Y%F5%FE%FD%FB%A8%7F%3B%21%D6%C6yn%CE%AB%2C%D8%2F%CC%05%D7Q%CB%C0%5D%9A%2B%1F%2CzUQU" +
		"9%AA%1CgbsK3%8A5%D7u%FE%99h%2C%3E%3D%DCD%9Dq%1D%D5%01%DE%29%5Bsj%A9%A5o%83%A2%60%5E%CE%" +
		"FC%F7%95%AE%0B%DE%BF%9F%7F%EAob%99%9E%E6V%E9%EA%EA%F0%CC%C2%40%26m%BC%21%00%C2%F6Vk%26T" +
		"V%8D%CA%99%1F5%05%F0%A6%CAw%D2Z%81%3E%F9%1E%1Fn2wV%C2%CC%13%06%BC6%EB7%C7%E0wf%07%5D%F6" +
		"%7D%3E%88B%A3ZI%EAFP%AA%B2Bo%BD%1DHz%D5%A92%3E%12ml%0C%BA%1C%C4%C9%E3%91%B5%28B%F9%02%E" +
		"E%9Awg%3B%90S%60A%24%D3%18%A61%B1%C41%E8%F9%A0%9E%91%E1%85d%DD%A7fd%B6%E2%29%84X%90%C0%" +
		"02%B5b%2A%5C%E0%97%A5k%E4%CBE%97%EB%1EO%C5%C2l%23X%5EpN%FEc-%3E%F1%3D%AD%7C%8A%E3%8A%10" +
		"%FD%BC%CD%07%11%2B%DFW%EA%9B%3B%08%F5%18DV%C2%3B%F5%3E%EC%5B%885%81I%26%8E%19%AA%82%8F%" +
		"BA_%B2%0E1R%1D%D6%BDI%11%CAm%10a%AD%C3%CAP%8B%FC%60%9A%22%964%9C%91%2Bh%23fl%88%E3%E7%8" +
		"3%24_%F4%60%FA%F1P%C4%92I%8B%11%AB%A0%E0%1C%E6%FBr%BAu1b%F1%08b%E9%FD%88%F5%21%28%FC%F4" +
		"J%28%3DA%F4%9C%F2%DER%AC%C0W%A7%99%5C%B9V%AD%AB%B2%60x%A6i%20%E7%097%27%93u%05T-%AD%D4y" +
		"%D9%97%7D1kl%12%F7G%88%E3%F1K%DDYd%27~J%A4b7%24Q%B5p%9A6%17%86%DB%90%5E%9Fb%C8l%E6%29%3" +
		"C%0B%25%7CU%24%EE%88%2F%18%A7%A4%F0%C9r%D1i%ADZdy%D9%2Fj1%B2%C53%9C%93%E4D%60%20%3E%40%" +
		"93%D8%AB%3A%EF%DA%5E%3C%C5k%0F%0E%BFcVFM%ABK%19%C4%FB%B2%A9%1E%BE%5Cc%E4H%B4%F1d%93%F2%" +
		"80V%ADm%A7.%F8V%CC%8FF%25Y%B5%AC%3E%AB%95%15%1Bn%09%EB%88%E1%96M%82%E6%13%A46%E46%3F%99" +
		"%B4%12%BEr%F5%B4%20%D6%9F%0F%AD%D8w8%C2%EA%F3g%2BA%FD%C8%BD%5E%BD%1A-iI%D5%FAd5%E8G%AC%" +
		"86%2Fq%EB%F2%95%0F3%E0l%24%0A%E2%F8l%9BM%22I5M%DA%02%82%EA%17H%92%24%C0%E5kT%14P%E0%A4%" +
		"E4%40%A2AJTr%07%1C%E5%DAR%8Ep%9C%1E-%8E%A2uBE%24%40%8E%B3%B6K%DCU%A5%81%C0%5D%DF%864%DB" +
		"%9By%2Fiw%B7%AB%03Z%B3o%F6%F7%FE%F3%9F%89%A4I%F2%C4%8A%BD%0B%5Ex%B9%F9K%60%3D%8E%BE7%2F" +
		"%C5%87%20%85%7Br%00fW%9E%CFF%AFV%1Fa%11%23%C2%D5%FAWfJ%B08%DA%EA%26%204%0Cg%E4M%D6%00%8" +
		"AxwL%9E%DDT%1B%A0W%EF%95%A1%16%CE%C6%16%DA%9F%E1%B0%F7%40XZ%D8%8A%85%D5%0A%BB%1C%EB%CF%" +
		"EE%01%7F%8F%7B%03%BD5%CF%B1NJ%84%85%8BJ%92%24%7C1%E6M%3A%DF%8A%26%86~%D4N%CE%0E%F8%FD%2" +
		"4J%94%EF%94n%B1%B4%D5y%A0%93%19W%19%B1%E8%B1%F8p%F2%01%B1n%D8%12%E8%BB%F7A%A5%E3%CB%C6V" +
		"X%05%0E%9B%1C%AB%E3%3B%B5%60%B1%08%A4o%7Fg%F1%EFbU%60E%B9x%84%F5%BE~%7B%89%17%99%AA%15E" +
		"%87%98%B0%26%E7S%AC%8F%F5R5%82%D9%88h%E2%0A%AA%F5%AD%FA%A4%96%EFz%8A%15%1C%D3%26%2F%AB%" +
		"AB%F0%D8%C3O%1C%C6%D5%AA%C0%CEL-%0BVT%7F%80%28z%C0%05%F2N%FD%8A%17X%3E%DB%13X%87%9F%F6%" +
		"F7r%90%0A%E0%A2%8A%0F%D6a%E0%1BX%D4%1A%85%F9%E3%F9%8D%DE%DF%FBU%88%7B%DF%F0q%90%0B%9E%3" +
		"BV%3C%D9Rs%907%DA%DBrP_%E2%8F%1Dk%8FG%B8%C9%A3%DE%06%8Ck%2C%E9%F1%24OcC%C3%0F%EFj%11%C4" +
		"Jy%92%F6%26" +
		"%A2%82_h%23%2C%B2U%5E%00%2F%B9-%D4%3A%0F%83%08%B9l%2C%01%86%DD%95%B2X%EB%0D%EBw%9F5%D6U" +
		"%BAL4Q~j%A2%D6%1E%F6E%5D%B7%2AL%CA%9B%98V%9D%BEd%10%16%2A%C8%B1%D4%29V%07%FF%A5%9C%DE%0" +
		"B%E3E%D0W%E0BKX%0F%93%D5.fM%E4z%E0c%22%83K%BD%DBV%B4%04%152au%14%9A%1D%C9tr%0E%9B%88%F3" +
		"MXx%DCZ%1C-%81~%93%10%EB%C6%E0XH%D9%7FVK%9A%AA%A5%DA%B1%B0%FD%11%81uToXs7%EC%EE%BA%3DL%" +
		"90%2B3%F2%82%2A%B0%8EY%5B%C1.%D0%28%09%93%BA%60.Hj1%27%B5%C8%12%F9%89B%A3%E7%F6%D2%ABeH" +
		"-%26%04%D6%B8%94%A3%3B%B5%E2%F3d%09%3B%D61.%23%20%AC%AF%FB%94%0B%B5%2A%CF%BF%9Ay%02A%3D" +
		"%27%0A%15r~A%1F%F0%1Am%81%85%27%C5t%A4%3D%15X%D3i%D0%D6%5Bq%B0%C7%B4%89%2F%90%A3%1A%9D%" +
		"08i%CA6%BC%12%B12%B6%02e%D7%13f%F0%96%91%00%5B%A4%B0%89%2F_CM%8CA%FEg%FF%14L%81%96%C0%2" +
		"6%86%B1%A0%03%96%C1%B1%C8%A9%98w%0E%17b1%C2j%A9%96%F5%D1%A2%ED%8As0%E3%10%85lX2a%A9%88%" +
		"25%7C%F4B%AD%BE%23VZ%BF%E0j%CD.%E9%1C%E8%10%BF0%A9%19k%19%C5%93%C1%14%CE%85%B2%10%10jY%" +
		"F6%16%22%B5%FC%B8%99%92%19%27%AC%B7%DE%01%B5%3D%90x%B5%89R%01%1DB%26%BD%B2%7B%D6%12%B2c" +
		"%21%94%D5%8B%83%86%08%85%8Ce%12q%9E%FE%B7S%07%18%00%031%10%00%BB%F4%FE%FF%E5%22%0A%A4%1" +
		"5%400%83%83%5B%12%AC%ACSG1%F9%FC%AE%F7%DFici%82%D3%21o%25%96%A9J%9C%8B%99%3B%8B%97%03%0" +
		"0%00%80%07sSV%04D%28w0%00%00%00%00IEND%AEB%60%82";	
		urlStr = "data:image/png," + base64ImageData;
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "image");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "png");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, base64ImageData);
		imageDataChars = imageInfo.getDataChars();
		assertNotNull(imageDataChars, "Data characters should not be null");
		imageValue = imageInfo.getImage();
		assertNotNull(imageValue, "Image value should not be null");
		/* Run a processDataUrl test */
		base64ImageData = "R0lGODlhCwAOAMQfAP////7+/vj4+Hh4eHd3d/v7+/Dw8HV1dfLy8ubm5vX19e3t7fr6+nl5" +
			              	"edra2nZ2dnx8fMHBwYODg/b29np6eujo6JGRkeHh4eTk5LCwsN3d3dfX13Jycp2dnevr6///" +
				              "/yH5BAEAAB8ALAAAAAALAA4AAAVq4NFw1DNAX/o9imAsBtKpxKRd1+YEWUoIiUoiEWEAApID" +
				              "MLGoRCyWiKThenkwDgeGMiggDLEXQkDoThCKNLpQDgjeAsY7MHgECgx8YR8oHwNHfwADBACG" +
				              "h4EDA4iGAYAEBAcQIg0DkgcEIQA7"; 
		urlStr = "data:image/gif;base64," + base64ImageData;
		imageInfo = HDLmImageInformation.processDataUrl(urlStr);
		assertNotNull(imageInfo, "HDLmImageInformation.processDataUrl should have returned a non-null value");
		errorMessage = imageInfo.getErrorMessage();
		assertEquals(errorMessage, null);
		imageType = imageInfo.getType();
		assertEquals(imageType, "image");
		imageSubType = imageInfo.getSubType();
		assertEquals(imageSubType, "gif");
		parametersCount = imageInfo.getParametersCount();
		assertEquals(parametersCount, 0);
		imageDataRaw = imageInfo.getDataRaw();
		assertEquals(imageDataRaw, base64ImageData);
		imageDataChars = imageInfo.getDataChars();
		assertNotNull(imageDataChars, "Data characters should not be null");
		imageValue = imageInfo.getImage();
		assertNotNull(imageValue, "Image value should not be null");		
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmImageInformation.processDataUrl(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "URL string passed to processDataUrl is null");
		}
	}	
}
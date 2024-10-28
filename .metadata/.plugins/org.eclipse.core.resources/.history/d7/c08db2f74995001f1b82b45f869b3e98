package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Function;

import javax.imageio.ImageIO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.eclipse.jetty.util.UrlEncoded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eatthepath.imagehash.PHashImageHash;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
/**
 * HDLmUtility short summary.
 *
 * HDLmUtility description.
 *
 * @version 1.0
 * @author Peter
 */
/* The HDLmUtility class is not used to create any objects.
   However, this class does contain code that is used for
   various utility purposes. */
public class HDLmUtility {
	/* The next statement initializes logging to some degree. Note that
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmUtility.class);
	/* This class can never be instantiated */
	private HDLmUtility() {}
  /* Build a bridge rest API query from values passed by
     the caller. The returned value is the query string. */
	protected static String buildBridgeRestQuery(String colName, HDLmEditorTypes editorType) {
		if (colName == null) {
			String   errorText = "Column name reference passed to buildBridgeRestQuery is null";
			throw new NullPointerException(errorText);
		}
		if (editorType == null) {
			String   errorText = "Editor type value passed to buildBridgeRestQuery is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value passed to buildBridgeRestQuery is invalid");
		}
		/* Get the current content string. This will be something like
		   'pass_javaa'. The content string is derived from the current
		   editor type and the current suffix character. */
		String  valueModified = HDLmUtility.getContentString(editorType);
	  StringBuilder  queryStr = new StringBuilder();
	  queryStr.append("q=[[[");
	  queryStr.append("'");
	  queryStr.append(colName);
	  queryStr.append("'");
	  queryStr.append(",'eq',");
	  queryStr.append("'");
	  queryStr.append(valueModified);
	  queryStr.append("'");
	  queryStr.append(",");
	  queryStr.append("'");
	  queryStr.append(valueModified);
	  queryStr.append("'");
	  queryStr.append("]]]");
	  return queryStr.toString();
	}
  /* Build a Dreamtsoft rest API query from values passed by
     the caller. The returned value is the query string. */
	protected static String buildDreamRestQuery(String colName, HDLmEditorTypes editorType) {
		if (colName == null) {
			String   errorText = "Column name reference passed to buildDreamRestQuery is null";
			throw new NullPointerException(errorText);
		}
		if (editorType == null) {
			String   errorText = "Editor type value passed to buildDreamRestQuery is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value to buildDreamRestQuery is invalid");
		}
		/* Get the current content string. This will be something like
		   'pass_javaa'. The content string is derived from the current
		   editor type and the current suffix character. */
		String  valueModified = HDLmUtility.getContentString(editorType);
	  StringBuilder  queryStr = new StringBuilder();
	  queryStr.append("q=[[[");
	  queryStr.append("'");
	  queryStr.append(colName);
	  queryStr.append("'");
	  queryStr.append(",'eq',");
	  queryStr.append("'");
	  queryStr.append(valueModified);
	  queryStr.append("'");
	  queryStr.append(",");
	  queryStr.append("'");
	  queryStr.append(valueModified);
	  queryStr.append("'");
	  queryStr.append("]]]");
	  return queryStr.toString();
	}
	/* Build a result object with failure values is each field.
	   Return the object to the caller. */
	protected static HDLmResult  buildResultFailure(final int failureCode) {
		/* Create a new HDLm result object */
		HDLmResult  rv = new HDLmResult();
	  if (rv == null) {
	  	String  errorText = "HDLm result object not allocated with new";
	    HDLmAssertAction(false, errorText);
	  }
	  /* Set a few fields in the HDLm result object */
	  rv.setDesired((Boolean) false);
	  rv.setCode((Integer) failureCode);
	  rv.setMessage("Failure");
		return rv;
	}
	/* Build a result object with failure values is each field.
	   Return the object to the caller. */
	protected static HDLmResult  buildResultFailure(final String errorMessage,
			                                            final int failureCode) {
		/* Check a few passed values */
		if (errorMessage == null) {
		  String   errorText = "Error message reference passed to buildResultFailure is null";
		  throw new NullPointerException(errorText);
		}
		/* Create a new HDLm result object */
		HDLmResult  rv = new HDLmResult();
	  if (rv == null) {
	 	  String  errorText = "HDLm result object not allocated with new";
	    HDLmAssertAction(false, errorText);
	  }
	  /* Set a few fields in the HDLm result object */
	  rv.setDesired((Boolean) false);
	  rv.setCode((Integer) failureCode);
	  rv.setMessage(errorMessage);
		return rv;
	}
	/* Build a result JSON string with failure values is each field.
	   Return the JSON string to the caller. */
	protected static String  buildResultFailureJsonString(final String errorMessage,
			                                                  final int failureCode) {
		/* Check a few passed values */
		if (errorMessage == null) {
		  String   errorText = "Error message reference passed to buildResultFailureJsonString is null";
		  throw new NullPointerException(errorText);
		}
		String  rv = null;
		/* Create a new HDLm result object with failure values
		   in each field */
		HDLmResult  failureResult = HDLmUtility.buildResultFailure(errorMessage, failureCode);
	  if (failureResult == null) {
	  	String  errorText = "HDLm failure result object not obtained";
	   HDLmAssertAction(false, errorText);
	  }
	  /* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
	  String   failureString = gsonInstance.toJson(failureResult);
	  /* Return the JSON string to the caller */
		return failureString;
	}
	/* Build a result JSON string with failure values is each field.
	   Return the JSON string to the caller. */
	protected static String  buildResultFailureJsonString(final int failureCode) {
		String  rv = null;
		/* Create a new HDLm result object with failure values
		   in each field */
		HDLmResult  failureResult = HDLmUtility.buildResultFailure(failureCode);
	 if (failureResult == null) {
	 	String  errorText = "HDLm failure result object not obtained";
	  HDLmAssertAction(false, errorText);
	 }
	 /* Convert the object to a JSON string */
	 Gson     gsonInstance = HDLmMain.gsonMain;
	 String   failureString = gsonInstance.toJson(failureResult);
	 /* Return the JSON string to the caller */
		return failureString;
	}
	/* Build a result object with success values is each field.
     Return the object to the caller. */
	protected static HDLmResult  buildResultSuccess() {
		/* Create a new HDLm result object */
		HDLmResult  rv = new HDLmResult();
	  if (rv == null) {
	  	String  errorText = "HDLm result object not allocated with new";
	    HDLmAssertAction(false, errorText);
	  }
	  /* Set a few fields in the HDLm result object */
	  rv.setDesired((Boolean) true);
	  rv.setCode((Integer) 0);
	  rv.setMessage("Success");
		return rv;
	}
	/* Build a result JSON string with success values is each field.
	   Return the JSON string to the caller. */
	protected static String  buildResultSuccessJsonString(final Integer successNumber) {
		String  rv = null;
		/* Create a new HDLm result object with success values
		   in each field */
		HDLmResult  successResult = HDLmUtility.buildResultSuccess();
	  if (successResult == null) {
	   	String  errorText = "HDLm success result object not obtained";
	    HDLmAssertAction(false, errorText);
	  }
	  /* Store the number or a null value in the result object */
	  successResult.setNumber(successNumber);
	  /* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
	  String   successString = gsonInstance.toJson(successResult);
	  /* Return the JSON string to the caller */
		return successString;
	}
	/* Build a two dimensional Java Array from a map (hash or tree)
	   passed by the caller. The 2D Java array is returned to the
	   caller. Note that the number of columns is always two (2).
	   The number of rows depends on the size of the map passed
	   by the caller. */
	protected static Object[][] buildTable(TreeMap<String, Object> inData) {
		if (inData == null) {
		  String   errorText = "Input data reference passed to buildTable is null";
		  throw new NullPointerException(errorText);
		}
		/* Get the number of rows of data passed by the caller */
		int         numRows = inData.size();
		int         rowCounter = 0;
    Object[][]  outArray = new Object[numRows][2];
    /* Populate the output array with the data passed by the caller */
    for (var dataEntry : inData.entrySet()) {
	   	outArray[rowCounter][0] = dataEntry.getKey();
	   	outArray[rowCounter][1] = dataEntry.getValue().toString();
	   	rowCounter++;
	  }
    return outArray;
	}
  /* Do all of the work needed to identify the current browser.
		 Return a boolean showing if the current browser can handle
		 our code. Note that some programs (why is not clear) do not
		 send a HTTP_USER_AGENT header. We always treat these as bad
		 browsers. */
	protected static boolean checkBrowser(HttpServletRequest request,
			                                  String timeStamp) {
		boolean browserOk = false;
		/* Check if the input request passed by the caller is null */
		if (request == null) {
			String  errorText = "Servlet request passed to checkBrowser is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the timestamp string reference passed by the caller is null */
		if (timeStamp == null) {
			String  errorText = "Timestamp string reference passed to checkBrowser is null";
			throw new NullPointerException(errorText);
		}
		/* Get the User-Agent header, if possible */
		String userAgent = HDLmJetty.getUserAgentHeader(request);
		if (userAgent == null) {
			/* Build a new browser status instance solely for building JSON */
			HDLmBrowserStatus   browserStatus = new HDLmBrowserStatus(timeStamp,
					                                                      "reject",
					                                                      "Not set",
					                                                      "Not set");
			/* Convert the browser status instance to JSON */
			String  browserStatusJson = browserStatus.buildJson();
			HDLmUtility.filePutAppendLineLogs(browserStatusJson, null);
			return browserOk;
		}
		/* Get some information about the current browser */
		HDLmClientInfo clientInfo = HDLmJetty.getClientInformationOld(userAgent);
		/* Get the details about the current browser */
		HDLmBrowserTypes  browserType = clientInfo.getBrowserType();
		int               browserVersion = clientInfo.getBrowserVersion();
		/* Check if user is running IE. We don't support IE. */
		if (browserType == HDLmBrowserTypes.IE) {
			String  browserTypeString = browserType.toString();
			String  browserVersionString = String.format("%.1f", (float) browserVersion);
			/* Build a new browser status instance solely for building JSON */
			HDLmBrowserStatus   browserStatus = new HDLmBrowserStatus(timeStamp,
					                                                      "reject",
					                                                      browserTypeString,
					                                                      browserVersionString);
			/* Convert the browser status instance to JSON */
			String  browserStatusJson = browserStatus.buildJson();
			HDLmUtility.filePutAppendLineLogs(browserStatusJson, null);
			return browserOk;
		}
		browserOk = true;
		return browserOk;
	}
	/* The next routine takes an input URL string and checks if it
	   has a valid host name after a valid protocol string (such as
	   HTTPS://). A boolean value is returned to the caller showing
	   if a valid protocol string was found or not. */
  protected static boolean checkForHostNameAfterProtocol(String urlStr) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to checkForHostNameAfterProtocol is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few local variables */
		boolean   rv = false;
		int       urlLength = urlStr.length();
		/* Check if the URL string is long enough for a one character
		   protocol, followed by a colon, followed by two forward
		   slashes */
		if (urlLength < 4)
			return rv;
		/* Check if we can find a protocol in the URL string */
		int  protocolIndex = urlStr.indexOf("://", 1);
		if (protocolIndex < 0)
			return rv;
		/* Look for a forward slash after after the protocol */
		int  slashIndex = urlStr.indexOf('/', protocolIndex + 3);
		if (slashIndex < 0)
			return rv;
		/* Make sure that the host name has at least one character */
		if ((slashIndex - protocolIndex - 4) < 1)
			return rv;
		rv = true;
		return rv;
	}
	/* The next routine takes an input URL string and checks if it
	   starts with a valid protocol string (such as HTTPS://)). A
	   boolean value is returned to the caller showing if a valid
	   protocol string was found or not. */
	protected static boolean checkForProtocol(String urlStr) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to checkForProtocol is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few local variables */
		boolean   rv = false;
		int       urlLength = urlStr.length();
		/* Check if the URL string is long enough for a one character
		   protocol, followed by a colon, followed by two forward
		   slashes */
		if (urlLength < 4)
			return rv;
		/* Check if we can find a protocol in the URL string */
		if (urlStr.indexOf("://", 1) < 0)
			return rv;
		rv = true;
		return rv;
	}
  /* The next routine takes an input URL string and checks if it
     is valid or not. A boolean value is returned to the caller
     showing if the URL string is valid or not. A URL string is
     considered to be valid if an URL object can be created from it. */
	protected static boolean checkUrl(String urlStr) {
	 /* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to checkUrl is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few local variables */
		boolean   rv = true;
		/* Build a URL object from the input string */
		URL urlObj = null;
		/* Try to create a new URL object from a URL string */
		try {
			urlObj = new URL(urlStr);
		}
		catch (MalformedURLException malformedException) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("MalformedURLException while executing checkUrl");
			LOG.info(malformedException.getMessage(), malformedException);
			HDLmEvent.eventOccurred("MalformedURLException");
			rv = false;
		}
		catch (Exception exception) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("Exception while executing checkUrl");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			rv = false;
		}
		return rv;
	}
  /* This routine processes a list of lines. Each line is added to
     the output line. Of course, the suffix (if any) is added to
     each input line. The suffix value can be passed as a null
     value in which case, no suffix will be used. */
	protected static String combineLinesSuffix(ArrayList<String> inLines, String suffix) {
		if (inLines == null) {
		  String  errorText = "Input lines array passed to combineLinesSuffix is null";
		  throw new NullPointerException(errorText);
		}
		if (suffix == null) {
		  String  errorText = "Suffix string passed to combineLinesSuffix is null";
		  throw new NullPointerException(errorText);
		}
		StringBuilder  outBuilder = new StringBuilder();
		/* Process each input line */
	  for (String inLine : inLines) {
	  	outBuilder.append(inLine);
	  	if (suffix != null)
	  		outBuilder.append(suffix);
	  }
		return outBuilder.toString();
	}
  /* Convert a string to a Double. This method will return a
     null value is any errors are detected in the conversion.
     The string passed by the caller must not be null. */
	protected static Double convertDouble(String inStr) {
		Double  rv = null;
		if (inStr == null) {
		  String  errorText = "String passed to double conversion is null";
		  throw new NullPointerException(errorText);
		}
		try {
			rv = Double.parseDouble(inStr);
		}
		catch (NumberFormatException numberException) {
			if (inStr != null)
			  LOG.info("Input string - " + inStr);
			LOG.info("NumberFormatException while executing convertDouble");
			LOG.info(numberException.getMessage(), numberException);
			HDLmEvent.eventOccurred("NumberFormatException");
			rv = null;
		}
		catch (Exception exception) {
			if (inStr != null)
			  LOG.info("Input string - " + inStr);
			LOG.info("Exception while executing convertDouble");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			rv = null;
		}
		return rv;
	}
  /* Convert a string to an integer. This method will return a
     null value is any errors are detected in the conversion.
     The string passed by the caller must not be null. */
	protected static Integer convertInteger(String inStr) {
		Integer  rv = null;
		if (inStr == null) {
		  String  errorText = "String passed to integer conversion is null";
		  throw new NullPointerException(errorText);
		}
		try {
			rv = Integer.parseInt(inStr);
		}
		catch (NumberFormatException numberException) {
			if (inStr != null)
			  LOG.info("Input string - " + inStr);
			LOG.info("NumberFormatException while executing convertInteger");
			LOG.info(numberException.getMessage(), numberException);
			HDLmEvent.eventOccurred("NumberFormatException");
			rv = null;
		}
		catch (Exception exception) {
			if (inStr != null)
			  LOG.info("Input string - " + inStr);
			LOG.info("Exception while executing convertInteger");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			rv = null;
		}
		return rv;
	}
	/* This routine breaks a string into an ArrayList of substrings.
	   The ArrayList is returned to the caller. The caller passes
	   the original string and the desired length of each substring.
	   The last substring may be a short string (if need be). If the
	   string passed by the caller is empty (zero-length), the ArrayList
	   will have zero elements. */
	protected static ArrayList<String>  createArrayListOfStrings(String inStr, int desiredLength) {
		/* Check the values passed by the caller */
		if (inStr == null) {
		  String  errorText = "String passed to createArrayListOfStrings is null";
		  throw new NullPointerException(errorText);
		}
		/* Check the desired length passed by the caller */
		if (desiredLength <= 0) {
			String  errorText = "Desired length passed to createArrayListOfStrings is less than or equal to zero";
			throw new IllegalArgumentException(errorText);
	  }
		/* Get the length of the string passed by the caller */
		int   inStrLen = inStr.length();
		/* Allocate the ArrayList that is returned to the caller */
		ArrayList<String>   localArray = new ArrayList<String>();
		if (localArray == null) {
			String   errorText = "ArrayList<String> not allocated in createArrayListOfStrings";
			HDLmAssertAction(false, errorText);
    }
		/* Build the ArrayList that is returned to the caller from each
		   substring */
		int   strOffset = 0;
		while (strOffset < inStrLen) {
			int   remainingLen = inStrLen - strOffset;
			if (remainingLen > desiredLength)
				remainingLen = desiredLength;
			String  tempStr = inStr.substring(strOffset, strOffset + remainingLen);
			localArray.add(tempStr);
			strOffset += desiredLength;
		}
		return localArray;
	}
	/* This routine executes a command and returns the output to the
	   caller. This routine may fail with an exception. Hopefully,
	   this routine will not fail. However, it might fail anyway.
	   In a normal case, this routine will return a Java string. */
	protected static String  execReadToString(final String execCommand) throws IOException {
	  try (Scanner s = new Scanner(Runtime.getRuntime().exec(execCommand).getInputStream()).useDelimiter("\\A")) {
	    return s.hasNext() ? s.next() : "";
	  }
	}
  /* This routine clears the contents of a file. The file may
     or may not be empty when this routine is caller. The file
     will be empty after this routine is called. */
	protected static void fileClearContents(String fileName) {
		if (fileName == null) {
		  String  errorText = "File name string passed to fileClearContents is null";
		  throw new NullPointerException(errorText);
		}
	  BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fileName, false));
		  writer.close();
		}
		catch (IOException ioException) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("IOException while executing fileClearContents");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
	    String  errorText = ioException.getMessage();
	    if (errorText == null)
	    	errorText = "I/O Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "I/O Error",
	                           28,
	                           errorText);
	    return;
	  }
		catch (Exception exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("Exception while executing fileClearContents");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "Exception Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "Exception Error",
	                           28,
	                           errorText);
	    return;
	  }
	}
  /* This routine determines if a file exists or not. True is
     returned if the file exists. False is returned if the
     file does not exist. This routine will return true if
     the file is actually a directory. */
	protected static boolean  fileExists(String fileName) {
		/* Check if the file name passed by the caller is null or not */
		if (fileName == null) {
		  String  errorText = "File name string passed to fileExists is null";
		  throw new NullPointerException(errorText);
		}
		/* Build a path for the current file */
		Path    currentRelativePath = Paths.get(fileName);
		/* Check if the file actually exists or not */
		boolean   fileExists = Files.exists(currentRelativePath);
		return fileExists;
	}
	/* This routine reads the contents of a file into an array
	   of bytes. The array of bytes is returned to the caller.
	   Note that I/O errors are handled in this routine. An I/O
	   error will cause an error message to be issued and the
	   returned byte array value will be null (not empty or
	   zero-length). */
	protected static byte[] fileGetBytes(String fileName) {
		if (fileName == null) {
		  String  errorText = "File name string passed to fileGetBytes is null";
		  throw new NullPointerException(errorText);
		}
		/* Get all of the bytes from the file */
		byte[]   allBytes = null;
		try {
			allBytes = Files.readAllBytes(Paths.get(fileName));
		}
		catch (FileNotFoundException exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("FileNotFoundException while executing fileGetBytes");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("FileNotFoundException");
			allBytes = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "File Not Found";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "File Not Found",
	                           28,
	                           errorText);
	    return null;
		}
		catch (NoSuchFileException exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("NoSuchFileException while executing fileGetBytes");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("NoSuchFileException");
			allBytes = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "No Such File";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "No Such File",
	                           28,
	                           errorText);
	    return null;
		}
		catch (IOException ioException) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("IOException while executing fileGetBytes");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			allBytes = null;
	    String  errorText = ioException.getMessage();
	    if (errorText == null)
	    	errorText = "I/O Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "I/O Error",
	                           28,
	                           errorText);
	    return null;
		}
		catch (Exception exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("Exception while executing fileGetBytes");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			allBytes = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "Exception Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "Exception Error",
	                           28,
	                           errorText);
	    return null;
		}
		return allBytes;
	}
  /* This routine reads the contents of a file into a string. The
     string is returned to the caller. Note that I/O errors are
     handled in this routine. An I/O error will cause an error
     message to be issued and the returned string value will be
     null (not empty). */
	protected static String fileGetContents(String fileName) {
		return fileGetContents(fileName, null);
	}
	protected static String fileGetContents(String fileName, Charset encoding) {
		if (fileName == null) {
		  String  errorText = "File name string passed to fileGetContents is null";
		  throw new NullPointerException(errorText);
		}
		/* Set the default encoding if the caller did not provide a specific
		   encoding */
		if (encoding == null)
			encoding = Charset.defaultCharset();
		/* Get all of the bytes from the file and then encode them
		   as need be */
		String   outContents = null;
		byte[]   allBytes;
		try {
			/* For debugging show a few values */
			if (LOG.isDebugEnabled()) {
				LOG.debug("in HDLmUtility.fileGetContents");
				LOG.debug(fileName);
			}
			allBytes = Files.readAllBytes(Paths.get(fileName));
			outContents = new String(allBytes, encoding);
		}
		catch (FileNotFoundException exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("FileNotFoundException while executing fileGetContents");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("FileNotFoundException");
			outContents = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "File Not Found";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "File Not Found",
	                           28,
	                           errorText);
	    return null;
		}
		catch (NoSuchFileException exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("NoSuchFileException while executing fileGetContents");
			boolean   logException = true;
			/* We really don't want to log all of the details of the exception
			   if the exception was actually expected */
			if (fileName != null &&
					fileName.indexOf("robots.txt") >= 0)
				logException = false;
			if (logException)
			  LOG.info(exception.getMessage(), exception);
			else
				LOG.info(exception.getMessage());
			HDLmEvent.eventOccurred("NoSuchFileException");
			outContents = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "No Such File";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "No Such File",
	                           28,
	                           errorText);
	    return null;
		}
		catch (IOException ioException) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("IOException while executing fileGetContents");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			outContents = null;
	    String  errorText = ioException.getMessage();
	    if (errorText == null)
	    	errorText = "I/O Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "I/O Error",
	                           28,
	                           errorText);
	    return null;
		}
		catch (Exception exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("Exception while executing fileGetContents");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			outContents = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "Exception Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "Exception Error",
	                           28,
	                           errorText);
	    return null;
		}
		return outContents;
	}
  /* This routine reads the contents of a file into a string.
     This routine uses a default encoding which means CP-1252
     for Windows. The string is returned to the caller. Note
     that I/O errors are handled in this routine. An I/O error
     will cause an error message to be issued and the returned
     string value will be null (not empty). */
	protected static ArrayList<String> fileGetLines(String fileName) {
		/* Check the file name passed by the caller */
		if (fileName == null) {
		  String  errorText = "File name string passed to fileGetLines is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a default character set for use below */
		Charset            encoding = Charset.defaultCharset();
		/* Use the default character set to read all of the lines. The
		   number of lines may be zero. */
		return HDLmUtility.fileGetLines(fileName, encoding);
	}
  /* This routine reads the contents of a file into a string. The
     string is returned to the caller. Note that I/O errors are
     handled in this routine. An I/O error will cause an error
     message to be issued and the returned string value will be
     null (not empty). */
	protected static ArrayList<String> fileGetLines(String fileName,
			                                            Charset encoding) {
		/* Check the file name passed by the caller */
		if (fileName == null) {
		  String  errorText = "File name string passed to fileGetLines is null";
		  throw new NullPointerException(errorText);
		}
		/* Check the file encoding passed by the caller */
		if (encoding == null) {
		  String  errorText = "File encoding passed to fileGetLines is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables for use below */
		ArrayList<String>  outLines = null;
		Path               filePath = Paths.get(fileName);
		try {
			List<String> outListLines = Files.readAllLines(filePath, encoding);
			outLines = new ArrayList<String>(outListLines);
		}
		catch (FileNotFoundException exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("FileNotFoundException while executing fileGetLines");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("FileNotFoundException");
			outLines = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "File Not Found";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "File Not Found",
	                           28,
	                           errorText);
	    return null;
		}
		catch (NoSuchFileException exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("NoSuchFileException while executing fileGetLines");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("NoSuchFileException");
			outLines = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "No Such File";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "No Such File",
	                           28,
	                           errorText);
	    return null;
		}
		catch (IOException ioException) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("IOException while executing fileGetLines");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			outLines = null;
	    String  errorText = ioException.getMessage();
	    if (errorText == null)
	    	errorText = "I/O Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "I/O Error",
	                           28,
	                           errorText);
	    return null;
		}
		catch (Exception exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("Exception while executing fileGetLines");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			outLines = null;
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "Exception Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "Exception Error",
	                           28,
	                           errorText);
	    return null;
		}
		return outLines;
	}
	/* This method tries to find the suffix of a file and return the
	   file suffix to the caller. If the suffix can not be found, a
	   null value is returned to the caller. The file suffix is used
	   (in most cases) as the file type. Note, that if the last character
	   of the file name is a period, then the suffix will be an empty
	   string, not a null string. */
	protected static String fileGetSuffix(String fileName) {
		int      lastIndex;
		if (fileName == null) {
		  String  errorText = "File name string passed to fileGetSuffix is null";
		  throw new NullPointerException(errorText);
		}
		/* Search for the last period in the string */
		lastIndex = fileName.lastIndexOf('.');
		if (lastIndex < 0)
			return null;
		return fileName.substring(lastIndex+1);
	}
  /* Send the string passed by the caller to the file passed by
     the caller. The string is always appended to the end of the
     file, if the file exists. The file is created if need be.
     Note that this entry point uses a null value as the encoding
     value. */
  protected static void filePutAppend(String fileName, String outStr) {
  	/* Check if the file name passed by the caller is null or not */
		if (fileName == null) {
		  String  errorText = "File name string passed to filePutAppend is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the output string passed by the caller is null or not */
		if (outStr == null) {
		  String  errorText = "Output data string passed to filePutAppend is null";
		  throw new NullPointerException(errorText);
		}
		/* Write the output string to the file specified by the caller
		   with a null encoding value */
		HDLmUtility.filePutAppend(fileName, outStr, null);
  }
  /* Send the string passed by the caller to the file passed by
     the caller. The string is always appended to the end of the
     file, if the file exists. The file is created if need be.
     The caller can optionally provide a file encoding value.
     If the file encoding value is null, then no file encoding
     value is used. */
	protected static void filePutAppend(String fileName, String outStr, Charset encoding) {
		/* Check if the file name passed by the caller is null or not */
		if (fileName == null) {
		  String  errorText = "File name string passed to filePutAppend is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the output string passed by the caller is null or not */
		if (outStr == null) {
		  String  errorText = "Output data string passed to filePutAppend is null";
		  throw new NullPointerException(errorText);
		}
		/* Declare and define a variable for use below */
	  BufferedWriter  writer;
		try {
			/* Check if the file encoding value is null. If the file encoding
			   value is null, then a default writer is constructed here. */
			if (encoding == null) {
			  writer = new BufferedWriter(new FileWriter(fileName, true));
			}
			else {
			  Path  appendFilePath = Paths.get(fileName);
			  writer = Files.newBufferedWriter(appendFilePath, StandardCharsets.UTF_8);
			}
		  writer.write(outStr);
		  writer.close();
		}
		catch (FileNotFoundException exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("FileNotFoundException while executing filePutAppend");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("FileNotFoundException");
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "File Not Found";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "File Not Found",
	                           28,
	                           errorText);
	    return;
		}
		catch (NoSuchFileException noSuchFileException) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("NoSuchFileException while executing filePutAppend");
			LOG.info(noSuchFileException.getMessage(), noSuchFileException);
			HDLmEvent.eventOccurred("NoSuchFileException");
	    String  errorText = noSuchFileException.getMessage();
	    if (errorText == null)
	    	errorText = "No Such File";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "No Such File",
	                           28,
	                           errorText);
	    return;
		}
		catch (IOException ioException) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("IOException while executing filePutAppend");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
	    String  errorText = ioException.getMessage();
	    if (errorText == null)
	    	errorText = "I/O Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "I/O Error",
	                           28,
	                           errorText);
	    return;
		}
		catch (Exception exception) {
			if (fileName != null)
			  LOG.info("File name - " + fileName);
			LOG.info("Exception while executing filePutAppend");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
	    String  errorText = exception.getMessage();
	    if (errorText == null)
	    	errorText = "Exception Error";
	    HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR,
	                           "Exception Error",
	                           28,
	                           errorText);
	    return;
		}
  }
  /* Send the string passed by the caller to the file passed by
     the caller. Make the string into a separate line by adding
     a newline character to the string passed by the caller. The
     string (line) is always appended to the end of the file, if
     the file exists. The file is created if need be. */
  protected static void filePutAppendLine(String fileName, String outStr) {
		if (fileName == null) {
		  String  errorText = "File name string passed to filePutAppendLine is null";
		  throw new NullPointerException(errorText);
		}
		if (outStr == null) {
		  String  errorText = "Output data string passed to filePutAppendLine is null";
		  throw new NullPointerException(errorText);
		}
    outStr += "\n";
    HDLmUtility.filePutAppend(fileName, outStr);
  }
	/* This routine appends a line to the standard log file and
	   a host name specific log file (in some cases). The caller
	   provides the line to be appended. The line must not
		 be null. This routine identifies the log files (which
		 depend on the platform) and calls the correct routine
		 to do the actual appending. The line should start with
		 a timestamp. However, this code does not verify the
		 presence of a timestamp.

		 The host name passed to this routine may be null. This
		 means that the current line is not associated with any
		 particular host and should just be written to the standard
		 log file.

		 This routine may need to be synchronized. It is possible,
		 that the log file is being corrupted because multiple
		 threads are calling this routine at the same time. */
	protected static void filePutAppendLineLogs(String outputLine,
			                                        String hostName) {
		/* Check if the output line passed by the caller is null */
		if (outputLine == null) {
			String  errorText = "Output line passed to filePutAppendLineLogs is null";
			throw new NullPointerException(errorText);
		}
	  /* Build the path name for the standard log file */
	  String   standardPathStr = HDLmUtility.getStandardPath();
	  /* Get the name of the file used log posted changes */
	  String   mainLogFileName = HDLmConfigInfo.getLogFileName();
	  String   standardFileName = standardPathStr + mainLogFileName;
	  /* Add the posted changes to the file */
	  HDLmUtility.filePutAppendLine(standardFileName, outputLine);
	  /* If a host name was passed to this routine, then the host
	     name specific file must also be updated */
	  if (hostName != null) {
	  	/* Check if this routine is invoked with a zero-length host
	  	   name string. This should never happen, but does happen
	  	   anyways. We need to figure this out. */
	  	if (hostName.equals("") == true) {
	  		HDLmUtility.logStackTrace();
	  	}
	  	int   mainLogfilePos = mainLogFileName.lastIndexOf('.');
			/* Make sure we found the period */
			if (mainLogfilePos < 0) {
			  HDLmAssertAction(false, "Main log file name does not have a period");
			}
	  	/* Build the host name specific log file name */
			String   hostSpecificFileName = standardPathStr +
					                            mainLogFileName.substring(0, mainLogfilePos + 1) +
					                            hostName +
					                            mainLogFileName.substring(mainLogfilePos);
			HDLmUtility.filePutAppendLine(hostSpecificFileName, outputLine);
	  }
	}
	/* Get some information about a bridge request. The caller
	   passes the path value string and the parameters. */
	protected static HDLmUtilityResponse  getBridgeInformation(String pathValue,
			                                                       String parmValue) {
		/* Check if the path value string passed by the caller is null */
		if (pathValue == null) {
			String  errorText = "Path value string passed to getBridgeInformation is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the parameter value string passed by the caller is null */
		if (parmValue == null && parmValue != null) {
			String  errorText = "Parameter value string passed to getBridgeInformation is null";
			throw new NullPointerException(errorText);
		}
		/* Declare/define a few local variables */
		ArrayList<HDLmToken>  parmTokens = null;

		/* Get some tokens from the path vaue and from the parameter value */
		ArrayList<HDLmToken>  pathTokens = HDLmString.getTokens(pathValue);
		if (parmValue != null)
		  parmTokens = HDLmString.getTokens(parmValue);
		/* Create a response object that can (and is) used to return
	     multiple values to the caller */
	  HDLmUtilityResponse   utilityResponse = new HDLmUtilityResponse();
	  /* Store some values in the utility response */
	  utilityResponse.setOperationType(pathTokens.get(5).getValue());
	  utilityResponse.setTableName(pathTokens.get(7).getValue());
	  if (parmValue != null) {
	  	utilityResponse.setQueryTarget(parmTokens.get(3).getValue());
	  	utilityResponse.setQueryValue(parmTokens.get(7).getValue());
	  }
		return utilityResponse;
	}
	/* Get the content string from the current configuation */
	protected static String getContentString() {
		return HDLmUtility.getContentString(HDLmEditorTypes.PASS);
	}
  protected static String getContentString(HDLmEditorTypes editorType) {
		/* Check if the editor type value passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value passed to getContentString is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
  	/* Declare and define a few values */
    /* Get some values used to build the content string */
    String  companyPrefix = HDLmConfigInfo.getEntriesDatabaseCompanyPrefix();
    String  contentType = HDLmUtility.getContentType(editorType);
    /* In at least one important case, we need to change the editor
       type here. The inline editors needs to share data with the main
       pass-through editor. This is accomplished by changing the
       editor type here. */
    if (HDLmGlobals.checkForInlineEditor(editorType)) {
      editorType = HDLmEditorTypes.PASS;
      contentType = HDLmUtility.getContentType(editorType);
    }
    String  contentSuffix = HDLmConfigInfo.getEntriesDatabaseContentSuffix();
    String  contentSuffixSystem = HDLmStateInfo.getSystemValue();
    /* The content suffix is wrong in the test environment. We must
       force the value to be 'a' (without the quotes) in all cases. */
    contentSuffixSystem = "a";
    contentSuffix += contentSuffixSystem;
    if (LOG.isDebugEnabled()) {
		  LOG.debug("In HDLmUtility.getContentString");
		  LOG.debug(contentSuffix);
		  LOG.debug(contentSuffixSystem);
    }
    /* Add the prefix to the value. If the prefix is an empty string,
       this step can be skipped. */
    String  valueModified = contentType;
    if (!companyPrefix.equals(""))
      valueModified = valueModified + '_' + companyPrefix;
    /* Add the suffix to the value. If the suffix is an empty string,
       this step can be skipped. */
    if (!contentSuffix.equals(""))
      valueModified = valueModified + '_' + contentSuffix;
    /* Return the content string */
    return valueModified;
  }
  /* Get the current content type based on the current editor type.
     This routine will always return a lower case string value. */
  protected static String getContentType(HDLmEditorTypes editorType) {
		/* Check if the editor type value passed by the caller is null */
		if (editorType == null) {
			String  errorText = "Editor type value passed to getContentType is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		String  contentTypeStr = editorType.toString().toLowerCase();
		if (LOG.isDebugEnabled()) {
		  LOG.debug("In HDLmUtility.getContentType");
		  LOG.debug(contentTypeStr);
		}
    return contentTypeStr;
  }
  /* Get the current DB2 version and return it as a string
     to the caller */
  protected static String  getDb2Version() {
  	String version = System.getProperty("java.version");
    if (version.startsWith("1."))
      version = version.substring(2, 3);
    return version;
  }
  /* Get the value of an environment variable, if possible.
     This routine, might return NULL, if the environment
     variable does not exist. The value of the environment
     variable might be in mixed case. */
  protected static String  getEnvironmentVariable(String envNameStr) {
		/* Check if the environment variable name passed by the caller is null */
		if (envNameStr == null) {
			String  errorText = "Environment variable name passed to getEnvironmentVariable is null";
			throw new NullPointerException(errorText);
		}
  	/* Try to get the value of the environment variable */
		String  envValueStr = System.getenv(envNameStr);
		return envValueStr;
	}
  /* Get the value of an environment variable in uppercase, if possible.
	   This routine, might return NULL, if the environment variable does
	   not exist. The value of the environment will always be NULL or the
	   value of the environment variable in uppercase. */
	protected static String  getEnvironmentVariableUpper(String envNameStr) {
		/* Check if the environment variable name passed by the caller is null */
		if (envNameStr == null) {
			String  errorText = "Environment variable name passed to getEnvironmentVariableUpper is null";
			throw new NullPointerException(errorText);
		}
		/* Try to get the value of the environment variable */
		String  envValueStr = System.getenv(envNameStr);
		/* Convert the value to uppercase, if possible */
		if (envValueStr != null)
			envValueStr = envValueStr.toUpperCase();
		return envValueStr;
	}
  /* Get the final value string from the value string passed by
     the caller and the content suffix passed by the caller.
     This routine implements the rule that an underscore is
     added to the value, if the content suffix is actually set. */
  protected static String getFinalValue(String value,
		                                    String contentSuffix) {
  	if (contentSuffix.length() == 0)
  		return value;
  	return value + '_' + contentSuffix;
  }
	/* The next method takes an input URL and extracts the full path
		 value from it. The full path value string is returned to the caller.
		 The full path value string does not include the protocol, the host
		 name, or the port number (if any). The full path value string also
		 includes the URL fragment (if any) and the URL search value (if
		 any). For example, the full path value part of
		 https://www.oneworldobservatory.com/en-US/buy-tickets?q=123 is
		 /en-US/buy-tickets?q=123. */
  protected static String getFullPathString(String urlStr) {
  	/* Check if the URL string passed by the caller is null */
  	if (urlStr == null) {
  		String  errorText = "URL string passed to getFullPathString is null";
		  throw new NullPointerException(errorText);
	  }
    /* Remove the protocol (if any), the host name (if any), and
       the port number (if any) from the URL string */
    urlStr = HDLmUtility.removeHost(urlStr);
    return urlStr;
  }
  /* This routine get the host name from the URL contained in a set
	   of JSON elements. The host name is returned to the caller. The
	   URL is removed from the JSON elements. The host name will be null,
	   if the URL is not valid. This routine is no longer in use, but
	   could be used if need be. */
	static String getHostNameFromJsonWithCheck(JsonElement jsonElement) {
		/* Check if the JSON element value passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element passed to getHostNameRemoveUrlFromJson is null";
			throw new NullPointerException(errorText);
		}
		/* Extract the URL value from the JSON and get the host name
	     from the URL value */
		String  keyStr;
		keyStr = "HDLmUrlValue";
		String  urlValue = HDLmJson.getJsonString(jsonElement, keyStr);
		if (urlValue == null) {
	   String  errorText = "URL value not found in JSON element";
	   HDLmAssertAction(false, errorText);
		}
		/* Remove the URL from the JSON elements */
		HDLmJson.removeJsonKey(jsonElement, keyStr);
		/* Check if the URL is valid or not. Just give up if the URL
		   string is not valid. */
		boolean   urlValid = HDLmUtility.checkUrl(urlValue);
		if (!urlValid)
			return null;
		String  hostName = HDLmUtility.getHostNameFromUrl(urlValue);
		return hostName;
	}
  /* The next routine tries to get the hostname of the current
     system. This code should work under Windows and Linux and
     the various flavors of Unix. The hostname is returned to
     the caller as a Java string. Note that this routine may
     return a null value.

     This routine will always return the parent host system name.
     In other words, even if we are running inside a Docker
     container, this routine will return the parent system name.
     This routine will never return the Docker container name
     or the system name of the Docker container.

     Under Docker, this routine will get the host name from an
     environment variable. The environmental variable must be
     set to proper value. This routine assumes that the environmental
     variable is present. */
	protected static String  getHostNameFromSystem() {
		/* Declare and define a few variables */
		String  hostName = null;
		/* First check if we are inside a Docker container. Special
		   case code is needed if a Docker container is active. */
		if (HDLmMain.isDockerFlagSet() == false) {
			/* Check if we are running under Windows or Linux/Unix */
		  if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS) {
		  	hostName = System.getenv("HOSTNAME");
		  }
		  /* We must be running under some type of Linux/Unix. In this
		     case we get the hostname by executing a command that gets
		     the hostname. */
		  else {
		  	/* The command that obtains the hostname may throw an
		  	   exception. We must be prepared for this case. */
		  	try {
		  		/* Execute the Linux/Unix command that returns the
		  		   hostname */
					hostName = HDLmUtility.execReadToString("hostname");
					hostName = hostName.trim();
				}
		  	/* Catch the possible exception from running the command
		  	   that returns the hostname */
		  	catch (IOException e) {
	        hostName = null;
	        LOG.info("hostname");
	  			LOG.info("IOException while executing getHostNameFromSystem");
	  			LOG.info(e.getMessage(), e);
	        HDLmEvent.eventOccurred("IOException");
	        return null;
				}
		  }
		}
		/* Looks like we are running inside a Docker container. Try to get
		   the host name from a Docker specific environment variable */
		else {
	    /* Get the string value for the host system name. This value
         is only set under Docker (in a Docker container). The host
         name is something like javaproxya.dnsalias.com. */
      String  hostEnvNameString = HDLmDefines.getString("HDLMHOSTNAME");
	    if (hostEnvNameString == null) {
		    String   errorFormat = "Define value for host name not found (%s)";
		    String   errorText = String.format(errorFormat, "HDLMHOSTNAME");
	 	    HDLmAssertAction(false, errorText);
	    }
	    /* Get the name of the actual host system */
		  String  hostNameValue = HDLmUtility.getEnvironmentVariableUpper(hostEnvNameString);
		  /* Check if the host environment variable was set or not */
	    if (hostNameValue == null) {
		    String   errorFormat = "Host name environment variable was not set (%s)";
		    String   errorText = String.format(errorFormat, hostEnvNameString);
	 	    HDLmAssertAction(false, errorText);
	    }
		  hostName = hostNameValue;
		}
		return hostName;
	}
  /* The next routine takes an input URL and gets the host name
     from it (if possible). The host name is returned to the
     caller. The host name will be undefined if the input URL
     does not have a host name. The returned host name will not
     include the port number, if a port number was used. */
  protected static String  getHostNameFromUrl(String urlStr) {
	  /* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getHostNameFromUrl is null";
			throw new NullPointerException(errorText);
		}
		/* Build a URL object from the input string */
		URL urlObj = null;
		/* Try to create a new URL object from a URL string */
		try {
			urlObj = new URL(urlStr);
		}
		catch (MalformedURLException malformedException) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("MalformedURLException while executing getHostNameFromUrl");
			LOG.info(malformedException.getMessage(), malformedException);
			HDLmEvent.eventOccurred("MalformedURLException");
			HDLmAssertAction(false, "MalformedURLException while executing getHostNameFromUrl");
			return null;
		}
		catch (Exception exception) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("Exception while executing getHostNameFromUrl");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			HDLmAssertAction(false, "Exception while executing getHostNameFromUrl");
			return null;
		}
		String  hostName = urlObj.getHost();
		return hostName;
	}
	/* This routine get the host name from a URL passed by the
	   caller. The host name is returned to the caller. The host
	   name will be null, if the URL is not valid. */
	static String getHostNameFromUrlWithCheck(final String urlValue) {
		/* Check if the URL value reference passed by the caller is null */
		if (urlValue == null) {
			String  errorText = "URL value reference passed to getHostNameFromUrlWithCheck is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the URL is valid or not. Just give up if the URL
		   string is not valid. */
		boolean   urlValid = HDLmUtility.checkUrl(urlValue);
		if (!urlValid)
			return null;
		String  hostName = HDLmUtility.getHostNameFromUrl(urlValue);
		return hostName;
	}
  /* Get the current date/time in ISO 8601 format. This means the
     current UTC in the form yyyy-mm-ddThh:mm:ss.sssZ. The 'T' and
     the 'Z' (without the quotes) are actually part of the string
     returned by this routine. */
  protected static String  getISO8601() {
    /* Get the current instant */
    Instant  instant = Instant.now();
    String   outStr = instant.toString();
    return outStr;
  }
 	/* The next method takes an input URL and extracts the path value
     from it. The path value string is returned to the caller. The
     path value string does not include the protocol, the host name,
     or the port number (if any). The path value string also excludes
     the URL fragment (if any) and the URL search value (if any).
     For example, the path value part of
     https://www.oneworldobservatory.com/en-US/buy-tickets?q=123 is
     /en-US/buy-tickets */
	protected static String getPathString(String urlStr) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getPathString is null";
			throw new NullPointerException(errorText);
		}
	  /* Remove the protocol (if any), the host name (if any), and
	     the port number (if any) from the URL string */
	  urlStr = HDLmUtility.removeHost(urlStr);
	  /* Remove the fragment (if any) from the URL string */
	  int   indexOf = urlStr.lastIndexOf('#');
	  if (indexOf >= 0)
	    urlStr = urlStr.substring(0, indexOf);
	  /* Remove the search value (if any) from the URL string */
	  indexOf = urlStr.lastIndexOf('?');
	  if (indexOf >= 0)
	    urlStr = urlStr.substring(0, indexOf);
	  return urlStr;
	}
  /* Check if a file type is binary or not. The caller provides the
		 file suffix as a string (with no period). If the file types is
		 recognized as binary, boolean true value is returned to the
		 caller. If the file is recognized as a non-binary file, a
		 false value is returned to the caller. If the file type can
		 not be recognized, a null value is returned. */
	protected static String getMimeType(String suffix) {
		if (suffix == null) {
		  String  errorText = "File suffix string passed to getMimeType is null";
		  throw new NullPointerException(errorText);
		}
		/* Check the file type */
		switch (suffix) {
	    case "avi":
	  	  return "video/avi";
		  case "bmp":
		  	return "image/bmp";
		  case "css":
		  	return "text/css";
		  case "gif":
		  	return "image/gif";
		  case "htm":
		  case "html":
		  	return "text/html";
		  case "ico":
		  	return "image/x-icon";
		  case "jpg":
		  case "jpeg":
		  	return "image/jpg";
		  case "js":
		  	return "text/javascript";
		  case "mp3":
		  	return "audio/mp3";
		  case "png":
		  	return "image/png";
		  case "txt":
		  case "text":
		  	return "text/plain";
		  default:
		  	return null;
		}
	}
  /* The method below gets the next number from a list of
	   numbers. If the list is empty, this routine will return
	   one. If the list has a gap, this routine will return the
	   first number from the gap. For example, if the ilst has
	   [1, 2, 4, 5], this routine will return 3. If the list has
	   no gaps, then this routine will return a value that is one
	   higher than the highest number in the list. For example, if
	   the list has [1, 2, 3, 4], then this routine will return 5.
	   Note that if the list is missing one, then the value of one
	   will be returned. For example, if the list has [2, 3, 4],
	   then this routine will return one. */
	protected static int getNextInteger(ArrayList<Integer> integerList) {
		/* Check if the integer list array list passed by the caller is null */
		if (integerList == null) {
		  String  errorText = "Integer list array list passed to getNextInteger is null";
		  throw new NullPointerException(errorText);
		}
	  int   integerListLen = integerList.size();
	  /* Check if the existing integer list is empty. Just return
	     one in this case. */
	  if (integerListLen == 0)
	    return 1;
	  /* Check if the number one is in the list. If the number one
	     is not in the list, return a one to the caller. */
	  if (integerList.contains(Integer.valueOf(1)) == false)
	    return 1;
	  /* Check each entry in the list passed by the caller. We may
	     find a gap in the list. */
	  for (int i = 0; i < integerListLen; i++) {
	    /* Get the current value and calculate the next
	       value. See if the next value is missing from the
	       list. If the next value is missing, then we can
	       return the next value to the caller. */
	   int  currentValue = integerList.get(i);
	   int  nextValue = currentValue + 1;
	   if (integerList.contains(Integer.valueOf(nextValue)) == false)
	     return nextValue;
	  }
	  return integerListLen + 1;
	}
  /* Get the perceptual hash value for an image. Return the perceptual
	   hash value to the caller. The returned value is actually a 64-bit
	   integer. However, the value is always returned as a 16-digit hex
	   string. */
	protected static String getPerceptualHashFromImage(Image imageValue) {
		if (imageValue == null) {
		  String  errorText = "Image value passed to getPerceptualHash is null";
		  throw new NullPointerException(errorText);
		}
		/* Build a hasher for use below */
		PHashImageHash  hasher = null;
		hasher = new PHashImageHash();
  	long            hashValue = hasher.getPerceptualHash(imageValue);
  	String          hashHex = Long.toHexString(hashValue);
  	return hashHex;
	}
  /* Get the perceptual hash value for a URL string. Return the perceptual
     hash value to the caller. The returned value is actually a 64-bit
     integer. However, the value is always returned as a 16-digit hex
     string. The URL passed to this routine may or may not be a data
     URL. If it not a data URL, then it may start with two forward
     slash characters without an HTTP/HTTPS protocol in front of the
     two forward slash characters.

     The caller can provide an already calculated perceptual hash value.
     This value will be null if it has not already been calculated and
     non-null, if it has already been calculated. */
	protected static HDLmUtilityResponse  getPerceptualHashFromUrl(String urlStr,
			                                                           String pHashHex) {
		if (urlStr == null) {
		  String  errorText = "URL value passed to getPerceptualHash is null";
		  throw new NullPointerException(errorText);
		}
		/* LOG.info("In getPerceptualHashFromUrl"); */
		/* LOG.info(urlStr); */
		/* Define a few variables for use below */
		Image   image;
		/* Create a response object that can (and is) used to return
		   multiple values to the caller */
		HDLmUtilityResponse   utilityResponse = new HDLmUtilityResponse();
		/* The code here handles a special case and may not even
	     be correct. If the original URL had plus signs in it,
	     then the plus signs will end up as blanks here. This
	     potential problem is bypassed by replacing plus signs
	     in the original URL with a special sequence. The code
	     below puts the plus signs back in. This may or may not
	     be correct. */
		String  hdlmPlusSign = HDLmDefines.getString("HDLMPLUSSIGN");
	  String  urlStrModified = urlStr.replaceAll(hdlmPlusSign, "+");
	  utilityResponse.setUrlStr(urlStrModified);
	  /* If the caller provided an already calculated perceptual hash
	     value, then we can just use that value */
	  if (pHashHex != null) {
	  	utilityResponse.setPHashValue(pHashHex);
	  	/* Set the update cache flag. This flag is checked by the
	  	   caller and forces an update of the cache. */
	  	utilityResponse.setUpdateCache(true);
	  	return utilityResponse;
	  }
		/* We now have the URL string, that we need a perceptual hash
	     value for. As a first step we should look in the local
	     cache for the value. This might or might not work. */
	  pHashHex = HDLmPHashCache.getIfPresent(urlStrModified);
	  if (pHashHex == null) {
	  	/* Check if the URL string starts with a special value
	  	   (data:). If this value is found, we have a data URL,
	  	   not a conventional network URL (HTTP or HTTPS). Data
	  	   URLs have all of their data inline. */
	  	if (urlStrModified.startsWith("data:")) {
	  	  HDLmImageInformation  imageInfo = HDLmImageInformation.processDataUrl(urlStrModified);
	  	  image = imageInfo.getImage();
	  	  if (image != null) {
			    pHashHex = HDLmUtility.getPerceptualHashFromImage(image);
			    if (pHashHex != null) {
			    	utilityResponse.setPHashValue(pHashHex);
			    	utilityResponse.setPHashActuallyCalculated(true);
				  	/* Set the update cache flag. This flag is checked by the
			  	     caller and forces an update of the cache. */
			  	  utilityResponse.setUpdateCache(true);
			    }
	  	  }
	  	}
	  	else {
	  		/* It turns out that we don't really support JP2 (Jpeg2000)
	  		   images. However, the WEBP image is rather likely to be
	  		   almost the same as the JP2 image, for any given image.
	  		   So we can get the WEBP image and calculate the perceptual
	  		   hash from it. This value should be very close to the
	  		   perceptual hash value we might calculate from the actual
	  		   JP2 image. Note that the use of 'f_jp2' may well be unique
	  		   to Legends/OWO. */
	  		urlStrModified = urlStrModified.replace("f_jp2", "f_webp");
		    image = HDLmUtility.loadImage("https" + ":" + urlStrModified);
		    if (image == null) {
					LOG.info("Image is null in getPerceptualHash" + " - " + urlStrModified);
		    }
		    else {
			    pHashHex = HDLmUtility.getPerceptualHashFromImage(image);
			    if (pHashHex != null) {
			    	utilityResponse.setPHashValue(pHashHex);
			    	utilityResponse.setPHashActuallyCalculated(true);
				  	/* Set the update cache flag. This flag is checked by the
			  	     caller and forces an update of the cache. */
			  	  utilityResponse.setUpdateCache(true);
			    }
		    }
	  	}
	  }
	  /* The perceptual hash value was actually found in the cache.
	     Set a flag showing that the perceptual hash value really
	     was found. */
	  else {
	  	utilityResponse.setPHashValue(pHashHex);
	  	utilityResponse.setFoundInCache(true);
	  	/* Set the update cache flag to false. This flag is checked by the
	       caller and forces an update of the cache if it is set to true. */
	    utilityResponse.setUpdateCache(false);
	  }
	  return utilityResponse;
	}
	/* The next routine takes an input URL and gets the protocol
	   from it (if possible). The protocol is returned to the
	   caller. The protocol will be undefined if the input URL
	   does not have a protocol. */
	protected static String  getProtocolFromUrl(String urlStr) {
	 /* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to getProtocolFromUrl is null";
			throw new NullPointerException(errorText);
		}
		/* Build a URL object from the input string */
		URL urlObj = null;
		/* Try to create a new URL object from a URL string */
		try {
			urlObj = new URL(urlStr);
		}
		catch (MalformedURLException malformedException) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("MalformedURLException while executing getProtocolFromUrl");
			LOG.info(malformedException.getMessage(), malformedException);
			HDLmEvent.eventOccurred("MalformedURLException");
			HDLmAssertAction(false, "MalformedURLException while executing getProtocolFromUrl");
			return null;
		}
		catch (Exception exception) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("Exception while executing getProtocolFromUrl");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			HDLmAssertAction(false, "Exception while executing getProtocolFromUrl");
			return null;
		}
		String  protocolName = urlObj.getProtocol();
		return protocolName;
	}
  /* This routine returns a double value in the range 0.0 to 1.0 */
  protected static double getRandomDouble() {
  	Random   rd = new Random();
  	double   randomDouble = rd.nextDouble();
  	return randomDouble;
  }
  /* This routine returns an array list of double values. Each of the
     double values is in the range 0.0 to 1.0. The caller specifies
     how many values should be obtained. That number of values must
     be greater than or equal to zero. */
  protected static ArrayList<Double> getRandomDoubleList(int numberValues) {
    if (numberValues < 0) {
		  HDLmAssertAction(false, "Number of values passed to getRandomDoubleList is less than zero");
		}
  	ArrayList<Double>  randomList = new ArrayList<Double>();
  	Random   rd = new Random();
  	double   randomDouble;
  	for (int i = 0; i < numberValues; i++) {
  		randomDouble = rd.nextDouble();
  		randomList.add(randomDouble);
  	}
  	return randomList;
  }
  /* This routine returns a float value in the range 0.0 to 1.0 */
  protected static float getRandomFloat() {
  	Random   rd = new Random();
  	float    randomFloat = rd.nextFloat();
  	return randomFloat;
  }
  /* This routine gets some basic error information from the
     exception and the stack trace. All of the information
     including the stack trace is returned to the caller as
     a string. */
	protected static String getStackTrace(Exception exception) {
    StringBuffer  stringBuffer = new StringBuffer(500);
    StackTraceElement[]   stackTraceArray = exception.getStackTrace();
    stringBuffer.append(exception.getClass().getName() + ": " + exception.getMessage() + "\n");
    for (int i = 0; i < stackTraceArray.length; i++) {
    	stringBuffer.append("\t" + "at " + stackTraceArray[i].toString() + "\n");
    }
    return stringBuffer.toString();
	}
	/* This routine returns the standard path string for the current
	   system. The path string is returned to the caller. The path
	   string is highly environment dependent. */
	protected static String  getStandardPath() {
	  /* Build the path name for the standard log file */
	  String   standardPathStr = "";
		/* First check if we are inside a Docker container. Special
       case code is needed if a Docker container is active. */
	  if (HDLmMain.isDockerFlagSet() == false) {
		  /* Check for Windows versus Unix (of some kind) */
		  if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS) {
		  	standardPathStr += "\\Users\\pscha\\HeadlampJetty\\workspace-4.33.0";
		  	standardPathStr += "\\ProxyServerA\\";
		  }
		  /* The following path is correct for our AWS Linux instance */
		  else {
		  	standardPathStr += "/var/www/html";
		  	standardPathStr += "/example.com/public_html/";
		  }
	  }
  	/* Looks like we are running inside a Docker container */
    else {
	  	standardPathStr += "/var/www/html";
	  	standardPathStr += "/example.com/public_html/";
    }
	  return standardPathStr;
	}
  /* This routine returns a single character that will be used to
     build keys for obtaining data. For javaproxyx.dnsalias.com,
     the character will be 'x' (without the quotes). For example,
     if the actual hostname is javaproxyc.dnsalias.com, then a 'c'
     (a single character, without the quotes) will be returned to
     the caller. Note that these rules really apply to Linux/Unix.
     Under Windows the system suffix character is provided by the
     Java project. If the Java project is ProxyServerA, then the
     suffix character will be 'a' (without the quotes). If the
     Java project is ProxySeverB, then the suffix character will
     be 'b' (without the quotes).  */
  protected static char  getSystemCharacter() {
  	/* Add some debugging information */
  	if (LOG.isDebugEnabled())
  		LOG.debug("In HDLmUtility.getSystemChacter");
  	/* Declare and define a few values */
  	char  systemChar = 'a';
    /* First check if we are inside a Docker container. Special
       case code is needed if a Docker container is active. */
  	if (HDLmMain.isDockerFlagSet() == false) {
  		/* Check if we are running under Windows or Linux/Unix */
	    if (HDLmMain.getOsType() == HDLmOsTypes.WINDOWS) {
	    	String  userDirString = System.getProperty("user.dir");
	    	/* Add some debugging information */
	    	if (LOG.isDebugEnabled())
	    		LOG.debug(userDirString);
	    	/* Check if we didn't get a user directory value. If we
	    	   did not, just return a default value. */
	    	if (userDirString == null)
	    		return 'a';
	  	  int     userDirStringLen = userDirString.length();
	  	  if (userDirStringLen >= 1) {
	  		  String  systemCharStr = userDirString.substring(userDirStringLen-1, userDirStringLen);
	  		  systemCharStr = systemCharStr.toLowerCase();
	  		  systemChar = systemCharStr.charAt(0);
	  		  return systemChar;
	  	  }
	  	  return 'a';
	    }
	    /* We must be running under Linux or Unix */
	    else {
	  	  /* Try to get the hostname of the current system */
	  	  String  hostName = HDLmUtility.getHostNameFromSystem();
	  	  if (LOG.isDebugEnabled())
	    		LOG.debug(hostName);
	  	  if (hostName != null &&
	  			  hostName.startsWith("javaproxy") &&
	  			  hostName.endsWith(".dnsalias.com"))
	  		  return hostName.charAt(9);
	  	  else
	  		  return 'a';
	    }
  	}
  	/* Looks like we are running inside a Docker container. Try to
  	   get the user directory name from a Docker specific environment
  	   variable */
    else {
      /* Get the string value for the user directory name. This value
         is only set under Docker (in a Docker container). The host
         user directory name is something like ProxyServerA. This
         will work because we really only use the last character. */
      String  hostEnvNameString = HDLmDefines.getString("HDLMHOSTUSERDIR");
      if (hostEnvNameString == null) {
        String   errorFormat = "Define value for host user directory not found (%s)";
        String   errorText = String.format(errorFormat, "HDLMHOSTUSERDIR");
        HDLmAssertAction(false, errorText);
      }
      /* Get the user directory name */
      String  userDirNameValue = HDLmUtility.getEnvironmentVariableUpper(hostEnvNameString);
  	  if (LOG.isDebugEnabled()) {
  	  	LOG.debug(hostEnvNameString);
    		LOG.debug(userDirNameValue);
  	  }
      /* Check if the environment variable has been set or not.
         If not, just use a default value for the system character. */
      if (userDirNameValue == null)
      	return 'a';
  	  int     userDirStringLen = userDirNameValue.length();
  	  if (userDirStringLen >= 1) {
  		  String  systemCharStr = userDirNameValue.substring(userDirStringLen-1, userDirStringLen);
  		  systemCharStr = systemCharStr.toLowerCase();
  		  systemChar = systemCharStr.charAt(0);
  		  return systemChar;
  	  }
    }
  	return 'a';
  }
  /* Get a timestamp string that can be used in log files and the
	   like. The returns string will always have an ISO 8601 format.
	   The format will be YYYY-MM-DDTHH:MM:SS.UUUUUU and that time
	   will always be the current UTC time. Note that this not exactly
	   the ISO 8601 format. However, it is close. */
	protected static String getUtcTimeStampNow() {
		Instant   instant = Instant.now().truncatedTo(ChronoUnit.MICROS);
		/* Get the timetamp as a string and remove the trailing Z character */
	  return HDLmUtility.getUtcTimeStamp(instant);
	}
  /* Get a timestamp string that can be used in log files and the
     like. The returns string will always have an ISO 8601 format.
     The format will be YYYY-MM-DDTHH:MM:SS.UUUUUU and that time
     will always be the current UTC time. Note that this not exactly
     the ISO 8601 format. However, it is close. */
	protected static String getUtcTimeStamp(Instant instant) {
		/* Get the timetamp as a string and remove the trailing Z character */
		String    timeStampStr = instant.toString();
		int       timeStampLen = timeStampStr.length();
		/* Check if the trailing 'Z' character can be removed and remove
		   it, if possible */
		if (timeStampLen > 0)
			timeStampStr = timeStampStr.substring(0, timeStampLen - 1);
		/* Pad the string on the right to finish the microseconds */
		timeStampStr = StringUtils.rightPad(timeStampStr, 26, '0');
		timeStampStr = timeStampStr.substring(0, 26);
	  return timeStampStr;
	}
  /* Get a UUID and return it as a string to the caller */
  protected static String  getUuidStr() {
  	UUID    uuid = UUID.randomUUID();
    String  uuidStr = uuid.toString();
    return uuidStr;
  }
	/* This routine is passed a string and a function. The function is invoked
	   on the string. */
	protected static void  handleStringUsingFunction(String inStr, Function<String, Void> strFunction) {
		/* Check the values passed by the caller */
		if (inStr == null) {
		  String  errorText = "String passed to handleStringUsingFunction is null";
		  throw new NullPointerException(errorText);
		}
		/* Make sure that the caller passed a function */
		if (strFunction == null) {
		  String  errorText = "Function passed to handleStringUsingFunction is null";
		  throw new NullPointerException(errorText);
		}
		/* Use the function passed by the caller */
		strFunction.apply(inStr);
	}
	/* The method convert a hexadecimal string to a binary string.
	   The output string will contain only zero and 1 characters.
	   The output string is returned to the caller. */
	public static String hexToBinary(String hexString) {
    int binaryLength = hexString.length() * 4;
    String binaryString = new BigInteger(hexString, 16).toString(2);
    /* Left pad the string result with 0s if converting to BigInteger
       removes them */
    if (binaryString.length() < binaryLength) {
      int lengthDifference = binaryLength - binaryString.length();
      binaryString = "0".repeat(lengthDifference) + binaryString;
    }
    return binaryString;
  }
	/* Check if we running under Docker. A certain file is created only
	   in the Docker environment. If this file exists, then we are almost
	   certainly running in a Docker container. */
	protected static Boolean  isDockerActive() {
    /* Check if a Docker related file actually exists. We assume we
       are running under Docker, if this file can be found. */
    File  dockerEnvFile = new File("/.dockerenv");
    if (dockerEnvFile.exists()) {
	    LOG.debug("We must be running under Docker");
	    /* Show that a Docker container is active */
	    return true;
    }
    /* The file does not exist. We are probably not running
       in a Docker container. */
    else {
	    LOG.debug("We must not be running under Docker");
	    /* Show that a Docker container is not active */
	    return false;
    }
  }
	/* Check if a Junit test if active or not. In a few cases, we 
	   actually need to know this. This is not a general require-
	   ment in almost all cases. A flag is checked to determine
	   if we are running a Junit test.  */
	protected static boolean isJUnitActive() {  
    /* Return the value of the Junit active flag */
		return HDLmMain.getJunitActive();
	}
  /* Check if a file type is binary or not. The caller provides the
     file suffix as a string (with no period). If the file types is
     recognized as binary, a Boolean true value is returned to the
     caller. If the file is recognized as a non-binary file, a
     Boolean false value is returned to the caller. If the file type
     can not be recognized, a null value is returned. */
  protected static Boolean isTypeBinary(String suffix) {
		if (suffix == null) {
		  String  errorText = "File suffix string passed to isTypeBinary is null";
		  throw new NullPointerException(errorText);
		}
  	/* Check for a binary file */
		if (suffix.equals("avi")  ||
		    suffix.equals("bmp")  ||
		    suffix.equals("gif")  ||
				suffix.equals("ico")  ||
				suffix.equals("jpg")  ||
				suffix.equals("jpeg") ||
				suffix.equals("mp3")  ||
				suffix.equals("png"))
		  return true;
		/* Check for some kind of character file */
		if (suffix.equals("css")  ||
		    suffix.equals("htm")  ||
				suffix.equals("html") ||
				suffix.equals("js")   ||
		  	suffix.equals("txt")  ||
        suffix.equals("text"))
		  return false;
		/* Report that the file type could not be recognized */
		return null;
  }
  /* Load an image from a URL (hopefully). This routine takes a URL
     and tries to get a image. The image is returned to the caller. */
  protected static Image loadImage(String urlStr) {
		if (urlStr == null) {
		  String  errorText = "URL string passed to loadImage is null";
		  throw new NullPointerException(errorText);
		}
		/* Change the URL string. This allows the URL to be sent over
		   the Internet. Blanks are converted to plus signs below. */
		String   urlStrMod = urlStr.replaceAll(" ", "+");
		/* Define a few variables */
		Image   image = null;
		URL     urlObjMod = null;
		/* Get a URL object from the URL string passed by the caller.
		   This operation could fail and throw an exception. We need
		   to catch and handle the exception. */
		try {
			urlObjMod = new URL(urlStrMod);
		}
		catch (MalformedURLException malformedException) {
			if (urlStrMod != null)
			  LOG.info("URL - " + urlStrMod);
			LOG.info("MalformedURLException while executing loadImage");
	 		LOG.info(malformedException.getMessage(), malformedException);
	 		HDLmEvent.eventOccurred("MalformedURLException");
	 		image = null;
	 		return image;
		}
		catch (Exception exception) {
			if (urlStrMod != null)
			  LOG.info("URL - " + urlStrMod);
			LOG.info("Exception while executing loadImage");
	 		LOG.info(exception.getMessage(), exception);
	 		HDLmEvent.eventOccurred("Exception");
	 		image = null;
	 		return image;
		}
		/* Try to read the URL and get the image from it. This operation
		   could fail and throw an exception. We need to catch and handle
		   the exception. */
		try {
			/* LOG.info("imageIO.read - " + urlStr); */
			image = ImageIO.read(urlObjMod);
			/* if (image == null) */
				/* LOG.info("imageIO.read - " + "Image is null"); */
		}
		catch (IOException ioException) {
			if (urlStrMod != null)
			  LOG.info("URL - " + urlStrMod);
			LOG.info("IOException while executing loadImage");
	 		LOG.info(ioException.getMessage(), ioException);
	 		HDLmEvent.eventOccurred("IOException");
	 		image = null;
	 		return image;
		}
		catch (Exception exception) {
			if (urlStrMod != null)
			  LOG.info("URL - " + urlStrMod);
			LOG.info("Exception while executing loadImage");
	 		LOG.info(exception.getMessage(), exception);
	 		HDLmEvent.eventOccurred("Exception");
	 		image = null;
	 		return image;
		}
		return image;
  }
  /* This log stack trace entry point assumes that we don't want
     to skip any stack frames */
  protected static void logStackTrace() {
  	logStackTrace(0);
  }
  /* This routine displays (logs) the current stack trace. The caller
     specifies the number of stack trace entries to be skipped. Zero
     means that all of the stack entries will be logged. One will
     skip the stack trace entry for the getStackTrace call. Two
     will skip the getStackTrace call and the call to this routine. */
  protected static void logStackTrace(int skipEntries) {
  	StackTraceElement[] elements = Thread.currentThread().getStackTrace();
  	for (int i = skipEntries; i < elements.length; i++) {
  	     StackTraceElement  s = elements[i];
  	     String   stackElememtClass = s.getClassName();
  	     String   stackElementMethod = s.getMethodName();
  	     String   stackElementFile = s.getFileName();
  	     int      stackElementLine = s.getLineNumber();
  	     String   stackFormat = "%s.%s(%s:%d)";
  	     String   stackElementStr = String.format(stackFormat, stackElememtClass,
  	    		                                                   stackElementMethod,
  	    		                                                   stackElementFile,
  	    		                                                   stackElementLine);
  	     LOG.info(stackElementStr);
  	}
  }
	/* This routine logs a string passed by the caller. The caller must
	   make sure that the string can be logged (is short enough). */
	protected static void  logString(String inStr, Logger passedLogger) {
		/* Check the values passed by the caller */
		if (inStr == null) {
		  String  errorText = "String passed to logString is null";
		  throw new NullPointerException(errorText);
		}
		/* Check if the caller passed a Logger */
		if (passedLogger == null) {
		  String  errorText = "Logger passed to logString is null";
		  throw new NullPointerException(errorText);
		}
		passedLogger.info(inStr);
	}
	/* This routine takes a string (possibly very long) and breaks
	   into parts (substrings). Each part is logged. */
	protected static void  logStringInParts(final String whereStr, final String inStr) {
		/* Check the values passed by the caller */
		if (whereStr == null) {
		  String  errorText = "Where string passed to logStringInParts is null";
		  throw new NullPointerException(errorText);
		}
		if (inStr == null) {
		  String  errorText = "Input string passed to logStringInParts is null";
		  throw new NullPointerException(errorText);
		}
		/* Set a few values for use later. These values make sure we always
		   use the correct part length. */
		int       partSize = 80;
		Integer   partOffset = 0;
		/* Break the passed string into an ArrayList of substrings and
		   log each one */
		ArrayList<String>   strArray = HDLmUtility.createArrayListOfStrings(inStr, partSize);
		/* Pass each part of the string passed by the caller to the string function */
		for (String  strEntry : strArray) {
			LOG.info(whereStr + " " + partOffset.toString() + " " + strEntry);
			partOffset += partSize;
		}
	}
  /* The next routine takes an input URL and removes the protocol
	   and the host name from it (if they are present). The returned
	   value is the path string followed by the search string followed
	   by the fragment string. Note that the host name (which is removed)
	   includes the port number, if any. */
	protected static String removeHost(String urlStr) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to removeHost is null";
			throw new NullPointerException(errorText);
		}
	  /* Check if the passed URL string has a colon in it. If it does
	     not have a colon or the colon is in the wrong place, then we
	     can just return the input string to the caller. */
		int   indexOfColon = urlStr.indexOf(':');
	  if (indexOfColon < 0 ||
	  		indexOfColon > 6)
	    return urlStr;
	  /* Build a URL object from the input string */
	  URL urlObj = null;
	  /* Try to create a new URL object from a URL string */
		try {
			urlObj = new URL(urlStr);
		}
		catch (MalformedURLException malformedException) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("MalformedURLException while executing removeHost");
	 		LOG.info(malformedException.getMessage(), malformedException);
	 		HDLmEvent.eventOccurred("MalformedURLException");
	 		HDLmAssertAction(false, "MalformedURLException while executing removeHost");
	 		return null;
		}
		catch (Exception exception) {
			if (urlStr != null)
			  LOG.info("URL - " + urlStr);
			LOG.info("Exception while executing removeHost");
	 		LOG.info(exception.getMessage(), exception);
	 		HDLmEvent.eventOccurred("Exception");
	 		HDLmAssertAction(false, "Exception while executing removeHost");
	 		return null;
		}
	  /* Return the part of the URL after the protocol, host name,
	     and port number */
		int   doubleSlashIndex = urlStr.indexOf("//");
		if (doubleSlashIndex < 0) {
			HDLmAssertAction(false, "Two slashes not found in the URL while executing removeHost");
		}
		urlStr = urlStr.substring(doubleSlashIndex+2);
		int   singleSlashIndex = urlStr.indexOf("/");
		if (singleSlashIndex < 0) {
			HDLmAssertAction(false, "Single slashes not found in the URL while executing removeHost");
		}
		urlStr = urlStr.substring(singleSlashIndex);
		return urlStr;
	}
  /* Remove a 'http:' or 'https:' prefix from a URL, if need be. If
     the URL starts with 'http:' or 'https:' the scheme is removed
     and the remaining URL is returned to the caller. If the URL does
     not start with 'http:' or 'https:', then the URL is not modified.
     Note that schemes such as 'file:' are ignored by this code. */
	protected static String removeHttpPrefix(String urlStr) {
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to removeHttpPrefix is null";
			throw new NullPointerException(errorText);
		}
		/* Remove the scheme (protocol) prefix as need be */
	  if (urlStr.startsWith("http:"))
	    return urlStr.substring(5);
	  else if (urlStr.startsWith("https:"))
	    return urlStr.substring(6);
	  else
	    return urlStr;
	}
	/* This routine runs a program in a process. The output is returned
	   to the caller. The response object has other fields for returning
	   the exit code and exception messages (if any). */
	protected static HDLmUtilityResponse  runProcess(final String commandLine,
			                                             final boolean reportErrorMessages) {
		/* Check a few values passed by the caller */
		if (commandLine == null) {
			String   errorText = "Command line string reference passed to runProcess is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		int     exitCode = 0;
		String  executeMessage = null;
		String  IOExceptionMessage = null;
		String  execStdErr = null;
		String  execStdOut = null;
		/* Create a new class instance for returning the results of this
		   method to the caller */
		HDLmUtilityResponse   response = new HDLmUtilityResponse();
		/* Parse the command line passed by the caller */
		CommandLine cmdLine = CommandLine.parse(commandLine);
		/* Build a few output handlers */
		ByteArrayOutputStream   stdErrStream = new ByteArrayOutputStream();
		ByteArrayOutputStream   stdOutStream = new ByteArrayOutputStream();
		PumpStreamHandler       streamHandler = new PumpStreamHandler(stdOutStream, stdErrStream);
	  /* Create an executor and capture standard output and standard error */
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
    /* Try to run the command line passed by the caller */
		try {
			exitCode = executor.execute(cmdLine);
		}
		catch (ExecuteException e) {
			/* Check if errors should be reported. This is not always
			   true. */
			if (reportErrorMessages) {
  			if (cmdLine != null)
			    LOG.info("Command Line - " + cmdLine);
			  LOG.info("ExecuteException while executing runProcess");
	 		  LOG.info(e.getMessage(), e);
			}
			executeMessage = e.getMessage();
			HDLmEvent.eventOccurred("ExecuteException");
			/* Check if the response block is NULL, just return
			   to the caller if the response block is NULL. The
			   execute message is returned in the response block. */
			if (response == null)
			  return null;
		}
		catch (IOException e) {
			/* Check if errors should be reported. This is not always
		     true. */
		  if (reportErrorMessages) {
			  if (cmdLine != null)
			    LOG.info("Command Line - " + cmdLine);
			  LOG.info("IOException while executing runProcess");
	 		  LOG.info(e.getMessage(), e);
		  }
			IOExceptionMessage = e.getMessage();
			HDLmEvent.eventOccurred("IOException");
			/* Check if the response block is NULL, just return
		     to the caller if the response block is NULL. The
		     IO exception message is returned in the response
		     block. */
			if (response == null)
			  return null;
		}
		/* We want to capture the output written to standard
		   error and standard output, if these streams are
		   available */
		if (stdErrStream != null)
			execStdErr = stdErrStream.toString();
		if (stdOutStream != null)
			execStdOut = stdOutStream.toString();
	  /* Store all of the output values in the utility response */
		response.setExitCode(exitCode);
		response.setStdErr(execStdErr);
		response.setStdOut(execStdOut);
		response.setExecuteMessage(executeMessage);
		response.setIOExceptionMessage(IOExceptionMessage);
    /* Return the final utility response to the caller */
		return response;
	}
	/* Set the current environment. This routine determines (or at least
  	 tries to determine) the current environment based on an environmental
	   variable (that may or may not be set). If the environmental variable is
	   set, the value is used to set the current environment. */
	protected static void  setCurrentEnvironment() {
		/* Get the string value for the current environment name. The
		   current environment name is something like 'prod', 'prodution',
		   or 'test' (all without the quotes). */
		String  curEnvNameString = HDLmDefines.getString("HDLMCURENV");
		if (curEnvNameString == null) {
			  String   errorFormat = "Define value for current environment name not found (%s)";
			  String   errorText = String.format(errorFormat, "HDLMCURENV");
			  HDLmAssertAction(false, errorText);
		}
		/* Try to get the value of the current environment variable */
		String  curEnvValue = HDLmUtility.getEnvironmentVariableUpper(curEnvNameString);
		/* Check if the environment variable value is set, if not then
		   we have no more work to do below. If this value is set, then
		   use it. */
		if (curEnvValue == null)
		  return;
		/* Check the value of the current environment. If the value
		   is 'test' (without the quotes), then set a few configuration
		   values showing that the 'test' environment is active. */
		if (curEnvValue.equals("TEST"))
		  HDLmUtility.setTestEnvironment();
  }
	/* This routine sets the current value of the Junit
     active flag. This flag is manually set. This flag is
     not automatically set. */ 
  protected static void  setJunitActive(final boolean junitActiveValue) {
    HDLmMain.setJunitActive(junitActiveValue);
  }
	/* Set the overall environment. This routine determines (or at least
	   tries to determine) if we are running in Docker container and what
	   the ultimate operating system type is. The ultimate operating system
	   is the parent operating system of the Docker container, if a Docker
	   container is active. */
	protected static void  setOverallEnvironment() {
		/* Get the string value for the current environment name. The
	     current environment name is something like 'prod', 'prodution',
  	   or 'test' (all without the quotes). */
	  String  curEnvNameString = HDLmDefines.getString("HDLMCURENV");
	  if (curEnvNameString == null) {
		  String   errorFormat = "Define value for current environment name not found (%s)";
		  String   errorText = String.format(errorFormat, "HDLMCURENV");
		  HDLmAssertAction(false, errorText);
	  }
	  /* Try to get the value of the current environment variable */
	  String  curEnvValue = HDLmUtility.getEnvironmentVariableUpper(curEnvNameString);
    /* Get the string value for the host system name. This value
       is only set under Docker (in a Docker container). The host
       name is something like javaproxya.dnsalias.com. */
    String  hostEnvNameString = HDLmDefines.getString("HDLMHOSTNAME");
    if (hostEnvNameString == null) {
      String   errorFormat = "Define value for host name not found (%s)";
      String   errorText = String.format(errorFormat, "HDLMHOSTNAME");
      HDLmAssertAction(false, errorText);
    }
    /* Get the name of the actual host system */
    String  hostNameValue = HDLmUtility.getEnvironmentVariableUpper(hostEnvNameString);
    /* Get the string value for the host operating system name. This value
       is only set under Docker (in a Docker container). The host operating
       system name is something like LINUX, MACOS, or WINDOWS. */
    String  hostEnvOsNameString = HDLmDefines.getString("HDLMHOSTOS");
	  if (hostEnvOsNameString == null) {
		  String   errorFormat = "Define value for host OS name not found (%s)";
		  String   errorText = String.format(errorFormat, "HDLMHOSTOS");
		  HDLmAssertAction(false, errorText);
	  }
	  /* Try to get the name of the host operating system */
	  String  hostOsValue = HDLmUtility.getEnvironmentVariableUpper(hostEnvOsNameString);
	  LOG.debug("In HDLmUtility.setOverallEnvironment");
	  LOG.debug(hostOsValue);
	  /* Check if any of a set of environment variables value are set,
       if not then we have more work to do below. If the host name
       value is set, then we use it. */
    if (curEnvValue   != null ||
 	    	hostNameValue != null ||
 		    hostOsValue   != null) {}
	  /* Check if we are running in a Docker container */
    if (isDockerActive()) {
	  	/* Show that a Docker container is active */
	  	HDLmMain.setDockerFlagActive(true);
	  	HDLmOsTypes  currentOsType = HDLmOsTypes.valueOfString(hostOsValue);
		  /* Store the parent operating system value */
		  HDLmMain.setOsType(currentOsType);
	  }
	  /* It look like we are not running in a container.
	     Set a few values for use later. */
	  else {
	  	/* Show that a Docker container is not active */
	  	HDLmMain.setDockerFlagActive(false);
	  	HDLmOsTypes           currentOsType = HDLmOsTypes.NONE;
			HDLmUtilityResponse   response;
			String                curLine = null;
	  	/* Check for Windows versus Unix (of some kind) */
	    curLine = "systeminfo";
		  response = HDLmUtility.runProcess(curLine, false);
		  /* Check the results from running the external program */
		  if (response.getExecuteMessage()     != null ||
		  		response.getIOExceptionMessage() != null) {
		  	currentOsType = HDLmOsTypes.LINUX;
		  }
		  else
		  	currentOsType = HDLmOsTypes.WINDOWS;
		  /* Set / store the current operating system type */
		  HDLmMain.setOsType(currentOsType);
	  }
	}
	/* Set the test environment. This is done by setting a set of
	   configuration values. These configuration values are checked
	   later to determine what database to update (for example). */
	protected static void  setTestEnvironment() {
		/* Set the current environment */
	  HDLmConfig.setConfigString("currentEnvironment", HDLmConfig.getString("currentEnvironmentTest"));
	  HDLmConfig.setConfigString("serverName", HDLmConfig.getString("serverNameTest"));
	}
	/* Set a system property to a value. The property name and value are
	   passed by the caller.  */
	protected static void  setProperty(final String propertyName, final String propertyValue) {
		/* Check the property name passed by the caller for a null value */
		if (propertyName == null) {
		  String  errorText = "Property name string passed to setProperty is null";
		  throw new NullPointerException(errorText);
		}
		/* Check the property value passed by the caller for a null value */
		if (propertyValue == null) {
		  String  errorText = "Property value string passed to setProperty is null";
		  throw new NullPointerException(errorText);
		}
		/* Set the system property */ 
		System.setProperty(propertyName, propertyValue);
	}
  /* This routine processes a string and splits it into a set of
     lines. The caller provides the input string and the suffix
     used to split the input string into lines. The suffix string
     must be at least one character long. */
	protected static ArrayList<String> splitLinesSuffix(String inString, String suffix) {
		if (inString == null) {
		  String  errorText = "Input string passed to splitLinesSuffix is null";
		  throw new NullPointerException(errorText);
		}
		if (suffix == null) {
		  String  errorText = "Suffix string passed to splitLinesSuffix is null";
		  throw new NullPointerException(errorText);
		}
		if (suffix.length() == 0) {
		  HDLmAssertAction(false, "Suffix string passed to splitLinesSuffix is empty");
		}
	  ArrayList<String>  outArrayList = new ArrayList<String>();
	  int                inStringIndex;
	  int                inStringLen = inString.length();
	  int                suffixLen = suffix.length();
	  int                inStringPos = 0;
	  String             newLine;
	  /* Check if the input string is an empty string. If this is true then
	     we don't want to add any lines (including zero-length lines) to the
	     output string array. */
	  if (inStringLen == 0)
	  	return outArrayList;
	  /* Process the input string from left to right. Keep moving
	     the current position to the right as additional lines are
	     extracted from the input string. */
	  while (inStringPos < inStringLen) {
	  	inStringIndex = inString.indexOf(suffix, inStringPos);
	  	/* Check if the search for the suffix failed. Just use the
	  	   rest of the input string as the next (and last) line
	  	   in this case. Add the next (and last) line to the
	  	   output array. */
	  	if (inStringIndex == -1) {
	  		newLine = inString.substring(inStringPos);
	  		outArrayList.add(newLine);
	  		break;
	  	}
	  	/* Get the next line from the input string and add it to
	  	   the output array. Skip past the next line. */
	  	newLine = inString.substring(inStringPos, inStringIndex);
	  	outArrayList.add(newLine);
	  	inStringPos = inStringIndex + suffixLen;
	  }
		return outArrayList;
	}
  /* This routine processes a list of lines. Each line is trimmed
     on the right for any trailing whitespace. The updated line
     is added to the output array. The original array and the lines
     in the original array are not modified. */
	protected static ArrayList<String> trimLinesRight(ArrayList<String> inLines) {
		if (inLines == null) {
		  String  errorText = "Input lines array passed to trimLinesRight is null";
		  throw new NullPointerException(errorText);
		}
		/* Create that output list for the lines */
		ArrayList<String>   outLines = new ArrayList<String>();
		String              outLine;
    for (String inLine : inLines) {
    	outLine = StringUtils.stripEnd(inLine, null);
    	outLines.add(outLine);
    }
		return outLines;
  }
	/* This routine updates the perceptual hash cache, if possible.
	   The cache maps URL strings to perceptual hash values using
	   a cache. Of course, this is vastly faster than downloading
	   an image and calculating a perceptual hash from the image.
	   The URL passed to this routine may or may not be a data
     URL. If it not a data URL, then it may start with two forward
     slash characters without an HTTP/HTTPS protocol in front of the
     two forward slash characters.*/
	protected static void  updatePHashCache(String urlStr, String pHashHex) {
		if (urlStr == null) {
		  String  errorText = "URL value passed to updatePHashCache is null";
		  throw new NullPointerException(errorText);
		}
		if (pHashHex == null) {
		  String  errorText = "Perceptual hash value passed to updatePHashCache is null";
		  throw new NullPointerException(errorText);
		}
		/* LOG.info("In updatePHashCache"); */
		/* LOG.info(urlStr); */
		HDLmPHashCache.put(urlStr, pHashHex);
	}
}
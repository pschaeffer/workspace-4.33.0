package com.headlamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmLogMsg short summary.
 *
 * The HDLmLogMsg class is not used to create any objects. However, it 
 * does contain some of the static methods used for error and non-error 
 * logging. The standard error numbers are listed in a separate source 
 * module. 
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmLogMsg {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmLogMsg.class);  
	/* This class can never be instantiated */
	private HDLmLogMsg() {}
	/* This is the standard log message function. The caller provides the 
	   severity, type, number, and log message text. Note that this routine
	   returns the final log message to the caller. This change was made to
	   facilitate automated testing. */
	protected static String buildLogMsg(HDLmLogLevels severity, 
			                                String type, 
                                      int number, 
                                      String text) {
		return buildLogMsg(severity, 
				               type, 
				               number, 
				               text, 
				               HDLmReportErrors.REPORTERRORS);
	}
	protected static String buildLogMsg(HDLmLogLevels severity, 
			                                String type, 
			                                int number, 
			                                String text, 
			                                HDLmReportErrors reportMessages) {
		HDLmLogLevels   internalSeverity;
		String          logMsgStr = "";
		/* We would like to test for and reject null values passed to this routine.
		   However, this is a logging routine. It should probably not fail when it
		   is needed to log messages. */
		String   internalType;
		String   internalText;
		/* Check if the severity value is null */
		if (severity == null) {
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Null Value", 26, "severity");
			internalSeverity = HDLmLogLevels.FATAL;
		} 
		else
			internalSeverity = severity;
		/* Check if the type value is null */
		if (type == null) {
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Null Value", 26, "type");
			internalType = "Null Type";
		} 
		else
			internalType = type;
		/* Check if the text value is null */
		if (text == null) {
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Null Value", 26, "text");
			internalText = "Null Text";
		} 
		else
			internalText = text;
		/* Check if the error reporting value is null */
		if (reportMessages == null) {
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Null Value", 26, "reportMessages");
			reportMessages = HDLmReportErrors.REPORTERRORS;
		}
		/* Check if the error reporting value is not set */
		if (reportMessages == HDLmReportErrors.NONE) {
			HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Not Set", 33, "reportMessages");
			reportMessages = HDLmReportErrors.REPORTERRORS;
		}
		/* Build the log message string using the values passed by the caller
		   or substitute values if the caller passed null values. */
		logMsgStr += HDLmDefines.getString("HDLMPREFIX") + " ";
		logMsgStr += internalType + " ";
		logMsgStr += Integer.toString(number) + " ";
		logMsgStr += internalText;
		/* Only actually log the message if a flag is set. This flag 
		   is true by default. It is set to false for unit testing. */
		if (reportMessages == HDLmReportErrors.REPORTERRORS) {
			switch (internalSeverity) {
			  /* The logging framework currently in use, has no SEVERE or 
			     FATAL level. Hence we must use the error level to log
			     these messages. */
				case FATAL:
					LOG.error(logMsgStr);
					break;
				case ERROR:
					LOG.error(logMsgStr);
					break;
				case WARN:
					LOG.warn(logMsgStr);
					break;
				case INFO:
					LOG.info(logMsgStr);
					break;
				case DEBUG:
					if (LOG.isDebugEnabled()) 
					  LOG.debug(logMsgStr);
					break;
				case TRACE:
					LOG.trace(logMsgStr);
					break;
				default:
					HDLmLogMsg.buildLogMsg(HDLmLogLevels.FATAL, 
							                   "Invalid Log Level", 
							                   34, 
							                   internalSeverity.toString());
					break;
			}
		}
		return logMsgStr;
	}
}
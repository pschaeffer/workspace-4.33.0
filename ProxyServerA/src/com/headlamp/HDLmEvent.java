package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.math.BigIntegerMath;
import com.google.gson.Gson;
/**
 * HDLmEvent short summary.
 *
 * HDLmEvent description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class
   can definitely be created */
public class HDLmEvent {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmEvent.class);  	
	/* Define the map that contains all of the event 
     values */
  private static Map<String, HDLmEvent>  eventsValues = new TreeMap<String, HDLmEvent>(); 	
	/* The list of general event names is stored below. These event names are 
     added to the events map and used to track event occurrences. They are 
     also use for event logging. These names should not be changed without
     finding all uses for a specific event name and changing it. */ 
  public static final ArrayList<String>  eventNames = new ArrayList<String>(
	  List.of("Aaaa1", "Aaaa2", "Bbbb", "Cccc", "Dddd",            /*  0-4 */
	  		    "Eeee1", "Eeee2", "Eeee3",                           /*  5-7  */
	  		    "Ffff", "JavaScript Exception", "Inbound Request",   /*  8-10 */
	  		    "Exception", "EofException",                         /* 11-12 */
	  		    "EOFException",                                      /* 13-13 */
	  		    "IOException",                                       /* 14-14 */
	  		    "MalformedJsonException",                            /* 15-15 */
	  		    "MalformedURLException",                             /* 16-16 */
	  		    "FileNotFoundException", "NoSuchFileException",      /* 17-18 */
	  		    "NumberFormatException", "PatternSyntaxException",   /* 19-20 */
	  		    "UnsupportedEncodingException", "AssertionError",    /* 21-22 */
	  		    "ExecuteException", "SQLException",                  /* 23-24 */
	  		    "ScriptException, EvaluatorException"));             /* 25-26 */
  /* The map below is used to convert event names to event numbers. This map
     is constructed as part of product startup. All of the event names listed
     above are added to map. */ 
  public static Map<String, Integer>  eventNumbers = new HashMap<String, Integer>();
  /* This constructor takes a new event name and builds a new event 
     instance */ 
  protected HDLmEvent(final HDLmEventTypes newEventType,
  		                final String newEventName) {
		/* Check if the new event type passed by the caller is null */
		if (newEventType == null) {
			String  errorText = "New event type string passed to HDLmEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new event name passed by the caller is null */
		if (newEventName == null) {
			String  errorText = "New event name string passed to HDLmEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few fields in the new event instance */
		eventType = newEventType;
		eventName = newEventName;  	
		/* Fill the array with null values */
		Integer   lastArraySize = HDLmDefines.getNumber("HDLMLASTARRAYSIZE");
		if (lastArraySize == null) {
			String   errorFormat = "Define value for last array size number not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMLASTARRAYSIZE");
			HDLmAssertAction(false, errorText);		    	
		}
		eventLastArray = new Instant[lastArraySize]; 
		for (int i = 0; i < lastArraySize; i++)
			eventLastArray[i] = null;
  }
  /* This constructor takes a set of company, division, site, and modification
     names and builds a new event instance */ 
	protected HDLmEvent(final HDLmEventTypes newEventType,
			                final String newEventCompany,
			                final String newEventDivision,
			                final String newEventSite,
			                final String newEventModification) {
		/* Check if the new event type passed by the caller is null */
		if (newEventType == null) {
			String  errorText = "New event type string passed to HDLmEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new company name passed by the caller is null */
		if (newEventCompany == null) {
			String  errorText = "New company name string passed to HDLmEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new division name passed by the caller is null */
		if (newEventDivision == null) {
			String  errorText = "New division name string passed to HDLmEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new site name passed by the caller is null */
		if (newEventSite == null) {
			String  errorText = "New site name string passed to HDLmEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new modification name passed by the caller is null */
		if (newEventModification == null) {
			String  errorText = "New modification name string passed to HDLmEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Set a few fields in the new event instance */
		eventType = newEventType;
		eventDomain = newEventCompany;  	
		eventDivision = newEventDivision;
		eventSite = newEventSite;
		eventRule = newEventModification;
		eventName = newEventCompany + "/" + newEventDivision + "/" +
		            newEventSite + "/" + newEventModification;
		/* Fill the array with null values */
		Integer   lastArraySize = HDLmDefines.getNumber("HDLMLASTARRAYSIZE");
		if (lastArraySize == null) {
			String   errorFormat = "Define value for last array size number not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMLASTARRAYSIZE");
			HDLmAssertAction(false, errorText);		    	
		}
		eventLastArray = new Instant[lastArraySize]; 
		for (int i = 0; i < lastArraySize; i++)
			eventLastArray[i] = null;
	}
	/* All events have a type. The type of each events is 
	   stored in the field below. */ 
  private HDLmEventTypes  eventType = null;
	/* The event name can be anything. It could be a file name.
	   It could be a URL name. In most (but not all) cases, the
	   event name will be a rule name. The concept is that 
	   eventually, the event name for rules will have the 
	   domain name, division name, site name, and rule name 
	   in it. In all cases, the overall event name will be 
	   a string. */
	private String          eventName = null;
	/* Each rule event is associated with a specific domain name
	   (company name). The company name is stored in the field 
	   below. */
	private String          eventDomain = null;
	/* Each rule event is associated with a specific division name
     The division name is stored in the field below. */
  private String          eventDivision = null;
	/* Each rule event is associated with a specific site name
     The site name is stored in the field below. */
  private String          eventSite = null;
  /* Each rule event is associated with a specific rule name.
     The rule name (modification name) is stored in the field
     below. */
  private String          eventRule = null;
  /* An event can occur zero or more times. The number of times
     might be quite large if the server stays up for a long period
     of time and the event occurs at a high rate. */
  private long            eventCount = 0;
  /* The next field contains the parameter number associated 
     with an event. Events really don't have parameter numbers.
     This field is used to store the last parameter number 
     associated with this event. */ 
  private Integer         eventParameter = -1;
  /* Each time an event occurs, the event timestamp is updated.
     This value will be null, if the event has never occurred.
     This value will be set to a GMT timestamp each time the
     event occurs. */
  private Instant         eventLast = null;
  /* Each time an event occurs, the event timestamp (an Instant)
     is added to the array below. This is a wraparound array.
     This means that if the array below has 100 entries, then
     the first event would use entry 0. The 100th event would 
     use entry 99 and the 101st event would reuse entry 0. */
  private Instant[]       eventLastArray = null;  
  /* The value below is calculated from the contents of the last
     events array. Of course, the last events array must have at
     least two entries for this calculation to be done. This is
     a value in nanoseconds. Of course, division is required to
     get a value that can be presented. */ 
  private BigInteger      eventMean = new BigInteger("0");
  /* The value below is calculated from the contents of the last
     events array. Of course, the last events array must have at
     least three entries for this calculation to be done. If the 
     last events array only has two entries, then the standard
     deviation will be zero. A non-zero standard deviation
     requires at least three entries. This is a value in nanoseconds.
     Of course, division is required to get a value that can be
     presented.*/ 
  private BigInteger      eventStandardDeviation = new BigInteger("0");
  /* This routine adds an event to the events map. Note that this
     routine can be called more than once with the same event name.
     If a duplicate event name is passed, it will just be ignored.
     This is not an error condition. */ 
  @SuppressWarnings("unused")
	protected static boolean  addEvent(final HDLmEventTypes newEventType,			
			                               final String newEventName) {
		/* Check if the new event type passed by the caller is null */
		if (newEventType == null) {
			String  errorText = "New event type string passed to addEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new event name passed by the caller is null */
		if (newEventName == null) {
			String  errorText = "New event name string passed to addEvent is null";
			throw new NullPointerException(errorText);
		}
  	boolean   eventAdded = false;
  	/* Check if the event has already been added */
  	if (eventsValues.containsKey(newEventName))
  		return eventAdded;
  	/* Create the new event instance */
  	HDLmEvent  newEvent = new HDLmEvent(newEventType, newEventName);
    if (newEvent == null)	{   
  	  String   errorText = "New event reference obtained from HDLmEvent is null";
  		HDLmAssertAction(false, errorText);		    	
    }
    /* Add the new event to the events map */
    eventsValues.put(newEventName, newEvent);
  	return eventAdded;
  }
  /* This routine adds an event to the events map. Note that this
	   routine can be called more than once with the same event name.
	   If a duplicate event name is passed, it will just be ignored.
	   This is not an error condition. */ 
  @SuppressWarnings("unused")
	protected static boolean  addEvent(final HDLmEventTypes newEventType,			
			                               final String newEventCompany,
			                               final String newEventDivision,
			                               final String newEventSite,
			                               final String newEventModification) {
		/* Check if the new event type passed by the caller is null */
		if (newEventType == null) {
			String  errorText = "New event type string passed to addEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new event company passed by the caller is null */
		if (newEventCompany == null) {
			String  errorText = "New event company string passed to addEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new event division passed by the caller is null */
		if (newEventDivision == null) {
			String  errorText = "New event division string passed to addEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new event site passed by the caller is null */
		if (newEventSite == null) {
			String  errorText = "New event site string passed to addEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the new event modification passed by the caller is null */
		if (newEventModification == null) {
			String  errorText = "New event modification string passed to addEvent is null";
			throw new NullPointerException(errorText);
		}
		/* Assume that the current event will not be added */
		boolean   eventAdded = false;
		/* Build the event name from the values passed by the caller */
		String  eventName = newEventCompany + "/" + newEventDivision + "/" +
                        newEventSite + "/" + newEventModification;
		/* Check if the event has already been added */
		if (eventsValues.containsKey(eventName))
			return eventAdded;
		/* Create the new event instance */
		HDLmEvent  newEvent = new HDLmEvent(newEventType, newEventCompany,
				                                newEventDivision, newEventSite, 
				                                newEventModification);
	  if (newEvent == null)	{   
		  String   errorText = "New event reference obtained from HDLmEvent is null";
			HDLmAssertAction(false, errorText);		    	
	  }
	  /* Add the new event to the events map */
	  eventsValues.put(eventName, newEvent);
		return eventAdded;
	}
	/* Add a set of general events to the events map */
	protected static void  addEventsList(final ArrayList<String> eventNames) {
		/* Check if the event names array passed by the caller is null */
		if (eventNames == null) {
			String  errorText = "Event names array passed to addEventsList is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the event names array is empty */
		if (eventNames.size() == 0) {
			String  errorText = "Event names array passed to addEventsList is empty";			 
			throw new IllegalArgumentException(errorText);
		}
		/* Set a few local variables */
		int   numberOfEvents = eventNames.size();
		/* Process each of the event names. Add each of them to the events
		   map. */ 
		Integer  eventCounter = 0;
		for (String eventName : eventNames) {
			HDLmEvent.addEvent(HDLmEventTypes.GENERAL, eventName);			
			/* Add the current event name to the map that can be used
			   to convert event names to event numbers */
			eventNumbers.put(eventName,  eventCounter);
			eventCounter += 1;			 
		}		
	} 
  /* Calculate a set of event statistics. The event statistics are 
     stored in the event instance (for use by other routines). The
     event statistics can only be calculated if we have at least 
     two event occurrences. The event statistics are calculated 
     using the contents of the last event array.   */ 
  public void            calculateEventStatistics() {
  	/* Declare and define a few variables */
  	BigInteger  accumulatedNanos                 = new BigInteger("0");
  	BigInteger  accumulatedNanosSquared          = new BigInteger("0");
  	BigInteger  eventMeanSquared                 = new BigInteger("0");
  	BigInteger  eventSumOfSquaresDividedByNumber = new BigInteger("0");
  	BigInteger  eventVariance                    = new BigInteger("0");
  	BigInteger  intervalNanosSquared             = new BigInteger("0");
  	Duration    intervalDuration;
  	Instant     intervalEnd;
  	Instant     intervalStart;
  	int         indexEnd;
  	int         indexStart;
  	int         numberOfIntervals;
  	int         startingCount;
  	Integer     lastArraySize = null;
  	long        intervalNanos;
  	long        intervalSeconds;
  	/* Try to get the last array size */
  	lastArraySize = HDLmDefines.getNumber("HDLMLASTARRAYSIZE");
		if (lastArraySize == null) {
			String   errorFormat = "Define value for last array size number not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMLASTARRAYSIZE");
			HDLmAssertAction(false, errorText);		    	
		}
  	/* Get the starting count value */
  	if (eventCount <= 1) {
  		return;
  	}
  	else if (eventCount <= lastArraySize) {
  		startingCount = 0;
  		numberOfIntervals = (int) (eventCount - 1);
  	}
  	else {
  		startingCount = (int) (eventCount - lastArraySize);
  		numberOfIntervals = lastArraySize - 1;
  	}
  	/* Process all of the intervals between events */
  	for (int i = startingCount; i < eventCount-1; i++) {
  		boolean   skipDuration = false;
  		indexStart = i % lastArraySize;
  		indexEnd = (i + 1) % lastArraySize;
  		intervalStart = eventLastArray[indexStart];
  		intervalEnd = eventLastArray[indexEnd];
  		if (intervalEnd == null) {
  			LOG.info("intervalEnd is null");
  			LOG.info(String.valueOf(startingCount));
  			LOG.info(String.valueOf(numberOfIntervals));
  			LOG.info(String.valueOf(eventCount));
  			LOG.info(String.valueOf(lastArraySize));
  			LOG.info(String.valueOf(i));
  			LOG.info(String.valueOf(indexStart));
  			LOG.info(String.valueOf(indexEnd));
  			skipDuration = true;
  		}
  		if (intervalStart == null) {
  			LOG.info("intervalStart is null");
  			LOG.info(String.valueOf(startingCount));
  			LOG.info(String.valueOf(numberOfIntervals));
  			LOG.info(String.valueOf(eventCount));
  			LOG.info(String.valueOf(lastArraySize));
  			LOG.info(String.valueOf(i));
  			LOG.info(String.valueOf(indexStart));
  			LOG.info(String.valueOf(indexEnd));
  			skipDuration = true;
  		}
  		/* Check if we should skip duration processing */
  		if (skipDuration == true)
  			continue;
  		intervalDuration = Duration.between(intervalStart, intervalEnd);
	    /* Get the total number of nanoseconds for the interval between 
         two events. This requires adding the actual number of
         nanoseconds to the seconds value (the seconds value is 
         multiplied by 1000000000). */ 
      intervalNanos = (long) intervalDuration.getNano();
      intervalSeconds = intervalDuration.getSeconds();
      if (intervalSeconds > 0)
	      intervalNanos += (intervalSeconds * 1000000000);
      accumulatedNanos = accumulatedNanos.add(BigInteger.valueOf(intervalNanos));
      intervalNanosSquared = BigInteger.valueOf(intervalNanos).multiply(BigInteger.valueOf(intervalNanos));
  	  accumulatedNanosSquared = accumulatedNanosSquared.add(intervalNanosSquared);
  	}  	
  	/* Get a big integer with the average (mean) time interval */
  	eventMean = accumulatedNanos.divide(BigInteger.valueOf(numberOfIntervals));
  	/* Get a big integer with the average event interval time squared */
  	eventMeanSquared = eventMean.multiply(eventMean);
  	/* Get the sum of the squares, divided by the number of measurements */
  	eventSumOfSquaresDividedByNumber = accumulatedNanosSquared.divide(BigInteger.valueOf(numberOfIntervals));
  	/* Get the variance (standard deviation squared) from the values so far */
  	eventVariance = eventSumOfSquaresDividedByNumber.subtract(eventMeanSquared);
  	eventStandardDeviation = BigIntegerMath.sqrt(eventVariance, RoundingMode.HALF_DOWN);  	
  }
	/* This routine returns either true or false. This routine returns
	   the events added flag. This flag will be false, if all of the 
	   events have not been added to the events map. This flag will be
	   true if all of the events have been added to the events map. */  
  protected static boolean  checkEventsAdded() {
	  return (HDLmState.checkString("eventsAdded") != null &&
		        HDLmStateInfo.getEventsAdded().equals("yes"));
  }
	/* Check for anomalies for the current event. We decide that an 
	   anomaly has been found if the time since that last event is 
	   greater than the average time interval plus four times the
	   standard deviation. */
	protected boolean      checkForAnomaly() {
		BigInteger  eventAnomalyValue = new BigInteger("0");
		boolean     anomalyFound = false;
		long        intervalNanos;
		long        intervalSeconds;
		/* Check if we have had at least three events so far. We
		   need at least three events, to get at least two intervals,
		   so the standard deviation will have some meaning. */
		if (eventCount < 3)
			return false;
		/* Check if the last event value is set */
		if (eventLast == null)
			return false;
		/* Check if the interval since the last event is out-of-bounds */
		Duration  durationSinceLast = Duration.between(eventLast, Instant.now());
	  intervalNanos = (long) durationSinceLast.getNano();
	  intervalSeconds = durationSinceLast.getSeconds();
	  if (intervalSeconds > 0)
	    intervalNanos += (intervalSeconds * 1000000000);
	  /* Recalculate all of the event statistics */
	  calculateEventStatistics();
	  eventAnomalyValue = eventMean.add(eventStandardDeviation.multiply(BigInteger.valueOf(4)));
	  if (intervalNanos > eventAnomalyValue.longValue())
	 	  return true;
	  return false;		
	}
  /* Note that an event has occurred. Update the event instance for 
     the current event. */ 
	public static void  eventOccurred(final String eventKey) {
		/* Check if the event key string passed by the caller is null */
		if (eventKey == null) {
			String  errorText = "Event key string passed to eventOccurred is null";
			throw new NullPointerException(errorText);
		}
		/* Get a reference to the map of events information */
		var  eventsMap = HDLmEvent.getEventsMap();
		if (eventsMap == null) {
			String   errorText = "Events map reference obtained from getEventsMap is null";
			HDLmAssertAction(false, errorText);		    	
    }
		/* Check if all of the events have been added to the events
		   map. If not, we can just return at this point. No further
		   work is possible. */ 
		var  eventsAdded = HDLmEvent.checkEventsAdded();
		if (!eventsAdded)
			return;
		/* Make sure the events map has the current event */
		if (!eventsMap.containsKey(eventKey)) {
			String  errorFormat = "Events map does not have current event (%s)"; 
			String  errorText = String.format(errorFormat,  eventKey);
			HDLmAssertAction(false, errorText);					
		}
		/* Get the event from the events map */
		HDLmEvent   curEvent = eventsMap.get(eventKey);
    /* Get the current instant */
    Instant  instant = Instant.now();   
		/* Update the event as need be */
		curEvent.incrementCount();
	  curEvent.setIsoTimestamp(instant);
    /* Add the current event to the array of events. What this
       routine really does is to add the current event instant
       to the array of instant values. */ 
	  curEvent.storeCurrentEventInstant(instant);
	  /* Store the updated event back in the events map */
	  eventsMap.put(eventKey, curEvent);		
	}
  /* Note that an event has occurred. Update the event instance for 
 	   the current event. */ 
	protected static void  eventOccurred(final String hostName,
			                                 final String divisionName,
			                                 final String siteName,
			                                 final String ruleName,
			                                 final Integer parameterNumber) {
		/* Check if the host name passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to eventOccurred is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the division name passed by the caller is null */
		if (divisionName == null) {
			String  errorText = "Division name string passed to eventOccurred is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the site name passed by the caller is null */
		if (siteName == null) {
			String  errorText = "Site name string passed to eventOccurred is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the rule name passed by the caller is null */
		if (ruleName == null) {
			String  errorText = "Rule name string passed to eventOccurred is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the parameter number passed by the caller is less than zero */
		/* The above comment is not quite correct. We now use -1 as a parameter 
		   number to show that parameters are not in use (index values are used
		   instead). This check has been modified accordingly. */ 
		if (parameterNumber != null && 
		    parameterNumber < -1) {
			String  errorFormat = "Parameter number passed to eventOccurred is less than minus 1 (%d)";
			String  errorText = String.format(errorFormat,  parameterNumber); 
			throw new IllegalArgumentException(errorText);
		}
		/* Get a reference to the map of events information */
		var  eventsMap = HDLmEvent.getEventsMap();
		if (eventsMap == null) {
			String   errorText = "Events map reference obtained from getEventsMap is null";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Check if all of the events have been added to the events
	     map. If not, we can just return at this point. No further
	     work is possible. */ 
    var  eventsAdded = HDLmEvent.checkEventsAdded();
	  if (!eventsAdded)
		  return;
		/* Build the event name from the host name, division name, site name, and
		   rule name */
		String  eventName = hostName + "/" + divisionName + "/" +
                        siteName + "/" + ruleName;
		/* Make sure the events map has the current event */
		if (!eventsMap.containsKey(eventName)) {
			String  errorFormat = "Events map does not have current event (%s)"; 
			String  errorText = String.format(errorFormat,  eventName);
			HDLmAssertAction(false, errorText);					
		}
		/* Get the event from the events map */
		HDLmEvent   curEvent = eventsMap.get(eventName);
    /* Get the current instant */
    Instant  instant = Instant.now();
		/* Update the event as need be */
		curEvent.incrementCount();
	  curEvent.setIsoTimestamp(instant);
	  /* Set the parameter number. Note that this is the parameter
	     number of the latest occurrence of this event. The parameter
	     number could change over time. The only value that will be
	     saved is the very latest parameter number. */
	  if (parameterNumber == null)
	  	curEvent.setParameterNull();
	  else
	    curEvent.setParameter(parameterNumber);
    /* Add the current event to the array of events. What this
       routine really does is to add the current event instant
       to the array of instant values. */ 
    curEvent.storeCurrentEventInstant(instant);
	  /* Store the updated event back in the events map */
	  eventsMap.put(eventName, curEvent);		
	}
	/* Note that a JavaScript exception event has occurred. Update the 
	   JavaScript exception event instance for the current exception. */ 
	protected static void  eventOccurredJavaScriptException(final String hostName,
			                                                    final String divisionName,
			                                                    final String siteName,
			                                                    final String ruleName) {
		/* Check if the host name passed by the caller is null */
		if (hostName == null) {
			String  errorText = "Host name string passed to eventOccurredJavaScriptException is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the division name passed by the caller is null */
		if (divisionName == null) {
			String  errorText = "Division name string passed to eventOccurredJavaScriptException is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the site name passed by the caller is null */
		if (siteName == null) {
			String  errorText = "Site name string passed to eventOccurredJavaScriptException is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the rule name passed by the caller is null */
		if (ruleName == null) {
			String  errorText = "Rule name string passed to eventOccurredJavaScriptException is null";
			throw new NullPointerException(errorText);
		}
		/* Get a reference to the map of events information */
		var  eventsMap = HDLmEvent.getEventsMap();
		if (eventsMap == null) {
			String   errorText = "Events map reference obtained from getEventsMap is null";
			HDLmAssertAction(false, errorText);		    	
	  }
		/* Set the event name */
		String  eventName = "JavaScript Exception";
		/* Make sure the events map has the current event */
		if (!eventsMap.containsKey(eventName)) {
			String  errorFormat = "Events map does not have current event (%s)"; 
			String  errorText = String.format(errorFormat,  eventName);
			HDLmAssertAction(false, errorText);					
		}
		/* Get the event from the events map */
		HDLmEvent   curEvent = eventsMap.get(eventName);
	  /* Get the current instant */
	  Instant  instant = Instant.now();
		/* Update the event as need be */
		curEvent.incrementCount();
	  curEvent.setIsoTimestamp(instant);
	  /* Add the current event to the array of events. What this
       routine really does is to add the current event instant
       to the array of instant values. */ 
    curEvent.storeCurrentEventInstant(instant);
	  /* Set a few exception related values */
	  curEvent.setHostName(hostName);
	  curEvent.setDivisionName(divisionName);
	  curEvent.setSiteName(siteName);
	  curEvent.setRuleName(ruleName);
	  /* Store the updated event back in the events map */
	  eventsMap.put(eventName, curEvent);		
	}
  /* Get the count value for an event */
  protected long         getCount() {
  	return eventCount;
  }
  /* Get the division name for an event */
  protected String       getDivisionName() {
  	return eventDivision;
  }
  /* This routine returns a set of heading suitable for all of
     the uses of an event */
	public static ArrayList<String>  getEventsHeadings(final String overallName) {
		/* Declare and define a few values */
		ArrayList<String>  headings = null;
    /* Check if we need headings for all of the events of just one event */
		if (overallName == null)
		  headings = new ArrayList<String>(List.of("Event<br/>Name", "Event<br/>Type", 
																			         "Event<br/>Count", "Parameter<br/>Number", 
																			         "Last Event<br/>Timestamp", 
																			         "Time Since<br/>Last Event",
																			         "Event Rate<br/>Average",
																			         "Event Rate<br/>Standard Deviation"));	
		/* We need to return headings for just one event */
		else
		  headings = new ArrayList<String>(List.of("Event<br/>Count", "Parameter<br/>Number", 
																			         "Last Event<br/>Timestamp", 
																			         "Time Since<br/>Last Event",
																			         "Event Rate<br/>Average",
																			         "Event Rate<br/>Standard Deviation"));				
		return headings;  	
	}
  /* Get the average event rate for an event */ 
  protected double       getEventRateAverage() { 	
  	/* Get the big integer with the average (mean) time interval */
  	calculateEventStatistics();
  	if (eventMean.equals(BigInteger.ZERO))
  		return -1.0;
  	return 1000000000.0 / eventMean.longValue(); 
  }
  /* Get the standard deviation for an event rate */ 
  protected double       getEventRateStd() {
  	/* Get a big integer with the time interval standard deviation */
  	calculateEventStatistics();
  	if (eventMean.equals(BigInteger.ZERO))
  		return -1.0;
  	return eventStandardDeviation.longValue() / 1000000000.0;
  }
  /* Return a reference to the main events map */
  protected static Map<String, HDLmEvent>  getEventsMap() {
  	return eventsValues;
  }
  /* This routine builds an ArrayList of event/events status 
     information. Some callers use this information directly.
     Other callers extract the information they need. */
	public static ArrayList<String>  getEventsStatus(final TreeSet<HDLmEvent> eventsSet,
			                                             final HDLmGetStatus informationType) {
		/* Declare and define a few variables */
	  boolean   encodingException = false;
		/* Build a (short) list of the event/events status information */			
	  var  statusListContents = new ArrayList<String>();
		/* Process each event in the events set. Note that the events
		   set is in the desired order. */ 
	  for (HDLmEvent curEvent : eventsSet) {
	  	/* Get a few values from the current event */
	  	double          eventRateAverage = curEvent.getEventRateAverage();
	  	double          eventRateStd = curEvent.getEventRateStd();
	  	HDLmEventTypes  eventType = curEvent.getType();
	  	Integer         eventParameter = curEvent.getParameter();
	  	long            eventCount = curEvent.getCount();
	  	String          eventName = curEvent.getName();
	  	String          eventTimestamp = curEvent.getTimestamp(); 
	  	String          eventTypeStr = eventType.toPrint();  
	  	/* Check if we are trying to get HTML information or not */
	  	if (informationType == HDLmGetStatus.HTMLINFORMATION) {
		  	/* Add the current event style. The style will only 
		  	   set to a 'useful' value if an anomaly is detected. */
		  	if (eventTimestamp != null) {
		  		boolean   anomalyFound = curEvent.checkForAnomaly();
		  		if (anomalyFound)
		  		  statusListContents.add("background-color: red");
		  		else
		  			statusListContents.add(null);
		  	}
		  	else
		  		/* Add a null value as the event style */
		  		statusListContents.add(null);
	    }
	  	/* Check if we are trying to get HTML information or not */
	  	if (informationType == HDLmGetStatus.HTMLINFORMATION) {
		  	/* Add the current event name and type */
		  	statusListContents.add(eventName);
		  	statusListContents.add(eventTypeStr);
	  	}
	  	/* Check if we actually have an event timestamp */
	  	if (eventTimestamp != null) {
	  		/* Add the event count */
	  		String  eventCountStr = String.valueOf(eventCount);
	  		statusListContents.add(eventCountStr);
	  		/* Add the event parameter number, if any */
	  		String  eventParameterStr = null;
	  		if (eventParameter == null ||
	  				eventParameter < 0)
	  			eventParameterStr = "-";
	  		else 
	  			eventParameterStr = String.valueOf(eventParameter);
	  		statusListContents.add(eventParameterStr);
	  		/* Add the timestamp for the current event */
	  		ZonedDateTime       eventZonedDateTime = Instant.parse(eventTimestamp).atZone(ZoneId.systemDefault());
	  		DateTimeFormatter   eventFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss.SSS");
	  		String              eventFormattedString = eventZonedDateTime.format(eventFormatter);
	  		statusListContents.add(eventFormattedString);   
	  		/* Get the elapsed time since the last occurence of this event */
	  		Instant   start = Instant.parse(eventTimestamp);
	  		Instant   finish = Instant.now();
	  		long      eventElapsedMillis = Duration.between(start, finish).toMillis();
	  		statusListContents.add(String.valueOf(eventElapsedMillis/1000.0)); 
	  		/* Add the event rate average, if possible */
	  		if (eventRateAverage >= 0.0 && eventCount >= 2) 
	  		  statusListContents.add(String.valueOf(eventRateAverage));
	  		else
	  			statusListContents.add("-");
	  		/* Add the event rate standard deviation, if possible */
	  		if (eventRateStd >= 0.0 && eventCount >= 3) 
	  		  statusListContents.add(String.valueOf(eventRateStd));
	  		else
	  			statusListContents.add("-");
	  	}    	
	  	/* The event timestamp value is not set. Note that 
	  	   we treat the count as a missing value in this 
	  	   case even though we actually have a count value
	  	   (zero) that could be used. */
	  	else {
	  		/* Add values that show that we have no data for all
	  		   of the columns */ 
			  statusListContents.add("-");
			  statusListContents.add("-");
			  statusListContents.add("-"); 
			  statusListContents.add("-"); 
			  statusListContents.add("-"); 
			  statusListContents.add("-"); 
	  	}    	
	  }		
	  return statusListContents;
	}   
  /* This routine returns a tree set with all of the events 
     or just one event */
  public static TreeSet<HDLmEvent>  getEventsTree(final String overallName) {
		/* Check if the overall rule name passed by the caller is null.
		   Actually, the overall rule name can be null if we want to 
		   get a tree set with all of the events. */
		if (overallName == null && overallName != null) {
			String  errorText = "Overall rule name string passed to getEventsTree is null";
			throw new NullPointerException(errorText);
		}
    /* Create an event comparator for use in building/maintaining the
       tree set of events */  		
		class eventsComparator implements Comparator<HDLmEvent> {     
			@Override
			public int compare(HDLmEvent e1, HDLmEvent e2) {
				int   compareInt;
				compareInt = e1.getType().getValue() - e2.getType().getValue();
				if (compareInt == 0) 
					compareInt = e1.getName().compareTo(e2.getName());
				return compareInt;		   
			}
	  }   
		/* Get a reference to the map of events information */
		var  eventsMap = HDLmEvent.getEventsMap();
		if (eventsMap == null) {
			String   errorText = "Events map reference obtained from getEventsMap is null";
			HDLmAssertAction(false, errorText);		    	
    }    
		/* Create a new events set that uses the events comparator we just created */
		TreeSet<HDLmEvent>  eventsSet = new TreeSet<HDLmEvent>(new eventsComparator());		
	  /* Process each event in the events map. Each event in the events map
	     is copied into the events set. This has the effect of creating an
	     events set with all of the correct vales in the correct order. */ 
	  for (HDLmEvent curEvent : eventsMap.values()) {
	  	String  eventName = curEvent.getName();
	  	if (overallName == null ||
	  			overallName.equals(eventName))	    
	  	  eventsSet.add(curEvent);	
	  }
		return eventsSet;
  } 
  /* Get the host name for an event */
  protected String       getHostName() {
  	return eventDomain;
  }
  /* Get the host name for an event */
  protected Instant      getInstant() {
  	return eventLast;
  }
  /* Get the name of an event */
  protected String       getName() {
  	return eventName;
  }
  /* Get the last parameter number associated with the current
     event. This value is returned as an Integer rather than as
     an int so that null values can be returned. */ 
  protected Integer      getParameter() {
  	return eventParameter;
  }
  /* Get the rule name for an event */
  protected String       getRuleName() {
  	return eventRule;
  }
  /* Get the site name for an event */
  protected String       getSiteName() {
  	return eventSite;
  }
  /* This routine builds a JSON string with a set of event
     statistics information in it. This routine does all of
     the work to obtain event statistics information and 
     convert it to a JSON string. */
	public static String   getStatsInfo(final String overallName) {
		/* Check if the overall rule name passed by the caller is null */
		if (overallName == null) {
			String  errorText = "Overall rule name string passed to getStatsInfo is null";
			throw new NullPointerException(errorText);
		}
		/* Declare and define a few variables */
		ArrayList<String>              headingsArray = HDLmEvent.getEventsHeadings(overallName);
		ArrayList<ArrayList<String>>   outputArray = new ArrayList<ArrayList<String>>();
		ArrayList<String>              statusArray; 
		ArrayList<String>              statusArraySublist = null;
		int   headingsArraySize = headingsArray.size();
		/* Add the table headings to the final output array */
		outputArray.add(headingsArray);
		/* Get a tree set of stats (statistics) information */
		TreeSet<HDLmEvent>   statsSet = HDLmEvent.getEventsTree(overallName);
		HDLmAssertAction(statsSet != null, "Tree set not returned by getEventTree");
		/* Get an array of statistics information */
		statusArray = HDLmEvent.getEventsStatus(statsSet, HDLmGetStatus.JSONINFORMATION);
		HDLmAssertAction(statusArray != null, "Status array not returned by getEventStatus");
		/* Add each of the subarrays to the array list of array lists */
		for (int i = 0; i < statusArray.size(); i += headingsArraySize) {		
			statusArraySublist = new ArrayList<String>();
			for (int j = i; j < i+headingsArraySize; j++)
			  statusArraySublist.add(statusArray.get(j)); 
			outputArray.add(statusArraySublist);
		}		
		/* Build the final output JSON string and return it to the caller */
		Gson    gson = HDLmMain.gsonMain;
		String  outputJson = gson.toJson(outputArray);
		return outputJson;
	}
  /* Get the timestamp value for an event. Note that the
     timestamp value is actually a string. Note also that 
     this could be a null value if the current event has
     never occurred. */
  protected String       getTimestamp() {
  	if (eventLast == null)
  		return null;
  	return eventLast.toString();
  }    
  /* Get the type of an event. All events have a type
     that can be obtained using this routine. */
  protected HDLmEventTypes  getType() {
  	return eventType;
  }   
  /* Increment the count value for an event */
  protected void         incrementCount() {
  	eventCount++;
  }
  /* Set the division name for an event to the value passed by the caller */
  protected void         setDivisionName(final String newDivisionName) {
		/* Check if the division name passed by the caller is null */
		if (newDivisionName == null) {
			String  errorText = "Division name string passed to setHostName is null";
			throw new NullPointerException(errorText);
		}
  	eventDivision = newDivisionName;
  }
  /* Set the host name for an event to the value passed by the caller */
  protected void         setHostName(final String newHostName) {
		/* Check if the host name passed by the caller is null */
		if (newHostName == null) {
			String  errorText = "Host name string passed to setHostName is null";
			throw new NullPointerException(errorText);
		}
  	eventDomain = newHostName;
  }
  /* Set the timestamp for an event to the current ISO8601 timestamp */
  protected void         setIsoTimestamp(final Instant newInstant) {
  	eventLast = newInstant;
  }
  /* Set the parameter number for an event to the value passed by the 
     caller */ 
  protected void         setParameter(final int parameterNumber) {
		/* Check if the parameter number passed by the caller is less than zero */
		/* The above comment is not quite correct. We now use -1 as a parameter 
	     number to show that parameters are not in use (index values are used
	     instead). This check has been modified accordingly. */  
		if (parameterNumber < -1) {
			String  errorFormat = "Parameter number passed to setParameter is less than minus 1 (%d)";
			String  errorText = String.format(errorFormat,  parameterNumber); 
			throw new IllegalArgumentException(errorText);
		}
  	eventParameter = parameterNumber; 
  }
  /* Set the parameter number to a null value */
  protected void         setParameterNull() {
  	eventParameter = null;
  }
  /* Set the rule name for an event to the value passed by the caller */
  protected void         setRuleName(final String newRuleName) {
		/* Check if the rule name passed by the caller is null */
		if (newRuleName == null) {
			String  errorText = "Rule name string passed to setRuleName is null";
			throw new NullPointerException(errorText);
		}
  	eventRule = newRuleName;
  }
  /* Set the site name for an event to the value passed by the caller */
  protected void         setSiteName(final String newSiteName) {
		/* Check if the site name passed by the caller is null */
		if (newSiteName == null) {
			String  errorText = "Site name string passed to setSiteName is null";
			throw new NullPointerException(errorText);
		}
  	eventSite = newSiteName;
  }
  /* Store the current event instant value in the array of 
     instant values */
  protected void         storeCurrentEventInstant(final Instant newInstant) {
  	Integer   lastArraySize = HDLmDefines.getNumber("HDLMLASTARRAYSIZE");
		if (lastArraySize == null) {
			String   errorFormat = "Define value for last array size number not found (%s)";
			String   errorText = String.format(errorFormat, "HDLMLASTARRAYSIZE");
			HDLmAssertAction(false, errorText);		    	
		}  	
  	int   curIndex = (int) ((eventCount-1) % lastArraySize);
  	eventLastArray[curIndex] = newInstant;  	
  }   
}
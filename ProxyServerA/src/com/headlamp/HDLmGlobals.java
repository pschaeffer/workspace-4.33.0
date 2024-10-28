package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HDLmGlobals short summary.
 *
 * HDLmGlobals description.
 *
 * @version 1.0
 * @author Peter
 */
/* The HDLmGlobals class is not used to create any objects.
   However, this class does contain code that is used for
   various global purposes. */
public class HDLmGlobals {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmGlobals.class);  
  /* The next global value is used to control execution of the update
     routine. If this value is greater than zero, then an update operation
		 is already in progress. This value is used to indicate that additional
		 update operations are needed. For example, a value of two means that 
		 an update operation is already running and an additional update is 
		 needed. */
  private static int  updateCounter = 0;
	/* This class can never be instantiated */
	private HDLmGlobals() {}
  /* This routine checks if one of the inline editors is in use.
     At present, the inline editors are the Popup editor and the
     simple editor. If any inline editor is in use, this routine 
     returns true. Otherwise, this routine returns false. */
  protected static boolean checkForInlineEditor(HDLmEditorTypes editorType) {
		if (editorType == null) {
			String   errorText = "Editor type value passed to checkForInlineEditor is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the editor type passed by the caller is invalid */
		if (editorType == HDLmEditorTypes.NONE) {
		  HDLmAssertAction(false, "Editor type value is invalid");
		}
		/* Check for one of the inline editors */
    if (editorType == HDLmEditorTypes.POPUP ||
		    editorType == HDLmEditorTypes.SIMPLE)
      return true;
    else
      return false;
  }
  /* This routine decrements the update counter and returns the new
	   update counter to the caller */ 
	protected static int   decrementUpdateCounter() {
		updateCounter--;
		return updateCounter;  	
	}
  /* Get the current value of the update counter and return it 
     to the caller */ 
  protected static int   getUpdateCounter() {
	  return updateCounter;  	
  }
  /* This routine increments the update counter and returns the new
     update counter to the caller */ 
  protected static int   incrementUpdateCounter() {
  	updateCounter++;
  	return updateCounter;  	
  }
}
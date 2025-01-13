package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Instant;
import org.junit.jupiter.api.Test;
/**
 * HDLmChangeTest short summary.
 *
 * HDLmChangeTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmChangeTest {
	@Test
	void toStringTest() {
		/* Run a few toString tests. This routine should really
		   be called toString. however this name causes a Java
		   error. */
		/* Set a few local values for use below */ 
		HDLmChangeSourceTypes   newSource = HDLmChangeSourceTypes.CHANGESOURCESOCKETS;
		HDLmChangeTypes         newType = HDLmChangeTypes.CHANGETYPEADD;
		Instant                 newTime = Instant.parse("2020-01-01T00:00:00.00Z");
		String                  newTop = "Top";
		String                  newGroup = "Group";
		String                  newCompany = "Company";
		String                  newLocation = "Location";
		String                  newDivision = "Division";
		String                  newSite = "Site";
		String                  newName = "Name";
		String                  newDetails = "Details";
		/* Create a new change object */
		HDLmChange  newchange = new HDLmChange(newSource, 
																					 newType,
																					 newTime,
																					 newTop,
																					 newGroup,
																					 newCompany, 
																					 newLocation,
																					 newDivision, 
																					 newSite, 
																					 newName, 
																					 newDetails); 
		/* Make sure we got a change object back */ 
		assertNotNull(newchange, "New change object is null");
		/* Convert the change object to a string */
		String  changeString = newchange.toString();
		/* Make sure we got a string back */
		assertNotNull(changeString, "New change object is null");
	}
	@Test
	void toStringUserFriendly() {
		/* Run a few toStringUserFriendly tests */
		/* Set a few local values for use below */ 
		HDLmChangeSourceTypes   newSource = HDLmChangeSourceTypes.CHANGESOURCESOCKETS;
		HDLmChangeTypes         newType = HDLmChangeTypes.CHANGETYPEADD;
		Instant                 newTime = Instant.parse("2020-01-01T00:00:00.00Z");
		String                  newTop = "Top";
		String                  newGroup = "Group";
		String                  newCompany = "Company";
		String                  newLocation = "Location";
		String                  newDivision = "Division";
		String                  newSite = "Site";
		String                  newName = "Name";
		String                  newInfo = "Info";
		/* Create a new change object */
		HDLmChange  newchange = new HDLmChange(newSource, 
																					 newType,
																					 newTime,
																					 newTop,
																					 newGroup,
																					 newCompany, 
																					 newLocation,
																					 newDivision, 
																					 newSite, 
																					 newName, 
																					 newInfo); 
		/* Make sure we got a change object back */ 
		assertNotNull(newchange, "New change object is null");
		/* Convert the change object to a user friendly string */
		String  changeString = newchange.toStringUserFriendly();
		/* Make sure we got a string back */
		assertNotNull(changeString, "New change object is null");
		/* Make sure we got the expected string back */
		String  expectedString = "{\"source\":\"WebSockets\",\"type\":\"add\"," +
				                     "\"time\":\"2020-01-01T00:00:00Z\"," +
				                     "\"top\":\"Top\"," +
				                     "\"group\":\"Group\"," +
				                     "\"company\":\"Company\"," +
				                     "\"location\":\"Location\"," +
				                     "\"division\":\"Division\",\"site\":\"Site\",\"name\":\"Name\",\"info\":\"Info\"}";				
		assertEquals(changeString, expectedString, "New change string is wrong");		
	}
}

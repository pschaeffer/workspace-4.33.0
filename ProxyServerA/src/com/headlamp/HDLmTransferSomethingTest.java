package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/**
 * HDLmHDLmTransferSomethingTest short summary.
 *
 * HDLmHDLmTransferSomethingTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmTransferSomethingTest {
  @BeforeAll
	static void HDLmUnReBeforeAll() {
		/* Get the Hikari data source. This call has the effect
	     of building the JDBC connection pool. */
	  HDLmHikariPool.getDataSource();
	  /* Initialize undo/redo processing. This is done by calling
	     an initialization routine to the undo/redo module. */ 
	  int   unReLimit = HDLmConfigInfo.getUnReLimit();
	  HDLmUnRe.unReStartup(unReLimit);	
  }
  @Test
	/* This routine tests the HDLmTransferSomething constructor. The constructor is
	   called with a variety of parameters. The parameters are tested for null
	   values and invalid values. The constructor is expected to throw exceptions
	   for null values and invalid values. */
  void HDLmTransferSomething() {
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {new HDLmTransferSomething(null,
                                                                     		  toSystemValue, 
                                                                          fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("From system value passed to transfer object constructor is null", execMsg,
					         "Unexpected exception message");
		}	
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                     		  null,
                                                                          fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("To system value passed to transfer object constructor is null", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                     		  toSystemValue, 
                                                                          null, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("From domain name value passed to transfer object constructor is null", execMsg,
					         "Unexpected exception message");
		}	
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                     		  toSystemValue, 
                                                                          fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          null,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("From division name value passed to transfer object constructor is null", execMsg,
					         "Unexpected exception message");
		}	
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                     		  toSystemValue, 
                                                                          fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          null,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("From site name value passed to transfer object constructor is null", execMsg,
					         "Unexpected exception message");
		}	
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                     		  toSystemValue, 
                                                                          fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          null,    
																                                          toRuleNameValue);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("From rule name value passed to transfer object constructor is null", execMsg,
					         "Unexpected exception message");
		}	
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething("prodx",
                                                                     		  toSystemValue, 
                                                                          fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("System value length (5) is invalid", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething("prod",
                                                                     		  "prodx", 
                                                                          fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("System value length (5) is invalid", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething("prod",
                                                                     		  "prod",  
                                                                          fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("From and to systems are equal", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "prod";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething("prod",
                                                                     		  "test",  
                                                                          "", 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("Domain name string length (0) is invalid", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "test";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                      		toSystemValue,  
                                                                      		fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          "",  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("Division value passed to checkValue is zero-length", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "test";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                      		toSystemValue,  
                                                                      		fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          "",     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("Site value passed to checkValue is zero-length", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "test";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                      		toSystemValue,  
                                                                      		fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,   
																                                          toSiteNameValue,       
																                                          "",    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("Rule value passed to checkValue is zero-length", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "test";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                      		toSystemValue,  
                                                                      		fromDomainNameValue, 
																                                          "",    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,   
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("Domain name string length (0) is invalid", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "test";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                      		toSystemValue,  
                                                                      		fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          "*",    
																                                          fromSiteNameValue,     
																                                          toSiteNameValue,       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("Division value passed to checkValueNotAsterisk starts with an asterisk", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "test";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                      		toSystemValue,  
                                                                      		fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,     
																                                          "*",       
																                                          fromRuleNameValue,    
																                                          toRuleNameValue);}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("Site value passed to checkValueNotAsterisk starts with an asterisk", execMsg,
					         "Unexpected exception message");
		}
		{   
			String fromSystemValue       = "prod";
      String toSystemValue         = "test";
      String fromDomainNameValue   = "zyxwvutsrq9876543210.com";
      String toDomainNameValue     = "zyxwvutsrq9876543211.com";
      String fromDivisionNameValue = "example.com"; 
      String toDivisionNameValue   = "example.com";
      String fromSiteNameValue     = "example.com";
      String toSiteNameValue       = "example.com";
      String fromRuleNameValue     = "Notify Add To Cart";
      String toRuleNameValue       = "Notify Add To Cart";
			Throwable exception = assertThrows(InstantiationException.class, 
                                         () -> {new HDLmTransferSomething(fromSystemValue,
                                                                      		toSystemValue,  
                                                                      		fromDomainNameValue, 
																                                          toDomainNameValue,    
																                                          fromDivisionNameValue,  
																                                          toDivisionNameValue,   
																                                          fromSiteNameValue,   
																                                          toSiteNameValue,       
																                                          toRuleNameValue,    
																                                          "*");}, 
                                         "Expected InstantiationException");
			String execMsg = exception.getMessage();
			assertEquals("Rule value passed to checkValueNotAsterisk starts with an asterisk", execMsg,
					         "Unexpected exception message");
		}
  }  
  @Test
  void buildTransferRequest() {
  	/* Check the code that builds a transfer request */ 
  	{   
  		/* Create a JSON string with the transfer request in it */ 
      String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
  							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
  										        "\"fromDivision\": \"example.com\"," + 
  										        "\"fromSite\": \"example.com\", " +
  										        "\"fromRule\": \"Notify Add To Cart\"," + 
  										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething  transferObject = new HDLmTransferSomething();
      Throwable exception = assertThrows(NullPointerException.class, 
          () -> {HDLmTransferSomething.buildTransferRequest(null,                                       		 
                                                        		transferObject);}, 
          "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("JSON elements passed to buildTransferRequest is null", execMsg,
					         "Unexpected exception message");
		}
  	{   
  		/* Create a JSON string with the transfer request in it */ 
      String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
  							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
  										        "\"fromDivision\": \"example.com\"," + 
  										        "\"fromSite\": \"example.com\", " +
  										        "\"fromRule\": \"Notify Add To Cart\"," + 
  										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement;
      Throwable exception = assertThrows(NullPointerException.class, 
          () -> {HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                        		null);}, 
          "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Empty transfer object passed to buildTransferRequest is null", execMsg,
					         "Unexpected exception message");
		}
  	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
      String  transferJson = "{\"fromSystem\": \"prodx\", \"toSystem\": \"prod\"," + 
  							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
  										        "\"fromDivision\": \"example.com\"," + 
  										        "\"fromSite\": \"example.com\", " +
  										        "\"fromRule\": \"Notify Add To Cart\"," + 
  										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "System value length (5) is invalid",
					         "Unexpected error message");
		}
  	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
      String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prodx\"," + 
  							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
  										        "\"fromDivision\": \"example.com\"," + 
  										        "\"fromSite\": \"example.com\", " +
  										        "\"fromRule\": \"Notify Add To Cart\"," + 
  										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "System value length (5) is invalid",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
      String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
  							 			        "\"fromDomain\": \"\"," + 
  										        "\"fromDivision\": \"example.com\"," + 
  										        "\"fromSite\": \"example.com\", " +
  										        "\"fromRule\": \"Notify Add To Cart\"," + 
  										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "Domain name string length (0) is invalid",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
      String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
  							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
  										        "\"fromDivision\": \"\"," + 
  										        "\"fromSite\": \"example.com\", " +
  										        "\"fromRule\": \"Notify Add To Cart\"," + 
  										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "Division value passed to checkValue is zero-length",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
      String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
  							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
  										        "\"fromDivision\": \"example.com\"," + 
  										        "\"fromSite\": \"\", " +
  										        "\"fromRule\": \"Notify Add To Cart\"," + 
  										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "Site value passed to checkValue is zero-length",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
      String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
  							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
  										        "\"fromDivision\": \"example.com\"," + 
  										        "\"fromSite\": \"example.com\", " +
  										        "\"fromRule\": \"\"," + 
  										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "Rule value passed to checkValue is zero-length",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
      String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
  							 			        "\"fromDomain\": \"zyxwvutsrq9876543211.com\"," + 
  										        "\"fromDivision\": \"example.com\"," + 
  										        "\"fromSite\": \"example.com\", " +
  										        "\"fromRule\": \"Notify Add To Cart\"," + 
  										        "\"toDomain\": \"\"}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "Domain name string length (0) is invalid",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
      String  transferJson = "{" + 
	  		                       "\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
	  							 			       "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
	  										       "\"fromDivision\": \"example.com\"," + 
	  										       "\"fromSite\": \"example.com\", " +
	  										       "\"fromRule\": \"Notify Add To Cart\"," + 
	  										       "\"toDomain\": \"zyxwvutsrq9876543211.com\"," +
	  										       "\"toDivision\": \"\"," +
	  										       "\"toSite\": \"example.com\"," +
	  										       "\"toRule\": \"Notify Add To Cart\""  +
  										        "}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "Division value passed to checkValueNotAsterisk is zero-length",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
  		String  transferJson = "{" + 
											         "\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
													     "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
													     "\"fromDivision\": \"example.com\"," + 
													     "\"fromSite\": \"example.com\", " +
													     "\"fromRule\": \"Notify Add To Cart\"," + 
													     "\"toDomain\": \"zyxwvutsrq9876543211.com\"," +
													     "\"toDivision\": \"example.com\"," +
													     "\"toSite\": \"\"," +
													     "\"toRule\": \"Notify Add To Cart\""  +
												     "}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "Site value passed to checkValueNotAsterisk is zero-length",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
  		String  transferJson = "{" + 
											         "\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
													     "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
													     "\"fromDivision\": \"example.com\"," + 
													     "\"fromSite\": \"example.com\", " +
													     "\"fromRule\": \"Notify Add To Cart\"," + 
													     "\"toDomain\": \"zyxwvutsrq9876543211.com\"," +
													     "\"toDivision\": \"example.com\"," +
													     "\"toSite\": \"example.com\"," +
													     "\"toRule\": \"\""  +
		                         "}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "Rule value passed to checkValueNotAsterisk is zero-length",
					         "Unexpected error message");
		}
   	{   
  		/* Create a JSON string with the transfer request in it */ 
  		String  errorMessage = null;
  		String  transferJson = "{" + 
											         "\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
													     "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
													     "\"fromDivision\": \"example.com\"," + 
													     "\"fromSite\": \"example.com\", " +
													     "\"fromRule\": \"Notify Add To Cart\"," + 
													     "\"toDomain\": \"zyxwvutsrq9876543210.com\"," +
													     "\"toDivision\": \"example.com\"," +
													     "\"toSite\": \"example.com\"," +
													     "\"toRule\": \"Notify Add To Cart\""  +
		                         "}";
  		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
  		   we do not have a string than can be converted to a JSON object. If this works,
  		   then we do have string than can be converted to a JSON object. */
  		JsonParser    parser = new JsonParser();		
  		JsonElement   jsonElement = null; 
  		try {
  			jsonElement = parser.parse(transferJson);
  		} 
  		catch (Exception exception) {
  			assertNull(jsonElement, "The transfer JSON string is not valid");
  			return;
  		}	
  		/* Create an empty transfer object */
  		HDLmTransferSomething   transferObject = new HDLmTransferSomething();
  		final JsonElement       jsonElementFinal = jsonElement; 
      errorMessage = HDLmTransferSomething.buildTransferRequest(jsonElementFinal,                                       		 
                                                            		transferObject);  
			assertEquals(errorMessage, "From/to domain names are the same, domain names must not match",
					         "Unexpected error message");
		}
  }
  @Test
  void checkSystemValue() {
  	/* Check a system value */   	
  	{   
  		/* Create a few values */ 
  		String  errorMessage = null;      
      errorMessage = HDLmTransferSomething.checkSystemValue(null);
			assertEquals(errorMessage, "System value is null",
					         "Unexpected error message");
		}  	
  	{   
  		/* Create a few values */ 
  		String  errorMessage = null;      
      errorMessage = HDLmTransferSomething.checkSystemValue("prodx");
			assertEquals(errorMessage, "System value length (5) is invalid",
					         "Unexpected error message");
		}
  	{   
  		/* Create a few values */ 
  		String  errorMessage = null;      
      errorMessage = HDLmTransferSomething.checkSystemValue("prox");
			assertEquals(errorMessage, "System value (prox) is invalid",
					         "Unexpected error message");
		}
  }
  @Test
  void checkValue() {
  	/* Check a value */   	
  	{   
  		/* Create a few values */ 
  		String  errorMessage = null;      
      errorMessage = HDLmTransferSomething.checkValue("division", null);
			assertEquals(errorMessage, "Division value passed to checkValue is null",
					         "Unexpected error message");
		}  	
  	{   
  		/* Create a few values */ 
  		String  errorMessage = null;      
      errorMessage = HDLmTransferSomething.checkValue("division", "");
			assertEquals(errorMessage, "Division value passed to checkValue is zero-length",
					         "Unexpected error message");
		}
  }
  @Test
  void checkValueNotAsterisk() {
  	/* Check a value that can not be an asterisk */   	
  	{   
  		/* Create a few values */ 
  		String  errorMessage = null;      
      errorMessage = HDLmTransferSomething.checkValueNotAsterisk("division", null);
			assertEquals(errorMessage, "Division value passed to checkValueNotAsterisk is null",
					         "Unexpected error message");
		}  	
  	{   
  		/* Create a few values */ 
  		String  errorMessage = null;      
      errorMessage = HDLmTransferSomething.checkValueNotAsterisk("division", "");
			assertEquals(errorMessage, "Division value passed to checkValueNotAsterisk is zero-length",
					         "Unexpected error message");
		}
   	{   
  		/* Create a few values */ 
  		String  errorMessage = null;      
      errorMessage = HDLmTransferSomething.checkValueNotAsterisk("division", "*");
			assertEquals(errorMessage, "Division value passed to checkValueNotAsterisk starts with an asterisk",
					         "Unexpected error message");
		}
  }
  @Test
  void filterRowList() {
  	/* Check the row list filter code */
  	{   
  		/* Create an empty transfer object */
  		HDLmTransferSomething       transferObject = new HDLmTransferSomething();
  		ArrayList<HDLmDatabaseRow>  inputRows = new ArrayList<HDLmDatabaseRow>();
      Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {HDLmTransferSomething.filterRowList(null,
                                                                                	  inputRows);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Transfer object passed to filterRowList is null", execMsg,
					         "Unexpected exception message");
		}  	
  	{   
  		/* Create an empty transfer object */
  		HDLmTransferSomething       transferObject = new HDLmTransferSomething();
  		ArrayList<HDLmDatabaseRow>  inputRows = new ArrayList<HDLmDatabaseRow>();
      Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {HDLmTransferSomething.filterRowList(transferObject,
                                                                                		null);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input row list passed to filterRowList is null", execMsg,
					         "Unexpected exception message");
		} 
  }
  @Test
  void insertRowList() {
  	/* Check the row list insertion code */
  	{   
  		/* Create an empty transfer object */
  		HDLmTransferSomething       transferObject = new HDLmTransferSomething();
  		ArrayList<HDLmDatabaseRow>  inputRows = new ArrayList<HDLmDatabaseRow>();
      Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {HDLmTransferSomething.insertRowList(null,
                                                                                	  inputRows);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Transfer object passed to insertRowList is null", execMsg,
					         "Unexpected exception message");
		}  	
  	{   
  		/* Create an empty transfer object */
  		HDLmTransferSomething       transferObject = new HDLmTransferSomething();
  		ArrayList<HDLmDatabaseRow>  inputRows = new ArrayList<HDLmDatabaseRow>();
      Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {HDLmTransferSomething.insertRowList(transferObject,
                                                                                		null);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Input row list passed to insertRowList is null", execMsg,
					         "Unexpected exception message");
		} 
  }
  @Test
  void runTransferRequest() {
  	/* Check the run a transfer code */
  	{   
  		/* Create an empty transfer object */
  		HDLmTransferSomething       transferObject = new HDLmTransferSomething();
      Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {HDLmTransferSomething.runTransferRequest(null);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("Transfer object passed to runTransferRequest is null", execMsg,
					         "Unexpected exception message");
		}  	
  }
  @Test
  void transferSomething() {
  	/* Check the transfer code */
  	{   
  		/* Create an empty transfer object */
      Throwable exception = assertThrows(NullPointerException.class, 
                                         () -> {HDLmTransferSomething.transferSomething(null);}, 
                                         "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals("JSON elements passed to transferSomething is null", execMsg,
					         "Unexpected exception message");
		}  	
  }
  @Test
	void checkTransferSomething() {
		/* The overall logic of this routine is too insert a company and then
		   transfer it. The transfer creates an undo/redo operation. The actual
		   test is the transfer. */  
		ArrayList<HDLmDatabaseRow>    databaseRowList;  
		ArrayList<ArrayList<String>>  dummyScopeArray;
		int                           unReNumber;
		String                        content; 
		String                        databaseOut;
		String                        dummyScope = "(zyxwvutsrq)";
		String                        errorMessage = null;
		String                        firstDummyCompanyName = "zyxwvutsrq9876543210.com";
		String                        jsonInsert; 
		String                        outJson;
		String                        secondDummyCompanyName = "zyxwvutsrq9876543211.com";
		String                        tableName; 
		/* Get all the rows (there may be none) for the test company */
		content = HDLmUtility.getContentString();
		tableName = HDLmConfigInfo.getEntriesDatabaseTableName();
		/* Delete the first dummy company from the table */
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, firstDummyCompanyName); 
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		} 
		/* Delete the second dummy company from the table */
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, secondDummyCompanyName); 
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		}  
		/* Try to insert the company into the table */  
		jsonInsert = HDLmTreeData.jsonUnReInsert; 
		databaseOut = HDLmDatabase.insertTableRowsJson(jsonInsert, tableName); 
		/* The next two lines are not really needed */ 
		dummyScopeArray = HDLmSecurity.convertScopeString(dummyScope);		
		outJson = HDLmDatabase.getTableRowsJson(content, tableName, dummyScopeArray);  
		/* Create a JSON string with the transfer request in it */ 
    String  transferJson = "{\"fromSystem\": \"prod\", \"toSystem\": \"prod\"," + 
							 			        "\"fromDomain\": \"zyxwvutsrq9876543210.com\"," + 
										        "\"fromDivision\": \"example.com\"," + 
										        "\"fromSite\": \"example.com\", " +
										        "\"fromRule\": \"Notify Add To Cart\"," + 
										        "\"toDomain\": \"zyxwvutsrq9876543211.com\"}";
		/* Try to convert the transfer JSON string to a JSON object. If this fails, then 
		   we do not have a string than can be converted to a JSON object. If this works,
		   then we do have string than can be converted to a JSON object. */
		JsonParser    parser = new JsonParser();		
		JsonElement   jsonElement = null; 
		try {
			jsonElement = parser.parse(transferJson);
		} 
		catch (Exception exception) {
			assertNull(jsonElement, "The transfer JSON string is not valid");
			return;
		}	
		/* Try to execute the transfer request */
		HDLmResponse  transferResponse = HDLmTransferSomething.transferSomething(jsonElement);		
		errorMessage = transferResponse.getErrorMessage();
		assertNull(errorMessage, "The transfer operation failed");	
		/* Delete the first dummy company from the table */
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, firstDummyCompanyName); 
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		} 
		/* Delete the second dummy company from the table */
		databaseRowList = HDLmDatabase.getTableRowsCompany(content, tableName, secondDummyCompanyName); 
		if (databaseRowList.size() > 0) {
			HDLmDatabase.deleteTableRows(databaseRowList, tableName);
		} 
	}  
}
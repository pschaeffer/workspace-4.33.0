package com.headlamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * HDLmCurlJettyTest short summary.
 *
 * HDLmCurlJettyTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmCurlJettyTest {
  @BeforeAll
	static void HDLmCurlApacheBeforeAll() throws Exception {
		/* The log changes flag is set to 'no' (without the quotes),
       so that changes are not logged. This value can be checked
       to see if changes should be logged. */
    HDLmState.setName("logChanges", "no");
  	/* Start the Jetty server, if it has not already been started */
  	if (HDLmMain.checkMainExecuted() == false) {
      String[]  emptyArgument = {};  	 
			HDLmMain.main(emptyArgument);
	  }
	}
	@Test
	void runCurl() {		
		/* Run a few runCurl tests */
		HDLmHttpTypes  type;
		String         colName;
		String         companyPrefix;
		String         contentSuffix;
		String         contentSuffixSystem;
		String         curlOut;
		String         password;
		String         protocol;
		String         queryString;
		String         readEntriesURL;
		String         updateEntriesURL;
		String         table;
		String         url;
		String         userid;
		String         value;
		/* Get a few values needed below */
		userid = HDLmConfigInfo.getEntriesBridgeUserid();
		password = HDLmConfigInfo.getEntriesBridgePassword();
		type = HDLmHttpTypes.GET;
		readEntriesURL = HDLmConfigInfo.getEntriesBridgeReadUrl();
		updateEntriesURL = HDLmConfigInfo.getEntriesBridgeUpdateUrl();
		table = HDLmConfigInfo.getEntriesDatabaseTableName();
		protocol = HDLmConfigInfo.getEntriesBridgeInternetMethod();
		/* Build the query string */
		colName = "content";
		value = "mod";
		companyPrefix = HDLmConfigInfo.getEntriesBridgeCompanyPrefix();
		contentSuffix = HDLmConfigInfo.getEntriesBridgeContentSuffix();
		contentSuffixSystem = HDLmStateInfo.getSystemValue();  
		contentSuffix += contentSuffixSystem;
		queryString = HDLmUtility.buildBridgeRestQuery(colName, HDLmEditorTypes.PASS);
		/* Build the final URL */
		url = protocol + "://" + readEntriesURL + table + "?" + queryString;
		/* Run the actual request */
		curlOut = HDLmCurlJetty.runCurl(url, userid, password, type);	
		assertNotNull(curlOut, "Null response recieved from run Curl routine");		
		/* Build the final URL for error testing */
		url = protocol + "://" +  "x" + table + "?" + queryString;
		/* Run the actual request (which will fail) */
		curlOut = HDLmCurlJetty.runCurl(url, userid, password, type, "", HDLmReportErrors.DONTREPORTERRORS);	
		/* Try an HTTP post request. Note that we change the table name here. We don't 
		   want to update any production tables. However, the table used below is just
		   a test table and can be changed at will.  */
		table = "test";
		url = protocol + "://" + updateEntriesURL + table;
		/* Run the actual request */
		type = HDLmHttpTypes.POST;
		/* We can't really do this. This would change a production system. We 
		   need to skip this test for now. Since the table name is set to "test",
		   we won't change any production data */		 
		curlOut = HDLmCurlJetty.runCurl(url, userid, password, type, HDLmTreeData.jsonPostStr,
				                       HDLmReportErrors.REPORTERRORS);		  
		assertNotNull(curlOut, "Null response recieved from run Curl routine"); 
		{ 
			HDLmHttpTypes  typeLocal = type;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmCurlJetty.runCurl(null, 
					                              		                          userid, 
					                              		                          password, 
					                              		                          typeLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "URL reference passed to runCurl is null",
					         "Unexpected exception message");
		}	
		{
			HDLmHttpTypes  typeLocal = type;
			String   urlLocal = url;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmCurlJetty.runCurl(urlLocal, 
					                              		                          null,  
					                              		                          password, 
					                              		                          typeLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Userid reference passed to runCurl is null",
					         "Unexpected exception message");
		}	
		{
			HDLmHttpTypes  typeLocal = type;
			String   urlLocal = url;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmCurlJetty.runCurl(urlLocal, 
					                              		                          userid, 
					                              		                          null, 
					                              		                          typeLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Password reference passed to runCurl is null",
					         "Unexpected exception message");
		}	
		{
			String   urlLocal = url;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmCurlJetty.runCurl(urlLocal, 
					                              		                          userid, 
					                              		                          password, 
					                              		                          null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Type reference passed to runCurl is null",
					         "Unexpected exception message");
		}
		{
			String   urlLocal = url;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmCurlJetty.runCurl(urlLocal, 
					                              		                          userid, 
					                              		                          password, 
					                              		                          HDLmHttpTypes.NONE);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Type value (NONE) passed to runCurl is invalid",
					         "Unexpected exception message");
		}
		{
			HDLmHttpTypes  typeLocal = type;
			String   urlLocal = url;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmCurlJetty.runCurl(urlLocal, 
					                              		                          userid, 
					                              		                          password, 
					                              		                          typeLocal, 
					                              		                          null,
					                              		                          HDLmReportErrors.REPORTERRORS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Extra information reference passed to runCurl is null",
					         "Unexpected exception message");
		}	
	}
	@Test
	void reportCurlError() {		
		/* Run a few reportCurlError tests */
		/* Try to report an error */
		String   errorMsg = "Dummy error message";
		String   outMessage;
		outMessage = HDLmCurlJetty.reportCurlError(errorMsg, HDLmReportErrors.DONTREPORTERRORS);	
		assertNotNull(outMessage, "Null response recieved from Curl error reporting routine");
		assertEquals("Dummy error message", outMessage, "Invalid response from Curl error reporting routine");
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmCurlJetty.reportCurlError(null, HDLmReportErrors.REPORTERRORS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Error message reference passed to reportCurlError is null",
					         "Unexpected exception message");
		}	
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmCurlJetty.reportCurlError("", null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Error reporting reference passed to reportCurlError is null",
					         "Unexpected exception message");
		}	
	}
}
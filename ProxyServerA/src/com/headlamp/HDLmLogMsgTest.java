package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * HDLmLogMsgTest short summary.
 *
 * HDLmLogMsgTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmLogMsgTest {
	@Test
	void buildLogMsg() {
		/* Run a buildLogMsg test */
		String   logMsgString = "(abc)";
		String   outStr = "HDLm Unmatched Quotes 15 (abc)";
		
		
	   String xyz = HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, 
         "Unmatched Quotes", 
         15, 
         logMsgString, 
         HDLmReportErrors.DONTREPORTERRORS);
		/* Note that a special value of false is passed here for message reporting.
		   This is done just for unit testing so that no messages will show up in
		   the console. */
		assertEquals(outStr, 
			           HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, 
			          		                    "Unmatched Quotes", 
			          		                    15, 
			          		                    logMsgString, 
			          		                    HDLmReportErrors.DONTREPORTERRORS),
				         "HDLmLogMsg.buildLogMsg did not return correct value");
		/* Run a buildLogMsg test */
		outStr = "HDLm Unmatched Quotes 15 (abc)";
		assertEquals(outStr, 
                 HDLmLogMsg.buildLogMsg(null, 
       		                              "Unmatched Quotes", 
       		                              15, 
       		                              logMsgString, 
       		                              HDLmReportErrors.DONTREPORTERRORS),
                 "HDLmLogMsg.buildLogMsg did not return correct value");
		/* Run a buildLogMsg test */
		outStr = "HDLm Null Type 15 (abc)";
		assertEquals(outStr, 
                 HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, 
	                                      null,
	                                      15, 
	                                      logMsgString, 
	                                      HDLmReportErrors.DONTREPORTERRORS),
                 "HDLmLogMsg.buildLogMsg did not return correct value");
		/* Run a buildLogMsg test */
		outStr = "HDLm Unmatched Quotes 0 (abc)";
		assertEquals(outStr, 
                 HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, 
                		                    "Unmatched Quotes", 
	                                      0,
	                                      logMsgString, 
	                                      HDLmReportErrors.DONTREPORTERRORS),
                 "HDLmLogMsg.buildLogMsg did not return correct value");
		/* Run a buildLogMsg test */
		outStr = "HDLm Unmatched Quotes 15 Null Text";
		assertEquals(outStr, 
                 HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, 
                		                    "Unmatched Quotes", 
	                                      15,
	                                      null, 
	                                      HDLmReportErrors.DONTREPORTERRORS),
                 "HDLmLogMsg.buildLogMsg did not return correct value");
		/* Run a buildLogMsg test */
		outStr = "HDLm Unmatched Quotes 15 (abc)";
		assertEquals(outStr, 
                 HDLmLogMsg.buildLogMsg(HDLmLogLevels.NONE, 
                		                    "Unmatched Quotes", 
	                                      15,
	                                      logMsgString, 
	                                      HDLmReportErrors.DONTREPORTERRORS),
                 "HDLmLogMsg.buildLogMsg did not return correct value");
	}
}
package com.headlamp;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
/**
 * HDLmAllTests short summary.
 *
 * HDLmAllTests description.
 *
 * @version 1.0
 * @author Peter
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({HDLmConfigTest.class, HDLmDefinesTest.class,	 
                HDLmErrorTest.class, HDLmStringTest.class, 
                HDLmTree1Test.class,
                HDLmTree2Test.class, HDLmTree3Test.class,
	              HDLmModTest.class, HDLmBuildJSTest.class, HDLmFindTest.class,
	              HDLmAssertTest.class, HDLmBuildLinesTest.class, HDLmUtilityTest.class,
	              HDLmSavedChangeTest.class, HDLmCurlApacheTest.class, HDLmMainTest.class,
	              HDLmJettyTest.class, HDLmCurlJettyTest.class, HDLmEditorServletTest.class,
	              HDLmApacheTest.class, HDLmProxyTest.class, HDLmSessionTest.class,
	              HDLmLogMsgTest.class, HDLmMatchTest.class, HDLmImageInformationTest.class,
	              HDLmClusteringTest.class, HDLmJsonTest.class, 
	              HDLmBridgeTest.class, HDLmDatabaseTest.class,
	              HDLmOpenAITest.class, HDLmTimingTest.class,
	              HDLmHikariPoolTest.class, HDLmSecurityTest.class,
	              HDLmSignatureTest.class, HDLmUnReTest.class,
	              HDLmTransferSomethingTest.class, HDLmChangeTest.class,
	              HDLmAwsUtilityTest.class})
class HDLmAllTests {}
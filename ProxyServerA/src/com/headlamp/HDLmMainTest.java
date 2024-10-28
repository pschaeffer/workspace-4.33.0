package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.eclipse.jetty.util.log.Log;
import org.junit.jupiter.api.Test;
/**
 * HDLmJMainTest short summary.
 *
 * HDLmMainTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmMainTest {
	@Test
	void buildModificationTree() {
		/* Disable logging for this test */
		Log.setLog(new HDLmNoLogging());
		/* Run a few buildModificationTree tests */
		HDLmTree   topModNode;
		topModNode = HDLmMain.buildNodeTreeMain(HDLmTreeData.jsonGetProxyStr, HDLmEditorTypes.PROXY, HDLmStartupMode.STARTUPMODENO); 
		assertNotNull(topModNode, "Null value returned from buildNodeTreeMain"); 
		topModNode = HDLmMain.buildNodeTreeMain(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO); 
		assertNotNull(topModNode, "Null value returned from buildNodeTreeMain"); 
		String     topModName = topModNode.getLastNodePathEntry();
		assertEquals("Top", topModName, "Name of the top node is wrong");
		String     topModTooltip = topModNode.getTooltip();
		assertEquals("Top node of the node tree", topModTooltip, "Tooltip of the top node is wrong");
		ArrayList<HDLmTree>  topModChildren = topModNode.getChildren();
		int        modChildrenSize = topModChildren.size();
		assertEquals(2, modChildrenSize, "Incorrect number of children for top node");
		HDLmTreeTypes  topModType = topModNode.getType();
		assertEquals(HDLmTreeTypes.TOP, topModType, "Top node has incorrect type");
		int        topModLevel = topModNode.getNodePathLength();
		assertEquals(1, topModLevel, "Top node has incorrect level");
		ArrayList<String>  modTopNodePath = topModNode.getNodePath();
		int        modNodePathSize = modTopNodePath.size();
		assertEquals(1, modNodePathSize, "Top node has incorrect node path length");
		assertEquals("Top", modTopNodePath.get(0), "Top node path has incorrect first entry");
		HDLmMod    topModDetails = topModNode.getDetails();
		assertNotNull(topModDetails, "Top node has null details");		
		/* Run a few buildProxyTree tests */
		HDLmTree   topProxyNode;
		topProxyNode = HDLmMain.buildNodeTreeMain(HDLmTreeData.jsonGetProxyStr, HDLmEditorTypes.PROXY, HDLmStartupMode.STARTUPMODENO); 
		assertNotNull(topProxyNode, "Null value returned from buildNodeTreeMain"); 
		String     topProxyName = topProxyNode.getLastNodePathEntry();
		assertEquals("Top", topProxyName, "Name of the top node is wrong");
		String     topProxyTooltip = topProxyNode.getTooltip();
		assertEquals("Top node of the node tree", topModTooltip, "Tooltip of the top node is wrong");
		ArrayList<HDLmTree>  topProxyChildren = topProxyNode.getChildren();
		int        proxyChildrenSize = topProxyChildren.size();
		assertEquals(7, proxyChildrenSize, "Incorrect number of children for top node");
		HDLmTreeTypes  topProxyType = topProxyNode.getType();
		assertEquals(HDLmTreeTypes.TOP, topProxyType, "Top node has incorrect type");
		int        topProxyLevel = topProxyNode.getNodePathLength();
		assertEquals(1, topProxyLevel, "Top node has incorrect level");
		ArrayList<String>  proxyTopNodePath = topProxyNode.getNodePath();
		int        proxyNodePathSize = proxyTopNodePath.size();
		assertEquals(1, proxyNodePathSize, "Top node has incorrect node path length");
		assertEquals("Top", proxyTopNodePath.get(0), "Top node path has incorrect first entry");
		HDLmMod    topProxyDetails = topProxyNode.getDetails();
		assertNull(topProxyDetails, "Top node has non-null details");	
	}
}
package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmTree2Test short summary.
 *
 * HDLmTree2Test description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmTree2Test {
  @BeforeAll
	static void HDLmTreeBeforeAll() {
 		/* Build the tree of HDLmTree nodes from some JSON */
 		HDLmTree  modTopTree = HDLmTree.addToTreeMod(HDLmTreeData.jsonGetModStr, HDLmEditorTypes.MOD);
		HDLmTree.setNodeModTreeTop(modTopTree);
		/* Build the tree of HDLmTree modification nodes from some JSON */
		HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		/* Build the tree of HDLmTree proxy definition nodes from some JSON */
		HDLmTree.addToTree(HDLmTreeData.jsonGetProxyStr, HDLmEditorTypes.PROXY, HDLmStartupMode.STARTUPMODENO);
	}
	@Test
	void HDLmTreeClassTest() {
		/* Run a few HDLmTree class tests */
		assertNotEquals(null, HDLmTree.getNodeModTreeTop(), "HDLmTree.modTreetop was null");
		assertNotNull(HDLmTree.getNodeModTreeTop(), "HDLmTree.modTreetop was null");
	}
	@Test
	void HDLmTreeConstructor() {
		/* Run a few HDLmTree constructor tests */
		String   newModName = "New Mod";
		ArrayList<String> newTreeArray = new ArrayList<String>(
			List.of("Top", "Legends", "example.com", "example.com", "New Mod"));
		HDLmTree   newTree = new HDLmTree(HDLmTreeTypes.MOD,
				                              "tip",
				                              newTreeArray);
		assertEquals(newModName, newTree.getLastNodePathEntry(), "HDLmTree name was not '" + newModName + "'");
		assertEquals(HDLmTreeTypes.MOD, newTree.getType(), "HDLmTree type was not 'mod'");
		assertEquals(5, newTree.getNodePathLength(), "HDLmTree level was not (5)");
		assertEquals("tip", newTree.getTooltip(), "HDLmTree tooltip was not 'tip'");
		assertEquals(null, newTree.getDetails(), "HDLmTree details was not null");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {new HDLmTree(null,
				                                                     "tip",
				                                                     newTreeArray);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New type value passed to tree object constructor is null", execMsg,
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {new HDLmTree(HDLmTreeTypes.NONE,
				                                                     "tip",
				                                                     newTreeArray);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("New type value passed to tree object constructor is not set", execMsg,
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {new HDLmTree(HDLmTreeTypes.SITE,
				                                                     null,
				                                                     newTreeArray);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New tooltip value passed to tree object constructor is null", execMsg,
				         	 "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {new HDLmTree(HDLmTreeTypes.SITE,
				                                                     "tip",
				                                                     null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("New node path value passed to tree object constructor is null", execMsg,
				         	 "Unexpected exception message");
		}
	}
	@Test
	void addOrReplaceChild() {
		/* Run a few addOrReplaceChild tests */
		ArrayList<String> newTreeArray = new ArrayList<String>(
	    List.of("Top", "Legends"));
		ArrayList<HDLmTree> childArray;
		HDLmTree   newTree = new HDLmTree(HDLmTreeTypes.COMPANY,
				                              "Company tooltip",
				                              newTreeArray);
		childArray = newTree.getChildren();
		assertEquals(0, childArray.size(), "Child array should be empty");
		newTreeArray = new ArrayList<String>(
		  List.of("Top", "Legends", "example.com"));
		HDLmTree   subTree = new HDLmTree(HDLmTreeTypes.DIVISION,
				                              "Divison tooltip",
				                              newTreeArray);
		newTree.addOrReplaceChild(subTree);
		childArray = newTree.getChildren();
		assertEquals(1, childArray.size(), "Child array should not be empty");
		newTreeArray = new ArrayList<String>(
			List.of("Top", "Legends", "example.com", "example.com"));
	  subTree = new HDLmTree(HDLmTreeTypes.SITE,
					                 "Site tooltip",
					                 newTreeArray);
	  {
	  	HDLmTree   subTreeCopy = subTree;
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {newTree.addOrReplaceChild(subTreeCopy);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Parent level (2) and child level (4) are wrong", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void addTop() {
		/* Run a few addTop tests */
		HDLmTree   topTree = HDLmTree.addTop();
		assertNotNull(topTree, "HDLmTree.top was null");
		assertNotNull(HDLmTree.getNodeModTreeTop(), "HDLmTree.modTreeTop was null");
		assertTrue((topTree instanceof HDLmTree), "Tree top is not an instance of HDLmTree");
		/* Rebuild the tree of HDLmTree modification nodes from some JSON */
		HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
	}
	@Test
	void addToTree() {
		/* Run a few addToTree tests */
		HDLmTree   subTree;
		HDLmTree   topTree;
		String     companyName;
		/* Build the tree of HDLmTree modification nodes from some JSON */
		topTree = HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		assertEquals(HDLmTreeTypes.TOP, topTree.getType(), "Top tree node should have a type of 'TOP'");
		assertEquals(1, topTree.getNodePathLength(), "Top tree node should be level 1");
		assertEquals("Top", topTree.getLastNodePathEntry(), "Top tree node should have a name of 'Top'");
		assertEquals("Top node of the node tree", topTree.getTooltip(), "Top tree node should have a correct tooltip");
		assertNotNull(topTree.getDetails(), "Top tree node must have details");
		assertNotNull(topTree.getDetails(), "Top tree node must have details");
		assertEquals(1, topTree.getNodePath().size(), "Top tree node should have a node path with one entry");
		assertEquals("Top", topTree.getNodePath().get(0), "Top tree node should have a node path that is correct");
		assertEquals(2, topTree.getChildren().size(), "Top tree node should have two children");
		/* Get the first company node */
		companyName = "bskinz.com";
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(0);
		assertEquals(HDLmTreeTypes.COMPANY, subTree.getType(), "Company tree node should have a type of 'COMPANY'");
		assertEquals(3, subTree.getNodePathLength(), "Company tree node should be level 3");
		assertEquals(companyName, subTree.getLastNodePathEntry(), "Company tree node should have a name of  '" + companyName + "'");
		assertEquals("Company node", subTree.getTooltip(), "Company tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Company tree node must have details");
		assertEquals(3, subTree.getNodePath().size(), "Company tree node should have a node path with three entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Company tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Company tree node should have a node path that is correct");
		int   subTreeChildrenSize = subTree.getChildren().size();
		if (subTreeChildrenSize != 3 && subTreeChildrenSize != 4)	
		  assertEquals(4, subTree.getChildren().size(), "Company tree node should have three or four children");
		/* Get the third company node */
		companyName = "owo.dnsalias.com";
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(2);
		assertEquals(HDLmTreeTypes.COMPANY, subTree.getType(), "Company tree node should have a type of 'COMPANY'");
		assertEquals(3, subTree.getNodePathLength(), "Company tree node should be level 3");
		assertEquals(companyName, subTree.getLastNodePathEntry(), "Company tree node should have a name of  '" + companyName + "'");
		assertEquals("Company node", subTree.getTooltip(), "Company tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Company tree node must have details");
		assertEquals(3, subTree.getNodePath().size(), "Company tree node should have a node path with three entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Company tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Company tree node should have a node path that is correct");
		subTreeChildrenSize = subTree.getChildren().size();
		if (subTreeChildrenSize != 3 && subTreeChildrenSize != 4)	
		  assertEquals(4, subTree.getChildren().size(), "Company tree node should have three or four children");
		/* Get the sixth company node */
		companyName = "www.oldrules.com";
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(5);
		assertEquals(HDLmTreeTypes.COMPANY, subTree.getType(), "Company tree node should have a type of 'COMPANY'");
		assertEquals(3, subTree.getNodePathLength(), "Company tree node should be level 3");
		assertEquals(companyName, subTree.getLastNodePathEntry(), "Company tree node should have a name of  '" + companyName + "'");
		assertEquals("Company node", subTree.getTooltip(), "Company tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Company tree node must have details");
		assertEquals(3, subTree.getNodePath().size(), "Company tree node should have a node path with three entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Company tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Company tree node should have a node path that is correct");
		subTreeChildrenSize = subTree.getChildren().size();
		if (subTreeChildrenSize != 3 && subTreeChildrenSize != 4)	
		  assertEquals(4, subTree.getChildren().size(), "Company tree node should have three or four children");
		/* Get the division node under the sixth company node */
		companyName = "www.oldrules.com";
		String divisionName = "example.com";
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(5);
		subTree = subTree.getChildren().get(2);
		subTree = subTree.getChildren().get(0);
		assertEquals(HDLmTreeTypes.DIVISION, subTree.getType(), "Division tree node should have a type of 'DIVISION'");
		assertEquals(5, subTree.getNodePathLength(), "Division tree node should be level 5");
		assertEquals(divisionName, subTree.getLastNodePathEntry(), "Division tree node should have a name of  '" + divisionName + "'");
		assertEquals("Division node", subTree.getTooltip(), "Division tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Division tree node must have details");
		assertEquals(5, subTree.getNodePath().size(), "Division tree node should have a node path with five entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Division tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Division tree node should have a node path that is correct");
		assertEquals(divisionName, subTree.getNodePath().get(4), "Division tree node should have a node path that is correct");
		assertEquals(1, subTree.getChildren().size(), "Division tree node should have one child");
		/* Get the site node under the division node under the sixth company node */
		companyName = "www.oldrules.com";
		divisionName = "example.com";
		String siteName = "example.com";
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(5);
		subTree = subTree.getChildren().get(2);
		subTree = subTree.getChildren().get(0);
		subTree = subTree.getChildren().get(0);
		assertEquals(HDLmTreeTypes.SITE, subTree.getType(), "Site tree node should have a type of 'SITE'");
		assertEquals(6, subTree.getNodePathLength(), "Site tree node should be level 6");
		assertEquals(siteName, subTree.getLastNodePathEntry(), "Site tree node should have a name of  '" + siteName + "'");
		assertEquals("Site node", subTree.getTooltip(), "Site tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Site tree node must have details");
		assertEquals(6, subTree.getNodePath().size(), "Site tree node should have a node path with six entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Site tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Site tree node should have a node path that is correct");
		assertEquals(divisionName, subTree.getNodePath().get(4), "Site tree node should have a node path that is correct");
		assertEquals(siteName, subTree.getNodePath().get(5), "Site tree node should have a node path that is correct");
		assertEquals(38, subTree.getChildren().size(), "Site tree node should have thirtyeight children");
		/* Get the modification node under the site node under the division node under the sixth company node */
		companyName = "www.oldrules.com";
		divisionName = "example.com";
		siteName = "example.com";
		String modName = "OWO Buy Tickets Image NYC Inspired";
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(5);
		subTree = subTree.getChildren().get(2);
		subTree = subTree.getChildren().get(0);
		subTree = subTree.getChildren().get(0);
		HDLmTree subTree0 = subTree.getChildren().get(0);
		HDLmTree subTree1 = subTree.getChildren().get(1);
		HDLmTree subTree2 = subTree.getChildren().get(2);
		HDLmTree subTree6 = subTree.getChildren().get(6);
		HDLmTree subTree7 = subTree.getChildren().get(7);
		HDLmTree subTree8 = subTree.getChildren().get(8);
		HDLmTree subTree9 = subTree.getChildren().get(9);
		HDLmTree subTree10 = subTree.getChildren().get(10);
		HDLmTree subTree11 = subTree.getChildren().get(11);
		HDLmTree subTree12 = subTree.getChildren().get(12);
		HDLmTree subTree13 = subTree.getChildren().get(13);
		HDLmTree subTree14 = subTree.getChildren().get(14);
		HDLmTree subTree15 = subTree.getChildren().get(15);
		HDLmTree subTree16 = subTree.getChildren().get(16);
		HDLmTree subTree17 = subTree.getChildren().get(17);
		HDLmTree subTree18 = subTree.getChildren().get(18);
		HDLmTree subTree19 = subTree.getChildren().get(19);
		HDLmTree subTree20 = subTree.getChildren().get(20);
		HDLmTree subTree21 = subTree.getChildren().get(21);
		HDLmTree subTree22 = subTree.getChildren().get(22);
		subTree = subTree.getChildren().get(12);
		assertEquals(HDLmTreeTypes.MOD, subTree.getType(), "Modification tree node should have a type of 'MOD'");
		assertEquals(7, subTree.getNodePathLength(), "Modification tree node should be level 7");
		assertEquals(modName, subTree.getLastNodePathEntry(), "Modification tree node should have a name of  '" + modName + "'");
		assertEquals("Style modification", subTree.getTooltip(), "Modification tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Modification tree node must have details");
		assertEquals(7, subTree.getNodePath().size(), "Modification tree node should have a node path with seven entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Modification tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Modification tree node should have a node path that is correct");
		assertEquals(divisionName, subTree.getNodePath().get(4), "Modification tree node should have a node path that is correct");
		assertEquals(siteName, subTree.getNodePath().get(5), "Modification tree node should have a node path that is correct");
		assertEquals(modName, subTree.getNodePath().get(6), "Modification tree node should have a node path that is correct");
		assertEquals(0, subTree.getChildren().size(), "Modification tree node should have no children");
		/* Check the modification node details */
		HDLmMod modDetails = subTree.getDetails();
		assertEquals(0, modDetails.getFinds().size(), "Modification finds size must be zero");
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null");
		assertNotNull(modDetails.getValues(), "Modification values value must not be null");
		assertEquals(false, modDetails.getEnabled(), "Modification enabled value must be false");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.STYLE, modDetails.getType(), "Modification type value must be style");
		assertEquals(2, modDetails.getValuesCount(), "Modification values count must be two");
		assertNotEquals(null, modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertNotNull(modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNotNull(modDetails.getExtra(), "Modification extra value must not be null");
		assertNotNull(modDetails.getExtra(), "Modification extra value must not be null");
		assertEquals(modName, modDetails.getName(), "Modification name value must be '" + modName + "'");
		assertEquals("/en-US/buy-tickets", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals(null, modDetails.getXPath(), "Modification XPath value must be null");
		assertNull(modDetails.getXPath(), "Modification XPath value must be null");
		/* Check the modification node details */
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(5);
		subTree = subTree.getChildren().get(2);
		subTree = subTree.getChildren().get(0);
		subTree = subTree.getChildren().get(0);
		subTree = subTree.getChildren().get(17);
		modDetails = subTree.getDetails();
		modName = "OWO Buy Tickets Text All";
		assertEquals(0, modDetails.getFinds().size(), "Modification finds size must be zero");
		assertNotNull(modDetails.getValues(), "Modification values value must not be null");
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.TEXTCHECKED, modDetails.getType(), "Modification type value must be 'TEXT");
		assertEquals(10, modDetails.getValuesCount(), "Modification values count must be ten");
		assertEquals(7, modDetails.getParameterNumber(), "Modification parameter number must be two");
		assertNotNull(modDetails.getParameterNumber(), "Modification parameter number must not be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertEquals("Buy Now", modDetails.getExtra(), "Modification extra value must be null");
		assertNotNull(modDetails.getExtra(), "Modification extra value must not be null");
		assertEquals(modName, modDetails.getName(), "Modification name value must be '" + modName + "'");
		assertEquals("/en-US/buy-tickets", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals(null, modDetails.getXPath(), "Modification XPath value must be null");
		assertNull(modDetails.getXPath(), "Modification XPath value must be null");
		/* Build the tree of HDLmTree proxy definition nodes from some JSON */
		topTree = HDLmTree.addToTree(HDLmTreeData.jsonGetProxyStr, HDLmEditorTypes.PROXY, HDLmStartupMode.STARTUPMODENO);
		assertEquals(HDLmTreeTypes.TOP, topTree.getType(), "Top tree node should have a type of 'TOP'");
		assertEquals(1, topTree.getNodePathLength(), "Top tree node should be level 1");
		assertEquals("Top", topTree.getLastNodePathEntry(), "Top tree node should have a name of 'Top'");
		assertEquals("Top node of the node tree", topTree.getTooltip(), "Top tree node should have a correct tooltip");
		assertEquals(null, topTree.getDetails(), "Top tree node should not have any details");
		assertNull(topTree.getDetails(), "Top tree node should not have any details");
		assertEquals(1, topTree.getNodePath().size(), "Top tree node should have a node path with one entry");
		assertEquals("Top", topTree.getNodePath().get(0), "Top tree node should have a node path that is correct");
		assertEquals(7, topTree.getChildren().size(), "Top tree node should have eight children");
		/* Get the first company node */
		companyName = "oneworldobservatory.com";
		subTree = topTree.getChildren().get(1);
		assertEquals(HDLmTreeTypes.COMPANY, subTree.getType(), "Company tree node should have a type of 'COMPANY'");
		assertEquals(2, subTree.getNodePathLength(), "Company tree node should be level 2");
		assertEquals(companyName, subTree.getLastNodePathEntry(), "Company tree node should have a name of  '" + companyName + "'");
		assertEquals("Company node", subTree.getTooltip(), "Company tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Company tree node must have details");
		assertEquals(2, subTree.getNodePath().size(), "Company tree node should have a node path with two entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Company tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(1), "Company tree node should have a node path that is correct");
		assertEquals(0, subTree.getChildren().size(), "Company tree node should have zero children");
		/* Get the second company node */
		companyName = "owo.dnsalias.com";
		subTree = topTree.getChildren().get(2);
		assertEquals(HDLmTreeTypes.COMPANY, subTree.getType(), "Company tree node should have a type of 'COMPANY'");
		assertEquals(2, subTree.getNodePathLength(), "Company tree node should be level 2");
		assertEquals(companyName, subTree.getLastNodePathEntry(), "Company tree node should have a name of  '" + companyName + "'");
		assertEquals("Company node", subTree.getTooltip(), "Company tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Company tree node should have any details");
		assertEquals(2, subTree.getNodePath().size(), "Company tree node should have a node path with two entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Company tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(1), "Company tree node should have a node path that is correct");
		assertEquals(0, subTree.getChildren().size(), "Company tree node should have zero children");
		/* Check the proxy definition node details */
		subTree = topTree.getChildren().get(1);
		HDLmProxy  proxyDetails = (HDLmProxy) subTree.getDetails();
		String   proxyName = "oneworldobservatory.com";
		assertEquals(null, proxyDetails.getFinds(), "Proxy definition finds must be null");
		assertNull(proxyDetails.getValues(), "Proxy definition values value must be null");
		assertEquals(true, proxyDetails.getEnabled(), "Proxy definition enabled value must be true");
		assertEquals(false, proxyDetails.getPathValueRe(), "Proxy definition regex value must be false");
		assertNotEquals(null, proxyDetails.getPathValueMatch(), "Proxy definition match must not be null");
		assertEquals(HDLmModTypes.NONE, proxyDetails.getType(), "Proxy definition type value must be 'NONE");
		assertEquals(HDLmProxyTypes.INJECT, proxyDetails.getProxyType(), "Proxy definition type value must be 'INJECT");
		assertEquals(0, proxyDetails.getValuesCount(), "Proxy definition values count must be zero");
		assertNull(proxyDetails.getParameterNumber(), "Proxy definition parameter number must be null");
		assertNull(proxyDetails.getParameterNumber(), "Proxy definition parameter number must be null");
		assertEquals(null, proxyDetails.getCssSelector(), "Proxy definition CSS Selected must be null");
		assertNull(proxyDetails.getCssSelector(), "Proxy definition CSS Selected must be null");
		assertEquals(null, proxyDetails.getExtra(), "Proxy definition extra value must be null");
		assertNull(proxyDetails.getExtra(), "Proxy definition extra value must be null");
		assertEquals(proxyName, proxyDetails.getName(), "Proxy definition name value must be '" + proxyName + "'");
		assertNotEquals(null, proxyDetails.getPathValueMatch(), "Proxy definition path value match must not be null");
		assertEquals(null, proxyDetails.getValue(), "Proxy definition value value must be null");
		assertEquals(null, proxyDetails.getValueSuffix(), "Proxy definition value suffix value must be null");
		assertEquals(null, proxyDetails.getXPath(),
				         "Proxy definition XPath value must be null");
		assertEquals("oneworldobservatory.com", proxyDetails.getBackendName(),
                 "Proxy definition backend host name value must be set");
		assertEquals(HDLmProtocolTypes.HTTPS, proxyDetails.getProtocolType(),
				         "Proxy definition protocol type value must be 'HTTPS");
		assertEquals("owo.secure.dnsalias.com", proxyDetails.getSecureServerName(),
                 "Proxy definition secure server host name value must be set");
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.addToTree(null, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON string value passed by caller is null",  execMsg,"Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.addToTree("", null, HDLmStartupMode.STARTUPMODENO);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Editor type value is null",  execMsg,"Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(AssertionError.class,
					                               () -> {HDLmTree.addToTree("", HDLmEditorTypes.NONE, HDLmStartupMode.STARTUPMODENO);},
					                               "Expected AssertionError");
			String execMsg = exception.getMessage();
			assertEquals("Editor type value is invalid",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void buildModDetailsFromJson() {
		/* Run a few buildModDetailsFromJson tests */
		String       proxyName = "OWO Home Bottom Parameters";
    JsonParser   parser = new JsonParser();
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetPassStrSaved1);
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of
       data rows should always be exactly one. */
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString);
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the companies JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the Rules JSON element */
    modsElement = modsArray.get(2);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification JSON element */
    modsElement = modsArray.get(7);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement modsElementDetails = modsObject.get("details");
    JsonObject modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys = modsElementObject.keySet();
    /* Build the modification details */
    HDLmMod modDetails = HDLmTree.buildModDetailsFromJson(modsObject);
		assertEquals(0, modDetails.getFinds().size(), "Modification finds size must be zero");
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null");
		assertNotNull(modDetails.getValues(), "Modification values value must not be null");
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.NOTIFY, modDetails.getType(), "Modification type value must be 'notify'");
		assertEquals(0, modDetails.getValuesCount(), "Modification values count must be zero");
		assertEquals(null, modDetails.getParameterNumber(), "Modification parameter number must be null");
		assertNull(modDetails.getParameterNumber(), "Modification parameter number must be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getExtra(), "Modification extra value must be null");
		assertNull(modDetails.getExtra(), "Modification extra value must be null");
		assertEquals(proxyName, modDetails.getName(), "Modification name value must be '" + proxyName + "'");
		assertEquals("/", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals("/html/body/div[1]/main/section/div/div/div/section/div/div/a", modDetails.getXPath(),
				         "Modification XPath value must not be null");
		assertNotNull(modDetails.getXPath(), "Modification XPath value must be null");
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.buildModDetailsFromJson(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void buildNodePathFromJson() {
		/* Run a few buildNodePathFromJson tests */
		String       ruleName = "OWO Home Bottom Parameters";
    JsonParser   parser = new JsonParser();
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetPassStrSaved1);
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of
       data rows should always be exactly one. */
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString);
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the Companies JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the Rules JSON element */
    modsElement = modsArray.get(2);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification JSON element */
    modsElement = modsArray.get(7);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement modsElementDetails = modsObject.get("details");
    JsonObject modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys = modsElementObject.keySet();
    /* Build the modification node path */
    ArrayList<String> modNodePath = HDLmTree.buildNodePathFromJson(modsObject);
  	String companyName = "oneworldobservatory.com";
		String divisionName = "example.com";
		String siteName = "example.com";
		assertEquals("Top", modNodePath.get(0), "Modification tree node should have a node path that is correct");
		assertEquals(companyName, modNodePath.get(2), "Modification tree node should have a node path that is correct");
		assertEquals(divisionName, modNodePath.get(4), "Modification tree node should have a node path that is correct");
		assertEquals(siteName, modNodePath.get(5), "Modification tree node should have a node path that is correct");
		assertEquals(ruleName, modNodePath.get(6), "Modification tree node should have a node path that is correct");
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.buildNodePathFromJson(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void buildTreeFromJson() {
		/* Run a few buildTreeFromJson tests */
		String       ruleName = "OWO Home Bottom Parameters";
    JsonParser   parser = new JsonParser();
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetPassStrSaved1);
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of
       data rows should always be exactly one. */
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString);
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the companies JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the Rules JSON element */
    modsElement = modsArray.get(2);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification JSON element */
    modsElement = modsArray.get(7);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement modsElementDetails = modsObject.get("details");
    JsonObject modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys = modsElementObject.keySet();
    /* A dummy ID string is constructed here so that the call below
       will not fail. This is not a real ID string. */
    String  dummyIdString = "abcdabcd";
    /* Build the modification tree instance */
    HDLmTree modTree = HDLmTree.buildTreeFromJson(modsObject, dummyIdString, HDLmEditorTypes.PASS);
    String companiesName = "Companies";
  	String companyName = "oneworldobservatory.com";
  	String rulesName = "Rules";
		String divisionName = "example.com";
		String siteName = "example.com";
		HDLmTree subTree = modTree;
		assertEquals(HDLmTreeTypes.MOD, subTree.getType(), "Modification tree node should have a type of 'MOD'");
		assertEquals(7, subTree.getNodePathLength(), "Modification tree node should be level 7");
		assertEquals(ruleName, subTree.getLastNodePathEntry(), "Modification tree node should have a name of  '" + ruleName + "'");
		assertEquals("Notify modification", subTree.getTooltip(), "Modification tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Modification tree node must have details");
		assertEquals(7, subTree.getNodePath().size(), "Modification tree node should have a node path with seven entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Modification tree node should have a node path that is correct");
		assertEquals("Companies", subTree.getNodePath().get(1), "Modification tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Modification tree node should have a node path that is correct");
		assertEquals(rulesName, subTree.getNodePath().get(3), "Modification tree node should have a node path that is correct");
		assertEquals(divisionName, subTree.getNodePath().get(4), "Modification tree node should have a node path that is correct");
		assertEquals(siteName, subTree.getNodePath().get(5), "Modification tree node should have a node path that is correct");
		assertEquals(ruleName, subTree.getNodePath().get(6), "Modification tree node should have a node path that is correct");
		assertEquals(0, subTree.getChildren().size(), "Modification tree node should have no children");
		/* Check the modification node details */
		HDLmMod modDetails = subTree.getDetails();
		assertEquals(0, modDetails.getFinds().size(), "Modification finds size must be zero");
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null");
		assertNotNull(modDetails.getValues(), "Modification values value must not be null");
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.NOTIFY, modDetails.getType(), "Modification type value must be 'NOTIFY'");
		assertEquals(0, modDetails.getValuesCount(), "Modification values count must be zero");
		assertEquals(null, modDetails.getParameterNumber(), "Modification parameter number must be null");
		assertNull(modDetails.getParameterNumber(), "Modification parameter number must be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getExtra(), "Modification extra value must be null");
		assertNull(modDetails.getExtra(), "Modification extra value must be null");
		assertEquals(ruleName, modDetails.getName(), "Modification name value must be '" + ruleName + "'");
		assertEquals("/", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals("/html/body/div[1]/main/section/div/div/div/section/div/div/a", modDetails.getXPath(),
				         "Modification XPath value must not be null");
		assertNotNull(modDetails.getXPath(), "Modification XPath value must not be null");
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.buildTreeFromJson(null,
					                              		                               null,
					                              		                               HDLmEditorTypes.PASS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON element value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void buildTreeFromJsonNotUsed() {
		/* Run a few buildTreeFromJsonNotUsed tests */
		String       ruleName = "OWO Home Bottom Parameters";
    JsonParser   parser = new JsonParser();
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetPassStrSaved1);
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of
       data rows should always be exactly one. */
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString);
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the Companies JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the Rules JSON element */
    modsElement = modsArray.get(2);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification JSON element */
    modsElement = modsArray.get(7);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification details */
    JsonElement modsElementDetails = modsObject.get("details");
    JsonObject modsElementObject = modsElementDetails.getAsJsonObject();
    Set<String>  modsElementKeys = modsElementObject.keySet();
    /* Build the modification tree instance */
    HDLmTree modTree = HDLmTree.buildTreeFromJsonNotUsed(modsObject, HDLmEditorTypes.PASS);
  	String companyName = "oneworldobservatory.com";
		String divisionName = "example.com";
		String siteName = "example.com";
		HDLmTree subTree = modTree;
		assertEquals(HDLmTreeTypes.MOD, subTree.getType(), "Modification tree node should have a type of 'MOD'");
		assertEquals(7, subTree.getNodePathLength(), "Modification tree node should be level 7");
		assertEquals(ruleName, subTree.getLastNodePathEntry(), "Modification tree node should have a name of  '" + ruleName + "'");
		assertEquals("Notify modification", subTree.getTooltip(), "Modification tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Modification tree node must have details");
		assertEquals(7, subTree.getNodePath().size(), "Modification tree node should have a node path with seven entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Modification tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Modification tree node should have a node path that is correct");
		assertEquals(divisionName, subTree.getNodePath().get(4), "Modification tree node should have a node path that is correct");
		assertEquals(siteName, subTree.getNodePath().get(5), "Modification tree node should have a node path that is correct");
		assertEquals(ruleName, subTree.getNodePath().get(6), "Modification tree node should have a node path that is correct");
		assertEquals(0, subTree.getChildren().size(), "Modification tree node should have no children");
		/* Check the modification node details */
		HDLmMod modDetails = subTree.getDetails();
		assertEquals(0, modDetails.getFinds().size(), "Modification finds size must be zero");
		assertNotEquals(null, modDetails.getValues(), "Modification values value must not be null");
		assertNotNull(modDetails.getValues(), "Modification values value must not be null");
		assertEquals(true, modDetails.getEnabled(), "Modification enabled value must be true");
		assertEquals(false, modDetails.getPathValueRe(), "Modification path value regex value must be false");
		assertEquals(HDLmMatchTypes.NONE, modDetails.getPathValueType(), "Modification path value match type must be 'NONE'");
		assertEquals(HDLmModTypes.NOTIFY, modDetails.getType(), "Modification type value must be 'NOTIFY'");
		assertEquals(0, modDetails.getValuesCount(), "Modification values count must be zero");
		assertEquals(null, modDetails.getParameterNumber(), "Modification parameter number must be null");
		assertNull(modDetails.getParameterNumber(), "Modification parameter number must be null");
		assertEquals(null, modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getCssSelector(), "Modification CSS Selected must be null");
		assertNull(modDetails.getExtra(), "Modification extra value must be ('Extra Bott)");
		assertNull(modDetails.getExtra(), "Modification extra value must not be null");
		assertEquals(ruleName, modDetails.getName(), "Modification name value must be '" + ruleName + "'");
		assertEquals("/", modDetails.getPathValue(), "Modification path value must be correct");
		assertEquals(null, modDetails.getValue(), "Modification value value must be null");
		assertEquals(null, modDetails.getValueSuffix(), "Modification value suffix value must be null");
		assertEquals("/html/body/div[1]/main/section/div/div/div/section/div/div/a", modDetails.getXPath(),
				         "Modification XPath value must not be null");
		assertNotNull(modDetails.getXPath(), "Modification XPath value must not be null");
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.buildTreeFromJsonNotUsed(null,
					                              		                                      HDLmEditorTypes.PASS);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON element value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getArrayFromJson() {
		/* Run a few getArrayFromJson tests */
		String       proxyName = "OWO Bottom Parameters";
    JsonParser   parser = new JsonParser();
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetPassStrSaved1);
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of
       data rows should always be exactly one. */
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString);
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the Companies JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the Rules JSON element */
    modsElement = modsArray.get(2);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the children as a JSON array */
    modsArray = HDLmTree.getArrayFromJson(modsObject, "children");
    assertNotNull(modsArray, "Children array should not be null");
    assertFalse(modsArray.isJsonNull(), "Children array should not be JSON null");
    assertTrue(modsArray.isJsonArray(), "Children array should be a JSON array");
    assertEquals(0, modsArray.size(), "The JSON array should be empty");
    modsArray = HDLmTree.getArrayFromJson(modsObject, "notfound");
    assertEquals(null, modsArray, "The JSON array should be a null value");
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getArrayFromJson(null, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object value is null",  execMsg,"Unexpected exception message");
		}
	  {
	  	JsonObject localObject = modsObject;
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getArrayFromJson(localObject, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON member name value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getIntegerFromJson() {
		/* Run a few getIntegerFromJson tests */
		String       proxyName = "OWO Bottom Parameters";
    JsonParser   parser = new JsonParser();
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetPassStrSaved1);
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of
       data rows should always be exactly one. */
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString);
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the Companies JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the Rules JSON element */
    modsElement = modsArray.get(2);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the parameter number as a JSON value */
    Integer  modsParmNumber = HDLmTree.getIntegerFromJson(modsObject, "parameter");
    assertNull(modsParmNumber, "Parameter number should be null");
    modsParmNumber = HDLmTree.getIntegerFromJson(modsObject, "notfound");
    assertEquals(null, modsParmNumber, "The Integer value should be a null value");
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getIntegerFromJson(null, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object value is null",  execMsg,"Unexpected exception message");
		}
	  {
	  	JsonObject localObject = modsObject;
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getIntegerFromJson(localObject, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON member name value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getNodeIndexGE() {
		/* Run a few getNodeIndexGE tests */
		HDLmTree   subTree = null;
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree topTree = HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		/* Get the site node under the division node under the sixth company node */
		String companyName = "www.oldrules.com";
		String divisionName = "example.com";
		String siteName = "example.com";
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(5);
		subTree = subTree.getChildren().get(2);
		subTree = subTree.getChildren().get(0);
		subTree = subTree.getChildren().get(0);
		assertEquals(HDLmTreeTypes.SITE, subTree.getType(), "Site tree node should have a type of 'SITE'");
		assertEquals(6, subTree.getNodePathLength(), "Site tree node should be level 6");
		assertEquals(siteName, subTree.getLastNodePathEntry(), "Site tree node should have a name of  '" + siteName + "'");
		assertEquals("Site node", subTree.getTooltip(), "Site tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Site tree node must have details");
		assertEquals(6, subTree.getNodePath().size(), "Site tree node should have a node path with six entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Site tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Site tree node should have a node path that is correct");
		assertEquals(divisionName, subTree.getNodePath().get(4), "Site tree node should have a node path that is correct");
		assertEquals(siteName, subTree.getNodePath().get(5), "Site tree node should have a node path that is correct");
		assertEquals(38, subTree.getChildren().size(), "Site tree node should have thirtyeight children");
		/* Get the modification nodes under the site node under the division node under the sixth company node */
		ArrayList<HDLmTree> modNodes = subTree.getChildren();
    /* The actual tests follow here */
		assertEquals(0, HDLmTree.getNodeIndexGE(modNodes, " "), "Wrong index value returned");
		assertEquals(12, HDLmTree.getNodeIndexGE(modNodes, "OWO Buy Tickets Image NYC Inspired"),
				         "Wrong index value returned");
		assertEquals(15, HDLmTree.getNodeIndexGE(modNodes, "OWO Buy Tickets Purchase Akk"),
                 "Wrong index value returned");
		assertEquals(17, HDLmTree.getNodeIndexGE(modNodes, "OWO Buy Tickets Text All"),
                 "Wrong index value returned");
		assertEquals(21, HDLmTree.getNodeIndexGE(modNodes, "OWO Experience Beneath Buy Ticketr"),
                 "Wrong index value returned");
    assertEquals(21, HDLmTree.getNodeIndexGE(modNodes, "OWO Experience Beneath Buy Tickets"),
                 "Wrong index value returned");
    assertEquals(-1, HDLmTree.getNodeIndexGE(modNodes, "ZZTOP"),
                 "Wrong index value returned");
    {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getNodeIndexGE(null, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Node array value is null",  execMsg,"Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getNodeIndexGE(modNodes, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getStringFromJson() {
		/* Run a few getStringFromJson tests */
		String       proxyName = "OWO Home Bottom Parameters";
    JsonParser   parser = new JsonParser();
    JsonElement  topNode = parser.parse(HDLmTreeData.jsonGetPassStrSaved1);
    JsonObject   topNodeObject = topNode.getAsJsonObject();
    Set<String>  topKeys = topNodeObject.keySet();
    JsonObject   topObject = topNode.getAsJsonObject();
    /* We need to extract the number of data rows. The number of
       data rows should always be exactly one. */
    Integer dataRowsCount = HDLmTree.getIntegerFromJson(topObject, "rows_returned");
    /* Get the array of data rows from JSON object. Of course, we really only
       have one data row at this point. */
    JsonArray dataRowsArray = HDLmTree.getArrayFromJson(topObject, "data");
    /* We can now obtain the one and only row from the JSON array */
    JsonElement dataRowElement = dataRowsArray.get(0);
    JsonObject  dataRowObject = dataRowElement.getAsJsonObject();
    String contentType = HDLmTree.getStringFromJson(dataRowObject, "content");
    /* We can now extract the actual modifications from the one data row.
       The modifications may actually be an array. We should process just
       the first element of the array.  */
    String modsString = HDLmTree.getStringFromJson(dataRowObject, "mods");
    JsonElement  modsElement = parser.parse(modsString);
    if (modsElement.isJsonArray()) {
    	JsonArray modsArray = modsElement.getAsJsonArray();
    	modsElement = modsArray.get(0);
    }
    JsonObject  modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    JsonArray  modsArray = modsElement.getAsJsonArray();
    /* Get the Companies JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the company JSON element */
    modsElement = modsArray.get(5);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the Rules JSON element */
    modsElement = modsArray.get(2);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the division JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the site JSON element */
    modsElement = modsArray.get(0);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification JSON element */
    modsElement = modsArray.get(7);
    modsObject = modsElement.getAsJsonObject();
    modsElement = modsObject.get("children");
    modsArray = modsElement.getAsJsonArray();
    /* Get the modification name as a Java string */
    JsonArray   modsNodePath = HDLmTree.getArrayFromJson(modsObject, "nodePath");
    int         modsNodePathLength = modsNodePath.size();
    JsonElement   modsLastNodePathEntry = modsNodePath.get(modsNodePathLength - 1);
    String  modsName = modsLastNodePathEntry.getAsString();
    assertEquals("OWO Home Bottom Parameters", modsName, "Modification Name should not be null");
    assertNotNull(modsName, "Modification Name should not be null");
    modsName = HDLmTree.getStringFromJson(modsObject, "notfound");
    assertEquals(null, modsName, "The String value should be a null value");
    assertNull(modsName, "The String value should be a null value");
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getStringFromJson(null, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON object value is null",  execMsg,"Unexpected exception message");
		}
	  {
	  	JsonObject localObject = modsObject;
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getStringFromJson(localObject, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("JSON member name value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void getTooltipString() {
		String  tooltip = null;
		tooltip = HDLmTree.getTooltipString("newcompmod");
		assertEquals("Company node", tooltip, "Company tooltip should be correct");
		tooltip = HDLmTree.getTooltipString("newtop");
		assertEquals("Top node of the node tree", tooltip, "Top node tooltip should be correct");
		/* This is a test of passing a type that won't match any of the if clauses
		   but will be found in the tree information JSON structure */
		tooltip = HDLmTree.getTooltipString("top");
		assertEquals("Top modification", tooltip, "Top node tooltip should be correct");
		/* This is a test of passing a type that won't match any of the if clauses
	     but will not be found in the tree information JSON structure */
		tooltip = HDLmTree.getTooltipString("toor");
		assertEquals("Toor modification", tooltip, "Toor node tooltip should be correct");
		/* Run a few getTootipString tests */
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.getTooltipString(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Node type value passed to getTooltipString is null", execMsg,
					         "Unexpected exception message");
		}
	}
	@Test
	void hasNode() {
		/* Run a few hasNode tests */
		HDLmTree   subTree = null;
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree topTree = HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		/* Get the site node under the division node under the sixth company node */
		String companyName = "www.oldrules.com";
		String divisionName = "example.com";
		String siteName = "example.com";
		subTree = topTree.getChildren().get(0);
		subTree = subTree.getChildren().get(5);
		subTree = subTree.getChildren().get(2);
		subTree = subTree.getChildren().get(0);
		subTree = subTree.getChildren().get(0);
		assertEquals(HDLmTreeTypes.SITE, subTree.getType(), "Site tree node should have a type of 'Site'");
		assertEquals(6, subTree.getNodePathLength(), "Site tree node should be level 6");
		assertEquals(siteName, subTree.getLastNodePathEntry(), "Site tree node should have a name of  '" + siteName + "'");
		assertEquals("Site node", subTree.getTooltip(), "Site tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Site tree node must have details");
		assertEquals(6, subTree.getNodePath().size(), "Site tree node should have a node path with six entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Site tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Site tree node should have a node path that is correct");
		assertEquals(divisionName, subTree.getNodePath().get(4), "Site tree node should have a node path that is correct");
		assertEquals(siteName, subTree.getNodePath().get(5), "Site tree node should have a node path that is correct");
		assertEquals(38, subTree.getChildren().size(), "Site tree node should have thirtyeight children");
		/* Get the modification nodes under the site node under the division node under the sixth company node */
		ArrayList<HDLmTree> modNodes = subTree.getChildren();
    /* The actual tests follow here */
		assertEquals(null, HDLmTree.hasNode(modNodes, " "), "Invalid node name match");
		assertNotNull(HDLmTree.hasNode(modNodes, "OWO Buy Tickets Change Experience"),
				          "Node name should have matched");
		assertNull(HDLmTree.hasNode(modNodes, "OWO Home Bottom Sand"),
               "Invalid node name match");
		assertNotNull(HDLmTree.hasNode(modNodes, "OWO Buy Tickets Change Order"),
                  "Node name should have matched");
		assertNull(HDLmTree.hasNode(modNodes, "OWO Home Top Line Sand"),
               "Invalid node name match");
    assertNotNull(HDLmTree.hasNode(modNodes, "OWO Home Top Line Tickets - US"),
                  "Node name should have matched");
    assertNull(HDLmTree.hasNode(modNodes, "OWO Home Top Line Zebras"),
               "Invalid node name match");
    {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.hasNode(null, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Node array value is null",  execMsg,"Unexpected exception message");
		}
	  {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.hasNode(modNodes, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Name value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void locateTreeNode() {
		/* Run a few locateTreeNode tests */
		HDLmTree   subTree = null;
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree  passTopTree = HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		HDLmTree.setNodePassTreeTop(passTopTree);
		/* Get the site node under the division node under the sixth company node */
		String companyName = "www.oldrules.com";
		String divisionName = "example.com";
		String siteName = "example.com";
		subTree = passTopTree.getChildren().get(0);
		subTree = subTree.getChildren().get(5);
		subTree = subTree.getChildren().get(2);
		subTree = subTree.getChildren().get(0);
		subTree = subTree.getChildren().get(0);
		assertEquals(HDLmTreeTypes.SITE, subTree.getType(), "Site tree node should have a type of 'SITE'");
		assertEquals(6, subTree.getNodePathLength(), "Site tree node should be level 6");
		assertEquals(siteName, subTree.getLastNodePathEntry(), "Site tree node should have a name of  '" + siteName + "'");
		assertEquals("Site node", subTree.getTooltip(), "Site tree node should have a correct tooltip");
		assertNotNull(subTree.getDetails(), "Site tree node must have details");
		assertEquals(6, subTree.getNodePath().size(), "Site tree node should have a node path with six entries");
		assertEquals("Top", subTree.getNodePath().get(0), "Site tree node should have a node path that is correct");
		assertEquals(companyName, subTree.getNodePath().get(2), "Site tree node should have a node path that is correct");
		assertEquals(divisionName, subTree.getNodePath().get(4), "Site tree node should have a node path that is correct");
		assertEquals(siteName, subTree.getNodePath().get(5), "Site tree node should have a node path that is correct");
		assertEquals(38, subTree.getChildren().size(), "Site tree node should have thirtyeight children");
		/* Build a node path for locating a node */
		ArrayList<String>  nodePath = new ArrayList<String>(
		  List.of("Top", "Companies", "oneworldobservatory.com", "Rules", "example.com", "example.com",
					    "OWO Experience Welcome"));
		assertNotNull(HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(),
				                                  nodePath),
                  "Node should have been located");
		nodePath.set(6, "OWO Bottom Texu");
		assertNull(HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(),
				                               nodePath),
               "Node should not have been located");
    {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.locateTreeNode(null,
					                              		                            null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Top tree node value is null",  execMsg,"Unexpected exception message");
		}
    {
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(),
					                              		                            null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals("Node path value is null",  execMsg,"Unexpected exception message");
		}
	}
	@Test
	void setModTreeTop() {
		/* Run a few setModTreeTop tests */
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree modTopTree = HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		HDLmTree.setNodeModTreeTop(modTopTree);
		/* Check the top HDLmTree node */
		HDLmTree   actualTopTree = HDLmTree.getNodeModTreeTop();
		assertNotNull(actualTopTree, "HDLmTree.modTreeTop was null");
		assertNotNull(HDLmTree.getNodeModTreeTop(), "HDLmTree.modTreeTop was null");
		assertTrue((actualTopTree instanceof HDLmTree), "Tree top is not an instance of HDLmTree");
		assertEquals(HDLmTreeTypes.TOP, actualTopTree.getType(), "Top tree node should have a type of 'TOP'");
		assertEquals(1, actualTopTree.getNodePathLength(), "Top tree node should be level 1");
		assertEquals("Top", actualTopTree.getLastNodePathEntry(), "Top tree node should have a name of 'Top'");
		assertEquals("Top node of the node tree", actualTopTree.getTooltip(), "Top tree node should have a correct tooltip");
		assertNotNull(actualTopTree.getDetails(), "Top tree node must have details");
		assertEquals(1, actualTopTree.getNodePath().size(), "Top tree node should have a node path with one entry");
		assertEquals("Top", actualTopTree.getNodePath().get(0), "Top tree node should have a node path that is correct");
		assertEquals(2, actualTopTree.getChildren().size(), "Top tree node should have two children");
	}
}
package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * HDLmFindTest short summary.
 *
 * HDLmFindTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmFindTest {
  @BeforeAll
	static void HDLmFindBeforeAll() {
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree  modTopTree = HDLmTree.addToTreeMod(HDLmTreeData.jsonGetModStr, HDLmEditorTypes.MOD);
		HDLmTree.setNodeModTreeTop(modTopTree);
		/* Build the tree of HDLmTree nodes from some JSON */
		HDLmTree  passTopTree = HDLmTree.addToTree(HDLmTreeData.jsonGetPassStr, HDLmEditorTypes.PASS, HDLmStartupMode.STARTUPMODENO);
		HDLmTree.setNodePassTreeTop(passTopTree);
	}
	@Test
	void HDLmFindConstructor() {
		/* Run a few HDLmfind constructor tests */
		String     newTag;   
		String     newAttribute;
		String     newValue;
		HDLmFind   newFind = new HDLmFind("tag", "attribute", "value");
		newTag = newFind.getTag();
		newAttribute = newFind.getAttribute();
		newValue = newFind.getValue();
		assertNotNull(newFind, "New find reference is null"); 
		assertEquals("tag", newTag, "Find tag value is not correct");
		assertEquals("attribute", newAttribute, "Find attribute value is not correct");
		assertEquals("value", newValue, "Find value is not correct");
		/* Make a copy of the current find instance and check it */
		newFind = new HDLmFind(newFind);
		newTag = newFind.getTag();
		newAttribute = newFind.getAttribute();
		newValue = newFind.getValue();
		assertNotNull(newFind, "New find reference is null"); 
		assertEquals("tag", newTag, "Find tag value is not correct");
		assertEquals("attribute", newAttribute, "Find attribute value is not correct");
		assertEquals("value", newValue, "Find value is not correct");
		/* Pass a null find reference to the find constructor */ 
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {new HDLmFind(null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Old find reference passed to find constructor is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void findOneLevel() {
		/* Run a few findOneLevel tests */
		/* Get the HTML string and build a document from it */
	  String     htmlStr = HDLmTreeData.magentoNeveJacketStr();	
	  String     pathValueStr = "/neve-studio-dance-jacket.html";
	  Document   htmlDoc = Jsoup.parse(htmlStr);
	  /* Build a node path for locating a site node */
		ArrayList<String>  nodePath = new ArrayList<String>(
		  List.of("Top", "legends-magento.dnsalias.com", "example.com", "example.com"));
		HDLmTree   locateTree = HDLmTree.locateTreeNode(HDLmTree.getNodeModTreeTop(),
        nodePath);
		assertNotNull(locateTree,
                  "Site Node should have been located");		
		/* Extract the matching modifications */
		ArrayList<HDLmMod>   matchingMods = new ArrayList<HDLmMod>();
		HDLmPassThruStatus   passThru = HDLmPassThruStatus.PASSTHRUNOTOK;
	  /* Try to extract some matching modifications */
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  /* Process each of the extracted modifications */
	  ArrayList<HDLmFind>  findsInfo = null;
	  Element              oneNode;
	  Elements             nodeList;
	  HDLmFind             findInfo;
	  HDLmMod              currentMod;
	  int                  nodeListLen; 
	  String               oneNodeClassName;
	  String               oneNodeId;
	  String               oneNodeTag;
	  String               oneNodeText;
	  String               oneNodeOwnText;
	  String               oneNodeTitle;
	  String               oneNodeType;
	  /* Get the first modification */
	  currentMod = matchingMods.get(0);
	  /* Get the array of finds and get the first find */
	  findsInfo = currentMod.getFinds();
	  findInfo = findsInfo.get(0);
	  /* Build the initial node list */
	  nodeList = new Elements();
	  nodeList.add(htmlDoc);
	  /* Try to find one level */
	  nodeList = HDLmFind.findOneLevel(nodeList, findInfo);   
  	nodeListLen = nodeList.size();  	 
    assertEquals(1, nodeListLen, "Incorrect number of matching nodes");
    /* Get and check the element we just found */
    oneNode = nodeList.get(0);
    oneNodeClassName = oneNode.className();
    oneNodeId = oneNode.id();
    oneNodeTag = oneNode.tag().toString();
    oneNodeText = oneNode.text();
    oneNodeOwnText = oneNode.ownText();
    oneNodeTitle = oneNode.attr("title");
    oneNodeType = oneNode.attr("type");
    assertEquals("action primary tocart", oneNodeClassName, "Incorrect node class name value found");
    assertEquals("product-addtocart-button", oneNodeId, "Incorrect node id value found");
    assertEquals("button", oneNodeTag, "Incorrect node tag value found");
    assertEquals("Add to Cart", oneNodeText, "Incorrect node text value found");
    assertEquals("", oneNodeOwnText, "Incorrect node own text value found");
    assertEquals("Add to Cart", oneNodeTitle, "Incorrect node title value found");
    assertEquals("submit", oneNodeType, "Incorrect node type value found");
		{
			Throwable exception = assertThrows(RuntimeException.class,
					                               () -> {HDLmFind.findOneLevel(null, findInfo);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Node list passed to findOneLevel is null",
					         "Unexpected exception message");
		}
		{
			Elements   nodeListLocal = nodeList;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmFind.findOneLevel(nodeListLocal, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Find reference passed to findOneLevel is null",
					         "Unexpected exception message");
		}
	}
	@Test
	void processFindArray() {
		/* Run a few processFindArray tests */
		/* Get the HTML string and build a document from it */
	  String     htmlStr = HDLmTreeData.magentoNeveJacketStr();	
	  String     pathValueStr = "/neve-studio-dance-jacket.html";
	  Document   htmlDoc = Jsoup.parse(htmlStr);
	  /* Build a node path for locating a site node */
		ArrayList<String>  nodePath = new ArrayList<String>(
		  List.of("Top", "legends-magento.dnsalias.com", "example.com", "example.com"));
		HDLmTree   locateTree = HDLmTree.locateTreeNode(HDLmTree.getNodeModTreeTop(),
        nodePath);
		assertNotNull(locateTree,
                  "Site Node should have been located");		
		/* Extract the matching modifications */
		ArrayList<HDLmMod>   matchingMods = new ArrayList<HDLmMod>();
		HDLmPassThruStatus   passThru = HDLmPassThruStatus.PASSTHRUNOTOK;
	  /* Try to extract some matching modifications */
	  matchingMods = HDLmMod.extractMods(pathValueStr, locateTree, passThru,
	  		                               HDLmUsePathValue.USEPATHVALUEOK);
	  int  extractCount = matchingMods.size();
	  assertEquals(11, extractCount, "Incorrect number of modifications extracted");
	  /* Process each of the extracted modifications */
	  ArrayList<HDLmFind>  findsInfo = null;
	  int                  modCounter = 0;
	  for (HDLmMod currentMod : matchingMods) {
	  	modCounter++;
	  	findsInfo = currentMod.getFinds();
	  	if (findsInfo == null)
	  		continue;	  	 
	  	/* This code does not support searching for matching HTML elements
	  	   using XPath. Some languages (including Java) do not support 
	  	   searching using XPath. */
	  	if (currentMod.getXPath() != null &&
	  			!StringUtils.isWhitespace(currentMod.getXPath()))
	  		continue;
	  	Elements   nodeList = HDLmFind.processFindsArray(htmlDoc, findsInfo);
	  	int        nodeListLen = nodeList.size();
	  	if (modCounter >= 1 && modCounter <= 11)
	  		assertEquals(1, nodeListLen, "Incorrect number of matching nodes");
	  }
		{
			ArrayList<HDLmFind>  findsInfoLocal = findsInfo;
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmFind.processFindsArray(null, findsInfoLocal);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "HTML DOM passed to processFindsArray is null",
					         "Unexpected exception message");
		}
		{
			Throwable exception = assertThrows(RuntimeException.class, 
					                               () -> {HDLmFind.processFindsArray(htmlDoc, null);},
					                               "Expected RuntimeException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Finds array passed to processFindsArray is null",
					         "Unexpected exception message");
		}
	}
}
package com.headlamp;
import com.google.common.cache.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
class HDLmBuildJsCompressBlanks {
  private static final Logger LOG=LoggerFactory.getLogger(HDLmBuildJsCompressBlanks.class);
	private HDLmBuildJsCompressBlanks() {}
  @SuppressWarnings("unused")
  public static String getJsBuildJs(HDLmProtocolTypes protocol,
                                    String secureHostName,
                                    String hostName,
                                    String divisionName,
                                    String siteName,
                                    ArrayList<HDLmMod>mods,
                                    HDLmSession sessionObj,
                                    HDLmLogMatchingTypes logRuleMatching,
                                    String serverName) {
    if (protocol==null) {
		  String  errorText="Protocol string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
		if (hostName==null) {
		  String  errorText="Host name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
		if (divisionName==null) {
		  String  errorText="Division name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
		if (siteName==null) {
		  String  errorText="Site name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
		if (mods==null) {
		  String  errorText="Modifications array passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    if (sessionObj==null) {
		  String  errorText="Session object passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    if (logRuleMatching==null) {
		  String  errorText="Log rule matching reference passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    if (serverName==null) {
		  String  errorText="Server name string passed to getJsBuildJs is null";
      throw new NullPointerException(errorText);
		}
    HDLmBuildLines  builder;
    String          actualJS;
    String          fixedJSName=null;
    boolean         useCreateFixedJS=false;
    String        sessionIdJava=sessionObj.getSessionId();
    String        sessionIndexStr=sessionObj.getIndex();
    String        sessionParametersStr=sessionObj.getParameters();
    ArrayList<Double>sessionParametersArray=HDLmMain.getParametersArray(sessionParametersStr);
    if (1==2) {
      actualJS=HDLmUtility.fileGetContents("HDLmBuildJsCompressBlanksOld.txt");
      return actualJS;
    }
    if (useCreateFixedJS) {
    	fixedJSName=HDLmDefines.getString("HDLMFIXEDFILENAME");
    	boolean   fileExists=HDLmUtility.fileExists(fixedJSName);
    	if (fileExists) {
        actualJS=HDLmUtility.fileGetContents(fixedJSName);
        return actualJS;
    	}
    }
    if (mods.size() ==0&&
    		mods.size() !=0)
      return "<script></script>";
    builder=new HDLmBuildLines("JS");
    builder.addLine("<script>");
    builder.addLine("\"use strict\";");
    builder.addLine("let HDLmNodeIdenTracing={");
    builder.addLine("\"none\":0,");
    builder.addLine("\"off\":1,");
    builder.addLine("\"error\":2,");
    builder.addLine("\"all\":3");
    builder.addLine("};");
    builder.addLine("document.addEventListener(\"keydown\",event=>{");
    builder.addLine("if (event.key=='b'&&event.ctrlKey==true)");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionDebugRulesEnabled\",'true');");
    builder.addLine("if (event.key=='i'&&event.ctrlKey==true)");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionDebugNodeIdenEnabled\",'all');");
    builder.addLine("if (event.key=='m'&&event.ctrlKey==true)");
    builder.addLine("HDLmToggleStyleSheetEnablement();");
    builder.addLine("if (event.key=='q'&&event.ctrlKey==true)");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionPostRuleTracingEnabled\",'true');");
    builder.addLine("if (event.key=='x'&&event.ctrlKey==true)");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionIgnoreProbability\",'true');");
    builder.addLine("});");
    builder.addLine("function HDLmApplyMod(pathValueStr,");
    builder.addLine("curMod,");
    builder.addLine("sessionIdJS,");
    builder.addLine("sessionIndexValue,");
    builder.addLine("parametersArray,");
    builder.addLine("proxyDomain,");
    builder.addLine("hostNameValue,");
    builder.addLine("divisionNameValue,");
    builder.addLine("siteNameValue,");
    builder.addLine("proxySecureDomain,");
    builder.addLine("forceSelectStringValue,");
    builder.addLine("logRuleMatching,");
    builder.addLine("readyState) {");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionRuleInfoHostName\",hostNameValue);");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionRuleInfoDivisionName\",divisionNameValue);");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionRuleInfoSiteName\",siteNameValue);");
    builder.addLine("if (sessionStorage.getItem('HDLmSessionDebugRulesEnabled') =='true')");
    builder.addLine("logRuleMatching=true;");
    builder.addLine("let nodeIdenTracing=HDLmNodeIdenTracing.off;");
    builder.addLine("if (sessionStorage.getItem('HDLmSessionDebugNodeIdenEnabled') =='all')");
    builder.addLine("nodeIdenTracing=HDLmNodeIdenTracing.all;");
    builder.addLine("let postRuleTracing=false;");
    builder.addLine("if (sessionStorage.getItem('HDLmSessionPostRuleTracingEnabled') =='true')");
    builder.addLine("postRuleTracing=true;");
    builder.addLine("let matchFound=false;");
    builder.addLine("let matchError='';");
    builder.addLine("let matchModifiedName=hostNameValue+'/'+divisionNameValue+'/'+siteNameValue+'/'+curMod.name");
    builder.addLine("matchModifiedName=HDLmReplaceInString(matchModifiedName);");
    builder.addLine("let curType=curMod.type;");
    builder.addLine("while (true) {");
    builder.addLine("let postTrace=new Object();");
    builder.addLine("if (curMod.prob<100.0) {");
    builder.addLine("let localRandomValue=Math.random();");
    builder.addLine("let ignoreProbability=sessionStorage.getItem('HDLmSessionIgnoreProbability');");
    builder.addLine("if (ignoreProbability==null)");
    builder.addLine("ignoreProbability='false';");
    builder.addLine("if (localRandomValue*100.0>curMod.prob&&");
    builder.addLine("ignoreProbability!='true') {");
    builder.addLine("matchError='probability';");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,null,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,null,null);");
    builder.addLine("postTrace.matcherror=matchError;");
    builder.addLine("HDLmSendUpdates(localUpdates,'failure','1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (curMod.enabled!=true) {");
    builder.addLine("matchError='disabled';");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,null,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,null,null);");
    builder.addLine("postTrace.matcherror=matchError;");
    builder.addLine("HDLmSendUpdates(localUpdates,'failure','1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("let matchRes;");
    builder.addLine("let matchRe;");
    builder.addLine("if (curMod.pathre===true) {");
    builder.addLine("let curModLen=curMod.path.length;");
    builder.addLine("matchRe=new RegExp(curMod.path.substr(2,curModLen-3));");
    builder.addLine("matchRes=matchRe.test(pathValueStr);");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("postTrace.matchpathre=curMod.pathre;");
    builder.addLine("postTrace.matchpath=curMod.path;");
    builder.addLine("postTrace.matchpathvalue=pathValueStr;");
    builder.addLine("postTrace.matchmatch=matchRes;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("matchRes= (curMod.path===pathValueStr);");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("postTrace.matchpathre=curMod.pathre;");
    builder.addLine("postTrace.matchpath=curMod.path;");
    builder.addLine("postTrace.matchpathvalue=pathValueStr;");
    builder.addLine("postTrace.matchmatch=matchRes;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (matchRes==false) {");
    builder.addLine("matchError='Path value mismatch';");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,null,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,null,null);");
    builder.addLine("postTrace.matcherror=matchError;");
    builder.addLine("HDLmSendUpdates(localUpdates,'failure','1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("if (logRuleMatching==true) {");
    builder.addLine("let errorText=HDLmBuildErrorRule(curMod,'match',pathValueStr);");
    builder.addLine("HDLmBuildError('Trace','Mod',35,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("const fontNames={");
    builder.addLine("'fontcolor':'color',");
    builder.addLine("'fontfamily':'font-family',");
    builder.addLine("'fontkerning':'font-kerning',");
    builder.addLine("'fontsize':'font-size',");
    builder.addLine("'fontstyle':'font-style',");
    builder.addLine("'fontweight':'font-weight'");
    builder.addLine("}");
    builder.addLine("let parameterNumber=-1;");
    builder.addLine("let finalLookupIndex=0;");
    builder.addLine("let lookupValue=-1.0;");
    builder.addLine("let sessionIndexValueUsed=false;");
    builder.addLine("let tempLookupIndex=HDLmGetLookupIndex(curMod.name);");
    builder.addLine("if (typeof(tempLookupIndex) !='undefined'&&");
    builder.addLine("tempLookupIndex!=null) {");
    builder.addLine("lookupValue=sessionIndexValue;");
    builder.addLine("sessionIndexValueUsed=true;");
    builder.addLine("finalLookupIndex=tempLookupIndex;");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("parameterNumber=curMod.parameter;");
    builder.addLine("if (parameterNumber!=null&&");
    builder.addLine("parameterNumber>=0&&");
    builder.addLine("parameterNumber<parametersArray.length)");
    builder.addLine("lookupValue=parametersArray[parameterNumber];");
    builder.addLine("}");
    builder.addLine("let nodeList=HDLmFind(curMod,nodeIdenTracing,postRuleTracing,postTrace);");
    builder.addLine("let nodeListLength=nodeList.length;");
    builder.addLine("if (nodeListLength==0&&curType!='visit') {");
    builder.addLine("matchError='nonodes';");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,null,null);");
    builder.addLine("postTrace.matcherror=matchError;");
    builder.addLine("HDLmSendUpdates(localUpdates,'failure','1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("switch (curType) {");
    builder.addLine("case'attribute':{");
    builder.addLine("let curModExtra=curMod.extra;");
    builder.addLine("let curModExtraArray=curModExtra.split('/');");
    builder.addLine("let attributeName=curModExtraArray[0];");
    builder.addLine("let attributeRequest=curModExtraArray[1]");
    builder.addLine("for (let i=0;i<nodeListLength;i++) {");
    builder.addLine("let curNode=nodeList[i];");
    builder.addLine("HDLmClassAddSpecialClass(curNode,curType,curModExtra);");
    builder.addLine("matchFound=true;");
    builder.addLine("if (HDLmIncrementUpdateCount(curNode,matchModifiedName,readyState) >0)");
    builder.addLine("break;");
    builder.addLine("HDLmIncrementUpdateCount(curNode,matchModifiedName);");
    builder.addLine("if (attributeRequest.toUpperCase() =='USEPROXYHOST') {");
    builder.addLine("let attributeValue=curNode.getAttribute(attributeName);");
    builder.addLine("let oldText=attributeValue;");
    builder.addLine("let nodeURL=new URL(attributeValue);");
    builder.addLine("nodeURL.host=proxyDomain;");
    builder.addLine("let newText=nodeURL.href;");
    builder.addLine("curNode.setAttribute(attributeName,nodeURL.href);");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("oldText,newText);");
    builder.addLine("postTrace.matcherror='attribute';");
    builder.addLine("HDLmSendUpdates(localUpdates,'href','1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'extract':{");
    builder.addLine("for (let i=0;i<nodeListLength;i++) {");
    builder.addLine("let curNode=nodeList[i];");
    builder.addLine("let curModExtra=curMod.extra;");
    builder.addLine("HDLmClassAddSpecialClass(curNode,curType,curModExtra);");
    builder.addLine("matchFound=true;");
    builder.addLine("if (HDLmGetUpdateCount(curNode,matchModifiedName,readyState) >0)");
    builder.addLine("break;");
    builder.addLine("HDLmIncrementUpdateCount(curNode,matchModifiedName);");
    builder.addLine("let oldText;");
    builder.addLine("if (HDLmSavedExtracts.hasOwnProperty(curMod.name))");
    builder.addLine("oldText=HDLmSavedExtracts[curMod.name];");
    builder.addLine("else");
    builder.addLine("oldText=null;");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("oldText,null);");
    builder.addLine("let localReason='extract';");
    builder.addLine("if ((typeof curModExtra) !='undefined'&&");
    builder.addLine("curModExtra!=null&&");
    builder.addLine("curModExtra!='')");
    builder.addLine("localReason=curModExtra;");
    builder.addLine("postTrace.matcherror='extract';");
    builder.addLine("HDLmSendUpdates(localUpdates,localReason,'1.0',postTrace);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'modify':{");
    builder.addLine("if (proxySecureDomain==null) {");
    builder.addLine("let errorText=`No secure host name for (${hostNameValue})`;");
    builder.addLine("HDLmBuildError('Error','Mod',16,errorText);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<nodeListLength;i++) {");
    builder.addLine("let curNode=nodeList[i];");
    builder.addLine("let curModExtra=curMod.extra;");
    builder.addLine("HDLmClassAddSpecialClass(curNode,curType,curModExtra);");
    builder.addLine("if (curModExtra.toUpperCase() !=='FIXIFRAMESRC')");
    builder.addLine("break;");
    builder.addLine("matchFound=true;");
    builder.addLine("if (HDLmGetUpdateCount(curNode,matchModifiedName,readyState) >0)");
    builder.addLine("break;");
    builder.addLine("HDLmIncrementUpdateCount(curNode,matchModifiedName);");
    builder.addLine("let nodeSrc=curNode.getAttribute('src');");
    builder.addLine("let oldText=nodeSrc;");
    builder.addLine("let nodeURL=new URL(nodeSrc);");
    builder.addLine("nodeURL.host=proxySecureDomain;");
    builder.addLine("let newText=nodeURL.href+'&HDLmSessionId='+sessionIdJS;");
    builder.addLine("curNode.setAttribute('src',newText);");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("null,null);");
    builder.addLine("let localReason='modify';");
    builder.addLine("if ((typeof curModExtra) !='undefined'&&");
    builder.addLine("curModExtra!=null&&");
    builder.addLine("curModExtra!='')");
    builder.addLine("localReason=curModExtra;");
    builder.addLine("postTrace.matcherror='modify';");
    builder.addLine("HDLmSendUpdates(localUpdates,localReason,'1.0',postTrace);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'notify':{");
    builder.addLine("let sendUpdates=false;");
    builder.addLine("for (let i=0;i<nodeListLength;i++) {");
    builder.addLine("let nodeSend=nodeList[i];");
    builder.addLine("if (HDLmGetUpdateCount(nodeSend,matchModifiedName,readyState) ==0) {");
    builder.addLine("sendUpdates=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("let curModExtra=curMod.extra;");
    builder.addLine("if (sendUpdates) {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("null,null);");
    builder.addLine("let localReason='notify';");
    builder.addLine("if ((typeof curModExtra) !='undefined'&&");
    builder.addLine("curModExtra!=null&&");
    builder.addLine("curModExtra!='')");
    builder.addLine("localReason=curModExtra;");
    builder.addLine("postTrace.matcherror='notify';");
    builder.addLine("HDLmSendUpdates(localUpdates,localReason,'1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<nodeListLength;i++) {");
    builder.addLine("let curNode=nodeList[i];");
    builder.addLine("HDLmClassAddSpecialClass(curNode,curType,curModExtra);");
    builder.addLine("matchFound=true;");
    builder.addLine("if (HDLmGetUpdateCount(curNode,matchModifiedName,readyState) >0)");
    builder.addLine("break;");
    builder.addLine("HDLmIncrementUpdateCount(curNode,matchModifiedName);");
    builder.addLine("curNode.addEventListener('click', (function() {");
    builder.addLine("return function() {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("if (curMod.valuesCount<=0) {");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("null,null);");
    builder.addLine("}");
    builder.addLine("for (let j=0;j<curMod.valuesCount;j++) {");
    builder.addLine("let searchText=curMod.values[j];");
    builder.addLine("searchText=HDLmModifySearch(searchText);");
    builder.addLine("let searchValue;");
    builder.addLine("if (HDLmSavedNotifies.hasOwnProperty(searchText))");
    builder.addLine("searchValue=HDLmSavedNotifies[searchText];");
    builder.addLine("else");
    builder.addLine("searchValue=null;");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("searchValue,null);");
    builder.addLine("}");
    builder.addLine("let localReason='notify';");
    builder.addLine("let curModExtra=curMod.extra;");
    builder.addLine("if ((typeof curModExtra) !='undefined'&&");
    builder.addLine("curModExtra!=null&&");
    builder.addLine("curModExtra!='')");
    builder.addLine("localReason=curModExtra;");
    builder.addLine("postTrace.matcherror='click';");
    builder.addLine("HDLmSendUpdates(localUpdates,localReason,'1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("})());");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'visit':{");
    builder.addLine("let testFlag=false;");
    builder.addLine("HDLmHandleVisitRequest(curMod.extra,postTrace,testFlag,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod,");
    builder.addLine("pathValueStr);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'changeattrs':");
    builder.addLine("case'changenodes':");
    builder.addLine("case'fontcolor':");
    builder.addLine("case'fontfamily':");
    builder.addLine("case'fontkerning':");
    builder.addLine("case'fontsize':");
    builder.addLine("case'fontstyle':");
    builder.addLine("case'fontweight':");
    builder.addLine("case'height':");
    builder.addLine("case'image':");
    builder.addLine("case'order':");
    builder.addLine("case'remove':");
    builder.addLine("case'replace':");
    builder.addLine("case'script':");
    builder.addLine("case'style':");
    builder.addLine("case'text':");
    builder.addLine("case'textchecked':");
    builder.addLine("case'title':");
    builder.addLine("case'width':{");
    builder.addLine("let newTexts=curMod.values;");
    builder.addLine("let newCount=curMod.valuesCount;");
    builder.addLine("if (lookupValue!=null&&sessionIndexValueUsed==false) {");
    builder.addLine("finalLookupIndex=Math.floor(newCount*lookupValue);");
    builder.addLine("finalLookupIndex=Math.min(finalLookupIndex,newCount-1);");
    builder.addLine("}");
    builder.addLine("let forceSelectFound=false;");
    builder.addLine("let newText;");
    builder.addLine("if (lookupValue!=null) {");
    builder.addLine("if (finalLookupIndex>=0)");
    builder.addLine("newText=newTexts[finalLookupIndex];");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<newCount;i++) {");
    builder.addLine("if (newTexts[i].startsWith(forceSelectStringValue)) {");
    builder.addLine("newText=newTexts[i].substring(forceSelectStringValue.length);");
    builder.addLine("finalLookupIndex=i;");
    builder.addLine("forceSelectFound=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (lookupValue==null&&");
    builder.addLine("forceSelectFound==false) {");
    builder.addLine("matchError='Null lookup value';");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("null,null);");
    builder.addLine("postTrace.matcherror=matchError;");
    builder.addLine("HDLmSendUpdates(localUpdates,'failure','1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<nodeListLength;i++) {");
    builder.addLine("matchError='Fired';");
    builder.addLine("let curNode=nodeList[i];");
    builder.addLine("let curModExtra=curMod.extra;");
    builder.addLine("HDLmClassAddSpecialClass(curNode,curType,curModExtra);");
    builder.addLine("let oldText;");
    builder.addLine("if (curType=='changeattrs') {");
    builder.addLine("oldText=HDLmGetAttributesString(curNode);");
    builder.addLine("}");
    builder.addLine("else if (curType=='changenodes') {");
    builder.addLine("oldText=curNode.outerHTML;");
    builder.addLine("}");
    builder.addLine("else if (curType=='fontcolor'||");
    builder.addLine("curType=='fontfamily'||");
    builder.addLine("curType=='fontkerning'||");
    builder.addLine("curType=='fontsize'||");
    builder.addLine("curType=='fontstyle'||");
    builder.addLine("curType=='fontweight') {");
    builder.addLine("let newName=fontNames[curType];");
    builder.addLine("oldText='';");
    builder.addLine("if (curNode.style.hasOwnProperty(newName))");
    builder.addLine("oldText=curNode.style.getPropertyValue(newName);");
    builder.addLine("else if (curNode.hasAttribute(newName))");
    builder.addLine("oldText=curNode.getAttribute(newName);");
    builder.addLine("}");
    builder.addLine("else if (curType=='height'||");
    builder.addLine("curType=='width') {");
    builder.addLine("oldText='';");
    builder.addLine("if (curNode.hasAttribute(curType))");
    builder.addLine("oldText=curNode.getAttribute(curType);");
    builder.addLine("}");
    builder.addLine("else if (curType=='image') {");
    builder.addLine("oldText='';");
    builder.addLine("if (curNode.hasAttribute('src')) {");
    builder.addLine("oldText=curNode.getAttribute('src');");
    builder.addLine("if (oldText.startsWith('http'))");
    builder.addLine("oldText=HDLmRemoveProtocol(oldText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (curType=='order') {");
    builder.addLine("oldText='';");
    builder.addLine("}");
    builder.addLine("else if (curType=='remove') {");
    builder.addLine("oldText='';");
    builder.addLine("}");
    builder.addLine("else if (curType=='replace') {");
    builder.addLine("oldText='';");
    builder.addLine("}");
    builder.addLine("else if (curType=='script') {");
    builder.addLine("oldText='';");
    builder.addLine("}");
    builder.addLine("else if (curType=='style') {");
    builder.addLine("oldText='';");
    builder.addLine("let curModSplit=HDLmStyleSplitString(curModExtra);");
    builder.addLine("let curModSplitLength=curModSplit.length;");
    builder.addLine("for (let i=0;i<curModSplitLength;i++) {");
    builder.addLine("let curStyle=curModSplit[i];");
    builder.addLine("let curValue='';");
    builder.addLine("if (curNode.hasAttribute('style')) {");
    builder.addLine("if (curNode.style.hasOwnProperty(curStyle))");
    builder.addLine("curValue=curNode.style.getPropertyValue(curStyle);");
    builder.addLine("}");
    builder.addLine("if (curValue==''&&");
    builder.addLine("curNode.hasAttribute(curStyle)) {");
    builder.addLine("curValue=curNode.getAttribute(curStyle);");
    builder.addLine("}");
    builder.addLine("if (curValue!='') {");
    builder.addLine("if (oldText!='')");
    builder.addLine("oldText+=' '");
    builder.addLine("oldText+=curValue;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (curType=='text'||");
    builder.addLine("curType=='textchecked'||");
    builder.addLine("curType=='title') {");
    builder.addLine("oldText=curNode.textContent;");
    builder.addLine("}");
    builder.addLine("if (curType=='textchecked') {");
    builder.addLine("let textMatch=HDLmCheckTextMatches(oldText,curModExtra,");
    builder.addLine("matchError,postTrace,postRuleTracing,");
    builder.addLine("parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod,");
    builder.addLine("pathValueStr);");
    builder.addLine("if (!textMatch) {");
    builder.addLine("matchError='textunequal';");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("matchFound=true;");
    builder.addLine("let matchUpdateCount=HDLmGetUpdateCount(curNode,matchModifiedName,readyState);");
    builder.addLine("if (curType=='changenodes') {");
    builder.addLine("if (matchUpdateCount>255)");
    builder.addLine("break;");
    builder.addLine("let testFlag=true;");
    builder.addLine("let forceBreak=HDLmChangeNodes(curNode,newText,matchUpdateCount,testFlag,sessionIndexValueUsed,");
    builder.addLine("matchError,postTrace,postRuleTracing,");
    builder.addLine("parametersArray,sessionIdJS,");
    builder.addLine("sessionIndexValue,parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,");
    builder.addLine("curMod,pathValueStr,oldText);");
    builder.addLine("if (forceBreak==true)");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("else if (curType=='image') {");
    builder.addLine("if (matchUpdateCount>2)");
    builder.addLine("if (oldText==newText||");
    builder.addLine("oldText.startsWith('data:'))");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("else if (curType=='remove'||");
    builder.addLine("curType=='replace') {");
    builder.addLine("let parentNode=curNode.parentNode;");
    builder.addLine("if (parentNode!=null) {");
    builder.addLine("if (HDLmGetUpdateCount(parentNode,matchModifiedName,readyState) >0)");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (curType=='textchecked') {");
    builder.addLine("if (matchUpdateCount>1)");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("if (matchUpdateCount>0)");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (curType=='remove'||");
    builder.addLine("curType=='replace') {");
    builder.addLine("let parentNode=curNode.parentNode;");
    builder.addLine("HDLmIncrementUpdateCount(parentNode,matchModifiedName);");
    builder.addLine("}");
    builder.addLine("else if (curType=='script') {");
    builder.addLine("if (readyState=='complete')");
    builder.addLine("HDLmIncrementUpdateCount(curNode,matchModifiedName);");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("HDLmIncrementUpdateCount(curNode,matchModifiedName);");
    builder.addLine("}");
    builder.addLine("if (curType=='changeattrs') {");
    builder.addLine("if (newText.trim() !='')");
    builder.addLine("HDLmChangeAttributes(curNode,newText);");
    builder.addLine("}");
    builder.addLine("else if (curType=='changenodes') {");
    builder.addLine("let testFlag=false;");
    builder.addLine("HDLmChangeNodes(curNode,newText,matchUpdateCount,testFlag,sessionIndexValueUsed,");
    builder.addLine("matchError,postTrace,postRuleTracing,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,");
    builder.addLine("curMod,pathValueStr,oldText);");
    builder.addLine("}");
    builder.addLine("else if (curType=='fontcolor'||");
    builder.addLine("curType=='fontfamily'||");
    builder.addLine("curType=='fontkerning'||");
    builder.addLine("curType=='fontsize'||");
    builder.addLine("curType=='fontstyle'||");
    builder.addLine("curType=='fontweight') {");
    builder.addLine("if (curType=='fontsize')");
    builder.addLine("newText=HDLmBuildSuffix(newText,'px');");
    builder.addLine("let newName=fontNames[curType];");
    builder.addLine("curNode.style.setProperty(newName,newText);");
    builder.addLine("}");
    builder.addLine("else if (curType=='height'||");
    builder.addLine("curType=='width') {");
    builder.addLine("newText=HDLmBuildSuffix(newText,'px');");
    builder.addLine("curNode.setAttribute(curType,newText);");
    builder.addLine("}");
    builder.addLine("else if (curType=='image') {");
    builder.addLine("if (newText.startsWith('//'))");
    builder.addLine("curNode.setAttribute('src','https:'+newText);");
    builder.addLine("if (newText.startsWith('data:'))");
    builder.addLine("curNode.setAttribute('src',newText);");
    builder.addLine("if (1==1) {");
    builder.addLine("curNode.style.setProperty('background-repeat','no-repeat');");
    builder.addLine("curNode.style.setProperty('background-size','cover');");
    builder.addLine("curNode.style.setProperty('text-align','center');");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (curType=='order') {");
    builder.addLine("let nodeChildrenLength=curNode.children.length;");
    builder.addLine("let newOrder=HDLmBuildOrder(newText,nodeChildrenLength);");
    builder.addLine("for (let j=0;j<newOrder.length;j++) {");
    builder.addLine("curNode.appendChild(curNode.children[newOrder[j]]);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (curType=='remove') {");
    builder.addLine("if (HDLmCompareCaseInsensitive(newText,'yes'))");
    builder.addLine("curNode.remove();");
    builder.addLine("}");
    builder.addLine("else if (curType=='replace') {");
    builder.addLine("if (newText!='') {");
    builder.addLine("let parentNode=curNode.parentNode;");
    builder.addLine("let newNodeObj=JSON.parse(newText);");
    builder.addLine("let newNode=HDLmBuildNodeFromObject(newNodeObj);");
    builder.addLine("parentNode.replaceChild(newNode,curNode);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (curType=='script') {");
    builder.addLine("if (readyState=='complete') {");
    builder.addLine("let functionStr='HDLmExecute'+HDLmReplaceInString(curMod.name) +finalLookupIndex;");
    builder.addLine("window[functionStr]();");
    builder.addLine("}");
    builder.addLine("matchError=readyState;");
    builder.addLine("}");
    builder.addLine("else if (curType=='style') {");
    builder.addLine("if (curModExtra=='background-image') {");
    builder.addLine("let newData=newText;");
    builder.addLine("if (newData.startsWith('url')) {");
    builder.addLine("}");
    builder.addLine("else if (newData.startsWith('data:')) {");
    builder.addLine("newData='url('+newData+')';");
    builder.addLine("}");
    builder.addLine("else if (newData.startsWith('http')) {");
    builder.addLine("newData='url('+newData+')';");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("if (newData.startsWith('//'))");
    builder.addLine("newData='url(https:'+newData+')';");
    builder.addLine("}");
    builder.addLine("if (1==1) {");
    builder.addLine("curNode.style.setProperty(curModExtra,newData);");
    builder.addLine("curNode.style.setProperty('background-repeat','no-repeat');");
    builder.addLine("curNode.style.setProperty('background-size','cover');");
    builder.addLine("}");
    builder.addLine("if (1==2) {");
    builder.addLine("let finalUrl=HDLmGetBackground(curNode,'junk.jpg');");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("let curModSplit=HDLmStyleSplitString(curModExtra);");
    builder.addLine("let newTextSplit=HDLmStyleFixValues(newText);");
    builder.addLine("for (let i in curModSplit) {");
    builder.addLine("let newValue=newTextSplit[i];");
    builder.addLine("if (newValue=='none')");
    builder.addLine("continue;");
    builder.addLine("curNode.style.setProperty(curModSplit[i],newValue);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (curType=='title'||");
    builder.addLine("curType=='text'||");
    builder.addLine("curType=='textchecked') {");
    builder.addLine("curNode.textContent=newText;");
    builder.addLine("}");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("oldText,newText);");
    builder.addLine("postTrace.matcherror=matchError;");
    builder.addLine("HDLmSendUpdates(localUpdates,curType,'1.0',postTrace);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("default:{");
    builder.addLine("let errorText=\"Invalid modification type value-\"+curType;");
    builder.addLine("HDLmBuildError('Error','Mod',31,errorText);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (matchFound===false) {");
    builder.addLine("if (matchError==='') {");
    builder.addLine("matchError='nomatch';");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("null,null,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("null,null);");
    builder.addLine("postTrace.matcherror=matchError;");
    builder.addLine("HDLmSendUpdates(localUpdates,'failure','1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (matchError!=''&&");
    builder.addLine("logRuleMatching==true) {");
    builder.addLine("let errorText=HDLmBuildErrorRule(curMod,matchError,pathValueStr);");
    builder.addLine("HDLmBuildError('Trace','Mod',2,errorText);");
    builder.addLine("}");
    builder.addLine("return matchFound;");
    builder.addLine("}");
    builder.addLine("function HDLmArrayJoin(curArray,joinChar) {");
    builder.addLine("let rv=\"\";");
    builder.addLine("let arrayType=typeof(curArray);");
    builder.addLine("if (arrayType=='undefined') {");
    builder.addLine("rv='undefined';");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("if (curArray==null) {");
    builder.addLine("rv=null;");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("let arrayLength=curArray.length;");
    builder.addLine("if (arrayLength<=0)");
    builder.addLine("return rv;");
    builder.addLine("for (let i=0;i<arrayLength;i++) {");
    builder.addLine("if (i>0)");
    builder.addLine("rv+=joinChar;");
    builder.addLine("let curValue=curArray[i];");
    builder.addLine("if (curValue==null)");
    builder.addLine("rv+='null';");
    builder.addLine("else");
    builder.addLine("rv+=String(curValue);");
    builder.addLine("}");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("function HDLmBuildErrorRule(curMod,matchError,pathValueStr) {");
    builder.addLine("let errorText=\"Modification \"+matchError+\"-\";");
    builder.addLine("errorText+=\"name (\";");
    builder.addLine("errorText+=curMod.name;");
    builder.addLine("errorText+=\")\";");
    builder.addLine("if (Array.isArray(curMod.find) &&");
    builder.addLine("curMod.find.length>0) {");
    builder.addLine("errorText+=\" key (\";");
    builder.addLine("let findFirst=curMod.find[0];");
    builder.addLine("errorText+=findFirst.attributeName;");
    builder.addLine("errorText+=\")\";");
    builder.addLine("errorText+=\" value (\";");
    builder.addLine("errorText+=findFirst.attributeValue;");
    builder.addLine("errorText+=\")\";");
    builder.addLine("}");
    builder.addLine("errorText+='-'+pathValueStr;");
    builder.addLine("return errorText;");
    builder.addLine("}");
    builder.addLine("function HDLmBuildNodeFromObject(domObj) {");
    builder.addLine("if (domObj.type!='Element')");
    builder.addLine("return null;");
    builder.addLine("if (domObj.tag==null)");
    builder.addLine("return null;");
    builder.addLine("let domNode=document.createElement(domObj.tag);");
    builder.addLine("let attrObj=domObj.attributes;");
    builder.addLine("if (attrObj!=null) {");
    builder.addLine("for (let attrObjName in attrObj) {");
    builder.addLine("domNode.setAttribute(attrObjName,attrObj[attrObjName]);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("let domText=domObj.text;");
    builder.addLine("if (domText!=null) {");
    builder.addLine("let textNode=document.createTextNode(domText);");
    builder.addLine("domNode.appendChild(textNode);");
    builder.addLine("}");
    builder.addLine("let domSubNodes=domObj.subnodes;");
    builder.addLine("if (domSubNodes!=null) {");
    builder.addLine("let domSubNodesLength=domSubNodes.length;");
    builder.addLine("for (let i=0;i<domSubNodesLength;i++) {");
    builder.addLine("let domSubNode=domSubNodes[i];");
    builder.addLine("let domSubNodeNode=HDLmBuildNodeFromObject(domSubNode);");
    builder.addLine("if (domSubNodeNode!=null)");
    builder.addLine("domNode.appendChild(domSubNodeNode);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return domNode;");
    builder.addLine("}");
    builder.addLine("function HDLmBuildOrder(newText,totalLength) {");
    builder.addLine("newText=newText.replace(/,/g,' ');");
    builder.addLine("let newTextArray=newText.split(' ');");
    builder.addLine("let newIntArray=[];");
    builder.addLine("let outIntArray=[];");
    builder.addLine("let tempIntArray= (function(a,b) {while(a--) b[a]=a;return b})(totalLength,[]);");
    builder.addLine("for (let i=0;i<newTextArray.length;i++) {");
    builder.addLine("if (newTextArray[i]=='')");
    builder.addLine("continue;");
    builder.addLine("let tempInt=parseInt(newTextArray[i]);");
    builder.addLine("if (typeof(tempInt) !='number')");
    builder.addLine("continue;");
    builder.addLine("newIntArray.push(tempInt);");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<totalLength;i++)");
    builder.addLine("if (newIntArray.includes(i) ==false)");
    builder.addLine("newIntArray.push(i);");
    builder.addLine("for (let i=0;i<totalLength;i++) {");
    builder.addLine("let ix=tempIntArray.indexOf(newIntArray[i]);");
    builder.addLine("outIntArray.push(ix);");
    builder.addLine("tempIntArray.splice(ix,1);");
    builder.addLine("tempIntArray.push(ix)");
    builder.addLine("}");
    builder.addLine("return outIntArray;");
    builder.addLine("}");
    builder.addLine("function HDLmBuildSuffix(newValue,suffixStr) {");
    builder.addLine("if ((typeof(newValue) =='number') &&");
    builder.addLine("newValue!='')");
    builder.addLine("newValue+=suffixStr;");
    builder.addLine("return newValue");
    builder.addLine("}");
    builder.addLine("function HDLmChangeNodes(curNode,jsonText,matchUpdateCount,testFlag,sessionIndexValueUsed,");
    builder.addLine("matchError,postTrace,postRuleTracing,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,");
    builder.addLine("curMod,pathValueStr,oldText) {");
    builder.addLine("let changesObj=JSON.parse(jsonText);");
    builder.addLine("let forceBreak=false;");
    builder.addLine("for (const keyValue in changesObj) {");
    builder.addLine("if (!changesObj.hasOwnProperty(keyValue))");
    builder.addLine("continue;");
    builder.addLine("let changesValue=changesObj[keyValue];");
    builder.addLine("switch (keyValue) {");
    builder.addLine("case'text':");
    builder.addLine("case'title':{");
    builder.addLine("if (matchUpdateCount>0) {");
    builder.addLine("forceBreak=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (testFlag==false)");
    builder.addLine("curNode.textContent=changesValue;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'textchecked':{");
    builder.addLine("let actualText=curNode.textContent;");
    builder.addLine("let requiredText=changesValue[0];");
    builder.addLine("let changesMatch=HDLmCheckTextMatches(actualText,requiredText,");
    builder.addLine("matchError,postTrace,postRuleTracing,");
    builder.addLine("parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod,");
    builder.addLine("pathValueStr);");
    builder.addLine("if (matchUpdateCount>1) {");
    builder.addLine("forceBreak=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (changesMatch&&testFlag==false)");
    builder.addLine("curNode.textContent=changesValue[1];");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'visit':{");
    builder.addLine("let countHigh=HDLmHandleVisitRequest(changesValue,postTrace,testFlag,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod,");
    builder.addLine("pathValueStr);");
    builder.addLine("if (countHigh==true)");
    builder.addLine("forceBreak=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("default:{");
    builder.addLine("if (matchUpdateCount>0) {");
    builder.addLine("forceBreak=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (changesValue==null) {");
    builder.addLine("if (testFlag==false)");
    builder.addLine("curNode.style.removeProperty(keyValue);");
    builder.addLine("}");
    builder.addLine("else  {");
    builder.addLine("let changesType=typeof changesValue;");
    builder.addLine("if (changesType=='number') {");
    builder.addLine("changesValue=changesValue.toString();");
    builder.addLine("changesValue+='px';");
    builder.addLine("}");
    builder.addLine("if (testFlag==false)");
    builder.addLine("curNode.style.setProperty(keyValue,changesValue);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return forceBreak;");
    builder.addLine("}");
    builder.addLine("function HDLmCheckTextMatches(actualText,requiredText,");
    builder.addLine("matchError,postTrace,postRuleTracing,");
    builder.addLine("parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod,");
    builder.addLine("pathValueStr) {");
    builder.addLine("let rv;");
    builder.addLine("let requiredTextLower=requiredText.toLowerCase();");
    builder.addLine("let actualTextLower=actualText.toLowerCase();");
    builder.addLine("if (actualTextLower.indexOf(requiredTextLower) ===-1) {");
    builder.addLine("if (postRuleTracing==true) {");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("matchError='textunequal';");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,actualText,requiredText);");
    builder.addLine("postTrace.matcherror=matchError;");
    builder.addLine("HDLmSendUpdates(localUpdates,'failure','1.0',postTrace);");
    builder.addLine("}");
    builder.addLine("rv=false;;");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("rv=true;");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("function HDLmClassAddSpecialClass(curNode,curType,extraStr) {");
    builder.addLine("if (curType=='order')");
    builder.addLine("return;");
    builder.addLine("if (curType=='style'&&extraStr=='background-image') {");
    builder.addLine("HDLmClassAddEntry(curNode,'HDLmClassBackground');");
    builder.addLine("return;");
    builder.addLine("}");
    builder.addLine("HDLmClassAddEntry(curNode,'HDLmClassPrimary');");
    builder.addLine("}");
    builder.addLine("function HDLmClassAddCss(passedName,passedRules) {");
    builder.addLine("var styleTitle='HDLmSessionClasses';");
    builder.addLine("var styleVar=document.createElement('style');");
    builder.addLine("styleVar.type='text/css';");
    builder.addLine("styleVar.title=styleTitle;");
    builder.addLine("document.getElementsByTagName('head')[0].appendChild(styleVar);");
    builder.addLine("styleVar.sheet.insertRule(passedName+\"{\"+passedRules+\"}\",0);");
    builder.addLine("var disabledStatus=sessionStorage.getItem(styleTitle+'Disabled');");
    builder.addLine("if (disabledStatus==null)");
    builder.addLine("disabledStatus=true;");
    builder.addLine("if (disabledStatus=='true')");
    builder.addLine("disabledStatus=true;");
    builder.addLine("if (disabledStatus=='false')");
    builder.addLine("disabledStatus=false;");
    builder.addLine("var styleSheetList=document.styleSheets;");
    builder.addLine("for (let i=0;i<styleSheetList.length;i++) {");
    builder.addLine("var styleSheet=styleSheetList[i];");
    builder.addLine("if (styleSheet.title!=null&&");
    builder.addLine("styleSheet.title==styleTitle) {");
    builder.addLine("if (styleSheet.disabled!=disabledStatus)");
    builder.addLine("styleSheet.disabled=disabledStatus;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("function HDLmClassAddEntry(elementNodeReference,newClass) {");
    builder.addLine("const elementClasses=elementNodeReference.classList;");
    builder.addLine("if (elementClasses.length==0)");
    builder.addLine("elementClasses.add(newClass);");
    builder.addLine("else if (elementClasses.contains(newClass) ==false)");
    builder.addLine("elementClasses.add(newClass);");
    builder.addLine("}");
    builder.addLine("function HDLmCompareCaseInsensitive(firstStr,secondStr) {");
    builder.addLine("return firstStr.localeCompare(secondStr,undefined,{ sensitivity:'accent' }) ===0;");
    builder.addLine("}");
    builder.addLine("function HDLmErrorToString(errorObj) {");
    builder.addLine("let newObj={};");
    builder.addLine("if (typeof errorObj==='string') {");
    builder.addLine("newObj.name='';");
    builder.addLine("newObj.message=errorObj;");
    builder.addLine("newObj.reason='exception';");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("newObj.name=errorObj.name;");
    builder.addLine("newObj.message=errorObj.message;");
    builder.addLine("newObj.stack=errorObj.stack;");
    builder.addLine("newObj.reason='exception';");
    builder.addLine("}");
    builder.addLine("return JSON.stringify(newObj);");
    builder.addLine("}");
    builder.addLine("function HDLmFind(curMod,nodeIdenTracing,postRuleTracing,postTrace) {");
    builder.addLine("let nodeList=[];");
    builder.addLine("if (curMod.cssselector!=='') {");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("postTrace.findtype='CSS Selector';");
    builder.addLine("postTrace.findvalue=curMod.cssselector;");
    builder.addLine("}");
    builder.addLine("nodeList=document.querySelectorAll(curMod.cssselector);");
    builder.addLine("}");
    builder.addLine("else if (curMod.xpath!=='') {");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("postTrace.findtype='XPath';");
    builder.addLine("postTrace.findvalue=curMod.xpath;");
    builder.addLine("}");
    builder.addLine("let nodeIter=document.evaluate(curMod.xpath,document,null,");
    builder.addLine("XPathResult.ORDERED_NODE_ITERATOR_TYPE,");
    builder.addLine("null);");
    builder.addLine("let thisNode=nodeIter.iterateNext();");
    builder.addLine("while (thisNode) {");
    builder.addLine("nodeList.push(thisNode);");
    builder.addLine("thisNode=nodeIter.iterateNext();");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (curMod.nodeiden!==null) {");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("postTrace.findtype='Node identifier';");
    builder.addLine("postTrace.findvalue=curMod.nodeiden;");
    builder.addLine("}");
    builder.addLine("nodeList=HDLmFindNodeIden(curMod,nodeIdenTracing,postRuleTracing,postTrace);");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("let findsArray=curMod.find;");
    builder.addLine("nodeList=[document];");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("postTrace.findtype='Finds';");
    builder.addLine("postTrace.findvalues=curMod.find;");
    builder.addLine("}");
    builder.addLine("let findsArrayLength=findsArray.length");
    builder.addLine("for (let i=0;i<findsArrayLength;i++) {");
    builder.addLine("let findEntry=findsArray[i];");
    builder.addLine("nodeList=HDLmFindOneLevel(nodeList,findEntry);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return nodeList;");
    builder.addLine("}");
    builder.addLine("function HDLmFindNodeIden(curMod,nodeIdenTracing,postRuleTracing,postTrace) {");
    builder.addLine("let nodeElement;");
    builder.addLine("let nodeElements=[];");
    builder.addLine("let nodeIden=curMod.nodeiden;");
    builder.addLine("let nodeList=[];");
    builder.addLine("let nodeAttributes=nodeIden.attributes;");
    builder.addLine("let nodeCounts=nodeIden.counts;");
    builder.addLine("let nodeType=nodeIden.type;");
    builder.addLine("let nodeValue=null;");
    builder.addLine("switch (nodeType) {");
    builder.addLine("case'tag':{");
    builder.addLine("let nodeTag=nodeAttributes.tag;");
    builder.addLine("nodeValue=nodeTag;");
    builder.addLine("nodeElements=document.getElementsByTagName(nodeTag);");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("postTrace.nodegetby='tag';");
    builder.addLine("postTrace.nodegetvalue=nodeTag;");
    builder.addLine("postTrace.nodecount=nodeElements.length;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'id':{");
    builder.addLine("let nodeId=nodeAttributes.id;");
    builder.addLine("nodeValue=nodeId;");
    builder.addLine("nodeElement=document.getElementById(nodeId);");
    builder.addLine("if (nodeElement!=null)");
    builder.addLine("nodeElements=[nodeElement];");
    builder.addLine("else");
    builder.addLine("nodeElements=[];");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("postTrace.nodegetby='id';");
    builder.addLine("postTrace.nodegetvalue=nodeId;");
    builder.addLine("postTrace.nodecount=nodeElements.length;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'class':{");
    builder.addLine("let nodeClass;");
    builder.addLine("if (nodeAttributes.hasOwnProperty('bestclass'))");
    builder.addLine("nodeClass=nodeAttributes['bestclass'];");
    builder.addLine("else {");
    builder.addLine("let nodeClassList=nodeAttributes.class;");
    builder.addLine("nodeClass=nodeClassList[0];");
    builder.addLine("}");
    builder.addLine("nodeValue=nodeClass;");
    builder.addLine("nodeElements=document.getElementsByClassName(nodeClass);");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&nodeClass=='')) {");
    builder.addLine("let errorText=`Node identifier - node class is (${nodeClass})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("postTrace.nodegetby='class';");
    builder.addLine("postTrace.nodegetvalue=nodeClass;");
    builder.addLine("postTrace.nodecount=nodeElements.length;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'name':{");
    builder.addLine("let nodeName=nodeAttributes.name;");
    builder.addLine("nodeValue=nodeName;");
    builder.addLine("nodeElements=document.getElementsByName(nodeName);");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("postTrace.nodegetby='name';");
    builder.addLine("postTrace.nodegetvalue=nodeName;");
    builder.addLine("postTrace.nodecount=nodeElements.length;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("default:{");
    builder.addLine("let errorText=\"Invalid node identifier type value-\"+nodeType;");
    builder.addLine("HDLmBuildError('Error','NodeIden',40,errorText);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("let nodeElementsLength=nodeElements.length;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&nodeElementsLength==0)) {");
    builder.addLine("let nodeText=nodeType;");
    builder.addLine("if (nodeValue!=null)");
    builder.addLine("nodeText=nodeType+'/'+nodeValue");
    builder.addLine("let errorText=`Node identifier - get for (${nodeText}) returned (${nodeElementsLength}) nodes`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("let nodeIdenCheckType='full';");
    builder.addLine("if (nodeCounts[nodeType]==1&&nodeElementsLength==1)");
    builder.addLine("nodeIdenCheckType='partial';");
    builder.addLine("nodeList=HDLmFindNodeIdenCheck(nodeElements,");
    builder.addLine("nodeIden,");
    builder.addLine("nodeIdenCheckType,");
    builder.addLine("nodeIdenTracing,");
    builder.addLine("postRuleTracing,");
    builder.addLine("postTrace);");
    builder.addLine("return nodeList;");
    builder.addLine("}");
    builder.addLine("function HDLmFindNodeIdenCheck(nodeElements,");
    builder.addLine("nodeIden,");
    builder.addLine("nodeIdenCheckType,");
    builder.addLine("nodeIdenTracing,");
    builder.addLine("postRuleTracing,");
    builder.addLine("postTrace) {");
    builder.addLine("let nodeList=[];");
    builder.addLine("let nodeCounter=0;");
    builder.addLine("let postTraceName;");
    builder.addLine("let nodeElementsLength=nodeElements.length;");
    builder.addLine("elementLoop:for (let i=0;i<nodeElementsLength;i++) {");
    builder.addLine("let currentElement=nodeElements[i];");
    builder.addLine("let grandParentElement;");
    builder.addLine("let parentElement;");
    builder.addLine("nodeCounter++;");
    builder.addLine("postTraceName='nodetarget';");
    builder.addLine("if (nodeCounter>1)");
    builder.addLine("postTraceName+=String(nodeCounter-1)");
    builder.addLine("let nodeCurrentAttributes=nodeIden.attributes;");
    builder.addLine("let currentMatchValue=HDLmFindNodeIdenMatch(currentElement,");
    builder.addLine("nodeCurrentAttributes,");
    builder.addLine("nodeIdenTracing,");
    builder.addLine("postRuleTracing,");
    builder.addLine("postTrace,");
    builder.addLine("postTraceName);");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&currentMatchValue<0.95)) {");
    builder.addLine("let errorText=`Node identifier - current match value (${currentMatchValue}) for element (${currentElement})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("if (currentMatchValue<0.95)");
    builder.addLine("continue elementLoop;");
    builder.addLine("while (true) {");
    builder.addLine("parentElement=currentElement.parentElement;");
    builder.addLine("if (typeof parentElement=='undefined'||");
    builder.addLine("parentElement==null)");
    builder.addLine("break;");
    builder.addLine("if (nodeIden.hasOwnProperty('parent') ==false)");
    builder.addLine("break;");
    builder.addLine("postTraceName='nodeparent';");
    builder.addLine("if (nodeCounter>1)");
    builder.addLine("postTraceName+=String(nodeCounter-1);");
    builder.addLine("let nodeParentAttributes=nodeIden.parent;");
    builder.addLine("if (typeof nodeParentAttributes=='undefined'||");
    builder.addLine("nodeParentAttributes==null)");
    builder.addLine("break;");
    builder.addLine("let parentMatchValue=HDLmFindNodeIdenMatch(parentElement,");
    builder.addLine("nodeParentAttributes,");
    builder.addLine("nodeIdenTracing,");
    builder.addLine("postRuleTracing,");
    builder.addLine("postTrace,");
    builder.addLine("postTraceName);");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&parentMatchValue<0.95)) {");
    builder.addLine("let errorText=`Node identifier - parent match value (${parentMatchValue}) for element (${parentElement})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("if (parentMatchValue<0.95)");
    builder.addLine("continue elementLoop;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("while (true) {");
    builder.addLine("if (typeof parentElement=='undefined'||");
    builder.addLine("parentElement==null)");
    builder.addLine("break;");
    builder.addLine("if (nodeIden.hasOwnProperty('grandparent') ==false)");
    builder.addLine("break;");
    builder.addLine("grandParentElement=parentElement.parentElement;");
    builder.addLine("if (typeof grandParentElement=='undefined'||");
    builder.addLine("grandParentElement==null)");
    builder.addLine("break;");
    builder.addLine("postTraceName='nodegrandparent';");
    builder.addLine("if (nodeCounter>1)");
    builder.addLine("postTraceName+=String(nodeCounter-1);");
    builder.addLine("let nodeGrandParentAttributes=nodeIden.grandparent;");
    builder.addLine("if (typeof nodeGrandParentAttributes=='undefined'||");
    builder.addLine("nodeGrandParentAttributes==null)");
    builder.addLine("break;");
    builder.addLine("let grandParentMatchValue=HDLmFindNodeIdenMatch(grandParentElement,");
    builder.addLine("nodeGrandParentAttributes,");
    builder.addLine("nodeIdenTracing,");
    builder.addLine("postRuleTracing,");
    builder.addLine("postTrace,");
    builder.addLine("postTraceName);");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&grandParentMatchValue<0.95)) {");
    builder.addLine("let errorText=`Node identifier - grandparent match value (${grandParentMatchValue}) for element (${grandParentElement})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("if (grandParentMatchValue<0.95)");
    builder.addLine("continue elementLoop;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("nodeList.push(currentElement);");
    builder.addLine("}");
    builder.addLine("return nodeList;");
    builder.addLine("}");
    builder.addLine("function HDLmFindNodeIdenMatch(nodeElement,");
    builder.addLine("nodeAttributes,");
    builder.addLine("nodeIdenTracing,");
    builder.addLine("postRuleTracing,");
    builder.addLine("postTrace,");
    builder.addLine("postName) {");
    builder.addLine("let denominator=0.0;");
    builder.addLine("let nodeActualValue;");
    builder.addLine("let nodeAttributeValue;");
    builder.addLine("let nodeAttributeChecks=[];");
    builder.addLine("let numerator=0.0;");
    builder.addLine("let numeratorIncrementValue;");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeActualValue=nodeElement.tagName;");
    builder.addLine("nodeAttributeValue=nodeAttributes.tag");
    builder.addLine("let nodeAttributeCheck=new Object();");
    builder.addLine("nodeAttributeCheck.type='tag';");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributeValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualValue;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (HDLmCompareCaseInsensitive(nodeElement.tagName,nodeAttributes.tag))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("nodeAttributeCheck.matchvalue=traceValue;");
    builder.addLine("postTrace[postName+'tag']=nodeAttributeCheck;");
    builder.addLine("}");
    builder.addLine("if (HDLmCompareCaseInsensitive(nodeElement.tagName,nodeAttributes.tag) ==false) {");
    builder.addLine("return 0.0;");
    builder.addLine("}");
    builder.addLine("let nodeAttributeKeys=Object.keys(nodeAttributes);");
    builder.addLine("let nodeAttributeKeysLength=nodeAttributeKeys.length;");
    builder.addLine("for (let i=0;i<nodeAttributeKeysLength;i++) {");
    builder.addLine("let nodeAttributeKey=nodeAttributeKeys[i];");
    builder.addLine("if (nodeAttributeKey=='bestclass')");
    builder.addLine("continue;");
    builder.addLine("let nodeAttributeCheck=new Object();");
    builder.addLine("numeratorIncrementValue=0.0;");
    builder.addLine("denominator++;");
    builder.addLine("nodeAttributeValue=nodeAttributes[nodeAttributeKey];");
    builder.addLine("if (nodeAttributeKey=='tag') {");
    builder.addLine("nodeActualValue=nodeElement.tagName;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("HDLmCompareCaseInsensitive(nodeActualValue,nodeAttributeValue))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&traceValue!=1.0)) {");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributeValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualValue;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("HDLmCompareCaseInsensitive(nodeActualValue,nodeAttributeValue))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("nodeAttributeCheck.matchvalue=traceValue;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("continue;");
    builder.addLine("if (HDLmCompareCaseInsensitive(nodeActualValue,nodeAttributeValue))");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("else if (nodeAttributeKey=='class') {");
    builder.addLine("if (Array.isArray(nodeAttributeValue) &&");
    builder.addLine("nodeAttributeValue.length>0)");
    builder.addLine("nodeAttributeValue=nodeAttributeValue[0];");
    builder.addLine("let nodeActualValueString=nodeElement.getAttribute('class');");
    builder.addLine("if (nodeActualValueString!=null) {");
    builder.addLine("let nodeActualValueSplitArray=nodeActualValueString.split(' ');");
    builder.addLine("let nodeActualValueSplitArrayLen=nodeActualValueSplitArray.length;");
    builder.addLine("let nodeActualValueSplit=[];");
    builder.addLine("for (let i=0;i<nodeActualValueSplitArrayLen;i++) {");
    builder.addLine("let nodeActualValueSplitValue=nodeActualValueSplitArray[i];");
    builder.addLine("if (nodeActualValueSplitValue.endsWith('\\n')) {");
    builder.addLine("let nodeActualValueSplitValueLen=nodeActualValueSplitValue.length;");
    builder.addLine("nodeActualValueSplitValue=nodeActualValueSplitValue.substr(0,nodeActualValueSplitValueLen-1);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValueSplitValue.length>0)");
    builder.addLine("nodeActualValueSplit.push(nodeActualValueSplitValue);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValueSplit.length>0) {");
    builder.addLine("nodeActualValue=[...nodeActualValueSplit];");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("nodeActualValue=null;");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("nodeActualValue=null;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("nodeActualValue.includes(nodeAttributeValue))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&traceValue!=1.0)) {");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributeValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualValue;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("nodeActualValue.includes(nodeAttributeValue))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("nodeAttributeCheck.matchvalue=traceValue;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("continue;");
    builder.addLine("if (nodeActualValue.includes(nodeAttributeValue))");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("else if (nodeAttributeKey=='innertext') {");
    builder.addLine("let nodeIndexOf;");
    builder.addLine("let nodeInnerText=nodeElement.innerText;");
    builder.addLine("if ((typeof nodeInnerText) =='undefined')");
    builder.addLine("nodeInnerText=null;");
    builder.addLine("if (nodeInnerText!=null) {");
    builder.addLine("nodeIndexOf=nodeInnerText.indexOf('ï¿½');");
    builder.addLine("if (nodeIndexOf>=0)");
    builder.addLine("nodeInnerText=nodeInnerText.substring(0,nodeIndexOf);");
    builder.addLine("nodeIndexOf=nodeInnerText.indexOf('\\n');");
    builder.addLine("if (nodeIndexOf>=0)");
    builder.addLine("nodeInnerText=nodeInnerText.substring(0,nodeIndexOf);");
    builder.addLine("nodeInnerText=nodeInnerText.toLowerCase().trim();");
    builder.addLine("if (nodeInnerText.length>20)");
    builder.addLine("nodeInnerText=nodeInnerText.substring(0,20);");
    builder.addLine("}");
    builder.addLine("nodeActualValue=nodeInnerText;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("HDLmCompareCaseInsensitive(nodeAttributeValue,nodeActualValue))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&traceValue!=1.0)) {");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributeValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualValue;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("HDLmCompareCaseInsensitive(nodeAttributeValue,nodeActualValue))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("nodeAttributeCheck.matchvalue=traceValue;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("continue;");
    builder.addLine("if (HDLmCompareCaseInsensitive(nodeAttributeValue,nodeActualValue))");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("else if (nodeAttributeKey=='phash') {");
    builder.addLine("nodeActualValue=nodeAttributeValue;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("HDLmCompareCaseInsensitive(nodeAttributeValue,nodeActualValue))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&traceValue!=1.0)) {");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributeValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualValue;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("HDLmCompareCaseInsensitive(nodeAttributeValue,nodeActualValue))");
    builder.addLine("traceValue=1.0;");
    builder.addLine("nodeAttributeCheck.matchvalue=traceValue;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("continue;");
    builder.addLine("if (HDLmCompareCaseInsensitive(nodeAttributeValue,nodeActualValue))");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("else if (nodeAttributeKey=='src') {");
    builder.addLine("nodeActualValue=nodeElement.getAttribute(nodeAttributeKey);");
    builder.addLine("let nodePHashCheck=false;");
    builder.addLine("while (true) {");
    builder.addLine("let nodeActualIndex;");
    builder.addLine("let nodeActualPHash;");
    builder.addLine("let nodeActualUrl;");
    builder.addLine("let nodeAttributesPHashSimilarity;");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("break;");
    builder.addLine("nodeActualIndex=nodeActualValue.indexOf('http');");
    builder.addLine("if (nodeActualIndex<0)");
    builder.addLine("break;");
    builder.addLine("nodeActualUrl=HDLmRemoveProtocol(nodeActualValue);");
    builder.addLine("nodeActualPHash=HDLmFindPHash(nodeActualUrl);");
    builder.addLine("if (nodeActualPHash==null) {");
    builder.addLine("HDLmGetPHash(nodeActualUrl);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (nodeAttributes.hasOwnProperty('phash') ==false)");
    builder.addLine("break;");
    builder.addLine("let nodeAttributesPHashValue=nodeAttributes['phash'];");
    builder.addLine("nodeAttributesPHashSimilarity=HDLmHammingDistanceAdjusted(nodeAttributesPHashValue,");
    builder.addLine("nodeActualPHash);");
    builder.addLine("nodePHashCheck=true;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&nodeAttributesPHashSimilarity>=0.10)) {");
    builder.addLine("errorText=`Node identifier - key (perceptual hash) actual (${nodeActualPHash}) expected (${nodeAttributesPHashValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (perceptual hash) comparison value (${nodeAttributesPHashSimilarity})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributesPHashValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualPHash;");
    builder.addLine("nodeAttributeCheck.matchvalue=nodeAttributesPHashSimilarity;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeAttributesPHashSimilarity<0.10) {");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (nodePHashCheck==false) {");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("nodeAttributeValue==nodeActualValue)");
    builder.addLine("traceValue=1.0;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&traceValue!=1.0)) {");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributeValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualValue;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("nodeAttributeValue==nodeActualValue)");
    builder.addLine("traceValue=1.0;");
    builder.addLine("nodeAttributeCheck.matchvalue=traceValue;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("continue;");
    builder.addLine("if (nodeAttributeValue==nodeActualValue)");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (nodeAttributeKey=='style') {");
    builder.addLine("nodeActualValue=nodeElement.getAttribute(nodeAttributeKey);");
    builder.addLine("let nodePHashCheck=false;");
    builder.addLine("while (true) {");
    builder.addLine("let nodeActualIndex;");
    builder.addLine("let nodeActualPHash;");
    builder.addLine("let nodeActualUrl;");
    builder.addLine("let nodeAttributesPHashSimilarity;");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("break;");
    builder.addLine("nodeActualIndex=nodeActualValue.indexOf('background-image');");
    builder.addLine("if (nodeActualIndex<0)");
    builder.addLine("break;");
    builder.addLine("nodeActualIndex=nodeActualValue.indexOf('url(\"http');");
    builder.addLine("if (nodeActualIndex<0)");
    builder.addLine("break;");
    builder.addLine("nodeActualUrl=nodeActualValue.substr(nodeActualIndex+5);");
    builder.addLine("nodeActualIndex=nodeActualUrl.indexOf('\")');");
    builder.addLine("if (nodeActualIndex<0)");
    builder.addLine("break");
    builder.addLine("nodeActualUrl=nodeActualUrl.substring(0,nodeActualIndex);");
    builder.addLine("nodeActualUrl=HDLmRemoveProtocol(nodeActualUrl);");
    builder.addLine("nodeActualPHash=HDLmFindPHash(nodeActualUrl);");
    builder.addLine("if (nodeActualPHash==null) {");
    builder.addLine("HDLmGetPHash(nodeActualUrl);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (nodeAttributes.hasOwnProperty('phash') ==false)");
    builder.addLine("break;");
    builder.addLine("let nodeAttributesPHashValue=nodeAttributes['phash'];");
    builder.addLine("nodeAttributesPHashSimilarity=HDLmHammingDistanceAdjusted(nodeAttributesPHashValue,");
    builder.addLine("nodeActualPHash);");
    builder.addLine("nodePHashCheck=true;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&nodeAttributesPHashSimilarity>=0.10)) {");
    builder.addLine("errorText=`Node identifier - key (perceptual hash) actual (${nodeActualPHash}) expected (${nodeAttributesPHashValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (perceptual hash) comparison value (${nodeAttributesPHashSimilarity})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributesPHashValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualPHash;");
    builder.addLine("nodeAttributeCheck.matchvalue=nodeAttributesPHashSimilarity;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeAttributesPHashSimilarity<0.10) {");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (nodePHashCheck==false) {");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("nodeAttributeValue==nodeActualValue)");
    builder.addLine("traceValue=1.0;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&traceValue!=1.0)) {");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributeValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualValue;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("nodeAttributeValue==nodeActualValue)");
    builder.addLine("traceValue=1.0;");
    builder.addLine("nodeAttributeCheck.matchvalue=traceValue;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("continue;");
    builder.addLine("if (nodeAttributeValue==nodeActualValue)");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("nodeActualValue=nodeElement.getAttribute(nodeAttributeKey);");
    builder.addLine("if (nodeAttributeKey=='href'&&");
    builder.addLine("nodeActualValue!=null)");
    builder.addLine("nodeActualValue=HDLmRemoveHost(nodeActualValue);");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("nodeIdenTracing==HDLmNodeIdenTracing.error) {");
    builder.addLine("let errorText;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("nodeAttributeValue==nodeActualValue)");
    builder.addLine("traceValue=1.0;");
    builder.addLine("if (nodeIdenTracing==HDLmNodeIdenTracing.all||");
    builder.addLine("(nodeIdenTracing==HDLmNodeIdenTracing.error&&traceValue!=1.0)) {");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) actual (${nodeActualValue}) expected (${nodeAttributeValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("errorText=`Node identifier - key (${nodeAttributeKey}) comparison value (${traceValue})`;");
    builder.addLine("HDLmBuildError('Trace','NodeIden',41,errorText);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing) {");
    builder.addLine("nodeAttributeCheck.type=nodeAttributeKey;");
    builder.addLine("nodeAttributeCheck.attributevalue=nodeAttributeValue;");
    builder.addLine("nodeAttributeCheck.actualvalue=nodeActualValue;");
    builder.addLine("let traceValue=0.0;");
    builder.addLine("if (nodeActualValue!=null&&");
    builder.addLine("nodeAttributeValue==nodeActualValue)");
    builder.addLine("traceValue=1.0;");
    builder.addLine("nodeAttributeCheck.matchvalue=traceValue;");
    builder.addLine("nodeAttributeChecks.push(nodeAttributeCheck);");
    builder.addLine("}");
    builder.addLine("if (nodeActualValue==null)");
    builder.addLine("continue;");
    builder.addLine("if (nodeAttributeValue==nodeActualValue)");
    builder.addLine("numeratorIncrementValue=1.0;");
    builder.addLine("}");
    builder.addLine("numerator+=numeratorIncrementValue;");
    builder.addLine("}");
    builder.addLine("if (postRuleTracing)");
    builder.addLine("postTrace[postName]=nodeAttributeChecks;");
    builder.addLine("return numerator/denominator;");
    builder.addLine("}");
    builder.addLine("function HDLmFindOneLevel(nodeList,findEntry) {");
    builder.addLine("let outArray=[];");
    builder.addLine("let nodeListLength=nodeList.length;");
    builder.addLine("for (let i=0;i<nodeListLength;i++) {");
    builder.addLine("let curNode=nodeList[i];");
    builder.addLine("let nodeType=curNode.constructor.name;");
    builder.addLine("if (typeof curNode.getElementById==='function'&&");
    builder.addLine("findEntry.attributeName==='id'&&");
    builder.addLine("findEntry.attributeValue!=='') {");
    builder.addLine("let newNode=curNode.getElementById(findEntry.attributeValue);");
    builder.addLine("if (newNode!==null) {");
    builder.addLine("if (findEntry.tag!=='') {");
    builder.addLine("if (findEntry.tag.toUpperCase() ===newNode.tagName.toUpperCase())");
    builder.addLine("outArray.push(newNode);");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("outArray.push(newNode);");
    builder.addLine("}");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("if (typeof curNode.getElementByClassName==='function'&&");
    builder.addLine("findEntry.attributeName==='class'&&");
    builder.addLine("findEntry.attributeValue!=='') {");
    builder.addLine("let newNodeList=curNode.getElementByClassName(findEntry.attributeValue);");
    builder.addLine("let newNodeListLength=newNodeList.length;");
    builder.addLine("for (let i=0;i<newNodeListLength;i++) {");
    builder.addLine("newNode=newNodeList[i];");
    builder.addLine("if (findEntry.tag!=='') {");
    builder.addLine("if (findEntry.tag.toUpperCase() ===newNode.tagName.toUpperCase())");
    builder.addLine("outArray.push(newNode);");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("outArray.push(newNode);");
    builder.addLine("}");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("if (typeof curNode.getElementsByTagName==='function'&&");
    builder.addLine("findEntry.tag!=='') {");
    builder.addLine("let newNodesList=curNode.getElementsByTagName(findEntry.tag);");
    builder.addLine("let newNodesListLength=newNodesList.length;");
    builder.addLine("if (findEntry.attributeName!==''&&");
    builder.addLine("findEntry.attributeValue!=='') {");
    builder.addLine("for (let i=0;i<newNodesListLength;i++) {");
    builder.addLine("let newNode=newNodesList[i];");
    builder.addLine("if (!newNode.hasAttribute(findEntry.attributeName))");
    builder.addLine("continue;");
    builder.addLine("if (newNode.getAttribute(findEntry.attributeName) !==findEntry.attributeValue)");
    builder.addLine("continue;");
    builder.addLine("outArray.push(newNode);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("for (let i=0;i<newNodesListLength;i++) {");
    builder.addLine("let newNode=newNodesList[i];");
    builder.addLine("outArray.push(newNode);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("let curChildren=curNode.childNodes;");
    builder.addLine("let curChildrenLength=curChildren.length;");
    builder.addLine("for (let i=0;i<curChildrenLength;i++) {");
    builder.addLine("let curChild=curChildren[i];");
    builder.addLine("if (typeof curChild.hasAttribute!=='function')");
    builder.addLine("continue;");
    builder.addLine("if (typeof curChild.getAttribute!=='function')");
    builder.addLine("continue;");
    builder.addLine("if (!curChild.hasAttribute(findEntry.attributeName))");
    builder.addLine("continue;");
    builder.addLine("if (curChild.getAttribute(findEntry.attributeName) !==findEntry.attributeValue)");
    builder.addLine("continue;");
    builder.addLine("outArray.push(curChild);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return outArray;");
    builder.addLine("}");
    builder.addLine("function HDLmFindPHash(urlStr) {");
    builder.addLine("let urlStrMod=urlStr.replace(/\\+/g,' ');");
    builder.addLine("if (HDLmPHashObject.hasOwnProperty(urlStrMod))");
    builder.addLine("return HDLmPHashObject[urlStrMod];");
    builder.addLine("return null;");
    builder.addLine("}");
    builder.addLine("function HDLmGetAllPropertyNames(obj) {");
    builder.addLine("let result=new Set();");
    builder.addLine("while (obj) {");
    builder.addLine("Object.getOwnPropertyNames(obj).forEach(p=>result.add(p));");
    builder.addLine("obj=Object.getPrototypeOf(obj);");
    builder.addLine("}");
    builder.addLine("return[...result];");
    builder.addLine("}");
    builder.addLine("function HDLmGetBackground(domElement,replacementImageName) {");
    builder.addLine("let firstElement=domElement;");
    builder.addLine("let finalUrl=null;");
    builder.addLine("while (domElement!=null) {");
    builder.addLine("let computedStyle=window.getComputedStyle(domElement);");
    builder.addLine("if (computedStyle==null)");
    builder.addLine("break;");
    builder.addLine("let backStr=computedStyle['background-image'];");
    builder.addLine("let backType=typeof backStr;");
    builder.addLine("if (backStr==null||backStr=='none'||backType!='string'||backStr.indexOf('url(') <0) {");
    builder.addLine("domElement=domElement.parentElement;");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("let backLast=backStr.lastIndexOf('/');");
    builder.addLine("if (backLast>0)");
    builder.addLine("finalUrl=backStr.substring(0,backLast+1) +replacementImageName+'\")';");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("return finalUrl;");
    builder.addLine("}");
    builder.addLine("function HDLmGetJsonForEventObject(obj,objName,hostName,pathName,sessionId) {");
    builder.addLine("let objProps=HDLmGetAllPropertyNames(obj);");
    builder.addLine("let rv='{\"eventName\":\"'+objName+'\"'+',\"hostName\":\"'+hostName+'\"'+',\"pathName\":\"'+pathName+'\"';");
    builder.addLine("rv+=',\"sessionId\":\"'+sessionId+'\"';");
    builder.addLine("objProps.forEach(prop=>{");
    builder.addLine("let objValue=obj[prop];");
    builder.addLine("let typeValue=typeof objValue;");
    builder.addLine("let quotes=true;");
    builder.addLine("if (typeValue=='number'||");
    builder.addLine("typeValue=='boolean'||");
    builder.addLine("objValue==null)");
    builder.addLine("quotes=false;");
    builder.addLine("if (typeValue=='string') {");
    builder.addLine("let lengthValue=objValue.length;");
    builder.addLine("if (lengthValue>=2) {");
    builder.addLine("let objValueFirst=objValue.charAt(0);");
    builder.addLine("let objValueLast=objValue.charAt(lengthValue-1);");
    builder.addLine("if (objValueFirst=='{'&&objValueLast=='}') {");
    builder.addLine("quotes=false;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("let oldRvLength=rv.length;");
    builder.addLine("try {");
    builder.addLine("rv+=',\"'+prop+'\":';");
    builder.addLine("if (quotes)");
    builder.addLine("rv+='\"';");
    builder.addLine("rv+=objValue;");
    builder.addLine("if (quotes)");
    builder.addLine("rv+='\"';");
    builder.addLine("}");
    builder.addLine("catch (errorObj) {");
    builder.addLine("rv=rv.substring(0,oldRvLength);");
    builder.addLine("}");
    builder.addLine("});");
    builder.addLine("rv+='}';");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("function HDLmGetJsonForLink(link,hostName,pathName,sessionId) {");
    builder.addLine("let rv='{\"link\":\"'+link+'\"'+',\"hostName\":\"'+hostName+'\"'+',\"pathName\":\"'+pathName+'\"';");
    builder.addLine("rv+=',\"sessionId\":\"'+sessionId+'\"';");
    builder.addLine("rv+='}';");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("function HDLmGetObjectName(obj) {");
    builder.addLine("let result=obj.constructor.name;");
    builder.addLine("return result;");
    builder.addLine("}");
    builder.addLine("function HDLmGetUpdateCount(curNode,matchModifiedName,readyState) {");
    builder.addLine("let attributeName='hdlmupdated'+matchModifiedName;");
    builder.addLine("if (curNode.hasAttribute(attributeName) ==false) {");
    builder.addLine("return 0;");
    builder.addLine("}");
    builder.addLine("let currentCount=curNode.getAttribute(attributeName);");
    builder.addLine("return currentCount;");
    builder.addLine("}");
    builder.addLine("function HDLmHammingDistance(firstVal,secondVal) {");
    builder.addLine("let xorValue=firstVal^secondVal;");
    builder.addLine("let distanceCount=0;");
    builder.addLine("while (xorValue>0) {");
    builder.addLine("xorValue&=xorValue-1;");
    builder.addLine("distanceCount++;");
    builder.addLine("}");
    builder.addLine("return distanceCount;");
    builder.addLine("};");
    builder.addLine("function HDLmHammingDistanceAdjusted(firstVal,secondVal) {");
    builder.addLine("let distanceValue=HDLmHammingDistanceLong(firstVal,secondVal);");
    builder.addLine("return distanceValue/(4.0*firstVal.length);");
    builder.addLine("};");
    builder.addLine("function HDLmHammingDistanceLong(firstVal,secondVal) {");
    builder.addLine("let distanceFinal=0;");
    builder.addLine("let firstSub,secondSub;");
    builder.addLine("while (firstVal.length>0) {");
    builder.addLine("if (firstVal.length>8) {");
    builder.addLine("firstSub=firstVal.substr(0,8);");
    builder.addLine("firstVal=firstVal.substr(8);");
    builder.addLine("secondSub=secondVal.substr(0,8);");
    builder.addLine("secondVal=secondVal.substr(8);");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("firstSub=firstVal;");
    builder.addLine("firstVal='';");
    builder.addLine("secondSub=secondVal;");
    builder.addLine("secondVal='';");
    builder.addLine("}");
    builder.addLine("let firstInt=parseInt(firstSub,16);");
    builder.addLine("let secondInt=parseInt(secondSub,16);");
    builder.addLine("distanceFinal+=HDLmHammingDistance(firstInt,secondInt);");
    builder.addLine("}");
    builder.addLine("return distanceFinal;");
    builder.addLine("};");
    builder.addLine("function HDLmHandleVisitRequest(visitText,postTrace,testFlag,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod,");
    builder.addLine("pathValueStr) {");
    builder.addLine("let countHigh=false;");
    builder.addLine("let updateName='HDLmUpdateCount'+curMod.name;");
    builder.addLine("if (isNaN(window[updateName]))");
    builder.addLine("window[updateName]=0;");
    builder.addLine("else");
    builder.addLine("if (window[updateName]>0)");
    builder.addLine("countHigh=true;");
    builder.addLine("if (countHigh||testFlag)");
    builder.addLine("return countHigh;");
    builder.addLine("window[updateName]+=1;");
    builder.addLine("let localUpdates=new Object();");
    builder.addLine("let oldText=null;");
    builder.addLine("let newText=null;");
    builder.addLine("if ((typeof visitText) !='undefined'&&");
    builder.addLine("visitText!=null&&");
    builder.addLine("visitText!='')");
    builder.addLine("oldText=visitText;");
    builder.addLine("HDLmSaveChange(localUpdates,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parameterNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,curMod.name,");
    builder.addLine("curMod.path,curMod.type,pathValueStr,");
    builder.addLine("oldText,newText);");
    builder.addLine("let localReason=curMod.type;");
    builder.addLine("postTrace.matcherror='visit';");
    builder.addLine("HDLmSendUpdates(localUpdates,localReason,'1.0',postTrace);");
    builder.addLine("return countHigh;");
    builder.addLine("}");
    builder.addLine("function HDLmIncrementUpdateCount(curNode,matchModifiedName) {");
    builder.addLine("let attributeName='hdlmupdated'+matchModifiedName;");
    builder.addLine("if (curNode.hasAttribute(attributeName) ==false) {");
    builder.addLine("curNode.setAttribute(attributeName,1);");
    builder.addLine("return 1;");
    builder.addLine("}");
    builder.addLine("let currentCount=curNode.getAttribute(attributeName);");
    builder.addLine("currentCount++;");
    builder.addLine("curNode.setAttribute(attributeName,currentCount);");
    builder.addLine("return currentCount;");
    builder.addLine("}");
    builder.addLine("function HDLmModifySearch(searchText) {");
    builder.addLine("let searchObj=JSON.parse(searchText);");
    builder.addLine("let searchAttrs=searchObj.attributes;");
    builder.addLine("if (searchAttrs.hasOwnProperty('innertext')) {");
    builder.addLine("let searchInner=searchAttrs.innertext;");
    builder.addLine("let searchIndex=searchInner.indexOf('$');");
    builder.addLine("if (searchIndex>=0) {");
    builder.addLine("delete searchAttrs['innertext'];");
    builder.addLine("searchObj['attributes']=searchAttrs;");
    builder.addLine("};");
    builder.addLine("searchText=JSON.stringify(searchObj);");
    builder.addLine("}");
    builder.addLine("return searchText;");
    builder.addLine("}");
    builder.addLine("function HDLmObtainValue(searchValue) {");
    builder.addLine("let textValue=null;");
    builder.addLine("let localMod={};");
    builder.addLine("if (searchValue.length>0&&searchValue.charAt(0) =='/') {");
    builder.addLine("localMod.cssselector='';");
    builder.addLine("localMod.nodeiden=null;");
    builder.addLine("localMod.xpath=searchValue;");
    builder.addLine("}");
    builder.addLine("else if (searchValue.length>0&&searchValue.charAt(0) =='{') {");
    builder.addLine("localMod.cssselector='';");
    builder.addLine("localMod.nodeiden=JSON.parse(searchValue);");
    builder.addLine("localMod.xpath=\"\";");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("localMod.cssselector=searchValue;");
    builder.addLine("localMod.nodeiden=null;");
    builder.addLine("localMod.xpath='';");
    builder.addLine("}");
    builder.addLine("let localNodeList=HDLmFind(localMod,false,null,null);");
    builder.addLine("let localNodeListLen=localNodeList.length;");
    builder.addLine("for (let i=0;i<localNodeListLen;i++) {");
    builder.addLine("let localNode=localNodeList[i];");
    builder.addLine("textValue=localNode.textContent;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("return textValue;");
    builder.addLine("}");
    builder.addLine("function HDLmRemoveHost(urlStr) {");
    builder.addLine("let urlStrIndexOfColon=urlStr.indexOf(':');");
    builder.addLine("if (urlStrIndexOfColon<0||");
    builder.addLine("urlStrIndexOfColon>6)");
    builder.addLine("return urlStr;");
    builder.addLine("let urlObj=new URL(urlStr);");
    builder.addLine("return urlStr.substring(urlObj.origin.length);");
    builder.addLine("}");
    builder.addLine("function HDLmRemoveProtocol(urlStr) {");
    builder.addLine("let urlIndex=urlStr.indexOf(':');");
    builder.addLine("if (urlIndex<0)");
    builder.addLine("return urlStr;");
    builder.addLine("return urlStr.substring(urlIndex+1);");
    builder.addLine("}");
    builder.addLine("function HDLmReplaceInString(inStr) {");
    builder.addLine("inStr=inStr.replace(/A/g,'\u0e81');");
    builder.addLine("inStr=inStr.replace(/B/g,'\u0e82');");
    builder.addLine("inStr=inStr.replace(/C/g,'\u0e84');");
    builder.addLine("inStr=inStr.replace(/D/g,'\u0e87');");
    builder.addLine("inStr=inStr.replace(/E/g,'\u0e88');");
    builder.addLine("inStr=inStr.replace(/F/g,'\u0e8a');");
    builder.addLine("inStr=inStr.replace(/G/g,'\u0e8d');");
    builder.addLine("inStr=inStr.replace(/H/g,'\u0e94');");
    builder.addLine("inStr=inStr.replace(/I/g,'\u0e97');");
    builder.addLine("inStr=inStr.replace(/J/g,'\u0e99');");
    builder.addLine("inStr=inStr.replace(/K/g,'\u0e9f');");
    builder.addLine("inStr=inStr.replace(/L/g,'\u0ea1');");
    builder.addLine("inStr=inStr.replace(/M/g,'\u0ea3');");
    builder.addLine("inStr=inStr.replace(/N/g,'\u0ea5');");
    builder.addLine("inStr=inStr.replace(/O/g,'\u0ea7');");
    builder.addLine("inStr=inStr.replace(/P/g,'\u0eaa');");
    builder.addLine("inStr=inStr.replace(/Q/g,'\u0eab');");
    builder.addLine("inStr=inStr.replace(/R/g,'\u0ead');");
    builder.addLine("inStr=inStr.replace(/S/g,'\u0eb9');");
    builder.addLine("inStr=inStr.replace(/T/g,'\u0ebb');");
    builder.addLine("inStr=inStr.replace(/U/g,'\u0ebd');");
    builder.addLine("inStr=inStr.replace(/V/g,'\u0ec0');");
    builder.addLine("inStr=inStr.replace(/W/g,'\u0ec4');");
    builder.addLine("inStr=inStr.replace(/X/g,'\u0ec6');");
    builder.addLine("inStr=inStr.replace(/Y/g,'\u0ec8');");
    builder.addLine("inStr=inStr.replace(/Z/g,'\u0ecd');");
    builder.addLine("inStr=inStr.replace(/\\s/g,'\u0ed0');");
    builder.addLine("inStr=inStr.replace(/\\$/g,'\u0ed1');");
    builder.addLine("inStr=inStr.replace(/\\./g,'\u0ed2');");
    builder.addLine("inStr=inStr.replace(/\\//g,'\u0ed3');");
    builder.addLine("inStr=inStr.replace(/\\(/g,'\u0ed4');");
    builder.addLine("inStr=inStr.replace(/\\)/g,'\u0ed5');");
    builder.addLine("return inStr;");
    builder.addLine("}");
    builder.addLine("function HDLmResetStyleSheetEnablement(titleValue,disabledStatus) {");
    builder.addLine("var styleSheetList=document.styleSheets;");
    builder.addLine("for (let i=0;i<styleSheetList.length;i++) {");
    builder.addLine("var styleSheet=styleSheetList[i];");
    builder.addLine("if (styleSheet.title!=null&&");
    builder.addLine("styleSheet.title==titleValue) {");
    builder.addLine("styleSheet.disabled=disabledStatus;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("function HDLmSaveChange(savedUpdates,indexUsed,");
    builder.addLine("sessionIndexValue,parametersArray,sessionIdJS,");
    builder.addLine("parmNumber,lookupValue,");
    builder.addLine("hostNameValue,divisionNameValue,siteNameValue,modName,");
    builder.addLine("modPathValue,modType,");
    builder.addLine("pathValue,oldValue,newValue) {");
    builder.addLine("let updateObj={};");
    builder.addLine("updateObj.indexValue=sessionIndexValue;");
    builder.addLine("updateObj.indexUsed=indexUsed;");
    builder.addLine("updateObj.parameters=HDLmArrayJoin(parametersArray,' ');");
    builder.addLine("updateObj.sessionId=sessionIdJS;");
    builder.addLine("updateObj.parmNumber=parmNumber;");
    builder.addLine("updateObj.lookupValue=lookupValue;");
    builder.addLine("updateObj.hostName=hostNameValue;");
    builder.addLine("updateObj.divisionName=divisionNameValue;");
    builder.addLine("updateObj.siteName=siteNameValue;");
    builder.addLine("updateObj.modName=modName;");
    builder.addLine("updateObj.modPathValue=modPathValue;");
    builder.addLine("updateObj.modType=modType;");
    builder.addLine("updateObj.pathValue=pathValue;");
    builder.addLine("updateObj.oldValue=oldValue;");
    builder.addLine("updateObj.newValue=newValue;");
    builder.addLine("if (!savedUpdates.hasOwnProperty('updates'))");
    builder.addLine("savedUpdates.updates=[];");
    builder.addLine("savedUpdates.updates.push(updateObj);");
    builder.addLine("}");
    builder.addLine("function HDLmStyleFixValues(inputStyles) {");
    builder.addLine("inputStyles=inputStyles.trim();");
    builder.addLine("inputStyles=inputStyles.toLowerCase();");
    builder.addLine("inputStyles=inputStyles.replace(/\\s+/g,' ');");
    builder.addLine("let splitOn;");
    builder.addLine("if (inputStyles.indexOf(';') >=0)");
    builder.addLine("splitOn=';'");
    builder.addLine("else");
    builder.addLine("splitOn=' ';");
    builder.addLine("let inputSplit=inputStyles.split(splitOn);");
    builder.addLine("for (let i in inputSplit) {");
    builder.addLine("let styleValue=inputSplit[i];");
    builder.addLine("if (splitOn==';')");
    builder.addLine("styleValue=styleValue.trim();");
    builder.addLine("if (styleValue=='unchanged'||");
    builder.addLine("styleValue=='novalue'||");
    builder.addLine("styleValue=='none'||");
    builder.addLine("styleValue.trim().length==0) {");
    builder.addLine("styleValue='none';");
    builder.addLine("inputSplit[i]=styleValue;");
    builder.addLine("}");
    builder.addLine("if (Number.isInteger(Number(styleValue)) ==true) {");
    builder.addLine("styleValue+='px';");
    builder.addLine("inputSplit[i]=styleValue;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return inputSplit;");
    builder.addLine("}");
    builder.addLine("function HDLmStyleSplitString(inputString) {");
    builder.addLine("inputString=inputString.trim();");
    builder.addLine("inputString=inputString.toLowerCase();");
    builder.addLine("inputString=inputString.replace(/\\s+/g,' ');");
    builder.addLine("let inputSplit=inputString.split(' ');");
    builder.addLine("return inputSplit;");
    builder.addLine("}");
    builder.addLine("function HDLmToggleStyleSheetEnablement() {");
    builder.addLine("var disabledStatus;");
    builder.addLine("var titleValue='HDLmSessionClasses';");
    builder.addLine("disabledStatus=sessionStorage.getItem(titleValue+'Disabled');");
    builder.addLine("if (disabledStatus==null)");
    builder.addLine("disabledStatus='true';");
    builder.addLine("disabledStatus= (disabledStatus=='true') ?false:true;");
    builder.addLine("sessionStorage.setItem(titleValue+'Disabled',disabledStatus);");
    builder.addLine("HDLmResetStyleSheetEnablement(titleValue,disabledStatus);");
    builder.addLine("}");
    builder.addLine("function HDLmUpdateJsonStr(jsonStr,keyStr,valueStr) {");
    builder.addLine("if (jsonStr==null)");
    builder.addLine("jsonStr='{}';");
    builder.addLine("let jsonObj=JSON.parse(jsonStr);");
    builder.addLine("jsonObj[keyStr]=valueStr;");
    builder.addLine("jsonStr=JSON.stringify(jsonObj);");
    builder.addLine("return jsonStr;");
    builder.addLine("}");
    builder.addLine("function HDLmApplyMods(readyState,HDLmIndexValue) {");
    builder.addLine("let pathValueStr=document.location.pathname;");
    builder.addLine("const modsArray=[");
    int         counter;
    int         modsCount=mods.size();
    String      newLine;
    counter=0;
    for (HDLmMod mod:mods) {
      counter++;
      newLine="".repeat(24);
      newLine+=mod.getJsonSpecialSerializeNulls();
      if (counter<modsCount)
        newLine+=",";
      builder.addLine(newLine);
    }
    String  logRuleMatchingString;
    if (logRuleMatching==HDLmLogMatchingTypes.LOGMATCHINGYES)
      logRuleMatchingString="true";
    else
      logRuleMatchingString="false";
    Double   arrayEntry;
    String   forceSelectString=HDLmDefines.getString("HDLMFORCEVALUE");
    builder.addLine("];");
    builder.addLine("const sessionIdJS='"+sessionIdJava+"';");
    builder.addLine("const parametersArray=HDLmGetParametersArray();");
    builder.addLine("let modsArrayLength=modsArray.length;");
    builder.addLine("for (let i=0;i<modsArrayLength;i++) {");
    builder.addLine("let curMod=modsArray[i];");
    builder.addLine("try {");
    builder.addLine("switch (curMod.type) {");
    builder.addLine("case'extract':{");
    builder.addLine("let nodeList=HDLmFind(curMod,false);");
    builder.addLine("let nodeListLen=nodeList.length;");
    builder.addLine("for (let j=0;j<nodeListLen;j++) {");
    builder.addLine("let curNode=nodeList[j];");
    builder.addLine("if (HDLmSavedExtracts.hasOwnProperty(curMod.name) &&");
    builder.addLine("HDLmSavedExtracts[curMod.name]!=null)");
    builder.addLine("continue;");
    builder.addLine("let oldText=curNode.textContent;");
    builder.addLine("HDLmSavedExtracts[curMod.name]=oldText;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'notify':{");
    builder.addLine("for (let j=0;j<curMod.valuesCount;j++) {");
    builder.addLine("let searchText=curMod.values[j];");
    builder.addLine("searchText=HDLmModifySearch(searchText);");
    builder.addLine("if (HDLmSavedNotifies.hasOwnProperty(searchText) &&");
    builder.addLine("HDLmSavedNotifies[searchText]!=null)");
    builder.addLine("continue;");
    builder.addLine("let searchValue=HDLmObtainValue(searchText);");
    builder.addLine("HDLmSavedNotifies[searchText]=searchValue;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("default:{");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("catch (errorObj) {");
    builder.addLine("console.log(errorObj);");
    builder.addLine("let errorStr=HDLmErrorToString(errorObj);");
    builder.addLine("let nameStr=curMod.name;");
    builder.addLine("let siteNameStr='"+siteName+"';");
    builder.addLine("let divisionNameStr='"+divisionName+"';");
    builder.addLine("let hostNameStr='"+hostName+"';");
    builder.addLine("let builtStr='Modification ('+nameStr+') Host ('+hostNameStr+') Error ('+errorStr+')';");
    builder.addLine("console.log(builtStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'modification',nameStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'siteName',siteNameStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'divisionName',divisionNameStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'hostName',hostNameStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'sessionId',sessionIdJS);");
    builder.addLine("HDLmSendData(errorStr);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<modsArrayLength;i++) {");
    builder.addLine("let curMod=modsArray[i];");
    builder.addLine("try {");
    builder.addLine("HDLmApplyMod(pathValueStr,");
    builder.addLine("curMod,");
    builder.addLine("sessionIdJS,");
    builder.addLine("HDLmIndexValue,");
    builder.addLine("parametersArray,");
    builder.addLine("'"+hostName+"',");
    builder.addLine("'"+hostName+"',");
    builder.addLine("'"+divisionName+"',");
    builder.addLine("'"+siteName+"',");
    if (secureHostName!=null)
      builder.addLine("'"+secureHostName+"',");
    else
      builder.addLine("null,");
    builder.addLine("'"+forceSelectString+"',");
    builder.addLine(""+logRuleMatchingString+",");
    builder.addLine("readyState);");
    builder.addLine("}");
    builder.addLine("catch (errorObj) {");
    builder.addLine("console.log(errorObj);");
    builder.addLine("let errorStr=HDLmErrorToString(errorObj);");
    builder.addLine("let nameStr=curMod.name;");
    builder.addLine("let siteNameStr='"+siteName+"';");
    builder.addLine("let divisionNameStr='"+divisionName+"';");
    builder.addLine("let hostNameStr='"+hostName+"';");
    builder.addLine("let builtStr='Modification ('+nameStr+') Host ('+hostNameStr+') Error ('+errorStr+')';");
    builder.addLine("console.log(builtStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'modification',nameStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'siteName',siteNameStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'divisionName',divisionNameStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'hostName',hostNameStr);");
    builder.addLine("errorStr=HDLmUpdateJsonStr(errorStr,'sessionId',sessionIdJS);");
    builder.addLine("HDLmSendData(errorStr);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("function HDLmBuildError(errSeverity,curType,errNumber,errText) {");
    builder.addLine("let errorStr='';");
    builder.addLine("errorStr+='"+HDLmDefines.getString("HDLMPREFIX") +"'+' ';");
    builder.addLine("errorStr+=errSeverity+' ';");
    builder.addLine("errorStr+=curType+' ';");
    builder.addLine("errorStr+=errNumber.toString() +' ';");
    builder.addLine("errorStr+=errText;");
    builder.addLine("console.log(errorStr);");
    builder.addLine("}");
    builder.addLine("function HDLmChangeAttributes(curNode,jsonText) {");
    builder.addLine("let changesObj=JSON.parse(jsonText);");
    builder.addLine("for (const keyValue in changesObj) {");
    builder.addLine("if (!changesObj.hasOwnProperty(keyValue))");
    builder.addLine("continue;");
    builder.addLine("let changesValue=changesObj[keyValue];");
    builder.addLine("if (changesValue==null)");
    builder.addLine("curNode.removeAttribute(keyValue);");
    builder.addLine("else {");
    builder.addLine("if (keyValue=='class') {");
    builder.addLine("HDLmClassAddEntry(curNode,changesValue);");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("curNode.setAttribute(keyValue,changesValue);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    for (HDLmMod mod:mods) {
    	if (mod.getType() !=HDLmModTypes.SCRIPT)
        continue;
    	String  newName=HDLmMod.replaceInString(mod.getName());
        int   valueCount=mod.getValues().size();
        for (int i=0;i<valueCount;i++) {
          String  curValue=mod.getValues().get(i);
          newLine="function HDLmExecute"+newName+i+"() {";
          builder.addLine(newLine);
          ArrayList<String>curValues=new ArrayList<String>(Arrays.asList(curValue.split("/n")));
          for (int j=0;j<curValues.size();j++) {
            String  curLine=curValues.get(j);
            builder.addLine(""+curLine);
          }
          newLine="}";
          builder.addLine(newLine);
      }
    }
    builder.addLine("function HDLmGetAttributesString(curNode) {");
    builder.addLine("let outputStr='';");
    builder.addLine("if (!curNode.hasAttributes())");
    builder.addLine("return outputStr;");
    builder.addLine("let attrs=curNode.attributes;");
    builder.addLine("let attrsLength=attrs.length;");
    builder.addLine("for (let i=attrsLength-1;i>=0;i--) {");
    builder.addLine("if (outputStr!='')");
    builder.addLine("outputStr+=' ';");
    builder.addLine("outputStr+=attrs[i].name+'='+\"'\"+attrs[i].value+\"'\";");
    builder.addLine("}");
    builder.addLine("return outputStr;");
    builder.addLine("}");
    builder.addLine("function HDLmGetLookupIndex(ruleName) {");
    builder.addLine("let lookupData={");
    JsonArray   indexJsonArray=getIndexJsonArray();
		if (!indexJsonArray.isJsonArray()) {
	 	  String  errorText="JSON array in getJSBuildJs is invalid";
	 	  HDLmAssert.HDLmAssertAction(false,errorText);
	  }
    int   indexJsonArraySize=indexJsonArray.size();
    boolean   logIsDebugEnabled=LOG.isDebugEnabled();
		if (logIsDebugEnabled) {
		  LOG.debug("In HDLmGetLookupIndex");
		  LOG.debug(sessionIndexStr);
	  }
    double  sessionIndexValue=0.0;
    if (sessionIndexStr!=null&&
!sessionIndexStr.equals("null"))
    	sessionIndexValue=Double.parseDouble(sessionIndexStr);
    for (int i=0;i<indexJsonArraySize;i++) {
    	JsonElement   indexJsonElement=indexJsonArray.get(i);
    	String  jsonHostName=HDLmJson.getJsonString(indexJsonElement,"website");
    	if (hostName.equals(jsonHostName)) {
    		JsonArray   rulesJsonArray=HDLmJson.getJsonArray(indexJsonElement,"rules");
    		JsonArray   choicesJsonArray=HDLmJson.getJsonArray(indexJsonElement,"choices");
    		if (!rulesJsonArray.isJsonArray()) {
    	 	  String  errorText="JSON array in getJSBuildJs is invalid";
    	 	  HDLmAssert.HDLmAssertAction(false,errorText);
    	  }
    		if (!choicesJsonArray.isJsonArray()) {
    	 	  String  errorText="JSON array in getJSBuildJs is invalid";
    	 	  HDLmAssert.HDLmAssertAction(false,errorText);
    	  }
    		int         rulesJsonArraySize=rulesJsonArray.size();
    		int         choicesJsonArraySize=choicesJsonArray.size();
    		JsonArray   choiceJsonArray=null;
        if (sessionIndexStr!=null&&
!sessionIndexStr.equals("null")) {
         	double      indexValue=sessionIndexValue*choicesJsonArraySize;
        	int         indexValueInt= (int) Math.floor(indexValue);
        	choiceJsonArray= (JsonArray) choicesJsonArray.get(indexValueInt);
      		if (!choiceJsonArray.isJsonArray()) {
      	 	  String  errorText="JSON array in getJSBuildJs is invalid";
      	 	  HDLmAssert.HDLmAssertAction(false,errorText);
      	  }
        }
    		counter=0;
        for (int j=0;j<rulesJsonArraySize;j++) {
        	counter++;
        	JsonElement   ruleJsonElement=rulesJsonArray.get(j);
      		if (!ruleJsonElement.isJsonPrimitive()) {
      			HDLmAssert.HDLmAssertAction(false,"JSON element is not a JSON primitive value");
      		}
        	String        ruleName=ruleJsonElement.getAsString();
          newLine="".repeat(23);
          newLine+="'";
          newLine+=ruleName;
          newLine+="':";
          if (sessionIndexStr==null||
          		sessionIndexStr.equals("null"))
          	newLine+="null";
          else {
            JsonElement   choiceJsonElement=choiceJsonArray.get(j);
        		if (!choiceJsonElement.isJsonPrimitive()) {
        			HDLmAssert.HDLmAssertAction(false,"JSON element is not a JSON primitive value");
        		}
            String  choiceJsonString=choiceJsonElement.getAsString();
            newLine+=choiceJsonString;
          }
          if (counter<rulesJsonArraySize)
            newLine+=",";
          builder.addLine(newLine);
        }
    	}
    }
    builder.addLine("};");
    builder.addLine("let lookupIndex=lookupData[ruleName];");
    builder.addLine("return lookupIndex;");
    builder.addLine("}");
    builder.addLine("function HDLmGetParametersArray() {");
    builder.addLine("let outputStr='';");
    builder.addLine("const parametersArray=[");
    counter=0;
    int   sessionParametersArrayLength=sessionParametersArray.size();
    for (int i=0;i<sessionParametersArrayLength;i++) {
      counter++;
      newLine="".repeat(30);
      arrayEntry=sessionParametersArray.get(i);
      if (arrayEntry==null)
        newLine+="null";
      else
        newLine+=arrayEntry;
      if (counter<sessionParametersArrayLength)
        newLine+=",";
      builder.addLine(newLine);
    }
    builder.addLine("];");
    builder.addLine("return parametersArray;");
    builder.addLine("}");
    builder.addLine("function HDLmGetPHash(urlStr) {");
    builder.addLine("let xHttpReq=new XMLHttpRequest();");
    String   protocolStringGetPHash;
    protocolStringGetPHash=protocol.toString().toLowerCase();
    builder.addLine("let serverNameValue='"+serverName+"';");
    builder.addLine("let urlVal='"+protocolStringGetPHash+"://'+serverNameValue+'/"+HDLmConfigInfo.getPHashName() +"';");
    builder.addLine("xHttpReq.open('POST',urlVal);");
    builder.addLine("urlStr=encodeURIComponent(urlStr);");
    builder.addLine("xHttpReq.send(urlStr);");
    builder.addLine("}");
    builder.addLine("{");
    builder.addLine("let hostNameStr=location.hostname;");
    builder.addLine("let linkStr=location.href;");
    builder.addLine("let pathNameStr=document.location.pathname;");
    builder.addLine("let sessionIdValue='"+sessionIdJava+"';");
    builder.addLine("let eventJson=HDLmGetJsonForLink(linkStr,hostNameStr,pathNameStr,sessionIdValue)");
    builder.addLine("Object.keys(window).forEach(key=>{");
    builder.addLine("if (key.startsWith('onmouse'))");
    builder.addLine("return;");
    builder.addLine("if (key.startsWith('onpointer'))");
    builder.addLine("return;");
    builder.addLine("if (/^on/.test(key)) {");
    builder.addLine("window.addEventListener(key.slice(2),event=>{");
    builder.addLine("let eventName=HDLmGetObjectName(event);");
    builder.addLine("let eventJson=HDLmGetJsonForEventObject(event,eventName,hostNameStr,pathNameStr,sessionIdValue)");
    builder.addLine("});");
    builder.addLine("}");
    builder.addLine("});");
    builder.addLine("};");
    builder.addLine("let HDLmSavedUpdates=new Object();");
    builder.addLine("let HDLmSavedExtracts=new Object();");
    builder.addLine("let HDLmSavedNotifies=new Object();");
    builder.addLine("var HDLmCheckVariable=true;");
    counter=0;
    builder.addLine("const HDLmPHashObject={");
    Map<String,String>mapObj=HDLmPHashCache.getMap();
    long  mapSize=mapObj.size();
		for (Map.Entry<String,String>entry:mapObj.entrySet()) {
	    String  key=entry.getKey();
	    String  value=entry.getValue();
      counter++;
      newLine="".repeat(28);
        newLine+="\"";
        newLine+=key;
        newLine+="\":\"";
        newLine+=value;
        newLine+="\"";
        if (counter<mapSize)
          newLine+=",";
      builder.addLine(newLine);
    }
    builder.addLine("};");
    String   protocolStringLower;
    protocolStringLower=protocol.toString().toLowerCase();
    builder.addLine("function HDLmSendData(dataStr) {");
    builder.addLine("dataStr='"+HDLmDefines.getString("HDLMPOSTDATA") +"="+"'+dataStr;");
    builder.addLine("let httpReq=new XMLHttpRequest();");
    builder.addLine("let serverNameValue='"+serverName+"';");
    builder.addLine("let urlStr='"+protocolStringLower+"://'+serverNameValue+'/"+HDLmDefines.getString("HDLMPOSTDATA") +"';");
    builder.addLine("httpReq.open('POST',urlStr);");
    builder.addLine("httpReq.setRequestHeader('Content-type','application/x-www-form-urlencoded');");
    builder.addLine("dataStr=encodeURIComponent(dataStr);");
    builder.addLine("httpReq.send(dataStr);");
    builder.addLine("}");
    builder.addLine("function HDLmSendUpdates(savedUpdates,reasonStr,weightStr,errorStr) {");
    builder.addLine("savedUpdates.reason=reasonStr;");
    builder.addLine("savedUpdates.weight=weightStr;");
    builder.addLine("savedUpdates.error=errorStr;");
    builder.addLine("let updateStr=JSON.stringify(savedUpdates);");
    builder.addLine("HDLmSendData(updateStr);");
    builder.addLine("}");
    if (sessionIndexStr==null||
        sessionIndexStr.equals("null"))
      builder.addLine("let HDLmIndexValue=null;");
    else
    	builder.addLine("let HDLmIndexValue="+sessionIndexStr+";");
    builder.addLine("HDLmClassAddCss('.HDLmClassPrimary',"+
                                      "'background-color:yellow');");
    builder.addLine("HDLmClassAddCss('.HDLmClassBackground',"+
                                      "'filter:grayscale(100%)');");
    builder.addLine("let HDLmObsTargetNode=document;");
    builder.addLine("let HDLmObsConfig={attributes:true,childList:true,subtree:true};");
    builder.addLine("let HDLmObsCallback=function (mutationsList,HDLmObsObserver) {");
    builder.addLine("let forceReadyState=false;");
    builder.addLine("if (document.location.hostname=='www.themarvelouslandofoz.com'&&");
    builder.addLine("document.readyState=='interactive')");
    builder.addLine("forceReadyState=true;");
    builder.addLine("HDLmApplyMods(document.readyState,HDLmIndexValue);");
    builder.addLine("if (document.readyState=='complete'||");
    builder.addLine("forceReadyState==true) {");
    builder.addLine("HDLmApplyMods(document.readyState,HDLmIndexValue);");
    builder.addLine("};");
    builder.addLine("};");
    builder.addLine("let HDLmObsObserver=new MutationObserver(HDLmObsCallback);");
    builder.addLine("HDLmObsObserver.observe(HDLmObsTargetNode,HDLmObsConfig);");
    builder.addLine("let pathValueStr=document.location.pathname;");
    builder.addLine("let curMod={};");
    builder.addLine("curMod.enabled=true;");
    String  modificationName=HDLmDefines.getString("HDLMLOADPAGEMODNAME");
    builder.addLine("curMod.name='"+modificationName+"';");
    builder.addLine("curMod.parameter=-1;");
    builder.addLine("curMod.path='//.*/';");
    builder.addLine("curMod.pathre=true;");
    String  modificationType=HDLmModTypes.VISIT.toString().toLowerCase();
    builder.addLine("curMod.type='"+modificationType+"';");
    builder.addLine("curMod.values=['Yes'];");
    builder.addLine("curMod.valuesCount=1;");
    builder.addLine("const sessionIdJS='"+sessionIdJava+"';");
    builder.addLine("const parametersArray=HDLmGetParametersArray()");
    builder.addLine("const readyState='unknown';");
    builder.addLine("HDLmApplyMod(pathValueStr,");
    builder.addLine("curMod,");
    builder.addLine("sessionIdJS,");
    builder.addLine("HDLmIndexValue,");
    builder.addLine("parametersArray,");
    builder.addLine("'"+hostName+"',");
    builder.addLine("'"+hostName+"',");
    builder.addLine("'"+divisionName+"',");
    builder.addLine("'"+siteName+"',");
    if (secureHostName!=null)
      builder.addLine("'"+secureHostName+"',");
    else
      builder.addLine("null,");
    builder.addLine("'"+forceSelectString+"',");
    builder.addLine("'"+logRuleMatchingString+"',");
    builder.addLine("readyState);");
    builder.addLine("</script>");
    actualJS=builder.getLinesWithSuffix("\r\n");
		if (useCreateFixedJS) {
		  HDLmUtility.fileClearContents(fixedJSName);
		  int             i;
		  int             actualJSLen;
		  StringBuilder   actualJSAdjustedBuilder=new StringBuilder();
		  actualJSLen=actualJS.length();
		  String  curStr;
		  for (i=0;i<actualJSLen;i++) {
		  	char  curChar=actualJS.charAt(i);
		  	if (curChar=='\u1000')
		  	  curStr="\\u1000";
		  	else if (curChar=='\u1001')
	  	    curStr="\\u1001";
        else if (curChar=='\u1002')
	  	    curStr="\\u1002";
        else if (curChar=='\u1003')
	  	    curStr="\\u1003";
        else if (curChar=='\u1004')
	  	    curStr="\\u1004";
        else if (curChar=='\u1005')
	  	    curStr="\\u1005";
        else if (curChar=='\u1006')
	  	    curStr="\\u1006";
        else if (curChar=='\u1007')
	  	    curStr="\\u1007";
        else if (curChar=='\u1008')
	  	    curStr="\\u1008";
        else if (curChar=='\u1009')
	  	    curStr="\\u1009";
        else if (curChar=='\u100a')
	  	    curStr="\\u100a";
        else if (curChar=='\u100b')
	  	    curStr="\\u100b";
        else if (curChar=='\u100c')
	  	    curStr="\\u100c";
        else if (curChar=='\u100d')
	  	    curStr="\\u100d";
        else if (curChar=='\u100e')
	  	    curStr="\\u100e";
        else if (curChar=='\u100f')
	  	    curStr="\\u100f";
        else if (curChar=='\u1010')
	  	    curStr="\\u1010";
        else if (curChar=='\u1011')
	  	    curStr="\\u1011";
        else if (curChar=='\u1012')
	  	    curStr="\\u1012";
        else if (curChar=='\u1013')
	  	    curStr="\\u1013";
        else if (curChar=='\u1014')
	  	    curStr="\\u1014";
        else if (curChar=='\u1015')
	  	    curStr="\\u1015";
        else if (curChar=='\u1016')
	  	    curStr="\\u1016";
        else if (curChar=='\u1017')
	  	    curStr="\\u1017";
        else if (curChar=='\u1018')
	  	    curStr="\\u1018";
        else if (curChar=='\u1019')
	  	    curStr="\\u1019";
        else if (curChar=='\u101a')
	  	    curStr="\\u101a";
        else if (curChar=='\u101b')
	  	    curStr="\\u101b";
        else if (curChar=='\u101c')
	  	    curStr="\\u101c";
        else if (curChar=='\u101d')
	  	    curStr="\\u101d";
        else if (curChar=='\u101e')
	  	    curStr="\\u101e";
        else if (curChar=='\u101f')
	  	    curStr="\\u101f";
        else if (curChar=='\u1020')
	  	    curStr="\\u1020";
        else if (curChar=='\u1021')
	  	    curStr="\\u1021";
        else if (curChar=='\u1022')
	  	    curStr="\\u1022";
        else if (curChar=='\u1023')
	  	    curStr="\\u1023";
        else if (curChar=='\u1024')
	  	    curStr="\\u1024";
        else if (curChar=='\u1025')
	  	    curStr="\\u1025";
        else if (curChar=='\u1026')
	  	    curStr="\\u1026";
        else if (curChar=='\u1027')
	  	    curStr="\\u1027";
        else if (curChar=='\u1028')
	  	    curStr="\\u1028";
        else if (curChar=='\u1029')
	  	    curStr="\\u1029";
        else if (curChar=='\u102a')
	  	    curStr="\\u102a";
        else if (curChar=='\u102b')
	  	    curStr="\\u102b";
        else if (curChar=='\u102c')
	  	    curStr="\\u102c";
        else if (curChar=='\u102d')
	  	    curStr="\\u102d";
        else if (curChar=='\u102e')
	  	    curStr="\\u102e";
        else if (curChar=='\u102f')
	  	    curStr="\\u102f";
        else if (curChar=='\u1030')
	  	    curStr="\\u1030";
        else if (curChar=='\u1031')
	  	    curStr="\\u1031";
        else if (curChar=='\u1032')
	  	    curStr="\\u1032";
        else if (curChar=='\u1033')
	  	    curStr="\\u1033";
        else if (curChar=='\u1034')
	  	    curStr="\\u1034";
        else if (curChar=='\u1035')
	  	    curStr="\\u1035";
        else if (curChar=='\u1036')
	  	    curStr="\\u1036";
        else if (curChar=='\u1037')
	  	    curStr="\\u1037";
        else if (curChar=='\u1038')
	  	    curStr="\\u1038";
        else if (curChar=='\u1039')
	  	    curStr="\\u1039";
		  	else {
			  	curStr="";
			  	curStr+=curChar;
		  	}
		  	actualJSAdjustedBuilder.append(curStr);
		  }
		  String  actualJSAdjusted=actualJSAdjustedBuilder.toString();
		  HDLmUtility.filePutAppend(fixedJSName,
		  		                      actualJSAdjusted);
		}
    return actualJS;
  }
  public static JsonArray  getIndexJsonArray() {
    class getIndexJsonArrayLocal {
      final static String  jsonString=
        "["+
        "  {"+
        "    \"website\":"+
        "      \"www.yogadirect.com\","+
        "    \"generated\":"+
        "      \"2024-06-13T01:51:52Z\","+
        "    \"rules\":"+
        "["+
        "        \"Change Banner\","+
        "        \"Change Add To Cart\""+
        "],"+
        "    \"choices\":"+
        "["+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5],"+
        "[4,6],"+
        "[5,0],"+
        "[0,1],"+
        "[1,2],"+
        "[2,3],"+
        "[3,4],"+
        "[4,5],"+
        "[5,6],"+
        "[0,0],"+
        "[1,1],"+
        "[2,2],"+
        "[3,3],"+
        "[4,4],"+
        "[5,5],"+
        "[0,6],"+
        "[1,0],"+
        "[2,1],"+
        "[3,2],"+
        "[4,3],"+
        "[5,4],"+
        "[0,5],"+
        "[1,6],"+
        "[2,0],"+
        "[3,1],"+
        "[4,2],"+
        "[5,3],"+
        "[0,4],"+
        "[1,5],"+
        "[2,6],"+
        "[3,0],"+
        "[4,1],"+
        "[5,2],"+
        "[0,3],"+
        "[1,4],"+
        "[2,5],"+
        "[3,6],"+
        "[4,0],"+
        "[5,1],"+
        "[0,2],"+
        "[1,3],"+
        "[2,4],"+
        "[3,5]"+
        "]"+
        "  }"+
        "]"+
        "";
	    static JsonParser  parser=new JsonParser();
	    static JsonArray   rvJsonArray= (JsonArray) parser.parse(jsonString);
  	}
    return getIndexJsonArrayLocal.rvJsonArray;
  }
}
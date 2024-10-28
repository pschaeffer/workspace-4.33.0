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
class HDLmBuildJsCompressBlanksAndNames {
  private static final Logger LOG=LoggerFactory.getLogger(HDLmBuildJsCompressBlanksAndNames.class);
	private HDLmBuildJsCompressBlanksAndNames() {}
  public static String getJsBuildJs(HDLmProtocolTypes protocol,
                                    String B5,
                                    String hostName,
                                    String divisionName,
                                    String siteName,
                                    ArrayList<HDLmMod>mods,
                                    HDLmSession sessionObj,
                                    HDLmLogMatchingTypes m4,
                                    String serverName) {
    if (protocol==null) {
		  String  b3="Protocol string passed to getJsBuildJs is null";
      throw new NullPointerException(b3);
		}
		if (hostName==null) {
		  String  b3="Host name string passed to getJsBuildJs is null";
      throw new NullPointerException(b3);
		}
		if (divisionName==null) {
		  String  b3="Division name string passed to getJsBuildJs is null";
      throw new NullPointerException(b3);
		}
		if (siteName==null) {
		  String  b3="Site name string passed to getJsBuildJs is null";
      throw new NullPointerException(b3);
		}
		if (mods==null) {
		  String  b3="Modifications array passed to getJsBuildJs is null";
      throw new NullPointerException(b3);
		}
    if (sessionObj==null) {
		  String  b3="Session object passed to getJsBuildJs is null";
      throw new NullPointerException(b3);
		}
    if (m4==null) {
		  String  b3="Log rule matching reference passed to getJsBuildJs is null";
      throw new NullPointerException(b3);
		}
    if (serverName==null) {
		  String  b3="Server name string passed to getJsBuildJs is null";
      throw new NullPointerException(b3);
		}
    HDLmBuildLines  builder;
    String          actualJS;
    String          fixedJSName=null;
    boolean         useCreateFixedJS=false;
    String        B7=sessionObj.getSessionId();
    String        C0=sessionObj.getIndex();
    String        sessionParametersStr=sessionObj.getParameters();
    ArrayList<Double>sessionParametersArray=HDLmMain.getParametersArray(sessionParametersStr);
    if (1==2) {
      actualJS=HDLmUtility.fileGetContents("HDLmBuildJsCompressBlanksAndNamesOld.txt");
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
    builder.addLine("let h9={");
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
    builder.addLine("j9();");
    builder.addLine("if (event.key=='q'&&event.ctrlKey==true)");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionPostRuleTracingEnabled\",'true');");
    builder.addLine("});");
    builder.addLine("function d7(y6,");
    builder.addLine("D,");
    builder.addLine("B8,");
    builder.addLine("sessionIndexValue,");
    builder.addLine("x6,");
    builder.addLine("z3,");
    builder.addLine("k2,");
    builder.addLine("Y,");
    builder.addLine("C2,");
    builder.addLine("z4,");
    builder.addLine("d4,");
    builder.addLine("m4,");
    builder.addLine("z6) {");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionRuleInfoHostName\",k2);");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionRuleInfoDivisionName\",Y);");
    builder.addLine("sessionStorage.setItem(\"HDLmSessionRuleInfoSiteName\",C2);");
    builder.addLine("if (sessionStorage.getItem('HDLmSessionDebugRulesEnabled') =='true')");
    builder.addLine("m4=true;");
    builder.addLine("let u0=h9.off;");
    builder.addLine("if (sessionStorage.getItem('HDLmSessionDebugNodeIdenEnabled') =='all')");
    builder.addLine("u0=h9.all;");
    builder.addLine("let y8=false;");
    builder.addLine("if (sessionStorage.getItem('HDLmSessionPostRuleTracingEnabled') =='true')");
    builder.addLine("y8=true;");
    builder.addLine("let n0=false;");
    builder.addLine("let m9='';");
    builder.addLine("let n1=k2+'/'+Y+'/'+C2+'/'+D.name");
    builder.addLine("n1=i8(n1);");
    builder.addLine("let P=D.type;");
    builder.addLine("while (true) {");
    builder.addLine("let y9=new Object();");
    builder.addLine("if (D.enabled!=true) {");
    builder.addLine("m9='disabled';");
    builder.addLine("if (y8==true) {");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,null,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,null,null);");
    builder.addLine("y9.matcherror=m9;");
    builder.addLine("j6(m3,'failure','1.0',y9);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("let n3;");
    builder.addLine("let n2;");
    builder.addLine("if (D.pathre===true) {");
    builder.addLine("let H=D.path.length;");
    builder.addLine("n2=new RegExp(D.path.substr(2,H-3));");
    builder.addLine("n3=n2.test(y6);");
    builder.addLine("if (y8==true) {");
    builder.addLine("y9.matchpathre=D.pathre;");
    builder.addLine("y9.matchpath=D.path;");
    builder.addLine("y9.matchpathvalue=y6;");
    builder.addLine("y9.matchmatch=n3;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("n3= (D.path===y6);");
    builder.addLine("if (y8==true) {");
    builder.addLine("y9.matchpathre=D.pathre;");
    builder.addLine("y9.matchpath=D.path;");
    builder.addLine("y9.matchpathvalue=y6;");
    builder.addLine("y9.matchmatch=n3;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (n3==false) {");
    builder.addLine("m9='Path value mismatch';");
    builder.addLine("if (y8==true) {");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,null,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,null,null);");
    builder.addLine("y9.matcherror=m9;");
    builder.addLine("j6(m3,'failure','1.0',y9);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("if (m4==true) {");
    builder.addLine("let b3=e1(D,'match',y6);");
    builder.addLine("e0('Trace','Mod',35,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("const c9={");
    builder.addLine("'fontcolor':'color',");
    builder.addLine("'fontfamily':'font-family',");
    builder.addLine("'fontkerning':'font-kerning',");
    builder.addLine("'fontsize':'font-size',");
    builder.addLine("'fontstyle':'font-style',");
    builder.addLine("'fontweight':'font-weight'");
    builder.addLine("}");
    builder.addLine("let x4=-1;");
    builder.addLine("let finalLookupIndex=0;");
    builder.addLine("let m8=-1.0;");
    builder.addLine("let sessionIndexValueUsed=false;");
    builder.addLine("let D2=g6(D.name);");
    builder.addLine("if (typeof(D2) !='undefined'&&");
    builder.addLine("D2!=null) {");
    builder.addLine("m8=sessionIndexValue;");
    builder.addLine("sessionIndexValueUsed=true;");
    builder.addLine("finalLookupIndex=D2;");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("x4=D.parameter;");
    builder.addLine("if (x4!=null&&");
    builder.addLine("x4>=0&&");
    builder.addLine("x4<x6.length)");
    builder.addLine("m8=x6[x4];");
    builder.addLine("}");
    builder.addLine("let u4=f5(D,u0,y8,y9);");
    builder.addLine("let u6=u4.length;");
    builder.addLine("if (u6==0&&P!='visit') {");
    builder.addLine("m9='nonodes';");
    builder.addLine("if (y8==true) {");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,null,null);");
    builder.addLine("y9.matcherror=m9;");
    builder.addLine("j6(m3,'failure','1.0',y9);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("switch (P) {");
    builder.addLine("case'attribute':{");
    builder.addLine("let E=D.extra;");
    builder.addLine("let F=E.split('/');");
    builder.addLine("let g=F[0];");
    builder.addLine("let h=F[1]");
    builder.addLine("for (let i=0;i<u6;i++) {");
    builder.addLine("let K=u4[i];");
    builder.addLine("f2(K,P,E);");
    builder.addLine("n0=true;");
    builder.addLine("if (h6(K,n1,z6) >0)");
    builder.addLine("break;");
    builder.addLine("h6(K,n1);");
    builder.addLine("if (h.toUpperCase() =='USEPROXYHOST') {");
    builder.addLine("let l=K.getAttribute(g);");
    builder.addLine("let w7=l;");
    builder.addLine("let v6=new URL(l);");
    builder.addLine("v6.host=z3;");
    builder.addLine("let q2=v6.href;");
    builder.addLine("K.setAttribute(g,v6.href);");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("w7,q2);");
    builder.addLine("y9.matcherror='attribute';");
    builder.addLine("j6(m3,'href','1.0',y9);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'extract':{");
    builder.addLine("for (let i=0;i<u6;i++) {");
    builder.addLine("let K=u4[i];");
    builder.addLine("let E=D.extra;");
    builder.addLine("f2(K,P,E);");
    builder.addLine("n0=true;");
    builder.addLine("if (h1(K,n1,z6) >0)");
    builder.addLine("break;");
    builder.addLine("h6(K,n1);");
    builder.addLine("let w7;");
    builder.addLine("if (j2.hasOwnProperty(D.name))");
    builder.addLine("w7=j2[D.name];");
    builder.addLine("else");
    builder.addLine("w7=null;");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("w7,null);");
    builder.addLine("let m2='extract';");
    builder.addLine("if ((typeof E) !='undefined'&&");
    builder.addLine("E!=null&&");
    builder.addLine("E!='')");
    builder.addLine("m2=E;");
    builder.addLine("y9.matcherror='extract';");
    builder.addLine("j6(m3,m2,'1.0',y9);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'modify':{");
    builder.addLine("if (z4==null) {");
    builder.addLine("let b3=`No secure host name for (${k2})`;");
    builder.addLine("e0('Error','Mod',16,b3);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<u6;i++) {");
    builder.addLine("let K=u4[i];");
    builder.addLine("let E=D.extra;");
    builder.addLine("f2(K,P,E);");
    builder.addLine("if (E.toUpperCase() !=='FIXIFRAMESRC')");
    builder.addLine("break;");
    builder.addLine("n0=true;");
    builder.addLine("if (h1(K,n1,z6) >0)");
    builder.addLine("break;");
    builder.addLine("h6(K,n1);");
    builder.addLine("let v1=K.getAttribute('src');");
    builder.addLine("let w7=v1;");
    builder.addLine("let v6=new URL(v1);");
    builder.addLine("v6.host=z4;");
    builder.addLine("let q2=v6.href+'&HDLmSessionId='+B8;");
    builder.addLine("K.setAttribute('src',q2);");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("null,null);");
    builder.addLine("let m2='modify';");
    builder.addLine("if ((typeof E) !='undefined'&&");
    builder.addLine("E!=null&&");
    builder.addLine("E!='')");
    builder.addLine("m2=E;");
    builder.addLine("y9.matcherror='modify';");
    builder.addLine("j6(m3,m2,'1.0',y9);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'notify':{");
    builder.addLine("let B6=false;");
    builder.addLine("for (let i=0;i<u6;i++) {");
    builder.addLine("let v0=u4[i];");
    builder.addLine("if (h1(v0,n1,z6) ==0) {");
    builder.addLine("B6=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("let E=D.extra;");
    builder.addLine("if (B6) {");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("null,null);");
    builder.addLine("let m2='notify';");
    builder.addLine("if ((typeof E) !='undefined'&&");
    builder.addLine("E!=null&&");
    builder.addLine("E!='')");
    builder.addLine("m2=E;");
    builder.addLine("y9.matcherror='notify';");
    builder.addLine("j6(m3,m2,'1.0',y9);");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<u6;i++) {");
    builder.addLine("let K=u4[i];");
    builder.addLine("f2(K,P,E);");
    builder.addLine("n0=true;");
    builder.addLine("if (h1(K,n1,z6) >0)");
    builder.addLine("break;");
    builder.addLine("h6(K,n1);");
    builder.addLine("K.addEventListener('click', (function() {");
    builder.addLine("return function() {");
    builder.addLine("let m3=new Object();");
    builder.addLine("if (D.valuesCount<=0) {");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("null,null);");
    builder.addLine("}");
    builder.addLine("for (let j=0;j<D.valuesCount;j++) {");
    builder.addLine("let A9=D.values[j];");
    builder.addLine("A9=h8(A9);");
    builder.addLine("let B0;");
    builder.addLine("if (j3.hasOwnProperty(A9))");
    builder.addLine("B0=j3[A9];");
    builder.addLine("else");
    builder.addLine("B0=null;");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("B0,null);");
    builder.addLine("}");
    builder.addLine("let m2='notify';");
    builder.addLine("let E=D.extra;");
    builder.addLine("if ((typeof E) !='undefined'&&");
    builder.addLine("E!=null&&");
    builder.addLine("E!='')");
    builder.addLine("m2=E;");
    builder.addLine("y9.matcherror='click';");
    builder.addLine("j6(m3,m2,'1.0',y9);");
    builder.addLine("}");
    builder.addLine("})());");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'visit':{");
    builder.addLine("let D3=false;");
    builder.addLine("h5(D.extra,y9,D3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D,");
    builder.addLine("y6);");
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
    builder.addLine("let q4=D.values;");
    builder.addLine("let o5=D.valuesCount;");
    builder.addLine("if (m8!=null&&sessionIndexValueUsed==false) {");
    builder.addLine("finalLookupIndex=Math.floor(o5*m8);");
    builder.addLine("finalLookupIndex=Math.min(finalLookupIndex,o5-1);");
    builder.addLine("}");
    builder.addLine("let d2=false;");
    builder.addLine("let q2;");
    builder.addLine("if (m8!=null) {");
    builder.addLine("if (finalLookupIndex>=0)");
    builder.addLine("q2=q4[finalLookupIndex];");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<o5;i++) {");
    builder.addLine("if (q4[i].startsWith(d4)) {");
    builder.addLine("q2=q4[i].substring(d4.length);");
    builder.addLine("finalLookupIndex=i;");
    builder.addLine("d2=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (m8==null&&");
    builder.addLine("d2==false) {");
    builder.addLine("m9='Null lookup value';");
    builder.addLine("if (y8==true) {");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("null,null);");
    builder.addLine("y9.matcherror=m9;");
    builder.addLine("j6(m3,'failure','1.0',y9);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<u6;i++) {");
    builder.addLine("let K=u4[i];");
    builder.addLine("let E=D.extra;");
    builder.addLine("f2(K,P,E);");
    builder.addLine("let w7;");
    builder.addLine("if (P=='changeattrs') {");
    builder.addLine("w7=g2(K);");
    builder.addLine("}");
    builder.addLine("else if (P=='changenodes') {");
    builder.addLine("w7=K.outerHTML;");
    builder.addLine("}");
    builder.addLine("else if (P=='fontcolor'||");
    builder.addLine("P=='fontfamily'||");
    builder.addLine("P=='fontkerning'||");
    builder.addLine("P=='fontsize'||");
    builder.addLine("P=='fontstyle'||");
    builder.addLine("P=='fontweight') {");
    builder.addLine("let p1=c9[P];");
    builder.addLine("w7='';");
    builder.addLine("if (K.style.hasOwnProperty(p1))");
    builder.addLine("w7=K.style.getPropertyValue(p1);");
    builder.addLine("else if (K.hasAttribute(p1))");
    builder.addLine("w7=K.getAttribute(p1);");
    builder.addLine("}");
    builder.addLine("else if (P=='height'||");
    builder.addLine("P=='width') {");
    builder.addLine("w7='';");
    builder.addLine("if (K.hasAttribute(P))");
    builder.addLine("w7=K.getAttribute(P);");
    builder.addLine("}");
    builder.addLine("else if (P=='image') {");
    builder.addLine("w7='';");
    builder.addLine("if (K.hasAttribute('src')) {");
    builder.addLine("w7=K.getAttribute('src');");
    builder.addLine("if (w7.startsWith('http'))");
    builder.addLine("w7=i7(w7);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (P=='order') {");
    builder.addLine("w7='';");
    builder.addLine("}");
    builder.addLine("else if (P=='remove') {");
    builder.addLine("w7='';");
    builder.addLine("}");
    builder.addLine("else if (P=='replace') {");
    builder.addLine("w7='';");
    builder.addLine("}");
    builder.addLine("else if (P=='script') {");
    builder.addLine("w7='';");
    builder.addLine("}");
    builder.addLine("else if (P=='style') {");
    builder.addLine("w7='';");
    builder.addLine("let I=j8(E);");
    builder.addLine("let J=I.length;");
    builder.addLine("for (let i=0;i<J;i++) {");
    builder.addLine("let O=I[i];");
    builder.addLine("let Q='';");
    builder.addLine("if (K.hasAttribute('style')) {");
    builder.addLine("if (K.style.hasOwnProperty(O))");
    builder.addLine("Q=K.style.getPropertyValue(O);");
    builder.addLine("}");
    builder.addLine("if (Q==''&&");
    builder.addLine("K.hasAttribute(O)) {");
    builder.addLine("Q=K.getAttribute(O);");
    builder.addLine("}");
    builder.addLine("if (Q!='') {");
    builder.addLine("if (w7!='')");
    builder.addLine("w7+=' '");
    builder.addLine("w7+=Q;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (P=='text'||");
    builder.addLine("P=='textchecked'||");
    builder.addLine("P=='title') {");
    builder.addLine("w7=K.textContent;");
    builder.addLine("}");
    builder.addLine("if (P=='textchecked') {");
    builder.addLine("let D4=e7(w7,E,");
    builder.addLine("m9,y9,y8,");
    builder.addLine("x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D,");
    builder.addLine("y6);");
    builder.addLine("if (!D4) {");
    builder.addLine("m9='textunequal';");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("n0=true;");
    builder.addLine("let n4=h1(K,n1,z6);");
    builder.addLine("if (P=='changenodes') {");
    builder.addLine("if (n4>255)");
    builder.addLine("break;");
    builder.addLine("let D3=true;");
    builder.addLine("let d0=e6(K,q2,n4,D3,sessionIndexValueUsed,");
    builder.addLine("m9,y9,y8,");
    builder.addLine("x6,B8,");
    builder.addLine("sessionIndexValue,x4,m8,");
    builder.addLine("k2,Y,C2,");
    builder.addLine("D,y6,w7);");
    builder.addLine("if (d0==true)");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("else if (P=='image') {");
    builder.addLine("if (n4>2)");
    builder.addLine("if (w7==q2||");
    builder.addLine("w7.startsWith('data:'))");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("else if (P=='remove'||");
    builder.addLine("P=='replace') {");
    builder.addLine("let x9=K.parentNode;");
    builder.addLine("if (x9!=null) {");
    builder.addLine("if (h1(x9,n1,z6) >0)");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (P=='textchecked') {");
    builder.addLine("if (n4>1)");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("if (n4>0)");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (P=='remove'||");
    builder.addLine("P=='replace') {");
    builder.addLine("let x9=K.parentNode;");
    builder.addLine("h6(x9,n1);");
    builder.addLine("}");
    builder.addLine("else if (P=='script') {");
    builder.addLine("if (z6=='complete')");
    builder.addLine("h6(K,n1);");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("h6(K,n1);");
    builder.addLine("}");
    builder.addLine("if (P=='changeattrs') {");
    builder.addLine("if (q2.trim() !='')");
    builder.addLine("e5(K,q2);");
    builder.addLine("}");
    builder.addLine("else if (P=='changenodes') {");
    builder.addLine("let D3=false;");
    builder.addLine("e6(K,q2,n4,D3,sessionIndexValueUsed,");
    builder.addLine("m9,y9,y8,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,");
    builder.addLine("D,y6,w7);");
    builder.addLine("}");
    builder.addLine("else if (P=='fontcolor'||");
    builder.addLine("P=='fontfamily'||");
    builder.addLine("P=='fontkerning'||");
    builder.addLine("P=='fontsize'||");
    builder.addLine("P=='fontstyle'||");
    builder.addLine("P=='fontweight') {");
    builder.addLine("if (P=='fontsize')");
    builder.addLine("q2=e4(q2,'px');");
    builder.addLine("let p1=c9[P];");
    builder.addLine("K.style.setProperty(p1,q2);");
    builder.addLine("}");
    builder.addLine("else if (P=='height'||");
    builder.addLine("P=='width') {");
    builder.addLine("q2=e4(q2,'px');");
    builder.addLine("K.setAttribute(P,q2);");
    builder.addLine("}");
    builder.addLine("else if (P=='image') {");
    builder.addLine("if (q2.startsWith('//'))");
    builder.addLine("K.setAttribute('src','https:'+q2);");
    builder.addLine("if (q2.startsWith('data:'))");
    builder.addLine("K.setAttribute('src',q2);");
    builder.addLine("if (1==1) {");
    builder.addLine("K.style.setProperty('background-repeat','no-repeat');");
    builder.addLine("K.style.setProperty('background-size','cover');");
    builder.addLine("K.style.setProperty('text-align','center');");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (P=='order') {");
    builder.addLine("let s7=K.children.length;");
    builder.addLine("let q1=e3(q2,s7);");
    builder.addLine("for (let j=0;j<q1.length;j++) {");
    builder.addLine("K.appendChild(K.children[q1[j]]);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (P=='remove') {");
    builder.addLine("if (f3(q2,'yes'))");
    builder.addLine("K.remove();");
    builder.addLine("}");
    builder.addLine("else if (P=='replace') {");
    builder.addLine("if (q2!='') {");
    builder.addLine("let x9=K.parentNode;");
    builder.addLine("let p6=JSON.parse(q2);");
    builder.addLine("let p2=e2(p6);");
    builder.addLine("x9.replaceChild(p2,K);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (P=='script'&&z6=='complete') {");
    builder.addLine("let functionStr='HDLmExecute'+i8(D.name) +finalLookupIndex;");
    builder.addLine("window[functionStr]();");
    builder.addLine("}");
    builder.addLine("else if (P=='style') {");
    builder.addLine("if (E=='background-image') {");
    builder.addLine("let o7=q2;");
    builder.addLine("if (o7.startsWith('url')) {");
    builder.addLine("}");
    builder.addLine("else if (o7.startsWith('data:')) {");
    builder.addLine("o7='url('+o7+')';");
    builder.addLine("}");
    builder.addLine("else if (o7.startsWith('http')) {");
    builder.addLine("o7='url('+o7+')';");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("if (o7.startsWith('//'))");
    builder.addLine("o7='url(https:'+o7+')';");
    builder.addLine("}");
    builder.addLine("if (1==1) {");
    builder.addLine("K.style.setProperty(E,o7);");
    builder.addLine("K.style.setProperty('background-repeat','no-repeat');");
    builder.addLine("K.style.setProperty('background-size','cover');");
    builder.addLine("}");
    builder.addLine("if (1==2) {");
    builder.addLine("let b9=g3(K,'junk.jpg');");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("let I=j8(E);");
    builder.addLine("let q5=j7(q2);");
    builder.addLine("for (let i in I) {");
    builder.addLine("let q6=q5[i];");
    builder.addLine("if (q6=='none')");
    builder.addLine("continue;");
    builder.addLine("K.style.setProperty(I[i],q6);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (P=='title'||");
    builder.addLine("P=='text'||");
    builder.addLine("P=='textchecked') {");
    builder.addLine("K.textContent=q2;");
    builder.addLine("}");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("w7,q2);");
    builder.addLine("y9.matcherror='fired';");
    builder.addLine("j6(m3,P,'1.0',y9);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("default:{");
    builder.addLine("let b3=\"Invalid modification type value-\"+P;");
    builder.addLine("e0('Error','Mod',31,b3);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (n0===false) {");
    builder.addLine("if (m9==='') {");
    builder.addLine("m9='nomatch';");
    builder.addLine("if (y8==true) {");
    builder.addLine("let m3=new Object();");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("null,null,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("null,null);");
    builder.addLine("y9.matcherror=m9;");
    builder.addLine("j6(m3,'failure','1.0',y9);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (m9!=''&&");
    builder.addLine("m4==true) {");
    builder.addLine("let b3=e1(D,m9,y6);");
    builder.addLine("e0('Trace','Mod',2,b3);");
    builder.addLine("}");
    builder.addLine("return n0;");
    builder.addLine("}");
    builder.addLine("function d9(y,l0) {");
    builder.addLine("let rv=\"\";");
    builder.addLine("let d=typeof(y);");
    builder.addLine("if (d=='undefined') {");
    builder.addLine("rv='undefined';");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("if (y==null) {");
    builder.addLine("rv=null;");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("let c=y.length;");
    builder.addLine("if (c<=0)");
    builder.addLine("return rv;");
    builder.addLine("for (let i=0;i<c;i++) {");
    builder.addLine("if (i>0)");
    builder.addLine("rv+=l0;");
    builder.addLine("let Q=y[i];");
    builder.addLine("if (Q==null)");
    builder.addLine("rv+='null';");
    builder.addLine("else");
    builder.addLine("rv+=String(Q);");
    builder.addLine("}");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("function e1(D,m9,y6) {");
    builder.addLine("let b3=\"Modification \"+m9+\"-\";");
    builder.addLine("b3+=\"name (\";");
    builder.addLine("b3+=D.name;");
    builder.addLine("b3+=\")\";");
    builder.addLine("if (Array.isArray(D.find) &&");
    builder.addLine("D.find.length>0) {");
    builder.addLine("b3+=\" key (\";");
    builder.addLine("let c1=D.find[0];");
    builder.addLine("b3+=c1.attributeName;");
    builder.addLine("b3+=\")\";");
    builder.addLine("b3+=\" value (\";");
    builder.addLine("b3+=c1.attributeValue;");
    builder.addLine("b3+=\")\";");
    builder.addLine("}");
    builder.addLine("b3+='-'+y6;");
    builder.addLine("return b3;");
    builder.addLine("}");
    builder.addLine("function e2(a1) {");
    builder.addLine("if (a1.type!='Element')");
    builder.addLine("return null;");
    builder.addLine("if (a1.tag==null)");
    builder.addLine("return null;");
    builder.addLine("let a0=document.createElement(a1.tag);");
    builder.addLine("let e=a1.attributes;");
    builder.addLine("if (e!=null) {");
    builder.addLine("for (let f in e) {");
    builder.addLine("a0.setAttribute(f,e[f]);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("let a6=a1.text;");
    builder.addLine("if (a6!=null) {");
    builder.addLine("let D5=document.createTextNode(a6);");
    builder.addLine("a0.appendChild(D5);");
    builder.addLine("}");
    builder.addLine("let a3=a1.subnodes;");
    builder.addLine("if (a3!=null) {");
    builder.addLine("let a4=a3.length;");
    builder.addLine("for (let i=0;i<a4;i++) {");
    builder.addLine("let a2=a3[i];");
    builder.addLine("let a5=e2(a2);");
    builder.addLine("if (a5!=null)");
    builder.addLine("a0.appendChild(a5);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return a0;");
    builder.addLine("}");
    builder.addLine("function e3(q2,D9) {");
    builder.addLine("q2=q2.replace(/,/g,' ');");
    builder.addLine("let q3=q2.split(' ');");
    builder.addLine("let o8=[];");
    builder.addLine("let x1=[];");
    builder.addLine("let D1= (function(a,b) {while(a--) b[a]=a;return b})(D9,[]);");
    builder.addLine("for (let i=0;i<q3.length;i++) {");
    builder.addLine("if (q3[i]=='')");
    builder.addLine("continue;");
    builder.addLine("let D0=parseInt(q3[i]);");
    builder.addLine("if (typeof(D0) !='v7')");
    builder.addLine("continue;");
    builder.addLine("o8.push(D0);");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<D9;i++)");
    builder.addLine("if (o8.includes(i) ==false)");
    builder.addLine("o8.push(i);");
    builder.addLine("for (let i=0;i<D9;i++) {");
    builder.addLine("let k9=D1.indexOf(o8[i]);");
    builder.addLine("x1.push(k9);");
    builder.addLine("D1.splice(k9,1);");
    builder.addLine("D1.push(k9)");
    builder.addLine("}");
    builder.addLine("return x1;");
    builder.addLine("}");
    builder.addLine("function e4(q6,C9) {");
    builder.addLine("if ((typeof(q6) =='v7') &&");
    builder.addLine("q6!='')");
    builder.addLine("q6+=C9;");
    builder.addLine("return q6");
    builder.addLine("}");
    builder.addLine("function e6(K,l3,n4,D3,sessionIndexValueUsed,");
    builder.addLine("m9,y9,y8,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,");
    builder.addLine("D,y6,w7) {");
    builder.addLine("let t=JSON.parse(l3);");
    builder.addLine("let d0=false;");
    builder.addLine("for (const l5 in t) {");
    builder.addLine("if (!t.hasOwnProperty(l5))");
    builder.addLine("continue;");
    builder.addLine("let v=t[l5];");
    builder.addLine("switch (l5) {");
    builder.addLine("case'text':");
    builder.addLine("case'title':{");
    builder.addLine("if (n4>0) {");
    builder.addLine("d0=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (D3==false)");
    builder.addLine("K.textContent=v;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'textchecked':{");
    builder.addLine("let a=K.textContent;");
    builder.addLine("let z9=v[0];");
    builder.addLine("let s=e7(a,z9,");
    builder.addLine("m9,y9,y8,");
    builder.addLine("x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D,");
    builder.addLine("y6);");
    builder.addLine("if (n4>1) {");
    builder.addLine("d0=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (s&&D3==false)");
    builder.addLine("K.textContent=v[1];");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'visit':{");
    builder.addLine("let x=h5(v,y9,D3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D,");
    builder.addLine("y6);");
    builder.addLine("if (x==true)");
    builder.addLine("d0=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("default:{");
    builder.addLine("if (n4>0) {");
    builder.addLine("d0=true;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (v==null) {");
    builder.addLine("if (D3==false)");
    builder.addLine("K.style.removeProperty(l5);");
    builder.addLine("}");
    builder.addLine("else  {");
    builder.addLine("let u=typeof v;");
    builder.addLine("if (u=='v7') {");
    builder.addLine("v=v.toString();");
    builder.addLine("v+='px';");
    builder.addLine("}");
    builder.addLine("if (D3==false)");
    builder.addLine("K.style.setProperty(l5,v);");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return d0;");
    builder.addLine("}");
    builder.addLine("function e7(a,z9,");
    builder.addLine("m9,y9,y8,");
    builder.addLine("x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D,");
    builder.addLine("y6) {");
    builder.addLine("let rv;");
    builder.addLine("let A0=z9.toLowerCase();");
    builder.addLine("let b=a.toLowerCase();");
    builder.addLine("if (b.indexOf(A0) ===-1) {");
    builder.addLine("if (y8==true) {");
    builder.addLine("let m3=new Object();");
    builder.addLine("m9='textunequal';");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,a,z9);");
    builder.addLine("y9.matcherror=m9;");
    builder.addLine("j6(m3,'failure','1.0',y9);");
    builder.addLine("}");
    builder.addLine("rv=false;;");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("rv=true;");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("function f2(K,P,b8) {");
    builder.addLine("if (P=='order')");
    builder.addLine("return;");
    builder.addLine("if (P=='style'&&b8=='background-image') {");
    builder.addLine("f1(K,'HDLmClassBackground');");
    builder.addLine("return;");
    builder.addLine("}");
    builder.addLine("f1(K,'HDLmClassPrimary');");
    builder.addLine("}");
    builder.addLine("function f0(y2,y3) {");
    builder.addLine("var C6='HDLmSessionClasses';");
    builder.addLine("var C8=document.createElement('style');");
    builder.addLine("C8.type='text/css';");
    builder.addLine("C8.title=C6;");
    builder.addLine("document.getElementsByTagName('head')[0].appendChild(C8);");
    builder.addLine("C8.sheet.insertRule(y2+\"{\"+y3+\"}\",0);");
    builder.addLine("var T=sessionStorage.getItem(C6+'Disabled');");
    builder.addLine("if (T==null)");
    builder.addLine("T=true;");
    builder.addLine("if (T=='true')");
    builder.addLine("T=true;");
    builder.addLine("if (T=='false')");
    builder.addLine("T=false;");
    builder.addLine("var C5=document.styleSheets;");
    builder.addLine("for (let i=0;i<C5.length;i++) {");
    builder.addLine("var C4=C5[i];");
    builder.addLine("if (C4.title!=null&&");
    builder.addLine("C4.title==C6) {");
    builder.addLine("if (C4.disabled!=T)");
    builder.addLine("C4.disabled=T;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("function f1(a8,o4) {");
    builder.addLine("const a7=a8.classList;");
    builder.addLine("if (a7.length==0)");
    builder.addLine("a7.add(o4);");
    builder.addLine("else if (a7.contains(o4) ==false)");
    builder.addLine("a7.add(o4);");
    builder.addLine("}");
    builder.addLine("function f3(c6,B2) {");
    builder.addLine("return c6.localeCompare(B2,undefined,{ sensitivity:'accent' }) ===0;");
    builder.addLine("}");
    builder.addLine("function f4(b1) {");
    builder.addLine("let q0={};");
    builder.addLine("if (typeof b1==='string') {");
    builder.addLine("q0.name='';");
    builder.addLine("q0.message=b1;");
    builder.addLine("q0.reason='exception';");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("q0.name=b1.name;");
    builder.addLine("q0.message=b1.message;");
    builder.addLine("q0.stack=b1.stack;");
    builder.addLine("q0.reason='exception';");
    builder.addLine("}");
    builder.addLine("return JSON.stringify(q0);");
    builder.addLine("}");
    builder.addLine("function f5(D,u0,y8,y9) {");
    builder.addLine("let u4=[];");
    builder.addLine("if (D.cssselector!=='') {");
    builder.addLine("if (y8) {");
    builder.addLine("y9.findtype='CSS Selector';");
    builder.addLine("y9.findvalue=D.cssselector;");
    builder.addLine("}");
    builder.addLine("u4=document.querySelectorAll(D.cssselector);");
    builder.addLine("}");
    builder.addLine("else if (D.xpath!=='') {");
    builder.addLine("if (y8) {");
    builder.addLine("y9.findtype='XPath';");
    builder.addLine("y9.findvalue=D.xpath;");
    builder.addLine("}");
    builder.addLine("let u3=document.evaluate(D.xpath,document,null,");
    builder.addLine("XPathResult.ORDERED_NODE_ITERATOR_TYPE,");
    builder.addLine("null);");
    builder.addLine("let D7=u3.iterateNext();");
    builder.addLine("while (D7) {");
    builder.addLine("u4.push(D7);");
    builder.addLine("D7=u3.iterateNext();");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (D.nodeiden!==null) {");
    builder.addLine("if (y8) {");
    builder.addLine("y9.findtype='Node identifier';");
    builder.addLine("y9.findvalue=D.nodeiden;");
    builder.addLine("}");
    builder.addLine("u4=f6(D,u0,y8,y9);");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("let c2=D.find;");
    builder.addLine("u4=[document];");
    builder.addLine("if (y8) {");
    builder.addLine("y9.findtype='Finds';");
    builder.addLine("y9.findvalues=D.find;");
    builder.addLine("}");
    builder.addLine("let c3=c2.length");
    builder.addLine("for (let i=0;i<c3;i++) {");
    builder.addLine("let c0=c2[i];");
    builder.addLine("u4=f9(u4,c0);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return u4;");
    builder.addLine("}");
    builder.addLine("function f6(D,u0,y8,y9) {");
    builder.addLine("let t3;");
    builder.addLine("let t4=[];");
    builder.addLine("let t8=D.nodeiden;");
    builder.addLine("let u4=[];");
    builder.addLine("let s4=t8.attributes;");
    builder.addLine("let t1=t8.counts;");
    builder.addLine("let v4=t8.type;");
    builder.addLine("let v5=null;");
    builder.addLine("switch (v4) {");
    builder.addLine("case'tag':{");
    builder.addLine("let v2=s4.tag;");
    builder.addLine("v5=v2;");
    builder.addLine("t4=document.getElementsByTagName(v2);");
    builder.addLine("if (y8) {");
    builder.addLine("y9.nodegetby='tag';");
    builder.addLine("y9.nodegetvalue=v2;");
    builder.addLine("y9.nodecount=t4.length;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'id':{");
    builder.addLine("let t7=s4.id;");
    builder.addLine("v5=t7;");
    builder.addLine("t3=document.getElementById(t7);");
    builder.addLine("if (t3!=null)");
    builder.addLine("t4=[t3];");
    builder.addLine("else");
    builder.addLine("t4=[];");
    builder.addLine("if (y8) {");
    builder.addLine("y9.nodegetby='id';");
    builder.addLine("y9.nodegetvalue=t7;");
    builder.addLine("y9.nodecount=t4.length;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'class':{");
    builder.addLine("let s8;");
    builder.addLine("if (s4.hasOwnProperty('bestclass'))");
    builder.addLine("s8=s4['bestclass'];");
    builder.addLine("else {");
    builder.addLine("let s9=s4.class;");
    builder.addLine("s8=s9[0];");
    builder.addLine("}");
    builder.addLine("v5=s8;");
    builder.addLine("t4=document.getElementsByClassName(s8);");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&s8=='')) {");
    builder.addLine("let b3=`Node identifier-node class is (${s8})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("y9.nodegetby='class';");
    builder.addLine("y9.nodegetvalue=s8;");
    builder.addLine("y9.nodecount=t4.length;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'name':{");
    builder.addLine("let u7=s4.name;");
    builder.addLine("v5=u7;");
    builder.addLine("t4=document.getElementsByName(u7);");
    builder.addLine("if (y8) {");
    builder.addLine("y9.nodegetby='name';");
    builder.addLine("y9.nodegetvalue=u7;");
    builder.addLine("y9.nodecount=t4.length;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("default:{");
    builder.addLine("let b3=\"Invalid node identifier type value-\"+v4;");
    builder.addLine("e0('Error','NodeIden',40,b3);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("let t5=t4.length;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&t5==0)) {");
    builder.addLine("let v3=v4;");
    builder.addLine("if (v5!=null)");
    builder.addLine("v3=v4+'/'+v5");
    builder.addLine("let b3=`Node identifier-get for (${v3}) returned (${t5}) nodes`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("let t9='full';");
    builder.addLine("if (t1[v4]==1&&t5==1)");
    builder.addLine("t9='partial';");
    builder.addLine("u4=f7(t4,");
    builder.addLine("t8,");
    builder.addLine("t9,");
    builder.addLine("u0,");
    builder.addLine("y8,");
    builder.addLine("y9);");
    builder.addLine("return u4;");
    builder.addLine("}");
    builder.addLine("function f7(t4,");
    builder.addLine("t8,");
    builder.addLine("t9,");
    builder.addLine("u0,");
    builder.addLine("y8,");
    builder.addLine("y9) {");
    builder.addLine("let u4=[];");
    builder.addLine("let t0=0;");
    builder.addLine("let z0;");
    builder.addLine("let t5=t4.length;");
    builder.addLine("a9:for (let i=0;i<t5;i++) {");
    builder.addLine("let M=t4[i];");
    builder.addLine("let d5;");
    builder.addLine("let x7;");
    builder.addLine("t0++;");
    builder.addLine("z0='nodetarget';");
    builder.addLine("if (t0>1)");
    builder.addLine("z0+=String(t0-1)");
    builder.addLine("let t2=t8.attributes;");
    builder.addLine("let C=f8(M,");
    builder.addLine("t2,");
    builder.addLine("u0,");
    builder.addLine("y8,");
    builder.addLine("y9,");
    builder.addLine("z0);");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&C<0.95)) {");
    builder.addLine("let b3=`Node identifier-current match value (${C}) for element (${M})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("if (C<0.95)");
    builder.addLine("continue a9;");
    builder.addLine("while (true) {");
    builder.addLine("x7=M.parentElement;");
    builder.addLine("if (typeof x7=='undefined'||");
    builder.addLine("x7==null)");
    builder.addLine("break;");
    builder.addLine("if (t8.hasOwnProperty('parent') ==false)");
    builder.addLine("break;");
    builder.addLine("z0='nodeparent';");
    builder.addLine("if (t0>1)");
    builder.addLine("z0+=String(t0-1);");
    builder.addLine("let u8=t8.parent;");
    builder.addLine("if (typeof u8=='undefined'||");
    builder.addLine("u8==null)");
    builder.addLine("break;");
    builder.addLine("let x8=f8(x7,");
    builder.addLine("u8,");
    builder.addLine("u0,");
    builder.addLine("y8,");
    builder.addLine("y9,");
    builder.addLine("z0);");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&x8<0.95)) {");
    builder.addLine("let b3=`Node identifier-parent match value (${x8}) for element (${x7})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("if (x8<0.95)");
    builder.addLine("continue a9;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("while (true) {");
    builder.addLine("if (typeof x7=='undefined'||");
    builder.addLine("x7==null)");
    builder.addLine("break;");
    builder.addLine("if (t8.hasOwnProperty('grandparent') ==false)");
    builder.addLine("break;");
    builder.addLine("d5=x7.parentElement;");
    builder.addLine("if (typeof d5=='undefined'||");
    builder.addLine("d5==null)");
    builder.addLine("break;");
    builder.addLine("z0='nodegrandparent';");
    builder.addLine("if (t0>1)");
    builder.addLine("z0+=String(t0-1);");
    builder.addLine("let t6=t8.grandparent;");
    builder.addLine("if (typeof t6=='undefined'||");
    builder.addLine("t6==null)");
    builder.addLine("break;");
    builder.addLine("let d6=f8(d5,");
    builder.addLine("t6,");
    builder.addLine("u0,");
    builder.addLine("y8,");
    builder.addLine("y9,");
    builder.addLine("z0);");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&d6<0.95)) {");
    builder.addLine("let b3=`Node identifier-grandparent match value (${d6}) for element (${d5})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("if (d6<0.95)");
    builder.addLine("continue a9;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("u4.push(M);");
    builder.addLine("}");
    builder.addLine("return u4;");
    builder.addLine("}");
    builder.addLine("function f8(t3,");
    builder.addLine("s4,");
    builder.addLine("u0,");
    builder.addLine("y8,");
    builder.addLine("y9,");
    builder.addLine("y7) {");
    builder.addLine("let S=0.0;");
    builder.addLine("let r0;");
    builder.addLine("let s3;");
    builder.addLine("let r8=[];");
    builder.addLine("let v8=0.0;");
    builder.addLine("let v9;");
    builder.addLine("if (y8) {");
    builder.addLine("r0=t3.tagName;");
    builder.addLine("s3=s4.tag");
    builder.addLine("let r7=new Object();");
    builder.addLine("r7.type='tag';");
    builder.addLine("r7.attributevalue=s3;");
    builder.addLine("r7.actualvalue=r0;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (f3(t3.tagName,s4.tag))");
    builder.addLine("E0=1.0;");
    builder.addLine("r7.matchvalue=E0;");
    builder.addLine("y9[y7+'tag']=r7;");
    builder.addLine("}");
    builder.addLine("if (f3(t3.tagName,s4.tag) ==false) {");
    builder.addLine("return 0.0;");
    builder.addLine("}");
    builder.addLine("let s0=Object.keys(s4);");
    builder.addLine("let s1=s0.length;");
    builder.addLine("for (let i=0;i<s1;i++) {");
    builder.addLine("let r9=s0[i];");
    builder.addLine("if (r9=='bestclass')");
    builder.addLine("continue;");
    builder.addLine("let r7=new Object();");
    builder.addLine("v9=0.0;");
    builder.addLine("S++;");
    builder.addLine("s3=s4[r9];");
    builder.addLine("if (r9=='tag') {");
    builder.addLine("r0=t3.tagName;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("f3(r0,s3))");
    builder.addLine("E0=1.0;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&E0!=1.0)) {");
    builder.addLine("b3=`Node identifier-key (${r9}) actual (${r0}) expected (${s3})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (${r9}) comparison value (${E0})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s3;");
    builder.addLine("r7.actualvalue=r0;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("f3(r0,s3))");
    builder.addLine("E0=1.0;");
    builder.addLine("r7.matchvalue=E0;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (r0==null)");
    builder.addLine("continue;");
    builder.addLine("if (f3(r0,s3))");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("else if (r9=='class') {");
    builder.addLine("if (Array.isArray(s3) &&");
    builder.addLine("s3.length>0)");
    builder.addLine("s3=s3[0];");
    builder.addLine("let r1=t3.getAttribute('class');");
    builder.addLine("if (r1!=null) {");
    builder.addLine("let r3=r1.split(' ');");
    builder.addLine("let r4=r3.length;");
    builder.addLine("let r2=[];");
    builder.addLine("for (let i=0;i<r4;i++) {");
    builder.addLine("let r5=r3[i];");
    builder.addLine("if (r5.endsWith('\\n')) {");
    builder.addLine("let r6=r5.length;");
    builder.addLine("r5=r5.substr(0,r6-1);");
    builder.addLine("}");
    builder.addLine("if (r5.length>0)");
    builder.addLine("r2.push(r5);");
    builder.addLine("}");
    builder.addLine("if (r2.length>0) {");
    builder.addLine("r0=[...r2];");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("r0=null;");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("r0=null;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("r0.includes(s3))");
    builder.addLine("E0=1.0;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&E0!=1.0)) {");
    builder.addLine("b3=`Node identifier-key (${r9}) actual (${r0}) expected (${s3})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (${r9}) comparison value (${E0})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s3;");
    builder.addLine("r7.actualvalue=r0;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("r0.includes(s3))");
    builder.addLine("E0=1.0;");
    builder.addLine("r7.matchvalue=E0;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (r0==null)");
    builder.addLine("continue;");
    builder.addLine("if (r0.includes(s3))");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("else if (r9=='innertext') {");
    builder.addLine("let u1;");
    builder.addLine("let u2=t3.innerText;");
    builder.addLine("if ((typeof u2) =='undefined')");
    builder.addLine("u2=null;");
    builder.addLine("if (u2!=null) {");
    builder.addLine("u1=u2.indexOf('ï¿½');");
    builder.addLine("if (u1>=0)");
    builder.addLine("u2=u2.substring(0,u1);");
    builder.addLine("u1=u2.indexOf('\\n');");
    builder.addLine("if (u1>=0)");
    builder.addLine("u2=u2.substring(0,u1);");
    builder.addLine("u2=u2.toLowerCase().trim();");
    builder.addLine("if (u2.length>20)");
    builder.addLine("u2=u2.substring(0,20);");
    builder.addLine("}");
    builder.addLine("r0=u2;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("f3(s3,r0))");
    builder.addLine("E0=1.0;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&E0!=1.0)) {");
    builder.addLine("b3=`Node identifier-key (${r9}) actual (${r0}) expected (${s3})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (${r9}) comparison value (${E0})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s3;");
    builder.addLine("r7.actualvalue=r0;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("f3(s3,r0))");
    builder.addLine("E0=1.0;");
    builder.addLine("r7.matchvalue=E0;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (r0==null)");
    builder.addLine("continue;");
    builder.addLine("if (f3(s3,r0))");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("else if (r9=='phash') {");
    builder.addLine("r0=s3;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("f3(s3,r0))");
    builder.addLine("E0=1.0;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&E0!=1.0)) {");
    builder.addLine("b3=`Node identifier-key (${r9}) actual (${r0}) expected (${s3})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (${r9}) comparison value (${E0})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s3;");
    builder.addLine("r7.actualvalue=r0;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("f3(s3,r0))");
    builder.addLine("E0=1.0;");
    builder.addLine("r7.matchvalue=E0;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (r0==null)");
    builder.addLine("continue;");
    builder.addLine("if (f3(s3,r0))");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("else if (r9=='src') {");
    builder.addLine("r0=t3.getAttribute(r9);");
    builder.addLine("let u9=false;");
    builder.addLine("while (true) {");
    builder.addLine("let q7;");
    builder.addLine("let q8;");
    builder.addLine("let q9;");
    builder.addLine("let s5;");
    builder.addLine("if (r0==null)");
    builder.addLine("break;");
    builder.addLine("q7=r0.indexOf('http');");
    builder.addLine("if (q7<0)");
    builder.addLine("break;");
    builder.addLine("q9=i7(r0);");
    builder.addLine("q8=g0(q9);");
    builder.addLine("if (q8==null) {");
    builder.addLine("h0(q9);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (s4.hasOwnProperty('phash') ==false)");
    builder.addLine("break;");
    builder.addLine("let s6=s4['phash'];");
    builder.addLine("s5=h3(s6,");
    builder.addLine("q8);");
    builder.addLine("u9=true;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&s5>=0.10)) {");
    builder.addLine("b3=`Node identifier-key (perceptual hash) actual (${q8}) expected (${s6})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (perceptual hash) comparison value (${s5})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s6;");
    builder.addLine("r7.actualvalue=q8;");
    builder.addLine("r7.matchvalue=s5;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (s5<0.10) {");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (u9==false) {");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("s3==r0)");
    builder.addLine("E0=1.0;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&E0!=1.0)) {");
    builder.addLine("b3=`Node identifier-key (${r9}) actual (${r0}) expected (${s3})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (${r9}) comparison value (${E0})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s3;");
    builder.addLine("r7.actualvalue=r0;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("s3==r0)");
    builder.addLine("E0=1.0;");
    builder.addLine("r7.matchvalue=E0;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (r0==null)");
    builder.addLine("continue;");
    builder.addLine("if (s3==r0)");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else if (r9=='style') {");
    builder.addLine("r0=t3.getAttribute(r9);");
    builder.addLine("let u9=false;");
    builder.addLine("while (true) {");
    builder.addLine("let q7;");
    builder.addLine("let q8;");
    builder.addLine("let q9;");
    builder.addLine("let s5;");
    builder.addLine("if (r0==null)");
    builder.addLine("break;");
    builder.addLine("q7=r0.indexOf('background-image');");
    builder.addLine("if (q7<0)");
    builder.addLine("break;");
    builder.addLine("q7=r0.indexOf('url(\"http');");
    builder.addLine("if (q7<0)");
    builder.addLine("break;");
    builder.addLine("q9=r0.substr(q7+5);");
    builder.addLine("q7=q9.indexOf('\")');");
    builder.addLine("if (q7<0)");
    builder.addLine("break");
    builder.addLine("q9=q9.substring(0,q7);");
    builder.addLine("q9=i7(q9);");
    builder.addLine("q8=g0(q9);");
    builder.addLine("if (q8==null) {");
    builder.addLine("h0(q9);");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (s4.hasOwnProperty('phash') ==false)");
    builder.addLine("break;");
    builder.addLine("let s6=s4['phash'];");
    builder.addLine("s5=h3(s6,");
    builder.addLine("q8);");
    builder.addLine("u9=true;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&s5>=0.10)) {");
    builder.addLine("b3=`Node identifier-key (perceptual hash) actual (${q8}) expected (${s6})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (perceptual hash) comparison value (${s5})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s6;");
    builder.addLine("r7.actualvalue=q8;");
    builder.addLine("r7.matchvalue=s5;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (s5<0.10) {");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("if (u9==false) {");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("s3==r0)");
    builder.addLine("E0=1.0;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&E0!=1.0)) {");
    builder.addLine("b3=`Node identifier-key (${r9}) actual (${r0}) expected (${s3})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (${r9}) comparison value (${E0})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s3;");
    builder.addLine("r7.actualvalue=r0;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("s3==r0)");
    builder.addLine("E0=1.0;");
    builder.addLine("r7.matchvalue=E0;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (r0==null)");
    builder.addLine("continue;");
    builder.addLine("if (s3==r0)");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("r0=t3.getAttribute(r9);");
    builder.addLine("if (r9=='href'&&");
    builder.addLine("r0!=null)");
    builder.addLine("r0=i6(r0);");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("u0==h9.error) {");
    builder.addLine("let b3;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("s3==r0)");
    builder.addLine("E0=1.0;");
    builder.addLine("if (u0==h9.all||");
    builder.addLine("(u0==h9.error&&E0!=1.0)) {");
    builder.addLine("b3=`Node identifier-key (${r9}) actual (${r0}) expected (${s3})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("b3=`Node identifier-key (${r9}) comparison value (${E0})`;");
    builder.addLine("e0('Trace','NodeIden',41,b3);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("if (y8) {");
    builder.addLine("r7.type=r9;");
    builder.addLine("r7.attributevalue=s3;");
    builder.addLine("r7.actualvalue=r0;");
    builder.addLine("let E0=0.0;");
    builder.addLine("if (r0!=null&&");
    builder.addLine("s3==r0)");
    builder.addLine("E0=1.0;");
    builder.addLine("r7.matchvalue=E0;");
    builder.addLine("r8.push(r7);");
    builder.addLine("}");
    builder.addLine("if (r0==null)");
    builder.addLine("continue;");
    builder.addLine("if (s3==r0)");
    builder.addLine("v9=1.0;");
    builder.addLine("}");
    builder.addLine("v8+=v9;");
    builder.addLine("}");
    builder.addLine("if (y8)");
    builder.addLine("y9[y7]=r8;");
    builder.addLine("return v8/S;");
    builder.addLine("}");
    builder.addLine("function f9(u4,c0) {");
    builder.addLine("let x0=[];");
    builder.addLine("let u6=u4.length;");
    builder.addLine("for (let i=0;i<u6;i++) {");
    builder.addLine("let K=u4[i];");
    builder.addLine("let v4=K.constructor.name;");
    builder.addLine("if (typeof K.getElementById==='function'&&");
    builder.addLine("c0.attributeName==='id'&&");
    builder.addLine("c0.attributeValue!=='') {");
    builder.addLine("let p2=K.getElementById(c0.attributeValue);");
    builder.addLine("if (p2!==null) {");
    builder.addLine("if (c0.tag!=='') {");
    builder.addLine("if (c0.tag.toUpperCase() ===p2.tagName.toUpperCase())");
    builder.addLine("x0.push(p2);");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("x0.push(p2);");
    builder.addLine("}");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("if (typeof K.getElementByClassName==='function'&&");
    builder.addLine("c0.attributeName==='class'&&");
    builder.addLine("c0.attributeValue!=='') {");
    builder.addLine("let p3=K.getElementByClassName(c0.attributeValue);");
    builder.addLine("let p5=p3.length;");
    builder.addLine("for (let i=0;i<p5;i++) {");
    builder.addLine("p2=p3[i];");
    builder.addLine("if (c0.tag!=='') {");
    builder.addLine("if (c0.tag.toUpperCase() ===p2.tagName.toUpperCase())");
    builder.addLine("x0.push(p2);");
    builder.addLine("}");
    builder.addLine("else");
    builder.addLine("x0.push(p2);");
    builder.addLine("}");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("if (typeof K.getElementsByTagName==='function'&&");
    builder.addLine("c0.tag!=='') {");
    builder.addLine("let p7=K.getElementsByTagName(c0.tag);");
    builder.addLine("let p9=p7.length;");
    builder.addLine("if (c0.attributeName!==''&&");
    builder.addLine("c0.attributeValue!=='') {");
    builder.addLine("for (let i=0;i<p9;i++) {");
    builder.addLine("let p2=p7[i];");
    builder.addLine("if (!p2.hasAttribute(c0.attributeName))");
    builder.addLine("continue;");
    builder.addLine("if (p2.getAttribute(c0.attributeName) !==c0.attributeValue)");
    builder.addLine("continue;");
    builder.addLine("x0.push(p2);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("for (let i=0;i<p9;i++) {");
    builder.addLine("let p2=p7[i];");
    builder.addLine("x0.push(p2);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("let A=K.childNodes;");
    builder.addLine("let B=A.length;");
    builder.addLine("for (let i=0;i<B;i++) {");
    builder.addLine("let z=A[i];");
    builder.addLine("if (typeof z.hasAttribute!=='function')");
    builder.addLine("continue;");
    builder.addLine("if (typeof z.getAttribute!=='function')");
    builder.addLine("continue;");
    builder.addLine("if (!z.hasAttribute(c0.attributeName))");
    builder.addLine("continue;");
    builder.addLine("if (z.getAttribute(c0.attributeName) !==c0.attributeValue)");
    builder.addLine("continue;");
    builder.addLine("x0.push(z);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return x0;");
    builder.addLine("}");
    builder.addLine("function g0(E7) {");
    builder.addLine("let E9=E7.replace(/\\+/g,' ');");
    builder.addLine("if (i5.hasOwnProperty(E9))");
    builder.addLine("return i5[E9];");
    builder.addLine("return null;");
    builder.addLine("}");
    builder.addLine("function g1(w0) {");
    builder.addLine("let A1=new Set();");
    builder.addLine("while (w0) {");
    builder.addLine("Object.getOwnPropertyNames(w0).forEach(p=>A1.add(p));");
    builder.addLine("w0=Object.getPrototypeOf(w0);");
    builder.addLine("}");
    builder.addLine("return[...A1];");
    builder.addLine("}");
    builder.addLine("function g3(Z,z8) {");
    builder.addLine("let c4=Z;");
    builder.addLine("let b9=null;");
    builder.addLine("while (Z!=null) {");
    builder.addLine("let w=window.getComputedStyle(Z);");
    builder.addLine("if (w==null)");
    builder.addLine("break;");
    builder.addLine("let p=w['background-image'];");
    builder.addLine("let q=typeof p;");
    builder.addLine("if (p==null||p=='none'||q!='string'||p.indexOf('url(') <0) {");
    builder.addLine("Z=Z.parentElement;");
    builder.addLine("continue;");
    builder.addLine("}");
    builder.addLine("let o=p.lastIndexOf('/');");
    builder.addLine("if (o>0)");
    builder.addLine("b9=p.substring(0,o+1) +z8+'\")';");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("return b9;");
    builder.addLine("}");
    builder.addLine("function g4(w0,w1,hostName,pathName,sessionId) {");
    builder.addLine("let w2=g1(w0);");
    builder.addLine("let rv='{\"b7\":\"'+w1+'\"'+',\"hostName\":\"'+hostName+'\"'+',\"pathName\":\"'+pathName+'\"';");
    builder.addLine("rv+=',\"sessionId\":\"'+sessionId+'\"';");
    builder.addLine("w2.forEach(prop=>{");
    builder.addLine("let w3=w0[prop];");
    builder.addLine("let E1=typeof w3;");
    builder.addLine("let z5=true;");
    builder.addLine("if (E1=='v7'||");
    builder.addLine("E1=='boolean'||");
    builder.addLine("w3==null)");
    builder.addLine("z5=false;");
    builder.addLine("if (E1=='string') {");
    builder.addLine("let l6=w3.length;");
    builder.addLine("if (l6>=2) {");
    builder.addLine("let w4=w3.charAt(0);");
    builder.addLine("let w5=w3.charAt(l6-1);");
    builder.addLine("if (w4=='{'&&w5=='}') {");
    builder.addLine("z5=false;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("let w6=rv.length;");
    builder.addLine("try {");
    builder.addLine("rv+=',\"'+prop+'\":';");
    builder.addLine("if (z5)");
    builder.addLine("rv+='\"';");
    builder.addLine("rv+=w3;");
    builder.addLine("if (z5)");
    builder.addLine("rv+='\"';");
    builder.addLine("}");
    builder.addLine("catch (b1) {");
    builder.addLine("rv=rv.substring(0,w6);");
    builder.addLine("}");
    builder.addLine("});");
    builder.addLine("rv+='}';");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("function g5(link,hostName,pathName,sessionId) {");
    builder.addLine("let rv='{\"link\":\"'+link+'\"'+',\"hostName\":\"'+hostName+'\"'+',\"pathName\":\"'+pathName+'\"';");
    builder.addLine("rv+=',\"sessionId\":\"'+sessionId+'\"';");
    builder.addLine("rv+='}';");
    builder.addLine("return rv;");
    builder.addLine("}");
    builder.addLine("function g8(w0) {");
    builder.addLine("let A1=w0.constructor.name;");
    builder.addLine("return A1;");
    builder.addLine("}");
    builder.addLine("function h1(K,n1,z6) {");
    builder.addLine("let g='hdlmupdated'+n1;");
    builder.addLine("if (K.hasAttribute(g) ==false) {");
    builder.addLine("return 0;");
    builder.addLine("}");
    builder.addLine("let L=K.getAttribute(g);");
    builder.addLine("return L;");
    builder.addLine("}");
    builder.addLine("function h2(c8,B4) {");
    builder.addLine("let F5=c8^B4;");
    builder.addLine("let U=0;");
    builder.addLine("while (F5>0) {");
    builder.addLine("F5&=F5-1;");
    builder.addLine("U++;");
    builder.addLine("}");
    builder.addLine("return U;");
    builder.addLine("};");
    builder.addLine("function h3(c8,B4) {");
    builder.addLine("let W=h4(c8,B4);");
    builder.addLine("return W/(4.0*c8.length);");
    builder.addLine("};");
    builder.addLine("function h4(c8,B4) {");
    builder.addLine("let V=0;");
    builder.addLine("let c7,B3;");
    builder.addLine("while (c8.length>0) {");
    builder.addLine("if (c8.length>8) {");
    builder.addLine("c7=c8.substr(0,8);");
    builder.addLine("c8=c8.substr(8);");
    builder.addLine("B3=B4.substr(0,8);");
    builder.addLine("B4=B4.substr(8);");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("c7=c8;");
    builder.addLine("c8='';");
    builder.addLine("B3=B4;");
    builder.addLine("B4='';");
    builder.addLine("}");
    builder.addLine("let c5=parseInt(c7,16);");
    builder.addLine("let B1=parseInt(B3,16);");
    builder.addLine("V+=h2(c5,B1);");
    builder.addLine("}");
    builder.addLine("return V;");
    builder.addLine("};");
    builder.addLine("function h5(F2,y9,D3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D,");
    builder.addLine("y6) {");
    builder.addLine("let x=false;");
    builder.addLine("let E2='HDLmUpdateCount'+D.name;");
    builder.addLine("if (isNaN(window[E2]))");
    builder.addLine("window[E2]=0;");
    builder.addLine("else");
    builder.addLine("if (window[E2]>0)");
    builder.addLine("x=true;");
    builder.addLine("if (x||D3)");
    builder.addLine("return x;");
    builder.addLine("window[E2]+=1;");
    builder.addLine("let m3=new Object();");
    builder.addLine("let w7=null;");
    builder.addLine("let q2=null;");
    builder.addLine("if ((typeof F2) !='undefined'&&");
    builder.addLine("F2!=null&&");
    builder.addLine("F2!='')");
    builder.addLine("w7=F2;");
    builder.addLine("j0(m3,sessionIndexValueUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("x4,m8,");
    builder.addLine("k2,Y,C2,D.name,");
    builder.addLine("D.path,D.type,y6,");
    builder.addLine("w7,q2);");
    builder.addLine("let m2=D.type;");
    builder.addLine("y9.matcherror='fired';");
    builder.addLine("j6(m3,m2,'1.0',y9);");
    builder.addLine("return x;");
    builder.addLine("}");
    builder.addLine("function h6(K,n1) {");
    builder.addLine("let g='hdlmupdated'+n1;");
    builder.addLine("if (K.hasAttribute(g) ==false) {");
    builder.addLine("K.setAttribute(g,1);");
    builder.addLine("return 1;");
    builder.addLine("}");
    builder.addLine("let L=K.getAttribute(g);");
    builder.addLine("L++;");
    builder.addLine("K.setAttribute(g,L);");
    builder.addLine("return L;");
    builder.addLine("}");
    builder.addLine("function h8(A9) {");
    builder.addLine("let A8=JSON.parse(A9);");
    builder.addLine("let A5=A8.attributes;");
    builder.addLine("if (A5.hasOwnProperty('innertext')) {");
    builder.addLine("let A7=A5.innertext;");
    builder.addLine("let A6=A7.indexOf('$');");
    builder.addLine("if (A6>=0) {");
    builder.addLine("delete A5['innertext'];");
    builder.addLine("A8['attributes']=A5;");
    builder.addLine("};");
    builder.addLine("A9=JSON.stringify(A8);");
    builder.addLine("}");
    builder.addLine("return A9;");
    builder.addLine("}");
    builder.addLine("function i4(B0) {");
    builder.addLine("let D6=null;");
    builder.addLine("let l8={};");
    builder.addLine("if (B0.length>0&&B0.charAt(0) =='/') {");
    builder.addLine("l8.cssselector='';");
    builder.addLine("l8.nodeiden=null;");
    builder.addLine("l8.xpath=B0;");
    builder.addLine("}");
    builder.addLine("else if (B0.length>0&&B0.charAt(0) =='{') {");
    builder.addLine("l8.cssselector='';");
    builder.addLine("l8.nodeiden=JSON.parse(B0);");
    builder.addLine("l8.xpath=\"\";");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("l8.cssselector=B0;");
    builder.addLine("l8.nodeiden=null;");
    builder.addLine("l8.xpath='';");
    builder.addLine("}");
    builder.addLine("let m0=f5(l8,false,null,null);");
    builder.addLine("let m1=m0.length;");
    builder.addLine("for (let i=0;i<m1;i++) {");
    builder.addLine("let l9=m0[i];");
    builder.addLine("D6=l9.textContent;");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("return D6;");
    builder.addLine("}");
    builder.addLine("function i6(E7) {");
    builder.addLine("let E8=E7.indexOf(':');");
    builder.addLine("if (E8<0||");
    builder.addLine("E8>6)");
    builder.addLine("return E7;");
    builder.addLine("let E6=new URL(E7);");
    builder.addLine("return E7.substring(E6.origin.length);");
    builder.addLine("}");
    builder.addLine("function i7(E7) {");
    builder.addLine("let E5=E7.indexOf(':');");
    builder.addLine("if (E5<0)");
    builder.addLine("return E7;");
    builder.addLine("return E7.substring(E5+1);");
    builder.addLine("}");
    builder.addLine("function i8(k5) {");
    builder.addLine("k5=k5.replace(/A/g,'\u0e81');");
    builder.addLine("k5=k5.replace(/B/g,'\u0e82');");
    builder.addLine("k5=k5.replace(/C/g,'\u0e84');");
    builder.addLine("k5=k5.replace(/D/g,'\u0e87');");
    builder.addLine("k5=k5.replace(/E/g,'\u0e88');");
    builder.addLine("k5=k5.replace(/F/g,'\u0e8a');");
    builder.addLine("k5=k5.replace(/G/g,'\u0e8d');");
    builder.addLine("k5=k5.replace(/H/g,'\u0e94');");
    builder.addLine("k5=k5.replace(/I/g,'\u0e97');");
    builder.addLine("k5=k5.replace(/J/g,'\u0e99');");
    builder.addLine("k5=k5.replace(/K/g,'\u0e9f');");
    builder.addLine("k5=k5.replace(/L/g,'\u0ea1');");
    builder.addLine("k5=k5.replace(/M/g,'\u0ea3');");
    builder.addLine("k5=k5.replace(/N/g,'\u0ea5');");
    builder.addLine("k5=k5.replace(/O/g,'\u0ea7');");
    builder.addLine("k5=k5.replace(/P/g,'\u0eaa');");
    builder.addLine("k5=k5.replace(/Q/g,'\u0eab');");
    builder.addLine("k5=k5.replace(/R/g,'\u0ead');");
    builder.addLine("k5=k5.replace(/S/g,'\u0eb9');");
    builder.addLine("k5=k5.replace(/T/g,'\u0ebb');");
    builder.addLine("k5=k5.replace(/U/g,'\u0ebd');");
    builder.addLine("k5=k5.replace(/V/g,'\u0ec0');");
    builder.addLine("k5=k5.replace(/W/g,'\u0ec4');");
    builder.addLine("k5=k5.replace(/X/g,'\u0ec6');");
    builder.addLine("k5=k5.replace(/Y/g,'\u0ec8');");
    builder.addLine("k5=k5.replace(/Z/g,'\u0ecd');");
    builder.addLine("k5=k5.replace(/\\s/g,'\u0ed0');");
    builder.addLine("k5=k5.replace(/\\$/g,'\u0ed1');");
    builder.addLine("k5=k5.replace(/\\./g,'\u0ed2');");
    builder.addLine("k5=k5.replace(/\\//g,'\u0ed3');");
    builder.addLine("k5=k5.replace(/\\(/g,'\u0ed4');");
    builder.addLine("k5=k5.replace(/\\)/g,'\u0ed5');");
    builder.addLine("return k5;");
    builder.addLine("}");
    builder.addLine("function i9(D8,T) {");
    builder.addLine("var C5=document.styleSheets;");
    builder.addLine("for (let i=0;i<C5.length;i++) {");
    builder.addLine("var C4=C5[i];");
    builder.addLine("if (C4.title!=null&&");
    builder.addLine("C4.title==D8) {");
    builder.addLine("C4.disabled=T;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("function j0(A4,indexUsed,");
    builder.addLine("sessionIndexValue,x6,B8,");
    builder.addLine("y0,m8,");
    builder.addLine("k2,Y,C2,n7,");
    builder.addLine("n8,o1,");
    builder.addLine("y5,w9,q6) {");
    builder.addLine("let E3={};");
    builder.addLine("E3.indexValue=sessionIndexValue;");
    builder.addLine("E3.indexUsed=indexUsed;");
    builder.addLine("E3.parameters=d9(x6,' ');");
    builder.addLine("E3.sessionId=B8;");
    builder.addLine("E3.parmNumber=y0;");
    builder.addLine("E3.lookupValue=m8;");
    builder.addLine("E3.hostName=k2;");
    builder.addLine("E3.divisionName=Y;");
    builder.addLine("E3.siteName=C2;");
    builder.addLine("E3.modName=n7;");
    builder.addLine("E3.modPathValue=n8;");
    builder.addLine("E3.modType=o1;");
    builder.addLine("E3.pathValue=y5;");
    builder.addLine("E3.oldValue=w9;");
    builder.addLine("E3.newValue=q6;");
    builder.addLine("if (!A4.hasOwnProperty('updates'))");
    builder.addLine("A4.updates=[];");
    builder.addLine("A4.updates.push(E3);");
    builder.addLine("}");
    builder.addLine("function j7(k8) {");
    builder.addLine("k8=k8.trim();");
    builder.addLine("k8=k8.toLowerCase();");
    builder.addLine("k8=k8.replace(/\\s+/g,' ');");
    builder.addLine("let C3;");
    builder.addLine("if (k8.indexOf(';') >=0)");
    builder.addLine("C3=';'");
    builder.addLine("else");
    builder.addLine("C3=' ';");
    builder.addLine("let k6=k8.split(C3);");
    builder.addLine("for (let i in k6) {");
    builder.addLine("let C7=k6[i];");
    builder.addLine("if (C3==';')");
    builder.addLine("C7=C7.trim();");
    builder.addLine("if (C7=='unchanged'||");
    builder.addLine("C7=='novalue'||");
    builder.addLine("C7=='none'||");
    builder.addLine("C7.trim().length==0) {");
    builder.addLine("C7='none';");
    builder.addLine("k6[i]=C7;");
    builder.addLine("}");
    builder.addLine("if (Number.isInteger(Number(C7)) ==true) {");
    builder.addLine("C7+='px';");
    builder.addLine("k6[i]=C7;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("return k6;");
    builder.addLine("}");
    builder.addLine("function j8(k7) {");
    builder.addLine("k7=k7.trim();");
    builder.addLine("k7=k7.toLowerCase();");
    builder.addLine("k7=k7.replace(/\\s+/g,' ');");
    builder.addLine("let k6=k7.split(' ');");
    builder.addLine("return k6;");
    builder.addLine("}");
    builder.addLine("function j9() {");
    builder.addLine("var T;");
    builder.addLine("var D8='HDLmSessionClasses';");
    builder.addLine("T=sessionStorage.getItem(D8+'Disabled');");
    builder.addLine("if (T==null)");
    builder.addLine("T='true';");
    builder.addLine("T= (T=='true') ?false:true;");
    builder.addLine("sessionStorage.setItem(D8+'Disabled',T);");
    builder.addLine("i9(D8,T);");
    builder.addLine("}");
    builder.addLine("function k0(l2,l4,F1) {");
    builder.addLine("if (l2==null)");
    builder.addLine("l2='{}';");
    builder.addLine("let l1=JSON.parse(l2);");
    builder.addLine("l1[l4]=F1;");
    builder.addLine("l2=JSON.stringify(l1);");
    builder.addLine("return l2;");
    builder.addLine("}");
    builder.addLine("function d8(z6,h7) {");
    builder.addLine("let y6=document.location.pathname;");
    builder.addLine("const n9=[");
    int         counter;
    int         modsCount=mods.size();
    String      newLine;
    counter=0;
    for (HDLmMod mod:mods) {
      counter++;
      newLine=" ".repeat(24);
      newLine+=mod.getJsonSpecialSerializeNulls();
      if (counter<modsCount)
        newLine+=",";
      builder.addLine(newLine);
    }
    String  m5;
    if (m4==HDLmLogMatchingTypes.LOGMATCHINGYES)
      m5="true";
    else
      m5="false";
    Double   arrayEntry;
    String   d3=HDLmDefines.getString("HDLMFORCEVALUE");
    builder.addLine("];");
    builder.addLine("const B8='"+B7+"';");
    builder.addLine("const x6=g9();");
    builder.addLine("let o0=n9.length;");
    builder.addLine("for (let i=0;i<o0;i++) {");
    builder.addLine("let D=n9[i];");
    builder.addLine("try {");
    builder.addLine("switch (D.type) {");
    builder.addLine("case'extract':{");
    builder.addLine("let u4=f5(D,false);");
    builder.addLine("let u5=u4.length;");
    builder.addLine("for (let j=0;j<u5;j++) {");
    builder.addLine("let K=u4[j];");
    builder.addLine("if (j2.hasOwnProperty(D.name) &&");
    builder.addLine("j2[D.name]!=null)");
    builder.addLine("continue;");
    builder.addLine("let w7=K.textContent;");
    builder.addLine("j2[D.name]=w7;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("case'notify':{");
    builder.addLine("for (let j=0;j<D.valuesCount;j++) {");
    builder.addLine("let A9=D.values[j];");
    builder.addLine("A9=h8(A9);");
    builder.addLine("if (j3.hasOwnProperty(A9) &&");
    builder.addLine("j3[A9]!=null)");
    builder.addLine("continue;");
    builder.addLine("let B0=i4(A9);");
    builder.addLine("j3[A9]=B0;");
    builder.addLine("}");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("default:{");
    builder.addLine("break;");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("catch (b1) {");
    builder.addLine("console.log(b1);");
    builder.addLine("let b2=f4(b1);");
    builder.addLine("let o3=D.name;");
    builder.addLine("let C1='"+siteName+"';");
    builder.addLine("let X='"+divisionName+"';");
    builder.addLine("let k1='"+hostName+"';");
    builder.addLine("let r='Modification ('+o3+') Host ('+k1+') Error ('+b2+')';");
    builder.addLine("console.log(r);");
    builder.addLine("b2=k0(b2,'modification',o3);");
    builder.addLine("b2=k0(b2,'siteName',C1);");
    builder.addLine("b2=k0(b2,'divisionName',X);");
    builder.addLine("b2=k0(b2,'hostName',k1);");
    builder.addLine("b2=k0(b2,'sessionId',B8);");
    builder.addLine("j5(b2);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("for (let i=0;i<o0;i++) {");
    builder.addLine("let D=n9[i];");
    builder.addLine("try {");
    builder.addLine("d7(y6,");
    builder.addLine("D,");
    builder.addLine("B8,");
    builder.addLine("h7,");
    builder.addLine("x6,");
    builder.addLine("'"+hostName+"',");
    builder.addLine("'"+hostName+"',");
    builder.addLine("'"+divisionName+"',");
    builder.addLine("'"+siteName+"',");
    if (B5!=null)
      builder.addLine("'"+B5+"',");
    else
      builder.addLine("null,");
    builder.addLine("'"+d3+"',");
    builder.addLine(""+m5+",");
    builder.addLine("z6);");
    builder.addLine("}");
    builder.addLine("catch (b1) {");
    builder.addLine("console.log(b1);");
    builder.addLine("let b2=f4(b1);");
    builder.addLine("let o3=D.name;");
    builder.addLine("let C1='"+siteName+"';");
    builder.addLine("let X='"+divisionName+"';");
    builder.addLine("let k1='"+hostName+"';");
    builder.addLine("let r='Modification ('+o3+') Host ('+k1+') Error ('+b2+')';");
    builder.addLine("console.log(r);");
    builder.addLine("b2=k0(b2,'modification',o3);");
    builder.addLine("b2=k0(b2,'siteName',C1);");
    builder.addLine("b2=k0(b2,'divisionName',X);");
    builder.addLine("b2=k0(b2,'hostName',k1);");
    builder.addLine("b2=k0(b2,'sessionId',B8);");
    builder.addLine("j5(b2);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("function e0(b4,P,b0,b5) {");
    builder.addLine("let b2='';");
    builder.addLine("b2+='"+HDLmDefines.getString("HDLMPREFIX") +"'+' ';");
    builder.addLine("b2+=b4+' ';");
    builder.addLine("b2+=P+' ';");
    builder.addLine("b2+=b0.toString() +' ';");
    builder.addLine("b2+=b5;");
    builder.addLine("console.log(b2);");
    builder.addLine("}");
    builder.addLine("function e5(K,l3) {");
    builder.addLine("let t=JSON.parse(l3);");
    builder.addLine("for (const l5 in t) {");
    builder.addLine("if (!t.hasOwnProperty(l5))");
    builder.addLine("continue;");
    builder.addLine("let v=t[l5];");
    builder.addLine("if (v==null)");
    builder.addLine("K.removeAttribute(l5);");
    builder.addLine("else {");
    builder.addLine("if (l5=='class') {");
    builder.addLine("f1(K,v);");
    builder.addLine("}");
    builder.addLine("else {");
    builder.addLine("K.setAttribute(l5,v);");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    builder.addLine("}");
    for (HDLmMod mod:mods) {
    	if (mod.getType() !=HDLmModTypes.SCRIPT)
        continue;
    	String  p1=HDLmMod.replaceInString(mod.getName());
        int   valueCount=mod.getValues().size();
        for (int i=0;i<valueCount;i++) {
          String  Q=mod.getValues().get(i);
          newLine="  function HDLmExecute"+p1+i+"() {";
          builder.addLine(newLine);
          ArrayList<String>curValues=new ArrayList<String>(Arrays.asList(Q.split("/n")));
          for (int j=0;j<curValues.size();j++) {
            String  curLine=curValues.get(j);
            builder.addLine(""+curLine);
          }
          newLine="  }";
          builder.addLine(newLine);
      }
    }
    newLine="  function HDLmExecuteSwitch(n7,choiceNumber) {";
		builder.addLine(newLine);
    newLine="    switch (n7) {";
		builder.addLine(newLine);
    for (HDLmMod mod:mods) {
    	if (mod.getType() !=HDLmModTypes.SCRIPT)
        continue;
    	String  curModName=mod.getName();
      newLine="      case'"+curModName+"':";
		  builder.addLine(newLine);
    	String  curModNameInternal=HDLmMod.replaceInString(curModName);
      newLine="        switch (choiceNumber) {";
		  builder.addLine(newLine);
    	int   valueCount=mod.getValues().size();
	  	for (int i=0;i<valueCount;i++) {
      newLine="          case"+i+":";
		  builder.addLine(newLine);
        newLine="            HDLmExecute"+curModNameInternal+i+"();";
		    builder.addLine(newLine);
        newLine="            break;";
		    builder.addLine(newLine);
      }
      newLine="          default:";
	  	builder.addLine(newLine);
      newLine="            let b3=\"Invalid choice value-\"+choiceNumber"+";";
  		builder.addLine(newLine);
      newLine="            e0('Error','Choice',63,b3);";
	  	builder.addLine(newLine);
      newLine="            break;";
	  	builder.addLine(newLine);
      newLine="        }";
	  	builder.addLine(newLine);
    }
      newLine="      default:";
	  	builder.addLine(newLine);
      newLine="        let b3=\"Invalid rule name value-\"+n7;";
  		builder.addLine(newLine);
      newLine="        e0('Error','RuleName',62,b3);";
	  	builder.addLine(newLine);
      newLine="        break;";
	  	builder.addLine(newLine);
    newLine="    }";
		builder.addLine(newLine);
    newLine="  }";
		builder.addLine(newLine);
    builder.addLine("function g2(K) {");
    builder.addLine("let x2='';");
    builder.addLine("if (!K.hasAttributes())");
    builder.addLine("return x2;");
    builder.addLine("let m=K.attributes;");
    builder.addLine("let n=m.length;");
    builder.addLine("for (let i=n-1;i>=0;i--) {");
    builder.addLine("if (x2!='')");
    builder.addLine("x2+=' ';");
    builder.addLine("x2+=m[i].name+'='+\"'\"+m[i].value+\"'\";");
    builder.addLine("}");
    builder.addLine("return x2;");
    builder.addLine("}");
    builder.addLine("function g6(A3) {");
    builder.addLine("let m6={");
    JsonArray   indexJsonArray=getIndexJsonArray();
		if (!indexJsonArray.isJsonArray()) {
	 	  String  b3="JSON array in getJSBuildJs is invalid";
	 	  HDLmAssert.HDLmAssertAction(false,b3);
	  }
    int   indexJsonArraySize=indexJsonArray.size();
    boolean   logIsDebugEnabled=LOG.isDebugEnabled();
		if (logIsDebugEnabled) {
		  LOG.debug("In g6");
		  LOG.debug(C0);
	  }
    double  sessionIndexValue=0.0;
    if (C0!=null&&
!C0.equals("null"))
    	sessionIndexValue=Double.parseDouble(C0);
    for (int i=0;i<indexJsonArraySize;i++) {
    	JsonElement   indexJsonElement=indexJsonArray.get(i);
    	String  jsonHostName=HDLmJson.getJsonString(indexJsonElement,"website");
    	if (hostName.equals(jsonHostName)) {
    		JsonArray   rulesJsonArray=HDLmJson.getJsonArray(indexJsonElement,"rules");
    		JsonArray   choicesJsonArray=HDLmJson.getJsonArray(indexJsonElement,"choices");
    		if (!rulesJsonArray.isJsonArray()) {
    	 	  String  b3="JSON array in getJSBuildJs is invalid";
    	 	  HDLmAssert.HDLmAssertAction(false,b3);
    	  }
    		if (!choicesJsonArray.isJsonArray()) {
    	 	  String  b3="JSON array in getJSBuildJs is invalid";
    	 	  HDLmAssert.HDLmAssertAction(false,b3);
    	  }
    		int         rulesJsonArraySize=rulesJsonArray.size();
    		int         choicesJsonArraySize=choicesJsonArray.size();
    		JsonArray   choiceJsonArray=null;
        if (C0!=null&&
!C0.equals("null")) {
         	double      indexValue=sessionIndexValue*choicesJsonArraySize;
        	int         indexValueInt= (int) Math.floor(indexValue);
        	choiceJsonArray= (JsonArray) choicesJsonArray.get(indexValueInt);
      		if (!choiceJsonArray.isJsonArray()) {
      	 	  String  b3="JSON array in getJSBuildJs is invalid";
      	 	  HDLmAssert.HDLmAssertAction(false,b3);
      	  }
        }
    		counter=0;
        for (int j=0;j<rulesJsonArraySize;j++) {
        	counter++;
        	JsonElement   ruleJsonElement=rulesJsonArray.get(j);
      		if (!ruleJsonElement.isJsonPrimitive()) {
      			HDLmAssert.HDLmAssertAction(false,"JSON element is not a JSON primitive value");
      		}
        	String        A3=ruleJsonElement.getAsString();
          newLine=" ".repeat(23);
          newLine+="'";
          newLine+=A3;
          newLine+="':";
          if (C0==null||
          		C0.equals("null"))
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
    builder.addLine("let m7=m6[A3];");
    builder.addLine("return m7;");
    builder.addLine("}");
    builder.addLine("function g9() {");
    builder.addLine("let x2='';");
    builder.addLine("const x6=[");
    counter=0;
    int   sessionParametersArrayLength=sessionParametersArray.size();
    for (int i=0;i<sessionParametersArrayLength;i++) {
      counter++;
      newLine=" ".repeat(30);
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
    builder.addLine("return x6;");
    builder.addLine("}");
    builder.addLine("function h0(E7) {");
    builder.addLine("let F4=new XMLHttpRequest();");
    String   z1;
    z1=protocol.toString().toLowerCase();
    builder.addLine("let serverNameValue='"+serverName+"';");
    builder.addLine("let F0='"+z1+"://'+serverNameValue+'/"+HDLmConfigInfo.getPHashName() +"';");
    builder.addLine("F4.open('POST',F0);");
    builder.addLine("E7=encodeURIComponent(E7);");
    builder.addLine("F4.send(E7);");
    builder.addLine("}");
    builder.addLine("{");
    builder.addLine("let k1=location.hostname;");
    builder.addLine("let l7=location.href;");
    builder.addLine("let y4=document.location.pathname;");
    builder.addLine("let B9='"+B7+"';");
    builder.addLine("let b6=g5(l7,k1,y4,B9)");
    builder.addLine("Object.keys(window).forEach(key=>{");
    builder.addLine("if (key.startsWith('onmouse'))");
    builder.addLine("return;");
    builder.addLine("if (key.startsWith('onpointer'))");
    builder.addLine("return;");
    builder.addLine("if (/^on/.test(key)) {");
    builder.addLine("window.addEventListener(key.slice(2),event=>{");
    builder.addLine("let b7=g8(event);");
    builder.addLine("let b6=g4(event,b7,k1,y4,B9)");
    builder.addLine("});");
    builder.addLine("}");
    builder.addLine("});");
    builder.addLine("};");
    builder.addLine("let j4=new Object();");
    builder.addLine("let j2=new Object();");
    builder.addLine("let j3=new Object();");
    builder.addLine("var e8=true;");
    counter=0;
    builder.addLine("const i5={");
    Map<String,String>mapObj=HDLmPHashCache.getMap();
    long  mapSize=mapObj.size();
		for (Map.Entry<String,String>entry:mapObj.entrySet()) {
	    String  key=entry.getKey();
	    String  value=entry.getValue();
      counter++;
      newLine=" ".repeat(28);
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
    String   z2;
    z2=protocol.toString().toLowerCase();
    builder.addLine("function j5(R) {");
    builder.addLine("R='"+HDLmDefines.getString("HDLMPOSTDATA") +"="+"'+R;");
    builder.addLine("let k3=new XMLHttpRequest();");
    builder.addLine("let serverNameValue='"+serverName+"';");
    builder.addLine("let E7='"+z2+"://'+serverNameValue+'/"+HDLmDefines.getString("HDLMPOSTDATA") +"';");
    builder.addLine("k3.open('POST',E7);");
    builder.addLine("k3.setRequestHeader('Content-type','application/x-www-form-urlencoded');");
    builder.addLine("R=encodeURIComponent(R);");
    builder.addLine("k3.send(R);");
    builder.addLine("}");
    builder.addLine("function j6(A4,z7,F3,b2) {");
    builder.addLine("A4.reason=z7;");
    builder.addLine("A4.weight=F3;");
    builder.addLine("A4.error=b2;");
    builder.addLine("let E4=JSON.stringify(A4);");
    builder.addLine("j5(E4);");
    builder.addLine("}");
    if (C0==null||
        C0.equals("null"))
      builder.addLine("let h7=null;");
    else
    	builder.addLine("let h7="+C0+";");
    builder.addLine("f0('.HDLmClassPrimary',"+
                                      "'background-color:yellow');");
    builder.addLine("f0('.HDLmClassBackground',"+
                                      "'filter:grayscale(100%)');");
    builder.addLine("let i3=document;");
    builder.addLine("let i1={attributes:true,childList:true,subtree:true};");
    builder.addLine("let i0=function (o2,i2) {");
    builder.addLine("let d1=false;");
    builder.addLine("if (document.location.hostname=='www.themarvelouslandofoz.com'&&");
    builder.addLine("document.readyState=='interactive')");
    builder.addLine("d1=true;");
    builder.addLine("d8(document.readyState,h7);");
    builder.addLine("if (document.readyState=='complete'||");
    builder.addLine("d1==true) {");
    builder.addLine("d8(document.readyState,h7);");
    builder.addLine("};");
    builder.addLine("};");
    builder.addLine("let i2=new MutationObserver(i0);");
    builder.addLine("i2.observe(i3,i1);");
    builder.addLine("let y6=document.location.pathname;");
    builder.addLine("let D={};");
    builder.addLine("D.enabled=true;");
    String  n5=HDLmDefines.getString("HDLMLOADPAGEMODNAME");
    builder.addLine("D.name='"+n5+"';");
    builder.addLine("D.parameter=-1;");
    builder.addLine("D.path='//.*/';");
    builder.addLine("D.pathre=true;");
    String  n6=HDLmModTypes.VISIT.toString().toLowerCase();
    builder.addLine("D.type='"+n6+"';");
    builder.addLine("D.values=['Yes'];");
    builder.addLine("D.valuesCount=1;");
    builder.addLine("const B8='"+B7+"';");
    builder.addLine("const x6=g9()");
    builder.addLine("const z6='unknown';");
    builder.addLine("d7(y6,");
    builder.addLine("D,");
    builder.addLine("B8,");
    builder.addLine("h7,");
    builder.addLine("x6,");
    builder.addLine("'"+hostName+"',");
    builder.addLine("'"+hostName+"',");
    builder.addLine("'"+divisionName+"',");
    builder.addLine("'"+siteName+"',");
    if (B5!=null)
      builder.addLine("'"+B5+"',");
    else
      builder.addLine("null,");
    builder.addLine("'"+d3+"',");
    builder.addLine("'"+m5+"',");
    builder.addLine("z6);");
    builder.addLine("</script>");
    actualJS=builder.getLinesWithSuffix("\r\n");
		if (useCreateFixedJS) {
		  HDLmUtility.fileClearContents(fixedJSName);
		  int             i;
		  int             actualJSLen;
		  StringBuilder   actualJSAdjustedBuilder=new StringBuilder();
		  actualJSLen=actualJS.length();
		  String  N;
		  for (i=0;i<actualJSLen;i++) {
		  	char  curChar=actualJS.charAt(i);
		  	if (curChar=='\u1000')
		  	  N="\\u1000";
		  	else if (curChar=='\u1001')
	  	    N="\\u1001";
        else if (curChar=='\u1002')
	  	    N="\\u1002";
        else if (curChar=='\u1003')
	  	    N="\\u1003";
        else if (curChar=='\u1004')
	  	    N="\\u1004";
        else if (curChar=='\u1005')
	  	    N="\\u1005";
        else if (curChar=='\u1006')
	  	    N="\\u1006";
        else if (curChar=='\u1007')
	  	    N="\\u1007";
        else if (curChar=='\u1008')
	  	    N="\\u1008";
        else if (curChar=='\u1009')
	  	    N="\\u1009";
        else if (curChar=='\u100a')
	  	    N="\\u100a";
        else if (curChar=='\u100b')
	  	    N="\\u100b";
        else if (curChar=='\u100c')
	  	    N="\\u100c";
        else if (curChar=='\u100d')
	  	    N="\\u100d";
        else if (curChar=='\u100e')
	  	    N="\\u100e";
        else if (curChar=='\u100f')
	  	    N="\\u100f";
        else if (curChar=='\u1010')
	  	    N="\\u1010";
        else if (curChar=='\u1011')
	  	    N="\\u1011";
        else if (curChar=='\u1012')
	  	    N="\\u1012";
        else if (curChar=='\u1013')
	  	    N="\\u1013";
        else if (curChar=='\u1014')
	  	    N="\\u1014";
        else if (curChar=='\u1015')
	  	    N="\\u1015";
        else if (curChar=='\u1016')
	  	    N="\\u1016";
        else if (curChar=='\u1017')
	  	    N="\\u1017";
        else if (curChar=='\u1018')
	  	    N="\\u1018";
        else if (curChar=='\u1019')
	  	    N="\\u1019";
        else if (curChar=='\u101a')
	  	    N="\\u101a";
        else if (curChar=='\u101b')
	  	    N="\\u101b";
        else if (curChar=='\u101c')
	  	    N="\\u101c";
        else if (curChar=='\u101d')
	  	    N="\\u101d";
        else if (curChar=='\u101e')
	  	    N="\\u101e";
        else if (curChar=='\u101f')
	  	    N="\\u101f";
        else if (curChar=='\u1020')
	  	    N="\\u1020";
        else if (curChar=='\u1021')
	  	    N="\\u1021";
        else if (curChar=='\u1022')
	  	    N="\\u1022";
        else if (curChar=='\u1023')
	  	    N="\\u1023";
        else if (curChar=='\u1024')
	  	    N="\\u1024";
        else if (curChar=='\u1025')
	  	    N="\\u1025";
        else if (curChar=='\u1026')
	  	    N="\\u1026";
        else if (curChar=='\u1027')
	  	    N="\\u1027";
        else if (curChar=='\u1028')
	  	    N="\\u1028";
        else if (curChar=='\u1029')
	  	    N="\\u1029";
        else if (curChar=='\u102a')
	  	    N="\\u102a";
        else if (curChar=='\u102b')
	  	    N="\\u102b";
        else if (curChar=='\u102c')
	  	    N="\\u102c";
        else if (curChar=='\u102d')
	  	    N="\\u102d";
        else if (curChar=='\u102e')
	  	    N="\\u102e";
        else if (curChar=='\u102f')
	  	    N="\\u102f";
        else if (curChar=='\u1030')
	  	    N="\\u1030";
        else if (curChar=='\u1031')
	  	    N="\\u1031";
        else if (curChar=='\u1032')
	  	    N="\\u1032";
        else if (curChar=='\u1033')
	  	    N="\\u1033";
        else if (curChar=='\u1034')
	  	    N="\\u1034";
        else if (curChar=='\u1035')
	  	    N="\\u1035";
        else if (curChar=='\u1036')
	  	    N="\\u1036";
        else if (curChar=='\u1037')
	  	    N="\\u1037";
        else if (curChar=='\u1038')
	  	    N="\\u1038";
        else if (curChar=='\u1039')
	  	    N="\\u1039";
		  	else {
			  	N="";
			  	N+=curChar;
		  	}
		  	actualJSAdjustedBuilder.append(N);
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
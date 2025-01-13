package com.headlamp;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * One instance of this class is built solely so that the process
 * tree position method can be invoked
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and one instance of this class
   will always be created */
public class HDLmProcessTreePHash implements HDLmProcessTreeInterface {
	/* The next statement initializes logging to some degree. Note that 
     having the slf4j jars and the log4j jars in the classpath also
     plays some role in logging initialization. */
  private static final Logger LOG = LoggerFactory.getLogger(HDLmProcessTreePHash.class);  
	@Override
	public void processTreePos(HDLmTree treePos) {
		/* Check if the current tree position has any details */
		HDLmMod   treePosDetails = treePos.getDetails();
		if (treePosDetails == null)
		  return;
		/* Check if the current set of details has a node identifier */ 
		HDLmNodeIden  treePosNodeIden = treePosDetails.getNodeIden();
		if (treePosNodeIden == null)
		  return;
		HDLmUtilityResponse   utilityResponse = null;
		Boolean               pHashUpdateCache = false;
		/* Check if the current node identifier has any attributes */ 
		Map<String, Object>  treePosAttributes = treePosNodeIden.getNodeAttributes();
		if (treePosAttributes == null)
		  return;
		/* Try to get the style, if it exists. In some cases, the style
		   uses a background image. We need to get and use the URL for 
		   the background image. */ 
		String  treePosStyle = (String) treePosAttributes.get("style");
		if (treePosStyle != null) {
			Boolean               pHashUpdateCacheStyle;
			HDLmUtilityResponse   utilityResponseStyle;
			/* Not all styles contain URLs. For example, linear gradients 
			   don't use URLs. The code below check for a zero-length URL
			   to handle this case. */ 
			boolean   pHashDidNotExistInAttrStyle = true;
			String  urlStrStyle = HDLmMenus.getUrlFromStyle(treePosStyle);
			String  treePosPHashStyle = (String) treePosAttributes.get("phash");
			if (treePosPHashStyle != null)
				pHashDidNotExistInAttrStyle = false;
			if (urlStrStyle != null &&
					urlStrStyle.length() > 0) {
				/* Get the perceptual hash value for a URL. Quite a few values
				   are extracted from the response object. */ 
				utilityResponseStyle = HDLmUtility.getPerceptualHashFromUrl(urlStrStyle, treePosPHashStyle);	
				treePosPHashStyle = utilityResponseStyle.getPHashValue();
				urlStrStyle = utilityResponseStyle.getUrlStr();
				pHashUpdateCacheStyle = utilityResponseStyle.getUpdateCache();
				if (pHashUpdateCacheStyle != null && 
						pHashUpdateCacheStyle == true)
					HDLmUtility.updatePHashCache(urlStrStyle, treePosPHashStyle);
				if (pHashDidNotExistInAttrStyle == true) {
				  treePosAttributes.put("phash", treePosPHashStyle);
				}
				return;				
			}
		}
		/* Try to get the source and procedural hash value */
		String  treePosSrc = (String) treePosAttributes.get("src");
		if (treePosSrc == null)
			return;
		boolean   pHashDidNotExistInAttr = true;
		String  treePosPHash = (String) treePosAttributes.get("phash");
		if (treePosPHash != null)
			pHashDidNotExistInAttr = false;
		/* We now have some non-null image source that may need to be
		   modified */
		String  modifiedSrc = HDLmUtility.removeHttpPrefix(treePosSrc);
		/* LOG.info(modifiedSrc); */
		/* LOG.info(treePosSrc); */
		/* Check if we need to fix the modified source. The modified source
	     is fixed, if the modified source starts with a dot and a forward 
	     slash. */  
		if (modifiedSrc.length() >= 2 &&
				modifiedSrc.startsWith("./"))
			modifiedSrc = modifiedSrc.substring(2);
		/* Check if we need to fix the modified source. The modified source
		   is fixed, if the original source does not start with a known 
		   protocol of some kind. */
		boolean   startsWithProtocol = false;
		if (treePosSrc.length() >= 6 &&
				treePosSrc.startsWith("https:"))
		  startsWithProtocol = true;
		else if (treePosSrc.length() >= 5 &&
	     			 treePosSrc.startsWith("http:"))
		       startsWithProtocol = true;
		/* If the original source did not start with a known protocol
		   then we must make some changes to the modified source */
		if (startsWithProtocol == false) {
			ArrayList<String>   treePosNodePath = treePos.getNodePath();
			String              treePosHostName = treePosNodePath.get(2);
			modifiedSrc = "//" + treePosHostName + "/" + modifiedSrc;
			/* LOG.info(modifiedSrc); */
			/* LOG.info(treePosSrc); */
		}		  
		/* We can now update the cache with the new URL value and the
		   new perceptual hash value (the perceptual hash value will
		   be null in some cases). */
		utilityResponse = HDLmUtility.getPerceptualHashFromUrl(modifiedSrc, treePosPHash);		
		treePosPHash = utilityResponse.getPHashValue();
		String  modifiedUrlStr = utilityResponse.getUrlStr();
		pHashUpdateCache = utilityResponse.getUpdateCache();
		if (pHashUpdateCache != null && 
				pHashUpdateCache == true)
			HDLmUtility.updatePHashCache(modifiedUrlStr, treePosPHash);
		if (pHashDidNotExistInAttr) {		  
		  treePosAttributes.put("phash", treePosPHash);
		}
	}
}
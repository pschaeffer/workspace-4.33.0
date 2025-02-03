package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jetty.websocket.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * HDLmWebSocketInternalAdapter short summary.
 *
 * HDLmWebSocketInternalAdapter description.
 *
 * @version 1.0
 * @author Peter
 */
/* This is not a purely static class and instances of this class can definitely
   be created */
public class HDLmWebSocketInternalAdapter extends WebSocketAdapter {
	/* The next statement initializes logging to some degree. Note that having the
	   slf4j jars and the log4j jars in the classpath also plays some role in
	   logging initialization.*/
	private static final Logger LOG = LoggerFactory.getLogger(HDLmWebSocketInternalAdapter.class);
	/* Session seems to be the only class instance field. It is not quite clear what
	   this field should be used for. */
	private Session session;
	/* This routine is invoked to handle inbound web sockets text messages. These
	   messages should be in JSON format and should be handleable via JSON. */
	public void handleMessage(String message) {
		/* Check if the JSON message instance passed by the caller is null */
		if (message == null) {
			String  errorText = "JSON message instance passed to handleMessage is null";
			throw new NullPointerException(errorText);
		}
		/* Get the lock used to serialize all WebSocket operations */
		HDLmDatabase.getDatabaseLock();
		/* Get the length of the current message value */
		int messageLength = message.length();
		/* Check if the JSON message length passed by the caller is zero */
		if (messageLength == 0) {
			String  errorText = "JSON message length passed to handleMessage is zero";
			throw new IllegalArgumentException(errorText);
		}
		/* Try to convert the message JSON to a JSON object. If this fails, then we do
		   not have a string than can be converted to a JSON object. If this works, then
		   we do have string than can be converted to a JSON object. */
		JsonParser parser = new JsonParser();		
		JsonElement   topNodeJsonElement = null; 
		boolean  logDebugEnabled = LOG.isDebugEnabled();
		try {
			if (logDebugEnabled)
			  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessage - about to parse inbound message");
			topNodeJsonElement = parser.parse(message);
			if (logDebugEnabled)
			  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessage - after parse of inbound message");
		} 
		catch (Exception exception) {
			if (message != null)
				HDLmUtility.logStringInParts("handleMessage", message);
			LOG.info("Exception while executing handleMessage");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}		
		/* Check if the JSON message passed by the caller is valid */
		if (!topNodeJsonElement.isJsonObject()) {
			String  errorText = "JSON message passed to handleMessage is invalid";
			throw new IllegalArgumentException(errorText);
		}
		/* Get the current request type from the message (if possible) */
		String requestType = HDLmJson.getJsonString(topNodeJsonElement, "HDLmRequestType");
		if (requestType == null) {
			HDLmAssertAction(false, "Request type value returned from getJsonValue is null");
		}
		/* Now that we have obtained the request type, we don't need the request type in
		   the JSON */
		HDLmJson.removeJsonKey(topNodeJsonElement, "HDLmRequestType");
		/* Try to get the URL value from the message. The URL value may or may not be
		   available. */
		String urlStr = null;
		if (HDLmJson.hasJsonKey(topNodeJsonElement, "HDLmUrlValue")) {
			urlStr = HDLmJson.getJsonString(topNodeJsonElement, "HDLmUrlValue");
			HDLmJson.removeJsonKey(topNodeJsonElement, "HDLmUrlValue");
		}
		/* Get the copy elements boolean value and remove the key */
		boolean nodeCopyElements = false;
		String newCopyElementsKey = "HDLmCopyElements";
		if (HDLmJson.hasJsonKey(topNodeJsonElement, newCopyElementsKey)) {
			nodeCopyElements = HDLmJson.getJsonBoolean(topNodeJsonElement, newCopyElementsKey);
			HDLmJson.removeJsonKey(topNodeJsonElement, newCopyElementsKey);
		}
		/* We can now handle each type of request */
		/* Handle each request type */
		switch (requestType) {
		  case "addTreeNode":
			  handleMessageAddTreeNode(topNodeJsonElement);
			  break;
      /* It doesn't look like the web socket client ever sends this
         message. It appears that the client doesn't use buildNode
         in any form. This appears to be left over from an earlier
         version of the client code. Actually it appears that 
         HDLmExtensionBothManageRule_saved and HDLmExtensionBothNodeIden
         use this message. */
		  case "buildNode":
			  handleMessageBuild(topNodeJsonElement, urlStr, nodeCopyElements);
			  break;
		  /* It doesn't look like the web socket client ever sends this message. It
		     appears that the client doesn't use copyNode(s) in any form. This appears 
		     to be left over from an earlier version of the client code. Actually it 
		     appears that HDLmExtensionBothManageRule_saved and HDLmExtensionBothNodeIden
         use this message. */
		  case "copyNode(s)":
			  handleMessageCopy(topNodeJsonElement, urlStr, nodeCopyElements);
			  break;
		  case "deleteTreeNode":
			  handleMessageDeleteTreeNode(topNodeJsonElement);
			  break;
		  case "getConfigValues":
			  handleMessageGetConfigValues(topNodeJsonElement);
			  break;
		  case "getModPart":
			  handleMessageGetModPart();
			  break;
		  case "getImageChoices":
			  handleMessageGetImageChoices(topNodeJsonElement, urlStr);
			  break;
		  /* It doesn't look like the web socket client ever sends this message. It
		     appears that the client doesn't use getModTree in any form. This appears 
		     to be left over from an earlier version of the client code. */
		  case "getModTree":
			  handleMessageGetPassThruTree();
			  break;
		  case "getTextChoices":
			  handleMessageGetTextChoices(topNodeJsonElement, urlStr);
			  break;
	    /* It doesn't look like the web socket client ever sends this message. It
		     appears that the client doesn't use getModTree in any form. This appears 
		     to be left over from an earlier version of the client code. */
	  	case "reloadNodes":
			  handleMessageReloadNodes();
			  break;
	    /* It doesn't look like the web socket client ever sends this message. It
		     appears that the client doesn't use saveDataValue in any form. The 
		     routine called below was never completed and never used. */ 
		  case "saveDataValue":
			  handleMessageSaveDataValue(topNodeJsonElement);
			  break;
		  case "storeTreeNodes":
			  handleMessageStoreTreeNodes(topNodeJsonElement);
			  break;
			/* Transfer something from one system (probably test) to another system
			   (probably production). The caller specifies all of the details of what
			   should be transferred. */ 
		  case "transferSomething":
		  	handleMessageTransferSomething(topNodeJsonElement); 
		  	break;
			/* Undo the last operation that was done. This is the 'current' operation.
			   This is a very general undo operation. This operation might fail if 
			   there is no 'current' operation. */
			case "unDo":
				handleMessageUnDo(topNodeJsonElement);
				break;
		  /* It doesn't look like the web socket client ever sends this message. It
		     appears that the client doesn't use UpdateTree in any form. This appears 
		     to be left over from an earlier version of the client code. Actually it 
		     appears that HDLmGEM and HDLmGXE_Saved use this message.*/
		  case "updateTree":
			  handleMessageUpdateTree(topNodeJsonElement, urlStr);
			  break;
		  case "updateTreeNode":
			  handleMessageUpdateTreeNode(topNodeJsonElement);
			  break;
		  /* Report an error if the request type did not match one of the expected choices */
		  default:
			  String  errorFormat = "Invalid request type (\"%s\") passed to this routine";
			  String  errorText = String.format(errorFormat, requestType);
			  HDLmLogMsg.buildLogMsg(HDLmLogLevels.ERROR, "Invalid Request Type", 51, errorText);
		}
		/* Release the lock used to serialize all WebSocket operations */
		HDLmDatabase.releaseDatabaseLock();
	}
	/* This routine is invoked to handle inbound web sockets add tree node messages.
	   These messages should be in JSON format and should be handleable via JSON. */
	public void handleMessageAddTreeNode(JsonElement jsonElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageAddTreeNode is null";
			throw new NullPointerException(errorText);
		}
		/* Get the host name from the node path. The node path will always contain the
		   correct host name. */
		JsonArray   nodePathJson = HDLmJson.getJsonArray(jsonElement, "nodePath");
		int nodePathJsonSize = nodePathJson.size();
		if (nodePathJsonSize < 3) {
			String  errorText = "JSON array is not large enough for the host name";
			HDLmAssertAction(false, errorText);
		}
		/* Get the maximum node path length */
		int   maxPathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		/* Check if the node path length is too long */ 
		if  (nodePathJsonSize > maxPathLength) {
			String errorFormat = "Add node path length (%d) is too long";
			String  errorText = String.format(errorFormat, nodePathJsonSize);
			HDLmAssertAction(false, errorText);
		}
		/* Convert the JSON array with the node path to an ArrayList with the node path */
		ArrayList<String>   addNodePath = new ArrayList<String>();
		if (addNodePath == null) {
			String  errorText = "Node path ArrayList allocation in handleMessageAddTreeNode is null";
			throw new NullPointerException(errorText);
		}
		/* Loop over and process each element of the JSON array */
		for (JsonElement arrayEntry : nodePathJson) {
			String  nodePathEntry = arrayEntry.getAsString();
			addNodePath.add(nodePathEntry);
		}
		String  hostName = addNodePath.get(2);
		/* Try to find the tree node from the add node path. This node should
		   not exist at this point. */  
	  HDLmTree  addNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
			                                          addNodePath);
		if (addNode != null) {
		  String  failureJson = HDLmUtility.buildResultFailureJsonString(59);
		  HDLmWebSocketInternalAdapter.sendString(session, failureJson);
		  return;
		}		 
		/* Pass the JSON element to another routine for further handling */
		HDLmTree.addTreeNode(jsonElement, hostName, addNodePath);
		/* Log the current change */		  
		HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
						                HDLmChangeTypes.CHANGETYPEADD,
						                addNodePath, 
						                jsonElement); 
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	}
	/* This routine is invoked to handle inbound web sockets build messages. These
	   messages should be in JSON format and should be handleable via JSON. */
	public void handleMessageBuild(JsonElement jsonElement, String urlStr, boolean nodeCopyElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageBuild is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the URL string reference passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string reference passed to handleMessageBuild is null";
			throw new NullPointerException(errorText);
		}
		/* Get the host name from the URL stored in the JSON elements */
		String hostName = HDLmUtility.getHostNameFromUrlWithCheck(urlStr);
		if (hostName == null) {
			String  errorText = "Host name not obtained from URL string";
			HDLmAssertAction(false, errorText);
		}
		/* Pass the JSON element to another routine for further handling */
		HDLmTree.addNodeIden(jsonElement, hostName, urlStr, nodeCopyElement);
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	}
	/* This routine is invoked to handle inbound web sockets copy messages. These
	   messages should be in JSON format and should be handleable via JSON. */
	public void handleMessageCopy(JsonElement jsonElement, String urlStr, boolean nodeCopyElements) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageCopy is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the URL string reference passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string reference passed to handleMessageCopy is null";
			throw new NullPointerException(errorText);
		}
		/* Get the host name from the URL stored in the JSON elements */
		String hostName = HDLmUtility.getHostNameFromUrlWithCheck(urlStr);
		if (hostName == null) {
			String  errorText = "Host name not obtained from URL string";
			HDLmAssertAction(false, errorText);
		}
		/* Pass the JSON element to another routine for further handling */
		HDLmTree.addNodeIden(jsonElement, hostName, urlStr, nodeCopyElements);
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	}
	/* This routine is invoked to handle inbound web sockets delete tree node
	   messages. These messages should be in JSON format and should be handleable
	   via JSON. */
	public void            handleMessageDeleteTreeNode(JsonElement jsonElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageDeleteTreeNode is null";
			throw new NullPointerException(errorText);
		}
		/* Get the node path length */
		int pathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		JsonArray  nodePathJson = HDLmJson.getJsonArray(jsonElement, "nodePath");
		int nodePathJsonSize = nodePathJson.size();
		/* Check if the node path length is invalid */
		if (nodePathJsonSize <= 0 ||
		    nodePathJsonSize > pathLength) {
			String  errorFormat = "Delete node path length (%d) is incorrect";
			String  errorText = String.format(errorFormat, nodePathJsonSize);
			HDLmAssertAction(false, errorText);
		}
		/* Convert the JSON array with the node path to an ArrayList with the node path */
		ArrayList<String> deleteNodePath = new ArrayList<String>();
		if (deleteNodePath == null) {
			String  errorText = "Node path ArrayList allocation in delete is null";
			throw new NullPointerException(errorText);
		}
		/* Loop over and process each element of the JSON array */
		for (JsonElement arrayEntry : nodePathJson) {
			String  nodePathEntry = arrayEntry.getAsString();
			deleteNodePath.add(nodePathEntry);
		}
		/* Try to find the tree node for the delete node path. This will be the node
		   that is about to be deleted. */
		HDLmTree deleteNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
				                                          deleteNodePath);
		if (deleteNode == null) {
			String  nodeString = deleteNodePath.toString(); 
			HDLmUtility.logString(nodeString, LOG);
			HDLmUtility.logStackTrace();
			HDLmAssertAction(false, "Null delete tree node returned by locateTreeNode");
			return;
		}
		/* Send a failure message back to the client */
		if (deleteNode == null) {
		  String  failureJson = HDLmUtility.buildResultFailureJsonString(9);
		  HDLmWebSocketInternalAdapter.sendString(session, failureJson);
		  return;
		}  
		HDLmTree.deleteNodeTreeDatabase(deleteNode);
		/* Log the current change */		  
		HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
						                HDLmChangeTypes.CHANGETYPEDELETE,
						                deleteNodePath, 
						                jsonElement); 
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	}
	/* This routine is invoked to handle inbound web sockets get configuration 
     values messages. These messages should be in JSON format and should be
     handleable via JSON. */
	public void handleMessageGetConfigValues(JsonElement jsonElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageGetConfigValues is null";
			throw new NullPointerException(errorText);
		}
		/* Get the configuration names length */
		JsonArray  configNamesJson = HDLmJson.getJsonArray(jsonElement, "configNames");
		int  configNamesJsonSize = configNamesJson.size();
		/* Check if the configuration names length is invalid */
		if (configNamesJsonSize <= 0) {
			String  errorFormat = "Get configuration names length (%d) is incorrect";
			String  errorText = String.format(errorFormat, configNamesJsonSize);
			HDLmAssertAction(false, errorText);
		}
		/* Build an array list with the configuration names that
		   are allowed to be requested. Configuration names not 
		   in this list will not be returned. */ 
		final ArrayList<String>  allowedConfigNames = new ArrayList<String>(
		                           Arrays.asList("entriesBridgePassword", "entriesBridgeUserid"));
		/* create a new configuration object */
		HDLmConfigValues  configValues = new HDLmConfigValues();
		/* Loop over and process each element of the JSON array.
		   Each element is a configuration name for which the 
		   value is needed.  */
		for (JsonElement arrayEntry : configNamesJson) {
			String  configNamesEntry = arrayEntry.getAsString();
			/* Check if the configuration name is in the list 
			   of allowed configuration names. If not, generate
			   an exception. */
			if (!allowedConfigNames.contains(configNamesEntry)) {
				String  errorFormat = "Configuration name (%s) is not allowed";
				String  errorText = String.format(errorFormat, configNamesEntry);
				HDLmAssertAction(false, errorText);
			}
			/* Get the configuration value for the configuration name */
			String  configValueEntry = HDLmConfig.getConfigValue(configNamesEntry);
			/* Build a configuration object */
			HDLmConfigValue  configValue = new HDLmConfigValue(configNamesEntry, configValueEntry);
			/* Add the new configuration entry to the list of configurations */
			configValues.addConfig(configValue);
		}
		/* Build a JSON string with all of the configurations in it */
	  Gson    gsonInstance = HDLmMain.gsonMain; 
	  String  jsonString = gsonInstance.toJson(configValues);
		/* Get the remote endpoint we need to send a reply to */
		RemoteEndpoint remote = session.getRemote();
		try {
			remote.sendString(jsonString);
			LOG.info("sendString" + " - " + jsonString);
		} 
		catch (IOException ioException) {
			if (jsonString != null)
				LOG.info("JSON String - " + jsonString);
			LOG.info("IOException while executing handleMessageGetConfigValues");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			return;
		} 
		catch (Exception exception) {
			if (jsonString != null)
				LOG.info("JSON String - " + jsonString);
			LOG.info("Exception while executing handleMessageGetConfigValues");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	}
	/* This routine is invoked to handle inbound web sockets get image 
     choices messages. These messages should be in JSON format and   
     should be handleable via JSON. */
	public void handleMessageGetImageChoices(JsonElement jsonElement, String urlStr) {		
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageGetImageChoices is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to handleMessageGetImageChoices is null";
			throw new NullPointerException(errorText);
		}
		/* Get the input image (URL) that was part of the WebSocket messages */
		String  imageStr = HDLmJson.getJsonString(jsonElement, "HDLmInputImage"); 
		/* Get the path value string that was part of the WebSocket message */
		String  pathValueStr = HDLmJson.getJsonString(jsonElement, "HDLmPathValue");
		HDLmResponse  getResponse = HDLmOpenAI.getImageChoices(imageStr, urlStr, pathValueStr); 	
		/* ArrayList<String>  choiceList = HDLmOpenAIOld.getImageChoices(imageStr); */ 		
		
		String              errorMessage = getResponse.getErrorMessage(); 
		/* String  errorMessage = null; */
		ArrayList<String>   choiceList = getResponse.getStringList(); 
		/* Check if the choices list array reference is null */
		if (choiceList == null)
			choiceList = new ArrayList<String>();
		/* Build a JSON string with all of the choices.variants in it */	   
	  HDLmOpenAIEditImageResponse   dataObject = new HDLmOpenAIEditImageResponse(null, 
	 		                                                                         choiceList,
	 		                                                                         errorMessage); 
	  /* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
		String   dataJson = gsonInstance.toJson(dataObject);
		/* Get the remote endpoint we need to send a reply to */
		RemoteEndpoint remote = session.getRemote();
		try {
			remote.sendString(dataJson);
			LOG.info("sendString" + " - " + dataJson);
		} 
		catch (IOException ioException) {
			if (dataJson != null)
				LOG.info("JSON response String - " + dataJson);
			LOG.info("IOException while executing handleMessageGetImageChoices");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			return;
		} 
		catch (Exception exception) {
			if (dataJson != null)
				LOG.info("JSON response String - " + dataJson);
			LOG.info("Exception while executing handleMessageGetImageChoices");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	}	
	/* This routine is invoked to handle inbound web sockets get modifications
	   partial tree messages. These messages should be in JSON format and should be
	   handleable via JSON. */
	public void handleMessageGetModPart() {
		SocketAddress   a = this.session.getRemoteAddress(); 
		InetAddress b = null; 
		try {
			b =  InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		public static String getClientIp(Session session) {
      String ip = session.getUserProperties().get("javax.websocket.endpoint.remoteAddress").toString();
      int i1 = ip.indexOf("/");
      int i2 = ip.indexOf(":");
      return ip.substring(i1 + 1, i2);
    }
		*/
		
		
		/* Build a JSON string with all of the rows in it. We have a routine that does
		   exactly that. */
		String  outTree = HDLmTree.buildJsonStringTree(HDLmTree.getNodePassTreeTop());
		/* Build a JSON element with the entire rule tree and more */
		JsonParser    parser = new JsonParser();
		JsonElement   jsonTopElement = null;
		try {
			jsonTopElement = parser.parse(outTree);
		} 
		catch (Exception exception) {
			if (outTree != null)
				HDLmUtility.logStringInParts("GetModPart", outTree);
			LOG.info("Exception while executing handleMessageGetModPart");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
		if (jsonTopElement == null) {
			String  errorText = "JSON top element not obtained from JSON top object";
			HDLmAssertAction(false, errorText);
		}
		/* Convert the JSON elements to a JSON string */
		String jsonTopString = HDLmJson.getStringJson(jsonTopElement);
		/* Get the remote endpoint we need to send a reply to */
		RemoteEndpoint remote = session.getRemote();
		try {
			remote.sendString(jsonTopString);
			LOG.info("sendString" + " - " + jsonTopString);
		} 
		catch (IOException ioException) {
			if (jsonTopString != null)
				LOG.info("JSON Top String - " + jsonTopString);
			LOG.info("IOException while executing handleMessageGetModPart");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			return;
		} 
		catch (Exception exception) {
			if (jsonTopString != null)
				LOG.info("JSON Top String - " + jsonTopString);
			LOG.info("Exception while executing handleMessageGetModPart");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	}
	/* This routine is invoked to handle inbound web sockets get modifications tree
	   messages. These messages should be in JSON format and should be handleable
	   via JSON.
	   
	   It doesn't look like the web socket client ever sends this message. It
	   appears that the client doesn't use getModTree in any form. This appears 
	   to be left over from an earlier version of the client code.
	   
	   Note the use of HDLmTreeTypes.MOD below. This is probably obsolete code at
	   this point in time. */
	public void handleMessageGetPassThruTree() {
		/* Build a JSON element with the entire rule tree and more */
		String contentType = HDLmTreeTypes.MOD.toString();
		JsonElement jsonTopElement = HDLmMod.getJsonTreePassThru(contentType, null);
		if (jsonTopElement == null) {
			String  errorText = "JSON top element not returned from getJsonTreePassThru";
			HDLmAssertAction(false, errorText);
		}
		/* Add the request type to the JSON elements */
		HDLmJson.setJsonString(jsonTopElement, "HDLmRequestType", "getModTree");
		String jsonTopString = HDLmJson.getStringJson(jsonTopElement);
		/* Get the remote endpoint we need to send a reply to */
		RemoteEndpoint remote = session.getRemote();
		try {
			remote.sendString(jsonTopString);
			LOG.info("sendString" + " - " + jsonTopString);
		} 
		catch (IOException ioException) {
			if (jsonTopString != null)
				LOG.info("JSON Top String - " + jsonTopString);
			LOG.info("IOException while executing handleMessageGetPassThruTree - finally");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			return;
		} 
		catch (Exception exception) {
			if (jsonTopString != null)
				LOG.info("JSON Top String - " + jsonTopString);
			LOG.info("Exception while executing handleMessageGetPassThruTree - finally");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	} 
	/* This routine is invoked to handle inbound web sockets get text 
	   choices messages. These messages should be in JSON format and   
	   should be handleable via JSON. */
	public void handleMessageGetTextChoices(JsonElement jsonElement, String urlStr) {		 
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageGetTextChoices is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the URL string passed by the caller is null */
		if (urlStr == null) {
			String  errorText = "URL string passed to handleMessageGetTextChoices is null";
			throw new NullPointerException(errorText);
		}
		/* Get the input text that was part of the WebSocket message */
		String  textStr = HDLmJson.getJsonString(jsonElement, "HDLmInputText"); 
		/* Get the path value string that was part of the WebSocket message */
		String  pathValueStr = HDLmJson.getJsonString(jsonElement, "HDLmPathValue"); 
		HDLmResponse  getResponse = HDLmOpenAI.getTextChoices(textStr, urlStr, pathValueStr);		
		String              errorMessage = getResponse.getErrorMessage();
		ArrayList<String>   choiceList = getResponse.getStringList();
		/* Check if the choices list array reference is null */
		if (choiceList == null)
			choiceList = new ArrayList<String>();
		/* Build a JSON string with all of the choices.variants in it */	   
	  HDLmOpenAIEditTextResponse  dataObject = new HDLmOpenAIEditTextResponse(null, 
	  		                                                                    choiceList,
	  		                                                                    errorMessage); 
	  /* Convert the object to a JSON string */
	  Gson     gsonInstance = HDLmMain.gsonMain;
		String   dataJson = gsonInstance.toJson(dataObject);
		/* Get the remote endpoint we need to send a reply to */
		RemoteEndpoint remote = session.getRemote();
		try {
			remote.sendString(dataJson);
			LOG.info("sendString" + " - " + dataJson);
		} 
		catch (IOException ioException) {
			if (dataJson != null)
				LOG.info("JSON response String - " + dataJson);
			LOG.info("IOException while executing handleMessageGetTextChoices");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			return;
		} 
		catch (Exception exception) {
			if (dataJson != null)
				LOG.info("JSON response String - " + dataJson);
			LOG.info("Exception while executing handleMessageGetTextChoices");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
	}
	/* This routine is invoked to handle inbound web sockets reload nodes messages.
	   These messages should be in JSON format and should be handleable via JSON. */
	public void handleMessageReloadNodes() {
		/* Invoke a common routine to reload all of the nodes */
		HDLmMain.reloadNodes();
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	} 
	/* This routine is invoked to handle inbound web sockets save data value messages.
     These messages should be in JSON format and should be handleable via JSON. */
  /* It doesn't look like the web socket client ever sends this message. It
     appears that the client doesn't use saveDataValue in any form. This
     is a routine that was never completed and never used. */ 
	public void handleMessageSaveDataValue(JsonElement jsonElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageSaveDataValue is null";
			throw new NullPointerException(errorText);
		}
		/* Get the host name from the node path. The node path will always contain the
		   correct host name. */
		JsonArray nodePathJson = HDLmJson.getJsonArray(jsonElement, "nodePath");
		int nodePathJsonSize = nodePathJson.size();
		if (nodePathJsonSize < 3) {
			String  errorText = "JSON array is not large enough for the host name";
			HDLmAssertAction(false, errorText);
		}
		String hostName = nodePathJson.get(2).getAsString();
		/* Pass the JSON element to another routine for further handling */
		/* HDLmTree.addTreeNode(jsonElement, hostName); */
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	}
	/* This routine is invoked to handle inbound web sockets store tree node
	   messages. These messages should be in JSON format and should be handleable
	   via JSON. An add is executed if the tree node does not exist. An update is
	   executed if the tree node, already exists. */
	public void handleMessageStoreTreeNodes(JsonElement jsonElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageStoreTreeNodes is null";
			throw new NullPointerException(errorText);
		}
		boolean  logDebugEnabled = LOG.isDebugEnabled();
		if (logDebugEnabled)
		  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessageStoreTreeNodes - at the start");
		/* Get the node path length */
		int pathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		/* Get the number of rules we are storing */
		JsonArray   rulesArrayJson = HDLmJson.getJsonArray(jsonElement,"rules");
		int  rulesArraySize = rulesArrayJson.size();
		/* Process each rule */
		for (int i = 0; i < rulesArraySize; i++) {		
			JsonElement   jsonElementRule = rulesArrayJson.get(i);
			boolean   scriptsValid = true;
			/* Check if we have mod entry. We should always
			   have a mod entry. */
			String  modEntry = HDLmJson.getJsonString(jsonElementRule, "type");
			if (modEntry.equals("mod") == false) {
				String errorText = "Mod entry not found in storeTreeNode";
				HDLmAssertAction(false, errorText);
			}
			/* Get the rule details */
			JsonObject  modDetaisObject = HDLmJson.getJsonObject(jsonElementRule, "details");
			if (modDetaisObject == null) {
				String errorText = "Mod details object not found in storeTreeNode";
				HDLmAssertAction(false, errorText);
			}
			/* Get the rule name */
			JsonElement   modDetailsElement = (JsonElement) modDetaisObject;
			String  ruleName = HDLmJson.getJsonString(modDetailsElement, "name");
			/* Get the rule type */
			String  ruleType = HDLmJson.getJsonString(modDetailsElement, "type");
			/* We have a lot more work to do for script rules */
			if (ruleType.equals("script")) {
				JsonArray   scriptsArrayJson = HDLmJson.getJsonArray(modDetailsElement,"scripts");
				int   scriptsArraySize = scriptsArrayJson.size();
				/* Process each script */
				for (int j = 0; j < scriptsArraySize; j++) {		
					JsonElement   scriptJson = scriptsArrayJson.get(j); 
					String  scriptString = scriptJson.getAsString();
					boolean   scriptValid = HDLmHtml.checkIfJavaScriptValid(scriptString, 
							                                                    HDLmReportErrors.REPORTERRORS);
					/* Check if the current script is invalid, if it is, then 
					   the entire set of scripts is treated as invalid */
					if (scriptValid == false)
						scriptsValid = false;
				}
			}		
			/* Skip the current rule if the invalid scripts flag is set */
			/*
			scriptsValid = true;
			*/
			if (scriptsValid == false)
				continue;
			JsonArray   nodePathJson = HDLmJson.getJsonArray(jsonElementRule,"nodePath");
			int nodePathJsonSize = nodePathJson.size();
			/* Check if the node path length is invalid */
			if (nodePathJsonSize != pathLength) {
				String  errorFormat = "Store node path length (%d) is incorrect";
				String  errorText = String.format(errorFormat, nodePathJsonSize);
				HDLmAssertAction(false, errorText);
			}
			/* No work has been done so far */
			boolean   workDone = false;
			/* Convert the JSON array with the node path to an ArrayList with the node path */
			ArrayList<String>   storeNodePath = new ArrayList<String>();
			if (storeNodePath == null) {
				String  errorText = "Node path ArrayList allocation in update is null";
				throw new NullPointerException(errorText);
			}
			/* Loop over and process each element of the JSON array */
			for (JsonElement arrayEntry : nodePathJson) {
				String  nodePathEntry = arrayEntry.getAsString();
				storeNodePath.add(nodePathEntry);
			}
			/* Try to find the tree node from the update node path. This will be the node
		     that is about to be updated. */
		  HDLmTree  storeNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
				                                            storeNodePath);
		  /* Check if the node already exists, if it does not exist, then add it */
		  if (storeNode == null) {
		  	String  hostName = storeNodePath.get(2);
				/* Pass the JSON element to another routine for further handling */
				HDLmTree.addTreeNode(jsonElementRule, hostName, storeNodePath);
				/* Log the current change */		  
				HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
								                HDLmChangeTypes.CHANGETYPEADD,
								                storeNodePath, 
								                jsonElementRule); 
				/* Show that work has been done */
				workDone = true;	  	
		  }
			/* Check if any actual work has been done so far. Execute the update 
			   if no add has been done */
		  if (workDone == false) {
				if (logDebugEnabled)
				  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode - about to update tree node");
				/* LOG.info("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode"); */
				/* Pass the JSON element to another routine for further handling */
				HDLmTree.updateTreeNode(storeNode, jsonElementRule);
				/* Log the current change */		  
				HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
								                HDLmChangeTypes.CHANGETYPEMODIFY,
								                storeNodePath, 
								                jsonElementRule); 
				/* Show that work has been done */
				workDone = true;
		  }
		}
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);	 		  
	}
	/* This routine is invoked to handle inbound transfer requests. A typical request
	   might be to copy some number of rules from the test system to the production 
	   system. The caller describes the transfer request in some detail. */
	public void handleMessageTransferSomething(JsonElement jsonElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageTransferSomething is null";
			throw new NullPointerException(errorText);
		}
    /* Try to transfer something as specified by the caller */
		Integer       changeNumber = -1;
		HDLmResponse  transferResponse = HDLmTransferSomething.transferSomething(jsonElement);
		String  errorMessage = transferResponse.getErrorMessage();
		if (errorMessage == null)
			changeNumber = transferResponse.getReturnNumber();
		/* Send a failure message back to the client */
		if (errorMessage != null) {
		  String  failureJson = HDLmUtility.buildResultFailureJsonString(errorMessage, 60);
		  HDLmWebSocketInternalAdapter.sendString(session, failureJson);
		  return;
		} 
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(changeNumber);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	} 
	/* This routine is invoked to handle an inbound undo requests. This is
	   a request to undo the 'current' operation. This might be the last
	   recorded operation or it might not be. If the current operation is 
	   at the top of the operation ArrayList, then this will be a request
	   to undo the last recorded operation. However, if this routine is 
	   invoked more than once, the 'current' operation will keep going 
	   down while the 'top' operation stays the same. */
	public void handleMessageUnDo(JsonElement jsonElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageUnDo is null";
			throw new NullPointerException(errorText);
		}
		/* Get the input text that was part of the WebSocket message */
		Integer   changeNumber = HDLmJson.getJsonInteger(jsonElement, "HDLmChangeNumber"); 
	  /* Try to undo something as specified by the caller */
		String  errorMessage = HDLmUnRe.unReUnDoOperation(changeNumber);
		/* Send a failure message back to the client */
		if (errorMessage != null) {
		  String  failureJson = HDLmUtility.buildResultFailureJsonString(errorMessage, 61);
		  HDLmWebSocketInternalAdapter.sendString(session, failureJson);
		  return;
		} 
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	}
	/* This routine is invoked to handle inbound web sockets update tree messages.
	   These messages should be in JSON format and should be handleable via JSON.
	   The tree node passed by the caller may or may not exist. This routine must be
	   able to handle both cases.
	   
	   It doesn't look like the web socket client ever sends this message. It
	   appears that the client doesn't use UpdateTree in any form. This appears 
	   to be left over from an earlier version of the client code. */
	public void handleMessageUpdateTree(JsonElement jsonElement, final String urlValue) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageUpdateTree is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the URL value string reference passed by the caller is null */
		if (urlValue == null) {
			String  errorText = "URL string value reference passed to handleMessageUpdateTree is null";
			throw new NullPointerException(errorText);
		}
		/* Get the host name from the node path. The node path will always contain the
		   correct host name. */
		JsonArray nodePathJson = HDLmJson.getJsonArray(jsonElement, "nodePath");
		int nodePathJsonSize = nodePathJson.size();
		if (nodePathJsonSize < 3) {
			String  errorText = "JSON array is not large enough for the host name";
			HDLmAssertAction(false, errorText);
		}
		String hostName = nodePathJson.get(2).getAsString();
		/* Pass the JSON element to another routine for further handling. PassThru is
		   part of the method name to help distinguish between different routines that
		   used to have the same name. */
		String contentType = HDLmEditorTypes.PASS.toString();
		HDLmTree.updateTreePassThru(contentType, hostName, jsonElement);
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	}
	/* This routine is invoked to handle inbound web sockets update tree node
	   messages. These messages should be in JSON format and should be handleable
	   via JSON. */
	public void handleMessageUpdateTreeNode(JsonElement jsonElement) {
		/* Check if the JSON element instance passed by the caller is null */
		if (jsonElement == null) {
			String  errorText = "JSON element instance passed to handleMessageUpdateTreeNode is null";
			throw new NullPointerException(errorText);
		}
		boolean  logDebugEnabled = LOG.isDebugEnabled();
		if (logDebugEnabled)
		  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode - at the start");
		/* Get the node path length */
		int pathLength = HDLmDefines.getNumber("HDLMRULESNODEPATHLENGTH");
		JsonArray nodePathJson = HDLmJson.getJsonArray(jsonElement,"nodePath");
		int nodePathJsonSize = nodePathJson.size();
		/* Check if the node path length is invalid */
		if (nodePathJsonSize != pathLength) {
			String  errorFormat = "Update node path length (%d) is incorrect";
			String  errorText = String.format(errorFormat, nodePathJsonSize);
			HDLmAssertAction(false, errorText);
		}
		/* Convert the JSON array with the node path to an ArrayList with the node path */
		ArrayList<String>   updateNodePath = new ArrayList<String>();
		if (updateNodePath == null) {
			String  errorText = "Node path ArrayList allocation in update is null";
			throw new NullPointerException(errorText);
		}
		/* Loop over and process each element of the JSON array */
		for (JsonElement arrayEntry : nodePathJson) {
			String  nodePathEntry = arrayEntry.getAsString();
			updateNodePath.add(nodePathEntry);
		}
		/* Try to find the tree node from the update node path. This will be the node
	     that is about to be updated. */
	  HDLmTree  updateNode = HDLmTree.locateTreeNode(HDLmTree.getNodePassTreeTop(), 
			                                             updateNodePath);
		if (updateNode == null) {
			String  nodeString = updateNodePath.toString(); 
			HDLmUtility.logString(nodeString, LOG);
			HDLmUtility.logStackTrace();
			HDLmAssertAction(false, "Null update tree node returned by locateTreeNode");
			return;
		}
		/* Send a failure message back to the client */
		if (updateNode == null) {
		  String  failureJson = HDLmUtility.buildResultFailureJsonString(9);
		  HDLmWebSocketInternalAdapter.sendString(session, failureJson);
		  return;
		} 
		if (logDebugEnabled)
		  LOG.debug("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode - about to update tree node");
		/* LOG.info("In HDLmWebSocketInternalAdapter.handleMessageUpdateTreeNode"); */
		/* Pass the JSON element to another routine for further handling */
		HDLmTree.updateTreeNode(updateNode, jsonElement);
		/* Log the current change */		  
		HDLmChange.recordChange(HDLmChangeSourceTypes.CHANGESOURCESOCKETS, 
						                HDLmChangeTypes.CHANGETYPEMODIFY,
						                updateNodePath, 
						                jsonElement); 
		/* Send a success message back to the caller */
		String  successJson = HDLmUtility.buildResultSuccessJsonString(null);
		HDLmWebSocketInternalAdapter.sendString(session, successJson);
	} 
	/* This routine is invoked when a binary message is sent. So far this routine
	   has not been used. */
	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len) {
		String textFormat = "onWebSocketBinary - %d - %d";
		String textString = String.format(textFormat, offset, len);
		LOG.info(textString);
		super.onWebSocketBinary(payload, offset, len);
	}
	/* This routine is invoked when a web socket connection is destroyed. This
	   routine is definitely used (invoked). We should handle the closing of
	   connections in some way. How is not clear. */
	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		this.session = null;
		String textFormat = "onWebSocketClose - %d - %s";
		String textString = String.format(textFormat, statusCode, reason);
		LOG.info(textString);
		super.onWebSocketClose(statusCode, reason);
	}
	/* This routine is invoked when a web socket connection is established. This
	   routine is definitely used (invoked). We should handle new connections by
	   saving some data. What data is not not clear. */
	@Override
	public void onWebSocketConnect(Session session) {
		LOG.info("onWebSocketConnect");
		super.onWebSocketConnect(session);
		this.session = session;
	}
	/* This routine is invoked when a text message is sent. So far this routine has
	   been used each time a message is sent from the client. */
	@Override
	public void onWebSocketText(String message) {
		if (1 == 1)
			LOG.info("onWebSocketText" + " - " + message);
		super.onWebSocketText(message);
		/* Try to handle the current web sockets message */
		handleMessage(message);
	}
	/* Send a string back to the caller in a web socket message. The
	   string probably contains JSON, but this is not required. The 
	   caller provides the web socket session and the message string.
	   Of course, the low-level send operation can cause exceptions.
	   These exceptions are caught and reported. */
	protected static void  sendString(final Session sessionToBeUsed, final String sendValue) {
		/* Check if the session object passed by the caller is null */
		if (sessionToBeUsed == null) {
			String  errorText = "Session object passed to sendString is null";
			throw new NullPointerException(errorText);
		}
		/* Check if the send string string reference passed by the caller is null */
		if (sendValue == null) {
			String  errorText = "Send string reference passed to sendString is null";
			throw new NullPointerException(errorText);
		}
		/* Get the remote endpoint we need to send a message to */
		RemoteEndpoint remote = sessionToBeUsed.getRemote();
		try {
			remote.sendString(sendValue);
			LOG.info("sendString" + " - " + sendValue);
		} 
		catch (IOException ioException) {
			if (sendValue != null)
				LOG.info("JSON response String - " + sendValue);
			LOG.info("IOException while executing sendString");
			LOG.info(ioException.getMessage(), ioException);
			HDLmEvent.eventOccurred("IOException");
			return;
		} 
		catch (Exception exception) {
			if (sendValue != null)
				LOG.info("JSON response String - " + sendValue);
			LOG.info("Exception while executing sendString");
			LOG.info(exception.getMessage(), exception);
			HDLmEvent.eventOccurred("Exception");
			return;
		}
		return;
	}
}
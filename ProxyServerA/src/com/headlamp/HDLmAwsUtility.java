package com.headlamp;
import static com.headlamp.HDLmAssert.HDLmAssertAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClientBuilder;
import software.amazon.awssdk.services.secretsmanager.model.BatchGetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.BatchGetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretValueEntry;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;
import software.amazon.awssdk.core.exception.*;
/**
 * HDLmAwsUtility short summary.
 *
 * HDLmAwsUtility This class is used to implement access to the ASW secrets
 *                manager. We store various secrets using the AWS secrets
 *                manager. This module has routines for accessing them.
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmAwsUtility {
	/* The next statement initializes logging to some degree. Note that having the
	   slf4j jars and the log4j jars in the classpath also plays some role in
	   logging initialization. */
	private static final Logger   LOG = LoggerFactory.getLogger(HDLmAwsUtility.class);
	/* This class can never be instantiated */
	private HDLmAwsUtility() {}
  /* Build a secret manager client for accessing secrets
     stored by the AWS Secrets Manager */
	protected static SecretsManagerClient  buildAwsSecretsManagerClient(String regionName) {
		/* Check a few values passed by the caller */
		if (regionName == null) {
			String   errorText = "Region name reference passed to buildAwsSecretsManagerClient is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the length of the AWS region name is zero or less */
		if (regionName.length() <= 0) {
			String  errorText = "Region name is invalid in buildAwsSecretsManagerClient";
			throw new IllegalArgumentException(errorText);
		}
	  /* Create a Secrets Manager client */
		Region  region = Region.of(regionName);
 	  /* Create a Secrets Manager client */
	  SecretsManagerClient  client = null;   
	  client = SecretsManagerClient.builder().region(region).build();    
	  return client;
	}
	/* This routine get a set of secrets from the AWS secrets Manager. 
	   A batch call is used to minimize network turn-around time. */ 
	protected static Map<String, String>  getAMapOfSecrets(SecretsManagerClient secretsClient,
	                                                       ArrayList<String> secretsName) {
		/* Check a few values passed by the caller */
		if (secretsClient == null) {
			String   errorText = "Secrets client reference passed to getASetOfSecrets is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if secrets Array List is null */ 
		if (secretsName == null) {
			String   errorText = "Secrets name reference passed to getASetOfSecrets is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the length of the AWS secrets name Array List is zero or less */
		if (secretsName.size() <= 0) {
			String  errorText = "Secrets name is invalid in getASetOfSecrets";
			throw new IllegalArgumentException(errorText);
		}
		/* Build the request that we send to AWS. The request we send to AWS 
		   gets a bunch of secrets, all at once. Also create a variable for
		   the response */
	  BatchGetSecretValueRequest  batchGetSecretValueRequest = BatchGetSecretValueRequest.builder().secretIdList(secretsName).build();
	  BatchGetSecretValueResponse   batchGetSecretValueResponse;
	  /* Send the request to AWS */
	  batchGetSecretValueResponse = secretsClient.batchGetSecretValue(batchGetSecretValueRequest);
	  List<SecretValueEntry>  listOfSecretValues = null;
	  listOfSecretValues = batchGetSecretValueResponse.secretValues();
	  /* Process each secret value */
	  Map<String, String>   mapOfSecrets =  new TreeMap<String, String>();
	  for (SecretValueEntry secretValue:listOfSecretValues) {
	  	String  actualName = secretValue.name();
		  String  actualSecret = secretValue.secretString();
		  mapOfSecrets.put(actualName, actualSecret);
	  }
	  /* Return the map of secret names and values to the caller */
	  return mapOfSecrets;	                                                                                                         
	} 
	/* Retrieve a secret from the AWS Secrets Manager. The client 
	   value passed by the caller is used to access the secret. The
	   secret name is also passed by the caller. The actual secret
	   value is returned to the caller. The client value can be 
	   null. If the client value is null, this routine will build
	   the client. */
	protected static String  getJustOneSecretFromAws(SecretsManagerClient client, String secretName) {
		/* Check a few values passed by the caller */
		if (secretName == null) {
			String   errorText = "Secret name reference passed to getJustOneSecretFromAws is null";
			throw new NullPointerException(errorText);		
		}
		/* Check if the length of the AWS secret name is zero or less */
		if (secretName.length() <= 0) {
			String  errorText = "Secret name is invalid in getJustOneSecretFromAws";
			throw new IllegalArgumentException(errorText);
		}
		/* The client can be null. We build a Secret Manager client if 
		   need be */
	  if (client == null) {
	    /* Build a secret manager client */
	    client = HDLmAwsUtility.buildAwsSecretsManagerClient("us-east-2");
	  }
	  /* Retrieve the secret value */
	  GetSecretValueRequest   getSecretValueRequest = GetSecretValueRequest.builder().secretId(secretName).build(); 
	  GetSecretValueResponse  getSecretValueResponse;
	  try {
	    getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
	  } 
	  catch (Exception e) {
	    /* For a list of exceptions thrown, see */
	    /* https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html */
	    throw e;
	  }
	  String  actualSecret = null;     
	  actualSecret = getSecretValueResponse.secretString();    
	  return actualSecret;
	} 
}
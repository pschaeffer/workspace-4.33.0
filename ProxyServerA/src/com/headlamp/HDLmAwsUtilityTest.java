package com.headlamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
/**
 * HDLmAwsUtilityTest short summary.
 *
 * HDLmAwsUtilityTest description.
 *
 * @version 1.0
 * @author Peter
 */
class HDLmAwsUtilityTest {
	@Test
	void buildAwsSecretsManagerClient() {
		String  region = "us-east-2";
 	  /* Create a Secrets Manager client */
	  SecretsManagerClient  client = null;   
	  client = HDLmAwsUtility.buildAwsSecretsManagerClient(region);
	  assertNotNull(client, "Null returned from AWS Secrets Manager Client builder");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmAwsUtility.buildAwsSecretsManagerClient(null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Region name reference passed to buildAwsSecretsManagerClient is null");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmAwsUtility.buildAwsSecretsManagerClient("");},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Region name is invalid in buildAwsSecretsManagerClient");
		}		
	}
	@Test
	void getAMapOfSecrets() {
    /* Build a secret manager client */
		SecretsManagerClient  client = null; 
    client = HDLmAwsUtility.buildAwsSecretsManagerClient("us-east-2");
		/* Get and store the name of a secret */
		String  secretName = "EntriesBridgeUserid";
		/* Build an Array List of secret names */
		ArrayList<String>   secretsName = new ArrayList<String>();
		secretsName.add(secretName);
 	  /* Get a map of secrets stored inside the Secrets Manager */
	  Map<String, String>  secretsMap = null;   
	  secretsMap = HDLmAwsUtility.getAMapOfSecrets(client, secretsName);	   
	  assertNotNull(secretsMap, "Null returned from AWS Secrets Manager get secret");
	  /* Check the value returned from the AWS Secrets Manager */
	  String  secretValue = null; 
    secretValue = secretsMap.get(secretName);
    assertEquals(secretValue, "admin", "Unexpected value returned from AWS Secrets Manager get a map of secrets");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmAwsUtility.getAMapOfSecrets(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Secrets client reference passed to getASetOfSecrets is null");
		}
		{
			final SecretsManagerClient  clientFinal = client;
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmAwsUtility.getAMapOfSecrets(clientFinal, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Secrets name reference passed to getASetOfSecrets is null");
		}
		{
			final SecretsManagerClient  clientFinal = client;
			secretsName.clear();
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmAwsUtility.getAMapOfSecrets(clientFinal, secretsName);},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Secrets name is invalid in getASetOfSecrets");
		}
	}
	@Test
	void getJustOneSecretFromAws() {
		String  secretName = "EntriesBridgeUserid";
 	  /* Get a secret stored inside the Secrets Manager */
	  String  secretValue = null;   
	  secretValue = HDLmAwsUtility.getJustOneSecretFromAws(null, secretName);	   
	  assertNotNull(secretValue, "Null returned from AWS Secrets Manager get secret");
	  assertEquals(secretValue, "admin", "Unexpected value returned from AWS Secrets Manager get secret");
		{
			Throwable exception = assertThrows(NullPointerException.class, 
					                               () -> {HDLmAwsUtility.getJustOneSecretFromAws(null, null);},
					                               "Expected NullPointerException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Secret name reference passed to getJustOneSecretFromAws is null");
		}
		{
			Throwable exception = assertThrows(IllegalArgumentException.class, 
					                               () -> {HDLmAwsUtility.getJustOneSecretFromAws(null, "");},
					                               "Expected IllegalArgumentException");
			String execMsg = exception.getMessage();
			assertEquals(execMsg, "Secret name is invalid in getJustOneSecretFromAws");
		}
	}
}
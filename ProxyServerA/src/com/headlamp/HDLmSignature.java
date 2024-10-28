package com.headlamp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* Sign AWS Requests with Signature Version 4 Signing Process */
public class HDLmSignature {
  private String accessKeyID;
  private String secretAccessKey;
  private String regionName;
  private String serviceName;
  private String httpMethodName;
  private String canonicalUri;
  private LinkedHashMap<String, String> queryParameters;
  private LinkedHashMap<String, String> awsHeaders;
  private String payload;
  /* Other variables */
  private String signedHeaderString;
  private String xAmzDate;
  private String currentDate;
  private String xAmzTarget;
  private String contentType;
  private String hostName;
  private HDLmSignature(Builder builder) {
    accessKeyID = builder.accessKeyID;
    secretAccessKey = builder.secretAccessKey;
    regionName = builder.regionName;
    serviceName = builder.serviceName;
    httpMethodName = builder.httpMethodName;
    canonicalUri = builder.canonicalUri;
    queryParameters = builder.queryParameters;
    awsHeaders = builder.awsHeaders;
    payload = builder.payload;
    xAmzTarget = builder.xAmzTarget;
    contentType = builder.contentType;
    hostName = builder.hostName;        
    /* Get current timestamp value (GTM) */
    xAmzDate = builder.dateTime;
    currentDate = builder.dateTime.substring(0, 8);
  } 
  /* Task 1: Create a Canonical Request for Signature Version 4   
     @return Canonical Request */
  private String prepareCanonicalRequest() throws Exception {
    StringBuilder canonicalURL = new StringBuilder("");
    /* Step 1.1 Start with the HTTP request method (GET, PUT, POST, etc.), 
       followed by a newline character */     
    canonicalURL.append(httpMethodName).append("\n");
    /* Step 1.2 Add the canonical URI parameter, 
       followed by a newline character */
    canonicalUri = canonicalUri == null || canonicalUri.trim().isEmpty() ? "/" : canonicalUri;
    canonicalURL.append(canonicalUri).append("\n");
    /* Step 1.3 Add the canonical query string, 
       followed by a newline character */
    canonicalURL = addCanonicalQueryString(canonicalURL);
    /* Step 1.4 Add the canonical headers, 
       followed by a newline character */
    StringBuilder signedHeaders = new StringBuilder("");
    if (awsHeaders != null && !awsHeaders.isEmpty()) {
      for (Map.Entry<String, String> entrySet : awsHeaders.entrySet()) {
          String key = entrySet.getKey();
          signedHeaders.append(key).append(";");
          canonicalURL.append(key).append(":").append(entrySet.getValue()).append("\n");
      }
      /* Note: Each individual header is followed by a newline 
         character, meaning the complete list ends with a newline 
         character */
      canonicalURL.append("\n");
    } 
    else {
      canonicalURL.append("\n");
    }
    /* Step 1.5 Add the signed headers, followed 
       by a newline character */
    /* The code below removes the last semicolon from the signed headers */
    signedHeaderString = signedHeaders.substring(0, signedHeaders.length() - 1);  
    canonicalURL.append(signedHeaderString).append("\n");
    /* Step 1.6 Use a hash (digest) function like SHA256 to create a hashed value 
       from the payload in the body of the HTTP or HTTPS */
    if (payload == null) {
      payload = "";
    }
    String  payloadHash = hash(payload);
    canonicalURL.append(payloadHash); 
    /* System.out.println("Canonical Request: " + canonicalURL.toString()); */ 
    return canonicalURL.toString();
  }
  /* Add the canonical query string, followed by a newline character
     @param canonicalURL Canonical URL
     @return Updated canonicalURL */
  private StringBuilder addCanonicalQueryString(StringBuilder canonicalURL) throws Exception {
    StringBuilder queryString = new StringBuilder("");
    if (queryParameters != null && !queryParameters.isEmpty()) {
      for (Map.Entry<String, String> entrySet : queryParameters.entrySet()) {
        String key = entrySet.getKey();
        String value = entrySet.getValue();
        queryString.append(key).append("=").append(encodeParameter(value)).append("&");
      }
      queryString.deleteCharAt(queryString.lastIndexOf("&"));
      queryString.append("\n");
    } 
    else {
      queryString.append("\n");
    }
    canonicalURL.append(queryString);
    return canonicalURL;
  }
  /* Task 2: Create a String to Sign for Signature Version 4 */
  private String prepareStringToSign(String canonicalURL) throws Exception {
    String stringToSign;
    /* Step 2.1 Start with the algorithm designation, followed 
       by a newline character */
    stringToSign = "AWS4-HMAC-SHA256" + "\n";
    /* Step 2.2 Append the request date value, followed 
       by a newline character */
    stringToSign += xAmzDate + "\n";
    /* Step 2.3 Append the credential scope value, followed 
       by a newline character */
    stringToSign += currentDate + "/" + regionName + "/" + serviceName + "/" + "aws4_request" + "\n";
    /* Step 2.4 Append the hash of the canonical request that you created in Task 1: Create a Canonical Request
       for Signature Version 4 */
    stringToSign += hash(canonicalURL); 
    /* System.out.println("String to sign: " + stringToSign); */ 
    return stringToSign;
  }
  /* Task 3: Calculate the AWS Signature Version 4 */ 
  private String calculateSignature(String stringToSign) throws Exception {
      try {
          /* Step 3.1 Derive your signing key */
          byte[] signatureKey = getSignatureKey(secretAccessKey, currentDate, regionName, serviceName);
          /* Step 3.2 Calculate the signature */
          byte[] signature = hmacSHA256(signatureKey, stringToSign);
          /* Step 3.2.1 Encode signature (byte[]) to Hex */
          return bytesToHex(signature);
      } 
      catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException ex) {
        throw new Exception("Error while calculating the signature." + ex);
      }
  }
  /* Task 4: Add the Signing Information to the Request. We'll return Map of
     all headers put this headers in your request.
     @return Headers */
  public Map<String, String> getHeaders() throws Exception {
    awsHeaders.put("content-type", contentType);
    awsHeaders.put("host", hostName);
    awsHeaders.put("x-amz-date", xAmzDate);
    awsHeaders.put("x-amz-target", xAmzTarget);
    /* Execute Task 1: Create a Canonical Request for Signature Version 4 */
    String canonicalURL = prepareCanonicalRequest();
    /* Execute Task 2: Create a String to Sign for Signature Version 4 */
    String stringToSign = prepareStringToSign(canonicalURL);
    /* Execute Task 3: Calculate the AWS Signature Version 4 */
    String signature = calculateSignature(stringToSign);
    Map<String, String> header = new HashMap<>(0);
    header.put("x-amz-date", xAmzDate);
    header.put("Authorization", buildAuthorizationString(signature));
    /*
    System.out.println("##Signature:\n" + signature);
    System.out.println("##Header:");
    for (Map.Entry<String, String> entrySet : header.entrySet()) {
        System.out.println((entrySet.getKey() + " = " + entrySet.getValue()));
    }
    System.out.println("================================");
    */
    return header;
  }
  /* Build string for Authorization header
     @param strSignature Signature value
     @return Authorization String
     @throws ParseException */
  private String buildAuthorizationString(String strSignature) throws ParseException {
      return "AWS4-HMAC-SHA256" + " "
									              + "Credential=" + accessKeyID 
									              + "/" + currentDate 
									              + "/" 
									      		    + regionName + "/" 
									      		    + serviceName + "/"
									              + "aws4_request" + ", "
									              + "SignedHeaders=" + signedHeaderString + ", "
									              + "Signature=" + strSignature;
  }
  /* Hashes the string contents (assumed to be UTF-8) 
     using the SHA-256 algorithm 
     @param data text to be hashed
     @return SHA-256 hashed text */
  private String hash(String data) throws Exception {
    MessageDigest messageDigest;
    try {
        messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(data.getBytes("UTF-8"));
        byte[] digest = messageDigest.digest();
        return String.format("%064x", new java.math.BigInteger(1, digest));
    } 
    catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      throw new Exception("Error while hashing the string contents." + e);
    }
  }
  /* Provides the HMAC SHA 256 encoded value (using the provided key) 
     of the given data 
     @param data to be encoded
     @param key  to use for encoding
     @return HMAC SHA 256 encoded byte array
     @throws UnsupportedEncodingException The Character Encoding is not supported
     @throws InvalidKeyException          This is the exception for invalid Keys (invalid encoding, wrong length,
                                          uninitialized, etc) 
     @throws NoSuchAlgorithmException     When a particular cryptographic algorithm that is requested is not available */
  private byte[] hmacSHA256(byte[] key, String data) throws UnsupportedEncodingException, 
                                                            InvalidKeyException,
                                                            NoSuchAlgorithmException {
    String algorithm = "HmacSHA256";
    Mac mac = Mac.getInstance(algorithm);
    mac.init(new SecretKeySpec(key, algorithm));
    return mac.doFinal(data.getBytes("UTF-8"));
  }
  /* Generate AWS signature key    
     @param key         Key to be used for signing
     @param date        Current date stamp
     @param regionName  Region name of AWS cloud directory
     @param serviceName Name of the service being addressed
     @return Signature key
     @throws UnsupportedEncodingException The Character Encoding is not supported
     @throws InvalidKeyException          This is the exception for invalid Keys (invalid encoding, wrong length,
                                          uninitialized, etc)
     @throws NoSuchAlgorithmException     When a particular cryptographic algorithm that is requested is not available  */
  private byte[] getSignatureKey(String key, String date, String regionName, String serviceName)
                   throws UnsupportedEncodingException, 
                   InvalidKeyException, 
                   NoSuchAlgorithmException {
      byte[] kSecret = ("AWS4" + key).getBytes("UTF-8");
      byte[] kDate = hmacSHA256(kSecret, date);
      byte[] kRegion = hmacSHA256(kDate, regionName);
      byte[] kService = hmacSHA256(kRegion, serviceName);
      return hmacSHA256(kService, "aws4_request");
  }
  /* Convert byte array to Hex
     @param bytes bytes to be hex encoded 
     @return hex encoded String of the given byte array */
  private String bytesToHex(byte[] bytes) {
    final char[] hexArray = "0123456789ABCDEF".toCharArray();
    char[] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = hexArray[v >>> 4];
      hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars).toLowerCase();
  }
  /* Get timestamp with yyyyMMdd'T'HHmmss'Z' format
     @return Time stamp value
     @throws ParseException */
  private String getTimeStampNotUsed() throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
    /* Set the server time as GTM */
    dateFormat.setTimeZone(TimeZone.getTimeZone("GTM"));
    String  localDateTimeString = "20230412T024911Z";
    Date    localDateTime = dateFormat.parse(localDateTimeString);
    return dateFormat.format(localDateTime);       
  }
  /* Get date with yyyyMMdd format
     @return Date
     @throws ParseException */
  private String getDateNotUsed() throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    /* Set the server time as GTM */
    dateFormat.setTimeZone(TimeZone.getTimeZone("GTM"));
    String  localDateString = "20230412";
    Date    localDate = dateFormat.parse(localDateString);
    return dateFormat.format(localDate);
  }
  /* Encode string value
     @param param String value that need to be encode.
     @return encoded string */
  private String encodeParameter(String param) throws Exception {
    try {
      return URLEncoder.encode(param, "UTF-8");
    } 
    catch (UnsupportedEncodingException e) {
      throw new Exception("Character encoding is not supported" + e);
    }
  }
  public static class Builder {
    private String accessKeyID;
    private String secretAccessKey;
    private String regionName;
    private String serviceName;
    private String httpMethodName;
    private String canonicalUri;
    private LinkedHashMap<String, String> queryParameters;
    private LinkedHashMap<String, String> awsHeaders;
    private String payload;
    private String xAmzTarget;
    private String contentType;
    private String hostName;
    private String dateTime;
    public Builder(String accessKeyID, String secretAccessKey) {
      this.accessKeyID = accessKeyID;
      this.secretAccessKey = secretAccessKey;
    }
    public Builder regionName(String regionName) {
      this.regionName = regionName;
      return this;
    }
    public Builder serviceName(String serviceName) {
      this.serviceName = serviceName;
      return this;
    }
    public Builder httpMethodName(String httpMethodName) {
      this.httpMethodName = httpMethodName;
      return this;
    }
    public Builder canonicalUri(String canonicalUri) {
      this.canonicalUri = canonicalUri;
      return this;
    }
    public Builder queryParameters(LinkedHashMap<String, String> queryParameters) {
      this.queryParameters = queryParameters;
      return this;
    }
    public Builder awsHeaders(LinkedHashMap<String, String> awsHeaders) {
      this.awsHeaders = awsHeaders;
      return this;
    }
    public Builder payload(String payload) {
      this.payload = payload;
      return this;
    }
    public Builder xAmzTarget(String xAmzTarget) {
      this.xAmzTarget = xAmzTarget;
      return this;
    }
    public Builder contentType(String contentType) {
      this.contentType = contentType;
      return this;
    }
    public Builder hostName(String hostName) {
      this.hostName = hostName;
      return this;
    }   
    public Builder dateTime(String dateTime) {
      this.dateTime = dateTime;
      return this;
    }        
    public HDLmSignature build() {
      return new HDLmSignature(this);
    }
  }
}
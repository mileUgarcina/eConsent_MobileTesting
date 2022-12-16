package testing.appium.runner.databaseSiding;

import com.google.gson.JsonObject;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;
import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.TCLogger.LoggerStep_Failed;
import static testing.appium.runner.propertyFile.DataProvider.eConsentAPI_Data.AUTH_SERVICE_TOKEN;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;


public class eConsentAPI {

    // This function should be made a bit more generic. It should be refactored in a way to serve different HTTP requests as HMAC is not working entirely the same for all.
    public static String HttpSignature(String host, String contentType, String contentLength, String xContentSha, String secret, String time) throws NoSuchAlgorithmException, InvalidKeyException {

        StringBuilder stringifyHeaderKeys = new StringBuilder();

        // It is important da use LinkedHashMap class because it keeps the order when we manipulate with its instance
        Map item = new LinkedHashMap();

        item.put("host", host);
        item.put("date", time);
        item.put("content-type", contentType);
        item.put("content-length", contentLength);
        item.put("x-content-sha256", xContentSha);

        Object[] results = item.keySet().toArray();

        for (int i = 0; i < results.length; i++) {
            stringifyHeaderKeys.append(results[i]).append(": ").append(item.get(results[i]));
            if(i < results.length - 1) {
                stringifyHeaderKeys.append("\n");
            }
        }

        final byte[] byteKey = secret.getBytes(StandardCharsets.UTF_8);
        Mac sha512_HMAC = Mac.getInstance("HmacSHA512");
        SecretKeySpec secret_key = new SecretKeySpec(byteKey, "HmacSHA512");
        sha512_HMAC.init(secret_key);
        byte[] macData = sha512_HMAC.doFinal(stringifyHeaderKeys.toString().getBytes(StandardCharsets.UTF_8));
        String hmac =  Base64.getEncoder().encodeToString(macData);


        return "Signature keyId=\"participant.auth@florencehc.com\",algorithm=\"hmac-sha512\",headers=\"host date content-type content-length x-content-sha256\",signature=\"" + hmac + "\"";
    }

    public static class Sha256AndContentLength {
        String xContentSha256, contentLength;

        Sha256AndContentLength(JsonObject payload) throws DecoderException {
            String payloadAsString = payload.toString();

            Charset charset = StandardCharsets.UTF_8;

            byte[] payloadByteArray = payloadAsString.getBytes(charset);
            String sha256String = DigestUtils.sha256Hex(payloadByteArray);

            byte[] sha256Hex = Hex.decodeHex(sha256String.toCharArray());
            xContentSha256 = encodeBase64String(sha256Hex);

            contentLength  = String.valueOf(payloadAsString.length());
        }
    }
    public static void seedData_API() throws DecoderException, NoSuchAlgorithmException, InvalidKeyException {

        final String appUrl = appEnvironment();
        final String contentType = "application/json";
        final String host = "docker.for.mac.localhost:7070";
        final String accept = "application/json, text/plain, */*";

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss Z zzzz", Locale.US);
        final String time = dateFormat.format(new Date());

        // It is important da use JsonObject class because it keeps the order when we manipulate with its instance
        JsonObject obj = new JsonObject();

        obj.addProperty("email", "dsadsasdasd@tttt.ca");
        obj.addProperty("invitingSystem", "econsent");
        obj.addProperty("invitingUserId", "632871400ada1c0061ded2f6");
        obj.addProperty("invitingOrganizationId", "638360d124e683008d92094d");
        obj.addProperty("siteName", "JSD Partizan");
        obj.addProperty("inviteMethod", "email");
        obj.addProperty("invitingUserType", "clinical");
        obj.addProperty("studySitePhoneNumber", "1234567890");
        obj.addProperty("language", "en");


        Sha256AndContentLength sha256AndContentLength = new Sha256AndContentLength(obj);

        String signedHeaders = HttpSignature(host, contentType, sha256AndContentLength.contentLength, sha256AndContentLength.xContentSha256, AUTH_SERVICE_TOKEN, time);

        try {
            HttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.STANDARD).build())
                    .build();

            HttpPost httpPost = new HttpPost(appUrl + "/api/v1/invites");

            StringEntity requestEntity = new StringEntity(String.valueOf(obj.toString()));

            httpPost.setEntity(requestEntity);

            httpPost.setHeader("accept", accept);
            httpPost.setHeader("content-type", contentType);
            httpPost.setHeader("date", time);
            httpPost.setHeader("x-content-sha256", sha256AndContentLength.xContentSha256);
            httpPost.setHeader("host", host);

            httpPost.setHeader("Authorization", signedHeaders);

            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            String statusString = Integer.toString(status);

            HttpEntity responseEntity = response.getEntity();
            InputStream inputStream = responseEntity.getContent();
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(inputStream, "UTF-8"));

            if (statusString.equals("200")) {
                LoggerInformation("eConsent POST API status Code: " + statusString);
            } else {
                LoggerInformation("eConsent POST API Status Code: " + statusString);
                LoggerInformation("eConsent POST API Response Body: " + jsonObject);
            }
        } catch (Exception ex) {
            LoggerStep_Failed("eConsent POST API Field: ", ex.getMessage(), true);
        }

    }



}
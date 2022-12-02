package testing.appium.runner.databaseSiding;

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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.TCLogger.LoggerStep_Failed;
import static testing.appium.runner.propertyFile.DataProvider.eConsentAPI_Data.AUTH_SERVICE_KEY_ID;
import static testing.appium.runner.propertyFile.DataProvider.eConsentAPI_Data.AUTH_SERVICE_TOKEN;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;


public class eConsentAPI {




    public static String SHAHashing(String algorithm, String inputString) {

        String result = null;
        try {
            byte[] byteArray = inputString.getBytes(StandardCharsets.UTF_8);
            byte[] decodedHex = new byte[0];

            if (algorithm.equals("sha256Hex")) {
                String sha256String = DigestUtils.sha256Hex(byteArray);
                decodedHex = org.apache.commons.codec.binary.Hex.decodeHex(sha256String.toCharArray());
            } else if (algorithm.equals("sha512Hex")) {
                String sha512String = DigestUtils.sha512Hex(byteArray);
                decodedHex = org.apache.commons.codec.binary.Hex.decodeHex(sha512String.toCharArray());
            }
            result = Base64.encodeBase64String(decodedHex);
        } catch (DecoderException ex) {
            LoggerStep_Failed("Unable to Hash String: " + inputString + " ", ex.getMessage(), true);
        }
        return result;
    }



    public static String httpSignature(String algorithm, String key, String content_length, String x_content_sha256){

        String httpSignature  = null;
        try {
            final String signingString = "content-type:application/json, text/plain, */*" + " content-length:" + content_length + " x-content-sha256:" + x_content_sha256;
            LoggerInformation("Headers To Sign: " + signingString);

            final Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm));
            final byte[] signedBytes = mac.doFinal(signingString.getBytes(StandardCharsets.UTF_8));

            httpSignature = SHAHashing("sha512Hex", String.valueOf(signedBytes));
            LoggerInformation("httpSignature: " + httpSignature);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
        return httpSignature;
    }




    public static void seedData_API()  {

        final String appUrl = appEnvironment();
        java.util.Date date = new java.util.Date();

        JSONObject obj = new JSONObject();
        obj.put("email", "okok@kokok.ca");
        obj.put("invitingSystem", "econsent");
        obj.put("invitingUserId", "632871400ada1c0061ded2f6");
        obj.put("invitingOrganizationId", "635a81c6dbd660009ecbace9");
        obj.put("siteName", "JSD PARTIZAN");
        obj.put("inviteMethod", "email");
        obj.put("invitingUserType", "clinical");
        obj.put("language", "en");
        LoggerInformation("Request body: " + obj);

        String content_length = String.valueOf(obj.toString().length());
        LoggerInformation("Content-length: " + content_length);

        String x_content_sha256 = SHAHashing("sha256Hex", String.valueOf(obj.toString()));
        LoggerInformation("x-content-sha256: " + x_content_sha256);

        String nikola = "{\"email\":\"okok@kokok.ca\",\"invitingSystem\":\"econsent\",\"invitingUserId\":\"632871400ada1c0061ded2f6\",\"invitingOrganizationId\":\"635a81c6dbd660009ecbace9\",\"siteName\":\"JSD PARTIZAN\",\"inviteMethod\":\"email\",\"invitingUserType\":\"clinical\",\"language\":\"en\"}";
        String nikola_x_content_sha256 = SHAHashing("sha256Hex", nikola);
        LoggerInformation("x-nikola_x_content_sha256-sha256: " + nikola_x_content_sha256);

        try {
            HttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.STANDARD).build())
                    .build();
            HttpPost httpPost;
            httpPost = new HttpPost(appUrl + "/api/v1/invites");

            StringEntity requestEntity = new StringEntity(String.valueOf(obj.toString()));
            httpPost.setEntity(requestEntity);
            httpPost.setHeader("accept", "application/json, text/plain, */*");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("date", String.valueOf(date));
//            httpPost.setHeader("Content-length", objAsBytes_length); //HttpClient automatically generates Content-Length and Transfer-Encoding header values based on properties of the enclosed message entity and the actual protocol settings.
            httpPost.setHeader("x-content-sha256", nikola_x_content_sha256);

            String headersToSign = "Signature keyId=\"" + AUTH_SERVICE_KEY_ID + "\",algorithm=\"hmac-sha512\",headers=\"host date content-type content-length x-content-sha256\",signature=\"" + httpSignature("sha512Hex", AUTH_SERVICE_KEY_ID, content_length, nikola_x_content_sha256) + "\"";
            LoggerInformation("authorization: " + headersToSign);


            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            String statusString = Integer.toString(status);

            HttpEntity responseEntity = response.getEntity();
            InputStream inputStream = responseEntity.getContent();
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(inputStream, "UTF-8"));

            if (statusString.equals("200")) {
                LoggerInformation("eConsent POST API status Code: " + statusString);
//                String id = jsonObject.get("id").toString();
//                LoggerInformation("Test Case Result ID: " + id);
            } else {
                LoggerInformation("eConsent POST API Status Code: " + statusString);
                LoggerInformation("eConsent POST API Response Body: " + jsonObject);
            }
        } catch (Exception ex) {
            LoggerStep_Failed("eConsent POST API Field: ", ex.getMessage(), true);
        }

    }



}


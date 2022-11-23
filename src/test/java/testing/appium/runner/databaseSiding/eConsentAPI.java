package testing.appium.runner.databaseSiding;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.TCLogger.LoggerStep_Failed;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;


public class eConsentAPI {

    public static void seedData_API(){

        final String appUrl = appEnvironment();
        String payload = "{\"email\":\"okok@kokok.ca\",\"invitingSystem\":\"econsent\",\"invitingUserId\":\"632871400ada1c0061ded2f6\",\"invitingOrganizationId\":\"635a81c6dbd660009ecbace9\",\"siteName\":\"JSDPARTIZAN\",\"inviteMethod\":\"email\",\"invitingUserType\":\"clinical\",\"language\":\"en\"}";
//        LoggerInformation("resultPayload: " + resultPayload);
        String headersToSign = "Signature keyId=\"participant.auth@florencehc.com\",algorithm=\"hmac-sha512\",headers=\"host date content-type content-length x-content-sha256\",signature=\"NTAzYWYzYmRhNjAxODhlNjgwOWEwNTNmMzA2NzkyMzJkNzdmNzBlNGM1ZjVkNWUwNTYxYTAxNDQ3YzFiMWY3Ng==\"";
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
        System.out.println("obj: " + obj);

        String objAsBytes_length = String.valueOf(obj.toString().length());
        System.out.println("obj_length: " + objAsBytes_length);

        byte[] obj_Bytes = payload.getBytes(StandardCharsets.UTF_8);
        String obj_Bytes_sha256Hex = new DigestUtils("SHA-256").digestAsHex(obj_Bytes);
        System.out.println("obj_Bytes_sha256Hex: " + obj_Bytes_sha256Hex);
        byte[] obj_Bytes_sha256Hex_Bytes = obj_Bytes_sha256Hex.getBytes(StandardCharsets.UTF_8);
        String obj_Bytes_sha256Hex_Bytes_Base64 = Base64.getEncoder().encodeToString(obj_Bytes_sha256Hex_Bytes);
        System.out.println("obj_Bytes_sha256Hex_Bytes_Base64: " + obj_Bytes_sha256Hex_Bytes_Base64);

        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost;
            httpPost = new HttpPost(appUrl + "/api/v1/invites");

            StringEntity requestEntity = new StringEntity(String.valueOf(obj.toString()));
            httpPost.setEntity(requestEntity);
            httpPost.setHeader("accept", "application/json, text/plain, */*");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("date", String.valueOf(date));
//            httpPost.setHeader("Content-length", objAsBytes_length); //HttpClient automatically generates Content-Length and Transfer-Encoding header values based on properties of the enclosed message entity and the actual protocol settings.
            httpPost.setHeader("x-content-sha256", obj_Bytes_sha256Hex_Bytes_Base64);
            httpPost.setHeader("authorization", headersToSign);
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


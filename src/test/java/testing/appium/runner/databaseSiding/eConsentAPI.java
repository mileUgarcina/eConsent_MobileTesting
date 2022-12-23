package testing.appium.runner.databaseSiding;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import org.json.simple.parser.ParseException;
import testing.appium.helpers.Utils;

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
import static testing.appium.helpers.Utils.cutString;
import static testing.appium.runner.propertyFile.DataProvider.PLATFORM_PARAMETER;
import static testing.appium.runner.propertyFile.DataProvider.eConsentAPI_Data.AUTH_SERVICE_KEY_ID;
import static testing.appium.runner.propertyFile.DataProvider.eConsentAPI_Data.AUTH_SERVICE_TOKEN;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.*;


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

        return "Signature keyId=\"" + AUTH_SERVICE_KEY_ID + "\",algorithm=\"hmac-sha512\",headers=\"host date content-type content-length x-content-sha256\",signature=\"" + hmac + "\"";
    }

    public static class Sha256AndContentLength {
        String xContentSha256, contentLength;

        Sha256AndContentLength(JsonObject payload) {

            try {
                String payloadAsString = payload.toString();
                Charset charset = StandardCharsets.UTF_8;

                byte[] payloadByteArray = payloadAsString.getBytes(charset);
                String sha256String = DigestUtils.sha256Hex(payloadByteArray);

                byte[] sha256Hex = Hex.decodeHex(sha256String.toCharArray());

                xContentSha256 = encodeBase64String(sha256Hex);
                contentLength  = String.valueOf(payloadAsString.length());
            } catch (DecoderException ex) {
                LoggerStep_Failed("Sha256AndContentLength Field: ", ex.getMessage(), true);
            }
        }
    }

    public static void seedData_API(String [] createUser, boolean activatedAccount, boolean manuallyCreated, boolean additionalAccount, boolean activatedAccount_additional, String additionalSignerType, boolean isEnabled){

        final String appUrl = appEnvironment();
        final String contentType = "application/json";
        final String host = "docker.for.mac.localhost:7070";
        final String accept = "application/json, text/plain, */*";

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss Z zzzz", Locale.US);
        final String time = dateFormat.format(new Date());

        // It is important da use JsonObject class because it keeps the order when we manipulate with its instance

//        JsonObject obj = new JsonObject();
//        obj.addProperty("email", "dsadsasdasd@tttt.ca");
//        obj.addProperty("invitingSystem", "econsent");
//        obj.addProperty("invitingUserId", "632871400ada1c0061ded2f6");
//        obj.addProperty("invitingOrganizationId", "638360d124e683008d92094d");
//        obj.addProperty("siteName", "JSD Partizan");
//        obj.addProperty("inviteMethod", "email");
//        obj.addProperty("invitingUserType", "clinical");
//        obj.addProperty("studySitePhoneNumber", "1234567890");
//        obj.addProperty("language", "en");

            try {
                JsonObject obj = jsonPayload(createUser, activatedAccount, manuallyCreated, additionalAccount, activatedAccount_additional, additionalSignerType, isEnabled);
                System.out.println("jsonPayload: " + obj);

                Sha256AndContentLength sha256AndContentLength = new Sha256AndContentLength(obj);
                String signedHeaders = HttpSignature(host, contentType, sha256AndContentLength.contentLength, sha256AndContentLength.xContentSha256, AUTH_SERVICE_TOKEN, time);

                HttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()).build();

                HttpPost httpPost = new HttpPost(appUrl + INVITES_API);
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
            } catch (NoSuchAlgorithmException | UnsupportedOperationException | ParseException | IOException | InvalidKeyException ex) {
                LoggerStep_Failed("eConsent POST API Field: ", ex.toString(), true);
            }
    }




    public static JsonObject studies(String title_en, String title_es, String status, String principalInvestigator, String sponsor, String sponsorType, String languages){

        JsonObject studies = new JsonObject();

        JsonArray titleArray = new JsonArray();
        JsonObject title = new JsonObject();

        title.addProperty("en", title_en);
        title.addProperty("es?", title_es);
        titleArray.add(title);
        studies.add("title", titleArray);

        studies.addProperty("status", status);
        studies.addProperty("principalInvestigator", principalInvestigator);
        studies.addProperty("sponsor", sponsor);
        studies.addProperty("sponsorType", sponsorType);
        studies.addProperty("languages", languages);

    return studies;
    }

    public static JsonElement participants(String firstName, String middleName, String lastName, String email, String password, boolean activatedAccount, boolean manuallyCreated, String language,
                                           boolean additionalSigners_, String firstName_additional, String middleName_additional, String lastName_additional, String email_additional, String password_additional, boolean activatedAccount_additional, String language_additional, String  additionalSignerType, boolean isEnabled_additional){

        JsonObject participants = new JsonObject();
        participants.addProperty("firstName", firstName);
        participants.addProperty("middleName", middleName);
        participants.addProperty("lastName", lastName);
        participants.addProperty("email", email);
        participants.addProperty("password", password);
        participants.addProperty("activatedAccount", activatedAccount);
        participants.addProperty("manuallyCreated", manuallyCreated);
        participants.addProperty("language", language);

        JsonObject additionalSigners = new JsonObject();
        JsonArray additionalSignersArray = new JsonArray();
        if (additionalSigners_) {
            additionalSigners.addProperty("firstName", firstName_additional);
            additionalSigners.addProperty("middleName", middleName_additional);
            additionalSigners.addProperty("lastName", lastName_additional);
            additionalSigners.addProperty("email", email_additional);
            additionalSigners.addProperty("password", password_additional);
            additionalSigners.addProperty("activatedAccount", activatedAccount_additional);
            additionalSigners.addProperty("language", language_additional);
            additionalSigners.addProperty("additionalSignerType", additionalSignerType);
            additionalSigners.addProperty("isEnabled", isEnabled_additional);
        }
        additionalSignersArray.add(additionalSigners);
        participants.add("additionalSigners", additionalSignersArray);


        return participants;
    }
    public static JsonObject jsonPayload(String [] createUser, boolean activatedAccount, boolean manuallyCreated, boolean additionalSigners_, boolean activatedAccount_additional, String additionalSignerType_additional, boolean isEnabled_additional){

        JsonObject jsonPayload = new JsonObject();

        jsonPayload.addProperty("siteName", createUser[0]);
        jsonPayload.add("studies", studies(createUser[1], createUser[2], createUser[3], createUser[4], createUser[5], createUser[6], createUser[7]));
        jsonPayload.add("participants", participants(createUser[8], createUser[9], createUser[10], createUser[11], createUser[12], activatedAccount, manuallyCreated, createUser[13], additionalSigners_, createUser[14], createUser[15], createUser[16], createUser[17], createUser[18], activatedAccount_additional, createUser[19], additionalSignerType_additional, isEnabled_additional));

        return jsonPayload;
    }

    public static String[] createUserValues(String status_, boolean additionalSigner){

        final String rndString = Utils.randomString(5);;
        final String prefix = rndString + "_" + PLATFORM_PARAMETER;
        final String userName_firstPart = cutString(PARTICIPANT_USERNAME, "@", 0);
        final String userName_secondPart = cutString(PARTICIPANT_USERNAME, "@", 1);

//        Studies Values
        String siteName = prefix + "_Site Name";
        String title_en = prefix + "_Study Name_en";
        String title_es = prefix + "_Study Name_es";
        String status = status_;
        String principalInvestigator = prefix + "_Investigator";
        String sponsor = prefix + "_Sponsor";
        String sponsorType = "Industry Sponsored";
        String languages_studies = "en";
//        Participants Values
        String firstName = prefix + "_Name";
        String middleName = prefix + "_Middle";
        String lastName = prefix + "_Last";
        String email_participant = userName_firstPart + "+" + prefix + "_participant" + "@" + userName_secondPart;
        String password_participant = "P0_" + prefix + "*";
        String languages_participant = "en";
//        Additional Signers Values
        String firstName_add = null;
        String middleName_add = null;
        String lastName_add = null;
        String email_add = null;
        String password_add = null;
        String languages_add = null;
        if (additionalSigner) {
            firstName_add = prefix + "_NameAdd";
            middleName_add = prefix + "_MiddleAdd";
            lastName_add = prefix + "_LastAdd";
            email_add = userName_firstPart + "+" + prefix + "_additional" + "@" + userName_secondPart;
            password_add = "A0_" + prefix + "*";
            languages_add = "en";
        }

        String [] arr_values = {
//        Studies Values
                siteName,
                title_en,
                title_es,
                status,
                principalInvestigator,
                sponsor,
                sponsorType,
                languages_studies,
//        Participants Values
                firstName,
                middleName,
                lastName,
                email_participant,
                password_participant,
                languages_participant,
//        Additional Signers Values
                firstName_add,
                middleName_add,
                lastName_add,
                email_add,
                password_add,
                languages_add,
        };

        return arr_values;

    }






}
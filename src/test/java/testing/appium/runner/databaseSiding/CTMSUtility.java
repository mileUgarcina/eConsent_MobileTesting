package testing.appium.runner.databaseSiding;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static testing.appium.runner.propertyFile.DataProvider.eConsentAPI_Data.AUTH_SERVICE_KEY_ID;

public class CTMSUtility {

//    public HttpEntity Body (String uploadFile, String value, String templateValue, String descriptionValue, String document, String templateUpdatedBy, String filename, String tags) {
//
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//
//        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//
//        // Required
//
//        builder.addTextBody("name", value, ContentType.TEXT_PLAIN);
//        builder.addTextBody("templateCreatedBy", templateValue, ContentType.TEXT_PLAIN);
//        builder.addTextBody("description", descriptionValue, ContentType.TEXT_PLAIN);
//
//        // Required when Creating Template, Optional when Updating Template
//
//        if (StringUtils.isNotBlank(uploadFile)) {
//            builder.addBinaryBody("file", new File(uploadFile), ContentType.APPLICATION_OCTET_STREAM, document);
//        }
//
//        // Optional
//
//        if (StringUtils.isNotBlank(templateUpdatedBy)) {
//            builder.addTextBody("templateUpdatedBy", templateUpdatedBy, ContentType.TEXT_PLAIN);
//        }
//        if (StringUtils.isNotBlank(filename)) {
//            builder.addTextBody("filename", filename, ContentType.TEXT_PLAIN);
//        }
//        if (StringUtils.isNotBlank(tags)) {
//            builder.addTextBody("tags", tags, ContentType.APPLICATION_JSON);
//        }
//
//        return builder.build();
//    }

    public String Sha256 (HttpEntity multipart) throws NoSuchAlgorithmException, IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        multipart.writeTo(byteArrayOutputStream);
        byte[] content = byteArrayOutputStream.toByteArray();
        String contentString = Arrays.toString(content);

        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(contentString.getBytes(StandardCharsets.UTF_8)));
    }

    public HttpGet GetRequestHeaders (String requestURL, String host, String teamId, String secret) throws NoSuchAlgorithmException, InvalidKeyException {

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss Z zzzz", Locale.US);
        final String time = dateFormat.format(new Date());
        System.out.println("time: " + time);

        HttpGet getRequest = new HttpGet(requestURL);

        StringBuilder stringifyHeaderKeys = new StringBuilder();

        Map item = new HashMap();


        item.put("accept", "application/json, text/plain, */*");
        item.put("Content-type", "application/json");
        item.put("date", time);
        item.put("x-content-sha256", "eKMJCkP2Egui+xCrPOATggcaL9mDv7Mdo57Hc9I3ET4=");

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

        getRequest.addHeader("host","us-participant-auth.se.qav2.researchbinders.com");
        getRequest.addHeader("date",time);
        getRequest.addHeader("x-fhc-team-id",teamId);
        getRequest.addHeader("Authorization","Signature keyId=\"" + AUTH_SERVICE_KEY_ID + "\",algorithm=\"hmac-sha512\",headers=\"host date content-type content-length x-content-sha256\",signature=\"" + hmac + "\"");
        System.out.println("Authorization: " + "Signature keyId=\"" + AUTH_SERVICE_KEY_ID + "\",algorithm=\"hmac-sha512\",headers=\"host date content-type content-length x-content-sha256\",signature=\"" + hmac + "\"");



        return getRequest;
    }

    public HttpPatch UpdateRequestHeaders (String requestURL, String sha256hex, String host, String teamId, String secret, HttpEntity multipart) throws NoSuchAlgorithmException, InvalidKeyException {

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss Z zzzz", Locale.US);
        final String time = dateFormat.format(new Date());

        HttpPatch updateFileMethod = new HttpPatch(requestURL);

        StringBuilder stringifyHeaderKeys = new StringBuilder();

        Map item = new HashMap();

        item.put("host", host);
        item.put("date", time);

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

        updateFileMethod.addHeader("host",host);
        updateFileMethod.addHeader("date",time);
        updateFileMethod.addHeader("x-fhc-team-id",teamId);
        updateFileMethod.addHeader("x-content-sha256",sha256hex);
        updateFileMethod.addHeader("Authorization","Signature keyId=\"branislav.djeric@florencehc.com\",algorithm=\"hmac-sha512\",headers=\"date host\",signature=\"" + hmac + "\"");
        updateFileMethod.setEntity(multipart);

        return updateFileMethod;
    }

    public HttpPost PostRequestHeaders (String requestURL, String sha256hex, String host, String teamId, String secret, HttpEntity multipart) throws NoSuchAlgorithmException, InvalidKeyException {

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss Z zzzz", Locale.US);
        final String time = dateFormat.format(new Date());

        HttpPost uploadFileMethod = new HttpPost(requestURL);

        StringBuilder stringifyHeaderKeys = new StringBuilder();

        Map item = new HashMap();

        item.put("host", host);
        item.put("date", time);

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

        uploadFileMethod.addHeader("host",host);
        uploadFileMethod.addHeader("date",time);
        uploadFileMethod.addHeader("x-fhc-team-id",teamId);
        uploadFileMethod.addHeader("x-content-sha256",sha256hex);
        uploadFileMethod.addHeader("Authorization","Signature keyId=\"branislav.djeric@florencehc.com\",algorithm=\"hmac-sha512\",headers=\"date host\",signature=\"" + hmac + "\"");
        uploadFileMethod.setEntity(multipart);

        return uploadFileMethod;
    }

    public HttpDelete DeleteRequestHeaders (String requestURL, String host, String teamId, String secret) throws NoSuchAlgorithmException, InvalidKeyException {

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss Z zzzz", Locale.US);
        final String time = dateFormat.format(new Date());

        HttpDelete deleteRequest = new HttpDelete(requestURL);

        StringBuilder stringifyHeaderKeys = new StringBuilder();

        Map item = new HashMap();

        item.put("host", host);
        item.put("date", time);

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

        deleteRequest.addHeader("host",host);
        deleteRequest.addHeader("date",time);
        deleteRequest.addHeader("x-fhc-team-id",teamId);
        deleteRequest.addHeader("Authorization","Signature keyId=\"branislav.djeric@florencehc.com\",algorithm=\"hmac-sha512\",headers=\"date host\",signature=\"" + hmac + "\"");

        return deleteRequest;
    }

    public String Response (HttpPost uploadFileMethod, HttpGet getRequest, HttpPatch patchRequest, HttpDelete deleteRequest) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()).build();
        HttpResponse response = null;

        if (uploadFileMethod != null) {
            response = httpClient.execute(uploadFileMethod);
        }

        if (getRequest != null) {
            response = httpClient.execute(getRequest);
        }

        if (patchRequest != null) {
            response = httpClient.execute(patchRequest);
        }

        if (deleteRequest != null) {
            response = httpClient.execute(deleteRequest);
        }

        assert response != null;
        HttpEntity responseEntity = response.getEntity();
        InputStream is = responseEntity.getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        is.close();
        return sb.toString().replace(",","\n");
    }

    public String PDFDownload (HttpGet PDFDownload) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()).build();
        HttpResponse response = httpClient.execute(PDFDownload);
        HttpEntity entity = response.getEntity();

        InputStream inputStream = entity.getContent();
        InputStream in = new BufferedInputStream(inputStream);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[131072];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] responsePDF = out.toByteArray();
        FileOutputStream fos = new FileOutputStream("./downloads/template.pdf");
        fos.write(responsePDF);
        fos.close();

        return "Document downloaded";
    }

//    public HttpEntity RunActionGroup (String uploadFile, String value, String templateValue, String descriptionValue, String document, String templateUpdatedBy, String filename, String tags) {
//
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//
//        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//
//        builder.addTextBody("name", value, ContentType.TEXT_PLAIN);
//        builder.addTextBody("templateCreatedBy", templateValue, ContentType.TEXT_PLAIN);
//        builder.addTextBody("description", descriptionValue, ContentType.TEXT_PLAIN);
//
//        if (StringUtils.isNotBlank(uploadFile)) {
//            builder.addBinaryBody("file", new File(uploadFile), ContentType.APPLICATION_OCTET_STREAM, document);
//        }
//
//        if (StringUtils.isNotBlank(templateUpdatedBy)) {
//            builder.addTextBody("templateUpdatedBy", templateUpdatedBy, ContentType.TEXT_PLAIN);
//        }
//        if (StringUtils.isNotBlank(filename)) {
//            builder.addTextBody("filename", filename, ContentType.TEXT_PLAIN);
//        }
//        if (StringUtils.isNotBlank(tags)) {
//            builder.addTextBody("tags", tags, ContentType.APPLICATION_JSON);
//        }
//
//        return builder.build();

    }


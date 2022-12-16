package testing.appium.runner.browserStack;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;
import testing.appium.runner.propertyFile.DataProvider;


import java.io.InputStream;
import java.io.InputStreamReader;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.TCLogger.LoggerStep_Failed;
import static testing.appium.runner.propertyFile.DataProvider.browserStackAuthorisationData.*;


public class BrowserStackAPI {

    public static String app_name;
    public static String app_version;
    public static String app_url;
    public static String uploaded_at;
    public static String app_id;


    /**
     * Get BrowserStack file ID
     */
    public static void getAppStorageFile_BrowserStack() {


        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()) {
            HttpGet httpget = new HttpGet(BROWSERSTACK_GET_APP_ID_URL);
            httpget.setHeader("Accept", "*/*");
            httpget.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("Authorization", "Basic " + BROWSERSTACK_TOKEN);
            httpget.setHeader("Cookie", BROWSERSTACK_TRACKING_ID);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
//            LoggerInformation("responseString: , " + responseString);
            String statusString = String.valueOf(response.getStatusLine().getStatusCode());

            if (statusString.contains("200")) {
//                LoggerInformation("BrowserStack Get App Details Status: " + statusString);
                LoggerInformation("App File ID, Name and Version will be taken from BrowserStack");

                JSONArray items = new JSONArray(responseString);
                org.json.JSONObject jObj = items.getJSONObject(0);

                app_name = jObj.getString("app_name");
//                LoggerInformation("app_name: " + app_name);
                app_version = jObj.getString("app_version");
//                LoggerInformation("app_version: " + app_version);
                app_url = jObj.getString("app_url");
//                LoggerInformation("app_url: " + app_url);
                app_id = jObj.getString("app_id");
//                LoggerInformation("app_id: " + app_id);
                uploaded_at = jObj.getString("uploaded_at");
//                LoggerInformation("uploaded_at: " + uploaded_at);

                LoggerInformation("BrowserStack - " + app_name + ", App File ID: " + app_url);

            } else {
                LoggerInformation("BrowserStack Get App Details Status: " + statusString);
                LoggerInformation("BrowserStack Get App Details Entities: " + responseString);
            }
        } catch (Exception ex) {
            LoggerStep_Failed("Unable to get BrowserStack Get App Details: ", ex.getMessage(), false);
        }

    }


    public static String getTestRunDetails_BrowserStack(String sessionID) {

        String entities;

        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()) {
            HttpGet httpget = new HttpGet(BROWSERSTACK_GET_TEST_RUN_DETAILS + sessionID + ".json");
            httpget.setHeader("Accept", "*/*");
            httpget.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("Authorization", "Basic " + BROWSERSTACK_TOKEN);
            httpget.setHeader("Cookie", BROWSERSTACK_SESSION + "; " + BROWSERSTACK_TRACKING_ID);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
//            LoggerInformation("responseString: " + responseString);

            String statusString = String.valueOf(response.getStatusLine().getStatusCode());
            if (statusString.contains("200")) {
                LoggerInformation("BrowserStack Run Details Status: " + statusString);

                JSONObject items = new JSONObject(responseString);
                org.json.JSONObject jObj = items.getJSONObject("automation_session");

                String name = jObj.getString("name");
                String os = jObj.getString("os");
                String os_version = jObj.getString("os_version");
//                String browser_version = jObj.getString("browser_version");
//                String browser = jObj.getString("browser");
                String device = jObj.getString("device");
                String video_url = jObj.getString("video_url");

                entities = " --- *Test Environment* ---  \\n \\n"
                        + " --- Test Device\\n"
                        + "Test Execution video: [" + "BrowserStack Execution Video" + "|" + video_url + "]" + "\\n"
                        + "Execution name: " + name + "\\n"
                        + "Device name: " + device + "\\n"
                        + "Platform: " + os + "\\n"
                        + "OS version: " + os_version +  "\\n" + "\\n"
                        + " --- Test Application";

                LoggerInformation("entities: " + entities);
                return entities;
            } else {
                LoggerInformation("BrowserStack Run Details Status: " + statusString);
                LoggerInformation("BrowserStack Run Details Entities: " + responseString);
            }
        } catch (Exception ex) {
            LoggerStep_Failed("Unable to get BrowserStack Run Details: ", ex.getMessage(), false);
        }
        return null;
    }


    public static void setTestName_BrowserStack(AppiumDriver<MobileElement> driver, String testName) {

        ((JavascriptExecutor) driver).executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"" +testName +"\" }}");
    }

    public static void setTestStatus_BrowserStack(AppiumDriver<MobileElement> driver, String status, String reason) {

        ((JavascriptExecutor)driver).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"" + status + "\", \"reason\": \"" + reason + "\"}}");

    }
}
package testing.appium.runner.eConsent_HealthCheckResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.TCLogger.LoggerStep_Failed;
import static testing.appium.runner.propertyFile.DataProvider.ENVIRONMENT;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.*;

public class eConsent_HC_API {

    public static String appVersion;
    public static String appRevision;
    public static String appStatus;

    public static void getAppHealthCheckResponse() {


        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()) {
            HttpGet httpget = new HttpGet(appEnvironment() + HEALTH_CHECK_API);
            httpget.setHeader("Accept", "*/*");
            httpget.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpget.setHeader("Connection", "keep-alive");
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
//            LoggerInformation("responseString: , " + responseString);
            String statusString = String.valueOf(response.getStatusLine().getStatusCode());

            if (statusString.contains("200")) {
//                LoggerInformation(DEV_TEAM + " Health Check Status: " + statusString);

                JSONObject items = new JSONObject(responseString);
                org.json.JSONObject result = items.getJSONObject("Result");
                appStatus = items.getString("Status");
                LoggerInformation(DEV_TEAM +   " Participant "  +"\""+ ENVIRONMENT + "\"" + " App Health Check Response: " + appStatus);

                if(!appStatus.equals("OK")){
                    Assert.fail();
                }else{
                    appVersion  = (String) result.get("AppVersion");
//                    LoggerInformation("appVersion: " + appVersion);
                    appRevision = (String) result.get("AppRevision");
//                    LoggerInformation("appRevision: " + appRevision);
                }
            } else {
                LoggerInformation(DEV_TEAM + " Health Check Response Get Details Status: " + statusString);
                LoggerInformation(DEV_TEAM + " Health Check Response Get Details Entities: " + responseString);
                Assert.fail();
            }
        } catch (Exception ex) {
            LoggerStep_Failed("Unable to get " + DEV_TEAM + " Health Check Response: ", ex.getMessage(), true);
        }

    }
}

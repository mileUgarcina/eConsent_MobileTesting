package testing.appium.runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import testing.appium.helpers.TCLogger;

import static testing.appium.helpers.Utils.testReport;
import static testing.appium.helpers.jiraTicket.JiraTicket.*;
import static testing.appium.helpers.testCaseId.TestCaseId.getTestCaseId;
import static testing.appium.runner.browserStack.BrowserStackAPI.*;
import static testing.appium.runner.eConsent_HealthCheckResponse.eConsent_HC_API.*;
import static testing.appium.runner.jiraXrayReporting.JiraXrayAPI.getXRayRunId;
import static testing.appium.runner.jiraXrayReporting.JiraXrayAPI.setTcResult_Xray;
import static testing.appium.runner.propertyFile.DataProvider.ENVIRONMENT;
import static testing.appium.runner.propertyFile.DataProvider.PLATFORM_PARAMETER;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.*;
import static testing.appium.runner.sauceLabs.SauceLabsAPI.entitiesVersion;
import static testing.appium.runner.sauceLabs.SauceLabsAPI.setSauceLabsTestStatus;


public class TestListener implements ITestListener {

    /**
     * Change the color of the log printout
     * Note - Test Rail cannot receive these codes when entering comments in Test Cases
     */
    private static final String reset = TCLogger.ANSI_RESET;
    private static final String red = TCLogger.ANSI_RED;
    private static final String green = TCLogger.ANSI_GREEN;
    private static final String yellow = TCLogger.ANSI_YELLOW;

    /**
     * Invoked each time a test succeeds.
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     * parameter "testStatus" - PASSED
     */
    @Override
    public void onTestSuccess(ITestResult result) {

        String nameTC = result.getName();
        String testCaseID = getTestCaseId(result);
        Reporter.log("--- Test Case - " + nameTC + " -- Finish --> {color:#36b37e}*PASSED*{color} ---\\n", true);
        String appLink = null;
        String deviceParameter = (String) result.getAttribute("deviceParameter");
        AppiumDriver<MobileElement> driver = (AppiumDriver<MobileElement>) result.getAttribute("driver");
        if(deviceParameter.contains("SauceLabs")) {
            setSauceLabsTestStatus("passed", driver);
            appLink =  entitiesVersion;
        }else if(deviceParameter.contains("BrowserStack")){
//            setTestName_BrowserStack(driver, testCaseID + " - "+ nameTC);
            setTestStatus_BrowserStack(driver, "passed", "passed");
            appLink =  app_version;}
        String testRunDetails = (String) result.getAttribute("testRunDetails");
        Reporter.log("\\n" +  testRunDetails + "\\n" + "App Name: " + COMPANY_NAME + " - " + DEV_TEAM + "\\n" + "Environment: " + ENVIRONMENT + "\\n" + "App Link: [" + appEnvironment() + "|" + appEnvironment() + "]" + "\\n" + "App Version: " + appVersion + "\\n" + "App Revision: " + appRevision  + "\\n" + "App Health Check: " + appStatus, true);
        boolean ticketAndroid = getAndroidTicket(result, PLATFORM_PARAMETER, true);
        boolean ticketiOS = getiOSTicket(result, PLATFORM_PARAMETER, true);
        String bugTicketNo = getTicketNumberValue(result, PLATFORM_PARAMETER);
        String testRunId = getXRayRunId(PLATFORM_PARAMETER);
        String getReport = testReport(result);
        setTcResult_Xray(result, PLATFORM_PARAMETER, nameTC, testRunId, testCaseID, "PASSED", getReport, ticketAndroid, ticketiOS, bugTicketNo);
        Reporter.log(green + "------------ >>>[ Test PASSED: " + nameTC + " ]<<< ------------" + reset, true);
    }

    /**
     * Invoked each time a test fails.
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     * parameter "testStatus" - FAILED
     */
    @Override
    public void onTestFailure(ITestResult result) {

        String nameTC = result.getName();
        String testCaseID = getTestCaseId(result);
        Reporter.log("--- Test Case - " + nameTC + " -- Finish --> {color:#FF0000}*FAILED*{color}  ---\\n", true);
        String appLink = null;
        String deviceParameter = (String) result.getAttribute("deviceParameter");
        AppiumDriver<MobileElement> driver = (AppiumDriver<MobileElement>) result.getAttribute("driver");
        if(deviceParameter.contains("SauceLabs")) {
            setSauceLabsTestStatus("failed", driver);
            appLink =  entitiesVersion;
        }else if(deviceParameter.contains("BrowserStack")){
//            setTestName_BrowserStack(driver, testCaseID + " - "+ nameTC);
            setTestStatus_BrowserStack(driver, "failed", "failed");
            appLink =  app_version;}
        String testRunDetails = (String) result.getAttribute("testRunDetails");
        Reporter.log("\\n" +  testRunDetails + "\\n" + "App Name: " + COMPANY_NAME + " - " + DEV_TEAM + "\\n" + "Environment: " + ENVIRONMENT + "\\n" + "App Link: [" + appEnvironment() + "|" + appEnvironment() + "]" + "\\n" + "App Version: " + appVersion + "\\n" + "App Revision: " + appRevision  + "\\n" + "App Health Check: " + appStatus, true);
        boolean ticketAndroid = getAndroidTicket(result, PLATFORM_PARAMETER, false);
        boolean ticketiOS = getiOSTicket(result, PLATFORM_PARAMETER, false);
        String testRunId = getXRayRunId(PLATFORM_PARAMETER);
        String bugTicketNo = getTicketNumberValue(result, PLATFORM_PARAMETER);
        String getReport = testReport(result);
        setTcResult_Xray(result, PLATFORM_PARAMETER, nameTC, testRunId, testCaseID, "FAILED", getReport, ticketAndroid, ticketiOS, bugTicketNo);
        Reporter.log(red + " ------------ >>>[ Test FAILED: " + nameTC + " ]<<< ------------" + reset, true);

    }

    /**
     * Invoked each time a test is skipped.
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     * parameter "testStatus" - SKIPPED
     */
    @Override
    public void onTestSkipped(ITestResult result) {

        String nameTC = result.getName();
        String testCaseID = getTestCaseId(result);
        Reporter.log("--- Test Case - " + nameTC + " -- Finish --> {color:#FF8E00}*SKIPPED*{color} ---\\n", true);
        String appLink = null;
        String deviceParameter = (String) result.getAttribute("deviceParameter");
        AppiumDriver<MobileElement> driver = (AppiumDriver<MobileElement>) result.getAttribute("driver");
        if(deviceParameter.contains("SauceLabs")) {
            setSauceLabsTestStatus("failed", driver);
            appLink =  entitiesVersion;
        }else if(deviceParameter.contains("BrowserStack")){
//            setTestName_BrowserStack(driver, testCaseID + " - "+ nameTC);
            setTestStatus_BrowserStack(driver, "failed", "failed");
            appLink =  app_version;}
        String testRunDetails = (String) result.getAttribute("testRunDetails");
        Reporter.log("\\n" +  testRunDetails + "\\n" + "App Name: " + COMPANY_NAME + " - " + DEV_TEAM + "\\n" + "Environment: " + ENVIRONMENT + "\\n" + "App Link: [" + appEnvironment() + "|" + appEnvironment() + "]" + "\\n" + "App Version: " + appVersion + "\\n" + "App Revision: " + appRevision  + "\\n" + "App Health Check: " + appStatus, true);
        boolean ticketAndroid = getAndroidTicket(result, PLATFORM_PARAMETER, true);
        boolean ticketiOS = getiOSTicket(result, PLATFORM_PARAMETER, true);
        String bugTicketNo = getTicketNumberValue(result, PLATFORM_PARAMETER);
        String testRunId = getXRayRunId(PLATFORM_PARAMETER);
        String getReport = testReport(result);
        setTcResult_Xray(result, PLATFORM_PARAMETER, nameTC, testRunId, testCaseID, "SKIPPED", getReport, ticketAndroid, ticketiOS, bugTicketNo);
        Reporter.log(yellow + "------------ >>>[ Test SKIPPED: " + nameTC + " ]<<< ------------" + reset, true);
    }
}





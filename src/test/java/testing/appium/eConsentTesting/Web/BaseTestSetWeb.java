package testing.appium.eConsentTesting.Web;

import org.testng.Assert;
import testing.appium.pageObjects.eConsentTesting.WebApp.*;
import testing.appium.runner.Init_Android;
import testing.appium.runner.Init_iOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.browserStack.BrowserStackAPI.getTestRunDetails_BrowserStack;
import static testing.appium.runner.eConsent_HealthCheckResponse.eConsent_HC_API.*;
import static testing.appium.runner.jiraXrayReporting.JiraXrayAPI.setTestRun_Xray;
import static testing.appium.runner.propertyFile.DataProvider.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.sauceLabs.SauceLabsAPI.*;


public class BaseTestSetWeb {

    public AppiumDriver<MobileElement> driver;
    public String suiteName;
    String testRunDetails;
    Init_Android initAndroid;
    Init_iOS iniOS;

    public RegistrationPage rp;
    public SignInPage sip;
    public MainPage mp;
    public PrivacyPage pp ;
    public TermsPage tp;
    public ForgotPasswordPage fpp;
    public PrivacySettingsPage psp;
    public ProfileSettingsPage prosp;
    public ResetYourPasswordPage rypp;
    public ViewTasks vt;
    public ViewStudyPage vsp;
    public StudyPage sp;

    @Parameters({"deviceParameter", "browserName"})
    @BeforeSuite(alwaysRun=true,
                 description = "Set capabilities depending on the platformParameter, Initiate driver and Page Objects, Create Xray Test Run")
    public void startSession(String deviceParameter, String browserName, ITestContext context, ITestResult result){

//        eConsent Participant App Health Check Response
        getAppHealthCheckResponse();

//        Get Suite Name from TestNG xml file
        suiteName = context.getCurrentXmlTest().getSuite().getName();
        LoggerInformation("Running Suite: " + suiteName + ", one \"" + ENVIRONMENT + "\" environment, " + "App Version: " + appVersion + ", App Revision: " + appRevision);

//        Set Xray Test Run
        setTestRun_Xray(browserName, PLATFORM_PARAMETER, suiteName);

        initAndroid = new Init_Android();
        iniOS = new Init_iOS();

//        Driver initialization
        if (PLATFORM_PARAMETER.contains("Android")) {
            driver = initAndroid.getDriverAndroid(deviceParameter, suiteName, PLATFORM_PARAMETER, browserName);
        } else if(PLATFORM_PARAMETER.contains("iOS")){
            driver = iniOS.getDriveriOS(deviceParameter, suiteName, PLATFORM_PARAMETER, browserName);
        }else{
            LoggerInformation("Test Parameter: " + PLATFORM_PARAMETER + " not recognized" );
            Assert.fail();
        }

//        Get Details of Sauce Labs/Browser Stack Run - device name, platform name, OS version...
        if (deviceParameter.contains("SauceLabs")) {
            testRunDetails = getTestRunDetails_SauceLabs_RD();
        }else if(deviceParameter.contains("BrowserStack")){
            testRunDetails = getTestRunDetails_BrowserStack(String.valueOf(driver.getSessionId()));
        }

        LoggerAction("Open Browser: " + browserName);
        deleteAllCookies_WebElement(driver);

        pp = new PrivacyPage(driver);
        tp = new TermsPage(driver);
        fpp = new ForgotPasswordPage(driver);
        sip = new SignInPage(driver);
        rp = new RegistrationPage(driver);
        mp = new MainPage(driver);
        psp = new PrivacySettingsPage(driver);
        prosp = new ProfileSettingsPage(driver);
        rypp = new ResetYourPasswordPage(driver);
        vt = new ViewTasks(driver);
        sp = new StudyPage(driver);
        vsp = new ViewStudyPage(driver);
    }


    @Parameters({"browserName", "deviceParameter"})
    @BeforeMethod(alwaysRun=true,
                  description = "Set the Diver attribute for the needs of the result")
    public void beforeMethod(Method method, String browserName, String deviceParameter,  ITestResult result) {

        result.setAttribute("driver", driver);
        result.setAttribute("testRunDetails", testRunDetails);
        result.setAttribute("browserName", browserName);
        result.setAttribute("deviceParameter", deviceParameter);
        result.setAttribute("appUrl", appEnvironment());
    }


    @AfterTest(alwaysRun=true,
               description = "Quit the driver and close the session")
    public void endSession(){

        driver.quit();
        LoggerAction("Driver quit");

//        Thread.sleep(5000);
//        System.out.println("getPageSource: " + driver.getPageSource());
    }

 }


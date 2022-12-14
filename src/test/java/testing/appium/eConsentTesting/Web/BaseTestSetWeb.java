package testing.appium.eConsentTesting.Web;

import org.apache.http.client.methods.HttpGet;
import testing.appium.pageObjects.eConsentTesting.WebApp.*;
import testing.appium.runner.Init_Android;
import testing.appium.runner.Init_iOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import testing.appium.runner.databaseSiding.CTMSUtility;

import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.Utils.deleteAllCookies_WebElement;
import static testing.appium.runner.databaseSiding.eConsentAPI.seedData_API;
import static testing.appium.runner.jiraXrayReporting.JiraXrayAPI.setTcRun_Xray;
import static testing.appium.runner.propertyFile.DataProvider.*;
import static testing.appium.runner.propertyFile.DataProvider.eConsentAPI_Data.AUTH_SERVICE_KEY_ID;
import static testing.appium.runner.propertyFile.DataProvider.eConsentAPI_Data.AUTH_SERVICE_TOKEN;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.sauceLabs.SauceLabsAPI.getTestRunDetailsRD;


public class BaseTestSetWeb {

    public AppiumDriver<MobileElement> driver;
    public String suiteName;
    String sauceLabsTestRunDetails;
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

    @Parameters({"deviceParameter", "platformParameter", "browserName", "crete_Xray_Test_Run"})
    @BeforeSuite(alwaysRun=true,
                 description = "Set capabilities depending on the platformParameter, Initiate driver and Page Objects, Create Xray Test Run")
    public void startSession(String deviceParameter, String platformParameter, String browserName, boolean crete_Xray_Test_Run, ITestContext context, ITestResult result) throws NoSuchAlgorithmException, InvalidKeyException {

        seedData_API();


        String response = String.valueOf(new CTMSUtility().GetRequestHeaders("https://us-participant-auth.se.qav2.researchbinders.com/api/v1/invites",null, null, AUTH_SERVICE_KEY_ID));


        setTcRun_Xray(crete_Xray_Test_Run, platformParameter);

//        Get Suite Name from TestNG xml file
        suiteName = context.getCurrentXmlTest().getSuite().getName();
        LoggerInformation("Running Suite: " + suiteName + ", one \"" + ENVIRONMENT + "\" environment" );

        initAndroid = new Init_Android();
        iniOS = new Init_iOS();

//        Driver initialization
        if (platformParameter.contains("Android")) {
            driver = initAndroid.getDriverAndroid(deviceParameter, suiteName, platformParameter, browserName);
        } else {
            driver = iniOS.getDriveriOS(deviceParameter, suiteName, platformParameter, browserName);
        }

        sauceLabsTestRunDetails = getTestRunDetailsRD();
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



    @Parameters({"browserName", "deviceParameter", "platformParameter"})
    @BeforeMethod(alwaysRun=true,
                  description = "Set the Diver attribute for the needs of the result")
    public void beforeMethod(Method method, String browserName, String deviceParameter, String platformParameter,  ITestResult result) {

        result.setAttribute("driver", driver);
        result.setAttribute("platformParameter", platformParameter);
        result.setAttribute("sauceLabsTestRunDetails", sauceLabsTestRunDetails);
        result.setAttribute("browserName", browserName);
        result.setAttribute("deviceParameter", deviceParameter);
        result.setAttribute("appUrl", appEnvironment());
    }


    @AfterTest(alwaysRun=true,
               description = "Quit the driver and close the session")
    public void endSession(){

        driver.quit();
        LoggerAction("Driver quit");


//        System.out.println("getPageSource" + driver.getPageSource());
    }

 }


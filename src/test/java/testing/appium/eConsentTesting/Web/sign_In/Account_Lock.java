package testing.appium.eConsentTesting.Web.sign_In;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.SIGN_IN_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.propertyFile.DataProvider.tcData.*;


@Listeners(TestListener.class)
public class Account_Lock extends BaseTestSetWeb {

    static private final String appUrl = appEnvironment();
    private String username;
    private String password;
    private String wrongPassword = "Pass" + randomString(4) + "1*";
    private String testCaseName;
    private String originalWindow;



    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(){
        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
    }


    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(){
        refreshPage_WebElement(driver);
    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2044")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Participant account locked temporarily",
            description = "Test checks for incorrect password entry five times",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Participant_account_locked_temporarily(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO waiting for TC Data
        username = username(user, platformParameter);
        password = password(user, platformParameter);
        ;
        for (int i = 0; i < 5; i++) {
            LoggerInformation("Wrong Password attempt number: " + i);
            refreshPage_WebElement(driver);
            sip.logInProcedure(username, wrongPassword);
        }
        sip.assert_toastMsg(errorMsg_lockedTemporarily);
//        TODO Email Verification
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2760")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Participant account locked permanently",
            description = "Test checks for incorrect password entry ten times",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Participant_account_locked_permanently(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO waiting for TC Data
        username = username(user, platformParameter);
        password = password(user, platformParameter);

        for (int i = 0; i < 10; i++) {
            LoggerInformation("Wrong Password attempt number: " + i);
            refreshPage_WebElement(driver);
            sip.logInProcedure(username, wrongPassword);
        }
        sip.assert_toastMsg(errorMsg_lockedPermanently);
//        TODO Email Verification
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4194")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Participant signs in after permanent lockout",
            description = "Test checks is it possible to sign in to the application after permanently locking the account",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Participant_signs_in_after_permanent_lockout(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO waiting for TC Data
        username = username(user, platformParameter);
        password = password(user, platformParameter);

        sip.logInProcedure(username, password);
        sip.assert_toastMsg(errorMsg_recoverAccount);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4195")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Additional signer account locked temporarily",
            description = "Test checks for incorrect password entry five times for Additional signer",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Additional_signer_account_locked_temporarily(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO waiting for TC Data
        String larUsername = "mobile.automation+lar_android@florencehc.com";
        String larPassword = "Password123*";
        ;
        for (int i = 0; i < 5; i++) {
            LoggerInformation("Wrong Password attempt number: " + i);
            refreshPage_WebElement(driver);
            sip.logInProcedure(larUsername, wrongPassword);
        }
        sip.assert_toastMsg(errorMsg_lockedTemporarily);
//        TODO Email Verification
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4196")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Additional signer account locked permanently",
            description = "Test checks for incorrect password entry ten times for Additional signer",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Additional_signer_account_locked_permanently(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO waiting for TC Data
        String larUsername = "mobile.automation+lar_android@florencehc.com";

        for (int i = 0; i < 10; i++) {
            LoggerInformation("Wrong Password attempt number: " + i);
            refreshPage_WebElement(driver);
            sip.logInProcedure(larUsername, wrongPassword);
        }
        sip.assert_toastMsg(errorMsg_lockedPermanently);
//        TODO Email Verification
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4197")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Additional signer signs in after permanent lockout",
            description = "Test checks is it possible to sign in for Additional signer to the application after permanently locking the account",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Additional_signer_signs_in_after_permanent_lockout(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO waiting for TC Data
        String larUsername = "mobile.automation+lar_android@florencehc.com";
        String larPassword = "Password1234*";

        sip.logInProcedure(larUsername, larPassword);
        sip.assert_toastMsg(errorMsg_recoverAccount);
    }




}

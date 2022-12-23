package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.Utils.*;


public class ResetYourPasswordPage {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 15;

    private final By pageTitle = By.xpath("//h1[normalize-space()='Reset Your Password']");
    private final By toastContainer = By.xpath("//div[@id='toast-container']");
    private final By emailInputField = By.id("email-input");
    private final By warningMessage = By.xpath("/html/body/app-root/app-auth-layout/div/div[1]/app-sign-in/div/form/div[1]/div"); // Email: 1. Required;  2. Incorrect email format; Password: 1. Doesn't meet all requirements
    private final By passwordInputField = By.id("password-input");
    private final By rePasswordInputField = By.id("password-confirmation-input");
    private final By rePassword_warningMessage = By.xpath("//div[normalize-space()='Password confirmation is required and must match the password. Both must be valid.']"); // Password confirmation is required and must match the password. Both must be valid.
    private final By resetPasswordBtn = By.cssSelector("button[type='submit']");
    private final By sigInBtn = By.cssSelector("#sign-up-pw-btn");
    private final By privacyPolicyBtn = By.xpath("//a[normalize-space()='Privacy Policy']");
    private final By termsAndConditionsBtn = By.xpath("//a[normalize-space()='Terms and Conditions']");



    public ResetYourPasswordPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void loadWebPage(String appUrl) {
        try {
            openWebPage_WebElement(driver,appUrl);
            LoggerWaiting("For the Application to Load");
            waitForElement(driver,waitInterval).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pageTitle));
            LoggerInformation("Application is Running");
        } catch (Exception ex) {
            LoggerStep_Failed("Application is Not Running: "  , ex.getMessage(), false);
            deleteAllCookies_WebElement(driver);
            refreshPage_WebElement(driver);
            openWebPage_WebElement(driver,appUrl);
            try {
                LoggerWaiting("For the Application to Load");
                waitForElement(driver,waitInterval).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pageTitle));
                LoggerInformation("Application is Running");
            } catch (Exception e) {
                LoggerStep_Failed("Application is Not Running: "  , ex.getMessage(), true);
            }
        }
    }


    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageTitle, waitInterval, "Sign in Page Title");
    }

    public void assert_toastContainer(){
        assertElementPresence_WebElement(driver, toastContainer, waitInterval, "Error Message");
    }

    public void assert_toastMsg(String errorMessage){
        assert_getText_WebElement(driver, toastContainer, waitInterval, "Toast Message", errorMessage);
    }

   public void insert_emailInputField(String text) {
       sendKey_toWebElement(driver, emailInputField, waitInterval,"Username Input Field", text, false);
    }

    public void assert_emailFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, emailInputField, waitInterval, "Email Input field", cssColor, color);
    }

    public void assert_usernameWarningMsg(String warningMsg){
        assert_getText_WebElement(driver, warningMessage, waitInterval, "Username Error Message", warningMsg);
    }

    public void clear_emailInputField() {
        clearInputField_toWebElement(driver, emailInputField, waitInterval, "Username Input Field");
//        clearInputField_doubleClick_toWebElement(driver, emailInputField, waitInterval, "Username Input Field");
    }

    public void clickOn_emailInputField(){
        click_onWebElement_waitClickable(driver, emailInputField, waitInterval,"Email Input Field");
    }

    public void insert_passwordInputField(String text) {
        sendKey_toWebElement(driver, passwordInputField, waitInterval,"Password Input Field", text, false);
    }

    public void clickOn_passwordInputField(){
        click_onWebElement_waitClickable(driver, passwordInputField, waitInterval,"Password Input Field");
    }

    public void clear_passwordInputField() {
        clearInputField_toWebElement(driver, passwordInputField, waitInterval, "Password Input Field");
    }

    public void assert_passwordFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, passwordInputField, waitInterval, "Password Input field", cssColor, color);
    }

    public void insert_rePasswordInputField(String text) {
        sendKey_toWebElement(driver, rePasswordInputField, waitInterval,"Retype Password Input Field", text, false);
    }

    public void clickOn_rePasswordInputField(){
        click_onWebElement_waitClickable(driver, rePasswordInputField, waitInterval,"Retype Password Input Field");
    }

    public void clear_rePasswordInputField() {
        clearInputField_toWebElement(driver, rePasswordInputField, waitInterval, "Retype Password Input Field");
    }

    public void assert_rePasswordFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, rePasswordInputField, waitInterval, "Retype Password Input field", cssColor, color);
    }

    public void assert_rePasswordWarningMsg(String warningMsg){
        assert_getText_WebElement(driver, rePassword_warningMessage, waitInterval, "Retype password Error Message", warningMsg);
    }


    public void clickOn_resetPasswordBtn(){
        click_onWebElement_waitClickable(driver, resetPasswordBtn, waitInterval,"Reset Password Button");
    }

    public void clickOn_privacyPolicyBtn(){
        click_onWebElement_waitClickable(driver, privacyPolicyBtn, waitInterval,"Privacy Policy");
    }

    public void clickOn_termsAndConditionsBtn(){
        click_onWebElement_waitClickable(driver, termsAndConditionsBtn, waitInterval,"Terms and Conditions");
    }

    public void clickOn_pageTitle(){
        click_onWebElement_waitClickable(driver, pageTitle, waitInterval,"Page Title");
    }

    public void assert_passwordInputField(){

        if(assertElementType_WebElement(driver, passwordInputField, waitInterval, "Password Input Field", "type", "text")){
            LoggerAssert_Passed("Verification successful. Password is Visible");
        }else{
            LoggerAssert_Failed("Verification unsuccessfully. Password is not Visible");
            Assert.fail();
        }
    }




}



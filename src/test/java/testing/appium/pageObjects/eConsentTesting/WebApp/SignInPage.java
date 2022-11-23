package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.TCLogger.LoggerStep_Failed;
import static testing.appium.helpers.Utils.*;


public class SignInPage {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 15;

    private final By pageTitle = By.xpath("(//img[@title='Florence eConsent'])[2]");
    private final By welcomeMsg = By.xpath("(//p[@class='u-text-align-center'])[1]");

    private final By toastContainer = By.xpath("//div[@id='toast-container']");
    private final By userTitle = By.xpath("//b[@class='u-display-block u-text-overflow-ellipsis']");
    private final By differentUserBtn = By.xpath("//a[normalize-space()='Sign in as a different user']");
    private final By emailInputField = By.id("email-input");
    private final By warningMessage = By.xpath("/html/body/app-root/app-auth-layout/div/div[1]/app-sign-in/div/form/div[1]/div"); // Email: 1. Required;  2. Incorrect email format; Password: 1. Doesn't meet all requirements


    private final By passwordInputField = By.id("password-input");
    private final By showPasswordChkBox = By.xpath("//div[@class='c-checkbox--container']");
    private final By nextBtn = By.xpath("//button[contains(text(), 'Next')]");
    private final By signInBtn = By.xpath("//button[contains(text(), 'Sign in')]");

    private final By forgotPasswordBtn = By.xpath("//a[normalize-space()='Forgot password?']");

    private final By privacyPolicyBtn = By.xpath("//a[normalize-space()='Privacy Policy']");
    private final By termsAndConditionsBtn = By.xpath("//a[normalize-space()='Terms and Conditions']");
    private final By versionTitle = By.xpath("/html/body/app-root/app-sign-in/div/div[2]/auth-footer/div/text()");



    public SignInPage(AppiumDriver<MobileElement> driver) {
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

    public void logInProcedure(String username, String password){
        insert_emailInputField(username);
        insert_passwordInputField(password);
        clickOn_signInBtn();
    }


    public void assert_usernameFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, emailInputField, waitInterval, "Username Input field", cssColor, color);
    }

    public void assert_passwordFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, passwordInputField, waitInterval, "Password Input field", cssColor, color);
    }

    public void assert_usernameWarningMsg(String warningMsg){
        assert_getText_WebElement(driver, warningMessage, waitInterval, "Username Error Message", warningMsg);
    }

    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageTitle, 30, "Sign in Page Title");
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

    public void clear_emailInputField() {
        clearInputField_toWebElement(driver, emailInputField, waitInterval, "Username Input Field");
    }

    public void insert_passwordInputField(String text) {
        sendKey_toWebElement(driver, passwordInputField, waitInterval,"Password Input Field", text, false);
    }

    public void clickOn_emailInputField(){
        click_onWebElement_waitClickable(driver, emailInputField, waitInterval,"Email Input Field");
    }


    public void clickOn_passwordInputField(){
        click_onWebElement_waitClickable(driver, passwordInputField, waitInterval,"Password Input Field");
    }

    public void clear_passwordInputField() {
        clearInputField_toWebElement(driver, passwordInputField, waitInterval, "Password Input Field");
    }

    public void clickOn_showPasswordChkBox(){
        click_onWebElement_waitClickable(driver, showPasswordChkBox, waitInterval,"Show Password Checkbox");
    }

    public void clickOn_signInBtn(){
        click_onWebElement_waitClickable(driver, signInBtn, waitInterval,"Sign in Button");
    }

    public void assert_signInBtn_isDisable(){
        assertElementIsDisable_WebElement(driver, signInBtn, waitInterval, "Sign in Button");
    }

    public void assert_signInBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, signInBtn, waitInterval, "Sign in Button");
    }

    public void clickOn_nextBtn(){
        click_onWebElement_waitClickable(driver, nextBtn, waitInterval,"Next Button");
    }

    public void clickOn_forgotPasswordBtn(){
        click_onWebElement_waitClickable(driver, forgotPasswordBtn, waitInterval,"Forgot Password");
    }

    public void clickOn_privacyPolicyBtn(){
        click_onWebElement_waitClickable(driver, privacyPolicyBtn, waitInterval,"Privacy Policy");
    }

    public void clickOn_termsAndConditionsBtn(){
        click_onWebElement_waitClickable(driver, termsAndConditionsBtn, waitInterval,"Terms and Conditions");
    }

    public String buffer_passwordInputField() {
        return buffer_getText_attribute_WebElement(driver, passwordInputField, waitInterval,"value", "Password Input Field");
    }

    public void assert_passwordInputField(String elementType, String expectedType){
        LoggerAction("Verification of Password Input Field Type");
        assertElementType_WebElement(driver, passwordInputField, waitInterval, "Password Input Field", elementType, expectedType);
    }

    public void assert_userName(String userName){
        assert_getText_WebElement(driver, userTitle, waitInterval, "User name", userName);

    }










}



package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.Utils.*;


public class RegistrationPage {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 15;

    private final By toastContainer = By.xpath("//div[@id='toast-container']");
    private final By pageTitle = By.xpath("//b[normalize-space()='Welcome!']");
    private final By welcomeMsg = By.xpath("//p[normalize-space()='Please complete this form to create your account.']");
    private final By legalNameInfoBtn = By.xpath("//p[@class='u-margin-t-4']//i[@class='fas fa-info-circle u-color-gray-light']");
    private final By legalNameTooltip = By.id("tooltip-0");
    private final By firstNameInputField = By.id("first-name-input");
    private final By firstName_warningMessage = By.xpath("(//div[@class='u-color-danger'][normalize-space()='Required'])[1]"); // Email: 1. Required;  2. Incorrect email format; Password: 1. Doesn't meet all requirements
    private final By middleNameInputField = By.id("middle-name-input");
    private final By lastNameInputField = By.id("last-name-input");
    private final By lastName_warningMessage = By.xpath("(//div[@class='u-color-danger'][normalize-space()='Required'])[2]"); // Email: 1. Required;  2. Incorrect email format; Password: 1. Doesn't meet all requirements
    private final By suffixInputField = By.id("suffix-input");
    private final By emailInfoBtn = By.xpath("//div[@class='c-form-group test-emailFormGroup']//i[@class='fas fa-info-circle u-color-gray-light']");
    private final By emailTooltip = By.id("tooltip-1");
    private final By emailInputField = By.id("email-input");
    private final By passwordInputField = By.id("password-input");
    private final By password_warningMessage = By.xpath("//div[normalize-space()='']"); // Password does not meet requirements above
    private final By rePasswordInputField = By.id("password-confirmation-input");
    private final By rePassword_warningMessage = By.xpath("//div[normalize-space()='Passwords do not match.']//i[@aria-hidden='true']"); // Passwords do not match
    private final By showPasswordChkBox = By.xpath("//div[@class='c-checkbox--container']");
    private final By submitInBtn = By.xpath("//button[contains(text(), 'Submit')]");
    private final By submitTooltip = By.id("tooltip-2");
    private final By privacyPolicyBtn = By.xpath("//a[normalize-space()='Privacy Policy']");
    private final By termsAndConditionsBtn = By.xpath("//a[normalize-space()='Terms and Conditions']");




    public RegistrationPage(AppiumDriver<MobileElement> driver) {
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

    public void clickOn_legalNameInfoBtn(){
        click_onWebElement_waitClickable(driver, legalNameInfoBtn, waitInterval,"Legal Name Info Button");
    }

    public void assert_legalNameTooltip(String submitTooltipMsg){
        assert_getText_WebElement(driver, legalNameTooltip, waitInterval, "Legal Name Tooltip Message", submitTooltipMsg);
    }

//      First Name
   public void insert_firstNameInputField(String text) {
       sendKey_toWebElement(driver, firstNameInputField, waitInterval,"First Name Input Field", text, false);
    }

    public void clear_firstNameInputField() {
        clearInputField_toWebElement(driver, firstNameInputField, waitInterval, "First Name Input Field");
    }

    public void clickOn_firstNameInputField(){
        click_onWebElement_waitClickable(driver, firstNameInputField, waitInterval,"First Name Input Field");
    }

    public void assert_firstNameFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, firstNameInputField, waitInterval, "First Name Input field", cssColor, color);
    }
    public void assert_firstNameWarningMsg(String warningMsg){
        assert_getText_WebElement(driver, firstName_warningMessage, waitInterval, "First Name Error Message", warningMsg);
    }

//    Middle Name
    public void insert_middleNameInputField(String text) {
        sendKey_toWebElement(driver, middleNameInputField, waitInterval,"Middle Name Input Field", text, false);
    }

    public void clear_middleNameInputField() {
        clearInputField_toWebElement(driver, middleNameInputField, waitInterval, "Middle Name Input Field");
    }

    public void clickOn_middleNameInputField(){
        click_onWebElement_waitClickable(driver, middleNameInputField, waitInterval,"Middle Name Input Field");
    }

//    Last Name
    public void insert_lastNameInputField(String text) {
        sendKey_toWebElement(driver, lastNameInputField, waitInterval,"Last Name Input Field", text, false);
    }

    public void clear_lastNameInputField() {
        clearInputField_toWebElement(driver, lastNameInputField, waitInterval, "Last Name Input Field");
    }

    public void clickOn_lastNameInputField(){
        click_onWebElement_waitClickable(driver, lastNameInputField, waitInterval,"Last Name Input Field");
    }

    public void assert_lastNameFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, lastNameInputField, waitInterval, "Last Name Input field", cssColor, color);
    }

    public void assert_lastNameWarningMsg(String warningMsg){
        assert_getText_WebElement(driver, lastName_warningMessage, waitInterval, "Last Name Error Message", warningMsg);
    }

    public void clickOn_emailInfoBtn(){
        click_onWebElement_waitClickable(driver, emailInfoBtn, waitInterval,"Email Info Button");
    }

    public void assert_emailTooltip(String submitTooltipMsg){
        assert_getText_WebElement(driver, emailTooltip, waitInterval, "Email Tooltip Message", submitTooltipMsg);
    }

    public void assert_emailInputField(String userMail){
        assertElementType_WebElement(driver, emailInputField, waitInterval, "Email Input Field", "value", userMail);

//        driver.findElement(emailInputField).getAttribute("value");

    }


//    Password
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

    public void assert_passwordWarningMsg(String warningMsg){
//        assert_getText_WebElement(driver, password_warningMessage, waitInterval, "Password Error Message", warningMsg);
        assertElementPresence_pageSource_WebElement(driver, passwordInputField, waitInterval, "Password Error Message", warningMsg);
    }

    public void assert_passwordInputField(){

        if(assertElementType_WebElement(driver, middleNameInputField, waitInterval, "Password Input Field", "type", "text")){
            LoggerAssert_Passed("Verification successful. Password is Visible");
        }else {
            LoggerAssert_Failed("Verification unsuccessfully. Password is not Visible");
            Assert.fail();
        }
    }

//    Retype password

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
//        assert_getText_WebElement(driver, rePassword_warningMessage, waitInterval, "Retype Password Error Message", warningMsg);
        assertElementPresence_pageSource_WebElement(driver, rePassword_warningMessage, waitInterval, "Retype Password Error Message", warningMsg);
    }

    public void assert_rePasswordInputField(){

        if(assertElementType_WebElement(driver, middleNameInputField, waitInterval, "Retype Password Input Field", "type", "text")){
            LoggerAssert_Passed("Verification successful. Retype Password is Visible");
        }else {
            LoggerAssert_Failed("Verification unsuccessfully. Retype Password is not Visible");
            Assert.fail();
        }
    }


    public void clickOn_pageTitle(){
        click_onWebElement_waitClickable(driver, pageTitle, waitInterval,"Page Title");
    }

    public void clickOn_showPasswordChkBox(){
        click_onWebElement_waitClickable(driver, showPasswordChkBox, waitInterval,"Show Password Checkbox");
    }

    public void clickOn_submitBtn(){
        click_onWebElement_waitVisibility(driver, submitInBtn, waitInterval,"Submit Button");
    }

    public void assert_submitTooltip(String submitTooltipMsg){
        assert_getText_WebElement(driver, submitTooltip, waitInterval, "Submit Tooltip Message", submitTooltipMsg);
    }

    public void assert_submitInBtn_isDisable(){
        assertElementIsDisable_WebElement(driver, submitInBtn, waitInterval, "Submit in Button");
    }

    public void assert_submitInBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, submitInBtn, waitInterval, "Submit in Button");
    }


    public void clickOn_privacyPolicyBtn(){
        click_onWebElement_waitClickable(driver, privacyPolicyBtn, waitInterval,"Privacy Policy");
    }

    public void clickOn_termsAndConditionsBtn(){
        click_onWebElement_waitClickable(driver, termsAndConditionsBtn, waitInterval,"Terms and Conditions");
    }

    public void assert_toastMsg(String errorMessage){
        assert_getText_WebElement(driver, toastContainer, waitInterval, "Toast Message", errorMessage);
    }




}



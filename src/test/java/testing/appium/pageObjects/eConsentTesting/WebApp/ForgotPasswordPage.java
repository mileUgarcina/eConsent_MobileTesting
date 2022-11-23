package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static testing.appium.helpers.Utils.*;


public class ForgotPasswordPage {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 15;

    private final By toastContainer = By.xpath("//div[@id='toast-container']");
    private final By pageTitle = By.xpath("//h1[normalize-space()='Reset Your Password']");
    private final By emailInputField = By.id("email-input");
    private final By warningMessage = By.xpath("/html[1]/body[1]/app-root[1]/app-auth-layout[1]/div[1]/div[1]/app-forgot-password[1]/div[1]/form[1]/div[1]/div[1]"); // Email: 1. Required;  2. Incorrect email format; Password: 1. Doesn't meet all requirements
    private final By requestPasswordResetBtn = By.xpath("//button[normalize-space()='Request password reset']");
    private final By signInBtn = By.xpath("//a[@id='sign-up-pw-btn']");



    public ForgotPasswordPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageTitle, waitInterval, "Forgot Password Page Title");
    }

    public void assert_toastMsg(String errorMessage){
        assert_getText_WebElement(driver, toastContainer, waitInterval, "Toast Message", errorMessage);
    }

    public void insert_emailInputField(String text) {
        sendKey_toWebElement(driver, emailInputField, waitInterval,"Email Input Field", text, false);
    }

    public void clear_emailInputField() {
        clearInputField_toWebElement(driver, emailInputField, waitInterval, "Email Input Field");
    }

    public void clickOn_emailInputField(){
        click_onWebElement_waitClickable(driver, emailInputField, waitInterval,"Email Input Field");
    }

    public void assert_emailInputField(String username){
        assertElementType_WebElement(driver, emailInputField, waitInterval, "Email Input Field", "value", username);
    }

    public void assert_emailWarningMsg(String warningMsg){
        assert_getText_WebElement(driver, warningMessage, waitInterval, "Email Error Message", warningMsg);
    }

    public void assert_emailFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, emailInputField, waitInterval, "Email Input field", cssColor, color);
    }

    public void clickOn_requestPasswordResetBtn(){
        click_onWebElement_waitClickable(driver, requestPasswordResetBtn, waitInterval,"Request Password Reset Button");
    }

    public void assert_requestPasswordResetBtn_isDisable(){
        assertElementIsDisable_WebElement(driver, requestPasswordResetBtn, waitInterval, "Request Password Reset Button");
    }

    public void assert_requestPasswordResetBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, requestPasswordResetBtn, waitInterval, "Request Password Reset Button");
    }

    public void clickOn_signInBtnBtn(){
        click_onWebElement_waitClickable(driver, signInBtn, waitInterval,"Sign In Button");
    }


}




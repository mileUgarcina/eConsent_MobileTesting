package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.*;


public class ProfileSettingsPage {

    static AppiumDriver<MobileElement> driver;

    private static final int waitInterval = 15;

    private final By toastContainer = By.xpath("//div[@id='toast-container']");
    private final By backBtn = By.xpath("//span[@class='profile-menu__back-button']");
    private static final By pageTitle = By.xpath("//p[@class='profile-settings__header']");

    private final By updateProfileNameBtn = By.xpath("//a[@data-test='updateProfileNameButton']");

    private final By updateProfileLanguageBtn = By.xpath("//a[@data-test='updateProfileLanguageButton']");

//    Change Profile Name popUp
    private final By pageTitleProfilePopUp = By.xpath("//div[@class='update-profile-modal__title']");
    private final By xBtn = By.xpath(" //i[@class='fa fa-lg fa-times u-pointer-hover']");

    private final By firstNameInputField = By.id("first-name-input");
    private final By warningMessageFirstName = By.xpath("(//div[contains(text(),'Required')])[1]"); //  "Required"
    private final By middleNameInputField = By.id("middle-name-input");
    private final By lastNameInputField = By.id("last-name-input");
    private final By warningMessageLastName = By.xpath("(//div[contains(text(),'Required')])[2]"); //  "Required"
    private final By cancelBtn = By.xpath("//button[contains(text(), 'Cancel')]");
    private final By saveBtn = By.xpath("//button[contains(text(), 'Save')]");

//    Change Language popUp
    private final By pageTitleLanguagePopUp = By.xpath("//div[@class='update-profile-modal__title']");
    private final By engRadioBtn = By.id("language-radio-en");
    private final By espRadioBtn = By.id("language-radio-es");


    public ProfileSettingsPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    public void assert_ProfileSettingsPage_for_Postcondition( ) {

        try {
            LoggerWaiting("To be Visible element: \"" + "Profile Settings Page Title" + "\"");
            Thread.sleep(1000);
            waitForElement(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pageTitle));
            LoggerAssert_Passed("Verification successful - element: \"" + "Profile Settings Page Title" + "\" is Present");
        } catch (Exception e) {
            LoggerAssert_Failed("Verification unsuccessful - element: \"" + "Profile Settings Page Title" + "\" is not Present");
            String appUrl = appEnvironment();
            openWebPage_WebElement(driver, appUrl + PROFILE_SETTINGS_PAGE_LINK);
        }
    }

    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageTitle, waitInterval, "Profile Settings Page");
    }

    public void clickOn_backBtn(){
        click_onWebElement_waitClickable(driver, backBtn, waitInterval,"Back Button");

    }

    public void clickOn_updateProfileNameBtn(){
        click_onWebElement_waitClickable(driver, updateProfileNameBtn, waitInterval,"Update Profile Name Button");
    }

    public void clickOn_updateProfileLanguageBtn(){
        click_onWebElement_waitClickable(driver, updateProfileLanguageBtn, waitInterval,"Update Profile Language Button");
    }

    public void assert_profileName(String firstName, String middleName, String lastName){
        SoftAssert softAssert = new SoftAssert();
        try {
            waitVisibility_ofWebElement(driver, pageTitle, "Profile Settings Page", waitInterval);
            String pageSource =  driver.getPageSource();
            if(pageSource.contains(firstName)){
                LoggerAssert_Passed("Verification successful. Page Contains First Name: " + firstName);
            }else{
                LoggerAssert_Failed("Verification unsuccessful! Page Does Not Contains First Name: " + firstName);
                softAssert.fail();
            }
            if(pageSource.contains(middleName)){
                LoggerAssert_Passed("Verification successful. Page Contains Middle Name: " + middleName);
            }else{
                LoggerAssert_Failed("Verification unsuccessful! Page Does Not Contains Middle Name: " + middleName);
                softAssert.fail();
            }
            if(pageSource.contains(lastName)){
                LoggerAssert_Passed("Verification successful. Page Contains Last Name: " + lastName);
            }else{
                LoggerAssert_Failed("Verification unsuccessful! Page Does Not Contains Last Name: " + lastName);
                softAssert.fail();
            }
            softAssert.assertAll();
        } catch (Exception ex) {
            LoggerStep_Failed("Not Present: " + "Profile Settings Page" , ex.getMessage(), true);
        }
    }

    public void assert_emailAddress(String email){

        try {
            waitVisibility_ofWebElement(driver, pageTitle, "Profile Settings Page", waitInterval);
            String pageSource =  driver.getPageSource();
            if(pageSource.contains(email)){
                LoggerAssert_Passed("Verification successful. Page Contains First Name: " + email);
            }else{
                LoggerAssert_Failed("Verification unsuccessful! Page Does Not Contains First Name: " + email);
                Assert.fail();
            }

        } catch (Exception ex) {
            LoggerStep_Failed("Not Present: " + "Profile Settings Page" , ex.getMessage(), true);
        }
    }


    public void assert_password(String password){

        try {
            String asterisk = "*".repeat(password.length());
            waitVisibility_ofWebElement(driver, pageTitle, "Profile Settings Page", waitInterval);
            String pageSource =  driver.getPageSource();
            if(pageSource.contains(asterisk)){
                LoggerAssert_Passed("Verification successful. Page Contains Password: " + asterisk);
            }else{
                LoggerAssert_Failed("Verification unsuccessful! Page Does Not Contains Password: " + asterisk);
                Assert.fail();
            }

        } catch (Exception ex) {
            LoggerStep_Failed("Not Present: " + "Profile Settings Page" , ex.getMessage(), true);
        }
    }

    public void assert_Language(String language){

        try {
            waitVisibility_ofWebElement(driver, pageTitle, "Profile Settings Page", waitInterval);
            String pageSource =  driver.getPageSource();
            if(pageSource.contains(language)){
                LoggerAssert_Passed("Verification successful. Page Contains Language: " + language);
            }else{
                LoggerAssert_Failed("Verification unsuccessful! Page Does Not Contains Language: " + language);
                Assert.fail();
            }

        } catch (Exception ex) {
            LoggerStep_Failed("Not Present: " + "Profile Settings Page" , ex.getMessage(), true);
        }
    }

//    Change Profile Name popUp

    public void assert_pageTitleProfilePopUp(){
        assertElementPresence_WebElement(driver, pageTitleProfilePopUp, waitInterval, "Change Profile Name PopUp");
    }

    public void assert_toastContainer(String msg){
        assert_getText_WebElement(driver, toastContainer, waitInterval, "Toast Message", msg);
    }

    public void clickOn_firstNameInputField(){
        click_onWebElement_waitClickable(driver, firstNameInputField, waitInterval,"First Name Input Field");
    }

    public String getText_firstNameInputField(){
        return getElementText_onWebElement(driver, firstNameInputField, waitInterval,"First Name Input Field");
    }

    public void clear_firstNameInputField() {
        clearInputField_doubleClick_toWebElement(driver, firstNameInputField, waitInterval, "First Name Input Field");
    }

    public void insert_firstNameInputField(String firstName) {
        sendKey_toWebElement(driver, firstNameInputField, waitInterval,"First Name Input Field", firstName, false);
    }

    public String buffer_firstNameInputField() {
        return buffer_getText_attribute_WebElement(driver, firstNameInputField, waitInterval,"value", "First Name Input Field");
    }

    public void assert_firstNameWarningMsg(String warningMsg){
        assert_getText_WebElement(driver, warningMessageFirstName, waitInterval, "First Name Error Message", warningMsg);
    }

    public void assert_firstNameFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, firstNameInputField, waitInterval, "First Name Input field", cssColor, color);
    }

    public void clickOn_middleNameInputField(){
        click_onWebElement_waitClickable(driver, middleNameInputField, waitInterval,"Middle Name Input Field");
    }

    public String getText_middleNameInputField(){
        return getElementText_onWebElement(driver, middleNameInputField, waitInterval,"Middle Name Input Field");

    }

    public String buffer_middleNameInputField() {
        return buffer_getText_attribute_WebElement(driver, middleNameInputField, waitInterval,"value", "Middle Name Input Field");
    }

    public void clear_middleNameInputField() {
        clearInputField_doubleClick_toWebElement(driver,middleNameInputField, waitInterval, "Middle Name Input Field");
    }

    public void insert_middleNameInputField(String middleName) {
        sendKey_toWebElement(driver, middleNameInputField, waitInterval,"Middle Name Input Field", middleName, false);
    }

    public void assert_middleNameFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, middleNameInputField, waitInterval, "Middle Name Input field", cssColor, color);
    }

    public void clickOn_lastNameInputField(){
        click_onWebElement_waitClickable(driver, lastNameInputField, waitInterval,"Last Name Input Field");
    }

    public String getText_lastNameInputField(){
        return getElementText_onWebElement(driver, lastNameInputField, waitInterval,"Last Name Input Field");
    }

    public void clear_lastNameInputField() {
        clearInputField_doubleClick_toWebElement(driver, lastNameInputField, waitInterval, "Last Name Input Field");
    }

    public void insert_lastNameInputField(String lastName) {
        sendKey_toWebElement(driver, lastNameInputField, waitInterval,"Last Name Input Field", lastName, false);
    }

    public String buffer_lastNameInputField() {
        return  buffer_getText_attribute_WebElement(driver, lastNameInputField, waitInterval,"value", "Last Name Input Field");
    }

    public void assert_lastNameWarningMsg(String warningMsg){
        assert_getText_WebElement(driver, warningMessageLastName, waitInterval, "Last Name Error Message", warningMsg);
    }

    public void assert_lastNameFieldColor(String cssColor, String color){
        assertElementBorderColor_WebElement(driver, lastNameInputField, waitInterval, "Last Name Input field", cssColor, color);
    }

    public void clickOn_cancelBtn(){
        click_onWebElement_waitClickable(driver, cancelBtn, waitInterval,"Cancel Button");
    }

    public void clickOn_saveBtn(){
        click_onWebElement_waitClickable(driver, saveBtn, waitInterval,"Save Button");
    }

    public void assert_saveBtn_isDisable(){
        assertElementIsDisable_WebElement(driver, saveBtn, waitInterval, "Save Button");
    }

    public void assert_saveBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, saveBtn, waitInterval, "Save Button");
    }

    public void clickOn_xBtn(){
        click_onWebElement_waitClickable(driver, xBtn, waitInterval,"X Button");
    }

    //    Change Language popUp

    public void assert_pageTitleLanguagePopUp(){
        assertElementPresence_WebElement(driver, pageTitleLanguagePopUp, waitInterval, "Change Language PopUp");
    }

    public void clickOn_engRadioBtn(){
        click_onWebElement_waitClickable(driver, engRadioBtn, waitInterval,"English Radio Button");
    }

    public void clickOn_espRadioBtn(){
        click_onWebElement_waitClickable(driver, espRadioBtn, waitInterval,"Español Radio Button");
    }



}




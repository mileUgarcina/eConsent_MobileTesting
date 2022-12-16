package testing.appium.runner;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.remote.MobileCapabilityType;
import testing.appium.runner.propertyFile.DataProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.runner.browserStack.BrowserStackAPI.app_name;
import static testing.appium.runner.browserStack.BrowserStackAPI.app_url;
import static testing.appium.runner.eConsent_HealthCheckResponse.eConsent_HC_API.appVersion;
import static testing.appium.runner.propertyFile.DataProvider.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.DEV_TEAM;


public class BaseInitConfig {

    boolean useRemoteUrl = false;
    boolean sauceLabs = false;
    boolean browserStack = false;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    //    protected AppiumDriver<MobileElement> driver = null;
    protected ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

    /**
     * local capabilities for Android
     */
    public void Local_Android_RealDevice_App() {
        useRemoteUrl = false;
        capabilities.setCapability("platformName", DataProvider.LocalAndroidConfigData.PLATFORM_NAME_LOCAL_ANDROID);
        capabilities.setCapability("deviceName", DataProvider.LocalAndroidConfigData.DEVICE_NAME_LOCAL_ANDROID);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("appPackage", "com.android.settings");
        capabilities.setCapability("appActivity", ".Settings");
        capabilities.setCapability("appPackage", DataProvider.LocalAndroidConfigData.appPackageLocalAndroid());
        capabilities.setCapability("appActivity", DataProvider.LocalAndroidConfigData.APP_ACTIVITY_LOCAL_ANDROID);
    }

    /**
     * local capabilities for iOS Simulator
     */
    public void localiOSimulatorSCaps() {
        useRemoteUrl = false;
        capabilities.setCapability("platformName", DataProvider.LocaliOSConfigurationData.PLATFORM_NAME_LOCAL_SIMULATOR_IOS);
        capabilities.setCapability("deviceName", DataProvider.LocaliOSConfigurationData.DEVICE_NAME_LOCAL_SIMULATOR_IOS);
        capabilities.setCapability("automationName", DataProvider.LocaliOSConfigurationData.AUTOMATION_NAME_LOCAL_SIMULATOR_IOS);
        capabilities.setCapability("noReset", DataProvider.LocaliOSConfigurationData.NO_RESET_LOCAL_SIMULATOR_IOS);
        capabilities.setCapability("platformVersion", DataProvider.LocaliOSConfigurationData.PLATFORM_VERSION_LOCAL_SIMULATOR_IOS);
        capabilities.setCapability("useNewWDA", DataProvider.LocaliOSConfigurationData.USE_NEW_WDA_LOCAL_SIMULATOR_IOS);
        capabilities.setCapability("autoAcceptAlerts", DataProvider.LocaliOSConfigurationData.AUTO_ACCEPT_ALERTS_LOCAL_SIMULATOR_IOS);
        capabilities.setCapability("app", DataProvider.LocaliOSConfigurationData.APP_LOCAL_SIMULATOR_IOS);
    }

    /**
     * local capabilities for iOS Real Device
     */
    public void localiOSRealDeviceCaps() {
        // compile app with XCode, grab path to compiled app from "Products" folder, it will be compiled for "simulator" target
        // Caps for iOS real device testing
        useRemoteUrl = false;
        capabilities.setCapability("deviceName", DataProvider.LocaliOSConfigurationData.DEVICE_NAME_LOCAL_IOS);
        capabilities.setCapability("browserName", DataProvider.LocaliOSConfigurationData.BROWSER_NAME_LOCAL_IOS);
        capabilities.setCapability("udid", DataProvider.LocaliOSConfigurationData.UDID_LOCAL_IOS);
        capabilities.setCapability("bundleId", DataProvider.LocaliOSConfigurationData.BUNDLE_ID_LOCA_LIOS);
        capabilities.setCapability("xcodeOrgId", DataProvider.LocaliOSConfigurationData.XCODE_ORG_ID_LOCAL_IOS);
        capabilities.setCapability("xcodeSigningId", DataProvider.LocaliOSConfigurationData.XCODE_SIGNING_ID_LOCAL_IOS);
        capabilities.setCapability("updatedWDABundleId", DataProvider.LocaliOSConfigurationData.UPDATED_WDA_BUNDLE_ID_LOCAL_IOS);

    }

    /**
     * SauceLabs capabilities for Android Real Device
     * @param suiteName - Parameter from testNG xml File
     * @param platformParameter - Parameter from testNG xml File
     */
    public void sauceLabs_Android_RealDevice_App(String suiteName, String platformParameter) {
        useRemoteUrl = true;
        sauceLabs = true;
        capabilities.setCapability("platformVersion", DataProvider.SlAndroidConfigData.platformVersionSlAndroid());
        capabilities.setCapability("platformName", DataProvider.SlAndroidConfigData.PLATFORM_NAME_SL_ANDROID);
//        capabilities.setCapability("deviceName", deviceNameSlAndroid);
        capabilities.setCapability("phoneOnly", DataProvider.SlAndroidConfigData.PHONE_ONLY_SL_ANDROID);
//        capabilities.setCapability("noReset", noResetSlAndroid);
        capabilities.setCapability("name", DataProvider.SlAndroidConfigData.nameSlAndroid() + " - " + PLATFORM_PARAMETER + " --> " + suiteName);
        capabilities.setCapability("appiumVersion", DataProvider.SlAndroidConfigData.APPIUM_VERSION_SL_ANDROID_REAL_DEVICE);
        capabilities.setCapability("app", DataProvider.SlAndroidConfigData.appSlAndroid());
        capabilities.setCapability("build", DataProvider.SlAndroidConfigData.nameSlAndroid());
        capabilities.setCapability("username", DataProvider.slAuthorisationData.USERNAME);
        capabilities.setCapability("accessKey", DataProvider.slAuthorisationData.ACCESS_KEY);
        capabilities.setCapability("allowTouchIdEnroll", DataProvider.SlAndroidConfigData.DISABLE_TOUCH_ID_ENROLL_SL_ANDROID);
        capabilities.setCapability("autoGrantPermissions", DataProvider.SlAndroidConfigData.AUTO_GRANT_PERMISSIONS);
        capabilities.setCapability("sauceLabsNetworkCapture", true);
        capabilities.setCapability("audioCapture", true);
    }

    /**
     * SauceLabs capabilities for Android Emulator
     * @param suiteName - Parameter from testNG xml File
     * @param platformParameter - Parameter from testNG xml File
     */
    public void SauceLabs_Android_Emulator_App(String suiteName, String platformParameter) {
        useRemoteUrl = true;
        sauceLabs = true;
        capabilities.setCapability("platformVersion", DataProvider.SlAndroidConfigData.platformVersionSlAndroid());
        capabilities.setCapability("platformName", DataProvider.SlAndroidConfigData.PLATFORM_NAME_SL_ANDROID);
        capabilities.setCapability("deviceName", DataProvider.SlAndroidConfigData.DEVICE_NAME_SL_ANDROID);
//        capabilities.setCapability("noReset", noResetSlAndroid);
        capabilities.setCapability("name", DataProvider.SlAndroidConfigData.nameSlAndroid() + " - "+ PLATFORM_PARAMETER + " --> " + suiteName);
        capabilities.setCapability("appiumVersion", DataProvider.SlAndroidConfigData.APPIUM_VERSION_SL_ANDROID_EMULATOR);
        capabilities.setCapability("app", DataProvider.SlAndroidConfigData.appSlAndroid());
        capabilities.setCapability("build", DataProvider.SlAndroidConfigData.nameSlAndroid());
        capabilities.setCapability("username", DataProvider.slAuthorisationData.USERNAME);
        capabilities.setCapability("accessKey", DataProvider.slAuthorisationData.ACCESS_KEY);
        capabilities.setCapability("autoGrantPermissions", DataProvider.SlAndroidConfigData.AUTO_GRANT_PERMISSIONS);
        capabilities.setCapability("audioCapture", true);
    }


    /**
     * SauceLabs capabilities for iOS Simulator
     * @param suiteName - Parameter from testNG xml File
     * @param platformParameter - Parameter from testNG xml File
     */
    public void sauceLabs_iOS_Simulator_App(String suiteName, String platformParameter) {
        useRemoteUrl = true;
        sauceLabs = true;
        capabilities.setCapability("platformVersion", DataProvider.SliOSConfigData.platformVersionSliOSSimulator());
        capabilities.setCapability("platformName", DataProvider.SliOSConfigData.PLATFORM_NAME_SL_IOS);
//        capabilities.setCapability("browserName", "");
        capabilities.setCapability("deviceName", DataProvider.SliOSConfigData.DEVICE_NAME_SL_IOS_SIMULATOR);
//        capabilities.setCapability("noReset", noResetSliOS);
        capabilities.setCapability("automationName", DataProvider.SliOSConfigData.AUTOMATION_NAME_SL_IOS);
//        capabilities.setCapability("useNewWDA", useNewWDASliOS);
        capabilities.setCapability("name", DataProvider.SliOSConfigData.nameSliOS() + " - "+ PLATFORM_PARAMETER + " --> " + suiteName);
        capabilities.setCapability("appiumVersion", DataProvider.SliOSConfigData.APPIUM_VERSION_SL_IOS_SIMULATOR);
        capabilities.setCapability("app", DataProvider.SliOSConfigData.appSliOS());
        capabilities.setCapability("build", DataProvider.SliOSConfigData.nameSliOS());
        capabilities.setCapability("autoAcceptAlerts", DataProvider.SliOSConfigData.AUTO_ACCEPT_ALERTS_SL_IOS);
        capabilities.setCapability("username", DataProvider.slAuthorisationData.USERNAME);
        capabilities.setCapability("accessKey", DataProvider.slAuthorisationData.ACCESS_KEY);
        capabilities.setCapability("allowTouchIdEnroll", DataProvider.SliOSConfigData.ALLOW_TOUCH_ID_ENROLL_SL_IOS);
        capabilities.setCapability("audioCapture", true);
    }

    /**
     * SauceLabs capabilities for Android Emulator, Biometric Enable
     * @param suiteName - Parameter from testNG xml File
     * @param platformParameter - Parameter from testNG xml File
     */
    public void sauceLabs_iOS_RealDevice_App_Biometric(String suiteName, String platformParameter) {
        useRemoteUrl = true;
        sauceLabs = true;
        capabilities.setCapability("deviceName", DataProvider.SliOSConfigData.DEVICE_NAME_SL_IOS_SIMULATOR);
        capabilities.setCapability("platformName", DataProvider.SliOSConfigData.PLATFORM_NAME_SL_IOS);
        capabilities.setCapability("allowTouchIdEnroll", DataProvider.SliOSConfigData.ALLOW_TOUCH_ID_ENROLL_SL_IOS);
        capabilities.setCapability("noReset", DataProvider.SliOSConfigData.NO_RESET_SL_IOS);
        capabilities.setCapability("automationName", DataProvider.SliOSConfigData.AUTOMATION_NAME_SL_IOS);
        capabilities.setCapability("useNewWDA", DataProvider.SliOSConfigData.USE_NEW_WDA_SL_IOS);
        capabilities.setCapability("name", DataProvider.SliOSConfigData.nameSliOS() + " - "+ PLATFORM_PARAMETER + " --> " + suiteName);
        capabilities.setCapability("app", DataProvider.SliOSConfigData.appSliOS());
        capabilities.setCapability("build", DataProvider.SliOSConfigData.nameSliOS());
        capabilities.setCapability("autoAcceptAlerts", DataProvider.SliOSConfigData.AUTO_ACCEPT_ALERTS_SL_IOS);
        capabilities.setCapability("username", DataProvider.slAuthorisationData.USERNAME);
        capabilities.setCapability("accessKey", DataProvider.slAuthorisationData.ACCESS_KEY);
    }

    public void SauceLabs_Android_RealDevice_Web(String browserName, String suiteName, String platformParameter) {
        useRemoteUrl = true;
        sauceLabs = true;
//        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", DataProvider.SlAndroidConfigData.PLATFORM_NAME_SL_ANDROID);
//        capabilities.setCapability("appium:platformVersion", DataProvider.SlAndroidConfigData.platformVersionSlAndroid());
//        capabilities.setCapability("appiumVersion", DataProvider.SlAndroidConfigData.APPIUM_VERSION_SL_ANDROID_REAL_DEVICE);
        capabilities.setCapability("phoneOnly", DataProvider.SlAndroidConfigData.PHONE_ONLY_SL_ANDROID);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("allowTouchIdEnroll", DataProvider.SlAndroidConfigData.DISABLE_TOUCH_ID_ENROLL_SL_ANDROID);
        capabilities.setCapability("autoGrantPermissions", DataProvider.SlAndroidConfigData.AUTO_GRANT_PERMISSIONS);
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("build", DataProvider.SliOSConfigData.nameSliOS() + " - " + appVersion);
        sauceOptions.setCapability("name", DataProvider.SliOSConfigData.nameSliOS() + " - " + PLATFORM_PARAMETER + " - " + appVersion + " --> " + suiteName);
        sauceOptions.setCapability("appiumVersion", DataProvider.SlAndroidConfigData.APPIUM_VERSION_SL_ANDROID_REAL_DEVICE);
        capabilities.setCapability("sauce:options", sauceOptions);
//        capabilities.setCapability("username", DataProvider.slAuthorisationData.USERNAME);
//        capabilities.setCapability("accessKey", DataProvider.slAuthorisationData.ACCESS_KEY);
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
    }

    public void browserStack_Android_RealDevice_Web(String browserName, String suiteName, String platformParameter) {
        useRemoteUrl = true;
        browserStack = true;
        String device = get_Android_Device_BrowserStack();

        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("projectName", DEV_TEAM);
        browserstackOptions.put("buildName", DataProvider.SlAndroidConfigData.nameSlAndroid() + " - " + PLATFORM_PARAMETER + " - " + appVersion);
        browserstackOptions.put("sessionName", DataProvider.SlAndroidConfigData.nameSlAndroid() + " - " + PLATFORM_PARAMETER + " - " + appVersion + " --> " + suiteName);
        browserstackOptions.put("appiumVersion", DataProvider.SlAndroidConfigData.APPIUM_VERSION_SL_ANDROID_REAL_DEVICE);
        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("platformName", DataProvider.SlAndroidConfigData.PLATFORM_NAME_SL_ANDROID);
        capabilities.setCapability("platformVersion", DataProvider.SlAndroidConfigData.platformVersionSlAndroid());
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("browserstack.idleTimeout", 300);

        LoggerInformation("Capabilities set for BrowserStack iOS - App, Device: " + device );
    }

    public void browserStack_Android_RealDevice_App(String suiteName, String platformParameter) {
        useRemoteUrl = true;
        browserStack = true;
        capabilities.setCapability("platformVersion", DataProvider.SlAndroidConfigData.platformVersionSlAndroid());
        capabilities.setCapability("platformName", DataProvider.SlAndroidConfigData.PLATFORM_NAME_SL_ANDROID);
//        capabilities.setCapability("deviceName", deviceNameSlAndroid);
        capabilities.setCapability("phoneOnly", DataProvider.SlAndroidConfigData.PHONE_ONLY_SL_ANDROID);
//        capabilities.setCapability("noReset", noResetSlAndroid);
        capabilities.setCapability("name", DataProvider.SlAndroidConfigData.nameSlAndroid() + " - " + PLATFORM_PARAMETER + " --> " + suiteName);
        capabilities.setCapability("appiumVersion", DataProvider.SlAndroidConfigData.APPIUM_VERSION_SL_ANDROID_REAL_DEVICE);
        capabilities.setCapability("app", DataProvider.SlAndroidConfigData.appSlAndroid());
        capabilities.setCapability("build", DataProvider.SlAndroidConfigData.nameSlAndroid());
        capabilities.setCapability("username", DataProvider.slAuthorisationData.USERNAME);
        capabilities.setCapability("accessKey", DataProvider.slAuthorisationData.ACCESS_KEY);
        capabilities.setCapability("allowTouchIdEnroll", DataProvider.SlAndroidConfigData.DISABLE_TOUCH_ID_ENROLL_SL_ANDROID);
        capabilities.setCapability("autoGrantPermissions", DataProvider.SlAndroidConfigData.AUTO_GRANT_PERMISSIONS);
        capabilities.setCapability("sauceLabsNetworkCapture", true);
        capabilities.setCapability("audioCapture", true);
        capabilities.setCapability("browserstack.idleTimeout", 300);
    }

    public void sauceLabs_iOS_RealDevice_Web(String browserName, String suiteName, String platformParameter) {
        useRemoteUrl = true;
        sauceLabs = true;
        capabilities.setCapability("platformName", DataProvider.SliOSConfigData.PLATFORM_NAME_SL_IOS);
//        capabilities.setCapability("platformVersion", DataProvider.SliOSConfigData.platformVersionSliOSRealDevice());
//        capabilities.setCapability("appiumVersion", DataProvider.SliOSConfigData.APPIUM_VERSION_SL_IOS_REAL_DEVICE);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("appium:deviceName", DataProvider.SliOSConfigData.DEVICE_NAME_SL_IOS_REAL_DEVICE);
//        capabilities.setCapability("phoneOnly", DataProvider.SliOSConfigData.PHONE_ONLY_SL_IOS);
        capabilities.setCapability("appium:automationName", DataProvider.SliOSConfigData.AUTOMATION_NAME_SL_IOS);
        capabilities.setCapability("autoGrantPermissions", DataProvider.SlAndroidConfigData.AUTO_GRANT_PERMISSIONS);
        capabilities.setCapability("appium:autoAcceptAlerts","true");
        capabilities.setCapability("safariAllowPopups", "true");
        capabilities.setCapability("browserVersion", "latest");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("build", DataProvider.SliOSConfigData.nameSliOS() + " - " + appVersion);
        sauceOptions.setCapability("name", DataProvider.SliOSConfigData.nameSliOS() + " - " + PLATFORM_PARAMETER + " - " + appVersion + " --> " + suiteName);
        sauceOptions.setCapability("appiumVersion", DataProvider.SlAndroidConfigData.APPIUM_VERSION_SL_ANDROID_REAL_DEVICE);
        sauceOptions.setCapability("phoneOnly", DataProvider.SliOSConfigData.PHONE_ONLY_SL_IOS);
        capabilities.setCapability("sauce:options", sauceOptions);
    }

    public void sauceLabs_iOS_RealDevice_App(String browserName, String suiteName, String platformParameter) {
        useRemoteUrl = true;
        sauceLabs = true;
//        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", DataProvider.SliOSConfigData.PLATFORM_NAME_SL_IOS);
//        capabilities.setCapability("platformVersion", DataProvider.SliOSConfigData.platformVersionSliOSRealDevice());
        capabilities.setCapability("appiumVersion", DataProvider.SliOSConfigData.APPIUM_VERSION_SL_IOS_REAL_DEVICE);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("appium:deviceName", DataProvider.SliOSConfigData.DEVICE_NAME_SL_IOS_REAL_DEVICE);
        capabilities.setCapability("phoneOnly", DataProvider.SliOSConfigData.PHONE_ONLY_SL_IOS);
        capabilities.setCapability("appium:automationName", DataProvider.SliOSConfigData.AUTOMATION_NAME_SL_IOS);
//        capabilities.setCapability("autoAcceptAlerts", DataProvider.SliOSConfigData.AUTO_ACCEPT_ALERTS_SL_IOS);
//        capabilities.setCapability("allowTouchIdEnroll", DataProvider.SliOSConfigData.ALLOW_TOUCH_ID_ENROLL_SL_IOS);
        capabilities.setCapability("autoGrantPermissions", DataProvider.SlAndroidConfigData.AUTO_GRANT_PERMISSIONS);
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("build", DataProvider.SliOSConfigData.nameSliOS());
        sauceOptions.setCapability("name", DataProvider.SliOSConfigData.nameSliOS() + " - " + PLATFORM_PARAMETER + " --> " + suiteName);
        capabilities.setCapability("sauce:options", sauceOptions);
//        capabilities.setCapability("username", DataProvider.slAuthorisationData.USERNAME);
//        capabilities.setCapability("accessKey", DataProvider.slAuthorisationData.ACCESS_KEY);
    }

    public void browserStack_iOS_RealDevice_Web(String browserName, String suiteName, String platformParameter) {
        useRemoteUrl = true;
        browserStack = true;
        String device = get_iOS_Device_BrowserStack();

        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("projectName", DEV_TEAM);
        browserstackOptions.put("buildName", DataProvider.SlAndroidConfigData.nameSlAndroid() + " - " + PLATFORM_PARAMETER + " - " + appVersion);
        browserstackOptions.put("sessionName", DataProvider.SlAndroidConfigData.nameSlAndroid() + " - " + PLATFORM_PARAMETER + " - " + appVersion + " --> " + suiteName);
        browserstackOptions.put("appiumVersion", DataProvider.SliOSConfigData.APPIUM_VERSION_SL_IOS_REAL_DEVICE);

        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("platformName", DataProvider.SliOSConfigData.PLATFORM_NAME_SL_IOS);
        capabilities.setCapability("platformVersion", DataProvider.SliOSConfigData.platformVersionSliOSRealDevice());
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("appium:autoAcceptAlerts", true);
        capabilities.setCapability("safariAllowPopups", true);
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("browserstack.idleTimeout", 300);

        LoggerInformation("Capabilities set for BrowserStack iOS - App, Device: " + device );
    }

    public void browserStack_iOS_RealDevice_App(String browserName, String suiteName, String platformParameter) {
        useRemoteUrl = true;
        browserStack = true;
        String device = get_iOS_Device_BrowserStack();

        String buildName = app_name.replace(".ipa", "");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("projectName", DEV_TEAM);
        browserstackOptions.put("buildName", buildName);
        browserstackOptions.put("sessionName", buildName + " --> " + suiteName);
        browserstackOptions.put("appiumVersion", DataProvider.SliOSConfigData.APPIUM_VERSION_SL_IOS_REAL_DEVICE);
        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("platformName", DataProvider.SliOSConfigData.PLATFORM_NAME_SL_IOS);
        capabilities.setCapability("platformVersion", DataProvider.SliOSConfigData.platformVersionSliOSRealDevice());
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("app", app_url);
        capabilities.setCapability("browserstack.idleTimeout", 300);
    }


    public void Local_Android_Emulator_Web() {
        useRemoteUrl = false;
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, DataProvider.LocalAndroidConfigData.PLATFORM_NAME_LOCAL_ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DataProvider.LocalAndroidConfigData.DEVICE_NAME_LOCAL_ANDROID);
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, DataProvider.LocalAndroidConfigData.BROWSER_NAME_LOCAL_ANDROID);
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability("newCommandTimeout", 120000);
    }

    public void localiOSimulatorSCapsWeb() {
        useRemoteUrl = false;
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, DataProvider.LocaliOSConfigurationData.PLATFORM_NAME_LOCAL_SIMULATOR_IOS);
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, DataProvider.LocaliOSConfigurationData.PLATFORM_VERSION_LOCAL_SIMULATOR_IOS);
//        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, DataProvider.LocaliOSConfigurationData.BROWSER_NAME_LOCAL_IOS);
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DataProvider.LocaliOSConfigurationData.DEVICE_NAME_LOCAL_SIMULATOR_IOS);


        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "16.0");
        capabilities.setCapability("deviceName", "iPhone 14 Pro Max");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("newCommandTimeout", 120000);



    }


}

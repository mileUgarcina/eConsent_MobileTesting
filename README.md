Documentation in Progress
# Florence eConsent Mobile Testing
Appium - Java - TestNG - SauceLabs - Test Rail framework for mobile testing.

This is a framework for testing Mobile applications on mobile devices, both Android and iOS.
Use Appium as a basis, and the code is written in Java. It also uses testng to manipulate test cases.
Execution of the tests themselves is performed at Sauce Labs, BrowserStack and Local dervices and the results are stored and displayed in Xray.

____________________________________________________________

## Install
* Java 13 or higher
* Gradle
* Appium
* Xcode (optional)
* Android Studio (optional)

____________________________________________________________

## Project Links


____________________________________________________________
## Run Test

- command for Mac OS terminal --> context

### _Android Test_

#### QE Environment

*  **./gradlew clean test -Pregression_Android_QA** --> Start regression testing for Android device on QA environment
*  **./gradlew clean test -PsmokeTest_Android_QA** --> Start smoke testing for Android device on QA environment

#### Dev Environment

*  **./gradlew clean test -Pregression_Android_UATv** --> Start regression testing for Android device on Dev environment
*  **./gradlew clean test -PsmokeTest_Android_UAT** --> Start smoke testing for Android device on Dev environment

#### UAT Environment

*  **./gradlew clean test -Pregression_Android_UAT** --> Start regression testing for Android device on UAT environment
*  **./gradlew clean test -PsmokeTest_Android_UAT** --> Start smoke testing for Android device on UAT environment


### _iOS Test_

#### QE Environment

*  **./gradlew clean test -Pregression_iOS_QA** --> Start regression testing for iOS device on QA environment
*  **./gradlew clean test -PsmokeTest_iOS_QA**  --> Start smoke testing for iOS device on QA environment


#### UAT Environment

*  **./gradlew clean test -Pregression_iOS_UAT**  --> Start regression testing for iOS device on UAT environment
*  **./gradlew clean test -PsmokeTest_iOS_UAT**  --> Start smoke testing for iSO device on UAT environment




### _Parallel Execution_

#### QE Environment

*  **./gradlew clean test -Pregression_Android_QA & ./gradlew clean test -Pregression_iOS_QA** --> Start parallel regression testing for Android and iOS device on QA environment
*  **./gradlew clean test -PsmokeTest_Android_QA & ./gradlew clean test -PsmokeTest_iOS_QA** --> Start parallel smoke testing for Android and iOS device on QA environment


#### Dev Environment

*  **./gradlew clean test -Pregression_Android_UAT & ./gradlew clean test -Pregression_iOS_Dev** --> Start parallel regression testing for Android and iOS device on Dev environment
*  **./gradlew clean test -PsmokeTest_Android_UAT & ./gradlew clean test -PsmokeTest_iOS_Dev** --> Start parallel smoke testing for Android and iOS device on Dev environment


#### UAT Environment

* **./gradlew clean test -Pregression_Android_UAT & ./gradlew clean test -Pregression_iOS_UAT** --> Start parallel regression testing for Android and iOS device on UAT environment
* **./gradlew clean test -PsmokeTest_Android_UAT & ./gradlew clean test -PsmokeTest_iOS_UAT** --> Start parallel smoke testing for Android and iOS device on UAT environment
____________________________________________________________


## [testNG](testNG)
Folder contains testNG XML files

* Android
  - Advisor
    - [Set1_Conversations_Advisor_AndroidEmulator.xml](testNG/Android/Advisor/Set1_Conversations_Advisor_AndroidEmulator.xml)
    - [Set1_Conversations_Advisor_AndroidRealDevice.xml](testNG/Android/Advisor/Set1_Conversations_Advisor_AndroidRealDevice.xml)
    - [SmokeTest_Advisor_AndroidRealDevice.xml](testNG/Android/Advisor/SmokeTest_Advisor_AndroidRealDevice.xml)
  - Client
    - [Set1_Conversations_Client_AndroidEmulator.xml](testNG/Android/Client/Set1_Conversations_Client_AndroidEmulator.xml)
    - [Set1_Conversations_Client_AndroidRealDevice.xml](testNG/Android/Client/Set1_Conversations_Client_AndroidRealDevice.xml)
    - [Set2_Channels_Client_AndroidRealDevice.xml](testNG/Android/Client/Set2_Channels_Client_AndroidRealDevice.xml)
    - [Set3_Assistant_Client_AndroidRealDevice.xml](testNG/Android/Client/Set3_Assistant_Client_AndroidRealDevice.xml)
    - [SmokeTest_Client_AndroidRealDevice.xml](testNG/Android/Client/SmokeTest_Client_AndroidRealDevice.xml)
  - [Regression_Android_Suite.xml](testNG/Android/Web/Regression_Android_Suite.xml)
  - [Smoke_Android_Suite.xml](testNG/Android/Web/Smoke_Android_Suite.xml)
* iOS
  - Advisor
    - [Set1_Conversations_Advisor_iOSRealDevice.xml](testNG/iOS/Advisor/Set1_Conversations_Advisor_iOSRealDevice.xml)
    - [Set1_Conversations_Advisor_iOSSimulator.xml](testNG/iOS/Advisor/Set1_Conversations_Advisor_iOSSimulator.xml)
    - [SmokeTest_Advisor_iOSRealDevice.xml](testNG/iOS/Advisor/SmokeTest_Advisor_iOSRealDevice.xml)
  - Client
    - [Set1_Conversations_Client_iOSRealDevice.xml](testNG/iOS/Client/Set1_Conversations_Client_iOSRealDevice.xml)
    - [Set1_Conversations_Client_iOSSimulator.xml](testNG/iOS/Client/Set1_Conversations_Client_iOSSimulator.xml)
    - [Set2_Channels_Client_iOSRealDevice.xml](testNG/iOS/Client/Set2_Channels_Client_iOSRealDevice.xml)
    - [Set3_Assistant_Client_iOSRealDevice.xml](testNG/iOS/Client/Set3_Assistant_Client_iOSRealDevice.xml)
    - [SmokeTest_Client_iOSRealDevice.xml](testNG/iOS/Client/SmokeTest_Client_iOSRealDevice.xml)
  - [Regression_iOS_Suite.xml](testNG/iOS/Web/Regression_iOS_Suite.xml)
  - [Smoke_iOS_Suite.xml](testNG/iOS/Web/Smoke_iOS_Suite.xml)

### testNG parameter
* name="platformParameter" - Parameter for setting desire capability
  - [value="remoteAndroidCapsEmulator"](src/test/java/ch/clx/testing/appium/runner/Init_Android.java)
  - [value="remoteAndroidCapsDefault"](src/test/java/ch/clx/testing/appium/runner/Init_Android.java)
  - [value="localAndroidCaps"](src/test/java/ch/clx/testing/appium/runner/Init_Android.java)
  - [value="remoteiOSCapsSimulator"](src/test/java/ch/clx/testing/appium/runner/Init_iOS.java)
  - [value="remoteiOSCapsDefault"](src/test/java/ch/clx/testing/appium/runner/Init_iOS.java)
  - [value="localiOSimulatorSCaps"](src/test/java/ch/clx/testing/appium/runner/Init_iOS.java)
  - [value="localiOSRealDeviceCaps"](src/test/java/ch/clx/testing/appium/runner/Init_iOS.java)
* name="testRailParameter" - Parameter for separate Android and iOS Test Run in Test Rail
  - [value="Android"](src/test/java/ch/clx/testing/appium/mepTesting/BaseTestSet.java)
  - [value="iOS"](src/test/java/ch/clx/testing/appium/mepTesting/BaseTestSet.java)
* name="testRailRun" - Parameter for creation or update Test Run in Test Rail
  - [value="Create"](src/test/java/ch/clx/testing/appium/mepTesting/BaseTestSet.java)
  - [value="Update"](src/test/java/ch/clx/testing/appium/mepTesting/BaseTestSet.java)
  - [value="No"](src/test/java/ch/clx/testing/appium/mepTesting/BaseTestSet.java)
* name="testRailRunCompleted" - Parameter for closing the Test Run and creating a Test Report
  - [value="Yes"](src/test/java/ch/clx/testing/appium/mepTesting/BaseTestSet.java)
  - [value="No"](src/test/java/ch/clx/testing/appium/mepTesting/BaseTestSet.java)
* name="user" - User for testing
* name="searchUser" - User for testing
* name="forwardUser" - User for testing
* name="participantUser" - User for testing
* name="bulkUser" - User for testing
* name="inboundUser" - User for testing

____________________________________________________________

## Config File

[settings.json](config/settings.json)

Serves for Ci CD pipeline main data file . The following, for all three environments can be found and set in it:

1. Android
* PackageId
* SauceLabsId
* SauceLabsName
* OSVersion
* Admin Username
* AdminPassword
* Client Username
* Client Password


2. iOS
* PackageId
* SauceLabsId
* SauceLabsName
* OSVersionRealDevice
* OSVersionSimulator
* Admin Username
* Admin Password
* Client Username
* Client Password
____________________________________________________________

## [Properties file](src/main/resources)


They include configuration data for Sauce Labs, Test Rail and Test Data

### *testRailRunId.properties
is used to store the Test Rail Run ID number
* [Android_dev_testRailRunId.properties](src/main/resources/Android_dev_testRailRunId.properties)
* [Android_qa_testRailRunId.properties](src/main/resources/Android_qa_testRailRunId.properties)
* [Android_sit_testRailRunId.properties](src/main/resources/Android_sit_testRailRunId.properties)
* [iOS_dev_testRailRunId.properties](src/main/resources/iOS_dev_testRailRunId.properties)
* [iOS_qa_testRailRunId.properties](src/main/resources/iOS_qa_testRailRunId.properties)
* [iOS_sit_testRailRunId.properties](src/main/resources/iOS_sit_testRailRunId.properties)


#### [environment.properties](src/main/resources/environment.properties)
serves to store basic environment parameters

* sauceLabsAppiumUrl - Sauce Labs appium server URL
* localAppiumUrl local appium server URL
* devTeam - Name of The Dev Team
* appName - Name of The Application
* envVariableQA - Name of the QA environment
* envVariableDev - Name of the Dev environment
* envVariableSIT - Name of the SIt environment
* manualSauceLabsData - Automatic or manual entry of File ID from Sauce Labs


### Configuration.properties

It serves to store [Appium Desired Capabilities](https://appium.io/docs/en/writing-running-appium/caps/) configuration data  for both local and Sauce Labs


* [localAndroidConfiguration.properties](src/main/resources/localAndroidConfiguration.properties)
* [localiOSConfiguration.properties](src/main/resources/localiOSConfiguration.properties)
* [slAndroidConfiguration.properties](src/main/resources/slAndroidConfiguration.properties)
* [sliOSConfiguration.properties](src/main/resources/sliOSConfiguration.properties)



### [slAuthorisationDataAndSetup.properties](src/main/resources/slAuthorisationDataAndSetup.properties)

In it, we can find the basic parameters needed for Sauce Labs such as:

* sLTestResultLink - Test Result link
* GetAppStorageFiles - App Storage Files link
* username - Sauce Labs Username
* accessKey - Sauce Labs Access Key
* sauceLabsToken - Sauce Labs User Token
* virtualDeviceLink - Get Jobs Virtual Device
* realDeviceLink -Get Jobs Real Device
* limit - Number of Get Jobs Real Device


### [tcData.properties](src/main/resources/tcData.properties)

All test data is in this file

* Path to test files
* Home Page Test Data
* Message Page Test Data
* Assistant Page


### [testRailData.properties](src/main/resources/testRailData.properties)

Here are all the necessary parameters and data for integration with Test Rail.
One of the most important data here would be "_case_ids_Android_" and "_case_ids_iOS_"

_case_ids_Android_ / _case_ids_iOS_ - variable that stores a string of IDs from Test Cases written in Test Rail.

____________________________________________________________




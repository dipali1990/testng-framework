package com.ymcareers.webapp.tests.core;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.ScreenshotManager;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.util.PropertiesFileReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.util.Properties;

public class Base extends ScreenshotManager {

  static String workingDir = System.getProperty("user.dir");
  public Properties prop;
  private WebDriver driver;
  public static Logger log;
  public static ExtentTest logger;
  public static ExtentReports extent;

  @BeforeSuite
  public void initiateLogExtentReport() {

    extent = new ExtentReports("./test-output/extent-reports/ExtentReportResults.html", true);
    extent.loadConfig(new File(workingDir + "/extent-config.xml"));
    log = LogManager.getLogger(ScreenshotManager.class.getName());
    PropertyConfigurator.configure("log4j.properties");
  }

  @Parameters({"environment"})
  @BeforeTest
  public void initiateProperties(String environment) {
    PropertiesFileReader propertiesFileReader = new PropertiesFileReader();
    prop = propertiesFileReader.initialiseProperties(environment);
  }

  @BeforeTest
  public void launchBrowser() {
    String browser = prop.getProperty("browser");
    driver = DriverManager.initializeDriver(browser);
  }

  @Parameters({"siteID"})
  @BeforeTest
  public void navigateToSite(String siteID) {
    String url = prop.getProperty("url") + siteID;
    driver.get(url);
    log.info(url);
  }

  @AfterMethod
  public void getResult(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
      logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
      String screenshotPath = ScreenshotManager.getScreenShot(driver, result.getName());
      // To add it in the extent report
      logger.log(LogStatus.FAIL, logger.addBase64ScreenShot(screenshotPath));

    } else if (result.getStatus() == ITestResult.SKIP) {
      System.out.println(result.getStatus());
      logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
    }
    // ending test

  }

  @AfterTest
  public void tearDown() {
    extent.endTest(logger);
    driver.quit();
  }

  @AfterSuite
  public void endReport() {
    extent.flush();
    extent.close();
  }
}

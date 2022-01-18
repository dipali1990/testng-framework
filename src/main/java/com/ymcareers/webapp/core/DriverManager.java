package com.ymcareers.webapp.core;

import com.ymcareers.webapp.enums.BrowserToUse;
import com.ymcareers.webapp.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/** This class will initialize the browser (Chrome, Firefox, IE). */
public class DriverManager {

  private static WebDriver driver;
  static String chromeDriverPath = "/var/jenkins_home/driver/chromedriver";

  public static WebDriver getDriver() {
    return driver;
  }

  /**
   * To initialize the browser.
   *
   * @param browser Which browser to use
   * @return driver
   */
  public static WebDriver initializeDriver(String browser) {
    if (browser.equalsIgnoreCase(BrowserToUse.CHROME.toString())) {
      /*System.setProperty("webdriver.chrome.driver", chromeDriverPath);
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--headless");
      options.addArguments("----window-size=1920,1080");
      options.addArguments("disable-infobars"); // disabling infobars
      // disabling extensions
      options.addArguments("--disable-extensions");
      options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
      options.addArguments("--no-sandbox"); // Bypass OS security model
      String binaryPath = "/usr/bin/google-chrome-stable";
      options.setBinary(binaryPath);
      driver = new ChromeDriver(options);*/

      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
      driver.manage().window().fullscreen();

    } else if (browser.equalsIgnoreCase(BrowserToUse.FIREFOX.toString())) {

      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();

    } else if (browser.equalsIgnoreCase(BrowserToUse.IE.toString())) {

      WebDriverManager.iedriver().setup();
      driver = new InternetExplorerDriver();
    }
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    return driver;
  }
}

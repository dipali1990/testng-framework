package com.ymcareers.webapp.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/** Info to capture screenshot after the test case failure. */
public class ScreenshotManager {

  public static String getScreenShot(WebDriver driver, String screenshotName) {
    SimpleDateFormat dateName = new SimpleDateFormat("MM-dd-yyyy_HH:mm:ss");
    String curDate = dateName.format(new Date());
    File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String encodedBase64 = null;
    try (FileInputStream fileInputStreamReader = new FileInputStream(sourceFile)) {
      byte[] bytes = new byte[(int) sourceFile.length()];
      fileInputStreamReader.read(bytes);
      encodedBase64 = new String(Base64.encodeBase64(bytes));
      String screenShotDestination =
          "./test-output/extent-reports/" + curDate + "/" + screenshotName + "_" + curDate + ".png";
      File destination = new File(screenShotDestination);
      FileUtils.copyFile(sourceFile, destination);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "data:image/png;base64," + encodedBase64;
  }
}

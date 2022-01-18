package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for the components where resume file is uploaded. */
public class UploadResume extends ComponentValidator {

  @FindBy(id = "resumeSubmit")
  WebElement uploadResumeButton;

  @FindBy(id = "file-upload-button")
  WebElement chooseFileButton;

  static String workingDir = System.getProperty("user.dir");

  public UploadResume(WebDriver driver) {
    super(driver);
  }

  /**
   * Method selects the resume from source location and uploads it.
   *
   * @param resumePath resume location
   */
  public void uploadResumeFile(String resumePath) {
    chooseFileButton.sendKeys(workingDir + resumePath);
    log.info("Resume is selected from '" + resumePath + "'");
    uploadResumeButton.click();
    log.info("Clicked on 'Upload Resume' button");
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(uploadResumeButton));
    return uploadResumeButton.isDisplayed();
  }
}

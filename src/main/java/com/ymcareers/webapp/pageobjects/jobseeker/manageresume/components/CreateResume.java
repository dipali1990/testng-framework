package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class contains WebElements for Create Resume Section available on the Manage Resumes Page.
 */
public class CreateResume extends ComponentValidator {

  @FindBy(id = "ico-upload-from-file-o")
  WebElement uploadResumeFileButton;

  @FindBy(id = "ico-build-online-o")
  WebElement buildResumeButton;

  public CreateResume(WebDriver driver) {
    super(driver);
  }

  /**
   * Method selects the Upload or Build Online Option to create a resume.
   *
   * @param resumeOption Upload or Build Online option
   */
  public void selectOptionToCreateResume(String resumeOption) {
    if (resumeOption.equalsIgnoreCase("Upload")) {
      uploadResumeFileButton.click();
    } else {
      wait.until(ExpectedConditions.elementToBeClickable(buildResumeButton));
      buildResumeButton.click();
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(uploadResumeFileButton));
    return uploadResumeFileButton.isDisplayed();
  }
}

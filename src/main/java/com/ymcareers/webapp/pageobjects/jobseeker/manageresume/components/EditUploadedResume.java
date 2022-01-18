package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for Edit Uploaded Resume Page. */
public class EditUploadedResume extends ComponentValidator {

  @FindBy(id = "button-input")
  WebElement saveResume;

  public EditUploadedResume(WebDriver driver) {
    super(driver);
  }

  public void saveUploadedResume() {
    saveResume.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(saveResume));
    return saveResume.isDisplayed();
  }
}

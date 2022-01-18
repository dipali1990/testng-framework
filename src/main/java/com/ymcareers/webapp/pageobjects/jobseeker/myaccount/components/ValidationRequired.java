package com.ymcareers.webapp.pageobjects.jobseeker.myaccount.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Validation Required Message. */
public class ValidationRequired extends ComponentValidator {

  @FindBy(xpath = "//*[text()='Validation Required']")
  WebElement validationText;

  public ValidationRequired(WebDriver driver) {
    super(driver);
  }

  public String getValidationMessage() {
    return validationText.getText();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(validationText));
    return validationText.isDisplayed();
  }
}

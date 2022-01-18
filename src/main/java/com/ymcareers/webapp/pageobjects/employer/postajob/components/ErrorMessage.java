package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Error Messages. */
public class ErrorMessage extends ComponentValidator {

  @FindBy(id = "modal-message")
  WebElement error;

  public ErrorMessage(WebDriver driver) {
    super(driver);
  }

  /**
   * Error pop-up verification.
   *
   * @return Boolean Value
   */
  public boolean isErrorMessageDisplayed() {
    boolean flag = false;
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("modal-message")));
    if (error.isDisplayed()) {
      flag = true;
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(error));
    return error.isDisplayed();
  }
}

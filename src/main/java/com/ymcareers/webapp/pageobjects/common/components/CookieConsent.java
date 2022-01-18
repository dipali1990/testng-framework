package com.ymcareers.webapp.pageobjects.common.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class has WebElements for Cookie Consent which displays when user navigates to the career
 * site.
 */
public class CookieConsent extends ComponentValidator {

  @FindBy(xpath = "//button[@class='secondary']")
  WebElement cookieAcceptanceButton;

  public CookieConsent(WebDriver driver) {
    super(driver);
  }

  /** This method accepts the cookie consent. */
  public void setCookieConsent() {
    try{
      if (cookieAcceptanceButton.isDisplayed()) {
        cookieAcceptanceButton.click();
        wait.until(ExpectedConditions.invisibilityOf(cookieAcceptanceButton));
      }
    }catch (NoSuchElementException e){
      log.info("CookieAcceptance pop up not displayed");
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(cookieAcceptanceButton));
    return cookieAcceptanceButton.isDisplayed();
  }
}

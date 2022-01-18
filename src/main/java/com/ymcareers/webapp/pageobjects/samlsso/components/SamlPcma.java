package com.ymcareers.webapp.pageobjects.samlsso.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for SAML login for PCMA site. */
public class SamlPcma extends ComponentValidator {
  @FindBy(id = "51:2;a")
  WebElement username;

  @FindBy(id = "63:2;a")
  WebElement password;

  @FindBy(xpath = "//span[text()='Sign In']")
  WebElement signInButton;

  public SamlPcma(WebDriver driver) {
    super(driver);
  }

  /**
   * Method to login to PCMA site (site id = 518).
   *
   * @param email Username
   * @param pwd Password
   */
  public void samlLogin(String email, String pwd) {
    wait.until(ExpectedConditions.visibilityOf(username));
    username.clear();
    username.sendKeys(email);
    log.info("Userid is entered as " + email);
    wait.until(ExpectedConditions.visibilityOf(password));
    password.clear();
    password.sendKeys(pwd);
    log.info("Password is entered ");
    signInButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(signInButton));
    return signInButton.isDisplayed();
  }
}

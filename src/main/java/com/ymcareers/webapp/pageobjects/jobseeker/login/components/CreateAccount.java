package com.ymcareers.webapp.pageobjects.jobseeker.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for Job Seeker Create a new account Panel. */
public class CreateAccount extends ComponentValidator {

  @FindBy(id = "first_name")
  WebElement firstName;

  @FindBy(id = "last_name")
  WebElement lastName;

  @FindBy(id = "email")
  WebElement emailField;

  @FindBy(id = "password")
  WebElement password;

  @FindBy(id = "terms_1")
  WebElement userAgreement;

  @FindBy(id = "privacy_1")
  WebElement privacyPolicy;

  @FindBy(id = "new-account-button")
  WebElement createMyAccount;

  public CreateAccount(WebDriver driver) {
    super(driver);
  }

  WebActions actions = new WebActions();

  /**
   * Enters Fist Name, Last Name and Email and Password and Submits form.
   *
   * @param pwd Password
   * @return First Name
   */
  public String createAccount(String pwd) {
    String name = actions.generateRandomName();
    firstName.sendKeys(name + "_test");
    log.info("First Name : " + name);
    lastName.sendKeys(name);
    log.info("Last Name : " + name);
    String email = actions.generateEmail(name);
    emailField.sendKeys(email);
    log.info("Email : " + email);
    password.sendKeys(pwd);
    log.info("Password : " + pwd);
    userAgreement.click();
    privacyPolicy.click();
    createMyAccount.click();
    log.info("Clicked 'Create my account' Button");
    return name + "_test";
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(firstName));
    return firstName.isDisplayed();
  }
}

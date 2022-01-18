package com.ymcareers.webapp.pageobjects.employer.login.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for Job Seeker Create a new account Panel. */
public class CreateAccount extends ComponentValidator {

  @FindBy(name = "first_name")
  WebElement firstName;

  @FindBy(name = "last_name")
  WebElement lastName;

  @FindBy(name = "title")
  WebElement jobTitle;

  @FindBy(name = "email")
  WebElement email;

  @FindBy(name = "email_confirm")
  WebElement emailConfirm;

  @FindBy(name = "phone")
  WebElement phone;

  @FindBy(name = "company")
  WebElement companyName;

  @FindBy(name = "address")
  WebElement address;

  @FindBy(name = "city")
  WebElement cityField;

  @FindBy(name = "state_select")
  WebElement stateDropdown;

  @FindBy(name = "zip")
  WebElement zip;

  @FindBy(name = "country")
  WebElement countryDropdown;

  @FindBy(name = "password")
  WebElement password;

  @FindBy(name = "password_confirm")
  WebElement passwordConfirm;

  @FindBy(name = "terms")
  WebElement usageAgreementCheckbox;

  @FindBy(name = "privacy_policy")
  WebElement privacyPolicyCheckbox;

  @FindBy(xpath = "//button[@tabindex='10']")
  WebElement createAccountButton;

  @FindBy(xpath = "//*[@id=\"register_form\"]/div/div[1]/div/table[2]/tbody/tr[2]/td")
  WebElement orgTypeRadioButton;

  public CreateAccount(WebDriver driver) {
    super(driver);
  }

  WebActions actions = new WebActions();
  String name = actions.generateRandomName();

  /**
   * Enters Employer's Personal Information.
   *
   * @return First Name
   */
  public String enterPersonalInformation() {
    firstName.sendKeys(name + "_Test");
    lastName.sendKeys(name);
    log.info("Last Name : " + name);
    jobTitle.sendKeys(actions.generateJobTitle());
    String emailId = actions.generateEmail(name);
    email.sendKeys(emailId);
    log.info("Email : " + email);
    emailConfirm.sendKeys(emailId);
    phone.sendKeys(actions.generateRandomNumber());
    return name + "_Test";
  }

  /**
   * Method Enters Company Information.
   *
   * @param orgType Organization Type
   * @param compAddress Company Address
   * @param city City Name
   * @param state State
   * @param zipcode Zip Code
   * @param country Country
   */
  public void enterCompanyInformation(
      String orgType,
      String compAddress,
      String city,
      String state,
      String zipcode,
      String country) {
    companyName.sendKeys(name);
    actions.selectRadioButton(orgTypeRadioButton, orgType);
    address.sendKeys(compAddress);
    cityField.sendKeys(city);
    actions.selectDropdownOption(stateDropdown, state);
    zip.sendKeys(zipcode);
    actions.selectDropdownOption(countryDropdown, country);
    log.info(
        "Entered Company Name : "
            + name
            + " Address : "
            + compAddress
            + " City : "
            + city
            + " State : "
            + state
            + " ZipCode : "
            + zipcode
            + " Country : "
            + country);
  }

  /**
   * Enters Password and Submits form.
   *
   * @param pwd Password
   */
  public void createAccount(String pwd) {
    password.sendKeys(pwd);
    passwordConfirm.sendKeys(pwd);
    usageAgreementCheckbox.click();
    privacyPolicyCheckbox.click();
    createAccountButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(firstName));
    return firstName.isDisplayed();
  }
}

package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class contains WebElements for the Contact Information Section on the Resume Builder Page.
 */
public class ContactInformationSection extends ComponentValidator {

  @FindBy(name = "contact_name_first")
  WebElement firstContactName;

  @FindBy(name = "contact_name_last")
  WebElement lastContactName;

  @FindBy(name = "contact_email")
  WebElement emailAddress;

  String attribute = "value";

  WebActions webActions = new WebActions();
  String randomName = webActions.generateRandomName();

  public ContactInformationSection(WebDriver driver) {
    super(driver);
  }

  /** Method enters mandatory field in Contact Details section. */
  public void enterContactInformation(String firstName, String lastName, String email) {
    if (firstContactName.getAttribute(attribute).equals("")) {
      firstContactName.sendKeys(firstName + randomName);
      log.info("Job Seeker has entered first name : " + firstName + randomName);
    } else {
      log.info("First Name is already present : " + firstContactName.getAttribute(attribute));
    }
    if (lastContactName.getAttribute(attribute).equals("")) {
      lastContactName.sendKeys(lastName + randomName);
      log.info("Job Seeker has entered last name : " + lastName + randomName);
    } else {
      log.info("Last Name is already present : " + lastContactName.getAttribute(attribute));
    }
    if (emailAddress.getAttribute(attribute).equals("")) {
      emailAddress.sendKeys(email);
      log.info("Job Seeker has entered email id : " + email);
    } else {
      log.info("Email id is already present : " + emailAddress.getAttribute(attribute));
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(firstContactName));
    return firstContactName.isDisplayed();
  }
}
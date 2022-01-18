package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for 'Contact Information' Section. */
public class ContactInformation extends ComponentValidator {

  @FindBy(id = "q65")
  WebElement contactPerson;

  @FindBy(id = "q66")
  WebElement emailAddress;

  public ContactInformation(WebDriver driver) {
    super(driver);
  }

  /**
   * Method enters contact information.
   *
   * @param contact Contact
   * @param email Email Id
   */
  public void setContactInformation(String contact, String email) {
    log.info("*********** Contact Information Section **********");
    contactPerson.sendKeys(contact);
    log.info("Entered Contact Person : " + contact);
    emailAddress.sendKeys(email);
    log.info("Entered Email : " + email);
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(contactPerson));
    return contactPerson.isDisplayed();
  }
}

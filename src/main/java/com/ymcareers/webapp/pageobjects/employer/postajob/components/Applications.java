package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for 'Applications' Section. */
public class Applications extends ComponentValidator {

  @FindBy(xpath = "//input[@name='recipients']")
  WebElement recipients;

  @FindBy(name = "recipient_first_name")
  WebElement recipientFirstName;

  @FindBy(name = "recipient_last_name")
  WebElement recipientLastName;

  @FindBy(name = "recipient_email")
  WebElement recipientEmail;

  @FindBy(name = "recipient_button")
  WebElement recipientButton;

  @FindBy(name = "post")
  WebElement continueButton;

  public Applications(WebDriver driver) {
    super(driver);
  }

  /**
   * Method to enter contact details.
   *
   * @param firstName Recipient's First Name
   * @param lastName Recipient's Last Name
   * @param email Recipient's Email
   */
  public void selectOnlineApplication(String firstName, String lastName, String email) {
    log.info("*********** Application Section **********");
    if (recipients.isDisplayed()) {
      recipients.click();
      log.info("Existing Contact Selected");
    } else {
      log.info("There is no Existing Contact");
      recipientFirstName.sendKeys(firstName);
      log.info("Entered First Name : " + firstName);
      recipientLastName.sendKeys(lastName);
      log.info("Entered Last Name : " + lastName);
      recipientEmail.sendKeys(email);
      log.info("Entered Email : " + email);
      recipientButton.click();
    }
  }

  public void submit() {
    continueButton.click();
    log.info("Clicked Continue Button");
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(recipients));
    return recipients.isDisplayed();
  }
}

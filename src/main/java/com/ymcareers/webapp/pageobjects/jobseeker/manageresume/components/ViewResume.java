package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Public Resume Section on the Manage Resume Page. */
public class ViewResume extends ComponentValidator {

  @FindBy(xpath = "//a[text()='Edit']")
  WebElement edit;

  @FindBy(xpath = "//a[text()='Print']")
  WebElement print;

  @FindBy(xpath = "//a[text()='Email']")
  WebElement emailLink;

  @FindBy(name = "contact_name_first")
  WebElement firstContactName;

  @FindBy(id = "button-input")
  WebElement saveMyResumeButton;

  @FindBy(name = "email_resume")
  WebElement emailModal;

  @FindBy(id = "emailTo")
  WebElement emailField;

  @FindBy(name = "send_email")
  WebElement sendButton;

  WebActions webActions = new WebActions();

  public ViewResume(WebDriver driver) {
    super(driver);
  }

  /** Method clicks on 'Edit' option. */
  public void editResume() {
    edit.click();
    log.info("Clicked on 'Edit' option available on View Resume Page");
    wait.until(ExpectedConditions.visibilityOf(firstContactName));
    firstContactName.clear();
    String newFirstName = webActions.generateRandomName();
    firstContactName.sendKeys(newFirstName);
    log.info("New First Name: " + newFirstName);
    saveMyResumeButton.click();
    log.info("Clicked on 'Save My Resume' Button");
  }

  /**
   * Method will send the resume via email.
   *
   * @param recipient email address of the recipient
   */
  public void emailResume(String recipient) {
    emailLink.click();
    log.info("Clicked on 'Email' link available on View Resume Page");
    wait.until(ExpectedConditions.visibilityOf(emailModal));
    emailField.sendKeys(recipient);
    log.info("Entered Email id in the Email Pop up. Email Id: " + recipient);
    sendButton.click();
    log.info("Clicked on Send Button");
  }

  public boolean printResume() {
    return print.isEnabled();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(edit));
    return edit.isDisplayed();
  }
}

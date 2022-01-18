package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Private Resume Section on the Manage Resume Page. */
public class PrivateResume extends ComponentValidator {

  @FindBy(xpath = "//table[@class='private-resumes']//a[text()='View']")
  WebElement privateViewOption;

  @FindBy(xpath = "//section[@class='details ']")
  WebElement detailsText;

  @FindBy(xpath = "//table[@class='private-resumes']//a[text()='Edit']")
  WebElement editLink;

  @FindBy(name = "contact_name_first")
  WebElement firstContactName;

  @FindBy(id = "button-input")
  WebElement saveMyResumeButton;

  @FindBy(xpath = "//table[@class='private-resumes']//a[text()='Email']")
  WebElement emailLink;

  @FindBy(id = "email_resume")
  WebElement emailModal;

  @FindBy(id = "emailTo")
  WebElement emailField;

  @FindBy(name = "send_email")
  WebElement sendButton;

  @FindBy(xpath = "//table[@class='private-resumes']//a[text()='Link']")
  WebElement link;

  @FindBy(xpath = "//p[@id='resume_link']//child::a")
  WebElement resumeLink;

  @FindBy(xpath = "//table[@class='private-resumes']//a[text()='Free Evaluation']")
  WebElement privateFreeEvaluationOption;

  @FindBy(name = "submit_evaluate")
  WebElement evaluateMyResume;

  @FindBy(xpath = "//table[@class='private-resumes']//a[text()='Publish']")
  WebElement publishLink;

  @FindBy(name = "activate")
  WebElement publishButton;

  @FindBy(xpath = "//table[@class='private-resumes']//a[text()='Delete']")
  WebElement deleteLink;

  @FindBy(name = "delresume")
  WebElement deleteButton;

  WebActions webActions = new WebActions();

  public PrivateResume(WebDriver driver) {
    super(driver);
  }

  /**
   * Method to verifies 'View' option of Private Resume.
   *
   * @return boolean
   */
  public boolean verifyViewPrivateResumeOption() {
    privateViewOption.click();
    log.info("Clicked on 'View' of Private Resume");
    wait.until(ExpectedConditions.visibilityOf(detailsText));
    return detailsText.isDisplayed();
  }

  /** Method clicks on 'Edit' option. */
  public void editPrivateResumeOption() {
    editLink.click();
    log.info("Clicked on 'Edit' option of Private Resume");
    wait.until(ExpectedConditions.visibilityOf(firstContactName));
    firstContactName.clear();
    String newFirstName = webActions.generateRandomName();
    firstContactName.sendKeys(newFirstName);
    log.info("New First Name: " + newFirstName);
    saveMyResumeButton.click();
    log.info("Clicked on 'Save My Resume' Button");
  }

  /**
   * Method will send the private resume via email.
   *
   * @param recipient email address of the recipient
   */
  public void emailPrivateResumeOption(String recipient) {
    emailLink.click();
    log.info("Clicked on 'Email' of Private Resume");
    wait.until(ExpectedConditions.visibilityOf(emailModal));
    emailField.sendKeys(recipient);
    log.info("Entered Email id in the Email Pop up. Email Id: " + recipient);
    sendButton.click();
    log.info("Clicked on Send Button");
  }

  /**
   * Method generates private resume link.
   *
   * @return Resume Link
   */
  public String linkPrivateResumeOption() {
    link.click();
    log.info("Clicked on 'Link' option");
    wait.until(ExpectedConditions.visibilityOf(resumeLink));
    return resumeLink.getText();
  }

  /** Method clicks on 'Publish' option. */
  public void publishPrivateResumeOption() {
    publishLink.click();
    log.info("Clicked on 'Publish' Option");
    wait.until(ExpectedConditions.visibilityOf(publishButton));
    publishButton.click();
    log.info("Confirmation Pop up displayed and Clicked on 'Publish My Resume' Button");
  }

  /** Clicked on 'Free Evaluation' Option. */
  public void freeEvaluationOptionPrivateResume() {
    privateFreeEvaluationOption.click();
    log.info("Clicked on 'Free Evaluation' Option of Private Resume");
    wait.until(ExpectedConditions.visibilityOf(evaluateMyResume));
    log.info("Top Resume Pop up displayed.");
    evaluateMyResume.click();
    log.info("Clicked on 'Evaluate My Resume' button");
  }

  /** Method deletes the private resume. */
  public void deletePrivateResumeOption() {
    deleteLink.click();
    log.info("Clicked on 'Delete' option of Private Resume");
    wait.until(ExpectedConditions.visibilityOf(deleteButton));
    deleteButton.click();
    log.info("Confirmation Pop up displayed and clicked on 'Delete' Button");
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(privateViewOption));
    return privateViewOption.isDisplayed();
  }
}

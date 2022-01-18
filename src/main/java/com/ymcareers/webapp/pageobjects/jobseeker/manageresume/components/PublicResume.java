package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Public Resume Section on the Manage Resume Page. */
public class PublicResume extends ComponentValidator {

  @FindBy(xpath = "//table[@class='public-resumes']//a[text()='View']")
  WebElement publicViewOption;

  @FindBy(xpath = "//section[@class='details ']")
  WebElement detailsText;

  @FindBy(xpath = "//table[@class='public-resumes']//a[text()='Edit']")
  WebElement editLink;

  @FindBy(name = "contact_name_first")
  WebElement firstContactName;

  @FindBy(id = "button-input")
  WebElement saveMyResumeButton;

  @FindBy(xpath = "//table[@class='public-resumes']//a[text()='Email']")
  WebElement emailLink;

  @FindBy(id = "email_resume")
  WebElement emailModal;

  @FindBy(id = "emailTo")
  WebElement emailField;

  @FindBy(name = "send_email")
  WebElement sendButton;

  @FindBy(xpath = "//table[@class='public-resumes']//a[text()='Link']")
  WebElement link;

  @FindBy(xpath = "//p[@id='resume_link']//child::a")
  WebElement resumeLink;

  @FindBy(xpath = "//table[@class='public-resumes']//a[text()='Free Evaluation']")
  WebElement publicFreeEvaluationOption;

  @FindBy(name = "submit_evaluate")
  WebElement evaluateMyResume;

  @FindBy(xpath = "//table[@class='public-resumes']//a[text()='Unpublish']")
  WebElement unpublishLink;

  @FindBy(name = "deactivate")
  WebElement unpublishButton;

  @FindBy(xpath = "//table[@class='public-resumes']//a[text()='Delete']")
  WebElement deleteLink;

  @FindBy(name = "delresume")
  WebElement deleteButton;

  WebActions webActions = new WebActions();

  public PublicResume(WebDriver driver) {
    super(driver);
  }

  /**
   * Method to verifies 'View' option of Public Resume.
   *
   * @return boolean
   */
  public boolean verifyViewPublicResumeOption() {
    publicViewOption.click();
    log.info("Clicked on 'View' of Public Resume");
    wait.until(ExpectedConditions.visibilityOf(detailsText));
    return detailsText.isDisplayed();
  }

  /** Method clicks on 'Edit' option. */
  public void editPublicResumeOption() {
    editLink.click();
    log.info("Clicked on 'Edit' option of Public Resume");
    wait.until(ExpectedConditions.visibilityOf(firstContactName));
    firstContactName.clear();
    String newFirstName = webActions.generateRandomName();
    firstContactName.sendKeys(newFirstName);
    log.info("New First Name: " + newFirstName);
    saveMyResumeButton.click();
    log.info("Clicked on 'Save My Resume' Button");
  }

  /**
   * Method will send the Public resume via email.
   *
   * @param recipient email address of the recipient
   */
  public void emailPublicResumeOption(String recipient) {
    emailLink.click();
    log.info("Clicked on 'Email' of Public Resume");
    wait.until(ExpectedConditions.visibilityOf(emailModal));
    emailField.sendKeys(recipient);
    log.info("Entered Email id in the Email Pop up. Email Id: " + recipient);
    sendButton.click();
    log.info("Clicked on Send Button");
  }

  /**
   * Method generates public resume link.
   *
   * @return Resume Link
   */
  public String linkPublicResumeOption() {
    link.click();
    log.info("Clicked on 'Link' option");
    wait.until(ExpectedConditions.visibilityOf(resumeLink));
    return resumeLink.getText();
  }

  /** Method clicks on 'Unpublish' option. */
  public void unpublishPublicResumeOption() {
    unpublishLink.click();
    log.info("Clicked on 'Unpublish' Option");
    wait.until(ExpectedConditions.visibilityOf(unpublishButton));
    unpublishButton.click();
    log.info("Confirmation Pop up displayed and Clicked on 'Unpublish My Resume' Button");
  }

  /** Clicked on 'Free Evaluation' Option. */
  public void freeEvaluationOptionPublicResume() {
    publicFreeEvaluationOption.click();
    log.info("Clicked on 'Free Evaluation' Option of Public Resume");
    wait.until(ExpectedConditions.visibilityOf(evaluateMyResume));
    log.info("Top Resume Pop up displayed.");
    evaluateMyResume.click();
    log.info("Clicked on 'Evaluate My Resume' button");
  }

  /** Method deletes the public resume. */
  public void deletePublicResumeOption() {
    deleteLink.click();
    log.info("Clicked on 'Delete' option of Public Resume");
    wait.until(ExpectedConditions.visibilityOf(deleteButton));
    deleteButton.click();
    log.info("Confirmation Pop up displayed and clicked on 'Delete' Button");
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(publicViewOption));
    return publicViewOption.isDisplayed();
  }
}

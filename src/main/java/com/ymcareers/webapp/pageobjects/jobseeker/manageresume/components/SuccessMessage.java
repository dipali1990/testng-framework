package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class contains WebElements for the Contact Information Section on the Resume Builder Page.
 */
public class SuccessMessage extends ComponentValidator {

  @FindBy(xpath = "//*[text()=' Your resume was saved.']")
  WebElement savedResumeMsg;

  @FindBy(xpath = "//div[@class='alert warning']")
  WebElement messageBox;

  @FindBy(xpath = "//div[@class='alert error fa fa-exclamation-circle']")
  WebElement errorMessage;

  public SuccessMessage(WebDriver driver) {
    super(driver);
  }

  /**
   * Method will verify if resume saved successfully.
   *
   * @return boolean value
   */
  public boolean verifyResumeSaved() {
    boolean flag = false;
    try {
      if (savedResumeMsg.isDisplayed()) {
        flag = true;
      }
    } catch (Exception e) {
      log.info("Resume could not be saved");
    }
    return flag;
  }

  /**
   * Method verifies Resume sent.
   *
   * @param email recipient's email address
   * @return boolean
   */
  public boolean verifyResumeSent(String email) {
    boolean flag = false;
    try {
      if (messageBox.isDisplayed()) {
        String msg = messageBox.getText();
        if (msg.contains(email)) {
          flag = true;
          log.info(msg);
        }
      }
    } catch (Exception e) {
      log.info("Resume could not be sent");
    }
    return flag;
  }

  /**
   * Verifies Success Message.
   *
   * @return Message content
   */
  public String verifyMessage() {
    String msg = "";
    try {
      if (messageBox.isDisplayed()) {
        msg = messageBox.getText();
        log.info(msg);
      }
    } catch (Exception e) {
      msg = errorMessage.getText();
    }
    return msg;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(savedResumeMsg));
    return savedResumeMsg.isDisplayed();
  }
}

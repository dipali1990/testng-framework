package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for Job Apply Page. */
public class JobApply extends ComponentValidator {

  @FindBy(xpath = "//div[contains(@class,'inner')]//child::h1")
  WebElement jobTitle;

  @FindBy(id = "applyBtn")
  WebElement applyNowButton;

  @FindBy(xpath = "//b[text()='Job ID:']")
  WebElement jobId;

  @FindBy(xpath = "//ul[@role='tablist']")
  WebElement jobBoardTabs;

  @FindBy(xpath = "//a[@class='secondary ga_int']")
  WebElement jobBoardButton;

  @FindBy(id = "apply_email_button")
  WebElement emailButton;

  @FindBy(xpath = "//span[contains(text(),'sent')]")
  WebElement emailSent;

  @FindBy(xpath = "//a[@role='button' and @href='#next']")
  WebElement submitButton;

  @FindBy(xpath = "//fieldset[@class='finish body current']//child::legend")
  WebElement successMessage;

  @FindBy(id = "apply_user_resume")
  WebElement resumeDropdownOnPopup;

  @FindBy(id = "send_apply_email")
  WebElement sendEmailButton;

  WebDriver driver;
  WebActions webActions = new WebActions();

  public JobApply(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * Method checks Apply Now button is enabled and call Apply Jobs methods accordingly.
   *
   * @param applyOption Apply Method Email or Job Board
   * @return Message
   */
  public String clickApplyNow(String applyOption) {
    log.info("Job Name : " + jobTitle.getText());
    log.info("Job Id : " + jobId.getText());
    String msg;
    try {
      wait.until(ExpectedConditions.elementToBeClickable(applyNowButton));
      applyNowButton.click();
      msg = handleApplyOptions(applyOption);
    } catch (Exception e) {
      msg = "Applied";
      log.info("Job Seeker has already applied to this job");
    }
    return msg;
  }

  /**
   * Method handles Apply Option.
   *
   * @param applyOption Apply Method Email or Job Board
   * @return Message
   */
  public String handleApplyOptions(String applyOption) {
    String msg = "";
    try {
      wait.until(ExpectedConditions.visibilityOf(jobBoardButton));
      if (applyOption.equalsIgnoreCase("Job Board")) {
        log.info("Pop up displayed after clicking on 'Apply Now' button");
        jobBoardButton.click();
        log.info("'Job Board' option selected");
        driver.switchTo().window("newwindow");
        msg = jobBoardApply();
      } else {
        msg = emailApply();
      }
    } catch (NoSuchElementException e) {
      log.info("Job board pop up not displayed instead new window opened");
      String parent = driver.getWindowHandle();
      Set<String> availableWindows = driver.getWindowHandles();
      String newWindow = null;
      for (String window : availableWindows) {
        if (!parent.equals(window)) {
          newWindow = window;
        }
      }
      driver.switchTo().window(newWindow);
    }
    return msg;
  }

  /**
   * Method Handles Job Board Apply.
   *
   * @return Message
   */
  public String jobBoardApply() {
    String msg = "";
    List<WebElement> jobBoardApplyOptions = jobBoardTabs.findElements(By.tagName("li"));
    for (int i = 0; i < jobBoardApplyOptions.size() - 1; i++) {
      wait.until(ExpectedConditions.visibilityOf(submitButton));
      webActions.scrollVertical();
      submitButton.click();
    }
    try {
      if (successMessage.isDisplayed()) {
        msg = successMessage.getText();
      }
    } catch (Exception e) {
      log.info("Success message could not be displayed");
    }
    return msg;
  }

  /**
   * Method handles Email Apply Method.
   *
   * @return Message
   */
  public String emailApply() {
    emailButton.click();
    try {
      webActions.scrollToElement(resumeDropdownOnPopup);
      webActions.selectDropdownOption(resumeDropdownOnPopup, "");
    } catch (NoSuchElementException e) {
      log.info("Resume is not available");
    }
    log.info("Resume selected from the dropdown");
    sendEmailButton.click();
    wait.until(ExpectedConditions.visibilityOf(emailSent));
    return emailSent.getText();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(applyNowButton));
    return applyNowButton.isDisplayed();
  }
}

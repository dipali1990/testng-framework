package com.ymcareers.webapp.pageobjects.employer.resumebank.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for tooltip displayed on the Resume Bank. */
public class ResumeResult extends ComponentValidator {

  @FindBy(id = "filterBtn")
  WebElement filterButton;

  @FindBy(xpath = "//div[@class='col alert-secondary alert']")
  WebElement noResult;

  @FindBy(xpath = "//div[@class='body-title-sm num-result']")
  WebElement result;

  @FindBy(xpath = "//a[contains(@id,'can-')][1]")
  WebElement viewCandidate;

  public ResumeResult(WebDriver driver) {
    super(driver);
  }

  /**
   * Method gets the result after filter is applied.
   *
   * @return Result
   */
  public String getResult() {
    String msg = "";
    try {
      if (noResult.isDisplayed()) {
        msg = noResult.getText();
        log.info(msg);
      }
    } catch (NoSuchElementException exception) {
      msg = result.getText();
      log.info("Total Resumes found : " + msg);
    }
    return msg;
  }

  /**
   * Method selects 'View Candidate' if resume is available on the Resume Bank Page.
   *
   * @return Boolean Value
   */
  public boolean viewResume() {
    boolean flag = false;
    try {
      if (noResult.isDisplayed()) {
        log.info("There are no results to be displayed in this site");
      }
    } catch (Exception e) {
      wait.until(ExpectedConditions.visibilityOf(viewCandidate));
      viewCandidate.click();
      flag = true;
      log.info("Clicked on 'View Candidate' on the Resume Search Result Page");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(filterButton));
    return filterButton.isDisplayed();
  }
}

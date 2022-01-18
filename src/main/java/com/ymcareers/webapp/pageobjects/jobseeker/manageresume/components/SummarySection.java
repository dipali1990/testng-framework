package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for Summary Section available on the Resume Builder Page. */
public class SummarySection extends ComponentValidator {

  @FindBy(name = "overview_summary")
  WebElement summaryField;

  @FindBy(name = "overview_objective")
  WebElement resumeObjective;

  public SummarySection(WebDriver driver) {
    super(driver);
  }

  /**
   * Method is used to enter summary details in the resume builder page.
   *
   * @param summary Summary
   * @param obj Objective
   */
  public void enterSummary(String summary, String obj) {
    if (summaryField.getText().equals("")) {
      summaryField.sendKeys(summary);
      log.info("Summary has been entered : " + summary);
    } else {
      log.info("Summary is already present : " + summaryField.getText());
    }
    if (resumeObjective.getText().equals("")) {
      resumeObjective.sendKeys(obj);
      log.info("Objective has been entered : " + obj);
    } else {
      log.info("Objective is already present : " + resumeObjective.getText());
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(summaryField));
    return summaryField.isDisplayed();
  }
}

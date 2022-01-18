package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Job Alert Popup. */
public class JobAlertPopUp extends ComponentValidator {

  @FindBy(xpath = "//div[@class='listings']")
  WebElement jobSearchResults;

  @FindBy(xpath = "//button[@id=\"ga_create_alert\"]//following::a[1]")
  WebElement jobAlertCancel;

  public JobAlertPopUp(WebDriver driver) {
    super(driver);
  }

  /** Method closes job alert popup. */
  public void handleJobAlertPopUp() {
    try {
      Thread.sleep(500);
      jobAlertCancel.click();
      wait.until(ExpectedConditions.invisibilityOf(jobAlertCancel));
    } catch (Exception e) {
      log.info("Job alert pop up not displayed");
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jobSearchResults));
    return jobSearchResults.isDisplayed();
  }
}

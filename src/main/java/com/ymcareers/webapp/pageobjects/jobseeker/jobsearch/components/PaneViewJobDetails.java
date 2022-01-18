package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for Job Details Page. */
public class PaneViewJobDetails extends ComponentValidator {

  @FindBy(id = "applyBtn")
  WebElement applyNowButton;

  @FindBy(xpath = "//div[contains(@class,'job-desc')]")
  WebElement descriptionTitle;

  public PaneViewJobDetails(WebDriver driver) {
    super(driver);
  }

  /**
   * Method Verifies if 'Apply Now' button is Enabled.
   *
   * @return Boolean
   */
  public boolean verifyApplyNowIsEnabled() {
    boolean flag = false;
    try {
      applyNowButton.isDisplayed();
      flag = true;
    } catch (NoSuchElementException e) {
      log.info("Job is already applied");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(descriptionTitle));
    return descriptionTitle.isDisplayed();
  }
}

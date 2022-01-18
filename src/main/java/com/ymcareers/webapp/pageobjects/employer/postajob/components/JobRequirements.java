package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for 'Job Requirements' Section. */
public class JobRequirements extends ComponentValidator {

  @FindBy(id = "job_requirements")
  WebElement jobReq;

  WebDriver driver;

  public JobRequirements(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * Enters Job Requirements.
   *
   * @param req Job Requirements
   */
  public void setJobRequirement(String req) {
    log.info("*********** Job Requirements Section **********");
    driver.switchTo().frame("mce_edit_requirements_ifr");
    jobReq.sendKeys(req);
    log.info("Entered Job Requirements : " + req);
    driver.switchTo().parentFrame();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jobReq));
    return jobReq.isDisplayed();
  }
}

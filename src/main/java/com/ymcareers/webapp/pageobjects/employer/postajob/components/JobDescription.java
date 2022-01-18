package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for 'Job Description' Section. */
public class JobDescription extends ComponentValidator {

  @FindBy(id = "job_description")
  WebElement jobDesc;

  WebDriver driver;

  public JobDescription(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * Enters Job Description.
   *
   * @param desc Job Description
   */
  public void setJobDescription(String desc) {
    log.info("*********** Job Description Section **********");
    driver.switchTo().frame("mce_edit_description_ifr");
    jobDesc.sendKeys(desc);
    log.info("Entered Job Description : " + desc);
    driver.switchTo().parentFrame();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jobDesc));
    return jobDesc.isDisplayed();
  }
}

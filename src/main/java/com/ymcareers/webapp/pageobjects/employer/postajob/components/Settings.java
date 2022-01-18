package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for 'Settings' Section. */
public class Settings extends ComponentValidator {

  @FindBy(id = "q9")
  WebElement jobName;

  WebActions webActions = new WebActions();
  private final String name = webActions.generateJobTitle();

  public Settings(WebDriver driver) {
    super(driver);
  }

  /**
   * Entered Job Name.
   *
   * @return Name of the Job
   */
  public String setJobName() {
    log.info("*********** Job Name Section **********");
    jobName.sendKeys(name);
    log.info("Entered Job Name : " + name);
    return name;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jobName));
    return jobName.isDisplayed();
  }
}

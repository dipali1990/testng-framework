package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for 'Job Details' Section. */
public class JobDetails extends ComponentValidator {

  @FindBy(id = "q60")
  WebElement jobDuration;

  @FindBy(id = "q61")
  WebElement requiredTravel;

  @FindBy(id = "q62")
  WebElement minEducation;

  @FindBy(id = "q63")
  WebElement minExperience;

  @FindBy(id = "q3868")
  WebElement fromSalary;

  @FindBy(id = "q3869")
  WebElement toSalary;

  @FindBy(name = "q3870")
  WebElement salaryType;

  WebActions webActions = new WebActions();

  public JobDetails(WebDriver driver) {
    super(driver);
  }

  /**
   * Enters Job Details.
   *
   * @param from Salary Range From
   * @param to Salary Range To
   */
  public void setJobDetails(String from, String to) {
    log.info("*********** Job Details Section **********");
    webActions.selectDropdownOption(jobDuration, "");
    log.info("Selected Job Duration");
    webActions.selectDropdownOption(requiredTravel, "");
    log.info("Selected Required Travel");
    webActions.selectDropdownOption(minEducation, "");
    log.info("Selected Minimum Education");
    webActions.selectDropdownOption(minExperience, "");
    log.info("Selected Minimum Experience");
    fromSalary.sendKeys(from);
    log.info("Entered From Salary : " + from);
    toSalary.sendKeys(to);
    log.info("Entered To Salary : " + to);
    webActions.selectDropdownOption(salaryType, "");
    log.info("Selected Salary Type");
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jobDuration));
    return jobDuration.isDisplayed();
  }
}

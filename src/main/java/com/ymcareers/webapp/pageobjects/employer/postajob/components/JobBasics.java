package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for 'Job Basics' Section. */
public class JobBasics extends ComponentValidator {

  @FindBy(id = "q23")
  WebElement profileName;

  @FindBy(id = "q58923")
  WebElement companyName;

  @FindBy(id = "q24")
  WebElement positionTitle;

  @FindBy(id = "q10")
  WebElement jobFunction;

  @FindBy(xpath = "//label[contains(@id,'51864') and text()='No']")
  WebElement attachLogoNo;

  WebActions webActions = new WebActions();

  public JobBasics(WebDriver driver) {
    super(driver);
  }

  /**
   * Enters Company Name.
   *
   * @param company Company Name
   */
  public void setCompanyName(String company) {
    log.info("*********** Job Basics Section **********");
    profileName.clear();
    profileName.sendKeys(company);
    companyName.clear();
    companyName.sendKeys(company);
    log.info("Entered Company Name : " + company);
  }

  public void setPositionTitle(String position) {
    positionTitle.sendKeys(position);
    log.info("Entered Position Title : " + position);
  }

  public void setJobFunction(String function) {
    webActions.selectDropdownOption(jobFunction, function);
    log.info("Entered Job Function : " + function);
  }

  /**
   * Method sets Company Logo.
   *
   * @param logo yes/no
   */
  public void setCompanyLogo(String logo) {
    if (logo.equalsIgnoreCase("Yes")) {
      log.info("Selected image as a company logo");
    } else {
      attachLogoNo.click();
      log.info("Selected 'No' option");
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(companyName));
    return companyName.isDisplayed();
  }
}

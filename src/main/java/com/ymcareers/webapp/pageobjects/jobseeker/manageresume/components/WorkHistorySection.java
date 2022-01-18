package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Work History Section on the Resume Builder Page. */
public class WorkHistorySection extends ComponentValidator {

  @FindBy(id = "work_employer1_company")
  WebElement employerName;

  @FindBy(id = "work_employer1_job1_position")
  WebElement positionTitle;

  @FindBy(id = "work_employer1_job1_start_month")
  WebElement startMonthJob1;

  @FindBy(id = "work_employer1_job1_start_year")
  WebElement startYearJob1;

  @FindBy(id = "work_employer1_job1_current")
  WebElement currentJobCheckbox;

  public WorkHistorySection(WebDriver driver) {
    super(driver);
  }

  /**
   * Method is used to enter work history details.
   *
   * @param company Employer Name
   * @param position Position Title
   * @param strMonth Start Month
   * @param strYear Start Year
   */
  public void enterContactInformation(
      String company, String position, String strMonth, String strYear) {
    String attribute = "value";
    if (employerName.getAttribute(attribute).equals("")) {
      employerName.sendKeys(company);
      log.info("Employer Name : " + company);
    } else {
      log.info("Employer Name is already present : " + employerName.getAttribute(attribute));
    }
    if (positionTitle.getAttribute(attribute).equals("")) {
      positionTitle.sendKeys(position);
      log.info("Position Title : " + position);
    } else {
      log.info("Position Title is already present : " + positionTitle.getAttribute(attribute));
    }
    if (startMonthJob1.getAttribute(attribute).equals("")) {
      startMonthJob1.sendKeys(strMonth);
      log.info("Start Month : " + startMonthJob1);
    } else {
      log.info("Start Month is already present : " + startMonthJob1);
    }
    if (startYearJob1.getAttribute(attribute).equals("")) {
      startYearJob1.sendKeys(strYear);
      log.info("Start Year : " + strYear);
    } else {
      log.info("Start year is already present : " + strYear);
    }
    currentJobCheckbox.click();
    log.info("'This is my current position' checkbox selected.");
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(employerName));
    return employerName.isDisplayed();
  }
}

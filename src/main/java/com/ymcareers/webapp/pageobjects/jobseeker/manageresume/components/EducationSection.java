package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Education Section on the Resume Builder Page. */
public class EducationSection extends ComponentValidator {

  @FindBy(name = "education_school1_name")
  WebElement institutionInputField;

  @FindBy(name = "education_school1_degree1_name")
  WebElement degree;

  @FindBy(name = "education_school1_degree1_level")
  WebElement educationCompletedDropdown;

  @FindBy(id = "education_school1_degree1_completed_month")
  WebElement educationCompletionMonth;

  @FindBy(id = "education_school1_degree1_completed_year")
  WebElement educationCompletionYear;

  @FindBy(id = "education_school2_remove_menu")
  WebElement removeSchool2Button;

  String attribute = "value";

  WebActions webActions = new WebActions();

  public EducationSection(WebDriver driver) {
    super(driver);
  }

  /**
   * Method enters School Details.
   *
   * @param school School Name
   */
  public void enterSchoolDetails(String school) {
    if (institutionInputField.getText().equals("")) {
      institutionInputField.sendKeys(school);
      log.info("School Name is entered : " + school);
    } else {
      log.info("School Name is already present : " + institutionInputField.getText());
    }
  }

  /**
   * Method is used to enter Degree details.
   *
   * @param degreeName Degree Name
   * @param month Completion Month
   * @param year Completion Year
   */
  public void enterDegreeDetails(String degreeName, String month, String year) {
    if (degree.getAttribute(attribute).equals("")) {
      degree.sendKeys(degreeName);
      log.info("Entered Degree Name : " + degreeName);
    } else {
      log.info("Degree Name is already present : " + degree.getAttribute(attribute));
    }
    if (educationCompletedDropdown.getAttribute(attribute).equals("")) {
      webActions.selectDropdownOption(educationCompletedDropdown, degreeName);
    } else {
      log.info("Degree Name is already present in the Education Name.");
    }
    if (educationCompletionMonth.getAttribute(attribute).equals("")) {
      webActions.selectDropdownOption(educationCompletionMonth, month);
    } else {
      log.info("Completion Month already present");
    }
    if (educationCompletionYear.getAttribute(attribute).equals("")) {
      webActions.selectDropdownOption(educationCompletionYear, year);
    } else {
      log.info("Completion Month already present");
    }
  }

  /** This method will remove extra school buttons. */
  public void removeExtraSchool() {
    try {
      removeSchool2Button.isDisplayed();
      removeSchool2Button.click();
    } catch (Exception e) {
      log.info("Extra school field not present.");
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(institutionInputField));
    return institutionInputField.isDisplayed();
  }
}

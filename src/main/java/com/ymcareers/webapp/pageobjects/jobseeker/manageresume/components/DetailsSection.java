package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Details Section on the Resume Builder Page. */
public class DetailsSection extends ComponentValidator {
  @FindBy(name = "details_highest_education")
  WebElement highestEducation;

  @FindBy(name = "details_career_level")
  WebElement careerLevelOption;

  @FindBy(xpath = "//legend[text()='Relocation']//parent::fieldset")
  WebElement relocationRadioButton;

  @FindBy(id = "button-input")
  WebElement submitButton;

  String attribute = "value";

  WebActions webActions = new WebActions();

  public DetailsSection(WebDriver driver) {
    super(driver);
  }

  /**
   * Method selects Higher Education and Career Level.
   *
   * @param degree Highest Education Level Completed
   * @param careerLevel Most Recent Career Level
   */
  public void enterDetailsSection(String degree, String careerLevel) {
    if (highestEducation.getAttribute(attribute).equals("")) {
      webActions.selectDropdownOption(highestEducation, degree);
    }
    if (careerLevelOption.getAttribute(attribute).equals("")) {
      webActions.selectDropdownOption(careerLevelOption, careerLevel);
    }
  }

  /**
   * Method selects relocation option.
   *
   * @param relocation Relocation Radio Button Option
   */
  public void selectRelocationOption(String relocation) {
    webActions.selectRadioButton(relocationRadioButton, relocation);
    log.info("Selected Relocation Option : " + relocation);
  }

  /** Method clicks on 'Submit' button. */
  public void submitResume() {
    submitButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(highestEducation));
    return highestEducation.isDisplayed();
  }
}

package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class contains WebElements for Current Location Section available on the Resume Builder
 * Page.
 */
public class CurrentLocationSection extends ComponentValidator {

  @FindBy(id = "location_country")
  WebElement countryDropdown;

  @FindBy(id = "location_state_select")
  WebElement stateDropdown;

  @FindBy(id = "location_city")
  WebElement cityInputField;

  @FindBy(xpath = "//legend[text()='Work Authorization']//parent::fieldset")
  WebElement workAuthorization;

  WebActions webActions = new WebActions();

  public CurrentLocationSection(WebDriver driver) {
    super(driver);
  }

  /**
   * Method selects current location.
   *
   * @param country Country
   * @param state State
   * @param city City
   * @param workAuth Work Authorization option
   */
  public void enterCurrentLocation(String country, String state, String city, String workAuth) {
    String attribute = "value";
    if (countryDropdown.getAttribute(attribute).equals("")) {
      webActions.selectDropdownOption(countryDropdown, country);
      log.info("Job Seeker has selected country : " + country);
    } else {
      log.info("Country is already entered : " + countryDropdown.getAttribute(attribute));
    }
    if (stateDropdown.getAttribute(attribute).equals("")) {
      webActions.selectDropdownOption(stateDropdown, state);
      log.info("Job Seeker has selected state : " + state);
    } else {
      log.info("State is already entered : " + stateDropdown.getAttribute(attribute));
    }
    if (cityInputField.getAttribute(attribute).equals("")) {
      cityInputField.sendKeys(city);
      log.info("Job Seeker has entered city : " + city);
    } else {
      log.info("City is already entered : " + cityInputField.getAttribute(attribute));
    }
    webActions.selectRadioButton(workAuthorization, workAuth);
    log.info("Job Seeker selected : " + workAuth);
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(countryDropdown));
    return countryDropdown.isDisplayed();
  }
}

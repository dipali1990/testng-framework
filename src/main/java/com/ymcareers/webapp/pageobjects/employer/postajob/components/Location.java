package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for 'Location' Section. */
public class Location extends ComponentValidator {

  @FindBy(id = "q59")
  WebElement city;

  @FindBy(name = "qstate")
  WebElement state;

  @FindBy(id = "q2225")
  WebElement zipCode;

  @FindBy(name = "qcountry")
  WebElement country;

  @FindBy(name = "qregion")
  WebElement region;

  @FindBy(name = "loc_button")
  WebElement addLocationButton;

  WebActions webActions = new WebActions();
  WebDriver driver;

  public Location(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * Method Enters Location Details.
   *
   * @param city City Name
   * @param state State Name
   * @param zipCode Zip Code
   * @param country Country Name
   */
  public void setLocation(String city, String state, String zipCode, String country) {
    log.info("*********** Location Section **********");
    webActions.selectDropdownOption(this.country, country);
    log.info("Selected Country : " + country);
    webActions.selectDropdownOption(this.state, state);
    log.info("Selected State : " + state);
    this.city.sendKeys(city);
    log.info("Entered City : " + city);
    this.zipCode.sendKeys(zipCode);
    log.info("Entered Zip Code : " + zipCode);
    try {
      webActions.selectDropdownOption(this.region, "");
    } catch (NoSuchElementException e) {
      log.info("Region field is not available.");
    }
    try {
      addLocationButton.click();
    } catch (NoSuchElementException e) {
      log.info("Add Location button is not present.");
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(city));
    return city.isDisplayed();
  }
}

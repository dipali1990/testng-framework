package com.ymcareers.webapp.pageobjects.home.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Job Search Panel. */
public class JobSearch extends ComponentValidator {

  @FindBy(id = "keywords")
  WebElement searchInput;

  @FindBy(name = "location")
  WebElement locationSearchField;

  @FindBy(id = "stateDropdown")
  WebElement locationDropdown;

  @FindBy(xpath = "//button[@aria-label='Submit Job Search']")
  WebElement searchButton;

  WebActions webActions = new WebActions();

  public JobSearch(final WebDriver driver) {
    super(driver);
  }

  /**
   * Method enters keyword and location and search the job.
   *
   * @param keyword Keyword to search jobs
   * @param location Location
   */
  public void searchJobHome(final String keyword, final String location) {
    searchInput.clear();
    searchInput.sendKeys(keyword);
    log.info("Entered Keyword in the search field of Home Page");
    try {
      webActions.selectDropdownOption(locationDropdown, location);
      log.info("Selected '" + location + "' from the Dropdown");
    } catch (Exception e) {
      locationSearchField.sendKeys(location);
      log.info("Entered location in the Location Field");
    }
    searchButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(searchInput));
    return searchInput.isDisplayed();
  }
}

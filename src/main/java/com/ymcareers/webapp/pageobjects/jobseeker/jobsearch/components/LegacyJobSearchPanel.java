package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Job Search Panel on Job Search Page. */
public final class LegacyJobSearchPanel extends ComponentValidator {

  @FindBy(id = "keywords")
  WebElement searchInput;

  @FindBy(name = "location")
  WebElement locationSearchField;

  @FindBy(name = "t735")
  WebElement locationDropdown;

  @FindBy(xpath = "//button[contains(@class,'btn-search')]")
  WebElement searchButton;

  WebActions webActions = new WebActions();

  public LegacyJobSearchPanel(final WebDriver driver) {
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
      if (locationDropdown.isDisplayed()) {
        webActions.selectDropdownOption(locationDropdown, location);
        log.info("Selected '" + location + "' from the Dropdown");
      }
    } catch (Exception e) {
      locationSearchField.sendKeys(location);
      log.info("Entered location in the Location Field");
    }
    wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    searchButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(searchInput));
    return searchInput.isDisplayed();
  }
}

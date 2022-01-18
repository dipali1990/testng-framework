package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Job Search Panel on Job Search Page. */
public final class PaneViewJobSearchPanel extends ComponentValidator {

  @FindBy(id = "keywords")
  WebElement searchInput;

  @FindBy(name = "location")
  WebElement locationSearchField;

  @FindBy(name = "t735")
  WebElement locationDropdown;

  @FindBy(xpath = "//button[contains(@class,'btn-search')]")
  WebElement searchButton;

  @FindBy(id = "data-step-launch-tutorial")
  WebElement questionIcon;

  @FindBy(xpath = "//div[@class='introjs-bullets']")
  WebElement tooltipBullets;

  @FindBy(xpath = "//div[@class='introjs-tooltiptext']")
  WebElement tooltipText;

  @FindBy(xpath = "//a[@role='button' and text()='Next →']")
  WebElement tooltipNextButton;

  @FindBy(xpath = "//a[text()='Done']")
  WebElement tooltipDoneButton;

  WebActions webActions = new WebActions();

  public PaneViewJobSearchPanel(final WebDriver driver) {
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

  /**
   * Method Verifies Tool Tip.
   *
   * @return Boolean Value
   */
  public boolean verifyToolTip() {
    wait.until(ExpectedConditions.visibilityOf(questionIcon));
    questionIcon.click();
    wait.until(ExpectedConditions.visibilityOf(tooltipBullets));
    List<WebElement> bullets = tooltipBullets.findElements(By.tagName("li"));
    for (int i = 0; i < bullets.size() - 1; i++) {
      wait.until(ExpectedConditions.visibilityOf(tooltipNextButton));
      log.info("Tooltip text: " + tooltipText.getText());
      tooltipNextButton.click();
      log.info("Clicked 'Next'");
    }
    wait.until(ExpectedConditions.visibilityOf(tooltipDoneButton));
    log.info("Tooltip text: " + tooltipText.getText());
    tooltipDoneButton.click();
    log.info("Clicked 'Done'");
    return searchButton.isDisplayed();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(searchInput));
    return searchInput.isDisplayed();
  }
}

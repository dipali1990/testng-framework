package com.ymcareers.webapp.pageobjects.employer.resumebank.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for tooltip displayed on the Resume Bank. */
public class Filter extends ComponentValidator {

  @FindBy(id = "filterBtn")
  WebElement filterButton;

  @FindBy(xpath = "//div[@class='container']//a[@href='javascript:void(0)']")
  WebElement filterClose;

  @FindBy(id = "bookmarked-1")
  WebElement bookmarked;

  @FindBy(xpath = "//a[contains(@id,'bookmarked')]")
  WebElement bookmarkedChip;

  @FindBy(id = "viewed-1")
  WebElement viewed;

  @FindBy(xpath = "//a[contains(@id,'viewed')]")
  WebElement viewedChip;

  @FindBy(xpath = "//a[@href='#filter-country']")
  WebElement countryText;

  @FindBy(xpath = "//div[@id='filter-country']")
  WebElement countryDropdown;

  @FindBy(xpath = "//*[@id='candidate-search']/div/section[3]/div[3]")
  WebElement filterPanelShowChips;

  @FindBy(xpath = "//a[@href='#filter-state']")
  WebElement stateText;

  @FindBy(xpath = "//div[@id='filter-state']")
  WebElement stateDropdown;

  @FindBy(xpath = "//a[@href='#filter-updated']")
  WebElement lastUpdatedText;

  @FindBy(xpath = "//div[@id='filter-updated']")
  WebElement lastUpdatedRadioButton;

  @FindBy(xpath = "//a[contains(@id,'updated')]")
  WebElement lastUpdatedChip;

  @FindBy(id = "input-save-search-btn")
  WebElement saveSearchButton;

  @FindBy(id = "input-save-search")
  WebElement savedSearchInputField;

  @FindBy(xpath = "//div[contains(text(),'Success')]")
  WebElement successMsg;

  WebActions webActions = new WebActions();

  public Filter(WebDriver driver) {
    super(driver);
  }

  public void selectFilterButton() {
    filterButton.click();
  }

  /**
   * Method selects 'Bookmarked' filter option.
   *
   * @return Boolean value
   * @throws InterruptedException Because of Thread.Sleep
   */
  public boolean applyBookmarkedFilter() throws InterruptedException {
    bookmarked.click();
    Thread.sleep(2000);
    filterClose.click();
    boolean flag;
    flag = bookmarkedChip.isDisplayed();
    return flag;
  }

  /**
   * Method selects 'Viewed' filter option.
   *
   * @return Boolean value
   * @throws InterruptedException Because of Thread.Sleep
   */
  public boolean applyViewedFilter() throws InterruptedException {
    viewed.click();
    Thread.sleep(2000);
    filterClose.click();
    boolean flag;
    flag = viewedChip.isDisplayed();
    return flag;
  }

  /**
   * Selects multiple countries from the multi-select country dropdown.
   *
   * @param countries Countries provided
   * @return Boolean value
   * @throws InterruptedException Because of Thread.Sleep
   */
  public boolean applyCountryFilter(String countries) throws InterruptedException {
    int count = 0;
    String[] countriesArray = countries.split(",");
    countryText.click();
    log.info("Clicked Country Dropdown");
    List<WebElement> countryList = countryDropdown.findElements(By.tagName("label"));
    for (WebElement country : countryList) {
      for (String s : countriesArray) {
        if (country.getText().equalsIgnoreCase(s)) {
          country.click();
          log.info(country.getText() + " is selected");
        }
      }
    }
    Thread.sleep(2000);
    filterClose.click();
    List<WebElement> countryChips =
        filterPanelShowChips.findElements(By.xpath("//a[contains(@id,'country')]"));
    for (WebElement chips : countryChips) {
      for (String s : countriesArray) {
        if (chips.getText().contains(s)) {
          count++;
        }
      }
    }
    boolean flag = false;
    int size = countriesArray.length;
    if (count == size) {
      flag = true;
    }
    return flag;
  }

  /**
   * Selects multiple states from the multi-select State dropdown.
   *
   * @param states States provided
   * @return Boolean value
   * @throws InterruptedException Because of Thread.Sleep
   */
  public boolean applyStateFilter(String states) throws InterruptedException {
    boolean flag = false;
    int count = 0;
    String[] stateArray = states.split(",");
    stateText.click();
    List<WebElement> stateList = stateDropdown.findElements(By.tagName("label"));

    for (WebElement state : stateList) {
      for (String s : stateArray) {
        if (state.getText().equalsIgnoreCase(s)) {
          state.click();
          log.info(state.getText() + " is selected");
        }
      }
    }
    Thread.sleep(2000);
    filterClose.click();
    List<WebElement> stateChips =
        filterPanelShowChips.findElements(By.xpath("//a[contains(@id,'state')]"));

    for (WebElement chips : stateChips) {
      for (String state : stateArray) {
        if (chips.getText().contains(state)) {
          count++;
        }
      }
    }
    int size = stateArray.length;
    if (count == size) {
      flag = true;
    }

    return flag;
  }

  /**
   * Selects last updated radio button.
   *
   * @param lastUpdatedOption Last Updated option
   * @return Boolean Value
   * @throws InterruptedException Because of Thread.Sleep
   */
  public boolean applyLastUpdatedFilter(String lastUpdatedOption) throws InterruptedException {
    lastUpdatedText.click();
    webActions.selectRadioButton(lastUpdatedRadioButton, lastUpdatedOption);
    Thread.sleep(2000);
    filterClose.click();
    boolean flag;
    flag = lastUpdatedChip.isDisplayed();
    return flag;
  }

  /**
   * Method verifies 'Saved Search' button is disabled.
   *
   * @return Boolean value
   */
  public boolean verifySavedSearchButtonIsDisabled() {
    boolean flag;
    flag = !saveSearchButton.isEnabled();
    return flag;
  }

  /**
   * Method verifies employer is able to save the search.
   *
   * @return Boolean value
   */
  public boolean saveSearch() {
    String savedSearchName = webActions.generateRandomName();
    boolean flag = false;
    bookmarked.click();
    savedSearchInputField.sendKeys("Saved Search " + savedSearchName);
    wait.until(ExpectedConditions.elementToBeClickable(saveSearchButton));
    saveSearchButton.click();
    try {
      wait.until(ExpectedConditions.visibilityOf(successMsg));
      flag = successMsg.isDisplayed();
    } catch (NoSuchElementException e) {
      log.info("Success Message couldn't be displayed");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(filterClose));
    return filterClose.isDisplayed();
  }
}

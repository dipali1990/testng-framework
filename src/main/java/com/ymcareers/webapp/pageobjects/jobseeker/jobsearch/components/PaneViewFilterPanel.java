package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Filter Panel on Job Search Page. */
public final class PaneViewFilterPanel extends ComponentValidator {

  @FindBy(id = "filterBtn")
  WebElement filterButton;

  @FindBy(xpath = "//div[@class='filters-nav show-filter']")
  WebElement filterPanel;

  @FindBy(xpath = "//div[@class='container']")
  WebElement filterContainer;

  @FindBy(xpath = "//div[starts-with(@class,'filter-panel')]")
  WebElement filterChip;

  @FindBy(xpath = "//div[@class='container']//a[@href='javascript:void(0)']")
  WebElement filterClose;

  @FindBy(xpath = "//a[@onClick='removeAllFilters();']")
  WebElement clearAll;

  @FindBy(xpath = "//div[contains(@class,'result-count')]")
  WebElement jobResultsCount;

  @FindBy(xpath = "//div[@class='mdc-slider__thumb-knob']")
  WebElement sliderKnob;

  @FindBy(xpath = "//span[@class='mdc-slider__value-indicator-text']")
  WebElement salaryRange;

  @FindBy(xpath = "//span[@class='badge badge-success']")
  WebElement filterCounter;

  @FindBy(xpath = "//a[@class='filters-show-all']")
  WebElement showAllEnabled;

  String attribute = "class";

  WebActions actions = new WebActions();

  public PaneViewFilterPanel(final WebDriver driver) {
    super(driver);
  }

  /** Clicks Filter Button Available next to Search Panel on Job Search Page. */
  public void clickFilterButton() {
    try {
      filterPanel.isDisplayed();
      log.info("Filter Panel is opened");
    } catch (Exception e) {
      filterButton.click();
      wait.until(ExpectedConditions.visibilityOf(filterPanel));
    }
  }

  /** Closes Filter Panel. */
  public void closeFilterPanel() {
    try {
      filterClose.click();
    } catch (Exception e) {
      log.info("Filter Panel is closed");
    }
  }

  /**
   * Method Finds Which Filter Type is present (Dropdown, Checkbox).
   *
   * @param subFilter List of Total Filters
   * @return Filter Type
   * @throws InterruptedException Exception because of Thread.Sleep
   */
  public String findFilterType(List<WebElement> subFilter) throws InterruptedException {
    String filterType;
    clickFilterButton();
    Thread.sleep(500);
    filterType = subFilter.get(1).getAttribute(attribute);
    filterType = filterType + subFilter.get(1).getText();
    return filterType;
  }

  /**
   * Clicks Checkbox.
   *
   * @param filter Filter Option
   * @return List of Selected Option
   * @throws InterruptedException Exception
   */
  public List<String> applyCheckboxFilter(List<WebElement> filter) throws InterruptedException {
    for (WebElement filterSec : filter) {
      if (filterSec.getAttribute(attribute).equalsIgnoreCase("filter-checkbox")) {
        filterSec.click();
      }
    }
    ArrayList<String> selectedOption = new ArrayList<>();
    selectedOption.add(filter.get(0).getText());
    log.info("Checkbox Selected for '" + filter.get(0).getText() + "'");
    Thread.sleep(500);
    return selectedOption;
  }

  /**
   * Method Selects First Two Options from the Dropdown.
   *
   * @param dropdownName Dropdown Name
   * @return List of Selected Options
   * @throws InterruptedException Exception
   */
  public List<String> applyDropdownFilter(List<WebElement> dropdownName)
      throws InterruptedException {
    int count = 0;
    List<String> selectedOptions = new ArrayList<>();
    WebElement collapse = dropdownName.get(0).findElement(By.tagName("a"));
    collapse.click();
    Thread.sleep(500);
    for (int i = 2; i < dropdownName.size(); i++) {
      try {
        dropdownName.get(i).click();
      } catch (ElementNotInteractableException e) {
        actions.moveToElement(dropdownName.get(i));
        dropdownName.get(i).click();
      }

      log.info("Options Selected from the Dropdown : " + dropdownName.get(i).getText());
      selectedOptions.add(dropdownName.get(i).getText());
      count++;
      if (count > 1) {
        break;
      }
    }
    collapse.click();
    return selectedOptions;
  }

  /**
   * Method Applied Slider Field.
   *
   * @return List of Selected Option
   */
  public List<String> applySliderFilter() {
    log.info("******** Slider ********");
    actions.handleSlider(sliderKnob);
    ArrayList<String> selectedOption = new ArrayList<>();
    selectedOption.add(salaryRange.getText());
    log.info("Range Selected from " + salaryRange.getText());
    return selectedOption;
  }

  /**
   * Methods Checks Filters Applied on the Job Search Page.
   *
   * @param selectedOptions List of Selected Options
   * @return Boolean Value
   */
  public boolean verifyFilterApplied(List<String> selectedOptions) {
    boolean flag = false;
    List<String> actual = new ArrayList<>();
    List<WebElement> filterChips = filterChip.findElements(By.tagName("a"));
    if (selectedOptions.size() == filterChips.size()
        && filterChips.size() == Integer.parseInt(filterCounter.getText())) {
      try {
        showAllEnabled.isDisplayed();
        showAllEnabled.click();
      } catch (Exception e) {
        log.info("All Selected Filters displayed in the row");
      }
      for (WebElement name : filterChips) {
        log.info(name.getText());
        if (name.getText().contains(":")) {
          String[] requiredString = name.getText().split(":");
          actual.add(requiredString[1].replaceAll("[$+]", "").trim());
        } else {
          actual.add(name.getText().trim());
        }
      }
      flag = actual.equals(selectedOptions);
    } else {
      log.info("Filter Applied doesn't match");
    }
    return flag;
  }

  /** Clears All Filter. */
  public void clearAllFilter() {
    try {
      clearAll.click();
      wait.until(ExpectedConditions.invisibilityOf(clearAll));
    } catch (ElementNotInteractableException e) {
      log.info("'Clear All' link not displayed");
    }
  }

  /**
   * Method Applies Each Filter One by One.
   *
   * @return boolean
   * @throws InterruptedException Exception
   */
  public boolean applyFilterOneByOne() throws InterruptedException {
    boolean flag = true;
    List<String> selectedFilter;
    List<WebElement> filters = filterContainer.findElements(By.cssSelector("div.row"));
    for (WebElement option : filters) {
      List<WebElement> subFilter = option.findElements(By.tagName("div"));
      if (subFilter.size() > 2) {
        String filterType = findFilterType(subFilter);
        if (filterType.contains("checkbox") && !filterType.contains("Search Only Position Title")) {
          selectedFilter = applyCheckboxFilter(subFilter);
          flag = verifyFilterApplied(selectedFilter);
        } else if (filterType.contains("collapse")) {
          selectedFilter = applyDropdownFilter(subFilter);
          flag = verifyFilterApplied(selectedFilter);
        } else if (filterType.contains("content-between")) {
          selectedFilter = applySliderFilter();
          flag = verifyFilterApplied(selectedFilter);
        }
        log.info("Total Results Displayed : " + jobResultsCount.getText());
        closeFilterPanel();
        wait.until(ExpectedConditions.invisibilityOf(filterClose));
        clearAllFilter();
        if (!flag) {
          break;
        }
      }
    }
    return flag;
  }

  /**
   * Apply All Filters.
   *
   * @return Boolean Value
   * @throws InterruptedException Exception
   */
  public boolean applyAllFilter() throws InterruptedException {
    List<String> selectedCheckboxFilter;
    List<String> selectedDropdownFilter;
    List<String> selectedSliderFilter;
    List<String> total = new ArrayList<>();
    List<WebElement> filters = filterContainer.findElements(By.cssSelector("div.row"));
    for (WebElement option : filters) {
      List<WebElement> subFilter = option.findElements(By.tagName("div"));
      if (subFilter.size() > 2) {
        String filterType = findFilterType(subFilter);
        if (filterType.contains("checkbox") && !filterType.contains("Search Only Position Title")) {
          selectedCheckboxFilter = applyCheckboxFilter(subFilter);
          total.addAll(selectedCheckboxFilter);
        } else if (filterType.contains("collapse")) {
          selectedDropdownFilter = applyDropdownFilter(subFilter);
          total.addAll(selectedDropdownFilter);
        } else if (filterType.contains("content-between")) {
          selectedSliderFilter = applySliderFilter();
          total.addAll(selectedSliderFilter);
        }
        log.info(total.size());
        log.info("Total Results Displayed : " + jobResultsCount.getText());
      }
    }
    log.info("*********** After Applying all Filter : " + total.size());
    closeFilterPanel();
    boolean flag;
    flag = verifyFilterApplied(total);
    return flag;
  }

  /**
   * Removes Filter From the Job Search Page.
   *
   * @return Boolean Value
   */
  public boolean removeFilter() {
    boolean flag = false;
    List<WebElement> filterChips = filterChip.findElements(By.tagName("a"));
    int counter = filterChips.size();
    for (WebElement chip : filterChips) {
      chip.click();
      counter--;
      if (Integer.parseInt(filterCounter.getText()) == counter) {
        flag = true;
      }
      if (counter < 2) {
        break;
      }
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(filterPanel));
    return filterPanel.isDisplayed();
  }
}

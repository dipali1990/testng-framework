package com.ymcareers.webapp.core;

import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/** This class contains all common reusable selenium actions. */
public class WebActions {

  private final WebDriver driver = DriverManager.getDriver();
  private final JavascriptExecutor js = (JavascriptExecutor) driver;
  Actions actions = new Actions(driver);

  // This method is to generate random String
  public String generateRandomName() {
    return RandomStringUtils.randomAlphabetic(5);
  }

  // This method is to generate random email id
  public String generateEmail(String name) {
    return name + "@communitybrands.com";
  }

  public String generateJobTitle() {

    return "QA Automation Engineer " + RandomStringUtils.randomNumeric(2);
  }

  public String generateRandomNumber() {
    return RandomStringUtils.randomNumeric(5);
  }

  /**
   * Method selects option from radio button.
   *
   * @param radioButton WebElement for Radiobutton
   * @param radioOption Option that needs to be selected
   */
  public void selectRadioButton(final WebElement radioButton, final String radioOption) {
    List<WebElement> radioButtonOptions = radioButton.findElements(By.tagName("label"));
    for (WebElement radioOpt : radioButtonOptions) {
      String option = radioOpt.getText();
      if (option.equalsIgnoreCase(radioOption)) {
        radioOpt.click();
        break;
      }
    }
  }

  /**
   * Method selects option from the radio button.
   *
   * @param dropdownName WebElement for the dropdown
   * @param dropdownOption Dropdown option
   */
  public void selectDropdownOption(final WebElement dropdownName, final String dropdownOption) {
    Select dropDown = new Select(dropdownName);
    if (!dropdownOption.isEmpty()) {
      try {
        dropDown.selectByVisibleText(dropdownOption);
      } catch (Exception e) {
        dropDown.selectByIndex(1);
      }
    } else {
      dropDown.selectByIndex(1);
    }
  }

  public void scrollVertical() {
    js.executeScript("window.scrollBy(0,1000)");
  }

  public void scrollToElement(final WebElement element) {
    js.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public void moveToElement(WebElement element) {
    actions.moveToElement(element).build().perform();
  }

  public void handleSlider(WebElement element) {
    actions.dragAndDropBy(element, 30, 0).build().perform();
  }
}

package com.ymcareers.webapp.pageobjects.employer.resumebank.components;

import com.ymcareers.webapp.core.ComponentValidator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for tooltip displayed on the Resume Bank. */
public class ToolTip extends ComponentValidator {

  @FindBy(xpath = "//div[@class='introjs-bullets']")
  WebElement tooltipBullets;

  @FindBy(xpath = "//div[@class='introjs-tooltiptext']")
  WebElement tooltipText;

  @FindBy(xpath = "//a[@role='button' and text()='Next →']")
  WebElement tooltipNextButton;

  @FindBy(xpath = "//a[text()='Done']")
  WebElement tooltipDoneButton;

  @FindBy(xpath = "//a[@role='button' and text()='Skip']")
  WebElement skipButton;

  @FindBy(xpath = "//div[text()='Filters']")
  WebElement filtersPabel;

  public ToolTip(WebDriver driver) {
    super(driver);
  }

  /**
   * Method handles Tooltip.
   *
   * @return boolean
   */
  public boolean handleTutorialTooltip() {
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
    return filtersPabel.isDisplayed();
  }

  public void skipToolTip() {
    wait.until(ExpectedConditions.visibilityOf(skipButton));
    skipButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(tooltipBullets));
    return tooltipBullets.isDisplayed();
  }
}

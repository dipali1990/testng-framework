package com.ymcareers.webapp.pageobjects.employer.postajob.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/** This class has WebElements for 'Settings' Section. */
public class Upgrades extends ComponentValidator {

  @FindBy(name = "cancel")
  WebElement noThanksButton;

  @FindBy(name = "submit")
  WebElement addUpgradeButton;

  public Upgrades(WebDriver driver) {
    super(driver);
  }

  public void cancelUpgrade() {
    noThanksButton.click();
  }

  @Override
  public boolean isDisplayed() {
    boolean flag = false;
    try {
      addUpgradeButton.isDisplayed();
      log.info("Upgrades Page is displayed");
      flag = true;
    } catch (NoSuchElementException e) {
      log.info("Upgrades Page not displayed");
    }
    return flag;
  }
}

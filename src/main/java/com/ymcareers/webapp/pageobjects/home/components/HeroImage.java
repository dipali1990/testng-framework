package com.ymcareers.webapp.pageobjects.home.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Hero Banner. */
public class HeroImage extends ComponentValidator {

  @FindBy(id = "hero")
  WebElement heroBanner;

  public HeroImage(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(heroBanner));
    return heroBanner.isDisplayed();
  }
}

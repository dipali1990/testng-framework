package com.ymcareers.webapp.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This abstract class will be extended by all component classes.
 */
public abstract class ComponentValidator {
  protected Logger log;
  protected WebDriverWait wait;

  protected ComponentValidator(WebDriver driver) {
    log = LogManager.getLogger(this.getClass());
    wait = new WebDriverWait(driver, 20);
    PageFactory.initElements(driver, this);
  }

  public abstract boolean isDisplayed();
}

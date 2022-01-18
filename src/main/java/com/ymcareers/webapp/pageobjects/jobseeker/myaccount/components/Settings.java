package com.ymcareers.webapp.pageobjects.jobseeker.myaccount.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;

/** This class has WebElements for Settings section available on Job Seeker's My Account Page. */
public class Settings extends ComponentValidator {

  public Settings(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean isDisplayed() {
    return false;
  }
}

package com.ymcareers.webapp.pageobjects.employer.postajob.pages;

import com.ymcareers.webapp.pageobjects.employer.postajob.components.Upgrades;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component Class. */
public class UpgradePage {

  private final Upgrades upgrades;

  /**
   * Initializes all components for Resume Bank Page.
   *
   * @param driver WebDriver Instance
   */
  public UpgradePage(WebDriver driver) {
    upgrades = PageFactory.initElements(driver, Upgrades.class);
  }

  public Upgrades getUpgrades() {
    return upgrades;
  }
}

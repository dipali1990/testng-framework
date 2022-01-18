package com.ymcareers.webapp.pageobjects.home.pages;

import com.ymcareers.webapp.pageobjects.common.components.CookieConsent;
import com.ymcareers.webapp.pageobjects.home.components.HeroImage;
import com.ymcareers.webapp.pageobjects.home.components.JobSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all WebElements displayed on the Home Page. */
public class HomePage {

  private final CookieConsent cookieConsent;
  private final HeroImage heroImage;
  private final JobSearch jobSearch;

  /**
   * Constructor to initiate WebElements for the Component classes.
   *
   * @param driver WebDriver Instance
   */
  public HomePage(WebDriver driver) {
    cookieConsent = PageFactory.initElements(driver, CookieConsent.class);
    heroImage = PageFactory.initElements(driver, HeroImage.class);
    jobSearch = PageFactory.initElements(driver, JobSearch.class);
  }

  public CookieConsent getCookieConsent() {
    return cookieConsent;
  }

  public HeroImage getHeroImage() {
    return heroImage;
  }

  public JobSearch getJobSearch() {
    return jobSearch;
  }
}

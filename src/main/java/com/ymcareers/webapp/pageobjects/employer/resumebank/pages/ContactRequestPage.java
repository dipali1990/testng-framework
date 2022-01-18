package com.ymcareers.webapp.pageobjects.employer.resumebank.pages;

import com.ymcareers.webapp.pageobjects.employer.resumebank.components.ResumeBankCart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component Class. */
public class ContactRequestPage {

  private final ResumeBankCart resumeBankCart;

  /**
   * Initializes all components for Resume Bank Page.
   *
   * @param driver WebDriver Instance
   */
  public ContactRequestPage(WebDriver driver) {
    resumeBankCart = PageFactory.initElements(driver, ResumeBankCart.class);
  }

  public ResumeBankCart getResumeBankCart() {
    return resumeBankCart;
  }
}

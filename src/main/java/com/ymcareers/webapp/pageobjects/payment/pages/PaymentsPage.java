package com.ymcareers.webapp.pageobjects.payment.pages;

import com.ymcareers.webapp.pageobjects.payment.components.CreditCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component Class. */
public class PaymentsPage {

  private final CreditCard creditCard;

  /**
   * Initializes all components for Resume Bank Page.
   *
   * @param driver WebDriver Instance
   */
  public PaymentsPage(WebDriver driver) {
    creditCard = PageFactory.initElements(driver, CreditCard.class);
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }
}

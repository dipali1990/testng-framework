package com.ymcareers.webapp.pageobjects.payment.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for tooltip displayed on the Resume Bank. */
public class CreditCard extends ComponentValidator {

  @FindBy(name = "use_same_billing")
  WebElement sameBillingCheckbox;

  @FindBy(name = "cc_number")
  WebElement cardNumber;

  @FindBy(name = "cc_type")
  WebElement cardType;

  @FindBy(name = "cc_cvv2")
  WebElement cvvNumber;

  @FindBy(name = "cc_expire_year")
  WebElement expYear;

  @FindBy(name = "cc_submit")
  WebElement payByCard;

  @FindBy(xpath = "//div[@class=\"jt_purchase_notification\"]")
  WebElement purchaseNotification;

  @FindBy(name = "fr_submit")
  WebElement completeOrder;

  public CreditCard(WebDriver driver) {
    super(driver);
  }

  WebActions webActions = new WebActions();

  /**
   * Method enters credit card details.
   *
   * @param card Credit Card number
   * @param type Credit Card Type
   * @param cvv CVV number
   */
  public void payByCreditCard(String card, String type, String cvv) {
    sameBillingCheckbox.click();
    log.info("Billing address is same");
    cardNumber.sendKeys(card);
    log.info("Entered Credit Card Number : " + card);
    webActions.selectDropdownOption(cardType, type);
    log.info("Selected card type as : " + type);
    webActions.selectDropdownOption(expYear, "");
    log.info("Selected Expiration date");
    cvvNumber.sendKeys(cvv);
    log.info("Entered CVV : " + cvv);
    payByCard.click();
    log.info("Clicked 'Pay By Credit Cart' button");
  }

  /**
   * To place order.
   *
   * @param card Credit Card number
   * @param type Credit Card Type
   * @param cvv CVV number
   */
  public void completeOrder(String card, String type, String cvv) {
    try {
      completeOrder.click();
    } catch (NoSuchElementException e) {
      log.info("******* Credit Card Payment *******");
      payByCreditCard(card, type, cvv);
    }
  }

  /**
   * Verifies whether order is placed.
   *
   * @return Boolean Value
   */
  public boolean verifyOrderPlaced() {
    boolean flag = false;
    try {
      if (purchaseNotification.isDisplayed()) {
        flag = true;
        log.info("Employer has successfully purchased the resume");
      }
    } catch (Exception e) {
      log.info("Something went wrong...");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(payByCard));
    return payByCard.isDisplayed();
  }
}

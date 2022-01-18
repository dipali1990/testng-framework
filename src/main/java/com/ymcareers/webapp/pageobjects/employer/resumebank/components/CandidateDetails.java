package com.ymcareers.webapp.pageobjects.employer.resumebank.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for tooltip displayed on the Resume Bank. */
public class CandidateDetails extends ComponentValidator {

  @FindBy(id = "filterBtn")
  WebElement filterButton;

  @FindBy(xpath = "//div[@class='jt_cte_area']//following::h3[2]")
  WebElement candidateId;

  @FindBy(xpath = "//a[@alt='Add to Cart']")
  WebElement addToCart;

  @FindBy(xpath = "//a[text()='« Return to your search results']")
  WebElement returnToSearchResultLink;

  @FindBy(xpath = "//a[@title='View Your Cart']")
  WebElement viewCartButton;

  @FindBy(xpath = "//a[@title='Print']")
  WebElement printIcon;

  @FindBy(xpath = "//a[@aria-label='Download PDF']")
  WebElement pdfIcon;

  @FindBy(xpath = "//a[@aria-label='Forward to a Colleague']")
  WebElement fwdToColleagueIcon;

  @FindBy(id = "site-name")
  WebElement siteName;

  @FindBy(name = "email")
  WebElement sendToEmail;

  WebDriver driver;
  Actions action;

  /**
   * Constructor to invoke Actions Class.
   *
   * @param driver WebDriver Instance
   */
  public CandidateDetails(WebDriver driver) {
    super(driver);
    this.driver = driver;
    action = new Actions(driver);
  }

  /**
   * Method verifies candidate id on Resume View Page.
   *
   * @return Candidate Id
   */
  public String getCandidateId() {
    String candidate = "";
    try {
      if (candidateId.isDisplayed()) {
        candidate = candidateId.getText();
      }
    } catch (Exception e) {
      candidate = "";
      log.info("Candidate ID not displayed ");
    }
    return candidate;
  }

  /**
   * Method adds resume to the cart.
   *
   * @return Boolean Value
   */
  public boolean addToCart() {
    boolean flag = false;
    for (int i = 0; i < 2; i++) {
      if (candidateId.getText().contains("Anonymous")) {
        flag = true;
        addToCart.click();
        break;
      } else {
        returnToSearchResultLink.click();
      }
    }
    return flag;
  }

  public void viewCart() {
    wait.until(ExpectedConditions.visibilityOf(viewCartButton));
    viewCartButton.click();
  }

  /**
   * Method verifies print resume functionality on Resume Bank Page.
   *
   * @return Boolean Value
   */
  public boolean printResume() {
    boolean flag = false;
    printIcon.click();
    driver.switchTo().window("resprint");
    try {
      if (siteName.isDisplayed()) {
        flag = true;
        log.info("User is able to print the resume");
      }
    } catch (Exception e) {
      flag = false;
    }
    return flag;
  }

  /**
   * Method clicks on 'pdf' icon.
   *
   * @return Boolean Value
   */
  public boolean pdfFormatResume() {
    boolean flag = false;
    try {
      if (pdfIcon.isEnabled()) {
        pdfIcon.click();
        flag = true;
        log.info("Able to click on PDF icon");
      }
    } catch (NoSuchElementException e) {
      log.info("Not able to click on PDF icon");
    }
    return flag;
  }

  /**
   * Method shares the resume to the colleague.
   *
   * @param email Recipient's mail id
   * @return Boolean Value
   */
  public boolean forwardResumeToColleague(String email) {
    boolean flag = false;
    fwdToColleagueIcon.click();
    wait.until(ExpectedConditions.visibilityOf(sendToEmail));
    sendToEmail.sendKeys(email);
    action.sendKeys(Keys.ENTER).build().perform();
    wait.until(ExpectedConditions.invisibilityOf(sendToEmail));
    if (fwdToColleagueIcon.isDisplayed()) {
      flag = true;
      log.info("Employer is able to send the resume to colleague");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(filterButton));
    return filterButton.isDisplayed();
  }
}

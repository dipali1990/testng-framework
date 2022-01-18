package com.ymcareers.webapp.pageobjects.employer.resumebank.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for tooltip displayed on the Resume Bank. */
public class ResumeBankCart extends ComponentValidator {

  @FindBy(xpath = "//span[text()=' Create Your Contact Request']")
  WebElement createContactRequest;

  @FindBy(id = "q2119")
  WebElement companyField;

  @FindBy(id = "q2120")
  WebElement positionField;

  @FindBy(name = "q2131")
  WebElement jobDescription;

  @FindBy(name = "submit_overview")
  WebElement submitContactRequest;

  public ResumeBankCart(WebDriver driver) {
    super(driver);
  }

  public void createContactRequest() {
    createContactRequest.click();
    log.info("Clicked 'Create Your Contact Request' button");
  }

  /**
   * Enter Company details to create a contact request.
   *
   * @param company Company Name
   * @param position Position
   * @param desc Job Description
   */
  public void enterCompanyDetails(String company, String position, String desc) {
    companyField.sendKeys(company);
    log.info("Entered Company Name : ");
    positionField.sendKeys(position);
    log.info("Entered Position Title : ");
    jobDescription.sendKeys(desc);
    log.info("Entered Job Description : ");
    submitContactRequest.click();
    log.info("Clicked 'Complete Contact Request' button");
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(createContactRequest));
    return createContactRequest.isDisplayed();
  }
}

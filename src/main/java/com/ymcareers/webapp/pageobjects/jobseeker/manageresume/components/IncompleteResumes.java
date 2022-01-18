package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class contains WebElements for Create Cover Letter Section available on the Manage Resumes
 * Page.
 */
public class IncompleteResumes extends ComponentValidator {

  @FindBy(xpath = "//td[@class='break-all']//a[contains(@aria-label,'resume')]")
  WebElement fileName;

  @FindBy(xpath = "//*[@class='incomplete-resumes']//a[contains(text(),'Edit')]")
  WebElement editLink;

  @FindBy(xpath = "//table[@class='incomplete-resumes']//*[@class='delete-alert']")
  WebElement deleteLink;

  @FindBy(name = "delresume")
  WebElement deleteResumeButton;

  public IncompleteResumes(WebDriver driver) {
    super(driver);
  }

  public String viewIncompleteResumeName() {
    return fileName.getText();
  }

  public void editIncompleteResumeName() {
    editLink.click();
  }

  /** Method deletes Incomplete Resumes. */
  public void deleteIncompleteResume() {
    deleteLink.click();
    wait.until(ExpectedConditions.visibilityOf(deleteResumeButton));
    deleteResumeButton.click();
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(fileName));
    return fileName.isDisplayed();
  }
}

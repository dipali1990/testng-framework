package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class contains WebElements for Create Cover Letter Section available on the Manage Resumes
 * Page.
 */
public class CoverLetter extends ComponentValidator {

  @FindBy(name = "cover_content")
  WebElement coverContent;

  @FindBy(name = "cover_filename")
  WebElement coverFileName;

  @FindBy(name = "create_cover")
  WebElement createCoverLetter;

  @FindBy(xpath = "//section[@aria-label='Cover letters']//a[text()='Edit']")
  WebElement editCoverLetter;

  @FindBy(xpath = "//section[@aria-label='Cover letters']//a[text()='Delete']")
  WebElement deleteLink;

  @FindBy(name = "del_cover")
  WebElement deleteCoverButton;

  WebActions webActions = new WebActions();
  String coverName = webActions.generateRandomName();

  public CoverLetter(WebDriver driver) {
    super(driver);
  }

  /**
   * Method creates Cover Letter.
   *
   * @param content Cover Letter Content
   */
  public void createCoverLetter(String content) {
    coverContent.sendKeys(content);
    log.info("Entered Cover Letter Content as \n" + content);
    coverFileName.sendKeys("Cover Letter_" + coverName);
    log.info("Cover Letter File Name : Cover Letter_" + coverName);
    createCoverLetter.click();
    log.info("Clicked on 'Create Cover Letter' button");
  }

  /** Method edits the Cover Letter. */
  public void editCoverLetter() {
    editCoverLetter.click();
    log.info("Clicked on 'Edit' link of Cover Letter");
    wait.until(ExpectedConditions.visibilityOf(coverFileName));
    createCoverLetter.sendKeys("Updated_" + coverName);
    log.info("New Cover Name : Updated_" + coverName);
    createCoverLetter.click();
    log.info("Clicked on 'Create Cover Letter' Button.");
  }

  /** Method deletes the cover letter. */
  public void deleteCoverLetter() {
    deleteLink.click();
    log.info("Clicked on 'Delete' option of Cover Letter");
    wait.until(ExpectedConditions.visibilityOf(deleteCoverButton));
    deleteCoverButton.click();
    log.info("Confirmation Pop up displayed and clicked on 'Delete' Button");
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(coverContent));
    return coverContent.isDisplayed();
  }
}

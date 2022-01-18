package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Job List. */
public class PaneViewJobList extends ComponentValidator {

  @FindBy(id = "data-step-results")
  WebElement jobListing;

  @FindBy(xpath = "//div[contains(@class,'job-result-tiles')]")
  WebElement jobSearchResults;

  @FindBy(id = "no-results")
  WebElement noResult;

  @FindBy(xpath = "//div[contains(@class,'job-result-count')]")
  WebElement searchResult;

  @FindBy(xpath = "//a[contains(@onclick,'saveJob')]")
  WebElement saveJob;

  @FindBy(xpath = "//div[@class='job-details']//div[@class='anim-icon bookmark']")
  WebElement saved;

  @FindBy(id = "applyBtn")
  WebElement applyNowButton;

  @FindBy(xpath = "//h1[@class='job-title']")
  WebElement jobTitle;

  @FindBy(xpath = "//*[@id='job-results-details']//div[contains(@class,'bookmark')]")
  WebElement jobDetailsSave;

  @FindBy(xpath = "//div[contains(@class,'logo-none')]")
  WebElement noLogo;

  @FindBy(xpath = "//div[@class='job-details-logo ']//img")
  WebElement logo;

  @FindBy(xpath = "//div[@class='job-title']//a")
  WebElement jobTitleOnLeftPane;

  @FindBy(css = ".job-company-row")
  WebElement company;

  @FindBy(css = ".job-location")
  WebElement companyLocation;

  @FindBy(css = ".job-posted-date")
  WebElement jobPostedDate;

  public PaneViewJobList(WebDriver driver) {
    super(driver);
  }

  /**
   * Method will select organically posted jobs.
   *
   * @param jobName Job Name
   */
  public boolean selectOrganicJob(String jobName) {
    boolean flag = false;
    List<WebElement> jobList = jobSearchResults.findElements(By.tagName("a"));
    for (WebElement job : jobList) {
      if (job.getText().contains(jobName)) {
        try {
          job.click();
          wait.until(ExpectedConditions.textToBePresentInElement(jobTitle, job.getText()));
          if (jobDetailsSave.isDisplayed()) {
            boolean isApplyEnabled = verifyApplyNowButtonIsEnabled();
            if (isApplyEnabled) {
              flag = true;
              break;
            }
          }
        } catch (Exception e) {
          log.info(job.getText() + " is a third party posted job");
          break;
        }
      }
    }
    return flag;
  }

  /**
   * Method Verifies Apply Now Button is enabled.
   *
   * @return Boolean
   */
  public boolean verifyApplyNowButtonIsEnabled() {
    boolean flag = false;
    try {
      applyNowButton.isDisplayed();
      flag = true;
    } catch (NoSuchElementException e) {
      log.info("Job is already applied");
    }
    return flag;
  }

  /**
   * Method verify if search result displayed and then will select a job.
   *
   * @param jobName Job Name
   */
  public boolean selectJob(String jobName) {
    boolean flag = false;
    try {
      noResult.isDisplayed();
    } catch (Exception e) {
      log.info("Search Result displayed " + searchResult.getText());
      flag = selectOrganicJob(jobName);
    }
    return flag;
  }

  /**
   * Method handles if no result found.
   *
   * @param jobName Job Name
   * @return Job Names shown in the search result
   */
  public String getSearchResult(String jobName) {
    StringBuilder msg;
    try {
      noResult.isDisplayed();
      msg = new StringBuilder(noResult.getText());
    } catch (Exception e) {
      msg = new StringBuilder(searchResult.getText());
      List<WebElement> jobList = jobSearchResults.findElements(By.tagName("span"));
      for (WebElement job : jobList) {
        if (job.getText().contains(jobName)) {
          msg.append("\n").append(job.getText());
        }
      }
    }
    return msg.toString();
  }

  /** Method Saves the job. */
  public void saveJob() {
    saveJob.click();
    log.info("Clicked Save");
  }

  /** Method verifies job is saved. */
  public boolean verifyJobIsSaved() {
    boolean flag;
    try {
      flag = verifySavedIconIsOn();
    } catch (Exception e) {
      saveJob();
      flag = verifySavedIconIsOn();
    }
    return flag;
  }

  /**
   * Method Verifies if Saved 'Star' Icon is Checked.
   *
   * @return boolean
   */
  public boolean verifySavedIconIsOn() {
    boolean flag = false;
    wait.until(ExpectedConditions.visibilityOf(saved));
    if (saved.isDisplayed()) {
      flag = true;
    }
    return flag;
  }

  /**
   * Method Verifies Logo.
   *
   * @return Name of the Initial Character/Image Path
   */
  public String verifyLogo() {
    String logoName;
    try {
      noLogo.isDisplayed();
      log.info("Company Logo is not available");
      logoName = noLogo.findElement(By.tagName("span")).getText();
    } catch (Exception e) {
      logoName = logo.getAttribute("src");
    }
    return logoName;
  }

  /**
   * Method Verifies required Job Details.
   *
   * @return Boolean Value
   */
  public boolean verifyJobDetails() {
    boolean flag = false;
    try {
      jobTitleOnLeftPane.isDisplayed();
      log.info("Job Title is displayed as '" + jobTitleOnLeftPane.getText() + "'");
      company.isDisplayed();
      log.info("Company is displayed as '" + company.getText() + "'");
      companyLocation.isDisplayed();
      log.info("Company Location is displayed as '" + companyLocation.getText() + "'");
      jobPostedDate.isDisplayed();
      log.info("Job Posted Date is displayed as '" + jobPostedDate.getText() + "'");
      flag = true;
    } catch (Exception e) {
      log.info("Job Details Not Displayed");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jobListing));
    return jobListing.isDisplayed();
  }
}

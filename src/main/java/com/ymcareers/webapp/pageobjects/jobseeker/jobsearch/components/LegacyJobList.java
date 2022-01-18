package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains WebElements for the Legacy Job List. */
public class LegacyJobList extends ComponentValidator {

  @FindBy(id = "job-listing")
  WebElement jobListing;

  @FindBy(xpath = "//div[@class='listings']")
  WebElement jobSearchResults;

  @FindBy(id = "no-results")
  WebElement noResult;

  @FindBy(xpath = "//span[@class='refine']//following-sibling::em")
  WebElement searchResult;

  @FindBy(xpath = "//a[contains(@id,'save-job')]")
  WebElement saveJob;

  @FindBy(xpath = "//div[@class='listings']//following::li[text()='Saved']")
  WebElement saved;

  public LegacyJobList(WebDriver driver) {
    super(driver);
  }

  /**
   * Method will select organically posted jobs.
   *
   * @param jobName Job Name
   */
  public void selectOrganicJob(String jobName) {
    List<WebElement> jobList = jobSearchResults.findElements(By.tagName("div"));
    for (WebElement job : jobList) {
      if (job.getText().contains(jobName)) {
        try {
          if (job.findElement(By.tagName("ol")).isDisplayed()) {
            wait.until(
                ExpectedConditions.elementToBeClickable(job.findElement(By.tagName("span"))));
            job.findElement(By.tagName("span")).click();
            break;
          }
        } catch (Exception e) {
          log.info(job.getText() + " is a third party posted job");
        }
      }
    }
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
      selectOrganicJob(jobName);
      flag = true;
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

  /**
   * Method Saves the job.
   *
   * @return Message if no result found
   */
  public String saveJob() {
    String msg;
    try {
      noResult.isDisplayed();
      msg = noResult.getText();
      log.info("No Result Displayed");
    } catch (Exception e) {
      wait.until(ExpectedConditions.visibilityOf(searchResult));
      msg = searchResult.getText();
      List<WebElement> jobList = jobSearchResults.findElements(By.tagName("div"));
      for (WebElement job : jobList) {
        try {
          if (job.findElement(By.tagName("ol")).isDisplayed()) {
            saveJob.click();
            break;
          }
        } catch (Exception exception) {
          log.info(job.getText() + " is a third party posted job");
        }
      }
    }
    return msg;
  }

  /**
   * Method verifies job is saved.
   *
   * @return boolean value
   */
  public boolean verifyJobIsSaved() {
    boolean flag = false;
    try {
      wait.until(ExpectedConditions.visibilityOf(saved));
      if (saved.isDisplayed()) {
        flag = true;
      }
    } catch (Exception e) {
      log.info("Job couldn't be saved");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jobListing));
    return jobListing.isDisplayed();
  }
}

package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.pages;

import com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components.JobAlertPopUp;
import com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components.LegacyJobList;
import com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components.LegacyJobSearchPanel;
import com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components.LegacyJobView;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** Class initiates WebElements on the Legacy View of Job Search Page. */
public final class LegacyJobSearchPage {
  private final LegacyJobList legacyJobList;
  private final JobAlertPopUp jobAlertPopUp;
  private final LegacyJobView legacyJobView;
  private final LegacyJobSearchPanel legacyJobSearchPanel;
  /**
   * Constructor to initiate WebElements for the Component classes.
   *
   * @param driver WebDriver Instance
   */

  public LegacyJobSearchPage(final WebDriver driver) {
    legacyJobList = PageFactory.initElements(driver, LegacyJobList.class);
    jobAlertPopUp = PageFactory.initElements(driver, JobAlertPopUp.class);
    legacyJobView = PageFactory.initElements(driver, LegacyJobView.class);
    legacyJobSearchPanel = PageFactory.initElements(driver, LegacyJobSearchPanel.class);
  }

  public LegacyJobList getLegacyJobList() {
    return legacyJobList;
  }

  public JobAlertPopUp getJobAlertPopUp() {
    return jobAlertPopUp;
  }

  public LegacyJobView getJobView() {
    return legacyJobView;
  }

  public LegacyJobSearchPanel getJobSearchPanel() {
    return legacyJobSearchPanel;
  }
}

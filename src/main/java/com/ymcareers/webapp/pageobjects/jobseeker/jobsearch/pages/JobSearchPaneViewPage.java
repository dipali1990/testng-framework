package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.pages;

import com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** Class initiates WebElements on the Legacy View of Job Search Page. */
public final class JobSearchPaneViewPage {
  private final JobAlertPopUp jobAlertPopUp;
  private final PaneViewJobList paneViewJobList;
  private final PaneViewJobSearchPanel paneViewJobSearchPanel;
  private final PaneViewJobDetails paneViewJobDetails;
  private final JobApply jobApply;
  private final PaneViewFilterPanel paneViewFilterPanel;
  private final PaneViewMiniFooter paneViewMiniFooter;
  /**
   * Constructor to initiate WebElements for the Component classes.
   *
   * @param driver WebDriver Instance
   */

  public JobSearchPaneViewPage(final WebDriver driver) {
    jobAlertPopUp = PageFactory.initElements(driver, JobAlertPopUp.class);
    paneViewJobList = PageFactory.initElements(driver, PaneViewJobList.class);
    paneViewJobSearchPanel = PageFactory.initElements(driver, PaneViewJobSearchPanel.class);
    paneViewJobDetails = PageFactory.initElements(driver, PaneViewJobDetails.class);
    jobApply = PageFactory.initElements(driver, JobApply.class);
    paneViewFilterPanel = PageFactory.initElements(driver, PaneViewFilterPanel.class);
    paneViewMiniFooter = PageFactory.initElements(driver, PaneViewMiniFooter.class);
  }

  public JobAlertPopUp getJobAlertPopUp() {
    return jobAlertPopUp;
  }

  public PaneViewJobList getPaneViewJobList() {
    return paneViewJobList;
  }

  public PaneViewJobSearchPanel getPaneViewJobSearchPanel() {
    return paneViewJobSearchPanel;
  }

  public PaneViewJobDetails getPaneViewJobDetails() {
    return paneViewJobDetails;
  }

  public JobApply getJobApply() {
    return jobApply;
  }

  public PaneViewFilterPanel getPaneViewFilterPanel() {
    return paneViewFilterPanel;
  }

  public PaneViewMiniFooter getPaneViewMiniFooter() {
    return paneViewMiniFooter;
  }
}

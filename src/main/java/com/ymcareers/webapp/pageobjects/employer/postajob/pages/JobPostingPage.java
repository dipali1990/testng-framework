package com.ymcareers.webapp.pageobjects.employer.postajob.pages;

import com.ymcareers.webapp.pageobjects.employer.postajob.components.Applications;
import com.ymcareers.webapp.pageobjects.employer.postajob.components.ContactInformation;
import com.ymcareers.webapp.pageobjects.employer.postajob.components.ErrorMessage;
import com.ymcareers.webapp.pageobjects.employer.postajob.components.JobBasics;
import com.ymcareers.webapp.pageobjects.employer.postajob.components.JobDescription;
import com.ymcareers.webapp.pageobjects.employer.postajob.components.JobDetails;
import com.ymcareers.webapp.pageobjects.employer.postajob.components.JobRequirements;
import com.ymcareers.webapp.pageobjects.employer.postajob.components.Location;
import com.ymcareers.webapp.pageobjects.employer.postajob.components.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component Class. */
public class JobPostingPage {

  private final Settings settings;
  private final JobBasics jobBasics;
  private final JobDescription jobDescription;
  private final JobRequirements jobRequirements;
  private final JobDetails jobDetails;
  private final Location location;
  private final ContactInformation contactInformation;
  private final Applications applications;
  private final ErrorMessage errorMessage;

  /**
   * Initializes all components for Resume Bank Page.
   *
   * @param driver WebDriver Instance
   */
  public JobPostingPage(WebDriver driver) {
    settings = PageFactory.initElements(driver, Settings.class);
    jobBasics = PageFactory.initElements(driver, JobBasics.class);
    jobDescription = PageFactory.initElements(driver, JobDescription.class);
    jobRequirements = PageFactory.initElements(driver, JobRequirements.class);
    jobDetails = PageFactory.initElements(driver, JobDetails.class);
    location = PageFactory.initElements(driver, Location.class);
    contactInformation = PageFactory.initElements(driver, ContactInformation.class);
    applications = PageFactory.initElements(driver, Applications.class);
    errorMessage = PageFactory.initElements(driver, ErrorMessage.class);
  }

  public Settings getSettings() {
    return settings;
  }

  public JobBasics getJobBasics() {
    return jobBasics;
  }

  public JobDescription getJobDescription() {
    return jobDescription;
  }

  public JobRequirements getJobRequirements() {
    return jobRequirements;
  }

  public JobDetails getJobDetails() {
    return jobDetails;
  }

  public Location getLocation() {
    return location;
  }

  public ContactInformation getContactInformation() {
    return contactInformation;
  }

  public Applications getApplications() {
    return applications;
  }

  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }
}

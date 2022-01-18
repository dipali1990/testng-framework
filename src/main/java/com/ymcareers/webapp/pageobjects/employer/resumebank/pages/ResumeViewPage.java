package com.ymcareers.webapp.pageobjects.employer.resumebank.pages;

import com.ymcareers.webapp.pageobjects.employer.resumebank.components.CandidateDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component Class. */
public class ResumeViewPage {

  private final CandidateDetails candidateDetails;

  /**
   * Initializes all components for Resume Bank Page.
   *
   * @param driver WebDriver Instance
   */
  public ResumeViewPage(WebDriver driver) {
    candidateDetails = PageFactory.initElements(driver, CandidateDetails.class);
  }

  public CandidateDetails getCandidateDetails() {
    return candidateDetails;
  }
}

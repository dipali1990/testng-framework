package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.pages;

import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.ContactInformationSection;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.CurrentLocationSection;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.DetailsSection;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.EducationSection;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.ResumeNameSection;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.SummarySection;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.WorkHistorySection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all Components for Resume Builder Page. */
public class ResumeBuilderPage {
  private final ResumeNameSection resumeNameSection;
  private final ContactInformationSection contactInformationSection;
  private final CurrentLocationSection currentLocationSection;
  private final SummarySection summarySection;
  private final WorkHistorySection workHistorySection;
  private final EducationSection educationSection;
  private final DetailsSection detailsSection;

  /**
   * This constructor will initiate all component present on the Resume Builder Page.
   *
   * @param driver WebDriver Instance
   */
  public ResumeBuilderPage(WebDriver driver) {
    resumeNameSection = PageFactory.initElements(driver, ResumeNameSection.class);
    contactInformationSection = PageFactory.initElements(driver, ContactInformationSection.class);
    currentLocationSection = PageFactory.initElements(driver, CurrentLocationSection.class);
    summarySection = PageFactory.initElements(driver, SummarySection.class);
    workHistorySection = PageFactory.initElements(driver, WorkHistorySection.class);
    educationSection = PageFactory.initElements(driver, EducationSection.class);
    detailsSection = PageFactory.initElements(driver, DetailsSection.class);
  }

  public ResumeNameSection getResumeNameSection() {
    return resumeNameSection;
  }

  public ContactInformationSection getContactInformationSection() {
    return contactInformationSection;
  }

  public CurrentLocationSection getCurrentLocationSection() {
    return currentLocationSection;
  }

  public SummarySection getSummarySection() {
    return summarySection;
  }

  public WorkHistorySection getWorkHistorySection() {
    return workHistorySection;
  }

  public EducationSection getEducationSection() {
    return educationSection;
  }

  public DetailsSection getDetailsSection() {
    return detailsSection;
  }
}

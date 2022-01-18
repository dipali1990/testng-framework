package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.pages;

import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.CoverLetter;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.CreateResume;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.IncompleteResumes;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.PrivateResume;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.PublicResume;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.SuccessMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all WebElements available on the Manage Resumes Page. */
public class ManageResumesPage {
  private final CreateResume createResume;
  private final SuccessMessage successMessage;
  private final PublicResume publicResume;
  private final PrivateResume privateResume;
  private final CoverLetter coverLetter;
  private final IncompleteResumes incompleteResumes;

  /**
   * Constructor initiates WebElements of all Components.
   *
   * @param driver WebDriver Instance
   */
  public ManageResumesPage(WebDriver driver) {
    createResume = PageFactory.initElements(driver, CreateResume.class);
    successMessage = PageFactory.initElements(driver, SuccessMessage.class);
    publicResume = PageFactory.initElements(driver, PublicResume.class);
    privateResume = PageFactory.initElements(driver, PrivateResume.class);
    coverLetter = PageFactory.initElements(driver, CoverLetter.class);
    incompleteResumes = PageFactory.initElements(driver, IncompleteResumes.class);
  }

  public CreateResume getUploadResumeFile() {
    return createResume;
  }

  public SuccessMessage getSuccessMessage() {
    return successMessage;
  }

  public PublicResume getPublicResume() {
    return publicResume;
  }

  public PrivateResume getPrivateResume() {
    return privateResume;
  }

  public CoverLetter getCoverLetter() {
    return coverLetter;
  }

  public IncompleteResumes getIncompleteResumes() {
    return incompleteResumes;
  }
}

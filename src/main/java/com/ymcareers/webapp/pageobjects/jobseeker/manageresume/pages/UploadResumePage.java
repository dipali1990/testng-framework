package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.pages;

import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.EditUploadedResume;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.UploadResume;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all WebElements for the Upload Resume Page. */
public class UploadResumePage {
  private final UploadResume uploadResume;
  private final EditUploadedResume editUploadedResume;

  public UploadResumePage(WebDriver driver) {
    uploadResume = PageFactory.initElements(driver, UploadResume.class);
    editUploadedResume = PageFactory.initElements(driver, EditUploadedResume.class);
  }

  public UploadResume getUploadResume() {
    return uploadResume;
  }

  public EditUploadedResume getEditUploadedResume() {
    return editUploadedResume;
  }
}

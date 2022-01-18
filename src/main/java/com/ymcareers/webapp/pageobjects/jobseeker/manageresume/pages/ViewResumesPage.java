package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.pages;

import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components.ViewResume;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all WebElements available on the View Resumes Page. */
public class ViewResumesPage {
  private final ViewResume viewResume;

  public ViewResumesPage(WebDriver driver) {
    viewResume = PageFactory.initElements(driver, ViewResume.class);
  }

  public ViewResume getViewResume() {
    return viewResume;
  }
}

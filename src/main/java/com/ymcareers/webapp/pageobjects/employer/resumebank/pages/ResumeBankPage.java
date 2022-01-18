package com.ymcareers.webapp.pageobjects.employer.resumebank.pages;

import com.ymcareers.webapp.pageobjects.employer.resumebank.components.Filter;
import com.ymcareers.webapp.pageobjects.employer.resumebank.components.ResumeResult;
import com.ymcareers.webapp.pageobjects.employer.resumebank.components.ToolTip;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates WebElements of Component Class. */
public class ResumeBankPage {

  private final ToolTip toolTip;
  private final Filter filter;
  private final ResumeResult resumeResult;

  /**
   * Initializes all components for Resume Bank Page.
   *
   * @param driver WebDriver Instance
   */
  public ResumeBankPage(WebDriver driver) {
    toolTip = PageFactory.initElements(driver, ToolTip.class);
    filter = PageFactory.initElements(driver, Filter.class);
    resumeResult = PageFactory.initElements(driver, ResumeResult.class);
  }

  public ToolTip getToolTip() {
    return toolTip;
  }

  public Filter getFilter() {
    return filter;
  }

  public ResumeResult getResumeResult() {
    return resumeResult;
  }
}

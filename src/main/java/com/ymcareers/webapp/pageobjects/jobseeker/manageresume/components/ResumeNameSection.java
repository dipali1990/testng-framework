package com.ymcareers.webapp.pageobjects.jobseeker.manageresume.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class contains methods to fill the Resume Name. */
public class ResumeNameSection extends ComponentValidator {

  @FindBy(id = "resumeForm")
  WebElement resumeForm;

  @FindBy(id = "settings_name")
  WebElement resumeName;

  public ResumeNameSection(WebDriver driver) {
    super(driver);
  }

  String attribute = "value";
  WebActions webActions = new WebActions();
  String randomName = webActions.generateRandomName();

  /** Method enters Resume Name if it's not present. */
  public String enterResumeName() {
    String resume;
    if (resumeName.getAttribute(attribute).equals("")) {
      resume = "Resume Automation_" + randomName;
      resumeName.sendKeys(resume);
      log.info("Resume Name is entered : " + randomName);
    } else {
      resume = resumeName.getAttribute(attribute);
      log.info("Resume Name is already Present : " + resume);
    }
    return resume;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(resumeForm));
    return resumeForm.isDisplayed();
  }
}

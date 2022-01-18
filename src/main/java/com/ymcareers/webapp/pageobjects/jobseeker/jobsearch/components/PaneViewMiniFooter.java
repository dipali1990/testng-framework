package com.ymcareers.webapp.pageobjects.jobseeker.jobsearch.components;

import com.ymcareers.webapp.core.ComponentValidator;
import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** This class has WebElements for Filter Panel on Job Search Page. */
public final class PaneViewMiniFooter extends ComponentValidator {

  @FindBy(xpath = "//strong[text()='More']")
  WebElement moreLink;

  @FindBy(css = ".expanded-footer.show-footer")
  WebElement expandedFooter;

  WebActions actions = new WebActions();

  public PaneViewMiniFooter(final WebDriver driver) {
    super(driver);
  }

  /**
   * Method Verifies Mini Footer.
   *
   * @return Boolean Value
   */
  public boolean verifyMiniFooter() {
    boolean flag = false;
    actions.moveToElement(moreLink);
    moreLink.click();
    try {
      wait.until(ExpectedConditions.visibilityOf(expandedFooter));
      expandedFooter.isDisplayed();
      flag = true;
    } catch (Exception e) {
      log.info("Footer Not Displayed after clicking on 'More' link");
    }
    return flag;
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(moreLink));
    return moreLink.isDisplayed();
  }
}

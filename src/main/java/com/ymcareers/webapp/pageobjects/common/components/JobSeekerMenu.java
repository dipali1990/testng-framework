package com.ymcareers.webapp.pageobjects.common.components;

import com.ymcareers.webapp.core.ComponentValidator;
import java.util.List;

import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.*;

/** Job Seeker Menu handled by this class. */
public class JobSeekerMenu extends ComponentValidator {
  @FindBy(xpath = "//nav[@id='menu']//following::a[contains(text(),'Seeker')]")
  WebElement jobseeker;

  @FindBy(xpath = "//a[contains(text(),'Seeker')]//following-sibling::ul")
  WebElement jobseekerSubMenu;


  public JobSeekerMenu(WebDriver driver) {
    super(driver);
  }

  WebActions webActions = new WebActions();

  /**
   * This method is used to select any Job Seeker Option from the dropdown.
   *
   * @param menu Option Name
   */

  public void navigateTo(String menu) {
    webActions.moveToElement(jobseeker);
    wait.until(ExpectedConditions.visibilityOf(jobseekerSubMenu));
    List<WebElement> jsOptions = jobseekerSubMenu.findElements(By.tagName("a"));
    for (WebElement option : jsOptions) {
      String value = option.getAttribute("href");
      if (option.getAttribute("href").contains(menu)) {
        option.click();
        log.info("Clicked '" + option + "'");
        break;
      }
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(jobseeker));
    return jobseeker.isDisplayed();
  }
}

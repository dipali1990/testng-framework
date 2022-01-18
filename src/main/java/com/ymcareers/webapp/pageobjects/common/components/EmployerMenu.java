package com.ymcareers.webapp.pageobjects.common.components;

import com.ymcareers.webapp.core.ComponentValidator;
import java.util.List;

import com.ymcareers.webapp.core.WebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** Employer Menu handled by this class. */
public class EmployerMenu extends ComponentValidator {

  @FindBy(xpath = "//nav[@id='menu']//following::a[contains(text(),'Employers')]")
  WebElement empMenu;

  @FindBy(xpath = "//a[contains(text(),'Employer')]//following-sibling::ul")
  WebElement empSubMenu;

  public EmployerMenu(WebDriver driver) {
    super(driver);
  }

  WebActions webActions = new WebActions();

  /**
   * This method is used to select any Employer Option from the dropdown.
   *
   * @param menu Option Name
   */
  public void navigateTo(String menu) {
    webActions.moveToElement(empMenu);
    wait.until(ExpectedConditions.visibilityOf(empSubMenu));
    List<WebElement> jsOptions = empSubMenu.findElements(By.tagName("a"));
    for (WebElement option : jsOptions) {
      if (option.getAttribute("href").contains(menu)) {
        option.click();
        log.info("Clicked '" + option + "'");
        break;
      }
    }
  }

  @Override
  public boolean isDisplayed() {
    wait.until(ExpectedConditions.visibilityOf(empMenu));
    return empMenu.isDisplayed();
  }
}

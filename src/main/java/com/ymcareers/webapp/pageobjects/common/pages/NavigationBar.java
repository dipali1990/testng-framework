package com.ymcareers.webapp.pageobjects.common.pages;

import com.ymcareers.webapp.pageobjects.common.components.EmployerMenu;
import com.ymcareers.webapp.pageobjects.common.components.JobSeekerMenu;
import com.ymcareers.webapp.pageobjects.common.components.WelcomeMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** All WebElements will be instantiated in this Page Class. */
public class NavigationBar {
  private final EmployerMenu employerMenu;
  private final JobSeekerMenu jobSeekerMenu;
  private final WelcomeMenu welcomeMenu;

  /**
   * This constructor will instantiate WebElement of the Components.
   *
   * @param driver WebDriver Instance
   */
  public NavigationBar(WebDriver driver) {
    employerMenu = PageFactory.initElements(driver, EmployerMenu.class);
    jobSeekerMenu = PageFactory.initElements(driver, JobSeekerMenu.class);
    welcomeMenu = PageFactory.initElements(driver, WelcomeMenu.class);
  }

  public EmployerMenu getEmployerMenu() {
    return employerMenu;
  }

  public JobSeekerMenu getJobSeekerMenu() {
    return jobSeekerMenu;
  }

  public WelcomeMenu getWelcomeMenu() {
    return welcomeMenu;
  }
}

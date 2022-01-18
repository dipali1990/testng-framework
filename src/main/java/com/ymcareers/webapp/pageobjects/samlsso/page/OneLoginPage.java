package com.ymcareers.webapp.pageobjects.samlsso.page;

import com.ymcareers.webapp.pageobjects.samlsso.components.OneLogIn;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all WebElements on the One Login Page. */
public class OneLoginPage {

  private final OneLogIn oneLogIn;

  public OneLoginPage(WebDriver driver) {
    oneLogIn = PageFactory.initElements(driver, OneLogIn.class);
  }

  public OneLogIn getOneLogIn() {
    return oneLogIn;
  }
}

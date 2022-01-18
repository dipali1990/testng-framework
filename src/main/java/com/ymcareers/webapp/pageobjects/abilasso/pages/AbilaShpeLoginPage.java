package com.ymcareers.webapp.pageobjects.abilasso.pages;

import com.ymcareers.webapp.pageobjects.abilasso.components.AbilaShpe;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiate factory elements of components class. */
public class AbilaShpeLoginPage {

  private final AbilaShpe abilashpe;

  public AbilaShpeLoginPage(WebDriver driver) {
    abilashpe = PageFactory.initElements(driver, AbilaShpe.class);
  }

  public AbilaShpe getAbilashpe() {
    return abilashpe;
  }
}

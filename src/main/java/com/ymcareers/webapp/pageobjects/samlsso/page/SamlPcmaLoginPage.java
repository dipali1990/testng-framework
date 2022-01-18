package com.ymcareers.webapp.pageobjects.samlsso.page;

import com.ymcareers.webapp.pageobjects.samlsso.components.SamlPcma;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/** This class initiates all WebElements for PCMA site. */
public class SamlPcmaLoginPage {

  private final SamlPcma samlpcma;

  public SamlPcmaLoginPage(WebDriver driver) {
    samlpcma = PageFactory.initElements(driver, SamlPcma.class);
  }

  public SamlPcma getSamlpcma() {
    return samlpcma;
  }
}

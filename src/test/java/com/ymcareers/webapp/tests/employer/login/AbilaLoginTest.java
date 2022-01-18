package com.ymcareers.webapp.tests.employer.login;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.abilasso.pages.AbilaShpeLoginPage;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerDualLoginPage;
import com.ymcareers.webapp.pageobjects.employer.myaccount.pages.EmployerMyAccountPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AbilaLoginTest extends Base {
  private HomePage homePage;
  private NavigationBar navigationBar;
  private EmployerDualLoginPage employerDualLoginPage;
  private AbilaShpeLoginPage abilashpeloginPage;
  private EmployerMyAccountPage employerMyAccountPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    employerDualLoginPage = new EmployerDualLoginPage(DriverManager.getDriver());
    abilashpeloginPage = new AbilaShpeLoginPage(DriverManager.getDriver());
    employerMyAccountPage = new EmployerMyAccountPage(DriverManager.getDriver());
  }

  @Test
  public void empAbilaDualLoginTest() {
    log =
        LogManager.getLogger(
            "Abila Employer (Dual Login) : "
                + "Verify Employer is able to Login as a Member using Abila SSO");
    logger =
        extent.startTest(
            "Abila JobSeeker (Dual Login) : "
                + "Verify Employer is able to Login as a Member using Abila SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerDualLoginPage.getMemberLogin().isDisplayed(),
        "Unable to locate Member Login Button");
    employerDualLoginPage.getMemberLogin().selectMemberLogin();
    Assert.assertTrue(
        abilashpeloginPage.getAbilashpe().isDisplayed(), "Unable to navigate to Abila Login Page");
    abilashpeloginPage
        .getAbilashpe()
        .shpeLogin(prop.getProperty("usernameShpe"), prop.getProperty("pwdShpe"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Employer has successfully logged in using Abila SSO with Dual Login");
    logger.log(
        LogStatus.PASS, "Employer has successfully logged in using Abila SSO with Dual Login");
  }

  @Test
  public void empAbilaLogin_CompleteSSO() {
    log = LogManager.getLogger("Verify Employer is able to login via Complete Abila SSO");
    logger = extent.startTest("Verify Employer is able to login via Complete Abila SSO");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        abilashpeloginPage.getAbilashpe().isDisplayed(), "Unable to navigate to Abila Login Page");
    abilashpeloginPage
        .getAbilashpe()
        .shpeLogin(prop.getProperty("usernameAmia"), prop.getProperty("pwdAmia"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(navigationBar.getWelcomeMenu().isDisplayed(), "Unable to Sign-in");
    log.info("Employer has successfully logged in using Abila SSO with Complete SSO");
    logger.log(
        LogStatus.PASS, "Employer has successfully logged in using Abila SSO with Complete SSO");
  }
}

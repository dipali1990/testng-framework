package com.ymcareers.webapp.tests.employer.resumebank;

import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.employer.login.pages.EmployerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.employer.myaccount.pages.EmployerMyAccountPage;
import com.ymcareers.webapp.pageobjects.employer.resumebank.pages.ResumeBankPage;
import com.ymcareers.webapp.pageobjects.employer.resumebank.pages.ContactRequestPage;
import com.ymcareers.webapp.pageobjects.employer.resumebank.pages.ResumeViewPage;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.payment.pages.PaymentsPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

public class ResumeBankTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private EmployerCareersLoginPage employerCareersLoginPage;
  private EmployerMyAccountPage employerMyAccountPage;
  private ResumeBankPage resumeBankPage;
  private ResumeViewPage resumeViewPage;
  private ContactRequestPage contactRequestPage;
  private PaymentsPage paymentsPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    employerCareersLoginPage = new EmployerCareersLoginPage(DriverManager.getDriver());
    employerMyAccountPage = new EmployerMyAccountPage(DriverManager.getDriver());
    resumeBankPage = new ResumeBankPage(DriverManager.getDriver());
    resumeViewPage = new ResumeViewPage(DriverManager.getDriver());
    contactRequestPage = new ContactRequestPage(DriverManager.getDriver());
    paymentsPage = new PaymentsPage(DriverManager.getDriver());
  }

  @Test
  public void verifyToolTip() {
    log = LogManager.getLogger("Verify ToolTip is displayed on the Resume Bank Page");
    logger = extent.startTest("Verify ToolTip is displayed on the Resume Bank Page");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer Navigated to the Resume Bank Page");
    Assert.assertTrue(resumeBankPage.getToolTip().isDisplayed(), "Unable to view tooltip");
    logger.log(LogStatus.PASS, "Tooltip is displayed on the Resume Bank Page");
    Assert.assertTrue(
        resumeBankPage.getToolTip().handleTutorialTooltip(), "Unable to complete tooltip tour");
    log.info("Employer successfully completed the tour");
    logger.log(LogStatus.PASS, "Employer successfully completed the tour");
  }

  @Test
  public void filterByBookmarkedOption() throws InterruptedException {
    log = LogManager.getLogger("Verify Employer is able to apply 'Bookmarked' filter");
    logger = extent.startTest("Verify Employer is able to apply 'Bookmarked' filter");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getFilter().selectFilterButton();
    Assert.assertTrue(
        resumeBankPage.getFilter().isDisplayed(), "Filter Panel couldn't be displayed");
    logger.log(LogStatus.PASS, "Employer clicked on Filter button & Filter Panel displayed");
    Assert.assertTrue(
        resumeBankPage.getFilter().applyBookmarkedFilter(), "Unable to apply 'Bookmarked' filter");
    logger.log(LogStatus.PASS, "Clicked 'Bookmarked' option");
    String msg = resumeBankPage.getResumeResult().getResult();
    log.info("Employer successfully applied 'Bookmarked' filter");
    logger.log(LogStatus.PASS, "Employer successfully applied 'Bookmarked' filter. Result: " + msg);
  }

  @Test
  public void filterByViewedOption() throws InterruptedException {
    log = LogManager.getLogger("Verify Employer is able to apply 'Viewed' filter");
    logger = extent.startTest("Verify Employer is able to apply 'Viewed' filter");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getFilter().selectFilterButton();
    Assert.assertTrue(
        resumeBankPage.getFilter().isDisplayed(), "Filter Panel couldn't be displayed");
    logger.log(LogStatus.PASS, "Employer clicked on Filter button & Filter Panel displayed");
    Assert.assertTrue(
        resumeBankPage.getFilter().applyViewedFilter(), "Unable to apply 'Viewed' filter");
    logger.log(LogStatus.PASS, "Clicked 'Viewed' option");
    String msg = resumeBankPage.getResumeResult().getResult();
    log.info("Employer successfully applied 'Viewed' filter");
    logger.log(LogStatus.PASS, "Employer successfully applied 'Viewed' filter. Result: " + msg);
  }

  @Parameters({"country"})
  @Test
  public void filterByCountryOption(String country) throws InterruptedException {
    log = LogManager.getLogger("Verify Employer is able to apply 'Country' filter");
    logger = extent.startTest("Verify Employer is able to apply 'Country' filter");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getFilter().selectFilterButton();
    Assert.assertTrue(
        resumeBankPage.getFilter().isDisplayed(), "Filter Panel couldn't be displayed");
    logger.log(LogStatus.PASS, "Employer clicked on Filter button & Filter Panel displayed");
    Assert.assertTrue(
        resumeBankPage.getFilter().applyCountryFilter(country), "Unable to apply 'Country' filter");
    logger.log(LogStatus.PASS, "Clicked 'Country' option");
    String msg = resumeBankPage.getResumeResult().getResult();
    log.info("Employer successfully applied 'Country' filter");
    logger.log(LogStatus.PASS, "Employer successfully applied 'Country' filter. Result: " + msg);
  }

  @Parameters({"state"})
  @Test
  public void filterByStateOption(String state) throws InterruptedException {
    log = LogManager.getLogger("Verify Employer is able to apply 'State' filter");
    logger = extent.startTest("Verify Employer is able to apply 'State' filter");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getFilter().selectFilterButton();
    Assert.assertTrue(
        resumeBankPage.getFilter().isDisplayed(), "Filter Panel couldn't be displayed");
    logger.log(LogStatus.PASS, "Employer clicked on Filter button & Filter Panel displayed");
    Assert.assertTrue(
        resumeBankPage.getFilter().applyStateFilter(state), "Unable to apply 'State' filter");
    logger.log(LogStatus.PASS, "Clicked 'State' option");
    String msg = resumeBankPage.getResumeResult().getResult();
    log.info("Employer successfully applied 'State' filter");
    logger.log(LogStatus.PASS, "Employer successfully applied 'State' filter. Result: " + msg);
  }

  @Parameters({"lastUpdated"})
  @Test
  public void filterByLastUpdatedOption(String lastUpdated) throws InterruptedException {
    log = LogManager.getLogger("Verify Employer is able to apply 'Last Updated' filter");
    logger = extent.startTest("Verify Employer is able to apply 'Last Updated' filter");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getFilter().selectFilterButton();
    Assert.assertTrue(
        resumeBankPage.getFilter().isDisplayed(), "Filter Panel couldn't be displayed");
    logger.log(LogStatus.PASS, "Employer clicked on Filter button & Filter Panel displayed");
    Assert.assertTrue(
        resumeBankPage.getFilter().applyLastUpdatedFilter(lastUpdated),
        "Unable to apply 'Last Updated' filter");
    logger.log(LogStatus.PASS, "Clicked 'Last Updated' option");
    String msg = resumeBankPage.getResumeResult().getResult();
    log.info("Employer successfully applied 'Last Updated' filter");
    logger.log(
        LogStatus.PASS, "Employer successfully applied 'Last Updated' filter. Result: " + msg);
  }

  @Test
  public void disabledSaveSearchButton() {
    log =
        LogManager.getLogger("Verify 'Save Search' button is disabled before applying any filter");
    logger = extent.startTest("Verify 'Save Search' button is disabled before applying any filter");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getFilter().selectFilterButton();
    Assert.assertTrue(
        resumeBankPage.getFilter().isDisplayed(), "Filter Panel couldn't be displayed");
    logger.log(LogStatus.PASS, "Employer clicked on Filter button & Filter Panel displayed");
    Assert.assertTrue(
        resumeBankPage.getFilter().verifySavedSearchButtonIsDisabled(),
        "'Save Search' button is not disabled");
    log.info("'Save Search' button is disabled before applying any filter");
    logger.log(LogStatus.PASS, "'Save Search' button is disabled before applying any filter");
  }

  @Test
  public void saveFilterSearch() {
    log = LogManager.getLogger("Verify if employer is able to save the search");
    logger = extent.startTest("Verify if employer is able to save the search");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    navigationBar.getEmployerMenu().navigateTo("resume");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getFilter().selectFilterButton();
    Assert.assertTrue(
        resumeBankPage.getFilter().isDisplayed(), "Filter Panel couldn't be displayed");
    logger.log(LogStatus.PASS, "Employer clicked on Filter button & Filter Panel displayed");
    Assert.assertTrue(resumeBankPage.getFilter().saveSearch(), "Unable to save the search");
    log.info("Employer is able to save the search");
    logger.log(LogStatus.PASS, "Employer is able to save the search");
  }

  @Test
  public void viewResumeEmpNotLoggedIn() {
    log = LogManager.getLogger("Verify if Employer prompted to login while viewing the resume");
    logger = extent.startTest("Verify if Employer prompted to login while viewing the resume");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getToolTip().skipToolTip();
    logger.log(LogStatus.PASS, "Skipped tooltip tutorial");
    Assert.assertTrue(resumeBankPage.getResumeResult().viewResume(), "Resumes are not available");
    logger.log(LogStatus.PASS, "Clicked 'View Candidate' option");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    logger.log(LogStatus.PASS, "Employer Prompted to Login");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(
        LogStatus.PASS, "Employer logged in successfully & navigated to the Resume Bank Page");
    String candidateId = resumeViewPage.getCandidateDetails().getCandidateId();
    Assert.assertNotEquals(candidateId, "");
    log.info("Employer is able to view the resume. Candidate id : " + candidateId);
    logger.log(
        LogStatus.PASS, "Employer is able to view the resume. Candidate id : " + candidateId);
  }

  @Test
  public void buyResumeFromResumeBank() {
    log = LogManager.getLogger("Verify if Employer is able to buy resume from the Resume Bank");
    logger = extent.startTest("Verify if Employer is able to buy resume from the Resume Bank");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("account");
    logger.log(LogStatus.PASS, "Employer navigated to the Login Page");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(LogStatus.PASS, "Employer logged in successfully");
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    Assert.assertTrue(resumeBankPage.getResumeResult().viewResume(), "Unable to view resume");
    Assert.assertTrue(
        resumeViewPage.getCandidateDetails().addToCart(), "Unable to add resume to the cart");
    logger.log(LogStatus.PASS, "Resume Added to the Cart");
    resumeViewPage.getCandidateDetails().viewCart();
    logger.log(LogStatus.PASS, "Clicked 'View Cart'");
    contactRequestPage.getResumeBankCart().createContactRequest();
    contactRequestPage
        .getResumeBankCart()
        .enterCompanyDetails(
            prop.getProperty("companyName"),
            prop.getProperty("positionTitle"),
            prop.getProperty("jobDescription"));
    logger.log(LogStatus.PASS, "Created Contact request");
    paymentsPage
        .getCreditCard()
        .payByCreditCard(
            prop.getProperty("cardNumber"), prop.getProperty("cardType"), prop.getProperty("cvv"));
    logger.log(LogStatus.PASS, "Entered Credit Card details and Clicked on 'Pay'");
    Assert.assertTrue(paymentsPage.getCreditCard().verifyOrderPlaced(), "Unable to complete order");
    log.info("Employer is able to buy the resume.");
    logger.log(LogStatus.PASS, "Employer is able to buy the resume.");
  }

  @Test
  public void resumeBankPrintResume() {
    log = LogManager.getLogger("Verify if Employer is able to print candidate's resume");
    logger = extent.startTest("Verify if Employer is able to print candidate's resume");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getToolTip().skipToolTip();
    logger.log(LogStatus.PASS, "Skipped tooltip tutorial");
    Assert.assertTrue(resumeBankPage.getResumeResult().viewResume(), "Resumes are not available");
    logger.log(LogStatus.PASS, "Clicked 'View Candidate' option");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    logger.log(LogStatus.PASS, "Employer Prompted to Login");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(
        LogStatus.PASS, "Employer logged in successfully & navigated to the Resume Bank Page");
    Assert.assertTrue(
        resumeViewPage.getCandidateDetails().printResume(), "Unable to print the resume");
    log.info("Employer is able to print candidate's resume");
    logger.log(LogStatus.PASS, "Employer is able to print candidate's resume");
  }

  @Test
  public void resumeBankPdfFormat() {
    log = LogManager.getLogger("Verify if Employer is able to download the resume");
    logger = extent.startTest("Verify if Employer is able to download the resume");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getToolTip().skipToolTip();
    logger.log(LogStatus.PASS, "Skipped tooltip tutorial");
    Assert.assertTrue(resumeBankPage.getResumeResult().viewResume(), "Resumes are not available");
    logger.log(LogStatus.PASS, "Clicked 'View Candidate' option");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    logger.log(LogStatus.PASS, "Employer Prompted to Login");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(
        LogStatus.PASS, "Employer logged in successfully & navigated to the Resume Bank Page");
    Assert.assertTrue(
        resumeViewPage.getCandidateDetails().pdfFormatResume(), "Unable to download the resume");
    log.info("Employer is able to download the candidate's resume");
    logger.log(LogStatus.PASS, "Employer is able to download the candidate's resume");
  }

  @Test
  public void resumeBankFwdToColleague() {
    log = LogManager.getLogger("Verify if Employer is able to share the resume");
    logger = extent.startTest("Verify if Employer is able to share the resume");
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getEmployerMenu().navigateTo("resumes");
    logger.log(LogStatus.PASS, "Employer navigated to the Resume Bank Page");
    resumeBankPage.getToolTip().skipToolTip();
    logger.log(LogStatus.PASS, "Skipped tooltip tutorial");
    Assert.assertTrue(resumeBankPage.getResumeResult().viewResume(), "Resumes are not available");
    logger.log(LogStatus.PASS, "Clicked 'View Candidate' option");
    Assert.assertTrue(
        employerCareersLoginPage.getLoginPanel().isDisplayed(), "Unable to Navigate to Login Page");
    logger.log(LogStatus.PASS, "Employer Prompted to Login");
    employerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    employerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    logger.log(
        LogStatus.PASS, "Employer logged in successfully & navigated to the Resume Bank Page");
    Assert.assertTrue(
        resumeViewPage
            .getCandidateDetails()
            .forwardResumeToColleague(prop.getProperty("emailAddress")),
        "Unable to download the resume");
    log.info("Employer is able to share the candidate's resume");
    logger.log(LogStatus.PASS, "Employer is able to share the candidate's resume");
  }
}

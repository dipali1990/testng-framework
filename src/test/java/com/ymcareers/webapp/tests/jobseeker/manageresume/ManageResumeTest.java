package com.ymcareers.webapp.tests.jobseeker.manageresume;

import com.relevantcodes.extentreports.LogStatus;
import com.ymcareers.webapp.core.DriverManager;
import com.ymcareers.webapp.pageobjects.common.pages.NavigationBar;
import com.ymcareers.webapp.pageobjects.home.pages.HomePage;
import com.ymcareers.webapp.pageobjects.jobseeker.login.pages.JobSeekerCareersLoginPage;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.pages.ManageResumesPage;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.pages.ResumeBuilderPage;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.pages.UploadResumePage;
import com.ymcareers.webapp.pageobjects.jobseeker.manageresume.pages.ViewResumesPage;
import com.ymcareers.webapp.pageobjects.jobseeker.myaccount.pages.JobSeekerMyAccountPage;
import com.ymcareers.webapp.tests.core.Base;
import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ManageResumeTest extends Base {

  private HomePage homePage;
  private NavigationBar navigationBar;
  private JobSeekerCareersLoginPage jobSeekerCareersLoginPage;
  private JobSeekerMyAccountPage jobSeekerMyAccountPage;
  private ManageResumesPage manageResumesPage;
  private UploadResumePage uploadResumePage;
  private ResumeBuilderPage resumeBuilderPage;
  private ViewResumesPage viewResumesPage;

  @BeforeTest
  public void setUpPages() {
    homePage = new HomePage(DriverManager.getDriver());
    navigationBar = new NavigationBar(DriverManager.getDriver());
    jobSeekerCareersLoginPage = new JobSeekerCareersLoginPage(DriverManager.getDriver());
    jobSeekerMyAccountPage = new JobSeekerMyAccountPage(DriverManager.getDriver());
    manageResumesPage = new ManageResumesPage(DriverManager.getDriver());
    uploadResumePage = new UploadResumePage(DriverManager.getDriver());
    resumeBuilderPage = new ResumeBuilderPage(DriverManager.getDriver());
    viewResumesPage = new ViewResumesPage(DriverManager.getDriver());
  }

  @BeforeMethod
  public void navigateToManageResumesPage() {
    homePage.getCookieConsent().setCookieConsent();
    navigationBar.getJobSeekerMenu().navigateTo("resumes");
    Assert.assertTrue(
        jobSeekerCareersLoginPage.getLoginPanel().isDisplayed(),
        "Unable to Navigate to Login Page");
    jobSeekerCareersLoginPage
        .getLoginPanel()
        .signIn(prop.getProperty("stdUsername"), prop.getProperty("stdPassword"));
    jobSeekerMyAccountPage.getUserAgreementPopUp().handleUserAgreementConsent();
    Assert.assertTrue(
        manageResumesPage.getUploadResumeFile().isDisplayed(),
        "Unable to Navigate to Manage Resume Page");
    log.info("Job Seeker Navigated to Manage Resumes Page after Login");
  }

  @Test
  public void createResumeUsingUploadFileOption() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to create resume using Upload Resume File option");
    logger =
        extent.startTest(
            "Verify Job Seeker is able to create resume using Upload Resume File option");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    manageResumesPage.getUploadResumeFile().selectOptionToCreateResume("Upload");
    logger.log(LogStatus.PASS, "Job Seeker selected 'Upload Resume File' option");
    Assert.assertTrue(
        uploadResumePage.getUploadResume().isDisplayed(),
        "Unable to Navigate to Upload Resume Page");
    uploadResumePage.getUploadResume().uploadResumeFile(prop.getProperty("resumePath"));
    logger.log(LogStatus.PASS, "Job Seeker selected a resume file to upload");
    Assert.assertTrue(uploadResumePage.getEditUploadedResume().isDisplayed());
    uploadResumePage.getEditUploadedResume().saveUploadedResume();
    logger.log(
        LogStatus.PASS,
        "Job Seeker navigated to the Edit Resume Page & Clicked on 'Save My Resume'");
    Assert.assertTrue(resumeBuilderPage.getResumeNameSection().isDisplayed());
    String resume = resumeBuilderPage.getResumeNameSection().enterResumeName();
    logger.log(LogStatus.PASS, "Resume Name : " + resume);
    resumeBuilderPage
        .getContactInformationSection()
        .enterContactInformation(
            prop.getProperty("firstName"),
            prop.getProperty("lastName"),
            prop.getProperty("emailAddress"));
    logger.log(LogStatus.PASS, "Verified Mandatory Contact Information ");
    resumeBuilderPage
        .getCurrentLocationSection()
        .enterCurrentLocation(
            prop.getProperty("country"),
            prop.getProperty("state"),
            prop.getProperty("city"),
            prop.getProperty("workAuthorizationOption"));
    logger.log(LogStatus.PASS, "Verified Mandatory Current Location Information ");
    resumeBuilderPage
        .getSummarySection()
        .enterSummary(prop.getProperty("summary"), prop.getProperty("objective"));
    logger.log(LogStatus.PASS, "Verified Mandatory Summary Information ");
    resumeBuilderPage
        .getWorkHistorySection()
        .enterContactInformation(
            prop.getProperty("employerName"),
            prop.getProperty("positionTitle"),
            prop.getProperty("employerStartMonth"),
            prop.getProperty("employerStartYear"));
    logger.log(LogStatus.PASS, "Verified Mandatory Work History Details ");
    resumeBuilderPage.getEducationSection().enterSchoolDetails(prop.getProperty("institution"));
    resumeBuilderPage
        .getEducationSection()
        .enterDegreeDetails(
            prop.getProperty("degree"),
            prop.getProperty("educationCompletionMonth"),
            prop.getProperty("educationCompletionYear"));
    logger.log(LogStatus.PASS, "Verified Mandatory Education Details ");
    resumeBuilderPage.getEducationSection().removeExtraSchool();
    resumeBuilderPage
        .getDetailsSection()
        .enterDetailsSection(prop.getProperty("degree"), prop.getProperty("careerLevel"));
    logger.log(LogStatus.PASS, "Verified Mandatory Details ");
    resumeBuilderPage.getDetailsSection().selectRelocationOption(prop.getProperty("relocation"));
    logger.log(LogStatus.PASS, "Selected relocation option as : " + prop.getProperty("relocation"));
    resumeBuilderPage.getDetailsSection().submitResume();
    logger.log(LogStatus.PASS, "Clicked Submit Button");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSaved(),
        "Resume could not be created using Resume Builder Option.");
    log.info("Job Seeker created resume successfully using Upload Resume File.");
    logger.log(LogStatus.PASS, "Job Seeker created resume successfully using Upload Resume File.");
  }

  @Test
  public void createResumeUsingResumeBuilder() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to create resume using Resume Builder option");
    logger =
        extent.startTest("Verify Job Seeker is able to create resume using Resume Builder option");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    manageResumesPage.getUploadResumeFile().selectOptionToCreateResume("Build Resume");
    logger.log(LogStatus.PASS, "Selected 'Build Resume Online' Option");
    Assert.assertTrue(
        resumeBuilderPage.getResumeNameSection().isDisplayed(),
        "Unable to navigate to Resume Builder Page.");
    logger.log(LogStatus.PASS, "Navigated to the Create Resume Page");
    String resume = resumeBuilderPage.getResumeNameSection().enterResumeName();
    logger.log(LogStatus.PASS, "Entered Resume Name : " + resume);
    resumeBuilderPage
        .getContactInformationSection()
        .enterContactInformation(
            prop.getProperty("firstName"),
            prop.getProperty("lastName"),
            prop.getProperty("emailAddress"));
    logger.log(LogStatus.PASS, "Entered Mandatory Contact Information");
    resumeBuilderPage
        .getCurrentLocationSection()
        .enterCurrentLocation(
            prop.getProperty("country"),
            prop.getProperty("state"),
            prop.getProperty("city"),
            prop.getProperty("workAuthorizationOption"));
    logger.log(LogStatus.PASS, "Entered Mandatory Current Location Information");
    resumeBuilderPage
        .getSummarySection()
        .enterSummary(prop.getProperty("summary"), prop.getProperty("objective"));
    logger.log(LogStatus.PASS, "Entered Mandatory Summary Information");
    resumeBuilderPage
        .getWorkHistorySection()
        .enterContactInformation(
            prop.getProperty("employerName"),
            prop.getProperty("positionTitle"),
            prop.getProperty("employerStartMonth"),
            prop.getProperty("employerStartYear"));
    logger.log(LogStatus.PASS, "Entered Mandatory Work History Information");
    resumeBuilderPage.getEducationSection().enterSchoolDetails(prop.getProperty("institution"));
    resumeBuilderPage
        .getEducationSection()
        .enterDegreeDetails(
            prop.getProperty("degree"),
            prop.getProperty("educationCompletionMonth"),
            prop.getProperty("educationCompletionYear"));
    logger.log(LogStatus.PASS, "Entered Mandatory Education Information");
    resumeBuilderPage.getEducationSection().removeExtraSchool();
    resumeBuilderPage
        .getDetailsSection()
        .enterDetailsSection(prop.getProperty("degree"), prop.getProperty("careerLevel"));
    logger.log(LogStatus.PASS, "Entered Other Mandatory Details");
    resumeBuilderPage.getDetailsSection().selectRelocationOption(prop.getProperty("relocation"));
    logger.log(LogStatus.PASS, "Selected relocation option as : " + prop.getProperty("relocation"));
    resumeBuilderPage.getDetailsSection().submitResume();
    logger.log(LogStatus.PASS, "Clicked Submit Resume Button");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSaved(),
        "Resume could not be created using Resume Builder Option.");
    log.info("Job Seeker created resume successfully using Resume Builder.");
    logger.log(LogStatus.PASS, "Job Seeker created resume successfully using Resume Builder.");
  }

  @Test
  public void viewPublicResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to view public resume.");
    logger = extent.startTest("Verify Job Seeker is able to view public resume.");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().verifyViewPublicResumeOption(),
        "Unable to view Resume");
    log.info("Job Seeker Clicked on 'View' option and is able to view public resume");
    logger.log(
        LogStatus.PASS, "Job Seeker Clicked on 'View' option and is able to view public resume");
  }

  @Test
  public void editPublicResumeOnViewPage() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to Edit Public Resume from View Resume Page");
    logger =
        extent.startTest("Verify Job Seeker is able to Edit Public Resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().verifyViewPublicResumeOption(),
        "Unable to view Resume");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on 'View' Option");
    viewResumesPage.getViewResume().editResume();
    logger.log(
        LogStatus.PASS, "Job Seeker Clicked on 'Edit' Option available on the View Resume Page");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSaved(), "Unable to edit the resume.");
    log.info("Job Seeker is able to edit the resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is able to edit the resume from View Resume Page");
  }

  @Test
  public void emailPublicResumeOnViewPage() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to email Public Resume from View Resume Page");
    logger =
        extent.startTest("Verify Job Seeker is able to email Public Resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().verifyViewPublicResumeOption(),
        "Unable to view Resume");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on 'View' Option for Public Resume");
    viewResumesPage.getViewResume().emailResume(prop.getProperty("recipientEmail"));
    logger.log(
        LogStatus.PASS, "Job Seeker Clicked on 'Email' option available on the View Resume Page");
    logger.log(LogStatus.PASS, "Email Modal displayed and entered recipient mail id. ");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSent(prop.getProperty("recipientEmail")),
        "Resume couldn't be sent.");
    log.info("Job Seeker is able to email the resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is able to email the resume from View Resume Page");
  }

  @Test
  public void printPublicResumeOnViewPage() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to print Public Resume from View Resume Page");
    logger =
        extent.startTest("Verify Job Seeker is able to print Public Resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().verifyViewPublicResumeOption(),
        "Unable to view Resume");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on 'View' Option");
    Assert.assertTrue(viewResumesPage.getViewResume().printResume(), "Print is not clickable");
    logger.log(LogStatus.PASS, "Job Seeker is able to verify Print Option");
  }

  @Test
  public void editPublicResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to edit public resume.");
    logger = extent.startTest("Verify Job Seeker is able to edit public resume.");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    manageResumesPage.getPublicResume().editPublicResumeOption();
    logger.log(LogStatus.PASS, "Clicked 'Edit' option for the Public Resume");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSaved(), "Unable to edit the resume.");
    log.info("Job Seeker is able to edit public resume");
    logger.log(LogStatus.PASS, "Job Seeker is able to edit public resume. ");
  }

  @Test
  public void emailPublicResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to email public resume.");
    logger = extent.startTest("Verify Job Seeker is able to email public resume.");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    manageResumesPage.getPublicResume().emailPublicResumeOption(prop.getProperty("recipientEmail"));
    logger.log(
        LogStatus.PASS,
        "Clicked 'Email' option & Email Modal displayed and entered recipient email id");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSent(prop.getProperty("recipientEmail")),
        "Resume couldn't be sent.");
    logger.log(LogStatus.PASS, "Job Seeker is able to email public resume to the recruiter.");
  }

  @Test
  public void linkPublicResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to generate link public resume.");
    logger = extent.startTest("Verify Job Seeker is able to generate link public resume.");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    String link = manageResumesPage.getPublicResume().linkPublicResumeOption();
    log.info("Public Resume Link generated successfully");
    logger.log(
        LogStatus.PASS,
        "Clicked 'Link' Option & Public Resume Link generated successfully : " + link);
  }

  @Test
  public void unpublishPublicResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to make Public Resume Private");
    logger = extent.startTest("Verify Job Seeker is able to make Public Resume Private");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    manageResumesPage.getPublicResume().unpublishPublicResumeOption();
    logger.log(LogStatus.PASS, "Job Seeker successfully clicked on Unpublish link.");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    Assert.assertEquals(msg, "Resume unpublished.");
    log.info("Resume unpublished successfully");
    logger.log(
        LogStatus.PASS, "Resume unpublished successfully. Message displayed as '" + msg + "'");
  }

  @Test
  public void freeEvaluationPublicResume() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to click on Free Evaluation for Public Resume");
    logger =
        extent.startTest("Verify Job Seeker is able to click on Free Evaluation for Public Resume");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    manageResumesPage.getPublicResume().freeEvaluationOptionPublicResume();
    logger.log(LogStatus.PASS, "Job Seeker successfully clicked on Free Evaluation link.");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    logger.log(
        LogStatus.PASS, "Message displayed after clicking on 'Free Evaluation' button ----" + msg);
  }

  @Test
  public void viewPrivateResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to view private resume.");
    logger = extent.startTest("Verify Job Seeker is able to view private resume");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().verifyViewPrivateResumeOption(),
        "Unable to view Private Resume");
    log.info("Job Seeker is able to view private resume");
    logger.log(LogStatus.PASS, "Job Seeker clicked 'View' option & is able to view private resume");
  }

  @Test
  public void editPrivateResumeOnViewPage() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to Edit Private Resume from View Resume Page");
    logger =
        extent.startTest("Verify Job Seeker is able to Edit Private Resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().verifyViewPrivateResumeOption(),
        "Unable to view Resume");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on 'View' Option");
    viewResumesPage.getViewResume().editResume();
    logger.log(
        LogStatus.PASS, "Job Seeker Clicked on 'Edit' Option available on the View Resume Page");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSaved(), "Unable to edit the resume.");
    log.info("Job Seeker is able to edit the resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is able to edit the resume from View Resume Page");
  }

  @Test
  public void emailPrivateResumeOnViewPage() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to email Public Resume from View Resume Page");
    logger =
        extent.startTest("Verify Job Seeker is able to email Public Resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().verifyViewPrivateResumeOption(),
        "Unable to view Resume");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on 'View' Option");
    viewResumesPage.getViewResume().emailResume(prop.getProperty("recipientEmail"));
    logger.log(
        LogStatus.PASS, "Job Seeker Clicked on 'Email' option available on View Resume Page");
    logger.log(LogStatus.PASS, "Email Modal displayed and entered recipient email id");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSent(prop.getProperty("recipientEmail")),
        "Resume couldn't be sent.");
    log.info("Job Seeker is able to email the resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is able to email the resume from View Resume Page");
  }

  @Test
  public void printPrivateResumeOnViewPage() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to print Private Resume from View Resume Page");
    logger =
        extent.startTest("Verify Job Seeker is able to print Private Resume from View Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().verifyViewPrivateResumeOption(),
        "Unable to view Resume");
    logger.log(LogStatus.PASS, "Job Seeker Clicked on 'View' Option");
    Assert.assertTrue(viewResumesPage.getViewResume().printResume(), "Print is not clickable");
    logger.log(LogStatus.PASS, "Job Seeker is able to verify Print Option");
  }

  @Test
  public void editPrivateResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to edit private resume.");
    logger = extent.startTest("Verify Job Seeker is able to edit private resume.");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    manageResumesPage.getPrivateResume().editPrivateResumeOption();
    logger.log(LogStatus.PASS, "Job Seeker Clicked on 'Edit' Option");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSaved(), "Unable to edit the resume.");
    log.info("Job Seeker is able to edit private resume");
    logger.log(LogStatus.PASS, "Job Seeker is able to edit private resume. ");
  }

  @Test
  public void emailPrivateResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to email private resume.");
    logger = extent.startTest("Verify Job Seeker is able to email private resume.");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    manageResumesPage
        .getPrivateResume()
        .emailPrivateResumeOption(prop.getProperty("recipientEmail"));
    logger.log(
        LogStatus.PASS,
        "Clicked 'Email' Option & Email Modal displayed and entered recipient email id");
    Assert.assertTrue(
        manageResumesPage.getSuccessMessage().verifyResumeSent(prop.getProperty("recipientEmail")),
        "Private Resume couldn't be sent.");
    logger.log(LogStatus.PASS, "Job Seeker is able to email private resume to the recruiter.");
  }

  @Test
  public void linkPrivateResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to generate link for private resume.");
    logger = extent.startTest("Verify Job Seeker is able to generate link for private resume.");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    String link = manageResumesPage.getPrivateResume().linkPrivateResumeOption();
    logger.log(LogStatus.PASS, "Private Resume Link generated successfully : " + link);
  }

  @Test
  public void publishPrivateResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to make Private Resume Public");
    logger = extent.startTest("Verify Job Seeker is able to make Private Resume Public");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    manageResumesPage.getPrivateResume().publishPrivateResumeOption();
    logger.log(LogStatus.PASS, "Job Seeker clicked on Publish link.");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    Assert.assertEquals(msg, "Successfully published the resume.");
    logger.log(
        LogStatus.PASS, " Resume published successfully. Message displayed as '" + msg + "'");
  }

  @Test
  public void freeEvaluationPrivateResume() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to click on Free Evaluation for Private Resume");
    logger =
        extent.startTest(
            "Verify Job Seeker is able to click on Free Evaluation for Private Resume");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    manageResumesPage.getPrivateResume().freeEvaluationOptionPrivateResume();
    logger.log(LogStatus.PASS, "Job Seeker clicked on Free Evaluation link.");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    logger.log(
        LogStatus.PASS, " Message displayed after clicking on 'Free Evaluation' button ----" + msg);
  }

  @Test
  public void deletePublicResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to delete public resume");
    logger = extent.startTest("Verify Job Seeker is able to delete public resume");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPublicResume().isDisplayed(), "Public Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Public Resume Section");
    manageResumesPage.getPublicResume().deletePublicResumeOption();
    logger.log(LogStatus.PASS, "Clicked 'Delete' Option");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    Assert.assertEquals(msg, "Resume deleted.");
    log.info("Job Seeker is able to delete public resume");
    logger.log(LogStatus.PASS, "Job Seeker is able to delete public resume");
  }

  @Test
  public void deletePrivateResume() {
    log = LogManager.getLogger("Verify Job Seeker is able to delete private resume");
    logger = extent.startTest("Verify Job Seeker is able to delete private resume");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getPrivateResume().isDisplayed(), "Private Resume Unavailable.");
    logger.log(LogStatus.PASS, "Resume is present under Private Resume Section");
    manageResumesPage.getPrivateResume().deletePrivateResumeOption();
    logger.log(LogStatus.PASS, "Clicked 'Delete' Option");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    Assert.assertEquals(msg, "Resume deleted.");
    log.info("Job Seeker is able to delete private resume");
    logger.log(
        LogStatus.PASS,
        "Job Seeker is able to delete private resume & Message displayed as '" + msg + "'");
  }

  @Test
  public void incompleteResumes() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to see Incomplete Resumes on Manage Resumes Page");
    logger =
        extent.startTest(
            "Verify Job Seeker is able to see Incomplete Resumes on Manage Resumes Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    manageResumesPage.getUploadResumeFile().selectOptionToCreateResume("Upload");
    logger.log(LogStatus.PASS, "Clicked 'Upload Resume File' button");
    Assert.assertTrue(
        uploadResumePage.getUploadResume().isDisplayed(),
        "Unable to Navigate to Upload Resume Page");
    uploadResumePage.getUploadResume().uploadResumeFile(prop.getProperty("resumePath"));
    logger.log(LogStatus.PASS, "Job Seeker Uploaded the Resume file");
    Assert.assertTrue(uploadResumePage.getEditUploadedResume().isDisplayed());
    uploadResumePage.getEditUploadedResume().saveUploadedResume();
    logger.log(LogStatus.PASS, "Clicked 'Save My Resume' option");
    Assert.assertTrue(resumeBuilderPage.getResumeNameSection().isDisplayed());
    String resumeName = resumeBuilderPage.getResumeNameSection().enterResumeName();
    navigationBar.getJobSeekerMenu().navigateTo("resumes");
    logger.log(
        LogStatus.PASS,
        "Job Seeker navigated to the Manage Resumes Page without completing Resume Details");
    Assert.assertTrue(
        manageResumesPage.getIncompleteResumes().isDisplayed(),
        "Unable to locate Incomplete Resumes Section");
    Assert.assertEquals(
        manageResumesPage.getIncompleteResumes().viewIncompleteResumeName(), resumeName);
    log.info("Job Seeker is able to view resume in Incomplete Resumes Section.");
    logger.log(LogStatus.PASS, "Job Seeker is able to view resume in Incomplete Resumes Section.");
  }

  @Test
  public void editIncompleteResume() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to edit Incomplete Resumes on Manage Resumes Page");
    logger =
        extent.startTest(
            "Verify Job Seeker is able to edit Incomplete Resumes on Manage Resumes Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    logger.log(LogStatus.PASS, "Job Seeker navigated to Manage Resumes Page.");
    Assert.assertTrue(
        manageResumesPage.getIncompleteResumes().isDisplayed(),
        "Unable to locate Incomplete Resumes Section");
    manageResumesPage.getIncompleteResumes().editIncompleteResumeName();
    logger.log(LogStatus.PASS, "Job Seeker clicked on 'Edit' link of Incomplete Resume.");
    Assert.assertTrue(uploadResumePage.getEditUploadedResume().isDisplayed());
    logger.log(LogStatus.PASS, "Edit Resume Panel gets displayed.");
    uploadResumePage.getEditUploadedResume().saveUploadedResume();
    Assert.assertTrue(resumeBuilderPage.getResumeNameSection().isDisplayed());
    log.info("Job Seeker is able to Edit the Incomplete Resume");
    logger.log(LogStatus.PASS, "Job Seeker is able to Edit the Incomplete Resume");
  }

  @Test
  public void deleteIncompleteResume() {
    log =
        LogManager.getLogger(
            "Verify Job Seeker is able to delete Incomplete Resumes on Manage Resumes Page");
    logger =
        extent.startTest(
            "Verify Job Seeker is able to delete Incomplete Resumes on Manage Resumes Page");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getIncompleteResumes().isDisplayed(),
        "Unable to locate Incomplete Resumes Section");
    manageResumesPage.getIncompleteResumes().deleteIncompleteResume();
    logger.log(
        LogStatus.PASS,
        "Job Seeker clicked on 'Delete' link of Incomplete Resume & Confirmed to Delete.");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    Assert.assertTrue(msg.contains("has been deleted."));
    logger.log(
        LogStatus.PASS,
        "Job Seeker is able to delete the cover letter & Message displayed as '" + msg + "'");
    log.info("Job Seeker is able to Edit the Incomplete Resume");
    logger.log(LogStatus.PASS, "Job Seeker is able to Edit the Incomplete Resume");
  }

  @Test
  public void createCoverLetter() {
    log = LogManager.getLogger("Verify Job Seeker is able to create a Cover Letter");
    logger = extent.startTest("Verify Job Seeker is able to create a Cover Letter");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getCoverLetter().isDisplayed(), "Unable to locate Cover Letter Section");
    manageResumesPage.getCoverLetter().createCoverLetter(prop.getProperty("coverDescription"));
    logger.log(
        LogStatus.PASS,
        "Job Seeker entered Cover Letter details and clicked on 'Create Cover Letter' Button.");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    Assert.assertTrue(msg.contains("Cover letter"));
    logger.log(
        LogStatus.PASS, "Cover Letter created successfully & Message displayed as '" + msg + "'");
  }

  @Test
  public void editCoverLetter() {
    log = LogManager.getLogger("Verify Job Seeker is able to edit the Cover Letter");
    logger = extent.startTest("Verify Job Seeker is able to edit the Cover Letter");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getCoverLetter().isDisplayed(), "Unable to locate Cover Letter Section");
    manageResumesPage.getCoverLetter().editCoverLetter();
    logger.log(
        LogStatus.PASS,
        "Job Seeker edited the cover letter content and clicked 'Save Cover Letter' button.");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    Assert.assertEquals(msg, "Your cover letter has been updated.");
    logger.log(
        LogStatus.PASS,
        "Job Seeker is able to edit the cover letter & Message displayed as '" + msg + "'");
  }

  @Test
  public void deleteCoverLetter() {
    log = LogManager.getLogger("Verify Job Seeker is able to delete the Cover Letter");
    logger = extent.startTest("Verify Job Seeker is able to delete the Cover Letter");
    logger.log(LogStatus.PASS, "Job Seeker is on Manage Resume Page");
    Assert.assertTrue(
        manageResumesPage.getCoverLetter().isDisplayed(), "Unable to locate Cover Letter Section");
    manageResumesPage.getCoverLetter().deleteCoverLetter();
    logger.log(
        LogStatus.PASS, "Job Seeker clicked on delete and confirmed to Delete the Cover Letter.");
    String msg = manageResumesPage.getSuccessMessage().verifyMessage();
    Assert.assertEquals(msg, "Cover letter deleted");
    logger.log(
        LogStatus.PASS,
        "Job Seeker is able to delete the cover letter & Message displayed as '" + msg + "'");
  }
}

package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.junit.Assert;
import org.testng.annotations.*;

@Listeners(CustomListener.class)
public class TestHomePage extends BaseClass {
    LoginPage loginPage;
    HomePage homePage;

    public TestHomePage(){
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();
        loginPage = new LoginPage();
        homePage = new HomePage();

        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void goToHomePage() throws InterruptedException {
        homePage.goToHomePage();
        Thread.sleep(1000);
    }

    @Test
    public void navToContactsPage(){
        homePage.goToContactsPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/contacts"));
    }

    @Test
    public void navToDealsPage()  {
        homePage.goToDealsPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/deals"));
    }

    @Test
    public void navToCompaniesPage(){
        homePage.goToCompaniesPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/companies"));
    }

    @Test
    public void navToTasksPage(){
        homePage.goToTasksPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/tasks"));
    }

    @Test
    public void navToCampaignsPage(){
        homePage.goToCampaignsPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/campaigns"));
    }

    @Test
    public void verifyCaptureScreeshotOfFailedTests(){
        homePage.goToDealsPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/companies"));

    }

    @AfterMethod
    public void goBackToHomePage(){
        homePage.goToHomePage();
    }

    @AfterClass
    public void tearDown(){
        loginPage.logout();
        driver.quit();
    }
}

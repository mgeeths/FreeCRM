package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.CampaignsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.junit.Assert;
import org.testng.annotations.*;

public class TestCampaignsPage extends BaseClass {

    LoginPage loginPage;
    HomePage homePage;
    CampaignsPage campaignsPage;

    public TestCampaignsPage(){
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();
        loginPage = new LoginPage();
        homePage = new HomePage();
        campaignsPage = new CampaignsPage();

        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void goToCampaignsPage(){
        homePage.goToCampaignsPage();
    }

    @Test
    public void goToNewCampaignsPage() throws InterruptedException {
        campaignsPage.goToNewCampaignsPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/campaigns/new"));
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

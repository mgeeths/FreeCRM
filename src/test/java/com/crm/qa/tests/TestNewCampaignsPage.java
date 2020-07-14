package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.CampaignsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.NewCampaignsPage;
import org.junit.Assert;
import org.testng.annotations.*;

@Listeners(CustomListener.class)
public class TestNewCampaignsPage extends BaseClass {

    LoginPage loginPage;
    HomePage homePage;
    CampaignsPage campaignsPage;
    NewCampaignsPage newCampaignsPage;

    public TestNewCampaignsPage(){
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();
        loginPage = new LoginPage();
        homePage = new HomePage();
        campaignsPage = new CampaignsPage();
        newCampaignsPage = new NewCampaignsPage();
        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void goToCampaignsPage() throws InterruptedException {
        homePage.goToCampaignsPage();
        campaignsPage.goToNewCampaignsPage();
    }
    /* When a data provider is used, each set of data is run as a separate test, so... the Before method
    and After Method will be executed for each set of data. Therefore here, the gotoHomePage and then
    go to campaignspage & new campaigns page is done automatically. We don't have to include it inside
    the main test method.
     */
    @DataProvider
    public Object[][] getData(){
        return new Object[][]{{"Diwali Mela","3"},{"Bangles Mela","5"},{"Kurthi Mela","7"}};
    }

    @Test(dataProvider = "getData")
    public void verifyCreateNewCampaignWithDPWithoutExcel(String name, String days){
        newCampaignsPage.createNewCampaign(name,days);
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

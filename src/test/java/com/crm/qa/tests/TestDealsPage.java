package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.junit.Assert;
import org.testng.annotations.*;

import java.text.ParseException;

@Listeners(CustomListener.class)
public class TestDealsPage extends BaseClass {

    LoginPage loginPage;
    HomePage homePage;
    DealsPage dealsPage;

    public TestDealsPage(){
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();
        loginPage = new LoginPage();
        homePage = new HomePage();
        dealsPage = new DealsPage();

        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void goToDealsPage() throws InterruptedException {
        homePage.goToDealsPage();
        Thread.sleep(1000);
    }

    @Test(priority = 1)
    public void goToNewDealsPage(){
        dealsPage.goToNewDealsPage();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/deals/new"));
    }

    @Test(enabled = false)
    public void verifyDeleteOneDeal() throws InterruptedException {
        Assert.assertTrue(dealsPage.deleteOneDeal());

    }

    @Test(priority = 2, groups="Filter")
    @Parameters({"searchComp"})
    public void verifyFilterByCompany(String searchComp) throws InterruptedException {
        Assert.assertTrue(dealsPage.filterByCompany(searchComp));
    }

    @Test(priority = 3, groups="Filter")
    public void verifyFilterByNewStatus() throws InterruptedException {
        Assert.assertTrue(dealsPage.filterByNewStatus());
    }

    @Test(priority = 4, groups="Filter")
    public void verifyFilterByActiveStatus() throws InterruptedException {
        Assert.assertTrue(dealsPage.filterByActiveStatus());
    }

    @Test(priority = 5, groups="Filter")
    public void verifyFilterByInactiveStatus() throws InterruptedException {
        Assert.assertTrue(dealsPage.filterByInactiveStatus());
    }

    @Test(priority = 5, groups="Filter")
    @Parameters({"fromDate", "toDate"})
    public void verifyFilterByDates(String fromDate, String toDate) throws InterruptedException, ParseException {
        Assert.assertTrue(dealsPage.filterByDate(fromDate, toDate));
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

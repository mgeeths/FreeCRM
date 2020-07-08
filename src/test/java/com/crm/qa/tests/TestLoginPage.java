package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestLoginPage extends BaseClass {
    LoginPage loginPage;

    public TestLoginPage(){
        super();
    }

    @BeforeClass
    public void setUp(){
        launchBrowser();
        loginPage = new LoginPage();
    }
    @BeforeMethod
    public void goToLoginPage() throws InterruptedException {
       loginPage.goToLoginPage();
    }

    //@Test
    public void verifyNavigation() throws InterruptedException {
        loginPage.goToLoginPage();
    }

    //@Test(priority = 1)
    public void validLogin() {
        loginPage.validLogin();
    }

    @Test(priority = 2)
    public void verifyLoginAndLogout(){
        loginPage.validLogin();
        loginPage.logout();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/"));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}

package com.crm.qa.tests;

import com.crm.qa.Util.TestUtil;
import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.NewDealsPage;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestNewDealsPage extends BaseClass {

    LoginPage loginPage;
    HomePage homePage;
    DealsPage dealsPage;
    NewDealsPage newDealsPage;

    public TestNewDealsPage(){
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();

        loginPage = new LoginPage();
        homePage = new HomePage();
        dealsPage = new DealsPage();
        newDealsPage = new NewDealsPage();

        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void goToNewDealsPage() throws InterruptedException {
        homePage.goToDealsPage();
        Thread.sleep(2000);
        dealsPage.goToNewDealsPage();
        Thread.sleep(2000);

        //driver.navigate().refresh();
    }

    @DataProvider(name = "getTestDataFromExcel")
    public Iterator<Object[]> getTestDataFromExcel1(){
        ArrayList<Object[]> myTestData = TestUtil.getTestDataForNewDealsFromExcel();
        System.out.println(myTestData.size());
        return myTestData.iterator();
    }

    @Test(dataProvider = "getTestDataFromExcel")
    public void createNewDealFromExcelWithDP(String title, String company, String contacts, String amount, String commission) throws InterruptedException {
        newDealsPage.createNewDealsFromExcelWithDP(title,company,contacts,amount,commission);
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

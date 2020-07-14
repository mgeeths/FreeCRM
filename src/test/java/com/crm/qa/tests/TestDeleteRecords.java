package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.*;
import org.testng.annotations.*;

@Listeners(CustomListener.class)
public class TestDeleteRecords extends BaseClass {
    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    DealsPage dealsPage;
    CampaignsPage campaignsPage;
    DeleteRecords deleteRecords;

    public TestDeleteRecords(){
        super();

    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();
        loginPage = new LoginPage();
        homePage = new HomePage();
        deleteRecords = new DeleteRecords();

        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void goToHomePage(){
        homePage.goToHomePage();
    }

    @Test(priority = 2)
    public void verifyDeleteContactsCreated() throws InterruptedException {
        homePage.goToContactsPage();
        deleteRecords.deleteContactsCreated();
        deleteRecords.validateDeletedContacts();
    }

    @Test(priority = 1)
    public void verifyDeleteDealsCreated() throws InterruptedException {
        homePage.goToDealsPage();
        deleteRecords.deleteDealsCreated();
        deleteRecords.validateDeletedDeals();
    }

    @Test(priority = 3)
    public void verifyDeleteCompaniesCreated() throws InterruptedException {
        homePage.goToCompaniesPage();
        deleteRecords.deleteCompaniesCreated();
        deleteRecords.validateDeletedCompanies();
    }

    @AfterClass
    public void tearDown(){
        loginPage.logout();
        driver.quit();

    }
}

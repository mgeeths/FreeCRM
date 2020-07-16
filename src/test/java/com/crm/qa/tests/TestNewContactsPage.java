package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.NewContactsPage;
import org.junit.Assert;
import org.testng.annotations.*;

@Listeners(CustomListener.class)
public class TestNewContactsPage extends BaseClass {

    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    NewContactsPage newContactsPage;

    public TestNewContactsPage(){
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();
        loginPage = new LoginPage();
        homePage = new HomePage();
        contactsPage = new ContactsPage();
        newContactsPage = new NewContactsPage();


        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void navToNewContactsPage() throws InterruptedException {
        homePage.goToContactsPage();
        contactsPage.goToNewContactsPage();

         }

    @Test
    public void verifyEnterAllDetailsDirectlyFromExcel() throws InterruptedException {
        newContactsPage.enterNewContactDetailsDirectlyFromExcel();
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




package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.junit.Assert;
import org.testng.annotations.*;


public class TestContactsPage  extends BaseClass {

        LoginPage loginPage;
        HomePage homePage;
        ContactsPage contactsPage;

        public TestContactsPage(){
            super();
        }

        @BeforeClass
        public void setUp() throws InterruptedException {
            launchBrowser();
            loginPage = new LoginPage();
            homePage = new HomePage();
            contactsPage = new ContactsPage();

            loginPage.goToLoginPage();
            loginPage.validLogin();
        }

        @BeforeMethod
        public void navToContactsPage(){
            homePage.goToContactsPage();
        }

        @Test(priority = 1)
        public void navToNewContactsPage(){
            contactsPage.goToNewContactsPage();
            Assert.assertTrue(driver.getCurrentUrl().equals("https://ui.freecrm.com/contacts/new"));
        }

        @Test(priority = 2, enabled = false)
        public void verifyNewlyAddedContact() throws InterruptedException {
            contactsPage.verifyNewlyAddedContact();
        }

        @Test(priority = 3, enabled = false)
        public void verifyContactInOtherPages() throws InterruptedException {
            contactsPage.checkContactInAllPages();
        }

        @Test(priority = 4)
        public void verifyContactsAddedFromExcel() throws InterruptedException {
                Assert.assertTrue(contactsPage.verifyNewlyAddedContactFromExcel());
        }

       @Test(priority = 5)
       @Parameters({"email"})
        public void verifyFilterByEmail(String email) throws InterruptedException {
            contactsPage.filterByEmailField(email);
            Assert.assertTrue(contactsPage.checkResultTableForEmailEndsWithFilter(email));
        }

        @Test(priority = 6)
        @Parameters({"searchName"})
        public void verifyFilterFirstNameByContains(String searchName) throws InterruptedException {
            contactsPage.filterFirstNameByContains(searchName);
            Assert.assertTrue(contactsPage.verifyResultTableForContainsName(searchName));
        }

    @Test(priority = 7)
    @Parameters({"searchLastName"})
    public void verifyFilterLastNameByContains(String searchName) throws InterruptedException {
        contactsPage.filterLastNameByContains(searchName);
        Assert.assertTrue(contactsPage.verifyResultTableForContainsName(searchName));
    }

    @Test(priority = 8)
    @Parameters({"searchLastName"})
    public void verifyFilterLastNameByStartswith(String searchName) throws InterruptedException {
        contactsPage.filterLastNameByStartswith(searchName);
        Assert.assertTrue(contactsPage.verifyResultTableForContainsName(searchName));
    }

    @Test(priority = 9)
    public void verifyFilterStatusNew() throws InterruptedException {
        contactsPage.filterStatusNew();
        Assert.assertTrue(contactsPage.verifyNewStatusFilterResults());
    }

    @Test(priority = 10)
    public void verifyFilterStatusActive() throws InterruptedException {
        contactsPage.filterStatusActive();
        Assert.assertTrue(contactsPage.verifyActiveStatusFilterResults());
    }

    @Test(priority = 11)
    public void verifyFilterStatusInactive() throws InterruptedException {
        contactsPage.filterStatusInactive();
        Assert.assertTrue(contactsPage.verifyInactiveStatusFilterResults());
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






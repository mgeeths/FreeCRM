package com.crm.qa.tests;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.*;
import org.testng.annotations.*;

@Listeners(CustomListener.class)

public class TestTasksPage extends BaseClass {
    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    TasksPage tasksPage;

    public TestTasksPage(){
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();
        loginPage = new LoginPage();
        homePage = new HomePage();
        contactsPage = new ContactsPage();
        tasksPage = new TasksPage();


        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void navToTasksPage() throws InterruptedException {
        homePage.goToTasksPage();

    }

    @Test
    public void navToNewTasksPage(){
        tasksPage.goToNewTasksPage();
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

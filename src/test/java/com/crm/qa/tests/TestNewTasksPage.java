package com.crm.qa.tests;

import com.crm.qa.Util.TestUtil;
import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.NewTasksPage;
import com.crm.qa.pages.TasksPage;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;

public class TestNewTasksPage extends BaseClass {

    LoginPage loginPage;
    HomePage homePage;
    TasksPage tasksPage;
    NewTasksPage newTasksPage;

    public TestNewTasksPage(){
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        launchBrowser();
        loginPage = new LoginPage();
        homePage = new HomePage();
        tasksPage = new TasksPage();
        newTasksPage = new NewTasksPage();

        loginPage.goToLoginPage();
        loginPage.validLogin();
    }

    @BeforeMethod
    public void navToNewTasksPage() throws InterruptedException {
        homePage.goToTasksPage();
        tasksPage.goToNewTasksPage();
    }

    @DataProvider
    public Iterator<Object[]> getNewTaskData(){
        ArrayList<Object[]> myTaskData = TestUtil.getNewTaskData();
        return myTaskData.iterator();
    }



    @Test(dataProvider = "getNewTaskData")
    public void createNewTask(String title,String contacts, String deals, String dueDate, String closeDate) throws InterruptedException {
        newTasksPage.enterAllTaskDetails(title,contacts,deals, dueDate, closeDate);
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


package com.crm.qa.pages;

import com.crm.qa.base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;


public class TasksPage extends BaseClass {
    private static final Logger logger = LogManager.getLogger(NewContactsPage.class);

    //Page Factory

    @FindBy(xpath = "//a[@href='/tasks/new']")
    WebElement newTaskBtn;

    @FindBy(xpath = "//div[@id='dashboard-toolbar']//div[text()='Create new Task']")
    WebElement pageHeader;


    public TasksPage(){
        PageFactory.initElements(driver, this);
    }

    //Actions
    public NewTasksPage goToNewTasksPage(){
        newTaskBtn.click();
        Assert.assertTrue(pageHeader.isDisplayed());
        return new NewTasksPage();
    }


}

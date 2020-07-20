package com.crm.qa.pages;

import com.crm.qa.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass {
    //Page Factory
    @FindBy(xpath = "//div[@id='main-nav']//a[contains(@href,'contacts')]")
    WebElement contactsTabLink;

    @FindBy(xpath = "//div[@id='main-nav']//a[contains(@href,'home')]")
    WebElement homeTabLink;

    @FindBy(xpath = "//div[@id='main-nav']//a[contains(@href,'deals')]")
    WebElement dealsTabLink;

    @FindBy(xpath = "//div[@id='main-nav']//a[contains(@href,'companies')]")
    WebElement companiesTabLink;

    @FindBy(xpath = "//div[@id='main-nav']//a[contains(@href,'tasks')]")
    WebElement tasksTabLink;

    @FindBy(xpath = "//div[@id='main-nav']//a[contains(@href,'campaigns')]")
    WebElement campaignsTabLink;


    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    //Actions
    public ContactsPage goToContactsPage() {
        contactsTabLink.click();
        return new ContactsPage();
    }

    public HomePage goToHomePage() {
        homeTabLink.click();
        return new HomePage();
    }

    public DealsPage goToDealsPage(){
        dealsTabLink.click();
        return new DealsPage();
    }

    public CompaniesPage goToCompaniesPage(){
        companiesTabLink.click();
        return new CompaniesPage();
    }

    public TasksPage goToTasksPage(){
        tasksTabLink.click();
        return new TasksPage();
    }


    public CampaignsPage goToCampaignsPage(){
        campaignsTabLink.click();
        return new CampaignsPage();
    }
}

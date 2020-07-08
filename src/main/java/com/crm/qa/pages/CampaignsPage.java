package com.crm.qa.pages;

import com.crm.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CampaignsPage extends BaseClass {

    //Page Factory
    @FindBy(xpath = "//button[contains(text(),'New')]")
    WebElement newCampaignsBtn;

    @FindBy(xpath = "//input[@name='name']")
    WebElement nameFieldInNewCampaignsPage;

    public CampaignsPage(){
        PageFactory.initElements(driver,this);

    }

    //Actions
    public NewCampaignsPage goToNewCampaignsPage() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", newCampaignsBtn);
        driver.navigate().refresh();
       Thread.sleep(2000);
        Assert.assertTrue(nameFieldInNewCampaignsPage.isDisplayed());
        return new NewCampaignsPage();
    }
}

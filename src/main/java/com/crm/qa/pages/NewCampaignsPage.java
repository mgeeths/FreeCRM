package com.crm.qa.pages;

import com.crm.qa.base.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewCampaignsPage extends BaseClass {
    HomePage homePage;
    //Page Factory
    @FindBy(xpath = "//input[@name='name']")
    WebElement nameField;

    @FindBy(xpath = "//div[@name='transport_type']")
    WebElement transportField;

    @FindBy(xpath = "//div[@class='visible menu transition']//span[contains(text(),'Email')]")
    WebElement emailTransportField;

    @FindBy(xpath = "//div[@class='visible menu transition']//span[contains(text(),'SMS')]")
    WebElement smsTransportField;

    @FindBy(xpath = "//div[@class='ui checkbox']")
    WebElement activeCheckBox;

    @FindBy(xpath = "//button[@class='ui linkedin icon button']")
    WebElement addIconBtn;

    @FindBy(xpath = "//input[@placeholder='Interval']")
    WebElement intervalField;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveBtn;

    public NewCampaignsPage(){
        PageFactory.initElements(driver,this);
        homePage = new HomePage();

    }

    //Actions
    public void createNewCampaign(String campName, String days){
        nameField.clear();
        nameField.click();
        nameField.sendKeys(campName);
        nameField.sendKeys(Keys.ENTER);
        transportField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(smsTransportField).build().perform();
        smsTransportField.click();
        activeCheckBox.click();
        addIconBtn.click();
        intervalField.sendKeys(days);
        saveBtn.click();
        //homePage.goToCampaignsPage();
    }
}

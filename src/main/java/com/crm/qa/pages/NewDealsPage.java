package com.crm.qa.pages;

import com.crm.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewDealsPage extends BaseClass {
    //Page Factory

    @FindBy(xpath = "//input[@name='title']")
    WebElement titleField;

    @FindBy(xpath = "//div[@name='company']/input")
    WebElement companyField;

    @FindBy(xpath = "//div[@name='contacts']/input")
    WebElement contactsField;

    @FindBy(xpath = "//input[@class='calendarField']")
    WebElement calenderField;

    @FindBy(xpath = "//div[@class='react-datepicker__week']//div[text()='30']")
    WebElement dateField;

    @FindBy(xpath = "//div[@class='react-datepicker__time-box']//li[contains(text(),'17:00')]")
    WebElement timeField;

    @FindBy(xpath="//input[@name='amount']")
    WebElement amountField;

    @FindBy(xpath="//input[@name='commission']")
    WebElement commissionField;

    @FindBy(xpath ="//div[@name='status']" )
    WebElement statusField;

    @FindBy(xpath = "//div[@name='status']//div[@class='visible menu transition']//div/span[contains(text(),'Active')]")
    WebElement activeStatus;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveBtn;

    public NewDealsPage(){
        PageFactory.initElements(driver,this);
    }

    public void createNewDealsFromExcelWithDP(String title, String company, String contacts, String amount, String commission) throws InterruptedException {
        titleField.clear();
        titleField.click();
        titleField.sendKeys(title);
        titleField.sendKeys(Keys.ENTER);
        companyField.clear();
        companyField.click();
        companyField.sendKeys(company);
        companyField.sendKeys(Keys.ENTER);
        Thread.sleep(1500);
        contactsField.clear();
        contactsField.click();
        contactsField.sendKeys(contacts);
        String contactName = String.format("//div[@class='visible menu transition']//div/span[contains(text(),'%s')]", contacts);
        WebElement contactValue = driver.findElement(By.xpath(contactName));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(contactValue).build().perform();
        driver.findElement(By.xpath(contactName)).click();
        //contactsField.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        calenderField.clear();
        calenderField.click();
        dateField.click();
        timeField.click();
        Thread.sleep(1500);
        amountField.clear();
        amountField.click();
        amountField.sendKeys(amount);
        commissionField.clear();
        commissionField.click();
        commissionField.sendKeys(commission);
        statusField.click();
        Actions actions= new Actions(driver);
        actions.moveToElement(activeStatus).build().perform();
        activeStatus.click();

        saveBtn.click();
        Thread.sleep(2000);
    }
}

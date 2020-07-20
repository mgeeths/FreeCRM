package com.crm.qa.pages;

import com.crm.qa.base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewTasksPage extends BaseClass {
    private static final Logger logger = LogManager.getLogger(NewContactsPage.class);

    //Page Factory
    @FindBy(xpath="//input[@name='title']")
    WebElement titleField;

    @FindBy(xpath = "//div[@name='type']")
    WebElement typeField;

    @FindBy(xpath ="//div[@class='visible menu transition']//div[@role='option']/span[text()='Business Support']")
    WebElement typeBusinessSupport;

    @FindBy(xpath = "//label[text()='Due Date']/following-sibling::div[@class='react-datepicker-wrapper']//input")
    WebElement dueDateField;

    @FindBy(xpath = "//label[text()='Due Date']/following-sibling::div[@class='react-datepicker-wrapper']//button")
    WebElement dueDateClearBtn;

    @FindBy(xpath="//button[text()='Next Month']")
    WebElement dueNextMonthIcon;

    @FindBy(xpath = "//div[@class='react-datepicker__week']//div[text()='29']")
    WebElement dateField;

    @FindBy(xpath = "//div[@class='react-datepicker__time-box']//li[text()='17:00']")
    WebElement timeField;

    @FindBy(xpath="//div[@name='contact']//input")
    WebElement contactField;

    @FindBy(xpath="//div[@name='deal']//input")
    WebElement dealField;

    @FindBy(xpath = "//label[text()='Close Date']/following-sibling::div[@class='react-datepicker-wrapper']//input")
    WebElement closeDateField;

    @FindBy(xpath = "//label[text()='Close Date']/following-sibling::div[@class='react-datepicker-wrapper']//button")
    WebElement closeDateClearBtn;

    @FindBy(xpath="//div[@name='priority']")
    WebElement priorityField;

    @FindBy(xpath="//div[@class='visible menu transition']//div[@role='option']/span[text()='High']")
    WebElement highPriority;

    @FindBy(xpath="//div[@name='status']")
    WebElement statusField;

    @FindBy(xpath="//div[@class='visible menu transition']//div[@role='option']/span[text()='Reviewing']")
    WebElement reviewingStatus;

    @FindBy(xpath= "//button[contains(text(),'Save')]")
    WebElement saveBtn;

    public NewTasksPage(){
        PageFactory.initElements(driver, this);
    }
    //Actions

    public void enterAllTaskDetails(String title, String contacts, String deals, String dueDate, String closeDate) throws InterruptedException {
        titleField.clear();
        Thread.sleep(50);
        titleField.sendKeys(title);
        typeField.click();
        Thread.sleep(50);
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(typeBusinessSupport).build().perform();
        typeBusinessSupport.click();
        dueDateClearBtn.click();
        Thread.sleep(50);
        //dueDateField.click();
        dueDateField.sendKeys(dueDate);
        //dueNextMonthIcon.click();
        //dueDateField.click();
        //timeField.click();
        contactField.clear();
        contactField.click();
        Thread.sleep(50);
        contactField.sendKeys(contacts);
        String contactName = String.format("//div[@class='visible menu transition']//div/span[contains(text(),'%s')]", contacts);
        WebElement contactElement = driver.findElement(By.xpath(contactName));
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(contactElement).build().perform();
        contactElement.click();
        dealField.click();
        Thread.sleep(50);
        dealField.sendKeys(deals);
        String dealName = String.format("//div[@class='visible menu transition']//div/span[contains(text(),'%s')]", deals);
        WebElement dealElement = driver.findElement(By.xpath(dealName));
        Actions actions3 = new Actions(driver);
        actions3.moveToElement(dealElement).build().perform();
        dealElement.click();
        closeDateClearBtn.click();
        closeDateField.sendKeys(closeDate);
        Thread.sleep(50);
        //dateField.click();
        //timeField.click();
        priorityField.click();
        Thread.sleep(50);
        highPriority.click();
        statusField.click();
        Thread.sleep(50);
        reviewingStatus.click();
        saveBtn.click();
    }

}

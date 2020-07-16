package com.crm.qa.pages;

import com.crm.qa.Util.Xls_Reader;
import com.crm.qa.base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class NewContactsPage extends BaseClass {
    private static final Logger logger = LogManager.getLogger(NewContactsPage.class);
    Xls_Reader reader;
    HomePage homePage;
    ContactsPage contactsPage;
    //Page Factory

    @FindBy(xpath="//div[@id='dashboard-toolbar']//div[text()='Create New Contact']")
    WebElement pageHeader;

    @FindBy(name = "first_name")
    WebElement fNameField;

    @FindBy(name = "last_name")
    WebElement lNameField;

    @FindBy(xpath = "//div[@name='company']/input[@class='search']")
    WebElement coNameField;

    @FindBy(xpath = "//input[@placeholder='Email address']")
    WebElement eMailField;

    @FindBy(xpath = "//input[@placeholder='Personal email, Business, Alt...']")
    WebElement emailCategoryField;

    @FindBy(xpath = "(//i[@class='add icon'])[1]")
    WebElement addEmailBtn;

    @FindBy(xpath = "(//input[@placeholder='Email address'])[2]")
    WebElement altEmailField;

    @FindBy(xpath = "(//input[@placeholder='Personal email, Business, Alt...'])[2]")
    WebElement altEmailCategoryField;

    @FindBy(xpath = "//div[@name='category']")
    WebElement categorySelectionField;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[3]/span")
    WebElement customerCategoryOption;

    @FindBy(xpath = "//div[@name='status']")
    WebElement statusSelectionField;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[3]/span")
    WebElement activeStatusOption;

    @FindBy(xpath="(//div[@class='ui toggle checkbox'])[1]")
    WebElement doNotCallBtn;

    @FindBy(xpath ="//input[@name='day']")
    WebElement dayField;

    @FindBy(xpath ="//div[@name='month']")
    WebElement monthField;

    @FindBy(xpath="(//div[@name='month']//div[@class='visible menu transition']//div[@role='option'])[3]/span")
    WebElement febMonth;

    @FindBy(xpath ="//input[@name='year']")
    WebElement yearField;

    @FindBy(xpath ="//input[@name='fileField']")
    WebElement imageField;

    @FindBy(xpath= "//button[contains(text(),'Save')]")
    WebElement saveBtn;


    public NewContactsPage(){
        PageFactory.initElements(driver,this);
        reader = new Xls_Reader("C:\\Users\\browse\\WebAutomation\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM.xlsx");
        homePage = new HomePage();
        contactsPage = new ContactsPage();
    }

    //Actions

    public void enterNewContactDetailsDirectlyFromExcel() throws InterruptedException {

        for (int rowNum=2; rowNum<=reader.getRowCount("NewContactDetails"); rowNum++) {
            /*
            logger.debug("In New contacts page");
            if(! pageHeader.isDisplayed()){
                driver.navigate().refresh();
                logger.debug("Refreshed the new contacts page");
                Thread.sleep(2000);
            }
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2000));
            wait.until(ExpectedConditions.visibilityOf(pageHeader));
             */
            fNameField.clear();
            fNameField.click();
            fNameField.sendKeys(reader.getCellData("NewContactDetails", "FirstName", rowNum));
            lNameField.clear();
            lNameField.click();
            lNameField.sendKeys(reader.getCellData("NewContactDetails", "LastName", rowNum));
            coNameField.clear();
            coNameField.click();
            coNameField.sendKeys(reader.getCellData("NewContactDetails", "Company", rowNum));
            eMailField.clear();
            eMailField.click();
            eMailField.sendKeys(reader.getCellData("NewContactDetails", "Email", rowNum));
            emailCategoryField.clear();
            emailCategoryField.click();
            emailCategoryField.sendKeys(reader.getCellData("NewContactDetails", "Category", rowNum));
            addEmailBtn.click();
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait1.until(ExpectedConditions.visibilityOf(altEmailField));
            altEmailField.sendKeys(reader.getCellData("NewContactDetails", "AltEmail", rowNum));
            altEmailCategoryField.sendKeys(reader.getCellData("NewContactDetails", "AltCategory", rowNum));
            categorySelectionField.click();
            customerCategoryOption.click();
            statusSelectionField.click();
            activeStatusOption.click();
            doNotCallBtn.click();
            dayField.sendKeys(reader.getCellData("NewContactDetails", "BirthDay", rowNum));
            monthField.click();
            Actions actions = new Actions(driver);
            actions.moveToElement(febMonth).build().perform();
            febMonth.click();
            yearField.sendKeys(reader.getCellData("NewContactDetails", "BirthYear", rowNum));
            imageField.sendKeys(reader.getCellData("NewContactDetails", "ImagePath", rowNum));
            Thread.sleep(2000);
            saveBtn.click();
            Thread.sleep(2000);
            homePage.goToContactsPage();
            contactsPage.goToNewContactsPage();
        }
   }
}

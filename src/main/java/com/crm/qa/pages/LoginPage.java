package com.crm.qa.pages;

import com.crm.qa.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BaseClass {
    //Page Factory
    @FindBy(xpath = "//div[@id='navbar-collapse']//a[contains(text(),'Log In')]")
    //@FindBy(xpath = "//div[@id='navbar-collapse']/ul/li[1]/a")
    WebElement goTologinPageBtn;

    @FindBy(name="email")
    WebElement usernameField;

    @FindBy(name="password")
    WebElement passwordField;

    @FindBy(xpath = "//div[contains(text(),'Login')]")
    WebElement loginBtn;

    @FindBy(xpath = "//div[@class='right menu']//span[@class='user-display']")
    WebElement userAccount;

    @FindBy(xpath = "//div[@role='listbox']/i[contains(@class,'settings')]")
    WebElement settingsIcon;

    @FindBy(xpath="//div[@class='menu transition visible']//a[5]/span")
    WebElement logoutLink;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    public void goToLoginPage() throws InterruptedException {
        driver.get(prop.getProperty("url"));
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.moveToElement(goTologinPageBtn).build().perform();
        goTologinPageBtn.click();
        Thread.sleep(2000);
    }

    public HomePage validLogin(){
        usernameField.sendKeys(prop.getProperty("uName"));
        passwordField.sendKeys(prop.getProperty("pwd"));
        loginBtn.click();
        Assert.assertTrue(userAccount.getText().equals(prop.getProperty("userAccount")));
        return new HomePage();
    }

    public void logout(){
        settingsIcon.click();
        logoutLink.click();
    }
}

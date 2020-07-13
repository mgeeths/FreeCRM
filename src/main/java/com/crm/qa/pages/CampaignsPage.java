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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CampaignsPage extends BaseClass {
    private static final Logger logger = LogManager.getLogger(CampaignsPage.class);

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
        logger.debug("The page got refreshed now");
        Thread.sleep(2000);
        Assert.assertTrue(nameFieldInNewCampaignsPage.isDisplayed());
        logger.info("Assertion was successful");
        return new NewCampaignsPage();
    }
/*
    public void arrayFunction(){
        String[] cars = new String[2];
        cars[1] ="ase";
        String[] firstnames;
        firstnames = new String[]{"sfdds","ser","sser"};

        String[] names = new String[]{"asdf", "sdf", "sdffs"};

        ArrayList<Integer> num = new ArrayList<>();
        num.add(3) ;
        num.add(4);
        int x= num.get(0);
        num.set(0,5);
        num.remove(0);
        for(int eachArr:num){

        }
        Collections.sort(num);

        HashMap<String, String> h = new HashMap<String, String>();
        h.put("A","Apple");
        h.put("B","Banana");
        String z= h.get("A");
}
*/



}

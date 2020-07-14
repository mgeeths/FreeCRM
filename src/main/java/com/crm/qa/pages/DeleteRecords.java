package com.crm.qa.pages;

import com.crm.qa.Util.Xls_Reader;
import com.crm.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DeleteRecords extends BaseClass {
    Xls_Reader reader;

    //Page Factory
    @FindBy(xpath = "//div[contains(@class,'custom-pagination')]//a[not(contains(@class,'icon'))]")
    List<WebElement> allPages;

    @FindBy(xpath = "//tbody//tr")
    List<WebElement> allRows;

    @FindBy(xpath = "//div[@name='action']")
    WebElement actionsField;

    @FindBy(xpath = "//div[@name='action']//div[@class='visible menu transition']//span[contains(text(),'Delete')]")
    WebElement deleteAction;

    @FindBy(xpath = "//i[@class='checkmark icon']")
    WebElement checkmarkIcon;

    @FindBy(xpath = "//div[@class='actions']//button[contains(text(),'Delete')]")
    WebElement confirmDelete;

    @FindBy(xpath = "//div[contains(@class,'custom-pagination')]//a[not(contains(@class,'icon'))]")
    List<WebElement> allPagesInDeals;

    @FindBy(xpath = "//tbody//tr")
    List<WebElement> rowsInDeals;

    @FindBy(xpath = "//div[@name='action']")
    WebElement actionsFieldInDeals;

    @FindBy(xpath = "//div[@name='action']//div[@class='visible menu transition']//span[contains(text(),'Delete')]")
    WebElement deleteActionInDeals;

    @FindBy(xpath = "//i[@class='checkmark icon']")
    WebElement checkmarkIconInDeals;

    @FindBy(xpath = "//div[@class='actions']//button[contains(text(),'Delete')]")
    WebElement confirmDeleteDeals;

    public DeleteRecords() {
        PageFactory.initElements(driver, this);
        reader = new Xls_Reader("C:\\Users\\browse\\WebAutomation\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM.xlsx");
    }

    public interface ActionInterface {
        void operation(WebElement col);
    }

    public void searchDoAction(List<WebElement> rows, String searchFor, ActionInterface actionObj) throws InterruptedException {
        for (WebElement eachRow : rows) {
            List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
            String name = allCols.get(1).getText();
            if (name.startsWith(searchFor)) {
                Thread.sleep(1000);
                System.out.println(name);
                actionObj.operation(allCols.get(0));
            }
        }
    }

    public void deleteContactsCreated1() throws InterruptedException {
        int rowCount = reader.getRowCount("NewContactDetails");
        ActionInterface actionObj = (WebElement col) -> {
            Actions actions = new Actions(driver);
            actions.moveToElement(col).build().perform();
            col.click();
        };

        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String contactName = reader.getCellData("NewContactDetails", "FirstName", rowNum);
            for (WebElement eachPage : allPages) {
                eachPage.click();
                Thread.sleep(2000);
            }
            searchDoAction(allRows, contactName, actionObj);
        }
        actionsField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteAction).build().perform();
        deleteAction.click();
        checkmarkIcon.click();
        confirmDelete.click();
        Thread.sleep(2000);

    }

    public void deleteContactsCreated() throws InterruptedException {
        int rowCount = reader.getRowCount("NewContactDetails");
        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String contactName = reader.getCellData("NewContactDetails", "FirstName", rowNum);
            for (WebElement eachPage : allPages) {
                eachPage.click();
                Thread.sleep(2000);
                for (WebElement eachRow : allRows) {
                    List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                    String name = allCols.get(1).getText().toLowerCase();
                    if (name.startsWith(contactName.toLowerCase())) {
                        Thread.sleep(1000);
                        System.out.println(name);
                        Actions actions = new Actions(driver);
                        actions.moveToElement(allCols.get(0)).build().perform();
                        allCols.get(0).click();
                        Thread.sleep(500);
                    }
                }
            }
        }
        actionsField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteAction).build().perform();
        deleteAction.click();
        checkmarkIcon.click();
        confirmDelete.click();
        Thread.sleep(2000);

    }

    public boolean validateNameNotFound(String searchName) throws InterruptedException {
        for(WebElement eachPage:allPages) {
            eachPage.click();
            Thread.sleep(2000);
            for (WebElement eachRow : allRows) {
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String nameCol = allCols.get(1).getText().toLowerCase();
                if (nameCol.contains(searchName.toLowerCase())) {
                    return false;
                }
            }
        }return true;
    }

    public boolean validateDeletedContacts() throws InterruptedException {
        int rowCount = reader.getRowCount("NewContactDetails");
        for(int rowNum=2; rowNum<=rowCount; rowNum++){
            String searchName = reader.getCellData("NewContactDetails","FirstName",rowNum);
            if(! validateNameNotFound(searchName.toLowerCase())){
                return false;
            }
        }return true;
    }

    public void deleteDealsCreated() throws InterruptedException {
        int rowCount = reader.getRowCount("NewDealsDetails");
        for (int rowNum = 2; rowNum<=rowCount; rowNum++){
            String dealName = reader.getCellData("NewDealsDetails","Title",rowNum);
            for (WebElement eachPage:allPages){
                eachPage.click();
                Thread.sleep(2000);
                for(WebElement eachRow:allRows){
                    List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                    String nameCol = allCols.get(1).getText().toLowerCase();
                    if(nameCol.equals(dealName.toLowerCase())){
                        Thread.sleep(1000);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allCols.get(0));
                        Thread.sleep(500);
                        allCols.get(0).click();
                    }
                }
            }
        }
        actionsField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteAction).build().perform();
        deleteAction.click();
        checkmarkIcon.click();
        confirmDelete.click();
        Thread.sleep(2000);
    }

    public boolean validateDeletedDeals() throws InterruptedException {
        int rowCount = reader.getRowCount("NewDealDetails");
        for(int rowNum=2; rowNum<=rowCount; rowNum++){
            String searchName = reader.getCellData("NewDealDetails","Title",rowNum);
            if(! validateNameNotFound(searchName.toLowerCase())){
                return false;
            }
        }return true;
    }

    public void deleteCompaniesCreated() throws InterruptedException {
        int rowCount = reader.getRowCount("NewDealsDetails");
        for (int rowNum = 2; rowNum<=rowCount; rowNum++){
            String companyName = reader.getCellData("NewDealsDetails","Company",rowNum);
            for (WebElement eachPage:allPages){
                eachPage.click();
                Thread.sleep(2000);
                for(WebElement eachRow:allRows){
                    List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                    String nameCol = allCols.get(1).getText().toLowerCase();
                    if(nameCol.equals(companyName.toLowerCase())){
                        //Thread.sleep(1000);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allCols.get(0));
                        Thread.sleep(500);
                        allCols.get(0).click();
                    }
                }
            }
        }
        actionsField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(deleteAction).build().perform();
        deleteAction.click();
        checkmarkIcon.click();
        confirmDelete.click();
        Thread.sleep(2000);
    }

    public boolean validateDeletedCompanies() throws InterruptedException {
        int rowCount = reader.getRowCount("NewContactDetails");
        for(int rowNum=2; rowNum<=rowCount; rowNum++){
            String searchName = reader.getCellData("NewContactDetails","Company",rowNum);
            if(! validateNameNotFound(searchName.toLowerCase())){
                return false;
            }
        }return true;
    }

}

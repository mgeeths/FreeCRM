package com.crm.qa.pages;

import com.crm.qa.Util.Xls_Reader;
import com.crm.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactsPage extends BaseClass {
    Xls_Reader reader;
    //Page Factory
    @FindBy(xpath = "//button[contains(text(),'New')]")
    WebElement newContactBtn;

    @FindBy(xpath = "//div[@id='dashboard-toolbar']//div[text()='Create New Contact']")
    WebElement pageHeader;

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> allRows;

    @FindBy(xpath = "//div[contains(@class,'custom-pagination')]//a[not(contains(@class,'icon'))]")
    List<WebElement> allPages;

    @FindBy(xpath = "//div[contains(@class,'custom-pagination')]//a[@class='item active']")
    WebElement activePage;

    @FindBy(xpath = "//a[@class='icon item']/i[contains(@class, 'chevron')]")
    WebElement nextPageIcon;

    @FindBy(xpath = "//button[contains(text(),'Show Filters')]")
    WebElement showFiltersBtn;

    @FindBy(xpath = "//input[@class='search']")
    WebElement searchColumnField;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[1]/span")
    WebElement searchFirstName;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[2]/span")
    WebElement searchLastName;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[6]/span")
    WebElement searchEmail;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[8]/span")
    WebElement searchStatus;

    @FindBy(xpath = "//div[@name='operator']")
    WebElement operatorDrpdown;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[2]/span")
    WebElement startsWithOperator;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[3]/span")
    WebElement endsWithOperator;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[1]/span")
    WebElement equalsOperator;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[4]/span")
    WebElement containsOperator;

    @FindBy(xpath = "//input[@name='value']")
    WebElement valueField;

    @FindBy(xpath = "//div[@name='value']")
    WebElement statusValueField;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[1]")
    WebElement newStatusFilter;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[2]")
    WebElement activeStatusFilter;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[3]")
    WebElement inactiveStatusFilter;

    @FindBy(xpath = "//i[contains(@class,'search small icon')]")
    WebElement searchIconBtn;

    public ContactsPage() {
        PageFactory.initElements(driver, this);
        reader = new Xls_Reader("C:\\Users\\browse\\WebAutomation\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM.xlsx");
    }

    public NewContactsPage goToNewContactsPage() {
        newContactBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        wait.until(ExpectedConditions.visibilityOf(pageHeader));
        return new NewContactsPage();
    }

    public boolean verifyNewlyAddedContact() throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);
        for (WebElement eachRow : allRows) {
            List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
            String name = allCols.get(1).getText();
            System.out.println(name + " added ");
            if (name.equals("Lulu L")) {
                System.out.println(name + " added to new contacts");
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkContactInAllPages() throws InterruptedException {
        boolean flag = false;
        int pageCount = allPages.size();
        System.out.println(pageCount);
        //Thread.sleep(3000);
        for (WebElement eachPage : allPages) {
            eachPage.click();
            Thread.sleep(3000);
            for (WebElement eachRow : allRows) {
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String name = allCols.get(1).getText();
                System.out.println(name);
                if (name.equals("Alex A")) {
                    String pageNo = activePage.getText();
                    System.out.println(name + " is found in page " + pageNo);
                    flag = true;
                    break;
                }
            }
        }
        return flag;

    }

    /*  foundInRow function will go to each page first, then each Row and then check
    if the name matches. If it matches it returns true otherwise false. ie. Even if one name
    does not match, the function will return false and so fail.
     */
    public boolean foundInRow(String searchName) throws InterruptedException {
        for (WebElement eachPage : allPages) {
            eachPage.click();
            Thread.sleep(3000);
            for (WebElement eachRow : allRows) {
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String name = allCols.get(1).getText();
                if (name.startsWith(searchName)) {
                    System.out.println(name + " found");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifyNewlyAddedContactFromExcel() throws InterruptedException {
        Thread.sleep(2000);
        int rowCount = reader.getRowCount("NewContactDetails");
        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String nameInExcel = reader.getCellData("NewContactDetails", "FirstName", rowNum);
            System.out.println("Searching for name " + nameInExcel);
            if (!foundInRow(nameInExcel)) {
                System.out.println("returning false");
                return false;
            }
        }
        System.out.println("returning true");
        return true;
    }

    public boolean verifyNewlyAddedContactFromExcelInline() throws InterruptedException {
        boolean matched;
        Thread.sleep(2000);
        int rowCount = reader.getRowCount("NewContactDetails");
        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String nameInExcel = reader.getCellData("NewContactDetails", "FirstName", rowNum);
            System.out.println("Searching for name " + nameInExcel);
            matched = false;
            for (WebElement eachPage : allPages) {
                eachPage.click();
                Thread.sleep(2000);
                for (WebElement eachRow : allRows) {
                    List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                    String name = allCols.get(1).getText();
                    if (name.startsWith(nameInExcel)) {
                        System.out.println(name + " found");
                        matched = true;
                        break;
                    }
                }
                if (matched) {
                    break;
                }
            }
            if (!matched) {
                System.out.println("returning false");
                return false;
            }
        }
        System.out.println("returning true");
        return true;
    }


    public void filterByEmailField(String email) throws InterruptedException {
        Thread.sleep(1000);
        showFiltersBtn.click();
        searchColumnField.click();
        searchEmail.click();
        operatorDrpdown.click();
        endsWithOperator.click();
        valueField.clear();
        valueField.sendKeys(email);
        searchIconBtn.click();
        Thread.sleep(2000);
    }

    public boolean checkResultTableForEmailEndsWithFilter(String email) throws InterruptedException {
        for (WebElement eachPage : allPages) {
            System.out.println(allPages.size());
            eachPage.click();
            Thread.sleep(2000);
            for (WebElement eachRow : allRows) {
                System.out.println(allRows.size());
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String nameInRow = allCols.get(6).getText();
                if (!nameInRow.endsWith(email)) {
                    return false;
                }
                System.out.println(nameInRow);
            }

        }
        return true;
    }

    public void filterFirstNameByContains(String searchName) throws InterruptedException {
        Thread.sleep(500);
        showFiltersBtn.click();
        searchColumnField.click();
        searchFirstName.click();
        operatorDrpdown.click();
        containsOperator.click();
        valueField.clear();
        valueField.sendKeys(searchName);
        searchIconBtn.click();
        Thread.sleep(2000);
    }

    public boolean verifyResultTableForContainsName(String searchName) throws InterruptedException {
        for(WebElement eachPage:allPages){
            eachPage.click();
            Thread.sleep(2000);
            System.out.println(allRows.size());
            for(WebElement eachRow:allRows){
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String nameInRow = allCols.get(1).getText();
                if (! nameInRow.toLowerCase().contains(searchName)){
                    return false;
                }System.out.println(nameInRow);
            }
        } return true;
    }

    public void filterLastNameByContains(String searchName) throws InterruptedException {
        Thread.sleep(500);
        showFiltersBtn.click();
        searchColumnField.click();
        searchLastName.click();
        operatorDrpdown.click();
        containsOperator.click();
        valueField.clear();
        valueField.sendKeys(searchName);
        searchIconBtn.click();
        Thread.sleep(2000);
    }

    public void filterLastNameByStartswith(String searchName) throws InterruptedException {
        Thread.sleep(500);
        showFiltersBtn.click();
        searchColumnField.click();
        searchLastName.click();
        operatorDrpdown.click();
        startsWithOperator.click();
        valueField.clear();
        valueField.sendKeys(searchName);
        searchIconBtn.click();
        Thread.sleep(2000);
    }

    public void filterStatusNew() throws InterruptedException {
        Thread.sleep(500);
        showFiltersBtn.click();
        searchColumnField.click();
        searchStatus.click();
        operatorDrpdown.click();
        equalsOperator.click();
        statusValueField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(newStatusFilter).build().perform();
        newStatusFilter.click();
        searchIconBtn.click();
        Thread.sleep(2000);
    }

    public void filterStatusActive() throws InterruptedException {
        Thread.sleep(500);
        showFiltersBtn.click();
        searchColumnField.click();
        searchStatus.click();
        operatorDrpdown.click();
        equalsOperator.click();
        statusValueField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(activeStatusFilter).build().perform();
        activeStatusFilter.click();
        searchIconBtn.click();
        Thread.sleep(2000);
    }

    public void filterStatusInactive() throws InterruptedException {
        Thread.sleep(500);
        showFiltersBtn.click();
        searchColumnField.click();
        searchStatus.click();
        operatorDrpdown.click();
        equalsOperator.click();
        statusValueField.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(inactiveStatusFilter).build().perform();
        inactiveStatusFilter.click();
        searchIconBtn.click();
        Thread.sleep(2000);
    }

    public boolean verifyNewStatusFilterResults() throws InterruptedException {
        for(WebElement eachPage:allPages){
            eachPage.click();
            Thread.sleep(1500);
            for(WebElement eachRow:allRows){
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String statusCol = allCols.get(4).getText();
                if(!statusCol.equals("New")){
                    return false;
                }
            }
        }return true;
    }

    public boolean verifyActiveStatusFilterResults() throws InterruptedException {
        for(WebElement eachPage:allPages){
            eachPage.click();
            Thread.sleep(1500);
            for(WebElement eachRow:allRows){
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String statusCol = allCols.get(4).getText();
                if(!statusCol.equals("Active")){
                    return false;
                }
            }
        }return true;
    }

    public boolean verifyInactiveStatusFilterResults() throws InterruptedException {
        for(WebElement eachPage:allPages){
            eachPage.click();
            Thread.sleep(1500);
            for(WebElement eachRow:allRows){
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String statusCol = allCols.get(4).getText();
                if(!statusCol.equals("Inactive")){
                    return false;
                }
            }
        }return true;
    }
}
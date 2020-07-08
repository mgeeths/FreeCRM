package com.crm.qa.pages;

import com.crm.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DealsPage extends BaseClass {
    //Page Factory
    @FindBy(xpath = "//button[contains(text(),'New')]")
    WebElement newDealsBtn;

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> allRows;

    @FindBy(xpath = "//div[contains(@class,'custom-pagination')]//a[not(contains(@class,'icon'))]")
    List<WebElement> allPages;

    @FindBy(xpath = "//button[contains(text(),'Show Filters')]")
    WebElement showFiltersBtn;

    @FindBy(xpath = "//input[@class='search']")
    WebElement searchColumnField;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[3]/span")
    WebElement searchByCompany;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[14]/span")
    WebElement searchByStatus;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[6]/span")
    WebElement searchByDate;

    @FindBy(xpath = "//div[@name='operator']")
    WebElement operatorDrpdown;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[1]/span")
    WebElement companyEqualsOperator;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[4]/span")
    WebElement datesBetweenOperator;

    @FindBy(xpath = "//div[@class='field value']//input")
    WebElement valueField;

    @FindBy(xpath = "//div[@name='value']")
    WebElement valueStatusField;

    @FindBy(xpath = "//div[@class='react-datepicker-wrapper']")
    WebElement valueDate1Field;

    @FindBy(xpath = "(//div[@class='react-datepicker-wrapper'])[2]")
    WebElement valueDate2Field;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[1]")
    WebElement newStatusFilter;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[2]")
    WebElement activeStatusFilter;

    @FindBy(xpath = "(//div[@class='visible menu transition']//div[@role='option'])[3]")
    WebElement inactiveStatusFilter;

    @FindBy(xpath = "//div[@class='react-datepicker__input-container']/input")
    WebElement firstDateField;

    @FindBy(xpath = "(//div[@class='react-datepicker__input-container'])[2]/input")
    WebElement secondDateField;

    @FindBy(xpath = "//i[contains(@class,'search small icon')]")
    WebElement searchIconBtn;

    @FindBy(xpath = "//i[@class='ban small icon']")
    WebElement clearFiltersIcon;

    @FindBy(xpath = "//div[@class='actions']/button[contains(text(),'Cancel')]")
    WebElement confirmCancelBtn;

    @FindBy(xpath = "//div[@class='actions']//button[@class='ui button']")
    WebElement confirmDeleteBtn;


    public DealsPage() {
        PageFactory.initElements(driver, this);
    }

    //Actions
    public NewDealsPage goToNewDealsPage() {
        newDealsBtn.click();
        return new NewDealsPage();
    }

    public boolean deleteOneDeal() throws InterruptedException {
        System.out.println(allRows.size());
        for (WebElement eachRow : allRows) {
            List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
            String name = allCols.get(1).getText();
            if (name.contains("Christmas")) {
                WebElement deleteIcon = eachRow.findElement(By.xpath("./td[contains(@class,'buttons-container')]//div/button"));
                deleteIcon.click();
                confirmDeleteBtn.click();
                break;
            }
        }
        driver.navigate().refresh();
        Thread.sleep(2000);
        for (WebElement eachRow : allRows) {
            List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
            String name = allCols.get(1).getText();
            if (name.contains("Christmas")) {
               return false;
            }
        }

        return true;
    }

    public boolean verifyCompanyEqualsFilterResults(String searchValue) throws InterruptedException {
        for(WebElement eachPage:allPages){
            eachPage.click();
            Thread.sleep(1000);
            for(WebElement eachRow:allRows){
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String companyCol = allCols.get(2).getText().toLowerCase();
                if(!searchValue.toLowerCase().equals(companyCol)){
                    return false;
                }
            }

        }return true;
    }

    public boolean filterByCompany(String searchComp) throws InterruptedException {
        showFiltersBtn.click();
        searchColumnField.click();
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(searchByCompany).build().perform();
        searchByCompany.click();
        operatorDrpdown.click();
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(companyEqualsOperator).build().perform();
        companyEqualsOperator.click();
        valueField.click();
        valueField.sendKeys(searchComp);
        String formattedXpath = String.format("//div[@class='visible menu transition']//div[@role='option']/span[contains(text(),'%s')]",searchComp);
        WebElement compName = driver.findElement(By.xpath(formattedXpath));
        compName.click();
        searchIconBtn.click();
        Thread.sleep(1000);
        return(verifyCompanyEqualsFilterResults(searchComp));
    }

    public boolean verifyStatusEqualsFilterResults(String searchValue) throws InterruptedException {
        for(WebElement eachPage:allPages){
            eachPage.click();
            Thread.sleep(1000);
            for(WebElement eachRow:allRows){
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
                String companyCol = allCols.get(5).getText().toLowerCase();
                if(!searchValue.toLowerCase().equals(companyCol)){
                    return false;
                }
            }

        }return true;
    }

    public boolean filterByNewStatus() throws InterruptedException {
        showFiltersBtn.click();
        clearFiltersIcon.click();
        showFiltersBtn.click();
        searchColumnField.click();
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(searchByStatus).build().perform();
        searchByStatus.click();
        operatorDrpdown.click();
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(companyEqualsOperator).build().perform();
        companyEqualsOperator.click();
        valueStatusField.click();
        Actions actions3 = new Actions(driver);
        actions3.moveToElement(newStatusFilter).build().perform();
        searchIconBtn.click();
        Thread.sleep(1000);
        return(verifyStatusEqualsFilterResults("New"));
    }

    public boolean filterByActiveStatus() throws InterruptedException {
        showFiltersBtn.click();
        clearFiltersIcon.click();
        showFiltersBtn.click();
        searchColumnField.click();
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(searchByStatus).build().perform();
        searchByStatus.click();
        operatorDrpdown.click();
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(companyEqualsOperator).build().perform();
        companyEqualsOperator.click();
        valueStatusField.click();
        Actions actions3 = new Actions(driver);
        actions3.moveToElement(activeStatusFilter).build().perform();
        activeStatusFilter.click();
        searchIconBtn.click();
        Thread.sleep(1000);
        return(verifyStatusEqualsFilterResults("Active"));
    }

    public boolean filterByInactiveStatus() throws InterruptedException {
        showFiltersBtn.click();
        clearFiltersIcon.click();
        showFiltersBtn.click();
        searchColumnField.click();
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(searchByStatus).build().perform();
        searchByStatus.click();
        operatorDrpdown.click();
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(companyEqualsOperator).build().perform();
        companyEqualsOperator.click();
        valueStatusField.click();
        Actions actions3 = new Actions(driver);
        actions3.moveToElement(inactiveStatusFilter).build().perform();
        inactiveStatusFilter.click();
        searchIconBtn.click();
        Thread.sleep(1000);
        return(verifyStatusEqualsFilterResults("Inactive"));
    }

    public boolean verifyResultTableForDates(String fromDate, String toDate) throws InterruptedException, ParseException {
        for(WebElement eachPage:allPages){
            eachPage.click();
            Thread.sleep(500);
            for(WebElement eachRow:allRows){
                List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));

                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date dateCol = format1.parse(allCols.get(3).getText());
                System.out.println(dateCol);
                Date formattedFromDate = format.parse(fromDate);
                Date formattedToDate = format.parse(toDate);

                if(dateCol.before(formattedFromDate)||dateCol.after(formattedToDate)){
                    System.out.println("Deal date " + dateCol.toString()+" was outside the range");
                    return false;
                }

            }
        }return true;
    }

    public boolean filterByDate(String fromDate, String toDate) throws InterruptedException, ParseException {
        showFiltersBtn.click();
        clearFiltersIcon.click();
        showFiltersBtn.click();
        searchColumnField.click();
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(searchByDate).build().perform();
        searchByDate.click();
        operatorDrpdown.click();
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(datesBetweenOperator).build().perform();
        datesBetweenOperator.click();
        //valueDate1Field.click();
        firstDateField.sendKeys(fromDate);
        //valueDate2Field.click();
        secondDateField.sendKeys(toDate);
        searchIconBtn.click();
        Thread.sleep(1000);
        return(verifyResultTableForDates(fromDate, toDate));
    }
}

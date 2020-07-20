package com.crm.qa.Util;

import com.crm.qa.base.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;



public class TestUtil extends BaseClass {

    public static long pageLoadTimeout = 30;
    public static long implicitlyWait = 10;
    static Xls_Reader reader;

    public static void takeScreenshot(String methodName) throws IOException {
        //take screenshot by casting driver to takescreeshot
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //now copy the file to the desired location using copyfile method
        FileUtils.copyFile(file,new File("C:\\Users\\browse\\WebAutomation\\FreeCRM\\Screenshots\\"+ "failShot of" +methodName+".jpg"));
    }

    public static ArrayList<Object[]> getTestDataForNewDealsFromExcel() {
        ArrayList<Object[]> testData = new ArrayList<>();
        reader = new Xls_Reader("C:\\Users\\browse\\WebAutomation\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM.xlsx");

        for (int rowNum = 2; rowNum <= reader.getRowCount("NewDealsDetails"); rowNum++) {
            String title = reader.getCellData("NewDealsDetails", "Title", rowNum);
            String company = reader.getCellData("NewDealsDetails", "Company", rowNum);
            String contacts = reader.getCellData("NewDealsDetails", "Contacts", rowNum);
            String amount = reader.getCellData("NewDealsDetails", "Amount", rowNum);
            String commission = reader.getCellData("NewDealsDetails", "Commission", rowNum);

            testData.add(new Object[]{title, company, contacts, amount, commission});
        }
        return testData;
    }

    public static ArrayList<Object[]> getNewTaskData() {
        ArrayList<Object[]> taskData = new ArrayList<>();
        reader = new Xls_Reader("C:\\Users\\browse\\WebAutomation\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRM.xlsx");
        for (int i = 2; i <= reader.getRowCount("NewTaskDetails"); i++) {
            String title = reader.getCellData("NewTaskDetails", "Title", i);
            String contacts = reader.getCellData("NewTaskDetails", "Contacts", i);
            String deals = reader.getCellData("NewTaskDetails", "Deals", i);
            String dueDate = reader.getCellData("NewTaskDetails", "DueDate", i);
            String closeDate = reader.getCellData("NewTaskDetails", "CloseDate", i);

            taskData.add(new Object[]{title, contacts, deals, dueDate, closeDate});
        }
        return taskData;
    }

}

package com.crm.qa.Util;

import com.crm.qa.base.BaseClass;

import java.util.ArrayList;
import java.util.List;

public class TestUtil extends BaseClass {

    public static long pageLoadTimeout = 30;
    public static long implicitlyWait = 10;
    static Xls_Reader reader;

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
}

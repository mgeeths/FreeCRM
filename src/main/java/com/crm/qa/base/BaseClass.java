package com.crm.qa.base;

import com.crm.qa.Util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;

    public BaseClass(){
        prop = new Properties();
        FileInputStream ip = null;
        try {
            ip = new FileInputStream("C:\\Users\\browse\\WebAutomation\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
       }

       public void launchBrowser(){
          String browserName = prop.getProperty("browser");
           if(browserName.equals("Chrome")) {
               System.setProperty("webdriver.chrome.driver", "C://Driver//chromedriver.exe");
               driver = new ChromeDriver();
           }else {
               System.setProperty("webdriver.gecko.driver", "C://Users//browse//Downloads//geckodriver-v0.26.0-win64//geckodriver.exe");
               driver = new FirefoxDriver();
           }
           driver.manage().deleteAllCookies();
           driver.manage().timeouts().implicitlyWait(TestUtil.implicitlyWait, TimeUnit.SECONDS);
           driver.manage().timeouts().pageLoadTimeout(TestUtil.pageLoadTimeout,TimeUnit.SECONDS);
           driver.manage().window().maximize();
       }
}

package com.cybertek.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    //Creating the private constructor so this class's object is not reachable from outside
    private Driver(){}

    //Making our driver instance private so that its not reachable from out side of the class.
    //Making it static because we want it to run before everything else, and also we will use it in static method
    private static WebDriver driver;


    //creating re-usability method that will return same 'driver' instance everytime we call it
    public static WebDriver getDriver(){
        if ( driver == null){
            //we read out browser type from config.prop file using .getProperty method we created in Config.Reader class
            String browserType = ConfigurationReader.getProperty("browser");

            /*
            Depending on the browser type, our switch statement will determine to open specific type of browser/driver.
             */
            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
            }
        }
        return driver;
    }

    /*

     */
    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver = null;
        }
    }

}

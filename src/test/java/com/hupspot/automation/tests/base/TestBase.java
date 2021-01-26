package com.hupspot.automation.tests.base;

import com.hupspot.automation.tests.constants.AppConst;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class TestBase {

    private static final Logger LOGGER = Logger.getLogger(TestBase.class);

    public WebDriver webDriver;
    public WebDriverWait webDriverWait;

    public WebDriver setUp() {
        String MethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        LOGGER.info("Current Method :: " + MethodName);

        String browser = AppConst.browser;

        if (null == webDriver) {
            switch (browser) {

                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver(setDesiredChromeOptions());
                    break;

                case "Firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    break;

                case "ie":
                    WebDriverManager.iedriver().setup();
                    webDriver = new InternetExplorerDriver();
                    break;

            }
        }

        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS) ;
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.get(AppConst.URL);
        webDriverWait = new WebDriverWait(webDriver,TimeUnit.MILLISECONDS.toSeconds(AppConst.timeout));


        return webDriver;
    }


    private ChromeOptions setDesiredChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
//        options.addArguments("--incognito", "--disable-blink-features=AutomationControlled");

        return options;
    }
}

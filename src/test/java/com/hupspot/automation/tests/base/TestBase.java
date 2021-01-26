package com.hupspot.automation.tests.base;

import com.hupspot.automation.tests.Utils.SelinumUtils;
import com.hupspot.automation.tests.constants.AppConst;
import com.hupspot.automation.tests.constants.HomepageConstants;
import com.hupspot.automation.tests.constants.LocatorType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
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
                    ChromeOptions chromeOptions = setDesiredChromeOptions();
                    webDriver = new ChromeDriver(chromeOptions);
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

        SelinumUtils.click(webDriver, HomepageConstants.cookie_decline_button, LocatorType.XPATH, AppConst.timeout,AppConst.polling_frequency );
        SelinumUtils.click(webDriver, HomepageConstants.chatBot_Cancel, LocatorType.XPATH);

        return webDriver;
    }


    private ChromeOptions setDesiredChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        Map prefs = new HashMap();
        prefs.put("profile.default_content_settings.cookies", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }
}

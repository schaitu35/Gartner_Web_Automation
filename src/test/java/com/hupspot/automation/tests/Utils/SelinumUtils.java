package com.hupspot.automation.tests.Utils;

import com.hupspot.automation.tests.constants.AppConst;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SelinumUtils {


    private static final Logger LOGGER = Logger.getLogger(SelinumUtils.class);

    public static By getLocator(String locator, String locatorType){

        By by;
        switch (locatorType) {

            case "ID":
                by = By.id(locator);
                break;
            case "CLASSNAME":
                by = By.className(locator);
                break;
            case "CSS":
                by = By.cssSelector(locator);
                break;
            case "LINKTEXT":
                by = By.linkText(locator);
                break;
            case "XPATH":
                by = By.xpath(locator);
                break;
            case "TAGNAME":
                by = By.tagName(locator);
                break;
            case "NAME":
                by = By.name(locator);
                break;
            case "PARTIAL_LINKTEXT":
                by = By.partialLinkText(locator);
                break;

            default:
                throw new IllegalStateException("Invalid Locator type: " + locatorType);
        }

       return by;

    }

    public static WebElement getElement(WebDriver webDriver, String locator, String locatorType, long timeOut, long pollingFrequency) {
        String MethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        LOGGER.info("Current Method :: " + MethodName);

        Wait<WebDriver> wait = new FluentWait<>(webDriver).withTimeout(Duration.ofMillis(timeOut)).pollingEvery(Duration.ofMillis(pollingFrequency))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,3000);

        return  wait.until(webDriver1 -> webDriver.findElement(getLocator(locator,locatorType)));

    }

    public static List<WebElement> getMultipleElements(WebDriver webDriver, String locator, String locatorType, long timeOut, long pollingFrequency){

        Wait<WebDriver> wait = new FluentWait<>(webDriver).withTimeout(timeOut, TimeUnit.MILLISECONDS).pollingEvery(pollingFrequency,TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
        return  wait.until(webDriver1 -> webDriver.findElements(getLocator(locator,locatorType)));

    }

    public static List<WebElement> getMultipleElements(WebDriver webDriver, String locator, String locatorType){

        return getMultipleElements(webDriver, locator, locatorType, AppConst.timeout, AppConst.polling_frequency);

    }

    public static void click(WebDriver webDriver, String locator, String locatorType) {

        click(webDriver, locator, locatorType , AppConst.timeout, AppConst.polling_frequency);
    }

    public static void click(WebDriver webDriver, String locator, String locatorType, long timeOut, long pollingFrequency) {
        String MethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        LOGGER.info("Current Method :: " + MethodName);
        try {
            WebElement element = getElement(webDriver ,locator ,locatorType ,timeOut ,pollingFrequency);
            element.click();
        } catch (ElementClickInterceptedException elementClickInterceptedException){
            LOGGER.error("Element Click Intercepted...!!!");
        } catch (ElementNotInteractableException elementNotInteractableException) {
            LOGGER.error("Element not Interactable...!!!");
        } catch (TimeoutException timeoutException){
            LOGGER.error("Couldn't find locator ::" + locator + " in " + timeOut);
        }

    }

    public static void enterText(WebDriver webDriver, String locator, String locatorType, String text,
                                                long timeOut, long pollingFrequency) {
        String MethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        LOGGER.info("Current Method :: " + MethodName);

        WebElement webElement = getElement(webDriver ,locator ,locatorType ,timeOut ,pollingFrequency );
        webElement.clear();
        webElement.sendKeys(text);
    }

    public static String getElementText( WebDriver webDriver, String locator, String locatorType, long timeOut, long pollingFrequency ){

        WebElement element = getElement(webDriver, locator, locatorType, timeOut, pollingFrequency);
        return  element.getText();
    }

    public static String getElementText( WebDriver webDriver, String locator, String locatorType){

        WebElement element = getElement(webDriver, locator, locatorType, AppConst.timeout, AppConst.polling_frequency);
        return  element.getText();
    }

    public static List<String> getMultipleElementsText(WebDriver webDriver, String locator, String locatorType, long timeOut, long pollingFrequency){
        List<WebElement> webElements = getMultipleElements(webDriver, locator, locatorType, timeOut, pollingFrequency);

        List<String> elementText = new ArrayList<>();


            for (WebElement element: webElements) {
                elementText.add(element.getText());
            }

        return elementText;
    }

    public static List<String> getMultipleElementsText(WebDriver webDriver, String locator, String locatorType){

        return getMultipleElementsText(webDriver, locator, locatorType, AppConst.timeout, AppConst.polling_frequency);

    }


    public static void selectFromDropdownByvisibleText(WebDriver webDriver, String locator, String locatorType, String text,
                                                       long timeOut, long pollingFrequency) {
        String MethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        LOGGER.info("Current Method :: " + MethodName);

        Select select = new Select(getElement( webDriver ,locator ,locatorType ,timeOut ,pollingFrequency ));
        select.selectByVisibleText(text);

    }

    public static void selectFromDropdownByIndex(WebDriver webDriver, String locator, String locatorType,
                                                    int index, long timeOut, long pollingFrequency) {

        String MethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        LOGGER.info("Current Method :: " + MethodName);

        Select select = new Select(getElement( webDriver ,locator ,locatorType ,timeOut ,pollingFrequency ));
        select.selectByIndex(index);
    }
    public static void selectFromDropdownByValue(WebDriver webDriver, String locator, String locatorType, String value,
                                                 long timeOut, long pollingFrequency) {

        String MethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        LOGGER.info("Current Method :: " + MethodName);

        Select select = new Select(getElement(webDriver ,locator ,locatorType ,timeOut ,pollingFrequency));
        select.selectByValue(value);
    }

    public static void mouseOverToElement(WebDriver webDriver, String locator, String locatorType) {

        mouseOverToElement(webDriver, locator, locatorType,AppConst.timeout,AppConst.polling_frequency);

    }
    public static void mouseOverToElement(WebDriver webDriver, String locator, String locatorType, long timeOut,
                                            long pollingFrequency) {

        String MethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        LOGGER.info("Current Method :: " + MethodName);

        Actions actions = new Actions(webDriver);
        Action action = actions.moveToElement(getElement( webDriver ,locator ,locatorType ,timeOut ,pollingFrequency )).build();
        action.perform();


    }

    public static boolean isElementDispayed(WebDriver webDriver, String locator, String locatorType, long timeOut, long pollingFrequency){

        WebElement element = getElement( webDriver ,locator ,locatorType ,timeOut ,pollingFrequency );
        return  element.isDisplayed();

    }

    public static boolean isElementDispayed(WebDriver webDriver, String locator, String locatorType){

        return  isElementDispayed( webDriver ,locator ,locatorType ,AppConst.timeout ,AppConst.polling_frequency );

    }

}

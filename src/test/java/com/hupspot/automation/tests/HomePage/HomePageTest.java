package com.hupspot.automation.tests.HomePage;

import com.hupspot.automation.tests.Utils.ScreenShot;
import com.hupspot.automation.tests.Utils.SelinumUtils;
import com.hupspot.automation.tests.base.TestBase;
import com.hupspot.automation.tests.constants.HomepageConstants;
import com.hupspot.automation.tests.constants.LocatorType;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest extends TestBase {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    private String testCaseName;

    @BeforeClass
    public void beforeClass(){
        setUp();
    }

    @Test
    public void TC_01_verify_pageTitle(){
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        LOGGER.info(" Test case executing is :: " + testCaseName);

        String title = webDriver.getTitle();
        LOGGER.info(" Actual Title :: " + title );
        LOGGER.info(" Expected Title :: " + HomepageConstants.title );

        Assert.assertEquals( title, HomepageConstants.title);


    }

    @Test
    public void TC_02_verify_Logo(){

        Assert.assertTrue(SelinumUtils.isElementDispayed(webDriver,HomepageConstants.logo ,LocatorType.XPATH),"Logo not displayed..");

    }

    @Test
    public void TC_03_verify_Locale_Dropdown(){

        SelinumUtils.mouseOverToElement(webDriver,HomepageConstants.locale_dropDown,LocatorType.XPATH);
        List<String> elements = SelinumUtils.getMultipleElementsText(webDriver,HomepageConstants.getLocale_dropDown,LocatorType.XPATH);
        for (String element: elements) {

            LOGGER.info(" " + element);
        }
        if(elements.size() != 6){
            ScreenShot.assertFalseWithScreenShot(webDriver);
        }

        SelinumUtils.selectFromDropdownByIndex(webDriver,"","",2,200,300);
    }
}

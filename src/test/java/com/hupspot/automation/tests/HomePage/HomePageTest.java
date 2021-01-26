package com.hupspot.automation.tests.HomePage;

import com.hupspot.automation.tests.Utils.ScreenShot;
import com.hupspot.automation.tests.Utils.SelinumUtils;
import com.hupspot.automation.tests.base.TestBase;
import com.hupspot.automation.tests.constants.HomepageConstants;
import com.hupspot.automation.tests.constants.LocatorType;
import com.hupspot.automation.tests.constants.LoginPageConstants;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    public void TC_03_verify_tabs_in_homePage() {

        String inSightsText = SelinumUtils.getElementText(webDriver, HomepageConstants.inSightsText,LocatorType.XPATH);
        String solutionsText = SelinumUtils.getElementText(webDriver, HomepageConstants.solutionsText,LocatorType.XPATH);
        String whatWeDoText = SelinumUtils.getElementText(webDriver, HomepageConstants.whatWeDoText,LocatorType.XPATH);

        if(! (inSightsText.equals("Insights") && solutionsText.equals("Solutions") && whatWeDoText.equals("What We Do") ) ){

            LOGGER.info("Home Tabs not verified..!! Please check  the screenshot");
            ScreenShot.assertFalseWithScreenShot(webDriver);

        }

    }

    @Test
    public void TC_04_verifyLoginPageLink() throws InterruptedException {
        SelinumUtils.click(webDriver,HomepageConstants.LoginPageLink, LocatorType.XPATH);
        if(! SelinumUtils.isElementDispayed(webDriver, LoginPageConstants.emailAddressField, LocatorType.ID) ){
            LOGGER.info("LoginPage not verified..!! Please check  the screenshot");
            ScreenShot.assertFalseWithScreenShot(webDriver);
        }

    }

}

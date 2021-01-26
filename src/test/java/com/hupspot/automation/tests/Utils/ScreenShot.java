package com.hupspot.automation.tests.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenShot {

    public static void getScreenShot(WebDriver webDriver) {
        try {
            Date date = new Date();
            String FileName = date.toString().replace(":", "_").replace(" ", "_") + ".png";
            File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            String absolutePath = new File("").getAbsolutePath();
            FileHandler.copy(screenshot, new File( absolutePath + File.separator + "Screenshot" + File.separator + FileName ));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void assertFalseWithScreenShot(WebDriver webDriver) {
        getScreenShot(webDriver);
        assert false;
    }

    public static void assertTrueWithScreenshot(WebDriver webDriver) {
        getScreenShot(webDriver);
        assert true;
    }


}

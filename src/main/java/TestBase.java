import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBase {

    private static Logger LOGGER = Logger.getLogger(TestBase.class);

    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    private static Properties properties;


    public WebDriver setUp() {
        LOGGER.info("Current Method :: SetUp");
        properties = loadProperties("config.properties", properties);
        String browser = properties.getProperty("browser");

        if (null == webDriver) {
            switch (browser) {

                case "Chrome":
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
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

        return webDriver;
    }

    public Properties loadProperties(String fileName , Properties props) {
        try {
            props = new Properties();
            InputStream inputStream = TestBase.class.getResourceAsStream(fileName);
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}

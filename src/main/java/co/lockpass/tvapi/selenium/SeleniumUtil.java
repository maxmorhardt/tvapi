package co.lockpass.tvapi.selenium;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component("selenium")
public class SeleniumUtil {

    private final Logger logger = LoggerFactory.getLogger(SeleniumUtil.class);
    private final String DRIVER_PATH = "havent downloaded it";

    private WebDriver driver;

    public SeleniumUtil() {
        logger.start("======== initializing selenium ========");
        logger.info("setting driver path to: " + DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

        logger.info("creating chrome options");
        ChromeOptions options = new ChromeOptions();
        logger.info("creating driver");
        driver = new ChromeDriver(options);
        logger.info("making window fullscreen");
        driver.manage().window().fullscreen();
        logger.success("======== completed selenium initialization ========");
    }

    public void visitUrl(String url) {
        logger.start("visiting url: " + url);
        try {
            driver.get(url);
        } catch (Exception e) {
            logger.error("error visiting url: " + url, e);
        }
        logger.success("successfully visited url: " + url);
    }

}

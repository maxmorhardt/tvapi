package co.lockpass.tvapi.selenium;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Duration;

@Component("seleniumUtil")
public class SeleniumUtil {

    private final Logger logger = LoggerFactory.getLogger(SeleniumUtil.class);
    private final String DRIVER_PATH = System.getProperty("user.home") + File.separator + "chromedriver" + File.separator + "chromedriver";
    private final int TIMEOUT_SEC = 30;

    private WebDriver driver;

    public void init() {
        logger.start("======== initializing selenium ========");

        logger.info("setting driver path to: " + DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

        logger.info("creating chrome options");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        logger.info("creating driver");
        driver = new ChromeDriver(options);

        logger.info("creating timeout settings");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_SEC));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT_SEC));

        logger.info("making window fullscreen");
        driver.manage().window().fullscreen();

        logger.success("======== completed selenium initialization ========");
    }

    public void visit(String url) {
        logger.start("visiting url: " + url);
        try {
            driver.get(url);
        } catch (Exception e) {
            logger.error("error visiting url: " + url, e);
            throw new RuntimeException("error visiting url: " + url);
        }
        logger.success("successfully visited url: " + url);
    }

    public void quit() {
        if (driver != null) {
            logger.start("======== quitting driver ========");
            driver.quit();
            logger.success("======== successfully quit driver ========");
        } else {
            logger.warn("driver quit attempt but was null");
        }
    }

    public void fullscreen() {
        //TODO
    }

}

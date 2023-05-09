package co.lockpass.tvapi.selenium;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Duration;

@Component("selenium")
public abstract class Selenium {

    private final Logger logger = LoggerFactory.getLogger(Selenium.class);

    private final String DRIVER_PATH;
    private final String PROFILE_PATH;

    private final int TIMEOUT_SEC = 30;

    private WebDriver driver;

    public Selenium(@Autowired Environment env) {
        this.DRIVER_PATH = env.getProperty("driver.path");
        this.PROFILE_PATH = env.getProperty("profile.path");
        init();
    }

    /**
     * Initializes driver with profile and geckodriver
     */
    public void init() {
        logger.start("======== initializing selenium ========");

        logger.info("setting driver path to: " + DRIVER_PATH);
        System.setProperty("webdriver.gecko.driver", DRIVER_PATH);

        logger.info("adding existing profile to options at path: " + PROFILE_PATH);
        File profileDir = new File(PROFILE_PATH);
        if (!profileDir.exists()) {
            logger.error("profile path invalid: " + PROFILE_PATH);
            throw new IllegalStateException("profile path invalid: " + PROFILE_PATH);
        }
        FirefoxProfile profile = new FirefoxProfile(profileDir);

        logger.info("creating options with profile");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        logger.info("creating driver");
        driver = new FirefoxDriver(options);

        logger.info("creating timeout settings");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_SEC));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT_SEC));

        logger.success("======== completed selenium initialization ========");
    }

    /**
     * Visits a website
     *
     * @param url url to website
     */
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

    /**
     * Quits the driver (can be initialized again)
     */
    public void quit() {
        if (driver != null) {
            logger.start("======== quitting driver ========");
            driver.quit();
            logger.success("======== successfully quit driver ========");
        } else {
            logger.warn("driver quit attempted but was null");
        }
    }

    public abstract void startVideo();

    public abstract void pauseVideo();

    /**
     * Puts a video in full screen (needs to be overrided for different platforms)
     */
    public abstract void fullscreen();

    /**
     * Synchronously waits for a given period of time (good for slow loading websites)
     *
     * @param waitTimeMs time in milliseconds to wait
     */
    protected final void syncWait(int waitTimeMs) {
        synchronized (driver) {
            try {
                driver.wait(waitTimeMs);
            } catch (InterruptedException e) {
                logger.error("error waiting in fullscreen", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Sends a string of text to a tag given an html tag name
     *
     * @param keys string of text to send
     * @param tagName name of html tag
     */
    protected final void sendKeysByTagName(String keys, String tagName) {
        WebElement element = driver.findElement(By.tagName(tagName));
        element.sendKeys(keys);
    }

}

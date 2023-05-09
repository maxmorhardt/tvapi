package co.lockpass.tvapi.selenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SeleniumFactory {

    @Autowired
    @Qualifier("selenium")
    private Selenium selenium;

    @Autowired
    @Qualifier("youtubeSelenium")
    private Selenium youtubeSelenium;

    @Autowired
    @Qualifier("streamSelenium")
    private Selenium streamSelenium;

    public Selenium getSelenium(SeleniumType type) {
        if (type == SeleniumType.NORMAL) {
            return selenium;
        } else if (type == SeleniumType.YOUTUBE) {
            return youtubeSelenium;
        } else if (type == SeleniumType.STREAM) {
            return streamSelenium;
        }
        throw new IllegalArgumentException("invalid type supplied: " + type);
    }

}

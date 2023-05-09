package co.lockpass.tvapi.selenium;

import co.lockpass.tvapi.selenium.Selenium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SeleniumFactory {

    @Autowired
    @Qualifier("youtubeSelenium")
    private Selenium youtubeSelenium;

    public Selenium getSelenium(SeleniumType type) {
        if (type == SeleniumType.YOUTUBE) {
            return youtubeSelenium;
        }
        throw new IllegalArgumentException("invalid type supplied: " + type);
    }

}

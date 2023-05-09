package co.lockpass.tvapi.selenium;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("youtubeSelenium")
public class YoutubeSelenium extends Selenium {

    private final Logger logger = LoggerFactory.getLogger(YoutubeSelenium.class);

    public YoutubeSelenium(@Autowired Environment env) {
        super(env);
    }

    @Override
    public void fullscreen() {
        logger.info("making youtube video fullscreen...");
        waitThreeSeconds();
        sendKeysByTagName("f", "html");
    }

}

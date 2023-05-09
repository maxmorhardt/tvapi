package co.lockpass.tvapi.selenium;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("streamSelenium")
public class StreamSelenium extends Selenium {

    private final Logger logger = LoggerFactory.getLogger(StreamSelenium.class);

    public StreamSelenium(@Autowired Environment env) {
        super(env);
    }

    @Override
    public void startVideo() {
        logger.info("starting stream video...");
        waitThreeSeconds();
        clickByClassName("player-poster");
    }

    @Override
    public void fullscreen() {
        logger.info("making stream video fullscreen...");
        waitThreeSeconds();
        clickByCssSelector("[aria-label=fullscreen]");
    }

}

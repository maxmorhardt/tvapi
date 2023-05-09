package co.lockpass.tvapi.selenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("youtubeSelenium")
public class YoutubeSelenium extends Selenium {

    public YoutubeSelenium(@Autowired Environment env) {
        super(env);
    }

    @Override
    public void fullscreen() {
        final int THREE_SECONDS_IN_MS = 3000;
        syncWait(THREE_SECONDS_IN_MS);
        sendKeysByTagName("f", "html");
    }

}

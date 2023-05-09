package co.lockpass.tvapi.selenium.youtube;

import co.lockpass.tvapi.selenium.Selenium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component("youtubeSelenium")
public class YoutubeSelenium extends Selenium {

    public YoutubeSelenium(@Autowired Environment env) {
        super(env);
    }

    @Override
    public void startVideo() {
        sendKeysByTagName(" ", "html");
    }

    @Override
    public void pauseVideo() {
        startVideo();
    }

    @Override
    public void fullscreen() {
        final int FIVE_SECONDS_IN_MS = 5000;
        syncWait(FIVE_SECONDS_IN_MS);
        sendKeysByTagName("f", "html");
    }

}

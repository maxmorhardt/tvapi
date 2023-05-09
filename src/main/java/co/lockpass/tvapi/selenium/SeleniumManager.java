package co.lockpass.tvapi.selenium;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles selenium api calls
 */
@Component
public class SeleniumManager {

    private final Logger logger = LoggerFactory.getLogger(SeleniumManager.class);

    @Autowired
    private SeleniumFactory seleniumFactory;
    private Selenium selenium;

    /**
     * Runs a single selenium instruction by calling the appropriate method in selenium util
     *
     * @param instruction instruction to be run
     * @param url optional arg for url if a visit instruction is given
     */
    private void runOneInstruction(SeleniumInstruction instruction, String url) {
        switch (instruction) {
            case INIT -> selenium.init();
            case VISIT -> selenium.visit(url);
            case QUIT -> selenium.quit();
            case FULLSCREEN -> selenium.fullscreen();
            case START -> selenium.startVideo();
            case PAUSE -> selenium.pauseVideo();
        }
    }

    /**
     * Runs all instructions for a given array of instructions
     *
     * @param instructions array of instructions to be run
     * @param url optional arg for url if a visit instruction is given
     */
    public void runAllInstructions(SeleniumInstruction[] instructions, String url, SeleniumType type) {
        selenium = seleniumFactory.getSelenium(type);
        for (SeleniumInstruction instruction : instructions) {
            runOneInstruction(instruction, url);
        }
    }

}

package co.lockpass.tvapi.selenium;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles selenium api calls
 */
@Component("seleniumManager")
public class SeleniumManager {

    private final Logger logger = LoggerFactory.getLogger(SeleniumManager.class);

    @Autowired
    private SeleniumUtil seleniumUtil;

    /**
     * Runs a single selenium instruction by calling the appropriate method in selenium util
     *
     * @param instruction instruction to be run
     * @param url optional arg for url if a visit instruction is given
     */
    private void runOneInstruction(SeleniumInstruction instruction, String... url) {
        String urlStr = "";
        if (url.length > 0) {
            urlStr = url[0];
        }
        switch (instruction) {
            case INIT -> seleniumUtil.init();
            case VISIT -> seleniumUtil.visit(urlStr);
            case QUIT -> seleniumUtil.quit();
            case FULLSCREEN -> seleniumUtil.fullscreen();
        }
    }

    /**
     * Runs all instructions for a given array of instructions
     *
     * @param instructions array of instructions to be run
     * @param url optional arg for url if a visit instruction is given
     */
    public void runAllInstructions(SeleniumInstruction[] instructions, String... url) {
        if (url.length > 1) {
            logger.error("url param must be of length 0 or 1");
            throw new IllegalArgumentException("url param must be of length 0 or 1");
        }
        for (SeleniumInstruction instruction : instructions) {
            runOneInstruction(instruction, url);
        }
    }

}

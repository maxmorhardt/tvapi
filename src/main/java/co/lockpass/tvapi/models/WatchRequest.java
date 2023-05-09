package co.lockpass.tvapi.models;

import co.lockpass.tvapi.selenium.SeleniumInstruction;

import java.util.Arrays;
import java.util.List;

/**
 * Model class for a request to watch something
 */
public class WatchRequest {

    private SeleniumInstruction[] instructions;

    public WatchRequest(SeleniumInstruction[] instructions) {
        this.instructions = instructions;
    }

    public WatchRequest() {}

    public SeleniumInstruction[] getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return Arrays.toString(instructions);
    }

}

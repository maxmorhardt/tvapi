package co.lockpass.tvapi.models;

import java.util.List;

public class WatchRequest {

    private String url;
    private List<String> instructions;

    public WatchRequest() {}

    public WatchRequest(String url, List<String> instructions) {
        this.url = url;
        this.instructions = instructions;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getInstructions() {
        return instructions;
    }

}

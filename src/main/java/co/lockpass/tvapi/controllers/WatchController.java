package co.lockpass.tvapi.controllers;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import co.lockpass.tvapi.models.WatchRequest;
import co.lockpass.tvapi.selenium.SeleniumManager;
import co.lockpass.tvapi.selenium.SeleniumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/watch")
public class WatchController {

    private final Logger logger = LoggerFactory.getLogger(WatchController.class);

    @Autowired
    @Qualifier("seleniumManager")
    private SeleniumManager seleniumManager;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("/watch is online", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> normal(@RequestBody WatchRequest request) {
        return watch(null, request, SeleniumType.NORMAL);
    }

    @PostMapping("/youtube")
    public ResponseEntity<String> youtube(@RequestParam(required = false) String url, @RequestBody WatchRequest request) {
        return watch(url, request, SeleniumType.YOUTUBE);
    }

    @PostMapping("/stream")
    public ResponseEntity<String> stream(@RequestParam(required = false) String url, @RequestBody WatchRequest request) {
        return watch(url, request, SeleniumType.STREAM);
    }

    /**
     * Method that will run selenium based on instructions given from request
     *
     * @param url url to visit (optional if not doing view instruction)
     * @param request request with instructions list
     * @param type type of the selenium subclass
     * @return response entity with message once selenium has completed running
     */
    private ResponseEntity<String> watch(String url, WatchRequest request, SeleniumType type) {
        logger.info("received request with url " + url + " and request " + request);
        seleniumManager.runAllInstructions(request.getInstructions(), url, type);
        return new ResponseEntity<>("request completed", HttpStatus.OK);
    }

}

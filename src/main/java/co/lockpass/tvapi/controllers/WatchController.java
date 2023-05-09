package co.lockpass.tvapi.controllers;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import co.lockpass.tvapi.models.WatchRequest;
import co.lockpass.tvapi.selenium.SeleniumManager;
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

    @PostMapping("/youtube")
    public ResponseEntity<String> watchVideo(@RequestParam(required = false) String url, @RequestBody WatchRequest request) {
        if (url != null && !url.contains("youtube")) {
            return new ResponseEntity<>("invalid request for youtube", HttpStatus.BAD_REQUEST);
        }
        logger.info("received request with url " + url + " and request " + request);
        logger.start("calling selenium");
        seleniumManager.runAllInstructions(request.getInstructions(), url);
        return new ResponseEntity<>("request completed", HttpStatus.OK);
    }

}

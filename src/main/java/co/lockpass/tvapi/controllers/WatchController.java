package co.lockpass.tvapi.controllers;

import co.lockpass.tvapi.loggerwrapper.Logger;
import co.lockpass.tvapi.loggerwrapper.LoggerFactory;
import co.lockpass.tvapi.models.WatchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchController {

    private final Logger logger = LoggerFactory.getLogger(WatchController.class);

    @PostMapping("/watch")
    public ResponseEntity<String> watchVideo(@RequestParam(required = false) String url, @RequestBody WatchRequest request) {
        logger.warn(url);
        logger.warn(request.toString());
        return ResponseEntity.ok("got the stuff");
    }

}
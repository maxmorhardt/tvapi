package co.lockpass.tvapi.rest;

import co.lockpass.tvapi.models.WatchRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/api")
public class RestController {

    @PostMapping("/login")
    public void watchYoutubeVideo(@RequestBody WatchRequest request) {

    }

}

package ratelimiter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

public class AlgoRateLimiter {
    private final RateLimiter rateLimiter;
    @GetMapping("/check/algo")
    public ResponseEntity<String> check_algo(@RequestHeader(value = "X-API-KEY", defaultValue = "anonymous") String apiKey){
        if(!rateLimiterService.isAllowed(apiKey)){

        }
    }

}

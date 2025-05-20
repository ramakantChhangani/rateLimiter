package com.example.ratelimiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.example.ratelimiter.service.RateLimiterService;

@RestController
public class RateLimiterController {

    @Autowired
    private RateLimiterService rateLimiterService;

    @GetMapping("/ping")
    public ResponseEntity<String> check(@RequestHeader(value = "X-API-KEY", defaultValue = "anonymous") String apiKey){
        if(!rateLimiterService.isAllowed(apiKey)){
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too Many Requests for : " + apiKey);
        }
        return ResponseEntity.ok("Hello Ramakant");
    }
}

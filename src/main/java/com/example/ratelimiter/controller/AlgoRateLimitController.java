package com.example.ratelimiter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ratelimiter.service.RateLimiter;
import com.example.ratelimiter.service.algofactory.RateLimiterAlgoFactory;

@RestController
public class AlgoRateLimitController {
    private final RateLimiter rateLimiter;
    private final String algorithm ;

    public AlgoRateLimitController(RateLimiterAlgoFactory factory) {
        this.rateLimiter = factory.getRateLimiter();
        this.algorithm = factory.getAlgorithm();
    }

    @GetMapping("check/algo")
    public int check_algo(@RequestHeader(value = "X-API-KEY", defaultValue = "anonymous") String apiKey){
        return rateLimiter.isAllowed(apiKey);
    }

    @GetMapping("check/algo/algorithm")
    public String getAlgorithm(){
        return this.algorithm;
    }
}

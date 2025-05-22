package com.example.ratelimiter.service.algofactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.ratelimiter.service.RateLimiter;

import java.util.Map;

@Component
public class RateLimiterAlgoFactory {
    private final Map<String, RateLimiter> rateLimiterMap;
    private final String algorithm;

    public RateLimiterAlgoFactory(Map<String, RateLimiter> rateLimiterMap, @Value("${rate.limit.algorithm}") String algorithm) {
        this.algorithm = algorithm;
        this.rateLimiterMap = rateLimiterMap;
    }

    public String getAlgorithm(){
        return this.algorithm;
    }

    public RateLimiter getRateLimiter(){
        return rateLimiterMap.getOrDefault(algorithm, rateLimiterMap.get("TOKEN_BUCKET"));
    }
}

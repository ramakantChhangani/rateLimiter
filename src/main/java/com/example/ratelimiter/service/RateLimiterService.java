package com.example.ratelimiter.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private final Duration window = Duration.ofSeconds(10);

    public boolean isAllowed(String key){
        String redisKey = "rate_limit:" + key;
        Long requests = redisTemplate.opsForValue().increment(redisKey);

        if(requests == null){
            return false;
        }
        if(requests == 1){
            redisTemplate.expire(redisKey, window);
        }

        int MAX_REQUESTS = 5;
        return requests.intValue() <= MAX_REQUESTS;
    }
}

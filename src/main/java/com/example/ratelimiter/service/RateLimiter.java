package com.example.ratelimiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.example.ratelimiter.service.TokenBucketScheduler;


public interface RateLimiter {
    int isAllowed(String apiKey);
}

@Service("TOKEN_BUCKET")
class TokenBucketRateLimiter implements RateLimiter{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private TokenBucketScheduler scheduler;

    @Override
    public int isAllowed(String apiKey) {

        scheduler.registerKey(apiKey);

        String key = "token_bucket_rate_limit:" + apiKey;

        String val = redisTemplate.opsForValue().get(key);

        int value = (val != null) ? Integer.parseInt(val) : 0;

        if(value == 0){
            redisTemplate.delete(key);
            return -1;
        }

        redisTemplate.opsForValue().decrement(key);
        return value--;
    }
}

@Service("LEAKY_TOKEN_BUCKET")
class LeakyTokenBucketRateLimiter implements RateLimiter{
    @Override
    public int isAllowed(String apiKey) {
        return -1;
    }
}

@Service("SLIDING_WINDOW")
class SlidingWindowRateLimiter implements RateLimiter{
    @Override
    public int isAllowed(String apiKey) {
        return -1;
    }
}
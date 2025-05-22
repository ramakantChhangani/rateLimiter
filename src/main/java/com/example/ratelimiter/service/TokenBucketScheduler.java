package com.example.ratelimiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenBucketScheduler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${tokens.to.add}")
    private int tokensToAdd;

    @Value("${token.bucket.size}")
    private int fixedBucketSize;

    private final Set<String> activeKeys = new HashSet<>();

    public void registerKey(String key){
        activeKeys.add(key);
    }

    //refills bucket in every preset.time.to.fill
    @Scheduled(fixedRateString = "${preset.time.to.fill}")
    public void refillBucket(){
        for(String apiKey : activeKeys) {
            String key = "token_bucket_rate_limit:" + apiKey;
            redisTemplate.opsForValue().set(key, String.valueOf(fixedBucketSize));
        }
    }
}

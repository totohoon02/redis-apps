package com.redisapps.Service;

import com.redisapps.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void setValues(String key, String data) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    public void setValuesNX(String key, String data) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.setIfAbsent(key, data);
    }

    public void removeValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.getAndDelete(key);
    }

    public void setExpiredValue(String key, String data, int expiredAfter) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data, expiredAfter, TimeUnit.SECONDS);

    }

    public String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) {
            return "false";
        }
        return (String) values.get(key);
    }

    // ##### Applications #####
    public ResponseDto rateLimit() {
        final String LIMIT_KEY = "limitKey";
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(LIMIT_KEY) == null) {
            setExpiredValue(LIMIT_KEY, "VALUE", 60);
            return new ResponseDto(HttpStatus.OK, "OK");
        }
        return new ResponseDto(HttpStatus.TOO_MANY_REQUESTS, "요청은 1분에 한번씩 가능합니다.");
    }
}

package com.redisapps.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final RedisTemplate<String, Object> redisTemplate;

    public String setVerifyingCode(String email) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        double randomValue = Math.random();
        String code = Integer.toString((int) (randomValue * 10000));
        if (ops.get(email) != null) {
            throw new RuntimeException("연속해서 실행할 수 없습니다.");
        }
        ops.set(email, code, 180, TimeUnit.SECONDS);
        return code;
    }

    public Boolean getVerifyingCode(String email, String code) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String verifyingCode = (String) ops.get(email);
        return code.equals(verifyingCode);
    }

    public void removeVerifyingCode(String email){
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.getAndDelete(email);
    }
}
package com.redisapps.controller;

import com.redisapps.Service.RedisService;
import com.redisapps.dto.RedisGetRequestDto;
import com.redisapps.dto.RedisPostRequestDto;
import com.redisapps.utils.DistributionLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("redis")
@Slf4j
public class RedisController {
    private final RedisService redisService;

    @GetMapping()
    public String getValue(@RequestBody RedisGetRequestDto request) {
        return redisService.getValues(request.getKey());
    }

    @PostMapping()
    public void setValue(@RequestBody RedisPostRequestDto<String> request) {
        redisService.setValues(request.getKey(), request.getValue());
    }

    @PostMapping("/expired")
    public void setExpireValue(@RequestBody RedisPostRequestDto<String> request) {
        redisService.setExpiredValue(request.getKey(), request.getValue(), 10);
    }

    // ##### Applications #####

    // 분산 락
    @GetMapping("/d-lock")
    public void distributionLock() {
        int random = (int) Math.round(Math.random() * 1000);
        DistributionLock m1 = new DistributionLock(redisService, "thread" + "[" + random + "]");
        m1.start();
    }

}

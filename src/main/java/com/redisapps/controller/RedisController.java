package com.redisapps.controller;

import com.redisapps.Service.RedisService;
import com.redisapps.dto.RedisGetRequestDto;
import com.redisapps.dto.RedisPostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {
    private final RedisService redisService;

    @GetMapping()
    public String getValue(@RequestBody RedisGetRequestDto request){
        return redisService.getValues(request.getKey());
    }

    @PostMapping()
    public void setValue(@RequestBody RedisPostRequestDto<String> request){
        redisService.setValues(request.getKey(), request.getValue());
    }
}
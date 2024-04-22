package com.redisapps.dto;

import lombok.Getter;

@Getter
public class RedisPostRequestDto<T> {
    private String key;
    private T value;
}

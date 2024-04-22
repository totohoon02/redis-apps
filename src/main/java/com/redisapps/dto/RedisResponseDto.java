package com.redisapps.dto;

import lombok.Setter;

@Setter
public class RedisResponseDto<T> {
    private String key;
    private T value;
}

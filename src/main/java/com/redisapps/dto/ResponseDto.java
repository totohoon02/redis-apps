package com.redisapps.dto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ResponseDto {
    private HttpStatus statusCode;
    private String message;
}

package com.redisapps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private HttpStatus statusCode;
    private String message;
}

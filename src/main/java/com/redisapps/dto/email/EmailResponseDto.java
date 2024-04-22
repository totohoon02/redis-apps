package com.redisapps.dto.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailResponseDto {
    private String verifyCode;
}

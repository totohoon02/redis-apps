package com.redisapps.controller;

import com.redisapps.Service.EmailService;
import com.redisapps.dto.email.CodeRequestDto;
import com.redisapps.dto.email.EmailRequestDto;
import com.redisapps.dto.email.EmailResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("email")
public class EmailController {
    private final EmailService emailService;

    @PostMapping()
    public ResponseEntity<EmailResponseDto> setVerifyCode(@RequestBody EmailRequestDto request) {
        log.info("유저 요청: " + request.getEmail());
        try {
            String code = emailService.setVerifyingCode(request.getEmail());
            return new ResponseEntity<>(new EmailResponseDto(code), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EmailResponseDto(null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<HttpStatus> verifyCode(@RequestBody CodeRequestDto request) {
        Boolean isCorrect = emailService.getVerifyingCode(request.getEmail(), request.getCode());
        if (isCorrect) {
            emailService.removeVerifyingCode(request.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

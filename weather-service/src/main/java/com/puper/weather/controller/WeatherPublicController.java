package com.puper.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/v1/weather")
@Validated
public class WeatherPublicController {

    @GetMapping(path = "/test")
    public ResponseEntity<String> getNothing() {
        return ResponseEntity.ok("Hello!");
    }
}
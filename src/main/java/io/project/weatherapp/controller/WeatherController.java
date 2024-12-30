package io.project.weatherapp.controller;

import io.project.weatherapp.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/getWeather")
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        String response = weatherService.getWeather(city);
        return ResponseEntity.ok(response);
    }

}

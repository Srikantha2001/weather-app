package io.project.weatherapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.project.weatherapp.model.WeatherResponse;
import io.project.weatherapp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Fetch Weather data based on given city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched weather data",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = WeatherResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Error Fetching weather data, invalid city", content = @Content)
    })
    @GetMapping("/getWeather")
    public ResponseEntity<WeatherResponse> getWeather(@Parameter(description = "City Name") @RequestParam String city) {
        try {
            WeatherResponse weatherResponse = weatherService.getWeather(city);
            return ResponseEntity.ok(weatherResponse);
        }
        catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}

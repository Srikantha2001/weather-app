package io.project.weatherapp.model;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private String cityName;
    private Double currentTemperature;
    private Double currentPressure;
    private Double currentHumidity;
    private Double maximumTemperature;
    private Double minimumTemperature;
    private Double currentWindSpeed;
}

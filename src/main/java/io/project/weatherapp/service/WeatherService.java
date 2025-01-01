package io.project.weatherapp.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.project.weatherapp.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    @Value("${weather.app.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public WeatherResponse getWeather(String city) throws JsonProcessingException {

        String url = UriComponentsBuilder.fromUriString("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q",city.toLowerCase())
                .queryParam("appid", apiKey)
                .toUriString();
        String response = restTemplate.getForObject(url, String.class);
        JsonNode jsonNode = objectMapper.readTree(response);
        return WeatherResponse.builder()
                .cityName(jsonNode.get("name").asText())
                .currentTemperature(jsonNode.get("main").get("temp").asDouble())
                .currentHumidity(jsonNode.get("main").get("humidity").asDouble())
                .currentPressure(jsonNode.get("main").get("pressure").asDouble())
                .currentWindSpeed(jsonNode.get("wind").get("speed").asDouble())
                .maximumTemperature(jsonNode.get("main").get("temp_max").asDouble())
                .minimumTemperature(jsonNode.get("main").get("temp_min").asDouble())
                .build();
    }
}

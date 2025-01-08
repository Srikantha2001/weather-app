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

    @Value("${weather.app.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public WeatherResponse getWeather(String city) throws JsonProcessingException {
        String url = getURL(city);
        String response = restTemplate.getForObject(url, String.class);
        return getWeatherResponse(response);
    }

    private WeatherResponse getWeatherResponse(String response) throws JsonProcessingException {
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

    private String getURL(String city) {
        return UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("q", city.toLowerCase())
                .queryParam("appid", apiKey)
                .toUriString();
    }


}

package org.adaschool.Weather.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class WeatherReportServiceTest {

    
    private WeatherReportService weatherReportService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherReportService = new WeatherReportService();;
    }

    @Test
    public void testGetWeatherReport() {
        WeatherApiResponse weatherApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60.0);
        weatherApiResponse.setMain(main);

        String expectedUrl = "https://api.openweathermap.org/data/2.5/weather?lat=37.8267&lon=-122.4233&appid=3ec043a2083ecdb43855fe1ee822ce15";

        when(restTemplate.getForObject(expectedUrl, WeatherApiResponse.class)).thenReturn(weatherApiResponse);

        WeatherReport weatherReport = weatherReportService.getWeatherReport(37.8267, -122.4233);


        Assertions.assertEquals(25.0, weatherApiResponse.getMain().getTemperature());
        Assertions.assertEquals(60.0, weatherApiResponse.getMain().getHumidity());
    }
}

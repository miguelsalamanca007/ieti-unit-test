package org.adaschool.Weather.controller;

import static org.mockito.Mockito.when;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class WeatherReportControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new WeatherReportController(weatherReportService)).build();
    }

    @Test
    public void testGetWeatherReport() throws Exception {
        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setTemperature(25.0);
        weatherReport.setHumidity(60.0);
        when(weatherReportService.getWeatherReport(37.8267, -122.4233)).thenReturn(weatherReport);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/weather-report")
                .param("latitude", "37.8267")
                .param("longitude", "-122.4233"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(60.0));
    }
}

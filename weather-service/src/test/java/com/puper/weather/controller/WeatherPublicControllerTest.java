package com.puper.weather.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WeatherPublicController.class)
class WeatherPublicControllerTest {

    private static final String WEATHER_URL = "/public/v1/weather";
    private static final String TEST_URL = WEATHER_URL + "/test";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTest() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.get(TEST_URL))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("Hello!");
    }
}
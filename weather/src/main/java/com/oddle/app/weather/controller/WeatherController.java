package com.oddle.app.weather.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oddle.app.weather.dto.openweather.OpenWeatherDto;
import com.oddle.app.weather.service.WeatherService;
import com.oddle.app.weather.utility.Response;

@RestController
@RequestMapping("/api")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Oddle Backend Challenge");
    }
    
    @GetMapping("/weather")
    public ResponseEntity<OpenWeatherDto> getWeatherByCity(@RequestParam("city") String city) {
    	return Response.ok(weatherService.getWeatherByCity(city));
    }
}
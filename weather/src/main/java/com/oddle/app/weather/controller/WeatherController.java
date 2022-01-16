package com.oddle.app.weather.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oddle.app.weather.dto.openweather.WeatherDto;
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
    public ResponseEntity<List<WeatherDto>> getAllWeather() {
    	List<WeatherDto> weathers = weatherService.getAllWeather();
    	return Response.ok(weathers);
    }
    
    @GetMapping("/weather/{weatherId}")
    public ResponseEntity<WeatherDto> getWeatherById(@PathVariable("weatherId") Long weatherId) {
    	WeatherDto weather = weatherService.getWeatherById(weatherId);
    	return Response.ok(weather);
    }
    
    @PostMapping("/weather")
    public ResponseEntity<WeatherDto> saveWeather(@RequestBody WeatherDto weatherDto) {
    	WeatherDto weather = weatherService.saveWeather(weatherDto);
    	return Response.ok(weather);
    }
    
    @PutMapping("/weather/{weatherId}")
    public ResponseEntity<WeatherDto> updateWeather(@PathVariable("weatherId") Long weatherId, @RequestBody WeatherDto weatherDto) {
    	WeatherDto weather = weatherService.updateWeather(weatherId, weatherDto);
    	return Response.ok(weather);
    }
    
    @DeleteMapping("/weather/{weatherId}")
    public ResponseEntity<String> deleteWeather(@PathVariable("weatherId") Long weatherId) {
    	weatherService.deleteWeather(weatherId);
    	return Response.ok("Deleted Successfully");
    }
}
package com.oddle.app.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oddle.app.weather.dto.CityWeatherDto;
import com.oddle.app.weather.dto.openweather.OpenWeatherDto;
import com.oddle.app.weather.service.CityWeatherService;
import com.oddle.app.weather.utility.Response;

@RestController
@RequestMapping("/api")
public class CityWeatherController {
	
	@Autowired
	private CityWeatherService cityWeatherService;
	
	@GetMapping("/city-weather")
    public ResponseEntity<OpenWeatherDto> getWeatherByCity(@RequestParam("city") String city) {
    	OpenWeatherDto openWeather = cityWeatherService.getWeatherByCity(city);
    	return Response.ok(openWeather);
    }
	
	@PostMapping("/city-weather")
	public ResponseEntity<CityWeatherDto> saveCityWeather(@RequestBody CityWeatherDto cityWeatherDto) {
		CityWeatherDto cityWeather = cityWeatherService.saveWeatherData(cityWeatherDto);
		return Response.ok(cityWeather);
	}
}

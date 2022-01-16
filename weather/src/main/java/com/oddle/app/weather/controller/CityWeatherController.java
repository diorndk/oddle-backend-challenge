package com.oddle.app.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/city-weathers")
	public ResponseEntity<List<CityWeatherDto>> getAllCityWeather(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		List<CityWeatherDto> cityWeathers = cityWeatherService.getWeatherFromPastDate(fromDate, toDate);
		return Response.ok(cityWeathers);
	}
	
	@GetMapping("/city-weathers/{cityWeatherId}")
	public ResponseEntity<CityWeatherDto> getAllCityWeather(@PathVariable("cityWeatherId") Long cityWeatherId) {
		CityWeatherDto cityWeather = cityWeatherService.getCityWeatherById(cityWeatherId);
		return Response.ok(cityWeather);
	}
	
	@PostMapping("/city-weathers")
	public ResponseEntity<CityWeatherDto> saveCityWeather(@RequestBody CityWeatherDto cityWeatherDto) {
		CityWeatherDto cityWeather = cityWeatherService.saveWeatherData(cityWeatherDto);
		return Response.ok(cityWeather);
	}
	
	@PutMapping("/city-weathers/{cityWeatherId}")
	public ResponseEntity<CityWeatherDto> updateCityWeather(@PathVariable("cityWeatherId") Long cityWeatherId, @RequestBody CityWeatherDto cityWeatherDto) {
		CityWeatherDto cityWeather = cityWeatherService.updateWeatherData(cityWeatherId, cityWeatherDto);
		return Response.ok(cityWeather);
	}
	
	@DeleteMapping("/city-weathers/{cityWeatherId}")
	public ResponseEntity<String> deleteCityWeather(@PathVariable("cityWeatherId") Long cityWeatherId) {
		cityWeatherService.deleteWeatherData(cityWeatherId);
		return Response.ok("Deleted Successfully");
	}
}

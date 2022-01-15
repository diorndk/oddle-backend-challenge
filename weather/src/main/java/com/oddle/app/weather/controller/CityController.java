package com.oddle.app.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oddle.app.weather.dto.CityDto;
import com.oddle.app.weather.service.CityService;
import com.oddle.app.weather.utility.Response;

@RestController
@RequestMapping("/api")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/city")
	public ResponseEntity<List<CityDto>> getAllCity() {
		List<CityDto> cities = cityService.getAllCity();
		return Response.ok(cities);
	}
	
	@GetMapping("/city/{cityId}")
	public ResponseEntity<CityDto> getCityById(@PathVariable("cityId") Long cityId) {
		CityDto city = cityService.getCityById(cityId);
		return Response.ok(city);
	}
	
	@PostMapping("/city")
	public ResponseEntity<CityDto> saveCity(@RequestBody CityDto cityDto) {
		CityDto city = cityService.saveCity(cityDto);
		return Response.ok(city);
	}
	
	@PutMapping("/city/{cityId}")
	public ResponseEntity<CityDto> updateCity(@PathVariable("cityId") Long cityId, @RequestBody CityDto cityDto) {
		CityDto city = cityService.updateCity(cityId, cityDto);
		return Response.ok(city);
	}
}

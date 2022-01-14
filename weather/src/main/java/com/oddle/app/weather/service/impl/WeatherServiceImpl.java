package com.oddle.app.weather.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.oddle.app.weather.dto.openweather.OpenWeatherDto;
import com.oddle.app.weather.service.WeatherService;

@Service
@Transactional
public class WeatherServiceImpl implements WeatherService {
	
	@Value("${open-weather.api.url}")
	private String apiUrl;
	
	@Value("${open-weather.api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public OpenWeatherDto getWeatherByCity(String name) {
		
		return null;
	}

}

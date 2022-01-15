package com.oddle.app.weather.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.oddle.app.weather.dto.openweather.OpenWeatherDto;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.service.WeatherService;

@Service
@Transactional
public class WeatherServiceImpl implements WeatherService {
	
	@Value("${open-weather.api.url}")
	private String apiUrl;
	
	@Value("${open-weather.api.key}")
	private String apiKey;

	@Override
	public OpenWeatherDto getWeatherByCity(String city) {
		if(isEmpty(city)) {
			throw new WeatherException(HttpStatus.BAD_REQUEST, "Please input the city");
		}
		OpenWeatherDto dto = new OpenWeatherDto();
		String url = concatUrl(apiUrl, apiKey, city);
		try {
			RestTemplate restTemplate = new RestTemplate();
			dto = restTemplate.getForObject(url, OpenWeatherDto.class);
		} catch(Exception e) {
			throw new WeatherException(HttpStatus.NOT_FOUND, "Data weather not found");
		}
		return dto;
	}
	
	private boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}
	
	private String concatUrl(String apiUrl, String apiKey, String param) {
		StringBuilder sb = new StringBuilder();
		sb.append(apiUrl);
		sb.append("?q=");
		sb.append(param);
		sb.append("&appid=");
		sb.append(apiKey);
		return sb.toString();
	}

}

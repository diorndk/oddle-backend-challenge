package com.oddle.app.weather.service;

import java.util.List;

import com.oddle.app.weather.dto.openweather.WeatherDto;

public interface WeatherService {
	List<WeatherDto> getAllWeather();
	WeatherDto getWeatherById(Long weatherId);
	WeatherDto saveWeather(WeatherDto weatherDto);
	WeatherDto updateWeather(Long weatherId, WeatherDto weatherDto);
	void deleteWeather(Long weatherId);
}

package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.openweather.OpenWeatherDto;

public interface WeatherService {
	OpenWeatherDto getWeatherByCity(String name);
}

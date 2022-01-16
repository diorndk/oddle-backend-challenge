package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.openweather.OpenWeatherDto;

public interface CityWeatherService {
	OpenWeatherDto getWeatherByCity(String city);
}

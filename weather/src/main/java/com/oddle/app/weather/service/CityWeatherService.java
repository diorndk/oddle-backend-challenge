package com.oddle.app.weather.service;

import java.util.List;

import com.oddle.app.weather.dto.CityWeatherDto;
import com.oddle.app.weather.dto.openweather.OpenWeatherDto;

public interface CityWeatherService {
	OpenWeatherDto getWeatherByCity(String city);
	List<OpenWeatherDto> getHistoricalWeather();
	CityWeatherDto saveWeatherData(CityWeatherDto dto);
}

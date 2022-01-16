package com.oddle.app.weather.service;

import java.util.List;

import com.oddle.app.weather.dto.CityWeatherDto;
import com.oddle.app.weather.dto.openweather.OpenWeatherDto;

public interface CityWeatherService {
	OpenWeatherDto getWeatherByCity(String city);
	List<CityWeatherDto> getWeatherFromPastDate(String fromDate, String toDate);
	CityWeatherDto getCityWeatherById(Long cityWeatherId);
	CityWeatherDto saveWeatherData(CityWeatherDto cityWeatherDto);
	CityWeatherDto updateWeatherData(Long cityWeatherId, CityWeatherDto cityWeatherDto);
	void deleteWeatherData(Long cityWeatherId);
}

package com.oddle.app.weather.service;

import java.util.List;

import com.oddle.app.weather.dto.CityDto;

public interface CityService {
	List<CityDto> getAllCity();
	CityDto getCityById(Long cityId);
	CityDto saveCity(CityDto cityDto);
	CityDto updateCity(Long cityId, CityDto cityDto);
	void deleteCity(Long cityId);
}

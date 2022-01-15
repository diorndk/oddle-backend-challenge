package com.oddle.app.weather.service;

import java.util.List;

import com.oddle.app.weather.dto.CityDto;

public interface CityService {
	public List<CityDto> getAllCity();
	public CityDto getCityById(Long cityId);
	public CityDto saveCity(CityDto cityDto);
	public CityDto updateCity(Long cityId, CityDto cityDto);
	public void deleteCity(Long cityId);
}

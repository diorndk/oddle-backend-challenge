package com.oddle.app.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oddle.app.weather.dto.CityDto;
import com.oddle.app.weather.entity.City;
import com.oddle.app.weather.exception.ResourceNotFoundException;
import com.oddle.app.weather.repository.CityRepository;
import com.oddle.app.weather.service.BaseService;
import com.oddle.app.weather.service.CityService;

@Service
@Transactional
public class CityServiceImpl extends BaseService implements CityService {
	
	private static final String RESOURCE_NAME = "City";
	private static final String FIELD_NAME = "id";
	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public List<CityDto> getAllCity() {
		List<City> cities = cityRepository.findAll();
		return convertObject(cities, CityDto.class);
	}

	@Override
	public CityDto getCityById(Long cityId) {
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> 
				new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, cityId));
		return convertObject(city, CityDto.class);
	}

	@Override
	public CityDto saveCity(CityDto cityDto) {
		City city = convertObject(cityDto, City.class);
		city = cityRepository.save(city);
		return convertObject(city, CityDto.class);
	}

	@Override
	public CityDto updateCity(Long cityId, CityDto cityDto) {
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> 
				new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, cityId));
		city.setCityName(cityDto.getCityName());
		city.setCountry(cityDto.getCountry());
		city.setLongitude(cityDto.getLongitude());
		city.setLatitude(cityDto.getLatitude());
		city = cityRepository.save(city);
		return convertObject(city, CityDto.class);
	}

	@Override
	public void deleteCity(Long cityId) {
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> 
				new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, cityId));
		cityRepository.delete(city);
	}

}

package com.oddle.app.weather.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oddle.app.weather.dto.openweather.WeatherDto;
import com.oddle.app.weather.entity.Weather;
import com.oddle.app.weather.exception.ResourceNotFoundException;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.BaseService;
import com.oddle.app.weather.service.WeatherService;

@Service
@Transactional
public class WeatherServiceImpl extends BaseService implements WeatherService {
	
	private static final String RESOURCE_NAME = "Weather";
	private static final String FIELD_NAME = "id";
	
	@Autowired
	private WeatherRepository weatherRepository;

	@Override
	public List<WeatherDto> getAllWeather() {
		List<Weather> weathers = weatherRepository.findAll();
		return convertObject(weathers, WeatherDto.class);
	}

	@Override
	public WeatherDto getWeatherById(Long weatherId) {
		Weather weather = weatherRepository.findById(weatherId)
				.orElseThrow(() -> 
				new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, weatherId));
		return convertObject(weather, WeatherDto.class);
	}

	@Override
	public WeatherDto saveWeather(WeatherDto weatherDto) {
		Weather weather = convertObject(weatherDto, Weather.class);
		weather = weatherRepository.save(weather);
		return convertObject(weather, WeatherDto.class);
	}

	@Override
	public WeatherDto updateWeather(Long weatherId, WeatherDto weatherDto) {
		Weather weather = weatherRepository.findById(weatherId)
				.orElseThrow(() -> 
				new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, weatherId));
		weather.setDescription(weatherDto.getDescription());
		weather.setMain(weatherDto.getMain());
		weather = weatherRepository.save(weather);
		return convertObject(weather, WeatherDto.class);
	}

	@Override
	public void deleteWeather(Long weatherId) {
		Weather weather = weatherRepository.findById(weatherId)
				.orElseThrow(() -> 
				new ResourceNotFoundException(RESOURCE_NAME, FIELD_NAME, weatherId));
		weatherRepository.delete(weather);
	}

}

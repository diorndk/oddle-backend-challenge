package com.oddle.app.weather.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.oddle.app.weather.dto.CityWeatherDto;
import com.oddle.app.weather.dto.openweather.OpenWeatherDto;
import com.oddle.app.weather.dto.openweather.WeatherDto;
import com.oddle.app.weather.entity.City;
import com.oddle.app.weather.entity.CityWeather;
import com.oddle.app.weather.entity.Weather;
import com.oddle.app.weather.exception.ResourceNotFoundException;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.repository.CityRepository;
import com.oddle.app.weather.repository.CityWeatherRepository;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.BaseService;
import com.oddle.app.weather.service.CityWeatherService;

@Service
@Transactional
public class CityWeatherServiceImpl extends BaseService implements CityWeatherService {
	
	private static final String CITY_RESOURCE_NAME = "City";
	private static final String WEATHER_RESOURCE_NAME = "Weather";
	private static final String FIELD_NAME = "id";

	@Value("${open-weather.api.url}")
	private String apiUrl;
	
	@Value("${open-weather.api.key}")
	private String apiKey;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private WeatherRepository weatherRepository;
	
	@Autowired
	private CityWeatherRepository cityWeatherRepository;

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
	
	@Override
	public List<OpenWeatherDto> getHistoricalWeather() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CityWeatherDto saveWeatherData(CityWeatherDto cityWeatherDto) {
		City city = cityRepository.findById(cityWeatherDto.getCityId())
				.orElseThrow(() -> 
				new ResourceNotFoundException(CITY_RESOURCE_NAME, FIELD_NAME, cityWeatherDto.getCityId()));
		List<Weather> weathers = new ArrayList<>();
		for(WeatherDto w: cityWeatherDto.getWeathers()) {
			Weather weather = weatherRepository.findById(w.getWeatherId())
					.orElseThrow(() -> 
					new ResourceNotFoundException(WEATHER_RESOURCE_NAME, FIELD_NAME, w.getWeatherId()));
			weathers.add(weather);
		}
		CityWeather cityWeather = convertObject(cityWeatherDto, CityWeather.class);
		cityWeather.setCity(city);
		cityWeather.setWeathers(weathers);
		cityWeather = cityWeatherRepository.save(cityWeather);
		CityWeatherDto dto = convertObject(cityWeather, CityWeatherDto.class);
		long epoch = convertTimeToEpoch(cityWeather.getWeatherDate());
		dto.setDt(epoch);
		return dto;
	}
	
//	@Override
//	public OpenWeatherDto saveWeatherData(OpenWeatherDto dto) {
//		City city = cityRepository.findById(dto.getCityId())
//				.orElseThrow(() -> 
//				new ResourceNotFoundException(CITY_RESOURCE_NAME, FIELD_NAME, dto.getCityId()));
//		List<Weather> weathers = new ArrayList<>();
//		dto.getWeathers()
//		.forEach(e -> {
//			Weather weather = weatherRepository.findById(e.getWeatherId())
//					.orElseThrow(() -> 
//					new ResourceNotFoundException(WEATHER_RESOURCE_NAME, FIELD_NAME, e.getWeatherId()));
//			weathers.add(weather);
//		});
//		CityWeather cityWeather = new CityWeather();
//		cityWeather.setBase(dto.getBase());
//		cityWeather.setTemperature(dto.getMain().getTemperature());
//		cityWeather.setPressure(dto.getMain().getPressure());
//		cityWeather.setHumidity(dto.getMain().getHumidity());
//		cityWeather.setCity(city);
//		cityWeather.setWeathers(weathers);
//		cityWeatherRepository.save(cityWeather);
//		return dto;
//	}
	
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

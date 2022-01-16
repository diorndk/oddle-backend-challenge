package com.oddle.app.weather.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CityWeatherServiceImpl extends BaseService implements CityWeatherService {
	
	private static final String CITY_RESOURCE_NAME = "City";
	private static final String WEATHER_RESOURCE_NAME = "Weather";
	private static final String CITY_WEATHER_RESOURCE_NAME = "City Weather";
	private static final String FIELD_NAME = "id";
	private static final long DEFAULT_PAST_DAY = 7;

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
	public CityWeatherDto getCityWeatherById(Long cityWeatherId) {
		CityWeather cityWeather = cityWeatherRepository.findById(cityWeatherId)
				.orElseThrow(() -> 
				new ResourceNotFoundException(CITY_WEATHER_RESOURCE_NAME, FIELD_NAME, cityWeatherId));
		return convertObject(cityWeather, CityWeatherDto.class);
	}
	
	@Override
	public List<CityWeatherDto> getWeatherFromPastDate(String fromDate, String toDate) {
		LocalDateTime from = convertStringToDateTime(fromDate, true);
		LocalDateTime to = convertStringToDateTime(toDate, false);
		if(from.isAfter(to)) {
			throw new WeatherException(HttpStatus.BAD_REQUEST, "'To' date must be grater than 'From' date");
		}
		List<CityWeather> cityWeathers = cityWeatherRepository.findByWeatherDateBetween(from, to);
		return convertObject(cityWeathers, CityWeatherDto.class);
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
		return convertObject(cityWeather, CityWeatherDto.class);
	}
	
	@Override
	public CityWeatherDto updateWeatherData(Long cityWeatherId, CityWeatherDto cityWeatherDto) {
		CityWeather cityWeather = cityWeatherRepository.findById(cityWeatherId)
				.orElseThrow(() -> 
				new ResourceNotFoundException(CITY_WEATHER_RESOURCE_NAME, FIELD_NAME, cityWeatherId));
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
		cityWeather.setCity(city);
		cityWeather.setWeathers(weathers);
		cityWeather.setBase(cityWeatherDto.getBase());
		cityWeather.setHumidity(cityWeatherDto.getHumidity());
		cityWeather.setPressure(cityWeatherDto.getPressure());
		cityWeather.setTemperature(cityWeatherDto.getTemperature());
		cityWeather.setTempMax(cityWeatherDto.getTempMax());
		cityWeather.setTempMin(cityWeatherDto.getTempMin());
		cityWeather.setVisibility(cityWeatherDto.getVisibility());
		return convertObject(cityWeather, CityWeatherDto.class);
	}

	@Override
	public void deleteWeatherData(Long cityWeatherId) {
		CityWeather cityWeather = cityWeatherRepository.findById(cityWeatherId)
				.orElseThrow(() -> new ResourceNotFoundException(CITY_WEATHER_RESOURCE_NAME, FIELD_NAME, cityWeatherId));
		cityWeatherRepository.delete(cityWeather);
	}
	
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
	
	private LocalDateTime convertStringToDateTime(String date, boolean isFromDate) {
		if(date.equals("") && isFromDate) {
			LocalDate fromDate = LocalDate.now();
			return fromDate.minusDays(DEFAULT_PAST_DAY).atStartOfDay();
		} else if(date.equals("") && !isFromDate) {
			return LocalDateTime.now();
		} else {
			DateTimeFormatter formatter = null;
			LocalDateTime result = null;
			try {
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				date = date.concat(" 00:00:00");
				result = LocalDateTime.parse(date, formatter);
			} catch(Exception e) {
				log.error("********** Error when parsing date **********", e);
				throw new WeatherException(HttpStatus.BAD_REQUEST, "Please input the date in 'yyyy-MM-dd' format");
			}
			return result;
		}
	}
}

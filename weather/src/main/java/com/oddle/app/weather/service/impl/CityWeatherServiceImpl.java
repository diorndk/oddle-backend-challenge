package com.oddle.app.weather.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oddle.app.weather.service.BaseService;
import com.oddle.app.weather.service.CityWeatherService;

@Service
@Transactional
public class CityWeatherServiceImpl extends BaseService implements CityWeatherService {

}

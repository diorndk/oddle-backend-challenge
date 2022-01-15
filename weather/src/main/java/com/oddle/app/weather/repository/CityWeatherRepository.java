package com.oddle.app.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oddle.app.weather.entity.CityWeather;

@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeather, Long> {

}

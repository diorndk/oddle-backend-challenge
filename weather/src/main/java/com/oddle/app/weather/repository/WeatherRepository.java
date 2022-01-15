package com.oddle.app.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oddle.app.weather.entity.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

}

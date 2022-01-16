package com.oddle.app.weather.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oddle.app.weather.entity.CityWeather;

@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeather, Long> {
	List<CityWeather> findByWeatherDateBetween(LocalDate fromDate, LocalDate toDate);
}

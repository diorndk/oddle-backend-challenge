package com.oddle.app.weather.dto.openweather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_EMPTY)
public class OpenWeatherDto {
	@JsonProperty("coord")
	private CoordinateDto coordinate;
	@JsonProperty("weather")
	private List<WeatherDto> weathers;
	private String base;
	private MainDto main;
	private int visibility;
	private WindDto wind;
	@JsonProperty("clouds")
	private CloudsDto cloud;
	private Long dt;
	@JsonProperty("sys")
	private SystemDto system;
	private Long timezone;
	@JsonProperty("id")
	private Long cityId;
	@JsonProperty("name")
	private String cityName;
	private int cod;
}

package com.oddle.app.weather.dto.openweather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_DEFAULT)
public class MainDto {
	@JsonProperty("temp")
	private double temperature;
	@JsonProperty("feels_like")
	private double feelsLike;
	@JsonProperty("temp_min")
	private double tempMin;
	@JsonProperty("temp_max")
	private double tempMax;
	private int pressure;
	private int humidity;
	@JsonProperty("sea_level")
	private int seaLevel;
	@JsonProperty("grnd_level")
	private int groundLevel;
}

package com.oddle.app.weather.dto.openweather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_EMPTY)
public class WeatherDto {
	@JsonProperty("id")
	private Long weatherId;
	private String main;
	private String description;
	private String icon;
}

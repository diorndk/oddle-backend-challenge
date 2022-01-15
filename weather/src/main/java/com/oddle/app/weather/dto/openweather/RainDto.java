package com.oddle.app.weather.dto.openweather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_DEFAULT)
public class RainDto {
	@JsonProperty("1h")
	private double volume1H;
	@JsonProperty("3h")
	private double volume3H;
}

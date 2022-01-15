package com.oddle.app.weather.dto.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoordinateDto {
	@JsonProperty("lon")
	private double longitude;
	@JsonProperty("lat")
	private double latitude;
}

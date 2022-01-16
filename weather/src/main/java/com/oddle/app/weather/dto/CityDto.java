package com.oddle.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class CityDto {
	@JsonProperty("id")
	private Long cityId;
	@JsonProperty("name")
	private String cityName;
	private String country;
	@JsonProperty("lon")
	private double longitude;
	@JsonProperty("lat")
	private double latitude;
}

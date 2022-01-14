package com.oddle.app.weather.dto.openweather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class SystemDto {
	private Long id;
	private int type;
	private String message;
	private String country;
	private Long sunrise;
	private Long sunset;
}

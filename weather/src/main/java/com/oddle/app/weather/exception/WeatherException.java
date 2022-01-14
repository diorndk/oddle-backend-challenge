package com.oddle.app.weather.exception;

import org.springframework.http.HttpStatus;

public class WeatherException extends RuntimeException {
	private static final long serialVersionUID = -4044051229286421399L;
	
	private final HttpStatus status;
	private final String message;
	
	public WeatherException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public WeatherException(HttpStatus status, String message, String otherMessage) {
		super(message);
		this.status = status;
		this.message = otherMessage;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
		
	@Override
	public String getMessage() {
		return message;
	}
}

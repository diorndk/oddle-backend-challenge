package com.oddle.app.weather.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data(staticConstructor="of")
public class Response<T> {
	private String status;
    private T data;
    
	@SuppressWarnings("unchecked")
	public static <T> ResponseEntity<T> create(T data, HttpStatus httpStatus) {
	        Response<T> output = new Response<>();
	        switch (httpStatus) {
	            case OK:
	            case ACCEPTED:
	            case NO_CONTENT:
	            case CREATED:
	                output.setStatus("success");
	                break;
	            case BAD_REQUEST:
	            case FORBIDDEN:
	            case METHOD_NOT_ALLOWED:
	            case NOT_FOUND:
	            case UNAUTHORIZED:
	            default:
	                output.setStatus("error");
	        }
	        output.setData(data);

	        return (ResponseEntity<T>) ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(output);
	    }

	    public static <T> ResponseEntity<T> create(T data, int status) {
	        return create(data, HttpStatus.valueOf(status));
	    }
	    public static <T> ResponseEntity<T> notFound(T data) {
	        return create(data, HttpStatus.NOT_FOUND);
	    }
	    public static <T> ResponseEntity<T> badRequest(T data) {
	        return create(data, HttpStatus.BAD_REQUEST);
	    }

	    public static <T> ResponseEntity<T> ok(T data) {
	        return create(data, HttpStatus.OK);
	    }
}

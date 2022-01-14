package com.oddle.app.weather.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
	private String status;
    private Object data;
    
	 public static ResponseEntity<Response> create(Object data, HttpStatus httpStatus) {
	        Response output = new Response();
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

	        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(output);
	    }

	    public static ResponseEntity<Response> create(Object data, int status) {
	        return create(data, HttpStatus.valueOf(status));
	    }
	    public static ResponseEntity<Response> notFound(Object data) {
	        return create(data, HttpStatus.NOT_FOUND);
	    }
	    public static ResponseEntity<Response> badRequest(Object data) {
	        return create(data, HttpStatus.BAD_REQUEST);
	    }

	    public static ResponseEntity<Response> ok(Object data) {
	        return create(data, HttpStatus.OK);
	    }
}

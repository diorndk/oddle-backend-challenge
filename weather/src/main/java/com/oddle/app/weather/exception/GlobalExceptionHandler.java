package com.oddle.app.weather.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oddle.app.weather.utility.Response;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        return Response.notFound(exception.getMessage());
    }

    @ExceptionHandler(WeatherException.class)
    public ResponseEntity<String> handleAccountsException(WeatherException exception,
                                                                        WebRequest webRequest){
        return Response.create(exception.getMessage(), exception.getStatus());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception exception,
                                                               WebRequest webRequest){
    	logger.error("********** Runtime Exception **********", exception);
        return Response.create(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return Response.badRequest(errors);
    }
}

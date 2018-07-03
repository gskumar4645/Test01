package com.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomExceptions extends RuntimeException{
	public CustomExceptions(String message){
		super(message);
	}
}

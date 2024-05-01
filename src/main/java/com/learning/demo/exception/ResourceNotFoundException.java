package com.learning.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends AppException {

	private static final long serialVersionUID = 6163954829128056766L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}

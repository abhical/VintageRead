package com.example.ElectronicStore.Exception;

import java.util.*;
import java.util.logging.Logger;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ElectronicStore.Dtos.ApiResponseMessage;

import lombok.Builder;

@Builder
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		logger.info("Exceptional Handing via Global Exceptional Handler is invoked");
		ApiResponseMessage response = ApiResponseMessage.builder().message(ex.getMessage()).success(true)
				.status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
	
	// MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handlerMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		HashMap<String, Object> response = new HashMap<>();
		allErrors.stream().forEach(ObjectError -> {
			String message = ObjectError.getDefaultMessage();
			String field = ((FieldError) ObjectError).getField();
			response.put(field, message);

		});
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}

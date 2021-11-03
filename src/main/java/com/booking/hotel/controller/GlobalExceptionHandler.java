package com.booking.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.booking.hotel.controller.response.ErrorResponse;
import com.booking.hotel.exception.BusinessException;
import com.booking.hotel.service.Translator;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private Translator translator;

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleAccessDeniedException(BusinessException ex) {
		final String errorCode = ex.getErrorCode();
		final String errorText = translator.toLocale(errorCode, ex.getArgs());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorCode, errorText), HttpStatus.BAD_REQUEST);
	}

}

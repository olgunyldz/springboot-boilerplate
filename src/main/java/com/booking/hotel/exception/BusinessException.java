package com.booking.hotel.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String errorCode;
	private final Object[] args;
	public BusinessException(Throwable ex, String errorCode, Object ... args) {
		super(ex);
		this.errorCode = errorCode;
		this.args = args;
		
	}
	public BusinessException(String errorCode, Object ... args) {
		this.errorCode = errorCode;
		this.args = args;
	}
}

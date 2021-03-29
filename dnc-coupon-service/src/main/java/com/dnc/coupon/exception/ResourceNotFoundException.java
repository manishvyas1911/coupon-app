package com.dnc.coupon.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * Custom Exception : Product not found
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message,Throwable throwable) {
		super(message,throwable);
	}
}

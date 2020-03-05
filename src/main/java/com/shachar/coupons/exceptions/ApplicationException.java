package com.shachar.coupons.exceptions;

import com.shachar.coupons.enums.ErrorTypes;

public class ApplicationException extends Exception {

	private   ErrorTypes errorTypes;
	private String message;
	private Exception e;
	
	
	public ApplicationException(ErrorTypes errorTypes, String message) {
		super(message);
		this.errorTypes = errorTypes;
		this.message = message;
	}


	public ApplicationException(ErrorTypes errorTypes, String message, Exception e) {
		super(message);
		this.errorTypes = errorTypes;
		this.message = message;
		this.e = e;
	}


	public  ErrorTypes getErrorTypes() {
		return errorTypes;
	}




	
	
	
}

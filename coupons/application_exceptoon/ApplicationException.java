package com.shachar.coupons.application_exceptoon;



public class ApplicationException extends Exception {

	private ErrorTypes errorTypes;
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
	
	
	
}

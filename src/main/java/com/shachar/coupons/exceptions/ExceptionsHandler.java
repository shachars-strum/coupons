package com.shachar.coupons.exceptions;


import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shachar.coupons.databeans.ErrorBean;
import com.shachar.coupons.enums.ErrorTypes;


// Exception handler class
@RestControllerAdvice
public class ExceptionsHandler {

	//	Response - Object in Spring
	@ExceptionHandler
	@ResponseBody
	// Variable name is throwable in order to remember that it handles Exception and Error
	public ErrorBean toResponse(Throwable throwable, HttpServletResponse response) {
		
		//	ErrorBean errorBean;
		if(throwable instanceof ApplicationException) {
			response.setStatus(650);
			ApplicationException appException = (ApplicationException) throwable;

			ErrorTypes errorType = appException.getErrorTypes(); 
			int errorNumber = errorType.getErrorNumber();
			String errorMessage = errorType.getErrorMessage();

			ErrorBean errorBean = new ErrorBean(errorNumber, errorMessage); 
			if(appException.getErrorTypes().isShowStackTrace()) {
				appException.printStackTrace();
			}

			return errorBean;
		}
		
		response.setStatus(600);

		String errorMessage = throwable.getMessage();
		ErrorBean errorBean = new ErrorBean(601, errorMessage);
		throwable.printStackTrace();

		return errorBean;
	}

}



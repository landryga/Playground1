package com.vetClinic.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	private Log logger = LogFactory.getLog(ExceptionController.class);
	
	@ExceptionHandler(value=Exception.class)
	public String handlerException(HttpServletRequest request, Exception ex) {
		logger.error("request" + request.getRequestURL() + "threw an exception", ex);
		
		return "error";
	}
}

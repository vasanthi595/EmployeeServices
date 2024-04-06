package com.msf.employee.exception;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Log4j2
@RestControllerAdvice
public class EmployeeControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CustomExceptionResponse handleException(final Exception e, final HttpServletRequest request) {
		log.error("Exception : ", e);
		return new CustomExceptionResponse(e.getMessage(),500, request.getRequestURI());
	}
	
	@ExceptionHandler({EmployeeGenericException.class })
	@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
	public @ResponseBody CustomExceptionResponse handleWrapperGenericException(EmployeeGenericException e, final HttpServletRequest request) {
		log.error("RenewalGeneric Exception : ", e);
		return new CustomExceptionResponse(e.getInfoMessage(),e.getInfoId(),request.getRequestURI());
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public  @ResponseBody CustomExceptionResponse handleUnexpectedTypeException(UnexpectedTypeException ex, final HttpServletRequest request) {
		String errorMessage = "Validation failed due to unexpected type: " + ex.getMessage();
		return new CustomExceptionResponse(errorMessage,400, request.getRequestURI());
	}

}
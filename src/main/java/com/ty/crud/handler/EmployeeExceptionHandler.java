package com.ty.crud.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.crud.pojo.EmployeeErrorMessage;

@RestControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String VALIDATION_ERROR = "validationError";

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public EmployeeErrorMessage employeeException(Exception exception) {
		return EmployeeErrorMessage.builder().isError(true).message(exception.getMessage()).build();
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public EmployeeErrorMessage requestHeaderException(MissingRequestHeaderException exception) {
		return EmployeeErrorMessage.builder().isError(true).message(exception.getMessage()).build();
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> validationError = new HashMap<>();
		List<String> errorList = exception.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		validationError.put(VALIDATION_ERROR, errorList);
		return ResponseEntity.badRequest().body(validationError);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> validationError = new HashMap<>();
		String errMsg = exception.getMessage();
		if (errMsg != null) {
			errMsg = errMsg.split(":")[0];
		}
		validationError.put(VALIDATION_ERROR, errMsg);
		return ResponseEntity.badRequest().body(validationError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		Map<String, String> validationError = new HashMap<>();
		validationError.put(VALIDATION_ERROR, exception.getMessage());
		return ResponseEntity.badRequest().body(validationError);
	}

}

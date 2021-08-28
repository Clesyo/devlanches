package com.app.devlanches.api.exception.handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.exception.EntityNotExist;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(EntityNotExist.class)
	public ResponseEntity<Object> handleEntityNotExist(EntityNotExist ex, WebRequest web) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return handleExceptionInternal(ex, getProblem(ex, status), new HttpHeaders(), status, web);
	}
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest web) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return handleExceptionInternal(ex, getProblem(ex, status), new HttpHeaders(), status, web);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<Field> fields = new ArrayList<Field>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String field = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			fields.add(new Field(field, message));
		}

		ProblemException problem = new ProblemException();
		problem.setStatus(status.value());
		problem.setMessage("Um ou mais campos estão inválidos." + "Preencha corretamente.");
		problem.setDateTime(OffsetDateTime.now());
		problem.setFields(fields);

		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	private ProblemException getProblem(Exception ex, HttpStatus status) {
		// TODO Auto-generated method stub
		ProblemException problem = new ProblemException();

		problem.setStatus(status.value());
		problem.setMessage(ex.getMessage());
		problem.setDateTime(OffsetDateTime.now());
		return problem;
	}
}

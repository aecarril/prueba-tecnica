package com.pichincha.accounts.infrastructure.middleware;

import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.utils.ErrorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionMiddleware extends ResponseEntityExceptionHandler {
    private static final Logger logger_ = LoggerFactory.getLogger(ExceptionMiddleware.class);
    public static final String MESSAGE = "!!!!!Exception: {}";

    @ExceptionHandler(AccountsDomainException.class)
    public final ResponseEntity<Object> handleAccountsDomainException(AccountsDomainException ex, WebRequest request) {
        logger_.warn(MESSAGE, ex.getMessage());

        return handleExceptionInternal(ex,
                ErrorUtils.buildErrorObject("Accounts error occurred", ex),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger_.warn(MESSAGE, ex.getMessage());

        return handleExceptionInternal(ex,
                ErrorUtils.buildErrorObject("Validation failed", ex),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        logger_.error(MESSAGE, ex);

        return handleExceptionInternal(ex,
                ErrorUtils.buildErrorObject(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

        logger_.info(ex.getClass().getName());
        logger_.warn(MESSAGE, ex.getMessage());

        return handleExceptionInternal(ex,
                ErrorUtils.buildErrorObject("Validation failed", ex),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }
}
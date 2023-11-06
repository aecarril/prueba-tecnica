package com.pichincha.clients.domain.utils;

import org.springframework.web.bind.MethodArgumentNotValidException;
import com.pichincha.clients.domain.error.Error;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ErrorUtils {

    private ErrorUtils() {

        throw new IllegalStateException("ErrorUtils class");
    }

    public static Error buildErrorObject(final String title, MethodArgumentNotValidException ex) {

        var response = getError(title);

        List<String> errors = new ArrayList<>();
        var bindingResult = ex.getBindingResult();

        bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        response.setErrors(errors);

        return response;
    }

    public static Error buildErrorObject(final String title, Exception ex) {

        var errorResponse = getError(title);

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        errorResponse.setErrors(errors);

        return errorResponse;
    }

    public static Error buildErrorObject() {

        var errorResponse = getError("Unknown error occurred");

        List<String> errors = new ArrayList<>();
        errors.add("Por favor, póngase en contacto con el Administrador del Sistema");
        errorResponse.setErrors(errors);

        return errorResponse;
    }

    public static Error buildErrorObject(String title, String error) {

        var errorResponse = getError(title);

        List<String> errors = new ArrayList<>();
        errors.add(error);
        errorResponse.setErrors(errors);

        return errorResponse;
    }

    public static Error buildErrorObject(final String title, ConstraintViolationException ex) {

        var errorResponse = getError(title);

        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }

        errorResponse.setErrors(errors);

        return errorResponse;
    }

    private static Error getError(final String title) {

        var errorResponse = new Error();

        errorResponse.setMessage(title);

        return errorResponse;
    }
}

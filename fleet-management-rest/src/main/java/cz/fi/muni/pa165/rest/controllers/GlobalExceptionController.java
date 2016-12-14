package cz.fi.muni.pa165.rest.controllers;

import cz.fi.muni.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.rest.exceptions.ResourceNotModifiedException;
import cz.fi.muni.pa165.rest.tools.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

/**
 * @author Richard Trebichavský
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ApiError handleException(ResourceAlreadyExistingException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrors(Arrays.asList("The requested resource already exists"));
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ApiError handleException(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrors(Arrays.asList("The requested resource was not found"));
        return apiError;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ResponseBody
    ApiError handleException(ResourceNotModifiedException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrors(Arrays.asList("The requested resource was not modified"));
        return apiError;
    }

}

package org.example.birthdaybook.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleValidationException(RuntimeException exc) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ValidationError validationException = new ValidationError(
                exc.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(validationException, badRequest);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllException(Exception exc) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ValidationError validationException = new ValidationError(
                exc.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(validationException, badRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        List<String> messages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ValidationError validationException = new ValidationError(
                ex.getMessage() + messages,
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(validationException, badRequest);
    }
}

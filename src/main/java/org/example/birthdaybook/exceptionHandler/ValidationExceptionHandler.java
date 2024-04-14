package org.example.birthdaybook.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleValidationException(RuntimeException exc) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ValidationException validationException = new ValidationException(
                exc.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(validationException, badRequest);
    }
}

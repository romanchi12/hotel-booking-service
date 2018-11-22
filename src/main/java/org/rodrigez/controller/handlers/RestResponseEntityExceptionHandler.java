package org.rodrigez.controller.handlers;

import org.rodrigez.service.exceptions.DateIntervalException;
import org.rodrigez.service.exceptions.NotAvailableRoomException;
import org.rodrigez.service.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DateIntervalException.class, NotAvailableRoomException.class})
    public final ResponseEntity<ErrorDetails> handleBadRequestsExceptions(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public final ResponseEntity<ErrorDetails> handleNotFoundExceptions(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
package com.itb.wave8.diploma.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class ObtenerDiplomaExceptionController {

    ErrorDTO error;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Errores al momento de validar: ");
        for(ObjectError errores: e.getBindingResult().getAllErrors()){
            sb.append(" | ").append(errores.getDefaultMessage());
        }
        error = new ErrorDTO(new Timestamp(System.currentTimeMillis()) , "[Exception Response] - MethodArgumentNotValidException: " + sb.toString(), HttpStatus.BAD_REQUEST.value(), "Error");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> userNotFoundException(UserNotFoundException e) {
        error = new ErrorDTO(new Timestamp(System.currentTimeMillis()) , "[Exception Response] - UserNotFoundException: " + e.getMessage(), HttpStatus.NOT_FOUND.value(), "Error");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<ErrorDTO> userNotActiveException(UserNotActiveException e) {
        error = new ErrorDTO(new Timestamp(System.currentTimeMillis()) , "[Exception Response] - UserNotFoundException: " + e.getMessage(), HttpStatus.UNAUTHORIZED.value(), "Error");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}

package com.example.demo.controladores;

import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Exceptions.RequestException;
import com.example.demo.models.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdivice {

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDTO> businessExceptionHandler(NotFoundException ex){
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

}

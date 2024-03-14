package com.study.library.controller.advice;


import com.study.library.exception.SaveException;
import com.study.library.exception.ValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    // @ExceptionHandler : 어디서든 SaveException.class 예외가 터지면 낚아첸다
    @ExceptionHandler(SaveException.class)
    public ResponseEntity<?> saveExcpetion(SaveException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }


    @ExceptionHandler(ValidException.class)
    public ResponseEntity<?> validException(ValidException e) {
        return ResponseEntity.badRequest().body(e.getErrorMap());
    }
}

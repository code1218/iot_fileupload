package com.korit.fileupload_back.controller;

import com.korit.fileupload_back.exception.MemberInsertException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MemberInsertException.class)
    public ResponseEntity<?> memberInsertException(MemberInsertException e) {
        return ResponseEntity
                .status((HttpStatus) e.getErrorMap().get("httpStatus"))
                .body(e.getErrorMap().get("message"));
    }

}

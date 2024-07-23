package com.handihub.auth_service.exceptions;

import com.handihub.auth_service.dtos.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionController extends RuntimeException {

    @ExceptionHandler(value = EmailNotValid.class)
    public ResponseHandler emailNotValidHandler(EmailNotValid emailNotValid){
        return ResponseHandler.builder()
                .statusCode(400)
                .status(HttpStatus.BAD_REQUEST)
                .message(emailNotValid.getMessage())
                .build();
    }
}

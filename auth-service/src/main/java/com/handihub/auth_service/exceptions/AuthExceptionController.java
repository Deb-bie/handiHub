package com.handihub.auth_service.exceptions;

import com.handihub.auth_service.dtos.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionController {
//    TODO: add not null to the exception handler


    @ExceptionHandler(value = NotValid.class)
    public ResponseEntity<ResponseHandler> notValidHandler(NotValid notValid){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseHandler.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .message(notValid.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ResponseHandler> alreadyExistsExceptionHandler(AlreadyExistsException alreadyExistsException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseHandler.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .status(HttpStatus.BAD_REQUEST)
                        .message(alreadyExistsException.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseHandler> notFoundExceptionHandler (NotFoundException notFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseHandler.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(notFoundException.getMessage())
                        .build()
                );
    }
}

package com.handihub.auth_service.controllers;

import com.handihub.auth_service.dtos.ResponseHandler;
import com.handihub.auth_service.dtos.SignUpRequest;
import com.handihub.auth_service.exceptions.AlreadyExistsException;
import com.handihub.auth_service.exceptions.EmailNotValid;
import com.handihub.auth_service.exceptions.NotNull;
import com.handihub.auth_service.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseHandler signUpUsingEmail(@RequestBody SignUpRequest signUpRequest) throws AlreadyExistsException, NotNull, EmailNotValid {
        return authService.signUpUsingEmail(signUpRequest);
    }

    public void signIn(){}

    public void signOut(){}
}

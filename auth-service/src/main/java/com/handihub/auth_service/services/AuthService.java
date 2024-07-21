package com.handihub.auth_service.services;

import com.handihub.auth_service.dtos.ResponseHandler;
import com.handihub.auth_service.dtos.SignUpRequest;
import com.handihub.auth_service.entities.User;
import com.handihub.auth_service.exceptions.AlreadyExistsException;
import com.handihub.auth_service.exceptions.NotNull;
import com.handihub.auth_service.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    public ResponseHandler signUpUsingEmail(SignUpRequest signUpRequest) throws AlreadyExistsException, NotNull {
        if(authRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistsException("This email address is already in use");
        } else {
            if (signUpRequest.getEmail() != null &&
                    signUpRequest.getUsername() != null &&
                    signUpRequest.getPassword() != null) {
                User newUser = User.builder()
                        .email(signUpRequest.getEmail())
                        .username(signUpRequest.getUsername())
                        .password(signUpRequest.getPassword())
                        .build();
                authRepository.save(newUser);

            } else {
                throw new NotNull("The email, username and password cannot be null");
            }
        }
        return ResponseHandler.builder()
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .message("successful")
                .build();
    }
}

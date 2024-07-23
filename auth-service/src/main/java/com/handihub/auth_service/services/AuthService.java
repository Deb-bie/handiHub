package com.handihub.auth_service.services;

import com.handihub.auth_service.dtos.ResponseHandler;
import com.handihub.auth_service.dtos.SignUpRequest;
import com.handihub.auth_service.entities.User;
import com.handihub.auth_service.exceptions.AlreadyExistsException;
import com.handihub.auth_service.exceptions.EmailNotValid;
import com.handihub.auth_service.exceptions.NotNull;
import com.handihub.auth_service.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
//    TODO: Test the service
//    TODO: check if the email already exists
//    TODO: check to see if an email has an @ and a dot at the right places, if not then that's not an email
//    TODO: check the password length, it should be more than 6 characters and throw the appropriate exceptions where needed


    @Autowired
    private AuthRepository authRepository;

    public ResponseHandler signUpUsingEmail(SignUpRequest signUpRequest) throws AlreadyExistsException, NotNull, EmailNotValid {
        if(authRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistsException("This email address is already in use");
        } else {
            if (signUpRequest.getEmail() != null &&
                    signUpRequest.getUsername() != null &&
                    signUpRequest.getPassword() != null) {
                if (validateEmail(signUpRequest.getEmail())){
                    User newUser = User.builder()
                            .email(signUpRequest.getEmail())
                            .username(signUpRequest.getUsername())
                            .password(signUpRequest.getPassword())
                            .build();
                    authRepository.save(newUser);
                } else {
                    throw new EmailNotValid("Email is not valid!");
                }
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


    public boolean validateEmail(String email){
        int indexOfAt;
        int indexOfDot;
        if(email.contains("@")){
            indexOfAt=email.indexOf("@");
            if (email.contains(".")) {
                indexOfDot=email.indexOf(".");
                if (indexOfDot > indexOfAt && indexOfDot != (indexOfAt+1)){
                    return indexOfDot != email.length() - 1 && email.length() - 1 > indexOfDot;
                }
            }
        }
        return false;
    }
}

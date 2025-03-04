package com.handihub.auth_service.services;

import com.handihub.auth_service.dtos.ResponseHandler;
import com.handihub.auth_service.dtos.SignUpRequest;
import com.handihub.auth_service.entities.User;
import com.handihub.auth_service.exceptions.AlreadyExistsException;
import com.handihub.auth_service.exceptions.NotValid;
import com.handihub.auth_service.exceptions.NotNull;
import com.handihub.auth_service.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
//    TODO: Test the service
//    TODO: hash password so that the actual password isn't saved to the db but its hash
//    TODO: add jwt configurations
//    TODO: implement login
//    TODO: implement logout
//    TODO: implement forgot password
//    TODO: implement registering / signing in using google
//    TODO: implement registering / signing in using microsoft
//    TODO: implement registering / signing in using facebook


    @Autowired
    private AuthRepository authRepository;

    public ResponseEntity<ResponseHandler> signUpUsingEmail(SignUpRequest signUpRequest) throws AlreadyExistsException, NotNull, NotValid {
        if(authRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistsException("This email address is already in use");
        } else {
            if (signUpRequest.getEmail() != null &&
                    signUpRequest.getUsername() != null &&
                    signUpRequest.getPassword() != null) {
                if (validateEmail(signUpRequest.getEmail())){
                    if(validatePassword(signUpRequest.getPassword())){
                        User newUser = User.builder()
                                .email(signUpRequest.getEmail())
                                .username(signUpRequest.getUsername())
                                .password(signUpRequest.getPassword())
                                .build();
                        authRepository.save(newUser);
                    } else {
                        throw new NotValid("The password must be more than 6 characters");
                    }
                } else {
                    throw new NotValid("Email is not valid!");
                }
            } else {
                throw new NotNull("The email, username and password cannot be null");
            }
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseHandler.builder()
                        .statusCode(201)
                        .status(HttpStatus.CREATED)
                        .message("successful")
                        .build()
                );
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

    public boolean validatePassword (String password) {
        return password.length() > 6;
    }


    public void hashPassword() {

    }
}

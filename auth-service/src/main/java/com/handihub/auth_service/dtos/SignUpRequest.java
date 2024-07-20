package com.handihub.auth_service.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignUpRequest {
    private String email;
    private String username;
    private String password;
}

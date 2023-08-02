package com.example.springplusassignment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignupRequestDto {

    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
}

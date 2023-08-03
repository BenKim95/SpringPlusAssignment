package com.example.springplusassignment.controller;

import com.example.springplusassignment.dto.ApiResponseDto;
import com.example.springplusassignment.dto.LoginRequestDto;
import com.example.springplusassignment.dto.SignupRequestDto;
import com.example.springplusassignment.jwt.JwtUtil;
import com.example.springplusassignment.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup")
    public ResponseEntity <ApiResponseDto> signup (@Valid @RequestBody SignupRequestDto signupRequestDto) {
         return userService.signup(signupRequestDto);
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity <ApiResponseDto> login (@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        return userService.login(loginRequestDto, httpServletResponse);
    }
}

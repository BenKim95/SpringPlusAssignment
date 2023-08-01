package com.example.springplusassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean // bean 수동 등록
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder(); // SpringSecurity 제공하는 암호화 매서드
    }
}

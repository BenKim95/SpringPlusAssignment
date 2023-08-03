package com.example.springplusassignment.service;

import com.example.springplusassignment.dto.ApiResponseDto;
import com.example.springplusassignment.dto.LoginRequestDto;
import com.example.springplusassignment.dto.SignupRequestDto;
import com.example.springplusassignment.entity.User;
import com.example.springplusassignment.jwt.JwtUtil;
import com.example.springplusassignment.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //회원가입 Api
    @Transactional
    public ResponseEntity<ApiResponseDto> signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String passwordConfirm = signupRequestDto.getPasswordConfirm();
        String email = signupRequestDto.getEmail();

        if (!Pattern.matches("^[a-zA-Z0-9]+", username)) { // User엔티티에서 정규식 걸어도 원하는 Response가 불가
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "닉네임은 문자와 숫자만 가능합니다."));
        } else if (!Pattern.matches("^[a-zA-Z0-9]{4,12}$", password)) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "비밀번호는 4자리이상 10자리 이하의 문자여야 합니다."));
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "이미 사용중인 닉네임입니다."));
        } else if (!Objects.equals(passwordConfirm, password)) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "비밀번호와 비밀번호 확인이 일치해야 합니다."));
        } else if (password.contains(username)) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "아이디가 들어간 비밀번호는 사용할 수 없습니다."));
        }

        userRepository.save(new User(username, passwordEncoder.encode(password), email));
        return ResponseEntity.status(202).body(new ApiResponseDto(HttpStatus.ACCEPTED, "회원가입이 완료되었습니다."));
    }

    // 로그인 API
    @Transactional
    public ResponseEntity<ApiResponseDto> login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "해당 유저 닉네임은 존재하지 않습니다."));
        } else if (!passwordEncoder.matches(password, user.get().getPassword())) {
            return ResponseEntity.status(400).body(new ApiResponseDto(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."));
        }

//        httpServletResponse.addHeader(com.sparta.springauth.jwt.JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get().getUsername()));
//        jwtUtil.addJwtToCookie(jwtUtil.createToken(user.get().getUsername()), httpServletResponse);

        String token = jwtUtil.createToken(user.get().getUsername());
        jwtUtil.addJwtToCookie(token, httpServletResponse);

        return ResponseEntity.status(202).body(new ApiResponseDto(HttpStatus.ACCEPTED, "로그인 되었습니다."));
    }
}

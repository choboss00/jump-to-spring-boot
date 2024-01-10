package com.example.sbb.user;

import com.example.sbb.config.utils.ApiResponseBuilder;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserRequest.SignupDTO signupDTO, Errors errors) throws IllegalAccessException {
        userService.signup(signupDTO); // TO-DO : 회원가입 Service 로직 생성
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseBuilder.successWithNoContent());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO loginDTO, Errors errors, HttpSession session) {
        userService.login(loginDTO); // TO-DO : 로그인 Service 로직 생성

        // 로그인 성공 후 세션에 사용자 정보 저장
        session.setAttribute("USER_EMAIL", loginDTO.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseBuilder.successWithNoContent());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute("USER_EMAIL"); // 세션에서 사용자 정보 제거
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseBuilder.successWithNoContent());
    }

}

package com.example.sbb.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid UserRequest.UserCreateDTO userCreateDTO, Error errors) {
        userService.signup(userCreateDTO); // TO-DO : 회원가입 Service 로직 생성
        return "redirect:/";
    }

}

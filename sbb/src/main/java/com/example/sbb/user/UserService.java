package com.example.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserJPARepository userJPARepository;

    @Transactional
    public void signup(UserRequest.SignupDTO signupDTO) throws IllegalAccessException {
        // 회원가입 로직 구현 : 동일한 이메일이 존재하는지 검증 후 비밀번호 암호화 후 저장

        // 암호화
        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        // 저장
        userJPARepository.save(signupDTO.toEntity());

    }


    @Transactional
    public void login(UserRequest.LoginDTO loginDTO) {
        // 로그인 로직 구현 : 동일한 이메일이 존재하는지 검증 후 비밀번호 일치 여부 검증
        User user = userJPARepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));

        // 비밀번호 일치 여부 검증
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }
}

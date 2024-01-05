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
    private final UserRepository userRepository;

    @Transactional
    public void signup(UserRequest.UserCreateDTO userCreateDTO) {
        // 회원가입 로직 구현 : 동일한 이메일이 존재하는지 검증 후 비밀번호 암호화 후 저장

        // 검증
        validateUserEmail(userCreateDTO.getEmail());

        // 암호화
        userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        // 저장
        userRepository.save(userCreateDTO.toEntity());


    }

    private void validateUserEmail(String email) {
        User user = userRepository.findByEmail(email);

        if ( !user.isNull() ) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

    }
}

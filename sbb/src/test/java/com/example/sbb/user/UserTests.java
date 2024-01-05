package com.example.sbb.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 로직 테스트")
    void CreateUserTests() {
        // given
        UserRequest.UserCreateDTO userCreateDTO = new UserRequest.UserCreateDTO();

        userCreateDTO.setUsername("test");
        userCreateDTO.setEmail("test@example.com");
        userCreateDTO.setPassword("test1234");
        userCreateDTO.setNickname("testst");
        userCreateDTO.setRole(Role.USER);
        // when
        userService.signup(userCreateDTO);

        // then
        User user = userRepository.findByEmail("test@example.com");

        assertEquals(user.getName(), "test");

    }

}

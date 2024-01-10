package com.example.sbb.user;

import com.example.sbb.RestDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class UserTests extends RestDoc {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private ObjectMapper om;

    @Test
    @DisplayName("회원가입 로직 테스트")
    void CreateUserTests() throws Exception {
        // given
        UserRequest.SignupDTO signupDTO = new UserRequest.SignupDTO();

        signupDTO.setUsername("test");
        signupDTO.setEmail("test@example.com");
        signupDTO.setPassword("test1234");
        signupDTO.setNickname("testst");
        signupDTO.setRole(Role.USER);

        // when
        ResultActions resultActions = mvc.perform(post("/api/user/signup")
                .contentType("application/json")
                .content(om.writeValueAsString(signupDTO)));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        resultActions.andExpect(jsonPath("$.status").value("success"));

    }

    @Test
    @DisplayName("로그인 테스트")
    void UserLoginTests() throws Exception {
        // given
        CreateUserTests();

        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO("test@example.com", "test1234");

        // when

        // then
    }

}

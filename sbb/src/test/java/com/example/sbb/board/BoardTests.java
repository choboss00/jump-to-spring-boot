package com.example.sbb.board;

import com.example.sbb.Board.Board;
import com.example.sbb.Board.BoardJPARepository;
import com.example.sbb.Board.BoardRequest;
import com.example.sbb.Board.BoardService;
import com.example.sbb.RestDoc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Sql("classpath:db/teardown.sql")
public class BoardTests extends RestDoc {

    @Autowired
    private ObjectMapper om;

    @MockBean
    private BoardService boardService;

    @Test
    @WithMockUser
    @DisplayName("게시글 작성 테스트")
    void boardPostTest() throws Exception {
        // given
        BoardRequest.PostDTO postDTO = new BoardRequest.PostDTO();
        postDTO.setTitle("제목");
        postDTO.setContent("내용");

        MockHttpSession session = new MockHttpSession();

        when(boardService.postBoard(any(BoardRequest.PostDTO.class), any(HttpSession.class)))
                .thenReturn(22);

        // when
        ResultActions resultActions = mvc.perform(post("/api/boards")
                .contentType("application/json")
                .session(session)
                .content(om.writeValueAsString(postDTO)));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        resultActions.andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    @DisplayName("게시글 조회 테스트")
    void BoardTests() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/api/boards"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : "+responseBody);

        // then
        resultActions.andExpect(jsonPath("$.status").value("success"));
    }
}

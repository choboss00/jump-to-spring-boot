package com.example.sbb.board;

import com.example.sbb.Board.Board;
import com.example.sbb.Board.BoardJPARepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTests {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Test
    @DisplayName("JPA 동작 테스트")
    void testJpa() {
        // given
        Board board = Board.builder()
                .title("제목")
                .content("내용")
                .build();
        // when
        boardJPARepository.save(board);

        // then
        Board b1 = boardJPARepository.findById(1).orElseThrow(RuntimeException::new);

        assertThat(b1.getTitle()).isEqualTo("제목");
    }

    @Test
    @DisplayName("JPA 삭제 테스트")
    void testDeleteJpa() {
        // given
        Board board = Board.builder()
                .title("제목")
                .content("내용")
                .build();
        // when
        boardJPARepository.save(board);

        // then
        boardJPARepository.delete(board);
    }
}

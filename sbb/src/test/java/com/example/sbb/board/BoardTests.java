package com.example.sbb.board;

import com.example.sbb.Board.Board;
import com.example.sbb.Board.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("JPA 동작 테스트")
    void testJpa() {
        // given
        Board board = Board.builder()
                .title("제목")
                .content("내용")
                .build();
        // when
        boardRepository.save(board);

        // then
        Board b1 = boardRepository.findById(1).orElseThrow(RuntimeException::new);

        assertThat(b1.getTitle()).isEqualTo("제목");
    }
}

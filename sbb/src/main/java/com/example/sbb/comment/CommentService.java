package com.example.sbb.comment;

import com.example.sbb.Board.Board;
import com.example.sbb.Board.BoardJPARepository;
import com.example.sbb.user.User;
import com.example.sbb.user.UserJPARepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentJPARepository commentJPARepository;
    private final UserJPARepository userJPARepository;
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public void createComment(CommentRequest.CommentDTO commentDTO, HttpSession session) {
        // TO-DO : 댓글 등록 Service 로직 생성
        /**
         * 1. 인증
         * 2. 댓글 데이터베이스에 저장
         * **/

        // 인증
        String email = (String)session.getAttribute("USER_EMAIL");
        if (email == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        // 댓글 데이터베이스에 저장
        User user = userJPARepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("로그인이 필요합니다."));
        Board board = boardJPARepository.findById(commentDTO.getBoardId())
                .orElseThrow(() -> new IllegalStateException("해당 게시글이 존재하지 않습니다."));

        Comment comment = new Comment(user, board, commentDTO.getContent());
        commentJPARepository.save(comment);

    }
}

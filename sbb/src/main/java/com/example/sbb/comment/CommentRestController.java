package com.example.sbb.comment;

import com.example.sbb.config.utils.ApiResponseBuilder;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest.CommentDTO commentDTO, HttpSession session) {
        // TO-DO : 댓글 등록 Service 로직 생성
        commentService.createComment(commentDTO, session);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseBuilder.successWithNoContent());
    }

}

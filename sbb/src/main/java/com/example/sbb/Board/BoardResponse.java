package com.example.sbb.Board;

import com.example.sbb.comment.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardResponse {

    @Getter
    @Setter
    public static class BoardListDTO {
        private int id;
        private String title;
        private String writer;
        private LocalDateTime createdAt;

        public BoardListDTO(int id, String title, String writer, LocalDateTime createdAt) {
            this.id = id;
            this.title = title;
            this.writer = writer;
            this.createdAt = createdAt;
        }
    }

    @Getter
    @Setter
    public static class BoardDetailDTO {
        private int id;
        private String title;
        private String content;
        private String writer;
        private LocalDateTime createdAt;
        private List<CommentReplyDTO> comments;

        public BoardDetailDTO(Board board, List<Comment> comments) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.writer = board.getUser().getNickname();
            this.createdAt = board.getCreatedAt();
            this.comments = comments.stream()
                    .map(comment -> new CommentReplyDTO(
                            comment.getUser().getNickname(),
                            comment.getContent(),
                            comment.getCreatedAt()
                    )).toList();
        }

        @Getter
        @Setter
        public static class CommentReplyDTO {
            private String writer;
            private String content;
            private LocalDateTime createdAt;

            public CommentReplyDTO(String writer, String content, LocalDateTime createdAt) {
                this.writer = writer;
                this.content = content;
                this.createdAt = createdAt;
            }
        }
    }

}

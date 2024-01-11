package com.example.sbb.comment;

import lombok.Getter;
import lombok.Setter;

public class CommentRequest {

    @Getter
    @Setter
    public static class CommentDTO {
        int boardId;
        String content;
    }
}

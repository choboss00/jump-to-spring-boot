package com.example.sbb.Board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

public class BoardRequest {

    @Getter
    @Setter
    public static class PostDTO {
        private String title;
        private String content;
    }
}

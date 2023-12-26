package com.example.sbb.comment;

import com.example.sbb.Board.Board;
import com.example.sbb.config.BaseTime;
import com.example.sbb.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "COMMENT")
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE COMMENT SET deleted_at = CURRENT_TIMESTAMP, is_deleted = TRUE where id = ?")
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Comment(User user, Board board, String content) {
        this.user = user;
        this.board = board;
        this.content = content;
    }
}

package com.example.sbb.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentJPARepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.board.id = :id")
    List<Comment> findAllByBoardId(int id);
}

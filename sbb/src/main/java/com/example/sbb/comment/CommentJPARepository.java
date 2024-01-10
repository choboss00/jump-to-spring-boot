package com.example.sbb.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJPARepository extends JpaRepository<Comment, Integer> {
}

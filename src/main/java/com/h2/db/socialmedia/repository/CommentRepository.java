package com.h2.db.socialmedia.repository;

import com.h2.db.socialmedia.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CommentRepository extends JpaRepository<Comment, Long> {
}





package com.h2.db.socialmedia.repository;

import com.h2.db.socialmedia.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;




public interface PostRepository extends JpaRepository<Post, Long> { List<Post> findAllByOrderByDateDesc();
}
package com.h2.db.socialmedia.repository;

import com.h2.db.socialmedia.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



// Change the return type to Optional<User>

public interface UserRepository extends JpaRepository<User, Long> { Optional<User> findByEmail(String email);
}



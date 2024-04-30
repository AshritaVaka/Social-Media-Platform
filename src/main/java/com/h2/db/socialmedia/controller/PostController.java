
package com.h2.db.socialmedia.controller;

import com.h2.db.socialmedia.model.Post;
import com.h2.db.socialmedia.model.User;
import com.h2.db.socialmedia.repository.PostRepository;
import com.h2.db.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/post")

public class PostController {

    private final PostRepository postRepository;

    private final UserRepository userRepository;


    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository) {


        this.postRepository = postRepository;

        this.userRepository = userRepository;
    }
    // ENDPOINTS TO create post
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest request)
    {
        Optional<User> optionalUser = userRepository.findById(request.getUserID());
        if (optionalUser.isPresent()) {

            User user = optionalUser.get();
            Post post = new Post(request.getPostBody(), user);

            postRepository.save(post);
            return ResponseEntity.ok("Post created successfully");

        }
        else
        {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Status\": \"Error\", \"message\": \"User does not exist\"}");
        }
    }
    // ENDPOINTS TO retrieve post
    @GetMapping
    public ResponseEntity<?> getPost(@RequestParam Long postID) {

        Optional<Post> optionalPost = postRepository.findById(postID);
        if (optionalPost.isPresent()) {

            return ResponseEntity.ok(optionalPost.get());
        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Status\": \"Error\", \"message\": \"Post does not exist\"}");
        }
    }
    // ENDPOINTS TO edit post
    @PatchMapping
    public ResponseEntity<?> editPost(@RequestBody PostRequest request)
    {
        Optional<Post> optionalPost = postRepository.findById(request.getPostID());
        if (optionalPost.isPresent())
        {
            Post post = optionalPost.get();
            post.setPostBody(request.getPostBody());
            postRepository.save(post);

            return ResponseEntity.ok("Post edited successfully");
        }
        else
        {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Status\": \"Error\", \"message\": \"Post does not exist\"}");
        }
    }
    // ENDPOINTS TO delete post
    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam Long postID) {
        if (postRepository.existsById(postID)) {
            postRepository.deleteById(postID);
            return ResponseEntity.ok("Post deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Status\": \"Error\", \"message\": \"Post does not exist\"}");
        }
    }

    // Inner class for request body
    static class PostRequest {

        private String postBody;

        private Long userID;

        private Long postID;


        public String getPostBody() {return postBody;
        }

        public void setPostBody(String postBody) {this.postBody = postBody;
        }

        public Long getUserID() {return userID;
        }

        public void setUserID(Long userId) {this.userID = userId;
        }

        public Long getPostID() {return postID;
        }

        public void setPostID(Long postID) {this.postID = postID;
        }
    }
}


package com.h2.db.socialmedia.controller;

import com.h2.db.socialmedia.model.Comment;
import com.h2.db.socialmedia.model.Post;
import com.h2.db.socialmedia.model.User;
import com.h2.db.socialmedia.repository.CommentRepository;
import com.h2.db.socialmedia.repository.PostRepository;
import com.h2.db.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
// ENDPOINTS for comment
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {

        this.commentRepository = commentRepository;

        this.postRepository = postRepository;

        this.userRepository = userRepository;
    }
    // ENDPOINTS for add comment
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentRequest request) {

        Optional<Post> post = postRepository.findById(request.getPostID());
        if (!post.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", : \"Post does not exist\"}"
            );

        }

        Optional<User> user = userRepository.findById(request.getUserID());

        if (!user.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Status\": \"Error\", : \"User does not exist\"}"
            );

        }

        Comment comment = new Comment(request.getCommentBody(), post.get(), user.get());

        commentRepository.save(comment);

        return ResponseEntity.ok("Comment created successfully");
    }
    // ENDPOINTS for getting comment
    @GetMapping
    public ResponseEntity<?> getComment(@RequestParam Long commentID) {

        Optional<Comment> optionalComment = commentRepository.findById(commentID);

        if (optionalComment.isPresent()) {

            return ResponseEntity.ok(optionalComment.get());

        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"Status\": \"Error\", : \"Comment does not exist\"}"

            );
        }
    }
    // ENDPOINTS for editing comment
    @PatchMapping
    public ResponseEntity<?> editComment(@RequestBody CommentRequest request) {

        Optional<Comment> optionalComment = commentRepository.findById(request.getCommentID());

        if (optionalComment.isPresent()) {

            Comment comment = optionalComment.get();

            comment.setCommentBody(request.getCommentBody());

            commentRepository.save(comment);

            return ResponseEntity.ok("Comment edited successfully");
        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"Status\": \"Error\", : \"Comment does not exist\"}"

            );

        }

    }

    // ENDPOINTS for deleting comment
    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestParam Long commentID) {

        if (commentRepository.existsById(commentID)) {

            commentRepository.deleteById(commentID);

            return ResponseEntity.ok("Comment deleted");


        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "{\"Status\": \"Error\",: \"Comment does not exist\"}"
            );

        }

    }

    // Inner class for request body
    static class CommentRequest {

        private String commentBody;

        private Long postID;

        private Long userID;

        private Long commentID;


        public String getCommentBody() {return commentBody;
        }

        public void setCommentBody(String commentBody) {this.commentBody = commentBody;
        }



        public Long getPostID() {return postID;
        }

        public void setPostID(Long postID) {this.postID = postID;
        }




        public Long getUserID() {return userID;
        }

        public void setUserID(Long userID) {this.userID = userID;
        }

        public Long getCommentID() {return commentID;
        }



        public void setCommentID(Long commentID) {this.commentID = commentID;
        }
    }
}


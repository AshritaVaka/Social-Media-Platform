package com.h2.db.socialmedia.controller;

// ALL NECESSARY LIBRARIES TO IMPORT

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2.db.socialmedia.model.User;
import com.h2.db.socialmedia.repository.UserRepository;
import com.h2.db.socialmedia.repository.PostRepository;
import com.h2.db.socialmedia.model.Comment;
import com.h2.db.socialmedia.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

// ENDPOINTS TO USE FOR USER
@RestController
@RequestMapping

public class UserController {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, PostRepository postRepository) {

        this.userRepository = userRepository;

        this.postRepository = postRepository;
    }

    // ENDPOINTS TO SIGNUP
    @PostMapping("/signup")  // BASE URL
    public ResponseEntity<String> addUser(@RequestBody User request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent())
        {return ResponseEntity.status(409).body("{\"Error\": \"Forbidden, Account already exists \"}");
        }
        userRepository.save(request);
        // Save the user directly
        return ResponseEntity.ok("Account Creation Successful");
    }

    // ENDPOINTS TO LOGIN
    @PostMapping("/login")

    public ResponseEntity<String> login(@RequestBody User request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty())
        {return ResponseEntity.status(404).body("{\"Error\": \"User does not exist\"}");
        }
        if (!user.get().getPassword().equals(request.getPassword())) {return ResponseEntity.status(401).body("{\"Error\": \"Username/Password Incorrect\"}");
        }

        return ResponseEntity.ok("Login Successful");
    }

    // ENDPOINTS TO PRINT USER DETAILS
    @GetMapping("/user")

    public ResponseEntity<?> getUserDetail(@RequestParam("userID") int userID)
    {
        Optional<User> user = userRepository.findById((long) userID);

        if (user.isEmpty()) {

            return ResponseEntity.status(404).body("{\"Error\": \"User does not exist\"}");
        }
        return ResponseEntity.ok(user.get());
    }

    // END POINTS TO PRINT ALL USERS SIGNEDUP
    @GetMapping("/users")

    public ResponseEntity<List<UserDetails>> getAllUsers()
    {
        List<User> users = userRepository.findAll();

        List<UserDetails> userDetailsList = users.stream()

                .map(user -> new UserDetails(user.getName(), user.getUserID(), user.getEmail()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDetailsList);
    }

    // Helper class to define the user details structure
    public static class UserDetails
    {

        private final String name;

        private final int userID;

        private final String email;

        public UserDetails(String name, int userID, String email) {

            this.name = name;

            this.userID = userID;

            this.email = email;
        }

        public String getName() {return name;
        }

        public int getUserID() {return userID;
        }

        public String getEmail() {return email;
        }
    }

    // ENDPOINTS TO IMPLEMENT USERFEED
    @GetMapping("/")
    public ResponseEntity<List<PostDetails>> getUserFeed()
    {
        List<Post> posts = postRepository.findAllByOrderByDateDesc();

        List<PostDetails> postDetailsList = posts.stream()

                .map(post -> new PostDetails(post.getPostID(), post.getPostBody(), post.getDate(), post.getComments()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(postDetailsList);
    }

    // Helper class to define the post details structure
    public static class PostDetails
    {
        private final int postID;

        private final String postBody;

        private final Date date;

        private final List<CommentDetails> comments;



        public PostDetails(int postID, String postBody, Date date, List<Comment> comments)
        {

            this.postID = postID;

            this.postBody = postBody;

            this.date = date;

            this.comments = comments.stream()

                    .map(comment -> new CommentDetails(comment.getCommentID(), comment.getCommentBody(), new CommentCreator(comment.getUser().getUserID(), comment.getUser().getName())))
                    .collect(Collectors.toList());
        }

        public int getPostID() {return postID;
        }

        public Date getDate() {return date;
        }

        public String getPostBody() {return postBody;
        }

        public List<CommentDetails> getComments() {return comments;
        }
    }

    // Helper class to define the comment details structure
    public static class CommentDetails
    {
        private final int commentID;

        private final String commentBody;

        private final CommentCreator commentCreator;

        public CommentDetails(int commentID, String commentBody, CommentCreator commentCreator) {

            this.commentID = commentID;

            this.commentBody = commentBody;

            this.commentCreator = commentCreator;
        }

        public int getCommentID() {return commentID;
        }

        public String getCommentBody() {return commentBody;
        }

        public CommentCreator getCommentCreator() {return commentCreator;
        }
    }

    // Helper class to define the comment creator structure
    public static class CommentCreator {

        private final int userID;

        private final String name;

        public CommentCreator(int userID, String name)
        {
            this.userID = userID;

            this.name = name;
        }

        public int getUserID() {return userID;
        }

        public String getName() {return name;
        }
    }
}

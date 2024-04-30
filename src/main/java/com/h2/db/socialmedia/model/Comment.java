
package com.h2.db.socialmedia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int commentID;  // Unique ID for the comment

    private String commentBody;  // Text content of the comment

    @ManyToOne
    @JoinColumn(name = "post_id")

    private Post post;  // Associated post

    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;  // User who created the comment

    // Constructor, Getters, and Setters
    public Comment() {
        // Default constructor
    }

    public Comment(String commentBody, Post post, User user)
    {
        this.commentBody = commentBody;

        this.post = post;

        this.user = user;
    }

    public int getCommentID() {return commentID;
    }

    public void setCommentID(int commentID) {this.commentID = commentID;
    }

    public void setCommentBody(String commentBody) {this.commentBody = commentBody;
    }

    public String getCommentBody() {return commentBody;
    }

    public Post getPost() {return post;
    }

    public void setPost(Post post) {this.post = post;
    }


    public void setUser(User user) {this.user = user;
    }

    public User getUser() {return user;
    }

    public int getPostID() {return this.post.getPostID();
    }

    public int getUserID() {return this.user.getUserID();
    }
}



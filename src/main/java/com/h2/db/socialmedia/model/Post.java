
package com.h2.db.socialmedia.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int postID;

    private String postBody;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)

    private List<Comment> comments;

    public Post() {// Default constructor
    }

    public Post(String postBody, User user)
    {
        this.postBody = postBody;

        this.date = new Date();

        this.user = user;

    }

    // Getters and Setters
    public int getPostID() {return postID;
    }

    public String getPostBody() {return postBody;
    }

    public void setPostBody(String postBody) {this.postBody = postBody;
    }

    public Date getDate() {return date;
    }

    public void setDate(Date date) {this.date = date;
    }

    public void setUser(User user) {this.user = user;
    }

    public User getUser() {return user;
    }

    public List<Comment> getComments() {return comments;
    }

    public void setComments(List<Comment> comments) {this.comments = comments;
    }
}
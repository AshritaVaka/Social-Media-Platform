package com.h2.db.socialmedia.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int userID;

    private String email;

    private String name;

    private String password;

    public User() {// Default constructor
    }

    public User(String email, String name, String password)
    {
        this.email = email;

        this.name = name;

        this.password = password;

    }

    // Getters and Setters
    public int getUserID() {return userID;
    }

    public String getEmail() {return email;
    }

    public void setEmail(String email) {this.email = email;
    }

    public void setName(String name) {this.name = name;
    }

    public String getName() {return name;
    }

    public String getPassword() {return password;
    }

    public void setPassword(String password) {this.password = password;
    }


}



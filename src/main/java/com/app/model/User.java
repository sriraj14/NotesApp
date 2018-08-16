package com.app.model;

import com.app.annotations.PasswordMatches;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Users")
@PasswordMatches
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    @Size(min=5, max=50)
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(min=6, max=50)
    private String password;

    @NotNull
    private String passwordConfirmation;

    @Column(name = "role")
    private String role;

    public User(String role, String username, String password, String passwordConfirmation) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

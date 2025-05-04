package com.airtribe.project.Entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> preferences; // Added preferences field

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPreferences() {
        return preferences; // Return preferences
    }

    public void setPreferences(Set<String> preferences) {
        this.preferences = preferences; // Setter for preferences
    }

    public void setRole(String user) {
        this.roles = Set.of(user);
    }

    public String getRole() {
        return roles.iterator().next();
    }
}
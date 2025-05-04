package com.airtribe.project.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "news_articles")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String description;

    private boolean read;
    private boolean favorite;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Reference the User entity
    private User user;

    public News() {}

    public News(String title, String url, String description, User user) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.user = user;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public String getDescription() { return description; }
    public boolean isRead() { return read; }
    public boolean isFavorite() { return favorite; }
    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setUrl(String url) { this.url = url; }
    public void setDescription(String description) { this.description = description; }
    public void setRead(boolean read) { this.read = read; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public void setUser(User user) { this.user = user; }

}
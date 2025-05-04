package com.airtribe.project.Controller;

import com.airtribe.project.Service.NewsService;
import com.airtribe.project.DTO.NewsArticle;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")

public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/preferences/{userId}")
    @Cacheable(value = "newsCache", key = "#userId")
    public List<NewsArticle> getNewsByPreference(@PathVariable Long userId) {
        return newsService.getNewsByPreference(userId);
    }

    @GetMapping("/category/{userId}/{category}")
    @Cacheable(value = "newsCache", key = "#userId")
    public List<NewsArticle> getNewsByCategory(@PathVariable Long userId, @PathVariable String category) {
        return newsService.getNewsByCategory(userId, category);
    }

    @GetMapping("/source/{userId}/{source}")
    @Cacheable(value = "newsCache", key = "#userId")
    public List<NewsArticle> getNewsBySource(@PathVariable Long userId, @PathVariable String source) {
        return newsService.getNewsBySource(userId, source);
    }

    @PostMapping("/markAsRead/{userid}/{newsid}")
    public void markAsRead(@PathVariable Long userid, @PathVariable Long newsid) {
        newsService.markAsRead(userid, newsid);
    }

    @PostMapping("/markAsFavorite/{userid}/{newsid}")
    public void markAsFavorite(@PathVariable Long userid, @PathVariable Long newsid) {
        newsService.markAsFavorite(userid, newsid);
    }

    @GetMapping("/read/{userId}")
    public List<NewsArticle> getReadNews(@PathVariable Long userId) {
        return newsService.getReadNews(userId);
    }

    @GetMapping("/favorite/{userId}")
    public List<NewsArticle> getFavoriteNews(@PathVariable Long userId) {
        return newsService.getFavoriteNews(userId);
    }


}

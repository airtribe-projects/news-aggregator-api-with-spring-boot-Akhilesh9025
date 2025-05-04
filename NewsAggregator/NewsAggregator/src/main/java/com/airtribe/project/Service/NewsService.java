package com.airtribe.project.Service;

import com.airtribe.project.DTO.NewsArticle;
import com.airtribe.project.Entity.News;
import com.airtribe.project.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Value("${news.api.base-url}")
    private String baseUrl;

    @Value("${news.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Cacheable(value = "newsCache", key = "#userId")
    public List<NewsArticle> getNewsByPreference(Long userId) {
        String url = baseUrl + "?apiKey=" + apiKey + "&country=in";
        return restTemplate.getForObject(url, List.class);
    }

    @Cacheable(value = "newsCache", key = "#userId")
    public List<NewsArticle> getNewsByCategory(Long userId, String category) {
        String url = baseUrl + "?apiKey=" + apiKey + "&category=" + category;
        return restTemplate.getForObject(url, List.class);
    }

    @Cacheable(value = "newsCache", key = "#userId")
    public List<NewsArticle> getNewsBySource(Long userId, String source) {
        String url = baseUrl + "?apiKey=" + apiKey + "&sources=" + source;
        return restTemplate.getForObject(url, List.class);
    }

    public void markAsRead(Long userId, Long newsId) {
        Optional<News> news = newsRepository.findById(newsId);
        news.ifPresent(n -> {
            if (n.getUser().getId().equals(userId)) {
                n.setRead(true);
                newsRepository.save(n);
            }
        });
    }

    public void markAsFavorite(Long userId, Long newsId) {
        Optional<News> news = newsRepository.findById(newsId);
        news.ifPresent(n -> {
            if (n.getUser().getId().equals(userId)) {
                n.setFavorite(true);
                newsRepository.save(n);
            }
        });
    }

    public List<News> getReadArticles(Long userId) {
        return newsRepository.findByUserIdAndReadTrue(userId);
    }

    public List<News> getFavoriteArticles(Long userId) {
        return newsRepository.findByUserIdAndFavoriteTrue(userId);
    }

    @Cacheable(value = "newsCache", key = "#userId")
    public List<NewsArticle> getFavoriteNews(Long userId) {
        List<News> favoriteArticles = newsRepository.findByUserIdAndFavoriteTrue(userId);
        return favoriteArticles.stream()
                .map(article -> {
                    NewsArticle newsArticle = new NewsArticle();
                    newsArticle.setTitle(article.getTitle());
                    newsArticle.setDescription(article.getDescription());
                    newsArticle.setUrl(article.getUrl());
                    return newsArticle;
                })
                .toList();
    }

    @Cacheable(value = "newsCache", key = "#userId")
    public List<NewsArticle> getReadNews(Long userId) {
        List<News> readArticles = newsRepository.findByUserIdAndReadTrue(userId);
        return readArticles.stream()
                .map(article -> {
                    NewsArticle newsArticle = new NewsArticle();
                    newsArticle.setTitle(article.getTitle());
                    newsArticle.setDescription(article.getDescription());
                    newsArticle.setUrl(article.getUrl());
                    return newsArticle;
                })
                .toList();
    }
}

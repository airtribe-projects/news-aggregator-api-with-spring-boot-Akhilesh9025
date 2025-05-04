package com.airtribe.project.Repository;

import com.airtribe.project.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByUserIdAndReadTrue(Long userId);
    List<News> findByUserIdAndFavoriteTrue(Long userId);
}
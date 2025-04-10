package com.saivarun.tiny.url.repository;

import com.saivarun.tiny.url.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {

    Optional<Url> findByShortUrl(String url);
    boolean existsByLongUrl(String longUrl);
}

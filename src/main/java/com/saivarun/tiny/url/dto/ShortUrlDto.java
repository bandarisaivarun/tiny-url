package com.saivarun.tiny.url.dto;

public class ShortUrlDto {
    public String shortUrl;

    public ShortUrlDto(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "ShortUrlDto{" +
                "shortUrl='" + shortUrl + '\'' +
                '}';
    }
}

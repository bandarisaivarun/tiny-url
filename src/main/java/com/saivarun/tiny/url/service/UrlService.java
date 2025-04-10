package com.saivarun.tiny.url.service;

import com.saivarun.tiny.url.dto.LongUrlDto;
import com.saivarun.tiny.url.dto.ShortUrlDto;
import com.saivarun.tiny.url.model.Url;
import com.saivarun.tiny.url.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UrlService {
    UrlRepository urlRepository;
    public UrlService(UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }
    public static String alphanum="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String BASE_URL="http://localhost:8080/";

    public ShortUrlDto getShortUrl(LongUrlDto url) throws NoSuchAlgorithmException {

        //check if longUrl is already exists or not
        if(urlRepository.existsByLongUrl(url.getLongUrl())){
            throw new RuntimeException("duplicate data: URL is already exist");
        }

       MessageDigest md= MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(url.getLongUrl().getBytes());
        String encode = Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
        System.out.println(encode);
        String shortUrl=encode.substring(0,6);
        Url urlR=new Url();
        urlR.setLongUrl(url.getLongUrl());
        urlR.setShortUrl(shortUrl);
        urlRepository.save(urlR);
        return new ShortUrlDto(BASE_URL+shortUrl);
    }

    public String redirectToLongUrl(String shortUrl){
        String longUrl= urlRepository.findByShortUrl(shortUrl)
                .map(Url::getLongUrl)
                .orElseThrow(()->new RuntimeException("URL not found"));
        return longUrl;
    }
}

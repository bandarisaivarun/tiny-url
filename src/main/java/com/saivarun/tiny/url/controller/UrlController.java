package com.saivarun.tiny.url.controller;

import com.saivarun.tiny.url.dto.LongUrlDto;
import com.saivarun.tiny.url.dto.ShortUrlDto;
import com.saivarun.tiny.url.service.UrlService;
import jakarta.websocket.server.PathParam;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.NoSuchAlgorithmException;


@RestController
public class UrlController {

    UrlService urlService;
    private static final Logger logger = LoggerFactory.getLogger(UrlController.class);

    public UrlController(UrlService urlService){
        this.urlService=urlService;
    }

    @PostMapping("/getTinyUrl")
    public ResponseEntity<ShortUrlDto> getUrlShortner(@RequestBody LongUrlDto url) throws NoSuchAlgorithmException {
        logger.info(url.toString());
        return ResponseEntity.status(200).body(urlService.getShortUrl(url));
    }

    @GetMapping("/{url}")
    public ResponseEntity<LongUrlDto> redirect(@PathVariable("url") String url){
        logger.info("url"+url);
        String longUrl= urlService.redirectToLongUrl(url);
        logger.info(longUrl);
       // return ResponseEntity.status(200).body(longUrl);
       return  ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longUrl)).build();
    }
}

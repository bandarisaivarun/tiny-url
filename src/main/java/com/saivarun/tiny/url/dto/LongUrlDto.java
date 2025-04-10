package com.saivarun.tiny.url.dto;


public class LongUrlDto {
    public String longUrl;

    public LongUrlDto(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public String toString() {
        return "LongUrlDto{" +
                "longUrl='" + longUrl + '\'' +
                '}';
    }
}

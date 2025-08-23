package com.example.urlshortener.controller;

public class ShortenResponse {
    private String code;
    private String shortUrl;
    private String longUrl;

    public ShortenResponse(String code, String shortUrl, String longUrl) {
        this.code = code;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }

    public String getCode() { return code; }
    public String getShortUrl() { return shortUrl; }
    public String getLongUrl() { return longUrl; }
}

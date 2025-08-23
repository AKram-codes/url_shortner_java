package com.example.urlshortener.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ShortenRequest {
    @NotBlank
    @Size(max = 2048)
    @Pattern(regexp = "^(?i)(https?://).+", message = "URL must start with http:// or https://")
    private String longUrl;

    private Integer customLength; // optional

    public String getLongUrl() { return longUrl; }
    public void setLongUrl(String longUrl) { this.longUrl = longUrl; }

    public Integer getCustomLength() { return customLength; }
    public void setCustomLength(Integer customLength) { this.customLength = customLength; }
}

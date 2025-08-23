package com.example.urlshortener.controller;

import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponse> shorten(@Valid @RequestBody ShortenRequest request,
                                                   HttpServletRequest http) {
        UrlMapping mapping = urlService.createShortUrl(request.getLongUrl(), request.getCustomLength());
        String base = getBaseUrl(http);
        return new ResponseEntity<>(new ShortenResponse(mapping.getCode(), base + "/r/" + mapping.getCode(), mapping.getLongUrl()), HttpStatus.CREATED);
    }

    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();
        String portPart = (("http".equals(scheme) && port == 80) || ("https".equals(scheme) && port == 443)) ? "" : (":" + port);
        return scheme + "://" + host + portPart;
    }
}

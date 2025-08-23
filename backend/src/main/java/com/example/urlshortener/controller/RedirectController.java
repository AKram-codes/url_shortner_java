package com.example.urlshortener.controller;

import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/r/{code}")
    public void redirect(@PathVariable("code") String code, HttpServletResponse response) throws IOException {
        try {
            UrlMapping m = urlService.resolve(code);
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", m.getLongUrl());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short code not found");
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.GONE, "Short link expired");
        }
    }

}

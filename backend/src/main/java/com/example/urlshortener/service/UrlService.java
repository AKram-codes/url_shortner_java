package com.example.urlshortener.service;

import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.repository.UrlMappingRepository;
import com.example.urlshortener.util.Base62;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class UrlService {
    private final UrlMappingRepository repository;

    public UrlService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UrlMapping createShortUrl(String longUrl, Integer customLength) {
        int length = (customLength != null && customLength >= 4 && customLength <= 12) ? customLength : 7;
        String code;
        int attempts = 0;
        do {
            code = Base62.randomCode(length);
            attempts++;
            if (attempts > 10) {
                length++; // expand if collisions
            }
        } while (repository.existsByCode(code));

        UrlMapping mapping = new UrlMapping(code, longUrl, null);
        return repository.save(mapping);
    }

    @Transactional
    public UrlMapping resolve(String code) {
        UrlMapping m = repository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Code not found"));
        // expiry check
        if (m.getExpiresAt() != null && m.getExpiresAt().isBefore(OffsetDateTime.now())) {
            throw new IllegalStateException("Link expired");
        }
        m.setClickCount(m.getClickCount() + 1);
        return repository.save(m);
    }
}

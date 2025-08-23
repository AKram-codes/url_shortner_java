package com.example.urlshortener.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.urlshortener.entity.UrlMapping;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByCode(String code);
    boolean existsByCode(String code);
}

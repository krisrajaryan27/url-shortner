package com.theprovenclub.urlshorner.model;


import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class ShortenedUrl {
    private final String originalUrl;
    private final String shortAlias;
    private final LocalDateTime createdAt;
    private final AtomicInteger accessCount;

    public ShortenedUrl(String originalUrl, String shortAlias, LocalDateTime createdAt) {
        this.originalUrl = originalUrl;
        this.shortAlias = shortAlias;
        this.createdAt = createdAt;
        this.accessCount = new AtomicInteger(0);
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortAlias() {
        return shortAlias;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getAccessCount() {
        return accessCount.get();
    }

    public void incrementAccessCount() {
        accessCount.incrementAndGet();
    }
}

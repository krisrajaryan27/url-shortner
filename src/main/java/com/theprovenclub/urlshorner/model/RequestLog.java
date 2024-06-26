package com.theprovenclub.urlshorner.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestLog {

    private final AtomicInteger accessCount;

    private LocalDateTime lastAccessTime;

    public RequestLog() {
        this.accessCount = new AtomicInteger(0);
        this.lastAccessTime = LocalDateTime.now();
    }

    public int getAccessCount() {
        return accessCount.get();
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public void incrementAccessCount() {
        accessCount.incrementAndGet();
    }
}

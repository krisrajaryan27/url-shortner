package com.theprovenclub.urlshorner.service;

import com.theprovenclub.urlshorner.model.RequestLog;
import com.theprovenclub.urlshorner.model.ShortenedUrl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerServiceImpl.class);

    private static final Map<String, ShortenedUrl> shortenedUrlMap = new ConcurrentHashMap<>();
    private static final Map<String, RequestLog> requestLogsMap = new ConcurrentHashMap<>();

    private static final int SHORT_ALIAS_LENGTH = 6;

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final int REQUEST_LIMIT = 100;

    @Override
    public ShortenedUrl shortenUrl(String longUrl) {
        LOGGER.info("Inside UrlShortener service for shortenURl with longUrl: {}", longUrl);
        String shortAlias = generateShortCode();
        ShortenedUrl shortenedUrl = new ShortenedUrl(longUrl, shortAlias, LocalDateTime.now());
        shortenedUrlMap.put(shortAlias, shortenedUrl);
        LOGGER.info("shortUrl: {} generated for longUrl: {}", shortAlias, longUrl);
        return shortenedUrl;
    }

    @Override
    public void redirectToOriginal(String shortAlias, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Inside UrlShortener service for redirectToOriginal with shortCode: {}", shortAlias);
        ShortenedUrl shortenedUrl = shortenedUrlMap.get(shortAlias);
        if(shortenedUrl != null){
            if(isWithinRequestLimit(shortAlias)){
                shortenedUrl.incrementAccessCount();
                logRequest(shortAlias);
                response.sendRedirect(shortenedUrl.getOriginalUrl());
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Rate limit exceeded, please try again later.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Short Code not found.");
        }
    }

    @Override
    public Map<String, Object> getStatistics(String shortAlias) {
        LOGGER.info("Inside UrlShortener service for getStatistics with shortCode: {}", shortAlias);
        ShortenedUrl shortenedUrl = shortenedUrlMap.get(shortAlias);
        if(shortenedUrl != null){
           Map<String, Object> statistics = new HashMap<>();
           statistics.put("originalUrl", shortenedUrl.getOriginalUrl());
           statistics.put("shortAlias", shortenedUrl.getShortAlias());
           statistics.put("createdAt", shortenedUrl.getCreatedAt());
           statistics.put("accessCount", shortenedUrl.getAccessCount());
           return statistics;
        }
        return null;
    }

    private String generateShortCode(){
        return IntStream.range(0, SHORT_ALIAS_LENGTH)
                        .mapToObj(i -> String.valueOf(CHARACTERS.charAt(ThreadLocalRandom.current().nextInt(CHARACTERS.length()))))
                        .collect(Collectors.joining());
    }

    private void logRequest(String shortAlias){
        RequestLog requestLog = requestLogsMap.getOrDefault(shortAlias, new RequestLog());
        requestLog.incrementAccessCount();
        requestLog.setLastAccessTime(LocalDateTime.now());
        requestLogsMap.put(shortAlias, requestLog);
    }

    private boolean isWithinRequestLimit(String shortAlias){
        LOGGER.info("Inside UrlShortener service to check if request isWithinRequestLimit for shortCode: {}", shortAlias);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMinuteAgo = now.minusMinutes(-1);
        long requestsInLastOneMinute = requestLogsMap.entrySet().stream().
                filter(entry -> entry.getKey().equals(shortAlias))
                .filter(entry -> entry.getValue().getLastAccessTime().isAfter(oneMinuteAgo))
                .mapToLong(entry -> entry.getValue().getAccessCount())
                .sum();


        return requestsInLastOneMinute < REQUEST_LIMIT;
    }
}

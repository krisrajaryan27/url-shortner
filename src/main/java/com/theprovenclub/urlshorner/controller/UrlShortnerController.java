package com.theprovenclub.urlshorner.controller;

import com.theprovenclub.urlshorner.model.ApiResponse;
import com.theprovenclub.urlshorner.model.ShortenedUrl;
import com.theprovenclub.urlshorner.service.UrlShortnerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class UrlShortnerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerController.class);

    @Autowired
    private UrlShortnerService urlShortnerService;

    @PostMapping("/url/shorten")
    public ResponseEntity<ApiResponse> shortenUrl(@RequestParam String longUrl){
        LOGGER.info("Inside UrlShortener controller for shortenURl with longUrl: {}", longUrl);
        if(longUrl.isEmpty()) throw new IllegalStateException("Incorrect url passed in request");
        ShortenedUrl shortenedUrl = urlShortnerService.shortenUrl(longUrl);
        ApiResponse response = new ApiResponse(shortenedUrl.getShortAlias(), longUrl, shortenedUrl.getCreatedAt(), shortenedUrl.getAccessCount());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/url/redirect/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Inside UrlShortener controller to redirectToOriginal for shortCode: {} for request: {} and response: {}", shortCode, request, response);
        urlShortnerService.redirectToOriginal(shortCode, request, response);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/url/stats/{shortCode}")
    public ResponseEntity<Map<String, Object>> getStats(@PathVariable String shortCode){
        Map<String, Object> statistics = urlShortnerService.getStatistics(shortCode);
        if(statistics != null){
            return ResponseEntity.ok(statistics);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

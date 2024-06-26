package com.theprovenclub.urlshorner.service;

import com.theprovenclub.urlshorner.model.ShortenedUrl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public interface UrlShortnerService {

    ShortenedUrl shortenUrl(String longUrl);

    void redirectToOriginal(String shortAlias, HttpServletRequest request, HttpServletResponse response) throws IOException;

    Map<String, Object> getStatistics(String shortAlias);


}

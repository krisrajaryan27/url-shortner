package com.theprovenclub.urlshorner.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public interface UrlShortnerService {

    String shortenUrl(String longUrl);

    void redirectToOriginal(String shortAlias, HttpServletRequest request, HttpServletResponse response) throws IOException;

    Map<String, Object> getStatistics(String shortAlias);


}

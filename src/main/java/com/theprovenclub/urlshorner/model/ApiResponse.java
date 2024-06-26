package com.theprovenclub.urlshorner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    String shortCode;
    String longUrl;
    LocalDateTime createdAt;
    Integer accessCount;
}

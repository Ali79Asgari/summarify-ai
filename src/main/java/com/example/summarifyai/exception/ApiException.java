package com.example.summarifyai.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom exception for API-related errors.
 * Includes HTTP status code and error message.
 */
@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}
package com.example.summarifyai.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private final Map<String, String> errors;
}
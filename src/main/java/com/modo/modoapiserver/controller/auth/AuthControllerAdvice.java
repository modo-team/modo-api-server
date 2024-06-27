package com.modo.modoapiserver.controller.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class AuthControllerAdvice {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(ApiErrorResponse.of(
                        e.getMessage(),
                        String.valueOf(e.getStatusCode().value())
                        )
                );
    }
}

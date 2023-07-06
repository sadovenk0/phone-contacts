package com.app.phonecontacts.model.dto.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class TokenResponse {
    private String token;
}

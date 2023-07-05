package com.app.phonecontacts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    @GetMapping
    public ResponseEntity<String> echo() {
        return ResponseEntity.ok("App is working");
    }
}

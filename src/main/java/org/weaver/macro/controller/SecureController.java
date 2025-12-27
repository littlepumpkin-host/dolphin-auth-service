package org.weaver.macro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping()
    public ResponseEntity<?> access() {
        return ResponseEntity.ok("Hello from secure endpoint");
    }
}


package org.weaver.macro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weaver.macro.dto.ClientAuthenticationRequest;
import org.weaver.macro.dto.CreateClientRequest;
import org.weaver.macro.service.ClientService;

import java.util.Map;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/new")
    public ResponseEntity<?> saveClient(@RequestBody CreateClientRequest request) {
        var response = clientService.createNewClient(request);
        if (response.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response.get());
    }

    @PostMapping("/verify")
    public ResponseEntity<?> authenticate(@RequestBody ClientAuthenticationRequest request) {
        try {
            var response = clientService.authenticate(request);
            if (response.isEmpty()) {
                // user not found
                // wrong credentials - wrong password
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(response.get());
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body(Map.of(
                    "error", "INVALID_CREDENTIALS",
                    "message", "Username or password is incorrect"
            ));
        }
    }
}

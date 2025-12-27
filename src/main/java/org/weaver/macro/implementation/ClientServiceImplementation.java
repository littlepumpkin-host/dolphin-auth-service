package org.weaver.macro.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.weaver.macro.dto.ClientAuthenticationRequest;
import org.weaver.macro.dto.ClientAuthenticationResponse;
import org.weaver.macro.dto.CreateClientRequest;
import org.weaver.macro.dto.CreateClientResponse;
import org.weaver.macro.model.Client;
import org.weaver.macro.repository.ClientRepository;
import org.weaver.macro.service.ClientService;
import org.weaver.macro.service.JwtService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public Optional<CreateClientResponse> createNewClient(CreateClientRequest request) {
        var client = clientRepository.save(
                Client.builder()
                        .username(request.username())
                        .password(encoder.encode(request.password()))
                        .build()
        );

        return Optional.ofNullable(
                CreateClientResponse.builder()
                        .username(client.getUsername())
                        .build()
        );
    }

    @Override
    public Optional<ClientAuthenticationResponse> authenticate(ClientAuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        if (authentication.isAuthenticated()) {
            return Optional.of(new ClientAuthenticationResponse(
                    jwtService.generateToken(request.username())
            ));
        }
        return Optional.empty();
    }

}

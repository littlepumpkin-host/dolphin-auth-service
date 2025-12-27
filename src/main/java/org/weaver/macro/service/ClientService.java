package org.weaver.macro.service;


import org.weaver.macro.dto.ClientAuthenticationRequest;
import org.weaver.macro.dto.ClientAuthenticationResponse;
import org.weaver.macro.dto.CreateClientRequest;
import org.weaver.macro.dto.CreateClientResponse;

import java.util.Optional;

public interface ClientService {
    Optional<CreateClientResponse> createNewClient(CreateClientRequest request);

    Optional<ClientAuthenticationResponse> authenticate(ClientAuthenticationRequest request);

}

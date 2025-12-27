package org.weaver.macro.dto;

public record ClientAuthenticationRequest(
        String username,
        String password
) {}
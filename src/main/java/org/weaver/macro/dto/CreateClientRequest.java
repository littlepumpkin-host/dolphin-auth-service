package org.weaver.macro.dto;

public record CreateClientRequest (
        String username,
        String password
) {}

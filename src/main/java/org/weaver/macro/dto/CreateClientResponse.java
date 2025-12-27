package org.weaver.macro.dto;

import lombok.Builder;

@Builder
public record CreateClientResponse(
        String username
) {}

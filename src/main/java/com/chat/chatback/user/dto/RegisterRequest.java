package com.chat.chatback.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record RegisterRequest(
        @NotNull
        @NotBlank
        String username,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        @NotBlank
        String displayName
) implements Serializable {

}
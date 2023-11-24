package com.chat.chatback.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatChannelRequest(
        @NotNull
        @NotBlank
        String username1,

        @NotNull
        @NotBlank
        String username2
) {
}

package com.chat.chatback.chat.dto;

import lombok.Builder;

@Builder
public record ChatMessageDTO(
        String content,
        String authorUsername,
        String recipientUsername
) {
}

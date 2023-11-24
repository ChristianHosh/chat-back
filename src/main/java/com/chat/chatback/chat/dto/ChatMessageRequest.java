package com.chat.chatback.chat.dto;

import java.io.Serializable;

public record ChatMessageRequest(
        String content,
        String senderUsername,
        Long chatChannelId
) implements Serializable {
    @Override
    public String toString() {
        return "ChatMessageRequest{" +
                "content='" + content + '\'' +
                ", senderUsername='" + senderUsername + '\'' +
                ", chatChannelId=" + chatChannelId +
                '}';
    }
}

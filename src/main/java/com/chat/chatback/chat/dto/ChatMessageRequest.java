package com.chat.chatback.chat.dto;

import java.io.Serializable;

public record ChatMessageRequest(
        String content,
        String sender,
        Long chatChannelId

) implements Serializable {
    @Override
    public String toString() {
        return "ChatMessageRequest{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", chatChannelId=" + chatChannelId +
                '}';
    }
}

package com.chat.chatback.chat.dto;

import lombok.Builder;

import java.io.Serializable;
import java.sql.Timestamp;

@Builder
public record ChatMessageResponse(
        Long id,
        Long chatChannelId,
        String content,
        String sender,
        Timestamp timeSent

) implements Serializable {
    @Override
    public String toString() {
        return "ChatMessageResponse{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", timeSent=" + timeSent +
                '}';
    }
}

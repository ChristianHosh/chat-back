package com.chat.chatback.chat.dto;

import lombok.Builder;

import java.io.Serializable;
import java.sql.Date;

@Builder
public record ChatMessageResponse(
        Long id,
        String content,
        String senderUsername,
        Date timeSent,
        Long chatChannelId

) implements Serializable {
    @Override
    public String toString() {
        return "ChatMessageResponse{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", senderUsername='" + senderUsername + '\'' +
                ", timeSent=" + timeSent +
                ", chatChannelId=" + chatChannelId +
                '}';
    }
}

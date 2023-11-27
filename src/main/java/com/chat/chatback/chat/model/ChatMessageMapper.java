package com.chat.chatback.chat.model;

import com.chat.chatback.chat.dto.ChatMessageRequest;
import com.chat.chatback.chat.dto.ChatMessageResponse;
import com.chat.chatback.user.model.User;

import java.sql.Timestamp;
import java.time.Instant;

public class ChatMessageMapper {

    public static ChatMessage requestToEntity(ChatMessageRequest chatMessageRequest, User sender, ChatChannel chatChannel){
        return ChatMessage.builder()
                .sender(sender)
                .chatChannel(chatChannel)
                .timeSent(Timestamp.from(Instant.now()))
                .content(chatMessageRequest.content())
                .build();
    }

    public static ChatMessageResponse entityToResponse(ChatMessage chatMessage) {
        return ChatMessageResponse.builder()
                .id(chatMessage.getId())
                .chatChannelId(chatMessage.getChatChannel().getId())
                .sender(chatMessage.getSender().getUsername())
                .content(chatMessage.getContent())
                .timeSent(chatMessage.getTimeSent())
                .build();
    }
}

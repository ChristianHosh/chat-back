package com.chat.chatback.chat.model;

import com.chat.chatback.chat.dto.ChatMessageRequest;
import com.chat.chatback.chat.dto.ChatMessageResponse;
import com.chat.chatback.user.model.User;

import java.sql.Date;

public class ChatMessageMapper {

    public static ChatMessage requestToEntity(ChatMessageRequest chatMessageRequest, User sender, ChatChannel chatChannel){
        return ChatMessage.builder()
                .sender(sender)
                .chatChannel(chatChannel)
                .timeSent(new Date(System.currentTimeMillis()))
                .content(chatMessageRequest.content())
                .build();
    }

    public static ChatMessageResponse entityToResponse(ChatMessage chatMessage) {
        return ChatMessageResponse.builder()
                .id(chatMessage.getId())
                .chatChannelId(chatMessage.getChatChannel().getId())
                .senderUsername(chatMessage.getSender().getUsername())
                .content(chatMessage.getContent())
                .timeSent(chatMessage.getTimeSent())
                .build();
    }
}

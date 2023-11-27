package com.chat.chatback.chat.model;

import com.chat.chatback.chat.dto.ChatMessageRequest;
import com.chat.chatback.chat.dto.ChatMessageResponse;
import com.chat.chatback.user.model.User;
import com.chat.chatback.user.model.UserMapper;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.Instant;

public class ChatMessageMapper {

    public static ChatMessage requestToEntity(@NotNull ChatMessageRequest chatMessageRequest, @NotNull User sender, @NotNull ChatChannel chatChannel) {
        return ChatMessage.builder()
                .sender(sender)
                .chatChannel(chatChannel)
                .timeSent(Timestamp.from(Instant.now()))
                .content(chatMessageRequest.content())
                .build();
    }

    public static ChatMessageResponse entityToResponse(@NotNull ChatMessage chatMessage) {
        return ChatMessageResponse.builder()
                .id(chatMessage.getId())
                .chatChannelId(chatMessage.getChatChannel().getId())
                .sender(UserMapper.entityToResponse(chatMessage.getSender()))
                .content(chatMessage.getContent())
                .timeSent(chatMessage.getTimeSent())
                .build();
    }
}

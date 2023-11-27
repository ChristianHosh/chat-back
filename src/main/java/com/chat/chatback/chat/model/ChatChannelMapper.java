package com.chat.chatback.chat.model;

import com.chat.chatback.chat.dto.ChatChannelResponse;
import com.chat.chatback.user.model.User;

public class ChatChannelMapper {

    public static ChatChannel requestToEntity(User user1, User user2){
        return ChatChannel.builder()
                .user1(user1)
                .user2(user2)
                .build();
    }
    public static ChatChannelResponse entityToResponse(ChatChannel chatChannel){
        return ChatChannelResponse.builder()
                .channelId(chatChannel.getId())
                .username1(chatChannel.getUser1().getUsername())
                .username2(chatChannel.getUser2().getUsername())
                .build();
    }
}

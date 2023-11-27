package com.chat.chatback.chat.model;

import com.chat.chatback.chat.dto.ChatChannelResponse;
import com.chat.chatback.user.model.User;
import org.jetbrains.annotations.NotNull;

public class ChatChannelMapper {

    /**
     *
     * @param user1 the first user in the chat channel
     * @param user2 the second user in the chat channel
     * @return a ChatChannel entity object to be saved
     */
    public static ChatChannel requestToEntity(@NotNull User user1, @NotNull User user2) {
        return ChatChannel.builder()
                .user1(user1)
                .user2(user2)
                .build();
    }

    /**
     *
     * @param chatChannel the chatChannel entity object to be converted
     * @return a chatChannelResponse object that can be sent via API
     */
    public static ChatChannelResponse entityToResponse(@NotNull ChatChannel chatChannel) {
        return ChatChannelResponse.builder()
                .channelId(chatChannel.getId())
                .username1(chatChannel.getUser1().getUsername())
                .username2(chatChannel.getUser2().getUsername())
                .build();
    }
}

package com.chat.chatback.chat.dto;

import lombok.Builder;

@Builder
public record ChatChannelResponse (
        Long channelId,
        String username1,
        String username2

) {


}

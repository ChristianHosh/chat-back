package com.chat.chatback.chat.controller;

import com.chat.chatback.chat.dto.ChatChannelRequest;
import com.chat.chatback.chat.dto.ChatChannelResponse;
import com.chat.chatback.chat.dto.ChatMessageRequest;
import com.chat.chatback.chat.dto.ChatMessageResponse;
import com.chat.chatback.chat.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/messages")
    public void chat(ChatMessageRequest message){
        ChatMessageResponse messageResponse = chatService.chat(message);

        messagingTemplate.convertAndSend("/channel/chat/" + messageResponse.chatChannelId(), messageResponse);
    }

    @PutMapping("/api/chat/channel")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ChatChannelResponse createChatChannel(@RequestBody @Valid ChatChannelRequest chatChannelRequest){
        return chatService.createChatChannel(chatChannelRequest);
    }

    @GetMapping("/api/chat/channel/{channelId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Page<ChatMessageResponse> getChatMessagesByChannel(@PathVariable Long channelId){
        return chatService.getChatMessagesByChannel(channelId);
    }
}

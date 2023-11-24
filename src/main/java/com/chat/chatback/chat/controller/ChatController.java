package com.chat.chatback.chat.controller;

import com.chat.chatback.chat.dto.ChatChannelRequest;
import com.chat.chatback.chat.dto.ChatChannelResponse;
import com.chat.chatback.chat.dto.ChatMessageRequest;
import com.chat.chatback.chat.dto.ChatMessageResponse;
import com.chat.chatback.chat.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/messages")
    public void chat(ChatMessageRequest message){
        ChatMessageResponse messageResponse = chatService.chat(message);

        messagingTemplate.convertAndSend("/channel/chat/" + messageResponse.chatChannelId(), message);
    }

    @PutMapping("/api/chat/channel")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ChatChannelResponse createChatChannel(@RequestBody @Valid ChatChannelRequest chatChannelRequest){
        return chatService.createChatChannel(chatChannelRequest);
    }
}

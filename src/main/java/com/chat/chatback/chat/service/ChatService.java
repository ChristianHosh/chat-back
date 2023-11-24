package com.chat.chatback.chat.service;

import com.chat.chatback.chat.dto.ChatChannelRequest;
import com.chat.chatback.chat.dto.ChatChannelResponse;
import com.chat.chatback.chat.dto.ChatMessageRequest;
import com.chat.chatback.chat.dto.ChatMessageResponse;
import com.chat.chatback.chat.model.ChatChannel;
import com.chat.chatback.chat.model.ChatMessage;
import com.chat.chatback.chat.model.ChatMessageMapper;
import com.chat.chatback.error.HttpNotFoundException;
import com.chat.chatback.user.model.User;
import com.chat.chatback.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatChannelRepository chatChannelRepository;
    private final UserRepository userRepository;

    private User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new HttpNotFoundException("user not found with username '" + username + "'"));
    }

    private ChatChannel findChatChannelById(Long id) {
        return chatChannelRepository.findById(id)
                .orElseThrow(() -> new HttpNotFoundException("chat channel not found with id '" + id + "'"));
    }

    public ChatMessageResponse chat(ChatMessageRequest message) {
        User senderUser = findUserByUsername(message.senderUsername());
        ChatChannel chatChannel = findChatChannelById(message.chatChannelId());

        ChatMessage chatMessage = chatMessageRepository.save(ChatMessageMapper.requestToEntity(message, senderUser, chatChannel));

        // IMPLEMENT NOTIFY USER

        return ChatMessageMapper.entityToResponse(chatMessage);
    }



    public ChatChannelResponse createChatChannel(ChatChannelRequest chatChannelRequest) {
        User user1 = findUserByUsername(chatChannelRequest.username1());
        User user2 = findUserByUsername(chatChannelRequest.username2());

        ChatChannel chatChannel = chatChannelRepository.findByUser1AndUser2(user1, user2)
                .orElse(null);

        if (chatChannel == null){
            chatChannel = ChatChannel.builder()
                    .user1(user1)
                    .user2(user2)
                    .build();

            chatChannel = chatChannelRepository.save(chatChannel);
        }

        return ChatChannelResponse.builder()
                .channelId(chatChannel.getId())
                .username1(user1.getUsername())
                .username2(user2.getUsername())
                .build();
    }
}

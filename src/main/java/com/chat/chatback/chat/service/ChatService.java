package com.chat.chatback.chat.service;

import com.chat.chatback.chat.dto.ChatChannelRequest;
import com.chat.chatback.chat.dto.ChatChannelResponse;
import com.chat.chatback.chat.dto.ChatMessageRequest;
import com.chat.chatback.chat.dto.ChatMessageResponse;
import com.chat.chatback.chat.model.ChatChannel;
import com.chat.chatback.chat.model.ChatChannelMapper;
import com.chat.chatback.chat.model.ChatMessage;
import com.chat.chatback.chat.model.ChatMessageMapper;
import com.chat.chatback.error.HttpNotFoundException;
import com.chat.chatback.user.model.User;
import com.chat.chatback.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatChannelRepository chatChannelRepository;
    private final UserRepository userRepository;

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new HttpNotFoundException("user not found with username '" + username + "'"));
    }

    private ChatChannel findChatChannelById(Long id) {
        return chatChannelRepository.findById(id)
                .orElseThrow(() -> new HttpNotFoundException("chat channel not found with id '" + id + "'"));
    }

    public ChatMessageResponse chat(ChatMessageRequest message) {
        User senderUser = findUserByUsername(message.sender());
        ChatChannel chatChannel = findChatChannelById(message.chatChannelId());

        ChatMessage chatMessage = chatMessageRepository.save(ChatMessageMapper.requestToEntity(message, senderUser, chatChannel));

        // IMPLEMENT NOTIFY USER

        return ChatMessageMapper.entityToResponse(chatMessage);
    }


    public ChatChannelResponse createChatChannel(ChatChannelRequest chatChannelRequest) {
        User user1 = findUserByUsername(chatChannelRequest.username1());
        User user2 = findUserByUsername(chatChannelRequest.username2());

        // find chat channel between 2 users,
        // if none exists then creates one using ChatChannelMapper and saves it
        System.out.println("user1 => " + user1);
        System.out.println("user2 => " + user2);
        ChatChannel chatChannel = chatChannelRepository.findChatChannelByUsers(user1, user2)
                .orElse(null);

        System.out.println("chat channel => " + chatChannel);

        if (chatChannel == null){
            chatChannel = chatChannelRepository.save(ChatChannelMapper.requestToEntity(user1, user2));
        }

        return ChatChannelMapper.entityToResponse(chatChannel);
    }

    public Page<ChatMessageResponse> getChatMessagesByChannel(Long channelId) {
        ChatChannel chatChannel = findChatChannelById(channelId);

        Pageable pageable = Pageable.ofSize(100);

        Page<ChatMessage> chatMessagePage = chatMessageRepository.findByChatChannel(chatChannel, pageable);

        return chatMessagePage.map(ChatMessageMapper::entityToResponse);
    }
}

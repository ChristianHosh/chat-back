package com.chat.chatback.chat.service;

import com.chat.chatback.chat.model.ChatChannel;
import com.chat.chatback.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatChannelRepository extends JpaRepository<ChatChannel, Long> {
    Optional<ChatChannel> findByUser1AndUser2(User user1, User user2);
}
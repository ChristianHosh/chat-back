package com.chat.chatback.chat.service;

import com.chat.chatback.chat.model.ChatChannel;
import com.chat.chatback.chat.model.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Page<ChatMessage> findByChatChannel(ChatChannel chatChannel, Pageable pageable);
}
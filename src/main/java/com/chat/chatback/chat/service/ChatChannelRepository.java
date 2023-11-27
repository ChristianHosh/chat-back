package com.chat.chatback.chat.service;

import com.chat.chatback.chat.model.ChatChannel;
import com.chat.chatback.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatChannelRepository extends JpaRepository<ChatChannel, Long> {
    @Query("""
        SELECT c FROM ChatChannel c
        WHERE (
            (c.user1 = :user1 OR c.user2 = :user1) AND
            (c.user1 = :user2 OR c.user2 = :user2)
        )
    """)
    Optional<ChatChannel> findChatChannelByUsers(User user1, User user2);
}
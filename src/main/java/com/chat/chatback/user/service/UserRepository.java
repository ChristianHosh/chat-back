package com.chat.chatback.user.service;

import com.chat.chatback.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("""
    SELECT u FROM User u
    WHERE (
        (:name IS NULL OR LOWER(u.displayName) LIKE LOWER(CONCAT('%',:name, '%'))) AND
        (:name IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%',:name, '%'))) AND
        (u.id <> :currentUserId)
    )
    """)
    Page<User> findUsersWithoutCurrent(String name, Long currentUserId, Pageable pageable);


}
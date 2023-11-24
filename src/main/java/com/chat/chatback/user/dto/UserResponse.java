package com.chat.chatback.user.dto;

import com.chat.chatback.user.model.User;
import com.chat.chatback.user.model.UserRole;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Builder
public record UserResponse(
        Long id,
        String username,
        String displayName,
        UserRole role,
        String token

) implements Serializable {
}
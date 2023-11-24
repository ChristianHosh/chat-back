package com.chat.chatback.user.model;

import com.chat.chatback.user.dto.RegisterRequest;
import com.chat.chatback.user.dto.UserResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static UserResponse entityToResponse(User user){
        return entityToResponse(user, null);
    }

    public static UserResponse entityToResponse(@NotNull User user, String token){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .role(user.getRole())
                .token(token)
                .build();
    }

    public static User requestToEntity(@NotNull RegisterRequest registerRequest) {
        return User.builder()
                .username(registerRequest.username())
                .displayName(registerRequest.displayName())
                .password(encoder.encode(registerRequest.password()))
                .role(UserRole.ROLE_USER)
                .build();
    }
}

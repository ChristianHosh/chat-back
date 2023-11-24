package com.chat.chatback.security.auth;

import com.chat.chatback.error.HttpNotFoundException;
import com.chat.chatback.user.model.User;
import com.chat.chatback.user.service.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final UserRepository userRepository;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getAuthenticatedUser() {
        String username = getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new HttpNotFoundException("user not found with username '" + username + "'"));
    }

    @Override
    public User getAuthenticatedUserOrNull() {
        return userRepository.findByUsername(getAuthentication().getName())
                .orElse(null);
    }
}

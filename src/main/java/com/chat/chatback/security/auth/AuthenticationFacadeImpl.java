package com.chris.socialbox.security.auth;

import com.chris.socialbox.controller.error.DefaultErrorMessage;
import com.chris.socialbox.exception.HttpNotFoundException;
import com.chris.socialbox.model.User;
import com.chris.socialbox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade implements IAuthenticationFacade{

    private final UserRepository userRepository;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getAuthenticatedUser() {
        return userRepository.findByEmail(getAuthentication().getName())
                .orElseThrow( () -> new HttpNotFoundException(DefaultErrorMessage.USER_NOT_FOUND));
    }

    @Override
    public User getAuthenticatedUserOrNull() {
        return userRepository.findByEmail(getAuthentication().getName())
                .orElse(null);
    }
}

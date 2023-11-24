package com.chat.chatback.security.auth;

import com.chat.chatback.user.model.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
    User getAuthenticatedUser();
    User getAuthenticatedUserOrNull();
}
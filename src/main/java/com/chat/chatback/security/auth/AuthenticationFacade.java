package com.chris.socialbox.security.auth;

import com.chris.socialbox.model.User;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
    User getAuthenticatedUser();
    User getAuthenticatedUserOrNull();
}
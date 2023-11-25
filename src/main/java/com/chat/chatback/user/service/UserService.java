package com.chat.chatback.user.service;

import com.chat.chatback.error.HttpNotFoundException;
import com.chat.chatback.security.auth.AuthenticationFacade;
import com.chat.chatback.security.jwt.JwtUtils;
import com.chat.chatback.user.dto.LoginRequest;
import com.chat.chatback.user.dto.RegisterRequest;
import com.chat.chatback.user.dto.UserResponse;
import com.chat.chatback.user.model.User;
import com.chat.chatback.user.model.UserMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthenticationFacade authenticationFacade;

    private User findUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new HttpNotFoundException("user not found with id '" + id + "'"));
    }

    private User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new HttpNotFoundException("user not found with username '" + username + "'"));
    }

    public UserResponse login(@NotNull LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = findUserByUsername(loginRequest.username());

        return UserMapper.entityToResponse(user, jwt);
    }

    public UserResponse register(@NotNull RegisterRequest registerRequest) {
        userRepository.save(UserMapper.requestToEntity(registerRequest));

        return login(new LoginRequest(registerRequest.username(), registerRequest.password()));
    }

    public UserResponse currentUser() {
        User user = authenticationFacade.getAuthenticatedUser();

        return UserMapper.entityToResponse(user);
    }

    public Page<UserResponse> findUsers(int page, int size, String name){
        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPage = userRepository.findUsers(name, pageable);

        return userPage.map(UserMapper::entityToResponse);
    }
}

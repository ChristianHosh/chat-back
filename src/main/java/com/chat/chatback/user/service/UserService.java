package com.chat.chatback.user;

import com.chat.chatback.error.HttpNotFoundException;
import com.chat.chatback.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
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

    private User findUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new HttpNotFoundException("user not found with id '" + id + "'"));
    }

    private User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new HttpNotFoundException("user not found with username '" + username + "'"));
    }

    public UserResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = findUserByUsername(loginRequest.username());

        return UserMapper.entityToResponse(user, jwt);
    }

    public UserResponse register(RegisterRequest registerRequest) {
        userRepository.save(UserMapper.requestToEntity(registerRequest));

        return login(new LoginRequest(registerRequest.username(), registerRequest.password()));
    }


}

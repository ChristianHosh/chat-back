package com.chat.chatback.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserResponse login(@RequestBody @Valid LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }
}

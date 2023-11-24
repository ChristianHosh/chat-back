package com.chat.chatback.user.controller;

import com.chat.chatback.user.service.UserService;
import com.chat.chatback.user.dto.LoginRequest;
import com.chat.chatback.user.dto.RegisterRequest;
import com.chat.chatback.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/login")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserResponse login(@RequestBody @Valid LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @PostMapping("/auth/register")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @GetMapping("/")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserResponse currentUser(){
        return userService.currentUser();
    }
}

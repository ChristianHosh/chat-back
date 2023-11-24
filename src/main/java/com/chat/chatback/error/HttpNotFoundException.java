package com.chat.chatback.error;

public class HttpNotFoundException extends RuntimeException{
    public HttpNotFoundException(String message) {
        super(message);
    }
}

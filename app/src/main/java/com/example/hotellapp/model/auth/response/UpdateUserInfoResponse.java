package com.example.hotellapp.model.auth.response;

import com.example.hotellapp.model.auth.User;

public class UpdateUserInfoResponse {
    private String message;
    private User user;

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}

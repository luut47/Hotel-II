package com.example.hotellapp.model.auth;

public class AuthResponse {
    private String access_token;
    private String token_type;
    private User user;

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public User getUser() {
        return user;
    }
}

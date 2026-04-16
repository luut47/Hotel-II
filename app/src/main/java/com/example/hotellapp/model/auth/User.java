package com.example.hotellapp.model.auth;

public class User {
    private int user_id;
    private String full_name;
    private String email;
    private String phone;
    private String citizen_id;
    private String address;
    private String avatar_url;
    private String role_name;
    private String status;

    public int getUser_id() {
        return user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public String getAddress() {
        return address;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getRole_name() {
        return role_name;
    }

    public String getStatus() {
        return status;
    }
}

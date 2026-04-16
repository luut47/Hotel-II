package com.example.hotellapp.model.auth;

public class RegisterRequest {
    private String full_name;
    private String email;
    private String phone;
    private String password;
    private String citizen_id;
    private String address;

    public RegisterRequest(String full_name, String email, String phone, String password, String citizen_id, String address) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.citizen_id = citizen_id;
        this.address = address;
    }
}
